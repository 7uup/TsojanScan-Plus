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
import com.google.protobuf.UnknownFieldSet;
import com.mysql.cj.x.protobuf.Mysqlx;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectStreamException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class MysqlxResultset {
    private static final Descriptors.Descriptor internal_static_Mysqlx_Resultset_FetchDoneMoreOutParams_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_Mysqlx_Resultset_FetchDoneMoreOutParams_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_Mysqlx_Resultset_FetchDoneMoreResultsets_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_Mysqlx_Resultset_FetchDoneMoreResultsets_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_Mysqlx_Resultset_FetchDone_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_Mysqlx_Resultset_FetchDone_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_Mysqlx_Resultset_ColumnMetaData_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_Mysqlx_Resultset_ColumnMetaData_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_Mysqlx_Resultset_Row_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_Mysqlx_Resultset_Row_fieldAccessorTable;
    private static Descriptors.FileDescriptor descriptor;

    private MysqlxResultset() {
    }

    public static void registerAllExtensions(ExtensionRegistry registry) {
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        String[] descriptorData = new String[]{"\n\u0016mysqlx_resultset.proto\u0012\u0010Mysqlx.Resultset\u001a\fmysqlx.proto\"\u001e\n\u0016FetchDoneMoreOutParams:\u0004\u0090\u00ea0\u0012\"\u001f\n\u0017FetchDoneMoreResultsets:\u0004\u0090\u00ea0\u0010\"\u0011\n\tFetchDone:\u0004\u0090\u00ea0\u000e\"\u00a5\u0003\n\u000eColumnMetaData\u00128\n\u0004type\u0018\u0001 \u0002(\u000e2*.Mysqlx.Resultset.ColumnMetaData.FieldType\u0012\f\n\u0004name\u0018\u0002 \u0001(\f\u0012\u0015\n\roriginal_name\u0018\u0003 \u0001(\f\u0012\r\n\u0005table\u0018\u0004 \u0001(\f\u0012\u0016\n\u000eoriginal_table\u0018\u0005 \u0001(\f\u0012\u000e\n\u0006schema\u0018\u0006 \u0001(\f\u0012\u000f\n\u0007catalog\u0018\u0007 \u0001(\f\u0012\u0011\n\tcollation\u0018\b \u0001(\u0004\u0012\u0019\n\u0011fractional_digits\u0018\t \u0001(\r\u0012\u000e\n\u0006length\u0018\n \u0001(\r\u0012\r\n\u0005flags\u0018\u000b ", "\u0001(\r\u0012\u0014\n\fcontent_type\u0018\f \u0001(\r\"\u0082\u0001\n\tFieldType\u0012\b\n\u0004SINT\u0010\u0001\u0012\b\n\u0004UINT\u0010\u0002\u0012\n\n\u0006DOUBLE\u0010\u0005\u0012\t\n\u0005FLOAT\u0010\u0006\u0012\t\n\u0005BYTES\u0010\u0007\u0012\b\n\u0004TIME\u0010\n\u0012\f\n\bDATETIME\u0010\f\u0012\u0007\n\u0003SET\u0010\u000f\u0012\b\n\u0004ENUM\u0010\u0010\u0012\u0007\n\u0003BIT\u0010\u0011\u0012\u000b\n\u0007DECIMAL\u0010\u0012:\u0004\u0090\u00ea0\f\"\u001a\n\u0003Row\u0012\r\n\u0005field\u0018\u0001 \u0003(\f:\u0004\u0090\u00ea0\r*4\n\u0011ContentType_BYTES\u0012\f\n\bGEOMETRY\u0010\u0001\u0012\b\n\u0004JSON\u0010\u0002\u0012\u0007\n\u0003XML\u0010\u0003*.\n\u0014ContentType_DATETIME\u0012\b\n\u0004DATE\u0010\u0001\u0012\f\n\bDATETIME\u0010\u0002B\u0019\n\u0017com.mysql.cj.x.protobuf"};
        Descriptors.FileDescriptor.InternalDescriptorAssigner assigner = new Descriptors.FileDescriptor.InternalDescriptorAssigner(){

            @Override
            public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor root) {
                descriptor = root;
                return null;
            }
        };
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[]{Mysqlx.getDescriptor()}, assigner);
        internal_static_Mysqlx_Resultset_FetchDoneMoreOutParams_descriptor = MysqlxResultset.getDescriptor().getMessageTypes().get(0);
        internal_static_Mysqlx_Resultset_FetchDoneMoreOutParams_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_Mysqlx_Resultset_FetchDoneMoreOutParams_descriptor, new String[0]);
        internal_static_Mysqlx_Resultset_FetchDoneMoreResultsets_descriptor = MysqlxResultset.getDescriptor().getMessageTypes().get(1);
        internal_static_Mysqlx_Resultset_FetchDoneMoreResultsets_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_Mysqlx_Resultset_FetchDoneMoreResultsets_descriptor, new String[0]);
        internal_static_Mysqlx_Resultset_FetchDone_descriptor = MysqlxResultset.getDescriptor().getMessageTypes().get(2);
        internal_static_Mysqlx_Resultset_FetchDone_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_Mysqlx_Resultset_FetchDone_descriptor, new String[0]);
        internal_static_Mysqlx_Resultset_ColumnMetaData_descriptor = MysqlxResultset.getDescriptor().getMessageTypes().get(3);
        internal_static_Mysqlx_Resultset_ColumnMetaData_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_Mysqlx_Resultset_ColumnMetaData_descriptor, new String[]{"Type", "Name", "OriginalName", "Table", "OriginalTable", "Schema", "Catalog", "Collation", "FractionalDigits", "Length", "Flags", "ContentType"});
        internal_static_Mysqlx_Resultset_Row_descriptor = MysqlxResultset.getDescriptor().getMessageTypes().get(4);
        internal_static_Mysqlx_Resultset_Row_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_Mysqlx_Resultset_Row_descriptor, new String[]{"Field"});
        ExtensionRegistry registry = ExtensionRegistry.newInstance();
        registry.add(Mysqlx.serverMessageId);
        registry.add(Mysqlx.serverMessageId);
        registry.add(Mysqlx.serverMessageId);
        registry.add(Mysqlx.serverMessageId);
        registry.add(Mysqlx.serverMessageId);
        Descriptors.FileDescriptor.internalUpdateFileDescriptor(descriptor, registry);
        Mysqlx.getDescriptor();
    }

    public static final class Row
    extends GeneratedMessage
    implements RowOrBuilder {
        private static final Row defaultInstance;
        private final UnknownFieldSet unknownFields;
        public static Parser<Row> PARSER;
        public static final int FIELD_FIELD_NUMBER = 1;
        private List<ByteString> field_;
        private byte memoizedIsInitialized = (byte)-1;
        private int memoizedSerializedSize = -1;
        private static final long serialVersionUID = 0L;

        private Row(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.unknownFields = builder.getUnknownFields();
        }

        private Row(boolean noInit) {
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static Row getDefaultInstance() {
            return defaultInstance;
        }

        @Override
        public Row getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private Row(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
                        this.field_ = new ArrayList<ByteString>();
                        mutable_bitField0_ |= true;
                    }
                    this.field_.add(input.readBytes());
                }
            } catch (InvalidProtocolBufferException e) {
                throw e.setUnfinishedMessage(this);
            } catch (IOException e) {
                throw new InvalidProtocolBufferException(e.getMessage()).setUnfinishedMessage(this);
            } finally {
                if (mutable_bitField0_ & true) {
                    this.field_ = Collections.unmodifiableList(this.field_);
                }
                this.unknownFields = unknownFields.build();
                this.makeExtensionsImmutable();
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return internal_static_Mysqlx_Resultset_Row_descriptor;
        }

        @Override
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return internal_static_Mysqlx_Resultset_Row_fieldAccessorTable.ensureFieldAccessorsInitialized(Row.class, Builder.class);
        }

        public Parser<Row> getParserForType() {
            return PARSER;
        }

        @Override
        public List<ByteString> getFieldList() {
            return this.field_;
        }

        @Override
        public int getFieldCount() {
            return this.field_.size();
        }

        @Override
        public ByteString getField(int index) {
            return this.field_.get(index);
        }

        private void initFields() {
            this.field_ = Collections.emptyList();
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
            for (int i = 0; i < this.field_.size(); ++i) {
                output.writeBytes(1, this.field_.get(i));
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
            int dataSize = 0;
            for (int i = 0; i < this.field_.size(); ++i) {
                dataSize += CodedOutputStream.computeBytesSizeNoTag(this.field_.get(i));
            }
            size += dataSize;
            size += 1 * this.getFieldList().size();
            this.memoizedSerializedSize = size += this.getUnknownFields().getSerializedSize();
            return size;
        }

        @Override
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static Row parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static Row parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static Row parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static Row parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static Row parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static Row parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Row parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static Row parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static Row parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static Row parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override
        public Builder newBuilderForType() {
            return Row.newBuilder();
        }

        public static Builder newBuilder(Row prototype) {
            return Row.newBuilder().mergeFrom(prototype);
        }

        @Override
        public Builder toBuilder() {
            return Row.newBuilder(this);
        }

        @Override
        protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        static {
            PARSER = new AbstractParser<Row>(){

                @Override
                public Row parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                    return new Row(input, extensionRegistry);
                }
            };
            defaultInstance = new Row(true);
            defaultInstance.initFields();
        }

        public static final class Builder
        extends GeneratedMessage.Builder<Builder>
        implements RowOrBuilder {
            private int bitField0_;
            private List<ByteString> field_ = Collections.emptyList();

            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_Mysqlx_Resultset_Row_descriptor;
            }

            @Override
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_Mysqlx_Resultset_Row_fieldAccessorTable.ensureFieldAccessorsInitialized(Row.class, Builder.class);
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
                this.field_ = Collections.emptyList();
                this.bitField0_ &= 0xFFFFFFFE;
                return this;
            }

            @Override
            public Builder clone() {
                return Builder.create().mergeFrom(this.buildPartial());
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return internal_static_Mysqlx_Resultset_Row_descriptor;
            }

            @Override
            public Row getDefaultInstanceForType() {
                return Row.getDefaultInstance();
            }

            @Override
            public Row build() {
                Row result = this.buildPartial();
                if (!result.isInitialized()) {
                    throw Builder.newUninitializedMessageException(result);
                }
                return result;
            }

            @Override
            public Row buildPartial() {
                Row result = new Row(this);
                int from_bitField0_ = this.bitField0_;
                if ((this.bitField0_ & 1) == 1) {
                    this.field_ = Collections.unmodifiableList(this.field_);
                    this.bitField0_ &= 0xFFFFFFFE;
                }
                result.field_ = this.field_;
                this.onBuilt();
                return result;
            }

            @Override
            public Builder mergeFrom(Message other) {
                if (other instanceof Row) {
                    return this.mergeFrom((Row)other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(Row other) {
                if (other == Row.getDefaultInstance()) {
                    return this;
                }
                if (!other.field_.isEmpty()) {
                    if (this.field_.isEmpty()) {
                        this.field_ = other.field_;
                        this.bitField0_ &= 0xFFFFFFFE;
                    } else {
                        this.ensureFieldIsMutable();
                        this.field_.addAll(other.field_);
                    }
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
                Row parsedMessage = null;
                try {
                    parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
                } catch (InvalidProtocolBufferException e) {
                    parsedMessage = (Row)e.getUnfinishedMessage();
                    throw e;
                } finally {
                    if (parsedMessage != null) {
                        this.mergeFrom(parsedMessage);
                    }
                }
                return this;
            }

            private void ensureFieldIsMutable() {
                if ((this.bitField0_ & 1) != 1) {
                    this.field_ = new ArrayList<ByteString>(this.field_);
                    this.bitField0_ |= 1;
                }
            }

            @Override
            public List<ByteString> getFieldList() {
                return Collections.unmodifiableList(this.field_);
            }

            @Override
            public int getFieldCount() {
                return this.field_.size();
            }

            @Override
            public ByteString getField(int index) {
                return this.field_.get(index);
            }

            public Builder setField(int index, ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ensureFieldIsMutable();
                this.field_.set(index, value);
                this.onChanged();
                return this;
            }

            public Builder addField(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ensureFieldIsMutable();
                this.field_.add(value);
                this.onChanged();
                return this;
            }

            public Builder addAllField(Iterable<? extends ByteString> values2) {
                this.ensureFieldIsMutable();
                AbstractMessageLite.Builder.addAll(values2, this.field_);
                this.onChanged();
                return this;
            }

            public Builder clearField() {
                this.field_ = Collections.emptyList();
                this.bitField0_ &= 0xFFFFFFFE;
                this.onChanged();
                return this;
            }
        }
    }

    public static interface RowOrBuilder
    extends MessageOrBuilder {
        public List<ByteString> getFieldList();

        public int getFieldCount();

        public ByteString getField(int var1);
    }

    public static final class ColumnMetaData
    extends GeneratedMessage
    implements ColumnMetaDataOrBuilder {
        private static final ColumnMetaData defaultInstance;
        private final UnknownFieldSet unknownFields;
        public static Parser<ColumnMetaData> PARSER;
        private int bitField0_;
        public static final int TYPE_FIELD_NUMBER = 1;
        private FieldType type_;
        public static final int NAME_FIELD_NUMBER = 2;
        private ByteString name_;
        public static final int ORIGINAL_NAME_FIELD_NUMBER = 3;
        private ByteString originalName_;
        public static final int TABLE_FIELD_NUMBER = 4;
        private ByteString table_;
        public static final int ORIGINAL_TABLE_FIELD_NUMBER = 5;
        private ByteString originalTable_;
        public static final int SCHEMA_FIELD_NUMBER = 6;
        private ByteString schema_;
        public static final int CATALOG_FIELD_NUMBER = 7;
        private ByteString catalog_;
        public static final int COLLATION_FIELD_NUMBER = 8;
        private long collation_;
        public static final int FRACTIONAL_DIGITS_FIELD_NUMBER = 9;
        private int fractionalDigits_;
        public static final int LENGTH_FIELD_NUMBER = 10;
        private int length_;
        public static final int FLAGS_FIELD_NUMBER = 11;
        private int flags_;
        public static final int CONTENT_TYPE_FIELD_NUMBER = 12;
        private int contentType_;
        private byte memoizedIsInitialized = (byte)-1;
        private int memoizedSerializedSize = -1;
        private static final long serialVersionUID = 0L;

        private ColumnMetaData(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.unknownFields = builder.getUnknownFields();
        }

        private ColumnMetaData(boolean noInit) {
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static ColumnMetaData getDefaultInstance() {
            return defaultInstance;
        }

        @Override
        public ColumnMetaData getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private ColumnMetaData(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this.initFields();
            boolean mutable_bitField0_ = false;
            UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
            try {
                boolean done = false;
                block21: while (!done) {
                    int tag = input.readTag();
                    switch (tag) {
                        case 0: {
                            done = true;
                            continue block21;
                        }
                        default: {
                            if (this.parseUnknownField(input, unknownFields, extensionRegistry, tag)) continue block21;
                            done = true;
                            continue block21;
                        }
                        case 8: {
                            int rawValue = input.readEnum();
                            FieldType value = FieldType.valueOf(rawValue);
                            if (value == null) {
                                unknownFields.mergeVarintField(1, rawValue);
                                continue block21;
                            }
                            this.bitField0_ |= 1;
                            this.type_ = value;
                            continue block21;
                        }
                        case 18: {
                            this.bitField0_ |= 2;
                            this.name_ = input.readBytes();
                            continue block21;
                        }
                        case 26: {
                            this.bitField0_ |= 4;
                            this.originalName_ = input.readBytes();
                            continue block21;
                        }
                        case 34: {
                            this.bitField0_ |= 8;
                            this.table_ = input.readBytes();
                            continue block21;
                        }
                        case 42: {
                            this.bitField0_ |= 0x10;
                            this.originalTable_ = input.readBytes();
                            continue block21;
                        }
                        case 50: {
                            this.bitField0_ |= 0x20;
                            this.schema_ = input.readBytes();
                            continue block21;
                        }
                        case 58: {
                            this.bitField0_ |= 0x40;
                            this.catalog_ = input.readBytes();
                            continue block21;
                        }
                        case 64: {
                            this.bitField0_ |= 0x80;
                            this.collation_ = input.readUInt64();
                            continue block21;
                        }
                        case 72: {
                            this.bitField0_ |= 0x100;
                            this.fractionalDigits_ = input.readUInt32();
                            continue block21;
                        }
                        case 80: {
                            this.bitField0_ |= 0x200;
                            this.length_ = input.readUInt32();
                            continue block21;
                        }
                        case 88: {
                            this.bitField0_ |= 0x400;
                            this.flags_ = input.readUInt32();
                            continue block21;
                        }
                        case 96: 
                    }
                    this.bitField0_ |= 0x800;
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
            return internal_static_Mysqlx_Resultset_ColumnMetaData_descriptor;
        }

        @Override
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return internal_static_Mysqlx_Resultset_ColumnMetaData_fieldAccessorTable.ensureFieldAccessorsInitialized(ColumnMetaData.class, Builder.class);
        }

        public Parser<ColumnMetaData> getParserForType() {
            return PARSER;
        }

        @Override
        public boolean hasType() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override
        public FieldType getType() {
            return this.type_;
        }

        @Override
        public boolean hasName() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override
        public ByteString getName() {
            return this.name_;
        }

        @Override
        public boolean hasOriginalName() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override
        public ByteString getOriginalName() {
            return this.originalName_;
        }

        @Override
        public boolean hasTable() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override
        public ByteString getTable() {
            return this.table_;
        }

        @Override
        public boolean hasOriginalTable() {
            return (this.bitField0_ & 0x10) == 16;
        }

        @Override
        public ByteString getOriginalTable() {
            return this.originalTable_;
        }

        @Override
        public boolean hasSchema() {
            return (this.bitField0_ & 0x20) == 32;
        }

        @Override
        public ByteString getSchema() {
            return this.schema_;
        }

        @Override
        public boolean hasCatalog() {
            return (this.bitField0_ & 0x40) == 64;
        }

        @Override
        public ByteString getCatalog() {
            return this.catalog_;
        }

        @Override
        public boolean hasCollation() {
            return (this.bitField0_ & 0x80) == 128;
        }

        @Override
        public long getCollation() {
            return this.collation_;
        }

        @Override
        public boolean hasFractionalDigits() {
            return (this.bitField0_ & 0x100) == 256;
        }

        @Override
        public int getFractionalDigits() {
            return this.fractionalDigits_;
        }

        @Override
        public boolean hasLength() {
            return (this.bitField0_ & 0x200) == 512;
        }

        @Override
        public int getLength() {
            return this.length_;
        }

        @Override
        public boolean hasFlags() {
            return (this.bitField0_ & 0x400) == 1024;
        }

        @Override
        public int getFlags() {
            return this.flags_;
        }

        @Override
        public boolean hasContentType() {
            return (this.bitField0_ & 0x800) == 2048;
        }

        @Override
        public int getContentType() {
            return this.contentType_;
        }

        private void initFields() {
            this.type_ = FieldType.SINT;
            this.name_ = ByteString.EMPTY;
            this.originalName_ = ByteString.EMPTY;
            this.table_ = ByteString.EMPTY;
            this.originalTable_ = ByteString.EMPTY;
            this.schema_ = ByteString.EMPTY;
            this.catalog_ = ByteString.EMPTY;
            this.collation_ = 0L;
            this.fractionalDigits_ = 0;
            this.length_ = 0;
            this.flags_ = 0;
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
                output.writeEnum(1, this.type_.getNumber());
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeBytes(2, this.name_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeBytes(3, this.originalName_);
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeBytes(4, this.table_);
            }
            if ((this.bitField0_ & 0x10) == 16) {
                output.writeBytes(5, this.originalTable_);
            }
            if ((this.bitField0_ & 0x20) == 32) {
                output.writeBytes(6, this.schema_);
            }
            if ((this.bitField0_ & 0x40) == 64) {
                output.writeBytes(7, this.catalog_);
            }
            if ((this.bitField0_ & 0x80) == 128) {
                output.writeUInt64(8, this.collation_);
            }
            if ((this.bitField0_ & 0x100) == 256) {
                output.writeUInt32(9, this.fractionalDigits_);
            }
            if ((this.bitField0_ & 0x200) == 512) {
                output.writeUInt32(10, this.length_);
            }
            if ((this.bitField0_ & 0x400) == 1024) {
                output.writeUInt32(11, this.flags_);
            }
            if ((this.bitField0_ & 0x800) == 2048) {
                output.writeUInt32(12, this.contentType_);
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
                size += CodedOutputStream.computeBytesSize(2, this.name_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size += CodedOutputStream.computeBytesSize(3, this.originalName_);
            }
            if ((this.bitField0_ & 8) == 8) {
                size += CodedOutputStream.computeBytesSize(4, this.table_);
            }
            if ((this.bitField0_ & 0x10) == 16) {
                size += CodedOutputStream.computeBytesSize(5, this.originalTable_);
            }
            if ((this.bitField0_ & 0x20) == 32) {
                size += CodedOutputStream.computeBytesSize(6, this.schema_);
            }
            if ((this.bitField0_ & 0x40) == 64) {
                size += CodedOutputStream.computeBytesSize(7, this.catalog_);
            }
            if ((this.bitField0_ & 0x80) == 128) {
                size += CodedOutputStream.computeUInt64Size(8, this.collation_);
            }
            if ((this.bitField0_ & 0x100) == 256) {
                size += CodedOutputStream.computeUInt32Size(9, this.fractionalDigits_);
            }
            if ((this.bitField0_ & 0x200) == 512) {
                size += CodedOutputStream.computeUInt32Size(10, this.length_);
            }
            if ((this.bitField0_ & 0x400) == 1024) {
                size += CodedOutputStream.computeUInt32Size(11, this.flags_);
            }
            if ((this.bitField0_ & 0x800) == 2048) {
                size += CodedOutputStream.computeUInt32Size(12, this.contentType_);
            }
            this.memoizedSerializedSize = size += this.getUnknownFields().getSerializedSize();
            return size;
        }

        @Override
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static ColumnMetaData parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static ColumnMetaData parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static ColumnMetaData parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static ColumnMetaData parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static ColumnMetaData parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static ColumnMetaData parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static ColumnMetaData parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static ColumnMetaData parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static ColumnMetaData parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static ColumnMetaData parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override
        public Builder newBuilderForType() {
            return ColumnMetaData.newBuilder();
        }

        public static Builder newBuilder(ColumnMetaData prototype) {
            return ColumnMetaData.newBuilder().mergeFrom(prototype);
        }

        @Override
        public Builder toBuilder() {
            return ColumnMetaData.newBuilder(this);
        }

        @Override
        protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        static {
            PARSER = new AbstractParser<ColumnMetaData>(){

                @Override
                public ColumnMetaData parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                    return new ColumnMetaData(input, extensionRegistry);
                }
            };
            defaultInstance = new ColumnMetaData(true);
            defaultInstance.initFields();
        }

        public static final class Builder
        extends GeneratedMessage.Builder<Builder>
        implements ColumnMetaDataOrBuilder {
            private int bitField0_;
            private FieldType type_ = FieldType.SINT;
            private ByteString name_ = ByteString.EMPTY;
            private ByteString originalName_ = ByteString.EMPTY;
            private ByteString table_ = ByteString.EMPTY;
            private ByteString originalTable_ = ByteString.EMPTY;
            private ByteString schema_ = ByteString.EMPTY;
            private ByteString catalog_ = ByteString.EMPTY;
            private long collation_;
            private int fractionalDigits_;
            private int length_;
            private int flags_;
            private int contentType_;

            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_Mysqlx_Resultset_ColumnMetaData_descriptor;
            }

            @Override
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_Mysqlx_Resultset_ColumnMetaData_fieldAccessorTable.ensureFieldAccessorsInitialized(ColumnMetaData.class, Builder.class);
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
                this.type_ = FieldType.SINT;
                this.bitField0_ &= 0xFFFFFFFE;
                this.name_ = ByteString.EMPTY;
                this.bitField0_ &= 0xFFFFFFFD;
                this.originalName_ = ByteString.EMPTY;
                this.bitField0_ &= 0xFFFFFFFB;
                this.table_ = ByteString.EMPTY;
                this.bitField0_ &= 0xFFFFFFF7;
                this.originalTable_ = ByteString.EMPTY;
                this.bitField0_ &= 0xFFFFFFEF;
                this.schema_ = ByteString.EMPTY;
                this.bitField0_ &= 0xFFFFFFDF;
                this.catalog_ = ByteString.EMPTY;
                this.bitField0_ &= 0xFFFFFFBF;
                this.collation_ = 0L;
                this.bitField0_ &= 0xFFFFFF7F;
                this.fractionalDigits_ = 0;
                this.bitField0_ &= 0xFFFFFEFF;
                this.length_ = 0;
                this.bitField0_ &= 0xFFFFFDFF;
                this.flags_ = 0;
                this.bitField0_ &= 0xFFFFFBFF;
                this.contentType_ = 0;
                this.bitField0_ &= 0xFFFFF7FF;
                return this;
            }

            @Override
            public Builder clone() {
                return Builder.create().mergeFrom(this.buildPartial());
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return internal_static_Mysqlx_Resultset_ColumnMetaData_descriptor;
            }

            @Override
            public ColumnMetaData getDefaultInstanceForType() {
                return ColumnMetaData.getDefaultInstance();
            }

            @Override
            public ColumnMetaData build() {
                ColumnMetaData result = this.buildPartial();
                if (!result.isInitialized()) {
                    throw Builder.newUninitializedMessageException(result);
                }
                return result;
            }

            @Override
            public ColumnMetaData buildPartial() {
                ColumnMetaData result = new ColumnMetaData(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ |= 1;
                }
                result.type_ = this.type_;
                if ((from_bitField0_ & 2) == 2) {
                    to_bitField0_ |= 2;
                }
                result.name_ = this.name_;
                if ((from_bitField0_ & 4) == 4) {
                    to_bitField0_ |= 4;
                }
                result.originalName_ = this.originalName_;
                if ((from_bitField0_ & 8) == 8) {
                    to_bitField0_ |= 8;
                }
                result.table_ = this.table_;
                if ((from_bitField0_ & 0x10) == 16) {
                    to_bitField0_ |= 0x10;
                }
                result.originalTable_ = this.originalTable_;
                if ((from_bitField0_ & 0x20) == 32) {
                    to_bitField0_ |= 0x20;
                }
                result.schema_ = this.schema_;
                if ((from_bitField0_ & 0x40) == 64) {
                    to_bitField0_ |= 0x40;
                }
                result.catalog_ = this.catalog_;
                if ((from_bitField0_ & 0x80) == 128) {
                    to_bitField0_ |= 0x80;
                }
                result.collation_ = this.collation_;
                if ((from_bitField0_ & 0x100) == 256) {
                    to_bitField0_ |= 0x100;
                }
                result.fractionalDigits_ = this.fractionalDigits_;
                if ((from_bitField0_ & 0x200) == 512) {
                    to_bitField0_ |= 0x200;
                }
                result.length_ = this.length_;
                if ((from_bitField0_ & 0x400) == 1024) {
                    to_bitField0_ |= 0x400;
                }
                result.flags_ = this.flags_;
                if ((from_bitField0_ & 0x800) == 2048) {
                    to_bitField0_ |= 0x800;
                }
                result.contentType_ = this.contentType_;
                result.bitField0_ = to_bitField0_;
                this.onBuilt();
                return result;
            }

            @Override
            public Builder mergeFrom(Message other) {
                if (other instanceof ColumnMetaData) {
                    return this.mergeFrom((ColumnMetaData)other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(ColumnMetaData other) {
                if (other == ColumnMetaData.getDefaultInstance()) {
                    return this;
                }
                if (other.hasType()) {
                    this.setType(other.getType());
                }
                if (other.hasName()) {
                    this.setName(other.getName());
                }
                if (other.hasOriginalName()) {
                    this.setOriginalName(other.getOriginalName());
                }
                if (other.hasTable()) {
                    this.setTable(other.getTable());
                }
                if (other.hasOriginalTable()) {
                    this.setOriginalTable(other.getOriginalTable());
                }
                if (other.hasSchema()) {
                    this.setSchema(other.getSchema());
                }
                if (other.hasCatalog()) {
                    this.setCatalog(other.getCatalog());
                }
                if (other.hasCollation()) {
                    this.setCollation(other.getCollation());
                }
                if (other.hasFractionalDigits()) {
                    this.setFractionalDigits(other.getFractionalDigits());
                }
                if (other.hasLength()) {
                    this.setLength(other.getLength());
                }
                if (other.hasFlags()) {
                    this.setFlags(other.getFlags());
                }
                if (other.hasContentType()) {
                    this.setContentType(other.getContentType());
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
                ColumnMetaData parsedMessage = null;
                try {
                    parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
                } catch (InvalidProtocolBufferException e) {
                    parsedMessage = (ColumnMetaData)e.getUnfinishedMessage();
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
            public FieldType getType() {
                return this.type_;
            }

            public Builder setType(FieldType value) {
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
                this.type_ = FieldType.SINT;
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasName() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override
            public ByteString getName() {
                return this.name_;
            }

            public Builder setName(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 2;
                this.name_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearName() {
                this.bitField0_ &= 0xFFFFFFFD;
                this.name_ = ColumnMetaData.getDefaultInstance().getName();
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasOriginalName() {
                return (this.bitField0_ & 4) == 4;
            }

            @Override
            public ByteString getOriginalName() {
                return this.originalName_;
            }

            public Builder setOriginalName(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 4;
                this.originalName_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearOriginalName() {
                this.bitField0_ &= 0xFFFFFFFB;
                this.originalName_ = ColumnMetaData.getDefaultInstance().getOriginalName();
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasTable() {
                return (this.bitField0_ & 8) == 8;
            }

            @Override
            public ByteString getTable() {
                return this.table_;
            }

            public Builder setTable(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 8;
                this.table_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearTable() {
                this.bitField0_ &= 0xFFFFFFF7;
                this.table_ = ColumnMetaData.getDefaultInstance().getTable();
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasOriginalTable() {
                return (this.bitField0_ & 0x10) == 16;
            }

            @Override
            public ByteString getOriginalTable() {
                return this.originalTable_;
            }

            public Builder setOriginalTable(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 0x10;
                this.originalTable_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearOriginalTable() {
                this.bitField0_ &= 0xFFFFFFEF;
                this.originalTable_ = ColumnMetaData.getDefaultInstance().getOriginalTable();
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasSchema() {
                return (this.bitField0_ & 0x20) == 32;
            }

            @Override
            public ByteString getSchema() {
                return this.schema_;
            }

            public Builder setSchema(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 0x20;
                this.schema_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearSchema() {
                this.bitField0_ &= 0xFFFFFFDF;
                this.schema_ = ColumnMetaData.getDefaultInstance().getSchema();
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasCatalog() {
                return (this.bitField0_ & 0x40) == 64;
            }

            @Override
            public ByteString getCatalog() {
                return this.catalog_;
            }

            public Builder setCatalog(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 0x40;
                this.catalog_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearCatalog() {
                this.bitField0_ &= 0xFFFFFFBF;
                this.catalog_ = ColumnMetaData.getDefaultInstance().getCatalog();
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasCollation() {
                return (this.bitField0_ & 0x80) == 128;
            }

            @Override
            public long getCollation() {
                return this.collation_;
            }

            public Builder setCollation(long value) {
                this.bitField0_ |= 0x80;
                this.collation_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearCollation() {
                this.bitField0_ &= 0xFFFFFF7F;
                this.collation_ = 0L;
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasFractionalDigits() {
                return (this.bitField0_ & 0x100) == 256;
            }

            @Override
            public int getFractionalDigits() {
                return this.fractionalDigits_;
            }

            public Builder setFractionalDigits(int value) {
                this.bitField0_ |= 0x100;
                this.fractionalDigits_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearFractionalDigits() {
                this.bitField0_ &= 0xFFFFFEFF;
                this.fractionalDigits_ = 0;
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasLength() {
                return (this.bitField0_ & 0x200) == 512;
            }

            @Override
            public int getLength() {
                return this.length_;
            }

            public Builder setLength(int value) {
                this.bitField0_ |= 0x200;
                this.length_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearLength() {
                this.bitField0_ &= 0xFFFFFDFF;
                this.length_ = 0;
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasFlags() {
                return (this.bitField0_ & 0x400) == 1024;
            }

            @Override
            public int getFlags() {
                return this.flags_;
            }

            public Builder setFlags(int value) {
                this.bitField0_ |= 0x400;
                this.flags_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearFlags() {
                this.bitField0_ &= 0xFFFFFBFF;
                this.flags_ = 0;
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasContentType() {
                return (this.bitField0_ & 0x800) == 2048;
            }

            @Override
            public int getContentType() {
                return this.contentType_;
            }

            public Builder setContentType(int value) {
                this.bitField0_ |= 0x800;
                this.contentType_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearContentType() {
                this.bitField0_ &= 0xFFFFF7FF;
                this.contentType_ = 0;
                this.onChanged();
                return this;
            }
        }

        public static enum FieldType implements ProtocolMessageEnum
        {
            SINT(0, 1),
            UINT(1, 2),
            DOUBLE(2, 5),
            FLOAT(3, 6),
            BYTES(4, 7),
            TIME(5, 10),
            DATETIME(6, 12),
            SET(7, 15),
            ENUM(8, 16),
            BIT(9, 17),
            DECIMAL(10, 18);

            public static final int SINT_VALUE = 1;
            public static final int UINT_VALUE = 2;
            public static final int DOUBLE_VALUE = 5;
            public static final int FLOAT_VALUE = 6;
            public static final int BYTES_VALUE = 7;
            public static final int TIME_VALUE = 10;
            public static final int DATETIME_VALUE = 12;
            public static final int SET_VALUE = 15;
            public static final int ENUM_VALUE = 16;
            public static final int BIT_VALUE = 17;
            public static final int DECIMAL_VALUE = 18;
            private static Internal.EnumLiteMap<FieldType> internalValueMap;
            private static final FieldType[] VALUES;
            private final int index;
            private final int value;

            @Override
            public final int getNumber() {
                return this.value;
            }

            public static FieldType valueOf(int value) {
                switch (value) {
                    case 1: {
                        return SINT;
                    }
                    case 2: {
                        return UINT;
                    }
                    case 5: {
                        return DOUBLE;
                    }
                    case 6: {
                        return FLOAT;
                    }
                    case 7: {
                        return BYTES;
                    }
                    case 10: {
                        return TIME;
                    }
                    case 12: {
                        return DATETIME;
                    }
                    case 15: {
                        return SET;
                    }
                    case 16: {
                        return ENUM;
                    }
                    case 17: {
                        return BIT;
                    }
                    case 18: {
                        return DECIMAL;
                    }
                }
                return null;
            }

            public static Internal.EnumLiteMap<FieldType> internalGetValueMap() {
                return internalValueMap;
            }

            @Override
            public final Descriptors.EnumValueDescriptor getValueDescriptor() {
                return FieldType.getDescriptor().getValues().get(this.index);
            }

            @Override
            public final Descriptors.EnumDescriptor getDescriptorForType() {
                return FieldType.getDescriptor();
            }

            public static final Descriptors.EnumDescriptor getDescriptor() {
                return ColumnMetaData.getDescriptor().getEnumTypes().get(0);
            }

            public static FieldType valueOf(Descriptors.EnumValueDescriptor desc) {
                if (desc.getType() != FieldType.getDescriptor()) {
                    throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
                }
                return VALUES[desc.getIndex()];
            }

            private FieldType(int index, int value) {
                this.index = index;
                this.value = value;
            }

            static {
                internalValueMap = new Internal.EnumLiteMap<FieldType>(){

                    @Override
                    public FieldType findValueByNumber(int number) {
                        return FieldType.valueOf(number);
                    }
                };
                VALUES = FieldType.values();
            }
        }
    }

    public static interface ColumnMetaDataOrBuilder
    extends MessageOrBuilder {
        public boolean hasType();

        public ColumnMetaData.FieldType getType();

        public boolean hasName();

        public ByteString getName();

        public boolean hasOriginalName();

        public ByteString getOriginalName();

        public boolean hasTable();

        public ByteString getTable();

        public boolean hasOriginalTable();

        public ByteString getOriginalTable();

        public boolean hasSchema();

        public ByteString getSchema();

        public boolean hasCatalog();

        public ByteString getCatalog();

        public boolean hasCollation();

        public long getCollation();

        public boolean hasFractionalDigits();

        public int getFractionalDigits();

        public boolean hasLength();

        public int getLength();

        public boolean hasFlags();

        public int getFlags();

        public boolean hasContentType();

        public int getContentType();
    }

    public static final class FetchDone
    extends GeneratedMessage
    implements FetchDoneOrBuilder {
        private static final FetchDone defaultInstance;
        private final UnknownFieldSet unknownFields;
        public static Parser<FetchDone> PARSER;
        private byte memoizedIsInitialized = (byte)-1;
        private int memoizedSerializedSize = -1;
        private static final long serialVersionUID = 0L;

        private FetchDone(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.unknownFields = builder.getUnknownFields();
        }

        private FetchDone(boolean noInit) {
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static FetchDone getDefaultInstance() {
            return defaultInstance;
        }

        @Override
        public FetchDone getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private FetchDone(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
            return internal_static_Mysqlx_Resultset_FetchDone_descriptor;
        }

        @Override
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return internal_static_Mysqlx_Resultset_FetchDone_fieldAccessorTable.ensureFieldAccessorsInitialized(FetchDone.class, Builder.class);
        }

        public Parser<FetchDone> getParserForType() {
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

        public static FetchDone parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static FetchDone parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static FetchDone parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static FetchDone parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static FetchDone parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static FetchDone parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static FetchDone parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static FetchDone parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static FetchDone parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static FetchDone parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override
        public Builder newBuilderForType() {
            return FetchDone.newBuilder();
        }

        public static Builder newBuilder(FetchDone prototype) {
            return FetchDone.newBuilder().mergeFrom(prototype);
        }

        @Override
        public Builder toBuilder() {
            return FetchDone.newBuilder(this);
        }

        @Override
        protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        static {
            PARSER = new AbstractParser<FetchDone>(){

                @Override
                public FetchDone parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                    return new FetchDone(input, extensionRegistry);
                }
            };
            defaultInstance = new FetchDone(true);
            defaultInstance.initFields();
        }

        public static final class Builder
        extends GeneratedMessage.Builder<Builder>
        implements FetchDoneOrBuilder {
            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_Mysqlx_Resultset_FetchDone_descriptor;
            }

            @Override
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_Mysqlx_Resultset_FetchDone_fieldAccessorTable.ensureFieldAccessorsInitialized(FetchDone.class, Builder.class);
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
                return internal_static_Mysqlx_Resultset_FetchDone_descriptor;
            }

            @Override
            public FetchDone getDefaultInstanceForType() {
                return FetchDone.getDefaultInstance();
            }

            @Override
            public FetchDone build() {
                FetchDone result = this.buildPartial();
                if (!result.isInitialized()) {
                    throw Builder.newUninitializedMessageException(result);
                }
                return result;
            }

            @Override
            public FetchDone buildPartial() {
                FetchDone result = new FetchDone(this);
                this.onBuilt();
                return result;
            }

            @Override
            public Builder mergeFrom(Message other) {
                if (other instanceof FetchDone) {
                    return this.mergeFrom((FetchDone)other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(FetchDone other) {
                if (other == FetchDone.getDefaultInstance()) {
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
                FetchDone parsedMessage = null;
                try {
                    parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
                } catch (InvalidProtocolBufferException e) {
                    parsedMessage = (FetchDone)e.getUnfinishedMessage();
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

    public static interface FetchDoneOrBuilder
    extends MessageOrBuilder {
    }

    public static final class FetchDoneMoreResultsets
    extends GeneratedMessage
    implements FetchDoneMoreResultsetsOrBuilder {
        private static final FetchDoneMoreResultsets defaultInstance;
        private final UnknownFieldSet unknownFields;
        public static Parser<FetchDoneMoreResultsets> PARSER;
        private byte memoizedIsInitialized = (byte)-1;
        private int memoizedSerializedSize = -1;
        private static final long serialVersionUID = 0L;

        private FetchDoneMoreResultsets(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.unknownFields = builder.getUnknownFields();
        }

        private FetchDoneMoreResultsets(boolean noInit) {
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static FetchDoneMoreResultsets getDefaultInstance() {
            return defaultInstance;
        }

        @Override
        public FetchDoneMoreResultsets getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private FetchDoneMoreResultsets(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
            return internal_static_Mysqlx_Resultset_FetchDoneMoreResultsets_descriptor;
        }

        @Override
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return internal_static_Mysqlx_Resultset_FetchDoneMoreResultsets_fieldAccessorTable.ensureFieldAccessorsInitialized(FetchDoneMoreResultsets.class, Builder.class);
        }

        public Parser<FetchDoneMoreResultsets> getParserForType() {
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

        public static FetchDoneMoreResultsets parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static FetchDoneMoreResultsets parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static FetchDoneMoreResultsets parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static FetchDoneMoreResultsets parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static FetchDoneMoreResultsets parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static FetchDoneMoreResultsets parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static FetchDoneMoreResultsets parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static FetchDoneMoreResultsets parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static FetchDoneMoreResultsets parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static FetchDoneMoreResultsets parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override
        public Builder newBuilderForType() {
            return FetchDoneMoreResultsets.newBuilder();
        }

        public static Builder newBuilder(FetchDoneMoreResultsets prototype) {
            return FetchDoneMoreResultsets.newBuilder().mergeFrom(prototype);
        }

        @Override
        public Builder toBuilder() {
            return FetchDoneMoreResultsets.newBuilder(this);
        }

        @Override
        protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        static {
            PARSER = new AbstractParser<FetchDoneMoreResultsets>(){

                @Override
                public FetchDoneMoreResultsets parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                    return new FetchDoneMoreResultsets(input, extensionRegistry);
                }
            };
            defaultInstance = new FetchDoneMoreResultsets(true);
            defaultInstance.initFields();
        }

        public static final class Builder
        extends GeneratedMessage.Builder<Builder>
        implements FetchDoneMoreResultsetsOrBuilder {
            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_Mysqlx_Resultset_FetchDoneMoreResultsets_descriptor;
            }

            @Override
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_Mysqlx_Resultset_FetchDoneMoreResultsets_fieldAccessorTable.ensureFieldAccessorsInitialized(FetchDoneMoreResultsets.class, Builder.class);
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
                return internal_static_Mysqlx_Resultset_FetchDoneMoreResultsets_descriptor;
            }

            @Override
            public FetchDoneMoreResultsets getDefaultInstanceForType() {
                return FetchDoneMoreResultsets.getDefaultInstance();
            }

            @Override
            public FetchDoneMoreResultsets build() {
                FetchDoneMoreResultsets result = this.buildPartial();
                if (!result.isInitialized()) {
                    throw Builder.newUninitializedMessageException(result);
                }
                return result;
            }

            @Override
            public FetchDoneMoreResultsets buildPartial() {
                FetchDoneMoreResultsets result = new FetchDoneMoreResultsets(this);
                this.onBuilt();
                return result;
            }

            @Override
            public Builder mergeFrom(Message other) {
                if (other instanceof FetchDoneMoreResultsets) {
                    return this.mergeFrom((FetchDoneMoreResultsets)other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(FetchDoneMoreResultsets other) {
                if (other == FetchDoneMoreResultsets.getDefaultInstance()) {
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
                FetchDoneMoreResultsets parsedMessage = null;
                try {
                    parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
                } catch (InvalidProtocolBufferException e) {
                    parsedMessage = (FetchDoneMoreResultsets)e.getUnfinishedMessage();
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

    public static interface FetchDoneMoreResultsetsOrBuilder
    extends MessageOrBuilder {
    }

    public static final class FetchDoneMoreOutParams
    extends GeneratedMessage
    implements FetchDoneMoreOutParamsOrBuilder {
        private static final FetchDoneMoreOutParams defaultInstance;
        private final UnknownFieldSet unknownFields;
        public static Parser<FetchDoneMoreOutParams> PARSER;
        private byte memoizedIsInitialized = (byte)-1;
        private int memoizedSerializedSize = -1;
        private static final long serialVersionUID = 0L;

        private FetchDoneMoreOutParams(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.unknownFields = builder.getUnknownFields();
        }

        private FetchDoneMoreOutParams(boolean noInit) {
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static FetchDoneMoreOutParams getDefaultInstance() {
            return defaultInstance;
        }

        @Override
        public FetchDoneMoreOutParams getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private FetchDoneMoreOutParams(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
            return internal_static_Mysqlx_Resultset_FetchDoneMoreOutParams_descriptor;
        }

        @Override
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return internal_static_Mysqlx_Resultset_FetchDoneMoreOutParams_fieldAccessorTable.ensureFieldAccessorsInitialized(FetchDoneMoreOutParams.class, Builder.class);
        }

        public Parser<FetchDoneMoreOutParams> getParserForType() {
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

        public static FetchDoneMoreOutParams parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static FetchDoneMoreOutParams parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static FetchDoneMoreOutParams parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static FetchDoneMoreOutParams parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static FetchDoneMoreOutParams parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static FetchDoneMoreOutParams parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static FetchDoneMoreOutParams parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static FetchDoneMoreOutParams parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static FetchDoneMoreOutParams parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static FetchDoneMoreOutParams parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override
        public Builder newBuilderForType() {
            return FetchDoneMoreOutParams.newBuilder();
        }

        public static Builder newBuilder(FetchDoneMoreOutParams prototype) {
            return FetchDoneMoreOutParams.newBuilder().mergeFrom(prototype);
        }

        @Override
        public Builder toBuilder() {
            return FetchDoneMoreOutParams.newBuilder(this);
        }

        @Override
        protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        static {
            PARSER = new AbstractParser<FetchDoneMoreOutParams>(){

                @Override
                public FetchDoneMoreOutParams parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                    return new FetchDoneMoreOutParams(input, extensionRegistry);
                }
            };
            defaultInstance = new FetchDoneMoreOutParams(true);
            defaultInstance.initFields();
        }

        public static final class Builder
        extends GeneratedMessage.Builder<Builder>
        implements FetchDoneMoreOutParamsOrBuilder {
            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_Mysqlx_Resultset_FetchDoneMoreOutParams_descriptor;
            }

            @Override
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_Mysqlx_Resultset_FetchDoneMoreOutParams_fieldAccessorTable.ensureFieldAccessorsInitialized(FetchDoneMoreOutParams.class, Builder.class);
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
                return internal_static_Mysqlx_Resultset_FetchDoneMoreOutParams_descriptor;
            }

            @Override
            public FetchDoneMoreOutParams getDefaultInstanceForType() {
                return FetchDoneMoreOutParams.getDefaultInstance();
            }

            @Override
            public FetchDoneMoreOutParams build() {
                FetchDoneMoreOutParams result = this.buildPartial();
                if (!result.isInitialized()) {
                    throw Builder.newUninitializedMessageException(result);
                }
                return result;
            }

            @Override
            public FetchDoneMoreOutParams buildPartial() {
                FetchDoneMoreOutParams result = new FetchDoneMoreOutParams(this);
                this.onBuilt();
                return result;
            }

            @Override
            public Builder mergeFrom(Message other) {
                if (other instanceof FetchDoneMoreOutParams) {
                    return this.mergeFrom((FetchDoneMoreOutParams)other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(FetchDoneMoreOutParams other) {
                if (other == FetchDoneMoreOutParams.getDefaultInstance()) {
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
                FetchDoneMoreOutParams parsedMessage = null;
                try {
                    parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
                } catch (InvalidProtocolBufferException e) {
                    parsedMessage = (FetchDoneMoreOutParams)e.getUnfinishedMessage();
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

    public static interface FetchDoneMoreOutParamsOrBuilder
    extends MessageOrBuilder {
    }

    public static enum ContentType_DATETIME implements ProtocolMessageEnum
    {
        DATE(0, 1),
        DATETIME(1, 2);

        public static final int DATE_VALUE = 1;
        public static final int DATETIME_VALUE = 2;
        private static Internal.EnumLiteMap<ContentType_DATETIME> internalValueMap;
        private static final ContentType_DATETIME[] VALUES;
        private final int index;
        private final int value;

        @Override
        public final int getNumber() {
            return this.value;
        }

        public static ContentType_DATETIME valueOf(int value) {
            switch (value) {
                case 1: {
                    return DATE;
                }
                case 2: {
                    return DATETIME;
                }
            }
            return null;
        }

        public static Internal.EnumLiteMap<ContentType_DATETIME> internalGetValueMap() {
            return internalValueMap;
        }

        @Override
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            return ContentType_DATETIME.getDescriptor().getValues().get(this.index);
        }

        @Override
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return ContentType_DATETIME.getDescriptor();
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return MysqlxResultset.getDescriptor().getEnumTypes().get(1);
        }

        public static ContentType_DATETIME valueOf(Descriptors.EnumValueDescriptor desc) {
            if (desc.getType() != ContentType_DATETIME.getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            return VALUES[desc.getIndex()];
        }

        private ContentType_DATETIME(int index, int value) {
            this.index = index;
            this.value = value;
        }

        static {
            internalValueMap = new Internal.EnumLiteMap<ContentType_DATETIME>(){

                @Override
                public ContentType_DATETIME findValueByNumber(int number) {
                    return ContentType_DATETIME.valueOf(number);
                }
            };
            VALUES = ContentType_DATETIME.values();
        }
    }

    public static enum ContentType_BYTES implements ProtocolMessageEnum
    {
        GEOMETRY(0, 1),
        JSON(1, 2),
        XML(2, 3);

        public static final int GEOMETRY_VALUE = 1;
        public static final int JSON_VALUE = 2;
        public static final int XML_VALUE = 3;
        private static Internal.EnumLiteMap<ContentType_BYTES> internalValueMap;
        private static final ContentType_BYTES[] VALUES;
        private final int index;
        private final int value;

        @Override
        public final int getNumber() {
            return this.value;
        }

        public static ContentType_BYTES valueOf(int value) {
            switch (value) {
                case 1: {
                    return GEOMETRY;
                }
                case 2: {
                    return JSON;
                }
                case 3: {
                    return XML;
                }
            }
            return null;
        }

        public static Internal.EnumLiteMap<ContentType_BYTES> internalGetValueMap() {
            return internalValueMap;
        }

        @Override
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            return ContentType_BYTES.getDescriptor().getValues().get(this.index);
        }

        @Override
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return ContentType_BYTES.getDescriptor();
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return MysqlxResultset.getDescriptor().getEnumTypes().get(0);
        }

        public static ContentType_BYTES valueOf(Descriptors.EnumValueDescriptor desc) {
            if (desc.getType() != ContentType_BYTES.getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            return VALUES[desc.getIndex()];
        }

        private ContentType_BYTES(int index, int value) {
            this.index = index;
            this.value = value;
        }

        static {
            internalValueMap = new Internal.EnumLiteMap<ContentType_BYTES>(){

                @Override
                public ContentType_BYTES findValueByNumber(int number) {
                    return ContentType_BYTES.valueOf(number);
                }
            };
            VALUES = ContentType_BYTES.values();
        }
    }
}

