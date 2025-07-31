/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.text;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.ExperimentalStdlibApi;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.SinceKotlin;
import kotlin.WasExperimental;
import kotlin.internal.InlineOnly;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlin.text.StringsKt__RegexExtensionsKt;
import kotlin.text.SystemProperties;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=5, xi=1, d1={"\u0000\\\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0005\n\u0002\u0010\u0006\n\u0002\u0010\u0007\n\u0002\u0010\b\n\u0002\u0010\t\n\u0002\u0010\n\n\u0000\n\u0002\u0010\u0019\n\u0002\b\u0002\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\f\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0005\u001a\u001f\u0010\u0000\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\u0087\b\u001a\u001d\u0010\u0000\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\u0005H\u0087\b\u001a\u001d\u0010\u0000\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\u0006H\u0087\b\u001a\u001d\u0010\u0000\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\u0007H\u0087\b\u001a\u001d\u0010\u0000\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\bH\u0087\b\u001a\u001d\u0010\u0000\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\tH\u0087\b\u001a\u001d\u0010\u0000\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\nH\u0087\b\u001a%\u0010\u0000\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u000e\u0010\u0003\u001a\n\u0018\u00010\u0001j\u0004\u0018\u0001`\u0002H\u0087\b\u001a-\u0010\u000b\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\bH\u0087\b\u001a-\u0010\u000b\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\u000f2\u0006\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\bH\u0087\b\u001a\u0014\u0010\u0010\u001a\u00060\u0011j\u0002`\u0012*\u00060\u0011j\u0002`\u0012H\u0007\u001a\u001d\u0010\u0010\u001a\u00060\u0011j\u0002`\u0012*\u00060\u0011j\u0002`\u00122\u0006\u0010\u0003\u001a\u00020\u0013H\u0087\b\u001a\u001f\u0010\u0010\u001a\u00060\u0011j\u0002`\u0012*\u00060\u0011j\u0002`\u00122\b\u0010\u0003\u001a\u0004\u0018\u00010\u000fH\u0087\b\u001a\u0014\u0010\u0010\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u0002H\u0007\u001a\u001f\u0010\u0010\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\u0087\b\u001a\u001f\u0010\u0010\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0014H\u0087\b\u001a\u001d\u0010\u0010\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\u0015H\u0087\b\u001a\u001d\u0010\u0010\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\u0005H\u0087\b\u001a\u001d\u0010\u0010\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\u0013H\u0087\b\u001a\u001d\u0010\u0010\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\fH\u0087\b\u001a\u001f\u0010\u0010\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u000fH\u0087\b\u001a\u001d\u0010\u0010\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\u0006H\u0087\b\u001a\u001d\u0010\u0010\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\u0007H\u0087\b\u001a\u001d\u0010\u0010\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\bH\u0087\b\u001a\u001d\u0010\u0010\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\tH\u0087\b\u001a\u001d\u0010\u0010\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\nH\u0087\b\u001a\u001f\u0010\u0010\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0016H\u0087\b\u001a%\u0010\u0010\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u000e\u0010\u0003\u001a\n\u0018\u00010\u0001j\u0004\u0018\u0001`\u0002H\u0087\b\u001a\u0014\u0010\u0017\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u0002H\u0007\u001a\u001d\u0010\u0018\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0019\u001a\u00020\bH\u0087\b\u001a%\u0010\u001a\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\bH\u0087\b\u001a5\u0010\u001b\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0019\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\bH\u0087\b\u001a5\u0010\u001b\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0019\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u000f2\u0006\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\bH\u0087\b\u001a!\u0010\u001c\u001a\u00020\u001d*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0019\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u0013H\u0087\n\u001a-\u0010\u001e\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u0016H\u0087\b\u001a7\u0010\u001f\u001a\u00020\u001d*\u00060\u0001j\u0002`\u00022\u0006\u0010 \u001a\u00020\f2\b\b\u0002\u0010!\u001a\u00020\b2\b\b\u0002\u0010\r\u001a\u00020\b2\b\b\u0002\u0010\u000e\u001a\u00020\bH\u0087\b\u00a8\u0006\""}, d2={"appendLine", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "value", "Ljava/lang/StringBuffer;", "", "", "", "", "", "", "appendRange", "", "startIndex", "endIndex", "", "appendln", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "", "", "", "", "clear", "deleteAt", "index", "deleteRange", "insertRange", "set", "", "setRange", "toCharArray", "destination", "destinationOffset", "kotlin-stdlib"}, xs="kotlin/text/StringsKt")
class StringsKt__StringBuilderJVMKt
extends StringsKt__RegexExtensionsKt {
    @SinceKotlin(version="1.3")
    @NotNull
    public static final StringBuilder clear(@NotNull StringBuilder $this$clear) {
        Intrinsics.checkNotNullParameter($this$clear, "$this$clear");
        StringBuilder stringBuilder = $this$clear;
        boolean bl = false;
        boolean bl2 = false;
        StringBuilder $this$apply = stringBuilder;
        boolean bl3 = false;
        $this$apply.setLength(0);
        return stringBuilder;
    }

    @InlineOnly
    private static final void set(StringBuilder $this$set, int index, char value) {
        int $i$f$set = 0;
        Intrinsics.checkNotNullParameter($this$set, "$this$set");
        $this$set.setCharAt(index, value);
    }

    @SinceKotlin(version="1.4")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @InlineOnly
    private static final StringBuilder setRange(StringBuilder $this$setRange, int startIndex, int endIndex, String value) {
        int $i$f$setRange = 0;
        StringBuilder stringBuilder = $this$setRange.replace(startIndex, endIndex, value);
        Intrinsics.checkNotNullExpressionValue(stringBuilder, "this.replace(startIndex, endIndex, value)");
        return stringBuilder;
    }

    @SinceKotlin(version="1.4")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @InlineOnly
    private static final StringBuilder deleteAt(StringBuilder $this$deleteAt, int index) {
        int $i$f$deleteAt = 0;
        StringBuilder stringBuilder = $this$deleteAt.deleteCharAt(index);
        Intrinsics.checkNotNullExpressionValue(stringBuilder, "this.deleteCharAt(index)");
        return stringBuilder;
    }

    @SinceKotlin(version="1.4")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @InlineOnly
    private static final StringBuilder deleteRange(StringBuilder $this$deleteRange, int startIndex, int endIndex) {
        int $i$f$deleteRange = 0;
        StringBuilder stringBuilder = $this$deleteRange.delete(startIndex, endIndex);
        Intrinsics.checkNotNullExpressionValue(stringBuilder, "this.delete(startIndex, endIndex)");
        return stringBuilder;
    }

    @SinceKotlin(version="1.4")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @InlineOnly
    private static final void toCharArray(StringBuilder $this$toCharArray, char[] destination, int destinationOffset, int startIndex, int endIndex) {
        int $i$f$toCharArray = 0;
        $this$toCharArray.getChars(startIndex, endIndex, destination, destinationOffset);
    }

    static /* synthetic */ void toCharArray$default(StringBuilder $this$toCharArray, char[] destination, int destinationOffset, int startIndex, int endIndex, int n, Object object) {
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
        $this$toCharArray.getChars(startIndex, endIndex, destination, destinationOffset);
    }

    @SinceKotlin(version="1.4")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @InlineOnly
    private static final StringBuilder appendRange(StringBuilder $this$appendRange, char[] value, int startIndex, int endIndex) {
        int $i$f$appendRange = 0;
        StringBuilder stringBuilder = $this$appendRange.append(value, startIndex, endIndex - startIndex);
        Intrinsics.checkNotNullExpressionValue(stringBuilder, "this.append(value, start\u2026x, endIndex - startIndex)");
        return stringBuilder;
    }

    @SinceKotlin(version="1.4")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @InlineOnly
    private static final StringBuilder appendRange(StringBuilder $this$appendRange, CharSequence value, int startIndex, int endIndex) {
        int $i$f$appendRange = 0;
        StringBuilder stringBuilder = $this$appendRange.append(value, startIndex, endIndex);
        Intrinsics.checkNotNullExpressionValue(stringBuilder, "this.append(value, startIndex, endIndex)");
        return stringBuilder;
    }

    @SinceKotlin(version="1.4")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @InlineOnly
    private static final StringBuilder insertRange(StringBuilder $this$insertRange, int index, char[] value, int startIndex, int endIndex) {
        int $i$f$insertRange = 0;
        StringBuilder stringBuilder = $this$insertRange.insert(index, value, startIndex, endIndex - startIndex);
        Intrinsics.checkNotNullExpressionValue(stringBuilder, "this.insert(index, value\u2026x, endIndex - startIndex)");
        return stringBuilder;
    }

    @SinceKotlin(version="1.4")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @InlineOnly
    private static final StringBuilder insertRange(StringBuilder $this$insertRange, int index, CharSequence value, int startIndex, int endIndex) {
        int $i$f$insertRange = 0;
        StringBuilder stringBuilder = $this$insertRange.insert(index, value, startIndex, endIndex);
        Intrinsics.checkNotNullExpressionValue(stringBuilder, "this.insert(index, value, startIndex, endIndex)");
        return stringBuilder;
    }

    @SinceKotlin(version="1.4")
    @InlineOnly
    private static final StringBuilder appendLine(StringBuilder $this$appendLine, StringBuffer value) {
        int $i$f$appendLine = 0;
        StringBuilder stringBuilder = $this$appendLine.append(value);
        Intrinsics.checkNotNullExpressionValue(stringBuilder, "append(value)");
        StringBuilder stringBuilder2 = stringBuilder;
        boolean bl = false;
        StringBuilder stringBuilder3 = stringBuilder2.append('\n');
        Intrinsics.checkNotNullExpressionValue(stringBuilder3, "append('\\n')");
        return stringBuilder3;
    }

    @SinceKotlin(version="1.4")
    @InlineOnly
    private static final StringBuilder appendLine(StringBuilder $this$appendLine, StringBuilder value) {
        int $i$f$appendLine = 0;
        StringBuilder stringBuilder = $this$appendLine.append((CharSequence)value);
        Intrinsics.checkNotNullExpressionValue(stringBuilder, "append(value)");
        StringBuilder stringBuilder2 = stringBuilder;
        boolean bl = false;
        StringBuilder stringBuilder3 = stringBuilder2.append('\n');
        Intrinsics.checkNotNullExpressionValue(stringBuilder3, "append('\\n')");
        return stringBuilder3;
    }

    @SinceKotlin(version="1.4")
    @InlineOnly
    private static final StringBuilder appendLine(StringBuilder $this$appendLine, int value) {
        int $i$f$appendLine = 0;
        StringBuilder stringBuilder = $this$appendLine.append(value);
        Intrinsics.checkNotNullExpressionValue(stringBuilder, "append(value)");
        StringBuilder stringBuilder2 = stringBuilder;
        boolean bl = false;
        StringBuilder stringBuilder3 = stringBuilder2.append('\n');
        Intrinsics.checkNotNullExpressionValue(stringBuilder3, "append('\\n')");
        return stringBuilder3;
    }

    @SinceKotlin(version="1.4")
    @InlineOnly
    private static final StringBuilder appendLine(StringBuilder $this$appendLine, short value) {
        int $i$f$appendLine = 0;
        StringBuilder stringBuilder = $this$appendLine.append(value);
        Intrinsics.checkNotNullExpressionValue(stringBuilder, "append(value.toInt())");
        StringBuilder stringBuilder2 = stringBuilder;
        boolean bl = false;
        StringBuilder stringBuilder3 = stringBuilder2.append('\n');
        Intrinsics.checkNotNullExpressionValue(stringBuilder3, "append('\\n')");
        return stringBuilder3;
    }

    @SinceKotlin(version="1.4")
    @InlineOnly
    private static final StringBuilder appendLine(StringBuilder $this$appendLine, byte value) {
        int $i$f$appendLine = 0;
        StringBuilder stringBuilder = $this$appendLine.append(value);
        Intrinsics.checkNotNullExpressionValue(stringBuilder, "append(value.toInt())");
        StringBuilder stringBuilder2 = stringBuilder;
        boolean bl = false;
        StringBuilder stringBuilder3 = stringBuilder2.append('\n');
        Intrinsics.checkNotNullExpressionValue(stringBuilder3, "append('\\n')");
        return stringBuilder3;
    }

    @SinceKotlin(version="1.4")
    @InlineOnly
    private static final StringBuilder appendLine(StringBuilder $this$appendLine, long value) {
        int $i$f$appendLine = 0;
        StringBuilder stringBuilder = $this$appendLine.append(value);
        Intrinsics.checkNotNullExpressionValue(stringBuilder, "append(value)");
        StringBuilder stringBuilder2 = stringBuilder;
        boolean bl = false;
        StringBuilder stringBuilder3 = stringBuilder2.append('\n');
        Intrinsics.checkNotNullExpressionValue(stringBuilder3, "append('\\n')");
        return stringBuilder3;
    }

    @SinceKotlin(version="1.4")
    @InlineOnly
    private static final StringBuilder appendLine(StringBuilder $this$appendLine, float value) {
        int $i$f$appendLine = 0;
        StringBuilder stringBuilder = $this$appendLine.append(value);
        Intrinsics.checkNotNullExpressionValue(stringBuilder, "append(value)");
        StringBuilder stringBuilder2 = stringBuilder;
        boolean bl = false;
        StringBuilder stringBuilder3 = stringBuilder2.append('\n');
        Intrinsics.checkNotNullExpressionValue(stringBuilder3, "append('\\n')");
        return stringBuilder3;
    }

    @SinceKotlin(version="1.4")
    @InlineOnly
    private static final StringBuilder appendLine(StringBuilder $this$appendLine, double value) {
        int $i$f$appendLine = 0;
        StringBuilder stringBuilder = $this$appendLine.append(value);
        Intrinsics.checkNotNullExpressionValue(stringBuilder, "append(value)");
        StringBuilder stringBuilder2 = stringBuilder;
        boolean bl = false;
        StringBuilder stringBuilder3 = stringBuilder2.append('\n');
        Intrinsics.checkNotNullExpressionValue(stringBuilder3, "append('\\n')");
        return stringBuilder3;
    }

    @Deprecated(message="Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.", replaceWith=@ReplaceWith(imports={}, expression="appendLine()"), level=DeprecationLevel.WARNING)
    @NotNull
    public static final Appendable appendln(@NotNull Appendable $this$appendln) {
        Intrinsics.checkNotNullParameter($this$appendln, "$this$appendln");
        Appendable appendable = $this$appendln.append(SystemProperties.LINE_SEPARATOR);
        Intrinsics.checkNotNullExpressionValue(appendable, "append(SystemProperties.LINE_SEPARATOR)");
        return appendable;
    }

    @Deprecated(message="Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.", replaceWith=@ReplaceWith(imports={}, expression="appendLine(value)"), level=DeprecationLevel.WARNING)
    @InlineOnly
    private static final Appendable appendln(Appendable $this$appendln, CharSequence value) {
        int $i$f$appendln = 0;
        Appendable appendable = $this$appendln.append(value);
        Intrinsics.checkNotNullExpressionValue(appendable, "append(value)");
        return StringsKt.appendln(appendable);
    }

    @Deprecated(message="Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.", replaceWith=@ReplaceWith(imports={}, expression="appendLine(value)"), level=DeprecationLevel.WARNING)
    @InlineOnly
    private static final Appendable appendln(Appendable $this$appendln, char value) {
        int $i$f$appendln = 0;
        Appendable appendable = $this$appendln.append(value);
        Intrinsics.checkNotNullExpressionValue(appendable, "append(value)");
        return StringsKt.appendln(appendable);
    }

    @Deprecated(message="Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.", replaceWith=@ReplaceWith(imports={}, expression="appendLine()"), level=DeprecationLevel.WARNING)
    @NotNull
    public static final StringBuilder appendln(@NotNull StringBuilder $this$appendln) {
        Intrinsics.checkNotNullParameter($this$appendln, "$this$appendln");
        StringBuilder stringBuilder = $this$appendln.append(SystemProperties.LINE_SEPARATOR);
        Intrinsics.checkNotNullExpressionValue(stringBuilder, "append(SystemProperties.LINE_SEPARATOR)");
        return stringBuilder;
    }

    @Deprecated(message="Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.", replaceWith=@ReplaceWith(imports={}, expression="appendLine(value)"), level=DeprecationLevel.WARNING)
    @InlineOnly
    private static final StringBuilder appendln(StringBuilder $this$appendln, StringBuffer value) {
        int $i$f$appendln = 0;
        StringBuilder stringBuilder = $this$appendln.append(value);
        Intrinsics.checkNotNullExpressionValue(stringBuilder, "append(value)");
        return StringsKt.appendln(stringBuilder);
    }

    @Deprecated(message="Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.", replaceWith=@ReplaceWith(imports={}, expression="appendLine(value)"), level=DeprecationLevel.WARNING)
    @InlineOnly
    private static final StringBuilder appendln(StringBuilder $this$appendln, CharSequence value) {
        int $i$f$appendln = 0;
        StringBuilder stringBuilder = $this$appendln.append(value);
        Intrinsics.checkNotNullExpressionValue(stringBuilder, "append(value)");
        return StringsKt.appendln(stringBuilder);
    }

    @Deprecated(message="Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.", replaceWith=@ReplaceWith(imports={}, expression="appendLine(value)"), level=DeprecationLevel.WARNING)
    @InlineOnly
    private static final StringBuilder appendln(StringBuilder $this$appendln, String value) {
        int $i$f$appendln = 0;
        StringBuilder stringBuilder = $this$appendln.append(value);
        Intrinsics.checkNotNullExpressionValue(stringBuilder, "append(value)");
        return StringsKt.appendln(stringBuilder);
    }

    @Deprecated(message="Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.", replaceWith=@ReplaceWith(imports={}, expression="appendLine(value)"), level=DeprecationLevel.WARNING)
    @InlineOnly
    private static final StringBuilder appendln(StringBuilder $this$appendln, Object value) {
        int $i$f$appendln = 0;
        StringBuilder stringBuilder = $this$appendln.append(value);
        Intrinsics.checkNotNullExpressionValue(stringBuilder, "append(value)");
        return StringsKt.appendln(stringBuilder);
    }

    @Deprecated(message="Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.", replaceWith=@ReplaceWith(imports={}, expression="appendLine(value)"), level=DeprecationLevel.WARNING)
    @InlineOnly
    private static final StringBuilder appendln(StringBuilder $this$appendln, StringBuilder value) {
        int $i$f$appendln = 0;
        StringBuilder stringBuilder = $this$appendln.append((CharSequence)value);
        Intrinsics.checkNotNullExpressionValue(stringBuilder, "append(value)");
        return StringsKt.appendln(stringBuilder);
    }

    @Deprecated(message="Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.", replaceWith=@ReplaceWith(imports={}, expression="appendLine(value)"), level=DeprecationLevel.WARNING)
    @InlineOnly
    private static final StringBuilder appendln(StringBuilder $this$appendln, char[] value) {
        int $i$f$appendln = 0;
        StringBuilder stringBuilder = $this$appendln.append(value);
        Intrinsics.checkNotNullExpressionValue(stringBuilder, "append(value)");
        return StringsKt.appendln(stringBuilder);
    }

    @Deprecated(message="Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.", replaceWith=@ReplaceWith(imports={}, expression="appendLine(value)"), level=DeprecationLevel.WARNING)
    @InlineOnly
    private static final StringBuilder appendln(StringBuilder $this$appendln, char value) {
        int $i$f$appendln = 0;
        StringBuilder stringBuilder = $this$appendln.append(value);
        Intrinsics.checkNotNullExpressionValue(stringBuilder, "append(value)");
        return StringsKt.appendln(stringBuilder);
    }

    @Deprecated(message="Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.", replaceWith=@ReplaceWith(imports={}, expression="appendLine(value)"), level=DeprecationLevel.WARNING)
    @InlineOnly
    private static final StringBuilder appendln(StringBuilder $this$appendln, boolean value) {
        int $i$f$appendln = 0;
        StringBuilder stringBuilder = $this$appendln.append(value);
        Intrinsics.checkNotNullExpressionValue(stringBuilder, "append(value)");
        return StringsKt.appendln(stringBuilder);
    }

    @Deprecated(message="Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.", replaceWith=@ReplaceWith(imports={}, expression="appendLine(value)"), level=DeprecationLevel.WARNING)
    @InlineOnly
    private static final StringBuilder appendln(StringBuilder $this$appendln, int value) {
        int $i$f$appendln = 0;
        StringBuilder stringBuilder = $this$appendln.append(value);
        Intrinsics.checkNotNullExpressionValue(stringBuilder, "append(value)");
        return StringsKt.appendln(stringBuilder);
    }

    @Deprecated(message="Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.", replaceWith=@ReplaceWith(imports={}, expression="appendLine(value)"), level=DeprecationLevel.WARNING)
    @InlineOnly
    private static final StringBuilder appendln(StringBuilder $this$appendln, short value) {
        int $i$f$appendln = 0;
        StringBuilder stringBuilder = $this$appendln.append(value);
        Intrinsics.checkNotNullExpressionValue(stringBuilder, "append(value.toInt())");
        return StringsKt.appendln(stringBuilder);
    }

    @Deprecated(message="Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.", replaceWith=@ReplaceWith(imports={}, expression="appendLine(value)"), level=DeprecationLevel.WARNING)
    @InlineOnly
    private static final StringBuilder appendln(StringBuilder $this$appendln, byte value) {
        int $i$f$appendln = 0;
        StringBuilder stringBuilder = $this$appendln.append(value);
        Intrinsics.checkNotNullExpressionValue(stringBuilder, "append(value.toInt())");
        return StringsKt.appendln(stringBuilder);
    }

    @Deprecated(message="Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.", replaceWith=@ReplaceWith(imports={}, expression="appendLine(value)"), level=DeprecationLevel.WARNING)
    @InlineOnly
    private static final StringBuilder appendln(StringBuilder $this$appendln, long value) {
        int $i$f$appendln = 0;
        StringBuilder stringBuilder = $this$appendln.append(value);
        Intrinsics.checkNotNullExpressionValue(stringBuilder, "append(value)");
        return StringsKt.appendln(stringBuilder);
    }

    @Deprecated(message="Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.", replaceWith=@ReplaceWith(imports={}, expression="appendLine(value)"), level=DeprecationLevel.WARNING)
    @InlineOnly
    private static final StringBuilder appendln(StringBuilder $this$appendln, float value) {
        int $i$f$appendln = 0;
        StringBuilder stringBuilder = $this$appendln.append(value);
        Intrinsics.checkNotNullExpressionValue(stringBuilder, "append(value)");
        return StringsKt.appendln(stringBuilder);
    }

    @Deprecated(message="Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.", replaceWith=@ReplaceWith(imports={}, expression="appendLine(value)"), level=DeprecationLevel.WARNING)
    @InlineOnly
    private static final StringBuilder appendln(StringBuilder $this$appendln, double value) {
        int $i$f$appendln = 0;
        StringBuilder stringBuilder = $this$appendln.append(value);
        Intrinsics.checkNotNullExpressionValue(stringBuilder, "append(value)");
        return StringsKt.appendln(stringBuilder);
    }
}

