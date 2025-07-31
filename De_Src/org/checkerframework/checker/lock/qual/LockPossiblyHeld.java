/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.checkerframework.checker.lock.qual;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.checkerframework.framework.qual.DefaultFor;
import org.checkerframework.framework.qual.DefaultInUncheckedCodeFor;
import org.checkerframework.framework.qual.DefaultQualifierInHierarchy;
import org.checkerframework.framework.qual.DefaultQualifierInHierarchyInUncheckedCode;
import org.checkerframework.framework.qual.InvisibleQualifier;
import org.checkerframework.framework.qual.LiteralKind;
import org.checkerframework.framework.qual.QualifierForLiterals;
import org.checkerframework.framework.qual.SubtypeOf;
import org.checkerframework.framework.qual.TypeUseLocation;

@InvisibleQualifier
@SubtypeOf(value={})
@Documented
@DefaultQualifierInHierarchy
@DefaultFor(value={TypeUseLocation.LOWER_BOUND}, types={Void.class})
@QualifierForLiterals(value={LiteralKind.NULL})
@DefaultQualifierInHierarchyInUncheckedCode
@DefaultInUncheckedCodeFor(value={TypeUseLocation.PARAMETER, TypeUseLocation.LOWER_BOUND})
@Retention(value=RetentionPolicy.RUNTIME)
@Target(value={})
public @interface LockPossiblyHeld {
}

