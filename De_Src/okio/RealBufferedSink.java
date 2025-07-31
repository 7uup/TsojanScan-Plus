/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okio;

import java.io.EOFException;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import okio.Buffer;
import okio.BufferedSink;
import okio.ByteString;
import okio.Sink;
import okio.Source;
import okio.Timeout;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u0001H\u0016J\b\u0010\u0011\u001a\u00020\u0001H\u0016J\b\u0010\u0012\u001a\u00020\u000fH\u0016J\b\u0010\u0013\u001a\u00020\rH\u0016J\b\u0010\u0014\u001a\u00020\u0015H\u0016J\b\u0010\u0016\u001a\u00020\u0017H\u0016J\b\u0010\u0018\u001a\u00020\u0019H\u0016J\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J\u0010\u0010\u001a\u001a\u00020\u00012\u0006\u0010\u001c\u001a\u00020\u001eH\u0016J \u0010\u001a\u001a\u00020\u00012\u0006\u0010\u001c\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u001b2\u0006\u0010 \u001a\u00020\u001bH\u0016J\u0018\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001c\u001a\u00020\u00062\u0006\u0010 \u001a\u00020!H\u0016J\u0010\u0010\u001a\u001a\u00020\u00012\u0006\u0010\"\u001a\u00020#H\u0016J \u0010\u001a\u001a\u00020\u00012\u0006\u0010\"\u001a\u00020#2\u0006\u0010\u001f\u001a\u00020\u001b2\u0006\u0010 \u001a\u00020\u001bH\u0016J\u0018\u0010\u001a\u001a\u00020\u00012\u0006\u0010\u001c\u001a\u00020$2\u0006\u0010 \u001a\u00020!H\u0016J\u0010\u0010%\u001a\u00020!2\u0006\u0010\u001c\u001a\u00020$H\u0016J\u0010\u0010&\u001a\u00020\u00012\u0006\u0010'\u001a\u00020\u001bH\u0016J\u0010\u0010(\u001a\u00020\u00012\u0006\u0010)\u001a\u00020!H\u0016J\u0010\u0010*\u001a\u00020\u00012\u0006\u0010)\u001a\u00020!H\u0016J\u0010\u0010+\u001a\u00020\u00012\u0006\u0010,\u001a\u00020\u001bH\u0016J\u0010\u0010-\u001a\u00020\u00012\u0006\u0010,\u001a\u00020\u001bH\u0016J\u0010\u0010.\u001a\u00020\u00012\u0006\u0010)\u001a\u00020!H\u0016J\u0010\u0010/\u001a\u00020\u00012\u0006\u0010)\u001a\u00020!H\u0016J\u0010\u00100\u001a\u00020\u00012\u0006\u00101\u001a\u00020\u001bH\u0016J\u0010\u00102\u001a\u00020\u00012\u0006\u00101\u001a\u00020\u001bH\u0016J\u0018\u00103\u001a\u00020\u00012\u0006\u00104\u001a\u00020\u00192\u0006\u00105\u001a\u000206H\u0016J(\u00103\u001a\u00020\u00012\u0006\u00104\u001a\u00020\u00192\u0006\u00107\u001a\u00020\u001b2\u0006\u00108\u001a\u00020\u001b2\u0006\u00105\u001a\u000206H\u0016J\u0010\u00109\u001a\u00020\u00012\u0006\u00104\u001a\u00020\u0019H\u0016J \u00109\u001a\u00020\u00012\u0006\u00104\u001a\u00020\u00192\u0006\u00107\u001a\u00020\u001b2\u0006\u00108\u001a\u00020\u001bH\u0016J\u0010\u0010:\u001a\u00020\u00012\u0006\u0010;\u001a\u00020\u001bH\u0016R\u001b\u0010\u0005\u001a\u00020\u00068\u00d6\u0002X\u0096\u0004\u00a2\u0006\f\u0012\u0004\b\u0007\u0010\b\u001a\u0004\b\t\u0010\nR\u0010\u0010\u000b\u001a\u00020\u00068\u0006X\u0087\u0004\u00a2\u0006\u0002\n\u0000R\u0012\u0010\f\u001a\u00020\r8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006<"}, d2={"Lokio/RealBufferedSink;", "Lokio/BufferedSink;", "sink", "Lokio/Sink;", "(Lokio/Sink;)V", "buffer", "Lokio/Buffer;", "getBuffer$annotations", "()V", "getBuffer", "()Lokio/Buffer;", "bufferField", "closed", "", "close", "", "emit", "emitCompleteSegments", "flush", "isOpen", "outputStream", "Ljava/io/OutputStream;", "timeout", "Lokio/Timeout;", "toString", "", "write", "", "source", "Ljava/nio/ByteBuffer;", "", "offset", "byteCount", "", "byteString", "Lokio/ByteString;", "Lokio/Source;", "writeAll", "writeByte", "b", "writeDecimalLong", "v", "writeHexadecimalUnsignedLong", "writeInt", "i", "writeIntLe", "writeLong", "writeLongLe", "writeShort", "s", "writeShortLe", "writeString", "string", "charset", "Ljava/nio/charset/Charset;", "beginIndex", "endIndex", "writeUtf8", "writeUtf8CodePoint", "codePoint", "okio"})
public final class RealBufferedSink
implements BufferedSink {
    @JvmField
    @NotNull
    public final Buffer bufferField;
    @JvmField
    public boolean closed;
    @JvmField
    @NotNull
    public final Sink sink;

    public static /* synthetic */ void getBuffer$annotations() {
    }

    @Override
    @NotNull
    public Buffer getBuffer() {
        int $i$f$getBuffer = 0;
        return this.bufferField;
    }

    @Override
    @NotNull
    public Buffer buffer() {
        return this.bufferField;
    }

    @Override
    public void write(@NotNull Buffer source2, long byteCount) {
        Intrinsics.checkNotNullParameter(source2, "source");
        RealBufferedSink $this$commonWrite$iv = this;
        boolean $i$f$commonWrite = false;
        boolean bl = !$this$commonWrite$iv.closed;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "closed";
            throw (Throwable)new IllegalStateException(string.toString());
        }
        RealBufferedSink this_$iv$iv = $this$commonWrite$iv;
        boolean $i$f$getBuffer = false;
        this_$iv$iv.bufferField.write(source2, byteCount);
        $this$commonWrite$iv.emitCompleteSegments();
    }

    @Override
    @NotNull
    public BufferedSink write(@NotNull ByteString byteString) {
        Intrinsics.checkNotNullParameter(byteString, "byteString");
        RealBufferedSink $this$commonWrite$iv = this;
        boolean $i$f$commonWrite = false;
        boolean bl = !$this$commonWrite$iv.closed;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "closed";
            throw (Throwable)new IllegalStateException(string.toString());
        }
        RealBufferedSink this_$iv$iv = $this$commonWrite$iv;
        boolean $i$f$getBuffer = false;
        this_$iv$iv.bufferField.write(byteString);
        return $this$commonWrite$iv.emitCompleteSegments();
    }

    @Override
    @NotNull
    public BufferedSink write(@NotNull ByteString byteString, int offset, int byteCount) {
        Intrinsics.checkNotNullParameter(byteString, "byteString");
        RealBufferedSink $this$commonWrite$iv = this;
        boolean $i$f$commonWrite = false;
        boolean bl = !$this$commonWrite$iv.closed;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "closed";
            throw (Throwable)new IllegalStateException(string.toString());
        }
        RealBufferedSink this_$iv$iv = $this$commonWrite$iv;
        boolean $i$f$getBuffer = false;
        this_$iv$iv.bufferField.write(byteString, offset, byteCount);
        return $this$commonWrite$iv.emitCompleteSegments();
    }

    @Override
    @NotNull
    public BufferedSink writeUtf8(@NotNull String string) {
        Intrinsics.checkNotNullParameter(string, "string");
        RealBufferedSink $this$commonWriteUtf8$iv = this;
        boolean $i$f$commonWriteUtf8 = false;
        boolean bl = !$this$commonWriteUtf8$iv.closed;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string2 = "closed";
            throw (Throwable)new IllegalStateException(string2.toString());
        }
        RealBufferedSink this_$iv$iv = $this$commonWriteUtf8$iv;
        boolean $i$f$getBuffer = false;
        this_$iv$iv.bufferField.writeUtf8(string);
        return $this$commonWriteUtf8$iv.emitCompleteSegments();
    }

    @Override
    @NotNull
    public BufferedSink writeUtf8(@NotNull String string, int beginIndex, int endIndex) {
        Intrinsics.checkNotNullParameter(string, "string");
        RealBufferedSink $this$commonWriteUtf8$iv = this;
        boolean $i$f$commonWriteUtf8 = false;
        boolean bl = !$this$commonWriteUtf8$iv.closed;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string2 = "closed";
            throw (Throwable)new IllegalStateException(string2.toString());
        }
        RealBufferedSink this_$iv$iv = $this$commonWriteUtf8$iv;
        boolean $i$f$getBuffer = false;
        this_$iv$iv.bufferField.writeUtf8(string, beginIndex, endIndex);
        return $this$commonWriteUtf8$iv.emitCompleteSegments();
    }

    @Override
    @NotNull
    public BufferedSink writeUtf8CodePoint(int codePoint) {
        RealBufferedSink $this$commonWriteUtf8CodePoint$iv = this;
        boolean $i$f$commonWriteUtf8CodePoint = false;
        boolean bl = !$this$commonWriteUtf8CodePoint$iv.closed;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "closed";
            throw (Throwable)new IllegalStateException(string.toString());
        }
        RealBufferedSink this_$iv$iv = $this$commonWriteUtf8CodePoint$iv;
        boolean $i$f$getBuffer = false;
        this_$iv$iv.bufferField.writeUtf8CodePoint(codePoint);
        return $this$commonWriteUtf8CodePoint$iv.emitCompleteSegments();
    }

    @Override
    @NotNull
    public BufferedSink writeString(@NotNull String string, @NotNull Charset charset) {
        Intrinsics.checkNotNullParameter(string, "string");
        Intrinsics.checkNotNullParameter(charset, "charset");
        boolean bl = !this.closed;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string2 = "closed";
            throw (Throwable)new IllegalStateException(string2.toString());
        }
        RealBufferedSink this_$iv = this;
        boolean $i$f$getBuffer = false;
        this_$iv.bufferField.writeString(string, charset);
        return this.emitCompleteSegments();
    }

    @Override
    @NotNull
    public BufferedSink writeString(@NotNull String string, int beginIndex, int endIndex, @NotNull Charset charset) {
        Intrinsics.checkNotNullParameter(string, "string");
        Intrinsics.checkNotNullParameter(charset, "charset");
        boolean bl = !this.closed;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string2 = "closed";
            throw (Throwable)new IllegalStateException(string2.toString());
        }
        RealBufferedSink this_$iv = this;
        boolean $i$f$getBuffer = false;
        this_$iv.bufferField.writeString(string, beginIndex, endIndex, charset);
        return this.emitCompleteSegments();
    }

    @Override
    @NotNull
    public BufferedSink write(@NotNull byte[] source2) {
        Intrinsics.checkNotNullParameter(source2, "source");
        RealBufferedSink $this$commonWrite$iv = this;
        boolean $i$f$commonWrite = false;
        boolean bl = !$this$commonWrite$iv.closed;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "closed";
            throw (Throwable)new IllegalStateException(string.toString());
        }
        RealBufferedSink this_$iv$iv = $this$commonWrite$iv;
        boolean $i$f$getBuffer = false;
        this_$iv$iv.bufferField.write(source2);
        return $this$commonWrite$iv.emitCompleteSegments();
    }

    @Override
    @NotNull
    public BufferedSink write(@NotNull byte[] source2, int offset, int byteCount) {
        Intrinsics.checkNotNullParameter(source2, "source");
        RealBufferedSink $this$commonWrite$iv = this;
        boolean $i$f$commonWrite = false;
        boolean bl = !$this$commonWrite$iv.closed;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "closed";
            throw (Throwable)new IllegalStateException(string.toString());
        }
        RealBufferedSink this_$iv$iv = $this$commonWrite$iv;
        boolean $i$f$getBuffer = false;
        this_$iv$iv.bufferField.write(source2, offset, byteCount);
        return $this$commonWrite$iv.emitCompleteSegments();
    }

    @Override
    public int write(@NotNull ByteBuffer source2) {
        Intrinsics.checkNotNullParameter(source2, "source");
        boolean bl = !this.closed;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "closed";
            throw (Throwable)new IllegalStateException(string.toString());
        }
        RealBufferedSink this_$iv = this;
        boolean $i$f$getBuffer = false;
        int result = this_$iv.bufferField.write(source2);
        this.emitCompleteSegments();
        return result;
    }

    @Override
    public long writeAll(@NotNull Source source2) {
        Intrinsics.checkNotNullParameter(source2, "source");
        RealBufferedSink $this$commonWriteAll$iv = this;
        boolean $i$f$commonWriteAll = false;
        long totalBytesRead$iv = 0L;
        while (true) {
            RealBufferedSink this_$iv$iv = $this$commonWriteAll$iv;
            boolean $i$f$getBuffer = false;
            long readCount$iv = source2.read(this_$iv$iv.bufferField, 8192);
            if (readCount$iv == -1L) break;
            totalBytesRead$iv += readCount$iv;
            $this$commonWriteAll$iv.emitCompleteSegments();
        }
        return totalBytesRead$iv;
    }

    @Override
    @NotNull
    public BufferedSink write(@NotNull Source source2, long byteCount) {
        long read$iv;
        Intrinsics.checkNotNullParameter(source2, "source");
        RealBufferedSink $this$commonWrite$iv = this;
        boolean $i$f$commonWrite = false;
        for (long byteCount$iv = byteCount; byteCount$iv > 0L; byteCount$iv -= read$iv) {
            RealBufferedSink this_$iv$iv = $this$commonWrite$iv;
            boolean $i$f$getBuffer = false;
            read$iv = source2.read(this_$iv$iv.bufferField, byteCount$iv);
            if (read$iv == -1L) {
                throw (Throwable)new EOFException();
            }
            $this$commonWrite$iv.emitCompleteSegments();
        }
        return $this$commonWrite$iv;
    }

    @Override
    @NotNull
    public BufferedSink writeByte(int b) {
        RealBufferedSink $this$commonWriteByte$iv = this;
        boolean $i$f$commonWriteByte = false;
        boolean bl = !$this$commonWriteByte$iv.closed;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "closed";
            throw (Throwable)new IllegalStateException(string.toString());
        }
        RealBufferedSink this_$iv$iv = $this$commonWriteByte$iv;
        boolean $i$f$getBuffer = false;
        this_$iv$iv.bufferField.writeByte(b);
        return $this$commonWriteByte$iv.emitCompleteSegments();
    }

    @Override
    @NotNull
    public BufferedSink writeShort(int s2) {
        RealBufferedSink $this$commonWriteShort$iv = this;
        boolean $i$f$commonWriteShort = false;
        boolean bl = !$this$commonWriteShort$iv.closed;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "closed";
            throw (Throwable)new IllegalStateException(string.toString());
        }
        RealBufferedSink this_$iv$iv = $this$commonWriteShort$iv;
        boolean $i$f$getBuffer = false;
        this_$iv$iv.bufferField.writeShort(s2);
        return $this$commonWriteShort$iv.emitCompleteSegments();
    }

    @Override
    @NotNull
    public BufferedSink writeShortLe(int s2) {
        RealBufferedSink $this$commonWriteShortLe$iv = this;
        boolean $i$f$commonWriteShortLe = false;
        boolean bl = !$this$commonWriteShortLe$iv.closed;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "closed";
            throw (Throwable)new IllegalStateException(string.toString());
        }
        RealBufferedSink this_$iv$iv = $this$commonWriteShortLe$iv;
        boolean $i$f$getBuffer = false;
        this_$iv$iv.bufferField.writeShortLe(s2);
        return $this$commonWriteShortLe$iv.emitCompleteSegments();
    }

    @Override
    @NotNull
    public BufferedSink writeInt(int i) {
        RealBufferedSink $this$commonWriteInt$iv = this;
        boolean $i$f$commonWriteInt = false;
        boolean bl = !$this$commonWriteInt$iv.closed;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "closed";
            throw (Throwable)new IllegalStateException(string.toString());
        }
        RealBufferedSink this_$iv$iv = $this$commonWriteInt$iv;
        boolean $i$f$getBuffer = false;
        this_$iv$iv.bufferField.writeInt(i);
        return $this$commonWriteInt$iv.emitCompleteSegments();
    }

    @Override
    @NotNull
    public BufferedSink writeIntLe(int i) {
        RealBufferedSink $this$commonWriteIntLe$iv = this;
        boolean $i$f$commonWriteIntLe = false;
        boolean bl = !$this$commonWriteIntLe$iv.closed;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "closed";
            throw (Throwable)new IllegalStateException(string.toString());
        }
        RealBufferedSink this_$iv$iv = $this$commonWriteIntLe$iv;
        boolean $i$f$getBuffer = false;
        this_$iv$iv.bufferField.writeIntLe(i);
        return $this$commonWriteIntLe$iv.emitCompleteSegments();
    }

    @Override
    @NotNull
    public BufferedSink writeLong(long v) {
        RealBufferedSink $this$commonWriteLong$iv = this;
        boolean $i$f$commonWriteLong = false;
        boolean bl = !$this$commonWriteLong$iv.closed;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "closed";
            throw (Throwable)new IllegalStateException(string.toString());
        }
        RealBufferedSink this_$iv$iv = $this$commonWriteLong$iv;
        boolean $i$f$getBuffer = false;
        this_$iv$iv.bufferField.writeLong(v);
        return $this$commonWriteLong$iv.emitCompleteSegments();
    }

    @Override
    @NotNull
    public BufferedSink writeLongLe(long v) {
        RealBufferedSink $this$commonWriteLongLe$iv = this;
        boolean $i$f$commonWriteLongLe = false;
        boolean bl = !$this$commonWriteLongLe$iv.closed;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "closed";
            throw (Throwable)new IllegalStateException(string.toString());
        }
        RealBufferedSink this_$iv$iv = $this$commonWriteLongLe$iv;
        boolean $i$f$getBuffer = false;
        this_$iv$iv.bufferField.writeLongLe(v);
        return $this$commonWriteLongLe$iv.emitCompleteSegments();
    }

    @Override
    @NotNull
    public BufferedSink writeDecimalLong(long v) {
        RealBufferedSink $this$commonWriteDecimalLong$iv = this;
        boolean $i$f$commonWriteDecimalLong = false;
        boolean bl = !$this$commonWriteDecimalLong$iv.closed;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "closed";
            throw (Throwable)new IllegalStateException(string.toString());
        }
        RealBufferedSink this_$iv$iv = $this$commonWriteDecimalLong$iv;
        boolean $i$f$getBuffer = false;
        this_$iv$iv.bufferField.writeDecimalLong(v);
        return $this$commonWriteDecimalLong$iv.emitCompleteSegments();
    }

    @Override
    @NotNull
    public BufferedSink writeHexadecimalUnsignedLong(long v) {
        RealBufferedSink $this$commonWriteHexadecimalUnsignedLong$iv = this;
        boolean $i$f$commonWriteHexadecimalUnsignedLong = false;
        boolean bl = !$this$commonWriteHexadecimalUnsignedLong$iv.closed;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "closed";
            throw (Throwable)new IllegalStateException(string.toString());
        }
        RealBufferedSink this_$iv$iv = $this$commonWriteHexadecimalUnsignedLong$iv;
        boolean $i$f$getBuffer = false;
        this_$iv$iv.bufferField.writeHexadecimalUnsignedLong(v);
        return $this$commonWriteHexadecimalUnsignedLong$iv.emitCompleteSegments();
    }

    @Override
    @NotNull
    public BufferedSink emitCompleteSegments() {
        RealBufferedSink $this$commonEmitCompleteSegments$iv = this;
        boolean $i$f$commonEmitCompleteSegments = false;
        boolean bl = !$this$commonEmitCompleteSegments$iv.closed;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "closed";
            throw (Throwable)new IllegalStateException(string.toString());
        }
        RealBufferedSink this_$iv$iv = $this$commonEmitCompleteSegments$iv;
        boolean $i$f$getBuffer = false;
        long byteCount$iv = this_$iv$iv.bufferField.completeSegmentByteCount();
        if (byteCount$iv > 0L) {
            this_$iv$iv = $this$commonEmitCompleteSegments$iv;
            $i$f$getBuffer = false;
            $this$commonEmitCompleteSegments$iv.sink.write(this_$iv$iv.bufferField, byteCount$iv);
        }
        return $this$commonEmitCompleteSegments$iv;
    }

    @Override
    @NotNull
    public BufferedSink emit() {
        RealBufferedSink $this$commonEmit$iv = this;
        boolean $i$f$commonEmit = false;
        boolean bl = !$this$commonEmit$iv.closed;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "closed";
            throw (Throwable)new IllegalStateException(string.toString());
        }
        RealBufferedSink this_$iv$iv = $this$commonEmit$iv;
        boolean $i$f$getBuffer = false;
        long byteCount$iv = this_$iv$iv.bufferField.size();
        if (byteCount$iv > 0L) {
            this_$iv$iv = $this$commonEmit$iv;
            $i$f$getBuffer = false;
            $this$commonEmit$iv.sink.write(this_$iv$iv.bufferField, byteCount$iv);
        }
        return $this$commonEmit$iv;
    }

    @Override
    @NotNull
    public OutputStream outputStream() {
        return new OutputStream(this){
            final /* synthetic */ RealBufferedSink this$0;

            public void write(int b) {
                if (this.this$0.closed) {
                    throw (Throwable)new IOException("closed");
                }
                RealBufferedSink this_$iv = this.this$0;
                boolean $i$f$getBuffer = false;
                this_$iv.bufferField.writeByte((byte)b);
                this.this$0.emitCompleteSegments();
            }

            public void write(@NotNull byte[] data, int offset, int byteCount) {
                Intrinsics.checkNotNullParameter(data, "data");
                if (this.this$0.closed) {
                    throw (Throwable)new IOException("closed");
                }
                RealBufferedSink this_$iv = this.this$0;
                boolean $i$f$getBuffer = false;
                this_$iv.bufferField.write(data, offset, byteCount);
                this.this$0.emitCompleteSegments();
            }

            public void flush() {
                if (!this.this$0.closed) {
                    this.this$0.flush();
                }
            }

            public void close() {
                this.this$0.close();
            }

            @NotNull
            public String toString() {
                return this.this$0 + ".outputStream()";
            }
            {
                this.this$0 = this$0;
            }
        };
    }

    @Override
    public void flush() {
        RealBufferedSink $this$commonFlush$iv = this;
        boolean $i$f$commonFlush = false;
        boolean bl = !$this$commonFlush$iv.closed;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "closed";
            throw (Throwable)new IllegalStateException(string.toString());
        }
        RealBufferedSink this_$iv$iv = $this$commonFlush$iv;
        boolean $i$f$getBuffer = false;
        if (this_$iv$iv.bufferField.size() > 0L) {
            this_$iv$iv = $this$commonFlush$iv;
            $i$f$getBuffer = false;
            this_$iv$iv = $this$commonFlush$iv;
            $i$f$getBuffer = false;
            $this$commonFlush$iv.sink.write(this_$iv$iv.bufferField, this_$iv$iv.bufferField.size());
        }
        $this$commonFlush$iv.sink.flush();
    }

    @Override
    public boolean isOpen() {
        return !this.closed;
    }

    @Override
    public void close() {
        RealBufferedSink $this$commonClose$iv = this;
        boolean $i$f$commonClose = false;
        if (!$this$commonClose$iv.closed) {
            Throwable thrown$iv;
            block7: {
                thrown$iv = null;
                try {
                    RealBufferedSink this_$iv$iv = $this$commonClose$iv;
                    boolean $i$f$getBuffer = false;
                    if (this_$iv$iv.bufferField.size() > 0L) {
                        this_$iv$iv = $this$commonClose$iv;
                        $i$f$getBuffer = false;
                        this_$iv$iv = $this$commonClose$iv;
                        $i$f$getBuffer = false;
                        $this$commonClose$iv.sink.write(this_$iv$iv.bufferField, this_$iv$iv.bufferField.size());
                    }
                } catch (Throwable e$iv) {
                    thrown$iv = e$iv;
                }
                try {
                    $this$commonClose$iv.sink.close();
                } catch (Throwable e$iv) {
                    if (thrown$iv != null) break block7;
                    thrown$iv = e$iv;
                }
            }
            $this$commonClose$iv.closed = true;
            if (thrown$iv != null) {
                throw thrown$iv;
            }
        }
    }

    @Override
    @NotNull
    public Timeout timeout() {
        RealBufferedSink $this$commonTimeout$iv = this;
        boolean $i$f$commonTimeout = false;
        return $this$commonTimeout$iv.sink.timeout();
    }

    @NotNull
    public String toString() {
        RealBufferedSink $this$commonToString$iv = this;
        boolean $i$f$commonToString = false;
        return "buffer(" + $this$commonToString$iv.sink + ')';
    }

    public RealBufferedSink(@NotNull Sink sink2) {
        Intrinsics.checkNotNullParameter(sink2, "sink");
        this.sink = sink2;
        this.bufferField = new Buffer();
    }
}

