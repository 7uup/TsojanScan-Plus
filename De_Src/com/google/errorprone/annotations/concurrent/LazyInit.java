/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.errorprone.annotations.concurrent;

import com.google.errorprone.annotations.IncompatibleModifiers;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.lang.model.element.Modifier;

@Retention(value=RetentionPolicy.RUNTIME)
@Target(value={ElementType.FIELD})
@IncompatibleModifiers(value={Modifier.FINAL})
public @interface LazyInit {
}

