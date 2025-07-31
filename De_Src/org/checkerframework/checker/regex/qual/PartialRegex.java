/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.checkerframework.checker.regex.qual;

import java.lang.annotation.Target;
import org.checkerframework.checker.regex.qual.UnknownRegex;
import org.checkerframework.framework.qual.InvisibleQualifier;
import org.checkerframework.framework.qual.SubtypeOf;

@InvisibleQualifier
@SubtypeOf(value={UnknownRegex.class})
@Target(value={})
public @interface PartialRegex {
    public String value() default "";
}

