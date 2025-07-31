/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.checkerframework.common.value.qual;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Target;

@Inherited
@Target(value={ElementType.TYPE})
public @interface MinLenFieldInvariant {
    public int[] minLen();

    public String[] field();
}

