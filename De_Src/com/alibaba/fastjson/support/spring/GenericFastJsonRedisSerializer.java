/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.springframework.data.redis.serializer.RedisSerializer
 *  org.springframework.data.redis.serializer.SerializationException
 */
package com.alibaba.fastjson.support.spring;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.util.IOUtils;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class GenericFastJsonRedisSerializer
implements RedisSerializer<Object> {
    private static final ParserConfig defaultRedisConfig = new ParserConfig();

    public byte[] serialize(Object object) throws SerializationException {
        if (object == null) {
            return new byte[0];
        }
        try {
            return JSON.toJSONBytes(object, SerializerFeature.WriteClassName);
        } catch (Exception ex) {
            throw new SerializationException("Could not serialize: " + ex.getMessage(), (Throwable)ex);
        }
    }

    public Object deserialize(byte[] bytes) throws SerializationException {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        try {
            return JSON.parseObject(new String(bytes, IOUtils.UTF8), Object.class, defaultRedisConfig, new Feature[0]);
        } catch (Exception ex) {
            throw new SerializationException("Could not deserialize: " + ex.getMessage(), (Throwable)ex);
        }
    }

    static {
        defaultRedisConfig.setAutoTypeSupport(true);
    }
}

