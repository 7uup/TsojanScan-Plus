/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.text;

import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=2, d1={"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\u0011\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0087\b\u00a8\u0006\u0004"}, d2={"charset", "Ljava/nio/charset/Charset;", "charsetName", "", "kotlin-stdlib"})
@JvmName(name="CharsetsKt")
public final class CharsetsKt {
    @InlineOnly
    private static final Charset charset(String charsetName) {
        int $i$f$charset = 0;
        Charset charset = Charset.forName(charsetName);
        Intrinsics.checkNotNullExpressionValue(charset, "Charset.forName(charsetName)");
        return charset;
    }
}

