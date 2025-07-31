/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.protobuf;

import com.google.protobuf.ByteString;
import com.google.protobuf.Internal;
import com.google.protobuf.LazyStringList;
import com.google.protobuf.UnmodifiableLazyStringList;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class LazyStringArrayList
extends AbstractList<String>
implements LazyStringList,
RandomAccess {
    public static final LazyStringList EMPTY = new LazyStringArrayList().getUnmodifiableView();
    private final List<Object> list;

    public LazyStringArrayList() {
        this.list = new ArrayList<Object>();
    }

    public LazyStringArrayList(LazyStringList from) {
        this.list = new ArrayList<Object>(from.size());
        this.addAll(from);
    }

    public LazyStringArrayList(List<String> from) {
        this.list = new ArrayList<String>(from);
    }

    @Override
    public String get(int index) {
        Object o = this.list.get(index);
        if (o instanceof String) {
            return (String)o;
        }
        if (o instanceof ByteString) {
            ByteString bs = (ByteString)o;
            String s2 = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.list.set(index, s2);
            }
            return s2;
        }
        byte[] ba = (byte[])o;
        String s3 = Internal.toStringUtf8(ba);
        if (Internal.isValidUtf8(ba)) {
            this.list.set(index, s3);
        }
        return s3;
    }

    @Override
    public int size() {
        return this.list.size();
    }

    @Override
    public String set(int index, String s2) {
        Object o = this.list.set(index, s2);
        return LazyStringArrayList.asString(o);
    }

    @Override
    public void add(int index, String element) {
        this.list.add(index, element);
        ++this.modCount;
    }

    @Override
    public boolean addAll(Collection<? extends String> c) {
        return this.addAll(this.size(), c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends String> c) {
        Collection<? extends String> collection = c instanceof LazyStringList ? ((LazyStringList)c).getUnderlyingElements() : c;
        boolean ret = this.list.addAll(index, collection);
        ++this.modCount;
        return ret;
    }

    @Override
    public boolean addAllByteString(Collection<? extends ByteString> values2) {
        boolean ret = this.list.addAll(values2);
        ++this.modCount;
        return ret;
    }

    @Override
    public boolean addAllByteArray(Collection<byte[]> c) {
        boolean ret = this.list.addAll(c);
        ++this.modCount;
        return ret;
    }

    @Override
    public String remove(int index) {
        Object o = this.list.remove(index);
        ++this.modCount;
        return LazyStringArrayList.asString(o);
    }

    @Override
    public void clear() {
        this.list.clear();
        ++this.modCount;
    }

    @Override
    public void add(ByteString element) {
        this.list.add(element);
        ++this.modCount;
    }

    @Override
    public void add(byte[] element) {
        this.list.add(element);
        ++this.modCount;
    }

    @Override
    public ByteString getByteString(int index) {
        Object o = this.list.get(index);
        ByteString b = LazyStringArrayList.asByteString(o);
        if (b != o) {
            this.list.set(index, b);
        }
        return b;
    }

    @Override
    public byte[] getByteArray(int index) {
        Object o = this.list.get(index);
        byte[] b = LazyStringArrayList.asByteArray(o);
        if (b != o) {
            this.list.set(index, b);
        }
        return b;
    }

    @Override
    public void set(int index, ByteString s2) {
        this.list.set(index, s2);
    }

    @Override
    public void set(int index, byte[] s2) {
        this.list.set(index, s2);
    }

    private static String asString(Object o) {
        if (o instanceof String) {
            return (String)o;
        }
        if (o instanceof ByteString) {
            return ((ByteString)o).toStringUtf8();
        }
        return Internal.toStringUtf8((byte[])o);
    }

    private static ByteString asByteString(Object o) {
        if (o instanceof ByteString) {
            return (ByteString)o;
        }
        if (o instanceof String) {
            return ByteString.copyFromUtf8((String)o);
        }
        return ByteString.copyFrom((byte[])o);
    }

    private static byte[] asByteArray(Object o) {
        if (o instanceof byte[]) {
            return (byte[])o;
        }
        if (o instanceof String) {
            return Internal.toByteArray((String)o);
        }
        return ((ByteString)o).toByteArray();
    }

    @Override
    public List<?> getUnderlyingElements() {
        return Collections.unmodifiableList(this.list);
    }

    @Override
    public void mergeFrom(LazyStringList other) {
        for (Object o : other.getUnderlyingElements()) {
            if (o instanceof byte[]) {
                byte[] b = (byte[])o;
                this.list.add(Arrays.copyOf(b, b.length));
                continue;
            }
            this.list.add(o);
        }
    }

    @Override
    public List<byte[]> asByteArrayList() {
        return new ByteArrayListView(this.list);
    }

    @Override
    public List<ByteString> asByteStringList() {
        return new ByteStringListView(this.list);
    }

    @Override
    public LazyStringList getUnmodifiableView() {
        return new UnmodifiableLazyStringList(this);
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    private static class ByteStringListView
    extends AbstractList<ByteString>
    implements RandomAccess {
        private final List<Object> list;

        ByteStringListView(List<Object> list) {
            this.list = list;
        }

        @Override
        public ByteString get(int index) {
            Object o = this.list.get(index);
            ByteString b = LazyStringArrayList.asByteString(o);
            if (b != o) {
                this.list.set(index, b);
            }
            return b;
        }

        @Override
        public int size() {
            return this.list.size();
        }

        @Override
        public ByteString set(int index, ByteString s2) {
            Object o = this.list.set(index, s2);
            ++this.modCount;
            return LazyStringArrayList.asByteString(o);
        }

        @Override
        public void add(int index, ByteString s2) {
            this.list.add(index, s2);
            ++this.modCount;
        }

        @Override
        public ByteString remove(int index) {
            Object o = this.list.remove(index);
            ++this.modCount;
            return LazyStringArrayList.asByteString(o);
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    private static class ByteArrayListView
    extends AbstractList<byte[]>
    implements RandomAccess {
        private final List<Object> list;

        ByteArrayListView(List<Object> list) {
            this.list = list;
        }

        @Override
        public byte[] get(int index) {
            Object o = this.list.get(index);
            byte[] b = LazyStringArrayList.asByteArray(o);
            if (b != o) {
                this.list.set(index, b);
            }
            return b;
        }

        @Override
        public int size() {
            return this.list.size();
        }

        @Override
        public byte[] set(int index, byte[] s2) {
            Object o = this.list.set(index, s2);
            ++this.modCount;
            return LazyStringArrayList.asByteArray(o);
        }

        @Override
        public void add(int index, byte[] s2) {
            this.list.add(index, s2);
            ++this.modCount;
        }

        @Override
        public byte[] remove(int index) {
            Object o = this.list.remove(index);
            ++this.modCount;
            return LazyStringArrayList.asByteArray(o);
        }
    }
}

