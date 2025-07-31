/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.util.TypeUtils;
import java.io.IOException;
import java.io.NotActiveException;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.io.Serializable;
import java.io.StreamCorruptedException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class JSONObject
extends JSON
implements Map<String, Object>,
Cloneable,
Serializable,
InvocationHandler {
    private static final long serialVersionUID = 1L;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private final Map<String, Object> map;

    public JSONObject() {
        this(16, false);
    }

    public JSONObject(Map<String, Object> map) {
        if (map == null) {
            throw new IllegalArgumentException("map is null.");
        }
        this.map = map;
    }

    public JSONObject(boolean ordered) {
        this(16, ordered);
    }

    public JSONObject(int initialCapacity) {
        this(initialCapacity, false);
    }

    public JSONObject(int initialCapacity, boolean ordered) {
        this.map = ordered ? new LinkedHashMap<String, Object>(initialCapacity) : new HashMap<String, Object>(initialCapacity);
    }

    @Override
    public int size() {
        return this.map.size();
    }

    @Override
    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        boolean result = this.map.containsKey(key);
        if (!result && (key instanceof Number || key instanceof Character || key instanceof Boolean || key instanceof UUID)) {
            result = this.map.containsKey(key.toString());
        }
        return result;
    }

    @Override
    public boolean containsValue(Object value) {
        return this.map.containsValue(value);
    }

    @Override
    public Object get(Object key) {
        Object val = this.map.get(key);
        if (val == null && (key instanceof Number || key instanceof Character || key instanceof Boolean || key instanceof UUID)) {
            val = this.map.get(key.toString());
        }
        return val;
    }

    @Override
    public Object getOrDefault(Object key, Object defaultValue) {
        Object v = this.get(key);
        return v != null ? v : defaultValue;
    }

    public JSONObject getJSONObject(String key) {
        Object value = this.map.get(key);
        if (value instanceof JSONObject) {
            return (JSONObject)value;
        }
        if (value instanceof Map) {
            return new JSONObject((Map)value);
        }
        if (value instanceof String) {
            return JSON.parseObject((String)value);
        }
        return (JSONObject)JSONObject.toJSON(value);
    }

    public JSONArray getJSONArray(String key) {
        Object value = this.map.get(key);
        if (value instanceof JSONArray) {
            return (JSONArray)value;
        }
        if (value instanceof List) {
            return new JSONArray((List)value);
        }
        if (value instanceof String) {
            return (JSONArray)JSON.parse((String)value);
        }
        return (JSONArray)JSONObject.toJSON(value);
    }

    public <T> T getObject(String key, Class<T> clazz) {
        Object obj = this.map.get(key);
        return TypeUtils.castToJavaBean(obj, clazz);
    }

    public <T> T getObject(String key, Type type) {
        Object obj = this.map.get(key);
        return TypeUtils.cast(obj, type, ParserConfig.getGlobalInstance());
    }

    public <T> T getObject(String key, TypeReference typeReference) {
        Object obj = this.map.get(key);
        if (typeReference == null) {
            return (T)obj;
        }
        return TypeUtils.cast(obj, typeReference.getType(), ParserConfig.getGlobalInstance());
    }

    public Boolean getBoolean(String key) {
        Object value = this.get(key);
        if (value == null) {
            return null;
        }
        return TypeUtils.castToBoolean(value);
    }

    public byte[] getBytes(String key) {
        Object value = this.get(key);
        if (value == null) {
            return null;
        }
        return TypeUtils.castToBytes(value);
    }

    public boolean getBooleanValue(String key) {
        Object value = this.get(key);
        Boolean booleanVal = TypeUtils.castToBoolean(value);
        if (booleanVal == null) {
            return false;
        }
        return booleanVal;
    }

    public Byte getByte(String key) {
        Object value = this.get(key);
        return TypeUtils.castToByte(value);
    }

    public byte getByteValue(String key) {
        Object value = this.get(key);
        Byte byteVal = TypeUtils.castToByte(value);
        if (byteVal == null) {
            return 0;
        }
        return byteVal;
    }

    public Short getShort(String key) {
        Object value = this.get(key);
        return TypeUtils.castToShort(value);
    }

    public short getShortValue(String key) {
        Object value = this.get(key);
        Short shortVal = TypeUtils.castToShort(value);
        if (shortVal == null) {
            return 0;
        }
        return shortVal;
    }

    public Integer getInteger(String key) {
        Object value = this.get(key);
        return TypeUtils.castToInt(value);
    }

    public int getIntValue(String key) {
        Object value = this.get(key);
        Integer intVal = TypeUtils.castToInt(value);
        if (intVal == null) {
            return 0;
        }
        return intVal;
    }

    public Long getLong(String key) {
        Object value = this.get(key);
        return TypeUtils.castToLong(value);
    }

    public long getLongValue(String key) {
        Object value = this.get(key);
        Long longVal = TypeUtils.castToLong(value);
        if (longVal == null) {
            return 0L;
        }
        return longVal;
    }

    public Float getFloat(String key) {
        Object value = this.get(key);
        return TypeUtils.castToFloat(value);
    }

    public float getFloatValue(String key) {
        Object value = this.get(key);
        Float floatValue = TypeUtils.castToFloat(value);
        if (floatValue == null) {
            return 0.0f;
        }
        return floatValue.floatValue();
    }

    public Double getDouble(String key) {
        Object value = this.get(key);
        return TypeUtils.castToDouble(value);
    }

    public double getDoubleValue(String key) {
        Object value = this.get(key);
        Double doubleValue = TypeUtils.castToDouble(value);
        if (doubleValue == null) {
            return 0.0;
        }
        return doubleValue;
    }

    public BigDecimal getBigDecimal(String key) {
        Object value = this.get(key);
        return TypeUtils.castToBigDecimal(value);
    }

    public BigInteger getBigInteger(String key) {
        Object value = this.get(key);
        return TypeUtils.castToBigInteger(value);
    }

    public String getString(String key) {
        Object value = this.get(key);
        if (value == null) {
            return null;
        }
        return value.toString();
    }

    public Date getDate(String key) {
        Object value = this.get(key);
        return TypeUtils.castToDate(value);
    }

    public Object getSqlDate(String key) {
        Object value = this.get(key);
        return TypeUtils.castToSqlDate(value);
    }

    public Object getTimestamp(String key) {
        Object value = this.get(key);
        return TypeUtils.castToTimestamp(value);
    }

    @Override
    public Object put(String key, Object value) {
        return this.map.put(key, value);
    }

    public JSONObject fluentPut(String key, Object value) {
        this.map.put(key, value);
        return this;
    }

    @Override
    public void putAll(Map<? extends String, ?> m3) {
        this.map.putAll(m3);
    }

    public JSONObject fluentPutAll(Map<? extends String, ?> m3) {
        this.map.putAll(m3);
        return this;
    }

    @Override
    public void clear() {
        this.map.clear();
    }

    public JSONObject fluentClear() {
        this.map.clear();
        return this;
    }

    @Override
    public Object remove(Object key) {
        return this.map.remove(key);
    }

    public JSONObject fluentRemove(Object key) {
        this.map.remove(key);
        return this;
    }

    @Override
    public Set<String> keySet() {
        return this.map.keySet();
    }

    @Override
    public Collection<Object> values() {
        return this.map.values();
    }

    @Override
    public Set<Map.Entry<String, Object>> entrySet() {
        return this.map.entrySet();
    }

    public JSONObject clone() {
        return new JSONObject(this.map instanceof LinkedHashMap ? new LinkedHashMap<String, Object>(this.map) : new HashMap<String, Object>(this.map));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof JSONObject) {
            return this.map.equals(((JSONObject)obj).map);
        }
        return this.map.equals(obj);
    }

    @Override
    public int hashCode() {
        return this.map.hashCode();
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args2) throws Throwable {
        Class<?>[] parameterTypes = method.getParameterTypes();
        if (parameterTypes.length == 1) {
            if (method.getName().equals("equals")) {
                return this.equals(args2[0]);
            }
            Class<?> returnType = method.getReturnType();
            if (returnType != Void.TYPE) {
                throw new JSONException("illegal setter");
            }
            String name = null;
            JSONField annotation = TypeUtils.getAnnotation(method, JSONField.class);
            if (annotation != null && annotation.name().length() != 0) {
                name = annotation.name();
            }
            if (name == null) {
                name = method.getName();
                if (!name.startsWith("set")) {
                    throw new JSONException("illegal setter");
                }
                if ((name = name.substring(3)).length() == 0) {
                    throw new JSONException("illegal setter");
                }
                name = Character.toLowerCase(name.charAt(0)) + name.substring(1);
            }
            this.map.put(name, args2[0]);
            return null;
        }
        if (parameterTypes.length == 0) {
            Class<?> returnType = method.getReturnType();
            if (returnType == Void.TYPE) {
                throw new JSONException("illegal getter");
            }
            String name = null;
            JSONField annotation = TypeUtils.getAnnotation(method, JSONField.class);
            if (annotation != null && annotation.name().length() != 0) {
                name = annotation.name();
            }
            if (name == null) {
                name = method.getName();
                if (name.startsWith("get")) {
                    if ((name = name.substring(3)).length() == 0) {
                        throw new JSONException("illegal getter");
                    }
                    name = Character.toLowerCase(name.charAt(0)) + name.substring(1);
                } else if (name.startsWith("is")) {
                    if ((name = name.substring(2)).length() == 0) {
                        throw new JSONException("illegal getter");
                    }
                    name = Character.toLowerCase(name.charAt(0)) + name.substring(1);
                } else {
                    if (name.startsWith("hashCode")) {
                        return this.hashCode();
                    }
                    if (name.startsWith("toString")) {
                        return this.toString();
                    }
                    throw new JSONException("illegal getter");
                }
            }
            Object value = this.map.get(name);
            return TypeUtils.cast(value, method.getGenericReturnType(), ParserConfig.getGlobalInstance());
        }
        throw new UnsupportedOperationException(method.toGenericString());
    }

    public Map<String, Object> getInnerMap() {
        return this.map;
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        SecureObjectInputStream.ensureFields();
        if (SecureObjectInputStream.fields != null && !SecureObjectInputStream.fields_error) {
            SecureObjectInputStream secIn = new SecureObjectInputStream(in);
            try {
                secIn.defaultReadObject();
                return;
            } catch (NotActiveException notActiveException) {
                // empty catch block
            }
        }
        in.defaultReadObject();
        for (Map.Entry<String, Object> entry : this.map.entrySet()) {
            Object value;
            String key = entry.getKey();
            if (key != null) {
                ParserConfig.global.checkAutoType(key.getClass());
            }
            if ((value = entry.getValue()) == null) continue;
            ParserConfig.global.checkAutoType(value.getClass());
        }
    }

    @Override
    public <T> T toJavaObject(Class<T> clazz) {
        if (clazz == Map.class || clazz == JSONObject.class || clazz == JSON.class) {
            return (T)this;
        }
        if (clazz == Object.class && !this.containsKey(JSON.DEFAULT_TYPE_KEY)) {
            return (T)this;
        }
        return TypeUtils.castToJavaBean(this, clazz, ParserConfig.getGlobalInstance());
    }

    public <T> T toJavaObject(Class<T> clazz, ParserConfig config, int features) {
        if (clazz == Map.class) {
            return (T)this;
        }
        if (clazz == Object.class && !this.containsKey(JSON.DEFAULT_TYPE_KEY)) {
            return (T)this;
        }
        return TypeUtils.castToJavaBean(this, clazz, config);
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    static class SecureObjectInputStream
    extends ObjectInputStream {
        static Field[] fields;
        static volatile boolean fields_error;

        static void ensureFields() {
            if (fields == null && !fields_error) {
                try {
                    Field[] declaredFields = ObjectInputStream.class.getDeclaredFields();
                    String[] fieldnames = new String[]{"bin", "passHandle", "handles", "curContext"};
                    Field[] array = new Field[fieldnames.length];
                    for (int i = 0; i < fieldnames.length; ++i) {
                        Field field = TypeUtils.getField(ObjectInputStream.class, fieldnames[i], declaredFields);
                        field.setAccessible(true);
                        array[i] = field;
                    }
                    fields = array;
                } catch (Throwable error) {
                    fields_error = true;
                }
            }
        }

        public SecureObjectInputStream(ObjectInputStream in) throws IOException {
            super(in);
            try {
                for (int i = 0; i < fields.length; ++i) {
                    Field field = fields[i];
                    Object value = field.get(in);
                    field.set(this, value);
                }
            } catch (IllegalAccessException e) {
                fields_error = true;
            }
        }

        @Override
        protected Class<?> resolveClass(ObjectStreamClass desc) throws IOException, ClassNotFoundException {
            String name = desc.getName();
            if (name.length() > 2) {
                int index = name.lastIndexOf(91);
                if (index != -1) {
                    name = name.substring(index + 1);
                }
                if (name.length() > 2 && name.charAt(0) == 'L' && name.charAt(name.length() - 1) == ';') {
                    name = name.substring(1, name.length() - 1);
                }
                if (TypeUtils.getClassFromMapping(name) == null) {
                    ParserConfig.global.checkAutoType(name, null, Feature.SupportAutoType.mask);
                }
            }
            return super.resolveClass(desc);
        }

        @Override
        protected Class<?> resolveProxyClass(String[] interfaces) throws IOException, ClassNotFoundException {
            for (String interfacename : interfaces) {
                if (TypeUtils.getClassFromMapping(interfacename) != null) continue;
                ParserConfig.global.checkAutoType(interfacename, null);
            }
            return super.resolveProxyClass(interfaces);
        }

        @Override
        protected void readStreamHeader() throws IOException, StreamCorruptedException {
        }
    }
}

