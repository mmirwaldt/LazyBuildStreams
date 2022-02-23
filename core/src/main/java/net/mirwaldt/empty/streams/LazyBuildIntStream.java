package net.mirwaldt.empty.streams;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

import static net.mirwaldt.empty.streams.util.LazyBuildIntStreamUtil.*;

final class LazyBuildIntStream
        extends AbstractLazyBuildStream<Integer, IntStream, Spliterator.OfInt>
        implements IntStream {
    public LazyBuildIntStream(IntStream first) {
        super(first.isParallel(), first.spliterator());
    }

    public LazyBuildIntStream(Spliterator.OfInt spliterator) {
        super(spliterator);
    }

    LazyBuildIntStream(boolean isParallel, Spliterator<?> spliterator, Supplier<IntStream> streamSupplier) {
        super(isParallel, spliterator, streamSupplier);
    }

    @Override
    public IntStream filter(IntPredicate predicate) {
        return nextIntStream(toIntStreamSupplier(streamSupplier, (stream) -> stream.filter(predicate)));
    }

    @Override
    public IntStream map(IntUnaryOperator mapper) {
        return nextIntStream(toIntStreamSupplier(streamSupplier, (stream) -> stream.map(mapper)));
    }

    @Override
    public <U> Stream<U> mapToObj(IntFunction<? extends U> mapper) {
        return nextStream(toStreamSupplier(streamSupplier, (stream) -> stream.mapToObj(mapper)));
    }

    @Override
    public LongStream mapToLong(IntToLongFunction mapper) {
        return nextLongStream(toLongStreamSupplier(streamSupplier, (stream) -> stream.mapToLong(mapper)));
    }

    @Override
    public DoubleStream mapToDouble(IntToDoubleFunction mapper) {
        return nextDoubleStream(toDoubleStreamSupplier(streamSupplier, (stream) -> stream.mapToDouble(mapper)));
    }

    @Override
    public IntStream flatMap(IntFunction<? extends IntStream> mapper) {
        return nextIntStream(toIntStreamSupplier(streamSupplier, (stream) -> stream.flatMap(mapper)));
    }

    @Override
    public IntStream distinct() {
        return nextIntStream(toIntStreamSupplier(streamSupplier, IntStream::distinct));
    }

    @Override
    public IntStream sorted() {
        return nextIntStream(toIntStreamSupplier(streamSupplier, IntStream::sorted));
    }

    @Override
    public IntStream peek(IntConsumer action) {
        return nextIntStream(toIntStreamSupplier(streamSupplier, (stream) -> stream.peek(action)));
    }

    @Override
    public IntStream limit(long maxSize) {
        return nextIntStream(toIntStreamSupplier(streamSupplier, (stream) -> stream.limit(maxSize)));
    }

    @Override
    public IntStream skip(long n) {
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
        return nextLongStream(toLongStreamSupplier(streamSupplier, IntStream::asLongStream));
    }

    @Override
    public DoubleStream asDoubleStream() {
        return nextDoubleStream(toDoubleStreamSupplier(streamSupplier, IntStream::asDoubleStream));
    }

    @Override
    public Stream<Integer> boxed() {
        return nextStream(toStreamSupplier(streamSupplier, IntStream::boxed));
    }

    @Override
    public IntStream sequential() {
        return nextIntStream(toIntStreamSupplier(streamSupplier, IntStream::sequential), false);
    }

    @Override
    public IntStream parallel() {
        return nextIntStream(toIntStreamSupplier(streamSupplier, IntStream::parallel), true);
    }

    @Override
    public IntStream unordered() {
        return nextIntStream(toIntStreamSupplier(streamSupplier, BaseStream::unordered));
    }

    @Override
    public IntStream onClose(Runnable closeHandler) {
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
    public boolean isParallel() {
        return isParallel;
    }

    @Override
    public void close() {
        getOnce().close();
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
}
