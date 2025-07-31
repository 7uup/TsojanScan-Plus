/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okhttp3.internal.http2;

import java.io.IOException;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.http2.ErrorCode;
import okhttp3.internal.http2.Header;
import okio.BufferedSource;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014J(\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0003H&J&\u0010\n\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\t\u001a\u00020\u0003H&J\u001e\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\r0\fH&J\u0018\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u0013H&\u00a8\u0006\u0015"}, d2={"Lokhttp3/internal/http2/PushObserver;", "", "onData", "", "streamId", "", "source", "Lokio/BufferedSource;", "byteCount", "last", "onHeaders", "responseHeaders", "", "Lokhttp3/internal/http2/Header;", "onRequest", "requestHeaders", "onReset", "", "errorCode", "Lokhttp3/internal/http2/ErrorCode;", "Companion", "okhttp"})
public interface PushObserver {
    @JvmField
    @NotNull
    public static final PushObserver CANCEL;
    public static final Companion Companion;

    public boolean onRequest(int var1, @NotNull List<Header> var2);

    public boolean onHeaders(int var1, @NotNull List<Header> var2, boolean var3);

    public boolean onData(int var1, @NotNull BufferedSource var2, int var3, boolean var4) throws IOException;

    public void onReset(int var1, @NotNull ErrorCode var2);

    static {
        Companion = new Companion(null);
        CANCEL = new Companion.PushObserverCancel();
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001:\u0001\u0005B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004\u00f8\u0001\u0000\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0001\u0082\u0002\u0007\n\u0005\b\u0091F0\u0001\u00a8\u0006\u0006"}, d2={"Lokhttp3/internal/http2/PushObserver$Companion;", "", "()V", "CANCEL", "Lokhttp3/internal/http2/PushObserver;", "PushObserverCancel", "okhttp"})
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE;

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J(\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u0004H\u0016J&\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\n\u001a\u00020\u0004H\u0016J\u001e\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000e0\rH\u0016J\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u0014H\u0016\u00a8\u0006\u0015"}, d2={"Lokhttp3/internal/http2/PushObserver$Companion$PushObserverCancel;", "Lokhttp3/internal/http2/PushObserver;", "()V", "onData", "", "streamId", "", "source", "Lokio/BufferedSource;", "byteCount", "last", "onHeaders", "responseHeaders", "", "Lokhttp3/internal/http2/Header;", "onRequest", "requestHeaders", "onReset", "", "errorCode", "Lokhttp3/internal/http2/ErrorCode;", "okhttp"})
        private static final class PushObserverCancel
        implements PushObserver {
            @Override
            public boolean onRequest(int streamId, @NotNull List<Header> requestHeaders) {
                Intrinsics.checkNotNullParameter(requestHeaders, "requestHeaders");
                return true;
            }

            @Override
            public boolean onHeaders(int streamId, @NotNull List<Header> responseHeaders, boolean last) {
                Intrinsics.checkNotNullParameter(responseHeaders, "responseHeaders");
                return true;
            }

            @Override
            public boolean onData(int streamId, @NotNull BufferedSource source2, int byteCount, boolean last) throws IOException {
                Intrinsics.checkNotNullParameter(source2, "source");
                source2.skip(byteCount);
                return true;
            }

            @Override
            public void onReset(int streamId, @NotNull ErrorCode errorCode) {
                Intrinsics.checkNotNullParameter((Object)errorCode, "errorCode");
            }
        }
    }
}

