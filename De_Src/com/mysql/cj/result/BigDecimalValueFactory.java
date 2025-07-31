/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.result;

import com.mysql.cj.result.DefaultValueFactory;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.ByteBuffer;

public class BigDecimalValueFactory
extends DefaultValueFactory<BigDecimal> {
    int scale;
    boolean hasScale;

    public BigDecimalValueFactory() {
    }

    public BigDecimalValueFactory(int scale) {
        this.scale = scale;
        this.hasScale = true;
    }

    private BigDecimal adjustResult(BigDecimal d) {
        if (this.hasScale) {
            try {
                return d.setScale(this.scale);
            } catch (ArithmeticException ex) {
                return d.setScale(this.scale, 4);
            }
        }
        return d;
    }

    @Override
    public BigDecimal createFromBigInteger(BigInteger i) {
        return this.adjustResult(new BigDecimal(i));
    }

    @Override
    public BigDecimal createFromLong(long l) {
        return this.adjustResult(BigDecimal.valueOf(l));
    }

    @Override
    public BigDecimal createFromBigDecimal(BigDecimal d) {
        return this.adjustResult(d);
    }

    @Override
    public BigDecimal createFromDouble(double d) {
        return this.adjustResult(BigDecimal.valueOf(d));
    }

    @Override
    public BigDecimal createFromBit(byte[] bytes, int offset, int length) {
        return new BigDecimal(new BigInteger(ByteBuffer.allocate(length + 1).put((byte)0).put(bytes, offset, length).array()));
    }

    @Override
    public String getTargetTypeName() {
        return BigDecimal.class.getName();
    }
}

