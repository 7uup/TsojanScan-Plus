/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.jdbc.ha;

import com.mysql.cj.jdbc.JdbcConnection;
import java.lang.reflect.InvocationHandler;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface BalanceStrategy {
    public JdbcConnection pickConnection(InvocationHandler var1, List<String> var2, Map<String, JdbcConnection> var3, long[] var4, int var5) throws SQLException;
}

