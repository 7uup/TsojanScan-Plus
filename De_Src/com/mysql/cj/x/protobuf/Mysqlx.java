/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.x.protobuf;

import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.DescriptorProtos;
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
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectStreamException;

public final class Mysqlx {
    public static final int CLIENT_MESSAGE_ID_FIELD_NUMBER = 100001;
    public static final GeneratedMessage.GeneratedExtension<DescriptorProtos.MessageOptions, ClientMessages.Type> clientMessageId = GeneratedMessage.newFileScopedGeneratedExtension(ClientMessages.Type.class, null);
    public static final int SERVER_MESSAGE_ID_FIELD_NUMBER = 100002;
    public static final GeneratedMessage.GeneratedExtension<DescriptorProtos.MessageOptions, ServerMessages.Type> serverMessageId = GeneratedMessage.newFileScopedGeneratedExtension(ServerMessages.Type.class, null);
    private static final Descriptors.Descriptor internal_static_Mysqlx_ClientMessages_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_Mysqlx_ClientMessages_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_Mysqlx_ServerMessages_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_Mysqlx_ServerMessages_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_Mysqlx_Ok_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_Mysqlx_Ok_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_Mysqlx_Error_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_Mysqlx_Error_fieldAccessorTable;
    private static Descriptors.FileDescriptor descriptor;

    private Mysqlx() {
    }

