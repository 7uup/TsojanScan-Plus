/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okio;

import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import okio.BlackholeSink;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.RealBufferedSink;
import okio.RealBufferedSource;
import okio.Sink;
import okio.Source;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=5, d1={"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\r\u0010\u0000\u001a\u00020\u0001H\u0007\u00a2\u0006\u0002\b\u0002\u001a\n\u0010\u0003\u001a\u00020\u0004*\u00020\u0001\u001a\n\u0010\u0003\u001a\u00020\u0005*\u00020\u0006\u00a8\u0006\u0007"}, d2={"blackholeSink", "Lokio/Sink;", "blackhole", "buffer", "Lokio/BufferedSink;", "Lokio/BufferedSource;", "Lokio/Source;", "okio"}, xs="okio/Okio")
final class Okio__OkioKt {
    @NotNull
    public static final BufferedSource buffer(@NotNull Source $this$buffer) {
        Intrinsics.checkNotNullParameter($this$buffer, "$this$buffer");
        return new RealBufferedSource($this$buffer);
    }

    @NotNull
    public static final BufferedSink buffer(@NotNull Sink $this$buffer) {
        Intrinsics.checkNotNullParameter($this$buffer, "$this$buffer");
        return new RealBufferedSink($this$buffer);
    }

    @JvmName(name="blackhole")
    @NotNull
    public static final Sink blackhole() {
        return new BlackholeSink();
    }
}

