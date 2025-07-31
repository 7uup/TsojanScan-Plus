/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.protocol;

import com.mysql.cj.result.ValueFactory;

public interface ValueDecoder {
    public <T> T decodeDate(byte[] var1, int var2, int var3, ValueFactory<T> var4);

    public <T> T decodeTime(byte[] var1, int var2, int var3, ValueFactory<T> var4);

    public <T> T decodeTimestamp(byte[] var1, int var2, int var3, ValueFactory<T> var4);

    public <T> T decodeInt1(byte[] var1, int var2, int var3, ValueFactory<T> var4);

    public <T> T decodeUInt1(byte[] var1, int var2, int var3, ValueFactory<T> var4);

    public <T> T decodeInt2(byte[] var1, int var2, int var3, ValueFactory<T> var4);

    public <T> T decodeUInt2(byte[] var1, int var2, int var3, ValueFactory<T> var4);

    public <T> T decodeInt4(byte[] var1, int var2, int var3, ValueFactory<T> var4);

    public <T> T decodeUInt4(byte[] var1, int var2, int var3, ValueFactory<T> var4);

    public <T> T decodeInt8(byte[] var1, int var2, int var3, ValueFactory<T> var4);

    public <T> T decodeUInt8(byte[] var1, int var2, int var3, ValueFactory<T> var4);

    public <T> T decodeFloat(byte[] var1, int var2, int var3, ValueFactory<T> var4);

    public <T> T decodeDouble(byte[] var1, int var2, int var3, ValueFactory<T> var4);

    public <T> T decodeDecimal(byte[] var1, int var2, int var3, ValueFactory<T> var4);

    public <T> T decodeByteArray(byte[] var1, int var2, int var3, ValueFactory<T> var4);

    public <T> T decodeBit(byte[] var1, int var2, int var3, ValueFactory<T> var4);

    public <T> T decodeSet(byte[] var1, int var2, int var3, ValueFactory<T> var4);
}

