/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.protocol;

import com.mysql.cj.protocol.WatchableStream;

public interface OutputStreamWatcher {
    public void streamClosed(WatchableStream var1);
}

