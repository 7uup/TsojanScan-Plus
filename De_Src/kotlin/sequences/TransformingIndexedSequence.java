/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.sequences;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010(\n\u0000\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\b\u0012\u0004\u0012\u0002H\u00020\u0003B-\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003\u0012\u0018\u0010\u0005\u001a\u0014\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0006\u00a2\u0006\u0002\u0010\bJ\u000f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00010\nH\u0096\u0002R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010\u0005\u001a\u0014\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2={"Lkotlin/sequences/TransformingIndexedSequence;", "T", "R", "Lkotlin/sequences/Sequence;", "sequence", "transformer", "Lkotlin/Function2;", "", "(Lkotlin/sequences/Sequence;Lkotlin/jvm/functions/Function2;)V", "iterator", "", "kotlin-stdlib"})
public final class TransformingIndexedSequence<T, R>
implements Sequence<R> {
    private final Sequence<T> sequence;
    private final Function2<Integer, T, R> transformer;

    @Override
    @NotNull
    public Iterator<R> iterator() {
        return new Iterator<R>(this){
            @NotNull
            private final Iterator<T> iterator;
            private int index;
            final /* synthetic */ TransformingIndexedSequence this$0;

            @NotNull
            public final Iterator<T> getIterator() {
                return this.iterator;
            }

            public final int getIndex() {
                return this.index;
            }

            public final void setIndex(int n) {
                this.index = n;
            }

            public R next() {
                Function2 function2 = TransformingIndexedSequence.access$getTransformer$p(this.this$0);
                int n = this.index;
                this.index = n + 1;
                boolean bl = false;
                if (n < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                return function2.invoke(n, this.iterator.next());
            }

            public boolean hasNext() {
                return this.iterator.hasNext();
            }
            {
                this.this$0 = this$0;
                this.iterator = TransformingIndexedSequence.access$getSequence$p(this$0).iterator();
            }

            public void remove() {
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
            }
        };
    }

    public TransformingIndexedSequence(@NotNull Sequence<? extends T> sequence, @NotNull Function2<? super Integer, ? super T, ? extends R> transformer) {
        Intrinsics.checkNotNullParameter(sequence, "sequence");
        Intrinsics.checkNotNullParameter(transformer, "transformer");
        this.sequence = sequence;
        this.transformer = transformer;
    }

    public static final /* synthetic */ Function2 access$getTransformer$p(TransformingIndexedSequence $this) {
        return $this.transformer;
    }

    public static final /* synthetic */ Sequence access$getSequence$p(TransformingIndexedSequence $this) {
        return $this.sequence;
    }
}

