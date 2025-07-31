/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.checkerframework.checker.nullness.qual;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.framework.qual.MonotonicQualifier;
import org.checkerframework.framework.qual.SubtypeOf;

@Documented
@SubtypeOf(value={Nullable.class})
@Target(value={ElementType.TYPE_USE})
@MonotonicQualifier(value=NonNull.class)
@Retention(value=RetentionPolicy.RUNTIME)
public @interface MonotonicNonNull {
}

