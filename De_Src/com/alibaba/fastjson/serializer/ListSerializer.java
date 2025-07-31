/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.JavaBeanSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerialContext;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.util.TypeUtils;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public final class ListSerializer
implements ObjectSerializer {
    public static final ListSerializer instance = new ListSerializer();

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
        boolean writeClassName = serializer.out.isEnabled(SerializerFeature.WriteClassName) || SerializerFeature.isEnabled(features, SerializerFeature.WriteClassName);
        SerializeWriter out = serializer.out;
        Type elementType = null;
        if (writeClassName) {
            elementType = TypeUtils.getCollectionItemType(fieldType);
        }
        if (object == null) {
            out.writeNull(SerializerFeature.WriteNullListAsEmpty);
            return;
        }
        List list = (List)object;
        if (list.size() == 0) {
            out.append("[]");
            return;
        }
        SerialContext context = serializer.context;
        serializer.setContext(context, object, fieldName, 0);
        ObjectSerializer itemSerializer = null;
        try {
            if (out.isEnabled(SerializerFeature.PrettyFormat)) {
                out.append('[');
                serializer.incrementIndent();
                int i = 0;
                for (Object item : list) {
                    if (i != 0) {
                        out.append(',');
                    }
                    serializer.println();
                    if (item != null) {
                        if (serializer.containsReference(item)) {
                            serializer.writeReference(item);
                        } else {
                            SerialContext itemContext;
                            itemSerializer = serializer.getObjectWriter(item.getClass());
                            serializer.context = itemContext = new SerialContext(context, object, fieldName, 0, 0);
                            itemSerializer.write(serializer, item, i, elementType, features);
                        }
                    } else {
                        serializer.out.writeNull();
                    }
                    ++i;
                }
                serializer.decrementIdent();
                serializer.println();
                out.append(']');
                return;
            }
            out.append('[');
            int size = list.size();
            for (int i = 0; i < size; ++i) {
                Object item = list.get(i);
                if (i != 0) {
                    out.append(',');
                }
                if (item == null) {
                    out.append("null");
                    continue;
                }
                Class<?> clazz = item.getClass();
                if (clazz == Integer.class) {
                    out.writeInt((Integer)item);
                    continue;
                }
                if (clazz == Long.class) {
                    long val = (Long)item;
                    if (writeClassName) {
                        out.writeLong(val);
                        out.write(76);
                        continue;
                    }
                    out.writeLong(val);
                    continue;
                }
                if ((SerializerFeature.DisableCircularReferenceDetect.mask & features) != 0) {
                    itemSerializer = serializer.getObjectWriter(item.getClass());
                    itemSerializer.write(serializer, item, i, elementType, features);
                    continue;
                }
                if (!out.disableCircularReferenceDetect) {
                    SerialContext itemContext;
                    serializer.context = itemContext = new SerialContext(context, object, fieldName, 0, 0);
                }
                if (serializer.containsReference(item)) {
                    serializer.writeReference(item);
                    continue;
                }
                itemSerializer = serializer.getObjectWriter(item.getClass());
                if ((SerializerFeature.WriteClassName.mask & features) != 0 && itemSerializer instanceof JavaBeanSerializer) {
                    JavaBeanSerializer javaBeanSerializer = (JavaBeanSerializer)itemSerializer;
                    javaBeanSerializer.writeNoneASM(serializer, item, i, elementType, features);
                    continue;
                }
                itemSerializer.write(serializer, item, i, elementType, features);
            }
            out.append(']');
        } finally {
            serializer.context = context;
        }
    }
}

