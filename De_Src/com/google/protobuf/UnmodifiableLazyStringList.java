/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.protobuf;

import com.google.protobuf.ByteString;
import com.google.protobuf.LazyStringList;
import java.util.AbstractList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class UnmodifiableLazyStringList
extends AbstractList<String>
implements LazyStringList,
RandomAccess {
    private final LazyStringList list;

    public UnmodifiableLazyStringList(LazyStringList list) {
        this.list = list;
    }

    @Override
    public String get(int index) {
        return (String)this.list.get(index);
    }

    @Override
    public int size() {
        return this.list.size();
    }

    @Override
    public ByteString getByteString(int index) {
        return this.list.getByteString(index);
    }

    @Override
    public void add(ByteString element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void set(int index, ByteString element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAllByteString(Collection<? extends ByteString> element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public byte[] getByteArray(int index) {
        return this.list.getByteArray(index);
    }

    @Override
    public void add(byte[] element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void set(int index, byte[] element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAllByteArray(Collection<byte[]> element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<String> listIterator(final int index) {
        return new ListIterator<String>(){
            ListIterator<String> iter;
            {
                this.iter = UnmodifiableLazyStringList.this.list.listIterator(index);
            }

            @Override
            public boolean hasNext() {
                return this.iter.hasNext();
            }

            @Override
            public String next() {
                return this.iter.next();
            }

            @Override
            public boolean hasPrevious() {
                return this.iter.hasPrevious();
            }

            @Override
            public String previous() {
                return this.iter.previous();
            }

            @Override
            public int nextIndex() {
                return this.iter.nextIndex();
            }

            @Override
            public int previousIndex() {
                return this.iter.previousIndex();
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }

            @Override
            public void set(String o) {
                throw new UnsupportedOperationException();
            }

            @Override
            public void add(String o) {
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override
    public Iterator<String> iterator() {
        return new Iterator<String>(){
            Iterator<String> iter;
            {
                this.iter = UnmodifiableLazyStringList.this.list.iterator();
            }

            @Override
            public boolean hasNext() {
                return this.iter.hasNext();
            }

            @Override
            public String next() {
                return this.iter.next();
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override
    public List<?> getUnderlyingElements() {
        return this.list.getUnderlyingElements();
    }

    @Override
    public void mergeFrom(LazyStringList other) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<byte[]> asByteArrayList() {
        return Collections.unmodifiableList(this.list.asByteArrayList());
    }

    @Override
    public List<ByteString> asByteStringList() {
        return Collections.unmodifiableList(this.list.asByteStringList());
    }

    @Override
    public LazyStringList getUnmodifiableView() {
        return this;
    }
}

