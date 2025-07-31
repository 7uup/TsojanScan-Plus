/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.conf;

import com.mysql.cj.conf.AbstractRuntimeProperty;
import com.mysql.cj.conf.PropertyDefinition;
import com.mysql.cj.exceptions.ExceptionFactory;
import com.mysql.cj.exceptions.ExceptionInterceptor;
import com.mysql.cj.exceptions.WrongArgumentException;

public class IntegerProperty
extends AbstractRuntimeProperty<Integer> {
    private static final long serialVersionUID = 9208223182595760858L;

    public IntegerProperty(PropertyDefinition<Integer> propertyDefinition) {
        super(propertyDefinition);
    }

    @Override
    protected void checkRange(Integer val, String valueAsString, ExceptionInterceptor exceptionInterceptor) {
        if (val < this.getPropertyDefinition().getLowerBound() || val > this.getPropertyDefinition().getUpperBound()) {
            throw ExceptionFactory.createException(WrongArgumentException.class, "The connection property '" + this.getPropertyDefinition().getName() + "' only accepts integer values in the range of " + this.getPropertyDefinition().getLowerBound() + " - " + this.getPropertyDefinition().getUpperBound() + ", the value '" + (valueAsString == null ? Integer.valueOf(val) : valueAsString) + "' exceeds this range.", exceptionInterceptor);
        }
    }
}

