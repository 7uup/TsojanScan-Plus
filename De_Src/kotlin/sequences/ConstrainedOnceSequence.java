/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.sequences;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010(\n\u0000\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0013\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002\u00a2\u0006\u0002\u0010\u0004J\u000f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\tH\u0096\u0002R(\u0010\u0005\u001a\u001c\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00028\u0000 \u0007*\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u00020\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\n"}, d2={"Lkotlin/sequences/ConstrainedOnceSequence;", "T", "Lkotlin/sequences/Sequence;", "sequence", "(Lkotlin/sequences/Sequence;)V", "sequenceRef", "Ljava/util/concurrent/atomic/AtomicReference;", "kotlin.jvm.PlatformType", "iterator", "", "kotlin-stdlib"})
public final class ConstrainedOnceSequence<T>
implements Sequence<T> {
    private final AtomicReference<Sequence<T>> sequenceRef;

    @Override
    @NotNull
    public Iterator<T> iterator() {
        Sequence sequence = this.sequenceRef.getAndSet(null);
        if (sequence == null) {
            throw (Throwable)new IllegalStateException("This sequence can be consumed only once.");
        }
        Sequence sequence2 = sequence;
        return sequence2.iterator();
    }

    public ConstrainedOnceSequence(@NotNull Sequence<? extends T> sequence) {
        Intrinsics.checkNotNullParameter(sequence, "sequence");
        this.sequenceRef = new AtomicReference<Sequence<? extends T>>(sequence);
    }
}

