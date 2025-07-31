/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.parser;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.alibaba.fastjson.JSONPathException;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.JSONLexerBase;
import com.alibaba.fastjson.parser.JSONScanner;
import com.alibaba.fastjson.parser.JSONToken;
import com.alibaba.fastjson.parser.ParseContext;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.parser.SymbolTable;
import com.alibaba.fastjson.parser.deserializer.ExtraProcessable;
import com.alibaba.fastjson.parser.deserializer.ExtraProcessor;
import com.alibaba.fastjson.parser.deserializer.ExtraTypeProvider;
import com.alibaba.fastjson.parser.deserializer.FieldDeserializer;
import com.alibaba.fastjson.parser.deserializer.FieldTypeResolver;
import com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer;
import com.alibaba.fastjson.parser.deserializer.MapDeserializer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.parser.deserializer.PropertyProcessable;
import com.alibaba.fastjson.parser.deserializer.ResolveFieldDeserializer;
import com.alibaba.fastjson.parser.deserializer.ThrowableDeserializer;
import com.alibaba.fastjson.serializer.BeanContext;
import com.alibaba.fastjson.serializer.IntegerCodec;
import com.alibaba.fastjson.serializer.LongCodec;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.StringCodec;
import com.alibaba.fastjson.util.TypeUtils;
import java.io.Closeable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class DefaultJSONParser
implements Closeable {
    public final Object input;
    public final SymbolTable symbolTable;
    protected ParserConfig config;
    private static final Set<Class<?>> primitiveClasses = new HashSet();
    private String dateFormatPattern = JSON.DEFFAULT_DATE_FORMAT;
    private DateFormat dateFormat;
    public final JSONLexer lexer;
    protected ParseContext context;
    private ParseContext[] contextArray;
    private int contextArrayIndex = 0;
    private List<ResolveTask> resolveTaskList;
    public static final int NONE = 0;
    public static final int NeedToResolve = 1;
    public static final int TypeNameRedirect = 2;
    public int resolveStatus = 0;
    private List<ExtraTypeProvider> extraTypeProviders = null;
    private List<ExtraProcessor> extraProcessors = null;
    protected FieldTypeResolver fieldTypeResolver = null;
    private int objectKeyLevel = 0;
    private boolean autoTypeEnable;
    private String[] autoTypeAccept = null;
    protected transient BeanContext lastBeanContext;

    public String getDateFomartPattern() {
        return this.dateFormatPattern;
    }

    public DateFormat getDateFormat() {
        if (this.dateFormat == null) {
            this.dateFormat = new SimpleDateFormat(this.dateFormatPattern, this.lexer.getLocale());
            this.dateFormat.setTimeZone(this.lexer.getTimeZone());
        }
        return this.dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormatPattern = dateFormat;
        this.dateFormat = null;
    }

    public void setDateFomrat(DateFormat dateFormat) {
        this.setDateFormat(dateFormat);
    }

    public void setDateFormat(DateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }

    public DefaultJSONParser(String input) {
        this(input, ParserConfig.getGlobalInstance(), JSON.DEFAULT_PARSER_FEATURE);
    }

    public DefaultJSONParser(String input, ParserConfig config) {
        this((Object)input, new JSONScanner(input, JSON.DEFAULT_PARSER_FEATURE), config);
    }

    public DefaultJSONParser(String input, ParserConfig config, int features) {
        this((Object)input, new JSONScanner(input, features), config);
    }

    public DefaultJSONParser(char[] input, int length, ParserConfig config, int features) {
        this(input, new JSONScanner(input, length, features), config);
    }

    public DefaultJSONParser(JSONLexer lexer) {
        this(lexer, ParserConfig.getGlobalInstance());
    }

    public DefaultJSONParser(JSONLexer lexer, ParserConfig config) {
        this(null, lexer, config);
    }

    public DefaultJSONParser(Object input, JSONLexer lexer, ParserConfig config) {
        this.lexer = lexer;
        this.input = input;
        this.config = config;
        this.symbolTable = config.symbolTable;
        char ch = lexer.getCurrent();
        if (ch == '{') {
            lexer.next();
            ((JSONLexerBase)lexer).token = 12;
        } else if (ch == '[') {
            lexer.next();
            ((JSONLexerBase)lexer).token = 14;
        } else {
            lexer.nextToken();
        }
    }

    public SymbolTable getSymbolTable() {
        return this.symbolTable;
    }

    public String getInput() {
        if (this.input instanceof char[]) {
            return new String((char[])this.input);
        }
        return this.input.toString();
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public final Object parseObject(Map object, Object fieldName) {
        block106: {
            block107: {
                lexer = this.lexer;
                if (lexer.token() == 8) {
                    lexer.nextToken();
                    return null;
                }
                if (lexer.token() == 13) {
                    lexer.nextToken();
                    return object;
                }
                if (lexer.token() == 4 && lexer.stringVal().length() == 0) {
                    lexer.nextToken();
                    return object;
                }
                if (lexer.token() != 12 && lexer.token() != 16) {
                    throw new JSONException("syntax error, expect {, actual " + lexer.tokenName() + ", " + lexer.info());
                }
                context = this.context;
                isJsonObjectMap = object instanceof JSONObject;
                map = isJsonObjectMap != false ? ((JSONObject)object).getInnerMap() : object;
                setContextFlag = false;
lbl18:
                // 2 sources

                while (true) {
                    block109: {
                        block108: {
                            block110: {
                                lexer.skipWhitespace();
                                ch = lexer.getCurrent();
                                if (lexer.isEnabled(Feature.AllowArbitraryCommas)) {
                                    while (ch == ',') {
                                        lexer.next();
                                        lexer.skipWhitespace();
                                        ch = lexer.getCurrent();
                                    }
                                }
                                isObjectKey = false;
                                if (ch == '\"') {
                                    key = lexer.scanSymbol(this.symbolTable, '\"');
                                    lexer.skipWhitespace();
                                    ch = lexer.getCurrent();
                                    if (ch != ':') {
                                        throw new JSONException("expect ':' at " + lexer.pos() + ", name " + key);
                                    }
                                } else {
                                    if (ch == '}') {
                                        lexer.next();
                                        lexer.resetStringPosition();
                                        lexer.nextToken();
                                        if (!setContextFlag) {
                                            if (this.context != null && fieldName == this.context.fieldName && object == this.context.object) {
                                                context = this.context;
                                            } else {
                                                contextR = this.setContext(object, fieldName);
                                                if (context == null) {
                                                    context = contextR;
                                                }
                                                setContextFlag = true;
                                            }
                                        }
                                        contextR = object;
                                        return contextR;
                                    }
                                    if (ch == '\'') {
                                        if (!lexer.isEnabled(Feature.AllowSingleQuotes)) {
                                            throw new JSONException("syntax error");
                                        }
                                        key = lexer.scanSymbol(this.symbolTable, '\'');
                                        lexer.skipWhitespace();
                                        ch = lexer.getCurrent();
                                        if (ch != ':') {
                                            throw new JSONException("expect ':' at " + lexer.pos());
                                        }
                                    } else {
                                        if (ch == '\u001a') {
                                            throw new JSONException("syntax error");
                                        }
                                        if (ch == ',') {
                                            throw new JSONException("syntax error");
                                        }
                                        if (ch >= '0' && ch <= '9' || ch == '-') {
                                            lexer.resetStringPosition();
                                            lexer.scanNumber();
                                            try {
                                                key = lexer.token() == 2 ? lexer.integerValue() : lexer.decimalValue(true);
                                                if (lexer.isEnabled(Feature.NonStringKeyAsString) || isJsonObjectMap) {
                                                    key = key.toString();
                                                }
                                            } catch (NumberFormatException e) {
                                                throw new JSONException("parse number key error" + lexer.info());
                                            }
                                            ch = lexer.getCurrent();
                                            if (ch != ':') {
                                                throw new JSONException("parse number key error" + lexer.info());
                                            }
                                        } else if (ch == '{' || ch == '[') {
                                            if (this.objectKeyLevel++ > 512) {
                                                throw new JSONException("object key level > 512");
                                            }
                                            lexer.nextToken();
                                            key = this.parse();
                                            isObjectKey = true;
                                        } else {
                                            if (!lexer.isEnabled(Feature.AllowUnQuotedFieldNames)) {
                                                throw new JSONException("syntax error");
                                            }
                                            key = lexer.scanSymbolUnQuoted(this.symbolTable);
                                            lexer.skipWhitespace();
                                            ch = lexer.getCurrent();
                                            if (ch != ':') {
                                                throw new JSONException("expect ':' at " + lexer.pos() + ", actual " + ch);
                                            }
                                        }
                                    }
                                }
                                if (!isObjectKey) {
                                    lexer.next();
                                    lexer.skipWhitespace();
                                }
                                ch = lexer.getCurrent();
                                lexer.resetStringPosition();
                                if (key == JSON.DEFAULT_TYPE_KEY && !lexer.isEnabled(Feature.DisableSpecialKeyDetect)) {
                                    typeName = lexer.scanSymbol(this.symbolTable, '\"');
                                    if (lexer.isEnabled(Feature.IgnoreAutoType)) continue;
                                    clazz = null;
                                    if (object != null && object.getClass().getName().equals(typeName)) {
                                        clazz = object.getClass();
                                        break block106;
                                    }
                                    if ("java.util.HashMap".equals(typeName)) {
                                        clazz = HashMap.class;
                                        break block106;
                                    }
                                    if ("java.util.LinkedHashMap".equals(typeName)) {
                                        clazz = LinkedHashMap.class;
                                        break block106;
                                    }
                                    allDigits = true;
                                    break block107;
                                }
                                if (key != "$ref" || context == null || object != null && object.size() != 0 || lexer.isEnabled(Feature.DisableSpecialKeyDetect)) break block109;
                                lexer.nextToken(4);
                                if (lexer.token() != 4) throw new JSONException("illegal ref, " + JSONToken.name(lexer.token()));
                                ref = lexer.stringVal();
                                lexer.nextToken(13);
                                if (lexer.token() == 16) {
                                    map.put((String)key, ref);
                                    continue;
                                }
                                refValue = null;
                                if (!"@".equals(ref)) break block110;
                                if (this.context != null) {
                                    thisContext = this.context;
                                    thisObj = thisContext.object;
                                    if (thisObj instanceof Object[] || thisObj instanceof Collection) {
                                        refValue = thisObj;
                                        break block108;
                                    } else if (thisContext.parent != null) {
                                        refValue = thisContext.parent.object;
                                    }
                                }
                                break block108;
                            }
                            if ("..".equals(ref)) {
                                if (context.object != null) {
                                    refValue = context.object;
                                } else {
                                    this.addResolveTask(new ResolveTask((ParseContext)context, ref));
                                    this.setResolveStatus(1);
                                }
                            } else if ("$".equals(ref)) {
                                rootContext = context;
                                while (rootContext.parent != null) {
                                    rootContext = rootContext.parent;
                                }
                                if (rootContext.object != null) {
                                    refValue = rootContext.object;
                                } else {
                                    this.addResolveTask(new ResolveTask((ParseContext)rootContext, ref));
                                    this.setResolveStatus(1);
                                }
                            } else {
                                jsonpath = JSONPath.compile(ref);
                                if (jsonpath.isRef()) {
                                    this.addResolveTask(new ResolveTask((ParseContext)context, ref));
                                    this.setResolveStatus(1);
                                } else {
                                    refValue = new JSONObject().fluentPut("$ref", ref);
                                }
                            }
                        }
                        if (lexer.token() != 13) {
                            throw new JSONException("syntax error, " + lexer.info());
                        }
                        lexer.nextToken(16);
                        jsonpath = refValue;
                        return jsonpath;
                    }
                    if (!setContextFlag) {
                        if (this.context != null && fieldName == this.context.fieldName && object == this.context.object) {
                            context = this.context;
                        } else {
                            contextR = this.setContext(object, fieldName);
                            if (context == null) {
                                context = contextR;
                            }
                            setContextFlag = true;
                        }
                    }
                    if (object.getClass() == JSONObject.class && key == null) {
                        key = "null";
                    }
                    if (ch == '\"') {
                        lexer.scanString();
                        strValue = lexer.stringVal();
                        value /* !! */  = strValue;
                        if (lexer.isEnabled(Feature.AllowISO8601DateFormat)) {
                            iso8601Lexer = new JSONScanner(strValue);
                            if (iso8601Lexer.scanISO8601DateIfMatch()) {
                                value /* !! */  = iso8601Lexer.getCalendar().getTime();
                            }
                            iso8601Lexer.close();
                        }
                        map.put((String)key, value /* !! */ );
                    } else if (ch >= '0' && ch <= '9' || ch == '-') {
                        lexer.scanNumber();
                        value /* !! */  = lexer.token() == 2 ? lexer.integerValue() : lexer.decimalValue(lexer.isEnabled(Feature.UseBigDecimal));
                        map.put((String)key, value /* !! */ );
                    } else {
                        if (ch == '[') {
                            lexer.nextToken();
                            list = new JSONArray();
                            v0 = parentIsArray = fieldName != null && fieldName.getClass() == Integer.class;
                            if (fieldName == null) {
                                this.setContext((ParseContext)context);
                            }
                            this.parseArray(list, key);
                            value /* !! */  = lexer.isEnabled(Feature.UseObjectArray) != false ? list.toArray() : list;
                            map.put((String)key, value /* !! */ );
                            if (lexer.token() == 13) {
                                lexer.nextToken();
                                thisObj = object;
                                return thisObj;
                            }
                            if (lexer.token() != 16) throw new JSONException("syntax error");
                            continue;
                        }
                        if (ch == '{') {
                            lexer.nextToken();
                            v1 = parentIsArray = fieldName != null && fieldName.getClass() == Integer.class;
                            if (lexer.isEnabled(Feature.CustomMapDeserializer)) {
                                mapDeserializer = (MapDeserializer)this.config.getDeserializer((Type)Map.class);
                                input /* !! */  = (lexer.getFeatures() & Feature.OrderedField.mask) != 0 ? mapDeserializer.createMap((Type)Map.class, lexer.getFeatures()) : mapDeserializer.createMap((Type)Map.class);
                            } else {
                                input /* !! */  = new JSONObject(lexer.isEnabled(Feature.OrderedField));
                            }
                            ctxLocal = null;
                            if (!parentIsArray) {
                                ctxLocal = this.setContext(this.context, input /* !! */ , key);
                            }
                            obj = null;
                            objParsed = false;
                            if (this.fieldTypeResolver != null && (fieldType = this.fieldTypeResolver.resolve(object, resolveFieldName = key != null ? key.toString() : null)) != null) {
                                fieldDeser = this.config.getDeserializer(fieldType);
                                obj = fieldDeser.deserialze(this, fieldType, key);
                                objParsed = true;
                            }
                            if (!objParsed) {
                                obj = this.parseObject(input /* !! */ , key);
                            }
                            if (ctxLocal != null && input /* !! */  != obj) {
                                ctxLocal.object = object;
                            }
                            if (key != null) {
                                this.checkMapResolve(object, key.toString());
                            }
                            map.put((String)key, obj);
                            if (parentIsArray) {
                                this.setContext(obj, key);
                            }
                            if (lexer.token() == 13) {
                                lexer.nextToken();
                                this.setContext((ParseContext)context);
                                var17_41 = object;
                                return var17_41;
                            }
                            if (lexer.token() != 16) throw new JSONException("syntax error, " + lexer.tokenName());
                            if (parentIsArray) {
                                this.popContext();
                                continue;
                            }
                            this.setContext((ParseContext)context);
                            continue;
                        }
                        lexer.nextToken();
                        value /* !! */  = this.parse();
                        map.put((String)key, value /* !! */ );
                        if (lexer.token() == 13) {
                            lexer.nextToken();
                            var12_13 = object;
                            return var12_13;
                        }
                        if (lexer.token() != 16) throw new JSONException("syntax error, position at " + lexer.pos() + ", name " + key);
                        continue;
                    }
                    lexer.skipWhitespace();
                    ch = lexer.getCurrent();
                    if (ch == ',') {
                        lexer.next();
                        continue;
                    }
                    if (ch != '}') throw new JSONException("syntax error, position at " + lexer.pos() + ", name " + key);
                    lexer.next();
                    lexer.resetStringPosition();
                    lexer.nextToken();
                    this.setContext(value /* !! */ , key);
                    var12_13 = object;
                    return var12_13;
                }
                finally {
                    this.setContext((ParseContext)context);
                }
            }
            for (i = 0; i < typeName.length(); ++i) {
                c = typeName.charAt(i);
                if (c >= '0' && c <= '9') continue;
                allDigits = false;
                break;
            }
            if (!allDigits) {
                clazz = this.config.checkAutoType(typeName, null, lexer.getFeatures());
            }
        }
        if (clazz == null) {
            map.put(JSON.DEFAULT_TYPE_KEY, typeName);
            ** continue;
        }
        lexer.nextToken(16);
        if (lexer.token() == 13) {
            lexer.nextToken(16);
            try {
                instance /* !! */  = null;
                deserializer = this.config.getDeserializer((Type)clazz);
                if (deserializer instanceof JavaBeanDeserializer) {
                    instance /* !! */  = TypeUtils.cast(object, clazz, this.config);
                }
                if (instance /* !! */  == null) {
                    instance /* !! */  = clazz == Cloneable.class ? new HashMap<K, V>() : ("java.util.Collections$EmptyMap".equals(typeName) != false ? Collections.emptyMap() : ("java.util.Collections$UnmodifiableMap".equals(typeName) != false ? Collections.unmodifiableMap(new HashMap<K, V>()) : clazz.newInstance()));
                }
                c = instance /* !! */ ;
                return c;
            } catch (Exception e) {
                throw new JSONException("create instance error", e);
            }
        }
        this.setResolveStatus(2);
        if (this.context != null && fieldName != null && !(fieldName instanceof Integer) && !(this.context.fieldName instanceof Integer)) {
            this.popContext();
        }
        if (object.size() > 0) {
            newObj = TypeUtils.cast(object, clazz, this.config);
            this.setResolveStatus(0);
            this.parseObject((Object)newObj);
            deserializer = newObj;
            return deserializer;
        }
        deserializer = this.config.getDeserializer((Type)clazz);
        deserClass = deserializer.getClass();
        if (JavaBeanDeserializer.class.isAssignableFrom(deserClass) && deserClass != JavaBeanDeserializer.class && deserClass != ThrowableDeserializer.class) {
            this.setResolveStatus(0);
        } else if (deserializer instanceof MapDeserializer) {
            this.setResolveStatus(0);
        }
        var16_40 = obj = deserializer.deserialze(this, (Type)clazz, fieldName);
        return var16_40;
    }

    public ParserConfig getConfig() {
        return this.config;
    }

    public void setConfig(ParserConfig config) {
        this.config = config;
    }

    public <T> T parseObject(Class<T> clazz) {
        return this.parseObject(clazz, null);
    }

    public <T> T parseObject(Type type) {
        return this.parseObject(type, null);
    }

    public <T> T parseObject(Type type, Object fieldName) {
        int token = this.lexer.token();
        if (token == 8) {
            this.lexer.nextToken();
            return (T)TypeUtils.optionalEmpty(type);
        }
        if (token == 4) {
            if (type == byte[].class) {
                byte[] bytes = this.lexer.bytesValue();
                this.lexer.nextToken();
                return (T)bytes;
            }
            if (type == char[].class) {
                String strVal = this.lexer.stringVal();
                this.lexer.nextToken();
                return (T)strVal.toCharArray();
            }
        }
        ObjectDeserializer deserializer = this.config.getDeserializer(type);
        try {
            if (deserializer.getClass() == JavaBeanDeserializer.class) {
                if (this.lexer.token() != 12 && this.lexer.token() != 14) {
                    throw new JSONException("syntax error,expect start with { or [,but actually start with " + this.lexer.tokenName());
                }
                return ((JavaBeanDeserializer)deserializer).deserialze(this, type, fieldName, 0);
            }
            return deserializer.deserialze(this, type, fieldName);
        } catch (JSONException e) {
            throw e;
        } catch (Throwable e) {
            throw new JSONException(e.getMessage(), e);
        }
    }

    public <T> List<T> parseArray(Class<T> clazz) {
        ArrayList array = new ArrayList();
        this.parseArray(clazz, array);
        return array;
    }

    public void parseArray(Class<?> clazz, Collection array) {
        this.parseArray((Type)clazz, array);
    }

    public void parseArray(Type type, Collection array) {
        this.parseArray(type, array, null);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void parseArray(Type type, Collection array, Object fieldName) {
        int token = this.lexer.token();
        if (token == 21 || token == 22) {
            this.lexer.nextToken();
            token = this.lexer.token();
        }
        if (token != 14) {
            throw new JSONException("field " + fieldName + " expect '[', but " + JSONToken.name(token) + ", " + this.lexer.info());
        }
        ObjectDeserializer deserializer = null;
        if (Integer.TYPE == type) {
            deserializer = IntegerCodec.instance;
            this.lexer.nextToken(2);
        } else if (String.class == type) {
            deserializer = StringCodec.instance;
            this.lexer.nextToken(4);
        } else {
            deserializer = this.config.getDeserializer(type);
            this.lexer.nextToken(deserializer.getFastMatchToken());
        }
        ParseContext context = this.context;
        this.setContext(array, fieldName);
        try {
            int i = 0;
            while (true) {
                Object val;
                if (this.lexer.isEnabled(Feature.AllowArbitraryCommas)) {
                    while (this.lexer.token() == 16) {
                        this.lexer.nextToken();
                    }
                }
                if (this.lexer.token() == 15) {
                    break;
                }
                if (Integer.TYPE == type) {
                    val = IntegerCodec.instance.deserialze(this, null, null);
                    array.add(val);
                } else if (String.class == type) {
                    String value;
                    if (this.lexer.token() == 4) {
                        value = this.lexer.stringVal();
                        this.lexer.nextToken(16);
                    } else {
                        Object obj = this.parse();
                        value = obj == null ? null : obj.toString();
                    }
                    array.add(value);
                } else {
                    if (this.lexer.token() == 8) {
                        this.lexer.nextToken();
                        val = null;
                    } else {
                        val = deserializer.deserialze(this, type, i);
                    }
                    array.add(val);
                    this.checkListResolve(array);
                }
                if (this.lexer.token() == 16) {
                    this.lexer.nextToken(deserializer.getFastMatchToken());
                }
                ++i;
            }
        } finally {
            this.setContext(context);
        }
        this.lexer.nextToken(16);
    }

    public Object[] parseArray(Type[] types) {
        if (this.lexer.token() == 8) {
            this.lexer.nextToken(16);
            return null;
        }
        if (this.lexer.token() != 14) {
            throw new JSONException("syntax error : " + this.lexer.tokenName());
        }
        Object[] list = new Object[types.length];
        if (types.length == 0) {
            this.lexer.nextToken(15);
            if (this.lexer.token() != 15) {
                throw new JSONException("syntax error");
            }
            this.lexer.nextToken(16);
            return new Object[0];
        }
        this.lexer.nextToken(2);
        for (int i = 0; i < types.length; ++i) {
            Object value;
            if (this.lexer.token() == 8) {
                value = null;
                this.lexer.nextToken(16);
            } else {
                Type type = types[i];
                if (type == Integer.TYPE || type == Integer.class) {
                    if (this.lexer.token() == 2) {
                        value = this.lexer.intValue();
                        this.lexer.nextToken(16);
                    } else {
                        value = this.parse();
                        value = TypeUtils.cast(value, type, this.config);
                    }
                } else if (type == String.class) {
                    if (this.lexer.token() == 4) {
                        value = this.lexer.stringVal();
                        this.lexer.nextToken(16);
                    } else {
                        value = this.parse();
                        value = TypeUtils.cast(value, type, this.config);
                    }
                } else {
                    Class clazz;
                    boolean isArray = false;
                    Class<?> componentType = null;
                    if (i == types.length - 1 && type instanceof Class && ((clazz = (Class)type) != byte[].class && clazz != char[].class || this.lexer.token() != 4)) {
                        isArray = clazz.isArray();
                        componentType = clazz.getComponentType();
                    }
                    if (isArray && this.lexer.token() != 14) {
                        ArrayList varList = new ArrayList();
                        ObjectDeserializer deserializer = this.config.getDeserializer(componentType);
                        int fastMatch = deserializer.getFastMatchToken();
                        if (this.lexer.token() != 15) {
                            while (true) {
                                Object item = deserializer.deserialze(this, type, null);
                                varList.add(item);
                                if (this.lexer.token() != 16) break;
                                this.lexer.nextToken(fastMatch);
                            }
                            if (this.lexer.token() != 15) {
                                throw new JSONException("syntax error :" + JSONToken.name(this.lexer.token()));
                            }
                        }
                        value = TypeUtils.cast(varList, type, this.config);
                    } else {
                        ObjectDeserializer deserializer = this.config.getDeserializer(type);
                        value = deserializer.deserialze(this, type, i);
                    }
                }
            }
            list[i] = value;
            if (this.lexer.token() == 15) break;
            if (this.lexer.token() != 16) {
                throw new JSONException("syntax error :" + JSONToken.name(this.lexer.token()));
            }
            if (i == types.length - 1) {
                this.lexer.nextToken(15);
                continue;
            }
            this.lexer.nextToken(2);
        }
        if (this.lexer.token() != 15) {
            throw new JSONException("syntax error");
        }
        this.lexer.nextToken(16);
        return list;
    }

    public void parseObject(Object object) {
        block14: {
            Class<?> clazz = object.getClass();
            JavaBeanDeserializer beanDeser = null;
            ObjectDeserializer deserializer = this.config.getDeserializer(clazz);
            if (deserializer instanceof JavaBeanDeserializer) {
                beanDeser = (JavaBeanDeserializer)deserializer;
            }
            if (this.lexer.token() != 12 && this.lexer.token() != 16) {
                throw new JSONException("syntax error, expect {, actual " + this.lexer.tokenName());
            }
            while (true) {
                Object fieldValue;
                String key;
                if ((key = this.lexer.scanSymbol(this.symbolTable)) == null) {
                    if (this.lexer.token() != 13) {
                        if (this.lexer.token() == 16 && this.lexer.isEnabled(Feature.AllowArbitraryCommas)) continue;
                    }
                    break block14;
                }
                FieldDeserializer fieldDeser = null;
                if (beanDeser != null) {
                    fieldDeser = beanDeser.getFieldDeserializer(key);
                }
                if (fieldDeser == null) {
                    if (!this.lexer.isEnabled(Feature.IgnoreNotMatch)) {
                        throw new JSONException("setter not found, class " + clazz.getName() + ", property " + key);
                    }
                    this.lexer.nextTokenWithColon();
                    this.parse();
                    if (this.lexer.token() != 13) continue;
                    this.lexer.nextToken();
                    return;
                }
                Class<?> fieldClass = fieldDeser.fieldInfo.fieldClass;
                Type fieldType = fieldDeser.fieldInfo.fieldType;
                if (fieldClass == Integer.TYPE) {
                    this.lexer.nextTokenWithColon(2);
                    fieldValue = IntegerCodec.instance.deserialze(this, fieldType, null);
                } else if (fieldClass == String.class) {
                    this.lexer.nextTokenWithColon(4);
                    fieldValue = StringCodec.deserialze(this);
                } else if (fieldClass == Long.TYPE) {
                    this.lexer.nextTokenWithColon(2);
                    fieldValue = LongCodec.instance.deserialze(this, fieldType, null);
                } else {
                    ObjectDeserializer fieldValueDeserializer = this.config.getDeserializer(fieldClass, fieldType);
                    this.lexer.nextTokenWithColon(fieldValueDeserializer.getFastMatchToken());
                    fieldValue = fieldValueDeserializer.deserialze(this, fieldType, null);
                }
                fieldDeser.setValue(object, fieldValue);
                if (this.lexer.token() != 16 && this.lexer.token() == 13) break;
            }
            this.lexer.nextToken(16);
            return;
        }
        this.lexer.nextToken(16);
    }

    public Object parseArrayWithType(Type collectionType) {
        if (this.lexer.token() == 8) {
            this.lexer.nextToken();
            return null;
        }
        Type[] actualTypes = ((ParameterizedType)collectionType).getActualTypeArguments();
        if (actualTypes.length != 1) {
            throw new JSONException("not support type " + collectionType);
        }
        Type actualTypeArgument = actualTypes[0];
        if (actualTypeArgument instanceof Class) {
            ArrayList array = new ArrayList();
            this.parseArray((Class)actualTypeArgument, array);
            return array;
        }
        if (actualTypeArgument instanceof WildcardType) {
            WildcardType wildcardType = (WildcardType)actualTypeArgument;
            Type upperBoundType = wildcardType.getUpperBounds()[0];
            if (Object.class.equals((Object)upperBoundType)) {
                if (wildcardType.getLowerBounds().length == 0) {
                    return this.parse();
                }
                throw new JSONException("not support type : " + collectionType);
            }
            ArrayList array = new ArrayList();
            this.parseArray((Class)upperBoundType, array);
            return array;
        }
        if (actualTypeArgument instanceof TypeVariable) {
            TypeVariable typeVariable = (TypeVariable)actualTypeArgument;
            Type[] bounds = typeVariable.getBounds();
            if (bounds.length != 1) {
                throw new JSONException("not support : " + typeVariable);
            }
            Type boundType = bounds[0];
            if (boundType instanceof Class) {
                ArrayList array = new ArrayList();
                this.parseArray((Class)boundType, array);
                return array;
            }
        }
        if (actualTypeArgument instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType)actualTypeArgument;
            ArrayList array = new ArrayList();
            this.parseArray(parameterizedType, array);
            return array;
        }
        throw new JSONException("TODO : " + collectionType);
    }

    public void acceptType(String typeName) {
        JSONLexer lexer = this.lexer;
        lexer.nextTokenWithColon();
        if (lexer.token() != 4) {
            throw new JSONException("type not match error");
        }
        if (typeName.equals(lexer.stringVal())) {
            lexer.nextToken();
            if (lexer.token() == 16) {
                lexer.nextToken();
            }
        } else {
            throw new JSONException("type not match error");
        }
    }

    public int getResolveStatus() {
        return this.resolveStatus;
    }

    public void setResolveStatus(int resolveStatus) {
        this.resolveStatus = resolveStatus;
    }

    public Object getObject(String path) {
        for (int i = 0; i < this.contextArrayIndex; ++i) {
            if (!path.equals(this.contextArray[i].toString())) continue;
            return this.contextArray[i].object;
        }
        return null;
    }

    public void checkListResolve(Collection array) {
        if (this.resolveStatus == 1) {
            if (array instanceof List) {
                int index = array.size() - 1;
                List list = (List)array;
                ResolveTask task = this.getLastResolveTask();
                task.fieldDeserializer = new ResolveFieldDeserializer(this, list, index);
                task.ownerContext = this.context;
                this.setResolveStatus(0);
            } else {
                ResolveTask task = this.getLastResolveTask();
                task.fieldDeserializer = new ResolveFieldDeserializer(array);
                task.ownerContext = this.context;
                this.setResolveStatus(0);
            }
        }
    }

    public void checkMapResolve(Map object, Object fieldName) {
        if (this.resolveStatus == 1) {
            ResolveFieldDeserializer fieldResolver = new ResolveFieldDeserializer(object, fieldName);
            ResolveTask task = this.getLastResolveTask();
            task.fieldDeserializer = fieldResolver;
            task.ownerContext = this.context;
            this.setResolveStatus(0);
        }
    }

    public Object parseObject(Map object) {
        return this.parseObject(object, null);
    }

    public JSONObject parseObject() {
        JSONObject object = new JSONObject(this.lexer.isEnabled(Feature.OrderedField));
        Object parsedObject = this.parseObject(object);
        if (parsedObject instanceof JSONObject) {
            return (JSONObject)parsedObject;
        }
        if (parsedObject == null) {
            return null;
        }
        return new JSONObject((Map)parsedObject);
    }

    public final void parseArray(Collection array) {
        this.parseArray(array, null);
    }

    /*
     * Exception decompiling
     */
    public final void parseArray(Collection array, Object fieldName) {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [14[CASE]], but top level block is 2[TRYBLOCK]
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.processEndingBlocks(Op04StructuredStatement.java:435)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:484)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:736)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:850)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:278)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:201)
         *     at org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:94)
         *     at org.benf.cfr.reader.entities.Method.analyse(Method.java:538)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:1055)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:942)
         *     at org.benf.cfr.reader.Driver.doJarVersionTypes(Driver.java:257)
         *     at org.benf.cfr.reader.Driver.doJar(Driver.java:139)
         *     at org.benf.cfr.reader.CfrDriverImpl.analyse(CfrDriverImpl.java:76)
         *     at org.benf.cfr.reader.Main.main(Main.java:54)
         *     at async.DecompilerRunnable.cfrDecompilation(DecompilerRunnable.java:348)
         *     at async.DecompilerRunnable.call(DecompilerRunnable.java:309)
         *     at async.DecompilerRunnable.call(DecompilerRunnable.java:31)
         *     at java.util.concurrent.FutureTask.run(FutureTask.java:266)
         *     at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)
         *     at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
         *     at java.lang.Thread.run(Thread.java:750)
         */
        throw new IllegalStateException("Decompilation failed");
    }

    public ParseContext getContext() {
        return this.context;
    }

    public ParseContext getOwnerContext() {
        return this.context.parent;
    }

    public List<ResolveTask> getResolveTaskList() {
        if (this.resolveTaskList == null) {
            this.resolveTaskList = new ArrayList<ResolveTask>(2);
        }
        return this.resolveTaskList;
    }

    public void addResolveTask(ResolveTask task) {
        if (this.resolveTaskList == null) {
            this.resolveTaskList = new ArrayList<ResolveTask>(2);
        }
        this.resolveTaskList.add(task);
    }

    public ResolveTask getLastResolveTask() {
        return this.resolveTaskList.get(this.resolveTaskList.size() - 1);
    }

    public List<ExtraProcessor> getExtraProcessors() {
        if (this.extraProcessors == null) {
            this.extraProcessors = new ArrayList<ExtraProcessor>(2);
        }
        return this.extraProcessors;
    }

    public List<ExtraTypeProvider> getExtraTypeProviders() {
        if (this.extraTypeProviders == null) {
            this.extraTypeProviders = new ArrayList<ExtraTypeProvider>(2);
        }
        return this.extraTypeProviders;
    }

    public FieldTypeResolver getFieldTypeResolver() {
        return this.fieldTypeResolver;
    }

    public void setFieldTypeResolver(FieldTypeResolver fieldTypeResolver) {
        this.fieldTypeResolver = fieldTypeResolver;
    }

    public void setContext(ParseContext context) {
        if (this.lexer.isEnabled(Feature.DisableCircularReferenceDetect)) {
            return;
        }
        this.context = context;
    }

    public void popContext() {
        if (this.lexer.isEnabled(Feature.DisableCircularReferenceDetect)) {
            return;
        }
        this.context = this.context.parent;
        if (this.contextArrayIndex <= 0) {
            return;
        }
        --this.contextArrayIndex;
        this.contextArray[this.contextArrayIndex] = null;
    }

    public ParseContext setContext(Object object, Object fieldName) {
        if (this.lexer.isEnabled(Feature.DisableCircularReferenceDetect)) {
            return null;
        }
        return this.setContext(this.context, object, fieldName);
    }

    public ParseContext setContext(ParseContext parent, Object object, Object fieldName) {
        if (this.lexer.isEnabled(Feature.DisableCircularReferenceDetect)) {
            return null;
        }
        this.context = new ParseContext(parent, object, fieldName);
        this.addContext(this.context);
        return this.context;
    }

    private void addContext(ParseContext context) {
        int i = this.contextArrayIndex++;
        if (this.contextArray == null) {
            this.contextArray = new ParseContext[8];
        } else if (i >= this.contextArray.length) {
            int newLen = this.contextArray.length * 3 / 2;
            ParseContext[] newArray = new ParseContext[newLen];
            System.arraycopy(this.contextArray, 0, newArray, 0, this.contextArray.length);
            this.contextArray = newArray;
        }
        this.contextArray[i] = context;
    }

    public Object parse() {
        return this.parse(null);
    }

    public Object parseKey() {
        if (this.lexer.token() == 18) {
            String value = this.lexer.stringVal();
            this.lexer.nextToken(16);
            return value;
        }
        return this.parse(null);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public Object parse(Object fieldName) {
        JSONLexer lexer = this.lexer;
        switch (lexer.token()) {
            case 21: {
                lexer.nextToken();
                HashSet set = new HashSet();
                this.parseArray(set, fieldName);
                return set;
            }
            case 22: {
                lexer.nextToken();
                TreeSet treeSet = new TreeSet();
                this.parseArray(treeSet, fieldName);
                return treeSet;
            }
            case 14: {
                List array = this.isEnabled(Feature.UseNativeJavaObject) ? new ArrayList() : new JSONArray();
                this.parseArray(array, fieldName);
                if (lexer.isEnabled(Feature.UseObjectArray)) {
                    return array.toArray();
                }
                return array;
            }
            case 12: {
                Map object = this.isEnabled(Feature.UseNativeJavaObject) ? (lexer.isEnabled(Feature.OrderedField) ? new HashMap() : new LinkedHashMap()) : new JSONObject(lexer.isEnabled(Feature.OrderedField));
                return this.parseObject(object, fieldName);
            }
            case 2: {
                Number intValue = lexer.integerValue();
                lexer.nextToken();
                return intValue;
            }
            case 3: {
                Number value = lexer.decimalValue(lexer.isEnabled(Feature.UseBigDecimal));
                lexer.nextToken();
                return value;
            }
            case 4: {
                String stringLiteral = lexer.stringVal();
                lexer.nextToken(16);
                if (lexer.isEnabled(Feature.AllowISO8601DateFormat)) {
                    JSONScanner iso8601Lexer = new JSONScanner(stringLiteral);
                    try {
                        if (iso8601Lexer.scanISO8601DateIfMatch()) {
                            Date date = iso8601Lexer.getCalendar().getTime();
                            return date;
                        }
                    } finally {
                        iso8601Lexer.close();
                    }
                }
                return stringLiteral;
            }
            case 8: {
                lexer.nextToken();
                return null;
            }
            case 23: {
                lexer.nextToken();
                return null;
            }
            case 6: {
                lexer.nextToken();
                return Boolean.TRUE;
            }
            case 7: {
                lexer.nextToken();
                return Boolean.FALSE;
            }
            case 9: {
                lexer.nextToken(18);
                if (lexer.token() != 18) {
                    throw new JSONException("syntax error");
                }
                lexer.nextToken(10);
                this.accept(10);
                long time = lexer.integerValue().longValue();
                this.accept(2);
                this.accept(11);
                return new Date(time);
            }
            case 20: {
                if (lexer.isBlankInput()) {
                    return null;
                }
                throw new JSONException("unterminated json string, " + lexer.info());
            }
            case 26: {
                byte[] bytes = lexer.bytesValue();
                lexer.nextToken();
                return bytes;
            }
            case 18: {
                String identifier = lexer.stringVal();
                if ("NaN".equals(identifier)) {
                    lexer.nextToken();
                    return null;
                }
                throw new JSONException("syntax error, " + lexer.info());
            }
        }
        throw new JSONException("syntax error, " + lexer.info());
    }

    public void config(Feature feature, boolean state) {
        this.lexer.config(feature, state);
    }

    public boolean isEnabled(Feature feature) {
        return this.lexer.isEnabled(feature);
    }

    public JSONLexer getLexer() {
        return this.lexer;
    }

    public final void accept(int token) {
        JSONLexer lexer = this.lexer;
        if (lexer.token() != token) {
            throw new JSONException("syntax error, expect " + JSONToken.name(token) + ", actual " + JSONToken.name(lexer.token()));
        }
        lexer.nextToken();
    }

    public final void accept(int token, int nextExpectToken) {
        JSONLexer lexer = this.lexer;
        if (lexer.token() == token) {
            lexer.nextToken(nextExpectToken);
        } else {
            this.throwException(token);
        }
    }

    public void throwException(int token) {
        throw new JSONException("syntax error, expect " + JSONToken.name(token) + ", actual " + JSONToken.name(this.lexer.token()));
    }

    @Override
    public void close() {
        JSONLexer lexer = this.lexer;
        try {
            if (lexer.isEnabled(Feature.AutoCloseSource) && lexer.token() != 20) {
                throw new JSONException("not close json text, token : " + JSONToken.name(lexer.token()));
            }
        } finally {
            lexer.close();
        }
    }

    public Object resolveReference(String ref) {
        if (this.contextArray == null) {
            return null;
        }
        for (int i = 0; i < this.contextArray.length && i < this.contextArrayIndex; ++i) {
            ParseContext context = this.contextArray[i];
            if (!context.toString().equals(ref)) continue;
            return context.object;
        }
        return null;
    }

    public void handleResovleTask(Object value) {
        if (this.resolveTaskList == null) {
            return;
        }
        int size = this.resolveTaskList.size();
        for (int i = 0; i < size; ++i) {
            FieldDeserializer fieldDeser;
            Object refValue;
            ResolveTask task = this.resolveTaskList.get(i);
            String ref = task.referenceValue;
            Object object = null;
            if (task.ownerContext != null) {
                object = task.ownerContext.object;
            }
            if (ref.startsWith("$")) {
                refValue = this.getObject(ref);
                if (refValue == null) {
                    try {
                        JSONPath jsonpath = new JSONPath(ref, SerializeConfig.getGlobalInstance(), this.config, true);
                        if (jsonpath.isRef()) {
                            refValue = jsonpath.eval(value);
                        }
                    } catch (JSONPathException jsonpath) {}
                }
            } else {
                refValue = task.context.object;
            }
            if ((fieldDeser = task.fieldDeserializer) == null) continue;
            if (refValue != null && refValue.getClass() == JSONObject.class && fieldDeser.fieldInfo != null && !Map.class.isAssignableFrom(fieldDeser.fieldInfo.fieldClass)) {
                Object root = this.contextArray[0].object;
                JSONPath jsonpath = JSONPath.compile(ref);
                if (jsonpath.isRef()) {
                    refValue = jsonpath.eval(root);
                }
            }
            if (fieldDeser.getOwnerClass() != null && !fieldDeser.getOwnerClass().isInstance(object) && task.ownerContext.parent != null) {
                ParseContext ctx = task.ownerContext.parent;
                while (ctx != null) {
                    if (fieldDeser.getOwnerClass().isInstance(ctx.object)) {
                        object = ctx.object;
                        break;
                    }
                    ctx = ctx.parent;
                }
            }
            fieldDeser.setValue(object, refValue);
        }
    }

    public void parseExtra(Object object, String key) {
        Object value;
        JSONLexer lexer = this.lexer;
        lexer.nextTokenWithColon();
        Type type = null;
        if (this.extraTypeProviders != null) {
            for (ExtraTypeProvider extraProvider : this.extraTypeProviders) {
                type = extraProvider.getExtraType(object, key);
            }
        }
        Object object2 = value = type == null ? this.parse() : this.parseObject(type);
        if (object instanceof ExtraProcessable) {
            ExtraProcessable extraProcessable = (ExtraProcessable)object;
            extraProcessable.processExtra(key, value);
            return;
        }
        if (this.extraProcessors != null) {
            for (ExtraProcessor process : this.extraProcessors) {
                process.processExtra(object, key, value);
            }
        }
        if (this.resolveStatus == 1) {
            this.resolveStatus = 0;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public Object parse(PropertyProcessable object, Object fieldName) {
        if (this.lexer.token() != 12) {
            String msg = "syntax error, expect {, actual " + this.lexer.tokenName();
            if (fieldName instanceof String) {
                msg = msg + ", fieldName ";
                msg = msg + fieldName;
            }
            msg = msg + ", ";
            msg = msg + this.lexer.info();
            JSONArray array = new JSONArray();
            this.parseArray(array, fieldName);
            if (array.size() != 1) throw new JSONException(msg);
            Object first = array.get(0);
            if (!(first instanceof JSONObject)) throw new JSONException(msg);
            return (JSONObject)first;
        }
        ParseContext context = this.context;
        try {
            int i = 0;
            while (true) {
                block33: {
                    Object value;
                    String key;
                    this.lexer.skipWhitespace();
                    char ch = this.lexer.getCurrent();
                    if (this.lexer.isEnabled(Feature.AllowArbitraryCommas)) {
                        while (ch == ',') {
                            this.lexer.next();
                            this.lexer.skipWhitespace();
                            ch = this.lexer.getCurrent();
                        }
                    }
                    if (ch == '\"') {
                        key = this.lexer.scanSymbol(this.symbolTable, '\"');
                        this.lexer.skipWhitespace();
                        ch = this.lexer.getCurrent();
                        if (ch != ':') {
                            throw new JSONException("expect ':' at " + this.lexer.pos());
                        }
                    } else {
                        if (ch == '}') {
                            this.lexer.next();
                            this.lexer.resetStringPosition();
                            this.lexer.nextToken(16);
                            PropertyProcessable propertyProcessable = object;
                            return propertyProcessable;
                        }
                        if (ch == '\'') {
                            if (!this.lexer.isEnabled(Feature.AllowSingleQuotes)) {
                                throw new JSONException("syntax error");
                            }
                            key = this.lexer.scanSymbol(this.symbolTable, '\'');
                            this.lexer.skipWhitespace();
                            ch = this.lexer.getCurrent();
                            if (ch != ':') {
                                throw new JSONException("expect ':' at " + this.lexer.pos());
                            }
                        } else {
                            if (!this.lexer.isEnabled(Feature.AllowUnQuotedFieldNames)) {
                                throw new JSONException("syntax error");
                            }
                            key = this.lexer.scanSymbolUnQuoted(this.symbolTable);
                            this.lexer.skipWhitespace();
                            ch = this.lexer.getCurrent();
                            if (ch != ':') {
                                throw new JSONException("expect ':' at " + this.lexer.pos() + ", actual " + ch);
                            }
                        }
                    }
                    this.lexer.next();
                    this.lexer.skipWhitespace();
                    ch = this.lexer.getCurrent();
                    this.lexer.resetStringPosition();
                    if (key == JSON.DEFAULT_TYPE_KEY && !this.lexer.isEnabled(Feature.DisableSpecialKeyDetect)) {
                        String typeName = this.lexer.scanSymbol(this.symbolTable, '\"');
                        Class<?> clazz = this.config.checkAutoType(typeName, null, this.lexer.getFeatures());
                        if (Map.class.isAssignableFrom(clazz)) {
                            this.lexer.nextToken(16);
                            if (this.lexer.token() == 13) {
                                this.lexer.nextToken(16);
                                PropertyProcessable propertyProcessable = object;
                                return propertyProcessable;
                            }
                            break block33;
                        } else {
                            ObjectDeserializer deserializer = this.config.getDeserializer(clazz);
                            this.lexer.nextToken(16);
                            this.setResolveStatus(2);
                            if (context != null && !(fieldName instanceof Integer)) {
                                this.popContext();
                            }
                            Map map = (Map)deserializer.deserialze(this, clazz, fieldName);
                            return map;
                        }
                    }
                    this.lexer.nextToken();
                    if (i != 0) {
                        this.setContext(context);
                    }
                    Type valueType = object.getType(key);
                    if (this.lexer.token() == 8) {
                        value = null;
                        this.lexer.nextToken();
                    } else {
                        value = this.parseObject(valueType, (Object)key);
                    }
                    object.apply(key, value);
                    this.setContext(context, value, key);
                    this.setContext(context);
                    int tok = this.lexer.token();
                    if (tok == 20 || tok == 15) {
                        PropertyProcessable propertyProcessable = object;
                        return propertyProcessable;
                    }
                    if (tok == 13) {
                        this.lexer.nextToken();
                        PropertyProcessable propertyProcessable = object;
                        return propertyProcessable;
                    }
                }
                ++i;
            }
        } finally {
            this.setContext(context);
        }
    }

    static {
        Class[] classes = new Class[]{Boolean.TYPE, Byte.TYPE, Short.TYPE, Integer.TYPE, Long.TYPE, Float.TYPE, Double.TYPE, Boolean.class, Byte.class, Short.class, Integer.class, Long.class, Float.class, Double.class, BigInteger.class, BigDecimal.class, String.class};
        primitiveClasses.addAll(Arrays.asList(classes));
    }

    public static class ResolveTask {
        public final ParseContext context;
        public final String referenceValue;
        public FieldDeserializer fieldDeserializer;
        public ParseContext ownerContext;

        public ResolveTask(ParseContext context, String referenceValue) {
            this.context = context;
            this.referenceValue = referenceValue;
        }
    }
}

