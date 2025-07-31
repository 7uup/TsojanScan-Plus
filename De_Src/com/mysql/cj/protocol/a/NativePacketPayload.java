/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.protocol.a;

import com.mysql.cj.Constants;
import com.mysql.cj.Messages;
import com.mysql.cj.exceptions.ExceptionFactory;
import com.mysql.cj.exceptions.WrongArgumentException;
import com.mysql.cj.protocol.Message;
import com.mysql.cj.protocol.a.NativeConstants;
import com.mysql.cj.util.StringUtils;

public class NativePacketPayload
implements Message {
    static final int NO_LENGTH_LIMIT = -1;
    public static final long NULL_LENGTH = -1L;
    public static final short TYPE_ID_ERROR = 255;
    public static final short TYPE_ID_EOF = 254;
    public static final short TYPE_ID_AUTH_SWITCH = 254;
    public static final short TYPE_ID_LOCAL_INFILE = 251;
    public static final short TYPE_ID_OK = 0;
    private int payloadLength = 0;
    private byte[] byteBuffer;
    private int position = 0;
    static final int MAX_BYTES_TO_DUMP = 1024;

    public String toString() {
        int numBytes = this.position <= this.payloadLength ? this.position : this.payloadLength;
        int numBytesToDump = numBytes < 1024 ? numBytes : 1024;
        this.position = 0;
        String dumped = StringUtils.dumpAsHex(this.readBytes(NativeConstants.StringLengthDataType.STRING_FIXED, numBytesToDump), numBytesToDump);
        if (numBytesToDump < numBytes) {
            return dumped + " ....(packet exceeds max. dump length)";
        }
        return dumped;
    }

    public String toSuperString() {
        return super.toString();
    }

    public NativePacketPayload(byte[] buf) {
        this.byteBuffer = buf;
        this.payloadLength = buf.length;
    }

    public NativePacketPayload(int size) {
        this.byteBuffer = new byte[size];
        this.payloadLength = size;
    }

    public int getCapacity() {
        return this.byteBuffer.length;
    }

    public final void ensureCapacity(int additionalData) {
        if (this.position + additionalData > this.byteBuffer.length) {
            int newLength = (int)((double)this.byteBuffer.length * 1.25);
            if (newLength < this.byteBuffer.length + additionalData) {
                newLength = this.byteBuffer.length + (int)((double)additionalData * 1.25);
            }
            if (newLength < this.byteBuffer.length) {
                newLength = this.byteBuffer.length + additionalData;
            }
            byte[] newBytes = new byte[newLength];
            System.arraycopy(this.byteBuffer, 0, newBytes, 0, this.byteBuffer.length);
            this.byteBuffer = newBytes;
        }
    }

    @Override
    public byte[] getByteBuffer() {
        return this.byteBuffer;
    }

    public void setByteBuffer(byte[] byteBufferToSet) {
        this.byteBuffer = byteBufferToSet;
    }

    public int getPayloadLength() {
        return this.payloadLength;
    }

    public void setPayloadLength(int bufLengthToSet) {
        if (bufLengthToSet > this.byteBuffer.length) {
            throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("Buffer.0"));
        }
        this.payloadLength = bufLengthToSet;
    }

    private void adjustPayloadLength() {
        if (this.position > this.payloadLength) {
            this.payloadLength = this.position;
        }
    }

    @Override
    public int getPosition() {
        return this.position;
    }

    public void setPosition(int positionToSet) {
        this.position = positionToSet;
    }

    public boolean isErrorPacket() {
        return (this.byteBuffer[0] & 0xFF) == 255;
    }

    public final boolean isEOFPacket() {
        return (this.byteBuffer[0] & 0xFF) == 254 && this.getPayloadLength() <= 5;
    }

    public final boolean isAuthMethodSwitchRequestPacket() {
        return (this.byteBuffer[0] & 0xFF) == 254;
    }

    public final boolean isOKPacket() {
        return (this.byteBuffer[0] & 0xFF) == 0;
    }

    public final boolean isResultSetOKPacket() {
        return (this.byteBuffer[0] & 0xFF) == 254 && this.getPayloadLength() < 0xFFFFFF;
    }

    public final boolean isAuthMoreData() {
        return (this.byteBuffer[0] & 0xFF) == 1;
    }

    public void writeInteger(NativeConstants.IntegerDataType type, long l) {
        switch (type) {
            case INT1: {
                this.ensureCapacity(1);
                byte[] b = this.byteBuffer;
                b[this.position++] = (byte)(l & 0xFFL);
                break;
            }
            case INT2: {
                this.ensureCapacity(2);
                byte[] b = this.byteBuffer;
                b[this.position++] = (byte)(l & 0xFFL);
                b[this.position++] = (byte)(l >>> 8);
                break;
            }
            case INT3: {
                this.ensureCapacity(3);
                byte[] b = this.byteBuffer;
                b[this.position++] = (byte)(l & 0xFFL);
                b[this.position++] = (byte)(l >>> 8);
                b[this.position++] = (byte)(l >>> 16);
                break;
            }
            case INT4: {
                this.ensureCapacity(4);
                byte[] b = this.byteBuffer;
                b[this.position++] = (byte)(l & 0xFFL);
                b[this.position++] = (byte)(l >>> 8);
                b[this.position++] = (byte)(l >>> 16);
                b[this.position++] = (byte)(l >>> 24);
                break;
            }
            case INT6: {
                this.ensureCapacity(6);
                byte[] b = this.byteBuffer;
                b[this.position++] = (byte)(l & 0xFFL);
                b[this.position++] = (byte)(l >>> 8);
                b[this.position++] = (byte)(l >>> 16);
                b[this.position++] = (byte)(l >>> 24);
                b[this.position++] = (byte)(l >>> 32);
                b[this.position++] = (byte)(l >>> 40);
                break;
            }
            case INT8: {
                this.ensureCapacity(8);
                byte[] b = this.byteBuffer;
                b[this.position++] = (byte)(l & 0xFFL);
                b[this.position++] = (byte)(l >>> 8);
                b[this.position++] = (byte)(l >>> 16);
                b[this.position++] = (byte)(l >>> 24);
                b[this.position++] = (byte)(l >>> 32);
                b[this.position++] = (byte)(l >>> 40);
                b[this.position++] = (byte)(l >>> 48);
                b[this.position++] = (byte)(l >>> 56);
                break;
            }
            case INT_LENENC: {
                if (l < 251L) {
                    this.ensureCapacity(1);
                    this.writeInteger(NativeConstants.IntegerDataType.INT1, l);
                    break;
                }
                if (l < 65536L) {
                    this.ensureCapacity(3);
                    this.writeInteger(NativeConstants.IntegerDataType.INT1, 252L);
                    this.writeInteger(NativeConstants.IntegerDataType.INT2, l);
                    break;
                }
                if (l < 0x1000000L) {
                    this.ensureCapacity(4);
                    this.writeInteger(NativeConstants.IntegerDataType.INT1, 253L);
                    this.writeInteger(NativeConstants.IntegerDataType.INT3, l);
                    break;
                }
                this.ensureCapacity(9);
                this.writeInteger(NativeConstants.IntegerDataType.INT1, 254L);
                this.writeInteger(NativeConstants.IntegerDataType.INT8, l);
            }
        }
        this.adjustPayloadLength();
    }

    public final long readInteger(NativeConstants.IntegerDataType type) {
        byte[] b = this.byteBuffer;
        switch (type) {
            case INT1: {
                return b[this.position++] & 0xFF;
            }
            case INT2: {
                return b[this.position++] & 0xFF | (b[this.position++] & 0xFF) << 8;
            }
            case INT3: {
                return b[this.position++] & 0xFF | (b[this.position++] & 0xFF) << 8 | (b[this.position++] & 0xFF) << 16;
            }
            case INT4: {
                return (long)b[this.position++] & 0xFFL | ((long)b[this.position++] & 0xFFL) << 8 | (long)(b[this.position++] & 0xFF) << 16 | (long)(b[this.position++] & 0xFF) << 24;
            }
            case INT6: {
                return (long)(b[this.position++] & 0xFF) | (long)(b[this.position++] & 0xFF) << 8 | (long)(b[this.position++] & 0xFF) << 16 | (long)(b[this.position++] & 0xFF) << 24 | (long)(b[this.position++] & 0xFF) << 32 | (long)(b[this.position++] & 0xFF) << 40;
            }
            case INT8: {
                return (long)(b[this.position++] & 0xFF) | (long)(b[this.position++] & 0xFF) << 8 | (long)(b[this.position++] & 0xFF) << 16 | (long)(b[this.position++] & 0xFF) << 24 | (long)(b[this.position++] & 0xFF) << 32 | (long)(b[this.position++] & 0xFF) << 40 | (long)(b[this.position++] & 0xFF) << 48 | (long)(b[this.position++] & 0xFF) << 56;
            }
            case INT_LENENC: {
                int sw = b[this.position++] & 0xFF;
                switch (sw) {
                    case 251: {
                        return -1L;
                    }
                    case 252: {
                        return this.readInteger(NativeConstants.IntegerDataType.INT2);
                    }
                    case 253: {
                        return this.readInteger(NativeConstants.IntegerDataType.INT3);
                    }
                    case 254: {
                        return this.readInteger(NativeConstants.IntegerDataType.INT8);
                    }
                }
                return sw;
            }
        }
        return b[this.position++] & 0xFF;
    }

    public final void writeBytes(NativeConstants.StringSelfDataType type, byte[] b) {
        this.writeBytes(type, b, 0, b.length);
    }

    public final void writeBytes(NativeConstants.StringLengthDataType type, byte[] b) {
        this.writeBytes(type, b, 0, b.length);
    }

    public void writeBytes(NativeConstants.StringSelfDataType type, byte[] b, int offset, int len) {
        switch (type) {
            case STRING_EOF: {
                this.writeBytes(NativeConstants.StringLengthDataType.STRING_FIXED, b, offset, len);
                break;
            }
            case STRING_TERM: {
                this.ensureCapacity(len + 1);
                this.writeBytes(NativeConstants.StringLengthDataType.STRING_FIXED, b, offset, len);
                this.byteBuffer[this.position++] = 0;
                break;
            }
            case STRING_LENENC: {
                this.ensureCapacity(len + 9);
                this.writeInteger(NativeConstants.IntegerDataType.INT_LENENC, len);
                this.writeBytes(NativeConstants.StringLengthDataType.STRING_FIXED, b, offset, len);
            }
        }
        this.adjustPayloadLength();
    }

    public void writeBytes(NativeConstants.StringLengthDataType type, byte[] b, int offset, int len) {
        switch (type) {
            case STRING_FIXED: 
            case STRING_VAR: {
                this.ensureCapacity(len);
                System.arraycopy(b, offset, this.byteBuffer, this.position, len);
                this.position += len;
            }
        }
        this.adjustPayloadLength();
    }

    public byte[] readBytes(NativeConstants.StringSelfDataType type) {
        switch (type) {
            case STRING_TERM: {
                int i;
                for (i = this.position; i < this.payloadLength && this.byteBuffer[i] != 0; ++i) {
                }
                byte[] b = this.readBytes(NativeConstants.StringLengthDataType.STRING_FIXED, i - this.position);
                ++this.position;
                return b;
            }
            case STRING_LENENC: {
                long l = this.readInteger(NativeConstants.IntegerDataType.INT_LENENC);
                return l == -1L ? null : (l == 0L ? Constants.EMPTY_BYTE_ARRAY : this.readBytes(NativeConstants.StringLengthDataType.STRING_FIXED, (int)l));
            }
            case STRING_EOF: {
                return this.readBytes(NativeConstants.StringLengthDataType.STRING_FIXED, this.payloadLength - this.position);
            }
        }
        return null;
    }

    public void skipBytes(NativeConstants.StringSelfDataType type) {
        switch (type) {
            case STRING_TERM: {
                while (this.position < this.payloadLength && this.byteBuffer[this.position] != 0) {
                    ++this.position;
                }
                ++this.position;
                break;
            }
            case STRING_LENENC: {
                long len = this.readInteger(NativeConstants.IntegerDataType.INT_LENENC);
                if (len == -1L || len == 0L) break;
                this.position += (int)len;
                break;
            }
            case STRING_EOF: {
                this.position = this.payloadLength;
            }
        }
    }

    public byte[] readBytes(NativeConstants.StringLengthDataType type, int len) {
        switch (type) {
            case STRING_FIXED: 
            case STRING_VAR: {
                byte[] b = new byte[len];
                System.arraycopy(this.byteBuffer, this.position, b, 0, len);
                this.position += len;
                return b;
            }
        }
        return null;
    }

    public String readString(NativeConstants.StringSelfDataType type, String encoding) {
        String res = null;
        switch (type) {
            case STRING_TERM: {
                int i;
                for (i = this.position; i < this.payloadLength && this.byteBuffer[i] != 0; ++i) {
                }
                res = this.readString(NativeConstants.StringLengthDataType.STRING_FIXED, encoding, i - this.position);
                ++this.position;
                break;
            }
            case STRING_LENENC: {
                long l = this.readInteger(NativeConstants.IntegerDataType.INT_LENENC);
                return l == -1L ? null : (l == 0L ? "" : this.readString(NativeConstants.StringLengthDataType.STRING_FIXED, encoding, (int)l));
            }
            case STRING_EOF: {
                return this.readString(NativeConstants.StringLengthDataType.STRING_FIXED, encoding, this.payloadLength - this.position);
            }
        }
        return res;
    }

    public String readString(NativeConstants.StringLengthDataType type, String encoding, int len) {
        String res = null;
        switch (type) {
            case STRING_FIXED: 
            case STRING_VAR: {
                if (this.position + len > this.payloadLength) {
                    throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("Buffer.1"));
                }
                res = StringUtils.toString(this.byteBuffer, this.position, len, encoding);
                this.position += len;
            }
        }
        return res;
    }

    public static String extractSqlFromPacket(String possibleSqlQuery, NativePacketPayload packet, int endOfQueryPacketPosition, int maxQuerySizeToLog) {
        String extractedSql = null;
        if (possibleSqlQuery != null) {
            if (possibleSqlQuery.length() > maxQuerySizeToLog) {
                StringBuilder truncatedQueryBuf = new StringBuilder(possibleSqlQuery.substring(0, maxQuerySizeToLog));
                truncatedQueryBuf.append(Messages.getString("MysqlIO.25"));
                extractedSql = truncatedQueryBuf.toString();
            } else {
                extractedSql = possibleSqlQuery;
            }
        }
        if (extractedSql == null) {
            int extractPosition = endOfQueryPacketPosition;
            boolean truncated = false;
            if (endOfQueryPacketPosition > maxQuerySizeToLog) {
                extractPosition = maxQuerySizeToLog;
                truncated = true;
            }
            extractedSql = StringUtils.toString(packet.getByteBuffer(), 1, extractPosition - 1);
            if (truncated) {
                extractedSql = extractedSql + Messages.getString("MysqlIO.25");
            }
        }
        return extractedSql;
    }
}

