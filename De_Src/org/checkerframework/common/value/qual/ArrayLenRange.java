/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.checkerframework.common.value.qual;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.checkerframework.common.value.qual.UnknownVal;
import org.checkerframework.framework.qual.SubtypeOf;

@SubtypeOf(value={UnknownVal.class})
@Retention(value=RetentionPolicy.RUNTIME)
@Target(value={ElementType.TYPE_PARAMETER, ElementType.TYPE_USE})
public @interface ArrayLenRange {
    public int from() default 0;

    public int to() default 0x7FFFFFFF;
}

