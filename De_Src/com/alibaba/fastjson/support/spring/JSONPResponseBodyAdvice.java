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

import com.alibaba.fastjson.JSONPObject;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.alibaba.fastjson.support.spring.annotation.ResponseJSONP;
import com.alibaba.fastjson.util.IOUtils;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@Order(value=-2147483648)
@ControllerAdvice
public class JSONPResponseBodyAdvice
implements ResponseBodyAdvice<Object> {
    public final Log logger = LogFactory.getLog(this.getClass());

    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return FastJsonHttpMessageConverter.class.isAssignableFrom(converterType) && (returnType.getContainingClass().isAnnotationPresent(ResponseJSONP.class) || returnType.hasMethodAnnotation(ResponseJSONP.class));
    }

    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        HttpServletRequest servletRequest;
        String callbackMethodName;
        ResponseJSONP responseJsonp = returnType.getMethodAnnotation(ResponseJSONP.class);
        if (responseJsonp == null) {
            responseJsonp = returnType.getContainingClass().getAnnotation(ResponseJSONP.class);
        }
        if (!IOUtils.isValidJsonpQueryParam(callbackMethodName = (servletRequest = ((ServletServerHttpRequest)request).getServletRequest()).getParameter(responseJsonp.callback()))) {
            if (this.logger.isDebugEnabled()) {
                this.logger.debug("Invalid jsonp parameter value:" + callbackMethodName);
            }
            callbackMethodName = null;
        }
        JSONPObject jsonpObject = new JSONPObject(callbackMethodName);
        jsonpObject.addParameter(body);
        this.beforeBodyWriteInternal(jsonpObject, selectedContentType, returnType, request, response);
        return jsonpObject;
    }

    public void beforeBodyWriteInternal(JSONPObject jsonpObject, MediaType contentType, MethodParameter returnType, ServerHttpRequest request, ServerHttpResponse response) {
    }

    protected MediaType getContentType(MediaType contentType, ServerHttpRequest request, ServerHttpResponse response) {
        return FastJsonHttpMessageConverter.APPLICATION_JAVASCRIPT;
    }
}

