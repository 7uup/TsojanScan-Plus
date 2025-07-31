/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okio.internal;

import java.io.EOFException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;
import okio.-Util;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;
import okio.Okio;
import okio.Options;
import okio.PeekSource;
import okio.RealBufferedSource;
import okio.Sink;
import okio.Timeout;
import okio.internal.BufferKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=2, d1={"\u0000j\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\n\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0080\b\u001a\r\u0010\u0003\u001a\u00020\u0004*\u00020\u0002H\u0080\b\u001a%\u0010\u0005\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u0006H\u0080\b\u001a\u001d\u0010\u0005\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\u0006H\u0080\b\u001a\u001d\u0010\r\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u000e\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\u0006H\u0080\b\u001a\r\u0010\u000f\u001a\u00020\u0010*\u00020\u0002H\u0080\b\u001a-\u0010\u0011\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0014H\u0080\b\u001a%\u0010\u0016\u001a\u00020\u0014*\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0012\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0014H\u0080\b\u001a\u001d\u0010\u0016\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u00192\u0006\u0010\u0015\u001a\u00020\u0006H\u0080\b\u001a\u0015\u0010\u001a\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u001bH\u0080\b\u001a\r\u0010\u001c\u001a\u00020\b*\u00020\u0002H\u0080\b\u001a\r\u0010\u001d\u001a\u00020\u0018*\u00020\u0002H\u0080\b\u001a\u0015\u0010\u001d\u001a\u00020\u0018*\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u0006H\u0080\b\u001a\r\u0010\u001e\u001a\u00020\f*\u00020\u0002H\u0080\b\u001a\u0015\u0010\u001e\u001a\u00020\f*\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u0006H\u0080\b\u001a\r\u0010\u001f\u001a\u00020\u0006*\u00020\u0002H\u0080\b\u001a\u0015\u0010 \u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u0018H\u0080\b\u001a\u001d\u0010 \u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u00192\u0006\u0010\u0015\u001a\u00020\u0006H\u0080\b\u001a\r\u0010!\u001a\u00020\u0006*\u00020\u0002H\u0080\b\u001a\r\u0010\"\u001a\u00020\u0014*\u00020\u0002H\u0080\b\u001a\r\u0010#\u001a\u00020\u0014*\u00020\u0002H\u0080\b\u001a\r\u0010$\u001a\u00020\u0006*\u00020\u0002H\u0080\b\u001a\r\u0010%\u001a\u00020\u0006*\u00020\u0002H\u0080\b\u001a\r\u0010&\u001a\u00020'*\u00020\u0002H\u0080\b\u001a\r\u0010(\u001a\u00020'*\u00020\u0002H\u0080\b\u001a\r\u0010)\u001a\u00020**\u00020\u0002H\u0080\b\u001a\u0015\u0010)\u001a\u00020**\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u0006H\u0080\b\u001a\r\u0010+\u001a\u00020\u0014*\u00020\u0002H\u0080\b\u001a\u000f\u0010,\u001a\u0004\u0018\u00010**\u00020\u0002H\u0080\b\u001a\u0015\u0010-\u001a\u00020**\u00020\u00022\u0006\u0010.\u001a\u00020\u0006H\u0080\b\u001a\u0015\u0010/\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u0006H\u0080\b\u001a\u0015\u00100\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u0006H\u0080\b\u001a\u0015\u00101\u001a\u00020\u0014*\u00020\u00022\u0006\u00102\u001a\u000203H\u0080\b\u001a\u0015\u00104\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u0006H\u0080\b\u001a\r\u00105\u001a\u000206*\u00020\u0002H\u0080\b\u001a\r\u00107\u001a\u00020**\u00020\u0002H\u0080\b\u00a8\u00068"}, d2={"commonClose", "", "Lokio/RealBufferedSource;", "commonExhausted", "", "commonIndexOf", "", "b", "", "fromIndex", "toIndex", "bytes", "Lokio/ByteString;", "commonIndexOfElement", "targetBytes", "commonPeek", "Lokio/BufferedSource;", "commonRangeEquals", "offset", "bytesOffset", "", "byteCount", "commonRead", "sink", "", "Lokio/Buffer;", "commonReadAll", "Lokio/Sink;", "commonReadByte", "commonReadByteArray", "commonReadByteString", "commonReadDecimalLong", "commonReadFully", "commonReadHexadecimalUnsignedLong", "commonReadInt", "commonReadIntLe", "commonReadLong", "commonReadLongLe", "commonReadShort", "", "commonReadShortLe", "commonReadUtf8", "", "commonReadUtf8CodePoint", "commonReadUtf8Line", "commonReadUtf8LineStrict", "limit", "commonRequest", "commonRequire", "commonSelect", "options", "Lokio/Options;", "commonSkip", "commonTimeout", "Lokio/Timeout;", "commonToString", "okio"})
public final class RealBufferedSourceKt {
    public static final long commonRead(@NotNull RealBufferedSource $this$commonRead, @NotNull Buffer sink2, long byteCount) {
        boolean $i$f$getBuffer;
        int $i$f$commonRead = 0;
        Intrinsics.checkNotNullParameter($this$commonRead, "$this$commonRead");
        Intrinsics.checkNotNullParameter(sink2, "sink");
        boolean bl = byteCount >= 0L;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "byteCount < 0: " + byteCount;
            throw (Throwable)new IllegalArgumentException(string.toString());
        }
        bl = !$this$commonRead.closed;
        bl2 = false;
        bl3 = false;
        if (!bl) {
            boolean bl5 = false;
            String string = "closed";
            throw (Throwable)new IllegalStateException(string.toString());
        }
        RealBufferedSource this_$iv = $this$commonRead;
        boolean $i$f$getBuffer2 = false;
        if (this_$iv.bufferField.size() == 0L) {
            RealBufferedSource this_$iv2 = $this$commonRead;
            $i$f$getBuffer = false;
            long read = $this$commonRead.source.read(this_$iv2.bufferField, 8192);
            if (read == -1L) {
                return -1L;
            }
        }
        RealBufferedSource this_$iv22 = $this$commonRead;
        $i$f$getBuffer = false;
        long this_$iv22 = this_$iv22.bufferField.size();
        boolean bl6 = false;
        long toRead = Math.min(byteCount, this_$iv22);
        RealBufferedSource this_$iv3 = $this$commonRead;
        $i$f$getBuffer = false;
        return this_$iv3.bufferField.read(sink2, toRead);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static final boolean commonExhausted(@NotNull RealBufferedSource $this$commonExhausted) {
        int $i$f$commonExhausted = 0;
        Intrinsics.checkNotNullParameter($this$commonExhausted, "$this$commonExhausted");
        boolean bl = !$this$commonExhausted.closed;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "closed";
            throw (Throwable)new IllegalStateException(string.toString());
        }
        RealBufferedSource this_$iv = $this$commonExhausted;
        boolean $i$f$getBuffer = false;
        if (!this_$iv.bufferField.exhausted()) return false;
        this_$iv = $this$commonExhausted;
        $i$f$getBuffer = false;
        if ($this$commonExhausted.source.read(this_$iv.bufferField, 8192) != -1L) return false;
        return true;
    }

    public static final void commonRequire(@NotNull RealBufferedSource $this$commonRequire, long byteCount) {
        int $i$f$commonRequire = 0;
        Intrinsics.checkNotNullParameter($this$commonRequire, "$this$commonRequire");
        if (!$this$commonRequire.request(byteCount)) {
            throw (Throwable)new EOFException();
        }
    }

    public static final boolean commonRequest(@NotNull RealBufferedSource $this$commonRequest, long byteCount) {
        block3: {
            RealBufferedSource this_$iv;
            int $i$f$commonRequest = 0;
            Intrinsics.checkNotNullParameter($this$commonRequest, "$this$commonRequest");
            boolean bl = byteCount >= 0L;
            boolean bl2 = false;
            boolean bl3 = false;
            if (!bl) {
                boolean bl4 = false;
                String string = "byteCount < 0: " + byteCount;
                throw (Throwable)new IllegalArgumentException(string.toString());
            }
            bl = !$this$commonRequest.closed;
            bl2 = false;
            bl3 = false;
            if (!bl) {
                boolean bl5 = false;
                String string = "closed";
                throw (Throwable)new IllegalStateException(string.toString());
            }
            do {
                this_$iv = $this$commonRequest;
                boolean $i$f$getBuffer = false;
                if (this_$iv.bufferField.size() >= byteCount) break block3;
                this_$iv = $this$commonRequest;
                $i$f$getBuffer = false;
            } while ($this$commonRequest.source.read(this_$iv.bufferField, 8192) != -1L);
            return false;
        }
        return true;
    }

    public static final byte commonReadByte(@NotNull RealBufferedSource $this$commonReadByte) {
        int $i$f$commonReadByte = 0;
        Intrinsics.checkNotNullParameter($this$commonReadByte, "$this$commonReadByte");
        $this$commonReadByte.require(1L);
        RealBufferedSource this_$iv = $this$commonReadByte;
        boolean $i$f$getBuffer = false;
        return this_$iv.bufferField.readByte();
    }

    @NotNull
    public static final ByteString commonReadByteString(@NotNull RealBufferedSource $this$commonReadByteString) {
        int $i$f$commonReadByteString = 0;
        Intrinsics.checkNotNullParameter($this$commonReadByteString, "$this$commonReadByteString");
        RealBufferedSource this_$iv = $this$commonReadByteString;
        boolean $i$f$getBuffer = false;
        this_$iv.bufferField.writeAll($this$commonReadByteString.source);
        this_$iv = $this$commonReadByteString;
        $i$f$getBuffer = false;
        return this_$iv.bufferField.readByteString();
    }

    @NotNull
    public static final ByteString commonReadByteString(@NotNull RealBufferedSource $this$commonReadByteString, long byteCount) {
        int $i$f$commonReadByteString = 0;
        Intrinsics.checkNotNullParameter($this$commonReadByteString, "$this$commonReadByteString");
        $this$commonReadByteString.require(byteCount);
        RealBufferedSource this_$iv = $this$commonReadByteString;
        boolean $i$f$getBuffer = false;
        return this_$iv.bufferField.readByteString(byteCount);
    }

    public static final int commonSelect(@NotNull RealBufferedSource $this$commonSelect, @NotNull Options options) {
        int index;
        int $i$f$commonSelect = 0;
        Intrinsics.checkNotNullParameter($this$commonSelect, "$this$commonSelect");
        Intrinsics.checkNotNullParameter(options, "options");
        boolean bl = !$this$commonSelect.closed;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "closed";
            throw (Throwable)new IllegalStateException(string.toString());
        }
        block4: while (true) {
            RealBufferedSource this_$iv = $this$commonSelect;
            boolean $i$f$getBuffer = false;
            index = BufferKt.selectPrefix(this_$iv.bufferField, options, true);
            switch (index) {
                case -1: {
                    return -1;
                }
                case -2: {
                    this_$iv = $this$commonSelect;
                    $i$f$getBuffer = false;
                    if ($this$commonSelect.source.read(this_$iv.bufferField, 8192) != -1L) continue block4;
                    return -1;
                }
            }
            break;
        }
        int selectedSize = options.getByteStrings$okio()[index].size();
        RealBufferedSource this_$iv = $this$commonSelect;
        boolean $i$f$getBuffer = false;
        this_$iv.bufferField.skip(selectedSize);
        return index;
    }

    @NotNull
    public static final byte[] commonReadByteArray(@NotNull RealBufferedSource $this$commonReadByteArray) {
        int $i$f$commonReadByteArray = 0;
        Intrinsics.checkNotNullParameter($this$commonReadByteArray, "$this$commonReadByteArray");
        RealBufferedSource this_$iv = $this$commonReadByteArray;
        boolean $i$f$getBuffer = false;
        this_$iv.bufferField.writeAll($this$commonReadByteArray.source);
        this_$iv = $this$commonReadByteArray;
        $i$f$getBuffer = false;
        return this_$iv.bufferField.readByteArray();
    }

    @NotNull
    public static final byte[] commonReadByteArray(@NotNull RealBufferedSource $this$commonReadByteArray, long byteCount) {
        int $i$f$commonReadByteArray = 0;
        Intrinsics.checkNotNullParameter($this$commonReadByteArray, "$this$commonReadByteArray");
        $this$commonReadByteArray.require(byteCount);
        RealBufferedSource this_$iv = $this$commonReadByteArray;
        boolean $i$f$getBuffer = false;
        return this_$iv.bufferField.readByteArray(byteCount);
    }

    public static final void commonReadFully(@NotNull RealBufferedSource $this$commonReadFully, @NotNull byte[] sink2) {
        int $i$f$commonReadFully = 0;
        Intrinsics.checkNotNullParameter($this$commonReadFully, "$this$commonReadFully");
        Intrinsics.checkNotNullParameter(sink2, "sink");
        try {
            $this$commonReadFully.require(sink2.length);
        } catch (EOFException e) {
            int offset = 0;
            while (true) {
                RealBufferedSource this_$iv = $this$commonReadFully;
                boolean $i$f$getBuffer = false;
                if (this_$iv.bufferField.size() <= 0L) break;
                RealBufferedSource this_$iv2 = $this$commonReadFully;
                boolean $i$f$getBuffer2 = false;
                this_$iv2 = $this$commonReadFully;
                $i$f$getBuffer2 = false;
                int read = this_$iv2.bufferField.read(sink2, offset, (int)this_$iv2.bufferField.size());
                if (read == -1) {
                    throw (Throwable)((Object)new AssertionError());
                }
                offset += read;
            }
            throw (Throwable)e;
        }
        RealBufferedSource this_$iv = $this$commonReadFully;
        boolean $i$f$getBuffer = false;
        this_$iv.bufferField.readFully(sink2);
    }

    public static final int commonRead(@NotNull RealBufferedSource $this$commonRead, @NotNull byte[] sink2, int offset, int byteCount) {
        int $i$f$commonRead = 0;
        Intrinsics.checkNotNullParameter($this$commonRead, "$this$commonRead");
        Intrinsics.checkNotNullParameter(sink2, "sink");
        -Util.checkOffsetAndCount(sink2.length, offset, byteCount);
        RealBufferedSource this_$iv = $this$commonRead;
        boolean $i$f$getBuffer = false;
        if (this_$iv.bufferField.size() == 0L) {
            RealBufferedSource this_$iv2 = $this$commonRead;
            boolean $i$f$getBuffer2 = false;
            long read = $this$commonRead.source.read(this_$iv2.bufferField, 8192);
            if (read == -1L) {
                return -1;
            }
        }
        RealBufferedSource this_$iv3 = $this$commonRead;
        boolean $i$f$getBuffer3 = false;
        long b$iv = this_$iv3.bufferField.size();
        boolean $i$f$minOf = false;
        long l = byteCount;
        boolean bl = false;
        int toRead = (int)Math.min(l, b$iv);
        this_$iv = $this$commonRead;
        $i$f$getBuffer3 = false;
        return this_$iv.bufferField.read(sink2, offset, toRead);
    }

    public static final void commonReadFully(@NotNull RealBufferedSource $this$commonReadFully, @NotNull Buffer sink2, long byteCount) {
        int $i$f$commonReadFully = 0;
        Intrinsics.checkNotNullParameter($this$commonReadFully, "$this$commonReadFully");
        Intrinsics.checkNotNullParameter(sink2, "sink");
        try {
            $this$commonReadFully.require(byteCount);
        } catch (EOFException e) {
            RealBufferedSource this_$iv = $this$commonReadFully;
            boolean $i$f$getBuffer = false;
            sink2.writeAll(this_$iv.bufferField);
            throw (Throwable)e;
        }
        RealBufferedSource this_$iv = $this$commonReadFully;
        boolean $i$f$getBuffer = false;
        this_$iv.bufferField.readFully(sink2, byteCount);
    }

    public static final long commonReadAll(@NotNull RealBufferedSource $this$commonReadAll, @NotNull Sink sink2) {
        boolean $i$f$getBuffer;
        RealBufferedSource this_$iv;
        int $i$f$commonReadAll = 0;
        Intrinsics.checkNotNullParameter($this$commonReadAll, "$this$commonReadAll");
        Intrinsics.checkNotNullParameter(sink2, "sink");
        long totalBytesWritten = 0L;
        while (true) {
            this_$iv = $this$commonReadAll;
            $i$f$getBuffer = false;
            if ($this$commonReadAll.source.read(this_$iv.bufferField, 8192) == -1L) break;
            RealBufferedSource this_$iv2 = $this$commonReadAll;
            boolean $i$f$getBuffer2 = false;
            long emitByteCount = this_$iv2.bufferField.completeSegmentByteCount();
            if (emitByteCount <= 0L) continue;
            totalBytesWritten += emitByteCount;
            this_$iv2 = $this$commonReadAll;
            $i$f$getBuffer2 = false;
            sink2.write(this_$iv2.bufferField, emitByteCount);
        }
        this_$iv = $this$commonReadAll;
        $i$f$getBuffer = false;
        if (this_$iv.bufferField.size() > 0L) {
            this_$iv = $this$commonReadAll;
            $i$f$getBuffer = false;
            totalBytesWritten += this_$iv.bufferField.size();
            this_$iv = $this$commonReadAll;
            $i$f$getBuffer = false;
            this_$iv = $this$commonReadAll;
            $i$f$getBuffer = false;
            sink2.write(this_$iv.bufferField, this_$iv.bufferField.size());
        }
        return totalBytesWritten;
    }

    @NotNull
    public static final String commonReadUtf8(@NotNull RealBufferedSource $this$commonReadUtf8) {
        int $i$f$commonReadUtf8 = 0;
        Intrinsics.checkNotNullParameter($this$commonReadUtf8, "$this$commonReadUtf8");
        RealBufferedSource this_$iv = $this$commonReadUtf8;
        boolean $i$f$getBuffer = false;
        this_$iv.bufferField.writeAll($this$commonReadUtf8.source);
        this_$iv = $this$commonReadUtf8;
        $i$f$getBuffer = false;
        return this_$iv.bufferField.readUtf8();
    }

    @NotNull
    public static final String commonReadUtf8(@NotNull RealBufferedSource $this$commonReadUtf8, long byteCount) {
        int $i$f$commonReadUtf8 = 0;
        Intrinsics.checkNotNullParameter($this$commonReadUtf8, "$this$commonReadUtf8");
        $this$commonReadUtf8.require(byteCount);
        RealBufferedSource this_$iv = $this$commonReadUtf8;
        boolean $i$f$getBuffer = false;
        return this_$iv.bufferField.readUtf8(byteCount);
    }

    @Nullable
    public static final String commonReadUtf8Line(@NotNull RealBufferedSource $this$commonReadUtf8Line) {
        String string;
        int $i$f$commonReadUtf8Line = 0;
        Intrinsics.checkNotNullParameter($this$commonReadUtf8Line, "$this$commonReadUtf8Line");
        long newline = $this$commonReadUtf8Line.indexOf((byte)10);
        if (newline == -1L) {
            RealBufferedSource this_$iv = $this$commonReadUtf8Line;
            boolean $i$f$getBuffer = false;
            if (this_$iv.bufferField.size() != 0L) {
                this_$iv = $this$commonReadUtf8Line;
                $i$f$getBuffer = false;
                string = $this$commonReadUtf8Line.readUtf8(this_$iv.bufferField.size());
            } else {
                string = null;
            }
        } else {
            RealBufferedSource this_$iv = $this$commonReadUtf8Line;
            boolean $i$f$getBuffer = false;
            string = BufferKt.readUtf8Line(this_$iv.bufferField, newline);
        }
        return string;
    }

    /*
     * WARNING - void declaration
     */
    @NotNull
    public static final String commonReadUtf8LineStrict(@NotNull RealBufferedSource $this$commonReadUtf8LineStrict, long limit) {
        void a$iv;
        int $i$f$commonReadUtf8LineStrict = 0;
        Intrinsics.checkNotNullParameter($this$commonReadUtf8LineStrict, "$this$commonReadUtf8LineStrict");
        boolean bl = limit >= 0L;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "limit < 0: " + limit;
            throw (Throwable)new IllegalArgumentException(string.toString());
        }
        long scanLength = limit == Long.MAX_VALUE ? Long.MAX_VALUE : limit + 1L;
        long newline = $this$commonReadUtf8LineStrict.indexOf((byte)10, 0L, scanLength);
        if (newline != -1L) {
            RealBufferedSource this_$iv = $this$commonReadUtf8LineStrict;
            boolean $i$f$getBuffer = false;
            return BufferKt.readUtf8Line(this_$iv.bufferField, newline);
        }
        if (scanLength < Long.MAX_VALUE && $this$commonReadUtf8LineStrict.request(scanLength)) {
            RealBufferedSource this_$iv = $this$commonReadUtf8LineStrict;
            boolean $i$f$getBuffer = false;
            if (this_$iv.bufferField.getByte(scanLength - 1L) == (byte)13 && $this$commonReadUtf8LineStrict.request(scanLength + 1L)) {
                this_$iv = $this$commonReadUtf8LineStrict;
                $i$f$getBuffer = false;
                if (this_$iv.bufferField.getByte(scanLength) == (byte)10) {
                    this_$iv = $this$commonReadUtf8LineStrict;
                    $i$f$getBuffer = false;
                    return BufferKt.readUtf8Line(this_$iv.bufferField, scanLength);
                }
            }
        }
        Buffer data = new Buffer();
        RealBufferedSource this_$iv22 = $this$commonReadUtf8LineStrict;
        boolean $i$f$getBuffer = false;
        int this_$iv22 = 32;
        RealBufferedSource this_$iv = $this$commonReadUtf8LineStrict;
        boolean $i$f$getBuffer2 = false;
        long b$iv = this_$iv.bufferField.size();
        boolean $i$f$minOf = false;
        long l = (long)a$iv;
        boolean bl5 = false;
        this_$iv22.bufferField.copyTo(data, 0L, Math.min(l, b$iv));
        RealBufferedSource this_$iv3 = $this$commonReadUtf8LineStrict;
        $i$f$getBuffer = false;
        long l2 = this_$iv3.bufferField.size();
        boolean bl6 = false;
        throw (Throwable)new EOFException("\\n not found: limit=" + Math.min(l2, limit) + " content=" + data.readByteString().hex() + "\u2026");
    }

    public static final int commonReadUtf8CodePoint(@NotNull RealBufferedSource $this$commonReadUtf8CodePoint) {
        int $i$f$commonReadUtf8CodePoint = 0;
        Intrinsics.checkNotNullParameter($this$commonReadUtf8CodePoint, "$this$commonReadUtf8CodePoint");
        $this$commonReadUtf8CodePoint.require(1L);
        RealBufferedSource this_$iv = $this$commonReadUtf8CodePoint;
        boolean $i$f$getBuffer = false;
        byte b0 = this_$iv.bufferField.getByte(0L);
        if ((b0 & 0xE0) == 192) {
            $this$commonReadUtf8CodePoint.require(2L);
        } else if ((b0 & 0xF0) == 224) {
            $this$commonReadUtf8CodePoint.require(3L);
        } else if ((b0 & 0xF8) == 240) {
            $this$commonReadUtf8CodePoint.require(4L);
        }
        this_$iv = $this$commonReadUtf8CodePoint;
        $i$f$getBuffer = false;
        return this_$iv.bufferField.readUtf8CodePoint();
    }

    public static final short commonReadShort(@NotNull RealBufferedSource $this$commonReadShort) {
        int $i$f$commonReadShort = 0;
        Intrinsics.checkNotNullParameter($this$commonReadShort, "$this$commonReadShort");
        $this$commonReadShort.require(2L);
        RealBufferedSource this_$iv = $this$commonReadShort;
        boolean $i$f$getBuffer = false;
        return this_$iv.bufferField.readShort();
    }

    public static final short commonReadShortLe(@NotNull RealBufferedSource $this$commonReadShortLe) {
        int $i$f$commonReadShortLe = 0;
        Intrinsics.checkNotNullParameter($this$commonReadShortLe, "$this$commonReadShortLe");
        $this$commonReadShortLe.require(2L);
        RealBufferedSource this_$iv = $this$commonReadShortLe;
        boolean $i$f$getBuffer = false;
        return this_$iv.bufferField.readShortLe();
    }

    public static final int commonReadInt(@NotNull RealBufferedSource $this$commonReadInt) {
        int $i$f$commonReadInt = 0;
        Intrinsics.checkNotNullParameter($this$commonReadInt, "$this$commonReadInt");
        $this$commonReadInt.require(4L);
        RealBufferedSource this_$iv = $this$commonReadInt;
        boolean $i$f$getBuffer = false;
        return this_$iv.bufferField.readInt();
    }

    public static final int commonReadIntLe(@NotNull RealBufferedSource $this$commonReadIntLe) {
        int $i$f$commonReadIntLe = 0;
        Intrinsics.checkNotNullParameter($this$commonReadIntLe, "$this$commonReadIntLe");
        $this$commonReadIntLe.require(4L);
        RealBufferedSource this_$iv = $this$commonReadIntLe;
        boolean $i$f$getBuffer = false;
        return this_$iv.bufferField.readIntLe();
    }

    public static final long commonReadLong(@NotNull RealBufferedSource $this$commonReadLong) {
        int $i$f$commonReadLong = 0;
        Intrinsics.checkNotNullParameter($this$commonReadLong, "$this$commonReadLong");
        $this$commonReadLong.require(8L);
        RealBufferedSource this_$iv = $this$commonReadLong;
        boolean $i$f$getBuffer = false;
        return this_$iv.bufferField.readLong();
    }

    public static final long commonReadLongLe(@NotNull RealBufferedSource $this$commonReadLongLe) {
        int $i$f$commonReadLongLe = 0;
        Intrinsics.checkNotNullParameter($this$commonReadLongLe, "$this$commonReadLongLe");
        $this$commonReadLongLe.require(8L);
        RealBufferedSource this_$iv = $this$commonReadLongLe;
        boolean $i$f$getBuffer = false;
        return this_$iv.bufferField.readLongLe();
    }

    public static final long commonReadDecimalLong(@NotNull RealBufferedSource $this$commonReadDecimalLong) {
        int $i$f$commonReadDecimalLong = 0;
        Intrinsics.checkNotNullParameter($this$commonReadDecimalLong, "$this$commonReadDecimalLong");
        $this$commonReadDecimalLong.require(1L);
        long pos = 0L;
        while ($this$commonReadDecimalLong.request(pos + 1L)) {
            RealBufferedSource this_$iv2 = $this$commonReadDecimalLong;
            boolean $i$f$getBuffer = false;
            byte b = this_$iv2.bufferField.getByte(pos);
            if (!(b >= (byte)48 && b <= (byte)57 || pos == 0L && b == (byte)45)) {
                if (pos != 0L) break;
                StringBuilder stringBuilder = new StringBuilder().append("Expected leading [0-9] or '-' character but was 0x");
                byte this_$iv2 = b;
                int n = 16;
                boolean bl = false;
                byte by = this_$iv2;
                int n2 = CharsKt.checkRadix(n);
                boolean bl2 = false;
                String string = Integer.toString(by, CharsKt.checkRadix(n2));
                Intrinsics.checkNotNullExpressionValue(string, "java.lang.Integer.toStri\u2026(this, checkRadix(radix))");
                throw (Throwable)new NumberFormatException(stringBuilder.append(string).toString());
            }
            long this_$iv2 = pos;
            pos = this_$iv2 + 1L;
        }
        RealBufferedSource this_$iv = $this$commonReadDecimalLong;
        boolean $i$f$getBuffer = false;
        return this_$iv.bufferField.readDecimalLong();
    }

    public static final long commonReadHexadecimalUnsignedLong(@NotNull RealBufferedSource $this$commonReadHexadecimalUnsignedLong) {
        int $i$f$commonReadHexadecimalUnsignedLong = 0;
        Intrinsics.checkNotNullParameter($this$commonReadHexadecimalUnsignedLong, "$this$commonReadHexadecimalUnsignedLong");
        $this$commonReadHexadecimalUnsignedLong.require(1L);
        int pos = 0;
        while ($this$commonReadHexadecimalUnsignedLong.request(pos + 1)) {
            RealBufferedSource this_$iv2 = $this$commonReadHexadecimalUnsignedLong;
            boolean $i$f$getBuffer = false;
            byte b = this_$iv2.bufferField.getByte(pos);
            if (!(b >= (byte)48 && b <= (byte)57 || b >= (byte)97 && b <= (byte)102 || b >= (byte)65 && b <= (byte)70)) {
                if (pos != 0) break;
                StringBuilder stringBuilder = new StringBuilder().append("Expected leading [0-9a-fA-F] character but was 0x");
                byte this_$iv2 = b;
                int n = 16;
                boolean bl = false;
                byte by = this_$iv2;
                int n2 = CharsKt.checkRadix(n);
                boolean bl2 = false;
                String string = Integer.toString(by, CharsKt.checkRadix(n2));
                Intrinsics.checkNotNullExpressionValue(string, "java.lang.Integer.toStri\u2026(this, checkRadix(radix))");
                throw (Throwable)new NumberFormatException(stringBuilder.append(string).toString());
            }
            ++pos;
        }
        RealBufferedSource this_$iv = $this$commonReadHexadecimalUnsignedLong;
        boolean $i$f$getBuffer = false;
        return this_$iv.bufferField.readHexadecimalUnsignedLong();
    }

    public static final void commonSkip(@NotNull RealBufferedSource $this$commonSkip, long byteCount) {
        long toSkip;
        int $i$f$commonSkip = 0;
        Intrinsics.checkNotNullParameter($this$commonSkip, "$this$commonSkip");
        boolean bl = !$this$commonSkip.closed;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "closed";
            throw (Throwable)new IllegalStateException(string.toString());
        }
        for (long byteCount2 = byteCount; byteCount2 > 0L; byteCount2 -= toSkip) {
            RealBufferedSource this_$iv = $this$commonSkip;
            boolean $i$f$getBuffer = false;
            if (this_$iv.bufferField.size() == 0L) {
                this_$iv = $this$commonSkip;
                $i$f$getBuffer = false;
                if ($this$commonSkip.source.read(this_$iv.bufferField, 8192) == -1L) {
                    throw (Throwable)new EOFException();
                }
            }
            RealBufferedSource this_$iv22 = $this$commonSkip;
            boolean $i$f$getBuffer2 = false;
            long this_$iv22 = this_$iv22.bufferField.size();
            boolean bl5 = false;
            toSkip = Math.min(byteCount2, this_$iv22);
            RealBufferedSource this_$iv3 = $this$commonSkip;
            $i$f$getBuffer2 = false;
            this_$iv3.bufferField.skip(toSkip);
        }
    }

    public static final long commonIndexOf(@NotNull RealBufferedSource $this$commonIndexOf, byte b, long fromIndex, long toIndex) {
        int $i$f$commonIndexOf = 0;
        Intrinsics.checkNotNullParameter($this$commonIndexOf, "$this$commonIndexOf");
        long fromIndex2 = fromIndex;
        boolean bl = !$this$commonIndexOf.closed;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "closed";
            throw (Throwable)new IllegalStateException(string.toString());
        }
        long l = fromIndex2;
        boolean bl5 = 0L <= l && toIndex >= l;
        bl2 = false;
        bl3 = false;
        if (!bl5) {
            boolean bl6 = false;
            String string = "fromIndex=" + fromIndex2 + " toIndex=" + toIndex;
            throw (Throwable)new IllegalArgumentException(string.toString());
        }
        while (fromIndex2 < toIndex) {
            long lastBufferSize;
            block8: {
                block7: {
                    RealBufferedSource this_$iv = $this$commonIndexOf;
                    boolean $i$f$getBuffer = false;
                    long result = this_$iv.bufferField.indexOf(b, fromIndex2, toIndex);
                    if (result != -1L) {
                        return result;
                    }
                    RealBufferedSource this_$iv2 = $this$commonIndexOf;
                    boolean $i$f$getBuffer2 = false;
                    lastBufferSize = this_$iv2.bufferField.size();
                    if (lastBufferSize >= toIndex) break block7;
                    this_$iv2 = $this$commonIndexOf;
                    $i$f$getBuffer2 = false;
                    if ($this$commonIndexOf.source.read(this_$iv2.bufferField, 8192) != -1L) break block8;
                }
                return -1L;
            }
            long l2 = fromIndex2;
            boolean bl7 = false;
            fromIndex2 = Math.max(l2, lastBufferSize);
        }
        return -1L;
    }

    public static final long commonIndexOf(@NotNull RealBufferedSource $this$commonIndexOf, @NotNull ByteString bytes, long fromIndex) {
        int $i$f$commonIndexOf = 0;
        Intrinsics.checkNotNullParameter($this$commonIndexOf, "$this$commonIndexOf");
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        long fromIndex2 = fromIndex;
        boolean bl = !$this$commonIndexOf.closed;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "closed";
            throw (Throwable)new IllegalStateException(string.toString());
        }
        while (true) {
            RealBufferedSource this_$iv = $this$commonIndexOf;
            boolean $i$f$getBuffer = false;
            long result = this_$iv.bufferField.indexOf(bytes, fromIndex2);
            if (result != -1L) {
                return result;
            }
            RealBufferedSource this_$iv2 = $this$commonIndexOf;
            boolean $i$f$getBuffer2 = false;
            long lastBufferSize = this_$iv2.bufferField.size();
            this_$iv2 = $this$commonIndexOf;
            $i$f$getBuffer2 = false;
            if ($this$commonIndexOf.source.read(this_$iv2.bufferField, 8192) == -1L) {
                return -1L;
            }
            long l = lastBufferSize - (long)bytes.size() + 1L;
            boolean bl5 = false;
            fromIndex2 = Math.max(fromIndex2, l);
        }
    }

    public static final long commonIndexOfElement(@NotNull RealBufferedSource $this$commonIndexOfElement, @NotNull ByteString targetBytes, long fromIndex) {
        int $i$f$commonIndexOfElement = 0;
        Intrinsics.checkNotNullParameter($this$commonIndexOfElement, "$this$commonIndexOfElement");
        Intrinsics.checkNotNullParameter(targetBytes, "targetBytes");
        long fromIndex2 = fromIndex;
        boolean bl = !$this$commonIndexOfElement.closed;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "closed";
            throw (Throwable)new IllegalStateException(string.toString());
        }
        while (true) {
            RealBufferedSource this_$iv = $this$commonIndexOfElement;
            boolean $i$f$getBuffer = false;
            long result = this_$iv.bufferField.indexOfElement(targetBytes, fromIndex2);
            if (result != -1L) {
                return result;
            }
            RealBufferedSource this_$iv2 = $this$commonIndexOfElement;
            boolean $i$f$getBuffer2 = false;
            long lastBufferSize = this_$iv2.bufferField.size();
            this_$iv2 = $this$commonIndexOfElement;
            $i$f$getBuffer2 = false;
            if ($this$commonIndexOfElement.source.read(this_$iv2.bufferField, 8192) == -1L) {
                return -1L;
            }
            boolean bl5 = false;
            fromIndex2 = Math.max(fromIndex2, lastBufferSize);
        }
    }

    /*
     * WARNING - void declaration
     */
    public static final boolean commonRangeEquals(@NotNull RealBufferedSource $this$commonRangeEquals, long offset, @NotNull ByteString bytes, int bytesOffset, int byteCount) {
        int $i$f$commonRangeEquals = 0;
        Intrinsics.checkNotNullParameter($this$commonRangeEquals, "$this$commonRangeEquals");
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        int n = !$this$commonRangeEquals.closed ? 1 : 0;
        int n2 = 0;
        boolean bl = false;
        if (n == 0) {
            boolean bl2 = false;
            String string = "closed";
            throw (Throwable)new IllegalStateException(string.toString());
        }
        if (offset < 0L || bytesOffset < 0 || byteCount < 0 || bytes.size() - bytesOffset < byteCount) {
            return false;
        }
        n = 0;
        n2 = byteCount;
        while (n < n2) {
            void i;
            long bufferOffset = offset + (long)i;
            if (!$this$commonRangeEquals.request(bufferOffset + 1L)) {
                return false;
            }
            RealBufferedSource this_$iv = $this$commonRangeEquals;
            boolean $i$f$getBuffer = false;
            if (this_$iv.bufferField.getByte(bufferOffset) != bytes.getByte(bytesOffset + i)) {
                return false;
            }
            ++i;
        }
        return true;
    }

    @NotNull
    public static final BufferedSource commonPeek(@NotNull RealBufferedSource $this$commonPeek) {
        int $i$f$commonPeek = 0;
        Intrinsics.checkNotNullParameter($this$commonPeek, "$this$commonPeek");
        return Okio.buffer(new PeekSource($this$commonPeek));
    }

    public static final void commonClose(@NotNull RealBufferedSource $this$commonClose) {
        int $i$f$commonClose = 0;
        Intrinsics.checkNotNullParameter($this$commonClose, "$this$commonClose");
        if ($this$commonClose.closed) {
            return;
        }
        $this$commonClose.closed = true;
        $this$commonClose.source.close();
        RealBufferedSource this_$iv = $this$commonClose;
        boolean $i$f$getBuffer = false;
        this_$iv.bufferField.clear();
    }

    @NotNull
    public static final Timeout commonTimeout(@NotNull RealBufferedSource $this$commonTimeout) {
        int $i$f$commonTimeout = 0;
        Intrinsics.checkNotNullParameter($this$commonTimeout, "$this$commonTimeout");
        return $this$commonTimeout.source.timeout();
    }

    @NotNull
    public static final String commonToString(@NotNull RealBufferedSource $this$commonToString) {
        int $i$f$commonToString = 0;
        Intrinsics.checkNotNullParameter($this$commonToString, "$this$commonToString");
        return "buffer(" + $this$commonToString.source + ')';
    }
}

