/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.xdevapi;

import com.mysql.cj.Messages;
import com.mysql.cj.MysqlxSession;
import com.mysql.cj.protocol.x.StatementExecuteOk;
import com.mysql.cj.protocol.x.XMessageBuilder;
import com.mysql.cj.xdevapi.DocFilterParams;
import com.mysql.cj.xdevapi.FilterableStatement;
import com.mysql.cj.xdevapi.RemoveStatement;
import com.mysql.cj.xdevapi.Result;
import com.mysql.cj.xdevapi.UpdateResult;
import com.mysql.cj.xdevapi.XDevAPIError;
import java.util.concurrent.CompletableFuture;

public class RemoveStatementImpl
extends FilterableStatement<RemoveStatement, Result>
implements RemoveStatement {
    private MysqlxSession mysqlxSession;

    RemoveStatementImpl(MysqlxSession mysqlxSession, String schema, String collection, String criteria) {
        super(new DocFilterParams(schema, collection));
        if (criteria == null || criteria.trim().length() == 0) {
            throw new XDevAPIError(Messages.getString("RemoveStatement.0", new String[]{"criteria"}));
        }
        this.filterParams.setCriteria(criteria);
        this.mysqlxSession = mysqlxSession;
    }

    @Override
    public Result execute() {
        StatementExecuteOk ok = (StatementExecuteOk)this.mysqlxSession.sendMessage(((XMessageBuilder)this.mysqlxSession.getMessageBuilder()).buildDelete(this.filterParams));
        return new UpdateResult(ok);
    }

    @Override
    public CompletableFuture<Result> executeAsync() {
        CompletableFuture okF = this.mysqlxSession.asyncSendMessage(((XMessageBuilder)this.mysqlxSession.getMessageBuilder()).buildDelete(this.filterParams));
        return okF.thenApply(ok -> new UpdateResult((StatementExecuteOk)ok));
    }
}

