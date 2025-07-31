/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.jdbc;

import com.mysql.cj.exceptions.CJException;
import com.mysql.cj.jdbc.JdbcConnection;
import com.mysql.cj.jdbc.MysqlPooledConnection;
import com.mysql.cj.jdbc.MysqlXAConnection;
import com.mysql.cj.jdbc.exceptions.SQLExceptionsMapping;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.sql.XAConnection;
import javax.transaction.xa.XAException;
import javax.transaction.xa.XAResource;
import javax.transaction.xa.Xid;

public class SuspendableXAConnection
extends MysqlPooledConnection
implements XAConnection,
XAResource {
    private static final Map<Xid, XAConnection> XIDS_TO_PHYSICAL_CONNECTIONS = new HashMap<Xid, XAConnection>();
    private Xid currentXid;
    private XAConnection currentXAConnection;
    private XAResource currentXAResource;
    private JdbcConnection underlyingConnection;

    protected static SuspendableXAConnection getInstance(JdbcConnection mysqlConnection) throws SQLException {
        return new SuspendableXAConnection(mysqlConnection);
    }

    public SuspendableXAConnection(JdbcConnection connection) {
        super(connection);
        this.underlyingConnection = connection;
    }

    private static synchronized XAConnection findConnectionForXid(JdbcConnection connectionToWrap, Xid xid) throws SQLException {
        XAConnection conn = XIDS_TO_PHYSICAL_CONNECTIONS.get(xid);
        if (conn == null) {
            conn = new MysqlXAConnection(connectionToWrap, connectionToWrap.getPropertySet().getBooleanProperty("logXaCommands").getValue());
            XIDS_TO_PHYSICAL_CONNECTIONS.put(xid, conn);
        }
        return conn;
    }

    private static synchronized void removeXAConnectionMapping(Xid xid) {
        XIDS_TO_PHYSICAL_CONNECTIONS.remove(xid);
    }

    private synchronized void switchToXid(Xid xid) throws XAException {
        if (xid == null) {
            throw new XAException();
        }
        try {
            if (!xid.equals(this.currentXid)) {
                XAConnection toSwitchTo;
                this.currentXAConnection = toSwitchTo = SuspendableXAConnection.findConnectionForXid(this.underlyingConnection, xid);
                this.currentXid = xid;
                this.currentXAResource = toSwitchTo.getXAResource();
            }
        } catch (SQLException sqlEx) {
            throw new XAException();
        }
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
    public void commit(Xid xid, boolean arg1) throws XAException {
        this.switchToXid(xid);
        this.currentXAResource.commit(xid, arg1);
        SuspendableXAConnection.removeXAConnectionMapping(xid);
    }

    @Override
    public void end(Xid xid, int arg1) throws XAException {
        this.switchToXid(xid);
        this.currentXAResource.end(xid, arg1);
    }

    @Override
    public void forget(Xid xid) throws XAException {
        this.switchToXid(xid);
        this.currentXAResource.forget(xid);
        SuspendableXAConnection.removeXAConnectionMapping(xid);
    }

    @Override
    public int getTransactionTimeout() throws XAException {
        return 0;
    }

    @Override
    public boolean isSameRM(XAResource xaRes) throws XAException {
        return xaRes == this;
    }

    @Override
    public int prepare(Xid xid) throws XAException {
        this.switchToXid(xid);
        return this.currentXAResource.prepare(xid);
    }

    @Override
    public Xid[] recover(int flag) throws XAException {
        return MysqlXAConnection.recover(this.underlyingConnection, flag);
    }

    @Override
    public void rollback(Xid xid) throws XAException {
        this.switchToXid(xid);
        this.currentXAResource.rollback(xid);
        SuspendableXAConnection.removeXAConnectionMapping(xid);
    }

    @Override
    public boolean setTransactionTimeout(int arg0) throws XAException {
        return false;
    }

    @Override
    public void start(Xid xid, int arg1) throws XAException {
        this.switchToXid(xid);
        if (arg1 != 0x200000) {
            this.currentXAResource.start(xid, arg1);
            return;
        }
        this.currentXAResource.start(xid, 0x8000000);
    }

    @Override
    public synchronized Connection getConnection() throws SQLException {
        try {
            if (this.currentXAConnection == null) {
                return this.getConnection(false, true);
            }
            return this.currentXAConnection.getConnection();
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException);
        }
    }

    @Override
    public void close() throws SQLException {
        try {
            if (this.currentXAConnection == null) {
                super.close();
            } else {
                SuspendableXAConnection.removeXAConnectionMapping(this.currentXid);
                this.currentXAConnection.close();
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException);
        }
    }
}

