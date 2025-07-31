/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.collections;

import java.util.Enumeration;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=5, xi=1, d1={"\u0000\u000e\n\u0000\n\u0002\u0010(\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u001f\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u0086\u0002\u00a8\u0006\u0004"}, d2={"iterator", "", "T", "Ljava/util/Enumeration;", "kotlin-stdlib"}, xs="kotlin/collections/CollectionsKt")
class CollectionsKt__IteratorsJVMKt
extends CollectionsKt__IterablesKt {
    @NotNull
    public static final <T> Iterator<T> iterator(@NotNull Enumeration<T> $this$iterator) {
        Intrinsics.checkNotNullParameter($this$iterator, "$this$iterator");
        return new Iterator<T>($this$iterator){
            final /* synthetic */ Enumeration $this_iterator;

            public boolean hasNext() {
                return this.$this_iterator.hasMoreElements();
            }

            public T next() {
                return (T)this.$this_iterator.nextElement();
            }
            {
                this.$this_iterator = $receiver;
            }

            public void remove() {
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
            }
        };
    }
}

