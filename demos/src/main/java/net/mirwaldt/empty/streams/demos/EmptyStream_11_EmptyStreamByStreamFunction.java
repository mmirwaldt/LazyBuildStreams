package net.mirwaldt.empty.streams.demos;

import net.mirwaldt.empty.streams.LazyBuildStreams;
import org.openjdk.jol.info.GraphLayout;

import java.util.function.Function;
import java.util.stream.Stream;

import static net.mirwaldt.empty.streams.demos.PrintUtil.*;

public class EmptyStream_11_EmptyStreamByStreamFunction {
    public static void main(String[] args) {
        // eager build
        Stream<String> eagerBuildEmpty = Stream.empty();
        printStatistics("eagerBuildEmpty:", eagerBuildEmpty);

        printLineOfMinus();

        Stream<String> eagerBuildFilteredMappedEmpty = eagerBuildEmpty
                .filter(s -> !s.isEmpty())
                .map(String::toUpperCase);
        printStatistics("eagerBuildFilteredMappedEmpty:", eagerBuildFilteredMappedEmpty);

        printLineOfMinus();

        System.out.println("eagerBuildFilteredMappedEmpty.findFirst().orElse(\"?\")="
                + eagerBuildFilteredMappedEmpty.findFirst().orElse("?"));


        printLineOfPlus();


        // lazy build
        Function<Stream<String>, Stream<String>> streamFunction = (var stream) -> stream
                .filter(s -> !s.isEmpty())
                .map(String::toUpperCase);
        GraphLayout graphLayout = GraphLayout.parseInstance(streamFunction);
        System.out.println("streamFunction: " + graphLayout.totalSize() + " bytes");

        printLineOfMinus();

        Stream<String> lazyBuildFilteredMappedEmpty = LazyBuildStreams.fromStreamToStream(Stream.empty(), streamFunction);
        printStatistics("lazyBuildFilteredMappedEmpty:", lazyBuildFilteredMappedEmpty);

        printLineOfMinus();

        System.out.println("lazyBuildFilteredMappedEmpty.findFirst().orElse(\"?\")="
                + lazyBuildFilteredMappedEmpty.findFirst().orElse("?"));
    }
// Output:
/*
eagerBuildEmpty:
totalSize=72 bytes
totalCount=2 object(s)
------------------------------------------------------------
eagerBuildFilteredMappedEmpty:
totalSize=232 bytes
totalCount=6 object(s)
------------------------------------------------------------
eagerBuildFilteredMappedEmpty.findFirst().orElse("?")=?
++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
streamFunction: 16 bytes
------------------------------------------------------------
lazyBuildFilteredMappedEmpty:
totalSize=72 bytes
totalCount=2 object(s)
------------------------------------------------------------
lazyBuildFilteredMappedEmpty.findFirst().orElse("?")=?
 */
}
