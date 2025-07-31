/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.x.protobuf;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.ProtocolMessageEnum;
import com.google.protobuf.RepeatedFieldBuilder;
import com.google.protobuf.SingleFieldBuilder;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectStreamException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class MysqlxDatatypes {
    private static final Descriptors.Descriptor internal_static_Mysqlx_Datatypes_Scalar_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_Mysqlx_Datatypes_Scalar_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_Mysqlx_Datatypes_Scalar_String_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_Mysqlx_Datatypes_Scalar_String_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_Mysqlx_Datatypes_Scalar_Octets_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_Mysqlx_Datatypes_Scalar_Octets_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_Mysqlx_Datatypes_Object_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_Mysqlx_Datatypes_Object_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_Mysqlx_Datatypes_Object_ObjectField_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_Mysqlx_Datatypes_Object_ObjectField_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_Mysqlx_Datatypes_Array_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_Mysqlx_Datatypes_Array_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_Mysqlx_Datatypes_Any_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_Mysqlx_Datatypes_Any_fieldAccessorTable;
    private static Descriptors.FileDescriptor descriptor;

    private MysqlxDatatypes() {
    }

    public static void registerAllExtensions(ExtensionRegistry registry) {
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        String[] descriptorData = new String[]{"\n\u0016mysqlx_datatypes.proto\u0012\u0010Mysqlx.Datatypes\"\u00c6\u0003\n\u0006Scalar\u0012+\n\u0004type\u0018\u0001 \u0002(\u000e2\u001d.Mysqlx.Datatypes.Scalar.Type\u0012\u0014\n\fv_signed_int\u0018\u0002 \u0001(\u0012\u0012\u0016\n\u000ev_unsigned_int\u0018\u0003 \u0001(\u0004\u00121\n\bv_octets\u0018\u0005 \u0001(\u000b2\u001f.Mysqlx.Datatypes.Scalar.Octets\u0012\u0010\n\bv_double\u0018\u0006 \u0001(\u0001\u0012\u000f\n\u0007v_float\u0018\u0007 \u0001(\u0002\u0012\u000e\n\u0006v_bool\u0018\b \u0001(\b\u00121\n\bv_string\u0018\t \u0001(\u000b2\u001f.Mysqlx.Datatypes.Scalar.String\u001a*\n\u0006String\u0012\r\n\u0005value\u0018\u0001 \u0002(\f\u0012\u0011\n\tcollation\u0018\u0002 \u0001(\u0004\u001a-\n\u0006Octets\u0012\r\n\u0005value\u0018\u0001 \u0002(\f\u0012\u0014\n\fcontent_type\u0018\u0002 \u0001(\r\"m\n\u0004Type\u0012\n\n\u0006", "V_SINT\u0010\u0001\u0012\n\n\u0006V_UINT\u0010\u0002\u0012\n\n\u0006V_NULL\u0010\u0003\u0012\f\n\bV_OCTETS\u0010\u0004\u0012\f\n\bV_DOUBLE\u0010\u0005\u0012\u000b\n\u0007V_FLOAT\u0010\u0006\u0012\n\n\u0006V_BOOL\u0010\u0007\u0012\f\n\bV_STRING\u0010\b\"}\n\u0006Object\u00121\n\u0003fld\u0018\u0001 \u0003(\u000b2$.Mysqlx.Datatypes.Object.ObjectField\u001a@\n\u000bObjectField\u0012\u000b\n\u0003key\u0018\u0001 \u0002(\t\u0012$\n\u0005value\u0018\u0002 \u0002(\u000b2\u0015.Mysqlx.Datatypes.Any\"-\n\u0005Array\u0012$\n\u0005value\u0018\u0001 \u0003(\u000b2\u0015.Mysqlx.Datatypes.Any\"\u00d3\u0001\n\u0003Any\u0012(\n\u0004type\u0018\u0001 \u0002(\u000e2\u001a.Mysqlx.Datatypes.Any.Type\u0012(\n\u0006scalar\u0018\u0002 \u0001(\u000b2\u0018.Mysqlx.Datatypes.Scalar\u0012%\n\u0003obj\u0018\u0003 \u0001(\u000b2\u0018.Mysqlx.Datatypes.Ob", "ject\u0012&\n\u0005array\u0018\u0004 \u0001(\u000b2\u0017.Mysqlx.Datatypes.Array\")\n\u0004Type\u0012\n\n\u0006SCALAR\u0010\u0001\u0012\n\n\u0006OBJECT\u0010\u0002\u0012\t\n\u0005ARRAY\u0010\u0003B\u0019\n\u0017com.mysql.cj.x.protobuf"};
        Descriptors.FileDescriptor.InternalDescriptorAssigner assigner = new Descriptors.FileDescriptor.InternalDescriptorAssigner(){

            @Override
            public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor root) {
                descriptor = root;
                return null;
            }
        };
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[0], assigner);
        internal_static_Mysqlx_Datatypes_Scalar_descriptor = MysqlxDatatypes.getDescriptor().getMessageTypes().get(0);
        internal_static_Mysqlx_Datatypes_Scalar_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_Mysqlx_Datatypes_Scalar_descriptor, new String[]{"Type", "VSignedInt", "VUnsignedInt", "VOctets", "VDouble", "VFloat", "VBool", "VString"});
        internal_static_Mysqlx_Datatypes_Scalar_String_descriptor = internal_static_Mysqlx_Datatypes_Scalar_descriptor.getNestedTypes().get(0);
        internal_static_Mysqlx_Datatypes_Scalar_String_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_Mysqlx_Datatypes_Scalar_String_descriptor, new String[]{"Value", "Collation"});
        internal_static_Mysqlx_Datatypes_Scalar_Octets_descriptor = internal_static_Mysqlx_Datatypes_Scalar_descriptor.getNestedTypes().get(1);
        internal_static_Mysqlx_Datatypes_Scalar_Octets_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_Mysqlx_Datatypes_Scalar_Octets_descriptor, new String[]{"Value", "ContentType"});
        internal_static_Mysqlx_Datatypes_Object_descriptor = MysqlxDatatypes.getDescriptor().getMessageTypes().get(1);
        internal_static_Mysqlx_Datatypes_Object_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_Mysqlx_Datatypes_Object_descriptor, new String[]{"Fld"});
        internal_static_Mysqlx_Datatypes_Object_ObjectField_descriptor = internal_static_Mysqlx_Datatypes_Object_descriptor.getNestedTypes().get(0);
        internal_static_Mysqlx_Datatypes_Object_ObjectField_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_Mysqlx_Datatypes_Object_ObjectField_descriptor, new String[]{"Key", "Value"});
        internal_static_Mysqlx_Datatypes_Array_descriptor = MysqlxDatatypes.getDescriptor().getMessageTypes().get(2);
        internal_static_Mysqlx_Datatypes_Array_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_Mysqlx_Datatypes_Array_descriptor, new String[]{"Value"});
        internal_static_Mysqlx_Datatypes_Any_descriptor = MysqlxDatatypes.getDescriptor().getMessageTypes().get(3);
        internal_static_Mysqlx_Datatypes_Any_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_Mysqlx_Datatypes_Any_descriptor, new String[]{"Type", "Scalar", "Obj", "Array"});
    }

    public static final class Any
    extends GeneratedMessage
    implements AnyOrBuilder {
        private static final Any defaultInstance;
        private final UnknownFieldSet unknownFields;
        public static Parser<Any> PARSER;
        private int bitField0_;
        public static final int TYPE_FIELD_NUMBER = 1;
        private Type type_;
        public static final int SCALAR_FIELD_NUMBER = 2;
        private Scalar scalar_;
        public static final int OBJ_FIELD_NUMBER = 3;
        private Object obj_;
        public static final int ARRAY_FIELD_NUMBER = 4;
        private Array array_;
        private byte memoizedIsInitialized = (byte)-1;
        private int memoizedSerializedSize = -1;
        private static final long serialVersionUID = 0L;

        private Any(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.unknownFields = builder.getUnknownFields();
        }

        private Any(boolean noInit) {
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static Any getDefaultInstance() {
            return defaultInstance;
        }

        @Override
        public Any getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private Any(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this.initFields();
            boolean mutable_bitField0_ = false;
            UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
            try {
                boolean done = false;
                block13: while (!done) {
                    int tag = input.readTag();
                    switch (tag) {
                        case 0: {
                            done = true;
                            continue block13;
                        }
                        default: {
                            if (this.parseUnknownField(input, unknownFields, extensionRegistry, tag)) continue block13;
                            done = true;
                            continue block13;
                        }
                        case 8: {
                            int rawValue = input.readEnum();
                            Type value = Type.valueOf(rawValue);
                            if (value == null) {
                                unknownFields.mergeVarintField(1, rawValue);
                                continue block13;
                            }
                            this.bitField0_ |= 1;
                            this.type_ = value;
                            continue block13;
                        }
                        case 18: {
                            Scalar.Builder subBuilder = null;
                            if ((this.bitField0_ & 2) == 2) {
                                subBuilder = this.scalar_.toBuilder();
                            }
                            this.scalar_ = input.readMessage(Scalar.PARSER, extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom(this.scalar_);
                                this.scalar_ = subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 2;
                            continue block13;
                        }
                        case 26: {
                            Object.Builder subBuilder = null;
                            if ((this.bitField0_ & 4) == 4) {
                                subBuilder = this.obj_.toBuilder();
                            }
                            this.obj_ = input.readMessage(Object.PARSER, extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom(this.obj_);
                                this.obj_ = subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 4;
                            continue block13;
                        }
                        case 34: 
                    }
                    Array.Builder subBuilder = null;
                    if ((this.bitField0_ & 8) == 8) {
                        subBuilder = this.array_.toBuilder();
                    }
                    this.array_ = input.readMessage(Array.PARSER, extensionRegistry);
                    if (subBuilder != null) {
                        subBuilder.mergeFrom(this.array_);
                        this.array_ = subBuilder.buildPartial();
                    }
                    this.bitField0_ |= 8;
                }
            } catch (InvalidProtocolBufferException e) {
                throw e.setUnfinishedMessage(this);
            } catch (IOException e) {
                throw new InvalidProtocolBufferException(e.getMessage()).setUnfinishedMessage(this);
            } finally {
                this.unknownFields = unknownFields.build();
                this.makeExtensionsImmutable();
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return internal_static_Mysqlx_Datatypes_Any_descriptor;
        }

        @Override
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return internal_static_Mysqlx_Datatypes_Any_fieldAccessorTable.ensureFieldAccessorsInitialized(Any.class, Builder.class);
        }

        public Parser<Any> getParserForType() {
            return PARSER;
        }

        @Override
        public boolean hasType() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override
        public Type getType() {
            return this.type_;
        }

        @Override
        public boolean hasScalar() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override
        public Scalar getScalar() {
            return this.scalar_;
        }

        @Override
        public ScalarOrBuilder getScalarOrBuilder() {
            return this.scalar_;
        }

        @Override
        public boolean hasObj() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override
        public Object getObj() {
            return this.obj_;
        }

        @Override
        public ObjectOrBuilder getObjOrBuilder() {
            return this.obj_;
        }

        @Override
        public boolean hasArray() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override
        public Array getArray() {
            return this.array_;
        }

        @Override
        public ArrayOrBuilder getArrayOrBuilder() {
            return this.array_;
        }

        private void initFields() {
            this.type_ = Type.SCALAR;
            this.scalar_ = Scalar.getDefaultInstance();
            this.obj_ = Object.getDefaultInstance();
            this.array_ = Array.getDefaultInstance();
        }

        @Override
        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            if (!this.hasType()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            if (this.hasScalar() && !this.getScalar().isInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            if (this.hasObj() && !this.getObj().isInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            if (this.hasArray() && !this.getArray().isInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        @Override
        public void writeTo(CodedOutputStream output) throws IOException {
            this.getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                output.writeEnum(1, this.type_.getNumber());
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeMessage(2, this.scalar_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeMessage(3, this.obj_);
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeMessage(4, this.array_);
            }
            this.getUnknownFields().writeTo(output);
        }

        @Override
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            size = 0;
            if ((this.bitField0_ & 1) == 1) {
                size += CodedOutputStream.computeEnumSize(1, this.type_.getNumber());
            }
            if ((this.bitField0_ & 2) == 2) {
                size += CodedOutputStream.computeMessageSize(2, this.scalar_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size += CodedOutputStream.computeMessageSize(3, this.obj_);
            }
            if ((this.bitField0_ & 8) == 8) {
                size += CodedOutputStream.computeMessageSize(4, this.array_);
            }
            this.memoizedSerializedSize = size += this.getUnknownFields().getSerializedSize();
            return size;
        }

        @Override
        protected java.lang.Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static Any parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static Any parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static Any parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static Any parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static Any parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static Any parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Any parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static Any parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static Any parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static Any parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override
        public Builder newBuilderForType() {
            return Any.newBuilder();
        }

        public static Builder newBuilder(Any prototype) {
            return Any.newBuilder().mergeFrom(prototype);
        }

        @Override
        public Builder toBuilder() {
            return Any.newBuilder(this);
        }

        @Override
        protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        static {
            PARSER = new AbstractParser<Any>(){

                @Override
                public Any parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                    return new Any(input, extensionRegistry);
                }
            };
            defaultInstance = new Any(true);
            defaultInstance.initFields();
        }

        public static final class Builder
        extends GeneratedMessage.Builder<Builder>
        implements AnyOrBuilder {
            private int bitField0_;
            private Type type_ = Type.SCALAR;
            private Scalar scalar_ = Scalar.getDefaultInstance();
            private SingleFieldBuilder<Scalar, Scalar.Builder, ScalarOrBuilder> scalarBuilder_;
            private Object obj_ = Object.getDefaultInstance();
            private SingleFieldBuilder<Object, Object.Builder, ObjectOrBuilder> objBuilder_;
            private Array array_ = Array.getDefaultInstance();
            private SingleFieldBuilder<Array, Array.Builder, ArrayOrBuilder> arrayBuilder_;

            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_Mysqlx_Datatypes_Any_descriptor;
            }

            @Override
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_Mysqlx_Datatypes_Any_fieldAccessorTable.ensureFieldAccessorsInitialized(Any.class, Builder.class);
            }

            private Builder() {
                this.maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessage.BuilderParent parent) {
                super(parent);
                this.maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                if (alwaysUseFieldBuilders) {
                    this.getScalarFieldBuilder();
                    this.getObjFieldBuilder();
                    this.getArrayFieldBuilder();
                }
            }

            private static Builder create() {
                return new Builder();
            }

            @Override
            public Builder clear() {
                super.clear();
                this.type_ = Type.SCALAR;
                this.bitField0_ &= 0xFFFFFFFE;
                if (this.scalarBuilder_ == null) {
                    this.scalar_ = Scalar.getDefaultInstance();
                } else {
                    this.scalarBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFFFD;
                if (this.objBuilder_ == null) {
                    this.obj_ = Object.getDefaultInstance();
                } else {
                    this.objBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFFFB;
                if (this.arrayBuilder_ == null) {
                    this.array_ = Array.getDefaultInstance();
                } else {
                    this.arrayBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFFF7;
                return this;
            }

            @Override
            public Builder clone() {
                return Builder.create().mergeFrom(this.buildPartial());
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return internal_static_Mysqlx_Datatypes_Any_descriptor;
            }

            @Override
            public Any getDefaultInstanceForType() {
                return Any.getDefaultInstance();
            }

            @Override
            public Any build() {
                Any result = this.buildPartial();
                if (!result.isInitialized()) {
                    throw Builder.newUninitializedMessageException(result);
                }
                return result;
            }

            @Override
            public Any buildPartial() {
                Any result = new Any(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ |= 1;
                }
                result.type_ = this.type_;
                if ((from_bitField0_ & 2) == 2) {
                    to_bitField0_ |= 2;
                }
                if (this.scalarBuilder_ == null) {
                    result.scalar_ = this.scalar_;
                } else {
                    result.scalar_ = this.scalarBuilder_.build();
                }
                if ((from_bitField0_ & 4) == 4) {
                    to_bitField0_ |= 4;
                }
                if (this.objBuilder_ == null) {
                    result.obj_ = this.obj_;
                } else {
                    result.obj_ = this.objBuilder_.build();
                }
                if ((from_bitField0_ & 8) == 8) {
                    to_bitField0_ |= 8;
                }
                if (this.arrayBuilder_ == null) {
                    result.array_ = this.array_;
                } else {
                    result.array_ = this.arrayBuilder_.build();
                }
                result.bitField0_ = to_bitField0_;
                this.onBuilt();
                return result;
            }

            @Override
            public Builder mergeFrom(Message other) {
                if (other instanceof Any) {
                    return this.mergeFrom((Any)other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(Any other) {
                if (other == Any.getDefaultInstance()) {
                    return this;
                }
                if (other.hasType()) {
                    this.setType(other.getType());
                }
                if (other.hasScalar()) {
                    this.mergeScalar(other.getScalar());
                }
                if (other.hasObj()) {
                    this.mergeObj(other.getObj());
                }
                if (other.hasArray()) {
                    this.mergeArray(other.getArray());
                }
                this.mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            @Override
            public final boolean isInitialized() {
                if (!this.hasType()) {
                    return false;
                }
                if (this.hasScalar() && !this.getScalar().isInitialized()) {
                    return false;
                }
                if (this.hasObj() && !this.getObj().isInitialized()) {
                    return false;
                }
                return !this.hasArray() || this.getArray().isInitialized();
            }

            @Override
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                Any parsedMessage = null;
                try {
                    parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
                } catch (InvalidProtocolBufferException e) {
                    parsedMessage = (Any)e.getUnfinishedMessage();
                    throw e;
                } finally {
                    if (parsedMessage != null) {
                        this.mergeFrom(parsedMessage);
                    }
                }
                return this;
            }

            @Override
            public boolean hasType() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override
            public Type getType() {
                return this.type_;
            }

            public Builder setType(Type value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 1;
                this.type_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearType() {
                this.bitField0_ &= 0xFFFFFFFE;
                this.type_ = Type.SCALAR;
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasScalar() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override
            public Scalar getScalar() {
                if (this.scalarBuilder_ == null) {
                    return this.scalar_;
                }
                return this.scalarBuilder_.getMessage();
            }

            public Builder setScalar(Scalar value) {
                if (this.scalarBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.scalar_ = value;
                    this.onChanged();
                } else {
                    this.scalarBuilder_.setMessage(value);
                }
                this.bitField0_ |= 2;
                return this;
            }

            public Builder setScalar(Scalar.Builder builderForValue) {
                if (this.scalarBuilder_ == null) {
                    this.scalar_ = builderForValue.build();
                    this.onChanged();
                } else {
                    this.scalarBuilder_.setMessage(builderForValue.build());
                }
                this.bitField0_ |= 2;
                return this;
            }

            public Builder mergeScalar(Scalar value) {
                if (this.scalarBuilder_ == null) {
                    this.scalar_ = (this.bitField0_ & 2) == 2 && this.scalar_ != Scalar.getDefaultInstance() ? Scalar.newBuilder(this.scalar_).mergeFrom(value).buildPartial() : value;
                    this.onChanged();
                } else {
                    this.scalarBuilder_.mergeFrom(value);
                }
                this.bitField0_ |= 2;
                return this;
            }

            public Builder clearScalar() {
                if (this.scalarBuilder_ == null) {
                    this.scalar_ = Scalar.getDefaultInstance();
                    this.onChanged();
                } else {
                    this.scalarBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFFFD;
                return this;
            }

            public Scalar.Builder getScalarBuilder() {
                this.bitField0_ |= 2;
                this.onChanged();
                return this.getScalarFieldBuilder().getBuilder();
            }

            @Override
            public ScalarOrBuilder getScalarOrBuilder() {
                if (this.scalarBuilder_ != null) {
                    return this.scalarBuilder_.getMessageOrBuilder();
                }
                return this.scalar_;
            }

            private SingleFieldBuilder<Scalar, Scalar.Builder, ScalarOrBuilder> getScalarFieldBuilder() {
                if (this.scalarBuilder_ == null) {
                    this.scalarBuilder_ = new SingleFieldBuilder(this.getScalar(), this.getParentForChildren(), this.isClean());
                    this.scalar_ = null;
                }
                return this.scalarBuilder_;
            }

            @Override
            public boolean hasObj() {
                return (this.bitField0_ & 4) == 4;
            }

            @Override
            public Object getObj() {
                if (this.objBuilder_ == null) {
                    return this.obj_;
                }
                return this.objBuilder_.getMessage();
            }

            public Builder setObj(Object value) {
                if (this.objBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.obj_ = value;
                    this.onChanged();
                } else {
                    this.objBuilder_.setMessage(value);
                }
                this.bitField0_ |= 4;
                return this;
            }

            public Builder setObj(Object.Builder builderForValue) {
                if (this.objBuilder_ == null) {
                    this.obj_ = builderForValue.build();
                    this.onChanged();
                } else {
                    this.objBuilder_.setMessage(builderForValue.build());
                }
                this.bitField0_ |= 4;
                return this;
            }

            public Builder mergeObj(Object value) {
                if (this.objBuilder_ == null) {
                    this.obj_ = (this.bitField0_ & 4) == 4 && this.obj_ != Object.getDefaultInstance() ? Object.newBuilder(this.obj_).mergeFrom(value).buildPartial() : value;
                    this.onChanged();
                } else {
                    this.objBuilder_.mergeFrom(value);
                }
                this.bitField0_ |= 4;
                return this;
            }

            public Builder clearObj() {
                if (this.objBuilder_ == null) {
                    this.obj_ = Object.getDefaultInstance();
                    this.onChanged();
                } else {
                    this.objBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFFFB;
                return this;
            }

            public Object.Builder getObjBuilder() {
                this.bitField0_ |= 4;
                this.onChanged();
                return this.getObjFieldBuilder().getBuilder();
            }

            @Override
            public ObjectOrBuilder getObjOrBuilder() {
                if (this.objBuilder_ != null) {
                    return this.objBuilder_.getMessageOrBuilder();
                }
                return this.obj_;
            }

            private SingleFieldBuilder<Object, Object.Builder, ObjectOrBuilder> getObjFieldBuilder() {
                if (this.objBuilder_ == null) {
                    this.objBuilder_ = new SingleFieldBuilder(this.getObj(), this.getParentForChildren(), this.isClean());
                    this.obj_ = null;
                }
                return this.objBuilder_;
            }

            @Override
            public boolean hasArray() {
                return (this.bitField0_ & 8) == 8;
            }

            @Override
            public Array getArray() {
                if (this.arrayBuilder_ == null) {
                    return this.array_;
                }
                return this.arrayBuilder_.getMessage();
            }

            public Builder setArray(Array value) {
                if (this.arrayBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.array_ = value;
                    this.onChanged();
                } else {
                    this.arrayBuilder_.setMessage(value);
                }
                this.bitField0_ |= 8;
                return this;
            }

            public Builder setArray(Array.Builder builderForValue) {
                if (this.arrayBuilder_ == null) {
                    this.array_ = builderForValue.build();
                    this.onChanged();
                } else {
                    this.arrayBuilder_.setMessage(builderForValue.build());
                }
                this.bitField0_ |= 8;
                return this;
            }

            public Builder mergeArray(Array value) {
                if (this.arrayBuilder_ == null) {
                    this.array_ = (this.bitField0_ & 8) == 8 && this.array_ != Array.getDefaultInstance() ? Array.newBuilder(this.array_).mergeFrom(value).buildPartial() : value;
                    this.onChanged();
                } else {
                    this.arrayBuilder_.mergeFrom(value);
                }
                this.bitField0_ |= 8;
                return this;
            }

            public Builder clearArray() {
                if (this.arrayBuilder_ == null) {
                    this.array_ = Array.getDefaultInstance();
                    this.onChanged();
                } else {
                    this.arrayBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFFF7;
                return this;
            }

            public Array.Builder getArrayBuilder() {
                this.bitField0_ |= 8;
                this.onChanged();
                return this.getArrayFieldBuilder().getBuilder();
            }

            @Override
            public ArrayOrBuilder getArrayOrBuilder() {
                if (this.arrayBuilder_ != null) {
                    return this.arrayBuilder_.getMessageOrBuilder();
                }
                return this.array_;
            }

            private SingleFieldBuilder<Array, Array.Builder, ArrayOrBuilder> getArrayFieldBuilder() {
                if (this.arrayBuilder_ == null) {
                    this.arrayBuilder_ = new SingleFieldBuilder(this.getArray(), this.getParentForChildren(), this.isClean());
                    this.array_ = null;
                }
                return this.arrayBuilder_;
            }
        }

        public static enum Type implements ProtocolMessageEnum
        {
            SCALAR(0, 1),
            OBJECT(1, 2),
            ARRAY(2, 3);

            public static final int SCALAR_VALUE = 1;
            public static final int OBJECT_VALUE = 2;
            public static final int ARRAY_VALUE = 3;
            private static Internal.EnumLiteMap<Type> internalValueMap;
            private static final Type[] VALUES;
            private final int index;
            private final int value;

            @Override
            public final int getNumber() {
                return this.value;
            }

            public static Type valueOf(int value) {
                switch (value) {
                    case 1: {
                        return SCALAR;
                    }
                    case 2: {
                        return OBJECT;
                    }
                    case 3: {
                        return ARRAY;
                    }
                }
                return null;
            }

            public static Internal.EnumLiteMap<Type> internalGetValueMap() {
                return internalValueMap;
            }

            @Override
            public final Descriptors.EnumValueDescriptor getValueDescriptor() {
                return Type.getDescriptor().getValues().get(this.index);
            }

            @Override
            public final Descriptors.EnumDescriptor getDescriptorForType() {
                return Type.getDescriptor();
            }

            public static final Descriptors.EnumDescriptor getDescriptor() {
                return Any.getDescriptor().getEnumTypes().get(0);
            }

            public static Type valueOf(Descriptors.EnumValueDescriptor desc) {
                if (desc.getType() != Type.getDescriptor()) {
                    throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
                }
                return VALUES[desc.getIndex()];
            }

            private Type(int index, int value) {
                this.index = index;
                this.value = value;
            }

            static {
                internalValueMap = new Internal.EnumLiteMap<Type>(){

                    @Override
                    public Type findValueByNumber(int number) {
                        return Type.valueOf(number);
                    }
                };
                VALUES = Type.values();
            }
        }
    }

    public static interface AnyOrBuilder
    extends MessageOrBuilder {
        public boolean hasType();

        public Any.Type getType();

        public boolean hasScalar();

        public Scalar getScalar();

        public ScalarOrBuilder getScalarOrBuilder();

        public boolean hasObj();

        public Object getObj();

        public ObjectOrBuilder getObjOrBuilder();

        public boolean hasArray();

        public Array getArray();

        public ArrayOrBuilder getArrayOrBuilder();
    }

    public static final class Array
    extends GeneratedMessage
    implements ArrayOrBuilder {
        private static final Array defaultInstance;
        private final UnknownFieldSet unknownFields;
        public static Parser<Array> PARSER;
        public static final int VALUE_FIELD_NUMBER = 1;
        private List<Any> value_;
        private byte memoizedIsInitialized = (byte)-1;
        private int memoizedSerializedSize = -1;
        private static final long serialVersionUID = 0L;

        private Array(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.unknownFields = builder.getUnknownFields();
        }

        private Array(boolean noInit) {
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static Array getDefaultInstance() {
            return defaultInstance;
        }

        @Override
        public Array getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private Array(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this.initFields();
            boolean mutable_bitField0_ = false;
            UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
            try {
                boolean done = false;
                block10: while (!done) {
                    int tag = input.readTag();
                    switch (tag) {
                        case 0: {
                            done = true;
                            continue block10;
                        }
                        default: {
                            if (this.parseUnknownField(input, unknownFields, extensionRegistry, tag)) continue block10;
                            done = true;
                            continue block10;
                        }
                        case 10: 
                    }
                    if (!(mutable_bitField0_ & true)) {
                        this.value_ = new ArrayList<Any>();
                        mutable_bitField0_ |= true;
                    }
                    this.value_.add(input.readMessage(Any.PARSER, extensionRegistry));
                }
            } catch (InvalidProtocolBufferException e) {
                throw e.setUnfinishedMessage(this);
            } catch (IOException e) {
                throw new InvalidProtocolBufferException(e.getMessage()).setUnfinishedMessage(this);
            } finally {
                if (mutable_bitField0_ & true) {
                    this.value_ = Collections.unmodifiableList(this.value_);
                }
                this.unknownFields = unknownFields.build();
                this.makeExtensionsImmutable();
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return internal_static_Mysqlx_Datatypes_Array_descriptor;
        }

        @Override
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return internal_static_Mysqlx_Datatypes_Array_fieldAccessorTable.ensureFieldAccessorsInitialized(Array.class, Builder.class);
        }

        public Parser<Array> getParserForType() {
            return PARSER;
        }

        @Override
        public List<Any> getValueList() {
            return this.value_;
        }

        @Override
        public List<? extends AnyOrBuilder> getValueOrBuilderList() {
            return this.value_;
        }

        @Override
        public int getValueCount() {
            return this.value_.size();
        }

        @Override
        public Any getValue(int index) {
            return this.value_.get(index);
        }

        @Override
        public AnyOrBuilder getValueOrBuilder(int index) {
            return this.value_.get(index);
        }

        private void initFields() {
            this.value_ = Collections.emptyList();
        }

        @Override
        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            for (int i = 0; i < this.getValueCount(); ++i) {
                if (this.getValue(i).isInitialized()) continue;
                this.memoizedIsInitialized = 0;
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        @Override
        public void writeTo(CodedOutputStream output) throws IOException {
            this.getSerializedSize();
            for (int i = 0; i < this.value_.size(); ++i) {
                output.writeMessage(1, this.value_.get(i));
            }
            this.getUnknownFields().writeTo(output);
        }

        @Override
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            size = 0;
            for (int i = 0; i < this.value_.size(); ++i) {
                size += CodedOutputStream.computeMessageSize(1, this.value_.get(i));
            }
            this.memoizedSerializedSize = size += this.getUnknownFields().getSerializedSize();
            return size;
        }

        @Override
        protected java.lang.Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static Array parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static Array parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static Array parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static Array parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static Array parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static Array parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Array parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static Array parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static Array parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static Array parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override
        public Builder newBuilderForType() {
            return Array.newBuilder();
        }

        public static Builder newBuilder(Array prototype) {
            return Array.newBuilder().mergeFrom(prototype);
        }

        @Override
        public Builder toBuilder() {
            return Array.newBuilder(this);
        }

        @Override
        protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        static {
            PARSER = new AbstractParser<Array>(){

                @Override
                public Array parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                    return new Array(input, extensionRegistry);
                }
            };
            defaultInstance = new Array(true);
            defaultInstance.initFields();
        }

        public static final class Builder
        extends GeneratedMessage.Builder<Builder>
        implements ArrayOrBuilder {
            private int bitField0_;
            private List<Any> value_ = Collections.emptyList();
            private RepeatedFieldBuilder<Any, Any.Builder, AnyOrBuilder> valueBuilder_;

            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_Mysqlx_Datatypes_Array_descriptor;
            }

            @Override
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_Mysqlx_Datatypes_Array_fieldAccessorTable.ensureFieldAccessorsInitialized(Array.class, Builder.class);
            }

            private Builder() {
                this.maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessage.BuilderParent parent) {
                super(parent);
                this.maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                if (alwaysUseFieldBuilders) {
                    this.getValueFieldBuilder();
                }
            }

            private static Builder create() {
                return new Builder();
            }

            @Override
            public Builder clear() {
                super.clear();
                if (this.valueBuilder_ == null) {
                    this.value_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFFFFE;
                } else {
                    this.valueBuilder_.clear();
                }
                return this;
            }

            @Override
            public Builder clone() {
                return Builder.create().mergeFrom(this.buildPartial());
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return internal_static_Mysqlx_Datatypes_Array_descriptor;
            }

            @Override
            public Array getDefaultInstanceForType() {
                return Array.getDefaultInstance();
            }

            @Override
            public Array build() {
                Array result = this.buildPartial();
                if (!result.isInitialized()) {
                    throw Builder.newUninitializedMessageException(result);
                }
                return result;
            }

            @Override
            public Array buildPartial() {
                Array result = new Array(this);
                int from_bitField0_ = this.bitField0_;
                if (this.valueBuilder_ == null) {
                    if ((this.bitField0_ & 1) == 1) {
                        this.value_ = Collections.unmodifiableList(this.value_);
                        this.bitField0_ &= 0xFFFFFFFE;
                    }
                    result.value_ = this.value_;
                } else {
                    result.value_ = this.valueBuilder_.build();
                }
                this.onBuilt();
                return result;
            }

            @Override
            public Builder mergeFrom(Message other) {
                if (other instanceof Array) {
                    return this.mergeFrom((Array)other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(Array other) {
                if (other == Array.getDefaultInstance()) {
                    return this;
                }
                if (this.valueBuilder_ == null) {
                    if (!other.value_.isEmpty()) {
                        if (this.value_.isEmpty()) {
                            this.value_ = other.value_;
                            this.bitField0_ &= 0xFFFFFFFE;
                        } else {
                            this.ensureValueIsMutable();
                            this.value_.addAll(other.value_);
                        }
                        this.onChanged();
                    }
                } else if (!other.value_.isEmpty()) {
                    if (this.valueBuilder_.isEmpty()) {
                        this.valueBuilder_.dispose();
                        this.valueBuilder_ = null;
                        this.value_ = other.value_;
                        this.bitField0_ &= 0xFFFFFFFE;
                        this.valueBuilder_ = alwaysUseFieldBuilders ? this.getValueFieldBuilder() : null;
                    } else {
                        this.valueBuilder_.addAllMessages(other.value_);
                    }
                }
                this.mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            @Override
            public final boolean isInitialized() {
                for (int i = 0; i < this.getValueCount(); ++i) {
                    if (this.getValue(i).isInitialized()) continue;
                    return false;
                }
                return true;
            }

            @Override
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                Array parsedMessage = null;
                try {
                    parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
                } catch (InvalidProtocolBufferException e) {
                    parsedMessage = (Array)e.getUnfinishedMessage();
                    throw e;
                } finally {
                    if (parsedMessage != null) {
                        this.mergeFrom(parsedMessage);
                    }
                }
                return this;
            }

            private void ensureValueIsMutable() {
                if ((this.bitField0_ & 1) != 1) {
                    this.value_ = new ArrayList<Any>(this.value_);
                    this.bitField0_ |= 1;
                }
            }

            @Override
            public List<Any> getValueList() {
                if (this.valueBuilder_ == null) {
                    return Collections.unmodifiableList(this.value_);
                }
                return this.valueBuilder_.getMessageList();
            }

            @Override
            public int getValueCount() {
                if (this.valueBuilder_ == null) {
                    return this.value_.size();
                }
                return this.valueBuilder_.getCount();
            }

            @Override
            public Any getValue(int index) {
                if (this.valueBuilder_ == null) {
                    return this.value_.get(index);
                }
                return this.valueBuilder_.getMessage(index);
            }

            public Builder setValue(int index, Any value) {
                if (this.valueBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureValueIsMutable();
                    this.value_.set(index, value);
                    this.onChanged();
                } else {
                    this.valueBuilder_.setMessage(index, value);
                }
                return this;
            }

            public Builder setValue(int index, Any.Builder builderForValue) {
                if (this.valueBuilder_ == null) {
                    this.ensureValueIsMutable();
                    this.value_.set(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.valueBuilder_.setMessage(index, builderForValue.build());
                }
                return this;
            }

            public Builder addValue(Any value) {
                if (this.valueBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureValueIsMutable();
                    this.value_.add(value);
                    this.onChanged();
                } else {
                    this.valueBuilder_.addMessage(value);
                }
                return this;
            }

            public Builder addValue(int index, Any value) {
                if (this.valueBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureValueIsMutable();
                    this.value_.add(index, value);
                    this.onChanged();
                } else {
                    this.valueBuilder_.addMessage(index, value);
                }
                return this;
            }

            public Builder addValue(Any.Builder builderForValue) {
                if (this.valueBuilder_ == null) {
                    this.ensureValueIsMutable();
                    this.value_.add(builderForValue.build());
                    this.onChanged();
                } else {
                    this.valueBuilder_.addMessage(builderForValue.build());
                }
                return this;
            }

            public Builder addValue(int index, Any.Builder builderForValue) {
                if (this.valueBuilder_ == null) {
                    this.ensureValueIsMutable();
                    this.value_.add(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.valueBuilder_.addMessage(index, builderForValue.build());
                }
                return this;
            }

            public Builder addAllValue(Iterable<? extends Any> values2) {
                if (this.valueBuilder_ == null) {
                    this.ensureValueIsMutable();
                    AbstractMessageLite.Builder.addAll(values2, this.value_);
                    this.onChanged();
                } else {
                    this.valueBuilder_.addAllMessages(values2);
                }
                return this;
            }

            public Builder clearValue() {
                if (this.valueBuilder_ == null) {
                    this.value_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFFFFE;
                    this.onChanged();
                } else {
                    this.valueBuilder_.clear();
                }
                return this;
            }

            public Builder removeValue(int index) {
                if (this.valueBuilder_ == null) {
                    this.ensureValueIsMutable();
                    this.value_.remove(index);
                    this.onChanged();
                } else {
                    this.valueBuilder_.remove(index);
                }
                return this;
            }

            public Any.Builder getValueBuilder(int index) {
                return this.getValueFieldBuilder().getBuilder(index);
            }

            @Override
            public AnyOrBuilder getValueOrBuilder(int index) {
                if (this.valueBuilder_ == null) {
                    return this.value_.get(index);
                }
                return this.valueBuilder_.getMessageOrBuilder(index);
            }

            @Override
            public List<? extends AnyOrBuilder> getValueOrBuilderList() {
                if (this.valueBuilder_ != null) {
                    return this.valueBuilder_.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.value_);
            }

            public Any.Builder addValueBuilder() {
                return this.getValueFieldBuilder().addBuilder(Any.getDefaultInstance());
            }

            public Any.Builder addValueBuilder(int index) {
                return this.getValueFieldBuilder().addBuilder(index, Any.getDefaultInstance());
            }

            public List<Any.Builder> getValueBuilderList() {
                return this.getValueFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilder<Any, Any.Builder, AnyOrBuilder> getValueFieldBuilder() {
                if (this.valueBuilder_ == null) {
                    this.valueBuilder_ = new RepeatedFieldBuilder(this.value_, (this.bitField0_ & 1) == 1, this.getParentForChildren(), this.isClean());
                    this.value_ = null;
                }
                return this.valueBuilder_;
            }
        }
    }

    public static interface ArrayOrBuilder
    extends MessageOrBuilder {
        public List<Any> getValueList();

        public Any getValue(int var1);

        public int getValueCount();

        public List<? extends AnyOrBuilder> getValueOrBuilderList();

        public AnyOrBuilder getValueOrBuilder(int var1);
    }

    public static final class Object
    extends GeneratedMessage
    implements ObjectOrBuilder {
        private static final Object defaultInstance;
        private final UnknownFieldSet unknownFields;
        public static Parser<Object> PARSER;
        public static final int FLD_FIELD_NUMBER = 1;
        private List<ObjectField> fld_;
        private byte memoizedIsInitialized = (byte)-1;
        private int memoizedSerializedSize = -1;
        private static final long serialVersionUID = 0L;

        private Object(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.unknownFields = builder.getUnknownFields();
        }

        private Object(boolean noInit) {
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static Object getDefaultInstance() {
            return defaultInstance;
        }

        @Override
        public Object getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private Object(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this.initFields();
            boolean mutable_bitField0_ = false;
            UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
            try {
                boolean done = false;
                block10: while (!done) {
                    int tag = input.readTag();
                    switch (tag) {
                        case 0: {
                            done = true;
                            continue block10;
                        }
                        default: {
                            if (this.parseUnknownField(input, unknownFields, extensionRegistry, tag)) continue block10;
                            done = true;
                            continue block10;
                        }
                        case 10: 
                    }
                    if (!(mutable_bitField0_ & true)) {
                        this.fld_ = new ArrayList<ObjectField>();
                        mutable_bitField0_ |= true;
                    }
                    this.fld_.add(input.readMessage(ObjectField.PARSER, extensionRegistry));
                }
            } catch (InvalidProtocolBufferException e) {
                throw e.setUnfinishedMessage(this);
            } catch (IOException e) {
                throw new InvalidProtocolBufferException(e.getMessage()).setUnfinishedMessage(this);
            } finally {
                if (mutable_bitField0_ & true) {
                    this.fld_ = Collections.unmodifiableList(this.fld_);
                }
                this.unknownFields = unknownFields.build();
                this.makeExtensionsImmutable();
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return internal_static_Mysqlx_Datatypes_Object_descriptor;
        }

        @Override
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return internal_static_Mysqlx_Datatypes_Object_fieldAccessorTable.ensureFieldAccessorsInitialized(Object.class, Builder.class);
        }

        public Parser<Object> getParserForType() {
            return PARSER;
        }

        @Override
        public List<ObjectField> getFldList() {
            return this.fld_;
        }

        @Override
        public List<? extends ObjectFieldOrBuilder> getFldOrBuilderList() {
            return this.fld_;
        }

        @Override
        public int getFldCount() {
            return this.fld_.size();
        }

        @Override
        public ObjectField getFld(int index) {
            return this.fld_.get(index);
        }

        @Override
        public ObjectFieldOrBuilder getFldOrBuilder(int index) {
            return this.fld_.get(index);
        }

        private void initFields() {
            this.fld_ = Collections.emptyList();
        }

        @Override
        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            for (int i = 0; i < this.getFldCount(); ++i) {
                if (this.getFld(i).isInitialized()) continue;
                this.memoizedIsInitialized = 0;
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        @Override
        public void writeTo(CodedOutputStream output) throws IOException {
            this.getSerializedSize();
            for (int i = 0; i < this.fld_.size(); ++i) {
                output.writeMessage(1, this.fld_.get(i));
            }
            this.getUnknownFields().writeTo(output);
        }

        @Override
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            size = 0;
            for (int i = 0; i < this.fld_.size(); ++i) {
                size += CodedOutputStream.computeMessageSize(1, this.fld_.get(i));
            }
            this.memoizedSerializedSize = size += this.getUnknownFields().getSerializedSize();
            return size;
        }

        @Override
        protected java.lang.Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static Object parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static Object parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static Object parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static Object parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static Object parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static Object parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Object parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static Object parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static Object parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static Object parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override
        public Builder newBuilderForType() {
            return Object.newBuilder();
        }

        public static Builder newBuilder(Object prototype) {
            return Object.newBuilder().mergeFrom(prototype);
        }

        @Override
        public Builder toBuilder() {
            return Object.newBuilder(this);
        }

        @Override
        protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        static {
            PARSER = new AbstractParser<Object>(){

                @Override
                public Object parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                    return new Object(input, extensionRegistry);
                }
            };
            defaultInstance = new Object(true);
            defaultInstance.initFields();
        }

        public static final class Builder
        extends GeneratedMessage.Builder<Builder>
        implements ObjectOrBuilder {
            private int bitField0_;
            private List<ObjectField> fld_ = Collections.emptyList();
            private RepeatedFieldBuilder<ObjectField, ObjectField.Builder, ObjectFieldOrBuilder> fldBuilder_;

            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_Mysqlx_Datatypes_Object_descriptor;
            }

            @Override
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_Mysqlx_Datatypes_Object_fieldAccessorTable.ensureFieldAccessorsInitialized(Object.class, Builder.class);
            }

            private Builder() {
                this.maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessage.BuilderParent parent) {
                super(parent);
                this.maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                if (alwaysUseFieldBuilders) {
                    this.getFldFieldBuilder();
                }
            }

            private static Builder create() {
                return new Builder();
            }

            @Override
            public Builder clear() {
                super.clear();
                if (this.fldBuilder_ == null) {
                    this.fld_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFFFFE;
                } else {
                    this.fldBuilder_.clear();
                }
                return this;
            }

            @Override
            public Builder clone() {
                return Builder.create().mergeFrom(this.buildPartial());
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return internal_static_Mysqlx_Datatypes_Object_descriptor;
            }

            @Override
            public Object getDefaultInstanceForType() {
                return Object.getDefaultInstance();
            }

            @Override
            public Object build() {
                Object result = this.buildPartial();
                if (!result.isInitialized()) {
                    throw Builder.newUninitializedMessageException(result);
                }
                return result;
            }

            @Override
            public Object buildPartial() {
                Object result = new Object(this);
                int from_bitField0_ = this.bitField0_;
                if (this.fldBuilder_ == null) {
                    if ((this.bitField0_ & 1) == 1) {
                        this.fld_ = Collections.unmodifiableList(this.fld_);
                        this.bitField0_ &= 0xFFFFFFFE;
                    }
                    result.fld_ = this.fld_;
                } else {
                    result.fld_ = this.fldBuilder_.build();
                }
                this.onBuilt();
                return result;
            }

            @Override
            public Builder mergeFrom(Message other) {
                if (other instanceof Object) {
                    return this.mergeFrom((Object)other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(Object other) {
                if (other == Object.getDefaultInstance()) {
                    return this;
                }
                if (this.fldBuilder_ == null) {
                    if (!other.fld_.isEmpty()) {
                        if (this.fld_.isEmpty()) {
                            this.fld_ = other.fld_;
                            this.bitField0_ &= 0xFFFFFFFE;
                        } else {
                            this.ensureFldIsMutable();
                            this.fld_.addAll(other.fld_);
                        }
                        this.onChanged();
                    }
                } else if (!other.fld_.isEmpty()) {
                    if (this.fldBuilder_.isEmpty()) {
                        this.fldBuilder_.dispose();
                        this.fldBuilder_ = null;
                        this.fld_ = other.fld_;
                        this.bitField0_ &= 0xFFFFFFFE;
                        this.fldBuilder_ = alwaysUseFieldBuilders ? this.getFldFieldBuilder() : null;
                    } else {
                        this.fldBuilder_.addAllMessages(other.fld_);
                    }
                }
                this.mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            @Override
            public final boolean isInitialized() {
                for (int i = 0; i < this.getFldCount(); ++i) {
                    if (this.getFld(i).isInitialized()) continue;
                    return false;
                }
                return true;
            }

            @Override
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                Object parsedMessage = null;
                try {
                    parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
                } catch (InvalidProtocolBufferException e) {
                    parsedMessage = (Object)e.getUnfinishedMessage();
                    throw e;
                } finally {
                    if (parsedMessage != null) {
                        this.mergeFrom(parsedMessage);
                    }
                }
                return this;
            }

            private void ensureFldIsMutable() {
                if ((this.bitField0_ & 1) != 1) {
                    this.fld_ = new ArrayList<ObjectField>(this.fld_);
                    this.bitField0_ |= 1;
                }
            }

            @Override
            public List<ObjectField> getFldList() {
                if (this.fldBuilder_ == null) {
                    return Collections.unmodifiableList(this.fld_);
                }
                return this.fldBuilder_.getMessageList();
            }

            @Override
            public int getFldCount() {
                if (this.fldBuilder_ == null) {
                    return this.fld_.size();
                }
                return this.fldBuilder_.getCount();
            }

            @Override
            public ObjectField getFld(int index) {
                if (this.fldBuilder_ == null) {
                    return this.fld_.get(index);
                }
                return this.fldBuilder_.getMessage(index);
            }

            public Builder setFld(int index, ObjectField value) {
                if (this.fldBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureFldIsMutable();
                    this.fld_.set(index, value);
                    this.onChanged();
                } else {
                    this.fldBuilder_.setMessage(index, value);
                }
                return this;
            }

            public Builder setFld(int index, ObjectField.Builder builderForValue) {
                if (this.fldBuilder_ == null) {
                    this.ensureFldIsMutable();
                    this.fld_.set(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.fldBuilder_.setMessage(index, builderForValue.build());
                }
                return this;
            }

            public Builder addFld(ObjectField value) {
                if (this.fldBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureFldIsMutable();
                    this.fld_.add(value);
                    this.onChanged();
                } else {
                    this.fldBuilder_.addMessage(value);
                }
                return this;
            }

            public Builder addFld(int index, ObjectField value) {
                if (this.fldBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureFldIsMutable();
                    this.fld_.add(index, value);
                    this.onChanged();
                } else {
                    this.fldBuilder_.addMessage(index, value);
                }
                return this;
            }

            public Builder addFld(ObjectField.Builder builderForValue) {
                if (this.fldBuilder_ == null) {
                    this.ensureFldIsMutable();
                    this.fld_.add(builderForValue.build());
                    this.onChanged();
                } else {
                    this.fldBuilder_.addMessage(builderForValue.build());
                }
                return this;
            }

            public Builder addFld(int index, ObjectField.Builder builderForValue) {
                if (this.fldBuilder_ == null) {
                    this.ensureFldIsMutable();
                    this.fld_.add(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.fldBuilder_.addMessage(index, builderForValue.build());
                }
                return this;
            }

            public Builder addAllFld(Iterable<? extends ObjectField> values2) {
                if (this.fldBuilder_ == null) {
                    this.ensureFldIsMutable();
                    AbstractMessageLite.Builder.addAll(values2, this.fld_);
                    this.onChanged();
                } else {
                    this.fldBuilder_.addAllMessages(values2);
                }
                return this;
            }

            public Builder clearFld() {
                if (this.fldBuilder_ == null) {
                    this.fld_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFFFFE;
                    this.onChanged();
                } else {
                    this.fldBuilder_.clear();
                }
                return this;
            }

            public Builder removeFld(int index) {
                if (this.fldBuilder_ == null) {
                    this.ensureFldIsMutable();
                    this.fld_.remove(index);
                    this.onChanged();
                } else {
                    this.fldBuilder_.remove(index);
                }
                return this;
            }

            public ObjectField.Builder getFldBuilder(int index) {
                return this.getFldFieldBuilder().getBuilder(index);
            }

            @Override
            public ObjectFieldOrBuilder getFldOrBuilder(int index) {
                if (this.fldBuilder_ == null) {
                    return this.fld_.get(index);
                }
                return this.fldBuilder_.getMessageOrBuilder(index);
            }

            @Override
            public List<? extends ObjectFieldOrBuilder> getFldOrBuilderList() {
                if (this.fldBuilder_ != null) {
                    return this.fldBuilder_.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.fld_);
            }

            public ObjectField.Builder addFldBuilder() {
                return this.getFldFieldBuilder().addBuilder(ObjectField.getDefaultInstance());
            }

            public ObjectField.Builder addFldBuilder(int index) {
                return this.getFldFieldBuilder().addBuilder(index, ObjectField.getDefaultInstance());
            }

            public List<ObjectField.Builder> getFldBuilderList() {
                return this.getFldFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilder<ObjectField, ObjectField.Builder, ObjectFieldOrBuilder> getFldFieldBuilder() {
                if (this.fldBuilder_ == null) {
                    this.fldBuilder_ = new RepeatedFieldBuilder(this.fld_, (this.bitField0_ & 1) == 1, this.getParentForChildren(), this.isClean());
                    this.fld_ = null;
                }
                return this.fldBuilder_;
            }
        }

        public static final class ObjectField
        extends GeneratedMessage
        implements ObjectFieldOrBuilder {
            private static final ObjectField defaultInstance;
            private final UnknownFieldSet unknownFields;
            public static Parser<ObjectField> PARSER;
            private int bitField0_;
            public static final int KEY_FIELD_NUMBER = 1;
            private java.lang.Object key_;
            public static final int VALUE_FIELD_NUMBER = 2;
            private Any value_;
            private byte memoizedIsInitialized = (byte)-1;
            private int memoizedSerializedSize = -1;
            private static final long serialVersionUID = 0L;

            private ObjectField(GeneratedMessage.Builder<?> builder) {
                super(builder);
                this.unknownFields = builder.getUnknownFields();
            }

            private ObjectField(boolean noInit) {
                this.unknownFields = UnknownFieldSet.getDefaultInstance();
            }

            public static ObjectField getDefaultInstance() {
                return defaultInstance;
            }

            @Override
            public ObjectField getDefaultInstanceForType() {
                return defaultInstance;
            }

            @Override
            public final UnknownFieldSet getUnknownFields() {
                return this.unknownFields;
            }

            private ObjectField(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                this.initFields();
                boolean mutable_bitField0_ = false;
                UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
                try {
                    boolean done = false;
                    block11: while (!done) {
                        int tag = input.readTag();
                        switch (tag) {
                            case 0: {
                                done = true;
                                continue block11;
                            }
                            default: {
                                if (this.parseUnknownField(input, unknownFields, extensionRegistry, tag)) continue block11;
                                done = true;
                                continue block11;
                            }
                            case 10: {
                                ByteString bs = input.readBytes();
                                this.bitField0_ |= 1;
                                this.key_ = bs;
                                continue block11;
                            }
                            case 18: 
                        }
                        Any.Builder subBuilder = null;
                        if ((this.bitField0_ & 2) == 2) {
                            subBuilder = this.value_.toBuilder();
                        }
                        this.value_ = input.readMessage(Any.PARSER, extensionRegistry);
                        if (subBuilder != null) {
                            subBuilder.mergeFrom(this.value_);
                            this.value_ = subBuilder.buildPartial();
                        }
                        this.bitField0_ |= 2;
                    }
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e) {
                    throw new InvalidProtocolBufferException(e.getMessage()).setUnfinishedMessage(this);
                } finally {
                    this.unknownFields = unknownFields.build();
                    this.makeExtensionsImmutable();
                }
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_Mysqlx_Datatypes_Object_ObjectField_descriptor;
            }

            @Override
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_Mysqlx_Datatypes_Object_ObjectField_fieldAccessorTable.ensureFieldAccessorsInitialized(ObjectField.class, Builder.class);
            }

            public Parser<ObjectField> getParserForType() {
                return PARSER;
            }

            @Override
            public boolean hasKey() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override
            public String getKey() {
                java.lang.Object ref = this.key_;
                if (ref instanceof String) {
                    return (String)ref;
                }
                ByteString bs = (ByteString)ref;
                String s2 = bs.toStringUtf8();
                if (bs.isValidUtf8()) {
                    this.key_ = s2;
                }
                return s2;
            }

            @Override
            public ByteString getKeyBytes() {
                java.lang.Object ref = this.key_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String)ref);
                    this.key_ = b;
                    return b;
                }
                return (ByteString)ref;
            }

            @Override
            public boolean hasValue() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override
            public Any getValue() {
                return this.value_;
            }

            @Override
            public AnyOrBuilder getValueOrBuilder() {
                return this.value_;
            }

            private void initFields() {
                this.key_ = "";
                this.value_ = Any.getDefaultInstance();
            }

            @Override
            public final boolean isInitialized() {
                byte isInitialized = this.memoizedIsInitialized;
                if (isInitialized == 1) {
                    return true;
                }
                if (isInitialized == 0) {
                    return false;
                }
                if (!this.hasKey()) {
                    this.memoizedIsInitialized = 0;
                    return false;
                }
                if (!this.hasValue()) {
                    this.memoizedIsInitialized = 0;
                    return false;
                }
                if (!this.getValue().isInitialized()) {
                    this.memoizedIsInitialized = 0;
                    return false;
                }
                this.memoizedIsInitialized = 1;
                return true;
            }

            @Override
            public void writeTo(CodedOutputStream output) throws IOException {
                this.getSerializedSize();
                if ((this.bitField0_ & 1) == 1) {
                    output.writeBytes(1, this.getKeyBytes());
                }
                if ((this.bitField0_ & 2) == 2) {
                    output.writeMessage(2, this.value_);
                }
                this.getUnknownFields().writeTo(output);
            }

            @Override
            public int getSerializedSize() {
                int size = this.memoizedSerializedSize;
                if (size != -1) {
                    return size;
                }
                size = 0;
                if ((this.bitField0_ & 1) == 1) {
                    size += CodedOutputStream.computeBytesSize(1, this.getKeyBytes());
                }
                if ((this.bitField0_ & 2) == 2) {
                    size += CodedOutputStream.computeMessageSize(2, this.value_);
                }
                this.memoizedSerializedSize = size += this.getUnknownFields().getSerializedSize();
                return size;
            }

            @Override
            protected java.lang.Object writeReplace() throws ObjectStreamException {
                return super.writeReplace();
            }

            public static ObjectField parseFrom(ByteString data) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(data);
            }

            public static ObjectField parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(data, extensionRegistry);
            }

            public static ObjectField parseFrom(byte[] data) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(data);
            }

            public static ObjectField parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(data, extensionRegistry);
            }

            public static ObjectField parseFrom(InputStream input) throws IOException {
                return PARSER.parseFrom(input);
            }

            public static ObjectField parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return PARSER.parseFrom(input, extensionRegistry);
            }

            public static ObjectField parseDelimitedFrom(InputStream input) throws IOException {
                return PARSER.parseDelimitedFrom(input);
            }

            public static ObjectField parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return PARSER.parseDelimitedFrom(input, extensionRegistry);
            }

            public static ObjectField parseFrom(CodedInputStream input) throws IOException {
                return PARSER.parseFrom(input);
            }

            public static ObjectField parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return PARSER.parseFrom(input, extensionRegistry);
            }

            public static Builder newBuilder() {
                return Builder.create();
            }

            @Override
            public Builder newBuilderForType() {
                return ObjectField.newBuilder();
            }

            public static Builder newBuilder(ObjectField prototype) {
                return ObjectField.newBuilder().mergeFrom(prototype);
            }

            @Override
            public Builder toBuilder() {
                return ObjectField.newBuilder(this);
            }

            @Override
            protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
                Builder builder = new Builder(parent);
                return builder;
            }

            static {
                PARSER = new AbstractParser<ObjectField>(){

                    @Override
                    public ObjectField parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                        return new ObjectField(input, extensionRegistry);
                    }
                };
                defaultInstance = new ObjectField(true);
                defaultInstance.initFields();
            }

            public static final class Builder
            extends GeneratedMessage.Builder<Builder>
            implements ObjectFieldOrBuilder {
                private int bitField0_;
                private java.lang.Object key_ = "";
                private Any value_ = Any.getDefaultInstance();
                private SingleFieldBuilder<Any, Any.Builder, AnyOrBuilder> valueBuilder_;

                public static final Descriptors.Descriptor getDescriptor() {
                    return internal_static_Mysqlx_Datatypes_Object_ObjectField_descriptor;
                }

                @Override
                protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                    return internal_static_Mysqlx_Datatypes_Object_ObjectField_fieldAccessorTable.ensureFieldAccessorsInitialized(ObjectField.class, Builder.class);
                }

                private Builder() {
                    this.maybeForceBuilderInitialization();
                }

                private Builder(GeneratedMessage.BuilderParent parent) {
                    super(parent);
                    this.maybeForceBuilderInitialization();
                }

                private void maybeForceBuilderInitialization() {
                    if (alwaysUseFieldBuilders) {
                        this.getValueFieldBuilder();
                    }
                }

                private static Builder create() {
                    return new Builder();
                }

                @Override
                public Builder clear() {
                    super.clear();
                    this.key_ = "";
                    this.bitField0_ &= 0xFFFFFFFE;
                    if (this.valueBuilder_ == null) {
                        this.value_ = Any.getDefaultInstance();
                    } else {
                        this.valueBuilder_.clear();
                    }
                    this.bitField0_ &= 0xFFFFFFFD;
                    return this;
                }

                @Override
                public Builder clone() {
                    return Builder.create().mergeFrom(this.buildPartial());
                }

                @Override
                public Descriptors.Descriptor getDescriptorForType() {
                    return internal_static_Mysqlx_Datatypes_Object_ObjectField_descriptor;
                }

                @Override
                public ObjectField getDefaultInstanceForType() {
                    return ObjectField.getDefaultInstance();
                }

                @Override
                public ObjectField build() {
                    ObjectField result = this.buildPartial();
                    if (!result.isInitialized()) {
                        throw Builder.newUninitializedMessageException(result);
                    }
                    return result;
                }

                @Override
                public ObjectField buildPartial() {
                    ObjectField result = new ObjectField(this);
                    int from_bitField0_ = this.bitField0_;
                    int to_bitField0_ = 0;
                    if ((from_bitField0_ & 1) == 1) {
                        to_bitField0_ |= 1;
                    }
                    result.key_ = this.key_;
                    if ((from_bitField0_ & 2) == 2) {
                        to_bitField0_ |= 2;
                    }
                    if (this.valueBuilder_ == null) {
                        result.value_ = this.value_;
                    } else {
                        result.value_ = this.valueBuilder_.build();
                    }
                    result.bitField0_ = to_bitField0_;
                    this.onBuilt();
                    return result;
                }

                @Override
                public Builder mergeFrom(Message other) {
                    if (other instanceof ObjectField) {
                        return this.mergeFrom((ObjectField)other);
                    }
                    super.mergeFrom(other);
                    return this;
                }

                public Builder mergeFrom(ObjectField other) {
                    if (other == ObjectField.getDefaultInstance()) {
                        return this;
                    }
                    if (other.hasKey()) {
                        this.bitField0_ |= 1;
                        this.key_ = other.key_;
                        this.onChanged();
                    }
                    if (other.hasValue()) {
                        this.mergeValue(other.getValue());
                    }
                    this.mergeUnknownFields(other.getUnknownFields());
                    return this;
                }

                @Override
                public final boolean isInitialized() {
                    if (!this.hasKey()) {
                        return false;
                    }
                    if (!this.hasValue()) {
                        return false;
                    }
                    return this.getValue().isInitialized();
                }

                @Override
                public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                    ObjectField parsedMessage = null;
                    try {
                        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
                    } catch (InvalidProtocolBufferException e) {
                        parsedMessage = (ObjectField)e.getUnfinishedMessage();
                        throw e;
                    } finally {
                        if (parsedMessage != null) {
                            this.mergeFrom(parsedMessage);
                        }
                    }
                    return this;
                }

                @Override
                public boolean hasKey() {
                    return (this.bitField0_ & 1) == 1;
                }

                @Override
                public String getKey() {
                    java.lang.Object ref = this.key_;
                    if (!(ref instanceof String)) {
                        ByteString bs = (ByteString)ref;
                        String s2 = bs.toStringUtf8();
                        if (bs.isValidUtf8()) {
                            this.key_ = s2;
                        }
                        return s2;
                    }
                    return (String)ref;
                }

                @Override
                public ByteString getKeyBytes() {
                    java.lang.Object ref = this.key_;
                    if (ref instanceof String) {
                        ByteString b = ByteString.copyFromUtf8((String)ref);
                        this.key_ = b;
                        return b;
                    }
                    return (ByteString)ref;
                }

                public Builder setKey(String value) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.bitField0_ |= 1;
                    this.key_ = value;
                    this.onChanged();
                    return this;
                }

                public Builder clearKey() {
                    this.bitField0_ &= 0xFFFFFFFE;
                    this.key_ = ObjectField.getDefaultInstance().getKey();
                    this.onChanged();
                    return this;
                }

                public Builder setKeyBytes(ByteString value) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.bitField0_ |= 1;
                    this.key_ = value;
                    this.onChanged();
                    return this;
                }

                @Override
                public boolean hasValue() {
                    return (this.bitField0_ & 2) == 2;
                }

                @Override
                public Any getValue() {
                    if (this.valueBuilder_ == null) {
                        return this.value_;
                    }
                    return this.valueBuilder_.getMessage();
                }

                public Builder setValue(Any value) {
                    if (this.valueBuilder_ == null) {
                        if (value == null) {
                            throw new NullPointerException();
                        }
                        this.value_ = value;
                        this.onChanged();
                    } else {
                        this.valueBuilder_.setMessage(value);
                    }
                    this.bitField0_ |= 2;
                    return this;
                }

                public Builder setValue(Any.Builder builderForValue) {
                    if (this.valueBuilder_ == null) {
                        this.value_ = builderForValue.build();
                        this.onChanged();
                    } else {
                        this.valueBuilder_.setMessage(builderForValue.build());
                    }
                    this.bitField0_ |= 2;
                    return this;
                }

                public Builder mergeValue(Any value) {
                    if (this.valueBuilder_ == null) {
                        this.value_ = (this.bitField0_ & 2) == 2 && this.value_ != Any.getDefaultInstance() ? Any.newBuilder(this.value_).mergeFrom(value).buildPartial() : value;
                        this.onChanged();
                    } else {
                        this.valueBuilder_.mergeFrom(value);
                    }
                    this.bitField0_ |= 2;
                    return this;
                }

                public Builder clearValue() {
                    if (this.valueBuilder_ == null) {
                        this.value_ = Any.getDefaultInstance();
                        this.onChanged();
                    } else {
                        this.valueBuilder_.clear();
                    }
                    this.bitField0_ &= 0xFFFFFFFD;
                    return this;
                }

                public Any.Builder getValueBuilder() {
                    this.bitField0_ |= 2;
                    this.onChanged();
                    return this.getValueFieldBuilder().getBuilder();
                }

                @Override
                public AnyOrBuilder getValueOrBuilder() {
                    if (this.valueBuilder_ != null) {
                        return this.valueBuilder_.getMessageOrBuilder();
                    }
                    return this.value_;
                }

                private SingleFieldBuilder<Any, Any.Builder, AnyOrBuilder> getValueFieldBuilder() {
                    if (this.valueBuilder_ == null) {
                        this.valueBuilder_ = new SingleFieldBuilder(this.getValue(), this.getParentForChildren(), this.isClean());
                        this.value_ = null;
                    }
                    return this.valueBuilder_;
                }
            }
        }

        public static interface ObjectFieldOrBuilder
        extends MessageOrBuilder {
            public boolean hasKey();

            public String getKey();

            public ByteString getKeyBytes();

            public boolean hasValue();

            public Any getValue();

            public AnyOrBuilder getValueOrBuilder();
        }
    }

    public static interface ObjectOrBuilder
    extends MessageOrBuilder {
        public List<Object.ObjectField> getFldList();

        public Object.ObjectField getFld(int var1);

        public int getFldCount();

        public List<? extends Object.ObjectFieldOrBuilder> getFldOrBuilderList();

        public Object.ObjectFieldOrBuilder getFldOrBuilder(int var1);
    }

    public static final class Scalar
    extends GeneratedMessage
    implements ScalarOrBuilder {
        private static final Scalar defaultInstance;
        private final UnknownFieldSet unknownFields;
        public static Parser<Scalar> PARSER;
        private int bitField0_;
        public static final int TYPE_FIELD_NUMBER = 1;
        private Type type_;
        public static final int V_SIGNED_INT_FIELD_NUMBER = 2;
        private long vSignedInt_;
        public static final int V_UNSIGNED_INT_FIELD_NUMBER = 3;
        private long vUnsignedInt_;
        public static final int V_OCTETS_FIELD_NUMBER = 5;
        private Octets vOctets_;
        public static final int V_DOUBLE_FIELD_NUMBER = 6;
        private double vDouble_;
        public static final int V_FLOAT_FIELD_NUMBER = 7;
        private float vFloat_;
        public static final int V_BOOL_FIELD_NUMBER = 8;
        private boolean vBool_;
        public static final int V_STRING_FIELD_NUMBER = 9;
        private String vString_;
        private byte memoizedIsInitialized = (byte)-1;
        private int memoizedSerializedSize = -1;
        private static final long serialVersionUID = 0L;

        private Scalar(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.unknownFields = builder.getUnknownFields();
        }

        private Scalar(boolean noInit) {
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static Scalar getDefaultInstance() {
            return defaultInstance;
        }

        @Override
        public Scalar getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private Scalar(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this.initFields();
            boolean mutable_bitField0_ = false;
            UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
            try {
                boolean done = false;
                block17: while (!done) {
                    int tag = input.readTag();
                    switch (tag) {
                        case 0: {
                            done = true;
                            continue block17;
                        }
                        default: {
                            if (this.parseUnknownField(input, unknownFields, extensionRegistry, tag)) continue block17;
                            done = true;
                            continue block17;
                        }
                        case 8: {
                            int rawValue = input.readEnum();
                            Type value = Type.valueOf(rawValue);
                            if (value == null) {
                                unknownFields.mergeVarintField(1, rawValue);
                                continue block17;
                            }
                            this.bitField0_ |= 1;
                            this.type_ = value;
                            continue block17;
                        }
                        case 16: {
                            this.bitField0_ |= 2;
                            this.vSignedInt_ = input.readSInt64();
                            continue block17;
                        }
                        case 24: {
                            this.bitField0_ |= 4;
                            this.vUnsignedInt_ = input.readUInt64();
                            continue block17;
                        }
                        case 42: {
                            Octets.Builder subBuilder = null;
                            if ((this.bitField0_ & 8) == 8) {
                                subBuilder = this.vOctets_.toBuilder();
                            }
                            this.vOctets_ = input.readMessage(Octets.PARSER, extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom(this.vOctets_);
                                this.vOctets_ = subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 8;
                            continue block17;
                        }
                        case 49: {
                            this.bitField0_ |= 0x10;
                            this.vDouble_ = input.readDouble();
                            continue block17;
                        }
                        case 61: {
                            this.bitField0_ |= 0x20;
                            this.vFloat_ = input.readFloat();
                            continue block17;
                        }
                        case 64: {
                            this.bitField0_ |= 0x40;
                            this.vBool_ = input.readBool();
                            continue block17;
                        }
                        case 74: 
                    }
                    String.Builder subBuilder = null;
                    if ((this.bitField0_ & 0x80) == 128) {
                        subBuilder = this.vString_.toBuilder();
                    }
                    this.vString_ = input.readMessage(String.PARSER, extensionRegistry);
                    if (subBuilder != null) {
                        subBuilder.mergeFrom(this.vString_);
                        this.vString_ = subBuilder.buildPartial();
                    }
                    this.bitField0_ |= 0x80;
                }
            } catch (InvalidProtocolBufferException e) {
                throw e.setUnfinishedMessage(this);
            } catch (IOException e) {
                throw new InvalidProtocolBufferException(e.getMessage()).setUnfinishedMessage(this);
            } finally {
                this.unknownFields = unknownFields.build();
                this.makeExtensionsImmutable();
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return internal_static_Mysqlx_Datatypes_Scalar_descriptor;
        }

        @Override
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return internal_static_Mysqlx_Datatypes_Scalar_fieldAccessorTable.ensureFieldAccessorsInitialized(Scalar.class, Builder.class);
        }

        public Parser<Scalar> getParserForType() {
            return PARSER;
        }

        @Override
        public boolean hasType() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override
        public Type getType() {
            return this.type_;
        }

        @Override
        public boolean hasVSignedInt() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override
        public long getVSignedInt() {
            return this.vSignedInt_;
        }

        @Override
        public boolean hasVUnsignedInt() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override
        public long getVUnsignedInt() {
            return this.vUnsignedInt_;
        }

        @Override
        public boolean hasVOctets() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override
        public Octets getVOctets() {
            return this.vOctets_;
        }

        @Override
        public OctetsOrBuilder getVOctetsOrBuilder() {
            return this.vOctets_;
        }

        @Override
        public boolean hasVDouble() {
            return (this.bitField0_ & 0x10) == 16;
        }

        @Override
        public double getVDouble() {
            return this.vDouble_;
        }

        @Override
        public boolean hasVFloat() {
            return (this.bitField0_ & 0x20) == 32;
        }

        @Override
        public float getVFloat() {
            return this.vFloat_;
        }

        @Override
        public boolean hasVBool() {
            return (this.bitField0_ & 0x40) == 64;
        }

        @Override
        public boolean getVBool() {
            return this.vBool_;
        }

        @Override
        public boolean hasVString() {
            return (this.bitField0_ & 0x80) == 128;
        }

        @Override
        public String getVString() {
            return this.vString_;
        }

        @Override
        public StringOrBuilder getVStringOrBuilder() {
            return this.vString_;
        }

        private void initFields() {
            this.type_ = Type.V_SINT;
            this.vSignedInt_ = 0L;
            this.vUnsignedInt_ = 0L;
            this.vOctets_ = Octets.getDefaultInstance();
            this.vDouble_ = 0.0;
            this.vFloat_ = 0.0f;
            this.vBool_ = false;
            this.vString_ = String.getDefaultInstance();
        }

        @Override
        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            if (!this.hasType()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            if (this.hasVOctets() && !this.getVOctets().isInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            if (this.hasVString() && !this.getVString().isInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        @Override
        public void writeTo(CodedOutputStream output) throws IOException {
            this.getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                output.writeEnum(1, this.type_.getNumber());
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeSInt64(2, this.vSignedInt_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeUInt64(3, this.vUnsignedInt_);
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeMessage(5, this.vOctets_);
            }
            if ((this.bitField0_ & 0x10) == 16) {
                output.writeDouble(6, this.vDouble_);
            }
            if ((this.bitField0_ & 0x20) == 32) {
                output.writeFloat(7, this.vFloat_);
            }
            if ((this.bitField0_ & 0x40) == 64) {
                output.writeBool(8, this.vBool_);
            }
            if ((this.bitField0_ & 0x80) == 128) {
                output.writeMessage(9, this.vString_);
            }
            this.getUnknownFields().writeTo(output);
        }

        @Override
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            size = 0;
            if ((this.bitField0_ & 1) == 1) {
                size += CodedOutputStream.computeEnumSize(1, this.type_.getNumber());
            }
            if ((this.bitField0_ & 2) == 2) {
                size += CodedOutputStream.computeSInt64Size(2, this.vSignedInt_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size += CodedOutputStream.computeUInt64Size(3, this.vUnsignedInt_);
            }
            if ((this.bitField0_ & 8) == 8) {
                size += CodedOutputStream.computeMessageSize(5, this.vOctets_);
            }
            if ((this.bitField0_ & 0x10) == 16) {
                size += CodedOutputStream.computeDoubleSize(6, this.vDouble_);
            }
            if ((this.bitField0_ & 0x20) == 32) {
                size += CodedOutputStream.computeFloatSize(7, this.vFloat_);
            }
            if ((this.bitField0_ & 0x40) == 64) {
                size += CodedOutputStream.computeBoolSize(8, this.vBool_);
            }
            if ((this.bitField0_ & 0x80) == 128) {
                size += CodedOutputStream.computeMessageSize(9, this.vString_);
            }
            this.memoizedSerializedSize = size += this.getUnknownFields().getSerializedSize();
            return size;
        }

        @Override
        protected java.lang.Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static Scalar parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static Scalar parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static Scalar parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static Scalar parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static Scalar parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static Scalar parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Scalar parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static Scalar parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static Scalar parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static Scalar parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override
        public Builder newBuilderForType() {
            return Scalar.newBuilder();
        }

        public static Builder newBuilder(Scalar prototype) {
            return Scalar.newBuilder().mergeFrom(prototype);
        }

        @Override
        public Builder toBuilder() {
            return Scalar.newBuilder(this);
        }

        @Override
        protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        static {
            PARSER = new AbstractParser<Scalar>(){

                @Override
                public Scalar parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                    return new Scalar(input, extensionRegistry);
                }
            };
            defaultInstance = new Scalar(true);
            defaultInstance.initFields();
        }

        public static final class Builder
        extends GeneratedMessage.Builder<Builder>
        implements ScalarOrBuilder {
            private int bitField0_;
            private Type type_ = Type.V_SINT;
            private long vSignedInt_;
            private long vUnsignedInt_;
            private Octets vOctets_ = Octets.getDefaultInstance();
            private SingleFieldBuilder<Octets, Octets.Builder, OctetsOrBuilder> vOctetsBuilder_;
            private double vDouble_;
            private float vFloat_;
            private boolean vBool_;
            private String vString_ = String.getDefaultInstance();
            private SingleFieldBuilder<String, String.Builder, StringOrBuilder> vStringBuilder_;

            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_Mysqlx_Datatypes_Scalar_descriptor;
            }

            @Override
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_Mysqlx_Datatypes_Scalar_fieldAccessorTable.ensureFieldAccessorsInitialized(Scalar.class, Builder.class);
            }

            private Builder() {
                this.maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessage.BuilderParent parent) {
                super(parent);
                this.maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                if (alwaysUseFieldBuilders) {
                    this.getVOctetsFieldBuilder();
                    this.getVStringFieldBuilder();
                }
            }

            private static Builder create() {
                return new Builder();
            }

            @Override
            public Builder clear() {
                super.clear();
                this.type_ = Type.V_SINT;
                this.bitField0_ &= 0xFFFFFFFE;
                this.vSignedInt_ = 0L;
                this.bitField0_ &= 0xFFFFFFFD;
                this.vUnsignedInt_ = 0L;
                this.bitField0_ &= 0xFFFFFFFB;
                if (this.vOctetsBuilder_ == null) {
                    this.vOctets_ = Octets.getDefaultInstance();
                } else {
                    this.vOctetsBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFFF7;
                this.vDouble_ = 0.0;
                this.bitField0_ &= 0xFFFFFFEF;
                this.vFloat_ = 0.0f;
                this.bitField0_ &= 0xFFFFFFDF;
                this.vBool_ = false;
                this.bitField0_ &= 0xFFFFFFBF;
                if (this.vStringBuilder_ == null) {
                    this.vString_ = String.getDefaultInstance();
                } else {
                    this.vStringBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFF7F;
                return this;
            }

            @Override
            public Builder clone() {
                return Builder.create().mergeFrom(this.buildPartial());
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return internal_static_Mysqlx_Datatypes_Scalar_descriptor;
            }

            @Override
            public Scalar getDefaultInstanceForType() {
                return Scalar.getDefaultInstance();
            }

            @Override
            public Scalar build() {
                Scalar result = this.buildPartial();
                if (!result.isInitialized()) {
                    throw Builder.newUninitializedMessageException(result);
                }
                return result;
            }

            @Override
            public Scalar buildPartial() {
                Scalar result = new Scalar(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ |= 1;
                }
                result.type_ = this.type_;
                if ((from_bitField0_ & 2) == 2) {
                    to_bitField0_ |= 2;
                }
                result.vSignedInt_ = this.vSignedInt_;
                if ((from_bitField0_ & 4) == 4) {
                    to_bitField0_ |= 4;
                }
                result.vUnsignedInt_ = this.vUnsignedInt_;
                if ((from_bitField0_ & 8) == 8) {
                    to_bitField0_ |= 8;
                }
                if (this.vOctetsBuilder_ == null) {
                    result.vOctets_ = this.vOctets_;
                } else {
                    result.vOctets_ = this.vOctetsBuilder_.build();
                }
                if ((from_bitField0_ & 0x10) == 16) {
                    to_bitField0_ |= 0x10;
                }
                result.vDouble_ = this.vDouble_;
                if ((from_bitField0_ & 0x20) == 32) {
                    to_bitField0_ |= 0x20;
                }
                result.vFloat_ = this.vFloat_;
                if ((from_bitField0_ & 0x40) == 64) {
                    to_bitField0_ |= 0x40;
                }
                result.vBool_ = this.vBool_;
                if ((from_bitField0_ & 0x80) == 128) {
                    to_bitField0_ |= 0x80;
                }
                if (this.vStringBuilder_ == null) {
                    result.vString_ = this.vString_;
                } else {
                    result.vString_ = this.vStringBuilder_.build();
                }
                result.bitField0_ = to_bitField0_;
                this.onBuilt();
                return result;
            }

            @Override
            public Builder mergeFrom(Message other) {
                if (other instanceof Scalar) {
                    return this.mergeFrom((Scalar)other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(Scalar other) {
                if (other == Scalar.getDefaultInstance()) {
                    return this;
                }
                if (other.hasType()) {
                    this.setType(other.getType());
                }
                if (other.hasVSignedInt()) {
                    this.setVSignedInt(other.getVSignedInt());
                }
                if (other.hasVUnsignedInt()) {
                    this.setVUnsignedInt(other.getVUnsignedInt());
                }
                if (other.hasVOctets()) {
                    this.mergeVOctets(other.getVOctets());
                }
                if (other.hasVDouble()) {
                    this.setVDouble(other.getVDouble());
                }
                if (other.hasVFloat()) {
                    this.setVFloat(other.getVFloat());
                }
                if (other.hasVBool()) {
                    this.setVBool(other.getVBool());
                }
                if (other.hasVString()) {
                    this.mergeVString(other.getVString());
                }
                this.mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            @Override
            public final boolean isInitialized() {
                if (!this.hasType()) {
                    return false;
                }
                if (this.hasVOctets() && !this.getVOctets().isInitialized()) {
                    return false;
                }
                return !this.hasVString() || this.getVString().isInitialized();
            }

            @Override
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                Scalar parsedMessage = null;
                try {
                    parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
                } catch (InvalidProtocolBufferException e) {
                    parsedMessage = (Scalar)e.getUnfinishedMessage();
                    throw e;
                } finally {
                    if (parsedMessage != null) {
                        this.mergeFrom(parsedMessage);
                    }
                }
                return this;
            }

            @Override
            public boolean hasType() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override
            public Type getType() {
                return this.type_;
            }

            public Builder setType(Type value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 1;
                this.type_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearType() {
                this.bitField0_ &= 0xFFFFFFFE;
                this.type_ = Type.V_SINT;
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasVSignedInt() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override
            public long getVSignedInt() {
                return this.vSignedInt_;
            }

            public Builder setVSignedInt(long value) {
                this.bitField0_ |= 2;
                this.vSignedInt_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearVSignedInt() {
                this.bitField0_ &= 0xFFFFFFFD;
                this.vSignedInt_ = 0L;
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasVUnsignedInt() {
                return (this.bitField0_ & 4) == 4;
            }

            @Override
            public long getVUnsignedInt() {
                return this.vUnsignedInt_;
            }

            public Builder setVUnsignedInt(long value) {
                this.bitField0_ |= 4;
                this.vUnsignedInt_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearVUnsignedInt() {
                this.bitField0_ &= 0xFFFFFFFB;
                this.vUnsignedInt_ = 0L;
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasVOctets() {
                return (this.bitField0_ & 8) == 8;
            }

            @Override
            public Octets getVOctets() {
                if (this.vOctetsBuilder_ == null) {
                    return this.vOctets_;
                }
                return this.vOctetsBuilder_.getMessage();
            }

            public Builder setVOctets(Octets value) {
                if (this.vOctetsBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.vOctets_ = value;
                    this.onChanged();
                } else {
                    this.vOctetsBuilder_.setMessage(value);
                }
                this.bitField0_ |= 8;
                return this;
            }

            public Builder setVOctets(Octets.Builder builderForValue) {
                if (this.vOctetsBuilder_ == null) {
                    this.vOctets_ = builderForValue.build();
                    this.onChanged();
                } else {
                    this.vOctetsBuilder_.setMessage(builderForValue.build());
                }
                this.bitField0_ |= 8;
                return this;
            }

            public Builder mergeVOctets(Octets value) {
                if (this.vOctetsBuilder_ == null) {
                    this.vOctets_ = (this.bitField0_ & 8) == 8 && this.vOctets_ != Octets.getDefaultInstance() ? Octets.newBuilder(this.vOctets_).mergeFrom(value).buildPartial() : value;
                    this.onChanged();
                } else {
                    this.vOctetsBuilder_.mergeFrom(value);
                }
                this.bitField0_ |= 8;
                return this;
            }

            public Builder clearVOctets() {
                if (this.vOctetsBuilder_ == null) {
                    this.vOctets_ = Octets.getDefaultInstance();
                    this.onChanged();
                } else {
                    this.vOctetsBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFFF7;
                return this;
            }

            public Octets.Builder getVOctetsBuilder() {
                this.bitField0_ |= 8;
                this.onChanged();
                return this.getVOctetsFieldBuilder().getBuilder();
            }

            @Override
            public OctetsOrBuilder getVOctetsOrBuilder() {
                if (this.vOctetsBuilder_ != null) {
                    return this.vOctetsBuilder_.getMessageOrBuilder();
                }
                return this.vOctets_;
            }

            private SingleFieldBuilder<Octets, Octets.Builder, OctetsOrBuilder> getVOctetsFieldBuilder() {
                if (this.vOctetsBuilder_ == null) {
                    this.vOctetsBuilder_ = new SingleFieldBuilder(this.getVOctets(), this.getParentForChildren(), this.isClean());
                    this.vOctets_ = null;
                }
                return this.vOctetsBuilder_;
            }

            @Override
            public boolean hasVDouble() {
                return (this.bitField0_ & 0x10) == 16;
            }

            @Override
            public double getVDouble() {
                return this.vDouble_;
            }

            public Builder setVDouble(double value) {
                this.bitField0_ |= 0x10;
                this.vDouble_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearVDouble() {
                this.bitField0_ &= 0xFFFFFFEF;
                this.vDouble_ = 0.0;
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasVFloat() {
                return (this.bitField0_ & 0x20) == 32;
            }

            @Override
            public float getVFloat() {
                return this.vFloat_;
            }

            public Builder setVFloat(float value) {
                this.bitField0_ |= 0x20;
                this.vFloat_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearVFloat() {
                this.bitField0_ &= 0xFFFFFFDF;
                this.vFloat_ = 0.0f;
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasVBool() {
                return (this.bitField0_ & 0x40) == 64;
            }

            @Override
            public boolean getVBool() {
                return this.vBool_;
            }

            public Builder setVBool(boolean value) {
                this.bitField0_ |= 0x40;
                this.vBool_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearVBool() {
                this.bitField0_ &= 0xFFFFFFBF;
                this.vBool_ = false;
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasVString() {
                return (this.bitField0_ & 0x80) == 128;
            }

            @Override
            public String getVString() {
                if (this.vStringBuilder_ == null) {
                    return this.vString_;
                }
                return this.vStringBuilder_.getMessage();
            }

            public Builder setVString(String value) {
                if (this.vStringBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.vString_ = value;
                    this.onChanged();
                } else {
                    this.vStringBuilder_.setMessage(value);
                }
                this.bitField0_ |= 0x80;
                return this;
            }

            public Builder setVString(String.Builder builderForValue) {
                if (this.vStringBuilder_ == null) {
                    this.vString_ = builderForValue.build();
                    this.onChanged();
                } else {
                    this.vStringBuilder_.setMessage(builderForValue.build());
                }
                this.bitField0_ |= 0x80;
                return this;
            }

            public Builder mergeVString(String value) {
                if (this.vStringBuilder_ == null) {
                    this.vString_ = (this.bitField0_ & 0x80) == 128 && this.vString_ != String.getDefaultInstance() ? String.newBuilder(this.vString_).mergeFrom(value).buildPartial() : value;
                    this.onChanged();
                } else {
                    this.vStringBuilder_.mergeFrom(value);
                }
                this.bitField0_ |= 0x80;
                return this;
            }

            public Builder clearVString() {
                if (this.vStringBuilder_ == null) {
                    this.vString_ = String.getDefaultInstance();
                    this.onChanged();
                } else {
                    this.vStringBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFF7F;
                return this;
            }

            public String.Builder getVStringBuilder() {
                this.bitField0_ |= 0x80;
                this.onChanged();
                return this.getVStringFieldBuilder().getBuilder();
            }

            @Override
            public StringOrBuilder getVStringOrBuilder() {
                if (this.vStringBuilder_ != null) {
                    return this.vStringBuilder_.getMessageOrBuilder();
                }
                return this.vString_;
            }

            private SingleFieldBuilder<String, String.Builder, StringOrBuilder> getVStringFieldBuilder() {
                if (this.vStringBuilder_ == null) {
                    this.vStringBuilder_ = new SingleFieldBuilder(this.getVString(), this.getParentForChildren(), this.isClean());
                    this.vString_ = null;
                }
                return this.vStringBuilder_;
            }
        }

        public static final class Octets
        extends GeneratedMessage
        implements OctetsOrBuilder {
            private static final Octets defaultInstance;
            private final UnknownFieldSet unknownFields;
            public static Parser<Octets> PARSER;
            private int bitField0_;
            public static final int VALUE_FIELD_NUMBER = 1;
            private ByteString value_;
            public static final int CONTENT_TYPE_FIELD_NUMBER = 2;
            private int contentType_;
            private byte memoizedIsInitialized = (byte)-1;
            private int memoizedSerializedSize = -1;
            private static final long serialVersionUID = 0L;

            private Octets(GeneratedMessage.Builder<?> builder) {
                super(builder);
                this.unknownFields = builder.getUnknownFields();
            }

            private Octets(boolean noInit) {
                this.unknownFields = UnknownFieldSet.getDefaultInstance();
            }

            public static Octets getDefaultInstance() {
                return defaultInstance;
            }

            @Override
            public Octets getDefaultInstanceForType() {
                return defaultInstance;
            }

            @Override
            public final UnknownFieldSet getUnknownFields() {
                return this.unknownFields;
            }

            private Octets(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                this.initFields();
                boolean mutable_bitField0_ = false;
                UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
                try {
                    boolean done = false;
                    block11: while (!done) {
                        int tag = input.readTag();
                        switch (tag) {
                            case 0: {
                                done = true;
                                continue block11;
                            }
                            default: {
                                if (this.parseUnknownField(input, unknownFields, extensionRegistry, tag)) continue block11;
                                done = true;
                                continue block11;
                            }
                            case 10: {
                                this.bitField0_ |= 1;
                                this.value_ = input.readBytes();
                                continue block11;
                            }
                            case 16: 
                        }
                        this.bitField0_ |= 2;
                        this.contentType_ = input.readUInt32();
                    }
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e) {
                    throw new InvalidProtocolBufferException(e.getMessage()).setUnfinishedMessage(this);
                } finally {
                    this.unknownFields = unknownFields.build();
                    this.makeExtensionsImmutable();
                }
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_Mysqlx_Datatypes_Scalar_Octets_descriptor;
            }

            @Override
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_Mysqlx_Datatypes_Scalar_Octets_fieldAccessorTable.ensureFieldAccessorsInitialized(Octets.class, Builder.class);
            }

            public Parser<Octets> getParserForType() {
                return PARSER;
            }

            @Override
            public boolean hasValue() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override
            public ByteString getValue() {
                return this.value_;
            }

            @Override
            public boolean hasContentType() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override
            public int getContentType() {
                return this.contentType_;
            }

            private void initFields() {
                this.value_ = ByteString.EMPTY;
                this.contentType_ = 0;
            }

            @Override
            public final boolean isInitialized() {
                byte isInitialized = this.memoizedIsInitialized;
                if (isInitialized == 1) {
                    return true;
                }
                if (isInitialized == 0) {
                    return false;
                }
                if (!this.hasValue()) {
                    this.memoizedIsInitialized = 0;
                    return false;
                }
                this.memoizedIsInitialized = 1;
                return true;
            }

            @Override
            public void writeTo(CodedOutputStream output) throws IOException {
                this.getSerializedSize();
                if ((this.bitField0_ & 1) == 1) {
                    output.writeBytes(1, this.value_);
                }
                if ((this.bitField0_ & 2) == 2) {
                    output.writeUInt32(2, this.contentType_);
                }
                this.getUnknownFields().writeTo(output);
            }

            @Override
            public int getSerializedSize() {
                int size = this.memoizedSerializedSize;
                if (size != -1) {
                    return size;
                }
                size = 0;
                if ((this.bitField0_ & 1) == 1) {
                    size += CodedOutputStream.computeBytesSize(1, this.value_);
                }
                if ((this.bitField0_ & 2) == 2) {
                    size += CodedOutputStream.computeUInt32Size(2, this.contentType_);
                }
                this.memoizedSerializedSize = size += this.getUnknownFields().getSerializedSize();
                return size;
            }

            @Override
            protected java.lang.Object writeReplace() throws ObjectStreamException {
                return super.writeReplace();
            }

            public static Octets parseFrom(ByteString data) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(data);
            }

            public static Octets parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(data, extensionRegistry);
            }

            public static Octets parseFrom(byte[] data) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(data);
            }

            public static Octets parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(data, extensionRegistry);
            }

            public static Octets parseFrom(InputStream input) throws IOException {
                return PARSER.parseFrom(input);
            }

            public static Octets parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return PARSER.parseFrom(input, extensionRegistry);
            }

            public static Octets parseDelimitedFrom(InputStream input) throws IOException {
                return PARSER.parseDelimitedFrom(input);
            }

            public static Octets parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return PARSER.parseDelimitedFrom(input, extensionRegistry);
            }

            public static Octets parseFrom(CodedInputStream input) throws IOException {
                return PARSER.parseFrom(input);
            }

            public static Octets parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return PARSER.parseFrom(input, extensionRegistry);
            }

            public static Builder newBuilder() {
                return Builder.create();
            }

            @Override
            public Builder newBuilderForType() {
                return Octets.newBuilder();
            }

            public static Builder newBuilder(Octets prototype) {
                return Octets.newBuilder().mergeFrom(prototype);
            }

            @Override
            public Builder toBuilder() {
                return Octets.newBuilder(this);
            }

            @Override
            protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
                Builder builder = new Builder(parent);
                return builder;
            }

            static {
                PARSER = new AbstractParser<Octets>(){

                    @Override
                    public Octets parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                        return new Octets(input, extensionRegistry);
                    }
                };
                defaultInstance = new Octets(true);
                defaultInstance.initFields();
            }

            public static final class Builder
            extends GeneratedMessage.Builder<Builder>
            implements OctetsOrBuilder {
                private int bitField0_;
                private ByteString value_ = ByteString.EMPTY;
                private int contentType_;

                public static final Descriptors.Descriptor getDescriptor() {
                    return internal_static_Mysqlx_Datatypes_Scalar_Octets_descriptor;
                }

                @Override
                protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                    return internal_static_Mysqlx_Datatypes_Scalar_Octets_fieldAccessorTable.ensureFieldAccessorsInitialized(Octets.class, Builder.class);
                }

                private Builder() {
                    this.maybeForceBuilderInitialization();
                }

                private Builder(GeneratedMessage.BuilderParent parent) {
                    super(parent);
                    this.maybeForceBuilderInitialization();
                }

                private void maybeForceBuilderInitialization() {
                    if (alwaysUseFieldBuilders) {
                        // empty if block
                    }
                }

                private static Builder create() {
                    return new Builder();
                }

                @Override
                public Builder clear() {
                    super.clear();
                    this.value_ = ByteString.EMPTY;
                    this.bitField0_ &= 0xFFFFFFFE;
                    this.contentType_ = 0;
                    this.bitField0_ &= 0xFFFFFFFD;
                    return this;
                }

                @Override
                public Builder clone() {
                    return Builder.create().mergeFrom(this.buildPartial());
                }

                @Override
                public Descriptors.Descriptor getDescriptorForType() {
                    return internal_static_Mysqlx_Datatypes_Scalar_Octets_descriptor;
                }

                @Override
                public Octets getDefaultInstanceForType() {
                    return Octets.getDefaultInstance();
                }

                @Override
                public Octets build() {
                    Octets result = this.buildPartial();
                    if (!result.isInitialized()) {
                        throw Builder.newUninitializedMessageException(result);
                    }
                    return result;
                }

                @Override
                public Octets buildPartial() {
                    Octets result = new Octets(this);
                    int from_bitField0_ = this.bitField0_;
                    int to_bitField0_ = 0;
                    if ((from_bitField0_ & 1) == 1) {
                        to_bitField0_ |= 1;
                    }
                    result.value_ = this.value_;
                    if ((from_bitField0_ & 2) == 2) {
                        to_bitField0_ |= 2;
                    }
                    result.contentType_ = this.contentType_;
                    result.bitField0_ = to_bitField0_;
                    this.onBuilt();
                    return result;
                }

                @Override
                public Builder mergeFrom(Message other) {
                    if (other instanceof Octets) {
                        return this.mergeFrom((Octets)other);
                    }
                    super.mergeFrom(other);
                    return this;
                }

                public Builder mergeFrom(Octets other) {
                    if (other == Octets.getDefaultInstance()) {
                        return this;
                    }
                    if (other.hasValue()) {
                        this.setValue(other.getValue());
                    }
                    if (other.hasContentType()) {
                        this.setContentType(other.getContentType());
                    }
                    this.mergeUnknownFields(other.getUnknownFields());
                    return this;
                }

                @Override
                public final boolean isInitialized() {
                    return this.hasValue();
                }

                @Override
                public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                    Octets parsedMessage = null;
                    try {
                        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
                    } catch (InvalidProtocolBufferException e) {
                        parsedMessage = (Octets)e.getUnfinishedMessage();
                        throw e;
                    } finally {
                        if (parsedMessage != null) {
                            this.mergeFrom(parsedMessage);
                        }
                    }
                    return this;
                }

                @Override
                public boolean hasValue() {
                    return (this.bitField0_ & 1) == 1;
                }

                @Override
                public ByteString getValue() {
                    return this.value_;
                }

                public Builder setValue(ByteString value) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.bitField0_ |= 1;
                    this.value_ = value;
                    this.onChanged();
                    return this;
                }

                public Builder clearValue() {
                    this.bitField0_ &= 0xFFFFFFFE;
                    this.value_ = Octets.getDefaultInstance().getValue();
                    this.onChanged();
                    return this;
                }

                @Override
                public boolean hasContentType() {
                    return (this.bitField0_ & 2) == 2;
                }

                @Override
                public int getContentType() {
                    return this.contentType_;
                }

                public Builder setContentType(int value) {
                    this.bitField0_ |= 2;
                    this.contentType_ = value;
                    this.onChanged();
                    return this;
                }

                public Builder clearContentType() {
                    this.bitField0_ &= 0xFFFFFFFD;
                    this.contentType_ = 0;
                    this.onChanged();
                    return this;
                }
            }
        }

        public static interface OctetsOrBuilder
        extends MessageOrBuilder {
            public boolean hasValue();

            public ByteString getValue();

            public boolean hasContentType();

            public int getContentType();
        }

        public static final class String
        extends GeneratedMessage
        implements StringOrBuilder {
            private static final String defaultInstance;
            private final UnknownFieldSet unknownFields;
            public static Parser<String> PARSER;
            private int bitField0_;
            public static final int VALUE_FIELD_NUMBER = 1;
            private ByteString value_;
            public static final int COLLATION_FIELD_NUMBER = 2;
            private long collation_;
            private byte memoizedIsInitialized = (byte)-1;
            private int memoizedSerializedSize = -1;
            private static final long serialVersionUID = 0L;

            private String(GeneratedMessage.Builder<?> builder) {
                super(builder);
                this.unknownFields = builder.getUnknownFields();
            }

            private String(boolean noInit) {
                this.unknownFields = UnknownFieldSet.getDefaultInstance();
            }

            public static String getDefaultInstance() {
                return defaultInstance;
            }

            @Override
            public String getDefaultInstanceForType() {
                return defaultInstance;
            }

            @Override
            public final UnknownFieldSet getUnknownFields() {
                return this.unknownFields;
            }

            private String(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                this.initFields();
                boolean mutable_bitField0_ = false;
                UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
                try {
                    boolean done = false;
                    block11: while (!done) {
                        int tag = input.readTag();
                        switch (tag) {
                            case 0: {
                                done = true;
                                continue block11;
                            }
                            default: {
                                if (this.parseUnknownField(input, unknownFields, extensionRegistry, tag)) continue block11;
                                done = true;
                                continue block11;
                            }
                            case 10: {
                                this.bitField0_ |= 1;
                                this.value_ = input.readBytes();
                                continue block11;
                            }
                            case 16: 
                        }
                        this.bitField0_ |= 2;
                        this.collation_ = input.readUInt64();
                    }
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e) {
                    throw new InvalidProtocolBufferException(e.getMessage()).setUnfinishedMessage(this);
                } finally {
                    this.unknownFields = unknownFields.build();
                    this.makeExtensionsImmutable();
                }
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_Mysqlx_Datatypes_Scalar_String_descriptor;
            }

            @Override
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_Mysqlx_Datatypes_Scalar_String_fieldAccessorTable.ensureFieldAccessorsInitialized(String.class, Builder.class);
            }

            public Parser<String> getParserForType() {
                return PARSER;
            }

            @Override
            public boolean hasValue() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override
            public ByteString getValue() {
                return this.value_;
            }

            @Override
            public boolean hasCollation() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override
            public long getCollation() {
                return this.collation_;
            }

            private void initFields() {
                this.value_ = ByteString.EMPTY;
                this.collation_ = 0L;
            }

            @Override
            public final boolean isInitialized() {
                byte isInitialized = this.memoizedIsInitialized;
                if (isInitialized == 1) {
                    return true;
                }
                if (isInitialized == 0) {
                    return false;
                }
                if (!this.hasValue()) {
                    this.memoizedIsInitialized = 0;
                    return false;
                }
                this.memoizedIsInitialized = 1;
                return true;
            }

            @Override
            public void writeTo(CodedOutputStream output) throws IOException {
                this.getSerializedSize();
                if ((this.bitField0_ & 1) == 1) {
                    output.writeBytes(1, this.value_);
                }
                if ((this.bitField0_ & 2) == 2) {
                    output.writeUInt64(2, this.collation_);
                }
                this.getUnknownFields().writeTo(output);
            }

            @Override
            public int getSerializedSize() {
                int size = this.memoizedSerializedSize;
                if (size != -1) {
                    return size;
                }
                size = 0;
                if ((this.bitField0_ & 1) == 1) {
                    size += CodedOutputStream.computeBytesSize(1, this.value_);
                }
                if ((this.bitField0_ & 2) == 2) {
                    size += CodedOutputStream.computeUInt64Size(2, this.collation_);
                }
                this.memoizedSerializedSize = size += this.getUnknownFields().getSerializedSize();
                return size;
            }

            @Override
            protected java.lang.Object writeReplace() throws ObjectStreamException {
                return super.writeReplace();
            }

            public static String parseFrom(ByteString data) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(data);
            }

            public static String parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(data, extensionRegistry);
            }

            public static String parseFrom(byte[] data) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(data);
            }

            public static String parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(data, extensionRegistry);
            }

            public static String parseFrom(InputStream input) throws IOException {
                return PARSER.parseFrom(input);
            }

            public static String parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return PARSER.parseFrom(input, extensionRegistry);
            }

            public static String parseDelimitedFrom(InputStream input) throws IOException {
                return PARSER.parseDelimitedFrom(input);
            }

            public static String parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return PARSER.parseDelimitedFrom(input, extensionRegistry);
            }

            public static String parseFrom(CodedInputStream input) throws IOException {
                return PARSER.parseFrom(input);
            }

            public static String parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return PARSER.parseFrom(input, extensionRegistry);
            }

            public static Builder newBuilder() {
                return Builder.create();
            }

            @Override
            public Builder newBuilderForType() {
                return String.newBuilder();
            }

            public static Builder newBuilder(String prototype) {
                return String.newBuilder().mergeFrom(prototype);
            }

            @Override
            public Builder toBuilder() {
                return String.newBuilder(this);
            }

            @Override
            protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
                Builder builder = new Builder(parent);
                return builder;
            }

            static {
                PARSER = new AbstractParser<String>(){

                    @Override
                    public String parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                        return new String(input, extensionRegistry);
                    }
                };
                defaultInstance = new String(true);
                defaultInstance.initFields();
            }

            public static final class Builder
            extends GeneratedMessage.Builder<Builder>
            implements StringOrBuilder {
                private int bitField0_;
                private ByteString value_ = ByteString.EMPTY;
                private long collation_;

                public static final Descriptors.Descriptor getDescriptor() {
                    return internal_static_Mysqlx_Datatypes_Scalar_String_descriptor;
                }

                @Override
                protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                    return internal_static_Mysqlx_Datatypes_Scalar_String_fieldAccessorTable.ensureFieldAccessorsInitialized(String.class, Builder.class);
                }

                private Builder() {
                    this.maybeForceBuilderInitialization();
                }

                private Builder(GeneratedMessage.BuilderParent parent) {
                    super(parent);
                    this.maybeForceBuilderInitialization();
                }

                private void maybeForceBuilderInitialization() {
                    if (alwaysUseFieldBuilders) {
                        // empty if block
                    }
                }

                private static Builder create() {
                    return new Builder();
                }

                @Override
                public Builder clear() {
                    super.clear();
                    this.value_ = ByteString.EMPTY;
                    this.bitField0_ &= 0xFFFFFFFE;
                    this.collation_ = 0L;
                    this.bitField0_ &= 0xFFFFFFFD;
                    return this;
                }

                @Override
                public Builder clone() {
                    return Builder.create().mergeFrom(this.buildPartial());
                }

                @Override
                public Descriptors.Descriptor getDescriptorForType() {
                    return internal_static_Mysqlx_Datatypes_Scalar_String_descriptor;
                }

                @Override
                public String getDefaultInstanceForType() {
                    return String.getDefaultInstance();
                }

                @Override
                public String build() {
                    String result = this.buildPartial();
                    if (!result.isInitialized()) {
                        throw Builder.newUninitializedMessageException(result);
                    }
                    return result;
                }

                @Override
                public String buildPartial() {
                    String result = new String(this);
                    int from_bitField0_ = this.bitField0_;
                    int to_bitField0_ = 0;
                    if ((from_bitField0_ & 1) == 1) {
                        to_bitField0_ |= 1;
                    }
                    result.value_ = this.value_;
                    if ((from_bitField0_ & 2) == 2) {
                        to_bitField0_ |= 2;
                    }
                    result.collation_ = this.collation_;
                    result.bitField0_ = to_bitField0_;
                    this.onBuilt();
                    return result;
                }

                @Override
                public Builder mergeFrom(Message other) {
                    if (other instanceof String) {
                        return this.mergeFrom((String)other);
                    }
                    super.mergeFrom(other);
                    return this;
                }

                public Builder mergeFrom(String other) {
                    if (other == String.getDefaultInstance()) {
                        return this;
                    }
                    if (other.hasValue()) {
                        this.setValue(other.getValue());
                    }
                    if (other.hasCollation()) {
                        this.setCollation(other.getCollation());
                    }
                    this.mergeUnknownFields(other.getUnknownFields());
                    return this;
                }

                @Override
                public final boolean isInitialized() {
                    return this.hasValue();
                }

                @Override
                public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                    String parsedMessage = null;
                    try {
                        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
                    } catch (InvalidProtocolBufferException e) {
                        parsedMessage = (String)e.getUnfinishedMessage();
                        throw e;
                    } finally {
                        if (parsedMessage != null) {
                            this.mergeFrom(parsedMessage);
                        }
                    }
                    return this;
                }

                @Override
                public boolean hasValue() {
                    return (this.bitField0_ & 1) == 1;
                }

                @Override
                public ByteString getValue() {
                    return this.value_;
                }

                public Builder setValue(ByteString value) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.bitField0_ |= 1;
                    this.value_ = value;
                    this.onChanged();
                    return this;
                }

                public Builder clearValue() {
                    this.bitField0_ &= 0xFFFFFFFE;
                    this.value_ = String.getDefaultInstance().getValue();
                    this.onChanged();
                    return this;
                }

                @Override
                public boolean hasCollation() {
                    return (this.bitField0_ & 2) == 2;
                }

                @Override
                public long getCollation() {
                    return this.collation_;
                }

                public Builder setCollation(long value) {
                    this.bitField0_ |= 2;
                    this.collation_ = value;
                    this.onChanged();
                    return this;
                }

                public Builder clearCollation() {
                    this.bitField0_ &= 0xFFFFFFFD;
                    this.collation_ = 0L;
                    this.onChanged();
                    return this;
                }
            }
        }

        public static interface StringOrBuilder
        extends MessageOrBuilder {
            public boolean hasValue();

            public ByteString getValue();

            public boolean hasCollation();

            public long getCollation();
        }

        public static enum Type implements ProtocolMessageEnum
        {
            V_SINT(0, 1),
            V_UINT(1, 2),
            V_NULL(2, 3),
            V_OCTETS(3, 4),
            V_DOUBLE(4, 5),
            V_FLOAT(5, 6),
            V_BOOL(6, 7),
            V_STRING(7, 8);

            public static final int V_SINT_VALUE = 1;
            public static final int V_UINT_VALUE = 2;
            public static final int V_NULL_VALUE = 3;
            public static final int V_OCTETS_VALUE = 4;
            public static final int V_DOUBLE_VALUE = 5;
            public static final int V_FLOAT_VALUE = 6;
            public static final int V_BOOL_VALUE = 7;
            public static final int V_STRING_VALUE = 8;
            private static Internal.EnumLiteMap<Type> internalValueMap;
            private static final Type[] VALUES;
            private final int index;
            private final int value;

            @Override
            public final int getNumber() {
                return this.value;
            }

            public static Type valueOf(int value) {
                switch (value) {
                    case 1: {
                        return V_SINT;
                    }
                    case 2: {
                        return V_UINT;
                    }
                    case 3: {
                        return V_NULL;
                    }
                    case 4: {
                        return V_OCTETS;
                    }
                    case 5: {
                        return V_DOUBLE;
                    }
                    case 6: {
                        return V_FLOAT;
                    }
                    case 7: {
                        return V_BOOL;
                    }
                    case 8: {
                        return V_STRING;
                    }
                }
                return null;
            }

            public static Internal.EnumLiteMap<Type> internalGetValueMap() {
                return internalValueMap;
            }

            @Override
            public final Descriptors.EnumValueDescriptor getValueDescriptor() {
                return Type.getDescriptor().getValues().get(this.index);
            }

            @Override
            public final Descriptors.EnumDescriptor getDescriptorForType() {
                return Type.getDescriptor();
            }

            public static final Descriptors.EnumDescriptor getDescriptor() {
                return Scalar.getDescriptor().getEnumTypes().get(0);
            }

            public static Type valueOf(Descriptors.EnumValueDescriptor desc) {
                if (desc.getType() != Type.getDescriptor()) {
                    throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
                }
                return VALUES[desc.getIndex()];
            }

            private Type(int index, int value) {
                this.index = index;
                this.value = value;
            }

            static {
                internalValueMap = new Internal.EnumLiteMap<Type>(){

                    @Override
                    public Type findValueByNumber(int number) {
                        return Type.valueOf(number);
                    }
                };
                VALUES = Type.values();
            }
        }
    }

    public static interface ScalarOrBuilder
    extends MessageOrBuilder {
        public boolean hasType();

        public Scalar.Type getType();

        public boolean hasVSignedInt();

        public long getVSignedInt();

        public boolean hasVUnsignedInt();

        public long getVUnsignedInt();

        public boolean hasVOctets();

        public Scalar.Octets getVOctets();

        public Scalar.OctetsOrBuilder getVOctetsOrBuilder();

        public boolean hasVDouble();

        public double getVDouble();

        public boolean hasVFloat();

        public float getVFloat();

        public boolean hasVBool();

        public boolean getVBool();

        public boolean hasVString();

        public Scalar.String getVString();

        public Scalar.StringOrBuilder getVStringOrBuilder();
    }
}

