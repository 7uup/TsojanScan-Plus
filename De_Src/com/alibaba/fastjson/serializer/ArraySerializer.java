/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerialContext;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import java.io.IOException;
import java.lang.reflect.Type;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class ArraySerializer
implements ObjectSerializer {
    private final Class<?> componentType;
    private final ObjectSerializer compObjectSerializer;

    public ArraySerializer(Class<?> componentType, ObjectSerializer compObjectSerializer) {
        this.componentType = componentType;
        this.compObjectSerializer = compObjectSerializer;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public final void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
        SerializeWriter out = serializer.out;
        if (object == null) {
            out.writeNull(SerializerFeature.WriteNullListAsEmpty);
            return;
        }
        Object[] array = (Object[])object;
        int size = array.length;
        SerialContext context = serializer.context;
        serializer.setContext(context, object, fieldName, 0);
        try {
            out.append('[');
            for (int i = 0; i < size; ++i) {
                Object item;
                if (i != 0) {
                    out.append(',');
                }
                if ((item = array[i]) == null) {
                    if (out.isEnabled(SerializerFeature.WriteNullStringAsEmpty) && object instanceof String[]) {
                        out.writeString("");
                        continue;
                    }
                    out.append("null");
                    continue;
                }
                if (item.getClass() == this.componentType) {
                    this.compObjectSerializer.write(serializer, item, i, null, 0);
                    continue;
                }
                ObjectSerializer itemSerializer = serializer.getObjectWriter(item.getClass());
                itemSerializer.write(serializer, item, i, null, 0);
            }
            out.append(']');
        } finally {
            serializer.context = context;
        }
    }
}

