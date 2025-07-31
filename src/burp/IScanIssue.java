/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package burp;

import burp.IHttpRequestResponse;
import burp.IHttpService;
import java.net.URL;

public interface IScanIssue {
    public URL getUrl();

    public String getIssueName();

    public int getIssueType();

    public String getSeverity();

    public String getConfidence();

    public String getIssueBackground();

    public String getRemediationBackground();

    public String getIssueDetail();

    public String getRemediationDetail();

    public IHttpRequestResponse[] getHttpMessages();

    public IHttpService getHttpService();
}

