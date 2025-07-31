/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.checkerframework.checker.signedness;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import org.checkerframework.checker.signedness.qual.Unsigned;

public final class SignednessUtil {
    private SignednessUtil() {
        throw new Error("Do not instantiate");
    }

    public static ByteBuffer wrapUnsigned(@Unsigned byte[] array) {
        return ByteBuffer.wrap(array);
    }

    public static ByteBuffer wrapUnsigned(@Unsigned byte[] array, int offset, int length) {
        return ByteBuffer.wrap(array, offset, length);
    }

    public static @Unsigned int getUnsignedInt(ByteBuffer b) {
        return b.getInt();
    }

    public static @Unsigned short getUnsignedShort(ByteBuffer b) {
        return b.getShort();
    }

    public static @Unsigned byte getUnsigned(ByteBuffer b) {
        return b.get();
    }

    public static @Unsigned byte getUnsigned(ByteBuffer b, int i) {
        return b.get(i);
    }

    public static ByteBuffer getUnsigned(ByteBuffer b, byte[] bs, int i, int l) {
        return b.get(bs, i, l);
    }

    public static ByteBuffer putUnsigned(ByteBuffer b, @Unsigned byte ubyte) {
        return b.put(ubyte);
    }

    public static ByteBuffer putUnsigned(ByteBuffer b, int i, @Unsigned byte ubyte) {
        return b.put(i, ubyte);
    }

    public static IntBuffer putUnsigned(IntBuffer b, @Unsigned int uint) {
        return b.put(uint);
    }

    public static IntBuffer putUnsigned(IntBuffer b, int i, @Unsigned int uint) {
        return b.put(i, uint);
    }

    public static IntBuffer putUnsigned(IntBuffer b, @Unsigned int[] uints) {
        return b.put(uints);
    }

    public static IntBuffer putUnsigned(IntBuffer b, @Unsigned int[] uints, int i, int l) {
        return b.put(uints, i, l);
    }

    public static @Unsigned int getUnsigned(IntBuffer b, int i) {
        return b.get(i);
    }

    public static ByteBuffer putUnsignedShort(ByteBuffer b, @Unsigned short ushort) {
        return b.putShort(ushort);
    }

    public static ByteBuffer putUnsignedShort(ByteBuffer b, int i, @Unsigned short ushort) {
        return b.putShort(i, ushort);
    }

    public static ByteBuffer putUnsignedInt(ByteBuffer b, @Unsigned int uint) {
        return b.putInt(uint);
    }

    public static ByteBuffer putUnsignedInt(ByteBuffer b, int i, @Unsigned int uint) {
        return b.putInt(i, uint);
    }

    public static ByteBuffer putUnsignedLong(ByteBuffer b, int i, @Unsigned long ulong) {
        return b.putLong(i, ulong);
    }

    @Deprecated
    public static @Unsigned byte readUnsignedByte(RandomAccessFile f) throws IOException {
        return f.readByte();
    }

    public static @Unsigned char readUnsignedChar(RandomAccessFile f) throws IOException {
        return f.readChar();
    }

    @Deprecated
    public static @Unsigned short readUnsignedShort(RandomAccessFile f) throws IOException {
        return f.readShort();
    }

    public static @Unsigned int readUnsignedInt(RandomAccessFile f) throws IOException {
        return f.readInt();
    }

    public static @Unsigned long readUnsignedLong(RandomAccessFile f) throws IOException {
        return f.readLong();
    }

    public static int readUnsigned(RandomAccessFile f, @Unsigned byte[] b, int off, int len) throws IOException {
        return f.read(b, off, len);
    }

    public static void readFullyUnsigned(RandomAccessFile f, @Unsigned byte[] b) throws IOException {
        f.readFully(b);
    }

    public static void writeUnsigned(RandomAccessFile f, @Unsigned byte[] bs, int off, int len) throws IOException {
        f.write(bs, off, len);
    }

    public static void writeUnsignedByte(RandomAccessFile f, @Unsigned byte b) throws IOException {
        f.writeByte(SignednessUtil.toUnsignedInt(b));
    }

    public static void writeUnsignedChar(RandomAccessFile f, @Unsigned char c) throws IOException {
        f.writeChar(SignednessUtil.toUnsignedInt(c));
    }

    public static void writeUnsignedShort(RandomAccessFile f, @Unsigned short s2) throws IOException {
        f.writeShort(SignednessUtil.toUnsignedInt(s2));
    }

    public static void writeUnsignedInt(RandomAccessFile f, @Unsigned int i) throws IOException {
        f.writeInt(i);
    }

    public static void writeUnsignedLong(RandomAccessFile f, @Unsigned long l) throws IOException {
        f.writeLong(l);
    }

    public static void getUnsigned(ByteBuffer b, @Unsigned byte[] bs) {
        b.get(bs);
    }

    @Deprecated
    public static int compareUnsigned(@Unsigned long x, @Unsigned long y) {
        return Long.compare(x + Long.MIN_VALUE, y + Long.MIN_VALUE);
    }

    @Deprecated
    public static int compareUnsigned(@Unsigned int x, @Unsigned int y) {
        return Integer.compare(x + Integer.MIN_VALUE, y + Integer.MIN_VALUE);
    }

    public static int compareUnsigned(@Unsigned short x, @Unsigned short y) {
        return SignednessUtil.compareUnsigned(SignednessUtil.toUnsignedInt(x), SignednessUtil.toUnsignedInt(y));
    }

