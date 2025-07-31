/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\u0010\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0002\b\u0002J\b\u0010\u0002\u001a\u00020\u0003H\u0016\u00a8\u0006\u0004"}, d2={"", "", "toString", "", "kotlin-stdlib"})
public final class Unit {
    public static final Unit INSTANCE;

    @NotNull
    public String toString() {
        return "kotlin.Unit";
    }

    private Unit() {
    }

    static {
        Unit unit;
        INSTANCE = unit = new Unit();
    }
}

