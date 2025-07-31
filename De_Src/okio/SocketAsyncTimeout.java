/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okio;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.logging.Level;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okio.AsyncTimeout;
import okio.Okio;
import okio.Okio__JvmOkioKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0012\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0014J\b\u0010\b\u001a\u00020\tH\u0014R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\n"}, d2={"Lokio/SocketAsyncTimeout;", "Lokio/AsyncTimeout;", "socket", "Ljava/net/Socket;", "(Ljava/net/Socket;)V", "newTimeoutException", "Ljava/io/IOException;", "cause", "timedOut", "", "okio"})
final class SocketAsyncTimeout
extends AsyncTimeout {
    private final Socket socket;

    @Override
    @NotNull
    protected IOException newTimeoutException(@Nullable IOException cause) {
        SocketTimeoutException ioe = new SocketTimeoutException("timeout");
        if (cause != null) {
            ioe.initCause(cause);
        }
        return ioe;
    }

    @Override
    protected void timedOut() {
        try {
            this.socket.close();
        } catch (Exception e) {
            Okio__JvmOkioKt.access$getLogger$p().log(Level.WARNING, "Failed to close timed out socket " + this.socket, e);
        } catch (AssertionError e) {
            if (Okio.isAndroidGetsocknameError(e)) {
                Okio__JvmOkioKt.access$getLogger$p().log(Level.WARNING, "Failed to close timed out socket " + this.socket, (Throwable)((Object)e));
            }
            throw (Throwable)((Object)e);
        }
    }

    public SocketAsyncTimeout(@NotNull Socket socket) {
        Intrinsics.checkNotNullParameter(socket, "socket");
        this.socket = socket;
    }
}

