/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package burp;

import burp.IHttpService;

public interface IHttpRequestResponse {
    public byte[] getRequest();

    public void setRequest(byte[] var1);

    public byte[] getResponse();

    public void setResponse(byte[] var1);

    public String getComment();

    public void setComment(String var1);

    public String getHighlight();

    public void setHighlight(String var1);

    public IHttpService getHttpService();

    public void setHttpService(IHttpService var1);
}

