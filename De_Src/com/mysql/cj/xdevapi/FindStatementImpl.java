/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.xdevapi;

import com.mysql.cj.MysqlxSession;
import com.mysql.cj.protocol.x.StatementExecuteOk;
import com.mysql.cj.result.RowList;
import com.mysql.cj.xdevapi.DocFilterParams;
import com.mysql.cj.xdevapi.DocResult;
import com.mysql.cj.xdevapi.DocResultImpl;
import com.mysql.cj.xdevapi.Expression;
import com.mysql.cj.xdevapi.FilterParams;
import com.mysql.cj.xdevapi.FilterableStatement;
import com.mysql.cj.xdevapi.FindStatement;
import com.mysql.cj.xdevapi.Statement;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class FindStatementImpl
extends FilterableStatement<FindStatement, DocResult>
implements FindStatement {
    private MysqlxSession mysqlxSession;

    FindStatementImpl(MysqlxSession mysqlxSession, String schema, String collection, String criteria) {
        super(new DocFilterParams(schema, collection));
        this.mysqlxSession = mysqlxSession;
        if (criteria != null && criteria.length() > 0) {
            this.filterParams.setCriteria(criteria);
        }
    }

    @Override
    public DocResultImpl execute() {
        return (DocResultImpl)this.mysqlxSession.find(this.filterParams, metadata -> (rows, task) -> new DocResultImpl((RowList)rows, (Supplier<StatementExecuteOk>)task));
    }

    @Override
    public CompletableFuture<DocResult> executeAsync() {
        return this.mysqlxSession.asyncFind(this.filterParams, metadata -> (rows, task) -> new DocResultImpl((RowList)rows, (Supplier<StatementExecuteOk>)task));
    }

    @Override
    public FindStatement fields(String ... projection) {
        this.filterParams.setFields(projection);
        return this;
    }

    @Override
    public FindStatement fields(Expression docProjection) {
        ((DocFilterParams)this.filterParams).setFields(docProjection);
        return this;
    }

    @Override
    public FindStatement groupBy(String ... groupBy) {
        this.filterParams.setGrouping(groupBy);
        return this;
    }

    @Override
    public FindStatement having(String having) {
        this.filterParams.setGroupingCriteria(having);
        return this;
    }

    @Override
    public FindStatement lockShared() {
        return this.lockShared(Statement.LockContention.DEFAULT);
    }

    @Override
    public FindStatement lockShared(Statement.LockContention lockContention) {
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
    public FindStatement lockExclusive() {
        return this.lockExclusive(Statement.LockContention.DEFAULT);
    }

    @Override
    public FindStatement lockExclusive(Statement.LockContention lockContention) {
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

