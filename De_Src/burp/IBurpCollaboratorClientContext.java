/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package burp;

import burp.IBurpCollaboratorInteraction;
import java.util.List;

public interface IBurpCollaboratorClientContext {
    public String generatePayload(boolean var1);

    public List<IBurpCollaboratorInteraction> fetchAllCollaboratorInteractions();

    public List<IBurpCollaboratorInteraction> fetchCollaboratorInteractionsFor(String var1);

    public List<IBurpCollaboratorInteraction> fetchAllInfiltratorInteractions();

    public List<IBurpCollaboratorInteraction> fetchInfiltratorInteractionsFor(String var1);

    public String getCollaboratorServerLocation();
}

