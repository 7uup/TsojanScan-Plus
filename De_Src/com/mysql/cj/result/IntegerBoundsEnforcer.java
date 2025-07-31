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

public class IntegerBoundsEnforcer<T>
extends BaseDecoratingValueFactory<T> {
    private long min;
    private long max;

    public IntegerBoundsEnforcer(ValueFactory<T> targetVf, long min2, long max) {
        super(targetVf);
        this.min = min2;
        this.max = max;
    }

    @Override
    public T createFromLong(long l) {
        if (l < this.min || l > this.max) {
            throw new NumberOutOfRange(Messages.getString("ResultSet.NumberOutOfRange", new Object[]{Long.valueOf(l).toString(), this.targetVf.getTargetTypeName()}));
        }
        return this.targetVf.createFromLong(l);
    }

    @Override
    public T createFromBigInteger(BigInteger i) {
        if (i.compareTo(BigInteger.valueOf(this.min)) < 0 || i.compareTo(BigInteger.valueOf(this.max)) > 0) {
            throw new NumberOutOfRange(Messages.getString("ResultSet.NumberOutOfRange", new Object[]{i, this.targetVf.getTargetTypeName()}));
        }
        return this.targetVf.createFromBigInteger(i);
    }

    @Override
    public T createFromDouble(double d) {
        if (d < (double)this.min || d > (double)this.max) {
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

