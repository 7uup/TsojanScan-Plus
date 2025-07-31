/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.protobuf;

import com.google.protobuf.ByteString;

public final class WireFormat {
    public static final int WIRETYPE_VARINT = 0;
    public static final int WIRETYPE_FIXED64 = 1;
    public static final int WIRETYPE_LENGTH_DELIMITED = 2;
    public static final int WIRETYPE_START_GROUP = 3;
    public static final int WIRETYPE_END_GROUP = 4;
    public static final int WIRETYPE_FIXED32 = 5;
    static final int TAG_TYPE_BITS = 3;
    static final int TAG_TYPE_MASK = 7;
    static final int MESSAGE_SET_ITEM = 1;
    static final int MESSAGE_SET_TYPE_ID = 2;
    static final int MESSAGE_SET_MESSAGE = 3;
    static final int MESSAGE_SET_ITEM_TAG = WireFormat.makeTag(1, 3);
    static final int MESSAGE_SET_ITEM_END_TAG = WireFormat.makeTag(1, 4);
    static final int MESSAGE_SET_TYPE_ID_TAG = WireFormat.makeTag(2, 0);
    static final int MESSAGE_SET_MESSAGE_TAG = WireFormat.makeTag(3, 2);

    private WireFormat() {
    }

    static int getTagWireType(int tag) {
        return tag & 7;
    }

    public static int getTagFieldNumber(int tag) {
        return tag >>> 3;
    }

    static int makeTag(int fieldNumber, int wireType) {
        return fieldNumber << 3 | wireType;
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static enum FieldType {
        DOUBLE(JavaType.DOUBLE, 1),
        FLOAT(JavaType.FLOAT, 5),
        INT64(JavaType.LONG, 0),
        UINT64(JavaType.LONG, 0),
        INT32(JavaType.INT, 0),
        FIXED64(JavaType.LONG, 1),
        FIXED32(JavaType.INT, 5),
        BOOL(JavaType.BOOLEAN, 0),
        STRING(JavaType.STRING, 2){

            public boolean isPackable() {
                return false;
            }
        }
        ,
        GROUP(JavaType.MESSAGE, 3){

            public boolean isPackable() {
                return false;
            }
        }
        ,
        MESSAGE(JavaType.MESSAGE, 2){

            public boolean isPackable() {
                return false;
            }
        }
        ,
        BYTES(JavaType.BYTE_STRING, 2){

            public boolean isPackable() {
                return false;
            }
        }
        ,
        UINT32(JavaType.INT, 0),
        ENUM(JavaType.ENUM, 0),
        SFIXED32(JavaType.INT, 5),
        SFIXED64(JavaType.LONG, 1),
        SINT32(JavaType.INT, 0),
        SINT64(JavaType.LONG, 0);

        private final JavaType javaType;
        private final int wireType;

        private FieldType(JavaType javaType, int wireType) {
            this.javaType = javaType;
            this.wireType = wireType;
        }

        public JavaType getJavaType() {
            return this.javaType;
        }

        public int getWireType() {
            return this.wireType;
        }

        public boolean isPackable() {
            return true;
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static enum JavaType {
        INT(0),
        LONG(0L),
        FLOAT(Float.valueOf(0.0f)),
        DOUBLE(0.0),
        BOOLEAN(false),
        STRING(""),
        BYTE_STRING(ByteString.EMPTY),
        ENUM(null),
        MESSAGE(null);

        private final Object defaultDefault;

        private JavaType(Object defaultDefault) {
            this.defaultDefault = defaultDefault;
        }

        Object getDefaultDefault() {
            return this.defaultDefault;
        }
    }
}

