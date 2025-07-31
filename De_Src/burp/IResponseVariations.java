/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package burp;

import java.util.List;

public interface IResponseVariations {
    public List<String> getVariantAttributes();

    public List<String> getInvariantAttributes();

    public int getAttributeValue(String var1, int var2);

    public void updateWith(byte[] ... var1);
}

