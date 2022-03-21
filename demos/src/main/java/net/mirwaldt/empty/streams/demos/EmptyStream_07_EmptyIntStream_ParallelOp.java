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

import net.mirwaldt.empty.streams.LazyBuildStreams;

import java.util.stream.IntStream;

import static net.mirwaldt.empty.streams.demos.PrintUtil.*;

/**
 * Use with VM option -Djol.magicFieldOffset=true
 */
public class EmptyStream_07_EmptyIntStream_ParallelOp {
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

        IntStream eagerBuildFilteredMappedParallelEmpty = eagerBuildFilteredMappedEmpty.parallel();
        printStatistics("eagerBuildFilteredMappedParallelEmpty:", eagerBuildFilteredMappedParallelEmpty);

        printLineOfMinus();

        System.out.println("eagerBuildFilteredMappedParallelEmpty.findFirst().orElse(-1)="
                + eagerBuildFilteredMappedParallelEmpty.findFirst().orElse(-1));


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

        IntStream lazyBuildFilteredMappedParallelEmpty = lazyBuildFilteredMappedEmpty.parallel();
        printStatistics("lazyBuildFilteredMappedParallelEmpty:", lazyBuildFilteredMappedParallelEmpty);

        printLineOfMinus();

        System.out.println("lazyBuildFilteredMappedParallelEmpty.findFirst().orElse(-1)="
                + lazyBuildFilteredMappedParallelEmpty.findFirst().orElse(-1));
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
eagerBuildFilteredMappedParallelEmpty:
totalSize=232 bytes
totalCount=6 objects
------------------------------------------------------------
eagerBuildFilteredMappedParallelEmpty.findFirst().orElse(-1)=-1
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
lazyBuildFilteredMappedParallelEmpty:
totalSize=24 bytes
totalCount=1 objects
------------------------------------------------------------
lazyBuildFilteredMappedParallelEmpty.findFirst().orElse(-1)=-1
*/
}
