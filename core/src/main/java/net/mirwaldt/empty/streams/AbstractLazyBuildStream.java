/*
 * Copyright (c) 2022, Michael Mirwaldt. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * <a rel="license" href="http://creativecommons.org/licenses/by-nd/4.0/">
 * <img alt="Creative Commons License" style="border-width:0" src="https://i.creativecommons.org/l/by-nd/4.0/88x31.png" />
 * </a><br /><span xmlns:dct="http://purl.org/dc/terms/" property="dct:title">
 * Lazy build streams</span> is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-nd/4.0/">
 * Creative Commons Attribution-NoDerivatives 4.0 International License</a>.
 */

package net.mirwaldt.empty.streams;

import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Function;
import java.util.stream.BaseStream;
import java.util.stream.StreamSupport;

abstract class AbstractLazyBuildStream<T, S extends BaseStream<T, S>> implements BaseStream<T, S> {
    protected Spliterator<?> spliterator;
    protected Function[] functions;
    protected boolean isParallel;
    private boolean usedOrClosed;

    AbstractLazyBuildStream(S stream) {
        if (stream instanceof AbstractLazyBuildStream lazyBuildStream) {
            init(lazyBuildStream.spliterator, lazyBuildStream.functions, lazyBuildStream.isParallel);
        } else {
            init(stream.spliterator(), stream.isParallel());
        }
    }

    AbstractLazyBuildStream(Spliterator<?> spliterator, boolean isParallel) {
        init(spliterator, isParallel);
    }

    AbstractLazyBuildStream(
            Spliterator<?> spliterator,
            Function[] functions,
            Function<BaseStream<?, ?>, BaseStream<?, ?>> function,
            boolean isParallel) {
        if (spliterator != null && isNonEmptySpliterator(spliterator)) {
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

    private void init(Spliterator<?> spliterator, Function[] functions, boolean isParallel) {
        if (spliterator != null && isNonEmptySpliterator(spliterator)) {
            this.spliterator = spliterator;
            this.functions = functions;
        }
        this.isParallel = isParallel;
    }

    private void init(Spliterator<?> spliterator, boolean isParallel) {
        if (spliterator != null && isNonEmptySpliterator(spliterator)) {
            this.spliterator = spliterator;
        }
        this.functions = null;
        this.isParallel = isParallel;
    }

    @Override
    public boolean isParallel() {
        return isParallel;
    }

    @Override
    public void close() {
        usedOrClosed = true;
        spliterator = null;
        functions = null;
    }

    protected static boolean isNonEmptySpliterator(Spliterator<?> spliterator) {
        if (spliterator instanceof Spliterator.OfInt) {
            return spliterator != Spliterators.emptyIntSpliterator();
        } else if (spliterator instanceof Spliterator.OfLong) {
            return spliterator != Spliterators.emptyLongSpliterator();
        } else if (spliterator instanceof Spliterator.OfDouble) {
            return spliterator != Spliterators.emptyDoubleSpliterator();
        } else {
            return spliterator != Spliterators.emptySpliterator();
        }
    }

    protected S getOnce() {
        if (usedOrClosed) {
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
