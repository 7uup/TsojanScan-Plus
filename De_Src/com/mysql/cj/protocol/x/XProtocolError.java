/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.protocol.x;

import com.mysql.cj.exceptions.CJException;
import com.mysql.cj.x.protobuf.Mysqlx;

public class XProtocolError
extends CJException {
    private static final long serialVersionUID = 6991120628391138584L;
    private Mysqlx.Error msg;

    public XProtocolError(String message) {
        super(message);
    }

    public XProtocolError(Mysqlx.Error msg) {
        super(XProtocolError.getFullErrorDescription(msg));
        this.msg = msg;
    }

    public XProtocolError(XProtocolError fromOtherThread) {
        super(XProtocolError.getFullErrorDescription(fromOtherThread.msg), fromOtherThread);
        this.msg = fromOtherThread.msg;
    }

    public XProtocolError(String message, Throwable t) {
        super(message, t);
    }

    private static String getFullErrorDescription(Mysqlx.Error msg) {
        StringBuilder stringMessage = new StringBuilder("ERROR ");
        stringMessage.append(msg.getCode());
        stringMessage.append(" (");
        stringMessage.append(msg.getSqlState());
        stringMessage.append(") ");
        stringMessage.append(msg.getMsg());
        return stringMessage.toString();
    }

    public int getErrorCode() {
        return this.msg == null ? super.getVendorCode() : this.msg.getCode();
    }

    @Override
    public String getSQLState() {
        return this.msg == null ? super.getSQLState() : this.msg.getSqlState();
    }
}

