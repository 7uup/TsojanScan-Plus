/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.conf;

import com.mysql.cj.conf.AbstractRuntimeProperty;
import com.mysql.cj.conf.PropertyDefinition;

public class BooleanProperty
extends AbstractRuntimeProperty<Boolean> {
    private static final long serialVersionUID = 1102859411443650569L;

    protected BooleanProperty(PropertyDefinition<Boolean> propertyDefinition) {
        super(propertyDefinition);
    }
}

