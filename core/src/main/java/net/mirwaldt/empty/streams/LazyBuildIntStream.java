package net.mirwaldt.empty.streams;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

final class LazyBuildIntStream
        extends AbstractLazyBuildStream<Integer, IntStream, Spliterator.OfInt>
        implements IntStream {
    LazyBuildIntStream(AbstractLazyBuildStream<Integer, IntStream, Spliterator.OfInt> first) {
        super(first.spliterator, first.streamSupplier, first.isParallel);
    }

    LazyBuildIntStream(IntStream first) {
        super(first.spliterator(), first.isParallel());
    }

    LazyBuildIntStream(Spliterator.OfInt spliterator, boolean isParallel) {
        super(spliterator, isParallel);
    }

    LazyBuildIntStream(Spliterator<?> spliterator, Supplier<IntStream> streamSupplier, boolean isParallel) {
        super(spliterator, streamSupplier, isParallel);
    }

    @Override
    public IntStream filter(IntPredicate predicate) {
        initIfFirst();
        return nextIntStream(toIntStreamSupplier(streamSupplier, (stream) -> stream.filter(predicate)));
    }

    @Override
    public IntStream map(IntUnaryOperator mapper) {
        initIfFirst();
        return nextIntStream(toIntStreamSupplier(streamSupplier, (stream) -> stream.map(mapper)));
    }

    @Override
    public <U> Stream<U> mapToObj(IntFunction<? extends U> mapper) {
        initIfFirst();
        return nextStream(toStreamSupplier(streamSupplier, (stream) -> stream.mapToObj(mapper)));
    }

    @Override
    public LongStream mapToLong(IntToLongFunction mapper) {
        initIfFirst();
        return nextLongStream(toLongStreamSupplier(streamSupplier, (stream) -> stream.mapToLong(mapper)));
    }

    @Override
    public DoubleStream mapToDouble(IntToDoubleFunction mapper) {
        initIfFirst();
        return nextDoubleStream(toDoubleStreamSupplier(streamSupplier, (stream) -> stream.mapToDouble(mapper)));
    }

    @Override
    public IntStream flatMap(IntFunction<? extends IntStream> mapper) {
        initIfFirst();
        return nextIntStream(toIntStreamSupplier(streamSupplier, (stream) -> stream.flatMap(mapper)));
    }

    @Override
    public IntStream distinct() {
        initIfFirst();
        return nextIntStream(toIntStreamSupplier(streamSupplier, IntStream::distinct));
    }

    @Override
    public IntStream sorted() {
        initIfFirst();
        return nextIntStream(toIntStreamSupplier(streamSupplier, IntStream::sorted));
    }

    @Override
    public IntStream peek(IntConsumer action) {
        initIfFirst();
        return nextIntStream(toIntStreamSupplier(streamSupplier, (stream) -> stream.peek(action)));
    }

    @Override
    public IntStream limit(long maxSize) {
        initIfFirst();
        return nextIntStream(toIntStreamSupplier(streamSupplier, (stream) -> stream.limit(maxSize)));
    }

    @Override
    public IntStream skip(long n) {
        initIfFirst();
        return nextIntStream(toIntStreamSupplier(streamSupplier, (stream) -> stream.skip(n)));
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
        initIfFirst();
        return nextLongStream(toLongStreamSupplier(streamSupplier, IntStream::asLongStream));
    }

    @Override
    public DoubleStream asDoubleStream() {
        initIfFirst();
        return nextDoubleStream(toDoubleStreamSupplier(streamSupplier, IntStream::asDoubleStream));
    }

    @Override
    public Stream<Integer> boxed() {
        initIfFirst();
        return nextStream(toStreamSupplier(streamSupplier, IntStream::boxed));
    }

    @Override
    public IntStream sequential() {
        initIfFirst();
        return nextIntStream(toIntStreamSupplier(streamSupplier, s -> s), false);
    }

    @Override
    public IntStream parallel() {
        initIfFirst();
        return nextIntStream(toIntStreamSupplier(streamSupplier, s -> s), true);
    }

    @Override
    public IntStream unordered() {
        initIfFirst();
        return nextIntStream(toIntStreamSupplier(streamSupplier, BaseStream::unordered));
    }

    @Override
    public IntStream onClose(Runnable closeHandler) {
        initIfFirst();
        return nextIntStream(toIntStreamSupplier(streamSupplier, (stream) -> stream.onClose(closeHandler)));
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
    protected Spliterator.OfInt emptySpliterator() {
        return Spliterators.emptyIntSpliterator();
    }

    @Override
    protected Supplier<IntStream> emptyStreamSupplier() {
        return EMPTY_INT_STREAM_SUPPLIER;
    }

    @Override
    public IntStream get() {
        return StreamSupport.intStream((Spliterator.OfInt) spliterator, isParallel);
    }


    public static <R> Supplier<Stream<R>> toStreamSupplier(
            Supplier<IntStream> streamSupplier, Function<IntStream, Stream<R>> nextOp) {
        return (isEmpty(streamSupplier)) ? emptyGenericStreamSupplier() : () -> nextOp.apply(streamSupplier.get());
    }

    public static Supplier<IntStream> toIntStreamSupplier(
            Supplier<IntStream> streamSupplier, Function<IntStream, IntStream> nextOp) {
        return (isEmpty(streamSupplier))
                ? EMPTY_INT_STREAM_SUPPLIER
                : (isIdentity(nextOp)) ? streamSupplier : () -> nextOp.apply(streamSupplier.get());
    }

    public static Supplier<LongStream> toLongStreamSupplier(
            Supplier<IntStream> streamSupplier, Function<IntStream, LongStream> nextOp) {
        return (isEmpty(streamSupplier)) ? EMPTY_LONG_STREAM_SUPPLIER : () -> nextOp.apply(streamSupplier.get());
    }

    public static Supplier<DoubleStream> toDoubleStreamSupplier(
            Supplier<IntStream> streamSupplier, Function<IntStream, DoubleStream> nextOp) {
        return (isEmpty(streamSupplier)) ? EMPTY_DOUBLE_STREAM_SUPPLIER : () -> nextOp.apply(streamSupplier.get());
    }
}
