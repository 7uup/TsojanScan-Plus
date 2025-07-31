/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.result;

import com.mysql.cj.result.DefaultValueFactory;
import com.mysql.cj.util.DataTypeUtil;
import java.math.BigDecimal;
import java.math.BigInteger;

public class LongValueFactory
extends DefaultValueFactory<Long> {
    @Override
    public Long createFromBigInteger(BigInteger i) {
        return i.longValue();
    }

    @Override
    public Long createFromLong(long l) {
        return l;
    }

    @Override
    public Long createFromBigDecimal(BigDecimal d) {
        return d.longValue();
    }

    @Override
    public Long createFromDouble(double d) {
        return (long)d;
    }

    @Override
    public Long createFromBit(byte[] bytes, int offset, int length) {
        return DataTypeUtil.bitToLong(bytes, offset, length);
    }

    @Override
    public Long createFromNull() {
        return 0L;
    }

    @Override
    public String getTargetTypeName() {
        return Long.class.getName();
    }
}

