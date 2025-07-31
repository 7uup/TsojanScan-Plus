/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okio.internal;

import java.io.EOFException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okio.Buffer;
import okio.BufferedSink;
import okio.ByteString;
import okio.RealBufferedSink;
import okio.Source;
import okio.Timeout;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=2, d1={"\u0000D\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0015\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0080\b\u001a\r\u0010\u0003\u001a\u00020\u0004*\u00020\u0002H\u0080\b\u001a\r\u0010\u0005\u001a\u00020\u0004*\u00020\u0002H\u0080\b\u001a\r\u0010\u0006\u001a\u00020\u0001*\u00020\u0002H\u0080\b\u001a\r\u0010\u0007\u001a\u00020\b*\u00020\u0002H\u0080\b\u001a\r\u0010\t\u001a\u00020\n*\u00020\u0002H\u0080\b\u001a\u0015\u0010\u000b\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\f\u001a\u00020\rH\u0080\b\u001a%\u0010\u000b\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fH\u0080\b\u001a\u001d\u0010\u000b\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\f\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u0012H\u0080\b\u001a\u0015\u0010\u000b\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u0014H\u0080\b\u001a%\u0010\u000b\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fH\u0080\b\u001a\u001d\u0010\u000b\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\f\u001a\u00020\u00152\u0006\u0010\u0010\u001a\u00020\u0012H\u0080\b\u001a\u0015\u0010\u0016\u001a\u00020\u0012*\u00020\u00022\u0006\u0010\f\u001a\u00020\u0015H\u0080\b\u001a\u0015\u0010\u0017\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u000fH\u0080\b\u001a\u0015\u0010\u0019\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u0012H\u0080\b\u001a\u0015\u0010\u001b\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u0012H\u0080\b\u001a\u0015\u0010\u001c\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u001d\u001a\u00020\u000fH\u0080\b\u001a\u0015\u0010\u001e\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u001d\u001a\u00020\u000fH\u0080\b\u001a\u0015\u0010\u001f\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u0012H\u0080\b\u001a\u0015\u0010 \u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u0012H\u0080\b\u001a\u0015\u0010!\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\"\u001a\u00020\u000fH\u0080\b\u001a\u0015\u0010#\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\"\u001a\u00020\u000fH\u0080\b\u001a\u0015\u0010$\u001a\u00020\u0004*\u00020\u00022\u0006\u0010%\u001a\u00020\nH\u0080\b\u001a%\u0010$\u001a\u00020\u0004*\u00020\u00022\u0006\u0010%\u001a\u00020\n2\u0006\u0010&\u001a\u00020\u000f2\u0006\u0010'\u001a\u00020\u000fH\u0080\b\u001a\u0015\u0010(\u001a\u00020\u0004*\u00020\u00022\u0006\u0010)\u001a\u00020\u000fH\u0080\b\u00a8\u0006*"}, d2={"commonClose", "", "Lokio/RealBufferedSink;", "commonEmit", "Lokio/BufferedSink;", "commonEmitCompleteSegments", "commonFlush", "commonTimeout", "Lokio/Timeout;", "commonToString", "", "commonWrite", "source", "", "offset", "", "byteCount", "Lokio/Buffer;", "", "byteString", "Lokio/ByteString;", "Lokio/Source;", "commonWriteAll", "commonWriteByte", "b", "commonWriteDecimalLong", "v", "commonWriteHexadecimalUnsignedLong", "commonWriteInt", "i", "commonWriteIntLe", "commonWriteLong", "commonWriteLongLe", "commonWriteShort", "s", "commonWriteShortLe", "commonWriteUtf8", "string", "beginIndex", "endIndex", "commonWriteUtf8CodePoint", "codePoint", "okio"})
public final class RealBufferedSinkKt {
    public static final void commonWrite(@NotNull RealBufferedSink $this$commonWrite, @NotNull Buffer source2, long byteCount) {
        int $i$f$commonWrite = 0;
        Intrinsics.checkNotNullParameter($this$commonWrite, "$this$commonWrite");
        Intrinsics.checkNotNullParameter(source2, "source");
        boolean bl = !$this$commonWrite.closed;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "closed";
            throw (Throwable)new IllegalStateException(string.toString());
        }
        RealBufferedSink this_$iv = $this$commonWrite;
        boolean $i$f$getBuffer = false;
        this_$iv.bufferField.write(source2, byteCount);
        $this$commonWrite.emitCompleteSegments();
    }

    @NotNull
    public static final BufferedSink commonWrite(@NotNull RealBufferedSink $this$commonWrite, @NotNull ByteString byteString) {
        int $i$f$commonWrite = 0;
        Intrinsics.checkNotNullParameter($this$commonWrite, "$this$commonWrite");
        Intrinsics.checkNotNullParameter(byteString, "byteString");
        boolean bl = !$this$commonWrite.closed;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "closed";
            throw (Throwable)new IllegalStateException(string.toString());
        }
        RealBufferedSink this_$iv = $this$commonWrite;
        boolean $i$f$getBuffer = false;
        this_$iv.bufferField.write(byteString);
        return $this$commonWrite.emitCompleteSegments();
    }

    @NotNull
    public static final BufferedSink commonWrite(@NotNull RealBufferedSink $this$commonWrite, @NotNull ByteString byteString, int offset, int byteCount) {
        int $i$f$commonWrite = 0;
        Intrinsics.checkNotNullParameter($this$commonWrite, "$this$commonWrite");
        Intrinsics.checkNotNullParameter(byteString, "byteString");
        boolean bl = !$this$commonWrite.closed;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "closed";
            throw (Throwable)new IllegalStateException(string.toString());
        }
        RealBufferedSink this_$iv = $this$commonWrite;
        boolean $i$f$getBuffer = false;
        this_$iv.bufferField.write(byteString, offset, byteCount);
        return $this$commonWrite.emitCompleteSegments();
    }

    @NotNull
    public static final BufferedSink commonWriteUtf8(@NotNull RealBufferedSink $this$commonWriteUtf8, @NotNull String string) {
        int $i$f$commonWriteUtf8 = 0;
        Intrinsics.checkNotNullParameter($this$commonWriteUtf8, "$this$commonWriteUtf8");
        Intrinsics.checkNotNullParameter(string, "string");
        boolean bl = !$this$commonWriteUtf8.closed;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string2 = "closed";
            throw (Throwable)new IllegalStateException(string2.toString());
        }
        RealBufferedSink this_$iv = $this$commonWriteUtf8;
        boolean $i$f$getBuffer = false;
        this_$iv.bufferField.writeUtf8(string);
        return $this$commonWriteUtf8.emitCompleteSegments();
    }

    @NotNull
    public static final BufferedSink commonWriteUtf8(@NotNull RealBufferedSink $this$commonWriteUtf8, @NotNull String string, int beginIndex, int endIndex) {
        int $i$f$commonWriteUtf8 = 0;
        Intrinsics.checkNotNullParameter($this$commonWriteUtf8, "$this$commonWriteUtf8");
        Intrinsics.checkNotNullParameter(string, "string");
        boolean bl = !$this$commonWriteUtf8.closed;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string2 = "closed";
            throw (Throwable)new IllegalStateException(string2.toString());
        }
        RealBufferedSink this_$iv = $this$commonWriteUtf8;
        boolean $i$f$getBuffer = false;
        this_$iv.bufferField.writeUtf8(string, beginIndex, endIndex);
        return $this$commonWriteUtf8.emitCompleteSegments();
    }

    @NotNull
    public static final BufferedSink commonWriteUtf8CodePoint(@NotNull RealBufferedSink $this$commonWriteUtf8CodePoint, int codePoint) {
        int $i$f$commonWriteUtf8CodePoint = 0;
        Intrinsics.checkNotNullParameter($this$commonWriteUtf8CodePoint, "$this$commonWriteUtf8CodePoint");
        boolean bl = !$this$commonWriteUtf8CodePoint.closed;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "closed";
            throw (Throwable)new IllegalStateException(string.toString());
        }
        RealBufferedSink this_$iv = $this$commonWriteUtf8CodePoint;
        boolean $i$f$getBuffer = false;
        this_$iv.bufferField.writeUtf8CodePoint(codePoint);
        return $this$commonWriteUtf8CodePoint.emitCompleteSegments();
    }

    @NotNull
    public static final BufferedSink commonWrite(@NotNull RealBufferedSink $this$commonWrite, @NotNull byte[] source2) {
        int $i$f$commonWrite = 0;
        Intrinsics.checkNotNullParameter($this$commonWrite, "$this$commonWrite");
        Intrinsics.checkNotNullParameter(source2, "source");
        boolean bl = !$this$commonWrite.closed;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "closed";
            throw (Throwable)new IllegalStateException(string.toString());
        }
        RealBufferedSink this_$iv = $this$commonWrite;
        boolean $i$f$getBuffer = false;
        this_$iv.bufferField.write(source2);
        return $this$commonWrite.emitCompleteSegments();
    }

    @NotNull
    public static final BufferedSink commonWrite(@NotNull RealBufferedSink $this$commonWrite, @NotNull byte[] source2, int offset, int byteCount) {
        int $i$f$commonWrite = 0;
        Intrinsics.checkNotNullParameter($this$commonWrite, "$this$commonWrite");
        Intrinsics.checkNotNullParameter(source2, "source");
        boolean bl = !$this$commonWrite.closed;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "closed";
            throw (Throwable)new IllegalStateException(string.toString());
        }
        RealBufferedSink this_$iv = $this$commonWrite;
        boolean $i$f$getBuffer = false;
        this_$iv.bufferField.write(source2, offset, byteCount);
        return $this$commonWrite.emitCompleteSegments();
    }

    public static final long commonWriteAll(@NotNull RealBufferedSink $this$commonWriteAll, @NotNull Source source2) {
        int $i$f$commonWriteAll = 0;
        Intrinsics.checkNotNullParameter($this$commonWriteAll, "$this$commonWriteAll");
        Intrinsics.checkNotNullParameter(source2, "source");
        long totalBytesRead = 0L;
        while (true) {
            RealBufferedSink this_$iv = $this$commonWriteAll;
            boolean $i$f$getBuffer = false;
            long readCount = source2.read(this_$iv.bufferField, 8192);
            if (readCount == -1L) break;
            totalBytesRead += readCount;
            $this$commonWriteAll.emitCompleteSegments();
        }
        return totalBytesRead;
    }

    @NotNull
    public static final BufferedSink commonWrite(@NotNull RealBufferedSink $this$commonWrite, @NotNull Source source2, long byteCount) {
        long read;
        int $i$f$commonWrite = 0;
        Intrinsics.checkNotNullParameter($this$commonWrite, "$this$commonWrite");
        Intrinsics.checkNotNullParameter(source2, "source");
        for (long byteCount2 = byteCount; byteCount2 > 0L; byteCount2 -= read) {
            RealBufferedSink this_$iv = $this$commonWrite;
            boolean $i$f$getBuffer = false;
            read = source2.read(this_$iv.bufferField, byteCount2);
            if (read == -1L) {
                throw (Throwable)new EOFException();
            }
            $this$commonWrite.emitCompleteSegments();
        }
        return $this$commonWrite;
    }

    @NotNull
    public static final BufferedSink commonWriteByte(@NotNull RealBufferedSink $this$commonWriteByte, int b) {
        int $i$f$commonWriteByte = 0;
        Intrinsics.checkNotNullParameter($this$commonWriteByte, "$this$commonWriteByte");
        boolean bl = !$this$commonWriteByte.closed;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "closed";
            throw (Throwable)new IllegalStateException(string.toString());
        }
        RealBufferedSink this_$iv = $this$commonWriteByte;
        boolean $i$f$getBuffer = false;
        this_$iv.bufferField.writeByte(b);
        return $this$commonWriteByte.emitCompleteSegments();
    }

    @NotNull
    public static final BufferedSink commonWriteShort(@NotNull RealBufferedSink $this$commonWriteShort, int s2) {
        int $i$f$commonWriteShort = 0;
        Intrinsics.checkNotNullParameter($this$commonWriteShort, "$this$commonWriteShort");
        boolean bl = !$this$commonWriteShort.closed;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "closed";
            throw (Throwable)new IllegalStateException(string.toString());
        }
        RealBufferedSink this_$iv = $this$commonWriteShort;
        boolean $i$f$getBuffer = false;
        this_$iv.bufferField.writeShort(s2);
        return $this$commonWriteShort.emitCompleteSegments();
    }

    @NotNull
    public static final BufferedSink commonWriteShortLe(@NotNull RealBufferedSink $this$commonWriteShortLe, int s2) {
        int $i$f$commonWriteShortLe = 0;
        Intrinsics.checkNotNullParameter($this$commonWriteShortLe, "$this$commonWriteShortLe");
        boolean bl = !$this$commonWriteShortLe.closed;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "closed";
            throw (Throwable)new IllegalStateException(string.toString());
        }
        RealBufferedSink this_$iv = $this$commonWriteShortLe;
        boolean $i$f$getBuffer = false;
        this_$iv.bufferField.writeShortLe(s2);
        return $this$commonWriteShortLe.emitCompleteSegments();
    }

    @NotNull
    public static final BufferedSink commonWriteInt(@NotNull RealBufferedSink $this$commonWriteInt, int i) {
        int $i$f$commonWriteInt = 0;
        Intrinsics.checkNotNullParameter($this$commonWriteInt, "$this$commonWriteInt");
        boolean bl = !$this$commonWriteInt.closed;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "closed";
            throw (Throwable)new IllegalStateException(string.toString());
        }
        RealBufferedSink this_$iv = $this$commonWriteInt;
        boolean $i$f$getBuffer = false;
        this_$iv.bufferField.writeInt(i);
        return $this$commonWriteInt.emitCompleteSegments();
    }

    @NotNull
    public static final BufferedSink commonWriteIntLe(@NotNull RealBufferedSink $this$commonWriteIntLe, int i) {
        int $i$f$commonWriteIntLe = 0;
        Intrinsics.checkNotNullParameter($this$commonWriteIntLe, "$this$commonWriteIntLe");
        boolean bl = !$this$commonWriteIntLe.closed;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "closed";
            throw (Throwable)new IllegalStateException(string.toString());
        }
        RealBufferedSink this_$iv = $this$commonWriteIntLe;
        boolean $i$f$getBuffer = false;
        this_$iv.bufferField.writeIntLe(i);
        return $this$commonWriteIntLe.emitCompleteSegments();
    }

    @NotNull
    public static final BufferedSink commonWriteLong(@NotNull RealBufferedSink $this$commonWriteLong, long v) {
        int $i$f$commonWriteLong = 0;
        Intrinsics.checkNotNullParameter($this$commonWriteLong, "$this$commonWriteLong");
        boolean bl = !$this$commonWriteLong.closed;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "closed";
            throw (Throwable)new IllegalStateException(string.toString());
        }
        RealBufferedSink this_$iv = $this$commonWriteLong;
        boolean $i$f$getBuffer = false;
        this_$iv.bufferField.writeLong(v);
        return $this$commonWriteLong.emitCompleteSegments();
    }

    @NotNull
    public static final BufferedSink commonWriteLongLe(@NotNull RealBufferedSink $this$commonWriteLongLe, long v) {
        int $i$f$commonWriteLongLe = 0;
        Intrinsics.checkNotNullParameter($this$commonWriteLongLe, "$this$commonWriteLongLe");
        boolean bl = !$this$commonWriteLongLe.closed;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "closed";
            throw (Throwable)new IllegalStateException(string.toString());
        }
        RealBufferedSink this_$iv = $this$commonWriteLongLe;
        boolean $i$f$getBuffer = false;
        this_$iv.bufferField.writeLongLe(v);
        return $this$commonWriteLongLe.emitCompleteSegments();
    }

    @NotNull
    public static final BufferedSink commonWriteDecimalLong(@NotNull RealBufferedSink $this$commonWriteDecimalLong, long v) {
        int $i$f$commonWriteDecimalLong = 0;
        Intrinsics.checkNotNullParameter($this$commonWriteDecimalLong, "$this$commonWriteDecimalLong");
        boolean bl = !$this$commonWriteDecimalLong.closed;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "closed";
            throw (Throwable)new IllegalStateException(string.toString());
        }
        RealBufferedSink this_$iv = $this$commonWriteDecimalLong;
        boolean $i$f$getBuffer = false;
        this_$iv.bufferField.writeDecimalLong(v);
        return $this$commonWriteDecimalLong.emitCompleteSegments();
    }

    @NotNull
    public static final BufferedSink commonWriteHexadecimalUnsignedLong(@NotNull RealBufferedSink $this$commonWriteHexadecimalUnsignedLong, long v) {
        int $i$f$commonWriteHexadecimalUnsignedLong = 0;
        Intrinsics.checkNotNullParameter($this$commonWriteHexadecimalUnsignedLong, "$this$commonWriteHexadecimalUnsignedLong");
        boolean bl = !$this$commonWriteHexadecimalUnsignedLong.closed;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "closed";
            throw (Throwable)new IllegalStateException(string.toString());
        }
        RealBufferedSink this_$iv = $this$commonWriteHexadecimalUnsignedLong;
        boolean $i$f$getBuffer = false;
        this_$iv.bufferField.writeHexadecimalUnsignedLong(v);
        return $this$commonWriteHexadecimalUnsignedLong.emitCompleteSegments();
    }

    @NotNull
    public static final BufferedSink commonEmitCompleteSegments(@NotNull RealBufferedSink $this$commonEmitCompleteSegments) {
        int $i$f$commonEmitCompleteSegments = 0;
        Intrinsics.checkNotNullParameter($this$commonEmitCompleteSegments, "$this$commonEmitCompleteSegments");
        boolean bl = !$this$commonEmitCompleteSegments.closed;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "closed";
            throw (Throwable)new IllegalStateException(string.toString());
        }
        RealBufferedSink this_$iv = $this$commonEmitCompleteSegments;
        boolean $i$f$getBuffer = false;
        long byteCount = this_$iv.bufferField.completeSegmentByteCount();
        if (byteCount > 0L) {
            this_$iv = $this$commonEmitCompleteSegments;
            $i$f$getBuffer = false;
            $this$commonEmitCompleteSegments.sink.write(this_$iv.bufferField, byteCount);
        }
        return $this$commonEmitCompleteSegments;
    }

    @NotNull
    public static final BufferedSink commonEmit(@NotNull RealBufferedSink $this$commonEmit) {
        int $i$f$commonEmit = 0;
        Intrinsics.checkNotNullParameter($this$commonEmit, "$this$commonEmit");
        boolean bl = !$this$commonEmit.closed;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "closed";
            throw (Throwable)new IllegalStateException(string.toString());
        }
        RealBufferedSink this_$iv = $this$commonEmit;
        boolean $i$f$getBuffer = false;
        long byteCount = this_$iv.bufferField.size();
        if (byteCount > 0L) {
            this_$iv = $this$commonEmit;
            $i$f$getBuffer = false;
            $this$commonEmit.sink.write(this_$iv.bufferField, byteCount);
        }
        return $this$commonEmit;
    }

    public static final void commonFlush(@NotNull RealBufferedSink $this$commonFlush) {
        int $i$f$commonFlush = 0;
        Intrinsics.checkNotNullParameter($this$commonFlush, "$this$commonFlush");
        boolean bl = !$this$commonFlush.closed;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "closed";
            throw (Throwable)new IllegalStateException(string.toString());
        }
        RealBufferedSink this_$iv = $this$commonFlush;
        boolean $i$f$getBuffer = false;
        if (this_$iv.bufferField.size() > 0L) {
            this_$iv = $this$commonFlush;
            $i$f$getBuffer = false;
            this_$iv = $this$commonFlush;
            $i$f$getBuffer = false;
            $this$commonFlush.sink.write(this_$iv.bufferField, this_$iv.bufferField.size());
        }
        $this$commonFlush.sink.flush();
    }

    public static final void commonClose(@NotNull RealBufferedSink $this$commonClose) {
        Throwable thrown;
        block7: {
            int $i$f$commonClose = 0;
            Intrinsics.checkNotNullParameter($this$commonClose, "$this$commonClose");
            if ($this$commonClose.closed) {
                return;
            }
            thrown = null;
            try {
                RealBufferedSink this_$iv = $this$commonClose;
                boolean $i$f$getBuffer = false;
                if (this_$iv.bufferField.size() > 0L) {
                    this_$iv = $this$commonClose;
                    $i$f$getBuffer = false;
                    this_$iv = $this$commonClose;
                    $i$f$getBuffer = false;
                    $this$commonClose.sink.write(this_$iv.bufferField, this_$iv.bufferField.size());
                }
            } catch (Throwable e) {
                thrown = e;
            }
            try {
                $this$commonClose.sink.close();
            } catch (Throwable e) {
                if (thrown != null) break block7;
                thrown = e;
            }
        }
        $this$commonClose.closed = true;
        if (thrown != null) {
            throw thrown;
        }
    }

    @NotNull
    public static final Timeout commonTimeout(@NotNull RealBufferedSink $this$commonTimeout) {
        int $i$f$commonTimeout = 0;
        Intrinsics.checkNotNullParameter($this$commonTimeout, "$this$commonTimeout");
        return $this$commonTimeout.sink.timeout();
    }

    @NotNull
    public static final String commonToString(@NotNull RealBufferedSink $this$commonToString) {
        int $i$f$commonToString = 0;
        Intrinsics.checkNotNullParameter($this$commonToString, "$this$commonToString");
        return "buffer(" + $this$commonToString.sink + ')';
    }
}

