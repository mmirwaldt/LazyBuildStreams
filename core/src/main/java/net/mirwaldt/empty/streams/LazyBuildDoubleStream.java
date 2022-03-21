/*
 * Copyright (c) 2022, Michael Mirwaldt. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * <a rel="license" href="http://creativecommons.org/licenses/by-nd/4.0/">
 * <img alt="Creative Commons License" style="border-width:0" src="https://i.creativecommons.org/l/by-nd/4.0/88x31.png" />
 * </a><br /><span xmlns:dct="http://purl.org/dc/terms/" property="dct:title">
 * Lazy build streams</span> is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-nd/4.0/">
 * Creative Commons Attribution-NoDerivatives 4.0 International License</a>.
 */

package net.mirwaldt.empty.streams;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

final class LazyBuildDoubleStream extends AbstractLazyBuildStream<Double, DoubleStream> implements DoubleStream {
    LazyBuildDoubleStream(DoubleStream stream) {
        super(stream);
    }

    LazyBuildDoubleStream(Spliterator<?> spliterator, boolean isParallel) {
        super(spliterator, isParallel);
    }

    LazyBuildDoubleStream(Spliterator<?> spliterator, Function[] functions,
                          Function<BaseStream<?, ?>, BaseStream<?, ?>> function, boolean isParallel) {
        super(spliterator, functions, function, isParallel);
    }

    @Override
    public DoubleStream filter(DoublePredicate predicate) {
        Objects.requireNonNull(predicate);
        return new LazyBuildDoubleStream(getSpliterator(), functions(),
                (stream) -> ((DoubleStream) stream).filter(predicate), isParallel);
    }

    @Override
    public DoubleStream map(DoubleUnaryOperator mapper) {
        Objects.requireNonNull(mapper);
        return new LazyBuildDoubleStream(getSpliterator(), functions(),
                (stream) -> ((DoubleStream) stream).map(mapper), isParallel);
    }

    @Override
    public <U> Stream<U> mapToObj(DoubleFunction<? extends U> mapper) {
        Objects.requireNonNull(mapper);
        return new LazyBuildStream<>(getSpliterator(), functions(),
                (stream) -> ((DoubleStream) stream).mapToObj(mapper), isParallel);
    }

    @Override
    public IntStream mapToInt(DoubleToIntFunction mapper) {
        Objects.requireNonNull(mapper);
        return new LazyBuildIntStream(getSpliterator(), functions(),
                (stream) -> ((DoubleStream) stream).mapToInt(mapper), isParallel);
    }

    @Override
    public LongStream mapToLong(DoubleToLongFunction mapper) {
        Objects.requireNonNull(mapper);
        return new LazyBuildLongStream(getSpliterator(), functions(),
                (stream) -> ((DoubleStream) stream).mapToLong(mapper), isParallel);
    }

    @Override
    public DoubleStream flatMap(DoubleFunction<? extends DoubleStream> mapper) {
        Objects.requireNonNull(mapper);
        return new LazyBuildDoubleStream(getSpliterator(), functions(),
                (stream) -> ((DoubleStream) stream).flatMap(mapper), isParallel);
    }

    @Override
    public DoubleStream distinct() {
        return new LazyBuildDoubleStream(getSpliterator(), functions(),
                (stream) -> ((DoubleStream) stream).distinct(), isParallel);
    }

    @Override
    public DoubleStream sorted() {
        return new LazyBuildDoubleStream(getSpliterator(), functions(),
                (stream) -> ((DoubleStream) stream).sorted(), isParallel);
    }

    @Override
    public DoubleStream peek(DoubleConsumer action) {
        Objects.requireNonNull(action);
        return new LazyBuildDoubleStream(getSpliterator(), functions(),
                (stream) -> ((DoubleStream) stream).peek(action), isParallel);
    }

