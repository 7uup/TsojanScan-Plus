/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  retrofit2.Converter
 *  retrofit2.Converter$Factory
 *  retrofit2.Retrofit
 */
package com.alibaba.fastjson.support.retrofit;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class Retrofit2ConverterFactory
extends Converter.Factory {
    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");
    private FastJsonConfig fastJsonConfig;
    @Deprecated
    private static final Feature[] EMPTY_SERIALIZER_FEATURES = new Feature[0];
    @Deprecated
    private ParserConfig parserConfig = ParserConfig.getGlobalInstance();
    @Deprecated
    private int featureValues = JSON.DEFAULT_PARSER_FEATURE;
    @Deprecated
    private Feature[] features;
    @Deprecated
    private SerializeConfig serializeConfig;
    @Deprecated
    private SerializerFeature[] serializerFeatures;

    public Retrofit2ConverterFactory() {
        this.fastJsonConfig = new FastJsonConfig();
    }

    public Retrofit2ConverterFactory(FastJsonConfig fastJsonConfig) {
        this.fastJsonConfig = fastJsonConfig;
    }

    public static Retrofit2ConverterFactory create() {
        return Retrofit2ConverterFactory.create(new FastJsonConfig());
    }

    public static Retrofit2ConverterFactory create(FastJsonConfig fastJsonConfig) {
        if (fastJsonConfig == null) {
            throw new NullPointerException("fastJsonConfig == null");
        }
        return new Retrofit2ConverterFactory(fastJsonConfig);
    }

    public Converter<ResponseBody, Object> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        return new ResponseBodyConverter<Object>(type);
    }

    public Converter<Object, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        return new RequestBodyConverter<Object>();
    }

    public FastJsonConfig getFastJsonConfig() {
        return this.fastJsonConfig;
    }

    public Retrofit2ConverterFactory setFastJsonConfig(FastJsonConfig fastJsonConfig) {
        this.fastJsonConfig = fastJsonConfig;
        return this;
    }

    @Deprecated
    public ParserConfig getParserConfig() {
        return this.fastJsonConfig.getParserConfig();
    }

    @Deprecated
    public Retrofit2ConverterFactory setParserConfig(ParserConfig config) {
        this.fastJsonConfig.setParserConfig(config);
        return this;
    }

    @Deprecated
    public int getParserFeatureValues() {
        return JSON.DEFAULT_PARSER_FEATURE;
    }

    @Deprecated
    public Retrofit2ConverterFactory setParserFeatureValues(int featureValues) {
        return this;
    }

    @Deprecated
    public Feature[] getParserFeatures() {
        return this.fastJsonConfig.getFeatures();
    }

    @Deprecated
    public Retrofit2ConverterFactory setParserFeatures(Feature[] features) {
        this.fastJsonConfig.setFeatures(features);
        return this;
    }

    @Deprecated
    public SerializeConfig getSerializeConfig() {
        return this.fastJsonConfig.getSerializeConfig();
    }

    @Deprecated
    public Retrofit2ConverterFactory setSerializeConfig(SerializeConfig serializeConfig) {
        this.fastJsonConfig.setSerializeConfig(serializeConfig);
        return this;
    }

    @Deprecated
    public SerializerFeature[] getSerializerFeatures() {
        return this.fastJsonConfig.getSerializerFeatures();
    }

    @Deprecated
    public Retrofit2ConverterFactory setSerializerFeatures(SerializerFeature[] features) {
        this.fastJsonConfig.setSerializerFeatures(features);
        return this;
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    final class RequestBodyConverter<T>
    implements Converter<T, RequestBody> {
        RequestBodyConverter() {
        }

        public RequestBody convert(T value) throws IOException {
            try {
                byte[] content = JSON.toJSONBytesWithFastJsonConfig(Retrofit2ConverterFactory.this.fastJsonConfig.getCharset(), value, Retrofit2ConverterFactory.this.fastJsonConfig.getSerializeConfig(), Retrofit2ConverterFactory.this.fastJsonConfig.getSerializeFilters(), Retrofit2ConverterFactory.this.fastJsonConfig.getDateFormat(), JSON.DEFAULT_GENERATE_FEATURE, Retrofit2ConverterFactory.this.fastJsonConfig.getSerializerFeatures());
                return RequestBody.create(MEDIA_TYPE, content);
            } catch (Exception e) {
                throw new IOException("Could not write JSON: " + e.getMessage(), e);
            }
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    final class ResponseBodyConverter<T>
    implements Converter<ResponseBody, T> {
        private Type type;

        ResponseBodyConverter(Type type) {
            this.type = type;
        }

        public T convert(ResponseBody value) throws IOException {
            try {
                Object t = JSON.parseObject(value.bytes(), Retrofit2ConverterFactory.this.fastJsonConfig.getCharset(), this.type, Retrofit2ConverterFactory.this.fastJsonConfig.getParserConfig(), Retrofit2ConverterFactory.this.fastJsonConfig.getParseProcess(), JSON.DEFAULT_PARSER_FEATURE, Retrofit2ConverterFactory.this.fastJsonConfig.getFeatures());
                return t;
            } catch (Exception e) {
                throw new IOException("JSON parse error: " + e.getMessage(), e);
            } finally {
                value.close();
            }
        }
    }
}

