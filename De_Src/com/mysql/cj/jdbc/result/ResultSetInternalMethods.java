/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.jdbc.result;

import com.mysql.cj.jdbc.JdbcPreparedStatement;
import com.mysql.cj.jdbc.JdbcStatement;
import com.mysql.cj.jdbc.result.CachedResultSetMetaData;
import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.protocol.ResultsetRowsOwner;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

public interface ResultSetInternalMethods
extends ResultSet,
ResultsetRowsOwner,
Resultset {
    public Object getObjectStoredProc(int var1, int var2) throws SQLException;

    public Object getObjectStoredProc(int var1, Map<Object, Object> var2, int var3) throws SQLException;

    public Object getObjectStoredProc(String var1, int var2) throws SQLException;

    public Object getObjectStoredProc(String var1, Map<Object, Object> var2, int var3) throws SQLException;

    public void realClose(boolean var1) throws SQLException;

    public void setFirstCharOfQuery(char var1);

    public void setOwningStatement(JdbcStatement var1);

    public char getFirstCharOfQuery();

    public void setStatementUsedForFetchingRows(JdbcPreparedStatement var1);

    public void setWrapperStatement(Statement var1);

    public void initializeWithMetadata() throws SQLException;

    public void populateCachedMetaData(CachedResultSetMetaData var1) throws SQLException;

    public BigInteger getBigInteger(int var1) throws SQLException;
}

