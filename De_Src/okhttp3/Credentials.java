/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okhttp3;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\"\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\bH\u0007\u00a8\u0006\t"}, d2={"Lokhttp3/Credentials;", "", "()V", "basic", "", "username", "password", "charset", "Ljava/nio/charset/Charset;", "okhttp"})
public final class Credentials {
    public static final Credentials INSTANCE;

    @JvmStatic
    @JvmOverloads
    @NotNull
    public static final String basic(@NotNull String username, @NotNull String password, @NotNull Charset charset) {
        Intrinsics.checkNotNullParameter(username, "username");
        Intrinsics.checkNotNullParameter(password, "password");
        Intrinsics.checkNotNullParameter(charset, "charset");
        String usernameAndPassword = username + ':' + password;
        String encoded = ByteString.Companion.encodeString(usernameAndPassword, charset).base64();
        return "Basic " + encoded;
    }

    public static /* synthetic */ String basic$default(String string, String string2, Charset charset, int n, Object object) {
        if ((n & 4) != 0) {
            Charset charset2 = StandardCharsets.ISO_8859_1;
            Intrinsics.checkNotNullExpressionValue(charset2, "ISO_8859_1");
            charset = charset2;
        }
        return Credentials.basic(string, string2, charset);
    }

    @JvmStatic
    @JvmOverloads
    @NotNull
    public static final String basic(@NotNull String username, @NotNull String password) {
        return Credentials.basic$default(username, password, null, 4, null);
    }

    private Credentials() {
    }

    static {
        Credentials credentials;
        INSTANCE = credentials = new Credentials();
    }
}

