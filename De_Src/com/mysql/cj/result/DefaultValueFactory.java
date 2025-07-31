/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.result;

import com.mysql.cj.Messages;
import com.mysql.cj.exceptions.DataConversionException;
import com.mysql.cj.result.ValueFactory;
import java.math.BigDecimal;
import java.math.BigInteger;

public abstract class DefaultValueFactory<T>
implements ValueFactory<T> {
    private T unsupported(String sourceType) {
        throw new DataConversionException(Messages.getString("ResultSet.UnsupportedConversion", new Object[]{sourceType, this.getTargetTypeName()}));
    }

    @Override
    public T createFromDate(int year, int month, int day) {
        return this.unsupported("DATE");
    }

    @Override
    public T createFromTime(int hours, int minutes, int seconds, int nanos) {
        return this.unsupported("TIME");
    }

    @Override
    public T createFromTimestamp(int year, int month, int day, int hours, int minutes, int seconds, int nanos) {
        return this.unsupported("TIMESTAMP");
    }

    @Override
    public T createFromLong(long l) {
        return this.unsupported("LONG");
    }

    @Override
    public T createFromBigInteger(BigInteger i) {
        return this.unsupported("BIGINT");
    }

    @Override
    public T createFromDouble(double d) {
        return this.unsupported("DOUBLE");
    }

    @Override
    public T createFromBigDecimal(BigDecimal d) {
        return this.unsupported("DECIMAL");
    }

    @Override
    public T createFromBytes(byte[] bytes, int offset, int length) {
        return this.unsupported("VARCHAR/TEXT/BLOB");
    }

    @Override
    public T createFromBit(byte[] bytes, int offset, int length) {
        return this.unsupported("BIT");
    }

    @Override
    public T createFromNull() {
        return null;
    }
}

