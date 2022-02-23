package net.mirwaldt.empty.streams;


import java.util.*;
import java.util.function.*;
import java.util.stream.*;

final class LazyBuildGenericStream<T>
        extends AbstractLazyBuildStream<T, Stream<T>, Spliterator<T>>
        implements Stream<T> {
    public LazyBuildGenericStream(Stream<T> first) {
        super(first.isParallel(), first.spliterator());
    }

    public LazyBuildGenericStream(Spliterator<T> spliterator) {
        super(spliterator);
    }

    LazyBuildGenericStream(boolean isParallel, Spliterator<?> spliterator, Supplier<Stream<T>> streamSupplier) {
        super(isParallel, spliterator, streamSupplier);
    }

    @Override
    public Stream<T> filter(Predicate<? super T> predicate) {
        return nextStream(toStreamSupplier(streamSupplier, (stream) -> stream.filter(predicate)));
    }

    @Override
    public <R> Stream<R> map(Function<? super T, ? extends R> mapper) {
        return nextStream(toStreamSupplier(streamSupplier, (stream) -> stream.map(mapper)));
    }

    @Override
    public IntStream mapToInt(ToIntFunction<? super T> mapper) {
        return nextIntStream(toIntStreamSupplier(streamSupplier, (stream) -> stream.mapToInt(mapper)));
    }

    @Override
    public LongStream mapToLong(ToLongFunction<? super T> mapper) {
        return nextLongStream(toLongStreamSupplier(streamSupplier, (stream) -> stream.mapToLong(mapper)));
    }

    @Override
    public DoubleStream mapToDouble(ToDoubleFunction<? super T> mapper) {
        return nextDoubleStream(toDoubleStreamSupplier(streamSupplier, (stream) -> stream.mapToDouble(mapper)));
    }

    @Override
    public <R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper) {
        return nextStream(toStreamSupplier(streamSupplier, (stream) -> stream.flatMap(mapper)));
    }

    @Override
    public IntStream flatMapToInt(Function<? super T, ? extends IntStream> mapper) {
        return nextIntStream(toIntStreamSupplier(streamSupplier, (stream) -> stream.flatMapToInt(mapper)));
    }

    @Override
    public LongStream flatMapToLong(Function<? super T, ? extends LongStream> mapper) {
        return nextLongStream(toLongStreamSupplier(streamSupplier, (stream) -> flatMapToLong(mapper)));
    }

    @Override
    public DoubleStream flatMapToDouble(Function<? super T, ? extends DoubleStream> mapper) {
        return nextDoubleStream(toDoubleStreamSupplier(streamSupplier, (stream) -> flatMapToDouble(mapper)));
    }

    @Override
    public Stream<T> distinct() {
        return nextStream(toStreamSupplier(streamSupplier, Stream::distinct));
    }

    @Override
    public Stream<T> sorted() {
        return nextStream(toStreamSupplier(streamSupplier, Stream::sorted));
    }

    @Override
    public Stream<T> sorted(Comparator<? super T> comparator) {
        return nextStream(toStreamSupplier(streamSupplier, (stream) -> stream.sorted(comparator)));
    }

    @Override
    public Stream<T> peek(Consumer<? super T> action) {
        return nextStream(toStreamSupplier(streamSupplier, (stream) -> stream.peek(action)));
    }

    @Override
    public Stream<T> limit(long maxSize) {
        return nextStream(toStreamSupplier(streamSupplier, (stream) -> stream.limit(maxSize)));
    }

    @Override
    public Stream<T> skip(long n) {
        return nextStream(toStreamSupplier(streamSupplier, (stream) -> stream.skip(n)));
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
        return nextStream(toStreamSupplier(streamSupplier, BaseStream::sequential), false);
    }

    @Override
    public Stream<T> parallel() {
        return nextStream(toStreamSupplier(streamSupplier, BaseStream::parallel), true);
    }

    @Override
    public Stream<T> unordered() {
        return nextStream(toStreamSupplier(streamSupplier, BaseStream::unordered));
    }

    @Override
    public Stream<T> onClose(Runnable closeHandler) {
        return nextStream(toStreamSupplier(streamSupplier, (stream) -> stream.onClose(closeHandler)));
    }

    @Override
    protected Spliterator<T> emptySpliterator() {
        return Spliterators.emptySpliterator();
    }

    @Override
    protected Supplier<Stream<T>> emptyStreamSupplier() {
        return emptyGenericStreamSupplier();
    }

    @Override
    public Stream<T> get() {
        return StreamSupport.stream((Spliterator<T>) spliterator, isParallel);
    }

    public static <T, R> Supplier<Stream<R>> toStreamSupplier(
            Supplier<Stream<T>> streamSupplier, Function<Stream<T>, Stream<R>> nextOp) {
        return (streamSupplier == AbstractLazyBuildStream.<T>emptyGenericStreamSupplier())
                ? emptyGenericStreamSupplier() : () -> nextOp.apply(streamSupplier.get());
    }

    public static <T> Supplier<IntStream> toIntStreamSupplier(
            Supplier<Stream<T>> streamSupplier, Function<Stream<T>, IntStream> nextOp) {
        return (streamSupplier == AbstractLazyBuildStream.<T>emptyGenericStreamSupplier())
                ? EMPTY_INT_STREAM_SUPPLIER : () -> nextOp.apply(streamSupplier.get());
    }

    public static <T> Supplier<LongStream> toLongStreamSupplier(
            Supplier<Stream<T>> streamSupplier, Function<Stream<T>, LongStream> nextOp) {
        return (streamSupplier == AbstractLazyBuildStream.<T>emptyGenericStreamSupplier())
                ? EMPTY_LONG_STREAM_SUPPLIER : () -> nextOp.apply(streamSupplier.get());
    }

    public static <T> Supplier<DoubleStream> toDoubleStreamSupplier(
            Supplier<Stream<T>> streamSupplier, Function<Stream<T>, DoubleStream> nextOp) {
        return (streamSupplier == AbstractLazyBuildStream.<T>emptyGenericStreamSupplier())
                ? EMPTY_DOUBLE_STREAM_SUPPLIER : () -> nextOp.apply(streamSupplier.get());
    }
}

