/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj;

import com.mysql.cj.AbstractQueryBindings;
import com.mysql.cj.BindValue;
import com.mysql.cj.CharsetMapping;
import com.mysql.cj.ClientPreparedQueryBindValue;
import com.mysql.cj.Messages;
import com.mysql.cj.MysqlType;
import com.mysql.cj.NativeSession;
import com.mysql.cj.Session;
import com.mysql.cj.exceptions.ExceptionFactory;
import com.mysql.cj.exceptions.WrongArgumentException;
import com.mysql.cj.util.StringUtils;
import com.mysql.cj.util.TimeUtil;
import com.mysql.cj.util.Util;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class ClientPreparedQueryBindings
extends AbstractQueryBindings<ClientPreparedQueryBindValue> {
    private CharsetEncoder charsetEncoder;
    private SimpleDateFormat ddf;
    private SimpleDateFormat tdf;
    private SimpleDateFormat tsdf = null;

    public ClientPreparedQueryBindings(int parameterCount, Session sess) {
        super(parameterCount, sess);
        if (((NativeSession)this.session).getRequiresEscapingEncoder()) {
            this.charsetEncoder = Charset.forName(this.charEncoding).newEncoder();
        }
    }

    @Override
    protected void initBindValues(int parameterCount) {
        this.bindValues = new ClientPreparedQueryBindValue[parameterCount];
        for (int i = 0; i < parameterCount; ++i) {
            ((ClientPreparedQueryBindValue[])this.bindValues)[i] = new ClientPreparedQueryBindValue();
        }
    }

    @Override
    public ClientPreparedQueryBindings clone() {
        ClientPreparedQueryBindings newBindings = new ClientPreparedQueryBindings(((ClientPreparedQueryBindValue[])this.bindValues).length, this.session);
        BindValue[] bvs = new ClientPreparedQueryBindValue[((ClientPreparedQueryBindValue[])this.bindValues).length];
        for (int i = 0; i < ((ClientPreparedQueryBindValue[])this.bindValues).length; ++i) {
            bvs[i] = ((ClientPreparedQueryBindValue[])this.bindValues)[i].clone();
        }
        newBindings.setBindValues(bvs);
        newBindings.isLoadDataQuery = this.isLoadDataQuery;
        return newBindings;
    }

    @Override
    public void checkParameterSet(int columnIndex) {
        if (!((ClientPreparedQueryBindValue[])this.bindValues)[columnIndex].isSet()) {
            throw ExceptionFactory.createException(Messages.getString("PreparedStatement.40") + (columnIndex + 1), "07001", 0, true, null, this.session.getExceptionInterceptor());
        }
    }

    @Override
    public void setAsciiStream(int parameterIndex, InputStream x) {
        this.setAsciiStream(parameterIndex, x, -1);
    }

    @Override
    public void setAsciiStream(int parameterIndex, InputStream x, int length) {
        if (x == null) {
            this.setNull(parameterIndex);
        } else {
            this.setBinaryStream(parameterIndex, x, length);
        }
    }

    @Override
    public void setAsciiStream(int parameterIndex, InputStream x, long length) {
        this.setAsciiStream(parameterIndex, x, (int)length);
        ((ClientPreparedQueryBindValue[])this.bindValues)[parameterIndex].setMysqlType(MysqlType.TEXT);
    }

    @Override
    public void setBigDecimal(int parameterIndex, BigDecimal x) {
        if (x == null) {
            this.setNull(parameterIndex);
        } else {
            this.setValue(parameterIndex, StringUtils.fixDecimalExponent(x.toPlainString()), MysqlType.DECIMAL);
        }
    }

    @Override
    public void setBigInteger(int parameterIndex, BigInteger x) {
        this.setValue(parameterIndex, x.toString(), MysqlType.BIGINT_UNSIGNED);
    }

    @Override
    public void setBinaryStream(int parameterIndex, InputStream x) {
        this.setBinaryStream(parameterIndex, x, -1);
    }

    @Override
    public void setBinaryStream(int parameterIndex, InputStream x, int length) {
        if (x == null) {
            this.setNull(parameterIndex);
        } else {
            ((ClientPreparedQueryBindValue[])this.bindValues)[parameterIndex].setNull(false);
            ((ClientPreparedQueryBindValue[])this.bindValues)[parameterIndex].setIsStream(true);
            ((ClientPreparedQueryBindValue[])this.bindValues)[parameterIndex].setMysqlType(MysqlType.BLOB);
            ((ClientPreparedQueryBindValue[])this.bindValues)[parameterIndex].setStreamValue(x, length);
        }
    }

    @Override
    public void setBinaryStream(int parameterIndex, InputStream x, long length) {
        this.setBinaryStream(parameterIndex, x, (int)length);
    }

    @Override
    public void setBlob(int parameterIndex, InputStream inputStream2) {
        this.setBinaryStream(parameterIndex, inputStream2);
    }

    @Override
    public void setBlob(int parameterIndex, InputStream inputStream2, long length) {
        this.setBinaryStream(parameterIndex, inputStream2, (int)length);
    }

    @Override
    public void setBlob(int parameterIndex, Blob x) {
        if (x == null) {
            this.setNull(parameterIndex);
        } else {
            try {
                ByteArrayOutputStream bytesOut = new ByteArrayOutputStream();
                bytesOut.write(39);
                StringUtils.escapeblockFast(x.getBytes(1L, (int)x.length()), bytesOut, (int)x.length(), this.session.getServerSession().useAnsiQuotedIdentifiers());
                bytesOut.write(39);
                this.setValue(parameterIndex, bytesOut.toByteArray(), MysqlType.BLOB);
            } catch (Throwable t) {
                throw ExceptionFactory.createException(t.getMessage(), t);
            }
        }
    }

    @Override
    public void setBoolean(int parameterIndex, boolean x) {
        this.setValue(parameterIndex, x ? "1" : "0");
    }

    @Override
    public void setByte(int parameterIndex, byte x) {
        this.setValue(parameterIndex, String.valueOf(x), MysqlType.TINYINT);
    }

    @Override
    public void setBytes(int parameterIndex, byte[] x) {
        this.setBytes(parameterIndex, x, true, true);
        if (x != null) {
            ((ClientPreparedQueryBindValue[])this.bindValues)[parameterIndex].setMysqlType(MysqlType.BINARY);
        }
    }

    @Override
    public synchronized void setBytes(int parameterIndex, byte[] x, boolean checkForIntroducer, boolean escapeForMBChars) {
        if (x == null) {
            this.setNull(parameterIndex);
        } else {
            if (this.session.getServerSession().isNoBackslashEscapesSet() || escapeForMBChars && CharsetMapping.isMultibyteCharset(this.charEncoding)) {
                ByteArrayOutputStream bOut = new ByteArrayOutputStream(x.length * 2 + 3);
                bOut.write(120);
                bOut.write(39);
                for (int i = 0; i < x.length; ++i) {
                    int lowBits = (x[i] & 0xFF) / 16;
                    int highBits = (x[i] & 0xFF) % 16;
                    bOut.write(HEX_DIGITS[lowBits]);
                    bOut.write(HEX_DIGITS[highBits]);
                }
                bOut.write(39);
                this.setValue(parameterIndex, bOut.toByteArray());
                return;
            }
            int numBytes = x.length;
            int pad = 2;
            if (checkForIntroducer) {
                pad += 7;
            }
            ByteArrayOutputStream bOut = new ByteArrayOutputStream(numBytes + pad);
            if (checkForIntroducer) {
                bOut.write(95);
                bOut.write(98);
                bOut.write(105);
                bOut.write(110);
                bOut.write(97);
                bOut.write(114);
                bOut.write(121);
            }
            bOut.write(39);
            block10: for (int i = 0; i < numBytes; ++i) {
                byte b = x[i];
                switch (b) {
                    case 0: {
                        bOut.write(92);
                        bOut.write(48);
                        continue block10;
                    }
                    case 10: {
                        bOut.write(92);
                        bOut.write(110);
                        continue block10;
                    }
                    case 13: {
                        bOut.write(92);
                        bOut.write(114);
                        continue block10;
                    }
                    case 92: {
                        bOut.write(92);
                        bOut.write(92);
                        continue block10;
                    }
                    case 39: {
                        bOut.write(92);
                        bOut.write(39);
                        continue block10;
                    }
                    case 34: {
                        bOut.write(92);
                        bOut.write(34);
                        continue block10;
                    }
                    case 26: {
                        bOut.write(92);
                        bOut.write(90);
                        continue block10;
                    }
                    default: {
                        bOut.write(b);
                    }
                }
            }
            bOut.write(39);
            this.setValue(parameterIndex, bOut.toByteArray());
        }
    }

    @Override
    public void setBytesNoEscape(int parameterIndex, byte[] parameterAsBytes) {
        byte[] parameterWithQuotes = new byte[parameterAsBytes.length + 2];
        parameterWithQuotes[0] = 39;
        System.arraycopy(parameterAsBytes, 0, parameterWithQuotes, 1, parameterAsBytes.length);
        parameterWithQuotes[parameterAsBytes.length + 1] = 39;
        this.setValue(parameterIndex, parameterWithQuotes);
    }

    @Override
    public void setBytesNoEscapeNoQuotes(int parameterIndex, byte[] parameterAsBytes) {
        this.setValue(parameterIndex, parameterAsBytes);
    }

    @Override
    public void setCharacterStream(int parameterIndex, Reader reader) {
        this.setCharacterStream(parameterIndex, reader, -1);
    }

    @Override
    public void setCharacterStream(int parameterIndex, Reader reader, int length) {
        try {
            if (reader == null) {
                this.setNull(parameterIndex);
            } else {
                char[] c = null;
                int len = 0;
                boolean useLength = (Boolean)this.useStreamLengthsInPrepStmts.getValue();
                String forcedEncoding = this.session.getPropertySet().getStringProperty("clobCharacterEncoding").getStringValue();
                if (useLength && length != -1) {
                    c = new char[length];
                    int numCharsRead = Util.readFully(reader, c, length);
                    if (forcedEncoding == null) {
                        this.setString(parameterIndex, new String(c, 0, numCharsRead));
                    } else {
                        this.setBytes(parameterIndex, StringUtils.getBytes(new String(c, 0, numCharsRead), forcedEncoding));
                    }
                } else {
                    c = new char[4096];
                    StringBuilder buf = new StringBuilder();
                    while ((len = reader.read(c)) != -1) {
                        buf.append(c, 0, len);
                    }
                    if (forcedEncoding == null) {
                        this.setString(parameterIndex, buf.toString());
                    } else {
                        this.setBytes(parameterIndex, StringUtils.getBytes(buf.toString(), forcedEncoding));
                    }
                }
                ((ClientPreparedQueryBindValue[])this.bindValues)[parameterIndex].setMysqlType(MysqlType.TEXT);
            }
        } catch (UnsupportedEncodingException uec) {
            throw ExceptionFactory.createException(WrongArgumentException.class, uec.toString(), uec, this.session.getExceptionInterceptor());
        } catch (IOException ioEx) {
            throw ExceptionFactory.createException(ioEx.toString(), ioEx, this.session.getExceptionInterceptor());
        }
    }

    @Override
    public void setCharacterStream(int parameterIndex, Reader reader, long length) {
        this.setCharacterStream(parameterIndex, reader, (int)length);
    }

    @Override
    public void setClob(int parameterIndex, Reader reader) {
        this.setCharacterStream(parameterIndex, reader);
    }

    @Override
    public void setClob(int parameterIndex, Reader reader, long length) {
        this.setCharacterStream(parameterIndex, reader, length);
    }

    @Override
    public void setClob(int i, Clob x) {
        if (x == null) {
            this.setNull(i);
        } else {
            try {
                String forcedEncoding = this.session.getPropertySet().getStringProperty("clobCharacterEncoding").getStringValue();
                if (forcedEncoding == null) {
                    this.setString(i, x.getSubString(1L, (int)x.length()));
                } else {
                    this.setBytes(i, StringUtils.getBytes(x.getSubString(1L, (int)x.length()), forcedEncoding));
                }
                ((ClientPreparedQueryBindValue[])this.bindValues)[i].setMysqlType(MysqlType.TEXT);
            } catch (Throwable t) {
                throw ExceptionFactory.createException(t.getMessage(), t);
            }
        }
    }

    @Override
    public void setDate(int parameterIndex, Date x) {
        this.setDate(parameterIndex, x, this.session.getServerSession().getDefaultTimeZone());
    }

    @Override
    public void setDate(int parameterIndex, Date x, Calendar cal) {
        this.setDate(parameterIndex, x, cal.getTimeZone());
    }

    @Override
    public void setDate(int parameterIndex, Date x, TimeZone tz) {
        if (x == null) {
            this.setNull(parameterIndex);
        } else {
            if (this.ddf == null) {
                this.ddf = new SimpleDateFormat("''yyyy-MM-dd''", Locale.US);
            }
            this.ddf.setTimeZone(tz);
            this.setValue(parameterIndex, this.ddf.format(x));
        }
    }

    @Override
    public void setDouble(int parameterIndex, double x) {
        if (!this.session.getPropertySet().getBooleanProperty("allowNanAndInf").getValue().booleanValue() && (x == Double.POSITIVE_INFINITY || x == Double.NEGATIVE_INFINITY || Double.isNaN(x))) {
            throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("PreparedStatement.64", new Object[]{x}), this.session.getExceptionInterceptor());
        }
        this.setValue(parameterIndex, StringUtils.fixDecimalExponent(String.valueOf(x)), MysqlType.DOUBLE);
    }

    @Override
    public void setFloat(int parameterIndex, float x) {
        this.setValue(parameterIndex, StringUtils.fixDecimalExponent(String.valueOf(x)), MysqlType.FLOAT);
    }

    @Override
    public void setInt(int parameterIndex, int x) {
        this.setValue(parameterIndex, String.valueOf(x), MysqlType.INT);
    }

    @Override
    public void setLong(int parameterIndex, long x) {
        this.setValue(parameterIndex, String.valueOf(x), MysqlType.BIGINT);
    }

    @Override
    public void setNCharacterStream(int parameterIndex, Reader value) {
        this.setNCharacterStream(parameterIndex, value, -1L);
    }

    @Override
    public void setNCharacterStream(int parameterIndex, Reader reader, long length) {
        if (reader == null) {
            this.setNull(parameterIndex);
        } else {
            try {
                char[] c = null;
                int len = 0;
                boolean useLength = (Boolean)this.useStreamLengthsInPrepStmts.getValue();
                if (useLength && length != -1L) {
                    c = new char[(int)length];
                    int numCharsRead = Util.readFully(reader, c, (int)length);
                    this.setNString(parameterIndex, new String(c, 0, numCharsRead));
                } else {
                    c = new char[4096];
                    StringBuilder buf = new StringBuilder();
                    while ((len = reader.read(c)) != -1) {
                        buf.append(c, 0, len);
                    }
                    this.setNString(parameterIndex, buf.toString());
                }
                ((ClientPreparedQueryBindValue[])this.bindValues)[parameterIndex].setMysqlType(MysqlType.TEXT);
            } catch (Throwable t) {
                throw ExceptionFactory.createException(t.getMessage(), t, this.session.getExceptionInterceptor());
            }
        }
    }

    @Override
    public void setNClob(int parameterIndex, Reader reader) {
        this.setNCharacterStream(parameterIndex, reader);
    }

    @Override
    public void setNClob(int parameterIndex, Reader reader, long length) {
        if (reader == null) {
            this.setNull(parameterIndex);
        } else {
            this.setNCharacterStream(parameterIndex, reader, length);
        }
    }

    @Override
    public void setNClob(int parameterIndex, NClob value) {
        if (value == null) {
            this.setNull(parameterIndex);
        } else {
            try {
                this.setNCharacterStream(parameterIndex, value.getCharacterStream(), value.length());
            } catch (Throwable t) {
                throw ExceptionFactory.createException(t.getMessage(), t, this.session.getExceptionInterceptor());
            }
        }
    }

    @Override
    public void setNString(int parameterIndex, String x) {
        if (x == null) {
            this.setNull(parameterIndex);
        } else {
            if (this.charEncoding.equalsIgnoreCase("UTF-8") || this.charEncoding.equalsIgnoreCase("utf8")) {
                this.setString(parameterIndex, x);
                return;
            }
            int stringLength = x.length();
            StringBuilder buf = new StringBuilder((int)((double)x.length() * 1.1 + 4.0));
            buf.append("_utf8");
            buf.append('\'');
            block9: for (int i = 0; i < stringLength; ++i) {
                char c = x.charAt(i);
                switch (c) {
                    case '\u0000': {
                        buf.append('\\');
                        buf.append('0');
                        continue block9;
                    }
                    case '\n': {
                        buf.append('\\');
                        buf.append('n');
                        continue block9;
                    }
                    case '\r': {
                        buf.append('\\');
                        buf.append('r');
                        continue block9;
                    }
                    case '\\': {
                        buf.append('\\');
                        buf.append('\\');
                        continue block9;
                    }
                    case '\'': {
                        buf.append('\\');
                        buf.append('\'');
                        continue block9;
                    }
                    case '\"': {
                        if (this.session.getServerSession().useAnsiQuotedIdentifiers()) {
                            buf.append('\\');
                        }
                        buf.append('\"');
                        continue block9;
                    }
                    case '\u001a': {
                        buf.append('\\');
                        buf.append('Z');
                        continue block9;
                    }
                    default: {
                        buf.append(c);
                    }
                }
            }
            buf.append('\'');
            byte[] parameterAsBytes = this.isLoadDataQuery ? StringUtils.getBytes(buf.toString()) : StringUtils.getBytes(buf.toString(), "UTF-8");
            this.setValue(parameterIndex, parameterAsBytes, MysqlType.VARCHAR);
        }
    }

    @Override
    public synchronized void setNull(int parameterIndex) {
        this.setValue(parameterIndex, "null");
        ((ClientPreparedQueryBindValue[])this.bindValues)[parameterIndex].setNull(true);
    }

    @Override
    public void setShort(int parameterIndex, short x) {
        this.setValue(parameterIndex, String.valueOf(x), MysqlType.SMALLINT);
    }

    @Override
    public void setString(int parameterIndex, String x) {
        if (x == null) {
            this.setNull(parameterIndex);
        } else {
            int stringLength = x.length();
            if (this.session.getServerSession().isNoBackslashEscapesSet()) {
                boolean needsHexEscape = this.isEscapeNeededForString(x, stringLength);
                if (!needsHexEscape) {
                    StringBuilder quotedString = new StringBuilder(x.length() + 2);
                    quotedString.append('\'');
                    quotedString.append(x);
                    quotedString.append('\'');
                    byte[] parameterAsBytes = this.isLoadDataQuery ? StringUtils.getBytes(quotedString.toString()) : StringUtils.getBytes(quotedString.toString(), this.charEncoding);
                    this.setValue(parameterIndex, parameterAsBytes);
                } else {
                    byte[] parameterAsBytes = this.isLoadDataQuery ? StringUtils.getBytes(x) : StringUtils.getBytes(x, this.charEncoding);
                    this.setBytes(parameterIndex, parameterAsBytes);
                }
                return;
            }
            String parameterAsString = x;
            boolean needsQuoted = true;
            if (this.isLoadDataQuery || this.isEscapeNeededForString(x, stringLength)) {
                needsQuoted = false;
                StringBuilder buf = new StringBuilder((int)((double)x.length() * 1.1));
                buf.append('\'');
                block10: for (int i = 0; i < stringLength; ++i) {
                    char c = x.charAt(i);
                    switch (c) {
                        case '\u0000': {
                            buf.append('\\');
                            buf.append('0');
                            continue block10;
                        }
                        case '\n': {
                            buf.append('\\');
                            buf.append('n');
                            continue block10;
                        }
                        case '\r': {
                            buf.append('\\');
                            buf.append('r');
                            continue block10;
                        }
                        case '\\': {
                            buf.append('\\');
                            buf.append('\\');
                            continue block10;
                        }
                        case '\'': {
                            buf.append('\\');
                            buf.append('\'');
                            continue block10;
                        }
                        case '\"': {
                            if (this.session.getServerSession().useAnsiQuotedIdentifiers()) {
                                buf.append('\\');
                            }
                            buf.append('\"');
                            continue block10;
                        }
                        case '\u001a': {
                            buf.append('\\');
                            buf.append('Z');
                            continue block10;
                        }
                        case '\u00a5': 
                        case '\u20a9': {
                            if (this.charsetEncoder != null) {
                                CharBuffer cbuf = CharBuffer.allocate(1);
                                ByteBuffer bbuf = ByteBuffer.allocate(1);
                                cbuf.put(c);
                                cbuf.position(0);
                                this.charsetEncoder.encode(cbuf, bbuf, true);
                                if (bbuf.get(0) == 92) {
                                    buf.append('\\');
                                }
                            }
                            buf.append(c);
                            continue block10;
                        }
                        default: {
                            buf.append(c);
                        }
                    }
                }
                buf.append('\'');
                parameterAsString = buf.toString();
            }
            byte[] parameterAsBytes = this.isLoadDataQuery ? StringUtils.getBytes(parameterAsString) : (needsQuoted ? StringUtils.getBytesWrapped(parameterAsString, '\'', '\'', this.charEncoding) : StringUtils.getBytes(parameterAsString, this.charEncoding));
            this.setValue(parameterIndex, parameterAsBytes, MysqlType.VARCHAR);
        }
    }

    private boolean isEscapeNeededForString(String x, int stringLength) {
        boolean needsHexEscape = false;
        for (int i = 0; i < stringLength; ++i) {
            char c = x.charAt(i);
            switch (c) {
                case '\u0000': 
                case '\n': 
                case '\r': 
                case '\u001a': 
                case '\"': 
                case '\'': 
                case '\\': {
                    needsHexEscape = true;
                }
            }
            if (needsHexEscape) break;
        }
        return needsHexEscape;
    }

    @Override
    public void setTime(int parameterIndex, Time x, Calendar cal) {
        this.setTime(parameterIndex, x, cal.getTimeZone());
    }

    @Override
    public void setTime(int parameterIndex, Time x) {
        this.setTime(parameterIndex, x, this.session.getServerSession().getDefaultTimeZone());
    }

    @Override
    public void setTime(int parameterIndex, Time x, TimeZone tz) {
        if (x == null) {
            this.setNull(parameterIndex);
        } else {
            if (this.tdf == null) {
                this.tdf = new SimpleDateFormat("''HH:mm:ss''", Locale.US);
            }
            this.tdf.setTimeZone(tz);
            this.setValue(parameterIndex, this.tdf.format(x), MysqlType.TIME);
        }
    }

    @Override
    public void setTimestamp(int parameterIndex, Timestamp x) {
        this.setTimestamp(parameterIndex, x, this.session.getServerSession().getDefaultTimeZone());
    }

    @Override
    public void setTimestamp(int parameterIndex, Timestamp x, Calendar cal) {
        this.setTimestamp(parameterIndex, x, cal.getTimeZone());
    }

    @Override
    public void setTimestamp(int parameterIndex, Timestamp x, TimeZone tz) {
        if (x == null) {
            this.setNull(parameterIndex);
        } else {
            if (!((Boolean)this.sendFractionalSeconds.getValue()).booleanValue()) {
                x = TimeUtil.truncateFractionalSeconds(x);
            }
            if (this.tsdf == null) {
                this.tsdf = new SimpleDateFormat("''yyyy-MM-dd HH:mm:ss", Locale.US);
            }
            this.tsdf.setTimeZone(tz);
            StringBuffer buf = new StringBuffer();
            buf.append(this.tsdf.format(x));
            if (this.session.getServerSession().getCapabilities().serverSupportsFracSecs()) {
                buf.append('.');
                buf.append(TimeUtil.formatNanos(x.getNanos(), true));
            }
            buf.append('\'');
            this.setValue(parameterIndex, buf.toString(), MysqlType.TIMESTAMP);
        }
    }
}

