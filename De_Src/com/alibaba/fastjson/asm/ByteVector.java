/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.asm;

public class ByteVector {
    public byte[] data;
    public int length;

    public ByteVector() {
        this.data = new byte[64];
    }

    public ByteVector(int initialSize) {
        this.data = new byte[initialSize];
    }

    public ByteVector putByte(int b) {
        int length = this.length;
        if (length + 1 > this.data.length) {
            this.enlarge(1);
        }
        this.data[length++] = (byte)b;
        this.length = length;
        return this;
    }

    ByteVector put11(int b1, int b2) {
        int length = this.length;
        if (length + 2 > this.data.length) {
            this.enlarge(2);
        }
        byte[] data = this.data;
        data[length++] = (byte)b1;
        data[length++] = (byte)b2;
        this.length = length;
        return this;
    }

    public ByteVector putShort(int s2) {
        int length = this.length;
        if (length + 2 > this.data.length) {
            this.enlarge(2);
        }
        byte[] data = this.data;
        data[length++] = (byte)(s2 >>> 8);
        data[length++] = (byte)s2;
        this.length = length;
        return this;
    }

    public ByteVector put12(int b, int s2) {
        int length = this.length;
        if (length + 3 > this.data.length) {
            this.enlarge(3);
        }
        byte[] data = this.data;
        data[length++] = (byte)b;
        data[length++] = (byte)(s2 >>> 8);
        data[length++] = (byte)s2;
        this.length = length;
        return this;
    }

    public ByteVector putInt(int i) {
        int length = this.length;
        if (length + 4 > this.data.length) {
            this.enlarge(4);
        }
        byte[] data = this.data;
        data[length++] = (byte)(i >>> 24);
        data[length++] = (byte)(i >>> 16);
        data[length++] = (byte)(i >>> 8);
        data[length++] = (byte)i;
        this.length = length;
        return this;
    }

    public ByteVector putUTF8(String s2) {
        int len = this.length;
        int charLength = s2.length();
        if (len + 2 + charLength > this.data.length) {
            this.enlarge(2 + charLength);
        }
        byte[] data = this.data;
        data[len++] = (byte)(charLength >>> 8);
        data[len++] = (byte)charLength;
        for (int i = 0; i < charLength; ++i) {
            char c = s2.charAt(i);
            if (!(c >= '\u0001' && c <= '\u007f' || c >= '\u4e00' && c <= '\u9fff')) {
                throw new UnsupportedOperationException();
            }
            data[len++] = (byte)c;
        }
        this.length = len;
        return this;
    }

    public ByteVector putByteArray(byte[] b, int off, int len) {
        if (this.length + len > this.data.length) {
            this.enlarge(len);
        }
        if (b != null) {
            System.arraycopy(b, off, this.data, this.length, len);
        }
        this.length += len;
        return this;
    }

    private void enlarge(int size) {
        int length1 = 2 * this.data.length;
        int length2 = this.length + size;
        byte[] newData = new byte[length1 > length2 ? length1 : length2];
        System.arraycopy(this.data, 0, newData, 0, this.length);
        this.data = newData;
    }
}

