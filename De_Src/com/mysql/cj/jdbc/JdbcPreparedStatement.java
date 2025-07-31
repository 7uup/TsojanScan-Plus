/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.jdbc;

import com.mysql.cj.MysqlType;
import com.mysql.cj.ParseInfo;
import com.mysql.cj.QueryBindings;
import com.mysql.cj.jdbc.JdbcStatement;
import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface JdbcPreparedStatement
extends PreparedStatement,
JdbcStatement {
    public void realClose(boolean var1, boolean var2) throws SQLException;

    public QueryBindings<?> getQueryBindings();

    public byte[] getBytesRepresentation(int var1) throws SQLException;

    public ParseInfo getParseInfo();

    public boolean isNull(int var1) throws SQLException;

    public String getPreparedSql();

    public void setBytes(int var1, byte[] var2, boolean var3, boolean var4) throws SQLException;

    public void setBytesNoEscape(int var1, byte[] var2) throws SQLException;

    public void setBytesNoEscapeNoQuotes(int var1, byte[] var2) throws SQLException;

    public void setBigInteger(int var1, BigInteger var2) throws SQLException;

    public void setNull(int var1, MysqlType var2) throws SQLException;
}

