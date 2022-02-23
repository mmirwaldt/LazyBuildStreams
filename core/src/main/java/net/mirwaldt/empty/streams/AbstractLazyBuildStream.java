package net.mirwaldt.empty.streams;

import java.util.Spliterator;
import java.util.function.Supplier;
import java.util.stream.*;

abstract sealed class AbstractLazyBuildStream<T, S extends BaseStream<T, S>, I extends Spliterator<T>>
        implements Supplier<S>
        permits LazyBuildGenericStream, LazyBuildIntStream, LazyBuildLongStream, LazyBuildDoubleStream {
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

    public Supplier<S> firstSupplier(I spliterator) {
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

    abstract protected I emptySpliterator();

    abstract protected Supplier<S> emptyStreamSupplier();

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
}
