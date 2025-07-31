/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.checkerframework.checker.formatter.qual;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import org.checkerframework.checker.formatter.qual.Format;
import org.checkerframework.checker.formatter.qual.InvalidFormat;
import org.checkerframework.framework.qual.DefaultFor;
import org.checkerframework.framework.qual.SubtypeOf;
import org.checkerframework.framework.qual.TargetLocations;
import org.checkerframework.framework.qual.TypeUseLocation;

@SubtypeOf(value={Format.class, InvalidFormat.class})
@Target(value={ElementType.TYPE_USE, ElementType.TYPE_PARAMETER})
@TargetLocations(value={TypeUseLocation.EXPLICIT_LOWER_BOUND, TypeUseLocation.EXPLICIT_UPPER_BOUND})
@DefaultFor(value={TypeUseLocation.LOWER_BOUND})
public @interface FormatBottom {
}

