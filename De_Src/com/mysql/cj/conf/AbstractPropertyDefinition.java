/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.conf;

import com.mysql.cj.conf.PropertyDefinition;
import com.mysql.cj.exceptions.ExceptionInterceptor;
import java.io.Serializable;

public abstract class AbstractPropertyDefinition<T>
implements PropertyDefinition<T>,
Serializable {
    private static final long serialVersionUID = 2696624840927848766L;
    private String name;
    private String ccAlias;
    private T defaultValue;
    private boolean isRuntimeModifiable;
    private String description;
    private String sinceVersion;
    private String category;
    private int order;
    private int lowerBound;
    private int upperBound;

    public AbstractPropertyDefinition(String name, String camelCaseAlias, T defaultValue, boolean isRuntimeModifiable, String description, String sinceVersion, String category, int orderInCategory) {
        this.name = name;
        this.ccAlias = camelCaseAlias;
        this.setDefaultValue(defaultValue);
        this.setRuntimeModifiable(isRuntimeModifiable);
        this.setDescription(description);
        this.setSinceVersion(sinceVersion);
        this.setCategory(category);
        this.setOrder(orderInCategory);
    }

    public AbstractPropertyDefinition(String name, String alias, T defaultValue, boolean isRuntimeModifiable, String description, String sinceVersion, String category, int orderInCategory, int lowerBound, int upperBound) {
        this(name, alias, defaultValue, isRuntimeModifiable, description, sinceVersion, category, orderInCategory);
        this.setLowerBound(lowerBound);
        this.setUpperBound(upperBound);
    }

    @Override
    public boolean hasValueConstraints() {
        return this.getAllowableValues() != null && this.getAllowableValues().length > 0;
    }

    @Override
    public boolean isRangeBased() {
        return false;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getCcAlias() {
        return this.ccAlias;
    }

    @Override
    public boolean hasCcAlias() {
        return this.ccAlias != null && this.ccAlias.length() > 0;
    }

    @Override
    public T getDefaultValue() {
        return this.defaultValue;
    }

    public void setDefaultValue(T defaultValue) {
        this.defaultValue = defaultValue;
    }

    @Override
    public boolean isRuntimeModifiable() {
        return this.isRuntimeModifiable;
    }

    public void setRuntimeModifiable(boolean isRuntimeModifiable) {
        this.isRuntimeModifiable = isRuntimeModifiable;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getSinceVersion() {
        return this.sinceVersion;
    }

    public void setSinceVersion(String sinceVersion) {
        this.sinceVersion = sinceVersion;
    }

    @Override
    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public int getOrder() {
        return this.order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    @Override
    public String[] getAllowableValues() {
        return null;
    }

    @Override
    public int getLowerBound() {
        return this.lowerBound;
    }

    public void setLowerBound(int lowerBound) {
        this.lowerBound = lowerBound;
    }

    @Override
    public int getUpperBound() {
        return this.upperBound;
    }

    public void setUpperBound(int upperBound) {
        this.upperBound = upperBound;
    }

    @Override
    public abstract T parseObject(String var1, ExceptionInterceptor var2);
}

