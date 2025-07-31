/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.xdevapi;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;

public interface Statement<STMT_T, RES_T> {
    public RES_T execute();

    public CompletableFuture<RES_T> executeAsync();

    default public STMT_T clearBindings() {
        throw new UnsupportedOperationException("This statement doesn't support bound parameters");
    }

    default public STMT_T bind(String argName, Object value) {
        throw new UnsupportedOperationException("This statement doesn't support bound parameters");
    }

    default public STMT_T bind(Map<String, Object> values2) {
        this.clearBindings();
        values2.entrySet().forEach(e -> this.bind((String)e.getKey(), e.getValue()));
        return (STMT_T)this;
    }

    default public STMT_T bind(List<Object> values2) {
        this.clearBindings();
        IntStream.range(0, values2.size()).forEach(i -> this.bind(String.valueOf(i), values2.get(i)));
        return (STMT_T)this;
    }

    default public STMT_T bind(Object ... values2) {
        return this.bind(Arrays.asList(values2));
    }

    public static enum LockContention {
        DEFAULT,
        NOWAIT,
        SKIP_LOCKED;

    }
}

