/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.parser;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.alibaba.fastjson.JSONPath;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.annotation.JSONCreator;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.asm.ClassReader;
import com.alibaba.fastjson.asm.TypeCollector;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.SymbolTable;
import com.alibaba.fastjson.parser.deserializer.ASMDeserializerFactory;
import com.alibaba.fastjson.parser.deserializer.ArrayListTypeFieldDeserializer;
import com.alibaba.fastjson.parser.deserializer.AutowiredObjectDeserializer;
import com.alibaba.fastjson.parser.deserializer.DefaultFieldDeserializer;
import com.alibaba.fastjson.parser.deserializer.EnumCreatorDeserializer;
import com.alibaba.fastjson.parser.deserializer.EnumDeserializer;
import com.alibaba.fastjson.parser.deserializer.FieldDeserializer;
import com.alibaba.fastjson.parser.deserializer.JSONPDeserializer;
import com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer;
import com.alibaba.fastjson.parser.deserializer.JavaObjectDeserializer;
import com.alibaba.fastjson.parser.deserializer.Jdk8DateCodec;
import com.alibaba.fastjson.parser.deserializer.MapDeserializer;
import com.alibaba.fastjson.parser.deserializer.NumberDeserializer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.parser.deserializer.OptionalCodec;
import com.alibaba.fastjson.parser.deserializer.PropertyProcessable;
import com.alibaba.fastjson.parser.deserializer.PropertyProcessableDeserializer;
import com.alibaba.fastjson.parser.deserializer.SqlDateDeserializer;
import com.alibaba.fastjson.parser.deserializer.StackTraceElementDeserializer;
import com.alibaba.fastjson.parser.deserializer.ThrowableDeserializer;
import com.alibaba.fastjson.parser.deserializer.TimeDeserializer;
import com.alibaba.fastjson.serializer.AtomicCodec;
import com.alibaba.fastjson.serializer.AwtCodec;
import com.alibaba.fastjson.serializer.BigDecimalCodec;
import com.alibaba.fastjson.serializer.BigIntegerCodec;
import com.alibaba.fastjson.serializer.BooleanCodec;
import com.alibaba.fastjson.serializer.ByteBufferCodec;
import com.alibaba.fastjson.serializer.CalendarCodec;
import com.alibaba.fastjson.serializer.CharArrayCodec;
import com.alibaba.fastjson.serializer.CharacterCodec;
import com.alibaba.fastjson.serializer.CollectionCodec;
import com.alibaba.fastjson.serializer.DateCodec;
import com.alibaba.fastjson.serializer.FloatCodec;
import com.alibaba.fastjson.serializer.GuavaCodec;
import com.alibaba.fastjson.serializer.IntegerCodec;
import com.alibaba.fastjson.serializer.JodaCodec;
import com.alibaba.fastjson.serializer.LongCodec;
import com.alibaba.fastjson.serializer.MiscCodec;
import com.alibaba.fastjson.serializer.ObjectArrayCodec;
import com.alibaba.fastjson.serializer.ReferenceCodec;
import com.alibaba.fastjson.serializer.StringCodec;
import com.alibaba.fastjson.spi.Module;
import com.alibaba.fastjson.support.moneta.MonetaCodec;
import com.alibaba.fastjson.util.ASMClassLoader;
import com.alibaba.fastjson.util.ASMUtils;
import com.alibaba.fastjson.util.FieldInfo;
import com.alibaba.fastjson.util.Function;
import com.alibaba.fastjson.util.IOUtils;
import com.alibaba.fastjson.util.IdentityHashMap;
import com.alibaba.fastjson.util.JavaBeanInfo;
import com.alibaba.fastjson.util.ModuleUtil;
import com.alibaba.fastjson.util.ParameterizedTypeImpl;
import com.alibaba.fastjson.util.ServiceLoader;
import com.alibaba.fastjson.util.TypeUtils;
import java.io.Closeable;
import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.AccessControlException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Currency;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TimeZone;
import java.util.TreeMap;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Pattern;
import javax.sql.DataSource;
import javax.sql.RowSet;
import javax.xml.datatype.XMLGregorianCalendar;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class ParserConfig {
    public static final String DENY_PROPERTY_INTERNAL = "fastjson.parser.deny.internal";
    public static final String DENY_PROPERTY = "fastjson.parser.deny";
    public static final String AUTOTYPE_ACCEPT = "fastjson.parser.autoTypeAccept";
    public static final String AUTOTYPE_SUPPORT_PROPERTY = "fastjson.parser.autoTypeSupport";
    public static final String SAFE_MODE_PROPERTY = "fastjson.parser.safeMode";
    public static final String[] DENYS_INTERNAL;
    public static final String[] DENYS;
    private static final String[] AUTO_TYPE_ACCEPT_LIST;
    public static final boolean AUTO_SUPPORT;
    public static final boolean SAFE_MODE;
    private static final long[] INTERNAL_WHITELIST_HASHCODES;
    public static ParserConfig global;
    private final IdentityHashMap<Type, ObjectDeserializer> deserializers = new IdentityHashMap();
    private final IdentityHashMap<Type, IdentityHashMap<Type, ObjectDeserializer>> mixInDeserializers = new IdentityHashMap(16);
    private final ConcurrentMap<String, Class<?>> typeMapping = new ConcurrentHashMap(16, 0.75f, 1);
    private boolean asmEnable = !ASMUtils.IS_ANDROID;
    public final SymbolTable symbolTable = new SymbolTable(4096);
    public PropertyNamingStrategy propertyNamingStrategy;
    protected ClassLoader defaultClassLoader;
    protected ASMDeserializerFactory asmFactory;
    private static boolean awtError;
    private static boolean jdk8Error;
    private static boolean jodaError;
    private static boolean guavaError;
    private boolean autoTypeSupport = AUTO_SUPPORT;
    private long[] internalDenyHashCodes;
    private long[] denyHashCodes;
    private long[] acceptHashCodes;
    public final boolean fieldBased;
    private boolean jacksonCompatible = false;
    public boolean compatibleWithJavaBean = TypeUtils.compatibleWithJavaBean;
    private List<Module> modules = new ArrayList<Module>();
    private volatile List<AutoTypeCheckHandler> autoTypeCheckHandlers;
    private boolean safeMode = SAFE_MODE;
    private final Callable<Void> initDeserializersWithJavaSql;
    private static Function<Class<?>, Boolean> isPrimitiveFuncation;

    public static ParserConfig getGlobalInstance() {
        return global;
    }

    public ParserConfig() {
        this(false);
    }

    public ParserConfig(boolean fieldBase) {
        this(null, null, fieldBase);
    }

    public ParserConfig(ClassLoader parentClassLoader) {
        this(null, parentClassLoader, false);
    }

    public ParserConfig(ASMDeserializerFactory asmFactory) {
        this(asmFactory, null, false);
    }

    private ParserConfig(ASMDeserializerFactory asmFactory, ClassLoader parentClassLoader, boolean fieldBased) {
        this.denyHashCodes = new long[]{-9164606388214699518L, -8754006975464705441L, -8720046426850100497L, -8649961213709896794L, -8614556368991373401L, -8382625455832334425L, -8165637398350707645L, -8109300701639721088L, -7966123100503199569L, -7921218830998286408L, -7775351613326101303L, -7768608037458185275L, -7766605818834748097L, -6835437086156813536L, -6316154655839304624L, -6179589609550493385L, -6149130139291498841L, -6149093380703242441L, -6088208984980396913L, -6025144546313590215L, -5939269048541779808L, -5885964883385605994L, -5767141746063564198L, -5764804792063216819L, -5472097725414717105L, -5194641081268104286L, -5076846148177416215L, -4837536971810737970L, -4836620931940850535L, -4733542790109620528L, -4703320437989596122L, -4608341446948126581L, -4537258998789938600L, -4438775680185074100L, -4314457471973557243L, -4150995715611818742L, -4082057040235125754L, -3975378478825053783L, -3967588558552655563L, -3935185854875733362L, -3750763034362895579L, -3319207949486691020L, -3077205613010077203L, -3053747177772160511L, -2995060141064716555L, -2825378362173150292L, -2533039401923731906L, -2439930098895578154L, -2378990704010641148L, -2364987994247679115L, -2262244760619952081L, -2192804397019347313L, -2095516571388852610L, -1872417015366588117L, -1800035667138631116L, -1650485814983027158L, -1589194880214235129L, -1363634950764737555L, -965955008570215305L, -905177026366752536L, -831789045734283466L, -803541446955902575L, -731978084025273882L, -666475508176557463L, -582813228520337988L, -254670111376247151L, -219577392946377768L, -190281065685395680L, -26639035867733124L, -9822483067882491L, 4750336058574309L, 33238344207745342L, 156405680656087946L, 218512992947536312L, 313864100207897507L, 386461436234701831L, 744602970950881621L, 823641066473609950L, 860052378298585747L, 1073634739308289776L, 1153291637701043748L, 1203232727967308606L, 1214780596910349029L, 1268707909007641340L, 1459860845934817624L, 1502845958873959152L, 1534439610567445754L, 1698504441317515818L, 1818089308493370394L, 2078113382421334967L, 2164696723069287854L, 2622551729063269307L, 2653453629929770569L, 2660670623866180977L, 2731823439467737506L, 2836431254737891113L, 2930861374593775110L, 3058452313624178956L, 3085473968517218653L, 3089451460101527857L, 3114862868117605599L, 3129395579983849527L, 3256258368248066264L, 3452379460455804429L, 3547627781654598988L, 3637939656440441093L, 3688179072722109200L, 3718352661124136681L, 3730752432285826863L, 3740226159580918099L, 3794316665763266033L, 3977090344859527316L, 4000049462512838776L, 4046190361520671643L, 4147696707147271408L, 4193204392725694463L, 4215053018660518963L, 4241163808635564644L, 4254584350247334433L, 4319304524795015394L, 4814658433570175913L, 4841947709850912914L, 4904007817188630457L, 5100336081510080343L, 5120543992130540564L, 5274044858141538265L, 5347909877633654828L, 5450448828334921485L, 5474268165959054640L, 5545425291794704408L, 5596129856135573697L, 5688200883751798389L, 5751393439502795295L, 5916409771425455946L, 5944107969236155580L, 6007332606592876737L, 6090377589998869205L, 6280357960959217660L, 6456855723474196908L, 6511035576063254270L, 6534946468240507089L, 6584624952928234050L, 6734240326434096246L, 6742705432718011780L, 6800727078373023163L, 6854854816081053523L, 7045245923763966215L, 7123326897294507060L, 7164889056054194741L, 7179336928365889465L, 7240293012336844478L, 7347653049056829645L, 7375862386996623731L, 7442624256860549330L, 7617522210483516279L, 7658177784286215602L, 8055461369741094911L, 8064026652676081192L, 8389032537095247355L, 8409640769019589119L, 8488266005336625107L, 8537233257283452655L, 8711531061028787095L, 8735538376409180149L, 8838294710098435315L, 8861402923078831179L, 9140390920032557669L, 9140416208800006522L, 9144212112462101475L};
        long[] hashCodes = new long[AUTO_TYPE_ACCEPT_LIST.length];
        for (int i = 0; i < AUTO_TYPE_ACCEPT_LIST.length; ++i) {
            hashCodes[i] = TypeUtils.fnv1a_64(AUTO_TYPE_ACCEPT_LIST[i]);
        }
        Arrays.sort(hashCodes);
        this.acceptHashCodes = hashCodes;
        this.initDeserializersWithJavaSql = new Callable<Void>(){

            @Override
            public Void call() {
                ParserConfig.this.deserializers.put(Timestamp.class, SqlDateDeserializer.instance_timestamp);
                ParserConfig.this.deserializers.put(java.sql.Date.class, SqlDateDeserializer.instance);
                ParserConfig.this.deserializers.put(Time.class, TimeDeserializer.instance);
                ParserConfig.this.deserializers.put(Date.class, DateCodec.instance);
                return null;
            }
        };
        this.fieldBased = fieldBased;
        if (asmFactory == null && !ASMUtils.IS_ANDROID) {
            try {
                asmFactory = parentClassLoader == null ? new ASMDeserializerFactory(new ASMClassLoader()) : new ASMDeserializerFactory(parentClassLoader);
            } catch (ExceptionInInitializerError exceptionInInitializerError) {
            } catch (AccessControlException accessControlException) {
            } catch (NoClassDefFoundError noClassDefFoundError) {
                // empty catch block
            }
        }
        this.asmFactory = asmFactory;
        if (asmFactory == null) {
            this.asmEnable = false;
        }
        this.initDeserializers();
        this.addItemsToDeny(DENYS);
        this.addItemsToDeny0(DENYS_INTERNAL);
        this.addItemsToAccept(AUTO_TYPE_ACCEPT_LIST);
    }

    private void initDeserializers() {
        this.deserializers.put((Type)((Object)SimpleDateFormat.class), MiscCodec.instance);
        this.deserializers.put((Type)((Object)Calendar.class), CalendarCodec.instance);
        this.deserializers.put((Type)((Object)XMLGregorianCalendar.class), CalendarCodec.instance);
        this.deserializers.put((Type)((Object)JSONObject.class), MapDeserializer.instance);
        this.deserializers.put((Type)((Object)JSONArray.class), CollectionCodec.instance);
        this.deserializers.put((Type)((Object)Map.class), MapDeserializer.instance);
        this.deserializers.put((Type)((Object)HashMap.class), MapDeserializer.instance);
        this.deserializers.put((Type)((Object)LinkedHashMap.class), MapDeserializer.instance);
        this.deserializers.put((Type)((Object)TreeMap.class), MapDeserializer.instance);
        this.deserializers.put((Type)((Object)ConcurrentMap.class), MapDeserializer.instance);
        this.deserializers.put((Type)((Object)ConcurrentHashMap.class), MapDeserializer.instance);
        this.deserializers.put((Type)((Object)Collection.class), CollectionCodec.instance);
        this.deserializers.put((Type)((Object)List.class), CollectionCodec.instance);
        this.deserializers.put((Type)((Object)ArrayList.class), CollectionCodec.instance);
        this.deserializers.put((Type)((Object)Object.class), JavaObjectDeserializer.instance);
        this.deserializers.put((Type)((Object)String.class), StringCodec.instance);
        this.deserializers.put((Type)((Object)StringBuffer.class), StringCodec.instance);
        this.deserializers.put((Type)((Object)StringBuilder.class), StringCodec.instance);
        this.deserializers.put(Character.TYPE, CharacterCodec.instance);
        this.deserializers.put((Type)((Object)Character.class), CharacterCodec.instance);
        this.deserializers.put(Byte.TYPE, NumberDeserializer.instance);
        this.deserializers.put((Type)((Object)Byte.class), NumberDeserializer.instance);
        this.deserializers.put(Short.TYPE, NumberDeserializer.instance);
        this.deserializers.put((Type)((Object)Short.class), NumberDeserializer.instance);
        this.deserializers.put(Integer.TYPE, IntegerCodec.instance);
        this.deserializers.put((Type)((Object)Integer.class), IntegerCodec.instance);
        this.deserializers.put(Long.TYPE, LongCodec.instance);
        this.deserializers.put((Type)((Object)Long.class), LongCodec.instance);
        this.deserializers.put((Type)((Object)BigInteger.class), BigIntegerCodec.instance);
        this.deserializers.put((Type)((Object)BigDecimal.class), BigDecimalCodec.instance);
        this.deserializers.put(Float.TYPE, FloatCodec.instance);
        this.deserializers.put((Type)((Object)Float.class), FloatCodec.instance);
        this.deserializers.put(Double.TYPE, NumberDeserializer.instance);
        this.deserializers.put((Type)((Object)Double.class), NumberDeserializer.instance);
        this.deserializers.put(Boolean.TYPE, BooleanCodec.instance);
        this.deserializers.put((Type)((Object)Boolean.class), BooleanCodec.instance);
        this.deserializers.put((Type)((Object)Class.class), MiscCodec.instance);
        this.deserializers.put((Type)((Object)char[].class), new CharArrayCodec());
        this.deserializers.put((Type)((Object)AtomicBoolean.class), BooleanCodec.instance);
        this.deserializers.put((Type)((Object)AtomicInteger.class), IntegerCodec.instance);
        this.deserializers.put((Type)((Object)AtomicLong.class), LongCodec.instance);
        this.deserializers.put((Type)((Object)AtomicReference.class), ReferenceCodec.instance);
        this.deserializers.put((Type)((Object)WeakReference.class), ReferenceCodec.instance);
        this.deserializers.put((Type)((Object)SoftReference.class), ReferenceCodec.instance);
        this.deserializers.put((Type)((Object)UUID.class), MiscCodec.instance);
        this.deserializers.put((Type)((Object)TimeZone.class), MiscCodec.instance);
        this.deserializers.put((Type)((Object)Locale.class), MiscCodec.instance);
        this.deserializers.put((Type)((Object)Currency.class), MiscCodec.instance);
        this.deserializers.put((Type)((Object)Inet4Address.class), MiscCodec.instance);
        this.deserializers.put((Type)((Object)Inet6Address.class), MiscCodec.instance);
        this.deserializers.put((Type)((Object)InetSocketAddress.class), MiscCodec.instance);
        this.deserializers.put((Type)((Object)File.class), MiscCodec.instance);
        this.deserializers.put((Type)((Object)URI.class), MiscCodec.instance);
        this.deserializers.put((Type)((Object)URL.class), MiscCodec.instance);
        this.deserializers.put((Type)((Object)Pattern.class), MiscCodec.instance);
        this.deserializers.put((Type)((Object)Charset.class), MiscCodec.instance);
        this.deserializers.put((Type)((Object)JSONPath.class), MiscCodec.instance);
        this.deserializers.put((Type)((Object)Number.class), NumberDeserializer.instance);
        this.deserializers.put((Type)((Object)AtomicIntegerArray.class), AtomicCodec.instance);
        this.deserializers.put((Type)((Object)AtomicLongArray.class), AtomicCodec.instance);
        this.deserializers.put((Type)((Object)StackTraceElement.class), StackTraceElementDeserializer.instance);
        this.deserializers.put((Type)((Object)Serializable.class), JavaObjectDeserializer.instance);
        this.deserializers.put((Type)((Object)Cloneable.class), JavaObjectDeserializer.instance);
        this.deserializers.put((Type)((Object)Comparable.class), JavaObjectDeserializer.instance);
        this.deserializers.put((Type)((Object)Closeable.class), JavaObjectDeserializer.instance);
        this.deserializers.put((Type)((Object)JSONPObject.class), new JSONPDeserializer());
        ModuleUtil.callWhenHasJavaSql(this.initDeserializersWithJavaSql);
    }

    private static String[] splitItemsFormProperty(String property) {
        if (property != null && property.length() > 0) {
            return property.split(",");
        }
        return null;
    }

    public void configFromPropety(Properties properties) {
        String property = properties.getProperty(DENY_PROPERTY);
        String[] items = ParserConfig.splitItemsFormProperty(property);
        this.addItemsToDeny(items);
        property = properties.getProperty(AUTOTYPE_ACCEPT);
        items = ParserConfig.splitItemsFormProperty(property);
        this.addItemsToAccept(items);
        property = properties.getProperty(AUTOTYPE_SUPPORT_PROPERTY);
        if ("true".equals(property)) {
            this.autoTypeSupport = true;
        } else if ("false".equals(property)) {
            this.autoTypeSupport = false;
        }
    }

    private void addItemsToDeny0(String[] items) {
        if (items == null) {
            return;
        }
        for (int i = 0; i < items.length; ++i) {
            String item = items[i];
            this.addDenyInternal(item);
        }
    }

    private void addItemsToDeny(String[] items) {
        if (items == null) {
            return;
        }
        for (int i = 0; i < items.length; ++i) {
            String item = items[i];
            this.addDeny(item);
        }
    }

    private void addItemsToAccept(String[] items) {
        if (items == null) {
            return;
        }
        for (int i = 0; i < items.length; ++i) {
            String item = items[i];
            this.addAccept(item);
        }
    }

    public boolean isSafeMode() {
        return this.safeMode;
    }

    public void setSafeMode(boolean safeMode) {
        this.safeMode = safeMode;
    }

    public boolean isAutoTypeSupport() {
        return this.autoTypeSupport;
    }

    public void setAutoTypeSupport(boolean autoTypeSupport) {
        this.autoTypeSupport = autoTypeSupport;
    }

    public boolean isAsmEnable() {
        return this.asmEnable;
    }

    public void setAsmEnable(boolean asmEnable) {
        this.asmEnable = asmEnable;
    }

    public IdentityHashMap<Type, ObjectDeserializer> getDerializers() {
        return this.deserializers;
    }

    public IdentityHashMap<Type, ObjectDeserializer> getDeserializers() {
        return this.deserializers;
    }

    public ObjectDeserializer getDeserializer(Type type) {
        WildcardType wildcardType;
        Type[] upperBounds;
        ObjectDeserializer deserializer = this.get(type);
        if (deserializer != null) {
            return deserializer;
        }
        if (type instanceof Class) {
            return this.getDeserializer((Class)type, type);
        }
        if (type instanceof ParameterizedType) {
            Type rawType = ((ParameterizedType)type).getRawType();
            if (rawType instanceof Class) {
                return this.getDeserializer((Class)rawType, type);
            }
            return this.getDeserializer(rawType);
        }
        if (type instanceof WildcardType && (upperBounds = (wildcardType = (WildcardType)type).getUpperBounds()).length == 1) {
            Type upperBoundType = upperBounds[0];
            return this.getDeserializer(upperBoundType);
        }
        return JavaObjectDeserializer.instance;
    }

    public ObjectDeserializer getDeserializer(Class<?> clazz, Type type) {
        String[] names;
        Class<?> mappingTo;
        ObjectDeserializer deserializer = this.get(type);
        if (deserializer == null && type instanceof ParameterizedTypeImpl) {
            Type innerType = TypeReference.intern((ParameterizedTypeImpl)((Object)type));
            deserializer = this.get(innerType);
        }
        if (deserializer != null) {
            return deserializer;
        }
        if (type == null) {
            type = clazz;
        }
        if ((deserializer = this.get(type)) != null) {
            return deserializer;
        }
        JSONType annotation = TypeUtils.getAnnotation(clazz, JSONType.class);
        if (annotation != null && (mappingTo = annotation.mappingTo()) != Void.class) {
            return this.getDeserializer(mappingTo, mappingTo);
        }
        if (type instanceof WildcardType || type instanceof TypeVariable || type instanceof ParameterizedType) {
            deserializer = this.get(clazz);
        }
        if (deserializer != null) {
            return deserializer;
        }
        for (Module module : this.modules) {
            deserializer = module.createDeserializer(this, clazz);
            if (deserializer == null) continue;
            this.putDeserializer(type, deserializer);
            return deserializer;
        }
        String className = clazz.getName();
        if ((className = className.replace('$', '.')).startsWith("java.awt.") && AwtCodec.support(clazz) && !awtError) {
            names = new String[]{"java.awt.Point", "java.awt.Font", "java.awt.Rectangle", "java.awt.Color"};
            try {
                for (String name : names) {
                    if (!name.equals(className)) continue;
                    deserializer = AwtCodec.instance;
                    this.putDeserializer(Class.forName(name), deserializer);
                    return deserializer;
                }
            } catch (Throwable e) {
                awtError = true;
            }
            deserializer = AwtCodec.instance;
        }
        if (!jdk8Error) {
            try {
                if (className.startsWith("java.time.")) {
                    for (String name : names = new String[]{"java.time.LocalDateTime", "java.time.LocalDate", "java.time.LocalTime", "java.time.ZonedDateTime", "java.time.OffsetDateTime", "java.time.OffsetTime", "java.time.ZoneOffset", "java.time.ZoneRegion", "java.time.ZoneId", "java.time.Period", "java.time.Duration", "java.time.Instant"}) {
                        if (!name.equals(className)) continue;
                        deserializer = Jdk8DateCodec.instance;
                        this.putDeserializer(Class.forName(name), deserializer);
                        return deserializer;
                    }
                } else if (className.startsWith("java.util.Optional")) {
                    for (String name : names = new String[]{"java.util.Optional", "java.util.OptionalDouble", "java.util.OptionalInt", "java.util.OptionalLong"}) {
                        if (!name.equals(className)) continue;
                        deserializer = OptionalCodec.instance;
                        this.putDeserializer(Class.forName(name), deserializer);
                        return deserializer;
                    }
                }
            } catch (Throwable e) {
                jdk8Error = true;
            }
        }
        if (!jodaError) {
            try {
                if (className.startsWith("org.joda.time.")) {
                    for (String name : names = new String[]{"org.joda.time.DateTime", "org.joda.time.LocalDate", "org.joda.time.LocalDateTime", "org.joda.time.LocalTime", "org.joda.time.Instant", "org.joda.time.Period", "org.joda.time.Duration", "org.joda.time.DateTimeZone", "org.joda.time.format.DateTimeFormatter"}) {
                        if (!name.equals(className)) continue;
                        deserializer = JodaCodec.instance;
                        this.putDeserializer(Class.forName(name), deserializer);
                        return deserializer;
                    }
                }
            } catch (Throwable e) {
                jodaError = true;
            }
        }
        if (!guavaError && className.startsWith("com.google.common.collect.")) {
            try {
                for (String name : names = new String[]{"com.google.common.collect.HashMultimap", "com.google.common.collect.LinkedListMultimap", "com.google.common.collect.LinkedHashMultimap", "com.google.common.collect.ArrayListMultimap", "com.google.common.collect.TreeMultimap"}) {
                    if (!name.equals(className)) continue;
                    deserializer = GuavaCodec.instance;
                    this.putDeserializer(Class.forName(name), deserializer);
                    return deserializer;
                }
            } catch (ClassNotFoundException e) {
                guavaError = true;
            }
        }
        if (className.equals("java.nio.ByteBuffer")) {
            deserializer = ByteBufferCodec.instance;
            this.putDeserializer(clazz, deserializer);
        }
        if (className.equals("java.nio.file.Path")) {
            deserializer = MiscCodec.instance;
            this.putDeserializer(clazz, deserializer);
        }
        if (clazz == Map.Entry.class) {
            deserializer = MiscCodec.instance;
            this.putDeserializer(clazz, deserializer);
        }
        if (className.equals("org.javamoney.moneta.Money")) {
            deserializer = MonetaCodec.instance;
            this.putDeserializer(clazz, deserializer);
        }
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try {
            for (AutowiredObjectDeserializer autowired : ServiceLoader.load(AutowiredObjectDeserializer.class, classLoader)) {
                for (Type forType : autowired.getAutowiredFor()) {
                    this.putDeserializer(forType, autowired);
                }
            }
        } catch (Exception e) {
            // empty catch block
        }
        if (deserializer == null) {
            deserializer = this.get(type);
        }
        if (deserializer != null) {
            return deserializer;
        }
        if (clazz.isEnum()) {
            if (this.jacksonCompatible) {
                Method[] methods;
                for (Method method : methods = clazz.getMethods()) {
                    if (!TypeUtils.isJacksonCreator(method)) continue;
                    deserializer = this.createJavaBeanDeserializer(clazz, type);
                    this.putDeserializer(type, deserializer);
                    return deserializer;
                }
            }
            Class<PropertyProcessable> mixInType = (Class<PropertyProcessable>)JSON.getMixInAnnotations(clazz);
            Class<?> deserClass = null;
            JSONType jsonType = TypeUtils.getAnnotation(mixInType != null ? mixInType : clazz, JSONType.class);
            if (jsonType != null) {
                deserClass = jsonType.deserializer();
                try {
                    deserializer = (ObjectDeserializer)deserClass.newInstance();
                    this.putDeserializer(clazz, deserializer);
                    return deserializer;
                } catch (Throwable forType) {
                    // empty catch block
                }
            }
            Method jsonCreatorMethod = null;
            if (mixInType != null) {
                Method mixedCreator = ParserConfig.getEnumCreator(mixInType, clazz);
                if (mixedCreator != null) {
                    try {
                        jsonCreatorMethod = clazz.getMethod(mixedCreator.getName(), mixedCreator.getParameterTypes());
                    } catch (Exception exception) {}
                }
            } else {
                jsonCreatorMethod = ParserConfig.getEnumCreator(clazz, clazz);
            }
            if (jsonCreatorMethod != null) {
                deserializer = new EnumCreatorDeserializer(jsonCreatorMethod);
                this.putDeserializer(clazz, deserializer);
                return deserializer;
            }
            deserializer = this.getEnumDeserializer(clazz);
        } else {
            deserializer = clazz.isArray() ? ObjectArrayCodec.instance : (clazz == Set.class || clazz == HashSet.class || clazz == Collection.class || clazz == List.class || clazz == ArrayList.class ? CollectionCodec.instance : (Collection.class.isAssignableFrom(clazz) ? CollectionCodec.instance : (Map.class.isAssignableFrom(clazz) ? MapDeserializer.instance : (Throwable.class.isAssignableFrom(clazz) ? new ThrowableDeserializer(this, clazz) : (PropertyProcessable.class.isAssignableFrom(clazz) ? new PropertyProcessableDeserializer(clazz) : (clazz == InetAddress.class ? MiscCodec.instance : this.createJavaBeanDeserializer(clazz, type)))))));
        }
        this.putDeserializer(type, deserializer);
        return deserializer;
    }

    private static Method getEnumCreator(Class clazz, Class enumClass) {
        Method[] methods = clazz.getMethods();
        Method jsonCreatorMethod = null;
        for (Method method : methods) {
            JSONCreator jsonCreator;
            if (!Modifier.isStatic(method.getModifiers()) || method.getReturnType() != enumClass || method.getParameterTypes().length != 1 || (jsonCreator = method.getAnnotation(JSONCreator.class)) == null) continue;
            jsonCreatorMethod = method;
            break;
        }
        return jsonCreatorMethod;
    }

    protected ObjectDeserializer getEnumDeserializer(Class<?> clazz) {
        return new EnumDeserializer(clazz);
    }

    public void initJavaBeanDeserializers(Class<?> ... classes) {
        if (classes == null) {
            return;
        }
        for (Class<?> type : classes) {
            if (type == null) continue;
            ObjectDeserializer deserializer = this.createJavaBeanDeserializer(type, type);
            this.putDeserializer(type, deserializer);
        }
    }

    public ObjectDeserializer createJavaBeanDeserializer(Class<?> clazz, Type type) {
        JavaBeanInfo beanInfo;
        boolean asmEnable = this.asmEnable & !this.fieldBased;
        if (asmEnable) {
            JSONType jsonType = TypeUtils.getAnnotation(clazz, JSONType.class);
            if (jsonType != null) {
                Class<?> deserializerClass = jsonType.deserializer();
                if (deserializerClass != Void.class) {
                    try {
                        Object deseralizer = deserializerClass.newInstance();
                        if (deseralizer instanceof ObjectDeserializer) {
                            return (ObjectDeserializer)deseralizer;
                        }
                    } catch (Throwable throwable) {
                        // empty catch block
                    }
                }
                boolean bl = asmEnable = jsonType.asm() && jsonType.parseFeatures().length == 0;
            }
            if (asmEnable) {
                Class<?> superClass = JavaBeanInfo.getBuilderClass(clazz, jsonType);
                if (superClass == null) {
                    superClass = clazz;
                }
                do {
                    if (Modifier.isPublic(superClass.getModifiers())) continue;
                    asmEnable = false;
                    break;
                } while ((superClass = superClass.getSuperclass()) != Object.class && superClass != null);
            }
        }
        if (clazz.getTypeParameters().length != 0) {
            asmEnable = false;
        }
        if (asmEnable && this.asmFactory != null && this.asmFactory.classLoader.isExternalClass(clazz)) {
            asmEnable = false;
        }
        if (asmEnable) {
            asmEnable = ASMUtils.checkName(clazz.getSimpleName());
        }
        if (asmEnable) {
            if (clazz.isInterface()) {
                asmEnable = false;
            }
            beanInfo = JavaBeanInfo.build(clazz, type, this.propertyNamingStrategy, false, TypeUtils.compatibleWithJavaBean, this.jacksonCompatible);
            if (asmEnable && beanInfo.fields.length > 200) {
                asmEnable = false;
            }
            Constructor<?> defaultConstructor = beanInfo.defaultConstructor;
            if (asmEnable && defaultConstructor == null && !clazz.isInterface()) {
                asmEnable = false;
            }
            for (FieldInfo fieldInfo : beanInfo.fields) {
                ObjectDeserializer fieldDeser;
                if (fieldInfo.getOnly) {
                    asmEnable = false;
                    break;
                }
                Class<?> fieldClass = fieldInfo.fieldClass;
                if (!Modifier.isPublic(fieldClass.getModifiers())) {
                    asmEnable = false;
                    break;
                }
                if (fieldClass.isMemberClass() && !Modifier.isStatic(fieldClass.getModifiers())) {
                    asmEnable = false;
                    break;
                }
                if (fieldInfo.getMember() != null && !ASMUtils.checkName(fieldInfo.getMember().getName())) {
                    asmEnable = false;
                    break;
                }
                JSONField annotation = fieldInfo.getAnnotation();
                if (annotation != null && (!ASMUtils.checkName(annotation.name()) || annotation.format().length() != 0 || annotation.deserializeUsing() != Void.class || annotation.parseFeatures().length != 0 || annotation.unwrapped()) || fieldInfo.method != null && fieldInfo.method.getParameterTypes().length > 1) {
                    asmEnable = false;
                    break;
                }
                if (!fieldClass.isEnum() || (fieldDeser = this.getDeserializer(fieldClass)) instanceof EnumDeserializer) continue;
                asmEnable = false;
                break;
            }
        }
        if (asmEnable && clazz.isMemberClass() && !Modifier.isStatic(clazz.getModifiers())) {
            asmEnable = false;
        }
        if (asmEnable && TypeUtils.isXmlField(clazz)) {
            asmEnable = false;
        }
        if (!asmEnable) {
            return new JavaBeanDeserializer(this, clazz, type);
        }
        beanInfo = JavaBeanInfo.build(clazz, type, this.propertyNamingStrategy);
        try {
            return this.asmFactory.createJavaBeanDeserializer(this, beanInfo);
        } catch (NoSuchMethodException ex) {
            return new JavaBeanDeserializer(this, clazz, type);
        } catch (JSONException asmError) {
            return new JavaBeanDeserializer(this, beanInfo);
        } catch (Exception e) {
            throw new JSONException("create asm deserializer error, " + clazz.getName(), e);
        }
    }

    public FieldDeserializer createFieldDeserializer(ParserConfig mapping, JavaBeanInfo beanInfo, FieldInfo fieldInfo) {
        Class<?> clazz = beanInfo.clazz;
        Class<?> fieldClass = fieldInfo.fieldClass;
        Class<?> deserializeUsing = null;
        JSONField annotation = fieldInfo.getAnnotation();
        if (annotation != null && (deserializeUsing = annotation.deserializeUsing()) == Void.class) {
            deserializeUsing = null;
        }
        if (deserializeUsing == null && (fieldClass == List.class || fieldClass == ArrayList.class)) {
            return new ArrayListTypeFieldDeserializer(mapping, clazz, fieldInfo);
        }
        return new DefaultFieldDeserializer(mapping, clazz, fieldInfo);
    }

    public void putDeserializer(Type type, ObjectDeserializer deserializer) {
        Type mixin = JSON.getMixInAnnotations(type);
        if (mixin != null) {
            IdentityHashMap<Type, ObjectDeserializer> mixInClasses = this.mixInDeserializers.get(type);
            if (mixInClasses == null) {
                mixInClasses = new IdentityHashMap(4);
                this.mixInDeserializers.put(type, mixInClasses);
            }
            mixInClasses.put(mixin, deserializer);
        } else {
            this.deserializers.put(type, deserializer);
        }
    }

    public ObjectDeserializer get(Type type) {
        Type mixin = JSON.getMixInAnnotations(type);
        if (null == mixin) {
            return this.deserializers.get(type);
        }
        IdentityHashMap<Type, ObjectDeserializer> mixInClasses = this.mixInDeserializers.get(type);
        if (mixInClasses == null) {
            return null;
        }
        return mixInClasses.get(mixin);
    }

    public ObjectDeserializer getDeserializer(FieldInfo fieldInfo) {
        return this.getDeserializer(fieldInfo.fieldClass, fieldInfo.fieldType);
    }

    public boolean isPrimitive(Class<?> clazz) {
        return ParserConfig.isPrimitive2(clazz);
    }

    public static boolean isPrimitive2(Class<?> clazz) {
        Boolean primitive = clazz.isPrimitive() || clazz == Boolean.class || clazz == Character.class || clazz == Byte.class || clazz == Short.class || clazz == Integer.class || clazz == Long.class || clazz == Float.class || clazz == Double.class || clazz == BigInteger.class || clazz == BigDecimal.class || clazz == String.class || clazz == Date.class || clazz.isEnum();
        if (!primitive.booleanValue()) {
            primitive = ModuleUtil.callWhenHasJavaSql(isPrimitiveFuncation, clazz);
        }
        return primitive != null ? primitive : false;
    }

    public static void parserAllFieldToCache(Class<?> clazz, Map<String, Field> fieldCacheMap) {
        Field[] fields;
        for (Field field : fields = clazz.getDeclaredFields()) {
            String fieldName = field.getName();
            if (fieldCacheMap.containsKey(fieldName)) continue;
            fieldCacheMap.put(fieldName, field);
        }
        if (clazz.getSuperclass() != null && clazz.getSuperclass() != Object.class) {
            ParserConfig.parserAllFieldToCache(clazz.getSuperclass(), fieldCacheMap);
        }
    }

    public static Field getFieldFromCache(String fieldName, Map<String, Field> fieldCacheMap) {
        Field field = fieldCacheMap.get(fieldName);
        if (field == null) {
            field = fieldCacheMap.get("_" + fieldName);
        }
        if (field == null) {
            field = fieldCacheMap.get("m_" + fieldName);
        }
        if (field == null) {
            char c0 = fieldName.charAt(0);
            if (c0 >= 'a' && c0 <= 'z') {
                char[] chars = fieldName.toCharArray();
                chars[0] = (char)(chars[0] - 32);
                String fieldNameX = new String(chars);
                field = fieldCacheMap.get(fieldNameX);
            }
            if (fieldName.length() > 2) {
                char c1 = fieldName.charAt(1);
                if (c0 >= 'a' && c0 <= 'z' && c1 >= 'A' && c1 <= 'Z') {
                    for (Map.Entry<String, Field> entry : fieldCacheMap.entrySet()) {
                        if (!fieldName.equalsIgnoreCase(entry.getKey())) continue;
                        field = entry.getValue();
                        break;
                    }
                }
            }
        }
        return field;
    }

    public ClassLoader getDefaultClassLoader() {
        return this.defaultClassLoader;
    }

    public void setDefaultClassLoader(ClassLoader defaultClassLoader) {
        this.defaultClassLoader = defaultClassLoader;
    }

    public void addDenyInternal(String name) {
        if (name == null || name.length() == 0) {
            return;
        }
        long hash = TypeUtils.fnv1a_64(name);
        if (this.internalDenyHashCodes == null) {
            this.internalDenyHashCodes = new long[]{hash};
            return;
        }
        if (Arrays.binarySearch(this.internalDenyHashCodes, hash) >= 0) {
            return;
        }
        long[] hashCodes = new long[this.internalDenyHashCodes.length + 1];
        hashCodes[hashCodes.length - 1] = hash;
        System.arraycopy(this.internalDenyHashCodes, 0, hashCodes, 0, this.internalDenyHashCodes.length);
        Arrays.sort(hashCodes);
        this.internalDenyHashCodes = hashCodes;
    }

    public void addDeny(String name) {
        if (name == null || name.length() == 0) {
            return;
        }
        long hash = TypeUtils.fnv1a_64(name);
        if (Arrays.binarySearch(this.denyHashCodes, hash) >= 0) {
            return;
        }
        long[] hashCodes = new long[this.denyHashCodes.length + 1];
        hashCodes[hashCodes.length - 1] = hash;
        System.arraycopy(this.denyHashCodes, 0, hashCodes, 0, this.denyHashCodes.length);
        Arrays.sort(hashCodes);
        this.denyHashCodes = hashCodes;
    }

    public void addAccept(String name) {
        if (name == null || name.length() == 0) {
            return;
        }
        long hash = TypeUtils.fnv1a_64(name);
        if (Arrays.binarySearch(this.acceptHashCodes, hash) >= 0) {
            return;
        }
        long[] hashCodes = new long[this.acceptHashCodes.length + 1];
        hashCodes[hashCodes.length - 1] = hash;
        System.arraycopy(this.acceptHashCodes, 0, hashCodes, 0, this.acceptHashCodes.length);
        Arrays.sort(hashCodes);
        this.acceptHashCodes = hashCodes;
    }

    public Class<?> checkAutoType(Class type) {
        if (this.get(type) != null) {
            return type;
        }
        return this.checkAutoType(type.getName(), null, JSON.DEFAULT_PARSER_FEATURE);
    }

    public Class<?> checkAutoType(String typeName, Class<?> expectClass) {
        return this.checkAutoType(typeName, expectClass, JSON.DEFAULT_PARSER_FEATURE);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Loose catch block
     */
    public Class<?> checkAutoType(String typeName, Class<?> expectClass, int features) {
        InputStream is;
        boolean jsonType;
        Class<?> clazz;
        boolean expectClassFlag;
        boolean autoTypeSupport;
        block40: {
            int i;
            long hash;
            boolean internalWhite;
            long expectHash;
            boolean safeMode;
            if (typeName == null) {
                return null;
            }
            if (this.autoTypeCheckHandlers != null) {
                for (AutoTypeCheckHandler h2 : this.autoTypeCheckHandlers) {
                    Class<?> type = h2.handler(typeName, expectClass, features);
                    if (type == null) continue;
                    return type;
                }
            }
            int safeModeMask = Feature.SafeMode.mask;
            boolean bl = safeMode = this.safeMode || (features & safeModeMask) != 0 || (JSON.DEFAULT_PARSER_FEATURE & safeModeMask) != 0;
            if (safeMode) {
                throw new JSONException("safeMode not support autoType : " + typeName);
            }
            int mask = Feature.SupportAutoType.mask;
            boolean bl2 = autoTypeSupport = this.autoTypeSupport || (features & mask) != 0 || (JSON.DEFAULT_PARSER_FEATURE & mask) != 0;
            if (typeName.length() >= 192 || typeName.length() < 3) {
                throw new JSONException("autoType is not support. " + typeName);
            }
            expectClassFlag = expectClass == null ? false : (expectHash = TypeUtils.fnv1a_64(expectClass.getName())) != -8024746738719829346L && expectHash != 3247277300971823414L && expectHash != -5811778396720452501L && expectHash != -1368967840069965882L && expectHash != 2980334044947851925L && expectHash != 5183404141909004468L && expectHash != 7222019943667248779L && expectHash != -2027296626235911549L && expectHash != -2114196234051346931L && expectHash != -2939497380989775398L;
            String className = typeName.replace('$', '.');
            long h1 = (0xCBF29CE484222325L ^ (long)className.charAt(0)) * 1099511628211L;
            if (h1 == -5808493101479473382L) {
                throw new JSONException("autoType is not support. " + typeName);
            }
            if ((h1 ^ (long)className.charAt(className.length() - 1)) * 1099511628211L == 655701488918567152L) {
                throw new JSONException("autoType is not support. " + typeName);
            }
            long h3 = (((0xCBF29CE484222325L ^ (long)className.charAt(0)) * 1099511628211L ^ (long)className.charAt(1)) * 1099511628211L ^ (long)className.charAt(2)) * 1099511628211L;
            long fullHash = TypeUtils.fnv1a_64(className);
            boolean bl3 = internalWhite = Arrays.binarySearch(INTERNAL_WHITELIST_HASHCODES, fullHash) >= 0;
            if (this.internalDenyHashCodes != null) {
                hash = h3;
                for (i = 3; i < className.length(); ++i) {
                    hash ^= (long)className.charAt(i);
                    if (Arrays.binarySearch(this.internalDenyHashCodes, hash *= 1099511628211L) < 0) continue;
                    throw new JSONException("autoType is not support. " + typeName);
                }
            }
            if (!internalWhite && (autoTypeSupport || expectClassFlag)) {
                hash = h3;
                for (i = 3; i < className.length(); ++i) {
                    hash ^= (long)className.charAt(i);
                    if (Arrays.binarySearch(this.acceptHashCodes, hash *= 1099511628211L) >= 0 && (clazz = TypeUtils.loadClass(typeName, this.defaultClassLoader, true)) != null) {
                        return clazz;
                    }
                    if (Arrays.binarySearch(this.denyHashCodes, hash) < 0 || TypeUtils.getClassFromMapping(typeName) != null || Arrays.binarySearch(this.acceptHashCodes, fullHash) >= 0) continue;
                    throw new JSONException("autoType is not support. " + typeName);
                }
            }
            if ((clazz = TypeUtils.getClassFromMapping(typeName)) == null) {
                clazz = this.deserializers.findClass(typeName);
            }
            if (expectClass == null && clazz != null && Throwable.class.isAssignableFrom(clazz) && !autoTypeSupport) {
                clazz = null;
            }
            if (clazz == null) {
                clazz = (Class)this.typeMapping.get(typeName);
            }
            if (internalWhite) {
                clazz = TypeUtils.loadClass(typeName, this.defaultClassLoader, true);
            }
            if (clazz != null) {
                if (expectClass != null && clazz != HashMap.class && clazz != LinkedHashMap.class && !expectClass.isAssignableFrom(clazz)) {
                    throw new JSONException("type not match. " + typeName + " -> " + expectClass.getName());
                }
                return clazz;
            }
            if (!autoTypeSupport) {
                hash = h3;
                for (i = 3; i < className.length(); ++i) {
                    char c = className.charAt(i);
                    hash ^= (long)c;
                    if (Arrays.binarySearch(this.denyHashCodes, hash *= 1099511628211L) >= 0) {
                        if (typeName.endsWith("Exception") || typeName.endsWith("Error")) {
                            return null;
                        }
                        throw new JSONException("autoType is not support. " + typeName);
                    }
                    if (Arrays.binarySearch(this.acceptHashCodes, hash) < 0) continue;
                    clazz = TypeUtils.loadClass(typeName, this.defaultClassLoader, true);
                    if (clazz == null) {
                        return expectClass;
                    }
                    if (expectClass != null && expectClass.isAssignableFrom(clazz)) {
                        throw new JSONException("type not match. " + typeName + " -> " + expectClass.getName());
                    }
                    return clazz;
                }
            }
            jsonType = false;
            is = null;
            try {
                String resource = typeName.replace('.', '/') + ".class";
                is = this.defaultClassLoader != null ? this.defaultClassLoader.getResourceAsStream(resource) : ParserConfig.class.getClassLoader().getResourceAsStream(resource);
                if (is == null) break block40;
                ClassReader classReader = new ClassReader(is, true);
                TypeCollector visitor = new TypeCollector("<clinit>", new Class[0]);
                classReader.accept(visitor);
                jsonType = visitor.hasJsonType();
            } catch (Exception resource) {
                IOUtils.close(is);
                catch (Throwable throwable) {
                    IOUtils.close(is);
                    throw throwable;
                }
            }
        }
        IOUtils.close(is);
        if (autoTypeSupport || jsonType || expectClassFlag) {
            boolean cacheClass = autoTypeSupport || jsonType;
            clazz = TypeUtils.loadClass(typeName, this.defaultClassLoader, cacheClass);
        }
        if (clazz != null) {
            if (jsonType) {
                if (autoTypeSupport) {
                    TypeUtils.addMapping(typeName, clazz);
                }
                return clazz;
            }
            if (ClassLoader.class.isAssignableFrom(clazz) || DataSource.class.isAssignableFrom(clazz) || RowSet.class.isAssignableFrom(clazz)) {
                throw new JSONException("autoType is not support. " + typeName);
            }
            if (expectClass != null) {
                if (expectClass.isAssignableFrom(clazz)) {
                    if (autoTypeSupport) {
                        TypeUtils.addMapping(typeName, clazz);
                    }
                    return clazz;
                }
                throw new JSONException("type not match. " + typeName + " -> " + expectClass.getName());
            }
            JavaBeanInfo beanInfo = JavaBeanInfo.build(clazz, clazz, this.propertyNamingStrategy);
            if (beanInfo.creatorConstructor != null && autoTypeSupport) {
                throw new JSONException("autoType is not support. " + typeName);
            }
        }
        if (!autoTypeSupport) {
            if (typeName.endsWith("Exception") || typeName.endsWith("Error")) {
                return null;
            }
            throw new JSONException("autoType is not support. " + typeName);
        }
        if (clazz != null && autoTypeSupport) {
            TypeUtils.addMapping(typeName, clazz);
        }
        return clazz;
    }

    public void clearDeserializers() {
        this.deserializers.clear();
        this.initDeserializers();
    }

    public boolean isJacksonCompatible() {
        return this.jacksonCompatible;
    }

    public void setJacksonCompatible(boolean jacksonCompatible) {
        this.jacksonCompatible = jacksonCompatible;
    }

    public void register(String typeName, Class type) {
        this.typeMapping.putIfAbsent(typeName, type);
    }

    public void register(Module module) {
        this.modules.add(module);
    }

    public void addAutoTypeCheckHandler(AutoTypeCheckHandler h2) {
        List<AutoTypeCheckHandler> autoTypeCheckHandlers = this.autoTypeCheckHandlers;
        if (autoTypeCheckHandlers == null) {
            this.autoTypeCheckHandlers = autoTypeCheckHandlers = new CopyOnWriteArrayList<AutoTypeCheckHandler>();
        }
        autoTypeCheckHandlers.add(h2);
    }

    static {
        String property = IOUtils.getStringProperty(DENY_PROPERTY_INTERNAL);
        DENYS_INTERNAL = ParserConfig.splitItemsFormProperty(property);
        property = IOUtils.getStringProperty(DENY_PROPERTY);
        DENYS = ParserConfig.splitItemsFormProperty(property);
        property = IOUtils.getStringProperty(AUTOTYPE_SUPPORT_PROPERTY);
        AUTO_SUPPORT = "true".equals(property);
        property = IOUtils.getStringProperty(SAFE_MODE_PROPERTY);
        SAFE_MODE = "true".equals(property);
        property = IOUtils.getStringProperty(AUTOTYPE_ACCEPT);
        String[] items = ParserConfig.splitItemsFormProperty(property);
        if (items == null) {
            items = new String[]{};
        }
        AUTO_TYPE_ACCEPT_LIST = items;
        INTERNAL_WHITELIST_HASHCODES = new long[]{-6976602508726000783L, -6293031534589903644L, 59775428743665658L, 7267793227937552092L};
        global = new ParserConfig();
        awtError = false;
        jdk8Error = false;
        jodaError = false;
        guavaError = false;
        isPrimitiveFuncation = new Function<Class<?>, Boolean>(){

            @Override
            public Boolean apply(Class<?> clazz) {
                return clazz == java.sql.Date.class || clazz == Time.class || clazz == Timestamp.class;
            }
        };
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static interface AutoTypeCheckHandler {
        public Class<?> handler(String var1, Class<?> var2, int var3);
    }
}

