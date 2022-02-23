package net.mirwaldt.empty.streams.demos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static net.mirwaldt.empty.streams.LazyBuildStreamFactory.lazyBuildGenericStream;
import static net.mirwaldt.empty.streams.LazyBuildStreamFactory.lazyBuildIntStream;
import static net.mirwaldt.empty.streams.demos.PrintUtil.*;

/**
 * Use with VM option -Djol.magicFieldOffset=true
 */
public class EmptyStream_04_IntStreamFromIntArray {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();

        printLineOfSharp(); // ############################################################

        // empty builds
        System.out.println("list=" + list);

        printLineOfSharp(); // ############################################################

        // eager build of empty
        IntStream eagerBuildEmpty = list.stream().mapToInt(Integer::valueOf);
        printStatistics("eagerBuildEmpty:", eagerBuildEmpty);

        printLineOfMinus(); // ------------------------------------------------------------

        IntStream eagerBuildFilteredEmpty = eagerBuildEmpty.filter(i -> 0 < i);
        printStatistics("eagerBuildFilteredEmpty:", eagerBuildFilteredEmpty);

        printLineOfMinus(); // ------------------------------------------------------------

        IntStream eagerBuildFilteredMappedEmpty = eagerBuildFilteredEmpty.map(i -> 2 * i);
        printStatistics("eagerBuildFilteredMappedEmpty:", eagerBuildFilteredMappedEmpty);

        printLineOfMinus(); // ------------------------------------------------------------

        System.out.println("eagerBuildFilteredMappedEmpty.findFirst().orElse(-1)="
                + eagerBuildFilteredMappedEmpty.findFirst().orElse(-1));


        printLineOfPlus();  // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


        // lazy build of empty
        IntStream lazyBuildEmpty = lazyBuildIntStream(lazyBuildGenericStream(list.stream()).mapToInt(Integer::valueOf));
        printStatistics("lazyBuildEmpty:", lazyBuildEmpty);

        printLineOfMinus(); // ------------------------------------------------------------

        IntStream lazyBuildFilteredEmpty = lazyBuildEmpty.filter(i -> 0 < i);
        printStatistics("lazyBuildFilteredEmpty:", lazyBuildFilteredEmpty);

        printLineOfMinus(); // ------------------------------------------------------------

        IntStream lazyBuildFilteredMappedEmpty = lazyBuildFilteredEmpty.map(i -> 2 * i);
        printStatistics("lazyBuildFilteredMappedEmpty:", lazyBuildFilteredMappedEmpty);

        printLineOfMinus(); // ------------------------------------------------------------

        System.out.println("lazyBuildFilteredMappedEmpty.findFirst().orElse(-1)="
                + lazyBuildFilteredMappedEmpty.findFirst().orElse(-1));



        printLineOfSharp(); // ############################################################

        // non-empty builds
        list.add(-3);
        list.add(2);
        list.add(5);
        System.out.println("list=" + list.stream().map(s -> "\"" + s +"\"").toList());

        printLineOfSharp(); // ############################################################

        // eager build of empty
        IntStream eagerBuildNonEmpty = list.stream().mapToInt(Integer::valueOf);
        printStatistics("eagerBuildNonEmpty:", eagerBuildNonEmpty);

        printLineOfMinus(); // ------------------------------------------------------------

        IntStream eagerBuildFilteredNonEmpty = eagerBuildNonEmpty.filter(i -> 0 < i);
        printStatistics("eagerBuildFilteredNonEmpty:", eagerBuildFilteredNonEmpty);

        printLineOfMinus(); // ------------------------------------------------------------

        IntStream eagerBuildFilteredMappedNonEmpty = eagerBuildFilteredNonEmpty.map(i -> 2 * i);
        printStatistics("eagerBuildFilteredMappedNonEmpty:", eagerBuildFilteredMappedNonEmpty);

        printLineOfMinus(); // ------------------------------------------------------------

        System.out.println("eagerBuildFilteredMappedNonEmpty.findFirst().orElse(\"?\")="
                + eagerBuildFilteredMappedNonEmpty.findFirst().orElse(-1));


        printLineOfPlus();  // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


        // lazy build of empty
        IntStream lazyBuildNonEmpty = lazyBuildIntStream(lazyBuildGenericStream(list.stream()).mapToInt(Integer::valueOf));
        printStatistics("lazyBuildNonEmpty:", lazyBuildNonEmpty);

