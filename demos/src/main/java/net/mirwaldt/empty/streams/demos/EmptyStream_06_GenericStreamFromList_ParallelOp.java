package net.mirwaldt.empty.streams.demos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static net.mirwaldt.empty.streams.LazyBuildStreamFactory.lazyBuildGenericStream;
import static net.mirwaldt.empty.streams.demos.PrintUtil.*;

/**
 * Use with VM option -Djol.magicFieldOffset=true
 */
public class EmptyStream_06_GenericStreamFromList_ParallelOp {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        printLineOfSharp(); // ############################################################

        // empty builds
        System.out.println("list=" + list);

        printLineOfSharp(); // ############################################################

        // eager build of empty
        Stream<String> eagerBuildEmpty = list.stream();
        printStatistics("eagerBuildEmpty:", eagerBuildEmpty);

        printLineOfMinus(); // ------------------------------------------------------------

        Stream<String> eagerBuildFilteredEmpty = eagerBuildEmpty.filter(s -> !s.isEmpty());
        printStatistics("eagerBuildFilteredEmpty:", eagerBuildFilteredEmpty);

        printLineOfMinus(); // ------------------------------------------------------------

        Stream<String> eagerBuildFilteredMappedEmpty = eagerBuildFilteredEmpty.map(String::toUpperCase);
        printStatistics("eagerBuildFilteredMappedEmpty:", eagerBuildFilteredMappedEmpty);

        printLineOfMinus(); // ------------------------------------------------------------

        Stream<String> eagerBuildFilteredMappedParallelEmpty = eagerBuildFilteredMappedEmpty.parallel();
        printStatistics("eagerBuildFilteredMappedParallelEmpty:", eagerBuildFilteredMappedParallelEmpty);

        printLineOfMinus(); // ------------------------------------------------------------

        System.out.println("eagerBuildFilteredMappedParallelEmpty.findFirst().orElse(\"?\")="
                + eagerBuildFilteredMappedParallelEmpty.findFirst().orElse("?"));


        printLineOfPlus();  // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


        // lazy build of empty
        Stream<String> lazyBuildEmpty = lazyBuildGenericStream(list.stream());
        printStatistics("lazyBuildEmpty:", lazyBuildEmpty);

        printLineOfMinus(); // ------------------------------------------------------------

        Stream<String> lazyBuildFilteredEmpty = lazyBuildEmpty.filter(s -> !s.isEmpty());
        printStatistics("lazyBuildFilteredEmpty:", lazyBuildFilteredEmpty);

        printLineOfMinus(); // ------------------------------------------------------------

        Stream<String> lazyBuildFilteredMappedEmpty = lazyBuildFilteredEmpty.map(String::toUpperCase);
        printStatistics("lazyBuildFilteredMappedEmpty:", lazyBuildFilteredMappedEmpty);

        printLineOfMinus(); // ------------------------------------------------------------

        Stream<String> lazyBuildFilteredMappedParallelEmpty = lazyBuildFilteredMappedEmpty.parallel();
        printStatistics("lazyBuildFilteredMappedParallelEmpty:", lazyBuildFilteredMappedParallelEmpty);

        printLineOfMinus(); // ------------------------------------------------------------

        System.out.println("lazyBuildFilteredMappedParallelEmpty.findFirst().orElse(\"?\")="
                + lazyBuildFilteredMappedParallelEmpty.findFirst().orElse("?"));


        printLineOfSharp(); // ############################################################

        // non-empty builds
        list.add("");
        list.add("Heinz");
        list.add("Michael");
        System.out.println("list=" + list.stream().map(s -> "\"" + s + "\"").toList());

        printLineOfSharp(); // ############################################################

        // eager build of empty
        Stream<String> eagerBuildNonEmpty = list.stream();
        printStatistics("eagerBuildNonEmpty:", eagerBuildNonEmpty);

        printLineOfMinus(); // ------------------------------------------------------------

        Stream<String> eagerBuildFilteredNonEmpty = eagerBuildNonEmpty.filter(s -> !s.isEmpty());
        printStatistics("eagerBuildFilteredNonEmpty:", eagerBuildFilteredNonEmpty);

        printLineOfMinus(); // ------------------------------------------------------------

        Stream<String> eagerBuildFilteredMappedNonEmpty = eagerBuildFilteredNonEmpty.map(String::toUpperCase);
        printStatistics("eagerBuildFilteredMappedNonEmpty:", eagerBuildFilteredMappedNonEmpty);

        printLineOfMinus(); // ------------------------------------------------------------

        Stream<String> eagerBuildFilteredMappedParallelNonEmpty = eagerBuildFilteredMappedNonEmpty.parallel();
        printStatistics("eagerBuildFilteredMappedParallelNonEmpty:", eagerBuildFilteredMappedParallelNonEmpty);

        printLineOfMinus(); // ------------------------------------------------------------

        System.out.println("eagerBuildFilteredMappedParallelNonEmpty.findFirst().orElse(\"?\")="
                + eagerBuildFilteredMappedParallelNonEmpty.findFirst().orElse("?"));


        printLineOfPlus();  // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


        // lazy build of empty
        Stream<String> lazyBuildNonEmpty = lazyBuildGenericStream(list.stream());
        printStatistics("lazyBuildNonEmpty:", lazyBuildNonEmpty);

        printLineOfMinus(); // ------------------------------------------------------------

        Stream<String> lazyBuildFilteredNonEmpty = lazyBuildNonEmpty.filter(s -> !s.isEmpty());
        printStatistics("lazyBuildFilteredNonEmpty:", lazyBuildFilteredNonEmpty);

