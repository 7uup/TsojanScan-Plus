/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.text;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CodingErrorAction;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import kotlin.Deprecated;
import kotlin.DeprecatedSinceKotlin;
import kotlin.ExperimentalStdlibApi;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.WasExperimental;
import kotlin.collections.AbstractList;
import kotlin.collections.ArraysKt;
import kotlin.collections.IntIterator;
import kotlin.internal.InlineOnly;
import kotlin.internal.LowPriorityInOverloadResolution;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.sequences.SequencesKt;
import kotlin.text.CharsKt;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import kotlin.text.StringsKt__StringNumberConversionsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=5, xi=1, d1={"\u0000~\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0019\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\r\n\u0002\b\t\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\t\n\u0002\u0010\f\n\u0002\b\u0011\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\u001a\u0011\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\tH\u0087\b\u001a\u0011\u0010\u0007\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u000bH\u0087\b\u001a\u0011\u0010\u0007\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\rH\u0087\b\u001a\u0019\u0010\u0007\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0087\b\u001a!\u0010\u0007\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011H\u0087\b\u001a)\u0010\u0007\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\u000fH\u0087\b\u001a\u0011\u0010\u0007\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u0014H\u0087\b\u001a!\u0010\u0007\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011H\u0087\b\u001a!\u0010\u0007\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011H\u0087\b\u001a\n\u0010\u0017\u001a\u00020\u0002*\u00020\u0002\u001a\u0014\u0010\u0017\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u0019H\u0007\u001a\u0015\u0010\u001a\u001a\u00020\u0011*\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u0011H\u0087\b\u001a\u0015\u0010\u001c\u001a\u00020\u0011*\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u0011H\u0087\b\u001a\u001d\u0010\u001d\u001a\u00020\u0011*\u00020\u00022\u0006\u0010\u001e\u001a\u00020\u00112\u0006\u0010\u001f\u001a\u00020\u0011H\u0087\b\u001a\u001c\u0010 \u001a\u00020\u0011*\u00020\u00022\u0006\u0010!\u001a\u00020\u00022\b\b\u0002\u0010\"\u001a\u00020#\u001a\f\u0010$\u001a\u00020\u0002*\u00020\u0014H\u0007\u001a \u0010$\u001a\u00020\u0002*\u00020\u00142\b\b\u0002\u0010%\u001a\u00020\u00112\b\b\u0002\u0010\u001f\u001a\u00020\u0011H\u0007\u001a\u0015\u0010&\u001a\u00020#*\u00020\u00022\u0006\u0010\n\u001a\u00020\tH\u0087\b\u001a\u0015\u0010&\u001a\u00020#*\u00020\u00022\u0006\u0010'\u001a\u00020(H\u0087\b\u001a\n\u0010)\u001a\u00020\u0002*\u00020\u0002\u001a\u0014\u0010)\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u0019H\u0007\u001a\f\u0010*\u001a\u00020\u0002*\u00020\rH\u0007\u001a*\u0010*\u001a\u00020\u0002*\u00020\r2\b\b\u0002\u0010%\u001a\u00020\u00112\b\b\u0002\u0010\u001f\u001a\u00020\u00112\b\b\u0002\u0010+\u001a\u00020#H\u0007\u001a\f\u0010,\u001a\u00020\r*\u00020\u0002H\u0007\u001a*\u0010,\u001a\u00020\r*\u00020\u00022\b\b\u0002\u0010%\u001a\u00020\u00112\b\b\u0002\u0010\u001f\u001a\u00020\u00112\b\b\u0002\u0010+\u001a\u00020#H\u0007\u001a\u001c\u0010-\u001a\u00020#*\u00020\u00022\u0006\u0010.\u001a\u00020\u00022\b\b\u0002\u0010\"\u001a\u00020#\u001a \u0010/\u001a\u00020#*\u0004\u0018\u00010\u00022\b\u0010!\u001a\u0004\u0018\u00010\u00022\b\b\u0002\u0010\"\u001a\u00020#\u001a2\u00100\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u00192\u0016\u00101\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010302\"\u0004\u0018\u000103H\u0087\b\u00a2\u0006\u0002\u00104\u001a6\u00100\u001a\u00020\u0002*\u00020\u00022\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0016\u00101\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010302\"\u0004\u0018\u000103H\u0087\b\u00a2\u0006\u0004\b5\u00104\u001a*\u00100\u001a\u00020\u0002*\u00020\u00022\u0016\u00101\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010302\"\u0004\u0018\u000103H\u0087\b\u00a2\u0006\u0002\u00106\u001a:\u00100\u001a\u00020\u0002*\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u00100\u001a\u00020\u00022\u0016\u00101\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010302\"\u0004\u0018\u000103H\u0087\b\u00a2\u0006\u0002\u00107\u001a>\u00100\u001a\u00020\u0002*\u00020\u00042\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u00100\u001a\u00020\u00022\u0016\u00101\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010302\"\u0004\u0018\u000103H\u0087\b\u00a2\u0006\u0004\b5\u00107\u001a2\u00100\u001a\u00020\u0002*\u00020\u00042\u0006\u00100\u001a\u00020\u00022\u0016\u00101\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010302\"\u0004\u0018\u000103H\u0087\b\u00a2\u0006\u0002\u00108\u001a\r\u00109\u001a\u00020\u0002*\u00020\u0002H\u0087\b\u001a\n\u0010:\u001a\u00020#*\u00020(\u001a\u001d\u0010;\u001a\u00020\u0011*\u00020\u00022\u0006\u0010<\u001a\u00020=2\u0006\u0010>\u001a\u00020\u0011H\u0081\b\u001a\u001d\u0010;\u001a\u00020\u0011*\u00020\u00022\u0006\u0010?\u001a\u00020\u00022\u0006\u0010>\u001a\u00020\u0011H\u0081\b\u001a\u001d\u0010@\u001a\u00020\u0011*\u00020\u00022\u0006\u0010<\u001a\u00020=2\u0006\u0010>\u001a\u00020\u0011H\u0081\b\u001a\u001d\u0010@\u001a\u00020\u0011*\u00020\u00022\u0006\u0010?\u001a\u00020\u00022\u0006\u0010>\u001a\u00020\u0011H\u0081\b\u001a\u001d\u0010A\u001a\u00020\u0011*\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u00112\u0006\u0010B\u001a\u00020\u0011H\u0087\b\u001a4\u0010C\u001a\u00020#*\u00020(2\u0006\u0010D\u001a\u00020\u00112\u0006\u0010!\u001a\u00020(2\u0006\u0010E\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\b\b\u0002\u0010\"\u001a\u00020#\u001a4\u0010C\u001a\u00020#*\u00020\u00022\u0006\u0010D\u001a\u00020\u00112\u0006\u0010!\u001a\u00020\u00022\u0006\u0010E\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\b\b\u0002\u0010\"\u001a\u00020#\u001a\u0012\u0010F\u001a\u00020\u0002*\u00020(2\u0006\u0010G\u001a\u00020\u0011\u001a$\u0010H\u001a\u00020\u0002*\u00020\u00022\u0006\u0010I\u001a\u00020=2\u0006\u0010J\u001a\u00020=2\b\b\u0002\u0010\"\u001a\u00020#\u001a$\u0010H\u001a\u00020\u0002*\u00020\u00022\u0006\u0010K\u001a\u00020\u00022\u0006\u0010L\u001a\u00020\u00022\b\b\u0002\u0010\"\u001a\u00020#\u001a$\u0010M\u001a\u00020\u0002*\u00020\u00022\u0006\u0010I\u001a\u00020=2\u0006\u0010J\u001a\u00020=2\b\b\u0002\u0010\"\u001a\u00020#\u001a$\u0010M\u001a\u00020\u0002*\u00020\u00022\u0006\u0010K\u001a\u00020\u00022\u0006\u0010L\u001a\u00020\u00022\b\b\u0002\u0010\"\u001a\u00020#\u001a\"\u0010N\u001a\b\u0012\u0004\u0012\u00020\u00020O*\u00020(2\u0006\u0010P\u001a\u00020Q2\b\b\u0002\u0010R\u001a\u00020\u0011\u001a\u001c\u0010S\u001a\u00020#*\u00020\u00022\u0006\u0010T\u001a\u00020\u00022\b\b\u0002\u0010\"\u001a\u00020#\u001a$\u0010S\u001a\u00020#*\u00020\u00022\u0006\u0010T\u001a\u00020\u00022\u0006\u0010%\u001a\u00020\u00112\b\b\u0002\u0010\"\u001a\u00020#\u001a\u0015\u0010U\u001a\u00020\u0002*\u00020\u00022\u0006\u0010%\u001a\u00020\u0011H\u0087\b\u001a\u001d\u0010U\u001a\u00020\u0002*\u00020\u00022\u0006\u0010%\u001a\u00020\u00112\u0006\u0010\u001f\u001a\u00020\u0011H\u0087\b\u001a\u0017\u0010V\u001a\u00020\r*\u00020\u00022\b\b\u0002\u0010\u000e\u001a\u00020\u000fH\u0087\b\u001a\r\u0010W\u001a\u00020\u0014*\u00020\u0002H\u0087\b\u001a3\u0010W\u001a\u00020\u0014*\u00020\u00022\u0006\u0010X\u001a\u00020\u00142\b\b\u0002\u0010Y\u001a\u00020\u00112\b\b\u0002\u0010%\u001a\u00020\u00112\b\b\u0002\u0010\u001f\u001a\u00020\u0011H\u0087\b\u001a \u0010W\u001a\u00020\u0014*\u00020\u00022\b\b\u0002\u0010%\u001a\u00020\u00112\b\b\u0002\u0010\u001f\u001a\u00020\u0011H\u0007\u001a\r\u0010Z\u001a\u00020\u0002*\u00020\u0002H\u0087\b\u001a\u0015\u0010Z\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u0019H\u0087\b\u001a\u0017\u0010[\u001a\u00020Q*\u00020\u00022\b\b\u0002\u0010\\\u001a\u00020\u0011H\u0087\b\u001a\r\u0010]\u001a\u00020\u0002*\u00020\u0002H\u0087\b\u001a\u0015\u0010]\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u0019H\u0087\b\"%\u0010\u0000\u001a\u0012\u0012\u0004\u0012\u00020\u00020\u0001j\b\u0012\u0004\u0012\u00020\u0002`\u0003*\u00020\u00048F\u00a2\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006^"}, d2={"CASE_INSENSITIVE_ORDER", "Ljava/util/Comparator;", "", "Lkotlin/Comparator;", "Lkotlin/String$Companion;", "getCASE_INSENSITIVE_ORDER", "(Lkotlin/jvm/internal/StringCompanionObject;)Ljava/util/Comparator;", "String", "stringBuffer", "Ljava/lang/StringBuffer;", "stringBuilder", "Ljava/lang/StringBuilder;", "bytes", "", "charset", "Ljava/nio/charset/Charset;", "offset", "", "length", "chars", "", "codePoints", "", "capitalize", "locale", "Ljava/util/Locale;", "codePointAt", "index", "codePointBefore", "codePointCount", "beginIndex", "endIndex", "compareTo", "other", "ignoreCase", "", "concatToString", "startIndex", "contentEquals", "charSequence", "", "decapitalize", "decodeToString", "throwOnInvalidSequence", "encodeToByteArray", "endsWith", "suffix", "equals", "format", "args", "", "", "(Ljava/lang/String;Ljava/util/Locale;[Ljava/lang/Object;)Ljava/lang/String;", "formatNullable", "(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;", "(Lkotlin/jvm/internal/StringCompanionObject;Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;", "(Lkotlin/jvm/internal/StringCompanionObject;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;", "intern", "isBlank", "nativeIndexOf", "ch", "", "fromIndex", "str", "nativeLastIndexOf", "offsetByCodePoints", "codePointOffset", "regionMatches", "thisOffset", "otherOffset", "repeat", "n", "replace", "oldChar", "newChar", "oldValue", "newValue", "replaceFirst", "split", "", "regex", "Ljava/util/regex/Pattern;", "limit", "startsWith", "prefix", "substring", "toByteArray", "toCharArray", "destination", "destinationOffset", "toLowerCase", "toPattern", "flags", "toUpperCase", "kotlin-stdlib"}, xs="kotlin/text/StringsKt")
class StringsKt__StringsJVMKt
extends StringsKt__StringNumberConversionsKt {
    @InlineOnly
    private static final int nativeIndexOf(String $this$nativeIndexOf, char ch, int fromIndex) {
        int $i$f$nativeIndexOf = 0;
        String string = $this$nativeIndexOf;
        if (string == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        return string.indexOf(ch, fromIndex);
    }

    @InlineOnly
    private static final int nativeIndexOf(String $this$nativeIndexOf, String str, int fromIndex) {
        int $i$f$nativeIndexOf = 0;
        String string = $this$nativeIndexOf;
        if (string == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        return string.indexOf(str, fromIndex);
    }

    @InlineOnly
    private static final int nativeLastIndexOf(String $this$nativeLastIndexOf, char ch, int fromIndex) {
        int $i$f$nativeLastIndexOf = 0;
        String string = $this$nativeLastIndexOf;
        if (string == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        return string.lastIndexOf(ch, fromIndex);
    }

    @InlineOnly
    private static final int nativeLastIndexOf(String $this$nativeLastIndexOf, String str, int fromIndex) {
        int $i$f$nativeLastIndexOf = 0;
        String string = $this$nativeLastIndexOf;
        if (string == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        return string.lastIndexOf(str, fromIndex);
    }

    public static final boolean equals(@Nullable String $this$equals, @Nullable String other, boolean ignoreCase) {
        if ($this$equals == null) {
            return other == null;
        }
        return !ignoreCase ? $this$equals.equals(other) : $this$equals.equalsIgnoreCase(other);
    }

    public static /* synthetic */ boolean equals$default(String string, String string2, boolean bl, int n, Object object) {
        if ((n & 2) != 0) {
            bl = false;
        }
        return StringsKt.equals(string, string2, bl);
    }

    @NotNull
    public static final String replace(@NotNull String $this$replace, char oldChar, char newChar, boolean ignoreCase) {
        Intrinsics.checkNotNullParameter($this$replace, "$this$replace");
        if (!ignoreCase) {
            String string = $this$replace.replace(oldChar, newChar);
            Intrinsics.checkNotNullExpressionValue(string, "(this as java.lang.Strin\u2026replace(oldChar, newChar)");
            return string;
        }
        return SequencesKt.joinToString$default(StringsKt.splitToSequence$default((CharSequence)$this$replace, new char[]{oldChar}, ignoreCase, 0, 4, null), String.valueOf(newChar), null, null, 0, null, null, 62, null);
    }

    public static /* synthetic */ String replace$default(String string, char c, char c2, boolean bl, int n, Object object) {
        if ((n & 4) != 0) {
            bl = false;
        }
        return StringsKt.replace(string, c, c2, bl);
    }

    @NotNull
    public static final String replace(@NotNull String $this$replace, @NotNull String oldValue, @NotNull String newValue, boolean ignoreCase) {
        Intrinsics.checkNotNullParameter($this$replace, "$this$replace");
        Intrinsics.checkNotNullParameter(oldValue, "oldValue");
        Intrinsics.checkNotNullParameter(newValue, "newValue");
        return SequencesKt.joinToString$default(StringsKt.splitToSequence$default((CharSequence)$this$replace, new String[]{oldValue}, ignoreCase, 0, 4, null), newValue, null, null, 0, null, null, 62, null);
    }

    public static /* synthetic */ String replace$default(String string, String string2, String string3, boolean bl, int n, Object object) {
        if ((n & 4) != 0) {
            bl = false;
        }
        return StringsKt.replace(string, string2, string3, bl);
    }

    @NotNull
    public static final String replaceFirst(@NotNull String $this$replaceFirst, char oldChar, char newChar, boolean ignoreCase) {
        String string;
        Intrinsics.checkNotNullParameter($this$replaceFirst, "$this$replaceFirst");
        int index = StringsKt.indexOf$default((CharSequence)$this$replaceFirst, oldChar, 0, ignoreCase, 2, null);
        if (index < 0) {
            string = $this$replaceFirst;
        } else {
            String string2 = $this$replaceFirst;
            int n = index + 1;
            CharSequence charSequence = String.valueOf(newChar);
            boolean bl = false;
            string = ((Object)StringsKt.replaceRange((CharSequence)string2, index, n, charSequence)).toString();
        }
        return string;
    }

    public static /* synthetic */ String replaceFirst$default(String string, char c, char c2, boolean bl, int n, Object object) {
        if ((n & 4) != 0) {
            bl = false;
        }
        return StringsKt.replaceFirst(string, c, c2, bl);
    }

    @NotNull
    public static final String replaceFirst(@NotNull String $this$replaceFirst, @NotNull String oldValue, @NotNull String newValue, boolean ignoreCase) {
        String string;
        Intrinsics.checkNotNullParameter($this$replaceFirst, "$this$replaceFirst");
        Intrinsics.checkNotNullParameter(oldValue, "oldValue");
        Intrinsics.checkNotNullParameter(newValue, "newValue");
        int index = StringsKt.indexOf$default((CharSequence)$this$replaceFirst, oldValue, 0, ignoreCase, 2, null);
        if (index < 0) {
            string = $this$replaceFirst;
        } else {
            String string2 = $this$replaceFirst;
            int n = index + oldValue.length();
            boolean bl = false;
            string = ((Object)StringsKt.replaceRange((CharSequence)string2, index, n, (CharSequence)newValue)).toString();
        }
        return string;
    }

    public static /* synthetic */ String replaceFirst$default(String string, String string2, String string3, boolean bl, int n, Object object) {
        if ((n & 4) != 0) {
            bl = false;
        }
        return StringsKt.replaceFirst(string, string2, string3, bl);
    }

    @InlineOnly
    private static final String toUpperCase(String $this$toUpperCase) {
        int $i$f$toUpperCase = 0;
        String string = $this$toUpperCase;
        if (string == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        String string2 = string.toUpperCase();
        Intrinsics.checkNotNullExpressionValue(string2, "(this as java.lang.String).toUpperCase()");
        return string2;
    }

    @InlineOnly
    private static final String toLowerCase(String $this$toLowerCase) {
        int $i$f$toLowerCase = 0;
        String string = $this$toLowerCase;
        if (string == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        String string2 = string.toLowerCase();
        Intrinsics.checkNotNullExpressionValue(string2, "(this as java.lang.String).toLowerCase()");
        return string2;
    }

    @SinceKotlin(version="1.4")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @NotNull
    public static final String concatToString(@NotNull char[] $this$concatToString) {
        Intrinsics.checkNotNullParameter($this$concatToString, "$this$concatToString");
        boolean bl = false;
        return new String($this$concatToString);
    }

    @SinceKotlin(version="1.4")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @NotNull
    public static final String concatToString(@NotNull char[] $this$concatToString, int startIndex, int endIndex) {
        Intrinsics.checkNotNullParameter($this$concatToString, "$this$concatToString");
        AbstractList.Companion.checkBoundsIndexes$kotlin_stdlib(startIndex, endIndex, $this$concatToString.length);
        int n = endIndex - startIndex;
        boolean bl = false;
        return new String($this$concatToString, startIndex, n);
    }

    public static /* synthetic */ String concatToString$default(char[] cArray, int n, int n2, int n3, Object object) {
        if ((n3 & 1) != 0) {
            n = 0;
        }
        if ((n3 & 2) != 0) {
            n2 = cArray.length;
        }
        return StringsKt.concatToString(cArray, n, n2);
    }

    @SinceKotlin(version="1.4")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @NotNull
    public static final char[] toCharArray(@NotNull String $this$toCharArray, int startIndex, int endIndex) {
        Intrinsics.checkNotNullParameter($this$toCharArray, "$this$toCharArray");
        AbstractList.Companion.checkBoundsIndexes$kotlin_stdlib(startIndex, endIndex, $this$toCharArray.length());
        String string = $this$toCharArray;
        char[] cArray = new char[endIndex - startIndex];
        int n = 0;
        boolean bl = false;
        string.getChars(startIndex, endIndex, cArray, n);
        return cArray;
    }

    public static /* synthetic */ char[] toCharArray$default(String string, int n, int n2, int n3, Object object) {
        if ((n3 & 1) != 0) {
            n = 0;
        }
        if ((n3 & 2) != 0) {
            n2 = string.length();
        }
        return StringsKt.toCharArray(string, n, n2);
    }

    @SinceKotlin(version="1.4")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @NotNull
    public static final String decodeToString(@NotNull byte[] $this$decodeToString) {
        Intrinsics.checkNotNullParameter($this$decodeToString, "$this$decodeToString");
        boolean bl = false;
        return new String($this$decodeToString, Charsets.UTF_8);
    }

    @SinceKotlin(version="1.4")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @NotNull
    public static final String decodeToString(@NotNull byte[] $this$decodeToString, int startIndex, int endIndex, boolean throwOnInvalidSequence) {
        Intrinsics.checkNotNullParameter($this$decodeToString, "$this$decodeToString");
        AbstractList.Companion.checkBoundsIndexes$kotlin_stdlib(startIndex, endIndex, $this$decodeToString.length);
        if (!throwOnInvalidSequence) {
            int n = endIndex - startIndex;
            boolean bl = false;
            return new String($this$decodeToString, startIndex, n, Charsets.UTF_8);
        }
        CharsetDecoder decoder = Charsets.UTF_8.newDecoder().onMalformedInput(CodingErrorAction.REPORT).onUnmappableCharacter(CodingErrorAction.REPORT);
        String string = decoder.decode(ByteBuffer.wrap($this$decodeToString, startIndex, endIndex - startIndex)).toString();
        Intrinsics.checkNotNullExpressionValue(string, "decoder.decode(ByteBuffe\u2026- startIndex)).toString()");
        return string;
    }

    public static /* synthetic */ String decodeToString$default(byte[] byArray, int n, int n2, boolean bl, int n3, Object object) {
        if ((n3 & 1) != 0) {
            n = 0;
        }
        if ((n3 & 2) != 0) {
            n2 = byArray.length;
        }
        if ((n3 & 4) != 0) {
            bl = false;
        }
        return StringsKt.decodeToString(byArray, n, n2, bl);
    }

    @SinceKotlin(version="1.4")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @NotNull
    public static final byte[] encodeToByteArray(@NotNull String $this$encodeToByteArray) {
        Intrinsics.checkNotNullParameter($this$encodeToByteArray, "$this$encodeToByteArray");
        String string = $this$encodeToByteArray;
        Charset charset = Charsets.UTF_8;
        boolean bl = false;
        byte[] byArray = string.getBytes(charset);
        Intrinsics.checkNotNullExpressionValue(byArray, "(this as java.lang.String).getBytes(charset)");
        return byArray;
    }

    /*
     * Enabled aggressive block sorting
     */
    @SinceKotlin(version="1.4")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @NotNull
    public static final byte[] encodeToByteArray(@NotNull String $this$encodeToByteArray, int startIndex, int endIndex, boolean throwOnInvalidSequence) {
        byte[] byArray;
        Intrinsics.checkNotNullParameter($this$encodeToByteArray, "$this$encodeToByteArray");
        AbstractList.Companion.checkBoundsIndexes$kotlin_stdlib(startIndex, endIndex, $this$encodeToByteArray.length());
        if (!throwOnInvalidSequence) {
            String string = $this$encodeToByteArray;
            boolean bl = false;
            String string2 = string.substring(startIndex, endIndex);
            Intrinsics.checkNotNullExpressionValue(string2, "(this as java.lang.Strin\u2026ing(startIndex, endIndex)");
            string = string2;
            Charset charset = Charsets.UTF_8;
            boolean bl2 = false;
            String string3 = string;
            if (string3 == null) {
                throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
            }
            byte[] byArray2 = string3.getBytes(charset);
            Intrinsics.checkNotNullExpressionValue(byArray2, "(this as java.lang.String).getBytes(charset)");
            return byArray2;
        }
        CharsetEncoder encoder = Charsets.UTF_8.newEncoder().onMalformedInput(CodingErrorAction.REPORT).onUnmappableCharacter(CodingErrorAction.REPORT);
        ByteBuffer byteBuffer = encoder.encode(CharBuffer.wrap($this$encodeToByteArray, startIndex, endIndex));
        if (byteBuffer.hasArray() && byteBuffer.arrayOffset() == 0) {
            int n = byteBuffer.remaining();
            byte[] byArray3 = byteBuffer.array();
            Intrinsics.checkNotNull(byArray3);
            if (n == byArray3.length) {
                byte[] byArray4 = byteBuffer.array();
                byArray = byArray4;
                Intrinsics.checkNotNullExpressionValue(byArray4, "byteBuffer.array()");
                return byArray;
            }
        }
        byte[] byArray5 = new byte[byteBuffer.remaining()];
        boolean bl = false;
        boolean bl3 = false;
        byte[] it = byArray5;
        boolean bl4 = false;
        byteBuffer.get(it);
        byArray = byArray5;
        return byArray;
    }

    public static /* synthetic */ byte[] encodeToByteArray$default(String string, int n, int n2, boolean bl, int n3, Object object) {
        if ((n3 & 1) != 0) {
            n = 0;
        }
        if ((n3 & 2) != 0) {
            n2 = string.length();
        }
        if ((n3 & 4) != 0) {
            bl = false;
        }
        return StringsKt.encodeToByteArray(string, n, n2, bl);
    }

    @InlineOnly
    private static final char[] toCharArray(String $this$toCharArray) {
        int $i$f$toCharArray = 0;
        String string = $this$toCharArray;
        if (string == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        char[] cArray = string.toCharArray();
        Intrinsics.checkNotNullExpressionValue(cArray, "(this as java.lang.String).toCharArray()");
        return cArray;
    }

    @InlineOnly
    private static final char[] toCharArray(String $this$toCharArray, char[] destination, int destinationOffset, int startIndex, int endIndex) {
        int $i$f$toCharArray = 0;
        String string = $this$toCharArray;
        if (string == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        string.getChars(startIndex, endIndex, destination, destinationOffset);
        return destination;
    }

    static /* synthetic */ char[] toCharArray$default(String $this$toCharArray, char[] destination, int destinationOffset, int startIndex, int endIndex, int n, Object object) {
        if ((n & 2) != 0) {
            destinationOffset = 0;
        }
        if ((n & 4) != 0) {
            startIndex = 0;
        }
        if ((n & 8) != 0) {
            endIndex = $this$toCharArray.length();
        }
        boolean $i$f$toCharArray = false;
        String string = $this$toCharArray;
        if (string == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        string.getChars(startIndex, endIndex, destination, destinationOffset);
        return destination;
    }

    @InlineOnly
    private static final String format(String $this$format, Object ... args2) {
        int $i$f$format = 0;
        String string = String.format($this$format, Arrays.copyOf(args2, args2.length));
        Intrinsics.checkNotNullExpressionValue(string, "java.lang.String.format(this, *args)");
        return string;
    }

    @InlineOnly
    private static final String format(StringCompanionObject $this$format, String format, Object ... args2) {
        int $i$f$format = 0;
        String string = String.format(format, Arrays.copyOf(args2, args2.length));
        Intrinsics.checkNotNullExpressionValue(string, "java.lang.String.format(format, *args)");
        return string;
    }

    @Deprecated(message="Use Kotlin compiler 1.4 to avoid deprecation warning.")
    @DeprecatedSinceKotlin(hiddenSince="1.4")
    @InlineOnly
    private static final /* synthetic */ String format(String $this$format, Locale locale, Object ... args2) {
        int $i$f$format = 0;
        String string = String.format(locale, $this$format, Arrays.copyOf(args2, args2.length));
        Intrinsics.checkNotNullExpressionValue(string, "java.lang.String.format(locale, this, *args)");
        return string;
    }

    @SinceKotlin(version="1.4")
    @JvmName(name="formatNullable")
    @InlineOnly
    private static final String formatNullable(String $this$format, Locale locale, Object ... args2) {
        int $i$f$formatNullable = 0;
        String string = String.format(locale, $this$format, Arrays.copyOf(args2, args2.length));
        Intrinsics.checkNotNullExpressionValue(string, "java.lang.String.format(locale, this, *args)");
        return string;
    }

    @Deprecated(message="Use Kotlin compiler 1.4 to avoid deprecation warning.")
    @DeprecatedSinceKotlin(hiddenSince="1.4")
    @InlineOnly
    private static final /* synthetic */ String format(StringCompanionObject $this$format, Locale locale, String format, Object ... args2) {
        int $i$f$format = 0;
        String string = String.format(locale, format, Arrays.copyOf(args2, args2.length));
        Intrinsics.checkNotNullExpressionValue(string, "java.lang.String.format(locale, format, *args)");
        return string;
    }

    @SinceKotlin(version="1.4")
    @JvmName(name="formatNullable")
    @InlineOnly
    private static final String formatNullable(StringCompanionObject $this$format, Locale locale, String format, Object ... args2) {
        int $i$f$formatNullable = 0;
        String string = String.format(locale, format, Arrays.copyOf(args2, args2.length));
        Intrinsics.checkNotNullExpressionValue(string, "java.lang.String.format(locale, format, *args)");
        return string;
    }

    @NotNull
    public static final List<String> split(@NotNull CharSequence $this$split, @NotNull Pattern regex, int limit) {
        Intrinsics.checkNotNullParameter($this$split, "$this$split");
        Intrinsics.checkNotNullParameter(regex, "regex");
        boolean bl = limit >= 0;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "Limit must be non-negative, but was " + limit + '.';
            throw (Throwable)new IllegalArgumentException(string.toString());
        }
        String[] stringArray = regex.split($this$split, limit == 0 ? -1 : limit);
        Intrinsics.checkNotNullExpressionValue(stringArray, "regex.split(this, if (limit == 0) -1 else limit)");
        return ArraysKt.asList(stringArray);
    }

    public static /* synthetic */ List split$default(CharSequence charSequence, Pattern pattern, int n, int n2, Object object) {
        if ((n2 & 2) != 0) {
            n = 0;
        }
        return StringsKt.split(charSequence, pattern, n);
    }

    @InlineOnly
    private static final String substring(String $this$substring, int startIndex) {
        int $i$f$substring = 0;
        String string = $this$substring;
        if (string == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        String string2 = string.substring(startIndex);
        Intrinsics.checkNotNullExpressionValue(string2, "(this as java.lang.String).substring(startIndex)");
        return string2;
    }

    @InlineOnly
    private static final String substring(String $this$substring, int startIndex, int endIndex) {
        int $i$f$substring = 0;
        String string = $this$substring;
        if (string == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        String string2 = string.substring(startIndex, endIndex);
        Intrinsics.checkNotNullExpressionValue(string2, "(this as java.lang.Strin\u2026ing(startIndex, endIndex)");
        return string2;
    }

    public static final boolean startsWith(@NotNull String $this$startsWith, @NotNull String prefix, boolean ignoreCase) {
        Intrinsics.checkNotNullParameter($this$startsWith, "$this$startsWith");
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        if (!ignoreCase) {
            return $this$startsWith.startsWith(prefix);
        }
        return StringsKt.regionMatches($this$startsWith, 0, prefix, 0, prefix.length(), ignoreCase);
    }

    public static /* synthetic */ boolean startsWith$default(String string, String string2, boolean bl, int n, Object object) {
        if ((n & 2) != 0) {
            bl = false;
        }
        return StringsKt.startsWith(string, string2, bl);
    }

    public static final boolean startsWith(@NotNull String $this$startsWith, @NotNull String prefix, int startIndex, boolean ignoreCase) {
        Intrinsics.checkNotNullParameter($this$startsWith, "$this$startsWith");
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        if (!ignoreCase) {
            return $this$startsWith.startsWith(prefix, startIndex);
        }
        return StringsKt.regionMatches($this$startsWith, startIndex, prefix, 0, prefix.length(), ignoreCase);
    }

    public static /* synthetic */ boolean startsWith$default(String string, String string2, int n, boolean bl, int n2, Object object) {
        if ((n2 & 4) != 0) {
            bl = false;
        }
        return StringsKt.startsWith(string, string2, n, bl);
    }

    public static final boolean endsWith(@NotNull String $this$endsWith, @NotNull String suffix, boolean ignoreCase) {
        Intrinsics.checkNotNullParameter($this$endsWith, "$this$endsWith");
        Intrinsics.checkNotNullParameter(suffix, "suffix");
        if (!ignoreCase) {
            return $this$endsWith.endsWith(suffix);
        }
        return StringsKt.regionMatches($this$endsWith, $this$endsWith.length() - suffix.length(), suffix, 0, suffix.length(), true);
    }

    public static /* synthetic */ boolean endsWith$default(String string, String string2, boolean bl, int n, Object object) {
        if ((n & 2) != 0) {
            bl = false;
        }
        return StringsKt.endsWith(string, string2, bl);
    }

    @InlineOnly
    private static final String String(byte[] bytes, int offset, int length, Charset charset) {
        int $i$f$String = 0;
        return new String(bytes, offset, length, charset);
    }

    @InlineOnly
    private static final String String(byte[] bytes, Charset charset) {
        int $i$f$String = 0;
        return new String(bytes, charset);
    }

    @InlineOnly
    private static final String String(byte[] bytes, int offset, int length) {
        int $i$f$String = 0;
        return new String(bytes, offset, length, Charsets.UTF_8);
    }

    @InlineOnly
    private static final String String(byte[] bytes) {
        int $i$f$String = 0;
        return new String(bytes, Charsets.UTF_8);
    }

    @InlineOnly
    private static final String String(char[] chars) {
        int $i$f$String = 0;
        return new String(chars);
    }

    @InlineOnly
    private static final String String(char[] chars, int offset, int length) {
        int $i$f$String = 0;
        return new String(chars, offset, length);
    }

    @InlineOnly
    private static final String String(int[] codePoints, int offset, int length) {
        int $i$f$String = 0;
        return new String(codePoints, offset, length);
    }

    @InlineOnly
    private static final String String(StringBuffer stringBuffer) {
        int $i$f$String = 0;
        return new String(stringBuffer);
    }

    @InlineOnly
    private static final String String(StringBuilder stringBuilder) {
        int $i$f$String = 0;
        return new String(stringBuilder);
    }

    @InlineOnly
    private static final int codePointAt(String $this$codePointAt, int index) {
        int $i$f$codePointAt = 0;
        String string = $this$codePointAt;
        if (string == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        return string.codePointAt(index);
    }

    @InlineOnly
    private static final int codePointBefore(String $this$codePointBefore, int index) {
        int $i$f$codePointBefore = 0;
        String string = $this$codePointBefore;
        if (string == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        return string.codePointBefore(index);
    }

    @InlineOnly
    private static final int codePointCount(String $this$codePointCount, int beginIndex, int endIndex) {
        int $i$f$codePointCount = 0;
        String string = $this$codePointCount;
        if (string == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        return string.codePointCount(beginIndex, endIndex);
    }

    public static final int compareTo(@NotNull String $this$compareTo, @NotNull String other, boolean ignoreCase) {
        Intrinsics.checkNotNullParameter($this$compareTo, "$this$compareTo");
        Intrinsics.checkNotNullParameter(other, "other");
        if (ignoreCase) {
            return $this$compareTo.compareToIgnoreCase(other);
        }
        return $this$compareTo.compareTo(other);
    }

    public static /* synthetic */ int compareTo$default(String string, String string2, boolean bl, int n, Object object) {
        if ((n & 2) != 0) {
            bl = false;
        }
        return StringsKt.compareTo(string, string2, bl);
    }

    @InlineOnly
    private static final boolean contentEquals(String $this$contentEquals, CharSequence charSequence) {
        int $i$f$contentEquals = 0;
        String string = $this$contentEquals;
        if (string == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        return string.contentEquals(charSequence);
    }

    @InlineOnly
    private static final boolean contentEquals(String $this$contentEquals, StringBuffer stringBuilder) {
        int $i$f$contentEquals = 0;
        String string = $this$contentEquals;
        if (string == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        return string.contentEquals(stringBuilder);
    }

    @InlineOnly
    private static final String intern(String $this$intern) {
        int $i$f$intern = 0;
        String string = $this$intern;
        if (string == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        String string2 = string.intern();
        Intrinsics.checkNotNullExpressionValue(string2, "(this as java.lang.String).intern()");
        return string2;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static final boolean isBlank(@NotNull CharSequence $this$isBlank) {
        int it;
        Intrinsics.checkNotNullParameter($this$isBlank, "$this$isBlank");
        if ($this$isBlank.length() == 0) return true;
        Iterable $this$all$iv = StringsKt.getIndices($this$isBlank);
        boolean $i$f$all = false;
        if ($this$all$iv instanceof Collection && ((Collection)$this$all$iv).isEmpty()) {
            return true;
        }
        Iterator iterator2 = $this$all$iv.iterator();
        do {
            int element$iv;
            if (!iterator2.hasNext()) return true;
            it = element$iv = ((IntIterator)iterator2).nextInt();
            boolean bl = false;
        } while (CharsKt.isWhitespace($this$isBlank.charAt(it)));
        return false;
    }

    @InlineOnly
    private static final int offsetByCodePoints(String $this$offsetByCodePoints, int index, int codePointOffset) {
        int $i$f$offsetByCodePoints = 0;
        String string = $this$offsetByCodePoints;
        if (string == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        return string.offsetByCodePoints(index, codePointOffset);
    }

    public static final boolean regionMatches(@NotNull CharSequence $this$regionMatches, int thisOffset, @NotNull CharSequence other, int otherOffset, int length, boolean ignoreCase) {
        Intrinsics.checkNotNullParameter($this$regionMatches, "$this$regionMatches");
        Intrinsics.checkNotNullParameter(other, "other");
        if ($this$regionMatches instanceof String && other instanceof String) {
            return StringsKt.regionMatches((String)$this$regionMatches, thisOffset, (String)other, otherOffset, length, ignoreCase);
        }
        return StringsKt.regionMatchesImpl($this$regionMatches, thisOffset, other, otherOffset, length, ignoreCase);
    }

    public static /* synthetic */ boolean regionMatches$default(CharSequence charSequence, int n, CharSequence charSequence2, int n2, int n3, boolean bl, int n4, Object object) {
        if ((n4 & 0x10) != 0) {
            bl = false;
        }
        return StringsKt.regionMatches(charSequence, n, charSequence2, n2, n3, bl);
    }

    public static final boolean regionMatches(@NotNull String $this$regionMatches, int thisOffset, @NotNull String other, int otherOffset, int length, boolean ignoreCase) {
        Intrinsics.checkNotNullParameter($this$regionMatches, "$this$regionMatches");
        Intrinsics.checkNotNullParameter(other, "other");
        return !ignoreCase ? $this$regionMatches.regionMatches(thisOffset, other, otherOffset, length) : $this$regionMatches.regionMatches(ignoreCase, thisOffset, other, otherOffset, length);
    }

    public static /* synthetic */ boolean regionMatches$default(String string, int n, String string2, int n2, int n3, boolean bl, int n4, Object object) {
        if ((n4 & 0x10) != 0) {
            bl = false;
        }
        return StringsKt.regionMatches(string, n, string2, n2, n3, bl);
    }

    @InlineOnly
    private static final String toLowerCase(String $this$toLowerCase, Locale locale) {
        int $i$f$toLowerCase = 0;
        String string = $this$toLowerCase;
        if (string == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        String string2 = string.toLowerCase(locale);
        Intrinsics.checkNotNullExpressionValue(string2, "(this as java.lang.String).toLowerCase(locale)");
        return string2;
    }

    @InlineOnly
    private static final String toUpperCase(String $this$toUpperCase, Locale locale) {
        int $i$f$toUpperCase = 0;
        String string = $this$toUpperCase;
        if (string == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        String string2 = string.toUpperCase(locale);
        Intrinsics.checkNotNullExpressionValue(string2, "(this as java.lang.String).toUpperCase(locale)");
        return string2;
    }

    @InlineOnly
    private static final byte[] toByteArray(String $this$toByteArray, Charset charset) {
        int $i$f$toByteArray = 0;
        String string = $this$toByteArray;
        if (string == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        byte[] byArray = string.getBytes(charset);
        Intrinsics.checkNotNullExpressionValue(byArray, "(this as java.lang.String).getBytes(charset)");
        return byArray;
    }

    static /* synthetic */ byte[] toByteArray$default(String $this$toByteArray, Charset charset, int n, Object object) {
        if ((n & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        boolean $i$f$toByteArray = false;
        String string = $this$toByteArray;
        if (string == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        byte[] byArray = string.getBytes(charset);
        Intrinsics.checkNotNullExpressionValue(byArray, "(this as java.lang.String).getBytes(charset)");
        return byArray;
    }

    @InlineOnly
    private static final Pattern toPattern(String $this$toPattern, int flags) {
        int $i$f$toPattern = 0;
        Pattern pattern = Pattern.compile($this$toPattern, flags);
        Intrinsics.checkNotNullExpressionValue(pattern, "java.util.regex.Pattern.compile(this, flags)");
        return pattern;
    }

    static /* synthetic */ Pattern toPattern$default(String $this$toPattern, int flags, int n, Object object) {
        if ((n & 1) != 0) {
            flags = 0;
        }
        boolean $i$f$toPattern = false;
        Pattern pattern = Pattern.compile($this$toPattern, flags);
        Intrinsics.checkNotNullExpressionValue(pattern, "java.util.regex.Pattern.compile(this, flags)");
        return pattern;
    }

    @NotNull
    public static final String capitalize(@NotNull String $this$capitalize) {
        Intrinsics.checkNotNullParameter($this$capitalize, "$this$capitalize");
        Locale locale = Locale.getDefault();
        Intrinsics.checkNotNullExpressionValue(locale, "Locale.getDefault()");
        return StringsKt.capitalize($this$capitalize, locale);
    }

    @SinceKotlin(version="1.4")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @LowPriorityInOverloadResolution
    @NotNull
    public static final String capitalize(@NotNull String $this$capitalize, @NotNull Locale locale) {
        Intrinsics.checkNotNullParameter($this$capitalize, "$this$capitalize");
        Intrinsics.checkNotNullParameter(locale, "locale");
        CharSequence charSequence = $this$capitalize;
        char c = '\u0000';
        if (charSequence.length() > 0) {
            char firstChar;
            c = firstChar = $this$capitalize.charAt(0);
            boolean bl = false;
            if (Character.isLowerCase(c)) {
                int n;
                c = '\u0000';
                bl = false;
                StringBuilder stringBuilder = new StringBuilder();
                boolean bl2 = false;
                boolean bl3 = false;
                StringBuilder $this$buildString = stringBuilder;
                boolean bl4 = false;
                char c2 = firstChar;
                int n2 = 0;
                char titleChar = Character.toTitleCase(c2);
                c2 = firstChar;
                n2 = 0;
                if (titleChar != Character.toUpperCase(c2)) {
                    $this$buildString.append(titleChar);
                } else {
                    String string = $this$capitalize;
                    n2 = 0;
                    n = 1;
                    boolean bl5 = false;
                    String string2 = string.substring(n2, n);
                    Intrinsics.checkNotNullExpressionValue(string2, "(this as java.lang.Strin\u2026ing(startIndex, endIndex)");
                    string = string2;
                    Locale locale2 = locale;
                    n = 0;
                    String string3 = string;
                    if (string3 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                    }
                    String string4 = string3.toUpperCase(locale2);
                    Intrinsics.checkNotNullExpressionValue(string4, "(this as java.lang.String).toUpperCase(locale)");
                    $this$buildString.append(string4);
                }
                String string = $this$capitalize;
                int n3 = 1;
                n = 0;
                String string5 = string.substring(n3);
                Intrinsics.checkNotNullExpressionValue(string5, "(this as java.lang.String).substring(startIndex)");
                $this$buildString.append(string5);
                String string6 = stringBuilder.toString();
                Intrinsics.checkNotNullExpressionValue(string6, "StringBuilder().apply(builderAction).toString()");
                return string6;
            }
        }
        return $this$capitalize;
    }

    /*
     * Enabled aggressive block sorting
     */
    @NotNull
    public static final String decapitalize(@NotNull String $this$decapitalize) {
        String string;
        Intrinsics.checkNotNullParameter($this$decapitalize, "$this$decapitalize");
        CharSequence charSequence = $this$decapitalize;
        int n = 0;
        if (charSequence.length() > 0) {
            char c = $this$decapitalize.charAt(0);
            n = 0;
            if (!Character.isLowerCase(c)) {
                StringBuilder stringBuilder = new StringBuilder();
                String string2 = $this$decapitalize;
                n = 0;
                int n2 = 1;
                boolean bl = false;
                String string3 = string2.substring(n, n2);
                Intrinsics.checkNotNullExpressionValue(string3, "(this as java.lang.Strin\u2026ing(startIndex, endIndex)");
                string2 = string3;
                n = 0;
                String string4 = string2;
                if (string4 == null) {
                    throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                }
                String string5 = string4.toLowerCase();
                Intrinsics.checkNotNullExpressionValue(string5, "(this as java.lang.String).toLowerCase()");
                StringBuilder stringBuilder2 = stringBuilder.append(string5);
                string2 = $this$decapitalize;
                n = 1;
                n2 = 0;
                String string6 = string2.substring(n);
                Intrinsics.checkNotNullExpressionValue(string6, "(this as java.lang.String).substring(startIndex)");
                string = stringBuilder2.append(string6).toString();
                return string;
            }
        }
        string = $this$decapitalize;
        return string;
    }

    /*
     * Enabled aggressive block sorting
     */
    @SinceKotlin(version="1.4")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @LowPriorityInOverloadResolution
    @NotNull
    public static final String decapitalize(@NotNull String $this$decapitalize, @NotNull Locale locale) {
        String string;
        Intrinsics.checkNotNullParameter($this$decapitalize, "$this$decapitalize");
        Intrinsics.checkNotNullParameter(locale, "locale");
        CharSequence charSequence = $this$decapitalize;
        int n = 0;
        if (charSequence.length() > 0) {
            char c = $this$decapitalize.charAt(0);
            n = 0;
            if (!Character.isLowerCase(c)) {
                StringBuilder stringBuilder = new StringBuilder();
                String string2 = $this$decapitalize;
                n = 0;
                int n2 = 1;
                boolean bl = false;
                String string3 = string2.substring(n, n2);
                Intrinsics.checkNotNullExpressionValue(string3, "(this as java.lang.Strin\u2026ing(startIndex, endIndex)");
                string2 = string3;
                n = 0;
                String string4 = string2;
                if (string4 == null) {
                    throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                }
                String string5 = string4.toLowerCase(locale);
                Intrinsics.checkNotNullExpressionValue(string5, "(this as java.lang.String).toLowerCase(locale)");
                StringBuilder stringBuilder2 = stringBuilder.append(string5);
                string2 = $this$decapitalize;
                n = 1;
                n2 = 0;
                String string6 = string2.substring(n);
                Intrinsics.checkNotNullExpressionValue(string6, "(this as java.lang.String).substring(startIndex)");
                string = stringBuilder2.append(string6).toString();
                return string;
            }
        }
        string = $this$decapitalize;
        return string;
    }

    /*
     * WARNING - void declaration
     */
    @NotNull
    public static final String repeat(@NotNull CharSequence $this$repeat, int n) {
        String string;
        Intrinsics.checkNotNullParameter($this$repeat, "$this$repeat");
        char c = n >= 0 ? (char)'\u0001' : '\u0000';
        int n2 = 0;
        int n3 = 0;
        if (c == '\u0000') {
            boolean bl = false;
            String string2 = "Count 'n' must be non-negative, but was " + n + '.';
            throw (Throwable)new IllegalArgumentException(string2.toString());
        }
        block0 : switch (n) {
            case 0: {
                string = "";
                break;
            }
            case 1: {
                string = ((Object)$this$repeat).toString();
                break;
            }
            default: {
                switch ($this$repeat.length()) {
                    case 0: {
                        string = "";
                        break block0;
                    }
                    case 1: {
                        c = $this$repeat.charAt(0);
                        n2 = 0;
                        n3 = 0;
                        char c2 = c;
                        boolean bl = false;
                        int n4 = n;
                        char[] cArray = new char[n4];
                        int n5 = 0;
                        while (n5 < n4) {
                            char c3;
                            int n6 = n5;
                            int n7 = n5++;
                            char[] cArray2 = cArray;
                            boolean bl2 = false;
                            cArray2[n7] = c3 = c2;
                        }
                        char[] cArray3 = cArray;
                        boolean bl3 = false;
                        string = new String(cArray3);
                        break block0;
                    }
                }
                StringBuilder sb = new StringBuilder(n * $this$repeat.length());
                n2 = 1;
                n3 = n;
                if (n2 <= n3) {
                    while (true) {
                        void i;
                        sb.append($this$repeat);
                        if (i == n3) break;
                        ++i;
                    }
                }
                String string3 = sb.toString();
                string = string3;
                Intrinsics.checkNotNullExpressionValue(string3, "sb.toString()");
            }
        }
        return string;
    }

    @NotNull
    public static final Comparator<String> getCASE_INSENSITIVE_ORDER(@NotNull StringCompanionObject $this$CASE_INSENSITIVE_ORDER) {
        Intrinsics.checkNotNullParameter($this$CASE_INSENSITIVE_ORDER, "$this$CASE_INSENSITIVE_ORDER");
        Comparator comparator = String.CASE_INSENSITIVE_ORDER;
        Intrinsics.checkNotNullExpressionValue(comparator, "java.lang.String.CASE_INSENSITIVE_ORDER");
        return comparator;
    }
}

