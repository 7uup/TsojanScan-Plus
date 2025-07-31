/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.checkerframework.framework.qual;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.checkerframework.framework.qual.PolymorphicQualifier;

@Documented
@Target(value={ElementType.TYPE_USE, ElementType.TYPE_PARAMETER})
@Retention(value=RetentionPolicy.RUNTIME)
@PolymorphicQualifier
public @interface PolyAll {
}

