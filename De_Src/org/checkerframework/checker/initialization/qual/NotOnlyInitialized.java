/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.checkerframework.checker.initialization.qual;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target(value={ElementType.FIELD})
@Retention(value=RetentionPolicy.RUNTIME)
public @interface NotOnlyInitialized {
}

