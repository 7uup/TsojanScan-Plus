/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.checkerframework.checker.tainting.qual;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.checkerframework.checker.tainting.qual.Tainted;
import org.checkerframework.framework.qual.DefaultFor;
import org.checkerframework.framework.qual.LiteralKind;
import org.checkerframework.framework.qual.QualifierForLiterals;
import org.checkerframework.framework.qual.SubtypeOf;
import org.checkerframework.framework.qual.TypeUseLocation;

@SubtypeOf(value={Tainted.class})
@QualifierForLiterals(value={LiteralKind.STRING})
@Target(value={ElementType.TYPE_USE, ElementType.TYPE_PARAMETER})
@Retention(value=RetentionPolicy.RUNTIME)
@DefaultFor(value={TypeUseLocation.LOWER_BOUND})
public @interface Untainted {
}

