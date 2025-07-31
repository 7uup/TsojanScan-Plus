/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj;

public interface DataStoreMetadata {
    public boolean schemaExists(String var1);

    public boolean tableExists(String var1, String var2);

    public long getTableRowCount(String var1, String var2);
}

