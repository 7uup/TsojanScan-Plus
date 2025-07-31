/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.checkerframework.common.value.qual;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.checkerframework.common.value.qual.MinLen;
import org.checkerframework.framework.qual.ConditionalPostconditionAnnotation;
import org.checkerframework.framework.qual.InheritedAnnotation;
import org.checkerframework.framework.qual.QualifierArgument;

@ConditionalPostconditionAnnotation(qualifier=MinLen.class)
@Retention(value=RetentionPolicy.RUNTIME)
@Target(value={ElementType.METHOD, ElementType.CONSTRUCTOR})
@InheritedAnnotation
public @interface EnsuresMinLenIf {
    public String[] expression();

    public boolean result();

    @QualifierArgument(value="value")
    public int targetValue() default 0;
}

