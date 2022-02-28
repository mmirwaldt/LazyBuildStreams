package net.mirwaldt.empty.streams;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Optional;
import java.util.Spliterator;
import java.util.function.*;
import java.util.stream.*;

final class LazyBuildGenericStream<T>
        extends AbstractLazyBuildStream<T, Stream<T>, Spliterator<T>>
        implements Stream<T> {
    LazyBuildGenericStream(AbstractLazyBuildStream<T, Stream<T>, Spliterator<T>> first) {
        super(first.spliterator, first.functions, first.isParallel);
    }

    LazyBuildGenericStream(Stream<T> first) {
        super(first.spliterator(), first.isParallel());
    }

    LazyBuildGenericStream(Spliterator<T> spliterator, boolean isParallel) {
        super(spliterator, isParallel);
    }

    LazyBuildGenericStream(
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
        return new LazyBuildGenericStream<>(getSpliterator(), functions(),
                (stream) -> ((Stream<T>) stream).filter(predicate), isParallel);
    }

    @Override
    public <R> Stream<R> map(Function<? super T, ? extends R> mapper) {
        return new LazyBuildGenericStream<>(getSpliterator(), functions(),
                (stream) -> ((Stream<T>) stream).map(mapper), isParallel);
    }

    @Override
    public IntStream mapToInt(ToIntFunction<? super T> mapper) {
        return new LazyBuildIntStream(getSpliterator(), functions(),
                (stream) -> ((Stream<T>) stream).mapToInt(mapper), isParallel);
    }

    @Override
    public LongStream mapToLong(ToLongFunction<? super T> mapper) {
        return new LazyBuildLongStream(getSpliterator(), functions(),
                (stream) -> ((Stream<T>) stream).mapToLong(mapper), isParallel);
    }

    @Override
    public DoubleStream mapToDouble(ToDoubleFunction<? super T> mapper) {
        return new LazyBuildDoubleStream(getSpliterator(), functions(),
                (stream) -> ((Stream<T>) stream).mapToDouble(mapper), isParallel);
    }

    @Override
    public <R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper) {
        return new LazyBuildGenericStream<>(getSpliterator(), functions(),
                (stream) -> ((Stream<T>) stream).flatMap(mapper), isParallel);
    }

    @Override
    public IntStream flatMapToInt(Function<? super T, ? extends IntStream> mapper) {
        return new LazyBuildIntStream(getSpliterator(), functions(),
                (stream) -> ((Stream<T>) stream).flatMapToInt(mapper), isParallel);
    }

    @Override
    public LongStream flatMapToLong(Function<? super T, ? extends LongStream> mapper) {
        return new LazyBuildLongStream(getSpliterator(), functions(),
                (stream) -> ((Stream<T>) stream).flatMapToLong(mapper), isParallel);
    }

    @Override
    public DoubleStream flatMapToDouble(Function<? super T, ? extends DoubleStream> mapper) {
        return new LazyBuildDoubleStream(getSpliterator(), functions(),
                (stream) -> ((Stream<T>) stream).flatMapToDouble(mapper), isParallel);
    }

    @Override
    public Stream<T> distinct() {
        return new LazyBuildGenericStream<>(getSpliterator(), functions(),
                (stream) -> ((Stream<T>) stream).distinct(), isParallel);
    }

    @Override
    public Stream<T> sorted() {
        return new LazyBuildGenericStream<>(getSpliterator(), functions(),
                (stream) -> ((Stream<T>) stream).sorted(), isParallel);
    }

    @Override
    public Stream<T> sorted(Comparator<? super T> comparator) {
        return new LazyBuildGenericStream<>(getSpliterator(), functions(),
                (stream) -> ((Stream<T>) stream).sorted(comparator), isParallel);
    }

    @Override
    public Stream<T> peek(Consumer<? super T> action) {
        return new LazyBuildGenericStream<>(getSpliterator(), functions(),
                (stream) -> ((Stream<T>) stream).peek(action), isParallel);
    }

    @Override
    public Stream<T> limit(long maxSize) {
        return new LazyBuildGenericStream<>(getSpliterator(), functions(),
                (stream) -> ((Stream<T>) stream).limit(maxSize), isParallel);
    }

    @Override
    public Stream<T> skip(long n) {
        return new LazyBuildGenericStream<>(getSpliterator(), functions(),
                (stream) -> ((Stream<T>) stream).skip(n), isParallel);
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        getOnce().forEach(action);
    }

    @Override
    public void forEachOrdered(Consumer<? super T> action) {
        getOnce().forEachOrdered(action);
    }

    @Override
    public Object[] toArray() {
        return getOnce().toArray();
    }

    @Override
    public <A> A[] toArray(IntFunction<A[]> generator) {
        return getOnce().toArray(generator);
    }

    @Override
    public T reduce(T identity, BinaryOperator<T> accumulator) {
        return getOnce().reduce(identity, accumulator);
    }

    @Override
    public Optional<T> reduce(BinaryOperator<T> accumulator) {
        return getOnce().reduce(accumulator);
    }

    @Override
    public <U> U reduce(U identity, BiFunction<U, ? super T, U> accumulator, BinaryOperator<U> combiner) {
        return getOnce().reduce(identity, accumulator, combiner);
    }

    @Override
    public <R> R collect(Supplier<R> supplier, BiConsumer<R, ? super T> accumulator, BiConsumer<R, R> combiner) {
        return getOnce().collect(supplier, accumulator, combiner);
    }

    @Override
    public <R, A> R collect(Collector<? super T, A, R> collector) {
        return getOnce().collect(collector);
    }

    @Override
    public Optional<T> min(Comparator<? super T> comparator) {
        return getOnce().min(comparator);
    }

    @Override
    public Optional<T> max(Comparator<? super T> comparator) {
        return getOnce().max(comparator);
    }

    @Override
    public long count() {
        return getOnce().count();
    }

    @Override
    public boolean anyMatch(Predicate<? super T> predicate) {
        return getOnce().anyMatch(predicate);
    }

    @Override
    public boolean allMatch(Predicate<? super T> predicate) {
        return getOnce().allMatch(predicate);
    }

    @Override
    public boolean noneMatch(Predicate<? super T> predicate) {
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
        return new LazyBuildGenericStream<>(getSpliterator(), functions(), Function.identity(), false);
    }

    @Override
    public Stream<T> parallel() {
        return new LazyBuildGenericStream<>(getSpliterator(), functions(), Function.identity(), true);
    }

    @Override
    public Stream<T> unordered() {
        return new LazyBuildGenericStream<>(getSpliterator(), functions(),
                (stream) -> ((Stream<T>) stream).unordered(), isParallel);
    }

    @Override
    public Stream<T> onClose(Runnable closeHandler) {
        return new LazyBuildGenericStream<>(getSpliterator(), functions(),
                (stream) -> ((Stream<T>) stream).onClose(closeHandler), isParallel);
    }
}

