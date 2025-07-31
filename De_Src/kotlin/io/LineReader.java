/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.io;

import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u00c0\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0014\u001a\u00020\u0004H\u0002J\u0010\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u0010H\u0002J\u0018\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u0004H\u0002J\u0018\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fJ\b\u0010 \u001a\u00020!H\u0002J\b\u0010\"\u001a\u00020!H\u0002J\u0010\u0010#\u001a\u00020!2\u0006\u0010\u001e\u001a\u00020\u001fH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0012\u0010\u0011\u001a\u00060\u0012j\u0002`\u0013X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006$"}, d2={"Lkotlin/io/LineReader;", "", "()V", "BUFFER_SIZE", "", "byteBuf", "Ljava/nio/ByteBuffer;", "bytes", "", "charBuf", "Ljava/nio/CharBuffer;", "chars", "", "decoder", "Ljava/nio/charset/CharsetDecoder;", "directEOL", "", "sb", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "compactBytes", "decode", "endOfInput", "decodeEndOfInput", "nBytes", "nChars", "readLine", "", "inputStream", "Ljava/io/InputStream;", "charset", "Ljava/nio/charset/Charset;", "resetAll", "", "trimStringBuilder", "updateCharset", "kotlin-stdlib"})
public final class LineReader {
    private static final int BUFFER_SIZE = 32;
    private static CharsetDecoder decoder;
    private static boolean directEOL;
    private static final byte[] bytes;
    private static final char[] chars;
    private static final ByteBuffer byteBuf;
    private static final CharBuffer charBuf;
    private static final StringBuilder sb;
    public static final LineReader INSTANCE;

    @Nullable
    public final synchronized String readLine(@NotNull InputStream inputStream2, @NotNull Charset charset) {
        boolean bl;
        block12: {
            block11: {
                Intrinsics.checkNotNullParameter(inputStream2, "inputStream");
                Intrinsics.checkNotNullParameter(charset, "charset");
                if (decoder == null) break block11;
                CharsetDecoder charsetDecoder = decoder;
                if (charsetDecoder == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("decoder");
                }
                if (!(Intrinsics.areEqual(charsetDecoder.charset(), charset) ^ true)) break block12;
            }
            this.updateCharset(charset);
        }
        int nBytes = 0;
        int nChars = 0;
        while (true) {
            int readByte;
            if ((readByte = inputStream2.read()) == -1) {
                CharSequence charSequence = sb;
                bl = false;
                if (charSequence.length() == 0 && nBytes == 0 && nChars == 0) {
                    return null;
                }
                nChars = this.decodeEndOfInput(nBytes, nChars);
                break;
            }
            LineReader.bytes[nBytes++] = (byte)readByte;
            if (readByte != 10 && nBytes != 32 && directEOL) continue;
            byteBuf.limit(nBytes);
            charBuf.position(nChars);
            nChars = this.decode(false);
            if (nChars > 0 && chars[nChars - 1] == '\n') {
                byteBuf.position(0);
                break;
            }
            nBytes = this.compactBytes();
        }
        if (nChars > 0 && chars[nChars - 1] == '\n' && --nChars > 0 && chars[nChars - 1] == '\r') {
            --nChars;
        }
        Object readByte = sb;
        int n = 0;
        if (readByte.length() == 0) {
            readByte = chars;
            n = 0;
            bl = false;
            return new String((char[])readByte, n, nChars);
        }
        sb.append(chars, 0, nChars);
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "sb.toString()");
        String result = string;
        if (sb.length() > 32) {
            this.trimStringBuilder();
        }
        sb.setLength(0);
        return result;
    }

    private final int decode(boolean endOfInput) {
        while (true) {
            CoderResult coderResult;
            CharsetDecoder charsetDecoder = decoder;
            if (charsetDecoder == null) {
                Intrinsics.throwUninitializedPropertyAccessException("decoder");
            }
            Intrinsics.checkNotNullExpressionValue(charsetDecoder.decode(byteBuf, charBuf, endOfInput), "decoder.decode(byteBuf, charBuf, endOfInput)");
            if (coderResult.isError()) {
                this.resetAll();
                coderResult.throwException();
            }
            int nChars = charBuf.position();
            if (!coderResult.isOverflow()) {
                return nChars;
            }
            sb.append(chars, 0, nChars - 1);
            charBuf.position(0);
            charBuf.limit(32);
            charBuf.put(chars[nChars - 1]);
        }
    }

    private final int compactBytes() {
        ByteBuffer byteBuffer = byteBuf;
        boolean bl = false;
        boolean bl2 = false;
        ByteBuffer $this$with = byteBuffer;
        boolean bl3 = false;
        $this$with.compact();
        int n = $this$with.position();
        boolean bl4 = false;
        boolean bl5 = false;
        int it = n;
        boolean bl6 = false;
        $this$with.position(0);
        return n;
    }

    private final int decodeEndOfInput(int nBytes, int nChars) {
        byteBuf.limit(nBytes);
        charBuf.position(nChars);
        int n = this.decode(true);
        boolean bl = false;
        boolean bl2 = false;
        int it = n;
        boolean bl3 = false;
        CharsetDecoder charsetDecoder = decoder;
        if (charsetDecoder == null) {
            Intrinsics.throwUninitializedPropertyAccessException("decoder");
        }
        charsetDecoder.reset();
        byteBuf.position(0);
        return n;
    }

    private final void updateCharset(Charset charset) {
        CharsetDecoder charsetDecoder = charset.newDecoder();
        Intrinsics.checkNotNullExpressionValue(charsetDecoder, "charset.newDecoder()");
        decoder = charsetDecoder;
        byteBuf.clear();
        charBuf.clear();
        byteBuf.put((byte)10);
        byteBuf.flip();
        CharsetDecoder charsetDecoder2 = decoder;
        if (charsetDecoder2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("decoder");
        }
        charsetDecoder2.decode(byteBuf, charBuf, false);
        directEOL = charBuf.position() == 1 && charBuf.get(0) == '\n';
        this.resetAll();
    }

    private final void resetAll() {
        CharsetDecoder charsetDecoder = decoder;
        if (charsetDecoder == null) {
            Intrinsics.throwUninitializedPropertyAccessException("decoder");
        }
        charsetDecoder.reset();
        byteBuf.position(0);
        sb.setLength(0);
    }

    private final void trimStringBuilder() {
        sb.setLength(32);
        sb.trimToSize();
    }

    private LineReader() {
    }

    static {
        LineReader lineReader;
        INSTANCE = lineReader = new LineReader();
        bytes = new byte[32];
        chars = new char[32];
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        Intrinsics.checkNotNullExpressionValue(byteBuffer, "ByteBuffer.wrap(bytes)");
        byteBuf = byteBuffer;
        CharBuffer charBuffer = CharBuffer.wrap(chars);
        Intrinsics.checkNotNullExpressionValue(charBuffer, "CharBuffer.wrap(chars)");
        charBuf = charBuffer;
        sb = new StringBuilder();
    }

    public static final /* synthetic */ CharsetDecoder access$getDecoder$p(LineReader $this) {
        LineReader lineReader = $this;
        CharsetDecoder charsetDecoder = decoder;
        if (charsetDecoder == null) {
            Intrinsics.throwUninitializedPropertyAccessException("decoder");
        }
        return charsetDecoder;
    }

    public static final /* synthetic */ void access$setDecoder$p(LineReader $this, CharsetDecoder charsetDecoder) {
        LineReader lineReader = $this;
        decoder = charsetDecoder;
    }
}

