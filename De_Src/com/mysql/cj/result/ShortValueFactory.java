/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.result;

import com.mysql.cj.result.DefaultValueFactory;
import com.mysql.cj.util.DataTypeUtil;
import java.math.BigDecimal;
import java.math.BigInteger;

public class ShortValueFactory
extends DefaultValueFactory<Short> {
    @Override
    public Short createFromBigInteger(BigInteger i) {
        return (short)i.intValue();
    }

    @Override
    public Short createFromLong(long l) {
        return (short)l;
    }

    @Override
    public Short createFromBigDecimal(BigDecimal d) {
        return (short)d.longValue();
    }

    @Override
    public Short createFromDouble(double d) {
        return (short)d;
    }

    @Override
    public Short createFromBit(byte[] bytes, int offset, int length) {
        return this.createFromLong(DataTypeUtil.bitToLong(bytes, offset, length));
    }

    @Override
    public Short createFromNull() {
        return (short)0;
    }

    @Override
    public String getTargetTypeName() {
        return Short.class.getName();
    }
}

