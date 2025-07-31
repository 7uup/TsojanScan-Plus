/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin;

import java.util.Arrays;
import java.util.Collection;
import java.util.NoSuchElementException;
import kotlin.ExperimentalUnsignedTypes;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.SinceKotlin;
import kotlin.ULong;
import kotlin.collections.ArraysKt;
import kotlin.collections.ULongIterator;
import kotlin.jvm.internal.CollectionToArray;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0016\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\u0000\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0087@\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001-B\u0014\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0005\u0010\u0006B\u0014\b\u0001\u0012\u0006\u0010\u0007\u001a\u00020\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0005\u0010\tJ\u001b\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0002H\u0096\u0002\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0011\u0010\u0012J \u0010\u0013\u001a\u00020\u000f2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\u0016\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u0013\u0010\u0017\u001a\u00020\u000f2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u00d6\u0003J\u001e\u0010\u001a\u001a\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u0004H\u0086\u0002\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u001c\u0010\u001dJ\t\u0010\u001e\u001a\u00020\u0004H\u00d6\u0001J\u000f\u0010\u001f\u001a\u00020\u000fH\u0016\u00a2\u0006\u0004\b \u0010!J\u0010\u0010\"\u001a\u00020#H\u0096\u0002\u00a2\u0006\u0004\b$\u0010%J#\u0010&\u001a\u00020'2\u0006\u0010\u001b\u001a\u00020\u00042\u0006\u0010(\u001a\u00020\u0002H\u0086\u0002\u00f8\u0001\u0000\u00a2\u0006\u0004\b)\u0010*J\t\u0010+\u001a\u00020,H\u00d6\u0001R\u0014\u0010\u0003\u001a\u00020\u00048VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0016\u0010\u0007\u001a\u00020\b8\u0000X\u0081\u0004\u00a2\u0006\b\n\u0000\u0012\u0004\b\f\u0010\r\u00f8\u0001\u0000\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!\u00a8\u0006."}, d2={"Lkotlin/ULongArray;", "", "Lkotlin/ULong;", "size", "", "constructor-impl", "(I)[J", "storage", "", "([J)[J", "getSize-impl", "([J)I", "getStorage$annotations", "()V", "contains", "", "element", "contains-VKZWuLQ", "([JJ)Z", "containsAll", "elements", "containsAll-impl", "([JLjava/util/Collection;)Z", "equals", "other", "", "get", "index", "get-s-VKNKU", "([JI)J", "hashCode", "isEmpty", "isEmpty-impl", "([J)Z", "iterator", "Lkotlin/collections/ULongIterator;", "iterator-impl", "([J)Lkotlin/collections/ULongIterator;", "set", "", "value", "set-k8EXiF4", "([JIJ)V", "toString", "", "Iterator", "kotlin-stdlib"})
@SinceKotlin(version="1.3")
@ExperimentalUnsignedTypes
public final class ULongArray
implements Collection<ULong>,
KMappedMarker {
    @NotNull
    private final long[] storage;

    public int getSize() {
        return ULongArray.getSize-impl(this.storage);
    }

    @NotNull
    public ULongIterator iterator() {
        return ULongArray.iterator-impl(this.storage);
    }

    public boolean contains-VKZWuLQ(long l) {
        return ULongArray.contains-VKZWuLQ(this.storage, l);
    }

    @Override
    public boolean containsAll(@NotNull Collection<? extends Object> collection) {
        return ULongArray.containsAll-impl(this.storage, collection);
    }

    @Override
    public boolean isEmpty() {
        return ULongArray.isEmpty-impl(this.storage);
    }

    @PublishedApi
    public static /* synthetic */ void getStorage$annotations() {
    }

    @PublishedApi
    private /* synthetic */ ULongArray(@NotNull long[] storage) {
        Intrinsics.checkNotNullParameter(storage, "storage");
        this.storage = storage;
    }

    public static final long get-s-VKNKU(long[] $this, int index) {
        long l = $this[index];
        boolean bl = false;
        return ULong.constructor-impl(l);
    }

    public static final void set-k8EXiF4(long[] $this, int index, long value) {
        long l = value;
        boolean bl = false;
        $this[index] = l;
    }

    public static int getSize-impl(long[] $this) {
        return $this.length;
    }

    @NotNull
    public static ULongIterator iterator-impl(long[] $this) {
        return new Iterator($this);
    }

    public static boolean contains-VKZWuLQ(long[] $this, long element) {
        long l = element;
        boolean bl = false;
        return ArraysKt.contains($this, l);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static boolean containsAll-impl(long[] $this, @NotNull Collection<ULong> elements) {
        boolean bl;
        Intrinsics.checkNotNullParameter(elements, "elements");
        Iterable $this$all$iv = elements;
        boolean $i$f$all = false;
        if (((Collection)$this$all$iv).isEmpty()) {
            return true;
        }
        java.util.Iterator iterator2 = $this$all$iv.iterator();
        do {
            Object element$iv;
            if (!iterator2.hasNext()) return true;
            Object it = element$iv = iterator2.next();
            boolean bl2 = false;
            if (!(it instanceof ULong)) return false;
            long l = ((ULong)it).unbox-impl();
            boolean bl3 = false;
            if (!ArraysKt.contains($this, l)) return false;
            bl = true;
        } while (bl);
        return false;
    }

    public static boolean isEmpty-impl(long[] $this) {
        return $this.length == 0;
    }

    @PublishedApi
    @NotNull
    public static long[] constructor-impl(@NotNull long[] storage) {
        Intrinsics.checkNotNullParameter(storage, "storage");
        return storage;
    }

    @NotNull
    public static long[] constructor-impl(int size) {
        return ULongArray.constructor-impl(new long[size]);
    }

    @NotNull
    public static final /* synthetic */ ULongArray box-impl(@NotNull long[] v) {
        Intrinsics.checkNotNullParameter(v, "v");
        return new ULongArray(v);
    }

    @NotNull
    public static String toString-impl(long[] lArray) {
        return "ULongArray(storage=" + Arrays.toString(lArray) + ")";
    }

    public static int hashCode-impl(long[] lArray) {
        return lArray != null ? Arrays.hashCode(lArray) : 0;
    }

    public static boolean equals-impl(long[] lArray, @Nullable Object object) {
        long[] lArray2;
        return object instanceof ULongArray && Intrinsics.areEqual(lArray, lArray2 = ((ULongArray)object).unbox-impl());
    }

    public static final boolean equals-impl0(@NotNull long[] p1, @NotNull long[] p2) {
        return Intrinsics.areEqual(p1, p2);
    }

    @NotNull
    public final /* synthetic */ long[] unbox-impl() {
        return this.storage;
    }

    public String toString() {
        return ULongArray.toString-impl(this.storage);
    }

    @Override
    public int hashCode() {
        return ULongArray.hashCode-impl(this.storage);
    }

    @Override
    public boolean equals(Object object) {
        return ULongArray.equals-impl(this.storage, object);
    }

    public boolean add-VKZWuLQ(long l) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override
    public boolean addAll(Collection<? extends ULong> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override
    public boolean remove(Object object) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override
    public boolean removeAll(Collection<? extends Object> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override
    public boolean retainAll(Collection<? extends Object> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override
    public /* synthetic */ boolean add(Object object) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override
    public Object[] toArray() {
        return CollectionToArray.toArray(this);
    }

    @Override
    public <T> T[] toArray(T[] TArray) {
        return CollectionToArray.toArray(this, TArray);
    }

    /*
     * Illegal identifiers - consider using --renameillegalidents true
     */
    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0016\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\bH\u0096\u0002J\u0015\u0010\t\u001a\u00020\nH\u0016\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u000b\u0010\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!\u00a8\u0006\r"}, d2={"Lkotlin/ULongArray$Iterator;", "Lkotlin/collections/ULongIterator;", "array", "", "([J)V", "index", "", "hasNext", "", "nextULong", "Lkotlin/ULong;", "nextULong-s-VKNKU", "()J", "kotlin-stdlib"})
    private static final class Iterator
    extends ULongIterator {
        private int index;
        private final long[] array;

        @Override
        public boolean hasNext() {
            return this.index < this.array.length;
        }

        @Override
        public long nextULong-s-VKNKU() {
            if (this.index >= this.array.length) {
                throw (Throwable)new NoSuchElementException(String.valueOf(this.index));
            }
            int n = this.index;
            this.index = n + 1;
            long l = this.array[n];
            boolean bl = false;
            return ULong.constructor-impl(l);
        }

        public Iterator(@NotNull long[] array) {
            Intrinsics.checkNotNullParameter(array, "array");
            this.array = array;
        }
    }
}

