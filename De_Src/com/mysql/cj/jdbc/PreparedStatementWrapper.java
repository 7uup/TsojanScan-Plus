/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.jdbc;

import com.mysql.cj.Messages;
import com.mysql.cj.jdbc.ClientPreparedStatement;
import com.mysql.cj.jdbc.ConnectionWrapper;
import com.mysql.cj.jdbc.MysqlPooledConnection;
import com.mysql.cj.jdbc.StatementWrapper;
import com.mysql.cj.jdbc.WrapperBase;
import com.mysql.cj.jdbc.exceptions.SQLError;
import com.mysql.cj.jdbc.result.ResultSetInternalMethods;
import java.io.InputStream;
import java.io.Reader;
import java.lang.reflect.Proxy;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLType;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashMap;
import javax.sql.StatementEvent;

public class PreparedStatementWrapper
extends StatementWrapper
implements PreparedStatement {
    protected static PreparedStatementWrapper getInstance(ConnectionWrapper c, MysqlPooledConnection conn, PreparedStatement toWrap) throws SQLException {
        return new PreparedStatementWrapper(c, conn, toWrap);
    }

    PreparedStatementWrapper(ConnectionWrapper c, MysqlPooledConnection conn, PreparedStatement toWrap) {
        super(c, conn, toWrap);
    }

    @Override
    public void setArray(int parameterIndex, Array x) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((PreparedStatement)this.wrappedStmt).setArray(parameterIndex, x);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void setAsciiStream(int parameterIndex, InputStream x, int length) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((PreparedStatement)this.wrappedStmt).setAsciiStream(parameterIndex, x, length);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void setBigDecimal(int parameterIndex, BigDecimal x) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((PreparedStatement)this.wrappedStmt).setBigDecimal(parameterIndex, x);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void setBinaryStream(int parameterIndex, InputStream x, int length) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((PreparedStatement)this.wrappedStmt).setBinaryStream(parameterIndex, x, length);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void setBlob(int parameterIndex, Blob x) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((PreparedStatement)this.wrappedStmt).setBlob(parameterIndex, x);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void setBoolean(int parameterIndex, boolean x) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((PreparedStatement)this.wrappedStmt).setBoolean(parameterIndex, x);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void setByte(int parameterIndex, byte x) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((PreparedStatement)this.wrappedStmt).setByte(parameterIndex, x);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void setBytes(int parameterIndex, byte[] x) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((PreparedStatement)this.wrappedStmt).setBytes(parameterIndex, x);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void setCharacterStream(int parameterIndex, Reader reader, int length) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((PreparedStatement)this.wrappedStmt).setCharacterStream(parameterIndex, reader, length);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void setClob(int parameterIndex, Clob x) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((PreparedStatement)this.wrappedStmt).setClob(parameterIndex, x);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void setDate(int parameterIndex, Date x) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((PreparedStatement)this.wrappedStmt).setDate(parameterIndex, x);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void setDate(int parameterIndex, Date x, Calendar cal) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((PreparedStatement)this.wrappedStmt).setDate(parameterIndex, x, cal);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void setDouble(int parameterIndex, double x) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((PreparedStatement)this.wrappedStmt).setDouble(parameterIndex, x);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void setFloat(int parameterIndex, float x) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((PreparedStatement)this.wrappedStmt).setFloat(parameterIndex, x);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void setInt(int parameterIndex, int x) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((PreparedStatement)this.wrappedStmt).setInt(parameterIndex, x);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void setLong(int parameterIndex, long x) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((PreparedStatement)this.wrappedStmt).setLong(parameterIndex, x);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public ResultSetMetaData getMetaData() throws SQLException {
        try {
            if (this.wrappedStmt != null) {
                return ((PreparedStatement)this.wrappedStmt).getMetaData();
            }
            throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
            return null;
        }
    }

    @Override
    public void setNull(int parameterIndex, int sqlType) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((PreparedStatement)this.wrappedStmt).setNull(parameterIndex, sqlType);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void setNull(int parameterIndex, int sqlType, String typeName) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((PreparedStatement)this.wrappedStmt).setNull(parameterIndex, sqlType, typeName);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void setObject(int parameterIndex, Object x) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((PreparedStatement)this.wrappedStmt).setObject(parameterIndex, x);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void setObject(int parameterIndex, Object x, int targetSqlType) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((PreparedStatement)this.wrappedStmt).setObject(parameterIndex, x, targetSqlType);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void setObject(int parameterIndex, Object x, int targetSqlType, int scale) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((PreparedStatement)this.wrappedStmt).setObject(parameterIndex, x, targetSqlType, scale);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public ParameterMetaData getParameterMetaData() throws SQLException {
        try {
            if (this.wrappedStmt != null) {
                return ((PreparedStatement)this.wrappedStmt).getParameterMetaData();
            }
            throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
            return null;
        }
    }

    @Override
    public void setRef(int parameterIndex, Ref x) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((PreparedStatement)this.wrappedStmt).setRef(parameterIndex, x);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void setShort(int parameterIndex, short x) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((PreparedStatement)this.wrappedStmt).setShort(parameterIndex, x);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void setString(int parameterIndex, String x) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((PreparedStatement)this.wrappedStmt).setString(parameterIndex, x);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void setTime(int parameterIndex, Time x) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((PreparedStatement)this.wrappedStmt).setTime(parameterIndex, x);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void setTime(int parameterIndex, Time x, Calendar cal) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((PreparedStatement)this.wrappedStmt).setTime(parameterIndex, x, cal);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void setTimestamp(int parameterIndex, Timestamp x) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((PreparedStatement)this.wrappedStmt).setTimestamp(parameterIndex, x);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void setTimestamp(int parameterIndex, Timestamp x, Calendar cal) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((PreparedStatement)this.wrappedStmt).setTimestamp(parameterIndex, x, cal);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void setURL(int parameterIndex, URL x) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((PreparedStatement)this.wrappedStmt).setURL(parameterIndex, x);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    @Deprecated
    public void setUnicodeStream(int parameterIndex, InputStream x, int length) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((PreparedStatement)this.wrappedStmt).setUnicodeStream(parameterIndex, x, length);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void addBatch() throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((PreparedStatement)this.wrappedStmt).addBatch();
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void clearParameters() throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((PreparedStatement)this.wrappedStmt).clearParameters();
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public boolean execute() throws SQLException {
        try {
            if (this.wrappedStmt != null) {
                return ((PreparedStatement)this.wrappedStmt).execute();
            }
            throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
            return false;
        }
    }

    @Override
    public ResultSet executeQuery() throws SQLException {
        ResultSet rs = null;
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            rs = ((PreparedStatement)this.wrappedStmt).executeQuery();
            ((ResultSetInternalMethods)rs).setWrapperStatement(this);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
        return rs;
    }

    @Override
    public int executeUpdate() throws SQLException {
        try {
            if (this.wrappedStmt != null) {
                return ((PreparedStatement)this.wrappedStmt).executeUpdate();
            }
            throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
            return -1;
        }
    }

    public String toString() {
        StringBuilder buf = new StringBuilder(super.toString());
        if (this.wrappedStmt != null) {
            buf.append(": ");
            try {
                buf.append(((ClientPreparedStatement)this.wrappedStmt).asSql());
            } catch (SQLException sqlEx) {
                buf.append("EXCEPTION: " + sqlEx.toString());
            }
        }
        return buf.toString();
    }

    @Override
    public void setRowId(int parameterIndex, RowId x) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((PreparedStatement)this.wrappedStmt).setRowId(parameterIndex, x);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void setNString(int parameterIndex, String value) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((PreparedStatement)this.wrappedStmt).setNString(parameterIndex, value);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void setNCharacterStream(int parameterIndex, Reader value, long length) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((PreparedStatement)this.wrappedStmt).setNCharacterStream(parameterIndex, value, length);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void setNClob(int parameterIndex, NClob value) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((PreparedStatement)this.wrappedStmt).setNClob(parameterIndex, value);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void setClob(int parameterIndex, Reader reader, long length) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((PreparedStatement)this.wrappedStmt).setClob(parameterIndex, reader, length);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void setBlob(int parameterIndex, InputStream inputStream2, long length) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((PreparedStatement)this.wrappedStmt).setBlob(parameterIndex, inputStream2, length);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void setNClob(int parameterIndex, Reader reader, long length) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((PreparedStatement)this.wrappedStmt).setNClob(parameterIndex, reader, length);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void setSQLXML(int parameterIndex, SQLXML xmlObject) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((PreparedStatement)this.wrappedStmt).setSQLXML(parameterIndex, xmlObject);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void setAsciiStream(int parameterIndex, InputStream x, long length) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((PreparedStatement)this.wrappedStmt).setAsciiStream(parameterIndex, x, length);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void setBinaryStream(int parameterIndex, InputStream x, long length) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((PreparedStatement)this.wrappedStmt).setBinaryStream(parameterIndex, x, length);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void setCharacterStream(int parameterIndex, Reader reader, long length) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((PreparedStatement)this.wrappedStmt).setCharacterStream(parameterIndex, reader, length);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void setAsciiStream(int parameterIndex, InputStream x) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((PreparedStatement)this.wrappedStmt).setAsciiStream(parameterIndex, x);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void setBinaryStream(int parameterIndex, InputStream x) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((PreparedStatement)this.wrappedStmt).setBinaryStream(parameterIndex, x);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void setCharacterStream(int parameterIndex, Reader reader) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((PreparedStatement)this.wrappedStmt).setCharacterStream(parameterIndex, reader);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void setNCharacterStream(int parameterIndex, Reader value) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((PreparedStatement)this.wrappedStmt).setNCharacterStream(parameterIndex, value);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void setClob(int parameterIndex, Reader reader) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((PreparedStatement)this.wrappedStmt).setClob(parameterIndex, reader);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void setBlob(int parameterIndex, InputStream inputStream2) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((PreparedStatement)this.wrappedStmt).setBlob(parameterIndex, inputStream2);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void setNClob(int parameterIndex, Reader reader) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((PreparedStatement)this.wrappedStmt).setNClob(parameterIndex, reader);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        boolean isInstance = iface.isInstance(this);
        if (isInstance) {
            return true;
        }
        String interfaceClassName = iface.getName();
        return interfaceClassName.equals("com.mysql.cj.jdbc.Statement") || interfaceClassName.equals("java.sql.Statement") || interfaceClassName.equals("java.sql.Wrapper") || interfaceClassName.equals("java.sql.PreparedStatement");
    }

    @Override
    public synchronized <T> T unwrap(Class<T> iface) throws SQLException {
        try {
            Object cachedUnwrapped;
            if ("java.sql.Statement".equals(iface.getName()) || "java.sql.PreparedStatement".equals(iface.getName()) || "java.sql.Wrapper.class".equals(iface.getName())) {
                return iface.cast(this);
            }
            if (this.unwrappedInterfaces == null) {
                this.unwrappedInterfaces = new HashMap();
            }
            if ((cachedUnwrapped = this.unwrappedInterfaces.get(iface)) == null) {
                cachedUnwrapped = Proxy.newProxyInstance(this.wrappedStmt.getClass().getClassLoader(), new Class[]{iface}, new WrapperBase.ConnectionErrorFiringInvocationHandler(this, this.wrappedStmt));
                this.unwrappedInterfaces.put(iface, cachedUnwrapped);
            }
            return iface.cast(cachedUnwrapped);
        } catch (ClassCastException cce) {
            throw SQLError.createSQLException(Messages.getString("Common.UnableToUnwrap", new Object[]{iface.toString()}), "S1009", this.exceptionInterceptor);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public synchronized void close() throws SQLException {
        if (this.pooledConnection == null) {
            return;
        }
        MysqlPooledConnection con = this.pooledConnection;
        try {
            super.close();
        } finally {
            try {
                StatementEvent e = new StatementEvent(con, this);
                con.fireStatementEvent(e);
            } finally {
                this.unwrappedInterfaces = null;
            }
        }
    }

    @Override
    public long executeLargeUpdate() throws SQLException {
        try {
            if (this.wrappedStmt != null) {
                return ((ClientPreparedStatement)this.wrappedStmt).executeLargeUpdate();
            }
            throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1009", this.exceptionInterceptor);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
            return -1L;
        }
    }

    @Override
    public void setObject(int parameterIndex, Object x, SQLType targetSqlType) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException("No operations allowed after statement closed", "S1000", this.exceptionInterceptor);
            }
            ((PreparedStatement)this.wrappedStmt).setObject(parameterIndex, x, targetSqlType);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void setObject(int parameterIndex, Object x, SQLType targetSqlType, int scaleOrLength) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException("No operations allowed after statement closed", "S1000", this.exceptionInterceptor);
            }
            ((PreparedStatement)this.wrappedStmt).setObject(parameterIndex, x, targetSqlType, scaleOrLength);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }
}

