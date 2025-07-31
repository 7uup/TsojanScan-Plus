/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package burp;

public interface IIntruderPayloadGenerator {
    public boolean hasMorePayloads();

    public byte[] getNextPayload(byte[] var1);

    public void reset();
}

