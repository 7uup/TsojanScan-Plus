/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.JSONSerializable;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class JSONPObject
implements JSONSerializable {
    public static String SECURITY_PREFIX = "/**/";
    private String function;
    private final List<Object> parameters = new ArrayList<Object>();

    public JSONPObject() {
    }

    public JSONPObject(String function) {
        this.function = function;
    }

    public String getFunction() {
        return this.function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public List<Object> getParameters() {
        return this.parameters;
    }

    public void addParameter(Object parameter) {
        this.parameters.add(parameter);
    }

    public String toJSONString() {
        return this.toString();
    }

    @Override
    public void write(JSONSerializer serializer, Object fieldName, Type fieldType, int features) throws IOException {
        SerializeWriter writer = serializer.out;
        if ((features & SerializerFeature.BrowserSecure.mask) != 0 || writer.isEnabled(SerializerFeature.BrowserSecure.mask)) {
            writer.write(SECURITY_PREFIX);
        }
        writer.write(this.function);
        writer.write(40);
        for (int i = 0; i < this.parameters.size(); ++i) {
            if (i != 0) {
                writer.write(44);
            }
            serializer.write(this.parameters.get(i));
        }
        writer.write(41);
    }

    public String toString() {
        return JSON.toJSONString(this);
    }
}

