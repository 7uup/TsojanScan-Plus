/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okio;

import java.io.IOException;
import java.io.InputStream;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okio.Buffer;
import okio.Okio;
import okio.Segment;
import okio.SegmentPool;
import okio.Source;
import okio.Timeout;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001a\u00020\bH\u0016J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\nH\u0016J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\b\u0010\u000e\u001a\u00020\u000fH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0010"}, d2={"Lokio/InputStreamSource;", "Lokio/Source;", "input", "Ljava/io/InputStream;", "timeout", "Lokio/Timeout;", "(Ljava/io/InputStream;Lokio/Timeout;)V", "close", "", "read", "", "sink", "Lokio/Buffer;", "byteCount", "toString", "", "okio"})
final class InputStreamSource
implements Source {
    private final InputStream input;
    private final Timeout timeout;

    @Override
    public long read(@NotNull Buffer sink2, long byteCount) {
        Intrinsics.checkNotNullParameter(sink2, "sink");
        if (byteCount == 0L) {
            return 0L;
        }
        boolean bl = byteCount >= 0L;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "byteCount < 0: " + byteCount;
            throw (Throwable)new IllegalArgumentException(string.toString());
        }
        try {
            this.timeout.throwIfReached();
            Segment tail = sink2.writableSegment$okio(1);
            int b$iv = 8192 - tail.limit;
            boolean $i$f$minOf = false;
            long l = b$iv;
            boolean bl5 = false;
            int maxToCopy = (int)Math.min(byteCount, l);
            int bytesRead = this.input.read(tail.data, tail.limit, maxToCopy);
            if (bytesRead == -1) {
                if (tail.pos == tail.limit) {
                    sink2.head = tail.pop();
                    SegmentPool.recycle(tail);
                }
                return -1L;
            }
            tail.limit += bytesRead;
            Buffer buffer = sink2;
            buffer.setSize$okio(buffer.size() + (long)bytesRead);
            return bytesRead;
        } catch (AssertionError e) {
            if (Okio.isAndroidGetsocknameError(e)) {
                throw (Throwable)new IOException((Throwable)((Object)e));
            }
            throw (Throwable)((Object)e);
        }
    }

    @Override
    public void close() {
        this.input.close();
    }

    @Override
    @NotNull
    public Timeout timeout() {
        return this.timeout;
    }

    @NotNull
    public String toString() {
        return "source(" + this.input + ')';
    }

    public InputStreamSource(@NotNull InputStream input, @NotNull Timeout timeout2) {
        Intrinsics.checkNotNullParameter(input, "input");
        Intrinsics.checkNotNullParameter(timeout2, "timeout");
        this.input = input;
        this.timeout = timeout2;
    }
}

