package net.mirwaldt.empty.streams.demos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static net.mirwaldt.empty.streams.LazyBuildStreamFactory.lazyBuildGenericStream;
import static net.mirwaldt.empty.streams.LazyBuildStreamFactory.lazyBuildIntStream;
import static net.mirwaldt.empty.streams.demos.PrintUtil.*;

/**
 * Use with VM option -Djol.magicFieldOffset=true
 */
public class EmptyStream_08_IntStreamFromIntArray_Parallel {
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

        IntStream eagerBuildFilteredMappedParallelEmpty = eagerBuildFilteredMappedEmpty.parallel();
        printStatistics("eagerBuildFilteredMappedParallelEmpty:", eagerBuildFilteredMappedParallelEmpty);

        printLineOfMinus(); // ------------------------------------------------------------

        System.out.println("eagerBuildFilteredMappedParallelEmpty.findFirst().orElse(-1)="
                + eagerBuildFilteredMappedParallelEmpty.findFirst().orElse(-1));


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

        IntStream lazyBuildFilteredMappedParallelEmpty = lazyBuildFilteredMappedEmpty.parallel();
        printStatistics("lazyBuildFilteredMappedParallelEmpty:", lazyBuildFilteredMappedParallelEmpty);

        printLineOfMinus(); // ------------------------------------------------------------

        System.out.println("lazyBuildFilteredMappedParallelEmpty.findFirst().orElse(-1)="
                + lazyBuildFilteredMappedParallelEmpty.findFirst().orElse(-1));



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

        IntStream eagerBuildFilteredMappedParallelNonEmpty = eagerBuildFilteredMappedNonEmpty.parallel();
        printStatistics("eagerBuildFilteredMappedParallelNonEmpty:", eagerBuildFilteredMappedParallelNonEmpty);

        printLineOfMinus(); // ------------------------------------------------------------

        System.out.println("eagerBuildFilteredMappedParallelNonEmpty.findFirst().orElse(\"?\")="
                + eagerBuildFilteredMappedParallelNonEmpty.findFirst().orElse(-1));


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

        IntStream lazyBuildFilteredMappedParallelNonEmpty = lazyBuildFilteredMappedNonEmpty.parallel();
        printStatistics("lazyBuildFilteredMappedParallelNonEmpty:", lazyBuildFilteredMappedParallelNonEmpty);

        printLineOfMinus(); // ------------------------------------------------------------

