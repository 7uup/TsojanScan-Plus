/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.protobuf;

import com.google.protobuf.ByteString;
import com.google.protobuf.LiteralByteString;
import com.google.protobuf.Utf8;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class Internal {
    private static final int DEFAULT_BUFFER_SIZE = 4096;
    public static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
    public static final ByteBuffer EMPTY_BYTE_BUFFER = ByteBuffer.wrap(EMPTY_BYTE_ARRAY);

    public static String stringDefaultValue(String bytes) {
        try {
            return new String(bytes.getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException("Java VM does not support a standard character set.", e);
        }
    }

    public static ByteString bytesDefaultValue(String bytes) {
        try {
            return ByteString.copyFrom(bytes.getBytes("ISO-8859-1"));
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException("Java VM does not support a standard character set.", e);
        }
    }

    public static byte[] byteArrayDefaultValue(String bytes) {
        try {
            return bytes.getBytes("ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException("Java VM does not support a standard character set.", e);
        }
    }

    public static ByteBuffer byteBufferDefaultValue(String bytes) {
        return ByteBuffer.wrap(Internal.byteArrayDefaultValue(bytes));
    }

    public static ByteBuffer copyByteBuffer(ByteBuffer source2) {
        ByteBuffer temp = source2.duplicate();
        temp.clear();
        ByteBuffer result = ByteBuffer.allocate(temp.capacity());
        result.put(temp);
        result.clear();
        return result;
    }

    public static boolean isValidUtf8(ByteString byteString) {
        return byteString.isValidUtf8();
    }

    public static boolean isValidUtf8(byte[] byteArray) {
        return Utf8.isValidUtf8(byteArray);
    }

    public static byte[] toByteArray(String value) {
        try {
            return value.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UTF-8 not supported?", e);
        }
    }

    public static String toStringUtf8(byte[] bytes) {
        try {
            return new String(bytes, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UTF-8 not supported?", e);
        }
    }

    public static int hashLong(long n) {
        return (int)(n ^ n >>> 32);
    }

    public static int hashBoolean(boolean b) {
        return b ? 1231 : 1237;
    }

    public static int hashEnum(EnumLite e) {
        return e.getNumber();
    }

    public static int hashEnumList(List<? extends EnumLite> list) {
        int hash = 1;
        for (EnumLite enumLite : list) {
            hash = 31 * hash + Internal.hashEnum(enumLite);
        }
        return hash;
    }

    public static boolean equals(List<byte[]> a, List<byte[]> b) {
        if (a.size() != b.size()) {
            return false;
        }
        for (int i = 0; i < a.size(); ++i) {
            if (Arrays.equals(a.get(i), b.get(i))) continue;
            return false;
        }
        return true;
    }

    public static int hashCode(List<byte[]> list) {
        int hash = 1;
        for (byte[] bytes : list) {
            hash = 31 * hash + Internal.hashCode(bytes);
        }
        return hash;
    }

    public static int hashCode(byte[] bytes) {
        return LiteralByteString.hashCode(bytes);
    }

    public static boolean equalsByteBuffer(ByteBuffer a, ByteBuffer b) {
        if (a.capacity() != b.capacity()) {
            return false;
        }
        return a.duplicate().clear().equals(b.duplicate().clear());
    }

    public static boolean equalsByteBuffer(List<ByteBuffer> a, List<ByteBuffer> b) {
        if (a.size() != b.size()) {
            return false;
        }
        for (int i = 0; i < a.size(); ++i) {
            if (Internal.equalsByteBuffer(a.get(i), b.get(i))) continue;
            return false;
        }
        return true;
    }

    public static int hashCodeByteBuffer(List<ByteBuffer> list) {
        int hash = 1;
        for (ByteBuffer bytes : list) {
            hash = 31 * hash + Internal.hashCodeByteBuffer(bytes);
        }
        return hash;
    }

    public static int hashCodeByteBuffer(ByteBuffer bytes) {
        if (bytes.hasArray()) {
            int h2 = LiteralByteString.hashCode(bytes.capacity(), bytes.array(), bytes.arrayOffset(), bytes.capacity());
            return h2 == 0 ? 1 : h2;
        }
        int bufferSize = bytes.capacity() > 4096 ? 4096 : bytes.capacity();
        byte[] buffer = new byte[bufferSize];
        ByteBuffer duplicated = bytes.duplicate();
        duplicated.clear();
        int h3 = bytes.capacity();
        while (duplicated.remaining() > 0) {
            int length = duplicated.remaining() <= bufferSize ? duplicated.remaining() : bufferSize;
            duplicated.get(buffer, 0, length);
            h3 = LiteralByteString.hashCode(h3, buffer, 0, length);
        }
        return h3 == 0 ? 1 : h3;
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static interface EnumLiteMap<T extends EnumLite> {
        public T findValueByNumber(int var1);
    }

    public static interface EnumLite {
        public int getNumber();
    }
}

