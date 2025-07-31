/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.checkerframework.checker.lock.qual;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.checkerframework.checker.lock.qual.GuardedByUnknown;
import org.checkerframework.framework.qual.SubtypeOf;
import org.checkerframework.framework.qual.TargetLocations;
import org.checkerframework.framework.qual.TypeUseLocation;

@SubtypeOf(value={GuardedByUnknown.class})
@Documented
@Retention(value=RetentionPolicy.RUNTIME)
@TargetLocations(value={TypeUseLocation.RECEIVER, TypeUseLocation.PARAMETER, TypeUseLocation.RETURN})
@Target(value={ElementType.TYPE_USE})
public @interface GuardSatisfied {
    public int value() default -1;
}

