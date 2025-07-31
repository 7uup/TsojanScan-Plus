/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okio;

import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import okio.GzipSource;
import okio.Source;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=2, d1={"\u0000$\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0005\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0015\u0010\n\u001a\u00020\u000b*\u00020\u00012\u0006\u0010\f\u001a\u00020\u0001H\u0082\b\u001a\r\u0010\r\u001a\u00020\u000e*\u00020\u000fH\u0086\b\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001X\u0082T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001X\u0082T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\b\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\t\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0010"}, d2={"FCOMMENT", "", "FEXTRA", "FHCRC", "FNAME", "SECTION_BODY", "", "SECTION_DONE", "SECTION_HEADER", "SECTION_TRAILER", "getBit", "", "bit", "gzip", "Lokio/GzipSource;", "Lokio/Source;", "okio"})
@JvmName(name="-GzipSourceExtensions")
public final class -GzipSourceExtensions {
    private static final int FHCRC = 1;
    private static final int FEXTRA = 2;
    private static final int FNAME = 3;
    private static final int FCOMMENT = 4;
    private static final byte SECTION_HEADER = 0;
    private static final byte SECTION_BODY = 1;
    private static final byte SECTION_TRAILER = 2;
    private static final byte SECTION_DONE = 3;

    private static final boolean getBit(int $this$getBit, int bit) {
        int $i$f$getBit = 0;
        return ($this$getBit >> bit & 1) == 1;
    }

    @NotNull
    public static final GzipSource gzip(@NotNull Source $this$gzip) {
        int $i$f$gzip = 0;
        Intrinsics.checkNotNullParameter($this$gzip, "$this$gzip");
        return new GzipSource($this$gzip);
    }

    public static final /* synthetic */ boolean access$getBit(int $this$access_u24getBit, int bit) {
        return -GzipSourceExtensions.getBit($this$access_u24getBit, bit);
    }
}

