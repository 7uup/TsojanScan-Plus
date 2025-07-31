/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.springframework.messaging.Message
 *  org.springframework.messaging.MessageHeaders
 *  org.springframework.messaging.converter.AbstractMessageConverter
 */
package com.alibaba.fastjson.support.spring.messaging;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import java.nio.charset.Charset;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.converter.AbstractMessageConverter;
import org.springframework.util.MimeType;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class MappingFastJsonMessageConverter
extends AbstractMessageConverter {
    private FastJsonConfig fastJsonConfig = new FastJsonConfig();

    public FastJsonConfig getFastJsonConfig() {
        return this.fastJsonConfig;
    }

    public void setFastJsonConfig(FastJsonConfig fastJsonConfig) {
        this.fastJsonConfig = fastJsonConfig;
    }

    public MappingFastJsonMessageConverter() {
        super(new MimeType("application", "json", Charset.forName("UTF-8")));
    }

    protected boolean supports(Class<?> clazz) {
        return true;
    }

    protected boolean canConvertFrom(Message<?> message, Class<?> targetClass) {
        return this.supports(targetClass);
    }

    protected boolean canConvertTo(Object payload, MessageHeaders headers) {
        return this.supports(payload.getClass());
    }

    protected Object convertFromInternal(Message<?> message, Class<?> targetClass, Object conversionHint) {
        Object payload = message.getPayload();
        Object obj = null;
        if (payload instanceof byte[]) {
            obj = JSON.parseObject((byte[])payload, this.fastJsonConfig.getCharset(), targetClass, this.fastJsonConfig.getParserConfig(), this.fastJsonConfig.getParseProcess(), JSON.DEFAULT_PARSER_FEATURE, this.fastJsonConfig.getFeatures());
        } else if (payload instanceof String) {
            obj = JSON.parseObject((String)payload, targetClass, this.fastJsonConfig.getParserConfig(), this.fastJsonConfig.getParseProcess(), JSON.DEFAULT_PARSER_FEATURE, this.fastJsonConfig.getFeatures());
        }
        return obj;
    }

    protected Object convertToInternal(Object payload, MessageHeaders headers, Object conversionHint) {
        Object obj = byte[].class == this.getSerializedPayloadClass() ? (payload instanceof String && JSON.isValid((String)payload) ? (Object)((String)payload).getBytes(this.fastJsonConfig.getCharset()) : (Object)JSON.toJSONBytesWithFastJsonConfig(this.fastJsonConfig.getCharset(), payload, this.fastJsonConfig.getSerializeConfig(), this.fastJsonConfig.getSerializeFilters(), this.fastJsonConfig.getDateFormat(), JSON.DEFAULT_GENERATE_FEATURE, this.fastJsonConfig.getSerializerFeatures())) : (payload instanceof String && JSON.isValid((String)payload) ? payload : JSON.toJSONString(payload, this.fastJsonConfig.getSerializeConfig(), this.fastJsonConfig.getSerializeFilters(), this.fastJsonConfig.getDateFormat(), JSON.DEFAULT_GENERATE_FEATURE, this.fastJsonConfig.getSerializerFeatures()));
        return obj;
    }
}

