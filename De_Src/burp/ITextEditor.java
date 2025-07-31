/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package burp;

import java.awt.Component;

public interface ITextEditor {
    public Component getComponent();

    public void setEditable(boolean var1);

    public void setText(byte[] var1);

    public byte[] getText();

    public boolean isTextModified();

    public byte[] getSelectedText();

    public int[] getSelectionBounds();

    public void setSearchExpression(String var1);
}

