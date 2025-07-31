/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.reflect;

import kotlin.Metadata;
import kotlin.SinceKotlin;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0087\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006\u00a8\u0006\u0007"}, d2={"Lkotlin/reflect/KVisibility;", "", "(Ljava/lang/String;I)V", "PUBLIC", "PROTECTED", "INTERNAL", "PRIVATE", "kotlin-stdlib"})
@SinceKotlin(version="1.1")
public final class KVisibility
extends Enum<KVisibility> {
    public static final /* enum */ KVisibility PUBLIC;
    public static final /* enum */ KVisibility PROTECTED;
    public static final /* enum */ KVisibility INTERNAL;
    public static final /* enum */ KVisibility PRIVATE;
    private static final /* synthetic */ KVisibility[] $VALUES;

    static {
        KVisibility[] kVisibilityArray = new KVisibility[4];
        KVisibility[] kVisibilityArray2 = kVisibilityArray;
        kVisibilityArray[0] = PUBLIC = new KVisibility();
        kVisibilityArray[1] = PROTECTED = new KVisibility();
        kVisibilityArray[2] = INTERNAL = new KVisibility();
        kVisibilityArray[3] = PRIVATE = new KVisibility();
        $VALUES = kVisibilityArray;
    }

    public static KVisibility[] values() {
        return (KVisibility[])$VALUES.clone();
    }

    public static KVisibility valueOf(String string) {
        return Enum.valueOf(KVisibility.class, string);
    }
}

