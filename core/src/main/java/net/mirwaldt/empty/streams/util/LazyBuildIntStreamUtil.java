package net.mirwaldt.empty.streams.util;

import java.util.Spliterator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.*;

import static java.util.Spliterators.emptyIntSpliterator;
import static net.mirwaldt.empty.streams.util.LazyBuildGenericStreamUtil.emptyGenericStreamSupplier;

public class LazyBuildIntStreamUtil {
    public final static Supplier<IntStream> EMPTY_INT_STREAM_SUPPLIER = new Supplier<IntStream>() {
        @Override
        public IntStream get() {
            return IntStream.empty();
        }
    };

    public static Supplier<IntStream> firstSupplier(IntStream first) {
        if (first.spliterator().equals(emptyIntSpliterator())) {
            return EMPTY_INT_STREAM_SUPPLIER;
        } else {
            Spliterator.OfInt spliterator = first.spliterator();
            if (0 < spliterator.estimateSize()) {
                return () -> StreamSupport.intStream(spliterator, false);
            } else {
                return IntStream::empty;
            }
        }
    }

    public static <R> Supplier<Stream<R>> toStreamSupplier(
            Supplier<IntStream> streamSupplier, Function<IntStream, Stream<R>> nextOp) {
        return (streamSupplier == EMPTY_INT_STREAM_SUPPLIER)
                ? emptyGenericStreamSupplier() : () -> nextOp.apply(streamSupplier.get());
    }

    public static Supplier<IntStream> toIntStreamSupplier(
            Supplier<IntStream> streamSupplier, Function<IntStream, IntStream> nextOp) {
        return (streamSupplier == EMPTY_INT_STREAM_SUPPLIER)
                ? IntStream::empty : () -> nextOp.apply(streamSupplier.get());
    }

    public static Supplier<LongStream> toLongStreamSupplier(
            Supplier<IntStream> streamSupplier, Function<IntStream, LongStream> nextOp) {
        return (streamSupplier == EMPTY_INT_STREAM_SUPPLIER)
                ? LongStream::empty : () -> nextOp.apply(streamSupplier.get());
    }

    public static Supplier<DoubleStream> toDoubleStreamSupplier(
            Supplier<IntStream> streamSupplier, Function<IntStream, DoubleStream> nextOp) {
        return (streamSupplier == EMPTY_INT_STREAM_SUPPLIER)
                ? DoubleStream::empty : () -> nextOp.apply(streamSupplier.get());
    }
}
