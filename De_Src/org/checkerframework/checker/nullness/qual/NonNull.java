/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.checkerframework.checker.nullness.qual;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.checkerframework.framework.qual.DefaultFor;
import org.checkerframework.framework.qual.DefaultInUncheckedCodeFor;
import org.checkerframework.framework.qual.DefaultQualifierInHierarchy;
import org.checkerframework.framework.qual.LiteralKind;
import org.checkerframework.framework.qual.QualifierForLiterals;
import org.checkerframework.framework.qual.SubtypeOf;
import org.checkerframework.framework.qual.TypeKind;
import org.checkerframework.framework.qual.TypeUseLocation;
import org.checkerframework.framework.qual.UpperBoundFor;

@SubtypeOf(value={MonotonicNonNull.class})
@QualifierForLiterals(value={LiteralKind.STRING})
@DefaultQualifierInHierarchy
@DefaultFor(value={TypeUseLocation.EXCEPTION_PARAMETER})
@UpperBoundFor(typeKinds={TypeKind.PACKAGE, TypeKind.INT, TypeKind.BOOLEAN, TypeKind.CHAR, TypeKind.DOUBLE, TypeKind.FLOAT, TypeKind.LONG, TypeKind.SHORT, TypeKind.BYTE})
@DefaultInUncheckedCodeFor(value={TypeUseLocation.PARAMETER, TypeUseLocation.LOWER_BOUND})
@Documented
@Retention(value=RetentionPolicy.RUNTIME)
@Target(value={ElementType.TYPE_USE, ElementType.TYPE_PARAMETER})
public @interface NonNull {
}

