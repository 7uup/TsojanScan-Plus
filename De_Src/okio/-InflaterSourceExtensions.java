/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okio;

import java.util.zip.Inflater;
import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import okio.InflaterSource;
import okio.Source;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=2, d1={"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0017\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u0004H\u0086\b\u00a8\u0006\u0005"}, d2={"inflate", "Lokio/InflaterSource;", "Lokio/Source;", "inflater", "Ljava/util/zip/Inflater;", "okio"})
@JvmName(name="-InflaterSourceExtensions")
public final class -InflaterSourceExtensions {
    @NotNull
    public static final InflaterSource inflate(@NotNull Source $this$inflate, @NotNull Inflater inflater) {
        int $i$f$inflate = 0;
        Intrinsics.checkNotNullParameter($this$inflate, "$this$inflate");
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return new InflaterSource($this$inflate, inflater);
    }

    public static /* synthetic */ InflaterSource inflate$default(Source $this$inflate, Inflater inflater, int n, Object object) {
        if ((n & 1) != 0) {
            inflater = new Inflater();
        }
        boolean $i$f$inflate = false;
        Intrinsics.checkNotNullParameter($this$inflate, "$this$inflate");
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return new InflaterSource($this$inflate, inflater);
    }
}

