/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.xdevapi;

import com.mysql.cj.exceptions.FeatureNotAvailableException;
import com.mysql.cj.protocol.x.StatementExecuteOk;
import com.mysql.cj.xdevapi.Column;
import com.mysql.cj.xdevapi.Row;
import com.mysql.cj.xdevapi.SqlResult;
import com.mysql.cj.xdevapi.UpdateResult;
import java.util.List;

public class SqlUpdateResult
extends UpdateResult
implements SqlResult {
    public SqlUpdateResult(StatementExecuteOk ok) {
        super(ok);
    }

    @Override
    public boolean hasData() {
        return false;
    }

    @Override
    public boolean nextResult() {
        throw new FeatureNotAvailableException("Not a multi-result");
    }

    @Override
    public List<Row> fetchAll() {
        throw new FeatureNotAvailableException("No data");
    }

    @Override
    public Row next() {
        throw new FeatureNotAvailableException("No data");
    }

    @Override
    public boolean hasNext() {
        throw new FeatureNotAvailableException("No data");
    }

    @Override
    public int getColumnCount() {
        throw new FeatureNotAvailableException("No data");
    }

    @Override
    public List<Column> getColumns() {
        throw new FeatureNotAvailableException("No data");
    }

    @Override
    public List<String> getColumnNames() {
        throw new FeatureNotAvailableException("No data");
    }

    @Override
    public long count() {
        throw new FeatureNotAvailableException("No data");
    }

    @Override
    public Long getAutoIncrementValue() {
        return this.ok.getLastInsertId();
    }
}

