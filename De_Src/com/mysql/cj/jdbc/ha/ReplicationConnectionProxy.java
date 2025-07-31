/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.jdbc.ha;

import com.mysql.cj.Messages;
import com.mysql.cj.PingTarget;
import com.mysql.cj.conf.HostInfo;
import com.mysql.cj.conf.url.LoadbalanceConnectionUrl;
import com.mysql.cj.conf.url.ReplicationConnectionUrl;
import com.mysql.cj.jdbc.JdbcConnection;
import com.mysql.cj.jdbc.JdbcStatement;
import com.mysql.cj.jdbc.exceptions.SQLError;
import com.mysql.cj.jdbc.ha.LoadBalancedConnection;
import com.mysql.cj.jdbc.ha.LoadBalancedConnectionProxy;
import com.mysql.cj.jdbc.ha.MultiHostConnectionProxy;
import com.mysql.cj.jdbc.ha.ReplicationConnection;
import com.mysql.cj.jdbc.ha.ReplicationConnectionGroup;
import com.mysql.cj.jdbc.ha.ReplicationConnectionGroupManager;
import com.mysql.cj.jdbc.ha.ReplicationMySQLConnection;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Executor;

public class ReplicationConnectionProxy
extends MultiHostConnectionProxy
implements PingTarget {
    private ReplicationConnection thisAsReplicationConnection;
    protected boolean enableJMX;
    protected boolean allowMasterDownConnections;
    protected boolean allowSlaveDownConnections;
    protected boolean readFromMasterWhenNoSlaves;
    protected boolean readFromMasterWhenNoSlavesOriginal;
    protected boolean readOnly;
    ReplicationConnectionGroup connectionGroup;
    private long connectionGroupID;
    private List<HostInfo> masterHosts;
    protected LoadBalancedConnection masterConnection;
    private List<HostInfo> slaveHosts;
    protected LoadBalancedConnection slavesConnection;

    public static ReplicationConnection createProxyInstance(ReplicationConnectionUrl connectionUrl) throws SQLException {
        ReplicationConnectionProxy connProxy = new ReplicationConnectionProxy(connectionUrl);
        return (ReplicationConnection)Proxy.newProxyInstance(ReplicationConnection.class.getClassLoader(), new Class[]{ReplicationConnection.class, JdbcConnection.class}, connProxy);
    }

    private ReplicationConnectionProxy(ReplicationConnectionUrl connectionUrl) throws SQLException {
        block21: {
            this.enableJMX = false;
            this.allowMasterDownConnections = false;
            this.allowSlaveDownConnections = false;
            this.readFromMasterWhenNoSlaves = false;
            this.readFromMasterWhenNoSlavesOriginal = false;
            this.readOnly = false;
            this.connectionGroupID = -1L;
            Properties props = connectionUrl.getConnectionArgumentsAsProperties();
            this.thisAsReplicationConnection = (ReplicationConnection)this.thisAsConnection;
            this.connectionUrl = connectionUrl;
            String enableJMXAsString = props.getProperty("ha.enableJMX", "false");
            try {
                this.enableJMX = Boolean.parseBoolean(enableJMXAsString);
            } catch (Exception e) {
                throw SQLError.createSQLException(Messages.getString("MultihostConnection.badValueForHaEnableJMX", new Object[]{enableJMXAsString}), "S1009", null);
            }
            String allowMasterDownConnectionsAsString = props.getProperty("allowMasterDownConnections", "false");
            try {
                this.allowMasterDownConnections = Boolean.parseBoolean(allowMasterDownConnectionsAsString);
            } catch (Exception e) {
                throw SQLError.createSQLException(Messages.getString("ReplicationConnectionProxy.badValueForAllowMasterDownConnections", new Object[]{enableJMXAsString}), "S1009", null);
            }
            String allowSlaveDownConnectionsAsString = props.getProperty("allowSlaveDownConnections", "false");
            try {
                this.allowSlaveDownConnections = Boolean.parseBoolean(allowSlaveDownConnectionsAsString);
            } catch (Exception e) {
                throw SQLError.createSQLException(Messages.getString("ReplicationConnectionProxy.badValueForAllowSlaveDownConnections", new Object[]{allowSlaveDownConnectionsAsString}), "S1009", null);
            }
            String readFromMasterWhenNoSlavesAsString = props.getProperty("readFromMasterWhenNoSlaves");
            try {
                this.readFromMasterWhenNoSlavesOriginal = Boolean.parseBoolean(readFromMasterWhenNoSlavesAsString);
            } catch (Exception e) {
                throw SQLError.createSQLException(Messages.getString("ReplicationConnectionProxy.badValueForReadFromMasterWhenNoSlaves", new Object[]{readFromMasterWhenNoSlavesAsString}), "S1009", null);
            }
            String group = props.getProperty("replicationConnectionGroup", null);
            if (group != null) {
                this.connectionGroup = ReplicationConnectionGroupManager.getConnectionGroupInstance(group);
                if (this.enableJMX) {
                    ReplicationConnectionGroupManager.registerJmx();
                }
                this.connectionGroupID = this.connectionGroup.registerReplicationConnection(this.thisAsReplicationConnection, connectionUrl.getMastersListAsHostPortPairs(), connectionUrl.getSlavesListAsHostPortPairs());
                this.masterHosts = connectionUrl.getMasterHostsListFromHostPortPairs(this.connectionGroup.getMasterHosts());
                this.slaveHosts = connectionUrl.getSlaveHostsListFromHostPortPairs(this.connectionGroup.getSlaveHosts());
            } else {
                this.masterHosts = new ArrayList<HostInfo>(connectionUrl.getMastersList());
                this.slaveHosts = new ArrayList<HostInfo>(connectionUrl.getSlavesList());
            }
            this.resetReadFromMasterWhenNoSlaves();
            try {
                this.initializeSlavesConnection();
            } catch (SQLException e) {
                if (this.allowSlaveDownConnections) break block21;
                if (this.connectionGroup != null) {
                    this.connectionGroup.handleCloseConnection(this.thisAsReplicationConnection);
                }
                throw e;
            }
        }
        SQLException exCaught = null;
        try {
            this.currentConnection = this.initializeMasterConnection();
        } catch (SQLException e) {
            exCaught = e;
        }
        if (this.currentConnection == null) {
            if (this.allowMasterDownConnections && this.slavesConnection != null) {
                this.readOnly = true;
                this.currentConnection = this.slavesConnection;
            } else {
                if (this.connectionGroup != null) {
                    this.connectionGroup.handleCloseConnection(this.thisAsReplicationConnection);
                }
                if (exCaught != null) {
                    throw exCaught;
                }
                throw SQLError.createSQLException(Messages.getString("ReplicationConnectionProxy.initializationWithEmptyHostsLists"), "S1009", null);
            }
        }
    }

    @Override
    JdbcConnection getNewWrapperForThisAsConnection() throws SQLException {
        return new ReplicationMySQLConnection(this);
    }

    @Override
    protected void propagateProxyDown(JdbcConnection proxyConn) {
        if (this.masterConnection != null) {
            this.masterConnection.setProxy(proxyConn);
        }
        if (this.slavesConnection != null) {
            this.slavesConnection.setProxy(proxyConn);
        }
    }

    @Override
    boolean shouldExceptionTriggerConnectionSwitch(Throwable t) {
        return false;
    }

    @Override
    public boolean isMasterConnection() {
        return this.currentConnection != null && this.currentConnection == this.masterConnection;
    }

    public boolean isSlavesConnection() {
        return this.currentConnection != null && this.currentConnection == this.slavesConnection;
    }

    @Override
    void pickNewConnection() throws SQLException {
    }

    @Override
    void syncSessionState(JdbcConnection source2, JdbcConnection target, boolean readonly) throws SQLException {
        try {
            super.syncSessionState(source2, target, readonly);
        } catch (SQLException e1) {
            try {
                super.syncSessionState(source2, target, readonly);
            } catch (SQLException sQLException) {
                // empty catch block
            }
        }
    }

    @Override
    void doClose() throws SQLException {
        if (this.masterConnection != null) {
            this.masterConnection.close();
        }
        if (this.slavesConnection != null) {
            this.slavesConnection.close();
        }
        if (this.connectionGroup != null) {
            this.connectionGroup.handleCloseConnection(this.thisAsReplicationConnection);
        }
    }

    @Override
    void doAbortInternal() throws SQLException {
        this.masterConnection.abortInternal();
        this.slavesConnection.abortInternal();
        if (this.connectionGroup != null) {
            this.connectionGroup.handleCloseConnection(this.thisAsReplicationConnection);
        }
    }

    @Override
    void doAbort(Executor executor) throws SQLException {
        this.masterConnection.abort(executor);
        this.slavesConnection.abort(executor);
        if (this.connectionGroup != null) {
            this.connectionGroup.handleCloseConnection(this.thisAsReplicationConnection);
        }
    }

    @Override
    Object invokeMore(Object proxy, Method method, Object[] args2) throws Throwable {
        this.checkConnectionCapabilityForMethod(method);
        boolean invokeAgain = false;
        while (true) {
            try {
                Object result = method.invoke(this.thisAsConnection, args2);
                if (result != null && result instanceof JdbcStatement) {
                    ((JdbcStatement)result).setPingTarget(this);
                }
                return result;
            } catch (InvocationTargetException e) {
                if (invokeAgain) {
                    invokeAgain = false;
                    continue;
                }
                if (e.getCause() == null || !(e.getCause() instanceof SQLException) || ((SQLException)e.getCause()).getSQLState() != "25000" || ((SQLException)e.getCause()).getErrorCode() != 1000001) continue;
                try {
                    this.setReadOnly(this.readOnly);
                    invokeAgain = true;
                    continue;
                } catch (SQLException sQLException) {
                    // empty catch block
                }
                if (invokeAgain) continue;
                throw e;
            }
            break;
        }
    }

    private void checkConnectionCapabilityForMethod(Method method) throws Throwable {
        if (this.masterHosts.isEmpty() && this.slaveHosts.isEmpty() && !ReplicationConnection.class.isAssignableFrom(method.getDeclaringClass())) {
            throw SQLError.createSQLException(Messages.getString("ReplicationConnectionProxy.noHostsInconsistentState"), "25000", 1000002, true, null);
        }
    }

    @Override
    public void doPing() throws SQLException {
        SQLException slavesPingException;
        SQLException mastersPingException;
        boolean isMasterConn;
        block16: {
            isMasterConn = this.isMasterConnection();
            mastersPingException = null;
            slavesPingException = null;
            if (this.masterConnection != null) {
                try {
                    this.masterConnection.ping();
                } catch (SQLException e) {
                    mastersPingException = e;
                }
            } else {
                this.initializeMasterConnection();
            }
            if (this.slavesConnection != null) {
                try {
                    this.slavesConnection.ping();
                } catch (SQLException e) {
                    slavesPingException = e;
                }
            } else {
                try {
                    this.initializeSlavesConnection();
                    if (this.switchToSlavesConnectionIfNecessary()) {
                        isMasterConn = false;
                    }
                } catch (SQLException e) {
                    if (this.masterConnection != null && this.readFromMasterWhenNoSlaves) break block16;
                    throw e;
                }
            }
        }
        if (isMasterConn && mastersPingException != null) {
            if (this.slavesConnection != null && slavesPingException == null) {
                this.masterConnection = null;
                this.currentConnection = this.slavesConnection;
                this.readOnly = true;
            }
            throw mastersPingException;
        }
        if (!(isMasterConn || slavesPingException == null && this.slavesConnection != null)) {
            if (this.masterConnection != null && this.readFromMasterWhenNoSlaves && mastersPingException == null) {
                this.slavesConnection = null;
                this.currentConnection = this.masterConnection;
                this.readOnly = true;
                this.currentConnection.setReadOnly(true);
            }
            if (slavesPingException != null) {
                throw slavesPingException;
            }
        }
    }

    private JdbcConnection initializeMasterConnection() throws SQLException {
        this.masterConnection = null;
        if (this.masterHosts.size() == 0) {
            return null;
        }
        LoadBalancedConnection newMasterConn = LoadBalancedConnectionProxy.createProxyInstance(new LoadbalanceConnectionUrl(this.masterHosts, this.connectionUrl.getOriginalProperties()));
        newMasterConn.setProxy(this.getProxy());
        this.masterConnection = newMasterConn;
        return this.masterConnection;
    }

    private JdbcConnection initializeSlavesConnection() throws SQLException {
        this.slavesConnection = null;
        if (this.slaveHosts.size() == 0) {
            return null;
        }
        LoadBalancedConnection newSlavesConn = LoadBalancedConnectionProxy.createProxyInstance(new LoadbalanceConnectionUrl(this.slaveHosts, this.connectionUrl.getOriginalProperties()));
        newSlavesConn.setProxy(this.getProxy());
        newSlavesConn.setReadOnly(true);
        this.slavesConnection = newSlavesConn;
        return this.slavesConnection;
    }

    private synchronized boolean switchToMasterConnection() throws SQLException {
        if (this.masterConnection == null || this.masterConnection.isClosed()) {
            try {
                if (this.initializeMasterConnection() == null) {
                    return false;
                }
            } catch (SQLException e) {
                this.currentConnection = null;
                throw e;
            }
        }
        if (!this.isMasterConnection() && this.masterConnection != null) {
            this.syncSessionState(this.currentConnection, this.masterConnection, false);
            this.currentConnection = this.masterConnection;
        }
        return true;
    }

    private synchronized boolean switchToSlavesConnection() throws SQLException {
        if (this.slavesConnection == null || this.slavesConnection.isClosed()) {
            try {
                if (this.initializeSlavesConnection() == null) {
                    return false;
                }
            } catch (SQLException e) {
                this.currentConnection = null;
                throw e;
            }
        }
        if (!this.isSlavesConnection() && this.slavesConnection != null) {
            this.syncSessionState(this.currentConnection, this.slavesConnection, true);
            this.currentConnection = this.slavesConnection;
        }
        return true;
    }

    private boolean switchToSlavesConnectionIfNecessary() throws SQLException {
        if (this.currentConnection == null || this.isMasterConnection() && (this.readOnly || this.masterHosts.isEmpty() && this.currentConnection.isClosed()) || !this.isMasterConnection() && this.currentConnection.isClosed()) {
            return this.switchToSlavesConnection();
        }
        return false;
    }

    public synchronized JdbcConnection getCurrentConnection() {
        return this.currentConnection == null ? LoadBalancedConnectionProxy.getNullLoadBalancedConnectionInstance() : this.currentConnection;
    }

    public long getConnectionGroupId() {
        return this.connectionGroupID;
    }

    public synchronized JdbcConnection getMasterConnection() {
        return this.masterConnection;
    }

    public synchronized void promoteSlaveToMaster(String hostPortPair) throws SQLException {
        HostInfo host = this.getSlaveHost(hostPortPair);
        if (host == null) {
            return;
        }
        this.masterHosts.add(host);
        this.removeSlave(hostPortPair);
        if (this.masterConnection != null) {
            this.masterConnection.addHost(hostPortPair);
        }
        if (!this.readOnly && !this.isMasterConnection()) {
            this.switchToMasterConnection();
        }
    }

    public synchronized void removeMasterHost(String hostPortPair) throws SQLException {
        this.removeMasterHost(hostPortPair, true);
    }

    public synchronized void removeMasterHost(String hostPortPair, boolean waitUntilNotInUse) throws SQLException {
        this.removeMasterHost(hostPortPair, waitUntilNotInUse, false);
    }

    public synchronized void removeMasterHost(String hostPortPair, boolean waitUntilNotInUse, boolean isNowSlave) throws SQLException {
        HostInfo host = this.getMasterHost(hostPortPair);
        if (host == null) {
            return;
        }
        if (isNowSlave) {
            this.slaveHosts.add(host);
            this.resetReadFromMasterWhenNoSlaves();
        }
        this.masterHosts.remove(host);
        if (this.masterConnection == null || this.masterConnection.isClosed()) {
            this.masterConnection = null;
            return;
        }
        if (waitUntilNotInUse) {
            this.masterConnection.removeHostWhenNotInUse(hostPortPair);
        } else {
            this.masterConnection.removeHost(hostPortPair);
        }
        if (this.masterHosts.isEmpty()) {
            this.masterConnection.close();
            this.masterConnection = null;
            this.switchToSlavesConnectionIfNecessary();
        }
    }

    public boolean isHostMaster(String hostPortPair) {
        if (hostPortPair == null) {
            return false;
        }
        return this.masterHosts.stream().anyMatch(hi -> hostPortPair.equalsIgnoreCase(hi.getHostPortPair()));
    }

    public synchronized JdbcConnection getSlavesConnection() {
        return this.slavesConnection;
    }

    public synchronized void addSlaveHost(String hostPortPair) throws SQLException {
        if (this.isHostSlave(hostPortPair)) {
            return;
        }
        this.slaveHosts.add(this.getConnectionUrl().getSlaveHostOrSpawnIsolated(hostPortPair));
        this.resetReadFromMasterWhenNoSlaves();
        if (this.slavesConnection == null) {
            this.initializeSlavesConnection();
            this.switchToSlavesConnectionIfNecessary();
        } else {
            this.slavesConnection.addHost(hostPortPair);
        }
    }

    public synchronized void removeSlave(String hostPortPair) throws SQLException {
        this.removeSlave(hostPortPair, true);
    }

    public synchronized void removeSlave(String hostPortPair, boolean closeGently) throws SQLException {
        HostInfo host = this.getSlaveHost(hostPortPair);
        if (host == null) {
            return;
        }
        this.slaveHosts.remove(host);
        this.resetReadFromMasterWhenNoSlaves();
        if (this.slavesConnection == null || this.slavesConnection.isClosed()) {
            this.slavesConnection = null;
            return;
        }
        if (closeGently) {
            this.slavesConnection.removeHostWhenNotInUse(hostPortPair);
        } else {
            this.slavesConnection.removeHost(hostPortPair);
        }
        if (this.slaveHosts.isEmpty()) {
            this.slavesConnection.close();
            this.slavesConnection = null;
            this.switchToMasterConnection();
            if (this.isMasterConnection()) {
                this.currentConnection.setReadOnly(this.readOnly);
            }
        }
    }

    public boolean isHostSlave(String hostPortPair) {
        if (hostPortPair == null) {
            return false;
        }
        return this.slaveHosts.stream().anyMatch(hi -> hostPortPair.equalsIgnoreCase(hi.getHostPortPair()));
    }

    public synchronized void setReadOnly(boolean readOnly) throws SQLException {
        if (readOnly) {
            if (!this.isSlavesConnection() || this.currentConnection.isClosed()) {
                boolean switched = true;
                SQLException exceptionCaught = null;
                try {
                    switched = this.switchToSlavesConnection();
                } catch (SQLException e) {
                    switched = false;
                    exceptionCaught = e;
                }
                if (!switched && this.readFromMasterWhenNoSlaves && this.switchToMasterConnection()) {
                    exceptionCaught = null;
                }
                if (exceptionCaught != null) {
                    throw exceptionCaught;
                }
            }
        } else if (!this.isMasterConnection() || this.currentConnection.isClosed()) {
            boolean switched = true;
            SQLException exceptionCaught = null;
            try {
                switched = this.switchToMasterConnection();
            } catch (SQLException e) {
                switched = false;
                exceptionCaught = e;
            }
            if (!switched && this.switchToSlavesConnectionIfNecessary()) {
                exceptionCaught = null;
            }
            if (exceptionCaught != null) {
                throw exceptionCaught;
            }
        }
        this.readOnly = readOnly;
        if (this.readFromMasterWhenNoSlaves && this.isMasterConnection()) {
            this.currentConnection.setReadOnly(this.readOnly);
        }
    }

    public boolean isReadOnly() throws SQLException {
        return !this.isMasterConnection() || this.readOnly;
    }

    private void resetReadFromMasterWhenNoSlaves() {
        this.readFromMasterWhenNoSlaves = this.slaveHosts.isEmpty() || this.readFromMasterWhenNoSlavesOriginal;
    }

    private HostInfo getMasterHost(String hostPortPair) {
        return this.masterHosts.stream().filter(hi -> hostPortPair.equalsIgnoreCase(hi.getHostPortPair())).findFirst().orElse(null);
    }

    private HostInfo getSlaveHost(String hostPortPair) {
        return this.slaveHosts.stream().filter(hi -> hostPortPair.equalsIgnoreCase(hi.getHostPortPair())).findFirst().orElse(null);
    }

    private ReplicationConnectionUrl getConnectionUrl() {
        return (ReplicationConnectionUrl)this.connectionUrl;
    }
}

