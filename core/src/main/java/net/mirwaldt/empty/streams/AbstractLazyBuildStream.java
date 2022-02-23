package net.mirwaldt.empty.streams;

import java.util.Spliterator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.*;

abstract sealed class AbstractLazyBuildStream<T, S extends BaseStream<T, S>, I extends Spliterator<T>>
        implements Supplier<S>, BaseStream<T, S>
        permits LazyBuildGenericStream, LazyBuildIntStream, LazyBuildLongStream, LazyBuildDoubleStream {
    @SuppressWarnings("Convert2Lambda")
    private final static Supplier<?> EMPTY_STREAM_SUPPLIER = new Supplier<Object>() {
        @Override
        public Object get() {
            return Stream.empty();
        }
    };

    protected final static Supplier<LongStream> EMPTY_LONG_STREAM_SUPPLIER = new Supplier<LongStream>() {
        @Override
        public LongStream get() {
            return LongStream.empty();
        }
    };

    protected final static Supplier<IntStream> EMPTY_INT_STREAM_SUPPLIER = new Supplier<IntStream>() {
        @Override
        public IntStream get() {
            return IntStream.empty();
        }
    };

    protected final static Supplier<DoubleStream> EMPTY_DOUBLE_STREAM_SUPPLIER = new Supplier<DoubleStream>() {
        @Override
        public DoubleStream get() {
            return DoubleStream.empty();
        }
    };

    private final boolean isFirst;
    protected Spliterator<?> spliterator;
    protected Supplier<S> streamSupplier;
    protected final boolean isParallel;

    private boolean wasUsedOrClose;

    AbstractLazyBuildStream(I spliterator, boolean isParallel) {
        this.isFirst = true;
        this.spliterator = spliterator;
        this.streamSupplier = null;
        this.isParallel = isParallel;
    }

    AbstractLazyBuildStream(Spliterator<?> spliterator, Supplier<S> streamSupplier, boolean isParallel) {
        this.isFirst = false;
        this.spliterator = spliterator;
        this.streamSupplier = streamSupplier;
        this.isParallel = isParallel;
    }

    abstract protected I emptySpliterator();

    abstract protected Supplier<S> emptyStreamSupplier();

    @Override
    public boolean isParallel() {
        return isParallel;
    }

    @Override
    public void close() {
        wasUsedOrClose = true;
        spliterator = null;
        streamSupplier = null;
    }

    protected void initIfFirst() {
        checkIfUsedOrClosed();
        if (isFirst) {
            streamSupplier = firstSupplier();
        }
    }

    private Supplier<S> firstSupplier() {
        if (spliterator.equals(emptySpliterator())) {
            spliterator = null;
            return emptyStreamSupplier();
        } else {
            return this;
        }
    }

    protected S getOnce() {
        checkIfUsedOrClosed();
        S stream;
        if (spliterator != null && 0 < spliterator.estimateSize()) {
            stream = streamSupplier.get();
        } else {
            stream = emptyStreamSupplier().get();
        }
        close();
        return stream;
    }

    protected void clear() {
        wasUsedOrClose = true;
        if (streamSupplier != this) {
            spliterator = null;
        }
        streamSupplier = null;
    }

    protected void checkIfUsedOrClosed() {
        if (wasUsedOrClose) {
            throw new IllegalStateException("stream has already been operated upon or closed");
        }
    }

    protected <U> Stream<U> nextStream(Supplier<Stream<U>> nextStreamSupplier) {
        return nextStream(nextStreamSupplier, isParallel);
    }

    protected IntStream nextIntStream(Supplier<IntStream> nextStreamSupplier) {
        return nextIntStream(nextStreamSupplier, isParallel);
    }

    protected LongStream nextLongStream(Supplier<LongStream> nextStreamSupplier) {
        return nextLongStream(nextStreamSupplier, isParallel);
    }

    protected DoubleStream nextDoubleStream(Supplier<DoubleStream> nextStreamSupplier) {
        return nextDoubleStream(nextStreamSupplier, isParallel);
    }

    protected <R> Stream<R> nextStream(Supplier<Stream<R>> nextStreamSupplier, boolean isParallel) {
        checkIfUsedOrClosed();
        var next = new LazyBuildGenericStream<>(spliterator, nextStreamSupplier, isParallel);
        clear();
        return next;
    }

    protected IntStream nextIntStream(Supplier<IntStream> nextStreamSupplier, boolean isParallel) {
        checkIfUsedOrClosed();
        var next = new LazyBuildIntStream(spliterator, nextStreamSupplier, isParallel);
        clear();
        return next;
    }

    protected LongStream nextLongStream(Supplier<LongStream> nextStreamSupplier, boolean isParallel) {
        checkIfUsedOrClosed();
        var next = new LazyBuildLongStream(spliterator, nextStreamSupplier, isParallel);
        clear();
        return next;
    }

    protected DoubleStream nextDoubleStream(Supplier<DoubleStream> nextStreamSupplier, boolean isParallel) {
        checkIfUsedOrClosed();
        var next = new LazyBuildDoubleStream(spliterator, nextStreamSupplier, isParallel);
        clear();
        return next;
    }

    public static <T> Supplier<Stream<T>> emptyGenericStreamSupplier() {
        return (Supplier<Stream<T>>) EMPTY_STREAM_SUPPLIER;
    }

    protected static <U, V extends BaseStream<U, V>> boolean isEmpty(Supplier<V> streamSupplier) {
        return streamSupplier == emptyGenericStreamSupplier()
                || streamSupplier == EMPTY_INT_STREAM_SUPPLIER
                || streamSupplier == EMPTY_LONG_STREAM_SUPPLIER
                || streamSupplier == EMPTY_DOUBLE_STREAM_SUPPLIER;
    }
}
