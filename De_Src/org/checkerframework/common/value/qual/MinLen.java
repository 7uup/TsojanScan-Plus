/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.checkerframework.common.value.qual;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(value={ElementType.TYPE_USE, ElementType.TYPE_PARAMETER})
public @interface MinLen {
    public int value() default 0;
}

