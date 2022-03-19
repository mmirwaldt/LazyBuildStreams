package net.mirwaldt.empty.streams;

import java.util.Objects;
import java.util.Spliterator;
import java.util.function.Function;
import java.util.stream.*;

public class LazyBuildStreams {
    private LazyBuildStreams() {

    }

    public static <T> Stream<T> ofStream(Stream<T> stream) {
        Objects.requireNonNull(stream);
        return new LazyBuildStream<>(stream);
    }

    public static IntStream ofIntStream(IntStream stream) {
        Objects.requireNonNull(stream);
        return new LazyBuildIntStream(stream);
    }

    public static LongStream ofLongStream(LongStream stream) {
        Objects.requireNonNull(stream);
        return new LazyBuildLongStream(stream);
    }

    public static DoubleStream ofDoubleStream(DoubleStream stream) {
        Objects.requireNonNull(stream);
        return new LazyBuildDoubleStream(stream);
    }

    public static <T> Stream<T> ofSpliterator(Spliterator<T> spliterator) {
        Objects.requireNonNull(spliterator);
        return ofSpliterator(spliterator, false);
    }

    public static IntStream ofIntSpliterator(Spliterator.OfInt spliterator) {
        Objects.requireNonNull(spliterator);
        return ofIntSpliterator(spliterator, false);
    }

    public static LongStream ofLongSpliterator(Spliterator.OfLong spliterator) {
        Objects.requireNonNull(spliterator);
        return ofLongSpliterator(spliterator, false);
    }

    public static DoubleStream ofDoubleSpliterator(Spliterator.OfDouble spliterator) {
        Objects.requireNonNull(spliterator);
        return ofDoubleSpliterator(spliterator, false);
    }

    public static <T> Stream<T> ofSpliterator(Spliterator<T> spliterator, boolean isParallel) {
        Objects.requireNonNull(spliterator);
        return new LazyBuildStream<T>(spliterator, isParallel);
    }

    public static IntStream ofIntSpliterator(Spliterator.OfInt spliterator, boolean isParallel) {
        Objects.requireNonNull(spliterator);
        return new LazyBuildIntStream(spliterator, isParallel);
    }

    public static LongStream ofLongSpliterator(Spliterator.OfLong spliterator, boolean isParallel) {
        Objects.requireNonNull(spliterator);
        return new LazyBuildLongStream(spliterator, isParallel);
    }

    public static DoubleStream ofDoubleSpliterator(Spliterator.OfDouble spliterator, boolean isParallel) {
        Objects.requireNonNull(spliterator);
        return new LazyBuildDoubleStream(spliterator, isParallel);
    }

    public static <S, T> Stream<T> fromStreamToStream(
            Stream<S> initialStream, Function<Stream<S>, Stream<T>> chainFunction) {
        Objects.requireNonNull(initialStream);
        Objects.requireNonNull(chainFunction);
        Spliterator<S> spliterator = initialStream.spliterator();
        if (0 < spliterator.estimateSize()) {
            Stream<S> newStream = StreamSupport.stream(spliterator, initialStream.isParallel());
            return chainFunction.apply(newStream);
        } else {
            return Stream.empty();
        }
    }

    public static <T> Stream<T> fromIntStreamToStream(
            IntStream initialStream, Function<IntStream, Stream<T>> chainFunction) {
        Objects.requireNonNull(initialStream);
        Objects.requireNonNull(chainFunction);
        Spliterator.OfInt spliterator = initialStream.spliterator();
        if (0 < spliterator.estimateSize()) {
            IntStream newStream = StreamSupport.intStream(spliterator, initialStream.isParallel());
            return chainFunction.apply(newStream);
        } else {
            return Stream.empty();
        }
    }

    public static <T> Stream<T> fromLongStreamToStream(
            LongStream initialStream, Function<LongStream, Stream<T>> chainFunction) {
        Objects.requireNonNull(initialStream);
        Objects.requireNonNull(chainFunction);
        Spliterator.OfLong spliterator = initialStream.spliterator();
        if (0 < spliterator.estimateSize()) {
            LongStream newStream = StreamSupport.longStream(spliterator, initialStream.isParallel());
            return chainFunction.apply(newStream);
        } else {
            return Stream.empty();
        }
    }

    public static <T> Stream<T> fromDoubleStreamToStream(
            DoubleStream initialStream, Function<DoubleStream, Stream<T>> chainFunction) {
        Objects.requireNonNull(initialStream);
        Objects.requireNonNull(chainFunction);
        Spliterator.OfDouble spliterator = initialStream.spliterator();
        if (0 < spliterator.estimateSize()) {
            DoubleStream newStream = StreamSupport.doubleStream(spliterator, initialStream.isParallel());
            return chainFunction.apply(newStream);
        } else {
            return Stream.empty();
        }
    }

    public static <S> IntStream fromStreamToIntStream(
            Stream<S> initialStream, Function<Stream<S>, IntStream> chainFunction) {
        Objects.requireNonNull(initialStream);
        Objects.requireNonNull(chainFunction);
        Spliterator<S> spliterator = initialStream.spliterator();
        if (0 < spliterator.estimateSize()) {
            Stream<S> newStream = StreamSupport.stream(spliterator, initialStream.isParallel());
            return chainFunction.apply(newStream);
        } else {
            return IntStream.empty();
        }
    }

    public static IntStream fromIntStreamToIntStream(
            IntStream initialStream, Function<IntStream, IntStream> chainFunction) {
        Objects.requireNonNull(initialStream);
        Objects.requireNonNull(chainFunction);
        Spliterator.OfInt spliterator = initialStream.spliterator();
        if (0 < spliterator.estimateSize()) {
            IntStream newStream = StreamSupport.intStream(spliterator, initialStream.isParallel());
            return chainFunction.apply(newStream);
        } else {
            return IntStream.empty();
        }
    }

