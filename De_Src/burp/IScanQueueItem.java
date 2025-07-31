/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package burp;

import burp.IScanIssue;

public interface IScanQueueItem {
    public String getStatus();

    @Deprecated
    public byte getPercentageComplete();

    public int getNumRequests();

    public int getNumErrors();

    public int getNumInsertionPoints();

    public void cancel();

    public IScanIssue[] getIssues();
}

