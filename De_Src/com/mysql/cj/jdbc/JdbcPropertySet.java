/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.jdbc;

import com.mysql.cj.conf.PropertySet;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.util.Properties;

public interface JdbcPropertySet
extends PropertySet {
    public DriverPropertyInfo[] exposeAsDriverPropertyInfo(Properties var1, int var2) throws SQLException;
}

