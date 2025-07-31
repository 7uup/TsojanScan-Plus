/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package burp;

public interface ITempFile {
    public byte[] getBuffer();

    @Deprecated
    public void delete();
}

