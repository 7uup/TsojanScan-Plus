/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.serializer.AfterFilter;
import com.alibaba.fastjson.serializer.BeanContext;
import com.alibaba.fastjson.serializer.BeforeFilter;
import com.alibaba.fastjson.serializer.FieldSerializer;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.LabelFilter;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerialContext;
import com.alibaba.fastjson.serializer.SerializeBeanInfo;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SerializeFilterable;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.util.FieldInfo;
import com.alibaba.fastjson.util.TypeUtils;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class JavaBeanSerializer
extends SerializeFilterable
implements ObjectSerializer {
    protected final FieldSerializer[] getters;
    protected final FieldSerializer[] sortedGetters;
    protected final SerializeBeanInfo beanInfo;
    private volatile transient long[] hashArray;
    private volatile transient short[] hashArrayMapping;

    public JavaBeanSerializer(Class<?> beanType) {
        this(beanType, (Map<String, String>)null);
    }

    public JavaBeanSerializer(Class<?> beanType, String ... aliasList) {
        this(beanType, JavaBeanSerializer.createAliasMap(aliasList));
    }

    static Map<String, String> createAliasMap(String ... aliasList) {
        HashMap<String, String> aliasMap = new HashMap<String, String>();
        for (String alias : aliasList) {
            aliasMap.put(alias, alias);
        }
        return aliasMap;
    }

    public JSONType getJSONType() {
        return this.beanInfo.jsonType;
    }

    public Class<?> getType() {
        return this.beanInfo.beanType;
    }

    public JavaBeanSerializer(Class<?> beanType, Map<String, String> aliasMap) {
        this(TypeUtils.buildBeanInfo(beanType, aliasMap, null));
    }

    public JavaBeanSerializer(SerializeBeanInfo beanInfo) {
        this.beanInfo = beanInfo;
        this.sortedGetters = new FieldSerializer[beanInfo.sortedFields.length];
        for (int i = 0; i < this.sortedGetters.length; ++i) {
            this.sortedGetters[i] = new FieldSerializer(beanInfo.beanType, beanInfo.sortedFields[i]);
        }
        if (beanInfo.fields == beanInfo.sortedFields) {
            this.getters = this.sortedGetters;
        } else {
            this.getters = new FieldSerializer[beanInfo.fields.length];
            boolean hashNotMatch = false;
            for (int i = 0; i < this.getters.length; ++i) {
                FieldSerializer fieldSerializer = this.getFieldSerializer(beanInfo.fields[i].name);
                if (fieldSerializer == null) {
                    hashNotMatch = true;
                    break;
                }
                this.getters[i] = fieldSerializer;
            }
            if (hashNotMatch) {
                System.arraycopy(this.sortedGetters, 0, this.getters, 0, this.sortedGetters.length);
            }
        }
        if (beanInfo.jsonType != null) {
            for (Class<? extends SerializeFilter> filterClass : beanInfo.jsonType.serialzeFilters()) {
                try {
                    SerializeFilter filter = filterClass.getConstructor(new Class[0]).newInstance(new Object[0]);
                    this.addFilter(filter);
                } catch (Exception exception) {
                    // empty catch block
                }
            }
        }
    }

    public void writeDirectNonContext(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
        this.write(serializer, object, fieldName, fieldType, features);
    }

    public void writeAsArray(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
        this.write(serializer, object, fieldName, fieldType, features);
    }

    public void writeAsArrayNonContext(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
        this.write(serializer, object, fieldName, fieldType, features);
    }

    @Override
    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
        this.write(serializer, object, fieldName, fieldType, features, false);
    }

    public void writeNoneASM(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
        this.write(serializer, object, fieldName, fieldType, features, false);
    }

    protected void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features, boolean unwrapped) throws IOException {
        SerializeWriter out = serializer.out;
        if (object == null) {
            out.writeNull();
            return;
        }
        if (this.writeReference(serializer, object, features)) {
            return;
        }
        FieldSerializer[] getters = out.sortField ? this.sortedGetters : this.getters;
        SerialContext parent = serializer.context;
        if (!this.beanInfo.beanType.isEnum()) {
            serializer.setContext(parent, object, fieldName, this.beanInfo.features, features);
        }
        boolean writeAsArray = this.isWriteAsArray(serializer, features);
        FieldSerializer errorFieldSerializer = null;
        try {
            Class<?> type;
            Class<?> objClass;
            char endSeperator;
            char startSeperator = writeAsArray ? (char)'[' : '{';
            char c = endSeperator = writeAsArray ? (char)']' : '}';
            if (!unwrapped) {
                out.append(startSeperator);
            }
            if (getters.length > 0 && out.isEnabled(SerializerFeature.PrettyFormat)) {
                serializer.incrementIndent();
                serializer.println();
            }
            boolean commaFlag = false;
            if (((this.beanInfo.features & SerializerFeature.WriteClassName.mask) != 0 || (features & SerializerFeature.WriteClassName.mask) != 0 || serializer.isWriteClassName(fieldType, object)) && objClass != (type = (objClass = object.getClass()) != fieldType && fieldType instanceof WildcardType ? TypeUtils.getClass(fieldType) : fieldType)) {
                this.writeClassName(serializer, this.beanInfo.typeKey, object);
                commaFlag = true;
            }
            char seperator = commaFlag ? (char)',' : '\u0000';
            boolean writeClassName = out.isEnabled(SerializerFeature.WriteClassName);
            char newSeperator = this.writeBefore(serializer, object, seperator);
            commaFlag = newSeperator == ',';
            boolean skipTransient = out.isEnabled(SerializerFeature.SkipTransientField);
            boolean ignoreNonFieldGetter = out.isEnabled(SerializerFeature.IgnoreNonFieldGetter);
            for (int i = 0; i < getters.length; ++i) {
                Class<?> fieldCLass;
                List propertyValue;
                boolean directWritePrefix;
                FieldSerializer fieldSerializer = getters[i];
                Field field = fieldSerializer.fieldInfo.field;
                FieldInfo fieldInfo = fieldSerializer.fieldInfo;
                String fieldInfoName = fieldInfo.name;
                Class<?> fieldClass = fieldInfo.fieldClass;
                boolean fieldUseSingleQuotes = SerializerFeature.isEnabled(out.features, fieldInfo.serialzeFeatures, SerializerFeature.UseSingleQuotes);
                boolean bl = directWritePrefix = out.quoteFieldNames && !fieldUseSingleQuotes;
                if (skipTransient && fieldInfo.fieldTransient || ignoreNonFieldGetter && field == null) continue;
                boolean notApply = false;
                if (!this.applyName(serializer, object, fieldInfoName) || !this.applyLabel(serializer, fieldInfo.label)) {
                    if (!writeAsArray) continue;
                    notApply = true;
                }
                if (fieldInfoName.equals(this.beanInfo.typeKey) && serializer.isWriteClassName(fieldType, object)) continue;
                if (notApply) {
                    propertyValue = null;
                } else {
                    try {
                        propertyValue = fieldSerializer.getPropertyValueDirect(object);
                    } catch (InvocationTargetException ex) {
                        errorFieldSerializer = fieldSerializer;
                        if (out.isEnabled(SerializerFeature.IgnoreErrorGetter)) {
                            propertyValue = null;
                        }
                        throw ex;
                    }
                }
                if (!this.apply(serializer, object, fieldInfoName, propertyValue)) continue;
                if (fieldClass == String.class && "trim".equals(fieldInfo.format) && propertyValue != null) {
                    propertyValue = ((String)((Object)propertyValue)).trim();
                }
                String key = fieldInfoName;
                key = this.processKey(serializer, object, key, propertyValue);
                List originalValue = propertyValue;
                if ((propertyValue = this.processValue(serializer, fieldSerializer.fieldContext, object, fieldInfoName, propertyValue, features)) == null) {
                    int defaultMask;
                    int serialzeFeatures = fieldInfo.serialzeFeatures;
                    JSONField jsonField = fieldInfo.getAnnotation();
                    if (this.beanInfo.jsonType != null) {
                        serialzeFeatures |= SerializerFeature.of(this.beanInfo.jsonType.serialzeFeatures());
                    }
                    if (jsonField != null && !"".equals(jsonField.defaultValue())) {
                        propertyValue = jsonField.defaultValue();
                    } else if (fieldClass == Boolean.class) {
                        defaultMask = SerializerFeature.WriteNullBooleanAsFalse.mask;
                        int mask = defaultMask | SerializerFeature.WriteMapNullValue.mask;
                        if (!writeAsArray && (serialzeFeatures & mask) == 0 && (out.features & mask) == 0) continue;
                        if ((serialzeFeatures & defaultMask) != 0) {
                            propertyValue = false;
                        } else if ((out.features & defaultMask) != 0 && (serialzeFeatures & SerializerFeature.WriteMapNullValue.mask) == 0) {
                            propertyValue = false;
                        }
                    } else if (fieldClass == String.class) {
                        defaultMask = SerializerFeature.WriteNullStringAsEmpty.mask;
                        int mask = defaultMask | SerializerFeature.WriteMapNullValue.mask;
                        if (!writeAsArray && (serialzeFeatures & mask) == 0 && (out.features & mask) == 0) continue;
                        if ((serialzeFeatures & defaultMask) != 0) {
                            propertyValue = "";
                        } else if ((out.features & defaultMask) != 0 && (serialzeFeatures & SerializerFeature.WriteMapNullValue.mask) == 0) {
                            propertyValue = "";
                        }
                    } else if (Number.class.isAssignableFrom(fieldClass)) {
                        defaultMask = SerializerFeature.WriteNullNumberAsZero.mask;
                        int mask = defaultMask | SerializerFeature.WriteMapNullValue.mask;
                        if (!writeAsArray && (serialzeFeatures & mask) == 0 && (out.features & mask) == 0) continue;
                        if ((serialzeFeatures & defaultMask) != 0) {
                            propertyValue = 0;
                        } else if ((out.features & defaultMask) != 0 && (serialzeFeatures & SerializerFeature.WriteMapNullValue.mask) == 0) {
                            propertyValue = 0;
                        }
                    } else if (Collection.class.isAssignableFrom(fieldClass)) {
                        defaultMask = SerializerFeature.WriteNullListAsEmpty.mask;
                        int mask = defaultMask | SerializerFeature.WriteMapNullValue.mask;
                        if (!writeAsArray && (serialzeFeatures & mask) == 0 && (out.features & mask) == 0) continue;
                        if ((serialzeFeatures & defaultMask) != 0) {
                            propertyValue = Collections.emptyList();
                        } else if ((out.features & defaultMask) != 0 && (serialzeFeatures & SerializerFeature.WriteMapNullValue.mask) == 0) {
                            propertyValue = Collections.emptyList();
                        }
                    } else if (!writeAsArray && !fieldSerializer.writeNull && !out.isEnabled(SerializerFeature.WriteMapNullValue.mask) && (serialzeFeatures & SerializerFeature.WriteMapNullValue.mask) == 0) continue;
                }
                if (propertyValue != null && (out.notWriteDefaultValue || (fieldInfo.serialzeFeatures & SerializerFeature.NotWriteDefaultValue.mask) != 0 || (this.beanInfo.features & SerializerFeature.NotWriteDefaultValue.mask) != 0) && ((fieldCLass = fieldInfo.fieldClass) == Byte.TYPE && propertyValue instanceof Byte && (Byte)((Object)propertyValue) == 0 || fieldCLass == Short.TYPE && propertyValue instanceof Short && (Short)((Object)propertyValue) == 0 || fieldCLass == Integer.TYPE && propertyValue instanceof Integer && (Integer)((Object)propertyValue) == 0 || fieldCLass == Long.TYPE && propertyValue instanceof Long && (Long)((Object)propertyValue) == 0L || fieldCLass == Float.TYPE && propertyValue instanceof Float && ((Float)((Object)propertyValue)).floatValue() == 0.0f || fieldCLass == Double.TYPE && propertyValue instanceof Double && (Double)((Object)propertyValue) == 0.0 || fieldCLass == Boolean.TYPE && propertyValue instanceof Boolean && !((Boolean)((Object)propertyValue)).booleanValue())) continue;
                if (commaFlag) {
                    if (fieldInfo.unwrapped && propertyValue instanceof Map && ((Map)((Object)propertyValue)).size() == 0) continue;
                    out.write(44);
                    if (out.isEnabled(SerializerFeature.PrettyFormat)) {
                        serializer.println();
                    }
                }
                if (key != fieldInfoName) {
                    if (!writeAsArray) {
                        out.writeFieldName(key, true);
                    }
                    serializer.write(propertyValue);
                } else if (originalValue != propertyValue) {
                    if (!writeAsArray) {
                        fieldSerializer.writePrefix(serializer);
                    }
                    serializer.write(propertyValue);
                } else {
                    if (!writeAsArray) {
                        boolean isJavaBean;
                        boolean isMap = Map.class.isAssignableFrom(fieldClass);
                        boolean bl2 = isJavaBean = !fieldClass.isPrimitive() && !fieldClass.getName().startsWith("java.") || fieldClass == Object.class;
                        if (writeClassName || !fieldInfo.unwrapped || !isMap && !isJavaBean) {
                            if (directWritePrefix) {
                                out.write(fieldInfo.name_chars, 0, fieldInfo.name_chars.length);
                            } else {
                                fieldSerializer.writePrefix(serializer);
                            }
                        }
                    }
                    if (!writeAsArray) {
                        JSONField fieldAnnotation = fieldInfo.getAnnotation();
                        if (fieldClass == String.class && (fieldAnnotation == null || fieldAnnotation.serializeUsing() == Void.class)) {
                            if (propertyValue == null) {
                                int serialzeFeatures = fieldSerializer.features;
                                if (this.beanInfo.jsonType != null) {
                                    serialzeFeatures |= SerializerFeature.of(this.beanInfo.jsonType.serialzeFeatures());
                                }
                                if ((out.features & SerializerFeature.WriteNullStringAsEmpty.mask) != 0 && (serialzeFeatures & SerializerFeature.WriteMapNullValue.mask) == 0) {
                                    out.writeString("");
                                } else if ((serialzeFeatures & SerializerFeature.WriteNullStringAsEmpty.mask) != 0) {
                                    out.writeString("");
                                } else {
                                    out.writeNull();
                                }
                            } else {
                                String propertyValueString = (String)((Object)propertyValue);
                                if (fieldUseSingleQuotes) {
                                    out.writeStringWithSingleQuote(propertyValueString);
                                } else {
                                    out.writeStringWithDoubleQuote(propertyValueString, '\u0000');
                                }
                            }
                        } else {
                            if (fieldInfo.unwrapped && propertyValue instanceof Map && ((Map)((Object)propertyValue)).size() == 0) {
                                commaFlag = false;
                                continue;
                            }
                            fieldSerializer.writeValue(serializer, propertyValue);
                        }
                    } else {
                        fieldSerializer.writeValue(serializer, propertyValue);
                    }
                }
                boolean fieldUnwrappedNull = false;
                if (fieldInfo.unwrapped && propertyValue instanceof Map) {
                    Map map = (Map)((Object)propertyValue);
                    if (map.size() == 0) {
                        fieldUnwrappedNull = true;
                    } else if (!serializer.isEnabled(SerializerFeature.WriteMapNullValue)) {
                        boolean hasNotNull = false;
                        for (Object value : map.values()) {
                            if (value == null) continue;
                            hasNotNull = true;
                            break;
                        }
                        if (!hasNotNull) {
                            fieldUnwrappedNull = true;
                        }
                    }
                }
                if (fieldUnwrappedNull) continue;
                commaFlag = true;
            }
            this.writeAfter(serializer, object, commaFlag ? (char)',' : '\u0000');
            if (getters.length > 0 && out.isEnabled(SerializerFeature.PrettyFormat)) {
                serializer.decrementIdent();
                serializer.println();
            }
            if (!unwrapped) {
                out.append(endSeperator);
            }
        } catch (Exception e) {
            String errorMessage = "write javaBean error, fastjson version 1.2.83";
            if (object != null) {
                errorMessage = errorMessage + ", class " + object.getClass().getName();
            }
            if (fieldName != null) {
                errorMessage = errorMessage + ", fieldName : " + fieldName;
            } else if (errorFieldSerializer != null && errorFieldSerializer.fieldInfo != null) {
                FieldInfo fieldInfo = errorFieldSerializer.fieldInfo;
                errorMessage = fieldInfo.method != null ? errorMessage + ", method : " + fieldInfo.method.getName() : errorMessage + ", fieldName : " + errorFieldSerializer.fieldInfo.name;
            }
            if (e.getMessage() != null) {
                errorMessage = errorMessage + ", " + e.getMessage();
            }
            Throwable cause = null;
            if (e instanceof InvocationTargetException) {
                cause = e.getCause();
            }
            if (cause == null) {
                cause = e;
            }
            throw new JSONException(errorMessage, cause);
        } finally {
            serializer.context = parent;
        }
    }

    protected void writeClassName(JSONSerializer serializer, String typeKey, Object object) {
        if (typeKey == null) {
            typeKey = serializer.config.typeKey;
        }
        serializer.out.writeFieldName(typeKey, false);
        String typeName = this.beanInfo.typeName;
        if (typeName == null) {
            Class<?> clazz = object.getClass();
            if (TypeUtils.isProxy(clazz)) {
                clazz = clazz.getSuperclass();
            }
            typeName = clazz.getName();
        }
        serializer.write(typeName);
    }

    public boolean writeReference(JSONSerializer serializer, Object object, int fieldFeatures) {
        SerialContext context = serializer.context;
        int mask = SerializerFeature.DisableCircularReferenceDetect.mask;
        if (context == null || (context.features & mask) != 0 || (fieldFeatures & mask) != 0) {
            return false;
        }
        if (serializer.references != null && serializer.references.containsKey(object)) {
            serializer.writeReference(object);
            return true;
        }
        return false;
    }

    protected boolean isWriteAsArray(JSONSerializer serializer) {
        return this.isWriteAsArray(serializer, 0);
    }

    protected boolean isWriteAsArray(JSONSerializer serializer, int fieldFeatrues) {
        int mask = SerializerFeature.BeanToArray.mask;
        return (this.beanInfo.features & mask) != 0 || serializer.out.beanToArray || (fieldFeatrues & mask) != 0;
    }

    public Object getFieldValue(Object object, String key) {
        FieldSerializer fieldDeser = this.getFieldSerializer(key);
        if (fieldDeser == null) {
            throw new JSONException("field not found. " + key);
        }
        try {
            return fieldDeser.getPropertyValue(object);
        } catch (InvocationTargetException ex) {
            throw new JSONException("getFieldValue error." + key, ex);
        } catch (IllegalAccessException ex) {
            throw new JSONException("getFieldValue error." + key, ex);
        }
    }

    public Object getFieldValue(Object object, String key, long keyHash, boolean throwFieldNotFoundException) {
        FieldSerializer fieldDeser = this.getFieldSerializer(keyHash);
        if (fieldDeser == null) {
            if (throwFieldNotFoundException) {
                throw new JSONException("field not found. " + key);
            }
            return null;
        }
        try {
            return fieldDeser.getPropertyValue(object);
        } catch (InvocationTargetException ex) {
            throw new JSONException("getFieldValue error." + key, ex);
        } catch (IllegalAccessException ex) {
            throw new JSONException("getFieldValue error." + key, ex);
        }
    }

    public FieldSerializer getFieldSerializer(String key) {
        if (key == null) {
            return null;
        }
        int low = 0;
        int high = this.sortedGetters.length - 1;
        while (low <= high) {
            int mid = low + high >>> 1;
            String fieldName = this.sortedGetters[mid].fieldInfo.name;
            int cmp = fieldName.compareTo(key);
            if (cmp < 0) {
                low = mid + 1;
                continue;
            }
            if (cmp > 0) {
                high = mid - 1;
                continue;
            }
            return this.sortedGetters[mid];
        }
        return null;
    }

    public FieldSerializer getFieldSerializer(long hash) {
        short getterIndex;
        int pos;
        String name;
        int i;
        PropertyNamingStrategy[] namingStrategies = null;
        if (this.hashArray == null) {
            namingStrategies = PropertyNamingStrategy.values();
            long[] hashArray = new long[this.sortedGetters.length * namingStrategies.length];
            int index = 0;
            for (i = 0; i < this.sortedGetters.length; ++i) {
                name = this.sortedGetters[i].fieldInfo.name;
                hashArray[index++] = TypeUtils.fnv1a_64(name);
                for (int j = 0; j < namingStrategies.length; ++j) {
                    String name_t = namingStrategies[j].translate(name);
                    if (name.equals(name_t)) continue;
                    hashArray[index++] = TypeUtils.fnv1a_64(name_t);
                }
            }
            Arrays.sort(hashArray, 0, index);
            this.hashArray = new long[index];
            System.arraycopy(hashArray, 0, this.hashArray, 0, index);
        }
        if ((pos = Arrays.binarySearch(this.hashArray, hash)) < 0) {
            return null;
        }
        if (this.hashArrayMapping == null) {
            if (namingStrategies == null) {
                namingStrategies = PropertyNamingStrategy.values();
            }
            short[] mapping = new short[this.hashArray.length];
            Arrays.fill(mapping, (short)-1);
            for (i = 0; i < this.sortedGetters.length; ++i) {
                name = this.sortedGetters[i].fieldInfo.name;
                int p = Arrays.binarySearch(this.hashArray, TypeUtils.fnv1a_64(name));
                if (p >= 0) {
                    mapping[p] = (short)i;
                }
                for (int j = 0; j < namingStrategies.length; ++j) {
                    int p_t;
                    String name_t = namingStrategies[j].translate(name);
                    if (name.equals(name_t) || (p_t = Arrays.binarySearch(this.hashArray, TypeUtils.fnv1a_64(name_t))) < 0) continue;
                    mapping[p_t] = (short)i;
                }
            }
            this.hashArrayMapping = mapping;
        }
        if ((getterIndex = this.hashArrayMapping[pos]) != -1) {
            return this.sortedGetters[getterIndex];
        }
        return null;
    }

    public List<Object> getFieldValues(Object object) throws Exception {
        ArrayList<Object> fieldValues = new ArrayList<Object>(this.sortedGetters.length);
        for (FieldSerializer getter : this.sortedGetters) {
            fieldValues.add(getter.getPropertyValue(object));
        }
        return fieldValues;
    }

    public List<Object> getObjectFieldValues(Object object) throws Exception {
        ArrayList<Object> fieldValues = new ArrayList<Object>(this.sortedGetters.length);
        for (FieldSerializer getter : this.sortedGetters) {
            Class<?> fieldClass = getter.fieldInfo.fieldClass;
            if (fieldClass.isPrimitive() || fieldClass.getName().startsWith("java.lang.")) continue;
            fieldValues.add(getter.getPropertyValue(object));
        }
        return fieldValues;
    }

    public int getSize(Object object) throws Exception {
        int size = 0;
        for (FieldSerializer getter : this.sortedGetters) {
            Object value = getter.getPropertyValueDirect(object);
            if (value == null) continue;
            ++size;
        }
        return size;
    }

    public Set<String> getFieldNames(Object object) throws Exception {
        HashSet<String> fieldNames = new HashSet<String>();
        for (FieldSerializer getter : this.sortedGetters) {
            Object value = getter.getPropertyValueDirect(object);
            if (value == null) continue;
            fieldNames.add(getter.fieldInfo.name);
        }
        return fieldNames;
    }

    public Map<String, Object> getFieldValuesMap(Object object) throws Exception {
        LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>(this.sortedGetters.length);
        boolean skipTransient = true;
        FieldInfo fieldInfo = null;
        for (FieldSerializer getter : this.sortedGetters) {
            skipTransient = SerializerFeature.isEnabled(getter.features, SerializerFeature.SkipTransientField);
            fieldInfo = getter.fieldInfo;
            if (skipTransient && fieldInfo != null && fieldInfo.fieldTransient) continue;
            if (getter.fieldInfo.unwrapped) {
                Object unwrappedValue = getter.getPropertyValue(object);
                Object map1 = JSON.toJSON(unwrappedValue);
                if (map1 instanceof Map) {
                    map.putAll((Map)map1);
                    continue;
                }
                map.put(getter.fieldInfo.name, getter.getPropertyValue(object));
                continue;
            }
            map.put(getter.fieldInfo.name, getter.getPropertyValue(object));
        }
        return map;
    }

    protected BeanContext getBeanContext(int orinal) {
        return this.sortedGetters[orinal].fieldContext;
    }

    protected Type getFieldType(int ordinal) {
        return this.sortedGetters[ordinal].fieldInfo.fieldType;
    }

    protected char writeBefore(JSONSerializer jsonBeanDeser, Object object, char seperator) {
        if (jsonBeanDeser.beforeFilters != null) {
            for (BeforeFilter beforeFilter : jsonBeanDeser.beforeFilters) {
                seperator = beforeFilter.writeBefore(jsonBeanDeser, object, seperator);
            }
        }
        if (this.beforeFilters != null) {
            for (BeforeFilter beforeFilter : this.beforeFilters) {
                seperator = beforeFilter.writeBefore(jsonBeanDeser, object, seperator);
            }
        }
        return seperator;
    }

    protected char writeAfter(JSONSerializer jsonBeanDeser, Object object, char seperator) {
        if (jsonBeanDeser.afterFilters != null) {
            for (AfterFilter afterFilter : jsonBeanDeser.afterFilters) {
                seperator = afterFilter.writeAfter(jsonBeanDeser, object, seperator);
            }
        }
        if (this.afterFilters != null) {
            for (AfterFilter afterFilter : this.afterFilters) {
                seperator = afterFilter.writeAfter(jsonBeanDeser, object, seperator);
            }
        }
        return seperator;
    }

    protected boolean applyLabel(JSONSerializer jsonBeanDeser, String label) {
        if (jsonBeanDeser.labelFilters != null) {
            for (LabelFilter propertyFilter : jsonBeanDeser.labelFilters) {
                if (propertyFilter.apply(label)) continue;
                return false;
            }
        }
        if (this.labelFilters != null) {
            for (LabelFilter propertyFilter : this.labelFilters) {
                if (propertyFilter.apply(label)) continue;
                return false;
            }
        }
        return true;
    }
}

