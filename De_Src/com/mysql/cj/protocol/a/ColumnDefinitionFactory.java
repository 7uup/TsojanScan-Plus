/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.protocol.a;

import com.mysql.cj.protocol.ColumnDefinition;
import com.mysql.cj.protocol.ProtocolEntityFactory;
import com.mysql.cj.protocol.a.NativePacketPayload;
import com.mysql.cj.result.DefaultColumnDefinition;
import com.mysql.cj.result.Field;

public class ColumnDefinitionFactory
implements ProtocolEntityFactory<ColumnDefinition, NativePacketPayload> {
    protected long columnCount;
    protected ColumnDefinition columnDefinitionFromCache;

    public ColumnDefinitionFactory(long columnCount, ColumnDefinition columnDefinitionFromCache) {
        this.columnCount = columnCount;
        this.columnDefinitionFromCache = columnDefinitionFromCache;
    }

    public long getColumnCount() {
        return this.columnCount;
    }

    public ColumnDefinition getColumnDefinitionFromCache() {
        return this.columnDefinitionFromCache;
    }

    @Override
    public ColumnDefinition createFromMessage(NativePacketPayload packetPayload) {
        return null;
    }

    public boolean mergeColumnDefinitions() {
        return false;
    }

    public ColumnDefinition createFromFields(Field[] fields) {
        return new DefaultColumnDefinition(fields);
    }
}

