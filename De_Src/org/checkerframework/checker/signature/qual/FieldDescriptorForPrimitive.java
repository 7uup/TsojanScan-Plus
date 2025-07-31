/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.checkerframework.checker.signature.qual;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import org.checkerframework.checker.signature.qual.FieldDescriptorForPrimitiveOrArrayInUnnamedPackage;
import org.checkerframework.checker.signature.qual.Identifier;
import org.checkerframework.framework.qual.QualifierForLiterals;
import org.checkerframework.framework.qual.SubtypeOf;

@SubtypeOf(value={FieldDescriptorForPrimitiveOrArrayInUnnamedPackage.class, Identifier.class})
@QualifierForLiterals(stringPatterns={"^[BCDFIJSZ]$"})
@Target(value={ElementType.TYPE_USE, ElementType.TYPE_PARAMETER})
public @interface FieldDescriptorForPrimitive {
}

