/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.jdbc.ha;

import com.mysql.cj.jdbc.ConnectionImpl;
import com.mysql.cj.jdbc.JdbcConnection;
import com.mysql.cj.jdbc.ha.BalanceStrategy;
import com.mysql.cj.jdbc.ha.LoadBalancedConnectionProxy;
import java.lang.reflect.InvocationHandler;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class SequentialBalanceStrategy
implements BalanceStrategy {
    private int currentHostIndex = -1;

    @Override
    public ConnectionImpl pickConnection(InvocationHandler proxy, List<String> configuredHosts, Map<String, JdbcConnection> liveConnections, long[] responseTimes, int numRetries) throws SQLException {
        int numHosts = configuredHosts.size();
        SQLException ex = null;
        Map<String, Long> blackList = ((LoadBalancedConnectionProxy)proxy).getGlobalBlacklist();
        int attempts = 0;
        while (attempts < numRetries) {
            if (numHosts == 1) {
                this.currentHostIndex = 0;
            } else if (this.currentHostIndex == -1) {
                int random;
                int i;
                for (i = random = (int)Math.floor(Math.random() * (double)numHosts); i < numHosts; ++i) {
                    if (blackList.containsKey(configuredHosts.get(i))) continue;
                    this.currentHostIndex = i;
                    break;
                }
                if (this.currentHostIndex == -1) {
                    for (i = 0; i < random; ++i) {
                        if (blackList.containsKey(configuredHosts.get(i))) continue;
                        this.currentHostIndex = i;
                        break;
                    }
                }
                if (this.currentHostIndex == -1) {
                    blackList = ((LoadBalancedConnectionProxy)proxy).getGlobalBlacklist();
                    try {
                        Thread.sleep(250L);
                    } catch (InterruptedException i2) {}
                    continue;
                }
            } else {
                int i;
                boolean foundGoodHost = false;
                for (i = this.currentHostIndex + 1; i < numHosts; ++i) {
                    if (blackList.containsKey(configuredHosts.get(i))) continue;
                    this.currentHostIndex = i;
                    foundGoodHost = true;
                    break;
                }
                if (!foundGoodHost) {
                    for (i = 0; i < this.currentHostIndex; ++i) {
                        if (blackList.containsKey(configuredHosts.get(i))) continue;
                        this.currentHostIndex = i;
                        foundGoodHost = true;
                        break;
                    }
                }
                if (!foundGoodHost) {
                    blackList = ((LoadBalancedConnectionProxy)proxy).getGlobalBlacklist();
                    try {
                        Thread.sleep(250L);
                    } catch (InterruptedException interruptedException) {}
                    continue;
                }
            }
            String hostPortSpec = configuredHosts.get(this.currentHostIndex);
            ConnectionImpl conn = (ConnectionImpl)liveConnections.get(hostPortSpec);
            if (conn == null) {
                try {
                    conn = ((LoadBalancedConnectionProxy)proxy).createConnectionForHost(hostPortSpec);
                } catch (SQLException sqlEx) {
                    ex = sqlEx;
                    if (((LoadBalancedConnectionProxy)proxy).shouldExceptionTriggerConnectionSwitch(sqlEx)) {
                        ((LoadBalancedConnectionProxy)proxy).addToGlobalBlacklist(hostPortSpec);
                        try {
                            Thread.sleep(250L);
                        } catch (InterruptedException interruptedException) {}
                        continue;
                    }
                    throw sqlEx;
                }
            }
            return conn;
        }
        if (ex != null) {
            throw ex;
        }
        return null;
    }
}

