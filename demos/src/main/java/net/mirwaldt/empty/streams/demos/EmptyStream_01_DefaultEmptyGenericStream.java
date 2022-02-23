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

// Output:
/*
eagerBuildEmpty:
totalSize=72 bytes
totalCount=2 objects
java.util.stream.ReferencePipeline$Head@5fd0d5aed object externals:
          ADDRESS       SIZE TYPE                                          PATH                           VALUE
        713c22470         56 java.util.stream.ReferencePipeline$Head                                      (object)
        713c224a8    2340680 (something else)                              (somewhere else)               (something else)
        713e5dbf0         16 java.util.Spliterators$EmptySpliterator$OfRef .sourceSpliterator             (object)

Addresses are stable after 1 tries.


------------------------------------------------------------
eagerBuildFilteredEmpty:
totalSize=152 bytes
totalCount=4 objects
java.util.stream.ReferencePipeline$2@eafc191d object externals:
          ADDRESS       SIZE TYPE                                                                                                    PATH                           VALUE
        71368e730         16 net.mirwaldt.empty.streams.demos.EmptyStream_01_DefaultEmptyGenericStream$$Lambda$89/0x0000000800cc6620 .val$predicate                 (object)
        71368e740        352 (something else)                                                                                        (somewhere else)               (something else)
        71368e8a0         64 java.util.stream.ReferencePipeline$2                                                                                                   (object)
        71368e8e0    5847952 (something else)                                                                                        (somewhere else)               (something else)
        713c22470         56 java.util.stream.ReferencePipeline$Head                                                                 .this$0                        (object)
        713c224a8    2340680 (something else)                                                                                        (somewhere else)               (something else)
        713e5dbf0         16 java.util.Spliterators$EmptySpliterator$OfRef                                                           .this$0.sourceSpliterator      (object)

Addresses are stable after 1 tries.


------------------------------------------------------------
eagerBuildFilteredMappedEmpty:
totalSize=232 bytes
totalCount=6 objects
java.util.stream.ReferencePipeline$3@1b26f7b2d object externals:
          ADDRESS       SIZE TYPE                                                                                                    PATH                           VALUE
        71368e730         16 net.mirwaldt.empty.streams.demos.EmptyStream_01_DefaultEmptyGenericStream$$Lambda$89/0x0000000800cc6620 .this$0.val$predicate          (object)
        71368e740        352 (something else)                                                                                        (somewhere else)               (something else)
        71368e8a0         64 java.util.stream.ReferencePipeline$2                                                                    .this$0                        (object)
        71368e8e0     187112 (something else)                                                                                        (somewhere else)               (something else)
        7136bc3c8         16 net.mirwaldt.empty.streams.demos.EmptyStream_01_DefaultEmptyGenericStream$$Lambda$90/0x0000000800cc6868 .val$mapper                    (object)
        7136bc3d8        352 (something else)                                                                                        (somewhere else)               (something else)
        7136bc538         64 java.util.stream.ReferencePipeline$3                                                                                                   (object)
        7136bc578    5660408 (something else)                                                                                        (somewhere else)               (something else)
        713c22470         56 java.util.stream.ReferencePipeline$Head                                                                 .sourceStage                   (object)
        713c224a8    2340680 (something else)                                                                                        (somewhere else)               (something else)
        713e5dbf0         16 java.util.Spliterators$EmptySpliterator$OfRef                                                           .sourceStage.sourceSpliterator (object)

Addresses are stable after 1 tries.


------------------------------------------------------------
eagerBuildFilteredMappedEmpty.findFirst().orElse("?")=?
++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
lazyBuildEmpty:
totalSize=56 bytes
totalCount=3 objects
net.mirwaldt.empty.streams.LazyBuildGenericStream@8646db9d object externals:
          ADDRESS       SIZE TYPE                                                         PATH                           VALUE
        71370e358         24 net.mirwaldt.empty.streams.LazyBuildGenericStream                                           (object)
        71370e370      22624 (something else)                                             (somewhere else)               (something else)
        713713bd0         16 net.mirwaldt.empty.streams.util.LazyBuildGenericStreamUtil$1 .streamSupplier                (object)
        713713be0    7643152 (something else)                                             (somewhere else)               (something else)
        713e5dbf0         16 java.util.Spliterators$EmptySpliterator$OfRef                .spliterator                   (object)

Addresses are stable after 1 tries.


------------------------------------------------------------
lazyBuildFilteredEmpty:
totalSize=56 bytes
totalCount=3 objects
net.mirwaldt.empty.streams.LazyBuildGenericStream@b7dd107d object externals:
          ADDRESS       SIZE TYPE                                                         PATH                           VALUE
        713713bd0         16 net.mirwaldt.empty.streams.util.LazyBuildGenericStreamUtil$1 .streamSupplier                (object)
        713713be0      49136 (something else)                                             (somewhere else)               (something else)
        71371fbd0         24 net.mirwaldt.empty.streams.LazyBuildGenericStream                                           (object)
        71371fbe8    7593992 (something else)                                             (somewhere else)               (something else)
        713e5dbf0         16 java.util.Spliterators$EmptySpliterator$OfRef                .spliterator                   (object)

Addresses are stable after 1 tries.


------------------------------------------------------------
lazyBuildFilteredMappedEmpty:
totalSize=56 bytes
totalCount=3 objects
net.mirwaldt.empty.streams.LazyBuildGenericStream@7cbd213ed object externals:
          ADDRESS       SIZE TYPE                                                         PATH                           VALUE
        713713bd0         16 net.mirwaldt.empty.streams.util.LazyBuildGenericStreamUtil$1 .streamSupplier                (object)
        713713be0      94392 (something else)                                             (somewhere else)               (something else)
        71372ac98         24 net.mirwaldt.empty.streams.LazyBuildGenericStream                                           (object)
        71372acb0    7548736 (something else)                                             (somewhere else)               (something else)
        713e5dbf0         16 java.util.Spliterators$EmptySpliterator$OfRef                .spliterator                   (object)

Addresses are stable after 1 tries.


------------------------------------------------------------
lazyBuildFilteredMappedEmpty.findFirst().orElse("?")=?
 */
}
