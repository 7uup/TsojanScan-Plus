/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.jvm.internal;

import kotlin.Metadata;
import kotlin.SinceKotlin;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\n\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\b\u00c0\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\u00020\u00078\u0006X\u0087T\u00a2\u0006\b\n\u0000\u0012\u0004\b\b\u0010\u0002R\u0016\u0010\t\u001a\u00020\u00078\u0006X\u0087T\u00a2\u0006\b\n\u0000\u0012\u0004\b\n\u0010\u0002\u00a8\u0006\u000b"}, d2={"Lkotlin/jvm/internal/ShortCompanionObject;", "", "()V", "MAX_VALUE", "", "MIN_VALUE", "SIZE_BITS", "", "getSIZE_BITS$annotations", "SIZE_BYTES", "getSIZE_BYTES$annotations", "kotlin-stdlib"})
public final class ShortCompanionObject {
    public static final short MIN_VALUE = Short.MIN_VALUE;
    public static final short MAX_VALUE = Short.MAX_VALUE;
    public static final int SIZE_BYTES = 2;
    public static final int SIZE_BITS = 16;
    public static final ShortCompanionObject INSTANCE;

    @SinceKotlin(version="1.3")
    public static /* synthetic */ void getSIZE_BYTES$annotations() {
    }

    @SinceKotlin(version="1.3")
    public static /* synthetic */ void getSIZE_BITS$annotations() {
    }

    private ShortCompanionObject() {
    }

    static {
        ShortCompanionObject shortCompanionObject;
        INSTANCE = shortCompanionObject = new ShortCompanionObject();
    }
}

