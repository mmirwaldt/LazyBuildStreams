package net.mirwaldt.empty.streams.demos;

import java.util.stream.Stream;

import static net.mirwaldt.empty.streams.LazyBuildStreamFactory.lazyBuildGenericStream;
import static net.mirwaldt.empty.streams.demos.PrintUtil.*;

/**
 * Use with VM option -Djol.magicFieldOffset=true
 */
public class EmptyStream_05_DefaultEmptyGenericStream_ParallelOp {
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
        Stream<String> lazyBuildEmpty = lazyBuildGenericStream(Stream.empty());
        printStatistics("lazyBuildEmpty:", lazyBuildEmpty);

        printLineOfMinus();

        Stream<String> lazyBuildFilteredEmpty = lazyBuildEmpty.filter(s -> !s.isEmpty());
        printStatistics("lazyBuildFilteredEmpty:", lazyBuildFilteredEmpty);

        printLineOfMinus();

        Stream<String> lazyBuildFilteredMappedEmpty = lazyBuildFilteredEmpty.map(String::toUpperCase);
        printStatistics("lazyBuildFilteredMappedEmpty:", lazyBuildFilteredMappedEmpty);

        printLineOfMinus();

        Stream<String> lazyBuildFilteredMappedParallelEmpty = lazyBuildFilteredMappedEmpty.parallel();
        printStatistics("lazyBuildFilteredMappedEmpty:", lazyBuildFilteredMappedParallelEmpty);

        printLineOfMinus();

        System.out.println("lazyBuildFilteredMappedParallelEmpty.findFirst().orElse(\"?\")="
                + lazyBuildFilteredMappedParallelEmpty.findFirst().orElse("?"));
    }

