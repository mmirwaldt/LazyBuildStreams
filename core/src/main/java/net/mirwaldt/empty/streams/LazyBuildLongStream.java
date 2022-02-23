package net.mirwaldt.empty.streams;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

import static net.mirwaldt.empty.streams.util.LazyBuildLongStreamUtil.*;

final class LazyBuildLongStream
        extends AbstractLazyBuildStream<Long, LongStream, Spliterator.OfLong>
        implements LongStream {
    public LazyBuildLongStream(LongStream first) {
        super(first.isParallel(), first.spliterator());
    }

    public LazyBuildLongStream(Spliterator.OfLong spliterator) {
        super(spliterator);
    }

    LazyBuildLongStream(boolean isParallel, Spliterator<?> spliterator, Supplier<LongStream> streamSupplier) {
        super(isParallel, spliterator, streamSupplier);
    }

    @Override
    public LongStream filter(LongPredicate predicate) {
        return nextLongStream(toLongStreamSupplier(streamSupplier, (stream) -> stream.filter(predicate)));
    }

    @Override
    public LongStream map(LongUnaryOperator mapper) {
        return nextLongStream(toLongStreamSupplier(streamSupplier, (stream) -> stream.map(mapper)));
    }

    @Override
    public <U> Stream<U> mapToObj(LongFunction<? extends U> mapper) {
        return nextStream(toStreamSupplier(streamSupplier, (stream) -> stream.mapToObj(mapper)));
    }

    @Override
    public IntStream mapToInt(LongToIntFunction mapper) {
        return nextIntStream(toIntStreamSupplier(streamSupplier, (stream) -> stream.mapToInt(mapper)));
    }

    @Override
    public DoubleStream mapToDouble(LongToDoubleFunction mapper) {
        return nextDoubleStream(toDoubleStreamSupplier(streamSupplier, (stream) -> stream.mapToDouble(mapper)));
    }

    @Override
    public LongStream flatMap(LongFunction<? extends LongStream> mapper) {
        return nextLongStream(toLongStreamSupplier(streamSupplier, (stream) -> stream.flatMap(mapper)));
    }

    @Override
    public LongStream distinct() {
        return nextLongStream(toLongStreamSupplier(streamSupplier, LongStream::distinct));
    }

    @Override
    public LongStream sorted() {
        return nextLongStream(toLongStreamSupplier(streamSupplier, LongStream::sorted));
    }

    @Override
    public LongStream peek(LongConsumer action) {
        return nextLongStream(toLongStreamSupplier(streamSupplier, (stream) -> stream.peek(action)));
    }

    @Override
    public LongStream limit(long maxSize) {
        return nextLongStream(toLongStreamSupplier(streamSupplier, (stream) -> stream.limit(maxSize)));
    }

    @Override
    public LongStream skip(long n) {
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
        return nextDoubleStream(toDoubleStreamSupplier(streamSupplier, LongStream::asDoubleStream));
    }

    @Override
    public Stream<Long> boxed() {
        return nextStream(toStreamSupplier(streamSupplier, LongStream::boxed));
    }

    @Override
    public LongStream sequential() {
        return nextLongStream(toLongStreamSupplier(streamSupplier, LongStream::sequential), false);
    }

    @Override
    public LongStream parallel() {
        return nextLongStream(toLongStreamSupplier(streamSupplier, LongStream::parallel), true);
    }

    @Override
    public LongStream unordered() {
        return nextLongStream(toLongStreamSupplier(streamSupplier, BaseStream::unordered));
    }

    @Override
    public LongStream onClose(Runnable closeHandler) {
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
    public boolean isParallel() {
        return isParallel;
    }

    @Override
    public void close() {
        streamSupplier = LongStream::empty;
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
}
