/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.jdbc;

import java.sql.Connection;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.util.Properties;

public interface ClientInfoProvider {
    public void initialize(Connection var1, Properties var2) throws SQLException;

    public void destroy() throws SQLException;

    public Properties getClientInfo(Connection var1) throws SQLException;

    public String getClientInfo(Connection var1, String var2) throws SQLException;

    public void setClientInfo(Connection var1, Properties var2) throws SQLClientInfoException;

    public void setClientInfo(Connection var1, String var2, String var3) throws SQLClientInfoException;
}

