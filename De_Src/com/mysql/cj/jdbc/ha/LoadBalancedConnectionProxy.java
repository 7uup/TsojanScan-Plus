/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.jdbc.ha;

import com.mysql.cj.Messages;
import com.mysql.cj.PingTarget;
import com.mysql.cj.conf.HostInfo;
import com.mysql.cj.conf.url.LoadbalanceConnectionUrl;
import com.mysql.cj.exceptions.CJCommunicationsException;
import com.mysql.cj.exceptions.CJException;
import com.mysql.cj.exceptions.ExceptionFactory;
import com.mysql.cj.interceptors.QueryInterceptor;
import com.mysql.cj.jdbc.ConnectionGroup;
import com.mysql.cj.jdbc.ConnectionGroupManager;
import com.mysql.cj.jdbc.ConnectionImpl;
import com.mysql.cj.jdbc.JdbcConnection;
import com.mysql.cj.jdbc.JdbcStatement;
import com.mysql.cj.jdbc.exceptions.SQLError;
import com.mysql.cj.jdbc.exceptions.SQLExceptionsMapping;
import com.mysql.cj.jdbc.ha.BalanceStrategy;
import com.mysql.cj.jdbc.ha.BestResponseTimeBalanceStrategy;
import com.mysql.cj.jdbc.ha.LoadBalanceExceptionChecker;
import com.mysql.cj.jdbc.ha.LoadBalancedAutoCommitInterceptor;
import com.mysql.cj.jdbc.ha.LoadBalancedConnection;
import com.mysql.cj.jdbc.ha.LoadBalancedMySQLConnection;
import com.mysql.cj.jdbc.ha.MultiHostConnectionProxy;
import com.mysql.cj.jdbc.ha.RandomBalanceStrategy;
import com.mysql.cj.jdbc.ha.ServerAffinityStrategy;
import com.mysql.cj.jdbc.ha.StandardLoadBalanceExceptionChecker;
import com.mysql.cj.util.Util;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;

