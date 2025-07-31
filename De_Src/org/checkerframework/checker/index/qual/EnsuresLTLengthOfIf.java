/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.checkerframework.checker.index.qual;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.checkerframework.checker.index.qual.LTLengthOf;
import org.checkerframework.framework.qual.ConditionalPostconditionAnnotation;
import org.checkerframework.framework.qual.InheritedAnnotation;
import org.checkerframework.framework.qual.JavaExpression;
import org.checkerframework.framework.qual.QualifierArgument;

@Documented
@Retention(value=RetentionPolicy.RUNTIME)
@Target(value={ElementType.METHOD, ElementType.CONSTRUCTOR})
@ConditionalPostconditionAnnotation(qualifier=LTLengthOf.class)
@InheritedAnnotation
public @interface EnsuresLTLengthOfIf {
    public String[] expression();

    public boolean result();

    @JavaExpression
    @QualifierArgument(value="value")
    public String[] targetValue();

    @JavaExpression
    @QualifierArgument(value="offset")
    public String[] offset() default {};
}

