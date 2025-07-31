/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.sql.Clob;
import java.sql.SQLException;

public class ClobSerializer
implements ObjectSerializer {
    public static final ClobSerializer instance = new ClobSerializer();

    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
        try {
            if (object == null) {
                serializer.writeNull();
                return;
            }
            Clob clob = (Clob)object;
            Reader reader = clob.getCharacterStream();
            StringBuilder buf = new StringBuilder();
            try {
                int len;
                char[] chars = new char[2048];
                while ((len = reader.read(chars, 0, chars.length)) >= 0) {
                    buf.append(chars, 0, len);
                }
            } catch (Exception ex) {
                throw new JSONException("read string from reader error", ex);
            }
            String text = buf.toString();
            reader.close();
            serializer.write(text);
        } catch (SQLException e) {
            throw new IOException("write clob error", e);
        }
    }
}

