package net.mirwaldt.empty.streams.demos;

import net.mirwaldt.empty.streams.LazyBuildStreams;

import java.util.stream.IntStream;

import static net.mirwaldt.empty.streams.demos.PrintUtil.*;

/**
 * Use with VM option -Djol.magicFieldOffset=true
 */
public class EmptyStream_03_DefaultEmptyIntStream {
    public static void main(String[] args) {
        // eager build
        IntStream eagerBuildEmpty = IntStream.empty();
        printStatistics("eagerBuildEmpty:", eagerBuildEmpty);
        
        printLineOfMinus();
        
        IntStream eagerBuildFilteredEmpty = eagerBuildEmpty.filter(i -> 0 < i);
        printStatistics("eagerBuildFilteredEmpty:", eagerBuildFilteredEmpty);

        printLineOfMinus();

        IntStream eagerBuildFilteredMappedEmpty = eagerBuildFilteredEmpty.map(i -> 2 * i);
        printStatistics("eagerBuildFilteredMappedEmpty:", eagerBuildFilteredMappedEmpty);

        printLineOfMinus();

        System.out.println("eagerBuildFilteredMappedEmpty.findFirst().orElse(-1)="
                + eagerBuildFilteredMappedEmpty.findFirst().orElse(-1));

        
        printLineOfPlus();

        
        // lazy build
        IntStream lazyBuildEmpty = LazyBuildStreams.ofIntStream(IntStream.empty());
        printStatistics("lazyBuildEmpty:", lazyBuildEmpty);

        printLineOfMinus();

        IntStream lazyBuildFilteredEmpty = lazyBuildEmpty.filter(i -> 0 < i);
        printStatistics("lazyBuildFilteredEmpty:", lazyBuildFilteredEmpty);

        printLineOfMinus();

        IntStream lazyBuildFilteredMappedEmpty = lazyBuildFilteredEmpty.map(i -> 2 * i);
        printStatistics("lazyBuildFilteredMappedEmpty:", lazyBuildFilteredMappedEmpty);

        printLineOfMinus();

        System.out.println("lazyBuildFilteredMappedEmpty.findFirst().orElse(-1)="
                + lazyBuildFilteredMappedEmpty.findFirst().orElse(-1));
    }

// Output:
/*
eagerBuildEmpty:
totalSize=72 bytes
totalCount=2 objects
------------------------------------------------------------
eagerBuildFilteredEmpty:
totalSize=152 bytes
totalCount=4 objects
------------------------------------------------------------
eagerBuildFilteredMappedEmpty:
totalSize=232 bytes
totalCount=6 objects
------------------------------------------------------------
eagerBuildFilteredMappedEmpty.findFirst().orElse(-1)=-1
++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
lazyBuildEmpty:
totalSize=24 bytes
totalCount=1 objects
------------------------------------------------------------
lazyBuildFilteredEmpty:
totalSize=24 bytes
totalCount=1 objects
------------------------------------------------------------
lazyBuildFilteredMappedEmpty:
totalSize=24 bytes
totalCount=1 objects
------------------------------------------------------------
lazyBuildFilteredMappedEmpty.findFirst().orElse(-1)=-1
*/
}
