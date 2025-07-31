/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.xdevapi;

import com.mysql.cj.xdevapi.FilterParams;
import com.mysql.cj.xdevapi.RowResult;
import com.mysql.cj.xdevapi.Statement;

public interface SelectStatement
extends Statement<SelectStatement, RowResult> {
    public SelectStatement where(String var1);

    public SelectStatement groupBy(String ... var1);

    public SelectStatement having(String var1);

    public SelectStatement orderBy(String ... var1);

    public SelectStatement limit(long var1);

    public SelectStatement offset(long var1);

    public SelectStatement lockShared();

    public SelectStatement lockShared(Statement.LockContention var1);

    public SelectStatement lockExclusive();

    public SelectStatement lockExclusive(Statement.LockContention var1);

    public FilterParams getFilterParams();
}

