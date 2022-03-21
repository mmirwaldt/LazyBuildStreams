/*
 * Copyright (c) 2022, Michael Mirwaldt. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * <a rel="license" href="http://creativecommons.org/licenses/by-nd/4.0/">
 * <img alt="Creative Commons License" style="border-width:0" src="https://i.creativecommons.org/l/by-nd/4.0/88x31.png" />
 * </a><br /><span xmlns:dct="http://purl.org/dc/terms/" property="dct:title">
 * Lazy build streams</span> is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-nd/4.0/">
 * Creative Commons Attribution-NoDerivatives 4.0 International License</a>.
 */

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
