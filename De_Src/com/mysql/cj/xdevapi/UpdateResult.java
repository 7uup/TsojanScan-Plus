/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.xdevapi;

import com.mysql.cj.protocol.x.StatementExecuteOk;
import com.mysql.cj.xdevapi.Result;
import com.mysql.cj.xdevapi.Warning;
import com.mysql.cj.xdevapi.WarningImpl;
import java.util.Iterator;
import java.util.stream.Collectors;

public class UpdateResult
implements Result {
    protected StatementExecuteOk ok;

    public UpdateResult(StatementExecuteOk ok) {
        this.ok = ok;
    }

    @Override
    public long getAffectedItemsCount() {
        return this.ok.getRowsAffected();
    }

    @Override
    public int getWarningsCount() {
        return this.ok.getWarnings().size();
    }

    @Override
    public Iterator<Warning> getWarnings() {
        return this.ok.getWarnings().stream().map(w -> new WarningImpl((com.mysql.cj.protocol.Warning)w)).collect(Collectors.toList()).iterator();
    }
}

