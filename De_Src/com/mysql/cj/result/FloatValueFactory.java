/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.result;

import com.mysql.cj.result.DefaultValueFactory;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.ByteBuffer;

public class FloatValueFactory
extends DefaultValueFactory<Float> {
    @Override
    public Float createFromBigInteger(BigInteger i) {
        return Float.valueOf((float)i.doubleValue());
    }

    @Override
    public Float createFromLong(long l) {
        return Float.valueOf(l);
    }

    @Override
    public Float createFromBigDecimal(BigDecimal d) {
        return Float.valueOf((float)d.doubleValue());
    }

    @Override
    public Float createFromDouble(double d) {
        return Float.valueOf((float)d);
    }

    @Override
    public Float createFromBit(byte[] bytes, int offset, int length) {
        return Float.valueOf(new BigInteger(ByteBuffer.allocate(length + 1).put((byte)0).put(bytes, offset, length).array()).floatValue());
    }

    @Override
    public Float createFromNull() {
        return Float.valueOf(0.0f);
    }

    @Override
    public String getTargetTypeName() {
        return Float.class.getName();
    }
}

