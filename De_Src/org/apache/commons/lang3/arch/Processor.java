/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3.arch;

public class Processor {
    private final Arch arch;
    private final Type type;

    public Processor(Arch arch, Type type) {
        this.arch = arch;
        this.type = type;
    }

    public Arch getArch() {
        return this.arch;
    }

    public Type getType() {
        return this.type;
    }

    public boolean is32Bit() {
        return Arch.BIT_32.equals((Object)this.arch);
    }

    public boolean is64Bit() {
        return Arch.BIT_64.equals((Object)this.arch);
    }

    public boolean isX86() {
        return Type.X86.equals((Object)this.type);
    }

    public boolean isIA64() {
        return Type.IA_64.equals((Object)this.type);
    }

    public boolean isPPC() {
        return Type.PPC.equals((Object)this.type);
    }

    public static enum Type {
        X86,
        IA_64,
        PPC,
        UNKNOWN;

    }

    public static enum Arch {
        BIT_32,
        BIT_64,
        UNKNOWN;

    }
}

