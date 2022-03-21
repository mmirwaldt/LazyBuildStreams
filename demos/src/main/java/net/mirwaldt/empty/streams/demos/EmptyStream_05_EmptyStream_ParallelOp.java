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

import java.util.stream.Stream;

import static net.mirwaldt.empty.streams.demos.PrintUtil.*;

/**
 * Use with VM option -Djol.magicFieldOffset=true
 */
public class EmptyStream_05_EmptyStream_ParallelOp {
    public static void main(String[] args) {
        // eager build
        Stream<String> eagerBuildEmpty = Stream.empty();
        printStatistics("eagerBuildEmpty:", eagerBuildEmpty);

        printLineOfMinus();

        Stream<String> eagerBuildFilteredEmpty = eagerBuildEmpty.filter(s -> !s.isEmpty());
        printStatistics("eagerBuildFilteredEmpty:", eagerBuildFilteredEmpty);

        printLineOfMinus();

        Stream<String> eagerBuildFilteredMappedEmpty = eagerBuildFilteredEmpty.map(String::toUpperCase);
        printStatistics("eagerBuildFilteredMappedEmpty:", eagerBuildFilteredMappedEmpty);

        printLineOfMinus();

        Stream<String> eagerBuildFilteredMappedParallelEmpty = eagerBuildFilteredMappedEmpty.parallel();
        printStatistics("eagerBuildFilteredMappedParallelEmpty:", eagerBuildFilteredMappedParallelEmpty);

        printLineOfMinus();

        System.out.println("eagerBuildFilteredMappedParallelEmpty.findFirst().orElse(\"?\")="
                + eagerBuildFilteredMappedParallelEmpty.findFirst().orElse("?"));


        printLineOfPlus();


        // lazy build
        Stream<String> lazyBuildEmpty = LazyBuildStreams.ofStream(Stream.empty());
        printStatistics("lazyBuildEmpty:", lazyBuildEmpty);

        printLineOfMinus();

        Stream<String> lazyBuildFilteredEmpty = lazyBuildEmpty.filter(s -> !s.isEmpty());
        printStatistics("lazyBuildFilteredEmpty:", lazyBuildFilteredEmpty);

        printLineOfMinus();

        Stream<String> lazyBuildFilteredMappedEmpty = lazyBuildFilteredEmpty.map(String::toUpperCase);
        printStatistics("lazyBuildFilteredMappedEmpty:", lazyBuildFilteredMappedEmpty);

        printLineOfMinus();

        Stream<String> lazyBuildFilteredMappedParallelEmpty = lazyBuildFilteredMappedEmpty.parallel();
        printStatistics("lazyBuildFilteredMappedParallelEmpty:", lazyBuildFilteredMappedParallelEmpty);

        printLineOfMinus();

        System.out.println("lazyBuildFilteredMappedParallelEmpty.findFirst().orElse(\"?\")="
                + lazyBuildFilteredMappedParallelEmpty.findFirst().orElse("?"));
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
eagerBuildFilteredMappedParallelEmpty.findFirst().orElse("?")=?
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
lazyBuildFilteredMappedParallelEmpty.findFirst().orElse("?")=?
*/
}
