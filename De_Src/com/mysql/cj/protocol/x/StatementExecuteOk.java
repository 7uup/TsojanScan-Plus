/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.protocol.x;

import com.mysql.cj.QueryResult;
import com.mysql.cj.protocol.ProtocolEntity;
import com.mysql.cj.protocol.Warning;
import java.util.Collections;
import java.util.List;

public class StatementExecuteOk
implements ProtocolEntity,
QueryResult {
    private long rowsAffected;
    private Long lastInsertId;
    private List<String> generatedIds;
    private List<Warning> warnings;

    public StatementExecuteOk(long rowsAffected, Long lastInsertId, List<String> generatedIds, List<Warning> warnings) {
        this.rowsAffected = rowsAffected;
        this.lastInsertId = lastInsertId;
        this.generatedIds = Collections.unmodifiableList(generatedIds);
        this.warnings = warnings;
    }

    public long getRowsAffected() {
        return this.rowsAffected;
    }

    public Long getLastInsertId() {
        return this.lastInsertId;
    }

    public List<String> getGeneratedIds() {
        return this.generatedIds;
    }

    public List<Warning> getWarnings() {
        return this.warnings;
    }
}

