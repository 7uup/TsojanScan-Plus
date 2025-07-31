/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okio;

import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import okio.GzipSink;
import okio.Sink;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=2, d1={"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0086\b\u00a8\u0006\u0003"}, d2={"gzip", "Lokio/GzipSink;", "Lokio/Sink;", "okio"})
@JvmName(name="-GzipSinkExtensions")
public final class -GzipSinkExtensions {
    @NotNull
    public static final GzipSink gzip(@NotNull Sink $this$gzip) {
        int $i$f$gzip = 0;
        Intrinsics.checkNotNullParameter($this$gzip, "$this$gzip");
        return new GzipSink($this$gzip);
    }
}

