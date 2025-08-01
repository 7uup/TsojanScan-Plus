/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.AbstractIndexedListIterator;
import com.google.common.collect.AbstractIterator;
import com.google.common.collect.CollectPreconditions;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.PeekingIterator;
import com.google.common.collect.TransformedIterator;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.collect.UnmodifiableListIterator;
import com.google.common.primitives.Ints;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Queue;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtCompatible(emulated=true)
public final class Iterators {
    private Iterators() {
    }

    static <T> UnmodifiableIterator<T> emptyIterator() {
        return Iterators.emptyListIterator();
    }

    static <T> UnmodifiableListIterator<T> emptyListIterator() {
        return ArrayItr.EMPTY;
    }

    static <T> Iterator<T> emptyModifiableIterator() {
        return EmptyModifiableIterator.INSTANCE;
    }

    public static <T> UnmodifiableIterator<T> unmodifiableIterator(final Iterator<? extends T> iterator2) {
        Preconditions.checkNotNull(iterator2);
        if (iterator2 instanceof UnmodifiableIterator) {
            UnmodifiableIterator result = (UnmodifiableIterator)iterator2;
            return result;
        }
        return new UnmodifiableIterator<T>(){

            @Override
            public boolean hasNext() {
                return iterator2.hasNext();
            }

            @Override
            public T next() {
                return iterator2.next();
            }
        };
    }

    @Deprecated
    public static <T> UnmodifiableIterator<T> unmodifiableIterator(UnmodifiableIterator<T> iterator2) {
        return Preconditions.checkNotNull(iterator2);
    }

    public static int size(Iterator<?> iterator2) {
        long count = 0L;
        while (iterator2.hasNext()) {
            iterator2.next();
            ++count;
        }
        return Ints.saturatedCast(count);
    }

    public static boolean contains(Iterator<?> iterator2, @Nullable Object element) {
        if (element == null) {
            while (iterator2.hasNext()) {
                if (iterator2.next() != null) continue;
                return true;
            }
        } else {
            while (iterator2.hasNext()) {
                if (!element.equals(iterator2.next())) continue;
                return true;
            }
        }
        return false;
    }

    @CanIgnoreReturnValue
    public static boolean removeAll(Iterator<?> removeFrom, Collection<?> elementsToRemove) {
        Preconditions.checkNotNull(elementsToRemove);
        boolean result = false;
        while (removeFrom.hasNext()) {
            if (!elementsToRemove.contains(removeFrom.next())) continue;
            removeFrom.remove();
            result = true;
        }
        return result;
    }

    @CanIgnoreReturnValue
    public static <T> boolean removeIf(Iterator<T> removeFrom, Predicate<? super T> predicate) {
        Preconditions.checkNotNull(predicate);
        boolean modified = false;
        while (removeFrom.hasNext()) {
            if (!predicate.apply(removeFrom.next())) continue;
            removeFrom.remove();
            modified = true;
        }
        return modified;
    }

    @CanIgnoreReturnValue
    public static boolean retainAll(Iterator<?> removeFrom, Collection<?> elementsToRetain) {
        Preconditions.checkNotNull(elementsToRetain);
        boolean result = false;
        while (removeFrom.hasNext()) {
            if (elementsToRetain.contains(removeFrom.next())) continue;
            removeFrom.remove();
            result = true;
        }
        return result;
    }

    public static boolean elementsEqual(Iterator<?> iterator1, Iterator<?> iterator2) {
        while (iterator1.hasNext()) {
            Object o2;
            if (!iterator2.hasNext()) {
                return false;
            }
            Object o1 = iterator1.next();
            if (Objects.equal(o1, o2 = iterator2.next())) continue;
            return false;
        }
        return !iterator2.hasNext();
    }

    public static String toString(Iterator<?> iterator2) {
        StringBuilder sb = new StringBuilder().append('[');
        boolean first = true;
        while (iterator2.hasNext()) {
            if (!first) {
                sb.append(", ");
            }
            first = false;
            sb.append(iterator2.next());
        }
        return sb.append(']').toString();
    }

