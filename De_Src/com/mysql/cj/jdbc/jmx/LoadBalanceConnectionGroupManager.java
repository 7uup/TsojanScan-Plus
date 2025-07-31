/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.jdbc.jmx;

import com.mysql.cj.Messages;
import com.mysql.cj.jdbc.ConnectionGroupManager;
import com.mysql.cj.jdbc.exceptions.SQLError;
import com.mysql.cj.jdbc.jmx.LoadBalanceConnectionGroupManagerMBean;
import java.lang.management.ManagementFactory;
import java.sql.SQLException;
import javax.management.MBeanServer;
import javax.management.ObjectName;

public class LoadBalanceConnectionGroupManager
implements LoadBalanceConnectionGroupManagerMBean {
    private boolean isJmxRegistered = false;

    public synchronized void registerJmx() throws SQLException {
        if (this.isJmxRegistered) {
            return;
        }
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        try {
            ObjectName name = new ObjectName("com.mysql.cj.jdbc.jmx:type=LoadBalanceConnectionGroupManager");
            mbs.registerMBean(this, name);
            this.isJmxRegistered = true;
        } catch (Exception e) {
            throw SQLError.createSQLException(Messages.getString("LoadBalanceConnectionGroupManager.0"), null, e, null);
        }
    }

    @Override
    public void addHost(String group, String host, boolean forExisting) {
        try {
            ConnectionGroupManager.addHost(group, host, forExisting);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getActiveHostCount(String group) {
        return ConnectionGroupManager.getActiveHostCount(group);
    }

    @Override
    public long getActiveLogicalConnectionCount(String group) {
        return ConnectionGroupManager.getActiveLogicalConnectionCount(group);
    }

    @Override
    public long getActivePhysicalConnectionCount(String group) {
        return ConnectionGroupManager.getActivePhysicalConnectionCount(group);
    }

    @Override
    public int getTotalHostCount(String group) {
        return ConnectionGroupManager.getTotalHostCount(group);
    }

    @Override
    public long getTotalLogicalConnectionCount(String group) {
        return ConnectionGroupManager.getTotalLogicalConnectionCount(group);
    }

    @Override
    public long getTotalPhysicalConnectionCount(String group) {
        return ConnectionGroupManager.getTotalPhysicalConnectionCount(group);
    }

    @Override
    public long getTotalTransactionCount(String group) {
        return ConnectionGroupManager.getTotalTransactionCount(group);
    }

    @Override
    public void removeHost(String group, String host) throws SQLException {
        ConnectionGroupManager.removeHost(group, host);
    }

    @Override
    public String getActiveHostsList(String group) {
        return ConnectionGroupManager.getActiveHostLists(group);
    }

    @Override
    public String getRegisteredConnectionGroups() {
        return ConnectionGroupManager.getRegisteredConnectionGroups();
    }

    @Override
    public void stopNewConnectionsToHost(String group, String host) throws SQLException {
        ConnectionGroupManager.removeHost(group, host);
    }
}

