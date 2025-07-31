/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.util;

import com.alibaba.fastjson.util.UTF8Decoder;
import java.lang.ref.SoftReference;
import java.nio.charset.CharsetDecoder;

public class ThreadLocalCache {
    public static final int CHARS_CACH_INIT_SIZE = 1024;
    public static final int CHARS_CACH_INIT_SIZE_EXP = 10;
    public static final int CHARS_CACH_MAX_SIZE = 131072;
    public static final int CHARS_CACH_MAX_SIZE_EXP = 17;
    private static final ThreadLocal<SoftReference<char[]>> charsBufLocal = new ThreadLocal();
    private static final ThreadLocal<CharsetDecoder> decoderLocal = new ThreadLocal();
    public static final int BYTES_CACH_INIT_SIZE = 1024;
    public static final int BYTES_CACH_INIT_SIZE_EXP = 10;
    public static final int BYTES_CACH_MAX_SIZE = 131072;
    public static final int BYTES_CACH_MAX_SIZE_EXP = 17;
    private static final ThreadLocal<SoftReference<byte[]>> bytesBufLocal = new ThreadLocal();

    public static CharsetDecoder getUTF8Decoder() {
        CharsetDecoder decoder = decoderLocal.get();
        if (decoder == null) {
            decoder = new UTF8Decoder();
            decoderLocal.set(decoder);
        }
        return decoder;
    }

    public static void clearChars() {
        charsBufLocal.set(null);
    }

    public static char[] getChars(int length) {
        SoftReference<char[]> ref = charsBufLocal.get();
        if (ref == null) {
            return ThreadLocalCache.allocate(length);
        }
        char[] chars = ref.get();
        if (chars == null) {
            return ThreadLocalCache.allocate(length);
        }
        if (chars.length < length) {
            chars = ThreadLocalCache.allocate(length);
        }
        return chars;
    }

    private static char[] allocate(int length) {
        if (length > 131072) {
            return new char[length];
        }
        int allocateLength = ThreadLocalCache.getAllocateLengthExp(10, 17, length);
        char[] chars = new char[allocateLength];
        charsBufLocal.set(new SoftReference<char[]>(chars));
        return chars;
    }

    private static int getAllocateLengthExp(int minExp, int maxExp, int length) {
        assert (1 << maxExp >= length);
        int part = length >>> minExp;
        if (part <= 0) {
            return 1 << minExp;
        }
        return 1 << 32 - Integer.numberOfLeadingZeros(length - 1);
    }

    public static void clearBytes() {
        bytesBufLocal.set(null);
    }

    public static byte[] getBytes(int length) {
        SoftReference<byte[]> ref = bytesBufLocal.get();
        if (ref == null) {
            return ThreadLocalCache.allocateBytes(length);
        }
        byte[] bytes = ref.get();
        if (bytes == null) {
            return ThreadLocalCache.allocateBytes(length);
        }
        if (bytes.length < length) {
            bytes = ThreadLocalCache.allocateBytes(length);
        }
        return bytes;
    }

    private static byte[] allocateBytes(int length) {
        if (length > 131072) {
            return new byte[length];
        }
        int allocateLength = ThreadLocalCache.getAllocateLengthExp(10, 17, length);
        byte[] chars = new byte[allocateLength];
        bytesBufLocal.set(new SoftReference<byte[]>(chars));
        return chars;
    }
}

