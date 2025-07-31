/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.conf;

import com.mysql.cj.Messages;
import com.mysql.cj.conf.PropertyDefinition;
import com.mysql.cj.conf.PropertyDefinitions;
import com.mysql.cj.conf.PropertySet;
import com.mysql.cj.conf.RuntimeProperty;
import com.mysql.cj.conf.StringProperty;
import com.mysql.cj.conf.StringPropertyDefinition;
import com.mysql.cj.exceptions.CJException;
import com.mysql.cj.exceptions.ExceptionFactory;
import com.mysql.cj.exceptions.WrongArgumentException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class DefaultPropertySet
implements PropertySet,
Serializable {
    private static final long serialVersionUID = -5156024634430650528L;
    private final Map<String, RuntimeProperty<?>> PROPERTY_NAME_TO_RUNTIME_PROPERTY = new HashMap();

    public DefaultPropertySet() {
        for (PropertyDefinition<?> pdef : PropertyDefinitions.PROPERTY_NAME_TO_PROPERTY_DEFINITION.values()) {
            this.addProperty(pdef.createRuntimeProperty());
        }
    }

    @Override
    public void addProperty(RuntimeProperty<?> prop) {
        PropertyDefinition<?> def = prop.getPropertyDefinition();
        this.PROPERTY_NAME_TO_RUNTIME_PROPERTY.put(def.getName(), prop);
        if (def.hasCcAlias()) {
            this.PROPERTY_NAME_TO_RUNTIME_PROPERTY.put(def.getCcAlias(), prop);
        }
    }

    @Override
    public void removeProperty(String name) {
        RuntimeProperty<?> prop = this.PROPERTY_NAME_TO_RUNTIME_PROPERTY.remove(name);
        if (prop != null) {
            if (!name.equals(prop.getPropertyDefinition().getName())) {
                this.PROPERTY_NAME_TO_RUNTIME_PROPERTY.remove(prop.getPropertyDefinition().getName());
            } else if (prop.getPropertyDefinition().hasCcAlias()) {
                this.PROPERTY_NAME_TO_RUNTIME_PROPERTY.remove(prop.getPropertyDefinition().getCcAlias());
            }
        }
    }

    @Override
    public <T> RuntimeProperty<T> getProperty(String name) {
        try {
            RuntimeProperty<?> prop = this.PROPERTY_NAME_TO_RUNTIME_PROPERTY.get(name);
            if (prop != null) {
                return prop;
            }
            throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("ConnectionProperties.notFound", new Object[]{name}));
        } catch (ClassCastException ex) {
            throw ExceptionFactory.createException(WrongArgumentException.class, ex.getMessage(), ex);
        }
    }

    @Override
    public RuntimeProperty<Boolean> getBooleanProperty(String name) {
        return this.getProperty(name);
    }

    @Override
    public RuntimeProperty<Integer> getIntegerProperty(String name) {
        return this.getProperty(name);
    }

    @Override
    public RuntimeProperty<Long> getLongProperty(String name) {
        return this.getProperty(name);
    }

    @Override
    public RuntimeProperty<Integer> getMemorySizeProperty(String name) {
        return this.getProperty(name);
    }

    @Override
    public RuntimeProperty<String> getStringProperty(String name) {
        return this.getProperty(name);
    }

    @Override
    public <T extends Enum<T>> RuntimeProperty<T> getEnumProperty(String name) {
        return this.getProperty(name);
    }

    @Override
    public void initializeProperties(Properties props) {
        if (props != null) {
            Properties infoCopy = (Properties)props.clone();
            infoCopy.remove(PropertyDefinitions.PropertyKey.HOST.getKeyName());
            infoCopy.remove(PropertyDefinitions.PropertyKey.PORT.getKeyName());
            infoCopy.remove(PropertyDefinitions.PropertyKey.USER.getKeyName());
            infoCopy.remove(PropertyDefinitions.PropertyKey.PASSWORD.getKeyName());
            infoCopy.remove(PropertyDefinitions.PropertyKey.DBNAME.getKeyName());
            for (String propName : PropertyDefinitions.PROPERTY_NAME_TO_PROPERTY_DEFINITION.keySet()) {
                try {
                    RuntimeProperty propToSet = this.getProperty(propName);
                    propToSet.initializeFrom(infoCopy, null);
                } catch (CJException e) {
                    throw ExceptionFactory.createException(WrongArgumentException.class, e.getMessage(), e);
                }
            }
            for (String key : infoCopy.keySet()) {
                String val = infoCopy.getProperty(key);
                StringPropertyDefinition def = new StringPropertyDefinition(key, null, val, true, Messages.getString("ConnectionProperties.unknown"), "8.0.10", PropertyDefinitions.CATEGORY_USER_DEFINED, Integer.MIN_VALUE);
                StringProperty p = new StringProperty(def);
                this.addProperty(p);
            }
            this.postInitialization();
        }
    }

    @Override
    public void postInitialization() {
    }

    @Override
    public Properties exposeAsProperties() {
        Properties props = new Properties();
        for (String propName : this.PROPERTY_NAME_TO_RUNTIME_PROPERTY.keySet()) {
            RuntimeProperty propToGet;
            String propValue;
            if (props.containsKey(propName) || (propValue = (propToGet = this.getProperty(propName)).getStringValue()) == null) continue;
            props.setProperty(propToGet.getPropertyDefinition().getName(), propValue);
        }
        return props;
    }
}

