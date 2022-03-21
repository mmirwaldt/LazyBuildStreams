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