public class LoadBalancedConnectionProxy
extends MultiHostConnectionProxy
implements PingTarget {
    private ConnectionGroup connectionGroup = null;
    private long connectionGroupProxyID = 0L;
    protected Map<String, ConnectionImpl> liveConnections;
    private Map<String, Integer> hostsToListIndexMap;
    private Map<ConnectionImpl, String> connectionsToHostsMap;
    private long totalPhysicalConnections = 0L;
    private long[] responseTimes;
    private int retriesAllDown;
    private BalanceStrategy balancer;
    private int globalBlacklistTimeout = 0;
    private static Map<String, Long> globalBlacklist = new HashMap<String, Long>();
    private int hostRemovalGracePeriod = 0;
    private Set<String> hostsToRemove = new HashSet<String>();
    private boolean inTransaction = false;
    private long transactionStartTime = 0L;
    private long transactionCount = 0L;
    private LoadBalanceExceptionChecker exceptionChecker;
    private static Class<?>[] INTERFACES_TO_PROXY = new Class[]{LoadBalancedConnection.class, JdbcConnection.class};
    private static LoadBalancedConnection nullLBConnectionInstance = null;

    public static LoadBalancedConnection createProxyInstance(LoadbalanceConnectionUrl connectionUrl) throws SQLException {
        LoadBalancedConnectionProxy connProxy = new LoadBalancedConnectionProxy(connectionUrl);
        return (LoadBalancedConnection)Proxy.newProxyInstance(LoadBalancedConnection.class.getClassLoader(), INTERFACES_TO_PROXY, connProxy);
    }

    public LoadBalancedConnectionProxy(LoadbalanceConnectionUrl connectionUrl) throws SQLException {
        List<HostInfo> hosts;
        Properties props = connectionUrl.getConnectionArgumentsAsProperties();
        String group = props.getProperty("loadBalanceConnectionGroup", null);
        boolean enableJMX = false;
        String enableJMXAsString = props.getProperty("ha.enableJMX", "false");
        try {
            enableJMX = Boolean.parseBoolean(enableJMXAsString);
        } catch (Exception e) {
            throw SQLError.createSQLException(Messages.getString("MultihostConnection.badValueForHaEnableJMX", new Object[]{enableJMXAsString}), "S1009", null);
        }
        if (group != null) {
            this.connectionGroup = ConnectionGroupManager.getConnectionGroupInstance(group);
            if (enableJMX) {
                ConnectionGroupManager.registerJmx();
            }
            this.connectionGroupProxyID = this.connectionGroup.registerConnectionProxy(this, connectionUrl.getHostInfoListAsHostPortPairs());
            hosts = connectionUrl.getHostInfoListFromHostPortPairs(this.connectionGroup.getInitialHosts());
        } else {
            hosts = connectionUrl.getHostsList();
        }
        int numHosts = this.initializeHostsSpecs(connectionUrl, hosts);
        this.liveConnections = new HashMap<String, ConnectionImpl>(numHosts);
        this.hostsToListIndexMap = new HashMap<String, Integer>(numHosts);
        for (int i = 0; i < numHosts; ++i) {
            this.hostsToListIndexMap.put(((HostInfo)this.hostsList.get(i)).getHostPortPair(), i);
        }
        this.connectionsToHostsMap = new HashMap<ConnectionImpl, String>(numHosts);
        this.responseTimes = new long[numHosts];
        String retriesAllDownAsString = props.getProperty("retriesAllDown", "120");
        try {
            this.retriesAllDown = Integer.parseInt(retriesAllDownAsString);
        } catch (NumberFormatException nfe) {
            throw SQLError.createSQLException(Messages.getString("LoadBalancedConnectionProxy.badValueForRetriesAllDown", new Object[]{retriesAllDownAsString}), "S1009", null);
        }
        String blacklistTimeoutAsString = props.getProperty("loadBalanceBlacklistTimeout", "0");
        try {
            this.globalBlacklistTimeout = Integer.parseInt(blacklistTimeoutAsString);
        } catch (NumberFormatException nfe) {
            throw SQLError.createSQLException(Messages.getString("LoadBalancedConnectionProxy.badValueForLoadBalanceBlacklistTimeout", new Object[]{blacklistTimeoutAsString}), "S1009", null);
        }
        String hostRemovalGracePeriodAsString = props.getProperty("loadBalanceHostRemovalGracePeriod", "15000");
        try {
            this.hostRemovalGracePeriod = Integer.parseInt(hostRemovalGracePeriodAsString);
        } catch (NumberFormatException nfe) {
            throw SQLError.createSQLException(Messages.getString("LoadBalancedConnectionProxy.badValueForLoadBalanceHostRemovalGracePeriod", new Object[]{hostRemovalGracePeriodAsString}), "S1009", null);
        }
        String strategy = props.getProperty("ha.loadBalanceStrategy", "random");
        try {
            switch (strategy) {
                case "random": {
                    this.balancer = new RandomBalanceStrategy();
                    break;
                }
                case "bestResponseTime": {
                    this.balancer = new BestResponseTimeBalanceStrategy();
                    break;
                }
                case "serverAffinity": {
                    this.balancer = new ServerAffinityStrategy(props.getProperty("serverAffinityOrder", null));
                    break;
                }
                default: {
                    this.balancer = (BalanceStrategy)Class.forName(strategy).newInstance();
                    break;
                }
            }
        } catch (Throwable t) {
            throw SQLError.createSQLException(Messages.getString("InvalidLoadBalanceStrategy", new Object[]{strategy}), "S1009", t, null);
        }
        String autoCommitSwapThresholdAsString = props.getProperty("loadBalanceAutoCommitStatementThreshold", "0");
        try {
            Integer.parseInt(autoCommitSwapThresholdAsString);
        } catch (NumberFormatException nfe) {
            throw SQLError.createSQLException(Messages.getString("LoadBalancedConnectionProxy.badValueForLoadBalanceAutoCommitStatementThreshold", new Object[]{autoCommitSwapThresholdAsString}), "S1009", null);
        }
        String autoCommitSwapRegex = props.getProperty("loadBalanceAutoCommitStatementRegex", "");
        if (!"".equals(autoCommitSwapRegex)) {
            try {
                "".matches(autoCommitSwapRegex);
            } catch (Exception e) {
                throw SQLError.createSQLException(Messages.getString("LoadBalancedConnectionProxy.badValueForLoadBalanceAutoCommitStatementRegex", new Object[]{autoCommitSwapRegex}), "S1009", null);
            }
        }
        try {
            String lbExceptionChecker = props.getProperty("loadBalanceExceptionChecker", StandardLoadBalanceExceptionChecker.class.getName());
            this.exceptionChecker = (LoadBalanceExceptionChecker)Util.getInstance(lbExceptionChecker, new Class[0], new Object[0], null, Messages.getString("InvalidLoadBalanceExceptionChecker"));
            this.exceptionChecker.init(props);
        } catch (CJException e) {
            throw SQLExceptionsMapping.translateException(e, null);
        }
        this.pickNewConnection();
    }

    @Override
    JdbcConnection getNewWrapperForThisAsConnection() throws SQLException {
        return new LoadBalancedMySQLConnection(this);
    }

    @Override
    protected void propagateProxyDown(JdbcConnection proxyConn) {
        for (JdbcConnection jdbcConnection : this.liveConnections.values()) {
            jdbcConnection.setProxy(proxyConn);
        }
    }

    @Deprecated
    public boolean shouldExceptionTriggerFailover(Throwable t) {
        return this.shouldExceptionTriggerConnectionSwitch(t);
    }

    @Override
    boolean shouldExceptionTriggerConnectionSwitch(Throwable t) {
        return t instanceof SQLException && this.exceptionChecker.shouldExceptionTriggerFailover(t);
    }

    @Override
    boolean isMasterConnection() {
        return true;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    synchronized void invalidateConnection(JdbcConnection conn) throws SQLException {
        super.invalidateConnection(conn);
        if (this.isGlobalBlacklistEnabled()) {
            this.addToGlobalBlacklist(this.connectionsToHostsMap.get(conn));
        }
        this.liveConnections.remove(this.connectionsToHostsMap.get(conn));
        String mappedHost = this.connectionsToHostsMap.remove(conn);
        if (mappedHost == null || !this.hostsToListIndexMap.containsKey(mappedHost)) return;
        int hostIndex = this.hostsToListIndexMap.get(mappedHost);
        long[] lArray = this.responseTimes;
        synchronized (this.responseTimes) {
            this.responseTimes[hostIndex] = 0L;
            // ** MonitorExit[var4_4] (shouldn't be in output)
            return;
        }
    }

    @Override
    public synchronized void pickNewConnection() throws SQLException {
        if (this.isClosed && this.closedExplicitly) {
            return;
        }
        List<String> hostPortList = Collections.unmodifiableList(this.hostsList.stream().map(hi -> hi.getHostPortPair()).collect(Collectors.toList()));
        if (this.currentConnection == null) {
            this.currentConnection = this.balancer.pickConnection(this, hostPortList, Collections.unmodifiableMap(this.liveConnections), (long[])this.responseTimes.clone(), this.retriesAllDown);
            return;
        }
        if (this.currentConnection.isClosed()) {
            this.invalidateCurrentConnection();
        }
        int pingTimeout = this.currentConnection.getPropertySet().getIntegerProperty("loadBalancePingTimeout").getValue();
        boolean pingBeforeReturn = this.currentConnection.getPropertySet().getBooleanProperty("loadBalanceValidateConnectionOnSwapServer").getValue();
        int hostsToTry = this.hostsList.size();
        for (int hostsTried = 0; hostsTried < hostsToTry; ++hostsTried) {
            ConnectionImpl newConn = null;
            try {
                newConn = (ConnectionImpl)this.balancer.pickConnection(this, hostPortList, Collections.unmodifiableMap(this.liveConnections), (long[])this.responseTimes.clone(), this.retriesAllDown);
                if (this.currentConnection != null) {
                    if (pingBeforeReturn) {
                        newConn.pingInternal(true, pingTimeout);
                    }
                    this.syncSessionState(this.currentConnection, newConn);
                }
                this.currentConnection = newConn;
                return;
            } catch (SQLException e) {
                if (!this.shouldExceptionTriggerConnectionSwitch(e) || newConn == null) continue;
                this.invalidateConnection(newConn);
                continue;
            }
        }
        this.isClosed = true;
        this.closedReason = "Connection closed after inability to pick valid new connection during load-balance.";
    }

    @Override
    public synchronized ConnectionImpl createConnectionForHost(HostInfo hostInfo) throws SQLException {
        ConnectionImpl conn = super.createConnectionForHost(hostInfo);
        this.liveConnections.put(hostInfo.getHostPortPair(), conn);
        this.connectionsToHostsMap.put(conn, hostInfo.getHostPortPair());
        ++this.totalPhysicalConnections;
        for (QueryInterceptor stmtInterceptor : conn.getQueryInterceptorsInstances()) {
            if (!(stmtInterceptor instanceof LoadBalancedAutoCommitInterceptor)) continue;
            ((LoadBalancedAutoCommitInterceptor)stmtInterceptor).resumeCounters();
            break;
        }
        return conn;
    }

    @Override
    void syncSessionState(JdbcConnection source2, JdbcConnection target, boolean readOnly) throws SQLException {
        LoadBalancedAutoCommitInterceptor lbAutoCommitStmtInterceptor = null;
        for (QueryInterceptor stmtInterceptor : target.getQueryInterceptorsInstances()) {
            if (!(stmtInterceptor instanceof LoadBalancedAutoCommitInterceptor)) continue;
            lbAutoCommitStmtInterceptor = (LoadBalancedAutoCommitInterceptor)stmtInterceptor;
            lbAutoCommitStmtInterceptor.pauseCounters();
            break;
        }
        super.syncSessionState(source2, target, readOnly);
        if (lbAutoCommitStmtInterceptor != null) {
            lbAutoCommitStmtInterceptor.resumeCounters();
        }
    }

    public synchronized ConnectionImpl createConnectionForHost(String hostPortPair) throws SQLException {
        for (HostInfo hi : this.hostsList) {
            if (!hi.getHostPortPair().equals(hostPortPair)) continue;
            return this.createConnectionForHost(hi);
        }
        return null;
    }

    private synchronized void closeAllConnections() {
        for (Connection connection : this.liveConnections.values()) {
            try {
                connection.close();
            } catch (SQLException sQLException) {}
        }
        if (!this.isClosed && this.connectionGroup != null) {
            this.connectionGroup.closeConnectionProxy(this);
        }
        this.liveConnections.clear();
        this.connectionsToHostsMap.clear();
    }

    @Override
    synchronized void doClose() {
        this.closeAllConnections();
    }

    @Override
    synchronized void doAbortInternal() {
        for (JdbcConnection jdbcConnection : this.liveConnections.values()) {
            try {
                jdbcConnection.abortInternal();
            } catch (SQLException sQLException) {}
        }
        if (!this.isClosed && this.connectionGroup != null) {
            this.connectionGroup.closeConnectionProxy(this);
        }
        this.liveConnections.clear();
        this.connectionsToHostsMap.clear();
    }

    @Override
    synchronized void doAbort(Executor executor) {
        for (Connection connection : this.liveConnections.values()) {
            try {
                connection.abort(executor);
            } catch (SQLException sQLException) {}
        }
        if (!this.isClosed && this.connectionGroup != null) {
            this.connectionGroup.closeConnectionProxy(this);
        }
        this.liveConnections.clear();
        this.connectionsToHostsMap.clear();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    @Override
    public synchronized Object invokeMore(Object proxy, Method method, Object[] args) throws Throwable {
        methodName = method.getName();
        if (!this.isClosed || this.allowedOnClosedConnection(method) || method.getExceptionTypes().length <= 0) ** GOTO lbl12
        if (!this.autoReconnect || this.closedExplicitly) {
            reason = "No operations allowed after connection closed.";
            if (this.closedReason != null) {
                reason = reason + " " + this.closedReason;
            }
        } else {
            block25: {
                this.currentConnection = null;
                this.pickNewConnection();
                this.isClosed = false;
                this.closedReason = null;
lbl12:
                // 2 sources

                if (!this.inTransaction) {
                    this.inTransaction = true;
                    this.transactionStartTime = System.nanoTime();
                    ++this.transactionCount;
                }
                result = null;
                try {
                    result = method.invoke(this.thisAsConnection, args);
                    if (result != null) {
                        if (result instanceof JdbcStatement) {
                            ((JdbcStatement)result).setPingTarget(this);
                        }
                        result = this.proxyIfReturnTypeIsJdbcInterface(method.getReturnType(), result);
                    }
                    if (!"commit".equals(methodName)) {
                        if ("rollback".equals(methodName) == false) return result;
                    }
                    this.inTransaction = false;
                    host = this.connectionsToHostsMap.get(this.currentConnection);
                    if (host == null) break block25;
                    var7_12 = this.responseTimes;
                } catch (InvocationTargetException e) {
                    block26: {
                        try {
                            this.dealWithInvocationException(e);
                            if (!"commit".equals(methodName)) {
                                if ("rollback".equals(methodName) == false) return result;
                            }
                            this.inTransaction = false;
                            host = this.connectionsToHostsMap.get(this.currentConnection);
                            if (host == null) break block26;
                            var7_13 = this.responseTimes;
                        } catch (Throwable var12_18) {
                            if (!"commit".equals(methodName)) {
                                if ("rollback".equals(methodName) == false) throw var12_18;
                            }
                            this.inTransaction = false;
                            host = this.connectionsToHostsMap.get(this.currentConnection);
                            if (host != null) {
                                var14_20 = this.responseTimes;
                                // MONITORENTER : this.responseTimes
                                hostIndex = this.hostsToListIndexMap.get(host);
                                if (hostIndex != null && hostIndex < this.responseTimes.length) {
                                    this.responseTimes[hostIndex.intValue()] = System.nanoTime() - this.transactionStartTime;
                                }
                                // MONITOREXIT : var14_20
                            }
                            this.pickNewConnection();
                            throw var12_18;
                        }
                        hostIndex = this.hostsToListIndexMap.get(host);
                        if (hostIndex != null && hostIndex < this.responseTimes.length) {
                            this.responseTimes[hostIndex.intValue()] = System.nanoTime() - this.transactionStartTime;
                        }
                        // MONITOREXIT : var7_13
                    }
                    this.pickNewConnection();
                    return result;
                }
                // MONITORENTER : this.responseTimes
                hostIndex = this.hostsToListIndexMap.get(host);
                if (hostIndex != null && hostIndex < this.responseTimes.length) {
                    this.responseTimes[hostIndex.intValue()] = System.nanoTime() - this.transactionStartTime;
                }
                // MONITOREXIT : var7_12
            }
            this.pickNewConnection();
            return result;
        }
        var6_7 = method.getExceptionTypes();
        var7_11 = var6_7.length;
        var8_14 = 0;
        while (var8_14 < var7_11) {
            excls = var6_7[var8_14];
            if (SQLException.class.isAssignableFrom(excls)) {
                throw SQLError.createSQLException(reason, "08003", null);
            }
            ++var8_14;
        }
        throw ExceptionFactory.createException(CJCommunicationsException.class, reason);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public synchronized void doPing() throws SQLException {
        SQLException se = null;
        boolean foundHost = false;
        int pingTimeout = this.currentConnection.getPropertySet().getIntegerProperty("loadBalancePingTimeout").getValue();
        LoadBalancedConnectionProxy loadBalancedConnectionProxy = this;
        synchronized (loadBalancedConnectionProxy) {
            for (HostInfo hi : this.hostsList) {
                String host = hi.getHostPortPair();
                ConnectionImpl conn = this.liveConnections.get(host);
                if (conn == null) continue;
                try {
                    if (pingTimeout == 0) {
                        conn.ping();
                    } else {
                        conn.pingInternal(true, pingTimeout);
                    }
                    foundHost = true;
                } catch (SQLException e) {
                    if (host.equals(this.connectionsToHostsMap.get(this.currentConnection))) {
                        this.closeAllConnections();
                        this.isClosed = true;
                        this.closedReason = "Connection closed because ping of current connection failed.";
                        throw e;
                    }
                    if (e.getMessage().equals(Messages.getString("Connection.exceededConnectionLifetime"))) {
                        if (se == null) {
                            se = e;
                        }
                    } else {
                        se = e;
                        if (this.isGlobalBlacklistEnabled()) {
                            this.addToGlobalBlacklist(host);
                        }
                    }
                    this.liveConnections.remove(this.connectionsToHostsMap.get(conn));
                }
            }
        }
        if (!foundHost) {
            this.closeAllConnections();
            this.isClosed = true;
            this.closedReason = "Connection closed due to inability to ping any active connections.";
            if (se != null) {
                throw se;
            }
            ((ConnectionImpl)this.currentConnection).throwConnectionClosedException();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void addToGlobalBlacklist(String host, long timeout2) {
        if (this.isGlobalBlacklistEnabled()) {
            Map<String, Long> map = globalBlacklist;
            synchronized (map) {
                globalBlacklist.put(host, timeout2);
            }
        }
    }

    public void addToGlobalBlacklist(String host) {
        this.addToGlobalBlacklist(host, System.currentTimeMillis() + (long)this.globalBlacklistTimeout);
    }

    public boolean isGlobalBlacklistEnabled() {
        return this.globalBlacklistTimeout > 0;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public synchronized Map<String, Long> getGlobalBlacklist() {
        if (!this.isGlobalBlacklistEnabled()) {
            if (this.hostsToRemove.isEmpty()) {
                return new HashMap<String, Long>(1);
            }
            HashMap<String, Long> fakedBlacklist = new HashMap<String, Long>();
            for (String h2 : this.hostsToRemove) {
                fakedBlacklist.put(h2, System.currentTimeMillis() + 5000L);
            }
            return fakedBlacklist;
        }
        HashMap<String, Long> blacklistClone = new HashMap<String, Long>(globalBlacklist.size());
        Map<String, Long> map = globalBlacklist;
        synchronized (map) {
            blacklistClone.putAll(globalBlacklist);
        }
        Set keys2 = blacklistClone.keySet();
        keys2.retainAll(this.hostsList.stream().map(hi -> hi.getHost()).collect(Collectors.toList()));
        Iterator i = keys2.iterator();
        while (i.hasNext()) {
            String host = (String)i.next();
            Long timeout2 = globalBlacklist.get(host);
            if (timeout2 == null || timeout2 >= System.currentTimeMillis()) continue;
            Map<String, Long> map2 = globalBlacklist;
            synchronized (map2) {
                globalBlacklist.remove(host);
            }
            i.remove();
        }
        if (keys2.size() == this.hostsList.size()) {
            return new HashMap<String, Long>(1);
        }
        return blacklistClone;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void removeHostWhenNotInUse(String hostPortPair) throws SQLException {
        if (this.hostRemovalGracePeriod <= 0) {
            this.removeHost(hostPortPair);
            return;
        }
        int timeBetweenChecks = this.hostRemovalGracePeriod > 1000 ? 1000 : this.hostRemovalGracePeriod;
        LoadBalancedConnectionProxy loadBalancedConnectionProxy = this;
        synchronized (loadBalancedConnectionProxy) {
            this.addToGlobalBlacklist(hostPortPair, System.currentTimeMillis() + (long)this.hostRemovalGracePeriod + (long)timeBetweenChecks);
            long cur = System.currentTimeMillis();
            while (System.currentTimeMillis() < cur + (long)this.hostRemovalGracePeriod) {
                this.hostsToRemove.add(hostPortPair);
                if (!hostPortPair.equals(this.currentConnection.getHostPortPair())) {
                    this.removeHost(hostPortPair);
                    return;
                }
                try {
                    Thread.sleep(timeBetweenChecks);
                } catch (InterruptedException interruptedException) {}
            }
        }
        this.removeHost(hostPortPair);
    }

    public synchronized void removeHost(String hostPortPair) throws SQLException {
        if (this.connectionGroup != null && this.connectionGroup.getInitialHosts().size() == 1 && this.connectionGroup.getInitialHosts().contains(hostPortPair)) {
            throw SQLError.createSQLException(Messages.getString("LoadBalancedConnectionProxy.0"), null);
        }
        this.hostsToRemove.add(hostPortPair);
        this.connectionsToHostsMap.remove(this.liveConnections.remove(hostPortPair));
        if (this.hostsToListIndexMap.remove(hostPortPair) != null) {
            long[] newResponseTimes = new long[this.responseTimes.length - 1];
            int newIdx = 0;
            for (HostInfo hostInfo : this.hostsList) {
                String host = hostInfo.getHostPortPair();
                if (this.hostsToRemove.contains(host)) continue;
                Integer idx = this.hostsToListIndexMap.get(host);
                if (idx != null && idx < this.responseTimes.length) {
                    newResponseTimes[newIdx] = this.responseTimes[idx];
                }
                this.hostsToListIndexMap.put(host, newIdx++);
            }
            this.responseTimes = newResponseTimes;
        }
        if (hostPortPair.equals(this.currentConnection.getHostPortPair())) {
            this.invalidateConnection(this.currentConnection);
            this.pickNewConnection();
        }
    }

    public synchronized boolean addHost(String hostPortPair) {
        if (this.hostsToListIndexMap.containsKey(hostPortPair)) {
            return false;
        }
        long[] newResponseTimes = new long[this.responseTimes.length + 1];
        System.arraycopy(this.responseTimes, 0, newResponseTimes, 0, this.responseTimes.length);
        this.responseTimes = newResponseTimes;
        if (this.hostsList.stream().noneMatch(hi -> hostPortPair.equals(hi.getHostPortPair()))) {
            this.hostsList.add(this.connectionUrl.getHostOrSpawnIsolated(hostPortPair));
        }
        this.hostsToListIndexMap.put(hostPortPair, this.responseTimes.length - 1);
        this.hostsToRemove.remove(hostPortPair);
        return true;
    }

    public synchronized boolean inTransaction() {
        return this.inTransaction;
    }

    public synchronized long getTransactionCount() {
        return this.transactionCount;
    }

    public synchronized long getActivePhysicalConnectionCount() {
        return this.liveConnections.size();
    }

    public synchronized long getTotalPhysicalConnectionCount() {
        return this.totalPhysicalConnections;
    }

    public synchronized long getConnectionGroupProxyID() {
        return this.connectionGroupProxyID;
    }

    public synchronized String getCurrentActiveHost() {
        String o;
        JdbcConnection c = this.currentConnection;
        if (c != null && (o = this.connectionsToHostsMap.get(c)) != null) {
            return o.toString();
        }
        return null;
    }

    public synchronized long getCurrentTransactionDuration() {
        if (this.inTransaction && this.transactionStartTime > 0L) {
            return System.nanoTime() - this.transactionStartTime;
        }
        return 0L;
    }

    static synchronized LoadBalancedConnection getNullLoadBalancedConnectionInstance() {
        if (nullLBConnectionInstance == null) {
            nullLBConnectionInstance = (LoadBalancedConnection)Proxy.newProxyInstance(LoadBalancedConnection.class.getClassLoader(), INTERFACES_TO_PROXY, new NullLoadBalancedConnectionProxy());
        }
        return nullLBConnectionInstance;
    }

    private static class NullLoadBalancedConnectionProxy
    implements InvocationHandler {
        @Override
        public Object invoke(Object proxy, Method method, Object[] args2) throws Throwable {
            Class<?>[] declaredException;
            SQLException exceptionToThrow = SQLError.createSQLException(Messages.getString("LoadBalancedConnectionProxy.unusableConnection"), "25000", 1000001, true, null);
            for (Class<?> declEx : declaredException = method.getExceptionTypes()) {
                if (!declEx.isAssignableFrom(exceptionToThrow.getClass())) continue;
                throw exceptionToThrow;
            }
            throw new IllegalStateException(exceptionToThrow.getMessage(), exceptionToThrow);
        }
    }
}

