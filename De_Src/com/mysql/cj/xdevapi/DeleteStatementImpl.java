/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.xdevapi;

import com.mysql.cj.MysqlxSession;
import com.mysql.cj.protocol.x.StatementExecuteOk;
import com.mysql.cj.protocol.x.XMessageBuilder;
import com.mysql.cj.xdevapi.DeleteStatement;
import com.mysql.cj.xdevapi.FilterableStatement;
import com.mysql.cj.xdevapi.Result;
import com.mysql.cj.xdevapi.TableFilterParams;
import com.mysql.cj.xdevapi.UpdateResult;
import java.util.concurrent.CompletableFuture;

public class DeleteStatementImpl
extends FilterableStatement<DeleteStatement, Result>
implements DeleteStatement {
    private MysqlxSession mysqlxSession;

    DeleteStatementImpl(MysqlxSession mysqlxSession, String schema, String table) {
        super(new TableFilterParams(schema, table));
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

