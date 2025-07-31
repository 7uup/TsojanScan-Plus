/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.xdevapi;

import com.mysql.cj.Messages;
import com.mysql.cj.MysqlxSession;
import com.mysql.cj.protocol.x.StatementExecuteOk;
import com.mysql.cj.protocol.x.XMessageBuilder;
import com.mysql.cj.xdevapi.DbDoc;
import com.mysql.cj.xdevapi.DocFilterParams;
import com.mysql.cj.xdevapi.Expression;
import com.mysql.cj.xdevapi.FilterableStatement;
import com.mysql.cj.xdevapi.ModifyStatement;
import com.mysql.cj.xdevapi.Result;
import com.mysql.cj.xdevapi.UpdateResult;
import com.mysql.cj.xdevapi.UpdateSpec;
import com.mysql.cj.xdevapi.UpdateType;
import com.mysql.cj.xdevapi.XDevAPIError;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class ModifyStatementImpl
extends FilterableStatement<ModifyStatement, Result>
implements ModifyStatement {
    private MysqlxSession mysqlxSession;
    private List<UpdateSpec> updates = new ArrayList<UpdateSpec>();

    ModifyStatementImpl(MysqlxSession mysqlxSession, String schema, String collection, String criteria) {
        super(new DocFilterParams(schema, collection));
        if (criteria == null || criteria.trim().length() == 0) {
            throw new XDevAPIError(Messages.getString("ModifyStatement.0", new String[]{"criteria"}));
        }
        this.filterParams.setCriteria(criteria);
        this.mysqlxSession = mysqlxSession;
    }

    @Override
    public Result execute() {
        StatementExecuteOk ok = (StatementExecuteOk)this.mysqlxSession.sendMessage(((XMessageBuilder)this.mysqlxSession.getMessageBuilder()).buildDocUpdate(this.filterParams, this.updates));
        return new UpdateResult(ok);
    }

    @Override
    public CompletableFuture<Result> executeAsync() {
        CompletableFuture okF = this.mysqlxSession.asyncSendMessage(((XMessageBuilder)this.mysqlxSession.getMessageBuilder()).buildDocUpdate(this.filterParams, this.updates));
        return okF.thenApply(ok -> new UpdateResult((StatementExecuteOk)ok));
    }

    @Override
    public ModifyStatement set(String docPath, Object value) {
        this.updates.add(new UpdateSpec(UpdateType.ITEM_SET, docPath).setValue(value));
        return this;
    }

    @Override
    public ModifyStatement change(String docPath, Object value) {
        this.updates.add(new UpdateSpec(UpdateType.ITEM_REPLACE, docPath).setValue(value));
        return this;
    }

    @Override
    public ModifyStatement unset(String ... fields) {
        this.updates.addAll(Arrays.stream(fields).map(docPath -> new UpdateSpec(UpdateType.ITEM_REMOVE, (String)docPath)).collect(Collectors.toList()));
        return this;
    }

    @Override
    public ModifyStatement patch(DbDoc document) {
        return this.patch(document.toString());
    }

    @Override
    public ModifyStatement patch(String document) {
        this.updates.add(new UpdateSpec(UpdateType.MERGE_PATCH, "").setValue(Expression.expr(document)));
        return this;
    }

    @Override
    public ModifyStatement arrayInsert(String field, Object value) {
        this.updates.add(new UpdateSpec(UpdateType.ARRAY_INSERT, field).setValue(value));
        return this;
    }

    @Override
    public ModifyStatement arrayAppend(String docPath, Object value) {
        this.updates.add(new UpdateSpec(UpdateType.ARRAY_APPEND, docPath).setValue(value));
        return this;
    }
}

