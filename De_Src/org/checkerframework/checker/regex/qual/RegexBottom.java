/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.checkerframework.checker.regex.qual;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import org.checkerframework.checker.regex.qual.PartialRegex;
import org.checkerframework.checker.regex.qual.Regex;
import org.checkerframework.framework.qual.DefaultFor;
import org.checkerframework.framework.qual.InvisibleQualifier;
import org.checkerframework.framework.qual.SubtypeOf;
import org.checkerframework.framework.qual.TargetLocations;
import org.checkerframework.framework.qual.TypeUseLocation;

@InvisibleQualifier
@SubtypeOf(value={Regex.class, PartialRegex.class})
@DefaultFor(value={TypeUseLocation.LOWER_BOUND})
@Target(value={ElementType.TYPE_USE, ElementType.TYPE_PARAMETER})
@TargetLocations(value={TypeUseLocation.EXPLICIT_LOWER_BOUND, TypeUseLocation.EXPLICIT_UPPER_BOUND})
public @interface RegexBottom {
}

