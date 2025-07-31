/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.errorprone.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.lang.model.element.Modifier;

@Documented
@Retention(value=RetentionPolicy.CLASS)
@Target(value={ElementType.ANNOTATION_TYPE})
public @interface RequiredModifiers {
    public Modifier[] value();
}

