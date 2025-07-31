/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.util;

public class DataTypeUtil {
    public static long bitToLong(byte[] bytes, int offset, int length) {
        long valueAsLong = 0L;
        for (int i = 0; i < length; ++i) {
            valueAsLong = valueAsLong << 8 | (long)(bytes[offset + i] & 0xFF);
        }
        return valueAsLong;
    }
}

