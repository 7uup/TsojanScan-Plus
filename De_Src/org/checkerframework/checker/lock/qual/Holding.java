/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.checkerframework.checker.lock.qual;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.checkerframework.checker.lock.qual.LockHeld;
import org.checkerframework.framework.qual.PreconditionAnnotation;

@Documented
@Target(value={ElementType.METHOD, ElementType.CONSTRUCTOR})
@Retention(value=RetentionPolicy.RUNTIME)
@PreconditionAnnotation(qualifier=LockHeld.class)
public @interface Holding {
    public String[] value();
}

