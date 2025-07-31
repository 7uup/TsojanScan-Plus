/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.jdbc.jmx;

import java.sql.SQLException;

public interface LoadBalanceConnectionGroupManagerMBean {
    public int getActiveHostCount(String var1);

    public int getTotalHostCount(String var1);

    public long getTotalLogicalConnectionCount(String var1);

    public long getActiveLogicalConnectionCount(String var1);

    public long getActivePhysicalConnectionCount(String var1);

    public long getTotalPhysicalConnectionCount(String var1);

    public long getTotalTransactionCount(String var1);

    public void removeHost(String var1, String var2) throws SQLException;

    public void stopNewConnectionsToHost(String var1, String var2) throws SQLException;

    public void addHost(String var1, String var2, boolean var3);

    public String getActiveHostsList(String var1);

    public String getRegisteredConnectionGroups();
}

