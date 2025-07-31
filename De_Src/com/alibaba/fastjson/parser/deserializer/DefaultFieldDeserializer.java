/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.ParseContext;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.parser.deserializer.ContextObjectDeserializer;
import com.alibaba.fastjson.parser.deserializer.FieldDeserializer;
import com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer;
import com.alibaba.fastjson.parser.deserializer.JavaObjectDeserializer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.util.FieldInfo;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.zip.GZIPInputStream;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class DefaultFieldDeserializer
extends FieldDeserializer {
    protected ObjectDeserializer fieldValueDeserilizer;
    protected boolean customDeserilizer = false;

    public DefaultFieldDeserializer(ParserConfig config, Class<?> clazz, FieldInfo fieldInfo) {
        super(clazz, fieldInfo);
        JSONField annotation = fieldInfo.getAnnotation();
        if (annotation != null) {
            Class<?> deserializeUsing = annotation.deserializeUsing();
            this.customDeserilizer = deserializeUsing != null && deserializeUsing != Void.class;
        }
    }

    public ObjectDeserializer getFieldValueDeserilizer(ParserConfig config) {
        if (this.fieldValueDeserilizer == null) {
            JSONField annotation = this.fieldInfo.getAnnotation();
            if (annotation != null && annotation.deserializeUsing() != Void.class) {
                Class<?> deserializeUsing = annotation.deserializeUsing();
                try {
                    this.fieldValueDeserilizer = (ObjectDeserializer)deserializeUsing.newInstance();
                } catch (Exception ex) {
                    throw new JSONException("create deserializeUsing ObjectDeserializer error", ex);
                }
            } else {
                this.fieldValueDeserilizer = config.getDeserializer(this.fieldInfo.fieldClass, this.fieldInfo.fieldType);
            }
        }
        return this.fieldValueDeserilizer;
    }

    @Override
    public void parseField(DefaultJSONParser parser, Object object, Type objectType, Map<String, Object> fieldValues) {
        Object value;
        if (this.fieldValueDeserilizer == null) {
            this.getFieldValueDeserilizer(parser.getConfig());
        }
        ObjectDeserializer fieldValueDeserilizer = this.fieldValueDeserilizer;
        Type fieldType = this.fieldInfo.fieldType;
        if (objectType instanceof ParameterizedType) {
            ParseContext objContext = parser.getContext();
            if (objContext != null) {
                objContext.type = objectType;
            }
            if (fieldType != objectType) {
                fieldType = FieldInfo.getFieldType(this.clazz, objectType, fieldType);
                if (fieldValueDeserilizer instanceof JavaObjectDeserializer) {
                    fieldValueDeserilizer = parser.getConfig().getDeserializer(fieldType);
                }
            }
        }
        if (fieldValueDeserilizer instanceof JavaBeanDeserializer && this.fieldInfo.parserFeatures != 0) {
            JavaBeanDeserializer javaBeanDeser = (JavaBeanDeserializer)fieldValueDeserilizer;
            value = javaBeanDeser.deserialze(parser, fieldType, this.fieldInfo.name, this.fieldInfo.parserFeatures);
        } else {
            value = (this.fieldInfo.format != null || this.fieldInfo.parserFeatures != 0) && fieldValueDeserilizer instanceof ContextObjectDeserializer ? ((ContextObjectDeserializer)fieldValueDeserilizer).deserialze(parser, fieldType, this.fieldInfo.name, this.fieldInfo.format, this.fieldInfo.parserFeatures) : fieldValueDeserilizer.deserialze(parser, fieldType, this.fieldInfo.name);
        }
        if (value instanceof byte[] && ("gzip".equals(this.fieldInfo.format) || "gzip,base64".equals(this.fieldInfo.format))) {
            byte[] bytes = (byte[])value;
            GZIPInputStream gzipIn = null;
            try {
                byte[] buf;
                int len;
                gzipIn = new GZIPInputStream(new ByteArrayInputStream(bytes));
                ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
                while ((len = gzipIn.read(buf = new byte[1024])) != -1) {
                    if (len <= 0) continue;
                    byteOut.write(buf, 0, len);
                }
                value = byteOut.toByteArray();
            } catch (IOException ex) {
                throw new JSONException("unzip bytes error.", ex);
            }
        }
        if (parser.getResolveStatus() == 1) {
            DefaultJSONParser.ResolveTask task = parser.getLastResolveTask();
            task.fieldDeserializer = this;
            task.ownerContext = parser.getContext();
            parser.setResolveStatus(0);
        } else if (object == null) {
            fieldValues.put(this.fieldInfo.name, value);
        } else {
            this.setValue(object, value);
        }
    }

    @Override
    public int getFastMatchToken() {
        if (this.fieldValueDeserilizer != null) {
            return this.fieldValueDeserilizer.getFastMatchToken();
        }
        return 2;
    }

    public void parseFieldUnwrapped(DefaultJSONParser parser, Object object, Type objectType, Map<String, Object> fieldValues) {
        throw new JSONException("TODO");
    }
}

