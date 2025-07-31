/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.sequences;

import java.util.Enumeration;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.internal.InlineOnly;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import kotlin.sequences.SequencesKt__SequenceBuilderKt;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=5, xi=1, d1={"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u001f\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u0087\b\u00a8\u0006\u0004"}, d2={"asSequence", "Lkotlin/sequences/Sequence;", "T", "Ljava/util/Enumeration;", "kotlin-stdlib"}, xs="kotlin/sequences/SequencesKt")
class SequencesKt__SequencesJVMKt
extends SequencesKt__SequenceBuilderKt {
    @InlineOnly
    private static final <T> Sequence<T> asSequence(Enumeration<T> $this$asSequence) {
        int $i$f$asSequence = 0;
        return SequencesKt.asSequence(CollectionsKt.iterator($this$asSequence));
    }
}