        printLineOfMinus(); // ------------------------------------------------------------

        IntStream lazyBuildFilteredNonEmpty = lazyBuildNonEmpty.filter(i -> 0 < i);
        printStatistics("lazyBuildFilteredNonEmpty:", lazyBuildFilteredNonEmpty);

        printLineOfMinus(); // ------------------------------------------------------------

        IntStream lazyBuildFilteredMappedNonEmpty = lazyBuildFilteredNonEmpty.map(i -> 2 * i);
        printStatistics("lazyBuildFilteredMappedNonEmpty:", lazyBuildFilteredMappedNonEmpty);

        printLineOfMinus(); // ------------------------------------------------------------

        System.out.println("lazyBuildFilteredMappedNonEmpty.findFirst().orElse(-1)="
                + lazyBuildFilteredMappedNonEmpty.findFirst().orElse(-1));
    }
// Output:
/*
############################################################
list=[]
############################################################
# WARNING: Unable to get Instrumentation. Dynamic Attach failed. You may add this JAR as -javaagent manually, or supply -Djdk.attach.allowAttachSelf
# WARNING: Unable to attach Serviceability Agent. sun.jvm.hotspot.memory.Universe.getNarrowOopBase()
eagerBuildEmpty:
totalSize=208 bytes
totalCount=6 objects
java.util.stream.ReferencePipeline$4@5f184fc6d object externals:
          ADDRESS       SIZE TYPE                                                                                                PATH                           VALUE
        713c230f8         24 java.util.ArrayList                                                                                 .this$0.sourceSpliterator.this$0 (object)
        713c23110      35800 (something else)                                                                                    (somewhere else)               (something else)
        713c2bce8         32 java.util.ArrayList$ArrayListSpliterator                                                            .this$0.sourceSpliterator      (object)
        713c2bd08         56 java.util.stream.ReferencePipeline$Head                                                             .this$0                        (object)
        713c2bd40      14560 (something else)                                                                                    (somewhere else)               (something else)
        713c2f620         16 net.mirwaldt.empty.streams.demos.EmptyStream_04_IntStreamFromIntArray$$Lambda$15/0x0000000800c01c00 .val$mapper                    (object)
        713c2f630       1520 (something else)                                                                                    (somewhere else)               (something else)
        713c2fc20         64 java.util.stream.ReferencePipeline$4                                                                                               (object)
        713c2fc60    2064016 (something else)                                                                                    (somewhere else)               (something else)
        713e27af0         16 [Ljava.lang.Object;                                                                                 .this$0.sourceSpliterator.this$0.elementData []

Addresses are stable after 1 tries.


------------------------------------------------------------
eagerBuildFilteredEmpty:
totalSize=288 bytes
totalCount=8 objects
java.util.stream.IntPipeline$10@6a1aab78d object externals:
          ADDRESS       SIZE TYPE                                                                                                PATH                           VALUE
        7136b9028         16 net.mirwaldt.empty.streams.demos.EmptyStream_04_IntStreamFromIntArray$$Lambda$90/0x0000000800cc6620 .val$predicate                 (object)
        7136b9038        600 (something else)                                                                                    (somewhere else)               (something else)
        7136b9290         64 java.util.stream.IntPipeline$10                                                                                                    (object)
        7136b92d0    5676584 (something else)                                                                                    (somewhere else)               (something else)
        713c230f8         24 java.util.ArrayList                                                                                 .sourceStage.sourceSpliterator.this$0 (object)
        713c23110      35800 (something else)                                                                                    (somewhere else)               (something else)
        713c2bce8         32 java.util.ArrayList$ArrayListSpliterator                                                            .sourceStage.sourceSpliterator (object)
        713c2bd08         56 java.util.stream.ReferencePipeline$Head                                                             .sourceStage                   (object)
        713c2bd40      14560 (something else)                                                                                    (somewhere else)               (something else)
        713c2f620         16 net.mirwaldt.empty.streams.demos.EmptyStream_04_IntStreamFromIntArray$$Lambda$15/0x0000000800c01c00 .this$0.val$mapper             (object)
        713c2f630       1520 (something else)                                                                                    (somewhere else)               (something else)
        713c2fc20         64 java.util.stream.ReferencePipeline$4                                                                .this$0                        (object)
        713c2fc60    2064016 (something else)                                                                                    (somewhere else)               (something else)
        713e27af0         16 [Ljava.lang.Object;                                                                                 .sourceStage.sourceSpliterator.this$0.elementData []

Addresses are stable after 1 tries.


------------------------------------------------------------
eagerBuildFilteredMappedEmpty:
totalSize=368 bytes
totalCount=10 objects
java.util.stream.IntPipeline$4@8646db9d object externals:
          ADDRESS       SIZE TYPE                                                                                                PATH                           VALUE
        7136b9028         16 net.mirwaldt.empty.streams.demos.EmptyStream_04_IntStreamFromIntArray$$Lambda$90/0x0000000800cc6620 .this$0.val$predicate          (object)
        7136b9038        600 (something else)                                                                                    (somewhere else)               (something else)
        7136b9290         64 java.util.stream.IntPipeline$10                                                                     .this$0                        (object)
        7136b92d0     287304 (something else)                                                                                    (somewhere else)               (something else)
        7136ff518         16 net.mirwaldt.empty.streams.demos.EmptyStream_04_IntStreamFromIntArray$$Lambda$91/0x0000000800cc6868 .val$mapper                    (object)
        7136ff528        608 (something else)                                                                                    (somewhere else)               (something else)
        7136ff788         64 java.util.stream.IntPipeline$4                                                                                                     (object)
        7136ff7c8    5388592 (something else)                                                                                    (somewhere else)               (something else)
        713c230f8         24 java.util.ArrayList                                                                                 .sourceStage.sourceSpliterator.this$0 (object)
        713c23110      35800 (something else)                                                                                    (somewhere else)               (something else)
        713c2bce8         32 java.util.ArrayList$ArrayListSpliterator                                                            .sourceStage.sourceSpliterator (object)
        713c2bd08         56 java.util.stream.ReferencePipeline$Head                                                             .sourceStage                   (object)
        713c2bd40      14560 (something else)                                                                                    (somewhere else)               (something else)
        713c2f620         16 net.mirwaldt.empty.streams.demos.EmptyStream_04_IntStreamFromIntArray$$Lambda$15/0x0000000800c01c00 .sourceStage.nextStage.val$mapper (object)
        713c2f630       1520 (something else)                                                                                    (somewhere else)               (something else)
        713c2fc20         64 java.util.stream.ReferencePipeline$4                                                                .sourceStage.nextStage         (object)
        713c2fc60    2064016 (something else)                                                                                    (somewhere else)               (something else)
        713e27af0         16 [Ljava.lang.Object;                                                                                 .sourceStage.sourceSpliterator.this$0.elementData []

Addresses are stable after 1 tries.


------------------------------------------------------------
eagerBuildFilteredMappedEmpty.findFirst().orElse(-1)=-1
++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
lazyBuildEmpty:
totalSize=56 bytes
totalCount=3 objects
net.mirwaldt.empty.streams.LazyBuildIntStream@8e24743d object externals:
          ADDRESS       SIZE TYPE                                                     PATH                           VALUE
        71377ffc8         24 net.mirwaldt.empty.streams.LazyBuildIntStream                                           (object)
        71377ffe0      22056 (something else)                                         (somewhere else)               (something else)
        713785608         16 net.mirwaldt.empty.streams.util.LazyBuildIntStreamUtil$1 .streamSupplier                (object)
        713785618    7178400 (something else)                                         (somewhere else)               (something else)
        713e5deb8         16 java.util.Spliterators$EmptySpliterator$OfInt            .spliterator                   (object)

Addresses are stable after 1 tries.


------------------------------------------------------------
lazyBuildFilteredEmpty:
totalSize=56 bytes
totalCount=3 objects
net.mirwaldt.empty.streams.LazyBuildIntStream@51c8530fd object externals:
          ADDRESS       SIZE TYPE                                                                                 PATH                           VALUE
        713796e88         16 net.mirwaldt.empty.streams.util.LazyBuildIntStreamUtil$$Lambda$98/0x0000000800ccd108 .streamSupplier                (object)
        713796e98        352 (something else)                                                                     (somewhere else)               (something else)
        713796ff8         24 net.mirwaldt.empty.streams.LazyBuildIntStream                                                                       (object)
        713797010    7106216 (something else)                                                                     (somewhere else)               (something else)
        713e5deb8         16 java.util.Spliterators$EmptySpliterator$OfInt                                        .spliterator                   (object)

Addresses are stable after 1 tries.


------------------------------------------------------------
lazyBuildFilteredMappedEmpty:
totalSize=112 bytes
totalCount=6 objects
net.mirwaldt.empty.streams.LazyBuildIntStream@75881071d object externals:
          ADDRESS       SIZE TYPE                                                                                                PATH                           VALUE
        713796e88         16 net.mirwaldt.empty.streams.util.LazyBuildIntStreamUtil$$Lambda$98/0x0000000800ccd108                .streamSupplier.arg$2          (object)
        713796e98      32416 (something else)                                                                                    (somewhere else)               (something else)
        71379ed38         16 net.mirwaldt.empty.streams.demos.EmptyStream_04_IntStreamFromIntArray$$Lambda$99/0x0000000800ccd320 .streamSupplier.arg$1.arg$1    (object)
        71379ed48      13744 (something else)                                                                                    (somewhere else)               (something else)
        7137a22f8         16 net.mirwaldt.empty.streams.LazyBuildIntStream$$Lambda$100/0x0000000800ccd558                        .streamSupplier.arg$1          (object)
        7137a2308      12320 (something else)                                                                                    (somewhere else)               (something else)
        7137a5328         24 net.mirwaldt.empty.streams.util.LazyBuildIntStreamUtil$$Lambda$101/0x0000000800ccd798               .streamSupplier                (object)
        7137a5340         24 net.mirwaldt.empty.streams.LazyBuildIntStream                                                                                      (object)
        7137a5358    7048032 (something else)                                                                                    (somewhere else)               (something else)
        713e5deb8         16 java.util.Spliterators$EmptySpliterator$OfInt                                                       .spliterator                   (object)

Addresses are stable after 1 tries.


------------------------------------------------------------
lazyBuildFilteredMappedEmpty.findFirst().orElse(-1)=-1
############################################################
list=["-3", "2", "5"]
############################################################
eagerBuildNonEmpty:
totalSize=296 bytes
totalCount=9 objects
java.util.stream.ReferencePipeline$4@6ec8211cd object externals:
          ADDRESS       SIZE TYPE                                                                                                 PATH                           VALUE
        7137d6918         56 [Ljava.lang.Object;                                                                                  .this$0.sourceSpliterator.this$0.elementData [-3, 2, 5, null, null, null, null, null, null, null]
        7137d6950      25744 (something else)                                                                                     (somewhere else)               (something else)
        7137dcde0         32 java.util.ArrayList$ArrayListSpliterator                                                             .this$0.sourceSpliterator      (object)
        7137dce00         56 java.util.stream.ReferencePipeline$Head                                                              .this$0                        (object)
        7137dce38       9696 (something else)                                                                                     (somewhere else)               (something else)
        7137df418         16 net.mirwaldt.empty.streams.demos.EmptyStream_04_IntStreamFromIntArray$$Lambda$104/0x0000000800ccdbf0 .val$mapper                    (object)
        7137df428        352 (something else)                                                                                     (somewhere else)               (something else)
        7137df588         64 java.util.stream.ReferencePipeline$4                                                                                                (object)
        7137df5c8    4471600 (something else)                                                                                     (somewhere else)               (something else)
        713c230f8         24 java.util.ArrayList                                                                                  .this$0.sourceSpliterator.this$0 (object)
        713c23110    2028144 (something else)                                                                                     (somewhere else)               (something else)
        713e12380         16 java.lang.Integer                                                                                    .this$0.sourceSpliterator.this$0.elementData[0] -3
        713e12390         64 (something else)                                                                                     (somewhere else)               (something else)
        713e123d0         16 java.lang.Integer                                                                                    .this$0.sourceSpliterator.this$0.elementData[1] 2
        713e123e0         32 (something else)                                                                                     (somewhere else)               (something else)
        713e12400         16 java.lang.Integer                                                                                    .this$0.sourceSpliterator.this$0.elementData[2] 5

Addresses are stable after 1 tries.


------------------------------------------------------------
eagerBuildFilteredNonEmpty:
totalSize=376 bytes
totalCount=11 objects
java.util.stream.IntPipeline$10@3578436ed object externals:
          ADDRESS       SIZE TYPE                                                                                                 PATH                           VALUE
        71341afc8         16 net.mirwaldt.empty.streams.demos.EmptyStream_04_IntStreamFromIntArray$$Lambda$105/0x0000000800ccde08 .val$predicate                 (object)
        71341afd8        352 (something else)                                                                                     (somewhere else)               (something else)
        71341b138         64 java.util.stream.IntPipeline$10                                                                                                     (object)
        71341b178    3913632 (something else)                                                                                     (somewhere else)               (something else)
        7137d6918         56 [Ljava.lang.Object;                                                                                  .sourceStage.sourceSpliterator.this$0.elementData [-3, 2, 5, null, null, null, null, null, null, null]
        7137d6950      25744 (something else)                                                                                     (somewhere else)               (something else)
        7137dcde0         32 java.util.ArrayList$ArrayListSpliterator                                                             .sourceStage.sourceSpliterator (object)
        7137dce00         56 java.util.stream.ReferencePipeline$Head                                                              .sourceStage                   (object)
        7137dce38       9696 (something else)                                                                                     (somewhere else)               (something else)
        7137df418         16 net.mirwaldt.empty.streams.demos.EmptyStream_04_IntStreamFromIntArray$$Lambda$104/0x0000000800ccdbf0 .this$0.val$mapper             (object)
        7137df428        352 (something else)                                                                                     (somewhere else)               (something else)
        7137df588         64 java.util.stream.ReferencePipeline$4                                                                 .this$0                        (object)
        7137df5c8    4471600 (something else)                                                                                     (somewhere else)               (something else)
        713c230f8         24 java.util.ArrayList                                                                                  .sourceStage.sourceSpliterator.this$0 (object)
        713c23110    2028144 (something else)                                                                                     (somewhere else)               (something else)
        713e12380         16 java.lang.Integer                                                                                    .sourceStage.sourceSpliterator.this$0.elementData[0] -3
        713e12390         64 (something else)                                                                                     (somewhere else)               (something else)
        713e123d0         16 java.lang.Integer                                                                                    .sourceStage.sourceSpliterator.this$0.elementData[1] 2
        713e123e0         32 (something else)                                                                                     (somewhere else)               (something else)
        713e12400         16 java.lang.Integer                                                                                    .sourceStage.sourceSpliterator.this$0.elementData[2] 5

Addresses are stable after 1 tries.


------------------------------------------------------------
eagerBuildFilteredMappedNonEmpty:
totalSize=456 bytes
totalCount=13 objects
java.util.stream.IntPipeline$4@10a035a0d object externals:
          ADDRESS       SIZE TYPE                                                                                                 PATH                           VALUE
        71341afc8         16 net.mirwaldt.empty.streams.demos.EmptyStream_04_IntStreamFromIntArray$$Lambda$105/0x0000000800ccde08 .this$0.val$predicate          (object)
        71341afd8        352 (something else)                                                                                     (somewhere else)               (something else)
        71341b138         64 java.util.stream.IntPipeline$10                                                                      .this$0                        (object)
        71341b178     269080 (something else)                                                                                     (somewhere else)               (something else)
        71345cc90         16 net.mirwaldt.empty.streams.demos.EmptyStream_04_IntStreamFromIntArray$$Lambda$106/0x0000000800cce050 .val$mapper                    (object)
        71345cca0        352 (something else)                                                                                     (somewhere else)               (something else)
        71345ce00         64 java.util.stream.IntPipeline$4                                                                                                      (object)
        71345ce40    3644120 (something else)                                                                                     (somewhere else)               (something else)
        7137d6918         56 [Ljava.lang.Object;                                                                                  .sourceStage.sourceSpliterator.this$0.elementData [-3, 2, 5, null, null, null, null, null, null, null]
        7137d6950      25744 (something else)                                                                                     (somewhere else)               (something else)
        7137dcde0         32 java.util.ArrayList$ArrayListSpliterator                                                             .sourceStage.sourceSpliterator (object)
        7137dce00         56 java.util.stream.ReferencePipeline$Head                                                              .sourceStage                   (object)
        7137dce38       9696 (something else)                                                                                     (somewhere else)               (something else)
        7137df418         16 net.mirwaldt.empty.streams.demos.EmptyStream_04_IntStreamFromIntArray$$Lambda$104/0x0000000800ccdbf0 .sourceStage.nextStage.val$mapper (object)
        7137df428        352 (something else)                                                                                     (somewhere else)               (something else)
        7137df588         64 java.util.stream.ReferencePipeline$4                                                                 .sourceStage.nextStage         (object)
        7137df5c8    4471600 (something else)                                                                                     (somewhere else)               (something else)
        713c230f8         24 java.util.ArrayList                                                                                  .sourceStage.sourceSpliterator.this$0 (object)
        713c23110    2028144 (something else)                                                                                     (somewhere else)               (something else)
        713e12380         16 java.lang.Integer                                                                                    .sourceStage.sourceSpliterator.this$0.elementData[0] -3
        713e12390         64 (something else)                                                                                     (somewhere else)               (something else)
        713e123d0         16 java.lang.Integer                                                                                    .sourceStage.sourceSpliterator.this$0.elementData[1] 2
        713e123e0         32 (something else)                                                                                     (somewhere else)               (something else)
        713e12400         16 java.lang.Integer                                                                                    .sourceStage.sourceSpliterator.this$0.elementData[2] 5

Addresses are stable after 1 tries.


------------------------------------------------------------
eagerBuildFilteredMappedNonEmpty.findFirst().orElse("?")=4
++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
lazyBuildNonEmpty:
totalSize=384 bytes
totalCount=12 objects
net.mirwaldt.empty.streams.LazyBuildIntStream@710726a3d object externals:
          ADDRESS       SIZE TYPE                                                                                                 PATH                           VALUE
        7134b1188         32 java.util.ArrayList$ArrayListSpliterator                                                             .spliterator.ph.this$0.sourceSpliterator (object)
        7134b11a8       9776 (something else)                                                                                     (somewhere else)               (something else)
        7134b37d8         16 net.mirwaldt.empty.streams.demos.EmptyStream_04_IntStreamFromIntArray$$Lambda$107/0x0000000800cce288 .spliterator.ph.val$mapper     (object)
        7134b37e8        416 (something else)                                                                                     (somewhere else)               (something else)
        7134b3988         24 net.mirwaldt.empty.streams.LazyBuildIntStream                                                                                       (object)
        7134b39a0        248 (something else)                                                                                     (somewhere else)               (something else)
        7134b3a98         56 java.util.stream.ReferencePipeline$Head                                                              .spliterator.ph.this$0         (object)
        7134b3ad0         64 java.util.stream.ReferencePipeline$4                                                                 .spliterator.ph                (object)
        7134b3b10      11888 (something else)                                                                                     (somewhere else)               (something else)
        7134b6980         16 java.util.stream.AbstractPipeline$$Lambda$108/0x0000000800c99198                                     .spliterator.spliteratorSupplier (object)
        7134b6990        336 (something else)                                                                                     (somewhere else)               (something else)
        7134b6ae0         48 java.util.stream.StreamSpliterators$IntWrappingSpliterator                                           .spliterator                   (object)
        7134b6b10    3276296 (something else)                                                                                     (somewhere else)               (something else)
        7137d6918         56 [Ljava.lang.Object;                                                                                  .spliterator.ph.this$0.sourceSpliterator.this$0.elementData [-3, 2, 5, null, null, null, null, null, null, null]
        7137d6950    4507560 (something else)                                                                                     (somewhere else)               (something else)
        713c230f8         24 java.util.ArrayList                                                                                  .spliterator.ph.this$0.sourceSpliterator.this$0 (object)
        713c23110    2028144 (something else)                                                                                     (somewhere else)               (something else)
        713e12380         16 java.lang.Integer                                                                                    .spliterator.ph.this$0.sourceSpliterator.this$0.elementData[0] -3
        713e12390         64 (something else)                                                                                     (somewhere else)               (something else)
        713e123d0         16 java.lang.Integer                                                                                    .spliterator.ph.this$0.sourceSpliterator.this$0.elementData[1] 2
        713e123e0         32 (something else)                                                                                     (somewhere else)               (something else)
        713e12400         16 java.lang.Integer                                                                                    .spliterator.ph.this$0.sourceSpliterator.this$0.elementData[2] 5

Addresses are stable after 1 tries.


------------------------------------------------------------
lazyBuildFilteredNonEmpty:
totalSize=464 bytes
totalCount=16 objects
net.mirwaldt.empty.streams.LazyBuildIntStream@548ad73bd object externals:
          ADDRESS       SIZE TYPE                                                                                                 PATH                           VALUE
        7134b1188         32 java.util.ArrayList$ArrayListSpliterator                                                             .spliterator.ph.this$0.sourceSpliterator (object)
        7134b11a8       9776 (something else)                                                                                     (somewhere else)               (something else)
        7134b37d8         16 net.mirwaldt.empty.streams.demos.EmptyStream_04_IntStreamFromIntArray$$Lambda$107/0x0000000800cce288 .spliterator.ph.val$mapper     (object)
        7134b37e8        416 (something else)                                                                                     (somewhere else)               (something else)
        7134b3988         24 net.mirwaldt.empty.streams.LazyBuildIntStream                                                        .streamSupplier.arg$2          (object)
        7134b39a0        248 (something else)                                                                                     (somewhere else)               (something else)
        7134b3a98         56 java.util.stream.ReferencePipeline$Head                                                              .spliterator.ph.this$0         (object)
        7134b3ad0         64 java.util.stream.ReferencePipeline$4                                                                 .spliterator.ph                (object)
        7134b3b10      11888 (something else)                                                                                     (somewhere else)               (something else)
        7134b6980         16 java.util.stream.AbstractPipeline$$Lambda$108/0x0000000800c99198                                     .spliterator.spliteratorSupplier (object)
        7134b6990        336 (something else)                                                                                     (somewhere else)               (something else)
        7134b6ae0         48 java.util.stream.StreamSpliterators$IntWrappingSpliterator                                           .spliterator                   (object)
        7134b6b10     310776 (something else)                                                                                     (somewhere else)               (something else)
        713502908         16 net.mirwaldt.empty.streams.demos.EmptyStream_04_IntStreamFromIntArray$$Lambda$109/0x0000000800cce4a0 .streamSupplier.arg$1.arg$1    (object)
        713502918        352 (something else)                                                                                     (somewhere else)               (something else)
        713502a78         16 net.mirwaldt.empty.streams.LazyBuildIntStream$$Lambda$97/0x0000000800cccec8                          .streamSupplier.arg$1          (object)
        713502a88         24 net.mirwaldt.empty.streams.util.LazyBuildIntStreamUtil$$Lambda$101/0x0000000800ccd798                .streamSupplier                (object)
        713502aa0         24 net.mirwaldt.empty.streams.LazyBuildIntStream                                                                                       (object)
        713502ab8    2965088 (something else)                                                                                     (somewhere else)               (something else)
        7137d6918         56 [Ljava.lang.Object;                                                                                  .spliterator.ph.this$0.sourceSpliterator.this$0.elementData [-3, 2, 5, null, null, null, null, null, null, null]
        7137d6950    4507560 (something else)                                                                                     (somewhere else)               (something else)
        713c230f8         24 java.util.ArrayList                                                                                  .spliterator.ph.this$0.sourceSpliterator.this$0 (object)
        713c23110    2028144 (something else)                                                                                     (somewhere else)               (something else)
        713e12380         16 java.lang.Integer                                                                                    .spliterator.ph.this$0.sourceSpliterator.this$0.elementData[0] -3
        713e12390         64 (something else)                                                                                     (somewhere else)               (something else)
        713e123d0         16 java.lang.Integer                                                                                    .spliterator.ph.this$0.sourceSpliterator.this$0.elementData[1] 2
        713e123e0         32 (something else)                                                                                     (somewhere else)               (something else)
        713e12400         16 java.lang.Integer                                                                                    .spliterator.ph.this$0.sourceSpliterator.this$0.elementData[2] 5

Addresses are stable after 1 tries.


------------------------------------------------------------
lazyBuildFilteredMappedNonEmpty:
totalSize=520 bytes
totalCount=19 objects
net.mirwaldt.empty.streams.LazyBuildIntStream@df27faed object externals:
          ADDRESS       SIZE TYPE                                                                                                 PATH                           VALUE
        7134b1188         32 java.util.ArrayList$ArrayListSpliterator                                                             .spliterator.ph.this$0.sourceSpliterator (object)
        7134b11a8       9776 (something else)                                                                                     (somewhere else)               (something else)
        7134b37d8         16 net.mirwaldt.empty.streams.demos.EmptyStream_04_IntStreamFromIntArray$$Lambda$107/0x0000000800cce288 .spliterator.ph.val$mapper     (object)
        7134b37e8        416 (something else)                                                                                     (somewhere else)               (something else)
        7134b3988         24 net.mirwaldt.empty.streams.LazyBuildIntStream                                                        .streamSupplier.arg$2.arg$2    (object)
        7134b39a0        248 (something else)                                                                                     (somewhere else)               (something else)
        7134b3a98         56 java.util.stream.ReferencePipeline$Head                                                              .spliterator.ph.this$0         (object)
        7134b3ad0         64 java.util.stream.ReferencePipeline$4                                                                 .spliterator.ph                (object)
        7134b3b10      11888 (something else)                                                                                     (somewhere else)               (something else)
        7134b6980         16 java.util.stream.AbstractPipeline$$Lambda$108/0x0000000800c99198                                     .spliterator.spliteratorSupplier (object)
        7134b6990        336 (something else)                                                                                     (somewhere else)               (something else)
        7134b6ae0         48 java.util.stream.StreamSpliterators$IntWrappingSpliterator                                           .spliterator                   (object)
        7134b6b10     310776 (something else)                                                                                     (somewhere else)               (something else)
        713502908         16 net.mirwaldt.empty.streams.demos.EmptyStream_04_IntStreamFromIntArray$$Lambda$109/0x0000000800cce4a0 .streamSupplier.arg$2.arg$1.arg$1 (object)
        713502918        352 (something else)                                                                                     (somewhere else)               (something else)
        713502a78         16 net.mirwaldt.empty.streams.LazyBuildIntStream$$Lambda$97/0x0000000800cccec8                          .streamSupplier.arg$2.arg$1    (object)
        713502a88         24 net.mirwaldt.empty.streams.util.LazyBuildIntStreamUtil$$Lambda$101/0x0000000800ccd798                .streamSupplier.arg$2          (object)
        713502aa0     306712 (something else)                                                                                     (somewhere else)               (something else)
        71354d8b8         16 net.mirwaldt.empty.streams.demos.EmptyStream_04_IntStreamFromIntArray$$Lambda$110/0x0000000800cce6e8 .streamSupplier.arg$1.arg$1    (object)
        71354d8c8        352 (something else)                                                                                     (somewhere else)               (something else)
        71354da28         16 net.mirwaldt.empty.streams.LazyBuildIntStream$$Lambda$100/0x0000000800ccd558                         .streamSupplier.arg$1          (object)
        71354da38         24 net.mirwaldt.empty.streams.util.LazyBuildIntStreamUtil$$Lambda$101/0x0000000800ccd798                .streamSupplier                (object)
        71354da50         24 net.mirwaldt.empty.streams.LazyBuildIntStream                                                                                       (object)
        71354da68    2657968 (something else)                                                                                     (somewhere else)               (something else)
        7137d6918         56 [Ljava.lang.Object;                                                                                  .spliterator.ph.this$0.sourceSpliterator.this$0.elementData [-3, 2, 5, null, null, null, null, null, null, null]
        7137d6950    4507560 (something else)                                                                                     (somewhere else)               (something else)
        713c230f8         24 java.util.ArrayList                                                                                  .spliterator.ph.this$0.sourceSpliterator.this$0 (object)
        713c23110    2028144 (something else)                                                                                     (somewhere else)               (something else)
        713e12380         16 java.lang.Integer                                                                                    .spliterator.ph.this$0.sourceSpliterator.this$0.elementData[0] -3
        713e12390         64 (something else)                                                                                     (somewhere else)               (something else)
        713e123d0         16 java.lang.Integer                                                                                    .spliterator.ph.this$0.sourceSpliterator.this$0.elementData[1] 2
        713e123e0         32 (something else)                                                                                     (somewhere else)               (something else)
        713e12400         16 java.lang.Integer                                                                                    .spliterator.ph.this$0.sourceSpliterator.this$0.elementData[2] 5

Addresses are stable after 1 tries.


------------------------------------------------------------
lazyBuildFilteredMappedNonEmpty.findFirst().orElse(-1)=4
 */
}
