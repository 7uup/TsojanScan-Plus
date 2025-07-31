/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import java.io.IOException;
import java.lang.reflect.Type;

public class PrimitiveArraySerializer
implements ObjectSerializer {
    public static PrimitiveArraySerializer instance = new PrimitiveArraySerializer();

    public final void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
        SerializeWriter out = serializer.out;
        if (object == null) {
            out.writeNull(SerializerFeature.WriteNullListAsEmpty);
            return;
        }
        if (object instanceof int[]) {
            int[] array = (int[])object;
            out.write(91);
            for (int i = 0; i < array.length; ++i) {
                if (i != 0) {
                    out.write(44);
                }
                out.writeInt(array[i]);
            }
            out.write(93);
            return;
        }
        if (object instanceof short[]) {
            short[] array = (short[])object;
            out.write(91);
            for (int i = 0; i < array.length; ++i) {
                if (i != 0) {
                    out.write(44);
                }
                out.writeInt(array[i]);
            }
            out.write(93);
            return;
        }
        if (object instanceof long[]) {
            long[] array = (long[])object;
            out.write(91);
            for (int i = 0; i < array.length; ++i) {
                if (i != 0) {
                    out.write(44);
                }
                out.writeLong(array[i]);
            }
            out.write(93);
            return;
        }
        if (object instanceof boolean[]) {
            boolean[] array = (boolean[])object;
            out.write(91);
            for (int i = 0; i < array.length; ++i) {
                if (i != 0) {
                    out.write(44);
                }
                out.write(array[i]);
            }
            out.write(93);
            return;
        }
        if (object instanceof float[]) {
            float[] array = (float[])object;
            out.write(91);
            for (int i = 0; i < array.length; ++i) {
                float item;
                if (i != 0) {
                    out.write(44);
                }
                if (Float.isNaN(item = array[i])) {
                    out.writeNull();
                    continue;
                }
                out.append(Float.toString(item));
            }
            out.write(93);
            return;
        }
        if (object instanceof double[]) {
            double[] array = (double[])object;
            out.write(91);
            for (int i = 0; i < array.length; ++i) {
                double item;
                if (i != 0) {
                    out.write(44);
                }
                if (Double.isNaN(item = array[i])) {
                    out.writeNull();
                    continue;
                }
                out.append(Double.toString(item));
            }
            out.write(93);
            return;
        }
        if (object instanceof byte[]) {
            byte[] array = (byte[])object;
            out.writeByteArray(array);
            return;
        }
        char[] chars = (char[])object;
        out.writeString(chars);
    }
}

