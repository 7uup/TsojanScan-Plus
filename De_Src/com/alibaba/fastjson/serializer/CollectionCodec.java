/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.JavaBeanSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerialContext;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.util.TypeUtils;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashSet;
import java.util.TreeSet;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class CollectionCodec
implements ObjectSerializer,
ObjectDeserializer {
    public static final CollectionCodec instance = new CollectionCodec();

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
        SerializeWriter out = serializer.out;
        if (object == null) {
            out.writeNull(SerializerFeature.WriteNullListAsEmpty);
            return;
        }
        Type elementType = null;
        if (out.isEnabled(SerializerFeature.WriteClassName) || SerializerFeature.isEnabled(features, SerializerFeature.WriteClassName)) {
            elementType = TypeUtils.getCollectionItemType(fieldType);
        }
        Collection collection = (Collection)object;
        SerialContext context = serializer.context;
        serializer.setContext(context, object, fieldName, 0);
        if (out.isEnabled(SerializerFeature.WriteClassName)) {
            if (HashSet.class.isAssignableFrom(collection.getClass())) {
                out.append("Set");
            } else if (TreeSet.class == collection.getClass()) {
                out.append("TreeSet");
            }
        }
        try {
            int i = 0;
            out.append('[');
            for (Object item : collection) {
                if (i++ != 0) {
                    out.append(',');
                }
                if (item == null) {
                    out.writeNull();
                    continue;
                }
                Class<?> clazz = item.getClass();
                if (clazz == Integer.class) {
                    out.writeInt((Integer)item);
                    continue;
                }
                if (clazz == Long.class) {
                    out.writeLong((Long)item);
                    if (!out.isEnabled(SerializerFeature.WriteClassName)) continue;
                    out.write(76);
                    continue;
                }
                ObjectSerializer itemSerializer = serializer.getObjectWriter(clazz);
                if (SerializerFeature.isEnabled(features, SerializerFeature.WriteClassName) && itemSerializer instanceof JavaBeanSerializer) {
                    JavaBeanSerializer javaBeanSerializer = (JavaBeanSerializer)itemSerializer;
                    javaBeanSerializer.writeNoneASM(serializer, item, i - 1, elementType, features);
                    continue;
                }
                itemSerializer.write(serializer, item, i - 1, elementType, features);
            }
            out.append(']');
        } finally {
            serializer.context = context;
        }
    }

    @Override
    public <T> T deserialze(DefaultJSONParser parser, Type type, Object fieldName) {
        Collection list;
        if (parser.lexer.token() == 8) {
            parser.lexer.nextToken(16);
            return null;
        }
        if (type == JSONArray.class) {
            JSONArray array = new JSONArray();
            parser.parseArray(array);
            return (T)array;
        }
        if (parser.lexer.token() == 21) {
            parser.lexer.nextToken();
            list = TypeUtils.createSet(type);
        } else {
            list = TypeUtils.createCollection(type);
        }
        Type itemType = TypeUtils.getCollectionItemType(type);
        parser.parseArray(itemType, list, fieldName);
        return (T)list;
    }

    @Override
    public int getFastMatchToken() {
        return 14;
    }
}

