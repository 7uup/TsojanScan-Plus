/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.util;

public class Base64Decoder {
    private static byte[] decoderMap = new byte[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -2, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1};

    private static byte getNextValidByte(byte[] in, IntWrapper pos, int maxPos) {
        while (pos.value <= maxPos) {
            if (in[pos.value] >= 0 && decoderMap[in[pos.value]] >= 0) {
                return in[pos.value++];
            }
            ++pos.value;
        }
        return 61;
    }

    public static byte[] decode(byte[] in, int pos, int length) {
        IntWrapper offset = new IntWrapper(pos);
        byte[] sestet = new byte[4];
        int outLen = length * 3 / 4;
        byte[] octet = new byte[outLen];
        int octetId = 0;
        int maxPos = offset.value + length - 1;
        while (offset.value <= maxPos) {
            sestet[0] = decoderMap[Base64Decoder.getNextValidByte(in, offset, maxPos)];
            sestet[1] = decoderMap[Base64Decoder.getNextValidByte(in, offset, maxPos)];
            sestet[2] = decoderMap[Base64Decoder.getNextValidByte(in, offset, maxPos)];
            sestet[3] = decoderMap[Base64Decoder.getNextValidByte(in, offset, maxPos)];
            if (sestet[1] != -2) {
                octet[octetId++] = (byte)(sestet[0] << 2 | sestet[1] >>> 4);
            }
            if (sestet[2] != -2) {
                octet[octetId++] = (byte)((sestet[1] & 0xF) << 4 | sestet[2] >>> 2);
            }
            if (sestet[3] == -2) continue;
            octet[octetId++] = (byte)((sestet[2] & 3) << 6 | sestet[3]);
        }
        byte[] out = new byte[octetId];
        System.arraycopy(octet, 0, out, 0, octetId);
        return out;
    }

    public static class IntWrapper {
        public int value;

        public IntWrapper(int value) {
            this.value = value;
        }
    }
}

