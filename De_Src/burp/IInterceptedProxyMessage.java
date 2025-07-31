/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package burp;

import burp.IHttpRequestResponse;
import java.net.InetAddress;

public interface IInterceptedProxyMessage {
    public static final int ACTION_FOLLOW_RULES = 0;
    public static final int ACTION_DO_INTERCEPT = 1;
    public static final int ACTION_DONT_INTERCEPT = 2;
    public static final int ACTION_DROP = 3;
    public static final int ACTION_FOLLOW_RULES_AND_REHOOK = 16;
    public static final int ACTION_DO_INTERCEPT_AND_REHOOK = 17;
    public static final int ACTION_DONT_INTERCEPT_AND_REHOOK = 18;

    public int getMessageReference();

    public IHttpRequestResponse getMessageInfo();

    public int getInterceptAction();

    public void setInterceptAction(int var1);

    public String getListenerInterface();

    public InetAddress getClientIpAddress();
}

