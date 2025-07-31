/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.xdevapi;

import com.mysql.cj.exceptions.FeatureNotAvailableException;
import com.mysql.cj.protocol.ColumnDefinition;
import com.mysql.cj.protocol.x.StatementExecuteOk;
import com.mysql.cj.result.RowList;
import com.mysql.cj.xdevapi.RowResultImpl;
import com.mysql.cj.xdevapi.SqlResult;
import com.mysql.cj.xdevapi.XDevAPIError;
import java.util.TimeZone;
import java.util.function.Supplier;

public class SqlDataResult
extends RowResultImpl
implements SqlResult {
    public SqlDataResult(ColumnDefinition metadata, TimeZone defaultTimeZone, RowList rows, Supplier<StatementExecuteOk> completer) {
        super(metadata, defaultTimeZone, rows, completer);
    }

    @Override
    public boolean nextResult() {
        throw new FeatureNotAvailableException("Not a multi-result");
    }

    @Override
    public long getAffectedItemsCount() {
        return this.getStatementExecuteOk().getRowsAffected();
    }

    @Override
    public Long getAutoIncrementValue() {
        throw new XDevAPIError("Method getAutoIncrementValue() is allowed only for insert statements.");
    }
}

