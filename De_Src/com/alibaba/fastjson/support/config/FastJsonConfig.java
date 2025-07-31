/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.support.config;

import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.parser.deserializer.ParseProcess;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.util.IOUtils;
import java.nio.charset.Charset;
import java.util.Map;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class FastJsonConfig {
    private Charset charset = IOUtils.UTF8;
    private SerializeConfig serializeConfig = SerializeConfig.getGlobalInstance();
    private ParserConfig parserConfig = ParserConfig.getGlobalInstance();
    private ParseProcess parseProcess;
    private SerializerFeature[] serializerFeatures = new SerializerFeature[]{SerializerFeature.BrowserSecure};
    private SerializeFilter[] serializeFilters = new SerializeFilter[0];
    private Feature[] features = new Feature[0];
    private Map<Class<?>, SerializeFilter> classSerializeFilters;
    private String dateFormat;
    private boolean writeContentLength = true;

    public SerializeConfig getSerializeConfig() {
        return this.serializeConfig;
    }

    public void setSerializeConfig(SerializeConfig serializeConfig) {
        this.serializeConfig = serializeConfig;
    }

    public ParserConfig getParserConfig() {
        return this.parserConfig;
    }

    public void setParserConfig(ParserConfig parserConfig) {
        this.parserConfig = parserConfig;
    }

    public SerializerFeature[] getSerializerFeatures() {
        return this.serializerFeatures;
    }

    public void setSerializerFeatures(SerializerFeature ... serializerFeatures) {
        this.serializerFeatures = serializerFeatures;
    }

    public SerializeFilter[] getSerializeFilters() {
        return this.serializeFilters;
    }

    public void setSerializeFilters(SerializeFilter ... serializeFilters) {
        this.serializeFilters = serializeFilters;
    }

    public Feature[] getFeatures() {
        return this.features;
    }

    public void setFeatures(Feature ... features) {
        this.features = features;
    }

    public Map<Class<?>, SerializeFilter> getClassSerializeFilters() {
        return this.classSerializeFilters;
    }

    public void setClassSerializeFilters(Map<Class<?>, SerializeFilter> classSerializeFilters) {
        if (classSerializeFilters == null) {
            return;
        }
        for (Map.Entry<Class<?>, SerializeFilter> entry : classSerializeFilters.entrySet()) {
            this.serializeConfig.addFilter(entry.getKey(), entry.getValue());
        }
        this.classSerializeFilters = classSerializeFilters;
    }

    public String getDateFormat() {
        return this.dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public Charset getCharset() {
        return this.charset;
    }

    public void setCharset(Charset charset) {
        this.charset = charset;
    }

    public boolean isWriteContentLength() {
        return this.writeContentLength;
    }

    public void setWriteContentLength(boolean writeContentLength) {
        this.writeContentLength = writeContentLength;
    }

    public ParseProcess getParseProcess() {
        return this.parseProcess;
    }

    public void setParseProcess(ParseProcess parseProcess) {
        this.parseProcess = parseProcess;
    }
}

