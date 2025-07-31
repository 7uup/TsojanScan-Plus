/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.coroutines.cancellation;

import java.util.concurrent.CancellationException;
import kotlin.ExperimentalStdlibApi;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.internal.InlineOnly;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=2, d1={"\u0000\"\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a!\u0010\u0000\u001a\u00060\u0001j\u0002`\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0087\b\u001a\u0017\u0010\u0000\u001a\u00060\u0001j\u0002`\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0087\b*\u001e\b\u0007\u0010\u0000\"\u00020\u00012\u00020\u0001B\u0002\b\u0007B\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u00a8\u0006\u000b"}, d2={"CancellationException", "Ljava/util/concurrent/CancellationException;", "Lkotlin/coroutines/cancellation/CancellationException;", "message", "", "cause", "", "Lkotlin/ExperimentalStdlibApi;", "Lkotlin/SinceKotlin;", "version", "1.4", "kotlin-stdlib"})
public final class CancellationExceptionKt {
    @ExperimentalStdlibApi
    @SinceKotlin(version="1.4")
    public static /* synthetic */ void CancellationException$annotations() {
    }

    @InlineOnly
    @ExperimentalStdlibApi
    @SinceKotlin(version="1.4")
    private static final CancellationException CancellationException(String message, Throwable cause) {
        int $i$f$CancellationException = 0;
        CancellationException cancellationException = new CancellationException(message);
        boolean bl = false;
        boolean bl2 = false;
        CancellationException it = cancellationException;
        boolean bl3 = false;
        it.initCause(cause);
        return cancellationException;
    }

    @InlineOnly
    @ExperimentalStdlibApi
    @SinceKotlin(version="1.4")
    private static final CancellationException CancellationException(Throwable cause) {
        int $i$f$CancellationException = 0;
        Throwable throwable = cause;
        CancellationException cancellationException = new CancellationException(throwable != null ? throwable.toString() : null);
        boolean bl = false;
        boolean bl2 = false;
        CancellationException it = cancellationException;
        boolean bl3 = false;
        it.initCause(cause);
        return cancellationException;
    }
}

