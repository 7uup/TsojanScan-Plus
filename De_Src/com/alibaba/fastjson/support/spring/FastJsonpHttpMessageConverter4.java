/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.springframework.http.HttpInputMessage
 *  org.springframework.http.HttpOutputMessage
 *  org.springframework.http.MediaType
 *  org.springframework.http.converter.HttpMessageNotReadableException
 *  org.springframework.http.converter.HttpMessageNotWritableException
 */
package com.alibaba.fastjson.support.spring;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import java.io.IOException;
import java.lang.reflect.Type;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@Deprecated
public class FastJsonpHttpMessageConverter4
extends FastJsonHttpMessageConverter {
    @Override
    protected boolean supports(Class<?> clazz) {
        return super.supports(clazz);
    }

    @Override
    public boolean canRead(Type type, Class<?> contextClass, MediaType mediaType) {
        return super.canRead(type, contextClass, mediaType);
    }

    @Override
    public boolean canWrite(Type type, Class<?> clazz, MediaType mediaType) {
        return super.canWrite(type, clazz, mediaType);
    }

    @Override
    public Object read(Type type, Class<?> contextClass, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return super.read(type, contextClass, inputMessage);
    }

    @Override
    public void write(Object o, Type type, MediaType contentType, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        super.write(o, type, contentType, outputMessage);
    }

    @Override
    protected Object readInternal(Class<?> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return super.readInternal(clazz, inputMessage);
    }

    @Override
    protected void writeInternal(Object object, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        super.writeInternal(object, outputMessage);
    }
}

