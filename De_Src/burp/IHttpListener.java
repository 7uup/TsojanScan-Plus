/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package burp;

import burp.IHttpRequestResponse;

public interface IHttpListener {
    public void processHttpMessage(int var1, boolean var2, IHttpRequestResponse var3);
}

