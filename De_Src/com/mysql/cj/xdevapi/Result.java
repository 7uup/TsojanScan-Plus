/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.xdevapi;

import com.mysql.cj.xdevapi.Warning;
import java.util.Iterator;

public interface Result {
    public long getAffectedItemsCount();

    public int getWarningsCount();

    public Iterator<Warning> getWarnings();
}

