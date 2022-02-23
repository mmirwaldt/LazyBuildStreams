package net.mirwaldt.empty.streams;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

final class LazyBuildDoubleStream
        extends AbstractLazyBuildStream<Double, DoubleStream, Spliterator.OfDouble>
        implements DoubleStream {
    public LazyBuildDoubleStream(DoubleStream first) {
        super(first.isParallel(), first.spliterator());
    }

    public LazyBuildDoubleStream(Spliterator.OfDouble spliterator) {
        super(spliterator);
    }

    LazyBuildDoubleStream(boolean isParallel, Spliterator<?> spliterator, Supplier<DoubleStream> streamSupplier) {
        super(isParallel, spliterator, streamSupplier);
    }

    @Override
    public DoubleStream filter(DoublePredicate predicate) {
        return nextDoubleStream(toDoubleStreamSupplier(streamSupplier, (stream) -> stream.filter(predicate)));
    }

    @Override
    public DoubleStream map(DoubleUnaryOperator mapper) {
        return nextDoubleStream(toDoubleStreamSupplier(streamSupplier, (stream) -> stream.map(mapper)));
    }

    @Override
    public <U> Stream<U> mapToObj(DoubleFunction<? extends U> mapper) {
        return nextStream(toStreamSupplier(streamSupplier, (stream) -> stream.mapToObj(mapper)));
    }

    @Override
    public IntStream mapToInt(DoubleToIntFunction mapper) {
        return nextIntStream(toIntStreamSupplier(streamSupplier, (stream) -> stream.mapToInt(mapper)));
    }

    @Override
    public LongStream mapToLong(DoubleToLongFunction mapper) {
        return nextLongStream(toLongStreamSupplier(streamSupplier, (stream) -> stream.mapToLong(mapper)));
    }

    @Override
    public DoubleStream flatMap(DoubleFunction<? extends DoubleStream> mapper) {
        return nextDoubleStream(toDoubleStreamSupplier(streamSupplier, (stream) -> stream.flatMap(mapper)));
    }

    @Override
    public DoubleStream distinct() {
        return nextDoubleStream(toDoubleStreamSupplier(streamSupplier, DoubleStream::distinct));
    }

    @Override
    public DoubleStream sorted() {
        return nextDoubleStream(toDoubleStreamSupplier(streamSupplier, DoubleStream::sorted));
    }

    @Override
    public DoubleStream peek(DoubleConsumer action) {
        return nextDoubleStream(toDoubleStreamSupplier(streamSupplier, (stream) -> stream.peek(action)));
    }

    @Override
    public DoubleStream limit(long maxSize) {
        return nextDoubleStream(toDoubleStreamSupplier(streamSupplier, (stream) -> stream.limit(maxSize)));
    }

    @Override
    public DoubleStream skip(long n) {
        return nextDoubleStream(toDoubleStreamSupplier(streamSupplier, (stream) -> stream.skip(n)));
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
        return nextStream(toStreamSupplier(streamSupplier, DoubleStream::boxed));
    }

    @Override
    public DoubleStream sequential() {
        return nextDoubleStream(toDoubleStreamSupplier(streamSupplier, DoubleStream::sequential), false);
    }

    @Override
    public DoubleStream parallel() {
        return nextDoubleStream(toDoubleStreamSupplier(streamSupplier, DoubleStream::parallel), true);
    }

    @Override
    public DoubleStream unordered() {
        return nextDoubleStream(toDoubleStreamSupplier(streamSupplier, BaseStream::unordered));
    }

    @Override
    public DoubleStream onClose(Runnable closeHandler) {
        return nextDoubleStream(toDoubleStreamSupplier(streamSupplier, (stream) -> stream.onClose(closeHandler)));
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
    protected Spliterator.OfDouble emptySpliterator() {
        return Spliterators.emptyDoubleSpliterator();
    }

    @Override
    protected Supplier<DoubleStream> emptyStreamSupplier() {
        return EMPTY_DOUBLE_STREAM_SUPPLIER;
    }

    @Override
    public DoubleStream get() {
        return StreamSupport.doubleStream((Spliterator.OfDouble) spliterator, isParallel);
    }


    public static <R> Supplier<Stream<R>> toStreamSupplier(
            Supplier<DoubleStream> streamSupplier, Function<DoubleStream, Stream<R>> nextOp) {
        return (streamSupplier == EMPTY_DOUBLE_STREAM_SUPPLIER)
                ? emptyGenericStreamSupplier() : () -> nextOp.apply(streamSupplier.get());
    }

    public static Supplier<IntStream> toIntStreamSupplier(
            Supplier<DoubleStream> streamSupplier, Function<DoubleStream, IntStream> nextOp) {
        return (streamSupplier == EMPTY_DOUBLE_STREAM_SUPPLIER)
                ? EMPTY_INT_STREAM_SUPPLIER : () -> nextOp.apply(streamSupplier.get());
    }

    public static Supplier<LongStream> toLongStreamSupplier(
            Supplier<DoubleStream> streamSupplier, Function<DoubleStream, LongStream> nextOp) {
        return (streamSupplier == EMPTY_DOUBLE_STREAM_SUPPLIER)
                ? EMPTY_LONG_STREAM_SUPPLIER : () -> nextOp.apply(streamSupplier.get());
    }

    public static Supplier<DoubleStream> toDoubleStreamSupplier(
            Supplier<DoubleStream> streamSupplier, Function<DoubleStream, DoubleStream> nextOp) {
        return (streamSupplier == EMPTY_DOUBLE_STREAM_SUPPLIER)
                ? EMPTY_DOUBLE_STREAM_SUPPLIER : () -> nextOp.apply(streamSupplier.get());
    }
}
