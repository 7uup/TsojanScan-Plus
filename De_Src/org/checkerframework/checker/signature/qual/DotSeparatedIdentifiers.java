/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.checkerframework.checker.signature.qual;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import org.checkerframework.checker.signature.qual.BinaryName;
import org.checkerframework.checker.signature.qual.FullyQualifiedName;
import org.checkerframework.framework.qual.QualifierForLiterals;
import org.checkerframework.framework.qual.SubtypeOf;

@SubtypeOf(value={FullyQualifiedName.class, BinaryName.class})
@QualifierForLiterals(stringPatterns={"^[A-Za-z_][A-Za-z_0-9]*(\\.[A-Za-z_][A-Za-z_0-9]*)*$"})
@Target(value={ElementType.TYPE_USE, ElementType.TYPE_PARAMETER})
public @interface DotSeparatedIdentifiers {
}

