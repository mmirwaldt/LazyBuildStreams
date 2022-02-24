package net.mirwaldt.empty.streams.demos;

import org.openjdk.jol.info.GraphLayout;

import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class PrintUtil {
    public static boolean printAll = false;

    public static <T> void printStatistics(String name, Stream<T> stream, Object...ignored) {
        printStatistics(name,
                GraphLayout.parseInstance(stream)
                        .subtract(GraphLayout.parseInstance(ignored)));
    }

    public static void printStatistics(String name, IntStream stream, Object...ignored) {
        printStatistics(name,
                GraphLayout.parseInstance(stream)
                        .subtract(GraphLayout.parseInstance(ignored)));
    }

    public static void printStatistics(String name, LongStream stream, Object...ignored) {
        printStatistics(name,
                GraphLayout.parseInstance(stream)
                        .subtract(GraphLayout.parseInstance(ignored)));
    }

    public static void printStatistics(String name, DoubleStream stream, Object...ignored) {
        printStatistics(name,
                GraphLayout.parseInstance(stream)
                        .subtract(GraphLayout.parseInstance(ignored)));
    }

    public static void printStatistics(String name, GraphLayout graphLayout) {
        System.out.println(name);
        System.out.println("totalSize=" + graphLayout.totalSize() + " bytes");
        System.out.println("totalCount=" + graphLayout.totalCount() + " objects");
        if(printAll) {
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
