/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.JSONScanner;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.parser.deserializer.EnumDeserializer;
import com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.serializer.CalendarCodec;
import com.alibaba.fastjson.serializer.SerializeBeanInfo;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.util.BiFunction;
import com.alibaba.fastjson.util.FieldInfo;
import com.alibaba.fastjson.util.Function;
import com.alibaba.fastjson.util.GenericArrayTypeImpl;
import com.alibaba.fastjson.util.IOUtils;
import com.alibaba.fastjson.util.ModuleUtil;
import com.alibaba.fastjson.util.ParameterizedTypeImpl;
import java.io.InputStream;
import java.io.Reader;
import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Clob;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.AbstractCollection;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Currency;
import java.util.Date;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.UUID;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class TypeUtils {
    private static final Pattern NUMBER_WITH_TRAILING_ZEROS_PATTERN = Pattern.compile("\\.0*$");
    public static boolean compatibleWithJavaBean = false;
    public static boolean compatibleWithFieldName = false;
    private static boolean setAccessibleEnable = true;
    private static boolean oracleTimestampMethodInited = false;
    private static Method oracleTimestampMethod;
    private static boolean oracleDateMethodInited;
    private static Method oracleDateMethod;
    private static boolean optionalClassInited;
    private static Class<?> optionalClass;
    private static boolean transientClassInited;
    private static Class<? extends Annotation> transientClass;
    private static Class<? extends Annotation> class_OneToMany;
    private static boolean class_OneToMany_error;
    private static Class<? extends Annotation> class_ManyToMany;
    private static boolean class_ManyToMany_error;
    private static Method method_HibernateIsInitialized;
    private static boolean method_HibernateIsInitialized_error;
    private static volatile Class kotlin_metadata;
    private static volatile boolean kotlin_metadata_error;
    private static volatile boolean kotlin_class_klass_error;
    private static volatile Constructor kotlin_kclass_constructor;
    private static volatile Method kotlin_kclass_getConstructors;
    private static volatile Method kotlin_kfunction_getParameters;
    private static volatile Method kotlin_kparameter_getName;
    private static volatile boolean kotlin_error;
    private static volatile Map<Class, String[]> kotlinIgnores;
    private static volatile boolean kotlinIgnores_error;
    private static ConcurrentMap<String, Class<?>> mappings;
    private static Class<?> pathClass;
    private static boolean pathClass_error;
    private static Class<? extends Annotation> class_JacksonCreator;
    private static boolean class_JacksonCreator_error;
    private static volatile Class class_XmlAccessType;
    private static volatile Class class_XmlAccessorType;
    private static volatile boolean classXmlAccessorType_error;
    private static volatile Method method_XmlAccessorType_value;
    private static volatile Field field_XmlAccessType_FIELD;
    private static volatile Object field_XmlAccessType_FIELD_VALUE;
    private static Class class_deque;
    private static Function<Class, Boolean> isClobFunction;
    private static Function<Object, Object> castToSqlDateFunction;
    private static Function<Object, Object> castToSqlTimeFunction;
    public static Function<Object, Object> castToTimestampFunction;
    private static BiFunction<Object, Class, Object> castFunction;
    private static Function<Map<String, Class<?>>, Void> addBaseClassMappingsFunction;
    private static final Map primitiveTypeMap;
    private static final Set<String> isProxyClassNames;
    public static final long fnv1a_64_magic_hashcode = -3750763034362895579L;
    public static final long fnv1a_64_magic_prime = 1099511628211L;
    private static Object OPTIONAL_EMPTY;
    private static boolean OPTIONAL_ERROR;

    public static boolean isXmlField(Class clazz) {
        if (class_XmlAccessorType == null && !classXmlAccessorType_error) {
            try {
                class_XmlAccessorType = Class.forName("javax.xml.bind.annotation.XmlAccessorType");
            } catch (Throwable ex) {
                classXmlAccessorType_error = true;
            }
        }
        if (class_XmlAccessorType == null) {
            return false;
        }
        Object annotation = TypeUtils.getAnnotation(clazz, class_XmlAccessorType);
        if (annotation == null) {
            return false;
        }
        if (method_XmlAccessorType_value == null && !classXmlAccessorType_error) {
            try {
                method_XmlAccessorType_value = class_XmlAccessorType.getMethod("value", new Class[0]);
            } catch (Throwable ex) {
                classXmlAccessorType_error = true;
            }
        }
        if (method_XmlAccessorType_value == null) {
            return false;
        }
        Object value = null;
        if (!classXmlAccessorType_error) {
            try {
                value = method_XmlAccessorType_value.invoke(annotation, new Object[0]);
            } catch (Throwable ex) {
                classXmlAccessorType_error = true;
            }
        }
        if (value == null) {
            return false;
        }
        if (class_XmlAccessType == null && !classXmlAccessorType_error) {
            try {
                class_XmlAccessType = Class.forName("javax.xml.bind.annotation.XmlAccessType");
                field_XmlAccessType_FIELD = class_XmlAccessType.getField("FIELD");
                field_XmlAccessType_FIELD_VALUE = field_XmlAccessType_FIELD.get(null);
            } catch (Throwable ex) {
                classXmlAccessorType_error = true;
            }
        }
        return value == field_XmlAccessType_FIELD_VALUE;
    }

    public static Annotation getXmlAccessorType(Class clazz) {
        if (class_XmlAccessorType == null && !classXmlAccessorType_error) {
            try {
                class_XmlAccessorType = Class.forName("javax.xml.bind.annotation.XmlAccessorType");
            } catch (Throwable ex) {
                classXmlAccessorType_error = true;
            }
        }
        if (class_XmlAccessorType == null) {
            return null;
        }
        return TypeUtils.getAnnotation(clazz, class_XmlAccessorType);
    }

    public static boolean isClob(Class clazz) {
        Boolean isClob = ModuleUtil.callWhenHasJavaSql(isClobFunction, clazz);
        return isClob != null ? isClob : false;
    }

    public static String castToString(Object value) {
        if (value == null) {
            return null;
        }
        return value.toString();
    }

    public static Byte castToByte(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof BigDecimal) {
            return TypeUtils.byteValue((BigDecimal)value);
        }
        if (value instanceof Number) {
            return ((Number)value).byteValue();
        }
        if (value instanceof String) {
            String strVal = (String)value;
            if (strVal.length() == 0 || "null".equals(strVal) || "NULL".equals(strVal)) {
                return null;
            }
            return Byte.parseByte(strVal);
        }
        if (value instanceof Boolean) {
            return (Boolean)value != false ? (byte)1 : 0;
        }
        throw new JSONException("can not cast to byte, value : " + value);
    }

    public static Character castToChar(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof Character) {
            return (Character)value;
        }
        if (value instanceof String) {
            String strVal = (String)value;
            if (strVal.length() == 0) {
                return null;
            }
            if (strVal.length() != 1) {
                throw new JSONException("can not cast to char, value : " + value);
            }
            return Character.valueOf(strVal.charAt(0));
        }
        throw new JSONException("can not cast to char, value : " + value);
    }

    public static Short castToShort(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof BigDecimal) {
            return TypeUtils.shortValue((BigDecimal)value);
        }
        if (value instanceof Number) {
            return ((Number)value).shortValue();
        }
        if (value instanceof String) {
            String strVal = (String)value;
            if (strVal.length() == 0 || "null".equals(strVal) || "NULL".equals(strVal)) {
                return null;
            }
            return Short.parseShort(strVal);
        }
        if (value instanceof Boolean) {
            return (Boolean)value != false ? (short)1 : 0;
        }
        throw new JSONException("can not cast to short, value : " + value);
    }

    public static BigDecimal castToBigDecimal(Object value) {
        String strVal;
        if (value == null) {
            return null;
        }
        if (value instanceof Float) {
            if (Float.isNaN(((Float)value).floatValue()) || Float.isInfinite(((Float)value).floatValue())) {
                return null;
            }
        } else if (value instanceof Double) {
            if (Double.isNaN((Double)value) || Double.isInfinite((Double)value)) {
                return null;
            }
        } else {
            if (value instanceof BigDecimal) {
                return (BigDecimal)value;
            }
            if (value instanceof BigInteger) {
                return new BigDecimal((BigInteger)value);
            }
            if (value instanceof Map && ((Map)value).size() == 0) {
                return null;
            }
        }
        if ((strVal = value.toString()).length() == 0 || strVal.equalsIgnoreCase("null")) {
            return null;
        }
        if (strVal.length() > 65535) {
            throw new JSONException("decimal overflow");
        }
        return new BigDecimal(strVal);
    }

    public static BigInteger castToBigInteger(Object value) {
        BigDecimal decimal;
        int scale;
        if (value == null) {
            return null;
        }
        if (value instanceof Float) {
            Float floatValue = (Float)value;
            if (Float.isNaN(floatValue.floatValue()) || Float.isInfinite(floatValue.floatValue())) {
                return null;
            }
            return BigInteger.valueOf(floatValue.longValue());
        }
        if (value instanceof Double) {
            Double doubleValue = (Double)value;
            if (Double.isNaN(doubleValue) || Double.isInfinite(doubleValue)) {
                return null;
            }
            return BigInteger.valueOf(doubleValue.longValue());
        }
        if (value instanceof BigInteger) {
            return (BigInteger)value;
        }
        if (value instanceof BigDecimal && (scale = (decimal = (BigDecimal)value).scale()) > -1000 && scale < 1000) {
            return ((BigDecimal)value).toBigInteger();
        }
        String strVal = value.toString();
        if (strVal.length() == 0 || strVal.equalsIgnoreCase("null")) {
            return null;
        }
        if (strVal.length() > 65535) {
            throw new JSONException("decimal overflow");
        }
        return new BigInteger(strVal);
    }

    public static Float castToFloat(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof Number) {
            return Float.valueOf(((Number)value).floatValue());
        }
        if (value instanceof String) {
            String strVal = value.toString();
            if (strVal.length() == 0 || "null".equals(strVal) || "NULL".equals(strVal)) {
                return null;
            }
            if (strVal.indexOf(44) != -1) {
                strVal = strVal.replaceAll(",", "");
            }
            return Float.valueOf(Float.parseFloat(strVal));
        }
        if (value instanceof Boolean) {
            return Float.valueOf((Boolean)value != false ? 1.0f : 0.0f);
        }
        throw new JSONException("can not cast to float, value : " + value);
    }

    public static Double castToDouble(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof Number) {
            return ((Number)value).doubleValue();
        }
        if (value instanceof String) {
            String strVal = value.toString();
            if (strVal.length() == 0 || "null".equals(strVal) || "NULL".equals(strVal)) {
                return null;
            }
            if (strVal.indexOf(44) != -1) {
                strVal = strVal.replaceAll(",", "");
            }
            return Double.parseDouble(strVal);
        }
        if (value instanceof Boolean) {
            return (Boolean)value != false ? 1.0 : 0.0;
        }
        throw new JSONException("can not cast to double, value : " + value);
    }

    public static Date castToDate(Object value) {
        return TypeUtils.castToDate(value, null);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static Date castToDate(Object value, String format) {
        if (value == null) {
            return null;
        }
        if (value instanceof Date) {
            return (Date)value;
        }
        if (value instanceof Calendar) {
            return ((Calendar)value).getTime();
        }
        long longValue = -1L;
        if (value instanceof BigDecimal) {
            longValue = TypeUtils.longValue((BigDecimal)value);
            return new Date(longValue);
        }
        if (value instanceof Number) {
            longValue = ((Number)value).longValue();
            if ("unixtime".equals(format)) {
                longValue *= 1000L;
            }
            return new Date(longValue);
        }
        if (value instanceof String) {
            String strVal = (String)value;
            JSONScanner dateLexer = new JSONScanner(strVal);
            try {
                if (dateLexer.scanISO8601DateIfMatch(false)) {
                    Calendar calendar = dateLexer.getCalendar();
                    Date date = calendar.getTime();
                    return date;
                }
            } finally {
                dateLexer.close();
            }
            if (strVal.startsWith("/Date(") && strVal.endsWith(")/")) {
                strVal = strVal.substring(6, strVal.length() - 2);
            }
            if (strVal.indexOf(45) > 0 || strVal.indexOf(43) > 0 || format != null) {
                if (format == null) {
                    int len = strVal.length();
                    format = len == JSON.DEFFAULT_DATE_FORMAT.length() || len == 22 && JSON.DEFFAULT_DATE_FORMAT.equals("yyyyMMddHHmmssSSSZ") ? JSON.DEFFAULT_DATE_FORMAT : (len == 10 ? "yyyy-MM-dd" : (len == "yyyy-MM-dd HH:mm:ss".length() ? "yyyy-MM-dd HH:mm:ss" : (len == 29 && strVal.charAt(26) == ':' && strVal.charAt(28) == '0' ? "yyyy-MM-dd'T'HH:mm:ss.SSSXXX" : (len == 23 && strVal.charAt(19) == ',' ? "yyyy-MM-dd HH:mm:ss,SSS" : "yyyy-MM-dd HH:mm:ss.SSS"))));
                }
                SimpleDateFormat dateFormat = new SimpleDateFormat(format, JSON.defaultLocale);
                dateFormat.setTimeZone(JSON.defaultTimeZone);
                try {
                    return dateFormat.parse(strVal);
                } catch (ParseException e) {
                    throw new JSONException("can not cast to Date, value : " + strVal);
                }
            }
            if (strVal.length() == 0) {
                return null;
            }
            longValue = Long.parseLong(strVal);
        }
        if (longValue == -1L) {
            Object result;
            Class<?> clazz = value.getClass();
            if ("oracle.sql.TIMESTAMP".equals(clazz.getName())) {
                if (oracleTimestampMethod == null && !oracleTimestampMethodInited) {
                    try {
                        oracleTimestampMethod = clazz.getMethod("toJdbc", new Class[0]);
                    } catch (NoSuchMethodException dateLexer) {
                    } finally {
                        oracleTimestampMethodInited = true;
                    }
                }
                try {
                    result = oracleTimestampMethod.invoke(value, new Object[0]);
                } catch (Exception e) {
                    throw new JSONException("can not cast oracle.sql.TIMESTAMP to Date", e);
                }
                return (Date)result;
            }
            if ("oracle.sql.DATE".equals(clazz.getName())) {
                if (oracleDateMethod == null && !oracleDateMethodInited) {
                    try {
                        oracleDateMethod = clazz.getMethod("toJdbc", new Class[0]);
                    } catch (NoSuchMethodException result2) {
                    } finally {
                        oracleDateMethodInited = true;
                    }
                }
                try {
                    result = oracleDateMethod.invoke(value, new Object[0]);
                } catch (Exception e) {
                    throw new JSONException("can not cast oracle.sql.DATE to Date", e);
                }
                return (Date)result;
            }
            throw new JSONException("can not cast to Date, value : " + value);
        }
        return new Date(longValue);
    }

    public static Object castToSqlDate(Object value) {
        return ModuleUtil.callWhenHasJavaSql(castToSqlDateFunction, value);
    }

    public static long longExtractValue(Number number) {
        if (number instanceof BigDecimal) {
            return ((BigDecimal)number).longValueExact();
        }
        return number.longValue();
    }

    public static Object castToSqlTime(Object value) {
        return ModuleUtil.callWhenHasJavaSql(castToSqlTimeFunction, value);
    }

    public static Object castToTimestamp(Object value) {
        return ModuleUtil.callWhenHasJavaSql(castToTimestampFunction, value);
    }

    static int num(char c0, char c1) {
        if (c0 >= '0' && c0 <= '9' && c1 >= '0' && c1 <= '9') {
            return (c0 - 48) * 10 + (c1 - 48);
        }
        return -1;
    }

    static int num(char c0, char c1, char c2, char c3) {
        if (c0 >= '0' && c0 <= '9' && c1 >= '0' && c1 <= '9' && c2 >= '0' && c2 <= '9' && c3 >= '0' && c3 <= '9') {
            return (c0 - 48) * 1000 + (c1 - 48) * 100 + (c2 - 48) * 10 + (c3 - 48);
        }
        return -1;
    }

    static int num(char c0, char c1, char c2, char c3, char c4, char c5, char c6, char c7, char c8) {
        if (c0 >= '0' && c0 <= '9' && c1 >= '0' && c1 <= '9' && c2 >= '0' && c2 <= '9' && c3 >= '0' && c3 <= '9' && c4 >= '0' && c4 <= '9' && c5 >= '0' && c5 <= '9' && c6 >= '0' && c6 <= '9' && c7 >= '0' && c7 <= '9' && c8 >= '0' && c8 <= '9') {
            return (c0 - 48) * 100000000 + (c1 - 48) * 10000000 + (c2 - 48) * 1000000 + (c3 - 48) * 100000 + (c4 - 48) * 10000 + (c5 - 48) * 1000 + (c6 - 48) * 100 + (c7 - 48) * 10 + (c8 - 48);
        }
        return -1;
    }

    public static boolean isNumber(String str) {
        for (int i = 0; i < str.length(); ++i) {
            char ch = str.charAt(i);
            if (!(ch == '+' || ch == '-' ? i != 0 : ch < '0' || ch > '9')) continue;
            return false;
        }
        return true;
    }

    public static Long castToLong(Object value) {
        Map map;
        block11: {
            if (value == null) {
                return null;
            }
            if (value instanceof BigDecimal) {
                return TypeUtils.longValue((BigDecimal)value);
            }
            if (value instanceof Number) {
                return ((Number)value).longValue();
            }
            if (value instanceof String) {
                String strVal = (String)value;
                if (strVal.length() == 0 || "null".equals(strVal) || "NULL".equals(strVal)) {
                    return null;
                }
                if (strVal.indexOf(44) != -1) {
                    strVal = strVal.replaceAll(",", "");
                }
                try {
                    return Long.parseLong(strVal);
                } catch (NumberFormatException numberFormatException) {
                    JSONScanner dateParser = new JSONScanner(strVal);
                    Calendar calendar = null;
                    if (dateParser.scanISO8601DateIfMatch(false)) {
                        calendar = dateParser.getCalendar();
                    }
                    dateParser.close();
                    if (calendar == null) break block11;
                    return calendar.getTimeInMillis();
                }
            }
        }
        if (value instanceof Map && (map = (Map)value).size() == 2 && map.containsKey("andIncrement") && map.containsKey("andDecrement")) {
            Iterator iter = map.values().iterator();
            iter.next();
            Object value2 = iter.next();
            return TypeUtils.castToLong(value2);
        }
        if (value instanceof Boolean) {
            return (Boolean)value != false ? 1L : 0L;
        }
        throw new JSONException("can not cast to long, value : " + value);
    }

    public static byte byteValue(BigDecimal decimal) {
        if (decimal == null) {
            return 0;
        }
        int scale = decimal.scale();
        if (scale >= -100 && scale <= 100) {
            return decimal.byteValue();
        }
        return decimal.byteValueExact();
    }

    public static short shortValue(BigDecimal decimal) {
        if (decimal == null) {
            return 0;
        }
        int scale = decimal.scale();
        if (scale >= -100 && scale <= 100) {
            return decimal.shortValue();
        }
        return decimal.shortValueExact();
    }

    public static int intValue(BigDecimal decimal) {
        if (decimal == null) {
            return 0;
        }
        int scale = decimal.scale();
        if (scale >= -100 && scale <= 100) {
            return decimal.intValue();
        }
        return decimal.intValueExact();
    }

    public static long longValue(BigDecimal decimal) {
        if (decimal == null) {
            return 0L;
        }
        int scale = decimal.scale();
        if (scale >= -100 && scale <= 100) {
            return decimal.longValue();
        }
        return decimal.longValueExact();
    }

    public static Integer castToInt(Object value) {
        Map map;
        if (value == null) {
            return null;
        }
        if (value instanceof Integer) {
            return (Integer)value;
        }
        if (value instanceof BigDecimal) {
            return TypeUtils.intValue((BigDecimal)value);
        }
        if (value instanceof Number) {
            return ((Number)value).intValue();
        }
        if (value instanceof String) {
            Matcher matcher;
            String strVal = (String)value;
            if (strVal.length() == 0 || "null".equals(strVal) || "NULL".equals(strVal)) {
                return null;
            }
            if (strVal.indexOf(44) != -1) {
                strVal = strVal.replaceAll(",", "");
            }
            if ((matcher = NUMBER_WITH_TRAILING_ZEROS_PATTERN.matcher(strVal)).find()) {
                strVal = matcher.replaceAll("");
            }
            return Integer.parseInt(strVal);
        }
        if (value instanceof Boolean) {
            return (Boolean)value != false ? 1 : 0;
        }
        if (value instanceof Map && (map = (Map)value).size() == 2 && map.containsKey("andIncrement") && map.containsKey("andDecrement")) {
            Iterator iter = map.values().iterator();
            iter.next();
            Object value2 = iter.next();
            return TypeUtils.castToInt(value2);
        }
        throw new JSONException("can not cast to int, value : " + value);
    }

    public static byte[] castToBytes(Object value) {
        if (value instanceof byte[]) {
            return (byte[])value;
        }
        if (value instanceof String) {
            return IOUtils.decodeBase64((String)value);
        }
        throw new JSONException("can not cast to byte[], value : " + value);
    }

    public static Boolean castToBoolean(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof Boolean) {
            return (Boolean)value;
        }
        if (value instanceof BigDecimal) {
            return TypeUtils.intValue((BigDecimal)value) == 1;
        }
        if (value instanceof Number) {
            return ((Number)value).intValue() == 1;
        }
        if (value instanceof String) {
            String strVal = (String)value;
            if (strVal.length() == 0 || "null".equals(strVal) || "NULL".equals(strVal)) {
                return null;
            }
            if ("true".equalsIgnoreCase(strVal) || "1".equals(strVal)) {
                return Boolean.TRUE;
            }
            if ("false".equalsIgnoreCase(strVal) || "0".equals(strVal)) {
                return Boolean.FALSE;
            }
            if ("Y".equalsIgnoreCase(strVal) || "T".equals(strVal)) {
                return Boolean.TRUE;
            }
            if ("F".equalsIgnoreCase(strVal) || "N".equals(strVal)) {
                return Boolean.FALSE;
            }
        }
        throw new JSONException("can not cast to boolean, value : " + value);
    }

    public static <T> T castToJavaBean(Object obj, Class<T> clazz) {
        return TypeUtils.cast(obj, clazz, ParserConfig.getGlobalInstance());
    }

    public static <T> T cast(Object obj, Class<T> clazz, ParserConfig config) {
        ObjectDeserializer objectDeserializer;
        if (obj == null) {
            if (clazz == Integer.TYPE) {
                return (T)Integer.valueOf(0);
            }
            if (clazz == Long.TYPE) {
                return (T)Long.valueOf(0L);
            }
            if (clazz == Short.TYPE) {
                return (T)Short.valueOf((short)0);
            }
            if (clazz == Byte.TYPE) {
                return (T)Byte.valueOf((byte)0);
            }
            if (clazz == Float.TYPE) {
                return (T)Float.valueOf(0.0f);
            }
            if (clazz == Double.TYPE) {
                return (T)Double.valueOf(0.0);
            }
            if (clazz == Boolean.TYPE) {
                return (T)Boolean.FALSE;
            }
            return null;
        }
        if (clazz == null) {
            throw new IllegalArgumentException("clazz is null");
        }
        if (clazz == obj.getClass()) {
            return (T)obj;
        }
        if (obj instanceof Map) {
            if (clazz == Map.class) {
                return (T)obj;
            }
            Map map = (Map)obj;
            if (clazz == Object.class && !map.containsKey(JSON.DEFAULT_TYPE_KEY)) {
                return (T)obj;
            }
            return TypeUtils.castToJavaBean((Map)obj, clazz, config);
        }
        if (clazz.isArray()) {
            if (obj instanceof Collection) {
                Collection collection = (Collection)obj;
                int index = 0;
                Object array = Array.newInstance(clazz.getComponentType(), collection.size());
                for (Object item : collection) {
                    Object value = TypeUtils.cast(item, clazz.getComponentType(), config);
                    Array.set(array, index, value);
                    ++index;
                }
                return (T)array;
            }
            if (clazz == byte[].class) {
                return (T)TypeUtils.castToBytes(obj);
            }
        }
        if (clazz.isAssignableFrom(obj.getClass())) {
            return (T)obj;
        }
        if (clazz == Boolean.TYPE || clazz == Boolean.class) {
            return (T)TypeUtils.castToBoolean(obj);
        }
        if (clazz == Byte.TYPE || clazz == Byte.class) {
            return (T)TypeUtils.castToByte(obj);
        }
        if (clazz == Character.TYPE || clazz == Character.class) {
            return (T)TypeUtils.castToChar(obj);
        }
        if (clazz == Short.TYPE || clazz == Short.class) {
            return (T)TypeUtils.castToShort(obj);
        }
        if (clazz == Integer.TYPE || clazz == Integer.class) {
            return (T)TypeUtils.castToInt(obj);
        }
        if (clazz == Long.TYPE || clazz == Long.class) {
            return (T)TypeUtils.castToLong(obj);
        }
        if (clazz == Float.TYPE || clazz == Float.class) {
            return (T)TypeUtils.castToFloat(obj);
        }
        if (clazz == Double.TYPE || clazz == Double.class) {
            return (T)TypeUtils.castToDouble(obj);
        }
        if (clazz == String.class) {
            return (T)TypeUtils.castToString(obj);
        }
        if (clazz == BigDecimal.class) {
            return (T)TypeUtils.castToBigDecimal(obj);
        }
        if (clazz == BigInteger.class) {
            return (T)TypeUtils.castToBigInteger(obj);
        }
        if (clazz == Date.class) {
            return (T)TypeUtils.castToDate(obj);
        }
        Object retObj = ModuleUtil.callWhenHasJavaSql(castFunction, obj, clazz);
        if (retObj != null) {
            return (T)retObj;
        }
        if (clazz.isEnum()) {
            return TypeUtils.castToEnum(obj, clazz, config);
        }
        if (Calendar.class.isAssignableFrom(clazz)) {
            Calendar calendar;
            Date date = TypeUtils.castToDate(obj);
            if (clazz == Calendar.class) {
                calendar = Calendar.getInstance(JSON.defaultTimeZone, JSON.defaultLocale);
            } else {
                try {
                    calendar = (Calendar)clazz.newInstance();
                } catch (Exception e) {
                    throw new JSONException("can not cast to : " + clazz.getName(), e);
                }
            }
            calendar.setTime(date);
            return (T)calendar;
        }
        String className = clazz.getName();
        if (className.equals("javax.xml.datatype.XMLGregorianCalendar")) {
            Date date = TypeUtils.castToDate(obj);
            Calendar calendar = Calendar.getInstance(JSON.defaultTimeZone, JSON.defaultLocale);
            calendar.setTime(date);
            return (T)CalendarCodec.instance.createXMLGregorianCalendar(calendar);
        }
        if (obj instanceof String) {
            String strVal = (String)obj;
            if (strVal.length() == 0 || "null".equals(strVal) || "NULL".equals(strVal)) {
                return null;
            }
            if (clazz == Currency.class) {
                return (T)Currency.getInstance(strVal);
            }
            if (clazz == Locale.class) {
                return (T)TypeUtils.toLocale(strVal);
            }
            if (className.startsWith("java.time.")) {
                String json = JSON.toJSONString(strVal);
                return JSON.parseObject(json, clazz);
            }
        }
        if ((objectDeserializer = config.get(clazz)) != null) {
            String str = JSON.toJSONString(obj);
            return JSON.parseObject(str, clazz);
        }
        throw new JSONException("can not cast to : " + clazz.getName());
    }

    public static Locale toLocale(String strVal) {
        String[] items = strVal.split("_");
        if (items.length == 1) {
            return new Locale(items[0]);
        }
        if (items.length == 2) {
            return new Locale(items[0], items[1]);
        }
        return new Locale(items[0], items[1], items[2]);
    }

    public static <T> T castToEnum(Object obj, Class<T> clazz, ParserConfig mapping) {
        try {
            T[] values2;
            int ordinal;
            if (obj instanceof String) {
                ObjectDeserializer deserializer;
                String name = (String)obj;
                if (name.length() == 0) {
                    return null;
                }
                if (mapping == null) {
                    mapping = ParserConfig.getGlobalInstance();
                }
                if ((deserializer = mapping.getDeserializer(clazz)) instanceof EnumDeserializer) {
                    EnumDeserializer enumDeserializer = (EnumDeserializer)deserializer;
                    return (T)enumDeserializer.getEnumByHashCode(TypeUtils.fnv1a_64(name));
                }
                return Enum.valueOf(clazz, name);
            }
            if (obj instanceof BigDecimal && (ordinal = TypeUtils.intValue((BigDecimal)obj)) < (values2 = clazz.getEnumConstants()).length) {
                return values2[ordinal];
            }
            if (obj instanceof Number && (ordinal = ((Number)obj).intValue()) < (values2 = clazz.getEnumConstants()).length) {
                return values2[ordinal];
            }
        } catch (Exception ex) {
            throw new JSONException("can not cast to : " + clazz.getName(), ex);
        }
        throw new JSONException("can not cast to : " + clazz.getName());
    }

    public static <T> T cast(Object obj, Type type, ParserConfig mapping) {
        String strVal;
        if (obj == null) {
            return null;
        }
        if (type instanceof Class) {
            return TypeUtils.cast(obj, (Class)type, mapping);
        }
        if (type instanceof ParameterizedType) {
            return TypeUtils.cast(obj, (ParameterizedType)type, mapping);
        }
        if (obj instanceof String && ((strVal = (String)obj).length() == 0 || "null".equals(strVal) || "NULL".equals(strVal))) {
            return null;
        }
        if (type instanceof TypeVariable) {
            return (T)obj;
        }
        throw new JSONException("can not cast to : " + type);
    }

    public static <T> T cast(Object obj, ParameterizedType type, ParserConfig mapping) {
        Type argType;
        String strVal;
        Type itemType;
        Type rawTye = type.getRawType();
        if (rawTye == List.class || rawTye == ArrayList.class) {
            itemType = type.getActualTypeArguments()[0];
            if (obj instanceof List) {
                List listObj = (List)obj;
                ArrayList<T> arrayList = new ArrayList<T>(listObj.size());
                for (Object item : listObj) {
                    T itemValue = itemType instanceof Class ? (item != null && item.getClass() == JSONObject.class ? ((JSONObject)item).toJavaObject((Class)itemType, mapping, 0) : TypeUtils.cast(item, (Class)itemType, mapping)) : TypeUtils.cast(item, itemType, mapping);
                    arrayList.add(itemValue);
                }
                return (T)arrayList;
            }
        }
        if (rawTye == Set.class || rawTye == HashSet.class || rawTye == TreeSet.class || rawTye == Collection.class || rawTye == List.class || rawTye == ArrayList.class) {
            itemType = type.getActualTypeArguments()[0];
            if (obj instanceof Iterable) {
                AbstractCollection collection = rawTye == Set.class || rawTye == HashSet.class ? new HashSet() : (rawTye == TreeSet.class ? new TreeSet() : new ArrayList());
                for (Object item : (Iterable)obj) {
                    T itemValue = itemType instanceof Class ? (item != null && item.getClass() == JSONObject.class ? ((JSONObject)item).toJavaObject((Class)itemType, mapping, 0) : TypeUtils.cast(item, (Class)itemType, mapping)) : TypeUtils.cast(item, itemType, mapping);
                    collection.add(itemValue);
                }
                return (T)collection;
            }
        }
        if (rawTye == Map.class || rawTye == HashMap.class) {
            Type keyType = type.getActualTypeArguments()[0];
            Type valueType = type.getActualTypeArguments()[1];
            if (obj instanceof Map) {
                HashMap<T, T> map = new HashMap<T, T>();
                for (Map.Entry entry : ((Map)obj).entrySet()) {
                    T key = TypeUtils.cast(entry.getKey(), keyType, mapping);
                    T value = TypeUtils.cast(entry.getValue(), valueType, mapping);
                    map.put(key, value);
                }
                return (T)map;
            }
        }
        if (obj instanceof String && (strVal = (String)obj).length() == 0) {
            return null;
        }
        Type[] actualTypeArguments = type.getActualTypeArguments();
        if (actualTypeArguments.length == 1 && (argType = type.getActualTypeArguments()[0]) instanceof WildcardType) {
            return TypeUtils.cast(obj, rawTye, mapping);
        }
        if (rawTye == Map.Entry.class && obj instanceof Map && ((Map)obj).size() == 1) {
            Map.Entry entry = ((Map)obj).entrySet().iterator().next();
            Object entryValue = entry.getValue();
            if (actualTypeArguments.length == 2 && entryValue instanceof Map) {
                Type valueType = actualTypeArguments[1];
                entry.setValue(TypeUtils.cast(entryValue, valueType, mapping));
            }
            return (T)entry;
        }
        if (rawTye instanceof Class) {
            ObjectDeserializer deserializer;
            if (mapping == null) {
                mapping = ParserConfig.global;
            }
            if ((deserializer = mapping.getDeserializer(rawTye)) != null) {
                String str = JSON.toJSONString(obj);
                DefaultJSONParser parser = new DefaultJSONParser(str, mapping);
                return deserializer.deserialze(parser, type, null);
            }
        }
        throw new JSONException("can not cast to : " + type);
    }

    public static <T> T castToJavaBean(Map<String, Object> map, Class<T> clazz, ParserConfig config) {
        try {
            JSONObject jsonObject;
            Map<String, Object> innerMap;
            ObjectDeserializer deserializer;
            if (clazz == StackTraceElement.class) {
                String declaringClass = (String)map.get("className");
                String methodName = (String)map.get("methodName");
                String fileName = (String)map.get("fileName");
                Number value = (Number)map.get("lineNumber");
                int lineNumber = value == null ? 0 : (value instanceof BigDecimal ? ((BigDecimal)value).intValueExact() : value.intValue());
                return (T)new StackTraceElement(declaringClass, methodName, fileName, lineNumber);
            }
            Object iClassObject = map.get(JSON.DEFAULT_TYPE_KEY);
            if (iClassObject instanceof String) {
                Class<?> loadClazz;
                String className = (String)iClassObject;
                if (config == null) {
                    config = ParserConfig.global;
                }
                if ((loadClazz = config.checkAutoType(className, null)) == null) {
                    throw new ClassNotFoundException(className + " not found");
                }
                if (!loadClazz.equals(clazz)) {
                    return (T)TypeUtils.castToJavaBean(map, loadClazz, config);
                }
            }
            if (clazz.isInterface()) {
                JSONObject object = map instanceof JSONObject ? (JSONObject)map : new JSONObject(map);
                if (config == null) {
                    config = ParserConfig.getGlobalInstance();
                }
                if ((deserializer = config.get(clazz)) != null) {
                    String json = JSON.toJSONString(object);
                    return JSON.parseObject(json, clazz);
                }
                return (T)Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{clazz}, object);
            }
            if (clazz == Locale.class) {
                Object arg0 = map.get("language");
                Object arg1 = map.get("country");
                if (arg0 instanceof String) {
                    String language = (String)arg0;
                    if (arg1 instanceof String) {
                        String country = (String)arg1;
                        return (T)new Locale(language, country);
                    }
                    if (arg1 == null) {
                        return (T)new Locale(language);
                    }
                }
            }
            if (clazz == String.class && map instanceof JSONObject) {
                return (T)map.toString();
            }
            if (clazz == JSON.class && map instanceof JSONObject) {
                return (T)map;
            }
            if (clazz == LinkedHashMap.class && map instanceof JSONObject && (innerMap = (jsonObject = (JSONObject)map).getInnerMap()) instanceof LinkedHashMap) {
                return (T)innerMap;
            }
            if (clazz.isInstance(map)) {
                return (T)map;
            }
            if (clazz == JSONObject.class) {
                return (T)new JSONObject(map);
            }
            if (config == null) {
                config = ParserConfig.getGlobalInstance();
            }
            JavaBeanDeserializer javaBeanDeser = null;
            deserializer = config.getDeserializer(clazz);
            if (deserializer instanceof JavaBeanDeserializer) {
                javaBeanDeser = (JavaBeanDeserializer)deserializer;
            }
            if (javaBeanDeser == null) {
                throw new JSONException("can not get javaBeanDeserializer. " + clazz.getName());
            }
            return (T)javaBeanDeser.createInstance(map, config);
        } catch (Exception e) {
            throw new JSONException(e.getMessage(), e);
        }
    }

    private static void addBaseClassMappings() {
        Class[] classes;
        mappings.put("byte", Byte.TYPE);
        mappings.put("short", Short.TYPE);
        mappings.put("int", Integer.TYPE);
        mappings.put("long", Long.TYPE);
        mappings.put("float", Float.TYPE);
        mappings.put("double", Double.TYPE);
        mappings.put("boolean", Boolean.TYPE);
        mappings.put("char", Character.TYPE);
        mappings.put("[byte", byte[].class);
        mappings.put("[short", short[].class);
        mappings.put("[int", int[].class);
        mappings.put("[long", long[].class);
        mappings.put("[float", float[].class);
        mappings.put("[double", double[].class);
        mappings.put("[boolean", boolean[].class);
        mappings.put("[char", char[].class);
        mappings.put("[B", byte[].class);
        mappings.put("[S", short[].class);
        mappings.put("[I", int[].class);
        mappings.put("[J", long[].class);
        mappings.put("[F", float[].class);
        mappings.put("[D", double[].class);
        mappings.put("[C", char[].class);
        mappings.put("[Z", boolean[].class);
        for (Class clazz : classes = new Class[]{Object.class, Cloneable.class, TypeUtils.loadClass("java.lang.AutoCloseable"), Exception.class, RuntimeException.class, IllegalAccessError.class, IllegalAccessException.class, IllegalArgumentException.class, IllegalMonitorStateException.class, IllegalStateException.class, IllegalThreadStateException.class, IndexOutOfBoundsException.class, InstantiationError.class, InstantiationException.class, InternalError.class, InterruptedException.class, LinkageError.class, NegativeArraySizeException.class, NoClassDefFoundError.class, NoSuchFieldError.class, NoSuchFieldException.class, NoSuchMethodError.class, NoSuchMethodException.class, NullPointerException.class, NumberFormatException.class, OutOfMemoryError.class, SecurityException.class, StackOverflowError.class, StringIndexOutOfBoundsException.class, TypeNotPresentException.class, VerifyError.class, StackTraceElement.class, HashMap.class, LinkedHashMap.class, Hashtable.class, TreeMap.class, IdentityHashMap.class, WeakHashMap.class, LinkedHashMap.class, HashSet.class, LinkedHashSet.class, TreeSet.class, ArrayList.class, TimeUnit.class, ConcurrentHashMap.class, AtomicInteger.class, AtomicLong.class, Collections.EMPTY_MAP.getClass(), Boolean.class, Character.class, Byte.class, Short.class, Integer.class, Long.class, Float.class, Double.class, Number.class, String.class, BigDecimal.class, BigInteger.class, BitSet.class, Calendar.class, Date.class, Locale.class, UUID.class, SimpleDateFormat.class, JSONObject.class, JSONPObject.class, JSONArray.class}) {
            if (clazz == null) continue;
            mappings.put(clazz.getName(), clazz);
        }
        ModuleUtil.callWhenHasJavaSql(addBaseClassMappingsFunction, mappings);
    }

    public static void clearClassMapping() {
        mappings.clear();
        TypeUtils.addBaseClassMappings();
    }

    public static void addMapping(String className, Class<?> clazz) {
        mappings.put(className, clazz);
    }

    public static Class<?> loadClass(String className) {
        return TypeUtils.loadClass(className, null);
    }

    public static boolean isPath(Class<?> clazz) {
        if (pathClass == null && !pathClass_error) {
            try {
                pathClass = Class.forName("java.nio.file.Path");
            } catch (Throwable ex) {
                pathClass_error = true;
            }
        }
        if (pathClass != null) {
            return pathClass.isAssignableFrom(clazz);
        }
        return false;
    }

    public static Class<?> getClassFromMapping(String className) {
        return (Class)mappings.get(className);
    }

    public static Class<?> loadClass(String className, ClassLoader classLoader) {
        return TypeUtils.loadClass(className, classLoader, false);
    }

    public static Class<?> loadClass(String className, ClassLoader classLoader, boolean cache) {
        if (className == null || className.length() == 0) {
            return null;
        }
        if (className.length() > 198) {
            throw new JSONException("illegal className : " + className);
        }
        Class<?> clazz = (Class<?>)mappings.get(className);
        if (clazz != null) {
            return clazz;
        }
        if (className.charAt(0) == '[') {
            Class<?> componentType = TypeUtils.loadClass(className.substring(1), classLoader);
            return Array.newInstance(componentType, 0).getClass();
        }
        if (className.startsWith("L") && className.endsWith(";")) {
            String newClassName = className.substring(1, className.length() - 1);
            return TypeUtils.loadClass(newClassName, classLoader);
        }
        try {
            if (classLoader != null) {
                clazz = classLoader.loadClass(className);
                if (cache) {
                    mappings.put(className, clazz);
                }
                return clazz;
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        try {
            ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
            if (contextClassLoader != null && contextClassLoader != classLoader) {
                clazz = contextClassLoader.loadClass(className);
                if (cache) {
                    mappings.put(className, clazz);
                }
                return clazz;
            }
        } catch (Throwable throwable) {
            // empty catch block
        }
        try {
            clazz = Class.forName(className);
            if (cache) {
                mappings.put(className, clazz);
            }
            return clazz;
        } catch (Throwable throwable) {
            return clazz;
        }
    }

    public static SerializeBeanInfo buildBeanInfo(Class<?> beanType, Map<String, String> aliasMap, PropertyNamingStrategy propertyNamingStrategy) {
        return TypeUtils.buildBeanInfo(beanType, aliasMap, propertyNamingStrategy, false);
    }

    public static SerializeBeanInfo buildBeanInfo(Class<?> beanType, Map<String, String> aliasMap, PropertyNamingStrategy propertyNamingStrategy, boolean fieldBased) {
        List<FieldInfo> sortedFieldList;
        int features;
        JSONType jsonType = TypeUtils.getAnnotation(beanType, JSONType.class);
        String[] orders = null;
        String typeName = null;
        String typeKey = null;
        if (jsonType != null) {
            Class<?> interfaceClass;
            JSONType superJsonType;
            JSONType superJsonType22;
            Object supperClass;
            PropertyNamingStrategy jsonTypeNaming;
            orders = jsonType.orders();
            typeName = jsonType.typeName();
            if (typeName.length() == 0) {
                typeName = null;
            }
            if ((jsonTypeNaming = jsonType.naming()) != PropertyNamingStrategy.NeverUseThisValueExceptDefaultValue) {
                propertyNamingStrategy = jsonTypeNaming;
            }
            features = SerializerFeature.of(jsonType.serialzeFeatures());
            for (supperClass = beanType.getSuperclass(); supperClass != null && supperClass != Object.class && (superJsonType22 = TypeUtils.getAnnotation(supperClass, JSONType.class)) != null && (typeKey = superJsonType22.typeKey()).length() == 0; supperClass = supperClass.getSuperclass()) {
            }
            supperClass = beanType.getInterfaces();
            int superJsonType22 = ((Class<?>[])supperClass).length;
            for (int i = 0; i < superJsonType22 && ((superJsonType = TypeUtils.getAnnotation(interfaceClass = supperClass[i], JSONType.class)) == null || (typeKey = superJsonType.typeKey()).length() == 0); ++i) {
            }
            if (typeKey != null && typeKey.length() == 0) {
                typeKey = null;
            }
        } else {
            features = 0;
        }
        HashMap<String, Field> fieldCacheMap = new HashMap<String, Field>();
        ParserConfig.parserAllFieldToCache(beanType, fieldCacheMap);
        List<FieldInfo> fieldInfoList = fieldBased ? TypeUtils.computeGettersWithFieldBase(beanType, aliasMap, false, propertyNamingStrategy) : TypeUtils.computeGetters(beanType, jsonType, aliasMap, fieldCacheMap, false, propertyNamingStrategy);
        Object[] fields = new FieldInfo[fieldInfoList.size()];
        fieldInfoList.toArray(fields);
        if (orders != null && orders.length != 0) {
            sortedFieldList = fieldBased ? TypeUtils.computeGettersWithFieldBase(beanType, aliasMap, true, propertyNamingStrategy) : TypeUtils.computeGetters(beanType, jsonType, aliasMap, fieldCacheMap, true, propertyNamingStrategy);
        } else {
            sortedFieldList = new ArrayList<FieldInfo>(fieldInfoList);
            Collections.sort(sortedFieldList);
        }
        Object[] sortedFields = new FieldInfo[sortedFieldList.size()];
        sortedFieldList.toArray(sortedFields);
        if (Arrays.equals(sortedFields, fields)) {
            sortedFields = fields;
        }
        return new SerializeBeanInfo(beanType, jsonType, typeName, typeKey, features, (FieldInfo[])fields, (FieldInfo[])sortedFields);
    }

    public static List<FieldInfo> computeGettersWithFieldBase(Class<?> clazz, Map<String, String> aliasMap, boolean sorted2, PropertyNamingStrategy propertyNamingStrategy) {
        LinkedHashMap<String, FieldInfo> fieldInfoMap = new LinkedHashMap<String, FieldInfo>();
        for (Class<?> currentClass = clazz; currentClass != null; currentClass = currentClass.getSuperclass()) {
            Field[] fields = currentClass.getDeclaredFields();
            TypeUtils.computeFields(currentClass, aliasMap, propertyNamingStrategy, fieldInfoMap, fields);
        }
        return TypeUtils.getFieldInfos(clazz, sorted2, fieldInfoMap);
    }

    public static List<FieldInfo> computeGetters(Class<?> clazz, Map<String, String> aliasMap) {
        return TypeUtils.computeGetters(clazz, aliasMap, true);
    }

    public static List<FieldInfo> computeGetters(Class<?> clazz, Map<String, String> aliasMap, boolean sorted2) {
        JSONType jsonType = TypeUtils.getAnnotation(clazz, JSONType.class);
        HashMap<String, Field> fieldCacheMap = new HashMap<String, Field>();
        ParserConfig.parserAllFieldToCache(clazz, fieldCacheMap);
        return TypeUtils.computeGetters(clazz, jsonType, aliasMap, fieldCacheMap, sorted2, PropertyNamingStrategy.CamelCase);
    }

    public static List<FieldInfo> computeGetters(Class<?> clazz, JSONType jsonType, Map<String, String> aliasMap, Map<String, Field> fieldCacheMap, boolean sorted2, PropertyNamingStrategy propertyNamingStrategy) {
        LinkedHashMap<String, FieldInfo> fieldInfoMap = new LinkedHashMap<String, FieldInfo>();
        boolean kotlin = TypeUtils.isKotlin(clazz);
        Constructor[] constructors = null;
        Annotation[][] paramAnnotationArrays = null;
        Object[] paramNames = null;
        short[] paramNameMapping = null;
        Method[] methods = clazz.getMethods();
        try {
            Arrays.sort(methods, new MethodInheritanceComparator());
        } catch (Throwable throwable) {
            // empty catch block
        }
        for (Method method : methods) {
            JSONField fieldAnnotation;
            String propertyName;
            String propertyName2;
            Class<?> returnType;
            String methodName = method.getName();
            int ordinal = 0;
            int serialzeFeatures = 0;
            int parserFeatures = 0;
            String label = null;
            if (Modifier.isStatic(method.getModifiers()) || (returnType = method.getReturnType()).equals(Void.TYPE) || method.getParameterTypes().length != 0 || returnType == ClassLoader.class || returnType == InputStream.class || returnType == Reader.class || methodName.equals("getMetaClass") && returnType.getName().equals("groovy.lang.MetaClass") || methodName.equals("getSuppressed") && method.getDeclaringClass() == Throwable.class || kotlin && TypeUtils.isKotlinIgnore(clazz, methodName)) continue;
            Boolean fieldAnnotationAndNameExists = false;
            JSONField annotation = TypeUtils.getAnnotation(method, JSONField.class);
            if (annotation == null) {
                annotation = TypeUtils.getSuperMethodAnnotation(clazz, method);
            }
            if (annotation == null && kotlin) {
                Constructor creatorConstructor;
                if (constructors == null && (creatorConstructor = TypeUtils.getKotlinConstructor(constructors = clazz.getDeclaredConstructors())) != null) {
                    paramAnnotationArrays = TypeUtils.getParameterAnnotations(creatorConstructor);
                    paramNames = TypeUtils.getKoltinConstructorParameters(clazz);
                    if (paramNames != null) {
                        Object[] paramNames_sorted = new String[paramNames.length];
                        System.arraycopy(paramNames, 0, paramNames_sorted, 0, paramNames.length);
                        Arrays.sort(paramNames_sorted);
                        paramNameMapping = new short[paramNames.length];
                        for (int p = 0; p < paramNames.length; p = (int)((short)(p + 1))) {
                            int index = Arrays.binarySearch(paramNames_sorted, paramNames[p]);
                            paramNameMapping[index] = p;
                        }
                        paramNames = paramNames_sorted;
                    }
                }
                if (paramNames != null && paramNameMapping != null && methodName.startsWith("get")) {
                    propertyName2 = TypeUtils.decapitalize(methodName.substring(3));
                    int p = Arrays.binarySearch(paramNames, propertyName2);
                    if (p < 0) {
                        for (int i = 0; i < paramNames.length; ++i) {
                            if (!propertyName2.equalsIgnoreCase((String)paramNames[i])) continue;
                            p = i;
                            break;
                        }
                    }
                    if (p >= 0) {
                        Field field;
                        void index = paramNameMapping[p];
                        Annotation[] paramAnnotations = paramAnnotationArrays[index];
                        if (paramAnnotations != null) {
                            for (Annotation paramAnnotation : paramAnnotations) {
                                if (!(paramAnnotation instanceof JSONField)) continue;
                                annotation = (JSONField)paramAnnotation;
                                break;
                            }
                        }
                        if (annotation == null && (field = ParserConfig.getFieldFromCache(propertyName2, fieldCacheMap)) != null) {
                            annotation = TypeUtils.getAnnotation(field, JSONField.class);
                        }
                    }
                }
            }
            if (annotation != null) {
                if (!annotation.serialize()) continue;
                ordinal = annotation.ordinal();
                serialzeFeatures = SerializerFeature.of(annotation.serialzeFeatures());
                parserFeatures = Feature.of(annotation.parseFeatures());
                if (annotation.name().length() != 0) {
                    propertyName2 = annotation.name();
                    if (aliasMap != null && (propertyName2 = aliasMap.get(propertyName2)) == null) continue;
                    FieldInfo fieldInfo = new FieldInfo(propertyName2, method, null, clazz, null, ordinal, serialzeFeatures, parserFeatures, annotation, null, label);
                    fieldInfoMap.put(propertyName2, fieldInfo);
                    continue;
                }
                if (annotation.label().length() != 0) {
                    label = annotation.label();
                }
            }
            if (methodName.startsWith("get")) {
                char ch;
                if (methodName.length() < 4 || methodName.equals("getClass") || methodName.equals("getDeclaringClass") && clazz.isEnum()) continue;
                char c3 = methodName.charAt(3);
                Field field = null;
                if (Character.isUpperCase(c3) || c3 > '\u0200') {
                    propertyName = compatibleWithJavaBean ? TypeUtils.decapitalize(methodName.substring(3)) : TypeUtils.getPropertyNameByMethodName(methodName);
                    propertyName = TypeUtils.getPropertyNameByCompatibleFieldName(fieldCacheMap, methodName, propertyName, 3);
                } else if (c3 == '_') {
                    propertyName = methodName.substring(3);
                    field = fieldCacheMap.get(propertyName);
                    if (field == null) {
                        String temp = propertyName;
                        propertyName = methodName.substring(4);
                        field = ParserConfig.getFieldFromCache(propertyName, fieldCacheMap);
                        if (field == null) {
                            propertyName = temp;
                        }
                    }
                } else if (c3 == 'f') {
                    propertyName = methodName.substring(3);
                } else if (methodName.length() >= 5 && Character.isUpperCase(methodName.charAt(4))) {
                    propertyName = TypeUtils.decapitalize(methodName.substring(3));
                } else {
                    propertyName = methodName.substring(3);
                    field = ParserConfig.getFieldFromCache(propertyName, fieldCacheMap);
                    if (field == null) continue;
                }
                boolean ignore = TypeUtils.isJSONTypeIgnore(clazz, propertyName);
                if (ignore) continue;
                if (field == null) {
                    field = ParserConfig.getFieldFromCache(propertyName, fieldCacheMap);
                }
                if (field == null && propertyName.length() > 1 && (ch = propertyName.charAt(1)) >= 'A' && ch <= 'Z') {
                    String javaBeanCompatiblePropertyName = TypeUtils.decapitalize(methodName.substring(3));
                    field = ParserConfig.getFieldFromCache(javaBeanCompatiblePropertyName, fieldCacheMap);
                }
                fieldAnnotation = null;
                if (field != null && (fieldAnnotation = TypeUtils.getAnnotation(field, JSONField.class)) != null) {
                    if (!fieldAnnotation.serialize()) continue;
                    ordinal = fieldAnnotation.ordinal();
                    serialzeFeatures = SerializerFeature.of(fieldAnnotation.serialzeFeatures());
                    parserFeatures = Feature.of(fieldAnnotation.parseFeatures());
                    if (fieldAnnotation.name().length() != 0) {
                        fieldAnnotationAndNameExists = true;
                        propertyName = fieldAnnotation.name();
                        if (aliasMap != null && (propertyName = aliasMap.get(propertyName)) == null) continue;
                    }
                    if (fieldAnnotation.label().length() != 0) {
                        label = fieldAnnotation.label();
                    }
                }
                if (aliasMap != null && (propertyName = aliasMap.get(propertyName)) == null) continue;
                if (propertyNamingStrategy != null && !fieldAnnotationAndNameExists.booleanValue()) {
                    propertyName = propertyNamingStrategy.translate(propertyName);
                }
                FieldInfo fieldInfo = new FieldInfo(propertyName, method, field, clazz, null, ordinal, serialzeFeatures, parserFeatures, annotation, fieldAnnotation, label);
                fieldInfoMap.put(propertyName, fieldInfo);
            }
            if (!methodName.startsWith("is") || methodName.length() < 3 || returnType != Boolean.TYPE && returnType != Boolean.class) continue;
            char c2 = methodName.charAt(2);
            Field field = null;
            if (Character.isUpperCase(c2)) {
                propertyName = compatibleWithJavaBean ? TypeUtils.decapitalize(methodName.substring(2)) : Character.toLowerCase(methodName.charAt(2)) + methodName.substring(3);
                propertyName = TypeUtils.getPropertyNameByCompatibleFieldName(fieldCacheMap, methodName, propertyName, 2);
            } else if (c2 == '_') {
                propertyName = methodName.substring(3);
                field = fieldCacheMap.get(propertyName);
                if (field == null) {
                    String temp = propertyName;
                    propertyName = methodName.substring(2);
                    field = ParserConfig.getFieldFromCache(propertyName, fieldCacheMap);
                    if (field == null) {
                        propertyName = temp;
                    }
                }
            } else if (c2 == 'f') {
                propertyName = methodName.substring(2);
            } else {
                propertyName = methodName.substring(2);
                field = ParserConfig.getFieldFromCache(propertyName, fieldCacheMap);
                if (field == null) continue;
            }
            boolean ignore = TypeUtils.isJSONTypeIgnore(clazz, propertyName);
            if (ignore) continue;
            if (field == null) {
                field = ParserConfig.getFieldFromCache(propertyName, fieldCacheMap);
            }
            if (field == null) {
                field = ParserConfig.getFieldFromCache(methodName, fieldCacheMap);
            }
            fieldAnnotation = null;
            if (field != null && (fieldAnnotation = TypeUtils.getAnnotation(field, JSONField.class)) != null) {
                if (!fieldAnnotation.serialize()) continue;
                ordinal = fieldAnnotation.ordinal();
                serialzeFeatures = SerializerFeature.of(fieldAnnotation.serialzeFeatures());
                parserFeatures = Feature.of(fieldAnnotation.parseFeatures());
                if (fieldAnnotation.name().length() != 0) {
                    propertyName = fieldAnnotation.name();
                    if (aliasMap != null && (propertyName = aliasMap.get(propertyName)) == null) continue;
                }
                if (fieldAnnotation.label().length() != 0) {
                    label = fieldAnnotation.label();
                }
            }
            if (aliasMap != null && (propertyName = aliasMap.get(propertyName)) == null) continue;
            if (propertyNamingStrategy != null) {
                propertyName = propertyNamingStrategy.translate(propertyName);
            }
            if (fieldInfoMap.containsKey(propertyName)) continue;
            FieldInfo fieldInfo = new FieldInfo(propertyName, method, field, clazz, null, ordinal, serialzeFeatures, parserFeatures, annotation, fieldAnnotation, label);
            fieldInfoMap.put(propertyName, fieldInfo);
        }
        Field[] fields = clazz.getFields();
        TypeUtils.computeFields(clazz, aliasMap, propertyNamingStrategy, fieldInfoMap, fields);
        return TypeUtils.getFieldInfos(clazz, sorted2, fieldInfoMap);
    }

    private static List<FieldInfo> getFieldInfos(Class<?> clazz, boolean sorted2, Map<String, FieldInfo> fieldInfoMap) {
        ArrayList<FieldInfo> fieldInfoList = new ArrayList<FieldInfo>();
        String[] orders = null;
        JSONType annotation = TypeUtils.getAnnotation(clazz, JSONType.class);
        if (annotation != null) {
            orders = annotation.orders();
        }
        if (orders != null && orders.length > 0) {
            LinkedHashMap<String, FieldInfo> map = new LinkedHashMap<String, FieldInfo>(fieldInfoMap.size());
            for (FieldInfo field : fieldInfoMap.values()) {
                map.put(field.name, field);
            }
            for (String item : orders) {
                FieldInfo field = (FieldInfo)map.get(item);
                if (field == null) continue;
                fieldInfoList.add(field);
                map.remove(item);
            }
            fieldInfoList.addAll(map.values());
        } else {
            fieldInfoList.addAll(fieldInfoMap.values());
            if (sorted2) {
                Collections.sort(fieldInfoList);
            }
        }
        return fieldInfoList;
    }

    private static void computeFields(Class<?> clazz, Map<String, String> aliasMap, PropertyNamingStrategy propertyNamingStrategy, Map<String, FieldInfo> fieldInfoMap, Field[] fields) {
        for (Field field : fields) {
            if (Modifier.isStatic(field.getModifiers())) continue;
            JSONField fieldAnnotation = TypeUtils.getAnnotation(field, JSONField.class);
            int ordinal = 0;
            int serialzeFeatures = 0;
            int parserFeatures = 0;
            String propertyName = field.getName();
            String label = null;
            if (fieldAnnotation != null) {
                if (!fieldAnnotation.serialize()) continue;
                ordinal = fieldAnnotation.ordinal();
                serialzeFeatures = SerializerFeature.of(fieldAnnotation.serialzeFeatures());
                parserFeatures = Feature.of(fieldAnnotation.parseFeatures());
                if (fieldAnnotation.name().length() != 0) {
                    propertyName = fieldAnnotation.name();
                }
                if (fieldAnnotation.label().length() != 0) {
                    label = fieldAnnotation.label();
                }
            }
            if (aliasMap != null && (propertyName = aliasMap.get(propertyName)) == null) continue;
            if (propertyNamingStrategy != null) {
                propertyName = propertyNamingStrategy.translate(propertyName);
            }
            if (fieldInfoMap.containsKey(propertyName)) continue;
            FieldInfo fieldInfo = new FieldInfo(propertyName, null, field, clazz, null, ordinal, serialzeFeatures, parserFeatures, null, fieldAnnotation, label);
            fieldInfoMap.put(propertyName, fieldInfo);
        }
    }

    private static String getPropertyNameByCompatibleFieldName(Map<String, Field> fieldCacheMap, String methodName, String propertyName, int fromIdx) {
        if (compatibleWithFieldName && !fieldCacheMap.containsKey(propertyName)) {
            String tempPropertyName = methodName.substring(fromIdx);
            return fieldCacheMap.containsKey(tempPropertyName) ? tempPropertyName : propertyName;
        }
        return propertyName;
    }

    public static JSONField getSuperMethodAnnotation(Class<?> clazz, Method method) {
        Class<?> superClass;
        Class<?>[] interfaces = clazz.getInterfaces();
        if (interfaces.length > 0) {
            Class<?>[] types = method.getParameterTypes();
            for (Class<?> interfaceClass : interfaces) {
                for (Method interfaceMethod : interfaceClass.getMethods()) {
                    JSONField annotation;
                    Class<?>[] interfaceTypes = interfaceMethod.getParameterTypes();
                    if (interfaceTypes.length != types.length || !interfaceMethod.getName().equals(method.getName())) continue;
                    boolean match = true;
                    for (int i = 0; i < types.length; ++i) {
                        if (interfaceTypes[i].equals(types[i])) continue;
                        match = false;
                        break;
                    }
                    if (!match || (annotation = TypeUtils.getAnnotation(interfaceMethod, JSONField.class)) == null) continue;
                    return annotation;
                }
            }
        }
        if ((superClass = clazz.getSuperclass()) == null) {
            return null;
        }
        if (Modifier.isAbstract(superClass.getModifiers())) {
            Class<?>[] types = method.getParameterTypes();
            for (Method interfaceMethod : superClass.getMethods()) {
                JSONField annotation;
                Class<?>[] interfaceTypes = interfaceMethod.getParameterTypes();
                if (interfaceTypes.length != types.length || !interfaceMethod.getName().equals(method.getName())) continue;
                boolean match = true;
                for (int i = 0; i < types.length; ++i) {
                    if (interfaceTypes[i].equals(types[i])) continue;
                    match = false;
                    break;
                }
                if (!match || (annotation = TypeUtils.getAnnotation(interfaceMethod, JSONField.class)) == null) continue;
                return annotation;
            }
        }
        return null;
    }

    private static boolean isJSONTypeIgnore(Class<?> clazz, String propertyName) {
        JSONType jsonType = TypeUtils.getAnnotation(clazz, JSONType.class);
        if (jsonType != null) {
            String[] fields = jsonType.includes();
            if (fields.length > 0) {
                for (String field : fields) {
                    if (!propertyName.equals(field)) continue;
                    return false;
                }
                return true;
            }
            for (String field : fields = jsonType.ignores()) {
                if (!propertyName.equals(field)) continue;
                return true;
            }
        }
        if (clazz.getSuperclass() != Object.class && clazz.getSuperclass() != null) {
            return TypeUtils.isJSONTypeIgnore(clazz.getSuperclass(), propertyName);
        }
        return false;
    }

    public static boolean isGenericParamType(Type type) {
        if (type instanceof ParameterizedType) {
            return true;
        }
        if (type instanceof Class) {
            Type superType = ((Class)type).getGenericSuperclass();
            return superType != Object.class && TypeUtils.isGenericParamType(superType);
        }
        return false;
    }

    public static Type getGenericParamType(Type type) {
        if (type instanceof ParameterizedType) {
            return type;
        }
        if (type instanceof Class) {
            return TypeUtils.getGenericParamType(((Class)type).getGenericSuperclass());
        }
        return type;
    }

    public static Type unwrapOptional(Type type) {
        ParameterizedType parameterizedType;
        if (!optionalClassInited) {
            try {
                optionalClass = Class.forName("java.util.Optional");
            } catch (Exception exception) {
            } finally {
                optionalClassInited = true;
            }
        }
        if (type instanceof ParameterizedType && (parameterizedType = (ParameterizedType)type).getRawType() == optionalClass) {
            return parameterizedType.getActualTypeArguments()[0];
        }
        return type;
    }

    public static Class<?> getClass(Type type) {
        Type[] upperBounds;
        if (type.getClass() == Class.class) {
            return (Class)type;
        }
        if (type instanceof ParameterizedType) {
            return TypeUtils.getClass(((ParameterizedType)type).getRawType());
        }
        if (type instanceof TypeVariable) {
            Type boundType = ((TypeVariable)type).getBounds()[0];
            if (boundType instanceof Class) {
                return (Class)boundType;
            }
            return TypeUtils.getClass(boundType);
        }
        if (type instanceof WildcardType && (upperBounds = ((WildcardType)type).getUpperBounds()).length == 1) {
            return TypeUtils.getClass(upperBounds[0]);
        }
        return Object.class;
    }

    public static Field getField(Class<?> clazz, String fieldName, Field[] declaredFields) {
        for (Field field : declaredFields) {
            char c1;
            char c0;
            String itemName = field.getName();
            if (fieldName.equals(itemName)) {
                return field;
            }
            if (fieldName.length() <= 2 || (c0 = fieldName.charAt(0)) < 'a' || c0 > 'z' || (c1 = fieldName.charAt(1)) < 'A' || c1 > 'Z' || !fieldName.equalsIgnoreCase(itemName)) continue;
            return field;
        }
        Class<?> superClass = clazz.getSuperclass();
        if (superClass != null && superClass != Object.class) {
            return TypeUtils.getField(superClass, fieldName, superClass.getDeclaredFields());
        }
        return null;
    }

    public static int getSerializeFeatures(Class<?> clazz) {
        JSONType annotation = TypeUtils.getAnnotation(clazz, JSONType.class);
        if (annotation == null) {
            return 0;
        }
        return SerializerFeature.of(annotation.serialzeFeatures());
    }

    public static int getParserFeatures(Class<?> clazz) {
        JSONType annotation = TypeUtils.getAnnotation(clazz, JSONType.class);
        if (annotation == null) {
            return 0;
        }
        return Feature.of(annotation.parseFeatures());
    }

    public static String decapitalize(String name) {
        if (name == null || name.length() == 0) {
            return name;
        }
        if (name.length() > 1 && Character.isUpperCase(name.charAt(1)) && Character.isUpperCase(name.charAt(0))) {
            return name;
        }
        char[] chars = name.toCharArray();
        chars[0] = Character.toLowerCase(chars[0]);
        return new String(chars);
    }

    public static String getPropertyNameByMethodName(String methodName) {
        return Character.toLowerCase(methodName.charAt(3)) + methodName.substring(4);
    }

    static void setAccessible(AccessibleObject obj) {
        if (!setAccessibleEnable) {
            return;
        }
        if (obj.isAccessible()) {
            return;
        }
        try {
            obj.setAccessible(true);
        } catch (Throwable error) {
            setAccessibleEnable = false;
        }
    }

    public static Type getCollectionItemType(Type fieldType) {
        if (fieldType instanceof ParameterizedType) {
            return TypeUtils.getCollectionItemType((ParameterizedType)fieldType);
        }
        if (fieldType instanceof Class) {
            return TypeUtils.getCollectionItemType((Class)fieldType);
        }
        return Object.class;
    }

    private static Type getCollectionItemType(Class<?> clazz) {
        return clazz.getName().startsWith("java.") ? Object.class : TypeUtils.getCollectionItemType(TypeUtils.getCollectionSuperType(clazz));
    }

    private static Type getCollectionItemType(ParameterizedType parameterizedType) {
        Type rawType = parameterizedType.getRawType();
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        if (rawType == Collection.class) {
            return TypeUtils.getWildcardTypeUpperBounds(actualTypeArguments[0]);
        }
        Class rawClass = (Class)rawType;
        Map<TypeVariable, Type> actualTypeMap = TypeUtils.createActualTypeMap(rawClass.getTypeParameters(), actualTypeArguments);
        Type superType = TypeUtils.getCollectionSuperType(rawClass);
        if (superType instanceof ParameterizedType) {
            Class<?> superClass = TypeUtils.getRawClass(superType);
            Type[] superClassTypeParameters = ((ParameterizedType)superType).getActualTypeArguments();
            return superClassTypeParameters.length > 0 ? TypeUtils.getCollectionItemType(TypeUtils.makeParameterizedType(superClass, superClassTypeParameters, actualTypeMap)) : TypeUtils.getCollectionItemType(superClass);
        }
        return TypeUtils.getCollectionItemType((Class)superType);
    }

    private static Type getCollectionSuperType(Class<?> clazz) {
        Type assignable = null;
        for (Type type : clazz.getGenericInterfaces()) {
            Class<?> rawClass = TypeUtils.getRawClass(type);
            if (rawClass == Collection.class) {
                return type;
            }
            if (!Collection.class.isAssignableFrom(rawClass)) continue;
            assignable = type;
        }
        return assignable == null ? clazz.getGenericSuperclass() : assignable;
    }

    private static Map<TypeVariable, Type> createActualTypeMap(TypeVariable[] typeParameters, Type[] actualTypeArguments) {
        int length = typeParameters.length;
        HashMap<TypeVariable, Type> actualTypeMap = new HashMap<TypeVariable, Type>(length);
        for (int i = 0; i < length; ++i) {
            actualTypeMap.put(typeParameters[i], actualTypeArguments[i]);
        }
        return actualTypeMap;
    }

    private static ParameterizedType makeParameterizedType(Class<?> rawClass, Type[] typeParameters, Map<TypeVariable, Type> actualTypeMap) {
        int length = typeParameters.length;
        Type[] actualTypeArguments = new Type[length];
        for (int i = 0; i < length; ++i) {
            actualTypeArguments[i] = TypeUtils.getActualType(typeParameters[i], actualTypeMap);
        }
        return new ParameterizedTypeImpl(actualTypeArguments, null, rawClass);
    }

    private static Type getActualType(Type typeParameter, Map<TypeVariable, Type> actualTypeMap) {
        if (typeParameter instanceof TypeVariable) {
            return actualTypeMap.get(typeParameter);
        }
        if (typeParameter instanceof ParameterizedType) {
            return TypeUtils.makeParameterizedType(TypeUtils.getRawClass(typeParameter), ((ParameterizedType)typeParameter).getActualTypeArguments(), actualTypeMap);
        }
        if (typeParameter instanceof GenericArrayType) {
            return new GenericArrayTypeImpl(TypeUtils.getActualType(((GenericArrayType)typeParameter).getGenericComponentType(), actualTypeMap));
        }
        return typeParameter;
    }

    private static Type getWildcardTypeUpperBounds(Type type) {
        if (type instanceof WildcardType) {
            WildcardType wildcardType = (WildcardType)type;
            Type[] upperBounds = wildcardType.getUpperBounds();
            return upperBounds.length > 0 ? upperBounds[0] : Object.class;
        }
        return type;
    }

    public static Class<?> getCollectionItemClass(Type fieldType) {
        if (fieldType instanceof ParameterizedType) {
            Class itemClass;
            WildcardType wildcardType;
            Type[] upperBounds;
            Type actualTypeArgument = ((ParameterizedType)fieldType).getActualTypeArguments()[0];
            if (actualTypeArgument instanceof WildcardType && (upperBounds = (wildcardType = (WildcardType)actualTypeArgument).getUpperBounds()).length == 1) {
                actualTypeArgument = upperBounds[0];
            }
            if (actualTypeArgument instanceof Class) {
                itemClass = (Class)actualTypeArgument;
                if (!Modifier.isPublic(itemClass.getModifiers())) {
                    throw new JSONException("can not create ASMParser");
                }
            } else {
                throw new JSONException("can not create ASMParser");
            }
            return itemClass;
        }
        return Object.class;
    }

    public static Type checkPrimitiveArray(GenericArrayType genericArrayType) {
        Class ck;
        Type clz = genericArrayType;
        Type genericComponentType = genericArrayType.getGenericComponentType();
        String prefix = "[";
        while (genericComponentType instanceof GenericArrayType) {
            genericComponentType = ((GenericArrayType)genericComponentType).getGenericComponentType();
            prefix = prefix + prefix;
        }
        if (genericComponentType instanceof Class && (ck = (Class)genericComponentType).isPrimitive()) {
            try {
                String postfix = (String)primitiveTypeMap.get(ck);
                if (postfix != null) {
                    clz = Class.forName(prefix + postfix);
                }
            } catch (ClassNotFoundException classNotFoundException) {
                // empty catch block
            }
        }
        return clz;
    }

    public static Set createSet(Type type) {
        Set<Object> set;
        Class<AbstractSet> rawClass = TypeUtils.getRawClass(type);
        if (rawClass == AbstractCollection.class || rawClass == Collection.class) {
            set = new HashSet();
        } else if (rawClass.isAssignableFrom(HashSet.class)) {
            set = new HashSet();
        } else if (rawClass.isAssignableFrom(LinkedHashSet.class)) {
            set = new LinkedHashSet();
        } else if (rawClass.isAssignableFrom(TreeSet.class)) {
            set = new TreeSet();
        } else if (rawClass.isAssignableFrom(EnumSet.class)) {
            Object itemType = type instanceof ParameterizedType ? ((ParameterizedType)type).getActualTypeArguments()[0] : Object.class;
            set = EnumSet.noneOf(itemType);
        } else {
            try {
                set = (Set)rawClass.newInstance();
            } catch (Exception e) {
                throw new JSONException("create instance error, class " + rawClass.getName());
            }
        }
        return set;
    }

    public static Collection createCollection(Type type) {
        Collection<Object> list;
        Class rawClass = TypeUtils.getRawClass(type);
        if (rawClass == AbstractCollection.class || rawClass == Collection.class) {
            list = new ArrayList();
        } else if (rawClass.isAssignableFrom(HashSet.class)) {
            list = new HashSet();
        } else if (rawClass.isAssignableFrom(LinkedHashSet.class)) {
            list = new LinkedHashSet();
        } else if (rawClass.isAssignableFrom(TreeSet.class)) {
            list = new TreeSet();
        } else if (rawClass.isAssignableFrom(ArrayList.class)) {
            list = new ArrayList();
        } else if (rawClass.isAssignableFrom(EnumSet.class)) {
            Object itemType = type instanceof ParameterizedType ? ((ParameterizedType)type).getActualTypeArguments()[0] : Object.class;
            list = EnumSet.noneOf(itemType);
        } else if (rawClass.isAssignableFrom(Queue.class) || class_deque != null && rawClass.isAssignableFrom(class_deque)) {
            list = new LinkedList();
        } else {
            try {
                list = (Collection)rawClass.newInstance();
            } catch (Exception e) {
                throw new JSONException("create instance error, class " + rawClass.getName());
            }
        }
        return list;
    }

    public static Class<?> getRawClass(Type type) {
        if (type instanceof Class) {
            return (Class)type;
        }
        if (type instanceof ParameterizedType) {
            return TypeUtils.getRawClass(((ParameterizedType)type).getRawType());
        }
        if (type instanceof WildcardType) {
            WildcardType wildcardType = (WildcardType)type;
            Type[] upperBounds = wildcardType.getUpperBounds();
            if (upperBounds.length == 1) {
                return TypeUtils.getRawClass(upperBounds[0]);
            }
            throw new JSONException("TODO");
        }
        throw new JSONException("TODO");
    }

    public static boolean isProxy(Class<?> clazz) {
        for (Class<?> item : clazz.getInterfaces()) {
            String interfaceName = item.getName();
            if (!isProxyClassNames.contains(interfaceName)) continue;
            return true;
        }
        return false;
    }

    public static boolean isTransient(Method method) {
        if (method == null) {
            return false;
        }
        if (!transientClassInited) {
            try {
                transientClass = Class.forName("java.beans.Transient");
            } catch (Exception exception) {
            } finally {
                transientClassInited = true;
            }
        }
        if (transientClass != null) {
            Annotation annotation = TypeUtils.getAnnotation(method, transientClass);
            return annotation != null;
        }
        return false;
    }

    public static boolean isAnnotationPresentOneToMany(Method method) {
        if (method == null) {
            return false;
        }
        if (class_OneToMany == null && !class_OneToMany_error) {
            try {
                class_OneToMany = Class.forName("javax.persistence.OneToMany");
            } catch (Throwable e) {
                class_OneToMany_error = true;
            }
        }
        return class_OneToMany != null && method.isAnnotationPresent(class_OneToMany);
    }

    public static boolean isAnnotationPresentManyToMany(Method method) {
        if (method == null) {
            return false;
        }
        if (class_ManyToMany == null && !class_ManyToMany_error) {
            try {
                class_ManyToMany = Class.forName("javax.persistence.ManyToMany");
            } catch (Throwable e) {
                class_ManyToMany_error = true;
            }
        }
        return class_ManyToMany != null && (method.isAnnotationPresent(class_OneToMany) || method.isAnnotationPresent(class_ManyToMany));
    }

    public static boolean isHibernateInitialized(Object object) {
        if (object == null) {
            return false;
        }
        if (method_HibernateIsInitialized == null && !method_HibernateIsInitialized_error) {
            try {
                Class<?> class_Hibernate = Class.forName("org.hibernate.Hibernate");
                method_HibernateIsInitialized = class_Hibernate.getMethod("isInitialized", Object.class);
            } catch (Throwable e) {
                method_HibernateIsInitialized_error = true;
            }
        }
        if (method_HibernateIsInitialized != null) {
            try {
                Boolean initialized = (Boolean)method_HibernateIsInitialized.invoke(null, object);
                return initialized;
            } catch (Throwable throwable) {
                // empty catch block
            }
        }
        return true;
    }

    public static double parseDouble(String str) {
        int len = str.length();
        if (len > 10) {
            return Double.parseDouble(str);
        }
        boolean negative = false;
        long longValue = 0L;
        int scale = 0;
        for (int i = 0; i < len; ++i) {
            char ch = str.charAt(i);
            if (ch == '-' && i == 0) {
                negative = true;
                continue;
            }
            if (ch == '.') {
                if (scale != 0) {
                    return Double.parseDouble(str);
                }
                scale = len - i - 1;
                continue;
            }
            if (ch >= '0' && ch <= '9') {
                int digit = ch - 48;
                longValue = longValue * 10L + (long)digit;
                continue;
            }
            return Double.parseDouble(str);
        }
        if (negative) {
            longValue = -longValue;
        }
        switch (scale) {
            case 0: {
                return longValue;
            }
            case 1: {
                return (double)longValue / 10.0;
            }
            case 2: {
                return (double)longValue / 100.0;
            }
            case 3: {
                return (double)longValue / 1000.0;
            }
            case 4: {
                return (double)longValue / 10000.0;
            }
            case 5: {
                return (double)longValue / 100000.0;
            }
            case 6: {
                return (double)longValue / 1000000.0;
            }
            case 7: {
                return (double)longValue / 1.0E7;
            }
            case 8: {
                return (double)longValue / 1.0E8;
            }
            case 9: {
                return (double)longValue / 1.0E9;
            }
        }
        return Double.parseDouble(str);
    }

    public static float parseFloat(String str) {
        int len = str.length();
        if (len >= 10) {
            return Float.parseFloat(str);
        }
        boolean negative = false;
        long longValue = 0L;
        int scale = 0;
        for (int i = 0; i < len; ++i) {
            char ch = str.charAt(i);
            if (ch == '-' && i == 0) {
                negative = true;
                continue;
            }
            if (ch == '.') {
                if (scale != 0) {
                    return Float.parseFloat(str);
                }
                scale = len - i - 1;
                continue;
            }
            if (ch >= '0' && ch <= '9') {
                int digit = ch - 48;
                longValue = longValue * 10L + (long)digit;
                continue;
            }
            return Float.parseFloat(str);
        }
        if (negative) {
            longValue = -longValue;
        }
        switch (scale) {
            case 0: {
                return longValue;
            }
            case 1: {
                return (float)longValue / 10.0f;
            }
            case 2: {
                return (float)longValue / 100.0f;
            }
            case 3: {
                return (float)longValue / 1000.0f;
            }
            case 4: {
                return (float)longValue / 10000.0f;
            }
            case 5: {
                return (float)longValue / 100000.0f;
            }
            case 6: {
                return (float)longValue / 1000000.0f;
            }
            case 7: {
                return (float)longValue / 1.0E7f;
            }
            case 8: {
                return (float)longValue / 1.0E8f;
            }
            case 9: {
                return (float)longValue / 1.0E9f;
            }
        }
        return Float.parseFloat(str);
    }

    public static long fnv1a_64_extract(String key) {
        long hashCode = -3750763034362895579L;
        for (int i = 0; i < key.length(); ++i) {
            char ch = key.charAt(i);
            if (ch == '_' || ch == '-') continue;
            if (ch >= 'A' && ch <= 'Z') {
                ch = (char)(ch + 32);
            }
            hashCode ^= (long)ch;
            hashCode *= 1099511628211L;
        }
        return hashCode;
    }

    public static long fnv1a_64_lower(String key) {
        long hashCode = -3750763034362895579L;
        for (int i = 0; i < key.length(); ++i) {
            char ch = key.charAt(i);
            if (ch >= 'A' && ch <= 'Z') {
                ch = (char)(ch + 32);
            }
            hashCode ^= (long)ch;
            hashCode *= 1099511628211L;
        }
        return hashCode;
    }

    public static long fnv1a_64(String key) {
        long hashCode = -3750763034362895579L;
        for (int i = 0; i < key.length(); ++i) {
            char ch = key.charAt(i);
            hashCode ^= (long)ch;
            hashCode *= 1099511628211L;
        }
        return hashCode;
    }

    public static boolean isKotlin(Class clazz) {
        if (kotlin_metadata == null && !kotlin_metadata_error) {
            try {
                kotlin_metadata = Class.forName("kotlin.Metadata");
            } catch (Throwable e) {
                kotlin_metadata_error = true;
            }
        }
        return kotlin_metadata != null && clazz.isAnnotationPresent(kotlin_metadata);
    }

    public static Constructor getKotlinConstructor(Constructor[] constructors) {
        return TypeUtils.getKotlinConstructor(constructors, null);
    }

    public static Constructor getKotlinConstructor(Constructor[] constructors, String[] paramNames) {
        Constructor creatorConstructor = null;
        for (Constructor constructor : constructors) {
            Class<?>[] parameterTypes = constructor.getParameterTypes();
            if (paramNames != null && parameterTypes.length != paramNames.length || parameterTypes.length > 0 && parameterTypes[parameterTypes.length - 1].getName().equals("kotlin.jvm.internal.DefaultConstructorMarker") || creatorConstructor != null && creatorConstructor.getParameterTypes().length >= parameterTypes.length) continue;
            creatorConstructor = constructor;
        }
        return creatorConstructor;
    }

    public static String[] getKoltinConstructorParameters(Class clazz) {
        Class<?> class_kotlin_kclass;
        if (kotlin_kclass_constructor == null && !kotlin_class_klass_error) {
            try {
                class_kotlin_kclass = Class.forName("kotlin.reflect.jvm.internal.KClassImpl");
                kotlin_kclass_constructor = class_kotlin_kclass.getConstructor(Class.class);
            } catch (Throwable e) {
                kotlin_class_klass_error = true;
            }
        }
        if (kotlin_kclass_constructor == null) {
            return null;
        }
        if (kotlin_kclass_getConstructors == null && !kotlin_class_klass_error) {
            try {
                class_kotlin_kclass = Class.forName("kotlin.reflect.jvm.internal.KClassImpl");
                kotlin_kclass_getConstructors = class_kotlin_kclass.getMethod("getConstructors", new Class[0]);
            } catch (Throwable e) {
                kotlin_class_klass_error = true;
            }
        }
        if (kotlin_kfunction_getParameters == null && !kotlin_class_klass_error) {
            try {
                Class<?> class_kotlin_kfunction = Class.forName("kotlin.reflect.KFunction");
                kotlin_kfunction_getParameters = class_kotlin_kfunction.getMethod("getParameters", new Class[0]);
            } catch (Throwable e) {
                kotlin_class_klass_error = true;
            }
        }
        if (kotlin_kparameter_getName == null && !kotlin_class_klass_error) {
            try {
                Class<?> class_kotlinn_kparameter = Class.forName("kotlin.reflect.KParameter");
                kotlin_kparameter_getName = class_kotlinn_kparameter.getMethod("getName", new Class[0]);
            } catch (Throwable e) {
                kotlin_class_klass_error = true;
            }
        }
        if (kotlin_error) {
            return null;
        }
        try {
            Object constructor = null;
            Object kclassImpl = kotlin_kclass_constructor.newInstance(clazz);
            Iterable it = (Iterable)kotlin_kclass_getConstructors.invoke(kclassImpl, new Object[0]);
            Iterator iterator2 = it.iterator();
            while (iterator2.hasNext()) {
                Object item = iterator2.next();
                List parameters = (List)kotlin_kfunction_getParameters.invoke(item, new Object[0]);
                if (constructor == null || parameters.size() != 0) {
                    constructor = item;
                }
                iterator2.hasNext();
            }
            if (constructor == null) {
                return null;
            }
            List parameters = (List)kotlin_kfunction_getParameters.invoke(constructor, new Object[0]);
            String[] names = new String[parameters.size()];
            for (int i = 0; i < parameters.size(); ++i) {
                Object param = parameters.get(i);
                names[i] = (String)kotlin_kparameter_getName.invoke(param, new Object[0]);
            }
            return names;
        } catch (Throwable e) {
            e.printStackTrace();
            kotlin_error = true;
            return null;
        }
    }

    private static boolean isKotlinIgnore(Class clazz, String methodName) {
        if (kotlinIgnores == null && !kotlinIgnores_error) {
            try {
                HashMap<Class, String[]> map = new HashMap<Class, String[]>();
                Class<?> charRangeClass = Class.forName("kotlin.ranges.CharRange");
                map.put(charRangeClass, new String[]{"getEndInclusive", "isEmpty"});
                Class<?> intRangeClass = Class.forName("kotlin.ranges.IntRange");
                map.put(intRangeClass, new String[]{"getEndInclusive", "isEmpty"});
                Class<?> longRangeClass = Class.forName("kotlin.ranges.LongRange");
                map.put(longRangeClass, new String[]{"getEndInclusive", "isEmpty"});
                Class<?> floatRangeClass = Class.forName("kotlin.ranges.ClosedFloatRange");
                map.put(floatRangeClass, new String[]{"getEndInclusive", "isEmpty"});
                Class<?> doubleRangeClass = Class.forName("kotlin.ranges.ClosedDoubleRange");
                map.put(doubleRangeClass, new String[]{"getEndInclusive", "isEmpty"});
                kotlinIgnores = map;
            } catch (Throwable error) {
                kotlinIgnores_error = true;
            }
        }
        if (kotlinIgnores == null) {
            return false;
        }
        Object[] ignores = kotlinIgnores.get(clazz);
        return ignores != null && Arrays.binarySearch(ignores, methodName) >= 0;
    }

    public static <A extends Annotation> A getAnnotation(Class<?> targetClass, Class<A> annotationClass) {
        int n;
        A targetAnnotation = targetClass.getAnnotation(annotationClass);
        Class mixInClass = null;
        Type type = JSON.getMixInAnnotations(targetClass);
        if (type instanceof Class) {
            mixInClass = (Class)type;
        }
        if (mixInClass != null) {
            A mixInAnnotation = mixInClass.getAnnotation(annotationClass);
            Annotation[] annotations = mixInClass.getAnnotations();
            if (mixInAnnotation == null && annotations.length > 0) {
                Annotation annotation;
                Annotation[] annotationArray = annotations;
                n = annotationArray.length;
                for (int i = 0; i < n && (mixInAnnotation = (annotation = annotationArray[i]).annotationType().getAnnotation(annotationClass)) == null; ++i) {
                }
            }
            if (mixInAnnotation != null) {
                return mixInAnnotation;
            }
        }
        Annotation[] targetClassAnnotations = targetClass.getAnnotations();
        if (targetAnnotation == null && targetClassAnnotations.length > 0) {
            Annotation annotation;
            Annotation[] annotationArray = targetClassAnnotations;
            int n2 = annotationArray.length;
            for (n = 0; n < n2 && (targetAnnotation = (annotation = annotationArray[n]).annotationType().getAnnotation(annotationClass)) == null; ++n) {
            }
        }
        return targetAnnotation;
    }

    public static <A extends Annotation> A getAnnotation(Field field, Class<A> annotationClass) {
        A targetAnnotation = field.getAnnotation(annotationClass);
        Class<?> clazz = field.getDeclaringClass();
        Class mixInClass = null;
        Type type = JSON.getMixInAnnotations(clazz);
        if (type instanceof Class) {
            mixInClass = (Class)type;
        }
        if (mixInClass != null) {
            Field mixInField = null;
            String fieldName = field.getName();
            for (Class currClass = mixInClass; currClass != null && currClass != Object.class; currClass = currClass.getSuperclass()) {
                try {
                    mixInField = currClass.getDeclaredField(fieldName);
                    break;
                } catch (NoSuchFieldException noSuchFieldException) {
                    continue;
                }
            }
            if (mixInField == null) {
                return targetAnnotation;
            }
            A mixInAnnotation = mixInField.getAnnotation(annotationClass);
            if (mixInAnnotation != null) {
                return mixInAnnotation;
            }
        }
        return targetAnnotation;
    }

    public static <A extends Annotation> A getAnnotation(Method method, Class<A> annotationClass) {
        A targetAnnotation = method.getAnnotation(annotationClass);
        Class<?> clazz = method.getDeclaringClass();
        Class mixInClass = null;
        Type type = JSON.getMixInAnnotations(clazz);
        if (type instanceof Class) {
            mixInClass = (Class)type;
        }
        if (mixInClass != null) {
            Method mixInMethod = null;
            String methodName = method.getName();
            Class<?>[] parameterTypes = method.getParameterTypes();
            for (Class currClass = mixInClass; currClass != null && currClass != Object.class; currClass = currClass.getSuperclass()) {
                try {
                    mixInMethod = currClass.getDeclaredMethod(methodName, parameterTypes);
                    break;
                } catch (NoSuchMethodException noSuchMethodException) {
                    continue;
                }
            }
            if (mixInMethod == null) {
                return targetAnnotation;
            }
            A mixInAnnotation = mixInMethod.getAnnotation(annotationClass);
            if (mixInAnnotation != null) {
                return mixInAnnotation;
            }
        }
        return targetAnnotation;
    }

    public static Annotation[][] getParameterAnnotations(Method method) {
        Annotation[][] targetAnnotations = method.getParameterAnnotations();
        Class<?> clazz = method.getDeclaringClass();
        Class mixInClass = null;
        Type type = JSON.getMixInAnnotations(clazz);
        if (type instanceof Class) {
            mixInClass = (Class)type;
        }
        if (mixInClass != null) {
            Method mixInMethod = null;
            String methodName = method.getName();
            Class<?>[] parameterTypes = method.getParameterTypes();
            for (Class currClass = mixInClass; currClass != null && currClass != Object.class; currClass = currClass.getSuperclass()) {
                try {
                    mixInMethod = currClass.getDeclaredMethod(methodName, parameterTypes);
                    break;
                } catch (NoSuchMethodException e) {
                    continue;
                }
            }
            if (mixInMethod == null) {
                return targetAnnotations;
            }
            Annotation[][] mixInAnnotations = mixInMethod.getParameterAnnotations();
            if (mixInAnnotations != null) {
                return mixInAnnotations;
            }
        }
        return targetAnnotations;
    }

    public static Annotation[][] getParameterAnnotations(Constructor constructor) {
        Annotation[][] targetAnnotations = constructor.getParameterAnnotations();
        Class clazz = constructor.getDeclaringClass();
        Class mixInClass = null;
        Type type = JSON.getMixInAnnotations(clazz);
        if (type instanceof Class) {
            mixInClass = (Class)type;
        }
        if (mixInClass != null) {
            Constructor mixInConstructor = null;
            Class<?>[] parameterTypes = constructor.getParameterTypes();
            ArrayList enclosingClasses = new ArrayList(2);
            for (Class<?> enclosingClass = mixInClass.getEnclosingClass(); enclosingClass != null; enclosingClass = enclosingClass.getEnclosingClass()) {
                enclosingClasses.add(enclosingClass);
            }
            int level = enclosingClasses.size();
            for (Class currClass = mixInClass; currClass != null && currClass != Object.class; currClass = currClass.getSuperclass()) {
                try {
                    if (level != 0) {
                        Class[] outerClassAndParameterTypes = new Class[level + parameterTypes.length];
                        System.arraycopy(parameterTypes, 0, outerClassAndParameterTypes, level, parameterTypes.length);
                        for (int i = level; i > 0; --i) {
                            outerClassAndParameterTypes[i - 1] = (Class)enclosingClasses.get(i - 1);
                        }
                        mixInConstructor = mixInClass.getDeclaredConstructor(outerClassAndParameterTypes);
                        break;
                    }
                    mixInConstructor = mixInClass.getDeclaredConstructor(parameterTypes);
                    break;
                } catch (NoSuchMethodException e) {
                    --level;
                    continue;
                }
            }
            if (mixInConstructor == null) {
                return targetAnnotations;
            }
            Annotation[][] mixInAnnotations = mixInConstructor.getParameterAnnotations();
            if (mixInAnnotations != null) {
                return mixInAnnotations;
            }
        }
        return targetAnnotations;
    }

    public static boolean isJacksonCreator(Method method) {
        if (method == null) {
            return false;
        }
        if (class_JacksonCreator == null && !class_JacksonCreator_error) {
            try {
                class_JacksonCreator = Class.forName("com.fasterxml.jackson.annotation.JsonCreator");
            } catch (Throwable e) {
                class_JacksonCreator_error = true;
            }
        }
        return class_JacksonCreator != null && method.isAnnotationPresent(class_JacksonCreator);
    }

    public static Object optionalEmpty(Type type) {
        if (OPTIONAL_ERROR) {
            return null;
        }
        Class<?> clazz = TypeUtils.getClass(type);
        if (clazz == null) {
            return null;
        }
        String className = clazz.getName();
        if ("java.util.Optional".equals(className)) {
            if (OPTIONAL_EMPTY == null) {
                try {
                    Method empty = Class.forName(className).getMethod("empty", new Class[0]);
                    OPTIONAL_EMPTY = empty.invoke(null, new Object[0]);
                } catch (Throwable e) {
                    OPTIONAL_ERROR = true;
                }
            }
            return OPTIONAL_EMPTY;
        }
        return null;
    }

    static {
        oracleDateMethodInited = false;
        optionalClassInited = false;
        transientClassInited = false;
        class_OneToMany = null;
        class_OneToMany_error = false;
        class_ManyToMany = null;
        class_ManyToMany_error = false;
        method_HibernateIsInitialized = null;
        method_HibernateIsInitialized_error = false;
        mappings = new ConcurrentHashMap(256, 0.75f, 1);
        pathClass_error = false;
        class_JacksonCreator = null;
        class_JacksonCreator_error = false;
        class_XmlAccessType = null;
        class_XmlAccessorType = null;
        classXmlAccessorType_error = false;
        method_XmlAccessorType_value = null;
        field_XmlAccessType_FIELD = null;
        field_XmlAccessType_FIELD_VALUE = null;
        class_deque = null;
        try {
            compatibleWithJavaBean = "true".equals(IOUtils.getStringProperty("fastjson.compatibleWithJavaBean"));
            compatibleWithFieldName = "true".equals(IOUtils.getStringProperty("fastjson.compatibleWithFieldName"));
        } catch (Throwable throwable) {
            // empty catch block
        }
        try {
            class_deque = Class.forName("java.util.Deque");
        } catch (Throwable throwable) {
            // empty catch block
        }
        isClobFunction = new Function<Class, Boolean>(){

            @Override
            public Boolean apply(Class clazz) {
                return Clob.class.isAssignableFrom(clazz);
            }
        };
        castToSqlDateFunction = new Function<Object, Object>(){

            @Override
            public Object apply(Object value) {
                if (value == null) {
                    return null;
                }
                if (value instanceof java.sql.Date) {
                    return (java.sql.Date)value;
                }
                if (value instanceof Date) {
                    return new java.sql.Date(((Date)value).getTime());
                }
                if (value instanceof Calendar) {
                    return new java.sql.Date(((Calendar)value).getTimeInMillis());
                }
                long longValue = 0L;
                if (value instanceof BigDecimal) {
                    longValue = TypeUtils.longValue((BigDecimal)value);
                } else if (value instanceof Number) {
                    longValue = ((Number)value).longValue();
                }
                if (value instanceof String) {
                    String strVal = (String)value;
                    if (strVal.length() == 0 || "null".equals(strVal) || "NULL".equals(strVal)) {
                        return null;
                    }
                    if (TypeUtils.isNumber(strVal)) {
                        longValue = Long.parseLong(strVal);
                    } else {
                        JSONScanner scanner = new JSONScanner(strVal);
                        if (scanner.scanISO8601DateIfMatch(false)) {
                            longValue = scanner.getCalendar().getTime().getTime();
                        } else {
                            throw new JSONException("can not cast to Timestamp, value : " + strVal);
                        }
                    }
                }
                if (longValue <= 0L) {
                    throw new JSONException("can not cast to Date, value : " + value);
                }
                return new java.sql.Date(longValue);
            }
        };
        castToSqlTimeFunction = new Function<Object, Object>(){

            @Override
            public Object apply(Object value) {
                if (value == null) {
                    return null;
                }
                if (value instanceof Time) {
                    return (Time)value;
                }
                if (value instanceof Date) {
                    return new Time(((Date)value).getTime());
                }
                if (value instanceof Calendar) {
                    return new Time(((Calendar)value).getTimeInMillis());
                }
                long longValue = 0L;
                if (value instanceof BigDecimal) {
                    longValue = TypeUtils.longValue((BigDecimal)value);
                } else if (value instanceof Number) {
                    longValue = ((Number)value).longValue();
                }
                if (value instanceof String) {
                    String strVal = (String)value;
                    if (strVal.length() == 0 || "null".equalsIgnoreCase(strVal)) {
                        return null;
                    }
                    if (TypeUtils.isNumber(strVal)) {
                        longValue = Long.parseLong(strVal);
                    } else {
                        if (strVal.length() == 8 && strVal.charAt(2) == ':' && strVal.charAt(5) == ':') {
                            return Time.valueOf(strVal);
                        }
                        JSONScanner scanner = new JSONScanner(strVal);
                        if (scanner.scanISO8601DateIfMatch(false)) {
                            longValue = scanner.getCalendar().getTime().getTime();
                        } else {
                            throw new JSONException("can not cast to Timestamp, value : " + strVal);
                        }
                    }
                }
                if (longValue <= 0L) {
                    throw new JSONException("can not cast to Date, value : " + value);
                }
                return new Time(longValue);
            }
        };
        castToTimestampFunction = new Function<Object, Object>(){

            @Override
            public Object apply(Object value) {
                if (value == null) {
                    return null;
                }
                if (value instanceof Calendar) {
                    return new Timestamp(((Calendar)value).getTimeInMillis());
                }
                if (value instanceof Timestamp) {
                    return (Timestamp)value;
                }
                if (value instanceof Date) {
                    return new Timestamp(((Date)value).getTime());
                }
                long longValue = 0L;
                if (value instanceof BigDecimal) {
                    longValue = TypeUtils.longValue((BigDecimal)value);
                } else if (value instanceof Number) {
                    longValue = ((Number)value).longValue();
                }
                if (value instanceof String) {
                    String strVal = (String)value;
                    if (strVal.length() == 0 || "null".equals(strVal) || "NULL".equals(strVal)) {
                        return null;
                    }
                    if (strVal.endsWith(".000000000")) {
                        strVal = strVal.substring(0, strVal.length() - 10);
                    } else if (strVal.endsWith(".000000")) {
                        strVal = strVal.substring(0, strVal.length() - 7);
                    }
                    if (strVal.length() == 29 && strVal.charAt(4) == '-' && strVal.charAt(7) == '-' && strVal.charAt(10) == ' ' && strVal.charAt(13) == ':' && strVal.charAt(16) == ':' && strVal.charAt(19) == '.') {
                        int year = TypeUtils.num(strVal.charAt(0), strVal.charAt(1), strVal.charAt(2), strVal.charAt(3));
                        int month = TypeUtils.num(strVal.charAt(5), strVal.charAt(6));
                        int day = TypeUtils.num(strVal.charAt(8), strVal.charAt(9));
                        int hour = TypeUtils.num(strVal.charAt(11), strVal.charAt(12));
                        int minute = TypeUtils.num(strVal.charAt(14), strVal.charAt(15));
                        int second = TypeUtils.num(strVal.charAt(17), strVal.charAt(18));
                        int nanos = TypeUtils.num(strVal.charAt(20), strVal.charAt(21), strVal.charAt(22), strVal.charAt(23), strVal.charAt(24), strVal.charAt(25), strVal.charAt(26), strVal.charAt(27), strVal.charAt(28));
                        return new Timestamp(year - 1900, month - 1, day, hour, minute, second, nanos);
                    }
                    if (TypeUtils.isNumber(strVal)) {
                        longValue = Long.parseLong(strVal);
                    } else {
                        JSONScanner scanner = new JSONScanner(strVal);
                        if (scanner.scanISO8601DateIfMatch(false)) {
                            longValue = scanner.getCalendar().getTime().getTime();
                        } else {
                            throw new JSONException("can not cast to Timestamp, value : " + strVal);
                        }
                    }
                }
                return new Timestamp(longValue);
            }
        };
        castFunction = new BiFunction<Object, Class, Object>(){

            @Override
            public Object apply(Object obj, Class clazz) {
                if (clazz == java.sql.Date.class) {
                    return TypeUtils.castToSqlDate(obj);
                }
                if (clazz == Time.class) {
                    return TypeUtils.castToSqlTime(obj);
                }
                if (clazz == Timestamp.class) {
                    return TypeUtils.castToTimestamp(obj);
                }
                return null;
            }
        };
        addBaseClassMappingsFunction = new Function<Map<String, Class<?>>, Void>(){

            @Override
            public Void apply(Map<String, Class<?>> mappings) {
                Class[] classes;
                for (Class clazz : classes = new Class[]{Time.class, java.sql.Date.class, Timestamp.class}) {
                    if (clazz == null) continue;
                    mappings.put(clazz.getName(), clazz);
                }
                return null;
            }
        };
        TypeUtils.addBaseClassMappings();
        primitiveTypeMap = new HashMap<Class, String>(8){
            {
                this.put(Boolean.TYPE, "Z");
                this.put(Character.TYPE, "C");
                this.put(Byte.TYPE, "B");
                this.put(Short.TYPE, "S");
                this.put(Integer.TYPE, "I");
                this.put(Long.TYPE, "J");
                this.put(Float.TYPE, "F");
                this.put(Double.TYPE, "D");
            }
        };
        isProxyClassNames = new HashSet<String>(6){
            {
                this.add("net.sf.cglib.proxy.Factory");
                this.add("org.springframework.cglib.proxy.Factory");
                this.add("javassist.util.proxy.ProxyObject");
                this.add("org.apache.ibatis.javassist.util.proxy.ProxyObject");
                this.add("org.hibernate.proxy.HibernateProxy");
                this.add("org.springframework.context.annotation.ConfigurationClassEnhancer$EnhancedConfiguration");
            }
        };
        OPTIONAL_ERROR = false;
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static class MethodInheritanceComparator
    implements Comparator<Method> {
        @Override
        public int compare(Method m1, Method m22) {
            Class<?> class2;
            int cmp = m1.getName().compareTo(m22.getName());
            if (cmp != 0) {
                return cmp;
            }
            Class<?> class1 = m1.getReturnType();
            if (class1.equals(class2 = m22.getReturnType())) {
                return 0;
            }
            if (class1.isAssignableFrom(class2)) {
                return -1;
            }
            if (class2.isAssignableFrom(class1)) {
                return 1;
            }
            return 0;
        }
    }
}

