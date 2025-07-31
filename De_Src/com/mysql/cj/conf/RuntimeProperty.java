/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.conf;

import com.mysql.cj.conf.PropertyDefinition;
import com.mysql.cj.exceptions.ExceptionInterceptor;
import java.util.Properties;
import javax.naming.Reference;

public interface RuntimeProperty<T> {
    public PropertyDefinition<T> getPropertyDefinition();

    public void initializeFrom(Properties var1, ExceptionInterceptor var2);

    public void initializeFrom(Reference var1, ExceptionInterceptor var2);

    public void resetValue();

    public boolean isExplicitlySet();

    public void addListener(RuntimePropertyListener var1);

    public void removeListener(RuntimePropertyListener var1);

    public T getValue();

    public T getInitialValue();

    public String getStringValue();

    public void setValue(T var1);

    public void setValue(T var1, ExceptionInterceptor var2);

    @FunctionalInterface
    public static interface RuntimePropertyListener {
        public void handlePropertyChange(RuntimeProperty<?> var1);
    }
}

