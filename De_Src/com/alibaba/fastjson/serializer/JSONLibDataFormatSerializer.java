/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Date;

public class JSONLibDataFormatSerializer
implements ObjectSerializer {
    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
        if (object == null) {
            serializer.out.writeNull();
            return;
        }
        Date date = (Date)object;
        JSONObject json = new JSONObject();
        json.put("date", (Object)date.getDate());
        json.put("day", (Object)date.getDay());
        json.put("hours", (Object)date.getHours());
        json.put("minutes", (Object)date.getMinutes());
        json.put("month", (Object)date.getMonth());
        json.put("seconds", (Object)date.getSeconds());
        json.put("time", (Object)date.getTime());
        json.put("timezoneOffset", (Object)date.getTimezoneOffset());
        json.put("year", (Object)date.getYear());
        serializer.write(json);
    }
}

