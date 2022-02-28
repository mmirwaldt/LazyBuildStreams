package net.mirwaldt.empty.streams;

import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Function;
import java.util.stream.BaseStream;
import java.util.stream.StreamSupport;

abstract class AbstractLazyBuildStream<T, S extends BaseStream<T, S>, I extends Spliterator<T>>
        implements BaseStream<T, S> {
    protected Spliterator<?> spliterator;
    protected Function[] functions;
    protected boolean isParallel;
    private boolean wasUsedOrClose;

    AbstractLazyBuildStream(Spliterator<?> spliterator, boolean isParallel) {
        if(spliterator != null && !isEmptySpliterator(spliterator)) {
            this.spliterator = spliterator;
        }
        this.functions = null;
        this.isParallel = isParallel;
    }

    AbstractLazyBuildStream(
            Spliterator<?> spliterator,
            Function[] functions,
            boolean isParallel) {
        if(spliterator != null && !isEmptySpliterator(spliterator)) {
            this.spliterator = spliterator;
        }
        this.isParallel = isParallel;
        this.functions = functions;
    }

    AbstractLazyBuildStream(
            Spliterator<?> spliterator,
            Function[] functions,
            Function<BaseStream<?, ?>, BaseStream<?, ?>> function,
            boolean isParallel) {
        if(spliterator != null && !isEmptySpliterator(spliterator)) {
            this.spliterator = spliterator;
            if (function.equals(Function.identity())) {
                this.functions = functions;
            } else {
                this.functions = new Function[(functions == null) ? 1 : functions.length + 1];
                if (functions != null && 0 < functions.length) {
                    System.arraycopy(functions, 0, this.functions, 0, functions.length);
                }
                this.functions[(functions == null) ? 0 : functions.length] = function;
            }
        }
        this.isParallel = isParallel;
    }

    abstract protected S emptyStream();

    @Override
    public boolean isParallel() {
        return isParallel;
    }

    @Override
    public void close() {
        wasUsedOrClose = true;
        spliterator = null;
        functions = null;
    }

    protected static boolean isEmptySpliterator(Spliterator<?> spliterator) {
        if (spliterator instanceof Spliterator.OfInt) {
            return spliterator == Spliterators.emptyIntSpliterator();
        } else if (spliterator instanceof Spliterator.OfLong) {
            return spliterator == Spliterators.emptyLongSpliterator();
        } else if (spliterator instanceof Spliterator.OfDouble) {
            return spliterator == Spliterators.emptyDoubleSpliterator();
        } else {
            return spliterator == Spliterators.emptySpliterator();
        }
    }

    protected S getOnce() {
        if (wasUsedOrClose) {
            throw new IllegalStateException("stream has already been operated upon or closed");
        }
        S stream;
        if (spliterator != null && 0 < spliterator.estimateSize()) {
            BaseStream<?, ?> newStream = createStream();
            stream = (S) decorateStream(newStream);
        } else {
            stream = emptyStream();
        }
        close();
        return stream;
    }

    private BaseStream<?, ?> createStream() {
        if (spliterator instanceof Spliterator.OfInt) {
            return StreamSupport.intStream((Spliterator.OfInt) spliterator, isParallel);
        } else if (spliterator instanceof Spliterator.OfLong) {
            return StreamSupport.longStream((Spliterator.OfLong) spliterator, isParallel);
        } else if (spliterator instanceof Spliterator.OfDouble) {
            return StreamSupport.doubleStream((Spliterator.OfDouble) spliterator, isParallel);
        } else {
            return StreamSupport.stream(spliterator, isParallel);
        }
    }

    private BaseStream<?, ?> decorateStream(BaseStream<?, ?> newStream) {
        for (Function<BaseStream<?, ?>, BaseStream<?, ?>> function : functions) {
            newStream = function.apply(newStream);
        }
        return newStream;
    }

    protected Spliterator<?> getSpliterator() {
        Spliterator<?> newSpliterator = spliterator;
        spliterator = null;
        return newSpliterator;
    }

    protected Function[] functions() {
        Function[] newFunctions = functions;
        functions = null;
        return newFunctions;
    }
}
