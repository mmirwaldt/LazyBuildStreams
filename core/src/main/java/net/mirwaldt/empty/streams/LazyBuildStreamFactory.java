package net.mirwaldt.empty.streams;

import java.util.Spliterator;
import java.util.function.Function;
import java.util.stream.*;

public class LazyBuildStreamFactory {
    private LazyBuildStreamFactory() {

    }

    public static <T> Stream<T> lazyBuildGenericStream(Stream<T> stream) {
        if(stream instanceof AbstractLazyBuildStream lazyBuildStream) {
            return new LazyBuildGenericStream<T>(lazyBuildStream);
        } else {
            return new LazyBuildGenericStream<T>(stream);
        }
    }

    public static IntStream lazyBuildIntStream(IntStream stream) {
        if(stream instanceof AbstractLazyBuildStream lazyBuildStream) {
            return new LazyBuildIntStream(lazyBuildStream);
        } else {
            return new LazyBuildIntStream(stream);
        }
    }

    public static LongStream lazyBuildLongStream(LongStream stream) {
        if(stream instanceof AbstractLazyBuildStream lazyBuildStream) {
            return new LazyBuildLongStream(lazyBuildStream);
        } else {
            return new LazyBuildLongStream(stream);
        }
    }

    public static DoubleStream lazyBuildDoubleStream(DoubleStream stream) {
        if(stream instanceof AbstractLazyBuildStream lazyBuildStream) {
            return new LazyBuildDoubleStream(lazyBuildStream);
        } else {
            return new LazyBuildDoubleStream(stream);
        }
    }

    public static <T> Stream<T> lazyBuildGenericStream(Spliterator<T> spliterator) {
        return lazyBuildGenericStream(spliterator, false);
    }

    public static IntStream lazyBuildIntStream(Spliterator.OfInt spliterator) {
        return lazyBuildIntStream(spliterator, false);
    }

    public static LongStream lazyBuildLongStream(Spliterator.OfLong spliterator) {
        return lazyBuildLongStream(spliterator, false);
    }

    public static DoubleStream lazyBuildDoubleStream(Spliterator.OfDouble spliterator) {
        return lazyBuildDoubleStream(spliterator, false);
    }

    public static <T> Stream<T> lazyBuildGenericStream(Spliterator<T> spliterator, boolean isParallel) {
        return new LazyBuildGenericStream<T>(spliterator, isParallel);
    }

    public static IntStream lazyBuildIntStream(Spliterator.OfInt spliterator, boolean isParallel) {
        return new LazyBuildIntStream(spliterator, isParallel);
    }

    public static LongStream lazyBuildLongStream(Spliterator.OfLong spliterator, boolean isParallel) {
        return new LazyBuildLongStream(spliterator, isParallel);
    }

    public static DoubleStream lazyBuildDoubleStream(Spliterator.OfDouble spliterator, boolean isParallel) {
        return new LazyBuildDoubleStream(spliterator, isParallel);
    }

    public static <S, T> Stream<T> lazyBuildFromGenericStreamToGenericStream(
            Stream<S> initialStream, Function<Stream<S>, Stream<T>> chainFunction) {
        Spliterator<S> spliterator = initialStream.spliterator();
        if (0 < spliterator.estimateSize()) {
            Stream<S> newStream = StreamSupport.stream(spliterator, initialStream.isParallel());
            return chainFunction.apply(newStream);
        } else {
            return Stream.empty();
        }
    }

    public static <T> Stream<T> lazyBuildFromIntStreamToGenericStream(
            IntStream initialStream, Function<IntStream, Stream<T>> chainFunction) {
        Spliterator.OfInt spliterator = initialStream.spliterator();
        if (0 < spliterator.estimateSize()) {
            IntStream newStream = StreamSupport.intStream(spliterator, initialStream.isParallel());
            return chainFunction.apply(newStream);
        } else {
            return Stream.empty();
        }
    }

    public static <T> Stream<T> lazyBuildFromLongStreamToGenericStream(
            LongStream initialStream, Function<LongStream, Stream<T>> chainFunction) {
        Spliterator.OfLong spliterator = initialStream.spliterator();
        if (0 < spliterator.estimateSize()) {
            LongStream newStream = StreamSupport.longStream(spliterator, initialStream.isParallel());
            return chainFunction.apply(newStream);
        } else {
            return Stream.empty();
        }
    }

    public static <T> Stream<T> lazyBuildFromDoubleStreamToGenericStream(
            DoubleStream initialStream, Function<DoubleStream, Stream<T>> chainFunction) {
        Spliterator.OfDouble spliterator = initialStream.spliterator();
        if (0 < spliterator.estimateSize()) {
            DoubleStream newStream = StreamSupport.doubleStream(spliterator, initialStream.isParallel());
            return chainFunction.apply(newStream);
        } else {
            return Stream.empty();
        }
    }

    public static <S> IntStream lazyBuildFromGenericStreamToIntStream(
            Stream<S> initialStream, Function<Stream<S>, IntStream> chainFunction) {
        Spliterator<S> spliterator = initialStream.spliterator();
        if (0 < spliterator.estimateSize()) {
            Stream<S> newStream = StreamSupport.stream(spliterator, initialStream.isParallel());
            return chainFunction.apply(newStream);
        } else {
            return IntStream.empty();
        }
    }

