/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.checkerframework.checker.interning.qual;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.checkerframework.checker.interning.qual.UnknownInterned;
import org.checkerframework.framework.qual.DefaultFor;
import org.checkerframework.framework.qual.LiteralKind;
import org.checkerframework.framework.qual.QualifierForLiterals;
import org.checkerframework.framework.qual.SubtypeOf;
import org.checkerframework.framework.qual.TypeKind;

@SubtypeOf(value={UnknownInterned.class})
@QualifierForLiterals(value={LiteralKind.PRIMITIVE, LiteralKind.STRING})
@DefaultFor(typeKinds={TypeKind.BOOLEAN, TypeKind.BYTE, TypeKind.CHAR, TypeKind.DOUBLE, TypeKind.FLOAT, TypeKind.INT, TypeKind.LONG, TypeKind.SHORT})
@Documented
@Retention(value=RetentionPolicy.RUNTIME)
@Target(value={ElementType.TYPE_USE, ElementType.TYPE_PARAMETER})
public @interface Interned {
}

