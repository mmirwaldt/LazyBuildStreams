package net.mirwaldt.empty.streams;

import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class LazyBuildStreamFactory {
    public static <T> Stream<T> lazyBuildGenericStream(Stream<T> stream) {
        return new LazyBuildGenericStream<T>(stream);
    }

    public static IntStream lazyBuildIntStream(IntStream stream) {
        return new LazyBuildIntStream(stream);
    }

    public static LongStream lazyBuildLongStream(LongStream stream) {
        return new LazyBuildLongStream(stream);
    }

    public static DoubleStream lazyBuildDoubleStream(DoubleStream stream) {
        return new LazyBuildDoubleStream(stream);
    }
}
