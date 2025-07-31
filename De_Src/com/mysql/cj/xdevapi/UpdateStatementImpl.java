/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.xdevapi;

import com.mysql.cj.MysqlxSession;
import com.mysql.cj.protocol.x.StatementExecuteOk;
import com.mysql.cj.protocol.x.XMessageBuilder;
import com.mysql.cj.xdevapi.FilterableStatement;
import com.mysql.cj.xdevapi.Result;
import com.mysql.cj.xdevapi.TableFilterParams;
import com.mysql.cj.xdevapi.UpdateParams;
import com.mysql.cj.xdevapi.UpdateResult;
import com.mysql.cj.xdevapi.UpdateStatement;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class UpdateStatementImpl
extends FilterableStatement<UpdateStatement, Result>
implements UpdateStatement {
    private MysqlxSession mysqlxSession;
    private UpdateParams updateParams = new UpdateParams();

    UpdateStatementImpl(MysqlxSession mysqlxSession, String schema, String table) {
        super(new TableFilterParams(schema, table));
        this.mysqlxSession = mysqlxSession;
    }

    @Override
    public Result execute() {
        StatementExecuteOk ok = (StatementExecuteOk)this.mysqlxSession.sendMessage(((XMessageBuilder)this.mysqlxSession.getMessageBuilder()).buildRowUpdate(this.filterParams, this.updateParams));
        return new UpdateResult(ok);
    }

    @Override
    public CompletableFuture<Result> executeAsync() {
        CompletableFuture okF = this.mysqlxSession.asyncSendMessage(((XMessageBuilder)this.mysqlxSession.getMessageBuilder()).buildRowUpdate(this.filterParams, this.updateParams));
        return okF.thenApply(ok -> new UpdateResult((StatementExecuteOk)ok));
    }

    @Override
    public UpdateStatement set(Map<String, Object> fieldsAndValues) {
        this.updateParams.setUpdates(fieldsAndValues);
        return this;
    }

    @Override
    public UpdateStatement set(String field, Object value) {
        this.updateParams.addUpdate(field, value);
        return this;
    }
}

