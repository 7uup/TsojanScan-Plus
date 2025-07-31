/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.xdevapi;

import com.mysql.cj.xdevapi.Type;

public interface Column {
    public String getSchemaName();

    public String getTableName();

    public String getTableLabel();

    public String getColumnName();

    public String getColumnLabel();

    public Type getType();

    public long getLength();

    public int getFractionalDigits();

    public boolean isNumberSigned();

    public String getCollationName();

    public String getCharacterSetName();

    public boolean isPadded();

    public boolean isNullable();

    public boolean isAutoIncrement();

    public boolean isPrimaryKey();

    public boolean isUniqueKey();

    public boolean isPartKey();
}

