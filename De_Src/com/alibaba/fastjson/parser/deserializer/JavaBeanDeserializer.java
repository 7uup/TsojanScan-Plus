/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONValidator;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.JSONLexerBase;
import com.alibaba.fastjson.parser.JSONToken;
import com.alibaba.fastjson.parser.ParseContext;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.parser.deserializer.DefaultFieldDeserializer;
import com.alibaba.fastjson.parser.deserializer.EnumDeserializer;
import com.alibaba.fastjson.parser.deserializer.FieldDeserializer;
import com.alibaba.fastjson.parser.deserializer.Jdk8DateCodec;
import com.alibaba.fastjson.parser.deserializer.MapDeserializer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.util.FieldInfo;
import com.alibaba.fastjson.util.JavaBeanInfo;
import com.alibaba.fastjson.util.TypeUtils;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class JavaBeanDeserializer
implements ObjectDeserializer {
    private final FieldDeserializer[] fieldDeserializers;
    protected final FieldDeserializer[] sortedFieldDeserializers;
    protected final Class<?> clazz;
    public final JavaBeanInfo beanInfo;
    private ConcurrentMap<String, Object> extraFieldDeserializers;
    private final Map<String, FieldDeserializer> alterNameFieldDeserializers;
    private Map<String, FieldDeserializer> fieldDeserializerMap;
    private transient long[] smartMatchHashArray;
    private transient short[] smartMatchHashArrayMapping;
    private transient long[] hashArray;
    private transient short[] hashArrayMapping;
    private final ParserConfig.AutoTypeCheckHandler autoTypeCheckHandler;

    public JavaBeanDeserializer(ParserConfig config, Class<?> clazz) {
        this(config, clazz, clazz);
    }

    public JavaBeanDeserializer(ParserConfig config, Class<?> clazz, Type type) {
        this(config, JavaBeanInfo.build(clazz, type, config.propertyNamingStrategy, config.fieldBased, config.compatibleWithJavaBean, config.isJacksonCompatible()));
    }

    public JavaBeanDeserializer(ParserConfig config, JavaBeanInfo beanInfo) {
        FieldDeserializer fieldDeserializer;
        this.clazz = beanInfo.clazz;
        this.beanInfo = beanInfo;
        ParserConfig.AutoTypeCheckHandler autoTypeCheckHandler = null;
        if (beanInfo.jsonType != null && beanInfo.jsonType.autoTypeCheckHandler() != ParserConfig.AutoTypeCheckHandler.class) {
            try {
                autoTypeCheckHandler = beanInfo.jsonType.autoTypeCheckHandler().newInstance();
            } catch (Exception exception) {
                // empty catch block
            }
        }
        this.autoTypeCheckHandler = autoTypeCheckHandler;
        HashMap<String, FieldDeserializer> alterNameFieldDeserializers = null;
        this.sortedFieldDeserializers = new FieldDeserializer[beanInfo.sortedFields.length];
        int size = beanInfo.sortedFields.length;
        for (int i = 0; i < size; ++i) {
            FieldInfo fieldInfo = beanInfo.sortedFields[i];
            this.sortedFieldDeserializers[i] = fieldDeserializer = config.createFieldDeserializer(config, beanInfo, fieldInfo);
            if (size > 128) {
                if (this.fieldDeserializerMap == null) {
                    this.fieldDeserializerMap = new HashMap<String, FieldDeserializer>();
                }
                this.fieldDeserializerMap.put(fieldInfo.name, fieldDeserializer);
            }
            for (String name : fieldInfo.alternateNames) {
                if (alterNameFieldDeserializers == null) {
                    alterNameFieldDeserializers = new HashMap<String, FieldDeserializer>();
                }
                alterNameFieldDeserializers.put(name, fieldDeserializer);
            }
        }
        this.alterNameFieldDeserializers = alterNameFieldDeserializers;
        this.fieldDeserializers = new FieldDeserializer[beanInfo.fields.length];
        for (FieldInfo fieldInfo : beanInfo.fields) {
            this.fieldDeserializers[i] = fieldDeserializer = this.getFieldDeserializer(fieldInfo.name);
        }
    }

    public FieldDeserializer getFieldDeserializer(String key) {
        return this.getFieldDeserializer(key, null);
    }

    public FieldDeserializer getFieldDeserializer(String key, int[] setFlags) {
        FieldDeserializer fieldDeserializer;
        if (key == null) {
            return null;
        }
        if (this.fieldDeserializerMap != null && (fieldDeserializer = this.fieldDeserializerMap.get(key)) != null) {
            return fieldDeserializer;
        }
        int low = 0;
        int high = this.sortedFieldDeserializers.length - 1;
        while (low <= high) {
            int mid = low + high >>> 1;
            String fieldName = this.sortedFieldDeserializers[mid].fieldInfo.name;
            int cmp = fieldName.compareTo(key);
            if (cmp < 0) {
                low = mid + 1;
                continue;
            }
            if (cmp > 0) {
                high = mid - 1;
                continue;
            }
            if (JavaBeanDeserializer.isSetFlag(mid, setFlags)) {
                return null;
            }
            return this.sortedFieldDeserializers[mid];
        }
        if (this.alterNameFieldDeserializers != null) {
            return this.alterNameFieldDeserializers.get(key);
        }
        return null;
    }

    public FieldDeserializer getFieldDeserializer(long hash) {
        short setterIndex;
        int pos;
        if (this.hashArray == null) {
            long[] hashArray = new long[this.sortedFieldDeserializers.length];
            for (int i = 0; i < this.sortedFieldDeserializers.length; ++i) {
                hashArray[i] = TypeUtils.fnv1a_64(this.sortedFieldDeserializers[i].fieldInfo.name);
            }
            Arrays.sort(hashArray);
            this.hashArray = hashArray;
        }
        if ((pos = Arrays.binarySearch(this.hashArray, hash)) < 0) {
            return null;
        }
        if (this.hashArrayMapping == null) {
            short[] mapping = new short[this.hashArray.length];
            Arrays.fill(mapping, (short)-1);
            for (int i = 0; i < this.sortedFieldDeserializers.length; ++i) {
                int p = Arrays.binarySearch(this.hashArray, TypeUtils.fnv1a_64(this.sortedFieldDeserializers[i].fieldInfo.name));
                if (p < 0) continue;
                mapping[p] = (short)i;
            }
            this.hashArrayMapping = mapping;
        }
        if ((setterIndex = this.hashArrayMapping[pos]) != -1) {
            return this.sortedFieldDeserializers[setterIndex];
        }
        return null;
    }

    static boolean isSetFlag(int i, int[] setFlags) {
        if (setFlags == null) {
            return false;
        }
        int flagIndex = i / 32;
        return flagIndex < setFlags.length && (setFlags[flagIndex] & 1 << i % 32) != 0;
    }

    public Object createInstance(DefaultJSONParser parser, Type type) {
        Object object;
        if (type instanceof Class && this.clazz.isInterface()) {
            Class clazz = (Class)type;
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            JSONObject obj = new JSONObject();
            Object proxy = Proxy.newProxyInstance(loader, new Class[]{clazz}, obj);
            return proxy;
        }
        if (this.beanInfo.defaultConstructor == null && this.beanInfo.factoryMethod == null) {
            return null;
        }
        if (this.beanInfo.factoryMethod != null && this.beanInfo.defaultConstructorParameterSize > 0) {
            return null;
        }
        try {
            Constructor<?> constructor = this.beanInfo.defaultConstructor;
            if (this.beanInfo.defaultConstructorParameterSize == 0) {
                object = constructor != null ? constructor.newInstance(new Object[0]) : this.beanInfo.factoryMethod.invoke(null, new Object[0]);
            } else {
                ParseContext context = parser.getContext();
                if (context == null || context.object == null) {
                    throw new JSONException("can't create non-static inner class instance.");
                }
                if (!(type instanceof Class)) {
                    throw new JSONException("can't create non-static inner class instance.");
                }
                String typeName = ((Class)type).getName();
                int lastIndex = typeName.lastIndexOf(36);
                String parentClassName = typeName.substring(0, lastIndex);
                Object ctxObj = context.object;
                String parentName = ctxObj.getClass().getName();
                Object param = null;
                if (!parentName.equals(parentClassName)) {
                    ParseContext parentContext = context.parent;
                    if (parentContext != null && parentContext.object != null && ("java.util.ArrayList".equals(parentName) || "java.util.List".equals(parentName) || "java.util.Collection".equals(parentName) || "java.util.Map".equals(parentName) || "java.util.HashMap".equals(parentName))) {
                        parentName = parentContext.object.getClass().getName();
                        if (parentName.equals(parentClassName)) {
                            param = parentContext.object;
                        }
                    } else {
                        param = ctxObj;
                    }
                } else {
                    param = ctxObj;
                }
                if (param == null || param instanceof Collection && ((Collection)param).isEmpty()) {
                    throw new JSONException("can't create non-static inner class instance.");
                }
                object = constructor.newInstance(param);
            }
        } catch (JSONException e) {
            throw e;
        } catch (Exception e) {
            throw new JSONException("create instance error, class " + this.clazz.getName(), e);
        }
        if (parser != null && parser.lexer.isEnabled(Feature.InitStringFieldAsEmpty)) {
            for (FieldInfo fieldInfo : this.beanInfo.fields) {
                if (fieldInfo.fieldClass != String.class) continue;
                try {
                    fieldInfo.set(object, "");
                } catch (Exception e) {
                    throw new JSONException("create instance error, class " + this.clazz.getName(), e);
                }
            }
        }
        return object;
    }

    @Override
    public <T> T deserialze(DefaultJSONParser parser, Type type, Object fieldName) {
        return this.deserialze(parser, type, fieldName, 0);
    }

    public <T> T deserialze(DefaultJSONParser parser, Type type, Object fieldName, int features) {
        return this.deserialze(parser, type, fieldName, null, features, null);
    }

    public <T> T deserialzeArrayMapping(DefaultJSONParser parser, Type type, Object fieldName, Object object) {
        JSONLexer lexer = parser.lexer;
        if (lexer.token() != 14) {
            throw new JSONException("error");
        }
        String typeName = null;
        typeName = lexer.scanTypeName(parser.symbolTable);
        if (typeName != null) {
            ObjectDeserializer deserializer = JavaBeanDeserializer.getSeeAlso(parser.getConfig(), this.beanInfo, typeName);
            Class<?> userType = null;
            if (deserializer == null) {
                Class<?> expectClass = TypeUtils.getClass(type);
                userType = parser.getConfig().checkAutoType(typeName, expectClass, lexer.getFeatures());
                deserializer = parser.getConfig().getDeserializer(userType);
            }
            if (deserializer instanceof JavaBeanDeserializer) {
                return deserializer.deserialzeArrayMapping(parser, type, fieldName, object);
            }
        }
        object = this.createInstance(parser, type);
        int size = this.sortedFieldDeserializers.length;
        for (int i = 0; i < size; ++i) {
            char seperator = i == size - 1 ? (char)']' : ',';
            FieldDeserializer fieldDeser = this.sortedFieldDeserializers[i];
            Class<?> fieldClass = fieldDeser.fieldInfo.fieldClass;
            if (fieldClass == Integer.TYPE) {
                int value = lexer.scanInt(seperator);
                fieldDeser.setValue(object, value);
                continue;
            }
            if (fieldClass == String.class) {
                String value = lexer.scanString(seperator);
                fieldDeser.setValue(object, value);
                continue;
            }
            if (fieldClass == Long.TYPE) {
                long value = lexer.scanLong(seperator);
                fieldDeser.setValue(object, value);
                continue;
            }
            if (fieldClass.isEnum()) {
                Enum<?> value;
                char ch = lexer.getCurrent();
                if (ch == '\"' || ch == 'n') {
                    value = lexer.scanEnum(fieldClass, parser.getSymbolTable(), seperator);
                } else if (ch >= '0' && ch <= '9') {
                    int ordinal = lexer.scanInt(seperator);
                    EnumDeserializer enumDeser = (EnumDeserializer)((DefaultFieldDeserializer)fieldDeser).getFieldValueDeserilizer(parser.getConfig());
                    value = enumDeser.valueOf(ordinal);
                } else {
                    value = this.scanEnum(lexer, seperator);
                }
                fieldDeser.setValue(object, value);
                continue;
            }
            if (fieldClass == Boolean.TYPE) {
                boolean value = lexer.scanBoolean(seperator);
                fieldDeser.setValue(object, value);
                continue;
            }
            if (fieldClass == Float.TYPE) {
                float value = lexer.scanFloat(seperator);
                fieldDeser.setValue(object, Float.valueOf(value));
                continue;
            }
            if (fieldClass == Double.TYPE) {
                double value = lexer.scanDouble(seperator);
                fieldDeser.setValue(object, value);
                continue;
            }
            if (fieldClass == Date.class && lexer.getCurrent() == '1') {
                long longValue = lexer.scanLong(seperator);
                fieldDeser.setValue(object, new Date(longValue));
                continue;
            }
            if (fieldClass == BigDecimal.class) {
                BigDecimal value = lexer.scanDecimal(seperator);
                fieldDeser.setValue(object, value);
                continue;
            }
            lexer.nextToken(14);
            Object value = parser.parseObject(fieldDeser.fieldInfo.fieldType, (Object)fieldDeser.fieldInfo.name);
            fieldDeser.setValue(object, value);
            if (lexer.token() == 15) break;
            this.check(lexer, seperator == ']' ? 15 : 16);
        }
        lexer.nextToken(16);
        return (T)object;
    }

    protected void check(JSONLexer lexer, int token) {
        if (lexer.token() != token) {
            throw new JSONException("syntax error");
        }
    }

    protected Enum<?> scanEnum(JSONLexer lexer, char seperator) {
        throw new JSONException("illegal enum. " + lexer.info());
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    protected <T> T deserialze(DefaultJSONParser parser, Type type, Object fieldName, Object object, int features, int[] setFlags) {
        if (type == JSON.class) return (T)parser.parse();
        if (type == JSONObject.class) {
            return (T)parser.parse();
        }
        lexer = (JSONLexerBase)parser.lexer;
        config = parser.getConfig();
        token = lexer.token();
        if (token == 8) {
            lexer.nextToken(16);
            return null;
        }
        context = parser.getContext();
        if (object != null && context != null) {
            context = context.parent;
        }
        childContext = null;
        try {
            block166: {
                block178: {
                    block177: {
                        block176: {
                            block167: {
                                fieldValues = null;
                                if (token == 13) {
                                    lexer.nextToken(16);
                                    if (object == null) {
                                        object = this.createInstance(parser, type);
                                    }
                                    var13_13 = object;
                                    return (T)var13_13;
                                }
                                if (token == 14) {
                                    mask = Feature.SupportArrayToBean.mask;
                                    v0 = isSupportArrayToBean = (this.beanInfo.parserFeatures & mask) != 0 || lexer.isEnabled(Feature.SupportArrayToBean) != false || (features & mask) != 0;
                                    if (isSupportArrayToBean) {
                                        var15_30 = this.deserialzeArrayMapping(parser, type, fieldName, object);
                                        return var15_30;
                                    }
                                }
                                if (token != 12 && token != 16) {
                                    block130: {
                                        if (lexer.isBlankInput()) {
                                            mask = null;
                                            return mask;
                                        }
                                        if (token == 4) {
                                            strVal = lexer.stringVal();
                                            if (strVal.length() == 0) {
                                                lexer.nextToken();
                                                isSupportArrayToBean = null;
                                                return isSupportArrayToBean;
                                            }
                                            if (this.beanInfo.jsonType != null) {
                                                for (Class<?> seeAlsoClass : this.beanInfo.jsonType.seeAlso()) {
                                                    if (!Enum.class.isAssignableFrom(seeAlsoClass)) continue;
                                                    try {
                                                        var19_60 = e = Enum.valueOf(seeAlsoClass, strVal);
                                                        return (T)var19_60;
                                                    } catch (IllegalArgumentException e) {
                                                    }
                                                }
                                            }
                                        }
                                        if (token == 14 && lexer.getCurrent() == ']') {
                                            lexer.next();
                                            lexer.nextToken();
                                            strVal = null;
                                            return strVal;
                                        }
                                        if (this.beanInfo.factoryMethod != null && this.beanInfo.fields.length == 1) {
                                            block129: {
                                                try {
                                                    field = this.beanInfo.fields[0];
                                                    if (field.fieldClass != Integer.class) break block129;
                                                    if (token == 2) {
                                                        intValue = lexer.intValue();
                                                        lexer.nextToken();
                                                        var15_32 = this.createFactoryInstance(config, intValue);
                                                        return (T)var15_32;
                                                    }
                                                    break block130;
                                                } catch (Exception ex) {
                                                    throw new JSONException(ex.getMessage(), ex);
                                                }
                                            }
                                            if (field.fieldClass == String.class && token == 4) {
                                                stringVal = lexer.stringVal();
                                                lexer.nextToken();
                                                var15_33 = this.createFactoryInstance(config, stringVal);
                                                return (T)var15_33;
                                            }
                                        }
                                    }
                                    buf = new StringBuilder().append("syntax error, expect {, actual ").append(lexer.tokenName()).append(", pos ").append(lexer.pos());
                                    if (fieldName instanceof String) {
                                        buf.append(", fieldName ").append(fieldName);
                                    }
                                    buf.append(", fastjson-version ").append("1.2.83");
                                    throw new JSONException(buf.toString());
                                }
                                if (parser.resolveStatus == 2) {
                                    parser.resolveStatus = 0;
                                }
                                typeKey = this.beanInfo.typeKey;
                                fieldIndex = 0;
                                notMatchCount = 0;
                                while (true) {
                                    block132: {
                                        block165: {
                                            block162: {
                                                block164: {
                                                    block163: {
                                                        block134: {
                                                            block160: {
                                                                block161: {
                                                                    block158: {
                                                                        block159: {
                                                                            block156: {
                                                                                block157: {
                                                                                    block154: {
                                                                                        block155: {
                                                                                            block152: {
                                                                                                block153: {
                                                                                                    block150: {
                                                                                                        block151: {
                                                                                                            block148: {
                                                                                                                block149: {
                                                                                                                    block146: {
                                                                                                                        block147: {
                                                                                                                            block144: {
                                                                                                                                block145: {
                                                                                                                                    block142: {
                                                                                                                                        block143: {
                                                                                                                                            block140: {
                                                                                                                                                block141: {
                                                                                                                                                    block138: {
                                                                                                                                                        block139: {
                                                                                                                                                            block136: {
                                                                                                                                                                block137: {
                                                                                                                                                                    block135: {
                                                                                                                                                                        key = null;
                                                                                                                                                                        fieldDeserializer = null;
                                                                                                                                                                        fieldInfo = null;
                                                                                                                                                                        fieldClass = null;
                                                                                                                                                                        fieldAnnotation = null;
                                                                                                                                                                        customDeserializer = false;
                                                                                                                                                                        if (fieldIndex < this.sortedFieldDeserializers.length && notMatchCount < 16) {
                                                                                                                                                                            fieldDeserializer = this.sortedFieldDeserializers[fieldIndex];
                                                                                                                                                                            fieldInfo = fieldDeserializer.fieldInfo;
                                                                                                                                                                            fieldClass = fieldInfo.fieldClass;
                                                                                                                                                                            fieldAnnotation = fieldInfo.getAnnotation();
                                                                                                                                                                            if (fieldAnnotation != null && fieldDeserializer instanceof DefaultFieldDeserializer) {
                                                                                                                                                                                customDeserializer = ((DefaultFieldDeserializer)fieldDeserializer).customDeserilizer;
                                                                                                                                                                            }
                                                                                                                                                                        }
                                                                                                                                                                        matchField = false;
                                                                                                                                                                        valueParsed = false;
                                                                                                                                                                        fieldValue = null;
                                                                                                                                                                        if (fieldDeserializer == null) break block134;
                                                                                                                                                                        name_chars = fieldInfo.name_chars;
                                                                                                                                                                        if (!customDeserializer || !lexer.matchField(name_chars)) break block135;
                                                                                                                                                                        matchField = true;
                                                                                                                                                                        break block134;
                                                                                                                                                                    }
                                                                                                                                                                    if (fieldClass != Integer.TYPE && fieldClass != Integer.class) break block136;
                                                                                                                                                                    intVal = lexer.scanFieldInt(name_chars);
                                                                                                                                                                    fieldValue = intVal == 0 && lexer.matchStat == 5 ? null : Integer.valueOf(intVal);
                                                                                                                                                                    if (lexer.matchStat <= 0) break block137;
                                                                                                                                                                    matchField = true;
                                                                                                                                                                    valueParsed = true;
                                                                                                                                                                    break block134;
                                                                                                                                                                }
                                                                                                                                                                if (lexer.matchStat != -2) break block134;
                                                                                                                                                                ++notMatchCount;
                                                                                                                                                                break block132;
                                                                                                                                                            }
                                                                                                                                                            if (fieldClass != Long.TYPE && fieldClass != Long.class) break block138;
                                                                                                                                                            longVal = lexer.scanFieldLong(name_chars);
                                                                                                                                                            fieldValue = longVal == 0L && lexer.matchStat == 5 ? null : Long.valueOf(longVal);
                                                                                                                                                            if (lexer.matchStat <= 0) break block139;
                                                                                                                                                            matchField = true;
                                                                                                                                                            valueParsed = true;
                                                                                                                                                            break block134;
                                                                                                                                                        }
                                                                                                                                                        if (lexer.matchStat != -2) break block134;
                                                                                                                                                        ++notMatchCount;
                                                                                                                                                        break block132;
                                                                                                                                                    }
                                                                                                                                                    if (fieldClass != String.class) break block140;
                                                                                                                                                    fieldValue = lexer.scanFieldString(name_chars);
                                                                                                                                                    if (lexer.matchStat <= 0) break block141;
                                                                                                                                                    matchField = true;
                                                                                                                                                    valueParsed = true;
                                                                                                                                                    break block134;
                                                                                                                                                }
                                                                                                                                                if (lexer.matchStat != -2) break block134;
                                                                                                                                                ++notMatchCount;
                                                                                                                                                break block132;
                                                                                                                                            }
                                                                                                                                            if (fieldClass != Date.class || fieldInfo.format != null) break block142;
                                                                                                                                            fieldValue = lexer.scanFieldDate(name_chars);
                                                                                                                                            if (lexer.matchStat <= 0) break block143;
                                                                                                                                            matchField = true;
                                                                                                                                            valueParsed = true;
                                                                                                                                            break block134;
                                                                                                                                        }
                                                                                                                                        if (lexer.matchStat != -2) break block134;
                                                                                                                                        ++notMatchCount;
                                                                                                                                        break block132;
                                                                                                                                    }
                                                                                                                                    if (fieldClass != BigDecimal.class) break block144;
                                                                                                                                    fieldValue = lexer.scanFieldDecimal(name_chars);
                                                                                                                                    if (lexer.matchStat <= 0) break block145;
                                                                                                                                    matchField = true;
                                                                                                                                    valueParsed = true;
                                                                                                                                    break block134;
                                                                                                                                }
                                                                                                                                if (lexer.matchStat != -2) break block134;
                                                                                                                                ++notMatchCount;
                                                                                                                                break block132;
                                                                                                                            }
                                                                                                                            if (fieldClass != BigInteger.class) break block146;
                                                                                                                            fieldValue = lexer.scanFieldBigInteger(name_chars);
                                                                                                                            if (lexer.matchStat <= 0) break block147;
                                                                                                                            matchField = true;
                                                                                                                            valueParsed = true;
                                                                                                                            break block134;
                                                                                                                        }
                                                                                                                        if (lexer.matchStat != -2) break block134;
                                                                                                                        ++notMatchCount;
                                                                                                                        break block132;
                                                                                                                    }
                                                                                                                    if (fieldClass != Boolean.TYPE && fieldClass != Boolean.class) break block148;
                                                                                                                    booleanVal = lexer.scanFieldBoolean(name_chars);
                                                                                                                    fieldValue = lexer.matchStat == 5 ? null : Boolean.valueOf(booleanVal);
                                                                                                                    if (lexer.matchStat <= 0) break block149;
                                                                                                                    matchField = true;
                                                                                                                    valueParsed = true;
                                                                                                                    break block134;
                                                                                                                }
                                                                                                                if (lexer.matchStat != -2) break block134;
                                                                                                                ++notMatchCount;
                                                                                                                break block132;
                                                                                                            }
                                                                                                            if (fieldClass != Float.TYPE && fieldClass != Float.class) break block150;
                                                                                                            floatVal = lexer.scanFieldFloat(name_chars);
                                                                                                            fieldValue = floatVal == 0.0f && lexer.matchStat == 5 ? null : Float.valueOf(floatVal);
                                                                                                            if (lexer.matchStat <= 0) break block151;
                                                                                                            matchField = true;
                                                                                                            valueParsed = true;
                                                                                                            break block134;
                                                                                                        }
                                                                                                        if (lexer.matchStat != -2) break block134;
                                                                                                        ++notMatchCount;
                                                                                                        break block132;
                                                                                                    }
                                                                                                    if (fieldClass != Double.TYPE && fieldClass != Double.class) break block152;
                                                                                                    doubleVal = lexer.scanFieldDouble(name_chars);
                                                                                                    fieldValue = doubleVal == 0.0 && lexer.matchStat == 5 ? null : Double.valueOf(doubleVal);
                                                                                                    if (lexer.matchStat <= 0) break block153;
                                                                                                    matchField = true;
                                                                                                    valueParsed = true;
                                                                                                    break block134;
                                                                                                }
                                                                                                if (lexer.matchStat != -2) break block134;
                                                                                                ++notMatchCount;
                                                                                                break block132;
                                                                                            }
                                                                                            if (!fieldClass.isEnum() || !(parser.getConfig().getDeserializer(fieldClass) instanceof EnumDeserializer) || fieldAnnotation != null && fieldAnnotation.deserializeUsing() != Void.class) break block154;
                                                                                            if (!(fieldDeserializer instanceof DefaultFieldDeserializer)) break block134;
                                                                                            fieldValueDeserilizer = ((DefaultFieldDeserializer)fieldDeserializer).fieldValueDeserilizer;
                                                                                            fieldValue = this.scanEnum(lexer, name_chars, fieldValueDeserilizer);
                                                                                            if (lexer.matchStat <= 0) break block155;
                                                                                            matchField = true;
                                                                                            valueParsed = true;
                                                                                            break block134;
                                                                                        }
                                                                                        if (lexer.matchStat != -2) break block134;
                                                                                        ++notMatchCount;
                                                                                        break block132;
                                                                                    }
                                                                                    if (fieldClass != int[].class) break block156;
                                                                                    fieldValue = lexer.scanFieldIntArray(name_chars);
                                                                                    if (lexer.matchStat <= 0) break block157;
                                                                                    matchField = true;
                                                                                    valueParsed = true;
                                                                                    break block134;
                                                                                }
                                                                                if (lexer.matchStat != -2) break block134;
                                                                                ++notMatchCount;
                                                                                break block132;
                                                                            }
                                                                            if (fieldClass != float[].class) break block158;
                                                                            fieldValue = lexer.scanFieldFloatArray(name_chars);
                                                                            if (lexer.matchStat <= 0) break block159;
                                                                            matchField = true;
                                                                            valueParsed = true;
                                                                            break block134;
                                                                        }
                                                                        if (lexer.matchStat != -2) break block134;
                                                                        ++notMatchCount;
                                                                        break block132;
                                                                    }
                                                                    if (fieldClass != float[][].class) break block160;
                                                                    fieldValue = lexer.scanFieldFloatArray2(name_chars);
                                                                    if (lexer.matchStat <= 0) break block161;
                                                                    matchField = true;
                                                                    valueParsed = true;
                                                                    break block134;
                                                                }
                                                                if (lexer.matchStat != -2) break block134;
                                                                ++notMatchCount;
                                                                break block132;
                                                            }
                                                            if (!lexer.matchField(name_chars)) break block132;
                                                            matchField = true;
                                                        }
                                                        if (matchField) break block162;
                                                        key = lexer.scanSymbol(parser.symbolTable);
                                                        if (key != null) break block163;
                                                        token = lexer.token();
                                                        if (token == 13) {
                                                            lexer.nextToken(16);
                                                            break;
                                                        }
                                                        if (token == 16 && lexer.isEnabled(Feature.AllowArbitraryCommas)) break block132;
                                                    }
                                                    if ("$ref" == key && context != null) {
                                                        lexer.nextTokenWithColon(4);
                                                        token = lexer.token();
                                                        if (token != 4) throw new JSONException("illegal ref, " + JSONToken.name(token));
                                                        ref = lexer.stringVal();
                                                        if ("@".equals(ref)) {
                                                            object = context.object;
                                                        } else if ("..".equals(ref)) {
                                                            parentContext = context.parent;
                                                            if (parentContext.object != null) {
                                                                object = parentContext.object;
                                                            } else {
                                                                parser.addResolveTask(new DefaultJSONParser.ResolveTask(parentContext, (String)ref));
                                                                parser.resolveStatus = 1;
                                                            }
                                                        } else if ("$".equals(ref)) {
                                                            rootContext = context;
                                                            while (rootContext.parent != null) {
                                                                rootContext = rootContext.parent;
                                                            }
                                                            if (rootContext.object != null) {
                                                                object = rootContext.object;
                                                            } else {
                                                                parser.addResolveTask(new DefaultJSONParser.ResolveTask(rootContext, (String)ref));
                                                                parser.resolveStatus = 1;
                                                            }
                                                        } else {
                                                            if (ref.indexOf(92) > 0) {
                                                                buf = new StringBuilder();
                                                                for (i = 0; i < ref.length(); ++i) {
                                                                    ch = ref.charAt(i);
                                                                    if (ch == '\\') {
                                                                        ch = ref.charAt(++i);
                                                                    }
                                                                    buf.append(ch);
                                                                }
                                                                ref = buf.toString();
                                                            }
                                                            if ((refObj = parser.resolveReference((String)ref)) != null) {
                                                                object = refObj;
                                                            } else {
                                                                parser.addResolveTask(new DefaultJSONParser.ResolveTask(context, (String)ref));
                                                                parser.resolveStatus = 1;
                                                            }
                                                        }
                                                        lexer.nextToken(13);
                                                        if (lexer.token() != 13) {
                                                            throw new JSONException("illegal ref");
                                                        }
                                                        lexer.nextToken(16);
                                                        parser.setContext(context, object, fieldName);
                                                        ref = object;
                                                        return (T)ref;
                                                    }
                                                    if ((typeKey == null || !typeKey.equals(key)) && JSON.DEFAULT_TYPE_KEY != key) break block162;
                                                    lexer.nextTokenWithColon(4);
                                                    if (lexer.token() != 4) throw new JSONException("syntax error");
                                                    typeName = lexer.stringVal();
                                                    lexer.nextToken(16);
                                                    if (!typeName.equals(this.beanInfo.typeName) && !parser.isEnabled(Feature.IgnoreAutoType)) break block164;
                                                    if (lexer.token() == 13) {
                                                        lexer.nextToken();
                                                        break;
                                                    }
                                                    break block132;
                                                }
                                                deserializer /* !! */  = JavaBeanDeserializer.getSeeAlso(config, this.beanInfo, typeName);
                                                userType = null;
                                                if (deserializer /* !! */  != null) ** GOTO lbl344
                                                expectClass = TypeUtils.getClass(type);
                                                if (this.autoTypeCheckHandler != null) {
                                                    userType = this.autoTypeCheckHandler.handler(typeName, expectClass, lexer.getFeatures());
                                                }
                                                if (userType == null && (typeName.equals("java.util.HashMap") || typeName.equals("java.util.LinkedHashMap"))) {
                                                    if (lexer.token() == 13) {
                                                        lexer.nextToken();
                                                        break;
                                                    }
                                                    break block132;
                                                } else {
                                                    if (userType == null) {
                                                        userType = config.checkAutoType(typeName, expectClass, lexer.getFeatures());
                                                    }
                                                    deserializer /* !! */  = parser.getConfig().getDeserializer(userType);
lbl344:
                                                    // 2 sources

                                                    typedObject = deserializer /* !! */ .deserialze(parser, userType, fieldName);
                                                    if (deserializer /* !! */  instanceof JavaBeanDeserializer) {
                                                        javaBeanDeserializer = deserializer /* !! */ ;
                                                        if (typeKey != null && (typeKeyFieldDeser = javaBeanDeserializer.getFieldDeserializer(typeKey)) != null) {
                                                            typeKeyFieldDeser.setValue(typedObject, typeName);
                                                        }
                                                    }
                                                    var29_91 = typedObject;
                                                    return var29_91;
                                                }
                                            }
                                            if (object == null && fieldValues == null) {
                                                object = this.createInstance(parser, type);
                                                if (object == null) {
                                                    fieldValues = new HashMap<String, Integer>(this.fieldDeserializers.length);
                                                }
                                                childContext = parser.setContext(context, object, fieldName);
                                                if (setFlags == null) {
                                                    setFlags = new int[this.fieldDeserializers.length / 32 + 1];
                                                }
                                            }
                                            if (!matchField) break block165;
                                            if (!valueParsed) {
                                                fieldDeserializer.parseField(parser, object, type, fieldValues);
                                            } else {
                                                if (object == null) {
                                                    fieldValues.put(fieldInfo.name, (Integer)fieldValue);
                                                } else if (fieldValue == null) {
                                                    if (fieldClass != Integer.TYPE && fieldClass != Long.TYPE && fieldClass != Float.TYPE && fieldClass != Double.TYPE && fieldClass != Boolean.TYPE) {
                                                        fieldDeserializer.setValue(object, fieldValue);
                                                    }
                                                } else {
                                                    if (fieldClass == String.class && ((features & Feature.TrimStringFieldValue.mask) != 0 || (this.beanInfo.parserFeatures & Feature.TrimStringFieldValue.mask) != 0 || (fieldInfo.parserFeatures & Feature.TrimStringFieldValue.mask) != 0)) {
                                                        fieldValue = ((String)fieldValue).trim();
                                                    }
                                                    fieldDeserializer.setValue(object, fieldValue);
                                                }
                                                if (setFlags != null) {
                                                    flagIndex = fieldIndex / 32;
                                                    bitIndex = fieldIndex % 32;
                                                    v1 = flagIndex;
                                                    setFlags[v1] = setFlags[v1] | 1 << bitIndex;
                                                }
                                                if (lexer.matchStat == 4) {
                                                    break;
                                                }
                                            }
                                            ** GOTO lbl391
                                        }
                                        match = this.parseField(parser, key, object, type, (Map<String, Object>)(fieldValues == null ? new HashMap<K, V>(this.fieldDeserializers.length) : fieldValues), setFlags);
                                        if (!match) {
                                            if (lexer.token() == 13) {
                                                lexer.nextToken();
                                                break;
                                            }
                                        } else {
                                            if (lexer.token() == 17) {
                                                throw new JSONException("syntax error, unexpect token ':'");
                                            }
lbl391:
                                            // 4 sources

                                            if (lexer.token() != 16) {
                                                if (lexer.token() == 13) {
                                                    lexer.nextToken(16);
                                                    break;
                                                }
                                                if (lexer.token() == 18) throw new JSONException("syntax error, unexpect token " + JSONToken.name(lexer.token()));
                                                if (lexer.token() == 1) {
                                                    throw new JSONException("syntax error, unexpect token " + JSONToken.name(lexer.token()));
                                                }
                                            }
                                        }
                                    }
                                    ++fieldIndex;
                                }
                                if (object != null) break block166;
                                if (fieldValues == null) {
                                    object = this.createInstance(parser, type);
                                    if (childContext == null) {
                                        childContext = parser.setContext(context, object, fieldName);
                                    }
                                    fieldIndex = object;
                                    return (T)fieldIndex;
                                }
                                paramNames = this.beanInfo.creatorConstructorParameters;
                                if (paramNames == null) break block167;
                                params = new Object[paramNames.length];
                                for (i = 0; i < paramNames.length; ++i) {
                                    block170: {
                                        block168: {
                                            block175: {
                                                block174: {
                                                    block173: {
                                                        block172: {
                                                            block171: {
                                                                block169: {
                                                                    paramName = paramNames[i];
                                                                    param /* !! */  = fieldValues.remove(paramName);
                                                                    if (param /* !! */  != null) break block168;
                                                                    fieldType = this.beanInfo.creatorConstructorParameterTypes[i];
                                                                    fieldInfo = this.beanInfo.fields[i];
                                                                    if (fieldType != Byte.TYPE) break block169;
                                                                    param /* !! */  = (byte)0;
                                                                    break block170;
                                                                }
                                                                if (fieldType != Short.TYPE) break block171;
                                                                param /* !! */  = (short)0;
                                                                break block170;
                                                            }
                                                            if (fieldType != Integer.TYPE) break block172;
                                                            param /* !! */  = 0;
                                                            break block170;
                                                        }
                                                        if (fieldType != Long.TYPE) break block173;
                                                        param /* !! */  = 0L;
                                                        break block170;
                                                    }
                                                    if (fieldType != Float.TYPE) break block174;
                                                    param /* !! */  = Float.valueOf(0.0f);
                                                    break block170;
                                                }
                                                if (fieldType != Double.TYPE) break block175;
                                                param /* !! */  = 0.0;
                                                break block170;
                                            }
                                            if (fieldType == Boolean.TYPE) {
                                                param /* !! */  = Boolean.FALSE;
                                                break block170;
                                            } else if (fieldType == String.class && (fieldInfo.parserFeatures & Feature.InitStringFieldAsEmpty.mask) != 0) {
                                                param /* !! */  = "";
                                            }
                                            break block170;
                                        }
                                        if (this.beanInfo.creatorConstructorParameterTypes != null && i < this.beanInfo.creatorConstructorParameterTypes.length && (paramType = this.beanInfo.creatorConstructorParameterTypes[i]) instanceof Class && !(paramClass = (Class)paramType).isInstance(param /* !! */ ) && param /* !! */  instanceof List && (list = (List)param /* !! */ ).size() == 1 && paramClass.isInstance(first = list.get(0))) {
                                            param /* !! */  = list.get(0);
                                        }
                                    }
                                    params[i] = param /* !! */ ;
                                }
                                break block176;
                            }
                            fieldInfoList = this.beanInfo.fields;
                            size = fieldInfoList.length;
                            params = new Object[size];
                            for (i = 0; i < size; ++i) {
                                fieldInfo = fieldInfoList[i];
                                param /* !! */  = fieldValues.get(fieldInfo.name);
                                if (param /* !! */  == null) {
                                    fieldType = fieldInfo.fieldType;
                                    if (fieldType == Byte.TYPE) {
                                        param /* !! */  = (byte)0;
                                    } else if (fieldType == Short.TYPE) {
                                        param /* !! */  = (short)0;
                                    } else if (fieldType == Integer.TYPE) {
                                        param /* !! */  = 0;
                                    } else if (fieldType == Long.TYPE) {
                                        param /* !! */  = 0L;
                                    } else if (fieldType == Float.TYPE) {
                                        param /* !! */  = Float.valueOf(0.0f);
                                    } else if (fieldType == Double.TYPE) {
                                        param /* !! */  = 0.0;
                                    } else if (fieldType == Boolean.TYPE) {
                                        param /* !! */  = Boolean.FALSE;
                                    } else if (fieldType == String.class && (fieldInfo.parserFeatures & Feature.InitStringFieldAsEmpty.mask) != 0) {
                                        param /* !! */  = "";
                                    }
                                }
                                params[i] = param /* !! */ ;
                            }
                        }
                        if (this.beanInfo.creatorConstructor == null) break block177;
                        hasNull = false;
                        if (this.beanInfo.kotlin) {
                            for (i = 0; i < params.length; ++i) {
                                if (params[i] != null || this.beanInfo.fields == null || i >= this.beanInfo.fields.length) continue;
                                fieldInfo = this.beanInfo.fields[i];
                                if (fieldInfo.fieldClass != String.class) break;
                                hasNull = true;
                                break;
                            }
                        }
                        try {
                            if (hasNull && this.beanInfo.kotlinDefaultConstructor != null) {
                                object = this.beanInfo.kotlinDefaultConstructor.newInstance(new Object[0]);
                                for (i = 0; i < params.length; ++i) {
                                    param = params[i];
                                    if (param == null || this.beanInfo.fields == null || i >= this.beanInfo.fields.length) continue;
                                    fieldInfo = this.beanInfo.fields[i];
                                    fieldInfo.set(object, param);
                                }
                            } else {
                                object = this.beanInfo.creatorConstructor.newInstance(params);
                            }
                        } catch (Exception e) {
                            throw new JSONException("create instance error, " + paramNames + ", " + this.beanInfo.creatorConstructor.toGenericString(), e);
                        }
                        if (paramNames != null) {
                            for (Map.Entry<K, V> entry : fieldValues.entrySet()) {
                                fieldDeserializer = this.getFieldDeserializer((String)entry.getKey());
                                if (fieldDeserializer == null) continue;
                                fieldDeserializer.setValue(object, entry.getValue());
                            }
                        }
                        break block178;
                    }
                    if (this.beanInfo.factoryMethod != null) {
                        try {
                            object = this.beanInfo.factoryMethod.invoke(null, params);
                        } catch (Exception e) {
                            throw new JSONException("create factory method error, " + this.beanInfo.factoryMethod.toString(), e);
                        }
                    }
                }
                if (childContext != null) {
                    childContext.object = object;
                }
            }
            if ((buildMethod = this.beanInfo.buildMethod) == null) {
                params = object;
                return (T)params;
            }
            try {
                builtObj = buildMethod.invoke(object, new Object[0]);
            } catch (Exception e) {
                throw new JSONException("build object error", e);
            }
            var16_44 = builtObj;
            return (T)var16_44;
        } finally {
            if (childContext != null) {
                childContext.object = object;
            }
            parser.setContext(context);
        }
    }

    protected Enum scanEnum(JSONLexerBase lexer, char[] name_chars, ObjectDeserializer fieldValueDeserilizer) {
        EnumDeserializer enumDeserializer = null;
        if (fieldValueDeserilizer instanceof EnumDeserializer) {
            enumDeserializer = (EnumDeserializer)fieldValueDeserilizer;
        }
        if (enumDeserializer == null) {
            lexer.matchStat = -1;
            return null;
        }
        long enumNameHashCode = lexer.scanEnumSymbol(name_chars);
        if (lexer.matchStat > 0) {
            Enum e = enumDeserializer.getEnumByHashCode(enumNameHashCode);
            if (e == null) {
                if (enumNameHashCode == -3750763034362895579L) {
                    return null;
                }
                if (lexer.isEnabled(Feature.ErrorOnEnumNotMatch)) {
                    throw new JSONException("not match enum value, " + enumDeserializer.enumClass);
                }
            }
            return e;
        }
        return null;
    }

    public boolean parseField(DefaultJSONParser parser, String key, Object object, Type objectType, Map<String, Object> fieldValues) {
        return this.parseField(parser, key, object, objectType, fieldValues, null);
    }

    public boolean parseField(DefaultJSONParser parser, String key, Object object, Type objectType, Map<String, Object> fieldValues, int[] setFlags) {
        JSONLexer lexer = parser.lexer;
        int disableFieldSmartMatchMask = Feature.DisableFieldSmartMatch.mask;
        int initStringFieldAsEmpty = Feature.InitStringFieldAsEmpty.mask;
        FieldDeserializer fieldDeserializer = lexer.isEnabled(disableFieldSmartMatchMask) || (this.beanInfo.parserFeatures & disableFieldSmartMatchMask) != 0 ? this.getFieldDeserializer(key) : (lexer.isEnabled(initStringFieldAsEmpty) || (this.beanInfo.parserFeatures & initStringFieldAsEmpty) != 0 ? this.smartMatch(key) : this.smartMatch(key, setFlags));
        int mask = Feature.SupportNonPublicField.mask;
        if (fieldDeserializer == null && (lexer.isEnabled(mask) || (this.beanInfo.parserFeatures & mask) != 0)) {
            Object deserOrField;
            if (this.extraFieldDeserializers == null) {
                ConcurrentHashMap<String, Object> extraFieldDeserializers = new ConcurrentHashMap<String, Object>(1, 0.75f, 1);
                for (Class<?> c = this.clazz; c != null && c != Object.class; c = c.getSuperclass()) {
                    Field[] fields;
                    for (Field field : fields = c.getDeclaredFields()) {
                        String alteredFieldName;
                        int fieldModifiers;
                        String fieldName = field.getName();
                        if (this.getFieldDeserializer(fieldName) != null || ((fieldModifiers = field.getModifiers()) & 0x10) != 0 || (fieldModifiers & 8) != 0) continue;
                        JSONField jsonField = TypeUtils.getAnnotation(field, JSONField.class);
                        if (jsonField != null && !"".equals(alteredFieldName = jsonField.name())) {
                            fieldName = alteredFieldName;
                        }
                        extraFieldDeserializers.put(fieldName, field);
                    }
                }
                this.extraFieldDeserializers = extraFieldDeserializers;
            }
            if ((deserOrField = this.extraFieldDeserializers.get(key)) != null) {
                if (deserOrField instanceof FieldDeserializer) {
                    fieldDeserializer = (FieldDeserializer)deserOrField;
                } else {
                    Field field = (Field)deserOrField;
                    field.setAccessible(true);
                    FieldInfo fieldInfo = new FieldInfo(key, field.getDeclaringClass(), field.getType(), field.getGenericType(), field, 0, 0, 0);
                    fieldDeserializer = new DefaultFieldDeserializer(parser.getConfig(), this.clazz, fieldInfo);
                    this.extraFieldDeserializers.put(key, fieldDeserializer);
                }
            }
        }
        if (fieldDeserializer == null) {
            if (!lexer.isEnabled(Feature.IgnoreNotMatch)) {
                throw new JSONException("setter not found, class " + this.clazz.getName() + ", property " + key);
            }
            int fieldIndex = -1;
            for (int i = 0; i < this.sortedFieldDeserializers.length; ++i) {
                FieldDeserializer fieldDeser = this.sortedFieldDeserializers[i];
                FieldInfo fieldInfo = fieldDeser.fieldInfo;
                if (!fieldInfo.unwrapped || !(fieldDeser instanceof DefaultFieldDeserializer)) continue;
                if (fieldInfo.field != null) {
                    ObjectDeserializer javaBeanFieldValueDeserializer;
                    DefaultFieldDeserializer defaultFieldDeserializer = (DefaultFieldDeserializer)fieldDeser;
                    ObjectDeserializer fieldValueDeser = defaultFieldDeserializer.getFieldValueDeserilizer(parser.getConfig());
                    if (fieldValueDeser instanceof JavaBeanDeserializer) {
                        javaBeanFieldValueDeserializer = (JavaBeanDeserializer)fieldValueDeser;
                        FieldDeserializer unwrappedFieldDeser = ((JavaBeanDeserializer)javaBeanFieldValueDeserializer).getFieldDeserializer(key);
                        if (unwrappedFieldDeser == null) continue;
                        try {
                            Object fieldObject = fieldInfo.field.get(object);
                            if (fieldObject == null) {
                                fieldObject = ((JavaBeanDeserializer)fieldValueDeser).createInstance(parser, fieldInfo.fieldType);
                                fieldDeser.setValue(object, fieldObject);
                            }
                            lexer.nextTokenWithColon(defaultFieldDeserializer.getFastMatchToken());
                            unwrappedFieldDeser.parseField(parser, fieldObject, objectType, fieldValues);
                            fieldIndex = i;
                            continue;
                        } catch (Exception e) {
                            throw new JSONException("parse unwrapped field error.", e);
                        }
                    }
                    if (!(fieldValueDeser instanceof MapDeserializer)) continue;
                    javaBeanFieldValueDeserializer = (MapDeserializer)fieldValueDeser;
                    try {
                        Map<Object, Object> fieldObject = (Map<Object, Object>)fieldInfo.field.get(object);
                        if (fieldObject == null) {
                            fieldObject = ((MapDeserializer)javaBeanFieldValueDeserializer).createMap(fieldInfo.fieldType);
                            fieldDeser.setValue(object, fieldObject);
                        }
                        lexer.nextTokenWithColon();
                        Object fieldValue = parser.parse(key);
                        fieldObject.put(key, fieldValue);
                    } catch (Exception e) {
                        throw new JSONException("parse unwrapped field error.", e);
                    }
                    fieldIndex = i;
                    continue;
                }
                if (fieldInfo.method.getParameterTypes().length != 2) continue;
                lexer.nextTokenWithColon();
                Object fieldValue = parser.parse(key);
                try {
                    fieldInfo.method.invoke(object, key, fieldValue);
                } catch (Exception e) {
                    throw new JSONException("parse unwrapped field error.", e);
                }
                fieldIndex = i;
            }
            if (fieldIndex != -1) {
                if (setFlags != null) {
                    int flagIndex = fieldIndex / 32;
                    int bitIndex = fieldIndex % 32;
                    int n = flagIndex;
                    setFlags[n] = setFlags[n] | 1 << bitIndex;
                }
                return true;
            }
            parser.parseExtra(object, key);
            return false;
        }
        int fieldIndex = -1;
        for (int i = 0; i < this.sortedFieldDeserializers.length; ++i) {
            if (this.sortedFieldDeserializers[i] != fieldDeserializer) continue;
            fieldIndex = i;
            break;
        }
        if (fieldIndex != -1 && setFlags != null && key.startsWith("_") && JavaBeanDeserializer.isSetFlag(fieldIndex, setFlags)) {
            parser.parseExtra(object, key);
            return false;
        }
        lexer.nextTokenWithColon(fieldDeserializer.getFastMatchToken());
        fieldDeserializer.parseField(parser, object, objectType, fieldValues);
        if (setFlags != null) {
            int flagIndex = fieldIndex / 32;
            int bitIndex = fieldIndex % 32;
            int n = flagIndex;
            setFlags[n] = setFlags[n] | 1 << bitIndex;
        }
        return true;
    }

    public FieldDeserializer smartMatch(String key) {
        return this.smartMatch(key, null);
    }

    public FieldDeserializer smartMatch(String key, int[] setFlags) {
        if (key == null) {
            return null;
        }
        FieldDeserializer fieldDeserializer = this.getFieldDeserializer(key, setFlags);
        if (fieldDeserializer == null) {
            long smartKeyHash;
            int pos;
            if (this.smartMatchHashArray == null) {
                long[] hashArray = new long[this.sortedFieldDeserializers.length];
                for (int i = 0; i < this.sortedFieldDeserializers.length; ++i) {
                    hashArray[i] = this.sortedFieldDeserializers[i].fieldInfo.nameHashCode;
                }
                Arrays.sort(hashArray);
                this.smartMatchHashArray = hashArray;
            }
            if ((pos = Arrays.binarySearch(this.smartMatchHashArray, smartKeyHash = TypeUtils.fnv1a_64_lower(key))) < 0) {
                long smartKeyHash1 = TypeUtils.fnv1a_64_extract(key);
                pos = Arrays.binarySearch(this.smartMatchHashArray, smartKeyHash1);
            }
            boolean is = false;
            if (pos < 0 && (is = key.startsWith("is"))) {
                smartKeyHash = TypeUtils.fnv1a_64_extract(key.substring(2));
                pos = Arrays.binarySearch(this.smartMatchHashArray, smartKeyHash);
            }
            if (pos >= 0) {
                short deserIndex;
                if (this.smartMatchHashArrayMapping == null) {
                    short[] mapping = new short[this.smartMatchHashArray.length];
                    Arrays.fill(mapping, (short)-1);
                    for (int i = 0; i < this.sortedFieldDeserializers.length; ++i) {
                        int p = Arrays.binarySearch(this.smartMatchHashArray, this.sortedFieldDeserializers[i].fieldInfo.nameHashCode);
                        if (p < 0) continue;
                        mapping[p] = (short)i;
                    }
                    this.smartMatchHashArrayMapping = mapping;
                }
                if ((deserIndex = this.smartMatchHashArrayMapping[pos]) != -1 && !JavaBeanDeserializer.isSetFlag(deserIndex, setFlags)) {
                    fieldDeserializer = this.sortedFieldDeserializers[deserIndex];
                }
            }
            if (fieldDeserializer != null) {
                FieldInfo fieldInfo = fieldDeserializer.fieldInfo;
                if ((fieldInfo.parserFeatures & Feature.DisableFieldSmartMatch.mask) != 0) {
                    return null;
                }
                Class<?> fieldClass = fieldInfo.fieldClass;
                if (is && fieldClass != Boolean.TYPE && fieldClass != Boolean.class) {
                    fieldDeserializer = null;
                }
            }
        }
        return fieldDeserializer;
    }

    @Override
    public int getFastMatchToken() {
        return 12;
    }

    private Object createFactoryInstance(ParserConfig config, Object value) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        return this.beanInfo.factoryMethod.invoke(null, value);
    }

    public Object createInstance(Map<String, Object> map, ParserConfig config) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        Object param;
        Object object = null;
        if (this.beanInfo.creatorConstructor == null && this.beanInfo.factoryMethod == null) {
            object = this.createInstance(null, this.clazz);
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                String format;
                String key = entry.getKey();
                Object value = entry.getValue();
                FieldDeserializer fieldDeser = this.smartMatch(key);
                if (fieldDeser == null) continue;
                FieldInfo fieldInfo = fieldDeser.fieldInfo;
                Field field = fieldDeser.fieldInfo.field;
                Type paramType = fieldInfo.fieldType;
                Class<?> fieldClass = fieldInfo.fieldClass;
                JSONField fieldAnnation = fieldInfo.getAnnotation();
                if (fieldInfo.declaringClass != null && (!fieldClass.isInstance(value) || fieldAnnation != null && fieldAnnation.deserializeUsing() != Void.class)) {
                    String input = value instanceof String && JSONValidator.from((String)value).validate() ? (String)value : JSON.toJSONString(value);
                    DefaultJSONParser parser = new DefaultJSONParser(input);
                    fieldDeser.parseField(parser, object, paramType, null);
                    continue;
                }
                if (field != null && fieldInfo.method == null) {
                    String strVal;
                    Class<?> fieldType = field.getType();
                    if (fieldType == Boolean.TYPE) {
                        if (value == Boolean.FALSE) {
                            field.setBoolean(object, false);
                            continue;
                        }
                        if (value == Boolean.TRUE) {
                            field.setBoolean(object, true);
                            continue;
                        }
                    } else if (fieldType == Integer.TYPE) {
                        if (value instanceof Number) {
                            field.setInt(object, ((Number)value).intValue());
                            continue;
                        }
                    } else if (fieldType == Long.TYPE) {
                        if (value instanceof Number) {
                            field.setLong(object, ((Number)value).longValue());
                            continue;
                        }
                    } else if (fieldType == Float.TYPE) {
                        if (value instanceof Number) {
                            field.setFloat(object, ((Number)value).floatValue());
                            continue;
                        }
                        if (value instanceof String) {
                            strVal = (String)value;
                            float floatValue = strVal.length() <= 10 ? TypeUtils.parseFloat(strVal) : Float.parseFloat(strVal);
                            field.setFloat(object, floatValue);
                            continue;
                        }
                    } else if (fieldType == Double.TYPE) {
                        if (value instanceof Number) {
                            field.setDouble(object, ((Number)value).doubleValue());
                            continue;
                        }
                        if (value instanceof String) {
                            strVal = (String)value;
                            double doubleValue = strVal.length() <= 10 ? TypeUtils.parseDouble(strVal) : Double.parseDouble(strVal);
                            field.setDouble(object, doubleValue);
                            continue;
                        }
                    } else if (value != null && paramType == value.getClass()) {
                        field.set(object, value);
                        continue;
                    }
                }
                value = (format = fieldInfo.format) != null && paramType == Date.class ? TypeUtils.castToDate(value, format) : (format != null && paramType instanceof Class && ((Class)paramType).getName().equals("java.time.LocalDateTime") ? Jdk8DateCodec.castToLocalDateTime(value, format) : (paramType instanceof ParameterizedType ? TypeUtils.cast(value, (ParameterizedType)paramType, config) : TypeUtils.cast(value, paramType, config)));
                fieldDeser.setValue(object, value);
            }
            if (this.beanInfo.buildMethod != null) {
                Object builtObj;
                try {
                    builtObj = this.beanInfo.buildMethod.invoke(object, new Object[0]);
                } catch (Exception e) {
                    throw new JSONException("build object error", e);
                }
                return builtObj;
            }
            return object;
        }
        FieldInfo[] fieldInfoList = this.beanInfo.fields;
        int size = fieldInfoList.length;
        Object[] params = new Object[size];
        HashMap<String, Integer> missFields = null;
        for (int i = 0; i < size; ++i) {
            FieldInfo fieldInfo = fieldInfoList[i];
            param = map.get(fieldInfo.name);
            if (param == null) {
                Class<?> fieldClass = fieldInfo.fieldClass;
                if (fieldClass == Integer.TYPE) {
                    param = 0;
                } else if (fieldClass == Long.TYPE) {
                    param = 0L;
                } else if (fieldClass == Short.TYPE) {
                    param = (short)0;
                } else if (fieldClass == Byte.TYPE) {
                    param = (byte)0;
                } else if (fieldClass == Float.TYPE) {
                    param = Float.valueOf(0.0f);
                } else if (fieldClass == Double.TYPE) {
                    param = 0.0;
                } else if (fieldClass == Character.TYPE) {
                    param = Character.valueOf('0');
                } else if (fieldClass == Boolean.TYPE) {
                    param = false;
                }
                if (missFields == null) {
                    missFields = new HashMap<String, Integer>();
                }
                missFields.put(fieldInfo.name, i);
            }
            params[i] = param;
        }
        if (missFields != null) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                Integer index;
                String key = entry.getKey();
                Object value = entry.getValue();
                FieldDeserializer fieldDeser = this.smartMatch(key);
                if (fieldDeser == null || (index = (Integer)missFields.get(fieldDeser.fieldInfo.name)) == null) continue;
                params[index.intValue()] = value;
            }
        }
        if (this.beanInfo.creatorConstructor != null) {
            FieldInfo fieldInfo;
            boolean hasNull = false;
            if (this.beanInfo.kotlin) {
                for (int i = 0; i < params.length; ++i) {
                    param = params[i];
                    if (param == null) {
                        if (this.beanInfo.fields == null || i >= this.beanInfo.fields.length) continue;
                        fieldInfo = this.beanInfo.fields[i];
                        if (fieldInfo.fieldClass != String.class) continue;
                        hasNull = true;
                        continue;
                    }
                    if (param.getClass() == this.beanInfo.fields[i].fieldClass) continue;
                    params[i] = TypeUtils.cast(param, this.beanInfo.fields[i].fieldClass, config);
                }
            }
            if (hasNull && this.beanInfo.kotlinDefaultConstructor != null) {
                try {
                    object = this.beanInfo.kotlinDefaultConstructor.newInstance(new Object[0]);
                    for (int i = 0; i < params.length; ++i) {
                        param = params[i];
                        if (param == null || this.beanInfo.fields == null || i >= this.beanInfo.fields.length) continue;
                        fieldInfo = this.beanInfo.fields[i];
                        fieldInfo.set(object, param);
                    }
                } catch (Exception e) {
                    throw new JSONException("create instance error, " + this.beanInfo.creatorConstructor.toGenericString(), e);
                }
            } else {
                try {
                    object = this.beanInfo.creatorConstructor.newInstance(params);
                } catch (Exception e) {
                    throw new JSONException("create instance error, " + this.beanInfo.creatorConstructor.toGenericString(), e);
                }
            }
        } else if (this.beanInfo.factoryMethod != null) {
            try {
                object = this.beanInfo.factoryMethod.invoke(null, params);
            } catch (Exception e) {
                throw new JSONException("create factory method error, " + this.beanInfo.factoryMethod.toString(), e);
            }
        }
        return object;
    }

    public Type getFieldType(int ordinal) {
        return this.sortedFieldDeserializers[ordinal].fieldInfo.fieldType;
    }

    protected Object parseRest(DefaultJSONParser parser, Type type, Object fieldName, Object instance, int features) {
        return this.parseRest(parser, type, fieldName, instance, features, new int[0]);
    }

    protected Object parseRest(DefaultJSONParser parser, Type type, Object fieldName, Object instance, int features, int[] setFlags) {
        Object value = this.deserialze(parser, type, fieldName, instance, features, setFlags);
        return value;
    }

    protected static JavaBeanDeserializer getSeeAlso(ParserConfig config, JavaBeanInfo beanInfo, String typeName) {
        if (beanInfo.jsonType == null) {
            return null;
        }
        for (Class<?> seeAlsoClass : beanInfo.jsonType.seeAlso()) {
            ObjectDeserializer seeAlsoDeser = config.getDeserializer(seeAlsoClass);
            if (!(seeAlsoDeser instanceof JavaBeanDeserializer)) continue;
            JavaBeanDeserializer seeAlsoJavaBeanDeser = (JavaBeanDeserializer)seeAlsoDeser;
            JavaBeanInfo subBeanInfo = seeAlsoJavaBeanDeser.beanInfo;
            if (subBeanInfo.typeName.equals(typeName)) {
                return seeAlsoJavaBeanDeser;
            }
            JavaBeanDeserializer subSeeAlso = JavaBeanDeserializer.getSeeAlso(config, subBeanInfo, typeName);
            if (subSeeAlso == null) continue;
            return subSeeAlso;
        }
        return null;
    }

    protected static void parseArray(Collection collection, ObjectDeserializer deser, DefaultJSONParser parser, Type type, Object fieldName) {
        char ch;
        JSONLexerBase lexer = (JSONLexerBase)parser.lexer;
        int token = lexer.token();
        if (token == 8) {
            lexer.nextToken(16);
            token = lexer.token();
            return;
        }
        if (token != 14) {
            parser.throwException(token);
        }
        if ((ch = lexer.getCurrent()) == '[') {
            lexer.next();
            lexer.setToken(14);
        } else {
            lexer.nextToken(14);
        }
        if (lexer.token() == 15) {
            lexer.nextToken();
            return;
        }
        int index = 0;
        while (true) {
            Object item = deser.deserialze(parser, type, index);
            collection.add(item);
            ++index;
            if (lexer.token() != 16) break;
            ch = lexer.getCurrent();
            if (ch == '[') {
                lexer.next();
                lexer.setToken(14);
                continue;
            }
            lexer.nextToken(14);
        }
        token = lexer.token();
        if (token != 15) {
            parser.throwException(token);
        }
        if ((ch = lexer.getCurrent()) == ',') {
            lexer.next();
            lexer.setToken(16);
        } else {
            lexer.nextToken(16);
        }
    }
}

