/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONAware;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPathException;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.JSONLexerBase;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.parser.deserializer.FieldDeserializer;
import com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.serializer.FieldSerializer;
import com.alibaba.fastjson.serializer.JavaBeanSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.util.IOUtils;
import com.alibaba.fastjson.util.TypeUtils;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class JSONPath
implements JSONAware {
    private static ConcurrentMap<String, JSONPath> pathCache = new ConcurrentHashMap<String, JSONPath>(128, 0.75f, 1);
    private final String path;
    private Segment[] segments;
    private boolean hasRefSegment;
    private SerializeConfig serializeConfig;
    private ParserConfig parserConfig;
    private boolean ignoreNullValue;
    static final long SIZE = 5614464919154503228L;
    static final long LENGTH = -1580386065683472715L;

    public JSONPath(String path) {
        this(path, SerializeConfig.getGlobalInstance(), ParserConfig.getGlobalInstance(), true);
    }

    public JSONPath(String path, boolean ignoreNullValue) {
        this(path, SerializeConfig.getGlobalInstance(), ParserConfig.getGlobalInstance(), ignoreNullValue);
    }

    public JSONPath(String path, SerializeConfig serializeConfig, ParserConfig parserConfig, boolean ignoreNullValue) {
        if (path == null || path.length() == 0) {
            throw new JSONPathException("json-path can not be null or empty");
        }
        this.path = path;
        this.serializeConfig = serializeConfig;
        this.parserConfig = parserConfig;
        this.ignoreNullValue = ignoreNullValue;
    }

    protected void init() {
        if (this.segments != null) {
            return;
        }
        if ("*".equals(this.path)) {
            this.segments = new Segment[]{WildCardSegment.instance};
        } else {
            JSONPathParser parser = new JSONPathParser(this.path);
            this.segments = parser.explain();
            this.hasRefSegment = parser.hasRefSegment;
        }
    }

    public boolean isRef() {
        try {
            this.init();
            for (int i = 0; i < this.segments.length; ++i) {
                Segment segment = this.segments[i];
                Class<?> segmentType = segment.getClass();
                if (segmentType == ArrayAccessSegment.class || segmentType == PropertySegment.class) continue;
                return false;
            }
            return true;
        } catch (JSONPathException ex) {
            return false;
        }
    }

    public Object eval(Object rootObject) {
        if (rootObject == null) {
            return null;
        }
        this.init();
        Object currentObject = rootObject;
        for (int i = 0; i < this.segments.length; ++i) {
            Segment segment = this.segments[i];
            currentObject = segment.eval(this, rootObject, currentObject);
        }
        return currentObject;
    }

    public <T> T eval(Object rootObject, Type clazz, ParserConfig parserConfig) {
        Object obj = this.eval(rootObject);
        return TypeUtils.cast(obj, clazz, parserConfig);
    }

    public <T> T eval(Object rootObject, Type clazz) {
        return this.eval(rootObject, clazz, ParserConfig.getGlobalInstance());
    }

    public Object extract(DefaultJSONParser parser) {
        if (parser == null) {
            return null;
        }
        this.init();
        if (this.hasRefSegment) {
            Object root = parser.parse();
            return this.eval(root);
        }
        if (this.segments.length == 0) {
            return parser.parse();
        }
        Segment lastSegment = this.segments[this.segments.length - 1];
        if (lastSegment instanceof TypeSegment || lastSegment instanceof FloorSegment || lastSegment instanceof MultiIndexSegment) {
            return this.eval(parser.parse());
        }
        Context context = null;
        for (int i = 0; i < this.segments.length; ++i) {
            boolean eval;
            boolean last;
            Segment segment = this.segments[i];
            boolean bl = last = i == this.segments.length - 1;
            if (context != null && context.object != null) {
                context.object = segment.eval(this, null, context.object);
                continue;
            }
            if (!last) {
                Segment nextSegment = this.segments[i + 1];
                eval = segment instanceof PropertySegment && ((PropertySegment)segment).deep && (nextSegment instanceof ArrayAccessSegment || nextSegment instanceof MultiIndexSegment || nextSegment instanceof MultiPropertySegment || nextSegment instanceof SizeSegment || nextSegment instanceof PropertySegment || nextSegment instanceof FilterSegment) ? true : (nextSegment instanceof ArrayAccessSegment && ((ArrayAccessSegment)nextSegment).index < 0 ? true : (nextSegment instanceof FilterSegment ? true : (segment instanceof WildCardSegment ? true : segment instanceof MultiIndexSegment)));
            } else {
                eval = true;
            }
            context = new Context(context, eval);
            segment.extract(this, parser, context);
        }
        return context.object;
    }

    public boolean contains(Object rootObject) {
        if (rootObject == null) {
            return false;
        }
        this.init();
        Object currentObject = rootObject;
        for (int i = 0; i < this.segments.length; ++i) {
            Object parentObject = currentObject;
            if ((currentObject = this.segments[i].eval(this, rootObject, currentObject)) == null) {
                return false;
            }
            if (currentObject != Collections.EMPTY_LIST || !(parentObject instanceof List)) continue;
            return ((List)parentObject).contains(currentObject);
        }
        return true;
    }

    public boolean containsValue(Object rootObject, Object value) {
        Object currentObject = this.eval(rootObject);
        if (currentObject == value) {
            return true;
        }
        if (currentObject == null) {
            return false;
        }
        if (currentObject instanceof Iterable) {
            for (Object item : (Iterable)currentObject) {
                if (!JSONPath.eq(item, value)) continue;
                return true;
            }
            return false;
        }
        return JSONPath.eq(currentObject, value);
    }

    public int size(Object rootObject) {
        if (rootObject == null) {
            return -1;
        }
        this.init();
        Object currentObject = rootObject;
        for (int i = 0; i < this.segments.length; ++i) {
            currentObject = this.segments[i].eval(this, rootObject, currentObject);
        }
        return this.evalSize(currentObject);
    }

    public Set<?> keySet(Object rootObject) {
        if (rootObject == null) {
            return null;
        }
        this.init();
        Object currentObject = rootObject;
        for (int i = 0; i < this.segments.length; ++i) {
            currentObject = this.segments[i].eval(this, rootObject, currentObject);
        }
        return this.evalKeySet(currentObject);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public void patchAdd(Object rootObject, Object value, boolean replace) {
        Segment lastSegment;
        Object newResult;
        if (rootObject == null) {
            return;
        }
        this.init();
        Object currentObject = rootObject;
        Object parentObject = null;
        for (int i = 0; i < this.segments.length; ++i) {
            parentObject = currentObject;
            Segment segment = this.segments[i];
            if ((currentObject = segment.eval(this, rootObject, currentObject)) != null || i == this.segments.length - 1 || !(segment instanceof PropertySegment)) continue;
            currentObject = new JSONObject();
            ((PropertySegment)segment).setValue(this, parentObject, currentObject);
        }
        Object result = currentObject;
        if (!replace && result instanceof Collection) {
            Collection collection = (Collection)result;
            collection.add(value);
            return;
        }
        if (result != null && !replace) {
            Class<?> resultClass = result.getClass();
            if (resultClass.isArray()) {
                int length = Array.getLength(result);
                Object descArray = Array.newInstance(resultClass.getComponentType(), length + 1);
                System.arraycopy(result, 0, descArray, 0, length);
                Array.set(descArray, length, value);
                newResult = descArray;
            } else {
                if (!Map.class.isAssignableFrom(resultClass)) throw new JSONException("unsupported array put operation. " + resultClass);
                newResult = value;
            }
        } else {
            newResult = value;
        }
        if ((lastSegment = this.segments[this.segments.length - 1]) instanceof PropertySegment) {
            PropertySegment propertySegment = (PropertySegment)lastSegment;
            propertySegment.setValue(this, parentObject, newResult);
            return;
        }
        if (!(lastSegment instanceof ArrayAccessSegment)) throw new UnsupportedOperationException();
        ((ArrayAccessSegment)lastSegment).setValue(this, parentObject, newResult);
    }

    public void arrayAdd(Object rootObject, Object ... values2) {
        Object descArray;
        if (values2 == null || values2.length == 0) {
            return;
        }
        if (rootObject == null) {
            return;
        }
        this.init();
        Object currentObject = rootObject;
        Object parentObject = null;
        for (int i = 0; i < this.segments.length; ++i) {
            if (i == this.segments.length - 1) {
                parentObject = currentObject;
            }
            currentObject = this.segments[i].eval(this, rootObject, currentObject);
        }
        Object result = currentObject;
        if (result == null) {
            throw new JSONPathException("value not found in path " + this.path);
        }
        if (result instanceof Collection) {
            Collection collection = (Collection)result;
            for (Object value : values2) {
                collection.add(value);
            }
            return;
        }
        Class<?> resultClass = result.getClass();
        if (resultClass.isArray()) {
            int length = Array.getLength(result);
            descArray = Array.newInstance(resultClass.getComponentType(), length + values2.length);
            System.arraycopy(result, 0, descArray, 0, length);
            for (int i = 0; i < values2.length; ++i) {
                Array.set(descArray, length + i, values2[i]);
            }
        } else {
            throw new JSONException("unsupported array put operation. " + resultClass);
        }
        Object newResult = descArray;
        Segment lastSegment = this.segments[this.segments.length - 1];
        if (lastSegment instanceof PropertySegment) {
            PropertySegment propertySegment = (PropertySegment)lastSegment;
            propertySegment.setValue(this, parentObject, newResult);
            return;
        }
        if (lastSegment instanceof ArrayAccessSegment) {
            ((ArrayAccessSegment)lastSegment).setValue(this, parentObject, newResult);
            return;
        }
        throw new UnsupportedOperationException();
    }

    public boolean remove(Object rootObject) {
        if (rootObject == null) {
            return false;
        }
        this.init();
        Object currentObject = rootObject;
        Object parentObject = null;
        Segment lastSegment = this.segments[this.segments.length - 1];
        for (int i = 0; i < this.segments.length; ++i) {
            if (i == this.segments.length - 1) {
                parentObject = currentObject;
                break;
            }
            Segment segement = this.segments[i];
            if (i == this.segments.length - 2 && lastSegment instanceof FilterSegment && segement instanceof PropertySegment) {
                PropertySegment propertySegment;
                FilterSegment filterSegment = (FilterSegment)lastSegment;
                if (currentObject instanceof List) {
                    propertySegment = (PropertySegment)segement;
                    List list = (List)currentObject;
                    Iterator it = list.iterator();
                    while (it.hasNext()) {
                        Object item = it.next();
                        Object result = propertySegment.eval(this, rootObject, item);
                        if (result instanceof Iterable) {
                            filterSegment.remove(this, rootObject, result);
                            continue;
                        }
                        if (!(result instanceof Map) || !filterSegment.filter.apply(this, rootObject, currentObject, result)) continue;
                        it.remove();
                    }
                    return true;
                }
                if (currentObject instanceof Map) {
                    propertySegment = (PropertySegment)segement;
                    Object result = propertySegment.eval(this, rootObject, currentObject);
                    if (result == null) {
                        return false;
                    }
                    if (result instanceof Map && filterSegment.filter.apply(this, rootObject, currentObject, result)) {
                        propertySegment.remove(this, currentObject);
                        return true;
                    }
                }
            }
            if ((currentObject = segement.eval(this, rootObject, currentObject)) == null) break;
        }
        if (parentObject == null) {
            return false;
        }
        if (lastSegment instanceof PropertySegment) {
            Segment parentSegment;
            PropertySegment propertySegment = (PropertySegment)lastSegment;
            if (parentObject instanceof Collection && this.segments.length > 1 && ((parentSegment = this.segments[this.segments.length - 2]) instanceof RangeSegment || parentSegment instanceof MultiIndexSegment)) {
                Collection collection = (Collection)parentObject;
                boolean removedOnce = false;
                for (Object item : collection) {
                    boolean removed = propertySegment.remove(this, item);
                    if (!removed) continue;
                    removedOnce = true;
                }
                return removedOnce;
            }
            return propertySegment.remove(this, parentObject);
        }
        if (lastSegment instanceof ArrayAccessSegment) {
            return ((ArrayAccessSegment)lastSegment).remove(this, parentObject);
        }
        if (lastSegment instanceof FilterSegment) {
            FilterSegment filterSegment = (FilterSegment)lastSegment;
            return filterSegment.remove(this, rootObject, parentObject);
        }
        throw new UnsupportedOperationException();
    }

    public boolean set(Object rootObject, Object value) {
        return this.set(rootObject, value, true);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean set(Object rootObject, Object value, boolean p) {
        if (rootObject == null) {
            return false;
        }
        this.init();
        Object currentObject = rootObject;
        Object parentObject = null;
        for (int i = 0; i < this.segments.length; ++i) {
            parentObject = currentObject;
            Segment segment = this.segments[i];
            if ((currentObject = segment.eval(this, rootObject, currentObject)) != null) continue;
            Segment nextSegment = null;
            if (i < this.segments.length - 1) {
                nextSegment = this.segments[i + 1];
            }
            Object newObj = null;
            if (nextSegment instanceof PropertySegment) {
                JavaBeanDeserializer beanDeserializer = null;
                Class<?> fieldClass = null;
                if (segment instanceof PropertySegment) {
                    String propertyName = ((PropertySegment)segment).propertyName;
                    Class<?> parentClass = parentObject.getClass();
                    JavaBeanDeserializer parentBeanDeserializer = this.getJavaBeanDeserializer(parentClass);
                    if (parentBeanDeserializer != null) {
                        FieldDeserializer fieldDeserializer = parentBeanDeserializer.getFieldDeserializer(propertyName);
                        fieldClass = fieldDeserializer.fieldInfo.fieldClass;
                        beanDeserializer = this.getJavaBeanDeserializer(fieldClass);
                    }
                }
                if (beanDeserializer != null) {
                    if (beanDeserializer.beanInfo.defaultConstructor == null) return false;
                    newObj = beanDeserializer.createInstance(null, fieldClass);
                } else {
                    newObj = new JSONObject();
                }
            } else if (nextSegment instanceof ArrayAccessSegment) {
                newObj = new JSONArray();
            }
            if (newObj == null) break;
            if (segment instanceof PropertySegment) {
                PropertySegment propSegement = (PropertySegment)segment;
                propSegement.setValue(this, parentObject, newObj);
                currentObject = newObj;
                continue;
            }
            if (!(segment instanceof ArrayAccessSegment)) break;
            ArrayAccessSegment arrayAccessSegement = (ArrayAccessSegment)segment;
            arrayAccessSegement.setValue(this, parentObject, newObj);
            currentObject = newObj;
        }
        if (parentObject == null) {
            return false;
        }
        Segment lastSegment = this.segments[this.segments.length - 1];
        if (lastSegment instanceof PropertySegment) {
            PropertySegment propertySegment = (PropertySegment)lastSegment;
            propertySegment.setValue(this, parentObject, value);
            return true;
        }
        if (!(lastSegment instanceof ArrayAccessSegment)) throw new UnsupportedOperationException();
        return ((ArrayAccessSegment)lastSegment).setValue(this, parentObject, value);
    }

    public static Object eval(Object rootObject, String path) {
        JSONPath jsonpath = JSONPath.compile(path);
        return jsonpath.eval(rootObject);
    }

    public static Object eval(Object rootObject, String path, boolean ignoreNullValue) {
        JSONPath jsonpath = JSONPath.compile(path, ignoreNullValue);
        return jsonpath.eval(rootObject);
    }

    public static int size(Object rootObject, String path) {
        JSONPath jsonpath = JSONPath.compile(path);
        Object result = jsonpath.eval(rootObject);
        return jsonpath.evalSize(result);
    }

    public static Set<?> keySet(Object rootObject, String path) {
        JSONPath jsonpath = JSONPath.compile(path);
        Object result = jsonpath.eval(rootObject);
        return jsonpath.evalKeySet(result);
    }

    public static boolean contains(Object rootObject, String path) {
        if (rootObject == null) {
            return false;
        }
        JSONPath jsonpath = JSONPath.compile(path);
        return jsonpath.contains(rootObject);
    }

    public static boolean containsValue(Object rootObject, String path, Object value) {
        JSONPath jsonpath = JSONPath.compile(path);
        return jsonpath.containsValue(rootObject, value);
    }

    public static void arrayAdd(Object rootObject, String path, Object ... values2) {
        JSONPath jsonpath = JSONPath.compile(path);
        jsonpath.arrayAdd(rootObject, values2);
    }

    public static boolean set(Object rootObject, String path, Object value) {
        JSONPath jsonpath = JSONPath.compile(path);
        return jsonpath.set(rootObject, value);
    }

    public static boolean remove(Object root, String path) {
        JSONPath jsonpath = JSONPath.compile(path);
        return jsonpath.remove(root);
    }

    public static JSONPath compile(String path) {
        if (path == null) {
            throw new JSONPathException("jsonpath can not be null");
        }
        JSONPath jsonpath = (JSONPath)pathCache.get(path);
        if (jsonpath == null) {
            jsonpath = new JSONPath(path);
            if (pathCache.size() < 1024) {
                pathCache.putIfAbsent(path, jsonpath);
                jsonpath = (JSONPath)pathCache.get(path);
            }
        }
        return jsonpath;
    }

    public static JSONPath compile(String path, boolean ignoreNullValue) {
        if (path == null) {
            throw new JSONPathException("jsonpath can not be null");
        }
        JSONPath jsonpath = (JSONPath)pathCache.get(path);
        if (jsonpath == null) {
            jsonpath = new JSONPath(path, ignoreNullValue);
            if (pathCache.size() < 1024) {
                pathCache.putIfAbsent(path, jsonpath);
                jsonpath = (JSONPath)pathCache.get(path);
            }
        }
        return jsonpath;
    }

    public static Object read(String json, String path) {
        return JSONPath.compile(path).eval(JSON.parse(json));
    }

    public static <T> T read(String json, String path, Type clazz, ParserConfig parserConfig) {
        return JSONPath.compile(path).eval(JSON.parse(json), clazz, parserConfig);
    }

    public static <T> T read(String json, String path, Type clazz) {
        return JSONPath.read(json, path, clazz, null);
    }

    public static Object extract(String json, String path, ParserConfig config, int features, Feature ... optionFeatures) {
        DefaultJSONParser parser = new DefaultJSONParser(json, config, features |= Feature.OrderedField.mask);
        JSONPath jsonPath = JSONPath.compile(path);
        Object result = jsonPath.extract(parser);
        parser.lexer.close();
        return result;
    }

    public static Object extract(String json, String path) {
        return JSONPath.extract(json, path, ParserConfig.global, JSON.DEFAULT_PARSER_FEATURE, new Feature[0]);
    }

    public static Map<String, Object> paths(Object javaObject) {
        return JSONPath.paths(javaObject, SerializeConfig.globalInstance);
    }

    public static Map<String, Object> paths(Object javaObject, SerializeConfig config) {
        IdentityHashMap<Object, String> values2 = new IdentityHashMap<Object, String>();
        HashMap<String, Object> paths = new HashMap<String, Object>();
        JSONPath.paths(values2, paths, "/", javaObject, config);
        return paths;
    }

    private static void paths(Map<Object, String> values2, Map<String, Object> paths, String parent, Object javaObject, SerializeConfig config) {
        if (javaObject == null) {
            return;
        }
        String p = values2.put(javaObject, parent);
        if (p != null) {
            boolean basicType;
            Class<?> type = javaObject.getClass();
            boolean bl = basicType = type == String.class || type == Boolean.class || type == Character.class || type == UUID.class || type.isEnum() || javaObject instanceof Number || javaObject instanceof Date;
            if (!basicType) {
                return;
            }
        }
        paths.put(parent, javaObject);
        if (javaObject instanceof Map) {
            Map map = (Map)javaObject;
            for (Map.Entry entryObj : map.entrySet()) {
                Map.Entry entry = entryObj;
                Object key = entry.getKey();
                if (!(key instanceof String)) continue;
                String path = parent.equals("/") ? "/" + key : parent + "/" + key;
                JSONPath.paths(values2, paths, path, entry.getValue(), config);
            }
            return;
        }
        if (javaObject instanceof Collection) {
            Collection collection = (Collection)javaObject;
            int i = 0;
            for (Object item : collection) {
                String path = parent.equals("/") ? "/" + i : parent + "/" + i;
                JSONPath.paths(values2, paths, path, item, config);
                ++i;
            }
            return;
        }
        Class<?> clazz = javaObject.getClass();
        if (clazz.isArray()) {
            int len = Array.getLength(javaObject);
            for (int i = 0; i < len; ++i) {
                Object item = Array.get(javaObject, i);
                String path = parent.equals("/") ? "/" + i : parent + "/" + i;
                JSONPath.paths(values2, paths, path, item, config);
            }
            return;
        }
        if (ParserConfig.isPrimitive2(clazz) || clazz.isEnum()) {
            return;
        }
        ObjectSerializer serializer = config.getObjectWriter(clazz);
        if (serializer instanceof JavaBeanSerializer) {
            JavaBeanSerializer javaBeanSerializer = (JavaBeanSerializer)serializer;
            try {
                Map<String, Object> fieldValues = javaBeanSerializer.getFieldValuesMap(javaObject);
                for (Map.Entry<String, Object> entry : fieldValues.entrySet()) {
                    String key = entry.getKey();
                    if (!(key instanceof String)) continue;
                    String path = parent.equals("/") ? "/" + key : parent + "/" + key;
                    JSONPath.paths(values2, paths, path, entry.getValue(), config);
                }
            } catch (Exception e) {
                throw new JSONException("toJSON error", e);
            }
            return;
        }
    }

    public String getPath() {
        return this.path;
    }

    static int compare(Object a, Object b) {
        if (a.getClass() == b.getClass()) {
            return ((Comparable)a).compareTo(b);
        }
        Class<?> typeA = a.getClass();
        Class<?> typeB = b.getClass();
        if (typeA == BigDecimal.class) {
            if (typeB == Integer.class) {
                b = new BigDecimal((Integer)b);
            } else if (typeB == Long.class) {
                b = new BigDecimal((Long)b);
            } else if (typeB == Float.class) {
                b = new BigDecimal(((Float)b).floatValue());
            } else if (typeB == Double.class) {
                b = new BigDecimal((Double)b);
            }
        } else if (typeA == Long.class) {
            if (typeB == Integer.class) {
                b = new Long(((Integer)b).intValue());
            } else if (typeB == BigDecimal.class) {
                a = new BigDecimal((Long)a);
            } else if (typeB == Float.class) {
                a = new Float(((Long)a).longValue());
            } else if (typeB == Double.class) {
                a = new Double(((Long)a).longValue());
            }
        } else if (typeA == Integer.class) {
            if (typeB == Long.class) {
                a = new Long(((Integer)a).intValue());
            } else if (typeB == BigDecimal.class) {
                a = new BigDecimal((Integer)a);
            } else if (typeB == Float.class) {
                a = new Float(((Integer)a).intValue());
            } else if (typeB == Double.class) {
                a = new Double(((Integer)a).intValue());
            }
        } else if (typeA == Double.class) {
            if (typeB == Integer.class) {
                b = new Double(((Integer)b).intValue());
            } else if (typeB == Long.class) {
                b = new Double(((Long)b).longValue());
            } else if (typeB == Float.class) {
                b = new Double(((Float)b).floatValue());
            }
        } else if (typeA == Float.class) {
            if (typeB == Integer.class) {
                b = new Float(((Integer)b).intValue());
            } else if (typeB == Long.class) {
                b = new Float(((Long)b).longValue());
            } else if (typeB == Double.class) {
                a = new Double(((Float)a).floatValue());
            }
        }
        return ((Comparable)a).compareTo(b);
    }

    protected Object getArrayItem(Object currentObject, int index) {
        if (currentObject == null) {
            return null;
        }
        if (currentObject instanceof List) {
            List list = (List)currentObject;
            if (index >= 0) {
                if (index < list.size()) {
                    return list.get(index);
                }
                return null;
            }
            if (Math.abs(index) <= list.size()) {
                return list.get(list.size() + index);
            }
            return null;
        }
        if (currentObject.getClass().isArray()) {
            int arrayLenth = Array.getLength(currentObject);
            if (index >= 0) {
                if (index < arrayLenth) {
                    return Array.get(currentObject, index);
                }
                return null;
            }
            if (Math.abs(index) <= arrayLenth) {
                return Array.get(currentObject, arrayLenth + index);
            }
            return null;
        }
        if (currentObject instanceof Map) {
            Map map = (Map)currentObject;
            Object value = map.get(index);
            if (value == null) {
                value = map.get(Integer.toString(index));
            }
            return value;
        }
        if (currentObject instanceof Collection) {
            Collection collection = (Collection)currentObject;
            int i = 0;
            for (Object item : collection) {
                if (i == index) {
                    return item;
                }
                ++i;
            }
            return null;
        }
        if (index == 0) {
            return currentObject;
        }
        throw new UnsupportedOperationException();
    }

    public boolean setArrayItem(JSONPath path, Object currentObject, int index, Object value) {
        if (currentObject instanceof List) {
            List list = (List)currentObject;
            if (index >= 0) {
                list.set(index, value);
            } else {
                list.set(list.size() + index, value);
            }
            return true;
        }
        Class<?> clazz = currentObject.getClass();
        if (clazz.isArray()) {
            int arrayLenth = Array.getLength(currentObject);
            if (index >= 0) {
                if (index < arrayLenth) {
                    Array.set(currentObject, index, value);
                }
            } else if (Math.abs(index) <= arrayLenth) {
                Array.set(currentObject, arrayLenth + index, value);
            }
            return true;
        }
        throw new JSONPathException("unsupported set operation." + clazz);
    }

    public boolean removeArrayItem(JSONPath path, Object currentObject, int index) {
        if (currentObject instanceof List) {
            List list = (List)currentObject;
            if (index >= 0) {
                if (index >= list.size()) {
                    return false;
                }
                list.remove(index);
            } else {
                int newIndex = list.size() + index;
                if (newIndex < 0) {
                    return false;
                }
                list.remove(newIndex);
            }
            return true;
        }
        Class<?> clazz = currentObject.getClass();
        throw new JSONPathException("unsupported set operation." + clazz);
    }

    protected Collection<Object> getPropertyValues(Object currentObject) {
        if (currentObject == null) {
            return null;
        }
        Class<?> currentClass = currentObject.getClass();
        JavaBeanSerializer beanSerializer = this.getJavaBeanSerializer(currentClass);
        if (beanSerializer != null) {
            try {
                return beanSerializer.getFieldValues(currentObject);
            } catch (Exception e) {
                throw new JSONPathException("jsonpath error, path " + this.path, e);
            }
        }
        if (currentObject instanceof Map) {
            Map map = (Map)currentObject;
            return map.values();
        }
        if (currentObject instanceof Collection) {
            return (Collection)currentObject;
        }
        throw new UnsupportedOperationException();
    }

    protected void deepGetObjects(Object currentObject, List<Object> outValues) {
        Class<?> currentClass = currentObject.getClass();
        JavaBeanSerializer beanSerializer = this.getJavaBeanSerializer(currentClass);
        Collection<Object> collection = null;
        if (beanSerializer != null) {
            try {
                collection = beanSerializer.getFieldValues(currentObject);
                outValues.add(currentObject);
            } catch (Exception e) {
                throw new JSONPathException("jsonpath error, path " + this.path, e);
            }
        } else if (currentObject instanceof Map) {
            outValues.add(currentObject);
            Map map = (Map)currentObject;
            collection = map.values();
        } else if (currentObject instanceof Collection) {
            collection = (Collection)currentObject;
        }
        if (collection != null) {
            for (Object fieldValue : collection) {
                if (fieldValue == null || ParserConfig.isPrimitive2(fieldValue.getClass())) continue;
                this.deepGetObjects(fieldValue, outValues);
            }
            return;
        }
        throw new UnsupportedOperationException(currentClass.getName());
    }

    protected void deepGetPropertyValues(Object currentObject, List<Object> outValues) {
        Class<?> currentClass = currentObject.getClass();
        JavaBeanSerializer beanSerializer = this.getJavaBeanSerializer(currentClass);
        Collection<Object> collection = null;
        if (beanSerializer != null) {
            try {
                collection = beanSerializer.getFieldValues(currentObject);
            } catch (Exception e) {
                throw new JSONPathException("jsonpath error, path " + this.path, e);
            }
        } else if (currentObject instanceof Map) {
            Map map = (Map)currentObject;
            collection = map.values();
        } else if (currentObject instanceof Collection) {
            collection = (Collection)currentObject;
        }
        if (collection != null) {
            for (Object e : collection) {
                if (e == null || ParserConfig.isPrimitive2(e.getClass())) {
                    outValues.add(e);
                    continue;
                }
                this.deepGetPropertyValues(e, outValues);
            }
            return;
        }
        throw new UnsupportedOperationException(currentClass.getName());
    }

    static boolean eq(Object a, Object b) {
        if (a == b) {
            return true;
        }
        if (a == null || b == null) {
            return false;
        }
        if (a.getClass() == b.getClass()) {
            return a.equals(b);
        }
        if (a instanceof Number) {
            if (b instanceof Number) {
                return JSONPath.eqNotNull((Number)a, (Number)b);
            }
            return false;
        }
        return a.equals(b);
    }

    static boolean eqNotNull(Number a, Number b) {
        Class<?> clazzA = a.getClass();
        boolean isIntA = JSONPath.isInt(clazzA);
        Class<?> clazzB = b.getClass();
        boolean isIntB = JSONPath.isInt(clazzB);
        if (a instanceof BigDecimal) {
            BigDecimal decimalA = (BigDecimal)a;
            if (isIntB) {
                return decimalA.equals(BigDecimal.valueOf(TypeUtils.longExtractValue(b)));
            }
        }
        if (isIntA) {
            if (isIntB) {
                return a.longValue() == b.longValue();
            }
            if (b instanceof BigInteger) {
                BigInteger bigIntB = (BigInteger)a;
                BigInteger bigIntA = BigInteger.valueOf(a.longValue());
                return bigIntA.equals(bigIntB);
            }
        }
        if (isIntB && a instanceof BigInteger) {
            BigInteger bigIntA = (BigInteger)a;
            BigInteger bigIntB = BigInteger.valueOf(TypeUtils.longExtractValue(b));
            return bigIntA.equals(bigIntB);
        }
        boolean isDoubleA = JSONPath.isDouble(clazzA);
        boolean isDoubleB = JSONPath.isDouble(clazzB);
        if (isDoubleA && isDoubleB || isDoubleA && isIntB || isDoubleB && isIntA) {
            return a.doubleValue() == b.doubleValue();
        }
        return false;
    }

    protected static boolean isDouble(Class<?> clazzA) {
        return clazzA == Float.class || clazzA == Double.class;
    }

    protected static boolean isInt(Class<?> clazzA) {
        return clazzA == Byte.class || clazzA == Short.class || clazzA == Integer.class || clazzA == Long.class;
    }

    protected Object getPropertyValue(Object currentObject, String propertyName, long propertyNameHash) {
        if (currentObject == null) {
            return null;
        }
        if (currentObject instanceof String) {
            try {
                JSONObject object = (JSONObject)JSON.parse((String)currentObject, this.parserConfig);
                currentObject = object;
            } catch (Exception object) {
                // empty catch block
            }
        }
        if (currentObject instanceof Map) {
            Map map = (Map)currentObject;
            Object val = map.get(propertyName);
            if (val == null && (5614464919154503228L == propertyNameHash || -1580386065683472715L == propertyNameHash)) {
                val = map.size();
            }
            return val;
        }
        Class<?> currentClass = currentObject.getClass();
        JavaBeanSerializer beanSerializer = this.getJavaBeanSerializer(currentClass);
        if (beanSerializer != null) {
            try {
                return beanSerializer.getFieldValue(currentObject, propertyName, propertyNameHash, false);
            } catch (Exception e) {
                throw new JSONPathException("jsonpath error, path " + this.path + ", segement " + propertyName, e);
            }
        }
        if (currentObject instanceof List) {
            List list = (List)currentObject;
            if (5614464919154503228L == propertyNameHash || -1580386065683472715L == propertyNameHash) {
                return list.size();
            }
            JSONArray fieldValues = null;
            for (int i = 0; i < list.size(); ++i) {
                Object obj = list.get(i);
                if (obj == list) {
                    if (fieldValues == null) {
                        fieldValues = new JSONArray(list.size());
                    }
                    fieldValues.add(obj);
                    continue;
                }
                Object itemValue = this.getPropertyValue(obj, propertyName, propertyNameHash);
                if (itemValue instanceof Collection) {
                    Collection collection = (Collection)itemValue;
                    if (fieldValues == null) {
                        fieldValues = new JSONArray(list.size());
                    }
                    fieldValues.addAll(collection);
                    continue;
                }
                if (itemValue == null && this.ignoreNullValue) continue;
                if (fieldValues == null) {
                    fieldValues = new JSONArray(list.size());
                }
                fieldValues.add(itemValue);
            }
            if (fieldValues == null) {
                fieldValues = Collections.emptyList();
            }
            return fieldValues;
        }
        if (currentObject instanceof Object[]) {
            Object[] array = (Object[])currentObject;
            if (5614464919154503228L == propertyNameHash || -1580386065683472715L == propertyNameHash) {
                return array.length;
            }
            JSONArray fieldValues = new JSONArray(array.length);
            for (int i = 0; i < array.length; ++i) {
                Object obj = array[i];
                if (obj == array) {
                    fieldValues.add(obj);
                    continue;
                }
                Object itemValue = this.getPropertyValue(obj, propertyName, propertyNameHash);
                if (itemValue instanceof Collection) {
                    Collection collection = (Collection)itemValue;
                    fieldValues.addAll(collection);
                    continue;
                }
                if (itemValue == null && this.ignoreNullValue) continue;
                fieldValues.add(itemValue);
            }
            return fieldValues;
        }
        if (currentObject instanceof Enum) {
            long NAME = -4270347329889690746L;
            long ORDINAL = -1014497654951707614L;
            Enum e = (Enum)currentObject;
            if (-4270347329889690746L == propertyNameHash) {
                return e.name();
            }
            if (-1014497654951707614L == propertyNameHash) {
                return e.ordinal();
            }
        }
        if (currentObject instanceof Calendar) {
            long YEAR = 8963398325558730460L;
            long MONTH = -811277319855450459L;
            long DAY = -3851359326990528739L;
            long HOUR = 4647432019745535567L;
            long MINUTE = 6607618197526598121L;
            long SECOND = -6586085717218287427L;
            Calendar e = (Calendar)currentObject;
            if (8963398325558730460L == propertyNameHash) {
                return e.get(1);
            }
            if (-811277319855450459L == propertyNameHash) {
                return e.get(2);
            }
            if (-3851359326990528739L == propertyNameHash) {
                return e.get(5);
            }
            if (4647432019745535567L == propertyNameHash) {
                return e.get(11);
            }
            if (6607618197526598121L == propertyNameHash) {
                return e.get(12);
            }
            if (-6586085717218287427L == propertyNameHash) {
                return e.get(13);
            }
        }
        return null;
    }

    protected void deepScan(Object currentObject, String propertyName, List<Object> results) {
        if (currentObject == null) {
            return;
        }
        if (currentObject instanceof Map) {
            Map map = (Map)currentObject;
            for (Map.Entry entry : map.entrySet()) {
                Object val = entry.getValue();
                if (propertyName.equals(entry.getKey())) {
                    if (val instanceof Collection) {
                        results.addAll((Collection)val);
                        continue;
                    }
                    results.add(val);
                    continue;
                }
                if (val == null || ParserConfig.isPrimitive2(val.getClass())) continue;
                this.deepScan(val, propertyName, results);
            }
            return;
        }
        if (currentObject instanceof Collection) {
            for (Object next : (Collection)currentObject) {
                if (ParserConfig.isPrimitive2(next.getClass())) continue;
                this.deepScan(next, propertyName, results);
            }
            return;
        }
        Class<?> currentClass = currentObject.getClass();
        JavaBeanSerializer beanSerializer = this.getJavaBeanSerializer(currentClass);
        if (beanSerializer != null) {
            try {
                FieldSerializer fieldDeser = beanSerializer.getFieldSerializer(propertyName);
                if (fieldDeser != null) {
                    try {
                        Object val = fieldDeser.getPropertyValueDirect(currentObject);
                        results.add(val);
                    } catch (InvocationTargetException ex) {
                        throw new JSONException("getFieldValue error." + propertyName, ex);
                    } catch (IllegalAccessException ex) {
                        throw new JSONException("getFieldValue error." + propertyName, ex);
                    }
                    return;
                }
                List<Object> fieldValues = beanSerializer.getFieldValues(currentObject);
                for (Object val : fieldValues) {
                    this.deepScan(val, propertyName, results);
                }
                return;
            } catch (Exception e) {
                throw new JSONPathException("jsonpath error, path " + this.path + ", segement " + propertyName, e);
            }
        }
        if (currentObject instanceof List) {
            List list = (List)currentObject;
            for (int i = 0; i < list.size(); ++i) {
                Object val = list.get(i);
                this.deepScan(val, propertyName, results);
            }
            return;
        }
    }

    protected void deepSet(Object currentObject, String propertyName, long propertyNameHash, Object value) {
        if (currentObject == null) {
            return;
        }
        if (currentObject instanceof Map) {
            Map map = (Map)currentObject;
            if (map.containsKey(propertyName)) {
                Object val = map.get(propertyName);
                map.put(propertyName, value);
                return;
            }
            for (Object val : map.values()) {
                this.deepSet(val, propertyName, propertyNameHash, value);
            }
            return;
        }
        Class<?> currentClass = currentObject.getClass();
        JavaBeanDeserializer beanDeserializer = this.getJavaBeanDeserializer(currentClass);
        if (beanDeserializer != null) {
            try {
                FieldDeserializer fieldDeser = beanDeserializer.getFieldDeserializer(propertyName);
                if (fieldDeser != null) {
                    fieldDeser.setValue(currentObject, value);
                    return;
                }
                JavaBeanSerializer beanSerializer = this.getJavaBeanSerializer(currentClass);
                List<Object> fieldValues = beanSerializer.getObjectFieldValues(currentObject);
                for (Object val : fieldValues) {
                    this.deepSet(val, propertyName, propertyNameHash, value);
                }
                return;
            } catch (Exception e) {
                throw new JSONPathException("jsonpath error, path " + this.path + ", segement " + propertyName, e);
            }
        }
        if (currentObject instanceof List) {
            List list = (List)currentObject;
            for (int i = 0; i < list.size(); ++i) {
                Object val = list.get(i);
                this.deepSet(val, propertyName, propertyNameHash, value);
            }
            return;
        }
    }

    protected boolean setPropertyValue(Object parent, String name, long propertyNameHash, Object value) {
        if (parent instanceof Map) {
            ((Map)parent).put(name, value);
            return true;
        }
        if (parent instanceof List) {
            for (Object element : (List)parent) {
                if (element == null) continue;
                this.setPropertyValue(element, name, propertyNameHash, value);
            }
            return true;
        }
        ObjectDeserializer deserializer = this.parserConfig.getDeserializer(parent.getClass());
        JavaBeanDeserializer beanDeserializer = null;
        if (deserializer instanceof JavaBeanDeserializer) {
            beanDeserializer = (JavaBeanDeserializer)deserializer;
        }
        if (beanDeserializer != null) {
            FieldDeserializer fieldDeserializer = beanDeserializer.getFieldDeserializer(propertyNameHash);
            if (fieldDeserializer == null) {
                return false;
            }
            if (value != null && value.getClass() != fieldDeserializer.fieldInfo.fieldClass) {
                value = TypeUtils.cast(value, fieldDeserializer.fieldInfo.fieldType, this.parserConfig);
            }
            fieldDeserializer.setValue(parent, value);
            return true;
        }
        throw new UnsupportedOperationException();
    }

    protected boolean removePropertyValue(Object parent, String name, boolean deep) {
        if (parent instanceof Map) {
            boolean found;
            Object origin = ((Map)parent).remove(name);
            boolean bl = found = origin != null;
            if (deep) {
                for (Object item : ((Map)parent).values()) {
                    this.removePropertyValue(item, name, deep);
                }
            }
            return found;
        }
        ObjectDeserializer deserializer = this.parserConfig.getDeserializer(parent.getClass());
        JavaBeanDeserializer beanDeserializer = null;
        if (deserializer instanceof JavaBeanDeserializer) {
            beanDeserializer = (JavaBeanDeserializer)deserializer;
        }
        if (beanDeserializer != null) {
            FieldDeserializer fieldDeserializer = beanDeserializer.getFieldDeserializer(name);
            boolean found = false;
            if (fieldDeserializer != null) {
                fieldDeserializer.setValue(parent, null);
                found = true;
            }
            if (deep) {
                Collection<Object> propertyValues = this.getPropertyValues(parent);
                for (Object item : propertyValues) {
                    if (item == null) continue;
                    this.removePropertyValue(item, name, deep);
                }
            }
            return found;
        }
        if (deep) {
            return false;
        }
        throw new UnsupportedOperationException();
    }

    protected JavaBeanSerializer getJavaBeanSerializer(Class<?> currentClass) {
        JavaBeanSerializer beanSerializer = null;
        ObjectSerializer serializer = this.serializeConfig.getObjectWriter(currentClass);
        if (serializer instanceof JavaBeanSerializer) {
            beanSerializer = (JavaBeanSerializer)serializer;
        }
        return beanSerializer;
    }

    protected JavaBeanDeserializer getJavaBeanDeserializer(Class<?> currentClass) {
        JavaBeanDeserializer beanDeserializer = null;
        ObjectDeserializer deserializer = this.parserConfig.getDeserializer(currentClass);
        if (deserializer instanceof JavaBeanDeserializer) {
            beanDeserializer = (JavaBeanDeserializer)deserializer;
        }
        return beanDeserializer;
    }

    int evalSize(Object currentObject) {
        if (currentObject == null) {
            return -1;
        }
        if (currentObject instanceof Collection) {
            return ((Collection)currentObject).size();
        }
        if (currentObject instanceof Object[]) {
            return ((Object[])currentObject).length;
        }
        if (currentObject.getClass().isArray()) {
            return Array.getLength(currentObject);
        }
        if (currentObject instanceof Map) {
            int count = 0;
            for (Object value : ((Map)currentObject).values()) {
                if (value == null) continue;
                ++count;
            }
            return count;
        }
        JavaBeanSerializer beanSerializer = this.getJavaBeanSerializer(currentObject.getClass());
        if (beanSerializer == null) {
            return -1;
        }
        try {
            return beanSerializer.getSize(currentObject);
        } catch (Exception e) {
            throw new JSONPathException("evalSize error : " + this.path, e);
        }
    }

    Set<?> evalKeySet(Object currentObject) {
        if (currentObject == null) {
            return null;
        }
        if (currentObject instanceof Map) {
            return ((Map)currentObject).keySet();
        }
        if (currentObject instanceof Collection || currentObject instanceof Object[] || currentObject.getClass().isArray()) {
            return null;
        }
        JavaBeanSerializer beanSerializer = this.getJavaBeanSerializer(currentObject.getClass());
        if (beanSerializer == null) {
            return null;
        }
        try {
            return beanSerializer.getFieldNames(currentObject);
        } catch (Exception e) {
            throw new JSONPathException("evalKeySet error : " + this.path, e);
        }
    }

    @Override
    public String toJSONString() {
        return JSON.toJSONString(this.path);
    }

    public static Object reserveToArray(Object object, String ... paths) {
        JSONArray reserved = new JSONArray();
        if (paths == null || paths.length == 0) {
            return reserved;
        }
        for (String item : paths) {
            JSONPath path = JSONPath.compile(item);
            path.init();
            Object value = path.eval(object);
            reserved.add(value);
        }
        return reserved;
    }

    public static Object reserveToObject(Object object, String ... paths) {
        if (paths == null || paths.length == 0) {
            return object;
        }
        JSONObject reserved = new JSONObject(true);
        for (String item : paths) {
            Object value;
            JSONPath path = JSONPath.compile(item);
            path.init();
            Segment lastSegement = path.segments[path.segments.length - 1];
            if (!(lastSegement instanceof PropertySegment) || (value = path.eval(object)) == null) continue;
            path.set(reserved, value);
        }
        return reserved;
    }

    static class FilterGroup
    implements Filter {
        private boolean and;
        private List<Filter> fitlers = new ArrayList<Filter>(2);

        public FilterGroup(Filter left, Filter right, boolean and) {
            this.fitlers.add(left);
            this.fitlers.add(right);
            this.and = and;
        }

        public boolean apply(JSONPath path, Object rootObject, Object currentObject, Object item) {
            if (this.and) {
                for (Filter fitler : this.fitlers) {
                    if (fitler.apply(path, rootObject, currentObject, item)) continue;
                    return false;
                }
                return true;
            }
            for (Filter fitler : this.fitlers) {
                if (!fitler.apply(path, rootObject, currentObject, item)) continue;
                return true;
            }
            return false;
        }
    }

    static interface Filter {
        public boolean apply(JSONPath var1, Object var2, Object var3, Object var4);
    }

    public static class FilterSegment
    implements Segment {
        private final Filter filter;

        public FilterSegment(Filter filter) {
            this.filter = filter;
        }

        public Object eval(JSONPath path, Object rootObject, Object currentObject) {
            if (currentObject == null) {
                return null;
            }
            JSONArray items = new JSONArray();
            if (currentObject instanceof Iterable) {
                for (Object item : (Iterable)currentObject) {
                    if (!this.filter.apply(path, rootObject, currentObject, item)) continue;
                    items.add(item);
                }
                return items;
            }
            if (this.filter.apply(path, rootObject, currentObject, currentObject)) {
                return currentObject;
            }
            return null;
        }

        public void extract(JSONPath path, DefaultJSONParser parser, Context context) {
            Object object = parser.parse();
            context.object = this.eval(path, object, object);
        }

        public boolean remove(JSONPath path, Object rootObject, Object currentObject) {
            if (currentObject == null) {
                return false;
            }
            if (currentObject instanceof Iterable) {
                Iterator it = ((Iterable)currentObject).iterator();
                while (it.hasNext()) {
                    Object item = it.next();
                    if (!this.filter.apply(path, rootObject, currentObject, item)) continue;
                    it.remove();
                }
                return true;
            }
            return false;
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    static enum Operator {
        EQ,
        NE,
        GT,
        GE,
        LT,
        LE,
        LIKE,
        NOT_LIKE,
        RLIKE,
        NOT_RLIKE,
        IN,
        NOT_IN,
        BETWEEN,
        NOT_BETWEEN,
        And,
        Or,
        REG_MATCH;

    }

    static class RegMatchSegement
    extends PropertyFilter {
        private final Pattern pattern;
        private final Operator op;

        public RegMatchSegement(String propertyName, boolean function, Pattern pattern, Operator op) {
            super(propertyName, function);
            this.pattern = pattern;
            this.op = op;
        }

        public boolean apply(JSONPath path, Object rootObject, Object currentObject, Object item) {
            Object propertyValue = this.get(path, rootObject, item);
            if (propertyValue == null) {
                return false;
            }
            String str = propertyValue.toString();
            Matcher m3 = this.pattern.matcher(str);
            return m3.matches();
        }
    }

    static class StringOpSegement
    extends PropertyFilter {
        private final String value;
        private final Operator op;

        public StringOpSegement(String propertyName, boolean function, String value, Operator op) {
            super(propertyName, function);
            this.value = value;
            this.op = op;
        }

        public boolean apply(JSONPath path, Object rootObject, Object currentObject, Object item) {
            Object propertyValue = this.get(path, rootObject, item);
            if (this.op == Operator.EQ) {
                return this.value.equals(propertyValue);
            }
            if (this.op == Operator.NE) {
                return !this.value.equals(propertyValue);
            }
            if (propertyValue == null) {
                return false;
            }
            int compareResult = this.value.compareTo(propertyValue.toString());
            if (this.op == Operator.GE) {
                return compareResult <= 0;
            }
            if (this.op == Operator.GT) {
                return compareResult < 0;
            }
            if (this.op == Operator.LE) {
                return compareResult >= 0;
            }
            if (this.op == Operator.LT) {
                return compareResult > 0;
            }
            return false;
        }
    }

    static class RlikeSegement
    extends PropertyFilter {
        private final Pattern pattern;
        private final boolean not;

        public RlikeSegement(String propertyName, boolean function, String pattern, boolean not) {
            super(propertyName, function);
            this.pattern = Pattern.compile(pattern);
            this.not = not;
        }

        public boolean apply(JSONPath path, Object rootObject, Object currentObject, Object item) {
            Object propertyValue = this.get(path, rootObject, item);
            if (propertyValue == null) {
                return false;
            }
            String strPropertyValue = propertyValue.toString();
            Matcher m3 = this.pattern.matcher(strPropertyValue);
            boolean match = m3.matches();
            if (this.not) {
                match = !match;
            }
            return match;
        }
    }

    static class MatchSegement
    extends PropertyFilter {
        private final String startsWithValue;
        private final String endsWithValue;
        private final String[] containsValues;
        private final int minLength;
        private final boolean not;

        public MatchSegement(String propertyName, boolean function, String startsWithValue, String endsWithValue, String[] containsValues, boolean not) {
            super(propertyName, function);
            this.startsWithValue = startsWithValue;
            this.endsWithValue = endsWithValue;
            this.containsValues = containsValues;
            this.not = not;
            int len = 0;
            if (startsWithValue != null) {
                len += startsWithValue.length();
            }
            if (endsWithValue != null) {
                len += endsWithValue.length();
            }
            if (containsValues != null) {
                for (String item : containsValues) {
                    len += item.length();
                }
            }
            this.minLength = len;
        }

        public boolean apply(JSONPath path, Object rootObject, Object currentObject, Object item) {
            Object propertyValue = this.get(path, rootObject, item);
            if (propertyValue == null) {
                return false;
            }
            String strPropertyValue = propertyValue.toString();
            if (strPropertyValue.length() < this.minLength) {
                return this.not;
            }
            int start = 0;
            if (this.startsWithValue != null) {
                if (!strPropertyValue.startsWith(this.startsWithValue)) {
                    return this.not;
                }
                start += this.startsWithValue.length();
            }
            if (this.containsValues != null) {
                for (String containsValue : this.containsValues) {
                    int index = strPropertyValue.indexOf(containsValue, start);
                    if (index == -1) {
                        return this.not;
                    }
                    start = index + containsValue.length();
                }
            }
            if (this.endsWithValue != null && !strPropertyValue.endsWith(this.endsWithValue)) {
                return this.not;
            }
            return !this.not;
        }
    }

    static class RefOpSegement
    extends PropertyFilter {
        private final Segment refSgement;
        private final Operator op;

        public RefOpSegement(String propertyName, boolean function, Segment refSgement, Operator op) {
            super(propertyName, function);
            this.refSgement = refSgement;
            this.op = op;
        }

        public boolean apply(JSONPath path, Object rootObject, Object currentObject, Object item) {
            Object propertyValue = this.get(path, rootObject, item);
            if (propertyValue == null) {
                return false;
            }
            if (!(propertyValue instanceof Number)) {
                return false;
            }
            Object refValue = this.refSgement.eval(path, rootObject, rootObject);
            if (refValue instanceof Integer || refValue instanceof Long || refValue instanceof Short || refValue instanceof Byte) {
                long value = TypeUtils.longExtractValue((Number)refValue);
                if (propertyValue instanceof Integer || propertyValue instanceof Long || propertyValue instanceof Short || propertyValue instanceof Byte) {
                    long longValue = TypeUtils.longExtractValue((Number)propertyValue);
                    switch (this.op) {
                        case EQ: {
                            return longValue == value;
                        }
                        case NE: {
                            return longValue != value;
                        }
                        case GE: {
                            return longValue >= value;
                        }
                        case GT: {
                            return longValue > value;
                        }
                        case LE: {
                            return longValue <= value;
                        }
                        case LT: {
                            return longValue < value;
                        }
                    }
                } else if (propertyValue instanceof BigDecimal) {
                    BigDecimal valueDecimal = BigDecimal.valueOf(value);
                    int result = valueDecimal.compareTo((BigDecimal)propertyValue);
                    switch (this.op) {
                        case EQ: {
                            return result == 0;
                        }
                        case NE: {
                            return result != 0;
                        }
                        case GE: {
                            return 0 >= result;
                        }
                        case GT: {
                            return 0 > result;
                        }
                        case LE: {
                            return 0 <= result;
                        }
                        case LT: {
                            return 0 < result;
                        }
                    }
                    return false;
                }
            }
            throw new UnsupportedOperationException();
        }
    }

    static class DoubleOpSegement
    extends PropertyFilter {
        private final double value;
        private final Operator op;

        public DoubleOpSegement(String propertyName, boolean function, double value, Operator op) {
            super(propertyName, function);
            this.value = value;
            this.op = op;
        }

        public boolean apply(JSONPath path, Object rootObject, Object currentObject, Object item) {
            Object propertyValue = this.get(path, rootObject, item);
            if (propertyValue == null) {
                return false;
            }
            if (!(propertyValue instanceof Number)) {
                return false;
            }
            double doubleValue = ((Number)propertyValue).doubleValue();
            switch (this.op) {
                case EQ: {
                    return doubleValue == this.value;
                }
                case NE: {
                    return doubleValue != this.value;
                }
                case GE: {
                    return doubleValue >= this.value;
                }
                case GT: {
                    return doubleValue > this.value;
                }
                case LE: {
                    return doubleValue <= this.value;
                }
                case LT: {
                    return doubleValue < this.value;
                }
            }
            return false;
        }
    }

    static abstract class PropertyFilter
    implements Filter {
        static long TYPE = TypeUtils.fnv1a_64("type");
        protected final String propertyName;
        protected final long propertyNameHash;
        protected final boolean function;
        protected Segment functionExpr;

        protected PropertyFilter(String propertyName, boolean function) {
            this.propertyName = propertyName;
            this.propertyNameHash = TypeUtils.fnv1a_64(propertyName);
            this.function = function;
            if (function) {
                if (this.propertyNameHash == TYPE) {
                    this.functionExpr = TypeSegment.instance;
                } else if (this.propertyNameHash == 5614464919154503228L) {
                    this.functionExpr = SizeSegment.instance;
                } else {
                    throw new JSONPathException("unsupported funciton : " + propertyName);
                }
            }
        }

        protected Object get(JSONPath path, Object rootObject, Object currentObject) {
            if (this.functionExpr != null) {
                return this.functionExpr.eval(path, rootObject, currentObject);
            }
            return path.getPropertyValue(currentObject, this.propertyName, this.propertyNameHash);
        }
    }

    static class IntOpSegement
    extends PropertyFilter {
        private final long value;
        private final Operator op;
        private BigDecimal valueDecimal;
        private Float valueFloat;
        private Double valueDouble;

        public IntOpSegement(String propertyName, boolean function, long value, Operator op) {
            super(propertyName, function);
            this.value = value;
            this.op = op;
        }

        public boolean apply(JSONPath path, Object rootObject, Object currentObject, Object item) {
            Object propertyValue = this.get(path, rootObject, item);
            if (propertyValue == null) {
                return false;
            }
            if (!(propertyValue instanceof Number)) {
                return false;
            }
            if (propertyValue instanceof BigDecimal) {
                if (this.valueDecimal == null) {
                    this.valueDecimal = BigDecimal.valueOf(this.value);
                }
                int result = this.valueDecimal.compareTo((BigDecimal)propertyValue);
                switch (this.op) {
                    case EQ: {
                        return result == 0;
                    }
                    case NE: {
                        return result != 0;
                    }
                    case GE: {
                        return 0 >= result;
                    }
                    case GT: {
                        return 0 > result;
                    }
                    case LE: {
                        return 0 <= result;
                    }
                    case LT: {
                        return 0 < result;
                    }
                }
                return false;
            }
            if (propertyValue instanceof Float) {
                if (this.valueFloat == null) {
                    this.valueFloat = Float.valueOf(this.value);
                }
                int result = this.valueFloat.compareTo((Float)propertyValue);
                switch (this.op) {
                    case EQ: {
                        return result == 0;
                    }
                    case NE: {
                        return result != 0;
                    }
                    case GE: {
                        return 0 >= result;
                    }
                    case GT: {
                        return 0 > result;
                    }
                    case LE: {
                        return 0 <= result;
                    }
                    case LT: {
                        return 0 < result;
                    }
                }
                return false;
            }
            if (propertyValue instanceof Double) {
                if (this.valueDouble == null) {
                    this.valueDouble = this.value;
                }
                int result = this.valueDouble.compareTo((Double)propertyValue);
                switch (this.op) {
                    case EQ: {
                        return result == 0;
                    }
                    case NE: {
                        return result != 0;
                    }
                    case GE: {
                        return 0 >= result;
                    }
                    case GT: {
                        return 0 > result;
                    }
                    case LE: {
                        return 0 <= result;
                    }
                    case LT: {
                        return 0 < result;
                    }
                }
                return false;
            }
            long longValue = TypeUtils.longExtractValue((Number)propertyValue);
            switch (this.op) {
                case EQ: {
                    return longValue == this.value;
                }
                case NE: {
                    return longValue != this.value;
                }
                case GE: {
                    return longValue >= this.value;
                }
                case GT: {
                    return longValue > this.value;
                }
                case LE: {
                    return longValue <= this.value;
                }
                case LT: {
                    return longValue < this.value;
                }
            }
            return false;
        }
    }

    static class StringInSegement
    extends PropertyFilter {
        private final String[] values;
        private final boolean not;

        public StringInSegement(String propertyName, boolean function, String[] values2, boolean not) {
            super(propertyName, function);
            this.values = values2;
            this.not = not;
        }

        public boolean apply(JSONPath path, Object rootObject, Object currentObject, Object item) {
            Object propertyValue = this.get(path, rootObject, item);
            for (String value : this.values) {
                if (value == propertyValue) {
                    return !this.not;
                }
                if (value == null || !value.equals(propertyValue)) continue;
                return !this.not;
            }
            return this.not;
        }
    }

    static class IntObjInSegement
    extends PropertyFilter {
        private final Long[] values;
        private final boolean not;

        public IntObjInSegement(String propertyName, boolean function, Long[] values2, boolean not) {
            super(propertyName, function);
            this.values = values2;
            this.not = not;
        }

        public boolean apply(JSONPath path, Object rootObject, Object currentObject, Object item) {
            Object propertyValue = this.get(path, rootObject, item);
            if (propertyValue == null) {
                for (Long value : this.values) {
                    if (value != null) continue;
                    return !this.not;
                }
                return this.not;
            }
            if (propertyValue instanceof Number) {
                long longPropertyValue = TypeUtils.longExtractValue((Number)propertyValue);
                for (Long value : this.values) {
                    if (value == null || value != longPropertyValue) continue;
                    return !this.not;
                }
            }
            return this.not;
        }
    }

    static class IntBetweenSegement
    extends PropertyFilter {
        private final long startValue;
        private final long endValue;
        private final boolean not;

        public IntBetweenSegement(String propertyName, boolean function, long startValue, long endValue, boolean not) {
            super(propertyName, function);
            this.startValue = startValue;
            this.endValue = endValue;
            this.not = not;
        }

        public boolean apply(JSONPath path, Object rootObject, Object currentObject, Object item) {
            long longPropertyValue;
            Object propertyValue = this.get(path, rootObject, item);
            if (propertyValue == null) {
                return false;
            }
            if (propertyValue instanceof Number && (longPropertyValue = TypeUtils.longExtractValue((Number)propertyValue)) >= this.startValue && longPropertyValue <= this.endValue) {
                return !this.not;
            }
            return this.not;
        }
    }

    static class IntInSegement
    extends PropertyFilter {
        private final long[] values;
        private final boolean not;

        public IntInSegement(String propertyName, boolean function, long[] values2, boolean not) {
            super(propertyName, function);
            this.values = values2;
            this.not = not;
        }

        public boolean apply(JSONPath path, Object rootObject, Object currentObject, Object item) {
            Object propertyValue = this.get(path, rootObject, item);
            if (propertyValue == null) {
                return false;
            }
            if (propertyValue instanceof Number) {
                long longPropertyValue = TypeUtils.longExtractValue((Number)propertyValue);
                for (long value : this.values) {
                    if (value != longPropertyValue) continue;
                    return !this.not;
                }
            }
            return this.not;
        }
    }

    static class ValueSegment
    extends PropertyFilter {
        private final Object value;
        private boolean eq = true;

        public ValueSegment(String propertyName, boolean function, Object value, boolean eq) {
            super(propertyName, function);
            if (value == null) {
                throw new IllegalArgumentException("value is null");
            }
            this.value = value;
            this.eq = eq;
        }

        public boolean apply(JSONPath path, Object rootObject, Object currentObject, Object item) {
            Object propertyValue = this.get(path, rootObject, item);
            boolean result = this.value.equals(propertyValue);
            if (!this.eq) {
                result = !result;
            }
            return result;
        }
    }

    static class NullSegement
    extends PropertyFilter {
        public NullSegement(String propertyName, boolean function) {
            super(propertyName, function);
        }

        public boolean apply(JSONPath path, Object rootObject, Object currentObject, Object item) {
            Object propertyValue = this.get(path, rootObject, item);
            return propertyValue == null;
        }
    }

    static class NotNullSegement
    extends PropertyFilter {
        public NotNullSegement(String propertyName, boolean function) {
            super(propertyName, function);
        }

        public boolean apply(JSONPath path, Object rootObject, Object currentObject, Object item) {
            return path.getPropertyValue(item, this.propertyName, this.propertyNameHash) != null;
        }
    }

    static class RangeSegment
    implements Segment {
        private final int start;
        private final int end;
        private final int step;

        public RangeSegment(int start, int end, int step) {
            this.start = start;
            this.end = end;
            this.step = step;
        }

        public Object eval(JSONPath path, Object rootObject, Object currentObject) {
            int start;
            int size = SizeSegment.instance.eval(path, rootObject, currentObject);
            int end = this.end >= 0 ? this.end : this.end + size;
            int array_size = (end - (start = this.start >= 0 ? this.start : this.start + size)) / this.step + 1;
            if (array_size == -1) {
                return null;
            }
            ArrayList<Object> items = new ArrayList<Object>(array_size);
            for (int i = start; i <= end && i < size; i += this.step) {
                Object item = path.getArrayItem(currentObject, i);
                items.add(item);
            }
            return items;
        }

        public void extract(JSONPath path, DefaultJSONParser parser, Context context) {
            throw new UnsupportedOperationException();
        }
    }

    static class MultiIndexSegment
    implements Segment {
        private final int[] indexes;

        public MultiIndexSegment(int[] indexes) {
            this.indexes = indexes;
        }

        public Object eval(JSONPath path, Object rootObject, Object currentObject) {
            JSONArray items = new JSONArray(this.indexes.length);
            for (int i = 0; i < this.indexes.length; ++i) {
                Object item = path.getArrayItem(currentObject, this.indexes[i]);
                items.add(item);
            }
            return items;
        }

        public void extract(JSONPath path, DefaultJSONParser parser, Context context) {
            Object object;
            if (context.eval && (object = parser.parse()) instanceof List) {
                int[] indexes = new int[this.indexes.length];
                System.arraycopy(this.indexes, 0, indexes, 0, indexes.length);
                boolean noneNegative = indexes[0] >= 0;
                List list = (List)object;
                if (noneNegative) {
                    for (int i = list.size() - 1; i >= 0; --i) {
                        if (Arrays.binarySearch(indexes, i) >= 0) continue;
                        list.remove(i);
                    }
                    context.object = list;
                    return;
                }
            }
            throw new UnsupportedOperationException();
        }
    }

    static class ArrayAccessSegment
    implements Segment {
        private final int index;

        public ArrayAccessSegment(int index) {
            this.index = index;
        }

        public Object eval(JSONPath path, Object rootObject, Object currentObject) {
            return path.getArrayItem(currentObject, this.index);
        }

        public boolean setValue(JSONPath path, Object currentObject, Object value) {
            return path.setArrayItem(path, currentObject, this.index, value);
        }

        public boolean remove(JSONPath path, Object currentObject) {
            return path.removeArrayItem(path, currentObject, this.index);
        }

        public void extract(JSONPath path, DefaultJSONParser parser, Context context) {
            JSONLexerBase lexer = (JSONLexerBase)parser.lexer;
            if (lexer.seekArrayToItem(this.index) && context.eval) {
                context.object = parser.parse();
            }
        }
    }

    static class WildCardSegment
    implements Segment {
        private boolean deep;
        private boolean objectOnly;
        public static final WildCardSegment instance = new WildCardSegment(false, false);
        public static final WildCardSegment instance_deep = new WildCardSegment(true, false);
        public static final WildCardSegment instance_deep_objectOnly = new WildCardSegment(true, true);

        private WildCardSegment(boolean deep, boolean objectOnly) {
            this.deep = deep;
            this.objectOnly = objectOnly;
        }

        public Object eval(JSONPath path, Object rootObject, Object currentObject) {
            if (!this.deep) {
                return path.getPropertyValues(currentObject);
            }
            ArrayList<Object> values2 = new ArrayList<Object>();
            path.deepGetPropertyValues(currentObject, values2);
            return values2;
        }

        public void extract(JSONPath path, DefaultJSONParser parser, Context context) {
            if (context.eval) {
                Object object = parser.parse();
                if (this.deep) {
                    ArrayList<Object> values2 = new ArrayList<Object>();
                    if (this.objectOnly) {
                        path.deepGetObjects(object, values2);
                    } else {
                        path.deepGetPropertyValues(object, values2);
                    }
                    context.object = values2;
                    return;
                }
                if (object instanceof JSONObject) {
                    Collection<Object> values3 = ((JSONObject)object).values();
                    JSONArray array = new JSONArray(values3.size());
                    array.addAll(values3);
                    context.object = array;
                    return;
                }
                if (object instanceof JSONArray) {
                    context.object = object;
                    return;
                }
            }
            throw new JSONException("TODO");
        }
    }

    static class MultiPropertySegment
    implements Segment {
        private final String[] propertyNames;
        private final long[] propertyNamesHash;

        public MultiPropertySegment(String[] propertyNames) {
            this.propertyNames = propertyNames;
            this.propertyNamesHash = new long[propertyNames.length];
            for (int i = 0; i < this.propertyNamesHash.length; ++i) {
                this.propertyNamesHash[i] = TypeUtils.fnv1a_64(propertyNames[i]);
            }
        }

        public Object eval(JSONPath path, Object rootObject, Object currentObject) {
            ArrayList<Object> fieldValues = new ArrayList<Object>(this.propertyNames.length);
            for (int i = 0; i < this.propertyNames.length; ++i) {
                Object fieldValue = path.getPropertyValue(currentObject, this.propertyNames[i], this.propertyNamesHash[i]);
                fieldValues.add(fieldValue);
            }
            return fieldValues;
        }

        public void extract(JSONPath path, DefaultJSONParser parser, Context context) {
            JSONArray array;
            JSONLexerBase lexer = (JSONLexerBase)parser.lexer;
            if (context.object == null) {
                array = new JSONArray();
                context.object = array;
            } else {
                array = (JSONArray)context.object;
            }
            for (int i = array.size(); i < this.propertyNamesHash.length; ++i) {
                array.add((Object)null);
            }
            do {
                Object value;
                int index = lexer.seekObjectToField(this.propertyNamesHash);
                int matchStat = lexer.matchStat;
                if (matchStat != 3) break;
                switch (lexer.token()) {
                    case 2: {
                        value = lexer.integerValue();
                        lexer.nextToken(16);
                        break;
                    }
                    case 3: {
                        value = lexer.decimalValue();
                        lexer.nextToken(16);
                        break;
                    }
                    case 4: {
                        value = lexer.stringVal();
                        lexer.nextToken(16);
                        break;
                    }
                    default: {
                        value = parser.parse();
                    }
                }
                array.set(index, value);
            } while (lexer.token() == 16);
        }
    }

    static class PropertySegment
    implements Segment {
        private final String propertyName;
        private final long propertyNameHash;
        private final boolean deep;

        public PropertySegment(String propertyName, boolean deep) {
            this.propertyName = propertyName;
            this.propertyNameHash = TypeUtils.fnv1a_64(propertyName);
            this.deep = deep;
        }

        public Object eval(JSONPath path, Object rootObject, Object currentObject) {
            if (this.deep) {
                ArrayList<Object> results = new ArrayList<Object>();
                path.deepScan(currentObject, this.propertyName, results);
                return results;
            }
            return path.getPropertyValue(currentObject, this.propertyName, this.propertyNameHash);
        }

        public void extract(JSONPath path, DefaultJSONParser parser, Context context) {
            int matchStat;
            JSONLexerBase lexer = (JSONLexerBase)parser.lexer;
            if (this.deep && context.object == null) {
                context.object = new JSONArray();
            }
            if (lexer.token() == 14) {
                JSONArray array;
                block36: {
                    if ("*".equals(this.propertyName)) {
                        return;
                    }
                    lexer.nextToken();
                    array = this.deep ? (JSONArray)context.object : new JSONArray();
                    block19: while (true) {
                        switch (lexer.token()) {
                            case 12: {
                                if (this.deep) {
                                    this.extract(path, parser, context);
                                    break;
                                }
                                int matchStat2 = lexer.seekObjectToField(this.propertyNameHash, this.deep);
                                if (matchStat2 == 3) {
                                    Object value;
                                    switch (lexer.token()) {
                                        case 2: {
                                            value = lexer.integerValue();
                                            lexer.nextToken();
                                            break;
                                        }
                                        case 4: {
                                            value = lexer.stringVal();
                                            lexer.nextToken();
                                            break;
                                        }
                                        default: {
                                            value = parser.parse();
                                        }
                                    }
                                    array.add(value);
                                    if (lexer.token() == 13) {
                                        lexer.nextToken();
                                        continue block19;
                                    }
                                    lexer.skipObject(false);
                                    break;
                                }
                                if (matchStat2 == -1) continue block19;
                                if (this.deep) {
                                    throw new UnsupportedOperationException(lexer.info());
                                }
                                lexer.skipObject(false);
                                break;
                            }
                            case 14: {
                                if (this.deep) {
                                    this.extract(path, parser, context);
                                    break;
                                }
                                lexer.skipObject(false);
                                break;
                            }
                            case 2: 
                            case 3: 
                            case 4: 
                            case 5: 
                            case 6: 
                            case 7: 
                            case 8: {
                                lexer.nextToken();
                                break;
                            }
                        }
                        if (lexer.token() == 15) break block36;
                        if (lexer.token() != 16) break;
                        lexer.nextToken();
                    }
                    throw new JSONException("illegal json : " + lexer.info());
                }
                lexer.nextToken();
                if (!this.deep && array.size() > 0) {
                    context.object = array;
                }
                return;
            }
            if (!this.deep) {
                int matchStat3 = lexer.seekObjectToField(this.propertyNameHash, this.deep);
                if (matchStat3 == 3 && context.eval) {
                    Object value;
                    switch (lexer.token()) {
                        case 2: {
                            value = lexer.integerValue();
                            lexer.nextToken(16);
                            break;
                        }
                        case 3: {
                            value = lexer.decimalValue();
                            lexer.nextToken(16);
                            break;
                        }
                        case 4: {
                            value = lexer.stringVal();
                            lexer.nextToken(16);
                            break;
                        }
                        default: {
                            value = parser.parse();
                        }
                    }
                    if (context.eval) {
                        context.object = value;
                    }
                }
                return;
            }
            while ((matchStat = lexer.seekObjectToField(this.propertyNameHash, this.deep)) != -1) {
                if (matchStat == 3) {
                    Object value;
                    if (!context.eval) continue;
                    switch (lexer.token()) {
                        case 2: {
                            value = lexer.integerValue();
                            lexer.nextToken(16);
                            break;
                        }
                        case 3: {
                            value = lexer.decimalValue();
                            lexer.nextToken(16);
                            break;
                        }
                        case 4: {
                            value = lexer.stringVal();
                            lexer.nextToken(16);
                            break;
                        }
                        default: {
                            value = parser.parse();
                        }
                    }
                    if (!context.eval) continue;
                    if (context.object instanceof List) {
                        List list = (List)context.object;
                        if (list.size() == 0 && value instanceof List) {
                            context.object = value;
                            continue;
                        }
                        list.add(value);
                        continue;
                    }
                    context.object = value;
                    continue;
                }
                if (matchStat != 1 && matchStat != 2) continue;
                this.extract(path, parser, context);
            }
        }

        public void setValue(JSONPath path, Object parent, Object value) {
            if (this.deep) {
                path.deepSet(parent, this.propertyName, this.propertyNameHash, value);
            } else {
                path.setPropertyValue(parent, this.propertyName, this.propertyNameHash, value);
            }
        }

        public boolean remove(JSONPath path, Object parent) {
            return path.removePropertyValue(parent, this.propertyName, this.deep);
        }
    }

    static class KeySetSegment
    implements Segment {
        public static final KeySetSegment instance = new KeySetSegment();

        KeySetSegment() {
        }

        public Object eval(JSONPath path, Object rootObject, Object currentObject) {
            return path.evalKeySet(currentObject);
        }

        public void extract(JSONPath path, DefaultJSONParser parser, Context context) {
            throw new UnsupportedOperationException();
        }
    }

    static class MinSegment
    implements Segment {
        public static final MinSegment instance = new MinSegment();

        MinSegment() {
        }

        public Object eval(JSONPath path, Object rootObject, Object currentObject) {
            Object min2 = null;
            if (currentObject instanceof Collection) {
                for (Object next : (Collection)currentObject) {
                    if (next == null) continue;
                    if (min2 == null) {
                        min2 = next;
                        continue;
                    }
                    if (JSONPath.compare(min2, next) <= 0) continue;
                    min2 = next;
                }
            } else {
                throw new UnsupportedOperationException();
            }
            return min2;
        }

        public void extract(JSONPath path, DefaultJSONParser parser, Context context) {
            throw new UnsupportedOperationException();
        }
    }

    static class MaxSegment
    implements Segment {
        public static final MaxSegment instance = new MaxSegment();

        MaxSegment() {
        }

        public Object eval(JSONPath path, Object rootObject, Object currentObject) {
            Object max = null;
            if (currentObject instanceof Collection) {
                for (Object next : (Collection)currentObject) {
                    if (next == null) continue;
                    if (max == null) {
                        max = next;
                        continue;
                    }
                    if (JSONPath.compare(max, next) >= 0) continue;
                    max = next;
                }
            } else {
                throw new UnsupportedOperationException();
            }
            return max;
        }

        public void extract(JSONPath path, DefaultJSONParser parser, Context context) {
            throw new UnsupportedOperationException();
        }
    }

    static class FloorSegment
    implements Segment {
        public static final FloorSegment instance = new FloorSegment();

        FloorSegment() {
        }

        public Object eval(JSONPath path, Object rootObject, Object currentObject) {
            if (currentObject instanceof JSONArray) {
                JSONArray array = (JSONArray)((JSONArray)currentObject).clone();
                for (int i = 0; i < array.size(); ++i) {
                    Object item = array.get(i);
                    Object newItem = FloorSegment.floor(item);
                    if (newItem == item) continue;
                    array.set(i, newItem);
                }
                return array;
            }
            return FloorSegment.floor(currentObject);
        }

        private static Object floor(Object item) {
            if (item == null) {
                return null;
            }
            if (item instanceof Float) {
                return Math.floor(((Float)item).floatValue());
            }
            if (item instanceof Double) {
                return Math.floor((Double)item);
            }
            if (item instanceof BigDecimal) {
                BigDecimal decimal = (BigDecimal)item;
                return decimal.setScale(0, RoundingMode.FLOOR);
            }
            if (item instanceof Byte || item instanceof Short || item instanceof Integer || item instanceof Long || item instanceof BigInteger) {
                return item;
            }
            throw new UnsupportedOperationException();
        }

        public void extract(JSONPath path, DefaultJSONParser parser, Context context) {
            throw new UnsupportedOperationException();
        }
    }

    static class TypeSegment
    implements Segment {
        public static final TypeSegment instance = new TypeSegment();

        TypeSegment() {
        }

        public String eval(JSONPath path, Object rootObject, Object currentObject) {
            if (currentObject == null) {
                return "null";
            }
            if (currentObject instanceof Collection) {
                return "array";
            }
            if (currentObject instanceof Number) {
                return "number";
            }
            if (currentObject instanceof Boolean) {
                return "boolean";
            }
            if (currentObject instanceof String || currentObject instanceof UUID || currentObject instanceof Enum) {
                return "string";
            }
            return "object";
        }

        public void extract(JSONPath path, DefaultJSONParser parser, Context context) {
            throw new UnsupportedOperationException();
        }
    }

    static class SizeSegment
    implements Segment {
        public static final SizeSegment instance = new SizeSegment();

        SizeSegment() {
        }

        public Integer eval(JSONPath path, Object rootObject, Object currentObject) {
            return path.evalSize(currentObject);
        }

        public void extract(JSONPath path, DefaultJSONParser parser, Context context) {
            Object object = parser.parse();
            context.object = path.evalSize(object);
        }
    }

    static interface Segment {
        public Object eval(JSONPath var1, Object var2, Object var3);

        public void extract(JSONPath var1, DefaultJSONParser var2, Context var3);
    }

    static class JSONPathParser {
        private final String path;
        private int pos;
        private char ch;
        private int level;
        private boolean hasRefSegment;
        private static final String strArrayRegex = "'\\s*,\\s*'";
        private static final Pattern strArrayPatternx = Pattern.compile("'\\s*,\\s*'");

        public JSONPathParser(String path) {
            this.path = path;
            this.next();
        }

        void next() {
            this.ch = this.path.charAt(this.pos++);
        }

        char getNextChar() {
            return this.path.charAt(this.pos);
        }

        boolean isEOF() {
            return this.pos >= this.path.length();
        }

        Segment readSegement() {
            if (this.level == 0 && this.path.length() == 1) {
                if (JSONPathParser.isDigitFirst(this.ch)) {
                    int index = this.ch - 48;
                    return new ArrayAccessSegment(index);
                }
                if (this.ch >= 'a' && this.ch <= 'z' || this.ch >= 'A' && this.ch <= 'Z') {
                    return new PropertySegment(Character.toString(this.ch), false);
                }
            }
            while (!this.isEOF()) {
                this.skipWhitespace();
                if (this.ch == '$') {
                    this.next();
                    this.skipWhitespace();
                    if (this.ch != '?') continue;
                    return new FilterSegment((Filter)this.parseArrayAccessFilter(false));
                }
                if (this.ch == '.' || this.ch == '/') {
                    char c0 = this.ch;
                    boolean deep = false;
                    this.next();
                    if (c0 == '.' && this.ch == '.') {
                        this.next();
                        deep = true;
                        if (this.path.length() > this.pos + 3 && this.ch == '[' && this.path.charAt(this.pos) == '*' && this.path.charAt(this.pos + 1) == ']' && this.path.charAt(this.pos + 2) == '.') {
                            this.next();
                            this.next();
                            this.next();
                            this.next();
                        }
                    }
                    if (this.ch == '*' || deep && this.ch == '[') {
                        boolean objectOnly;
                        boolean bl = objectOnly = this.ch == '[';
                        if (!this.isEOF()) {
                            this.next();
                        }
                        if (deep) {
                            if (objectOnly) {
                                return WildCardSegment.instance_deep_objectOnly;
                            }
                            return WildCardSegment.instance_deep;
                        }
                        return WildCardSegment.instance;
                    }
                    if (JSONPathParser.isDigitFirst(this.ch)) {
                        return this.parseArrayAccess(false);
                    }
                    String propertyName = this.readName();
                    if (this.ch == '(') {
                        this.next();
                        if (this.ch == ')') {
                            if (!this.isEOF()) {
                                this.next();
                            }
                            if ("size".equals(propertyName) || "length".equals(propertyName)) {
                                return SizeSegment.instance;
                            }
                            if ("max".equals(propertyName)) {
                                return MaxSegment.instance;
                            }
                            if ("min".equals(propertyName)) {
                                return MinSegment.instance;
                            }
                            if ("keySet".equals(propertyName)) {
                                return KeySetSegment.instance;
                            }
                            if ("type".equals(propertyName)) {
                                return TypeSegment.instance;
                            }
                            if ("floor".equals(propertyName)) {
                                return FloorSegment.instance;
                            }
                            throw new JSONPathException("not support jsonpath : " + this.path);
                        }
                        throw new JSONPathException("not support jsonpath : " + this.path);
                    }
                    return new PropertySegment(propertyName, deep);
                }
                if (this.ch == '[') {
                    return this.parseArrayAccess(true);
                }
                if (this.level == 0) {
                    String propertyName = this.readName();
                    return new PropertySegment(propertyName, false);
                }
                if (this.ch == '?') {
                    return new FilterSegment((Filter)this.parseArrayAccessFilter(false));
                }
                throw new JSONPathException("not support jsonpath : " + this.path);
            }
            return null;
        }

        public final void skipWhitespace() {
            while (this.ch <= ' ' && (this.ch == ' ' || this.ch == '\r' || this.ch == '\n' || this.ch == '\t' || this.ch == '\f' || this.ch == '\b')) {
                this.next();
            }
        }

        Segment parseArrayAccess(boolean acceptBracket) {
            Object object = this.parseArrayAccessFilter(acceptBracket);
            if (object instanceof Segment) {
                return (Segment)object;
            }
            return new FilterSegment((Filter)object);
        }

        Object parseArrayAccessFilter(boolean acceptBracket) {
            if (acceptBracket) {
                this.accept('[');
            }
            boolean predicateFlag = false;
            int lparanCount = 0;
            if (this.ch == '?') {
                this.next();
                this.accept('(');
                ++lparanCount;
                while (this.ch == '(') {
                    this.next();
                    ++lparanCount;
                }
                predicateFlag = true;
            }
            this.skipWhitespace();
            if (predicateFlag || IOUtils.firstIdentifier(this.ch) || Character.isJavaIdentifierStart(this.ch) || this.ch == '\\' || this.ch == '@') {
                String name;
                boolean self = false;
                if (this.ch == '@') {
                    this.next();
                    this.accept('.');
                    self = true;
                }
                String propertyName = this.readName();
                this.skipWhitespace();
                if (predicateFlag && this.ch == ')') {
                    this.next();
                    Filter filter = new NotNullSegement(propertyName, false);
                    while (this.ch == ' ') {
                        this.next();
                    }
                    if (this.ch == '&' || this.ch == '|') {
                        filter = this.filterRest(filter);
                    }
                    if (acceptBracket) {
                        this.accept(']');
                    }
                    return filter;
                }
                if (acceptBracket && this.ch == ']') {
                    if (this.isEOF() && propertyName.equals("last")) {
                        return new MultiIndexSegment(new int[]{-1});
                    }
                    this.next();
                    Filter filter = new NotNullSegement(propertyName, false);
                    while (this.ch == ' ') {
                        this.next();
                    }
                    if (this.ch == '&' || this.ch == '|') {
                        filter = this.filterRest(filter);
                    }
                    this.accept(')');
                    if (predicateFlag) {
                        this.accept(')');
                    }
                    if (acceptBracket) {
                        this.accept(']');
                    }
                    return filter;
                }
                boolean function = false;
                this.skipWhitespace();
                if (this.ch == '(') {
                    this.next();
                    this.accept(')');
                    this.skipWhitespace();
                    function = true;
                }
                Operator op = this.readOp();
                this.skipWhitespace();
                if (op == Operator.BETWEEN || op == Operator.NOT_BETWEEN) {
                    boolean not = op == Operator.NOT_BETWEEN;
                    Object startValue = this.readValue();
                    String name2 = this.readName();
                    if (!"and".equalsIgnoreCase(name2)) {
                        throw new JSONPathException(this.path);
                    }
                    Object endValue = this.readValue();
                    if (startValue == null || endValue == null) {
                        throw new JSONPathException(this.path);
                    }
                    if (JSONPath.isInt(startValue.getClass()) && JSONPath.isInt(endValue.getClass())) {
                        IntBetweenSegement filter = new IntBetweenSegement(propertyName, function, TypeUtils.longExtractValue((Number)startValue), TypeUtils.longExtractValue((Number)endValue), not);
                        return filter;
                    }
                    throw new JSONPathException(this.path);
                }
                if (op == Operator.IN || op == Operator.NOT_IN) {
                    Object[] values2;
                    boolean not = op == Operator.NOT_IN;
                    this.accept('(');
                    JSONArray valueList = new JSONArray();
                    Object value = this.readValue();
                    valueList.add(value);
                    while (true) {
                        this.skipWhitespace();
                        if (this.ch != ',') break;
                        this.next();
                        value = this.readValue();
                        valueList.add(value);
                    }
                    boolean isInt = true;
                    boolean isIntObj = true;
                    boolean isString = true;
                    for (Object item : valueList) {
                        if (item == null) {
                            if (!isInt) continue;
                            isInt = false;
                            continue;
                        }
                        Class<?> clazz = item.getClass();
                        if (isInt && clazz != Byte.class && clazz != Short.class && clazz != Integer.class && clazz != Long.class) {
                            isInt = false;
                            isIntObj = false;
                        }
                        if (!isString || clazz == String.class) continue;
                        isString = false;
                    }
                    if (valueList.size() == 1 && valueList.get(0) == null) {
                        Filter filter = not ? new NotNullSegement(propertyName, function) : new NullSegement(propertyName, function);
                        while (this.ch == ' ') {
                            this.next();
                        }
                        if (this.ch == '&' || this.ch == '|') {
                            filter = this.filterRest(filter);
                        }
                        this.accept(')');
                        if (predicateFlag) {
                            this.accept(')');
                        }
                        if (acceptBracket) {
                            this.accept(']');
                        }
                        return filter;
                    }
                    if (isInt) {
                        if (valueList.size() == 1) {
                            long value2 = TypeUtils.longExtractValue((Number)valueList.get(0));
                            Operator intOp = not ? Operator.NE : Operator.EQ;
                            Filter filter = new IntOpSegement(propertyName, function, value2, intOp);
                            while (this.ch == ' ') {
                                this.next();
                            }
                            if (this.ch == '&' || this.ch == '|') {
                                filter = this.filterRest(filter);
                            }
                            this.accept(')');
                            if (predicateFlag) {
                                this.accept(')');
                            }
                            if (acceptBracket) {
                                this.accept(']');
                            }
                            return filter;
                        }
                        values2 = new long[valueList.size()];
                        for (int i = 0; i < values2.length; ++i) {
                            values2[i] = TypeUtils.longExtractValue((Number)valueList.get(i));
                        }
                        Filter filter = new IntInSegement(propertyName, function, (long[])values2, not);
                        while (this.ch == ' ') {
                            this.next();
                        }
                        if (this.ch == '&' || this.ch == '|') {
                            filter = this.filterRest(filter);
                        }
                        this.accept(')');
                        if (predicateFlag) {
                            this.accept(')');
                        }
                        if (acceptBracket) {
                            this.accept(']');
                        }
                        return filter;
                    }
                    if (isString) {
                        if (valueList.size() == 1) {
                            String value3 = (String)valueList.get(0);
                            Operator intOp = not ? Operator.NE : Operator.EQ;
                            Filter filter = new StringOpSegement(propertyName, function, value3, intOp);
                            while (this.ch == ' ') {
                                this.next();
                            }
                            if (this.ch == '&' || this.ch == '|') {
                                filter = this.filterRest(filter);
                            }
                            this.accept(')');
                            if (predicateFlag) {
                                this.accept(')');
                            }
                            if (acceptBracket) {
                                this.accept(']');
                            }
                            return filter;
                        }
                        values2 = new String[valueList.size()];
                        valueList.toArray(values2);
                        Filter filter = new StringInSegement(propertyName, function, (String[])values2, not);
                        while (this.ch == ' ') {
                            this.next();
                        }
                        if (this.ch == '&' || this.ch == '|') {
                            filter = this.filterRest(filter);
                        }
                        this.accept(')');
                        if (predicateFlag) {
                            this.accept(')');
                        }
                        if (acceptBracket) {
                            this.accept(']');
                        }
                        return filter;
                    }
                    if (isIntObj) {
                        values2 = new Long[valueList.size()];
                        for (int i = 0; i < values2.length; ++i) {
                            Number item = (Number)valueList.get(i);
                            if (item == null) continue;
                            values2[i] = TypeUtils.longExtractValue(item);
                        }
                        Filter filter = new IntObjInSegement(propertyName, function, (Long[])values2, not);
                        while (this.ch == ' ') {
                            this.next();
                        }
                        if (this.ch == '&' || this.ch == '|') {
                            filter = this.filterRest(filter);
                        }
                        this.accept(')');
                        if (predicateFlag) {
                            this.accept(')');
                        }
                        if (acceptBracket) {
                            this.accept(']');
                        }
                        return filter;
                    }
                    throw new UnsupportedOperationException();
                }
                if (this.ch == '\'' || this.ch == '\"') {
                    String strValue = this.readString();
                    Filter filter = null;
                    if (op == Operator.RLIKE) {
                        filter = new RlikeSegement(propertyName, function, strValue, false);
                    } else if (op == Operator.NOT_RLIKE) {
                        filter = new RlikeSegement(propertyName, function, strValue, true);
                    } else if (op == Operator.LIKE || op == Operator.NOT_LIKE) {
                        while (strValue.indexOf("%%") != -1) {
                            strValue = strValue.replaceAll("%%", "%");
                        }
                        boolean not = op == Operator.NOT_LIKE;
                        int p0 = strValue.indexOf(37);
                        if (p0 == -1) {
                            op = op == Operator.LIKE ? Operator.EQ : Operator.NE;
                            filter = new StringOpSegement(propertyName, function, strValue, op);
                        } else {
                            String[] items = strValue.split("%");
                            String startsWithValue = null;
                            String endsWithValue = null;
                            String[] containsValues = null;
                            if (p0 == 0) {
                                if (strValue.charAt(strValue.length() - 1) == '%') {
                                    containsValues = new String[items.length - 1];
                                    System.arraycopy(items, 1, containsValues, 0, containsValues.length);
                                } else {
                                    endsWithValue = items[items.length - 1];
                                    if (items.length > 2) {
                                        containsValues = new String[items.length - 2];
                                        System.arraycopy(items, 1, containsValues, 0, containsValues.length);
                                    }
                                }
                            } else if (strValue.charAt(strValue.length() - 1) == '%') {
                                if (items.length == 1) {
                                    startsWithValue = items[0];
                                } else {
                                    containsValues = items;
                                }
                            } else if (items.length == 1) {
                                startsWithValue = items[0];
                            } else if (items.length == 2) {
                                startsWithValue = items[0];
                                endsWithValue = items[1];
                            } else {
                                startsWithValue = items[0];
                                endsWithValue = items[items.length - 1];
                                containsValues = new String[items.length - 2];
                                System.arraycopy(items, 1, containsValues, 0, containsValues.length);
                            }
                            filter = new MatchSegement(propertyName, function, startsWithValue, endsWithValue, containsValues, not);
                        }
                    } else {
                        filter = new StringOpSegement(propertyName, function, strValue, op);
                    }
                    while (this.ch == ' ') {
                        this.next();
                    }
                    if (this.ch == '&' || this.ch == '|') {
                        filter = this.filterRest(filter);
                    }
                    if (predicateFlag) {
                        this.accept(')');
                    }
                    if (acceptBracket) {
                        this.accept(']');
                    }
                    return filter;
                }
                if (JSONPathParser.isDigitFirst(this.ch)) {
                    long value = this.readLongValue();
                    double doubleValue = 0.0;
                    if (this.ch == '.') {
                        doubleValue = this.readDoubleValue(value);
                    }
                    Filter filter = doubleValue == 0.0 ? new IntOpSegement(propertyName, function, value, op) : new DoubleOpSegement(propertyName, function, doubleValue, op);
                    while (this.ch == ' ') {
                        this.next();
                    }
                    if (lparanCount > 1 && this.ch == ')') {
                        this.next();
                        --lparanCount;
                    }
                    if (this.ch == '&' || this.ch == '|') {
                        filter = this.filterRest(filter);
                    }
                    if (predicateFlag) {
                        --lparanCount;
                        this.accept(')');
                    }
                    if (acceptBracket) {
                        this.accept(']');
                    }
                    return filter;
                }
                if (this.ch == '$') {
                    Segment segment = this.readSegement();
                    RefOpSegement filter = new RefOpSegement(propertyName, function, segment, op);
                    this.hasRefSegment = true;
                    while (this.ch == ' ') {
                        this.next();
                    }
                    if (predicateFlag) {
                        this.accept(')');
                    }
                    if (acceptBracket) {
                        this.accept(']');
                    }
                    return filter;
                }
                if (this.ch == '/') {
                    int flags = 0;
                    StringBuilder regBuf = new StringBuilder();
                    while (true) {
                        this.next();
                        if (this.ch == '/') {
                            this.next();
                            if (this.ch != 'i') break;
                            this.next();
                            flags |= 2;
                            break;
                        }
                        if (this.ch == '\\') {
                            this.next();
                            regBuf.append(this.ch);
                            continue;
                        }
                        regBuf.append(this.ch);
                    }
                    Pattern pattern = Pattern.compile(regBuf.toString(), flags);
                    RegMatchSegement filter = new RegMatchSegement(propertyName, function, pattern, op);
                    if (predicateFlag) {
                        this.accept(')');
                    }
                    if (acceptBracket) {
                        this.accept(']');
                    }
                    return filter;
                }
                if (this.ch == 'n') {
                    String name3 = this.readName();
                    if ("null".equals(name3)) {
                        Filter filter = null;
                        if (op == Operator.EQ) {
                            filter = new NullSegement(propertyName, function);
                        } else if (op == Operator.NE) {
                            filter = new NotNullSegement(propertyName, function);
                        }
                        if (filter != null) {
                            while (this.ch == ' ') {
                                this.next();
                            }
                            if (this.ch == '&' || this.ch == '|') {
                                filter = this.filterRest(filter);
                            }
                        }
                        if (predicateFlag) {
                            this.accept(')');
                        }
                        this.accept(']');
                        if (filter != null) {
                            return filter;
                        }
                        throw new UnsupportedOperationException();
                    }
                } else if (this.ch == 't') {
                    String name4 = this.readName();
                    if ("true".equals(name4)) {
                        Filter filter = null;
                        if (op == Operator.EQ) {
                            filter = new ValueSegment(propertyName, function, Boolean.TRUE, true);
                        } else if (op == Operator.NE) {
                            filter = new ValueSegment(propertyName, function, Boolean.TRUE, false);
                        }
                        if (filter != null) {
                            while (this.ch == ' ') {
                                this.next();
                            }
                            if (this.ch == '&' || this.ch == '|') {
                                filter = this.filterRest(filter);
                            }
                        }
                        if (predicateFlag) {
                            this.accept(')');
                        }
                        this.accept(']');
                        if (filter != null) {
                            return filter;
                        }
                        throw new UnsupportedOperationException();
                    }
                } else if (this.ch == 'f' && "false".equals(name = this.readName())) {
                    Filter filter = null;
                    if (op == Operator.EQ) {
                        filter = new ValueSegment(propertyName, function, Boolean.FALSE, true);
                    } else if (op == Operator.NE) {
                        filter = new ValueSegment(propertyName, function, Boolean.FALSE, false);
                    }
                    if (filter != null) {
                        while (this.ch == ' ') {
                            this.next();
                        }
                        if (this.ch == '&' || this.ch == '|') {
                            filter = this.filterRest(filter);
                        }
                    }
                    if (predicateFlag) {
                        this.accept(')');
                    }
                    this.accept(']');
                    if (filter != null) {
                        return filter;
                    }
                    throw new UnsupportedOperationException();
                }
                throw new UnsupportedOperationException();
            }
            int start = this.pos - 1;
            char startCh = this.ch;
            while (this.ch != ']' && this.ch != '/' && !this.isEOF() && (this.ch != '.' || predicateFlag || predicateFlag || startCh == '\'')) {
                if (this.ch == '\\') {
                    this.next();
                }
                this.next();
            }
            int end = acceptBracket ? this.pos - 1 : (this.ch == '/' || this.ch == '.' ? this.pos - 1 : this.pos);
            String text = this.path.substring(start, end);
            if (text.indexOf(92) != 0) {
                StringBuilder buf = new StringBuilder(text.length());
                for (int i = 0; i < text.length(); ++i) {
                    char c2;
                    char ch = text.charAt(i);
                    if (ch == '\\' && i < text.length() - 1 && ((c2 = text.charAt(i + 1)) == '@' || ch == '\\' || ch == '\"')) {
                        buf.append(c2);
                        ++i;
                        continue;
                    }
                    buf.append(ch);
                }
                text = buf.toString();
            }
            if (text.indexOf("\\.") != -1) {
                String propName;
                if (startCh == '\'' && text.length() > 2 && text.charAt(text.length() - 1) == startCh) {
                    propName = text.substring(1, text.length() - 1);
                } else {
                    propName = text.replaceAll("\\\\\\.", "\\.");
                    if (propName.indexOf("\\-") != -1) {
                        propName = propName.replaceAll("\\\\-", "-");
                    }
                }
                if (predicateFlag) {
                    this.accept(')');
                }
                return new PropertySegment(propName, false);
            }
            Segment segment = this.buildArraySegement(text);
            if (acceptBracket && !this.isEOF()) {
                this.accept(']');
            }
            return segment;
        }

        Filter filterRest(Filter filter) {
            boolean and;
            boolean bl = and = this.ch == '&';
            if (this.ch == '&' && this.getNextChar() == '&' || this.ch == '|' && this.getNextChar() == '|') {
                this.next();
                this.next();
                boolean paren = false;
                if (this.ch == '(') {
                    paren = true;
                    this.next();
                }
                while (this.ch == ' ') {
                    this.next();
                }
                Filter right = (Filter)this.parseArrayAccessFilter(false);
                filter = new FilterGroup(filter, right, and);
                if (paren && this.ch == ')') {
                    this.next();
                }
            }
            return filter;
        }

        protected long readLongValue() {
            int beginIndex = this.pos - 1;
            if (this.ch == '+' || this.ch == '-') {
                this.next();
            }
            while (this.ch >= '0' && this.ch <= '9') {
                this.next();
            }
            int endIndex = this.pos - 1;
            String text = this.path.substring(beginIndex, endIndex);
            long value = Long.parseLong(text);
            return value;
        }

        protected double readDoubleValue(long longValue) {
            int beginIndex = this.pos - 1;
            this.next();
            while (this.ch >= '0' && this.ch <= '9') {
                this.next();
            }
            int endIndex = this.pos - 1;
            String text = this.path.substring(beginIndex, endIndex);
            double value = Double.parseDouble(text);
            return value += (double)longValue;
        }

        protected Object readValue() {
            this.skipWhitespace();
            if (JSONPathParser.isDigitFirst(this.ch)) {
                return this.readLongValue();
            }
            if (this.ch == '\"' || this.ch == '\'') {
                return this.readString();
            }
            if (this.ch == 'n') {
                String name = this.readName();
                if ("null".equals(name)) {
                    return null;
                }
                throw new JSONPathException(this.path);
            }
            throw new UnsupportedOperationException();
        }

        static boolean isDigitFirst(char ch) {
            return ch == '-' || ch == '+' || ch >= '0' && ch <= '9';
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        protected Operator readOp() {
            Operator op = null;
            if (this.ch == '=') {
                this.next();
                if (this.ch == '~') {
                    this.next();
                    op = Operator.REG_MATCH;
                } else if (this.ch == '=') {
                    this.next();
                    op = Operator.EQ;
                } else {
                    op = Operator.EQ;
                }
            } else if (this.ch == '!') {
                this.next();
                this.accept('=');
                op = Operator.NE;
            } else if (this.ch == '<') {
                this.next();
                if (this.ch == '=') {
                    this.next();
                    op = Operator.LE;
                } else {
                    op = Operator.LT;
                }
            } else if (this.ch == '>') {
                this.next();
                if (this.ch == '=') {
                    this.next();
                    op = Operator.GE;
                } else {
                    op = Operator.GT;
                }
            }
            if (op != null) return op;
            String name = this.readName();
            if ("not".equalsIgnoreCase(name)) {
                this.skipWhitespace();
                name = this.readName();
                if ("like".equalsIgnoreCase(name)) {
                    return Operator.NOT_LIKE;
                }
                if ("rlike".equalsIgnoreCase(name)) {
                    return Operator.NOT_RLIKE;
                }
                if ("in".equalsIgnoreCase(name)) {
                    return Operator.NOT_IN;
                }
                if (!"between".equalsIgnoreCase(name)) throw new UnsupportedOperationException();
                return Operator.NOT_BETWEEN;
            }
            if ("nin".equalsIgnoreCase(name)) {
                return Operator.NOT_IN;
            }
            if ("like".equalsIgnoreCase(name)) {
                return Operator.LIKE;
            }
            if ("rlike".equalsIgnoreCase(name)) {
                return Operator.RLIKE;
            }
            if ("in".equalsIgnoreCase(name)) {
                return Operator.IN;
            }
            if (!"between".equalsIgnoreCase(name)) throw new UnsupportedOperationException();
            return Operator.BETWEEN;
        }

        String readName() {
            this.skipWhitespace();
            if (this.ch != '\\' && !Character.isJavaIdentifierStart(this.ch)) {
                throw new JSONPathException("illeal jsonpath syntax. " + this.path);
            }
            StringBuilder buf = new StringBuilder();
            while (!this.isEOF()) {
                if (this.ch == '\\') {
                    this.next();
                    buf.append(this.ch);
                    if (this.isEOF()) {
                        return buf.toString();
                    }
                    this.next();
                    continue;
                }
                boolean identifierFlag = Character.isJavaIdentifierPart(this.ch);
                if (!identifierFlag) break;
                buf.append(this.ch);
                this.next();
            }
            if (this.isEOF() && Character.isJavaIdentifierPart(this.ch)) {
                buf.append(this.ch);
            }
            return buf.toString();
        }

        String readString() {
            char quoate = this.ch;
            this.next();
            int beginIndex = this.pos - 1;
            while (this.ch != quoate && !this.isEOF()) {
                this.next();
            }
            String strValue = this.path.substring(beginIndex, this.isEOF() ? this.pos : this.pos - 1);
            this.accept(quoate);
            return strValue;
        }

        void accept(char expect) {
            if (this.ch == ' ') {
                this.next();
            }
            if (this.ch != expect) {
                throw new JSONPathException("expect '" + expect + ", but '" + this.ch + "'");
            }
            if (!this.isEOF()) {
                this.next();
            }
        }

        public Segment[] explain() {
            Segment segment;
            if (this.path == null || this.path.length() == 0) {
                throw new IllegalArgumentException();
            }
            Segment[] segments = new Segment[8];
            while ((segment = this.readSegement()) != null) {
                PropertySegment propertySegment;
                if (segment instanceof PropertySegment && !(propertySegment = (PropertySegment)segment).deep && propertySegment.propertyName.equals("*")) continue;
                if (this.level == segments.length) {
                    Segment[] t = new Segment[this.level * 3 / 2];
                    System.arraycopy(segments, 0, t, 0, this.level);
                    segments = t;
                }
                segments[this.level++] = segment;
            }
            if (this.level == segments.length) {
                return segments;
            }
            Segment[] result = new Segment[this.level];
            System.arraycopy(segments, 0, result, 0, this.level);
            return result;
        }

        Segment buildArraySegement(String indexText) {
            int indexTextLen = indexText.length();
            char firstChar = indexText.charAt(0);
            char lastChar = indexText.charAt(indexTextLen - 1);
            int commaIndex = indexText.indexOf(44);
            if (indexText.length() > 2 && firstChar == '\'' && lastChar == '\'') {
                String propertyName = indexText.substring(1, indexTextLen - 1);
                if (commaIndex == -1 || !strArrayPatternx.matcher(indexText).find()) {
                    return new PropertySegment(propertyName, false);
                }
                String[] propertyNames = propertyName.split(strArrayRegex);
                return new MultiPropertySegment(propertyNames);
            }
            int colonIndex = indexText.indexOf(58);
            if (commaIndex == -1 && colonIndex == -1) {
                if (TypeUtils.isNumber(indexText)) {
                    try {
                        int index = Integer.parseInt(indexText);
                        return new ArrayAccessSegment(index);
                    } catch (NumberFormatException ex) {
                        return new PropertySegment(indexText, false);
                    }
                }
                if (indexText.charAt(0) == '\"' && indexText.charAt(indexText.length() - 1) == '\"') {
                    indexText = indexText.substring(1, indexText.length() - 1);
                }
                return new PropertySegment(indexText, false);
            }
            if (commaIndex != -1) {
                String[] indexesText = indexText.split(",");
                int[] indexes = new int[indexesText.length];
                for (int i = 0; i < indexesText.length; ++i) {
                    indexes[i] = Integer.parseInt(indexesText[i]);
                }
                return new MultiIndexSegment(indexes);
            }
            if (colonIndex != -1) {
                String[] indexesText = indexText.split(":");
                int[] indexes = new int[indexesText.length];
                for (int i = 0; i < indexesText.length; ++i) {
                    String str = indexesText[i];
                    if (str.length() == 0) {
                        if (i == 0) {
                            indexes[i] = 0;
                            continue;
                        }
                        throw new UnsupportedOperationException();
                    }
                    indexes[i] = Integer.parseInt(str);
                }
                int start = indexes[0];
                int end = indexes.length > 1 ? indexes[1] : -1;
                int step = indexes.length == 3 ? indexes[2] : 1;
                if (end >= 0 && end < start) {
                    throw new UnsupportedOperationException("end must greater than or equals start. start " + start + ",  end " + end);
                }
                if (step <= 0) {
                    throw new UnsupportedOperationException("step must greater than zero : " + step);
                }
                return new RangeSegment(start, end, step);
            }
            throw new UnsupportedOperationException();
        }
    }

    private static class Context {
        final Context parent;
        final boolean eval;
        Object object;

        public Context(Context parent, boolean eval) {
            this.parent = parent;
            this.eval = eval;
        }
    }
}

