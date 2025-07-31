/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.protobuf;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.LazyStringList;
import com.google.protobuf.MessageLite;
import com.google.protobuf.UninitializedMessageException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;

public abstract class AbstractMessageLite
implements MessageLite {
    protected int memoizedHashCode = 0;

    public ByteString toByteString() {
        try {
            ByteString.CodedBuilder out = ByteString.newCodedBuilder(this.getSerializedSize());
            this.writeTo(out.getCodedOutput());
            return out.build();
        } catch (IOException e) {
            throw new RuntimeException("Serializing to a ByteString threw an IOException (should never happen).", e);
        }
    }

    public byte[] toByteArray() {
        try {
            byte[] result = new byte[this.getSerializedSize()];
            CodedOutputStream output = CodedOutputStream.newInstance(result);
            this.writeTo(output);
            output.checkNoSpaceLeft();
            return result;
        } catch (IOException e) {
            throw new RuntimeException("Serializing to a byte array threw an IOException (should never happen).", e);
        }
    }

    public void writeTo(OutputStream output) throws IOException {
        int bufferSize = CodedOutputStream.computePreferredBufferSize(this.getSerializedSize());
        CodedOutputStream codedOutput = CodedOutputStream.newInstance(output, bufferSize);
        this.writeTo(codedOutput);
        codedOutput.flush();
    }

    public void writeDelimitedTo(OutputStream output) throws IOException {
        int serialized = this.getSerializedSize();
        int bufferSize = CodedOutputStream.computePreferredBufferSize(CodedOutputStream.computeRawVarint32Size(serialized) + serialized);
        CodedOutputStream codedOutput = CodedOutputStream.newInstance(output, bufferSize);
        codedOutput.writeRawVarint32(serialized);
        this.writeTo(codedOutput);
        codedOutput.flush();
    }

    UninitializedMessageException newUninitializedMessageException() {
        return new UninitializedMessageException(this);
    }

    protected static void checkByteStringIsUtf8(ByteString byteString) throws IllegalArgumentException {
        if (!byteString.isValidUtf8()) {
            throw new IllegalArgumentException("Byte string is not UTF-8.");
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static abstract class Builder<BuilderType extends Builder>
    implements MessageLite.Builder {
        public abstract BuilderType clone();

        public BuilderType mergeFrom(CodedInputStream input) throws IOException {
            return (BuilderType)this.mergeFrom(input, ExtensionRegistryLite.getEmptyRegistry());
        }

        public abstract BuilderType mergeFrom(CodedInputStream var1, ExtensionRegistryLite var2) throws IOException;

        public BuilderType mergeFrom(ByteString data) throws InvalidProtocolBufferException {
            try {
                CodedInputStream input = data.newCodedInput();
                this.mergeFrom(input);
                input.checkLastTagWas(0);
                return (BuilderType)this;
            } catch (InvalidProtocolBufferException e) {
                throw e;
            } catch (IOException e) {
                throw new RuntimeException("Reading from a ByteString threw an IOException (should never happen).", e);
            }
        }

        public BuilderType mergeFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            try {
                CodedInputStream input = data.newCodedInput();
                this.mergeFrom(input, extensionRegistry);
                input.checkLastTagWas(0);
                return (BuilderType)this;
            } catch (InvalidProtocolBufferException e) {
                throw e;
            } catch (IOException e) {
                throw new RuntimeException("Reading from a ByteString threw an IOException (should never happen).", e);
            }
        }

        public BuilderType mergeFrom(byte[] data) throws InvalidProtocolBufferException {
            return (BuilderType)this.mergeFrom(data, 0, data.length);
        }

        public BuilderType mergeFrom(byte[] data, int off, int len) throws InvalidProtocolBufferException {
            try {
                CodedInputStream input = CodedInputStream.newInstance(data, off, len);
                this.mergeFrom(input);
                input.checkLastTagWas(0);
                return (BuilderType)this;
            } catch (InvalidProtocolBufferException e) {
                throw e;
            } catch (IOException e) {
                throw new RuntimeException("Reading from a byte array threw an IOException (should never happen).", e);
            }
        }

        public BuilderType mergeFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (BuilderType)this.mergeFrom(data, 0, data.length, extensionRegistry);
        }

        public BuilderType mergeFrom(byte[] data, int off, int len, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            try {
                CodedInputStream input = CodedInputStream.newInstance(data, off, len);
                this.mergeFrom(input, extensionRegistry);
                input.checkLastTagWas(0);
                return (BuilderType)this;
            } catch (InvalidProtocolBufferException e) {
                throw e;
            } catch (IOException e) {
                throw new RuntimeException("Reading from a byte array threw an IOException (should never happen).", e);
            }
        }

        public BuilderType mergeFrom(InputStream input) throws IOException {
            CodedInputStream codedInput = CodedInputStream.newInstance(input);
            this.mergeFrom(codedInput);
            codedInput.checkLastTagWas(0);
            return (BuilderType)this;
        }

        public BuilderType mergeFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            CodedInputStream codedInput = CodedInputStream.newInstance(input);
            this.mergeFrom(codedInput, extensionRegistry);
            codedInput.checkLastTagWas(0);
            return (BuilderType)this;
        }

        @Override
        public boolean mergeDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            int firstByte = input.read();
            if (firstByte == -1) {
                return false;
            }
            int size = CodedInputStream.readRawVarint32(firstByte, input);
            LimitedInputStream limitedInput = new LimitedInputStream(input, size);
            this.mergeFrom(limitedInput, extensionRegistry);
            return true;
        }

        @Override
        public boolean mergeDelimitedFrom(InputStream input) throws IOException {
            return this.mergeDelimitedFrom(input, ExtensionRegistryLite.getEmptyRegistry());
        }

        protected static UninitializedMessageException newUninitializedMessageException(MessageLite message) {
            return new UninitializedMessageException(message);
        }

        protected static <T> void addAll(Iterable<T> values2, Collection<? super T> list) {
            if (values2 instanceof LazyStringList) {
                Builder.checkForNullValues(((LazyStringList)values2).getUnderlyingElements());
                list.addAll((Collection)values2);
            } else if (values2 instanceof Collection) {
                Builder.checkForNullValues(values2);
                list.addAll((Collection)values2);
            } else {
                for (T value : values2) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    list.add(value);
                }
            }
        }

        private static void checkForNullValues(Iterable<?> values2) {
            for (Object value : values2) {
                if (value != null) continue;
                throw new NullPointerException();
            }
        }

        static final class LimitedInputStream
        extends FilterInputStream {
            private int limit;

            LimitedInputStream(InputStream in, int limit) {
                super(in);
                this.limit = limit;
            }

            public int available() throws IOException {
                return Math.min(super.available(), this.limit);
            }

            public int read() throws IOException {
                if (this.limit <= 0) {
                    return -1;
                }
                int result = super.read();
                if (result >= 0) {
                    --this.limit;
                }
                return result;
            }

            public int read(byte[] b, int off, int len) throws IOException {
                if (this.limit <= 0) {
                    return -1;
                }
                int result = super.read(b, off, len = Math.min(len, this.limit));
                if (result >= 0) {
                    this.limit -= result;
                }
                return result;
            }

            public long skip(long n) throws IOException {
                long result = super.skip(Math.min(n, (long)this.limit));
                if (result >= 0L) {
                    this.limit = (int)((long)this.limit - result);
                }
                return result;
            }
        }
    }
}

