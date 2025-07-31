/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package bsh.org.objectweb.asm;

final class ByteVector {
    byte[] data;
    int length;

    public ByteVector() {
        this.data = new byte[64];
    }

    public ByteVector(int initialSize) {
        this.data = new byte[initialSize];
    }

    public ByteVector put1(int b) {
        int length = this.length;
        if (length + 1 > this.data.length) {
            this.enlarge(1);
        }
        this.data[length++] = (byte)b;
        this.length = length;
        return this;
    }

    public ByteVector put11(int b1, int b2) {
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

    public ByteVector put2(int s2) {
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

    public ByteVector put4(int i) {
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

    public ByteVector put8(long l) {
        int length = this.length;
        if (length + 8 > this.data.length) {
            this.enlarge(8);
        }
        byte[] data = this.data;
        int i = (int)(l >>> 32);
        data[length++] = (byte)(i >>> 24);
        data[length++] = (byte)(i >>> 16);
        data[length++] = (byte)(i >>> 8);
        data[length++] = (byte)i;
        i = (int)l;
        data[length++] = (byte)(i >>> 24);
        data[length++] = (byte)(i >>> 16);
        data[length++] = (byte)(i >>> 8);
        data[length++] = (byte)i;
        this.length = length;
        return this;
    }

    public ByteVector putUTF(String s2) {
        int charLength = s2.length();
        int byteLength = 0;
        for (int i = 0; i < charLength; ++i) {
            char c = s2.charAt(i);
            if (c >= '\u0001' && c <= '\u007f') {
                ++byteLength;
                continue;
            }
            if (c > '\u07ff') {
                byteLength += 3;
                continue;
            }
            byteLength += 2;
        }
        if (byteLength > 65535) {
            throw new IllegalArgumentException();
        }
        int length = this.length;
        if (length + 2 + byteLength > this.data.length) {
            this.enlarge(2 + byteLength);
        }
        byte[] data = this.data;
        data[length++] = (byte)(byteLength >>> 8);
        data[length++] = (byte)byteLength;
        for (int i = 0; i < charLength; ++i) {
            char c = s2.charAt(i);
            if (c >= '\u0001' && c <= '\u007f') {
                data[length++] = (byte)c;
                continue;
            }
            if (c > '\u07ff') {
                data[length++] = (byte)(0xE0 | c >> 12 & 0xF);
                data[length++] = (byte)(0x80 | c >> 6 & 0x3F);
                data[length++] = (byte)(0x80 | c & 0x3F);
                continue;
            }
            data[length++] = (byte)(0xC0 | c >> 6 & 0x1F);
            data[length++] = (byte)(0x80 | c & 0x3F);
        }
        this.length = length;
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
        byte[] newData = new byte[Math.max(2 * this.data.length, this.length + size)];
        System.arraycopy(this.data, 0, newData, 0, this.length);
        this.data = newData;
    }
}

