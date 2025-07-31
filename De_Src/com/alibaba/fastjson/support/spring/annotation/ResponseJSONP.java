/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.springframework.web.bind.annotation.ResponseBody
 */
package com.alibaba.fastjson.support.spring.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.web.bind.annotation.ResponseBody;

@Documented
@Target(value={ElementType.TYPE, ElementType.METHOD})
@Retention(value=RetentionPolicy.RUNTIME)
@ResponseBody
public @interface ResponseJSONP {
    public String callback() default "callback";
}

