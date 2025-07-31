/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okhttp3.internal.http1;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Headers;
import okio.BufferedSource;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 \r2\u00020\u0001:\u0001\rB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\t\u001a\u00020\nJ\u0006\u0010\u000b\u001a\u00020\fR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b\u00a8\u0006\u000e"}, d2={"Lokhttp3/internal/http1/HeadersReader;", "", "source", "Lokio/BufferedSource;", "(Lokio/BufferedSource;)V", "headerLimit", "", "getSource", "()Lokio/BufferedSource;", "readHeaders", "Lokhttp3/Headers;", "readLine", "", "Companion", "okhttp"})
public final class HeadersReader {
    private long headerLimit;
    @NotNull
    private final BufferedSource source;
    private static final int HEADER_LIMIT = 262144;
    public static final Companion Companion = new Companion(null);

    @NotNull
    public final String readLine() {
        String line = this.source.readUtf8LineStrict(this.headerLimit);
        this.headerLimit -= (long)line.length();
        return line;
    }

    @NotNull
    public final Headers readHeaders() {
        Headers.Builder result = new Headers.Builder();
        while (true) {
            String line = this.readLine();
            CharSequence charSequence = line;
            boolean bl = false;
            if (charSequence.length() == 0) break;
            result.addLenient$okhttp(line);
        }
        return result.build();
    }

    @NotNull
    public final BufferedSource getSource() {
        return this.source;
    }

    public HeadersReader(@NotNull BufferedSource source2) {
        Intrinsics.checkNotNullParameter(source2, "source");
        this.source = source2;
        this.headerLimit = 262144;
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2={"Lokhttp3/internal/http1/HeadersReader$Companion;", "", "()V", "HEADER_LIMIT", "", "okhttp"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