        printLineOfMinus(); // ------------------------------------------------------------

        Stream<String> lazyBuildFilteredMappedNonEmpty = lazyBuildFilteredNonEmpty.map(String::toUpperCase);
        printStatistics("lazyBuildFilteredMappedNonEmpty:", lazyBuildFilteredMappedNonEmpty);

        printLineOfMinus(); // ------------------------------------------------------------

        Stream<String> lazyBuildFilteredMappedParallelNonEmpty = lazyBuildFilteredMappedNonEmpty.parallel();
        printStatistics("lazyBuildFilteredMappedParallelNonEmpty:", lazyBuildFilteredMappedParallelNonEmpty);

        printLineOfMinus(); // ------------------------------------------------------------

        System.out.println("lazyBuildFilteredMappedParallelNonEmpty.findFirst().orElse(\"?\")="
                + lazyBuildFilteredMappedParallelNonEmpty.findFirst().orElse("?"));
    }

// Output:
/*
############################################################
list=[]
############################################################
    eagerBuildEmpty:
    totalSize=128 bytes
            totalCount=4 objects
    java.util.stream.ReferencePipeline$Head@4f3f5b24d object externals:
    ADDRESS       SIZE TYPE                                     PATH                           VALUE
        713c23060         24 java.util.ArrayList                      .sourceSpliterator.this$0      (object)
            713c23078      35800 (something else)                         (somewhere else)               (something else)
            713c2bc50         32 java.util.ArrayList$ArrayListSpliterator .sourceSpliterator             (object)
            713c2bc70         56 java.util.stream.ReferencePipeline$Head                                 (object)
            713c2bca8    2080328 (something else)                         (somewhere else)               (something else)
            713e27af0         16 [Ljava.lang.Object;                      .sourceSpliterator.this$0.elementData []

    Addresses are stable after 1 tries.


------------------------------------------------------------
    eagerBuildFilteredEmpty:
    totalSize=208 bytes
            totalCount=6 objects
    java.util.stream.ReferencePipeline$2@612fc6ebd object externals:
    ADDRESS       SIZE TYPE                                                                                                PATH                           VALUE
        713697d48         16 net.mirwaldt.empty.streams.demos.EmptyStream_06_GenericStreamFromList$$Lambda$89/0x0000000800c1ec28 .val$predicate                 (object)
        713697d58        584 (something else)                                                                                    (somewhere else)               (something else)
            713697fa0         64 java.util.stream.ReferencePipeline$2                                                                                               (object)
            713697fe0    5812352 (something else)                                                                                    (somewhere else)               (something else)
            713c23060         24 java.util.ArrayList                                                                                 .this$0.sourceSpliterator.this$0 (object)
            713c23078      35800 (something else)                                                                                    (somewhere else)               (something else)
            713c2bc50         32 java.util.ArrayList$ArrayListSpliterator                                                            .this$0.sourceSpliterator      (object)
            713c2bc70         56 java.util.stream.ReferencePipeline$Head                                                             .this$0                        (object)
            713c2bca8    2080328 (something else)                                                                                    (somewhere else)               (something else)
            713e27af0         16 [Ljava.lang.Object;                                                                                 .this$0.sourceSpliterator.this$0.elementData []

    Addresses are stable after 1 tries.


------------------------------------------------------------
    eagerBuildFilteredMappedEmpty:
    totalSize=288 bytes
            totalCount=8 objects
    java.util.stream.ReferencePipeline$3@491cc5c9d object externals:
    ADDRESS       SIZE TYPE                                                                                                PATH                           VALUE
        713697d48         16 net.mirwaldt.empty.streams.demos.EmptyStream_06_GenericStreamFromList$$Lambda$89/0x0000000800c1ec28 .this$0.val$predicate          (object)
            713697d58        584 (something else)                                                                                    (somewhere else)               (something else)
            713697fa0         64 java.util.stream.ReferencePipeline$2                                                                .this$0                        (object)
            713697fe0     211128 (something else)                                                                                    (somewhere else)               (something else)
            7136cb898         16 net.mirwaldt.empty.streams.demos.EmptyStream_06_GenericStreamFromList$$Lambda$90/0x0000000800c1ee70 .val$mapper                    (object)
        7136cb8a8        352 (something else)                                                                                    (somewhere else)               (something else)
            7136cba08         64 java.util.stream.ReferencePipeline$3                                                                                               (object)
            7136cba48    5600792 (something else)                                                                                    (somewhere else)               (something else)
            713c23060         24 java.util.ArrayList                                                                                 .sourceStage.sourceSpliterator.this$0 (object)
            713c23078      35800 (something else)                                                                                    (somewhere else)               (something else)
            713c2bc50         32 java.util.ArrayList$ArrayListSpliterator                                                            .sourceStage.sourceSpliterator (object)
            713c2bc70         56 java.util.stream.ReferencePipeline$Head                                                             .sourceStage                   (object)
            713c2bca8    2080328 (something else)                                                                                    (somewhere else)               (something else)
            713e27af0         16 [Ljava.lang.Object;                                                                                 .sourceStage.sourceSpliterator.this$0.elementData []

    Addresses are stable after 1 tries.


------------------------------------------------------------
    eagerBuildFilteredMappedParallelEmpty:
    totalSize=288 bytes
            totalCount=8 objects
    java.util.stream.ReferencePipeline$3@491cc5c9d object externals:
    ADDRESS       SIZE TYPE                                                                                                PATH                           VALUE
        713697d48         16 net.mirwaldt.empty.streams.demos.EmptyStream_06_GenericStreamFromList$$Lambda$89/0x0000000800c1ec28 .this$0.val$predicate          (object)
            713697d58        584 (something else)                                                                                    (somewhere else)               (something else)
            713697fa0         64 java.util.stream.ReferencePipeline$2                                                                .this$0                        (object)
            713697fe0     211128 (something else)                                                                                    (somewhere else)               (something else)
            7136cb898         16 net.mirwaldt.empty.streams.demos.EmptyStream_06_GenericStreamFromList$$Lambda$90/0x0000000800c1ee70 .val$mapper                    (object)
        7136cb8a8        352 (something else)                                                                                    (somewhere else)               (something else)
            7136cba08         64 java.util.stream.ReferencePipeline$3                                                                                               (object)
            7136cba48    5600792 (something else)                                                                                    (somewhere else)               (something else)
            713c23060         24 java.util.ArrayList                                                                                 .sourceStage.sourceSpliterator.this$0 (object)
            713c23078      35800 (something else)                                                                                    (somewhere else)               (something else)
            713c2bc50         32 java.util.ArrayList$ArrayListSpliterator                                                            .sourceStage.sourceSpliterator (object)
            713c2bc70         56 java.util.stream.ReferencePipeline$Head                                                             .sourceStage                   (object)
            713c2bca8    2080328 (something else)                                                                                    (somewhere else)               (something else)
            713e27af0         16 [Ljava.lang.Object;                                                                                 .sourceStage.sourceSpliterator.this$0.elementData []

    Addresses are stable after 1 tries.


------------------------------------------------------------
        eagerBuildFilteredMappedParallelEmpty.findFirst().orElse("?")=?
            ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    lazyBuildEmpty:
    totalSize=96 bytes
            totalCount=4 objects
    net.mirwaldt.empty.streams.LazyBuildGenericStream@2db7a79bd object externals:
    ADDRESS       SIZE TYPE                                              PATH                           VALUE
        71374dd20         32 java.util.ArrayList$ArrayListSpliterator          .spliterator                   (object)
            71374dd40      79032 (something else)                                  (somewhere else)               (something else)
            7137611f8         24 net.mirwaldt.empty.streams.LazyBuildGenericStream                                (object)
            713761210    4988496 (something else)                                  (somewhere else)               (something else)
            713c23060         24 java.util.ArrayList                               .spliterator.this$0            (object)
            713c23078    2116216 (something else)                                  (somewhere else)               (something else)
            713e27af0         16 [Ljava.lang.Object;                               .spliterator.this$0.elementData []

    Addresses are stable after 1 tries.


------------------------------------------------------------
    lazyBuildFilteredEmpty:
    totalSize=176 bytes
            totalCount=8 objects
    net.mirwaldt.empty.streams.LazyBuildGenericStream@569cfc36d object externals:
    ADDRESS       SIZE TYPE                                                                                                PATH                           VALUE
        71374dd20         32 java.util.ArrayList$ArrayListSpliterator                                                            .spliterator                   (object)
            71374dd40      79032 (something else)                                                                                    (somewhere else)               (something else)
            7137611f8         24 net.mirwaldt.empty.streams.LazyBuildGenericStream                                                   .streamSupplier.arg$2          (object)
            713761210      54880 (something else)                                                                                    (somewhere else)               (something else)
            71376e870         16 net.mirwaldt.empty.streams.demos.EmptyStream_06_GenericStreamFromList$$Lambda$91/0x0000000800c1fb78 .streamSupplier.arg$1.arg$1    (object)
            71376e880      14120 (something else)                                                                                    (somewhere else)               (something else)
            713771fa8         16 net.mirwaldt.empty.streams.LazyBuildGenericStream$$Lambda$92/0x0000000800c1fdc0                     .streamSupplier.arg$1          (object)
            713771fb8      37528 (something else)                                                                                    (somewhere else)               (something else)
            71377b250         24 net.mirwaldt.empty.streams.util.LazyBuildGenericStreamUtil$$Lambda$93/0x0000000800c87420            .streamSupplier                (object)
        71377b268         24 net.mirwaldt.empty.streams.LazyBuildGenericStream                                                                                  (object)
            71377b280    4881888 (something else)                                                                                    (somewhere else)               (something else)
            713c23060         24 java.util.ArrayList                                                                                 .spliterator.this$0            (object)
            713c23078    2116216 (something else)                                                                                    (somewhere else)               (something else)
            713e27af0         16 [Ljava.lang.Object;                                                                                 .spliterator.this$0.elementData []

    Addresses are stable after 1 tries.


------------------------------------------------------------
    lazyBuildFilteredMappedEmpty:
    totalSize=232 bytes
            totalCount=11 objects
    net.mirwaldt.empty.streams.LazyBuildGenericStream@1d371b2dd object externals:
    ADDRESS       SIZE TYPE                                                                                                PATH                           VALUE
        71374dd20         32 java.util.ArrayList$ArrayListSpliterator                                                            .spliterator                   (object)
            71374dd40      79032 (something else)                                                                                    (somewhere else)               (something else)
            7137611f8         24 net.mirwaldt.empty.streams.LazyBuildGenericStream                                                   .streamSupplier.arg$2.arg$2    (object)
            713761210      54880 (something else)                                                                                    (somewhere else)               (something else)
            71376e870         16 net.mirwaldt.empty.streams.demos.EmptyStream_06_GenericStreamFromList$$Lambda$91/0x0000000800c1fb78 .streamSupplier.arg$2.arg$1.arg$1 (object)
            71376e880      14120 (something else)                                                                                    (somewhere else)               (something else)
            713771fa8         16 net.mirwaldt.empty.streams.LazyBuildGenericStream$$Lambda$92/0x0000000800c1fdc0                     .streamSupplier.arg$2.arg$1    (object)
            713771fb8      37528 (something else)                                                                                    (somewhere else)               (something else)
            71377b250         24 net.mirwaldt.empty.streams.util.LazyBuildGenericStreamUtil$$Lambda$93/0x0000000800c87420            .streamSupplier.arg$2          (object)
            71377b268     237224 (something else)                                                                                    (somewhere else)               (something else)
            7137b5110         16 net.mirwaldt.empty.streams.demos.EmptyStream_06_GenericStreamFromList$$Lambda$94/0x0000000800c87640 .streamSupplier.arg$1.arg$1    (object)
            7137b5120      13288 (something else)                                                                                    (somewhere else)               (something else)
            7137b8508         16 net.mirwaldt.empty.streams.LazyBuildGenericStream$$Lambda$95/0x0000000800c87878                     .streamSupplier.arg$1          (object)
            7137b8518         24 net.mirwaldt.empty.streams.util.LazyBuildGenericStreamUtil$$Lambda$93/0x0000000800c87420            .streamSupplier                (object)
        7137b8530         24 net.mirwaldt.empty.streams.LazyBuildGenericStream                                                                                  (object)
            7137b8548    4631320 (something else)                                                                                    (somewhere else)               (something else)
            713c23060         24 java.util.ArrayList                                                                                 .spliterator.this$0            (object)
            713c23078    2116216 (something else)                                                                                    (somewhere else)               (something else)
            713e27af0         16 [Ljava.lang.Object;                                                                                 .spliterator.this$0.elementData []

    Addresses are stable after 1 tries.


------------------------------------------------------------
    lazyBuildFilteredMappedParallelEmpty:
    totalSize=272 bytes
            totalCount=13 objects
    net.mirwaldt.empty.streams.LazyBuildGenericStream@1fc2b765d object externals:
    ADDRESS       SIZE TYPE                                                                                                PATH                           VALUE
        71374dd20         32 java.util.ArrayList$ArrayListSpliterator                                                            .spliterator                   (object)
            71374dd40      79032 (something else)                                                                                    (somewhere else)               (something else)
            7137611f8         24 net.mirwaldt.empty.streams.LazyBuildGenericStream                                                   .streamSupplier.arg$2.arg$2.arg$2 (object)
            713761210      54880 (something else)                                                                                    (somewhere else)               (something else)
            71376e870         16 net.mirwaldt.empty.streams.demos.EmptyStream_06_GenericStreamFromList$$Lambda$91/0x0000000800c1fb78 .streamSupplier.arg$2.arg$2.arg$1.arg$1 (object)
            71376e880      14120 (something else)                                                                                    (somewhere else)               (something else)
            713771fa8         16 net.mirwaldt.empty.streams.LazyBuildGenericStream$$Lambda$92/0x0000000800c1fdc0                     .streamSupplier.arg$2.arg$2.arg$1 (object)
            713771fb8      37528 (something else)                                                                                    (somewhere else)               (something else)
            71377b250         24 net.mirwaldt.empty.streams.util.LazyBuildGenericStreamUtil$$Lambda$93/0x0000000800c87420            .streamSupplier.arg$2.arg$2    (object)
            71377b268     237224 (something else)                                                                                    (somewhere else)               (something else)
            7137b5110         16 net.mirwaldt.empty.streams.demos.EmptyStream_06_GenericStreamFromList$$Lambda$94/0x0000000800c87640 .streamSupplier.arg$2.arg$1.arg$1 (object)
            7137b5120      13288 (something else)                                                                                    (somewhere else)               (something else)
            7137b8508         16 net.mirwaldt.empty.streams.LazyBuildGenericStream$$Lambda$95/0x0000000800c87878                     .streamSupplier.arg$2.arg$1    (object)
            7137b8518         24 net.mirwaldt.empty.streams.util.LazyBuildGenericStreamUtil$$Lambda$93/0x0000000800c87420            .streamSupplier.arg$2          (object)
            7137b8530     119440 (something else)                                                                                    (somewhere else)               (something else)
            7137d57c0         16 net.mirwaldt.empty.streams.LazyBuildGenericStream$$Lambda$96/0x0000000800c87ab8                     .streamSupplier.arg$1          (object)
            7137d57d0        352 (something else)                                                                                    (somewhere else)               (something else)
            7137d5930         24 net.mirwaldt.empty.streams.util.LazyBuildGenericStreamUtil$$Lambda$93/0x0000000800c87420            .streamSupplier                (object)
        7137d5948         24 net.mirwaldt.empty.streams.LazyBuildGenericStream                                                                                  (object)
            7137d5960    4511488 (something else)                                                                                    (somewhere else)               (something else)
            713c23060         24 java.util.ArrayList                                                                                 .spliterator.this$0            (object)
            713c23078    2116216 (something else)                                                                                    (somewhere else)               (something else)
            713e27af0         16 [Ljava.lang.Object;                                                                                 .spliterator.this$0.elementData []

    Addresses are stable after 1 tries.


------------------------------------------------------------
        lazyBuildFilteredMappedParallelEmpty.findFirst().orElse("?")=?
            ############################################################
    list=["", "Heinz", "Michael"]
            ############################################################
    eagerBuildNonEmpty:
    totalSize=304 bytes
            totalCount=10 objects
    java.util.stream.ReferencePipeline$Head@12c8a2c0d object externals:
    ADDRESS       SIZE TYPE                                     PATH                           VALUE
        7137f2388         56 [Ljava.lang.Object;                      .sourceSpliterator.this$0.elementData [(object), (object), (object), null, null, null, null, null, null, null]
            7137f23c0         24 java.lang.String                         .sourceSpliterator.this$0.elementData[1] (object)
            7137f23d8         24 [B                                       .sourceSpliterator.this$0.elementData[1].value [72, 101, 105, 110, 122]
            7137f23f0         24 java.lang.String                         .sourceSpliterator.this$0.elementData[2] (object)
            7137f2408         24 [B                                       .sourceSpliterator.this$0.elementData[2].value [77, 105, 99, 104, 97, 101, 108]
            7137f2420      24760 (something else)                         (somewhere else)               (something else)
            7137f84d8         32 java.util.ArrayList$ArrayListSpliterator .sourceSpliterator             (object)
            7137f84f8         56 java.util.stream.ReferencePipeline$Head                                 (object)
            7137f8530    4369200 (something else)                         (somewhere else)               (something else)
            713c23060         24 java.util.ArrayList                      .sourceSpliterator.this$0      (object)
            713c23078    2020256 (something else)                         (somewhere else)               (something else)
            713e10418         24 java.lang.String                         .sourceSpliterator.this$0.elementData[0] (object)
            713e10430         16 [B                                       .sourceSpliterator.this$0.elementData[0].value []

    Addresses are stable after 1 tries.


------------------------------------------------------------
    eagerBuildFilteredNonEmpty:
    totalSize=384 bytes
            totalCount=12 objects
    java.util.stream.ReferencePipeline$2@2e3fc542d object externals:
    ADDRESS       SIZE TYPE                                                                                                 PATH                           VALUE
        713428150         16 net.mirwaldt.empty.streams.demos.EmptyStream_06_GenericStreamFromList$$Lambda$100/0x0000000800c86a38 .val$predicate                 (object)
        713428160        352 (something else)                                                                                     (somewhere else)               (something else)
            7134282c0         64 java.util.stream.ReferencePipeline$2                                                                                                (object)
            713428300    3973256 (something else)                                                                                     (somewhere else)               (something else)
            7137f2388         56 [Ljava.lang.Object;                                                                                  .this$0.sourceSpliterator.this$0.elementData [(object), (object), (object), null, null, null, null, null, null, null]
            7137f23c0         24 java.lang.String                                                                                     .this$0.sourceSpliterator.this$0.elementData[1] (object)
            7137f23d8         24 [B                                                                                                   .this$0.sourceSpliterator.this$0.elementData[1].value [72, 101, 105, 110, 122]
            7137f23f0         24 java.lang.String                                                                                     .this$0.sourceSpliterator.this$0.elementData[2] (object)
            7137f2408         24 [B                                                                                                   .this$0.sourceSpliterator.this$0.elementData[2].value [77, 105, 99, 104, 97, 101, 108]
            7137f2420      24760 (something else)                                                                                     (somewhere else)               (something else)
            7137f84d8         32 java.util.ArrayList$ArrayListSpliterator                                                             .this$0.sourceSpliterator      (object)
            7137f84f8         56 java.util.stream.ReferencePipeline$Head                                                              .this$0                        (object)
            7137f8530    4369200 (something else)                                                                                     (somewhere else)               (something else)
            713c23060         24 java.util.ArrayList                                                                                  .this$0.sourceSpliterator.this$0 (object)
            713c23078    2020256 (something else)                                                                                     (somewhere else)               (something else)
            713e10418         24 java.lang.String                                                                                     .this$0.sourceSpliterator.this$0.elementData[0] (object)
            713e10430         16 [B                                                                                                   .this$0.sourceSpliterator.this$0.elementData[0].value []

    Addresses are stable after 1 tries.


------------------------------------------------------------
    eagerBuildFilteredMappedNonEmpty:
    totalSize=464 bytes
            totalCount=14 objects
    java.util.stream.ReferencePipeline$3@401e7803d object externals:
    ADDRESS       SIZE TYPE                                                                                                 PATH                           VALUE
        713428150         16 net.mirwaldt.empty.streams.demos.EmptyStream_06_GenericStreamFromList$$Lambda$100/0x0000000800c86a38 .this$0.val$predicate          (object)
            713428160        352 (something else)                                                                                     (somewhere else)               (something else)
            7134282c0         64 java.util.stream.ReferencePipeline$2                                                                 .this$0                        (object)
            713428300     223008 (something else)                                                                                     (somewhere else)               (something else)
            71345ea20         16 net.mirwaldt.empty.streams.demos.EmptyStream_06_GenericStreamFromList$$Lambda$101/0x0000000800c86c80 .val$mapper                    (object)
        71345ea30        352 (something else)                                                                                     (somewhere else)               (something else)
            71345eb90         64 java.util.stream.ReferencePipeline$3                                                                                                (object)
            71345ebd0    3749816 (something else)                                                                                     (somewhere else)               (something else)
            7137f2388         56 [Ljava.lang.Object;                                                                                  .sourceStage.sourceSpliterator.this$0.elementData [(object), (object), (object), null, null, null, null, null, null, null]
            7137f23c0         24 java.lang.String                                                                                     .sourceStage.sourceSpliterator.this$0.elementData[1] (object)
            7137f23d8         24 [B                                                                                                   .sourceStage.sourceSpliterator.this$0.elementData[1].value [72, 101, 105, 110, 122]
            7137f23f0         24 java.lang.String                                                                                     .sourceStage.sourceSpliterator.this$0.elementData[2] (object)
            7137f2408         24 [B                                                                                                   .sourceStage.sourceSpliterator.this$0.elementData[2].value [77, 105, 99, 104, 97, 101, 108]
            7137f2420      24760 (something else)                                                                                     (somewhere else)               (something else)
            7137f84d8         32 java.util.ArrayList$ArrayListSpliterator                                                             .sourceStage.sourceSpliterator (object)
            7137f84f8         56 java.util.stream.ReferencePipeline$Head                                                              .sourceStage                   (object)
            7137f8530    4369200 (something else)                                                                                     (somewhere else)               (something else)
            713c23060         24 java.util.ArrayList                                                                                  .sourceStage.sourceSpliterator.this$0 (object)
            713c23078    2020256 (something else)                                                                                     (somewhere else)               (something else)
            713e10418         24 java.lang.String                                                                                     .sourceStage.sourceSpliterator.this$0.elementData[0] (object)
            713e10430         16 [B                                                                                                   .sourceStage.sourceSpliterator.this$0.elementData[0].value []

    Addresses are stable after 1 tries.


------------------------------------------------------------
    eagerBuildFilteredMappedParallelNonEmpty:
    totalSize=464 bytes
            totalCount=14 objects
    java.util.stream.ReferencePipeline$3@401e7803d object externals:
    ADDRESS       SIZE TYPE                                                                                                 PATH                           VALUE
        713428150         16 net.mirwaldt.empty.streams.demos.EmptyStream_06_GenericStreamFromList$$Lambda$100/0x0000000800c86a38 .this$0.val$predicate          (object)
            713428160        352 (something else)                                                                                     (somewhere else)               (something else)
            7134282c0         64 java.util.stream.ReferencePipeline$2                                                                 .this$0                        (object)
            713428300     223008 (something else)                                                                                     (somewhere else)               (something else)
            71345ea20         16 net.mirwaldt.empty.streams.demos.EmptyStream_06_GenericStreamFromList$$Lambda$101/0x0000000800c86c80 .val$mapper                    (object)
        71345ea30        352 (something else)                                                                                     (somewhere else)               (something else)
            71345eb90         64 java.util.stream.ReferencePipeline$3                                                                                                (object)
            71345ebd0    3749816 (something else)                                                                                     (somewhere else)               (something else)
            7137f2388         56 [Ljava.lang.Object;                                                                                  .sourceStage.sourceSpliterator.this$0.elementData [(object), (object), (object), null, null, null, null, null, null, null]
            7137f23c0         24 java.lang.String                                                                                     .sourceStage.sourceSpliterator.this$0.elementData[1] (object)
            7137f23d8         24 [B                                                                                                   .sourceStage.sourceSpliterator.this$0.elementData[1].value [72, 101, 105, 110, 122]
            7137f23f0         24 java.lang.String                                                                                     .sourceStage.sourceSpliterator.this$0.elementData[2] (object)
            7137f2408         24 [B                                                                                                   .sourceStage.sourceSpliterator.this$0.elementData[2].value [77, 105, 99, 104, 97, 101, 108]
            7137f2420      24760 (something else)                                                                                     (somewhere else)               (something else)
            7137f84d8         32 java.util.ArrayList$ArrayListSpliterator                                                             .sourceStage.sourceSpliterator (object)
            7137f84f8         56 java.util.stream.ReferencePipeline$Head                                                              .sourceStage                   (object)
            7137f8530    4369200 (something else)                                                                                     (somewhere else)               (something else)
            713c23060         24 java.util.ArrayList                                                                                  .sourceStage.sourceSpliterator.this$0 (object)
            713c23078    2020256 (something else)                                                                                     (somewhere else)               (something else)
            713e10418         24 java.lang.String                                                                                     .sourceStage.sourceSpliterator.this$0.elementData[0] (object)
            713e10430         16 [B                                                                                                   .sourceStage.sourceSpliterator.this$0.elementData[0].value []

    Addresses are stable after 1 tries.


------------------------------------------------------------
        eagerBuildFilteredMappedParallelNonEmpty.findFirst().orElse("?")=HEINZ
++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    lazyBuildNonEmpty:
    totalSize=272 bytes
            totalCount=10 objects
    net.mirwaldt.empty.streams.LazyBuildGenericStream@2eda0940d object externals:
    ADDRESS       SIZE TYPE                                              PATH                           VALUE
        7134efbc0         32 java.util.ArrayList$ArrayListSpliterator          .spliterator                   (object)
            7134efbe0         56 (something else)                                  (somewhere else)               (something else)
            7134efc18         24 net.mirwaldt.empty.streams.LazyBuildGenericStream                                (object)
            7134efc30    3155800 (something else)                                  (somewhere else)               (something else)
            7137f2388         56 [Ljava.lang.Object;                               .spliterator.this$0.elementData [(object), (object), (object), null, null, null, null, null, null, null]
            7137f23c0         24 java.lang.String                                  .spliterator.this$0.elementData[1] (object)
            7137f23d8         24 [B                                                .spliterator.this$0.elementData[1].value [72, 101, 105, 110, 122]
            7137f23f0         24 java.lang.String                                  .spliterator.this$0.elementData[2] (object)
            7137f2408         24 [B                                                .spliterator.this$0.elementData[2].value [77, 105, 99, 104, 97, 101, 108]
            7137f2420    4394048 (something else)                                  (somewhere else)               (something else)
            713c23060         24 java.util.ArrayList                               .spliterator.this$0            (object)
            713c23078    2020256 (something else)                                  (somewhere else)               (something else)
            713e10418         24 java.lang.String                                  .spliterator.this$0.elementData[0] (object)
            713e10430         16 [B                                                .spliterator.this$0.elementData[0].value []

    Addresses are stable after 1 tries.


------------------------------------------------------------
    lazyBuildFilteredNonEmpty:
    totalSize=352 bytes
            totalCount=14 objects
    net.mirwaldt.empty.streams.LazyBuildGenericStream@6eceb130d object externals:
    ADDRESS       SIZE TYPE                                                                                                 PATH                           VALUE
        7134efbc0         32 java.util.ArrayList$ArrayListSpliterator                                                             .spliterator                   (object)
            7134efbe0         56 (something else)                                                                                     (somewhere else)               (something else)
            7134efc18         24 net.mirwaldt.empty.streams.LazyBuildGenericStream                                                    .streamSupplier.arg$2          (object)
            7134efc30      97576 (something else)                                                                                     (somewhere else)               (something else)
            713507958         16 net.mirwaldt.empty.streams.demos.EmptyStream_06_GenericStreamFromList$$Lambda$102/0x0000000800c88000 .streamSupplier.arg$1.arg$1    (object)
            713507968        352 (something else)                                                                                     (somewhere else)               (something else)
            713507ac8         16 net.mirwaldt.empty.streams.LazyBuildGenericStream$$Lambda$92/0x0000000800c1fdc0                      .streamSupplier.arg$1          (object)
            713507ad8         24 net.mirwaldt.empty.streams.util.LazyBuildGenericStreamUtil$$Lambda$93/0x0000000800c87420             .streamSupplier                (object)
        713507af0         24 net.mirwaldt.empty.streams.LazyBuildGenericStream                                                                                   (object)
            713507b08    3057792 (something else)                                                                                     (somewhere else)               (something else)
            7137f2388         56 [Ljava.lang.Object;                                                                                  .spliterator.this$0.elementData [(object), (object), (object), null, null, null, null, null, null, null]
            7137f23c0         24 java.lang.String                                                                                     .spliterator.this$0.elementData[1] (object)
            7137f23d8         24 [B                                                                                                   .spliterator.this$0.elementData[1].value [72, 101, 105, 110, 122]
            7137f23f0         24 java.lang.String                                                                                     .spliterator.this$0.elementData[2] (object)
            7137f2408         24 [B                                                                                                   .spliterator.this$0.elementData[2].value [77, 105, 99, 104, 97, 101, 108]
            7137f2420    4394048 (something else)                                                                                     (somewhere else)               (something else)
            713c23060         24 java.util.ArrayList                                                                                  .spliterator.this$0            (object)
            713c23078    2020256 (something else)                                                                                     (somewhere else)               (something else)
            713e10418         24 java.lang.String                                                                                     .spliterator.this$0.elementData[0] (object)
            713e10430         16 [B                                                                                                   .spliterator.this$0.elementData[0].value []

    Addresses are stable after 1 tries.


------------------------------------------------------------
    lazyBuildFilteredMappedNonEmpty:
    totalSize=408 bytes
            totalCount=17 objects
    net.mirwaldt.empty.streams.LazyBuildGenericStream@4d1b0d2ad object externals:
    ADDRESS       SIZE TYPE                                                                                                 PATH                           VALUE
        7134efbc0         32 java.util.ArrayList$ArrayListSpliterator                                                             .spliterator                   (object)
            7134efbe0         56 (something else)                                                                                     (somewhere else)               (something else)
            7134efc18         24 net.mirwaldt.empty.streams.LazyBuildGenericStream                                                    .streamSupplier.arg$2.arg$2    (object)
            7134efc30      97576 (something else)                                                                                     (somewhere else)               (something else)
            713507958         16 net.mirwaldt.empty.streams.demos.EmptyStream_06_GenericStreamFromList$$Lambda$102/0x0000000800c88000 .streamSupplier.arg$2.arg$1.arg$1 (object)
            713507968        352 (something else)                                                                                     (somewhere else)               (something else)
            713507ac8         16 net.mirwaldt.empty.streams.LazyBuildGenericStream$$Lambda$92/0x0000000800c1fdc0                      .streamSupplier.arg$2.arg$1    (object)
            713507ad8         24 net.mirwaldt.empty.streams.util.LazyBuildGenericStreamUtil$$Lambda$93/0x0000000800c87420             .streamSupplier.arg$2          (object)
            713507af0     126736 (something else)                                                                                     (somewhere else)               (something else)
            713526a00         16 net.mirwaldt.empty.streams.demos.EmptyStream_06_GenericStreamFromList$$Lambda$103/0x0000000800c88248 .streamSupplier.arg$1.arg$1    (object)
            713526a10        352 (something else)                                                                                     (somewhere else)               (something else)
            713526b70         16 net.mirwaldt.empty.streams.LazyBuildGenericStream$$Lambda$95/0x0000000800c87878                      .streamSupplier.arg$1          (object)
            713526b80         24 net.mirwaldt.empty.streams.util.LazyBuildGenericStreamUtil$$Lambda$93/0x0000000800c87420             .streamSupplier                (object)
        713526b98         24 net.mirwaldt.empty.streams.LazyBuildGenericStream                                                                                   (object)
            713526bb0    2930648 (something else)                                                                                     (somewhere else)               (something else)
            7137f2388         56 [Ljava.lang.Object;                                                                                  .spliterator.this$0.elementData [(object), (object), (object), null, null, null, null, null, null, null]
            7137f23c0         24 java.lang.String                                                                                     .spliterator.this$0.elementData[1] (object)
            7137f23d8         24 [B                                                                                                   .spliterator.this$0.elementData[1].value [72, 101, 105, 110, 122]
            7137f23f0         24 java.lang.String                                                                                     .spliterator.this$0.elementData[2] (object)
            7137f2408         24 [B                                                                                                   .spliterator.this$0.elementData[2].value [77, 105, 99, 104, 97, 101, 108]
            7137f2420    4394048 (something else)                                                                                     (somewhere else)               (something else)
            713c23060         24 java.util.ArrayList                                                                                  .spliterator.this$0            (object)
            713c23078    2020256 (something else)                                                                                     (somewhere else)               (something else)
            713e10418         24 java.lang.String                                                                                     .spliterator.this$0.elementData[0] (object)
            713e10430         16 [B                                                                                                   .spliterator.this$0.elementData[0].value []

    Addresses are stable after 1 tries.


------------------------------------------------------------
    lazyBuildFilteredMappedParallelNonEmpty:
    totalSize=448 bytes
            totalCount=19 objects
    net.mirwaldt.empty.streams.LazyBuildGenericStream@78186a70d object externals:
    ADDRESS       SIZE TYPE                                                                                                 PATH                           VALUE
        7134efbc0         32 java.util.ArrayList$ArrayListSpliterator                                                             .spliterator                   (object)
            7134efbe0         56 (something else)                                                                                     (somewhere else)               (something else)
            7134efc18         24 net.mirwaldt.empty.streams.LazyBuildGenericStream                                                    .streamSupplier.arg$2.arg$2.arg$2 (object)
            7134efc30      97576 (something else)                                                                                     (somewhere else)               (something else)
            713507958         16 net.mirwaldt.empty.streams.demos.EmptyStream_06_GenericStreamFromList$$Lambda$102/0x0000000800c88000 .streamSupplier.arg$2.arg$2.arg$1.arg$1 (object)
            713507968        352 (something else)                                                                                     (somewhere else)               (something else)
            713507ac8         16 net.mirwaldt.empty.streams.LazyBuildGenericStream$$Lambda$92/0x0000000800c1fdc0                      .streamSupplier.arg$2.arg$2.arg$1 (object)
            713507ad8         24 net.mirwaldt.empty.streams.util.LazyBuildGenericStreamUtil$$Lambda$93/0x0000000800c87420             .streamSupplier.arg$2.arg$2    (object)
            713507af0     126736 (something else)                                                                                     (somewhere else)               (something else)
            713526a00         16 net.mirwaldt.empty.streams.demos.EmptyStream_06_GenericStreamFromList$$Lambda$103/0x0000000800c88248 .streamSupplier.arg$2.arg$1.arg$1 (object)
            713526a10        352 (something else)                                                                                     (somewhere else)               (something else)
            713526b70         16 net.mirwaldt.empty.streams.LazyBuildGenericStream$$Lambda$95/0x0000000800c87878                      .streamSupplier.arg$2.arg$1    (object)
            713526b80         24 net.mirwaldt.empty.streams.util.LazyBuildGenericStreamUtil$$Lambda$93/0x0000000800c87420             .streamSupplier.arg$2          (object)
            713526b98     144136 (something else)                                                                                     (somewhere else)               (something else)
            713549ea0         24 net.mirwaldt.empty.streams.util.LazyBuildGenericStreamUtil$$Lambda$93/0x0000000800c87420             .streamSupplier                (object)
        713549eb8         24 net.mirwaldt.empty.streams.LazyBuildGenericStream                                                                                   (object)
            713549ed0    2668784 (something else)                                                                                     (somewhere else)               (something else)
            7137d57c0         16 net.mirwaldt.empty.streams.LazyBuildGenericStream$$Lambda$96/0x0000000800c87ab8                      .streamSupplier.arg$1          (object)
            7137d57d0     117688 (something else)                                                                                     (somewhere else)               (something else)
            7137f2388         56 [Ljava.lang.Object;                                                                                  .spliterator.this$0.elementData [(object), (object), (object), null, null, null, null, null, null, null]
            7137f23c0         24 java.lang.String                                                                                     .spliterator.this$0.elementData[1] (object)
            7137f23d8         24 [B                                                                                                   .spliterator.this$0.elementData[1].value [72, 101, 105, 110, 122]
            7137f23f0         24 java.lang.String                                                                                     .spliterator.this$0.elementData[2] (object)
            7137f2408         24 [B                                                                                                   .spliterator.this$0.elementData[2].value [77, 105, 99, 104, 97, 101, 108]
            7137f2420    4394048 (something else)                                                                                     (somewhere else)               (something else)
            713c23060         24 java.util.ArrayList                                                                                  .spliterator.this$0            (object)
            713c23078    2020256 (something else)                                                                                     (somewhere else)               (something else)
            713e10418         24 java.lang.String                                                                                     .spliterator.this$0.elementData[0] (object)
            713e10430         16 [B                                                                                                   .spliterator.this$0.elementData[0].value []

    Addresses are stable after 1 tries.


------------------------------------------------------------
        lazyBuildFilteredMappedParallelNonEmpty.findFirst().orElse("?")=HEINZ

    Process finished with exit code 0
*/
}
