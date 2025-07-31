/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.jvm.internal;

import kotlin.Metadata;
import kotlin.reflect.KVariance;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=3)
public final class TypeReference$WhenMappings {
    public static final /* synthetic */ int[] $EnumSwitchMapping$0;

    static {
        $EnumSwitchMapping$0 = new int[KVariance.values().length];
        TypeReference$WhenMappings.$EnumSwitchMapping$0[KVariance.INVARIANT.ordinal()] = 1;
        TypeReference$WhenMappings.$EnumSwitchMapping$0[KVariance.IN.ordinal()] = 2;
        TypeReference$WhenMappings.$EnumSwitchMapping$0[KVariance.OUT.ordinal()] = 3;
    }
}

