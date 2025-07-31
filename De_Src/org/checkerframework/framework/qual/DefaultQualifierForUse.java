/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.checkerframework.framework.qual;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(value={ElementType.TYPE})
public @interface DefaultQualifierForUse {
    public Class<? extends Annotation>[] value() default {};
}

