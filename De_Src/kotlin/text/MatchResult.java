/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.text;

import java.util.List;
import kotlin.Metadata;
import kotlin.internal.InlineOnly;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.text.MatchGroupCollection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\bf\u0018\u00002\u00020\u0001:\u0001\u0017J\n\u0010\u0016\u001a\u0004\u0018\u00010\u0000H&R\u0014\u0010\u0002\u001a\u00020\u00038VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0018\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0012\u0010\u000b\u001a\u00020\fX\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0012\u0010\u000f\u001a\u00020\u0010X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0012\u0010\u0013\u001a\u00020\bX\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015\u00a8\u0006\u0018"}, d2={"Lkotlin/text/MatchResult;", "", "destructured", "Lkotlin/text/MatchResult$Destructured;", "getDestructured", "()Lkotlin/text/MatchResult$Destructured;", "groupValues", "", "", "getGroupValues", "()Ljava/util/List;", "groups", "Lkotlin/text/MatchGroupCollection;", "getGroups", "()Lkotlin/text/MatchGroupCollection;", "range", "Lkotlin/ranges/IntRange;", "getRange", "()Lkotlin/ranges/IntRange;", "value", "getValue", "()Ljava/lang/String;", "next", "Destructured", "kotlin-stdlib"})
public interface MatchResult {
    @NotNull
    public IntRange getRange();

    @NotNull
    public String getValue();

    @NotNull
    public MatchGroupCollection getGroups();

    @NotNull
    public List<String> getGroupValues();

    @NotNull
    public Destructured getDestructured();

    @Nullable
    public MatchResult next();

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010 \n\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\bH\u0087\nJ\t\u0010\t\u001a\u00020\bH\u0087\nJ\t\u0010\n\u001a\u00020\bH\u0087\nJ\t\u0010\u000b\u001a\u00020\bH\u0087\nJ\t\u0010\f\u001a\u00020\bH\u0087\nJ\t\u0010\r\u001a\u00020\bH\u0087\nJ\t\u0010\u000e\u001a\u00020\bH\u0087\nJ\t\u0010\u000f\u001a\u00020\bH\u0087\nJ\t\u0010\u0010\u001a\u00020\bH\u0087\nJ\t\u0010\u0011\u001a\u00020\bH\u0087\nJ\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\b0\u0013R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0014"}, d2={"Lkotlin/text/MatchResult$Destructured;", "", "match", "Lkotlin/text/MatchResult;", "(Lkotlin/text/MatchResult;)V", "getMatch", "()Lkotlin/text/MatchResult;", "component1", "", "component10", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "toList", "", "kotlin-stdlib"})
    public static final class Destructured {
        @NotNull
        private final MatchResult match;

        @InlineOnly
        private final String component1() {
            int $i$f$component1 = 0;
            return this.getMatch().getGroupValues().get(1);
        }

        @InlineOnly
        private final String component2() {
            int $i$f$component2 = 0;
            return this.getMatch().getGroupValues().get(2);
        }

        @InlineOnly
        private final String component3() {
            int $i$f$component3 = 0;
            return this.getMatch().getGroupValues().get(3);
        }

        @InlineOnly
        private final String component4() {
            int $i$f$component4 = 0;
            return this.getMatch().getGroupValues().get(4);
        }

        @InlineOnly
        private final String component5() {
            int $i$f$component5 = 0;
            return this.getMatch().getGroupValues().get(5);
        }

        @InlineOnly
        private final String component6() {
            int $i$f$component6 = 0;
            return this.getMatch().getGroupValues().get(6);
        }

        @InlineOnly
        private final String component7() {
            int $i$f$component7 = 0;
            return this.getMatch().getGroupValues().get(7);
        }

        @InlineOnly
        private final String component8() {
            int $i$f$component8 = 0;
            return this.getMatch().getGroupValues().get(8);
        }

        @InlineOnly
        private final String component9() {
            int $i$f$component9 = 0;
            return this.getMatch().getGroupValues().get(9);
        }

        @InlineOnly
        private final String component10() {
            int $i$f$component10 = 0;
            return this.getMatch().getGroupValues().get(10);
        }

        @NotNull
        public final List<String> toList() {
            return this.match.getGroupValues().subList(1, this.match.getGroupValues().size());
        }

        @NotNull
        public final MatchResult getMatch() {
            return this.match;
        }

        public Destructured(@NotNull MatchResult match) {
            Intrinsics.checkNotNullParameter(match, "match");
            this.match = match;
        }
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=3)
    public static final class DefaultImpls {
        @NotNull
        public static Destructured getDestructured(@NotNull MatchResult $this) {
            return new Destructured($this);
        }
    }
}

