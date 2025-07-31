/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okhttp3;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.ArraysKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntProgression;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B-\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007\u00a2\u0006\u0002\u0010\bJ\u0016\u0010\u000b\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\fH\u0007J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0014\u001a\u00020\u0003J\r\u0010\u0005\u001a\u00020\u0003H\u0007\u00a2\u0006\u0002\b\u0015J\b\u0010\u0016\u001a\u00020\u0003H\u0016J\r\u0010\u0004\u001a\u00020\u0003H\u0007\u00a2\u0006\u0002\b\u0017R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\tR\u0013\u0010\u0005\u001a\u00020\u00038\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\nR\u0013\u0010\u0004\u001a\u00020\u00038\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\n\u00a8\u0006\u0019"}, d2={"Lokhttp3/MediaType;", "", "mediaType", "", "type", "subtype", "parameterNamesAndValues", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)V", "[Ljava/lang/String;", "()Ljava/lang/String;", "charset", "Ljava/nio/charset/Charset;", "defaultValue", "equals", "", "other", "hashCode", "", "parameter", "name", "-deprecated_subtype", "toString", "-deprecated_type", "Companion", "okhttp"})
public final class MediaType {
    private final String mediaType;
    @NotNull
    private final String type;
    @NotNull
    private final String subtype;
    private final String[] parameterNamesAndValues;
    private static final String TOKEN = "([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)";
    private static final String QUOTED = "\"([^\"]*)\"";
    private static final Pattern TYPE_SUBTYPE;
    private static final Pattern PARAMETER;
    public static final Companion Companion;

    @JvmOverloads
    @Nullable
    public final Charset charset(@Nullable Charset defaultValue) {
        Charset charset;
        String string = this.parameter("charset");
        if (string == null) {
            return defaultValue;
        }
        String charset2 = string;
        try {
            charset = Charset.forName(charset2);
        } catch (IllegalArgumentException _) {
            charset = defaultValue;
        }
        return charset;
    }

    public static /* synthetic */ Charset charset$default(MediaType mediaType, Charset charset, int n, Object object) {
        if ((n & 1) != 0) {
            charset = null;
        }
        return mediaType.charset(charset);
    }

    @JvmOverloads
    @Nullable
    public final Charset charset() {
        return MediaType.charset$default(this, null, 1, null);
    }

