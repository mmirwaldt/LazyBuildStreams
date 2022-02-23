package net.mirwaldt.empty.streams.demos;

import java.util.stream.Stream;

import static net.mirwaldt.empty.streams.LazyBuildStreamFactory.lazyBuildGenericStream;
import static net.mirwaldt.empty.streams.demos.PrintUtil.*;

/**
 * Use with VM option -Djol.magicFieldOffset=true
 */
public class EmptyStream_01_DefaultEmptyGenericStream {
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

        System.out.println("eagerBuildFilteredMappedEmpty.findFirst().orElse(\"?\")="
                + eagerBuildFilteredMappedEmpty.findFirst().orElse("?"));


        printLineOfPlus();


        // lazy build
        Stream<String> lazyBuildEmpty = lazyBuildGenericStream(Stream.empty());
        printStatistics("lazyBuildEmpty:", lazyBuildEmpty);

        printLineOfMinus();

        Stream<String> lazyBuildFilteredEmpty = lazyBuildEmpty.filter(s -> !s.isEmpty());
        printStatistics("lazyBuildFilteredEmpty:", lazyBuildFilteredEmpty);

        printLineOfMinus();

        Stream<String> lazyBuildFilteredMappedEmpty = lazyBuildFilteredEmpty.map(String::toUpperCase);
        printStatistics("lazyBuildFilteredMappedEmpty:", lazyBuildFilteredMappedEmpty);

        printLineOfMinus();

        System.out.println("lazyBuildFilteredMappedEmpty.findFirst().orElse(\"?\")="
                + lazyBuildFilteredMappedEmpty.findFirst().orElse("?"));
    }
}
