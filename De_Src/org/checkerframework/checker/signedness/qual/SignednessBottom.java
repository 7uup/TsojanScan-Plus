/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.checkerframework.checker.signedness.qual;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import org.checkerframework.checker.signedness.qual.SignednessGlb;
import org.checkerframework.framework.qual.SubtypeOf;
import org.checkerframework.framework.qual.TargetLocations;
import org.checkerframework.framework.qual.TypeUseLocation;

@SubtypeOf(value={SignednessGlb.class})
@Target(value={ElementType.TYPE_USE, ElementType.TYPE_PARAMETER})
@TargetLocations(value={TypeUseLocation.EXPLICIT_LOWER_BOUND, TypeUseLocation.EXPLICIT_UPPER_BOUND})
public @interface SignednessBottom {
}

