/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.protocol;

import com.mysql.cj.protocol.ProtocolEntity;
import com.mysql.cj.result.Field;
import java.util.Map;

public interface ColumnDefinition
extends ProtocolEntity {
    public Field[] getFields();

    public void setFields(Field[] var1);

    public void buildIndexMapping();

    public boolean hasBuiltIndexMapping();

    public Map<String, Integer> getColumnLabelToIndex();

    public void setColumnLabelToIndex(Map<String, Integer> var1);

    public Map<String, Integer> getFullColumnNameToIndex();

    public void setFullColumnNameToIndex(Map<String, Integer> var1);

    public Map<String, Integer> getColumnNameToIndex();

    public void setColumnNameToIndex(Map<String, Integer> var1);

    public Map<String, Integer> getColumnToIndexCache();

    public void setColumnToIndexCache(Map<String, Integer> var1);

    public void initializeFrom(ColumnDefinition var1);

    public void exportTo(ColumnDefinition var1);

    public int findColumn(String var1, boolean var2, int var3);

    public boolean hasLargeFields();
}

