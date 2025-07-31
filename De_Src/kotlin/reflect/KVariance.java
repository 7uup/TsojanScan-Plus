/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.reflect;

import kotlin.Metadata;
import kotlin.SinceKotlin;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0087\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005\u00a8\u0006\u0006"}, d2={"Lkotlin/reflect/KVariance;", "", "(Ljava/lang/String;I)V", "INVARIANT", "IN", "OUT", "kotlin-stdlib"})
@SinceKotlin(version="1.1")
public final class KVariance
extends Enum<KVariance> {
    public static final /* enum */ KVariance INVARIANT;
    public static final /* enum */ KVariance IN;
    public static final /* enum */ KVariance OUT;
    private static final /* synthetic */ KVariance[] $VALUES;

    static {
        KVariance[] kVarianceArray = new KVariance[3];
        KVariance[] kVarianceArray2 = kVarianceArray;
        kVarianceArray[0] = INVARIANT = new KVariance();
        kVarianceArray[1] = IN = new KVariance();
        kVarianceArray[2] = OUT = new KVariance();
        $VALUES = kVarianceArray;
    }

    public static KVariance[] values() {
        return (KVariance[])$VALUES.clone();
    }

    public static KVariance valueOf(String string) {
        return Enum.valueOf(KVariance.class, string);
    }
}

