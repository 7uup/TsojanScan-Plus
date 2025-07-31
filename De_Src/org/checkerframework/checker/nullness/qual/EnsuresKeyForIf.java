/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.checkerframework.checker.nullness.qual;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.checkerframework.checker.nullness.qual.KeyFor;
import org.checkerframework.framework.qual.ConditionalPostconditionAnnotation;
import org.checkerframework.framework.qual.InheritedAnnotation;
import org.checkerframework.framework.qual.JavaExpression;
import org.checkerframework.framework.qual.QualifierArgument;

@Documented
@Retention(value=RetentionPolicy.RUNTIME)
@Target(value={ElementType.METHOD, ElementType.CONSTRUCTOR})
@ConditionalPostconditionAnnotation(qualifier=KeyFor.class)
@InheritedAnnotation
public @interface EnsuresKeyForIf {
    public boolean result();

    public String[] expression();

    @JavaExpression
    @QualifierArgument(value="value")
    public String[] map();
}