    public static int compareUnsigned(@Unsigned byte x, @Unsigned byte y) {
        return SignednessUtil.compareUnsigned(SignednessUtil.toUnsignedInt(x), SignednessUtil.toUnsignedInt(y));
    }

    @Deprecated
    public static String toUnsignedString(@Unsigned long l) {
        return SignednessUtil.toUnsignedBigInteger(l).toString();
    }

    @Deprecated
    public static String toUnsignedString(@Unsigned long l, int radix) {
        return SignednessUtil.toUnsignedBigInteger(l).toString(radix);
    }

    @Deprecated
    public static String toUnsignedString(@Unsigned int i) {
        return Long.toString(SignednessUtil.toUnsignedLong(i));
    }

    @Deprecated
    public static String toUnsignedString(@Unsigned int i, int radix) {
        return Long.toString(SignednessUtil.toUnsignedLong(i), radix);
    }

    public static String toUnsignedString(@Unsigned short s2) {
        return Long.toString(SignednessUtil.toUnsignedLong(s2));
    }

    public static String toUnsignedString(@Unsigned short s2, int radix) {
        return Long.toString(SignednessUtil.toUnsignedLong(s2), radix);
    }

    public static String toUnsignedString(@Unsigned byte b) {
        return Long.toString(SignednessUtil.toUnsignedLong(b));
    }

    public static String toUnsignedString(@Unsigned byte b, int radix) {
        return Long.toString(SignednessUtil.toUnsignedLong(b), radix);
    }

    private static @Unsigned BigInteger toUnsignedBigInteger(@Unsigned long l) {
        if (l >= 0L) {
            return BigInteger.valueOf(l);
        }
        int upper = (int)(l >>> 32);
        int lower = (int)l;
        return BigInteger.valueOf(SignednessUtil.toUnsignedLong(upper)).shiftLeft(32).add(BigInteger.valueOf(SignednessUtil.toUnsignedLong(lower)));
    }

    @Deprecated
    public static @Unsigned long toUnsignedLong(@Unsigned int i) {
        return (long)i & 0xFFFFFFFFL;
    }

    @Deprecated
    public static @Unsigned long toUnsignedLong(@Unsigned short s2) {
        return (long)s2 & 0xFFFFL;
    }

    @Deprecated
    public static @Unsigned int toUnsignedInt(@Unsigned short s2) {
        return s2 & 0xFFFF;
    }

    @Deprecated
    public static @Unsigned long toUnsignedLong(@Unsigned byte b) {
        return (long)b & 0xFFL;
    }

    @Deprecated
    public static @Unsigned int toUnsignedInt(@Unsigned byte b) {
        return b & 0xFF;
    }

    public static @Unsigned short toUnsignedShort(@Unsigned byte b) {
        return (short)(b & 0xFF);
    }

    public static @Unsigned long toUnsignedLong(@Unsigned char c) {
        return (long)c & 0xFFL;
    }

    public static @Unsigned int toUnsignedInt(@Unsigned char c) {
        return c & 0xFF;
    }

    public static @Unsigned short toUnsignedShort(@Unsigned char c) {
        return (short)(c & 0xFF);
    }

    public static float toFloat(@Unsigned byte b) {
        return SignednessUtil.toUnsignedBigInteger(SignednessUtil.toUnsignedLong(b)).floatValue();
    }

    public static float toFloat(@Unsigned short s2) {
        return SignednessUtil.toUnsignedBigInteger(SignednessUtil.toUnsignedLong(s2)).floatValue();
    }

    public static float toFloat(@Unsigned int i) {
        return SignednessUtil.toUnsignedBigInteger(SignednessUtil.toUnsignedLong(i)).floatValue();
    }

    public static float toFloat(@Unsigned long l) {
        return SignednessUtil.toUnsignedBigInteger(l).floatValue();
    }

    public static double toDouble(@Unsigned byte b) {
        return SignednessUtil.toUnsignedBigInteger(SignednessUtil.toUnsignedLong(b)).doubleValue();
    }

    public static double toDouble(@Unsigned short s2) {
        return SignednessUtil.toUnsignedBigInteger(SignednessUtil.toUnsignedLong(s2)).doubleValue();
    }

    public static double toDouble(@Unsigned int i) {
        return SignednessUtil.toUnsignedBigInteger(SignednessUtil.toUnsignedLong(i)).doubleValue();
    }

    public static double toDouble(@Unsigned long l) {
        return SignednessUtil.toUnsignedBigInteger(l).doubleValue();
    }

    public static @Unsigned byte byteFromFloat(float f) {
        assert (f >= 0.0f);
        return (byte)f;
    }

    public static @Unsigned short shortFromFloat(float f) {
        assert (f >= 0.0f);
        return (short)f;
    }

    public static @Unsigned int intFromFloat(float f) {
        assert (f >= 0.0f);
        return (int)f;
    }

    public static @Unsigned long longFromFloat(float f) {
        assert (f >= 0.0f);
        return (long)f;
    }

    public static @Unsigned byte byteFromDouble(double d) {
        assert (d >= 0.0);
        return (byte)d;
    }

    public static @Unsigned short shortFromDouble(double d) {
        assert (d >= 0.0);
        return (short)d;
    }

    public static @Unsigned int intFromDouble(double d) {
        assert (d >= 0.0);
        return (int)d;
    }

    public static @Unsigned long longFromDouble(double d) {
        assert (d >= 0.0);
        return (long)d;
    }
}