    /*
     * WARNING - void declaration
     */
    @Nullable
    public final String parameter(@NotNull String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        IntProgression intProgression = RangesKt.step(ArraysKt.getIndices(this.parameterNamesAndValues), 2);
        int n = intProgression.getFirst();
        int n2 = intProgression.getLast();
        int n3 = intProgression.getStep();
        int n4 = n;
        int n5 = n2;
        if (n3 >= 0 ? n4 <= n5 : n4 >= n5) {
            while (true) {
                void i;
                if (StringsKt.equals(this.parameterNamesAndValues[i], name, true)) {
                    return this.parameterNamesAndValues[i + true];
                }
                if (i == n2) break;
                n = i + n3;
            }
        }
        return null;
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="type"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_type")
    @NotNull
    public final String -deprecated_type() {
        return this.type;
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="subtype"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_subtype")
    @NotNull
    public final String -deprecated_subtype() {
        return this.subtype;
    }

    @NotNull
    public String toString() {
        return this.mediaType;
    }

    public boolean equals(@Nullable Object other) {
        return other instanceof MediaType && Intrinsics.areEqual(((MediaType)other).mediaType, this.mediaType);
    }

    public int hashCode() {
        return this.mediaType.hashCode();
    }

    @JvmName(name="type")
    @NotNull
    public final String type() {
        return this.type;
    }

    @JvmName(name="subtype")
    @NotNull
    public final String subtype() {
        return this.subtype;
    }

    private MediaType(String mediaType, String type, String subtype, String[] parameterNamesAndValues) {
        this.mediaType = mediaType;
        this.type = type;
        this.subtype = subtype;
        this.parameterNamesAndValues = parameterNamesAndValues;
    }

    static {
        Companion = new Companion(null);
        TYPE_SUBTYPE = Pattern.compile("([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)/([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)");
        PARAMETER = Pattern.compile(";\\s*(?:([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)=(?:([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)|\"([^\"]*)\"))?");
    }

    public /* synthetic */ MediaType(String mediaType, String type, String subtype, String[] parameterNamesAndValues, DefaultConstructorMarker $constructor_marker) {
        this(mediaType, type, subtype, parameterNamesAndValues);
    }

    @JvmStatic
    @JvmName(name="get")
    @NotNull
    public static final MediaType get(@NotNull String $this$toMediaType) {
        return Companion.get($this$toMediaType);
    }

    @JvmStatic
    @JvmName(name="parse")
    @Nullable
    public static final MediaType parse(@NotNull String $this$toMediaTypeOrNull) {
        return Companion.parse($this$toMediaTypeOrNull);
    }

    /*
     * Illegal identifiers - consider using --renameillegalidents true
     */
    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0015\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0007H\u0007\u00a2\u0006\u0002\b\rJ\u0017\u0010\u000e\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\u0007H\u0007\u00a2\u0006\u0002\b\u000fJ\u0011\u0010\u0010\u001a\u00020\u000b*\u00020\u0007H\u0007\u00a2\u0006\u0002\b\nJ\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u000b*\u00020\u0007H\u0007\u00a2\u0006\u0002\b\u000eR\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082T\u00a2\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2={"Lokhttp3/MediaType$Companion;", "", "()V", "PARAMETER", "Ljava/util/regex/Pattern;", "kotlin.jvm.PlatformType", "QUOTED", "", "TOKEN", "TYPE_SUBTYPE", "get", "Lokhttp3/MediaType;", "mediaType", "-deprecated_get", "parse", "-deprecated_parse", "toMediaType", "toMediaTypeOrNull", "okhttp"})
    public static final class Companion {
        @JvmStatic
        @JvmName(name="get")
        @NotNull
        public final MediaType get(@NotNull String $this$toMediaType) {
            Intrinsics.checkNotNullParameter($this$toMediaType, "$this$toMediaType");
            Matcher typeSubtype = TYPE_SUBTYPE.matcher($this$toMediaType);
            boolean bl = typeSubtype.lookingAt();
            boolean bl2 = false;
            boolean bl3 = false;
            if (!bl) {
                boolean $i$a$-require-MediaType$Companion$toMediaType$422 = false;
                String string = "No subtype found for: \"" + $this$toMediaType + '\"';
                throw (Throwable)new IllegalArgumentException(string.toString());
            }
            String string = typeSubtype.group(1);
            Intrinsics.checkNotNullExpressionValue(string, "typeSubtype.group(1)");
            String string2 = string;
            Locale locale = Locale.US;
            Intrinsics.checkNotNullExpressionValue(locale, "Locale.US");
            Object object = locale;
            boolean $i$a$-require-MediaType$Companion$toMediaType$422 = false;
            String string3 = string2;
            if (string3 == null) {
                throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
            }
            String string4 = string3.toLowerCase((Locale)object);
            Intrinsics.checkNotNullExpressionValue(string4, "(this as java.lang.String).toLowerCase(locale)");
            String type = string4;
            String string5 = typeSubtype.group(2);
            Intrinsics.checkNotNullExpressionValue(string5, "typeSubtype.group(2)");
            object = string5;
            Locale locale2 = Locale.US;
            Intrinsics.checkNotNullExpressionValue(locale2, "Locale.US");
            Locale $i$a$-require-MediaType$Companion$toMediaType$422 = locale2;
            boolean bl4 = false;
            Object object2 = object;
            if (object2 == null) {
                throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
            }
            String string6 = ((String)object2).toLowerCase($i$a$-require-MediaType$Companion$toMediaType$422);
            Intrinsics.checkNotNullExpressionValue(string6, "(this as java.lang.String).toLowerCase(locale)");
            String subtype = string6;
            boolean $i$a$-require-MediaType$Companion$toMediaType$422 = false;
            List parameterNamesAndValues = new ArrayList();
            Matcher parameter = PARAMETER.matcher($this$toMediaType);
            int s2 = typeSubtype.end();
            while (s2 < $this$toMediaType.length()) {
                int n;
                Object object3;
                String string7;
                boolean bl5;
                int n2;
                parameter.region(s2, $this$toMediaType.length());
                boolean bl6 = parameter.lookingAt();
                boolean bl7 = false;
                boolean bl8 = false;
                if (!bl6) {
                    boolean bl9 = false;
                    StringBuilder stringBuilder = new StringBuilder().append("Parameter is not formatted correctly: \"");
                    String string8 = $this$toMediaType;
                    n2 = s2;
                    bl5 = false;
                    String string9 = string8.substring(n2);
                    Intrinsics.checkNotNullExpressionValue(string9, "(this as java.lang.String).substring(startIndex)");
                    String string10 = stringBuilder.append(string9).append("\" for: \"").append($this$toMediaType).append('\"').toString();
                    throw (Throwable)new IllegalArgumentException(string10.toString());
                }
                String name = parameter.group(1);
                if (name == null) {
                    s2 = parameter.end();
                    continue;
                }
                String token = parameter.group(2);
                if (token == null) {
                    string7 = parameter.group(3);
                } else if (StringsKt.startsWith$default(token, "'", false, 2, null) && StringsKt.endsWith$default(token, "'", false, 2, null) && token.length() > 2) {
                    object3 = token;
                    n = 1;
                    n2 = token.length() - 1;
                    bl5 = false;
                    String string11 = ((String)object3).substring(n, n2);
                    string7 = string11;
                    Intrinsics.checkNotNullExpressionValue(string11, "(this as java.lang.Strin\u2026ing(startIndex, endIndex)");
                } else {
                    string7 = token;
                }
                String value = string7;
                object3 = parameterNamesAndValues;
                n = 0;
                object3.add(name);
                object3 = parameterNamesAndValues;
                n = 0;
                object3.add(value);
                s2 = parameter.end();
            }
            Collection $this$toTypedArray$iv = parameterNamesAndValues;
            boolean $i$f$toTypedArray = false;
            Collection thisCollection$iv = $this$toTypedArray$iv;
            String[] stringArray = thisCollection$iv.toArray(new String[0]);
            if (stringArray == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
            }
            return new MediaType($this$toMediaType, type, subtype, stringArray, null);
        }

        @JvmStatic
        @JvmName(name="parse")
        @Nullable
        public final MediaType parse(@NotNull String $this$toMediaTypeOrNull) {
            MediaType mediaType;
            Intrinsics.checkNotNullParameter($this$toMediaTypeOrNull, "$this$toMediaTypeOrNull");
            try {
                mediaType = this.get($this$toMediaTypeOrNull);
            } catch (IllegalArgumentException _) {
                mediaType = null;
            }
            return mediaType;
        }

        @Deprecated(message="moved to extension function", replaceWith=@ReplaceWith(imports={"okhttp3.MediaType.Companion.toMediaType"}, expression="mediaType.toMediaType()"), level=DeprecationLevel.ERROR)
        @JvmName(name="-deprecated_get")
        @NotNull
        public final MediaType -deprecated_get(@NotNull String mediaType) {
            Intrinsics.checkNotNullParameter(mediaType, "mediaType");
            return this.get(mediaType);
        }

        @Deprecated(message="moved to extension function", replaceWith=@ReplaceWith(imports={"okhttp3.MediaType.Companion.toMediaTypeOrNull"}, expression="mediaType.toMediaTypeOrNull()"), level=DeprecationLevel.ERROR)
        @JvmName(name="-deprecated_parse")
        @Nullable
        public final MediaType -deprecated_parse(@NotNull String mediaType) {
            Intrinsics.checkNotNullParameter(mediaType, "mediaType");
            return this.parse(mediaType);
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

