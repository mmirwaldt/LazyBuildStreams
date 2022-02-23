package net.mirwaldt.empty.streams;

import org.junit.jupiter.api.Test;

import java.util.function.Supplier;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static net.mirwaldt.empty.streams.util.LazyBuildDoubleStreamUtil.EMPTY_DOUBLE_STREAM_SUPPLIER;
import static net.mirwaldt.empty.streams.util.LazyBuildIntStreamUtil.EMPTY_INT_STREAM_SUPPLIER;
import static net.mirwaldt.empty.streams.util.LazyBuildLongStreamUtil.EMPTY_LONG_STREAM_SUPPLIER;
import static net.mirwaldt.empty.streams.util.LazyBuildGenericStreamUtil.*;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;

public class LazyBuildStreamUtilTest {
    @Test
    void testIdentityOfEmptyStreamSupplier() {
        var x = emptyGenericStreamSupplier();
        assertSame(emptyGenericStreamSupplier(), x);

        var y = (Supplier<Stream<Object>>) Stream::empty;
        assertNotSame(emptyGenericStreamSupplier(), y);
    }

    @Test
    void testIdentityOfEmptyIntStreamSupplier() {
        var x = EMPTY_INT_STREAM_SUPPLIER;
        assertSame(EMPTY_INT_STREAM_SUPPLIER, x);

        var y = (Supplier<IntStream>) IntStream::empty;
        assertNotSame(EMPTY_INT_STREAM_SUPPLIER, y);
    }

    @Test
    void testIdentityOfEmptyLongStreamSupplier() {
        var x = EMPTY_LONG_STREAM_SUPPLIER;
        assertSame(EMPTY_LONG_STREAM_SUPPLIER, x);

        var y = (Supplier<LongStream>) LongStream::empty;
        assertNotSame(EMPTY_LONG_STREAM_SUPPLIER, y);
    }

    @Test
    void testIdentityOfEmptyDoubleStreamSupplier() {
        var x = EMPTY_DOUBLE_STREAM_SUPPLIER;
        assertSame(EMPTY_DOUBLE_STREAM_SUPPLIER, x);

        var y = (Supplier<DoubleStream>) DoubleStream::empty;
        assertNotSame(EMPTY_DOUBLE_STREAM_SUPPLIER, y);
    }
}
