package net.mirwaldt.empty.streams.supplier;

import org.junit.jupiter.api.Test;

import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static net.mirwaldt.empty.streams.LazyBuildStreamFactory.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LazyBuildStreamUtilTest {
    @Test
    void testParallelGenericStreams() {
        assertFalse(lazyBuildGenericStream(Stream.empty()).isParallel());
        assertFalse(lazyBuildGenericStream(Stream.empty().sequential()).isParallel());
        assertTrue(lazyBuildGenericStream(Stream.empty().parallel()).isParallel());
        assertFalse(lazyBuildGenericStream(Stream.empty().parallel().sequential()).isParallel());
        assertTrue(lazyBuildGenericStream(Stream.empty().sequential().parallel()).isParallel());

        assertFalse(lazyBuildGenericStream(Stream.empty().sequential()).isParallel());
        assertTrue(lazyBuildGenericStream(Stream.empty()).parallel().isParallel());
        assertTrue(lazyBuildGenericStream(Stream.empty().sequential().parallel()).isParallel());
        assertFalse(lazyBuildGenericStream(Stream.empty()).parallel().sequential().isParallel());
    }

    @Test
    void testParallelIntStreams() {
        assertFalse(lazyBuildIntStream(IntStream.empty()).isParallel());
        assertFalse(lazyBuildIntStream(IntStream.empty().sequential()).isParallel());
        assertTrue(lazyBuildIntStream(IntStream.empty().parallel()).isParallel());
        assertFalse(lazyBuildIntStream(IntStream.empty().parallel().sequential()).isParallel());
        assertTrue(lazyBuildIntStream(IntStream.empty().sequential().parallel()).isParallel());

        assertFalse(lazyBuildIntStream(IntStream.empty().sequential()).isParallel());
        assertTrue(lazyBuildIntStream(IntStream.empty()).parallel().isParallel());
        assertTrue(lazyBuildIntStream(IntStream.empty().sequential().parallel()).isParallel());
        assertFalse(lazyBuildIntStream(IntStream.empty()).parallel().sequential().isParallel());
    }

    @Test
    void testParallelLongStreams() {
        assertFalse(lazyBuildLongStream(LongStream.empty()).isParallel());
        assertFalse(lazyBuildLongStream(LongStream.empty().sequential()).isParallel());
        assertTrue(lazyBuildLongStream(LongStream.empty().parallel()).isParallel());
        assertFalse(lazyBuildLongStream(LongStream.empty().parallel().sequential()).isParallel());
        assertTrue(lazyBuildLongStream(LongStream.empty().sequential().parallel()).isParallel());

        assertFalse(lazyBuildLongStream(LongStream.empty().sequential()).isParallel());
        assertTrue(lazyBuildLongStream(LongStream.empty()).parallel().isParallel());
        assertTrue(lazyBuildLongStream(LongStream.empty().sequential().parallel()).isParallel());
        assertFalse(lazyBuildLongStream(LongStream.empty()).parallel().sequential().isParallel());
    }

    @Test
    void testParallelDoubleStreams() {
        assertFalse(lazyBuildDoubleStream(DoubleStream.empty()).isParallel());
        assertFalse(lazyBuildDoubleStream(DoubleStream.empty().sequential()).isParallel());
        assertTrue(lazyBuildDoubleStream(DoubleStream.empty().parallel()).isParallel());
        assertFalse(lazyBuildDoubleStream(DoubleStream.empty().parallel().sequential()).isParallel());
        assertTrue(lazyBuildDoubleStream(DoubleStream.empty().sequential().parallel()).isParallel());

        assertFalse(lazyBuildDoubleStream(DoubleStream.empty().sequential()).isParallel());
        assertTrue(lazyBuildDoubleStream(DoubleStream.empty()).parallel().isParallel());
        assertTrue(lazyBuildDoubleStream(DoubleStream.empty().sequential().parallel()).isParallel());
        assertFalse(lazyBuildDoubleStream(DoubleStream.empty()).parallel().sequential().isParallel());
    }
}