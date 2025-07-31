/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package burp;

import java.awt.Component;

public interface IMessageEditorTab {
    public String getTabCaption();

    public Component getUiComponent();

    public boolean isEnabled(byte[] var1, boolean var2);

    public void setMessage(byte[] var1, boolean var2);

    public byte[] getMessage();

    public boolean isModified();

    public byte[] getSelectedData();
}

