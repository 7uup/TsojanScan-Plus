/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.springframework.http.HttpHeaders
 *  org.springframework.http.HttpInputMessage
 *  org.springframework.http.HttpOutputMessage
 *  org.springframework.http.MediaType
 *  org.springframework.http.converter.AbstractHttpMessageConverter
 *  org.springframework.http.converter.GenericHttpMessageConverter
 *  org.springframework.http.converter.HttpMessageNotReadableException
 *  org.springframework.http.converter.HttpMessageNotWritableException
 */
package com.alibaba.fastjson.support.spring;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONPObject;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonContainer;
import com.alibaba.fastjson.support.spring.MappingFastJsonValue;
import com.alibaba.fastjson.support.spring.PropertyPreFilters;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import org.springframework.core.ResolvableType;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.GenericHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.StringUtils;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class FastJsonHttpMessageConverter
extends AbstractHttpMessageConverter<Object>
implements GenericHttpMessageConverter<Object> {
    public static final MediaType APPLICATION_JAVASCRIPT = new MediaType("application", "javascript");
    @Deprecated
    protected SerializerFeature[] features = new SerializerFeature[0];
    @Deprecated
    protected SerializeFilter[] filters = new SerializeFilter[0];
    @Deprecated
    protected String dateFormat;
    private boolean setLengthError = false;
    private FastJsonConfig fastJsonConfig = new FastJsonConfig();

    public FastJsonConfig getFastJsonConfig() {
        return this.fastJsonConfig;
    }

    public void setFastJsonConfig(FastJsonConfig fastJsonConfig) {
        this.fastJsonConfig = fastJsonConfig;
    }

    public FastJsonHttpMessageConverter() {
        super(MediaType.ALL);
    }

    @Deprecated
    public Charset getCharset() {
        return this.fastJsonConfig.getCharset();
    }

    @Deprecated
    public void setCharset(Charset charset) {
        this.fastJsonConfig.setCharset(charset);
    }

    @Deprecated
    public String getDateFormat() {
        return this.fastJsonConfig.getDateFormat();
    }

    @Deprecated
    public void setDateFormat(String dateFormat) {
        this.fastJsonConfig.setDateFormat(dateFormat);
    }

    @Deprecated
    public SerializerFeature[] getFeatures() {
        return this.fastJsonConfig.getSerializerFeatures();
    }

    @Deprecated
    public void setFeatures(SerializerFeature ... features) {
        this.fastJsonConfig.setSerializerFeatures(features);
    }

    @Deprecated
    public SerializeFilter[] getFilters() {
        return this.fastJsonConfig.getSerializeFilters();
    }

    @Deprecated
    public void setFilters(SerializeFilter ... filters) {
        this.fastJsonConfig.setSerializeFilters(filters);
    }

    @Deprecated
    public void addSerializeFilter(SerializeFilter filter) {
        if (filter == null) {
            return;
        }
        int length = this.fastJsonConfig.getSerializeFilters().length;
        SerializeFilter[] filters = new SerializeFilter[length + 1];
        System.arraycopy(this.fastJsonConfig.getSerializeFilters(), 0, filters, 0, length);
        filters[filters.length - 1] = filter;
        this.fastJsonConfig.setSerializeFilters(filters);
    }

    protected boolean supports(Class<?> clazz) {
        return true;
    }

    public boolean canRead(Type type, Class<?> contextClass, MediaType mediaType) {
        return super.canRead(contextClass, mediaType);
    }

    public boolean canWrite(Type type, Class<?> clazz, MediaType mediaType) {
        return super.canWrite(clazz, mediaType);
    }

    public Object read(Type type, Class<?> contextClass, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return this.readType(this.getType(type, contextClass), inputMessage);
    }

    public void write(Object o, Type type, MediaType contentType, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        super.write(o, contentType, outputMessage);
    }

    protected Object readInternal(Class<?> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return this.readType(this.getType(clazz, null), inputMessage);
    }

    private Object readType(Type type, HttpInputMessage inputMessage) {
        try {
            InputStream in = inputMessage.getBody();
            return JSON.parseObject(in, this.fastJsonConfig.getCharset(), type, this.fastJsonConfig.getParserConfig(), this.fastJsonConfig.getParseProcess(), JSON.DEFAULT_PARSER_FEATURE, this.fastJsonConfig.getFeatures());
        } catch (JSONException ex) {
            throw new HttpMessageNotReadableException("JSON parse error: " + ex.getMessage(), (Throwable)ex);
        } catch (IOException ex) {
            throw new HttpMessageNotReadableException("I/O error while reading input message", (Throwable)ex);
        }
    }

    protected void writeInternal(Object object, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        ByteArrayOutputStream outnew = new ByteArrayOutputStream();
        try {
            HttpHeaders headers = outputMessage.getHeaders();
            SerializeFilter[] globalFilters = this.fastJsonConfig.getSerializeFilters();
            ArrayList<SerializeFilter> allFilters = new ArrayList<SerializeFilter>(Arrays.asList(globalFilters));
            boolean isJsonp = false;
            Object value = this.strangeCodeForJackson(object);
            if (value instanceof FastJsonContainer) {
                FastJsonContainer fastJsonContainer = (FastJsonContainer)value;
                PropertyPreFilters filters = fastJsonContainer.getFilters();
                allFilters.addAll(filters.getFilters());
                value = fastJsonContainer.getValue();
            }
            if (value instanceof MappingFastJsonValue) {
                if (!StringUtils.isEmpty(((MappingFastJsonValue)value).getJsonpFunction())) {
                    isJsonp = true;
                }
            } else if (value instanceof JSONPObject) {
                isJsonp = true;
            }
            int len = JSON.writeJSONStringWithFastJsonConfig(outnew, this.fastJsonConfig.getCharset(), value, this.fastJsonConfig.getSerializeConfig(), allFilters.toArray(new SerializeFilter[allFilters.size()]), this.fastJsonConfig.getDateFormat(), JSON.DEFAULT_GENERATE_FEATURE, this.fastJsonConfig.getSerializerFeatures());
            if (isJsonp) {
                headers.setContentType(APPLICATION_JAVASCRIPT);
            }
            if (this.fastJsonConfig.isWriteContentLength() && !this.setLengthError) {
                try {
                    headers.setContentLength((long)len);
                } catch (UnsupportedOperationException ex) {
                    this.setLengthError = true;
                }
            }
            outnew.writeTo(outputMessage.getBody());
        } catch (JSONException ex) {
            throw new HttpMessageNotWritableException("Could not write JSON: " + ex.getMessage(), (Throwable)ex);
        } finally {
            outnew.close();
        }
    }

    private Object strangeCodeForJackson(Object obj) {
        String className;
        if (obj != null && "com.fasterxml.jackson.databind.node.ObjectNode".equals(className = obj.getClass().getName())) {
            return obj.toString();
        }
        return obj;
    }

    protected Type getType(Type type, Class<?> contextClass) {
        if (Spring4TypeResolvableHelper.isSupport()) {
            return Spring4TypeResolvableHelper.getType(type, contextClass);
        }
        return type;
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    private static class Spring4TypeResolvableHelper {
        private static boolean hasClazzResolvableType;

        private Spring4TypeResolvableHelper() {
        }

        private static boolean isSupport() {
            return hasClazzResolvableType;
        }

        private static Type getType(Type type, Class<?> contextClass) {
            if (contextClass != null) {
                ResolvableType resolvedType = ResolvableType.forType(type);
                if (type instanceof TypeVariable) {
                    ResolvableType resolvedTypeVariable = Spring4TypeResolvableHelper.resolveVariable((TypeVariable)type, ResolvableType.forClass(contextClass));
                    if (resolvedTypeVariable != ResolvableType.NONE) {
                        return resolvedTypeVariable.resolve();
                    }
                } else if (type instanceof ParameterizedType && resolvedType.hasUnresolvableGenerics()) {
                    ParameterizedType parameterizedType = (ParameterizedType)type;
                    Class[] generics = new Class[parameterizedType.getActualTypeArguments().length];
                    Type[] typeArguments = parameterizedType.getActualTypeArguments();
                    for (int i = 0; i < typeArguments.length; ++i) {
                        Type typeArgument = typeArguments[i];
                        if (typeArgument instanceof TypeVariable) {
                            ResolvableType resolvedTypeArgument = Spring4TypeResolvableHelper.resolveVariable((TypeVariable)typeArgument, ResolvableType.forClass(contextClass));
                            if (resolvedTypeArgument != ResolvableType.NONE) {
                                generics[i] = resolvedTypeArgument.resolve();
                                continue;
                            }
                            generics[i] = ResolvableType.forType(typeArgument).resolve();
                            continue;
                        }
                        generics[i] = ResolvableType.forType(typeArgument).resolve();
                    }
                    return ResolvableType.forClassWithGenerics(resolvedType.getRawClass(), generics).getType();
                }
            }
            return type;
        }

        private static ResolvableType resolveVariable(TypeVariable<?> typeVariable, ResolvableType contextType) {
            ResolvableType resolvedType;
            if (contextType.hasGenerics() && (resolvedType = ResolvableType.forType(typeVariable, contextType)).resolve() != null) {
                return resolvedType;
            }
            ResolvableType superType = contextType.getSuperType();
            if (superType != ResolvableType.NONE && (resolvedType = Spring4TypeResolvableHelper.resolveVariable(typeVariable, superType)).resolve() != null) {
                return resolvedType;
            }
            for (ResolvableType ifc : contextType.getInterfaces()) {
                resolvedType = Spring4TypeResolvableHelper.resolveVariable(typeVariable, ifc);
                if (resolvedType.resolve() == null) continue;
                return resolvedType;
            }
            return ResolvableType.NONE;
        }

        static {
            try {
                Class.forName("org.springframework.core.ResolvableType");
                hasClazzResolvableType = true;
            } catch (ClassNotFoundException e) {
                hasClazzResolvableType = false;
            }
        }
    }
}

