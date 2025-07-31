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
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.RepeatedFieldBuilder;
import com.google.protobuf.SingleFieldBuilder;
import com.google.protobuf.UnknownFieldSet;
import com.mysql.cj.x.protobuf.Mysqlx;
import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectStreamException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class MysqlxConnection {
    private static final Descriptors.Descriptor internal_static_Mysqlx_Connection_Capability_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_Mysqlx_Connection_Capability_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_Mysqlx_Connection_Capabilities_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_Mysqlx_Connection_Capabilities_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_Mysqlx_Connection_CapabilitiesGet_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_Mysqlx_Connection_CapabilitiesGet_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_Mysqlx_Connection_CapabilitiesSet_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_Mysqlx_Connection_CapabilitiesSet_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_Mysqlx_Connection_Close_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_Mysqlx_Connection_Close_fieldAccessorTable;
    private static Descriptors.FileDescriptor descriptor;

    private MysqlxConnection() {
    }

    public static void registerAllExtensions(ExtensionRegistry registry) {
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        String[] descriptorData = new String[]{"\n\u0017mysqlx_connection.proto\u0012\u0011Mysqlx.Connection\u001a\u0016mysqlx_datatypes.proto\u001a\fmysqlx.proto\"@\n\nCapability\u0012\f\n\u0004name\u0018\u0001 \u0002(\t\u0012$\n\u0005value\u0018\u0002 \u0002(\u000b2\u0015.Mysqlx.Datatypes.Any\"I\n\fCapabilities\u00123\n\fcapabilities\u0018\u0001 \u0003(\u000b2\u001d.Mysqlx.Connection.Capability:\u0004\u0090\u00ea0\u0002\"\u0017\n\u000fCapabilitiesGet:\u0004\u0088\u00ea0\u0001\"N\n\u000fCapabilitiesSet\u00125\n\fcapabilities\u0018\u0001 \u0002(\u000b2\u001f.Mysqlx.Connection.Capabilities:\u0004\u0088\u00ea0\u0002\"\r\n\u0005Close:\u0004\u0088\u00ea0\u0003B\u0019\n\u0017com.mysql.cj.x.protobuf"};
        Descriptors.FileDescriptor.InternalDescriptorAssigner assigner = new Descriptors.FileDescriptor.InternalDescriptorAssigner(){

            @Override
            public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor root) {
                descriptor = root;
                return null;
            }
        };
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[]{MysqlxDatatypes.getDescriptor(), Mysqlx.getDescriptor()}, assigner);
        internal_static_Mysqlx_Connection_Capability_descriptor = MysqlxConnection.getDescriptor().getMessageTypes().get(0);
        internal_static_Mysqlx_Connection_Capability_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_Mysqlx_Connection_Capability_descriptor, new String[]{"Name", "Value"});
        internal_static_Mysqlx_Connection_Capabilities_descriptor = MysqlxConnection.getDescriptor().getMessageTypes().get(1);
        internal_static_Mysqlx_Connection_Capabilities_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_Mysqlx_Connection_Capabilities_descriptor, new String[]{"Capabilities"});
        internal_static_Mysqlx_Connection_CapabilitiesGet_descriptor = MysqlxConnection.getDescriptor().getMessageTypes().get(2);
        internal_static_Mysqlx_Connection_CapabilitiesGet_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_Mysqlx_Connection_CapabilitiesGet_descriptor, new String[0]);
        internal_static_Mysqlx_Connection_CapabilitiesSet_descriptor = MysqlxConnection.getDescriptor().getMessageTypes().get(3);
        internal_static_Mysqlx_Connection_CapabilitiesSet_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_Mysqlx_Connection_CapabilitiesSet_descriptor, new String[]{"Capabilities"});
        internal_static_Mysqlx_Connection_Close_descriptor = MysqlxConnection.getDescriptor().getMessageTypes().get(4);
        internal_static_Mysqlx_Connection_Close_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_Mysqlx_Connection_Close_descriptor, new String[0]);
        ExtensionRegistry registry = ExtensionRegistry.newInstance();
        registry.add(Mysqlx.serverMessageId);
        registry.add(Mysqlx.clientMessageId);
        registry.add(Mysqlx.clientMessageId);
        registry.add(Mysqlx.clientMessageId);
        Descriptors.FileDescriptor.internalUpdateFileDescriptor(descriptor, registry);
        MysqlxDatatypes.getDescriptor();
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
            return internal_static_Mysqlx_Connection_Close_descriptor;
        }

        @Override
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return internal_static_Mysqlx_Connection_Close_fieldAccessorTable.ensureFieldAccessorsInitialized(Close.class, Builder.class);
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
                return internal_static_Mysqlx_Connection_Close_descriptor;
            }

            @Override
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_Mysqlx_Connection_Close_fieldAccessorTable.ensureFieldAccessorsInitialized(Close.class, Builder.class);
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
                return internal_static_Mysqlx_Connection_Close_descriptor;
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

    public static final class CapabilitiesSet
    extends GeneratedMessage
    implements CapabilitiesSetOrBuilder {
        private static final CapabilitiesSet defaultInstance;
        private final UnknownFieldSet unknownFields;
        public static Parser<CapabilitiesSet> PARSER;
        private int bitField0_;
        public static final int CAPABILITIES_FIELD_NUMBER = 1;
        private Capabilities capabilities_;
        private byte memoizedIsInitialized = (byte)-1;
        private int memoizedSerializedSize = -1;
        private static final long serialVersionUID = 0L;

        private CapabilitiesSet(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.unknownFields = builder.getUnknownFields();
        }

        private CapabilitiesSet(boolean noInit) {
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static CapabilitiesSet getDefaultInstance() {
            return defaultInstance;
        }

        @Override
        public CapabilitiesSet getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private CapabilitiesSet(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
                    Capabilities.Builder subBuilder = null;
                    if ((this.bitField0_ & 1) == 1) {
                        subBuilder = this.capabilities_.toBuilder();
                    }
                    this.capabilities_ = input.readMessage(Capabilities.PARSER, extensionRegistry);
                    if (subBuilder != null) {
                        subBuilder.mergeFrom(this.capabilities_);
                        this.capabilities_ = subBuilder.buildPartial();
                    }
                    this.bitField0_ |= 1;
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
            return internal_static_Mysqlx_Connection_CapabilitiesSet_descriptor;
        }

        @Override
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return internal_static_Mysqlx_Connection_CapabilitiesSet_fieldAccessorTable.ensureFieldAccessorsInitialized(CapabilitiesSet.class, Builder.class);
        }

        public Parser<CapabilitiesSet> getParserForType() {
            return PARSER;
        }

        @Override
        public boolean hasCapabilities() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override
        public Capabilities getCapabilities() {
            return this.capabilities_;
        }

        @Override
        public CapabilitiesOrBuilder getCapabilitiesOrBuilder() {
            return this.capabilities_;
        }

        private void initFields() {
            this.capabilities_ = Capabilities.getDefaultInstance();
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
            if (!this.hasCapabilities()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            if (!this.getCapabilities().isInitialized()) {
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
                output.writeMessage(1, this.capabilities_);
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
                size += CodedOutputStream.computeMessageSize(1, this.capabilities_);
            }
            this.memoizedSerializedSize = size += this.getUnknownFields().getSerializedSize();
            return size;
        }

        @Override
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static CapabilitiesSet parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static CapabilitiesSet parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static CapabilitiesSet parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static CapabilitiesSet parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static CapabilitiesSet parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static CapabilitiesSet parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static CapabilitiesSet parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static CapabilitiesSet parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static CapabilitiesSet parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static CapabilitiesSet parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override
        public Builder newBuilderForType() {
            return CapabilitiesSet.newBuilder();
        }

        public static Builder newBuilder(CapabilitiesSet prototype) {
            return CapabilitiesSet.newBuilder().mergeFrom(prototype);
        }

        @Override
        public Builder toBuilder() {
            return CapabilitiesSet.newBuilder(this);
        }

        @Override
        protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        static {
            PARSER = new AbstractParser<CapabilitiesSet>(){

                @Override
                public CapabilitiesSet parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                    return new CapabilitiesSet(input, extensionRegistry);
                }
            };
            defaultInstance = new CapabilitiesSet(true);
            defaultInstance.initFields();
        }

        public static final class Builder
        extends GeneratedMessage.Builder<Builder>
        implements CapabilitiesSetOrBuilder {
            private int bitField0_;
            private Capabilities capabilities_ = Capabilities.getDefaultInstance();
            private SingleFieldBuilder<Capabilities, Capabilities.Builder, CapabilitiesOrBuilder> capabilitiesBuilder_;

            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_Mysqlx_Connection_CapabilitiesSet_descriptor;
            }

            @Override
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_Mysqlx_Connection_CapabilitiesSet_fieldAccessorTable.ensureFieldAccessorsInitialized(CapabilitiesSet.class, Builder.class);
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
                    this.getCapabilitiesFieldBuilder();
                }
            }

            private static Builder create() {
                return new Builder();
            }

            @Override
            public Builder clear() {
                super.clear();
                if (this.capabilitiesBuilder_ == null) {
                    this.capabilities_ = Capabilities.getDefaultInstance();
                } else {
                    this.capabilitiesBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFFFE;
                return this;
            }

            @Override
            public Builder clone() {
                return Builder.create().mergeFrom(this.buildPartial());
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return internal_static_Mysqlx_Connection_CapabilitiesSet_descriptor;
            }

            @Override
            public CapabilitiesSet getDefaultInstanceForType() {
                return CapabilitiesSet.getDefaultInstance();
            }

            @Override
            public CapabilitiesSet build() {
                CapabilitiesSet result = this.buildPartial();
                if (!result.isInitialized()) {
                    throw Builder.newUninitializedMessageException(result);
                }
                return result;
            }

            @Override
            public CapabilitiesSet buildPartial() {
                CapabilitiesSet result = new CapabilitiesSet(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ |= 1;
                }
                if (this.capabilitiesBuilder_ == null) {
                    result.capabilities_ = this.capabilities_;
                } else {
                    result.capabilities_ = this.capabilitiesBuilder_.build();
                }
                result.bitField0_ = to_bitField0_;
                this.onBuilt();
                return result;
            }

            @Override
            public Builder mergeFrom(Message other) {
                if (other instanceof CapabilitiesSet) {
                    return this.mergeFrom((CapabilitiesSet)other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(CapabilitiesSet other) {
                if (other == CapabilitiesSet.getDefaultInstance()) {
                    return this;
                }
                if (other.hasCapabilities()) {
                    this.mergeCapabilities(other.getCapabilities());
                }
                this.mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            @Override
            public final boolean isInitialized() {
                if (!this.hasCapabilities()) {
                    return false;
                }
                return this.getCapabilities().isInitialized();
            }

            @Override
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                CapabilitiesSet parsedMessage = null;
                try {
                    parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
                } catch (InvalidProtocolBufferException e) {
                    parsedMessage = (CapabilitiesSet)e.getUnfinishedMessage();
                    throw e;
                } finally {
                    if (parsedMessage != null) {
                        this.mergeFrom(parsedMessage);
                    }
                }
                return this;
            }

            @Override
            public boolean hasCapabilities() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override
            public Capabilities getCapabilities() {
                if (this.capabilitiesBuilder_ == null) {
                    return this.capabilities_;
                }
                return this.capabilitiesBuilder_.getMessage();
            }

            public Builder setCapabilities(Capabilities value) {
                if (this.capabilitiesBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.capabilities_ = value;
                    this.onChanged();
                } else {
                    this.capabilitiesBuilder_.setMessage(value);
                }
                this.bitField0_ |= 1;
                return this;
            }

            public Builder setCapabilities(Capabilities.Builder builderForValue) {
                if (this.capabilitiesBuilder_ == null) {
                    this.capabilities_ = builderForValue.build();
                    this.onChanged();
                } else {
                    this.capabilitiesBuilder_.setMessage(builderForValue.build());
                }
                this.bitField0_ |= 1;
                return this;
            }

            public Builder mergeCapabilities(Capabilities value) {
                if (this.capabilitiesBuilder_ == null) {
                    this.capabilities_ = (this.bitField0_ & 1) == 1 && this.capabilities_ != Capabilities.getDefaultInstance() ? Capabilities.newBuilder(this.capabilities_).mergeFrom(value).buildPartial() : value;
                    this.onChanged();
                } else {
                    this.capabilitiesBuilder_.mergeFrom(value);
                }
                this.bitField0_ |= 1;
                return this;
            }

            public Builder clearCapabilities() {
                if (this.capabilitiesBuilder_ == null) {
                    this.capabilities_ = Capabilities.getDefaultInstance();
                    this.onChanged();
                } else {
                    this.capabilitiesBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFFFE;
                return this;
            }

            public Capabilities.Builder getCapabilitiesBuilder() {
                this.bitField0_ |= 1;
                this.onChanged();
                return this.getCapabilitiesFieldBuilder().getBuilder();
            }

            @Override
            public CapabilitiesOrBuilder getCapabilitiesOrBuilder() {
                if (this.capabilitiesBuilder_ != null) {
                    return this.capabilitiesBuilder_.getMessageOrBuilder();
                }
                return this.capabilities_;
            }

            private SingleFieldBuilder<Capabilities, Capabilities.Builder, CapabilitiesOrBuilder> getCapabilitiesFieldBuilder() {
                if (this.capabilitiesBuilder_ == null) {
                    this.capabilitiesBuilder_ = new SingleFieldBuilder(this.getCapabilities(), this.getParentForChildren(), this.isClean());
                    this.capabilities_ = null;
                }
                return this.capabilitiesBuilder_;
            }
        }
    }

    public static interface CapabilitiesSetOrBuilder
    extends MessageOrBuilder {
        public boolean hasCapabilities();

        public Capabilities getCapabilities();

        public CapabilitiesOrBuilder getCapabilitiesOrBuilder();
    }

    public static final class CapabilitiesGet
    extends GeneratedMessage
    implements CapabilitiesGetOrBuilder {
        private static final CapabilitiesGet defaultInstance;
        private final UnknownFieldSet unknownFields;
        public static Parser<CapabilitiesGet> PARSER;
        private byte memoizedIsInitialized = (byte)-1;
        private int memoizedSerializedSize = -1;
        private static final long serialVersionUID = 0L;

        private CapabilitiesGet(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.unknownFields = builder.getUnknownFields();
        }

        private CapabilitiesGet(boolean noInit) {
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static CapabilitiesGet getDefaultInstance() {
            return defaultInstance;
        }

        @Override
        public CapabilitiesGet getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private CapabilitiesGet(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
            return internal_static_Mysqlx_Connection_CapabilitiesGet_descriptor;
        }

        @Override
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return internal_static_Mysqlx_Connection_CapabilitiesGet_fieldAccessorTable.ensureFieldAccessorsInitialized(CapabilitiesGet.class, Builder.class);
        }

        public Parser<CapabilitiesGet> getParserForType() {
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

        public static CapabilitiesGet parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static CapabilitiesGet parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static CapabilitiesGet parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static CapabilitiesGet parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static CapabilitiesGet parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static CapabilitiesGet parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static CapabilitiesGet parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static CapabilitiesGet parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static CapabilitiesGet parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static CapabilitiesGet parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override
        public Builder newBuilderForType() {
            return CapabilitiesGet.newBuilder();
        }

        public static Builder newBuilder(CapabilitiesGet prototype) {
            return CapabilitiesGet.newBuilder().mergeFrom(prototype);
        }

        @Override
        public Builder toBuilder() {
            return CapabilitiesGet.newBuilder(this);
        }

        @Override
        protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        static {
            PARSER = new AbstractParser<CapabilitiesGet>(){

                @Override
                public CapabilitiesGet parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                    return new CapabilitiesGet(input, extensionRegistry);
                }
            };
            defaultInstance = new CapabilitiesGet(true);
            defaultInstance.initFields();
        }

        public static final class Builder
        extends GeneratedMessage.Builder<Builder>
        implements CapabilitiesGetOrBuilder {
            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_Mysqlx_Connection_CapabilitiesGet_descriptor;
            }

            @Override
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_Mysqlx_Connection_CapabilitiesGet_fieldAccessorTable.ensureFieldAccessorsInitialized(CapabilitiesGet.class, Builder.class);
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
                return internal_static_Mysqlx_Connection_CapabilitiesGet_descriptor;
            }

            @Override
            public CapabilitiesGet getDefaultInstanceForType() {
                return CapabilitiesGet.getDefaultInstance();
            }

            @Override
            public CapabilitiesGet build() {
                CapabilitiesGet result = this.buildPartial();
                if (!result.isInitialized()) {
                    throw Builder.newUninitializedMessageException(result);
                }
                return result;
            }

            @Override
            public CapabilitiesGet buildPartial() {
                CapabilitiesGet result = new CapabilitiesGet(this);
                this.onBuilt();
                return result;
            }

            @Override
            public Builder mergeFrom(Message other) {
                if (other instanceof CapabilitiesGet) {
                    return this.mergeFrom((CapabilitiesGet)other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(CapabilitiesGet other) {
                if (other == CapabilitiesGet.getDefaultInstance()) {
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
                CapabilitiesGet parsedMessage = null;
                try {
                    parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
                } catch (InvalidProtocolBufferException e) {
                    parsedMessage = (CapabilitiesGet)e.getUnfinishedMessage();
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

    public static interface CapabilitiesGetOrBuilder
    extends MessageOrBuilder {
    }

    public static final class Capabilities
    extends GeneratedMessage
    implements CapabilitiesOrBuilder {
        private static final Capabilities defaultInstance;
        private final UnknownFieldSet unknownFields;
        public static Parser<Capabilities> PARSER;
        public static final int CAPABILITIES_FIELD_NUMBER = 1;
        private List<Capability> capabilities_;
        private byte memoizedIsInitialized = (byte)-1;
        private int memoizedSerializedSize = -1;
        private static final long serialVersionUID = 0L;

        private Capabilities(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.unknownFields = builder.getUnknownFields();
        }

        private Capabilities(boolean noInit) {
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static Capabilities getDefaultInstance() {
            return defaultInstance;
        }

        @Override
        public Capabilities getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private Capabilities(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
                        this.capabilities_ = new ArrayList<Capability>();
                        mutable_bitField0_ |= true;
                    }
                    this.capabilities_.add(input.readMessage(Capability.PARSER, extensionRegistry));
                }
            } catch (InvalidProtocolBufferException e) {
                throw e.setUnfinishedMessage(this);
            } catch (IOException e) {
                throw new InvalidProtocolBufferException(e.getMessage()).setUnfinishedMessage(this);
            } finally {
                if (mutable_bitField0_ & true) {
                    this.capabilities_ = Collections.unmodifiableList(this.capabilities_);
                }
                this.unknownFields = unknownFields.build();
                this.makeExtensionsImmutable();
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return internal_static_Mysqlx_Connection_Capabilities_descriptor;
        }

        @Override
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return internal_static_Mysqlx_Connection_Capabilities_fieldAccessorTable.ensureFieldAccessorsInitialized(Capabilities.class, Builder.class);
        }

        public Parser<Capabilities> getParserForType() {
            return PARSER;
        }

        @Override
        public List<Capability> getCapabilitiesList() {
            return this.capabilities_;
        }

        @Override
        public List<? extends CapabilityOrBuilder> getCapabilitiesOrBuilderList() {
            return this.capabilities_;
        }

        @Override
        public int getCapabilitiesCount() {
            return this.capabilities_.size();
        }

        @Override
        public Capability getCapabilities(int index) {
            return this.capabilities_.get(index);
        }

        @Override
        public CapabilityOrBuilder getCapabilitiesOrBuilder(int index) {
            return this.capabilities_.get(index);
        }

        private void initFields() {
            this.capabilities_ = Collections.emptyList();
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
            for (int i = 0; i < this.getCapabilitiesCount(); ++i) {
                if (this.getCapabilities(i).isInitialized()) continue;
                this.memoizedIsInitialized = 0;
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        @Override
        public void writeTo(CodedOutputStream output) throws IOException {
            this.getSerializedSize();
            for (int i = 0; i < this.capabilities_.size(); ++i) {
                output.writeMessage(1, this.capabilities_.get(i));
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
            for (int i = 0; i < this.capabilities_.size(); ++i) {
                size += CodedOutputStream.computeMessageSize(1, this.capabilities_.get(i));
            }
            this.memoizedSerializedSize = size += this.getUnknownFields().getSerializedSize();
            return size;
        }

        @Override
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static Capabilities parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static Capabilities parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static Capabilities parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static Capabilities parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static Capabilities parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static Capabilities parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Capabilities parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static Capabilities parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static Capabilities parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static Capabilities parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override
        public Builder newBuilderForType() {
            return Capabilities.newBuilder();
        }

        public static Builder newBuilder(Capabilities prototype) {
            return Capabilities.newBuilder().mergeFrom(prototype);
        }

        @Override
        public Builder toBuilder() {
            return Capabilities.newBuilder(this);
        }

        @Override
        protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        static {
            PARSER = new AbstractParser<Capabilities>(){

                @Override
                public Capabilities parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                    return new Capabilities(input, extensionRegistry);
                }
            };
            defaultInstance = new Capabilities(true);
            defaultInstance.initFields();
        }

        public static final class Builder
        extends GeneratedMessage.Builder<Builder>
        implements CapabilitiesOrBuilder {
            private int bitField0_;
            private List<Capability> capabilities_ = Collections.emptyList();
            private RepeatedFieldBuilder<Capability, Capability.Builder, CapabilityOrBuilder> capabilitiesBuilder_;

            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_Mysqlx_Connection_Capabilities_descriptor;
            }

            @Override
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_Mysqlx_Connection_Capabilities_fieldAccessorTable.ensureFieldAccessorsInitialized(Capabilities.class, Builder.class);
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
                    this.getCapabilitiesFieldBuilder();
                }
            }

            private static Builder create() {
                return new Builder();
            }

            @Override
            public Builder clear() {
                super.clear();
                if (this.capabilitiesBuilder_ == null) {
                    this.capabilities_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFFFFE;
                } else {
                    this.capabilitiesBuilder_.clear();
                }
                return this;
            }

            @Override
            public Builder clone() {
                return Builder.create().mergeFrom(this.buildPartial());
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return internal_static_Mysqlx_Connection_Capabilities_descriptor;
            }

            @Override
            public Capabilities getDefaultInstanceForType() {
                return Capabilities.getDefaultInstance();
            }

            @Override
            public Capabilities build() {
                Capabilities result = this.buildPartial();
                if (!result.isInitialized()) {
                    throw Builder.newUninitializedMessageException(result);
                }
                return result;
            }

            @Override
            public Capabilities buildPartial() {
                Capabilities result = new Capabilities(this);
                int from_bitField0_ = this.bitField0_;
                if (this.capabilitiesBuilder_ == null) {
                    if ((this.bitField0_ & 1) == 1) {
                        this.capabilities_ = Collections.unmodifiableList(this.capabilities_);
                        this.bitField0_ &= 0xFFFFFFFE;
                    }
                    result.capabilities_ = this.capabilities_;
                } else {
                    result.capabilities_ = this.capabilitiesBuilder_.build();
                }
                this.onBuilt();
                return result;
            }

            @Override
            public Builder mergeFrom(Message other) {
                if (other instanceof Capabilities) {
                    return this.mergeFrom((Capabilities)other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(Capabilities other) {
                if (other == Capabilities.getDefaultInstance()) {
                    return this;
                }
                if (this.capabilitiesBuilder_ == null) {
                    if (!other.capabilities_.isEmpty()) {
                        if (this.capabilities_.isEmpty()) {
                            this.capabilities_ = other.capabilities_;
                            this.bitField0_ &= 0xFFFFFFFE;
                        } else {
                            this.ensureCapabilitiesIsMutable();
                            this.capabilities_.addAll(other.capabilities_);
                        }
                        this.onChanged();
                    }
                } else if (!other.capabilities_.isEmpty()) {
                    if (this.capabilitiesBuilder_.isEmpty()) {
                        this.capabilitiesBuilder_.dispose();
                        this.capabilitiesBuilder_ = null;
                        this.capabilities_ = other.capabilities_;
                        this.bitField0_ &= 0xFFFFFFFE;
                        this.capabilitiesBuilder_ = alwaysUseFieldBuilders ? this.getCapabilitiesFieldBuilder() : null;
                    } else {
                        this.capabilitiesBuilder_.addAllMessages(other.capabilities_);
                    }
                }
                this.mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            @Override
            public final boolean isInitialized() {
                for (int i = 0; i < this.getCapabilitiesCount(); ++i) {
                    if (this.getCapabilities(i).isInitialized()) continue;
                    return false;
                }
                return true;
            }

            @Override
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                Capabilities parsedMessage = null;
                try {
                    parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
                } catch (InvalidProtocolBufferException e) {
                    parsedMessage = (Capabilities)e.getUnfinishedMessage();
                    throw e;
                } finally {
                    if (parsedMessage != null) {
                        this.mergeFrom(parsedMessage);
                    }
                }
                return this;
            }

            private void ensureCapabilitiesIsMutable() {
                if ((this.bitField0_ & 1) != 1) {
                    this.capabilities_ = new ArrayList<Capability>(this.capabilities_);
                    this.bitField0_ |= 1;
                }
            }

            @Override
            public List<Capability> getCapabilitiesList() {
                if (this.capabilitiesBuilder_ == null) {
                    return Collections.unmodifiableList(this.capabilities_);
                }
                return this.capabilitiesBuilder_.getMessageList();
            }

            @Override
            public int getCapabilitiesCount() {
                if (this.capabilitiesBuilder_ == null) {
                    return this.capabilities_.size();
                }
                return this.capabilitiesBuilder_.getCount();
            }

            @Override
            public Capability getCapabilities(int index) {
                if (this.capabilitiesBuilder_ == null) {
                    return this.capabilities_.get(index);
                }
                return this.capabilitiesBuilder_.getMessage(index);
            }

            public Builder setCapabilities(int index, Capability value) {
                if (this.capabilitiesBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureCapabilitiesIsMutable();
                    this.capabilities_.set(index, value);
                    this.onChanged();
                } else {
                    this.capabilitiesBuilder_.setMessage(index, value);
                }
                return this;
            }

            public Builder setCapabilities(int index, Capability.Builder builderForValue) {
                if (this.capabilitiesBuilder_ == null) {
                    this.ensureCapabilitiesIsMutable();
                    this.capabilities_.set(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.capabilitiesBuilder_.setMessage(index, builderForValue.build());
                }
                return this;
            }

            public Builder addCapabilities(Capability value) {
                if (this.capabilitiesBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureCapabilitiesIsMutable();
                    this.capabilities_.add(value);
                    this.onChanged();
                } else {
                    this.capabilitiesBuilder_.addMessage(value);
                }
                return this;
            }

            public Builder addCapabilities(int index, Capability value) {
                if (this.capabilitiesBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureCapabilitiesIsMutable();
                    this.capabilities_.add(index, value);
                    this.onChanged();
                } else {
                    this.capabilitiesBuilder_.addMessage(index, value);
                }
                return this;
            }

            public Builder addCapabilities(Capability.Builder builderForValue) {
                if (this.capabilitiesBuilder_ == null) {
                    this.ensureCapabilitiesIsMutable();
                    this.capabilities_.add(builderForValue.build());
                    this.onChanged();
                } else {
                    this.capabilitiesBuilder_.addMessage(builderForValue.build());
                }
                return this;
            }

            public Builder addCapabilities(int index, Capability.Builder builderForValue) {
                if (this.capabilitiesBuilder_ == null) {
                    this.ensureCapabilitiesIsMutable();
                    this.capabilities_.add(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.capabilitiesBuilder_.addMessage(index, builderForValue.build());
                }
                return this;
            }

            public Builder addAllCapabilities(Iterable<? extends Capability> values2) {
                if (this.capabilitiesBuilder_ == null) {
                    this.ensureCapabilitiesIsMutable();
                    AbstractMessageLite.Builder.addAll(values2, this.capabilities_);
                    this.onChanged();
                } else {
                    this.capabilitiesBuilder_.addAllMessages(values2);
                }
                return this;
            }

            public Builder clearCapabilities() {
                if (this.capabilitiesBuilder_ == null) {
                    this.capabilities_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFFFFE;
                    this.onChanged();
                } else {
                    this.capabilitiesBuilder_.clear();
                }
                return this;
            }

            public Builder removeCapabilities(int index) {
                if (this.capabilitiesBuilder_ == null) {
                    this.ensureCapabilitiesIsMutable();
                    this.capabilities_.remove(index);
                    this.onChanged();
                } else {
                    this.capabilitiesBuilder_.remove(index);
                }
                return this;
            }

            public Capability.Builder getCapabilitiesBuilder(int index) {
                return this.getCapabilitiesFieldBuilder().getBuilder(index);
            }

            @Override
            public CapabilityOrBuilder getCapabilitiesOrBuilder(int index) {
                if (this.capabilitiesBuilder_ == null) {
                    return this.capabilities_.get(index);
                }
                return this.capabilitiesBuilder_.getMessageOrBuilder(index);
            }

            @Override
            public List<? extends CapabilityOrBuilder> getCapabilitiesOrBuilderList() {
                if (this.capabilitiesBuilder_ != null) {
                    return this.capabilitiesBuilder_.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.capabilities_);
            }

            public Capability.Builder addCapabilitiesBuilder() {
                return this.getCapabilitiesFieldBuilder().addBuilder(Capability.getDefaultInstance());
            }

            public Capability.Builder addCapabilitiesBuilder(int index) {
                return this.getCapabilitiesFieldBuilder().addBuilder(index, Capability.getDefaultInstance());
            }

            public List<Capability.Builder> getCapabilitiesBuilderList() {
                return this.getCapabilitiesFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilder<Capability, Capability.Builder, CapabilityOrBuilder> getCapabilitiesFieldBuilder() {
                if (this.capabilitiesBuilder_ == null) {
                    this.capabilitiesBuilder_ = new RepeatedFieldBuilder(this.capabilities_, (this.bitField0_ & 1) == 1, this.getParentForChildren(), this.isClean());
                    this.capabilities_ = null;
                }
                return this.capabilitiesBuilder_;
            }
        }
    }

    public static interface CapabilitiesOrBuilder
    extends MessageOrBuilder {
        public List<Capability> getCapabilitiesList();

        public Capability getCapabilities(int var1);

        public int getCapabilitiesCount();

        public List<? extends CapabilityOrBuilder> getCapabilitiesOrBuilderList();

        public CapabilityOrBuilder getCapabilitiesOrBuilder(int var1);
    }

    public static final class Capability
    extends GeneratedMessage
    implements CapabilityOrBuilder {
        private static final Capability defaultInstance;
        private final UnknownFieldSet unknownFields;
        public static Parser<Capability> PARSER;
        private int bitField0_;
        public static final int NAME_FIELD_NUMBER = 1;
        private Object name_;
        public static final int VALUE_FIELD_NUMBER = 2;
        private MysqlxDatatypes.Any value_;
        private byte memoizedIsInitialized = (byte)-1;
        private int memoizedSerializedSize = -1;
        private static final long serialVersionUID = 0L;

        private Capability(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.unknownFields = builder.getUnknownFields();
        }

        private Capability(boolean noInit) {
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static Capability getDefaultInstance() {
            return defaultInstance;
        }

        @Override
        public Capability getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private Capability(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
                            this.name_ = bs;
                            continue block11;
                        }
                        case 18: 
                    }
                    MysqlxDatatypes.Any.Builder subBuilder = null;
                    if ((this.bitField0_ & 2) == 2) {
                        subBuilder = this.value_.toBuilder();
                    }
                    this.value_ = input.readMessage(MysqlxDatatypes.Any.PARSER, extensionRegistry);
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
            return internal_static_Mysqlx_Connection_Capability_descriptor;
        }

        @Override
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return internal_static_Mysqlx_Connection_Capability_fieldAccessorTable.ensureFieldAccessorsInitialized(Capability.class, Builder.class);
        }

        public Parser<Capability> getParserForType() {
            return PARSER;
        }

        @Override
        public boolean hasName() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override
        public String getName() {
            Object ref = this.name_;
            if (ref instanceof String) {
                return (String)ref;
            }
            ByteString bs = (ByteString)ref;
            String s2 = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.name_ = s2;
            }
            return s2;
        }

        @Override
        public ByteString getNameBytes() {
            Object ref = this.name_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.name_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        @Override
        public boolean hasValue() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override
        public MysqlxDatatypes.Any getValue() {
            return this.value_;
        }

        @Override
        public MysqlxDatatypes.AnyOrBuilder getValueOrBuilder() {
            return this.value_;
        }

        private void initFields() {
            this.name_ = "";
            this.value_ = MysqlxDatatypes.Any.getDefaultInstance();
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
            if (!this.hasName()) {
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
                output.writeBytes(1, this.getNameBytes());
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
                size += CodedOutputStream.computeBytesSize(1, this.getNameBytes());
            }
            if ((this.bitField0_ & 2) == 2) {
                size += CodedOutputStream.computeMessageSize(2, this.value_);
            }
            this.memoizedSerializedSize = size += this.getUnknownFields().getSerializedSize();
            return size;
        }

        @Override
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static Capability parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static Capability parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static Capability parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static Capability parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static Capability parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static Capability parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Capability parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static Capability parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static Capability parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static Capability parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override
        public Builder newBuilderForType() {
            return Capability.newBuilder();
        }

        public static Builder newBuilder(Capability prototype) {
            return Capability.newBuilder().mergeFrom(prototype);
        }

        @Override
        public Builder toBuilder() {
            return Capability.newBuilder(this);
        }

        @Override
        protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        static {
            PARSER = new AbstractParser<Capability>(){

                @Override
                public Capability parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                    return new Capability(input, extensionRegistry);
                }
            };
            defaultInstance = new Capability(true);
            defaultInstance.initFields();
        }

        public static final class Builder
        extends GeneratedMessage.Builder<Builder>
        implements CapabilityOrBuilder {
            private int bitField0_;
            private Object name_ = "";
            private MysqlxDatatypes.Any value_ = MysqlxDatatypes.Any.getDefaultInstance();
            private SingleFieldBuilder<MysqlxDatatypes.Any, MysqlxDatatypes.Any.Builder, MysqlxDatatypes.AnyOrBuilder> valueBuilder_;

            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_Mysqlx_Connection_Capability_descriptor;
            }

            @Override
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_Mysqlx_Connection_Capability_fieldAccessorTable.ensureFieldAccessorsInitialized(Capability.class, Builder.class);
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
                this.name_ = "";
                this.bitField0_ &= 0xFFFFFFFE;
                if (this.valueBuilder_ == null) {
                    this.value_ = MysqlxDatatypes.Any.getDefaultInstance();
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
                return internal_static_Mysqlx_Connection_Capability_descriptor;
            }

            @Override
            public Capability getDefaultInstanceForType() {
                return Capability.getDefaultInstance();
            }

            @Override
            public Capability build() {
                Capability result = this.buildPartial();
                if (!result.isInitialized()) {
                    throw Builder.newUninitializedMessageException(result);
                }
                return result;
            }

            @Override
            public Capability buildPartial() {
                Capability result = new Capability(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ |= 1;
                }
                result.name_ = this.name_;
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
                if (other instanceof Capability) {
                    return this.mergeFrom((Capability)other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(Capability other) {
                if (other == Capability.getDefaultInstance()) {
                    return this;
                }
                if (other.hasName()) {
                    this.bitField0_ |= 1;
                    this.name_ = other.name_;
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
                if (!this.hasName()) {
                    return false;
                }
                if (!this.hasValue()) {
                    return false;
                }
                return this.getValue().isInitialized();
            }

            @Override
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                Capability parsedMessage = null;
                try {
                    parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
                } catch (InvalidProtocolBufferException e) {
                    parsedMessage = (Capability)e.getUnfinishedMessage();
                    throw e;
                } finally {
                    if (parsedMessage != null) {
                        this.mergeFrom(parsedMessage);
                    }
                }
                return this;
            }

            @Override
            public boolean hasName() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override
            public String getName() {
                Object ref = this.name_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString)ref;
                    String s2 = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.name_ = s2;
                    }
                    return s2;
                }
                return (String)ref;
            }

            @Override
            public ByteString getNameBytes() {
                Object ref = this.name_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String)ref);
                    this.name_ = b;
                    return b;
                }
                return (ByteString)ref;
            }

            public Builder setName(String value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 1;
                this.name_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearName() {
                this.bitField0_ &= 0xFFFFFFFE;
                this.name_ = Capability.getDefaultInstance().getName();
                this.onChanged();
                return this;
            }

            public Builder setNameBytes(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 1;
                this.name_ = value;
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasValue() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override
            public MysqlxDatatypes.Any getValue() {
                if (this.valueBuilder_ == null) {
                    return this.value_;
                }
                return this.valueBuilder_.getMessage();
            }

            public Builder setValue(MysqlxDatatypes.Any value) {
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

            public Builder setValue(MysqlxDatatypes.Any.Builder builderForValue) {
                if (this.valueBuilder_ == null) {
                    this.value_ = builderForValue.build();
                    this.onChanged();
                } else {
                    this.valueBuilder_.setMessage(builderForValue.build());
                }
                this.bitField0_ |= 2;
                return this;
            }

            public Builder mergeValue(MysqlxDatatypes.Any value) {
                if (this.valueBuilder_ == null) {
                    this.value_ = (this.bitField0_ & 2) == 2 && this.value_ != MysqlxDatatypes.Any.getDefaultInstance() ? MysqlxDatatypes.Any.newBuilder(this.value_).mergeFrom(value).buildPartial() : value;
                    this.onChanged();
                } else {
                    this.valueBuilder_.mergeFrom(value);
                }
                this.bitField0_ |= 2;
                return this;
            }

            public Builder clearValue() {
                if (this.valueBuilder_ == null) {
                    this.value_ = MysqlxDatatypes.Any.getDefaultInstance();
                    this.onChanged();
                } else {
                    this.valueBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFFFD;
                return this;
            }

            public MysqlxDatatypes.Any.Builder getValueBuilder() {
                this.bitField0_ |= 2;
                this.onChanged();
                return this.getValueFieldBuilder().getBuilder();
            }

            @Override
            public MysqlxDatatypes.AnyOrBuilder getValueOrBuilder() {
                if (this.valueBuilder_ != null) {
                    return this.valueBuilder_.getMessageOrBuilder();
                }
                return this.value_;
            }

            private SingleFieldBuilder<MysqlxDatatypes.Any, MysqlxDatatypes.Any.Builder, MysqlxDatatypes.AnyOrBuilder> getValueFieldBuilder() {
                if (this.valueBuilder_ == null) {
                    this.valueBuilder_ = new SingleFieldBuilder(this.getValue(), this.getParentForChildren(), this.isClean());
                    this.value_ = null;
                }
                return this.valueBuilder_;
            }
        }
    }

    public static interface CapabilityOrBuilder
    extends MessageOrBuilder {
        public boolean hasName();

        public String getName();

        public ByteString getNameBytes();

        public boolean hasValue();

        public MysqlxDatatypes.Any getValue();

        public MysqlxDatatypes.AnyOrBuilder getValueOrBuilder();
    }
}

