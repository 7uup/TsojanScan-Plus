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
import com.mysql.cj.x.protobuf.Mysqlx;
import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectStreamException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class MysqlxNotice {
    private static final Descriptors.Descriptor internal_static_Mysqlx_Notice_Frame_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_Mysqlx_Notice_Frame_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_Mysqlx_Notice_Warning_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_Mysqlx_Notice_Warning_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_Mysqlx_Notice_SessionVariableChanged_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_Mysqlx_Notice_SessionVariableChanged_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_Mysqlx_Notice_SessionStateChanged_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_Mysqlx_Notice_SessionStateChanged_fieldAccessorTable;
    private static Descriptors.FileDescriptor descriptor;

    private MysqlxNotice() {
    }

    public static void registerAllExtensions(ExtensionRegistry registry) {
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        String[] descriptorData = new String[]{"\n\u0013mysqlx_notice.proto\u0012\rMysqlx.Notice\u001a\fmysqlx.proto\u001a\u0016mysqlx_datatypes.proto\"\u00cd\u0001\n\u0005Frame\u0012\f\n\u0004type\u0018\u0001 \u0002(\r\u00121\n\u0005scope\u0018\u0002 \u0001(\u000e2\u001a.Mysqlx.Notice.Frame.Scope:\u0006GLOBAL\u0012\u000f\n\u0007payload\u0018\u0003 \u0001(\f\"\u001e\n\u0005Scope\u0012\n\n\u0006GLOBAL\u0010\u0001\u0012\t\n\u0005LOCAL\u0010\u0002\"L\n\u0004Type\u0012\u000b\n\u0007WARNING\u0010\u0001\u0012\u001c\n\u0018SESSION_VARIABLE_CHANGED\u0010\u0002\u0012\u0019\n\u0015SESSION_STATE_CHANGED\u0010\u0003:\u0004\u0090\u00ea0\u000b\"\u0085\u0001\n\u0007Warning\u00124\n\u0005level\u0018\u0001 \u0001(\u000e2\u001c.Mysqlx.Notice.Warning.Level:\u0007WARNING\u0012\f\n\u0004code\u0018\u0002 \u0002(\r\u0012\u000b\n\u0003msg\u0018\u0003 \u0002(\t\")\n\u0005Level\u0012\b\n\u0004NOTE\u0010\u0001\u0012\u000b\n\u0007WA", "RNING\u0010\u0002\u0012\t\n\u0005ERROR\u0010\u0003\"P\n\u0016SessionVariableChanged\u0012\r\n\u0005param\u0018\u0001 \u0002(\t\u0012'\n\u0005value\u0018\u0002 \u0001(\u000b2\u0018.Mysqlx.Datatypes.Scalar\"\u00f1\u0002\n\u0013SessionStateChanged\u0012;\n\u0005param\u0018\u0001 \u0002(\u000e2,.Mysqlx.Notice.SessionStateChanged.Parameter\u0012'\n\u0005value\u0018\u0002 \u0003(\u000b2\u0018.Mysqlx.Datatypes.Scalar\"\u00f3\u0001\n\tParameter\u0012\u0012\n\u000eCURRENT_SCHEMA\u0010\u0001\u0012\u0013\n\u000fACCOUNT_EXPIRED\u0010\u0002\u0012\u0017\n\u0013GENERATED_INSERT_ID\u0010\u0003\u0012\u0011\n\rROWS_AFFECTED\u0010\u0004\u0012\u000e\n\nROWS_FOUND\u0010\u0005\u0012\u0010\n\fROWS_MATCHED\u0010\u0006\u0012\u0011\n\rTRX_COMMITTED\u0010\u0007\u0012\u0012\n\u000eTRX_ROLLEDBACK\u0010\t\u0012\u0014", "\n\u0010PRODUCED_MESSAGE\u0010\n\u0012\u0016\n\u0012CLIENT_ID_ASSIGNED\u0010\u000b\u0012\u001a\n\u0016GENERATED_DOCUMENT_IDS\u0010\fB\u0019\n\u0017com.mysql.cj.x.protobuf"};
        Descriptors.FileDescriptor.InternalDescriptorAssigner assigner = new Descriptors.FileDescriptor.InternalDescriptorAssigner(){

            @Override
            public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor root) {
                descriptor = root;
                return null;
            }
        };
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[]{Mysqlx.getDescriptor(), MysqlxDatatypes.getDescriptor()}, assigner);
        internal_static_Mysqlx_Notice_Frame_descriptor = MysqlxNotice.getDescriptor().getMessageTypes().get(0);
        internal_static_Mysqlx_Notice_Frame_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_Mysqlx_Notice_Frame_descriptor, new String[]{"Type", "Scope", "Payload"});
        internal_static_Mysqlx_Notice_Warning_descriptor = MysqlxNotice.getDescriptor().getMessageTypes().get(1);
        internal_static_Mysqlx_Notice_Warning_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_Mysqlx_Notice_Warning_descriptor, new String[]{"Level", "Code", "Msg"});
        internal_static_Mysqlx_Notice_SessionVariableChanged_descriptor = MysqlxNotice.getDescriptor().getMessageTypes().get(2);
        internal_static_Mysqlx_Notice_SessionVariableChanged_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_Mysqlx_Notice_SessionVariableChanged_descriptor, new String[]{"Param", "Value"});
        internal_static_Mysqlx_Notice_SessionStateChanged_descriptor = MysqlxNotice.getDescriptor().getMessageTypes().get(3);
        internal_static_Mysqlx_Notice_SessionStateChanged_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_Mysqlx_Notice_SessionStateChanged_descriptor, new String[]{"Param", "Value"});
        ExtensionRegistry registry = ExtensionRegistry.newInstance();
        registry.add(Mysqlx.serverMessageId);
        Descriptors.FileDescriptor.internalUpdateFileDescriptor(descriptor, registry);
        Mysqlx.getDescriptor();
        MysqlxDatatypes.getDescriptor();
    }

    public static final class SessionStateChanged
    extends GeneratedMessage
    implements SessionStateChangedOrBuilder {
        private static final SessionStateChanged defaultInstance;
        private final UnknownFieldSet unknownFields;
        public static Parser<SessionStateChanged> PARSER;
        private int bitField0_;
        public static final int PARAM_FIELD_NUMBER = 1;
        private Parameter param_;
        public static final int VALUE_FIELD_NUMBER = 2;
        private List<MysqlxDatatypes.Scalar> value_;
        private byte memoizedIsInitialized = (byte)-1;
        private int memoizedSerializedSize = -1;
        private static final long serialVersionUID = 0L;

        private SessionStateChanged(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.unknownFields = builder.getUnknownFields();
        }

        private SessionStateChanged(boolean noInit) {
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static SessionStateChanged getDefaultInstance() {
            return defaultInstance;
        }

        @Override
        public SessionStateChanged getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private SessionStateChanged(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
                            Parameter value = Parameter.valueOf(rawValue);
                            if (value == null) {
                                unknownFields.mergeVarintField(1, rawValue);
                                continue block11;
                            }
                            this.bitField0_ |= 1;
                            this.param_ = value;
                            continue block11;
                        }
                        case 18: 
                    }
                    if ((mutable_bitField0_ & 2) != 2) {
                        this.value_ = new ArrayList<MysqlxDatatypes.Scalar>();
                        mutable_bitField0_ |= 2;
                    }
                    this.value_.add(input.readMessage(MysqlxDatatypes.Scalar.PARSER, extensionRegistry));
                }
            } catch (InvalidProtocolBufferException e) {
                throw e.setUnfinishedMessage(this);
            } catch (IOException e) {
                throw new InvalidProtocolBufferException(e.getMessage()).setUnfinishedMessage(this);
            } finally {
                if ((mutable_bitField0_ & 2) == 2) {
                    this.value_ = Collections.unmodifiableList(this.value_);
                }
                this.unknownFields = unknownFields.build();
                this.makeExtensionsImmutable();
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return internal_static_Mysqlx_Notice_SessionStateChanged_descriptor;
        }

        @Override
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return internal_static_Mysqlx_Notice_SessionStateChanged_fieldAccessorTable.ensureFieldAccessorsInitialized(SessionStateChanged.class, Builder.class);
        }

        public Parser<SessionStateChanged> getParserForType() {
            return PARSER;
        }

        @Override
        public boolean hasParam() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override
        public Parameter getParam() {
            return this.param_;
        }

        @Override
        public List<MysqlxDatatypes.Scalar> getValueList() {
            return this.value_;
        }

        @Override
        public List<? extends MysqlxDatatypes.ScalarOrBuilder> getValueOrBuilderList() {
            return this.value_;
        }

        @Override
        public int getValueCount() {
            return this.value_.size();
        }

        @Override
        public MysqlxDatatypes.Scalar getValue(int index) {
            return this.value_.get(index);
        }

        @Override
        public MysqlxDatatypes.ScalarOrBuilder getValueOrBuilder(int index) {
            return this.value_.get(index);
        }

        private void initFields() {
            this.param_ = Parameter.CURRENT_SCHEMA;
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
            if (!this.hasParam()) {
                this.memoizedIsInitialized = 0;
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
            if ((this.bitField0_ & 1) == 1) {
                output.writeEnum(1, this.param_.getNumber());
            }
            for (int i = 0; i < this.value_.size(); ++i) {
                output.writeMessage(2, this.value_.get(i));
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
                size += CodedOutputStream.computeEnumSize(1, this.param_.getNumber());
            }
            for (int i = 0; i < this.value_.size(); ++i) {
                size += CodedOutputStream.computeMessageSize(2, this.value_.get(i));
            }
            this.memoizedSerializedSize = size += this.getUnknownFields().getSerializedSize();
            return size;
        }

        @Override
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static SessionStateChanged parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static SessionStateChanged parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static SessionStateChanged parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static SessionStateChanged parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static SessionStateChanged parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static SessionStateChanged parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static SessionStateChanged parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static SessionStateChanged parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static SessionStateChanged parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static SessionStateChanged parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override
        public Builder newBuilderForType() {
            return SessionStateChanged.newBuilder();
        }

        public static Builder newBuilder(SessionStateChanged prototype) {
            return SessionStateChanged.newBuilder().mergeFrom(prototype);
        }

        @Override
        public Builder toBuilder() {
            return SessionStateChanged.newBuilder(this);
        }

        @Override
        protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        static {
            PARSER = new AbstractParser<SessionStateChanged>(){

                @Override
                public SessionStateChanged parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                    return new SessionStateChanged(input, extensionRegistry);
                }
            };
            defaultInstance = new SessionStateChanged(true);
            defaultInstance.initFields();
        }

        public static final class Builder
        extends GeneratedMessage.Builder<Builder>
        implements SessionStateChangedOrBuilder {
            private int bitField0_;
            private Parameter param_ = Parameter.CURRENT_SCHEMA;
            private List<MysqlxDatatypes.Scalar> value_ = Collections.emptyList();
            private RepeatedFieldBuilder<MysqlxDatatypes.Scalar, MysqlxDatatypes.Scalar.Builder, MysqlxDatatypes.ScalarOrBuilder> valueBuilder_;

            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_Mysqlx_Notice_SessionStateChanged_descriptor;
            }

            @Override
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_Mysqlx_Notice_SessionStateChanged_fieldAccessorTable.ensureFieldAccessorsInitialized(SessionStateChanged.class, Builder.class);
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
                this.param_ = Parameter.CURRENT_SCHEMA;
                this.bitField0_ &= 0xFFFFFFFE;
                if (this.valueBuilder_ == null) {
                    this.value_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFFFFD;
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
                return internal_static_Mysqlx_Notice_SessionStateChanged_descriptor;
            }

            @Override
            public SessionStateChanged getDefaultInstanceForType() {
                return SessionStateChanged.getDefaultInstance();
            }

            @Override
            public SessionStateChanged build() {
                SessionStateChanged result = this.buildPartial();
                if (!result.isInitialized()) {
                    throw Builder.newUninitializedMessageException(result);
                }
                return result;
            }

            @Override
            public SessionStateChanged buildPartial() {
                SessionStateChanged result = new SessionStateChanged(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ |= 1;
                }
                result.param_ = this.param_;
                if (this.valueBuilder_ == null) {
                    if ((this.bitField0_ & 2) == 2) {
                        this.value_ = Collections.unmodifiableList(this.value_);
                        this.bitField0_ &= 0xFFFFFFFD;
                    }
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
                if (other instanceof SessionStateChanged) {
                    return this.mergeFrom((SessionStateChanged)other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(SessionStateChanged other) {
                if (other == SessionStateChanged.getDefaultInstance()) {
                    return this;
                }
                if (other.hasParam()) {
                    this.setParam(other.getParam());
                }
                if (this.valueBuilder_ == null) {
                    if (!other.value_.isEmpty()) {
                        if (this.value_.isEmpty()) {
                            this.value_ = other.value_;
                            this.bitField0_ &= 0xFFFFFFFD;
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
                        this.bitField0_ &= 0xFFFFFFFD;
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
                if (!this.hasParam()) {
                    return false;
                }
                for (int i = 0; i < this.getValueCount(); ++i) {
                    if (this.getValue(i).isInitialized()) continue;
                    return false;
                }
                return true;
            }

            @Override
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                SessionStateChanged parsedMessage = null;
                try {
                    parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
                } catch (InvalidProtocolBufferException e) {
                    parsedMessage = (SessionStateChanged)e.getUnfinishedMessage();
                    throw e;
                } finally {
                    if (parsedMessage != null) {
                        this.mergeFrom(parsedMessage);
                    }
                }
                return this;
            }

            @Override
            public boolean hasParam() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override
            public Parameter getParam() {
                return this.param_;
            }

            public Builder setParam(Parameter value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 1;
                this.param_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearParam() {
                this.bitField0_ &= 0xFFFFFFFE;
                this.param_ = Parameter.CURRENT_SCHEMA;
                this.onChanged();
                return this;
            }

            private void ensureValueIsMutable() {
                if ((this.bitField0_ & 2) != 2) {
                    this.value_ = new ArrayList<MysqlxDatatypes.Scalar>(this.value_);
                    this.bitField0_ |= 2;
                }
            }

            @Override
            public List<MysqlxDatatypes.Scalar> getValueList() {
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
            public MysqlxDatatypes.Scalar getValue(int index) {
                if (this.valueBuilder_ == null) {
                    return this.value_.get(index);
                }
                return this.valueBuilder_.getMessage(index);
            }

            public Builder setValue(int index, MysqlxDatatypes.Scalar value) {
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

            public Builder setValue(int index, MysqlxDatatypes.Scalar.Builder builderForValue) {
                if (this.valueBuilder_ == null) {
                    this.ensureValueIsMutable();
                    this.value_.set(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.valueBuilder_.setMessage(index, builderForValue.build());
                }
                return this;
            }

            public Builder addValue(MysqlxDatatypes.Scalar value) {
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

            public Builder addValue(int index, MysqlxDatatypes.Scalar value) {
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

            public Builder addValue(MysqlxDatatypes.Scalar.Builder builderForValue) {
                if (this.valueBuilder_ == null) {
                    this.ensureValueIsMutable();
                    this.value_.add(builderForValue.build());
                    this.onChanged();
                } else {
                    this.valueBuilder_.addMessage(builderForValue.build());
                }
                return this;
            }

            public Builder addValue(int index, MysqlxDatatypes.Scalar.Builder builderForValue) {
                if (this.valueBuilder_ == null) {
                    this.ensureValueIsMutable();
                    this.value_.add(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.valueBuilder_.addMessage(index, builderForValue.build());
                }
                return this;
            }

            public Builder addAllValue(Iterable<? extends MysqlxDatatypes.Scalar> values2) {
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
                    this.bitField0_ &= 0xFFFFFFFD;
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

            public MysqlxDatatypes.Scalar.Builder getValueBuilder(int index) {
                return this.getValueFieldBuilder().getBuilder(index);
            }

            @Override
            public MysqlxDatatypes.ScalarOrBuilder getValueOrBuilder(int index) {
                if (this.valueBuilder_ == null) {
                    return this.value_.get(index);
                }
                return this.valueBuilder_.getMessageOrBuilder(index);
            }

            @Override
            public List<? extends MysqlxDatatypes.ScalarOrBuilder> getValueOrBuilderList() {
                if (this.valueBuilder_ != null) {
                    return this.valueBuilder_.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.value_);
            }

            public MysqlxDatatypes.Scalar.Builder addValueBuilder() {
                return this.getValueFieldBuilder().addBuilder(MysqlxDatatypes.Scalar.getDefaultInstance());
            }

            public MysqlxDatatypes.Scalar.Builder addValueBuilder(int index) {
                return this.getValueFieldBuilder().addBuilder(index, MysqlxDatatypes.Scalar.getDefaultInstance());
            }

            public List<MysqlxDatatypes.Scalar.Builder> getValueBuilderList() {
                return this.getValueFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilder<MysqlxDatatypes.Scalar, MysqlxDatatypes.Scalar.Builder, MysqlxDatatypes.ScalarOrBuilder> getValueFieldBuilder() {
                if (this.valueBuilder_ == null) {
                    this.valueBuilder_ = new RepeatedFieldBuilder(this.value_, (this.bitField0_ & 2) == 2, this.getParentForChildren(), this.isClean());
                    this.value_ = null;
                }
                return this.valueBuilder_;
            }
        }

        public static enum Parameter implements ProtocolMessageEnum
        {
            CURRENT_SCHEMA(0, 1),
            ACCOUNT_EXPIRED(1, 2),
            GENERATED_INSERT_ID(2, 3),
            ROWS_AFFECTED(3, 4),
            ROWS_FOUND(4, 5),
            ROWS_MATCHED(5, 6),
            TRX_COMMITTED(6, 7),
            TRX_ROLLEDBACK(7, 9),
            PRODUCED_MESSAGE(8, 10),
            CLIENT_ID_ASSIGNED(9, 11),
            GENERATED_DOCUMENT_IDS(10, 12);

            public static final int CURRENT_SCHEMA_VALUE = 1;
            public static final int ACCOUNT_EXPIRED_VALUE = 2;
            public static final int GENERATED_INSERT_ID_VALUE = 3;
            public static final int ROWS_AFFECTED_VALUE = 4;
            public static final int ROWS_FOUND_VALUE = 5;
            public static final int ROWS_MATCHED_VALUE = 6;
            public static final int TRX_COMMITTED_VALUE = 7;
            public static final int TRX_ROLLEDBACK_VALUE = 9;
            public static final int PRODUCED_MESSAGE_VALUE = 10;
            public static final int CLIENT_ID_ASSIGNED_VALUE = 11;
            public static final int GENERATED_DOCUMENT_IDS_VALUE = 12;
            private static Internal.EnumLiteMap<Parameter> internalValueMap;
            private static final Parameter[] VALUES;
            private final int index;
            private final int value;

            @Override
            public final int getNumber() {
                return this.value;
            }

            public static Parameter valueOf(int value) {
                switch (value) {
                    case 1: {
                        return CURRENT_SCHEMA;
                    }
                    case 2: {
                        return ACCOUNT_EXPIRED;
                    }
                    case 3: {
                        return GENERATED_INSERT_ID;
                    }
                    case 4: {
                        return ROWS_AFFECTED;
                    }
                    case 5: {
                        return ROWS_FOUND;
                    }
                    case 6: {
                        return ROWS_MATCHED;
                    }
                    case 7: {
                        return TRX_COMMITTED;
                    }
                    case 9: {
                        return TRX_ROLLEDBACK;
                    }
                    case 10: {
                        return PRODUCED_MESSAGE;
                    }
                    case 11: {
                        return CLIENT_ID_ASSIGNED;
                    }
                    case 12: {
                        return GENERATED_DOCUMENT_IDS;
                    }
                }
                return null;
            }

            public static Internal.EnumLiteMap<Parameter> internalGetValueMap() {
                return internalValueMap;
            }

            @Override
            public final Descriptors.EnumValueDescriptor getValueDescriptor() {
                return Parameter.getDescriptor().getValues().get(this.index);
            }

            @Override
            public final Descriptors.EnumDescriptor getDescriptorForType() {
                return Parameter.getDescriptor();
            }

            public static final Descriptors.EnumDescriptor getDescriptor() {
                return SessionStateChanged.getDescriptor().getEnumTypes().get(0);
            }

            public static Parameter valueOf(Descriptors.EnumValueDescriptor desc) {
                if (desc.getType() != Parameter.getDescriptor()) {
                    throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
                }
                return VALUES[desc.getIndex()];
            }

            private Parameter(int index, int value) {
                this.index = index;
                this.value = value;
            }

            static {
                internalValueMap = new Internal.EnumLiteMap<Parameter>(){

                    @Override
                    public Parameter findValueByNumber(int number) {
                        return Parameter.valueOf(number);
                    }
                };
                VALUES = Parameter.values();
            }
        }
    }

    public static interface SessionStateChangedOrBuilder
    extends MessageOrBuilder {
        public boolean hasParam();

        public SessionStateChanged.Parameter getParam();

        public List<MysqlxDatatypes.Scalar> getValueList();

        public MysqlxDatatypes.Scalar getValue(int var1);

        public int getValueCount();

        public List<? extends MysqlxDatatypes.ScalarOrBuilder> getValueOrBuilderList();

        public MysqlxDatatypes.ScalarOrBuilder getValueOrBuilder(int var1);
    }

    public static final class SessionVariableChanged
    extends GeneratedMessage
    implements SessionVariableChangedOrBuilder {
        private static final SessionVariableChanged defaultInstance;
        private final UnknownFieldSet unknownFields;
        public static Parser<SessionVariableChanged> PARSER;
        private int bitField0_;
        public static final int PARAM_FIELD_NUMBER = 1;
        private Object param_;
        public static final int VALUE_FIELD_NUMBER = 2;
        private MysqlxDatatypes.Scalar value_;
        private byte memoizedIsInitialized = (byte)-1;
        private int memoizedSerializedSize = -1;
        private static final long serialVersionUID = 0L;

        private SessionVariableChanged(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.unknownFields = builder.getUnknownFields();
        }

        private SessionVariableChanged(boolean noInit) {
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static SessionVariableChanged getDefaultInstance() {
            return defaultInstance;
        }

        @Override
        public SessionVariableChanged getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private SessionVariableChanged(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
                            this.param_ = bs;
                            continue block11;
                        }
                        case 18: 
                    }
                    MysqlxDatatypes.Scalar.Builder subBuilder = null;
                    if ((this.bitField0_ & 2) == 2) {
                        subBuilder = this.value_.toBuilder();
                    }
                    this.value_ = input.readMessage(MysqlxDatatypes.Scalar.PARSER, extensionRegistry);
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
            return internal_static_Mysqlx_Notice_SessionVariableChanged_descriptor;
        }

        @Override
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return internal_static_Mysqlx_Notice_SessionVariableChanged_fieldAccessorTable.ensureFieldAccessorsInitialized(SessionVariableChanged.class, Builder.class);
        }

        public Parser<SessionVariableChanged> getParserForType() {
            return PARSER;
        }

        @Override
        public boolean hasParam() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override
        public String getParam() {
            Object ref = this.param_;
            if (ref instanceof String) {
                return (String)ref;
            }
            ByteString bs = (ByteString)ref;
            String s2 = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.param_ = s2;
            }
            return s2;
        }

        @Override
        public ByteString getParamBytes() {
            Object ref = this.param_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.param_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        @Override
        public boolean hasValue() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override
        public MysqlxDatatypes.Scalar getValue() {
            return this.value_;
        }

        @Override
        public MysqlxDatatypes.ScalarOrBuilder getValueOrBuilder() {
            return this.value_;
        }

        private void initFields() {
            this.param_ = "";
            this.value_ = MysqlxDatatypes.Scalar.getDefaultInstance();
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
            if (!this.hasParam()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            if (this.hasValue() && !this.getValue().isInitialized()) {
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
                output.writeBytes(1, this.getParamBytes());
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
                size += CodedOutputStream.computeBytesSize(1, this.getParamBytes());
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

        public static SessionVariableChanged parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static SessionVariableChanged parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static SessionVariableChanged parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static SessionVariableChanged parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static SessionVariableChanged parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static SessionVariableChanged parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static SessionVariableChanged parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static SessionVariableChanged parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static SessionVariableChanged parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static SessionVariableChanged parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override
        public Builder newBuilderForType() {
            return SessionVariableChanged.newBuilder();
        }

        public static Builder newBuilder(SessionVariableChanged prototype) {
            return SessionVariableChanged.newBuilder().mergeFrom(prototype);
        }

        @Override
        public Builder toBuilder() {
            return SessionVariableChanged.newBuilder(this);
        }

        @Override
        protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        static {
            PARSER = new AbstractParser<SessionVariableChanged>(){

                @Override
                public SessionVariableChanged parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                    return new SessionVariableChanged(input, extensionRegistry);
                }
            };
            defaultInstance = new SessionVariableChanged(true);
            defaultInstance.initFields();
        }

        public static final class Builder
        extends GeneratedMessage.Builder<Builder>
        implements SessionVariableChangedOrBuilder {
            private int bitField0_;
            private Object param_ = "";
            private MysqlxDatatypes.Scalar value_ = MysqlxDatatypes.Scalar.getDefaultInstance();
            private SingleFieldBuilder<MysqlxDatatypes.Scalar, MysqlxDatatypes.Scalar.Builder, MysqlxDatatypes.ScalarOrBuilder> valueBuilder_;

            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_Mysqlx_Notice_SessionVariableChanged_descriptor;
            }

            @Override
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_Mysqlx_Notice_SessionVariableChanged_fieldAccessorTable.ensureFieldAccessorsInitialized(SessionVariableChanged.class, Builder.class);
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
                this.param_ = "";
                this.bitField0_ &= 0xFFFFFFFE;
                if (this.valueBuilder_ == null) {
                    this.value_ = MysqlxDatatypes.Scalar.getDefaultInstance();
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
                return internal_static_Mysqlx_Notice_SessionVariableChanged_descriptor;
            }

            @Override
            public SessionVariableChanged getDefaultInstanceForType() {
                return SessionVariableChanged.getDefaultInstance();
            }

            @Override
            public SessionVariableChanged build() {
                SessionVariableChanged result = this.buildPartial();
                if (!result.isInitialized()) {
                    throw Builder.newUninitializedMessageException(result);
                }
                return result;
            }

            @Override
            public SessionVariableChanged buildPartial() {
                SessionVariableChanged result = new SessionVariableChanged(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ |= 1;
                }
                result.param_ = this.param_;
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
                if (other instanceof SessionVariableChanged) {
                    return this.mergeFrom((SessionVariableChanged)other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(SessionVariableChanged other) {
                if (other == SessionVariableChanged.getDefaultInstance()) {
                    return this;
                }
                if (other.hasParam()) {
                    this.bitField0_ |= 1;
                    this.param_ = other.param_;
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
                if (!this.hasParam()) {
                    return false;
                }
                return !this.hasValue() || this.getValue().isInitialized();
            }

            @Override
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                SessionVariableChanged parsedMessage = null;
                try {
                    parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
                } catch (InvalidProtocolBufferException e) {
                    parsedMessage = (SessionVariableChanged)e.getUnfinishedMessage();
                    throw e;
                } finally {
                    if (parsedMessage != null) {
                        this.mergeFrom(parsedMessage);
                    }
                }
                return this;
            }

            @Override
            public boolean hasParam() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override
            public String getParam() {
                Object ref = this.param_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString)ref;
                    String s2 = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.param_ = s2;
                    }
                    return s2;
                }
                return (String)ref;
            }

            @Override
            public ByteString getParamBytes() {
                Object ref = this.param_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String)ref);
                    this.param_ = b;
                    return b;
                }
                return (ByteString)ref;
            }

            public Builder setParam(String value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 1;
                this.param_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearParam() {
                this.bitField0_ &= 0xFFFFFFFE;
                this.param_ = SessionVariableChanged.getDefaultInstance().getParam();
                this.onChanged();
                return this;
            }

            public Builder setParamBytes(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 1;
                this.param_ = value;
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasValue() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override
            public MysqlxDatatypes.Scalar getValue() {
                if (this.valueBuilder_ == null) {
                    return this.value_;
                }
                return this.valueBuilder_.getMessage();
            }

            public Builder setValue(MysqlxDatatypes.Scalar value) {
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

            public Builder setValue(MysqlxDatatypes.Scalar.Builder builderForValue) {
                if (this.valueBuilder_ == null) {
                    this.value_ = builderForValue.build();
                    this.onChanged();
                } else {
                    this.valueBuilder_.setMessage(builderForValue.build());
                }
                this.bitField0_ |= 2;
                return this;
            }

            public Builder mergeValue(MysqlxDatatypes.Scalar value) {
                if (this.valueBuilder_ == null) {
                    this.value_ = (this.bitField0_ & 2) == 2 && this.value_ != MysqlxDatatypes.Scalar.getDefaultInstance() ? MysqlxDatatypes.Scalar.newBuilder(this.value_).mergeFrom(value).buildPartial() : value;
                    this.onChanged();
                } else {
                    this.valueBuilder_.mergeFrom(value);
                }
                this.bitField0_ |= 2;
                return this;
            }

            public Builder clearValue() {
                if (this.valueBuilder_ == null) {
                    this.value_ = MysqlxDatatypes.Scalar.getDefaultInstance();
                    this.onChanged();
                } else {
                    this.valueBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFFFD;
                return this;
            }

            public MysqlxDatatypes.Scalar.Builder getValueBuilder() {
                this.bitField0_ |= 2;
                this.onChanged();
                return this.getValueFieldBuilder().getBuilder();
            }

            @Override
            public MysqlxDatatypes.ScalarOrBuilder getValueOrBuilder() {
                if (this.valueBuilder_ != null) {
                    return this.valueBuilder_.getMessageOrBuilder();
                }
                return this.value_;
            }

            private SingleFieldBuilder<MysqlxDatatypes.Scalar, MysqlxDatatypes.Scalar.Builder, MysqlxDatatypes.ScalarOrBuilder> getValueFieldBuilder() {
                if (this.valueBuilder_ == null) {
                    this.valueBuilder_ = new SingleFieldBuilder(this.getValue(), this.getParentForChildren(), this.isClean());
                    this.value_ = null;
                }
                return this.valueBuilder_;
            }
        }
    }

    public static interface SessionVariableChangedOrBuilder
    extends MessageOrBuilder {
        public boolean hasParam();

        public String getParam();

        public ByteString getParamBytes();

        public boolean hasValue();

        public MysqlxDatatypes.Scalar getValue();

        public MysqlxDatatypes.ScalarOrBuilder getValueOrBuilder();
    }

    public static final class Warning
    extends GeneratedMessage
    implements WarningOrBuilder {
        private static final Warning defaultInstance;
        private final UnknownFieldSet unknownFields;
        public static Parser<Warning> PARSER;
        private int bitField0_;
        public static final int LEVEL_FIELD_NUMBER = 1;
        private Level level_;
        public static final int CODE_FIELD_NUMBER = 2;
        private int code_;
        public static final int MSG_FIELD_NUMBER = 3;
        private Object msg_;
        private byte memoizedIsInitialized = (byte)-1;
        private int memoizedSerializedSize = -1;
        private static final long serialVersionUID = 0L;

        private Warning(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.unknownFields = builder.getUnknownFields();
        }

        private Warning(boolean noInit) {
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static Warning getDefaultInstance() {
            return defaultInstance;
        }

        @Override
        public Warning getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private Warning(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
                            int rawValue = input.readEnum();
                            Level value = Level.valueOf(rawValue);
                            if (value == null) {
                                unknownFields.mergeVarintField(1, rawValue);
                                continue block12;
                            }
                            this.bitField0_ |= 1;
                            this.level_ = value;
                            continue block12;
                        }
                        case 16: {
                            this.bitField0_ |= 2;
                            this.code_ = input.readUInt32();
                            continue block12;
                        }
                        case 26: 
                    }
                    ByteString bs = input.readBytes();
                    this.bitField0_ |= 4;
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
            return internal_static_Mysqlx_Notice_Warning_descriptor;
        }

        @Override
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return internal_static_Mysqlx_Notice_Warning_fieldAccessorTable.ensureFieldAccessorsInitialized(Warning.class, Builder.class);
        }

        public Parser<Warning> getParserForType() {
            return PARSER;
        }

        @Override
        public boolean hasLevel() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override
        public Level getLevel() {
            return this.level_;
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
        public boolean hasMsg() {
            return (this.bitField0_ & 4) == 4;
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
            this.level_ = Level.WARNING;
            this.code_ = 0;
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
                output.writeEnum(1, this.level_.getNumber());
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeUInt32(2, this.code_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeBytes(3, this.getMsgBytes());
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
                size += CodedOutputStream.computeEnumSize(1, this.level_.getNumber());
            }
            if ((this.bitField0_ & 2) == 2) {
                size += CodedOutputStream.computeUInt32Size(2, this.code_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size += CodedOutputStream.computeBytesSize(3, this.getMsgBytes());
            }
            this.memoizedSerializedSize = size += this.getUnknownFields().getSerializedSize();
            return size;
        }

        @Override
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static Warning parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static Warning parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static Warning parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static Warning parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static Warning parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static Warning parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Warning parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static Warning parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static Warning parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static Warning parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override
        public Builder newBuilderForType() {
            return Warning.newBuilder();
        }

        public static Builder newBuilder(Warning prototype) {
            return Warning.newBuilder().mergeFrom(prototype);
        }

        @Override
        public Builder toBuilder() {
            return Warning.newBuilder(this);
        }

        @Override
        protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        static {
            PARSER = new AbstractParser<Warning>(){

                @Override
                public Warning parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                    return new Warning(input, extensionRegistry);
                }
            };
            defaultInstance = new Warning(true);
            defaultInstance.initFields();
        }

        public static final class Builder
        extends GeneratedMessage.Builder<Builder>
        implements WarningOrBuilder {
            private int bitField0_;
            private Level level_ = Level.WARNING;
            private int code_;
            private Object msg_ = "";

            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_Mysqlx_Notice_Warning_descriptor;
            }

            @Override
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_Mysqlx_Notice_Warning_fieldAccessorTable.ensureFieldAccessorsInitialized(Warning.class, Builder.class);
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
                this.level_ = Level.WARNING;
                this.bitField0_ &= 0xFFFFFFFE;
                this.code_ = 0;
                this.bitField0_ &= 0xFFFFFFFD;
                this.msg_ = "";
                this.bitField0_ &= 0xFFFFFFFB;
                return this;
            }

            @Override
            public Builder clone() {
                return Builder.create().mergeFrom(this.buildPartial());
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return internal_static_Mysqlx_Notice_Warning_descriptor;
            }

            @Override
            public Warning getDefaultInstanceForType() {
                return Warning.getDefaultInstance();
            }

            @Override
            public Warning build() {
                Warning result = this.buildPartial();
                if (!result.isInitialized()) {
                    throw Builder.newUninitializedMessageException(result);
                }
                return result;
            }

            @Override
            public Warning buildPartial() {
                Warning result = new Warning(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ |= 1;
                }
                result.level_ = this.level_;
                if ((from_bitField0_ & 2) == 2) {
                    to_bitField0_ |= 2;
                }
                result.code_ = this.code_;
                if ((from_bitField0_ & 4) == 4) {
                    to_bitField0_ |= 4;
                }
                result.msg_ = this.msg_;
                result.bitField0_ = to_bitField0_;
                this.onBuilt();
                return result;
            }

            @Override
            public Builder mergeFrom(Message other) {
                if (other instanceof Warning) {
                    return this.mergeFrom((Warning)other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(Warning other) {
                if (other == Warning.getDefaultInstance()) {
                    return this;
                }
                if (other.hasLevel()) {
                    this.setLevel(other.getLevel());
                }
                if (other.hasCode()) {
                    this.setCode(other.getCode());
                }
                if (other.hasMsg()) {
                    this.bitField0_ |= 4;
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
                return this.hasMsg();
            }

            @Override
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                Warning parsedMessage = null;
                try {
                    parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
                } catch (InvalidProtocolBufferException e) {
                    parsedMessage = (Warning)e.getUnfinishedMessage();
                    throw e;
                } finally {
                    if (parsedMessage != null) {
                        this.mergeFrom(parsedMessage);
                    }
                }
                return this;
            }

            @Override
            public boolean hasLevel() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override
            public Level getLevel() {
                return this.level_;
            }

            public Builder setLevel(Level value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 1;
                this.level_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearLevel() {
                this.bitField0_ &= 0xFFFFFFFE;
                this.level_ = Level.WARNING;
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
            public boolean hasMsg() {
                return (this.bitField0_ & 4) == 4;
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
                this.bitField0_ |= 4;
                this.msg_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearMsg() {
                this.bitField0_ &= 0xFFFFFFFB;
                this.msg_ = Warning.getDefaultInstance().getMsg();
                this.onChanged();
                return this;
            }

            public Builder setMsgBytes(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 4;
                this.msg_ = value;
                this.onChanged();
                return this;
            }
        }

        public static enum Level implements ProtocolMessageEnum
        {
            NOTE(0, 1),
            WARNING(1, 2),
            ERROR(2, 3);

            public static final int NOTE_VALUE = 1;
            public static final int WARNING_VALUE = 2;
            public static final int ERROR_VALUE = 3;
            private static Internal.EnumLiteMap<Level> internalValueMap;
            private static final Level[] VALUES;
            private final int index;
            private final int value;

            @Override
            public final int getNumber() {
                return this.value;
            }

            public static Level valueOf(int value) {
                switch (value) {
                    case 1: {
                        return NOTE;
                    }
                    case 2: {
                        return WARNING;
                    }
                    case 3: {
                        return ERROR;
                    }
                }
                return null;
            }

            public static Internal.EnumLiteMap<Level> internalGetValueMap() {
                return internalValueMap;
            }

            @Override
            public final Descriptors.EnumValueDescriptor getValueDescriptor() {
                return Level.getDescriptor().getValues().get(this.index);
            }

            @Override
            public final Descriptors.EnumDescriptor getDescriptorForType() {
                return Level.getDescriptor();
            }

            public static final Descriptors.EnumDescriptor getDescriptor() {
                return Warning.getDescriptor().getEnumTypes().get(0);
            }

            public static Level valueOf(Descriptors.EnumValueDescriptor desc) {
                if (desc.getType() != Level.getDescriptor()) {
                    throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
                }
                return VALUES[desc.getIndex()];
            }

            private Level(int index, int value) {
                this.index = index;
                this.value = value;
            }

            static {
                internalValueMap = new Internal.EnumLiteMap<Level>(){

                    @Override
                    public Level findValueByNumber(int number) {
                        return Level.valueOf(number);
                    }
                };
                VALUES = Level.values();
            }
        }
    }

    public static interface WarningOrBuilder
    extends MessageOrBuilder {
        public boolean hasLevel();

        public Warning.Level getLevel();

        public boolean hasCode();

        public int getCode();

        public boolean hasMsg();

        public String getMsg();

        public ByteString getMsgBytes();
    }

    public static final class Frame
    extends GeneratedMessage
    implements FrameOrBuilder {
        private static final Frame defaultInstance;
        private final UnknownFieldSet unknownFields;
        public static Parser<Frame> PARSER;
        private int bitField0_;
        public static final int TYPE_FIELD_NUMBER = 1;
        private int type_;
        public static final int SCOPE_FIELD_NUMBER = 2;
        private Scope scope_;
        public static final int PAYLOAD_FIELD_NUMBER = 3;
        private ByteString payload_;
        private byte memoizedIsInitialized = (byte)-1;
        private int memoizedSerializedSize = -1;
        private static final long serialVersionUID = 0L;

        private Frame(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.unknownFields = builder.getUnknownFields();
        }

        private Frame(boolean noInit) {
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static Frame getDefaultInstance() {
            return defaultInstance;
        }

        @Override
        public Frame getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private Frame(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
                            this.type_ = input.readUInt32();
                            continue block12;
                        }
                        case 16: {
                            int rawValue = input.readEnum();
                            Scope value = Scope.valueOf(rawValue);
                            if (value == null) {
                                unknownFields.mergeVarintField(2, rawValue);
                                continue block12;
                            }
                            this.bitField0_ |= 2;
                            this.scope_ = value;
                            continue block12;
                        }
                        case 26: 
                    }
                    this.bitField0_ |= 4;
                    this.payload_ = input.readBytes();
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
            return internal_static_Mysqlx_Notice_Frame_descriptor;
        }

        @Override
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return internal_static_Mysqlx_Notice_Frame_fieldAccessorTable.ensureFieldAccessorsInitialized(Frame.class, Builder.class);
        }

        public Parser<Frame> getParserForType() {
            return PARSER;
        }

        @Override
        public boolean hasType() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override
        public int getType() {
            return this.type_;
        }

        @Override
        public boolean hasScope() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override
        public Scope getScope() {
            return this.scope_;
        }

        @Override
        public boolean hasPayload() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override
        public ByteString getPayload() {
            return this.payload_;
        }

        private void initFields() {
            this.type_ = 0;
            this.scope_ = Scope.GLOBAL;
            this.payload_ = ByteString.EMPTY;
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
            this.memoizedIsInitialized = 1;
            return true;
        }

        @Override
        public void writeTo(CodedOutputStream output) throws IOException {
            this.getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                output.writeUInt32(1, this.type_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeEnum(2, this.scope_.getNumber());
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeBytes(3, this.payload_);
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
                size += CodedOutputStream.computeUInt32Size(1, this.type_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size += CodedOutputStream.computeEnumSize(2, this.scope_.getNumber());
            }
            if ((this.bitField0_ & 4) == 4) {
                size += CodedOutputStream.computeBytesSize(3, this.payload_);
            }
            this.memoizedSerializedSize = size += this.getUnknownFields().getSerializedSize();
            return size;
        }

        @Override
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static Frame parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static Frame parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static Frame parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static Frame parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static Frame parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static Frame parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Frame parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static Frame parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static Frame parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static Frame parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override
        public Builder newBuilderForType() {
            return Frame.newBuilder();
        }

        public static Builder newBuilder(Frame prototype) {
            return Frame.newBuilder().mergeFrom(prototype);
        }

        @Override
        public Builder toBuilder() {
            return Frame.newBuilder(this);
        }

        @Override
        protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        static {
            PARSER = new AbstractParser<Frame>(){

                @Override
                public Frame parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                    return new Frame(input, extensionRegistry);
                }
            };
            defaultInstance = new Frame(true);
            defaultInstance.initFields();
        }

        public static final class Builder
        extends GeneratedMessage.Builder<Builder>
        implements FrameOrBuilder {
            private int bitField0_;
            private int type_;
            private Scope scope_ = Scope.GLOBAL;
            private ByteString payload_ = ByteString.EMPTY;

            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_Mysqlx_Notice_Frame_descriptor;
            }

            @Override
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_Mysqlx_Notice_Frame_fieldAccessorTable.ensureFieldAccessorsInitialized(Frame.class, Builder.class);
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
                this.type_ = 0;
                this.bitField0_ &= 0xFFFFFFFE;
                this.scope_ = Scope.GLOBAL;
                this.bitField0_ &= 0xFFFFFFFD;
                this.payload_ = ByteString.EMPTY;
                this.bitField0_ &= 0xFFFFFFFB;
                return this;
            }

            @Override
            public Builder clone() {
                return Builder.create().mergeFrom(this.buildPartial());
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return internal_static_Mysqlx_Notice_Frame_descriptor;
            }

            @Override
            public Frame getDefaultInstanceForType() {
                return Frame.getDefaultInstance();
            }

            @Override
            public Frame build() {
                Frame result = this.buildPartial();
                if (!result.isInitialized()) {
                    throw Builder.newUninitializedMessageException(result);
                }
                return result;
            }

            @Override
            public Frame buildPartial() {
                Frame result = new Frame(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ |= 1;
                }
                result.type_ = this.type_;
                if ((from_bitField0_ & 2) == 2) {
                    to_bitField0_ |= 2;
                }
                result.scope_ = this.scope_;
                if ((from_bitField0_ & 4) == 4) {
                    to_bitField0_ |= 4;
                }
                result.payload_ = this.payload_;
                result.bitField0_ = to_bitField0_;
                this.onBuilt();
                return result;
            }

            @Override
            public Builder mergeFrom(Message other) {
                if (other instanceof Frame) {
                    return this.mergeFrom((Frame)other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(Frame other) {
                if (other == Frame.getDefaultInstance()) {
                    return this;
                }
                if (other.hasType()) {
                    this.setType(other.getType());
                }
                if (other.hasScope()) {
                    this.setScope(other.getScope());
                }
                if (other.hasPayload()) {
                    this.setPayload(other.getPayload());
                }
                this.mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            @Override
            public final boolean isInitialized() {
                return this.hasType();
            }

            @Override
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                Frame parsedMessage = null;
                try {
                    parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
                } catch (InvalidProtocolBufferException e) {
                    parsedMessage = (Frame)e.getUnfinishedMessage();
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
            public int getType() {
                return this.type_;
            }

            public Builder setType(int value) {
                this.bitField0_ |= 1;
                this.type_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearType() {
                this.bitField0_ &= 0xFFFFFFFE;
                this.type_ = 0;
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasScope() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override
            public Scope getScope() {
                return this.scope_;
            }

            public Builder setScope(Scope value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 2;
                this.scope_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearScope() {
                this.bitField0_ &= 0xFFFFFFFD;
                this.scope_ = Scope.GLOBAL;
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasPayload() {
                return (this.bitField0_ & 4) == 4;
            }

            @Override
            public ByteString getPayload() {
                return this.payload_;
            }

            public Builder setPayload(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 4;
                this.payload_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearPayload() {
                this.bitField0_ &= 0xFFFFFFFB;
                this.payload_ = Frame.getDefaultInstance().getPayload();
                this.onChanged();
                return this;
            }
        }

        public static enum Type implements ProtocolMessageEnum
        {
            WARNING(0, 1),
            SESSION_VARIABLE_CHANGED(1, 2),
            SESSION_STATE_CHANGED(2, 3);

            public static final int WARNING_VALUE = 1;
            public static final int SESSION_VARIABLE_CHANGED_VALUE = 2;
            public static final int SESSION_STATE_CHANGED_VALUE = 3;
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
                        return WARNING;
                    }
                    case 2: {
                        return SESSION_VARIABLE_CHANGED;
                    }
                    case 3: {
                        return SESSION_STATE_CHANGED;
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
                return Frame.getDescriptor().getEnumTypes().get(1);
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

        public static enum Scope implements ProtocolMessageEnum
        {
            GLOBAL(0, 1),
            LOCAL(1, 2);

            public static final int GLOBAL_VALUE = 1;
            public static final int LOCAL_VALUE = 2;
            private static Internal.EnumLiteMap<Scope> internalValueMap;
            private static final Scope[] VALUES;
            private final int index;
            private final int value;

            @Override
            public final int getNumber() {
                return this.value;
            }

            public static Scope valueOf(int value) {
                switch (value) {
                    case 1: {
                        return GLOBAL;
                    }
                    case 2: {
                        return LOCAL;
                    }
                }
                return null;
            }

            public static Internal.EnumLiteMap<Scope> internalGetValueMap() {
                return internalValueMap;
            }

            @Override
            public final Descriptors.EnumValueDescriptor getValueDescriptor() {
                return Scope.getDescriptor().getValues().get(this.index);
            }

            @Override
            public final Descriptors.EnumDescriptor getDescriptorForType() {
                return Scope.getDescriptor();
            }

            public static final Descriptors.EnumDescriptor getDescriptor() {
                return Frame.getDescriptor().getEnumTypes().get(0);
            }

            public static Scope valueOf(Descriptors.EnumValueDescriptor desc) {
                if (desc.getType() != Scope.getDescriptor()) {
                    throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
                }
                return VALUES[desc.getIndex()];
            }

            private Scope(int index, int value) {
                this.index = index;
                this.value = value;
            }

            static {
                internalValueMap = new Internal.EnumLiteMap<Scope>(){

                    @Override
                    public Scope findValueByNumber(int number) {
                        return Scope.valueOf(number);
                    }
                };
                VALUES = Scope.values();
            }
        }
    }

    public static interface FrameOrBuilder
    extends MessageOrBuilder {
        public boolean hasType();

        public int getType();

        public boolean hasScope();

        public Frame.Scope getScope();

        public boolean hasPayload();

        public ByteString getPayload();
    }
}

