/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.internal;

import kotlin.Metadata;
import kotlin.SinceKotlin;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0081\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005\u00a8\u0006\u0006"}, d2={"Lkotlin/internal/RequireKotlinVersionKind;", "", "(Ljava/lang/String;I)V", "LANGUAGE_VERSION", "COMPILER_VERSION", "API_VERSION", "kotlin-stdlib"})
@SinceKotlin(version="1.2")
public final class RequireKotlinVersionKind
extends Enum<RequireKotlinVersionKind> {
    public static final /* enum */ RequireKotlinVersionKind LANGUAGE_VERSION;
    public static final /* enum */ RequireKotlinVersionKind COMPILER_VERSION;
    public static final /* enum */ RequireKotlinVersionKind API_VERSION;
    private static final /* synthetic */ RequireKotlinVersionKind[] $VALUES;

    static {
        RequireKotlinVersionKind[] requireKotlinVersionKindArray = new RequireKotlinVersionKind[3];
        RequireKotlinVersionKind[] requireKotlinVersionKindArray2 = requireKotlinVersionKindArray;
        requireKotlinVersionKindArray[0] = LANGUAGE_VERSION = new RequireKotlinVersionKind();
        requireKotlinVersionKindArray[1] = COMPILER_VERSION = new RequireKotlinVersionKind();
        requireKotlinVersionKindArray[2] = API_VERSION = new RequireKotlinVersionKind();
        $VALUES = requireKotlinVersionKindArray;
    }

    public static RequireKotlinVersionKind[] values() {
        return (RequireKotlinVersionKind[])$VALUES.clone();
    }

    public static RequireKotlinVersionKind valueOf(String string) {
        return Enum.valueOf(RequireKotlinVersionKind.class, string);
    }
}

