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

final class LazyBuildStream<T> extends AbstractLazyBuildStream<T, Stream<T>> implements Stream<T> {
    LazyBuildStream(Stream<T> stream) {
        super(stream);
    }

    LazyBuildStream(Spliterator<T> spliterator, boolean isParallel) {
        super(spliterator, isParallel);
    }

    LazyBuildStream(
            Spliterator<?> spliterator,
            Function[] functions,
            Function<BaseStream<?, ?>, BaseStream<?, ?>> function,
            boolean isParallel) {
        super(spliterator, functions, function, isParallel);
    }

    @Override
    protected Stream<T> emptyStream() {
        return Stream.empty();
    }

    @Override
    public Stream<T> filter(Predicate<? super T> predicate) {
        Objects.requireNonNull(predicate);
        return new LazyBuildStream<>(getSpliterator(), functions(),
                (stream) -> ((Stream<T>) stream).filter(predicate), isParallel);
    }

    @Override
    public <R> Stream<R> map(Function<? super T, ? extends R> mapper) {
        Objects.requireNonNull(mapper);
        return new LazyBuildStream<>(getSpliterator(), functions(),
                (stream) -> ((Stream<T>) stream).map(mapper), isParallel);
    }

    @Override
    public IntStream mapToInt(ToIntFunction<? super T> mapper) {
        Objects.requireNonNull(mapper);
        return new LazyBuildIntStream(getSpliterator(), functions(),
                (stream) -> ((Stream<T>) stream).mapToInt(mapper), isParallel);
    }

    @Override
    public LongStream mapToLong(ToLongFunction<? super T> mapper) {
        Objects.requireNonNull(mapper);
        return new LazyBuildLongStream(getSpliterator(), functions(),
                (stream) -> ((Stream<T>) stream).mapToLong(mapper), isParallel);
    }

    @Override
    public DoubleStream mapToDouble(ToDoubleFunction<? super T> mapper) {
        Objects.requireNonNull(mapper);
        return new LazyBuildDoubleStream(getSpliterator(), functions(),
                (stream) -> ((Stream<T>) stream).mapToDouble(mapper), isParallel);
    }

