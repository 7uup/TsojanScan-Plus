/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.xdevapi;

import com.mysql.cj.MysqlxSession;
import com.mysql.cj.protocol.ColumnDefinition;
import com.mysql.cj.protocol.x.StatementExecuteOk;
import com.mysql.cj.result.RowList;
import com.mysql.cj.xdevapi.FilterParams;
import com.mysql.cj.xdevapi.FilterableStatement;
import com.mysql.cj.xdevapi.RowResult;
import com.mysql.cj.xdevapi.RowResultImpl;
import com.mysql.cj.xdevapi.SelectStatement;
import com.mysql.cj.xdevapi.Statement;
import com.mysql.cj.xdevapi.TableFilterParams;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class SelectStatementImpl
extends FilterableStatement<SelectStatement, RowResult>
implements SelectStatement {
    private MysqlxSession mysqlxSession;

    SelectStatementImpl(MysqlxSession mysqlxSession, String schema, String table, String ... projection) {
        super(new TableFilterParams(schema, table));
        this.mysqlxSession = mysqlxSession;
        if (projection != null && projection.length > 0) {
            this.filterParams.setFields(projection);
        }
    }

    @Override
    public RowResultImpl execute() {
        return (RowResultImpl)this.mysqlxSession.find(this.filterParams, metadata -> (rows, task) -> new RowResultImpl((ColumnDefinition)metadata, this.mysqlxSession.getServerSession().getDefaultTimeZone(), (RowList)rows, (Supplier<StatementExecuteOk>)task));
    }

    @Override
    public CompletableFuture<RowResult> executeAsync() {
        return this.mysqlxSession.asyncFind(this.filterParams, metadata -> (rows, task) -> new RowResultImpl((ColumnDefinition)metadata, this.mysqlxSession.getServerSession().getDefaultTimeZone(), (RowList)rows, (Supplier<StatementExecuteOk>)task));
    }

    @Override
    public SelectStatement groupBy(String ... groupBy) {
        this.filterParams.setGrouping(groupBy);
        return this;
    }

    @Override
    public SelectStatement having(String having) {
        this.filterParams.setGroupingCriteria(having);
        return this;
    }

    @Override
    public FilterParams getFilterParams() {
        return this.filterParams;
    }

    @Override
    public SelectStatement lockShared() {
        return this.lockShared(Statement.LockContention.DEFAULT);
    }

    @Override
    public SelectStatement lockShared(Statement.LockContention lockContention) {
        this.filterParams.setLock(FilterParams.RowLock.SHARED_LOCK);
        switch (lockContention) {
            case NOWAIT: {
                this.filterParams.setLockOption(FilterParams.RowLockOptions.NOWAIT);
                break;
            }
            case SKIP_LOCKED: {
                this.filterParams.setLockOption(FilterParams.RowLockOptions.SKIP_LOCKED);
                break;
            }
        }
        return this;
    }

    @Override
    public SelectStatement lockExclusive() {
        return this.lockExclusive(Statement.LockContention.DEFAULT);
    }

    @Override
    public SelectStatement lockExclusive(Statement.LockContention lockContention) {
        this.filterParams.setLock(FilterParams.RowLock.EXCLUSIVE_LOCK);
        switch (lockContention) {
            case NOWAIT: {
                this.filterParams.setLockOption(FilterParams.RowLockOptions.NOWAIT);
                break;
            }
            case SKIP_LOCKED: {
                this.filterParams.setLockOption(FilterParams.RowLockOptions.SKIP_LOCKED);
                break;
            }
        }
        return this;
    }
}

