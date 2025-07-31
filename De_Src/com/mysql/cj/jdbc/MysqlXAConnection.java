/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.jdbc;

import com.mysql.cj.Messages;
import com.mysql.cj.exceptions.CJException;
import com.mysql.cj.jdbc.JdbcConnection;
import com.mysql.cj.jdbc.MysqlPooledConnection;
import com.mysql.cj.jdbc.MysqlXAException;
import com.mysql.cj.jdbc.MysqlXid;
import com.mysql.cj.jdbc.exceptions.SQLExceptionsMapping;
import com.mysql.cj.log.Log;
import com.mysql.cj.util.StringUtils;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.sql.XAConnection;
import javax.transaction.xa.XAException;
import javax.transaction.xa.XAResource;
import javax.transaction.xa.Xid;

public class MysqlXAConnection
extends MysqlPooledConnection
implements XAConnection,
XAResource {
    private static final int MAX_COMMAND_LENGTH = 300;
    private JdbcConnection underlyingConnection;
    private static final Map<Integer, Integer> MYSQL_ERROR_CODES_TO_XA_ERROR_CODES;
    private Log log;
    protected boolean logXaCommands;

    protected static MysqlXAConnection getInstance(JdbcConnection mysqlConnection, boolean logXaCommands) throws SQLException {
        return new MysqlXAConnection(mysqlConnection, logXaCommands);
    }

    public MysqlXAConnection(JdbcConnection connection, boolean logXaCommands) {
        super(connection);
        this.underlyingConnection = connection;
        this.log = connection.getSession().getLog();
        this.logXaCommands = logXaCommands;
    }

    @Override
    public XAResource getXAResource() throws SQLException {
        try {
            return this;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException);
        }
    }

    @Override
    public int getTransactionTimeout() throws XAException {
        return 0;
    }

    @Override
    public boolean setTransactionTimeout(int arg0) throws XAException {
        return false;
    }

    @Override
    public boolean isSameRM(XAResource xares) throws XAException {
        if (xares instanceof MysqlXAConnection) {
            return this.underlyingConnection.isSameResource(((MysqlXAConnection)xares).underlyingConnection);
        }
        return false;
    }

    @Override
    public Xid[] recover(int flag) throws XAException {
        return MysqlXAConnection.recover(this.underlyingConnection, flag);
    }

    protected static Xid[] recover(Connection c, int flag) throws XAException {
        boolean endRscan;
        boolean startRscan = (flag & 0x1000000) > 0;
        boolean bl = endRscan = (flag & 0x800000) > 0;
        if (!startRscan && !endRscan && flag != 0) {
            throw new MysqlXAException(-5, Messages.getString("MysqlXAConnection.001"), null);
        }
        if (!startRscan) {
            return new Xid[0];
        }
        ResultSet rs = null;
        Statement stmt = null;
        ArrayList<MysqlXid> recoveredXidList = new ArrayList<MysqlXid>();
        try {
            stmt = c.createStatement();
            rs = stmt.executeQuery("XA RECOVER");
            while (rs.next()) {
                int formatId = rs.getInt(1);
                int gtridLength = rs.getInt(2);
                int bqualLength = rs.getInt(3);
                byte[] gtridAndBqual = rs.getBytes(4);
                byte[] gtrid = new byte[gtridLength];
                byte[] bqual = new byte[bqualLength];
                if (gtridAndBqual.length != gtridLength + bqualLength) {
                    throw new MysqlXAException(105, Messages.getString("MysqlXAConnection.002"), null);
                }
                System.arraycopy(gtridAndBqual, 0, gtrid, 0, gtridLength);
                System.arraycopy(gtridAndBqual, gtridLength, bqual, 0, bqualLength);
                recoveredXidList.add(new MysqlXid(gtrid, bqual, formatId));
            }
        } catch (SQLException sqlEx) {
            throw MysqlXAConnection.mapXAExceptionFromSQLException(sqlEx);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) {
                    throw MysqlXAConnection.mapXAExceptionFromSQLException(sqlEx);
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {
                    throw MysqlXAConnection.mapXAExceptionFromSQLException(sqlEx);
                }
            }
        }
        int numXids = recoveredXidList.size();
        Xid[] asXids = new Xid[numXids];
        Object[] asObjects = recoveredXidList.toArray();
        for (int i = 0; i < numXids; ++i) {
            asXids[i] = (Xid)asObjects[i];
        }
        return asXids;
    }

    @Override
    public int prepare(Xid xid) throws XAException {
        StringBuilder commandBuf = new StringBuilder(300);
        commandBuf.append("XA PREPARE ");
        MysqlXAConnection.appendXid(commandBuf, xid);
        this.dispatchCommand(commandBuf.toString());
        return 0;
    }

    @Override
    public void forget(Xid xid) throws XAException {
    }

    @Override
    public void rollback(Xid xid) throws XAException {
        StringBuilder commandBuf = new StringBuilder(300);
        commandBuf.append("XA ROLLBACK ");
        MysqlXAConnection.appendXid(commandBuf, xid);
        try {
            this.dispatchCommand(commandBuf.toString());
        } finally {
            this.underlyingConnection.setInGlobalTx(false);
        }
    }

    @Override
    public void end(Xid xid, int flags) throws XAException {
        StringBuilder commandBuf = new StringBuilder(300);
        commandBuf.append("XA END ");
        MysqlXAConnection.appendXid(commandBuf, xid);
        switch (flags) {
            case 0x4000000: {
                break;
            }
            case 0x2000000: {
                commandBuf.append(" SUSPEND");
                break;
            }
            case 0x20000000: {
                break;
            }
            default: {
                throw new XAException(-5);
            }
        }
        this.dispatchCommand(commandBuf.toString());
    }

    @Override
    public void start(Xid xid, int flags) throws XAException {
        StringBuilder commandBuf = new StringBuilder(300);
        commandBuf.append("XA START ");
        MysqlXAConnection.appendXid(commandBuf, xid);
        switch (flags) {
            case 0x200000: {
                commandBuf.append(" JOIN");
                break;
            }
            case 0x8000000: {
                commandBuf.append(" RESUME");
                break;
            }
            case 0: {
                break;
            }
            default: {
                throw new XAException(-5);
            }
        }
        this.dispatchCommand(commandBuf.toString());
        this.underlyingConnection.setInGlobalTx(true);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void commit(Xid xid, boolean onePhase) throws XAException {
        StringBuilder commandBuf = new StringBuilder(300);
        commandBuf.append("XA COMMIT ");
        MysqlXAConnection.appendXid(commandBuf, xid);
        if (onePhase) {
            commandBuf.append(" ONE PHASE");
        }
        try {
            this.dispatchCommand(commandBuf.toString());
        } finally {
            this.underlyingConnection.setInGlobalTx(false);
        }
    }

    private ResultSet dispatchCommand(String command) throws XAException {
        Statement stmt = null;
        try {
            ResultSet rs;
            if (this.logXaCommands) {
                this.log.logDebug("Executing XA statement: " + command);
            }
            stmt = this.underlyingConnection.createStatement();
            stmt.execute(command);
            ResultSet resultSet = rs = stmt.getResultSet();
            return resultSet;
        } catch (SQLException sqlEx) {
            throw MysqlXAConnection.mapXAExceptionFromSQLException(sqlEx);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sQLException) {}
            }
        }
    }

    protected static XAException mapXAExceptionFromSQLException(SQLException sqlEx) {
        Integer xaCode = MYSQL_ERROR_CODES_TO_XA_ERROR_CODES.get(sqlEx.getErrorCode());
        if (xaCode != null) {
            return (XAException)new MysqlXAException(xaCode, sqlEx.getMessage(), null).initCause(sqlEx);
        }
        return (XAException)new MysqlXAException(-7, Messages.getString("MysqlXAConnection.003"), null).initCause(sqlEx);
    }

    private static void appendXid(StringBuilder builder, Xid xid) {
        byte[] gtrid = xid.getGlobalTransactionId();
        byte[] btrid = xid.getBranchQualifier();
        if (gtrid != null) {
            StringUtils.appendAsHex(builder, gtrid);
        }
        builder.append(',');
        if (btrid != null) {
            StringUtils.appendAsHex(builder, btrid);
        }
        builder.append(',');
        StringUtils.appendAsHex(builder, xid.getFormatId());
    }

    @Override
    public synchronized Connection getConnection() throws SQLException {
        try {
            Connection connToWrap = this.getConnection(false, true);
            return connToWrap;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException);
        }
    }

    static {
        HashMap<Integer, Integer> temp = new HashMap<Integer, Integer>();
        temp.put(1397, -4);
        temp.put(1398, -5);
        temp.put(1399, -7);
        temp.put(1400, -9);
        temp.put(1401, -3);
        temp.put(1402, 100);
        temp.put(1440, -8);
        temp.put(1613, 106);
        temp.put(1614, 102);
        MYSQL_ERROR_CODES_TO_XA_ERROR_CODES = Collections.unmodifiableMap(temp);
    }
}

