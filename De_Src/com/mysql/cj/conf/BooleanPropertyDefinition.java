/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.conf;

import com.mysql.cj.Messages;
import com.mysql.cj.conf.AbstractPropertyDefinition;
import com.mysql.cj.conf.BooleanProperty;
import com.mysql.cj.conf.RuntimeProperty;
import com.mysql.cj.exceptions.ExceptionFactory;
import com.mysql.cj.exceptions.ExceptionInterceptor;
import com.mysql.cj.util.StringUtils;
import java.util.Arrays;

public class BooleanPropertyDefinition
extends AbstractPropertyDefinition<Boolean> {
    private static final long serialVersionUID = -7288366734350231540L;

    public BooleanPropertyDefinition(String name, String alias, Boolean defaultValue, boolean isRuntimeModifiable, String description, String sinceVersion, String category, int orderInCategory) {
        super(name, alias, defaultValue, isRuntimeModifiable, description, sinceVersion, category, orderInCategory);
    }

    @Override
    public String[] getAllowableValues() {
        return (String[])Arrays.stream(AllowableValues.values()).map(Enum::toString).toArray(String[]::new);
    }

    @Override
    public Boolean parseObject(String value, ExceptionInterceptor exceptionInterceptor) {
        try {
            return AllowableValues.valueOf(value.toUpperCase()).asBoolean();
        } catch (Exception e) {
            throw ExceptionFactory.createException(Messages.getString("PropertyDefinition.1", new Object[]{this.getName(), StringUtils.stringArrayToString(this.getAllowableValues(), "'", "', '", "' or '", "'"), value}), e, exceptionInterceptor);
        }
    }

    @Override
    public RuntimeProperty<Boolean> createRuntimeProperty() {
        return new BooleanProperty(this);
    }

    public static enum AllowableValues {
        TRUE(true),
        FALSE(false),
        YES(true),
        NO(false);

        private boolean asBoolean;

        private AllowableValues(boolean booleanValue) {
            this.asBoolean = booleanValue;
        }

        public boolean asBoolean() {
            return this.asBoolean;
        }
    }
}

