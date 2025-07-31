/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.result;

import com.mysql.cj.result.BaseDecoratingValueFactory;
import com.mysql.cj.result.ValueFactory;

public class YearToDateValueFactory<T>
extends BaseDecoratingValueFactory<T> {
    public YearToDateValueFactory(ValueFactory<T> targetVf) {
        super(targetVf);
    }

    @Override
    public T createFromLong(long year) {
        if (year < 100L) {
            if (year <= 69L) {
                year += 100L;
            }
            year += 1900L;
        }
        return this.targetVf.createFromDate((int)year, 1, 1);
    }
}

