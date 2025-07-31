/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.conf;

import com.mysql.cj.conf.AbstractRuntimeProperty;
import com.mysql.cj.conf.PropertyDefinition;
import com.mysql.cj.exceptions.ExceptionFactory;
import com.mysql.cj.exceptions.ExceptionInterceptor;
import com.mysql.cj.exceptions.WrongArgumentException;

public class LongProperty
extends AbstractRuntimeProperty<Long> {
    private static final long serialVersionUID = 1814429804634837665L;

    protected LongProperty(PropertyDefinition<Long> propertyDefinition) {
        super(propertyDefinition);
    }

    @Override
    protected void checkRange(Long val, String valueAsString, ExceptionInterceptor exceptionInterceptor) {
        if (val < (long)this.getPropertyDefinition().getLowerBound() || val > (long)this.getPropertyDefinition().getUpperBound()) {
            throw ExceptionFactory.createException(WrongArgumentException.class, "The connection property '" + this.getPropertyDefinition().getName() + "' only accepts long integer values in the range of " + this.getPropertyDefinition().getLowerBound() + " - " + this.getPropertyDefinition().getUpperBound() + ", the value '" + (valueAsString == null ? Long.valueOf(val) : valueAsString) + "' exceeds this range.", exceptionInterceptor);
        }
    }
}

