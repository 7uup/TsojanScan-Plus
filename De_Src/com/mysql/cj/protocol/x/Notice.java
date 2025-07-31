/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.protocol.x;

import com.mysql.cj.protocol.Warning;
import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import java.util.List;

public class Notice
implements Warning {
    public static final int XProtocolNoticeFrameType_WARNING = 1;
    public static final int XProtocolNoticeFrameType_SESS_VAR_CHANGED = 2;
    public static final int XProtocolNoticeFrameType_SESS_STATE_CHANGED = 3;
    public static final int SessionStateChanged_CURRENT_SCHEMA = 1;
    public static final int SessionStateChanged_ACCOUNT_EXPIRED = 2;
    public static final int SessionStateChanged_GENERATED_INSERT_ID = 3;
    public static final int SessionStateChanged_ROWS_AFFECTED = 4;
    public static final int SessionStateChanged_ROWS_FOUND = 5;
    public static final int SessionStateChanged_ROWS_MATCHED = 6;
    public static final int SessionStateChanged_TRX_COMMITTED = 7;
    public static final int SessionStateChanged_TRX_ROLLEDBACK = 9;
    public static final int SessionStateChanged_PRODUCED_MESSAGE = 10;
    public static final int SessionStateChanged_CLIENT_ID_ASSIGNED = 11;
    public static final int SessionStateChanged_GENERATED_DOCUMENT_IDS = 12;
    private int noticeType = 0;
    private int level;
    private long code;
    private String message;
    private Integer paramType = null;
    private String paramName = null;
    private MysqlxDatatypes.Scalar value = null;
    private List<MysqlxDatatypes.Scalar> valueList = null;

    public Notice(int level, long code, String message) {
        this.noticeType = 1;
        this.level = level;
        this.code = code;
        this.message = message;
    }

    public Notice(int paramType, List<MysqlxDatatypes.Scalar> valueList) {
        this.noticeType = 3;
        this.paramType = paramType;
        this.valueList = valueList;
    }

    public Notice(String paramName, MysqlxDatatypes.Scalar value) {
        this.noticeType = 2;
        this.paramName = paramName;
        this.value = value;
    }

    public int getType() {
        return this.noticeType;
    }

    @Override
    public int getLevel() {
        return this.level;
    }

    @Override
    public long getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    public Integer getParamType() {
        return this.paramType;
    }

    public String getParamName() {
        return this.paramName;
    }

    public MysqlxDatatypes.Scalar getValue() {
        if (this.value == null && this.valueList != null && !this.valueList.isEmpty()) {
            return this.valueList.get(0);
        }
        return this.value;
    }

    public List<MysqlxDatatypes.Scalar> getValueList() {
        return this.valueList;
    }
}

