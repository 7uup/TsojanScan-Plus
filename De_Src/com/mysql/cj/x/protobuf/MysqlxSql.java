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
import com.google.protobuf.UnknownFieldSet;
import com.mysql.cj.x.protobuf.Mysqlx;
import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectStreamException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class MysqlxSql {
    private static final Descriptors.Descriptor internal_static_Mysqlx_Sql_StmtExecute_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_Mysqlx_Sql_StmtExecute_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_Mysqlx_Sql_StmtExecuteOk_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_Mysqlx_Sql_StmtExecuteOk_fieldAccessorTable;
    private static Descriptors.FileDescriptor descriptor;

    private MysqlxSql() {
    }

    public static void registerAllExtensions(ExtensionRegistry registry) {
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        String[] descriptorData = new String[]{"\n\u0010mysqlx_sql.proto\u0012\nMysqlx.Sql\u001a\fmysqlx.proto\u001a\u0016mysqlx_datatypes.proto\"\u007f\n\u000bStmtExecute\u0012\u0016\n\tnamespace\u0018\u0003 \u0001(\t:\u0003sql\u0012\f\n\u0004stmt\u0018\u0001 \u0002(\f\u0012#\n\u0004args\u0018\u0002 \u0003(\u000b2\u0015.Mysqlx.Datatypes.Any\u0012\u001f\n\u0010compact_metadata\u0018\u0004 \u0001(\b:\u0005false:\u0004\u0088\u00ea0\f\"\u0015\n\rStmtExecuteOk:\u0004\u0090\u00ea0\u0011B\u0019\n\u0017com.mysql.cj.x.protobuf"};
        Descriptors.FileDescriptor.InternalDescriptorAssigner assigner = new Descriptors.FileDescriptor.InternalDescriptorAssigner(){

            @Override
            public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor root) {
                descriptor = root;
                return null;
            }
        };
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[]{Mysqlx.getDescriptor(), MysqlxDatatypes.getDescriptor()}, assigner);
        internal_static_Mysqlx_Sql_StmtExecute_descriptor = MysqlxSql.getDescriptor().getMessageTypes().get(0);
        internal_static_Mysqlx_Sql_StmtExecute_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_Mysqlx_Sql_StmtExecute_descriptor, new String[]{"Namespace", "Stmt", "Args", "CompactMetadata"});
        internal_static_Mysqlx_Sql_StmtExecuteOk_descriptor = MysqlxSql.getDescriptor().getMessageTypes().get(1);
        internal_static_Mysqlx_Sql_StmtExecuteOk_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_Mysqlx_Sql_StmtExecuteOk_descriptor, new String[0]);
        ExtensionRegistry registry = ExtensionRegistry.newInstance();
        registry.add(Mysqlx.clientMessageId);
        registry.add(Mysqlx.serverMessageId);
        Descriptors.FileDescriptor.internalUpdateFileDescriptor(descriptor, registry);
        Mysqlx.getDescriptor();
        MysqlxDatatypes.getDescriptor();
    }

    public static final class StmtExecuteOk
    extends GeneratedMessage
    implements StmtExecuteOkOrBuilder {
        private static final StmtExecuteOk defaultInstance;
        private final UnknownFieldSet unknownFields;
        public static Parser<StmtExecuteOk> PARSER;
        private byte memoizedIsInitialized = (byte)-1;
        private int memoizedSerializedSize = -1;
        private static final long serialVersionUID = 0L;

        private StmtExecuteOk(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.unknownFields = builder.getUnknownFields();
        }

        private StmtExecuteOk(boolean noInit) {
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static StmtExecuteOk getDefaultInstance() {
            return defaultInstance;
        }

        @Override
        public StmtExecuteOk getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private StmtExecuteOk(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
            return internal_static_Mysqlx_Sql_StmtExecuteOk_descriptor;
        }

        @Override
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return internal_static_Mysqlx_Sql_StmtExecuteOk_fieldAccessorTable.ensureFieldAccessorsInitialized(StmtExecuteOk.class, Builder.class);
        }

        public Parser<StmtExecuteOk> getParserForType() {
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

        public static StmtExecuteOk parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static StmtExecuteOk parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static StmtExecuteOk parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static StmtExecuteOk parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static StmtExecuteOk parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static StmtExecuteOk parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static StmtExecuteOk parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static StmtExecuteOk parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static StmtExecuteOk parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static StmtExecuteOk parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override
        public Builder newBuilderForType() {
            return StmtExecuteOk.newBuilder();
        }

        public static Builder newBuilder(StmtExecuteOk prototype) {
            return StmtExecuteOk.newBuilder().mergeFrom(prototype);
        }

        @Override
        public Builder toBuilder() {
            return StmtExecuteOk.newBuilder(this);
        }

        @Override
        protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        static {
            PARSER = new AbstractParser<StmtExecuteOk>(){

                @Override
                public StmtExecuteOk parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                    return new StmtExecuteOk(input, extensionRegistry);
                }
            };
            defaultInstance = new StmtExecuteOk(true);
            defaultInstance.initFields();
        }

        public static final class Builder
        extends GeneratedMessage.Builder<Builder>
        implements StmtExecuteOkOrBuilder {
            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_Mysqlx_Sql_StmtExecuteOk_descriptor;
            }

            @Override
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_Mysqlx_Sql_StmtExecuteOk_fieldAccessorTable.ensureFieldAccessorsInitialized(StmtExecuteOk.class, Builder.class);
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
                return internal_static_Mysqlx_Sql_StmtExecuteOk_descriptor;
            }

            @Override
            public StmtExecuteOk getDefaultInstanceForType() {
                return StmtExecuteOk.getDefaultInstance();
            }

            @Override
            public StmtExecuteOk build() {
                StmtExecuteOk result = this.buildPartial();
                if (!result.isInitialized()) {
                    throw Builder.newUninitializedMessageException(result);
                }
                return result;
            }

            @Override
            public StmtExecuteOk buildPartial() {
                StmtExecuteOk result = new StmtExecuteOk(this);
                this.onBuilt();
                return result;
            }

            @Override
            public Builder mergeFrom(Message other) {
                if (other instanceof StmtExecuteOk) {
                    return this.mergeFrom((StmtExecuteOk)other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(StmtExecuteOk other) {
                if (other == StmtExecuteOk.getDefaultInstance()) {
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
                StmtExecuteOk parsedMessage = null;
                try {
                    parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
                } catch (InvalidProtocolBufferException e) {
                    parsedMessage = (StmtExecuteOk)e.getUnfinishedMessage();
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

    public static interface StmtExecuteOkOrBuilder
    extends MessageOrBuilder {
    }

    public static final class StmtExecute
    extends GeneratedMessage
    implements StmtExecuteOrBuilder {
        private static final StmtExecute defaultInstance;
        private final UnknownFieldSet unknownFields;
        public static Parser<StmtExecute> PARSER;
        private int bitField0_;
        public static final int NAMESPACE_FIELD_NUMBER = 3;
        private Object namespace_;
        public static final int STMT_FIELD_NUMBER = 1;
        private ByteString stmt_;
        public static final int ARGS_FIELD_NUMBER = 2;
        private List<MysqlxDatatypes.Any> args_;
        public static final int COMPACT_METADATA_FIELD_NUMBER = 4;
        private boolean compactMetadata_;
        private byte memoizedIsInitialized = (byte)-1;
        private int memoizedSerializedSize = -1;
        private static final long serialVersionUID = 0L;

        private StmtExecute(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.unknownFields = builder.getUnknownFields();
        }

        private StmtExecute(boolean noInit) {
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static StmtExecute getDefaultInstance() {
            return defaultInstance;
        }

        @Override
        public StmtExecute getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private StmtExecute(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this.initFields();
            int mutable_bitField0_ = 0;
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
                        case 10: {
                            this.bitField0_ |= 2;
                            this.stmt_ = input.readBytes();
                            continue block13;
                        }
                        case 18: {
                            if ((mutable_bitField0_ & 4) != 4) {
                                this.args_ = new ArrayList<MysqlxDatatypes.Any>();
                                mutable_bitField0_ |= 4;
                            }
                            this.args_.add(input.readMessage(MysqlxDatatypes.Any.PARSER, extensionRegistry));
                            continue block13;
                        }
                        case 26: {
                            ByteString bs = input.readBytes();
                            this.bitField0_ |= 1;
                            this.namespace_ = bs;
                            continue block13;
                        }
                        case 32: 
                    }
                    this.bitField0_ |= 4;
                    this.compactMetadata_ = input.readBool();
                }
            } catch (InvalidProtocolBufferException e) {
                throw e.setUnfinishedMessage(this);
            } catch (IOException e) {
                throw new InvalidProtocolBufferException(e.getMessage()).setUnfinishedMessage(this);
            } finally {
                if ((mutable_bitField0_ & 4) == 4) {
                    this.args_ = Collections.unmodifiableList(this.args_);
                }
                this.unknownFields = unknownFields.build();
                this.makeExtensionsImmutable();
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return internal_static_Mysqlx_Sql_StmtExecute_descriptor;
        }

        @Override
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return internal_static_Mysqlx_Sql_StmtExecute_fieldAccessorTable.ensureFieldAccessorsInitialized(StmtExecute.class, Builder.class);
        }

        public Parser<StmtExecute> getParserForType() {
            return PARSER;
        }

        @Override
        public boolean hasNamespace() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override
        public String getNamespace() {
            Object ref = this.namespace_;
            if (ref instanceof String) {
                return (String)ref;
            }
            ByteString bs = (ByteString)ref;
            String s2 = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.namespace_ = s2;
            }
            return s2;
        }

        @Override
        public ByteString getNamespaceBytes() {
            Object ref = this.namespace_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.namespace_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        @Override
        public boolean hasStmt() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override
        public ByteString getStmt() {
            return this.stmt_;
        }

        @Override
        public List<MysqlxDatatypes.Any> getArgsList() {
            return this.args_;
        }

        @Override
        public List<? extends MysqlxDatatypes.AnyOrBuilder> getArgsOrBuilderList() {
            return this.args_;
        }

        @Override
        public int getArgsCount() {
            return this.args_.size();
        }

        @Override
        public MysqlxDatatypes.Any getArgs(int index) {
            return this.args_.get(index);
        }

        @Override
        public MysqlxDatatypes.AnyOrBuilder getArgsOrBuilder(int index) {
            return this.args_.get(index);
        }

        @Override
        public boolean hasCompactMetadata() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override
        public boolean getCompactMetadata() {
            return this.compactMetadata_;
        }

        private void initFields() {
            this.namespace_ = "sql";
            this.stmt_ = ByteString.EMPTY;
            this.args_ = Collections.emptyList();
            this.compactMetadata_ = false;
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
            if (!this.hasStmt()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            for (int i = 0; i < this.getArgsCount(); ++i) {
                if (this.getArgs(i).isInitialized()) continue;
                this.memoizedIsInitialized = 0;
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        @Override
        public void writeTo(CodedOutputStream output) throws IOException {
            this.getSerializedSize();
            if ((this.bitField0_ & 2) == 2) {
                output.writeBytes(1, this.stmt_);
            }
            for (int i = 0; i < this.args_.size(); ++i) {
                output.writeMessage(2, this.args_.get(i));
            }
            if ((this.bitField0_ & 1) == 1) {
                output.writeBytes(3, this.getNamespaceBytes());
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeBool(4, this.compactMetadata_);
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
            if ((this.bitField0_ & 2) == 2) {
                size += CodedOutputStream.computeBytesSize(1, this.stmt_);
            }
            for (int i = 0; i < this.args_.size(); ++i) {
                size += CodedOutputStream.computeMessageSize(2, this.args_.get(i));
            }
            if ((this.bitField0_ & 1) == 1) {
                size += CodedOutputStream.computeBytesSize(3, this.getNamespaceBytes());
            }
            if ((this.bitField0_ & 4) == 4) {
                size += CodedOutputStream.computeBoolSize(4, this.compactMetadata_);
            }
            this.memoizedSerializedSize = size += this.getUnknownFields().getSerializedSize();
            return size;
        }

        @Override
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static StmtExecute parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static StmtExecute parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static StmtExecute parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static StmtExecute parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static StmtExecute parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static StmtExecute parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static StmtExecute parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static StmtExecute parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static StmtExecute parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static StmtExecute parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override
        public Builder newBuilderForType() {
            return StmtExecute.newBuilder();
        }

        public static Builder newBuilder(StmtExecute prototype) {
            return StmtExecute.newBuilder().mergeFrom(prototype);
        }

        @Override
        public Builder toBuilder() {
            return StmtExecute.newBuilder(this);
        }

        @Override
        protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        static {
            PARSER = new AbstractParser<StmtExecute>(){

                @Override
                public StmtExecute parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                    return new StmtExecute(input, extensionRegistry);
                }
            };
            defaultInstance = new StmtExecute(true);
            defaultInstance.initFields();
        }

        public static final class Builder
        extends GeneratedMessage.Builder<Builder>
        implements StmtExecuteOrBuilder {
            private int bitField0_;
            private Object namespace_ = "sql";
            private ByteString stmt_ = ByteString.EMPTY;
            private List<MysqlxDatatypes.Any> args_ = Collections.emptyList();
            private RepeatedFieldBuilder<MysqlxDatatypes.Any, MysqlxDatatypes.Any.Builder, MysqlxDatatypes.AnyOrBuilder> argsBuilder_;
            private boolean compactMetadata_;

            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_Mysqlx_Sql_StmtExecute_descriptor;
            }

            @Override
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_Mysqlx_Sql_StmtExecute_fieldAccessorTable.ensureFieldAccessorsInitialized(StmtExecute.class, Builder.class);
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
                    this.getArgsFieldBuilder();
                }
            }

            private static Builder create() {
                return new Builder();
            }

            @Override
            public Builder clear() {
                super.clear();
                this.namespace_ = "sql";
                this.bitField0_ &= 0xFFFFFFFE;
                this.stmt_ = ByteString.EMPTY;
                this.bitField0_ &= 0xFFFFFFFD;
                if (this.argsBuilder_ == null) {
                    this.args_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFFFFB;
                } else {
                    this.argsBuilder_.clear();
                }
                this.compactMetadata_ = false;
                this.bitField0_ &= 0xFFFFFFF7;
                return this;
            }

            @Override
            public Builder clone() {
                return Builder.create().mergeFrom(this.buildPartial());
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return internal_static_Mysqlx_Sql_StmtExecute_descriptor;
            }

            @Override
            public StmtExecute getDefaultInstanceForType() {
                return StmtExecute.getDefaultInstance();
            }

            @Override
            public StmtExecute build() {
                StmtExecute result = this.buildPartial();
                if (!result.isInitialized()) {
                    throw Builder.newUninitializedMessageException(result);
                }
                return result;
            }

            @Override
            public StmtExecute buildPartial() {
                StmtExecute result = new StmtExecute(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ |= 1;
                }
                result.namespace_ = this.namespace_;
                if ((from_bitField0_ & 2) == 2) {
                    to_bitField0_ |= 2;
                }
                result.stmt_ = this.stmt_;
                if (this.argsBuilder_ == null) {
                    if ((this.bitField0_ & 4) == 4) {
                        this.args_ = Collections.unmodifiableList(this.args_);
                        this.bitField0_ &= 0xFFFFFFFB;
                    }
                    result.args_ = this.args_;
                } else {
                    result.args_ = this.argsBuilder_.build();
                }
                if ((from_bitField0_ & 8) == 8) {
                    to_bitField0_ |= 4;
                }
                result.compactMetadata_ = this.compactMetadata_;
                result.bitField0_ = to_bitField0_;
                this.onBuilt();
                return result;
            }

            @Override
            public Builder mergeFrom(Message other) {
                if (other instanceof StmtExecute) {
                    return this.mergeFrom((StmtExecute)other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(StmtExecute other) {
                if (other == StmtExecute.getDefaultInstance()) {
                    return this;
                }
                if (other.hasNamespace()) {
                    this.bitField0_ |= 1;
                    this.namespace_ = other.namespace_;
                    this.onChanged();
                }
                if (other.hasStmt()) {
                    this.setStmt(other.getStmt());
                }
                if (this.argsBuilder_ == null) {
                    if (!other.args_.isEmpty()) {
                        if (this.args_.isEmpty()) {
                            this.args_ = other.args_;
                            this.bitField0_ &= 0xFFFFFFFB;
                        } else {
                            this.ensureArgsIsMutable();
                            this.args_.addAll(other.args_);
                        }
                        this.onChanged();
                    }
                } else if (!other.args_.isEmpty()) {
                    if (this.argsBuilder_.isEmpty()) {
                        this.argsBuilder_.dispose();
                        this.argsBuilder_ = null;
                        this.args_ = other.args_;
                        this.bitField0_ &= 0xFFFFFFFB;
                        this.argsBuilder_ = alwaysUseFieldBuilders ? this.getArgsFieldBuilder() : null;
                    } else {
                        this.argsBuilder_.addAllMessages(other.args_);
                    }
                }
                if (other.hasCompactMetadata()) {
                    this.setCompactMetadata(other.getCompactMetadata());
                }
                this.mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            @Override
            public final boolean isInitialized() {
                if (!this.hasStmt()) {
                    return false;
                }
                for (int i = 0; i < this.getArgsCount(); ++i) {
                    if (this.getArgs(i).isInitialized()) continue;
                    return false;
                }
                return true;
            }

            @Override
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                StmtExecute parsedMessage = null;
                try {
                    parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
                } catch (InvalidProtocolBufferException e) {
                    parsedMessage = (StmtExecute)e.getUnfinishedMessage();
                    throw e;
                } finally {
                    if (parsedMessage != null) {
                        this.mergeFrom(parsedMessage);
                    }
                }
                return this;
            }

            @Override
            public boolean hasNamespace() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override
            public String getNamespace() {
                Object ref = this.namespace_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString)ref;
                    String s2 = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.namespace_ = s2;
                    }
                    return s2;
                }
                return (String)ref;
            }

            @Override
            public ByteString getNamespaceBytes() {
                Object ref = this.namespace_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String)ref);
                    this.namespace_ = b;
                    return b;
                }
                return (ByteString)ref;
            }

            public Builder setNamespace(String value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 1;
                this.namespace_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearNamespace() {
                this.bitField0_ &= 0xFFFFFFFE;
                this.namespace_ = StmtExecute.getDefaultInstance().getNamespace();
                this.onChanged();
                return this;
            }

            public Builder setNamespaceBytes(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 1;
                this.namespace_ = value;
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasStmt() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override
            public ByteString getStmt() {
                return this.stmt_;
            }

            public Builder setStmt(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 2;
                this.stmt_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearStmt() {
                this.bitField0_ &= 0xFFFFFFFD;
                this.stmt_ = StmtExecute.getDefaultInstance().getStmt();
                this.onChanged();
                return this;
            }

            private void ensureArgsIsMutable() {
                if ((this.bitField0_ & 4) != 4) {
                    this.args_ = new ArrayList<MysqlxDatatypes.Any>(this.args_);
                    this.bitField0_ |= 4;
                }
            }

            @Override
            public List<MysqlxDatatypes.Any> getArgsList() {
                if (this.argsBuilder_ == null) {
                    return Collections.unmodifiableList(this.args_);
                }
                return this.argsBuilder_.getMessageList();
            }

            @Override
            public int getArgsCount() {
                if (this.argsBuilder_ == null) {
                    return this.args_.size();
                }
                return this.argsBuilder_.getCount();
            }

            @Override
            public MysqlxDatatypes.Any getArgs(int index) {
                if (this.argsBuilder_ == null) {
                    return this.args_.get(index);
                }
                return this.argsBuilder_.getMessage(index);
            }

            public Builder setArgs(int index, MysqlxDatatypes.Any value) {
                if (this.argsBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureArgsIsMutable();
                    this.args_.set(index, value);
                    this.onChanged();
                } else {
                    this.argsBuilder_.setMessage(index, value);
                }
                return this;
            }

            public Builder setArgs(int index, MysqlxDatatypes.Any.Builder builderForValue) {
                if (this.argsBuilder_ == null) {
                    this.ensureArgsIsMutable();
                    this.args_.set(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.argsBuilder_.setMessage(index, builderForValue.build());
                }
                return this;
            }

            public Builder addArgs(MysqlxDatatypes.Any value) {
                if (this.argsBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureArgsIsMutable();
                    this.args_.add(value);
                    this.onChanged();
                } else {
                    this.argsBuilder_.addMessage(value);
                }
                return this;
            }

            public Builder addArgs(int index, MysqlxDatatypes.Any value) {
                if (this.argsBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureArgsIsMutable();
                    this.args_.add(index, value);
                    this.onChanged();
                } else {
                    this.argsBuilder_.addMessage(index, value);
                }
                return this;
            }

            public Builder addArgs(MysqlxDatatypes.Any.Builder builderForValue) {
                if (this.argsBuilder_ == null) {
                    this.ensureArgsIsMutable();
                    this.args_.add(builderForValue.build());
                    this.onChanged();
                } else {
                    this.argsBuilder_.addMessage(builderForValue.build());
                }
                return this;
            }

            public Builder addArgs(int index, MysqlxDatatypes.Any.Builder builderForValue) {
                if (this.argsBuilder_ == null) {
                    this.ensureArgsIsMutable();
                    this.args_.add(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.argsBuilder_.addMessage(index, builderForValue.build());
                }
                return this;
            }

            public Builder addAllArgs(Iterable<? extends MysqlxDatatypes.Any> values2) {
                if (this.argsBuilder_ == null) {
                    this.ensureArgsIsMutable();
                    AbstractMessageLite.Builder.addAll(values2, this.args_);
                    this.onChanged();
                } else {
                    this.argsBuilder_.addAllMessages(values2);
                }
                return this;
            }

            public Builder clearArgs() {
                if (this.argsBuilder_ == null) {
                    this.args_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFFFFB;
                    this.onChanged();
                } else {
                    this.argsBuilder_.clear();
                }
                return this;
            }

            public Builder removeArgs(int index) {
                if (this.argsBuilder_ == null) {
                    this.ensureArgsIsMutable();
                    this.args_.remove(index);
                    this.onChanged();
                } else {
                    this.argsBuilder_.remove(index);
                }
                return this;
            }

            public MysqlxDatatypes.Any.Builder getArgsBuilder(int index) {
                return this.getArgsFieldBuilder().getBuilder(index);
            }

            @Override
            public MysqlxDatatypes.AnyOrBuilder getArgsOrBuilder(int index) {
                if (this.argsBuilder_ == null) {
                    return this.args_.get(index);
                }
                return this.argsBuilder_.getMessageOrBuilder(index);
            }

            @Override
            public List<? extends MysqlxDatatypes.AnyOrBuilder> getArgsOrBuilderList() {
                if (this.argsBuilder_ != null) {
                    return this.argsBuilder_.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.args_);
            }

            public MysqlxDatatypes.Any.Builder addArgsBuilder() {
                return this.getArgsFieldBuilder().addBuilder(MysqlxDatatypes.Any.getDefaultInstance());
            }

            public MysqlxDatatypes.Any.Builder addArgsBuilder(int index) {
                return this.getArgsFieldBuilder().addBuilder(index, MysqlxDatatypes.Any.getDefaultInstance());
            }

            public List<MysqlxDatatypes.Any.Builder> getArgsBuilderList() {
                return this.getArgsFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilder<MysqlxDatatypes.Any, MysqlxDatatypes.Any.Builder, MysqlxDatatypes.AnyOrBuilder> getArgsFieldBuilder() {
                if (this.argsBuilder_ == null) {
                    this.argsBuilder_ = new RepeatedFieldBuilder(this.args_, (this.bitField0_ & 4) == 4, this.getParentForChildren(), this.isClean());
                    this.args_ = null;
                }
                return this.argsBuilder_;
            }

            @Override
            public boolean hasCompactMetadata() {
                return (this.bitField0_ & 8) == 8;
            }

            @Override
            public boolean getCompactMetadata() {
                return this.compactMetadata_;
            }

            public Builder setCompactMetadata(boolean value) {
                this.bitField0_ |= 8;
                this.compactMetadata_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearCompactMetadata() {
                this.bitField0_ &= 0xFFFFFFF7;
                this.compactMetadata_ = false;
                this.onChanged();
                return this;
            }
        }
    }

    public static interface StmtExecuteOrBuilder
    extends MessageOrBuilder {
        public boolean hasNamespace();

        public String getNamespace();

        public ByteString getNamespaceBytes();

        public boolean hasStmt();

        public ByteString getStmt();

        public List<MysqlxDatatypes.Any> getArgsList();

        public MysqlxDatatypes.Any getArgs(int var1);

        public int getArgsCount();

        public List<? extends MysqlxDatatypes.AnyOrBuilder> getArgsOrBuilderList();

        public MysqlxDatatypes.AnyOrBuilder getArgsOrBuilder(int var1);

        public boolean hasCompactMetadata();

        public boolean getCompactMetadata();
    }
}

