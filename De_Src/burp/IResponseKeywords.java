/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package burp;

import java.util.List;

public interface IResponseKeywords {
    public List<String> getVariantKeywords();

    public List<String> getInvariantKeywords();

    public int getKeywordCount(String var1, int var2);

    public void updateWith(byte[] ... var1);
}

