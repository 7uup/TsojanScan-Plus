/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.jdbc;

import com.mysql.cj.Messages;
import com.mysql.cj.exceptions.CJException;
import com.mysql.cj.exceptions.ExceptionInterceptor;
import com.mysql.cj.jdbc.exceptions.SQLError;
import com.mysql.cj.jdbc.exceptions.SQLExceptionsMapping;
import com.mysql.cj.jdbc.result.ResultSetImpl;
import com.mysql.cj.result.Field;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BlobFromLocator
implements Blob {
    private List<String> primaryKeyColumns = null;
    private List<String> primaryKeyValues = null;
    private ResultSetImpl creatorResultSet;
    private String blobColumnName = null;
    private String tableName = null;
    private int numColsInResultSet = 0;
    private int numPrimaryKeys = 0;
    private String quotedId;
    private ExceptionInterceptor exceptionInterceptor;

    public BlobFromLocator(ResultSetImpl creatorResultSetToSet, int blobColumnIndex, ExceptionInterceptor exceptionInterceptor) throws SQLException {
        this.exceptionInterceptor = exceptionInterceptor;
        this.creatorResultSet = creatorResultSetToSet;
        Field[] fields = this.creatorResultSet.getMetadata().getFields();
        this.numColsInResultSet = fields.length;
        this.quotedId = this.creatorResultSet.getSession().getIdentifierQuoteString();
        if (this.numColsInResultSet > 1) {
            this.primaryKeyColumns = new ArrayList<String>();
            this.primaryKeyValues = new ArrayList<String>();
            for (int i = 0; i < this.numColsInResultSet; ++i) {
                if (!fields[i].isPrimaryKey()) continue;
                StringBuilder keyName = new StringBuilder();
                keyName.append(this.quotedId);
                String originalColumnName = fields[i].getOriginalName();
                if (originalColumnName != null && originalColumnName.length() > 0) {
                    keyName.append(originalColumnName);
                } else {
                    keyName.append(fields[i].getName());
                }
                keyName.append(this.quotedId);
                this.primaryKeyColumns.add(keyName.toString());
                this.primaryKeyValues.add(this.creatorResultSet.getString(i + 1));
            }
        } else {
            this.notEnoughInformationInQuery();
        }
        this.numPrimaryKeys = this.primaryKeyColumns.size();
        if (this.numPrimaryKeys == 0) {
            this.notEnoughInformationInQuery();
        }
        if (fields[0].getOriginalTableName() != null) {
            StringBuilder tableNameBuffer = new StringBuilder();
            String databaseName = fields[0].getDatabaseName();
            if (databaseName != null && databaseName.length() > 0) {
                tableNameBuffer.append(this.quotedId);
                tableNameBuffer.append(databaseName);
                tableNameBuffer.append(this.quotedId);
                tableNameBuffer.append('.');
            }
            tableNameBuffer.append(this.quotedId);
            tableNameBuffer.append(fields[0].getOriginalTableName());
            tableNameBuffer.append(this.quotedId);
            this.tableName = tableNameBuffer.toString();
        } else {
            StringBuilder tableNameBuffer = new StringBuilder();
            tableNameBuffer.append(this.quotedId);
            tableNameBuffer.append(fields[0].getTableName());
            tableNameBuffer.append(this.quotedId);
            this.tableName = tableNameBuffer.toString();
        }
        this.blobColumnName = this.quotedId + this.creatorResultSet.getString(blobColumnIndex) + this.quotedId;
    }

    private void notEnoughInformationInQuery() throws SQLException {
        throw SQLError.createSQLException(Messages.getString("Blob.8"), "S1000", this.exceptionInterceptor);
    }

    @Override
    public OutputStream setBinaryStream(long indexToWriteAt) throws SQLException {
        try {
            throw SQLError.createSQLFeatureNotSupportedException();
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public InputStream getBinaryStream() throws SQLException {
        try {
            return new BufferedInputStream(new LocatorInputStream(), this.creatorResultSet.getSession().getPropertySet().getMemorySizeProperty("locatorFetchBufferSize").getValue());
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int setBytes(long writeAt, byte[] bytes, int offset, int length) throws SQLException {
        try {
            int i;
            PreparedStatement pStmt = null;
            if (offset + length > bytes.length) {
                length = bytes.length - offset;
            }
            byte[] bytesToWrite = new byte[length];
            System.arraycopy(bytes, offset, bytesToWrite, 0, length);
            StringBuilder query = new StringBuilder("UPDATE ");
            query.append(this.tableName);
            query.append(" SET ");
            query.append(this.blobColumnName);
            query.append(" = INSERT(");
            query.append(this.blobColumnName);
            query.append(", ");
            query.append(writeAt);
            query.append(", ");
            query.append(length);
            query.append(", ?) WHERE ");
            query.append(this.primaryKeyColumns.get(0));
            query.append(" = ?");
            for (i = 1; i < this.numPrimaryKeys; ++i) {
                query.append(" AND ");
                query.append(this.primaryKeyColumns.get(i));
                query.append(" = ?");
            }
            try {
                pStmt = this.creatorResultSet.getConnection().prepareStatement(query.toString());
                pStmt.setBytes(1, bytesToWrite);
                for (i = 0; i < this.numPrimaryKeys; ++i) {
                    pStmt.setString(i + 2, this.primaryKeyValues.get(i));
                }
                int rowsUpdated = pStmt.executeUpdate();
                if (rowsUpdated != 1) {
                    throw SQLError.createSQLException(Messages.getString("Blob.9"), "S1000", this.exceptionInterceptor);
                }
            } finally {
                if (pStmt != null) {
                    try {
                        pStmt.close();
                    } catch (SQLException sQLException) {}
                    pStmt = null;
                }
            }
            return (int)this.length();
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public int setBytes(long writeAt, byte[] bytes) throws SQLException {
        try {
            return this.setBytes(writeAt, bytes, 0, bytes.length);
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public byte[] getBytes(long pos, int length) throws SQLException {
        try {
            PreparedStatement pStmt = null;
            try {
                pStmt = this.createGetBytesStatement();
                byte[] byArray = this.getBytesInternal(pStmt, pos, length);
                return byArray;
            } finally {
                if (pStmt != null) {
                    try {
                        pStmt.close();
                    } catch (SQLException sQLException) {}
                    pStmt = null;
                }
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public long length() throws SQLException {
        try {
            int i;
            ResultSet blobRs = null;
            PreparedStatement pStmt = null;
            StringBuilder query = new StringBuilder("SELECT LENGTH(");
            query.append(this.blobColumnName);
            query.append(") FROM ");
            query.append(this.tableName);
            query.append(" WHERE ");
            query.append(this.primaryKeyColumns.get(0));
            query.append(" = ?");
            for (i = 1; i < this.numPrimaryKeys; ++i) {
                query.append(" AND ");
                query.append(this.primaryKeyColumns.get(i));
                query.append(" = ?");
            }
            try {
                pStmt = this.creatorResultSet.getConnection().prepareStatement(query.toString());
                for (i = 0; i < this.numPrimaryKeys; ++i) {
                    pStmt.setString(i + 1, this.primaryKeyValues.get(i));
                }
                blobRs = pStmt.executeQuery();
                if (blobRs.next()) {
                    long l = blobRs.getLong(1);
                    return l;
                }
                throw SQLError.createSQLException(Messages.getString("Blob.9"), "S1000", this.exceptionInterceptor);
            } finally {
                if (blobRs != null) {
                    try {
                        blobRs.close();
                    } catch (SQLException sQLException) {}
                    blobRs = null;
                }
                if (pStmt != null) {
                    try {
                        pStmt.close();
                    } catch (SQLException sQLException) {}
                    pStmt = null;
                }
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public long position(Blob pattern, long start) throws SQLException {
        try {
            return this.position(pattern.getBytes(0L, (int)pattern.length()), start);
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public long position(byte[] pattern, long start) throws SQLException {
        try {
            int i;
            ResultSet blobRs = null;
            PreparedStatement pStmt = null;
            StringBuilder query = new StringBuilder("SELECT LOCATE(");
            query.append("?, ");
            query.append(this.blobColumnName);
            query.append(", ");
            query.append(start);
            query.append(") FROM ");
            query.append(this.tableName);
            query.append(" WHERE ");
            query.append(this.primaryKeyColumns.get(0));
            query.append(" = ?");
            for (i = 1; i < this.numPrimaryKeys; ++i) {
                query.append(" AND ");
                query.append(this.primaryKeyColumns.get(i));
                query.append(" = ?");
            }
            try {
                pStmt = this.creatorResultSet.getConnection().prepareStatement(query.toString());
                pStmt.setBytes(1, pattern);
                for (i = 0; i < this.numPrimaryKeys; ++i) {
                    pStmt.setString(i + 2, this.primaryKeyValues.get(i));
                }
                blobRs = pStmt.executeQuery();
                if (blobRs.next()) {
                    long l = blobRs.getLong(1);
                    return l;
                }
                throw SQLError.createSQLException(Messages.getString("Blob.9"), "S1000", this.exceptionInterceptor);
            } finally {
                if (blobRs != null) {
                    try {
                        blobRs.close();
                    } catch (SQLException sQLException) {}
                    blobRs = null;
                }
                if (pStmt != null) {
                    try {
                        pStmt.close();
                    } catch (SQLException sQLException) {}
                    pStmt = null;
                }
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void truncate(long length) throws SQLException {
        try {
            int i;
            PreparedStatement pStmt = null;
            StringBuilder query = new StringBuilder("UPDATE ");
            query.append(this.tableName);
            query.append(" SET ");
            query.append(this.blobColumnName);
            query.append(" = LEFT(");
            query.append(this.blobColumnName);
            query.append(", ");
            query.append(length);
            query.append(") WHERE ");
            query.append(this.primaryKeyColumns.get(0));
            query.append(" = ?");
            for (i = 1; i < this.numPrimaryKeys; ++i) {
                query.append(" AND ");
                query.append(this.primaryKeyColumns.get(i));
                query.append(" = ?");
            }
            try {
                pStmt = this.creatorResultSet.getConnection().prepareStatement(query.toString());
                for (i = 0; i < this.numPrimaryKeys; ++i) {
                    pStmt.setString(i + 1, this.primaryKeyValues.get(i));
                }
                int rowsUpdated = pStmt.executeUpdate();
                if (rowsUpdated != 1) {
                    throw SQLError.createSQLException(Messages.getString("Blob.9"), "S1000", this.exceptionInterceptor);
                }
            } finally {
                if (pStmt != null) {
                    try {
                        pStmt.close();
                    } catch (SQLException sQLException) {}
                    pStmt = null;
                }
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    PreparedStatement createGetBytesStatement() throws SQLException {
        StringBuilder query = new StringBuilder("SELECT SUBSTRING(");
        query.append(this.blobColumnName);
        query.append(", ");
        query.append("?");
        query.append(", ");
        query.append("?");
        query.append(") FROM ");
        query.append(this.tableName);
        query.append(" WHERE ");
        query.append(this.primaryKeyColumns.get(0));
        query.append(" = ?");
        for (int i = 1; i < this.numPrimaryKeys; ++i) {
            query.append(" AND ");
            query.append(this.primaryKeyColumns.get(i));
            query.append(" = ?");
        }
        return this.creatorResultSet.getConnection().prepareStatement(query.toString());
    }

    byte[] getBytesInternal(PreparedStatement pStmt, long pos, int length) throws SQLException {
        ResultSet blobRs = null;
        try {
            pStmt.setLong(1, pos);
            pStmt.setInt(2, length);
            for (int i = 0; i < this.numPrimaryKeys; ++i) {
                pStmt.setString(i + 3, this.primaryKeyValues.get(i));
            }
            blobRs = pStmt.executeQuery();
            if (blobRs.next()) {
                byte[] byArray = ((ResultSetImpl)blobRs).getBytes(1);
                return byArray;
            }
            throw SQLError.createSQLException(Messages.getString("Blob.9"), "S1000", this.exceptionInterceptor);
        } finally {
            if (blobRs != null) {
                try {
                    blobRs.close();
                } catch (SQLException sQLException) {}
                blobRs = null;
            }
        }
    }

    @Override
    public void free() throws SQLException {
        try {
            this.creatorResultSet = null;
            this.primaryKeyColumns = null;
            this.primaryKeyValues = null;
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    @Override
    public InputStream getBinaryStream(long pos, long length) throws SQLException {
        try {
            return new LocatorInputStream(pos, length);
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.exceptionInterceptor);
        }
    }

    class LocatorInputStream
    extends InputStream {
        long currentPositionInBlob = 0L;
        long length = 0L;
        PreparedStatement pStmt = null;

        LocatorInputStream() throws SQLException {
            this.length = BlobFromLocator.this.length();
            this.pStmt = BlobFromLocator.this.createGetBytesStatement();
        }

        LocatorInputStream(long pos, long len) throws SQLException {
            this.length = pos + len;
            this.currentPositionInBlob = pos;
            long blobLength = BlobFromLocator.this.length();
            if (pos + len > blobLength) {
                throw SQLError.createSQLException(Messages.getString("Blob.invalidStreamLength", new Object[]{blobLength, pos, len}), "S1009", BlobFromLocator.this.exceptionInterceptor);
            }
            if (pos < 1L) {
                throw SQLError.createSQLException(Messages.getString("Blob.invalidStreamPos"), "S1009", BlobFromLocator.this.exceptionInterceptor);
            }
            if (pos > blobLength) {
                throw SQLError.createSQLException(Messages.getString("Blob.invalidStreamPos"), "S1009", BlobFromLocator.this.exceptionInterceptor);
            }
        }

        @Override
        public int read() throws IOException {
            if (this.currentPositionInBlob + 1L > this.length) {
                return -1;
            }
            try {
                byte[] asBytes = BlobFromLocator.this.getBytesInternal(this.pStmt, this.currentPositionInBlob++ + 1L, 1);
                if (asBytes == null) {
                    return -1;
                }
                return asBytes[0];
            } catch (SQLException sqlEx) {
                throw new IOException(sqlEx.toString());
            }
        }

        @Override
        public int read(byte[] b, int off, int len) throws IOException {
            if (this.currentPositionInBlob + 1L > this.length) {
                return -1;
            }
            try {
                byte[] asBytes = BlobFromLocator.this.getBytesInternal(this.pStmt, this.currentPositionInBlob + 1L, len);
                if (asBytes == null) {
                    return -1;
                }
                System.arraycopy(asBytes, 0, b, off, asBytes.length);
                this.currentPositionInBlob += (long)asBytes.length;
                return asBytes.length;
            } catch (SQLException sqlEx) {
                throw new IOException(sqlEx.toString());
            }
        }

        @Override
        public int read(byte[] b) throws IOException {
            if (this.currentPositionInBlob + 1L > this.length) {
                return -1;
            }
            try {
                byte[] asBytes = BlobFromLocator.this.getBytesInternal(this.pStmt, this.currentPositionInBlob + 1L, b.length);
                if (asBytes == null) {
                    return -1;
                }
                System.arraycopy(asBytes, 0, b, 0, asBytes.length);
                this.currentPositionInBlob += (long)asBytes.length;
                return asBytes.length;
            } catch (SQLException sqlEx) {
                throw new IOException(sqlEx.toString());
            }
        }

        @Override
        public void close() throws IOException {
            if (this.pStmt != null) {
                try {
                    this.pStmt.close();
                } catch (SQLException sqlEx) {
                    throw new IOException(sqlEx.toString());
                }
            }
            super.close();
        }
    }
}

