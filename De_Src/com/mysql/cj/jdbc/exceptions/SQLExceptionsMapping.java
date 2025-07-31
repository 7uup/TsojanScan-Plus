/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.jdbc.exceptions;

import com.mysql.cj.exceptions.CJCommunicationsException;
import com.mysql.cj.exceptions.CJConnectionFeatureNotAvailableException;
import com.mysql.cj.exceptions.CJException;
import com.mysql.cj.exceptions.CJOperationNotSupportedException;
import com.mysql.cj.exceptions.CJPacketTooBigException;
import com.mysql.cj.exceptions.CJTimeoutException;
import com.mysql.cj.exceptions.ConnectionIsClosedException;
import com.mysql.cj.exceptions.DataConversionException;
import com.mysql.cj.exceptions.DataReadException;
import com.mysql.cj.exceptions.DataTruncationException;
import com.mysql.cj.exceptions.ExceptionInterceptor;
import com.mysql.cj.exceptions.InvalidConnectionAttributeException;
import com.mysql.cj.exceptions.NumberOutOfRange;
import com.mysql.cj.exceptions.OperationCancelledException;
import com.mysql.cj.exceptions.SSLParamsException;
import com.mysql.cj.exceptions.StatementIsClosedException;
import com.mysql.cj.exceptions.UnableToConnectException;
import com.mysql.cj.exceptions.WrongArgumentException;
import com.mysql.cj.jdbc.exceptions.ConnectionFeatureNotAvailableException;
import com.mysql.cj.jdbc.exceptions.MySQLStatementCancelledException;
import com.mysql.cj.jdbc.exceptions.MySQLTimeoutException;
import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;
import com.mysql.cj.jdbc.exceptions.OperationNotSupportedException;
import com.mysql.cj.jdbc.exceptions.PacketTooBigException;
import com.mysql.cj.jdbc.exceptions.SQLError;
import java.sql.SQLException;

public class SQLExceptionsMapping {
    public static SQLException translateException(Throwable ex, ExceptionInterceptor interceptor) {
        if (ex instanceof SQLException) {
            return (SQLException)ex;
        }
        if (ex.getCause() != null && ex.getCause() instanceof SQLException) {
            return (SQLException)ex.getCause();
        }
        if (ex instanceof CJCommunicationsException) {
            return SQLError.createCommunicationsException(ex.getMessage(), ex, interceptor);
        }
        if (ex instanceof CJConnectionFeatureNotAvailableException) {
            return new ConnectionFeatureNotAvailableException(ex.getMessage(), ex);
        }
        if (ex instanceof SSLParamsException) {
            return SQLError.createSQLException(ex.getMessage(), "08000", 0, false, ex, interceptor);
        }
        if (ex instanceof ConnectionIsClosedException) {
            return SQLError.createSQLException(ex.getMessage(), "08003", ex, interceptor);
        }
        if (ex instanceof InvalidConnectionAttributeException) {
            return SQLError.createSQLException(ex.getMessage(), "01S00", ex, interceptor);
        }
        if (ex instanceof UnableToConnectException) {
            return SQLError.createSQLException(ex.getMessage(), "08001", ex, interceptor);
        }
        if (ex instanceof StatementIsClosedException) {
            return SQLError.createSQLException(ex.getMessage(), "S1009", ex, interceptor);
        }
        if (ex instanceof WrongArgumentException) {
            return SQLError.createSQLException(ex.getMessage(), "S1009", ex, interceptor);
        }
        if (ex instanceof StringIndexOutOfBoundsException) {
            return SQLError.createSQLException(ex.getMessage(), "S1009", ex, interceptor);
        }
        if (ex instanceof NumberOutOfRange) {
            return SQLError.createSQLException(ex.getMessage(), "22003", ex, interceptor);
        }
        if (ex instanceof DataConversionException) {
            return SQLError.createSQLException(ex.getMessage(), "22018", ex, interceptor);
        }
        if (ex instanceof DataReadException) {
            return SQLError.createSQLException(ex.getMessage(), "S1009", ex, interceptor);
        }
        if (ex instanceof DataTruncationException) {
            return new MysqlDataTruncation(((DataTruncationException)ex).getMessage(), ((DataTruncationException)ex).getIndex(), ((DataTruncationException)ex).isParameter(), ((DataTruncationException)ex).isRead(), ((DataTruncationException)ex).getDataSize(), ((DataTruncationException)ex).getTransferSize(), ((DataTruncationException)ex).getVendorCode());
        }
        if (ex instanceof CJPacketTooBigException) {
            return new PacketTooBigException(ex.getMessage());
        }
        if (ex instanceof OperationCancelledException) {
            return new MySQLStatementCancelledException(ex.getMessage());
        }
        if (ex instanceof CJTimeoutException) {
            return new MySQLTimeoutException(ex.getMessage());
        }
        if (ex instanceof CJOperationNotSupportedException) {
            return new OperationNotSupportedException(ex.getMessage());
        }
        if (ex instanceof UnsupportedOperationException) {
            return new OperationNotSupportedException(ex.getMessage());
        }
        if (ex instanceof CJException) {
            return SQLError.createSQLException(ex.getMessage(), ((CJException)ex).getSQLState(), ((CJException)ex).getVendorCode(), ((CJException)ex).isTransient(), interceptor);
        }
        return SQLError.createSQLException(ex.getMessage(), "S1000", ex, interceptor);
    }

    public static SQLException translateException(Throwable ex) {
        return SQLExceptionsMapping.translateException(ex, null);
    }
}

