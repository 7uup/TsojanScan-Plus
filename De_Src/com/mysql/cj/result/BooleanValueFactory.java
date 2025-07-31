/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.result;

import com.mysql.cj.result.DefaultValueFactory;
import com.mysql.cj.util.DataTypeUtil;
import java.math.BigDecimal;
import java.math.BigInteger;

public class BooleanValueFactory
extends DefaultValueFactory<Boolean> {
    @Override
    public Boolean createFromLong(long l) {
        return l == -1L || l > 0L;
    }

    @Override
    public Boolean createFromBigInteger(BigInteger i) {
        return i.compareTo(BigInteger.valueOf(0L)) > 0 || i.compareTo(BigInteger.valueOf(-1L)) == 0;
    }

    @Override
    public Boolean createFromDouble(double d) {
        return d > 0.0 || d == -1.0;
    }

    @Override
    public Boolean createFromBigDecimal(BigDecimal d) {
        return d.compareTo(BigDecimal.valueOf(0L)) > 0 || d.compareTo(BigDecimal.valueOf(-1L)) == 0;
    }

    @Override
    public Boolean createFromBit(byte[] bytes, int offset, int length) {
        return this.createFromLong(DataTypeUtil.bitToLong(bytes, offset, length));
    }

    @Override
    public Boolean createFromNull() {
        return false;
    }

    @Override
    public String getTargetTypeName() {
        return Boolean.class.getName();
    }
}

