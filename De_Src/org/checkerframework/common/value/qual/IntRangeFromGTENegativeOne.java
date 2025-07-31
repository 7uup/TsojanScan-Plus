/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.checkerframework.common.value.qual;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.checkerframework.common.value.qual.UnknownVal;
import org.checkerframework.framework.qual.SubtypeOf;

@SubtypeOf(value={UnknownVal.class})
@Retention(value=RetentionPolicy.SOURCE)
@Target(value={})
public @interface IntRangeFromGTENegativeOne {
}

