package net.mirwaldt.empty.streams.demos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static net.mirwaldt.empty.streams.LazyBuildStreamFactory.lazyBuildGenericStream;
import static net.mirwaldt.empty.streams.LazyBuildStreamFactory.lazyBuildIntStream;
import static net.mirwaldt.empty.streams.demos.PrintUtil.*;

/**
 * Use with VM option -Djol.magicFieldOffset=true
 */
public class EmptyStream_08_IntStreamFromIntArray_ParallelOp {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();

        printLineOfSharp(); // ############################################################

        // empty builds
        System.out.println("list=" + list);

        printLineOfSharp(); // ############################################################

        // eager build of empty
        IntStream eagerBuildEmpty = list.stream().mapToInt(Integer::valueOf);
        printStatistics("eagerBuildEmpty:", eagerBuildEmpty);

        printLineOfMinus(); // ------------------------------------------------------------

        IntStream eagerBuildFilteredEmpty = eagerBuildEmpty.filter(i -> 0 < i);
        printStatistics("eagerBuildFilteredEmpty:", eagerBuildFilteredEmpty);

        printLineOfMinus(); // ------------------------------------------------------------

        IntStream eagerBuildFilteredMappedEmpty = eagerBuildFilteredEmpty.map(i -> 2 * i);
        printStatistics("eagerBuildFilteredMappedEmpty:", eagerBuildFilteredMappedEmpty);

        printLineOfMinus(); // ------------------------------------------------------------

        IntStream eagerBuildFilteredMappedParallelEmpty = eagerBuildFilteredMappedEmpty.parallel();
        printStatistics("eagerBuildFilteredMappedParallelEmpty:", eagerBuildFilteredMappedParallelEmpty);

        printLineOfMinus(); // ------------------------------------------------------------

        System.out.println("eagerBuildFilteredMappedParallelEmpty.findFirst().orElse(-1)="
                + eagerBuildFilteredMappedParallelEmpty.findFirst().orElse(-1));


        printLineOfPlus();  // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


        // lazy build of empty
        IntStream lazyBuildEmpty = lazyBuildIntStream(lazyBuildGenericStream(list.stream()).mapToInt(Integer::valueOf));
        printStatistics("lazyBuildEmpty:", lazyBuildEmpty);

        printLineOfMinus(); // ------------------------------------------------------------

        IntStream lazyBuildFilteredEmpty = lazyBuildEmpty.filter(i -> 0 < i);
        printStatistics("lazyBuildFilteredEmpty:", lazyBuildFilteredEmpty);

        printLineOfMinus(); // ------------------------------------------------------------

        IntStream lazyBuildFilteredMappedEmpty = lazyBuildFilteredEmpty.map(i -> 2 * i);
        printStatistics("lazyBuildFilteredMappedEmpty:", lazyBuildFilteredMappedEmpty);

        printLineOfMinus(); // ------------------------------------------------------------

        IntStream lazyBuildFilteredMappedParallelEmpty = lazyBuildFilteredMappedEmpty.parallel();
        printStatistics("lazyBuildFilteredMappedParallelEmpty:", lazyBuildFilteredMappedParallelEmpty);

        printLineOfMinus(); // ------------------------------------------------------------

        System.out.println("lazyBuildFilteredMappedParallelEmpty.findFirst().orElse(-1)="
                + lazyBuildFilteredMappedParallelEmpty.findFirst().orElse(-1));



        printLineOfSharp(); // ############################################################

        // non-empty builds
        list.add(-3);
        list.add(2);
        list.add(5);
        System.out.println("list=" + list.stream().map(s -> "\"" + s +"\"").toList());

        printLineOfSharp(); // ############################################################

        // eager build of empty
        IntStream eagerBuildNonEmpty = list.stream().mapToInt(Integer::valueOf);
        printStatistics("eagerBuildNonEmpty:", eagerBuildNonEmpty);

        printLineOfMinus(); // ------------------------------------------------------------

        IntStream eagerBuildFilteredNonEmpty = eagerBuildNonEmpty.filter(i -> 0 < i);
        printStatistics("eagerBuildFilteredNonEmpty:", eagerBuildFilteredNonEmpty);

        printLineOfMinus(); // ------------------------------------------------------------

        IntStream eagerBuildFilteredMappedNonEmpty = eagerBuildFilteredNonEmpty.map(i -> 2 * i);
        printStatistics("eagerBuildFilteredMappedNonEmpty:", eagerBuildFilteredMappedNonEmpty);

        printLineOfMinus(); // ------------------------------------------------------------

