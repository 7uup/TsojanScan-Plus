/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.util.FieldInfo;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class SerializeBeanInfo {
    protected final Class<?> beanType;
    protected final String typeName;
    protected final String typeKey;
    protected final JSONType jsonType;
    protected final FieldInfo[] fields;
    protected final FieldInfo[] sortedFields;
    protected int features;

    public SerializeBeanInfo(Class<?> beanType, JSONType jsonType, String typeName, String typeKey, int features, FieldInfo[] fields, FieldInfo[] sortedFields) {
        this.beanType = beanType;
        this.jsonType = jsonType;
        this.typeName = typeName;
        this.typeKey = typeKey;
        this.features = features;
        this.fields = fields;
        this.sortedFields = sortedFields;
    }
}

