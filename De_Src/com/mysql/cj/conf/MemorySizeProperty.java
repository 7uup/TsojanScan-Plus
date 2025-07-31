/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.conf;

import com.mysql.cj.conf.IntegerProperty;
import com.mysql.cj.conf.PropertyDefinition;
import com.mysql.cj.exceptions.ExceptionInterceptor;
import java.util.Properties;
import javax.naming.Reference;

public class MemorySizeProperty
extends IntegerProperty {
    private static final long serialVersionUID = 4200558564320133284L;
    private String initialValueAsString;
    protected String valueAsString;

    protected MemorySizeProperty(PropertyDefinition<Integer> propertyDefinition) {
        super(propertyDefinition);
        this.valueAsString = propertyDefinition.getDefaultValue().toString();
    }

    @Override
    public void initializeFrom(Properties extractFrom, ExceptionInterceptor exceptionInterceptor) {
        super.initializeFrom(extractFrom, exceptionInterceptor);
        this.initialValueAsString = this.valueAsString;
    }

    @Override
    public void initializeFrom(Reference ref, ExceptionInterceptor exceptionInterceptor) {
        super.initializeFrom(ref, exceptionInterceptor);
        this.initialValueAsString = this.valueAsString;
    }

    @Override
    public String getStringValue() {
        return this.valueAsString;
    }

    @Override
    public void setValueInternal(Integer value, String valueAsString, ExceptionInterceptor exceptionInterceptor) {
        super.setValueInternal(value, valueAsString, exceptionInterceptor);
        this.valueAsString = valueAsString == null ? String.valueOf(value) : valueAsString;
    }

    @Override
    public void resetValue() {
        this.value = this.initialValue;
        this.valueAsString = this.initialValueAsString;
        this.invokeListeners();
    }
}

