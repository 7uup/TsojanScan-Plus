/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package burp;

import burp.IHttpRequestResponse;
import burp.IScanIssue;
import java.awt.event.InputEvent;

public interface IContextMenuInvocation {
    public static final byte CONTEXT_MESSAGE_EDITOR_REQUEST = 0;
    public static final byte CONTEXT_MESSAGE_EDITOR_RESPONSE = 1;
    public static final byte CONTEXT_MESSAGE_VIEWER_REQUEST = 2;
    public static final byte CONTEXT_MESSAGE_VIEWER_RESPONSE = 3;
    public static final byte CONTEXT_TARGET_SITE_MAP_TREE = 4;
    public static final byte CONTEXT_TARGET_SITE_MAP_TABLE = 5;
    public static final byte CONTEXT_PROXY_HISTORY = 6;
    public static final byte CONTEXT_SCANNER_RESULTS = 7;
    public static final byte CONTEXT_INTRUDER_PAYLOAD_POSITIONS = 8;
    public static final byte CONTEXT_INTRUDER_ATTACK_RESULTS = 9;
    public static final byte CONTEXT_SEARCH_RESULTS = 10;

    public InputEvent getInputEvent();

    public int getToolFlag();

    public byte getInvocationContext();

    public int[] getSelectionBounds();

    public IHttpRequestResponse[] getSelectedMessages();

    public IScanIssue[] getSelectedIssues();
}

