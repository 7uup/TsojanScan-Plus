/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement
 */
package okio;

import java.io.IOException;
import java.util.zip.Deflater;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okio.-Util;
import okio.Buffer;
import okio.BufferedSink;
import okio.Okio;
import okio.Segment;
import okio.SegmentPool;
import okio.Sink;
import okio.Timeout;
import org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\u0018\u00002\u00020\u0001B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\u0002\u0010\u0005B\u0017\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0006\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\u0002\u0010\u0007J\b\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\tH\u0003J\r\u0010\u000e\u001a\u00020\u000bH\u0000\u00a2\u0006\u0002\b\u000fJ\b\u0010\u0010\u001a\u00020\u000bH\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\u0018\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0016R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001a"}, d2={"Lokio/DeflaterSink;", "Lokio/Sink;", "sink", "deflater", "Ljava/util/zip/Deflater;", "(Lokio/Sink;Ljava/util/zip/Deflater;)V", "Lokio/BufferedSink;", "(Lokio/BufferedSink;Ljava/util/zip/Deflater;)V", "closed", "", "close", "", "deflate", "syncFlush", "finishDeflate", "finishDeflate$okio", "flush", "timeout", "Lokio/Timeout;", "toString", "", "write", "source", "Lokio/Buffer;", "byteCount", "", "okio"})
public final class DeflaterSink
implements Sink {
    private boolean closed;
    private final BufferedSink sink;
    private final Deflater deflater;

    @Override
    public void write(@NotNull Buffer source2, long byteCount) throws IOException {
        int toDeflate;
        Intrinsics.checkNotNullParameter(source2, "source");
        -Util.checkOffsetAndCount(source2.size(), 0L, byteCount);
        for (long remaining = byteCount; remaining > 0L; remaining -= (long)toDeflate) {
            Segment head;
            Intrinsics.checkNotNull(source2.head);
            int b$iv = head.limit - head.pos;
            boolean $i$f$minOf = false;
            long l = b$iv;
            boolean bl = false;
            toDeflate = (int)Math.min(remaining, l);
            this.deflater.setInput(head.data, head.pos, toDeflate);
            this.deflate(false);
            Buffer buffer = source2;
            buffer.setSize$okio(buffer.size() - (long)toDeflate);
            head.pos += toDeflate;
            if (head.pos != head.limit) continue;
            source2.head = head.pop();
            SegmentPool.recycle(head);
        }
    }

    @IgnoreJRERequirement
    private final void deflate(boolean syncFlush) {
        Segment s2;
        Buffer buffer = this.sink.getBuffer();
        while (true) {
            int deflated;
            s2 = buffer.writableSegment$okio(1);
            int n = deflated = syncFlush ? this.deflater.deflate(s2.data, s2.limit, 8192 - s2.limit, 2) : this.deflater.deflate(s2.data, s2.limit, 8192 - s2.limit);
            if (deflated > 0) {
                s2.limit += deflated;
                Buffer buffer2 = buffer;
                buffer2.setSize$okio(buffer2.size() + (long)deflated);
                this.sink.emitCompleteSegments();
                continue;
            }
            if (this.deflater.needsInput()) break;
        }
        if (s2.pos == s2.limit) {
            buffer.head = s2.pop();
            SegmentPool.recycle(s2);
        }
    }

    @Override
    public void flush() throws IOException {
        this.deflate(true);
        this.sink.flush();
    }

    public final void finishDeflate$okio() {
        this.deflater.finish();
        this.deflate(false);
    }

    @Override
    public void close() throws IOException {
        Throwable thrown;
        block9: {
            block8: {
                if (this.closed) {
                    return;
                }
                thrown = null;
                try {
                    this.finishDeflate$okio();
                } catch (Throwable e) {
                    thrown = e;
                }
                try {
                    this.deflater.end();
                } catch (Throwable e) {
                    if (thrown != null) break block8;
                    thrown = e;
                }
            }
            try {
                this.sink.close();
            } catch (Throwable e) {
                if (thrown != null) break block9;
                thrown = e;
            }
        }
        this.closed = true;
        if (thrown != null) {
            throw thrown;
        }
    }

    @Override
    @NotNull
    public Timeout timeout() {
        return this.sink.timeout();
    }

    @NotNull
    public String toString() {
        return "DeflaterSink(" + this.sink + ')';
    }

    public DeflaterSink(@NotNull BufferedSink sink2, @NotNull Deflater deflater) {
        Intrinsics.checkNotNullParameter(sink2, "sink");
        Intrinsics.checkNotNullParameter(deflater, "deflater");
        this.sink = sink2;
        this.deflater = deflater;
    }

    public DeflaterSink(@NotNull Sink sink2, @NotNull Deflater deflater) {
        Intrinsics.checkNotNullParameter(sink2, "sink");
        Intrinsics.checkNotNullParameter(deflater, "deflater");
        this(Okio.buffer(sink2), deflater);
    }
}

