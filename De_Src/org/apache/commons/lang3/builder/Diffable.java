/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3.builder;

import org.apache.commons.lang3.builder.DiffResult;

public interface Diffable<T> {
    public DiffResult diff(T var1);
}

