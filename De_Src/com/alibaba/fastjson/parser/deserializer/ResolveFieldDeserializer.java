/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.FieldDeserializer;
import com.alibaba.fastjson.util.TypeUtils;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public final class ResolveFieldDeserializer
extends FieldDeserializer {
    private final int index;
    private final List list;
    private final DefaultJSONParser parser;
    private final Object key;
    private final Map map;
    private final Collection collection;

    public ResolveFieldDeserializer(DefaultJSONParser parser, List list, int index) {
        super(null, null);
        this.parser = parser;
        this.index = index;
        this.list = list;
        this.key = null;
        this.map = null;
        this.collection = null;
    }

    public ResolveFieldDeserializer(Map map, Object index) {
        super(null, null);
        this.parser = null;
        this.index = -1;
        this.list = null;
        this.key = index;
        this.map = map;
        this.collection = null;
    }

    public ResolveFieldDeserializer(Collection collection) {
        super(null, null);
        this.parser = null;
        this.index = -1;
        this.list = null;
        this.key = null;
        this.map = null;
        this.collection = collection;
    }

    @Override
    public void setValue(Object object, Object value) {
        int arrayLength;
        JSONArray jsonArray;
        Object array;
        if (this.map != null) {
            this.map.put(this.key, value);
            return;
        }
        if (this.collection != null) {
            this.collection.add(value);
            return;
        }
        this.list.set(this.index, value);
        if (this.list instanceof JSONArray && (array = (jsonArray = (JSONArray)this.list).getRelatedArray()) != null && (arrayLength = Array.getLength(array)) > this.index) {
            Object item = jsonArray.getComponentType() != null ? TypeUtils.cast(value, jsonArray.getComponentType(), this.parser.getConfig()) : value;
            Array.set(array, this.index, item);
        }
    }

    @Override
    public void parseField(DefaultJSONParser parser, Object object, Type objectType, Map<String, Object> fieldValues) {
    }
}

