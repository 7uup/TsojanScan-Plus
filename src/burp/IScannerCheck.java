/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package burp;

import burp.IHttpRequestResponse;
import burp.IScanIssue;
import burp.IScannerInsertionPoint;
import java.util.List;

public interface IScannerCheck {
    public List<IScanIssue> doPassiveScan(IHttpRequestResponse var1);

    public List<IScanIssue> doActiveScan(IHttpRequestResponse var1, IScannerInsertionPoint var2);

    public int consolidateDuplicateIssues(IScanIssue var1, IScanIssue var2);
}

