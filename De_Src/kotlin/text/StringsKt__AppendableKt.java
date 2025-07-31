/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.text;

import kotlin.ExperimentalStdlibApi;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.WasExperimental;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=5, xi=1, d1={"\u0000:\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\r\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\f\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\u001a5\u0010\u0000\u001a\u0002H\u0001\"\f\b\u0000\u0010\u0001*\u00060\u0002j\u0002`\u0003*\u0002H\u00012\u0016\u0010\u0004\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00060\u0005\"\u0004\u0018\u00010\u0006\u00a2\u0006\u0002\u0010\u0007\u001a9\u0010\b\u001a\u00020\t\"\u0004\b\u0000\u0010\u0001*\u00060\u0002j\u0002`\u00032\u0006\u0010\n\u001a\u0002H\u00012\u0014\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u00020\u0006\u0018\u00010\fH\u0000\u00a2\u0006\u0002\u0010\r\u001a\u0015\u0010\u000e\u001a\u00060\u0002j\u0002`\u0003*\u00060\u0002j\u0002`\u0003H\u0087\b\u001a\u001d\u0010\u000e\u001a\u00060\u0002j\u0002`\u0003*\u00060\u0002j\u0002`\u00032\u0006\u0010\u0004\u001a\u00020\u000fH\u0087\b\u001a\u001f\u0010\u000e\u001a\u00060\u0002j\u0002`\u0003*\u00060\u0002j\u0002`\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0006H\u0087\b\u001a7\u0010\u0010\u001a\u0002H\u0001\"\f\b\u0000\u0010\u0001*\u00060\u0002j\u0002`\u0003*\u0002H\u00012\u0006\u0010\u0004\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0012H\u0007\u00a2\u0006\u0002\u0010\u0014\u00a8\u0006\u0015"}, d2={"append", "T", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "value", "", "", "(Ljava/lang/Appendable;[Ljava/lang/CharSequence;)Ljava/lang/Appendable;", "appendElement", "", "element", "transform", "Lkotlin/Function1;", "(Ljava/lang/Appendable;Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)V", "appendLine", "", "appendRange", "startIndex", "", "endIndex", "(Ljava/lang/Appendable;Ljava/lang/CharSequence;II)Ljava/lang/Appendable;", "kotlin-stdlib"}, xs="kotlin/text/StringsKt")
class StringsKt__AppendableKt {
    @SinceKotlin(version="1.4")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @NotNull
    public static final <T extends Appendable> T appendRange(@NotNull T $this$appendRange, @NotNull CharSequence value, int startIndex, int endIndex) {
        Intrinsics.checkNotNullParameter($this$appendRange, "$this$appendRange");
        Intrinsics.checkNotNullParameter(value, "value");
        Appendable appendable = $this$appendRange.append(value, startIndex, endIndex);
        if (appendable == null) {
            throw new NullPointerException("null cannot be cast to non-null type T");
        }
        return (T)appendable;
    }

    @NotNull
    public static final <T extends Appendable> T append(@NotNull T $this$append, @NotNull CharSequence ... value) {
        Intrinsics.checkNotNullParameter($this$append, "$this$append");
        Intrinsics.checkNotNullParameter(value, "value");
        for (CharSequence item : value) {
            $this$append.append(item);
        }
        return $this$append;
    }

    @SinceKotlin(version="1.4")
    @InlineOnly
    private static final Appendable appendLine(Appendable $this$appendLine) {
        int $i$f$appendLine = 0;
        Appendable appendable = $this$appendLine.append('\n');
        Intrinsics.checkNotNullExpressionValue(appendable, "append('\\n')");
        return appendable;
    }

    @SinceKotlin(version="1.4")
    @InlineOnly
    private static final Appendable appendLine(Appendable $this$appendLine, CharSequence value) {
        int $i$f$appendLine = 0;
        Appendable appendable = $this$appendLine.append(value);
        Intrinsics.checkNotNullExpressionValue(appendable, "append(value)");
        Appendable appendable2 = appendable;
        boolean bl = false;
        Appendable appendable3 = appendable2.append('\n');
        Intrinsics.checkNotNullExpressionValue(appendable3, "append('\\n')");
        return appendable3;
    }

    @SinceKotlin(version="1.4")
    @InlineOnly
    private static final Appendable appendLine(Appendable $this$appendLine, char value) {
        int $i$f$appendLine = 0;
        Appendable appendable = $this$appendLine.append(value);
        Intrinsics.checkNotNullExpressionValue(appendable, "append(value)");
        Appendable appendable2 = appendable;
        boolean bl = false;
        Appendable appendable3 = appendable2.append('\n');
        Intrinsics.checkNotNullExpressionValue(appendable3, "append('\\n')");
        return appendable3;
    }

    public static final <T> void appendElement(@NotNull Appendable $this$appendElement, T element, @Nullable Function1<? super T, ? extends CharSequence> transform) {
        Intrinsics.checkNotNullParameter($this$appendElement, "$this$appendElement");
        if (transform != null) {
            $this$appendElement.append(transform.invoke(element));
        } else {
            T t = element;
            if (t != null ? t instanceof CharSequence : true) {
                $this$appendElement.append((CharSequence)element);
            } else if (element instanceof Character) {
                $this$appendElement.append(((Character)element).charValue());
            } else {
                $this$appendElement.append(String.valueOf(element));
            }
        }
    }
}

