/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.result;

import java.math.BigDecimal;
import java.math.BigInteger;

public interface ValueFactory<T> {
    public T createFromDate(int var1, int var2, int var3);

    public T createFromTime(int var1, int var2, int var3, int var4);

    public T createFromTimestamp(int var1, int var2, int var3, int var4, int var5, int var6, int var7);

    public T createFromLong(long var1);

    public T createFromBigInteger(BigInteger var1);

    public T createFromDouble(double var1);

    public T createFromBigDecimal(BigDecimal var1);

    public T createFromBytes(byte[] var1, int var2, int var3);

    public T createFromBit(byte[] var1, int var2, int var3);

    public T createFromNull();

    public String getTargetTypeName();
}

