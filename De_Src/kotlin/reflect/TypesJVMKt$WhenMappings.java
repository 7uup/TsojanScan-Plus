/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.reflect;

import kotlin.Metadata;
import kotlin.reflect.KVariance;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=3)
public final class TypesJVMKt$WhenMappings {
    public static final /* synthetic */ int[] $EnumSwitchMapping$0;
    public static final /* synthetic */ int[] $EnumSwitchMapping$1;

    static {
        $EnumSwitchMapping$0 = new int[KVariance.values().length];
        TypesJVMKt$WhenMappings.$EnumSwitchMapping$0[KVariance.IN.ordinal()] = 1;
        TypesJVMKt$WhenMappings.$EnumSwitchMapping$0[KVariance.INVARIANT.ordinal()] = 2;
        TypesJVMKt$WhenMappings.$EnumSwitchMapping$0[KVariance.OUT.ordinal()] = 3;
        $EnumSwitchMapping$1 = new int[KVariance.values().length];
        TypesJVMKt$WhenMappings.$EnumSwitchMapping$1[KVariance.INVARIANT.ordinal()] = 1;
        TypesJVMKt$WhenMappings.$EnumSwitchMapping$1[KVariance.IN.ordinal()] = 2;
        TypesJVMKt$WhenMappings.$EnumSwitchMapping$1[KVariance.OUT.ordinal()] = 3;
    }
}

