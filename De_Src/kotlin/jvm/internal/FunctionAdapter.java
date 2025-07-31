/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.jvm.internal;

import kotlin.Function;
import kotlin.SinceKotlin;

@SinceKotlin(version="1.4")
public interface FunctionAdapter {
    public Function<?> getFunctionDelegate();
}

