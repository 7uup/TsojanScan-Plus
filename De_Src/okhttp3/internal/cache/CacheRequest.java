/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okhttp3.internal.cache;

import java.io.IOException;
import kotlin.Metadata;
import okio.Sink;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0005H&\u00a8\u0006\u0006"}, d2={"Lokhttp3/internal/cache/CacheRequest;", "", "abort", "", "body", "Lokio/Sink;", "okhttp"})
public interface CacheRequest {
    @NotNull
    public Sink body() throws IOException;

    public void abort();
}

