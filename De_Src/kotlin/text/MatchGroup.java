/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.text;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\f\u001a\u00020\u0005H\u00c6\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0011\u001a\u00020\u0012H\u00d6\u0001J\t\u0010\u0013\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0014"}, d2={"Lkotlin/text/MatchGroup;", "", "value", "", "range", "Lkotlin/ranges/IntRange;", "(Ljava/lang/String;Lkotlin/ranges/IntRange;)V", "getRange", "()Lkotlin/ranges/IntRange;", "getValue", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "kotlin-stdlib"})
public final class MatchGroup {
    @NotNull
    private final String value;
    @NotNull
    private final IntRange range;

    @NotNull
    public final String getValue() {
        return this.value;
    }

    @NotNull
    public final IntRange getRange() {
        return this.range;
    }

    public MatchGroup(@NotNull String value, @NotNull IntRange range) {
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(range, "range");
        this.value = value;
        this.range = range;
    }

    @NotNull
    public final String component1() {
        return this.value;
    }

    @NotNull
    public final IntRange component2() {
        return this.range;
    }

    @NotNull
    public final MatchGroup copy(@NotNull String value, @NotNull IntRange range) {
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(range, "range");
        return new MatchGroup(value, range);
    }

    public static /* synthetic */ MatchGroup copy$default(MatchGroup matchGroup, String string, IntRange intRange, int n, Object object) {
        if ((n & 1) != 0) {
            string = matchGroup.value;
        }
        if ((n & 2) != 0) {
            intRange = matchGroup.range;
        }
        return matchGroup.copy(string, intRange);
    }

    @NotNull
    public String toString() {
        return "MatchGroup(value=" + this.value + ", range=" + this.range + ")";
    }

    public int hashCode() {
        String string = this.value;
        IntRange intRange = this.range;
        return (string != null ? string.hashCode() : 0) * 31 + (intRange != null ? ((Object)intRange).hashCode() : 0);
    }

    public boolean equals(@Nullable Object object) {
        block3: {
            block2: {
                if (this == object) break block2;
                if (!(object instanceof MatchGroup)) break block3;
                MatchGroup matchGroup = (MatchGroup)object;
                if (!Intrinsics.areEqual(this.value, matchGroup.value) || !Intrinsics.areEqual(this.range, matchGroup.range)) break block3;
            }
            return true;
        }
        return false;
    }
}

