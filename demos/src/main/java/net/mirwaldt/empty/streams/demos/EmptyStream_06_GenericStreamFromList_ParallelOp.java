package net.mirwaldt.empty.streams.demos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static net.mirwaldt.empty.streams.LazyBuildStreamFactory.lazyBuildGenericStream;
import static net.mirwaldt.empty.streams.demos.PrintUtil.*;

/**
 * Use with VM option -Djol.magicFieldOffset=true
 */
public class EmptyStream_06_GenericStreamFromList_ParallelOp {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        printLineOfSharp(); // ############################################################

        // empty builds
        System.out.println("list=" + list);

        printLineOfSharp(); // ############################################################

        // eager build of empty
        Stream<String> eagerBuildEmpty = list.stream();
        printStatistics("eagerBuildEmpty:", eagerBuildEmpty);

        printLineOfMinus(); // ------------------------------------------------------------

        Stream<String> eagerBuildFilteredEmpty = eagerBuildEmpty.filter(s -> !s.isEmpty());
        printStatistics("eagerBuildFilteredEmpty:", eagerBuildFilteredEmpty);

        printLineOfMinus(); // ------------------------------------------------------------

        Stream<String> eagerBuildFilteredMappedEmpty = eagerBuildFilteredEmpty.map(String::toUpperCase);
        printStatistics("eagerBuildFilteredMappedEmpty:", eagerBuildFilteredMappedEmpty);

        printLineOfMinus(); // ------------------------------------------------------------

        Stream<String> eagerBuildFilteredMappedParallelEmpty = eagerBuildFilteredMappedEmpty.parallel();
        printStatistics("eagerBuildFilteredMappedParallelEmpty:", eagerBuildFilteredMappedParallelEmpty);

        printLineOfMinus(); // ------------------------------------------------------------

        System.out.println("eagerBuildFilteredMappedParallelEmpty.findFirst().orElse(\"?\")="
                + eagerBuildFilteredMappedParallelEmpty.findFirst().orElse("?"));


        printLineOfPlus();  // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


        // lazy build of empty
        Stream<String> lazyBuildEmpty = lazyBuildGenericStream(list.stream());
        printStatistics("lazyBuildEmpty:", lazyBuildEmpty);

        printLineOfMinus(); // ------------------------------------------------------------

        Stream<String> lazyBuildFilteredEmpty = lazyBuildEmpty.filter(s -> !s.isEmpty());
        printStatistics("lazyBuildFilteredEmpty:", lazyBuildFilteredEmpty);

        printLineOfMinus(); // ------------------------------------------------------------

        Stream<String> lazyBuildFilteredMappedEmpty = lazyBuildFilteredEmpty.map(String::toUpperCase);
        printStatistics("lazyBuildFilteredMappedEmpty:", lazyBuildFilteredMappedEmpty);

        printLineOfMinus(); // ------------------------------------------------------------

        Stream<String> lazyBuildFilteredMappedParallelEmpty = lazyBuildFilteredMappedEmpty.parallel();
        printStatistics("lazyBuildFilteredMappedParallelEmpty:", lazyBuildFilteredMappedParallelEmpty);

        printLineOfMinus(); // ------------------------------------------------------------

        System.out.println("lazyBuildFilteredMappedParallelEmpty.findFirst().orElse(\"?\")="
                + lazyBuildFilteredMappedParallelEmpty.findFirst().orElse("?"));


        printLineOfSharp(); // ############################################################

        // non-empty builds
        list.add("");
        list.add("Heinz");
        list.add("Michael");
        System.out.println("list=" + list.stream().map(s -> "\"" + s + "\"").toList());

        printLineOfSharp(); // ############################################################

        // eager build of empty
        Stream<String> eagerBuildNonEmpty = list.stream();
        printStatistics("eagerBuildNonEmpty:", eagerBuildNonEmpty);

        printLineOfMinus(); // ------------------------------------------------------------

        Stream<String> eagerBuildFilteredNonEmpty = eagerBuildNonEmpty.filter(s -> !s.isEmpty());
        printStatistics("eagerBuildFilteredNonEmpty:", eagerBuildFilteredNonEmpty);

        printLineOfMinus(); // ------------------------------------------------------------

        Stream<String> eagerBuildFilteredMappedNonEmpty = eagerBuildFilteredNonEmpty.map(String::toUpperCase);
        printStatistics("eagerBuildFilteredMappedNonEmpty:", eagerBuildFilteredMappedNonEmpty);

        printLineOfMinus(); // ------------------------------------------------------------

