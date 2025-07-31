/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.jdbc.ha;

import com.mysql.cj.jdbc.JdbcConnection;
import java.sql.SQLException;

public interface ReplicationConnection
extends JdbcConnection {
    public long getConnectionGroupId();

    public JdbcConnection getCurrentConnection();

    public JdbcConnection getMasterConnection();

    public void promoteSlaveToMaster(String var1) throws SQLException;

    public void removeMasterHost(String var1) throws SQLException;

    public void removeMasterHost(String var1, boolean var2) throws SQLException;

    public boolean isHostMaster(String var1);

    public JdbcConnection getSlavesConnection();

    public void addSlaveHost(String var1) throws SQLException;

    public void removeSlave(String var1) throws SQLException;

    public void removeSlave(String var1, boolean var2) throws SQLException;

    public boolean isHostSlave(String var1);
}

