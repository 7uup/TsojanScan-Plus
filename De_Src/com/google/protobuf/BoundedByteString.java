/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.protobuf;

import com.google.protobuf.ByteString;
import com.google.protobuf.LiteralByteString;
import java.util.NoSuchElementException;

class BoundedByteString
extends LiteralByteString {
    private final int bytesOffset;
    private final int bytesLength;

    BoundedByteString(byte[] bytes, int offset, int length) {
        super(bytes);
        if (offset < 0) {
            int n = offset;
            throw new IllegalArgumentException(new StringBuilder(29).append("Offset too small: ").append(n).toString());
        }
        if (length < 0) {
            int n = offset;
            throw new IllegalArgumentException(new StringBuilder(29).append("Length too small: ").append(n).toString());
        }
        if ((long)offset + (long)length > (long)bytes.length) {
            int n = offset;
            int n2 = length;
            throw new IllegalArgumentException(new StringBuilder(48).append("Offset+Length too large: ").append(n).append("+").append(n2).toString());
        }
        this.bytesOffset = offset;
        this.bytesLength = length;
    }

    public byte byteAt(int index) {
        if (index < 0) {
            int n = index;
            throw new ArrayIndexOutOfBoundsException(new StringBuilder(28).append("Index too small: ").append(n).toString());
        }
        if (index >= this.size()) {
            int n = index;
            int n2 = this.size();
            throw new ArrayIndexOutOfBoundsException(new StringBuilder(41).append("Index too large: ").append(n).append(", ").append(n2).toString());
        }
        return this.bytes[this.bytesOffset + index];
    }

    public int size() {
        return this.bytesLength;
    }

    protected int getOffsetIntoBytes() {
        return this.bytesOffset;
    }

    protected void copyToInternal(byte[] target, int sourceOffset, int targetOffset, int numberToCopy) {
        System.arraycopy(this.bytes, this.getOffsetIntoBytes() + sourceOffset, target, targetOffset, numberToCopy);
    }

    public ByteString.ByteIterator iterator() {
        return new BoundedByteIterator();
    }

    private class BoundedByteIterator
    implements ByteString.ByteIterator {
        private int position;
        private final int limit;

        private BoundedByteIterator() {
            this.position = BoundedByteString.this.getOffsetIntoBytes();
            this.limit = this.position + BoundedByteString.this.size();
        }

        public boolean hasNext() {
            return this.position < this.limit;
        }

        public Byte next() {
            return this.nextByte();
        }

        public byte nextByte() {
            if (this.position >= this.limit) {
                throw new NoSuchElementException();
            }
            return BoundedByteString.this.bytes[this.position++];
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}

