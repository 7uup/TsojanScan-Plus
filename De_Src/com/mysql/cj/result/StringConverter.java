/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.result;

import com.mysql.cj.Constants;
import com.mysql.cj.Messages;
import com.mysql.cj.exceptions.DataConversionException;
import com.mysql.cj.log.ProfilerEventHandler;
import com.mysql.cj.log.ProfilerEventImpl;
import com.mysql.cj.protocol.a.MysqlTextValueDecoder;
import com.mysql.cj.result.BaseDecoratingValueFactory;
import com.mysql.cj.result.ValueFactory;
import com.mysql.cj.util.LogUtils;
import com.mysql.cj.util.StringUtils;

public class StringConverter<T>
extends BaseDecoratingValueFactory<T> {
    private String encoding;
    private boolean emptyStringsConvertToZero = false;
    private ProfilerEventHandler eventSink;

    public StringConverter(String encoding, ValueFactory<T> targetVf) {
        super(targetVf);
        this.encoding = encoding;
    }

    public void setEmptyStringsConvertToZero(boolean val) {
        this.emptyStringsConvertToZero = val;
    }

    public void setEventSink(ProfilerEventHandler eventSink) {
        this.eventSink = eventSink;
    }

    private void issueConversionViaParsingWarning() {
        if (this.eventSink == null) {
            return;
        }
        String message = Messages.getString("ResultSet.CostlyConversion", new Object[]{this.targetVf.getTargetTypeName(), -1, "<unknown>", "<unknown>", "<unknown>", "<unknown>", "<unknown>", "<unknown>"});
        this.eventSink.consumeEvent(new ProfilerEventImpl(0, "", "<unknown>", -1L, -1, -1, System.currentTimeMillis(), 0L, Constants.MILLIS_I18N, null, LogUtils.findCallingClassAndMethod(new Throwable()), message));
    }

    @Override
    public T createFromBytes(byte[] origBytes, int offset, int length) {
        MysqlTextValueDecoder stringInterpreter = new MysqlTextValueDecoder();
        String s2 = StringUtils.toString(origBytes, offset, length, this.encoding);
        byte[] bytes = s2.getBytes();
        ValueFactory vf = this.targetVf;
        this.issueConversionViaParsingWarning();
        if (s2.length() == 0) {
            if (this.emptyStringsConvertToZero) {
                return this.targetVf.createFromLong(0L);
            }
        } else {
            if (s2.equalsIgnoreCase("true")) {
                return vf.createFromLong(1L);
            }
            if (s2.equalsIgnoreCase("false")) {
                return vf.createFromLong(0L);
            }
            if (s2.contains("e") || s2.contains("E") || s2.matches("-?(\\d+)?\\.\\d+")) {
                return stringInterpreter.decodeDouble(bytes, 0, bytes.length, vf);
            }
            if (s2.matches("-?\\d+")) {
                if (s2.charAt(0) == '-') {
                    return stringInterpreter.decodeInt8(bytes, 0, bytes.length, vf);
                }
                return stringInterpreter.decodeUInt8(bytes, 0, bytes.length, vf);
            }
            if (s2.length() == 10 && s2.charAt(4) == '-' && s2.charAt(7) == '-') {
                return stringInterpreter.decodeDate(bytes, 0, bytes.length, vf);
            }
            if (s2.length() >= 8 && s2.length() <= 17 && s2.charAt(2) == ':' && s2.charAt(5) == ':') {
                return stringInterpreter.decodeTime(bytes, 0, bytes.length, vf);
            }
            if (s2.length() >= 19 && (s2.length() <= 26 || s2.length() == 29) && s2.charAt(4) == '-' && s2.charAt(7) == '-' && s2.charAt(10) == ' ' && s2.charAt(13) == ':' && s2.charAt(16) == ':') {
                return stringInterpreter.decodeTimestamp(bytes, 0, bytes.length, vf);
            }
        }
        throw new DataConversionException(Messages.getString("ResultSet.UnableToInterpretString", new Object[]{s2}));
    }

    @Override
    public T createFromBit(byte[] bytes, int offset, int length) {
        MysqlTextValueDecoder stringInterpreter = new MysqlTextValueDecoder();
        ValueFactory vf = this.targetVf;
        return stringInterpreter.decodeBit(bytes, offset, length, vf);
    }
}

