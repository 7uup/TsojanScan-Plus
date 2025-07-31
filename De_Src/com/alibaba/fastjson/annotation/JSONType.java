/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.annotation;

import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@Retention(value=RetentionPolicy.RUNTIME)
@Target(value={ElementType.TYPE})
public @interface JSONType {
    public boolean asm() default true;

    public String[] orders() default {};

    public String[] includes() default {};

    public String[] ignores() default {};

    public SerializerFeature[] serialzeFeatures() default {};

    public Feature[] parseFeatures() default {};

    public boolean alphabetic() default true;

    public Class<?> mappingTo() default Void.class;

    public Class<?> builder() default Void.class;

    public String typeName() default "";

    public String typeKey() default "";

    public Class<?>[] seeAlso() default {};

    public Class<?> serializer() default Void.class;

    public Class<?> deserializer() default Void.class;

    public boolean serializeEnumAsJavaBean() default false;

    public PropertyNamingStrategy naming() default PropertyNamingStrategy.NeverUseThisValueExceptDefaultValue;

    public Class<? extends SerializeFilter>[] serialzeFilters() default {};

    public Class<? extends ParserConfig.AutoTypeCheckHandler> autoTypeCheckHandler() default ParserConfig.AutoTypeCheckHandler.class;
}

