/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.protocol.a;

public class NativeConstants {
    public static final int MAX_PACKET_SIZE = 0xFFFFFF;
    public static final int HEADER_LENGTH = 4;
    public static final int SEED_LENGTH = 20;
    public static final short TYPE_ID_ERROR = 255;
    public static final short TYPE_ID_EOF = 254;
    public static final short TYPE_ID_LOCAL_INFILE = 251;
    public static final short TYPE_ID_OK = 0;
    public static final int BIN_LEN_INT1 = 1;
    public static final int BIN_LEN_INT2 = 2;
    public static final int BIN_LEN_INT4 = 4;
    public static final int BIN_LEN_INT8 = 8;
    public static final int BIN_LEN_FLOAT = 4;
    public static final int BIN_LEN_DOUBLE = 8;
    public static final int BIN_LEN_DATE = 4;
    public static final int BIN_LEN_TIMESTAMP = 11;
    public static final int BIN_LEN_TIMESTAMP_NO_US = 7;
    public static final int BIN_LEN_TIME = 8;
    public static final int BIN_LEN_TIME_NO_US = 12;
    public static final int COM_SLEEP = 0;
    public static final int COM_QUIT = 1;
    public static final int COM_INIT_DB = 2;
    public static final int COM_QUERY = 3;
    public static final int COM_FIELD_LIST = 4;
    public static final int COM_CREATE_DB = 5;
    public static final int COM_DROP_DB = 6;
    public static final int COM_REFRESH = 7;
    public static final int COM_SHUTDOWN = 8;
    public static final int COM_STATISTICS = 9;
    public static final int COM_PROCESS_INFO = 10;
    public static final int COM_CONNECT = 11;
    public static final int COM_PROCESS_KILL = 12;
    public static final int COM_DEBUG = 13;
    public static final int COM_PING = 14;
    public static final int COM_TIME = 15;
    public static final int COM_DELAYED_INSERT = 16;
    public static final int COM_CHANGE_USER = 17;
    public static final int COM_BINLOG_DUMP = 18;
    public static final int COM_TABLE_DUMP = 19;
    public static final int COM_CONNECT_OUT = 20;
    public static final int COM_REGISTER_SLAVE = 21;
    public static final int COM_STMT_PREPARE = 22;
    public static final int COM_STMT_EXECUTE = 23;
    public static final int COM_STMT_SEND_LONG_DATA = 24;
    public static final int COM_STMT_CLOSE = 25;
    public static final int COM_STMT_RESET = 26;
    public static final int COM_SET_OPTION = 27;
    public static final int COM_STMT_FETCH = 28;
    public static final int COM_DAEMON = 29;
    public static final int COM_BINLOG_DUMP_GTID = 30;
    public static final int COM_RESET_CONNECTION = 31;
    public static final int NO_CHARSET_INFO = -1;

    public static enum StringSelfDataType {
        STRING_TERM,
        STRING_LENENC,
        STRING_EOF;

    }

    public static enum StringLengthDataType {
        STRING_FIXED,
        STRING_VAR;

    }

    public static enum IntegerDataType {
        INT1,
        INT2,
        INT3,
        INT4,
        INT6,
        INT8,
        INT_LENENC;

    }
}

