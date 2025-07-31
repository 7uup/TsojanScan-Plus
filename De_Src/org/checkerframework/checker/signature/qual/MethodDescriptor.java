/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.checkerframework.checker.signature.qual;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import org.checkerframework.checker.signature.qual.SignatureUnknown;
import org.checkerframework.framework.qual.SubtypeOf;

@SubtypeOf(value={SignatureUnknown.class})
@Target(value={ElementType.TYPE_USE, ElementType.TYPE_PARAMETER})
public @interface MethodDescriptor {
}

