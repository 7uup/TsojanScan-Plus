/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.checkerframework.checker.index.qual;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import org.checkerframework.checker.index.qual.LowerBoundUnknown;
import org.checkerframework.framework.qual.SubtypeOf;

@SubtypeOf(value={LowerBoundUnknown.class})
@Target(value={ElementType.TYPE_USE, ElementType.TYPE_PARAMETER})
public @interface GTENegativeOne {
}

