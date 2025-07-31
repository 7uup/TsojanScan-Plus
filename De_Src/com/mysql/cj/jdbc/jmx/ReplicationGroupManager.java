/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.jdbc.jmx;

import com.mysql.cj.Messages;
import com.mysql.cj.jdbc.exceptions.SQLError;
import com.mysql.cj.jdbc.ha.ReplicationConnectionGroup;
import com.mysql.cj.jdbc.ha.ReplicationConnectionGroupManager;
import com.mysql.cj.jdbc.jmx.ReplicationGroupManagerMBean;
import java.lang.management.ManagementFactory;
import java.sql.SQLException;
import javax.management.MBeanServer;
import javax.management.ObjectName;

public class ReplicationGroupManager
implements ReplicationGroupManagerMBean {
    private boolean isJmxRegistered = false;

    public synchronized void registerJmx() throws SQLException {
        if (this.isJmxRegistered) {
            return;
        }
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        try {
            ObjectName name = new ObjectName("com.mysql.cj.jdbc.jmx:type=ReplicationGroupManager");
            mbs.registerMBean(this, name);
            this.isJmxRegistered = true;
        } catch (Exception e) {
            throw SQLError.createSQLException(Messages.getString("ReplicationGroupManager.0"), null, e, null);
        }
    }

    @Override
    public void addSlaveHost(String groupFilter, String host) throws SQLException {
        ReplicationConnectionGroupManager.addSlaveHost(groupFilter, host);
    }

    @Override
    public void removeSlaveHost(String groupFilter, String host) throws SQLException {
        ReplicationConnectionGroupManager.removeSlaveHost(groupFilter, host);
    }

    @Override
    public void promoteSlaveToMaster(String groupFilter, String host) throws SQLException {
        ReplicationConnectionGroupManager.promoteSlaveToMaster(groupFilter, host);
    }

    @Override
    public void removeMasterHost(String groupFilter, String host) throws SQLException {
        ReplicationConnectionGroupManager.removeMasterHost(groupFilter, host);
    }

    @Override
    public String getMasterHostsList(String group) {
        StringBuilder sb = new StringBuilder("");
        boolean found = false;
        for (String host : ReplicationConnectionGroupManager.getMasterHosts(group)) {
            if (found) {
                sb.append(",");
            }
            found = true;
            sb.append(host);
        }
        return sb.toString();
    }

    @Override
    public String getSlaveHostsList(String group) {
        StringBuilder sb = new StringBuilder("");
        boolean found = false;
        for (String host : ReplicationConnectionGroupManager.getSlaveHosts(group)) {
            if (found) {
                sb.append(",");
            }
            found = true;
            sb.append(host);
        }
        return sb.toString();
    }

    @Override
    public String getRegisteredConnectionGroups() {
        StringBuilder sb = new StringBuilder("");
        boolean found = false;
        for (ReplicationConnectionGroup group : ReplicationConnectionGroupManager.getGroupsMatching(null)) {
            if (found) {
                sb.append(",");
            }
            found = true;
            sb.append(group.getGroupName());
        }
        return sb.toString();
    }

    @Override
    public int getActiveMasterHostCount(String group) {
        return ReplicationConnectionGroupManager.getMasterHosts(group).size();
    }

    @Override
    public int getActiveSlaveHostCount(String group) {
        return ReplicationConnectionGroupManager.getSlaveHosts(group).size();
    }

    @Override
    public int getSlavePromotionCount(String group) {
        return ReplicationConnectionGroupManager.getNumberOfMasterPromotion(group);
    }

    @Override
    public long getTotalLogicalConnectionCount(String group) {
        return ReplicationConnectionGroupManager.getTotalConnectionCount(group);
    }

    @Override
    public long getActiveLogicalConnectionCount(String group) {
        return ReplicationConnectionGroupManager.getActiveConnectionCount(group);
    }
}

