package net.mirwaldt.empty.streams;

import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.*;

public class AbstractLazyBuildStream<T, S extends BaseStream<T, S>> {
    protected final boolean isParallel;
    protected Supplier<S> streamSupplier;

    AbstractLazyBuildStream(boolean isParallel, Supplier<S> next) {
        this.isParallel = isParallel;
        this.streamSupplier = next;
    }

    protected S getOnce(UnaryOperator<S> parallelOperator) {
        checkIfUsed();
        S stream = parallelOperator.apply(streamSupplier.get());
        streamSupplier = () -> stream;
        return stream;
    }

    protected void clear() {
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

    protected <S> Stream<S> nextStream(Supplier<Stream<S>> nextStreamSupplier) {
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
        LazyBuildGenericStream<R> next = new LazyBuildGenericStream<R>(isParallel, nextStreamSupplier);
        clear();
        return next;
    }

    protected IntStream nextIntStream(Supplier<IntStream> nextStreamSupplier, boolean isParallel) {
        checkIfUsed();
        LazyBuildIntStream next = new LazyBuildIntStream(isParallel, nextStreamSupplier);
        clear();
        return next;
    }

    protected LongStream nextLongStream(Supplier<LongStream> nextStreamSupplier, boolean isParallel) {
        checkIfUsed();
        LazyBuildLongStream next = new LazyBuildLongStream(isParallel, nextStreamSupplier);
        clear();
        return next;
    }

    protected DoubleStream nextDoubleStream(Supplier<DoubleStream> nextStreamSupplier, boolean isParallel) {
        checkIfUsed();
        LazyBuildDoubleStream next = new LazyBuildDoubleStream(isParallel, nextStreamSupplier);
        clear();
        return next;
    }
}
