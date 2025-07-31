/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.j2objc.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value={ElementType.LOCAL_VARIABLE})
@Retention(value=RetentionPolicy.SOURCE)
public @interface LoopTranslation {
    public LoopStyle value();

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static enum LoopStyle {
        JAVA_ITERATOR,
        FAST_ENUMERATION;

    }
}

