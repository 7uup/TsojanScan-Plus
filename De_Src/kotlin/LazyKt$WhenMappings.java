/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin;

import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=3)
public final class LazyKt$WhenMappings {
    public static final /* synthetic */ int[] $EnumSwitchMapping$0;

    static {
        $EnumSwitchMapping$0 = new int[LazyThreadSafetyMode.values().length];
        LazyKt$WhenMappings.$EnumSwitchMapping$0[LazyThreadSafetyMode.SYNCHRONIZED.ordinal()] = 1;
        LazyKt$WhenMappings.$EnumSwitchMapping$0[LazyThreadSafetyMode.PUBLICATION.ordinal()] = 2;
        LazyKt$WhenMappings.$EnumSwitchMapping$0[LazyThreadSafetyMode.NONE.ordinal()] = 3;
    }
}

