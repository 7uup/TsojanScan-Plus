/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.protobuf;

import com.google.protobuf.ByteString;
import com.google.protobuf.LazyFieldLite;
import com.google.protobuf.MessageLite;
import com.google.protobuf.WireFormat;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

public final class CodedOutputStream {
    private final byte[] buffer;
    private final int limit;
    private int position;
    private int totalBytesWritten = 0;
    private final OutputStream output;
    public static final int DEFAULT_BUFFER_SIZE = 4096;
    public static final int LITTLE_ENDIAN_32_SIZE = 4;
    public static final int LITTLE_ENDIAN_64_SIZE = 8;

    static int computePreferredBufferSize(int dataLength) {
        if (dataLength > 4096) {
            return 4096;
        }
        return dataLength;
    }

    private CodedOutputStream(byte[] buffer, int offset, int length) {
        this.output = null;
        this.buffer = buffer;
        this.position = offset;
        this.limit = offset + length;
    }

    private CodedOutputStream(OutputStream output, byte[] buffer) {
        this.output = output;
        this.buffer = buffer;
        this.position = 0;
        this.limit = buffer.length;
    }

    public static CodedOutputStream newInstance(OutputStream output) {
        return CodedOutputStream.newInstance(output, 4096);
    }

    public static CodedOutputStream newInstance(OutputStream output, int bufferSize) {
        return new CodedOutputStream(output, new byte[bufferSize]);
    }

    public static CodedOutputStream newInstance(byte[] flatArray) {
        return CodedOutputStream.newInstance(flatArray, 0, flatArray.length);
    }

    public static CodedOutputStream newInstance(byte[] flatArray, int offset, int length) {
        return new CodedOutputStream(flatArray, offset, length);
    }

    public static CodedOutputStream newInstance(ByteBuffer byteBuffer) {
        return CodedOutputStream.newInstance(byteBuffer, 4096);
    }

    public static CodedOutputStream newInstance(ByteBuffer byteBuffer, int bufferSize) {
        return CodedOutputStream.newInstance(new ByteBufferOutputStream(byteBuffer), bufferSize);
    }

    public void writeDouble(int fieldNumber, double value) throws IOException {
        this.writeTag(fieldNumber, 1);
        this.writeDoubleNoTag(value);
    }

    public void writeFloat(int fieldNumber, float value) throws IOException {
        this.writeTag(fieldNumber, 5);
        this.writeFloatNoTag(value);
    }

    public void writeUInt64(int fieldNumber, long value) throws IOException {
        this.writeTag(fieldNumber, 0);
        this.writeUInt64NoTag(value);
    }

    public void writeInt64(int fieldNumber, long value) throws IOException {
        this.writeTag(fieldNumber, 0);
        this.writeInt64NoTag(value);
    }

    public void writeInt32(int fieldNumber, int value) throws IOException {
        this.writeTag(fieldNumber, 0);
        this.writeInt32NoTag(value);
    }

    public void writeFixed64(int fieldNumber, long value) throws IOException {
        this.writeTag(fieldNumber, 1);
        this.writeFixed64NoTag(value);
    }

    public void writeFixed32(int fieldNumber, int value) throws IOException {
        this.writeTag(fieldNumber, 5);
        this.writeFixed32NoTag(value);
    }

    public void writeBool(int fieldNumber, boolean value) throws IOException {
        this.writeTag(fieldNumber, 0);
        this.writeBoolNoTag(value);
    }

    public void writeString(int fieldNumber, String value) throws IOException {
        this.writeTag(fieldNumber, 2);
        this.writeStringNoTag(value);
    }

    public void writeGroup(int fieldNumber, MessageLite value) throws IOException {
        this.writeTag(fieldNumber, 3);
        this.writeGroupNoTag(value);
        this.writeTag(fieldNumber, 4);
    }

    @Deprecated
    public void writeUnknownGroup(int fieldNumber, MessageLite value) throws IOException {
        this.writeGroup(fieldNumber, value);
    }

    public void writeMessage(int fieldNumber, MessageLite value) throws IOException {
        this.writeTag(fieldNumber, 2);
        this.writeMessageNoTag(value);
    }

    public void writeBytes(int fieldNumber, ByteString value) throws IOException {
        this.writeTag(fieldNumber, 2);
        this.writeBytesNoTag(value);
    }

