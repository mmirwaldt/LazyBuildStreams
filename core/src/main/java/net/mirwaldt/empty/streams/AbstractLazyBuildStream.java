package net.mirwaldt.empty.streams;

import java.util.Spliterator;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;
import java.util.stream.*;

public abstract class AbstractLazyBuildStream<T, S extends BaseStream<T, S>, I extends Spliterator<T>> {
    protected final boolean isParallel;
    protected BooleanSupplier isEmpty;
    protected Supplier<S> streamSupplier;

    AbstractLazyBuildStream(boolean isParallel, I spliterator) {
        this.isParallel = isParallel;
        this.isEmpty = () -> 0 < spliterator.estimateSize();
        this.streamSupplier = firstSupplier(spliterator, isParallel);
    }
    AbstractLazyBuildStream(boolean isParallel, BooleanSupplier isEmpty, Supplier<S> streamSupplier) {
        this.isParallel = isParallel;
        this.isEmpty = isEmpty;
        this.streamSupplier = streamSupplier;
    }

    public Supplier<S> firstSupplier(I spliterator, boolean isParallel) {
        if (spliterator.equals(emptySpliterator())) {
            return emptyStreamSupplier();
        } else {
            return () -> streamFactory(spliterator, isParallel);
        }
    }

    protected S getOnce() {
        checkIfUsed();
        S stream;
        if(isEmpty.getAsBoolean()) {
            stream = (isParallel) ? streamSupplier.get().parallel() : streamSupplier.get().sequential();
        } else {
            stream = emptyStreamSupplier().get();
        }
        streamSupplier = () -> stream;
        return stream;
    }

    abstract protected S streamFactory(I spliterator, boolean isParallel);

    abstract protected I emptySpliterator();

    abstract protected Supplier<S> emptyStreamSupplier();

    protected void clear() {
        isEmpty = null;
        streamSupplier = null;
    }

    protected boolean wasUsed() {
        return streamSupplier == null;
    }

    protected void checkIfUsed() {
        if(wasUsed()) {
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
        var next = new LazyBuildGenericStream<>(isParallel, isEmpty, nextStreamSupplier);
        clear();
        return next;
    }

    protected IntStream nextIntStream(Supplier<IntStream> nextStreamSupplier, boolean isParallel) {
        checkIfUsed();
        var next = new LazyBuildIntStream(isParallel, isEmpty, nextStreamSupplier);
        clear();
        return next;
    }

    protected LongStream nextLongStream(Supplier<LongStream> nextStreamSupplier, boolean isParallel) {
        checkIfUsed();
        var next = new LazyBuildLongStream(isParallel, isEmpty, nextStreamSupplier);
        clear();
        return next;
    }

    protected DoubleStream nextDoubleStream(Supplier<DoubleStream> nextStreamSupplier, boolean isParallel) {
        checkIfUsed();
        var next = new LazyBuildDoubleStream(isParallel, isEmpty, nextStreamSupplier);
        clear();
        return next;
    }
}
