package net.mirwaldt.empty.streams.util;

import java.util.Spliterator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.*;

import static java.util.Spliterators.emptySpliterator;
import static net.mirwaldt.empty.streams.util.LazyBuildDoubleStreamUtil.EMPTY_DOUBLE_STREAM_SUPPLIER;
import static net.mirwaldt.empty.streams.util.LazyBuildIntStreamUtil.EMPTY_INT_STREAM_SUPPLIER;
import static net.mirwaldt.empty.streams.util.LazyBuildLongStreamUtil.EMPTY_LONG_STREAM_SUPPLIER;

public class LazyBuildGenericStreamUtil {
    @SuppressWarnings("Convert2Lambda") // We need an anonymous class for identity
    private final static Supplier<?> EMPTY_STREAM_SUPPLIER = new Supplier<Object>() {
        @Override
        public Object get() {
            return Stream.empty();
        }
    };

    public static <T> Supplier<Stream<T>> emptyStreamSupplier() {
        return (Supplier<Stream<T>>) EMPTY_STREAM_SUPPLIER;
    }

    public static <T> Supplier<Stream<T>> firstSupplier(Stream<T> first) {
        Spliterator<T> spliterator = first.spliterator();
        if (spliterator.equals(emptySpliterator())) {
            return emptyStreamSupplier();
        } else {
            return () -> StreamSupport.stream(spliterator, first.isParallel());
        }
    }

    public static <T, R> Supplier<Stream<R>> toStreamSupplier(
            Supplier<Stream<T>> streamSupplier, Function<Stream<T>, Stream<R>> nextOp) {
        return (streamSupplier == LazyBuildGenericStreamUtil.<T>emptyStreamSupplier())
                ? emptyStreamSupplier() : () -> nextOp.apply(streamSupplier.get());
    }

    public static <T> Supplier<IntStream> toIntStreamSupplier(
            Supplier<Stream<T>> streamSupplier, Function<Stream<T>, IntStream> nextOp) {
        return (streamSupplier == LazyBuildGenericStreamUtil.<T>emptyStreamSupplier())
                ? EMPTY_INT_STREAM_SUPPLIER : () -> nextOp.apply(streamSupplier.get());
    }

    public static <T> Supplier<LongStream> toLongStreamSupplier(
            Supplier<Stream<T>> streamSupplier, Function<Stream<T>, LongStream> nextOp) {
        return (streamSupplier == LazyBuildGenericStreamUtil.<T>emptyStreamSupplier())
                ? EMPTY_LONG_STREAM_SUPPLIER : () -> nextOp.apply(streamSupplier.get());
    }

    public static <T> Supplier<DoubleStream> toDoubleStreamSupplier(
            Supplier<Stream<T>> streamSupplier, Function<Stream<T>, DoubleStream> nextOp) {
        return (streamSupplier == LazyBuildGenericStreamUtil.<T>emptyStreamSupplier())
                ? EMPTY_DOUBLE_STREAM_SUPPLIER : () -> nextOp.apply(streamSupplier.get());
    }
}
