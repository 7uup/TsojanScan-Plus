/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okhttp3;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntProgression;
import kotlin.ranges.RangesKt;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import okhttp3.internal.HostnamesKt;
import okhttp3.internal.Util;
import okhttp3.internal.publicsuffix.PublicSuffixDatabase;
import okio.Buffer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\"\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 J2\u00020\u0001:\u0002IJBa\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\n\u0012\u0010\u0010\u000b\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\n\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\r\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u000eJ\u000f\u0010\u000f\u001a\u0004\u0018\u00010\u0003H\u0007\u00a2\u0006\u0002\b!J\r\u0010\u0011\u001a\u00020\u0003H\u0007\u00a2\u0006\u0002\b\"J\r\u0010\u0012\u001a\u00020\u0003H\u0007\u00a2\u0006\u0002\b#J\u0013\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00030\nH\u0007\u00a2\u0006\u0002\b$J\u000f\u0010\u0015\u001a\u0004\u0018\u00010\u0003H\u0007\u00a2\u0006\u0002\b%J\r\u0010\u0016\u001a\u00020\u0003H\u0007\u00a2\u0006\u0002\b&J\u0013\u0010'\u001a\u00020\u00182\b\u0010(\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\u000f\u0010\f\u001a\u0004\u0018\u00010\u0003H\u0007\u00a2\u0006\u0002\b)J\b\u0010*\u001a\u00020\bH\u0016J\r\u0010\u0006\u001a\u00020\u0003H\u0007\u00a2\u0006\u0002\b+J\u0006\u0010,\u001a\u00020-J\u0010\u0010,\u001a\u0004\u0018\u00010-2\u0006\u0010.\u001a\u00020\u0003J\r\u0010\u0005\u001a\u00020\u0003H\u0007\u00a2\u0006\u0002\b/J\u0013\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\nH\u0007\u00a2\u0006\u0002\b0J\r\u0010\u001a\u001a\u00020\bH\u0007\u00a2\u0006\u0002\b1J\r\u0010\u0007\u001a\u00020\bH\u0007\u00a2\u0006\u0002\b2J\u000f\u0010\u001c\u001a\u0004\u0018\u00010\u0003H\u0007\u00a2\u0006\u0002\b3J\u0010\u00104\u001a\u0004\u0018\u00010\u00032\u0006\u00105\u001a\u00020\u0003J\u000e\u00106\u001a\u00020\u00032\u0006\u00107\u001a\u00020\bJ\u0013\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00030\u001eH\u0007\u00a2\u0006\u0002\b8J\u0010\u00109\u001a\u0004\u0018\u00010\u00032\u0006\u00107\u001a\u00020\bJ\u0016\u0010:\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\n2\u0006\u00105\u001a\u00020\u0003J\r\u0010 \u001a\u00020\bH\u0007\u00a2\u0006\u0002\b;J\u0006\u0010<\u001a\u00020\u0003J\u0010\u0010=\u001a\u0004\u0018\u00010\u00002\u0006\u0010.\u001a\u00020\u0003J\r\u0010\u0002\u001a\u00020\u0003H\u0007\u00a2\u0006\u0002\b>J\b\u0010?\u001a\u00020\u0003H\u0016J\r\u0010@\u001a\u00020AH\u0007\u00a2\u0006\u0002\bBJ\r\u0010C\u001a\u00020DH\u0007\u00a2\u0006\u0002\b\rJ\b\u0010E\u001a\u0004\u0018\u00010\u0003J\r\u0010B\u001a\u00020AH\u0007\u00a2\u0006\u0002\bFJ\r\u0010\r\u001a\u00020DH\u0007\u00a2\u0006\u0002\bGJ\r\u0010\u0004\u001a\u00020\u0003H\u0007\u00a2\u0006\u0002\bHR\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u00038G\u00a2\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0011\u001a\u00020\u00038G\u00a2\u0006\u0006\u001a\u0004\b\u0011\u0010\u0010R\u0011\u0010\u0012\u001a\u00020\u00038G\u00a2\u0006\u0006\u001a\u0004\b\u0012\u0010\u0010R\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00030\n8G\u00a2\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u0013\u0010\u0015\u001a\u0004\u0018\u00010\u00038G\u00a2\u0006\u0006\u001a\u0004\b\u0015\u0010\u0010R\u0011\u0010\u0016\u001a\u00020\u00038G\u00a2\u0006\u0006\u001a\u0004\b\u0016\u0010\u0010R\u0015\u0010\f\u001a\u0004\u0018\u00010\u00038\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0010R\u0013\u0010\u0006\u001a\u00020\u00038\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0010R\u0011\u0010\u0017\u001a\u00020\u0018\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0019R\u0013\u0010\u0005\u001a\u00020\u00038\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0010R\u0019\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\n8\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0014R\u0011\u0010\u001a\u001a\u00020\b8G\u00a2\u0006\u0006\u001a\u0004\b\u001a\u0010\u001bR\u0013\u0010\u0007\u001a\u00020\b8\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u001bR\u0013\u0010\u001c\u001a\u0004\u0018\u00010\u00038G\u00a2\u0006\u0006\u001a\u0004\b\u001c\u0010\u0010R\u0018\u0010\u000b\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00030\u001e8G\u00a2\u0006\u0006\u001a\u0004\b\u001d\u0010\u001fR\u0011\u0010 \u001a\u00020\b8G\u00a2\u0006\u0006\u001a\u0004\b \u0010\u001bR\u0013\u0010\u0002\u001a\u00020\u00038\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0010R\u000e\u0010\r\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0013\u0010\u0004\u001a\u00020\u00038\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0010\u00a8\u0006K"}, d2={"Lokhttp3/HttpUrl;", "", "scheme", "", "username", "password", "host", "port", "", "pathSegments", "", "queryNamesAndValues", "fragment", "url", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/util/List;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)V", "encodedFragment", "()Ljava/lang/String;", "encodedPassword", "encodedPath", "encodedPathSegments", "()Ljava/util/List;", "encodedQuery", "encodedUsername", "isHttps", "", "()Z", "pathSize", "()I", "query", "queryParameterNames", "", "()Ljava/util/Set;", "querySize", "-deprecated_encodedFragment", "-deprecated_encodedPassword", "-deprecated_encodedPath", "-deprecated_encodedPathSegments", "-deprecated_encodedQuery", "-deprecated_encodedUsername", "equals", "other", "-deprecated_fragment", "hashCode", "-deprecated_host", "newBuilder", "Lokhttp3/HttpUrl$Builder;", "link", "-deprecated_password", "-deprecated_pathSegments", "-deprecated_pathSize", "-deprecated_port", "-deprecated_query", "queryParameter", "name", "queryParameterName", "index", "-deprecated_queryParameterNames", "queryParameterValue", "queryParameterValues", "-deprecated_querySize", "redact", "resolve", "-deprecated_scheme", "toString", "toUri", "Ljava/net/URI;", "uri", "toUrl", "Ljava/net/URL;", "topPrivateDomain", "-deprecated_uri", "-deprecated_url", "-deprecated_username", "Builder", "Companion", "okhttp"})
public final class HttpUrl {
    private final boolean isHttps;
    @NotNull
    private final String scheme;
    @NotNull
    private final String username;
    @NotNull
    private final String password;
    @NotNull
    private final String host;
    private final int port;
    @NotNull
    private final List<String> pathSegments;
    private final List<String> queryNamesAndValues;
    @Nullable
    private final String fragment;
    private final String url;
    private static final char[] HEX_DIGITS;
    @NotNull
    public static final String USERNAME_ENCODE_SET = " \"':;<=>@[]^`{}|/\\?#";
    @NotNull
    public static final String PASSWORD_ENCODE_SET = " \"':;<=>@[]^`{}|/\\?#";
    @NotNull
    public static final String PATH_SEGMENT_ENCODE_SET = " \"<>^`{}|/\\?#";
    @NotNull
    public static final String PATH_SEGMENT_ENCODE_SET_URI = "[]";
    @NotNull
    public static final String QUERY_ENCODE_SET = " \"'<>#";
    @NotNull
    public static final String QUERY_COMPONENT_REENCODE_SET = " \"'<>#&=";
    @NotNull
    public static final String QUERY_COMPONENT_ENCODE_SET = " !\"#$&'(),/:;<=>?@[]\\^`{|}~";
    @NotNull
    public static final String QUERY_COMPONENT_ENCODE_SET_URI = "\\^`{|}";
    @NotNull
    public static final String FORM_ENCODE_SET = " \"':;<=>@[]^`{}|/\\?#&!$(),~";
    @NotNull
    public static final String FRAGMENT_ENCODE_SET = "";
    @NotNull
    public static final String FRAGMENT_ENCODE_SET_URI = " \"#<>\\^`{|}";
    public static final Companion Companion;

    public final boolean isHttps() {
        return this.isHttps;
    }

    @JvmName(name="url")
    @NotNull
    public final URL url() {
        try {
            return new URL(this.url);
        } catch (MalformedURLException e) {
            throw (Throwable)new RuntimeException(e);
        }
    }

    @JvmName(name="uri")
    @NotNull
    public final URI uri() {
        URI uRI;
        String uri = this.newBuilder().reencodeForUri$okhttp().toString();
        try {
            uRI = new URI(uri);
        } catch (URISyntaxException e) {
            URI uRI2;
            try {
                CharSequence charSequence = uri;
                Regex regex = new Regex("[\\u0000-\\u001F\\u007F-\\u009F\\p{javaWhitespace}]");
                String string = FRAGMENT_ENCODE_SET;
                boolean bl = false;
                String stripped = regex.replace(charSequence, string);
                uRI2 = URI.create(stripped);
            } catch (Exception e1) {
                throw (Throwable)new RuntimeException(e);
            }
            URI uRI3 = uRI2;
            Intrinsics.checkNotNullExpressionValue(uRI3, "try {\n        val stripp\u2026e) // Unexpected!\n      }");
            uRI = uRI3;
        }
        return uRI;
    }

