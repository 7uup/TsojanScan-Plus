/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.protocol;

import com.mysql.cj.protocol.ColumnDefinition;
import com.mysql.cj.protocol.ProtocolEntity;
import com.mysql.cj.result.Row;

public interface ResultListener<OK extends ProtocolEntity> {
    public void onMetadata(ColumnDefinition var1);

    public void onRow(Row var1);

    public void onComplete(OK var1);

    public void onException(Throwable var1);
}