    public void writeByteArray(int fieldNumber, byte[] value) throws IOException {
        this.writeTag(fieldNumber, 2);
        this.writeByteArrayNoTag(value);
    }

    public void writeByteArray(int fieldNumber, byte[] value, int offset, int length) throws IOException {
        this.writeTag(fieldNumber, 2);
        this.writeByteArrayNoTag(value, offset, length);
    }

    public void writeByteBuffer(int fieldNumber, ByteBuffer value) throws IOException {
        this.writeTag(fieldNumber, 2);
        this.writeByteBufferNoTag(value);
    }

    public void writeUInt32(int fieldNumber, int value) throws IOException {
        this.writeTag(fieldNumber, 0);
        this.writeUInt32NoTag(value);
    }

    public void writeEnum(int fieldNumber, int value) throws IOException {
        this.writeTag(fieldNumber, 0);
        this.writeEnumNoTag(value);
    }

    public void writeSFixed32(int fieldNumber, int value) throws IOException {
        this.writeTag(fieldNumber, 5);
        this.writeSFixed32NoTag(value);
    }

    public void writeSFixed64(int fieldNumber, long value) throws IOException {
        this.writeTag(fieldNumber, 1);
        this.writeSFixed64NoTag(value);
    }

    public void writeSInt32(int fieldNumber, int value) throws IOException {
        this.writeTag(fieldNumber, 0);
        this.writeSInt32NoTag(value);
    }

    public void writeSInt64(int fieldNumber, long value) throws IOException {
        this.writeTag(fieldNumber, 0);
        this.writeSInt64NoTag(value);
    }

    public void writeMessageSetExtension(int fieldNumber, MessageLite value) throws IOException {
        this.writeTag(1, 3);
        this.writeUInt32(2, fieldNumber);
        this.writeMessage(3, value);
        this.writeTag(1, 4);
    }

    public void writeRawMessageSetExtension(int fieldNumber, ByteString value) throws IOException {
        this.writeTag(1, 3);
        this.writeUInt32(2, fieldNumber);
        this.writeBytes(3, value);
        this.writeTag(1, 4);
    }

    public void writeDoubleNoTag(double value) throws IOException {
        this.writeRawLittleEndian64(Double.doubleToRawLongBits(value));
    }

    public void writeFloatNoTag(float value) throws IOException {
        this.writeRawLittleEndian32(Float.floatToRawIntBits(value));
    }

    public void writeUInt64NoTag(long value) throws IOException {
        this.writeRawVarint64(value);
    }

    public void writeInt64NoTag(long value) throws IOException {
        this.writeRawVarint64(value);
    }

    public void writeInt32NoTag(int value) throws IOException {
        if (value >= 0) {
            this.writeRawVarint32(value);
        } else {
            this.writeRawVarint64(value);
        }
    }

    public void writeFixed64NoTag(long value) throws IOException {
        this.writeRawLittleEndian64(value);
    }

    public void writeFixed32NoTag(int value) throws IOException {
        this.writeRawLittleEndian32(value);
    }

    public void writeBoolNoTag(boolean value) throws IOException {
        this.writeRawByte(value ? 1 : 0);
    }

    public void writeStringNoTag(String value) throws IOException {
        byte[] bytes = value.getBytes("UTF-8");
        this.writeRawVarint32(bytes.length);
        this.writeRawBytes(bytes);
    }

    public void writeGroupNoTag(MessageLite value) throws IOException {
        value.writeTo(this);
    }

    @Deprecated
    public void writeUnknownGroupNoTag(MessageLite value) throws IOException {
        this.writeGroupNoTag(value);
    }

    public void writeMessageNoTag(MessageLite value) throws IOException {
        this.writeRawVarint32(value.getSerializedSize());
        value.writeTo(this);
    }

    public void writeBytesNoTag(ByteString value) throws IOException {
        this.writeRawVarint32(value.size());
        this.writeRawBytes(value);
    }

    public void writeByteArrayNoTag(byte[] value) throws IOException {
        this.writeRawVarint32(value.length);
        this.writeRawBytes(value);
    }

    public void writeByteArrayNoTag(byte[] value, int offset, int length) throws IOException {
        this.writeRawVarint32(length);
        this.writeRawBytes(value, offset, length);
    }

