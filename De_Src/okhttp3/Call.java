/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okhttp3;

import java.io.IOException;
import kotlin.Metadata;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;
import okio.Timeout;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u001a\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001:\u0001\u0011J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0000H&J\u0010\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H&J\b\u0010\b\u001a\u00020\tH&J\b\u0010\n\u001a\u00020\u000bH&J\b\u0010\f\u001a\u00020\u000bH&J\b\u0010\r\u001a\u00020\u000eH&J\b\u0010\u000f\u001a\u00020\u0010H&\u00a8\u0006\u0012"}, d2={"Lokhttp3/Call;", "", "cancel", "", "clone", "enqueue", "responseCallback", "Lokhttp3/Callback;", "execute", "Lokhttp3/Response;", "isCanceled", "", "isExecuted", "request", "Lokhttp3/Request;", "timeout", "Lokio/Timeout;", "Factory", "okhttp"})
public interface Call
extends Cloneable {
    @NotNull
    public Request request();

    @NotNull
    public Response execute() throws IOException;

    public void enqueue(@NotNull Callback var1);

    public void cancel();

    public boolean isExecuted();

    public boolean isCanceled();

    @NotNull
    public Timeout timeout();

    @NotNull
    public Call clone();

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u00e6\u0080\u0001\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&\u00a8\u0006\u0006"}, d2={"Lokhttp3/Call$Factory;", "", "newCall", "Lokhttp3/Call;", "request", "Lokhttp3/Request;", "okhttp"})
    public static interface Factory {
        @NotNull
        public Call newCall(@NotNull Request var1);
    }
}

