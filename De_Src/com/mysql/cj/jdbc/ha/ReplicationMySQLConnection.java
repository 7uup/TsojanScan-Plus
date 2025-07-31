/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.jdbc.ha;

import com.mysql.cj.Messages;
import com.mysql.cj.exceptions.CJException;
import com.mysql.cj.jdbc.JdbcConnection;
import com.mysql.cj.jdbc.exceptions.SQLError;
import com.mysql.cj.jdbc.exceptions.SQLExceptionsMapping;
import com.mysql.cj.jdbc.ha.LoadBalancedConnection;
import com.mysql.cj.jdbc.ha.MultiHostConnectionProxy;
import com.mysql.cj.jdbc.ha.MultiHostMySQLConnection;
import com.mysql.cj.jdbc.ha.ReplicationConnection;
import com.mysql.cj.jdbc.ha.ReplicationConnectionProxy;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.Executor;

public class ReplicationMySQLConnection
extends MultiHostMySQLConnection
implements ReplicationConnection {
    public ReplicationMySQLConnection(MultiHostConnectionProxy proxy) {
        super(proxy);
    }

    @Override
    public ReplicationConnectionProxy getThisAsProxy() {
        return (ReplicationConnectionProxy)super.getThisAsProxy();
    }

    @Override
    public JdbcConnection getActiveMySQLConnection() {
        return this.getCurrentConnection();
    }

    @Override
    public synchronized JdbcConnection getCurrentConnection() {
        return this.getThisAsProxy().getCurrentConnection();
    }

    @Override
    public long getConnectionGroupId() {
        return this.getThisAsProxy().getConnectionGroupId();
    }

    @Override
    public synchronized JdbcConnection getMasterConnection() {
        return this.getThisAsProxy().getMasterConnection();
    }

    private JdbcConnection getValidatedMasterConnection() {
        LoadBalancedConnection conn = this.getThisAsProxy().masterConnection;
        try {
            return conn == null || conn.isClosed() ? null : conn;
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public void promoteSlaveToMaster(String host) throws SQLException {
        try {
            this.getThisAsProxy().promoteSlaveToMaster(host);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void removeMasterHost(String host) throws SQLException {
        try {
            this.getThisAsProxy().removeMasterHost(host);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void removeMasterHost(String host, boolean waitUntilNotInUse) throws SQLException {
        try {
            this.getThisAsProxy().removeMasterHost(host, waitUntilNotInUse);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean isHostMaster(String host) {
        return this.getThisAsProxy().isHostMaster(host);
    }

    @Override
    public synchronized JdbcConnection getSlavesConnection() {
        return this.getThisAsProxy().getSlavesConnection();
    }

    private JdbcConnection getValidatedSlavesConnection() {
        LoadBalancedConnection conn = this.getThisAsProxy().slavesConnection;
        try {
            return conn == null || conn.isClosed() ? null : conn;
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public void addSlaveHost(String host) throws SQLException {
        try {
            this.getThisAsProxy().addSlaveHost(host);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void removeSlave(String host) throws SQLException {
        try {
            this.getThisAsProxy().removeSlave(host);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void removeSlave(String host, boolean closeGently) throws SQLException {
        try {
            this.getThisAsProxy().removeSlave(host, closeGently);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean isHostSlave(String host) {
        return this.getThisAsProxy().isHostSlave(host);
    }

    @Override
    public void setReadOnly(boolean readOnlyFlag) throws SQLException {
        try {
            this.getThisAsProxy().setReadOnly(readOnlyFlag);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public boolean isReadOnly() throws SQLException {
        try {
            return this.getThisAsProxy().isReadOnly();
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public synchronized void ping() throws SQLException {
        try {
            block9: {
                JdbcConnection conn;
                block8: {
                    try {
                        conn = this.getValidatedMasterConnection();
                        if (conn != null) {
                            conn.ping();
                        }
                    } catch (SQLException e) {
                        if (!this.isMasterConnection()) break block8;
                        throw e;
                    }
                }
                try {
                    conn = this.getValidatedSlavesConnection();
                    if (conn != null) {
                        conn.ping();
                    }
                } catch (SQLException e) {
                    if (this.isMasterConnection()) break block9;
                    throw e;
                }
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public synchronized void changeUser(String userName, String newPassword) throws SQLException {
        try {
            JdbcConnection conn = this.getValidatedMasterConnection();
            if (conn != null) {
                conn.changeUser(userName, newPassword);
            }
            if ((conn = this.getValidatedSlavesConnection()) != null) {
                conn.changeUser(userName, newPassword);
            }
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public synchronized void setStatementComment(String comment) {
        JdbcConnection conn = this.getValidatedMasterConnection();
        if (conn != null) {
            conn.setStatementComment(comment);
        }
        if ((conn = this.getValidatedSlavesConnection()) != null) {
            conn.setStatementComment(comment);
        }
    }

    @Override
    public boolean hasSameProperties(JdbcConnection c) {
        JdbcConnection connM = this.getValidatedMasterConnection();
        JdbcConnection connS = this.getValidatedSlavesConnection();
        if (connM == null && connS == null) {
            return false;
        }
        return !(connM != null && !connM.hasSameProperties(c) || connS != null && !connS.hasSameProperties(c));
    }

    @Override
    public Properties getProperties() {
        Properties props = new Properties();
        JdbcConnection conn = this.getValidatedMasterConnection();
        if (conn != null) {
            props.putAll(conn.getProperties());
        }
        if ((conn = this.getValidatedSlavesConnection()) != null) {
            props.putAll(conn.getProperties());
        }
        return props;
    }

    @Override
    public void abort(Executor executor) throws SQLException {
        try {
            this.getThisAsProxy().doAbort(executor);
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void abortInternal() throws SQLException {
        try {
            this.getThisAsProxy().doAbortInternal();
            return;
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public void setProxy(JdbcConnection proxy) {
        this.getThisAsProxy().setProxy(proxy);
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        try {
            return iface.isInstance(this);
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        try {
            try {
                return iface.cast(this);
            } catch (ClassCastException cce) {
                throw SQLError.createSQLException(Messages.getString("Common.UnableToUnwrap", new Object[]{iface.toString()}), "S1009", this.getExceptionInterceptor());
            }
        } catch (CJException cJException) {
            throw SQLExceptionsMapping.translateException(cJException, this.getExceptionInterceptor());
        }
    }

    @Override
    @Deprecated
    public synchronized void clearHasTriedMaster() {
        this.getThisAsProxy().masterConnection.clearHasTriedMaster();
        this.getThisAsProxy().slavesConnection.clearHasTriedMaster();
    }
}

