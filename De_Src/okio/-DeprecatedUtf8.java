/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okio;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;
import okio.Utf8;
import org.jetbrains.annotations.NotNull;

@Deprecated(message="changed in Okio 2.x")
@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J \u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0007\u00a8\u0006\n"}, d2={"Lokio/-DeprecatedUtf8;", "", "()V", "size", "", "string", "", "beginIndex", "", "endIndex", "okio"})
public final class -DeprecatedUtf8 {
    public static final -DeprecatedUtf8 INSTANCE;

    @Deprecated(message="moved to extension function", replaceWith=@ReplaceWith(imports={"okio.utf8Size"}, expression="string.utf8Size()"), level=DeprecationLevel.ERROR)
    public final long size(@NotNull String string) {
        Intrinsics.checkNotNullParameter(string, "string");
        return Utf8.size$default(string, 0, 0, 3, null);
    }

    @Deprecated(message="moved to extension function", replaceWith=@ReplaceWith(imports={"okio.utf8Size"}, expression="string.utf8Size(beginIndex, endIndex)"), level=DeprecationLevel.ERROR)
    public final long size(@NotNull String string, int beginIndex, int endIndex) {
        Intrinsics.checkNotNullParameter(string, "string");
        return Utf8.size(string, beginIndex, endIndex);
    }

    private -DeprecatedUtf8() {
    }

    static {
        -DeprecatedUtf8 -DeprecatedUtf82;
        INSTANCE = -DeprecatedUtf82 = new -DeprecatedUtf8();
    }
}

