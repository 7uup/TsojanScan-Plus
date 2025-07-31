/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okio;

import java.util.zip.Deflater;
import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import okio.DeflaterSink;
import okio.Sink;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=2, d1={"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0017\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u0004H\u0086\b\u00a8\u0006\u0005"}, d2={"deflate", "Lokio/DeflaterSink;", "Lokio/Sink;", "deflater", "Ljava/util/zip/Deflater;", "okio"})
@JvmName(name="-DeflaterSinkExtensions")
public final class -DeflaterSinkExtensions {
    @NotNull
    public static final DeflaterSink deflate(@NotNull Sink $this$deflate, @NotNull Deflater deflater) {
        int $i$f$deflate = 0;
        Intrinsics.checkNotNullParameter($this$deflate, "$this$deflate");
        Intrinsics.checkNotNullParameter(deflater, "deflater");
        return new DeflaterSink($this$deflate, deflater);
    }

    public static /* synthetic */ DeflaterSink deflate$default(Sink $this$deflate, Deflater deflater, int n, Object object) {
        if ((n & 1) != 0) {
            deflater = new Deflater();
        }
        boolean $i$f$deflate = false;
        Intrinsics.checkNotNullParameter($this$deflate, "$this$deflate");
        Intrinsics.checkNotNullParameter(deflater, "deflater");
        return new DeflaterSink($this$deflate, deflater);
    }
}

