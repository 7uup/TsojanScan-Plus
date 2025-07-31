/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okio;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import okio.Buffer;
import okio.Sink;
import okio.Source;
import okio.Timeout;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010!\u001a\u00020\"J\u000e\u0010#\u001a\u00020\"2\u0006\u0010\u0017\u001a\u00020\u0010J\r\u0010\u0017\u001a\u00020\u0010H\u0007\u00a2\u0006\u0002\b$J\r\u0010\u001b\u001a\u00020\u001cH\u0007\u00a2\u0006\u0002\b%J&\u0010&\u001a\u00020\"*\u00020\u00102\u0017\u0010'\u001a\u0013\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\"0(\u00a2\u0006\u0002\b)H\u0082\bR\u0014\u0010\u0005\u001a\u00020\u0006X\u0080\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0013\u0010\u0017\u001a\u00020\u00108G\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0012R\u001a\u0010\u0018\u001a\u00020\nX\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\f\"\u0004\b\u001a\u0010\u000eR\u0013\u0010\u001b\u001a\u00020\u001c8G\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001dR\u001a\u0010\u001e\u001a\u00020\nX\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\f\"\u0004\b \u0010\u000e\u00a8\u0006*"}, d2={"Lokio/Pipe;", "", "maxBufferSize", "", "(J)V", "buffer", "Lokio/Buffer;", "getBuffer$okio", "()Lokio/Buffer;", "canceled", "", "getCanceled$okio", "()Z", "setCanceled$okio", "(Z)V", "foldedSink", "Lokio/Sink;", "getFoldedSink$okio", "()Lokio/Sink;", "setFoldedSink$okio", "(Lokio/Sink;)V", "getMaxBufferSize$okio", "()J", "sink", "sinkClosed", "getSinkClosed$okio", "setSinkClosed$okio", "source", "Lokio/Source;", "()Lokio/Source;", "sourceClosed", "getSourceClosed$okio", "setSourceClosed$okio", "cancel", "", "fold", "-deprecated_sink", "-deprecated_source", "forward", "block", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "okio"})
public final class Pipe {
    @NotNull
    private final Buffer buffer;
    private boolean canceled;
    private boolean sinkClosed;
    private boolean sourceClosed;
    @Nullable
    private Sink foldedSink;
    @NotNull
    private final Sink sink;
    @NotNull
    private final Source source;
    private final long maxBufferSize;

    @NotNull
    public final Buffer getBuffer$okio() {
        return this.buffer;
    }

    public final boolean getCanceled$okio() {
        return this.canceled;
    }

    public final void setCanceled$okio(boolean bl) {
        this.canceled = bl;
    }

    public final boolean getSinkClosed$okio() {
        return this.sinkClosed;
    }

    public final void setSinkClosed$okio(boolean bl) {
        this.sinkClosed = bl;
    }

    public final boolean getSourceClosed$okio() {
        return this.sourceClosed;
    }

    public final void setSourceClosed$okio(boolean bl) {
        this.sourceClosed = bl;
    }

    @Nullable
    public final Sink getFoldedSink$okio() {
        return this.foldedSink;
    }

    public final void setFoldedSink$okio(@Nullable Sink sink2) {
        this.foldedSink = sink2;
    }

    @JvmName(name="sink")
    @NotNull
    public final Sink sink() {
        return this.sink;
    }

