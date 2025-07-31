/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.text;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.internal.PlatformImplementationsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;
import kotlin.text.CharsKt;
import kotlin.text.StringsKt;
import kotlin.text.StringsKt__AppendableKt;
import kotlin.text.StringsKt__IndentKt;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=5, xi=1, d1={"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u000b\u001a!\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0002H\u0002\u00a2\u0006\u0002\b\u0004\u001a\u0011\u0010\u0005\u001a\u00020\u0006*\u00020\u0002H\u0002\u00a2\u0006\u0002\b\u0007\u001a\u0014\u0010\b\u001a\u00020\u0002*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u001aJ\u0010\t\u001a\u00020\u0002*\b\u0012\u0004\u0012\u00020\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00062\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u00012\u0014\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001H\u0082\b\u00a2\u0006\u0002\b\u000e\u001a\u0014\u0010\u000f\u001a\u00020\u0002*\u00020\u00022\b\b\u0002\u0010\u0010\u001a\u00020\u0002\u001a\u001e\u0010\u0011\u001a\u00020\u0002*\u00020\u00022\b\b\u0002\u0010\u0010\u001a\u00020\u00022\b\b\u0002\u0010\u0012\u001a\u00020\u0002\u001a\n\u0010\u0013\u001a\u00020\u0002*\u00020\u0002\u001a\u0014\u0010\u0014\u001a\u00020\u0002*\u00020\u00022\b\b\u0002\u0010\u0012\u001a\u00020\u0002\u00a8\u0006\u0015"}, d2={"getIndentFunction", "Lkotlin/Function1;", "", "indent", "getIndentFunction$StringsKt__IndentKt", "indentWidth", "", "indentWidth$StringsKt__IndentKt", "prependIndent", "reindent", "", "resultSizeEstimate", "indentAddFunction", "indentCutFunction", "reindent$StringsKt__IndentKt", "replaceIndent", "newIndent", "replaceIndentByMargin", "marginPrefix", "trimIndent", "trimMargin", "kotlin-stdlib"}, xs="kotlin/text/StringsKt")
class StringsKt__IndentKt
extends StringsKt__AppendableKt {
    @NotNull
    public static final String trimMargin(@NotNull String $this$trimMargin, @NotNull String marginPrefix) {
        Intrinsics.checkNotNullParameter($this$trimMargin, "$this$trimMargin");
        Intrinsics.checkNotNullParameter(marginPrefix, "marginPrefix");
        return StringsKt.replaceIndentByMargin($this$trimMargin, "", marginPrefix);
    }

    public static /* synthetic */ String trimMargin$default(String string, String string2, int n, Object object) {
        if ((n & 1) != 0) {
            string2 = "|";
        }
        return StringsKt.trimMargin(string, string2);
    }

    /*
     * WARNING - void declaration
     */
    @NotNull
    public static final String replaceIndentByMargin(@NotNull String $this$replaceIndentByMargin, @NotNull String newIndent, @NotNull String marginPrefix) {
        void resultSizeEstimate$iv;
        void $this$mapIndexedNotNullTo$iv$iv$iv;
        void $this$reindent$iv;
        List<String> lines;
        Intrinsics.checkNotNullParameter($this$replaceIndentByMargin, "$this$replaceIndentByMargin");
        Intrinsics.checkNotNullParameter(newIndent, "newIndent");
        Intrinsics.checkNotNullParameter(marginPrefix, "marginPrefix");
        CharSequence charSequence = marginPrefix;
        boolean bl = false;
        boolean bl2 = !StringsKt.isBlank(charSequence);
        bl = false;
        int n = 0;
        if (!bl2) {
            boolean bl3 = false;
            String string = "marginPrefix must be non-blank string.";
            throw (Throwable)new IllegalArgumentException(string.toString());
        }
        List<String> list = lines = StringsKt.lines($this$replaceIndentByMargin);
        n = $this$replaceIndentByMargin.length() + newIndent.length() * lines.size();
        Function1<String, String> indentAddFunction$iv = StringsKt__IndentKt.getIndentFunction$StringsKt__IndentKt(newIndent);
        boolean $i$f$reindent$StringsKt__IndentKt = false;
        int lastIndex$iv = CollectionsKt.getLastIndex($this$reindent$iv);
        Iterable $this$mapIndexedNotNull$iv$iv = (Iterable)$this$reindent$iv;
        boolean $i$f$mapIndexedNotNull = false;
        Iterable iterable = $this$mapIndexedNotNull$iv$iv;
        Collection destination$iv$iv$iv = new ArrayList();
        boolean $i$f$mapIndexedNotNullTo = false;
        void $this$forEachIndexed$iv$iv$iv$iv = $this$mapIndexedNotNullTo$iv$iv$iv;
        boolean $i$f$forEachIndexed = false;
        int index$iv$iv$iv$iv = 0;
        for (Object item$iv$iv$iv$iv : $this$forEachIndexed$iv$iv$iv$iv) {
            String string;
            block16: {
                void value$iv;
                block17: {
                    String string2;
                    int n2;
                    int n3;
                    void line;
                    block14: {
                        block15: {
                            void element$iv$iv$iv;
                            int n4 = index$iv$iv$iv$iv++;
                            boolean bl4 = false;
                            if (n4 < 0) {
                                CollectionsKt.throwIndexOverflow();
                            }
                            Object t = item$iv$iv$iv$iv;
                            int index$iv$iv$iv = n4;
                            boolean bl5 = false;
                            String string3 = (String)element$iv$iv$iv;
                            int index$iv = index$iv$iv$iv;
                            boolean bl6 = false;
                            if (index$iv != 0 && index$iv != lastIndex$iv || !StringsKt.isBlank((CharSequence)value$iv)) break block15;
                            string = null;
                            break block16;
                        }
                        line = value$iv;
                        boolean bl7 = false;
                        CharSequence $this$indexOfFirst$iv = (CharSequence)line;
                        boolean $i$f$indexOfFirst = false;
                        n3 = 0;
                        int n5 = $this$indexOfFirst$iv.length();
                        while (n3 < n5) {
                            void index$iv;
                            char it = $this$indexOfFirst$iv.charAt((int)index$iv);
                            boolean bl8 = false;
                            if (!CharsKt.isWhitespace(it)) {
                                n2 = index$iv;
                                break block14;
                            }
                            ++index$iv;
                        }
                        n2 = -1;
                    }
                    int firstNonWhitespaceIndex = n2;
                    if (firstNonWhitespaceIndex == -1) {
                        string2 = null;
                    } else if (StringsKt.startsWith$default((String)line, marginPrefix, firstNonWhitespaceIndex, false, 4, null)) {
                        void var29_34 = line;
                        int n6 = firstNonWhitespaceIndex + marginPrefix.length();
                        n3 = 0;
                        void v3 = var29_34;
                        if (v3 == null) {
                            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                        }
                        String string4 = v3.substring(n6);
                        string2 = string4;
                        Intrinsics.checkNotNullExpressionValue(string4, "(this as java.lang.String).substring(startIndex)");
                    } else {
                        string2 = null;
                    }
                    string = string2;
                    if (string2 == null) break block17;
                    String string5 = string;
                    boolean bl9 = false;
                    boolean bl10 = false;
                    string = indentAddFunction$iv.invoke(string5);
                    if (string != null) break block16;
                }
                string = value$iv;
            }
            if (string == null) continue;
            String string6 = string;
            boolean bl11 = false;
            boolean bl12 = false;
            String it$iv$iv$iv = string6;
            boolean bl13 = false;
            destination$iv$iv$iv.add(it$iv$iv$iv);
        }
        String string = ((StringBuilder)CollectionsKt.joinTo$default((List)destination$iv$iv$iv, new StringBuilder((int)resultSizeEstimate$iv), "\n", null, null, 0, null, null, 124, null)).toString();
        Intrinsics.checkNotNullExpressionValue(string, "mapIndexedNotNull { inde\u2026\"\\n\")\n        .toString()");
        return string;
    }

    public static /* synthetic */ String replaceIndentByMargin$default(String string, String string2, String string3, int n, Object object) {
        if ((n & 1) != 0) {
            string2 = "";
        }
        if ((n & 2) != 0) {
            string3 = "|";
        }
        return StringsKt.replaceIndentByMargin(string, string2, string3);
    }

    @NotNull
    public static final String trimIndent(@NotNull String $this$trimIndent) {
        Intrinsics.checkNotNullParameter($this$trimIndent, "$this$trimIndent");
        return StringsKt.replaceIndent($this$trimIndent, "");
    }

    /*
     * WARNING - void declaration
     */
    @NotNull
    public static final String replaceIndent(@NotNull String $this$replaceIndent, @NotNull String newIndent) {
        void resultSizeEstimate$iv;
        void $this$mapIndexedNotNullTo$iv$iv$iv;
        void $this$reindent$iv;
        void $this$mapTo$iv$iv;
        boolean bl;
        Object p1;
        List<String> $this$filterTo$iv$iv;
        Intrinsics.checkNotNullParameter($this$replaceIndent, "$this$replaceIndent");
        Intrinsics.checkNotNullParameter(newIndent, "newIndent");
        List<String> lines = StringsKt.lines($this$replaceIndent);
        Iterable $this$filter$iv = lines;
        boolean $i$f$filter = false;
        Iterable iterable = $this$filter$iv;
        Collection destination$iv$iv = new ArrayList();
        boolean $i$f$filterTo = false;
        for (Object t : $this$filterTo$iv$iv) {
            p1 = (String)t;
            bl = false;
            CharSequence charSequence = (CharSequence)p1;
            boolean bl2 = false;
            if (!(!StringsKt.isBlank(charSequence))) continue;
            destination$iv$iv.add(t);
        }
        List<String> $this$map$iv = (List)destination$iv$iv;
        int $i$f$map = 0;
        $this$filterTo$iv$iv = $this$map$iv;
        destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        boolean $i$f$mapTo = false;
        for (Object t : $this$mapTo$iv$iv) {
            p1 = (String)t;
            Collection collection = destination$iv$iv;
            bl = false;
            Integer n = StringsKt__IndentKt.indentWidth$StringsKt__IndentKt((String)p1);
            collection.add(n);
        }
        Integer n = (Integer)CollectionsKt.minOrNull((List)destination$iv$iv);
        int minCommonIndent = n != null ? n : 0;
        $this$map$iv = lines;
        $i$f$map = $this$replaceIndent.length() + newIndent.length() * lines.size();
        Function1<String, String> indentAddFunction$iv = StringsKt__IndentKt.getIndentFunction$StringsKt__IndentKt(newIndent);
        boolean $i$f$reindent$StringsKt__IndentKt = false;
        int lastIndex$iv = CollectionsKt.getLastIndex($this$reindent$iv);
        Iterable $this$mapIndexedNotNull$iv$iv = (Iterable)$this$reindent$iv;
        boolean bl3 = false;
        p1 = $this$mapIndexedNotNull$iv$iv;
        Collection destination$iv$iv$iv = new ArrayList();
        boolean $i$f$mapIndexedNotNullTo = false;
        void $this$forEachIndexed$iv$iv$iv$iv = $this$mapIndexedNotNullTo$iv$iv$iv;
        boolean $i$f$forEachIndexed = false;
        int index$iv$iv$iv$iv = 0;
        for (Object item$iv$iv$iv$iv : $this$forEachIndexed$iv$iv$iv$iv) {
            String string;
            block8: {
                void value$iv;
                block9: {
                    block7: {
                        void element$iv$iv$iv;
                        int n2 = index$iv$iv$iv$iv++;
                        boolean bl4 = false;
                        if (n2 < 0) {
                            CollectionsKt.throwIndexOverflow();
                        }
                        Object t = item$iv$iv$iv$iv;
                        int index$iv$iv$iv = n2;
                        boolean bl32 = false;
                        String string2 = (String)element$iv$iv$iv;
                        int index$iv = index$iv$iv$iv;
                        boolean bl42 = false;
                        if (index$iv != 0 && index$iv != lastIndex$iv || !StringsKt.isBlank((CharSequence)value$iv)) break block7;
                        string = null;
                        break block8;
                    }
                    void line = value$iv;
                    boolean bl5 = false;
                    string = StringsKt.drop((String)line, minCommonIndent);
                    if (string == null) break block9;
                    String string3 = string;
                    boolean bl6 = false;
                    boolean bl7 = false;
                    string = indentAddFunction$iv.invoke(string3);
                    if (string != null) break block8;
                }
                string = value$iv;
            }
            if (string == null) continue;
            String string4 = string;
            boolean bl8 = false;
            boolean bl9 = false;
            String it$iv$iv$iv = string4;
            boolean bl82 = false;
            destination$iv$iv$iv.add(it$iv$iv$iv);
        }
        String string = ((StringBuilder)CollectionsKt.joinTo$default((List)destination$iv$iv$iv, new StringBuilder((int)resultSizeEstimate$iv), "\n", null, null, 0, null, null, 124, null)).toString();
        Intrinsics.checkNotNullExpressionValue(string, "mapIndexedNotNull { inde\u2026\"\\n\")\n        .toString()");
        return string;
    }

    public static /* synthetic */ String replaceIndent$default(String string, String string2, int n, Object object) {
        if ((n & 1) != 0) {
            string2 = "";
        }
        return StringsKt.replaceIndent(string, string2);
    }

    @NotNull
    public static final String prependIndent(@NotNull String $this$prependIndent, @NotNull String indent) {
        Intrinsics.checkNotNullParameter($this$prependIndent, "$this$prependIndent");
        Intrinsics.checkNotNullParameter(indent, "indent");
        return SequencesKt.joinToString$default(SequencesKt.map(StringsKt.lineSequence($this$prependIndent), (Function1)new Function1<String, String>(indent){
            final /* synthetic */ String $indent;

            @NotNull
            public final String invoke(@NotNull String it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return StringsKt.isBlank(it) ? (it.length() < this.$indent.length() ? this.$indent : it) : this.$indent + it;
            }
            {
                this.$indent = string;
                super(1);
            }
        }), "\n", null, null, 0, null, null, 62, null);
    }

    public static /* synthetic */ String prependIndent$default(String string, String string2, int n, Object object) {
        if ((n & 1) != 0) {
            string2 = "    ";
        }
        return StringsKt.prependIndent(string, string2);
    }

    /*
     * WARNING - void declaration
     */
    private static final int indentWidth$StringsKt__IndentKt(String $this$indentWidth) {
        int n;
        int n2;
        block2: {
            CharSequence $this$indexOfFirst$iv = $this$indentWidth;
            boolean $i$f$indexOfFirst = false;
            n2 = 0;
            int n3 = $this$indexOfFirst$iv.length();
            while (n2 < n3) {
                void index$iv;
                char it = $this$indexOfFirst$iv.charAt((int)index$iv);
                boolean bl = false;
                if (!CharsKt.isWhitespace(it)) {
                    n = index$iv;
                    break block2;
                }
                ++index$iv;
            }
            n = -1;
        }
        int n4 = n;
        boolean bl = false;
        n2 = 0;
        int it = n4;
        boolean bl2 = false;
        return it == -1 ? $this$indentWidth.length() : it;
    }

    private static final Function1<String, String> getIndentFunction$StringsKt__IndentKt(String indent) {
        CharSequence charSequence = indent;
        boolean bl = false;
        return charSequence.length() == 0 ? (Function1)getIndentFunction.1.INSTANCE : (Function1)new Function1<String, String>(indent){
            final /* synthetic */ String $indent;

            @NotNull
            public final String invoke(@NotNull String line) {
                Intrinsics.checkNotNullParameter(line, "line");
                return this.$indent + line;
            }
            {
                this.$indent = string;
                super(1);
            }
        };
    }

    /*
     * WARNING - void declaration
     */
    private static final String reindent$StringsKt__IndentKt(List<String> $this$reindent, int resultSizeEstimate, Function1<? super String, String> indentAddFunction, Function1<? super String, String> indentCutFunction) {
        void $this$mapIndexedNotNullTo$iv$iv;
        int $i$f$reindent$StringsKt__IndentKt = 0;
        int lastIndex = CollectionsKt.getLastIndex($this$reindent);
        Iterable $this$mapIndexedNotNull$iv = $this$reindent;
        boolean $i$f$mapIndexedNotNull = false;
        Iterable iterable = $this$mapIndexedNotNull$iv;
        Collection destination$iv$iv = new ArrayList();
        boolean $i$f$mapIndexedNotNullTo = false;
        void $this$forEachIndexed$iv$iv$iv = $this$mapIndexedNotNullTo$iv$iv;
        boolean $i$f$forEachIndexed = false;
        int index$iv$iv$iv = 0;
        for (Object item$iv$iv$iv : $this$forEachIndexed$iv$iv$iv) {
            String string;
            block8: {
                void value;
                block9: {
                    block7: {
                        void element$iv$iv;
                        int n = index$iv$iv$iv++;
                        boolean bl = false;
                        if (n < 0) {
                            if (PlatformImplementationsKt.apiVersionIsAtLeast(1, 3, 0)) {
                                CollectionsKt.throwIndexOverflow();
                            } else {
                                throw (Throwable)new ArithmeticException("Index overflow has happened.");
                            }
                        }
                        Object t = item$iv$iv$iv;
                        int index$iv$iv = n;
                        boolean bl2 = false;
                        String string2 = (String)element$iv$iv;
                        int index = index$iv$iv;
                        boolean bl3 = false;
                        if (index != 0 && index != lastIndex || !StringsKt.isBlank((CharSequence)value)) break block7;
                        string = null;
                        break block8;
                    }
                    string = indentCutFunction.invoke((String)value);
                    if (string == null) break block9;
                    String string3 = string;
                    boolean bl = false;
                    boolean bl4 = false;
                    string = indentAddFunction.invoke(string3);
                    if (string != null) break block8;
                }
                string = value;
            }
            if (string == null) continue;
            String string4 = string;
            boolean bl = false;
            boolean bl5 = false;
            String it$iv$iv = string4;
            boolean bl6 = false;
            destination$iv$iv.add(it$iv$iv);
        }
        String string = ((StringBuilder)CollectionsKt.joinTo$default((List)destination$iv$iv, new StringBuilder(resultSizeEstimate), "\n", null, null, 0, null, null, 124, null)).toString();
        Intrinsics.checkNotNullExpressionValue(string, "mapIndexedNotNull { inde\u2026\"\\n\")\n        .toString()");
        return string;
    }
}

