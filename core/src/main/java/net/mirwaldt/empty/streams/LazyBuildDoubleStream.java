package net.mirwaldt.empty.streams;

import java.util.DoubleSummaryStatistics;
import java.util.OptionalDouble;
import java.util.PrimitiveIterator;
import java.util.Spliterator;
import java.util.function.*;
import java.util.stream.*;

final class LazyBuildDoubleStream
        extends AbstractLazyBuildStream<Double, DoubleStream, Spliterator.OfDouble>
        implements DoubleStream {
    LazyBuildDoubleStream(AbstractLazyBuildStream<Double, DoubleStream, Spliterator.OfDouble> first) {
        super(first.spliterator, first.functions, first.isParallel);
    }

    LazyBuildDoubleStream(DoubleStream first) {
        super(first.spliterator(), first.isParallel());
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
        return new LazyBuildDoubleStream(getSpliterator(), functions(),
                (stream) -> ((DoubleStream) stream).filter(predicate), isParallel);
    }

    @Override
    public DoubleStream map(DoubleUnaryOperator mapper) {
        return new LazyBuildDoubleStream(getSpliterator(), functions(),
                (stream) -> ((DoubleStream) stream).map(mapper), isParallel);
    }

    @Override
    public <U> Stream<U> mapToObj(DoubleFunction<? extends U> mapper) {
        return new LazyBuildStream<>(getSpliterator(), functions(),
                (stream) -> ((DoubleStream) stream).mapToObj(mapper), isParallel);
    }

    @Override
    public IntStream mapToInt(DoubleToIntFunction mapper) {
        return new LazyBuildIntStream(getSpliterator(), functions(),
                (stream) -> ((DoubleStream) stream).mapToInt(mapper), isParallel);
    }

    @Override
    public LongStream mapToLong(DoubleToLongFunction mapper) {
        return new LazyBuildLongStream(getSpliterator(), functions(),
                (stream) -> ((DoubleStream) stream).mapToLong(mapper), isParallel);
    }

    @Override
    public DoubleStream flatMap(DoubleFunction<? extends DoubleStream> mapper) {
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
        getOnce().forEach(action);
    }

    @Override
    public void forEachOrdered(DoubleConsumer action) {
        getOnce().forEachOrdered(action);
    }

    @Override
    public double[] toArray() {
        return getOnce().toArray();
    }

    @Override
    public double reduce(double identity, DoubleBinaryOperator op) {
        return getOnce().reduce(identity, op);
    }

    @Override
    public OptionalDouble reduce(DoubleBinaryOperator op) {
        return getOnce().reduce(op);
    }

    @Override
    public <R> R collect(Supplier<R> supplier, ObjDoubleConsumer<R> accumulator, BiConsumer<R, R> combiner) {
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
        return getOnce().anyMatch(predicate);
    }

    @Override
    public boolean allMatch(DoublePredicate predicate) {
        return getOnce().allMatch(predicate);
    }

    @Override
    public boolean noneMatch(DoublePredicate predicate) {
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
