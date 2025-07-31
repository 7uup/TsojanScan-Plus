/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.springframework.http.MediaType
 *  org.springframework.http.converter.HttpMessageConverter
 *  org.springframework.http.server.ServerHttpRequest
 *  org.springframework.http.server.ServerHttpResponse
 *  org.springframework.web.bind.annotation.ControllerAdvice
 *  org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice
 */
package com.alibaba.fastjson.support.spring;

import com.alibaba.fastjson.support.spring.FastJsonContainer;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.alibaba.fastjson.support.spring.PropertyPreFilters;
import com.alibaba.fastjson.support.spring.annotation.FastJsonFilter;
import com.alibaba.fastjson.support.spring.annotation.FastJsonView;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@Order
@ControllerAdvice
public class FastJsonViewResponseBodyAdvice
implements ResponseBodyAdvice<Object> {
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return FastJsonHttpMessageConverter.class.isAssignableFrom(converterType) && returnType.hasMethodAnnotation(FastJsonView.class);
    }

    public FastJsonContainer beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        FastJsonContainer container = this.getOrCreateContainer(body);
        this.beforeBodyWriteInternal(container, selectedContentType, returnType, request, response);
        return container;
    }

    private FastJsonContainer getOrCreateContainer(Object body) {
        return body instanceof FastJsonContainer ? (FastJsonContainer)body : new FastJsonContainer(body);
    }

    protected void beforeBodyWriteInternal(FastJsonContainer container, MediaType contentType, MethodParameter returnType, ServerHttpRequest request, ServerHttpResponse response) {
        FastJsonView annotation = returnType.getMethodAnnotation(FastJsonView.class);
        FastJsonFilter[] include = annotation.include();
        FastJsonFilter[] exclude = annotation.exclude();
        PropertyPreFilters filters = new PropertyPreFilters();
        for (FastJsonFilter item : include) {
            filters.addFilter(item.clazz(), item.props());
        }
        for (FastJsonFilter item : exclude) {
            filters.addFilter(item.clazz(), new String[0]).addExcludes(item.props());
        }
        container.setFilters(filters);
    }
}