    public static <T> T getOnlyElement(Iterator<T> iterator2) {
        T first = iterator2.next();
        if (!iterator2.hasNext()) {
            return first;
        }
        StringBuilder sb = new StringBuilder().append("expected one element but was: <").append(first);
        for (int i = 0; i < 4 && iterator2.hasNext(); ++i) {
            sb.append(", ").append(iterator2.next());
        }
        if (iterator2.hasNext()) {
            sb.append(", ...");
        }
        sb.append('>');
        throw new IllegalArgumentException(sb.toString());
    }

    public static <T> @Nullable T getOnlyElement(Iterator<? extends T> iterator2, @Nullable T defaultValue) {
        return iterator2.hasNext() ? Iterators.getOnlyElement(iterator2) : defaultValue;
    }

    @GwtIncompatible
    public static <T> T[] toArray(Iterator<? extends T> iterator2, Class<T> type) {
        ArrayList<? extends T> list = Lists.newArrayList(iterator2);
        return Iterables.toArray(list, type);
    }

    @CanIgnoreReturnValue
    public static <T> boolean addAll(Collection<T> addTo, Iterator<? extends T> iterator2) {
        Preconditions.checkNotNull(addTo);
        Preconditions.checkNotNull(iterator2);
        boolean wasModified = false;
        while (iterator2.hasNext()) {
            wasModified |= addTo.add(iterator2.next());
        }
        return wasModified;
    }

    public static int frequency(Iterator<?> iterator2, @Nullable Object element) {
        int count = 0;
        while (Iterators.contains(iterator2, element)) {
            ++count;
        }
        return count;
    }

    public static <T> Iterator<T> cycle(final Iterable<T> iterable) {
        Preconditions.checkNotNull(iterable);
        return new Iterator<T>(){
            Iterator<T> iterator = Iterators.emptyModifiableIterator();

            @Override
            public boolean hasNext() {
                return this.iterator.hasNext() || iterable.iterator().hasNext();
            }

            @Override
            public T next() {
                if (!this.iterator.hasNext()) {
                    this.iterator = iterable.iterator();
                    if (!this.iterator.hasNext()) {
                        throw new NoSuchElementException();
                    }
                }
                return this.iterator.next();
            }

            @Override
            public void remove() {
                this.iterator.remove();
            }
        };
    }

    @SafeVarargs
    public static <T> Iterator<T> cycle(T ... elements) {
        return Iterators.cycle(Lists.newArrayList(elements));
    }

