/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.annotation;

import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@Retention(value=RetentionPolicy.RUNTIME)
@Target(value={ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
public @interface JSONField {
    public int ordinal() default 0;

    public String name() default "";

    public String format() default "";

    public boolean serialize() default true;

    public boolean deserialize() default true;

    public SerializerFeature[] serialzeFeatures() default {};

    public Feature[] parseFeatures() default {};

    public String label() default "";

    public boolean jsonDirect() default false;

    public Class<?> serializeUsing() default Void.class;

    public Class<?> deserializeUsing() default Void.class;

    public String[] alternateNames() default {};

    public boolean unwrapped() default false;

    public String defaultValue() default "";
}

