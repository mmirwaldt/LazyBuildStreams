package net.mirwaldt.empty.streams.demos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static net.mirwaldt.empty.streams.LazyBuildStreamFactory.lazyBuildGenericStream;
import static net.mirwaldt.empty.streams.demos.PrintUtil.*;

/**
 * Use with VM option -Djol.magicFieldOffset=true
 */
public class EmptyStream_09_GenericStreamFromList_ParallelStream {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        printLineOfSharp(); // ############################################################

        // empty builds
        System.out.println("list=" + list);

        printLineOfSharp(); // ############################################################

        // eager build of empty
        Stream<String> eagerBuildEmpty = list.parallelStream();
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
        Stream<String> lazyBuildEmpty = lazyBuildGenericStream(list.parallelStream());
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
        Stream<String> eagerBuildNonEmpty = list.parallelStream();
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
        Stream<String> lazyBuildNonEmpty = lazyBuildGenericStream(list.parallelStream());
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
eagerBuildEmpty:
totalSize=128 bytes
totalCount=4 objects
java.util.stream.ReferencePipeline$Head@4f3f5b24d object externals:
          ADDRESS       SIZE TYPE                                     PATH                           VALUE
        713c22f00         24 java.util.ArrayList                      .sourceSpliterator.this$0      (object)
        713c22f18      35800 (something else)                         (somewhere else)               (something else)
        713c2baf0         32 java.util.ArrayList$ArrayListSpliterator .sourceSpliterator             (object)
        713c2bb10         56 java.util.stream.ReferencePipeline$Head                                 (object)
        713c2bb48    2080696 (something else)                         (somewhere else)               (something else)
        713e27b00         16 [Ljava.lang.Object;                      .sourceSpliterator.this$0.elementData []

Addresses are stable after 1 tries.


------------------------------------------------------------
eagerBuildFilteredEmpty:
totalSize=208 bytes
totalCount=6 objects
java.util.stream.ReferencePipeline$2@612fc6ebd object externals:
          ADDRESS       SIZE TYPE                                                                                                               PATH                           VALUE
        7136982c0         16 net.mirwaldt.empty.streams.demos.EmptyStream_09_GenericStreamFromList_ParallelStream$$Lambda$89/0x0000000800cc6620 .val$predicate                 (object)
        7136982d0        584 (something else)                                                                                                   (somewhere else)               (something else)
        713698518         64 java.util.stream.ReferencePipeline$2                                                                                                              (object)
        713698558    5810600 (something else)                                                                                                   (somewhere else)               (something else)
        713c22f00         24 java.util.ArrayList                                                                                                .this$0.sourceSpliterator.this$0 (object)
        713c22f18      35800 (something else)                                                                                                   (somewhere else)               (something else)
        713c2baf0         32 java.util.ArrayList$ArrayListSpliterator                                                                           .this$0.sourceSpliterator      (object)
        713c2bb10         56 java.util.stream.ReferencePipeline$Head                                                                            .this$0                        (object)
        713c2bb48    2080696 (something else)                                                                                                   (somewhere else)               (something else)
        713e27b00         16 [Ljava.lang.Object;                                                                                                .this$0.sourceSpliterator.this$0.elementData []

Addresses are stable after 1 tries.


------------------------------------------------------------
eagerBuildFilteredMappedEmpty:
totalSize=288 bytes
totalCount=8 objects
java.util.stream.ReferencePipeline$3@491cc5c9d object externals:
          ADDRESS       SIZE TYPE                                                                                                               PATH                           VALUE
        7136982c0         16 net.mirwaldt.empty.streams.demos.EmptyStream_09_GenericStreamFromList_ParallelStream$$Lambda$89/0x0000000800cc6620 .this$0.val$predicate          (object)
        7136982d0        584 (something else)                                                                                                   (somewhere else)               (something else)
        713698518         64 java.util.stream.ReferencePipeline$2                                                                               .this$0                        (object)
        713698558     215784 (something else)                                                                                                   (somewhere else)               (something else)
        7136cd040         16 net.mirwaldt.empty.streams.demos.EmptyStream_09_GenericStreamFromList_ParallelStream$$Lambda$90/0x0000000800cc6868 .val$mapper                    (object)
        7136cd050        352 (something else)                                                                                                   (somewhere else)               (something else)
        7136cd1b0         64 java.util.stream.ReferencePipeline$3                                                                                                              (object)
        7136cd1f0    5594384 (something else)                                                                                                   (somewhere else)               (something else)
        713c22f00         24 java.util.ArrayList                                                                                                .sourceStage.sourceSpliterator.this$0 (object)
        713c22f18      35800 (something else)                                                                                                   (somewhere else)               (something else)
        713c2baf0         32 java.util.ArrayList$ArrayListSpliterator                                                                           .sourceStage.sourceSpliterator (object)
        713c2bb10         56 java.util.stream.ReferencePipeline$Head                                                                            .sourceStage                   (object)
        713c2bb48    2080696 (something else)                                                                                                   (somewhere else)               (something else)
        713e27b00         16 [Ljava.lang.Object;                                                                                                .sourceStage.sourceSpliterator.this$0.elementData []

Addresses are stable after 1 tries.


------------------------------------------------------------
eagerBuildFilteredMappedEmpty.findFirst().orElse("?")=?
++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
lazyBuildEmpty:
totalSize=96 bytes
totalCount=4 objects
net.mirwaldt.empty.streams.LazyBuildGenericStream@2db7a79bd object externals:
          ADDRESS       SIZE TYPE                                              PATH                           VALUE
        713714760         32 java.util.ArrayList$ArrayListSpliterator          .spliterator                   (object)
        713714780      78312 (something else)                                  (somewhere else)               (something else)
        713727968         24 net.mirwaldt.empty.streams.LazyBuildGenericStream                                (object)
        713727980    5223808 (something else)                                  (somewhere else)               (something else)
        713c22f00         24 java.util.ArrayList                               .spliterator.this$0            (object)
        713c22f18    2116584 (something else)                                  (somewhere else)               (something else)
        713e27b00         16 [Ljava.lang.Object;                               .spliterator.this$0.elementData []

Addresses are stable after 1 tries.


------------------------------------------------------------
lazyBuildFilteredEmpty:
totalSize=176 bytes
totalCount=8 objects
net.mirwaldt.empty.streams.LazyBuildGenericStream@569cfc36d object externals:
          ADDRESS       SIZE TYPE                                                                                                               PATH                           VALUE
        713714760         32 java.util.ArrayList$ArrayListSpliterator                                                                           .spliterator                   (object)
        713714780      78312 (something else)                                                                                                   (somewhere else)               (something else)
        713727968         24 net.mirwaldt.empty.streams.LazyBuildGenericStream                                                                  .streamSupplier.arg$2          (object)
        713727980      55032 (something else)                                                                                                   (somewhere else)               (something else)
        713735078         16 net.mirwaldt.empty.streams.demos.EmptyStream_09_GenericStreamFromList_ParallelStream$$Lambda$91/0x0000000800cc7570 .streamSupplier.arg$1.arg$1    (object)
        713735088      14120 (something else)                                                                                                   (somewhere else)               (something else)
        7137387b0         16 net.mirwaldt.empty.streams.LazyBuildGenericStream$$Lambda$92/0x0000000800cc77b8                                    .streamSupplier.arg$1          (object)
        7137387c0      36744 (something else)                                                                                                   (somewhere else)               (something else)
        713741748         24 net.mirwaldt.empty.streams.util.LazyBuildGenericStreamUtil$$Lambda$93/0x0000000800ccc000                           .streamSupplier                (object)
        713741760         24 net.mirwaldt.empty.streams.LazyBuildGenericStream                                                                                                 (object)
        713741778    5117832 (something else)                                                                                                   (somewhere else)               (something else)
        713c22f00         24 java.util.ArrayList                                                                                                .spliterator.this$0            (object)
        713c22f18    2116584 (something else)                                                                                                   (somewhere else)               (something else)
        713e27b00         16 [Ljava.lang.Object;                                                                                                .spliterator.this$0.elementData []

Addresses are stable after 1 tries.


------------------------------------------------------------
lazyBuildFilteredMappedEmpty:
totalSize=232 bytes
totalCount=11 objects
net.mirwaldt.empty.streams.LazyBuildGenericStream@1d371b2dd object externals:
          ADDRESS       SIZE TYPE                                                                                                               PATH                           VALUE
        713714760         32 java.util.ArrayList$ArrayListSpliterator                                                                           .spliterator                   (object)
        713714780      78312 (something else)                                                                                                   (somewhere else)               (something else)
        713727968         24 net.mirwaldt.empty.streams.LazyBuildGenericStream                                                                  .streamSupplier.arg$2.arg$2    (object)
        713727980      55032 (something else)                                                                                                   (somewhere else)               (something else)
        713735078         16 net.mirwaldt.empty.streams.demos.EmptyStream_09_GenericStreamFromList_ParallelStream$$Lambda$91/0x0000000800cc7570 .streamSupplier.arg$2.arg$1.arg$1 (object)
        713735088      14120 (something else)                                                                                                   (somewhere else)               (something else)
        7137387b0         16 net.mirwaldt.empty.streams.LazyBuildGenericStream$$Lambda$92/0x0000000800cc77b8                                    .streamSupplier.arg$2.arg$1    (object)
        7137387c0      36744 (something else)                                                                                                   (somewhere else)               (something else)
        713741748         24 net.mirwaldt.empty.streams.util.LazyBuildGenericStreamUtil$$Lambda$93/0x0000000800ccc000                           .streamSupplier.arg$2          (object)
        713741760     238288 (something else)                                                                                                   (somewhere else)               (something else)
        71377ba30         16 net.mirwaldt.empty.streams.demos.EmptyStream_09_GenericStreamFromList_ParallelStream$$Lambda$94/0x0000000800ccc220 .streamSupplier.arg$1.arg$1    (object)
        71377ba40      13288 (something else)                                                                                                   (somewhere else)               (something else)
        71377ee28         16 net.mirwaldt.empty.streams.LazyBuildGenericStream$$Lambda$95/0x0000000800ccc458                                    .streamSupplier.arg$1          (object)
        71377ee38         24 net.mirwaldt.empty.streams.util.LazyBuildGenericStreamUtil$$Lambda$93/0x0000000800ccc000                           .streamSupplier                (object)
        71377ee50         24 net.mirwaldt.empty.streams.LazyBuildGenericStream                                                                                                 (object)
        71377ee68    4866200 (something else)                                                                                                   (somewhere else)               (something else)
        713c22f00         24 java.util.ArrayList                                                                                                .spliterator.this$0            (object)
        713c22f18    2116584 (something else)                                                                                                   (somewhere else)               (something else)
        713e27b00         16 [Ljava.lang.Object;                                                                                                .spliterator.this$0.elementData []

Addresses are stable after 1 tries.


------------------------------------------------------------
lazyBuildFilteredMappedEmpty.findFirst().orElse("?")=?
############################################################
list=["", "Heinz", "Michael"]
############################################################
eagerBuildNonEmpty:
totalSize=304 bytes
totalCount=10 objects
java.util.stream.ReferencePipeline$Head@3891771ed object externals:
          ADDRESS       SIZE TYPE                                     PATH                           VALUE
        7137993c8         56 [Ljava.lang.Object;                      .sourceSpliterator.this$0.elementData [(object), (object), (object), null, null, null, null, null, null, null]
        713799400         24 java.lang.String                         .sourceSpliterator.this$0.elementData[1] (object)
        713799418         24 [B                                       .sourceSpliterator.this$0.elementData[1].value [72, 101, 105, 110, 122]
        713799430         24 java.lang.String                         .sourceSpliterator.this$0.elementData[2] (object)
        713799448         24 [B                                       .sourceSpliterator.this$0.elementData[2].value [77, 105, 99, 104, 97, 101, 108]
        713799460      25040 (something else)                         (somewhere else)               (something else)
        71379f630         32 java.util.ArrayList$ArrayListSpliterator .sourceSpliterator             (object)
        71379f650         56 java.util.stream.ReferencePipeline$Head                                 (object)
        71379f688    4733048 (something else)                         (somewhere else)               (something else)
        713c22f00         24 java.util.ArrayList                      .sourceSpliterator.this$0      (object)
        713c22f18    2020624 (something else)                         (somewhere else)               (something else)
        713e10428         24 java.lang.String                         .sourceSpliterator.this$0.elementData[0] (object)
        713e10440         16 [B                                       .sourceSpliterator.this$0.elementData[0].value []

Addresses are stable after 1 tries.


------------------------------------------------------------
eagerBuildFilteredNonEmpty:
totalSize=384 bytes
totalCount=12 objects
java.util.stream.ReferencePipeline$2@64485a47d object externals:
          ADDRESS       SIZE TYPE                                                                                                               PATH                           VALUE
        7137993c8         56 [Ljava.lang.Object;                                                                                                .this$0.sourceSpliterator.this$0.elementData [(object), (object), (object), null, null, null, null, null, null, null]
        713799400         24 java.lang.String                                                                                                   .this$0.sourceSpliterator.this$0.elementData[1] (object)
        713799418         24 [B                                                                                                                 .this$0.sourceSpliterator.this$0.elementData[1].value [72, 101, 105, 110, 122]
        713799430         24 java.lang.String                                                                                                   .this$0.sourceSpliterator.this$0.elementData[2] (object)
        713799448         24 [B                                                                                                                 .this$0.sourceSpliterator.this$0.elementData[2].value [77, 105, 99, 104, 97, 101, 108]
        713799460      25040 (something else)                                                                                                   (somewhere else)               (something else)
        71379f630         32 java.util.ArrayList$ArrayListSpliterator                                                                           .this$0.sourceSpliterator      (object)
        71379f650         56 java.util.stream.ReferencePipeline$Head                                                                            .this$0                        (object)
        71379f688     195280 (something else)                                                                                                   (somewhere else)               (something else)
        7137cf158         16 net.mirwaldt.empty.streams.demos.EmptyStream_09_GenericStreamFromList_ParallelStream$$Lambda$99/0x0000000800cccaf0 .val$predicate                 (object)
        7137cf168        352 (something else)                                                                                                   (somewhere else)               (something else)
        7137cf2c8         64 java.util.stream.ReferencePipeline$2                                                                                                              (object)
        7137cf308    4537336 (something else)                                                                                                   (somewhere else)               (something else)
        713c22f00         24 java.util.ArrayList                                                                                                .this$0.sourceSpliterator.this$0 (object)
        713c22f18    2020624 (something else)                                                                                                   (somewhere else)               (something else)
        713e10428         24 java.lang.String                                                                                                   .this$0.sourceSpliterator.this$0.elementData[0] (object)
        713e10440         16 [B                                                                                                                 .this$0.sourceSpliterator.this$0.elementData[0].value []

Addresses are stable after 1 tries.


------------------------------------------------------------
eagerBuildFilteredMappedNonEmpty:
totalSize=464 bytes
totalCount=14 objects
java.util.stream.ReferencePipeline$3@7276c8cdd object externals:
          ADDRESS       SIZE TYPE                                                                                                                PATH                           VALUE
        713406090         16 net.mirwaldt.empty.streams.demos.EmptyStream_09_GenericStreamFromList_ParallelStream$$Lambda$100/0x0000000800cccd38 .val$mapper                    (object)
        7134060a0        352 (something else)                                                                                                    (somewhere else)               (something else)
        713406200         64 java.util.stream.ReferencePipeline$3                                                                                                               (object)
        713406240    3748232 (something else)                                                                                                    (somewhere else)               (something else)
        7137993c8         56 [Ljava.lang.Object;                                                                                                 .sourceStage.sourceSpliterator.this$0.elementData [(object), (object), (object), null, null, null, null, null, null, null]
        713799400         24 java.lang.String                                                                                                    .sourceStage.sourceSpliterator.this$0.elementData[1] (object)
        713799418         24 [B                                                                                                                  .sourceStage.sourceSpliterator.this$0.elementData[1].value [72, 101, 105, 110, 122]
        713799430         24 java.lang.String                                                                                                    .sourceStage.sourceSpliterator.this$0.elementData[2] (object)
        713799448         24 [B                                                                                                                  .sourceStage.sourceSpliterator.this$0.elementData[2].value [77, 105, 99, 104, 97, 101, 108]
        713799460      25040 (something else)                                                                                                    (somewhere else)               (something else)
        71379f630         32 java.util.ArrayList$ArrayListSpliterator                                                                            .sourceStage.sourceSpliterator (object)
        71379f650         56 java.util.stream.ReferencePipeline$Head                                                                             .sourceStage                   (object)
        71379f688     195280 (something else)                                                                                                    (somewhere else)               (something else)
        7137cf158         16 net.mirwaldt.empty.streams.demos.EmptyStream_09_GenericStreamFromList_ParallelStream$$Lambda$99/0x0000000800cccaf0  .this$0.val$predicate          (object)
        7137cf168        352 (something else)                                                                                                    (somewhere else)               (something else)
        7137cf2c8         64 java.util.stream.ReferencePipeline$2                                                                                .this$0                        (object)
        7137cf308    4537336 (something else)                                                                                                    (somewhere else)               (something else)
        713c22f00         24 java.util.ArrayList                                                                                                 .sourceStage.sourceSpliterator.this$0 (object)
        713c22f18    2020624 (something else)                                                                                                    (somewhere else)               (something else)
        713e10428         24 java.lang.String                                                                                                    .sourceStage.sourceSpliterator.this$0.elementData[0] (object)
        713e10440         16 [B                                                                                                                  .sourceStage.sourceSpliterator.this$0.elementData[0].value []

Addresses are stable after 1 tries.


------------------------------------------------------------
eagerBuildFilteredMappedNonEmpty.findFirst().orElse("?")=HEINZ
++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
lazyBuildNonEmpty:
totalSize=272 bytes
totalCount=10 objects
net.mirwaldt.empty.streams.LazyBuildGenericStream@10dba097d object externals:
          ADDRESS       SIZE TYPE                                              PATH                           VALUE
        7134529d0         32 java.util.ArrayList$ArrayListSpliterator          .spliterator                   (object)
        7134529f0         56 (something else)                                  (somewhere else)               (something else)
        713452a28         24 net.mirwaldt.empty.streams.LazyBuildGenericStream                                (object)
        713452a40    3434888 (something else)                                  (somewhere else)               (something else)
        7137993c8         56 [Ljava.lang.Object;                               .spliterator.this$0.elementData [(object), (object), (object), null, null, null, null, null, null, null]
        713799400         24 java.lang.String                                  .spliterator.this$0.elementData[1] (object)
        713799418         24 [B                                                .spliterator.this$0.elementData[1].value [72, 101, 105, 110, 122]
        713799430         24 java.lang.String                                  .spliterator.this$0.elementData[2] (object)
        713799448         24 [B                                                .spliterator.this$0.elementData[2].value [77, 105, 99, 104, 97, 101, 108]
        713799460    4758176 (something else)                                  (somewhere else)               (something else)
        713c22f00         24 java.util.ArrayList                               .spliterator.this$0            (object)
        713c22f18    2020624 (something else)                                  (somewhere else)               (something else)
        713e10428         24 java.lang.String                                  .spliterator.this$0.elementData[0] (object)
        713e10440         16 [B                                                .spliterator.this$0.elementData[0].value []

Addresses are stable after 1 tries.


------------------------------------------------------------
lazyBuildFilteredNonEmpty:
totalSize=352 bytes
totalCount=14 objects
net.mirwaldt.empty.streams.LazyBuildGenericStream@43a0cee9d object externals:
          ADDRESS       SIZE TYPE                                                                                                                PATH                           VALUE
        7134529d0         32 java.util.ArrayList$ArrayListSpliterator                                                                            .spliterator                   (object)
        7134529f0         56 (something else)                                                                                                    (somewhere else)               (something else)
        713452a28         24 net.mirwaldt.empty.streams.LazyBuildGenericStream                                                                   .streamSupplier.arg$2          (object)
        713452a40      97840 (something else)                                                                                                    (somewhere else)               (something else)
        71346a870         16 net.mirwaldt.empty.streams.demos.EmptyStream_09_GenericStreamFromList_ParallelStream$$Lambda$101/0x0000000800cccf70 .streamSupplier.arg$1.arg$1    (object)
        71346a880        352 (something else)                                                                                                    (somewhere else)               (something else)
        71346a9e0         16 net.mirwaldt.empty.streams.LazyBuildGenericStream$$Lambda$92/0x0000000800cc77b8                                     .streamSupplier.arg$1          (object)
        71346a9f0         24 net.mirwaldt.empty.streams.util.LazyBuildGenericStreamUtil$$Lambda$93/0x0000000800ccc000                            .streamSupplier                (object)
        71346aa08         24 net.mirwaldt.empty.streams.LazyBuildGenericStream                                                                                                  (object)
        71346aa20    3336616 (something else)                                                                                                    (somewhere else)               (something else)
        7137993c8         56 [Ljava.lang.Object;                                                                                                 .spliterator.this$0.elementData [(object), (object), (object), null, null, null, null, null, null, null]
        713799400         24 java.lang.String                                                                                                    .spliterator.this$0.elementData[1] (object)
        713799418         24 [B                                                                                                                  .spliterator.this$0.elementData[1].value [72, 101, 105, 110, 122]
        713799430         24 java.lang.String                                                                                                    .spliterator.this$0.elementData[2] (object)
        713799448         24 [B                                                                                                                  .spliterator.this$0.elementData[2].value [77, 105, 99, 104, 97, 101, 108]
        713799460    4758176 (something else)                                                                                                    (somewhere else)               (something else)
        713c22f00         24 java.util.ArrayList                                                                                                 .spliterator.this$0            (object)
        713c22f18    2020624 (something else)                                                                                                    (somewhere else)               (something else)
        713e10428         24 java.lang.String                                                                                                    .spliterator.this$0.elementData[0] (object)
        713e10440         16 [B                                                                                                                  .spliterator.this$0.elementData[0].value []

Addresses are stable after 1 tries.


------------------------------------------------------------
lazyBuildFilteredMappedNonEmpty:
totalSize=408 bytes
totalCount=17 objects
net.mirwaldt.empty.streams.LazyBuildGenericStream@6eceb130d object externals:
          ADDRESS       SIZE TYPE                                                                                                                PATH                           VALUE
        7134529d0         32 java.util.ArrayList$ArrayListSpliterator                                                                            .spliterator                   (object)
        7134529f0         56 (something else)                                                                                                    (somewhere else)               (something else)
        713452a28         24 net.mirwaldt.empty.streams.LazyBuildGenericStream                                                                   .streamSupplier.arg$2.arg$2    (object)
        713452a40      97840 (something else)                                                                                                    (somewhere else)               (something else)
        71346a870         16 net.mirwaldt.empty.streams.demos.EmptyStream_09_GenericStreamFromList_ParallelStream$$Lambda$101/0x0000000800cccf70 .streamSupplier.arg$2.arg$1.arg$1 (object)
        71346a880        352 (something else)                                                                                                    (somewhere else)               (something else)
        71346a9e0         16 net.mirwaldt.empty.streams.LazyBuildGenericStream$$Lambda$92/0x0000000800cc77b8                                     .streamSupplier.arg$2.arg$1    (object)
        71346a9f0         24 net.mirwaldt.empty.streams.util.LazyBuildGenericStreamUtil$$Lambda$93/0x0000000800ccc000                            .streamSupplier.arg$2          (object)
        71346aa08     590264 (something else)                                                                                                    (somewhere else)               (something else)
        7134fabc0         16 net.mirwaldt.empty.streams.demos.EmptyStream_09_GenericStreamFromList_ParallelStream$$Lambda$102/0x0000000800ccd1b8 .streamSupplier.arg$1.arg$1    (object)
        7134fabd0        352 (something else)                                                                                                    (somewhere else)               (something else)
        7134fad30         16 net.mirwaldt.empty.streams.LazyBuildGenericStream$$Lambda$95/0x0000000800ccc458                                     .streamSupplier.arg$1          (object)
        7134fad40         24 net.mirwaldt.empty.streams.util.LazyBuildGenericStreamUtil$$Lambda$93/0x0000000800ccc000                            .streamSupplier                (object)
        7134fad58         24 net.mirwaldt.empty.streams.LazyBuildGenericStream                                                                                                  (object)
        7134fad70    2745944 (something else)                                                                                                    (somewhere else)               (something else)
        7137993c8         56 [Ljava.lang.Object;                                                                                                 .spliterator.this$0.elementData [(object), (object), (object), null, null, null, null, null, null, null]
        713799400         24 java.lang.String                                                                                                    .spliterator.this$0.elementData[1] (object)
        713799418         24 [B                                                                                                                  .spliterator.this$0.elementData[1].value [72, 101, 105, 110, 122]
        713799430         24 java.lang.String                                                                                                    .spliterator.this$0.elementData[2] (object)
        713799448         24 [B                                                                                                                  .spliterator.this$0.elementData[2].value [77, 105, 99, 104, 97, 101, 108]
        713799460    4758176 (something else)                                                                                                    (somewhere else)               (something else)
        713c22f00         24 java.util.ArrayList                                                                                                 .spliterator.this$0            (object)
        713c22f18    2020624 (something else)                                                                                                    (somewhere else)               (something else)
        713e10428         24 java.lang.String                                                                                                    .spliterator.this$0.elementData[0] (object)
        713e10440         16 [B                                                                                                                  .spliterator.this$0.elementData[0].value []

Addresses are stable after 1 tries.


------------------------------------------------------------
lazyBuildFilteredMappedNonEmpty.findFirst().orElse("?")=HEINZ
*/
}
