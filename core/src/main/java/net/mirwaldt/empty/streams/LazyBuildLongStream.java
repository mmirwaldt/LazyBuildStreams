package net.mirwaldt.empty.streams;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

final class LazyBuildLongStream
        extends AbstractLazyBuildStream<Long, LongStream, Spliterator.OfLong>
        implements LongStream {
    LazyBuildLongStream(LongStream stream) {
        super(stream);
    }

    LazyBuildLongStream(Spliterator.OfLong spliterator, boolean isParallel) {
        super(spliterator, isParallel);
    }

    LazyBuildLongStream(Spliterator<?> spliterator, Function[] functions,
                        Function<BaseStream<?, ?>, BaseStream<?, ?>> function, boolean isParallel) {
        super(spliterator, functions, function, isParallel);
    }

    @Override
    public LongStream filter(LongPredicate predicate) {
        return new LazyBuildLongStream(getSpliterator(), functions(),
                (stream) -> ((LongStream) stream).filter(predicate), isParallel);
    }

    @Override
    public LongStream map(LongUnaryOperator mapper) {
        return new LazyBuildLongStream(getSpliterator(), functions(),
                (stream) -> ((LongStream) stream).map(mapper), isParallel);
    }

    @Override
    public <U> Stream<U> mapToObj(LongFunction<? extends U> mapper) {
        return new LazyBuildStream(getSpliterator(), functions(),
                (stream) -> ((LongStream) stream).mapToObj(mapper), isParallel);
    }

    @Override
    public IntStream mapToInt(LongToIntFunction mapper) {
        return new LazyBuildIntStream(getSpliterator(), functions(),
                (stream) -> ((LongStream) stream).mapToInt(mapper), isParallel);
    }

    @Override
    public DoubleStream mapToDouble(LongToDoubleFunction mapper) {
        return new LazyBuildDoubleStream(getSpliterator(), functions(),
                (stream) -> ((LongStream) stream).mapToDouble(mapper), isParallel);
    }

    @Override
    public LongStream flatMap(LongFunction<? extends LongStream> mapper) {
        return new LazyBuildLongStream(getSpliterator(), functions(),
                (stream) -> ((LongStream) stream).flatMap(mapper), isParallel);
    }

    @Override
    public LongStream distinct() {
        return new LazyBuildLongStream(getSpliterator(), functions(),
                (stream) -> ((LongStream) stream).distinct(), isParallel);
    }

    @Override
    public LongStream sorted() {
        return new LazyBuildLongStream(getSpliterator(), functions(),
                (stream) -> ((LongStream) stream).sorted(), isParallel);
    }

    @Override
    public LongStream peek(LongConsumer action) {
        return new LazyBuildLongStream(getSpliterator(), functions(),
                (stream) -> ((LongStream) stream).peek(action), isParallel);
    }

    @Override
    public LongStream limit(long maxSize) {
        return new LazyBuildLongStream(getSpliterator(), functions(),
                (stream) -> ((LongStream) stream).limit(maxSize), isParallel);
    }

    @Override
    public LongStream skip(long n) {
        return new LazyBuildLongStream(getSpliterator(), functions(),
                (stream) -> ((LongStream) stream).skip(n), isParallel);
    }

    @Override
    public void forEach(LongConsumer action) {
        getOnce().forEach(action);
    }

    @Override
    public void forEachOrdered(LongConsumer action) {
        getOnce().forEachOrdered(action);
    }

    @Override
    public long[] toArray() {
        return getOnce().toArray();
    }

    @Override
    public long reduce(long identity, LongBinaryOperator op) {
        return getOnce().reduce(identity, op);
    }

    @Override
    public OptionalLong reduce(LongBinaryOperator op) {
        return getOnce().reduce(op);
    }

    @Override
    public <R> R collect(Supplier<R> supplier, ObjLongConsumer<R> accumulator, BiConsumer<R, R> combiner) {
        return getOnce().collect(supplier, accumulator, combiner);
    }

    @Override
    public long sum() {
        return getOnce().sum();
    }

    @Override
    public OptionalLong min() {
        return getOnce().min();
    }

    @Override
    public OptionalLong max() {
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
    public LongSummaryStatistics summaryStatistics() {
        return getOnce().summaryStatistics();
    }

    @Override
    public boolean anyMatch(LongPredicate predicate) {
        return getOnce().anyMatch(predicate);
    }

    @Override
    public boolean allMatch(LongPredicate predicate) {
        return getOnce().allMatch(predicate);
    }

    @Override
    public boolean noneMatch(LongPredicate predicate) {
        return getOnce().noneMatch(predicate);
    }

    @Override
    public OptionalLong findFirst() {
        return getOnce().findFirst();
    }

    @Override
    public OptionalLong findAny() {
        return getOnce().findAny();
    }

    @Override
    public DoubleStream asDoubleStream() {
        return new LazyBuildDoubleStream(getSpliterator(), functions(),
                (stream) -> ((LongStream) stream).asDoubleStream(), isParallel);
    }

    @Override
    public Stream<Long> boxed() {
        return new LazyBuildStream<>(getSpliterator(), functions(),
                (stream) -> ((LongStream) stream).boxed(), isParallel);
    }

    @Override
    public LongStream sequential() {
        return new LazyBuildLongStream(getSpliterator(), functions(), Function.identity(), false);
    }

    @Override
    public LongStream parallel() {
        return new LazyBuildLongStream(getSpliterator(), functions(), Function.identity(), true);
    }

    @Override
    public LongStream unordered() {
        return new LazyBuildLongStream(getSpliterator(), functions(),
                (stream) -> ((LongStream) stream).unordered(), isParallel);
    }

    @Override
    public LongStream onClose(Runnable closeHandler) {
        return new LazyBuildLongStream(getSpliterator(), functions(),
                (stream) -> ((LongStream) stream).onClose(closeHandler), isParallel);
    }

    @Override
    public PrimitiveIterator.OfLong iterator() {
        return getOnce().iterator();
    }

    @Override
    public Spliterator.OfLong spliterator() {
        return getOnce().spliterator();
    }

    @Override
    protected LongStream emptyStream() {
        return LongStream.empty();
    }
}
