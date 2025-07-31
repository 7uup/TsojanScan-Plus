/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.collections.builders;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.RandomAccess;
import kotlin.Metadata;
import kotlin.collections.AbstractList;
import kotlin.collections.AbstractMutableList;
import kotlin.collections.ArrayDeque;
import kotlin.collections.ArraysKt;
import kotlin.collections.builders.ListBuilderKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMutableList;
import kotlin.jvm.internal.markers.KMutableListIterator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000j\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u001e\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\b\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0010)\n\u0002\b\u0002\n\u0002\u0010+\n\u0002\b\u0010\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\u00060\u0003j\u0002`\u00042\b\u0012\u0004\u0012\u0002H\u00010\u0005:\u0001LB\u0007\b\u0016\u00a2\u0006\u0002\u0010\u0006B\u000f\b\u0016\u0012\u0006\u0010\u0007\u001a\u00020\b\u00a2\u0006\u0002\u0010\tBM\b\u0002\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\u000b\u0012\u0006\u0010\f\u001a\u00020\b\u0012\u0006\u0010\r\u001a\u00020\b\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u000e\u0010\u0010\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0000\u0012\u000e\u0010\u0011\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0000\u00a2\u0006\u0002\u0010\u0012J\u0015\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00028\u0000H\u0016\u00a2\u0006\u0002\u0010\u0019J\u001d\u0010\u0017\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\b2\u0006\u0010\u0018\u001a\u00028\u0000H\u0016\u00a2\u0006\u0002\u0010\u001cJ\u001e\u0010\u001d\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\b2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00028\u00000\u001fH\u0016J\u0016\u0010\u001d\u001a\u00020\u000f2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00028\u00000\u001fH\u0016J&\u0010 \u001a\u00020\u001a2\u0006\u0010!\u001a\u00020\b2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00028\u00000\u001f2\u0006\u0010\"\u001a\u00020\bH\u0002J\u001d\u0010#\u001a\u00020\u001a2\u0006\u0010!\u001a\u00020\b2\u0006\u0010\u0018\u001a\u00028\u0000H\u0002\u00a2\u0006\u0002\u0010\u001cJ\f\u0010$\u001a\b\u0012\u0004\u0012\u00028\u00000%J\b\u0010&\u001a\u00020\u001aH\u0002J\b\u0010'\u001a\u00020\u001aH\u0016J\u0014\u0010(\u001a\u00020\u000f2\n\u0010)\u001a\u0006\u0012\u0002\b\u00030%H\u0002J\u0010\u0010*\u001a\u00020\u001a2\u0006\u0010+\u001a\u00020\bH\u0002J\u0010\u0010,\u001a\u00020\u001a2\u0006\u0010\"\u001a\u00020\bH\u0002J\u0013\u0010-\u001a\u00020\u000f2\b\u0010)\u001a\u0004\u0018\u00010.H\u0096\u0002J\u0016\u0010/\u001a\u00028\u00002\u0006\u0010\u001b\u001a\u00020\bH\u0096\u0002\u00a2\u0006\u0002\u00100J\b\u00101\u001a\u00020\bH\u0016J\u0015\u00102\u001a\u00020\b2\u0006\u0010\u0018\u001a\u00028\u0000H\u0016\u00a2\u0006\u0002\u00103J\u0018\u00104\u001a\u00020\u001a2\u0006\u0010!\u001a\u00020\b2\u0006\u0010\"\u001a\u00020\bH\u0002J\b\u00105\u001a\u00020\u000fH\u0016J\u000f\u00106\u001a\b\u0012\u0004\u0012\u00028\u000007H\u0096\u0002J\u0015\u00108\u001a\u00020\b2\u0006\u0010\u0018\u001a\u00028\u0000H\u0016\u00a2\u0006\u0002\u00103J\u000e\u00109\u001a\b\u0012\u0004\u0012\u00028\u00000:H\u0016J\u0016\u00109\u001a\b\u0012\u0004\u0012\u00028\u00000:2\u0006\u0010\u001b\u001a\u00020\bH\u0016J\u0015\u0010;\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00028\u0000H\u0016\u00a2\u0006\u0002\u0010\u0019J\u0016\u0010<\u001a\u00020\u000f2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00028\u00000\u001fH\u0016J\u0015\u0010=\u001a\u00028\u00002\u0006\u0010\u001b\u001a\u00020\bH\u0016\u00a2\u0006\u0002\u00100J\u0015\u0010>\u001a\u00028\u00002\u0006\u0010!\u001a\u00020\bH\u0002\u00a2\u0006\u0002\u00100J\u0018\u0010?\u001a\u00020\u001a2\u0006\u0010@\u001a\u00020\b2\u0006\u0010A\u001a\u00020\bH\u0002J\u0016\u0010B\u001a\u00020\u000f2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00028\u00000\u001fH\u0016J.\u0010C\u001a\u00020\b2\u0006\u0010@\u001a\u00020\b2\u0006\u0010A\u001a\u00020\b2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00028\u00000\u001f2\u0006\u0010D\u001a\u00020\u000fH\u0002J\u001e\u0010E\u001a\u00028\u00002\u0006\u0010\u001b\u001a\u00020\b2\u0006\u0010\u0018\u001a\u00028\u0000H\u0096\u0002\u00a2\u0006\u0002\u0010FJ\u001e\u0010G\u001a\b\u0012\u0004\u0012\u00028\u00000\u00022\u0006\u0010H\u001a\u00020\b2\u0006\u0010I\u001a\u00020\bH\u0016J\b\u0010J\u001a\u00020KH\u0016R\u0016\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\u000bX\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\u0013R\u0016\u0010\u0010\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0000X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0011\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0000X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\u00020\b8VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016\u00a8\u0006M"}, d2={"Lkotlin/collections/builders/ListBuilder;", "E", "", "Ljava/util/RandomAccess;", "Lkotlin/collections/RandomAccess;", "Lkotlin/collections/AbstractMutableList;", "()V", "initialCapacity", "", "(I)V", "array", "", "offset", "length", "isReadOnly", "", "backing", "root", "([Ljava/lang/Object;IIZLkotlin/collections/builders/ListBuilder;Lkotlin/collections/builders/ListBuilder;)V", "[Ljava/lang/Object;", "size", "getSize", "()I", "add", "element", "(Ljava/lang/Object;)Z", "", "index", "(ILjava/lang/Object;)V", "addAll", "elements", "", "addAllInternal", "i", "n", "addAtInternal", "build", "", "checkIsMutable", "clear", "contentEquals", "other", "ensureCapacity", "minCapacity", "ensureExtraCapacity", "equals", "", "get", "(I)Ljava/lang/Object;", "hashCode", "indexOf", "(Ljava/lang/Object;)I", "insertAtInternal", "isEmpty", "iterator", "", "lastIndexOf", "listIterator", "", "remove", "removeAll", "removeAt", "removeAtInternal", "removeRangeInternal", "rangeOffset", "rangeLength", "retainAll", "retainOrRemoveAllInternal", "retain", "set", "(ILjava/lang/Object;)Ljava/lang/Object;", "subList", "fromIndex", "toIndex", "toString", "", "Itr", "kotlin-stdlib"})
public final class ListBuilder<E>
extends AbstractMutableList<E>
implements List<E>,
RandomAccess,
KMutableList {
    private E[] array;
    private int offset;
    private int length;
    private boolean isReadOnly;
    private final ListBuilder<E> backing;
    private final ListBuilder<E> root;

    @NotNull
    public final List<E> build() {
        if (this.backing != null) {
            throw (Throwable)new IllegalStateException();
        }
        this.checkIsMutable();
        this.isReadOnly = true;
        return this;
    }

    @Override
    public int getSize() {
        return this.length;
    }

    @Override
    public boolean isEmpty() {
        return this.length == 0;
    }

    @Override
    public E get(int index) {
        AbstractList.Companion.checkElementIndex$kotlin_stdlib(index, this.length);
        return this.array[this.offset + index];
    }

    @Override
    public E set(int index, E element) {
        this.checkIsMutable();
        AbstractList.Companion.checkElementIndex$kotlin_stdlib(index, this.length);
        E old = this.array[this.offset + index];
        this.array[this.offset + index] = element;
        return old;
    }

    @Override
    public int indexOf(Object element) {
        for (int i = 0; i < this.length; ++i) {
            if (!Intrinsics.areEqual(this.array[this.offset + i], element)) continue;
            return i;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object element) {
        for (int i = this.length - 1; i >= 0; --i) {
            if (!Intrinsics.areEqual(this.array[this.offset + i], element)) continue;
            return i;
        }
        return -1;
    }

    @Override
    @NotNull
    public Iterator<E> iterator() {
        return new Itr(this, 0);
    }

    @Override
    @NotNull
    public ListIterator<E> listIterator() {
        return new Itr(this, 0);
    }

    @Override
    @NotNull
    public ListIterator<E> listIterator(int index) {
        AbstractList.Companion.checkPositionIndex$kotlin_stdlib(index, this.length);
        return new Itr(this, index);
    }

    @Override
    public boolean add(E element) {
        this.checkIsMutable();
        this.addAtInternal(this.offset + this.length, element);
        return true;
    }

    @Override
    public void add(int index, E element) {
        this.checkIsMutable();
        AbstractList.Companion.checkPositionIndex$kotlin_stdlib(index, this.length);
        this.addAtInternal(this.offset + index, element);
    }

    @Override
    public boolean addAll(@NotNull Collection<? extends E> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        this.checkIsMutable();
        int n = elements.size();
        this.addAllInternal(this.offset + this.length, elements, n);
        return n > 0;
    }

    @Override
    public boolean addAll(int index, @NotNull Collection<? extends E> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        this.checkIsMutable();
        AbstractList.Companion.checkPositionIndex$kotlin_stdlib(index, this.length);
        int n = elements.size();
        this.addAllInternal(this.offset + index, elements, n);
        return n > 0;
    }

    @Override
    public void clear() {
        this.checkIsMutable();
        this.removeRangeInternal(this.offset, this.length);
    }

    @Override
    public E removeAt(int index) {
        this.checkIsMutable();
        AbstractList.Companion.checkElementIndex$kotlin_stdlib(index, this.length);
        return this.removeAtInternal(this.offset + index);
    }

    @Override
    public boolean remove(Object element) {
        this.checkIsMutable();
        int i = this.indexOf(element);
        if (i >= 0) {
            this.remove(i);
        }
        return i >= 0;
    }

    @Override
    public boolean removeAll(@NotNull Collection<? extends Object> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        this.checkIsMutable();
        return this.retainOrRemoveAllInternal(this.offset, this.length, elements, false) > 0;
    }

    @Override
    public boolean retainAll(@NotNull Collection<? extends Object> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        this.checkIsMutable();
        return this.retainOrRemoveAllInternal(this.offset, this.length, elements, true) > 0;
    }

    @Override
    @NotNull
    public List<E> subList(int fromIndex, int toIndex) {
        AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(fromIndex, toIndex, this.length);
        ListBuilder listBuilder = this.root;
        if (listBuilder == null) {
            listBuilder = this;
        }
        return new ListBuilder<E>(this.array, this.offset + fromIndex, toIndex - fromIndex, this.isReadOnly, this, listBuilder);
    }

    private final void ensureCapacity(int minCapacity) {
        if (this.backing != null) {
            throw (Throwable)new IllegalStateException();
        }
        if (minCapacity > this.array.length) {
            int newSize = ArrayDeque.Companion.newCapacity$kotlin_stdlib(this.array.length, minCapacity);
            this.array = ListBuilderKt.copyOfUninitializedElements(this.array, newSize);
        }
    }

    @Override
    public boolean equals(@Nullable Object other) {
        return other == this || other instanceof List && this.contentEquals((List)other);
    }

    @Override
    public int hashCode() {
        return ListBuilderKt.access$subarrayContentHashCode(this.array, this.offset, this.length);
    }

    @Override
    @NotNull
    public String toString() {
        return ListBuilderKt.access$subarrayContentToString(this.array, this.offset, this.length);
    }

    private final void checkIsMutable() {
        if (this.isReadOnly || this.root != null && this.root.isReadOnly) {
            throw (Throwable)new UnsupportedOperationException();
        }
    }

    private final void ensureExtraCapacity(int n) {
        this.ensureCapacity(this.length + n);
    }

    private final boolean contentEquals(List<?> other) {
        return ListBuilderKt.access$subarrayContentEquals(this.array, this.offset, this.length, other);
    }

    private final void insertAtInternal(int i, int n) {
        this.ensureExtraCapacity(n);
        int n2 = i + n;
        int n3 = this.offset + this.length;
        int n4 = i;
        ArraysKt.copyInto(this.array, this.array, n2, n4, n3);
        this.length += n;
    }

    private final void addAtInternal(int i, E element) {
        if (this.backing != null) {
            super.addAtInternal(i, element);
            this.array = this.backing.array;
            int n = this.length;
            this.length = n + 1;
        } else {
            this.insertAtInternal(i, 1);
            this.array[i] = element;
        }
    }

    private final void addAllInternal(int i, Collection<? extends E> elements, int n) {
        if (this.backing != null) {
            super.addAllInternal(i, elements, n);
            this.array = this.backing.array;
            this.length += n;
        } else {
            this.insertAtInternal(i, n);
            Iterator<E> it = elements.iterator();
            for (int j = 0; j < n; ++j) {
                this.array[i + j] = it.next();
            }
        }
    }

    private final E removeAtInternal(int i) {
        if (this.backing != null) {
            E old = super.removeAtInternal(i);
            int n = this.length;
            this.length = n + -1;
            return old;
        }
        E old = this.array[i];
        int n = i;
        int n2 = this.offset + this.length;
        int n3 = i + 1;
        ArraysKt.copyInto(this.array, this.array, n, n3, n2);
        ListBuilderKt.resetAt(this.array, this.offset + this.length - 1);
        n = this.length;
        this.length = n + -1;
        return old;
    }

    private final void removeRangeInternal(int rangeOffset, int rangeLength) {
        if (this.backing != null) {
            super.removeRangeInternal(rangeOffset, rangeLength);
        } else {
            int n = rangeOffset;
            int n2 = this.length;
            int n3 = rangeOffset + rangeLength;
            ArraysKt.copyInto(this.array, this.array, n, n3, n2);
            ListBuilderKt.resetRange(this.array, this.length - rangeLength, this.length);
        }
        this.length -= rangeLength;
    }

    private final int retainOrRemoveAllInternal(int rangeOffset, int rangeLength, Collection<? extends E> elements, boolean retain) {
        if (this.backing != null) {
            int removed = super.retainOrRemoveAllInternal(rangeOffset, rangeLength, elements, retain);
            this.length -= removed;
            return removed;
        }
        int i = 0;
        int j = 0;
        while (i < rangeLength) {
            if (elements.contains(this.array[rangeOffset + i]) == retain) {
                this.array[rangeOffset + j++] = this.array[rangeOffset + i++];
                continue;
            }
            ++i;
        }
        int removed = rangeLength - j;
        int n = rangeOffset + j;
        int n2 = this.length;
        int n3 = rangeOffset + rangeLength;
        ArraysKt.copyInto(this.array, this.array, n, n3, n2);
        ListBuilderKt.resetRange(this.array, this.length - removed, this.length);
        this.length -= removed;
        return removed;
    }

    private ListBuilder(E[] array, int offset, int length, boolean isReadOnly, ListBuilder<E> backing, ListBuilder<E> root) {
        this.array = array;
        this.offset = offset;
        this.length = length;
        this.isReadOnly = isReadOnly;
        this.backing = backing;
        this.root = root;
    }

    public ListBuilder() {
        this(10);
    }

    public ListBuilder(int initialCapacity) {
        this(ListBuilderKt.arrayOfUninitializedElements(initialCapacity), 0, 0, false, null, null);
    }

    public static final /* synthetic */ void access$setLength$p(ListBuilder $this, int n) {
        $this.length = n;
    }

    public static final /* synthetic */ void access$setArray$p(ListBuilder $this, Object[] objectArray) {
        $this.array = objectArray;
    }

    public static final /* synthetic */ void access$setOffset$p(ListBuilder $this, int n) {
        $this.offset = n;
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010+\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\t\b\u0002\u0018\u0000*\u0004\b\u0001\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u001d\b\u0016\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00010\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\u0015\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00028\u0001H\u0016\u00a2\u0006\u0002\u0010\fJ\t\u0010\r\u001a\u00020\u000eH\u0096\u0002J\b\u0010\u000f\u001a\u00020\u000eH\u0016J\u000e\u0010\u0010\u001a\u00028\u0001H\u0096\u0002\u00a2\u0006\u0002\u0010\u0011J\b\u0010\u0012\u001a\u00020\u0006H\u0016J\r\u0010\u0013\u001a\u00028\u0001H\u0016\u00a2\u0006\u0002\u0010\u0011J\b\u0010\u0014\u001a\u00020\u0006H\u0016J\b\u0010\u0015\u001a\u00020\nH\u0016J\u0015\u0010\u0016\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00028\u0001H\u0016\u00a2\u0006\u0002\u0010\fR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00010\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0017"}, d2={"Lkotlin/collections/builders/ListBuilder$Itr;", "E", "", "list", "Lkotlin/collections/builders/ListBuilder;", "index", "", "(Lkotlin/collections/builders/ListBuilder;I)V", "lastIndex", "add", "", "element", "(Ljava/lang/Object;)V", "hasNext", "", "hasPrevious", "next", "()Ljava/lang/Object;", "nextIndex", "previous", "previousIndex", "remove", "set", "kotlin-stdlib"})
    private static final class Itr<E>
    implements ListIterator<E>,
    KMutableListIterator {
        private final ListBuilder<E> list;
        private int index;
        private int lastIndex;

        @Override
        public boolean hasPrevious() {
            return this.index > 0;
        }

        @Override
        public boolean hasNext() {
            return this.index < ((ListBuilder)this.list).length;
        }

        @Override
        public int previousIndex() {
            return this.index - 1;
        }

        @Override
        public int nextIndex() {
            return this.index;
        }

        @Override
        public E previous() {
            if (this.index <= 0) {
                throw (Throwable)new NoSuchElementException();
            }
            Itr itr = this;
            itr.index += -1;
            this.lastIndex = itr.index;
            return (E)((ListBuilder)this.list).array[((ListBuilder)this.list).offset + this.lastIndex];
        }

        @Override
        public E next() {
            if (this.index >= ((ListBuilder)this.list).length) {
                throw (Throwable)new NoSuchElementException();
            }
            int n = this.index;
            this.index = n + 1;
            this.lastIndex = n;
            return (E)((ListBuilder)this.list).array[((ListBuilder)this.list).offset + this.lastIndex];
        }

        @Override
        public void set(E element) {
            boolean bl = this.lastIndex != -1;
            boolean bl2 = false;
            boolean bl3 = false;
            if (!bl) {
                boolean bl4 = false;
                String string = "Call next() or previous() before replacing element from the iterator.";
                throw (Throwable)new IllegalStateException(string.toString());
            }
            this.list.set(this.lastIndex, element);
        }

        @Override
        public void add(E element) {
            int n = this.index;
            this.index = n + 1;
            this.list.add(n, element);
            this.lastIndex = -1;
        }

        @Override
        public void remove() {
            boolean bl = this.lastIndex != -1;
            boolean bl2 = false;
            boolean bl3 = false;
            if (!bl) {
                boolean bl4 = false;
                String string = "Call next() or previous() before removing element from the iterator.";
                throw (Throwable)new IllegalStateException(string.toString());
            }
            this.list.remove(this.lastIndex);
            this.index = this.lastIndex;
            this.lastIndex = -1;
        }

        public Itr(@NotNull ListBuilder<E> list, int index) {
            Intrinsics.checkNotNullParameter(list, "list");
            this.list = list;
            this.index = index;
            this.lastIndex = -1;
        }
    }
}

