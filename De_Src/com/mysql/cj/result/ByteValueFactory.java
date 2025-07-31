/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.result;

import com.mysql.cj.result.DefaultValueFactory;
import java.math.BigDecimal;
import java.math.BigInteger;

public class ByteValueFactory
extends DefaultValueFactory<Byte> {
    @Override
    public Byte createFromBigInteger(BigInteger i) {
        return (byte)i.intValue();
    }

    @Override
    public Byte createFromLong(long l) {
        return (byte)l;
    }

    @Override
    public Byte createFromBigDecimal(BigDecimal d) {
        return (byte)d.longValue();
    }

    @Override
    public Byte createFromDouble(double d) {
        return (byte)d;
    }

    @Override
    public Byte createFromBit(byte[] bytes, int offset, int length) {
        return bytes[offset + length - 1];
    }

    @Override
    public Byte createFromNull() {
        return (byte)0;
    }

    @Override
    public String getTargetTypeName() {
        return Byte.class.getName();
    }
}

