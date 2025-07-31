/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.conf;

import com.mysql.cj.conf.AbstractPropertyDefinition;
import com.mysql.cj.conf.LongProperty;
import com.mysql.cj.conf.RuntimeProperty;
import com.mysql.cj.exceptions.ExceptionFactory;
import com.mysql.cj.exceptions.ExceptionInterceptor;
import com.mysql.cj.exceptions.WrongArgumentException;

public class LongPropertyDefinition
extends AbstractPropertyDefinition<Long> {
    private static final long serialVersionUID = -5264490959206230852L;

    public LongPropertyDefinition(String name, String alias, long defaultValue, boolean isRuntimeModifiable, String description, String sinceVersion, String category, int orderInCategory) {
        super(name, alias, defaultValue, isRuntimeModifiable, description, sinceVersion, category, orderInCategory);
    }

    public LongPropertyDefinition(String name, String alias, long defaultValue, boolean isRuntimeModifiable, String description, String sinceVersion, String category, int orderInCategory, long lowerBound, long upperBound) {
        super(name, alias, defaultValue, isRuntimeModifiable, description, sinceVersion, category, orderInCategory, (int)lowerBound, (int)upperBound);
    }

    @Override
    public Long parseObject(String value, ExceptionInterceptor exceptionInterceptor) {
        try {
            return Double.valueOf(value).longValue();
        } catch (NumberFormatException nfe) {
            throw ExceptionFactory.createException(WrongArgumentException.class, "The connection property '" + this.getName() + "' only accepts long integer values. The value '" + value + "' can not be converted to a long integer.", exceptionInterceptor);
        }
    }

    @Override
    public boolean isRangeBased() {
        return this.getUpperBound() != this.getLowerBound();
    }

    @Override
    public RuntimeProperty<Long> createRuntimeProperty() {
        return new LongProperty(this);
    }
}

