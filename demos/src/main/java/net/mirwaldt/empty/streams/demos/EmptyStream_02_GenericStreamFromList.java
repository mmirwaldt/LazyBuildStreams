package net.mirwaldt.empty.streams.demos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static net.mirwaldt.empty.streams.LazyBuildStreamFactory.lazyBuildGenericStream;
import static net.mirwaldt.empty.streams.demos.PrintUtil.*;

/**
 * Use with VM option -Djol.magicFieldOffset=true
 */
public class EmptyStream_02_GenericStreamFromList {
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

        System.out.println("eagerBuildFilteredMappedEmpty.findFirst().orElse(\"?\")="
                + eagerBuildFilteredMappedEmpty.findFirst().orElse("?"));


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

        System.out.println("lazyBuildFilteredMappedEmpty.findFirst().orElse(\"?\")="
                + lazyBuildFilteredMappedEmpty.findFirst().orElse("?"));


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

        System.out.println("eagerBuildFilteredMappedNonEmpty.findFirst().orElse(\"?\")="
                + eagerBuildFilteredMappedNonEmpty.findFirst().orElse("?"));


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

        System.out.println("lazyBuildFilteredMappedNonEmpty.findFirst().orElse(\"?\")="
                + lazyBuildFilteredMappedNonEmpty.findFirst().orElse("?"));
    }

