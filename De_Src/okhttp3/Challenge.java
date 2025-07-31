/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okhttp3;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0005B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0014\u0010\u0006\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0012\u0004\u0012\u00020\u00030\u0007\u00a2\u0006\u0002\u0010\bJ\u001b\u0010\u0006\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0012\u0004\u0012\u00020\u00030\u0007H\u0007\u00a2\u0006\u0002\b\u000eJ\r\u0010\n\u001a\u00020\u000bH\u0007\u00a2\u0006\u0002\b\u000fJ\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\u000f\u0010\u0004\u001a\u0004\u0018\u00010\u0003H\u0007\u00a2\u0006\u0002\b\u0015J\r\u0010\u0002\u001a\u00020\u0003H\u0007\u00a2\u0006\u0002\b\u0016J\b\u0010\u0017\u001a\u00020\u0003H\u0016J\u000e\u0010\u0018\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u000bR!\u0010\u0006\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0012\u0004\u0012\u00020\u00030\u00078G\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\tR\u0011\u0010\n\u001a\u00020\u000b8G\u00a2\u0006\u0006\u001a\u0004\b\n\u0010\fR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u00038G\u00a2\u0006\u0006\u001a\u0004\b\u0004\u0010\rR\u0013\u0010\u0002\u001a\u00020\u00038\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\r\u00a8\u0006\u0019"}, d2={"Lokhttp3/Challenge;", "", "scheme", "", "realm", "(Ljava/lang/String;Ljava/lang/String;)V", "authParams", "", "(Ljava/lang/String;Ljava/util/Map;)V", "()Ljava/util/Map;", "charset", "Ljava/nio/charset/Charset;", "()Ljava/nio/charset/Charset;", "()Ljava/lang/String;", "-deprecated_authParams", "-deprecated_charset", "equals", "", "other", "hashCode", "", "-deprecated_realm", "-deprecated_scheme", "toString", "withCharset", "okhttp"})
public final class Challenge {
    @NotNull
    private final Map<String, String> authParams;
    @NotNull
    private final String scheme;

    @JvmName(name="authParams")
    @NotNull
    public final Map<String, String> authParams() {
        return this.authParams;
    }

    @JvmName(name="realm")
    @Nullable
    public final String realm() {
        return this.authParams.get("realm");
    }

    @JvmName(name="charset")
    @NotNull
    public final Charset charset() {
        String charset = this.authParams.get("charset");
        if (charset != null) {
            try {
                Charset charset2 = Charset.forName(charset);
                Intrinsics.checkNotNullExpressionValue(charset2, "Charset.forName(charset)");
                return charset2;
            } catch (Exception exception) {
                // empty catch block
            }
        }
        Charset charset3 = StandardCharsets.ISO_8859_1;
        Intrinsics.checkNotNullExpressionValue(charset3, "ISO_8859_1");
        return charset3;
    }

    @NotNull
    public final Challenge withCharset(@NotNull Charset charset) {
        Intrinsics.checkNotNullParameter(charset, "charset");
        Map<String, String> authParams = MapsKt.toMutableMap(this.authParams);
        String string = charset.name();
        Intrinsics.checkNotNullExpressionValue(string, "charset.name()");
        authParams.put("charset", string);
        return new Challenge(this.scheme, authParams);
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="scheme"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_scheme")
    @NotNull
    public final String -deprecated_scheme() {
        return this.scheme;
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="authParams"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_authParams")
    @NotNull
    public final Map<String, String> -deprecated_authParams() {
        return this.authParams;
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="realm"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_realm")
    @Nullable
    public final String -deprecated_realm() {
        return this.realm();
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="charset"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_charset")
    @NotNull
    public final Charset -deprecated_charset() {
        return this.charset();
    }

    public boolean equals(@Nullable Object other) {
        return other instanceof Challenge && Intrinsics.areEqual(((Challenge)other).scheme, this.scheme) && Intrinsics.areEqual(((Challenge)other).authParams, this.authParams);
    }

    public int hashCode() {
        int result = 29;
        result = 31 * result + this.scheme.hashCode();
        result = 31 * result + ((Object)this.authParams).hashCode();
        return result;
    }

    @NotNull
    public String toString() {
        return this.scheme + " authParams=" + this.authParams;
    }

    @JvmName(name="scheme")
    @NotNull
    public final String scheme() {
        return this.scheme;
    }

    /*
     * WARNING - void declaration
     */
    public Challenge(@NotNull String scheme, @NotNull Map<String, String> authParams) {
        Intrinsics.checkNotNullParameter(scheme, "scheme");
        Intrinsics.checkNotNullParameter(authParams, "authParams");
        this.scheme = scheme;
        boolean bl = false;
        Map newAuthParams = new LinkedHashMap();
        Object object = authParams;
        boolean bl2 = false;
        Iterator<Map.Entry<String, String>> iterator2 = object.entrySet().iterator();
        while (iterator2.hasNext()) {
            String string;
            void key;
            Map.Entry<String, String> entry;
            Map.Entry<String, String> entry2 = entry = iterator2.next();
            boolean bl3 = false;
            object = entry2.getKey();
            entry2 = entry;
            bl3 = false;
            String value = entry2.getValue();
            if (key != null) {
                Locale locale;
                void var9_12;
                Intrinsics.checkNotNullExpressionValue(Locale.US, "US");
                boolean bl4 = false;
                void v0 = var9_12;
                if (v0 == null) {
                    throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                }
                String string2 = v0.toLowerCase(locale);
                string = string2;
                Intrinsics.checkNotNullExpressionValue(string2, "(this as java.lang.String).toLowerCase(locale)");
            } else {
                string = null;
            }
            String newKey = string;
            newAuthParams.put(newKey, value);
        }
        Map map = Collections.unmodifiableMap(newAuthParams);
        Intrinsics.checkNotNullExpressionValue(map, "unmodifiableMap<String?, String>(newAuthParams)");
        this.authParams = map;
    }

    public Challenge(@NotNull String scheme, @NotNull String realm) {
        Intrinsics.checkNotNullParameter(scheme, "scheme");
        Intrinsics.checkNotNullParameter(realm, "realm");
        Map<String, String> map = Collections.singletonMap("realm", realm);
        Intrinsics.checkNotNullExpressionValue(map, "singletonMap(\"realm\", realm)");
        this(scheme, map);
    }
}

