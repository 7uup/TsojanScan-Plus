/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okio;

import java.io.IOException;
import java.util.zip.CRC32;
import java.util.zip.Deflater;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import okio.Buffer;
import okio.DeflaterSink;
import okio.RealBufferedSink;
import okio.Segment;
import okio.Sink;
import okio.Timeout;
import org.jetbrains.annotations.NotNull;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u00a2\u0006\u0002\u0010\u0003J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\r\u0010\b\u001a\u00020\tH\u0007\u00a2\u0006\u0002\b\u0010J\b\u0010\u0011\u001a\u00020\u000fH\u0016J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\u0018\u0010\u0014\u001a\u00020\u000f2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u0018\u0010\u0019\u001a\u00020\u000f2\u0006\u0010\u001a\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\b\u0010\u001b\u001a\u00020\u000fH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0013\u0010\b\u001a\u00020\t8G\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001c"}, d2={"Lokio/GzipSink;", "Lokio/Sink;", "sink", "(Lokio/Sink;)V", "closed", "", "crc", "Ljava/util/zip/CRC32;", "deflater", "Ljava/util/zip/Deflater;", "()Ljava/util/zip/Deflater;", "deflaterSink", "Lokio/DeflaterSink;", "Lokio/RealBufferedSink;", "close", "", "-deprecated_deflater", "flush", "timeout", "Lokio/Timeout;", "updateCrc", "buffer", "Lokio/Buffer;", "byteCount", "", "write", "source", "writeFooter", "okio"})
public final class GzipSink
implements Sink {
    private final RealBufferedSink sink;
    @NotNull
    private final Deflater deflater;
    private final DeflaterSink deflaterSink;
    private boolean closed;
    private final CRC32 crc;

    @JvmName(name="deflater")
    @NotNull
    public final Deflater deflater() {
        return this.deflater;
    }

    @Override
    public void write(@NotNull Buffer source2, long byteCount) throws IOException {
        Intrinsics.checkNotNullParameter(source2, "source");
        boolean bl = byteCount >= 0L;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "byteCount < 0: " + byteCount;
            throw (Throwable)new IllegalArgumentException(string.toString());
        }
        if (byteCount == 0L) {
            return;
        }
        this.updateCrc(source2, byteCount);
        this.deflaterSink.write(source2, byteCount);
    }

    @Override
    public void flush() throws IOException {
        this.deflaterSink.flush();
    }

    @Override
    @NotNull
    public Timeout timeout() {
        return this.sink.timeout();
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
                    this.deflaterSink.finishDeflate$okio();
                    this.writeFooter();
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

    private final void writeFooter() {
        this.sink.writeIntLe((int)this.crc.getValue());
        this.sink.writeIntLe((int)this.deflater.getBytesRead());
    }

    private final void updateCrc(Buffer buffer, long byteCount) {
        int segmentLength;
        Segment segment = buffer.head;
        Intrinsics.checkNotNull(segment);
        Segment head = segment;
        for (long remaining = byteCount; remaining > 0L; remaining -= (long)segmentLength) {
            int b$iv = head.limit - head.pos;
            boolean $i$f$minOf = false;
            long l = b$iv;
            boolean bl = false;
            segmentLength = (int)Math.min(remaining, l);
            this.crc.update(head.data, head.pos, segmentLength);
            Intrinsics.checkNotNull(head.next);
        }
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="deflater"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_deflater")
    @NotNull
    public final Deflater -deprecated_deflater() {
        return this.deflater;
    }

    public GzipSink(@NotNull Sink sink2) {
        Intrinsics.checkNotNullParameter(sink2, "sink");
        this.sink = new RealBufferedSink(sink2);
        this.deflater = new Deflater(-1, true);
        this.deflaterSink = new DeflaterSink(this.sink, this.deflater);
        this.crc = new CRC32();
        RealBufferedSink this_$iv = this.sink;
        boolean $i$f$getBuffer = false;
        Buffer buffer = this_$iv.bufferField;
        boolean bl = false;
        boolean bl2 = false;
        Buffer $this$apply = buffer;
        boolean bl3 = false;
        $this$apply.writeShort(8075);
        $this$apply.writeByte(8);
        $this$apply.writeByte(0);
        $this$apply.writeInt(0);
        $this$apply.writeByte(0);
        $this$apply.writeByte(0);
    }
}

