/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okio;

import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.Charset;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import okio.Buffer;
import okio.ByteString;
import okio.Options;
import okio.Sink;
import okio.Source;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\n\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u00012\u00020\u0002J\b\u0010\u0003\u001a\u00020\u0004H'J\b\u0010\u0007\u001a\u00020\bH&J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH&J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\nH&J \u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\nH&J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u0010H&J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\nH&J\u0010\u0010\u0011\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\u0010H&J\u0018\u0010\u0011\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\nH&J\b\u0010\u0013\u001a\u00020\u0014H&J\b\u0010\u0015\u001a\u00020\u0000H&J\u0018\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u0010H&J(\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0019H&J\u0010\u0010\u001b\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u001dH&J \u0010\u001b\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u0017\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0019H&J\u0010\u0010\u001e\u001a\u00020\n2\u0006\u0010\u001c\u001a\u00020\u001fH&J\b\u0010 \u001a\u00020\fH&J\b\u0010!\u001a\u00020\u001dH&J\u0010\u0010!\u001a\u00020\u001d2\u0006\u0010\u001a\u001a\u00020\nH&J\b\u0010\"\u001a\u00020\u0010H&J\u0010\u0010\"\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\nH&J\b\u0010#\u001a\u00020\nH&J\u0010\u0010$\u001a\u00020%2\u0006\u0010\u001c\u001a\u00020\u001dH&J\u0018\u0010$\u001a\u00020%2\u0006\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\nH&J\b\u0010&\u001a\u00020\nH&J\b\u0010'\u001a\u00020\u0019H&J\b\u0010(\u001a\u00020\u0019H&J\b\u0010)\u001a\u00020\nH&J\b\u0010*\u001a\u00020\nH&J\b\u0010+\u001a\u00020,H&J\b\u0010-\u001a\u00020,H&J\u0010\u0010.\u001a\u00020/2\u0006\u00100\u001a\u000201H&J\u0018\u0010.\u001a\u00020/2\u0006\u0010\u001a\u001a\u00020\n2\u0006\u00100\u001a\u000201H&J\b\u00102\u001a\u00020/H&J\u0010\u00102\u001a\u00020/2\u0006\u0010\u001a\u001a\u00020\nH&J\b\u00103\u001a\u00020\u0019H&J\n\u00104\u001a\u0004\u0018\u00010/H&J\b\u00105\u001a\u00020/H&J\u0010\u00105\u001a\u00020/2\u0006\u00106\u001a\u00020\nH&J\u0010\u00107\u001a\u00020\b2\u0006\u0010\u001a\u001a\u00020\nH&J\u0010\u00108\u001a\u00020%2\u0006\u0010\u001a\u001a\u00020\nH&J\u0010\u00109\u001a\u00020\u00192\u0006\u0010:\u001a\u00020;H&J\u0010\u0010<\u001a\u00020%2\u0006\u0010\u001a\u001a\u00020\nH&R\u0012\u0010\u0003\u001a\u00020\u0004X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006="}, d2={"Lokio/BufferedSource;", "Lokio/Source;", "Ljava/nio/channels/ReadableByteChannel;", "buffer", "Lokio/Buffer;", "getBuffer", "()Lokio/Buffer;", "exhausted", "", "indexOf", "", "b", "", "fromIndex", "toIndex", "bytes", "Lokio/ByteString;", "indexOfElement", "targetBytes", "inputStream", "Ljava/io/InputStream;", "peek", "rangeEquals", "offset", "bytesOffset", "", "byteCount", "read", "sink", "", "readAll", "Lokio/Sink;", "readByte", "readByteArray", "readByteString", "readDecimalLong", "readFully", "", "readHexadecimalUnsignedLong", "readInt", "readIntLe", "readLong", "readLongLe", "readShort", "", "readShortLe", "readString", "", "charset", "Ljava/nio/charset/Charset;", "readUtf8", "readUtf8CodePoint", "readUtf8Line", "readUtf8LineStrict", "limit", "request", "require", "select", "options", "Lokio/Options;", "skip", "okio"})
public interface BufferedSource
extends Source,
ReadableByteChannel {
    @Deprecated(message="moved to val: use getBuffer() instead", replaceWith=@ReplaceWith(imports={}, expression="buffer"), level=DeprecationLevel.WARNING)
    @NotNull
    public Buffer buffer();

    @NotNull
    public Buffer getBuffer();

    public boolean exhausted() throws IOException;

    public void require(long var1) throws IOException;

    public boolean request(long var1) throws IOException;

    public byte readByte() throws IOException;

    public short readShort() throws IOException;

    public short readShortLe() throws IOException;

    public int readInt() throws IOException;

    public int readIntLe() throws IOException;

    public long readLong() throws IOException;

    public long readLongLe() throws IOException;

    public long readDecimalLong() throws IOException;

    public long readHexadecimalUnsignedLong() throws IOException;

    public void skip(long var1) throws IOException;

    @NotNull
    public ByteString readByteString() throws IOException;

    @NotNull
    public ByteString readByteString(long var1) throws IOException;

    public int select(@NotNull Options var1) throws IOException;

    @NotNull
    public byte[] readByteArray() throws IOException;

    @NotNull
    public byte[] readByteArray(long var1) throws IOException;

    public int read(@NotNull byte[] var1) throws IOException;

    public void readFully(@NotNull byte[] var1) throws IOException;

    public int read(@NotNull byte[] var1, int var2, int var3) throws IOException;

    public void readFully(@NotNull Buffer var1, long var2) throws IOException;

    public long readAll(@NotNull Sink var1) throws IOException;

    @NotNull
    public String readUtf8() throws IOException;

    @NotNull
    public String readUtf8(long var1) throws IOException;

    @Nullable
    public String readUtf8Line() throws IOException;

    @NotNull
    public String readUtf8LineStrict() throws IOException;

    @NotNull
    public String readUtf8LineStrict(long var1) throws IOException;

    public int readUtf8CodePoint() throws IOException;

    @NotNull
    public String readString(@NotNull Charset var1) throws IOException;

    @NotNull
    public String readString(long var1, @NotNull Charset var3) throws IOException;

    public long indexOf(byte var1) throws IOException;

    public long indexOf(byte var1, long var2) throws IOException;

    public long indexOf(byte var1, long var2, long var4) throws IOException;

    public long indexOf(@NotNull ByteString var1) throws IOException;

    public long indexOf(@NotNull ByteString var1, long var2) throws IOException;

    public long indexOfElement(@NotNull ByteString var1) throws IOException;

    public long indexOfElement(@NotNull ByteString var1, long var2) throws IOException;

    public boolean rangeEquals(long var1, @NotNull ByteString var3) throws IOException;

    public boolean rangeEquals(long var1, @NotNull ByteString var3, int var4, int var5) throws IOException;

    @NotNull
    public BufferedSource peek();

    @NotNull
    public InputStream inputStream();
}

