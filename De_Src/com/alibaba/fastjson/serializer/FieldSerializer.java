/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.serializer.BeanContext;
import com.alibaba.fastjson.serializer.ContextObjectSerializer;
import com.alibaba.fastjson.serializer.DoubleSerializer;
import com.alibaba.fastjson.serializer.FloatCodec;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.JavaBeanSerializer;
import com.alibaba.fastjson.serializer.MapSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.util.FieldInfo;
import com.alibaba.fastjson.util.TypeUtils;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Collection;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class FieldSerializer
implements Comparable<FieldSerializer> {
    public final FieldInfo fieldInfo;
    protected final boolean writeNull;
    protected int features;
    private final String double_quoted_fieldPrefix;
    private String single_quoted_fieldPrefix;
    private String un_quoted_fieldPrefix;
    protected BeanContext fieldContext;
    private String format;
    protected boolean writeEnumUsingToString = false;
    protected boolean writeEnumUsingName = false;
    protected boolean disableCircularReferenceDetect = false;
    protected boolean serializeUsing = false;
    protected boolean persistenceXToMany = false;
    protected boolean browserCompatible;
    private RuntimeSerializerInfo runtimeInfo;

    public FieldSerializer(Class<?> beanType, FieldInfo fieldInfo) {
        JSONType jsonType;
        this.fieldInfo = fieldInfo;
        this.fieldContext = new BeanContext(beanType, fieldInfo);
        if (beanType != null && (jsonType = TypeUtils.getAnnotation(beanType, JSONType.class)) != null) {
            for (SerializerFeature feature : jsonType.serialzeFeatures()) {
                if (feature == SerializerFeature.WriteEnumUsingToString) {
                    this.writeEnumUsingToString = true;
                    continue;
                }
                if (feature == SerializerFeature.WriteEnumUsingName) {
                    this.writeEnumUsingName = true;
                    continue;
                }
                if (feature == SerializerFeature.DisableCircularReferenceDetect) {
                    this.disableCircularReferenceDetect = true;
                    continue;
                }
                if (feature == SerializerFeature.BrowserCompatible) {
                    this.features |= SerializerFeature.BrowserCompatible.mask;
                    this.browserCompatible = true;
                    continue;
                }
                if (feature != SerializerFeature.WriteMapNullValue) continue;
                this.features |= SerializerFeature.WriteMapNullValue.mask;
            }
        }
        fieldInfo.setAccessible();
        this.double_quoted_fieldPrefix = '\"' + fieldInfo.name + "\":";
        boolean writeNull = false;
        JSONField annotation = fieldInfo.getAnnotation();
        if (annotation != null) {
            for (SerializerFeature feature : annotation.serialzeFeatures()) {
                if ((feature.getMask() & SerializerFeature.WRITE_MAP_NULL_FEATURES) == 0) continue;
                writeNull = true;
                break;
            }
            this.format = annotation.format();
            if (this.format.trim().length() == 0) {
                this.format = null;
            }
            for (SerializerFeature feature : annotation.serialzeFeatures()) {
                if (feature == SerializerFeature.WriteEnumUsingToString) {
                    this.writeEnumUsingToString = true;
                    continue;
                }
                if (feature == SerializerFeature.WriteEnumUsingName) {
                    this.writeEnumUsingName = true;
                    continue;
                }
                if (feature == SerializerFeature.DisableCircularReferenceDetect) {
                    this.disableCircularReferenceDetect = true;
                    continue;
                }
                if (feature != SerializerFeature.BrowserCompatible) continue;
                this.browserCompatible = true;
            }
            this.features |= SerializerFeature.of(annotation.serialzeFeatures());
        }
        this.writeNull = writeNull;
        this.persistenceXToMany = TypeUtils.isAnnotationPresentOneToMany(fieldInfo.method) || TypeUtils.isAnnotationPresentManyToMany(fieldInfo.method);
    }

    public void writePrefix(JSONSerializer serializer) throws IOException {
        SerializeWriter out = serializer.out;
        if (out.quoteFieldNames) {
            boolean useSingleQuotes = SerializerFeature.isEnabled(out.features, this.fieldInfo.serialzeFeatures, SerializerFeature.UseSingleQuotes);
            if (useSingleQuotes) {
                if (this.single_quoted_fieldPrefix == null) {
                    this.single_quoted_fieldPrefix = '\'' + this.fieldInfo.name + "':";
                }
                out.write(this.single_quoted_fieldPrefix);
            } else {
                out.write(this.double_quoted_fieldPrefix);
            }
        } else {
            if (this.un_quoted_fieldPrefix == null) {
                this.un_quoted_fieldPrefix = this.fieldInfo.name + ":";
            }
            out.write(this.un_quoted_fieldPrefix);
        }
    }

    public Object getPropertyValueDirect(Object object) throws InvocationTargetException, IllegalAccessException {
        Object fieldValue = this.fieldInfo.get(object);
        if (this.persistenceXToMany && !TypeUtils.isHibernateInitialized(fieldValue)) {
            return null;
        }
        return fieldValue;
    }

    public Object getPropertyValue(Object object) throws InvocationTargetException, IllegalAccessException {
        Object propertyValue = this.fieldInfo.get(object);
        if (this.format != null && propertyValue != null && (this.fieldInfo.fieldClass == java.util.Date.class || this.fieldInfo.fieldClass == Date.class)) {
            SimpleDateFormat dateFormat = new SimpleDateFormat(this.format, JSON.defaultLocale);
            dateFormat.setTimeZone(JSON.defaultTimeZone);
            return dateFormat.format(propertyValue);
        }
        return propertyValue;
    }

    @Override
    public int compareTo(FieldSerializer o) {
        return this.fieldInfo.compareTo(o.fieldInfo);
    }

    public void writeValue(JSONSerializer serializer, Object propertyValue) throws Exception {
        long value;
        Class<?> valueClass;
        if (this.runtimeInfo == null) {
            Class<Object> runtimeFieldClass;
            if (propertyValue == null) {
                runtimeFieldClass = this.fieldInfo.fieldClass;
                if (runtimeFieldClass == Byte.TYPE) {
                    runtimeFieldClass = Byte.class;
                } else if (runtimeFieldClass == Short.TYPE) {
                    runtimeFieldClass = Short.class;
                } else if (runtimeFieldClass == Integer.TYPE) {
                    runtimeFieldClass = Integer.class;
                } else if (runtimeFieldClass == Long.TYPE) {
                    runtimeFieldClass = Long.class;
                } else if (runtimeFieldClass == Float.TYPE) {
                    runtimeFieldClass = Float.class;
                } else if (runtimeFieldClass == Double.TYPE) {
                    runtimeFieldClass = Double.class;
                } else if (runtimeFieldClass == Boolean.TYPE) {
                    runtimeFieldClass = Boolean.class;
                }
            } else {
                runtimeFieldClass = propertyValue.getClass();
            }
            ObjectSerializer fieldSerializer = null;
            JSONField fieldAnnotation = this.fieldInfo.getAnnotation();
            if (fieldAnnotation != null && fieldAnnotation.serializeUsing() != Void.class) {
                fieldSerializer = (ObjectSerializer)fieldAnnotation.serializeUsing().newInstance();
                this.serializeUsing = true;
            } else {
                if (this.format != null) {
                    if (runtimeFieldClass == Double.TYPE || runtimeFieldClass == Double.class) {
                        fieldSerializer = new DoubleSerializer(this.format);
                    } else if (runtimeFieldClass == Float.TYPE || runtimeFieldClass == Float.class) {
                        fieldSerializer = new FloatCodec(this.format);
                    }
                }
                if (fieldSerializer == null) {
                    fieldSerializer = serializer.getObjectWriter(runtimeFieldClass);
                }
            }
            this.runtimeInfo = new RuntimeSerializerInfo(fieldSerializer, runtimeFieldClass);
        }
        RuntimeSerializerInfo runtimeInfo = this.runtimeInfo;
        int fieldFeatures = (this.disableCircularReferenceDetect ? this.fieldInfo.serialzeFeatures | SerializerFeature.DisableCircularReferenceDetect.mask : this.fieldInfo.serialzeFeatures) | this.features;
        if (propertyValue == null) {
            SerializeWriter out = serializer.out;
            if (this.fieldInfo.fieldClass == Object.class && out.isEnabled(SerializerFeature.WRITE_MAP_NULL_FEATURES)) {
                out.writeNull();
                return;
            }
            Class<?> runtimeFieldClass = runtimeInfo.runtimeFieldClass;
            if (Number.class.isAssignableFrom(runtimeFieldClass)) {
                out.writeNull(this.features, SerializerFeature.WriteNullNumberAsZero.mask);
                return;
            }
            if (String.class == runtimeFieldClass) {
                out.writeNull(this.features, SerializerFeature.WriteNullStringAsEmpty.mask);
                return;
            }
            if (Boolean.class == runtimeFieldClass) {
                out.writeNull(this.features, SerializerFeature.WriteNullBooleanAsFalse.mask);
                return;
            }
            if (Collection.class.isAssignableFrom(runtimeFieldClass) || runtimeFieldClass.isArray()) {
                out.writeNull(this.features, SerializerFeature.WriteNullListAsEmpty.mask);
                return;
            }
            ObjectSerializer fieldSerializer = runtimeInfo.fieldSerializer;
            if (out.isEnabled(SerializerFeature.WRITE_MAP_NULL_FEATURES) && fieldSerializer instanceof JavaBeanSerializer) {
                out.writeNull();
                return;
            }
            fieldSerializer.write(serializer, null, this.fieldInfo.name, this.fieldInfo.fieldType, fieldFeatures);
            return;
        }
        if (this.fieldInfo.isEnum) {
            if (this.writeEnumUsingName) {
                serializer.out.writeString(((Enum)propertyValue).name());
                return;
            }
            if (this.writeEnumUsingToString) {
                serializer.out.writeString(((Enum)propertyValue).toString());
                return;
            }
        }
        ObjectSerializer valueSerializer = (valueClass = propertyValue.getClass()) == runtimeInfo.runtimeFieldClass || this.serializeUsing ? runtimeInfo.fieldSerializer : serializer.getObjectWriter(valueClass);
        if (this.format != null && !(valueSerializer instanceof DoubleSerializer) && !(valueSerializer instanceof FloatCodec)) {
            if (valueSerializer instanceof ContextObjectSerializer) {
                ((ContextObjectSerializer)valueSerializer).write(serializer, propertyValue, this.fieldContext);
            } else {
                serializer.writeWithFormat(propertyValue, this.format);
            }
            return;
        }
        if (this.fieldInfo.unwrapped) {
            if (valueSerializer instanceof JavaBeanSerializer) {
                JavaBeanSerializer javaBeanSerializer = (JavaBeanSerializer)valueSerializer;
                javaBeanSerializer.write(serializer, propertyValue, this.fieldInfo.name, this.fieldInfo.fieldType, fieldFeatures, true);
                return;
            }
            if (valueSerializer instanceof MapSerializer) {
                MapSerializer mapSerializer = (MapSerializer)valueSerializer;
                mapSerializer.write(serializer, propertyValue, this.fieldInfo.name, this.fieldInfo.fieldType, fieldFeatures, true);
                return;
            }
        }
        if ((this.features & SerializerFeature.WriteClassName.mask) != 0 && valueClass != this.fieldInfo.fieldClass && valueSerializer instanceof JavaBeanSerializer) {
            ((JavaBeanSerializer)valueSerializer).write(serializer, propertyValue, this.fieldInfo.name, this.fieldInfo.fieldType, fieldFeatures, false);
            return;
        }
        if (!(!this.browserCompatible || this.fieldInfo.fieldClass != Long.TYPE && this.fieldInfo.fieldClass != Long.class || (value = ((Long)propertyValue).longValue()) <= 0x1FFFFFFFFFFFFFL && value >= -9007199254740991L)) {
            serializer.getWriter().writeString(Long.toString(value));
            return;
        }
        valueSerializer.write(serializer, propertyValue, this.fieldInfo.name, this.fieldInfo.fieldType, fieldFeatures);
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    static class RuntimeSerializerInfo {
        final ObjectSerializer fieldSerializer;
        final Class<?> runtimeFieldClass;

        public RuntimeSerializerInfo(ObjectSerializer fieldSerializer, Class<?> runtimeFieldClass) {
            this.fieldSerializer = fieldSerializer;
            this.runtimeFieldClass = runtimeFieldClass;
        }
    }
}

