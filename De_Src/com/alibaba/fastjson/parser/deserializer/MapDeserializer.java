/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.JSONToken;
import com.alibaba.fastjson.parser.ParseContext;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.parser.deserializer.ContextObjectDeserializer;
import com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class MapDeserializer
extends ContextObjectDeserializer
implements ObjectDeserializer {
    public static MapDeserializer instance = new MapDeserializer();

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public <T> T deserialze(DefaultJSONParser parser, Type type, Object fieldName, String format, int features) {
        if (type == JSONObject.class && parser.getFieldTypeResolver() == null) {
            return (T)parser.parseObject();
        }
        JSONLexer lexer = parser.lexer;
        if (lexer.token() == 8) {
            lexer.nextToken(16);
            return null;
        }
        boolean unmodifiableMap = type instanceof Class && "java.util.Collections$UnmodifiableMap".equals(((Class)type).getName());
        Map<Object, Object> map = (lexer.getFeatures() & Feature.OrderedField.mask) != 0 ? this.createMap(type, lexer.getFeatures()) : this.createMap(type);
        ParseContext context = parser.getContext();
        try {
            parser.setContext(context, map, fieldName);
            Map t = this.deserialze(parser, type, fieldName, map, features);
            if (unmodifiableMap) {
                t = Collections.unmodifiableMap(t);
            }
            Map map2 = t;
            return (T)map2;
        } finally {
            parser.setContext(context);
        }
    }

    protected Object deserialze(DefaultJSONParser parser, Type type, Object fieldName, Map map) {
        return this.deserialze(parser, type, fieldName, map, 0);
    }

    protected Object deserialze(DefaultJSONParser parser, Type type, Object fieldName, Map map, int features) {
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType)type;
            Type keyType = parameterizedType.getActualTypeArguments()[0];
            Object valueType = null;
            valueType = map.getClass().getName().equals("org.springframework.util.LinkedMultiValueMap") ? List.class : parameterizedType.getActualTypeArguments()[1];
            if (String.class == keyType) {
                return MapDeserializer.parseMap(parser, map, (Type)valueType, fieldName, features);
            }
            return MapDeserializer.parseMap(parser, map, keyType, (Type)valueType, fieldName);
        }
        return parser.parseObject(map, fieldName);
    }

    public static Map parseMap(DefaultJSONParser parser, Map<String, Object> map, Type valueType, Object fieldName) {
        return MapDeserializer.parseMap(parser, map, valueType, fieldName, 0);
    }

    /*
     * WARNING - void declaration
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static Map parseMap(DefaultJSONParser parser, Map<String, Object> map, Type valueType, Object fieldName, int features) {
        JSONLexer lexer = parser.lexer;
        int token = lexer.token();
        if (token != 12) {
            if (token == 4) {
                String stringVal = lexer.stringVal();
                if (stringVal.length() == 0) return null;
                if (stringVal.equals("null")) {
                    return null;
                }
            }
            String msg = "syntax error, expect {, actual " + lexer.tokenName();
            if (fieldName instanceof String) {
                msg = msg + ", fieldName ";
                msg = msg + fieldName;
            }
            msg = msg + ", ";
            msg = msg + lexer.info();
            if (token == 4) throw new JSONException(msg);
            JSONArray array = new JSONArray();
            parser.parseArray(array, fieldName);
            if (array.size() != 1) throw new JSONException(msg);
            Object first = array.get(0);
            if (!(first instanceof JSONObject)) throw new JSONException(msg);
            return (JSONObject)first;
        }
        ParseContext context = parser.getContext();
        try {
            int i = 0;
            while (true) {
                block43: {
                    Object value;
                    String key;
                    lexer.skipWhitespace();
                    char ch = lexer.getCurrent();
                    if (lexer.isEnabled(Feature.AllowArbitraryCommas)) {
                        while (ch == ',') {
                            lexer.next();
                            lexer.skipWhitespace();
                            ch = lexer.getCurrent();
                        }
                    }
                    if (ch == '\"') {
                        key = lexer.scanSymbol(parser.getSymbolTable(), '\"');
                        lexer.skipWhitespace();
                        ch = lexer.getCurrent();
                        if (ch != ':') {
                            throw new JSONException("expect ':' at " + lexer.pos());
                        }
                    } else {
                        if (ch == '}') {
                            lexer.next();
                            lexer.resetStringPosition();
                            lexer.nextToken(16);
                            Map<String, Object> map2 = map;
                            return map2;
                        }
                        if (ch == '\'') {
                            if (!lexer.isEnabled(Feature.AllowSingleQuotes)) {
                                throw new JSONException("syntax error");
                            }
                            key = lexer.scanSymbol(parser.getSymbolTable(), '\'');
                            lexer.skipWhitespace();
                            ch = lexer.getCurrent();
                            if (ch != ':') {
                                throw new JSONException("expect ':' at " + lexer.pos());
                            }
                        } else {
                            if (!lexer.isEnabled(Feature.AllowUnQuotedFieldNames)) {
                                throw new JSONException("syntax error");
                            }
                            key = lexer.scanSymbolUnQuoted(parser.getSymbolTable());
                            lexer.skipWhitespace();
                            ch = lexer.getCurrent();
                            if (ch != ':') {
                                throw new JSONException("expect ':' at " + lexer.pos() + ", actual " + ch);
                            }
                        }
                    }
                    lexer.next();
                    lexer.skipWhitespace();
                    ch = lexer.getCurrent();
                    lexer.resetStringPosition();
                    if (key == JSON.DEFAULT_TYPE_KEY && !lexer.isEnabled(Feature.DisableSpecialKeyDetect) && !Feature.isEnabled(features, Feature.DisableSpecialKeyDetect)) {
                        void var13_17;
                        String typeName = lexer.scanSymbol(parser.getSymbolTable(), '\"');
                        ParserConfig config = parser.getConfig();
                        if (typeName.equals("java.util.HashMap")) {
                            Class<HashMap> clazz = HashMap.class;
                        } else if (typeName.equals("java.util.LinkedHashMap")) {
                            Class<LinkedHashMap> clazz = LinkedHashMap.class;
                        } else if (config.isSafeMode()) {
                            Class<HashMap> clazz = HashMap.class;
                        } else {
                            try {
                                Class<?> clazz = config.checkAutoType(typeName, null, lexer.getFeatures());
                            } catch (JSONException ex) {
                                Class<HashMap> clazz = HashMap.class;
                            }
                        }
                        if (Map.class.isAssignableFrom((Class<?>)var13_17)) {
                            lexer.nextToken(16);
                            if (lexer.token() == 13) {
                                lexer.nextToken(16);
                                Map<String, Object> ex = map;
                                return ex;
                            }
                            break block43;
                        } else {
                            ObjectDeserializer deserializer = config.getDeserializer((Type)var13_17);
                            lexer.nextToken(16);
                            parser.setResolveStatus(2);
                            if (context != null && !(fieldName instanceof Integer)) {
                                parser.popContext();
                            }
                            Map map3 = (Map)deserializer.deserialze(parser, (Type)var13_17, fieldName);
                            return map3;
                        }
                    }
                    lexer.nextToken();
                    if (i != 0) {
                        parser.setContext(context);
                    }
                    if (lexer.token() == 8) {
                        value = null;
                        lexer.nextToken();
                    } else {
                        value = parser.parseObject(valueType, (Object)key);
                    }
                    map.put(key, value);
                    parser.checkMapResolve(map, key);
                    parser.setContext(context, value, key);
                    parser.setContext(context);
                    int tok = lexer.token();
                    if (tok == 20 || tok == 15) {
                        Map<String, Object> map4 = map;
                        return map4;
                    }
                    if (tok == 13) {
                        lexer.nextToken();
                        Map<String, Object> map5 = map;
                        return map5;
                    }
                }
                ++i;
            }
        } finally {
            parser.setContext(context);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static Object parseMap(DefaultJSONParser parser, Map<Object, Object> map, Type keyType, Type valueType, Object fieldName) {
        JSONLexer lexer = parser.lexer;
        if (lexer.token() != 12 && lexer.token() != 16) {
            throw new JSONException("syntax error, expect {, actual " + lexer.tokenName());
        }
        ObjectDeserializer keyDeserializer = parser.getConfig().getDeserializer(keyType);
        ObjectDeserializer valueDeserializer = parser.getConfig().getDeserializer(valueType);
        lexer.nextToken(keyDeserializer.getFastMatchToken());
        ParseContext context = parser.getContext();
        try {
            while (true) {
                Object key;
                Object object;
                if (lexer.token() == 13) {
                    lexer.nextToken(16);
                    break;
                }
                if (lexer.token() == 4 && lexer.isRef() && !lexer.isEnabled(Feature.DisableSpecialKeyDetect)) {
                    Object ref;
                    object = null;
                    lexer.nextTokenWithColon(4);
                    if (lexer.token() == 4) {
                        ref = lexer.stringVal();
                        if ("..".equals(ref)) {
                            ParseContext parentContext = context.parent;
                            object = parentContext.object;
                        } else if ("$".equals(ref)) {
                            ParseContext rootContext = context;
                            while (rootContext.parent != null) {
                                rootContext = rootContext.parent;
                            }
                            object = rootContext.object;
                        } else {
                            parser.addResolveTask(new DefaultJSONParser.ResolveTask(context, (String)ref));
                            parser.setResolveStatus(1);
                        }
                    } else {
                        throw new JSONException("illegal ref, " + JSONToken.name(lexer.token()));
                    }
                    lexer.nextToken(13);
                    if (lexer.token() != 13) {
                        throw new JSONException("illegal ref");
                    }
                    lexer.nextToken(16);
                    ref = object;
                    return ref;
                }
                if (map.size() == 0 && lexer.token() == 4 && JSON.DEFAULT_TYPE_KEY.equals(lexer.stringVal()) && !lexer.isEnabled(Feature.DisableSpecialKeyDetect)) {
                    lexer.nextTokenWithColon(4);
                    lexer.nextToken(16);
                    if (lexer.token() == 13) {
                        lexer.nextToken();
                        object = map;
                        return object;
                    }
                    lexer.nextToken(keyDeserializer.getFastMatchToken());
                }
                if (lexer.token() == 4 && keyDeserializer instanceof JavaBeanDeserializer) {
                    String keyStrValue = lexer.stringVal();
                    lexer.nextToken();
                    DefaultJSONParser keyParser = new DefaultJSONParser(keyStrValue, parser.getConfig(), parser.getLexer().getFeatures());
                    keyParser.setDateFormat(parser.getDateFomartPattern());
                    key = keyDeserializer.deserialze(keyParser, keyType, null);
                } else {
                    key = keyDeserializer.deserialze(parser, keyType, null);
                }
                if (lexer.token() != 17) {
                    throw new JSONException("syntax error, expect :, actual " + lexer.token());
                }
                lexer.nextToken(valueDeserializer.getFastMatchToken());
                Object value = valueDeserializer.deserialze(parser, valueType, key);
                parser.checkMapResolve(map, key);
                map.put(key, value);
                if (lexer.token() != 16) continue;
                lexer.nextToken(keyDeserializer.getFastMatchToken());
            }
        } finally {
            parser.setContext(context);
        }
        return map;
    }

    public Map<Object, Object> createMap(Type type) {
        return this.createMap(type, JSON.DEFAULT_GENERATE_FEATURE);
    }

    public Map<Object, Object> createMap(Type type, int featrues) {
        if (type == Properties.class) {
            return new Properties();
        }
        if (type == Hashtable.class) {
            return new Hashtable<Object, Object>();
        }
        if (type == IdentityHashMap.class) {
            return new IdentityHashMap<Object, Object>();
        }
        if (type == SortedMap.class || type == TreeMap.class) {
            return new TreeMap<Object, Object>();
        }
        if (type == ConcurrentMap.class || type == ConcurrentHashMap.class) {
            return new ConcurrentHashMap<Object, Object>();
        }
        if (type == Map.class) {
            return (featrues & Feature.OrderedField.mask) != 0 ? new LinkedHashMap() : new HashMap();
        }
        if (type == HashMap.class) {
            return new HashMap<Object, Object>();
        }
        if (type == LinkedHashMap.class) {
            return new LinkedHashMap<Object, Object>();
        }
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType)type;
            Type rawType = parameterizedType.getRawType();
            if (EnumMap.class.equals((Object)rawType)) {
                Type[] actualArgs = parameterizedType.getActualTypeArguments();
                return new EnumMap<Object, Object>((Class)actualArgs[0]);
            }
            return this.createMap(rawType, featrues);
        }
        Class clazz = (Class)type;
        if (clazz.isInterface()) {
            throw new JSONException("unsupport type " + type);
        }
        if ("java.util.Collections$UnmodifiableMap".equals(clazz.getName())) {
            return new HashMap<Object, Object>();
        }
        try {
            return (Map)clazz.newInstance();
        } catch (Exception e) {
            throw new JSONException("unsupport type " + type, e);
        }
    }

    @Override
    public int getFastMatchToken() {
        return 12;
    }
}

