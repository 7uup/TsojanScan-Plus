/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package burp;

import burp.IHttpRequestResponse;

public interface IHttpRequestResponsePersisted
extends IHttpRequestResponse {
    @Deprecated
    public void deleteTempFiles();
}

