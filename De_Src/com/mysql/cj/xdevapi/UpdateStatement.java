/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.xdevapi;

import com.mysql.cj.xdevapi.Result;
import com.mysql.cj.xdevapi.Statement;
import java.util.Map;

public interface UpdateStatement
extends Statement<UpdateStatement, Result> {
    public UpdateStatement set(Map<String, Object> var1);

    public UpdateStatement set(String var1, Object var2);

    public UpdateStatement where(String var1);

    public UpdateStatement orderBy(String ... var1);

    public UpdateStatement limit(long var1);
}

