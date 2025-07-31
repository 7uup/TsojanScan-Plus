/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package burp;

import burp.IContextMenuInvocation;
import java.util.List;
import javax.swing.JMenuItem;

public interface IContextMenuFactory {
    public List<JMenuItem> createMenuItems(IContextMenuInvocation var1);
}

