/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.protocol;

import com.mysql.cj.protocol.ProtocolEntity;
import com.mysql.cj.result.Row;

public interface ResultsetRow
extends Row,
ProtocolEntity {
    default public boolean isBinaryEncoded() {
        return false;
    }
}

