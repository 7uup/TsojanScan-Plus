/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okio;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okio.Buffer;
import okio.BufferedSource;
import okio.Segment;
import okio.Source;
import okio.Timeout;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\u0018\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u000eH\u0016J\b\u0010\u0014\u001a\u00020\u0015H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0016"}, d2={"Lokio/PeekSource;", "Lokio/Source;", "upstream", "Lokio/BufferedSource;", "(Lokio/BufferedSource;)V", "buffer", "Lokio/Buffer;", "closed", "", "expectedPos", "", "expectedSegment", "Lokio/Segment;", "pos", "", "close", "", "read", "sink", "byteCount", "timeout", "Lokio/Timeout;", "okio"})
public final class PeekSource
implements Source {
    private final Buffer buffer;
    private Segment expectedSegment;
    private int expectedPos;
    private boolean closed;
    private long pos;
    private final BufferedSource upstream;

    /*
     * Unable to fully structure code
     */
    @Override
    public long read(@NotNull Buffer sink, long byteCount) {
        Intrinsics.checkNotNullParameter(sink, "sink");
        var4_3 = byteCount >= 0L;
        var5_5 = false;
        var6_6 = false;
        if (!var4_3) {
            $i$a$-require-PeekSource$read$1 = false;
            var6_7 = "byteCount < 0: " + byteCount;
            throw (Throwable)new IllegalArgumentException(var6_7.toString());
        }
        var4_3 = this.closed == false;
        var5_5 = false;
        var6_6 = false;
        if (!var4_3) {
            $i$a$-check-PeekSource$read$2 = false;
            var6_8 = "closed";
            throw (Throwable)new IllegalStateException(var6_8.toString());
        }
        if (this.expectedSegment == null) ** GOTO lbl-1000
        if (this.expectedSegment == this.buffer.head) {
            v0 = this.buffer.head;
            Intrinsics.checkNotNull(v0);
            ** if (this.expectedPos != v0.pos) goto lbl-1000
        }
        ** GOTO lbl-1000
lbl-1000:
        // 2 sources

        {
            v1 = true;
            ** GOTO lbl25
        }
lbl-1000:
        // 2 sources

        {
            v1 = false;
        }
lbl25:
        // 2 sources

        var4_3 = v1;
        var5_5 = false;
        var6_6 = false;
        if (!var4_3) {
            $i$a$-check-PeekSource$read$3 = false;
            var6_9 = "Peek source is invalid because upstream source was used";
            throw (Throwable)new IllegalStateException(var6_9.toString());
        }
        if (byteCount == 0L) {
            return 0L;
        }
        if (!this.upstream.request(this.pos + 1L)) {
            return -1L;
        }
        if (this.expectedSegment == null && this.buffer.head != null) {
            this.expectedSegment = this.buffer.head;
            v2 = this.buffer.head;
            Intrinsics.checkNotNull(v2);
            this.expectedPos = v2.pos;
        }
        var6_10 = this.buffer.size() - this.pos;
        var8_14 = false;
        toCopy = Math.min(byteCount, var6_10);
        this.buffer.copyTo(sink, this.pos, toCopy);
        this.pos += toCopy;
        return toCopy;
    }

    @Override
    @NotNull
    public Timeout timeout() {
        return this.upstream.timeout();
    }

    @Override
    public void close() {
        this.closed = true;
    }

    public PeekSource(@NotNull BufferedSource upstream) {
        Intrinsics.checkNotNullParameter(upstream, "upstream");
        this.upstream = upstream;
        this.buffer = this.upstream.getBuffer();
        this.expectedSegment = this.buffer.head;
        Segment segment = this.buffer.head;
        this.expectedPos = segment != null ? segment.pos : -1;
    }
}

