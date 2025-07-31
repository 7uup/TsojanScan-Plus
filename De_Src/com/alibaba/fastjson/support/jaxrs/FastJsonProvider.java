/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  javax.ws.rs.Consumes
 *  javax.ws.rs.Produces
 *  javax.ws.rs.WebApplicationException
 *  javax.ws.rs.core.Context
 *  javax.ws.rs.core.MediaType
 *  javax.ws.rs.core.MultivaluedMap
 *  javax.ws.rs.core.Response
 *  javax.ws.rs.core.StreamingOutput
 *  javax.ws.rs.ext.ContextResolver
 *  javax.ws.rs.ext.MessageBodyReader
 *  javax.ws.rs.ext.MessageBodyWriter
 *  javax.ws.rs.ext.Provider
 *  javax.ws.rs.ext.Providers
 */
package com.alibaba.fastjson.support.jaxrs;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.Providers;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@Provider
@Consumes(value={"*/*"})
@Produces(value={"*/*"})
public class FastJsonProvider
implements MessageBodyReader<Object>,
MessageBodyWriter<Object> {
    public static final Class<?>[] DEFAULT_UNREADABLES = new Class[]{InputStream.class, Reader.class};
    public static final Class<?>[] DEFAULT_UNWRITABLES = new Class[]{InputStream.class, OutputStream.class, Writer.class, StreamingOutput.class, Response.class};
    @Deprecated
    protected Charset charset = Charset.forName("UTF-8");
    @Deprecated
    protected SerializerFeature[] features = new SerializerFeature[0];
    @Deprecated
    protected SerializeFilter[] filters = new SerializeFilter[0];
    @Deprecated
    protected String dateFormat;
    @Context
    protected Providers providers;
    private FastJsonConfig fastJsonConfig = new FastJsonConfig();
    private Class<?>[] clazzes = null;
    private boolean pretty;

    public FastJsonConfig getFastJsonConfig() {
        return this.fastJsonConfig;
    }

    public void setFastJsonConfig(FastJsonConfig fastJsonConfig) {
        this.fastJsonConfig = fastJsonConfig;
    }

    public FastJsonProvider() {
    }

    public FastJsonProvider(Class<?>[] clazzes) {
        this.clazzes = clazzes;
    }

    public FastJsonProvider setPretty(boolean p) {
        this.pretty = p;
        return this;
    }

    @Deprecated
    public FastJsonProvider(String charset) {
        this.fastJsonConfig.setCharset(Charset.forName(charset));
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

    protected boolean isAssignableFrom(Class<?> type, Class<?>[] classes) {
        if (type == null) {
            return false;
        }
        for (Class<?> cls : classes) {
            if (!cls.isAssignableFrom(type)) continue;
            return false;
        }
        return true;
    }

    protected boolean isValidType(Class<?> type, Annotation[] classAnnotations) {
        if (type == null) {
            return false;
        }
        if (this.clazzes != null) {
            for (Class<?> cls : this.clazzes) {
                if (cls != type) continue;
                return true;
            }
            return false;
        }
        return true;
    }

    protected boolean hasMatchingMediaType(MediaType mediaType) {
        if (mediaType != null) {
            String subtype = mediaType.getSubtype();
            return "json".equalsIgnoreCase(subtype) || subtype.endsWith("+json") || "javascript".equals(subtype) || "x-javascript".equals(subtype) || "x-json".equals(subtype) || "x-www-form-urlencoded".equalsIgnoreCase(subtype) || subtype.endsWith("x-www-form-urlencoded");
        }
        return true;
    }

    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        if (!this.hasMatchingMediaType(mediaType)) {
            return false;
        }
        if (!this.isAssignableFrom(type, DEFAULT_UNWRITABLES)) {
            return false;
        }
        return this.isValidType(type, annotations);
    }

    public long getSize(Object t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return -1L;
    }

    public void writeTo(Object obj, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream) throws IOException, WebApplicationException {
        FastJsonConfig fastJsonConfig = this.locateConfigProvider(type, mediaType);
        SerializerFeature[] serializerFeatures = fastJsonConfig.getSerializerFeatures();
        if (this.pretty) {
            if (serializerFeatures == null) {
                serializerFeatures = new SerializerFeature[]{SerializerFeature.PrettyFormat};
            } else {
                ArrayList<SerializerFeature> featureList = new ArrayList<SerializerFeature>(Arrays.asList(serializerFeatures));
                featureList.add(SerializerFeature.PrettyFormat);
                serializerFeatures = featureList.toArray(serializerFeatures);
            }
            fastJsonConfig.setSerializerFeatures(serializerFeatures);
        }
        try {
            JSON.writeJSONStringWithFastJsonConfig(entityStream, fastJsonConfig.getCharset(), obj, fastJsonConfig.getSerializeConfig(), fastJsonConfig.getSerializeFilters(), fastJsonConfig.getDateFormat(), JSON.DEFAULT_GENERATE_FEATURE, fastJsonConfig.getSerializerFeatures());
            entityStream.flush();
        } catch (JSONException ex) {
            throw new WebApplicationException((Throwable)ex);
        }
    }

    public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        if (!this.hasMatchingMediaType(mediaType)) {
            return false;
        }
        if (!this.isAssignableFrom(type, DEFAULT_UNREADABLES)) {
            return false;
        }
        return this.isValidType(type, annotations);
    }

    public Object readFrom(Class<Object> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> httpHeaders, InputStream entityStream) throws IOException, WebApplicationException {
        try {
            FastJsonConfig fastJsonConfig = this.locateConfigProvider(type, mediaType);
            return JSON.parseObject(entityStream, fastJsonConfig.getCharset(), genericType, fastJsonConfig.getParserConfig(), fastJsonConfig.getParseProcess(), JSON.DEFAULT_PARSER_FEATURE, fastJsonConfig.getFeatures());
        } catch (JSONException ex) {
            throw new WebApplicationException((Throwable)ex);
        }
    }

    protected FastJsonConfig locateConfigProvider(Class<?> type, MediaType mediaType) {
        if (this.providers != null) {
            ContextResolver resolver = this.providers.getContextResolver(FastJsonConfig.class, mediaType);
            if (resolver == null) {
                resolver = this.providers.getContextResolver(FastJsonConfig.class, null);
            }
            if (resolver != null) {
                return (FastJsonConfig)resolver.getContext(type);
            }
        }
        return this.fastJsonConfig;
    }
}

