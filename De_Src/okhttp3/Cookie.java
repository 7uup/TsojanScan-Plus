/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement
 */
package okhttp3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.internal.HostnamesKt;
import okhttp3.internal.Util;
import okhttp3.internal.http.DatesKt;
import okhttp3.internal.publicsuffix.PublicSuffixDatabase;
import org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u0000 &2\u00020\u0001:\u0002%&BO\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\f\u001a\u00020\n\u0012\u0006\u0010\r\u001a\u00020\n\u00a2\u0006\u0002\u0010\u000eJ\r\u0010\u0007\u001a\u00020\u0003H\u0007\u00a2\u0006\u0002\b\u0012J\u0013\u0010\u0013\u001a\u00020\n2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\r\u0010\u0005\u001a\u00020\u0006H\u0007\u00a2\u0006\u0002\b\u0015J\b\u0010\u0016\u001a\u00020\u0017H\u0017J\r\u0010\r\u001a\u00020\nH\u0007\u00a2\u0006\u0002\b\u0018J\r\u0010\u000b\u001a\u00020\nH\u0007\u00a2\u0006\u0002\b\u0019J\u000e\u0010\u001a\u001a\u00020\n2\u0006\u0010\u001b\u001a\u00020\u001cJ\r\u0010\u0002\u001a\u00020\u0003H\u0007\u00a2\u0006\u0002\b\u001dJ\r\u0010\b\u001a\u00020\u0003H\u0007\u00a2\u0006\u0002\b\u001eJ\r\u0010\f\u001a\u00020\nH\u0007\u00a2\u0006\u0002\b\u001fJ\r\u0010\t\u001a\u00020\nH\u0007\u00a2\u0006\u0002\b J\b\u0010!\u001a\u00020\u0003H\u0016J\u0015\u0010!\u001a\u00020\u00032\u0006\u0010\"\u001a\u00020\nH\u0000\u00a2\u0006\u0002\b#J\r\u0010\u0004\u001a\u00020\u0003H\u0007\u00a2\u0006\u0002\b$R\u0013\u0010\u0007\u001a\u00020\u00038\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u000fR\u0013\u0010\u0005\u001a\u00020\u00068\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0010R\u0013\u0010\r\u001a\u00020\n8\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0011R\u0013\u0010\u000b\u001a\u00020\n8\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0011R\u0013\u0010\u0002\u001a\u00020\u00038\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u000fR\u0013\u0010\b\u001a\u00020\u00038\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u000fR\u0013\u0010\f\u001a\u00020\n8\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0011R\u0013\u0010\t\u001a\u00020\n8\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0011R\u0013\u0010\u0004\u001a\u00020\u00038\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u000f\u00a8\u0006'"}, d2={"Lokhttp3/Cookie;", "", "name", "", "value", "expiresAt", "", "domain", "path", "secure", "", "httpOnly", "persistent", "hostOnly", "(Ljava/lang/String;Ljava/lang/String;JLjava/lang/String;Ljava/lang/String;ZZZZ)V", "()Ljava/lang/String;", "()J", "()Z", "-deprecated_domain", "equals", "other", "-deprecated_expiresAt", "hashCode", "", "-deprecated_hostOnly", "-deprecated_httpOnly", "matches", "url", "Lokhttp3/HttpUrl;", "-deprecated_name", "-deprecated_path", "-deprecated_persistent", "-deprecated_secure", "toString", "forObsoleteRfc2965", "toString$okhttp", "-deprecated_value", "Builder", "Companion", "okhttp"})
public final class Cookie {
    @NotNull
    private final String name;
    @NotNull
    private final String value;
    private final long expiresAt;
    @NotNull
    private final String domain;
    @NotNull
    private final String path;
    private final boolean secure;
    private final boolean httpOnly;
    private final boolean persistent;
    private final boolean hostOnly;
    private static final Pattern YEAR_PATTERN;
    private static final Pattern MONTH_PATTERN;
    private static final Pattern DAY_OF_MONTH_PATTERN;
    private static final Pattern TIME_PATTERN;
    public static final Companion Companion;

