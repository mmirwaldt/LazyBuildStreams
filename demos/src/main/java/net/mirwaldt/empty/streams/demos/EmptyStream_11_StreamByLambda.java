package net.mirwaldt.empty.streams.demos;

import org.openjdk.jol.info.GraphLayout;

import java.util.function.Function;
import java.util.stream.Stream;

import static net.mirwaldt.empty.streams.LazyBuildStreamFactory.lazyBuildFromGenericStreamToGenericStream;

public class EmptyStream_11_StreamByLambda {
    public static void main(String[] args) {
        Function<Stream<Integer>, Stream<String>> streamFunction = (var stream) -> stream
                .filter(i -> 0 < i)
                .map(Integer::toBinaryString);
        GraphLayout graphLayout = GraphLayout.parseInstance(streamFunction);
        System.out.println("streamFunction: " + graphLayout.totalSize() + " bytes");
        System.out.println(graphLayout.toFootprint());

        System.out.println("-".repeat(120));

        Stream<String> smartEmptyStream = lazyBuildFromGenericStreamToGenericStream(Stream.empty(), streamFunction);
        GraphLayout smartEmptyStreamLayout = GraphLayout.parseInstance(smartEmptyStream);
        System.out.println("smartEmptyStream: " + smartEmptyStreamLayout.totalSize() + " bytes");
        System.out.println(smartEmptyStreamLayout.toFootprint());
        System.out.println("smartEmptyStream.toList()=" + smartEmptyStream.toList());

        System.out.println("-".repeat(120));

        Stream<String> smartNonEmptyStream = lazyBuildFromGenericStreamToGenericStream(Stream.of(0, 1, 2), streamFunction);
        GraphLayout smartNonEmptyStreamLayout = GraphLayout.parseInstance(smartNonEmptyStream);
        System.out.println("smartNonEmptyStreamLayout: " + smartNonEmptyStreamLayout.totalSize() + " bytes");
        System.out.println(smartNonEmptyStreamLayout.toFootprint());
        System.out.println("smartNonEmptyStream.toList()=" + smartNonEmptyStream.toList());
    }
}