    public static IntStream fromLongStreamToIntStream(
            LongStream initialStream, Function<LongStream, IntStream> chainFunction) {
        Objects.requireNonNull(initialStream);
        Objects.requireNonNull(chainFunction);
        Spliterator.OfLong spliterator = initialStream.spliterator();
        if (0 < spliterator.estimateSize()) {
            LongStream newStream = StreamSupport.longStream(spliterator, initialStream.isParallel());
            return chainFunction.apply(newStream);
        } else {
            return IntStream.empty();
        }
    }

    public static IntStream fromDoubleStreamToIntStream(
            DoubleStream initialStream, Function<DoubleStream, IntStream> chainFunction) {
        Objects.requireNonNull(initialStream);
        Objects.requireNonNull(chainFunction);
        Spliterator.OfDouble spliterator = initialStream.spliterator();
        if (0 < spliterator.estimateSize()) {
            DoubleStream newStream = StreamSupport.doubleStream(spliterator, initialStream.isParallel());
            return chainFunction.apply(newStream);
        } else {
            return IntStream.empty();
        }
    }

    public static <S> LongStream fromStreamToLongStream(
            Stream<S> initialStream, Function<Stream<S>, LongStream> chainFunction) {
        Objects.requireNonNull(initialStream);
        Objects.requireNonNull(chainFunction);
        Spliterator<S> spliterator = initialStream.spliterator();
        if (0 < spliterator.estimateSize()) {
            Stream<S> newStream = StreamSupport.stream(spliterator, initialStream.isParallel());
            return chainFunction.apply(newStream);
        } else {
            return LongStream.empty();
        }
    }

    public static LongStream fromIntStreamToLongStream(
            IntStream initialStream, Function<IntStream, LongStream> chainFunction) {
        Objects.requireNonNull(initialStream);
        Objects.requireNonNull(chainFunction);
        Spliterator.OfInt spliterator = initialStream.spliterator();
        if (0 < spliterator.estimateSize()) {
            IntStream newStream = StreamSupport.intStream(spliterator, initialStream.isParallel());
            return chainFunction.apply(newStream);
        } else {
            return LongStream.empty();
        }
    }

    public static LongStream fromLongStreamToLongStream(
            LongStream initialStream, Function<LongStream, LongStream> chainFunction) {
        Objects.requireNonNull(initialStream);
        Objects.requireNonNull(chainFunction);
        Spliterator.OfLong spliterator = initialStream.spliterator();
        if (0 < spliterator.estimateSize()) {
            LongStream newStream = StreamSupport.longStream(spliterator, initialStream.isParallel());
            return chainFunction.apply(newStream);
        } else {
            return LongStream.empty();
        }
    }

    public static LongStream fromDoubleStreamToLongStream(
            DoubleStream initialStream, Function<DoubleStream, LongStream> chainFunction) {
        Objects.requireNonNull(initialStream);
        Objects.requireNonNull(chainFunction);
        Spliterator.OfDouble spliterator = initialStream.spliterator();
        if (0 < spliterator.estimateSize()) {
            DoubleStream newStream = StreamSupport.doubleStream(spliterator, initialStream.isParallel());
            return chainFunction.apply(newStream);
        } else {
            return LongStream.empty();
        }
    }

    public static <S> DoubleStream fromStreamToDoubleStream(
            Stream<S> initialStream, Function<Stream<S>, DoubleStream> chainFunction) {
        Objects.requireNonNull(initialStream);
        Objects.requireNonNull(chainFunction);
        Spliterator<S> spliterator = initialStream.spliterator();
        if (0 < spliterator.estimateSize()) {
            Stream<S> newStream = StreamSupport.stream(spliterator, initialStream.isParallel());
            return chainFunction.apply(newStream);
        } else {
            return DoubleStream.empty();
        }
    }

    public static DoubleStream fromIntStreamToDoubleStream(
            IntStream initialStream, Function<IntStream, DoubleStream> chainFunction) {
        Objects.requireNonNull(initialStream);
        Objects.requireNonNull(chainFunction);
        Spliterator.OfInt spliterator = initialStream.spliterator();
        if (0 < spliterator.estimateSize()) {
            IntStream newStream = StreamSupport.intStream(spliterator, initialStream.isParallel());
            return chainFunction.apply(newStream);
        } else {
            return DoubleStream.empty();
        }
    }

    public static DoubleStream fromLongStreamToDoubleStream(
            LongStream initialStream, Function<LongStream, DoubleStream> chainFunction) {
        Objects.requireNonNull(initialStream);
        Objects.requireNonNull(chainFunction);
        Spliterator.OfLong spliterator = initialStream.spliterator();
        if (0 < spliterator.estimateSize()) {
            LongStream newStream = StreamSupport.longStream(spliterator, initialStream.isParallel());
            return chainFunction.apply(newStream);
        } else {
            return DoubleStream.empty();
        }
    }

    public static DoubleStream fromDoubleStreamToDoubleStream(
            DoubleStream initialStream, Function<DoubleStream, DoubleStream> chainFunction) {
        Objects.requireNonNull(initialStream);
        Objects.requireNonNull(chainFunction);
        Spliterator.OfDouble spliterator = initialStream.spliterator();
        if (0 < spliterator.estimateSize()) {
            DoubleStream newStream = StreamSupport.doubleStream(spliterator, initialStream.isParallel());
            return chainFunction.apply(newStream);
        } else {
            return DoubleStream.empty();
        }
    }
}