    public final boolean matches(@NotNull HttpUrl url) {
        boolean domainMatch;
        Intrinsics.checkNotNullParameter(url, "url");
        boolean bl = domainMatch = this.hostOnly ? Intrinsics.areEqual(url.host(), this.domain) : Cookie.Companion.domainMatch(url.host(), this.domain);
        if (!domainMatch) {
            return false;
        }
        if (!Cookie.Companion.pathMatch(url, this.path)) {
            return false;
        }
        return !this.secure || url.isHttps();
    }

    public boolean equals(@Nullable Object other) {
        return other instanceof Cookie && Intrinsics.areEqual(((Cookie)other).name, this.name) && Intrinsics.areEqual(((Cookie)other).value, this.value) && ((Cookie)other).expiresAt == this.expiresAt && Intrinsics.areEqual(((Cookie)other).domain, this.domain) && Intrinsics.areEqual(((Cookie)other).path, this.path) && ((Cookie)other).secure == this.secure && ((Cookie)other).httpOnly == this.httpOnly && ((Cookie)other).persistent == this.persistent && ((Cookie)other).hostOnly == this.hostOnly;
    }

    @IgnoreJRERequirement
    public int hashCode() {
        int result = 17;
        result = 31 * result + this.name.hashCode();
        result = 31 * result + this.value.hashCode();
        result = 31 * result + Long.hashCode(this.expiresAt);
        result = 31 * result + this.domain.hashCode();
        result = 31 * result + this.path.hashCode();
        result = 31 * result + Boolean.hashCode(this.secure);
        result = 31 * result + Boolean.hashCode(this.httpOnly);
        result = 31 * result + Boolean.hashCode(this.persistent);
        result = 31 * result + Boolean.hashCode(this.hostOnly);
        return result;
    }

