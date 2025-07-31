/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.JavaBeanSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerialContext;
import com.alibaba.fastjson.serializer.SerializeFilterable;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class MapSerializer
extends SerializeFilterable
implements ObjectSerializer {
    public static MapSerializer instance = new MapSerializer();
    private static final int NON_STRINGKEY_AS_STRING = SerializerFeature.of(new SerializerFeature[]{SerializerFeature.BrowserCompatible, SerializerFeature.WriteNonStringKeyAsString, SerializerFeature.BrowserSecure});

    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
        this.write(serializer, object, fieldName, fieldType, features, false);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features, boolean unwrapped) throws IOException {
        SerializeWriter out = serializer.out;
        if (object == null) {
            out.writeNull();
            return;
        }
        Map<String, Object> map = (Map<String, Object>)object;
        int mapSortFieldMask = SerializerFeature.MapSortField.mask;
        if ((out.features & mapSortFieldMask) != 0 || (features & mapSortFieldMask) != 0) {
            if (map instanceof JSONObject) {
                map = ((JSONObject)map).getInnerMap();
            }
            if (!(map instanceof SortedMap) && !(map instanceof LinkedHashMap)) {
                try {
                    map = new TreeMap<String, Object>(map);
                } catch (Exception exception) {
                    // empty catch block
                }
            }
        }
        if (serializer.containsReference(object)) {
            serializer.writeReference(object);
            return;
        }
        SerialContext parent = serializer.context;
        serializer.setContext(parent, object, fieldName, 0);
        try {
            if (!unwrapped) {
                out.write(123);
            }
            serializer.incrementIndent();
            Class<?> preClazz = null;
            ObjectSerializer preWriter = null;
            boolean first = true;
            if (out.isEnabled(SerializerFeature.WriteClassName)) {
                boolean containsKey;
                String typeKey = serializer.config.typeKey;
                Class<?> mapClass = map.getClass();
                boolean bl = containsKey = (mapClass == JSONObject.class || mapClass == HashMap.class || mapClass == LinkedHashMap.class) && map.containsKey(typeKey);
                if (!containsKey) {
                    out.writeFieldName(typeKey);
                    out.writeString(object.getClass().getName());
                    first = false;
                }
            }
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                String strKey;
                Object value = entry.getValue();
                String entryKey = entry.getKey();
                List preFilters = serializer.propertyPreFilters;
                if (preFilters != null && preFilters.size() > 0 && (entryKey != null && !(entryKey instanceof String) ? (entryKey.getClass().isPrimitive() || entryKey instanceof Number) && !this.applyName(serializer, object, strKey = JSON.toJSONString(entryKey)) : !this.applyName(serializer, object, entryKey))) continue;
                preFilters = this.propertyPreFilters;
                if (preFilters != null && preFilters.size() > 0 && (entryKey != null && !(entryKey instanceof String) ? (entryKey.getClass().isPrimitive() || entryKey instanceof Number) && !this.applyName(serializer, object, strKey = JSON.toJSONString(entryKey)) : !this.applyName(serializer, object, entryKey))) continue;
                List propertyFilters = serializer.propertyFilters;
                if (propertyFilters != null && propertyFilters.size() > 0 && (entryKey != null && !(entryKey instanceof String) ? (entryKey.getClass().isPrimitive() || entryKey instanceof Number) && !this.apply(serializer, object, strKey = JSON.toJSONString(entryKey), value) : !this.apply(serializer, object, entryKey, value))) continue;
                propertyFilters = this.propertyFilters;
                if (propertyFilters != null && propertyFilters.size() > 0 && (entryKey != null && !(entryKey instanceof String) ? (entryKey.getClass().isPrimitive() || entryKey instanceof Number) && !this.apply(serializer, object, strKey = JSON.toJSONString(entryKey), value) : !this.apply(serializer, object, entryKey, value))) continue;
                List nameFilters = serializer.nameFilters;
                if (nameFilters != null && nameFilters.size() > 0) {
                    if (entryKey == null || entryKey instanceof String) {
                        entryKey = this.processKey(serializer, object, entryKey, value);
                    } else if (entryKey.getClass().isPrimitive() || entryKey instanceof Number) {
                        strKey = JSON.toJSONString(entryKey);
                        entryKey = this.processKey(serializer, object, strKey, value);
                    }
                }
                if ((nameFilters = this.nameFilters) != null && nameFilters.size() > 0) {
                    if (entryKey == null || entryKey instanceof String) {
                        entryKey = this.processKey(serializer, object, entryKey, value);
                    } else if (entryKey.getClass().isPrimitive() || entryKey instanceof Number) {
                        strKey = JSON.toJSONString(entryKey);
                        entryKey = this.processKey(serializer, object, strKey, value);
                    }
                }
                if (entryKey == null || entryKey instanceof String) {
                    value = this.processValue(serializer, null, object, entryKey, value, features);
                } else {
                    boolean objectOrArray;
                    boolean bl = objectOrArray = entryKey instanceof Map || entryKey instanceof Collection;
                    if (!objectOrArray) {
                        strKey = JSON.toJSONString(entryKey);
                        value = this.processValue(serializer, null, object, strKey, value, features);
                    }
                }
                if (value == null && !SerializerFeature.isEnabled(out.features, features, SerializerFeature.WriteMapNullValue)) continue;
                if (entryKey instanceof String) {
                    String key = entryKey;
                    if (!first) {
                        out.write(44);
                    }
                    if (out.isEnabled(SerializerFeature.PrettyFormat)) {
                        serializer.println();
                    }
                    out.writeFieldName(key, true);
                } else {
                    if (!first) {
                        out.write(44);
                    }
                    if ((out.isEnabled(NON_STRINGKEY_AS_STRING) || SerializerFeature.isEnabled(features, SerializerFeature.WriteNonStringKeyAsString)) && !(entryKey instanceof Enum)) {
                        String strEntryKey = JSON.toJSONString(entryKey);
                        serializer.write(strEntryKey);
                    } else {
                        serializer.write((Object)entryKey);
                    }
                    out.write(58);
                }
                first = false;
                if (value == null) {
                    out.writeNull();
                    continue;
                }
                Class<?> clazz = value.getClass();
                if (clazz != preClazz) {
                    preClazz = clazz;
                    preWriter = serializer.getObjectWriter(clazz);
                }
                if (SerializerFeature.isEnabled(features, SerializerFeature.WriteClassName) && preWriter instanceof JavaBeanSerializer) {
                    ParameterizedType parameterizedType;
                    Type[] actualTypeArguments;
                    Type valueType = null;
                    if (fieldType instanceof ParameterizedType && (actualTypeArguments = (parameterizedType = (ParameterizedType)fieldType).getActualTypeArguments()).length == 2) {
                        valueType = actualTypeArguments[1];
                    }
                    JavaBeanSerializer javaBeanSerializer = (JavaBeanSerializer)preWriter;
                    javaBeanSerializer.writeNoneASM(serializer, value, entryKey, valueType, features);
                    continue;
                }
                preWriter.write(serializer, value, entryKey, null, features);
            }
        } finally {
            serializer.context = parent;
        }
        serializer.decrementIdent();
        if (out.isEnabled(SerializerFeature.PrettyFormat) && map.size() > 0) {
            serializer.println();
        }
        if (!unwrapped) {
            out.write(125);
        }
    }
}

