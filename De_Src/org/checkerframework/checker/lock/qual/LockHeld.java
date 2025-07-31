/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.checkerframework.checker.lock.qual;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.checkerframework.checker.lock.qual.LockPossiblyHeld;
import org.checkerframework.framework.qual.InvisibleQualifier;
import org.checkerframework.framework.qual.SubtypeOf;

@SubtypeOf(value={LockPossiblyHeld.class})
@InvisibleQualifier
@Documented
@Retention(value=RetentionPolicy.RUNTIME)
@Target(value={})
public @interface LockHeld {
}

