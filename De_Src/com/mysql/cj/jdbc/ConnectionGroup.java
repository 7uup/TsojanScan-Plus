/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.jdbc;

import com.mysql.cj.Messages;
import com.mysql.cj.jdbc.exceptions.SQLError;
import com.mysql.cj.jdbc.ha.LoadBalancedConnectionProxy;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ConnectionGroup {
    private String groupName;
    private long connections = 0L;
    private long activeConnections = 0L;
    private HashMap<Long, LoadBalancedConnectionProxy> connectionProxies = new HashMap();
    private Set<String> hostList = new HashSet<String>();
    private boolean isInitialized = false;
    private long closedProxyTotalPhysicalConnections = 0L;
    private long closedProxyTotalTransactions = 0L;
    private int activeHosts = 0;
    private Set<String> closedHosts = new HashSet<String>();

    ConnectionGroup(String groupName) {
        this.groupName = groupName;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public long registerConnectionProxy(LoadBalancedConnectionProxy proxy, List<String> localHostList) {
        long currentConnectionId;
        ConnectionGroup connectionGroup = this;
        synchronized (connectionGroup) {
            if (!this.isInitialized) {
                this.hostList.addAll(localHostList);
                this.isInitialized = true;
                this.activeHosts = localHostList.size();
            }
            currentConnectionId = ++this.connections;
            this.connectionProxies.put(currentConnectionId, proxy);
        }
        ++this.activeConnections;
        return currentConnectionId;
    }

    public String getGroupName() {
        return this.groupName;
    }

    public Collection<String> getInitialHosts() {
        return this.hostList;
    }

    public int getActiveHostCount() {
        return this.activeHosts;
    }

    public Collection<String> getClosedHosts() {
        return this.closedHosts;
    }

    public long getTotalLogicalConnectionCount() {
        return this.connections;
    }

    public long getActiveLogicalConnectionCount() {
        return this.activeConnections;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public long getActivePhysicalConnectionCount() {
        long result = 0L;
        HashMap<Long, LoadBalancedConnectionProxy> proxyMap = new HashMap<Long, LoadBalancedConnectionProxy>();
        HashMap<Long, LoadBalancedConnectionProxy> hashMap = this.connectionProxies;
        synchronized (hashMap) {
            proxyMap.putAll(this.connectionProxies);
        }
        for (LoadBalancedConnectionProxy proxy : proxyMap.values()) {
            result += proxy.getActivePhysicalConnectionCount();
        }
        return result;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public long getTotalPhysicalConnectionCount() {
        long allConnections = this.closedProxyTotalPhysicalConnections;
        HashMap<Long, LoadBalancedConnectionProxy> proxyMap = new HashMap<Long, LoadBalancedConnectionProxy>();
        HashMap<Long, LoadBalancedConnectionProxy> hashMap = this.connectionProxies;
        synchronized (hashMap) {
            proxyMap.putAll(this.connectionProxies);
        }
        for (LoadBalancedConnectionProxy proxy : proxyMap.values()) {
            allConnections += proxy.getTotalPhysicalConnectionCount();
        }
        return allConnections;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public long getTotalTransactionCount() {
        long transactions = this.closedProxyTotalTransactions;
        HashMap<Long, LoadBalancedConnectionProxy> proxyMap = new HashMap<Long, LoadBalancedConnectionProxy>();
        HashMap<Long, LoadBalancedConnectionProxy> hashMap = this.connectionProxies;
        synchronized (hashMap) {
            proxyMap.putAll(this.connectionProxies);
        }
        for (LoadBalancedConnectionProxy proxy : proxyMap.values()) {
            transactions += proxy.getTransactionCount();
        }
        return transactions;
    }

    public void closeConnectionProxy(LoadBalancedConnectionProxy proxy) {
        --this.activeConnections;
        this.connectionProxies.remove(proxy.getConnectionGroupProxyID());
        this.closedProxyTotalPhysicalConnections += proxy.getTotalPhysicalConnectionCount();
        this.closedProxyTotalTransactions += proxy.getTransactionCount();
    }

    public void removeHost(String hostPortPair) throws SQLException {
        this.removeHost(hostPortPair, false);
    }

    public void removeHost(String hostPortPair, boolean removeExisting) throws SQLException {
        this.removeHost(hostPortPair, removeExisting, true);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public synchronized void removeHost(String hostPortPair, boolean removeExisting, boolean waitForGracefulFailover) throws SQLException {
        if (this.activeHosts == 1) {
            throw SQLError.createSQLException(Messages.getString("ConnectionGroup.0"), null);
        }
        if (this.hostList.remove(hostPortPair)) {
            --this.activeHosts;
        } else {
            throw SQLError.createSQLException(Messages.getString("ConnectionGroup.1", new Object[]{hostPortPair}), null);
        }
        if (removeExisting) {
            HashMap<Long, LoadBalancedConnectionProxy> proxyMap = new HashMap<Long, LoadBalancedConnectionProxy>();
            HashMap<Long, LoadBalancedConnectionProxy> hashMap = this.connectionProxies;
            synchronized (hashMap) {
                proxyMap.putAll(this.connectionProxies);
            }
            for (LoadBalancedConnectionProxy proxy : proxyMap.values()) {
                if (waitForGracefulFailover) {
                    proxy.removeHostWhenNotInUse(hostPortPair);
                    continue;
                }
                proxy.removeHost(hostPortPair);
            }
        }
        this.closedHosts.add(hostPortPair);
    }

    public void addHost(String hostPortPair) {
        this.addHost(hostPortPair, false);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void addHost(String hostPortPair, boolean forExisting) {
        ConnectionGroup connectionGroup = this;
        synchronized (connectionGroup) {
            if (this.hostList.add(hostPortPair)) {
                ++this.activeHosts;
            }
        }
        if (!forExisting) {
            return;
        }
        HashMap<Long, LoadBalancedConnectionProxy> proxyMap = new HashMap<Long, LoadBalancedConnectionProxy>();
        HashMap<Long, LoadBalancedConnectionProxy> hashMap = this.connectionProxies;
        synchronized (hashMap) {
            proxyMap.putAll(this.connectionProxies);
        }
        for (LoadBalancedConnectionProxy proxy : proxyMap.values()) {
            proxy.addHost(hostPortPair);
        }
    }
}

