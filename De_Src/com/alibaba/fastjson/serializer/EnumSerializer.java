/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

public class EnumSerializer
implements ObjectSerializer {
    private final Member member;
    public static final EnumSerializer instance = new EnumSerializer();

    public EnumSerializer() {
        this.member = null;
    }

    public EnumSerializer(Member member) {
        this.member = member;
    }

    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
        if (this.member == null) {
            SerializeWriter out = serializer.out;
            out.writeEnum((Enum)object);
            return;
        }
        Object fieldValue = null;
        try {
            fieldValue = this.member instanceof Field ? ((Field)this.member).get(object) : ((Method)this.member).invoke(object, new Object[0]);
        } catch (Exception e) {
            throw new JSONException("getEnumValue error", e);
        }
        serializer.write(fieldValue);
    }
}

