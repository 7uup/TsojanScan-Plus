/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.result;

import com.mysql.cj.Messages;
import com.mysql.cj.exceptions.NumberOutOfRange;
import com.mysql.cj.result.BaseDecoratingValueFactory;
import com.mysql.cj.result.ValueFactory;
import java.math.BigDecimal;
import java.math.BigInteger;

public class FloatingPointBoundsEnforcer<T>
extends BaseDecoratingValueFactory<T> {
    private double min;
    private double max;

    public FloatingPointBoundsEnforcer(ValueFactory<T> targetVf, double min2, double max) {
        super(targetVf);
        this.min = min2;
        this.max = max;
    }

    @Override
    public T createFromLong(long l) {
        if ((double)l < this.min || (double)l > this.max) {
            throw new NumberOutOfRange(Messages.getString("ResultSet.NumberOutOfRange", new Object[]{l, this.targetVf.getTargetTypeName()}));
        }
        return this.targetVf.createFromLong(l);
    }

    @Override
    public T createFromBigInteger(BigInteger i) {
        if (new BigDecimal(i).compareTo(BigDecimal.valueOf(this.min)) < 0 || new BigDecimal(i).compareTo(BigDecimal.valueOf(this.max)) > 0) {
            throw new NumberOutOfRange(Messages.getString("ResultSet.NumberOutOfRange", new Object[]{i, this.targetVf.getTargetTypeName()}));
        }
        return this.targetVf.createFromBigInteger(i);
    }

    @Override
    public T createFromDouble(double d) {
        if (d < this.min || d > this.max) {
            throw new NumberOutOfRange(Messages.getString("ResultSet.NumberOutOfRange", new Object[]{d, this.targetVf.getTargetTypeName()}));
        }
        return this.targetVf.createFromDouble(d);
    }

    @Override
    public T createFromBigDecimal(BigDecimal d) {
        if (d.compareTo(BigDecimal.valueOf(this.min)) < 0 || d.compareTo(BigDecimal.valueOf(this.max)) > 0) {
            throw new NumberOutOfRange(Messages.getString("ResultSet.NumberOutOfRange", new Object[]{d, this.targetVf.getTargetTypeName()}));
        }
        return this.targetVf.createFromBigDecimal(d);
    }
}

