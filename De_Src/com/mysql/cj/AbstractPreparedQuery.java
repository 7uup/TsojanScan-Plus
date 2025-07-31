/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj;

import com.mysql.cj.AbstractQuery;
import com.mysql.cj.AbstractQueryBindings;
import com.mysql.cj.BindValue;
import com.mysql.cj.Constants;
import com.mysql.cj.Messages;
import com.mysql.cj.NativeSession;
import com.mysql.cj.ParseInfo;
import com.mysql.cj.PreparedQuery;
import com.mysql.cj.QueryBindings;
import com.mysql.cj.conf.RuntimeProperty;
import com.mysql.cj.exceptions.ExceptionFactory;
import com.mysql.cj.exceptions.WrongArgumentException;
import com.mysql.cj.protocol.Message;
import com.mysql.cj.protocol.a.NativeConstants;
import com.mysql.cj.protocol.a.NativePacketPayload;
import com.mysql.cj.util.StringUtils;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public abstract class AbstractPreparedQuery<T extends QueryBindings<?>>
extends AbstractQuery
implements PreparedQuery<T> {
    protected ParseInfo parseInfo;
    protected T queryBindings = null;
    protected String originalSql = null;
    protected int parameterCount;
    protected RuntimeProperty<Boolean> autoClosePStmtStreams = this.session.getPropertySet().getBooleanProperty("autoClosePStmtStreams");
    protected int batchCommandIndex = -1;
    protected RuntimeProperty<Boolean> useStreamLengthsInPrepStmts = this.session.getPropertySet().getBooleanProperty("useStreamLengthsInPrepStmts");
    private byte[] streamConvertBuf = null;
    private boolean usingAnsiMode = !this.session.getServerSession().useAnsiQuotedIdentifiers();

    public AbstractPreparedQuery(NativeSession sess) {
        super(sess);
    }

    @Override
    public void closeQuery() {
        this.streamConvertBuf = null;
        super.closeQuery();
    }

    @Override
    public ParseInfo getParseInfo() {
        return this.parseInfo;
    }

    @Override
    public void setParseInfo(ParseInfo parseInfo) {
        this.parseInfo = parseInfo;
    }

    @Override
    public String getOriginalSql() {
        return this.originalSql;
    }

    @Override
    public void setOriginalSql(String originalSql) {
        this.originalSql = originalSql;
    }

    @Override
    public int getParameterCount() {
        return this.parameterCount;
    }

    @Override
    public void setParameterCount(int parameterCount) {
        this.parameterCount = parameterCount;
    }

    @Override
    public T getQueryBindings() {
        return this.queryBindings;
    }

    @Override
    public void setQueryBindings(T queryBindings) {
        this.queryBindings = queryBindings;
    }

    @Override
    public int getBatchCommandIndex() {
        return this.batchCommandIndex;
    }

    @Override
    public void setBatchCommandIndex(int batchCommandIndex) {
        this.batchCommandIndex = batchCommandIndex;
    }

    @Override
    public int computeBatchSize(int numBatchedArgs) {
        long[] combinedValues = this.computeMaxParameterSetSizeAndBatchSize(numBatchedArgs);
        long maxSizeOfParameterSet = combinedValues[0];
        long sizeOfEntireBatch = combinedValues[1];
        if (sizeOfEntireBatch < (long)((Integer)this.maxAllowedPacket.getValue() - this.originalSql.length())) {
            return numBatchedArgs;
        }
        return (int)Math.max(1L, (long)((Integer)this.maxAllowedPacket.getValue() - this.originalSql.length()) / maxSizeOfParameterSet);
    }

    @Override
    public void checkNullOrEmptyQuery(String sql) {
        if (sql == null) {
            throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("PreparedQuery.0"), this.session.getExceptionInterceptor());
        }
        if (sql.length() == 0) {
            throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("PreparedQuery.1"), this.session.getExceptionInterceptor());
        }
    }

    @Override
    public String asSql() {
        return this.asSql(false);
    }

    @Override
    public String asSql(boolean quoteStreamsAndUnknowns) {
        StringBuilder buf = new StringBuilder();
        Object batchArg = null;
        if (this.batchCommandIndex != -1) {
            batchArg = this.batchedArgs.get(this.batchCommandIndex);
        }
        byte[][] staticSqlStrings = this.parseInfo.getStaticSql();
        for (int i = 0; i < this.parameterCount; ++i) {
            boolean isStreamParam;
            buf.append(this.charEncoding != null ? StringUtils.toString(staticSqlStrings[i], this.charEncoding) : StringUtils.toString(staticSqlStrings[i]));
            byte[] val = null;
            if (batchArg != null && batchArg instanceof String) {
                buf.append((String)batchArg);
                continue;
            }
            byte[] byArray = this.batchCommandIndex == -1 ? (this.queryBindings == null ? null : this.queryBindings.getBindValues()[i].getByteValue()) : (val = ((QueryBindings)batchArg).getBindValues()[i].getByteValue());
            boolean bl = this.batchCommandIndex == -1 ? (this.queryBindings == null ? false : this.queryBindings.getBindValues()[i].isStream()) : (isStreamParam = ((QueryBindings)batchArg).getBindValues()[i].isStream());
            if (val == null && !isStreamParam) {
                buf.append(quoteStreamsAndUnknowns ? "'** NOT SPECIFIED **'" : "** NOT SPECIFIED **");
                continue;
            }
            if (isStreamParam) {
                buf.append(quoteStreamsAndUnknowns ? "'** STREAM DATA **'" : "** STREAM DATA **");
                continue;
            }
            buf.append(StringUtils.toString(val, this.charEncoding));
        }
        buf.append(this.charEncoding != null ? StringUtils.toString(staticSqlStrings[this.parameterCount], this.charEncoding) : StringUtils.toAsciiString(staticSqlStrings[this.parameterCount]));
        return buf.toString();
    }

    protected abstract long[] computeMaxParameterSetSizeAndBatchSize(int var1);

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public <M extends Message> M fillSendPacket() {
        AbstractPreparedQuery abstractPreparedQuery = this;
        synchronized (abstractPreparedQuery) {
            return this.fillSendPacket((QueryBindings<?>)this.queryBindings);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public <M extends Message> M fillSendPacket(QueryBindings<?> bindings) {
        AbstractPreparedQuery abstractPreparedQuery = this;
        synchronized (abstractPreparedQuery) {
            BindValue[] bindValues = bindings.getBindValues();
            NativePacketPayload sendPacket = this.session.getSharedSendPacket();
            sendPacket.writeInteger(NativeConstants.IntegerDataType.INT1, 3L);
            boolean useStreamLengths = this.useStreamLengthsInPrepStmts.getValue();
            int ensurePacketSize = 0;
            String statementComment = this.session.getProtocol().getQueryComment();
            byte[] commentAsBytes = null;
            if (statementComment != null) {
                commentAsBytes = StringUtils.getBytes(statementComment, this.charEncoding);
                ensurePacketSize += commentAsBytes.length;
                ensurePacketSize += 6;
            }
            for (int i = 0; i < bindValues.length; ++i) {
                if (!bindValues[i].isStream() || !useStreamLengths) continue;
                ensurePacketSize += bindValues[i].getStreamLength();
            }
            if (ensurePacketSize != 0) {
                sendPacket.ensureCapacity(ensurePacketSize);
            }
            if (commentAsBytes != null) {
                sendPacket.writeBytes(NativeConstants.StringLengthDataType.STRING_FIXED, Constants.SLASH_STAR_SPACE_AS_BYTES);
                sendPacket.writeBytes(NativeConstants.StringLengthDataType.STRING_FIXED, commentAsBytes);
                sendPacket.writeBytes(NativeConstants.StringLengthDataType.STRING_FIXED, Constants.SPACE_STAR_SLASH_SPACE_AS_BYTES);
            }
            byte[][] staticSqlStrings = this.parseInfo.getStaticSql();
            for (int i = 0; i < bindValues.length; ++i) {
                bindings.checkParameterSet(i);
                sendPacket.writeBytes(NativeConstants.StringLengthDataType.STRING_FIXED, staticSqlStrings[i]);
                if (bindValues[i].isStream()) {
                    this.streamToBytes(sendPacket, bindValues[i].getStreamValue(), true, bindValues[i].getStreamLength(), useStreamLengths);
                    continue;
                }
                sendPacket.writeBytes(NativeConstants.StringLengthDataType.STRING_FIXED, bindValues[i].getByteValue());
            }
            sendPacket.writeBytes(NativeConstants.StringLengthDataType.STRING_FIXED, staticSqlStrings[bindValues.length]);
            return (M)sendPacket;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private final void streamToBytes(NativePacketPayload packet, InputStream in, boolean escape, int streamLength, boolean useLength) {
        try {
            if (this.streamConvertBuf == null) {
                this.streamConvertBuf = new byte[4096];
            }
            boolean hexEscape = this.session.getServerSession().isNoBackslashEscapesSet();
            if (streamLength == -1) {
                useLength = false;
            }
            int bc = useLength ? this.readblock(in, this.streamConvertBuf, streamLength) : this.readblock(in, this.streamConvertBuf);
            int lengthLeftToRead = streamLength - bc;
            packet.writeBytes(NativeConstants.StringLengthDataType.STRING_FIXED, StringUtils.getBytes(hexEscape ? "x" : "_binary"));
            if (escape) {
                packet.writeInteger(NativeConstants.IntegerDataType.INT1, 39L);
            }
            while (bc > 0) {
                if (hexEscape) {
                    ((AbstractQueryBindings)this.queryBindings).hexEscapeBlock(this.streamConvertBuf, packet, bc);
                } else if (escape) {
                    this.escapeblockFast(this.streamConvertBuf, packet, bc);
                } else {
                    packet.writeBytes(NativeConstants.StringLengthDataType.STRING_FIXED, this.streamConvertBuf, 0, bc);
                }
                if (useLength) {
                    bc = this.readblock(in, this.streamConvertBuf, lengthLeftToRead);
                    if (bc <= 0) continue;
                    lengthLeftToRead -= bc;
                    continue;
                }
                bc = this.readblock(in, this.streamConvertBuf);
            }
            if (escape) {
                packet.writeInteger(NativeConstants.IntegerDataType.INT1, 39L);
            }
        } finally {
            if (this.autoClosePStmtStreams.getValue().booleanValue()) {
                try {
                    in.close();
                } catch (IOException iOException) {}
                in = null;
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected final byte[] streamToBytes(InputStream in, boolean escape, int streamLength, boolean useLength) {
        in.mark(Integer.MAX_VALUE);
        try {
            if (this.streamConvertBuf == null) {
                this.streamConvertBuf = new byte[4096];
            }
            if (streamLength == -1) {
                useLength = false;
            }
            ByteArrayOutputStream bytesOut = new ByteArrayOutputStream();
            int bc = useLength ? this.readblock(in, this.streamConvertBuf, streamLength) : this.readblock(in, this.streamConvertBuf);
            int lengthLeftToRead = streamLength - bc;
            if (escape) {
                bytesOut.write(95);
                bytesOut.write(98);
                bytesOut.write(105);
                bytesOut.write(110);
                bytesOut.write(97);
                bytesOut.write(114);
                bytesOut.write(121);
                bytesOut.write(39);
            }
            while (bc > 0) {
                if (escape) {
                    StringUtils.escapeblockFast(this.streamConvertBuf, bytesOut, bc, this.usingAnsiMode);
                } else {
                    bytesOut.write(this.streamConvertBuf, 0, bc);
                }
                if (useLength) {
                    bc = this.readblock(in, this.streamConvertBuf, lengthLeftToRead);
                    if (bc <= 0) continue;
                    lengthLeftToRead -= bc;
                    continue;
                }
                bc = this.readblock(in, this.streamConvertBuf);
            }
            if (escape) {
                bytesOut.write(39);
            }
            byte[] byArray = bytesOut.toByteArray();
            return byArray;
        } finally {
            try {
                in.reset();
            } catch (IOException iOException) {}
            if (this.autoClosePStmtStreams.getValue().booleanValue()) {
                try {
                    in.close();
                } catch (IOException iOException) {}
                in = null;
            }
        }
    }

    private final int readblock(InputStream i, byte[] b) {
        try {
            return i.read(b);
        } catch (Throwable ex) {
            throw ExceptionFactory.createException(Messages.getString("PreparedStatement.56") + ex.getClass().getName(), this.session.getExceptionInterceptor());
        }
    }

    private final int readblock(InputStream i, byte[] b, int length) {
        try {
            int lengthToRead = length;
            if (lengthToRead > b.length) {
                lengthToRead = b.length;
            }
            return i.read(b, 0, lengthToRead);
        } catch (Throwable ex) {
            throw ExceptionFactory.createException(Messages.getString("PreparedStatement.56") + ex.getClass().getName(), this.session.getExceptionInterceptor());
        }
    }

    private final void escapeblockFast(byte[] buf, NativePacketPayload packet, int size) {
        int lastwritten = 0;
        for (int i = 0; i < size; ++i) {
            byte b = buf[i];
            if (b == 0) {
                if (i > lastwritten) {
                    packet.writeBytes(NativeConstants.StringLengthDataType.STRING_FIXED, buf, lastwritten, i - lastwritten);
                }
                packet.writeInteger(NativeConstants.IntegerDataType.INT1, 92L);
                packet.writeInteger(NativeConstants.IntegerDataType.INT1, 48L);
                lastwritten = i + 1;
                continue;
            }
            if (b != 92 && b != 39 && (this.usingAnsiMode || b != 34)) continue;
            if (i > lastwritten) {
                packet.writeBytes(NativeConstants.StringLengthDataType.STRING_FIXED, buf, lastwritten, i - lastwritten);
            }
            packet.writeInteger(NativeConstants.IntegerDataType.INT1, 92L);
            lastwritten = i;
        }
        if (lastwritten < size) {
            packet.writeBytes(NativeConstants.StringLengthDataType.STRING_FIXED, buf, lastwritten, size - lastwritten);
        }
    }
}