// Output:
/*
eagerBuildEmpty:
totalSize=72 bytes
totalCount=2 objects
java.util.stream.ReferencePipeline$Head@5fd0d5aed object externals:
          ADDRESS       SIZE TYPE                                          PATH                           VALUE
        713c225f8         56 java.util.stream.ReferencePipeline$Head                                      (object)
        713c22630    2340344 (something else)                              (somewhere else)               (something else)
        713e5dc28         16 java.util.Spliterators$EmptySpliterator$OfRef .sourceSpliterator             (object)

Addresses are stable after 1 tries.


------------------------------------------------------------
eagerBuildFilteredEmpty:
totalSize=152 bytes
totalCount=4 objects
java.util.stream.ReferencePipeline$2@eafc191d object externals:
          ADDRESS       SIZE TYPE                                                                                                             PATH                           VALUE
        71368eb90         16 net.mirwaldt.empty.streams.demos.EmptyStream_05_DefaultEmptyGenericStream_Parallel$$Lambda$89/0x0000000800cc6620 .val$predicate                 (object)
        71368eba0        352 (something else)                                                                                                 (somewhere else)               (something else)
        71368ed00         64 java.util.stream.ReferencePipeline$2                                                                                                            (object)
        71368ed40    5847224 (something else)                                                                                                 (somewhere else)               (something else)
        713c225f8         56 java.util.stream.ReferencePipeline$Head                                                                          .this$0                        (object)
        713c22630    2340344 (something else)                                                                                                 (somewhere else)               (something else)
        713e5dc28         16 java.util.Spliterators$EmptySpliterator$OfRef                                                                    .this$0.sourceSpliterator      (object)

Addresses are stable after 1 tries.


------------------------------------------------------------
eagerBuildFilteredMappedEmpty:
totalSize=232 bytes
totalCount=6 objects
java.util.stream.ReferencePipeline$3@1b26f7b2d object externals:
          ADDRESS       SIZE TYPE                                                                                                             PATH                           VALUE
        71368eb90         16 net.mirwaldt.empty.streams.demos.EmptyStream_05_DefaultEmptyGenericStream_Parallel$$Lambda$89/0x0000000800cc6620 .this$0.val$predicate          (object)
        71368eba0        352 (something else)                                                                                                 (somewhere else)               (something else)
        71368ed00         64 java.util.stream.ReferencePipeline$2                                                                             .this$0                        (object)
        71368ed40     187312 (something else)                                                                                                 (somewhere else)               (something else)
        7136bc8f0         16 net.mirwaldt.empty.streams.demos.EmptyStream_05_DefaultEmptyGenericStream_Parallel$$Lambda$90/0x0000000800cc6868 .val$mapper                    (object)
        7136bc900        352 (something else)                                                                                                 (somewhere else)               (something else)
        7136bca60         64 java.util.stream.ReferencePipeline$3                                                                                                            (object)
        7136bcaa0    5659480 (something else)                                                                                                 (somewhere else)               (something else)
        713c225f8         56 java.util.stream.ReferencePipeline$Head                                                                          .sourceStage                   (object)
        713c22630    2340344 (something else)                                                                                                 (somewhere else)               (something else)
        713e5dc28         16 java.util.Spliterators$EmptySpliterator$OfRef                                                                    .sourceStage.sourceSpliterator (object)

Addresses are stable after 1 tries.


------------------------------------------------------------
eagerBuildFilteredMappedParallelEmpty:
totalSize=232 bytes
totalCount=6 objects
java.util.stream.ReferencePipeline$3@1b26f7b2d object externals:
          ADDRESS       SIZE TYPE                                                                                                             PATH                           VALUE
        71368eb90         16 net.mirwaldt.empty.streams.demos.EmptyStream_05_DefaultEmptyGenericStream_Parallel$$Lambda$89/0x0000000800cc6620 .this$0.val$predicate          (object)
        71368eba0        352 (something else)                                                                                                 (somewhere else)               (something else)
        71368ed00         64 java.util.stream.ReferencePipeline$2                                                                             .this$0                        (object)
        71368ed40     187312 (something else)                                                                                                 (somewhere else)               (something else)
        7136bc8f0         16 net.mirwaldt.empty.streams.demos.EmptyStream_05_DefaultEmptyGenericStream_Parallel$$Lambda$90/0x0000000800cc6868 .val$mapper                    (object)
        7136bc900        352 (something else)                                                                                                 (somewhere else)               (something else)
        7136bca60         64 java.util.stream.ReferencePipeline$3                                                                                                            (object)
        7136bcaa0    5659480 (something else)                                                                                                 (somewhere else)               (something else)
        713c225f8         56 java.util.stream.ReferencePipeline$Head                                                                          .sourceStage                   (object)
        713c22630    2340344 (something else)                                                                                                 (somewhere else)               (something else)
        713e5dc28         16 java.util.Spliterators$EmptySpliterator$OfRef                                                                    .sourceStage.sourceSpliterator (object)

Addresses are stable after 1 tries.


------------------------------------------------------------
eagerBuildFilteredMappedParallelEmpty.findFirst().orElse("?")=?
++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
lazyBuildEmpty:
totalSize=56 bytes
totalCount=3 objects
net.mirwaldt.empty.streams.LazyBuildGenericStream@6950e31d object externals:
          ADDRESS       SIZE TYPE                                                         PATH                           VALUE
        7137460d8         24 net.mirwaldt.empty.streams.LazyBuildGenericStream                                           (object)
        7137460f0      22624 (something else)                                             (somewhere else)               (something else)
        71374b950         16 net.mirwaldt.empty.streams.util.LazyBuildGenericStreamUtil$1 .streamSupplier                (object)
        71374b960    7414472 (something else)                                             (somewhere else)               (something else)
        713e5dc28         16 java.util.Spliterators$EmptySpliterator$OfRef                .spliterator                   (object)

Addresses are stable after 1 tries.


------------------------------------------------------------
lazyBuildFilteredEmpty:
totalSize=56 bytes
totalCount=3 objects
net.mirwaldt.empty.streams.LazyBuildGenericStream@192d3247d object externals:
          ADDRESS       SIZE TYPE                                                         PATH                           VALUE
        71374b950         16 net.mirwaldt.empty.streams.util.LazyBuildGenericStreamUtil$1 .streamSupplier                (object)
        71374b960      49872 (something else)                                             (somewhere else)               (something else)
        713757c30         24 net.mirwaldt.empty.streams.LazyBuildGenericStream                                           (object)
        713757c48    7364576 (something else)                                             (somewhere else)               (something else)
        713e5dc28         16 java.util.Spliterators$EmptySpliterator$OfRef                .spliterator                   (object)

Addresses are stable after 1 tries.


------------------------------------------------------------
lazyBuildFilteredMappedEmpty:
totalSize=56 bytes
totalCount=3 objects
net.mirwaldt.empty.streams.LazyBuildGenericStream@43bd930ad object externals:
          ADDRESS       SIZE TYPE                                                         PATH                           VALUE
        71374b950         16 net.mirwaldt.empty.streams.util.LazyBuildGenericStreamUtil$1 .streamSupplier                (object)
        71374b960      95280 (something else)                                             (somewhere else)               (something else)
        713762d90         24 net.mirwaldt.empty.streams.LazyBuildGenericStream                                           (object)
        713762da8    7319168 (something else)                                             (somewhere else)               (something else)
        713e5dc28         16 java.util.Spliterators$EmptySpliterator$OfRef                .spliterator                   (object)

Addresses are stable after 1 tries.


------------------------------------------------------------
lazyBuildFilteredMappedEmpty:
totalSize=56 bytes
totalCount=3 objects
net.mirwaldt.empty.streams.LazyBuildGenericStream@7a30d1e6d object externals:
          ADDRESS       SIZE TYPE                                                         PATH                           VALUE
        71374b950         16 net.mirwaldt.empty.streams.util.LazyBuildGenericStreamUtil$1 .streamSupplier                (object)
        71374b960     144736 (something else)                                             (somewhere else)               (something else)
        71376eec0         24 net.mirwaldt.empty.streams.LazyBuildGenericStream                                           (object)
        71376eed8    7269712 (something else)                                             (somewhere else)               (something else)
        713e5dc28         16 java.util.Spliterators$EmptySpliterator$OfRef                .spliterator                   (object)

Addresses are stable after 1 tries.


------------------------------------------------------------
lazyBuildFilteredMappedParallelEmpty.findFirst().orElse("?")=?
 */
}
