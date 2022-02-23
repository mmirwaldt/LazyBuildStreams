package net.mirwaldt.empty.streams.util;

import java.util.Spliterator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.*;

import static net.mirwaldt.empty.streams.util.LazyBuildGenericStreamUtil.emptyStreamSupplier;
import static net.mirwaldt.empty.streams.util.LazyBuildIntStreamUtil.EMPTY_INT_STREAM_SUPPLIER;
import static net.mirwaldt.empty.streams.util.LazyBuildLongStreamUtil.EMPTY_LONG_STREAM_SUPPLIER;

public class LazyBuildDoubleStreamUtil {
    public final static Supplier<DoubleStream> EMPTY_DOUBLE_STREAM_SUPPLIER = new Supplier<DoubleStream>() {
        @Override
        public DoubleStream get() {
            return DoubleStream.empty();
        }
    };

    public static Supplier<DoubleStream> firstSupplier(DoubleStream first) {
        if (first.equals(DoubleStream.empty())) {
            return EMPTY_DOUBLE_STREAM_SUPPLIER;
        } else {
            Spliterator.OfDouble spliterator = first.spliterator();
            if (0 < spliterator.estimateSize()) {
                return () -> StreamSupport.doubleStream(spliterator, false);
            } else {
                return DoubleStream::empty;
            }
        }
    }

    public static <R> Supplier<Stream<R>> toStreamSupplier(
            Supplier<DoubleStream> streamSupplier, Function<DoubleStream, Stream<R>> nextOp) {
        return (streamSupplier == EMPTY_DOUBLE_STREAM_SUPPLIER)
                ? emptyStreamSupplier() : () -> nextOp.apply(streamSupplier.get());
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
