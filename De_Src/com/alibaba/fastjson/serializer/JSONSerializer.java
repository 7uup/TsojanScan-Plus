/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerialContext;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializeFilterable;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.StringCodec;
import com.alibaba.fastjson.util.IOUtils;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.TimeZone;
import java.util.zip.GZIPOutputStream;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class JSONSerializer
extends SerializeFilterable {
    protected final SerializeConfig config;
    public final SerializeWriter out;
    private int indentCount = 0;
    private String indent = "\t";
    private String dateFormatPattern;
    private DateFormat dateFormat;
    private String fastJsonConfigDateFormatPattern;
    protected IdentityHashMap<Object, SerialContext> references = null;
    protected SerialContext context;
    protected TimeZone timeZone = JSON.defaultTimeZone;
    protected Locale locale = JSON.defaultLocale;

    public JSONSerializer() {
        this(new SerializeWriter(), SerializeConfig.getGlobalInstance());
    }

    public JSONSerializer(SerializeWriter out) {
        this(out, SerializeConfig.getGlobalInstance());
    }

    public JSONSerializer(SerializeConfig config) {
        this(new SerializeWriter(), config);
    }

    public JSONSerializer(SerializeWriter out, SerializeConfig config) {
        this.out = out;
        this.config = config;
    }

    public String getDateFormatPattern() {
        if (this.dateFormat instanceof SimpleDateFormat) {
            return ((SimpleDateFormat)this.dateFormat).toPattern();
        }
        return this.dateFormatPattern;
    }

    public DateFormat getDateFormat() {
        if (this.dateFormat == null && this.dateFormatPattern != null) {
            this.dateFormat = this.generateDateFormat(this.dateFormatPattern);
        }
        return this.dateFormat;
    }

    private DateFormat generateDateFormat(String dateFormatPattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatPattern, this.locale);
        dateFormat.setTimeZone(this.timeZone);
        return dateFormat;
    }

    public void setDateFormat(DateFormat dateFormat) {
        this.dateFormat = dateFormat;
        if (this.dateFormatPattern != null) {
            this.dateFormatPattern = null;
        }
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormatPattern = dateFormat;
        if (this.dateFormat != null) {
            this.dateFormat = null;
        }
    }

    public void setFastJsonConfigDateFormatPattern(String dateFormatPattern) {
        this.fastJsonConfigDateFormatPattern = dateFormatPattern;
    }

    public String getFastJsonConfigDateFormatPattern() {
        return this.fastJsonConfigDateFormatPattern;
    }

    public SerialContext getContext() {
        return this.context;
    }

    public void setContext(SerialContext context) {
        this.context = context;
    }

    public void setContext(SerialContext parent, Object object, Object fieldName, int features) {
        this.setContext(parent, object, fieldName, features, 0);
    }

    public void setContext(SerialContext parent, Object object, Object fieldName, int features, int fieldFeatures) {
        if (this.out.disableCircularReferenceDetect) {
            return;
        }
        this.context = new SerialContext(parent, object, fieldName, features, fieldFeatures);
        if (this.references == null) {
            this.references = new IdentityHashMap();
        }
        this.references.put(object, this.context);
    }

    public void setContext(Object object, Object fieldName) {
        this.setContext(this.context, object, fieldName, 0);
    }

    public void popContext() {
        if (this.context != null) {
            this.context = this.context.parent;
        }
    }

    public final boolean isWriteClassName(Type fieldType, Object obj) {
        return this.out.isEnabled(SerializerFeature.WriteClassName) && (fieldType != null || !this.out.isEnabled(SerializerFeature.NotWriteRootClassName) || this.context != null && this.context.parent != null);
    }

    public boolean containsReference(Object value) {
        if (this.references == null) {
            return false;
        }
        SerialContext refContext = this.references.get(value);
        if (refContext == null) {
            return false;
        }
        if (value == Collections.emptyMap()) {
            return false;
        }
        Object fieldName = refContext.fieldName;
        return fieldName == null || fieldName instanceof Integer || fieldName instanceof String;
    }

    public void writeReference(Object object) {
        SerialContext context = this.context;
        Object current = context.object;
        if (object == current) {
            this.out.write("{\"$ref\":\"@\"}");
            return;
        }
        SerialContext parentContext = context.parent;
        if (parentContext != null && object == parentContext.object) {
            this.out.write("{\"$ref\":\"..\"}");
            return;
        }
        SerialContext rootContext = context;
        while (rootContext.parent != null) {
            rootContext = rootContext.parent;
        }
        if (object == rootContext.object) {
            this.out.write("{\"$ref\":\"$\"}");
        } else {
            this.out.write("{\"$ref\":\"");
            String path = this.references.get(object).toString();
            this.out.write(path);
            this.out.write("\"}");
        }
    }

    public boolean checkValue(SerializeFilterable filterable) {
        return this.valueFilters != null && this.valueFilters.size() > 0 || this.contextValueFilters != null && this.contextValueFilters.size() > 0 || filterable.valueFilters != null && filterable.valueFilters.size() > 0 || filterable.contextValueFilters != null && filterable.contextValueFilters.size() > 0 || this.out.writeNonStringValueAsString;
    }

    public boolean hasNameFilters(SerializeFilterable filterable) {
        return this.nameFilters != null && this.nameFilters.size() > 0 || filterable.nameFilters != null && filterable.nameFilters.size() > 0;
    }

    public boolean hasPropertyFilters(SerializeFilterable filterable) {
        return this.propertyFilters != null && this.propertyFilters.size() > 0 || filterable.propertyFilters != null && filterable.propertyFilters.size() > 0;
    }

    public int getIndentCount() {
        return this.indentCount;
    }

    public void incrementIndent() {
        ++this.indentCount;
    }

    public void decrementIdent() {
        --this.indentCount;
    }

    public void println() {
        this.out.write(10);
        for (int i = 0; i < this.indentCount; ++i) {
            this.out.write(this.indent);
        }
    }

    public SerializeWriter getWriter() {
        return this.out;
    }

    public String toString() {
        return this.out.toString();
    }

    public void config(SerializerFeature feature, boolean state) {
        this.out.config(feature, state);
    }

    public boolean isEnabled(SerializerFeature feature) {
        return this.out.isEnabled(feature);
    }

    public void writeNull() {
        this.out.writeNull();
    }

    public SerializeConfig getMapping() {
        return this.config;
    }

    public static void write(Writer out, Object object) {
        SerializeWriter writer = new SerializeWriter();
        try {
            JSONSerializer serializer = new JSONSerializer(writer);
            serializer.write(object);
            writer.writeTo(out);
        } catch (IOException ex) {
            throw new JSONException(ex.getMessage(), ex);
        } finally {
            writer.close();
        }
    }

    public static void write(SerializeWriter out, Object object) {
        JSONSerializer serializer = new JSONSerializer(out);
        serializer.write(object);
    }

    public final void write(Object object) {
        if (object == null) {
            this.out.writeNull();
            return;
        }
        Class<?> clazz = object.getClass();
        ObjectSerializer writer = this.getObjectWriter(clazz);
        try {
            writer.write(this, object, null, null, 0);
        } catch (IOException e) {
            throw new JSONException(e.getMessage(), e);
        }
    }

    public final void writeAs(Object object, Class type) {
        if (object == null) {
            this.out.writeNull();
            return;
        }
        ObjectSerializer writer = this.getObjectWriter(type);
        try {
            writer.write(this, object, null, null, 0);
        } catch (IOException e) {
            throw new JSONException(e.getMessage(), e);
        }
    }

    public final void writeWithFieldName(Object object, Object fieldName) {
        this.writeWithFieldName(object, fieldName, null, 0);
    }

    protected final void writeKeyValue(char seperator, String key, Object value) {
        if (seperator != '\u0000') {
            this.out.write(seperator);
        }
        this.out.writeFieldName(key);
        this.write(value);
    }

    public final void writeWithFieldName(Object object, Object fieldName, Type fieldType, int fieldFeatures) {
        try {
            if (object == null) {
                this.out.writeNull();
                return;
            }
            Class<?> clazz = object.getClass();
            ObjectSerializer writer = this.getObjectWriter(clazz);
            writer.write(this, object, fieldName, fieldType, fieldFeatures);
        } catch (IOException e) {
            throw new JSONException(e.getMessage(), e);
        }
    }

    public final void writeWithFormat(Object object, String format) {
        block17: {
            block19: {
                byte[] bytes;
                block18: {
                    if (object instanceof Date) {
                        if ("unixtime".equals(format)) {
                            long seconds = ((Date)object).getTime() / 1000L;
                            this.out.writeInt((int)seconds);
                            return;
                        }
                        if ("millis".equals(format)) {
                            this.out.writeLong(((Date)object).getTime());
                            return;
                        }
                        DateFormat dateFormat = this.getDateFormat();
                        if (dateFormat == null) {
                            if (format != null) {
                                try {
                                    dateFormat = this.generateDateFormat(format);
                                } catch (IllegalArgumentException e) {
                                    String format2 = format.replaceAll("T", "'T'");
                                    dateFormat = this.generateDateFormat(format2);
                                }
                            } else {
                                dateFormat = this.fastJsonConfigDateFormatPattern != null ? this.generateDateFormat(this.fastJsonConfigDateFormatPattern) : this.generateDateFormat(JSON.DEFFAULT_DATE_FORMAT);
                            }
                        }
                        String text = dateFormat.format((Date)object);
                        this.out.writeString(text);
                        return;
                    }
                    if (!(object instanceof byte[])) break block17;
                    bytes = (byte[])object;
                    if (!"gzip".equals(format) && !"gzip,base64".equals(format)) break block18;
                    GZIPOutputStream gzipOut = null;
                    try {
                        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
                        gzipOut = bytes.length < 512 ? new GZIPOutputStream((OutputStream)byteOut, bytes.length) : new GZIPOutputStream(byteOut);
                        gzipOut.write(bytes);
                        gzipOut.finish();
                        this.out.writeByteArray(byteOut.toByteArray());
                    } catch (IOException ex) {
                        try {
                            throw new JSONException("write gzipBytes error", ex);
                        } catch (Throwable throwable) {
                            IOUtils.close(gzipOut);
                            throw throwable;
                        }
                    }
                    IOUtils.close(gzipOut);
                    break block19;
                }
                if ("hex".equals(format)) {
                    this.out.writeHex(bytes);
                } else {
                    this.out.writeByteArray(bytes);
                }
            }
            return;
        }
        if (object instanceof Collection) {
            Collection collection = (Collection)object;
            Iterator iterator2 = collection.iterator();
            this.out.write(91);
            for (int i = 0; i < collection.size(); ++i) {
                Object item = iterator2.next();
                if (i != 0) {
                    this.out.write(44);
                }
                this.writeWithFormat(item, format);
            }
            this.out.write(93);
            return;
        }
        this.write(object);
    }

    public final void write(String text) {
        StringCodec.instance.write(this, text);
    }

    public ObjectSerializer getObjectWriter(Class<?> clazz) {
        return this.config.getObjectWriter(clazz);
    }

    public void close() {
        this.out.close();
    }
}