        System.out.println("lazyBuildFilteredMappedParallelNonEmpty.findFirst().orElse(-1)="
                + lazyBuildFilteredMappedParallelNonEmpty.findFirst().orElse(-1));
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
          ADDRESS       SIZE TYPE                                                                                                         PATH                           VALUE
        713c23438         24 java.util.ArrayList                                                                                          .this$0.sourceSpliterator.this$0 (object)
        713c23450      35800 (something else)                                                                                             (somewhere else)               (something else)
        713c2c028         32 java.util.ArrayList$ArrayListSpliterator                                                                     .this$0.sourceSpliterator      (object)
        713c2c048         56 java.util.stream.ReferencePipeline$Head                                                                      .this$0                        (object)
        713c2c080      14704 (something else)                                                                                             (somewhere else)               (something else)
        713c2f9f0         16 net.mirwaldt.empty.streams.demos.EmptyStream_08_IntStreamFromIntArray_Parallel$$Lambda$15/0x0000000800c01400 .val$mapper                    (object)
        713c2fa00       1520 (something else)                                                                                             (somewhere else)               (something else)
        713c2fff0         64 java.util.stream.ReferencePipeline$4                                                                                                        (object)
        713c30030    2063048 (something else)                                                                                             (somewhere else)               (something else)
        713e27af8         16 [Ljava.lang.Object;                                                                                          .this$0.sourceSpliterator.this$0.elementData []

Addresses are stable after 1 tries.


------------------------------------------------------------
eagerBuildFilteredEmpty:
totalSize=288 bytes
totalCount=8 objects
java.util.stream.IntPipeline$10@6a1aab78d object externals:
          ADDRESS       SIZE TYPE                                                                                                         PATH                           VALUE
        7136ba1c8         16 net.mirwaldt.empty.streams.demos.EmptyStream_08_IntStreamFromIntArray_Parallel$$Lambda$90/0x0000000800cc6620 .val$predicate                 (object)
        7136ba1d8        600 (something else)                                                                                             (somewhere else)               (something else)
        7136ba430         64 java.util.stream.IntPipeline$10                                                                                                             (object)
        7136ba470    5672904 (something else)                                                                                             (somewhere else)               (something else)
        713c23438         24 java.util.ArrayList                                                                                          .sourceStage.sourceSpliterator.this$0 (object)
        713c23450      35800 (something else)                                                                                             (somewhere else)               (something else)
        713c2c028         32 java.util.ArrayList$ArrayListSpliterator                                                                     .sourceStage.sourceSpliterator (object)
        713c2c048         56 java.util.stream.ReferencePipeline$Head                                                                      .sourceStage                   (object)
        713c2c080      14704 (something else)                                                                                             (somewhere else)               (something else)
        713c2f9f0         16 net.mirwaldt.empty.streams.demos.EmptyStream_08_IntStreamFromIntArray_Parallel$$Lambda$15/0x0000000800c01400 .this$0.val$mapper             (object)
        713c2fa00       1520 (something else)                                                                                             (somewhere else)               (something else)
        713c2fff0         64 java.util.stream.ReferencePipeline$4                                                                         .this$0                        (object)
        713c30030    2063048 (something else)                                                                                             (somewhere else)               (something else)
        713e27af8         16 [Ljava.lang.Object;                                                                                          .sourceStage.sourceSpliterator.this$0.elementData []

Addresses are stable after 1 tries.


------------------------------------------------------------
eagerBuildFilteredMappedEmpty:
totalSize=368 bytes
totalCount=10 objects
java.util.stream.IntPipeline$4@8646db9d object externals:
          ADDRESS       SIZE TYPE                                                                                                         PATH                           VALUE
        7136ba1c8         16 net.mirwaldt.empty.streams.demos.EmptyStream_08_IntStreamFromIntArray_Parallel$$Lambda$90/0x0000000800cc6620 .this$0.val$predicate          (object)
        7136ba1d8        600 (something else)                                                                                             (somewhere else)               (something else)
        7136ba430         64 java.util.stream.IntPipeline$10                                                                              .this$0                        (object)
        7136ba470     287496 (something else)                                                                                             (somewhere else)               (something else)
        713700778         16 net.mirwaldt.empty.streams.demos.EmptyStream_08_IntStreamFromIntArray_Parallel$$Lambda$91/0x0000000800cc6868 .val$mapper                    (object)
        713700788        608 (something else)                                                                                             (somewhere else)               (something else)
        7137009e8         64 java.util.stream.IntPipeline$4                                                                                                              (object)
        713700a28    5384720 (something else)                                                                                             (somewhere else)               (something else)
        713c23438         24 java.util.ArrayList                                                                                          .sourceStage.sourceSpliterator.this$0 (object)
        713c23450      35800 (something else)                                                                                             (somewhere else)               (something else)
        713c2c028         32 java.util.ArrayList$ArrayListSpliterator                                                                     .sourceStage.sourceSpliterator (object)
        713c2c048         56 java.util.stream.ReferencePipeline$Head                                                                      .sourceStage                   (object)
        713c2c080      14704 (something else)                                                                                             (somewhere else)               (something else)
        713c2f9f0         16 net.mirwaldt.empty.streams.demos.EmptyStream_08_IntStreamFromIntArray_Parallel$$Lambda$15/0x0000000800c01400 .sourceStage.nextStage.val$mapper (object)
        713c2fa00       1520 (something else)                                                                                             (somewhere else)               (something else)
        713c2fff0         64 java.util.stream.ReferencePipeline$4                                                                         .sourceStage.nextStage         (object)
        713c30030    2063048 (something else)                                                                                             (somewhere else)               (something else)
        713e27af8         16 [Ljava.lang.Object;                                                                                          .sourceStage.sourceSpliterator.this$0.elementData []

Addresses are stable after 1 tries.


------------------------------------------------------------
eagerBuildFilteredMappedParallelEmpty:
totalSize=368 bytes
totalCount=10 objects
java.util.stream.IntPipeline$4@8646db9d object externals:
          ADDRESS       SIZE TYPE                                                                                                         PATH                           VALUE
        7136ba1c8         16 net.mirwaldt.empty.streams.demos.EmptyStream_08_IntStreamFromIntArray_Parallel$$Lambda$90/0x0000000800cc6620 .this$0.val$predicate          (object)
        7136ba1d8        600 (something else)                                                                                             (somewhere else)               (something else)
        7136ba430         64 java.util.stream.IntPipeline$10                                                                              .this$0                        (object)
        7136ba470     287496 (something else)                                                                                             (somewhere else)               (something else)
        713700778         16 net.mirwaldt.empty.streams.demos.EmptyStream_08_IntStreamFromIntArray_Parallel$$Lambda$91/0x0000000800cc6868 .val$mapper                    (object)
        713700788        608 (something else)                                                                                             (somewhere else)               (something else)
        7137009e8         64 java.util.stream.IntPipeline$4                                                                                                              (object)
        713700a28    5384720 (something else)                                                                                             (somewhere else)               (something else)
        713c23438         24 java.util.ArrayList                                                                                          .sourceStage.sourceSpliterator.this$0 (object)
        713c23450      35800 (something else)                                                                                             (somewhere else)               (something else)
        713c2c028         32 java.util.ArrayList$ArrayListSpliterator                                                                     .sourceStage.sourceSpliterator (object)
        713c2c048         56 java.util.stream.ReferencePipeline$Head                                                                      .sourceStage                   (object)
        713c2c080      14704 (something else)                                                                                             (somewhere else)               (something else)
        713c2f9f0         16 net.mirwaldt.empty.streams.demos.EmptyStream_08_IntStreamFromIntArray_Parallel$$Lambda$15/0x0000000800c01400 .sourceStage.nextStage.val$mapper (object)
        713c2fa00       1520 (something else)                                                                                             (somewhere else)               (something else)
        713c2fff0         64 java.util.stream.ReferencePipeline$4                                                                         .sourceStage.nextStage         (object)
        713c30030    2063048 (something else)                                                                                             (somewhere else)               (something else)
        713e27af8         16 [Ljava.lang.Object;                                                                                          .sourceStage.sourceSpliterator.this$0.elementData []

Addresses are stable after 1 tries.


------------------------------------------------------------
eagerBuildFilteredMappedParallelEmpty.findFirst().orElse(-1)=-1
++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
lazyBuildEmpty:
totalSize=56 bytes
totalCount=3 objects
net.mirwaldt.empty.streams.LazyBuildIntStream@32eebfcad object externals:
          ADDRESS       SIZE TYPE                                                     PATH                           VALUE
        7137d0758         24 net.mirwaldt.empty.streams.LazyBuildIntStream                                           (object)
        7137d0770      22056 (something else)                                         (somewhere else)               (something else)
        7137d5d98         16 net.mirwaldt.empty.streams.util.LazyBuildIntStreamUtil$1 .streamSupplier                (object)
        7137d5da8    6848560 (something else)                                         (somewhere else)               (something else)
        713e5ddd8         16 java.util.Spliterators$EmptySpliterator$OfInt            .spliterator                   (object)

Addresses are stable after 1 tries.


------------------------------------------------------------
lazyBuildFilteredEmpty:
totalSize=56 bytes
totalCount=3 objects
net.mirwaldt.empty.streams.LazyBuildIntStream@1fc2b765d object externals:
          ADDRESS       SIZE TYPE                                                                                 PATH                           VALUE
        7137e76f0         16 net.mirwaldt.empty.streams.util.LazyBuildIntStreamUtil$$Lambda$98/0x0000000800ccd108 .streamSupplier                (object)
        7137e7700        352 (something else)                                                                     (somewhere else)               (something else)
        7137e7860         24 net.mirwaldt.empty.streams.LazyBuildIntStream                                                                       (object)
        7137e7878    6776160 (something else)                                                                     (somewhere else)               (something else)
        713e5ddd8         16 java.util.Spliterators$EmptySpliterator$OfInt                                        .spliterator                   (object)

Addresses are stable after 1 tries.


------------------------------------------------------------
lazyBuildFilteredMappedEmpty:
totalSize=112 bytes
totalCount=6 objects
net.mirwaldt.empty.streams.LazyBuildIntStream@17d0685fd object externals:
          ADDRESS       SIZE TYPE                                                                                                         PATH                           VALUE
        7137e76f0         16 net.mirwaldt.empty.streams.util.LazyBuildIntStreamUtil$$Lambda$98/0x0000000800ccd108                         .streamSupplier.arg$2          (object)
        7137e7700      32456 (something else)                                                                                             (somewhere else)               (something else)
        7137ef5c8         16 net.mirwaldt.empty.streams.demos.EmptyStream_08_IntStreamFromIntArray_Parallel$$Lambda$99/0x0000000800ccd320 .streamSupplier.arg$1.arg$1    (object)
        7137ef5d8      13744 (something else)                                                                                             (somewhere else)               (something else)
        7137f2b88         16 net.mirwaldt.empty.streams.LazyBuildIntStream$$Lambda$100/0x0000000800ccd558                                 .streamSupplier.arg$1          (object)
        7137f2b98      12320 (something else)                                                                                             (somewhere else)               (something else)
        7137f5bb8         24 net.mirwaldt.empty.streams.util.LazyBuildIntStreamUtil$$Lambda$101/0x0000000800ccd798                        .streamSupplier                (object)
        7137f5bd0         24 net.mirwaldt.empty.streams.LazyBuildIntStream                                                                                               (object)
        7137f5be8    6717936 (something else)                                                                                             (somewhere else)               (something else)
        713e5ddd8         16 java.util.Spliterators$EmptySpliterator$OfInt                                                                .spliterator                   (object)

Addresses are stable after 1 tries.


------------------------------------------------------------
lazyBuildFilteredMappedParallelEmpty:
totalSize=152 bytes
totalCount=8 objects
net.mirwaldt.empty.streams.LazyBuildIntStream@64485a47d object externals:
          ADDRESS       SIZE TYPE                                                                                                         PATH                           VALUE
        71342c5e0         16 net.mirwaldt.empty.streams.LazyBuildIntStream$$Lambda$102/0x0000000800ccd9b8                                 .streamSupplier.arg$1          (object)
        71342c5f0        352 (something else)                                                                                             (somewhere else)               (something else)
        71342c750         24 net.mirwaldt.empty.streams.util.LazyBuildIntStreamUtil$$Lambda$101/0x0000000800ccd798                        .streamSupplier                (object)
        71342c768         24 net.mirwaldt.empty.streams.LazyBuildIntStream                                                                                               (object)
        71342c780    3911536 (something else)                                                                                             (somewhere else)               (something else)
        7137e76f0         16 net.mirwaldt.empty.streams.util.LazyBuildIntStreamUtil$$Lambda$98/0x0000000800ccd108                         .streamSupplier.arg$2.arg$2    (object)
        7137e7700      32456 (something else)                                                                                             (somewhere else)               (something else)
        7137ef5c8         16 net.mirwaldt.empty.streams.demos.EmptyStream_08_IntStreamFromIntArray_Parallel$$Lambda$99/0x0000000800ccd320 .streamSupplier.arg$2.arg$1.arg$1 (object)
        7137ef5d8      13744 (something else)                                                                                             (somewhere else)               (something else)
        7137f2b88         16 net.mirwaldt.empty.streams.LazyBuildIntStream$$Lambda$100/0x0000000800ccd558                                 .streamSupplier.arg$2.arg$1    (object)
        7137f2b98      12320 (something else)                                                                                             (somewhere else)               (something else)
        7137f5bb8         24 net.mirwaldt.empty.streams.util.LazyBuildIntStreamUtil$$Lambda$101/0x0000000800ccd798                        .streamSupplier.arg$2          (object)
        7137f5bd0    6717960 (something else)                                                                                             (somewhere else)               (something else)
        713e5ddd8         16 java.util.Spliterators$EmptySpliterator$OfInt                                                                .spliterator                   (object)

Addresses are stable after 1 tries.


------------------------------------------------------------
lazyBuildFilteredMappedParallelEmpty.findFirst().orElse(-1)=-1
############################################################
list=["-3", "2", "5"]
############################################################
eagerBuildNonEmpty:
totalSize=296 bytes
totalCount=9 objects
java.util.stream.ReferencePipeline$4@704d6e83d object externals:
          ADDRESS       SIZE TYPE                                                                                                          PATH                           VALUE
        71343c1a0         56 [Ljava.lang.Object;                                                                                           .this$0.sourceSpliterator.this$0.elementData [-3, 2, 5, null, null, null, null, null, null, null]
        71343c1d8      25912 (something else)                                                                                              (somewhere else)               (something else)
        713442710         32 java.util.ArrayList$ArrayListSpliterator                                                                      .this$0.sourceSpliterator      (object)
        713442730         56 java.util.stream.ReferencePipeline$Head                                                                       .this$0                        (object)
        713442768       9840 (something else)                                                                                              (somewhere else)               (something else)
        713444dd8         16 net.mirwaldt.empty.streams.demos.EmptyStream_08_IntStreamFromIntArray_Parallel$$Lambda$105/0x0000000800ccde28 .val$mapper                    (object)
        713444de8        352 (something else)                                                                                              (somewhere else)               (something else)
        713444f48         64 java.util.stream.ReferencePipeline$4                                                                                                         (object)
        713444f88    8250544 (something else)                                                                                              (somewhere else)               (something else)
        713c23438         24 java.util.ArrayList                                                                                           .this$0.sourceSpliterator.this$0 (object)
        713c23450    2027320 (something else)                                                                                              (somewhere else)               (something else)
        713e12388         16 java.lang.Integer                                                                                             .this$0.sourceSpliterator.this$0.elementData[0] -3
        713e12398         64 (something else)                                                                                              (somewhere else)               (something else)
        713e123d8         16 java.lang.Integer                                                                                             .this$0.sourceSpliterator.this$0.elementData[1] 2
        713e123e8         32 (something else)                                                                                              (somewhere else)               (something else)
        713e12408         16 java.lang.Integer                                                                                             .this$0.sourceSpliterator.this$0.elementData[2] 5

Addresses are stable after 1 tries.


------------------------------------------------------------
eagerBuildFilteredNonEmpty:
totalSize=376 bytes
totalCount=11 objects
java.util.stream.IntPipeline$10@149494d8d object externals:
          ADDRESS       SIZE TYPE                                                                                                          PATH                           VALUE
        71343c1a0         56 [Ljava.lang.Object;                                                                                           .sourceStage.sourceSpliterator.this$0.elementData [-3, 2, 5, null, null, null, null, null, null, null]
        71343c1d8      25912 (something else)                                                                                              (somewhere else)               (something else)
        713442710         32 java.util.ArrayList$ArrayListSpliterator                                                                      .sourceStage.sourceSpliterator (object)
        713442730         56 java.util.stream.ReferencePipeline$Head                                                                       .sourceStage                   (object)
        713442768       9840 (something else)                                                                                              (somewhere else)               (something else)
        713444dd8         16 net.mirwaldt.empty.streams.demos.EmptyStream_08_IntStreamFromIntArray_Parallel$$Lambda$105/0x0000000800ccde28 .this$0.val$mapper             (object)
        713444de8        352 (something else)                                                                                              (somewhere else)               (something else)
        713444f48         64 java.util.stream.ReferencePipeline$4                                                                          .this$0                        (object)
        713444f88     243888 (something else)                                                                                              (somewhere else)               (something else)
        713480838         16 net.mirwaldt.empty.streams.demos.EmptyStream_08_IntStreamFromIntArray_Parallel$$Lambda$106/0x0000000800cce040 .val$predicate                 (object)
        713480848        352 (something else)                                                                                              (somewhere else)               (something else)
        7134809a8         64 java.util.stream.IntPipeline$10                                                                                                              (object)
        7134809e8    8006224 (something else)                                                                                              (somewhere else)               (something else)
        713c23438         24 java.util.ArrayList                                                                                           .sourceStage.sourceSpliterator.this$0 (object)
        713c23450    2027320 (something else)                                                                                              (somewhere else)               (something else)
        713e12388         16 java.lang.Integer                                                                                             .sourceStage.sourceSpliterator.this$0.elementData[0] -3
        713e12398         64 (something else)                                                                                              (somewhere else)               (something else)
        713e123d8         16 java.lang.Integer                                                                                             .sourceStage.sourceSpliterator.this$0.elementData[1] 2
        713e123e8         32 (something else)                                                                                              (somewhere else)               (something else)
        713e12408         16 java.lang.Integer                                                                                             .sourceStage.sourceSpliterator.this$0.elementData[2] 5

Addresses are stable after 1 tries.


------------------------------------------------------------
eagerBuildFilteredMappedNonEmpty:
totalSize=456 bytes
totalCount=13 objects
java.util.stream.IntPipeline$4@481a15ffd object externals:
          ADDRESS       SIZE TYPE                                                                                                          PATH                           VALUE
        71343c1a0         56 [Ljava.lang.Object;                                                                                           .sourceStage.sourceSpliterator.this$0.elementData [-3, 2, 5, null, null, null, null, null, null, null]
        71343c1d8      25912 (something else)                                                                                              (somewhere else)               (something else)
        713442710         32 java.util.ArrayList$ArrayListSpliterator                                                                      .sourceStage.sourceSpliterator (object)
        713442730         56 java.util.stream.ReferencePipeline$Head                                                                       .sourceStage                   (object)
        713442768       9840 (something else)                                                                                              (somewhere else)               (something else)
        713444dd8         16 net.mirwaldt.empty.streams.demos.EmptyStream_08_IntStreamFromIntArray_Parallel$$Lambda$105/0x0000000800ccde28 .sourceStage.nextStage.val$mapper (object)
        713444de8        352 (something else)                                                                                              (somewhere else)               (something else)
        713444f48         64 java.util.stream.ReferencePipeline$4                                                                          .sourceStage.nextStage         (object)
        713444f88     243888 (something else)                                                                                              (somewhere else)               (something else)
        713480838         16 net.mirwaldt.empty.streams.demos.EmptyStream_08_IntStreamFromIntArray_Parallel$$Lambda$106/0x0000000800cce040 .this$0.val$predicate          (object)
        713480848        352 (something else)                                                                                              (somewhere else)               (something else)
        7134809a8         64 java.util.stream.IntPipeline$10                                                                               .this$0                        (object)
        7134809e8     277272 (something else)                                                                                              (somewhere else)               (something else)
        7134c4500         16 net.mirwaldt.empty.streams.demos.EmptyStream_08_IntStreamFromIntArray_Parallel$$Lambda$107/0x0000000800cce288 .val$mapper                    (object)
        7134c4510        352 (something else)                                                                                              (somewhere else)               (something else)
        7134c4670         64 java.util.stream.IntPipeline$4                                                                                                               (object)
        7134c46b0    7728520 (something else)                                                                                              (somewhere else)               (something else)
        713c23438         24 java.util.ArrayList                                                                                           .sourceStage.sourceSpliterator.this$0 (object)
        713c23450    2027320 (something else)                                                                                              (somewhere else)               (something else)
        713e12388         16 java.lang.Integer                                                                                             .sourceStage.sourceSpliterator.this$0.elementData[0] -3
        713e12398         64 (something else)                                                                                              (somewhere else)               (something else)
        713e123d8         16 java.lang.Integer                                                                                             .sourceStage.sourceSpliterator.this$0.elementData[1] 2
        713e123e8         32 (something else)                                                                                              (somewhere else)               (something else)
        713e12408         16 java.lang.Integer                                                                                             .sourceStage.sourceSpliterator.this$0.elementData[2] 5

Addresses are stable after 1 tries.


------------------------------------------------------------
eagerBuildFilteredMappedParallelNonEmpty:
totalSize=456 bytes
totalCount=13 objects
java.util.stream.IntPipeline$4@481a15ffd object externals:
          ADDRESS       SIZE TYPE                                                                                                          PATH                           VALUE
        71343c1a0         56 [Ljava.lang.Object;                                                                                           .sourceStage.sourceSpliterator.this$0.elementData [-3, 2, 5, null, null, null, null, null, null, null]
        71343c1d8      25912 (something else)                                                                                              (somewhere else)               (something else)
        713442710         32 java.util.ArrayList$ArrayListSpliterator                                                                      .sourceStage.sourceSpliterator (object)
        713442730         56 java.util.stream.ReferencePipeline$Head                                                                       .sourceStage                   (object)
        713442768       9840 (something else)                                                                                              (somewhere else)               (something else)
        713444dd8         16 net.mirwaldt.empty.streams.demos.EmptyStream_08_IntStreamFromIntArray_Parallel$$Lambda$105/0x0000000800ccde28 .sourceStage.nextStage.val$mapper (object)
        713444de8        352 (something else)                                                                                              (somewhere else)               (something else)
        713444f48         64 java.util.stream.ReferencePipeline$4                                                                          .sourceStage.nextStage         (object)
        713444f88     243888 (something else)                                                                                              (somewhere else)               (something else)
        713480838         16 net.mirwaldt.empty.streams.demos.EmptyStream_08_IntStreamFromIntArray_Parallel$$Lambda$106/0x0000000800cce040 .this$0.val$predicate          (object)
        713480848        352 (something else)                                                                                              (somewhere else)               (something else)
        7134809a8         64 java.util.stream.IntPipeline$10                                                                               .this$0                        (object)
        7134809e8     277272 (something else)                                                                                              (somewhere else)               (something else)
        7134c4500         16 net.mirwaldt.empty.streams.demos.EmptyStream_08_IntStreamFromIntArray_Parallel$$Lambda$107/0x0000000800cce288 .val$mapper                    (object)
        7134c4510        352 (something else)                                                                                              (somewhere else)               (something else)
        7134c4670         64 java.util.stream.IntPipeline$4                                                                                                               (object)
        7134c46b0    7728520 (something else)                                                                                              (somewhere else)               (something else)
        713c23438         24 java.util.ArrayList                                                                                           .sourceStage.sourceSpliterator.this$0 (object)
        713c23450    2027320 (something else)                                                                                              (somewhere else)               (something else)
        713e12388         16 java.lang.Integer                                                                                             .sourceStage.sourceSpliterator.this$0.elementData[0] -3
        713e12398         64 (something else)                                                                                              (somewhere else)               (something else)
        713e123d8         16 java.lang.Integer                                                                                             .sourceStage.sourceSpliterator.this$0.elementData[1] 2
        713e123e8         32 (something else)                                                                                              (somewhere else)               (something else)
        713e12408         16 java.lang.Integer                                                                                             .sourceStage.sourceSpliterator.this$0.elementData[2] 5

Addresses are stable after 1 tries.


------------------------------------------------------------
eagerBuildFilteredMappedParallelNonEmpty.findFirst().orElse("?")=4
++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
lazyBuildNonEmpty:
totalSize=384 bytes
totalCount=12 objects
net.mirwaldt.empty.streams.LazyBuildIntStream@df27faed object externals:
          ADDRESS       SIZE TYPE                                                                                                          PATH                           VALUE
        71343c1a0         56 [Ljava.lang.Object;                                                                                           .spliterator.ph.this$0.sourceSpliterator.this$0.elementData [-3, 2, 5, null, null, null, null, null, null, null]
        71343c1d8    1259784 (something else)                                                                                              (somewhere else)               (something else)
        71356fae0         32 java.util.ArrayList$ArrayListSpliterator                                                                      .spliterator.ph.this$0.sourceSpliterator (object)
        71356fb00       9920 (something else)                                                                                              (somewhere else)               (something else)
        7135721c0         16 net.mirwaldt.empty.streams.demos.EmptyStream_08_IntStreamFromIntArray_Parallel$$Lambda$108/0x0000000800cce4c0 .spliterator.ph.val$mapper     (object)
        7135721d0        416 (something else)                                                                                              (somewhere else)               (something else)
        713572370         24 net.mirwaldt.empty.streams.LazyBuildIntStream                                                                                                (object)
        713572388        248 (something else)                                                                                              (somewhere else)               (something else)
        713572480         56 java.util.stream.ReferencePipeline$Head                                                                       .spliterator.ph.this$0         (object)
        7135724b8         64 java.util.stream.ReferencePipeline$4                                                                          .spliterator.ph                (object)
        7135724f8      11888 (something else)                                                                                              (somewhere else)               (something else)
        713575368         16 java.util.stream.AbstractPipeline$$Lambda$109/0x0000000800c99fb0                                              .spliterator.spliteratorSupplier (object)
        713575378        336 (something else)                                                                                              (somewhere else)               (something else)
        7135754c8         48 java.util.stream.StreamSpliterators$IntWrappingSpliterator                                                    .spliterator                   (object)
        7135754f8    7003968 (something else)                                                                                              (somewhere else)               (something else)
        713c23438         24 java.util.ArrayList                                                                                           .spliterator.ph.this$0.sourceSpliterator.this$0 (object)
        713c23450    2027320 (something else)                                                                                              (somewhere else)               (something else)
        713e12388         16 java.lang.Integer                                                                                             .spliterator.ph.this$0.sourceSpliterator.this$0.elementData[0] -3
        713e12398         64 (something else)                                                                                              (somewhere else)               (something else)
        713e123d8         16 java.lang.Integer                                                                                             .spliterator.ph.this$0.sourceSpliterator.this$0.elementData[1] 2
        713e123e8         32 (something else)                                                                                              (somewhere else)               (something else)
        713e12408         16 java.lang.Integer                                                                                             .spliterator.ph.this$0.sourceSpliterator.this$0.elementData[2] 5

Addresses are stable after 1 tries.


------------------------------------------------------------
lazyBuildFilteredNonEmpty:
totalSize=464 bytes
totalCount=16 objects
net.mirwaldt.empty.streams.LazyBuildIntStream@31f924f5d object externals:
          ADDRESS       SIZE TYPE                                                                                                          PATH                           VALUE
        71343c1a0         56 [Ljava.lang.Object;                                                                                           .spliterator.ph.this$0.sourceSpliterator.this$0.elementData [-3, 2, 5, null, null, null, null, null, null, null]
        71343c1d8    1259784 (something else)                                                                                              (somewhere else)               (something else)
        71356fae0         32 java.util.ArrayList$ArrayListSpliterator                                                                      .spliterator.ph.this$0.sourceSpliterator (object)
        71356fb00       9920 (something else)                                                                                              (somewhere else)               (something else)
        7135721c0         16 net.mirwaldt.empty.streams.demos.EmptyStream_08_IntStreamFromIntArray_Parallel$$Lambda$108/0x0000000800cce4c0 .spliterator.ph.val$mapper     (object)
        7135721d0        416 (something else)                                                                                              (somewhere else)               (something else)
        713572370         24 net.mirwaldt.empty.streams.LazyBuildIntStream                                                                 .streamSupplier.arg$2          (object)
        713572388        248 (something else)                                                                                              (somewhere else)               (something else)
        713572480         56 java.util.stream.ReferencePipeline$Head                                                                       .spliterator.ph.this$0         (object)
        7135724b8         64 java.util.stream.ReferencePipeline$4                                                                          .spliterator.ph                (object)
        7135724f8      11888 (something else)                                                                                              (somewhere else)               (something else)
        713575368         16 java.util.stream.AbstractPipeline$$Lambda$109/0x0000000800c99fb0                                              .spliterator.spliteratorSupplier (object)
        713575378        336 (something else)                                                                                              (somewhere else)               (something else)
        7135754c8         48 java.util.stream.StreamSpliterators$IntWrappingSpliterator                                                    .spliterator                   (object)
        7135754f8     311488 (something else)                                                                                              (somewhere else)               (something else)
        7135c15b8         16 net.mirwaldt.empty.streams.demos.EmptyStream_08_IntStreamFromIntArray_Parallel$$Lambda$110/0x0000000800cce6d8 .streamSupplier.arg$1.arg$1    (object)
        7135c15c8        352 (something else)                                                                                              (somewhere else)               (something else)
        7135c1728         16 net.mirwaldt.empty.streams.LazyBuildIntStream$$Lambda$97/0x0000000800cccec8                                   .streamSupplier.arg$1          (object)
        7135c1738         24 net.mirwaldt.empty.streams.util.LazyBuildIntStreamUtil$$Lambda$101/0x0000000800ccd798                         .streamSupplier                (object)
        7135c1750         24 net.mirwaldt.empty.streams.LazyBuildIntStream                                                                                                (object)
        7135c1768    6692048 (something else)                                                                                              (somewhere else)               (something else)
        713c23438         24 java.util.ArrayList                                                                                           .spliterator.ph.this$0.sourceSpliterator.this$0 (object)
        713c23450    2027320 (something else)                                                                                              (somewhere else)               (something else)
        713e12388         16 java.lang.Integer                                                                                             .spliterator.ph.this$0.sourceSpliterator.this$0.elementData[0] -3
        713e12398         64 (something else)                                                                                              (somewhere else)               (something else)
        713e123d8         16 java.lang.Integer                                                                                             .spliterator.ph.this$0.sourceSpliterator.this$0.elementData[1] 2
        713e123e8         32 (something else)                                                                                              (somewhere else)               (something else)
        713e12408         16 java.lang.Integer                                                                                             .spliterator.ph.this$0.sourceSpliterator.this$0.elementData[2] 5

Addresses are stable after 1 tries.


------------------------------------------------------------
lazyBuildFilteredMappedNonEmpty:
totalSize=520 bytes
totalCount=19 objects
net.mirwaldt.empty.streams.LazyBuildIntStream@2fd66ad3d object externals:
          ADDRESS       SIZE TYPE                                                                                                          PATH                           VALUE
        713249910         16 net.mirwaldt.empty.streams.demos.EmptyStream_08_IntStreamFromIntArray_Parallel$$Lambda$111/0x0000000800cce920 .streamSupplier.arg$1.arg$1    (object)
        713249920        352 (something else)                                                                                              (somewhere else)               (something else)
        713249a80         16 net.mirwaldt.empty.streams.LazyBuildIntStream$$Lambda$100/0x0000000800ccd558                                  .streamSupplier.arg$1          (object)
        713249a90         24 net.mirwaldt.empty.streams.util.LazyBuildIntStreamUtil$$Lambda$101/0x0000000800ccd798                         .streamSupplier                (object)
        713249aa8         24 net.mirwaldt.empty.streams.LazyBuildIntStream                                                                                                (object)
        713249ac0    2041568 (something else)                                                                                              (somewhere else)               (something else)
        71343c1a0         56 [Ljava.lang.Object;                                                                                           .spliterator.ph.this$0.sourceSpliterator.this$0.elementData [-3, 2, 5, null, null, null, null, null, null, null]
        71343c1d8    1259784 (something else)                                                                                              (somewhere else)               (something else)
        71356fae0         32 java.util.ArrayList$ArrayListSpliterator                                                                      .spliterator.ph.this$0.sourceSpliterator (object)
        71356fb00       9920 (something else)                                                                                              (somewhere else)               (something else)
        7135721c0         16 net.mirwaldt.empty.streams.demos.EmptyStream_08_IntStreamFromIntArray_Parallel$$Lambda$108/0x0000000800cce4c0 .spliterator.ph.val$mapper     (object)
        7135721d0        416 (something else)                                                                                              (somewhere else)               (something else)
        713572370         24 net.mirwaldt.empty.streams.LazyBuildIntStream                                                                 .streamSupplier.arg$2.arg$2    (object)
        713572388        248 (something else)                                                                                              (somewhere else)               (something else)
        713572480         56 java.util.stream.ReferencePipeline$Head                                                                       .spliterator.ph.this$0         (object)
        7135724b8         64 java.util.stream.ReferencePipeline$4                                                                          .spliterator.ph                (object)
        7135724f8      11888 (something else)                                                                                              (somewhere else)               (something else)
        713575368         16 java.util.stream.AbstractPipeline$$Lambda$109/0x0000000800c99fb0                                              .spliterator.spliteratorSupplier (object)
        713575378        336 (something else)                                                                                              (somewhere else)               (something else)
        7135754c8         48 java.util.stream.StreamSpliterators$IntWrappingSpliterator                                                    .spliterator                   (object)
        7135754f8     311488 (something else)                                                                                              (somewhere else)               (something else)
        7135c15b8         16 net.mirwaldt.empty.streams.demos.EmptyStream_08_IntStreamFromIntArray_Parallel$$Lambda$110/0x0000000800cce6d8 .streamSupplier.arg$2.arg$1.arg$1 (object)
        7135c15c8        352 (something else)                                                                                              (somewhere else)               (something else)
        7135c1728         16 net.mirwaldt.empty.streams.LazyBuildIntStream$$Lambda$97/0x0000000800cccec8                                   .streamSupplier.arg$2.arg$1    (object)
        7135c1738         24 net.mirwaldt.empty.streams.util.LazyBuildIntStreamUtil$$Lambda$101/0x0000000800ccd798                         .streamSupplier.arg$2          (object)
        7135c1750    6692072 (something else)                                                                                              (somewhere else)               (something else)
        713c23438         24 java.util.ArrayList                                                                                           .spliterator.ph.this$0.sourceSpliterator.this$0 (object)
        713c23450    2027320 (something else)                                                                                              (somewhere else)               (something else)
        713e12388         16 java.lang.Integer                                                                                             .spliterator.ph.this$0.sourceSpliterator.this$0.elementData[0] -3
        713e12398         64 (something else)                                                                                              (somewhere else)               (something else)
        713e123d8         16 java.lang.Integer                                                                                             .spliterator.ph.this$0.sourceSpliterator.this$0.elementData[1] 2
        713e123e8         32 (something else)                                                                                              (somewhere else)               (something else)
        713e12408         16 java.lang.Integer                                                                                             .spliterator.ph.this$0.sourceSpliterator.this$0.elementData[2] 5

Addresses are stable after 1 tries.


------------------------------------------------------------
lazyBuildFilteredMappedParallelNonEmpty:
totalSize=560 bytes
totalCount=21 objects
net.mirwaldt.empty.streams.LazyBuildIntStream@10d59286d object externals:
          ADDRESS       SIZE TYPE                                                                                                          PATH                           VALUE
        713249910         16 net.mirwaldt.empty.streams.demos.EmptyStream_08_IntStreamFromIntArray_Parallel$$Lambda$111/0x0000000800cce920 .streamSupplier.arg$2.arg$1.arg$1 (object)
        713249920        352 (something else)                                                                                              (somewhere else)               (something else)
        713249a80         16 net.mirwaldt.empty.streams.LazyBuildIntStream$$Lambda$100/0x0000000800ccd558                                  .streamSupplier.arg$2.arg$1    (object)
        713249a90         24 net.mirwaldt.empty.streams.util.LazyBuildIntStreamUtil$$Lambda$101/0x0000000800ccd798                         .streamSupplier.arg$2          (object)
        713249aa8     324512 (something else)                                                                                              (somewhere else)               (something else)
        713298e48         24 net.mirwaldt.empty.streams.util.LazyBuildIntStreamUtil$$Lambda$101/0x0000000800ccd798                         .streamSupplier                (object)
        713298e60         24 net.mirwaldt.empty.streams.LazyBuildIntStream                                                                                                (object)
        713298e78    1652584 (something else)                                                                                              (somewhere else)               (something else)
        71342c5e0         16 net.mirwaldt.empty.streams.LazyBuildIntStream$$Lambda$102/0x0000000800ccd9b8                                  .streamSupplier.arg$1          (object)
        71342c5f0      64432 (something else)                                                                                              (somewhere else)               (something else)
        71343c1a0         56 [Ljava.lang.Object;                                                                                           .spliterator.ph.this$0.sourceSpliterator.this$0.elementData [-3, 2, 5, null, null, null, null, null, null, null]
        71343c1d8    1259784 (something else)                                                                                              (somewhere else)               (something else)
        71356fae0         32 java.util.ArrayList$ArrayListSpliterator                                                                      .spliterator.ph.this$0.sourceSpliterator (object)
        71356fb00       9920 (something else)                                                                                              (somewhere else)               (something else)
        7135721c0         16 net.mirwaldt.empty.streams.demos.EmptyStream_08_IntStreamFromIntArray_Parallel$$Lambda$108/0x0000000800cce4c0 .spliterator.ph.val$mapper     (object)
        7135721d0        416 (something else)                                                                                              (somewhere else)               (something else)
        713572370         24 net.mirwaldt.empty.streams.LazyBuildIntStream                                                                 .streamSupplier.arg$2.arg$2.arg$2 (object)
        713572388        248 (something else)                                                                                              (somewhere else)               (something else)
        713572480         56 java.util.stream.ReferencePipeline$Head                                                                       .spliterator.ph.this$0         (object)
        7135724b8         64 java.util.stream.ReferencePipeline$4                                                                          .spliterator.ph                (object)
        7135724f8      11888 (something else)                                                                                              (somewhere else)               (something else)
        713575368         16 java.util.stream.AbstractPipeline$$Lambda$109/0x0000000800c99fb0                                              .spliterator.spliteratorSupplier (object)
        713575378        336 (something else)                                                                                              (somewhere else)               (something else)
        7135754c8         48 java.util.stream.StreamSpliterators$IntWrappingSpliterator                                                    .spliterator                   (object)
        7135754f8     311488 (something else)                                                                                              (somewhere else)               (something else)
        7135c15b8         16 net.mirwaldt.empty.streams.demos.EmptyStream_08_IntStreamFromIntArray_Parallel$$Lambda$110/0x0000000800cce6d8 .streamSupplier.arg$2.arg$2.arg$1.arg$1 (object)
        7135c15c8        352 (something else)                                                                                              (somewhere else)               (something else)
        7135c1728         16 net.mirwaldt.empty.streams.LazyBuildIntStream$$Lambda$97/0x0000000800cccec8                                   .streamSupplier.arg$2.arg$2.arg$1 (object)
        7135c1738         24 net.mirwaldt.empty.streams.util.LazyBuildIntStreamUtil$$Lambda$101/0x0000000800ccd798                         .streamSupplier.arg$2.arg$2    (object)
        7135c1750    6692072 (something else)                                                                                              (somewhere else)               (something else)
        713c23438         24 java.util.ArrayList                                                                                           .spliterator.ph.this$0.sourceSpliterator.this$0 (object)
        713c23450    2027320 (something else)                                                                                              (somewhere else)               (something else)
        713e12388         16 java.lang.Integer                                                                                             .spliterator.ph.this$0.sourceSpliterator.this$0.elementData[0] -3
        713e12398         64 (something else)                                                                                              (somewhere else)               (something else)
        713e123d8         16 java.lang.Integer                                                                                             .spliterator.ph.this$0.sourceSpliterator.this$0.elementData[1] 2
        713e123e8         32 (something else)                                                                                              (somewhere else)               (something else)
        713e12408         16 java.lang.Integer                                                                                             .spliterator.ph.this$0.sourceSpliterator.this$0.elementData[2] 5

Addresses are stable after 1 tries.


------------------------------------------------------------
lazyBuildFilteredMappedParallelNonEmpty.findFirst().orElse(-1)=4
 */
}
