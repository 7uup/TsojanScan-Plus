/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.checkerframework.checker.units.qual;

import java.lang.annotation.Annotation;
import org.checkerframework.checker.units.qual.Prefix;

public @interface UnitsMultiple {
    public Class<? extends Annotation> quantity();

    public Prefix prefix() default Prefix.one;
}

