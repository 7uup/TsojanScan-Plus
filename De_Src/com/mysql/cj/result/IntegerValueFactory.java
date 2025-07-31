/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.result;

import com.mysql.cj.result.DefaultValueFactory;
import com.mysql.cj.util.DataTypeUtil;
import java.math.BigDecimal;
import java.math.BigInteger;

public class IntegerValueFactory
extends DefaultValueFactory<Integer> {
    @Override
    public Integer createFromBigInteger(BigInteger i) {
        return i.intValue();
    }

    @Override
    public Integer createFromLong(long l) {
        return (int)l;
    }

    @Override
    public Integer createFromBigDecimal(BigDecimal d) {
        return (int)d.longValue();
    }

    @Override
    public Integer createFromDouble(double d) {
        return (int)d;
    }

    @Override
    public Integer createFromBit(byte[] bytes, int offset, int length) {
        return (int)DataTypeUtil.bitToLong(bytes, offset, length);
    }

    @Override
    public Integer createFromNull() {
        return 0;
    }

    @Override
    public String getTargetTypeName() {
        return Integer.class.getName();
    }
}

