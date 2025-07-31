/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.result;

import com.mysql.cj.result.BaseDecoratingValueFactory;
import com.mysql.cj.result.ValueFactory;

public class ZeroDateTimeToNullValueFactory<T>
extends BaseDecoratingValueFactory<T> {
    public ZeroDateTimeToNullValueFactory(ValueFactory<T> targetVf) {
        super(targetVf);
    }

    @Override
    public T createFromDate(int year, int month, int day) {
        if (year + month + day == 0) {
            return null;
        }
        return this.targetVf.createFromDate(year, month, day);
    }

    @Override
    public T createFromTime(int hours, int minutes, int seconds, int nanos) {
        if (hours + minutes + seconds + nanos == 0) {
            return null;
        }
        return this.targetVf.createFromTime(hours, minutes, seconds, nanos);
    }

    @Override
    public T createFromTimestamp(int year, int month, int day, int hours, int minutes, int seconds, int nanos) {
        if (year + month + day + hours + minutes + seconds + nanos == 0) {
            return null;
        }
        return this.targetVf.createFromTimestamp(year, month, day, hours, minutes, seconds, nanos);
    }
}

