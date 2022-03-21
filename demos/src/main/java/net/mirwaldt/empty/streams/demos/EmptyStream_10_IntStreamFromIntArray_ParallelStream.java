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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static net.mirwaldt.empty.streams.demos.PrintUtil.*;

/**
 * Use with VM option -Djol.magicFieldOffset=true
 */
public class EmptyStream_10_IntStreamFromIntArray_ParallelStream {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();

        printLineOfSharp(); // ############################################################

        // empty builds
        System.out.println("list=" + list);

        printLineOfSharp(); // ############################################################

        // eager build of empty
        IntStream eagerBuildEmpty = list.parallelStream()
                .mapToInt(Integer::valueOf);
        printStatistics("eagerBuildEmpty:", eagerBuildEmpty, list);

        printLineOfMinus(); // ------------------------------------------------------------

        IntStream eagerBuildFilteredEmpty = eagerBuildEmpty.filter(i -> 0 < i);
        printStatistics("eagerBuildFilteredEmpty:", eagerBuildFilteredEmpty, list);

        printLineOfMinus(); // ------------------------------------------------------------

        IntStream eagerBuildFilteredMappedEmpty = eagerBuildFilteredEmpty.map(i -> 2 * i);
        printStatistics("eagerBuildFilteredMappedEmpty:", eagerBuildFilteredMappedEmpty, list);

        printLineOfMinus(); // ------------------------------------------------------------

        System.out.println("eagerBuildFilteredMappedEmpty.findFirst().orElse(-1)="
                + eagerBuildFilteredMappedEmpty.findFirst().orElse(-1));


        printLineOfPlus();  // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


        // lazy build of empty
        IntStream lazyBuildEmpty = LazyBuildStreams.ofIntStream(LazyBuildStreams.ofStream(list.parallelStream())
                .mapToInt(Integer::valueOf));
        printStatistics("lazyBuildEmpty:", lazyBuildEmpty, list);

        printLineOfMinus(); // ------------------------------------------------------------

        IntStream lazyBuildFilteredEmpty = lazyBuildEmpty.filter(i -> 0 < i);
        printStatistics("lazyBuildFilteredEmpty:", lazyBuildFilteredEmpty, list);

        printLineOfMinus(); // ------------------------------------------------------------

        IntStream lazyBuildFilteredMappedEmpty = lazyBuildFilteredEmpty.map(i -> 2 * i);
        printStatistics("lazyBuildFilteredMappedEmpty:", lazyBuildFilteredMappedEmpty, list);

        printLineOfMinus(); // ------------------------------------------------------------

        System.out.println("lazyBuildFilteredMappedEmpty.findFirst().orElse(-1)="
                + lazyBuildFilteredMappedEmpty.findFirst().orElse(-1));


        printLineOfSharp(); // ############################################################

        // non-empty builds
        list.add(-3);
        list.add(2);
        list.add(5);
        System.out.println("list=" + list.stream().map(s -> "\"" + s + "\"").toList());

        printLineOfSharp(); // ############################################################

        // eager build of empty
        IntStream eagerBuildNonEmpty = list.parallelStream()
                .mapToInt(Integer::valueOf);
        printStatistics("eagerBuildNonEmpty:", eagerBuildNonEmpty, list);

        printLineOfMinus(); // ------------------------------------------------------------

        IntStream eagerBuildFilteredNonEmpty = eagerBuildNonEmpty.filter(i -> 0 < i);
        printStatistics("eagerBuildFilteredNonEmpty:", eagerBuildFilteredNonEmpty, list);

        printLineOfMinus(); // ------------------------------------------------------------

        IntStream eagerBuildFilteredMappedNonEmpty = eagerBuildFilteredNonEmpty.map(i -> 2 * i);
        printStatistics("eagerBuildFilteredMappedNonEmpty:", eagerBuildFilteredMappedNonEmpty, list);

        printLineOfMinus(); // ------------------------------------------------------------

        System.out.println("eagerBuildFilteredMappedNonEmpty.findFirst().orElse(\"?\")="
                + eagerBuildFilteredMappedNonEmpty.findFirst().orElse(-1));


        printLineOfPlus();  // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


        // lazy build of empty
        IntStream lazyBuildNonEmpty = LazyBuildStreams.ofIntStream(LazyBuildStreams.ofStream(list.parallelStream())
                .mapToInt(Integer::valueOf));
        printStatistics("lazyBuildNonEmpty:", lazyBuildNonEmpty, list);

        printLineOfMinus(); // ------------------------------------------------------------

        IntStream lazyBuildFilteredNonEmpty = lazyBuildNonEmpty.filter(i -> 0 < i);
        printStatistics("lazyBuildFilteredNonEmpty:", lazyBuildFilteredNonEmpty, list);

        printLineOfMinus(); // ------------------------------------------------------------

        IntStream lazyBuildFilteredMappedNonEmpty = lazyBuildFilteredNonEmpty.map(i -> 2 * i);
        printStatistics("lazyBuildFilteredMappedNonEmpty:", lazyBuildFilteredMappedNonEmpty, list);

        printLineOfMinus(); // ------------------------------------------------------------

        System.out.println("lazyBuildFilteredMappedNonEmpty.findFirst().orElse(-1)="
                + lazyBuildFilteredMappedNonEmpty.findFirst().orElse(-1));
    }
// Output:
/*
############################################################
list=[]
############################################################
eagerBuildEmpty:
totalSize=168 bytes
totalCount=4 objects
------------------------------------------------------------
eagerBuildFilteredEmpty:
totalSize=248 bytes
totalCount=6 objects
------------------------------------------------------------
eagerBuildFilteredMappedEmpty:
totalSize=328 bytes
totalCount=8 objects
------------------------------------------------------------
eagerBuildFilteredMappedEmpty.findFirst().orElse(-1)=-1
++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
lazyBuildEmpty:
totalSize=112 bytes
totalCount=5 objects
------------------------------------------------------------
lazyBuildFilteredEmpty:
totalSize=144 bytes
totalCount=7 objects
------------------------------------------------------------
lazyBuildFilteredMappedEmpty:
totalSize=184 bytes
totalCount=9 objects
------------------------------------------------------------
lazyBuildFilteredMappedEmpty.findFirst().orElse(-1)=-1
############################################################
list=["-3", "2", "5"]
############################################################
eagerBuildNonEmpty:
totalSize=168 bytes
totalCount=4 objects
------------------------------------------------------------
eagerBuildFilteredNonEmpty:
totalSize=248 bytes
totalCount=6 objects
------------------------------------------------------------
eagerBuildFilteredMappedNonEmpty:
totalSize=328 bytes
totalCount=8 objects
------------------------------------------------------------
eagerBuildFilteredMappedNonEmpty.findFirst().orElse("?")=4
++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
lazyBuildNonEmpty:
totalSize=112 bytes
totalCount=5 objects
------------------------------------------------------------
lazyBuildFilteredNonEmpty:
totalSize=144 bytes
totalCount=7 objects
------------------------------------------------------------
lazyBuildFilteredMappedNonEmpty:
totalSize=184 bytes
totalCount=9 objects
------------------------------------------------------------
lazyBuildFilteredMappedNonEmpty.findFirst().orElse(-1)=4
*/
}
