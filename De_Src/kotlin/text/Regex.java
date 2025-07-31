/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.text;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import kotlin.text.FlagEnum;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.RegexKt;
import kotlin.text.RegexOption;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 ,2\u00060\u0001j\u0002`\u0002:\u0002,-B\u000f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\u0002\u0010\u0005B\u0017\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bB\u001d\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00070\n\u00a2\u0006\u0002\u0010\u000bB\u000f\b\u0001\u0012\u0006\u0010\f\u001a\u00020\r\u00a2\u0006\u0002\u0010\u000eJ\u000e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017J\u001a\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u001a\u001a\u00020\u001bJ\u001e\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00190\u001d2\u0006\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u001a\u001a\u00020\u001bJ\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u0016\u001a\u00020\u0017J\u0011\u0010\u001f\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0086\u0004J\"\u0010 \u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u00172\u0012\u0010!\u001a\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00170\"J\u0016\u0010 \u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010#\u001a\u00020\u0004J\u0016\u0010$\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010#\u001a\u00020\u0004J\u001e\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00040&2\u0006\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010'\u001a\u00020\u001bJ\u0006\u0010(\u001a\u00020\rJ\b\u0010)\u001a\u00020\u0004H\u0016J\b\u0010*\u001a\u00020+H\u0002R\u0016\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00070\n8F\u00a2\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0003\u001a\u00020\u00048F\u00a2\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013\u00a8\u0006."}, d2={"Lkotlin/text/Regex;", "Ljava/io/Serializable;", "Lkotlin/io/Serializable;", "pattern", "", "(Ljava/lang/String;)V", "option", "Lkotlin/text/RegexOption;", "(Ljava/lang/String;Lkotlin/text/RegexOption;)V", "options", "", "(Ljava/lang/String;Ljava/util/Set;)V", "nativePattern", "Ljava/util/regex/Pattern;", "(Ljava/util/regex/Pattern;)V", "_options", "getOptions", "()Ljava/util/Set;", "getPattern", "()Ljava/lang/String;", "containsMatchIn", "", "input", "", "find", "Lkotlin/text/MatchResult;", "startIndex", "", "findAll", "Lkotlin/sequences/Sequence;", "matchEntire", "matches", "replace", "transform", "Lkotlin/Function1;", "replacement", "replaceFirst", "split", "", "limit", "toPattern", "toString", "writeReplace", "", "Companion", "Serialized", "kotlin-stdlib"})
public final class Regex
implements Serializable {
    private Set<? extends RegexOption> _options;
    private final Pattern nativePattern;
    public static final Companion Companion = new Companion(null);

    @NotNull
    public final String getPattern() {
        String string = this.nativePattern.pattern();
        Intrinsics.checkNotNullExpressionValue(string, "nativePattern.pattern()");
        return string;
    }

    @NotNull
    public final Set<RegexOption> getOptions() {
        Set<RegexOption> set = this._options;
        if (set == null) {
            int value$iv = this.nativePattern.flags();
            boolean $i$f$fromInt = false;
            EnumSet<RegexOption> enumSet = EnumSet.allOf(RegexOption.class);
            boolean bl = false;
            boolean bl2 = false;
            EnumSet<RegexOption> $this$apply$iv = enumSet;
            boolean bl3 = false;
            CollectionsKt.retainAll((Iterable)$this$apply$iv, (Function1)new Function1<T, Boolean>(value$iv){
                final /* synthetic */ int $value$inlined;
                {
                    this.$value$inlined = n;
                    super(1);
                }

                public final boolean invoke(T it) {
                    return (this.$value$inlined & ((FlagEnum)it).getMask()) == ((FlagEnum)it).getValue();
                }
            });
            Set set2 = Collections.unmodifiableSet((Set)enumSet);
            Intrinsics.checkNotNullExpressionValue(set2, "Collections.unmodifiable\u2026mask == it.value }\n    })");
            Set set3 = set2;
            boolean bl4 = false;
            boolean bl5 = false;
            Set it = set3;
            boolean bl6 = false;
            this._options = it;
            set = set3;
        }
        return set;
    }

    public final boolean matches(@NotNull CharSequence input) {
        Intrinsics.checkNotNullParameter(input, "input");
        return this.nativePattern.matcher(input).matches();
    }

    public final boolean containsMatchIn(@NotNull CharSequence input) {
        Intrinsics.checkNotNullParameter(input, "input");
        return this.nativePattern.matcher(input).find();
    }

    @Nullable
    public final MatchResult find(@NotNull CharSequence input, int startIndex) {
        Intrinsics.checkNotNullParameter(input, "input");
        Matcher matcher = this.nativePattern.matcher(input);
        Intrinsics.checkNotNullExpressionValue(matcher, "nativePattern.matcher(input)");
        return RegexKt.access$findNext(matcher, startIndex, input);
    }

    public static /* synthetic */ MatchResult find$default(Regex regex, CharSequence charSequence, int n, int n2, Object object) {
        if ((n2 & 2) != 0) {
            n = 0;
        }
        return regex.find(charSequence, n);
    }

    @NotNull
    public final Sequence<MatchResult> findAll(@NotNull CharSequence input, int startIndex) {
        Intrinsics.checkNotNullParameter(input, "input");
        if (startIndex < 0 || startIndex > input.length()) {
            throw (Throwable)new IndexOutOfBoundsException("Start index out of bounds: " + startIndex + ", input length: " + input.length());
        }
        return SequencesKt.generateSequence((Function0)new Function0<MatchResult>(this, input, startIndex){
            final /* synthetic */ Regex this$0;
            final /* synthetic */ CharSequence $input;
            final /* synthetic */ int $startIndex;

            @Nullable
            public final MatchResult invoke() {
                return this.this$0.find(this.$input, this.$startIndex);
            }
            {
                this.this$0 = regex;
                this.$input = charSequence;
                this.$startIndex = n;
                super(0);
            }
        }, (Function1)findAll.2.INSTANCE);
    }

    public static /* synthetic */ Sequence findAll$default(Regex regex, CharSequence charSequence, int n, int n2, Object object) {
        if ((n2 & 2) != 0) {
            n = 0;
        }
        return regex.findAll(charSequence, n);
    }

    @Nullable
    public final MatchResult matchEntire(@NotNull CharSequence input) {
        Intrinsics.checkNotNullParameter(input, "input");
        Matcher matcher = this.nativePattern.matcher(input);
        Intrinsics.checkNotNullExpressionValue(matcher, "nativePattern.matcher(input)");
        return RegexKt.access$matchEntire(matcher, input);
    }

    @NotNull
    public final String replace(@NotNull CharSequence input, @NotNull String replacement) {
        Intrinsics.checkNotNullParameter(input, "input");
        Intrinsics.checkNotNullParameter(replacement, "replacement");
        String string = this.nativePattern.matcher(input).replaceAll(replacement);
        Intrinsics.checkNotNullExpressionValue(string, "nativePattern.matcher(in\u2026).replaceAll(replacement)");
        return string;
    }

    @NotNull
    public final String replace(@NotNull CharSequence input, @NotNull Function1<? super MatchResult, ? extends CharSequence> transform) {
        Intrinsics.checkNotNullParameter(input, "input");
        Intrinsics.checkNotNullParameter(transform, "transform");
        MatchResult matchResult = Regex.find$default(this, input, 0, 2, null);
        if (matchResult == null) {
            return ((Object)input).toString();
        }
        MatchResult match = matchResult;
        int lastStart = 0;
        int length = input.length();
        StringBuilder sb = new StringBuilder(length);
        do {
            MatchResult foundMatch;
            Intrinsics.checkNotNull(match);
            sb.append(input, lastStart, (int)foundMatch.getRange().getStart());
            sb.append(transform.invoke(foundMatch));
            lastStart = foundMatch.getRange().getEndInclusive() + 1;
            match = foundMatch.next();
        } while (lastStart < length && match != null);
        if (lastStart < length) {
            sb.append(input, lastStart, length);
        }
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "sb.toString()");
        return string;
    }

    @NotNull
    public final String replaceFirst(@NotNull CharSequence input, @NotNull String replacement) {
        Intrinsics.checkNotNullParameter(input, "input");
        Intrinsics.checkNotNullParameter(replacement, "replacement");
        String string = this.nativePattern.matcher(input).replaceFirst(replacement);
        Intrinsics.checkNotNullExpressionValue(string, "nativePattern.matcher(in\u2026replaceFirst(replacement)");
        return string;
    }

    @NotNull
    public final List<String> split(@NotNull CharSequence input, int limit) {
        boolean bl;
        int n;
        CharSequence charSequence;
        Intrinsics.checkNotNullParameter(input, "input");
        boolean bl2 = limit >= 0;
        boolean bl3 = false;
        boolean bl4 = false;
        if (!bl2) {
            boolean bl5 = false;
            String string = "Limit must be non-negative, but was " + limit + '.';
            throw (Throwable)new IllegalArgumentException(string.toString());
        }
        Matcher matcher = this.nativePattern.matcher(input);
        if (!matcher.find() || limit == 1) {
            return CollectionsKt.listOf(((Object)input).toString());
        }
        ArrayList<String> result = new ArrayList<String>(limit > 0 ? RangesKt.coerceAtMost(limit, 10) : 10);
        int lastStart = 0;
        int lastSplit = limit - 1;
        do {
            charSequence = input;
            n = matcher.start();
            bl = false;
            result.add(((Object)charSequence.subSequence(lastStart, n)).toString());
            lastStart = matcher.end();
        } while ((lastSplit < 0 || result.size() != lastSplit) && matcher.find());
        charSequence = input;
        n = input.length();
        bl = false;
        result.add(((Object)charSequence.subSequence(lastStart, n)).toString());
        return result;
    }

    public static /* synthetic */ List split$default(Regex regex, CharSequence charSequence, int n, int n2, Object object) {
        if ((n2 & 2) != 0) {
            n = 0;
        }
        return regex.split(charSequence, n);
    }

    @NotNull
    public String toString() {
        String string = this.nativePattern.toString();
        Intrinsics.checkNotNullExpressionValue(string, "nativePattern.toString()");
        return string;
    }

    @NotNull
    public final Pattern toPattern() {
        return this.nativePattern;
    }

    private final Object writeReplace() {
        String string = this.nativePattern.pattern();
        Intrinsics.checkNotNullExpressionValue(string, "nativePattern.pattern()");
        return new Serialized(string, this.nativePattern.flags());
    }

    @PublishedApi
    public Regex(@NotNull Pattern nativePattern) {
        Intrinsics.checkNotNullParameter(nativePattern, "nativePattern");
        this.nativePattern = nativePattern;
    }

    public Regex(@NotNull String pattern) {
        Intrinsics.checkNotNullParameter(pattern, "pattern");
        Pattern pattern2 = Pattern.compile(pattern);
        Intrinsics.checkNotNullExpressionValue(pattern2, "Pattern.compile(pattern)");
        this(pattern2);
    }

    public Regex(@NotNull String pattern, @NotNull RegexOption option) {
        Intrinsics.checkNotNullParameter(pattern, "pattern");
        Intrinsics.checkNotNullParameter(option, "option");
        Pattern pattern2 = Pattern.compile(pattern, Regex.Companion.ensureUnicodeCase(option.getValue()));
        Intrinsics.checkNotNullExpressionValue(pattern2, "Pattern.compile(pattern,\u2026nicodeCase(option.value))");
        this(pattern2);
    }

    public Regex(@NotNull String pattern, @NotNull Set<? extends RegexOption> options) {
        Intrinsics.checkNotNullParameter(pattern, "pattern");
        Intrinsics.checkNotNullParameter(options, "options");
        Pattern pattern2 = Pattern.compile(pattern, Regex.Companion.ensureUnicodeCase(RegexKt.access$toInt(options)));
        Intrinsics.checkNotNullExpressionValue(pattern2, "Pattern.compile(pattern,\u2026odeCase(options.toInt()))");
        this(pattern2);
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0002\u0018\u0000 \u000e2\u00060\u0001j\u0002`\u0002:\u0001\u000eB\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\b\u0010\f\u001a\u00020\rH\u0002R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u000f"}, d2={"Lkotlin/text/Regex$Serialized;", "Ljava/io/Serializable;", "Lkotlin/io/Serializable;", "pattern", "", "flags", "", "(Ljava/lang/String;I)V", "getFlags", "()I", "getPattern", "()Ljava/lang/String;", "readResolve", "", "Companion", "kotlin-stdlib"})
    private static final class Serialized
    implements Serializable {
        @NotNull
        private final String pattern;
        private final int flags;
        private static final long serialVersionUID = 0L;
        public static final Companion Companion = new Companion(null);

        private final Object readResolve() {
            Pattern pattern = Pattern.compile(this.pattern, this.flags);
            Intrinsics.checkNotNullExpressionValue(pattern, "Pattern.compile(pattern, flags)");
            return new Regex(pattern);
        }

        @NotNull
        public final String getPattern() {
            return this.pattern;
        }

        public final int getFlags() {
            return this.flags;
        }

        public Serialized(@NotNull String pattern, int flags) {
            Intrinsics.checkNotNullParameter(pattern, "pattern");
            this.pattern = pattern;
            this.flags = flags;
        }

        @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2={"Lkotlin/text/Regex$Serialized$Companion;", "", "()V", "serialVersionUID", "", "kotlin-stdlib"})
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
                this();
            }
        }
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0002J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007J\u000e\u0010\t\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\u0007\u00a8\u0006\f"}, d2={"Lkotlin/text/Regex$Companion;", "", "()V", "ensureUnicodeCase", "", "flags", "escape", "", "literal", "escapeReplacement", "fromLiteral", "Lkotlin/text/Regex;", "kotlin-stdlib"})
    public static final class Companion {
        @NotNull
        public final Regex fromLiteral(@NotNull String literal) {
            Intrinsics.checkNotNullParameter(literal, "literal");
            String string = literal;
            RegexOption regexOption = RegexOption.LITERAL;
            boolean bl = false;
            return new Regex(string, regexOption);
        }

        @NotNull
        public final String escape(@NotNull String literal) {
            Intrinsics.checkNotNullParameter(literal, "literal");
            String string = Pattern.quote(literal);
            Intrinsics.checkNotNullExpressionValue(string, "Pattern.quote(literal)");
            return string;
        }

        @NotNull
        public final String escapeReplacement(@NotNull String literal) {
            Intrinsics.checkNotNullParameter(literal, "literal");
            String string = Matcher.quoteReplacement(literal);
            Intrinsics.checkNotNullExpressionValue(string, "Matcher.quoteReplacement(literal)");
            return string;
        }

        private final int ensureUnicodeCase(int flags) {
            return (flags & 2) != 0 ? flags | 0x40 : flags;
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

