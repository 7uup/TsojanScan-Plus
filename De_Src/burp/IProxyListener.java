/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package burp;

import burp.IInterceptedProxyMessage;

public interface IProxyListener {
    public void processProxyMessage(boolean var1, IInterceptedProxyMessage var2);
}