    public static void registerAllExtensions(ExtensionRegistry registry) {
        registry.add(clientMessageId);
        registry.add(serverMessageId);
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        String[] descriptorData = new String[]{"\n\fmysqlx.proto\u0012\u0006Mysqlx\u001a google/protobuf/descriptor.proto\"\u00f4\u0002\n\u000eClientMessages\"\u00e1\u0002\n\u0004Type\u0012\u0018\n\u0014CON_CAPABILITIES_GET\u0010\u0001\u0012\u0018\n\u0014CON_CAPABILITIES_SET\u0010\u0002\u0012\r\n\tCON_CLOSE\u0010\u0003\u0012\u001b\n\u0017SESS_AUTHENTICATE_START\u0010\u0004\u0012\u001e\n\u001aSESS_AUTHENTICATE_CONTINUE\u0010\u0005\u0012\u000e\n\nSESS_RESET\u0010\u0006\u0012\u000e\n\nSESS_CLOSE\u0010\u0007\u0012\u0014\n\u0010SQL_STMT_EXECUTE\u0010\f\u0012\r\n\tCRUD_FIND\u0010\u0011\u0012\u000f\n\u000bCRUD_INSERT\u0010\u0012\u0012\u000f\n\u000bCRUD_UPDATE\u0010\u0013\u0012\u000f\n\u000bCRUD_DELETE\u0010\u0014\u0012\u000f\n\u000bEXPECT_OPEN\u0010\u0018\u0012\u0010\n\fEXPECT_CLOSE\u0010\u0019\u0012\u0014\n\u0010CRUD_CREATE_VIEW\u0010\u001e\u0012\u0014\n\u0010CRUD_MO", "DIFY_VIEW\u0010\u001f\u0012\u0012\n\u000eCRUD_DROP_VIEW\u0010 \"\u00e2\u0002\n\u000eServerMessages\"\u00cf\u0002\n\u0004Type\u0012\u0006\n\u0002OK\u0010\u0000\u0012\t\n\u0005ERROR\u0010\u0001\u0012\u0015\n\u0011CONN_CAPABILITIES\u0010\u0002\u0012\u001e\n\u001aSESS_AUTHENTICATE_CONTINUE\u0010\u0003\u0012\u0018\n\u0014SESS_AUTHENTICATE_OK\u0010\u0004\u0012\n\n\u0006NOTICE\u0010\u000b\u0012\u001e\n\u001aRESULTSET_COLUMN_META_DATA\u0010\f\u0012\u0011\n\rRESULTSET_ROW\u0010\r\u0012\u0018\n\u0014RESULTSET_FETCH_DONE\u0010\u000e\u0012\u001d\n\u0019RESULTSET_FETCH_SUSPENDED\u0010\u000f\u0012(\n$RESULTSET_FETCH_DONE_MORE_RESULTSETS\u0010\u0010\u0012\u0017\n\u0013SQL_STMT_EXECUTE_OK\u0010\u0011\u0012(\n$RESULTSET_FETCH_DONE_MORE_OUT_PARAMS\u0010\u0012\"\u0017\n\u0002Ok\u0012\u000b\n\u0003ms", "g\u0018\u0001 \u0001(\t:\u0004\u0090\u00ea0\u0000\"\u008e\u0001\n\u0005Error\u0012/\n\bseverity\u0018\u0001 \u0001(\u000e2\u0016.Mysqlx.Error.Severity:\u0005ERROR\u0012\f\n\u0004code\u0018\u0002 \u0002(\r\u0012\u0011\n\tsql_state\u0018\u0004 \u0002(\t\u0012\u000b\n\u0003msg\u0018\u0003 \u0002(\t\" \n\bSeverity\u0012\t\n\u0005ERROR\u0010\u0000\u0012\t\n\u0005FATAL\u0010\u0001:\u0004\u0090\u00ea0\u0001:Y\n\u0011client_message_id\u0012\u001f.google.protobuf.MessageOptions\u0018\u00a1\u008d\u0006 \u0001(\u000e2\u001b.Mysqlx.ClientMessages.Type:Y\n\u0011server_message_id\u0012\u001f.google.protobuf.MessageOptions\u0018\u00a2\u008d\u0006 \u0001(\u000e2\u001b.Mysqlx.ServerMessages.TypeB\u0019\n\u0017com.mysql.cj.x.protobuf"};
        Descriptors.FileDescriptor.InternalDescriptorAssigner assigner = new Descriptors.FileDescriptor.InternalDescriptorAssigner(){

            @Override
            public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor root) {
                descriptor = root;
                return null;
            }
        };
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[]{DescriptorProtos.getDescriptor()}, assigner);
        internal_static_Mysqlx_ClientMessages_descriptor = Mysqlx.getDescriptor().getMessageTypes().get(0);
        internal_static_Mysqlx_ClientMessages_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_Mysqlx_ClientMessages_descriptor, new String[0]);
        internal_static_Mysqlx_ServerMessages_descriptor = Mysqlx.getDescriptor().getMessageTypes().get(1);
        internal_static_Mysqlx_ServerMessages_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_Mysqlx_ServerMessages_descriptor, new String[0]);
        internal_static_Mysqlx_Ok_descriptor = Mysqlx.getDescriptor().getMessageTypes().get(2);
        internal_static_Mysqlx_Ok_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_Mysqlx_Ok_descriptor, new String[]{"Msg"});
        internal_static_Mysqlx_Error_descriptor = Mysqlx.getDescriptor().getMessageTypes().get(3);
        internal_static_Mysqlx_Error_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_Mysqlx_Error_descriptor, new String[]{"Severity", "Code", "SqlState", "Msg"});
        clientMessageId.internalInit(descriptor.getExtensions().get(0));
        serverMessageId.internalInit(descriptor.getExtensions().get(1));
        ExtensionRegistry registry = ExtensionRegistry.newInstance();
        registry.add(serverMessageId);
        registry.add(serverMessageId);
        Descriptors.FileDescriptor.internalUpdateFileDescriptor(descriptor, registry);
        DescriptorProtos.getDescriptor();
    }

    public static final class Error
    extends GeneratedMessage
    implements ErrorOrBuilder {
        private static final Error defaultInstance;
        private final UnknownFieldSet unknownFields;
        public static Parser<Error> PARSER;
        private int bitField0_;
        public static final int SEVERITY_FIELD_NUMBER = 1;
        private Severity severity_;
        public static final int CODE_FIELD_NUMBER = 2;
        private int code_;
        public static final int SQL_STATE_FIELD_NUMBER = 4;
        private Object sqlState_;
        public static final int MSG_FIELD_NUMBER = 3;
        private Object msg_;
        private byte memoizedIsInitialized = (byte)-1;
        private int memoizedSerializedSize = -1;
        private static final long serialVersionUID = 0L;

        private Error(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.unknownFields = builder.getUnknownFields();
        }

        private Error(boolean noInit) {
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static Error getDefaultInstance() {
            return defaultInstance;
        }

        @Override
        public Error getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private Error(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
                            Severity value = Severity.valueOf(rawValue);
                            if (value == null) {
                                unknownFields.mergeVarintField(1, rawValue);
                                continue block13;
                            }
                            this.bitField0_ |= 1;
                            this.severity_ = value;
                            continue block13;
                        }
                        case 16: {
                            this.bitField0_ |= 2;
                            this.code_ = input.readUInt32();
                            continue block13;
                        }
                        case 26: {
                            ByteString bs = input.readBytes();
                            this.bitField0_ |= 8;
                            this.msg_ = bs;
                            continue block13;
                        }
                        case 34: 
                    }
                    ByteString bs = input.readBytes();
                    this.bitField0_ |= 4;
                    this.sqlState_ = bs;
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
            return internal_static_Mysqlx_Error_descriptor;
        }

        @Override
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return internal_static_Mysqlx_Error_fieldAccessorTable.ensureFieldAccessorsInitialized(Error.class, Builder.class);
        }

        public Parser<Error> getParserForType() {
            return PARSER;
        }

        @Override
        public boolean hasSeverity() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override
        public Severity getSeverity() {
            return this.severity_;
        }

        @Override
        public boolean hasCode() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override
        public int getCode() {
            return this.code_;
        }

        @Override
        public boolean hasSqlState() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override
        public String getSqlState() {
            Object ref = this.sqlState_;
            if (ref instanceof String) {
                return (String)ref;
            }
            ByteString bs = (ByteString)ref;
            String s2 = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.sqlState_ = s2;
            }
            return s2;
        }

        @Override
        public ByteString getSqlStateBytes() {
            Object ref = this.sqlState_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.sqlState_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        @Override
        public boolean hasMsg() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override
        public String getMsg() {
            Object ref = this.msg_;
            if (ref instanceof String) {
                return (String)ref;
            }
            ByteString bs = (ByteString)ref;
            String s2 = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.msg_ = s2;
            }
            return s2;
        }

        @Override
        public ByteString getMsgBytes() {
            Object ref = this.msg_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.msg_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        private void initFields() {
            this.severity_ = Severity.ERROR;
            this.code_ = 0;
            this.sqlState_ = "";
            this.msg_ = "";
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
            if (!this.hasCode()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            if (!this.hasSqlState()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            if (!this.hasMsg()) {
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
                output.writeEnum(1, this.severity_.getNumber());
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeUInt32(2, this.code_);
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeBytes(3, this.getMsgBytes());
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeBytes(4, this.getSqlStateBytes());
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
                size += CodedOutputStream.computeEnumSize(1, this.severity_.getNumber());
            }
            if ((this.bitField0_ & 2) == 2) {
                size += CodedOutputStream.computeUInt32Size(2, this.code_);
            }
            if ((this.bitField0_ & 8) == 8) {
                size += CodedOutputStream.computeBytesSize(3, this.getMsgBytes());
            }
            if ((this.bitField0_ & 4) == 4) {
                size += CodedOutputStream.computeBytesSize(4, this.getSqlStateBytes());
            }
            this.memoizedSerializedSize = size += this.getUnknownFields().getSerializedSize();
            return size;
        }

        @Override
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static Error parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static Error parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static Error parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static Error parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static Error parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static Error parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Error parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static Error parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static Error parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static Error parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override
        public Builder newBuilderForType() {
            return Error.newBuilder();
        }

        public static Builder newBuilder(Error prototype) {
            return Error.newBuilder().mergeFrom(prototype);
        }

        @Override
        public Builder toBuilder() {
            return Error.newBuilder(this);
        }

        @Override
        protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        static {
            PARSER = new AbstractParser<Error>(){

                @Override
                public Error parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                    return new Error(input, extensionRegistry);
                }
            };
            defaultInstance = new Error(true);
            defaultInstance.initFields();
        }

        public static final class Builder
        extends GeneratedMessage.Builder<Builder>
        implements ErrorOrBuilder {
            private int bitField0_;
            private Severity severity_ = Severity.ERROR;
            private int code_;
            private Object sqlState_ = "";
            private Object msg_ = "";

            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_Mysqlx_Error_descriptor;
            }

            @Override
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_Mysqlx_Error_fieldAccessorTable.ensureFieldAccessorsInitialized(Error.class, Builder.class);
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
                this.severity_ = Severity.ERROR;
                this.bitField0_ &= 0xFFFFFFFE;
                this.code_ = 0;
                this.bitField0_ &= 0xFFFFFFFD;
                this.sqlState_ = "";
                this.bitField0_ &= 0xFFFFFFFB;
                this.msg_ = "";
                this.bitField0_ &= 0xFFFFFFF7;
                return this;
            }

            @Override
            public Builder clone() {
                return Builder.create().mergeFrom(this.buildPartial());
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return internal_static_Mysqlx_Error_descriptor;
            }

            @Override
            public Error getDefaultInstanceForType() {
                return Error.getDefaultInstance();
            }

            @Override
            public Error build() {
                Error result = this.buildPartial();
                if (!result.isInitialized()) {
                    throw Builder.newUninitializedMessageException(result);
                }
                return result;
            }

            @Override
            public Error buildPartial() {
                Error result = new Error(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ |= 1;
                }
                result.severity_ = this.severity_;
                if ((from_bitField0_ & 2) == 2) {
                    to_bitField0_ |= 2;
                }
                result.code_ = this.code_;
                if ((from_bitField0_ & 4) == 4) {
                    to_bitField0_ |= 4;
                }
                result.sqlState_ = this.sqlState_;
                if ((from_bitField0_ & 8) == 8) {
                    to_bitField0_ |= 8;
                }
                result.msg_ = this.msg_;
                result.bitField0_ = to_bitField0_;
                this.onBuilt();
                return result;
            }

            @Override
            public Builder mergeFrom(Message other) {
                if (other instanceof Error) {
                    return this.mergeFrom((Error)other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(Error other) {
                if (other == Error.getDefaultInstance()) {
                    return this;
                }
                if (other.hasSeverity()) {
                    this.setSeverity(other.getSeverity());
                }
                if (other.hasCode()) {
                    this.setCode(other.getCode());
                }
                if (other.hasSqlState()) {
                    this.bitField0_ |= 4;
                    this.sqlState_ = other.sqlState_;
                    this.onChanged();
                }
                if (other.hasMsg()) {
                    this.bitField0_ |= 8;
                    this.msg_ = other.msg_;
                    this.onChanged();
                }
                this.mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            @Override
            public final boolean isInitialized() {
                if (!this.hasCode()) {
                    return false;
                }
                if (!this.hasSqlState()) {
                    return false;
                }
                return this.hasMsg();
            }

            @Override
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                Error parsedMessage = null;
                try {
                    parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
                } catch (InvalidProtocolBufferException e) {
                    parsedMessage = (Error)e.getUnfinishedMessage();
                    throw e;
                } finally {
                    if (parsedMessage != null) {
                        this.mergeFrom(parsedMessage);
                    }
                }
                return this;
            }

            @Override
            public boolean hasSeverity() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override
            public Severity getSeverity() {
                return this.severity_;
            }

            public Builder setSeverity(Severity value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 1;
                this.severity_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearSeverity() {
                this.bitField0_ &= 0xFFFFFFFE;
                this.severity_ = Severity.ERROR;
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasCode() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override
            public int getCode() {
                return this.code_;
            }

            public Builder setCode(int value) {
                this.bitField0_ |= 2;
                this.code_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearCode() {
                this.bitField0_ &= 0xFFFFFFFD;
                this.code_ = 0;
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasSqlState() {
                return (this.bitField0_ & 4) == 4;
            }

            @Override
            public String getSqlState() {
                Object ref = this.sqlState_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString)ref;
                    String s2 = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.sqlState_ = s2;
                    }
                    return s2;
                }
                return (String)ref;
            }

            @Override
            public ByteString getSqlStateBytes() {
                Object ref = this.sqlState_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String)ref);
                    this.sqlState_ = b;
                    return b;
                }
                return (ByteString)ref;
            }

            public Builder setSqlState(String value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 4;
                this.sqlState_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearSqlState() {
                this.bitField0_ &= 0xFFFFFFFB;
                this.sqlState_ = Error.getDefaultInstance().getSqlState();
                this.onChanged();
                return this;
            }

            public Builder setSqlStateBytes(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 4;
                this.sqlState_ = value;
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasMsg() {
                return (this.bitField0_ & 8) == 8;
            }

            @Override
            public String getMsg() {
                Object ref = this.msg_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString)ref;
                    String s2 = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.msg_ = s2;
                    }
                    return s2;
                }
                return (String)ref;
            }

            @Override
            public ByteString getMsgBytes() {
                Object ref = this.msg_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String)ref);
                    this.msg_ = b;
                    return b;
                }
                return (ByteString)ref;
            }

            public Builder setMsg(String value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 8;
                this.msg_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearMsg() {
                this.bitField0_ &= 0xFFFFFFF7;
                this.msg_ = Error.getDefaultInstance().getMsg();
                this.onChanged();
                return this;
            }

            public Builder setMsgBytes(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 8;
                this.msg_ = value;
                this.onChanged();
                return this;
            }
        }

        public static enum Severity implements ProtocolMessageEnum
        {
            ERROR(0, 0),
            FATAL(1, 1);

            public static final int ERROR_VALUE = 0;
            public static final int FATAL_VALUE = 1;
            private static Internal.EnumLiteMap<Severity> internalValueMap;
            private static final Severity[] VALUES;
            private final int index;
            private final int value;

            @Override
            public final int getNumber() {
                return this.value;
            }

            public static Severity valueOf(int value) {
                switch (value) {
                    case 0: {
                        return ERROR;
                    }
                    case 1: {
                        return FATAL;
                    }
                }
                return null;
            }

            public static Internal.EnumLiteMap<Severity> internalGetValueMap() {
                return internalValueMap;
            }

            @Override
            public final Descriptors.EnumValueDescriptor getValueDescriptor() {
                return Severity.getDescriptor().getValues().get(this.index);
            }

            @Override
            public final Descriptors.EnumDescriptor getDescriptorForType() {
                return Severity.getDescriptor();
            }

            public static final Descriptors.EnumDescriptor getDescriptor() {
                return Error.getDescriptor().getEnumTypes().get(0);
            }

            public static Severity valueOf(Descriptors.EnumValueDescriptor desc) {
                if (desc.getType() != Severity.getDescriptor()) {
                    throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
                }
                return VALUES[desc.getIndex()];
            }

            private Severity(int index, int value) {
                this.index = index;
                this.value = value;
            }

            static {
                internalValueMap = new Internal.EnumLiteMap<Severity>(){

                    @Override
                    public Severity findValueByNumber(int number) {
                        return Severity.valueOf(number);
                    }
                };
                VALUES = Severity.values();
            }
        }
    }

    public static interface ErrorOrBuilder
    extends MessageOrBuilder {
        public boolean hasSeverity();

        public Error.Severity getSeverity();

        public boolean hasCode();

        public int getCode();

        public boolean hasSqlState();

        public String getSqlState();

        public ByteString getSqlStateBytes();

        public boolean hasMsg();

        public String getMsg();

        public ByteString getMsgBytes();
    }

    public static final class Ok
    extends GeneratedMessage
    implements OkOrBuilder {
        private static final Ok defaultInstance;
        private final UnknownFieldSet unknownFields;
        public static Parser<Ok> PARSER;
        private int bitField0_;
        public static final int MSG_FIELD_NUMBER = 1;
        private Object msg_;
        private byte memoizedIsInitialized = (byte)-1;
        private int memoizedSerializedSize = -1;
        private static final long serialVersionUID = 0L;

        private Ok(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.unknownFields = builder.getUnknownFields();
        }

        private Ok(boolean noInit) {
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static Ok getDefaultInstance() {
            return defaultInstance;
        }

        @Override
        public Ok getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private Ok(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
                    ByteString bs = input.readBytes();
                    this.bitField0_ |= 1;
                    this.msg_ = bs;
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
            return internal_static_Mysqlx_Ok_descriptor;
        }

        @Override
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return internal_static_Mysqlx_Ok_fieldAccessorTable.ensureFieldAccessorsInitialized(Ok.class, Builder.class);
        }

        public Parser<Ok> getParserForType() {
            return PARSER;
        }

        @Override
        public boolean hasMsg() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override
        public String getMsg() {
            Object ref = this.msg_;
            if (ref instanceof String) {
                return (String)ref;
            }
            ByteString bs = (ByteString)ref;
            String s2 = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.msg_ = s2;
            }
            return s2;
        }

        @Override
        public ByteString getMsgBytes() {
            Object ref = this.msg_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.msg_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        private void initFields() {
            this.msg_ = "";
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
            this.memoizedIsInitialized = 1;
            return true;
        }

        @Override
        public void writeTo(CodedOutputStream output) throws IOException {
            this.getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                output.writeBytes(1, this.getMsgBytes());
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
                size += CodedOutputStream.computeBytesSize(1, this.getMsgBytes());
            }
            this.memoizedSerializedSize = size += this.getUnknownFields().getSerializedSize();
            return size;
        }

        @Override
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static Ok parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static Ok parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static Ok parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static Ok parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static Ok parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static Ok parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Ok parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static Ok parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static Ok parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static Ok parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override
        public Builder newBuilderForType() {
            return Ok.newBuilder();
        }

        public static Builder newBuilder(Ok prototype) {
            return Ok.newBuilder().mergeFrom(prototype);
        }

        @Override
        public Builder toBuilder() {
            return Ok.newBuilder(this);
        }

        @Override
        protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        static {
            PARSER = new AbstractParser<Ok>(){

                @Override
                public Ok parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                    return new Ok(input, extensionRegistry);
                }
            };
            defaultInstance = new Ok(true);
            defaultInstance.initFields();
        }

        public static final class Builder
        extends GeneratedMessage.Builder<Builder>
        implements OkOrBuilder {
            private int bitField0_;
            private Object msg_ = "";

            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_Mysqlx_Ok_descriptor;
            }

            @Override
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_Mysqlx_Ok_fieldAccessorTable.ensureFieldAccessorsInitialized(Ok.class, Builder.class);
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
                this.msg_ = "";
                this.bitField0_ &= 0xFFFFFFFE;
                return this;
            }

            @Override
            public Builder clone() {
                return Builder.create().mergeFrom(this.buildPartial());
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return internal_static_Mysqlx_Ok_descriptor;
            }

            @Override
            public Ok getDefaultInstanceForType() {
                return Ok.getDefaultInstance();
            }

            @Override
            public Ok build() {
                Ok result = this.buildPartial();
                if (!result.isInitialized()) {
                    throw Builder.newUninitializedMessageException(result);
                }
                return result;
            }

            @Override
            public Ok buildPartial() {
                Ok result = new Ok(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ |= 1;
                }
                result.msg_ = this.msg_;
                result.bitField0_ = to_bitField0_;
                this.onBuilt();
                return result;
            }

            @Override
            public Builder mergeFrom(Message other) {
                if (other instanceof Ok) {
                    return this.mergeFrom((Ok)other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(Ok other) {
                if (other == Ok.getDefaultInstance()) {
                    return this;
                }
                if (other.hasMsg()) {
                    this.bitField0_ |= 1;
                    this.msg_ = other.msg_;
                    this.onChanged();
                }
                this.mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            @Override
            public final boolean isInitialized() {
                return true;
            }

            @Override
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                Ok parsedMessage = null;
                try {
                    parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
                } catch (InvalidProtocolBufferException e) {
                    parsedMessage = (Ok)e.getUnfinishedMessage();
                    throw e;
                } finally {
                    if (parsedMessage != null) {
                        this.mergeFrom(parsedMessage);
                    }
                }
                return this;
            }

            @Override
            public boolean hasMsg() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override
            public String getMsg() {
                Object ref = this.msg_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString)ref;
                    String s2 = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.msg_ = s2;
                    }
                    return s2;
                }
                return (String)ref;
            }

            @Override
            public ByteString getMsgBytes() {
                Object ref = this.msg_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String)ref);
                    this.msg_ = b;
                    return b;
                }
                return (ByteString)ref;
            }

            public Builder setMsg(String value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 1;
                this.msg_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearMsg() {
                this.bitField0_ &= 0xFFFFFFFE;
                this.msg_ = Ok.getDefaultInstance().getMsg();
                this.onChanged();
                return this;
            }

            public Builder setMsgBytes(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 1;
                this.msg_ = value;
                this.onChanged();
                return this;
            }
        }
    }

    public static interface OkOrBuilder
    extends MessageOrBuilder {
        public boolean hasMsg();

        public String getMsg();

        public ByteString getMsgBytes();
    }

    public static final class ServerMessages
    extends GeneratedMessage
    implements ServerMessagesOrBuilder {
        private static final ServerMessages defaultInstance;
        private final UnknownFieldSet unknownFields;
        public static Parser<ServerMessages> PARSER;
        private byte memoizedIsInitialized = (byte)-1;
        private int memoizedSerializedSize = -1;
        private static final long serialVersionUID = 0L;

        private ServerMessages(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.unknownFields = builder.getUnknownFields();
        }

        private ServerMessages(boolean noInit) {
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static ServerMessages getDefaultInstance() {
            return defaultInstance;
        }

        @Override
        public ServerMessages getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private ServerMessages(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this.initFields();
            UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
            try {
                boolean done = false;
                block9: while (!done) {
                    int tag = input.readTag();
                    switch (tag) {
                        case 0: {
                            done = true;
                            continue block9;
                        }
                    }
                    if (this.parseUnknownField(input, unknownFields, extensionRegistry, tag)) continue;
                    done = true;
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
            return internal_static_Mysqlx_ServerMessages_descriptor;
        }

        @Override
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return internal_static_Mysqlx_ServerMessages_fieldAccessorTable.ensureFieldAccessorsInitialized(ServerMessages.class, Builder.class);
        }

        public Parser<ServerMessages> getParserForType() {
            return PARSER;
        }

        private void initFields() {
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
            this.memoizedIsInitialized = 1;
            return true;
        }

        @Override
        public void writeTo(CodedOutputStream output) throws IOException {
            this.getSerializedSize();
            this.getUnknownFields().writeTo(output);
        }

        @Override
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            size = 0;
            this.memoizedSerializedSize = size += this.getUnknownFields().getSerializedSize();
            return size;
        }

        @Override
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static ServerMessages parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static ServerMessages parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static ServerMessages parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static ServerMessages parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static ServerMessages parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static ServerMessages parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static ServerMessages parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static ServerMessages parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static ServerMessages parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static ServerMessages parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override
        public Builder newBuilderForType() {
            return ServerMessages.newBuilder();
        }

        public static Builder newBuilder(ServerMessages prototype) {
            return ServerMessages.newBuilder().mergeFrom(prototype);
        }

        @Override
        public Builder toBuilder() {
            return ServerMessages.newBuilder(this);
        }

        @Override
        protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        static {
            PARSER = new AbstractParser<ServerMessages>(){

                @Override
                public ServerMessages parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                    return new ServerMessages(input, extensionRegistry);
                }
            };
            defaultInstance = new ServerMessages(true);
            defaultInstance.initFields();
        }

        public static final class Builder
        extends GeneratedMessage.Builder<Builder>
        implements ServerMessagesOrBuilder {
            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_Mysqlx_ServerMessages_descriptor;
            }

            @Override
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_Mysqlx_ServerMessages_fieldAccessorTable.ensureFieldAccessorsInitialized(ServerMessages.class, Builder.class);
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
                return this;
            }

            @Override
            public Builder clone() {
                return Builder.create().mergeFrom(this.buildPartial());
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return internal_static_Mysqlx_ServerMessages_descriptor;
            }

            @Override
            public ServerMessages getDefaultInstanceForType() {
                return ServerMessages.getDefaultInstance();
            }

            @Override
            public ServerMessages build() {
                ServerMessages result = this.buildPartial();
                if (!result.isInitialized()) {
                    throw Builder.newUninitializedMessageException(result);
                }
                return result;
            }

            @Override
            public ServerMessages buildPartial() {
                ServerMessages result = new ServerMessages(this);
                this.onBuilt();
                return result;
            }

            @Override
            public Builder mergeFrom(Message other) {
                if (other instanceof ServerMessages) {
                    return this.mergeFrom((ServerMessages)other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(ServerMessages other) {
                if (other == ServerMessages.getDefaultInstance()) {
                    return this;
                }
                this.mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            @Override
            public final boolean isInitialized() {
                return true;
            }

            @Override
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                ServerMessages parsedMessage = null;
                try {
                    parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
                } catch (InvalidProtocolBufferException e) {
                    parsedMessage = (ServerMessages)e.getUnfinishedMessage();
                    throw e;
                } finally {
                    if (parsedMessage != null) {
                        this.mergeFrom(parsedMessage);
                    }
                }
                return this;
            }
        }

        public static enum Type implements ProtocolMessageEnum
        {
            OK(0, 0),
            ERROR(1, 1),
            CONN_CAPABILITIES(2, 2),
            SESS_AUTHENTICATE_CONTINUE(3, 3),
            SESS_AUTHENTICATE_OK(4, 4),
            NOTICE(5, 11),
            RESULTSET_COLUMN_META_DATA(6, 12),
            RESULTSET_ROW(7, 13),
            RESULTSET_FETCH_DONE(8, 14),
            RESULTSET_FETCH_SUSPENDED(9, 15),
            RESULTSET_FETCH_DONE_MORE_RESULTSETS(10, 16),
            SQL_STMT_EXECUTE_OK(11, 17),
            RESULTSET_FETCH_DONE_MORE_OUT_PARAMS(12, 18);

            public static final int OK_VALUE = 0;
            public static final int ERROR_VALUE = 1;
            public static final int CONN_CAPABILITIES_VALUE = 2;
            public static final int SESS_AUTHENTICATE_CONTINUE_VALUE = 3;
            public static final int SESS_AUTHENTICATE_OK_VALUE = 4;
            public static final int NOTICE_VALUE = 11;
            public static final int RESULTSET_COLUMN_META_DATA_VALUE = 12;
            public static final int RESULTSET_ROW_VALUE = 13;
            public static final int RESULTSET_FETCH_DONE_VALUE = 14;
            public static final int RESULTSET_FETCH_SUSPENDED_VALUE = 15;
            public static final int RESULTSET_FETCH_DONE_MORE_RESULTSETS_VALUE = 16;
            public static final int SQL_STMT_EXECUTE_OK_VALUE = 17;
            public static final int RESULTSET_FETCH_DONE_MORE_OUT_PARAMS_VALUE = 18;
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
                    case 0: {
                        return OK;
                    }
                    case 1: {
                        return ERROR;
                    }
                    case 2: {
                        return CONN_CAPABILITIES;
                    }
                    case 3: {
                        return SESS_AUTHENTICATE_CONTINUE;
                    }
                    case 4: {
                        return SESS_AUTHENTICATE_OK;
                    }
                    case 11: {
                        return NOTICE;
                    }
                    case 12: {
                        return RESULTSET_COLUMN_META_DATA;
                    }
                    case 13: {
                        return RESULTSET_ROW;
                    }
                    case 14: {
                        return RESULTSET_FETCH_DONE;
                    }
                    case 15: {
                        return RESULTSET_FETCH_SUSPENDED;
                    }
                    case 16: {
                        return RESULTSET_FETCH_DONE_MORE_RESULTSETS;
                    }
                    case 17: {
                        return SQL_STMT_EXECUTE_OK;
                    }
                    case 18: {
                        return RESULTSET_FETCH_DONE_MORE_OUT_PARAMS;
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
                return ServerMessages.getDescriptor().getEnumTypes().get(0);
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

    public static interface ServerMessagesOrBuilder
    extends MessageOrBuilder {
    }

    public static final class ClientMessages
    extends GeneratedMessage
    implements ClientMessagesOrBuilder {
        private static final ClientMessages defaultInstance;
        private final UnknownFieldSet unknownFields;
        public static Parser<ClientMessages> PARSER;
        private byte memoizedIsInitialized = (byte)-1;
        private int memoizedSerializedSize = -1;
        private static final long serialVersionUID = 0L;

        private ClientMessages(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.unknownFields = builder.getUnknownFields();
        }

        private ClientMessages(boolean noInit) {
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static ClientMessages getDefaultInstance() {
            return defaultInstance;
        }

        @Override
        public ClientMessages getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private ClientMessages(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this.initFields();
            UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
            try {
                boolean done = false;
                block9: while (!done) {
                    int tag = input.readTag();
                    switch (tag) {
                        case 0: {
                            done = true;
                            continue block9;
                        }
                    }
                    if (this.parseUnknownField(input, unknownFields, extensionRegistry, tag)) continue;
                    done = true;
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
            return internal_static_Mysqlx_ClientMessages_descriptor;
        }

        @Override
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return internal_static_Mysqlx_ClientMessages_fieldAccessorTable.ensureFieldAccessorsInitialized(ClientMessages.class, Builder.class);
        }

        public Parser<ClientMessages> getParserForType() {
            return PARSER;
        }

        private void initFields() {
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
            this.memoizedIsInitialized = 1;
            return true;
        }

        @Override
        public void writeTo(CodedOutputStream output) throws IOException {
            this.getSerializedSize();
            this.getUnknownFields().writeTo(output);
        }

        @Override
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            size = 0;
            this.memoizedSerializedSize = size += this.getUnknownFields().getSerializedSize();
            return size;
        }

        @Override
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static ClientMessages parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static ClientMessages parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static ClientMessages parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static ClientMessages parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static ClientMessages parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static ClientMessages parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static ClientMessages parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static ClientMessages parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static ClientMessages parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static ClientMessages parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override
        public Builder newBuilderForType() {
            return ClientMessages.newBuilder();
        }

        public static Builder newBuilder(ClientMessages prototype) {
            return ClientMessages.newBuilder().mergeFrom(prototype);
        }

        @Override
        public Builder toBuilder() {
            return ClientMessages.newBuilder(this);
        }

        @Override
        protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        static {
            PARSER = new AbstractParser<ClientMessages>(){

                @Override
                public ClientMessages parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                    return new ClientMessages(input, extensionRegistry);
                }
            };
            defaultInstance = new ClientMessages(true);
            defaultInstance.initFields();
        }

        public static final class Builder
        extends GeneratedMessage.Builder<Builder>
        implements ClientMessagesOrBuilder {
            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_Mysqlx_ClientMessages_descriptor;
            }

            @Override
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_Mysqlx_ClientMessages_fieldAccessorTable.ensureFieldAccessorsInitialized(ClientMessages.class, Builder.class);
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
                return this;
            }

            @Override
            public Builder clone() {
                return Builder.create().mergeFrom(this.buildPartial());
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return internal_static_Mysqlx_ClientMessages_descriptor;
            }

            @Override
            public ClientMessages getDefaultInstanceForType() {
                return ClientMessages.getDefaultInstance();
            }

            @Override
            public ClientMessages build() {
                ClientMessages result = this.buildPartial();
                if (!result.isInitialized()) {
                    throw Builder.newUninitializedMessageException(result);
                }
                return result;
            }

            @Override
            public ClientMessages buildPartial() {
                ClientMessages result = new ClientMessages(this);
                this.onBuilt();
                return result;
            }

            @Override
            public Builder mergeFrom(Message other) {
                if (other instanceof ClientMessages) {
                    return this.mergeFrom((ClientMessages)other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(ClientMessages other) {
                if (other == ClientMessages.getDefaultInstance()) {
                    return this;
                }
                this.mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            @Override
            public final boolean isInitialized() {
                return true;
            }

            @Override
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                ClientMessages parsedMessage = null;
                try {
                    parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
                } catch (InvalidProtocolBufferException e) {
                    parsedMessage = (ClientMessages)e.getUnfinishedMessage();
                    throw e;
                } finally {
                    if (parsedMessage != null) {
                        this.mergeFrom(parsedMessage);
                    }
                }
                return this;
            }
        }

        public static enum Type implements ProtocolMessageEnum
        {
            CON_CAPABILITIES_GET(0, 1),
            CON_CAPABILITIES_SET(1, 2),
            CON_CLOSE(2, 3),
            SESS_AUTHENTICATE_START(3, 4),
            SESS_AUTHENTICATE_CONTINUE(4, 5),
            SESS_RESET(5, 6),
            SESS_CLOSE(6, 7),
            SQL_STMT_EXECUTE(7, 12),
            CRUD_FIND(8, 17),
            CRUD_INSERT(9, 18),
            CRUD_UPDATE(10, 19),
            CRUD_DELETE(11, 20),
            EXPECT_OPEN(12, 24),
            EXPECT_CLOSE(13, 25),
            CRUD_CREATE_VIEW(14, 30),
            CRUD_MODIFY_VIEW(15, 31),
            CRUD_DROP_VIEW(16, 32);

            public static final int CON_CAPABILITIES_GET_VALUE = 1;
            public static final int CON_CAPABILITIES_SET_VALUE = 2;
            public static final int CON_CLOSE_VALUE = 3;
            public static final int SESS_AUTHENTICATE_START_VALUE = 4;
            public static final int SESS_AUTHENTICATE_CONTINUE_VALUE = 5;
            public static final int SESS_RESET_VALUE = 6;
            public static final int SESS_CLOSE_VALUE = 7;
            public static final int SQL_STMT_EXECUTE_VALUE = 12;
            public static final int CRUD_FIND_VALUE = 17;
            public static final int CRUD_INSERT_VALUE = 18;
            public static final int CRUD_UPDATE_VALUE = 19;
            public static final int CRUD_DELETE_VALUE = 20;
            public static final int EXPECT_OPEN_VALUE = 24;
            public static final int EXPECT_CLOSE_VALUE = 25;
            public static final int CRUD_CREATE_VIEW_VALUE = 30;
            public static final int CRUD_MODIFY_VIEW_VALUE = 31;
            public static final int CRUD_DROP_VIEW_VALUE = 32;
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
                        return CON_CAPABILITIES_GET;
                    }
                    case 2: {
                        return CON_CAPABILITIES_SET;
                    }
                    case 3: {
                        return CON_CLOSE;
                    }
                    case 4: {
                        return SESS_AUTHENTICATE_START;
                    }
                    case 5: {
                        return SESS_AUTHENTICATE_CONTINUE;
                    }
                    case 6: {
                        return SESS_RESET;
                    }
                    case 7: {
                        return SESS_CLOSE;
                    }
                    case 12: {
                        return SQL_STMT_EXECUTE;
                    }
                    case 17: {
                        return CRUD_FIND;
                    }
                    case 18: {
                        return CRUD_INSERT;
                    }
                    case 19: {
                        return CRUD_UPDATE;
                    }
                    case 20: {
                        return CRUD_DELETE;
                    }
                    case 24: {
                        return EXPECT_OPEN;
                    }
                    case 25: {
                        return EXPECT_CLOSE;
                    }
                    case 30: {
                        return CRUD_CREATE_VIEW;
                    }
                    case 31: {
                        return CRUD_MODIFY_VIEW;
                    }
                    case 32: {
                        return CRUD_DROP_VIEW;
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
                return ClientMessages.getDescriptor().getEnumTypes().get(0);
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

    public static interface ClientMessagesOrBuilder
    extends MessageOrBuilder {
    }
}

