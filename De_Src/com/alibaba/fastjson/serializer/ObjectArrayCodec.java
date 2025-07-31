/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerialContext;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.util.TypeUtils;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class ObjectArrayCodec
implements ObjectSerializer,
ObjectDeserializer {
    public static final ObjectArrayCodec instance = new ObjectArrayCodec();

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public final void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
        SerializeWriter out = serializer.out;
        Object[] array = (Object[])object;
        if (object == null) {
            out.writeNull(SerializerFeature.WriteNullListAsEmpty);
            return;
        }
        int size = array.length;
        int end = size - 1;
        if (end == -1) {
            out.append("[]");
            return;
        }
        SerialContext context = serializer.context;
        serializer.setContext(context, object, fieldName, 0);
        try {
            Class<?> preClazz = null;
            ObjectSerializer preWriter = null;
            out.append('[');
            if (out.isEnabled(SerializerFeature.PrettyFormat)) {
                serializer.incrementIndent();
                serializer.println();
                for (int i = 0; i < size; ++i) {
                    if (i != 0) {
                        out.write(44);
                        serializer.println();
                    }
                    serializer.writeWithFieldName(array[i], i);
                }
                serializer.decrementIdent();
                serializer.println();
                out.write(93);
                return;
            }
            for (int i = 0; i < end; ++i) {
                Object item = array[i];
                if (item == null) {
                    out.append("null,");
                    continue;
                }
                if (serializer.containsReference(item)) {
                    serializer.writeReference(item);
                } else {
                    Class<?> clazz = item.getClass();
                    if (clazz == preClazz) {
                        preWriter.write(serializer, item, i, null, 0);
                    } else {
                        preClazz = clazz;
                        preWriter = serializer.getObjectWriter(clazz);
                        preWriter.write(serializer, item, i, null, 0);
                    }
                }
                out.append(',');
            }
            Object item = array[end];
            if (item == null) {
                out.append("null]");
            } else {
                if (serializer.containsReference(item)) {
                    serializer.writeReference(item);
                } else {
                    serializer.writeWithFieldName(item, end);
                }
                out.append(']');
            }
        } finally {
            serializer.context = context;
        }
    }

    @Override
    public <T> T deserialze(DefaultJSONParser parser, Type type, Object fieldName) {
        Class<Object> componentClass;
        Class<?> componentType;
        Type clazz;
        JSONLexer lexer = parser.lexer;
        int token = lexer.token();
        if (token == 8) {
            lexer.nextToken(16);
            return null;
        }
        if (token == 4 || token == 26) {
            byte[] bytes = lexer.bytesValue();
            lexer.nextToken(16);
            if (bytes.length == 0 && type != byte[].class) {
                return null;
            }
            return (T)bytes;
        }
        if (type instanceof GenericArrayType) {
            clazz = (GenericArrayType)type;
            componentType = clazz.getGenericComponentType();
            if (componentType instanceof TypeVariable) {
                TypeVariable typeVar = (TypeVariable)((Object)componentType);
                Type objType = parser.getContext().type;
                if (objType instanceof ParameterizedType) {
                    ParameterizedType objParamType = (ParameterizedType)objType;
                    Type objRawType = objParamType.getRawType();
                    Type actualType = null;
                    if (objRawType instanceof Class) {
                        TypeVariable<Class<T>>[] objTypeParams = ((Class)objRawType).getTypeParameters();
                        for (int i = 0; i < objTypeParams.length; ++i) {
                            if (!objTypeParams[i].getName().equals(typeVar.getName())) continue;
                            actualType = objParamType.getActualTypeArguments()[i];
                        }
                    }
                    componentClass = actualType instanceof Class ? (Class<Object>)actualType : Object.class;
                } else {
                    componentClass = TypeUtils.getClass(typeVar.getBounds()[0]);
                }
            } else {
                componentClass = TypeUtils.getClass(componentType);
            }
        } else {
            clazz = (Class)type;
            componentClass = ((Class)clazz).getComponentType();
            componentType = componentClass;
        }
        JSONArray array = new JSONArray();
        parser.parseArray(componentType, array, fieldName);
        return this.toObjectArray(parser, componentClass, array);
    }

    private <T> T toObjectArray(DefaultJSONParser parser, Class<?> componentType, JSONArray array) {
        if (array == null) {
            return null;
        }
        int size = array.size();
        Object objArray = Array.newInstance(componentType, size);
        for (int i = 0; i < size; ++i) {
            Object element;
            Object value = array.get(i);
            if (value == array) {
                Array.set(objArray, i, objArray);
                continue;
            }
            if (componentType.isArray()) {
                element = componentType.isInstance(value) ? value : this.toObjectArray(parser, componentType, (JSONArray)value);
                Array.set(objArray, i, element);
                continue;
            }
            element = null;
            if (value instanceof JSONArray) {
                boolean contains = false;
                JSONArray valueArray = (JSONArray)value;
                int valueArraySize = valueArray.size();
                for (int y = 0; y < valueArraySize; ++y) {
                    Object valueItem = valueArray.get(y);
                    if (valueItem != array) continue;
                    valueArray.set(i, objArray);
                    contains = true;
                }
                if (contains) {
                    element = valueArray.toArray();
                }
            }
            if (element == null) {
                element = TypeUtils.cast(value, componentType, parser.getConfig());
            }
            Array.set(objArray, i, element);
        }
        array.setRelatedArray(objArray);
        array.setComponentType(componentType);
        return (T)objArray;
    }

    @Override
    public int getFastMatchToken() {
        return 14;
    }
}

