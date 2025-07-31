/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  springfox.documentation.spring.web.json.Json
 */
package com.alibaba.fastjson.support.springfox;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import springfox.documentation.spring.web.json.Json;

public class SwaggerJsonSerializer
implements ObjectSerializer {
    public static final SwaggerJsonSerializer instance = new SwaggerJsonSerializer();

    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
        SerializeWriter out = serializer.getWriter();
        Json json = (Json)object;
        String value = json.value();
        out.write(value);
    }
}

