package net.mirwaldt.empty.streams;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

final class LazyBuildLongStream
        extends AbstractLazyBuildStream<Long, LongStream, Spliterator.OfLong>
        implements LongStream {
    LazyBuildLongStream(LongStream first) {
        super(first.spliterator(), first.isParallel());
    }

    LazyBuildLongStream(Spliterator.OfLong spliterator, boolean isParallel) {
        super(spliterator, isParallel);
    }

    LazyBuildLongStream(Spliterator<?> spliterator, Supplier<LongStream> streamSupplier, boolean isParallel) {
        super(spliterator, streamSupplier, isParallel);
    }

    @Override
    public LongStream filter(LongPredicate predicate) {
        initIfFirst();
        return nextLongStream(toLongStreamSupplier(streamSupplier, (stream) -> stream.filter(predicate)));
    }

    @Override
    public LongStream map(LongUnaryOperator mapper) {
        initIfFirst();
        return nextLongStream(toLongStreamSupplier(streamSupplier, (stream) -> stream.map(mapper)));
    }

    @Override
    public <U> Stream<U> mapToObj(LongFunction<? extends U> mapper) {
        initIfFirst();
        return nextStream(toStreamSupplier(streamSupplier, (stream) -> stream.mapToObj(mapper)));
    }

    @Override
    public IntStream mapToInt(LongToIntFunction mapper) {
        initIfFirst();
        return nextIntStream(toIntStreamSupplier(streamSupplier, (stream) -> stream.mapToInt(mapper)));
    }

    @Override
    public DoubleStream mapToDouble(LongToDoubleFunction mapper) {
        initIfFirst();
        return nextDoubleStream(toDoubleStreamSupplier(streamSupplier, (stream) -> stream.mapToDouble(mapper)));
    }

    @Override
    public LongStream flatMap(LongFunction<? extends LongStream> mapper) {
        initIfFirst();
        return nextLongStream(toLongStreamSupplier(streamSupplier, (stream) -> stream.flatMap(mapper)));
    }

    @Override
    public LongStream distinct() {
        initIfFirst();
        return nextLongStream(toLongStreamSupplier(streamSupplier, LongStream::distinct));
    }

    @Override
    public LongStream sorted() {
        initIfFirst();
        return nextLongStream(toLongStreamSupplier(streamSupplier, LongStream::sorted));
    }

    @Override
    public LongStream peek(LongConsumer action) {
        initIfFirst();
        return nextLongStream(toLongStreamSupplier(streamSupplier, (stream) -> stream.peek(action)));
    }

    @Override
    public LongStream limit(long maxSize) {
        initIfFirst();
        return nextLongStream(toLongStreamSupplier(streamSupplier, (stream) -> stream.limit(maxSize)));
    }

    @Override
    public LongStream skip(long n) {
        initIfFirst();
        return nextLongStream(toLongStreamSupplier(streamSupplier, (stream) -> stream.skip(n)));
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
        initIfFirst();
        return nextDoubleStream(toDoubleStreamSupplier(streamSupplier, LongStream::asDoubleStream));
    }

    @Override
    public Stream<Long> boxed() {
        initIfFirst();
        return nextStream(toStreamSupplier(streamSupplier, LongStream::boxed));
    }

    @Override
    public LongStream sequential() {
        initIfFirst();
        return nextLongStream(toLongStreamSupplier(streamSupplier, LongStream::sequential), false);
    }

    @Override
    public LongStream parallel() {
        initIfFirst();
        return nextLongStream(toLongStreamSupplier(streamSupplier, LongStream::parallel), true);
    }

    @Override
    public LongStream unordered() {
        initIfFirst();
        return nextLongStream(toLongStreamSupplier(streamSupplier, BaseStream::unordered));
    }

    @Override
    public LongStream onClose(Runnable closeHandler) {
        initIfFirst();
        return nextLongStream(toLongStreamSupplier(streamSupplier, (stream) -> stream.onClose(closeHandler)));
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
    protected Spliterator.OfLong emptySpliterator() {
        return Spliterators.emptyLongSpliterator();
    }

    @Override
    protected Supplier<LongStream> emptyStreamSupplier() {
        return EMPTY_LONG_STREAM_SUPPLIER;
    }

    @Override
    public LongStream get() {
        return StreamSupport.longStream((Spliterator.OfLong) spliterator, isParallel);
    }

    public static <R> Supplier<Stream<R>> toStreamSupplier(
            Supplier<LongStream> streamSupplier, Function<LongStream, Stream<R>> nextOp) {
        return (isEmpty(streamSupplier)) ? emptyGenericStreamSupplier() : () -> nextOp.apply(streamSupplier.get());
    }

    public static Supplier<IntStream> toIntStreamSupplier(
            Supplier<LongStream> streamSupplier, Function<LongStream, IntStream> nextOp) {
        return (isEmpty(streamSupplier)) ? EMPTY_INT_STREAM_SUPPLIER : () -> nextOp.apply(streamSupplier.get());
    }

    public static Supplier<LongStream> toLongStreamSupplier(
            Supplier<LongStream> streamSupplier, Function<LongStream, LongStream> nextOp) {
        return (isEmpty(streamSupplier)) ? EMPTY_LONG_STREAM_SUPPLIER : () -> nextOp.apply(streamSupplier.get());
    }

    public static Supplier<DoubleStream> toDoubleStreamSupplier(
            Supplier<LongStream> streamSupplier, Function<LongStream, DoubleStream> nextOp) {
        return (isEmpty(streamSupplier)) ? EMPTY_DOUBLE_STREAM_SUPPLIER : () -> nextOp.apply(streamSupplier.get());
    }
}
