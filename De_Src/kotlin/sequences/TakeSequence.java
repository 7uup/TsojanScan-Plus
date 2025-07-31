/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.sequences;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.DropTakeSequence;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import kotlin.sequences.SubSequence;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010(\n\u0002\b\u0002\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u001b\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\u0016\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u00022\u0006\u0010\t\u001a\u00020\u0006H\u0016J\u000f\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\u000bH\u0096\u0002J\u0016\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\u00022\u0006\u0010\t\u001a\u00020\u0006H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2={"Lkotlin/sequences/TakeSequence;", "T", "Lkotlin/sequences/Sequence;", "Lkotlin/sequences/DropTakeSequence;", "sequence", "count", "", "(Lkotlin/sequences/Sequence;I)V", "drop", "n", "iterator", "", "take", "kotlin-stdlib"})
public final class TakeSequence<T>
implements Sequence<T>,
DropTakeSequence<T> {
    private final Sequence<T> sequence;
    private final int count;

    @Override
    @NotNull
    public Sequence<T> drop(int n) {
        return n >= this.count ? SequencesKt.emptySequence() : (Sequence)new SubSequence<T>(this.sequence, n, this.count);
    }

    @Override
    @NotNull
    public Sequence<T> take(int n) {
        return n >= this.count ? this : new TakeSequence<T>(this.sequence, n);
    }

    @Override
    @NotNull
    public Iterator<T> iterator() {
        return new Iterator<T>(this){
            private int left;
            @NotNull
            private final Iterator<T> iterator;
            final /* synthetic */ TakeSequence this$0;

            public final int getLeft() {
                return this.left;
            }

            public final void setLeft(int n) {
                this.left = n;
            }

            @NotNull
            public final Iterator<T> getIterator() {
                return this.iterator;
            }

            public T next() {
                if (this.left == 0) {
                    throw (Throwable)new NoSuchElementException();
                }
                int n = this.left;
                this.left = n + -1;
                return this.iterator.next();
            }

            public boolean hasNext() {
                return this.left > 0 && this.iterator.hasNext();
            }
            {
                this.this$0 = this$0;
                this.left = TakeSequence.access$getCount$p(this$0);
                this.iterator = TakeSequence.access$getSequence$p(this$0).iterator();
            }

            public void remove() {
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
            }
        };
    }

    public TakeSequence(@NotNull Sequence<? extends T> sequence, int count) {
        Intrinsics.checkNotNullParameter(sequence, "sequence");
        this.sequence = sequence;
        this.count = count;
        boolean bl = this.count >= 0;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "count must be non-negative, but was " + this.count + '.';
            throw (Throwable)new IllegalArgumentException(string.toString());
        }
    }

    public static final /* synthetic */ int access$getCount$p(TakeSequence $this) {
        return $this.count;
    }

    public static final /* synthetic */ Sequence access$getSequence$p(TakeSequence $this) {
        return $this.sequence;
    }
}