    @Override
    public DoubleStream limit(long maxSize) {
        return new LazyBuildDoubleStream(getSpliterator(), functions(),
                (stream) -> ((DoubleStream) stream).limit(maxSize), isParallel);
    }

    @Override
    public DoubleStream skip(long n) {
        return new LazyBuildDoubleStream(getSpliterator(), functions(),
                (stream) -> ((DoubleStream) stream).skip(n), isParallel);
    }

    @Override
    public void forEach(DoubleConsumer action) {
        Objects.requireNonNull(action);
        getOnce().forEach(action);
    }

    @Override
    public void forEachOrdered(DoubleConsumer action) {
        Objects.requireNonNull(action);
        getOnce().forEachOrdered(action);
    }

    @Override
    public double[] toArray() {
        return getOnce().toArray();
    }

    @Override
    public double reduce(double identity, DoubleBinaryOperator op) {
        Objects.requireNonNull(op);
        return getOnce().reduce(identity, op);
    }

    @Override
    public OptionalDouble reduce(DoubleBinaryOperator op) {
        Objects.requireNonNull(op);
        return getOnce().reduce(op);
    }

    @Override
    public <R> R collect(Supplier<R> supplier, ObjDoubleConsumer<R> accumulator, BiConsumer<R, R> combiner) {
        Objects.requireNonNull(supplier);
        Objects.requireNonNull(accumulator);
        Objects.requireNonNull(combiner);
        return getOnce().collect(supplier, accumulator, combiner);
    }

    @Override
    public double sum() {
        return getOnce().sum();
    }

    @Override
    public OptionalDouble min() {
        return getOnce().min();
    }

    @Override
    public OptionalDouble max() {
        return getOnce().max();
    }

    @Override
    public long count() {
        return getOnce().count();
    }

    @Override
    public OptionalDouble average() {
        return getOnce().average();
    }

    @Override
    public DoubleSummaryStatistics summaryStatistics() {
        return getOnce().summaryStatistics();
    }

    @Override
    public boolean anyMatch(DoublePredicate predicate) {
        Objects.requireNonNull(predicate);
        return getOnce().anyMatch(predicate);
    }

    @Override
    public boolean allMatch(DoublePredicate predicate) {
        Objects.requireNonNull(predicate);
        return getOnce().allMatch(predicate);
    }

    @Override
    public boolean noneMatch(DoublePredicate predicate) {
        Objects.requireNonNull(predicate);
        return getOnce().noneMatch(predicate);
    }

    @Override
    public OptionalDouble findFirst() {
        return getOnce().findFirst();
    }

    @Override
    public OptionalDouble findAny() {
        return getOnce().findAny();
    }

    @Override
    public Stream<Double> boxed() {
        return new LazyBuildStream<>(getSpliterator(), functions(),
                (stream) -> ((DoubleStream) stream).boxed(), isParallel);
    }

    @Override
    public DoubleStream sequential() {
        return new LazyBuildDoubleStream(getSpliterator(), functions(), Function.identity(), false);
    }

    @Override
    public DoubleStream parallel() {
        return new LazyBuildDoubleStream(getSpliterator(), functions(), Function.identity(), true);
    }

    @Override
    public DoubleStream unordered() {
        return new LazyBuildDoubleStream(getSpliterator(), functions(),
                (stream) -> ((DoubleStream) stream).unordered(), isParallel);
    }

    @Override
    public DoubleStream onClose(Runnable closeHandler) {
        Objects.requireNonNull(closeHandler);
        return new LazyBuildDoubleStream(getSpliterator(), functions(),
                (stream) -> ((DoubleStream) stream).onClose(closeHandler), isParallel);
    }

    @Override
    public PrimitiveIterator.OfDouble iterator() {
        return getOnce().iterator();
    }

    @Override
    public Spliterator.OfDouble spliterator() {
        return getOnce().spliterator();
    }

    @Override
    protected DoubleStream emptyStream() {
        return DoubleStream.empty();
    }
}
