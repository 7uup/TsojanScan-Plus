/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okio;

import java.io.EOFException;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okio.Buffer;
import okio.BufferedSource;
import okio.Okio;
import okio.Segment;
import okio.SegmentPool;
import okio.Source;
import okio.Timeout;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\u0002\u0010\u0005B\u0017\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0006\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\u0002\u0010\u0007J\b\u0010\f\u001a\u00020\rH\u0016J\u0018\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u000fH\u0016J\u0016\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u000fJ\u0006\u0010\u0014\u001a\u00020\u000bJ\b\u0010\u0015\u001a\u00020\rH\u0002J\b\u0010\u0016\u001a\u00020\u0017H\u0016R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0018"}, d2={"Lokio/InflaterSource;", "Lokio/Source;", "source", "inflater", "Ljava/util/zip/Inflater;", "(Lokio/Source;Ljava/util/zip/Inflater;)V", "Lokio/BufferedSource;", "(Lokio/BufferedSource;Ljava/util/zip/Inflater;)V", "bufferBytesHeldByInflater", "", "closed", "", "close", "", "read", "", "sink", "Lokio/Buffer;", "byteCount", "readOrInflate", "refill", "releaseBytesAfterInflate", "timeout", "Lokio/Timeout;", "okio"})
public final class InflaterSource
implements Source {
    private int bufferBytesHeldByInflater;
    private boolean closed;
    private final BufferedSource source;
    private final Inflater inflater;

    @Override
    public long read(@NotNull Buffer sink2, long byteCount) throws IOException {
        Intrinsics.checkNotNullParameter(sink2, "sink");
        do {
            long bytesInflated;
            if ((bytesInflated = this.readOrInflate(sink2, byteCount)) > 0L) {
                return bytesInflated;
            }
            if (!this.inflater.finished() && !this.inflater.needsDictionary()) continue;
            return -1L;
        } while (!this.source.exhausted());
        throw (Throwable)new EOFException("source exhausted prematurely");
    }

    public final long readOrInflate(@NotNull Buffer sink2, long byteCount) throws IOException {
        Intrinsics.checkNotNullParameter(sink2, "sink");
        boolean bl = byteCount >= 0L;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "byteCount < 0: " + byteCount;
            throw (Throwable)new IllegalArgumentException(string.toString());
        }
        bl = !this.closed;
        bl2 = false;
        bl3 = false;
        if (!bl) {
            boolean bl5 = false;
            String string = "closed";
            throw (Throwable)new IllegalStateException(string.toString());
        }
        if (byteCount == 0L) {
            return 0L;
        }
        try {
            Segment tail = sink2.writableSegment$okio(1);
            int b$iv = 8192 - tail.limit;
            boolean $i$f$minOf = false;
            long l = b$iv;
            boolean bl6 = false;
            int toRead = (int)Math.min(byteCount, l);
            this.refill();
            int bytesInflated = this.inflater.inflate(tail.data, tail.limit, toRead);
            this.releaseBytesAfterInflate();
            if (bytesInflated > 0) {
                tail.limit += bytesInflated;
                Buffer buffer = sink2;
                buffer.setSize$okio(buffer.size() + (long)bytesInflated);
                return bytesInflated;
            }
            if (tail.pos == tail.limit) {
                sink2.head = tail.pop();
                SegmentPool.recycle(tail);
            }
            return 0L;
        } catch (DataFormatException e) {
            throw (Throwable)new IOException(e);
        }
    }

    public final boolean refill() throws IOException {
        if (!this.inflater.needsInput()) {
            return false;
        }
        if (this.source.exhausted()) {
            return true;
        }
        Segment segment = this.source.getBuffer().head;
        Intrinsics.checkNotNull(segment);
        Segment head = segment;
        this.bufferBytesHeldByInflater = head.limit - head.pos;
        this.inflater.setInput(head.data, head.pos, this.bufferBytesHeldByInflater);
        return false;
    }

    private final void releaseBytesAfterInflate() {
        if (this.bufferBytesHeldByInflater == 0) {
            return;
        }
        int toRelease = this.bufferBytesHeldByInflater - this.inflater.getRemaining();
        this.bufferBytesHeldByInflater -= toRelease;
        this.source.skip(toRelease);
    }

    @Override
    @NotNull
    public Timeout timeout() {
        return this.source.timeout();
    }

    @Override
    public void close() throws IOException {
        if (this.closed) {
            return;
        }
        this.inflater.end();
        this.closed = true;
        this.source.close();
    }

    public InflaterSource(@NotNull BufferedSource source2, @NotNull Inflater inflater) {
        Intrinsics.checkNotNullParameter(source2, "source");
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this.source = source2;
        this.inflater = inflater;
    }

    public InflaterSource(@NotNull Source source2, @NotNull Inflater inflater) {
        Intrinsics.checkNotNullParameter(source2, "source");
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this(Okio.buffer(source2), inflater);
    }
}

