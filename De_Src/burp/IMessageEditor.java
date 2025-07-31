/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package burp;

import java.awt.Component;

public interface IMessageEditor {
    public Component getComponent();

    public void setMessage(byte[] var1, boolean var2);

    public byte[] getMessage();

    public boolean isMessageModified();

    public byte[] getSelectedData();

    public int[] getSelectionBounds();
}

