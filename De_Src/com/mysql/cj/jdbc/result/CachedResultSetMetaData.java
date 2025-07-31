/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.jdbc.result;

import com.mysql.cj.protocol.ColumnDefinition;
import java.sql.ResultSetMetaData;

public interface CachedResultSetMetaData
extends ColumnDefinition {
    public ResultSetMetaData getMetadata();

    public void setMetadata(ResultSetMetaData var1);
}

