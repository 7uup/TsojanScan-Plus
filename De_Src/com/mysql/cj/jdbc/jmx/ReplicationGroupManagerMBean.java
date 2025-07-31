/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.jdbc.jmx;

import java.sql.SQLException;

public interface ReplicationGroupManagerMBean {
    public void addSlaveHost(String var1, String var2) throws SQLException;

    public void removeSlaveHost(String var1, String var2) throws SQLException;

    public void promoteSlaveToMaster(String var1, String var2) throws SQLException;

    public void removeMasterHost(String var1, String var2) throws SQLException;

    public String getMasterHostsList(String var1);

    public String getSlaveHostsList(String var1);

    public String getRegisteredConnectionGroups();

    public int getActiveMasterHostCount(String var1);

    public int getActiveSlaveHostCount(String var1);

    public int getSlavePromotionCount(String var1);

    public long getTotalLogicalConnectionCount(String var1);

    public long getActiveLogicalConnectionCount(String var1);
}