    @Override
    public <R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper) {
        Objects.requireNonNull(mapper);
        return new LazyBuildStream<>(getSpliterator(), functions(),
                (stream) -> ((Stream<T>) stream).flatMap(mapper), isParallel);
    }

    @Override
    public IntStream flatMapToInt(Function<? super T, ? extends IntStream> mapper) {
        Objects.requireNonNull(mapper);
        return new LazyBuildIntStream(getSpliterator(), functions(),
                (stream) -> ((Stream<T>) stream).flatMapToInt(mapper), isParallel);
    }

    @Override
    public LongStream flatMapToLong(Function<? super T, ? extends LongStream> mapper) {
        Objects.requireNonNull(mapper);
        return new LazyBuildLongStream(getSpliterator(), functions(),
                (stream) -> ((Stream<T>) stream).flatMapToLong(mapper), isParallel);
    }

    @Override
    public DoubleStream flatMapToDouble(Function<? super T, ? extends DoubleStream> mapper) {
        Objects.requireNonNull(mapper);
        return new LazyBuildDoubleStream(getSpliterator(), functions(),
                (stream) -> ((Stream<T>) stream).flatMapToDouble(mapper), isParallel);
    }

    @Override
    public Stream<T> distinct() {
        return new LazyBuildStream<>(getSpliterator(), functions(),
                (stream) -> ((Stream<T>) stream).distinct(), isParallel);
    }

    @Override
    public Stream<T> sorted() {
        return new LazyBuildStream<>(getSpliterator(), functions(),
                (stream) -> ((Stream<T>) stream).sorted(), isParallel);
    }

    @Override
    public Stream<T> sorted(Comparator<? super T> comparator) {
        Objects.requireNonNull(comparator);
        return new LazyBuildStream<>(getSpliterator(), functions(),
                (stream) -> ((Stream<T>) stream).sorted(comparator), isParallel);
    }

    @Override
    public Stream<T> peek(Consumer<? super T> action) {
        Objects.requireNonNull(action);
        return new LazyBuildStream<>(getSpliterator(), functions(),
                (stream) -> ((Stream<T>) stream).peek(action), isParallel);
    }

    @Override
    public Stream<T> limit(long maxSize) {
        return new LazyBuildStream<>(getSpliterator(), functions(),
                (stream) -> ((Stream<T>) stream).limit(maxSize), isParallel);
    }

    @Override
    public Stream<T> skip(long n) {
        return new LazyBuildStream<>(getSpliterator(), functions(),
                (stream) -> ((Stream<T>) stream).skip(n), isParallel);
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        Objects.requireNonNull(action);
        getOnce().forEach(action);
    }

    @Override
    public void forEachOrdered(Consumer<? super T> action) {
        Objects.requireNonNull(action);
        getOnce().forEachOrdered(action);
    }

    @Override
    public Object[] toArray() {
        return getOnce().toArray();
    }

    @Override
    public <A> A[] toArray(IntFunction<A[]> generator) {
        Objects.requireNonNull(generator);
        return getOnce().toArray(generator);
    }

    @Override
    public T reduce(T identity, BinaryOperator<T> accumulator) {
        Objects.requireNonNull(identity);
        Objects.requireNonNull(accumulator);
        return getOnce().reduce(identity, accumulator);
    }

    @Override
    public Optional<T> reduce(BinaryOperator<T> accumulator) {
        Objects.requireNonNull(accumulator);
        return getOnce().reduce(accumulator);
    }

    @Override
    public <U> U reduce(U identity, BiFunction<U, ? super T, U> accumulator, BinaryOperator<U> combiner) {
        Objects.requireNonNull(identity);
        Objects.requireNonNull(accumulator);
        Objects.requireNonNull(combiner);
        return getOnce().reduce(identity, accumulator, combiner);
    }

    @Override
    public <R> R collect(Supplier<R> supplier, BiConsumer<R, ? super T> accumulator, BiConsumer<R, R> combiner) {
        Objects.requireNonNull(supplier);
        Objects.requireNonNull(accumulator);
        Objects.requireNonNull(combiner);
        return getOnce().collect(supplier, accumulator, combiner);
    }

    @Override
    public <R, A> R collect(Collector<? super T, A, R> collector) {
        Objects.requireNonNull(collector);
        return getOnce().collect(collector);
    }

    @Override
    public Optional<T> min(Comparator<? super T> comparator) {
        Objects.requireNonNull(comparator);
        return getOnce().min(comparator);
    }

    @Override
    public Optional<T> max(Comparator<? super T> comparator) {
        Objects.requireNonNull(comparator);
        return getOnce().max(comparator);
    }

    @Override
    public long count() {
        return getOnce().count();
    }

    @Override
    public boolean anyMatch(Predicate<? super T> predicate) {
        Objects.requireNonNull(predicate);
        return getOnce().anyMatch(predicate);
    }

    @Override
    public boolean allMatch(Predicate<? super T> predicate) {
        Objects.requireNonNull(predicate);
        return getOnce().allMatch(predicate);
    }

    @Override
    public boolean noneMatch(Predicate<? super T> predicate) {
        Objects.requireNonNull(predicate);
        return getOnce().noneMatch(predicate);
    }

    @Override
    public Optional<T> findFirst() {
        return getOnce().findFirst();
    }

    @Override
    public Optional<T> findAny() {
        return getOnce().findAny();
    }

    @Override
    public Iterator<T> iterator() {
        return getOnce().iterator();
    }

    @Override
    public Spliterator<T> spliterator() {
        return getOnce().spliterator();
    }

    @Override
    public Stream<T> sequential() {
        return new LazyBuildStream<>(getSpliterator(), functions(), Function.identity(), false);
    }

    @Override
    public Stream<T> parallel() {
        return new LazyBuildStream<>(getSpliterator(), functions(), Function.identity(), true);
    }

    @Override
    public Stream<T> unordered() {
        return new LazyBuildStream<>(getSpliterator(), functions(),
                (stream) -> ((Stream<T>) stream).unordered(), isParallel);
    }

    @Override
    public Stream<T> onClose(Runnable closeHandler) {
        Objects.requireNonNull(closeHandler);
        return new LazyBuildStream<>(getSpliterator(), functions(),
                (stream) -> ((Stream<T>) stream).onClose(closeHandler), isParallel);
    }
}