    public static IntStream lazyBuildFromIntStreamToIntStream(
            IntStream initialStream, Function<IntStream, IntStream> chainFunction) {
        Spliterator.OfInt spliterator = initialStream.spliterator();
        if (0 < spliterator.estimateSize()) {
            IntStream newStream = StreamSupport.intStream(spliterator, initialStream.isParallel());
            return chainFunction.apply(newStream);
        } else {
            return IntStream.empty();
        }
    }

    public static IntStream lazyBuildFromLongStreamToIntStream(
            LongStream initialStream, Function<LongStream, IntStream> chainFunction) {
        Spliterator.OfLong spliterator = initialStream.spliterator();
        if (0 < spliterator.estimateSize()) {
            LongStream newStream = StreamSupport.longStream(spliterator, initialStream.isParallel());
            return chainFunction.apply(newStream);
        } else {
            return IntStream.empty();
        }
    }

    public static IntStream lazyBuildFromDoubleStreamToIntStream(
            DoubleStream initialStream, Function<DoubleStream, IntStream> chainFunction) {
        Spliterator.OfDouble spliterator = initialStream.spliterator();
        if (0 < spliterator.estimateSize()) {
            DoubleStream newStream = StreamSupport.doubleStream(spliterator, initialStream.isParallel());
            return chainFunction.apply(newStream);
        } else {
            return IntStream.empty();
        }
    }

    public static <S> LongStream lazyBuildFromGenericStreamToLongStream(
            Stream<S> initialStream, Function<Stream<S>, LongStream> chainFunction) {
        Spliterator<S> spliterator = initialStream.spliterator();
        if (0 < spliterator.estimateSize()) {
            Stream<S> newStream = StreamSupport.stream(spliterator, initialStream.isParallel());
            return chainFunction.apply(newStream);
        } else {
            return LongStream.empty();
        }
    }

    public static LongStream lazyBuildFromIntStreamToLongStream(
            IntStream initialStream, Function<IntStream, LongStream> chainFunction) {
        Spliterator.OfInt spliterator = initialStream.spliterator();
        if (0 < spliterator.estimateSize()) {
            IntStream newStream = StreamSupport.intStream(spliterator, initialStream.isParallel());
            return chainFunction.apply(newStream);
        } else {
            return LongStream.empty();
        }
    }

    public static LongStream lazyBuildFromLongStreamToLongStream(
            LongStream initialStream, Function<LongStream, LongStream> chainFunction) {
        Spliterator.OfLong spliterator = initialStream.spliterator();
        if (0 < spliterator.estimateSize()) {
            LongStream newStream = StreamSupport.longStream(spliterator, initialStream.isParallel());
            return chainFunction.apply(newStream);
        } else {
            return LongStream.empty();
        }
    }

    public static LongStream lazyBuildFromDoubleStreamToLongStream(
            DoubleStream initialStream, Function<DoubleStream, LongStream> chainFunction) {
        Spliterator.OfDouble spliterator = initialStream.spliterator();
        if (0 < spliterator.estimateSize()) {
            DoubleStream newStream = StreamSupport.doubleStream(spliterator, initialStream.isParallel());
            return chainFunction.apply(newStream);
        } else {
            return LongStream.empty();
        }
    }

    public static <S> DoubleStream lazyBuildFromGenericStreamToDoubleStream(
            Stream<S> initialStream, Function<Stream<S>, DoubleStream> chainFunction) {
        Spliterator<S> spliterator = initialStream.spliterator();
        if (0 < spliterator.estimateSize()) {
            Stream<S> newStream = StreamSupport.stream(spliterator, initialStream.isParallel());
            return chainFunction.apply(newStream);
        } else {
            return DoubleStream.empty();
        }
    }

    public static DoubleStream lazyBuildFromIntStreamToDoubleStream(
            IntStream initialStream, Function<IntStream, DoubleStream> chainFunction) {
        Spliterator.OfInt spliterator = initialStream.spliterator();
        if (0 < spliterator.estimateSize()) {
            IntStream newStream = StreamSupport.intStream(spliterator, initialStream.isParallel());
            return chainFunction.apply(newStream);
        } else {
            return DoubleStream.empty();
        }
    }

    public static DoubleStream lazyBuildFromLongStreamToDoubleStream(
            LongStream initialStream, Function<LongStream, DoubleStream> chainFunction) {
        Spliterator.OfLong spliterator = initialStream.spliterator();
        if (0 < spliterator.estimateSize()) {
            LongStream newStream = StreamSupport.longStream(spliterator, initialStream.isParallel());
            return chainFunction.apply(newStream);
        } else {
            return DoubleStream.empty();
        }
    }

    public static DoubleStream lazyBuildFromDoubleStreamToDoubleStream(
            DoubleStream initialStream, Function<DoubleStream, DoubleStream> chainFunction) {
        Spliterator.OfDouble spliterator = initialStream.spliterator();
        if (0 < spliterator.estimateSize()) {
            DoubleStream newStream = StreamSupport.doubleStream(spliterator, initialStream.isParallel());
            return chainFunction.apply(newStream);
        } else {
            return DoubleStream.empty();
        }
    }
}