// Output:
/*
############################################################
list=[]
############################################################
# WARNING: Unable to get Instrumentation. Dynamic Attach failed. You may add this JAR as -javaagent manually, or supply -Djdk.attach.allowAttachSelf
# WARNING: Unable to attach Serviceability Agent. sun.jvm.hotspot.memory.Universe.getNarrowOopBase()
eagerBuildEmpty:
totalSize=128 bytes
totalCount=4 objects
java.util.stream.ReferencePipeline$Head@4f3f5b24d object externals:
          ADDRESS       SIZE TYPE                                     PATH                           VALUE
        713c22e00         24 java.util.ArrayList                      .sourceSpliterator.this$0      (object)
        713c22e18      35800 (something else)                         (somewhere else)               (something else)
        713c2b9f0         32 java.util.ArrayList$ArrayListSpliterator .sourceSpliterator             (object)
        713c2ba10         56 java.util.stream.ReferencePipeline$Head                                 (object)
        713c2ba48    2080936 (something else)                         (somewhere else)               (something else)
        713e27af0         16 [Ljava.lang.Object;                      .sourceSpliterator.this$0.elementData []

Addresses are stable after 1 tries.


------------------------------------------------------------
eagerBuildFilteredEmpty:
totalSize=208 bytes
totalCount=6 objects
java.util.stream.ReferencePipeline$2@612fc6ebd object externals:
          ADDRESS       SIZE TYPE                                                                                                PATH                           VALUE
        713697d48         16 net.mirwaldt.empty.streams.demos.EmptyStream_02_GenericStreamFromList$$Lambda$89/0x0000000800c1ec28 .val$predicate                 (object)
        713697d58        584 (something else)                                                                                    (somewhere else)               (something else)
        713697fa0         64 java.util.stream.ReferencePipeline$2                                                                                               (object)
        713697fe0    5811744 (something else)                                                                                    (somewhere else)               (something else)
        713c22e00         24 java.util.ArrayList                                                                                 .this$0.sourceSpliterator.this$0 (object)
        713c22e18      35800 (something else)                                                                                    (somewhere else)               (something else)
        713c2b9f0         32 java.util.ArrayList$ArrayListSpliterator                                                            .this$0.sourceSpliterator      (object)
        713c2ba10         56 java.util.stream.ReferencePipeline$Head                                                             .this$0                        (object)
        713c2ba48    2080936 (something else)                                                                                    (somewhere else)               (something else)
        713e27af0         16 [Ljava.lang.Object;                                                                                 .this$0.sourceSpliterator.this$0.elementData []

Addresses are stable after 1 tries.


------------------------------------------------------------
eagerBuildFilteredMappedEmpty:
totalSize=288 bytes
totalCount=8 objects
java.util.stream.ReferencePipeline$3@491cc5c9d object externals:
          ADDRESS       SIZE TYPE                                                                                                PATH                           VALUE
        713697d48         16 net.mirwaldt.empty.streams.demos.EmptyStream_02_GenericStreamFromList$$Lambda$89/0x0000000800c1ec28 .this$0.val$predicate          (object)
        713697d58        584 (something else)                                                                                    (somewhere else)               (something else)
        713697fa0         64 java.util.stream.ReferencePipeline$2                                                                .this$0                        (object)
        713697fe0     211128 (something else)                                                                                    (somewhere else)               (something else)
        7136cb898         16 net.mirwaldt.empty.streams.demos.EmptyStream_02_GenericStreamFromList$$Lambda$90/0x0000000800c1ee70 .val$mapper                    (object)
        7136cb8a8        352 (something else)                                                                                    (somewhere else)               (something else)
        7136cba08         64 java.util.stream.ReferencePipeline$3                                                                                               (object)
        7136cba48    5600184 (something else)                                                                                    (somewhere else)               (something else)
        713c22e00         24 java.util.ArrayList                                                                                 .sourceStage.sourceSpliterator.this$0 (object)
        713c22e18      35800 (something else)                                                                                    (somewhere else)               (something else)
        713c2b9f0         32 java.util.ArrayList$ArrayListSpliterator                                                            .sourceStage.sourceSpliterator (object)
        713c2ba10         56 java.util.stream.ReferencePipeline$Head                                                             .sourceStage                   (object)
        713c2ba48    2080936 (something else)                                                                                    (somewhere else)               (something else)
        713e27af0         16 [Ljava.lang.Object;                                                                                 .sourceStage.sourceSpliterator.this$0.elementData []

Addresses are stable after 1 tries.


------------------------------------------------------------
eagerBuildFilteredMappedEmpty.findFirst().orElse("?")=?
++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
lazyBuildEmpty:
totalSize=96 bytes
totalCount=4 objects
net.mirwaldt.empty.streams.LazyBuildGenericStream@66d1af89d object externals:
          ADDRESS       SIZE TYPE                                              PATH                           VALUE
        713710248         32 java.util.ArrayList$ArrayListSpliterator          .spliterator                   (object)
        713710268      78160 (something else)                                  (somewhere else)               (something else)
        7137233b8         24 net.mirwaldt.empty.streams.LazyBuildGenericStream                                (object)
        7137233d0    5241392 (something else)                                  (somewhere else)               (something else)
        713c22e00         24 java.util.ArrayList                               .spliterator.this$0            (object)
        713c22e18    2116824 (something else)                                  (somewhere else)               (something else)
        713e27af0         16 [Ljava.lang.Object;                               .spliterator.this$0.elementData []

Addresses are stable after 1 tries.


------------------------------------------------------------
lazyBuildFilteredEmpty:
totalSize=176 bytes
totalCount=8 objects
net.mirwaldt.empty.streams.LazyBuildGenericStream@52f759d7d object externals:
          ADDRESS       SIZE TYPE                                                                                                PATH                           VALUE
        713710248         32 java.util.ArrayList$ArrayListSpliterator                                                            .spliterator                   (object)
        713710268      78160 (something else)                                                                                    (somewhere else)               (something else)
        7137233b8         24 net.mirwaldt.empty.streams.LazyBuildGenericStream                                                   .streamSupplier.arg$2          (object)
        7137233d0      54752 (something else)                                                                                    (somewhere else)               (something else)
        7137309b0         16 net.mirwaldt.empty.streams.demos.EmptyStream_02_GenericStreamFromList$$Lambda$91/0x0000000800c1fb78 .streamSupplier.arg$1.arg$1    (object)
        7137309c0      14120 (something else)                                                                                    (somewhere else)               (something else)
        7137340e8         16 net.mirwaldt.empty.streams.LazyBuildGenericStream$$Lambda$92/0x0000000800c1fdc0                     .streamSupplier.arg$1          (object)
        7137340f8      37528 (something else)                                                                                    (somewhere else)               (something else)
        71373d390         24 net.mirwaldt.empty.streams.util.LazyBuildGenericStreamUtil$$Lambda$93/0x0000000800c87420            .streamSupplier                (object)
        71373d3a8         24 net.mirwaldt.empty.streams.LazyBuildGenericStream                                                                                  (object)
        71373d3c0    5134912 (something else)                                                                                    (somewhere else)               (something else)
        713c22e00         24 java.util.ArrayList                                                                                 .spliterator.this$0            (object)
        713c22e18    2116824 (something else)                                                                                    (somewhere else)               (something else)
        713e27af0         16 [Ljava.lang.Object;                                                                                 .spliterator.this$0.elementData []

Addresses are stable after 1 tries.


------------------------------------------------------------
lazyBuildFilteredMappedEmpty:
totalSize=232 bytes
totalCount=11 objects
net.mirwaldt.empty.streams.LazyBuildGenericStream@23fe1d71d object externals:
          ADDRESS       SIZE TYPE                                                                                                PATH                           VALUE
        713710248         32 java.util.ArrayList$ArrayListSpliterator                                                            .spliterator                   (object)
        713710268      78160 (something else)                                                                                    (somewhere else)               (something else)
        7137233b8         24 net.mirwaldt.empty.streams.LazyBuildGenericStream                                                   .streamSupplier.arg$2.arg$2    (object)
        7137233d0      54752 (something else)                                                                                    (somewhere else)               (something else)
        7137309b0         16 net.mirwaldt.empty.streams.demos.EmptyStream_02_GenericStreamFromList$$Lambda$91/0x0000000800c1fb78 .streamSupplier.arg$2.arg$1.arg$1 (object)
        7137309c0      14120 (something else)                                                                                    (somewhere else)               (something else)
        7137340e8         16 net.mirwaldt.empty.streams.LazyBuildGenericStream$$Lambda$92/0x0000000800c1fdc0                     .streamSupplier.arg$2.arg$1    (object)
        7137340f8      37528 (something else)                                                                                    (somewhere else)               (something else)
        71373d390         24 net.mirwaldt.empty.streams.util.LazyBuildGenericStreamUtil$$Lambda$93/0x0000000800c87420            .streamSupplier.arg$2          (object)
        71373d3a8     237832 (something else)                                                                                    (somewhere else)               (something else)
        7137774b0         16 net.mirwaldt.empty.streams.demos.EmptyStream_02_GenericStreamFromList$$Lambda$94/0x0000000800c87640 .streamSupplier.arg$1.arg$1    (object)
        7137774c0      13288 (something else)                                                                                    (somewhere else)               (something else)
        71377a8a8         16 net.mirwaldt.empty.streams.LazyBuildGenericStream$$Lambda$95/0x0000000800c87878                     .streamSupplier.arg$1          (object)
        71377a8b8         24 net.mirwaldt.empty.streams.util.LazyBuildGenericStreamUtil$$Lambda$93/0x0000000800c87420            .streamSupplier                (object)
        71377a8d0         24 net.mirwaldt.empty.streams.LazyBuildGenericStream                                                                                  (object)
        71377a8e8    4883736 (something else)                                                                                    (somewhere else)               (something else)
        713c22e00         24 java.util.ArrayList                                                                                 .spliterator.this$0            (object)
        713c22e18    2116824 (something else)                                                                                    (somewhere else)               (something else)
        713e27af0         16 [Ljava.lang.Object;                                                                                 .spliterator.this$0.elementData []

Addresses are stable after 1 tries.


------------------------------------------------------------
lazyBuildFilteredMappedEmpty.findFirst().orElse("?")=?
############################################################
list=["", "Heinz", "Michael"]
############################################################
eagerBuildNonEmpty:
totalSize=304 bytes
totalCount=10 objects
java.util.stream.ReferencePipeline$Head@2a70a3d8d object externals:
          ADDRESS       SIZE TYPE                                     PATH                           VALUE
        713794cd0         56 [Ljava.lang.Object;                      .sourceSpliterator.this$0.elementData [(object), (object), (object), null, null, null, null, null, null, null]
        713794d08         24 java.lang.String                         .sourceSpliterator.this$0.elementData[1] (object)
        713794d20         24 [B                                       .sourceSpliterator.this$0.elementData[1].value [72, 101, 105, 110, 122]
        713794d38         24 java.lang.String                         .sourceSpliterator.this$0.elementData[2] (object)
        713794d50         24 [B                                       .sourceSpliterator.this$0.elementData[2].value [77, 105, 99, 104, 97, 101, 108]
        713794d68      24760 (something else)                         (somewhere else)               (something else)
        71379ae20         32 java.util.ArrayList$ArrayListSpliterator .sourceSpliterator             (object)
        71379ae40         56 java.util.stream.ReferencePipeline$Head                                 (object)
        71379ae78    4751240 (something else)                         (somewhere else)               (something else)
        713c22e00         24 java.util.ArrayList                      .sourceSpliterator.this$0      (object)
        713c22e18    2020864 (something else)                         (somewhere else)               (something else)
        713e10418         24 java.lang.String                         .sourceSpliterator.this$0.elementData[0] (object)
        713e10430         16 [B                                       .sourceSpliterator.this$0.elementData[0].value []

Addresses are stable after 1 tries.


------------------------------------------------------------
eagerBuildFilteredNonEmpty:
totalSize=384 bytes
totalCount=12 objects
java.util.stream.ReferencePipeline$2@4fb64261d object externals:
          ADDRESS       SIZE TYPE                                                                                                PATH                           VALUE
        713794cd0         56 [Ljava.lang.Object;                                                                                 .this$0.sourceSpliterator.this$0.elementData [(object), (object), (object), null, null, null, null, null, null, null]
        713794d08         24 java.lang.String                                                                                    .this$0.sourceSpliterator.this$0.elementData[1] (object)
        713794d20         24 [B                                                                                                  .this$0.sourceSpliterator.this$0.elementData[1].value [72, 101, 105, 110, 122]
        713794d38         24 java.lang.String                                                                                    .this$0.sourceSpliterator.this$0.elementData[2] (object)
        713794d50         24 [B                                                                                                  .this$0.sourceSpliterator.this$0.elementData[2].value [77, 105, 99, 104, 97, 101, 108]
        713794d68      24760 (something else)                                                                                    (somewhere else)               (something else)
        71379ae20         32 java.util.ArrayList$ArrayListSpliterator                                                            .this$0.sourceSpliterator      (object)
        71379ae40         56 java.util.stream.ReferencePipeline$Head                                                             .this$0                        (object)
        71379ae78     195048 (something else)                                                                                    (somewhere else)               (something else)
        7137ca860         16 net.mirwaldt.empty.streams.demos.EmptyStream_02_GenericStreamFromList$$Lambda$99/0x0000000800c86800 .val$predicate                 (object)
        7137ca870        352 (something else)                                                                                    (somewhere else)               (something else)
        7137ca9d0         64 java.util.stream.ReferencePipeline$2                                                                                               (object)
        7137caa10    4555760 (something else)                                                                                    (somewhere else)               (something else)
        713c22e00         24 java.util.ArrayList                                                                                 .this$0.sourceSpliterator.this$0 (object)
        713c22e18    2020864 (something else)                                                                                    (somewhere else)               (something else)
        713e10418         24 java.lang.String                                                                                    .this$0.sourceSpliterator.this$0.elementData[0] (object)
        713e10430         16 [B                                                                                                  .this$0.sourceSpliterator.this$0.elementData[0].value []

Addresses are stable after 1 tries.


------------------------------------------------------------
eagerBuildFilteredMappedNonEmpty:
totalSize=464 bytes
totalCount=14 objects
java.util.stream.ReferencePipeline$3@1990a65ed object externals:
          ADDRESS       SIZE TYPE                                                                                                 PATH                           VALUE
        713401758         16 net.mirwaldt.empty.streams.demos.EmptyStream_02_GenericStreamFromList$$Lambda$100/0x0000000800c86a48 .val$mapper                    (object)
        713401768        352 (something else)                                                                                     (somewhere else)               (something else)
        7134018c8         64 java.util.stream.ReferencePipeline$3                                                                                                (object)
        713401908    3748808 (something else)                                                                                     (somewhere else)               (something else)
        713794cd0         56 [Ljava.lang.Object;                                                                                  .sourceStage.sourceSpliterator.this$0.elementData [(object), (object), (object), null, null, null, null, null, null, null]
        713794d08         24 java.lang.String                                                                                     .sourceStage.sourceSpliterator.this$0.elementData[1] (object)
        713794d20         24 [B                                                                                                   .sourceStage.sourceSpliterator.this$0.elementData[1].value [72, 101, 105, 110, 122]
        713794d38         24 java.lang.String                                                                                     .sourceStage.sourceSpliterator.this$0.elementData[2] (object)
        713794d50         24 [B                                                                                                   .sourceStage.sourceSpliterator.this$0.elementData[2].value [77, 105, 99, 104, 97, 101, 108]
        713794d68      24760 (something else)                                                                                     (somewhere else)               (something else)
        71379ae20         32 java.util.ArrayList$ArrayListSpliterator                                                             .sourceStage.sourceSpliterator (object)
        71379ae40         56 java.util.stream.ReferencePipeline$Head                                                              .sourceStage                   (object)
        71379ae78     195048 (something else)                                                                                     (somewhere else)               (something else)
        7137ca860         16 net.mirwaldt.empty.streams.demos.EmptyStream_02_GenericStreamFromList$$Lambda$99/0x0000000800c86800  .this$0.val$predicate          (object)
        7137ca870        352 (something else)                                                                                     (somewhere else)               (something else)
        7137ca9d0         64 java.util.stream.ReferencePipeline$2                                                                 .this$0                        (object)
        7137caa10    4555760 (something else)                                                                                     (somewhere else)               (something else)
        713c22e00         24 java.util.ArrayList                                                                                  .sourceStage.sourceSpliterator.this$0 (object)
        713c22e18    2020864 (something else)                                                                                     (somewhere else)               (something else)
        713e10418         24 java.lang.String                                                                                     .sourceStage.sourceSpliterator.this$0.elementData[0] (object)
        713e10430         16 [B                                                                                                   .sourceStage.sourceSpliterator.this$0.elementData[0].value []

Addresses are stable after 1 tries.


------------------------------------------------------------
eagerBuildFilteredMappedNonEmpty.findFirst().orElse("?")=HEINZ
++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
lazyBuildNonEmpty:
totalSize=272 bytes
totalCount=10 objects
net.mirwaldt.empty.streams.LazyBuildGenericStream@25bbf683d object externals:
          ADDRESS       SIZE TYPE                                              PATH                           VALUE
        713448738         32 java.util.ArrayList$ArrayListSpliterator          .spliterator                   (object)
        713448758         56 (something else)                                  (somewhere else)               (something else)
        713448790         24 net.mirwaldt.empty.streams.LazyBuildGenericStream                                (object)
        7134487a8    3458344 (something else)                                  (somewhere else)               (something else)
        713794cd0         56 [Ljava.lang.Object;                               .spliterator.this$0.elementData [(object), (object), (object), null, null, null, null, null, null, null]
        713794d08         24 java.lang.String                                  .spliterator.this$0.elementData[1] (object)
        713794d20         24 [B                                                .spliterator.this$0.elementData[1].value [72, 101, 105, 110, 122]
        713794d38         24 java.lang.String                                  .spliterator.this$0.elementData[2] (object)
        713794d50         24 [B                                                .spliterator.this$0.elementData[2].value [77, 105, 99, 104, 97, 101, 108]
        713794d68    4776088 (something else)                                  (somewhere else)               (something else)
        713c22e00         24 java.util.ArrayList                               .spliterator.this$0            (object)
        713c22e18    2020864 (something else)                                  (somewhere else)               (something else)
        713e10418         24 java.lang.String                                  .spliterator.this$0.elementData[0] (object)
        713e10430         16 [B                                                .spliterator.this$0.elementData[0].value []

Addresses are stable after 1 tries.


------------------------------------------------------------
lazyBuildFilteredNonEmpty:
totalSize=352 bytes
totalCount=14 objects
net.mirwaldt.empty.streams.LazyBuildGenericStream@544a2ea6d object externals:
          ADDRESS       SIZE TYPE                                                                                                 PATH                           VALUE
        713448738         32 java.util.ArrayList$ArrayListSpliterator                                                             .spliterator                   (object)
        713448758         56 (something else)                                                                                     (somewhere else)               (something else)
        713448790         24 net.mirwaldt.empty.streams.LazyBuildGenericStream                                                    .streamSupplier.arg$2          (object)
        7134487a8      97576 (something else)                                                                                     (somewhere else)               (something else)
        7134604d0         16 net.mirwaldt.empty.streams.demos.EmptyStream_02_GenericStreamFromList$$Lambda$101/0x0000000800c86c80 .streamSupplier.arg$1.arg$1    (object)
        7134604e0        352 (something else)                                                                                     (somewhere else)               (something else)
        713460640         16 net.mirwaldt.empty.streams.LazyBuildGenericStream$$Lambda$92/0x0000000800c1fdc0                      .streamSupplier.arg$1          (object)
        713460650         24 net.mirwaldt.empty.streams.util.LazyBuildGenericStreamUtil$$Lambda$93/0x0000000800c87420             .streamSupplier                (object)
        713460668         24 net.mirwaldt.empty.streams.LazyBuildGenericStream                                                                                   (object)
        713460680    3360336 (something else)                                                                                     (somewhere else)               (something else)
        713794cd0         56 [Ljava.lang.Object;                                                                                  .spliterator.this$0.elementData [(object), (object), (object), null, null, null, null, null, null, null]
        713794d08         24 java.lang.String                                                                                     .spliterator.this$0.elementData[1] (object)
        713794d20         24 [B                                                                                                   .spliterator.this$0.elementData[1].value [72, 101, 105, 110, 122]
        713794d38         24 java.lang.String                                                                                     .spliterator.this$0.elementData[2] (object)
        713794d50         24 [B                                                                                                   .spliterator.this$0.elementData[2].value [77, 105, 99, 104, 97, 101, 108]
        713794d68    4776088 (something else)                                                                                     (somewhere else)               (something else)
        713c22e00         24 java.util.ArrayList                                                                                  .spliterator.this$0            (object)
        713c22e18    2020864 (something else)                                                                                     (somewhere else)               (something else)
        713e10418         24 java.lang.String                                                                                     .spliterator.this$0.elementData[0] (object)
        713e10430         16 [B                                                                                                   .spliterator.this$0.elementData[0].value []

Addresses are stable after 1 tries.


------------------------------------------------------------
lazyBuildFilteredMappedNonEmpty:
totalSize=408 bytes
totalCount=17 objects
net.mirwaldt.empty.streams.LazyBuildGenericStream@10dba097d object externals:
          ADDRESS       SIZE TYPE                                                                                                 PATH                           VALUE
        713448738         32 java.util.ArrayList$ArrayListSpliterator                                                             .spliterator                   (object)
        713448758         56 (something else)                                                                                     (somewhere else)               (something else)
        713448790         24 net.mirwaldt.empty.streams.LazyBuildGenericStream                                                    .streamSupplier.arg$2.arg$2    (object)
        7134487a8      97576 (something else)                                                                                     (somewhere else)               (something else)
        7134604d0         16 net.mirwaldt.empty.streams.demos.EmptyStream_02_GenericStreamFromList$$Lambda$101/0x0000000800c86c80 .streamSupplier.arg$2.arg$1.arg$1 (object)
        7134604e0        352 (something else)                                                                                     (somewhere else)               (something else)
        713460640         16 net.mirwaldt.empty.streams.LazyBuildGenericStream$$Lambda$92/0x0000000800c1fdc0                      .streamSupplier.arg$2.arg$1    (object)
        713460650         24 net.mirwaldt.empty.streams.util.LazyBuildGenericStreamUtil$$Lambda$93/0x0000000800c87420             .streamSupplier.arg$2          (object)
        713460668     127312 (something else)                                                                                     (somewhere else)               (something else)
        71347f7b8         16 net.mirwaldt.empty.streams.demos.EmptyStream_02_GenericStreamFromList$$Lambda$102/0x0000000800c86400 .streamSupplier.arg$1.arg$1    (object)
        71347f7c8        352 (something else)                                                                                     (somewhere else)               (something else)
        71347f928         16 net.mirwaldt.empty.streams.LazyBuildGenericStream$$Lambda$95/0x0000000800c87878                      .streamSupplier.arg$1          (object)
        71347f938         24 net.mirwaldt.empty.streams.util.LazyBuildGenericStreamUtil$$Lambda$93/0x0000000800c87420             .streamSupplier                (object)
        71347f950         24 net.mirwaldt.empty.streams.LazyBuildGenericStream                                                                                   (object)
        71347f968    3232616 (something else)                                                                                     (somewhere else)               (something else)
        713794cd0         56 [Ljava.lang.Object;                                                                                  .spliterator.this$0.elementData [(object), (object), (object), null, null, null, null, null, null, null]
        713794d08         24 java.lang.String                                                                                     .spliterator.this$0.elementData[1] (object)
        713794d20         24 [B                                                                                                   .spliterator.this$0.elementData[1].value [72, 101, 105, 110, 122]
        713794d38         24 java.lang.String                                                                                     .spliterator.this$0.elementData[2] (object)
        713794d50         24 [B                                                                                                   .spliterator.this$0.elementData[2].value [77, 105, 99, 104, 97, 101, 108]
        713794d68    4776088 (something else)                                                                                     (somewhere else)               (something else)
        713c22e00         24 java.util.ArrayList                                                                                  .spliterator.this$0            (object)
        713c22e18    2020864 (something else)                                                                                     (somewhere else)               (something else)
        713e10418         24 java.lang.String                                                                                     .spliterator.this$0.elementData[0] (object)
        713e10430         16 [B                                                                                                   .spliterator.this$0.elementData[0].value []

Addresses are stable after 1 tries.


------------------------------------------------------------
lazyBuildFilteredMappedNonEmpty.findFirst().orElse("?")=HEINZ
*/
}
