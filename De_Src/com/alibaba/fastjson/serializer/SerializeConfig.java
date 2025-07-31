/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONAware;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONStreamAware;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.parser.deserializer.Jdk8DateCodec;
import com.alibaba.fastjson.parser.deserializer.OptionalCodec;
import com.alibaba.fastjson.serializer.ASMSerializerFactory;
import com.alibaba.fastjson.serializer.AdderSerializer;
import com.alibaba.fastjson.serializer.AnnotationSerializer;
import com.alibaba.fastjson.serializer.AppendableSerializer;
import com.alibaba.fastjson.serializer.ArraySerializer;
import com.alibaba.fastjson.serializer.AtomicCodec;
import com.alibaba.fastjson.serializer.AutowiredObjectSerializer;
import com.alibaba.fastjson.serializer.AwtCodec;
import com.alibaba.fastjson.serializer.BigDecimalCodec;
import com.alibaba.fastjson.serializer.BigIntegerCodec;
import com.alibaba.fastjson.serializer.BooleanCodec;
import com.alibaba.fastjson.serializer.ByteBufferCodec;
import com.alibaba.fastjson.serializer.CalendarCodec;
import com.alibaba.fastjson.serializer.CharacterCodec;
import com.alibaba.fastjson.serializer.ClobSerializer;
import com.alibaba.fastjson.serializer.CollectionCodec;
import com.alibaba.fastjson.serializer.DateCodec;
import com.alibaba.fastjson.serializer.DoubleSerializer;
import com.alibaba.fastjson.serializer.EnumSerializer;
import com.alibaba.fastjson.serializer.EnumerationSerializer;
import com.alibaba.fastjson.serializer.FieldSerializer;
import com.alibaba.fastjson.serializer.FloatCodec;
import com.alibaba.fastjson.serializer.GuavaCodec;
import com.alibaba.fastjson.serializer.IntegerCodec;
import com.alibaba.fastjson.serializer.JSONAwareSerializer;
import com.alibaba.fastjson.serializer.JSONObjectCodec;
import com.alibaba.fastjson.serializer.JSONSerializable;
import com.alibaba.fastjson.serializer.JSONSerializableSerializer;
import com.alibaba.fastjson.serializer.JavaBeanSerializer;
import com.alibaba.fastjson.serializer.JodaCodec;
import com.alibaba.fastjson.serializer.ListSerializer;
import com.alibaba.fastjson.serializer.LongCodec;
import com.alibaba.fastjson.serializer.MapSerializer;
import com.alibaba.fastjson.serializer.MiscCodec;
import com.alibaba.fastjson.serializer.ObjectArrayCodec;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.PrimitiveArraySerializer;
import com.alibaba.fastjson.serializer.ReferenceCodec;
import com.alibaba.fastjson.serializer.SerializeBeanInfo;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SerializeFilterable;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.StringCodec;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.alibaba.fastjson.spi.Module;
import com.alibaba.fastjson.support.moneta.MonetaCodec;
import com.alibaba.fastjson.support.springfox.SwaggerJsonSerializer;
import com.alibaba.fastjson.util.ASMUtils;
import com.alibaba.fastjson.util.FieldInfo;
import com.alibaba.fastjson.util.IdentityHashMap;
import com.alibaba.fastjson.util.ServiceLoader;
import com.alibaba.fastjson.util.TypeUtils;
import java.io.File;
import java.io.Serializable;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Currency;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Pattern;
import javax.xml.datatype.XMLGregorianCalendar;
import org.w3c.dom.Node;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class SerializeConfig {
    public static final SerializeConfig globalInstance = new SerializeConfig();
    private static boolean awtError = false;
    private static boolean jdk8Error = false;
    private static boolean oracleJdbcError = false;
    private static boolean springfoxError = false;
    private static boolean guavaError = false;
    private static boolean jodaError = false;
    private boolean asm = !ASMUtils.IS_ANDROID;
    private ASMSerializerFactory asmFactory;
    protected String typeKey = JSON.DEFAULT_TYPE_KEY;
    public PropertyNamingStrategy propertyNamingStrategy;
    private final IdentityHashMap<Type, ObjectSerializer> serializers;
    private final IdentityHashMap<Type, IdentityHashMap<Type, ObjectSerializer>> mixInSerializers;
    private final boolean fieldBased;
    private long[] denyClasses = new long[]{4165360493669296979L, 4446674157046724083L};
    private List<Module> modules = new ArrayList<Module>();

    public String getTypeKey() {
        return this.typeKey;
    }

    public void setTypeKey(String typeKey) {
        this.typeKey = typeKey;
    }

    private final JavaBeanSerializer createASMSerializer(SerializeBeanInfo beanInfo) throws Exception {
        JavaBeanSerializer serializer = this.asmFactory.createJavaBeanSerializer(beanInfo);
        for (int i = 0; i < serializer.sortedGetters.length; ++i) {
            ObjectSerializer fieldSer;
            FieldSerializer fieldDeser = serializer.sortedGetters[i];
            Class<?> fieldClass = fieldDeser.fieldInfo.fieldClass;
            if (!fieldClass.isEnum() || (fieldSer = this.getObjectWriter(fieldClass)) instanceof EnumSerializer) continue;
            serializer.writeDirect = false;
        }
        return serializer;
    }

    public final ObjectSerializer createJavaBeanSerializer(Class<?> clazz) {
        String className = clazz.getName();
        long hashCode64 = TypeUtils.fnv1a_64(className);
        if (Arrays.binarySearch(this.denyClasses, hashCode64) >= 0) {
            throw new JSONException("not support class : " + className);
        }
        SerializeBeanInfo beanInfo = TypeUtils.buildBeanInfo(clazz, null, this.propertyNamingStrategy, this.fieldBased);
        if (beanInfo.fields.length == 0 && Iterable.class.isAssignableFrom(clazz)) {
            return MiscCodec.instance;
        }
        return this.createJavaBeanSerializer(beanInfo);
    }

    public ObjectSerializer createJavaBeanSerializer(SerializeBeanInfo beanInfo) {
        boolean asm;
        JSONType jsonType = beanInfo.jsonType;
        boolean bl = asm = this.asm && !this.fieldBased;
        if (jsonType != null) {
            Class<? extends SerializeFilter>[] classArray;
            Class<?> serializerClass = jsonType.serializer();
            if (serializerClass != Void.class) {
                try {
                    Object obj = serializerClass.newInstance();
                    if (obj instanceof ObjectSerializer) {
                        return (ObjectSerializer)obj;
                    }
                } catch (Throwable throwable) {
                    // empty catch block
                }
            }
            if (!jsonType.asm()) {
                asm = false;
            }
            if (asm) {
                for (SerializerFeature serializerFeature : jsonType.serialzeFeatures()) {
                    if (SerializerFeature.WriteNonStringValueAsString != serializerFeature && SerializerFeature.WriteEnumUsingToString != serializerFeature && SerializerFeature.NotWriteDefaultValue != serializerFeature && SerializerFeature.BrowserCompatible != serializerFeature) continue;
                    asm = false;
                    break;
                }
            }
            if (asm && (classArray = jsonType.serialzeFilters()).length != 0) {
                asm = false;
            }
        }
        Class<?> clazz = beanInfo.beanType;
        if (!Modifier.isPublic(beanInfo.beanType.getModifiers())) {
            return new JavaBeanSerializer(beanInfo);
        }
        if (asm && this.asmFactory.classLoader.isExternalClass(clazz) || clazz == Serializable.class || clazz == Object.class) {
            asm = false;
        }
        if (asm && !ASMUtils.checkName(clazz.getSimpleName())) {
            asm = false;
        }
        if (asm && beanInfo.beanType.isInterface()) {
            asm = false;
        }
        if (asm) {
            for (FieldInfo fieldInfo : beanInfo.fields) {
                Field field = fieldInfo.field;
                if (field != null && !field.getType().equals(fieldInfo.fieldClass)) {
                    asm = false;
                    break;
                }
                Method method = fieldInfo.method;
                if (method != null && !method.getReturnType().equals(fieldInfo.fieldClass)) {
                    asm = false;
                    break;
                }
                if (fieldInfo.fieldClass.isEnum() && this.get(fieldInfo.fieldClass) != EnumSerializer.instance) {
                    asm = false;
                    break;
                }
                JSONField annotation = fieldInfo.getAnnotation();
                if (annotation == null) continue;
                String format = annotation.format();
                if (!(format.length() == 0 || fieldInfo.fieldClass == String.class && "trim".equals(format))) {
                    asm = false;
                    break;
                }
                if (!ASMUtils.checkName(annotation.name()) || annotation.jsonDirect() || annotation.serializeUsing() != Void.class || annotation.unwrapped()) {
                    asm = false;
                    break;
                }
                for (SerializerFeature feature : annotation.serialzeFeatures()) {
                    if (SerializerFeature.WriteNonStringValueAsString != feature && SerializerFeature.WriteEnumUsingToString != feature && SerializerFeature.NotWriteDefaultValue != feature && SerializerFeature.BrowserCompatible != feature && SerializerFeature.WriteClassName != feature) continue;
                    asm = false;
                    break;
                }
                if (TypeUtils.isAnnotationPresentOneToMany(method) || TypeUtils.isAnnotationPresentManyToMany(method)) {
                    asm = false;
                    break;
                }
                if (annotation.defaultValue() == null || "".equals(annotation.defaultValue())) continue;
                asm = false;
                break;
            }
        }
        if (asm) {
            try {
                JavaBeanSerializer javaBeanSerializer = this.createASMSerializer(beanInfo);
                if (javaBeanSerializer != null) {
                    return javaBeanSerializer;
                }
            } catch (ClassNotFoundException classNotFoundException) {
            } catch (ClassFormatError classFormatError) {
            } catch (ClassCastException classCastException) {
            } catch (OutOfMemoryError outOfMemoryError) {
                if (outOfMemoryError.getMessage().indexOf("Metaspace") != -1) {
                    throw outOfMemoryError;
                }
            } catch (Throwable throwable) {
                throw new JSONException("create asm serializer error, verson 1.2.83, class " + clazz, throwable);
            }
        }
        return new JavaBeanSerializer(beanInfo);
    }

    public boolean isAsmEnable() {
        return this.asm;
    }

    public void setAsmEnable(boolean asmEnable) {
        if (ASMUtils.IS_ANDROID) {
            return;
        }
        this.asm = asmEnable;
    }

    public static SerializeConfig getGlobalInstance() {
        return globalInstance;
    }

    public SerializeConfig() {
        this(8192);
    }

    public SerializeConfig(boolean fieldBase) {
        this(8192, fieldBase);
    }

    public SerializeConfig(int tableSize) {
        this(tableSize, false);
    }

    public SerializeConfig(int tableSize, boolean fieldBase) {
        this.fieldBased = fieldBase;
        this.serializers = new IdentityHashMap(tableSize);
        this.mixInSerializers = new IdentityHashMap(16);
        try {
            if (this.asm) {
                this.asmFactory = new ASMSerializerFactory();
            }
        } catch (Throwable eror) {
            this.asm = false;
        }
        this.initSerializers();
    }

    private void initSerializers() {
        this.put((Type)((Object)Boolean.class), BooleanCodec.instance);
        this.put((Type)((Object)Character.class), CharacterCodec.instance);
        this.put((Type)((Object)Byte.class), IntegerCodec.instance);
        this.put((Type)((Object)Short.class), IntegerCodec.instance);
        this.put((Type)((Object)Integer.class), IntegerCodec.instance);
        this.put((Type)((Object)Long.class), LongCodec.instance);
        this.put((Type)((Object)Float.class), FloatCodec.instance);
        this.put((Type)((Object)Double.class), DoubleSerializer.instance);
        this.put((Type)((Object)BigDecimal.class), BigDecimalCodec.instance);
        this.put((Type)((Object)BigInteger.class), BigIntegerCodec.instance);
        this.put((Type)((Object)String.class), StringCodec.instance);
        this.put((Type)((Object)byte[].class), PrimitiveArraySerializer.instance);
        this.put((Type)((Object)short[].class), PrimitiveArraySerializer.instance);
        this.put((Type)((Object)int[].class), PrimitiveArraySerializer.instance);
        this.put((Type)((Object)long[].class), PrimitiveArraySerializer.instance);
        this.put((Type)((Object)float[].class), PrimitiveArraySerializer.instance);
        this.put((Type)((Object)double[].class), PrimitiveArraySerializer.instance);
        this.put((Type)((Object)boolean[].class), PrimitiveArraySerializer.instance);
        this.put((Type)((Object)char[].class), PrimitiveArraySerializer.instance);
        this.put((Type)((Object)Object[].class), ObjectArrayCodec.instance);
        this.put((Type)((Object)Class.class), MiscCodec.instance);
        this.put((Type)((Object)SimpleDateFormat.class), MiscCodec.instance);
        this.put((Type)((Object)Currency.class), new MiscCodec());
        this.put((Type)((Object)TimeZone.class), MiscCodec.instance);
        this.put((Type)((Object)InetAddress.class), MiscCodec.instance);
        this.put((Type)((Object)Inet4Address.class), MiscCodec.instance);
        this.put((Type)((Object)Inet6Address.class), MiscCodec.instance);
        this.put((Type)((Object)InetSocketAddress.class), MiscCodec.instance);
        this.put((Type)((Object)File.class), MiscCodec.instance);
        this.put((Type)((Object)Appendable.class), AppendableSerializer.instance);
        this.put((Type)((Object)StringBuffer.class), AppendableSerializer.instance);
        this.put((Type)((Object)StringBuilder.class), AppendableSerializer.instance);
        this.put((Type)((Object)Charset.class), ToStringSerializer.instance);
        this.put((Type)((Object)Pattern.class), ToStringSerializer.instance);
        this.put((Type)((Object)Locale.class), ToStringSerializer.instance);
        this.put((Type)((Object)URI.class), ToStringSerializer.instance);
        this.put((Type)((Object)URL.class), ToStringSerializer.instance);
        this.put((Type)((Object)UUID.class), ToStringSerializer.instance);
        this.put((Type)((Object)AtomicBoolean.class), AtomicCodec.instance);
        this.put((Type)((Object)AtomicInteger.class), AtomicCodec.instance);
        this.put((Type)((Object)AtomicLong.class), AtomicCodec.instance);
        this.put((Type)((Object)AtomicReference.class), ReferenceCodec.instance);
        this.put((Type)((Object)AtomicIntegerArray.class), AtomicCodec.instance);
        this.put((Type)((Object)AtomicLongArray.class), AtomicCodec.instance);
        this.put((Type)((Object)WeakReference.class), ReferenceCodec.instance);
        this.put((Type)((Object)SoftReference.class), ReferenceCodec.instance);
        this.put((Type)((Object)LinkedList.class), CollectionCodec.instance);
    }

    public void addFilter(Class<?> clazz, SerializeFilter filter) {
        ObjectSerializer serializer = this.getObjectWriter(clazz);
        if (serializer instanceof SerializeFilterable) {
            SerializeFilterable filterable = (SerializeFilterable)((Object)serializer);
            if (this != globalInstance && filterable == MapSerializer.instance) {
                MapSerializer newMapSer = new MapSerializer();
                this.put(clazz, newMapSer);
                newMapSer.addFilter(filter);
                return;
            }
            filterable.addFilter(filter);
        }
    }

    public void config(Class<?> clazz, SerializerFeature feature, boolean value) {
        ObjectSerializer serializer = this.getObjectWriter(clazz, false);
        if (serializer == null) {
            SerializeBeanInfo beanInfo = TypeUtils.buildBeanInfo(clazz, null, this.propertyNamingStrategy);
            beanInfo.features = value ? (beanInfo.features |= feature.mask) : (beanInfo.features &= ~feature.mask);
            serializer = this.createJavaBeanSerializer(beanInfo);
            this.put(clazz, serializer);
            return;
        }
        if (serializer instanceof JavaBeanSerializer) {
            JavaBeanSerializer javaBeanSerializer = (JavaBeanSerializer)serializer;
            SerializeBeanInfo beanInfo = javaBeanSerializer.beanInfo;
            int originalFeaturs = beanInfo.features;
            beanInfo.features = value ? (beanInfo.features |= feature.mask) : (beanInfo.features &= ~feature.mask);
            if (originalFeaturs == beanInfo.features) {
                return;
            }
            Class<?> serializerClass = serializer.getClass();
            if (serializerClass != JavaBeanSerializer.class) {
                ObjectSerializer newSerializer = this.createJavaBeanSerializer(beanInfo);
                this.put(clazz, newSerializer);
            }
        }
    }

    public ObjectSerializer getObjectWriter(Class<?> clazz) {
        return this.getObjectWriter(clazz, true);
    }

    public ObjectSerializer getObjectWriter(Class<?> clazz, boolean create) {
        AutowiredObjectSerializer autowired;
        ObjectSerializer writer = this.get(clazz);
        if (writer != null) {
            return writer;
        }
        try {
            Object classLoader = Thread.currentThread().getContextClassLoader();
            for (AutowiredObjectSerializer o : ServiceLoader.load(AutowiredObjectSerializer.class, (ClassLoader)classLoader)) {
                if (!(o instanceof AutowiredObjectSerializer)) continue;
                autowired = o;
                for (Type forType : autowired.getAutowiredFor()) {
                    this.put(forType, autowired);
                }
            }
        } catch (ClassCastException classLoader) {
            // empty catch block
        }
        if ((writer = this.get(clazz)) == null && (classLoader = JSON.class.getClassLoader()) != Thread.currentThread().getContextClassLoader()) {
            try {
                for (AutowiredObjectSerializer o : ServiceLoader.load(AutowiredObjectSerializer.class, (ClassLoader)classLoader)) {
                    if (!(o instanceof AutowiredObjectSerializer)) continue;
                    autowired = o;
                    for (Type forType : autowired.getAutowiredFor()) {
                        this.put(forType, autowired);
                    }
                }
            } catch (ClassCastException classCastException) {
                // empty catch block
            }
            writer = this.get(clazz);
        }
        for (Module module : this.modules) {
            writer = module.createSerializer(this, clazz);
            if (writer == null) continue;
            this.put(clazz, writer);
            return writer;
        }
        if (writer == null) {
            String className = clazz.getName();
            if (Map.class.isAssignableFrom(clazz)) {
                writer = MapSerializer.instance;
                this.put(clazz, writer);
            } else if (List.class.isAssignableFrom(clazz)) {
                writer = ListSerializer.instance;
                this.put(clazz, writer);
            } else if (Collection.class.isAssignableFrom(clazz)) {
                writer = CollectionCodec.instance;
                this.put(clazz, writer);
            } else if (Date.class.isAssignableFrom(clazz)) {
                writer = DateCodec.instance;
                this.put(clazz, writer);
            } else if (JSONAware.class.isAssignableFrom(clazz)) {
                writer = JSONAwareSerializer.instance;
                this.put(clazz, writer);
            } else if (JSONSerializable.class.isAssignableFrom(clazz)) {
                writer = JSONSerializableSerializer.instance;
                this.put(clazz, writer);
            } else if (JSONStreamAware.class.isAssignableFrom(clazz)) {
                writer = MiscCodec.instance;
                this.put(clazz, writer);
            } else if (clazz.isEnum()) {
                Class mixedInType = (Class)JSON.getMixInAnnotations(clazz);
                JSONType jsonType = mixedInType != null ? TypeUtils.getAnnotation(mixedInType, JSONType.class) : TypeUtils.getAnnotation(clazz, JSONType.class);
                if (jsonType != null && jsonType.serializeEnumAsJavaBean()) {
                    writer = this.createJavaBeanSerializer(clazz);
                    this.put(clazz, writer);
                } else {
                    Member member = null;
                    if (mixedInType != null) {
                        Member mixedInMember = SerializeConfig.getEnumValueField(mixedInType);
                        if (mixedInMember != null) {
                            try {
                                if (mixedInMember instanceof Method) {
                                    Method mixedInMethod = (Method)mixedInMember;
                                    member = clazz.getMethod(mixedInMethod.getName(), mixedInMethod.getParameterTypes());
                                }
                            } catch (Exception mixedInMethod) {}
                        }
                    } else {
                        member = SerializeConfig.getEnumValueField(clazz);
                    }
                    if (member != null) {
                        writer = new EnumSerializer(member);
                        this.put(clazz, writer);
                    } else {
                        writer = this.getEnumSerializer();
                        this.put(clazz, writer);
                    }
                }
            } else {
                Class<?> superClass = clazz.getSuperclass();
                if (superClass != null && superClass.isEnum()) {
                    JSONType jsonType = TypeUtils.getAnnotation(superClass, JSONType.class);
                    if (jsonType != null && jsonType.serializeEnumAsJavaBean()) {
                        writer = this.createJavaBeanSerializer(clazz);
                        this.put(clazz, writer);
                    } else {
                        writer = this.getEnumSerializer();
                        this.put(clazz, writer);
                    }
                } else if (clazz.isArray()) {
                    Class<?> componentType = clazz.getComponentType();
                    String[] compObjectSerializer = this.getObjectWriter(componentType);
                    writer = new ArraySerializer(componentType, (ObjectSerializer)compObjectSerializer);
                    this.put(clazz, writer);
                } else if (Throwable.class.isAssignableFrom(clazz)) {
                    SerializeBeanInfo beanInfo = TypeUtils.buildBeanInfo(clazz, null, this.propertyNamingStrategy);
                    beanInfo.features |= SerializerFeature.WriteClassName.mask;
                    writer = new JavaBeanSerializer(beanInfo);
                    this.put(clazz, writer);
                } else if (TimeZone.class.isAssignableFrom(clazz) || Map.Entry.class.isAssignableFrom(clazz)) {
                    writer = MiscCodec.instance;
                    this.put(clazz, writer);
                } else if (Appendable.class.isAssignableFrom(clazz)) {
                    writer = AppendableSerializer.instance;
                    this.put(clazz, writer);
                } else if (Charset.class.isAssignableFrom(clazz)) {
                    writer = ToStringSerializer.instance;
                    this.put(clazz, writer);
                } else if (Enumeration.class.isAssignableFrom(clazz)) {
                    writer = EnumerationSerializer.instance;
                    this.put(clazz, writer);
                } else if (Calendar.class.isAssignableFrom(clazz) || XMLGregorianCalendar.class.isAssignableFrom(clazz)) {
                    writer = CalendarCodec.instance;
                    this.put(clazz, writer);
                } else if (TypeUtils.isClob(clazz)) {
                    writer = ClobSerializer.instance;
                    this.put(clazz, writer);
                } else if (TypeUtils.isPath(clazz)) {
                    writer = ToStringSerializer.instance;
                    this.put(clazz, writer);
                } else if (Iterator.class.isAssignableFrom(clazz)) {
                    writer = MiscCodec.instance;
                    this.put(clazz, writer);
                } else if (Node.class.isAssignableFrom(clazz)) {
                    writer = MiscCodec.instance;
                    this.put(clazz, writer);
                } else {
                    String[] names;
                    if (className.startsWith("java.awt.") && AwtCodec.support(clazz) && !awtError) {
                        try {
                            for (String name : names = new String[]{"java.awt.Color", "java.awt.Font", "java.awt.Point", "java.awt.Rectangle"}) {
                                if (!name.equals(className)) continue;
                                writer = AwtCodec.instance;
                                this.put(Class.forName(name), writer);
                                return writer;
                            }
                        } catch (Throwable e) {
                            awtError = true;
                        }
                    }
                    if (!jdk8Error && (className.startsWith("java.time.") || className.startsWith("java.util.Optional") || className.equals("java.util.concurrent.atomic.LongAdder") || className.equals("java.util.concurrent.atomic.DoubleAdder"))) {
                        try {
                            for (String name : names = new String[]{"java.time.LocalDateTime", "java.time.LocalDate", "java.time.LocalTime", "java.time.ZonedDateTime", "java.time.OffsetDateTime", "java.time.OffsetTime", "java.time.ZoneOffset", "java.time.ZoneRegion", "java.time.Period", "java.time.Duration", "java.time.Instant"}) {
                                if (!name.equals(className)) continue;
                                writer = Jdk8DateCodec.instance;
                                this.put(Class.forName(name), writer);
                                return writer;
                            }
                            for (String name : names = new String[]{"java.util.Optional", "java.util.OptionalDouble", "java.util.OptionalInt", "java.util.OptionalLong"}) {
                                if (!name.equals(className)) continue;
                                writer = OptionalCodec.instance;
                                this.put(Class.forName(name), writer);
                                return writer;
                            }
                            for (String name : names = new String[]{"java.util.concurrent.atomic.LongAdder", "java.util.concurrent.atomic.DoubleAdder"}) {
                                if (!name.equals(className)) continue;
                                writer = AdderSerializer.instance;
                                this.put(Class.forName(name), writer);
                                return writer;
                            }
                        } catch (Throwable e) {
                            jdk8Error = true;
                        }
                    }
                    if (!oracleJdbcError && className.startsWith("oracle.sql.")) {
                        try {
                            for (String name : names = new String[]{"oracle.sql.DATE", "oracle.sql.TIMESTAMP"}) {
                                if (!name.equals(className)) continue;
                                writer = DateCodec.instance;
                                this.put(Class.forName(name), writer);
                                return writer;
                            }
                        } catch (Throwable e) {
                            oracleJdbcError = true;
                        }
                    }
                    if (!springfoxError && className.equals("springfox.documentation.spring.web.json.Json")) {
                        try {
                            writer = SwaggerJsonSerializer.instance;
                            this.put(Class.forName("springfox.documentation.spring.web.json.Json"), writer);
                            return writer;
                        } catch (ClassNotFoundException e) {
                            springfoxError = true;
                        }
                    }
                    if (!guavaError && className.startsWith("com.google.common.collect.")) {
                        try {
                            for (String name : names = new String[]{"com.google.common.collect.HashMultimap", "com.google.common.collect.LinkedListMultimap", "com.google.common.collect.LinkedHashMultimap", "com.google.common.collect.ArrayListMultimap", "com.google.common.collect.TreeMultimap"}) {
                                if (!name.equals(className)) continue;
                                writer = GuavaCodec.instance;
                                this.put(Class.forName(name), writer);
                                return writer;
                            }
                        } catch (ClassNotFoundException e) {
                            guavaError = true;
                        }
                    }
                    if (className.equals("net.sf.json.JSONNull")) {
                        writer = MiscCodec.instance;
                        this.put(clazz, writer);
                        return writer;
                    }
                    if (className.equals("org.json.JSONObject")) {
                        writer = JSONObjectCodec.instance;
                        this.put(clazz, writer);
                        return writer;
                    }
                    if (!jodaError && className.startsWith("org.joda.")) {
                        try {
                            for (String name : names = new String[]{"org.joda.time.LocalDate", "org.joda.time.LocalDateTime", "org.joda.time.LocalTime", "org.joda.time.Instant", "org.joda.time.DateTime", "org.joda.time.Period", "org.joda.time.Duration", "org.joda.time.DateTimeZone", "org.joda.time.UTCDateTimeZone", "org.joda.time.tz.CachedDateTimeZone", "org.joda.time.tz.FixedDateTimeZone"}) {
                                if (!name.equals(className)) continue;
                                writer = JodaCodec.instance;
                                this.put(Class.forName(name), writer);
                                return writer;
                            }
                        } catch (ClassNotFoundException e) {
                            jodaError = true;
                        }
                    }
                    if ("java.nio.HeapByteBuffer".equals(className)) {
                        writer = ByteBufferCodec.instance;
                        this.put(clazz, writer);
                        return writer;
                    }
                    if ("org.javamoney.moneta.Money".equals(className)) {
                        writer = MonetaCodec.instance;
                        this.put(clazz, writer);
                        return writer;
                    }
                    if ("com.google.protobuf.Descriptors$FieldDescriptor".equals(className)) {
                        writer = ToStringSerializer.instance;
                        this.put(clazz, writer);
                        return writer;
                    }
                    Class<?>[] interfaces = clazz.getInterfaces();
                    if (interfaces.length == 1 && interfaces[0].isAnnotation()) {
                        this.put(clazz, AnnotationSerializer.instance);
                        return AnnotationSerializer.instance;
                    }
                    if (TypeUtils.isProxy(clazz)) {
                        Class<?> superClazz = clazz.getSuperclass();
                        ObjectSerializer superWriter = this.getObjectWriter(superClazz);
                        this.put(clazz, superWriter);
                        return superWriter;
                    }
                    if (Proxy.isProxyClass(clazz)) {
                        Class<?> handlerClass = null;
                        if (interfaces.length == 2) {
                            handlerClass = interfaces[1];
                        } else {
                            for (Class<?> proxiedInterface : interfaces) {
                                if (proxiedInterface.getName().startsWith("org.springframework.aop.")) continue;
                                if (handlerClass != null) {
                                    handlerClass = null;
                                    break;
                                }
                                handlerClass = proxiedInterface;
                            }
                        }
                        if (handlerClass != null) {
                            ObjectSerializer superWriter = this.getObjectWriter(handlerClass);
                            this.put(clazz, superWriter);
                            return superWriter;
                        }
                    }
                    if (create) {
                        writer = this.createJavaBeanSerializer(clazz);
                        this.put(clazz, writer);
                    }
                }
            }
            if (writer == null) {
                writer = this.get(clazz);
            }
        }
        return writer;
    }

    private static Member getEnumValueField(Class clazz) {
        JSONField jsonField;
        AccessibleObject member = null;
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            if (method.getReturnType() == Void.class || (jsonField = method.getAnnotation(JSONField.class)) == null) continue;
            if (member != null) {
                return null;
            }
            member = method;
        }
        for (AccessibleObject accessibleObject : clazz.getFields()) {
            jsonField = ((Field)accessibleObject).getAnnotation(JSONField.class);
            if (jsonField == null) continue;
            if (member != null) {
                return null;
            }
            member = accessibleObject;
        }
        return member;
    }

    protected ObjectSerializer getEnumSerializer() {
        return EnumSerializer.instance;
    }

    public final ObjectSerializer get(Type type) {
        Type mixin = JSON.getMixInAnnotations(type);
        if (null == mixin) {
            return this.serializers.get(type);
        }
        IdentityHashMap<Type, ObjectSerializer> mixInClasses = this.mixInSerializers.get(type);
        if (mixInClasses == null) {
            return null;
        }
        return mixInClasses.get(mixin);
    }

    public boolean put(Object type, Object value) {
        return this.put((Type)type, (ObjectSerializer)value);
    }

    public boolean put(Type type, ObjectSerializer value) {
        Type mixin = JSON.getMixInAnnotations(type);
        if (mixin != null) {
            IdentityHashMap<Type, ObjectSerializer> mixInClasses = this.mixInSerializers.get(type);
            if (mixInClasses == null) {
                mixInClasses = new IdentityHashMap(4);
                this.mixInSerializers.put(type, mixInClasses);
            }
            return mixInClasses.put(mixin, value);
        }
        return this.serializers.put(type, value);
    }

    public void configEnumAsJavaBean(Class<? extends Enum> ... enumClasses) {
        for (Class<? extends Enum> enumClass : enumClasses) {
            this.put(enumClass, this.createJavaBeanSerializer(enumClass));
        }
    }

    public void setPropertyNamingStrategy(PropertyNamingStrategy propertyNamingStrategy) {
        this.propertyNamingStrategy = propertyNamingStrategy;
    }

    public void clearSerializers() {
        this.serializers.clear();
        this.initSerializers();
    }

    public void register(Module module) {
        this.modules.add(module);
    }
}

