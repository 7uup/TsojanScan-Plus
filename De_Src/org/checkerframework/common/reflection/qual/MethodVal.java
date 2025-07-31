/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.checkerframework.common.reflection.qual;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.checkerframework.common.reflection.qual.UnknownMethod;
import org.checkerframework.framework.qual.SubtypeOf;

@SubtypeOf(value={UnknownMethod.class})
@Retention(value=RetentionPolicy.RUNTIME)
@Target(value={ElementType.TYPE_USE, ElementType.TYPE_PARAMETER})
public @interface MethodVal {
    public String[] className();

    public String[] methodName();

    public int[] params();
}

