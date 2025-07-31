/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package burp;

import burp.IHttpRequestResponse;
import burp.IScannerInsertionPoint;
import java.util.List;

public interface IScannerInsertionPointProvider {
    public List<IScannerInsertionPoint> getInsertionPoints(IHttpRequestResponse var1);
}

