/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.conf;

import com.mysql.cj.Messages;
import com.mysql.cj.conf.AbstractPropertyDefinition;
import com.mysql.cj.conf.EnumProperty;
import com.mysql.cj.conf.RuntimeProperty;
import com.mysql.cj.exceptions.ExceptionFactory;
import com.mysql.cj.exceptions.ExceptionInterceptor;
import com.mysql.cj.util.StringUtils;
import java.util.Arrays;

public class EnumPropertyDefinition<T extends Enum<T>>
extends AbstractPropertyDefinition<T> {
    private static final long serialVersionUID = -3297521968759540444L;
    private Class<T> enumType;

    public EnumPropertyDefinition(String name, String alias, T defaultValue, boolean isRuntimeModifiable, String description, String sinceVersion, String category, int orderInCategory) {
        super(name, alias, defaultValue, isRuntimeModifiable, description, sinceVersion, category, orderInCategory);
        if (defaultValue == null) {
            throw ExceptionFactory.createException("Enum property '" + name + "' cannot be initialized with null.");
        }
        this.enumType = ((Enum)defaultValue).getDeclaringClass();
    }

    @Override
    public String[] getAllowableValues() {
        return (String[])Arrays.stream(this.enumType.getEnumConstants()).map(Enum::toString).sorted().toArray(String[]::new);
    }

    @Override
    public T parseObject(String value, ExceptionInterceptor exceptionInterceptor) {
        try {
            return Enum.valueOf(this.enumType, value.toUpperCase());
        } catch (Exception e) {
            throw ExceptionFactory.createException(Messages.getString("PropertyDefinition.1", new Object[]{this.getName(), StringUtils.stringArrayToString(this.getAllowableValues(), "'", "', '", "' or '", "'"), value}), e, exceptionInterceptor);
        }
    }

    @Override
    public RuntimeProperty<T> createRuntimeProperty() {
        return new EnumProperty(this);
    }
}

