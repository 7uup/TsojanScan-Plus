/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.checkerframework.common.aliasing.qual;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.checkerframework.framework.qual.DefaultFor;
import org.checkerframework.framework.qual.DefaultQualifierInHierarchy;
import org.checkerframework.framework.qual.SubtypeOf;
import org.checkerframework.framework.qual.TypeUseLocation;

@Documented
@DefaultQualifierInHierarchy
@DefaultFor(value={TypeUseLocation.UPPER_BOUND, TypeUseLocation.LOWER_BOUND}, types={Void.class})
@Retention(value=RetentionPolicy.RUNTIME)
@Target(value={ElementType.TYPE_PARAMETER, ElementType.TYPE_USE})
@SubtypeOf(value={})
public @interface MaybeAliased {
}

