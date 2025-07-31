/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.checkerframework.checker.lock.qual;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.checkerframework.framework.qual.DefaultInUncheckedCodeFor;
import org.checkerframework.framework.qual.SubtypeOf;
import org.checkerframework.framework.qual.TypeUseLocation;

@SubtypeOf(value={})
@Retention(value=RetentionPolicy.RUNTIME)
@DefaultInUncheckedCodeFor(value={TypeUseLocation.RECEIVER})
@Target(value={ElementType.TYPE_USE, ElementType.TYPE_PARAMETER})
public @interface GuardedByUnknown {
}