    @JvmName(name="source")
    @NotNull
    public final Source source() {
        return this.source;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public final void fold(@NotNull Sink sink2) throws IOException {
        Intrinsics.checkNotNullParameter(sink2, "sink");
        while (true) {
            boolean bl;
            boolean closed = false;
            Buffer sinkBuffer = null;
            Buffer lock$iv = this.buffer;
            boolean $i$f$synchronized = false;
            boolean bl2 = false;
            boolean bl3 = false;
            synchronized (lock$iv) {
                boolean bl4 = false;
                bl = this.foldedSink == null;
                boolean bl5 = false;
                boolean bl6 = false;
                if (!bl) {
                    boolean bl7 = false;
                    String string = "sink already folded";
                    throw (Throwable)new IllegalStateException(string.toString());
                }
                if (this.canceled) {
                    this.foldedSink = sink2;
                    throw (Throwable)new IOException("canceled");
                }
                if (this.buffer.exhausted()) {
                    this.sourceClosed = true;
                    this.foldedSink = sink2;
                    return;
                }
                closed = this.sinkClosed;
                sinkBuffer = new Buffer();
                sinkBuffer.write(this.buffer, this.buffer.size());
                Buffer buffer = this.buffer;
                if (buffer == null) {
                    throw new NullPointerException("null cannot be cast to non-null type java.lang.Object");
                }
                ((Object)buffer).notifyAll();
                Unit unit = Unit.INSTANCE;
            }
            boolean success = false;
            try {
                sink2.write(sinkBuffer, sinkBuffer.size());
                if (closed) {
                    sink2.close();
                } else {
                    sink2.flush();
                }
                success = true;
            } catch (Throwable throwable) {
                Buffer lock$iv2 = this.buffer;
                boolean $i$f$synchronized2 = false;
                boolean bl8 = false;
                bl = false;
                synchronized (lock$iv2) {
                    boolean bl9 = false;
                    this.sourceClosed = true;
                    Buffer buffer = this.buffer;
                    if (buffer == null) {
                        throw new NullPointerException("null cannot be cast to non-null type java.lang.Object");
                    }
                    ((Object)buffer).notifyAll();
                    Unit unit = Unit.INSTANCE;
                    throw throwable;
                }
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * WARNING - void declaration
     */
    private final void forward(Sink $this$forward, Function1<? super Sink, Unit> block) {
        void this_$iv;
        int $i$f$forward = 0;
        Timeout timeout2 = $this$forward.timeout();
        Timeout other$iv = this.sink().timeout();
        boolean $i$f$intersectWith = false;
        long originalTimeout$iv = this_$iv.timeoutNanos();
        this_$iv.timeout(Timeout.Companion.minTimeout(other$iv.timeoutNanos(), this_$iv.timeoutNanos()), TimeUnit.NANOSECONDS);
        if (this_$iv.hasDeadline()) {
            long originalDeadline$iv = this_$iv.deadlineNanoTime();
            if (other$iv.hasDeadline()) {
                this_$iv.deadlineNanoTime(Math.min(this_$iv.deadlineNanoTime(), other$iv.deadlineNanoTime()));
            }
            try {
                boolean bl = false;
                block.invoke($this$forward);
            } finally {
                InlineMarker.finallyStart(1);
                this_$iv.timeout(originalTimeout$iv, TimeUnit.NANOSECONDS);
                if (other$iv.hasDeadline()) {
                    this_$iv.deadlineNanoTime(originalDeadline$iv);
                }
                InlineMarker.finallyEnd(1);
            }
        }
        if (other$iv.hasDeadline()) {
            this_$iv.deadlineNanoTime(other$iv.deadlineNanoTime());
        }
        try {
            boolean bl = false;
            block.invoke($this$forward);
        } finally {
            InlineMarker.finallyStart(1);
            this_$iv.timeout(originalTimeout$iv, TimeUnit.NANOSECONDS);
            if (other$iv.hasDeadline()) {
                this_$iv.clearDeadline();
            }
            InlineMarker.finallyEnd(1);
        }
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="sink"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_sink")
    @NotNull
    public final Sink -deprecated_sink() {
        return this.sink;
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="source"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_source")
    @NotNull
    public final Source -deprecated_source() {
        return this.source;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final void cancel() {
        Buffer lock$iv = this.buffer;
        boolean $i$f$synchronized = false;
        boolean bl = false;
        boolean bl2 = false;
        synchronized (lock$iv) {
            boolean bl3 = false;
            this.canceled = true;
            this.buffer.clear();
            Buffer buffer = this.buffer;
            if (buffer == null) {
                throw new NullPointerException("null cannot be cast to non-null type java.lang.Object");
            }
            ((Object)buffer).notifyAll();
            Unit unit = Unit.INSTANCE;
        }
    }

    public final long getMaxBufferSize$okio() {
        return this.maxBufferSize;
    }

    public Pipe(long maxBufferSize) {
        this.maxBufferSize = maxBufferSize;
        this.buffer = new Buffer();
        boolean bl = this.maxBufferSize >= 1L;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "maxBufferSize < 1: " + this.maxBufferSize;
            throw (Throwable)new IllegalArgumentException(string.toString());
        }
        this.sink = new Sink(this){
            private final Timeout timeout;
            final /* synthetic */ Pipe this$0;

            /*
             * WARNING - Removed try catching itself - possible behaviour change.
             * WARNING - void declaration
             */
            public void write(@NotNull Buffer source2, long byteCount) {
                Object object;
                Intrinsics.checkNotNullParameter(source2, "source");
                long byteCount2 = byteCount;
                Sink delegate = null;
                Buffer lock$iv = this.this$0.getBuffer$okio();
                boolean $i$f$synchronized2 = false;
                boolean bl = false;
                boolean bl2 = false;
                synchronized (lock$iv) {
                    boolean bl3 = false;
                    boolean bl4 = !this.this$0.getSinkClosed$okio();
                    boolean bl5 = false;
                    boolean bl6 = false;
                    if (!bl4) {
                        boolean bl7 = false;
                        String string = "closed";
                        throw (Throwable)new IllegalStateException(string.toString());
                    }
                    if (this.this$0.getCanceled$okio()) {
                        throw (Throwable)new IOException("canceled");
                    }
                    while (byteCount2 > 0L) {
                        Sink sink2 = this.this$0.getFoldedSink$okio();
                        if (sink2 != null) {
                            Sink sink3 = sink2;
                            bl5 = false;
                            bl6 = false;
                            Sink it = sink3;
                            boolean bl8 = false;
                            delegate = it;
                            break;
                        }
                        if (this.this$0.getSourceClosed$okio()) {
                            throw (Throwable)new IOException("source is closed");
                        }
                        long bufferSpaceAvailable = this.this$0.getMaxBufferSize$okio() - this.this$0.getBuffer$okio().size();
                        if (bufferSpaceAvailable == 0L) {
                            this.timeout.waitUntilNotified(this.this$0.getBuffer$okio());
                            if (!this.this$0.getCanceled$okio()) continue;
                            throw (Throwable)new IOException("canceled");
                        }
                        long l = byteCount2;
                        boolean bl9 = false;
                        long bytesToWrite = Math.min(bufferSpaceAvailable, l);
                        this.this$0.getBuffer$okio().write(source2, bytesToWrite);
                        byteCount2 -= bytesToWrite;
                        Buffer buffer = this.this$0.getBuffer$okio();
                        if (buffer == null) {
                            throw new NullPointerException("null cannot be cast to non-null type java.lang.Object");
                        }
                        ((Object)buffer).notifyAll();
                    }
                    object = Unit.INSTANCE;
                }
                Sink sink4 = delegate;
                if (sink4 != null) {
                    void this_$iv$iv;
                    void $this$forward$iv;
                    Sink $i$f$synchronized2 = sink4;
                    Pipe this_$iv = this.this$0;
                    boolean $i$f$forward = false;
                    object = $this$forward$iv.timeout();
                    Timeout other$iv$iv = this_$iv.sink().timeout();
                    boolean $i$f$intersectWith = false;
                    long originalTimeout$iv$iv = this_$iv$iv.timeoutNanos();
                    this_$iv$iv.timeout(Timeout.Companion.minTimeout(other$iv$iv.timeoutNanos(), this_$iv$iv.timeoutNanos()), TimeUnit.NANOSECONDS);
                    if (this_$iv$iv.hasDeadline()) {
                        long originalDeadline$iv$iv = this_$iv$iv.deadlineNanoTime();
                        if (other$iv$iv.hasDeadline()) {
                            this_$iv$iv.deadlineNanoTime(Math.min(this_$iv$iv.deadlineNanoTime(), other$iv$iv.deadlineNanoTime()));
                        }
                        try {
                            boolean bl10 = false;
                            void $this$forward = $this$forward$iv;
                            boolean bl11 = false;
                            $this$forward.write(source2, byteCount2);
                        } finally {
                            this_$iv$iv.timeout(originalTimeout$iv$iv, TimeUnit.NANOSECONDS);
                            if (other$iv$iv.hasDeadline()) {
                                this_$iv$iv.deadlineNanoTime(originalDeadline$iv$iv);
                            }
                        }
                    }
                    if (other$iv$iv.hasDeadline()) {
                        this_$iv$iv.deadlineNanoTime(other$iv$iv.deadlineNanoTime());
                    }
                    try {
                        boolean bl12 = false;
                        void $this$forward = $this$forward$iv;
                        boolean bl13 = false;
                        $this$forward.write(source2, byteCount2);
                    } finally {
                        this_$iv$iv.timeout(originalTimeout$iv$iv, TimeUnit.NANOSECONDS);
                        if (other$iv$iv.hasDeadline()) {
                            this_$iv$iv.clearDeadline();
                        }
                    }
                }
            }

            /*
             * WARNING - Removed try catching itself - possible behaviour change.
             * WARNING - void declaration
             */
            public void flush() {
                Object object;
                Sink delegate = null;
                Buffer lock$iv = this.this$0.getBuffer$okio();
                boolean $i$f$synchronized = false;
                boolean bl = false;
                boolean bl2 = false;
                synchronized (lock$iv) {
                    boolean bl3 = false;
                    boolean bl4 = !this.this$0.getSinkClosed$okio();
                    boolean bl5 = false;
                    boolean bl6 = false;
                    if (!bl4) {
                        boolean bl7 = false;
                        String string = "closed";
                        throw (Throwable)new IllegalStateException(string.toString());
                    }
                    if (this.this$0.getCanceled$okio()) {
                        throw (Throwable)new IOException("canceled");
                    }
                    Sink sink2 = this.this$0.getFoldedSink$okio();
                    if (sink2 != null) {
                        Sink sink3 = sink2;
                        bl5 = false;
                        bl6 = false;
                        Sink it = sink3;
                        boolean bl8 = false;
                        delegate = it;
                    } else if (this.this$0.getSourceClosed$okio() && this.this$0.getBuffer$okio().size() > 0L) {
                        throw (Throwable)new IOException("source is closed");
                    }
                    object = Unit.INSTANCE;
                }
                Sink sink4 = delegate;
                if (sink4 != null) {
                    void this_$iv$iv;
                    Sink $this$forward$iv = sink4;
                    Pipe this_$iv = this.this$0;
                    boolean $i$f$forward = false;
                    object = $this$forward$iv.timeout();
                    Timeout other$iv$iv = this_$iv.sink().timeout();
                    boolean $i$f$intersectWith = false;
                    long originalTimeout$iv$iv = this_$iv$iv.timeoutNanos();
                    this_$iv$iv.timeout(Timeout.Companion.minTimeout(other$iv$iv.timeoutNanos(), this_$iv$iv.timeoutNanos()), TimeUnit.NANOSECONDS);
                    if (this_$iv$iv.hasDeadline()) {
                        long originalDeadline$iv$iv = this_$iv$iv.deadlineNanoTime();
                        if (other$iv$iv.hasDeadline()) {
                            this_$iv$iv.deadlineNanoTime(Math.min(this_$iv$iv.deadlineNanoTime(), other$iv$iv.deadlineNanoTime()));
                        }
                        try {
                            boolean bl9 = false;
                            Sink $this$forward = $this$forward$iv;
                            boolean bl10 = false;
                            $this$forward.flush();
                        } finally {
                            this_$iv$iv.timeout(originalTimeout$iv$iv, TimeUnit.NANOSECONDS);
                            if (other$iv$iv.hasDeadline()) {
                                this_$iv$iv.deadlineNanoTime(originalDeadline$iv$iv);
                            }
                        }
                    }
                    if (other$iv$iv.hasDeadline()) {
                        this_$iv$iv.deadlineNanoTime(other$iv$iv.deadlineNanoTime());
                    }
                    try {
                        boolean bl11 = false;
                        Sink $this$forward = $this$forward$iv;
                        boolean bl12 = false;
                        $this$forward.flush();
                    } finally {
                        this_$iv$iv.timeout(originalTimeout$iv$iv, TimeUnit.NANOSECONDS);
                        if (other$iv$iv.hasDeadline()) {
                            this_$iv$iv.clearDeadline();
                        }
                    }
                }
            }

            /*
             * WARNING - Removed try catching itself - possible behaviour change.
             * WARNING - void declaration
             */
            public void close() {
                Object object;
                Sink delegate = null;
                Buffer lock$iv = this.this$0.getBuffer$okio();
                boolean $i$f$synchronized = false;
                boolean bl = false;
                boolean bl2 = false;
                synchronized (lock$iv) {
                    boolean bl3 = false;
                    if (this.this$0.getSinkClosed$okio()) {
                        return;
                    }
                    Sink sink2 = this.this$0.getFoldedSink$okio();
                    if (sink2 != null) {
                        Sink sink3 = sink2;
                        boolean bl4 = false;
                        boolean bl5 = false;
                        Sink it = sink3;
                        boolean bl6 = false;
                        delegate = it;
                    } else {
                        if (this.this$0.getSourceClosed$okio() && this.this$0.getBuffer$okio().size() > 0L) {
                            throw (Throwable)new IOException("source is closed");
                        }
                        this.this$0.setSinkClosed$okio(true);
                        Buffer buffer = this.this$0.getBuffer$okio();
                        if (buffer == null) {
                            throw new NullPointerException("null cannot be cast to non-null type java.lang.Object");
                        }
                        ((Object)buffer).notifyAll();
                    }
                    object = Unit.INSTANCE;
                }
                Sink sink4 = delegate;
                if (sink4 != null) {
                    void this_$iv$iv;
                    Sink $this$forward$iv = sink4;
                    Pipe this_$iv = this.this$0;
                    boolean $i$f$forward = false;
                    object = $this$forward$iv.timeout();
                    Timeout other$iv$iv = this_$iv.sink().timeout();
                    boolean $i$f$intersectWith = false;
                    long originalTimeout$iv$iv = this_$iv$iv.timeoutNanos();
                    this_$iv$iv.timeout(Timeout.Companion.minTimeout(other$iv$iv.timeoutNanos(), this_$iv$iv.timeoutNanos()), TimeUnit.NANOSECONDS);
                    if (this_$iv$iv.hasDeadline()) {
                        long originalDeadline$iv$iv = this_$iv$iv.deadlineNanoTime();
                        if (other$iv$iv.hasDeadline()) {
                            this_$iv$iv.deadlineNanoTime(Math.min(this_$iv$iv.deadlineNanoTime(), other$iv$iv.deadlineNanoTime()));
                        }
                        try {
                            boolean bl7 = false;
                            Sink $this$forward = $this$forward$iv;
                            boolean bl8 = false;
                            $this$forward.close();
                        } finally {
                            this_$iv$iv.timeout(originalTimeout$iv$iv, TimeUnit.NANOSECONDS);
                            if (other$iv$iv.hasDeadline()) {
                                this_$iv$iv.deadlineNanoTime(originalDeadline$iv$iv);
                            }
                        }
                    }
                    if (other$iv$iv.hasDeadline()) {
                        this_$iv$iv.deadlineNanoTime(other$iv$iv.deadlineNanoTime());
                    }
                    try {
                        boolean bl9 = false;
                        Sink $this$forward = $this$forward$iv;
                        boolean bl10 = false;
                        $this$forward.close();
                    } finally {
                        this_$iv$iv.timeout(originalTimeout$iv$iv, TimeUnit.NANOSECONDS);
                        if (other$iv$iv.hasDeadline()) {
                            this_$iv$iv.clearDeadline();
                        }
                    }
                }
            }

            @NotNull
            public Timeout timeout() {
                return this.timeout;
            }
            {
                this.this$0 = this$0;
                this.timeout = new Timeout();
            }
        };
        this.source = new Source(this){
            private final Timeout timeout;
            final /* synthetic */ Pipe this$0;

            /*
             * WARNING - Removed try catching itself - possible behaviour change.
             * Enabled aggressive block sorting
             * Enabled unnecessary exception pruning
             * Enabled aggressive exception aggregation
             */
            public long read(@NotNull Buffer sink2, long byteCount) {
                Intrinsics.checkNotNullParameter(sink2, "sink");
                Buffer lock$iv = this.this$0.getBuffer$okio();
                boolean $i$f$synchronized = false;
                boolean bl = false;
                boolean bl2 = false;
                synchronized (lock$iv) {
                    boolean bl3 = false;
                    boolean bl4 = !this.this$0.getSourceClosed$okio();
                    boolean bl5 = false;
                    boolean bl6 = false;
                    if (!bl4) {
                        boolean bl7 = false;
                        String string = "closed";
                        throw (Throwable)new IllegalStateException(string.toString());
                    }
                    if (this.this$0.getCanceled$okio()) {
                        throw (Throwable)new IOException("canceled");
                    }
                    while (this.this$0.getBuffer$okio().size() == 0L) {
                        if (this.this$0.getSinkClosed$okio()) {
                            return -1L;
                        }
                        this.timeout.waitUntilNotified(this.this$0.getBuffer$okio());
                        if (!this.this$0.getCanceled$okio()) continue;
                        throw (Throwable)new IOException("canceled");
                    }
                    long result = this.this$0.getBuffer$okio().read(sink2, byteCount);
                    Buffer buffer = this.this$0.getBuffer$okio();
                    if (buffer == null) {
                        throw new NullPointerException("null cannot be cast to non-null type java.lang.Object");
                    }
                    ((Object)buffer).notifyAll();
                    return result;
                }
            }

            /*
             * WARNING - Removed try catching itself - possible behaviour change.
             */
            public void close() {
                Buffer lock$iv = this.this$0.getBuffer$okio();
                boolean $i$f$synchronized = false;
                boolean bl = false;
                boolean bl2 = false;
                synchronized (lock$iv) {
                    boolean bl3 = false;
                    this.this$0.setSourceClosed$okio(true);
                    Buffer buffer = this.this$0.getBuffer$okio();
                    if (buffer == null) {
                        throw new NullPointerException("null cannot be cast to non-null type java.lang.Object");
                    }
                    ((Object)buffer).notifyAll();
                    Unit unit = Unit.INSTANCE;
                }
            }

            @NotNull
            public Timeout timeout() {
                return this.timeout;
            }
            {
                this.this$0 = this$0;
                this.timeout = new Timeout();
            }
        };
    }

    public static final /* synthetic */ void access$forward(Pipe $this, Sink $this$access_u24forward, Function1 block) {
        $this.forward($this$access_u24forward, block);
    }
}

