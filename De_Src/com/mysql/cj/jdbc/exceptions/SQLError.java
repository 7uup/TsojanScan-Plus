/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.jdbc.exceptions;

import com.mysql.cj.exceptions.ExceptionInterceptor;
import com.mysql.cj.jdbc.JdbcConnection;
import com.mysql.cj.jdbc.exceptions.CommunicationsException;
import com.mysql.cj.jdbc.exceptions.MySQLQueryInterruptedException;
import com.mysql.cj.jdbc.exceptions.MySQLTransactionRollbackException;
import com.mysql.cj.protocol.PacketReceivedTimeHolder;
import com.mysql.cj.protocol.PacketSentTimeHolder;
import com.mysql.cj.util.Util;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLNonTransientConnectionException;
import java.sql.SQLSyntaxErrorException;
import java.sql.SQLTransientConnectionException;

public class SQLError {
    public static SQLException createSQLException(String message, String sqlState, ExceptionInterceptor interceptor) {
        return SQLError.createSQLException(message, sqlState, 0, interceptor);
    }

    public static SQLException createSQLException(String message, ExceptionInterceptor interceptor) {
        SQLException sqlEx = new SQLException(message);
        return SQLError.runThroughExceptionInterceptor(interceptor, sqlEx);
    }

    public static SQLException createSQLException(String message, String sqlState, Throwable cause, ExceptionInterceptor interceptor) {
        SQLException sqlEx = SQLError.createSQLException(message, sqlState, null);
        if (sqlEx.getCause() == null && cause != null) {
            try {
                sqlEx.initCause(cause);
            } catch (Throwable throwable) {
                // empty catch block
            }
        }
        return SQLError.runThroughExceptionInterceptor(interceptor, sqlEx);
    }

    public static SQLException createSQLException(String message, String sqlState, int vendorErrorCode, ExceptionInterceptor interceptor) {
        return SQLError.createSQLException(message, sqlState, vendorErrorCode, false, interceptor);
    }

    public static SQLException createSQLException(String message, String sqlState, int vendorErrorCode, Throwable cause, ExceptionInterceptor interceptor) {
        return SQLError.createSQLException(message, sqlState, vendorErrorCode, false, cause, interceptor);
    }

    public static SQLException createSQLException(String message, String sqlState, int vendorErrorCode, boolean isTransient, ExceptionInterceptor interceptor) {
        return SQLError.createSQLException(message, sqlState, vendorErrorCode, isTransient, null, interceptor);
    }

    public static SQLException createSQLException(String message, String sqlState, int vendorErrorCode, boolean isTransient, Throwable cause, ExceptionInterceptor interceptor) {
        try {
            SQLException sqlEx = null;
            sqlEx = sqlState != null ? (sqlState.startsWith("08") ? (isTransient ? new SQLTransientConnectionException(message, sqlState, vendorErrorCode) : new SQLNonTransientConnectionException(message, sqlState, vendorErrorCode)) : (sqlState.startsWith("22") ? new SQLDataException(message, sqlState, vendorErrorCode) : (sqlState.startsWith("23") ? new SQLIntegrityConstraintViolationException(message, sqlState, vendorErrorCode) : (sqlState.startsWith("42") ? new SQLSyntaxErrorException(message, sqlState, vendorErrorCode) : (sqlState.startsWith("40") ? new MySQLTransactionRollbackException(message, sqlState, vendorErrorCode) : (sqlState.startsWith("70100") ? new MySQLQueryInterruptedException(message, sqlState, vendorErrorCode) : new SQLException(message, sqlState, vendorErrorCode))))))) : new SQLException(message, sqlState, vendorErrorCode);
            if (cause != null) {
                try {
                    sqlEx.initCause(cause);
                } catch (Throwable throwable) {
                    // empty catch block
                }
            }
            return SQLError.runThroughExceptionInterceptor(interceptor, sqlEx);
        } catch (Exception sqlEx) {
            SQLException unexpectedEx = new SQLException("Unable to create correct SQLException class instance, error class/codes may be incorrect. Reason: " + Util.stackTraceToString(sqlEx), "S1000");
            return SQLError.runThroughExceptionInterceptor(interceptor, unexpectedEx);
        }
    }

    public static SQLException createCommunicationsException(JdbcConnection conn, PacketSentTimeHolder packetSentTimeHolder, PacketReceivedTimeHolder packetReceivedTimeHolder, Exception underlyingException, ExceptionInterceptor interceptor) {
        CommunicationsException exToReturn = new CommunicationsException(conn, packetSentTimeHolder, packetReceivedTimeHolder, underlyingException);
        if (underlyingException != null) {
            try {
                exToReturn.initCause(underlyingException);
            } catch (Throwable throwable) {
                // empty catch block
            }
        }
        return SQLError.runThroughExceptionInterceptor(interceptor, exToReturn);
    }

    public static SQLException createCommunicationsException(String message, Throwable underlyingException, ExceptionInterceptor interceptor) {
        CommunicationsException exToReturn = null;
        exToReturn = new CommunicationsException(message, underlyingException);
        if (underlyingException != null) {
            try {
                exToReturn.initCause(underlyingException);
            } catch (Throwable throwable) {
                // empty catch block
            }
        }
        return SQLError.runThroughExceptionInterceptor(interceptor, exToReturn);
    }

    private static SQLException runThroughExceptionInterceptor(ExceptionInterceptor exInterceptor, SQLException sqlEx) {
        SQLException interceptedEx;
        if (exInterceptor != null && (interceptedEx = (SQLException)exInterceptor.interceptException(sqlEx)) != null) {
            return interceptedEx;
        }
        return sqlEx;
    }

    public static SQLException createBatchUpdateException(SQLException underlyingEx, long[] updateCounts, ExceptionInterceptor interceptor) throws SQLException {
        SQLException newEx = (SQLException)Util.getInstance("java.sql.BatchUpdateException", new Class[]{String.class, String.class, Integer.TYPE, long[].class, Throwable.class}, new Object[]{underlyingEx.getMessage(), underlyingEx.getSQLState(), underlyingEx.getErrorCode(), updateCounts, underlyingEx}, interceptor);
        return SQLError.runThroughExceptionInterceptor(interceptor, newEx);
    }

    public static SQLException createSQLFeatureNotSupportedException() {
        return new SQLFeatureNotSupportedException();
    }

    public static SQLException createSQLFeatureNotSupportedException(String message, String sqlState, ExceptionInterceptor interceptor) throws SQLException {
        SQLFeatureNotSupportedException newEx = new SQLFeatureNotSupportedException(message, sqlState);
        return SQLError.runThroughExceptionInterceptor(interceptor, newEx);
    }
}

