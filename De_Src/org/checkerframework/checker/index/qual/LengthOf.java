/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.checkerframework.checker.index.qual;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(value={ElementType.TYPE_USE, ElementType.TYPE_PARAMETER, ElementType.METHOD})
public @interface LengthOf {
    public String[] value();
}