        Stream<String> eagerBuildFilteredMappedParallelNonEmpty = eagerBuildFilteredMappedNonEmpty.parallel();
        printStatistics("eagerBuildFilteredMappedParallelNonEmpty:", eagerBuildFilteredMappedParallelNonEmpty);

        printLineOfMinus(); // ------------------------------------------------------------

        System.out.println("eagerBuildFilteredMappedParallelNonEmpty.findFirst().orElse(\"?\")="
                + eagerBuildFilteredMappedParallelNonEmpty.findFirst().orElse("?"));


        printLineOfPlus();  // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


        // lazy build of empty
        Stream<String> lazyBuildNonEmpty = lazyBuildGenericStream(list.stream());
        printStatistics("lazyBuildNonEmpty:", lazyBuildNonEmpty);

        printLineOfMinus(); // ------------------------------------------------------------

        Stream<String> lazyBuildFilteredNonEmpty = lazyBuildNonEmpty.filter(s -> !s.isEmpty());
        printStatistics("lazyBuildFilteredNonEmpty:", lazyBuildFilteredNonEmpty);

        printLineOfMinus(); // ------------------------------------------------------------

        Stream<String> lazyBuildFilteredMappedNonEmpty = lazyBuildFilteredNonEmpty.map(String::toUpperCase);
        printStatistics("lazyBuildFilteredMappedNonEmpty:", lazyBuildFilteredMappedNonEmpty);

        printLineOfMinus(); // ------------------------------------------------------------

        Stream<String> lazyBuildFilteredMappedParallelNonEmpty = lazyBuildFilteredMappedNonEmpty.parallel();
        printStatistics("lazyBuildFilteredMappedParallelNonEmpty:", lazyBuildFilteredMappedParallelNonEmpty);

        printLineOfMinus(); // ------------------------------------------------------------

        System.out.println("lazyBuildFilteredMappedParallelNonEmpty.findFirst().orElse(\"?\")="
                + lazyBuildFilteredMappedParallelNonEmpty.findFirst().orElse("?"));
    }

// Output:
/*
############################################################
list=[]
############################################################
eagerBuildEmpty:
totalSize=128 bytes
totalCount=4 objects
------------------------------------------------------------
eagerBuildFilteredEmpty:
totalSize=208 bytes
totalCount=6 objects
------------------------------------------------------------
eagerBuildFilteredMappedEmpty:
totalSize=288 bytes
totalCount=8 objects
------------------------------------------------------------
eagerBuildFilteredMappedParallelEmpty:
totalSize=288 bytes
totalCount=8 objects
------------------------------------------------------------
eagerBuildFilteredMappedParallelEmpty.findFirst().orElse("?")=?
++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
lazyBuildEmpty:
totalSize=96 bytes
totalCount=4 objects
------------------------------------------------------------
lazyBuildFilteredEmpty:
totalSize=176 bytes
totalCount=8 objects
------------------------------------------------------------
lazyBuildFilteredMappedEmpty:
totalSize=232 bytes
totalCount=11 objects
------------------------------------------------------------
lazyBuildFilteredMappedParallelEmpty:
totalSize=272 bytes
totalCount=13 objects
------------------------------------------------------------
lazyBuildFilteredMappedParallelEmpty.findFirst().orElse("?")=?
############################################################
list=["", "Heinz", "Michael"]
############################################################
eagerBuildNonEmpty:
totalSize=304 bytes
totalCount=10 objects
------------------------------------------------------------
eagerBuildFilteredNonEmpty:
totalSize=384 bytes
totalCount=12 objects
------------------------------------------------------------
eagerBuildFilteredMappedNonEmpty:
totalSize=464 bytes
totalCount=14 objects
------------------------------------------------------------
eagerBuildFilteredMappedParallelNonEmpty:
totalSize=464 bytes
totalCount=14 objects
------------------------------------------------------------
eagerBuildFilteredMappedParallelNonEmpty.findFirst().orElse("?")=HEINZ
++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
lazyBuildNonEmpty:
totalSize=272 bytes
totalCount=10 objects
------------------------------------------------------------
lazyBuildFilteredNonEmpty:
totalSize=352 bytes
totalCount=14 objects
------------------------------------------------------------
lazyBuildFilteredMappedNonEmpty:
totalSize=408 bytes
totalCount=17 objects
------------------------------------------------------------
lazyBuildFilteredMappedParallelNonEmpty:
totalSize=448 bytes
totalCount=19 objects
------------------------------------------------------------
lazyBuildFilteredMappedParallelNonEmpty.findFirst().orElse("?")=HEINZ
*/
}
