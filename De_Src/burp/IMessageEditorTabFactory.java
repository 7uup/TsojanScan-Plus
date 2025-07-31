/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package burp;

import burp.IMessageEditorController;
import burp.IMessageEditorTab;

public interface IMessageEditorTabFactory {
    public IMessageEditorTab createNewInstance(IMessageEditorController var1, boolean var2);
}

