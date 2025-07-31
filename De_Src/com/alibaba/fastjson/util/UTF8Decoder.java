/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.util;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;

public class UTF8Decoder
extends CharsetDecoder {
    private static final Charset charset = Charset.forName("UTF-8");

    public UTF8Decoder() {
        super(charset, 1.0f, 1.0f);
    }

    private static boolean isNotContinuation(int b) {
        return (b & 0xC0) != 128;
    }

    private static boolean isMalformed2(int b1, int b2) {
        return (b1 & 0x1E) == 0 || (b2 & 0xC0) != 128;
    }

    private static boolean isMalformed3(int b1, int b2, int b3) {
        return b1 == -32 && (b2 & 0xE0) == 128 || (b2 & 0xC0) != 128 || (b3 & 0xC0) != 128;
    }

    private static boolean isMalformed4(int b2, int b3, int b4) {
        return (b2 & 0xC0) != 128 || (b3 & 0xC0) != 128 || (b4 & 0xC0) != 128;
    }

    private static CoderResult lookupN(ByteBuffer src, int n) {
        for (int i = 1; i < n; ++i) {
            if (!UTF8Decoder.isNotContinuation(src.get())) continue;
            return CoderResult.malformedForLength(i);
        }
        return CoderResult.malformedForLength(n);
    }

    public static CoderResult malformedN(ByteBuffer src, int nb) {
        switch (nb) {
            case 1: {
                byte b1 = src.get();
                if (b1 >> 2 == -2) {
                    if (src.remaining() < 4) {
                        return CoderResult.UNDERFLOW;
                    }
                    return UTF8Decoder.lookupN(src, 5);
                }
                if (b1 >> 1 == -2) {
                    if (src.remaining() < 5) {
                        return CoderResult.UNDERFLOW;
                    }
                    return UTF8Decoder.lookupN(src, 6);
                }
                return CoderResult.malformedForLength(1);
            }
            case 2: {
                return CoderResult.malformedForLength(1);
            }
            case 3: {
                byte b1 = src.get();
                byte b2 = src.get();
                return CoderResult.malformedForLength(b1 == -32 && (b2 & 0xE0) == 128 || UTF8Decoder.isNotContinuation(b2) ? 1 : 2);
            }
            case 4: {
                int b1 = src.get() & 0xFF;
                int b2 = src.get() & 0xFF;
                if (b1 > 244 || b1 == 240 && (b2 < 144 || b2 > 191) || b1 == 244 && (b2 & 0xF0) != 128 || UTF8Decoder.isNotContinuation(b2)) {
                    return CoderResult.malformedForLength(1);
                }
                if (UTF8Decoder.isNotContinuation(src.get())) {
                    return CoderResult.malformedForLength(2);
                }
                return CoderResult.malformedForLength(3);
            }
        }
        throw new IllegalStateException();
    }

    private static CoderResult malformed(ByteBuffer src, int sp, CharBuffer dst, int dp, int nb) {
        src.position(sp - src.arrayOffset());
        CoderResult cr = UTF8Decoder.malformedN(src, nb);
        src.position(sp);
        dst.position(dp);
        return cr;
    }

    private static CoderResult xflow(Buffer src, int sp, int sl, Buffer dst, int dp, int nb) {
        src.position(sp);
        dst.position(dp);
        return nb == 0 || sl - sp < nb ? CoderResult.UNDERFLOW : CoderResult.OVERFLOW;
    }

    private CoderResult decodeArrayLoop(ByteBuffer src, CharBuffer dst) {
        byte[] srcArray = src.array();
        int srcPosition = src.arrayOffset() + src.position();
        int srcLength = src.arrayOffset() + src.limit();
        char[] destArray = dst.array();
        int destPosition = dst.arrayOffset() + dst.position();
        int destLength = dst.arrayOffset() + dst.limit();
        int destLengthASCII = destPosition + Math.min(srcLength - srcPosition, destLength - destPosition);
        while (destPosition < destLengthASCII && srcArray[srcPosition] >= 0) {
            destArray[destPosition++] = (char)srcArray[srcPosition++];
        }
        while (srcPosition < srcLength) {
            byte b3;
            byte b2;
            byte b1 = srcArray[srcPosition];
            if (b1 >= 0) {
                if (destPosition >= destLength) {
                    return UTF8Decoder.xflow(src, srcPosition, srcLength, dst, destPosition, 1);
                }
                destArray[destPosition++] = (char)b1;
                ++srcPosition;
                continue;
            }
            if (b1 >> 5 == -2) {
                if (srcLength - srcPosition < 2 || destPosition >= destLength) {
                    return UTF8Decoder.xflow(src, srcPosition, srcLength, dst, destPosition, 2);
                }
                b2 = srcArray[srcPosition + 1];
                if (UTF8Decoder.isMalformed2(b1, b2)) {
                    return UTF8Decoder.malformed(src, srcPosition, dst, destPosition, 2);
                }
                destArray[destPosition++] = (char)(b1 << 6 ^ b2 ^ 0xF80);
                srcPosition += 2;
                continue;
            }
            if (b1 >> 4 == -2) {
                if (srcLength - srcPosition < 3 || destPosition >= destLength) {
                    return UTF8Decoder.xflow(src, srcPosition, srcLength, dst, destPosition, 3);
                }
                b2 = srcArray[srcPosition + 1];
                b3 = srcArray[srcPosition + 2];
                if (UTF8Decoder.isMalformed3(b1, b2, b3)) {
                    return UTF8Decoder.malformed(src, srcPosition, dst, destPosition, 3);
                }
                destArray[destPosition++] = (char)(b1 << 12 ^ b2 << 6 ^ b3 ^ 0x1F80);
                srcPosition += 3;
                continue;
            }
            if (b1 >> 3 == -2) {
                if (srcLength - srcPosition < 4 || destLength - destPosition < 2) {
                    return UTF8Decoder.xflow(src, srcPosition, srcLength, dst, destPosition, 4);
                }
                b2 = srcArray[srcPosition + 1];
                b3 = srcArray[srcPosition + 2];
                byte b4 = srcArray[srcPosition + 3];
                int uc = (b1 & 7) << 18 | (b2 & 0x3F) << 12 | (b3 & 0x3F) << 6 | b4 & 0x3F;
                if (UTF8Decoder.isMalformed4(b2, b3, b4) || uc < 65536 || uc > 0x10FFFF) {
                    return UTF8Decoder.malformed(src, srcPosition, dst, destPosition, 4);
                }
                destArray[destPosition++] = (char)(0xD800 | uc - 65536 >> 10 & 0x3FF);
                destArray[destPosition++] = (char)(0xDC00 | uc - 65536 & 0x3FF);
                srcPosition += 4;
                continue;
            }
            return UTF8Decoder.malformed(src, srcPosition, dst, destPosition, 1);
        }
        return UTF8Decoder.xflow(src, srcPosition, srcLength, dst, destPosition, 0);
    }

    protected CoderResult decodeLoop(ByteBuffer src, CharBuffer dst) {
        return this.decodeArrayLoop(src, dst);
    }
}

