package net.mirwaldt.empty.streams;

import java.util.Spliterator;
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

    protected final boolean isParallel;
    protected Spliterator<?> spliterator;
    protected Supplier<S> streamSupplier;

    AbstractLazyBuildStream(I spliterator) {
        this(false, spliterator);
    }

    AbstractLazyBuildStream(boolean isParallel, I spliterator) {
        this.isParallel = isParallel;
        this.spliterator = spliterator;
        this.streamSupplier = firstSupplier(spliterator);
    }

    AbstractLazyBuildStream(boolean isParallel, Spliterator<?> spliterator, Supplier<S> streamSupplier) {
        this.isParallel = isParallel;
        this.spliterator = spliterator;
        this.streamSupplier = streamSupplier;
    }

    abstract protected I emptySpliterator();

    abstract protected Supplier<S> emptyStreamSupplier();

    @Override
    public boolean isParallel() {
        return isParallel;
    }

    @Override
    public void close() {
        streamSupplier = null;
    }

    private Supplier<S> firstSupplier(Spliterator<?> spliterator) {
        if (spliterator.equals(emptySpliterator())) {
            return emptyStreamSupplier();
        } else {
            return this;
        }
    }

    protected S getOnce() {
        checkIfUsed();
        S stream;
        if (0 < spliterator.estimateSize()) {
            stream = streamSupplier.get();
        } else {
            stream = emptyStreamSupplier().get();
        }
        streamSupplier = () -> stream;
        return stream;
    }


    protected void clear() {
        if(streamSupplier != this) {
            spliterator = null;
        }
        streamSupplier = null;
    }

    protected boolean wasUsed() {
        return streamSupplier == null;
    }

    protected void checkIfUsed() {
        if (wasUsed()) {
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
        checkIfUsed();
        var next = new LazyBuildGenericStream<>(isParallel, spliterator, nextStreamSupplier);
        clear();
        return next;
    }

    protected IntStream nextIntStream(Supplier<IntStream> nextStreamSupplier, boolean isParallel) {
        checkIfUsed();
        var next = new LazyBuildIntStream(isParallel, spliterator, nextStreamSupplier);
        clear();
        return next;
    }

    protected LongStream nextLongStream(Supplier<LongStream> nextStreamSupplier, boolean isParallel) {
        checkIfUsed();
        var next = new LazyBuildLongStream(isParallel, spliterator, nextStreamSupplier);
        clear();
        return next;
    }

    protected DoubleStream nextDoubleStream(Supplier<DoubleStream> nextStreamSupplier, boolean isParallel) {
        checkIfUsed();
        var next = new LazyBuildDoubleStream(isParallel, spliterator, nextStreamSupplier);
        clear();
        return next;
    }

    public static <T> Supplier<Stream<T>> emptyGenericStreamSupplier() {
        return (Supplier<Stream<T>>) EMPTY_STREAM_SUPPLIER;
    }
}