    private static <T> Iterator<T> consumingForArray(final T ... elements) {
        return new UnmodifiableIterator<T>(){
            int index = 0;

            @Override
            public boolean hasNext() {
                return this.index < elements.length;
            }

            @Override
            public T next() {
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }
                Object result = elements[this.index];
                elements[this.index] = null;
                ++this.index;
                return result;
            }
        };
    }

    public static <T> Iterator<T> concat(Iterator<? extends T> a, Iterator<? extends T> b) {
        Preconditions.checkNotNull(a);
        Preconditions.checkNotNull(b);
        return Iterators.concat(Iterators.consumingForArray(a, b));
    }

    public static <T> Iterator<T> concat(Iterator<? extends T> a, Iterator<? extends T> b, Iterator<? extends T> c) {
        Preconditions.checkNotNull(a);
        Preconditions.checkNotNull(b);
        Preconditions.checkNotNull(c);
        return Iterators.concat(Iterators.consumingForArray(a, b, c));
    }

    public static <T> Iterator<T> concat(Iterator<? extends T> a, Iterator<? extends T> b, Iterator<? extends T> c, Iterator<? extends T> d) {
        Preconditions.checkNotNull(a);
        Preconditions.checkNotNull(b);
        Preconditions.checkNotNull(c);
        Preconditions.checkNotNull(d);
        return Iterators.concat(Iterators.consumingForArray(a, b, c, d));
    }

    public static <T> Iterator<T> concat(Iterator<? extends T> ... inputs) {
        return Iterators.concatNoDefensiveCopy(Arrays.copyOf(inputs, inputs.length));
    }

    public static <T> Iterator<T> concat(Iterator<? extends Iterator<? extends T>> inputs) {
        return new ConcatenatedIterator(inputs);
    }

    static <T> Iterator<T> concatNoDefensiveCopy(Iterator<? extends T> ... inputs) {
        for (Iterator<? extends T> input : Preconditions.checkNotNull(inputs)) {
            Preconditions.checkNotNull(input);
        }
        return Iterators.concat(Iterators.consumingForArray(inputs));
    }

    public static <T> UnmodifiableIterator<List<T>> partition(Iterator<T> iterator2, int size) {
        return Iterators.partitionImpl(iterator2, size, false);
    }

    public static <T> UnmodifiableIterator<List<T>> paddedPartition(Iterator<T> iterator2, int size) {
        return Iterators.partitionImpl(iterator2, size, true);
    }

    private static <T> UnmodifiableIterator<List<T>> partitionImpl(final Iterator<T> iterator2, final int size, final boolean pad) {
        Preconditions.checkNotNull(iterator2);
        Preconditions.checkArgument(size > 0);
        return new UnmodifiableIterator<List<T>>(){

            @Override
            public boolean hasNext() {
                return iterator2.hasNext();
            }

            @Override
            public List<T> next() {
                int count;
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }
                Object[] array = new Object[size];
                for (count = 0; count < size && iterator2.hasNext(); ++count) {
                    array[count] = iterator2.next();
                }
                for (int i = count; i < size; ++i) {
                    array[i] = null;
                }
                List<Object> list = Collections.unmodifiableList(Arrays.asList(array));
                return pad || count == size ? list : list.subList(0, count);
            }
        };
    }

    public static <T> UnmodifiableIterator<T> filter(final Iterator<T> unfiltered, final Predicate<? super T> retainIfTrue) {
        Preconditions.checkNotNull(unfiltered);
        Preconditions.checkNotNull(retainIfTrue);
        return new AbstractIterator<T>(){

            @Override
            protected T computeNext() {
                while (unfiltered.hasNext()) {
                    Object element = unfiltered.next();
                    if (!retainIfTrue.apply(element)) continue;
                    return element;
                }
                return this.endOfData();
            }
        };
    }

    @GwtIncompatible
    public static <T> UnmodifiableIterator<T> filter(Iterator<?> unfiltered, Class<T> desiredType) {
        return Iterators.filter(unfiltered, Predicates.instanceOf(desiredType));
    }

    public static <T> boolean any(Iterator<T> iterator2, Predicate<? super T> predicate) {
        return Iterators.indexOf(iterator2, predicate) != -1;
    }

    public static <T> boolean all(Iterator<T> iterator2, Predicate<? super T> predicate) {
        Preconditions.checkNotNull(predicate);
        while (iterator2.hasNext()) {
            T element = iterator2.next();
            if (predicate.apply(element)) continue;
            return false;
        }
        return true;
    }

    public static <T> T find(Iterator<T> iterator2, Predicate<? super T> predicate) {
        Preconditions.checkNotNull(iterator2);
        Preconditions.checkNotNull(predicate);
        while (iterator2.hasNext()) {
            T t = iterator2.next();
            if (!predicate.apply(t)) continue;
            return t;
        }
        throw new NoSuchElementException();
    }

    public static <T> @Nullable T find(Iterator<? extends T> iterator2, Predicate<? super T> predicate, @Nullable T defaultValue) {
        Preconditions.checkNotNull(iterator2);
        Preconditions.checkNotNull(predicate);
        while (iterator2.hasNext()) {
            T t = iterator2.next();
            if (!predicate.apply(t)) continue;
            return t;
        }
        return defaultValue;
    }

    public static <T> Optional<T> tryFind(Iterator<T> iterator2, Predicate<? super T> predicate) {
        Preconditions.checkNotNull(iterator2);
        Preconditions.checkNotNull(predicate);
        while (iterator2.hasNext()) {
            T t = iterator2.next();
            if (!predicate.apply(t)) continue;
            return Optional.of(t);
        }
        return Optional.absent();
    }

    public static <T> int indexOf(Iterator<T> iterator2, Predicate<? super T> predicate) {
        Preconditions.checkNotNull(predicate, "predicate");
        int i = 0;
        while (iterator2.hasNext()) {
            T current = iterator2.next();
            if (predicate.apply(current)) {
                return i;
            }
            ++i;
        }
        return -1;
    }

    public static <F, T> Iterator<T> transform(Iterator<F> fromIterator, final Function<? super F, ? extends T> function) {
        Preconditions.checkNotNull(function);
        return new TransformedIterator<F, T>(fromIterator){

            @Override
            T transform(F from) {
                return function.apply(from);
            }
        };
    }

    public static <T> T get(Iterator<T> iterator2, int position) {
        Iterators.checkNonnegative(position);
        int skipped = Iterators.advance(iterator2, position);
        if (!iterator2.hasNext()) {
            throw new IndexOutOfBoundsException("position (" + position + ") must be less than the number of elements that remained (" + skipped + ")");
        }
        return iterator2.next();
    }

    public static <T> @Nullable T get(Iterator<? extends T> iterator2, int position, @Nullable T defaultValue) {
        Iterators.checkNonnegative(position);
        Iterators.advance(iterator2, position);
        return Iterators.getNext(iterator2, defaultValue);
    }

    static void checkNonnegative(int position) {
        if (position < 0) {
            throw new IndexOutOfBoundsException("position (" + position + ") must not be negative");
        }
    }

    public static <T> @Nullable T getNext(Iterator<? extends T> iterator2, @Nullable T defaultValue) {
        return iterator2.hasNext() ? iterator2.next() : defaultValue;
    }

    public static <T> T getLast(Iterator<T> iterator2) {
        T current;
        do {
            current = iterator2.next();
        } while (iterator2.hasNext());
        return current;
    }

    public static <T> @Nullable T getLast(Iterator<? extends T> iterator2, @Nullable T defaultValue) {
        return iterator2.hasNext() ? Iterators.getLast(iterator2) : defaultValue;
    }

    @CanIgnoreReturnValue
    public static int advance(Iterator<?> iterator2, int numberToAdvance) {
        int i;
        Preconditions.checkNotNull(iterator2);
        Preconditions.checkArgument(numberToAdvance >= 0, "numberToAdvance must be nonnegative");
        for (i = 0; i < numberToAdvance && iterator2.hasNext(); ++i) {
            iterator2.next();
        }
        return i;
    }

    public static <T> Iterator<T> limit(final Iterator<T> iterator2, final int limitSize) {
        Preconditions.checkNotNull(iterator2);
        Preconditions.checkArgument(limitSize >= 0, "limit is negative");
        return new Iterator<T>(){
            private int count;

            @Override
            public boolean hasNext() {
                return this.count < limitSize && iterator2.hasNext();
            }

            @Override
            public T next() {
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }
                ++this.count;
                return iterator2.next();
            }

            @Override
            public void remove() {
                iterator2.remove();
            }
        };
    }

    public static <T> Iterator<T> consumingIterator(final Iterator<T> iterator2) {
        Preconditions.checkNotNull(iterator2);
        return new UnmodifiableIterator<T>(){

            @Override
            public boolean hasNext() {
                return iterator2.hasNext();
            }

            @Override
            public T next() {
                Object next = iterator2.next();
                iterator2.remove();
                return next;
            }

            public String toString() {
                return "Iterators.consumingIterator(...)";
            }
        };
    }

    static <T> @Nullable T pollNext(Iterator<T> iterator2) {
        if (iterator2.hasNext()) {
            T result = iterator2.next();
            iterator2.remove();
            return result;
        }
        return null;
    }

    static void clear(Iterator<?> iterator2) {
        Preconditions.checkNotNull(iterator2);
        while (iterator2.hasNext()) {
            iterator2.next();
            iterator2.remove();
        }
    }

    @SafeVarargs
    public static <T> UnmodifiableIterator<T> forArray(T ... array) {
        return Iterators.forArray(array, 0, array.length, 0);
    }

    static <T> UnmodifiableListIterator<T> forArray(T[] array, int offset, int length, int index) {
        Preconditions.checkArgument(length >= 0);
        int end = offset + length;
        Preconditions.checkPositionIndexes(offset, end, array.length);
        Preconditions.checkPositionIndex(index, length);
        if (length == 0) {
            return Iterators.emptyListIterator();
        }
        return new ArrayItr<T>(array, offset, length, index);
    }

    public static <T> UnmodifiableIterator<T> singletonIterator(final @Nullable T value) {
        return new UnmodifiableIterator<T>(){
            boolean done;

            @Override
            public boolean hasNext() {
                return !this.done;
            }

            @Override
            public T next() {
                if (this.done) {
                    throw new NoSuchElementException();
                }
                this.done = true;
                return value;
            }
        };
    }

    public static <T> UnmodifiableIterator<T> forEnumeration(final Enumeration<T> enumeration) {
        Preconditions.checkNotNull(enumeration);
        return new UnmodifiableIterator<T>(){

            @Override
            public boolean hasNext() {
                return enumeration.hasMoreElements();
            }

            @Override
            public T next() {
                return enumeration.nextElement();
            }
        };
    }

    public static <T> Enumeration<T> asEnumeration(final Iterator<T> iterator2) {
        Preconditions.checkNotNull(iterator2);
        return new Enumeration<T>(){

            @Override
            public boolean hasMoreElements() {
                return iterator2.hasNext();
            }

            @Override
            public T nextElement() {
                return iterator2.next();
            }
        };
    }

    public static <T> PeekingIterator<T> peekingIterator(Iterator<? extends T> iterator2) {
        if (iterator2 instanceof PeekingImpl) {
            PeekingImpl peeking = (PeekingImpl)iterator2;
            return peeking;
        }
        return new PeekingImpl<T>(iterator2);
    }

    @Deprecated
    public static <T> PeekingIterator<T> peekingIterator(PeekingIterator<T> iterator2) {
        return Preconditions.checkNotNull(iterator2);
    }

    @Beta
    public static <T> UnmodifiableIterator<T> mergeSorted(Iterable<? extends Iterator<? extends T>> iterators, Comparator<? super T> comparator) {
        Preconditions.checkNotNull(iterators, "iterators");
        Preconditions.checkNotNull(comparator, "comparator");
        return new MergingIterator<T>(iterators, comparator);
    }

    static <T> ListIterator<T> cast(Iterator<T> iterator2) {
        return (ListIterator)iterator2;
    }

    private static class ConcatenatedIterator<T>
    implements Iterator<T> {
        private @Nullable Iterator<? extends T> toRemove;
        private Iterator<? extends T> iterator = Iterators.emptyIterator();
        private Iterator<? extends Iterator<? extends T>> topMetaIterator;
        private @Nullable Deque<Iterator<? extends Iterator<? extends T>>> metaIterators;

        ConcatenatedIterator(Iterator<? extends Iterator<? extends T>> metaIterator) {
            this.topMetaIterator = Preconditions.checkNotNull(metaIterator);
        }

        private @Nullable Iterator<? extends Iterator<? extends T>> getTopMetaIterator() {
            while (this.topMetaIterator == null || !this.topMetaIterator.hasNext()) {
                if (this.metaIterators != null && !this.metaIterators.isEmpty()) {
                    this.topMetaIterator = this.metaIterators.removeFirst();
                    continue;
                }
                return null;
            }
            return this.topMetaIterator;
        }

        @Override
        public boolean hasNext() {
            while (!Preconditions.checkNotNull(this.iterator).hasNext()) {
                this.topMetaIterator = this.getTopMetaIterator();
                if (this.topMetaIterator == null) {
                    return false;
                }
                this.iterator = this.topMetaIterator.next();
                if (!(this.iterator instanceof ConcatenatedIterator)) continue;
                ConcatenatedIterator topConcat = (ConcatenatedIterator)this.iterator;
                this.iterator = topConcat.iterator;
                if (this.metaIterators == null) {
                    this.metaIterators = new ArrayDeque<Iterator<? extends Iterator<? extends T>>>();
                }
                this.metaIterators.addFirst(this.topMetaIterator);
                if (topConcat.metaIterators != null) {
                    while (!topConcat.metaIterators.isEmpty()) {
                        this.metaIterators.addFirst(topConcat.metaIterators.removeLast());
                    }
                }
                this.topMetaIterator = topConcat.topMetaIterator;
            }
            return true;
        }

        @Override
        public T next() {
            if (this.hasNext()) {
                this.toRemove = this.iterator;
                return this.iterator.next();
            }
            throw new NoSuchElementException();
        }

        @Override
        public void remove() {
            CollectPreconditions.checkRemove(this.toRemove != null);
            this.toRemove.remove();
            this.toRemove = null;
        }
    }

    private static class MergingIterator<T>
    extends UnmodifiableIterator<T> {
        final Queue<PeekingIterator<T>> queue;

        public MergingIterator(Iterable<? extends Iterator<? extends T>> iterators, final Comparator<? super T> itemComparator) {
            Comparator heapComparator = new Comparator<PeekingIterator<T>>(){

                @Override
                public int compare(PeekingIterator<T> o1, PeekingIterator<T> o2) {
                    return itemComparator.compare(o1.peek(), o2.peek());
                }
            };
            this.queue = new PriorityQueue<PeekingIterator<T>>(2, heapComparator);
            for (Iterator<T> iterator2 : iterators) {
                if (!iterator2.hasNext()) continue;
                this.queue.add(Iterators.peekingIterator(iterator2));
            }
        }

        @Override
        public boolean hasNext() {
            return !this.queue.isEmpty();
        }

        @Override
        public T next() {
            PeekingIterator<T> nextIter = this.queue.remove();
            T next = nextIter.next();
            if (nextIter.hasNext()) {
                this.queue.add(nextIter);
            }
            return next;
        }
    }

    private static class PeekingImpl<E>
    implements PeekingIterator<E> {
        private final Iterator<? extends E> iterator;
        private boolean hasPeeked;
        private @Nullable E peekedElement;

        public PeekingImpl(Iterator<? extends E> iterator2) {
            this.iterator = Preconditions.checkNotNull(iterator2);
        }

        @Override
        public boolean hasNext() {
            return this.hasPeeked || this.iterator.hasNext();
        }

        @Override
        public E next() {
            if (!this.hasPeeked) {
                return this.iterator.next();
            }
            E result = this.peekedElement;
            this.hasPeeked = false;
            this.peekedElement = null;
            return result;
        }

        @Override
        public void remove() {
            Preconditions.checkState(!this.hasPeeked, "Can't remove after you've peeked at next");
            this.iterator.remove();
        }

        @Override
        public E peek() {
            if (!this.hasPeeked) {
                this.peekedElement = this.iterator.next();
                this.hasPeeked = true;
            }
            return this.peekedElement;
        }
    }

    private static final class ArrayItr<T>
    extends AbstractIndexedListIterator<T> {
        static final UnmodifiableListIterator<Object> EMPTY = new ArrayItr<Object>(new Object[0], 0, 0, 0);
        private final T[] array;
        private final int offset;

        ArrayItr(T[] array, int offset, int length, int index) {
            super(length, index);
            this.array = array;
            this.offset = offset;
        }

        @Override
        protected T get(int index) {
            return this.array[this.offset + index];
        }
    }

    private static enum EmptyModifiableIterator implements Iterator<Object>
    {
        INSTANCE;


        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public Object next() {
            throw new NoSuchElementException();
        }

        @Override
        public void remove() {
            CollectPreconditions.checkRemove(false);
        }
    }
}

