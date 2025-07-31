/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.jdbc;

import com.mysql.cj.Messages;
import com.mysql.cj.jdbc.ConnectionWrapper;
import com.mysql.cj.jdbc.MysqlPooledConnection;
import com.mysql.cj.jdbc.PreparedStatementWrapper;
import com.mysql.cj.jdbc.WrapperBase;
import com.mysql.cj.jdbc.exceptions.SQLError;
import java.io.InputStream;
import java.io.Reader;
import java.lang.reflect.Proxy;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.Ref;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLType;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class CallableStatementWrapper
extends PreparedStatementWrapper
implements CallableStatement {
    protected static CallableStatementWrapper getInstance(ConnectionWrapper c, MysqlPooledConnection conn, CallableStatement toWrap) throws SQLException {
        return new CallableStatementWrapper(c, conn, toWrap);
    }

    public CallableStatementWrapper(ConnectionWrapper c, MysqlPooledConnection conn, CallableStatement toWrap) {
        super(c, conn, toWrap);
    }

    @Override
    public void registerOutParameter(int parameterIndex, int sqlType) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((CallableStatement)this.wrappedStmt).registerOutParameter(parameterIndex, sqlType);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void registerOutParameter(int parameterIndex, int sqlType, int scale) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((CallableStatement)this.wrappedStmt).registerOutParameter(parameterIndex, sqlType, scale);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public boolean wasNull() throws SQLException {
        try {
            if (this.wrappedStmt != null) {
                return ((CallableStatement)this.wrappedStmt).wasNull();
            }
            throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
            return false;
        }
    }

    @Override
    public String getString(int parameterIndex) throws SQLException {
        try {
            if (this.wrappedStmt != null) {
                return ((CallableStatement)this.wrappedStmt).getString(parameterIndex);
            }
            throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
            return null;
        }
    }

    @Override
    public boolean getBoolean(int parameterIndex) throws SQLException {
        try {
            if (this.wrappedStmt != null) {
                return ((CallableStatement)this.wrappedStmt).getBoolean(parameterIndex);
            }
            throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
            return false;
        }
    }

    @Override
    public byte getByte(int parameterIndex) throws SQLException {
        try {
            if (this.wrappedStmt != null) {
                return ((CallableStatement)this.wrappedStmt).getByte(parameterIndex);
            }
            throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
            return 0;
        }
    }

    @Override
    public short getShort(int parameterIndex) throws SQLException {
        try {
            if (this.wrappedStmt != null) {
                return ((CallableStatement)this.wrappedStmt).getShort(parameterIndex);
            }
            throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
            return 0;
        }
    }

    @Override
    public int getInt(int parameterIndex) throws SQLException {
        try {
            if (this.wrappedStmt != null) {
                return ((CallableStatement)this.wrappedStmt).getInt(parameterIndex);
            }
            throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
            return 0;
        }
    }

    @Override
    public long getLong(int parameterIndex) throws SQLException {
        try {
            if (this.wrappedStmt != null) {
                return ((CallableStatement)this.wrappedStmt).getLong(parameterIndex);
            }
            throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
            return 0L;
        }
    }

    @Override
    public float getFloat(int parameterIndex) throws SQLException {
        try {
            if (this.wrappedStmt != null) {
                return ((CallableStatement)this.wrappedStmt).getFloat(parameterIndex);
            }
            throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
            return 0.0f;
        }
    }

    @Override
    public double getDouble(int parameterIndex) throws SQLException {
        try {
            if (this.wrappedStmt != null) {
                return ((CallableStatement)this.wrappedStmt).getDouble(parameterIndex);
            }
            throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
            return 0.0;
        }
    }

    @Override
    @Deprecated
    public BigDecimal getBigDecimal(int parameterIndex, int scale) throws SQLException {
        try {
            if (this.wrappedStmt != null) {
                return ((CallableStatement)this.wrappedStmt).getBigDecimal(parameterIndex, scale);
            }
            throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
            return null;
        }
    }

    @Override
    public byte[] getBytes(int parameterIndex) throws SQLException {
        try {
            if (this.wrappedStmt != null) {
                return ((CallableStatement)this.wrappedStmt).getBytes(parameterIndex);
            }
            throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
            return null;
        }
    }

    @Override
    public Date getDate(int parameterIndex) throws SQLException {
        try {
            if (this.wrappedStmt != null) {
                return ((CallableStatement)this.wrappedStmt).getDate(parameterIndex);
            }
            throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
            return null;
        }
    }

    @Override
    public Time getTime(int parameterIndex) throws SQLException {
        try {
            if (this.wrappedStmt != null) {
                return ((CallableStatement)this.wrappedStmt).getTime(parameterIndex);
            }
            throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
            return null;
        }
    }

    @Override
    public Timestamp getTimestamp(int parameterIndex) throws SQLException {
        try {
            if (this.wrappedStmt != null) {
                return ((CallableStatement)this.wrappedStmt).getTimestamp(parameterIndex);
            }
            throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
            return null;
        }
    }

    @Override
    public Object getObject(int parameterIndex) throws SQLException {
        try {
            if (this.wrappedStmt != null) {
                return ((CallableStatement)this.wrappedStmt).getObject(parameterIndex);
            }
            throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
            return null;
        }
    }

    @Override
    public BigDecimal getBigDecimal(int parameterIndex) throws SQLException {
        try {
            if (this.wrappedStmt != null) {
                return ((CallableStatement)this.wrappedStmt).getBigDecimal(parameterIndex);
            }
            throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
            return null;
        }
    }

    @Override
    public Object getObject(int parameterIndex, Map<String, Class<?>> typeMap) throws SQLException {
        try {
            if (this.wrappedStmt != null) {
                return ((CallableStatement)this.wrappedStmt).getObject(parameterIndex, typeMap);
            }
            throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
            return null;
        }
    }

    @Override
    public Ref getRef(int parameterIndex) throws SQLException {
        try {
            if (this.wrappedStmt != null) {
                return ((CallableStatement)this.wrappedStmt).getRef(parameterIndex);
            }
            throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
            return null;
        }
    }

    @Override
    public Blob getBlob(int parameterIndex) throws SQLException {
        try {
            if (this.wrappedStmt != null) {
                return ((CallableStatement)this.wrappedStmt).getBlob(parameterIndex);
            }
            throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
            return null;
        }
    }

    @Override
    public Clob getClob(int parameterIndex) throws SQLException {
        try {
            if (this.wrappedStmt != null) {
                return ((CallableStatement)this.wrappedStmt).getClob(parameterIndex);
            }
            throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
            return null;
        }
    }

    @Override
    public Array getArray(int parameterIndex) throws SQLException {
        try {
            if (this.wrappedStmt != null) {
                return ((CallableStatement)this.wrappedStmt).getArray(parameterIndex);
            }
            throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
            return null;
        }
    }

    @Override
    public Date getDate(int parameterIndex, Calendar cal) throws SQLException {
        try {
            if (this.wrappedStmt != null) {
                return ((CallableStatement)this.wrappedStmt).getDate(parameterIndex, cal);
            }
            throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
            return null;
        }
    }

    @Override
    public Time getTime(int parameterIndex, Calendar cal) throws SQLException {
        try {
            if (this.wrappedStmt != null) {
                return ((CallableStatement)this.wrappedStmt).getTime(parameterIndex, cal);
            }
            throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
            return null;
        }
    }

    @Override
    public Timestamp getTimestamp(int parameterIndex, Calendar cal) throws SQLException {
        try {
            if (this.wrappedStmt != null) {
                return ((CallableStatement)this.wrappedStmt).getTimestamp(parameterIndex, cal);
            }
            throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
            return null;
        }
    }

    @Override
    public void registerOutParameter(int paramIndex, int sqlType, String typeName) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((CallableStatement)this.wrappedStmt).registerOutParameter(paramIndex, sqlType, typeName);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void registerOutParameter(String parameterName, int sqlType) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((CallableStatement)this.wrappedStmt).registerOutParameter(parameterName, sqlType);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void registerOutParameter(String parameterName, int sqlType, int scale) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((CallableStatement)this.wrappedStmt).registerOutParameter(parameterName, sqlType, scale);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void registerOutParameter(String parameterName, int sqlType, String typeName) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((CallableStatement)this.wrappedStmt).registerOutParameter(parameterName, sqlType, typeName);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public URL getURL(int parameterIndex) throws SQLException {
        try {
            if (this.wrappedStmt != null) {
                return ((CallableStatement)this.wrappedStmt).getURL(parameterIndex);
            }
            throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
            return null;
        }
    }

    @Override
    public void setURL(String parameterName, URL val) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((CallableStatement)this.wrappedStmt).setURL(parameterName, val);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void setNull(String parameterName, int sqlType) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((CallableStatement)this.wrappedStmt).setNull(parameterName, sqlType);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void setBoolean(String parameterName, boolean x) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((CallableStatement)this.wrappedStmt).setBoolean(parameterName, x);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void setByte(String parameterName, byte x) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((CallableStatement)this.wrappedStmt).setByte(parameterName, x);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void setShort(String parameterName, short x) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((CallableStatement)this.wrappedStmt).setShort(parameterName, x);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void setInt(String parameterName, int x) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((CallableStatement)this.wrappedStmt).setInt(parameterName, x);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void setLong(String parameterName, long x) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((CallableStatement)this.wrappedStmt).setLong(parameterName, x);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void setFloat(String parameterName, float x) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((CallableStatement)this.wrappedStmt).setFloat(parameterName, x);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void setDouble(String parameterName, double x) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((CallableStatement)this.wrappedStmt).setDouble(parameterName, x);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void setBigDecimal(String parameterName, BigDecimal x) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((CallableStatement)this.wrappedStmt).setBigDecimal(parameterName, x);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void setString(String parameterName, String x) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((CallableStatement)this.wrappedStmt).setString(parameterName, x);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void setBytes(String parameterName, byte[] x) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((CallableStatement)this.wrappedStmt).setBytes(parameterName, x);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void setDate(String parameterName, Date x) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((CallableStatement)this.wrappedStmt).setDate(parameterName, x);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void setTime(String parameterName, Time x) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((CallableStatement)this.wrappedStmt).setTime(parameterName, x);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void setTimestamp(String parameterName, Timestamp x) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((CallableStatement)this.wrappedStmt).setTimestamp(parameterName, x);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void setAsciiStream(String parameterName, InputStream x, int length) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((CallableStatement)this.wrappedStmt).setAsciiStream(parameterName, x, length);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void setBinaryStream(String parameterName, InputStream x, int length) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((CallableStatement)this.wrappedStmt).setBinaryStream(parameterName, x, length);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void setObject(String parameterName, Object x, int targetSqlType, int scale) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((CallableStatement)this.wrappedStmt).setObject(parameterName, x, targetSqlType, scale);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void setObject(String parameterName, Object x, int targetSqlType) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((CallableStatement)this.wrappedStmt).setObject(parameterName, x, targetSqlType);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void setObject(String parameterName, Object x) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((CallableStatement)this.wrappedStmt).setObject(parameterName, x);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void setCharacterStream(String parameterName, Reader reader, int length) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((CallableStatement)this.wrappedStmt).setCharacterStream(parameterName, reader, length);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void setDate(String parameterName, Date x, Calendar cal) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((CallableStatement)this.wrappedStmt).setDate(parameterName, x, cal);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void setTime(String parameterName, Time x, Calendar cal) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((CallableStatement)this.wrappedStmt).setTime(parameterName, x, cal);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void setTimestamp(String parameterName, Timestamp x, Calendar cal) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((CallableStatement)this.wrappedStmt).setTimestamp(parameterName, x, cal);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void setNull(String parameterName, int sqlType, String typeName) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((CallableStatement)this.wrappedStmt).setNull(parameterName, sqlType, typeName);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public String getString(String parameterName) throws SQLException {
        try {
            if (this.wrappedStmt != null) {
                return ((CallableStatement)this.wrappedStmt).getString(parameterName);
            }
            throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
            return null;
        }
    }

    @Override
    public boolean getBoolean(String parameterName) throws SQLException {
        try {
            if (this.wrappedStmt != null) {
                return ((CallableStatement)this.wrappedStmt).getBoolean(parameterName);
            }
            throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
            return false;
        }
    }

    @Override
    public byte getByte(String parameterName) throws SQLException {
        try {
            if (this.wrappedStmt != null) {
                return ((CallableStatement)this.wrappedStmt).getByte(parameterName);
            }
            throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
            return 0;
        }
    }

    @Override
    public short getShort(String parameterName) throws SQLException {
        try {
            if (this.wrappedStmt != null) {
                return ((CallableStatement)this.wrappedStmt).getShort(parameterName);
            }
            throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
            return 0;
        }
    }

    @Override
    public int getInt(String parameterName) throws SQLException {
        try {
            if (this.wrappedStmt != null) {
                return ((CallableStatement)this.wrappedStmt).getInt(parameterName);
            }
            throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
            return 0;
        }
    }

    @Override
    public long getLong(String parameterName) throws SQLException {
        try {
            if (this.wrappedStmt != null) {
                return ((CallableStatement)this.wrappedStmt).getLong(parameterName);
            }
            throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
            return 0L;
        }
    }

    @Override
    public float getFloat(String parameterName) throws SQLException {
        try {
            if (this.wrappedStmt != null) {
                return ((CallableStatement)this.wrappedStmt).getFloat(parameterName);
            }
            throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
            return 0.0f;
        }
    }

    @Override
    public double getDouble(String parameterName) throws SQLException {
        try {
            if (this.wrappedStmt != null) {
                return ((CallableStatement)this.wrappedStmt).getDouble(parameterName);
            }
            throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
            return 0.0;
        }
    }

    @Override
    public byte[] getBytes(String parameterName) throws SQLException {
        try {
            if (this.wrappedStmt != null) {
                return ((CallableStatement)this.wrappedStmt).getBytes(parameterName);
            }
            throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
            return null;
        }
    }

    @Override
    public Date getDate(String parameterName) throws SQLException {
        try {
            if (this.wrappedStmt != null) {
                return ((CallableStatement)this.wrappedStmt).getDate(parameterName);
            }
            throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
            return null;
        }
    }

    @Override
    public Time getTime(String parameterName) throws SQLException {
        try {
            if (this.wrappedStmt != null) {
                return ((CallableStatement)this.wrappedStmt).getTime(parameterName);
            }
            throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
            return null;
        }
    }

    @Override
    public Timestamp getTimestamp(String parameterName) throws SQLException {
        try {
            if (this.wrappedStmt != null) {
                return ((CallableStatement)this.wrappedStmt).getTimestamp(parameterName);
            }
            throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
            return null;
        }
    }

    @Override
    public Object getObject(String parameterName) throws SQLException {
        try {
            if (this.wrappedStmt != null) {
                return ((CallableStatement)this.wrappedStmt).getObject(parameterName);
            }
            throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
            return null;
        }
    }

    @Override
    public BigDecimal getBigDecimal(String parameterName) throws SQLException {
        try {
            if (this.wrappedStmt != null) {
                return ((CallableStatement)this.wrappedStmt).getBigDecimal(parameterName);
            }
            throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
            return null;
        }
    }

    @Override
    public Object getObject(String parameterName, Map<String, Class<?>> typeMap) throws SQLException {
        try {
            if (this.wrappedStmt != null) {
                return ((CallableStatement)this.wrappedStmt).getObject(parameterName, typeMap);
            }
            throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
            return null;
        }
    }

    @Override
    public Ref getRef(String parameterName) throws SQLException {
        try {
            if (this.wrappedStmt != null) {
                return ((CallableStatement)this.wrappedStmt).getRef(parameterName);
            }
            throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
            return null;
        }
    }

    @Override
    public Blob getBlob(String parameterName) throws SQLException {
        try {
            if (this.wrappedStmt != null) {
                return ((CallableStatement)this.wrappedStmt).getBlob(parameterName);
            }
            throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
            return null;
        }
    }

    @Override
    public Clob getClob(String parameterName) throws SQLException {
        try {
            if (this.wrappedStmt != null) {
                return ((CallableStatement)this.wrappedStmt).getClob(parameterName);
            }
            throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
            return null;
        }
    }

    @Override
    public Array getArray(String parameterName) throws SQLException {
        try {
            if (this.wrappedStmt != null) {
                return ((CallableStatement)this.wrappedStmt).getArray(parameterName);
            }
            throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
            return null;
        }
    }

    @Override
    public Date getDate(String parameterName, Calendar cal) throws SQLException {
        try {
            if (this.wrappedStmt != null) {
                return ((CallableStatement)this.wrappedStmt).getDate(parameterName, cal);
            }
            throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
            return null;
        }
    }

    @Override
    public Time getTime(String parameterName, Calendar cal) throws SQLException {
        try {
            if (this.wrappedStmt != null) {
                return ((CallableStatement)this.wrappedStmt).getTime(parameterName, cal);
            }
            throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
            return null;
        }
    }

    @Override
    public Timestamp getTimestamp(String parameterName, Calendar cal) throws SQLException {
        try {
            if (this.wrappedStmt != null) {
                return ((CallableStatement)this.wrappedStmt).getTimestamp(parameterName, cal);
            }
            throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
            return null;
        }
    }

    @Override
    public URL getURL(String parameterName) throws SQLException {
        try {
            if (this.wrappedStmt != null) {
                return ((CallableStatement)this.wrappedStmt).getURL(parameterName);
            }
            throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
            return null;
        }
    }

    @Override
    public RowId getRowId(String parameterName) throws SQLException {
        try {
            if (this.wrappedStmt != null) {
                return ((CallableStatement)this.wrappedStmt).getRowId(parameterName);
            }
            throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
            return null;
        }
    }

    @Override
    public RowId getRowId(int parameterIndex) throws SQLException {
        try {
            if (this.wrappedStmt != null) {
                return ((CallableStatement)this.wrappedStmt).getRowId(parameterIndex);
            }
            throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
            return null;
        }
    }

    @Override
    public void setRowId(String parameterName, RowId x) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((CallableStatement)this.wrappedStmt).setRowId(parameterName, x);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void setNString(String parameterName, String value) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((CallableStatement)this.wrappedStmt).setNString(parameterName, value);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void setNCharacterStream(String parameterName, Reader reader, long length) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((CallableStatement)this.wrappedStmt).setNCharacterStream(parameterName, reader, length);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void setNClob(String parameterName, NClob value) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((CallableStatement)this.wrappedStmt).setNClob(parameterName, value);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void setClob(String parameterName, Reader reader, long length) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((CallableStatement)this.wrappedStmt).setClob(parameterName, reader, length);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void setBlob(String parameterName, InputStream x, long length) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((CallableStatement)this.wrappedStmt).setBlob(parameterName, x, length);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void setNClob(String parameterName, Reader reader, long length) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((CallableStatement)this.wrappedStmt).setNClob(parameterName, reader, length);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public NClob getNClob(String parameterName) throws SQLException {
        try {
            if (this.wrappedStmt != null) {
                return ((CallableStatement)this.wrappedStmt).getNClob(parameterName);
            }
            throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
            return null;
        }
    }

    @Override
    public NClob getNClob(int parameterIndex) throws SQLException {
        try {
            if (this.wrappedStmt != null) {
                return ((CallableStatement)this.wrappedStmt).getNClob(parameterIndex);
            }
            throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
            return null;
        }
    }

    @Override
    public void setSQLXML(String parameterName, SQLXML xmlObject) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((CallableStatement)this.wrappedStmt).setSQLXML(parameterName, xmlObject);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public SQLXML getSQLXML(int parameterIndex) throws SQLException {
        try {
            if (this.wrappedStmt != null) {
                return ((CallableStatement)this.wrappedStmt).getSQLXML(parameterIndex);
            }
            throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
            return null;
        }
    }

    @Override
    public SQLXML getSQLXML(String parameterName) throws SQLException {
        try {
            if (this.wrappedStmt != null) {
                return ((CallableStatement)this.wrappedStmt).getSQLXML(parameterName);
            }
            throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
            return null;
        }
    }

    @Override
    public String getNString(int parameterIndex) throws SQLException {
        try {
            if (this.wrappedStmt != null) {
                return ((CallableStatement)this.wrappedStmt).getNString(parameterIndex);
            }
            throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
            return null;
        }
    }

    @Override
    public String getNString(String parameterName) throws SQLException {
        try {
            if (this.wrappedStmt != null) {
                return ((CallableStatement)this.wrappedStmt).getNString(parameterName);
            }
            throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
            return null;
        }
    }

    @Override
    public Reader getNCharacterStream(int parameterIndex) throws SQLException {
        try {
            if (this.wrappedStmt != null) {
                return ((CallableStatement)this.wrappedStmt).getNCharacterStream(parameterIndex);
            }
            throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
            return null;
        }
    }

    @Override
    public Reader getNCharacterStream(String parameterName) throws SQLException {
        try {
            if (this.wrappedStmt != null) {
                return ((CallableStatement)this.wrappedStmt).getNCharacterStream(parameterName);
            }
            throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
            return null;
        }
    }

    @Override
    public Reader getCharacterStream(int parameterIndex) throws SQLException {
        try {
            if (this.wrappedStmt != null) {
                return ((CallableStatement)this.wrappedStmt).getCharacterStream(parameterIndex);
            }
            throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
            return null;
        }
    }

    @Override
    public Reader getCharacterStream(String parameterName) throws SQLException {
        try {
            if (this.wrappedStmt != null) {
                return ((CallableStatement)this.wrappedStmt).getCharacterStream(parameterName);
            }
            throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
            return null;
        }
    }

    @Override
    public void setBlob(String parameterName, Blob x) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((CallableStatement)this.wrappedStmt).setBlob(parameterName, x);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void setClob(String parameterName, Clob x) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((CallableStatement)this.wrappedStmt).setClob(parameterName, x);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void setAsciiStream(String parameterName, InputStream x, long length) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((CallableStatement)this.wrappedStmt).setAsciiStream(parameterName, x, length);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void setBinaryStream(String parameterName, InputStream x, long length) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((CallableStatement)this.wrappedStmt).setBinaryStream(parameterName, x, length);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void setCharacterStream(String parameterName, Reader reader, long length) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((CallableStatement)this.wrappedStmt).setCharacterStream(parameterName, reader, length);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void setAsciiStream(String parameterName, InputStream x) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((CallableStatement)this.wrappedStmt).setAsciiStream(parameterName, x);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void setBinaryStream(String parameterName, InputStream x) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((CallableStatement)this.wrappedStmt).setBinaryStream(parameterName, x);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void setCharacterStream(String parameterName, Reader reader) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((CallableStatement)this.wrappedStmt).setCharacterStream(parameterName, reader);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void setNCharacterStream(String parameterName, Reader reader) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((CallableStatement)this.wrappedStmt).setNCharacterStream(parameterName, reader);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void setClob(String parameterName, Reader reader) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((CallableStatement)this.wrappedStmt).setClob(parameterName, reader);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void setBlob(String parameterName, InputStream x) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((CallableStatement)this.wrappedStmt).setBlob(parameterName, x);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void setNClob(String parameterName, Reader reader) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((CallableStatement)this.wrappedStmt).setNClob(parameterName, reader);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public <T> T getObject(int parameterIndex, Class<T> type) throws SQLException {
        if (this.wrappedStmt != null) {
            return null;
        }
        throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
    }

    @Override
    public <T> T getObject(String parameterName, Class<T> type) throws SQLException {
        if (this.wrappedStmt != null) {
            return null;
        }
        throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        boolean isInstance = iface.isInstance(this);
        if (isInstance) {
            return true;
        }
        String interfaceClassName = iface.getName();
        return interfaceClassName.equals("com.mysql.cj.jdbc.Statement") || interfaceClassName.equals("java.sql.Statement") || interfaceClassName.equals("java.sql.Wrapper") || interfaceClassName.equals("java.sql.PreparedStatement") || interfaceClassName.equals("java.sql.CallableStatement");
    }

    @Override
    public void close() throws SQLException {
        try {
            super.close();
        } finally {
            this.unwrappedInterfaces = null;
        }
    }

    @Override
    public synchronized <T> T unwrap(Class<T> iface) throws SQLException {
        try {
            Object cachedUnwrapped;
            if ("java.sql.Statement".equals(iface.getName()) || "java.sql.CallableStatement".equals(iface.getName()) || "java.sql.PreparedStatement".equals(iface.getName()) || "java.sql.Wrapper.class".equals(iface.getName())) {
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

    @Override
    public void registerOutParameter(int parameterIndex, SQLType sqlType) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((CallableStatement)this.wrappedStmt).registerOutParameter(parameterIndex, sqlType);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void registerOutParameter(int parameterIndex, SQLType sqlType, int scale) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((CallableStatement)this.wrappedStmt).registerOutParameter(parameterIndex, sqlType, scale);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void registerOutParameter(int parameterIndex, SQLType sqlType, String typeName) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((CallableStatement)this.wrappedStmt).registerOutParameter(parameterIndex, sqlType, typeName);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void registerOutParameter(String parameterName, SQLType sqlType) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((CallableStatement)this.wrappedStmt).registerOutParameter(parameterName, sqlType);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void registerOutParameter(String parameterName, SQLType sqlType, int scale) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((CallableStatement)this.wrappedStmt).registerOutParameter(parameterName, sqlType, scale);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void registerOutParameter(String parameterName, SQLType sqlType, String typeName) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((CallableStatement)this.wrappedStmt).registerOutParameter(parameterName, sqlType, typeName);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void setObject(int parameterIndex, Object x, SQLType targetSqlType) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((CallableStatement)this.wrappedStmt).setObject(parameterIndex, x, targetSqlType);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void setObject(int parameterIndex, Object x, SQLType targetSqlType, int scaleOrLength) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((CallableStatement)this.wrappedStmt).setObject(parameterIndex, x, targetSqlType, scaleOrLength);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void setObject(String parameterName, Object x, SQLType targetSqlType) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((CallableStatement)this.wrappedStmt).setObject(parameterName, x, targetSqlType);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }

    @Override
    public void setObject(String parameterName, Object x, SQLType targetSqlType, int scaleOrLength) throws SQLException {
        try {
            if (this.wrappedStmt == null) {
                throw SQLError.createSQLException(Messages.getString("Statement.AlreadyClosed"), "S1000", this.exceptionInterceptor);
            }
            ((CallableStatement)this.wrappedStmt).setObject(parameterName, x, targetSqlType, scaleOrLength);
        } catch (SQLException sqlEx) {
            this.checkAndFireConnectionError(sqlEx);
        }
    }
}

