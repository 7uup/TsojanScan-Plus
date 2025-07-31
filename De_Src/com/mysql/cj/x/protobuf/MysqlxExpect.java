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
import com.google.protobuf.UnknownFieldSet;
import com.mysql.cj.x.protobuf.Mysqlx;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectStreamException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class MysqlxExpect {
    private static final Descriptors.Descriptor internal_static_Mysqlx_Expect_Open_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_Mysqlx_Expect_Open_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_Mysqlx_Expect_Open_Condition_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_Mysqlx_Expect_Open_Condition_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_Mysqlx_Expect_Close_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_Mysqlx_Expect_Close_fieldAccessorTable;
    private static Descriptors.FileDescriptor descriptor;

    private MysqlxExpect() {
    }

    public static void registerAllExtensions(ExtensionRegistry registry) {
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        String[] descriptorData = new String[]{"\n\u0013mysqlx_expect.proto\u0012\rMysqlx.Expect\u001a\fmysqlx.proto\"\u00d6\u0003\n\u0004Open\u0012B\n\u0002op\u0018\u0001 \u0001(\u000e2 .Mysqlx.Expect.Open.CtxOperation:\u0014EXPECT_CTX_COPY_PREV\u0012+\n\u0004cond\u0018\u0002 \u0003(\u000b2\u001d.Mysqlx.Expect.Open.Condition\u001a\u0096\u0002\n\tCondition\u0012\u0015\n\rcondition_key\u0018\u0001 \u0002(\r\u0012\u0017\n\u000fcondition_value\u0018\u0002 \u0001(\f\u0012K\n\u0002op\u0018\u0003 \u0001(\u000e20.Mysqlx.Expect.Open.Condition.ConditionOperation:\rEXPECT_OP_SET\"N\n\u0003Key\u0012\u0013\n\u000fEXPECT_NO_ERROR\u0010\u0001\u0012\u0016\n\u0012EXPECT_FIELD_EXIST\u0010\u0002\u0012\u001a\n\u0016EXPECT_DOCID_GENERATED\u0010\u0003\"<\n\u0012Condi", "tionOperation\u0012\u0011\n\rEXPECT_OP_SET\u0010\u0000\u0012\u0013\n\u000fEXPECT_OP_UNSET\u0010\u0001\">\n\fCtxOperation\u0012\u0018\n\u0014EXPECT_CTX_COPY_PREV\u0010\u0000\u0012\u0014\n\u0010EXPECT_CTX_EMPTY\u0010\u0001:\u0004\u0088\u00ea0\u0018\"\r\n\u0005Close:\u0004\u0088\u00ea0\u0019B\u0019\n\u0017com.mysql.cj.x.protobuf"};
        Descriptors.FileDescriptor.InternalDescriptorAssigner assigner = new Descriptors.FileDescriptor.InternalDescriptorAssigner(){

            @Override
            public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor root) {
                descriptor = root;
                return null;
            }
        };
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[]{Mysqlx.getDescriptor()}, assigner);
        internal_static_Mysqlx_Expect_Open_descriptor = MysqlxExpect.getDescriptor().getMessageTypes().get(0);
        internal_static_Mysqlx_Expect_Open_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_Mysqlx_Expect_Open_descriptor, new String[]{"Op", "Cond"});
        internal_static_Mysqlx_Expect_Open_Condition_descriptor = internal_static_Mysqlx_Expect_Open_descriptor.getNestedTypes().get(0);
        internal_static_Mysqlx_Expect_Open_Condition_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_Mysqlx_Expect_Open_Condition_descriptor, new String[]{"ConditionKey", "ConditionValue", "Op"});
        internal_static_Mysqlx_Expect_Close_descriptor = MysqlxExpect.getDescriptor().getMessageTypes().get(1);
        internal_static_Mysqlx_Expect_Close_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_Mysqlx_Expect_Close_descriptor, new String[0]);
        ExtensionRegistry registry = ExtensionRegistry.newInstance();
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
            return internal_static_Mysqlx_Expect_Close_descriptor;
        }

        @Override
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return internal_static_Mysqlx_Expect_Close_fieldAccessorTable.ensureFieldAccessorsInitialized(Close.class, Builder.class);
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
                return internal_static_Mysqlx_Expect_Close_descriptor;
            }

            @Override
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_Mysqlx_Expect_Close_fieldAccessorTable.ensureFieldAccessorsInitialized(Close.class, Builder.class);
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
                return internal_static_Mysqlx_Expect_Close_descriptor;
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

    public static final class Open
    extends GeneratedMessage
    implements OpenOrBuilder {
        private static final Open defaultInstance;
        private final UnknownFieldSet unknownFields;
        public static Parser<Open> PARSER;
        private int bitField0_;
        public static final int OP_FIELD_NUMBER = 1;
        private CtxOperation op_;
        public static final int COND_FIELD_NUMBER = 2;
        private List<Condition> cond_;
        private byte memoizedIsInitialized = (byte)-1;
        private int memoizedSerializedSize = -1;
        private static final long serialVersionUID = 0L;

        private Open(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.unknownFields = builder.getUnknownFields();
        }

        private Open(boolean noInit) {
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static Open getDefaultInstance() {
            return defaultInstance;
        }

        @Override
        public Open getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private Open(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this.initFields();
            int mutable_bitField0_ = 0;
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
                        case 8: {
                            int rawValue = input.readEnum();
                            CtxOperation value = CtxOperation.valueOf(rawValue);
                            if (value == null) {
                                unknownFields.mergeVarintField(1, rawValue);
                                continue block11;
                            }
                            this.bitField0_ |= 1;
                            this.op_ = value;
                            continue block11;
                        }
                        case 18: 
                    }
                    if ((mutable_bitField0_ & 2) != 2) {
                        this.cond_ = new ArrayList<Condition>();
                        mutable_bitField0_ |= 2;
                    }
                    this.cond_.add(input.readMessage(Condition.PARSER, extensionRegistry));
                }
            } catch (InvalidProtocolBufferException e) {
                throw e.setUnfinishedMessage(this);
            } catch (IOException e) {
                throw new InvalidProtocolBufferException(e.getMessage()).setUnfinishedMessage(this);
            } finally {
                if ((mutable_bitField0_ & 2) == 2) {
                    this.cond_ = Collections.unmodifiableList(this.cond_);
                }
                this.unknownFields = unknownFields.build();
                this.makeExtensionsImmutable();
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return internal_static_Mysqlx_Expect_Open_descriptor;
        }

        @Override
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return internal_static_Mysqlx_Expect_Open_fieldAccessorTable.ensureFieldAccessorsInitialized(Open.class, Builder.class);
        }

        public Parser<Open> getParserForType() {
            return PARSER;
        }

        @Override
        public boolean hasOp() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override
        public CtxOperation getOp() {
            return this.op_;
        }

        @Override
        public List<Condition> getCondList() {
            return this.cond_;
        }

        @Override
        public List<? extends ConditionOrBuilder> getCondOrBuilderList() {
            return this.cond_;
        }

        @Override
        public int getCondCount() {
            return this.cond_.size();
        }

        @Override
        public Condition getCond(int index) {
            return this.cond_.get(index);
        }

        @Override
        public ConditionOrBuilder getCondOrBuilder(int index) {
            return this.cond_.get(index);
        }

        private void initFields() {
            this.op_ = CtxOperation.EXPECT_CTX_COPY_PREV;
            this.cond_ = Collections.emptyList();
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
            for (int i = 0; i < this.getCondCount(); ++i) {
                if (this.getCond(i).isInitialized()) continue;
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
                output.writeEnum(1, this.op_.getNumber());
            }
            for (int i = 0; i < this.cond_.size(); ++i) {
                output.writeMessage(2, this.cond_.get(i));
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
                size += CodedOutputStream.computeEnumSize(1, this.op_.getNumber());
            }
            for (int i = 0; i < this.cond_.size(); ++i) {
                size += CodedOutputStream.computeMessageSize(2, this.cond_.get(i));
            }
            this.memoizedSerializedSize = size += this.getUnknownFields().getSerializedSize();
            return size;
        }

        @Override
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static Open parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static Open parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static Open parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static Open parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static Open parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static Open parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Open parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static Open parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static Open parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static Open parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override
        public Builder newBuilderForType() {
            return Open.newBuilder();
        }

        public static Builder newBuilder(Open prototype) {
            return Open.newBuilder().mergeFrom(prototype);
        }

        @Override
        public Builder toBuilder() {
            return Open.newBuilder(this);
        }

        @Override
        protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        static {
            PARSER = new AbstractParser<Open>(){

                @Override
                public Open parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                    return new Open(input, extensionRegistry);
                }
            };
            defaultInstance = new Open(true);
            defaultInstance.initFields();
        }

        public static final class Builder
        extends GeneratedMessage.Builder<Builder>
        implements OpenOrBuilder {
            private int bitField0_;
            private CtxOperation op_ = CtxOperation.EXPECT_CTX_COPY_PREV;
            private List<Condition> cond_ = Collections.emptyList();
            private RepeatedFieldBuilder<Condition, Condition.Builder, ConditionOrBuilder> condBuilder_;

            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_Mysqlx_Expect_Open_descriptor;
            }

            @Override
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_Mysqlx_Expect_Open_fieldAccessorTable.ensureFieldAccessorsInitialized(Open.class, Builder.class);
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
                    this.getCondFieldBuilder();
                }
            }

            private static Builder create() {
                return new Builder();
            }

            @Override
            public Builder clear() {
                super.clear();
                this.op_ = CtxOperation.EXPECT_CTX_COPY_PREV;
                this.bitField0_ &= 0xFFFFFFFE;
                if (this.condBuilder_ == null) {
                    this.cond_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFFFFD;
                } else {
                    this.condBuilder_.clear();
                }
                return this;
            }

            @Override
            public Builder clone() {
                return Builder.create().mergeFrom(this.buildPartial());
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return internal_static_Mysqlx_Expect_Open_descriptor;
            }

            @Override
            public Open getDefaultInstanceForType() {
                return Open.getDefaultInstance();
            }

            @Override
            public Open build() {
                Open result = this.buildPartial();
                if (!result.isInitialized()) {
                    throw Builder.newUninitializedMessageException(result);
                }
                return result;
            }

            @Override
            public Open buildPartial() {
                Open result = new Open(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ |= 1;
                }
                result.op_ = this.op_;
                if (this.condBuilder_ == null) {
                    if ((this.bitField0_ & 2) == 2) {
                        this.cond_ = Collections.unmodifiableList(this.cond_);
                        this.bitField0_ &= 0xFFFFFFFD;
                    }
                    result.cond_ = this.cond_;
                } else {
                    result.cond_ = this.condBuilder_.build();
                }
                result.bitField0_ = to_bitField0_;
                this.onBuilt();
                return result;
            }

            @Override
            public Builder mergeFrom(Message other) {
                if (other instanceof Open) {
                    return this.mergeFrom((Open)other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(Open other) {
                if (other == Open.getDefaultInstance()) {
                    return this;
                }
                if (other.hasOp()) {
                    this.setOp(other.getOp());
                }
                if (this.condBuilder_ == null) {
                    if (!other.cond_.isEmpty()) {
                        if (this.cond_.isEmpty()) {
                            this.cond_ = other.cond_;
                            this.bitField0_ &= 0xFFFFFFFD;
                        } else {
                            this.ensureCondIsMutable();
                            this.cond_.addAll(other.cond_);
                        }
                        this.onChanged();
                    }
                } else if (!other.cond_.isEmpty()) {
                    if (this.condBuilder_.isEmpty()) {
                        this.condBuilder_.dispose();
                        this.condBuilder_ = null;
                        this.cond_ = other.cond_;
                        this.bitField0_ &= 0xFFFFFFFD;
                        this.condBuilder_ = alwaysUseFieldBuilders ? this.getCondFieldBuilder() : null;
                    } else {
                        this.condBuilder_.addAllMessages(other.cond_);
                    }
                }
                this.mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            @Override
            public final boolean isInitialized() {
                for (int i = 0; i < this.getCondCount(); ++i) {
                    if (this.getCond(i).isInitialized()) continue;
                    return false;
                }
                return true;
            }

            @Override
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                Open parsedMessage = null;
                try {
                    parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
                } catch (InvalidProtocolBufferException e) {
                    parsedMessage = (Open)e.getUnfinishedMessage();
                    throw e;
                } finally {
                    if (parsedMessage != null) {
                        this.mergeFrom(parsedMessage);
                    }
                }
                return this;
            }

            @Override
            public boolean hasOp() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override
            public CtxOperation getOp() {
                return this.op_;
            }

            public Builder setOp(CtxOperation value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 1;
                this.op_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearOp() {
                this.bitField0_ &= 0xFFFFFFFE;
                this.op_ = CtxOperation.EXPECT_CTX_COPY_PREV;
                this.onChanged();
                return this;
            }

            private void ensureCondIsMutable() {
                if ((this.bitField0_ & 2) != 2) {
                    this.cond_ = new ArrayList<Condition>(this.cond_);
                    this.bitField0_ |= 2;
                }
            }

            @Override
            public List<Condition> getCondList() {
                if (this.condBuilder_ == null) {
                    return Collections.unmodifiableList(this.cond_);
                }
                return this.condBuilder_.getMessageList();
            }

            @Override
            public int getCondCount() {
                if (this.condBuilder_ == null) {
                    return this.cond_.size();
                }
                return this.condBuilder_.getCount();
            }

            @Override
            public Condition getCond(int index) {
                if (this.condBuilder_ == null) {
                    return this.cond_.get(index);
                }
                return this.condBuilder_.getMessage(index);
            }

            public Builder setCond(int index, Condition value) {
                if (this.condBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureCondIsMutable();
                    this.cond_.set(index, value);
                    this.onChanged();
                } else {
                    this.condBuilder_.setMessage(index, value);
                }
                return this;
            }

            public Builder setCond(int index, Condition.Builder builderForValue) {
                if (this.condBuilder_ == null) {
                    this.ensureCondIsMutable();
                    this.cond_.set(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.condBuilder_.setMessage(index, builderForValue.build());
                }
                return this;
            }

            public Builder addCond(Condition value) {
                if (this.condBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureCondIsMutable();
                    this.cond_.add(value);
                    this.onChanged();
                } else {
                    this.condBuilder_.addMessage(value);
                }
                return this;
            }

            public Builder addCond(int index, Condition value) {
                if (this.condBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureCondIsMutable();
                    this.cond_.add(index, value);
                    this.onChanged();
                } else {
                    this.condBuilder_.addMessage(index, value);
                }
                return this;
            }

            public Builder addCond(Condition.Builder builderForValue) {
                if (this.condBuilder_ == null) {
                    this.ensureCondIsMutable();
                    this.cond_.add(builderForValue.build());
                    this.onChanged();
                } else {
                    this.condBuilder_.addMessage(builderForValue.build());
                }
                return this;
            }

            public Builder addCond(int index, Condition.Builder builderForValue) {
                if (this.condBuilder_ == null) {
                    this.ensureCondIsMutable();
                    this.cond_.add(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.condBuilder_.addMessage(index, builderForValue.build());
                }
                return this;
            }

            public Builder addAllCond(Iterable<? extends Condition> values2) {
                if (this.condBuilder_ == null) {
                    this.ensureCondIsMutable();
                    AbstractMessageLite.Builder.addAll(values2, this.cond_);
                    this.onChanged();
                } else {
                    this.condBuilder_.addAllMessages(values2);
                }
                return this;
            }

            public Builder clearCond() {
                if (this.condBuilder_ == null) {
                    this.cond_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFFFFD;
                    this.onChanged();
                } else {
                    this.condBuilder_.clear();
                }
                return this;
            }

            public Builder removeCond(int index) {
                if (this.condBuilder_ == null) {
                    this.ensureCondIsMutable();
                    this.cond_.remove(index);
                    this.onChanged();
                } else {
                    this.condBuilder_.remove(index);
                }
                return this;
            }

            public Condition.Builder getCondBuilder(int index) {
                return this.getCondFieldBuilder().getBuilder(index);
            }

            @Override
            public ConditionOrBuilder getCondOrBuilder(int index) {
                if (this.condBuilder_ == null) {
                    return this.cond_.get(index);
                }
                return this.condBuilder_.getMessageOrBuilder(index);
            }

            @Override
            public List<? extends ConditionOrBuilder> getCondOrBuilderList() {
                if (this.condBuilder_ != null) {
                    return this.condBuilder_.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.cond_);
            }

            public Condition.Builder addCondBuilder() {
                return this.getCondFieldBuilder().addBuilder(Condition.getDefaultInstance());
            }

            public Condition.Builder addCondBuilder(int index) {
                return this.getCondFieldBuilder().addBuilder(index, Condition.getDefaultInstance());
            }

            public List<Condition.Builder> getCondBuilderList() {
                return this.getCondFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilder<Condition, Condition.Builder, ConditionOrBuilder> getCondFieldBuilder() {
                if (this.condBuilder_ == null) {
                    this.condBuilder_ = new RepeatedFieldBuilder(this.cond_, (this.bitField0_ & 2) == 2, this.getParentForChildren(), this.isClean());
                    this.cond_ = null;
                }
                return this.condBuilder_;
            }
        }

        public static final class Condition
        extends GeneratedMessage
        implements ConditionOrBuilder {
            private static final Condition defaultInstance;
            private final UnknownFieldSet unknownFields;
            public static Parser<Condition> PARSER;
            private int bitField0_;
            public static final int CONDITION_KEY_FIELD_NUMBER = 1;
            private int conditionKey_;
            public static final int CONDITION_VALUE_FIELD_NUMBER = 2;
            private ByteString conditionValue_;
            public static final int OP_FIELD_NUMBER = 3;
            private ConditionOperation op_;
            private byte memoizedIsInitialized = (byte)-1;
            private int memoizedSerializedSize = -1;
            private static final long serialVersionUID = 0L;

            private Condition(GeneratedMessage.Builder<?> builder) {
                super(builder);
                this.unknownFields = builder.getUnknownFields();
            }

            private Condition(boolean noInit) {
                this.unknownFields = UnknownFieldSet.getDefaultInstance();
            }

            public static Condition getDefaultInstance() {
                return defaultInstance;
            }

            @Override
            public Condition getDefaultInstanceForType() {
                return defaultInstance;
            }

            @Override
            public final UnknownFieldSet getUnknownFields() {
                return this.unknownFields;
            }

            private Condition(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
                            case 8: {
                                this.bitField0_ |= 1;
                                this.conditionKey_ = input.readUInt32();
                                continue block12;
                            }
                            case 18: {
                                this.bitField0_ |= 2;
                                this.conditionValue_ = input.readBytes();
                                continue block12;
                            }
                            case 24: 
                        }
                        int rawValue = input.readEnum();
                        ConditionOperation value = ConditionOperation.valueOf(rawValue);
                        if (value == null) {
                            unknownFields.mergeVarintField(3, rawValue);
                            continue;
                        }
                        this.bitField0_ |= 4;
                        this.op_ = value;
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
                return internal_static_Mysqlx_Expect_Open_Condition_descriptor;
            }

            @Override
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_Mysqlx_Expect_Open_Condition_fieldAccessorTable.ensureFieldAccessorsInitialized(Condition.class, Builder.class);
            }

            public Parser<Condition> getParserForType() {
                return PARSER;
            }

            @Override
            public boolean hasConditionKey() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override
            public int getConditionKey() {
                return this.conditionKey_;
            }

            @Override
            public boolean hasConditionValue() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override
            public ByteString getConditionValue() {
                return this.conditionValue_;
            }

            @Override
            public boolean hasOp() {
                return (this.bitField0_ & 4) == 4;
            }

            @Override
            public ConditionOperation getOp() {
                return this.op_;
            }

            private void initFields() {
                this.conditionKey_ = 0;
                this.conditionValue_ = ByteString.EMPTY;
                this.op_ = ConditionOperation.EXPECT_OP_SET;
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
                if (!this.hasConditionKey()) {
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
                    output.writeUInt32(1, this.conditionKey_);
                }
                if ((this.bitField0_ & 2) == 2) {
                    output.writeBytes(2, this.conditionValue_);
                }
                if ((this.bitField0_ & 4) == 4) {
                    output.writeEnum(3, this.op_.getNumber());
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
                    size += CodedOutputStream.computeUInt32Size(1, this.conditionKey_);
                }
                if ((this.bitField0_ & 2) == 2) {
                    size += CodedOutputStream.computeBytesSize(2, this.conditionValue_);
                }
                if ((this.bitField0_ & 4) == 4) {
                    size += CodedOutputStream.computeEnumSize(3, this.op_.getNumber());
                }
                this.memoizedSerializedSize = size += this.getUnknownFields().getSerializedSize();
                return size;
            }

            @Override
            protected Object writeReplace() throws ObjectStreamException {
                return super.writeReplace();
            }

            public static Condition parseFrom(ByteString data) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(data);
            }

            public static Condition parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(data, extensionRegistry);
            }

            public static Condition parseFrom(byte[] data) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(data);
            }

            public static Condition parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(data, extensionRegistry);
            }

            public static Condition parseFrom(InputStream input) throws IOException {
                return PARSER.parseFrom(input);
            }

            public static Condition parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return PARSER.parseFrom(input, extensionRegistry);
            }

            public static Condition parseDelimitedFrom(InputStream input) throws IOException {
                return PARSER.parseDelimitedFrom(input);
            }

            public static Condition parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return PARSER.parseDelimitedFrom(input, extensionRegistry);
            }

            public static Condition parseFrom(CodedInputStream input) throws IOException {
                return PARSER.parseFrom(input);
            }

            public static Condition parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return PARSER.parseFrom(input, extensionRegistry);
            }

            public static Builder newBuilder() {
                return Builder.create();
            }

            @Override
            public Builder newBuilderForType() {
                return Condition.newBuilder();
            }

            public static Builder newBuilder(Condition prototype) {
                return Condition.newBuilder().mergeFrom(prototype);
            }

            @Override
            public Builder toBuilder() {
                return Condition.newBuilder(this);
            }

            @Override
            protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
                Builder builder = new Builder(parent);
                return builder;
            }

            static {
                PARSER = new AbstractParser<Condition>(){

                    @Override
                    public Condition parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                        return new Condition(input, extensionRegistry);
                    }
                };
                defaultInstance = new Condition(true);
                defaultInstance.initFields();
            }

            public static final class Builder
            extends GeneratedMessage.Builder<Builder>
            implements ConditionOrBuilder {
                private int bitField0_;
                private int conditionKey_;
                private ByteString conditionValue_ = ByteString.EMPTY;
                private ConditionOperation op_ = ConditionOperation.EXPECT_OP_SET;

                public static final Descriptors.Descriptor getDescriptor() {
                    return internal_static_Mysqlx_Expect_Open_Condition_descriptor;
                }

                @Override
                protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                    return internal_static_Mysqlx_Expect_Open_Condition_fieldAccessorTable.ensureFieldAccessorsInitialized(Condition.class, Builder.class);
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
                    this.conditionKey_ = 0;
                    this.bitField0_ &= 0xFFFFFFFE;
                    this.conditionValue_ = ByteString.EMPTY;
                    this.bitField0_ &= 0xFFFFFFFD;
                    this.op_ = ConditionOperation.EXPECT_OP_SET;
                    this.bitField0_ &= 0xFFFFFFFB;
                    return this;
                }

                @Override
                public Builder clone() {
                    return Builder.create().mergeFrom(this.buildPartial());
                }

                @Override
                public Descriptors.Descriptor getDescriptorForType() {
                    return internal_static_Mysqlx_Expect_Open_Condition_descriptor;
                }

                @Override
                public Condition getDefaultInstanceForType() {
                    return Condition.getDefaultInstance();
                }

                @Override
                public Condition build() {
                    Condition result = this.buildPartial();
                    if (!result.isInitialized()) {
                        throw Builder.newUninitializedMessageException(result);
                    }
                    return result;
                }

                @Override
                public Condition buildPartial() {
                    Condition result = new Condition(this);
                    int from_bitField0_ = this.bitField0_;
                    int to_bitField0_ = 0;
                    if ((from_bitField0_ & 1) == 1) {
                        to_bitField0_ |= 1;
                    }
                    result.conditionKey_ = this.conditionKey_;
                    if ((from_bitField0_ & 2) == 2) {
                        to_bitField0_ |= 2;
                    }
                    result.conditionValue_ = this.conditionValue_;
                    if ((from_bitField0_ & 4) == 4) {
                        to_bitField0_ |= 4;
                    }
                    result.op_ = this.op_;
                    result.bitField0_ = to_bitField0_;
                    this.onBuilt();
                    return result;
                }

                @Override
                public Builder mergeFrom(Message other) {
                    if (other instanceof Condition) {
                        return this.mergeFrom((Condition)other);
                    }
                    super.mergeFrom(other);
                    return this;
                }

                public Builder mergeFrom(Condition other) {
                    if (other == Condition.getDefaultInstance()) {
                        return this;
                    }
                    if (other.hasConditionKey()) {
                        this.setConditionKey(other.getConditionKey());
                    }
                    if (other.hasConditionValue()) {
                        this.setConditionValue(other.getConditionValue());
                    }
                    if (other.hasOp()) {
                        this.setOp(other.getOp());
                    }
                    this.mergeUnknownFields(other.getUnknownFields());
                    return this;
                }

                @Override
                public final boolean isInitialized() {
                    return this.hasConditionKey();
                }

                @Override
                public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                    Condition parsedMessage = null;
                    try {
                        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
                    } catch (InvalidProtocolBufferException e) {
                        parsedMessage = (Condition)e.getUnfinishedMessage();
                        throw e;
                    } finally {
                        if (parsedMessage != null) {
                            this.mergeFrom(parsedMessage);
                        }
                    }
                    return this;
                }

                @Override
                public boolean hasConditionKey() {
                    return (this.bitField0_ & 1) == 1;
                }

                @Override
                public int getConditionKey() {
                    return this.conditionKey_;
                }

                public Builder setConditionKey(int value) {
                    this.bitField0_ |= 1;
                    this.conditionKey_ = value;
                    this.onChanged();
                    return this;
                }

                public Builder clearConditionKey() {
                    this.bitField0_ &= 0xFFFFFFFE;
                    this.conditionKey_ = 0;
                    this.onChanged();
                    return this;
                }

                @Override
                public boolean hasConditionValue() {
                    return (this.bitField0_ & 2) == 2;
                }

                @Override
                public ByteString getConditionValue() {
                    return this.conditionValue_;
                }

                public Builder setConditionValue(ByteString value) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.bitField0_ |= 2;
                    this.conditionValue_ = value;
                    this.onChanged();
                    return this;
                }

                public Builder clearConditionValue() {
                    this.bitField0_ &= 0xFFFFFFFD;
                    this.conditionValue_ = Condition.getDefaultInstance().getConditionValue();
                    this.onChanged();
                    return this;
                }

                @Override
                public boolean hasOp() {
                    return (this.bitField0_ & 4) == 4;
                }

                @Override
                public ConditionOperation getOp() {
                    return this.op_;
                }

                public Builder setOp(ConditionOperation value) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.bitField0_ |= 4;
                    this.op_ = value;
                    this.onChanged();
                    return this;
                }

                public Builder clearOp() {
                    this.bitField0_ &= 0xFFFFFFFB;
                    this.op_ = ConditionOperation.EXPECT_OP_SET;
                    this.onChanged();
                    return this;
                }
            }

            public static enum ConditionOperation implements ProtocolMessageEnum
            {
                EXPECT_OP_SET(0, 0),
                EXPECT_OP_UNSET(1, 1);

                public static final int EXPECT_OP_SET_VALUE = 0;
                public static final int EXPECT_OP_UNSET_VALUE = 1;
                private static Internal.EnumLiteMap<ConditionOperation> internalValueMap;
                private static final ConditionOperation[] VALUES;
                private final int index;
                private final int value;

                @Override
                public final int getNumber() {
                    return this.value;
                }

                public static ConditionOperation valueOf(int value) {
                    switch (value) {
                        case 0: {
                            return EXPECT_OP_SET;
                        }
                        case 1: {
                            return EXPECT_OP_UNSET;
                        }
                    }
                    return null;
                }

                public static Internal.EnumLiteMap<ConditionOperation> internalGetValueMap() {
                    return internalValueMap;
                }

                @Override
                public final Descriptors.EnumValueDescriptor getValueDescriptor() {
                    return ConditionOperation.getDescriptor().getValues().get(this.index);
                }

                @Override
                public final Descriptors.EnumDescriptor getDescriptorForType() {
                    return ConditionOperation.getDescriptor();
                }

                public static final Descriptors.EnumDescriptor getDescriptor() {
                    return Condition.getDescriptor().getEnumTypes().get(1);
                }

                public static ConditionOperation valueOf(Descriptors.EnumValueDescriptor desc) {
                    if (desc.getType() != ConditionOperation.getDescriptor()) {
                        throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
                    }
                    return VALUES[desc.getIndex()];
                }

                private ConditionOperation(int index, int value) {
                    this.index = index;
                    this.value = value;
                }

                static {
                    internalValueMap = new Internal.EnumLiteMap<ConditionOperation>(){

                        @Override
                        public ConditionOperation findValueByNumber(int number) {
                            return ConditionOperation.valueOf(number);
                        }
                    };
                    VALUES = ConditionOperation.values();
                }
            }

            public static enum Key implements ProtocolMessageEnum
            {
                EXPECT_NO_ERROR(0, 1),
                EXPECT_FIELD_EXIST(1, 2),
                EXPECT_DOCID_GENERATED(2, 3);

                public static final int EXPECT_NO_ERROR_VALUE = 1;
                public static final int EXPECT_FIELD_EXIST_VALUE = 2;
                public static final int EXPECT_DOCID_GENERATED_VALUE = 3;
                private static Internal.EnumLiteMap<Key> internalValueMap;
                private static final Key[] VALUES;
                private final int index;
                private final int value;

                @Override
                public final int getNumber() {
                    return this.value;
                }

                public static Key valueOf(int value) {
                    switch (value) {
                        case 1: {
                            return EXPECT_NO_ERROR;
                        }
                        case 2: {
                            return EXPECT_FIELD_EXIST;
                        }
                        case 3: {
                            return EXPECT_DOCID_GENERATED;
                        }
                    }
                    return null;
                }

                public static Internal.EnumLiteMap<Key> internalGetValueMap() {
                    return internalValueMap;
                }

                @Override
                public final Descriptors.EnumValueDescriptor getValueDescriptor() {
                    return Key.getDescriptor().getValues().get(this.index);
                }

                @Override
                public final Descriptors.EnumDescriptor getDescriptorForType() {
                    return Key.getDescriptor();
                }

                public static final Descriptors.EnumDescriptor getDescriptor() {
                    return Condition.getDescriptor().getEnumTypes().get(0);
                }

                public static Key valueOf(Descriptors.EnumValueDescriptor desc) {
                    if (desc.getType() != Key.getDescriptor()) {
                        throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
                    }
                    return VALUES[desc.getIndex()];
                }

                private Key(int index, int value) {
                    this.index = index;
                    this.value = value;
                }

                static {
                    internalValueMap = new Internal.EnumLiteMap<Key>(){

                        @Override
                        public Key findValueByNumber(int number) {
                            return Key.valueOf(number);
                        }
                    };
                    VALUES = Key.values();
                }
            }
        }

        public static interface ConditionOrBuilder
        extends MessageOrBuilder {
            public boolean hasConditionKey();

            public int getConditionKey();

            public boolean hasConditionValue();

            public ByteString getConditionValue();

            public boolean hasOp();

            public Condition.ConditionOperation getOp();
        }

        public static enum CtxOperation implements ProtocolMessageEnum
        {
            EXPECT_CTX_COPY_PREV(0, 0),
            EXPECT_CTX_EMPTY(1, 1);

            public static final int EXPECT_CTX_COPY_PREV_VALUE = 0;
            public static final int EXPECT_CTX_EMPTY_VALUE = 1;
            private static Internal.EnumLiteMap<CtxOperation> internalValueMap;
            private static final CtxOperation[] VALUES;
            private final int index;
            private final int value;

            @Override
            public final int getNumber() {
                return this.value;
            }

            public static CtxOperation valueOf(int value) {
                switch (value) {
                    case 0: {
                        return EXPECT_CTX_COPY_PREV;
                    }
                    case 1: {
                        return EXPECT_CTX_EMPTY;
                    }
                }
                return null;
            }

            public static Internal.EnumLiteMap<CtxOperation> internalGetValueMap() {
                return internalValueMap;
            }

            @Override
            public final Descriptors.EnumValueDescriptor getValueDescriptor() {
                return CtxOperation.getDescriptor().getValues().get(this.index);
            }

            @Override
            public final Descriptors.EnumDescriptor getDescriptorForType() {
                return CtxOperation.getDescriptor();
            }

            public static final Descriptors.EnumDescriptor getDescriptor() {
                return Open.getDescriptor().getEnumTypes().get(0);
            }

            public static CtxOperation valueOf(Descriptors.EnumValueDescriptor desc) {
                if (desc.getType() != CtxOperation.getDescriptor()) {
                    throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
                }
                return VALUES[desc.getIndex()];
            }

            private CtxOperation(int index, int value) {
                this.index = index;
                this.value = value;
            }

            static {
                internalValueMap = new Internal.EnumLiteMap<CtxOperation>(){

                    @Override
                    public CtxOperation findValueByNumber(int number) {
                        return CtxOperation.valueOf(number);
                    }
                };
                VALUES = CtxOperation.values();
            }
        }
    }

    public static interface OpenOrBuilder
    extends MessageOrBuilder {
        public boolean hasOp();

        public Open.CtxOperation getOp();

        public List<Open.Condition> getCondList();

        public Open.Condition getCond(int var1);

        public int getCondCount();

        public List<? extends Open.ConditionOrBuilder> getCondOrBuilderList();

        public Open.ConditionOrBuilder getCondOrBuilder(int var1);
    }
}

