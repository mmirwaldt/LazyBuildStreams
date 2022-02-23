package net.mirwaldt.empty.streams.demos;

import java.util.stream.IntStream;

import static net.mirwaldt.empty.streams.LazyBuildStreamFactory.lazyBuildIntStream;
import static net.mirwaldt.empty.streams.demos.PrintUtil.*;

/**
 * Use with VM option -Djol.magicFieldOffset=true
 */
public class EmptyStream_07_DefaultEmptyIntStream_ParallelOp {
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

        IntStream eagerBuildFilteredMappedParallelEmpty = eagerBuildFilteredMappedEmpty.parallel();
        printStatistics("eagerBuildFilteredMappedParallelEmpty:", eagerBuildFilteredMappedParallelEmpty);

        printLineOfMinus();

        System.out.println("eagerBuildFilteredMappedParallelEmpty.findFirst().orElse(-1)="
                + eagerBuildFilteredMappedParallelEmpty.findFirst().orElse(-1));

        
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

        IntStream lazyBuildFilteredMappedParallelEmpty = lazyBuildFilteredMappedEmpty.parallel();
        printStatistics("lazyBuildFilteredMappedParallelEmpty:", lazyBuildFilteredMappedParallelEmpty);

        printLineOfMinus();

        System.out.println("lazyBuildFilteredMappedParallelEmpty.findFirst().orElse(-1)="
                + lazyBuildFilteredMappedParallelEmpty.findFirst().orElse(-1));
    }

