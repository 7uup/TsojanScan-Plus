/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okhttp3.internal.http2;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u0013\b\u0086\u0001\u0018\u0000 \u00152\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0015B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014\u00a8\u0006\u0016"}, d2={"Lokhttp3/internal/http2/ErrorCode;", "", "httpCode", "", "(Ljava/lang/String;II)V", "getHttpCode", "()I", "NO_ERROR", "PROTOCOL_ERROR", "INTERNAL_ERROR", "FLOW_CONTROL_ERROR", "SETTINGS_TIMEOUT", "STREAM_CLOSED", "FRAME_SIZE_ERROR", "REFUSED_STREAM", "CANCEL", "COMPRESSION_ERROR", "CONNECT_ERROR", "ENHANCE_YOUR_CALM", "INADEQUATE_SECURITY", "HTTP_1_1_REQUIRED", "Companion", "okhttp"})
public final class ErrorCode
extends Enum<ErrorCode> {
    public static final /* enum */ ErrorCode NO_ERROR;
    public static final /* enum */ ErrorCode PROTOCOL_ERROR;
    public static final /* enum */ ErrorCode INTERNAL_ERROR;
    public static final /* enum */ ErrorCode FLOW_CONTROL_ERROR;
    public static final /* enum */ ErrorCode SETTINGS_TIMEOUT;
    public static final /* enum */ ErrorCode STREAM_CLOSED;
    public static final /* enum */ ErrorCode FRAME_SIZE_ERROR;
    public static final /* enum */ ErrorCode REFUSED_STREAM;
    public static final /* enum */ ErrorCode CANCEL;
    public static final /* enum */ ErrorCode COMPRESSION_ERROR;
    public static final /* enum */ ErrorCode CONNECT_ERROR;
    public static final /* enum */ ErrorCode ENHANCE_YOUR_CALM;
    public static final /* enum */ ErrorCode INADEQUATE_SECURITY;
    public static final /* enum */ ErrorCode HTTP_1_1_REQUIRED;
    private static final /* synthetic */ ErrorCode[] $VALUES;
    private final int httpCode;
    public static final Companion Companion;

    static {
        ErrorCode[] errorCodeArray = new ErrorCode[14];
        ErrorCode[] errorCodeArray2 = errorCodeArray;
        errorCodeArray[0] = NO_ERROR = new ErrorCode(0);
        errorCodeArray[1] = PROTOCOL_ERROR = new ErrorCode(1);
        errorCodeArray[2] = INTERNAL_ERROR = new ErrorCode(2);
        errorCodeArray[3] = FLOW_CONTROL_ERROR = new ErrorCode(3);
        errorCodeArray[4] = SETTINGS_TIMEOUT = new ErrorCode(4);
        errorCodeArray[5] = STREAM_CLOSED = new ErrorCode(5);
        errorCodeArray[6] = FRAME_SIZE_ERROR = new ErrorCode(6);
        errorCodeArray[7] = REFUSED_STREAM = new ErrorCode(7);
        errorCodeArray[8] = CANCEL = new ErrorCode(8);
        errorCodeArray[9] = COMPRESSION_ERROR = new ErrorCode(9);
        errorCodeArray[10] = CONNECT_ERROR = new ErrorCode(10);
        errorCodeArray[11] = ENHANCE_YOUR_CALM = new ErrorCode(11);
        errorCodeArray[12] = INADEQUATE_SECURITY = new ErrorCode(12);
        errorCodeArray[13] = HTTP_1_1_REQUIRED = new ErrorCode(13);
        $VALUES = errorCodeArray;
        Companion = new Companion(null);
    }

    public final int getHttpCode() {
        return this.httpCode;
    }

    private ErrorCode(int httpCode) {
        this.httpCode = httpCode;
    }

    public static ErrorCode[] values() {
        return (ErrorCode[])$VALUES.clone();
    }

    public static ErrorCode valueOf(String string) {
        return Enum.valueOf(ErrorCode.class, string);
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u00a8\u0006\u0007"}, d2={"Lokhttp3/internal/http2/ErrorCode$Companion;", "", "()V", "fromHttp2", "Lokhttp3/internal/http2/ErrorCode;", "code", "", "okhttp"})
    public static final class Companion {
        @Nullable
        public final ErrorCode fromHttp2(int code) {
            ErrorCode errorCode;
            block1: {
                ErrorCode[] errorCodeArray = ErrorCode.values();
                boolean bl = false;
                ErrorCode[] errorCodeArray2 = errorCodeArray;
                boolean bl2 = false;
                ErrorCode[] errorCodeArray3 = errorCodeArray2;
                int n = errorCodeArray3.length;
                for (int i = 0; i < n; ++i) {
                    ErrorCode errorCode2;
                    ErrorCode it = errorCode2 = errorCodeArray3[i];
                    boolean bl3 = false;
                    if (!(it.getHttpCode() == code)) continue;
                    errorCode = errorCode2;
                    break block1;
                }
                errorCode = null;
            }
            return errorCode;
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

