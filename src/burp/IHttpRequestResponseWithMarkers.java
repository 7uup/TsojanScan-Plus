/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package burp;

import burp.IHttpRequestResponse;
import java.util.List;

public interface IHttpRequestResponseWithMarkers
extends IHttpRequestResponse {
    public List<int[]> getRequestMarkers();

    public List<int[]> getResponseMarkers();
}

