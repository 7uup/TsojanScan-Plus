/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.text;

import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.text.CharCategory;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\f\n\u0002\b \b\u0086\u0001\u0018\u0000 -2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001-B\u0017\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0011\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0086\u0002R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nj\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001bj\u0002\b\u001cj\u0002\b\u001dj\u0002\b\u001ej\u0002\b\u001fj\u0002\b j\u0002\b!j\u0002\b\"j\u0002\b#j\u0002\b$j\u0002\b%j\u0002\b&j\u0002\b'j\u0002\b(j\u0002\b)j\u0002\b*j\u0002\b+j\u0002\b,\u00a8\u0006."}, d2={"Lkotlin/text/CharCategory;", "", "value", "", "code", "", "(Ljava/lang/String;IILjava/lang/String;)V", "getCode", "()Ljava/lang/String;", "getValue", "()I", "contains", "", "char", "", "UNASSIGNED", "UPPERCASE_LETTER", "LOWERCASE_LETTER", "TITLECASE_LETTER", "MODIFIER_LETTER", "OTHER_LETTER", "NON_SPACING_MARK", "ENCLOSING_MARK", "COMBINING_SPACING_MARK", "DECIMAL_DIGIT_NUMBER", "LETTER_NUMBER", "OTHER_NUMBER", "SPACE_SEPARATOR", "LINE_SEPARATOR", "PARAGRAPH_SEPARATOR", "CONTROL", "FORMAT", "PRIVATE_USE", "SURROGATE", "DASH_PUNCTUATION", "START_PUNCTUATION", "END_PUNCTUATION", "CONNECTOR_PUNCTUATION", "OTHER_PUNCTUATION", "MATH_SYMBOL", "CURRENCY_SYMBOL", "MODIFIER_SYMBOL", "OTHER_SYMBOL", "INITIAL_QUOTE_PUNCTUATION", "FINAL_QUOTE_PUNCTUATION", "Companion", "kotlin-stdlib"})
public final class CharCategory
extends Enum<CharCategory> {
    public static final /* enum */ CharCategory UNASSIGNED;
    public static final /* enum */ CharCategory UPPERCASE_LETTER;
    public static final /* enum */ CharCategory LOWERCASE_LETTER;
    public static final /* enum */ CharCategory TITLECASE_LETTER;
    public static final /* enum */ CharCategory MODIFIER_LETTER;
    public static final /* enum */ CharCategory OTHER_LETTER;
    public static final /* enum */ CharCategory NON_SPACING_MARK;
    public static final /* enum */ CharCategory ENCLOSING_MARK;
    public static final /* enum */ CharCategory COMBINING_SPACING_MARK;
    public static final /* enum */ CharCategory DECIMAL_DIGIT_NUMBER;
    public static final /* enum */ CharCategory LETTER_NUMBER;
    public static final /* enum */ CharCategory OTHER_NUMBER;
    public static final /* enum */ CharCategory SPACE_SEPARATOR;
    public static final /* enum */ CharCategory LINE_SEPARATOR;
    public static final /* enum */ CharCategory PARAGRAPH_SEPARATOR;
    public static final /* enum */ CharCategory CONTROL;
    public static final /* enum */ CharCategory FORMAT;
    public static final /* enum */ CharCategory PRIVATE_USE;
    public static final /* enum */ CharCategory SURROGATE;
    public static final /* enum */ CharCategory DASH_PUNCTUATION;
    public static final /* enum */ CharCategory START_PUNCTUATION;
    public static final /* enum */ CharCategory END_PUNCTUATION;
    public static final /* enum */ CharCategory CONNECTOR_PUNCTUATION;
    public static final /* enum */ CharCategory OTHER_PUNCTUATION;
    public static final /* enum */ CharCategory MATH_SYMBOL;
    public static final /* enum */ CharCategory CURRENCY_SYMBOL;
    public static final /* enum */ CharCategory MODIFIER_SYMBOL;
    public static final /* enum */ CharCategory OTHER_SYMBOL;
    public static final /* enum */ CharCategory INITIAL_QUOTE_PUNCTUATION;
    public static final /* enum */ CharCategory FINAL_QUOTE_PUNCTUATION;
    private static final /* synthetic */ CharCategory[] $VALUES;
    private final int value;
    @NotNull
    private final String code;
    private static final Lazy categoryMap$delegate;
    public static final Companion Companion;

    static {
        CharCategory[] charCategoryArray = new CharCategory[30];
        CharCategory[] charCategoryArray2 = charCategoryArray;
        charCategoryArray[0] = UNASSIGNED = new CharCategory(0, "Cn");
        charCategoryArray[1] = UPPERCASE_LETTER = new CharCategory(1, "Lu");
        charCategoryArray[2] = LOWERCASE_LETTER = new CharCategory(2, "Ll");
        charCategoryArray[3] = TITLECASE_LETTER = new CharCategory(3, "Lt");
        charCategoryArray[4] = MODIFIER_LETTER = new CharCategory(4, "Lm");
        charCategoryArray[5] = OTHER_LETTER = new CharCategory(5, "Lo");
        charCategoryArray[6] = NON_SPACING_MARK = new CharCategory(6, "Mn");
        charCategoryArray[7] = ENCLOSING_MARK = new CharCategory(7, "Me");
        charCategoryArray[8] = COMBINING_SPACING_MARK = new CharCategory(8, "Mc");
        charCategoryArray[9] = DECIMAL_DIGIT_NUMBER = new CharCategory(9, "Nd");
        charCategoryArray[10] = LETTER_NUMBER = new CharCategory(10, "Nl");
        charCategoryArray[11] = OTHER_NUMBER = new CharCategory(11, "No");
        charCategoryArray[12] = SPACE_SEPARATOR = new CharCategory(12, "Zs");
        charCategoryArray[13] = LINE_SEPARATOR = new CharCategory(13, "Zl");
        charCategoryArray[14] = PARAGRAPH_SEPARATOR = new CharCategory(14, "Zp");
        charCategoryArray[15] = CONTROL = new CharCategory(15, "Cc");
        charCategoryArray[16] = FORMAT = new CharCategory(16, "Cf");
        charCategoryArray[17] = PRIVATE_USE = new CharCategory(18, "Co");
        charCategoryArray[18] = SURROGATE = new CharCategory(19, "Cs");
        charCategoryArray[19] = DASH_PUNCTUATION = new CharCategory(20, "Pd");
        charCategoryArray[20] = START_PUNCTUATION = new CharCategory(21, "Ps");
        charCategoryArray[21] = END_PUNCTUATION = new CharCategory(22, "Pe");
        charCategoryArray[22] = CONNECTOR_PUNCTUATION = new CharCategory(23, "Pc");
        charCategoryArray[23] = OTHER_PUNCTUATION = new CharCategory(24, "Po");
        charCategoryArray[24] = MATH_SYMBOL = new CharCategory(25, "Sm");
        charCategoryArray[25] = CURRENCY_SYMBOL = new CharCategory(26, "Sc");
        charCategoryArray[26] = MODIFIER_SYMBOL = new CharCategory(27, "Sk");
        charCategoryArray[27] = OTHER_SYMBOL = new CharCategory(28, "So");
        charCategoryArray[28] = INITIAL_QUOTE_PUNCTUATION = new CharCategory(29, "Pi");
        charCategoryArray[29] = FINAL_QUOTE_PUNCTUATION = new CharCategory(30, "Pf");
        $VALUES = charCategoryArray;
        Companion = new Companion(null);
        categoryMap$delegate = LazyKt.lazy(Companion.categoryMap.2.INSTANCE);
    }

    public final boolean contains(char c) {
        return Character.getType(c) == this.value;
    }

    public final int getValue() {
        return this.value;
    }

    @NotNull
    public final String getCode() {
        return this.code;
    }

    private CharCategory(int value, String code) {
        this.value = value;
        this.code = code;
    }

    public static CharCategory[] values() {
        return (CharCategory[])$VALUES.clone();
    }

    public static CharCategory valueOf(String string) {
        return Enum.valueOf(CharCategory.class, string);
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u0005R'\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00048BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\b\u00a8\u0006\r"}, d2={"Lkotlin/text/CharCategory$Companion;", "", "()V", "categoryMap", "", "", "Lkotlin/text/CharCategory;", "getCategoryMap", "()Ljava/util/Map;", "categoryMap$delegate", "Lkotlin/Lazy;", "valueOf", "category", "kotlin-stdlib"})
    public static final class Companion {
        private final Map<Integer, CharCategory> getCategoryMap() {
            Lazy lazy = categoryMap$delegate;
            Companion companion = Companion;
            Object var3_3 = null;
            boolean bl = false;
            return (Map)lazy.getValue();
        }

        @NotNull
        public final CharCategory valueOf(int category) {
            CharCategory charCategory = this.getCategoryMap().get(category);
            if (charCategory == null) {
                throw (Throwable)new IllegalArgumentException("Category #" + category + " is not defined.");
            }
            return charCategory;
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

