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
import kotlin.UByte;
import kotlin.collections.ArraysKt;
import kotlin.collections.UByteIterator;
import kotlin.jvm.internal.CollectionToArray;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\u0000\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0087@\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001-B\u0014\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0005\u0010\u0006B\u0014\b\u0001\u0012\u0006\u0010\u0007\u001a\u00020\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0005\u0010\tJ\u001b\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0002H\u0096\u0002\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0011\u0010\u0012J \u0010\u0013\u001a\u00020\u000f2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\u0016\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u0013\u0010\u0017\u001a\u00020\u000f2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u00d6\u0003J\u001e\u0010\u001a\u001a\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u0004H\u0086\u0002\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u001c\u0010\u001dJ\t\u0010\u001e\u001a\u00020\u0004H\u00d6\u0001J\u000f\u0010\u001f\u001a\u00020\u000fH\u0016\u00a2\u0006\u0004\b \u0010!J\u0010\u0010\"\u001a\u00020#H\u0096\u0002\u00a2\u0006\u0004\b$\u0010%J#\u0010&\u001a\u00020'2\u0006\u0010\u001b\u001a\u00020\u00042\u0006\u0010(\u001a\u00020\u0002H\u0086\u0002\u00f8\u0001\u0000\u00a2\u0006\u0004\b)\u0010*J\t\u0010+\u001a\u00020,H\u00d6\u0001R\u0014\u0010\u0003\u001a\u00020\u00048VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0016\u0010\u0007\u001a\u00020\b8\u0000X\u0081\u0004\u00a2\u0006\b\n\u0000\u0012\u0004\b\f\u0010\r\u00f8\u0001\u0000\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!\u00a8\u0006."}, d2={"Lkotlin/UByteArray;", "", "Lkotlin/UByte;", "size", "", "constructor-impl", "(I)[B", "storage", "", "([B)[B", "getSize-impl", "([B)I", "getStorage$annotations", "()V", "contains", "", "element", "contains-7apg3OU", "([BB)Z", "containsAll", "elements", "containsAll-impl", "([BLjava/util/Collection;)Z", "equals", "other", "", "get", "index", "get-w2LRezQ", "([BI)B", "hashCode", "isEmpty", "isEmpty-impl", "([B)Z", "iterator", "Lkotlin/collections/UByteIterator;", "iterator-impl", "([B)Lkotlin/collections/UByteIterator;", "set", "", "value", "set-VurrAj0", "([BIB)V", "toString", "", "Iterator", "kotlin-stdlib"})
@SinceKotlin(version="1.3")
@ExperimentalUnsignedTypes
public final class UByteArray
implements Collection<UByte>,
KMappedMarker {
    @NotNull
    private final byte[] storage;

    public int getSize() {
        return UByteArray.getSize-impl(this.storage);
    }

    @NotNull
    public UByteIterator iterator() {
        return UByteArray.iterator-impl(this.storage);
    }

    public boolean contains-7apg3OU(byte by) {
        return UByteArray.contains-7apg3OU(this.storage, by);
    }

    @Override
    public boolean containsAll(@NotNull Collection<? extends Object> collection) {
        return UByteArray.containsAll-impl(this.storage, collection);
    }

    @Override
    public boolean isEmpty() {
        return UByteArray.isEmpty-impl(this.storage);
    }

    @PublishedApi
    public static /* synthetic */ void getStorage$annotations() {
    }

    @PublishedApi
    private /* synthetic */ UByteArray(@NotNull byte[] storage) {
        Intrinsics.checkNotNullParameter(storage, "storage");
        this.storage = storage;
    }

    public static final byte get-w2LRezQ(byte[] $this, int index) {
        byte by = $this[index];
        boolean bl = false;
        return UByte.constructor-impl(by);
    }

    public static final void set-VurrAj0(byte[] $this, int index, byte value) {
        byte by = value;
        boolean bl = false;
        $this[index] = by;
    }

    public static int getSize-impl(byte[] $this) {
        return $this.length;
    }

    @NotNull
    public static UByteIterator iterator-impl(byte[] $this) {
        return new Iterator($this);
    }

    public static boolean contains-7apg3OU(byte[] $this, byte element) {
        byte by = element;
        boolean bl = false;
        return ArraysKt.contains($this, by);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static boolean containsAll-impl(byte[] $this, @NotNull Collection<UByte> elements) {
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
            if (!(it instanceof UByte)) return false;
            byte by = ((UByte)it).unbox-impl();
            boolean bl3 = false;
            if (!ArraysKt.contains($this, by)) return false;
            bl = true;
        } while (bl);
        return false;
    }

    public static boolean isEmpty-impl(byte[] $this) {
        return $this.length == 0;
    }

    @PublishedApi
    @NotNull
    public static byte[] constructor-impl(@NotNull byte[] storage) {
        Intrinsics.checkNotNullParameter(storage, "storage");
        return storage;
    }

    @NotNull
    public static byte[] constructor-impl(int size) {
        return UByteArray.constructor-impl(new byte[size]);
    }

    @NotNull
    public static final /* synthetic */ UByteArray box-impl(@NotNull byte[] v) {
        Intrinsics.checkNotNullParameter(v, "v");
        return new UByteArray(v);
    }

    @NotNull
    public static String toString-impl(byte[] byArray) {
        return "UByteArray(storage=" + Arrays.toString(byArray) + ")";
    }

    public static int hashCode-impl(byte[] byArray) {
        return byArray != null ? Arrays.hashCode(byArray) : 0;
    }

    public static boolean equals-impl(byte[] byArray, @Nullable Object object) {
        byte[] byArray2;
        return object instanceof UByteArray && Intrinsics.areEqual(byArray, byArray2 = ((UByteArray)object).unbox-impl());
    }

    public static final boolean equals-impl0(@NotNull byte[] p1, @NotNull byte[] p2) {
        return Intrinsics.areEqual(p1, p2);
    }

    @NotNull
    public final /* synthetic */ byte[] unbox-impl() {
        return this.storage;
    }

    public String toString() {
        return UByteArray.toString-impl(this.storage);
    }

    @Override
    public int hashCode() {
        return UByteArray.hashCode-impl(this.storage);
    }

    @Override
    public boolean equals(Object object) {
        return UByteArray.equals-impl(this.storage, object);
    }

    public boolean add-7apg3OU(byte by) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override
    public boolean addAll(Collection<? extends UByte> collection) {
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
    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\bH\u0096\u0002J\u0015\u0010\t\u001a\u00020\nH\u0016\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u000b\u0010\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!\u00a8\u0006\r"}, d2={"Lkotlin/UByteArray$Iterator;", "Lkotlin/collections/UByteIterator;", "array", "", "([B)V", "index", "", "hasNext", "", "nextUByte", "Lkotlin/UByte;", "nextUByte-w2LRezQ", "()B", "kotlin-stdlib"})
    private static final class Iterator
    extends UByteIterator {
        private int index;
        private final byte[] array;

        @Override
        public boolean hasNext() {
            return this.index < this.array.length;
        }

        @Override
        public byte nextUByte-w2LRezQ() {
            if (this.index >= this.array.length) {
                throw (Throwable)new NoSuchElementException(String.valueOf(this.index));
            }
            int by = this.index;
            this.index = by + 1;
            byte by2 = this.array[by];
            boolean bl = false;
            return UByte.constructor-impl(by2);
        }

        public Iterator(@NotNull byte[] array) {
            Intrinsics.checkNotNullParameter(array, "array");
            this.array = array;
        }
    }
}

