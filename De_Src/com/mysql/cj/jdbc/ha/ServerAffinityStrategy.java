/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.jdbc.ha;

import com.mysql.cj.jdbc.ConnectionImpl;
import com.mysql.cj.jdbc.JdbcConnection;
import com.mysql.cj.jdbc.ha.LoadBalancedConnectionProxy;
import com.mysql.cj.jdbc.ha.RandomBalanceStrategy;
import com.mysql.cj.util.StringUtils;
import java.lang.reflect.InvocationHandler;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class ServerAffinityStrategy
extends RandomBalanceStrategy {
    public String[] affinityOrderedServers = null;

    public ServerAffinityStrategy(String affinityOrdervers) {
        if (!StringUtils.isNullOrEmpty(affinityOrdervers)) {
            this.affinityOrderedServers = affinityOrdervers.split(",");
        }
    }

    @Override
    public ConnectionImpl pickConnection(InvocationHandler proxy, List<String> configuredHosts, Map<String, JdbcConnection> liveConnections, long[] responseTimes, int numRetries) throws SQLException {
        if (this.affinityOrderedServers == null) {
            return super.pickConnection(proxy, (List)configuredHosts, (Map)liveConnections, responseTimes, numRetries);
        }
        Map<String, Long> blackList = ((LoadBalancedConnectionProxy)proxy).getGlobalBlacklist();
        for (String host : this.affinityOrderedServers) {
            if (!configuredHosts.contains(host) || blackList.containsKey(host)) continue;
            ConnectionImpl conn = (ConnectionImpl)liveConnections.get(host);
            if (conn != null) {
                return conn;
            }
            try {
                conn = ((LoadBalancedConnectionProxy)proxy).createConnectionForHost(host);
                return conn;
            } catch (SQLException sqlEx) {
                if (!((LoadBalancedConnectionProxy)proxy).shouldExceptionTriggerConnectionSwitch(sqlEx)) continue;
                ((LoadBalancedConnectionProxy)proxy).addToGlobalBlacklist(host);
            }
        }
        return super.pickConnection(proxy, (List)configuredHosts, (Map)liveConnections, responseTimes, numRetries);
    }
}

