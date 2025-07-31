/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.x.protobuf;

import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.UnknownFieldSet;
import com.mysql.cj.x.protobuf.Mysqlx;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectStreamException;

public final class MysqlxSession {
    private static final Descriptors.Descriptor internal_static_Mysqlx_Session_AuthenticateStart_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_Mysqlx_Session_AuthenticateStart_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_Mysqlx_Session_AuthenticateContinue_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_Mysqlx_Session_AuthenticateContinue_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_Mysqlx_Session_AuthenticateOk_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_Mysqlx_Session_AuthenticateOk_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_Mysqlx_Session_Reset_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_Mysqlx_Session_Reset_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_Mysqlx_Session_Close_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_Mysqlx_Session_Close_fieldAccessorTable;
    private static Descriptors.FileDescriptor descriptor;

    private MysqlxSession() {
    }

    public static void registerAllExtensions(ExtensionRegistry registry) {
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        String[] descriptorData = new String[]{"\n\u0014mysqlx_session.proto\u0012\u000eMysqlx.Session\u001a\fmysqlx.proto\"Y\n\u0011AuthenticateStart\u0012\u0011\n\tmech_name\u0018\u0001 \u0002(\t\u0012\u0011\n\tauth_data\u0018\u0002 \u0001(\f\u0012\u0018\n\u0010initial_response\u0018\u0003 \u0001(\f:\u0004\u0088\u00ea0\u0004\"3\n\u0014AuthenticateContinue\u0012\u0011\n\tauth_data\u0018\u0001 \u0002(\f:\b\u0090\u00ea0\u0003\u0088\u00ea0\u0005\")\n\u000eAuthenticateOk\u0012\u0011\n\tauth_data\u0018\u0001 \u0001(\f:\u0004\u0090\u00ea0\u0004\"\r\n\u0005Reset:\u0004\u0088\u00ea0\u0006\"\r\n\u0005Close:\u0004\u0088\u00ea0\u0007B\u0019\n\u0017com.mysql.cj.x.protobuf"};
        Descriptors.FileDescriptor.InternalDescriptorAssigner assigner = new Descriptors.FileDescriptor.InternalDescriptorAssigner(){

            @Override
            public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor root) {
                descriptor = root;
                return null;
            }
        };
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[]{Mysqlx.getDescriptor()}, assigner);
        internal_static_Mysqlx_Session_AuthenticateStart_descriptor = MysqlxSession.getDescriptor().getMessageTypes().get(0);
        internal_static_Mysqlx_Session_AuthenticateStart_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_Mysqlx_Session_AuthenticateStart_descriptor, new String[]{"MechName", "AuthData", "InitialResponse"});
        internal_static_Mysqlx_Session_AuthenticateContinue_descriptor = MysqlxSession.getDescriptor().getMessageTypes().get(1);
        internal_static_Mysqlx_Session_AuthenticateContinue_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_Mysqlx_Session_AuthenticateContinue_descriptor, new String[]{"AuthData"});
        internal_static_Mysqlx_Session_AuthenticateOk_descriptor = MysqlxSession.getDescriptor().getMessageTypes().get(2);
        internal_static_Mysqlx_Session_AuthenticateOk_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_Mysqlx_Session_AuthenticateOk_descriptor, new String[]{"AuthData"});
        internal_static_Mysqlx_Session_Reset_descriptor = MysqlxSession.getDescriptor().getMessageTypes().get(3);
        internal_static_Mysqlx_Session_Reset_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_Mysqlx_Session_Reset_descriptor, new String[0]);
        internal_static_Mysqlx_Session_Close_descriptor = MysqlxSession.getDescriptor().getMessageTypes().get(4);
        internal_static_Mysqlx_Session_Close_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_Mysqlx_Session_Close_descriptor, new String[0]);
        ExtensionRegistry registry = ExtensionRegistry.newInstance();
        registry.add(Mysqlx.clientMessageId);
        registry.add(Mysqlx.clientMessageId);
        registry.add(Mysqlx.serverMessageId);
        registry.add(Mysqlx.serverMessageId);
        registry.add(Mysqlx.clientMessageId);
        registry.add(Mysqlx.clientMessageId);
        Descriptors.FileDescriptor.internalUpdateFileDescriptor(descriptor, registry);
        Mysqlx.getDescriptor();
    }

    public static final class Close
    extends GeneratedMessage
    implements CloseOrBuilder {
        private static final Close defaultInstance;
        private final UnknownFieldSet unknownFields;
        public static Parser<Close> PARSER;
        private byte memoizedIsInitialized = (byte)-1;
        private int memoizedSerializedSize = -1;
        private static final long serialVersionUID = 0L;

        private Close(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.unknownFields = builder.getUnknownFields();
        }

        private Close(boolean noInit) {
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static Close getDefaultInstance() {
            return defaultInstance;
        }

        @Override
        public Close getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private Close(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
            return internal_static_Mysqlx_Session_Close_descriptor;
        }

        @Override
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return internal_static_Mysqlx_Session_Close_fieldAccessorTable.ensureFieldAccessorsInitialized(Close.class, Builder.class);
        }

        public Parser<Close> getParserForType() {
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

        public static Close parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static Close parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static Close parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static Close parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static Close parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static Close parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Close parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static Close parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static Close parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static Close parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override
        public Builder newBuilderForType() {
            return Close.newBuilder();
        }

        public static Builder newBuilder(Close prototype) {
            return Close.newBuilder().mergeFrom(prototype);
        }

        @Override
        public Builder toBuilder() {
            return Close.newBuilder(this);
        }

        @Override
        protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        static {
            PARSER = new AbstractParser<Close>(){

                @Override
                public Close parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                    return new Close(input, extensionRegistry);
                }
            };
            defaultInstance = new Close(true);
            defaultInstance.initFields();
        }

        public static final class Builder
        extends GeneratedMessage.Builder<Builder>
        implements CloseOrBuilder {
            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_Mysqlx_Session_Close_descriptor;
            }

            @Override
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_Mysqlx_Session_Close_fieldAccessorTable.ensureFieldAccessorsInitialized(Close.class, Builder.class);
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
                return internal_static_Mysqlx_Session_Close_descriptor;
            }

            @Override
            public Close getDefaultInstanceForType() {
                return Close.getDefaultInstance();
            }

            @Override
            public Close build() {
                Close result = this.buildPartial();
                if (!result.isInitialized()) {
                    throw Builder.newUninitializedMessageException(result);
                }
                return result;
            }

            @Override
            public Close buildPartial() {
                Close result = new Close(this);
                this.onBuilt();
                return result;
            }

            @Override
            public Builder mergeFrom(Message other) {
                if (other instanceof Close) {
                    return this.mergeFrom((Close)other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(Close other) {
                if (other == Close.getDefaultInstance()) {
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
                Close parsedMessage = null;
                try {
                    parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
                } catch (InvalidProtocolBufferException e) {
                    parsedMessage = (Close)e.getUnfinishedMessage();
                    throw e;
                } finally {
                    if (parsedMessage != null) {
                        this.mergeFrom(parsedMessage);
                    }
                }
                return this;
            }
        }
    }

    public static interface CloseOrBuilder
    extends MessageOrBuilder {
    }

    public static final class Reset
    extends GeneratedMessage
    implements ResetOrBuilder {
        private static final Reset defaultInstance;
        private final UnknownFieldSet unknownFields;
        public static Parser<Reset> PARSER;
        private byte memoizedIsInitialized = (byte)-1;
        private int memoizedSerializedSize = -1;
        private static final long serialVersionUID = 0L;

        private Reset(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.unknownFields = builder.getUnknownFields();
        }

        private Reset(boolean noInit) {
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static Reset getDefaultInstance() {
            return defaultInstance;
        }

        @Override
        public Reset getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private Reset(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
            return internal_static_Mysqlx_Session_Reset_descriptor;
        }

        @Override
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return internal_static_Mysqlx_Session_Reset_fieldAccessorTable.ensureFieldAccessorsInitialized(Reset.class, Builder.class);
        }

        public Parser<Reset> getParserForType() {
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

        public static Reset parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static Reset parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static Reset parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static Reset parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static Reset parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static Reset parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Reset parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static Reset parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static Reset parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static Reset parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override
        public Builder newBuilderForType() {
            return Reset.newBuilder();
        }

        public static Builder newBuilder(Reset prototype) {
            return Reset.newBuilder().mergeFrom(prototype);
        }

        @Override
        public Builder toBuilder() {
            return Reset.newBuilder(this);
        }

        @Override
        protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        static {
            PARSER = new AbstractParser<Reset>(){

                @Override
                public Reset parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                    return new Reset(input, extensionRegistry);
                }
            };
            defaultInstance = new Reset(true);
            defaultInstance.initFields();
        }

        public static final class Builder
        extends GeneratedMessage.Builder<Builder>
        implements ResetOrBuilder {
            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_Mysqlx_Session_Reset_descriptor;
            }

            @Override
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_Mysqlx_Session_Reset_fieldAccessorTable.ensureFieldAccessorsInitialized(Reset.class, Builder.class);
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
                return internal_static_Mysqlx_Session_Reset_descriptor;
            }

            @Override
            public Reset getDefaultInstanceForType() {
                return Reset.getDefaultInstance();
            }

            @Override
            public Reset build() {
                Reset result = this.buildPartial();
                if (!result.isInitialized()) {
                    throw Builder.newUninitializedMessageException(result);
                }
                return result;
            }

            @Override
            public Reset buildPartial() {
                Reset result = new Reset(this);
                this.onBuilt();
                return result;
            }

            @Override
            public Builder mergeFrom(Message other) {
                if (other instanceof Reset) {
                    return this.mergeFrom((Reset)other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(Reset other) {
                if (other == Reset.getDefaultInstance()) {
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
                Reset parsedMessage = null;
                try {
                    parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
                } catch (InvalidProtocolBufferException e) {
                    parsedMessage = (Reset)e.getUnfinishedMessage();
                    throw e;
                } finally {
                    if (parsedMessage != null) {
                        this.mergeFrom(parsedMessage);
                    }
                }
                return this;
            }
        }
    }

    public static interface ResetOrBuilder
    extends MessageOrBuilder {
    }

    public static final class AuthenticateOk
    extends GeneratedMessage
    implements AuthenticateOkOrBuilder {
        private static final AuthenticateOk defaultInstance;
        private final UnknownFieldSet unknownFields;
        public static Parser<AuthenticateOk> PARSER;
        private int bitField0_;
        public static final int AUTH_DATA_FIELD_NUMBER = 1;
        private ByteString authData_;
        private byte memoizedIsInitialized = (byte)-1;
        private int memoizedSerializedSize = -1;
        private static final long serialVersionUID = 0L;

        private AuthenticateOk(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.unknownFields = builder.getUnknownFields();
        }

        private AuthenticateOk(boolean noInit) {
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static AuthenticateOk getDefaultInstance() {
            return defaultInstance;
        }

        @Override
        public AuthenticateOk getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private AuthenticateOk(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
                    this.bitField0_ |= 1;
                    this.authData_ = input.readBytes();
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
            return internal_static_Mysqlx_Session_AuthenticateOk_descriptor;
        }

        @Override
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return internal_static_Mysqlx_Session_AuthenticateOk_fieldAccessorTable.ensureFieldAccessorsInitialized(AuthenticateOk.class, Builder.class);
        }

        public Parser<AuthenticateOk> getParserForType() {
            return PARSER;
        }

        @Override
        public boolean hasAuthData() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override
        public ByteString getAuthData() {
            return this.authData_;
        }

        private void initFields() {
            this.authData_ = ByteString.EMPTY;
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
                output.writeBytes(1, this.authData_);
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
                size += CodedOutputStream.computeBytesSize(1, this.authData_);
            }
            this.memoizedSerializedSize = size += this.getUnknownFields().getSerializedSize();
            return size;
        }

        @Override
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static AuthenticateOk parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static AuthenticateOk parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static AuthenticateOk parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static AuthenticateOk parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static AuthenticateOk parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static AuthenticateOk parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static AuthenticateOk parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static AuthenticateOk parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static AuthenticateOk parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static AuthenticateOk parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override
        public Builder newBuilderForType() {
            return AuthenticateOk.newBuilder();
        }

        public static Builder newBuilder(AuthenticateOk prototype) {
            return AuthenticateOk.newBuilder().mergeFrom(prototype);
        }

        @Override
        public Builder toBuilder() {
            return AuthenticateOk.newBuilder(this);
        }

        @Override
        protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        static {
            PARSER = new AbstractParser<AuthenticateOk>(){

                @Override
                public AuthenticateOk parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                    return new AuthenticateOk(input, extensionRegistry);
                }
            };
            defaultInstance = new AuthenticateOk(true);
            defaultInstance.initFields();
        }

        public static final class Builder
        extends GeneratedMessage.Builder<Builder>
        implements AuthenticateOkOrBuilder {
            private int bitField0_;
            private ByteString authData_ = ByteString.EMPTY;

            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_Mysqlx_Session_AuthenticateOk_descriptor;
            }

            @Override
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_Mysqlx_Session_AuthenticateOk_fieldAccessorTable.ensureFieldAccessorsInitialized(AuthenticateOk.class, Builder.class);
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
                this.authData_ = ByteString.EMPTY;
                this.bitField0_ &= 0xFFFFFFFE;
                return this;
            }

            @Override
            public Builder clone() {
                return Builder.create().mergeFrom(this.buildPartial());
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return internal_static_Mysqlx_Session_AuthenticateOk_descriptor;
            }

            @Override
            public AuthenticateOk getDefaultInstanceForType() {
                return AuthenticateOk.getDefaultInstance();
            }

            @Override
            public AuthenticateOk build() {
                AuthenticateOk result = this.buildPartial();
                if (!result.isInitialized()) {
                    throw Builder.newUninitializedMessageException(result);
                }
                return result;
            }

            @Override
            public AuthenticateOk buildPartial() {
                AuthenticateOk result = new AuthenticateOk(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ |= 1;
                }
                result.authData_ = this.authData_;
                result.bitField0_ = to_bitField0_;
                this.onBuilt();
                return result;
            }

            @Override
            public Builder mergeFrom(Message other) {
                if (other instanceof AuthenticateOk) {
                    return this.mergeFrom((AuthenticateOk)other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(AuthenticateOk other) {
                if (other == AuthenticateOk.getDefaultInstance()) {
                    return this;
                }
                if (other.hasAuthData()) {
                    this.setAuthData(other.getAuthData());
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
                AuthenticateOk parsedMessage = null;
                try {
                    parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
                } catch (InvalidProtocolBufferException e) {
                    parsedMessage = (AuthenticateOk)e.getUnfinishedMessage();
                    throw e;
                } finally {
                    if (parsedMessage != null) {
                        this.mergeFrom(parsedMessage);
                    }
                }
                return this;
            }

            @Override
            public boolean hasAuthData() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override
            public ByteString getAuthData() {
                return this.authData_;
            }

            public Builder setAuthData(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 1;
                this.authData_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearAuthData() {
                this.bitField0_ &= 0xFFFFFFFE;
                this.authData_ = AuthenticateOk.getDefaultInstance().getAuthData();
                this.onChanged();
                return this;
            }
        }
    }

    public static interface AuthenticateOkOrBuilder
    extends MessageOrBuilder {
        public boolean hasAuthData();

        public ByteString getAuthData();
    }

    public static final class AuthenticateContinue
    extends GeneratedMessage
    implements AuthenticateContinueOrBuilder {
        private static final AuthenticateContinue defaultInstance;
        private final UnknownFieldSet unknownFields;
        public static Parser<AuthenticateContinue> PARSER;
        private int bitField0_;
        public static final int AUTH_DATA_FIELD_NUMBER = 1;
        private ByteString authData_;
        private byte memoizedIsInitialized = (byte)-1;
        private int memoizedSerializedSize = -1;
        private static final long serialVersionUID = 0L;

        private AuthenticateContinue(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.unknownFields = builder.getUnknownFields();
        }

        private AuthenticateContinue(boolean noInit) {
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static AuthenticateContinue getDefaultInstance() {
            return defaultInstance;
        }

        @Override
        public AuthenticateContinue getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private AuthenticateContinue(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
                    this.bitField0_ |= 1;
                    this.authData_ = input.readBytes();
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
            return internal_static_Mysqlx_Session_AuthenticateContinue_descriptor;
        }

        @Override
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return internal_static_Mysqlx_Session_AuthenticateContinue_fieldAccessorTable.ensureFieldAccessorsInitialized(AuthenticateContinue.class, Builder.class);
        }

        public Parser<AuthenticateContinue> getParserForType() {
            return PARSER;
        }

        @Override
        public boolean hasAuthData() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override
        public ByteString getAuthData() {
            return this.authData_;
        }

        private void initFields() {
            this.authData_ = ByteString.EMPTY;
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
            if (!this.hasAuthData()) {
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
                output.writeBytes(1, this.authData_);
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
                size += CodedOutputStream.computeBytesSize(1, this.authData_);
            }
            this.memoizedSerializedSize = size += this.getUnknownFields().getSerializedSize();
            return size;
        }

        @Override
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static AuthenticateContinue parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static AuthenticateContinue parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static AuthenticateContinue parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static AuthenticateContinue parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static AuthenticateContinue parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static AuthenticateContinue parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static AuthenticateContinue parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static AuthenticateContinue parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static AuthenticateContinue parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static AuthenticateContinue parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override
        public Builder newBuilderForType() {
            return AuthenticateContinue.newBuilder();
        }

        public static Builder newBuilder(AuthenticateContinue prototype) {
            return AuthenticateContinue.newBuilder().mergeFrom(prototype);
        }

        @Override
        public Builder toBuilder() {
            return AuthenticateContinue.newBuilder(this);
        }

        @Override
        protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        static {
            PARSER = new AbstractParser<AuthenticateContinue>(){

                @Override
                public AuthenticateContinue parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                    return new AuthenticateContinue(input, extensionRegistry);
                }
            };
            defaultInstance = new AuthenticateContinue(true);
            defaultInstance.initFields();
        }

        public static final class Builder
        extends GeneratedMessage.Builder<Builder>
        implements AuthenticateContinueOrBuilder {
            private int bitField0_;
            private ByteString authData_ = ByteString.EMPTY;

            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_Mysqlx_Session_AuthenticateContinue_descriptor;
            }

            @Override
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_Mysqlx_Session_AuthenticateContinue_fieldAccessorTable.ensureFieldAccessorsInitialized(AuthenticateContinue.class, Builder.class);
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
                this.authData_ = ByteString.EMPTY;
                this.bitField0_ &= 0xFFFFFFFE;
                return this;
            }

            @Override
            public Builder clone() {
                return Builder.create().mergeFrom(this.buildPartial());
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return internal_static_Mysqlx_Session_AuthenticateContinue_descriptor;
            }

            @Override
            public AuthenticateContinue getDefaultInstanceForType() {
                return AuthenticateContinue.getDefaultInstance();
            }

            @Override
            public AuthenticateContinue build() {
                AuthenticateContinue result = this.buildPartial();
                if (!result.isInitialized()) {
                    throw Builder.newUninitializedMessageException(result);
                }
                return result;
            }

            @Override
            public AuthenticateContinue buildPartial() {
                AuthenticateContinue result = new AuthenticateContinue(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ |= 1;
                }
                result.authData_ = this.authData_;
                result.bitField0_ = to_bitField0_;
                this.onBuilt();
                return result;
            }

            @Override
            public Builder mergeFrom(Message other) {
                if (other instanceof AuthenticateContinue) {
                    return this.mergeFrom((AuthenticateContinue)other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(AuthenticateContinue other) {
                if (other == AuthenticateContinue.getDefaultInstance()) {
                    return this;
                }
                if (other.hasAuthData()) {
                    this.setAuthData(other.getAuthData());
                }
                this.mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            @Override
            public final boolean isInitialized() {
                return this.hasAuthData();
            }

            @Override
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                AuthenticateContinue parsedMessage = null;
                try {
                    parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
                } catch (InvalidProtocolBufferException e) {
                    parsedMessage = (AuthenticateContinue)e.getUnfinishedMessage();
                    throw e;
                } finally {
                    if (parsedMessage != null) {
                        this.mergeFrom(parsedMessage);
                    }
                }
                return this;
            }

            @Override
            public boolean hasAuthData() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override
            public ByteString getAuthData() {
                return this.authData_;
            }

            public Builder setAuthData(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 1;
                this.authData_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearAuthData() {
                this.bitField0_ &= 0xFFFFFFFE;
                this.authData_ = AuthenticateContinue.getDefaultInstance().getAuthData();
                this.onChanged();
                return this;
            }
        }
    }

    public static interface AuthenticateContinueOrBuilder
    extends MessageOrBuilder {
        public boolean hasAuthData();

        public ByteString getAuthData();
    }

    public static final class AuthenticateStart
    extends GeneratedMessage
    implements AuthenticateStartOrBuilder {
        private static final AuthenticateStart defaultInstance;
        private final UnknownFieldSet unknownFields;
        public static Parser<AuthenticateStart> PARSER;
        private int bitField0_;
        public static final int MECH_NAME_FIELD_NUMBER = 1;
        private Object mechName_;
        public static final int AUTH_DATA_FIELD_NUMBER = 2;
        private ByteString authData_;
        public static final int INITIAL_RESPONSE_FIELD_NUMBER = 3;
        private ByteString initialResponse_;
        private byte memoizedIsInitialized = (byte)-1;
        private int memoizedSerializedSize = -1;
        private static final long serialVersionUID = 0L;

        private AuthenticateStart(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.unknownFields = builder.getUnknownFields();
        }

        private AuthenticateStart(boolean noInit) {
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static AuthenticateStart getDefaultInstance() {
            return defaultInstance;
        }

        @Override
        public AuthenticateStart getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private AuthenticateStart(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this.initFields();
            boolean mutable_bitField0_ = false;
            UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
            try {
                boolean done = false;
                block12: while (!done) {
                    int tag = input.readTag();
                    switch (tag) {
                        case 0: {
                            done = true;
                            continue block12;
                        }
                        default: {
                            if (this.parseUnknownField(input, unknownFields, extensionRegistry, tag)) continue block12;
                            done = true;
                            continue block12;
                        }
                        case 10: {
                            ByteString bs = input.readBytes();
                            this.bitField0_ |= 1;
                            this.mechName_ = bs;
                            continue block12;
                        }
                        case 18: {
                            this.bitField0_ |= 2;
                            this.authData_ = input.readBytes();
                            continue block12;
                        }
                        case 26: 
                    }
                    this.bitField0_ |= 4;
                    this.initialResponse_ = input.readBytes();
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
            return internal_static_Mysqlx_Session_AuthenticateStart_descriptor;
        }

        @Override
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return internal_static_Mysqlx_Session_AuthenticateStart_fieldAccessorTable.ensureFieldAccessorsInitialized(AuthenticateStart.class, Builder.class);
        }

        public Parser<AuthenticateStart> getParserForType() {
            return PARSER;
        }

        @Override
        public boolean hasMechName() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override
        public String getMechName() {
            Object ref = this.mechName_;
            if (ref instanceof String) {
                return (String)ref;
            }
            ByteString bs = (ByteString)ref;
            String s2 = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.mechName_ = s2;
            }
            return s2;
        }

        @Override
        public ByteString getMechNameBytes() {
            Object ref = this.mechName_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.mechName_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        @Override
        public boolean hasAuthData() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override
        public ByteString getAuthData() {
            return this.authData_;
        }

        @Override
        public boolean hasInitialResponse() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override
        public ByteString getInitialResponse() {
            return this.initialResponse_;
        }

        private void initFields() {
            this.mechName_ = "";
            this.authData_ = ByteString.EMPTY;
            this.initialResponse_ = ByteString.EMPTY;
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
            if (!this.hasMechName()) {
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
                output.writeBytes(1, this.getMechNameBytes());
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeBytes(2, this.authData_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeBytes(3, this.initialResponse_);
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
                size += CodedOutputStream.computeBytesSize(1, this.getMechNameBytes());
            }
            if ((this.bitField0_ & 2) == 2) {
                size += CodedOutputStream.computeBytesSize(2, this.authData_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size += CodedOutputStream.computeBytesSize(3, this.initialResponse_);
            }
            this.memoizedSerializedSize = size += this.getUnknownFields().getSerializedSize();
            return size;
        }

        @Override
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static AuthenticateStart parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static AuthenticateStart parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static AuthenticateStart parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static AuthenticateStart parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static AuthenticateStart parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static AuthenticateStart parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static AuthenticateStart parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static AuthenticateStart parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static AuthenticateStart parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static AuthenticateStart parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override
        public Builder newBuilderForType() {
            return AuthenticateStart.newBuilder();
        }

        public static Builder newBuilder(AuthenticateStart prototype) {
            return AuthenticateStart.newBuilder().mergeFrom(prototype);
        }

        @Override
        public Builder toBuilder() {
            return AuthenticateStart.newBuilder(this);
        }

        @Override
        protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        static {
            PARSER = new AbstractParser<AuthenticateStart>(){

                @Override
                public AuthenticateStart parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                    return new AuthenticateStart(input, extensionRegistry);
                }
            };
            defaultInstance = new AuthenticateStart(true);
            defaultInstance.initFields();
        }

        public static final class Builder
        extends GeneratedMessage.Builder<Builder>
        implements AuthenticateStartOrBuilder {
            private int bitField0_;
            private Object mechName_ = "";
            private ByteString authData_ = ByteString.EMPTY;
            private ByteString initialResponse_ = ByteString.EMPTY;

            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_Mysqlx_Session_AuthenticateStart_descriptor;
            }

            @Override
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_Mysqlx_Session_AuthenticateStart_fieldAccessorTable.ensureFieldAccessorsInitialized(AuthenticateStart.class, Builder.class);
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
                this.mechName_ = "";
                this.bitField0_ &= 0xFFFFFFFE;
                this.authData_ = ByteString.EMPTY;
                this.bitField0_ &= 0xFFFFFFFD;
                this.initialResponse_ = ByteString.EMPTY;
                this.bitField0_ &= 0xFFFFFFFB;
                return this;
            }

            @Override
            public Builder clone() {
                return Builder.create().mergeFrom(this.buildPartial());
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return internal_static_Mysqlx_Session_AuthenticateStart_descriptor;
            }

            @Override
            public AuthenticateStart getDefaultInstanceForType() {
                return AuthenticateStart.getDefaultInstance();
            }

            @Override
            public AuthenticateStart build() {
                AuthenticateStart result = this.buildPartial();
                if (!result.isInitialized()) {
                    throw Builder.newUninitializedMessageException(result);
                }
                return result;
            }

            @Override
            public AuthenticateStart buildPartial() {
                AuthenticateStart result = new AuthenticateStart(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ |= 1;
                }
                result.mechName_ = this.mechName_;
                if ((from_bitField0_ & 2) == 2) {
                    to_bitField0_ |= 2;
                }
                result.authData_ = this.authData_;
                if ((from_bitField0_ & 4) == 4) {
                    to_bitField0_ |= 4;
                }
                result.initialResponse_ = this.initialResponse_;
                result.bitField0_ = to_bitField0_;
                this.onBuilt();
                return result;
            }

            @Override
            public Builder mergeFrom(Message other) {
                if (other instanceof AuthenticateStart) {
                    return this.mergeFrom((AuthenticateStart)other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(AuthenticateStart other) {
                if (other == AuthenticateStart.getDefaultInstance()) {
                    return this;
                }
                if (other.hasMechName()) {
                    this.bitField0_ |= 1;
                    this.mechName_ = other.mechName_;
                    this.onChanged();
                }
                if (other.hasAuthData()) {
                    this.setAuthData(other.getAuthData());
                }
                if (other.hasInitialResponse()) {
                    this.setInitialResponse(other.getInitialResponse());
                }
                this.mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            @Override
            public final boolean isInitialized() {
                return this.hasMechName();
            }

            @Override
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                AuthenticateStart parsedMessage = null;
                try {
                    parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
                } catch (InvalidProtocolBufferException e) {
                    parsedMessage = (AuthenticateStart)e.getUnfinishedMessage();
                    throw e;
                } finally {
                    if (parsedMessage != null) {
                        this.mergeFrom(parsedMessage);
                    }
                }
                return this;
            }

            @Override
            public boolean hasMechName() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override
            public String getMechName() {
                Object ref = this.mechName_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString)ref;
                    String s2 = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.mechName_ = s2;
                    }
                    return s2;
                }
                return (String)ref;
            }

            @Override
            public ByteString getMechNameBytes() {
                Object ref = this.mechName_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String)ref);
                    this.mechName_ = b;
                    return b;
                }
                return (ByteString)ref;
            }

            public Builder setMechName(String value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 1;
                this.mechName_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearMechName() {
                this.bitField0_ &= 0xFFFFFFFE;
                this.mechName_ = AuthenticateStart.getDefaultInstance().getMechName();
                this.onChanged();
                return this;
            }

            public Builder setMechNameBytes(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 1;
                this.mechName_ = value;
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasAuthData() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override
            public ByteString getAuthData() {
                return this.authData_;
            }

            public Builder setAuthData(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 2;
                this.authData_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearAuthData() {
                this.bitField0_ &= 0xFFFFFFFD;
                this.authData_ = AuthenticateStart.getDefaultInstance().getAuthData();
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasInitialResponse() {
                return (this.bitField0_ & 4) == 4;
            }

            @Override
            public ByteString getInitialResponse() {
                return this.initialResponse_;
            }

            public Builder setInitialResponse(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 4;
                this.initialResponse_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearInitialResponse() {
                this.bitField0_ &= 0xFFFFFFFB;
                this.initialResponse_ = AuthenticateStart.getDefaultInstance().getInitialResponse();
                this.onChanged();
                return this;
            }
        }
    }

    public static interface AuthenticateStartOrBuilder
    extends MessageOrBuilder {
        public boolean hasMechName();

        public String getMechName();

        public ByteString getMechNameBytes();

        public boolean hasAuthData();

        public ByteString getAuthData();

        public boolean hasInitialResponse();

        public ByteString getInitialResponse();
    }
}

