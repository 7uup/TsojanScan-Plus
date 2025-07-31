/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  javax.servlet.http.HttpServletRequest
 *  org.springframework.http.MediaType
 *  org.springframework.http.converter.HttpMessageConverter
 *  org.springframework.http.server.ServerHttpRequest
 *  org.springframework.http.server.ServerHttpResponse
 *  org.springframework.http.server.ServletServerHttpRequest
 *  org.springframework.web.bind.annotation.ControllerAdvice
 *  org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice
 */
package com.alibaba.fastjson.support.spring;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.alibaba.fastjson.support.spring.MappingFastJsonValue;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@Deprecated
@Order(value=-2147483648)
@ControllerAdvice
public class FastJsonpResponseBodyAdvice
implements ResponseBodyAdvice<Object> {
    private static final Pattern CALLBACK_PARAM_PATTERN = Pattern.compile("[0-9A-Za-z_\\.]*");
    private final String[] jsonpQueryParamNames;
    public static final String[] DEFAULT_JSONP_QUERY_PARAM_NAMES = new String[]{"callback", "jsonp"};

    public FastJsonpResponseBodyAdvice() {
        this.jsonpQueryParamNames = DEFAULT_JSONP_QUERY_PARAM_NAMES;
    }

    public FastJsonpResponseBodyAdvice(String ... queryParamNames) {
        Assert.isTrue(!ObjectUtils.isEmpty(queryParamNames), "At least one query param name is required");
        this.jsonpQueryParamNames = queryParamNames;
    }

    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return FastJsonHttpMessageConverter.class.isAssignableFrom(converterType);
    }

    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        MappingFastJsonValue container = this.getOrCreateContainer(body);
        this.beforeBodyWriteInternal(container, selectedContentType, returnType, request, response);
        return container;
    }

    protected MappingFastJsonValue getOrCreateContainer(Object body) {
        return body instanceof MappingFastJsonValue ? (MappingFastJsonValue)body : new MappingFastJsonValue(body);
    }

    public void beforeBodyWriteInternal(MappingFastJsonValue bodyContainer, MediaType contentType, MethodParameter returnType, ServerHttpRequest request, ServerHttpResponse response) {
        HttpServletRequest servletRequest = ((ServletServerHttpRequest)request).getServletRequest();
        for (String name : this.jsonpQueryParamNames) {
            String value = servletRequest.getParameter(name);
            if (value == null || !this.isValidJsonpQueryParam(value)) continue;
            bodyContainer.setJsonpFunction(value);
            break;
        }
    }

    protected boolean isValidJsonpQueryParam(String value) {
        return CALLBACK_PARAM_PATTERN.matcher(value).matches();
    }

    protected MediaType getContentType(MediaType contentType, ServerHttpRequest request, ServerHttpResponse response) {
        return new MediaType("application", "javascript");
    }
}