    public void writeByteBufferNoTag(ByteBuffer value) throws IOException {
        this.writeRawVarint32(value.capacity());
        this.writeRawBytes(value);
    }

    public void writeUInt32NoTag(int value) throws IOException {
        this.writeRawVarint32(value);
    }

    public void writeEnumNoTag(int value) throws IOException {
        this.writeInt32NoTag(value);
    }

    public void writeSFixed32NoTag(int value) throws IOException {
        this.writeRawLittleEndian32(value);
    }

    public void writeSFixed64NoTag(long value) throws IOException {
        this.writeRawLittleEndian64(value);
    }

    public void writeSInt32NoTag(int value) throws IOException {
        this.writeRawVarint32(CodedOutputStream.encodeZigZag32(value));
    }

    public void writeSInt64NoTag(long value) throws IOException {
        this.writeRawVarint64(CodedOutputStream.encodeZigZag64(value));
    }

    public static int computeDoubleSize(int fieldNumber, double value) {
        return CodedOutputStream.computeTagSize(fieldNumber) + CodedOutputStream.computeDoubleSizeNoTag(value);
    }

    public static int computeFloatSize(int fieldNumber, float value) {
        return CodedOutputStream.computeTagSize(fieldNumber) + CodedOutputStream.computeFloatSizeNoTag(value);
    }

    public static int computeUInt64Size(int fieldNumber, long value) {
        return CodedOutputStream.computeTagSize(fieldNumber) + CodedOutputStream.computeUInt64SizeNoTag(value);
    }

    public static int computeInt64Size(int fieldNumber, long value) {
        return CodedOutputStream.computeTagSize(fieldNumber) + CodedOutputStream.computeInt64SizeNoTag(value);
    }

    public static int computeInt32Size(int fieldNumber, int value) {
        return CodedOutputStream.computeTagSize(fieldNumber) + CodedOutputStream.computeInt32SizeNoTag(value);
    }

    public static int computeFixed64Size(int fieldNumber, long value) {
        return CodedOutputStream.computeTagSize(fieldNumber) + CodedOutputStream.computeFixed64SizeNoTag(value);
    }

    public static int computeFixed32Size(int fieldNumber, int value) {
        return CodedOutputStream.computeTagSize(fieldNumber) + CodedOutputStream.computeFixed32SizeNoTag(value);
    }

    public static int computeBoolSize(int fieldNumber, boolean value) {
        return CodedOutputStream.computeTagSize(fieldNumber) + CodedOutputStream.computeBoolSizeNoTag(value);
    }

    public static int computeStringSize(int fieldNumber, String value) {
        return CodedOutputStream.computeTagSize(fieldNumber) + CodedOutputStream.computeStringSizeNoTag(value);
    }

    public static int computeGroupSize(int fieldNumber, MessageLite value) {
        return CodedOutputStream.computeTagSize(fieldNumber) * 2 + CodedOutputStream.computeGroupSizeNoTag(value);
    }

    @Deprecated
    public static int computeUnknownGroupSize(int fieldNumber, MessageLite value) {
        return CodedOutputStream.computeGroupSize(fieldNumber, value);
    }

    public static int computeMessageSize(int fieldNumber, MessageLite value) {
        return CodedOutputStream.computeTagSize(fieldNumber) + CodedOutputStream.computeMessageSizeNoTag(value);
    }

    public static int computeBytesSize(int fieldNumber, ByteString value) {
        return CodedOutputStream.computeTagSize(fieldNumber) + CodedOutputStream.computeBytesSizeNoTag(value);
    }

    public static int computeByteArraySize(int fieldNumber, byte[] value) {
        return CodedOutputStream.computeTagSize(fieldNumber) + CodedOutputStream.computeByteArraySizeNoTag(value);
    }

    public static int computeByteBufferSize(int fieldNumber, ByteBuffer value) {
        return CodedOutputStream.computeTagSize(fieldNumber) + CodedOutputStream.computeByteBufferSizeNoTag(value);
    }

    public static int computeLazyFieldSize(int fieldNumber, LazyFieldLite value) {
        return CodedOutputStream.computeTagSize(fieldNumber) + CodedOutputStream.computeLazyFieldSizeNoTag(value);
    }

    public static int computeUInt32Size(int fieldNumber, int value) {
        return CodedOutputStream.computeTagSize(fieldNumber) + CodedOutputStream.computeUInt32SizeNoTag(value);
    }

