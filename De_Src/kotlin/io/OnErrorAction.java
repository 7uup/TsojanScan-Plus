/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.io;

import kotlin.Metadata;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004\u00a8\u0006\u0005"}, d2={"Lkotlin/io/OnErrorAction;", "", "(Ljava/lang/String;I)V", "SKIP", "TERMINATE", "kotlin-stdlib"})
public final class OnErrorAction
extends Enum<OnErrorAction> {
    public static final /* enum */ OnErrorAction SKIP;
    public static final /* enum */ OnErrorAction TERMINATE;
    private static final /* synthetic */ OnErrorAction[] $VALUES;

    static {
        OnErrorAction[] onErrorActionArray = new OnErrorAction[2];
        OnErrorAction[] onErrorActionArray2 = onErrorActionArray;
        onErrorActionArray[0] = SKIP = new OnErrorAction();
        onErrorActionArray[1] = TERMINATE = new OnErrorAction();
        $VALUES = onErrorActionArray;
    }

    public static OnErrorAction[] values() {
        return (OnErrorAction[])$VALUES.clone();
    }

    public static OnErrorAction valueOf(String string) {
        return Enum.valueOf(OnErrorAction.class, string);
    }
}

