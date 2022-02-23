package net.mirwaldt.empty.streams.demos;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static net.mirwaldt.empty.streams.LazyBuildStreamFactory.lazyBuildGenericStream;
import static net.mirwaldt.empty.streams.LazyBuildStreamFactory.lazyBuildIntStream;
import static net.mirwaldt.empty.streams.demos.PrintUtil.*;

/**
 * Use with VM option -Djol.magicFieldOffset=true
 */
public class EmptyStream_03_DefaultEmptyIntStream {
    public static void main(String[] args) {
        // eager build
        IntStream eagerBuildEmpty = IntStream.empty();
        printStatistics("eagerBuildEmpty:", eagerBuildEmpty);
        
        printLineOfMinus();
        
        IntStream eagerBuildFilteredEmpty = eagerBuildEmpty.filter(i -> 0 < i);
        printStatistics("eagerBuildFilteredEmpty:", eagerBuildFilteredEmpty);

        printLineOfMinus();

        IntStream eagerBuildFilteredMappedEmpty = eagerBuildFilteredEmpty.map(i -> 2 * i);
        printStatistics("eagerBuildFilteredMappedEmpty:", eagerBuildFilteredMappedEmpty);

        printLineOfMinus();

        System.out.println("eagerBuildFilteredMappedEmpty.findFirst().orElse(-1)="
                + eagerBuildFilteredMappedEmpty.findFirst().orElse(-1));

        
        printLineOfPlus();

        
        // lazy build
        IntStream lazyBuildEmpty = lazyBuildIntStream(IntStream.empty());
        printStatistics("lazyBuildEmpty:", lazyBuildEmpty);

        printLineOfMinus();

        IntStream lazyBuildFilteredEmpty = lazyBuildEmpty.filter(i -> 0 < i);
        printStatistics("lazyBuildFilteredEmpty:", lazyBuildFilteredEmpty);

        printLineOfMinus();

        IntStream lazyBuildFilteredMappedEmpty = lazyBuildFilteredEmpty.map(i -> 2 * i);
        printStatistics("lazyBuildFilteredMappedEmpty:", lazyBuildFilteredMappedEmpty);

        printLineOfMinus();

        System.out.println("lazyBuildFilteredMappedEmpty.findFirst().orElse(-1)="
                + lazyBuildFilteredMappedEmpty.findFirst().orElse(-1));
    }

// Output:
/*
eagerBuildEmpty:
totalSize=72 bytes
totalCount=2 objects
java.util.stream.IntPipeline$Head@5fd0d5aed object externals:
          ADDRESS       SIZE TYPE                                          PATH                           VALUE
        713c226a0         56 java.util.stream.IntPipeline$Head                                            (object)
        713c226d8    2340832 (something else)                              (somewhere else)               (something else)
        713e5deb8         16 java.util.Spliterators$EmptySpliterator$OfInt .sourceSpliterator             (object)

Addresses are stable after 1 tries.


------------------------------------------------------------
eagerBuildFilteredEmpty:
totalSize=152 bytes
totalCount=4 objects
java.util.stream.IntPipeline$10@eafc191d object externals:
          ADDRESS       SIZE TYPE                                                                                                PATH                           VALUE
        71368ddf0         16 net.mirwaldt.empty.streams.demos.EmptyStream_03_DefaultEmptyIntStream$$Lambda$89/0x0000000800cc6620 .val$predicate                 (object)
        71368de00        352 (something else)                                                                                    (somewhere else)               (something else)
        71368df60         64 java.util.stream.IntPipeline$10                                                                                                    (object)
        71368dfa0    5850880 (something else)                                                                                    (somewhere else)               (something else)
        713c226a0         56 java.util.stream.IntPipeline$Head                                                                   .this$0                        (object)
        713c226d8    2340832 (something else)                                                                                    (somewhere else)               (something else)
        713e5deb8         16 java.util.Spliterators$EmptySpliterator$OfInt                                                       .this$0.sourceSpliterator      (object)

Addresses are stable after 1 tries.


------------------------------------------------------------
eagerBuildFilteredMappedEmpty:
totalSize=232 bytes
totalCount=6 objects
java.util.stream.IntPipeline$4@74ad1f1fd object externals:
          ADDRESS       SIZE TYPE                                                                                                PATH                           VALUE
        71368ddf0         16 net.mirwaldt.empty.streams.demos.EmptyStream_03_DefaultEmptyIntStream$$Lambda$89/0x0000000800cc6620 .this$0.val$predicate          (object)
        71368de00        352 (something else)                                                                                    (somewhere else)               (something else)
        71368df60         64 java.util.stream.IntPipeline$10                                                                     .this$0                        (object)
        71368dfa0     185352 (something else)                                                                                    (somewhere else)               (something else)
        7136bb3a8         16 net.mirwaldt.empty.streams.demos.EmptyStream_03_DefaultEmptyIntStream$$Lambda$90/0x0000000800cc6868 .val$mapper                    (object)
        7136bb3b8        608 (something else)                                                                                    (somewhere else)               (something else)
        7136bb618         64 java.util.stream.IntPipeline$4                                                                                                     (object)
        7136bb658    5664840 (something else)                                                                                    (somewhere else)               (something else)
        713c226a0         56 java.util.stream.IntPipeline$Head                                                                   .sourceStage                   (object)
        713c226d8    2340832 (something else)                                                                                    (somewhere else)               (something else)
        713e5deb8         16 java.util.Spliterators$EmptySpliterator$OfInt                                                       .sourceStage.sourceSpliterator (object)

Addresses are stable after 1 tries.


------------------------------------------------------------
eagerBuildFilteredMappedEmpty.findFirst().orElse(-1)=-1
++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
lazyBuildEmpty:
totalSize=56 bytes
totalCount=3 objects
net.mirwaldt.empty.streams.LazyBuildIntStream@4671e53bd object externals:
          ADDRESS       SIZE TYPE                                                     PATH                           VALUE
        713708450         24 net.mirwaldt.empty.streams.LazyBuildIntStream                                           (object)
        713708468      22384 (something else)                                         (somewhere else)               (something else)
        71370dbd8         16 net.mirwaldt.empty.streams.util.LazyBuildIntStreamUtil$1 .streamSupplier                (object)
        71370dbe8    7668432 (something else)                                         (somewhere else)               (something else)
        713e5deb8         16 java.util.Spliterators$EmptySpliterator$OfInt            .spliterator                   (object)

Addresses are stable after 1 tries.


------------------------------------------------------------
lazyBuildFilteredEmpty:
totalSize=56 bytes
totalCount=3 objects
net.mirwaldt.empty.streams.LazyBuildIntStream@192d3247d object externals:
          ADDRESS       SIZE TYPE                                                                                 PATH                           VALUE
        71371bf40         16 net.mirwaldt.empty.streams.util.LazyBuildIntStreamUtil$$Lambda$93/0x0000000800ccc000 .streamSupplier                (object)
        71371bf50        352 (something else)                                                                     (somewhere else)               (something else)
        71371c0b0         24 net.mirwaldt.empty.streams.LazyBuildIntStream                                                                       (object)
        71371c0c8    7609840 (something else)                                                                     (somewhere else)               (something else)
        713e5deb8         16 java.util.Spliterators$EmptySpliterator$OfInt                                        .spliterator                   (object)

Addresses are stable after 1 tries.


------------------------------------------------------------
lazyBuildFilteredMappedEmpty:
totalSize=112 bytes
totalCount=6 objects
net.mirwaldt.empty.streams.LazyBuildIntStream@64f6106cd object externals:
          ADDRESS       SIZE TYPE                                                                                                PATH                           VALUE
        71371bf40         16 net.mirwaldt.empty.streams.util.LazyBuildIntStreamUtil$$Lambda$93/0x0000000800ccc000                .streamSupplier.arg$2          (object)
        71371bf50      32288 (something else)                                                                                    (somewhere else)               (something else)
        713723d70         16 net.mirwaldt.empty.streams.demos.EmptyStream_03_DefaultEmptyIntStream$$Lambda$94/0x0000000800ccc218 .streamSupplier.arg$1.arg$1    (object)
        713723d80      13720 (something else)                                                                                    (somewhere else)               (something else)
        713727318         16 net.mirwaldt.empty.streams.LazyBuildIntStream$$Lambda$95/0x0000000800ccc450                         .streamSupplier.arg$1          (object)
        713727328      14560 (something else)                                                                                    (somewhere else)               (something else)
        71372ac08         24 net.mirwaldt.empty.streams.util.LazyBuildIntStreamUtil$$Lambda$96/0x0000000800ccc690                .streamSupplier                (object)
        71372ac20         24 net.mirwaldt.empty.streams.LazyBuildIntStream                                                                                      (object)
        71372ac38    7549568 (something else)                                                                                    (somewhere else)               (something else)
        713e5deb8         16 java.util.Spliterators$EmptySpliterator$OfInt                                                       .spliterator                   (object)

Addresses are stable after 1 tries.


------------------------------------------------------------
lazyBuildFilteredMappedEmpty.findFirst().orElse(-1)=-1
 */
}
