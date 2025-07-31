/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package burp;

public interface IIntruderPayloadProcessor {
    public String getProcessorName();

    public byte[] processPayload(byte[] var1, byte[] var2, byte[] var3);
}

