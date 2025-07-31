/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.log;

import com.mysql.cj.log.Log;
import com.mysql.cj.log.ProfilerEvent;

public interface ProfilerEventHandler {
    public void init(Log var1);

    public void destroy();

    public void consumeEvent(ProfilerEvent var1);
}

