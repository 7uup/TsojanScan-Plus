/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.xdevapi;

import com.mysql.cj.MysqlxSession;
import com.mysql.cj.exceptions.FeatureNotAvailableException;
import com.mysql.cj.xdevapi.SqlResult;
import com.mysql.cj.xdevapi.SqlStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class SqlStatementImpl
implements SqlStatement {
    private MysqlxSession mysqlxSession;
    private String sql;
    private List<Object> args = new ArrayList<Object>();

    public SqlStatementImpl(MysqlxSession mysqlxSession, String sql) {
        this.mysqlxSession = mysqlxSession;
        this.sql = sql;
    }

    @Override
    public SqlResult execute() {
        return this.mysqlxSession.executeSql(this.sql, this.args);
    }

    @Override
    public CompletableFuture<SqlResult> executeAsync() {
        return this.mysqlxSession.asyncExecuteSql(this.sql, this.args);
    }

    @Override
    public SqlStatement clearBindings() {
        this.args.clear();
        return this;
    }

    @Override
    public SqlStatement bind(List<Object> values2) {
        this.args.addAll(values2);
        return this;
    }

    @Override
    public SqlStatement bind(Map<String, Object> values2) {
        throw new FeatureNotAvailableException("Cannot bind named parameters for SQL statements");
    }
}

