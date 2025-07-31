/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.protocol.a;

import com.mysql.cj.conf.RuntimeProperty;
import com.mysql.cj.exceptions.ExceptionInterceptor;
import com.mysql.cj.protocol.ColumnDefinition;
import com.mysql.cj.protocol.ProtocolEntityFactory;
import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.protocol.ResultsetRow;
import com.mysql.cj.protocol.ValueDecoder;
import com.mysql.cj.protocol.a.NativePacketPayload;

public abstract class AbstractRowFactory
implements ProtocolEntityFactory<ResultsetRow, NativePacketPayload> {
    protected ColumnDefinition columnDefinition;
    protected Resultset.Concurrency resultSetConcurrency;
    protected boolean canReuseRowPacketForBufferRow;
    protected RuntimeProperty<Integer> useBufferRowSizeThreshold;
    protected ExceptionInterceptor exceptionInterceptor;
    protected ValueDecoder valueDecoder;

    public boolean canReuseRowPacketForBufferRow() {
        return this.canReuseRowPacketForBufferRow;
    }
}

