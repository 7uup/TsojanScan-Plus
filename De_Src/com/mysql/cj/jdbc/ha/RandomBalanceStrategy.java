/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.jdbc.ha;

import com.mysql.cj.Messages;
import com.mysql.cj.jdbc.ConnectionImpl;
import com.mysql.cj.jdbc.JdbcConnection;
import com.mysql.cj.jdbc.exceptions.SQLError;
import com.mysql.cj.jdbc.ha.BalanceStrategy;
import com.mysql.cj.jdbc.ha.LoadBalancedConnectionProxy;
import java.lang.reflect.InvocationHandler;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RandomBalanceStrategy
implements BalanceStrategy {
    @Override
    public ConnectionImpl pickConnection(InvocationHandler proxy, List<String> configuredHosts, Map<String, JdbcConnection> liveConnections, long[] responseTimes, int numRetries) throws SQLException {
        int numHosts = configuredHosts.size();
        SQLException ex = null;
        ArrayList<String> whiteList = new ArrayList<String>(numHosts);
        whiteList.addAll(configuredHosts);
        Map<String, Long> blackList = ((LoadBalancedConnectionProxy)proxy).getGlobalBlacklist();
        whiteList.removeAll(blackList.keySet());
        Map<String, Integer> whiteListMap = this.getArrayIndexMap(whiteList);
        int attempts = 0;
        while (attempts < numRetries) {
            int random = (int)Math.floor(Math.random() * (double)whiteList.size());
            if (whiteList.size() == 0) {
                throw SQLError.createSQLException(Messages.getString("RandomBalanceStrategy.0"), null);
            }
            String hostPortSpec = (String)whiteList.get(random);
            ConnectionImpl conn = (ConnectionImpl)liveConnections.get(hostPortSpec);
            if (conn == null) {
                try {
                    conn = ((LoadBalancedConnectionProxy)proxy).createConnectionForHost(hostPortSpec);
                } catch (SQLException sqlEx) {
                    ex = sqlEx;
                    if (((LoadBalancedConnectionProxy)proxy).shouldExceptionTriggerConnectionSwitch(sqlEx)) {
                        Integer whiteListIndex = whiteListMap.get(hostPortSpec);
                        if (whiteListIndex != null) {
                            whiteList.remove(whiteListIndex);
                            whiteListMap = this.getArrayIndexMap(whiteList);
                        }
                        ((LoadBalancedConnectionProxy)proxy).addToGlobalBlacklist(hostPortSpec);
                        if (whiteList.size() != 0) continue;
                        ++attempts;
                        try {
                            Thread.sleep(250L);
                        } catch (InterruptedException interruptedException) {
                            // empty catch block
                        }
                        whiteListMap = new HashMap<String, Integer>(numHosts);
                        whiteList.addAll(configuredHosts);
                        blackList = ((LoadBalancedConnectionProxy)proxy).getGlobalBlacklist();
                        whiteList.removeAll(blackList.keySet());
                        whiteListMap = this.getArrayIndexMap(whiteList);
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

    private Map<String, Integer> getArrayIndexMap(List<String> l) {
        HashMap<String, Integer> m3 = new HashMap<String, Integer>(l.size());
        for (int i = 0; i < l.size(); ++i) {
            m3.put(l.get(i), i);
        }
        return m3;
    }
}