// Output:
/*
eagerBuildEmpty:
totalSize=72 bytes
totalCount=2 objects
java.util.stream.IntPipeline$Head@5fd0d5aed object externals:
          ADDRESS       SIZE TYPE                                          PATH                           VALUE
        713c22840         56 java.util.stream.IntPipeline$Head                                            (object)
        713c22878    2340416 (something else)                              (somewhere else)               (something else)
        713e5deb8         16 java.util.Spliterators$EmptySpliterator$OfInt .sourceSpliterator             (object)

Addresses are stable after 1 tries.


------------------------------------------------------------
eagerBuildFilteredEmpty:
totalSize=152 bytes
totalCount=4 objects
java.util.stream.IntPipeline$10@eafc191d object externals:
          ADDRESS       SIZE TYPE                                                                                                PATH                           VALUE
        71368e1a0         16 net.mirwaldt.empty.streams.demos.EmptyStream_07_DefaultEmptyIntStream$$Lambda$89/0x0000000800cc6620 .val$predicate                 (object)
        71368e1b0        352 (something else)                                                                                    (somewhere else)               (something else)
        71368e310         64 java.util.stream.IntPipeline$10                                                                                                    (object)
        71368e350    5850352 (something else)                                                                                    (somewhere else)               (something else)
        713c22840         56 java.util.stream.IntPipeline$Head                                                                   .this$0                        (object)
        713c22878    2340416 (something else)                                                                                    (somewhere else)               (something else)
        713e5deb8         16 java.util.Spliterators$EmptySpliterator$OfInt                                                       .this$0.sourceSpliterator      (object)

Addresses are stable after 1 tries.


------------------------------------------------------------
eagerBuildFilteredMappedEmpty:
totalSize=232 bytes
totalCount=6 objects
java.util.stream.IntPipeline$4@74ad1f1fd object externals:
          ADDRESS       SIZE TYPE                                                                                                PATH                           VALUE
        71368e1a0         16 net.mirwaldt.empty.streams.demos.EmptyStream_07_DefaultEmptyIntStream$$Lambda$89/0x0000000800cc6620 .this$0.val$predicate          (object)
        71368e1b0        352 (something else)                                                                                    (somewhere else)               (something else)
        71368e310         64 java.util.stream.IntPipeline$10                                                                     .this$0                        (object)
        71368e350     186384 (something else)                                                                                    (somewhere else)               (something else)
        7136bbb60         16 net.mirwaldt.empty.streams.demos.EmptyStream_07_DefaultEmptyIntStream$$Lambda$90/0x0000000800cc6868 .val$mapper                    (object)
        7136bbb70        608 (something else)                                                                                    (somewhere else)               (something else)
        7136bbdd0         64 java.util.stream.IntPipeline$4                                                                                                     (object)
        7136bbe10    5663280 (something else)                                                                                    (somewhere else)               (something else)
        713c22840         56 java.util.stream.IntPipeline$Head                                                                   .sourceStage                   (object)
        713c22878    2340416 (something else)                                                                                    (somewhere else)               (something else)
        713e5deb8         16 java.util.Spliterators$EmptySpliterator$OfInt                                                       .sourceStage.sourceSpliterator (object)

Addresses are stable after 1 tries.


------------------------------------------------------------
eagerBuildFilteredMappedParallelEmpty:
totalSize=232 bytes
totalCount=6 objects
java.util.stream.IntPipeline$4@74ad1f1fd object externals:
          ADDRESS       SIZE TYPE                                                                                                PATH                           VALUE
        71368e1a0         16 net.mirwaldt.empty.streams.demos.EmptyStream_07_DefaultEmptyIntStream$$Lambda$89/0x0000000800cc6620 .this$0.val$predicate          (object)
        71368e1b0        352 (something else)                                                                                    (somewhere else)               (something else)
        71368e310         64 java.util.stream.IntPipeline$10                                                                     .this$0                        (object)
        71368e350     186384 (something else)                                                                                    (somewhere else)               (something else)
        7136bbb60         16 net.mirwaldt.empty.streams.demos.EmptyStream_07_DefaultEmptyIntStream$$Lambda$90/0x0000000800cc6868 .val$mapper                    (object)
        7136bbb70        608 (something else)                                                                                    (somewhere else)               (something else)
        7136bbdd0         64 java.util.stream.IntPipeline$4                                                                                                     (object)
        7136bbe10    5663280 (something else)                                                                                    (somewhere else)               (something else)
        713c22840         56 java.util.stream.IntPipeline$Head                                                                   .sourceStage                   (object)
        713c22878    2340416 (something else)                                                                                    (somewhere else)               (something else)
        713e5deb8         16 java.util.Spliterators$EmptySpliterator$OfInt                                                       .sourceStage.sourceSpliterator (object)

Addresses are stable after 1 tries.


------------------------------------------------------------
eagerBuildFilteredMappedParallelEmpty.findFirst().orElse(-1)=-1
++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
lazyBuildEmpty:
totalSize=56 bytes
totalCount=3 objects
net.mirwaldt.empty.streams.LazyBuildIntStream@42eca56ed object externals:
          ADDRESS       SIZE TYPE                                                     PATH                           VALUE
        71373fda0         24 net.mirwaldt.empty.streams.LazyBuildIntStream                                           (object)
        71373fdb8      22384 (something else)                                         (somewhere else)               (something else)
        713745528         16 net.mirwaldt.empty.streams.util.LazyBuildIntStreamUtil$1 .streamSupplier                (object)
        713745538    7440768 (something else)                                         (somewhere else)               (something else)
        713e5deb8         16 java.util.Spliterators$EmptySpliterator$OfInt            .spliterator                   (object)

Addresses are stable after 1 tries.


------------------------------------------------------------
lazyBuildFilteredEmpty:
totalSize=56 bytes
totalCount=3 objects
net.mirwaldt.empty.streams.LazyBuildIntStream@33723e30d object externals:
          ADDRESS       SIZE TYPE                                                                                 PATH                           VALUE
        713753ba0         16 net.mirwaldt.empty.streams.util.LazyBuildIntStreamUtil$$Lambda$93/0x0000000800ccc000 .streamSupplier                (object)
        713753bb0        352 (something else)                                                                     (somewhere else)               (something else)
        713753d10         24 net.mirwaldt.empty.streams.LazyBuildIntStream                                                                       (object)
        713753d28    7381392 (something else)                                                                     (somewhere else)               (something else)
        713e5deb8         16 java.util.Spliterators$EmptySpliterator$OfInt                                        .spliterator                   (object)

Addresses are stable after 1 tries.


------------------------------------------------------------
lazyBuildFilteredMappedEmpty:
totalSize=112 bytes
totalCount=6 objects
net.mirwaldt.empty.streams.LazyBuildIntStream@cb0ed20d object externals:
          ADDRESS       SIZE TYPE                                                                                                PATH                           VALUE
        713753ba0         16 net.mirwaldt.empty.streams.util.LazyBuildIntStreamUtil$$Lambda$93/0x0000000800ccc000                .streamSupplier.arg$2          (object)
        713753bb0      32288 (something else)                                                                                    (somewhere else)               (something else)
        71375b9d0         16 net.mirwaldt.empty.streams.demos.EmptyStream_07_DefaultEmptyIntStream$$Lambda$94/0x0000000800ccc218 .streamSupplier.arg$1.arg$1    (object)
        71375b9e0      13720 (something else)                                                                                    (somewhere else)               (something else)
        71375ef78         16 net.mirwaldt.empty.streams.LazyBuildIntStream$$Lambda$95/0x0000000800ccc450                         .streamSupplier.arg$1          (object)
        71375ef88      14560 (something else)                                                                                    (somewhere else)               (something else)
        713762868         24 net.mirwaldt.empty.streams.util.LazyBuildIntStreamUtil$$Lambda$96/0x0000000800ccc690                .streamSupplier                (object)
        713762880         24 net.mirwaldt.empty.streams.LazyBuildIntStream                                                                                      (object)
        713762898    7321120 (something else)                                                                                    (somewhere else)               (something else)
        713e5deb8         16 java.util.Spliterators$EmptySpliterator$OfInt                                                       .spliterator                   (object)

Addresses are stable after 1 tries.


------------------------------------------------------------
lazyBuildFilteredMappedParallelEmpty:
totalSize=168 bytes
totalCount=9 objects
net.mirwaldt.empty.streams.LazyBuildIntStream@c81cdd1d object externals:
          ADDRESS       SIZE TYPE                                                                                                PATH                           VALUE
        713753ba0         16 net.mirwaldt.empty.streams.util.LazyBuildIntStreamUtil$$Lambda$93/0x0000000800ccc000                .streamSupplier.arg$2.arg$2    (object)
        713753bb0      32288 (something else)                                                                                    (somewhere else)               (something else)
        71375b9d0         16 net.mirwaldt.empty.streams.demos.EmptyStream_07_DefaultEmptyIntStream$$Lambda$94/0x0000000800ccc218 .streamSupplier.arg$2.arg$1.arg$1 (object)
        71375b9e0      13720 (something else)                                                                                    (somewhere else)               (something else)
        71375ef78         16 net.mirwaldt.empty.streams.LazyBuildIntStream$$Lambda$95/0x0000000800ccc450                         .streamSupplier.arg$2.arg$1    (object)
        71375ef88      14560 (something else)                                                                                    (somewhere else)               (something else)
        713762868         24 net.mirwaldt.empty.streams.util.LazyBuildIntStreamUtil$$Lambda$96/0x0000000800ccc690                .streamSupplier.arg$2          (object)
        713762880     206560 (something else)                                                                                    (somewhere else)               (something else)
        713794f60         16 net.mirwaldt.empty.streams.demos.EmptyStream_07_DefaultEmptyIntStream$$Lambda$97/0x0000000800ccc8b0 .streamSupplier.arg$1.arg$1    (object)
        713794f70        352 (something else)                                                                                    (somewhere else)               (something else)
        7137950d0         16 net.mirwaldt.empty.streams.LazyBuildIntStream$$Lambda$95/0x0000000800ccc450                         .streamSupplier.arg$1          (object)
        7137950e0         24 net.mirwaldt.empty.streams.util.LazyBuildIntStreamUtil$$Lambda$96/0x0000000800ccc690                .streamSupplier                (object)
        7137950f8         24 net.mirwaldt.empty.streams.LazyBuildIntStream                                                                                      (object)
        713795110    7114152 (something else)                                                                                    (somewhere else)               (something else)
        713e5deb8         16 java.util.Spliterators$EmptySpliterator$OfInt                                                       .spliterator                   (object)

Addresses are stable after 1 tries.


------------------------------------------------------------
lazyBuildFilteredMappedParallelEmpty.findFirst().orElse(-1)=-1

Process finished with exit code 0
 */
}
