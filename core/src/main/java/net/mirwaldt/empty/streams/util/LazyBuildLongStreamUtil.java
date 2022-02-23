package net.mirwaldt.empty.streams.util;

import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static net.mirwaldt.empty.streams.util.LazyBuildDoubleStreamUtil.EMPTY_DOUBLE_STREAM_SUPPLIER;
import static net.mirwaldt.empty.streams.util.LazyBuildGenericStreamUtil.emptyGenericStreamSupplier;
import static net.mirwaldt.empty.streams.util.LazyBuildIntStreamUtil.EMPTY_INT_STREAM_SUPPLIER;

public class LazyBuildLongStreamUtil {
    private LazyBuildLongStreamUtil() {

    }

    public final static Supplier<LongStream> EMPTY_LONG_STREAM_SUPPLIER = new Supplier<LongStream>() {
        @Override
        public LongStream get() {
            return LongStream.empty();
        }
    };

    public static <R> Supplier<Stream<R>> toStreamSupplier(
            Supplier<LongStream> streamSupplier, Function<LongStream, Stream<R>> nextOp) {
        return (streamSupplier == EMPTY_LONG_STREAM_SUPPLIER)
                ? emptyGenericStreamSupplier() : () -> nextOp.apply(streamSupplier.get());
    }

    public static Supplier<IntStream> toIntStreamSupplier(
            Supplier<LongStream> streamSupplier, Function<LongStream, IntStream> nextOp) {
        return (streamSupplier == EMPTY_LONG_STREAM_SUPPLIER)
                ? EMPTY_INT_STREAM_SUPPLIER : () -> nextOp.apply(streamSupplier.get());
    }

    public static Supplier<LongStream> toLongStreamSupplier(
            Supplier<LongStream> streamSupplier, Function<LongStream, LongStream> nextOp) {
        return (streamSupplier == EMPTY_LONG_STREAM_SUPPLIER)
                ? EMPTY_LONG_STREAM_SUPPLIER : () -> nextOp.apply(streamSupplier.get());
    }

    public static Supplier<DoubleStream> toDoubleStreamSupplier(
            Supplier<LongStream> streamSupplier, Function<LongStream, DoubleStream> nextOp) {
        return (streamSupplier == EMPTY_LONG_STREAM_SUPPLIER)
                ? EMPTY_DOUBLE_STREAM_SUPPLIER : () -> nextOp.apply(streamSupplier.get());
    }
}
