/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.ranges;

import kotlin.ExperimentalUnsignedTypes;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.UInt;
import kotlin.UnsignedKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.ranges.ClosedRange;
import kotlin.ranges.UIntProgression;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0007\u0018\u0000 \u00172\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002:\u0001\u0017B\u0018\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J\u001b\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0003H\u0096\u0002\u00f8\u0001\u0000\u00a2\u0006\u0004\b\r\u0010\u000eJ\u0013\u0010\u000f\u001a\u00020\u000b2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0096\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\b\u0010\u0014\u001a\u00020\u000bH\u0016J\b\u0010\u0015\u001a\u00020\u0016H\u0016R\u001a\u0010\u0005\u001a\u00020\u00038VX\u0096\u0004\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u001a\u0010\u0004\u001a\u00020\u00038VX\u0096\u0004\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0006\u001a\u0004\b\t\u0010\b\u00f8\u0001\u0000\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!\u00a8\u0006\u0018"}, d2={"Lkotlin/ranges/UIntRange;", "Lkotlin/ranges/UIntProgression;", "Lkotlin/ranges/ClosedRange;", "Lkotlin/UInt;", "start", "endInclusive", "(IILkotlin/jvm/internal/DefaultConstructorMarker;)V", "getEndInclusive-pVg5ArA", "()I", "getStart-pVg5ArA", "contains", "", "value", "contains-WZ4Q5Ns", "(I)Z", "equals", "other", "", "hashCode", "", "isEmpty", "toString", "", "Companion", "kotlin-stdlib"})
@SinceKotlin(version="1.3")
@ExperimentalUnsignedTypes
public final class UIntRange
extends UIntProgression
implements ClosedRange<UInt> {
    @NotNull
    private static final UIntRange EMPTY;
    public static final Companion Companion;

    public int getStart-pVg5ArA() {
        return this.getFirst-pVg5ArA();
    }

    public int getEndInclusive-pVg5ArA() {
        return this.getLast-pVg5ArA();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean contains-WZ4Q5Ns(int value) {
        int n = this.getFirst-pVg5ArA();
        int n2 = 0;
        if (UnsignedKt.uintCompare(n, value) > 0) return false;
        n = value;
        n2 = this.getLast-pVg5ArA();
        boolean bl = false;
        if (UnsignedKt.uintCompare(n, n2) > 0) return false;
        return true;
    }

    @Override
    public boolean isEmpty() {
        int n = this.getFirst-pVg5ArA();
        int n2 = this.getLast-pVg5ArA();
        boolean bl = false;
        return UnsignedKt.uintCompare(n, n2) > 0;
    }

    @Override
    public boolean equals(@Nullable Object other) {
        return other instanceof UIntRange && (this.isEmpty() && ((UIntRange)other).isEmpty() || this.getFirst-pVg5ArA() == ((UIntRange)other).getFirst-pVg5ArA() && this.getLast-pVg5ArA() == ((UIntRange)other).getLast-pVg5ArA());
    }

    @Override
    public int hashCode() {
        int n;
        if (this.isEmpty()) {
            n = -1;
        } else {
            int n2 = this.getFirst-pVg5ArA();
            boolean bl = false;
            int n3 = 31 * n2;
            n2 = this.getLast-pVg5ArA();
            bl = false;
            n = n3 + n2;
        }
        return n;
    }

    @Override
    @NotNull
    public String toString() {
        return UInt.toString-impl(this.getFirst-pVg5ArA()) + ".." + UInt.toString-impl(this.getLast-pVg5ArA());
    }

    private UIntRange(int start, int endInclusive) {
        super(start, endInclusive, 1, null);
    }

    static {
        Companion = new Companion(null);
        EMPTY = new UIntRange(-1, 0, null);
    }

    public /* synthetic */ UIntRange(int start, int endInclusive, DefaultConstructorMarker $constructor_marker) {
        this(start, endInclusive);
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2={"Lkotlin/ranges/UIntRange$Companion;", "", "()V", "EMPTY", "Lkotlin/ranges/UIntRange;", "getEMPTY", "()Lkotlin/ranges/UIntRange;", "kotlin-stdlib"})
    public static final class Companion {
        @NotNull
        public final UIntRange getEMPTY() {
            return EMPTY;
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

