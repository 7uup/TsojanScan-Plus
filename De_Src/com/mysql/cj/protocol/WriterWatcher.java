/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.protocol;

import com.mysql.cj.protocol.WatchableWriter;

public interface WriterWatcher {
    public void writerClosed(WatchableWriter var1);
}

