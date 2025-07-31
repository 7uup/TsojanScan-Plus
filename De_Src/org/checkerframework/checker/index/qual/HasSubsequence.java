/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.checkerframework.checker.index.qual;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import org.checkerframework.framework.qual.JavaExpression;

@Target(value={ElementType.FIELD})
public @interface HasSubsequence {
    @JavaExpression
    public String subsequence();

    @JavaExpression
    public String from();

    @JavaExpression
    public String to();
}

