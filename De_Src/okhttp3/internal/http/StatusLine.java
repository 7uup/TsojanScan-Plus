/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okhttp3.internal.http;

import java.io.IOException;
import java.net.ProtocolException;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.Protocol;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u0000 \n2\u00020\u0001:\u0001\nB\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\b\u0010\t\u001a\u00020\u0007H\u0016R\u0010\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00020\u00078\u0006X\u0087\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2={"Lokhttp3/internal/http/StatusLine;", "", "protocol", "Lokhttp3/Protocol;", "code", "", "message", "", "(Lokhttp3/Protocol;ILjava/lang/String;)V", "toString", "Companion", "okhttp"})
public final class StatusLine {
    @JvmField
    @NotNull
    public final Protocol protocol;
    @JvmField
    public final int code;
    @JvmField
    @NotNull
    public final String message;
    public static final int HTTP_TEMP_REDIRECT = 307;
    public static final int HTTP_PERM_REDIRECT = 308;
    public static final int HTTP_MISDIRECTED_REQUEST = 421;
    public static final int HTTP_CONTINUE = 100;
    public static final Companion Companion = new Companion(null);

    @NotNull
    public String toString() {
        boolean bl = false;
        boolean bl2 = false;
        StringBuilder stringBuilder = new StringBuilder();
        boolean bl3 = false;
        boolean bl4 = false;
        StringBuilder $this$buildString = stringBuilder;
        boolean bl5 = false;
        if (this.protocol == Protocol.HTTP_1_0) {
            $this$buildString.append("HTTP/1.0");
        } else {
            $this$buildString.append("HTTP/1.1");
        }
        $this$buildString.append(' ').append(this.code);
        $this$buildString.append(' ').append(this.message);
        String string = stringBuilder.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }

    public StatusLine(@NotNull Protocol protocol, int code, @NotNull String message) {
        Intrinsics.checkNotNullParameter((Object)protocol, "protocol");
        Intrinsics.checkNotNullParameter(message, "message");
        this.protocol = protocol;
        this.code = code;
        this.message = message;
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ\u000e\u0010\f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u000eR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2={"Lokhttp3/internal/http/StatusLine$Companion;", "", "()V", "HTTP_CONTINUE", "", "HTTP_MISDIRECTED_REQUEST", "HTTP_PERM_REDIRECT", "HTTP_TEMP_REDIRECT", "get", "Lokhttp3/internal/http/StatusLine;", "response", "Lokhttp3/Response;", "parse", "statusLine", "", "okhttp"})
    public static final class Companion {
        @NotNull
        public final StatusLine get(@NotNull Response response) {
            Intrinsics.checkNotNullParameter(response, "response");
            return new StatusLine(response.protocol(), response.code(), response.message());
        }

        @NotNull
        public final StatusLine parse(@NotNull String statusLine) throws IOException {
            int n;
            int n2;
            Intrinsics.checkNotNullParameter(statusLine, "statusLine");
            int codeStart = 0;
            Protocol protocol = null;
            if (StringsKt.startsWith$default(statusLine, "HTTP/1.", false, 2, null)) {
                Protocol protocol2;
                if (statusLine.length() < 9 || statusLine.charAt(8) != ' ') {
                    throw (Throwable)new ProtocolException("Unexpected status line: " + statusLine);
                }
                int httpMinorVersion = statusLine.charAt(7) - 48;
                codeStart = 9;
                if (httpMinorVersion == 0) {
                    protocol2 = Protocol.HTTP_1_0;
                } else if (httpMinorVersion == 1) {
                    protocol2 = Protocol.HTTP_1_1;
                } else {
                    throw (Throwable)new ProtocolException("Unexpected status line: " + statusLine);
                }
                protocol = protocol2;
            } else if (StringsKt.startsWith$default(statusLine, "ICY ", false, 2, null)) {
                protocol = Protocol.HTTP_1_0;
                codeStart = 4;
            } else {
                throw (Throwable)new ProtocolException("Unexpected status line: " + statusLine);
            }
            if (statusLine.length() < codeStart + 3) {
                throw (Throwable)new ProtocolException("Unexpected status line: " + statusLine);
            }
            try {
                String string = statusLine;
                int n3 = codeStart + 3;
                n2 = 0;
                String string2 = string.substring(codeStart, n3);
                Intrinsics.checkNotNullExpressionValue(string2, "(this as java.lang.Strin\u2026ing(startIndex, endIndex)");
                n = Integer.parseInt(string2);
            } catch (NumberFormatException _) {
                throw (Throwable)new ProtocolException("Unexpected status line: " + statusLine);
            }
            int code = n;
            String message = "";
            if (statusLine.length() > codeStart + 3) {
                if (statusLine.charAt(codeStart + 3) != ' ') {
                    throw (Throwable)new ProtocolException("Unexpected status line: " + statusLine);
                }
                String string = statusLine;
                n2 = codeStart + 4;
                boolean bl = false;
                String string3 = string.substring(n2);
                Intrinsics.checkNotNullExpressionValue(string3, "(this as java.lang.String).substring(startIndex)");
                message = string3;
            }
            return new StatusLine(protocol, code, message);
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

