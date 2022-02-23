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
public class EmptyStream_10_IntStreamFromIntArray_ParallelStream {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();

        printLineOfSharp(); // ############################################################

        // empty builds
        System.out.println("list=" + list);

        printLineOfSharp(); // ############################################################

        // eager build of empty
        IntStream eagerBuildEmpty = list.parallelStream()
                .mapToInt(Integer::valueOf);
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
        IntStream lazyBuildEmpty = lazyBuildIntStream(lazyBuildGenericStream(list.parallelStream())
                .mapToInt(Integer::valueOf));
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
        IntStream eagerBuildNonEmpty = list.parallelStream()
                .mapToInt(Integer::valueOf);
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
        IntStream lazyBuildNonEmpty = lazyBuildIntStream(lazyBuildGenericStream(list.parallelStream())
                .mapToInt(Integer::valueOf));
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
eagerBuildEmpty:
totalSize=208 bytes
totalCount=6 objects
java.util.stream.ReferencePipeline$4@5f184fc6d object externals:
          ADDRESS       SIZE TYPE                                                                                                               PATH                           VALUE
        713c23258         24 java.util.ArrayList                                                                                                .this$0.sourceSpliterator.this$0 (object)
        713c23270      35800 (something else)                                                                                                   (somewhere else)               (something else)
        713c2be48         32 java.util.ArrayList$ArrayListSpliterator                                                                           .this$0.sourceSpliterator      (object)
        713c2be68         56 java.util.stream.ReferencePipeline$Head                                                                            .this$0                        (object)
        713c2bea0      14792 (something else)                                                                                                   (somewhere else)               (something else)
        713c2f868         16 net.mirwaldt.empty.streams.demos.EmptyStream_10_IntStreamFromIntArray_ParallelStream$$Lambda$15/0x0000000800c01400 .val$mapper                    (object)
        713c2f878       1520 (something else)                                                                                                   (somewhere else)               (something else)
        713c2fe68         64 java.util.stream.ReferencePipeline$4                                                                                                              (object)
        713c2fea8    2063448 (something else)                                                                                                   (somewhere else)               (something else)
        713e27b00         16 [Ljava.lang.Object;                                                                                                .this$0.sourceSpliterator.this$0.elementData []

Addresses are stable after 1 tries.


------------------------------------------------------------
eagerBuildFilteredEmpty:
totalSize=288 bytes
totalCount=8 objects
java.util.stream.IntPipeline$10@6a1aab78d object externals:
          ADDRESS       SIZE TYPE                                                                                                               PATH                           VALUE
        7136bbc98         16 net.mirwaldt.empty.streams.demos.EmptyStream_10_IntStreamFromIntArray_ParallelStream$$Lambda$90/0x0000000800c1f030 .val$predicate                 (object)
        7136bbca8        600 (something else)                                                                                                   (somewhere else)               (something else)
        7136bbf00         64 java.util.stream.IntPipeline$10                                                                                                                   (object)
        7136bbf40    5665560 (something else)                                                                                                   (somewhere else)               (something else)
        713c23258         24 java.util.ArrayList                                                                                                .sourceStage.sourceSpliterator.this$0 (object)
        713c23270      35800 (something else)                                                                                                   (somewhere else)               (something else)
        713c2be48         32 java.util.ArrayList$ArrayListSpliterator                                                                           .sourceStage.sourceSpliterator (object)
        713c2be68         56 java.util.stream.ReferencePipeline$Head                                                                            .sourceStage                   (object)
        713c2bea0      14792 (something else)                                                                                                   (somewhere else)               (something else)
        713c2f868         16 net.mirwaldt.empty.streams.demos.EmptyStream_10_IntStreamFromIntArray_ParallelStream$$Lambda$15/0x0000000800c01400 .this$0.val$mapper             (object)
        713c2f878       1520 (something else)                                                                                                   (somewhere else)               (something else)
        713c2fe68         64 java.util.stream.ReferencePipeline$4                                                                               .this$0                        (object)
        713c2fea8    2063448 (something else)                                                                                                   (somewhere else)               (something else)
        713e27b00         16 [Ljava.lang.Object;                                                                                                .sourceStage.sourceSpliterator.this$0.elementData []

Addresses are stable after 1 tries.


------------------------------------------------------------
eagerBuildFilteredMappedEmpty:
totalSize=368 bytes
totalCount=10 objects
java.util.stream.IntPipeline$4@8646db9d object externals:
          ADDRESS       SIZE TYPE                                                                                                               PATH                           VALUE
        7136bbc98         16 net.mirwaldt.empty.streams.demos.EmptyStream_10_IntStreamFromIntArray_ParallelStream$$Lambda$90/0x0000000800c1f030 .this$0.val$predicate          (object)
        7136bbca8        600 (something else)                                                                                                   (somewhere else)               (something else)
        7136bbf00         64 java.util.stream.IntPipeline$10                                                                                    .this$0                        (object)
        7136bbf40     287672 (something else)                                                                                                   (somewhere else)               (something else)
        7137022f8         16 net.mirwaldt.empty.streams.demos.EmptyStream_10_IntStreamFromIntArray_ParallelStream$$Lambda$91/0x0000000800c1f278 .val$mapper                    (object)
        713702308        608 (something else)                                                                                                   (somewhere else)               (something else)
        713702568         64 java.util.stream.IntPipeline$4                                                                                                                    (object)
        7137025a8    5377200 (something else)                                                                                                   (somewhere else)               (something else)
        713c23258         24 java.util.ArrayList                                                                                                .sourceStage.sourceSpliterator.this$0 (object)
        713c23270      35800 (something else)                                                                                                   (somewhere else)               (something else)
        713c2be48         32 java.util.ArrayList$ArrayListSpliterator                                                                           .sourceStage.sourceSpliterator (object)
        713c2be68         56 java.util.stream.ReferencePipeline$Head                                                                            .sourceStage                   (object)
        713c2bea0      14792 (something else)                                                                                                   (somewhere else)               (something else)
        713c2f868         16 net.mirwaldt.empty.streams.demos.EmptyStream_10_IntStreamFromIntArray_ParallelStream$$Lambda$15/0x0000000800c01400 .sourceStage.nextStage.val$mapper (object)
        713c2f878       1520 (something else)                                                                                                   (somewhere else)               (something else)
        713c2fe68         64 java.util.stream.ReferencePipeline$4                                                                               .sourceStage.nextStage         (object)
        713c2fea8    2063448 (something else)                                                                                                   (somewhere else)               (something else)
        713e27b00         16 [Ljava.lang.Object;                                                                                                .sourceStage.sourceSpliterator.this$0.elementData []

Addresses are stable after 1 tries.


------------------------------------------------------------
eagerBuildFilteredMappedEmpty.findFirst().orElse(-1)=-1
++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
lazyBuildEmpty:
totalSize=56 bytes
totalCount=3 objects
net.mirwaldt.empty.streams.LazyBuildIntStream@32eebfcad object externals:
          ADDRESS       SIZE TYPE                                                     PATH                           VALUE
        713785160         24 net.mirwaldt.empty.streams.LazyBuildIntStream                                           (object)
        713785178      21000 (something else)                                         (somewhere else)               (something else)
        71378a380         16 net.mirwaldt.empty.streams.util.LazyBuildIntStreamUtil$1 .streamSupplier                (object)
        71378a390    7158352 (something else)                                         (somewhere else)               (something else)
        713e5dde0         16 java.util.Spliterators$EmptySpliterator$OfInt            .spliterator                   (object)

Addresses are stable after 1 tries.


------------------------------------------------------------
lazyBuildFilteredEmpty:
totalSize=56 bytes
totalCount=3 objects
net.mirwaldt.empty.streams.LazyBuildIntStream@1fc2b765d object externals:
          ADDRESS       SIZE TYPE                                                                                 PATH                           VALUE
        71379bd48         16 net.mirwaldt.empty.streams.util.LazyBuildIntStreamUtil$$Lambda$98/0x0000000800c88488 .streamSupplier                (object)
        71379bd58        352 (something else)                                                                     (somewhere else)               (something else)
        71379beb8         24 net.mirwaldt.empty.streams.LazyBuildIntStream                                                                       (object)
        71379bed0    7085840 (something else)                                                                     (somewhere else)               (something else)
        713e5dde0         16 java.util.Spliterators$EmptySpliterator$OfInt                                        .spliterator                   (object)

Addresses are stable after 1 tries.


------------------------------------------------------------
lazyBuildFilteredMappedEmpty:
totalSize=112 bytes
totalCount=6 objects
net.mirwaldt.empty.streams.LazyBuildIntStream@17d0685fd object externals:
          ADDRESS       SIZE TYPE                                                                                                               PATH                           VALUE
        71379bd48         16 net.mirwaldt.empty.streams.util.LazyBuildIntStreamUtil$$Lambda$98/0x0000000800c88488                               .streamSupplier.arg$2          (object)
        71379bd58      32696 (something else)                                                                                                   (somewhere else)               (something else)
        7137a3d10         16 net.mirwaldt.empty.streams.demos.EmptyStream_10_IntStreamFromIntArray_ParallelStream$$Lambda$99/0x0000000800c886a0 .streamSupplier.arg$1.arg$1    (object)
        7137a3d20      13744 (something else)                                                                                                   (somewhere else)               (something else)
        7137a72d0         16 net.mirwaldt.empty.streams.LazyBuildIntStream$$Lambda$100/0x0000000800c888d8                                       .streamSupplier.arg$1          (object)
        7137a72e0      12320 (something else)                                                                                                   (somewhere else)               (something else)
        7137aa300         24 net.mirwaldt.empty.streams.util.LazyBuildIntStreamUtil$$Lambda$101/0x0000000800c88b18                              .streamSupplier                (object)
        7137aa318         24 net.mirwaldt.empty.streams.LazyBuildIntStream                                                                                                     (object)
        7137aa330    7027376 (something else)                                                                                                   (somewhere else)               (something else)
        713e5dde0         16 java.util.Spliterators$EmptySpliterator$OfInt                                                                      .spliterator                   (object)

Addresses are stable after 1 tries.


------------------------------------------------------------
lazyBuildFilteredMappedEmpty.findFirst().orElse(-1)=-1
############################################################
list=["-3", "2", "5"]
############################################################
eagerBuildNonEmpty:
totalSize=296 bytes
totalCount=9 objects
java.util.stream.ReferencePipeline$4@150c158d object externals:
          ADDRESS       SIZE TYPE                                                                                                                PATH                           VALUE
        7137db650         56 [Ljava.lang.Object;                                                                                                 .this$0.sourceSpliterator.this$0.elementData [-3, 2, 5, null, null, null, null, null, null, null]
        7137db688      26008 (something else)                                                                                                    (somewhere else)               (something else)
        7137e1c20         32 java.util.ArrayList$ArrayListSpliterator                                                                            .this$0.sourceSpliterator      (object)
        7137e1c40         56 java.util.stream.ReferencePipeline$Head                                                                             .this$0                        (object)
        7137e1c78       9912 (something else)                                                                                                    (somewhere else)               (something else)
        7137e4330         16 net.mirwaldt.empty.streams.demos.EmptyStream_10_IntStreamFromIntArray_ParallelStream$$Lambda$104/0x0000000800c88f70 .val$mapper                    (object)
        7137e4340        352 (something else)                                                                                                    (somewhere else)               (something else)
        7137e44a0         64 java.util.stream.ReferencePipeline$4                                                                                                               (object)
        7137e44e0    4451704 (something else)                                                                                                    (somewhere else)               (something else)
        713c23258         24 java.util.ArrayList                                                                                                 .this$0.sourceSpliterator.this$0 (object)
        713c23270    2027808 (something else)                                                                                                    (somewhere else)               (something else)
        713e12390         16 java.lang.Integer                                                                                                   .this$0.sourceSpliterator.this$0.elementData[0] -3
        713e123a0         64 (something else)                                                                                                    (somewhere else)               (something else)
        713e123e0         16 java.lang.Integer                                                                                                   .this$0.sourceSpliterator.this$0.elementData[1] 2
        713e123f0         32 (something else)                                                                                                    (somewhere else)               (something else)
        713e12410         16 java.lang.Integer                                                                                                   .this$0.sourceSpliterator.this$0.elementData[2] 5

Addresses are stable after 1 tries.


------------------------------------------------------------
eagerBuildFilteredNonEmpty:
totalSize=376 bytes
totalCount=11 objects
java.util.stream.IntPipeline$10@67b467e9d object externals:
          ADDRESS       SIZE TYPE                                                                                                                PATH                           VALUE
        713420038         16 net.mirwaldt.empty.streams.demos.EmptyStream_10_IntStreamFromIntArray_ParallelStream$$Lambda$105/0x0000000800c89188 .val$predicate                 (object)
        713420048        352 (something else)                                                                                                    (somewhere else)               (something else)
        7134201a8         64 java.util.stream.IntPipeline$10                                                                                                                    (object)
        7134201e8    3912808 (something else)                                                                                                    (somewhere else)               (something else)
        7137db650         56 [Ljava.lang.Object;                                                                                                 .sourceStage.sourceSpliterator.this$0.elementData [-3, 2, 5, null, null, null, null, null, null, null]
        7137db688      26008 (something else)                                                                                                    (somewhere else)               (something else)
        7137e1c20         32 java.util.ArrayList$ArrayListSpliterator                                                                            .sourceStage.sourceSpliterator (object)
        7137e1c40         56 java.util.stream.ReferencePipeline$Head                                                                             .sourceStage                   (object)
        7137e1c78       9912 (something else)                                                                                                    (somewhere else)               (something else)
        7137e4330         16 net.mirwaldt.empty.streams.demos.EmptyStream_10_IntStreamFromIntArray_ParallelStream$$Lambda$104/0x0000000800c88f70 .this$0.val$mapper             (object)
        7137e4340        352 (something else)                                                                                                    (somewhere else)               (something else)
        7137e44a0         64 java.util.stream.ReferencePipeline$4                                                                                .this$0                        (object)
        7137e44e0    4451704 (something else)                                                                                                    (somewhere else)               (something else)
        713c23258         24 java.util.ArrayList                                                                                                 .sourceStage.sourceSpliterator.this$0 (object)
        713c23270    2027808 (something else)                                                                                                    (somewhere else)               (something else)
        713e12390         16 java.lang.Integer                                                                                                   .sourceStage.sourceSpliterator.this$0.elementData[0] -3
        713e123a0         64 (something else)                                                                                                    (somewhere else)               (something else)
        713e123e0         16 java.lang.Integer                                                                                                   .sourceStage.sourceSpliterator.this$0.elementData[1] 2
        713e123f0         32 (something else)                                                                                                    (somewhere else)               (something else)
        713e12410         16 java.lang.Integer                                                                                                   .sourceStage.sourceSpliterator.this$0.elementData[2] 5

Addresses are stable after 1 tries.


------------------------------------------------------------
eagerBuildFilteredMappedNonEmpty:
totalSize=456 bytes
totalCount=13 objects
java.util.stream.IntPipeline$4@4d1b0d2ad object externals:
          ADDRESS       SIZE TYPE                                                                                                                PATH                           VALUE
        713420038         16 net.mirwaldt.empty.streams.demos.EmptyStream_10_IntStreamFromIntArray_ParallelStream$$Lambda$105/0x0000000800c89188 .this$0.val$predicate          (object)
        713420048        352 (something else)                                                                                                    (somewhere else)               (something else)
        7134201a8         64 java.util.stream.IntPipeline$10                                                                                     .this$0                        (object)
        7134201e8     277504 (something else)                                                                                                    (somewhere else)               (something else)
        713463de8         16 net.mirwaldt.empty.streams.demos.EmptyStream_10_IntStreamFromIntArray_ParallelStream$$Lambda$106/0x0000000800c893d0 .val$mapper                    (object)
        713463df8        352 (something else)                                                                                                    (somewhere else)               (something else)
        713463f58         64 java.util.stream.IntPipeline$4                                                                                                                     (object)
        713463f98    3634872 (something else)                                                                                                    (somewhere else)               (something else)
        7137db650         56 [Ljava.lang.Object;                                                                                                 .sourceStage.sourceSpliterator.this$0.elementData [-3, 2, 5, null, null, null, null, null, null, null]
        7137db688      26008 (something else)                                                                                                    (somewhere else)               (something else)
        7137e1c20         32 java.util.ArrayList$ArrayListSpliterator                                                                            .sourceStage.sourceSpliterator (object)
        7137e1c40         56 java.util.stream.ReferencePipeline$Head                                                                             .sourceStage                   (object)
        7137e1c78       9912 (something else)                                                                                                    (somewhere else)               (something else)
        7137e4330         16 net.mirwaldt.empty.streams.demos.EmptyStream_10_IntStreamFromIntArray_ParallelStream$$Lambda$104/0x0000000800c88f70 .sourceStage.nextStage.val$mapper (object)
        7137e4340        352 (something else)                                                                                                    (somewhere else)               (something else)
        7137e44a0         64 java.util.stream.ReferencePipeline$4                                                                                .sourceStage.nextStage         (object)
        7137e44e0    4451704 (something else)                                                                                                    (somewhere else)               (something else)
        713c23258         24 java.util.ArrayList                                                                                                 .sourceStage.sourceSpliterator.this$0 (object)
        713c23270    2027808 (something else)                                                                                                    (somewhere else)               (something else)
        713e12390         16 java.lang.Integer                                                                                                   .sourceStage.sourceSpliterator.this$0.elementData[0] -3
        713e123a0         64 (something else)                                                                                                    (somewhere else)               (something else)
        713e123e0         16 java.lang.Integer                                                                                                   .sourceStage.sourceSpliterator.this$0.elementData[1] 2
        713e123f0         32 (something else)                                                                                                    (somewhere else)               (something else)
        713e12410         16 java.lang.Integer                                                                                                   .sourceStage.sourceSpliterator.this$0.elementData[2] 5

Addresses are stable after 1 tries.


------------------------------------------------------------
eagerBuildFilteredMappedNonEmpty.findFirst().orElse("?")=4
++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
lazyBuildNonEmpty:
totalSize=384 bytes
totalCount=12 objects
net.mirwaldt.empty.streams.LazyBuildIntStream@548ad73bd object externals:
          ADDRESS       SIZE TYPE                                                                                                                PATH                           VALUE
        7134bbc50         32 java.util.ArrayList$ArrayListSpliterator                                                                            .spliterator.ph.this$0.sourceSpliterator (object)
        7134bbc70       9992 (something else)                                                                                                    (somewhere else)               (something else)
        7134be378         16 net.mirwaldt.empty.streams.demos.EmptyStream_10_IntStreamFromIntArray_ParallelStream$$Lambda$107/0x0000000800c89608 .spliterator.ph.val$mapper     (object)
        7134be388        416 (something else)                                                                                                    (somewhere else)               (something else)
        7134be528         24 net.mirwaldt.empty.streams.LazyBuildIntStream                                                                                                      (object)
        7134be540        248 (something else)                                                                                                    (somewhere else)               (something else)
        7134be638         56 java.util.stream.ReferencePipeline$Head                                                                             .spliterator.ph.this$0         (object)
        7134be670         64 java.util.stream.ReferencePipeline$4                                                                                .spliterator.ph                (object)
        7134be6b0      11888 (something else)                                                                                                    (somewhere else)               (something else)
        7134c1520         16 java.util.stream.AbstractPipeline$$Lambda$108/0x0000000800cdc160                                                    .spliterator.spliteratorSupplier (object)
        7134c1530        336 (something else)                                                                                                    (somewhere else)               (something else)
        7134c1680         48 java.util.stream.StreamSpliterators$IntWrappingSpliterator                                                          .spliterator                   (object)
        7134c16b0    3252128 (something else)                                                                                                    (somewhere else)               (something else)
        7137db650         56 [Ljava.lang.Object;                                                                                                 .spliterator.ph.this$0.sourceSpliterator.this$0.elementData [-3, 2, 5, null, null, null, null, null, null, null]
        7137db688    4488144 (something else)                                                                                                    (somewhere else)               (something else)
        713c23258         24 java.util.ArrayList                                                                                                 .spliterator.ph.this$0.sourceSpliterator.this$0 (object)
        713c23270    2027808 (something else)                                                                                                    (somewhere else)               (something else)
        713e12390         16 java.lang.Integer                                                                                                   .spliterator.ph.this$0.sourceSpliterator.this$0.elementData[0] -3
        713e123a0         64 (something else)                                                                                                    (somewhere else)               (something else)
        713e123e0         16 java.lang.Integer                                                                                                   .spliterator.ph.this$0.sourceSpliterator.this$0.elementData[1] 2
        713e123f0         32 (something else)                                                                                                    (somewhere else)               (something else)
        713e12410         16 java.lang.Integer                                                                                                   .spliterator.ph.this$0.sourceSpliterator.this$0.elementData[2] 5

Addresses are stable after 1 tries.


------------------------------------------------------------
lazyBuildFilteredNonEmpty:
totalSize=464 bytes
totalCount=16 objects
net.mirwaldt.empty.streams.LazyBuildIntStream@2f0a87b3d object externals:
          ADDRESS       SIZE TYPE                                                                                                                PATH                           VALUE
        7134bbc50         32 java.util.ArrayList$ArrayListSpliterator                                                                            .spliterator.ph.this$0.sourceSpliterator (object)
        7134bbc70       9992 (something else)                                                                                                    (somewhere else)               (something else)
        7134be378         16 net.mirwaldt.empty.streams.demos.EmptyStream_10_IntStreamFromIntArray_ParallelStream$$Lambda$107/0x0000000800c89608 .spliterator.ph.val$mapper     (object)
        7134be388        416 (something else)                                                                                                    (somewhere else)               (something else)
        7134be528         24 net.mirwaldt.empty.streams.LazyBuildIntStream                                                                       .streamSupplier.arg$2          (object)
        7134be540        248 (something else)                                                                                                    (somewhere else)               (something else)
        7134be638         56 java.util.stream.ReferencePipeline$Head                                                                             .spliterator.ph.this$0         (object)
        7134be670         64 java.util.stream.ReferencePipeline$4                                                                                .spliterator.ph                (object)
        7134be6b0      11888 (something else)                                                                                                    (somewhere else)               (something else)
        7134c1520         16 java.util.stream.AbstractPipeline$$Lambda$108/0x0000000800cdc160                                                    .spliterator.spliteratorSupplier (object)
        7134c1530        336 (something else)                                                                                                    (somewhere else)               (something else)
        7134c1680         48 java.util.stream.StreamSpliterators$IntWrappingSpliterator                                                          .spliterator                   (object)
        7134c16b0     773800 (something else)                                                                                                    (somewhere else)               (something else)
        71357e558         16 net.mirwaldt.empty.streams.demos.EmptyStream_10_IntStreamFromIntArray_ParallelStream$$Lambda$109/0x0000000800c89820 .streamSupplier.arg$1.arg$1    (object)
        71357e568        352 (something else)                                                                                                    (somewhere else)               (something else)
        71357e6c8         16 net.mirwaldt.empty.streams.LazyBuildIntStream$$Lambda$97/0x0000000800c88248                                         .streamSupplier.arg$1          (object)
        71357e6d8         24 net.mirwaldt.empty.streams.util.LazyBuildIntStreamUtil$$Lambda$101/0x0000000800c88b18                               .streamSupplier                (object)
        71357e6f0         24 net.mirwaldt.empty.streams.LazyBuildIntStream                                                                                                      (object)
        71357e708    2477896 (something else)                                                                                                    (somewhere else)               (something else)
        7137db650         56 [Ljava.lang.Object;                                                                                                 .spliterator.ph.this$0.sourceSpliterator.this$0.elementData [-3, 2, 5, null, null, null, null, null, null, null]
        7137db688    4488144 (something else)                                                                                                    (somewhere else)               (something else)
        713c23258         24 java.util.ArrayList                                                                                                 .spliterator.ph.this$0.sourceSpliterator.this$0 (object)
        713c23270    2027808 (something else)                                                                                                    (somewhere else)               (something else)
        713e12390         16 java.lang.Integer                                                                                                   .spliterator.ph.this$0.sourceSpliterator.this$0.elementData[0] -3
        713e123a0         64 (something else)                                                                                                    (somewhere else)               (something else)
        713e123e0         16 java.lang.Integer                                                                                                   .spliterator.ph.this$0.sourceSpliterator.this$0.elementData[1] 2
        713e123f0         32 (something else)                                                                                                    (somewhere else)               (something else)
        713e12410         16 java.lang.Integer                                                                                                   .spliterator.ph.this$0.sourceSpliterator.this$0.elementData[2] 5

Addresses are stable after 1 tries.


------------------------------------------------------------
lazyBuildFilteredMappedNonEmpty:
totalSize=520 bytes
totalCount=19 objects
net.mirwaldt.empty.streams.LazyBuildIntStream@31f924f5d object externals:
          ADDRESS       SIZE TYPE                                                                                                                PATH                           VALUE
        7134bbc50         32 java.util.ArrayList$ArrayListSpliterator                                                                            .spliterator.ph.this$0.sourceSpliterator (object)
        7134bbc70       9992 (something else)                                                                                                    (somewhere else)               (something else)
        7134be378         16 net.mirwaldt.empty.streams.demos.EmptyStream_10_IntStreamFromIntArray_ParallelStream$$Lambda$107/0x0000000800c89608 .spliterator.ph.val$mapper     (object)
        7134be388        416 (something else)                                                                                                    (somewhere else)               (something else)
        7134be528         24 net.mirwaldt.empty.streams.LazyBuildIntStream                                                                       .streamSupplier.arg$2.arg$2    (object)
        7134be540        248 (something else)                                                                                                    (somewhere else)               (something else)
        7134be638         56 java.util.stream.ReferencePipeline$Head                                                                             .spliterator.ph.this$0         (object)
        7134be670         64 java.util.stream.ReferencePipeline$4                                                                                .spliterator.ph                (object)
        7134be6b0      11888 (something else)                                                                                                    (somewhere else)               (something else)
        7134c1520         16 java.util.stream.AbstractPipeline$$Lambda$108/0x0000000800cdc160                                                    .spliterator.spliteratorSupplier (object)
        7134c1530        336 (something else)                                                                                                    (somewhere else)               (something else)
        7134c1680         48 java.util.stream.StreamSpliterators$IntWrappingSpliterator                                                          .spliterator                   (object)
        7134c16b0     773800 (something else)                                                                                                    (somewhere else)               (something else)
        71357e558         16 net.mirwaldt.empty.streams.demos.EmptyStream_10_IntStreamFromIntArray_ParallelStream$$Lambda$109/0x0000000800c89820 .streamSupplier.arg$2.arg$1.arg$1 (object)
        71357e568        352 (something else)                                                                                                    (somewhere else)               (something else)
        71357e6c8         16 net.mirwaldt.empty.streams.LazyBuildIntStream$$Lambda$97/0x0000000800c88248                                         .streamSupplier.arg$2.arg$1    (object)
        71357e6d8         24 net.mirwaldt.empty.streams.util.LazyBuildIntStreamUtil$$Lambda$101/0x0000000800c88b18                               .streamSupplier.arg$2          (object)
        71357e6f0     309320 (something else)                                                                                                    (somewhere else)               (something else)
        7135c9f38         16 net.mirwaldt.empty.streams.demos.EmptyStream_10_IntStreamFromIntArray_ParallelStream$$Lambda$110/0x0000000800c89a68 .streamSupplier.arg$1.arg$1    (object)
        7135c9f48        352 (something else)                                                                                                    (somewhere else)               (something else)
        7135ca0a8         16 net.mirwaldt.empty.streams.LazyBuildIntStream$$Lambda$100/0x0000000800c888d8                                        .streamSupplier.arg$1          (object)
        7135ca0b8         24 net.mirwaldt.empty.streams.util.LazyBuildIntStreamUtil$$Lambda$101/0x0000000800c88b18                               .streamSupplier                (object)
        7135ca0d0         24 net.mirwaldt.empty.streams.LazyBuildIntStream                                                                                                      (object)
        7135ca0e8    2168168 (something else)                                                                                                    (somewhere else)               (something else)
        7137db650         56 [Ljava.lang.Object;                                                                                                 .spliterator.ph.this$0.sourceSpliterator.this$0.elementData [-3, 2, 5, null, null, null, null, null, null, null]
        7137db688    4488144 (something else)                                                                                                    (somewhere else)               (something else)
        713c23258         24 java.util.ArrayList                                                                                                 .spliterator.ph.this$0.sourceSpliterator.this$0 (object)
        713c23270    2027808 (something else)                                                                                                    (somewhere else)               (something else)
        713e12390         16 java.lang.Integer                                                                                                   .spliterator.ph.this$0.sourceSpliterator.this$0.elementData[0] -3
        713e123a0         64 (something else)                                                                                                    (somewhere else)               (something else)
        713e123e0         16 java.lang.Integer                                                                                                   .spliterator.ph.this$0.sourceSpliterator.this$0.elementData[1] 2
        713e123f0         32 (something else)                                                                                                    (somewhere else)               (something else)
        713e12410         16 java.lang.Integer                                                                                                   .spliterator.ph.this$0.sourceSpliterator.this$0.elementData[2] 5

Addresses are stable after 1 tries.


------------------------------------------------------------
lazyBuildFilteredMappedNonEmpty.findFirst().orElse(-1)=4
 */
}
