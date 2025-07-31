/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.xdevapi;

import com.mysql.cj.xdevapi.DocResult;
import com.mysql.cj.xdevapi.Expression;
import com.mysql.cj.xdevapi.Statement;

public interface FindStatement
extends Statement<FindStatement, DocResult> {
    public FindStatement fields(String ... var1);

    public FindStatement fields(Expression var1);

    public FindStatement groupBy(String ... var1);

    public FindStatement having(String var1);

    public FindStatement orderBy(String ... var1);

    public FindStatement sort(String ... var1);

    @Deprecated
    default public FindStatement skip(long limitOffset) {
        return this.offset(limitOffset);
    }

    public FindStatement offset(long var1);

    public FindStatement limit(long var1);

    public FindStatement lockShared();

    public FindStatement lockShared(Statement.LockContention var1);

    public FindStatement lockExclusive();

    public FindStatement lockExclusive(Statement.LockContention var1);
}

