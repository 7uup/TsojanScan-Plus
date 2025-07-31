/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okio;

import java.io.EOFException;
import java.io.IOException;
import java.util.Arrays;
import java.util.zip.CRC32;
import java.util.zip.Inflater;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okio.Buffer;
import okio.InflaterSource;
import okio.RealBufferedSource;
import okio.Segment;
import okio.Source;
import okio.Timeout;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u00a2\u0006\u0002\u0010\u0003J \u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0012H\u0002J\b\u0010\u0014\u001a\u00020\u000eH\u0016J\b\u0010\u0015\u001a\u00020\u000eH\u0002J\b\u0010\u0016\u001a\u00020\u000eH\u0002J\u0018\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0018H\u0016J\b\u0010\u001c\u001a\u00020\u001dH\u0016J \u0010\u001e\u001a\u00020\u000e2\u0006\u0010\u001f\u001a\u00020\u001a2\u0006\u0010 \u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u0018H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006!"}, d2={"Lokio/GzipSource;", "Lokio/Source;", "source", "(Lokio/Source;)V", "crc", "Ljava/util/zip/CRC32;", "inflater", "Ljava/util/zip/Inflater;", "inflaterSource", "Lokio/InflaterSource;", "section", "", "Lokio/RealBufferedSource;", "checkEqual", "", "name", "", "expected", "", "actual", "close", "consumeHeader", "consumeTrailer", "read", "", "sink", "Lokio/Buffer;", "byteCount", "timeout", "Lokio/Timeout;", "updateCrc", "buffer", "offset", "okio"})
public final class GzipSource
implements Source {
    private byte section;
    private final RealBufferedSource source;
    private final Inflater inflater;
    private final InflaterSource inflaterSource;
    private final CRC32 crc;

    @Override
    public long read(@NotNull Buffer sink2, long byteCount) throws IOException {
        Intrinsics.checkNotNullParameter(sink2, "sink");
        boolean bl = byteCount >= 0L;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "byteCount < 0: " + byteCount;
            throw (Throwable)new IllegalArgumentException(string.toString());
        }
        if (byteCount == 0L) {
            return 0L;
        }
        if (this.section == 0) {
            this.consumeHeader();
            this.section = 1;
        }
        if (this.section == 1) {
            long offset = sink2.size();
            long result = this.inflaterSource.read(sink2, byteCount);
            if (result != -1L) {
                this.updateCrc(sink2, offset, result);
                return result;
            }
            this.section = (byte)2;
        }
        if (this.section == 2) {
            this.consumeTrailer();
            this.section = (byte)3;
            if (!this.source.exhausted()) {
                throw (Throwable)new IOException("gzip finished without exhausting source");
            }
        }
        return -1L;
    }

    /*
     * WARNING - void declaration
     */
    private final void consumeHeader() throws IOException {
        boolean $i$f$getBuffer;
        void $this$getBit$iv;
        byte $i$f$getBuffer2;
        void $this$getBit$iv2;
        boolean fhcrc;
        byte flags;
        this.source.require(10L);
        RealBufferedSource this_$iv = this.source;
        byte $i$f$getBuffer3 = 0;
        $i$f$getBuffer3 = flags = this_$iv.bufferField.getByte(3L);
        int bit$iv = 1;
        boolean $i$f$getBit = false;
        boolean bl = fhcrc = ($this$getBit$iv2 >> bit$iv & 1) == 1;
        if (fhcrc) {
            RealBufferedSource this_$iv2 = this.source;
            $i$f$getBuffer2 = 0;
            this.updateCrc(this_$iv2.bufferField, 0L, 10L);
        }
        short id1id2 = this.source.readShort();
        this.checkEqual("ID1ID2", 8075, id1id2);
        this.source.skip(8L);
        $i$f$getBuffer2 = flags;
        int bit$iv2 = 2;
        boolean $i$f$getBit2 = false;
        if (($this$getBit$iv >> bit$iv2 & 1) == 1) {
            this.source.require(2L);
            if (fhcrc) {
                RealBufferedSource this_$iv3 = this.source;
                boolean $i$f$getBuffer4 = false;
                this.updateCrc(this_$iv3.bufferField, 0L, 2L);
            }
            RealBufferedSource this_$iv4 = this.source;
            $i$f$getBuffer = false;
            long xlen = this_$iv4.bufferField.readShortLe();
            this.source.require(xlen);
            if (fhcrc) {
                this_$iv4 = this.source;
                $i$f$getBuffer = false;
                this.updateCrc(this_$iv4.bufferField, 0L, xlen);
            }
            this.source.skip(xlen);
        }
        byte xlen = flags;
        bit$iv2 = 3;
        $i$f$getBit2 = false;
        if (($this$getBit$iv >> bit$iv2 & 1) == 1) {
            long index = this.source.indexOf((byte)0);
            if (index == -1L) {
                throw (Throwable)new EOFException();
            }
            if (fhcrc) {
                RealBufferedSource this_$iv5 = this.source;
                $i$f$getBuffer = false;
                this.updateCrc(this_$iv5.bufferField, 0L, index + 1L);
            }
            this.source.skip(index + 1L);
        }
        byte index = flags;
        bit$iv2 = 4;
        $i$f$getBit = false;
        if (($this$getBit$iv >> bit$iv2 & 1) == 1) {
            long index2 = this.source.indexOf((byte)0);
            if (index2 == -1L) {
                throw (Throwable)new EOFException();
            }
            if (fhcrc) {
                RealBufferedSource this_$iv6 = this.source;
                $i$f$getBuffer = false;
                this.updateCrc(this_$iv6.bufferField, 0L, index2 + 1L);
            }
            this.source.skip(index2 + 1L);
        }
        if (fhcrc) {
            this.checkEqual("FHCRC", this.source.readShortLe(), (short)this.crc.getValue());
            this.crc.reset();
        }
    }

    private final void consumeTrailer() throws IOException {
        this.checkEqual("CRC", this.source.readIntLe(), (int)this.crc.getValue());
        this.checkEqual("ISIZE", this.source.readIntLe(), (int)this.inflater.getBytesWritten());
    }

    @Override
    @NotNull
    public Timeout timeout() {
        return this.source.timeout();
    }

    @Override
    public void close() throws IOException {
        this.inflaterSource.close();
    }

    private final void updateCrc(Buffer buffer, long offset, long byteCount) {
        long offset2;
        long byteCount2 = byteCount;
        Segment segment = buffer.head;
        Intrinsics.checkNotNull(segment);
        Segment s2 = segment;
        for (offset2 = offset; offset2 >= (long)(s2.limit - s2.pos); offset2 -= (long)(s2.limit - s2.pos)) {
            Intrinsics.checkNotNull(s2.next);
        }
        while (byteCount2 > 0L) {
            int pos = (int)((long)s2.pos + offset2);
            int a$iv = s2.limit - pos;
            boolean $i$f$minOf = false;
            long l = a$iv;
            boolean bl = false;
            int toUpdate = (int)Math.min(l, byteCount2);
            this.crc.update(s2.data, pos, toUpdate);
            byteCount2 -= (long)toUpdate;
            offset2 = 0L;
            Intrinsics.checkNotNull(s2.next);
        }
    }

    private final void checkEqual(String name, int expected, int actual) {
        if (actual != expected) {
            String string = "%s: actual 0x%08x != expected 0x%08x";
            Object[] objectArray = new Object[]{name, actual, expected};
            boolean bl = false;
            String string2 = String.format(string, Arrays.copyOf(objectArray, objectArray.length));
            Intrinsics.checkNotNullExpressionValue(string2, "java.lang.String.format(this, *args)");
            throw (Throwable)new IOException(string2);
        }
    }

    public GzipSource(@NotNull Source source2) {
        Intrinsics.checkNotNullParameter(source2, "source");
        this.source = new RealBufferedSource(source2);
        this.inflater = new Inflater(true);
        this.inflaterSource = new InflaterSource(this.source, this.inflater);
        this.crc = new CRC32();
    }
}

