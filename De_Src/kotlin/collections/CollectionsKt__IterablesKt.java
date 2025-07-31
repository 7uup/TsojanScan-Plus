/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.PublishedApi;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=5, xi=1, d1={"\u0000:\n\u0000\n\u0002\u0010\u001c\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010(\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u001e\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a.\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0014\b\u0004\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00050\u0004H\u0087\b\u00f8\u0001\u0000\u001a \u0010\u0006\u001a\u00020\u0007\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\b\u001a\u00020\u0007H\u0001\u001a\u001f\u0010\t\u001a\u0004\u0018\u00010\u0007\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0001H\u0001\u00a2\u0006\u0002\u0010\n\u001a\u001e\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00020\f\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0001H\u0000\u001a,\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\u00020\f\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001H\u0000\u001a\"\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0010\"\u0004\b\u0000\u0010\u0002*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00010\u0001\u001a\u001d\u0010\u0011\u001a\u00020\u0012\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\fH\u0002\u00a2\u0006\u0002\b\u0013\u001a@\u0010\u0014\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00160\u00100\u0015\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0016*\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00160\u00150\u0001\u0082\u0002\u0007\n\u0005\b\u009920\u0001\u00a8\u0006\u0017"}, d2={"Iterable", "", "T", "iterator", "Lkotlin/Function0;", "", "collectionSizeOrDefault", "", "default", "collectionSizeOrNull", "(Ljava/lang/Iterable;)Ljava/lang/Integer;", "convertToSetForSetOperation", "", "convertToSetForSetOperationWith", "source", "flatten", "", "safeToConvertToSet", "", "safeToConvertToSet$CollectionsKt__IterablesKt", "unzip", "Lkotlin/Pair;", "R", "kotlin-stdlib"}, xs="kotlin/collections/CollectionsKt")
class CollectionsKt__IterablesKt
extends CollectionsKt__CollectionsKt {
    @InlineOnly
    private static final <T> Iterable<T> Iterable(Function0<? extends Iterator<? extends T>> iterator2) {
        int $i$f$Iterable = 0;
        return new Iterable<T>(iterator2){
            final /* synthetic */ Function0 $iterator;

            @NotNull
            public Iterator<T> iterator() {
                return (Iterator)this.$iterator.invoke();
            }
            {
                this.$iterator = $captured_local_variable$0;
            }
        };
    }

    @PublishedApi
    @Nullable
    public static final <T> Integer collectionSizeOrNull(@NotNull Iterable<? extends T> $this$collectionSizeOrNull) {
        Intrinsics.checkNotNullParameter($this$collectionSizeOrNull, "$this$collectionSizeOrNull");
        return $this$collectionSizeOrNull instanceof Collection ? Integer.valueOf(((Collection)$this$collectionSizeOrNull).size()) : null;
    }

    @PublishedApi
    public static final <T> int collectionSizeOrDefault(@NotNull Iterable<? extends T> $this$collectionSizeOrDefault, int n) {
        Intrinsics.checkNotNullParameter($this$collectionSizeOrDefault, "$this$collectionSizeOrDefault");
        return $this$collectionSizeOrDefault instanceof Collection ? ((Collection)$this$collectionSizeOrDefault).size() : n;
    }

    private static final <T> boolean safeToConvertToSet$CollectionsKt__IterablesKt(Collection<? extends T> $this$safeToConvertToSet) {
        return $this$safeToConvertToSet.size() > 2 && $this$safeToConvertToSet instanceof ArrayList;
    }

    @NotNull
    public static final <T> Collection<T> convertToSetForSetOperationWith(@NotNull Iterable<? extends T> $this$convertToSetForSetOperationWith, @NotNull Iterable<? extends T> source2) {
        Intrinsics.checkNotNullParameter($this$convertToSetForSetOperationWith, "$this$convertToSetForSetOperationWith");
        Intrinsics.checkNotNullParameter(source2, "source");
        Iterable<? extends T> iterable = $this$convertToSetForSetOperationWith;
        return iterable instanceof Set ? (Collection)$this$convertToSetForSetOperationWith : (iterable instanceof Collection ? (source2 instanceof Collection && ((Collection)source2).size() < 2 ? (Collection)$this$convertToSetForSetOperationWith : (CollectionsKt__IterablesKt.safeToConvertToSet$CollectionsKt__IterablesKt((Collection)$this$convertToSetForSetOperationWith) ? (Collection)CollectionsKt.toHashSet($this$convertToSetForSetOperationWith) : (Collection)$this$convertToSetForSetOperationWith)) : (Collection)CollectionsKt.toHashSet($this$convertToSetForSetOperationWith));
    }

    @NotNull
    public static final <T> Collection<T> convertToSetForSetOperation(@NotNull Iterable<? extends T> $this$convertToSetForSetOperation) {
        Intrinsics.checkNotNullParameter($this$convertToSetForSetOperation, "$this$convertToSetForSetOperation");
        Iterable<? extends T> iterable = $this$convertToSetForSetOperation;
        return iterable instanceof Set ? (Collection)$this$convertToSetForSetOperation : (iterable instanceof Collection ? (CollectionsKt__IterablesKt.safeToConvertToSet$CollectionsKt__IterablesKt((Collection)$this$convertToSetForSetOperation) ? (Collection)CollectionsKt.toHashSet($this$convertToSetForSetOperation) : (Collection)$this$convertToSetForSetOperation) : (Collection)CollectionsKt.toHashSet($this$convertToSetForSetOperation));
    }

    @NotNull
    public static final <T> List<T> flatten(@NotNull Iterable<? extends Iterable<? extends T>> $this$flatten) {
        Intrinsics.checkNotNullParameter($this$flatten, "$this$flatten");
        ArrayList result = new ArrayList();
        for (Iterable<T> iterable : $this$flatten) {
            CollectionsKt.addAll((Collection)result, iterable);
        }
        return result;
    }

    @NotNull
    public static final <T, R> Pair<List<T>, List<R>> unzip(@NotNull Iterable<? extends Pair<? extends T, ? extends R>> $this$unzip) {
        Intrinsics.checkNotNullParameter($this$unzip, "$this$unzip");
        int expectedSize = CollectionsKt.collectionSizeOrDefault($this$unzip, 10);
        ArrayList<T> listT = new ArrayList<T>(expectedSize);
        ArrayList<R> listR = new ArrayList<R>(expectedSize);
        for (Pair<T, R> pair : $this$unzip) {
            listT.add(pair.getFirst());
            listR.add(pair.getSecond());
        }
        return TuplesKt.to(listT, listR);
    }
}

