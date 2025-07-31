/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.util;

import java.util.Arrays;

public class Base64 {
    public static final char[] CA = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();
    public static final int[] IA = new int[256];

    public static byte[] decodeFast(char[] chars, int offset, int charsLen) {
        int sIx;
        if (charsLen == 0) {
            return new byte[0];
        }
        int eIx = offset + charsLen - 1;
        for (sIx = offset; sIx < eIx && IA[chars[sIx]] < 0; ++sIx) {
        }
        while (eIx > 0 && IA[chars[eIx]] < 0) {
            --eIx;
        }
        int pad = chars[eIx] == '=' ? (chars[eIx - 1] == '=' ? 2 : 1) : 0;
        int cCnt = eIx - sIx + 1;
        int sepCnt = charsLen > 76 ? (chars[76] == '\r' ? cCnt / 78 : 0) << 1 : 0;
        int len = ((cCnt - sepCnt) * 6 >> 3) - pad;
        byte[] bytes = new byte[len];
        int d = 0;
        int cc = 0;
        int eLen = len / 3 * 3;
        while (d < eLen) {
            int i = IA[chars[sIx++]] << 18 | IA[chars[sIx++]] << 12 | IA[chars[sIx++]] << 6 | IA[chars[sIx++]];
            bytes[d++] = (byte)(i >> 16);
            bytes[d++] = (byte)(i >> 8);
            bytes[d++] = (byte)i;
            if (sepCnt <= 0 || ++cc != 19) continue;
            sIx += 2;
            cc = 0;
        }
        if (d < len) {
            int i = 0;
            int j = 0;
            while (sIx <= eIx - pad) {
                i |= IA[chars[sIx++]] << 18 - j * 6;
                ++j;
            }
            int r = 16;
            while (d < len) {
                bytes[d++] = (byte)(i >> r);
                r -= 8;
            }
        }
        return bytes;
    }

    public static byte[] decodeFast(String chars, int offset, int charsLen) {
        int sIx;
        if (charsLen == 0) {
            return new byte[0];
        }
        int eIx = offset + charsLen - 1;
        for (sIx = offset; sIx < eIx && IA[chars.charAt(sIx)] < 0; ++sIx) {
        }
        while (eIx > 0 && IA[chars.charAt(eIx)] < 0) {
            --eIx;
        }
        int pad = chars.charAt(eIx) == '=' ? (chars.charAt(eIx - 1) == '=' ? 2 : 1) : 0;
        int cCnt = eIx - sIx + 1;
        int sepCnt = charsLen > 76 ? (chars.charAt(76) == '\r' ? cCnt / 78 : 0) << 1 : 0;
        int len = ((cCnt - sepCnt) * 6 >> 3) - pad;
        byte[] bytes = new byte[len];
        int d = 0;
        int cc = 0;
        int eLen = len / 3 * 3;
        while (d < eLen) {
            int i = IA[chars.charAt(sIx++)] << 18 | IA[chars.charAt(sIx++)] << 12 | IA[chars.charAt(sIx++)] << 6 | IA[chars.charAt(sIx++)];
            bytes[d++] = (byte)(i >> 16);
            bytes[d++] = (byte)(i >> 8);
            bytes[d++] = (byte)i;
            if (sepCnt <= 0 || ++cc != 19) continue;
            sIx += 2;
            cc = 0;
        }
        if (d < len) {
            int i = 0;
            int j = 0;
            while (sIx <= eIx - pad) {
                i |= IA[chars.charAt(sIx++)] << 18 - j * 6;
                ++j;
            }
            int r = 16;
            while (d < len) {
                bytes[d++] = (byte)(i >> r);
                r -= 8;
            }
        }
        return bytes;
    }

    public static byte[] decodeFast(String s2) {
        int sIx;
        int sLen = s2.length();
        if (sLen == 0) {
            return new byte[0];
        }
        int eIx = sLen - 1;
        for (sIx = 0; sIx < eIx && IA[s2.charAt(sIx) & 0xFF] < 0; ++sIx) {
        }
        while (eIx > 0 && IA[s2.charAt(eIx) & 0xFF] < 0) {
            --eIx;
        }
        int pad = s2.charAt(eIx) == '=' ? (s2.charAt(eIx - 1) == '=' ? 2 : 1) : 0;
        int cCnt = eIx - sIx + 1;
        int sepCnt = sLen > 76 ? (s2.charAt(76) == '\r' ? cCnt / 78 : 0) << 1 : 0;
        int len = ((cCnt - sepCnt) * 6 >> 3) - pad;
        byte[] dArr = new byte[len];
        int d = 0;
        int cc = 0;
        int eLen = len / 3 * 3;
        while (d < eLen) {
            int i = IA[s2.charAt(sIx++)] << 18 | IA[s2.charAt(sIx++)] << 12 | IA[s2.charAt(sIx++)] << 6 | IA[s2.charAt(sIx++)];
            dArr[d++] = (byte)(i >> 16);
            dArr[d++] = (byte)(i >> 8);
            dArr[d++] = (byte)i;
            if (sepCnt <= 0 || ++cc != 19) continue;
            sIx += 2;
            cc = 0;
        }
        if (d < len) {
            int i = 0;
            int j = 0;
            while (sIx <= eIx - pad) {
                i |= IA[s2.charAt(sIx++)] << 18 - j * 6;
                ++j;
            }
            int r = 16;
            while (d < len) {
                dArr[d++] = (byte)(i >> r);
                r -= 8;
            }
        }
        return dArr;
    }

    static {
        Arrays.fill(IA, -1);
        int iS = CA.length;
        for (int i = 0; i < iS; ++i) {
            Base64.IA[Base64.CA[i]] = i;
        }
        Base64.IA[61] = 0;
    }
}

