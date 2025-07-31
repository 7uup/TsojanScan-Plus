/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okio;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;
import okio.-Util;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;
import okio.Okio;
import okio.Options;
import okio.PeekSource;
import okio.Sink;
import okio.Source;
import okio.Timeout;
import okio.internal.BufferKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\n\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\rH\u0016J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0012H\u0016J \u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u0012H\u0016J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0015\u001a\u00020\u0012H\u0016J\u0010\u0010\u0019\u001a\u00020\u00122\u0006\u0010\u001a\u001a\u00020\u0018H\u0016J\u0018\u0010\u0019\u001a\u00020\u00122\u0006\u0010\u001a\u001a\u00020\u00182\u0006\u0010\u0015\u001a\u00020\u0012H\u0016J\b\u0010\u001b\u001a\u00020\u001cH\u0016J\b\u0010\u001d\u001a\u00020\rH\u0016J\b\u0010\u001e\u001a\u00020\u0001H\u0016J\u0018\u0010\u001f\u001a\u00020\r2\u0006\u0010 \u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J(\u0010\u001f\u001a\u00020\r2\u0006\u0010 \u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\"H\u0016J\u0010\u0010$\u001a\u00020\"2\u0006\u0010%\u001a\u00020&H\u0016J\u0010\u0010$\u001a\u00020\"2\u0006\u0010%\u001a\u00020'H\u0016J \u0010$\u001a\u00020\"2\u0006\u0010%\u001a\u00020'2\u0006\u0010 \u001a\u00020\"2\u0006\u0010#\u001a\u00020\"H\u0016J\u0018\u0010$\u001a\u00020\u00122\u0006\u0010%\u001a\u00020\u00062\u0006\u0010#\u001a\u00020\u0012H\u0016J\u0010\u0010(\u001a\u00020\u00122\u0006\u0010%\u001a\u00020)H\u0016J\b\u0010*\u001a\u00020\u0014H\u0016J\b\u0010+\u001a\u00020'H\u0016J\u0010\u0010+\u001a\u00020'2\u0006\u0010#\u001a\u00020\u0012H\u0016J\b\u0010,\u001a\u00020\u0018H\u0016J\u0010\u0010,\u001a\u00020\u00182\u0006\u0010#\u001a\u00020\u0012H\u0016J\b\u0010-\u001a\u00020\u0012H\u0016J\u0010\u0010.\u001a\u00020\u000f2\u0006\u0010%\u001a\u00020'H\u0016J\u0018\u0010.\u001a\u00020\u000f2\u0006\u0010%\u001a\u00020\u00062\u0006\u0010#\u001a\u00020\u0012H\u0016J\b\u0010/\u001a\u00020\u0012H\u0016J\b\u00100\u001a\u00020\"H\u0016J\b\u00101\u001a\u00020\"H\u0016J\b\u00102\u001a\u00020\u0012H\u0016J\b\u00103\u001a\u00020\u0012H\u0016J\b\u00104\u001a\u000205H\u0016J\b\u00106\u001a\u000205H\u0016J\u0010\u00107\u001a\u0002082\u0006\u00109\u001a\u00020:H\u0016J\u0018\u00107\u001a\u0002082\u0006\u0010#\u001a\u00020\u00122\u0006\u00109\u001a\u00020:H\u0016J\b\u0010;\u001a\u000208H\u0016J\u0010\u0010;\u001a\u0002082\u0006\u0010#\u001a\u00020\u0012H\u0016J\b\u0010<\u001a\u00020\"H\u0016J\n\u0010=\u001a\u0004\u0018\u000108H\u0016J\b\u0010>\u001a\u000208H\u0016J\u0010\u0010>\u001a\u0002082\u0006\u0010?\u001a\u00020\u0012H\u0016J\u0010\u0010@\u001a\u00020\r2\u0006\u0010#\u001a\u00020\u0012H\u0016J\u0010\u0010A\u001a\u00020\u000f2\u0006\u0010#\u001a\u00020\u0012H\u0016J\u0010\u0010B\u001a\u00020\"2\u0006\u0010C\u001a\u00020DH\u0016J\u0010\u0010E\u001a\u00020\u000f2\u0006\u0010#\u001a\u00020\u0012H\u0016J\b\u0010F\u001a\u00020GH\u0016J\b\u0010H\u001a\u000208H\u0016R\u001b\u0010\u0005\u001a\u00020\u00068\u00d6\u0002X\u0096\u0004\u00a2\u0006\f\u0012\u0004\b\u0007\u0010\b\u001a\u0004\b\t\u0010\nR\u0010\u0010\u000b\u001a\u00020\u00068\u0006X\u0087\u0004\u00a2\u0006\u0002\n\u0000R\u0012\u0010\f\u001a\u00020\r8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006I"}, d2={"Lokio/RealBufferedSource;", "Lokio/BufferedSource;", "source", "Lokio/Source;", "(Lokio/Source;)V", "buffer", "Lokio/Buffer;", "getBuffer$annotations", "()V", "getBuffer", "()Lokio/Buffer;", "bufferField", "closed", "", "close", "", "exhausted", "indexOf", "", "b", "", "fromIndex", "toIndex", "bytes", "Lokio/ByteString;", "indexOfElement", "targetBytes", "inputStream", "Ljava/io/InputStream;", "isOpen", "peek", "rangeEquals", "offset", "bytesOffset", "", "byteCount", "read", "sink", "Ljava/nio/ByteBuffer;", "", "readAll", "Lokio/Sink;", "readByte", "readByteArray", "readByteString", "readDecimalLong", "readFully", "readHexadecimalUnsignedLong", "readInt", "readIntLe", "readLong", "readLongLe", "readShort", "", "readShortLe", "readString", "", "charset", "Ljava/nio/charset/Charset;", "readUtf8", "readUtf8CodePoint", "readUtf8Line", "readUtf8LineStrict", "limit", "request", "require", "select", "options", "Lokio/Options;", "skip", "timeout", "Lokio/Timeout;", "toString", "okio"})
public final class RealBufferedSource
implements BufferedSource {
    @JvmField
    @NotNull
    public final Buffer bufferField;
    @JvmField
    public boolean closed;
    @JvmField
    @NotNull
    public final Source source;

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

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public long read(@NotNull Buffer sink2, long byteCount) {
        boolean $i$f$getBuffer;
        Intrinsics.checkNotNullParameter(sink2, "sink");
        RealBufferedSource $this$commonRead$iv = this;
        boolean $i$f$commonRead = false;
        boolean bl = byteCount >= 0L;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "byteCount < 0: " + byteCount;
            throw (Throwable)new IllegalArgumentException(string.toString());
        }
        bl = !$this$commonRead$iv.closed;
        bl2 = false;
        bl3 = false;
        if (!bl) {
            boolean bl5 = false;
            String string = "closed";
            throw (Throwable)new IllegalStateException(string.toString());
        }
        RealBufferedSource this_$iv$iv = $this$commonRead$iv;
        boolean $i$f$getBuffer2 = false;
        if (this_$iv$iv.bufferField.size() == 0L) {
            RealBufferedSource this_$iv$iv2 = $this$commonRead$iv;
            $i$f$getBuffer = false;
            long read$iv = $this$commonRead$iv.source.read(this_$iv$iv2.bufferField, 8192);
            if (read$iv == -1L) {
                return -1L;
            }
        }
        RealBufferedSource this_$iv$iv3 = $this$commonRead$iv;
        $i$f$getBuffer = false;
        long l = this_$iv$iv3.bufferField.size();
        boolean bl6 = false;
        long toRead$iv = Math.min(byteCount, l);
        this_$iv$iv3 = $this$commonRead$iv;
        $i$f$getBuffer = false;
        long l2 = this_$iv$iv3.bufferField.read(sink2, toRead$iv);
        return l2;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public boolean exhausted() {
        RealBufferedSource $this$commonExhausted$iv = this;
        boolean $i$f$commonExhausted = false;
        boolean bl = !$this$commonExhausted$iv.closed;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "closed";
            throw (Throwable)new IllegalStateException(string.toString());
        }
        RealBufferedSource this_$iv$iv = $this$commonExhausted$iv;
        boolean $i$f$getBuffer = false;
        if (!this_$iv$iv.bufferField.exhausted()) return false;
        this_$iv$iv = $this$commonExhausted$iv;
        $i$f$getBuffer = false;
        if ($this$commonExhausted$iv.source.read(this_$iv$iv.bufferField, 8192) != -1L) return false;
        return true;
    }

    @Override
    public void require(long byteCount) {
        RealBufferedSource $this$commonRequire$iv = this;
        boolean $i$f$commonRequire = false;
        if (!$this$commonRequire$iv.request(byteCount)) {
            throw (Throwable)new EOFException();
        }
    }

    @Override
    public boolean request(long byteCount) {
        boolean bl;
        block4: {
            block3: {
                RealBufferedSource this_$iv$iv;
                RealBufferedSource $this$commonRequest$iv = this;
                boolean $i$f$commonRequest = false;
                boolean bl2 = byteCount >= 0L;
                boolean bl3 = false;
                boolean bl4 = false;
                if (!bl2) {
                    boolean bl5 = false;
                    String string = "byteCount < 0: " + byteCount;
                    throw (Throwable)new IllegalArgumentException(string.toString());
                }
                bl2 = !$this$commonRequest$iv.closed;
                bl3 = false;
                bl4 = false;
                if (!bl2) {
                    boolean bl6 = false;
                    String string = "closed";
                    throw (Throwable)new IllegalStateException(string.toString());
                }
                do {
                    this_$iv$iv = $this$commonRequest$iv;
                    boolean $i$f$getBuffer = false;
                    if (this_$iv$iv.bufferField.size() >= byteCount) break block3;
                    this_$iv$iv = $this$commonRequest$iv;
                    $i$f$getBuffer = false;
                } while ($this$commonRequest$iv.source.read(this_$iv$iv.bufferField, 8192) != -1L);
                bl = false;
                break block4;
            }
            bl = true;
        }
        return bl;
    }

    @Override
    public byte readByte() {
        RealBufferedSource $this$commonReadByte$iv = this;
        boolean $i$f$commonReadByte = false;
        $this$commonReadByte$iv.require(1L);
        RealBufferedSource this_$iv$iv = $this$commonReadByte$iv;
        boolean $i$f$getBuffer = false;
        return this_$iv$iv.bufferField.readByte();
    }

    @Override
    @NotNull
    public ByteString readByteString() {
        RealBufferedSource $this$commonReadByteString$iv = this;
        boolean $i$f$commonReadByteString = false;
        RealBufferedSource this_$iv$iv = $this$commonReadByteString$iv;
        boolean $i$f$getBuffer = false;
        this_$iv$iv.bufferField.writeAll($this$commonReadByteString$iv.source);
        this_$iv$iv = $this$commonReadByteString$iv;
        $i$f$getBuffer = false;
        return this_$iv$iv.bufferField.readByteString();
    }

    @Override
    @NotNull
    public ByteString readByteString(long byteCount) {
        RealBufferedSource $this$commonReadByteString$iv = this;
        boolean $i$f$commonReadByteString = false;
        $this$commonReadByteString$iv.require(byteCount);
        RealBufferedSource this_$iv$iv = $this$commonReadByteString$iv;
        boolean $i$f$getBuffer = false;
        return this_$iv$iv.bufferField.readByteString(byteCount);
    }

    @Override
    public int select(@NotNull Options options) {
        int n;
        Intrinsics.checkNotNullParameter(options, "options");
        RealBufferedSource $this$commonSelect$iv = this;
        boolean $i$f$commonSelect = false;
        boolean bl = !$this$commonSelect$iv.closed;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "closed";
            throw (Throwable)new IllegalStateException(string.toString());
        }
        block4: while (true) {
            RealBufferedSource this_$iv$iv = $this$commonSelect$iv;
            boolean $i$f$getBuffer = false;
            int index$iv = BufferKt.selectPrefix(this_$iv$iv.bufferField, options, true);
            switch (index$iv) {
                case -1: {
                    n = -1;
                    break block4;
                }
                case -2: {
                    this_$iv$iv = $this$commonSelect$iv;
                    $i$f$getBuffer = false;
                    if ($this$commonSelect$iv.source.read(this_$iv$iv.bufferField, 8192) != -1L) continue block4;
                    n = -1;
                    break block4;
                }
                default: {
                    int selectedSize$iv = options.getByteStrings$okio()[index$iv].size();
                    RealBufferedSource this_$iv$iv2 = $this$commonSelect$iv;
                    boolean $i$f$getBuffer2 = false;
                    this_$iv$iv2.bufferField.skip(selectedSize$iv);
                    n = index$iv;
                    break block4;
                }
            }
            break;
        }
        return n;
    }

    @Override
    @NotNull
    public byte[] readByteArray() {
        RealBufferedSource $this$commonReadByteArray$iv = this;
        boolean $i$f$commonReadByteArray = false;
        RealBufferedSource this_$iv$iv = $this$commonReadByteArray$iv;
        boolean $i$f$getBuffer = false;
        this_$iv$iv.bufferField.writeAll($this$commonReadByteArray$iv.source);
        this_$iv$iv = $this$commonReadByteArray$iv;
        $i$f$getBuffer = false;
        return this_$iv$iv.bufferField.readByteArray();
    }

    @Override
    @NotNull
    public byte[] readByteArray(long byteCount) {
        RealBufferedSource $this$commonReadByteArray$iv = this;
        boolean $i$f$commonReadByteArray = false;
        $this$commonReadByteArray$iv.require(byteCount);
        RealBufferedSource this_$iv$iv = $this$commonReadByteArray$iv;
        boolean $i$f$getBuffer = false;
        return this_$iv$iv.bufferField.readByteArray(byteCount);
    }

    @Override
    public int read(@NotNull byte[] sink2) {
        Intrinsics.checkNotNullParameter(sink2, "sink");
        return this.read(sink2, 0, sink2.length);
    }

    @Override
    public void readFully(@NotNull byte[] sink2) {
        Intrinsics.checkNotNullParameter(sink2, "sink");
        RealBufferedSource $this$commonReadFully$iv = this;
        boolean $i$f$commonReadFully = false;
        try {
            $this$commonReadFully$iv.require(sink2.length);
        } catch (EOFException e$iv) {
            int offset$iv = 0;
            while (true) {
                RealBufferedSource this_$iv$iv = $this$commonReadFully$iv;
                boolean $i$f$getBuffer = false;
                if (this_$iv$iv.bufferField.size() <= 0L) break;
                RealBufferedSource this_$iv$iv2 = $this$commonReadFully$iv;
                boolean $i$f$getBuffer2 = false;
                this_$iv$iv2 = $this$commonReadFully$iv;
                $i$f$getBuffer2 = false;
                int read$iv = this_$iv$iv2.bufferField.read(sink2, offset$iv, (int)this_$iv$iv2.bufferField.size());
                if (read$iv == -1) {
                    throw (Throwable)((Object)new AssertionError());
                }
                offset$iv += read$iv;
            }
            throw (Throwable)e$iv;
        }
        RealBufferedSource this_$iv$iv = $this$commonReadFully$iv;
        boolean $i$f$getBuffer = false;
        this_$iv$iv.bufferField.readFully(sink2);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public int read(@NotNull byte[] sink2, int offset, int byteCount) {
        Intrinsics.checkNotNullParameter(sink2, "sink");
        RealBufferedSource $this$commonRead$iv = this;
        boolean $i$f$commonRead = false;
        -Util.checkOffsetAndCount(sink2.length, offset, byteCount);
        RealBufferedSource this_$iv$iv = $this$commonRead$iv;
        boolean $i$f$getBuffer = false;
        if (this_$iv$iv.bufferField.size() == 0L) {
            RealBufferedSource this_$iv$iv2 = $this$commonRead$iv;
            boolean $i$f$getBuffer2 = false;
            long read$iv = $this$commonRead$iv.source.read(this_$iv$iv2.bufferField, 8192);
            if (read$iv == -1L) {
                return -1;
            }
        }
        RealBufferedSource this_$iv$iv3 = $this$commonRead$iv;
        boolean $i$f$getBuffer3 = false;
        long b$iv$iv = this_$iv$iv3.bufferField.size();
        boolean $i$f$minOf = false;
        long l = byteCount;
        boolean bl = false;
        int toRead$iv = (int)Math.min(l, b$iv$iv);
        this_$iv$iv3 = $this$commonRead$iv;
        $i$f$getBuffer3 = false;
        int n = this_$iv$iv3.bufferField.read(sink2, offset, toRead$iv);
        return n;
    }

    @Override
    public int read(@NotNull ByteBuffer sink2) {
        Intrinsics.checkNotNullParameter(sink2, "sink");
        RealBufferedSource this_$iv = this;
        boolean $i$f$getBuffer = false;
        if (this_$iv.bufferField.size() == 0L) {
            RealBufferedSource this_$iv2 = this;
            boolean $i$f$getBuffer2 = false;
            long read = this.source.read(this_$iv2.bufferField, 8192);
            if (read == -1L) {
                return -1;
            }
        }
        this_$iv = this;
        $i$f$getBuffer = false;
        return this_$iv.bufferField.read(sink2);
    }

    @Override
    public void readFully(@NotNull Buffer sink2, long byteCount) {
        Intrinsics.checkNotNullParameter(sink2, "sink");
        RealBufferedSource $this$commonReadFully$iv = this;
        boolean $i$f$commonReadFully = false;
        try {
            $this$commonReadFully$iv.require(byteCount);
        } catch (EOFException e$iv) {
            RealBufferedSource this_$iv$iv = $this$commonReadFully$iv;
            boolean $i$f$getBuffer = false;
            sink2.writeAll(this_$iv$iv.bufferField);
            throw (Throwable)e$iv;
        }
        RealBufferedSource this_$iv$iv = $this$commonReadFully$iv;
        boolean $i$f$getBuffer = false;
        this_$iv$iv.bufferField.readFully(sink2, byteCount);
    }

    @Override
    public long readAll(@NotNull Sink sink2) {
        boolean $i$f$getBuffer;
        RealBufferedSource this_$iv$iv;
        Intrinsics.checkNotNullParameter(sink2, "sink");
        RealBufferedSource $this$commonReadAll$iv = this;
        boolean $i$f$commonReadAll = false;
        long totalBytesWritten$iv = 0L;
        while (true) {
            this_$iv$iv = $this$commonReadAll$iv;
            $i$f$getBuffer = false;
            if ($this$commonReadAll$iv.source.read(this_$iv$iv.bufferField, 8192) == -1L) break;
            RealBufferedSource this_$iv$iv2 = $this$commonReadAll$iv;
            boolean $i$f$getBuffer2 = false;
            long emitByteCount$iv = this_$iv$iv2.bufferField.completeSegmentByteCount();
            if (emitByteCount$iv <= 0L) continue;
            totalBytesWritten$iv += emitByteCount$iv;
            this_$iv$iv2 = $this$commonReadAll$iv;
            $i$f$getBuffer2 = false;
            sink2.write(this_$iv$iv2.bufferField, emitByteCount$iv);
        }
        this_$iv$iv = $this$commonReadAll$iv;
        $i$f$getBuffer = false;
        if (this_$iv$iv.bufferField.size() > 0L) {
            this_$iv$iv = $this$commonReadAll$iv;
            $i$f$getBuffer = false;
            totalBytesWritten$iv += this_$iv$iv.bufferField.size();
            this_$iv$iv = $this$commonReadAll$iv;
            $i$f$getBuffer = false;
            this_$iv$iv = $this$commonReadAll$iv;
            $i$f$getBuffer = false;
            sink2.write(this_$iv$iv.bufferField, this_$iv$iv.bufferField.size());
        }
        return totalBytesWritten$iv;
    }

    @Override
    @NotNull
    public String readUtf8() {
        RealBufferedSource $this$commonReadUtf8$iv = this;
        boolean $i$f$commonReadUtf8 = false;
        RealBufferedSource this_$iv$iv = $this$commonReadUtf8$iv;
        boolean $i$f$getBuffer = false;
        this_$iv$iv.bufferField.writeAll($this$commonReadUtf8$iv.source);
        this_$iv$iv = $this$commonReadUtf8$iv;
        $i$f$getBuffer = false;
        return this_$iv$iv.bufferField.readUtf8();
    }

    @Override
    @NotNull
    public String readUtf8(long byteCount) {
        RealBufferedSource $this$commonReadUtf8$iv = this;
        boolean $i$f$commonReadUtf8 = false;
        $this$commonReadUtf8$iv.require(byteCount);
        RealBufferedSource this_$iv$iv = $this$commonReadUtf8$iv;
        boolean $i$f$getBuffer = false;
        return this_$iv$iv.bufferField.readUtf8(byteCount);
    }

    @Override
    @NotNull
    public String readString(@NotNull Charset charset) {
        Intrinsics.checkNotNullParameter(charset, "charset");
        RealBufferedSource this_$iv = this;
        boolean $i$f$getBuffer = false;
        this_$iv.bufferField.writeAll(this.source);
        this_$iv = this;
        $i$f$getBuffer = false;
        return this_$iv.bufferField.readString(charset);
    }

    @Override
    @NotNull
    public String readString(long byteCount, @NotNull Charset charset) {
        Intrinsics.checkNotNullParameter(charset, "charset");
        this.require(byteCount);
        RealBufferedSource this_$iv = this;
        boolean $i$f$getBuffer = false;
        return this_$iv.bufferField.readString(byteCount, charset);
    }

    @Override
    @Nullable
    public String readUtf8Line() {
        String string;
        RealBufferedSource $this$commonReadUtf8Line$iv = this;
        boolean $i$f$commonReadUtf8Line = false;
        long newline$iv = $this$commonReadUtf8Line$iv.indexOf((byte)10);
        if (newline$iv == -1L) {
            RealBufferedSource this_$iv$iv = $this$commonReadUtf8Line$iv;
            boolean $i$f$getBuffer = false;
            if (this_$iv$iv.bufferField.size() != 0L) {
                this_$iv$iv = $this$commonReadUtf8Line$iv;
                $i$f$getBuffer = false;
                string = $this$commonReadUtf8Line$iv.readUtf8(this_$iv$iv.bufferField.size());
            } else {
                string = null;
            }
        } else {
            RealBufferedSource this_$iv$iv = $this$commonReadUtf8Line$iv;
            boolean $i$f$getBuffer = false;
            string = BufferKt.readUtf8Line(this_$iv$iv.bufferField, newline$iv);
        }
        return string;
    }

    @Override
    @NotNull
    public String readUtf8LineStrict() {
        return this.readUtf8LineStrict(Long.MAX_VALUE);
    }

    /*
     * WARNING - void declaration
     * Enabled aggressive block sorting
     */
    @Override
    @NotNull
    public String readUtf8LineStrict(long limit) {
        void a$iv$iv;
        String string;
        RealBufferedSource $this$commonReadUtf8LineStrict$iv = this;
        boolean $i$f$commonReadUtf8LineStrict = false;
        boolean bl = limit >= 0L;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string2 = "limit < 0: " + limit;
            throw (Throwable)new IllegalArgumentException(string2.toString());
        }
        long scanLength$iv = limit == Long.MAX_VALUE ? Long.MAX_VALUE : limit + 1L;
        long newline$iv = $this$commonReadUtf8LineStrict$iv.indexOf((byte)10, 0L, scanLength$iv);
        if (newline$iv != -1L) {
            RealBufferedSource this_$iv$iv = $this$commonReadUtf8LineStrict$iv;
            boolean $i$f$getBuffer = false;
            string = BufferKt.readUtf8Line(this_$iv$iv.bufferField, newline$iv);
            return string;
        }
        if (scanLength$iv < Long.MAX_VALUE && $this$commonReadUtf8LineStrict$iv.request(scanLength$iv)) {
            RealBufferedSource this_$iv$iv = $this$commonReadUtf8LineStrict$iv;
            boolean $i$f$getBuffer = false;
            if (this_$iv$iv.bufferField.getByte(scanLength$iv - 1L) == (byte)13 && $this$commonReadUtf8LineStrict$iv.request(scanLength$iv + 1L)) {
                this_$iv$iv = $this$commonReadUtf8LineStrict$iv;
                $i$f$getBuffer = false;
                if (this_$iv$iv.bufferField.getByte(scanLength$iv) == (byte)10) {
                    this_$iv$iv = $this$commonReadUtf8LineStrict$iv;
                    $i$f$getBuffer = false;
                    string = BufferKt.readUtf8Line(this_$iv$iv.bufferField, scanLength$iv);
                    return string;
                }
            }
        }
        Buffer data$iv = new Buffer();
        RealBufferedSource this_$iv$iv22 = $this$commonReadUtf8LineStrict$iv;
        boolean $i$f$getBuffer = false;
        int this_$iv$iv22 = 32;
        RealBufferedSource this_$iv$iv = $this$commonReadUtf8LineStrict$iv;
        boolean $i$f$getBuffer2 = false;
        long b$iv$iv = this_$iv$iv.bufferField.size();
        boolean $i$f$minOf = false;
        long l = (long)a$iv$iv;
        boolean bl5 = false;
        this_$iv$iv22.bufferField.copyTo(data$iv, 0L, Math.min(l, b$iv$iv));
        RealBufferedSource this_$iv$iv3 = $this$commonReadUtf8LineStrict$iv;
        $i$f$getBuffer = false;
        long l2 = this_$iv$iv3.bufferField.size();
        boolean bl6 = false;
        throw (Throwable)new EOFException("\\n not found: limit=" + Math.min(l2, limit) + " content=" + data$iv.readByteString().hex() + "\u2026");
    }

    @Override
    public int readUtf8CodePoint() {
        RealBufferedSource $this$commonReadUtf8CodePoint$iv = this;
        boolean $i$f$commonReadUtf8CodePoint = false;
        $this$commonReadUtf8CodePoint$iv.require(1L);
        RealBufferedSource this_$iv$iv = $this$commonReadUtf8CodePoint$iv;
        boolean $i$f$getBuffer = false;
        byte b0$iv = this_$iv$iv.bufferField.getByte(0L);
        if ((b0$iv & 0xE0) == 192) {
            $this$commonReadUtf8CodePoint$iv.require(2L);
        } else if ((b0$iv & 0xF0) == 224) {
            $this$commonReadUtf8CodePoint$iv.require(3L);
        } else if ((b0$iv & 0xF8) == 240) {
            $this$commonReadUtf8CodePoint$iv.require(4L);
        }
        this_$iv$iv = $this$commonReadUtf8CodePoint$iv;
        $i$f$getBuffer = false;
        return this_$iv$iv.bufferField.readUtf8CodePoint();
    }

    @Override
    public short readShort() {
        RealBufferedSource $this$commonReadShort$iv = this;
        boolean $i$f$commonReadShort = false;
        $this$commonReadShort$iv.require(2L);
        RealBufferedSource this_$iv$iv = $this$commonReadShort$iv;
        boolean $i$f$getBuffer = false;
        return this_$iv$iv.bufferField.readShort();
    }

    @Override
    public short readShortLe() {
        RealBufferedSource $this$commonReadShortLe$iv = this;
        boolean $i$f$commonReadShortLe = false;
        $this$commonReadShortLe$iv.require(2L);
        RealBufferedSource this_$iv$iv = $this$commonReadShortLe$iv;
        boolean $i$f$getBuffer = false;
        return this_$iv$iv.bufferField.readShortLe();
    }

    @Override
    public int readInt() {
        RealBufferedSource $this$commonReadInt$iv = this;
        boolean $i$f$commonReadInt = false;
        $this$commonReadInt$iv.require(4L);
        RealBufferedSource this_$iv$iv = $this$commonReadInt$iv;
        boolean $i$f$getBuffer = false;
        return this_$iv$iv.bufferField.readInt();
    }

    @Override
    public int readIntLe() {
        RealBufferedSource $this$commonReadIntLe$iv = this;
        boolean $i$f$commonReadIntLe = false;
        $this$commonReadIntLe$iv.require(4L);
        RealBufferedSource this_$iv$iv = $this$commonReadIntLe$iv;
        boolean $i$f$getBuffer = false;
        return this_$iv$iv.bufferField.readIntLe();
    }

    @Override
    public long readLong() {
        RealBufferedSource $this$commonReadLong$iv = this;
        boolean $i$f$commonReadLong = false;
        $this$commonReadLong$iv.require(8L);
        RealBufferedSource this_$iv$iv = $this$commonReadLong$iv;
        boolean $i$f$getBuffer = false;
        return this_$iv$iv.bufferField.readLong();
    }

    @Override
    public long readLongLe() {
        RealBufferedSource $this$commonReadLongLe$iv = this;
        boolean $i$f$commonReadLongLe = false;
        $this$commonReadLongLe$iv.require(8L);
        RealBufferedSource this_$iv$iv = $this$commonReadLongLe$iv;
        boolean $i$f$getBuffer = false;
        return this_$iv$iv.bufferField.readLongLe();
    }

    @Override
    public long readDecimalLong() {
        RealBufferedSource $this$commonReadDecimalLong$iv = this;
        boolean $i$f$commonReadDecimalLong = false;
        $this$commonReadDecimalLong$iv.require(1L);
        long pos$iv = 0L;
        while ($this$commonReadDecimalLong$iv.request(pos$iv + 1L)) {
            RealBufferedSource this_$iv$iv2 = $this$commonReadDecimalLong$iv;
            boolean $i$f$getBuffer = false;
            byte b$iv = this_$iv$iv2.bufferField.getByte(pos$iv);
            if (!(b$iv >= (byte)48 && b$iv <= (byte)57 || pos$iv == 0L && b$iv == (byte)45)) {
                if (pos$iv != 0L) break;
                StringBuilder stringBuilder = new StringBuilder().append("Expected leading [0-9] or '-' character but was 0x");
                byte this_$iv$iv2 = b$iv;
                int n = 16;
                boolean bl = false;
                byte by = this_$iv$iv2;
                int n2 = CharsKt.checkRadix(n);
                boolean bl2 = false;
                String string = Integer.toString(by, CharsKt.checkRadix(n2));
                Intrinsics.checkNotNullExpressionValue(string, "java.lang.Integer.toStri\u2026(this, checkRadix(radix))");
                throw (Throwable)new NumberFormatException(stringBuilder.append(string).toString());
            }
            long l = pos$iv;
            pos$iv = l + 1L;
        }
        RealBufferedSource this_$iv$iv = $this$commonReadDecimalLong$iv;
        boolean $i$f$getBuffer = false;
        return this_$iv$iv.bufferField.readDecimalLong();
    }

    @Override
    public long readHexadecimalUnsignedLong() {
        RealBufferedSource $this$commonReadHexadecimalUnsignedLong$iv = this;
        boolean $i$f$commonReadHexadecimalUnsignedLong = false;
        $this$commonReadHexadecimalUnsignedLong$iv.require(1L);
        int pos$iv = 0;
        while ($this$commonReadHexadecimalUnsignedLong$iv.request(pos$iv + 1)) {
            RealBufferedSource this_$iv$iv2 = $this$commonReadHexadecimalUnsignedLong$iv;
            boolean $i$f$getBuffer = false;
            byte b$iv = this_$iv$iv2.bufferField.getByte(pos$iv);
            if (!(b$iv >= (byte)48 && b$iv <= (byte)57 || b$iv >= (byte)97 && b$iv <= (byte)102 || b$iv >= (byte)65 && b$iv <= (byte)70)) {
                if (pos$iv != 0) break;
                StringBuilder stringBuilder = new StringBuilder().append("Expected leading [0-9a-fA-F] character but was 0x");
                byte this_$iv$iv2 = b$iv;
                int n = 16;
                boolean bl = false;
                byte by = this_$iv$iv2;
                int n2 = CharsKt.checkRadix(n);
                boolean bl2 = false;
                String string = Integer.toString(by, CharsKt.checkRadix(n2));
                Intrinsics.checkNotNullExpressionValue(string, "java.lang.Integer.toStri\u2026(this, checkRadix(radix))");
                throw (Throwable)new NumberFormatException(stringBuilder.append(string).toString());
            }
            ++pos$iv;
        }
        RealBufferedSource this_$iv$iv = $this$commonReadHexadecimalUnsignedLong$iv;
        boolean $i$f$getBuffer = false;
        return this_$iv$iv.bufferField.readHexadecimalUnsignedLong();
    }

    @Override
    public void skip(long byteCount) {
        long toSkip$iv;
        RealBufferedSource $this$commonSkip$iv = this;
        boolean $i$f$commonSkip = false;
        boolean bl = !$this$commonSkip$iv.closed;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "closed";
            throw (Throwable)new IllegalStateException(string.toString());
        }
        for (long byteCount$iv = byteCount; byteCount$iv > 0L; byteCount$iv -= toSkip$iv) {
            RealBufferedSource this_$iv$iv = $this$commonSkip$iv;
            boolean $i$f$getBuffer = false;
            if (this_$iv$iv.bufferField.size() == 0L) {
                this_$iv$iv = $this$commonSkip$iv;
                $i$f$getBuffer = false;
                if ($this$commonSkip$iv.source.read(this_$iv$iv.bufferField, 8192) == -1L) {
                    throw (Throwable)new EOFException();
                }
            }
            RealBufferedSource this_$iv$iv2 = $this$commonSkip$iv;
            boolean $i$f$getBuffer2 = false;
            long l = this_$iv$iv2.bufferField.size();
            boolean bl5 = false;
            toSkip$iv = Math.min(byteCount$iv, l);
            this_$iv$iv2 = $this$commonSkip$iv;
            $i$f$getBuffer2 = false;
            this_$iv$iv2.bufferField.skip(toSkip$iv);
        }
    }

    @Override
    public long indexOf(byte b) {
        return this.indexOf(b, 0L, Long.MAX_VALUE);
    }

    @Override
    public long indexOf(byte b, long fromIndex) {
        return this.indexOf(b, fromIndex, Long.MAX_VALUE);
    }

    @Override
    public long indexOf(byte b, long fromIndex, long toIndex) {
        long l;
        block6: {
            RealBufferedSource $this$commonIndexOf$iv = this;
            boolean $i$f$commonIndexOf = false;
            long fromIndex$iv = fromIndex;
            boolean bl = !$this$commonIndexOf$iv.closed;
            boolean bl2 = false;
            boolean bl3 = false;
            if (!bl) {
                boolean bl4 = false;
                String string = "closed";
                throw (Throwable)new IllegalStateException(string.toString());
            }
            long l2 = fromIndex$iv;
            bl = 0L <= l2 && toIndex >= l2;
            bl2 = false;
            bl3 = false;
            if (!bl) {
                boolean bl5 = false;
                String string = "fromIndex=" + fromIndex$iv + " toIndex=" + toIndex;
                throw (Throwable)new IllegalArgumentException(string.toString());
            }
            while (fromIndex$iv < toIndex) {
                long lastBufferSize$iv;
                block8: {
                    block7: {
                        RealBufferedSource this_$iv$iv = $this$commonIndexOf$iv;
                        boolean $i$f$getBuffer = false;
                        long result$iv = this_$iv$iv.bufferField.indexOf(b, fromIndex$iv, toIndex);
                        if (result$iv != -1L) {
                            l = result$iv;
                            break block6;
                        }
                        RealBufferedSource this_$iv$iv2 = $this$commonIndexOf$iv;
                        boolean $i$f$getBuffer2 = false;
                        lastBufferSize$iv = this_$iv$iv2.bufferField.size();
                        if (lastBufferSize$iv >= toIndex) break block7;
                        this_$iv$iv2 = $this$commonIndexOf$iv;
                        $i$f$getBuffer2 = false;
                        if ($this$commonIndexOf$iv.source.read(this_$iv$iv2.bufferField, 8192) != -1L) break block8;
                    }
                    l = -1L;
                    break block6;
                }
                long l3 = fromIndex$iv;
                boolean bl6 = false;
                fromIndex$iv = Math.max(l3, lastBufferSize$iv);
            }
            l = -1L;
        }
        return l;
    }

    @Override
    public long indexOf(@NotNull ByteString bytes) {
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        return this.indexOf(bytes, 0L);
    }

    @Override
    public long indexOf(@NotNull ByteString bytes, long fromIndex) {
        long l;
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        RealBufferedSource $this$commonIndexOf$iv = this;
        boolean $i$f$commonIndexOf = false;
        long fromIndex$iv = fromIndex;
        boolean bl = !$this$commonIndexOf$iv.closed;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "closed";
            throw (Throwable)new IllegalStateException(string.toString());
        }
        while (true) {
            RealBufferedSource this_$iv$iv = $this$commonIndexOf$iv;
            boolean $i$f$getBuffer = false;
            long result$iv = this_$iv$iv.bufferField.indexOf(bytes, fromIndex$iv);
            if (result$iv != -1L) {
                l = result$iv;
                break;
            }
            RealBufferedSource this_$iv$iv2 = $this$commonIndexOf$iv;
            boolean $i$f$getBuffer2 = false;
            long lastBufferSize$iv = this_$iv$iv2.bufferField.size();
            this_$iv$iv2 = $this$commonIndexOf$iv;
            $i$f$getBuffer2 = false;
            if ($this$commonIndexOf$iv.source.read(this_$iv$iv2.bufferField, 8192) == -1L) {
                l = -1L;
                break;
            }
            long l2 = lastBufferSize$iv - (long)bytes.size() + 1L;
            boolean bl5 = false;
            fromIndex$iv = Math.max(fromIndex$iv, l2);
        }
        return l;
    }

    @Override
    public long indexOfElement(@NotNull ByteString targetBytes) {
        Intrinsics.checkNotNullParameter(targetBytes, "targetBytes");
        return this.indexOfElement(targetBytes, 0L);
    }

    @Override
    public long indexOfElement(@NotNull ByteString targetBytes, long fromIndex) {
        long l;
        Intrinsics.checkNotNullParameter(targetBytes, "targetBytes");
        RealBufferedSource $this$commonIndexOfElement$iv = this;
        boolean $i$f$commonIndexOfElement = false;
        long fromIndex$iv = fromIndex;
        boolean bl = !$this$commonIndexOfElement$iv.closed;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "closed";
            throw (Throwable)new IllegalStateException(string.toString());
        }
        while (true) {
            RealBufferedSource this_$iv$iv = $this$commonIndexOfElement$iv;
            boolean $i$f$getBuffer = false;
            long result$iv = this_$iv$iv.bufferField.indexOfElement(targetBytes, fromIndex$iv);
            if (result$iv != -1L) {
                l = result$iv;
                break;
            }
            RealBufferedSource this_$iv$iv2 = $this$commonIndexOfElement$iv;
            boolean $i$f$getBuffer2 = false;
            long lastBufferSize$iv = this_$iv$iv2.bufferField.size();
            this_$iv$iv2 = $this$commonIndexOfElement$iv;
            $i$f$getBuffer2 = false;
            if ($this$commonIndexOfElement$iv.source.read(this_$iv$iv2.bufferField, 8192) == -1L) {
                l = -1L;
                break;
            }
            boolean bl5 = false;
            fromIndex$iv = Math.max(fromIndex$iv, lastBufferSize$iv);
        }
        return l;
    }

    @Override
    public boolean rangeEquals(long offset, @NotNull ByteString bytes) {
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        return this.rangeEquals(offset, bytes, 0, bytes.size());
    }

    /*
     * WARNING - void declaration
     */
    @Override
    public boolean rangeEquals(long offset, @NotNull ByteString bytes, int bytesOffset, int byteCount) {
        boolean bl;
        block6: {
            Intrinsics.checkNotNullParameter(bytes, "bytes");
            RealBufferedSource $this$commonRangeEquals$iv = this;
            boolean $i$f$commonRangeEquals = false;
            int n = !$this$commonRangeEquals$iv.closed ? 1 : 0;
            int n2 = 0;
            boolean bl2 = false;
            if (n == 0) {
                boolean bl3 = false;
                String string = "closed";
                throw (Throwable)new IllegalStateException(string.toString());
            }
            if (offset < 0L || bytesOffset < 0 || byteCount < 0 || bytes.size() - bytesOffset < byteCount) {
                bl = false;
            } else {
                n = 0;
                n2 = byteCount;
                while (n < n2) {
                    void i$iv;
                    long bufferOffset$iv = offset + (long)i$iv;
                    if (!$this$commonRangeEquals$iv.request(bufferOffset$iv + 1L)) {
                        bl = false;
                        break block6;
                    }
                    RealBufferedSource this_$iv$iv = $this$commonRangeEquals$iv;
                    boolean $i$f$getBuffer = false;
                    if (this_$iv$iv.bufferField.getByte(bufferOffset$iv) != bytes.getByte(bytesOffset + i$iv)) {
                        bl = false;
                        break block6;
                    }
                    ++i$iv;
                }
                bl = true;
            }
        }
        return bl;
    }

    @Override
    @NotNull
    public BufferedSource peek() {
        RealBufferedSource $this$commonPeek$iv = this;
        boolean $i$f$commonPeek = false;
        return Okio.buffer(new PeekSource($this$commonPeek$iv));
    }

    @Override
    @NotNull
    public InputStream inputStream() {
        return new InputStream(this){
            final /* synthetic */ RealBufferedSource this$0;

            /*
             * WARNING - void declaration
             */
            public int read() {
                void $this$and$iv;
                if (this.this$0.closed) {
                    throw (Throwable)new IOException("closed");
                }
                RealBufferedSource this_$iv = this.this$0;
                boolean $i$f$getBuffer = false;
                if (this_$iv.bufferField.size() == 0L) {
                    RealBufferedSource this_$iv2 = this.this$0;
                    boolean $i$f$getBuffer2 = false;
                    long count = this.this$0.source.read(this_$iv2.bufferField, 8192);
                    if (count == -1L) {
                        return -1;
                    }
                }
                this_$iv = this.this$0;
                $i$f$getBuffer = false;
                byte this_$iv2 = this_$iv.bufferField.readByte();
                int other$iv = 255;
                boolean $i$f$and = false;
                return $this$and$iv & other$iv;
            }

            public int read(@NotNull byte[] data, int offset, int byteCount) {
                Intrinsics.checkNotNullParameter(data, "data");
                if (this.this$0.closed) {
                    throw (Throwable)new IOException("closed");
                }
                -Util.checkOffsetAndCount(data.length, offset, byteCount);
                RealBufferedSource this_$iv = this.this$0;
                boolean $i$f$getBuffer = false;
                if (this_$iv.bufferField.size() == 0L) {
                    RealBufferedSource this_$iv2 = this.this$0;
                    boolean $i$f$getBuffer2 = false;
                    long count = this.this$0.source.read(this_$iv2.bufferField, 8192);
                    if (count == -1L) {
                        return -1;
                    }
                }
                this_$iv = this.this$0;
                $i$f$getBuffer = false;
                return this_$iv.bufferField.read(data, offset, byteCount);
            }

            /*
             * WARNING - void declaration
             */
            public int available() {
                void a$iv;
                if (this.this$0.closed) {
                    throw (Throwable)new IOException("closed");
                }
                RealBufferedSource this_$iv22 = this.this$0;
                boolean $i$f$getBuffer = false;
                long this_$iv22 = this_$iv22.bufferField.size();
                int b$iv = Integer.MAX_VALUE;
                boolean $i$f$minOf = false;
                long l = b$iv;
                boolean bl = false;
                return (int)Math.min((long)a$iv, l);
            }

            public void close() {
                this.this$0.close();
            }

            @NotNull
            public String toString() {
                return this.this$0 + ".inputStream()";
            }
            {
                this.this$0 = this$0;
            }
        };
    }

    @Override
    public boolean isOpen() {
        return !this.closed;
    }

    @Override
    public void close() {
        RealBufferedSource $this$commonClose$iv = this;
        boolean $i$f$commonClose = false;
        if (!$this$commonClose$iv.closed) {
            $this$commonClose$iv.closed = true;
            $this$commonClose$iv.source.close();
            RealBufferedSource this_$iv$iv = $this$commonClose$iv;
            boolean $i$f$getBuffer = false;
            this_$iv$iv.bufferField.clear();
        }
    }

    @Override
    @NotNull
    public Timeout timeout() {
        RealBufferedSource $this$commonTimeout$iv = this;
        boolean $i$f$commonTimeout = false;
        return $this$commonTimeout$iv.source.timeout();
    }

    @NotNull
    public String toString() {
        RealBufferedSource $this$commonToString$iv = this;
        boolean $i$f$commonToString = false;
        return "buffer(" + $this$commonToString$iv.source + ')';
    }

    public RealBufferedSource(@NotNull Source source2) {
        Intrinsics.checkNotNullParameter(source2, "source");
        this.source = source2;
        this.bufferField = new Buffer();
    }
}

