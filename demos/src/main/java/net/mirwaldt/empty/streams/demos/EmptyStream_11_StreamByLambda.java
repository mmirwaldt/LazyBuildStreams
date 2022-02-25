package net.mirwaldt.empty.streams.demos;

import org.openjdk.jol.info.GraphLayout;

import java.util.function.UnaryOperator;
import java.util.stream.Stream;

public class EmptyStream_11_StreamByLambda {
    public static void main(String[] args) {
        UnaryOperator<Stream<String>> streamOperator = (var stream) -> stream
                .filter(str -> !str.isEmpty())
                .map(String::toUpperCase);
        GraphLayout graphLayout = GraphLayout.parseInstance(streamOperator);
        System.out.println("streamOperator: " + graphLayout.totalSize() + " bytes");
        System.out.println(graphLayout.toFootprint());
        System.out.println("Stream.empty(): " + streamOperator.apply(Stream.empty()).toList());
        System.out.println("Stream.of(\"a\"): " + streamOperator.apply(Stream.of("a")).toList());
    }
}
