/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package burp;

import burp.IHttpService;

public interface IMessageEditorController {
    public IHttpService getHttpService();

    public byte[] getRequest();

    public byte[] getResponse();
}

