/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.collections;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.collections.ReversedList;
import kotlin.collections.ReversedListReadOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=5, xi=1, d1={"\u0000\u0018\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\u001a\u001c\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0001\u001a#\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0003\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u0007\u00a2\u0006\u0002\b\u0004\u001a\u001d\u0010\u0005\u001a\u00020\u0006*\u0006\u0012\u0002\b\u00030\u00012\u0006\u0010\u0007\u001a\u00020\u0006H\u0002\u00a2\u0006\u0002\b\b\u001a\u001d\u0010\t\u001a\u00020\u0006*\u0006\u0012\u0002\b\u00030\u00012\u0006\u0010\u0007\u001a\u00020\u0006H\u0002\u00a2\u0006\u0002\b\n\u00a8\u0006\u000b"}, d2={"asReversed", "", "T", "", "asReversedMutable", "reverseElementIndex", "", "index", "reverseElementIndex$CollectionsKt__ReversedViewsKt", "reversePositionIndex", "reversePositionIndex$CollectionsKt__ReversedViewsKt", "kotlin-stdlib"}, xs="kotlin/collections/CollectionsKt")
class CollectionsKt__ReversedViewsKt
extends CollectionsKt__MutableCollectionsKt {
    private static final int reverseElementIndex$CollectionsKt__ReversedViewsKt(List<?> $this$reverseElementIndex, int index) {
        int n = index;
        if (0 > n || CollectionsKt.getLastIndex($this$reverseElementIndex) < n) {
            n = 0;
            throw (Throwable)new IndexOutOfBoundsException("Element index " + index + " must be in range [" + new IntRange(n, CollectionsKt.getLastIndex($this$reverseElementIndex)) + "].");
        }
        return CollectionsKt.getLastIndex($this$reverseElementIndex) - index;
    }

    private static final int reversePositionIndex$CollectionsKt__ReversedViewsKt(List<?> $this$reversePositionIndex, int index) {
        int n = index;
        if (0 > n || $this$reversePositionIndex.size() < n) {
            n = 0;
            throw (Throwable)new IndexOutOfBoundsException("Position index " + index + " must be in range [" + new IntRange(n, $this$reversePositionIndex.size()) + "].");
        }
        return $this$reversePositionIndex.size() - index;
    }

    @NotNull
    public static final <T> List<T> asReversed(@NotNull List<? extends T> $this$asReversed) {
        Intrinsics.checkNotNullParameter($this$asReversed, "$this$asReversed");
        return new ReversedListReadOnly<T>($this$asReversed);
    }

    @JvmName(name="asReversedMutable")
    @NotNull
    public static final <T> List<T> asReversedMutable(@NotNull List<T> $this$asReversed) {
        Intrinsics.checkNotNullParameter($this$asReversed, "$this$asReversed");
        return new ReversedList<T>($this$asReversed);
    }

    public static final /* synthetic */ int access$reverseElementIndex(List $this$access_u24reverseElementIndex, int index) {
        return CollectionsKt__ReversedViewsKt.reverseElementIndex$CollectionsKt__ReversedViewsKt($this$access_u24reverseElementIndex, index);
    }

    public static final /* synthetic */ int access$reversePositionIndex(List $this$access_u24reversePositionIndex, int index) {
        return CollectionsKt__ReversedViewsKt.reversePositionIndex$CollectionsKt__ReversedViewsKt($this$access_u24reversePositionIndex, index);
    }
}

