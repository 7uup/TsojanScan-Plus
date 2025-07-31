/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin;

import kotlin.Metadata;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005\u00a8\u0006\u0006"}, d2={"Lkotlin/LazyThreadSafetyMode;", "", "(Ljava/lang/String;I)V", "SYNCHRONIZED", "PUBLICATION", "NONE", "kotlin-stdlib"})
public final class LazyThreadSafetyMode
extends Enum<LazyThreadSafetyMode> {
    public static final /* enum */ LazyThreadSafetyMode SYNCHRONIZED;
    public static final /* enum */ LazyThreadSafetyMode PUBLICATION;
    public static final /* enum */ LazyThreadSafetyMode NONE;
    private static final /* synthetic */ LazyThreadSafetyMode[] $VALUES;

    static {
        LazyThreadSafetyMode[] lazyThreadSafetyModeArray = new LazyThreadSafetyMode[3];
        LazyThreadSafetyMode[] lazyThreadSafetyModeArray2 = lazyThreadSafetyModeArray;
        lazyThreadSafetyModeArray[0] = SYNCHRONIZED = new LazyThreadSafetyMode();
        lazyThreadSafetyModeArray[1] = PUBLICATION = new LazyThreadSafetyMode();
        lazyThreadSafetyModeArray[2] = NONE = new LazyThreadSafetyMode();
        $VALUES = lazyThreadSafetyModeArray;
    }

    public static LazyThreadSafetyMode[] values() {
        return (LazyThreadSafetyMode[])$VALUES.clone();
    }

    public static LazyThreadSafetyMode valueOf(String string) {
        return Enum.valueOf(LazyThreadSafetyMode.class, string);
    }
}

