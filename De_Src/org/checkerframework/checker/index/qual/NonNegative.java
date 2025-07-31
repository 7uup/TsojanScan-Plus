/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.checkerframework.checker.index.qual;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import org.checkerframework.checker.index.qual.GTENegativeOne;
import org.checkerframework.framework.qual.SubtypeOf;

@SubtypeOf(value={GTENegativeOne.class})
@Target(value={ElementType.TYPE_USE, ElementType.TYPE_PARAMETER})
public @interface NonNegative {
}

