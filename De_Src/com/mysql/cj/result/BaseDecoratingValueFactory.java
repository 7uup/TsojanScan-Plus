/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.result;

import com.mysql.cj.result.ValueFactory;
import java.math.BigDecimal;
import java.math.BigInteger;

public abstract class BaseDecoratingValueFactory<T>
implements ValueFactory<T> {
    protected ValueFactory<T> targetVf;

    public BaseDecoratingValueFactory(ValueFactory<T> targetVf) {
        this.targetVf = targetVf;
    }

    @Override
    public T createFromDate(int year, int month, int day) {
        return this.targetVf.createFromDate(year, month, day);
    }

    @Override
    public T createFromTime(int hours, int minutes, int seconds, int nanos) {
        return this.targetVf.createFromTime(hours, minutes, seconds, nanos);
    }

    @Override
    public T createFromTimestamp(int year, int month, int day, int hours, int minutes, int seconds, int nanos) {
        return this.targetVf.createFromTimestamp(year, month, day, hours, minutes, seconds, nanos);
    }

    @Override
    public T createFromLong(long l) {
        return this.targetVf.createFromLong(l);
    }

    @Override
    public T createFromBigInteger(BigInteger i) {
        return this.targetVf.createFromBigInteger(i);
    }

    @Override
    public T createFromDouble(double d) {
        return this.targetVf.createFromDouble(d);
    }

    @Override
    public T createFromBigDecimal(BigDecimal d) {
        return this.targetVf.createFromBigDecimal(d);
    }

    @Override
    public T createFromBytes(byte[] bytes, int offset, int length) {
        return this.targetVf.createFromBytes(bytes, offset, length);
    }

    @Override
    public T createFromBit(byte[] bytes, int offset, int length) {
        return this.targetVf.createFromBit(bytes, offset, length);
    }

    @Override
    public T createFromNull() {
        return this.targetVf.createFromNull();
    }

    @Override
    public String getTargetTypeName() {
        return this.targetVf.getTargetTypeName();
    }
}

