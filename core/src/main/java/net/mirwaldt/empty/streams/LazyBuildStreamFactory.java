package net.mirwaldt.empty.streams;

import java.util.Spliterator;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

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
}
