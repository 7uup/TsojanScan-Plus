/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.conf;

import com.mysql.cj.conf.AbstractRuntimeProperty;
import com.mysql.cj.conf.PropertyDefinition;

public class StringProperty
extends AbstractRuntimeProperty<String> {
    private static final long serialVersionUID = -4141084145739428803L;

    protected StringProperty(PropertyDefinition<String> propertyDefinition) {
        super(propertyDefinition);
    }

    @Override
    public String getStringValue() {
        return (String)this.value;
    }
}

