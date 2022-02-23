package net.mirwaldt.empty.streams.demos;

import org.openjdk.jol.info.GraphLayout;

import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class PrintUtil {
    public static <T> void printStatistics(String name, Stream<T> stream) {
        printStatistics(name, GraphLayout.parseInstance(stream));
    }

    public static void printStatistics(String name, IntStream stream) {
        printStatistics(name, GraphLayout.parseInstance(stream));
    }

    public static void printStatistics(String name, LongStream stream) {
        printStatistics(name, GraphLayout.parseInstance(stream));
    }

    public static void printStatistics(String name, DoubleStream stream) {
        printStatistics(name, GraphLayout.parseInstance(stream));
    }

    public static void printStatistics(String name, GraphLayout graphLayout) {
        System.out.println(name);
        System.out.println("totalSize=" + graphLayout.totalSize() + " bytes");
        System.out.println("totalCount=" + graphLayout.totalCount() + " objects");
        System.out.println(graphLayout.toPrintable());
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