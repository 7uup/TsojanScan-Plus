/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.checkerframework.checker.index.qual;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.checkerframework.checker.index.qual.LessThan;
import org.checkerframework.framework.qual.SubtypeOf;

@SubtypeOf(value={LessThan.class})
@Retention(value=RetentionPolicy.RUNTIME)
@Target(value={ElementType.TYPE_PARAMETER, ElementType.TYPE_USE})
public @interface LessThanBottom {
}

