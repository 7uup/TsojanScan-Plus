/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.ranges;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.CharProgression;
import kotlin.ranges.ClosedRange;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\f\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 \u00152\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002:\u0001\u0015B\u0015\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0006J\u0011\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0003H\u0096\u0002J\u0013\u0010\r\u001a\u00020\u000b2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0096\u0002J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\u000bH\u0016J\b\u0010\u0013\u001a\u00020\u0014H\u0016R\u0014\u0010\u0005\u001a\u00020\u00038VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u0004\u001a\u00020\u00038VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\t\u0010\b\u00a8\u0006\u0016"}, d2={"Lkotlin/ranges/CharRange;", "Lkotlin/ranges/CharProgression;", "Lkotlin/ranges/ClosedRange;", "", "start", "endInclusive", "(CC)V", "getEndInclusive", "()Ljava/lang/Character;", "getStart", "contains", "", "value", "equals", "other", "", "hashCode", "", "isEmpty", "toString", "", "Companion", "kotlin-stdlib"})
public final class CharRange
extends CharProgression
implements ClosedRange<Character> {
    @NotNull
    private static final CharRange EMPTY;
    public static final Companion Companion;

    @Override
    @NotNull
    public Character getStart() {
        return Character.valueOf(this.getFirst());
    }

    @Override
    @NotNull
    public Character getEndInclusive() {
        return Character.valueOf(this.getLast());
    }

    @Override
    public boolean contains(char value) {
        return Intrinsics.compare(this.getFirst(), value) <= 0 && Intrinsics.compare(value, this.getLast()) <= 0;
    }

    @Override
    public boolean isEmpty() {
        return Intrinsics.compare(this.getFirst(), this.getLast()) > 0;
    }

    @Override
    public boolean equals(@Nullable Object other) {
        return other instanceof CharRange && (this.isEmpty() && ((CharRange)other).isEmpty() || this.getFirst() == ((CharRange)other).getFirst() && this.getLast() == ((CharRange)other).getLast());
    }

    @Override
    public int hashCode() {
        return this.isEmpty() ? -1 : 31 * this.getFirst() + this.getLast();
    }

    @Override
    @NotNull
    public String toString() {
        return this.getFirst() + ".." + this.getLast();
    }

    public CharRange(char start, char endInclusive) {
        super(start, endInclusive, 1);
    }

    static {
        Companion = new Companion(null);
        EMPTY = new CharRange((char)1, (char)0);
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2={"Lkotlin/ranges/CharRange$Companion;", "", "()V", "EMPTY", "Lkotlin/ranges/CharRange;", "getEMPTY", "()Lkotlin/ranges/CharRange;", "kotlin-stdlib"})
    public static final class Companion {
        @NotNull
        public final CharRange getEMPTY() {
            return EMPTY;
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