    @NotNull
    public String toString() {
        return this.toString$okhttp(false);
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="name"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_name")
    @NotNull
    public final String -deprecated_name() {
        return this.name;
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="value"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_value")
    @NotNull
    public final String -deprecated_value() {
        return this.value;
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="persistent"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_persistent")
    public final boolean -deprecated_persistent() {
        return this.persistent;
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="expiresAt"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_expiresAt")
    public final long -deprecated_expiresAt() {
        return this.expiresAt;
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="hostOnly"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_hostOnly")
    public final boolean -deprecated_hostOnly() {
        return this.hostOnly;
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="domain"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_domain")
    @NotNull
    public final String -deprecated_domain() {
        return this.domain;
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="path"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_path")
    @NotNull
    public final String -deprecated_path() {
        return this.path;
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="httpOnly"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_httpOnly")
    public final boolean -deprecated_httpOnly() {
        return this.httpOnly;
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="secure"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_secure")
    public final boolean -deprecated_secure() {
        return this.secure;
    }

    @NotNull
    public final String toString$okhttp(boolean forObsoleteRfc2965) {
        boolean bl = false;
        boolean bl2 = false;
        StringBuilder stringBuilder = new StringBuilder();
        boolean bl3 = false;
        boolean bl4 = false;
        StringBuilder $this$buildString = stringBuilder;
        boolean bl5 = false;
        $this$buildString.append(this.name);
        $this$buildString.append('=');
        $this$buildString.append(this.value);
        if (this.persistent) {
            if (this.expiresAt == Long.MIN_VALUE) {
                $this$buildString.append("; max-age=0");
            } else {
                $this$buildString.append("; expires=").append(DatesKt.toHttpDateString(new Date(this.expiresAt)));
            }
        }
        if (!this.hostOnly) {
            $this$buildString.append("; domain=");
            if (forObsoleteRfc2965) {
                $this$buildString.append(".");
            }
            $this$buildString.append(this.domain);
        }
        $this$buildString.append("; path=").append(this.path);
        if (this.secure) {
            $this$buildString.append("; secure");
        }
        if (this.httpOnly) {
            $this$buildString.append("; httponly");
        }
        String string = $this$buildString.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString()");
        return string;
    }

    @JvmName(name="name")
    @NotNull
    public final String name() {
        return this.name;
    }

    @JvmName(name="value")
    @NotNull
    public final String value() {
        return this.value;
    }

    @JvmName(name="expiresAt")
    public final long expiresAt() {
        return this.expiresAt;
    }

    @JvmName(name="domain")
    @NotNull
    public final String domain() {
        return this.domain;
    }

    @JvmName(name="path")
    @NotNull
    public final String path() {
        return this.path;
    }

    @JvmName(name="secure")
    public final boolean secure() {
        return this.secure;
    }

    @JvmName(name="httpOnly")
    public final boolean httpOnly() {
        return this.httpOnly;
    }

    @JvmName(name="persistent")
    public final boolean persistent() {
        return this.persistent;
    }

    @JvmName(name="hostOnly")
    public final boolean hostOnly() {
        return this.hostOnly;
    }

    private Cookie(String name, String value, long expiresAt, String domain, String path, boolean secure, boolean httpOnly, boolean persistent, boolean hostOnly) {
        this.name = name;
        this.value = value;
        this.expiresAt = expiresAt;
        this.domain = domain;
        this.path = path;
        this.secure = secure;
        this.httpOnly = httpOnly;
        this.persistent = persistent;
        this.hostOnly = hostOnly;
    }

    static {
        Companion = new Companion(null);
        YEAR_PATTERN = Pattern.compile("(\\d{2,4})[^\\d]*");
        MONTH_PATTERN = Pattern.compile("(?i)(jan|feb|mar|apr|may|jun|jul|aug|sep|oct|nov|dec).*");
        DAY_OF_MONTH_PATTERN = Pattern.compile("(\\d{1,2})[^\\d]*");
        TIME_PATTERN = Pattern.compile("(\\d{1,2}):(\\d{1,2}):(\\d{1,2})[^\\d]*");
    }

    public /* synthetic */ Cookie(String name, String value, long expiresAt, String domain, String path, boolean secure, boolean httpOnly, boolean persistent, boolean hostOnly, DefaultConstructorMarker $constructor_marker) {
        this(name, value, expiresAt, domain, path, secure, httpOnly, persistent, hostOnly);
    }

    @JvmStatic
    @Nullable
    public static final Cookie parse(@NotNull HttpUrl url, @NotNull String setCookie) {
        return Companion.parse(url, setCookie);
    }

    @JvmStatic
    @NotNull
    public static final List<Cookie> parseAll(@NotNull HttpUrl url, @NotNull Headers headers) {
        return Companion.parseAll(url, headers);
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u000f\u001a\u00020\u0010J\u000e\u0010\u0003\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0004J\u0018\u0010\u0003\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u000e\u0010\u0005\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0011\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0004J\u0006\u0010\t\u001a\u00020\u0000J\u000e\u0010\n\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0004J\u000e\u0010\u000b\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\u0004J\u0006\u0010\r\u001a\u00020\u0000J\u000e\u0010\u000e\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u0004R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2={"Lokhttp3/Cookie$Builder;", "", "()V", "domain", "", "expiresAt", "", "hostOnly", "", "httpOnly", "name", "path", "persistent", "secure", "value", "build", "Lokhttp3/Cookie;", "hostOnlyDomain", "okhttp"})
    public static final class Builder {
        private String name;
        private String value;
        private long expiresAt = 253402300799999L;
        private String domain;
        private String path = "/";
        private boolean secure;
        private boolean httpOnly;
        private boolean persistent;
        private boolean hostOnly;

        @NotNull
        public final Builder name(@NotNull String name) {
            Intrinsics.checkNotNullParameter(name, "name");
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            String string = name;
            boolean bl4 = false;
            boolean bl5 = Intrinsics.areEqual(((Object)StringsKt.trim((CharSequence)string)).toString(), name);
            bl4 = false;
            boolean bl6 = false;
            if (!bl5) {
                boolean bl7 = false;
                String string2 = "name is not trimmed";
                throw (Throwable)new IllegalArgumentException(string2.toString());
            }
            $this$apply.name = name;
            return builder;
        }

        @NotNull
        public final Builder value(@NotNull String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            String string = value;
            boolean bl4 = false;
            boolean bl5 = Intrinsics.areEqual(((Object)StringsKt.trim((CharSequence)string)).toString(), value);
            bl4 = false;
            boolean bl6 = false;
            if (!bl5) {
                boolean bl7 = false;
                String string2 = "value is not trimmed";
                throw (Throwable)new IllegalArgumentException(string2.toString());
            }
            $this$apply.value = value;
            return builder;
        }

        @NotNull
        public final Builder expiresAt(long expiresAt) {
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            long expiresAt2 = expiresAt;
            if (expiresAt2 <= 0L) {
                expiresAt2 = Long.MIN_VALUE;
            }
            if (expiresAt2 > 253402300799999L) {
                expiresAt2 = 253402300799999L;
            }
            $this$apply.expiresAt = expiresAt2;
            $this$apply.persistent = true;
            return builder;
        }

        @NotNull
        public final Builder domain(@NotNull String domain) {
            Intrinsics.checkNotNullParameter(domain, "domain");
            return this.domain(domain, false);
        }

        @NotNull
        public final Builder hostOnlyDomain(@NotNull String domain) {
            Intrinsics.checkNotNullParameter(domain, "domain");
            return this.domain(domain, true);
        }

        private final Builder domain(String domain, boolean hostOnly) {
            String canonicalDomain;
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            String string = HostnamesKt.toCanonicalHost(domain);
            if (string == null) {
                throw (Throwable)new IllegalArgumentException("unexpected domain: " + domain);
            }
            $this$apply.domain = canonicalDomain = string;
            $this$apply.hostOnly = hostOnly;
            return builder;
        }

        @NotNull
        public final Builder path(@NotNull String path) {
            Intrinsics.checkNotNullParameter(path, "path");
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            boolean bl4 = StringsKt.startsWith$default(path, "/", false, 2, null);
            boolean bl5 = false;
            boolean bl6 = false;
            if (!bl4) {
                boolean bl7 = false;
                String string = "path must start with '/'";
                throw (Throwable)new IllegalArgumentException(string.toString());
            }
            $this$apply.path = path;
            return builder;
        }

        @NotNull
        public final Builder secure() {
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            $this$apply.secure = true;
            return builder;
        }

        @NotNull
        public final Builder httpOnly() {
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            $this$apply.httpOnly = true;
            return builder;
        }

        @NotNull
        public final Cookie build() {
            String string = this.name;
            if (string == null) {
                throw (Throwable)new NullPointerException("builder.name == null");
            }
            String string2 = this.value;
            if (string2 == null) {
                throw (Throwable)new NullPointerException("builder.value == null");
            }
            String string3 = this.domain;
            if (string3 == null) {
                throw (Throwable)new NullPointerException("builder.domain == null");
            }
            return new Cookie(string, string2, this.expiresAt, string3, this.path, this.secure, this.httpOnly, this.persistent, this.hostOnly, null);
        }
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J(\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0018\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\fH\u0002J'\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\fH\u0000\u00a2\u0006\u0002\b\u001bJ\u001a\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\fH\u0007J\u001e\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00150\u001d2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001e\u001a\u00020\u001fH\u0007J\u0010\u0010 \u001a\u00020\f2\u0006\u0010!\u001a\u00020\fH\u0002J \u0010\"\u001a\u00020\u00172\u0006\u0010!\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\nH\u0002J\u0010\u0010#\u001a\u00020\u00172\u0006\u0010!\u001a\u00020\fH\u0002J\u0018\u0010$\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010%\u001a\u00020\fH\u0002R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006&"}, d2={"Lokhttp3/Cookie$Companion;", "", "()V", "DAY_OF_MONTH_PATTERN", "Ljava/util/regex/Pattern;", "kotlin.jvm.PlatformType", "MONTH_PATTERN", "TIME_PATTERN", "YEAR_PATTERN", "dateCharacterOffset", "", "input", "", "pos", "limit", "invert", "", "domainMatch", "urlHost", "domain", "parse", "Lokhttp3/Cookie;", "currentTimeMillis", "", "url", "Lokhttp3/HttpUrl;", "setCookie", "parse$okhttp", "parseAll", "", "headers", "Lokhttp3/Headers;", "parseDomain", "s", "parseExpires", "parseMaxAge", "pathMatch", "path", "okhttp"})
    public static final class Companion {
        private final boolean domainMatch(String urlHost, String domain) {
            if (Intrinsics.areEqual(urlHost, domain)) {
                return true;
            }
            return StringsKt.endsWith$default(urlHost, domain, false, 2, null) && urlHost.charAt(urlHost.length() - domain.length() - 1) == '.' && !Util.canParseAsIpAddress(urlHost);
        }

        private final boolean pathMatch(HttpUrl url, String path) {
            String urlPath = url.encodedPath();
            if (Intrinsics.areEqual(urlPath, path)) {
                return true;
            }
            if (StringsKt.startsWith$default(urlPath, path, false, 2, null)) {
                if (StringsKt.endsWith$default(path, "/", false, 2, null)) {
                    return true;
                }
                if (urlPath.charAt(path.length()) == '/') {
                    return true;
                }
            }
            return false;
        }

        @JvmStatic
        @Nullable
        public final Cookie parse(@NotNull HttpUrl url, @NotNull String setCookie) {
            Intrinsics.checkNotNullParameter(url, "url");
            Intrinsics.checkNotNullParameter(setCookie, "setCookie");
            return this.parse$okhttp(System.currentTimeMillis(), url, setCookie);
        }

        @Nullable
        public final Cookie parse$okhttp(long currentTimeMillis, @NotNull HttpUrl url, @NotNull String setCookie) {
            long deltaMilliseconds;
            Intrinsics.checkNotNullParameter(url, "url");
            Intrinsics.checkNotNullParameter(setCookie, "setCookie");
            int cookiePairEnd = Util.delimiterOffset$default(setCookie, ';', 0, 0, 6, null);
            int pairEqualsSign = Util.delimiterOffset$default(setCookie, '=', 0, cookiePairEnd, 2, null);
            if (pairEqualsSign == cookiePairEnd) {
                return null;
            }
            String cookieName = Util.trimSubstring$default(setCookie, 0, pairEqualsSign, 1, null);
            CharSequence charSequence = cookieName;
            boolean bl = false;
            if (charSequence.length() == 0 || Util.indexOfControlOrNonAscii(cookieName) != -1) {
                return null;
            }
            String cookieValue = Util.trimSubstring(setCookie, pairEqualsSign + 1, cookiePairEnd);
            if (Util.indexOfControlOrNonAscii(cookieValue) != -1) {
                return null;
            }
            long expiresAt = 253402300799999L;
            long deltaSeconds = -1L;
            String domain = null;
            String path = null;
            boolean secureOnly = false;
            boolean httpOnly = false;
            boolean hostOnly = true;
            boolean persistent = false;
            int pos = cookiePairEnd + 1;
            int limit = setCookie.length();
            while (pos < limit) {
                int attributePairEnd = Util.delimiterOffset(setCookie, ';', pos, limit);
                int attributeEqualsSign = Util.delimiterOffset(setCookie, '=', pos, attributePairEnd);
                String attributeName = Util.trimSubstring(setCookie, pos, attributeEqualsSign);
                String attributeValue = attributeEqualsSign < attributePairEnd ? Util.trimSubstring(setCookie, attributeEqualsSign + 1, attributePairEnd) : "";
                if (StringsKt.equals(attributeName, "expires", true)) {
                    try {
                        expiresAt = this.parseExpires(attributeValue, 0, attributeValue.length());
                        persistent = true;
                    } catch (IllegalArgumentException illegalArgumentException) {}
                } else if (StringsKt.equals(attributeName, "max-age", true)) {
                    try {
                        deltaSeconds = this.parseMaxAge(attributeValue);
                        persistent = true;
                    } catch (NumberFormatException numberFormatException) {}
                } else if (StringsKt.equals(attributeName, "domain", true)) {
                    try {
                        domain = this.parseDomain(attributeValue);
                        hostOnly = false;
                    } catch (IllegalArgumentException illegalArgumentException) {}
                } else if (StringsKt.equals(attributeName, "path", true)) {
                    path = attributeValue;
                } else if (StringsKt.equals(attributeName, "secure", true)) {
                    secureOnly = true;
                } else if (StringsKt.equals(attributeName, "httponly", true)) {
                    httpOnly = true;
                }
                pos = attributePairEnd + 1;
            }
            if (deltaSeconds == Long.MIN_VALUE) {
                expiresAt = Long.MIN_VALUE;
            } else if (deltaSeconds != -1L && ((expiresAt = currentTimeMillis + (deltaMilliseconds = deltaSeconds <= 9223372036854775L ? deltaSeconds * (long)1000 : Long.MAX_VALUE)) < currentTimeMillis || expiresAt > 253402300799999L)) {
                expiresAt = 253402300799999L;
            }
            String urlHost = url.host();
            if (domain == null) {
                domain = urlHost;
            } else if (!this.domainMatch(urlHost, domain)) {
                return null;
            }
            if (urlHost.length() != domain.length() && PublicSuffixDatabase.Companion.get().getEffectiveTldPlusOne(domain) == null) {
                return null;
            }
            if (path == null || !StringsKt.startsWith$default(path, "/", false, 2, null)) {
                String string;
                String encodedPath = url.encodedPath();
                int lastSlash = StringsKt.lastIndexOf$default((CharSequence)encodedPath, '/', 0, false, 6, null);
                if (lastSlash != 0) {
                    String string2 = encodedPath;
                    int n = 0;
                    boolean bl2 = false;
                    String string3 = string2;
                    if (string3 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                    }
                    String string4 = string3.substring(n, lastSlash);
                    string = string4;
                    Intrinsics.checkNotNullExpressionValue(string4, "(this as java.lang.Strin\u2026ing(startIndex, endIndex)");
                } else {
                    string = "/";
                }
                path = string;
            }
            return new Cookie(cookieName, cookieValue, expiresAt, domain, path, secureOnly, httpOnly, persistent, hostOnly, null);
        }

        private final long parseExpires(String s2, int pos, int limit) {
            boolean bl;
            int pos2 = pos;
            pos2 = this.dateCharacterOffset(s2, pos2, limit, false);
            int hour = -1;
            int minute = -1;
            int second = -1;
            int dayOfMonth = -1;
            int month = -1;
            int year = -1;
            Matcher matcher = TIME_PATTERN.matcher(s2);
            while (pos2 < limit) {
                String string;
                int end = this.dateCharacterOffset(s2, pos2 + 1, limit, true);
                matcher.region(pos2, end);
                if (hour == -1 && matcher.usePattern(TIME_PATTERN).matches()) {
                    String string2 = matcher.group(1);
                    Intrinsics.checkNotNullExpressionValue(string2, "matcher.group(1)");
                    string = string2;
                    boolean bl2 = false;
                    hour = Integer.parseInt(string);
                    String string3 = matcher.group(2);
                    Intrinsics.checkNotNullExpressionValue(string3, "matcher.group(2)");
                    string = string3;
                    bl2 = false;
                    minute = Integer.parseInt(string);
                    String string4 = matcher.group(3);
                    Intrinsics.checkNotNullExpressionValue(string4, "matcher.group(3)");
                    string = string4;
                    bl2 = false;
                    second = Integer.parseInt(string);
                } else if (dayOfMonth == -1 && matcher.usePattern(DAY_OF_MONTH_PATTERN).matches()) {
                    Intrinsics.checkNotNullExpressionValue(matcher.group(1), "matcher.group(1)");
                    boolean bl3 = false;
                    dayOfMonth = Integer.parseInt(string);
                } else if (month == -1 && matcher.usePattern(MONTH_PATTERN).matches()) {
                    String monthString;
                    Locale locale;
                    String string5;
                    Intrinsics.checkNotNullExpressionValue(matcher.group(1), "matcher.group(1)");
                    Intrinsics.checkNotNullExpressionValue(Locale.US, "Locale.US");
                    bl = false;
                    String string6 = string5;
                    if (string6 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                    }
                    Intrinsics.checkNotNullExpressionValue(string6.toLowerCase(locale), "(this as java.lang.String).toLowerCase(locale)");
                    String string7 = MONTH_PATTERN.pattern();
                    Intrinsics.checkNotNullExpressionValue(string7, "MONTH_PATTERN.pattern()");
                    month = StringsKt.indexOf$default((CharSequence)string7, monthString, 0, false, 6, null) / 4;
                } else if (year == -1 && matcher.usePattern(YEAR_PATTERN).matches()) {
                    Intrinsics.checkNotNullExpressionValue(matcher.group(1), "matcher.group(1)");
                    boolean bl4 = false;
                    year = Integer.parseInt(string);
                }
                pos2 = this.dateCharacterOffset(s2, end + 1, limit, false);
            }
            int n = year;
            if (70 <= n && 99 >= n) {
                year += 1900;
            }
            if (0 <= (n = year) && 69 >= n) {
                year += 2000;
            }
            n = year >= 1601 ? 1 : 0;
            boolean bl5 = false;
            boolean bl6 = false;
            bl6 = false;
            boolean bl7 = false;
            if (n == 0) {
                bl = false;
                String string = "Failed requirement.";
                throw (Throwable)new IllegalArgumentException(string.toString());
            }
            n = month != -1 ? 1 : 0;
            bl5 = false;
            bl6 = false;
            bl6 = false;
            bl7 = false;
            if (n == 0) {
                bl = false;
                String string = "Failed requirement.";
                throw (Throwable)new IllegalArgumentException(string.toString());
            }
            n = dayOfMonth;
            n = 1 <= n && 31 >= n ? 1 : 0;
            bl5 = false;
            bl6 = false;
            bl6 = false;
            bl7 = false;
            if (n == 0) {
                bl = false;
                String string = "Failed requirement.";
                throw (Throwable)new IllegalArgumentException(string.toString());
            }
            n = hour;
            n = 0 <= n && 23 >= n ? 1 : 0;
            bl5 = false;
            bl6 = false;
            bl6 = false;
            bl7 = false;
            if (n == 0) {
                bl = false;
                String string = "Failed requirement.";
                throw (Throwable)new IllegalArgumentException(string.toString());
            }
            n = minute;
            n = 0 <= n && 59 >= n ? 1 : 0;
            bl5 = false;
            bl6 = false;
            bl6 = false;
            bl7 = false;
            if (n == 0) {
                bl = false;
                String string = "Failed requirement.";
                throw (Throwable)new IllegalArgumentException(string.toString());
            }
            n = second;
            n = 0 <= n && 59 >= n ? 1 : 0;
            bl5 = false;
            bl6 = false;
            bl6 = false;
            bl7 = false;
            if (n == 0) {
                bl = false;
                String string = "Failed requirement.";
                throw (Throwable)new IllegalArgumentException(string.toString());
            }
            GregorianCalendar gregorianCalendar = new GregorianCalendar(Util.UTC);
            bl5 = false;
            bl6 = false;
            GregorianCalendar $this$apply = gregorianCalendar;
            boolean bl8 = false;
            $this$apply.setLenient(false);
            $this$apply.set(1, year);
            $this$apply.set(2, month - 1);
            $this$apply.set(5, dayOfMonth);
            $this$apply.set(11, hour);
            $this$apply.set(12, minute);
            $this$apply.set(13, second);
            $this$apply.set(14, 0);
            return $this$apply.getTimeInMillis();
        }

        /*
         * WARNING - void declaration
         * Enabled aggressive block sorting
         */
        private final int dateCharacterOffset(String input, int pos, int limit, boolean invert) {
            int n = pos;
            int n2 = limit;
            while (n < n2) {
                char c;
                void i;
                char c2 = input.charAt((int)i);
                boolean dateCharacter = c2 < ' ' && c2 != '\t' || c2 >= '\u007f' || '0' <= (c = c2) && '9' >= c || 'a' <= (c = c2) && 'z' >= c || 'A' <= (c = c2) && 'Z' >= c || c2 == ':';
                if (dateCharacter == !invert) {
                    return (int)i;
                }
                ++i;
            }
            return limit;
        }

        private final long parseMaxAge(String s2) {
            try {
                String string = s2;
                boolean bl = false;
                long parsed = Long.parseLong(string);
                return parsed <= 0L ? Long.MIN_VALUE : parsed;
            } catch (NumberFormatException e) {
                CharSequence charSequence = s2;
                Object object = "-?\\d+";
                boolean bl = false;
                object = new Regex((String)object);
                bl = false;
                if (((Regex)object).matches(charSequence)) {
                    return StringsKt.startsWith$default(s2, "-", false, 2, null) ? Long.MIN_VALUE : Long.MAX_VALUE;
                }
                throw (Throwable)e;
            }
        }

        private final String parseDomain(String s2) {
            boolean bl = !StringsKt.endsWith$default(s2, ".", false, 2, null);
            boolean bl2 = false;
            boolean bl3 = false;
            bl3 = false;
            boolean bl4 = false;
            if (!bl) {
                boolean bl5 = false;
                String string = "Failed requirement.";
                throw (Throwable)new IllegalArgumentException(string.toString());
            }
            String string = HostnamesKt.toCanonicalHost(StringsKt.removePrefix(s2, (CharSequence)"."));
            if (string == null) {
                throw (Throwable)new IllegalArgumentException();
            }
            return string;
        }

        /*
         * WARNING - void declaration
         */
        @JvmStatic
        @NotNull
        public final List<Cookie> parseAll(@NotNull HttpUrl url, @NotNull Headers headers) {
            List<Cookie> list;
            Intrinsics.checkNotNullParameter(url, "url");
            Intrinsics.checkNotNullParameter(headers, "headers");
            List<String> cookieStrings = headers.values("Set-Cookie");
            List cookies = null;
            int n = 0;
            int n2 = cookieStrings.size();
            while (n < n2) {
                void i;
                if (this.parse(url, cookieStrings.get((int)i)) == null) {
                } else {
                    Cookie cookie;
                    if (cookies == null) {
                        boolean bl = false;
                        cookies = new ArrayList();
                    }
                    cookies.add(cookie);
                }
                ++i;
            }
            if (cookies != null) {
                List list2 = Collections.unmodifiableList(cookies);
                list = list2;
                Intrinsics.checkNotNullExpressionValue(list2, "Collections.unmodifiableList(cookies)");
            } else {
                list = CollectionsKt.emptyList();
            }
            return list;
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

