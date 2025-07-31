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
import java.text.DecimalFormat;

public class DoubleSerializer
implements ObjectSerializer {
    public static final DoubleSerializer instance = new DoubleSerializer();
    private DecimalFormat decimalFormat = null;

    public DoubleSerializer() {
    }

    public DoubleSerializer(DecimalFormat decimalFormat) {
        this.decimalFormat = decimalFormat;
    }

    public DoubleSerializer(String decimalFormat) {
        this(new DecimalFormat(decimalFormat));
    }

    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
        SerializeWriter out = serializer.out;
        if (object == null) {
            out.writeNull(SerializerFeature.WriteNullNumberAsZero);
            return;
        }
        double doubleValue = (Double)object;
        if (Double.isNaN(doubleValue) || Double.isInfinite(doubleValue)) {
            out.writeNull();
        } else if (this.decimalFormat == null) {
            out.writeDouble(doubleValue, true);
        } else {
            String doubleText = this.decimalFormat.format(doubleValue);
            out.write(doubleText);
        }
    }
}