    public static int computeEnumSize(int fieldNumber, int value) {
        return CodedOutputStream.computeTagSize(fieldNumber) + CodedOutputStream.computeEnumSizeNoTag(value);
    }

    public static int computeSFixed32Size(int fieldNumber, int value) {
        return CodedOutputStream.computeTagSize(fieldNumber) + CodedOutputStream.computeSFixed32SizeNoTag(value);
    }

    public static int computeSFixed64Size(int fieldNumber, long value) {
        return CodedOutputStream.computeTagSize(fieldNumber) + CodedOutputStream.computeSFixed64SizeNoTag(value);
    }

    public static int computeSInt32Size(int fieldNumber, int value) {
        return CodedOutputStream.computeTagSize(fieldNumber) + CodedOutputStream.computeSInt32SizeNoTag(value);
    }

    public static int computeSInt64Size(int fieldNumber, long value) {
        return CodedOutputStream.computeTagSize(fieldNumber) + CodedOutputStream.computeSInt64SizeNoTag(value);
    }

    public static int computeMessageSetExtensionSize(int fieldNumber, MessageLite value) {
        return CodedOutputStream.computeTagSize(1) * 2 + CodedOutputStream.computeUInt32Size(2, fieldNumber) + CodedOutputStream.computeMessageSize(3, value);
    }

    public static int computeRawMessageSetExtensionSize(int fieldNumber, ByteString value) {
        return CodedOutputStream.computeTagSize(1) * 2 + CodedOutputStream.computeUInt32Size(2, fieldNumber) + CodedOutputStream.computeBytesSize(3, value);
    }

    public static int computeLazyFieldMessageSetExtensionSize(int fieldNumber, LazyFieldLite value) {
        return CodedOutputStream.computeTagSize(1) * 2 + CodedOutputStream.computeUInt32Size(2, fieldNumber) + CodedOutputStream.computeLazyFieldSize(3, value);
    }

    public static int computeDoubleSizeNoTag(double value) {
        return 8;
    }

    public static int computeFloatSizeNoTag(float value) {
        return 4;
    }

    public static int computeUInt64SizeNoTag(long value) {
        return CodedOutputStream.computeRawVarint64Size(value);
    }

    public static int computeInt64SizeNoTag(long value) {
        return CodedOutputStream.computeRawVarint64Size(value);
    }

    public static int computeInt32SizeNoTag(int value) {
        if (value >= 0) {
            return CodedOutputStream.computeRawVarint32Size(value);
        }
        return 10;
    }

    public static int computeFixed64SizeNoTag(long value) {
        return 8;
    }

    public static int computeFixed32SizeNoTag(int value) {
        return 4;
    }

    public static int computeBoolSizeNoTag(boolean value) {
        return 1;
    }

    public static int computeStringSizeNoTag(String value) {
        try {
            byte[] bytes = value.getBytes("UTF-8");
            return CodedOutputStream.computeRawVarint32Size(bytes.length) + bytes.length;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UTF-8 not supported.", e);
        }
    }

    public static int computeGroupSizeNoTag(MessageLite value) {
        return value.getSerializedSize();
    }

    @Deprecated
    public static int computeUnknownGroupSizeNoTag(MessageLite value) {
        return CodedOutputStream.computeGroupSizeNoTag(value);
    }

    public static int computeMessageSizeNoTag(MessageLite value) {
        int size = value.getSerializedSize();
        return CodedOutputStream.computeRawVarint32Size(size) + size;
    }

    public static int computeLazyFieldSizeNoTag(LazyFieldLite value) {
        int size = value.getSerializedSize();
        return CodedOutputStream.computeRawVarint32Size(size) + size;
    }

    public static int computeBytesSizeNoTag(ByteString value) {
        return CodedOutputStream.computeRawVarint32Size(value.size()) + value.size();
    }

    public static int computeByteArraySizeNoTag(byte[] value) {
        return CodedOutputStream.computeRawVarint32Size(value.length) + value.length;
    }

    public static int computeByteBufferSizeNoTag(ByteBuffer value) {
        return CodedOutputStream.computeRawVarint32Size(value.capacity()) + value.capacity();
    }

    public static int computeUInt32SizeNoTag(int value) {
        return CodedOutputStream.computeRawVarint32Size(value);
    }

