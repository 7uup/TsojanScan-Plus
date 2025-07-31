/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.util.concurrent;

import com.google.errorprone.annotations.DoNotMock;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;

@DoNotMock(value="Use the methods in Futures (like immediateFuture) or SettableFuture")
public interface ListenableFuture<V>
extends Future<V> {
    public void addListener(Runnable var1, Executor var2);
}

