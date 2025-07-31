/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.protocol;

import com.mysql.cj.ServerVersion;

public interface ServerCapabilities {
    public int getCapabilityFlags();

    public void setCapabilityFlags(int var1);

    public ServerVersion getServerVersion();

    public void setServerVersion(ServerVersion var1);

    public boolean serverSupportsFracSecs();
}