    public static int computeEnumSizeNoTag(int value) {
        return CodedOutputStream.computeInt32SizeNoTag(value);
    }

    public static int computeSFixed32SizeNoTag(int value) {
        return 4;
    }

    public static int computeSFixed64SizeNoTag(long value) {
        return 8;
    }

    public static int computeSInt32SizeNoTag(int value) {
        return CodedOutputStream.computeRawVarint32Size(CodedOutputStream.encodeZigZag32(value));
    }

    public static int computeSInt64SizeNoTag(long value) {
        return CodedOutputStream.computeRawVarint64Size(CodedOutputStream.encodeZigZag64(value));
    }

    private void refreshBuffer() throws IOException {
        if (this.output == null) {
            throw new OutOfSpaceException();
        }
        this.output.write(this.buffer, 0, this.position);
        this.position = 0;
    }

    public void flush() throws IOException {
        if (this.output != null) {
            this.refreshBuffer();
        }
    }

    public int spaceLeft() {
        if (this.output == null) {
            return this.limit - this.position;
        }
        throw new UnsupportedOperationException("spaceLeft() can only be called on CodedOutputStreams that are writing to a flat array.");
    }

    public void checkNoSpaceLeft() {
        if (this.spaceLeft() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }

    public int getTotalBytesWritten() {
        return this.totalBytesWritten;
    }

    public void writeRawByte(byte value) throws IOException {
        if (this.position == this.limit) {
            this.refreshBuffer();
        }
        this.buffer[this.position++] = value;
        ++this.totalBytesWritten;
    }

    public void writeRawByte(int value) throws IOException {
        this.writeRawByte((byte)value);
    }

    public void writeRawBytes(ByteString value) throws IOException {
        this.writeRawBytes(value, 0, value.size());
    }

    public void writeRawBytes(byte[] value) throws IOException {
        this.writeRawBytes(value, 0, value.length);
    }

    public void writeRawBytes(ByteBuffer value) throws IOException {
        if (value.hasArray()) {
            this.writeRawBytes(value.array(), value.arrayOffset(), value.capacity());
        } else {
            ByteBuffer duplicated = value.duplicate();
            duplicated.clear();
            this.writeRawBytesInternal(duplicated);
        }
    }

    private void writeRawBytesInternal(ByteBuffer value) throws IOException {
        int length = value.remaining();
        if (this.limit - this.position >= length) {
            value.get(this.buffer, this.position, length);
            this.position += length;
            this.totalBytesWritten += length;
        } else {
            int bytesWritten = this.limit - this.position;
            value.get(this.buffer, this.position, bytesWritten);
            length -= bytesWritten;
            this.position = this.limit;
            this.totalBytesWritten += bytesWritten;
            this.refreshBuffer();
            while (length > this.limit) {
                value.get(this.buffer, 0, this.limit);
                this.output.write(this.buffer, 0, this.limit);
                length -= this.limit;
                this.totalBytesWritten += this.limit;
            }
            value.get(this.buffer, 0, length);
            this.position = length;
            this.totalBytesWritten += length;
        }
    }

    public void writeRawBytes(byte[] value, int offset, int length) throws IOException {
        if (this.limit - this.position >= length) {
            System.arraycopy(value, offset, this.buffer, this.position, length);
            this.position += length;
            this.totalBytesWritten += length;
        } else {
            int bytesWritten = this.limit - this.position;
            System.arraycopy(value, offset, this.buffer, this.position, bytesWritten);
            offset += bytesWritten;
            this.position = this.limit;
            this.totalBytesWritten += bytesWritten;
            this.refreshBuffer();
            if ((length -= bytesWritten) <= this.limit) {
                System.arraycopy(value, offset, this.buffer, 0, length);
                this.position = length;
            } else {
                this.output.write(value, offset, length);
            }
            this.totalBytesWritten += length;
        }
    }

    public void writeRawBytes(ByteString value, int offset, int length) throws IOException {
        if (this.limit - this.position >= length) {
            value.copyTo(this.buffer, offset, this.position, length);
            this.position += length;
            this.totalBytesWritten += length;
        } else {
            int bytesWritten = this.limit - this.position;
            value.copyTo(this.buffer, offset, this.position, bytesWritten);
            offset += bytesWritten;
            this.position = this.limit;
            this.totalBytesWritten += bytesWritten;
            this.refreshBuffer();
            if ((length -= bytesWritten) <= this.limit) {
                value.copyTo(this.buffer, offset, 0, length);
                this.position = length;
            } else {
                value.writeTo(this.output, offset, length);
            }
            this.totalBytesWritten += length;
        }
    }

    public void writeTag(int fieldNumber, int wireType) throws IOException {
        this.writeRawVarint32(WireFormat.makeTag(fieldNumber, wireType));
    }

    public static int computeTagSize(int fieldNumber) {
        return CodedOutputStream.computeRawVarint32Size(WireFormat.makeTag(fieldNumber, 0));
    }

    public void writeRawVarint32(int value) throws IOException {
        while (true) {
            if ((value & 0xFFFFFF80) == 0) {
                this.writeRawByte(value);
                return;
            }
            this.writeRawByte(value & 0x7F | 0x80);
            value >>>= 7;
        }
    }

    public static int computeRawVarint32Size(int value) {
        if ((value & 0xFFFFFF80) == 0) {
            return 1;
        }
        if ((value & 0xFFFFC000) == 0) {
            return 2;
        }
        if ((value & 0xFFE00000) == 0) {
            return 3;
        }
        if ((value & 0xF0000000) == 0) {
            return 4;
        }
        return 5;
    }

    public void writeRawVarint64(long value) throws IOException {
        while (true) {
            if ((value & 0xFFFFFFFFFFFFFF80L) == 0L) {
                this.writeRawByte((int)value);
                return;
            }
            this.writeRawByte((int)value & 0x7F | 0x80);
            value >>>= 7;
        }
    }

    public static int computeRawVarint64Size(long value) {
        if ((value & 0xFFFFFFFFFFFFFF80L) == 0L) {
            return 1;
        }
        if ((value & 0xFFFFFFFFFFFFC000L) == 0L) {
            return 2;
        }
        if ((value & 0xFFFFFFFFFFE00000L) == 0L) {
            return 3;
        }
        if ((value & 0xFFFFFFFFF0000000L) == 0L) {
            return 4;
        }
        if ((value & 0xFFFFFFF800000000L) == 0L) {
            return 5;
        }
        if ((value & 0xFFFFFC0000000000L) == 0L) {
            return 6;
        }
        if ((value & 0xFFFE000000000000L) == 0L) {
            return 7;
        }
        if ((value & 0xFF00000000000000L) == 0L) {
            return 8;
        }
        if ((value & Long.MIN_VALUE) == 0L) {
            return 9;
        }
        return 10;
    }

    public void writeRawLittleEndian32(int value) throws IOException {
        this.writeRawByte(value & 0xFF);
        this.writeRawByte(value >> 8 & 0xFF);
        this.writeRawByte(value >> 16 & 0xFF);
        this.writeRawByte(value >> 24 & 0xFF);
    }

    public void writeRawLittleEndian64(long value) throws IOException {
        this.writeRawByte((int)value & 0xFF);
        this.writeRawByte((int)(value >> 8) & 0xFF);
        this.writeRawByte((int)(value >> 16) & 0xFF);
        this.writeRawByte((int)(value >> 24) & 0xFF);
        this.writeRawByte((int)(value >> 32) & 0xFF);
        this.writeRawByte((int)(value >> 40) & 0xFF);
        this.writeRawByte((int)(value >> 48) & 0xFF);
        this.writeRawByte((int)(value >> 56) & 0xFF);
    }

    public static int encodeZigZag32(int n) {
        return n << 1 ^ n >> 31;
    }

    public static long encodeZigZag64(long n) {
        return n << 1 ^ n >> 63;
    }

    public static class OutOfSpaceException
    extends IOException {
        private static final long serialVersionUID = -6947486886997889499L;

        OutOfSpaceException() {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.");
        }
    }

    private static class ByteBufferOutputStream
    extends OutputStream {
        private final ByteBuffer byteBuffer;

        public ByteBufferOutputStream(ByteBuffer byteBuffer) {
            this.byteBuffer = byteBuffer;
        }

        public void write(int b) throws IOException {
            this.byteBuffer.put((byte)b);
        }

        public void write(byte[] data, int offset, int length) throws IOException {
            this.byteBuffer.put(data, offset, length);
        }
    }
}

