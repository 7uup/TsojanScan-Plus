/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package burp;

import burp.IIntruderAttack;
import burp.IIntruderPayloadGenerator;

public interface IIntruderPayloadGeneratorFactory {
    public String getGeneratorName();

    public IIntruderPayloadGenerator createNewInstance(IIntruderAttack var1);
}