        IntStream eagerBuildFilteredMappedParallelNonEmpty = eagerBuildFilteredMappedNonEmpty.parallel();
        printStatistics("eagerBuildFilteredMappedParallelNonEmpty:", eagerBuildFilteredMappedParallelNonEmpty);

        printLineOfMinus(); // ------------------------------------------------------------

        System.out.println("eagerBuildFilteredMappedParallelNonEmpty.findFirst().orElse(\"?\")="
                + eagerBuildFilteredMappedParallelNonEmpty.findFirst().orElse(-1));


        printLineOfPlus();  // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


        // lazy build of empty
        IntStream lazyBuildNonEmpty = lazyBuildIntStream(lazyBuildGenericStream(list.stream()).mapToInt(Integer::valueOf));
        printStatistics("lazyBuildNonEmpty:", lazyBuildNonEmpty);

        printLineOfMinus(); // ------------------------------------------------------------

        IntStream lazyBuildFilteredNonEmpty = lazyBuildNonEmpty.filter(i -> 0 < i);
        printStatistics("lazyBuildFilteredNonEmpty:", lazyBuildFilteredNonEmpty);

        printLineOfMinus(); // ------------------------------------------------------------

        IntStream lazyBuildFilteredMappedNonEmpty = lazyBuildFilteredNonEmpty.map(i -> 2 * i);
        printStatistics("lazyBuildFilteredMappedNonEmpty:", lazyBuildFilteredMappedNonEmpty);

        printLineOfMinus(); // ------------------------------------------------------------

        IntStream lazyBuildFilteredMappedParallelNonEmpty = lazyBuildFilteredMappedNonEmpty.parallel();
        printStatistics("lazyBuildFilteredMappedParallelNonEmpty:", lazyBuildFilteredMappedParallelNonEmpty);

        printLineOfMinus(); // ------------------------------------------------------------

        System.out.println("lazyBuildFilteredMappedParallelNonEmpty.findFirst().orElse(-1)="
                + lazyBuildFilteredMappedParallelNonEmpty.findFirst().orElse(-1));
    }
// Output:
/*
############################################################
list=[]
############################################################
eagerBuildEmpty:
totalSize=208 bytes
totalCount=6 objects
------------------------------------------------------------
eagerBuildFilteredEmpty:
totalSize=288 bytes
totalCount=8 objects
------------------------------------------------------------
eagerBuildFilteredMappedEmpty:
totalSize=368 bytes
totalCount=10 objects
------------------------------------------------------------
eagerBuildFilteredMappedParallelEmpty:
totalSize=368 bytes
totalCount=10 objects
------------------------------------------------------------
eagerBuildFilteredMappedParallelEmpty.findFirst().orElse(-1)=-1
++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
lazyBuildEmpty:
totalSize=40 bytes
totalCount=2 objects
------------------------------------------------------------
lazyBuildFilteredEmpty:
totalSize=40 bytes
totalCount=2 objects
------------------------------------------------------------
lazyBuildFilteredMappedEmpty:
totalSize=40 bytes
totalCount=2 objects
------------------------------------------------------------
lazyBuildFilteredMappedParallelEmpty:
totalSize=40 bytes
totalCount=2 objects
------------------------------------------------------------
lazyBuildFilteredMappedParallelEmpty.findFirst().orElse(-1)=-1
############################################################
list=["-3", "2", "5"]
############################################################
eagerBuildNonEmpty:
totalSize=296 bytes
totalCount=9 objects
------------------------------------------------------------
eagerBuildFilteredNonEmpty:
totalSize=376 bytes
totalCount=11 objects
------------------------------------------------------------
eagerBuildFilteredMappedNonEmpty:
totalSize=456 bytes
totalCount=13 objects
------------------------------------------------------------
eagerBuildFilteredMappedParallelNonEmpty:
totalSize=456 bytes
totalCount=13 objects
------------------------------------------------------------
eagerBuildFilteredMappedParallelNonEmpty.findFirst().orElse("?")=4
++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
lazyBuildNonEmpty:
totalSize=384 bytes
totalCount=12 objects
------------------------------------------------------------
lazyBuildFilteredNonEmpty:
totalSize=464 bytes
totalCount=16 objects
------------------------------------------------------------
lazyBuildFilteredMappedNonEmpty:
totalSize=520 bytes
totalCount=19 objects
------------------------------------------------------------
lazyBuildFilteredMappedParallelNonEmpty:
totalSize=560 bytes
totalCount=21 objects
------------------------------------------------------------
lazyBuildFilteredMappedParallelNonEmpty.findFirst().orElse(-1)=4
 */
}
