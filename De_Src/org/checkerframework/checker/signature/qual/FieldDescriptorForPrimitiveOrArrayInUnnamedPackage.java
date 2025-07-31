/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.checkerframework.checker.signature.qual;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import org.checkerframework.checker.signature.qual.ClassGetName;
import org.checkerframework.checker.signature.qual.FieldDescriptor;
import org.checkerframework.framework.qual.QualifierForLiterals;
import org.checkerframework.framework.qual.SubtypeOf;

@SubtypeOf(value={ClassGetName.class, FieldDescriptor.class})
@QualifierForLiterals(stringPatterns={"^([BCDFIJSZ]|\\[+[BCDFIJSZ]|\\[L[A-Za-z_][A-Za-z_0-9]*;)$"})
@Target(value={ElementType.TYPE_USE, ElementType.TYPE_PARAMETER})
public @interface FieldDescriptorForPrimitiveOrArrayInUnnamedPackage {
}

