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

package net.mirwaldt.empty.streams.demos;

import org.openjdk.jol.info.GraphLayout;

import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class PrintUtil {
    public static boolean printAll = false;

    public static <T> void printStatistics(String name, Stream<T> stream, Object... ignored) {
        printStatistics(name,
                GraphLayout.parseInstance(stream)
                        .subtract(GraphLayout.parseInstance(ignored)));
    }

    public static void printStatistics(String name, IntStream stream, Object... ignored) {
        printStatistics(name,
                GraphLayout.parseInstance(stream)
                        .subtract(GraphLayout.parseInstance(ignored)));
    }

    public static void printStatistics(String name, LongStream stream, Object... ignored) {
        printStatistics(name,
                GraphLayout.parseInstance(stream)
                        .subtract(GraphLayout.parseInstance(ignored)));
    }

    public static void printStatistics(String name, DoubleStream stream, Object... ignored) {
        printStatistics(name,
                GraphLayout.parseInstance(stream)
                        .subtract(GraphLayout.parseInstance(ignored)));
    }

    public static void printStatistics(String name, GraphLayout graphLayout) {
        System.out.println(name);
        System.out.println("totalSize=" + graphLayout.totalSize() + " bytes");
        System.out.println("totalCount=" + graphLayout.totalCount() + " object(s)");
        if (printAll) {
            System.out.println(graphLayout.toFootprint());
        }
    }

    public static void printLineOfMinus() {
        System.out.println("-".repeat(60));
    }

    public static void printLineOfPlus() {
        System.out.println("+".repeat(60));
    }

    public static void printLineOfSharp() {
        System.out.println("#".repeat(60));
    }
}
