/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.xdevapi;

import com.mysql.cj.MysqlxSession;
import com.mysql.cj.exceptions.AssertionFailedException;
import com.mysql.cj.protocol.x.StatementExecuteOk;
import com.mysql.cj.protocol.x.XMessageBuilder;
import com.mysql.cj.xdevapi.AddResult;
import com.mysql.cj.xdevapi.AddResultImpl;
import com.mysql.cj.xdevapi.AddStatement;
import com.mysql.cj.xdevapi.DbDoc;
import com.mysql.cj.xdevapi.JsonParser;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class AddStatementImpl
implements AddStatement {
    private MysqlxSession mysqlxSession;
    private String schemaName;
    private String collectionName;
    private List<DbDoc> newDocs;
    private boolean upsert = false;

    AddStatementImpl(MysqlxSession mysqlxSession, String schema, String collection, DbDoc newDoc) {
        this.mysqlxSession = mysqlxSession;
        this.schemaName = schema;
        this.collectionName = collection;
        this.newDocs = new ArrayList<DbDoc>();
        this.newDocs.add(newDoc);
    }

    AddStatementImpl(MysqlxSession mysqlxSession, String schema, String collection, DbDoc[] newDocs) {
        this.mysqlxSession = mysqlxSession;
        this.schemaName = schema;
        this.collectionName = collection;
        this.newDocs = new ArrayList<DbDoc>();
        this.newDocs.addAll(Arrays.asList(newDocs));
    }

    @Override
    public AddStatement add(String jsonString) {
        try {
            DbDoc doc = JsonParser.parseDoc(new StringReader(jsonString));
            return this.add(doc);
        } catch (IOException ex) {
            throw AssertionFailedException.shouldNotHappen(ex);
        }
    }

    @Override
    public AddStatement add(DbDoc ... docs) {
        this.newDocs.addAll(Arrays.asList(docs));
        return this;
    }

    private List<String> serializeDocs() {
        return this.newDocs.stream().map(Object::toString).collect(Collectors.toList());
    }

    @Override
    public AddResult execute() {
        if (this.newDocs.size() == 0) {
            StatementExecuteOk ok = new StatementExecuteOk(0L, null, Collections.emptyList(), Collections.emptyList());
            return new AddResultImpl(ok);
        }
        StatementExecuteOk ok = (StatementExecuteOk)this.mysqlxSession.sendMessage(((XMessageBuilder)this.mysqlxSession.getMessageBuilder()).buildDocInsert(this.schemaName, this.collectionName, this.serializeDocs(), this.upsert));
        return new AddResultImpl(ok);
    }

    @Override
    public CompletableFuture<AddResult> executeAsync() {
        CompletableFuture okF = this.mysqlxSession.asyncSendMessage(((XMessageBuilder)this.mysqlxSession.getMessageBuilder()).buildDocInsert(this.schemaName, this.collectionName, this.serializeDocs(), this.upsert));
        return okF.thenApply(ok -> new AddResultImpl((StatementExecuteOk)ok));
    }

    @Override
    public boolean isUpsert() {
        return this.upsert;
    }

    @Override
    public AddStatement setUpsert(boolean upsert) {
        this.upsert = upsert;
        return this;
    }
}

