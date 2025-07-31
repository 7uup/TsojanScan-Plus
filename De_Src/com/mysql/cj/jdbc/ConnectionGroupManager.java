/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.jdbc;

import com.mysql.cj.jdbc.ConnectionGroup;
import com.mysql.cj.jdbc.jmx.LoadBalanceConnectionGroupManager;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

public class ConnectionGroupManager {
    private static HashMap<String, ConnectionGroup> GROUP_MAP = new HashMap();
    private static LoadBalanceConnectionGroupManager mbean = new LoadBalanceConnectionGroupManager();
    private static boolean hasRegisteredJmx = false;

    public static synchronized ConnectionGroup getConnectionGroupInstance(String groupName) {
        if (GROUP_MAP.containsKey(groupName)) {
            return GROUP_MAP.get(groupName);
        }
        ConnectionGroup group = new ConnectionGroup(groupName);
        GROUP_MAP.put(groupName, group);
        return group;
    }

    public static void registerJmx() throws SQLException {
        if (hasRegisteredJmx) {
            return;
        }
        mbean.registerJmx();
        hasRegisteredJmx = true;
    }

    public static ConnectionGroup getConnectionGroup(String groupName) {
        return GROUP_MAP.get(groupName);
    }

    private static Collection<ConnectionGroup> getGroupsMatching(String group) {
        if (group == null || group.equals("")) {
            HashSet<ConnectionGroup> s2 = new HashSet<ConnectionGroup>();
            s2.addAll(GROUP_MAP.values());
            return s2;
        }
        HashSet<ConnectionGroup> s3 = new HashSet<ConnectionGroup>();
        ConnectionGroup o = GROUP_MAP.get(group);
        if (o != null) {
            s3.add(o);
        }
        return s3;
    }

    public static void addHost(String group, String hostPortPair, boolean forExisting) {
        Collection<ConnectionGroup> s2 = ConnectionGroupManager.getGroupsMatching(group);
        for (ConnectionGroup cg : s2) {
            cg.addHost(hostPortPair, forExisting);
        }
    }

    public static int getActiveHostCount(String group) {
        HashSet<String> active = new HashSet<String>();
        Collection<ConnectionGroup> s2 = ConnectionGroupManager.getGroupsMatching(group);
        for (ConnectionGroup cg : s2) {
            active.addAll(cg.getInitialHosts());
        }
        return active.size();
    }

    public static long getActiveLogicalConnectionCount(String group) {
        int count = 0;
        Collection<ConnectionGroup> s2 = ConnectionGroupManager.getGroupsMatching(group);
        for (ConnectionGroup cg : s2) {
            count = (int)((long)count + cg.getActiveLogicalConnectionCount());
        }
        return count;
    }

    public static long getActivePhysicalConnectionCount(String group) {
        int count = 0;
        Collection<ConnectionGroup> s2 = ConnectionGroupManager.getGroupsMatching(group);
        for (ConnectionGroup cg : s2) {
            count = (int)((long)count + cg.getActivePhysicalConnectionCount());
        }
        return count;
    }

    public static int getTotalHostCount(String group) {
        Collection<ConnectionGroup> s2 = ConnectionGroupManager.getGroupsMatching(group);
        HashSet<String> hosts = new HashSet<String>();
        for (ConnectionGroup cg : s2) {
            hosts.addAll(cg.getInitialHosts());
            hosts.addAll(cg.getClosedHosts());
        }
        return hosts.size();
    }

    public static long getTotalLogicalConnectionCount(String group) {
        long count = 0L;
        Collection<ConnectionGroup> s2 = ConnectionGroupManager.getGroupsMatching(group);
        for (ConnectionGroup cg : s2) {
            count += cg.getTotalLogicalConnectionCount();
        }
        return count;
    }

    public static long getTotalPhysicalConnectionCount(String group) {
        long count = 0L;
        Collection<ConnectionGroup> s2 = ConnectionGroupManager.getGroupsMatching(group);
        for (ConnectionGroup cg : s2) {
            count += cg.getTotalPhysicalConnectionCount();
        }
        return count;
    }

    public static long getTotalTransactionCount(String group) {
        long count = 0L;
        Collection<ConnectionGroup> s2 = ConnectionGroupManager.getGroupsMatching(group);
        for (ConnectionGroup cg : s2) {
            count += cg.getTotalTransactionCount();
        }
        return count;
    }

    public static void removeHost(String group, String hostPortPair) throws SQLException {
        ConnectionGroupManager.removeHost(group, hostPortPair, false);
    }

    public static void removeHost(String group, String host, boolean removeExisting) throws SQLException {
        Collection<ConnectionGroup> s2 = ConnectionGroupManager.getGroupsMatching(group);
        for (ConnectionGroup cg : s2) {
            cg.removeHost(host, removeExisting);
        }
    }

    public static String getActiveHostLists(String group) {
        Collection<ConnectionGroup> s2 = ConnectionGroupManager.getGroupsMatching(group);
        HashMap<String, Integer> hosts = new HashMap<String, Integer>();
        for (ConnectionGroup cg : s2) {
            Collection<String> l = cg.getInitialHosts();
            for (String host : l) {
                Integer o = (Integer)hosts.get(host);
                o = o == null ? Integer.valueOf(1) : Integer.valueOf(o + 1);
                hosts.put(host, o);
            }
        }
        StringBuilder sb = new StringBuilder();
        String sep = "";
        for (String host : hosts.keySet()) {
            sb.append(sep);
            sb.append(host);
            sb.append('(');
            sb.append(hosts.get(host));
            sb.append(')');
            sep = ",";
        }
        return sb.toString();
    }

    public static String getRegisteredConnectionGroups() {
        Collection<ConnectionGroup> s2 = ConnectionGroupManager.getGroupsMatching(null);
        StringBuilder sb = new StringBuilder();
        String sep = "";
        for (ConnectionGroup cg : s2) {
            String group = cg.getGroupName();
            sb.append(sep);
            sb.append(group);
            sep = ",";
        }
        return sb.toString();
    }
}

