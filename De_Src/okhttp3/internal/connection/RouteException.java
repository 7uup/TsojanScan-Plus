/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okhttp3.internal.connection;

import java.io.IOException;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00060\u0001j\u0002`\u0002B\u000f\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\u0002\u0010\u0005J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0004R\u0011\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001e\u0010\t\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0004@BX\u0086\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0007\u00a8\u0006\u000e"}, d2={"Lokhttp3/internal/connection/RouteException;", "Ljava/lang/RuntimeException;", "Lkotlin/RuntimeException;", "firstConnectException", "Ljava/io/IOException;", "(Ljava/io/IOException;)V", "getFirstConnectException", "()Ljava/io/IOException;", "<set-?>", "lastConnectException", "getLastConnectException", "addConnectException", "", "e", "okhttp"})
public final class RouteException
extends RuntimeException {
    @NotNull
    private IOException lastConnectException;
    @NotNull
    private final IOException firstConnectException;

    @NotNull
    public final IOException getLastConnectException() {
        return this.lastConnectException;
    }

    public final void addConnectException(@NotNull IOException e) {
        Intrinsics.checkNotNullParameter(e, "e");
        ExceptionsKt.addSuppressed(this.firstConnectException, e);
        this.lastConnectException = e;
    }

    @NotNull
    public final IOException getFirstConnectException() {
        return this.firstConnectException;
    }

    public RouteException(@NotNull IOException firstConnectException) {
        Intrinsics.checkNotNullParameter(firstConnectException, "firstConnectException");
        super(firstConnectException);
        this.lastConnectException = this.firstConnectException = firstConnectException;
    }
}

