/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okio;

import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.util.Arrays;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.ArraysKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.Charsets;
import okio.-Util;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ByteString;
import okio.Okio;
import okio.Options;
import okio.PeekSource;
import okio.Segment;
import okio.SegmentPool;
import okio.SegmentedByteString;
import okio.Sink;
import okio.Source;
import okio.Timeout;
import okio.internal.BufferKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\u00aa\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u001a\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0005\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\n\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0017\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004:\u0002\u0090\u0001B\u0005\u00a2\u0006\u0002\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0000H\u0016J\u0006\u0010\u0011\u001a\u00020\u0012J\b\u0010\u0013\u001a\u00020\u0000H\u0016J\b\u0010\u0014\u001a\u00020\u0012H\u0016J\u0006\u0010\u0015\u001a\u00020\fJ\u0006\u0010\u0016\u001a\u00020\u0000J$\u0010\u0017\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\f2\b\b\u0002\u0010\u001b\u001a\u00020\fH\u0007J\u0018\u0010\u0017\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u001a\u001a\u00020\fJ \u0010\u0017\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u001a\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\fJ\u0010\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0002J\b\u0010 \u001a\u00020\u0000H\u0016J\b\u0010!\u001a\u00020\u0000H\u0016J\u0013\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010%H\u0096\u0002J\b\u0010&\u001a\u00020#H\u0016J\b\u0010'\u001a\u00020\u0012H\u0016J\u0016\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020\fH\u0087\u0002\u00a2\u0006\u0002\b+J\u0015\u0010+\u001a\u00020)2\u0006\u0010,\u001a\u00020\fH\u0007\u00a2\u0006\u0002\b-J\b\u0010.\u001a\u00020/H\u0016J\u0018\u00100\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u00101\u001a\u00020\u001dH\u0002J\u000e\u00102\u001a\u00020\u001d2\u0006\u00101\u001a\u00020\u001dJ\u000e\u00103\u001a\u00020\u001d2\u0006\u00101\u001a\u00020\u001dJ\u000e\u00104\u001a\u00020\u001d2\u0006\u00101\u001a\u00020\u001dJ\u0010\u00105\u001a\u00020\f2\u0006\u00106\u001a\u00020)H\u0016J\u0018\u00105\u001a\u00020\f2\u0006\u00106\u001a\u00020)2\u0006\u00107\u001a\u00020\fH\u0016J \u00105\u001a\u00020\f2\u0006\u00106\u001a\u00020)2\u0006\u00107\u001a\u00020\f2\u0006\u00108\u001a\u00020\fH\u0016J\u0010\u00105\u001a\u00020\f2\u0006\u00109\u001a\u00020\u001dH\u0016J\u0018\u00105\u001a\u00020\f2\u0006\u00109\u001a\u00020\u001d2\u0006\u00107\u001a\u00020\fH\u0016J\u0010\u0010:\u001a\u00020\f2\u0006\u0010;\u001a\u00020\u001dH\u0016J\u0018\u0010:\u001a\u00020\f2\u0006\u0010;\u001a\u00020\u001d2\u0006\u00107\u001a\u00020\fH\u0016J\b\u0010<\u001a\u00020=H\u0016J\b\u0010>\u001a\u00020#H\u0016J\u0006\u0010?\u001a\u00020\u001dJ\b\u0010@\u001a\u00020\u0019H\u0016J\b\u0010A\u001a\u00020\u0001H\u0016J\u0018\u0010B\u001a\u00020#2\u0006\u0010\u001a\u001a\u00020\f2\u0006\u00109\u001a\u00020\u001dH\u0016J(\u0010B\u001a\u00020#2\u0006\u0010\u001a\u001a\u00020\f2\u0006\u00109\u001a\u00020\u001d2\u0006\u0010C\u001a\u00020/2\u0006\u0010\u001b\u001a\u00020/H\u0016J\u0010\u0010D\u001a\u00020/2\u0006\u0010E\u001a\u00020FH\u0016J\u0010\u0010D\u001a\u00020/2\u0006\u0010E\u001a\u00020GH\u0016J \u0010D\u001a\u00020/2\u0006\u0010E\u001a\u00020G2\u0006\u0010\u001a\u001a\u00020/2\u0006\u0010\u001b\u001a\u00020/H\u0016J\u0018\u0010D\u001a\u00020\f2\u0006\u0010E\u001a\u00020\u00002\u0006\u0010\u001b\u001a\u00020\fH\u0016J\u0010\u0010H\u001a\u00020\f2\u0006\u0010E\u001a\u00020IH\u0016J\u0012\u0010J\u001a\u00020K2\b\b\u0002\u0010L\u001a\u00020KH\u0007J\b\u0010M\u001a\u00020)H\u0016J\b\u0010N\u001a\u00020GH\u0016J\u0010\u0010N\u001a\u00020G2\u0006\u0010\u001b\u001a\u00020\fH\u0016J\b\u0010O\u001a\u00020\u001dH\u0016J\u0010\u0010O\u001a\u00020\u001d2\u0006\u0010\u001b\u001a\u00020\fH\u0016J\b\u0010P\u001a\u00020\fH\u0016J\u000e\u0010Q\u001a\u00020\u00002\u0006\u0010R\u001a\u00020=J\u0016\u0010Q\u001a\u00020\u00002\u0006\u0010R\u001a\u00020=2\u0006\u0010\u001b\u001a\u00020\fJ \u0010Q\u001a\u00020\u00122\u0006\u0010R\u001a\u00020=2\u0006\u0010\u001b\u001a\u00020\f2\u0006\u0010S\u001a\u00020#H\u0002J\u0010\u0010T\u001a\u00020\u00122\u0006\u0010E\u001a\u00020GH\u0016J\u0018\u0010T\u001a\u00020\u00122\u0006\u0010E\u001a\u00020\u00002\u0006\u0010\u001b\u001a\u00020\fH\u0016J\b\u0010U\u001a\u00020\fH\u0016J\b\u0010V\u001a\u00020/H\u0016J\b\u0010W\u001a\u00020/H\u0016J\b\u0010X\u001a\u00020\fH\u0016J\b\u0010Y\u001a\u00020\fH\u0016J\b\u0010Z\u001a\u00020[H\u0016J\b\u0010\\\u001a\u00020[H\u0016J\u0010\u0010]\u001a\u00020\u001f2\u0006\u0010^\u001a\u00020_H\u0016J\u0018\u0010]\u001a\u00020\u001f2\u0006\u0010\u001b\u001a\u00020\f2\u0006\u0010^\u001a\u00020_H\u0016J\u0012\u0010`\u001a\u00020K2\b\b\u0002\u0010L\u001a\u00020KH\u0007J\b\u0010a\u001a\u00020\u001fH\u0016J\u0010\u0010a\u001a\u00020\u001f2\u0006\u0010\u001b\u001a\u00020\fH\u0016J\b\u0010b\u001a\u00020/H\u0016J\n\u0010c\u001a\u0004\u0018\u00010\u001fH\u0016J\b\u0010d\u001a\u00020\u001fH\u0016J\u0010\u0010d\u001a\u00020\u001f2\u0006\u0010e\u001a\u00020\fH\u0016J\u0010\u0010f\u001a\u00020#2\u0006\u0010\u001b\u001a\u00020\fH\u0016J\u0010\u0010g\u001a\u00020\u00122\u0006\u0010\u001b\u001a\u00020\fH\u0016J\u0010\u0010h\u001a\u00020/2\u0006\u0010i\u001a\u00020jH\u0016J\u0006\u0010k\u001a\u00020\u001dJ\u0006\u0010l\u001a\u00020\u001dJ\u0006\u0010m\u001a\u00020\u001dJ\r\u0010\r\u001a\u00020\fH\u0007\u00a2\u0006\u0002\bnJ\u0010\u0010o\u001a\u00020\u00122\u0006\u0010\u001b\u001a\u00020\fH\u0016J\u0006\u0010p\u001a\u00020\u001dJ\u000e\u0010p\u001a\u00020\u001d2\u0006\u0010\u001b\u001a\u00020/J\b\u0010q\u001a\u00020rH\u0016J\b\u0010s\u001a\u00020\u001fH\u0016J\u0015\u0010t\u001a\u00020\n2\u0006\u0010u\u001a\u00020/H\u0000\u00a2\u0006\u0002\bvJ\u0010\u0010w\u001a\u00020/2\u0006\u0010x\u001a\u00020FH\u0016J\u0010\u0010w\u001a\u00020\u00002\u0006\u0010x\u001a\u00020GH\u0016J \u0010w\u001a\u00020\u00002\u0006\u0010x\u001a\u00020G2\u0006\u0010\u001a\u001a\u00020/2\u0006\u0010\u001b\u001a\u00020/H\u0016J\u0018\u0010w\u001a\u00020\u00122\u0006\u0010x\u001a\u00020\u00002\u0006\u0010\u001b\u001a\u00020\fH\u0016J\u0010\u0010w\u001a\u00020\u00002\u0006\u0010y\u001a\u00020\u001dH\u0016J \u0010w\u001a\u00020\u00002\u0006\u0010y\u001a\u00020\u001d2\u0006\u0010\u001a\u001a\u00020/2\u0006\u0010\u001b\u001a\u00020/H\u0016J\u0018\u0010w\u001a\u00020\u00002\u0006\u0010x\u001a\u00020z2\u0006\u0010\u001b\u001a\u00020\fH\u0016J\u0010\u0010{\u001a\u00020\f2\u0006\u0010x\u001a\u00020zH\u0016J\u0010\u0010|\u001a\u00020\u00002\u0006\u00106\u001a\u00020/H\u0016J\u0010\u0010}\u001a\u00020\u00002\u0006\u0010~\u001a\u00020\fH\u0016J\u0010\u0010\u007f\u001a\u00020\u00002\u0006\u0010~\u001a\u00020\fH\u0016J\u0012\u0010\u0080\u0001\u001a\u00020\u00002\u0007\u0010\u0081\u0001\u001a\u00020/H\u0016J\u0012\u0010\u0082\u0001\u001a\u00020\u00002\u0007\u0010\u0081\u0001\u001a\u00020/H\u0016J\u0011\u0010\u0083\u0001\u001a\u00020\u00002\u0006\u0010~\u001a\u00020\fH\u0016J\u0011\u0010\u0084\u0001\u001a\u00020\u00002\u0006\u0010~\u001a\u00020\fH\u0016J\u0012\u0010\u0085\u0001\u001a\u00020\u00002\u0007\u0010\u0086\u0001\u001a\u00020/H\u0016J\u0012\u0010\u0087\u0001\u001a\u00020\u00002\u0007\u0010\u0086\u0001\u001a\u00020/H\u0016J\u001a\u0010\u0088\u0001\u001a\u00020\u00002\u0007\u0010\u0089\u0001\u001a\u00020\u001f2\u0006\u0010^\u001a\u00020_H\u0016J,\u0010\u0088\u0001\u001a\u00020\u00002\u0007\u0010\u0089\u0001\u001a\u00020\u001f2\u0007\u0010\u008a\u0001\u001a\u00020/2\u0007\u0010\u008b\u0001\u001a\u00020/2\u0006\u0010^\u001a\u00020_H\u0016J\u001b\u0010\u008c\u0001\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001b\u001a\u00020\fH\u0007J\u0012\u0010\u008d\u0001\u001a\u00020\u00002\u0007\u0010\u0089\u0001\u001a\u00020\u001fH\u0016J$\u0010\u008d\u0001\u001a\u00020\u00002\u0007\u0010\u0089\u0001\u001a\u00020\u001f2\u0007\u0010\u008a\u0001\u001a\u00020/2\u0007\u0010\u008b\u0001\u001a\u00020/H\u0016J\u0012\u0010\u008e\u0001\u001a\u00020\u00002\u0007\u0010\u008f\u0001\u001a\u00020/H\u0016R\u0014\u0010\u0006\u001a\u00020\u00008VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u0004\u0018\u00010\n8\u0000@\u0000X\u0081\u000e\u00a2\u0006\u0002\n\u0000R&\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\f8G@@X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010\u00a8\u0006\u0091\u0001"}, d2={"Lokio/Buffer;", "Lokio/BufferedSource;", "Lokio/BufferedSink;", "", "Ljava/nio/channels/ByteChannel;", "()V", "buffer", "getBuffer", "()Lokio/Buffer;", "head", "Lokio/Segment;", "<set-?>", "", "size", "()J", "setSize$okio", "(J)V", "clear", "", "clone", "close", "completeSegmentByteCount", "copy", "copyTo", "out", "Ljava/io/OutputStream;", "offset", "byteCount", "digest", "Lokio/ByteString;", "algorithm", "", "emit", "emitCompleteSegments", "equals", "", "other", "", "exhausted", "flush", "get", "", "pos", "getByte", "index", "-deprecated_getByte", "hashCode", "", "hmac", "key", "hmacSha1", "hmacSha256", "hmacSha512", "indexOf", "b", "fromIndex", "toIndex", "bytes", "indexOfElement", "targetBytes", "inputStream", "Ljava/io/InputStream;", "isOpen", "md5", "outputStream", "peek", "rangeEquals", "bytesOffset", "read", "sink", "Ljava/nio/ByteBuffer;", "", "readAll", "Lokio/Sink;", "readAndWriteUnsafe", "Lokio/Buffer$UnsafeCursor;", "unsafeCursor", "readByte", "readByteArray", "readByteString", "readDecimalLong", "readFrom", "input", "forever", "readFully", "readHexadecimalUnsignedLong", "readInt", "readIntLe", "readLong", "readLongLe", "readShort", "", "readShortLe", "readString", "charset", "Ljava/nio/charset/Charset;", "readUnsafe", "readUtf8", "readUtf8CodePoint", "readUtf8Line", "readUtf8LineStrict", "limit", "request", "require", "select", "options", "Lokio/Options;", "sha1", "sha256", "sha512", "-deprecated_size", "skip", "snapshot", "timeout", "Lokio/Timeout;", "toString", "writableSegment", "minimumCapacity", "writableSegment$okio", "write", "source", "byteString", "Lokio/Source;", "writeAll", "writeByte", "writeDecimalLong", "v", "writeHexadecimalUnsignedLong", "writeInt", "i", "writeIntLe", "writeLong", "writeLongLe", "writeShort", "s", "writeShortLe", "writeString", "string", "beginIndex", "endIndex", "writeTo", "writeUtf8", "writeUtf8CodePoint", "codePoint", "UnsafeCursor", "okio"})
public final class Buffer
implements BufferedSource,
BufferedSink,
Cloneable,
ByteChannel {
    @JvmField
    @Nullable
    public Segment head;
    private long size;

    @JvmName(name="size")
    public final long size() {
        return this.size;
    }

    public final void setSize$okio(long l) {
        this.size = l;
    }

    @Override
    @NotNull
    public Buffer buffer() {
        return this;
    }

    @Override
    @NotNull
    public Buffer getBuffer() {
        return this;
    }

    @Override
    @NotNull
    public OutputStream outputStream() {
        return new OutputStream(this){
            final /* synthetic */ Buffer this$0;

            public void write(int b) {
                this.this$0.writeByte(b);
            }

            public void write(@NotNull byte[] data, int offset, int byteCount) {
                Intrinsics.checkNotNullParameter(data, "data");
                this.this$0.write(data, offset, byteCount);
            }

            public void flush() {
            }

            public void close() {
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
    @NotNull
    public Buffer emitCompleteSegments() {
        return this;
    }

    @Override
    @NotNull
    public Buffer emit() {
        return this;
    }

    @Override
    public boolean exhausted() {
        return this.size == 0L;
    }

    @Override
    public void require(long byteCount) throws EOFException {
        if (this.size < byteCount) {
            throw (Throwable)new EOFException();
        }
    }

    @Override
    public boolean request(long byteCount) {
        return this.size >= byteCount;
    }

    @Override
    @NotNull
    public BufferedSource peek() {
        return Okio.buffer(new PeekSource(this));
    }

    @Override
    @NotNull
    public InputStream inputStream() {
        return new InputStream(this){
            final /* synthetic */ Buffer this$0;

            /*
             * WARNING - void declaration
             */
            public int read() {
                int n;
                if (this.this$0.size() > 0L) {
                    void $this$and$iv;
                    byte by = this.this$0.readByte();
                    int other$iv = 255;
                    boolean $i$f$and = false;
                    n = $this$and$iv & other$iv;
                } else {
                    n = -1;
                }
                return n;
            }

            public int read(@NotNull byte[] sink2, int offset, int byteCount) {
                Intrinsics.checkNotNullParameter(sink2, "sink");
                return this.this$0.read(sink2, offset, byteCount);
            }

            /*
             * WARNING - void declaration
             */
            public int available() {
                void a$iv;
                long l = this.this$0.size();
                int b$iv = Integer.MAX_VALUE;
                boolean $i$f$minOf = false;
                long l2 = b$iv;
                boolean bl = false;
                return (int)Math.min((long)a$iv, l2);
            }

            public void close() {
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

    @JvmOverloads
    @NotNull
    public final Buffer copyTo(@NotNull OutputStream out, long offset, long byteCount) throws IOException {
        Intrinsics.checkNotNullParameter(out, "out");
        long offset2 = offset;
        long byteCount2 = byteCount;
        -Util.checkOffsetAndCount(this.size, offset2, byteCount2);
        if (byteCount2 == 0L) {
            return this;
        }
        Segment s2 = this.head;
        while (true) {
            Segment segment = s2;
            Intrinsics.checkNotNull(segment);
            if (offset2 < (long)(segment.limit - s2.pos)) break;
            offset2 -= (long)(s2.limit - s2.pos);
            s2 = s2.next;
        }
        while (byteCount2 > 0L) {
            Segment segment = s2;
            Intrinsics.checkNotNull(segment);
            int pos = (int)((long)segment.pos + offset2);
            int a$iv = s2.limit - pos;
            boolean $i$f$minOf = false;
            long l = a$iv;
            boolean bl = false;
            int toCopy = (int)Math.min(l, byteCount2);
            out.write(s2.data, pos, toCopy);
            byteCount2 -= (long)toCopy;
            offset2 = 0L;
            s2 = s2.next;
        }
        return this;
    }

    public static /* synthetic */ Buffer copyTo$default(Buffer buffer, OutputStream outputStream2, long l, long l2, int n, Object object) throws IOException {
        if ((n & 2) != 0) {
            l = 0L;
        }
        if ((n & 4) != 0) {
            l2 = buffer.size - l;
        }
        return buffer.copyTo(outputStream2, l, l2);
    }

    @JvmOverloads
    @NotNull
    public final Buffer copyTo(@NotNull OutputStream out, long offset) throws IOException {
        return Buffer.copyTo$default(this, out, offset, 0L, 4, null);
    }

    @JvmOverloads
    @NotNull
    public final Buffer copyTo(@NotNull OutputStream out) throws IOException {
        return Buffer.copyTo$default(this, out, 0L, 0L, 6, null);
    }

    @NotNull
    public final Buffer copyTo(@NotNull Buffer out, long offset, long byteCount) {
        Buffer buffer;
        Intrinsics.checkNotNullParameter(out, "out");
        Buffer $this$commonCopyTo$iv = this;
        boolean $i$f$commonCopyTo = false;
        long offset$iv = offset;
        long byteCount$iv = byteCount;
        -Util.checkOffsetAndCount($this$commonCopyTo$iv.size(), offset$iv, byteCount$iv);
        if (byteCount$iv == 0L) {
            buffer = $this$commonCopyTo$iv;
        } else {
            Buffer buffer2 = out;
            buffer2.setSize$okio(buffer2.size() + byteCount$iv);
            Segment s$iv = $this$commonCopyTo$iv.head;
            while (true) {
                Segment segment = s$iv;
                Intrinsics.checkNotNull(segment);
                if (offset$iv < (long)(segment.limit - s$iv.pos)) break;
                offset$iv -= (long)(s$iv.limit - s$iv.pos);
                s$iv = s$iv.next;
            }
            while (byteCount$iv > 0L) {
                Segment segment = s$iv;
                Intrinsics.checkNotNull(segment);
                Segment copy$iv = segment.sharedCopy();
                copy$iv.pos += (int)offset$iv;
                int n = copy$iv.pos + (int)byteCount$iv;
                int n2 = copy$iv.limit;
                boolean bl = false;
                copy$iv.limit = Math.min(n, n2);
                if (out.head == null) {
                    out.head = copy$iv.next = (copy$iv.prev = copy$iv);
                } else {
                    Segment segment2 = out.head;
                    Intrinsics.checkNotNull(segment2);
                    Segment segment3 = segment2.prev;
                    Intrinsics.checkNotNull(segment3);
                    segment3.push(copy$iv);
                }
                byteCount$iv -= (long)(copy$iv.limit - copy$iv.pos);
                offset$iv = 0L;
                s$iv = s$iv.next;
            }
            buffer = $this$commonCopyTo$iv;
        }
        return buffer;
    }

    public static /* synthetic */ Buffer copyTo$default(Buffer buffer, Buffer buffer2, long l, long l2, int n, Object object) {
        if ((n & 2) != 0) {
            l = 0L;
        }
        return buffer.copyTo(buffer2, l, l2);
    }

    @NotNull
    public final Buffer copyTo(@NotNull Buffer out, long offset) {
        Intrinsics.checkNotNullParameter(out, "out");
        return this.copyTo(out, offset, this.size - offset);
    }

    public static /* synthetic */ Buffer copyTo$default(Buffer buffer, Buffer buffer2, long l, int n, Object object) {
        if ((n & 2) != 0) {
            l = 0L;
        }
        return buffer.copyTo(buffer2, l);
    }

    @JvmOverloads
    @NotNull
    public final Buffer writeTo(@NotNull OutputStream out, long byteCount) throws IOException {
        int toCopy;
        long byteCount2;
        Intrinsics.checkNotNullParameter(out, "out");
        -Util.checkOffsetAndCount(this.size, 0L, byteCount2);
        Segment s2 = this.head;
        for (byteCount2 = byteCount; byteCount2 > 0L; byteCount2 -= (long)toCopy) {
            Segment segment = s2;
            Intrinsics.checkNotNull(segment);
            int b$iv = segment.limit - s2.pos;
            boolean $i$f$minOf = false;
            long l = b$iv;
            boolean bl = false;
            toCopy = (int)Math.min(byteCount2, l);
            out.write(s2.data, s2.pos, toCopy);
            s2.pos += toCopy;
            this.size -= (long)toCopy;
            if (s2.pos != s2.limit) continue;
            Segment toRecycle = s2;
            this.head = s2 = toRecycle.pop();
            SegmentPool.recycle(toRecycle);
        }
        return this;
    }

    public static /* synthetic */ Buffer writeTo$default(Buffer buffer, OutputStream outputStream2, long l, int n, Object object) throws IOException {
        if ((n & 2) != 0) {
            l = buffer.size;
        }
        return buffer.writeTo(outputStream2, l);
    }

    @JvmOverloads
    @NotNull
    public final Buffer writeTo(@NotNull OutputStream out) throws IOException {
        return Buffer.writeTo$default(this, out, 0L, 2, null);
    }

    @NotNull
    public final Buffer readFrom(@NotNull InputStream input) throws IOException {
        Intrinsics.checkNotNullParameter(input, "input");
        this.readFrom(input, Long.MAX_VALUE, true);
        return this;
    }

    @NotNull
    public final Buffer readFrom(@NotNull InputStream input, long byteCount) throws IOException {
        Intrinsics.checkNotNullParameter(input, "input");
        boolean bl = byteCount >= 0L;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "byteCount < 0: " + byteCount;
            throw (Throwable)new IllegalArgumentException(string.toString());
        }
        this.readFrom(input, byteCount, false);
        return this;
    }

    private final void readFrom(InputStream input, long byteCount, boolean forever) throws IOException {
        int bytesRead;
        for (long byteCount2 = byteCount; byteCount2 > 0L || forever; byteCount2 -= (long)bytesRead) {
            Segment tail = this.writableSegment$okio(1);
            int b$iv = 8192 - tail.limit;
            boolean $i$f$minOf = false;
            long l = b$iv;
            boolean bl = false;
            int maxToCopy = (int)Math.min(byteCount2, l);
            bytesRead = input.read(tail.data, tail.limit, maxToCopy);
            if (bytesRead == -1) {
                if (tail.pos == tail.limit) {
                    this.head = tail.pop();
                    SegmentPool.recycle(tail);
                }
                if (forever) {
                    return;
                }
                throw (Throwable)new EOFException();
            }
            tail.limit += bytesRead;
            this.size += (long)bytesRead;
        }
    }

    /*
     * WARNING - void declaration
     */
    public final long completeSegmentByteCount() {
        long l;
        Buffer $this$commonCompleteSegmentByteCount$iv = this;
        boolean $i$f$commonCompleteSegmentByteCount = false;
        long result$iv = $this$commonCompleteSegmentByteCount$iv.size();
        if (result$iv == 0L) {
            l = 0L;
        } else {
            void var3_3;
            Segment segment = $this$commonCompleteSegmentByteCount$iv.head;
            Intrinsics.checkNotNull(segment);
            Segment segment2 = segment.prev;
            Intrinsics.checkNotNull(segment2);
            Segment tail$iv = segment2;
            if (tail$iv.limit < 8192 && tail$iv.owner) {
                result$iv -= (long)(tail$iv.limit - tail$iv.pos);
            }
            l = var3_3;
        }
        return l;
    }

    @Override
    public byte readByte() throws EOFException {
        Buffer $this$commonReadByte$iv = this;
        boolean $i$f$commonReadByte = false;
        if ($this$commonReadByte$iv.size() == 0L) {
            throw (Throwable)new EOFException();
        }
        Segment segment = $this$commonReadByte$iv.head;
        Intrinsics.checkNotNull(segment);
        Segment segment$iv = segment;
        int pos$iv = segment$iv.pos;
        int limit$iv = segment$iv.limit;
        byte[] data$iv = segment$iv.data;
        byte b$iv = data$iv[pos$iv++];
        Buffer buffer = $this$commonReadByte$iv;
        buffer.setSize$okio(buffer.size() - 1L);
        if (pos$iv == limit$iv) {
            $this$commonReadByte$iv.head = segment$iv.pop();
            SegmentPool.recycle(segment$iv);
        } else {
            segment$iv.pos = pos$iv;
        }
        return b$iv;
    }

    /*
     * WARNING - void declaration
     */
    @JvmName(name="getByte")
    public final byte getByte(long pos) {
        byte by;
        Buffer $this$commonGet$iv = this;
        boolean $i$f$commonGet = false;
        -Util.checkOffsetAndCount($this$commonGet$iv.size(), pos, 1L);
        Buffer $this$seek$iv$iv = $this$commonGet$iv;
        boolean $i$f$seek = false;
        Segment segment = $this$seek$iv$iv.head;
        if (segment == null) {
            void offset$iv;
            long l = -1L;
            Segment s$iv = null;
            boolean bl = false;
            Segment segment2 = s$iv;
            Intrinsics.checkNotNull(segment2);
            by = segment2.data[(int)((long)s$iv.pos + pos - offset$iv)];
        } else {
            Segment s$iv$iv = segment;
            if ($this$seek$iv$iv.size() - pos < pos) {
                void offset$iv;
                long offset$iv$iv;
                for (offset$iv$iv = $this$seek$iv$iv.size(); offset$iv$iv > pos; offset$iv$iv -= (long)(s$iv$iv.limit - s$iv$iv.pos)) {
                    Intrinsics.checkNotNull(s$iv$iv.prev);
                }
                long l = offset$iv$iv;
                Segment s$iv = s$iv$iv;
                boolean bl = false;
                Segment segment3 = s$iv;
                Intrinsics.checkNotNull(segment3);
                by = segment3.data[(int)((long)s$iv.pos + pos - offset$iv)];
            } else {
                void offset$iv;
                long nextOffset$iv$iv;
                long offset$iv$iv = 0L;
                while ((nextOffset$iv$iv = offset$iv$iv + (long)(s$iv$iv.limit - s$iv$iv.pos)) <= pos) {
                    Intrinsics.checkNotNull(s$iv$iv.next);
                    offset$iv$iv = nextOffset$iv$iv;
                }
                long l = offset$iv$iv;
                Segment s$iv = s$iv$iv;
                boolean bl = false;
                Segment segment4 = s$iv;
                Intrinsics.checkNotNull(segment4);
                by = segment4.data[(int)((long)s$iv.pos + pos - offset$iv)];
            }
        }
        return by;
    }

    @Override
    public short readShort() throws EOFException {
        short s2;
        Buffer $this$commonReadShort$iv = this;
        boolean $i$f$commonReadShort = false;
        if ($this$commonReadShort$iv.size() < 2L) {
            throw (Throwable)new EOFException();
        }
        Segment segment = $this$commonReadShort$iv.head;
        Intrinsics.checkNotNull(segment);
        Segment segment$iv = segment;
        int pos$iv = segment$iv.pos;
        int limit$iv = segment$iv.limit;
        if (limit$iv - pos$iv < 2) {
            byte $this$and$iv$iv;
            byte by = $this$commonReadShort$iv.readByte();
            int other$iv$iv = 255;
            boolean $i$f$and = false;
            int n = ($this$and$iv$iv & other$iv$iv) << 8;
            $this$and$iv$iv = $this$commonReadShort$iv.readByte();
            other$iv$iv = 255;
            $i$f$and = false;
            int s$iv = n | $this$and$iv$iv & other$iv$iv;
            s2 = (short)s$iv;
        } else {
            byte $this$and$iv$iv;
            byte[] data$iv = segment$iv.data;
            byte other$iv$iv = data$iv[pos$iv++];
            int other$iv$iv2 = 255;
            boolean $i$f$and = false;
            int n = ($this$and$iv$iv & other$iv$iv2) << 8;
            $this$and$iv$iv = data$iv[pos$iv++];
            other$iv$iv2 = 255;
            $i$f$and = false;
            int s$iv = n | $this$and$iv$iv & other$iv$iv2;
            Buffer buffer = $this$commonReadShort$iv;
            buffer.setSize$okio(buffer.size() - 2L);
            if (pos$iv == limit$iv) {
                $this$commonReadShort$iv.head = segment$iv.pop();
                SegmentPool.recycle(segment$iv);
            } else {
                segment$iv.pos = pos$iv;
            }
            s2 = (short)s$iv;
        }
        return s2;
    }

    @Override
    public int readInt() throws EOFException {
        int n;
        Buffer $this$commonReadInt$iv = this;
        boolean $i$f$commonReadInt = false;
        if ($this$commonReadInt$iv.size() < 4L) {
            throw (Throwable)new EOFException();
        }
        Segment segment = $this$commonReadInt$iv.head;
        Intrinsics.checkNotNull(segment);
        Segment segment$iv = segment;
        int pos$iv = segment$iv.pos;
        int limit$iv = segment$iv.limit;
        if ((long)(limit$iv - pos$iv) < 4L) {
            byte $this$and$iv$iv;
            byte by = $this$commonReadInt$iv.readByte();
            int other$iv$iv = 255;
            boolean $i$f$and = false;
            int n2 = ($this$and$iv$iv & other$iv$iv) << 24;
            $this$and$iv$iv = $this$commonReadInt$iv.readByte();
            other$iv$iv = 255;
            $i$f$and = false;
            int n3 = n2 | ($this$and$iv$iv & other$iv$iv) << 16;
            $this$and$iv$iv = $this$commonReadInt$iv.readByte();
            other$iv$iv = 255;
            $i$f$and = false;
            int n4 = n3 | ($this$and$iv$iv & other$iv$iv) << 8;
            $this$and$iv$iv = $this$commonReadInt$iv.readByte();
            other$iv$iv = 255;
            $i$f$and = false;
            n = n4 | $this$and$iv$iv & other$iv$iv;
        } else {
            byte $this$and$iv$iv;
            byte[] data$iv = segment$iv.data;
            byte $i$f$and = data$iv[pos$iv++];
            int other$iv$iv = 255;
            boolean $i$f$and2 = false;
            int n5 = ($this$and$iv$iv & other$iv$iv) << 24;
            $this$and$iv$iv = data$iv[pos$iv++];
            other$iv$iv = 255;
            $i$f$and2 = false;
            int n6 = n5 | ($this$and$iv$iv & other$iv$iv) << 16;
            $this$and$iv$iv = data$iv[pos$iv++];
            other$iv$iv = 255;
            $i$f$and2 = false;
            int n7 = n6 | ($this$and$iv$iv & other$iv$iv) << 8;
            $this$and$iv$iv = data$iv[pos$iv++];
            other$iv$iv = 255;
            $i$f$and2 = false;
            int i$iv = n7 | $this$and$iv$iv & other$iv$iv;
            Buffer buffer = $this$commonReadInt$iv;
            buffer.setSize$okio(buffer.size() - 4L);
            if (pos$iv == limit$iv) {
                $this$commonReadInt$iv.head = segment$iv.pop();
                SegmentPool.recycle(segment$iv);
            } else {
                segment$iv.pos = pos$iv;
            }
            n = i$iv;
        }
        return n;
    }

    @Override
    public long readLong() throws EOFException {
        long l;
        Buffer $this$commonReadLong$iv = this;
        boolean $i$f$commonReadLong = false;
        if ($this$commonReadLong$iv.size() < 8L) {
            throw (Throwable)new EOFException();
        }
        Segment segment = $this$commonReadLong$iv.head;
        Intrinsics.checkNotNull(segment);
        Segment segment$iv = segment;
        int pos$iv = segment$iv.pos;
        int limit$iv = segment$iv.limit;
        if ((long)(limit$iv - pos$iv) < 8L) {
            int $this$and$iv$iv;
            int n = $this$commonReadLong$iv.readInt();
            long other$iv$iv = 0xFFFFFFFFL;
            boolean $i$f$and = false;
            long l2 = ((long)$this$and$iv$iv & other$iv$iv) << 32;
            $this$and$iv$iv = $this$commonReadLong$iv.readInt();
            other$iv$iv = 0xFFFFFFFFL;
            $i$f$and = false;
            l = l2 | (long)$this$and$iv$iv & other$iv$iv;
        } else {
            byte $this$and$iv$iv;
            byte[] data$iv = segment$iv.data;
            byte $i$f$and = data$iv[pos$iv++];
            long other$iv$iv = 255L;
            boolean $i$f$and2 = false;
            long l3 = ((long)$this$and$iv$iv & other$iv$iv) << 56;
            $this$and$iv$iv = data$iv[pos$iv++];
            other$iv$iv = 255L;
            $i$f$and2 = false;
            long l4 = l3 | ((long)$this$and$iv$iv & other$iv$iv) << 48;
            $this$and$iv$iv = data$iv[pos$iv++];
            other$iv$iv = 255L;
            $i$f$and2 = false;
            long l5 = l4 | ((long)$this$and$iv$iv & other$iv$iv) << 40;
            $this$and$iv$iv = data$iv[pos$iv++];
            other$iv$iv = 255L;
            $i$f$and2 = false;
            long l6 = l5 | ((long)$this$and$iv$iv & other$iv$iv) << 32;
            $this$and$iv$iv = data$iv[pos$iv++];
            other$iv$iv = 255L;
            $i$f$and2 = false;
            long l7 = l6 | ((long)$this$and$iv$iv & other$iv$iv) << 24;
            $this$and$iv$iv = data$iv[pos$iv++];
            other$iv$iv = 255L;
            $i$f$and2 = false;
            long l8 = l7 | ((long)$this$and$iv$iv & other$iv$iv) << 16;
            $this$and$iv$iv = data$iv[pos$iv++];
            other$iv$iv = 255L;
            $i$f$and2 = false;
            long l9 = l8 | ((long)$this$and$iv$iv & other$iv$iv) << 8;
            $this$and$iv$iv = data$iv[pos$iv++];
            other$iv$iv = 255L;
            $i$f$and2 = false;
            long v$iv = l9 | (long)$this$and$iv$iv & other$iv$iv;
            Buffer buffer = $this$commonReadLong$iv;
            buffer.setSize$okio(buffer.size() - 8L);
            if (pos$iv == limit$iv) {
                $this$commonReadLong$iv.head = segment$iv.pop();
                SegmentPool.recycle(segment$iv);
            } else {
                segment$iv.pos = pos$iv;
            }
            l = v$iv;
        }
        return l;
    }

    @Override
    public short readShortLe() throws EOFException {
        return -Util.reverseBytes(this.readShort());
    }

    @Override
    public int readIntLe() throws EOFException {
        return -Util.reverseBytes(this.readInt());
    }

    @Override
    public long readLongLe() throws EOFException {
        return -Util.reverseBytes(this.readLong());
    }

    @Override
    public long readDecimalLong() throws EOFException {
        Buffer $this$commonReadDecimalLong$iv = this;
        boolean $i$f$commonReadDecimalLong = false;
        if ($this$commonReadDecimalLong$iv.size() == 0L) {
            throw (Throwable)new EOFException();
        }
        long value$iv = 0L;
        int seen$iv = 0;
        boolean negative$iv = false;
        boolean done$iv = false;
        long overflowDigit$iv = -7L;
        do {
            Segment segment$iv;
            Intrinsics.checkNotNull($this$commonReadDecimalLong$iv.head);
            byte[] data$iv = segment$iv.data;
            int pos$iv = segment$iv.pos;
            int limit$iv = segment$iv.limit;
            while (pos$iv < limit$iv) {
                byte b$iv = data$iv[pos$iv];
                if (b$iv >= (byte)48 && b$iv <= (byte)57) {
                    int digit$iv = (byte)48 - b$iv;
                    if (value$iv < -922337203685477580L || value$iv == -922337203685477580L && (long)digit$iv < overflowDigit$iv) {
                        Buffer buffer$iv = new Buffer().writeDecimalLong(value$iv).writeByte(b$iv);
                        if (!negative$iv) {
                            buffer$iv.readByte();
                        }
                        throw (Throwable)new NumberFormatException("Number too large: " + buffer$iv.readUtf8());
                    }
                    value$iv *= 10L;
                    value$iv += (long)digit$iv;
                } else if (b$iv == (byte)45 && seen$iv == 0) {
                    negative$iv = true;
                    --overflowDigit$iv;
                } else {
                    if (seen$iv == 0) {
                        throw (Throwable)new NumberFormatException("Expected leading [0-9] or '-' character but was 0x" + -Util.toHexString(b$iv));
                    }
                    done$iv = true;
                    break;
                }
                ++pos$iv;
                ++seen$iv;
            }
            if (pos$iv == limit$iv) {
                $this$commonReadDecimalLong$iv.head = segment$iv.pop();
                SegmentPool.recycle(segment$iv);
                continue;
            }
            segment$iv.pos = pos$iv;
        } while (!done$iv && $this$commonReadDecimalLong$iv.head != null);
        Buffer buffer = $this$commonReadDecimalLong$iv;
        buffer.setSize$okio(buffer.size() - (long)seen$iv);
        return negative$iv ? value$iv : -value$iv;
    }

    /*
     * WARNING - void declaration
     */
    @Override
    public long readHexadecimalUnsignedLong() throws EOFException {
        void var3_3;
        Buffer $this$commonReadHexadecimalUnsignedLong$iv = this;
        boolean $i$f$commonReadHexadecimalUnsignedLong = false;
        if ($this$commonReadHexadecimalUnsignedLong$iv.size() == 0L) {
            throw (Throwable)new EOFException();
        }
        long value$iv = 0L;
        int seen$iv = 0;
        boolean done$iv = false;
        do {
            Segment segment$iv;
            Intrinsics.checkNotNull($this$commonReadHexadecimalUnsignedLong$iv.head);
            byte[] data$iv = segment$iv.data;
            int pos$iv = segment$iv.pos;
            int limit$iv = segment$iv.limit;
            while (pos$iv < limit$iv) {
                int digit$iv = 0;
                byte b$iv = data$iv[pos$iv];
                if (b$iv >= (byte)48 && b$iv <= (byte)57) {
                    digit$iv = b$iv - (byte)48;
                } else if (b$iv >= (byte)97 && b$iv <= (byte)102) {
                    digit$iv = b$iv - (byte)97 + 10;
                } else if (b$iv >= (byte)65 && b$iv <= (byte)70) {
                    digit$iv = b$iv - (byte)65 + 10;
                } else {
                    if (seen$iv == 0) {
                        throw (Throwable)new NumberFormatException("Expected leading [0-9a-fA-F] character but was 0x" + -Util.toHexString(b$iv));
                    }
                    done$iv = true;
                    break;
                }
                if ((value$iv & 0xF000000000000000L) != 0L) {
                    Buffer buffer$iv = new Buffer().writeHexadecimalUnsignedLong(value$iv).writeByte(b$iv);
                    throw (Throwable)new NumberFormatException("Number too large: " + buffer$iv.readUtf8());
                }
                value$iv <<= 4;
                value$iv |= (long)digit$iv;
                ++pos$iv;
                ++seen$iv;
            }
            if (pos$iv == limit$iv) {
                $this$commonReadHexadecimalUnsignedLong$iv.head = segment$iv.pop();
                SegmentPool.recycle(segment$iv);
                continue;
            }
            segment$iv.pos = pos$iv;
        } while (!done$iv && $this$commonReadHexadecimalUnsignedLong$iv.head != null);
        Buffer buffer = $this$commonReadHexadecimalUnsignedLong$iv;
        buffer.setSize$okio(buffer.size() - (long)seen$iv);
        return (long)var3_3;
    }

    @Override
    @NotNull
    public ByteString readByteString() {
        Buffer $this$commonReadByteString$iv = this;
        boolean $i$f$commonReadByteString = false;
        return $this$commonReadByteString$iv.readByteString($this$commonReadByteString$iv.size());
    }

    @Override
    @NotNull
    public ByteString readByteString(long byteCount) throws EOFException {
        ByteString byteString;
        Buffer $this$commonReadByteString$iv = this;
        boolean $i$f$commonReadByteString = false;
        boolean bl = byteCount >= 0L && byteCount <= (long)Integer.MAX_VALUE;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "byteCount: " + byteCount;
            throw (Throwable)new IllegalArgumentException(string.toString());
        }
        if ($this$commonReadByteString$iv.size() < byteCount) {
            throw (Throwable)new EOFException();
        }
        if (byteCount >= (long)4096) {
            ByteString byteString2 = $this$commonReadByteString$iv.snapshot((int)byteCount);
            bl2 = false;
            bl3 = false;
            ByteString it$iv = byteString2;
            boolean bl5 = false;
            $this$commonReadByteString$iv.skip(byteCount);
            byteString = byteString2;
        } else {
            byteString = new ByteString($this$commonReadByteString$iv.readByteArray(byteCount));
        }
        return byteString;
    }

    @Override
    public int select(@NotNull Options options) {
        int n;
        Intrinsics.checkNotNullParameter(options, "options");
        Buffer $this$commonSelect$iv = this;
        boolean $i$f$commonSelect = false;
        int index$iv = BufferKt.selectPrefix$default($this$commonSelect$iv, options, false, 2, null);
        if (index$iv == -1) {
            n = -1;
        } else {
            int selectedSize$iv = options.getByteStrings$okio()[index$iv].size();
            $this$commonSelect$iv.skip(selectedSize$iv);
            n = index$iv;
        }
        return n;
    }

    @Override
    public void readFully(@NotNull Buffer sink2, long byteCount) throws EOFException {
        Intrinsics.checkNotNullParameter(sink2, "sink");
        Buffer $this$commonReadFully$iv = this;
        boolean $i$f$commonReadFully = false;
        if ($this$commonReadFully$iv.size() < byteCount) {
            sink2.write($this$commonReadFully$iv, $this$commonReadFully$iv.size());
            throw (Throwable)new EOFException();
        }
        sink2.write($this$commonReadFully$iv, byteCount);
    }

    @Override
    public long readAll(@NotNull Sink sink2) throws IOException {
        Intrinsics.checkNotNullParameter(sink2, "sink");
        Buffer $this$commonReadAll$iv = this;
        boolean $i$f$commonReadAll = false;
        long byteCount$iv = $this$commonReadAll$iv.size();
        if (byteCount$iv > 0L) {
            sink2.write($this$commonReadAll$iv, byteCount$iv);
        }
        return byteCount$iv;
    }

    @Override
    @NotNull
    public String readUtf8() {
        return this.readString(this.size, Charsets.UTF_8);
    }

    @Override
    @NotNull
    public String readUtf8(long byteCount) throws EOFException {
        return this.readString(byteCount, Charsets.UTF_8);
    }

    @Override
    @NotNull
    public String readString(@NotNull Charset charset) {
        Intrinsics.checkNotNullParameter(charset, "charset");
        return this.readString(this.size, charset);
    }

    @Override
    @NotNull
    public String readString(long byteCount, @NotNull Charset charset) throws EOFException {
        Intrinsics.checkNotNullParameter(charset, "charset");
        boolean bl = byteCount >= 0L && byteCount <= (long)Integer.MAX_VALUE;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "byteCount: " + byteCount;
            throw (Throwable)new IllegalArgumentException(string.toString());
        }
        if (this.size < byteCount) {
            throw (Throwable)new EOFException();
        }
        if (byteCount == 0L) {
            return "";
        }
        Segment segment = this.head;
        Intrinsics.checkNotNull(segment);
        Segment s2 = segment;
        if ((long)s2.pos + byteCount > (long)s2.limit) {
            byte[] byArray = this.readByteArray(byteCount);
            bl3 = false;
            return new String(byArray, charset);
        }
        byte[] byArray = s2.data;
        int n = s2.pos;
        int n2 = (int)byteCount;
        boolean bl5 = false;
        String result = new String(byArray, n, n2, charset);
        s2.pos += (int)byteCount;
        this.size -= byteCount;
        if (s2.pos == s2.limit) {
            this.head = s2.pop();
            SegmentPool.recycle(s2);
        }
        return result;
    }

    @Override
    @Nullable
    public String readUtf8Line() throws EOFException {
        Buffer $this$commonReadUtf8Line$iv = this;
        boolean $i$f$commonReadUtf8Line = false;
        long newline$iv = $this$commonReadUtf8Line$iv.indexOf((byte)10);
        return newline$iv != -1L ? BufferKt.readUtf8Line($this$commonReadUtf8Line$iv, newline$iv) : ($this$commonReadUtf8Line$iv.size() != 0L ? $this$commonReadUtf8Line$iv.readUtf8($this$commonReadUtf8Line$iv.size()) : null);
    }

    @Override
    @NotNull
    public String readUtf8LineStrict() throws EOFException {
        return this.readUtf8LineStrict(Long.MAX_VALUE);
    }

    /*
     * WARNING - void declaration
     */
    @Override
    @NotNull
    public String readUtf8LineStrict(long limit) throws EOFException {
        String string;
        Buffer $this$commonReadUtf8LineStrict$iv = this;
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
            string = BufferKt.readUtf8Line($this$commonReadUtf8LineStrict$iv, newline$iv);
        } else if (scanLength$iv < $this$commonReadUtf8LineStrict$iv.size() && $this$commonReadUtf8LineStrict$iv.getByte(scanLength$iv - 1L) == (byte)13 && $this$commonReadUtf8LineStrict$iv.getByte(scanLength$iv) == (byte)10) {
            string = BufferKt.readUtf8Line($this$commonReadUtf8LineStrict$iv, scanLength$iv);
        } else {
            void a$iv$iv;
            Buffer data$iv = new Buffer();
            int n = 32;
            long b$iv$iv = $this$commonReadUtf8LineStrict$iv.size();
            boolean $i$f$minOf = false;
            long l = (long)a$iv$iv;
            boolean bl5 = false;
            $this$commonReadUtf8LineStrict$iv.copyTo(data$iv, 0L, Math.min(l, b$iv$iv));
            long l2 = $this$commonReadUtf8LineStrict$iv.size();
            boolean bl6 = false;
            throw (Throwable)new EOFException("\\n not found: limit=" + Math.min(l2, limit) + " content=" + data$iv.readByteString().hex() + '\u2026');
        }
        return string;
    }

    /*
     * WARNING - void declaration
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public int readUtf8CodePoint() throws EOFException {
        int $this$and$iv$iv;
        Buffer $this$commonReadUtf8CodePoint$iv = this;
        boolean $i$f$commonReadUtf8CodePoint = false;
        if ($this$commonReadUtf8CodePoint$iv.size() == 0L) {
            throw (Throwable)new EOFException();
        }
        int b0$iv = $this$commonReadUtf8CodePoint$iv.getByte(0L);
        int codePoint$iv = 0;
        int byteCount$iv = 0;
        int min$iv = 0;
        int n = b0$iv;
        int other$iv$iv = 128;
        boolean $i$f$and = false;
        if (($this$and$iv$iv & other$iv$iv) == 0) {
            $this$and$iv$iv = b0$iv;
            other$iv$iv = 127;
            $i$f$and = false;
            codePoint$iv = $this$and$iv$iv & other$iv$iv;
            byteCount$iv = 1;
            min$iv = 0;
        } else {
            $this$and$iv$iv = b0$iv;
            other$iv$iv = 224;
            $i$f$and = false;
            if (($this$and$iv$iv & other$iv$iv) == 192) {
                $this$and$iv$iv = b0$iv;
                other$iv$iv = 31;
                $i$f$and = false;
                codePoint$iv = $this$and$iv$iv & other$iv$iv;
                byteCount$iv = 2;
                min$iv = 128;
            } else {
                $this$and$iv$iv = b0$iv;
                other$iv$iv = 240;
                $i$f$and = false;
                if (($this$and$iv$iv & other$iv$iv) == 224) {
                    $this$and$iv$iv = b0$iv;
                    other$iv$iv = 15;
                    $i$f$and = false;
                    codePoint$iv = $this$and$iv$iv & other$iv$iv;
                    byteCount$iv = 3;
                    min$iv = 2048;
                } else {
                    $this$and$iv$iv = b0$iv;
                    other$iv$iv = 248;
                    $i$f$and = false;
                    if (($this$and$iv$iv & other$iv$iv) == 240) {
                        $this$and$iv$iv = b0$iv;
                        other$iv$iv = 7;
                        $i$f$and = false;
                        codePoint$iv = $this$and$iv$iv & other$iv$iv;
                        byteCount$iv = 4;
                        min$iv = 65536;
                    } else {
                        $this$commonReadUtf8CodePoint$iv.skip(1L);
                        return 65533;
                    }
                }
            }
        }
        if ($this$commonReadUtf8CodePoint$iv.size() < (long)byteCount$iv) {
            throw (Throwable)new EOFException("size < " + byteCount$iv + ": " + $this$commonReadUtf8CodePoint$iv.size() + " (to read code point prefixed 0x" + -Util.toHexString((byte)b0$iv) + ')');
        }
        $this$and$iv$iv = 1;
        int n2 = byteCount$iv;
        while ($this$and$iv$iv < n2) {
            byte $this$and$iv$iv2;
            void i$iv;
            byte b$iv;
            byte by = b$iv = $this$commonReadUtf8CodePoint$iv.getByte((long)i$iv);
            int other$iv$iv2 = 192;
            boolean $i$f$and2 = false;
            if (($this$and$iv$iv2 & other$iv$iv2) == 128) {
                codePoint$iv <<= 6;
                $this$and$iv$iv2 = b$iv;
                other$iv$iv2 = 63;
                $i$f$and2 = false;
                codePoint$iv |= $this$and$iv$iv2 & other$iv$iv2;
            } else {
                $this$commonReadUtf8CodePoint$iv.skip((long)i$iv);
                return 65533;
            }
            ++i$iv;
        }
        $this$commonReadUtf8CodePoint$iv.skip(byteCount$iv);
        if (codePoint$iv > 0x10FFFF) {
            return 65533;
        }
        n = codePoint$iv;
        if (55296 <= n && 57343 >= n) {
            return 65533;
        }
        if (codePoint$iv < min$iv) {
            return 65533;
        }
        int n3 = codePoint$iv;
        return n3;
    }

    @Override
    @NotNull
    public byte[] readByteArray() {
        Buffer $this$commonReadByteArray$iv = this;
        boolean $i$f$commonReadByteArray = false;
        return $this$commonReadByteArray$iv.readByteArray($this$commonReadByteArray$iv.size());
    }

    @Override
    @NotNull
    public byte[] readByteArray(long byteCount) throws EOFException {
        Buffer $this$commonReadByteArray$iv = this;
        boolean $i$f$commonReadByteArray = false;
        boolean bl = byteCount >= 0L && byteCount <= (long)Integer.MAX_VALUE;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "byteCount: " + byteCount;
            throw (Throwable)new IllegalArgumentException(string.toString());
        }
        if ($this$commonReadByteArray$iv.size() < byteCount) {
            throw (Throwable)new EOFException();
        }
        byte[] result$iv = new byte[(int)byteCount];
        $this$commonReadByteArray$iv.readFully(result$iv);
        return result$iv;
    }

    @Override
    public int read(@NotNull byte[] sink2) {
        Intrinsics.checkNotNullParameter(sink2, "sink");
        Buffer $this$commonRead$iv = this;
        boolean $i$f$commonRead = false;
        return $this$commonRead$iv.read(sink2, 0, sink2.length);
    }

    @Override
    public void readFully(@NotNull byte[] sink2) throws EOFException {
        int read$iv;
        Intrinsics.checkNotNullParameter(sink2, "sink");
        Buffer $this$commonReadFully$iv = this;
        boolean $i$f$commonReadFully = false;
        for (int offset$iv = 0; offset$iv < sink2.length; offset$iv += read$iv) {
            read$iv = $this$commonReadFully$iv.read(sink2, offset$iv, sink2.length - offset$iv);
            if (read$iv != -1) continue;
            throw (Throwable)new EOFException();
        }
    }

    @Override
    public int read(@NotNull byte[] sink2, int offset, int byteCount) {
        int n;
        Intrinsics.checkNotNullParameter(sink2, "sink");
        Buffer $this$commonRead$iv = this;
        boolean $i$f$commonRead = false;
        -Util.checkOffsetAndCount(sink2.length, offset, byteCount);
        Segment segment = $this$commonRead$iv.head;
        if (segment == null) {
            n = -1;
        } else {
            Segment s$iv = segment;
            int n2 = s$iv.limit - s$iv.pos;
            boolean bl = false;
            int toCopy$iv = Math.min(byteCount, n2);
            ArraysKt.copyInto(s$iv.data, sink2, offset, s$iv.pos, s$iv.pos + toCopy$iv);
            s$iv.pos += toCopy$iv;
            Buffer buffer = $this$commonRead$iv;
            buffer.setSize$okio(buffer.size() - (long)toCopy$iv);
            if (s$iv.pos == s$iv.limit) {
                $this$commonRead$iv.head = s$iv.pop();
                SegmentPool.recycle(s$iv);
            }
            n = toCopy$iv;
        }
        return n;
    }

    @Override
    public int read(@NotNull ByteBuffer sink2) throws IOException {
        Intrinsics.checkNotNullParameter(sink2, "sink");
        Segment segment = this.head;
        if (segment == null) {
            return -1;
        }
        Segment s2 = segment;
        int n = sink2.remaining();
        int n2 = s2.limit - s2.pos;
        boolean bl = false;
        int toCopy = Math.min(n, n2);
        sink2.put(s2.data, s2.pos, toCopy);
        s2.pos += toCopy;
        this.size -= (long)toCopy;
        if (s2.pos == s2.limit) {
            this.head = s2.pop();
            SegmentPool.recycle(s2);
        }
        return toCopy;
    }

    public final void clear() {
        Buffer $this$commonClear$iv = this;
        boolean $i$f$commonClear = false;
        $this$commonClear$iv.skip($this$commonClear$iv.size());
    }

    @Override
    public void skip(long byteCount) throws EOFException {
        Buffer $this$commonSkip$iv = this;
        boolean $i$f$commonSkip = false;
        long byteCount$iv = byteCount;
        while (byteCount$iv > 0L) {
            Segment head$iv;
            if ($this$commonSkip$iv.head == null) {
                throw (Throwable)new EOFException();
            }
            int b$iv$iv = head$iv.limit - head$iv.pos;
            boolean $i$f$minOf = false;
            long l = b$iv$iv;
            boolean bl = false;
            int toSkip$iv = (int)Math.min(byteCount$iv, l);
            Buffer buffer = $this$commonSkip$iv;
            buffer.setSize$okio(buffer.size() - (long)toSkip$iv);
            byteCount$iv -= (long)toSkip$iv;
            head$iv.pos += toSkip$iv;
            if (head$iv.pos != head$iv.limit) continue;
            $this$commonSkip$iv.head = head$iv.pop();
            SegmentPool.recycle(head$iv);
        }
    }

    /*
     * WARNING - void declaration
     */
    @Override
    @NotNull
    public Buffer write(@NotNull ByteString byteString) {
        void var2_2;
        Intrinsics.checkNotNullParameter(byteString, "byteString");
        Buffer $this$commonWrite$iv = this;
        int offset$iv = 0;
        int byteCount$iv = byteString.size();
        boolean $i$f$commonWrite = false;
        byteString.write$okio($this$commonWrite$iv, offset$iv, byteCount$iv);
        return var2_2;
    }

    @Override
    @NotNull
    public Buffer write(@NotNull ByteString byteString, int offset, int byteCount) {
        Intrinsics.checkNotNullParameter(byteString, "byteString");
        Buffer $this$commonWrite$iv = this;
        boolean $i$f$commonWrite = false;
        byteString.write$okio($this$commonWrite$iv, offset, byteCount);
        return $this$commonWrite$iv;
    }

    @Override
    @NotNull
    public Buffer writeUtf8(@NotNull String string) {
        Intrinsics.checkNotNullParameter(string, "string");
        return this.writeUtf8(string, 0, string.length());
    }

    @Override
    @NotNull
    public Buffer writeUtf8(@NotNull String string, int beginIndex, int endIndex) {
        Intrinsics.checkNotNullParameter(string, "string");
        Buffer $this$commonWriteUtf8$iv = this;
        boolean $i$f$commonWriteUtf8 = false;
        boolean bl = beginIndex >= 0;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string2 = "beginIndex < 0: " + beginIndex;
            throw (Throwable)new IllegalArgumentException(string2.toString());
        }
        bl = endIndex >= beginIndex;
        bl2 = false;
        bl3 = false;
        if (!bl) {
            boolean bl5 = false;
            String string3 = "endIndex < beginIndex: " + endIndex + " < " + beginIndex;
            throw (Throwable)new IllegalArgumentException(string3.toString());
        }
        bl = endIndex <= string.length();
        bl2 = false;
        bl3 = false;
        if (!bl) {
            boolean bl6 = false;
            String string4 = "endIndex > string.length: " + endIndex + " > " + string.length();
            throw (Throwable)new IllegalArgumentException(string4.toString());
        }
        int i$iv = beginIndex;
        while (i$iv < endIndex) {
            char data$iv2;
            char low$iv;
            char c$iv = string.charAt(i$iv);
            if (c$iv < '\u0080') {
                Segment tail$iv = $this$commonWriteUtf8$iv.writableSegment$okio(1);
                byte[] data$iv2 = tail$iv.data;
                int segmentOffset$iv = tail$iv.limit - i$iv;
                int n = 8192 - segmentOffset$iv;
                boolean bl7 = false;
                int runLimit$iv = Math.min(endIndex, n);
                data$iv2[segmentOffset$iv + i$iv++] = (byte)c$iv;
                while (i$iv < runLimit$iv && (c$iv = string.charAt(i$iv)) < '\u0080') {
                    data$iv2[segmentOffset$iv + i$iv++] = (byte)c$iv;
                }
                int runSize$iv = i$iv + segmentOffset$iv - tail$iv.limit;
                tail$iv.limit += runSize$iv;
                Buffer buffer = $this$commonWriteUtf8$iv;
                buffer.setSize$okio(buffer.size() + (long)runSize$iv);
                continue;
            }
            if (c$iv < '\u0800') {
                Segment tail$iv = $this$commonWriteUtf8$iv.writableSegment$okio(2);
                tail$iv.data[tail$iv.limit] = (byte)(c$iv >> 6 | 0xC0);
                tail$iv.data[tail$iv.limit + 1] = (byte)(c$iv & 0x3F | 0x80);
                tail$iv.limit += 2;
                Buffer buffer = $this$commonWriteUtf8$iv;
                buffer.setSize$okio(buffer.size() + 2L);
                ++i$iv;
                continue;
            }
            if (c$iv < '\ud800' || c$iv > '\udfff') {
                Segment tail$iv = $this$commonWriteUtf8$iv.writableSegment$okio(3);
                tail$iv.data[tail$iv.limit] = (byte)(c$iv >> 12 | 0xE0);
                tail$iv.data[tail$iv.limit + 1] = (byte)(c$iv >> 6 & 0x3F | 0x80);
                tail$iv.data[tail$iv.limit + 2] = (byte)(c$iv & 0x3F | 0x80);
                tail$iv.limit += 3;
                Buffer buffer = $this$commonWriteUtf8$iv;
                buffer.setSize$okio(buffer.size() + 3L);
                ++i$iv;
                continue;
            }
            char c = low$iv = i$iv + 1 < endIndex ? string.charAt(i$iv + 1) : (char)'\u0000';
            if (c$iv > '\udbff' || '\udc00' > (data$iv2 = low$iv) || '\udfff' < data$iv2) {
                $this$commonWriteUtf8$iv.writeByte(63);
                ++i$iv;
                continue;
            }
            int codePoint$iv = 65536 + ((c$iv & 0x3FF) << 10 | low$iv & 0x3FF);
            Segment tail$iv = $this$commonWriteUtf8$iv.writableSegment$okio(4);
            tail$iv.data[tail$iv.limit] = (byte)(codePoint$iv >> 18 | 0xF0);
            tail$iv.data[tail$iv.limit + 1] = (byte)(codePoint$iv >> 12 & 0x3F | 0x80);
            tail$iv.data[tail$iv.limit + 2] = (byte)(codePoint$iv >> 6 & 0x3F | 0x80);
            tail$iv.data[tail$iv.limit + 3] = (byte)(codePoint$iv & 0x3F | 0x80);
            tail$iv.limit += 4;
            Buffer buffer = $this$commonWriteUtf8$iv;
            buffer.setSize$okio(buffer.size() + 4L);
            i$iv += 2;
        }
        return $this$commonWriteUtf8$iv;
    }

    /*
     * WARNING - void declaration
     */
    @Override
    @NotNull
    public Buffer writeUtf8CodePoint(int codePoint) {
        void var2_2;
        Buffer $this$commonWriteUtf8CodePoint$iv = this;
        boolean $i$f$commonWriteUtf8CodePoint = false;
        if (codePoint < 128) {
            $this$commonWriteUtf8CodePoint$iv.writeByte(codePoint);
        } else if (codePoint < 2048) {
            Segment tail$iv = $this$commonWriteUtf8CodePoint$iv.writableSegment$okio(2);
            tail$iv.data[tail$iv.limit] = (byte)(codePoint >> 6 | 0xC0);
            tail$iv.data[tail$iv.limit + 1] = (byte)(codePoint & 0x3F | 0x80);
            tail$iv.limit += 2;
            Buffer buffer = $this$commonWriteUtf8CodePoint$iv;
            buffer.setSize$okio(buffer.size() + 2L);
        } else {
            int tail$iv = codePoint;
            if (55296 <= tail$iv && 57343 >= tail$iv) {
                $this$commonWriteUtf8CodePoint$iv.writeByte(63);
            } else if (codePoint < 65536) {
                Segment tail$iv2 = $this$commonWriteUtf8CodePoint$iv.writableSegment$okio(3);
                tail$iv2.data[tail$iv2.limit] = (byte)(codePoint >> 12 | 0xE0);
                tail$iv2.data[tail$iv2.limit + 1] = (byte)(codePoint >> 6 & 0x3F | 0x80);
                tail$iv2.data[tail$iv2.limit + 2] = (byte)(codePoint & 0x3F | 0x80);
                tail$iv2.limit += 3;
                Buffer buffer = $this$commonWriteUtf8CodePoint$iv;
                buffer.setSize$okio(buffer.size() + 3L);
            } else if (codePoint <= 0x10FFFF) {
                Segment tail$iv3 = $this$commonWriteUtf8CodePoint$iv.writableSegment$okio(4);
                tail$iv3.data[tail$iv3.limit] = (byte)(codePoint >> 18 | 0xF0);
                tail$iv3.data[tail$iv3.limit + 1] = (byte)(codePoint >> 12 & 0x3F | 0x80);
                tail$iv3.data[tail$iv3.limit + 2] = (byte)(codePoint >> 6 & 0x3F | 0x80);
                tail$iv3.data[tail$iv3.limit + 3] = (byte)(codePoint & 0x3F | 0x80);
                tail$iv3.limit += 4;
                Buffer buffer = $this$commonWriteUtf8CodePoint$iv;
                buffer.setSize$okio(buffer.size() + 4L);
            } else {
                throw (Throwable)new IllegalArgumentException("Unexpected code point: 0x" + -Util.toHexString(codePoint));
            }
        }
        return var2_2;
    }

    @Override
    @NotNull
    public Buffer writeString(@NotNull String string, @NotNull Charset charset) {
        Intrinsics.checkNotNullParameter(string, "string");
        Intrinsics.checkNotNullParameter(charset, "charset");
        return this.writeString(string, 0, string.length(), charset);
    }

    @Override
    @NotNull
    public Buffer writeString(@NotNull String string, int beginIndex, int endIndex, @NotNull Charset charset) {
        Intrinsics.checkNotNullParameter(string, "string");
        Intrinsics.checkNotNullParameter(charset, "charset");
        boolean bl = beginIndex >= 0;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string2 = "beginIndex < 0: " + beginIndex;
            throw (Throwable)new IllegalArgumentException(string2.toString());
        }
        bl = endIndex >= beginIndex;
        bl2 = false;
        bl3 = false;
        if (!bl) {
            boolean bl5 = false;
            String string3 = "endIndex < beginIndex: " + endIndex + " < " + beginIndex;
            throw (Throwable)new IllegalArgumentException(string3.toString());
        }
        bl = endIndex <= string.length();
        bl2 = false;
        bl3 = false;
        if (!bl) {
            boolean bl6 = false;
            String string4 = "endIndex > string.length: " + endIndex + " > " + string.length();
            throw (Throwable)new IllegalArgumentException(string4.toString());
        }
        if (Intrinsics.areEqual(charset, Charsets.UTF_8)) {
            return this.writeUtf8(string, beginIndex, endIndex);
        }
        String string5 = string;
        bl3 = false;
        String string6 = string5.substring(beginIndex, endIndex);
        Intrinsics.checkNotNullExpressionValue(string6, "(this as java.lang.Strin\u2026ing(startIndex, endIndex)");
        string5 = string6;
        bl3 = false;
        String string7 = string5;
        if (string7 == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        byte[] byArray = string7.getBytes(charset);
        Intrinsics.checkNotNullExpressionValue(byArray, "(this as java.lang.String).getBytes(charset)");
        byte[] data = byArray;
        return this.write(data, 0, data.length);
    }

    @Override
    @NotNull
    public Buffer write(@NotNull byte[] source2) {
        Intrinsics.checkNotNullParameter(source2, "source");
        Buffer $this$commonWrite$iv = this;
        boolean $i$f$commonWrite = false;
        return $this$commonWrite$iv.write(source2, 0, source2.length);
    }

    @Override
    @NotNull
    public Buffer write(@NotNull byte[] source2, int offset, int byteCount) {
        Intrinsics.checkNotNullParameter(source2, "source");
        Buffer $this$commonWrite$iv = this;
        boolean $i$f$commonWrite = false;
        int offset$iv = offset;
        -Util.checkOffsetAndCount(source2.length, offset$iv, byteCount);
        int limit$iv = offset$iv + byteCount;
        while (offset$iv < limit$iv) {
            Segment tail$iv = $this$commonWrite$iv.writableSegment$okio(1);
            int n = limit$iv - offset$iv;
            int n2 = 8192 - tail$iv.limit;
            boolean bl = false;
            int toCopy$iv = Math.min(n, n2);
            ArraysKt.copyInto(source2, tail$iv.data, tail$iv.limit, offset$iv, offset$iv + toCopy$iv);
            offset$iv += toCopy$iv;
            tail$iv.limit += toCopy$iv;
        }
        Buffer buffer = $this$commonWrite$iv;
        buffer.setSize$okio(buffer.size() + (long)byteCount);
        return $this$commonWrite$iv;
    }

    @Override
    public int write(@NotNull ByteBuffer source2) throws IOException {
        int byteCount;
        Intrinsics.checkNotNullParameter(source2, "source");
        int remaining = byteCount = source2.remaining();
        while (remaining > 0) {
            Segment tail = this.writableSegment$okio(1);
            int n = 8192 - tail.limit;
            boolean bl = false;
            int toCopy = Math.min(remaining, n);
            source2.get(tail.data, tail.limit, toCopy);
            remaining -= toCopy;
            tail.limit += toCopy;
        }
        this.size += (long)byteCount;
        return byteCount;
    }

    @Override
    public long writeAll(@NotNull Source source2) throws IOException {
        long readCount$iv;
        Intrinsics.checkNotNullParameter(source2, "source");
        Buffer $this$commonWriteAll$iv = this;
        boolean $i$f$commonWriteAll = false;
        long totalBytesRead$iv = 0L;
        while ((readCount$iv = source2.read($this$commonWriteAll$iv, 8192)) != -1L) {
            totalBytesRead$iv += readCount$iv;
        }
        return totalBytesRead$iv;
    }

    @Override
    @NotNull
    public Buffer write(@NotNull Source source2, long byteCount) throws IOException {
        long read$iv;
        Intrinsics.checkNotNullParameter(source2, "source");
        Buffer $this$commonWrite$iv = this;
        boolean $i$f$commonWrite = false;
        for (long byteCount$iv = byteCount; byteCount$iv > 0L; byteCount$iv -= read$iv) {
            read$iv = source2.read($this$commonWrite$iv, byteCount$iv);
            if (read$iv != -1L) continue;
            throw (Throwable)new EOFException();
        }
        return $this$commonWrite$iv;
    }

    /*
     * WARNING - void declaration
     */
    @Override
    @NotNull
    public Buffer writeByte(int b) {
        void var2_2;
        Buffer $this$commonWriteByte$iv = this;
        boolean $i$f$commonWriteByte = false;
        Segment tail$iv = $this$commonWriteByte$iv.writableSegment$okio(1);
        int n = tail$iv.limit;
        tail$iv.limit = n + 1;
        tail$iv.data[n] = (byte)b;
        Buffer buffer = $this$commonWriteByte$iv;
        buffer.setSize$okio(buffer.size() + 1L);
        return var2_2;
    }

    /*
     * WARNING - void declaration
     */
    @Override
    @NotNull
    public Buffer writeShort(int s2) {
        void var2_2;
        Buffer $this$commonWriteShort$iv = this;
        boolean $i$f$commonWriteShort = false;
        Segment tail$iv = $this$commonWriteShort$iv.writableSegment$okio(2);
        byte[] data$iv = tail$iv.data;
        int limit$iv = tail$iv.limit;
        data$iv[limit$iv++] = (byte)(s2 >>> 8 & 0xFF);
        data$iv[limit$iv++] = (byte)(s2 & 0xFF);
        tail$iv.limit = limit$iv;
        Buffer buffer = $this$commonWriteShort$iv;
        buffer.setSize$okio(buffer.size() + 2L);
        return var2_2;
    }

    @Override
    @NotNull
    public Buffer writeShortLe(int s2) {
        return this.writeShort(-Util.reverseBytes((short)s2));
    }

    /*
     * WARNING - void declaration
     */
    @Override
    @NotNull
    public Buffer writeInt(int i) {
        void var2_2;
        Buffer $this$commonWriteInt$iv = this;
        boolean $i$f$commonWriteInt = false;
        Segment tail$iv = $this$commonWriteInt$iv.writableSegment$okio(4);
        byte[] data$iv = tail$iv.data;
        int limit$iv = tail$iv.limit;
        data$iv[limit$iv++] = (byte)(i >>> 24 & 0xFF);
        data$iv[limit$iv++] = (byte)(i >>> 16 & 0xFF);
        data$iv[limit$iv++] = (byte)(i >>> 8 & 0xFF);
        data$iv[limit$iv++] = (byte)(i & 0xFF);
        tail$iv.limit = limit$iv;
        Buffer buffer = $this$commonWriteInt$iv;
        buffer.setSize$okio(buffer.size() + 4L);
        return var2_2;
    }

    @Override
    @NotNull
    public Buffer writeIntLe(int i) {
        return this.writeInt(-Util.reverseBytes(i));
    }

    /*
     * WARNING - void declaration
     */
    @Override
    @NotNull
    public Buffer writeLong(long v) {
        void var3_2;
        Buffer $this$commonWriteLong$iv = this;
        boolean $i$f$commonWriteLong = false;
        Segment tail$iv = $this$commonWriteLong$iv.writableSegment$okio(8);
        byte[] data$iv = tail$iv.data;
        int limit$iv = tail$iv.limit;
        data$iv[limit$iv++] = (byte)(v >>> 56 & 0xFFL);
        data$iv[limit$iv++] = (byte)(v >>> 48 & 0xFFL);
        data$iv[limit$iv++] = (byte)(v >>> 40 & 0xFFL);
        data$iv[limit$iv++] = (byte)(v >>> 32 & 0xFFL);
        data$iv[limit$iv++] = (byte)(v >>> 24 & 0xFFL);
        data$iv[limit$iv++] = (byte)(v >>> 16 & 0xFFL);
        data$iv[limit$iv++] = (byte)(v >>> 8 & 0xFFL);
        data$iv[limit$iv++] = (byte)(v & 0xFFL);
        tail$iv.limit = limit$iv;
        Buffer buffer = $this$commonWriteLong$iv;
        buffer.setSize$okio(buffer.size() + 8L);
        return var3_2;
    }

    @Override
    @NotNull
    public Buffer writeLongLe(long v) {
        return this.writeLong(-Util.reverseBytes(v));
    }

    /*
     * WARNING - void declaration
     * Enabled aggressive block sorting
     */
    @Override
    @NotNull
    public Buffer writeDecimalLong(long v) {
        void var3_2;
        int width$iv;
        Buffer buffer;
        Buffer $this$commonWriteDecimalLong$iv = this;
        boolean $i$f$commonWriteDecimalLong = false;
        long v$iv = v;
        if (v$iv == 0L) {
            buffer = $this$commonWriteDecimalLong$iv.writeByte(48);
            return buffer;
        }
        boolean negative$iv = false;
        if (v$iv < 0L) {
            if ((v$iv = -v$iv) < 0L) {
                buffer = $this$commonWriteDecimalLong$iv.writeUtf8("-9223372036854775808");
                return buffer;
            }
            negative$iv = true;
        }
        int n = v$iv < 100000000L ? (v$iv < 10000L ? (v$iv < 100L ? (v$iv < 10L ? 1 : 2) : (v$iv < 1000L ? 3 : 4)) : (v$iv < 1000000L ? (v$iv < 100000L ? 5 : 6) : (v$iv < 10000000L ? 7 : 8))) : (v$iv < 1000000000000L ? (v$iv < 10000000000L ? (v$iv < 1000000000L ? 9 : 10) : (v$iv < 100000000000L ? 11 : 12)) : (v$iv < 1000000000000000L ? (v$iv < 10000000000000L ? 13 : (v$iv < 100000000000000L ? 14 : 15)) : (v$iv < 100000000000000000L ? (v$iv < 10000000000000000L ? 16 : 17) : (width$iv = v$iv < 1000000000000000000L ? 18 : 19))));
        if (negative$iv) {
            ++width$iv;
        }
        Segment tail$iv = $this$commonWriteDecimalLong$iv.writableSegment$okio(width$iv);
        byte[] data$iv = tail$iv.data;
        int pos$iv = tail$iv.limit + width$iv;
        while (v$iv != 0L) {
            int digit$iv = (int)(v$iv % (long)10);
            data$iv[--pos$iv] = BufferKt.getHEX_DIGIT_BYTES()[digit$iv];
            v$iv /= (long)10;
        }
        if (negative$iv) {
            data$iv[--pos$iv] = (byte)45;
        }
        tail$iv.limit += width$iv;
        Buffer buffer2 = $this$commonWriteDecimalLong$iv;
        buffer2.setSize$okio(buffer2.size() + (long)width$iv);
        buffer = var3_2;
        return buffer;
    }

    /*
     * WARNING - void declaration
     */
    @Override
    @NotNull
    public Buffer writeHexadecimalUnsignedLong(long v) {
        Buffer buffer;
        Buffer $this$commonWriteHexadecimalUnsignedLong$iv = this;
        boolean $i$f$commonWriteHexadecimalUnsignedLong = false;
        long v$iv = v;
        if (v$iv == 0L) {
            buffer = $this$commonWriteHexadecimalUnsignedLong$iv.writeByte(48);
        } else {
            void var3_2;
            long x$iv = v$iv;
            x$iv |= x$iv >>> 1;
            x$iv |= x$iv >>> 2;
            x$iv |= x$iv >>> 4;
            x$iv |= x$iv >>> 8;
            x$iv |= x$iv >>> 16;
            x$iv |= x$iv >>> 32;
            x$iv -= x$iv >>> 1 & 0x5555555555555555L;
            x$iv = (x$iv >>> 2 & 0x3333333333333333L) + (x$iv & 0x3333333333333333L);
            x$iv = (x$iv >>> 4) + x$iv & 0xF0F0F0F0F0F0F0FL;
            x$iv += x$iv >>> 8;
            x$iv += x$iv >>> 16;
            x$iv = (x$iv & 0x3FL) + (x$iv >>> 32 & 0x3FL);
            int width$iv = (int)((x$iv + (long)3) / (long)4);
            Segment tail$iv = $this$commonWriteHexadecimalUnsignedLong$iv.writableSegment$okio(width$iv);
            byte[] data$iv = tail$iv.data;
            int start$iv = tail$iv.limit;
            for (int pos$iv = tail$iv.limit + width$iv - 1; pos$iv >= start$iv; --pos$iv) {
                data$iv[pos$iv] = BufferKt.getHEX_DIGIT_BYTES()[(int)(v$iv & 0xFL)];
                v$iv >>>= 4;
            }
            tail$iv.limit += width$iv;
            Buffer buffer2 = $this$commonWriteHexadecimalUnsignedLong$iv;
            buffer2.setSize$okio(buffer2.size() + (long)width$iv);
            buffer = var3_2;
        }
        return buffer;
    }

    @NotNull
    public final Segment writableSegment$okio(int minimumCapacity) {
        Segment segment;
        Buffer $this$commonWritableSegment$iv = this;
        boolean $i$f$commonWritableSegment = false;
        boolean bl = minimumCapacity >= 1 && minimumCapacity <= 8192;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "unexpected capacity";
            throw (Throwable)new IllegalArgumentException(string.toString());
        }
        if ($this$commonWritableSegment$iv.head == null) {
            Segment result$iv;
            $this$commonWritableSegment$iv.head = result$iv = SegmentPool.take();
            result$iv.prev = result$iv;
            result$iv.next = result$iv;
            segment = result$iv;
        } else {
            Segment tail$iv;
            Segment segment2 = $this$commonWritableSegment$iv.head;
            Intrinsics.checkNotNull(segment2);
            Segment segment3 = tail$iv = segment2.prev;
            Intrinsics.checkNotNull(segment3);
            if (segment3.limit + minimumCapacity > 8192 || !tail$iv.owner) {
                tail$iv = tail$iv.push(SegmentPool.take());
            }
            segment = tail$iv;
        }
        return segment;
    }

    @Override
    public void write(@NotNull Buffer source2, long byteCount) {
        long movedByteCount$iv;
        long byteCount$iv;
        Intrinsics.checkNotNullParameter(source2, "source");
        Buffer $this$commonWrite$iv = this;
        boolean $i$f$commonWrite = false;
        boolean bl = source2 != $this$commonWrite$iv;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "source == this";
            throw (Throwable)new IllegalArgumentException(string.toString());
        }
        -Util.checkOffsetAndCount(source2.size(), 0L, byteCount$iv);
        for (byteCount$iv = byteCount; byteCount$iv > 0L; byteCount$iv -= movedByteCount$iv) {
            Segment segmentToMove$iv;
            Segment segment = source2.head;
            Intrinsics.checkNotNull(segment);
            int n = segment.limit;
            Segment segment2 = source2.head;
            Intrinsics.checkNotNull(segment2);
            if (byteCount$iv < (long)(n - segment2.pos)) {
                Segment tail$iv;
                Segment segment3;
                if ($this$commonWrite$iv.head != null) {
                    Segment segment4 = $this$commonWrite$iv.head;
                    Intrinsics.checkNotNull(segment4);
                    segment3 = segment4.prev;
                } else {
                    segment3 = tail$iv = null;
                }
                if (tail$iv != null && tail$iv.owner && byteCount$iv + (long)tail$iv.limit - (long)(tail$iv.shared ? 0 : tail$iv.pos) <= (long)8192) {
                    Segment segment5 = source2.head;
                    Intrinsics.checkNotNull(segment5);
                    segment5.writeTo(tail$iv, (int)byteCount$iv);
                    Buffer buffer = source2;
                    buffer.setSize$okio(buffer.size() - byteCount$iv);
                    Buffer buffer2 = $this$commonWrite$iv;
                    buffer2.setSize$okio(buffer2.size() + byteCount$iv);
                    break;
                }
                Segment segment6 = source2.head;
                Intrinsics.checkNotNull(segment6);
                source2.head = segment6.split((int)byteCount$iv);
            }
            Segment segment7 = segmentToMove$iv = source2.head;
            Intrinsics.checkNotNull(segment7);
            movedByteCount$iv = segment7.limit - segmentToMove$iv.pos;
            source2.head = segmentToMove$iv.pop();
            if ($this$commonWrite$iv.head == null) {
                $this$commonWrite$iv.head = segmentToMove$iv;
                segmentToMove$iv.next = segmentToMove$iv.prev = segmentToMove$iv;
            } else {
                Segment tail$iv;
                Segment segment8 = $this$commonWrite$iv.head;
                Intrinsics.checkNotNull(segment8);
                Segment segment9 = tail$iv = segment8.prev;
                Intrinsics.checkNotNull(segment9);
                tail$iv = segment9.push(segmentToMove$iv);
                tail$iv.compact();
            }
            Buffer buffer = source2;
            buffer.setSize$okio(buffer.size() - movedByteCount$iv);
            Buffer buffer3 = $this$commonWrite$iv;
            buffer3.setSize$okio(buffer3.size() + movedByteCount$iv);
        }
    }

    @Override
    public long read(@NotNull Buffer sink2, long byteCount) {
        long l;
        Intrinsics.checkNotNullParameter(sink2, "sink");
        Buffer $this$commonRead$iv = this;
        boolean $i$f$commonRead = false;
        long byteCount$iv = byteCount;
        boolean bl = byteCount$iv >= 0L;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "byteCount < 0: " + byteCount$iv;
            throw (Throwable)new IllegalArgumentException(string.toString());
        }
        if ($this$commonRead$iv.size() == 0L) {
            l = -1L;
        } else {
            if (byteCount$iv > $this$commonRead$iv.size()) {
                byteCount$iv = $this$commonRead$iv.size();
            }
            sink2.write($this$commonRead$iv, byteCount$iv);
            l = byteCount$iv;
        }
        return l;
    }

    @Override
    public long indexOf(byte b) {
        return this.indexOf(b, 0L, Long.MAX_VALUE);
    }

    @Override
    public long indexOf(byte b, long fromIndex) {
        return this.indexOf(b, fromIndex, Long.MAX_VALUE);
    }

    /*
     * WARNING - void declaration
     */
    @Override
    public long indexOf(byte b, long fromIndex, long toIndex) {
        long l;
        block18: {
            long toIndex$iv;
            Buffer $this$commonIndexOf$iv = this;
            boolean $i$f$commonIndexOf = false;
            long fromIndex$iv = fromIndex;
            long l2 = fromIndex$iv;
            boolean bl = 0L <= l2 && (toIndex$iv = toIndex) >= l2;
            boolean bl2 = false;
            boolean bl3 = false;
            if (!bl) {
                boolean bl4 = false;
                String string = "size=" + $this$commonIndexOf$iv.size() + " fromIndex=" + fromIndex$iv + " toIndex=" + toIndex$iv;
                throw (Throwable)new IllegalArgumentException(string.toString());
            }
            if (toIndex$iv > $this$commonIndexOf$iv.size()) {
                toIndex$iv = $this$commonIndexOf$iv.size();
            }
            if (fromIndex$iv == toIndex$iv) {
                l = -1L;
            } else {
                void $this$seek$iv$iv;
                Buffer buffer = $this$commonIndexOf$iv;
                long fromIndex$iv$iv = fromIndex$iv;
                boolean $i$f$seek = false;
                Segment segment = $this$seek$iv$iv.head;
                if (segment == null) {
                    long l3 = -1L;
                    Segment s$iv = null;
                    boolean bl5 = false;
                    l = -1L;
                } else {
                    Segment s$iv$iv = segment;
                    if ($this$seek$iv$iv.size() - fromIndex$iv$iv < fromIndex$iv$iv) {
                        long offset$iv$iv;
                        for (offset$iv$iv = $this$seek$iv$iv.size(); offset$iv$iv > fromIndex$iv$iv; offset$iv$iv -= (long)(s$iv$iv.limit - s$iv$iv.pos)) {
                            Intrinsics.checkNotNull(s$iv$iv.prev);
                        }
                        long offset$iv = offset$iv$iv;
                        Segment s$iv = s$iv$iv;
                        boolean bl6 = false;
                        Segment segment2 = s$iv;
                        if (segment2 == null) {
                            l = -1L;
                        } else {
                            Segment s$iv2 = segment2;
                            long offset$iv2 = offset$iv;
                            while (offset$iv2 < toIndex$iv) {
                                byte[] data$iv = s$iv2.data;
                                long l4 = s$iv2.limit;
                                long l5 = (long)s$iv2.pos + toIndex$iv - offset$iv2;
                                boolean bl7 = false;
                                int limit$iv = (int)Math.min(l4, l5);
                                for (int pos$iv = (int)((long)s$iv2.pos + fromIndex$iv - offset$iv2); pos$iv < limit$iv; ++pos$iv) {
                                    if (data$iv[pos$iv] != b) continue;
                                    l = (long)(pos$iv - s$iv2.pos) + offset$iv2;
                                    break block18;
                                }
                                fromIndex$iv = offset$iv2 += (long)(s$iv2.limit - s$iv2.pos);
                                Intrinsics.checkNotNull(s$iv2.next);
                            }
                            l = -1L;
                        }
                    } else {
                        long nextOffset$iv$iv;
                        long offset$iv$iv = 0L;
                        while ((nextOffset$iv$iv = offset$iv$iv + (long)(s$iv$iv.limit - s$iv$iv.pos)) <= fromIndex$iv$iv) {
                            Intrinsics.checkNotNull(s$iv$iv.next);
                            offset$iv$iv = nextOffset$iv$iv;
                        }
                        long l6 = offset$iv$iv;
                        Segment s$iv = s$iv$iv;
                        boolean bl8 = false;
                        Segment segment3 = s$iv;
                        if (segment3 == null) {
                            l = -1L;
                        } else {
                            void offset$iv;
                            Segment s$iv3 = segment3;
                            void offset$iv3 = offset$iv;
                            while (offset$iv3 < toIndex$iv) {
                                byte[] data$iv = s$iv3.data;
                                long l7 = s$iv3.limit;
                                long l8 = (long)s$iv3.pos + toIndex$iv - offset$iv3;
                                boolean bl9 = false;
                                int limit$iv = (int)Math.min(l7, l8);
                                for (int pos$iv = (int)((long)s$iv3.pos + fromIndex$iv - offset$iv3); pos$iv < limit$iv; ++pos$iv) {
                                    if (data$iv[pos$iv] != b) continue;
                                    l = (long)(pos$iv - s$iv3.pos) + offset$iv3;
                                    break block18;
                                }
                                fromIndex$iv = offset$iv3 += (long)(s$iv3.limit - s$iv3.pos);
                                Intrinsics.checkNotNull(s$iv3.next);
                            }
                            l = -1L;
                        }
                    }
                }
            }
        }
        return l;
    }

    @Override
    public long indexOf(@NotNull ByteString bytes) throws IOException {
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        return this.indexOf(bytes, 0L);
    }

    /*
     * WARNING - void declaration
     */
    @Override
    public long indexOf(@NotNull ByteString bytes, long fromIndex) throws IOException {
        long l;
        block18: {
            void $this$seek$iv$iv;
            Intrinsics.checkNotNullParameter(bytes, "bytes");
            Buffer $this$commonIndexOf$iv = this;
            boolean $i$f$commonIndexOf = false;
            long fromIndex$iv = fromIndex;
            boolean bl = bytes.size() > 0;
            boolean bl2 = false;
            boolean bl3 = false;
            if (!bl) {
                boolean bl4 = false;
                String string = "bytes is empty";
                throw (Throwable)new IllegalArgumentException(string.toString());
            }
            bl = fromIndex$iv >= 0L;
            bl2 = false;
            bl3 = false;
            if (!bl) {
                boolean bl5 = false;
                String string = "fromIndex < 0: " + fromIndex$iv;
                throw (Throwable)new IllegalArgumentException(string.toString());
            }
            Buffer buffer = $this$commonIndexOf$iv;
            long fromIndex$iv$iv = fromIndex$iv;
            boolean $i$f$seek = false;
            Segment segment = $this$seek$iv$iv.head;
            if (segment == null) {
                long l2 = -1L;
                Segment s$iv = null;
                boolean bl6 = false;
                l = -1L;
            } else {
                Segment s$iv$iv = segment;
                if ($this$seek$iv$iv.size() - fromIndex$iv$iv < fromIndex$iv$iv) {
                    long offset$iv$iv;
                    for (offset$iv$iv = $this$seek$iv$iv.size(); offset$iv$iv > fromIndex$iv$iv; offset$iv$iv -= (long)(s$iv$iv.limit - s$iv$iv.pos)) {
                        Intrinsics.checkNotNull(s$iv$iv.prev);
                    }
                    long offset$iv = offset$iv$iv;
                    Segment s$iv = s$iv$iv;
                    boolean bl7 = false;
                    Segment segment2 = s$iv;
                    if (segment2 == null) {
                        l = -1L;
                    } else {
                        Segment s$iv2 = segment2;
                        long offset$iv2 = offset$iv;
                        byte[] targetByteArray$iv = bytes.internalArray$okio();
                        byte b0$iv = targetByteArray$iv[0];
                        int bytesSize$iv = bytes.size();
                        long resultLimit$iv = $this$commonIndexOf$iv.size() - (long)bytesSize$iv + 1L;
                        while (offset$iv2 < resultLimit$iv) {
                            int a$iv$iv;
                            byte[] data$iv = s$iv2.data;
                            int n = s$iv2.limit;
                            long b$iv$iv = (long)s$iv2.pos + resultLimit$iv - offset$iv2;
                            boolean $i$f$minOf = false;
                            long l3 = a$iv$iv;
                            boolean bl8 = false;
                            int segmentLimit$iv = (int)Math.min(l3, b$iv$iv);
                            a$iv$iv = (int)((long)s$iv2.pos + fromIndex$iv - offset$iv2);
                            int n2 = segmentLimit$iv;
                            while (a$iv$iv < n2) {
                                void pos$iv;
                                if (data$iv[pos$iv] == b0$iv && BufferKt.rangeEquals(s$iv2, (int)(pos$iv + true), targetByteArray$iv, 1, bytesSize$iv)) {
                                    l = (long)(pos$iv - s$iv2.pos) + offset$iv2;
                                    break block18;
                                }
                                ++pos$iv;
                            }
                            fromIndex$iv = offset$iv2 += (long)(s$iv2.limit - s$iv2.pos);
                            Intrinsics.checkNotNull(s$iv2.next);
                        }
                        l = -1L;
                    }
                } else {
                    long nextOffset$iv$iv;
                    long offset$iv$iv = 0L;
                    while ((nextOffset$iv$iv = offset$iv$iv + (long)(s$iv$iv.limit - s$iv$iv.pos)) <= fromIndex$iv$iv) {
                        Intrinsics.checkNotNull(s$iv$iv.next);
                        offset$iv$iv = nextOffset$iv$iv;
                    }
                    long l4 = offset$iv$iv;
                    Segment s$iv = s$iv$iv;
                    boolean bl9 = false;
                    Segment segment3 = s$iv;
                    if (segment3 == null) {
                        l = -1L;
                    } else {
                        void offset$iv;
                        Segment s$iv3 = segment3;
                        void offset$iv3 = offset$iv;
                        byte[] targetByteArray$iv = bytes.internalArray$okio();
                        byte b0$iv = targetByteArray$iv[0];
                        int bytesSize$iv = bytes.size();
                        long resultLimit$iv = $this$commonIndexOf$iv.size() - (long)bytesSize$iv + 1L;
                        while (offset$iv3 < resultLimit$iv) {
                            int a$iv$iv;
                            byte[] data$iv = s$iv3.data;
                            int n = s$iv3.limit;
                            long b$iv$iv = (long)s$iv3.pos + resultLimit$iv - offset$iv3;
                            boolean $i$f$minOf = false;
                            long l5 = a$iv$iv;
                            boolean bl10 = false;
                            int segmentLimit$iv = (int)Math.min(l5, b$iv$iv);
                            a$iv$iv = (int)((long)s$iv3.pos + fromIndex$iv - offset$iv3);
                            int n3 = segmentLimit$iv;
                            while (a$iv$iv < n3) {
                                void pos$iv;
                                if (data$iv[pos$iv] == b0$iv && BufferKt.rangeEquals(s$iv3, (int)(pos$iv + true), targetByteArray$iv, 1, bytesSize$iv)) {
                                    l = (long)(pos$iv - s$iv3.pos) + offset$iv3;
                                    break block18;
                                }
                                ++pos$iv;
                            }
                            fromIndex$iv = offset$iv3 += (long)(s$iv3.limit - s$iv3.pos);
                            Intrinsics.checkNotNull(s$iv3.next);
                        }
                        l = -1L;
                    }
                }
            }
        }
        return l;
    }

    @Override
    public long indexOfElement(@NotNull ByteString targetBytes) {
        Intrinsics.checkNotNullParameter(targetBytes, "targetBytes");
        return this.indexOfElement(targetBytes, 0L);
    }

    /*
     * WARNING - void declaration
     */
    @Override
    public long indexOfElement(@NotNull ByteString targetBytes, long fromIndex) {
        long l;
        block25: {
            void $this$seek$iv$iv;
            Intrinsics.checkNotNullParameter(targetBytes, "targetBytes");
            Buffer $this$commonIndexOfElement$iv = this;
            boolean $i$f$commonIndexOfElement = false;
            long fromIndex$iv = fromIndex;
            boolean bl = fromIndex$iv >= 0L;
            boolean bl2 = false;
            boolean bl3 = false;
            if (!bl) {
                boolean bl4 = false;
                String string = "fromIndex < 0: " + fromIndex$iv;
                throw (Throwable)new IllegalArgumentException(string.toString());
            }
            Buffer buffer = $this$commonIndexOfElement$iv;
            long fromIndex$iv$iv = fromIndex$iv;
            boolean $i$f$seek = false;
            Segment segment = $this$seek$iv$iv.head;
            if (segment == null) {
                long l2 = -1L;
                Segment s$iv = null;
                boolean bl5 = false;
                l = -1L;
            } else {
                Segment s$iv$iv = segment;
                if ($this$seek$iv$iv.size() - fromIndex$iv$iv < fromIndex$iv$iv) {
                    long offset$iv$iv;
                    for (offset$iv$iv = $this$seek$iv$iv.size(); offset$iv$iv > fromIndex$iv$iv; offset$iv$iv -= (long)(s$iv$iv.limit - s$iv$iv.pos)) {
                        Intrinsics.checkNotNull(s$iv$iv.prev);
                    }
                    long offset$iv = offset$iv$iv;
                    Segment s$iv = s$iv$iv;
                    boolean bl6 = false;
                    Segment segment2 = s$iv;
                    if (segment2 == null) {
                        l = -1L;
                    } else {
                        Segment s$iv2 = segment2;
                        long offset$iv2 = offset$iv;
                        if (targetBytes.size() == 2) {
                            byte b0$iv = targetBytes.getByte(0);
                            byte b1$iv = targetBytes.getByte(1);
                            while (offset$iv2 < $this$commonIndexOfElement$iv.size()) {
                                byte[] data$iv = s$iv2.data;
                                int limit$iv = s$iv2.limit;
                                for (int pos$iv = (int)((long)s$iv2.pos + fromIndex$iv - offset$iv2); pos$iv < limit$iv; ++pos$iv) {
                                    byte b$iv = data$iv[pos$iv];
                                    if (b$iv != b0$iv && b$iv != b1$iv) continue;
                                    l = (long)(pos$iv - s$iv2.pos) + offset$iv2;
                                    break block25;
                                }
                                fromIndex$iv = offset$iv2 += (long)(s$iv2.limit - s$iv2.pos);
                                Intrinsics.checkNotNull(s$iv2.next);
                            }
                        } else {
                            byte[] targetByteArray$iv = targetBytes.internalArray$okio();
                            while (offset$iv2 < $this$commonIndexOfElement$iv.size()) {
                                byte[] data$iv = s$iv2.data;
                                int limit$iv = s$iv2.limit;
                                for (int pos$iv = (int)((long)s$iv2.pos + fromIndex$iv - offset$iv2); pos$iv < limit$iv; ++pos$iv) {
                                    byte b$iv = data$iv[pos$iv];
                                    for (byte t$iv : targetByteArray$iv) {
                                        if (b$iv != t$iv) continue;
                                        l = (long)(pos$iv - s$iv2.pos) + offset$iv2;
                                        break block25;
                                    }
                                }
                                fromIndex$iv = offset$iv2 += (long)(s$iv2.limit - s$iv2.pos);
                                Intrinsics.checkNotNull(s$iv2.next);
                            }
                        }
                        l = -1L;
                    }
                } else {
                    long nextOffset$iv$iv;
                    long offset$iv$iv = 0L;
                    while ((nextOffset$iv$iv = offset$iv$iv + (long)(s$iv$iv.limit - s$iv$iv.pos)) <= fromIndex$iv$iv) {
                        Intrinsics.checkNotNull(s$iv$iv.next);
                        offset$iv$iv = nextOffset$iv$iv;
                    }
                    long l3 = offset$iv$iv;
                    Segment s$iv = s$iv$iv;
                    boolean bl7 = false;
                    Segment segment3 = s$iv;
                    if (segment3 == null) {
                        l = -1L;
                    } else {
                        void offset$iv;
                        Segment s$iv3 = segment3;
                        void offset$iv3 = offset$iv;
                        if (targetBytes.size() == 2) {
                            byte b0$iv = targetBytes.getByte(0);
                            byte b1$iv = targetBytes.getByte(1);
                            while (offset$iv3 < $this$commonIndexOfElement$iv.size()) {
                                byte[] data$iv = s$iv3.data;
                                int limit$iv = s$iv3.limit;
                                for (int pos$iv = (int)((long)s$iv3.pos + fromIndex$iv - offset$iv3); pos$iv < limit$iv; ++pos$iv) {
                                    byte b$iv = data$iv[pos$iv];
                                    if (b$iv != b0$iv && b$iv != b1$iv) continue;
                                    l = (long)(pos$iv - s$iv3.pos) + offset$iv3;
                                    break block25;
                                }
                                fromIndex$iv = offset$iv3 += (long)(s$iv3.limit - s$iv3.pos);
                                Intrinsics.checkNotNull(s$iv3.next);
                            }
                        } else {
                            byte[] targetByteArray$iv = targetBytes.internalArray$okio();
                            while (offset$iv3 < $this$commonIndexOfElement$iv.size()) {
                                byte[] data$iv = s$iv3.data;
                                int limit$iv = s$iv3.limit;
                                for (int pos$iv = (int)((long)s$iv3.pos + fromIndex$iv - offset$iv3); pos$iv < limit$iv; ++pos$iv) {
                                    byte b$iv = data$iv[pos$iv];
                                    for (byte t$iv : targetByteArray$iv) {
                                        if (b$iv != t$iv) continue;
                                        l = (long)(pos$iv - s$iv3.pos) + offset$iv3;
                                        break block25;
                                    }
                                }
                                fromIndex$iv = offset$iv3 += (long)(s$iv3.limit - s$iv3.pos);
                                Intrinsics.checkNotNull(s$iv3.next);
                            }
                        }
                        l = -1L;
                    }
                }
            }
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
        block4: {
            Intrinsics.checkNotNullParameter(bytes, "bytes");
            Buffer $this$commonRangeEquals$iv = this;
            boolean $i$f$commonRangeEquals = false;
            if (offset < 0L || bytesOffset < 0 || byteCount < 0 || $this$commonRangeEquals$iv.size() - offset < (long)byteCount || bytes.size() - bytesOffset < byteCount) {
                bl = false;
            } else {
                int n = 0;
                int n2 = byteCount;
                while (n < n2) {
                    void i$iv;
                    if ($this$commonRangeEquals$iv.getByte(offset + (long)i$iv) != bytes.getByte(bytesOffset + i$iv)) {
                        bl = false;
                        break block4;
                    }
                    ++i$iv;
                }
                bl = true;
            }
        }
        return bl;
    }

    @Override
    public void flush() {
    }

    @Override
    public boolean isOpen() {
        return true;
    }

    @Override
    public void close() {
    }

    @Override
    @NotNull
    public Timeout timeout() {
        return Timeout.NONE;
    }

    @NotNull
    public final ByteString md5() {
        return this.digest("MD5");
    }

    @NotNull
    public final ByteString sha1() {
        return this.digest("SHA-1");
    }

    @NotNull
    public final ByteString sha256() {
        return this.digest("SHA-256");
    }

    @NotNull
    public final ByteString sha512() {
        return this.digest("SHA-512");
    }

    private final ByteString digest(String algorithm) {
        MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
        Segment segment = this.head;
        if (segment != null) {
            Segment segment2 = segment;
            boolean bl = false;
            boolean bl2 = false;
            Segment head = segment2;
            boolean bl3 = false;
            messageDigest.update(head.data, head.pos, head.limit - head.pos);
            Segment segment3 = head.next;
            Intrinsics.checkNotNull(segment3);
            Segment s2 = segment3;
            while (s2 != head) {
                messageDigest.update(s2.data, s2.pos, s2.limit - s2.pos);
                Intrinsics.checkNotNull(s2.next);
            }
        }
        byte[] byArray = messageDigest.digest();
        Intrinsics.checkNotNullExpressionValue(byArray, "messageDigest.digest()");
        return new ByteString(byArray);
    }

    @NotNull
    public final ByteString hmacSha1(@NotNull ByteString key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return this.hmac("HmacSHA1", key);
    }

    @NotNull
    public final ByteString hmacSha256(@NotNull ByteString key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return this.hmac("HmacSHA256", key);
    }

    @NotNull
    public final ByteString hmacSha512(@NotNull ByteString key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return this.hmac("HmacSHA512", key);
    }

    private final ByteString hmac(String algorithm, ByteString key) {
        try {
            Mac mac = Mac.getInstance(algorithm);
            mac.init(new SecretKeySpec(key.internalArray$okio(), algorithm));
            Segment segment = this.head;
            if (segment != null) {
                Segment segment2 = segment;
                boolean bl = false;
                boolean bl2 = false;
                Segment head = segment2;
                boolean bl3 = false;
                mac.update(head.data, head.pos, head.limit - head.pos);
                Segment segment3 = head.next;
                Intrinsics.checkNotNull(segment3);
                Segment s2 = segment3;
                while (s2 != head) {
                    mac.update(s2.data, s2.pos, s2.limit - s2.pos);
                    Intrinsics.checkNotNull(s2.next);
                }
            }
            byte[] byArray = mac.doFinal();
            Intrinsics.checkNotNullExpressionValue(byArray, "mac.doFinal()");
            return new ByteString(byArray);
        } catch (InvalidKeyException e) {
            throw (Throwable)new IllegalArgumentException(e);
        }
    }

    /*
     * WARNING - void declaration
     */
    public boolean equals(@Nullable Object other) {
        boolean bl;
        block12: {
            Buffer $this$commonEquals$iv = this;
            boolean $i$f$commonEquals = false;
            if ($this$commonEquals$iv == other) {
                bl = true;
            } else if (!(other instanceof Buffer)) {
                bl = false;
            } else if ($this$commonEquals$iv.size() != ((Buffer)other).size()) {
                bl = false;
            } else if ($this$commonEquals$iv.size() == 0L) {
                bl = true;
            } else {
                Segment segment = $this$commonEquals$iv.head;
                Intrinsics.checkNotNull(segment);
                Segment sa$iv = segment;
                Segment segment2 = ((Buffer)other).head;
                Intrinsics.checkNotNull(segment2);
                Segment sb$iv = segment2;
                int posA$iv = sa$iv.pos;
                int posB$iv = sb$iv.pos;
                long count$iv = 0L;
                for (long pos$iv = 0L; pos$iv < $this$commonEquals$iv.size(); pos$iv += count$iv) {
                    int n = sa$iv.limit - posA$iv;
                    int n2 = sb$iv.limit - posB$iv;
                    boolean bl2 = false;
                    count$iv = Math.min(n, n2);
                    long l = 0L;
                    long l2 = count$iv;
                    while (l < l2) {
                        void i$iv;
                        if (sa$iv.data[posA$iv++] != sb$iv.data[posB$iv++]) {
                            bl = false;
                            break block12;
                        }
                        ++i$iv;
                    }
                    if (posA$iv == sa$iv.limit) {
                        Intrinsics.checkNotNull(sa$iv.next);
                        posA$iv = sa$iv.pos;
                    }
                    if (posB$iv != sb$iv.limit) continue;
                    Intrinsics.checkNotNull(sb$iv.next);
                    posB$iv = sb$iv.pos;
                }
                bl = true;
            }
        }
        return bl;
    }

    public int hashCode() {
        int n;
        Buffer $this$commonHashCode$iv = this;
        boolean $i$f$commonHashCode = false;
        Segment segment = $this$commonHashCode$iv.head;
        if (segment == null) {
            n = 0;
        } else {
            Segment s$iv = segment;
            int result$iv = 1;
            do {
                int limit$iv = s$iv.limit;
                for (int pos$iv = s$iv.pos; pos$iv < limit$iv; ++pos$iv) {
                    result$iv = 31 * result$iv + s$iv.data[pos$iv];
                }
                Intrinsics.checkNotNull(s$iv.next);
            } while (s$iv != $this$commonHashCode$iv.head);
            n = result$iv;
        }
        return n;
    }

    @NotNull
    public String toString() {
        return this.snapshot().toString();
    }

    /*
     * WARNING - void declaration
     */
    @NotNull
    public final Buffer copy() {
        Buffer buffer;
        Buffer $this$commonCopy$iv = this;
        boolean $i$f$commonCopy = false;
        Buffer result$iv = new Buffer();
        if ($this$commonCopy$iv.size() == 0L) {
            buffer = result$iv;
        } else {
            void var3_3;
            Segment headCopy$iv;
            Segment segment = $this$commonCopy$iv.head;
            Intrinsics.checkNotNull(segment);
            Segment head$iv = segment;
            headCopy$iv.next = headCopy$iv.prev = (result$iv.head = (headCopy$iv = head$iv.sharedCopy()));
            Segment s$iv = head$iv.next;
            while (s$iv != head$iv) {
                Segment segment2 = headCopy$iv.prev;
                Intrinsics.checkNotNull(segment2);
                Segment segment3 = s$iv;
                Intrinsics.checkNotNull(segment3);
                segment2.push(segment3.sharedCopy());
                s$iv = s$iv.next;
            }
            result$iv.setSize$okio($this$commonCopy$iv.size());
            buffer = var3_3;
        }
        return buffer;
    }

    @NotNull
    public Buffer clone() {
        return this.copy();
    }

    @NotNull
    public final ByteString snapshot() {
        Buffer $this$commonSnapshot$iv = this;
        boolean $i$f$commonSnapshot = false;
        boolean bl = $this$commonSnapshot$iv.size() <= (long)Integer.MAX_VALUE;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "size > Int.MAX_VALUE: " + $this$commonSnapshot$iv.size();
            throw (Throwable)new IllegalStateException(string.toString());
        }
        return $this$commonSnapshot$iv.snapshot((int)$this$commonSnapshot$iv.size());
    }

    @NotNull
    public final ByteString snapshot(int byteCount) {
        ByteString byteString;
        Buffer $this$commonSnapshot$iv = this;
        boolean $i$f$commonSnapshot = false;
        if (byteCount == 0) {
            byteString = ByteString.EMPTY;
        } else {
            -Util.checkOffsetAndCount($this$commonSnapshot$iv.size(), 0L, byteCount);
            int offset$iv = 0;
            int segmentCount$iv = 0;
            Segment s$iv = $this$commonSnapshot$iv.head;
            while (offset$iv < byteCount) {
                Segment segment = s$iv;
                Intrinsics.checkNotNull(segment);
                if (segment.limit == s$iv.pos) {
                    throw (Throwable)((Object)new AssertionError((Object)"s.limit == s.pos"));
                }
                offset$iv += s$iv.limit - s$iv.pos;
                ++segmentCount$iv;
                s$iv = s$iv.next;
            }
            byte[][] segments$iv = new byte[segmentCount$iv][];
            int[] directory$iv = new int[segmentCount$iv * 2];
            offset$iv = 0;
            segmentCount$iv = 0;
            s$iv = $this$commonSnapshot$iv.head;
            while (offset$iv < byteCount) {
                Segment segment = s$iv;
                Intrinsics.checkNotNull(segment);
                segments$iv[segmentCount$iv] = segment.data;
                boolean bl = false;
                directory$iv[segmentCount$iv] = Math.min(offset$iv += s$iv.limit - s$iv.pos, byteCount);
                directory$iv[segmentCount$iv + ((Object[])segments$iv).length] = s$iv.pos;
                s$iv.shared = true;
                ++segmentCount$iv;
                s$iv = s$iv.next;
            }
            byteString = new SegmentedByteString(segments$iv, directory$iv);
        }
        return byteString;
    }

    @JvmOverloads
    @NotNull
    public final UnsafeCursor readUnsafe(@NotNull UnsafeCursor unsafeCursor) {
        Intrinsics.checkNotNullParameter(unsafeCursor, "unsafeCursor");
        boolean bl = unsafeCursor.buffer == null;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "already attached to a buffer";
            throw (Throwable)new IllegalStateException(string.toString());
        }
        unsafeCursor.buffer = this;
        unsafeCursor.readWrite = false;
        return unsafeCursor;
    }

    public static /* synthetic */ UnsafeCursor readUnsafe$default(Buffer buffer, UnsafeCursor unsafeCursor, int n, Object object) {
        if ((n & 1) != 0) {
            unsafeCursor = new UnsafeCursor();
        }
        return buffer.readUnsafe(unsafeCursor);
    }

    @JvmOverloads
    @NotNull
    public final UnsafeCursor readUnsafe() {
        return Buffer.readUnsafe$default(this, null, 1, null);
    }

    @JvmOverloads
    @NotNull
    public final UnsafeCursor readAndWriteUnsafe(@NotNull UnsafeCursor unsafeCursor) {
        Intrinsics.checkNotNullParameter(unsafeCursor, "unsafeCursor");
        boolean bl = unsafeCursor.buffer == null;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "already attached to a buffer";
            throw (Throwable)new IllegalStateException(string.toString());
        }
        unsafeCursor.buffer = this;
        unsafeCursor.readWrite = true;
        return unsafeCursor;
    }

    public static /* synthetic */ UnsafeCursor readAndWriteUnsafe$default(Buffer buffer, UnsafeCursor unsafeCursor, int n, Object object) {
        if ((n & 1) != 0) {
            unsafeCursor = new UnsafeCursor();
        }
        return buffer.readAndWriteUnsafe(unsafeCursor);
    }

    @JvmOverloads
    @NotNull
    public final UnsafeCursor readAndWriteUnsafe() {
        return Buffer.readAndWriteUnsafe$default(this, null, 1, null);
    }

    @Deprecated(message="moved to operator function", replaceWith=@ReplaceWith(imports={}, expression="this[index]"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_getByte")
    public final byte -deprecated_getByte(long index) {
        return this.getByte(index);
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="size"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_size")
    public final long -deprecated_size() {
        return this.size;
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\u000e\u0010\u0012\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\bJ\u0006\u0010\u0014\u001a\u00020\bJ\u000e\u0010\u0015\u001a\u00020\n2\u0006\u0010\u0016\u001a\u00020\nJ\u000e\u0010\u0017\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nR\u0014\u0010\u0003\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u0004\u0018\u00010\u00068\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0002\n\u0000R\u0012\u0010\u0007\u001a\u00020\b8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0002\n\u0000R\u0012\u0010\t\u001a\u00020\n8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0002\n\u0000R\u0012\u0010\u000b\u001a\u00020\f8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0012\u0010\u000f\u001a\u00020\b8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0018"}, d2={"Lokio/Buffer$UnsafeCursor;", "Ljava/io/Closeable;", "()V", "buffer", "Lokio/Buffer;", "data", "", "end", "", "offset", "", "readWrite", "", "segment", "Lokio/Segment;", "start", "close", "", "expandBuffer", "minByteCount", "next", "resizeBuffer", "newSize", "seek", "okio"})
    public static final class UnsafeCursor
    implements Closeable {
        @JvmField
        @Nullable
        public Buffer buffer;
        @JvmField
        public boolean readWrite;
        private Segment segment;
        @JvmField
        public long offset = -1L;
        @JvmField
        @Nullable
        public byte[] data;
        @JvmField
        public int start = -1;
        @JvmField
        public int end = -1;

        public final int next() {
            Buffer buffer = this.buffer;
            Intrinsics.checkNotNull(buffer);
            boolean bl = this.offset != buffer.size();
            boolean bl2 = false;
            boolean bl3 = false;
            if (!bl) {
                boolean bl4 = false;
                String string = "no more bytes";
                throw (Throwable)new IllegalStateException(string.toString());
            }
            return this.offset == -1L ? this.seek(0L) : this.seek(this.offset + (long)(this.end - this.start));
        }

        public final int seek(long offset) {
            Object object = this.buffer;
            boolean bl = false;
            boolean bl2 = false;
            if (object == null) {
                boolean bl3 = false;
                String string = "not attached to a buffer";
                throw (Throwable)new IllegalStateException(string.toString());
            }
            Buffer buffer = object;
            if (offset < (long)-1 || offset > buffer.size()) {
                object = StringCompanionObject.INSTANCE;
                String string = "offset=%s > size=%s";
                Object[] objectArray = new Object[]{offset, buffer.size()};
                boolean bl4 = false;
                String string2 = String.format(string, Arrays.copyOf(objectArray, objectArray.length));
                Intrinsics.checkNotNullExpressionValue(string2, "java.lang.String.format(format, *args)");
                throw (Throwable)new ArrayIndexOutOfBoundsException(string2);
            }
            if (offset == -1L || offset == buffer.size()) {
                this.segment = null;
                this.offset = offset;
                this.data = null;
                this.start = -1;
                this.end = -1;
                return -1;
            }
            long min2 = 0L;
            long max = buffer.size();
            Segment head = buffer.head;
            Segment tail = buffer.head;
            if (this.segment != null) {
                Segment segment = this.segment;
                Intrinsics.checkNotNull(segment);
                long segmentOffset = this.offset - (long)(this.start - segment.pos);
                if (segmentOffset > offset) {
                    max = segmentOffset;
                    tail = this.segment;
                } else {
                    min2 = segmentOffset;
                    head = this.segment;
                }
            }
            Segment next = null;
            long nextOffset = 0L;
            if (max - offset > offset - min2) {
                next = head;
                nextOffset = min2;
                while (true) {
                    Segment segment = next;
                    Intrinsics.checkNotNull(segment);
                    if (offset >= nextOffset + (long)(segment.limit - next.pos)) {
                        nextOffset += (long)(next.limit - next.pos);
                        next = next.next;
                        continue;
                    }
                    break;
                }
            } else {
                Segment segment;
                next = tail;
                for (nextOffset = max; nextOffset > offset; nextOffset -= (long)(segment.limit - next.pos)) {
                    Segment segment2 = next;
                    Intrinsics.checkNotNull(segment2);
                    segment = next = segment2.prev;
                    Intrinsics.checkNotNull(segment);
                }
            }
            if (this.readWrite) {
                Segment segment = next;
                Intrinsics.checkNotNull(segment);
                if (segment.shared) {
                    Segment unsharedNext = next.unsharedCopy();
                    if (buffer.head == next) {
                        buffer.head = unsharedNext;
                    }
                    next = next.push(unsharedNext);
                    Segment segment3 = next.prev;
                    Intrinsics.checkNotNull(segment3);
                    segment3.pop();
                }
            }
            this.segment = next;
            this.offset = offset;
            Segment segment = next;
            Intrinsics.checkNotNull(segment);
            this.data = segment.data;
            this.start = next.pos + (int)(offset - nextOffset);
            this.end = next.limit;
            return this.end - this.start;
        }

        public final long resizeBuffer(long newSize) {
            Buffer buffer = this.buffer;
            boolean bl = false;
            boolean bl2 = false;
            if (buffer == null) {
                boolean bl3 = false;
                String string = "not attached to a buffer";
                throw (Throwable)new IllegalStateException(string.toString());
            }
            Buffer buffer2 = buffer;
            boolean bl4 = this.readWrite;
            bl = false;
            bl2 = false;
            if (!bl4) {
                boolean bl5 = false;
                String string = "resizeBuffer() only permitted for read/write buffers";
                throw (Throwable)new IllegalStateException(string.toString());
            }
            long oldSize = buffer2.size();
            if (newSize <= oldSize) {
                int tailSize;
                bl2 = newSize >= 0L;
                boolean bl5 = false;
                boolean bl6 = false;
                if (!bl2) {
                    boolean bl7 = false;
                    String string = "newSize < 0: " + newSize;
                    throw (Throwable)new IllegalArgumentException(string.toString());
                }
                for (long bytesToSubtract = oldSize - newSize; bytesToSubtract > 0L; bytesToSubtract -= (long)tailSize) {
                    Segment tail;
                    Segment segment = buffer2.head;
                    Intrinsics.checkNotNull(segment);
                    Segment segment2 = tail = segment.prev;
                    Intrinsics.checkNotNull(segment2);
                    tailSize = segment2.limit - tail.pos;
                    if ((long)tailSize <= bytesToSubtract) {
                        buffer2.head = tail.pop();
                        SegmentPool.recycle(tail);
                        continue;
                    }
                    tail.limit -= (int)bytesToSubtract;
                    break;
                }
                this.segment = null;
                this.offset = newSize;
                this.data = null;
                this.start = -1;
                this.end = -1;
            } else if (newSize > oldSize) {
                int segmentBytesToAdd;
                boolean needsToSeek = true;
                for (long bytesToAdd = newSize - oldSize; bytesToAdd > 0L; bytesToAdd -= (long)segmentBytesToAdd) {
                    Segment tail = buffer2.writableSegment$okio(1);
                    int b$iv = 8192 - tail.limit;
                    boolean $i$f$minOf = false;
                    long l = b$iv;
                    boolean bl8 = false;
                    segmentBytesToAdd = (int)Math.min(bytesToAdd, l);
                    tail.limit += segmentBytesToAdd;
                    if (!needsToSeek) continue;
                    this.segment = tail;
                    this.offset = oldSize;
                    this.data = tail.data;
                    this.start = tail.limit - segmentBytesToAdd;
                    this.end = tail.limit;
                    needsToSeek = false;
                }
            }
            buffer2.setSize$okio(newSize);
            return oldSize;
        }

        public final long expandBuffer(int minByteCount) {
            boolean bl = minByteCount > 0;
            boolean bl2 = false;
            boolean bl3 = false;
            if (!bl) {
                boolean bl4 = false;
                String string = "minByteCount <= 0: " + minByteCount;
                throw (Throwable)new IllegalArgumentException(string.toString());
            }
            bl = minByteCount <= 8192;
            bl2 = false;
            bl3 = false;
            if (!bl) {
                boolean bl52 = false;
                String string = "minByteCount > Segment.SIZE: " + minByteCount;
                throw (Throwable)new IllegalArgumentException(string.toString());
            }
            Buffer buffer = this.buffer;
            bl3 = false;
            boolean bl52 = false;
            if (buffer == null) {
                boolean bl6 = false;
                String bl52 = "not attached to a buffer";
                throw (Throwable)new IllegalStateException(bl52.toString());
            }
            Buffer buffer2 = buffer;
            boolean bl7 = this.readWrite;
            bl3 = false;
            bl52 = false;
            if (!bl7) {
                boolean bl8 = false;
                String bl52 = "expandBuffer() only permitted for read/write buffers";
                throw (Throwable)new IllegalStateException(bl52.toString());
            }
            long oldSize = buffer2.size();
            Segment tail = buffer2.writableSegment$okio(minByteCount);
            int result = 8192 - tail.limit;
            tail.limit = 8192;
            buffer2.setSize$okio(oldSize + (long)result);
            this.segment = tail;
            this.offset = oldSize;
            this.data = tail.data;
            this.start = 8192 - result;
            this.end = 8192;
            return result;
        }

        @Override
        public void close() {
            boolean bl = this.buffer != null;
            boolean bl2 = false;
            boolean bl3 = false;
            if (!bl) {
                boolean bl4 = false;
                String string = "not attached to a buffer";
                throw (Throwable)new IllegalStateException(string.toString());
            }
            this.buffer = null;
            this.segment = null;
            this.offset = -1L;
            this.data = null;
            this.start = -1;
            this.end = -1;
        }
    }
}

