/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.support.spring.annotation;

import com.alibaba.fastjson.support.spring.annotation.FastJsonFilter;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value={ElementType.METHOD})
@Retention(value=RetentionPolicy.RUNTIME)
@Documented
public @interface FastJsonView {
    public FastJsonFilter[] include() default {};

    public FastJsonFilter[] exclude() default {};
}

