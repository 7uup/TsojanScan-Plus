/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.jdbc;

import com.mysql.cj.PingTarget;
import com.mysql.cj.Query;
import com.mysql.cj.exceptions.ExceptionInterceptor;
import com.mysql.cj.jdbc.result.ResultSetInternalMethods;
import java.io.InputStream;
import java.sql.SQLException;
import java.sql.Statement;

public interface JdbcStatement
extends Statement,
Query {
    public static final int MAX_ROWS = 50000000;

    public void enableStreamingResults() throws SQLException;

    public void disableStreamingResults() throws SQLException;

    public void setLocalInfileInputStream(InputStream var1);

    public InputStream getLocalInfileInputStream();

    public void setPingTarget(PingTarget var1);

    public ExceptionInterceptor getExceptionInterceptor();

    public void removeOpenResultSet(ResultSetInternalMethods var1);

    public int getOpenResultSetCount();

    public void setHoldResultsOpenOverClose(boolean var1);

    public Query getQuery();
}

