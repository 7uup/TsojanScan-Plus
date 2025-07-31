/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.util.TypeUtils;
import java.io.IOException;
import java.io.NotActiveException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.RandomAccess;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class JSONArray
extends JSON
implements List<Object>,
Cloneable,
RandomAccess,
Serializable {
    private static final long serialVersionUID = 1L;
    private final List<Object> list;
    protected transient Object relatedArray;
    protected transient Type componentType;

    public JSONArray() {
        this.list = new ArrayList<Object>();
    }

    public JSONArray(List<Object> list) {
        if (list == null) {
            throw new IllegalArgumentException("list is null.");
        }
        this.list = list;
    }

    public JSONArray(int initialCapacity) {
        this.list = new ArrayList<Object>(initialCapacity);
    }

    public Object getRelatedArray() {
        return this.relatedArray;
    }

    public void setRelatedArray(Object relatedArray) {
        this.relatedArray = relatedArray;
    }

    public Type getComponentType() {
        return this.componentType;
    }

    public void setComponentType(Type componentType) {
        this.componentType = componentType;
    }

    @Override
    public int size() {
        return this.list.size();
    }

    @Override
    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return this.list.contains(o);
    }

    @Override
    public Iterator<Object> iterator() {
        return this.list.iterator();
    }

    @Override
    public Object[] toArray() {
        return this.list.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return this.list.toArray(a);
    }

    @Override
    public boolean add(Object e) {
        return this.list.add(e);
    }

    public JSONArray fluentAdd(Object e) {
        this.list.add(e);
        return this;
    }

    @Override
    public boolean remove(Object o) {
        return this.list.remove(o);
    }

    public JSONArray fluentRemove(Object o) {
        this.list.remove(o);
        return this;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return this.list.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<?> c) {
        return this.list.addAll(c);
    }

    public JSONArray fluentAddAll(Collection<?> c) {
        this.list.addAll(c);
        return this;
    }

    @Override
    public boolean addAll(int index, Collection<?> c) {
        return this.list.addAll(index, c);
    }

    public JSONArray fluentAddAll(int index, Collection<?> c) {
        this.list.addAll(index, c);
        return this;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return this.list.removeAll(c);
    }

    public JSONArray fluentRemoveAll(Collection<?> c) {
        this.list.removeAll(c);
        return this;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return this.list.retainAll(c);
    }

    public JSONArray fluentRetainAll(Collection<?> c) {
        this.list.retainAll(c);
        return this;
    }

    @Override
    public void clear() {
        this.list.clear();
    }

    public JSONArray fluentClear() {
        this.list.clear();
        return this;
    }

    @Override
    public Object set(int index, Object element) {
        if (index == -1) {
            this.list.add(element);
            return null;
        }
        if (this.list.size() <= index) {
            for (int i = this.list.size(); i < index; ++i) {
                this.list.add(null);
            }
            this.list.add(element);
            return null;
        }
        return this.list.set(index, element);
    }

    public JSONArray fluentSet(int index, Object element) {
        this.set(index, element);
        return this;
    }

    @Override
    public void add(int index, Object element) {
        this.list.add(index, element);
    }

    public JSONArray fluentAdd(int index, Object element) {
        this.list.add(index, element);
        return this;
    }

    @Override
    public Object remove(int index) {
        return this.list.remove(index);
    }

    public JSONArray fluentRemove(int index) {
        this.list.remove(index);
        return this;
    }

    @Override
    public int indexOf(Object o) {
        return this.list.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return this.list.lastIndexOf(o);
    }

    @Override
    public ListIterator<Object> listIterator() {
        return this.list.listIterator();
    }

    @Override
    public ListIterator<Object> listIterator(int index) {
        return this.list.listIterator(index);
    }

    @Override
    public List<Object> subList(int fromIndex, int toIndex) {
        return this.list.subList(fromIndex, toIndex);
    }

    @Override
    public Object get(int index) {
        return this.list.get(index);
    }

    public JSONObject getJSONObject(int index) {
        Object value = this.list.get(index);
        if (value instanceof JSONObject) {
            return (JSONObject)value;
        }
        if (value instanceof Map) {
            return new JSONObject((Map)value);
        }
        return (JSONObject)JSONArray.toJSON(value);
    }

    public JSONArray getJSONArray(int index) {
        Object value = this.list.get(index);
        if (value instanceof JSONArray) {
            return (JSONArray)value;
        }
        if (value instanceof List) {
            return new JSONArray((List)value);
        }
        return (JSONArray)JSONArray.toJSON(value);
    }

    public <T> T getObject(int index, Class<T> clazz) {
        Object obj = this.list.get(index);
        return TypeUtils.castToJavaBean(obj, clazz);
    }

    public <T> T getObject(int index, Type type) {
        Object obj = this.list.get(index);
        if (type instanceof Class) {
            return TypeUtils.castToJavaBean(obj, (Class)type);
        }
        String json = JSON.toJSONString(obj);
        return JSON.parseObject(json, type, new Feature[0]);
    }

    public Boolean getBoolean(int index) {
        Object value = this.get(index);
        if (value == null) {
            return null;
        }
        return TypeUtils.castToBoolean(value);
    }

    public boolean getBooleanValue(int index) {
        Object value = this.get(index);
        if (value == null) {
            return false;
        }
        return TypeUtils.castToBoolean(value);
    }

    public Byte getByte(int index) {
        Object value = this.get(index);
        return TypeUtils.castToByte(value);
    }

    public byte getByteValue(int index) {
        Object value = this.get(index);
        Byte byteVal = TypeUtils.castToByte(value);
        if (byteVal == null) {
            return 0;
        }
        return byteVal;
    }

    public Short getShort(int index) {
        Object value = this.get(index);
        return TypeUtils.castToShort(value);
    }

    public short getShortValue(int index) {
        Object value = this.get(index);
        Short shortVal = TypeUtils.castToShort(value);
        if (shortVal == null) {
            return 0;
        }
        return shortVal;
    }

    public Integer getInteger(int index) {
        Object value = this.get(index);
        return TypeUtils.castToInt(value);
    }

    public int getIntValue(int index) {
        Object value = this.get(index);
        Integer intVal = TypeUtils.castToInt(value);
        if (intVal == null) {
            return 0;
        }
        return intVal;
    }

    public Long getLong(int index) {
        Object value = this.get(index);
        return TypeUtils.castToLong(value);
    }

    public long getLongValue(int index) {
        Object value = this.get(index);
        Long longVal = TypeUtils.castToLong(value);
        if (longVal == null) {
            return 0L;
        }
        return longVal;
    }

    public Float getFloat(int index) {
        Object value = this.get(index);
        return TypeUtils.castToFloat(value);
    }

    public float getFloatValue(int index) {
        Object value = this.get(index);
        Float floatValue = TypeUtils.castToFloat(value);
        if (floatValue == null) {
            return 0.0f;
        }
        return floatValue.floatValue();
    }

    public Double getDouble(int index) {
        Object value = this.get(index);
        return TypeUtils.castToDouble(value);
    }

    public double getDoubleValue(int index) {
        Object value = this.get(index);
        Double doubleValue = TypeUtils.castToDouble(value);
        if (doubleValue == null) {
            return 0.0;
        }
        return doubleValue;
    }

    public BigDecimal getBigDecimal(int index) {
        Object value = this.get(index);
        return TypeUtils.castToBigDecimal(value);
    }

    public BigInteger getBigInteger(int index) {
        Object value = this.get(index);
        return TypeUtils.castToBigInteger(value);
    }

    public String getString(int index) {
        Object value = this.get(index);
        return TypeUtils.castToString(value);
    }

    public Date getDate(int index) {
        Object value = this.get(index);
        return TypeUtils.castToDate(value);
    }

    public Object getSqlDate(int index) {
        Object value = this.get(index);
        return TypeUtils.castToSqlDate(value);
    }

    public Object getTimestamp(int index) {
        Object value = this.get(index);
        return TypeUtils.castToTimestamp(value);
    }

    public <T> List<T> toJavaList(Class<T> clazz) {
        ArrayList<T> list = new ArrayList<T>(this.size());
        ParserConfig config = ParserConfig.getGlobalInstance();
        for (Object item : this) {
            T classItem = TypeUtils.cast(item, clazz, config);
            list.add(classItem);
        }
        return list;
    }

    public Object clone() {
        return new JSONArray(new ArrayList<Object>(this.list));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof JSONArray) {
            return this.list.equals(((JSONArray)obj).list);
        }
        return this.list.equals(obj);
    }

    @Override
    public int hashCode() {
        return this.list.hashCode();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        JSONObject.SecureObjectInputStream.ensureFields();
        if (JSONObject.SecureObjectInputStream.fields != null && !JSONObject.SecureObjectInputStream.fields_error) {
            JSONObject.SecureObjectInputStream secIn = new JSONObject.SecureObjectInputStream(in);
            try {
                secIn.defaultReadObject();
                return;
            } catch (NotActiveException notActiveException) {
                // empty catch block
            }
        }
        in.defaultReadObject();
        for (Object item : this.list) {
            String typeName;
            if (item == null || TypeUtils.getClassFromMapping(typeName = item.getClass().getName()) != null) continue;
            ParserConfig.global.checkAutoType(typeName, null);
        }
    }
}

