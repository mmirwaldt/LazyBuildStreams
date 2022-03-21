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
import java.util.stream.Stream;

import static net.mirwaldt.empty.streams.demos.PrintUtil.*;

/**
 * Use with VM option -Djol.magicFieldOffset=true
 */
public class EmptyStream_02_StreamFromList {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        printLineOfSharp(); // ############################################################

        // empty builds
        System.out.println("list=" + list);

        printLineOfSharp(); // ############################################################

        // eager build of empty
        Stream<String> eagerBuildEmpty = list.stream();
        printStatistics("eagerBuildEmpty:", eagerBuildEmpty, list);

        printLineOfMinus(); // ------------------------------------------------------------

        Stream<String> eagerBuildFilteredEmpty = eagerBuildEmpty.filter(s -> !s.isEmpty());
        printStatistics("eagerBuildFilteredEmpty:", eagerBuildFilteredEmpty, list);

        printLineOfMinus(); // ------------------------------------------------------------

        Stream<String> eagerBuildFilteredMappedEmpty = eagerBuildFilteredEmpty.map(String::toUpperCase);
        printStatistics("eagerBuildFilteredMappedEmpty:", eagerBuildFilteredMappedEmpty, list);

        printLineOfMinus(); // ------------------------------------------------------------

        System.out.println("eagerBuildFilteredMappedEmpty.findFirst().orElse(\"?\")="
                + eagerBuildFilteredMappedEmpty.findFirst().orElse("?"));


        printLineOfPlus();  // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


        // lazy build of empty
        Stream<String> lazyBuildEmpty = LazyBuildStreams.ofStream(list.stream());
        printStatistics("lazyBuildEmpty:", lazyBuildEmpty, list);

        printLineOfMinus(); // ------------------------------------------------------------

        Stream<String> lazyBuildFilteredEmpty = lazyBuildEmpty.filter(s -> !s.isEmpty());
        printStatistics("lazyBuildFilteredEmpty:", lazyBuildFilteredEmpty, list);

        printLineOfMinus(); // ------------------------------------------------------------

        Stream<String> lazyBuildFilteredMappedEmpty = lazyBuildFilteredEmpty.map(String::toUpperCase);
        printStatistics("lazyBuildFilteredMappedEmpty:", lazyBuildFilteredMappedEmpty, list);

        printLineOfMinus(); // ------------------------------------------------------------

        System.out.println("lazyBuildFilteredMappedEmpty.findFirst().orElse(\"?\")="
                + lazyBuildFilteredMappedEmpty.findFirst().orElse("?"));


        printLineOfSharp(); // ############################################################

        // non-empty builds
        list.add("");
        list.add("Venkat");
        list.add("Michael");
        System.out.println("list=" + list.stream().map(s -> "\"" + s + "\"").toList());

        printLineOfSharp(); // ############################################################

        // eager build of empty
        Stream<String> eagerBuildNonEmpty = list.stream();
        printStatistics("eagerBuildNonEmpty:", eagerBuildNonEmpty, list);

        printLineOfMinus(); // ------------------------------------------------------------

        Stream<String> eagerBuildFilteredNonEmpty = eagerBuildNonEmpty.filter(s -> !s.isEmpty());
        printStatistics("eagerBuildFilteredNonEmpty:", eagerBuildFilteredNonEmpty, list);

        printLineOfMinus(); // ------------------------------------------------------------

        Stream<String> eagerBuildFilteredMappedNonEmpty = eagerBuildFilteredNonEmpty.map(String::toUpperCase);
        printStatistics("eagerBuildFilteredMappedNonEmpty:", eagerBuildFilteredMappedNonEmpty, list);

        printLineOfMinus(); // ------------------------------------------------------------

        System.out.println("eagerBuildFilteredMappedNonEmpty.findFirst().orElse(\"?\")="
                + eagerBuildFilteredMappedNonEmpty.findFirst().orElse("?"));


        printLineOfPlus();  // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


        // lazy build of empty
        Stream<String> lazyBuildNonEmpty = LazyBuildStreams.ofStream(list.stream());
        printStatistics("lazyBuildNonEmpty:", lazyBuildNonEmpty, list);

        printLineOfMinus(); // ------------------------------------------------------------

        Stream<String> lazyBuildFilteredNonEmpty = lazyBuildNonEmpty.filter(s -> !s.isEmpty());
        printStatistics("lazyBuildFilteredNonEmpty:", lazyBuildFilteredNonEmpty, list);

        printLineOfMinus(); // ------------------------------------------------------------

        Stream<String> lazyBuildFilteredMappedNonEmpty = lazyBuildFilteredNonEmpty.map(String::toUpperCase);
        printStatistics("lazyBuildFilteredMappedNonEmpty:", lazyBuildFilteredMappedNonEmpty, list);

        printLineOfMinus(); // ------------------------------------------------------------

        System.out.println("lazyBuildFilteredMappedNonEmpty.findFirst().orElse(\"?\")="
                + lazyBuildFilteredMappedNonEmpty.findFirst().orElse("?"));
    }

// Output:
/*
############################################################
list=[]
############################################################
eagerBuildEmpty:
totalSize=88 bytes
totalCount=2 objects
------------------------------------------------------------
eagerBuildFilteredEmpty:
totalSize=168 bytes
totalCount=4 objects
------------------------------------------------------------
eagerBuildFilteredMappedEmpty:
totalSize=248 bytes
totalCount=6 objects
------------------------------------------------------------
eagerBuildFilteredMappedEmpty.findFirst().orElse("?")=?
++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
lazyBuildEmpty:
totalSize=56 bytes
totalCount=2 objects
------------------------------------------------------------
lazyBuildFilteredEmpty:
totalSize=112 bytes
totalCount=5 objects
------------------------------------------------------------
lazyBuildFilteredMappedEmpty:
totalSize=144 bytes
totalCount=7 objects
------------------------------------------------------------
lazyBuildFilteredMappedEmpty.findFirst().orElse("?")=?
############################################################
list=["", "Venkat", "Michael"]
############################################################
eagerBuildNonEmpty:
totalSize=88 bytes
totalCount=2 objects
------------------------------------------------------------
eagerBuildFilteredNonEmpty:
totalSize=168 bytes
totalCount=4 objects
------------------------------------------------------------
eagerBuildFilteredMappedNonEmpty:
totalSize=248 bytes
totalCount=6 objects
------------------------------------------------------------
eagerBuildFilteredMappedNonEmpty.findFirst().orElse("?")=VENKAT
++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
lazyBuildNonEmpty:
totalSize=56 bytes
totalCount=2 objects
------------------------------------------------------------
lazyBuildFilteredNonEmpty:
totalSize=112 bytes
totalCount=5 objects
------------------------------------------------------------
lazyBuildFilteredMappedNonEmpty:
totalSize=144 bytes
totalCount=7 objects
------------------------------------------------------------
lazyBuildFilteredMappedNonEmpty.findFirst().orElse("?")=VENKAT
*/
}
