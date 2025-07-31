/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.text;

import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.internal.InlineOnly;
import kotlin.ranges.IntRange;
import kotlin.text.CharCategory;
import kotlin.text.CharDirectionality;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=5, xi=1, d1={"\u0000&\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\f\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0011\u001a\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0001\u001a\u0018\u0010\f\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\nH\u0000\u001a\r\u0010\u000e\u001a\u00020\u000f*\u00020\u0002H\u0087\b\u001a\r\u0010\u0010\u001a\u00020\u000f*\u00020\u0002H\u0087\b\u001a\r\u0010\u0011\u001a\u00020\u000f*\u00020\u0002H\u0087\b\u001a\r\u0010\u0012\u001a\u00020\u000f*\u00020\u0002H\u0087\b\u001a\r\u0010\u0013\u001a\u00020\u000f*\u00020\u0002H\u0087\b\u001a\r\u0010\u0014\u001a\u00020\u000f*\u00020\u0002H\u0087\b\u001a\r\u0010\u0015\u001a\u00020\u000f*\u00020\u0002H\u0087\b\u001a\r\u0010\u0016\u001a\u00020\u000f*\u00020\u0002H\u0087\b\u001a\r\u0010\u0017\u001a\u00020\u000f*\u00020\u0002H\u0087\b\u001a\r\u0010\u0018\u001a\u00020\u000f*\u00020\u0002H\u0087\b\u001a\r\u0010\u0019\u001a\u00020\u000f*\u00020\u0002H\u0087\b\u001a\r\u0010\u001a\u001a\u00020\u000f*\u00020\u0002H\u0087\b\u001a\r\u0010\u001b\u001a\u00020\u000f*\u00020\u0002H\u0087\b\u001a\n\u0010\u001c\u001a\u00020\u000f*\u00020\u0002\u001a\r\u0010\u001d\u001a\u00020\u0002*\u00020\u0002H\u0087\b\u001a\r\u0010\u001e\u001a\u00020\u0002*\u00020\u0002H\u0087\b\u001a\r\u0010\u001f\u001a\u00020\u0002*\u00020\u0002H\u0087\b\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F\u00a2\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0015\u0010\u0005\u001a\u00020\u0006*\u00020\u00028F\u00a2\u0006\u0006\u001a\u0004\b\u0007\u0010\b\u00a8\u0006 "}, d2={"category", "Lkotlin/text/CharCategory;", "", "getCategory", "(C)Lkotlin/text/CharCategory;", "directionality", "Lkotlin/text/CharDirectionality;", "getDirectionality", "(C)Lkotlin/text/CharDirectionality;", "checkRadix", "", "radix", "digitOf", "char", "isDefined", "", "isDigit", "isHighSurrogate", "isISOControl", "isIdentifierIgnorable", "isJavaIdentifierPart", "isJavaIdentifierStart", "isLetter", "isLetterOrDigit", "isLowSurrogate", "isLowerCase", "isTitleCase", "isUpperCase", "isWhitespace", "toLowerCase", "toTitleCase", "toUpperCase", "kotlin-stdlib"}, xs="kotlin/text/CharsKt")
class CharsKt__CharJVMKt {
    @InlineOnly
    private static final boolean isDefined(char $this$isDefined) {
        int $i$f$isDefined = 0;
        return Character.isDefined($this$isDefined);
    }

    @InlineOnly
    private static final boolean isLetter(char $this$isLetter) {
        int $i$f$isLetter = 0;
        return Character.isLetter($this$isLetter);
    }

    @InlineOnly
    private static final boolean isLetterOrDigit(char $this$isLetterOrDigit) {
        int $i$f$isLetterOrDigit = 0;
        return Character.isLetterOrDigit($this$isLetterOrDigit);
    }

    @InlineOnly
    private static final boolean isDigit(char $this$isDigit) {
        int $i$f$isDigit = 0;
        return Character.isDigit($this$isDigit);
    }

    @InlineOnly
    private static final boolean isIdentifierIgnorable(char $this$isIdentifierIgnorable) {
        int $i$f$isIdentifierIgnorable = 0;
        return Character.isIdentifierIgnorable($this$isIdentifierIgnorable);
    }

    @InlineOnly
    private static final boolean isISOControl(char $this$isISOControl) {
        int $i$f$isISOControl = 0;
        return Character.isISOControl($this$isISOControl);
    }

    @InlineOnly
    private static final boolean isJavaIdentifierPart(char $this$isJavaIdentifierPart) {
        int $i$f$isJavaIdentifierPart = 0;
        return Character.isJavaIdentifierPart($this$isJavaIdentifierPart);
    }

    @InlineOnly
    private static final boolean isJavaIdentifierStart(char $this$isJavaIdentifierStart) {
        int $i$f$isJavaIdentifierStart = 0;
        return Character.isJavaIdentifierStart($this$isJavaIdentifierStart);
    }

    public static final boolean isWhitespace(char $this$isWhitespace) {
        return Character.isWhitespace($this$isWhitespace) || Character.isSpaceChar($this$isWhitespace);
    }

    @InlineOnly
    private static final boolean isUpperCase(char $this$isUpperCase) {
        int $i$f$isUpperCase = 0;
        return Character.isUpperCase($this$isUpperCase);
    }

    @InlineOnly
    private static final boolean isLowerCase(char $this$isLowerCase) {
        int $i$f$isLowerCase = 0;
        return Character.isLowerCase($this$isLowerCase);
    }

    @InlineOnly
    private static final char toUpperCase(char $this$toUpperCase) {
        int $i$f$toUpperCase = 0;
        return Character.toUpperCase($this$toUpperCase);
    }

    @InlineOnly
    private static final char toLowerCase(char $this$toLowerCase) {
        int $i$f$toLowerCase = 0;
        return Character.toLowerCase($this$toLowerCase);
    }

    @InlineOnly
    private static final boolean isTitleCase(char $this$isTitleCase) {
        int $i$f$isTitleCase = 0;
        return Character.isTitleCase($this$isTitleCase);
    }

    @InlineOnly
    private static final char toTitleCase(char $this$toTitleCase) {
        int $i$f$toTitleCase = 0;
        return Character.toTitleCase($this$toTitleCase);
    }

    @NotNull
    public static final CharCategory getCategory(char $this$category) {
        return CharCategory.Companion.valueOf(Character.getType($this$category));
    }

    @NotNull
    public static final CharDirectionality getDirectionality(char $this$directionality) {
        return CharDirectionality.Companion.valueOf(Character.getDirectionality($this$directionality));
    }

    @InlineOnly
    private static final boolean isHighSurrogate(char $this$isHighSurrogate) {
        int $i$f$isHighSurrogate = 0;
        return Character.isHighSurrogate($this$isHighSurrogate);
    }

    @InlineOnly
    private static final boolean isLowSurrogate(char $this$isLowSurrogate) {
        int $i$f$isLowSurrogate = 0;
        return Character.isLowSurrogate($this$isLowSurrogate);
    }

    public static final int digitOf(char c, int radix) {
        return Character.digit((int)c, radix);
    }

    @PublishedApi
    public static final int checkRadix(int radix) {
        int n = radix;
        if (2 > n || 36 < n) {
            n = 2;
            throw (Throwable)new IllegalArgumentException("radix " + radix + " was not in valid range " + new IntRange(n, 36));
        }
        return radix;
    }
}

