/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.ranges;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.ranges.ClosedRange;
import kotlin.ranges.LongProgression;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 \u00152\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002:\u0001\u0015B\u0015\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0006J\u0011\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0003H\u0096\u0002J\u0013\u0010\r\u001a\u00020\u000b2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0096\u0002J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\u000bH\u0016J\b\u0010\u0013\u001a\u00020\u0014H\u0016R\u0014\u0010\u0005\u001a\u00020\u00038VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u0004\u001a\u00020\u00038VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\t\u0010\b\u00a8\u0006\u0016"}, d2={"Lkotlin/ranges/LongRange;", "Lkotlin/ranges/LongProgression;", "Lkotlin/ranges/ClosedRange;", "", "start", "endInclusive", "(JJ)V", "getEndInclusive", "()Ljava/lang/Long;", "getStart", "contains", "", "value", "equals", "other", "", "hashCode", "", "isEmpty", "toString", "", "Companion", "kotlin-stdlib"})
public final class LongRange
extends LongProgression
implements ClosedRange<Long> {
    @NotNull
    private static final LongRange EMPTY;
    public static final Companion Companion;

    @Override
    @NotNull
    public Long getStart() {
        return this.getFirst();
    }

    @Override
    @NotNull
    public Long getEndInclusive() {
        return this.getLast();
    }

    @Override
    public boolean contains(long value) {
        return this.getFirst() <= value && value <= this.getLast();
    }

    @Override
    public boolean isEmpty() {
        return this.getFirst() > this.getLast();
    }

    @Override
    public boolean equals(@Nullable Object other) {
        return other instanceof LongRange && (this.isEmpty() && ((LongRange)other).isEmpty() || this.getFirst() == ((LongRange)other).getFirst() && this.getLast() == ((LongRange)other).getLast());
    }

    @Override
    public int hashCode() {
        return this.isEmpty() ? -1 : (int)((long)31 * (this.getFirst() ^ this.getFirst() >>> 32) + (this.getLast() ^ this.getLast() >>> 32));
    }

    @Override
    @NotNull
    public String toString() {
        return this.getFirst() + ".." + this.getLast();
    }

    public LongRange(long start, long endInclusive) {
        super(start, endInclusive, 1L);
    }

    static {
        Companion = new Companion(null);
        EMPTY = new LongRange(1L, 0L);
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2={"Lkotlin/ranges/LongRange$Companion;", "", "()V", "EMPTY", "Lkotlin/ranges/LongRange;", "getEMPTY", "()Lkotlin/ranges/LongRange;", "kotlin-stdlib"})
    public static final class Companion {
        @NotNull
        public final LongRange getEMPTY() {
            return EMPTY;
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

