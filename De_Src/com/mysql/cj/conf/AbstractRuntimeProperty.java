/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.conf;

import com.mysql.cj.Messages;
import com.mysql.cj.conf.PropertyDefinition;
import com.mysql.cj.conf.RuntimeProperty;
import com.mysql.cj.exceptions.ExceptionFactory;
import com.mysql.cj.exceptions.ExceptionInterceptor;
import com.mysql.cj.exceptions.PropertyNotModifiableException;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.naming.RefAddr;
import javax.naming.Reference;

public abstract class AbstractRuntimeProperty<T>
implements RuntimeProperty<T>,
Serializable {
    private static final long serialVersionUID = -3424722534876438236L;
    private PropertyDefinition<T> propertyDefinition;
    protected T value;
    protected T initialValue;
    protected boolean wasExplicitlySet = false;
    private List<WeakReference<RuntimeProperty.RuntimePropertyListener>> listeners;

    public AbstractRuntimeProperty() {
    }

    protected AbstractRuntimeProperty(PropertyDefinition<T> propertyDefinition) {
        this.propertyDefinition = propertyDefinition;
        this.value = propertyDefinition.getDefaultValue();
        this.initialValue = propertyDefinition.getDefaultValue();
    }

    @Override
    public PropertyDefinition<T> getPropertyDefinition() {
        return this.propertyDefinition;
    }

    @Override
    public void initializeFrom(Properties extractFrom, ExceptionInterceptor exceptionInterceptor) {
        String extractedValue;
        String name = this.getPropertyDefinition().getName();
        if (extractFrom.containsKey(name) && (extractedValue = (String)extractFrom.remove(name)) != null) {
            this.setValueInternal(extractedValue, exceptionInterceptor);
            this.initialValue = this.value;
        }
    }

    @Override
    public void initializeFrom(Reference ref, ExceptionInterceptor exceptionInterceptor) {
        String refContentAsString;
        RefAddr refAddr = ref.get(this.getPropertyDefinition().getName());
        if (refAddr != null && (refContentAsString = (String)refAddr.getContent()) != null) {
            this.setValueInternal(refContentAsString, exceptionInterceptor);
            this.initialValue = this.value;
        }
    }

    @Override
    public void resetValue() {
        this.value = this.initialValue;
        this.invokeListeners();
    }

    @Override
    public boolean isExplicitlySet() {
        return this.wasExplicitlySet;
    }

    @Override
    public void addListener(RuntimeProperty.RuntimePropertyListener l) {
        if (this.listeners == null) {
            this.listeners = new ArrayList<WeakReference<RuntimeProperty.RuntimePropertyListener>>();
        }
        boolean found = false;
        for (WeakReference<RuntimeProperty.RuntimePropertyListener> weakReference : this.listeners) {
            if (!l.equals(weakReference.get())) continue;
            found = true;
            break;
        }
        if (!found) {
            this.listeners.add(new WeakReference<RuntimeProperty.RuntimePropertyListener>(l));
        }
    }

    @Override
    public void removeListener(RuntimeProperty.RuntimePropertyListener listener) {
        if (this.listeners != null) {
            for (WeakReference<RuntimeProperty.RuntimePropertyListener> wr : this.listeners) {
                RuntimeProperty.RuntimePropertyListener l = (RuntimeProperty.RuntimePropertyListener)wr.get();
                if (!l.equals(listener)) continue;
                this.listeners.remove(wr);
                break;
            }
        }
    }

    protected void invokeListeners() {
        if (this.listeners != null) {
            for (WeakReference<RuntimeProperty.RuntimePropertyListener> wr : this.listeners) {
                RuntimeProperty.RuntimePropertyListener l = (RuntimeProperty.RuntimePropertyListener)wr.get();
                if (l != null) {
                    l.handlePropertyChange(this);
                    continue;
                }
                this.listeners.remove(wr);
            }
        }
    }

    @Override
    public T getValue() {
        return this.value;
    }

    @Override
    public T getInitialValue() {
        return this.initialValue;
    }

    @Override
    public String getStringValue() {
        return this.value == null ? null : this.value.toString();
    }

    public void setValueInternal(String value, ExceptionInterceptor exceptionInterceptor) {
        this.setValueInternal(this.getPropertyDefinition().parseObject(value, exceptionInterceptor), value, exceptionInterceptor);
    }

    public void setValueInternal(T value, String valueAsString, ExceptionInterceptor exceptionInterceptor) {
        if (this.getPropertyDefinition().isRangeBased()) {
            this.checkRange(value, valueAsString, exceptionInterceptor);
        }
        this.value = value;
        this.wasExplicitlySet = true;
    }

    protected void checkRange(T val, String valueAsString, ExceptionInterceptor exceptionInterceptor) {
    }

    @Override
    public void setValue(T value) {
        this.setValue(value, null);
    }

    @Override
    public void setValue(T value, ExceptionInterceptor exceptionInterceptor) {
        if (!this.getPropertyDefinition().isRuntimeModifiable()) {
            throw ExceptionFactory.createException(PropertyNotModifiableException.class, Messages.getString("ConnectionProperties.dynamicChangeIsNotAllowed", new Object[]{"'" + this.getPropertyDefinition().getName() + "'"}));
        }
        this.setValueInternal(value, null, exceptionInterceptor);
        this.invokeListeners();
    }
}

