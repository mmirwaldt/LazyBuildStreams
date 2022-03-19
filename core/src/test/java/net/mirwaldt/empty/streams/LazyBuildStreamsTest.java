package net.mirwaldt.empty.streams;

import org.junit.jupiter.api.Test;

import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static net.mirwaldt.empty.streams.LazyBuildStreams.*;
import static org.junit.jupiter.api.Assertions.*;

public class LazyBuildStreamsTest {
    @Test
    void testParallelGenericStreams() {
        assertFalse(ofStream(Stream.empty()).isParallel());
        assertFalse(ofStream(Stream.empty().sequential()).isParallel());
        assertTrue(ofStream(Stream.empty().parallel()).isParallel());
        assertFalse(ofStream(Stream.empty().parallel().sequential()).isParallel());
        assertTrue(ofStream(Stream.empty().sequential().parallel()).isParallel());

        assertFalse(ofStream(Stream.empty()).sequential().isParallel());
        assertTrue(ofStream(Stream.empty()).parallel().isParallel());
        assertTrue(ofStream(Stream.empty()).sequential().parallel().isParallel());
        assertFalse(ofStream(Stream.empty()).parallel().sequential().isParallel());
    }

    @Test
    void testParallelIntStreams() {
        assertFalse(ofIntStream(IntStream.empty()).isParallel());
        assertFalse(ofIntStream(IntStream.empty().sequential()).isParallel());
        assertTrue(ofIntStream(IntStream.empty().parallel()).isParallel());
        assertFalse(ofIntStream(IntStream.empty().parallel().sequential()).isParallel());
        assertTrue(ofIntStream(IntStream.empty().sequential().parallel()).isParallel());

        assertFalse(ofIntStream(IntStream.empty()).sequential().isParallel());
        assertTrue(ofIntStream(IntStream.empty()).parallel().isParallel());
        assertTrue(ofIntStream(IntStream.empty()).sequential().parallel().isParallel());
        assertFalse(ofIntStream(IntStream.empty()).parallel().sequential().isParallel());
    }

    @Test
    void testParallelLongStreams() {
        assertFalse(ofLongStream(LongStream.empty()).isParallel());
        assertFalse(ofLongStream(LongStream.empty().sequential()).isParallel());
        assertTrue(ofLongStream(LongStream.empty().parallel()).isParallel());
        assertFalse(ofLongStream(LongStream.empty().parallel().sequential()).isParallel());
        assertTrue(ofLongStream(LongStream.empty().sequential().parallel()).isParallel());

        assertFalse(ofLongStream(LongStream.empty()).sequential().isParallel());
        assertTrue(ofLongStream(LongStream.empty()).parallel().isParallel());
        assertTrue(ofLongStream(LongStream.empty()).sequential().parallel().isParallel());
        assertFalse(ofLongStream(LongStream.empty()).parallel().sequential().isParallel());
    }

    @Test
    void testParallelDoubleStreams() {
        assertFalse(ofDoubleStream(DoubleStream.empty()).isParallel());
        assertFalse(ofDoubleStream(DoubleStream.empty().sequential()).isParallel());
        assertTrue(ofDoubleStream(DoubleStream.empty().parallel()).isParallel());
        assertFalse(ofDoubleStream(DoubleStream.empty().parallel().sequential()).isParallel());
        assertTrue(ofDoubleStream(DoubleStream.empty().sequential().parallel()).isParallel());

        assertFalse(ofDoubleStream(DoubleStream.empty()).sequential().isParallel());
        assertTrue(ofDoubleStream(DoubleStream.empty()).parallel().isParallel());
        assertTrue(ofDoubleStream(DoubleStream.empty()).sequential().parallel().isParallel());
        assertFalse(ofDoubleStream(DoubleStream.empty()).parallel().sequential().isParallel());
    }
}
