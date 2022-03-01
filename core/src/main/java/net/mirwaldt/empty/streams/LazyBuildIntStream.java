package net.mirwaldt.empty.streams;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

final class LazyBuildIntStream
        extends AbstractLazyBuildStream<Integer, IntStream, Spliterator.OfInt>
        implements IntStream {
    LazyBuildIntStream(IntStream stream) {
        super(stream);
    }

    LazyBuildIntStream(Spliterator.OfInt spliterator, boolean isParallel) {
        super(spliterator, isParallel);
    }

    LazyBuildIntStream(Spliterator<?> spliterator, Function[] functions,
                       Function<BaseStream<?, ?>, BaseStream<?, ?>> function, boolean isParallel) {
        super(spliterator, functions, function, isParallel);
    }

    @Override
    public IntStream filter(IntPredicate predicate) {
        return new LazyBuildIntStream(getSpliterator(), functions(),
                (stream) -> ((IntStream) stream).filter(predicate), isParallel);
    }

    @Override
    public IntStream map(IntUnaryOperator mapper) {
        return new LazyBuildIntStream(getSpliterator(), functions(),
                (stream) -> ((IntStream) stream).map(mapper), isParallel);
    }

    @Override
    public <U> Stream<U> mapToObj(IntFunction<? extends U> mapper) {
        return new LazyBuildStream<>(getSpliterator(), functions(),
                (stream) -> ((IntStream) stream).mapToObj(mapper), isParallel);
    }

    @Override
    public LongStream mapToLong(IntToLongFunction mapper) {
        return new LazyBuildLongStream(getSpliterator(), functions(),
                (stream) -> ((IntStream) stream).mapToLong(mapper), isParallel);
    }

    @Override
    public DoubleStream mapToDouble(IntToDoubleFunction mapper) {
        return new LazyBuildDoubleStream(getSpliterator(), functions(),
                (stream) -> ((IntStream) stream).mapToDouble(mapper), isParallel);
    }

    @Override
    public IntStream flatMap(IntFunction<? extends IntStream> mapper) {
        return new LazyBuildIntStream(getSpliterator(), functions(),
                (stream) -> ((IntStream) stream).flatMap(mapper), isParallel);
    }

    @Override
    public IntStream distinct() {
        return new LazyBuildIntStream(getSpliterator(), functions(),
                (stream) -> ((IntStream) stream).distinct(), isParallel);
    }

    @Override
    public IntStream sorted() {
        return new LazyBuildIntStream(getSpliterator(), functions(),
                (stream) -> ((IntStream) stream).sorted(), isParallel);
    }

    @Override
    public IntStream peek(IntConsumer action) {
        return new LazyBuildIntStream(getSpliterator(), functions(),
                (stream) -> ((IntStream) stream).peek(action), isParallel);
    }

    @Override
    public IntStream limit(long maxSize) {
        return new LazyBuildIntStream(getSpliterator(), functions(),
                (stream) -> ((IntStream) stream).limit(maxSize), isParallel);
    }

    @Override
    public IntStream skip(long n) {
        return new LazyBuildIntStream(getSpliterator(), functions(),
                (stream) -> ((IntStream) stream).skip(n), isParallel);
    }

    @Override
    public void forEach(IntConsumer action) {
        getOnce().forEach(action);
    }

    @Override
    public void forEachOrdered(IntConsumer action) {
        getOnce().forEachOrdered(action);
    }

    @Override
    public int[] toArray() {
        return getOnce().toArray();
    }

    @Override
    public int reduce(int identity, IntBinaryOperator op) {
        return getOnce().reduce(identity, op);
    }

    @Override
    public OptionalInt reduce(IntBinaryOperator op) {
        return getOnce().reduce(op);
    }

    @Override
    public <R> R collect(Supplier<R> supplier, ObjIntConsumer<R> accumulator, BiConsumer<R, R> combiner) {
        return getOnce().collect(supplier, accumulator, combiner);
    }

    @Override
    public int sum() {
        return getOnce().sum();
    }

    @Override
    public OptionalInt min() {
        return getOnce().min();
    }

    @Override
    public OptionalInt max() {
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
    public IntSummaryStatistics summaryStatistics() {
        return getOnce().summaryStatistics();
    }

    @Override
    public boolean anyMatch(IntPredicate predicate) {
        return getOnce().anyMatch(predicate);
    }

    @Override
    public boolean allMatch(IntPredicate predicate) {
        return getOnce().allMatch(predicate);
    }

    @Override
    public boolean noneMatch(IntPredicate predicate) {
        return getOnce().noneMatch(predicate);
    }

    @Override
    public OptionalInt findFirst() {
        return getOnce().findFirst();
    }

    @Override
    public OptionalInt findAny() {
        return getOnce().findAny();
    }

    @Override
    public LongStream asLongStream() {
        return new LazyBuildLongStream(getSpliterator(), functions(),
                (stream) -> ((IntStream) stream).asLongStream(), isParallel);
    }

    @Override
    public DoubleStream asDoubleStream() {
        return new LazyBuildDoubleStream(getSpliterator(), functions(),
                (stream) -> ((IntStream) stream).asDoubleStream(), isParallel);
    }

    @Override
    public Stream<Integer> boxed() {
        return new LazyBuildStream<>(getSpliterator(), functions(),
                (stream) -> ((IntStream) stream).boxed(), isParallel);
    }

    @Override
    public IntStream sequential() {
        return new LazyBuildIntStream(getSpliterator(), functions(), Function.identity(), false);
    }

    @Override
    public IntStream parallel() {
        return new LazyBuildIntStream(getSpliterator(), functions(), Function.identity(), true);
    }

    @Override
    public IntStream unordered() {
        return new LazyBuildIntStream(getSpliterator(), functions(),
                (stream) -> ((IntStream) stream).unordered(), isParallel);
    }

    @Override
    public IntStream onClose(Runnable closeHandler) {
        return new LazyBuildIntStream(getSpliterator(), functions(),
                (stream) -> ((IntStream) stream).onClose(closeHandler), isParallel);
    }

    @Override
    public PrimitiveIterator.OfInt iterator() {
        return getOnce().iterator();
    }

    @Override
    public Spliterator.OfInt spliterator() {
        return getOnce().spliterator();
    }

    @Override
    protected IntStream emptyStream() {
        return IntStream.empty();
    }
}
