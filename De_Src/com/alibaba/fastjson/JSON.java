/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONAware;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONStreamAware;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.JSONScanner;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.parser.deserializer.ExtraProcessor;
import com.alibaba.fastjson.parser.deserializer.ExtraTypeProvider;
import com.alibaba.fastjson.parser.deserializer.FieldTypeResolver;
import com.alibaba.fastjson.parser.deserializer.ParseProcess;
import com.alibaba.fastjson.serializer.JSONSerializable;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.JavaBeanSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.util.IOUtils;
import com.alibaba.fastjson.util.TypeUtils;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Writer;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.TimeZone;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.zip.GZIPInputStream;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public abstract class JSON
implements JSONStreamAware,
JSONAware {
    public static TimeZone defaultTimeZone = TimeZone.getDefault();
    public static Locale defaultLocale = Locale.getDefault();
    public static String DEFAULT_TYPE_KEY = "@type";
    static final SerializeFilter[] emptyFilters = new SerializeFilter[0];
    public static String DEFFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static int DEFAULT_PARSER_FEATURE;
    public static int DEFAULT_GENERATE_FEATURE;
    private static final ConcurrentHashMap<Type, Type> mixInsMapper;
    private static final ThreadLocal<byte[]> bytesLocal;
    private static final ThreadLocal<char[]> charsLocal;
    public static final String VERSION = "1.2.83";

    private static void config(Properties properties) {
        String featuresProperty = properties.getProperty("fastjson.serializerFeatures.MapSortField");
        int mask = SerializerFeature.MapSortField.getMask();
        if ("true".equals(featuresProperty)) {
            DEFAULT_GENERATE_FEATURE |= mask;
        } else if ("false".equals(featuresProperty)) {
            DEFAULT_GENERATE_FEATURE &= ~mask;
        }
        if ("true".equals(properties.getProperty("parser.features.NonStringKeyAsString"))) {
            DEFAULT_PARSER_FEATURE |= Feature.NonStringKeyAsString.getMask();
        }
        if ("true".equals(properties.getProperty("parser.features.ErrorOnEnumNotMatch")) || "true".equals(properties.getProperty("fastjson.parser.features.ErrorOnEnumNotMatch"))) {
            DEFAULT_PARSER_FEATURE |= Feature.ErrorOnEnumNotMatch.getMask();
        }
        if ("false".equals(properties.getProperty("fastjson.asmEnable"))) {
            ParserConfig.global.setAsmEnable(false);
            SerializeConfig.globalInstance.setAsmEnable(false);
        }
    }

    public static void setDefaultTypeKey(String typeKey) {
        DEFAULT_TYPE_KEY = typeKey;
        ParserConfig.global.symbolTable.addSymbol(typeKey, 0, typeKey.length(), typeKey.hashCode(), true);
    }

    public static Object parse(String text) {
        return JSON.parse(text, DEFAULT_PARSER_FEATURE);
    }

    public static Object parse(String text, ParserConfig config) {
        return JSON.parse(text, config, DEFAULT_PARSER_FEATURE);
    }

    public static Object parse(String text, ParserConfig config, Feature ... features) {
        int featureValues = DEFAULT_PARSER_FEATURE;
        for (Feature feature : features) {
            featureValues = Feature.config(featureValues, feature, true);
        }
        return JSON.parse(text, config, featureValues);
    }

    public static Object parse(String text, ParserConfig config, int features) {
        if (text == null) {
            return null;
        }
        DefaultJSONParser parser = new DefaultJSONParser(text, config, features);
        Object value = parser.parse();
        parser.handleResovleTask(value);
        parser.close();
        return value;
    }

    public static Object parse(String text, int features) {
        return JSON.parse(text, ParserConfig.getGlobalInstance(), features);
    }

    public static Object parse(byte[] input, Feature ... features) {
        char[] chars = JSON.allocateChars(input.length);
        int len = IOUtils.decodeUTF8(input, 0, input.length, chars);
        if (len < 0) {
            return null;
        }
        return JSON.parse(new String(chars, 0, len), features);
    }

    public static Object parse(byte[] input, int off, int len, CharsetDecoder charsetDecoder, Feature ... features) {
        if (input == null || input.length == 0) {
            return null;
        }
        int featureValues = DEFAULT_PARSER_FEATURE;
        for (Feature feature : features) {
            featureValues = Feature.config(featureValues, feature, true);
        }
        return JSON.parse(input, off, len, charsetDecoder, featureValues);
    }

    public static Object parse(byte[] input, int off, int len, CharsetDecoder charsetDecoder, int features) {
        charsetDecoder.reset();
        int scaleLength = (int)((double)len * (double)charsetDecoder.maxCharsPerByte());
        char[] chars = JSON.allocateChars(scaleLength);
        ByteBuffer byteBuf = ByteBuffer.wrap(input, off, len);
        CharBuffer charBuf = CharBuffer.wrap(chars);
        IOUtils.decode(charsetDecoder, byteBuf, charBuf);
        int position = charBuf.position();
        DefaultJSONParser parser = new DefaultJSONParser(chars, position, ParserConfig.getGlobalInstance(), features);
        Object value = parser.parse();
        parser.handleResovleTask(value);
        parser.close();
        return value;
    }

    public static Object parse(String text, Feature ... features) {
        int featureValues = DEFAULT_PARSER_FEATURE;
        for (Feature feature : features) {
            featureValues = Feature.config(featureValues, feature, true);
        }
        return JSON.parse(text, featureValues);
    }

    public static JSONObject parseObject(String text, Feature ... features) {
        return (JSONObject)JSON.parse(text, features);
    }

    public static JSONObject parseObject(String text) {
        Object obj = JSON.parse(text);
        if (obj instanceof JSONObject) {
            return (JSONObject)obj;
        }
        try {
            return (JSONObject)JSON.toJSON(obj);
        } catch (RuntimeException e) {
            throw new JSONException("can not cast to JSONObject.", e);
        }
    }

    public static <T> T parseObject(String text, TypeReference<T> type, Feature ... features) {
        return JSON.parseObject(text, type.type, ParserConfig.global, DEFAULT_PARSER_FEATURE, features);
    }

    public static <T> T parseObject(String json, Class<T> clazz, Feature ... features) {
        return JSON.parseObject(json, clazz, ParserConfig.global, null, DEFAULT_PARSER_FEATURE, features);
    }

    public static <T> T parseObject(String text, Class<T> clazz, ParseProcess processor, Feature ... features) {
        return JSON.parseObject(text, clazz, ParserConfig.global, processor, DEFAULT_PARSER_FEATURE, features);
    }

    public static <T> T parseObject(String json, Type type, Feature ... features) {
        return JSON.parseObject(json, type, ParserConfig.global, DEFAULT_PARSER_FEATURE, features);
    }

    public static <T> T parseObject(String input, Type clazz, ParseProcess processor, Feature ... features) {
        return JSON.parseObject(input, clazz, ParserConfig.global, processor, DEFAULT_PARSER_FEATURE, features);
    }

    public static <T> T parseObject(String input, Type clazz, int featureValues, Feature ... features) {
        if (input == null) {
            return null;
        }
        for (Feature feature : features) {
            featureValues = Feature.config(featureValues, feature, true);
        }
        DefaultJSONParser parser = new DefaultJSONParser(input, ParserConfig.getGlobalInstance(), featureValues);
        Object value = parser.parseObject(clazz);
        parser.handleResovleTask(value);
        parser.close();
        return value;
    }

    public static <T> T parseObject(String input, Type clazz, ParserConfig config, Feature ... features) {
        return JSON.parseObject(input, clazz, config, null, DEFAULT_PARSER_FEATURE, features);
    }

    public static <T> T parseObject(String input, Type clazz, ParserConfig config, int featureValues, Feature ... features) {
        return JSON.parseObject(input, clazz, config, null, featureValues, features);
    }

    public static <T> T parseObject(String input, Type clazz, ParserConfig config, ParseProcess processor, int featureValues, Feature ... features) {
        if (input == null || input.length() == 0) {
            return null;
        }
        if (features != null) {
            for (Feature feature : features) {
                featureValues |= feature.mask;
            }
        }
        DefaultJSONParser parser = new DefaultJSONParser(input, config, featureValues);
        if (processor != null) {
            if (processor instanceof ExtraTypeProvider) {
                parser.getExtraTypeProviders().add((ExtraTypeProvider)processor);
            }
            if (processor instanceof ExtraProcessor) {
                parser.getExtraProcessors().add((ExtraProcessor)processor);
            }
            if (processor instanceof FieldTypeResolver) {
                parser.setFieldTypeResolver((FieldTypeResolver)processor);
            }
        }
        Object value = parser.parseObject(clazz, null);
        parser.handleResovleTask(value);
        parser.close();
        return value;
    }

    public static <T> T parseObject(byte[] bytes, Type clazz, Feature ... features) {
        return JSON.parseObject(bytes, 0, bytes.length, IOUtils.UTF8, clazz, features);
    }

    public static <T> T parseObject(byte[] bytes, int offset, int len, Charset charset, Type clazz, Feature ... features) {
        return JSON.parseObject(bytes, offset, len, charset, clazz, ParserConfig.global, null, DEFAULT_PARSER_FEATURE, features);
    }

    public static <T> T parseObject(byte[] bytes, Charset charset, Type clazz, ParserConfig config, ParseProcess processor, int featureValues, Feature ... features) {
        return JSON.parseObject(bytes, 0, bytes.length, charset, clazz, config, processor, featureValues, features);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static <T> T parseObject(byte[] bytes, int offset, int len, Charset charset, Type clazz, ParserConfig config, ParseProcess processor, int featureValues, Feature ... features) {
        if (charset == null) {
            charset = IOUtils.UTF8;
        }
        String strVal = null;
        if (charset == IOUtils.UTF8) {
            char[] chars = JSON.allocateChars(bytes.length);
            int chars_len = IOUtils.decodeUTF8(bytes, offset, len, chars);
            if (chars_len < 0) {
                InputStreamReader gzipReader = null;
                try {
                    gzipReader = new InputStreamReader((InputStream)new GZIPInputStream(new ByteArrayInputStream(bytes, offset, len)), "UTF-8");
                    strVal = IOUtils.readAll(gzipReader);
                    IOUtils.close(gzipReader);
                } catch (Exception ex) {
                    T t = null;
                    return t;
                } finally {
                    IOUtils.close(gzipReader);
                }
            }
            if (strVal == null && chars_len < 0) {
                return null;
            }
            if (strVal == null) {
                strVal = new String(chars, 0, chars_len);
            }
        } else {
            if (len < 0) {
                return null;
            }
            strVal = new String(bytes, offset, len, charset);
        }
        return JSON.parseObject(strVal, clazz, config, processor, featureValues, features);
    }

    public static <T> T parseObject(byte[] input, int off, int len, CharsetDecoder charsetDecoder, Type clazz, Feature ... features) {
        charsetDecoder.reset();
        int scaleLength = (int)((double)len * (double)charsetDecoder.maxCharsPerByte());
        char[] chars = JSON.allocateChars(scaleLength);
        ByteBuffer byteBuf = ByteBuffer.wrap(input, off, len);
        CharBuffer charByte = CharBuffer.wrap(chars);
        IOUtils.decode(charsetDecoder, byteBuf, charByte);
        int position = charByte.position();
        return JSON.parseObject(chars, position, clazz, features);
    }

    public static <T> T parseObject(char[] input, int length, Type clazz, Feature ... features) {
        if (input == null || input.length == 0) {
            return null;
        }
        int featureValues = DEFAULT_PARSER_FEATURE;
        for (Feature feature : features) {
            featureValues = Feature.config(featureValues, feature, true);
        }
        DefaultJSONParser parser = new DefaultJSONParser(input, length, ParserConfig.getGlobalInstance(), featureValues);
        Object value = parser.parseObject(clazz);
        parser.handleResovleTask(value);
        parser.close();
        return value;
    }

    public static <T> T parseObject(InputStream is, Type type, Feature ... features) throws IOException {
        return JSON.parseObject(is, IOUtils.UTF8, type, features);
    }

    public static <T> T parseObject(InputStream is, Charset charset, Type type, Feature ... features) throws IOException {
        return JSON.parseObject(is, charset, type, ParserConfig.global, features);
    }

    public static <T> T parseObject(InputStream is, Charset charset, Type type, ParserConfig config, Feature ... features) throws IOException {
        return JSON.parseObject(is, charset, type, config, null, DEFAULT_PARSER_FEATURE, features);
    }

    public static <T> T parseObject(InputStream is, Charset charset, Type type, ParserConfig config, ParseProcess processor, int featureValues, Feature ... features) throws IOException {
        int readCount;
        if (charset == null) {
            charset = IOUtils.UTF8;
        }
        byte[] bytes = JSON.allocateBytes(65536);
        int offset = 0;
        while ((readCount = is.read(bytes, offset, bytes.length - offset)) != -1) {
            if ((offset += readCount) != bytes.length) continue;
            byte[] newBytes = new byte[bytes.length * 3 / 2];
            System.arraycopy(bytes, 0, newBytes, 0, bytes.length);
            bytes = newBytes;
        }
        return JSON.parseObject(bytes, 0, offset, charset, type, config, processor, featureValues, features);
    }

    public static <T> T parseObject(String text, Class<T> clazz) {
        return JSON.parseObject(text, clazz, new Feature[0]);
    }

    public static JSONArray parseArray(String text) {
        return JSON.parseArray(text, ParserConfig.global);
    }

    public static JSONArray parseArray(String text, ParserConfig parserConfig) {
        JSONArray array;
        if (text == null) {
            return null;
        }
        DefaultJSONParser parser = new DefaultJSONParser(text, parserConfig);
        JSONLexer lexer = parser.lexer;
        if (lexer.token() == 8) {
            lexer.nextToken();
            array = null;
        } else if (lexer.token() == 20 && lexer.isBlankInput()) {
            array = null;
        } else {
            array = new JSONArray();
            parser.parseArray(array);
            parser.handleResovleTask(array);
        }
        parser.close();
        return array;
    }

    public static <T> List<T> parseArray(String text, Class<T> clazz) {
        return JSON.parseArray(text, clazz, ParserConfig.global);
    }

    public static <T> List<T> parseArray(String text, Class<T> clazz, ParserConfig config) {
        ArrayList list;
        if (text == null) {
            return null;
        }
        DefaultJSONParser parser = new DefaultJSONParser(text, config);
        JSONLexer lexer = parser.lexer;
        int token = lexer.token();
        if (token == 8) {
            lexer.nextToken();
            list = null;
        } else if (token == 20 && lexer.isBlankInput()) {
            list = null;
        } else {
            list = new ArrayList();
            parser.parseArray(clazz, list);
            parser.handleResovleTask(list);
        }
        parser.close();
        return list;
    }

    public static List<Object> parseArray(String text, Type[] types) {
        return JSON.parseArray(text, types, ParserConfig.global);
    }

    public static List<Object> parseArray(String text, Type[] types, ParserConfig config) {
        if (text == null) {
            return null;
        }
        DefaultJSONParser parser = new DefaultJSONParser(text, config);
        Object[] objectArray = parser.parseArray(types);
        List<Object> list = objectArray == null ? null : Arrays.asList(objectArray);
        parser.handleResovleTask(list);
        parser.close();
        return list;
    }

    public static String toJSONString(Object object) {
        return JSON.toJSONString(object, emptyFilters, new SerializerFeature[0]);
    }

    public static String toJSONString(Object object, SerializerFeature ... features) {
        return JSON.toJSONString(object, DEFAULT_GENERATE_FEATURE, features);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static String toJSONString(Object object, int defaultFeatures, SerializerFeature ... features) {
        SerializeWriter out = new SerializeWriter((Writer)null, defaultFeatures, features);
        try {
            JSONSerializer serializer = new JSONSerializer(out);
            serializer.write(object);
            String outString = out.toString();
            int len = outString.length();
            if (len > 0 && outString.charAt(len - 1) == '.' && object instanceof Number && !out.isEnabled(SerializerFeature.WriteClassName)) {
                String string = outString.substring(0, len - 1);
                return string;
            }
            String string = outString;
            return string;
        } finally {
            out.close();
        }
    }

    public static String toJSONStringWithDateFormat(Object object, String dateFormat, SerializerFeature ... features) {
        return JSON.toJSONString(object, SerializeConfig.globalInstance, null, dateFormat, DEFAULT_GENERATE_FEATURE, features);
    }

    public static String toJSONString(Object object, SerializeFilter filter, SerializerFeature ... features) {
        return JSON.toJSONString(object, SerializeConfig.globalInstance, new SerializeFilter[]{filter}, null, DEFAULT_GENERATE_FEATURE, features);
    }

    public static String toJSONString(Object object, SerializeFilter[] filters, SerializerFeature ... features) {
        return JSON.toJSONString(object, SerializeConfig.globalInstance, filters, null, DEFAULT_GENERATE_FEATURE, features);
    }

    public static byte[] toJSONBytes(Object object, SerializerFeature ... features) {
        return JSON.toJSONBytes(object, DEFAULT_GENERATE_FEATURE, features);
    }

    public static byte[] toJSONBytes(Object object, SerializeFilter filter, SerializerFeature ... features) {
        return JSON.toJSONBytes(object, SerializeConfig.globalInstance, new SerializeFilter[]{filter}, DEFAULT_GENERATE_FEATURE, features);
    }

    public static byte[] toJSONBytes(Object object, int defaultFeatures, SerializerFeature ... features) {
        return JSON.toJSONBytes(object, SerializeConfig.globalInstance, defaultFeatures, features);
    }

    public static String toJSONString(Object object, SerializeConfig config, SerializerFeature ... features) {
        return JSON.toJSONString(object, config, (SerializeFilter)null, features);
    }

    public static String toJSONString(Object object, SerializeConfig config, SerializeFilter filter, SerializerFeature ... features) {
        return JSON.toJSONString(object, config, new SerializeFilter[]{filter}, null, DEFAULT_GENERATE_FEATURE, features);
    }

    public static String toJSONString(Object object, SerializeConfig config, SerializeFilter[] filters, SerializerFeature ... features) {
        return JSON.toJSONString(object, config, filters, null, DEFAULT_GENERATE_FEATURE, features);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static String toJSONString(Object object, SerializeConfig config, SerializeFilter[] filters, String dateFormat, int defaultFeatures, SerializerFeature ... features) {
        SerializeWriter out = new SerializeWriter(null, defaultFeatures, features);
        try {
            JSONSerializer serializer = new JSONSerializer(out, config);
            if (dateFormat != null && dateFormat.length() != 0) {
                serializer.setDateFormat(dateFormat);
                serializer.config(SerializerFeature.WriteDateUseDateFormat, true);
            }
            if (filters != null) {
                for (SerializeFilter filter : filters) {
                    serializer.addFilter(filter);
                }
            }
            serializer.write(object);
            String string = out.toString();
            return string;
        } finally {
            out.close();
        }
    }

    public static String toJSONStringZ(Object object, SerializeConfig mapping, SerializerFeature ... features) {
        return JSON.toJSONString(object, mapping, emptyFilters, null, 0, features);
    }

    public static byte[] toJSONBytes(Object object, SerializeConfig config, SerializerFeature ... features) {
        return JSON.toJSONBytes(object, config, emptyFilters, DEFAULT_GENERATE_FEATURE, features);
    }

    public static byte[] toJSONBytes(Object object, SerializeConfig config, int defaultFeatures, SerializerFeature ... features) {
        return JSON.toJSONBytes(object, config, emptyFilters, defaultFeatures, features);
    }

    public static byte[] toJSONBytes(Object object, SerializeFilter[] filters, SerializerFeature ... features) {
        return JSON.toJSONBytes(object, SerializeConfig.globalInstance, filters, DEFAULT_GENERATE_FEATURE, features);
    }

    public static byte[] toJSONBytes(Object object, SerializeConfig config, SerializeFilter filter, SerializerFeature ... features) {
        return JSON.toJSONBytes(object, config, new SerializeFilter[]{filter}, DEFAULT_GENERATE_FEATURE, features);
    }

    public static byte[] toJSONBytes(Object object, SerializeConfig config, SerializeFilter[] filters, int defaultFeatures, SerializerFeature ... features) {
        return JSON.toJSONBytes(object, config, filters, null, defaultFeatures, features);
    }

    public static byte[] toJSONBytes(Object object, SerializeConfig config, SerializeFilter[] filters, String dateFormat, int defaultFeatures, SerializerFeature ... features) {
        return JSON.toJSONBytes(IOUtils.UTF8, object, config, filters, dateFormat, defaultFeatures, features);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static byte[] toJSONBytes(Charset charset, Object object, SerializeConfig config, SerializeFilter[] filters, String dateFormat, int defaultFeatures, SerializerFeature ... features) {
        SerializeWriter out = new SerializeWriter(null, defaultFeatures, features);
        try {
            JSONSerializer serializer = new JSONSerializer(out, config);
            if (dateFormat != null && dateFormat.length() != 0) {
                serializer.setDateFormat(dateFormat);
                serializer.config(SerializerFeature.WriteDateUseDateFormat, true);
            }
            if (filters != null) {
                for (SerializeFilter filter : filters) {
                    serializer.addFilter(filter);
                }
            }
            serializer.write(object);
            byte[] byArray = out.toBytes(charset);
            return byArray;
        } finally {
            out.close();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static byte[] toJSONBytesWithFastJsonConfig(Charset charset, Object object, SerializeConfig config, SerializeFilter[] filters, String dateFormat, int defaultFeatures, SerializerFeature ... features) {
        SerializeWriter out = new SerializeWriter(null, defaultFeatures, features);
        try {
            JSONSerializer serializer = new JSONSerializer(out, config);
            if (dateFormat != null && dateFormat.length() != 0) {
                serializer.setFastJsonConfigDateFormatPattern(dateFormat);
                serializer.config(SerializerFeature.WriteDateUseDateFormat, true);
            }
            if (filters != null) {
                for (SerializeFilter filter : filters) {
                    serializer.addFilter(filter);
                }
            }
            serializer.write(object);
            byte[] byArray = out.toBytes(charset);
            return byArray;
        } finally {
            out.close();
        }
    }

    public static String toJSONString(Object object, boolean prettyFormat) {
        if (!prettyFormat) {
            return JSON.toJSONString(object);
        }
        return JSON.toJSONString(object, SerializerFeature.PrettyFormat);
    }

    public static void writeJSONStringTo(Object object, Writer writer, SerializerFeature ... features) {
        JSON.writeJSONString(writer, object, features);
    }

    public static void writeJSONString(Writer writer, Object object, SerializerFeature ... features) {
        JSON.writeJSONString(writer, object, DEFAULT_GENERATE_FEATURE, features);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void writeJSONString(Writer writer, Object object, int defaultFeatures, SerializerFeature ... features) {
        SerializeWriter out = new SerializeWriter(writer, defaultFeatures, features);
        try {
            JSONSerializer serializer = new JSONSerializer(out);
            serializer.write(object);
        } finally {
            out.close();
        }
    }

    public static final int writeJSONString(OutputStream os, Object object, SerializerFeature ... features) throws IOException {
        return JSON.writeJSONString(os, object, DEFAULT_GENERATE_FEATURE, features);
    }

    public static final int writeJSONString(OutputStream os, Object object, int defaultFeatures, SerializerFeature ... features) throws IOException {
        return JSON.writeJSONString(os, IOUtils.UTF8, object, SerializeConfig.globalInstance, null, null, defaultFeatures, features);
    }

    public static final int writeJSONString(OutputStream os, Charset charset, Object object, SerializerFeature ... features) throws IOException {
        return JSON.writeJSONString(os, charset, object, SerializeConfig.globalInstance, null, null, DEFAULT_GENERATE_FEATURE, features);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static final int writeJSONString(OutputStream os, Charset charset, Object object, SerializeConfig config, SerializeFilter[] filters, String dateFormat, int defaultFeatures, SerializerFeature ... features) throws IOException {
        SerializeWriter writer = new SerializeWriter(null, defaultFeatures, features);
        try {
            int len;
            JSONSerializer serializer = new JSONSerializer(writer, config);
            if (dateFormat != null && dateFormat.length() != 0) {
                serializer.setDateFormat(dateFormat);
                serializer.config(SerializerFeature.WriteDateUseDateFormat, true);
            }
            if (filters != null) {
                for (SerializeFilter filter : filters) {
                    serializer.addFilter(filter);
                }
            }
            serializer.write(object);
            int n = len = writer.writeToEx(os, charset);
            return n;
        } finally {
            writer.close();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static final int writeJSONStringWithFastJsonConfig(OutputStream os, Charset charset, Object object, SerializeConfig config, SerializeFilter[] filters, String dateFormat, int defaultFeatures, SerializerFeature ... features) throws IOException {
        SerializeWriter writer = new SerializeWriter(null, defaultFeatures, features);
        try {
            int len;
            JSONSerializer serializer = new JSONSerializer(writer, config);
            if (dateFormat != null && dateFormat.length() != 0) {
                serializer.setFastJsonConfigDateFormatPattern(dateFormat);
                serializer.config(SerializerFeature.WriteDateUseDateFormat, true);
            }
            if (filters != null) {
                for (SerializeFilter filter : filters) {
                    serializer.addFilter(filter);
                }
            }
            serializer.write(object);
            int n = len = writer.writeToEx(os, charset);
            return n;
        } finally {
            writer.close();
        }
    }

    public String toString() {
        return this.toJSONString();
    }

    @Override
    public String toJSONString() {
        SerializeWriter out = new SerializeWriter();
        try {
            new JSONSerializer(out).write(this);
            String string = out.toString();
            return string;
        } finally {
            out.close();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public String toString(SerializerFeature ... features) {
        SerializeWriter out = new SerializeWriter(null, DEFAULT_GENERATE_FEATURE, features);
        try {
            new JSONSerializer(out).write(this);
            String string = out.toString();
            return string;
        } finally {
            out.close();
        }
    }

    @Override
    public void writeJSONString(Appendable appendable) {
        SerializeWriter out = new SerializeWriter();
        try {
            new JSONSerializer(out).write(this);
            appendable.append(out.toString());
        } catch (IOException e) {
            throw new JSONException(e.getMessage(), e);
        } finally {
            out.close();
        }
    }

    public static Object toJSON(Object javaObject) {
        return JSON.toJSON(javaObject, SerializeConfig.globalInstance);
    }

    public static Object toJSON(Object javaObject, ParserConfig parserConfig) {
        return JSON.toJSON(javaObject, SerializeConfig.globalInstance);
    }

    public static Object toJSON(Object javaObject, SerializeConfig config) {
        if (javaObject == null) {
            return null;
        }
        if (javaObject instanceof JSON) {
            return javaObject;
        }
        if (javaObject instanceof Map) {
            Map map = (Map)javaObject;
            int size = map.size();
            AbstractMap innerMap = map instanceof LinkedHashMap ? new LinkedHashMap(size) : (map instanceof TreeMap ? new TreeMap() : new HashMap(size));
            JSONObject json = new JSONObject(innerMap);
            for (Map.Entry entry : map.entrySet()) {
                Object key = entry.getKey();
                String jsonKey = TypeUtils.castToString(key);
                Object jsonValue = JSON.toJSON(entry.getValue(), config);
                json.put(jsonKey, jsonValue);
            }
            return json;
        }
        if (javaObject instanceof Collection) {
            Collection collection = (Collection)javaObject;
            JSONArray array = new JSONArray(collection.size());
            for (Object item : collection) {
                Object jsonValue = JSON.toJSON(item, config);
                array.add(jsonValue);
            }
            return array;
        }
        if (javaObject instanceof JSONSerializable) {
            String json = JSON.toJSONString(javaObject);
            return JSON.parse(json);
        }
        Class<?> clazz = javaObject.getClass();
        if (clazz.isEnum()) {
            return ((Enum)javaObject).name();
        }
        if (clazz.isArray()) {
            int len = Array.getLength(javaObject);
            JSONArray array = new JSONArray(len);
            for (int i = 0; i < len; ++i) {
                Object item = Array.get(javaObject, i);
                Object jsonValue = JSON.toJSON(item);
                array.add(jsonValue);
            }
            return array;
        }
        if (ParserConfig.isPrimitive2(clazz)) {
            return javaObject;
        }
        ObjectSerializer serializer = config.getObjectWriter(clazz);
        if (serializer instanceof JavaBeanSerializer) {
            JavaBeanSerializer javaBeanSerializer = (JavaBeanSerializer)serializer;
            JSONType jsonType = javaBeanSerializer.getJSONType();
            boolean ordered = false;
            if (jsonType != null) {
                for (SerializerFeature serializerFeature : jsonType.serialzeFeatures()) {
                    if (serializerFeature != SerializerFeature.SortField && serializerFeature != SerializerFeature.MapSortField) continue;
                    ordered = true;
                }
            }
            JSONObject json = new JSONObject(ordered);
            try {
                Map<String, Object> values2 = javaBeanSerializer.getFieldValuesMap(javaObject);
                for (Map.Entry<String, Object> entry : values2.entrySet()) {
                    json.put(entry.getKey(), JSON.toJSON(entry.getValue(), config));
                }
            } catch (Exception e) {
                throw new JSONException("toJSON error", e);
            }
            return json;
        }
        String text = JSON.toJSONString(javaObject, config, new SerializerFeature[0]);
        return JSON.parse(text);
    }

    public static <T> T toJavaObject(JSON json, Class<T> clazz) {
        return TypeUtils.cast((Object)json, clazz, ParserConfig.getGlobalInstance());
    }

    public <T> T toJavaObject(Class<T> clazz) {
        if (clazz == JSONArray.class || clazz == JSON.class || clazz == Collection.class || clazz == List.class) {
            return (T)this;
        }
        return TypeUtils.cast((Object)this, clazz, ParserConfig.getGlobalInstance());
    }

    public <T> T toJavaObject(Type type) {
        return TypeUtils.cast((Object)this, type, ParserConfig.getGlobalInstance());
    }

    public <T> T toJavaObject(TypeReference typeReference) {
        Type type = typeReference != null ? typeReference.getType() : null;
        return TypeUtils.cast((Object)this, type, ParserConfig.getGlobalInstance());
    }

    private static byte[] allocateBytes(int length) {
        byte[] chars = bytesLocal.get();
        if (chars == null) {
            if (length <= 65536) {
                chars = new byte[65536];
                bytesLocal.set(chars);
            } else {
                chars = new byte[length];
            }
        } else if (chars.length < length) {
            chars = new byte[length];
        }
        return chars;
    }

    private static char[] allocateChars(int length) {
        char[] chars = charsLocal.get();
        if (chars == null) {
            if (length <= 65536) {
                chars = new char[65536];
                charsLocal.set(chars);
            } else {
                chars = new char[length];
            }
        } else if (chars.length < length) {
            chars = new char[length];
        }
        return chars;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static boolean isValid(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        JSONScanner lexer = new JSONScanner(str);
        try {
            lexer.nextToken();
            int token = lexer.token();
            switch (token) {
                case 12: {
                    if (lexer.getCurrent() == '\u001a') {
                        boolean bl = false;
                        return bl;
                    }
                    lexer.skipObject(true);
                    break;
                }
                case 14: {
                    lexer.skipArray(true);
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
                default: {
                    boolean bl = false;
                    return bl;
                }
            }
            boolean bl = lexer.token() == 20;
            return bl;
        } catch (Exception ex) {
            boolean bl = false;
            return bl;
        } finally {
            lexer.close();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static boolean isValidObject(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        JSONScanner lexer = new JSONScanner(str);
        try {
            lexer.nextToken();
            int token = lexer.token();
            if (token == 12) {
                if (lexer.getCurrent() == '\u001a') {
                    boolean bl = false;
                    return bl;
                }
                lexer.skipObject(true);
                boolean bl = lexer.token() == 20;
                return bl;
            }
            boolean bl = false;
            return bl;
        } catch (Exception ex) {
            boolean bl = false;
            return bl;
        } finally {
            lexer.close();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static boolean isValidArray(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        JSONScanner lexer = new JSONScanner(str);
        try {
            lexer.nextToken();
            int token = lexer.token();
            if (token == 14) {
                lexer.skipArray(true);
                boolean bl = lexer.token() == 20;
                return bl;
            }
            boolean bl = false;
            return bl;
        } catch (Exception ex) {
            boolean bl = false;
            return bl;
        } finally {
            lexer.close();
        }
    }

    public static <T> void handleResovleTask(DefaultJSONParser parser, T value) {
        parser.handleResovleTask(value);
    }

    public static void addMixInAnnotations(Type target, Type mixinSource) {
        if (target != null && mixinSource != null) {
            mixInsMapper.put(target, mixinSource);
        }
    }

    public static void removeMixInAnnotations(Type target) {
        if (target != null) {
            mixInsMapper.remove(target);
        }
    }

    public static void clearMixInAnnotations() {
        mixInsMapper.clear();
    }

    public static Type getMixInAnnotations(Type target) {
        if (target != null) {
            return mixInsMapper.get(target);
        }
        return null;
    }

    static {
        mixInsMapper = new ConcurrentHashMap(16);
        int features = 0;
        features |= Feature.AutoCloseSource.getMask();
        features |= Feature.InternFieldNames.getMask();
        features |= Feature.UseBigDecimal.getMask();
        features |= Feature.AllowUnQuotedFieldNames.getMask();
        features |= Feature.AllowSingleQuotes.getMask();
        features |= Feature.AllowArbitraryCommas.getMask();
        features |= Feature.SortFeidFastMatch.getMask();
        DEFAULT_PARSER_FEATURE = features |= Feature.IgnoreNotMatch.getMask();
        features = 0;
        features |= SerializerFeature.QuoteFieldNames.getMask();
        features |= SerializerFeature.SkipTransientField.getMask();
        features |= SerializerFeature.WriteEnumUsingName.getMask();
        DEFAULT_GENERATE_FEATURE = features |= SerializerFeature.SortField.getMask();
        JSON.config(IOUtils.DEFAULT_PROPERTIES);
        bytesLocal = new ThreadLocal();
        charsLocal = new ThreadLocal();
    }
}

