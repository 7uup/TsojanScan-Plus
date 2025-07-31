/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package burp;

import burp.IHttpRequestResponse;

public interface ISessionHandlingAction {
    public String getActionName();

    public void performAction(IHttpRequestResponse var1, IHttpRequestResponse[] var2);
}

