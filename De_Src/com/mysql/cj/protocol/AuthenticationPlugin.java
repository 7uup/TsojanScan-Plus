/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.protocol;

import com.mysql.cj.protocol.Message;
import com.mysql.cj.protocol.Protocol;
import java.util.List;

public interface AuthenticationPlugin<M extends Message> {
    default public void init(Protocol<M> protocol) {
    }

    default public void reset() {
    }

    default public void destroy() {
    }

    public String getProtocolPluginName();

    public boolean requiresConfidentiality();

    public boolean isReusable();

    public void setAuthenticationParameters(String var1, String var2);

    public boolean nextAuthenticationStep(M var1, List<M> var2);
}

