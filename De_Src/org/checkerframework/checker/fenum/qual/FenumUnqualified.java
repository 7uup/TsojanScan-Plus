/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.checkerframework.checker.fenum.qual;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.checkerframework.checker.fenum.qual.FenumTop;
import org.checkerframework.framework.qual.DefaultFor;
import org.checkerframework.framework.qual.DefaultQualifierInHierarchy;
import org.checkerframework.framework.qual.SubtypeOf;
import org.checkerframework.framework.qual.TypeUseLocation;

@SubtypeOf(value={FenumTop.class})
@DefaultQualifierInHierarchy
@DefaultFor(value={TypeUseLocation.EXCEPTION_PARAMETER})
@Documented
@Retention(value=RetentionPolicy.RUNTIME)
@Target(value={})
public @interface FenumUnqualified {
}

