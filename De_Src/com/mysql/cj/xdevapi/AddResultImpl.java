/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.xdevapi;

import com.mysql.cj.protocol.x.StatementExecuteOk;
import com.mysql.cj.xdevapi.AddResult;
import com.mysql.cj.xdevapi.UpdateResult;
import java.util.List;

public class AddResultImpl
extends UpdateResult
implements AddResult {
    public AddResultImpl(StatementExecuteOk ok) {
        super(ok);
    }

    @Override
    public List<String> getGeneratedIds() {
        return this.ok.getGeneratedIds();
    }
}

