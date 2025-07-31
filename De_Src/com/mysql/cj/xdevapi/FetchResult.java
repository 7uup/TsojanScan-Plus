/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.xdevapi;

import com.mysql.cj.xdevapi.Warning;
import java.util.Iterator;
import java.util.List;

public interface FetchResult<T>
extends Iterator<T>,
Iterable<T> {
    default public boolean hasData() {
        return true;
    }

    default public T fetchOne() {
        if (this.hasNext()) {
            return (T)this.next();
        }
        return null;
    }

    @Override
    default public Iterator<T> iterator() {
        return this.fetchAll().iterator();
    }

    public long count();

    public List<T> fetchAll();

    public int getWarningsCount();

    public Iterator<Warning> getWarnings();
}

