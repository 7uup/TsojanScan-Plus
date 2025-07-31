/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.result;

import com.mysql.cj.result.ValueFactory;
import com.mysql.cj.util.StringUtils;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.ByteBuffer;

public class StringValueFactory
implements ValueFactory<String> {
    private String encoding;

    public StringValueFactory() {
    }

    public StringValueFactory(String encoding) {
        this.encoding = encoding;
    }

    @Override
    public String createFromDate(int year, int month, int day) {
        return String.format("%04d-%02d-%02d", year, month, day);
    }

    @Override
    public String createFromTime(int hours, int minutes, int seconds, int nanos) {
        if (nanos > 0) {
            return String.format("%02d:%02d:%02d.%09d", hours, minutes, seconds, nanos);
        }
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    @Override
    public String createFromTimestamp(int year, int month, int day, int hours, int minutes, int seconds, int nanos) {
        return String.format("%s %s", this.createFromDate(year, month, day), this.createFromTime(hours, minutes, seconds, nanos));
    }

    @Override
    public String createFromLong(long l) {
        return String.valueOf(l);
    }

    @Override
    public String createFromBigInteger(BigInteger i) {
        return i.toString();
    }

    @Override
    public String createFromDouble(double d) {
        return String.valueOf(d);
    }

    @Override
    public String createFromBigDecimal(BigDecimal d) {
        return d.toString();
    }

    @Override
    public String createFromBytes(byte[] bytes, int offset, int length) {
        return StringUtils.toString(bytes, offset, length, this.encoding);
    }

    @Override
    public String createFromBit(byte[] bytes, int offset, int length) {
        return new BigInteger(ByteBuffer.allocate(length + 1).put((byte)0).put(bytes, offset, length).array()).toString();
    }

    @Override
    public String createFromNull() {
        return null;
    }

    @Override
    public String getTargetTypeName() {
        return String.class.getName();
    }
}

