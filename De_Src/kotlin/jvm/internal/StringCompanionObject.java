/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.jvm.internal;

import kotlin.Metadata;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u00c0\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2={"Lkotlin/jvm/internal/StringCompanionObject;", "", "()V", "kotlin-stdlib"})
public final class StringCompanionObject {
    public static final StringCompanionObject INSTANCE;

    private StringCompanionObject() {
    }

    static {
        StringCompanionObject stringCompanionObject;
        INSTANCE = stringCompanionObject = new StringCompanionObject();
    }
}