    @JvmName(name="encodedUsername")
    @NotNull
    public final String encodedUsername() {
        CharSequence charSequence = this.username;
        boolean bl = false;
        if (charSequence.length() == 0) {
            return FRAGMENT_ENCODE_SET;
        }
        int usernameStart = this.scheme.length() + 3;
        int usernameEnd = Util.delimiterOffset(this.url, ":@", usernameStart, this.url.length());
        String string = this.url;
        boolean bl2 = false;
        String string2 = string;
        if (string2 == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        String string3 = string2.substring(usernameStart, usernameEnd);
        Intrinsics.checkNotNullExpressionValue(string3, "(this as java.lang.Strin\u2026ing(startIndex, endIndex)");
        return string3;
    }

    @JvmName(name="encodedPassword")
    @NotNull
    public final String encodedPassword() {
        CharSequence charSequence = this.password;
        boolean bl = false;
        if (charSequence.length() == 0) {
            return FRAGMENT_ENCODE_SET;
        }
        int passwordStart = StringsKt.indexOf$default((CharSequence)this.url, ':', this.scheme.length() + 3, false, 4, null) + 1;
        int passwordEnd = StringsKt.indexOf$default((CharSequence)this.url, '@', 0, false, 6, null);
        String string = this.url;
        boolean bl2 = false;
        String string2 = string;
        if (string2 == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        String string3 = string2.substring(passwordStart, passwordEnd);
        Intrinsics.checkNotNullExpressionValue(string3, "(this as java.lang.Strin\u2026ing(startIndex, endIndex)");
        return string3;
    }

    @JvmName(name="pathSize")
    public final int pathSize() {
        return this.pathSegments.size();
    }

    @JvmName(name="encodedPath")
    @NotNull
    public final String encodedPath() {
        int pathStart = StringsKt.indexOf$default((CharSequence)this.url, '/', this.scheme.length() + 3, false, 4, null);
        int pathEnd = Util.delimiterOffset(this.url, "?#", pathStart, this.url.length());
        String string = this.url;
        boolean bl = false;
        String string2 = string;
        if (string2 == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        String string3 = string2.substring(pathStart, pathEnd);
        Intrinsics.checkNotNullExpressionValue(string3, "(this as java.lang.Strin\u2026ing(startIndex, endIndex)");
        return string3;
    }

    @JvmName(name="encodedPathSegments")
    @NotNull
    public final List<String> encodedPathSegments() {
        int pathStart = StringsKt.indexOf$default((CharSequence)this.url, '/', this.scheme.length() + 3, false, 4, null);
        int pathEnd = Util.delimiterOffset(this.url, "?#", pathStart, this.url.length());
        boolean bl = false;
        List result = new ArrayList();
        int i = pathStart;
        while (i < pathEnd) {
            int segmentEnd = Util.delimiterOffset(this.url, '/', ++i, pathEnd);
            String string = this.url;
            boolean bl2 = false;
            String string2 = string;
            if (string2 == null) {
                throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
            }
            String string3 = string2.substring(i, segmentEnd);
            Intrinsics.checkNotNullExpressionValue(string3, "(this as java.lang.Strin\u2026ing(startIndex, endIndex)");
            result.add(string3);
            i = segmentEnd;
        }
        return result;
    }

    @JvmName(name="encodedQuery")
    @Nullable
    public final String encodedQuery() {
        if (this.queryNamesAndValues == null) {
            return null;
        }
        int queryStart = StringsKt.indexOf$default((CharSequence)this.url, '?', 0, false, 6, null) + 1;
        int queryEnd = Util.delimiterOffset(this.url, '#', queryStart, this.url.length());
        String string = this.url;
        boolean bl = false;
        String string2 = string;
        if (string2 == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        String string3 = string2.substring(queryStart, queryEnd);
        Intrinsics.checkNotNullExpressionValue(string3, "(this as java.lang.Strin\u2026ing(startIndex, endIndex)");
        return string3;
    }

    @JvmName(name="query")
    @Nullable
    public final String query() {
        if (this.queryNamesAndValues == null) {
            return null;
        }
        StringBuilder result = new StringBuilder();
        Companion.toQueryString$okhttp(this.queryNamesAndValues, result);
        return result.toString();
    }

    @JvmName(name="querySize")
    public final int querySize() {
        return this.queryNamesAndValues != null ? this.queryNamesAndValues.size() / 2 : 0;
    }

    /*
     * WARNING - void declaration
     */
    @Nullable
    public final String queryParameter(@NotNull String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        if (this.queryNamesAndValues == null) {
            return null;
        }
        IntProgression intProgression = RangesKt.step(RangesKt.until(0, this.queryNamesAndValues.size()), 2);
        int n = intProgression.getFirst();
        int n2 = intProgression.getLast();
        int n3 = intProgression.getStep();
        int n4 = n;
        int n5 = n2;
        if (n3 >= 0 ? n4 <= n5 : n4 >= n5) {
            while (true) {
                void i;
                if (Intrinsics.areEqual(name, this.queryNamesAndValues.get((int)i))) {
                    return this.queryNamesAndValues.get((int)(i + true));
                }
                if (i == n2) break;
                n = i + n3;
            }
        }
        return null;
    }

    /*
     * WARNING - void declaration
     */
    @JvmName(name="queryParameterNames")
    @NotNull
    public final Set<String> queryParameterNames() {
        if (this.queryNamesAndValues == null) {
            return SetsKt.emptySet();
        }
        LinkedHashSet<String> result = new LinkedHashSet<String>();
        IntProgression intProgression = RangesKt.step(RangesKt.until(0, this.queryNamesAndValues.size()), 2);
        int n = intProgression.getFirst();
        int n2 = intProgression.getLast();
        int n3 = intProgression.getStep();
        int n4 = n;
        int n5 = n2;
        if (n3 >= 0 ? n4 <= n5 : n4 >= n5) {
            while (true) {
                void i;
                String string = this.queryNamesAndValues.get((int)i);
                Intrinsics.checkNotNull(string);
                result.add(string);
                if (i == n2) break;
                n = i + n3;
            }
        }
        Set<String> set = Collections.unmodifiableSet((Set)result);
        Intrinsics.checkNotNullExpressionValue(set, "Collections.unmodifiableSet(result)");
        return set;
    }

    /*
     * WARNING - void declaration
     */
    @NotNull
    public final List<String> queryParameterValues(@NotNull String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        if (this.queryNamesAndValues == null) {
            return CollectionsKt.emptyList();
        }
        int n = 0;
        List result = new ArrayList();
        IntProgression intProgression = RangesKt.step(RangesKt.until(0, this.queryNamesAndValues.size()), 2);
        n = intProgression.getFirst();
        int n2 = intProgression.getLast();
        int n3 = intProgression.getStep();
        int n4 = n;
        int n5 = n2;
        if (n3 >= 0 ? n4 <= n5 : n4 >= n5) {
            while (true) {
                void i;
                if (Intrinsics.areEqual(name, this.queryNamesAndValues.get((int)i))) {
                    result.add(this.queryNamesAndValues.get((int)(i + true)));
                }
                if (i == n2) break;
                n = i + n3;
            }
        }
        List<String> list = Collections.unmodifiableList(result);
        Intrinsics.checkNotNullExpressionValue(list, "Collections.unmodifiableList(result)");
        return list;
    }

    @NotNull
    public final String queryParameterName(int index) {
        if (this.queryNamesAndValues == null) {
            throw (Throwable)new IndexOutOfBoundsException();
        }
        String string = this.queryNamesAndValues.get(index * 2);
        Intrinsics.checkNotNull(string);
        return string;
    }

    @Nullable
    public final String queryParameterValue(int index) {
        if (this.queryNamesAndValues == null) {
            throw (Throwable)new IndexOutOfBoundsException();
        }
        return this.queryNamesAndValues.get(index * 2 + 1);
    }

    @JvmName(name="encodedFragment")
    @Nullable
    public final String encodedFragment() {
        if (this.fragment == null) {
            return null;
        }
        int fragmentStart = StringsKt.indexOf$default((CharSequence)this.url, '#', 0, false, 6, null) + 1;
        String string = this.url;
        boolean bl = false;
        String string2 = string;
        if (string2 == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        String string3 = string2.substring(fragmentStart);
        Intrinsics.checkNotNullExpressionValue(string3, "(this as java.lang.String).substring(startIndex)");
        return string3;
    }

    @NotNull
    public final String redact() {
        Builder builder = this.newBuilder("/...");
        Intrinsics.checkNotNull(builder);
        return builder.username(FRAGMENT_ENCODE_SET).password(FRAGMENT_ENCODE_SET).build().toString();
    }

    @Nullable
    public final HttpUrl resolve(@NotNull String link) {
        Intrinsics.checkNotNullParameter(link, "link");
        Builder builder = this.newBuilder(link);
        return builder != null ? builder.build() : null;
    }

    @NotNull
    public final Builder newBuilder() {
        Builder result = new Builder();
        result.setScheme$okhttp(this.scheme);
        result.setEncodedUsername$okhttp(this.encodedUsername());
        result.setEncodedPassword$okhttp(this.encodedPassword());
        result.setHost$okhttp(this.host);
        result.setPort$okhttp(this.port != Companion.defaultPort(this.scheme) ? this.port : -1);
        result.getEncodedPathSegments$okhttp().clear();
        result.getEncodedPathSegments$okhttp().addAll((Collection<String>)this.encodedPathSegments());
        result.encodedQuery(this.encodedQuery());
        result.setEncodedFragment$okhttp(this.encodedFragment());
        return result;
    }

    @Nullable
    public final Builder newBuilder(@NotNull String link) {
        Builder builder;
        Intrinsics.checkNotNullParameter(link, "link");
        try {
            builder = new Builder().parse$okhttp(this, link);
        } catch (IllegalArgumentException _) {
            builder = null;
        }
        return builder;
    }

    public boolean equals(@Nullable Object other) {
        return other instanceof HttpUrl && Intrinsics.areEqual(((HttpUrl)other).url, this.url);
    }

    public int hashCode() {
        return this.url.hashCode();
    }

    @NotNull
    public String toString() {
        return this.url;
    }

    @Nullable
    public final String topPrivateDomain() {
        return Util.canParseAsIpAddress(this.host) ? null : PublicSuffixDatabase.Companion.get().getEffectiveTldPlusOne(this.host);
    }

    @Deprecated(message="moved to toUrl()", replaceWith=@ReplaceWith(imports={}, expression="toUrl()"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_url")
    @NotNull
    public final URL -deprecated_url() {
        return this.url();
    }

    @Deprecated(message="moved to toUri()", replaceWith=@ReplaceWith(imports={}, expression="toUri()"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_uri")
    @NotNull
    public final URI -deprecated_uri() {
        return this.uri();
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="scheme"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_scheme")
    @NotNull
    public final String -deprecated_scheme() {
        return this.scheme;
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="encodedUsername"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_encodedUsername")
    @NotNull
    public final String -deprecated_encodedUsername() {
        return this.encodedUsername();
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="username"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_username")
    @NotNull
    public final String -deprecated_username() {
        return this.username;
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="encodedPassword"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_encodedPassword")
    @NotNull
    public final String -deprecated_encodedPassword() {
        return this.encodedPassword();
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="password"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_password")
    @NotNull
    public final String -deprecated_password() {
        return this.password;
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="host"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_host")
    @NotNull
    public final String -deprecated_host() {
        return this.host;
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="port"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_port")
    public final int -deprecated_port() {
        return this.port;
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="pathSize"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_pathSize")
    public final int -deprecated_pathSize() {
        return this.pathSize();
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="encodedPath"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_encodedPath")
    @NotNull
    public final String -deprecated_encodedPath() {
        return this.encodedPath();
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="encodedPathSegments"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_encodedPathSegments")
    @NotNull
    public final List<String> -deprecated_encodedPathSegments() {
        return this.encodedPathSegments();
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="pathSegments"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_pathSegments")
    @NotNull
    public final List<String> -deprecated_pathSegments() {
        return this.pathSegments;
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="encodedQuery"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_encodedQuery")
    @Nullable
    public final String -deprecated_encodedQuery() {
        return this.encodedQuery();
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="query"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_query")
    @Nullable
    public final String -deprecated_query() {
        return this.query();
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="querySize"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_querySize")
    public final int -deprecated_querySize() {
        return this.querySize();
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="queryParameterNames"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_queryParameterNames")
    @NotNull
    public final Set<String> -deprecated_queryParameterNames() {
        return this.queryParameterNames();
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="encodedFragment"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_encodedFragment")
    @Nullable
    public final String -deprecated_encodedFragment() {
        return this.encodedFragment();
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="fragment"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_fragment")
    @Nullable
    public final String -deprecated_fragment() {
        return this.fragment;
    }

    @JvmName(name="scheme")
    @NotNull
    public final String scheme() {
        return this.scheme;
    }

    @JvmName(name="username")
    @NotNull
    public final String username() {
        return this.username;
    }

    @JvmName(name="password")
    @NotNull
    public final String password() {
        return this.password;
    }

    @JvmName(name="host")
    @NotNull
    public final String host() {
        return this.host;
    }

    @JvmName(name="port")
    public final int port() {
        return this.port;
    }

    @JvmName(name="pathSegments")
    @NotNull
    public final List<String> pathSegments() {
        return this.pathSegments;
    }

    @JvmName(name="fragment")
    @Nullable
    public final String fragment() {
        return this.fragment;
    }

    public HttpUrl(@NotNull String scheme, @NotNull String username, @NotNull String password, @NotNull String host, int port, @NotNull List<String> pathSegments, @Nullable List<String> queryNamesAndValues, @Nullable String fragment, @NotNull String url) {
        Intrinsics.checkNotNullParameter(scheme, "scheme");
        Intrinsics.checkNotNullParameter(username, "username");
        Intrinsics.checkNotNullParameter(password, "password");
        Intrinsics.checkNotNullParameter(host, "host");
        Intrinsics.checkNotNullParameter(pathSegments, "pathSegments");
        Intrinsics.checkNotNullParameter(url, "url");
        this.scheme = scheme;
        this.username = username;
        this.password = password;
        this.host = host;
        this.port = port;
        this.pathSegments = pathSegments;
        this.queryNamesAndValues = queryNamesAndValues;
        this.fragment = fragment;
        this.url = url;
        this.isHttps = Intrinsics.areEqual(this.scheme, "https");
    }

    static {
        Companion = new Companion(null);
        HEX_DIGITS = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    }

    @JvmStatic
    public static final int defaultPort(@NotNull String scheme) {
        return Companion.defaultPort(scheme);
    }

    @JvmStatic
    @JvmName(name="get")
    @NotNull
    public static final HttpUrl get(@NotNull String $this$toHttpUrl) {
        return Companion.get($this$toHttpUrl);
    }

    @JvmStatic
    @JvmName(name="parse")
    @Nullable
    public static final HttpUrl parse(@NotNull String $this$toHttpUrlOrNull) {
        return Companion.parse($this$toHttpUrlOrNull);
    }

    @JvmStatic
    @JvmName(name="get")
    @Nullable
    public static final HttpUrl get(@NotNull URL $this$toHttpUrlOrNull) {
        return Companion.get($this$toHttpUrlOrNull);
    }

    @JvmStatic
    @JvmName(name="get")
    @Nullable
    public static final HttpUrl get(@NotNull URI $this$toHttpUrlOrNull) {
        return Companion.get($this$toHttpUrlOrNull);
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010!\n\u0002\b\r\n\u0002\u0010\b\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0002\n\u0002\b\u0017\u0018\u0000 V2\u00020\u0001:\u0001VB\u0005\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010#\u001a\u00020\u00002\u0006\u0010$\u001a\u00020\u0004J\u000e\u0010%\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\u0004J\u0018\u0010&\u001a\u00020\u00002\u0006\u0010'\u001a\u00020\u00042\b\u0010(\u001a\u0004\u0018\u00010\u0004J\u000e\u0010)\u001a\u00020\u00002\u0006\u0010*\u001a\u00020\u0004J\u000e\u0010+\u001a\u00020\u00002\u0006\u0010,\u001a\u00020\u0004J\u0018\u0010+\u001a\u00020\u00002\u0006\u0010,\u001a\u00020\u00042\u0006\u0010-\u001a\u00020.H\u0002J\u0018\u0010/\u001a\u00020\u00002\u0006\u00100\u001a\u00020\u00042\b\u00101\u001a\u0004\u0018\u00010\u0004J\u0006\u00102\u001a\u000203J\b\u00104\u001a\u00020\u001bH\u0002J\u0010\u0010\u0003\u001a\u00020\u00002\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004J\u000e\u0010\t\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0004J\u000e\u00105\u001a\u00020\u00002\u0006\u00105\u001a\u00020\u0004J\u0010\u00106\u001a\u00020\u00002\b\u00106\u001a\u0004\u0018\u00010\u0004J\u000e\u0010\u0014\u001a\u00020\u00002\u0006\u0010\u0014\u001a\u00020\u0004J\u0010\u00107\u001a\u00020\u00002\b\u00107\u001a\u0004\u0018\u00010\u0004J\u000e\u0010\u0017\u001a\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u0004J\u0010\u00108\u001a\u00020.2\u0006\u00109\u001a\u00020\u0004H\u0002J\u0010\u0010:\u001a\u00020.2\u0006\u00109\u001a\u00020\u0004H\u0002J\u001f\u0010;\u001a\u00020\u00002\b\u0010<\u001a\u0004\u0018\u0001032\u0006\u00109\u001a\u00020\u0004H\u0000\u00a2\u0006\u0002\b=J\u000e\u0010>\u001a\u00020\u00002\u0006\u0010>\u001a\u00020\u0004J\b\u0010?\u001a\u00020@H\u0002J\u000e\u0010\u001a\u001a\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u001bJ0\u0010A\u001a\u00020@2\u0006\u00109\u001a\u00020\u00042\u0006\u0010B\u001a\u00020\u001b2\u0006\u0010C\u001a\u00020\u001b2\u0006\u0010D\u001a\u00020.2\u0006\u0010-\u001a\u00020.H\u0002J\u0010\u0010E\u001a\u00020\u00002\b\u0010E\u001a\u0004\u0018\u00010\u0004J\r\u0010F\u001a\u00020\u0000H\u0000\u00a2\u0006\u0002\bGJ\u0010\u0010H\u001a\u00020@2\u0006\u0010I\u001a\u00020\u0004H\u0002J\u000e\u0010J\u001a\u00020\u00002\u0006\u0010'\u001a\u00020\u0004J\u000e\u0010K\u001a\u00020\u00002\u0006\u00100\u001a\u00020\u0004J\u000e\u0010L\u001a\u00020\u00002\u0006\u0010M\u001a\u00020\u001bJ \u0010N\u001a\u00020@2\u0006\u00109\u001a\u00020\u00042\u0006\u0010O\u001a\u00020\u001b2\u0006\u0010C\u001a\u00020\u001bH\u0002J\u000e\u0010 \u001a\u00020\u00002\u0006\u0010 \u001a\u00020\u0004J\u0016\u0010P\u001a\u00020\u00002\u0006\u0010M\u001a\u00020\u001b2\u0006\u0010$\u001a\u00020\u0004J\u0018\u0010Q\u001a\u00020\u00002\u0006\u0010'\u001a\u00020\u00042\b\u0010(\u001a\u0004\u0018\u00010\u0004J\u0016\u0010R\u001a\u00020\u00002\u0006\u0010M\u001a\u00020\u001b2\u0006\u0010*\u001a\u00020\u0004J\u0018\u0010S\u001a\u00020\u00002\u0006\u00100\u001a\u00020\u00042\b\u00101\u001a\u0004\u0018\u00010\u0004J\b\u0010T\u001a\u00020\u0004H\u0016J\u000e\u0010U\u001a\u00020\u00002\u0006\u0010U\u001a\u00020\u0004R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001a\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\rX\u0080\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR$\u0010\u0010\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010\rX\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u000f\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0014\u001a\u00020\u0004X\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0006\"\u0004\b\u0016\u0010\bR\u001c\u0010\u0017\u001a\u0004\u0018\u00010\u0004X\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0006\"\u0004\b\u0019\u0010\bR\u001a\u0010\u001a\u001a\u00020\u001bX\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u001c\u0010 \u001a\u0004\u0018\u00010\u0004X\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u0006\"\u0004\b\"\u0010\b\u00a8\u0006W"}, d2={"Lokhttp3/HttpUrl$Builder;", "", "()V", "encodedFragment", "", "getEncodedFragment$okhttp", "()Ljava/lang/String;", "setEncodedFragment$okhttp", "(Ljava/lang/String;)V", "encodedPassword", "getEncodedPassword$okhttp", "setEncodedPassword$okhttp", "encodedPathSegments", "", "getEncodedPathSegments$okhttp", "()Ljava/util/List;", "encodedQueryNamesAndValues", "getEncodedQueryNamesAndValues$okhttp", "setEncodedQueryNamesAndValues$okhttp", "(Ljava/util/List;)V", "encodedUsername", "getEncodedUsername$okhttp", "setEncodedUsername$okhttp", "host", "getHost$okhttp", "setHost$okhttp", "port", "", "getPort$okhttp", "()I", "setPort$okhttp", "(I)V", "scheme", "getScheme$okhttp", "setScheme$okhttp", "addEncodedPathSegment", "encodedPathSegment", "addEncodedPathSegments", "addEncodedQueryParameter", "encodedName", "encodedValue", "addPathSegment", "pathSegment", "addPathSegments", "pathSegments", "alreadyEncoded", "", "addQueryParameter", "name", "value", "build", "Lokhttp3/HttpUrl;", "effectivePort", "encodedPath", "encodedQuery", "fragment", "isDot", "input", "isDotDot", "parse", "base", "parse$okhttp", "password", "pop", "", "push", "pos", "limit", "addTrailingSlash", "query", "reencodeForUri", "reencodeForUri$okhttp", "removeAllCanonicalQueryParameters", "canonicalName", "removeAllEncodedQueryParameters", "removeAllQueryParameters", "removePathSegment", "index", "resolvePath", "startPos", "setEncodedPathSegment", "setEncodedQueryParameter", "setPathSegment", "setQueryParameter", "toString", "username", "Companion", "okhttp"})
    public static final class Builder {
        @Nullable
        private String scheme;
        @NotNull
        private String encodedUsername = "";
        @NotNull
        private String encodedPassword = "";
        @Nullable
        private String host;
        private int port = -1;
        @NotNull
        private final List<String> encodedPathSegments;
        @Nullable
        private List<String> encodedQueryNamesAndValues;
        @Nullable
        private String encodedFragment;
        @NotNull
        public static final String INVALID_HOST = "Invalid URL host";
        public static final Companion Companion = new Companion(null);

        @Nullable
        public final String getScheme$okhttp() {
            return this.scheme;
        }

        public final void setScheme$okhttp(@Nullable String string) {
            this.scheme = string;
        }

        @NotNull
        public final String getEncodedUsername$okhttp() {
            return this.encodedUsername;
        }

        public final void setEncodedUsername$okhttp(@NotNull String string) {
            Intrinsics.checkNotNullParameter(string, "<set-?>");
            this.encodedUsername = string;
        }

        @NotNull
        public final String getEncodedPassword$okhttp() {
            return this.encodedPassword;
        }

        public final void setEncodedPassword$okhttp(@NotNull String string) {
            Intrinsics.checkNotNullParameter(string, "<set-?>");
            this.encodedPassword = string;
        }

        @Nullable
        public final String getHost$okhttp() {
            return this.host;
        }

        public final void setHost$okhttp(@Nullable String string) {
            this.host = string;
        }

        public final int getPort$okhttp() {
            return this.port;
        }

        public final void setPort$okhttp(int n) {
            this.port = n;
        }

        @NotNull
        public final List<String> getEncodedPathSegments$okhttp() {
            return this.encodedPathSegments;
        }

        @Nullable
        public final List<String> getEncodedQueryNamesAndValues$okhttp() {
            return this.encodedQueryNamesAndValues;
        }

        public final void setEncodedQueryNamesAndValues$okhttp(@Nullable List<String> list) {
            this.encodedQueryNamesAndValues = list;
        }

        @Nullable
        public final String getEncodedFragment$okhttp() {
            return this.encodedFragment;
        }

        public final void setEncodedFragment$okhttp(@Nullable String string) {
            this.encodedFragment = string;
        }

        @NotNull
        public final Builder scheme(@NotNull String scheme) {
            Intrinsics.checkNotNullParameter(scheme, "scheme");
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            if (StringsKt.equals(scheme, "http", true)) {
                $this$apply.scheme = "http";
            } else if (StringsKt.equals(scheme, "https", true)) {
                $this$apply.scheme = "https";
            } else {
                throw (Throwable)new IllegalArgumentException("unexpected scheme: " + scheme);
            }
            return builder;
        }

        @NotNull
        public final Builder username(@NotNull String username) {
            Intrinsics.checkNotNullParameter(username, "username");
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            $this$apply.encodedUsername = okhttp3.HttpUrl$Companion.canonicalize$okhttp$default(Companion, username, 0, 0, " \"':;<=>@[]^`{}|/\\?#", false, false, false, false, null, 251, null);
            return builder;
        }

        @NotNull
        public final Builder encodedUsername(@NotNull String encodedUsername) {
            Intrinsics.checkNotNullParameter(encodedUsername, "encodedUsername");
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            $this$apply.encodedUsername = okhttp3.HttpUrl$Companion.canonicalize$okhttp$default(Companion, encodedUsername, 0, 0, " \"':;<=>@[]^`{}|/\\?#", true, false, false, false, null, 243, null);
            return builder;
        }

        @NotNull
        public final Builder password(@NotNull String password) {
            Intrinsics.checkNotNullParameter(password, "password");
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            $this$apply.encodedPassword = okhttp3.HttpUrl$Companion.canonicalize$okhttp$default(Companion, password, 0, 0, " \"':;<=>@[]^`{}|/\\?#", false, false, false, false, null, 251, null);
            return builder;
        }

        @NotNull
        public final Builder encodedPassword(@NotNull String encodedPassword) {
            Intrinsics.checkNotNullParameter(encodedPassword, "encodedPassword");
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            $this$apply.encodedPassword = okhttp3.HttpUrl$Companion.canonicalize$okhttp$default(Companion, encodedPassword, 0, 0, " \"':;<=>@[]^`{}|/\\?#", true, false, false, false, null, 243, null);
            return builder;
        }

        @NotNull
        public final Builder host(@NotNull String host) {
            String encoded;
            Intrinsics.checkNotNullParameter(host, "host");
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            String string = HostnamesKt.toCanonicalHost(okhttp3.HttpUrl$Companion.percentDecode$okhttp$default(Companion, host, 0, 0, false, 7, null));
            if (string == null) {
                throw (Throwable)new IllegalArgumentException("unexpected host: " + host);
            }
            $this$apply.host = encoded = string;
            return builder;
        }

        @NotNull
        public final Builder port(int port) {
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            int n = port;
            n = 1 <= n && 65535 >= n ? 1 : 0;
            boolean bl4 = false;
            boolean bl5 = false;
            if (n == 0) {
                boolean bl6 = false;
                String string = "unexpected port: " + port;
                throw (Throwable)new IllegalArgumentException(string.toString());
            }
            $this$apply.port = port;
            return builder;
        }

        private final int effectivePort() {
            int n;
            if (this.port != -1) {
                n = this.port;
            } else {
                String string = this.scheme;
                Intrinsics.checkNotNull(string);
                n = Companion.defaultPort(string);
            }
            return n;
        }

        @NotNull
        public final Builder addPathSegment(@NotNull String pathSegment) {
            Intrinsics.checkNotNullParameter(pathSegment, "pathSegment");
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            $this$apply.push(pathSegment, 0, pathSegment.length(), false, false);
            return builder;
        }

        @NotNull
        public final Builder addPathSegments(@NotNull String pathSegments) {
            Intrinsics.checkNotNullParameter(pathSegments, "pathSegments");
            return this.addPathSegments(pathSegments, false);
        }

        @NotNull
        public final Builder addEncodedPathSegment(@NotNull String encodedPathSegment) {
            Intrinsics.checkNotNullParameter(encodedPathSegment, "encodedPathSegment");
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            $this$apply.push(encodedPathSegment, 0, encodedPathSegment.length(), false, true);
            return builder;
        }

        @NotNull
        public final Builder addEncodedPathSegments(@NotNull String encodedPathSegments) {
            Intrinsics.checkNotNullParameter(encodedPathSegments, "encodedPathSegments");
            return this.addPathSegments(encodedPathSegments, true);
        }

        private final Builder addPathSegments(String pathSegments, boolean alreadyEncoded) {
            int segmentEnd;
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            int offset = 0;
            do {
                boolean addTrailingSlash = (segmentEnd = Util.delimiterOffset(pathSegments, "/\\", offset, pathSegments.length())) < pathSegments.length();
                $this$apply.push(pathSegments, offset, segmentEnd, addTrailingSlash, alreadyEncoded);
            } while ((offset = segmentEnd + 1) <= pathSegments.length());
            return builder;
        }

        @NotNull
        public final Builder setPathSegment(int index, @NotNull String pathSegment) {
            Intrinsics.checkNotNullParameter(pathSegment, "pathSegment");
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            String canonicalPathSegment = okhttp3.HttpUrl$Companion.canonicalize$okhttp$default(Companion, pathSegment, 0, 0, HttpUrl.PATH_SEGMENT_ENCODE_SET, false, false, false, false, null, 251, null);
            boolean bl4 = !$this$apply.isDot(canonicalPathSegment) && !$this$apply.isDotDot(canonicalPathSegment);
            boolean bl5 = false;
            boolean bl6 = false;
            if (!bl4) {
                boolean bl7 = false;
                String string = "unexpected path segment: " + pathSegment;
                throw (Throwable)new IllegalArgumentException(string.toString());
            }
            $this$apply.encodedPathSegments.set(index, canonicalPathSegment);
            return builder;
        }

        @NotNull
        public final Builder setEncodedPathSegment(int index, @NotNull String encodedPathSegment) {
            Intrinsics.checkNotNullParameter(encodedPathSegment, "encodedPathSegment");
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            String canonicalPathSegment = okhttp3.HttpUrl$Companion.canonicalize$okhttp$default(Companion, encodedPathSegment, 0, 0, HttpUrl.PATH_SEGMENT_ENCODE_SET, true, false, false, false, null, 243, null);
            $this$apply.encodedPathSegments.set(index, canonicalPathSegment);
            boolean bl4 = !$this$apply.isDot(canonicalPathSegment) && !$this$apply.isDotDot(canonicalPathSegment);
            boolean bl5 = false;
            boolean bl6 = false;
            if (!bl4) {
                boolean bl7 = false;
                String string = "unexpected path segment: " + encodedPathSegment;
                throw (Throwable)new IllegalArgumentException(string.toString());
            }
            return builder;
        }

        @NotNull
        public final Builder removePathSegment(int index) {
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            $this$apply.encodedPathSegments.remove(index);
            if ($this$apply.encodedPathSegments.isEmpty()) {
                $this$apply.encodedPathSegments.add(HttpUrl.FRAGMENT_ENCODE_SET);
            }
            return builder;
        }

        @NotNull
        public final Builder encodedPath(@NotNull String encodedPath) {
            Intrinsics.checkNotNullParameter(encodedPath, "encodedPath");
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            boolean bl4 = StringsKt.startsWith$default(encodedPath, "/", false, 2, null);
            boolean bl5 = false;
            boolean bl6 = false;
            if (!bl4) {
                boolean bl7 = false;
                String string = "unexpected encodedPath: " + encodedPath;
                throw (Throwable)new IllegalArgumentException(string.toString());
            }
            $this$apply.resolvePath(encodedPath, 0, encodedPath.length());
            return builder;
        }

        @NotNull
        public final Builder query(@Nullable String query) {
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            String string = query;
            $this$apply.encodedQueryNamesAndValues = string != null && (string = okhttp3.HttpUrl$Companion.canonicalize$okhttp$default(Companion, string, 0, 0, HttpUrl.QUERY_ENCODE_SET, false, false, true, false, null, 219, null)) != null ? Companion.toQueryNamesAndValues$okhttp(string) : null;
            return builder;
        }

        @NotNull
        public final Builder encodedQuery(@Nullable String encodedQuery) {
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            String string = encodedQuery;
            $this$apply.encodedQueryNamesAndValues = string != null && (string = okhttp3.HttpUrl$Companion.canonicalize$okhttp$default(Companion, string, 0, 0, HttpUrl.QUERY_ENCODE_SET, true, false, true, false, null, 211, null)) != null ? Companion.toQueryNamesAndValues$okhttp(string) : null;
            return builder;
        }

        @NotNull
        public final Builder addQueryParameter(@NotNull String name, @Nullable String value) {
            Intrinsics.checkNotNullParameter(name, "name");
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            if ($this$apply.encodedQueryNamesAndValues == null) {
                boolean bl4 = false;
                $this$apply.encodedQueryNamesAndValues = new ArrayList();
            }
            List<String> list = $this$apply.encodedQueryNamesAndValues;
            Intrinsics.checkNotNull(list);
            list.add(okhttp3.HttpUrl$Companion.canonicalize$okhttp$default(Companion, name, 0, 0, HttpUrl.QUERY_COMPONENT_ENCODE_SET, false, false, true, false, null, 219, null));
            List<String> list2 = $this$apply.encodedQueryNamesAndValues;
            Intrinsics.checkNotNull(list2);
            String string = value;
            list2.add(string != null ? okhttp3.HttpUrl$Companion.canonicalize$okhttp$default(Companion, string, 0, 0, HttpUrl.QUERY_COMPONENT_ENCODE_SET, false, false, true, false, null, 219, null) : null);
            return builder;
        }

        @NotNull
        public final Builder addEncodedQueryParameter(@NotNull String encodedName, @Nullable String encodedValue) {
            Intrinsics.checkNotNullParameter(encodedName, "encodedName");
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            if ($this$apply.encodedQueryNamesAndValues == null) {
                boolean bl4 = false;
                $this$apply.encodedQueryNamesAndValues = new ArrayList();
            }
            List<String> list = $this$apply.encodedQueryNamesAndValues;
            Intrinsics.checkNotNull(list);
            list.add(okhttp3.HttpUrl$Companion.canonicalize$okhttp$default(Companion, encodedName, 0, 0, HttpUrl.QUERY_COMPONENT_REENCODE_SET, true, false, true, false, null, 211, null));
            List<String> list2 = $this$apply.encodedQueryNamesAndValues;
            Intrinsics.checkNotNull(list2);
            String string = encodedValue;
            list2.add(string != null ? okhttp3.HttpUrl$Companion.canonicalize$okhttp$default(Companion, string, 0, 0, HttpUrl.QUERY_COMPONENT_REENCODE_SET, true, false, true, false, null, 211, null) : null);
            return builder;
        }

        @NotNull
        public final Builder setQueryParameter(@NotNull String name, @Nullable String value) {
            Intrinsics.checkNotNullParameter(name, "name");
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            $this$apply.removeAllQueryParameters(name);
            $this$apply.addQueryParameter(name, value);
            return builder;
        }

        @NotNull
        public final Builder setEncodedQueryParameter(@NotNull String encodedName, @Nullable String encodedValue) {
            Intrinsics.checkNotNullParameter(encodedName, "encodedName");
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            $this$apply.removeAllEncodedQueryParameters(encodedName);
            $this$apply.addEncodedQueryParameter(encodedName, encodedValue);
            return builder;
        }

        @NotNull
        public final Builder removeAllQueryParameters(@NotNull String name) {
            Intrinsics.checkNotNullParameter(name, "name");
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            if ($this$apply.encodedQueryNamesAndValues == null) {
                return $this$apply;
            }
            String nameToRemove = okhttp3.HttpUrl$Companion.canonicalize$okhttp$default(Companion, name, 0, 0, HttpUrl.QUERY_COMPONENT_ENCODE_SET, false, false, true, false, null, 219, null);
            $this$apply.removeAllCanonicalQueryParameters(nameToRemove);
            return builder;
        }

        @NotNull
        public final Builder removeAllEncodedQueryParameters(@NotNull String encodedName) {
            Intrinsics.checkNotNullParameter(encodedName, "encodedName");
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            if ($this$apply.encodedQueryNamesAndValues == null) {
                return $this$apply;
            }
            $this$apply.removeAllCanonicalQueryParameters(okhttp3.HttpUrl$Companion.canonicalize$okhttp$default(Companion, encodedName, 0, 0, HttpUrl.QUERY_COMPONENT_REENCODE_SET, true, false, true, false, null, 211, null));
            return builder;
        }

        /*
         * WARNING - void declaration
         */
        private final void removeAllCanonicalQueryParameters(String canonicalName) {
            List<String> list = this.encodedQueryNamesAndValues;
            Intrinsics.checkNotNull(list);
            IntProgression intProgression = RangesKt.step(RangesKt.downTo(list.size() - 2, 0), 2);
            int n = intProgression.getFirst();
            int n2 = intProgression.getLast();
            int n3 = intProgression.getStep();
            int n4 = n;
            int n5 = n2;
            if (n3 >= 0 ? n4 <= n5 : n4 >= n5) {
                while (true) {
                    void i;
                    List<String> list2 = this.encodedQueryNamesAndValues;
                    Intrinsics.checkNotNull(list2);
                    if (Intrinsics.areEqual(canonicalName, list2.get((int)i))) {
                        List<String> list3 = this.encodedQueryNamesAndValues;
                        Intrinsics.checkNotNull(list3);
                        list3.remove((int)(i + true));
                        List<String> list4 = this.encodedQueryNamesAndValues;
                        Intrinsics.checkNotNull(list4);
                        list4.remove((int)i);
                        List<String> list5 = this.encodedQueryNamesAndValues;
                        Intrinsics.checkNotNull(list5);
                        if (list5.isEmpty()) {
                            this.encodedQueryNamesAndValues = null;
                            return;
                        }
                    }
                    if (i == n2) break;
                    n = i + n3;
                }
            }
        }

        @NotNull
        public final Builder fragment(@Nullable String fragment) {
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            String string = fragment;
            $this$apply.encodedFragment = string != null ? okhttp3.HttpUrl$Companion.canonicalize$okhttp$default(Companion, string, 0, 0, HttpUrl.FRAGMENT_ENCODE_SET, false, false, false, true, null, 187, null) : null;
            return builder;
        }

        @NotNull
        public final Builder encodedFragment(@Nullable String encodedFragment) {
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            String string = encodedFragment;
            $this$apply.encodedFragment = string != null ? okhttp3.HttpUrl$Companion.canonicalize$okhttp$default(Companion, string, 0, 0, HttpUrl.FRAGMENT_ENCODE_SET, true, false, false, true, null, 179, null) : null;
            return builder;
        }

        /*
         * WARNING - void declaration
         */
        @NotNull
        public final Builder reencodeForUri$okhttp() {
            String string;
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            String string2 = $this$apply.host;
            if (string2 != null) {
                CharSequence charSequence = string2;
                Regex regex = new Regex("[\"<>^`{|}]");
                String string3 = HttpUrl.FRAGMENT_ENCODE_SET;
                boolean bl4 = false;
                string = regex.replace(charSequence, string3);
            } else {
                string = null;
            }
            $this$apply.host = string;
            int n = 0;
            int n2 = $this$apply.encodedPathSegments.size();
            while (n < n2) {
                void i;
                $this$apply.encodedPathSegments.set((int)i, okhttp3.HttpUrl$Companion.canonicalize$okhttp$default(Companion, $this$apply.encodedPathSegments.get((int)i), 0, 0, HttpUrl.PATH_SEGMENT_ENCODE_SET_URI, true, true, false, false, null, 227, null));
                ++i;
            }
            List<String> encodedQueryNamesAndValues = $this$apply.encodedQueryNamesAndValues;
            if (encodedQueryNamesAndValues != null) {
                n2 = 0;
                int n3 = encodedQueryNamesAndValues.size();
                while (n2 < n3) {
                    void i;
                    String string4 = encodedQueryNamesAndValues.get((int)i);
                    encodedQueryNamesAndValues.set((int)i, string4 != null ? okhttp3.HttpUrl$Companion.canonicalize$okhttp$default(Companion, string4, 0, 0, HttpUrl.QUERY_COMPONENT_ENCODE_SET_URI, true, true, true, false, null, 195, null) : null);
                    ++i;
                }
            }
            String string5 = $this$apply.encodedFragment;
            $this$apply.encodedFragment = string5 != null ? okhttp3.HttpUrl$Companion.canonicalize$okhttp$default(Companion, string5, 0, 0, HttpUrl.FRAGMENT_ENCODE_SET_URI, true, true, false, true, null, 163, null) : null;
            return builder;
        }

        @NotNull
        public final HttpUrl build() {
            Object object;
            Object object2;
            String it;
            Collection<String> collection;
            Iterable $this$mapTo$iv$iv;
            Iterable $this$map$iv;
            String string = this.scheme;
            if (string == null) {
                throw (Throwable)new IllegalStateException("scheme == null");
            }
            String string2 = this.host;
            if (string2 == null) {
                throw (Throwable)new IllegalStateException("host == null");
            }
            Iterable iterable = this.encodedPathSegments;
            int n = this.effectivePort();
            String string3 = string2;
            String string4 = okhttp3.HttpUrl$Companion.percentDecode$okhttp$default(Companion, this.encodedPassword, 0, 0, false, 7, null);
            String string5 = okhttp3.HttpUrl$Companion.percentDecode$okhttp$default(Companion, this.encodedUsername, 0, 0, false, 7, null);
            String string6 = string;
            boolean $i$f$map = false;
            void var3_8 = $this$map$iv;
            Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
            boolean $i$f$mapTo = false;
            for (Object item$iv$iv : $this$mapTo$iv$iv) {
                String string7 = (String)item$iv$iv;
                collection = destination$iv$iv;
                boolean bl = false;
                object2 = okhttp3.HttpUrl$Companion.percentDecode$okhttp$default(Companion, it, 0, 0, false, 7, null);
                collection.add((String)object2);
            }
            collection = (List)destination$iv$iv;
            String string8 = string6;
            String string9 = string5;
            String string10 = string4;
            String string11 = string3;
            int n2 = n;
            Collection<String> collection2 = collection;
            List<String> list = this.encodedQueryNamesAndValues;
            if (list != null) {
                $this$map$iv = list;
                collection = collection2;
                n = n2;
                string3 = string11;
                string4 = string10;
                string5 = string9;
                string6 = string8;
                $i$f$map = false;
                $this$mapTo$iv$iv = $this$map$iv;
                destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
                $i$f$mapTo = false;
                for (Object item$iv$iv : $this$mapTo$iv$iv) {
                    it = (String)item$iv$iv;
                    object2 = destination$iv$iv;
                    boolean bl = false;
                    String string12 = it;
                    String string13 = string12 != null ? okhttp3.HttpUrl$Companion.percentDecode$okhttp$default(Companion, string12, 0, 0, true, 3, null) : null;
                    object2.add(string13);
                }
                object2 = (List)destination$iv$iv;
                string8 = string6;
                string9 = string5;
                string10 = string4;
                string11 = string3;
                n2 = n;
                collection2 = collection;
                object = object2;
            } else {
                object = null;
            }
            String string14 = this.encodedFragment;
            String string15 = this.toString();
            String string16 = string14 != null ? okhttp3.HttpUrl$Companion.percentDecode$okhttp$default(Companion, string14, 0, 0, false, 7, null) : null;
            Object object3 = object;
            Collection<String> collection3 = collection2;
            int n3 = n2;
            String string17 = string11;
            String string18 = string10;
            String string19 = string9;
            String string20 = string8;
            return new HttpUrl(string20, string19, string18, string17, n3, (List<String>)collection3, (List<String>)object3, string16, string15);
        }

        @NotNull
        public String toString() {
            StringBuilder $this$buildString;
            StringBuilder stringBuilder;
            block16: {
                int effectivePort;
                block17: {
                    block15: {
                        boolean bl;
                        CharSequence charSequence;
                        block14: {
                            boolean bl2 = false;
                            boolean bl3 = false;
                            stringBuilder = new StringBuilder();
                            boolean bl4 = false;
                            boolean bl5 = false;
                            $this$buildString = stringBuilder;
                            boolean bl6 = false;
                            if (this.scheme != null) {
                                $this$buildString.append(this.scheme);
                                $this$buildString.append("://");
                            } else {
                                $this$buildString.append("//");
                            }
                            charSequence = this.encodedUsername;
                            bl = false;
                            if (charSequence.length() > 0) break block14;
                            charSequence = this.encodedPassword;
                            bl = false;
                            if (!(charSequence.length() > 0)) break block15;
                        }
                        $this$buildString.append(this.encodedUsername);
                        charSequence = this.encodedPassword;
                        bl = false;
                        if (charSequence.length() > 0) {
                            $this$buildString.append(':');
                            $this$buildString.append(this.encodedPassword);
                        }
                        $this$buildString.append('@');
                    }
                    if (this.host != null) {
                        String string = this.host;
                        Intrinsics.checkNotNull(string);
                        if (StringsKt.contains$default((CharSequence)string, ':', false, 2, null)) {
                            $this$buildString.append('[');
                            $this$buildString.append(this.host);
                            $this$buildString.append(']');
                        } else {
                            $this$buildString.append(this.host);
                        }
                    }
                    if (this.port == -1 && this.scheme == null) break block16;
                    effectivePort = this.effectivePort();
                    if (this.scheme == null) break block17;
                    String string = this.scheme;
                    Intrinsics.checkNotNull(string);
                    if (effectivePort == Companion.defaultPort(string)) break block16;
                }
                $this$buildString.append(':');
                $this$buildString.append(effectivePort);
            }
            Companion.toPathString$okhttp(this.encodedPathSegments, $this$buildString);
            if (this.encodedQueryNamesAndValues != null) {
                $this$buildString.append('?');
                List<String> list = this.encodedQueryNamesAndValues;
                Intrinsics.checkNotNull(list);
                Companion.toQueryString$okhttp(list, $this$buildString);
            }
            if (this.encodedFragment != null) {
                $this$buildString.append('#');
                $this$buildString.append(this.encodedFragment);
            }
            String string = stringBuilder.toString();
            Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
            return string;
        }

        /*
         * Enabled aggressive block sorting
         */
        @NotNull
        public final Builder parse$okhttp(@Nullable HttpUrl base, @NotNull String input) {
            Intrinsics.checkNotNullParameter(input, "input");
            int pos = Util.indexOfFirstNonAsciiWhitespace$default(input, 0, 0, 3, null);
            int limit = Util.indexOfLastNonAsciiWhitespace$default(input, pos, 0, 2, null);
            int schemeDelimiterOffset = Builder.Companion.schemeDelimiterOffset(input, pos, limit);
            if (schemeDelimiterOffset != -1) {
                int n = pos;
                int n2 = 1;
                if (StringsKt.startsWith(input, "https:", n, n2 != 0)) {
                    this.scheme = "https";
                    pos += "https:".length();
                } else {
                    n = pos;
                    n2 = 1;
                    if (!StringsKt.startsWith(input, "http:", n, n2 != 0)) {
                        StringBuilder stringBuilder = new StringBuilder().append("Expected URL scheme 'http' or 'https' but was '");
                        String string = input;
                        n2 = 0;
                        boolean bl = false;
                        String string2 = string.substring(n2, schemeDelimiterOffset);
                        Intrinsics.checkNotNullExpressionValue(string2, "(this as java.lang.Strin\u2026ing(startIndex, endIndex)");
                        throw (Throwable)new IllegalArgumentException(stringBuilder.append(string2).append("'").toString());
                    }
                    this.scheme = "http";
                    pos += "http:".length();
                }
            } else {
                if (base == null) {
                    throw (Throwable)new IllegalArgumentException("Expected URL scheme 'http' or 'https' but no colon was found");
                }
                this.scheme = base.scheme();
            }
            boolean hasUsername = false;
            boolean hasPassword = false;
            int slashCount = Builder.Companion.slashCount(input, pos, limit);
            if (slashCount < 2 && base != null && !(Intrinsics.areEqual(base.scheme(), this.scheme) ^ true)) {
                this.encodedUsername = base.encodedUsername();
                this.encodedPassword = base.encodedPassword();
                this.host = base.host();
                this.port = base.port();
                this.encodedPathSegments.clear();
                this.encodedPathSegments.addAll((Collection<String>)base.encodedPathSegments());
                if (pos == limit || input.charAt(pos) == '#') {
                    this.encodedQuery(base.encodedQuery());
                }
            } else {
                pos += slashCount;
                block4: while (true) {
                    int componentDelimiterOffset;
                    int c = (componentDelimiterOffset = Util.delimiterOffset(input, "@/\\?#", pos, limit)) != limit ? (int)input.charAt(componentDelimiterOffset) : -1;
                    switch (c) {
                        case 64: {
                            if (!hasPassword) {
                                int passwordColonOffset = Util.delimiterOffset(input, ':', pos, componentDelimiterOffset);
                                String canonicalUsername = okhttp3.HttpUrl$Companion.canonicalize$okhttp$default(Companion, input, pos, passwordColonOffset, " \"':;<=>@[]^`{}|/\\?#", true, false, false, false, null, 240, null);
                                String string = this.encodedUsername = hasUsername ? this.encodedUsername + "%40" + canonicalUsername : canonicalUsername;
                                if (passwordColonOffset != componentDelimiterOffset) {
                                    hasPassword = true;
                                    this.encodedPassword = okhttp3.HttpUrl$Companion.canonicalize$okhttp$default(Companion, input, passwordColonOffset + 1, componentDelimiterOffset, " \"':;<=>@[]^`{}|/\\?#", true, false, false, false, null, 240, null);
                                }
                                hasUsername = true;
                            } else {
                                this.encodedPassword = this.encodedPassword + "%40" + okhttp3.HttpUrl$Companion.canonicalize$okhttp$default(Companion, input, pos, componentDelimiterOffset, " \"':;<=>@[]^`{}|/\\?#", true, false, false, false, null, 240, null);
                            }
                            pos = componentDelimiterOffset + 1;
                            continue block4;
                        }
                        case -1: 
                        case 35: 
                        case 47: 
                        case 63: 
                        case 92: {
                            boolean bl;
                            boolean bl2;
                            int portColonOffset = Builder.Companion.portColonOffset(input, pos, componentDelimiterOffset);
                            if (portColonOffset + 1 < componentDelimiterOffset) {
                                this.host = HostnamesKt.toCanonicalHost(okhttp3.HttpUrl$Companion.percentDecode$okhttp$default(Companion, input, pos, portColonOffset, false, 4, null));
                                this.port = Builder.Companion.parsePort(input, portColonOffset + 1, componentDelimiterOffset);
                                boolean bl3 = this.port != -1;
                                bl2 = false;
                                bl = false;
                                if (!bl3) {
                                    boolean bl4 = false;
                                    StringBuilder stringBuilder = new StringBuilder().append("Invalid URL port: \"");
                                    String string = input;
                                    int n = portColonOffset + 1;
                                    int n3 = componentDelimiterOffset;
                                    boolean bl5 = false;
                                    String string3 = string.substring(n, n3);
                                    Intrinsics.checkNotNullExpressionValue(string3, "(this as java.lang.Strin\u2026ing(startIndex, endIndex)");
                                    String string4 = stringBuilder.append(string3).append('\"').toString();
                                    throw (Throwable)new IllegalArgumentException(string4.toString());
                                }
                            } else {
                                this.host = HostnamesKt.toCanonicalHost(okhttp3.HttpUrl$Companion.percentDecode$okhttp$default(Companion, input, pos, portColonOffset, false, 4, null));
                                String string = this.scheme;
                                Intrinsics.checkNotNull(string);
                                this.port = Companion.defaultPort(string);
                            }
                            boolean bl6 = this.host != null;
                            bl2 = false;
                            bl = false;
                            if (!bl6) {
                                boolean bl7 = false;
                                StringBuilder stringBuilder = new StringBuilder().append("Invalid URL host: \"");
                                String string = input;
                                int n = pos;
                                int n4 = portColonOffset;
                                boolean bl8 = false;
                                String string5 = string.substring(n, n4);
                                Intrinsics.checkNotNullExpressionValue(string5, "(this as java.lang.Strin\u2026ing(startIndex, endIndex)");
                                String string6 = stringBuilder.append(string5).append('\"').toString();
                                throw (Throwable)new IllegalArgumentException(string6.toString());
                            }
                            pos = componentDelimiterOffset;
                            break block4;
                        }
                        default: {
                            continue block4;
                        }
                    }
                    break;
                }
            }
            int pathDelimiterOffset = Util.delimiterOffset(input, "?#", pos, limit);
            this.resolvePath(input, pos, pathDelimiterOffset);
            pos = pathDelimiterOffset;
            if (pos < limit && input.charAt(pos) == '?') {
                int queryDelimiterOffset = Util.delimiterOffset(input, '#', pos, limit);
                this.encodedQueryNamesAndValues = Companion.toQueryNamesAndValues$okhttp(okhttp3.HttpUrl$Companion.canonicalize$okhttp$default(Companion, input, pos + 1, queryDelimiterOffset, HttpUrl.QUERY_ENCODE_SET, true, false, true, false, null, 208, null));
                pos = queryDelimiterOffset;
            }
            if (pos < limit && input.charAt(pos) == '#') {
                this.encodedFragment = okhttp3.HttpUrl$Companion.canonicalize$okhttp$default(Companion, input, pos + 1, limit, HttpUrl.FRAGMENT_ENCODE_SET, true, false, false, true, null, 176, null);
            }
            return this;
        }

        private final void resolvePath(String input, int startPos, int limit) {
            int pos = startPos;
            if (pos == limit) {
                return;
            }
            char c = input.charAt(pos);
            if (c == '/' || c == '\\') {
                this.encodedPathSegments.clear();
                this.encodedPathSegments.add(HttpUrl.FRAGMENT_ENCODE_SET);
                ++pos;
            } else {
                this.encodedPathSegments.set(this.encodedPathSegments.size() - 1, HttpUrl.FRAGMENT_ENCODE_SET);
            }
            int i = pos;
            while (i < limit) {
                int pathSegmentDelimiterOffset = Util.delimiterOffset(input, "/\\", i, limit);
                boolean segmentHasTrailingSlash = pathSegmentDelimiterOffset < limit;
                this.push(input, i, pathSegmentDelimiterOffset, segmentHasTrailingSlash, true);
                i = pathSegmentDelimiterOffset;
                if (!segmentHasTrailingSlash) continue;
                ++i;
            }
        }

        private final void push(String input, int pos, int limit, boolean addTrailingSlash, boolean alreadyEncoded) {
            String segment = okhttp3.HttpUrl$Companion.canonicalize$okhttp$default(Companion, input, pos, limit, HttpUrl.PATH_SEGMENT_ENCODE_SET, alreadyEncoded, false, false, false, null, 240, null);
            if (this.isDot(segment)) {
                return;
            }
            if (this.isDotDot(segment)) {
                this.pop();
                return;
            }
            CharSequence charSequence = this.encodedPathSegments.get(this.encodedPathSegments.size() - 1);
            boolean bl = false;
            if (charSequence.length() == 0) {
                this.encodedPathSegments.set(this.encodedPathSegments.size() - 1, segment);
            } else {
                this.encodedPathSegments.add(segment);
            }
            if (addTrailingSlash) {
                this.encodedPathSegments.add(HttpUrl.FRAGMENT_ENCODE_SET);
            }
        }

        private final boolean isDot(String input) {
            return Intrinsics.areEqual(input, ".") || StringsKt.equals(input, "%2e", true);
        }

        private final boolean isDotDot(String input) {
            return Intrinsics.areEqual(input, "..") || StringsKt.equals(input, "%2e.", true) || StringsKt.equals(input, ".%2e", true) || StringsKt.equals(input, "%2e%2e", true);
        }

        /*
         * Enabled aggressive block sorting
         */
        private final void pop() {
            String removed = this.encodedPathSegments.remove(this.encodedPathSegments.size() - 1);
            Object object = removed;
            boolean bl = false;
            if (object.length() == 0) {
                object = this.encodedPathSegments;
                bl = false;
                if (!object.isEmpty()) {
                    this.encodedPathSegments.set(this.encodedPathSegments.size() - 1, HttpUrl.FRAGMENT_ENCODE_SET);
                    return;
                }
            }
            this.encodedPathSegments.add(HttpUrl.FRAGMENT_ENCODE_SET);
        }

        public Builder() {
            boolean bl = false;
            this.encodedPathSegments = new ArrayList();
            this.encodedPathSegments.add(HttpUrl.FRAGMENT_ENCODE_SET);
        }

        @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J \u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0006H\u0002J \u0010\n\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0006H\u0002J \u0010\u000b\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0006H\u0002J\u001c\u0010\f\u001a\u00020\u0006*\u00020\u00042\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0006H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0080T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2={"Lokhttp3/HttpUrl$Builder$Companion;", "", "()V", "INVALID_HOST", "", "parsePort", "", "input", "pos", "limit", "portColonOffset", "schemeDelimiterOffset", "slashCount", "okhttp"})
        public static final class Companion {
            private final int schemeDelimiterOffset(String input, int pos, int limit) {
                if (limit - pos < 2) {
                    return -1;
                }
                char c0 = input.charAt(pos);
                if (!(Intrinsics.compare(c0, 97) >= 0 && Intrinsics.compare(c0, 122) <= 0 || Intrinsics.compare(c0, 65) >= 0 && Intrinsics.compare(c0, 90) <= 0)) {
                    return -1;
                }
                int n = pos + 1;
                int n2 = limit;
                while (n < n2) {
                    int i;
                    char c = input.charAt(i);
                    char c2 = c;
                    if (!('a' <= c2 && 'z' >= c2 || 'A' <= (c2 = c) && 'Z' >= c2 || '0' <= (c2 = c) && '9' >= c2 || c == '+' || c == '-' || c == '.')) {
                        return c == ':' ? i : -1;
                    }
                    ++i;
                }
                return -1;
            }

            /*
             * WARNING - void declaration
             */
            private final int slashCount(String $this$slashCount, int pos, int limit) {
                void i;
                char c;
                int slashCount = 0;
                int n = pos;
                int n2 = limit;
                while (n < n2 && ((c = $this$slashCount.charAt((int)i)) == '\\' || c == '/')) {
                    ++slashCount;
                    ++i;
                }
                return slashCount;
            }

            private final int portColonOffset(String input, int pos, int limit) {
                block4: for (int i = pos; i < limit; ++i) {
                    switch (input.charAt(i)) {
                        case '[': {
                            while (++i < limit && input.charAt(i) != ']') {
                            }
                            continue block4;
                        }
                        case ':': {
                            return i;
                        }
                    }
                }
                return limit;
            }

            private final int parsePort(String input, int pos, int limit) {
                int n;
                try {
                    int i;
                    String portString;
                    String string = portString = okhttp3.HttpUrl$Companion.canonicalize$okhttp$default(HttpUrl.Companion, input, pos, limit, HttpUrl.FRAGMENT_ENCODE_SET, false, false, false, false, null, 248, null);
                    boolean bl = false;
                    int n2 = i = Integer.parseInt(string);
                    n = 1 <= n2 && 65535 >= n2 ? i : -1;
                } catch (NumberFormatException _) {
                    n = -1;
                }
                return n;
            }

            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
                this();
            }
        }
    }

    /*
     * Illegal identifiers - consider using --renameillegalidents true
     */
    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000p\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0019\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0004H\u0007J\u0017\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0007\u00a2\u0006\u0002\b\u0018J\u0017\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0019\u001a\u00020\u001aH\u0007\u00a2\u0006\u0002\b\u0018J\u0015\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\u0004H\u0007\u00a2\u0006\u0002\b\u0018J\u0017\u0010\u001b\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0019\u001a\u00020\u0004H\u0007\u00a2\u0006\u0002\b\u001cJa\u0010\u001d\u001a\u00020\u0004*\u00020\u00042\b\b\u0002\u0010\u001e\u001a\u00020\u00122\b\b\u0002\u0010\u001f\u001a\u00020\u00122\u0006\u0010 \u001a\u00020\u00042\b\b\u0002\u0010!\u001a\u00020\"2\b\b\u0002\u0010#\u001a\u00020\"2\b\b\u0002\u0010$\u001a\u00020\"2\b\b\u0002\u0010%\u001a\u00020\"2\n\b\u0002\u0010&\u001a\u0004\u0018\u00010'H\u0000\u00a2\u0006\u0002\b(J\u001c\u0010)\u001a\u00020\"*\u00020\u00042\u0006\u0010\u001e\u001a\u00020\u00122\u0006\u0010\u001f\u001a\u00020\u0012H\u0002J/\u0010*\u001a\u00020\u0004*\u00020\u00042\b\b\u0002\u0010\u001e\u001a\u00020\u00122\b\b\u0002\u0010\u001f\u001a\u00020\u00122\b\b\u0002\u0010$\u001a\u00020\"H\u0000\u00a2\u0006\u0002\b+J\u0011\u0010,\u001a\u00020\u0015*\u00020\u0004H\u0007\u00a2\u0006\u0002\b\u0014J\u0013\u0010-\u001a\u0004\u0018\u00010\u0015*\u00020\u0017H\u0007\u00a2\u0006\u0002\b\u0014J\u0013\u0010-\u001a\u0004\u0018\u00010\u0015*\u00020\u001aH\u0007\u00a2\u0006\u0002\b\u0014J\u0013\u0010-\u001a\u0004\u0018\u00010\u0015*\u00020\u0004H\u0007\u00a2\u0006\u0002\b\u001bJ#\u0010.\u001a\u00020/*\b\u0012\u0004\u0012\u00020\u0004002\n\u00101\u001a\u000602j\u0002`3H\u0000\u00a2\u0006\u0002\b4J\u0019\u00105\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000406*\u00020\u0004H\u0000\u00a2\u0006\u0002\b7J%\u00108\u001a\u00020/*\n\u0012\u0006\u0012\u0004\u0018\u00010\u0004002\n\u00101\u001a\u000602j\u0002`3H\u0000\u00a2\u0006\u0002\b9JV\u0010:\u001a\u00020/*\u00020;2\u0006\u0010<\u001a\u00020\u00042\u0006\u0010\u001e\u001a\u00020\u00122\u0006\u0010\u001f\u001a\u00020\u00122\u0006\u0010 \u001a\u00020\u00042\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\"2\u0006\u0010$\u001a\u00020\"2\u0006\u0010%\u001a\u00020\"2\b\u0010&\u001a\u0004\u0018\u00010'H\u0002J,\u0010=\u001a\u00020/*\u00020;2\u0006\u0010>\u001a\u00020\u00042\u0006\u0010\u001e\u001a\u00020\u00122\u0006\u0010\u001f\u001a\u00020\u00122\u0006\u0010$\u001a\u00020\"H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0080T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0080T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0080T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0080T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0080T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0080T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0080T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0080T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0080T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0080T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0080T\u00a2\u0006\u0002\n\u0000\u00a8\u0006?"}, d2={"Lokhttp3/HttpUrl$Companion;", "", "()V", "FORM_ENCODE_SET", "", "FRAGMENT_ENCODE_SET", "FRAGMENT_ENCODE_SET_URI", "HEX_DIGITS", "", "PASSWORD_ENCODE_SET", "PATH_SEGMENT_ENCODE_SET", "PATH_SEGMENT_ENCODE_SET_URI", "QUERY_COMPONENT_ENCODE_SET", "QUERY_COMPONENT_ENCODE_SET_URI", "QUERY_COMPONENT_REENCODE_SET", "QUERY_ENCODE_SET", "USERNAME_ENCODE_SET", "defaultPort", "", "scheme", "get", "Lokhttp3/HttpUrl;", "uri", "Ljava/net/URI;", "-deprecated_get", "url", "Ljava/net/URL;", "parse", "-deprecated_parse", "canonicalize", "pos", "limit", "encodeSet", "alreadyEncoded", "", "strict", "plusIsSpace", "unicodeAllowed", "charset", "Ljava/nio/charset/Charset;", "canonicalize$okhttp", "isPercentEncoded", "percentDecode", "percentDecode$okhttp", "toHttpUrl", "toHttpUrlOrNull", "toPathString", "", "", "out", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "toPathString$okhttp", "toQueryNamesAndValues", "", "toQueryNamesAndValues$okhttp", "toQueryString", "toQueryString$okhttp", "writeCanonicalized", "Lokio/Buffer;", "input", "writePercentDecoded", "encoded", "okhttp"})
    public static final class Companion {
        @JvmStatic
        public final int defaultPort(@NotNull String scheme) {
            int n;
            Intrinsics.checkNotNullParameter(scheme, "scheme");
            switch (scheme) {
                case "http": {
                    n = 80;
                    break;
                }
                case "https": {
                    n = 443;
                    break;
                }
                default: {
                    n = -1;
                }
            }
            return n;
        }

        /*
         * WARNING - void declaration
         */
        public final void toPathString$okhttp(@NotNull List<String> $this$toPathString, @NotNull StringBuilder out) {
            Intrinsics.checkNotNullParameter($this$toPathString, "$this$toPathString");
            Intrinsics.checkNotNullParameter(out, "out");
            int n = 0;
            int n2 = $this$toPathString.size();
            while (n < n2) {
                void i;
                out.append('/');
                out.append($this$toPathString.get((int)i));
                ++i;
            }
        }

        /*
         * WARNING - void declaration
         */
        public final void toQueryString$okhttp(@NotNull List<String> $this$toQueryString, @NotNull StringBuilder out) {
            Intrinsics.checkNotNullParameter($this$toQueryString, "$this$toQueryString");
            Intrinsics.checkNotNullParameter(out, "out");
            IntProgression intProgression = RangesKt.step(RangesKt.until(0, $this$toQueryString.size()), 2);
            int n = intProgression.getFirst();
            int n2 = intProgression.getLast();
            int n3 = intProgression.getStep();
            int n4 = n;
            int n5 = n2;
            if (n3 >= 0 ? n4 <= n5 : n4 >= n5) {
                while (true) {
                    void i;
                    String name = $this$toQueryString.get((int)i);
                    String value = $this$toQueryString.get((int)(i + true));
                    if (i > 0) {
                        out.append('&');
                    }
                    out.append(name);
                    if (value != null) {
                        out.append('=');
                        out.append(value);
                    }
                    if (i == n2) break;
                    n = i + n3;
                }
            }
        }

        @NotNull
        public final List<String> toQueryNamesAndValues$okhttp(@NotNull String $this$toQueryNamesAndValues) {
            Intrinsics.checkNotNullParameter($this$toQueryNamesAndValues, "$this$toQueryNamesAndValues");
            boolean bl = false;
            List result = new ArrayList();
            int pos = 0;
            while (pos <= $this$toQueryNamesAndValues.length()) {
                int n;
                String string;
                int equalsOffset;
                int ampersandOffset = StringsKt.indexOf$default((CharSequence)$this$toQueryNamesAndValues, '&', pos, false, 4, null);
                if (ampersandOffset == -1) {
                    ampersandOffset = $this$toQueryNamesAndValues.length();
                }
                if ((equalsOffset = StringsKt.indexOf$default((CharSequence)$this$toQueryNamesAndValues, '=', pos, false, 4, null)) == -1 || equalsOffset > ampersandOffset) {
                    string = $this$toQueryNamesAndValues;
                    n = 0;
                    String string2 = string.substring(pos, ampersandOffset);
                    Intrinsics.checkNotNullExpressionValue(string2, "(this as java.lang.Strin\u2026ing(startIndex, endIndex)");
                    result.add(string2);
                    result.add(null);
                } else {
                    string = $this$toQueryNamesAndValues;
                    n = 0;
                    String string3 = string.substring(pos, equalsOffset);
                    Intrinsics.checkNotNullExpressionValue(string3, "(this as java.lang.Strin\u2026ing(startIndex, endIndex)");
                    result.add(string3);
                    string = $this$toQueryNamesAndValues;
                    n = equalsOffset + 1;
                    boolean bl2 = false;
                    String string4 = string.substring(n, ampersandOffset);
                    Intrinsics.checkNotNullExpressionValue(string4, "(this as java.lang.Strin\u2026ing(startIndex, endIndex)");
                    result.add(string4);
                }
                pos = ampersandOffset + 1;
            }
            return result;
        }

        @JvmStatic
        @JvmName(name="get")
        @NotNull
        public final HttpUrl get(@NotNull String $this$toHttpUrl) {
            Intrinsics.checkNotNullParameter($this$toHttpUrl, "$this$toHttpUrl");
            return new Builder().parse$okhttp(null, $this$toHttpUrl).build();
        }

        @JvmStatic
        @JvmName(name="parse")
        @Nullable
        public final HttpUrl parse(@NotNull String $this$toHttpUrlOrNull) {
            HttpUrl httpUrl;
            Intrinsics.checkNotNullParameter($this$toHttpUrlOrNull, "$this$toHttpUrlOrNull");
            try {
                httpUrl = this.get($this$toHttpUrlOrNull);
            } catch (IllegalArgumentException _) {
                httpUrl = null;
            }
            return httpUrl;
        }

        @JvmStatic
        @JvmName(name="get")
        @Nullable
        public final HttpUrl get(@NotNull URL $this$toHttpUrlOrNull) {
            Intrinsics.checkNotNullParameter($this$toHttpUrlOrNull, "$this$toHttpUrlOrNull");
            String string = $this$toHttpUrlOrNull.toString();
            Intrinsics.checkNotNullExpressionValue(string, "toString()");
            return this.parse(string);
        }

        @JvmStatic
        @JvmName(name="get")
        @Nullable
        public final HttpUrl get(@NotNull URI $this$toHttpUrlOrNull) {
            Intrinsics.checkNotNullParameter($this$toHttpUrlOrNull, "$this$toHttpUrlOrNull");
            String string = $this$toHttpUrlOrNull.toString();
            Intrinsics.checkNotNullExpressionValue(string, "toString()");
            return this.parse(string);
        }

        @Deprecated(message="moved to extension function", replaceWith=@ReplaceWith(imports={"okhttp3.HttpUrl.Companion.toHttpUrl"}, expression="url.toHttpUrl()"), level=DeprecationLevel.ERROR)
        @JvmName(name="-deprecated_get")
        @NotNull
        public final HttpUrl -deprecated_get(@NotNull String url) {
            Intrinsics.checkNotNullParameter(url, "url");
            return this.get(url);
        }

        @Deprecated(message="moved to extension function", replaceWith=@ReplaceWith(imports={"okhttp3.HttpUrl.Companion.toHttpUrlOrNull"}, expression="url.toHttpUrlOrNull()"), level=DeprecationLevel.ERROR)
        @JvmName(name="-deprecated_parse")
        @Nullable
        public final HttpUrl -deprecated_parse(@NotNull String url) {
            Intrinsics.checkNotNullParameter(url, "url");
            return this.parse(url);
        }

        @Deprecated(message="moved to extension function", replaceWith=@ReplaceWith(imports={"okhttp3.HttpUrl.Companion.toHttpUrlOrNull"}, expression="url.toHttpUrlOrNull()"), level=DeprecationLevel.ERROR)
        @JvmName(name="-deprecated_get")
        @Nullable
        public final HttpUrl -deprecated_get(@NotNull URL url) {
            Intrinsics.checkNotNullParameter(url, "url");
            return this.get(url);
        }

        @Deprecated(message="moved to extension function", replaceWith=@ReplaceWith(imports={"okhttp3.HttpUrl.Companion.toHttpUrlOrNull"}, expression="uri.toHttpUrlOrNull()"), level=DeprecationLevel.ERROR)
        @JvmName(name="-deprecated_get")
        @Nullable
        public final HttpUrl -deprecated_get(@NotNull URI uri) {
            Intrinsics.checkNotNullParameter(uri, "uri");
            return this.get(uri);
        }

        /*
         * WARNING - void declaration
         */
        @NotNull
        public final String percentDecode$okhttp(@NotNull String $this$percentDecode, int pos, int limit, boolean plusIsSpace) {
            Intrinsics.checkNotNullParameter($this$percentDecode, "$this$percentDecode");
            int n = pos;
            int n2 = limit;
            while (n < n2) {
                void i;
                char c = $this$percentDecode.charAt((int)i);
                if (c == '%' || c == '+' && plusIsSpace) {
                    Buffer out = new Buffer();
                    out.writeUtf8($this$percentDecode, pos, (int)i);
                    this.writePercentDecoded(out, $this$percentDecode, (int)i, limit, plusIsSpace);
                    return out.readUtf8();
                }
                ++i;
            }
            String string = $this$percentDecode;
            n2 = 0;
            String string2 = string.substring(pos, limit);
            Intrinsics.checkNotNullExpressionValue(string2, "(this as java.lang.Strin\u2026ing(startIndex, endIndex)");
            return string2;
        }

        public static /* synthetic */ String percentDecode$okhttp$default(Companion companion, String string, int n, int n2, boolean bl, int n3, Object object) {
            if ((n3 & 1) != 0) {
                n = 0;
            }
            if ((n3 & 2) != 0) {
                n2 = string.length();
            }
            if ((n3 & 4) != 0) {
                bl = false;
            }
            return companion.percentDecode$okhttp(string, n, n2, bl);
        }

        private final void writePercentDecoded(Buffer $this$writePercentDecoded, String encoded, int pos, int limit, boolean plusIsSpace) {
            int codePoint = 0;
            int i = pos;
            while (i < limit) {
                String string = encoded;
                boolean bl = false;
                String string2 = string;
                if (string2 == null) {
                    throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                }
                codePoint = string2.codePointAt(i);
                if (codePoint == 37 && i + 2 < limit) {
                    int d1 = Util.parseHexDigit(encoded.charAt(i + 1));
                    int d2 = Util.parseHexDigit(encoded.charAt(i + 2));
                    if (d1 != -1 && d2 != -1) {
                        $this$writePercentDecoded.writeByte((d1 << 4) + d2);
                        i += 2;
                        i += Character.charCount(codePoint);
                        continue;
                    }
                } else if (codePoint == 43 && plusIsSpace) {
                    $this$writePercentDecoded.writeByte(32);
                    ++i;
                    continue;
                }
                $this$writePercentDecoded.writeUtf8CodePoint(codePoint);
                i += Character.charCount(codePoint);
            }
        }

        private final boolean isPercentEncoded(String $this$isPercentEncoded, int pos, int limit) {
            return pos + 2 < limit && $this$isPercentEncoded.charAt(pos) == '%' && Util.parseHexDigit($this$isPercentEncoded.charAt(pos + 1)) != -1 && Util.parseHexDigit($this$isPercentEncoded.charAt(pos + 2)) != -1;
        }

        @NotNull
        public final String canonicalize$okhttp(@NotNull String $this$canonicalize, int pos, int limit, @NotNull String encodeSet, boolean alreadyEncoded, boolean strict, boolean plusIsSpace, boolean unicodeAllowed, @Nullable Charset charset) {
            boolean bl;
            String string;
            Intrinsics.checkNotNullParameter($this$canonicalize, "$this$canonicalize");
            Intrinsics.checkNotNullParameter(encodeSet, "encodeSet");
            int codePoint = 0;
            for (int i = pos; i < limit; i += Character.charCount(codePoint)) {
                string = $this$canonicalize;
                bl = false;
                codePoint = string.codePointAt(i);
                if (!(codePoint < 32 || codePoint == 127 || codePoint >= 128 && !unicodeAllowed || StringsKt.contains$default((CharSequence)encodeSet, (char)codePoint, false, 2, null) || codePoint == 37 && (!alreadyEncoded || strict && !this.isPercentEncoded($this$canonicalize, i, limit))) && (codePoint != 43 || !plusIsSpace)) continue;
                Buffer out = new Buffer();
                out.writeUtf8($this$canonicalize, pos, i);
                this.writeCanonicalized(out, $this$canonicalize, i, limit, encodeSet, alreadyEncoded, strict, plusIsSpace, unicodeAllowed, charset);
                return out.readUtf8();
            }
            string = $this$canonicalize;
            bl = false;
            String string2 = string.substring(pos, limit);
            Intrinsics.checkNotNullExpressionValue(string2, "(this as java.lang.Strin\u2026ing(startIndex, endIndex)");
            return string2;
        }

        public static /* synthetic */ String canonicalize$okhttp$default(Companion companion, String string, int n, int n2, String string2, boolean bl, boolean bl2, boolean bl3, boolean bl4, Charset charset, int n3, Object object) {
            if ((n3 & 1) != 0) {
                n = 0;
            }
            if ((n3 & 2) != 0) {
                n2 = string.length();
            }
            if ((n3 & 8) != 0) {
                bl = false;
            }
            if ((n3 & 0x10) != 0) {
                bl2 = false;
            }
            if ((n3 & 0x20) != 0) {
                bl3 = false;
            }
            if ((n3 & 0x40) != 0) {
                bl4 = false;
            }
            if ((n3 & 0x80) != 0) {
                charset = null;
            }
            return companion.canonicalize$okhttp(string, n, n2, string2, bl, bl2, bl3, bl4, charset);
        }

        private final void writeCanonicalized(Buffer $this$writeCanonicalized, String input, int pos, int limit, String encodeSet, boolean alreadyEncoded, boolean strict, boolean plusIsSpace, boolean unicodeAllowed, Charset charset) {
            Buffer encodedCharBuffer = null;
            int codePoint = 0;
            for (int i = pos; i < limit; i += Character.charCount(codePoint)) {
                String string = input;
                boolean bl = false;
                String string2 = string;
                if (string2 == null) {
                    throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                }
                codePoint = string2.codePointAt(i);
                if (alreadyEncoded && (codePoint == 9 || codePoint == 10 || codePoint == 12 || codePoint == 13)) continue;
                if (codePoint == 43 && plusIsSpace) {
                    $this$writeCanonicalized.writeUtf8(alreadyEncoded ? "+" : "%2B");
                    continue;
                }
                if (codePoint < 32 || codePoint == 127 || codePoint >= 128 && !unicodeAllowed || StringsKt.contains$default((CharSequence)encodeSet, (char)codePoint, false, 2, null) || codePoint == 37 && (!alreadyEncoded || strict && !this.isPercentEncoded(input, i, limit))) {
                    if (encodedCharBuffer == null) {
                        encodedCharBuffer = new Buffer();
                    }
                    if (charset == null || Intrinsics.areEqual(charset, StandardCharsets.UTF_8)) {
                        encodedCharBuffer.writeUtf8CodePoint(codePoint);
                    } else {
                        encodedCharBuffer.writeString(input, i, i + Character.charCount(codePoint), charset);
                    }
                    while (!encodedCharBuffer.exhausted()) {
                        int b = encodedCharBuffer.readByte() & 0xFF;
                        $this$writeCanonicalized.writeByte(37);
                        $this$writeCanonicalized.writeByte(HEX_DIGITS[b >> 4 & 0xF]);
                        $this$writeCanonicalized.writeByte(HEX_DIGITS[b & 0xF]);
                    }
                    continue;
                }
                $this$writeCanonicalized.writeUtf8CodePoint(codePoint);
            }
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

