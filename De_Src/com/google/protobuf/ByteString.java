/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.protobuf;

import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Internal;
import com.google.protobuf.LiteralByteString;
import com.google.protobuf.RopeByteString;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public abstract class ByteString
implements Iterable<Byte> {
    static final int CONCATENATE_BY_COPY_SIZE = 128;
    static final int MIN_READ_FROM_CHUNK_SIZE = 256;
    static final int MAX_READ_FROM_CHUNK_SIZE = 8192;
    public static final ByteString EMPTY = new LiteralByteString(new byte[0]);

    ByteString() {
    }

    public abstract byte byteAt(int var1);

    public abstract ByteIterator iterator();

    public abstract int size();

    public boolean isEmpty() {
        return this.size() == 0;
    }

    public ByteString substring(int beginIndex) {
        return this.substring(beginIndex, this.size());
    }

    public abstract ByteString substring(int var1, int var2);

    public boolean startsWith(ByteString prefix) {
        return this.size() >= prefix.size() && this.substring(0, prefix.size()).equals(prefix);
    }

    public boolean endsWith(ByteString suffix) {
        return this.size() >= suffix.size() && this.substring(this.size() - suffix.size()).equals(suffix);
    }

    public static ByteString copyFrom(byte[] bytes, int offset, int size) {
        byte[] copy = new byte[size];
        System.arraycopy(bytes, offset, copy, 0, size);
        return new LiteralByteString(copy);
    }

    public static ByteString copyFrom(byte[] bytes) {
        return ByteString.copyFrom(bytes, 0, bytes.length);
    }

    public static ByteString copyFrom(ByteBuffer bytes, int size) {
        byte[] copy = new byte[size];
        bytes.get(copy);
        return new LiteralByteString(copy);
    }

    public static ByteString copyFrom(ByteBuffer bytes) {
        return ByteString.copyFrom(bytes, bytes.remaining());
    }

    public static ByteString copyFrom(String text, String charsetName) throws UnsupportedEncodingException {
        return new LiteralByteString(text.getBytes(charsetName));
    }

    public static ByteString copyFromUtf8(String text) {
        try {
            return new LiteralByteString(text.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UTF-8 not supported?", e);
        }
    }

    public static ByteString readFrom(InputStream streamToDrain) throws IOException {
        return ByteString.readFrom(streamToDrain, 256, 8192);
    }

    public static ByteString readFrom(InputStream streamToDrain, int chunkSize) throws IOException {
        return ByteString.readFrom(streamToDrain, chunkSize, chunkSize);
    }

    public static ByteString readFrom(InputStream streamToDrain, int minChunkSize, int maxChunkSize) throws IOException {
        ByteString chunk;
        ArrayList<ByteString> results = new ArrayList<ByteString>();
        int chunkSize = minChunkSize;
        while ((chunk = ByteString.readChunk(streamToDrain, chunkSize)) != null) {
            results.add(chunk);
            chunkSize = Math.min(chunkSize * 2, maxChunkSize);
        }
        return ByteString.copyFrom(results);
    }

    private static ByteString readChunk(InputStream in, int chunkSize) throws IOException {
        int bytesRead;
        int count;
        byte[] buf = new byte[chunkSize];
        for (bytesRead = 0; bytesRead < chunkSize && (count = in.read(buf, bytesRead, chunkSize - bytesRead)) != -1; bytesRead += count) {
        }
        if (bytesRead == 0) {
            return null;
        }
        return ByteString.copyFrom(buf, 0, bytesRead);
    }

    public ByteString concat(ByteString other) {
        int otherSize;
        int thisSize = this.size();
        if ((long)thisSize + (long)(otherSize = other.size()) >= Integer.MAX_VALUE) {
            int n = thisSize;
            int n2 = otherSize;
            throw new IllegalArgumentException(new StringBuilder(53).append("ByteString would be too long: ").append(n).append("+").append(n2).toString());
        }
        return RopeByteString.concatenate(this, other);
    }

    public static ByteString copyFrom(Iterable<ByteString> byteStrings) {
        ArrayList<ByteString> collection;
        if (!(byteStrings instanceof Collection)) {
            collection = new ArrayList<ByteString>();
            for (ByteString byteString : byteStrings) {
                collection.add(byteString);
            }
        } else {
            collection = (ArrayList<ByteString>)byteStrings;
        }
        ByteString result = collection.isEmpty() ? EMPTY : ByteString.balancedConcat(collection.iterator(), collection.size());
        return result;
    }

    private static ByteString balancedConcat(Iterator<ByteString> iterator2, int length) {
        ByteString result;
        assert (length >= 1);
        if (length == 1) {
            result = iterator2.next();
        } else {
            int halfLength = length >>> 1;
            ByteString left = ByteString.balancedConcat(iterator2, halfLength);
            ByteString right = ByteString.balancedConcat(iterator2, length - halfLength);
            result = left.concat(right);
        }
        return result;
    }

    public void copyTo(byte[] target, int offset) {
        this.copyTo(target, 0, offset, this.size());
    }

    public void copyTo(byte[] target, int sourceOffset, int targetOffset, int numberToCopy) {
        if (sourceOffset < 0) {
            int n = sourceOffset;
            throw new IndexOutOfBoundsException(new StringBuilder(30).append("Source offset < 0: ").append(n).toString());
        }
        if (targetOffset < 0) {
            int n = targetOffset;
            throw new IndexOutOfBoundsException(new StringBuilder(30).append("Target offset < 0: ").append(n).toString());
        }
        if (numberToCopy < 0) {
            int n = numberToCopy;
            throw new IndexOutOfBoundsException(new StringBuilder(23).append("Length < 0: ").append(n).toString());
        }
        if (sourceOffset + numberToCopy > this.size()) {
            int n = sourceOffset + numberToCopy;
            throw new IndexOutOfBoundsException(new StringBuilder(34).append("Source end offset < 0: ").append(n).toString());
        }
        if (targetOffset + numberToCopy > target.length) {
            int n = targetOffset + numberToCopy;
            throw new IndexOutOfBoundsException(new StringBuilder(34).append("Target end offset < 0: ").append(n).toString());
        }
        if (numberToCopy > 0) {
            this.copyToInternal(target, sourceOffset, targetOffset, numberToCopy);
        }
    }

    protected abstract void copyToInternal(byte[] var1, int var2, int var3, int var4);

    public abstract void copyTo(ByteBuffer var1);

    public byte[] toByteArray() {
        int size = this.size();
        if (size == 0) {
            return Internal.EMPTY_BYTE_ARRAY;
        }
        byte[] result = new byte[size];
        this.copyToInternal(result, 0, 0, size);
        return result;
    }

    public abstract void writeTo(OutputStream var1) throws IOException;

    void writeTo(OutputStream out, int sourceOffset, int numberToWrite) throws IOException {
        if (sourceOffset < 0) {
            int n = sourceOffset;
            throw new IndexOutOfBoundsException(new StringBuilder(30).append("Source offset < 0: ").append(n).toString());
        }
        if (numberToWrite < 0) {
            int n = numberToWrite;
            throw new IndexOutOfBoundsException(new StringBuilder(23).append("Length < 0: ").append(n).toString());
        }
        if (sourceOffset + numberToWrite > this.size()) {
            int n = sourceOffset + numberToWrite;
            throw new IndexOutOfBoundsException(new StringBuilder(39).append("Source end offset exceeded: ").append(n).toString());
        }
        if (numberToWrite > 0) {
            this.writeToInternal(out, sourceOffset, numberToWrite);
        }
    }

    abstract void writeToInternal(OutputStream var1, int var2, int var3) throws IOException;

    public abstract ByteBuffer asReadOnlyByteBuffer();

    public abstract List<ByteBuffer> asReadOnlyByteBufferList();

    public abstract String toString(String var1) throws UnsupportedEncodingException;

    public String toStringUtf8() {
        try {
            return this.toString("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UTF-8 not supported?", e);
        }
    }

    public abstract boolean isValidUtf8();

    protected abstract int partialIsValidUtf8(int var1, int var2, int var3);

    public abstract boolean equals(Object var1);

    public abstract int hashCode();

    public abstract InputStream newInput();

    public abstract CodedInputStream newCodedInput();

    public static Output newOutput(int initialCapacity) {
        return new Output(initialCapacity);
    }

    public static Output newOutput() {
        return new Output(128);
    }

    static CodedBuilder newCodedBuilder(int size) {
        return new CodedBuilder(size);
    }

    protected abstract int getTreeDepth();

    protected abstract boolean isBalanced();

    protected abstract int peekCachedHashCode();

    protected abstract int partialHash(int var1, int var2, int var3);

    public String toString() {
        return String.format("<ByteString@%s size=%d>", Integer.toHexString(System.identityHashCode(this)), this.size());
    }

    static final class CodedBuilder {
        private final CodedOutputStream output;
        private final byte[] buffer;

        private CodedBuilder(int size) {
            this.buffer = new byte[size];
            this.output = CodedOutputStream.newInstance(this.buffer);
        }

        public ByteString build() {
            this.output.checkNoSpaceLeft();
            return new LiteralByteString(this.buffer);
        }

        public CodedOutputStream getCodedOutput() {
            return this.output;
        }
    }

    public static final class Output
    extends OutputStream {
        private static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
        private final int initialCapacity;
        private final ArrayList<ByteString> flushedBuffers;
        private int flushedBuffersTotalBytes;
        private byte[] buffer;
        private int bufferPos;

        Output(int initialCapacity) {
            if (initialCapacity < 0) {
                throw new IllegalArgumentException("Buffer size < 0");
            }
            this.initialCapacity = initialCapacity;
            this.flushedBuffers = new ArrayList();
            this.buffer = new byte[initialCapacity];
        }

        public synchronized void write(int b) {
            if (this.bufferPos == this.buffer.length) {
                this.flushFullBuffer(1);
            }
            this.buffer[this.bufferPos++] = (byte)b;
        }

        public synchronized void write(byte[] b, int offset, int length) {
            if (length <= this.buffer.length - this.bufferPos) {
                System.arraycopy(b, offset, this.buffer, this.bufferPos, length);
                this.bufferPos += length;
            } else {
                int copySize = this.buffer.length - this.bufferPos;
                System.arraycopy(b, offset, this.buffer, this.bufferPos, copySize);
                this.flushFullBuffer(length -= copySize);
                System.arraycopy(b, offset += copySize, this.buffer, 0, length);
                this.bufferPos = length;
            }
        }

        public synchronized ByteString toByteString() {
            this.flushLastBuffer();
            return ByteString.copyFrom(this.flushedBuffers);
        }

        private byte[] copyArray(byte[] buffer, int length) {
            byte[] result = new byte[length];
            System.arraycopy(buffer, 0, result, 0, Math.min(buffer.length, length));
            return result;
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        public void writeTo(OutputStream out) throws IOException {
            int cachedBufferPos;
            byte[] cachedBuffer;
            ByteString[] cachedFlushBuffers;
            Output output = this;
            synchronized (output) {
                cachedFlushBuffers = this.flushedBuffers.toArray(new ByteString[this.flushedBuffers.size()]);
                cachedBuffer = this.buffer;
                cachedBufferPos = this.bufferPos;
            }
            for (ByteString byteString : cachedFlushBuffers) {
                byteString.writeTo(out);
            }
            out.write(this.copyArray(cachedBuffer, cachedBufferPos));
        }

        public synchronized int size() {
            return this.flushedBuffersTotalBytes + this.bufferPos;
        }

        public synchronized void reset() {
            this.flushedBuffers.clear();
            this.flushedBuffersTotalBytes = 0;
            this.bufferPos = 0;
        }

        public String toString() {
            return String.format("<ByteString.Output@%s size=%d>", Integer.toHexString(System.identityHashCode(this)), this.size());
        }

        private void flushFullBuffer(int minSize) {
            this.flushedBuffers.add(new LiteralByteString(this.buffer));
            this.flushedBuffersTotalBytes += this.buffer.length;
            int newSize = Math.max(this.initialCapacity, Math.max(minSize, this.flushedBuffersTotalBytes >>> 1));
            this.buffer = new byte[newSize];
            this.bufferPos = 0;
        }

        private void flushLastBuffer() {
            if (this.bufferPos < this.buffer.length) {
                if (this.bufferPos > 0) {
                    byte[] bufferCopy = this.copyArray(this.buffer, this.bufferPos);
                    this.flushedBuffers.add(new LiteralByteString(bufferCopy));
                }
            } else {
                this.flushedBuffers.add(new LiteralByteString(this.buffer));
                this.buffer = EMPTY_BYTE_ARRAY;
            }
            this.flushedBuffersTotalBytes += this.bufferPos;
            this.bufferPos = 0;
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static interface ByteIterator
    extends Iterator<Byte> {
        public byte nextByte();
    }
}

