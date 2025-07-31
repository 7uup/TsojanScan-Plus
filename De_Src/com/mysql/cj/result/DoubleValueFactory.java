/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.result;

import com.mysql.cj.result.DefaultValueFactory;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.ByteBuffer;

public class DoubleValueFactory
extends DefaultValueFactory<Double> {
    @Override
    public Double createFromBigInteger(BigInteger i) {
        return i.doubleValue();
    }

    @Override
    public Double createFromLong(long l) {
        return l;
    }

    @Override
    public Double createFromBigDecimal(BigDecimal d) {
        return d.doubleValue();
    }

    @Override
    public Double createFromDouble(double d) {
        return d;
    }

    @Override
    public Double createFromBit(byte[] bytes, int offset, int length) {
        return new BigInteger(ByteBuffer.allocate(length + 1).put((byte)0).put(bytes, offset, length).array()).doubleValue();
    }

    @Override
    public Double createFromNull() {
        return 0.0;
    }

    @Override
    public String getTargetTypeName() {
        return Double.class.getName();
    }
}

