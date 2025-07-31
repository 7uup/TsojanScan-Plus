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
import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectStreamException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class MysqlxExpr {
    private static final Descriptors.Descriptor internal_static_Mysqlx_Expr_Expr_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_Mysqlx_Expr_Expr_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_Mysqlx_Expr_Identifier_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_Mysqlx_Expr_Identifier_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_Mysqlx_Expr_DocumentPathItem_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_Mysqlx_Expr_DocumentPathItem_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_Mysqlx_Expr_ColumnIdentifier_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_Mysqlx_Expr_ColumnIdentifier_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_Mysqlx_Expr_FunctionCall_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_Mysqlx_Expr_FunctionCall_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_Mysqlx_Expr_Operator_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_Mysqlx_Expr_Operator_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_Mysqlx_Expr_Object_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_Mysqlx_Expr_Object_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_Mysqlx_Expr_Object_ObjectField_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_Mysqlx_Expr_Object_ObjectField_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_Mysqlx_Expr_Array_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_Mysqlx_Expr_Array_fieldAccessorTable;
    private static Descriptors.FileDescriptor descriptor;

    private MysqlxExpr() {
    }

    public static void registerAllExtensions(ExtensionRegistry registry) {
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        String[] descriptorData = new String[]{"\n\u0011mysqlx_expr.proto\u0012\u000bMysqlx.Expr\u001a\u0016mysqlx_datatypes.proto\"\u00c4\u0003\n\u0004Expr\u0012$\n\u0004type\u0018\u0001 \u0002(\u000e2\u0016.Mysqlx.Expr.Expr.Type\u00121\n\nidentifier\u0018\u0002 \u0001(\u000b2\u001d.Mysqlx.Expr.ColumnIdentifier\u0012\u0010\n\bvariable\u0018\u0003 \u0001(\t\u0012)\n\u0007literal\u0018\u0004 \u0001(\u000b2\u0018.Mysqlx.Datatypes.Scalar\u00120\n\rfunction_call\u0018\u0005 \u0001(\u000b2\u0019.Mysqlx.Expr.FunctionCall\u0012'\n\boperator\u0018\u0006 \u0001(\u000b2\u0015.Mysqlx.Expr.Operator\u0012\u0010\n\bposition\u0018\u0007 \u0001(\r\u0012#\n\u0006object\u0018\b \u0001(\u000b2\u0013.Mysqlx.Expr.Object\u0012!\n\u0005array\u0018\t \u0001(\u000b2\u0012.Mysqlx.Expr.Array\"q\n\u0004", "Type\u0012\t\n\u0005IDENT\u0010\u0001\u0012\u000b\n\u0007LITERAL\u0010\u0002\u0012\f\n\bVARIABLE\u0010\u0003\u0012\r\n\tFUNC_CALL\u0010\u0004\u0012\f\n\bOPERATOR\u0010\u0005\u0012\u000f\n\u000bPLACEHOLDER\u0010\u0006\u0012\n\n\u0006OBJECT\u0010\u0007\u0012\t\n\u0005ARRAY\u0010\b\"/\n\nIdentifier\u0012\f\n\u0004name\u0018\u0001 \u0002(\t\u0012\u0013\n\u000bschema_name\u0018\u0002 \u0001(\t\"\u00cb\u0001\n\u0010DocumentPathItem\u00120\n\u0004type\u0018\u0001 \u0002(\u000e2\".Mysqlx.Expr.DocumentPathItem.Type\u0012\r\n\u0005value\u0018\u0002 \u0001(\t\u0012\r\n\u0005index\u0018\u0003 \u0001(\r\"g\n\u0004Type\u0012\n\n\u0006MEMBER\u0010\u0001\u0012\u0013\n\u000fMEMBER_ASTERISK\u0010\u0002\u0012\u000f\n\u000bARRAY_INDEX\u0010\u0003\u0012\u0018\n\u0014ARRAY_INDEX_ASTERISK\u0010\u0004\u0012\u0013\n\u000fDOUBLE_ASTERISK\u0010\u0005\"\u007f\n\u0010ColumnIdentifier\u00124\n\rdocument_p", "ath\u0018\u0001 \u0003(\u000b2\u001d.Mysqlx.Expr.DocumentPathItem\u0012\f\n\u0004name\u0018\u0002 \u0001(\t\u0012\u0012\n\ntable_name\u0018\u0003 \u0001(\t\u0012\u0013\n\u000bschema_name\u0018\u0004 \u0001(\t\"W\n\fFunctionCall\u0012%\n\u0004name\u0018\u0001 \u0002(\u000b2\u0017.Mysqlx.Expr.Identifier\u0012 \n\u0005param\u0018\u0002 \u0003(\u000b2\u0011.Mysqlx.Expr.Expr\":\n\bOperator\u0012\f\n\u0004name\u0018\u0001 \u0002(\t\u0012 \n\u0005param\u0018\u0002 \u0003(\u000b2\u0011.Mysqlx.Expr.Expr\"t\n\u0006Object\u0012,\n\u0003fld\u0018\u0001 \u0003(\u000b2\u001f.Mysqlx.Expr.Object.ObjectField\u001a<\n\u000bObjectField\u0012\u000b\n\u0003key\u0018\u0001 \u0002(\t\u0012 \n\u0005value\u0018\u0002 \u0002(\u000b2\u0011.Mysqlx.Expr.Expr\")\n\u0005Array\u0012 \n\u0005value\u0018\u0001 \u0003(\u000b2\u0011.Mysqlx.Expr", ".ExprB\u0019\n\u0017com.mysql.cj.x.protobuf"};
        Descriptors.FileDescriptor.InternalDescriptorAssigner assigner = new Descriptors.FileDescriptor.InternalDescriptorAssigner(){

            @Override
            public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor root) {
                descriptor = root;
                return null;
            }
        };
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[]{MysqlxDatatypes.getDescriptor()}, assigner);
        internal_static_Mysqlx_Expr_Expr_descriptor = MysqlxExpr.getDescriptor().getMessageTypes().get(0);
        internal_static_Mysqlx_Expr_Expr_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_Mysqlx_Expr_Expr_descriptor, new String[]{"Type", "Identifier", "Variable", "Literal", "FunctionCall", "Operator", "Position", "Object", "Array"});
        internal_static_Mysqlx_Expr_Identifier_descriptor = MysqlxExpr.getDescriptor().getMessageTypes().get(1);
        internal_static_Mysqlx_Expr_Identifier_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_Mysqlx_Expr_Identifier_descriptor, new String[]{"Name", "SchemaName"});
        internal_static_Mysqlx_Expr_DocumentPathItem_descriptor = MysqlxExpr.getDescriptor().getMessageTypes().get(2);
        internal_static_Mysqlx_Expr_DocumentPathItem_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_Mysqlx_Expr_DocumentPathItem_descriptor, new String[]{"Type", "Value", "Index"});
        internal_static_Mysqlx_Expr_ColumnIdentifier_descriptor = MysqlxExpr.getDescriptor().getMessageTypes().get(3);
        internal_static_Mysqlx_Expr_ColumnIdentifier_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_Mysqlx_Expr_ColumnIdentifier_descriptor, new String[]{"DocumentPath", "Name", "TableName", "SchemaName"});
        internal_static_Mysqlx_Expr_FunctionCall_descriptor = MysqlxExpr.getDescriptor().getMessageTypes().get(4);
        internal_static_Mysqlx_Expr_FunctionCall_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_Mysqlx_Expr_FunctionCall_descriptor, new String[]{"Name", "Param"});
        internal_static_Mysqlx_Expr_Operator_descriptor = MysqlxExpr.getDescriptor().getMessageTypes().get(5);
        internal_static_Mysqlx_Expr_Operator_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_Mysqlx_Expr_Operator_descriptor, new String[]{"Name", "Param"});
        internal_static_Mysqlx_Expr_Object_descriptor = MysqlxExpr.getDescriptor().getMessageTypes().get(6);
        internal_static_Mysqlx_Expr_Object_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_Mysqlx_Expr_Object_descriptor, new String[]{"Fld"});
        internal_static_Mysqlx_Expr_Object_ObjectField_descriptor = internal_static_Mysqlx_Expr_Object_descriptor.getNestedTypes().get(0);
        internal_static_Mysqlx_Expr_Object_ObjectField_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_Mysqlx_Expr_Object_ObjectField_descriptor, new String[]{"Key", "Value"});
        internal_static_Mysqlx_Expr_Array_descriptor = MysqlxExpr.getDescriptor().getMessageTypes().get(7);
        internal_static_Mysqlx_Expr_Array_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_Mysqlx_Expr_Array_descriptor, new String[]{"Value"});
        MysqlxDatatypes.getDescriptor();
    }

    public static final class Array
    extends GeneratedMessage
    implements ArrayOrBuilder {
        private static final Array defaultInstance;
        private final UnknownFieldSet unknownFields;
        public static Parser<Array> PARSER;
        public static final int VALUE_FIELD_NUMBER = 1;
        private List<Expr> value_;
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
                        this.value_ = new ArrayList<Expr>();
                        mutable_bitField0_ |= true;
                    }
                    this.value_.add(input.readMessage(Expr.PARSER, extensionRegistry));
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
            return internal_static_Mysqlx_Expr_Array_descriptor;
        }

        @Override
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return internal_static_Mysqlx_Expr_Array_fieldAccessorTable.ensureFieldAccessorsInitialized(Array.class, Builder.class);
        }

        public Parser<Array> getParserForType() {
            return PARSER;
        }

        @Override
        public List<Expr> getValueList() {
            return this.value_;
        }

        @Override
        public List<? extends ExprOrBuilder> getValueOrBuilderList() {
            return this.value_;
        }

        @Override
        public int getValueCount() {
            return this.value_.size();
        }

        @Override
        public Expr getValue(int index) {
            return this.value_.get(index);
        }

        @Override
        public ExprOrBuilder getValueOrBuilder(int index) {
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
            private List<Expr> value_ = Collections.emptyList();
            private RepeatedFieldBuilder<Expr, Expr.Builder, ExprOrBuilder> valueBuilder_;

            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_Mysqlx_Expr_Array_descriptor;
            }

            @Override
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_Mysqlx_Expr_Array_fieldAccessorTable.ensureFieldAccessorsInitialized(Array.class, Builder.class);
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
                return internal_static_Mysqlx_Expr_Array_descriptor;
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
                    this.value_ = new ArrayList<Expr>(this.value_);
                    this.bitField0_ |= 1;
                }
            }

            @Override
            public List<Expr> getValueList() {
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
            public Expr getValue(int index) {
                if (this.valueBuilder_ == null) {
                    return this.value_.get(index);
                }
                return this.valueBuilder_.getMessage(index);
            }

            public Builder setValue(int index, Expr value) {
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

            public Builder setValue(int index, Expr.Builder builderForValue) {
                if (this.valueBuilder_ == null) {
                    this.ensureValueIsMutable();
                    this.value_.set(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.valueBuilder_.setMessage(index, builderForValue.build());
                }
                return this;
            }

            public Builder addValue(Expr value) {
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

            public Builder addValue(int index, Expr value) {
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

            public Builder addValue(Expr.Builder builderForValue) {
                if (this.valueBuilder_ == null) {
                    this.ensureValueIsMutable();
                    this.value_.add(builderForValue.build());
                    this.onChanged();
                } else {
                    this.valueBuilder_.addMessage(builderForValue.build());
                }
                return this;
            }

            public Builder addValue(int index, Expr.Builder builderForValue) {
                if (this.valueBuilder_ == null) {
                    this.ensureValueIsMutable();
                    this.value_.add(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.valueBuilder_.addMessage(index, builderForValue.build());
                }
                return this;
            }

            public Builder addAllValue(Iterable<? extends Expr> values2) {
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

            public Expr.Builder getValueBuilder(int index) {
                return this.getValueFieldBuilder().getBuilder(index);
            }

            @Override
            public ExprOrBuilder getValueOrBuilder(int index) {
                if (this.valueBuilder_ == null) {
                    return this.value_.get(index);
                }
                return this.valueBuilder_.getMessageOrBuilder(index);
            }

            @Override
            public List<? extends ExprOrBuilder> getValueOrBuilderList() {
                if (this.valueBuilder_ != null) {
                    return this.valueBuilder_.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.value_);
            }

            public Expr.Builder addValueBuilder() {
                return this.getValueFieldBuilder().addBuilder(Expr.getDefaultInstance());
            }

            public Expr.Builder addValueBuilder(int index) {
                return this.getValueFieldBuilder().addBuilder(index, Expr.getDefaultInstance());
            }

            public List<Expr.Builder> getValueBuilderList() {
                return this.getValueFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilder<Expr, Expr.Builder, ExprOrBuilder> getValueFieldBuilder() {
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
        public List<Expr> getValueList();

        public Expr getValue(int var1);

        public int getValueCount();

        public List<? extends ExprOrBuilder> getValueOrBuilderList();

        public ExprOrBuilder getValueOrBuilder(int var1);
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
            return internal_static_Mysqlx_Expr_Object_descriptor;
        }

        @Override
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return internal_static_Mysqlx_Expr_Object_fieldAccessorTable.ensureFieldAccessorsInitialized(Object.class, Builder.class);
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
                return internal_static_Mysqlx_Expr_Object_descriptor;
            }

            @Override
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_Mysqlx_Expr_Object_fieldAccessorTable.ensureFieldAccessorsInitialized(Object.class, Builder.class);
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
                return internal_static_Mysqlx_Expr_Object_descriptor;
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
            private Expr value_;
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
                        Expr.Builder subBuilder = null;
                        if ((this.bitField0_ & 2) == 2) {
                            subBuilder = this.value_.toBuilder();
                        }
                        this.value_ = input.readMessage(Expr.PARSER, extensionRegistry);
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
                return internal_static_Mysqlx_Expr_Object_ObjectField_descriptor;
            }

            @Override
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_Mysqlx_Expr_Object_ObjectField_fieldAccessorTable.ensureFieldAccessorsInitialized(ObjectField.class, Builder.class);
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
            public Expr getValue() {
                return this.value_;
            }

            @Override
            public ExprOrBuilder getValueOrBuilder() {
                return this.value_;
            }

            private void initFields() {
                this.key_ = "";
                this.value_ = Expr.getDefaultInstance();
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
                private Expr value_ = Expr.getDefaultInstance();
                private SingleFieldBuilder<Expr, Expr.Builder, ExprOrBuilder> valueBuilder_;

                public static final Descriptors.Descriptor getDescriptor() {
                    return internal_static_Mysqlx_Expr_Object_ObjectField_descriptor;
                }

                @Override
                protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                    return internal_static_Mysqlx_Expr_Object_ObjectField_fieldAccessorTable.ensureFieldAccessorsInitialized(ObjectField.class, Builder.class);
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
                        this.value_ = Expr.getDefaultInstance();
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
                    return internal_static_Mysqlx_Expr_Object_ObjectField_descriptor;
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
                public Expr getValue() {
                    if (this.valueBuilder_ == null) {
                        return this.value_;
                    }
                    return this.valueBuilder_.getMessage();
                }

                public Builder setValue(Expr value) {
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

                public Builder setValue(Expr.Builder builderForValue) {
                    if (this.valueBuilder_ == null) {
                        this.value_ = builderForValue.build();
                        this.onChanged();
                    } else {
                        this.valueBuilder_.setMessage(builderForValue.build());
                    }
                    this.bitField0_ |= 2;
                    return this;
                }

                public Builder mergeValue(Expr value) {
                    if (this.valueBuilder_ == null) {
                        this.value_ = (this.bitField0_ & 2) == 2 && this.value_ != Expr.getDefaultInstance() ? Expr.newBuilder(this.value_).mergeFrom(value).buildPartial() : value;
                        this.onChanged();
                    } else {
                        this.valueBuilder_.mergeFrom(value);
                    }
                    this.bitField0_ |= 2;
                    return this;
                }

                public Builder clearValue() {
                    if (this.valueBuilder_ == null) {
                        this.value_ = Expr.getDefaultInstance();
                        this.onChanged();
                    } else {
                        this.valueBuilder_.clear();
                    }
                    this.bitField0_ &= 0xFFFFFFFD;
                    return this;
                }

                public Expr.Builder getValueBuilder() {
                    this.bitField0_ |= 2;
                    this.onChanged();
                    return this.getValueFieldBuilder().getBuilder();
                }

                @Override
                public ExprOrBuilder getValueOrBuilder() {
                    if (this.valueBuilder_ != null) {
                        return this.valueBuilder_.getMessageOrBuilder();
                    }
                    return this.value_;
                }

                private SingleFieldBuilder<Expr, Expr.Builder, ExprOrBuilder> getValueFieldBuilder() {
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

            public Expr getValue();

            public ExprOrBuilder getValueOrBuilder();
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

    public static final class Operator
    extends GeneratedMessage
    implements OperatorOrBuilder {
        private static final Operator defaultInstance;
        private final UnknownFieldSet unknownFields;
        public static Parser<Operator> PARSER;
        private int bitField0_;
        public static final int NAME_FIELD_NUMBER = 1;
        private java.lang.Object name_;
        public static final int PARAM_FIELD_NUMBER = 2;
        private List<Expr> param_;
        private byte memoizedIsInitialized = (byte)-1;
        private int memoizedSerializedSize = -1;
        private static final long serialVersionUID = 0L;

        private Operator(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.unknownFields = builder.getUnknownFields();
        }

        private Operator(boolean noInit) {
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static Operator getDefaultInstance() {
            return defaultInstance;
        }

        @Override
        public Operator getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private Operator(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
                        case 10: {
                            ByteString bs = input.readBytes();
                            this.bitField0_ |= 1;
                            this.name_ = bs;
                            continue block11;
                        }
                        case 18: 
                    }
                    if ((mutable_bitField0_ & 2) != 2) {
                        this.param_ = new ArrayList<Expr>();
                        mutable_bitField0_ |= 2;
                    }
                    this.param_.add(input.readMessage(Expr.PARSER, extensionRegistry));
                }
            } catch (InvalidProtocolBufferException e) {
                throw e.setUnfinishedMessage(this);
            } catch (IOException e) {
                throw new InvalidProtocolBufferException(e.getMessage()).setUnfinishedMessage(this);
            } finally {
                if ((mutable_bitField0_ & 2) == 2) {
                    this.param_ = Collections.unmodifiableList(this.param_);
                }
                this.unknownFields = unknownFields.build();
                this.makeExtensionsImmutable();
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return internal_static_Mysqlx_Expr_Operator_descriptor;
        }

        @Override
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return internal_static_Mysqlx_Expr_Operator_fieldAccessorTable.ensureFieldAccessorsInitialized(Operator.class, Builder.class);
        }

        public Parser<Operator> getParserForType() {
            return PARSER;
        }

        @Override
        public boolean hasName() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override
        public String getName() {
            java.lang.Object ref = this.name_;
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
            java.lang.Object ref = this.name_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.name_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        @Override
        public List<Expr> getParamList() {
            return this.param_;
        }

        @Override
        public List<? extends ExprOrBuilder> getParamOrBuilderList() {
            return this.param_;
        }

        @Override
        public int getParamCount() {
            return this.param_.size();
        }

        @Override
        public Expr getParam(int index) {
            return this.param_.get(index);
        }

        @Override
        public ExprOrBuilder getParamOrBuilder(int index) {
            return this.param_.get(index);
        }

        private void initFields() {
            this.name_ = "";
            this.param_ = Collections.emptyList();
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
            for (int i = 0; i < this.getParamCount(); ++i) {
                if (this.getParam(i).isInitialized()) continue;
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
            for (int i = 0; i < this.param_.size(); ++i) {
                output.writeMessage(2, this.param_.get(i));
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
            for (int i = 0; i < this.param_.size(); ++i) {
                size += CodedOutputStream.computeMessageSize(2, this.param_.get(i));
            }
            this.memoizedSerializedSize = size += this.getUnknownFields().getSerializedSize();
            return size;
        }

        @Override
        protected java.lang.Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static Operator parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static Operator parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static Operator parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static Operator parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static Operator parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static Operator parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Operator parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static Operator parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static Operator parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static Operator parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override
        public Builder newBuilderForType() {
            return Operator.newBuilder();
        }

        public static Builder newBuilder(Operator prototype) {
            return Operator.newBuilder().mergeFrom(prototype);
        }

        @Override
        public Builder toBuilder() {
            return Operator.newBuilder(this);
        }

        @Override
        protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        static {
            PARSER = new AbstractParser<Operator>(){

                @Override
                public Operator parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                    return new Operator(input, extensionRegistry);
                }
            };
            defaultInstance = new Operator(true);
            defaultInstance.initFields();
        }

        public static final class Builder
        extends GeneratedMessage.Builder<Builder>
        implements OperatorOrBuilder {
            private int bitField0_;
            private java.lang.Object name_ = "";
            private List<Expr> param_ = Collections.emptyList();
            private RepeatedFieldBuilder<Expr, Expr.Builder, ExprOrBuilder> paramBuilder_;

            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_Mysqlx_Expr_Operator_descriptor;
            }

            @Override
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_Mysqlx_Expr_Operator_fieldAccessorTable.ensureFieldAccessorsInitialized(Operator.class, Builder.class);
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
                    this.getParamFieldBuilder();
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
                if (this.paramBuilder_ == null) {
                    this.param_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFFFFD;
                } else {
                    this.paramBuilder_.clear();
                }
                return this;
            }

            @Override
            public Builder clone() {
                return Builder.create().mergeFrom(this.buildPartial());
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return internal_static_Mysqlx_Expr_Operator_descriptor;
            }

            @Override
            public Operator getDefaultInstanceForType() {
                return Operator.getDefaultInstance();
            }

            @Override
            public Operator build() {
                Operator result = this.buildPartial();
                if (!result.isInitialized()) {
                    throw Builder.newUninitializedMessageException(result);
                }
                return result;
            }

            @Override
            public Operator buildPartial() {
                Operator result = new Operator(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ |= 1;
                }
                result.name_ = this.name_;
                if (this.paramBuilder_ == null) {
                    if ((this.bitField0_ & 2) == 2) {
                        this.param_ = Collections.unmodifiableList(this.param_);
                        this.bitField0_ &= 0xFFFFFFFD;
                    }
                    result.param_ = this.param_;
                } else {
                    result.param_ = this.paramBuilder_.build();
                }
                result.bitField0_ = to_bitField0_;
                this.onBuilt();
                return result;
            }

            @Override
            public Builder mergeFrom(Message other) {
                if (other instanceof Operator) {
                    return this.mergeFrom((Operator)other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(Operator other) {
                if (other == Operator.getDefaultInstance()) {
                    return this;
                }
                if (other.hasName()) {
                    this.bitField0_ |= 1;
                    this.name_ = other.name_;
                    this.onChanged();
                }
                if (this.paramBuilder_ == null) {
                    if (!other.param_.isEmpty()) {
                        if (this.param_.isEmpty()) {
                            this.param_ = other.param_;
                            this.bitField0_ &= 0xFFFFFFFD;
                        } else {
                            this.ensureParamIsMutable();
                            this.param_.addAll(other.param_);
                        }
                        this.onChanged();
                    }
                } else if (!other.param_.isEmpty()) {
                    if (this.paramBuilder_.isEmpty()) {
                        this.paramBuilder_.dispose();
                        this.paramBuilder_ = null;
                        this.param_ = other.param_;
                        this.bitField0_ &= 0xFFFFFFFD;
                        this.paramBuilder_ = alwaysUseFieldBuilders ? this.getParamFieldBuilder() : null;
                    } else {
                        this.paramBuilder_.addAllMessages(other.param_);
                    }
                }
                this.mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            @Override
            public final boolean isInitialized() {
                if (!this.hasName()) {
                    return false;
                }
                for (int i = 0; i < this.getParamCount(); ++i) {
                    if (this.getParam(i).isInitialized()) continue;
                    return false;
                }
                return true;
            }

            @Override
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                Operator parsedMessage = null;
                try {
                    parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
                } catch (InvalidProtocolBufferException e) {
                    parsedMessage = (Operator)e.getUnfinishedMessage();
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
                java.lang.Object ref = this.name_;
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
                java.lang.Object ref = this.name_;
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
                this.name_ = Operator.getDefaultInstance().getName();
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

            private void ensureParamIsMutable() {
                if ((this.bitField0_ & 2) != 2) {
                    this.param_ = new ArrayList<Expr>(this.param_);
                    this.bitField0_ |= 2;
                }
            }

            @Override
            public List<Expr> getParamList() {
                if (this.paramBuilder_ == null) {
                    return Collections.unmodifiableList(this.param_);
                }
                return this.paramBuilder_.getMessageList();
            }

            @Override
            public int getParamCount() {
                if (this.paramBuilder_ == null) {
                    return this.param_.size();
                }
                return this.paramBuilder_.getCount();
            }

            @Override
            public Expr getParam(int index) {
                if (this.paramBuilder_ == null) {
                    return this.param_.get(index);
                }
                return this.paramBuilder_.getMessage(index);
            }

            public Builder setParam(int index, Expr value) {
                if (this.paramBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureParamIsMutable();
                    this.param_.set(index, value);
                    this.onChanged();
                } else {
                    this.paramBuilder_.setMessage(index, value);
                }
                return this;
            }

            public Builder setParam(int index, Expr.Builder builderForValue) {
                if (this.paramBuilder_ == null) {
                    this.ensureParamIsMutable();
                    this.param_.set(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.paramBuilder_.setMessage(index, builderForValue.build());
                }
                return this;
            }

            public Builder addParam(Expr value) {
                if (this.paramBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureParamIsMutable();
                    this.param_.add(value);
                    this.onChanged();
                } else {
                    this.paramBuilder_.addMessage(value);
                }
                return this;
            }

            public Builder addParam(int index, Expr value) {
                if (this.paramBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureParamIsMutable();
                    this.param_.add(index, value);
                    this.onChanged();
                } else {
                    this.paramBuilder_.addMessage(index, value);
                }
                return this;
            }

            public Builder addParam(Expr.Builder builderForValue) {
                if (this.paramBuilder_ == null) {
                    this.ensureParamIsMutable();
                    this.param_.add(builderForValue.build());
                    this.onChanged();
                } else {
                    this.paramBuilder_.addMessage(builderForValue.build());
                }
                return this;
            }

            public Builder addParam(int index, Expr.Builder builderForValue) {
                if (this.paramBuilder_ == null) {
                    this.ensureParamIsMutable();
                    this.param_.add(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.paramBuilder_.addMessage(index, builderForValue.build());
                }
                return this;
            }

            public Builder addAllParam(Iterable<? extends Expr> values2) {
                if (this.paramBuilder_ == null) {
                    this.ensureParamIsMutable();
                    AbstractMessageLite.Builder.addAll(values2, this.param_);
                    this.onChanged();
                } else {
                    this.paramBuilder_.addAllMessages(values2);
                }
                return this;
            }

            public Builder clearParam() {
                if (this.paramBuilder_ == null) {
                    this.param_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFFFFD;
                    this.onChanged();
                } else {
                    this.paramBuilder_.clear();
                }
                return this;
            }

            public Builder removeParam(int index) {
                if (this.paramBuilder_ == null) {
                    this.ensureParamIsMutable();
                    this.param_.remove(index);
                    this.onChanged();
                } else {
                    this.paramBuilder_.remove(index);
                }
                return this;
            }

            public Expr.Builder getParamBuilder(int index) {
                return this.getParamFieldBuilder().getBuilder(index);
            }

            @Override
            public ExprOrBuilder getParamOrBuilder(int index) {
                if (this.paramBuilder_ == null) {
                    return this.param_.get(index);
                }
                return this.paramBuilder_.getMessageOrBuilder(index);
            }

            @Override
            public List<? extends ExprOrBuilder> getParamOrBuilderList() {
                if (this.paramBuilder_ != null) {
                    return this.paramBuilder_.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.param_);
            }

            public Expr.Builder addParamBuilder() {
                return this.getParamFieldBuilder().addBuilder(Expr.getDefaultInstance());
            }

            public Expr.Builder addParamBuilder(int index) {
                return this.getParamFieldBuilder().addBuilder(index, Expr.getDefaultInstance());
            }

            public List<Expr.Builder> getParamBuilderList() {
                return this.getParamFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilder<Expr, Expr.Builder, ExprOrBuilder> getParamFieldBuilder() {
                if (this.paramBuilder_ == null) {
                    this.paramBuilder_ = new RepeatedFieldBuilder(this.param_, (this.bitField0_ & 2) == 2, this.getParentForChildren(), this.isClean());
                    this.param_ = null;
                }
                return this.paramBuilder_;
            }
        }
    }

    public static interface OperatorOrBuilder
    extends MessageOrBuilder {
        public boolean hasName();

        public String getName();

        public ByteString getNameBytes();

        public List<Expr> getParamList();

        public Expr getParam(int var1);

        public int getParamCount();

        public List<? extends ExprOrBuilder> getParamOrBuilderList();

        public ExprOrBuilder getParamOrBuilder(int var1);
    }

    public static final class FunctionCall
    extends GeneratedMessage
    implements FunctionCallOrBuilder {
        private static final FunctionCall defaultInstance;
        private final UnknownFieldSet unknownFields;
        public static Parser<FunctionCall> PARSER;
        private int bitField0_;
        public static final int NAME_FIELD_NUMBER = 1;
        private Identifier name_;
        public static final int PARAM_FIELD_NUMBER = 2;
        private List<Expr> param_;
        private byte memoizedIsInitialized = (byte)-1;
        private int memoizedSerializedSize = -1;
        private static final long serialVersionUID = 0L;

        private FunctionCall(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.unknownFields = builder.getUnknownFields();
        }

        private FunctionCall(boolean noInit) {
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static FunctionCall getDefaultInstance() {
            return defaultInstance;
        }

        @Override
        public FunctionCall getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private FunctionCall(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
                        case 10: {
                            Identifier.Builder subBuilder = null;
                            if ((this.bitField0_ & 1) == 1) {
                                subBuilder = this.name_.toBuilder();
                            }
                            this.name_ = input.readMessage(Identifier.PARSER, extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom(this.name_);
                                this.name_ = subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 1;
                            continue block11;
                        }
                        case 18: 
                    }
                    if ((mutable_bitField0_ & 2) != 2) {
                        this.param_ = new ArrayList<Expr>();
                        mutable_bitField0_ |= 2;
                    }
                    this.param_.add(input.readMessage(Expr.PARSER, extensionRegistry));
                }
            } catch (InvalidProtocolBufferException e) {
                throw e.setUnfinishedMessage(this);
            } catch (IOException e) {
                throw new InvalidProtocolBufferException(e.getMessage()).setUnfinishedMessage(this);
            } finally {
                if ((mutable_bitField0_ & 2) == 2) {
                    this.param_ = Collections.unmodifiableList(this.param_);
                }
                this.unknownFields = unknownFields.build();
                this.makeExtensionsImmutable();
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return internal_static_Mysqlx_Expr_FunctionCall_descriptor;
        }

        @Override
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return internal_static_Mysqlx_Expr_FunctionCall_fieldAccessorTable.ensureFieldAccessorsInitialized(FunctionCall.class, Builder.class);
        }

        public Parser<FunctionCall> getParserForType() {
            return PARSER;
        }

        @Override
        public boolean hasName() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override
        public Identifier getName() {
            return this.name_;
        }

        @Override
        public IdentifierOrBuilder getNameOrBuilder() {
            return this.name_;
        }

        @Override
        public List<Expr> getParamList() {
            return this.param_;
        }

        @Override
        public List<? extends ExprOrBuilder> getParamOrBuilderList() {
            return this.param_;
        }

        @Override
        public int getParamCount() {
            return this.param_.size();
        }

        @Override
        public Expr getParam(int index) {
            return this.param_.get(index);
        }

        @Override
        public ExprOrBuilder getParamOrBuilder(int index) {
            return this.param_.get(index);
        }

        private void initFields() {
            this.name_ = Identifier.getDefaultInstance();
            this.param_ = Collections.emptyList();
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
            if (!this.getName().isInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            for (int i = 0; i < this.getParamCount(); ++i) {
                if (this.getParam(i).isInitialized()) continue;
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
                output.writeMessage(1, this.name_);
            }
            for (int i = 0; i < this.param_.size(); ++i) {
                output.writeMessage(2, this.param_.get(i));
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
                size += CodedOutputStream.computeMessageSize(1, this.name_);
            }
            for (int i = 0; i < this.param_.size(); ++i) {
                size += CodedOutputStream.computeMessageSize(2, this.param_.get(i));
            }
            this.memoizedSerializedSize = size += this.getUnknownFields().getSerializedSize();
            return size;
        }

        @Override
        protected java.lang.Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static FunctionCall parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static FunctionCall parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static FunctionCall parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static FunctionCall parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static FunctionCall parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static FunctionCall parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static FunctionCall parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static FunctionCall parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static FunctionCall parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static FunctionCall parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override
        public Builder newBuilderForType() {
            return FunctionCall.newBuilder();
        }

        public static Builder newBuilder(FunctionCall prototype) {
            return FunctionCall.newBuilder().mergeFrom(prototype);
        }

        @Override
        public Builder toBuilder() {
            return FunctionCall.newBuilder(this);
        }

        @Override
        protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        static {
            PARSER = new AbstractParser<FunctionCall>(){

                @Override
                public FunctionCall parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                    return new FunctionCall(input, extensionRegistry);
                }
            };
            defaultInstance = new FunctionCall(true);
            defaultInstance.initFields();
        }

        public static final class Builder
        extends GeneratedMessage.Builder<Builder>
        implements FunctionCallOrBuilder {
            private int bitField0_;
            private Identifier name_ = Identifier.getDefaultInstance();
            private SingleFieldBuilder<Identifier, Identifier.Builder, IdentifierOrBuilder> nameBuilder_;
            private List<Expr> param_ = Collections.emptyList();
            private RepeatedFieldBuilder<Expr, Expr.Builder, ExprOrBuilder> paramBuilder_;

            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_Mysqlx_Expr_FunctionCall_descriptor;
            }

            @Override
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_Mysqlx_Expr_FunctionCall_fieldAccessorTable.ensureFieldAccessorsInitialized(FunctionCall.class, Builder.class);
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
                    this.getNameFieldBuilder();
                    this.getParamFieldBuilder();
                }
            }

            private static Builder create() {
                return new Builder();
            }

            @Override
            public Builder clear() {
                super.clear();
                if (this.nameBuilder_ == null) {
                    this.name_ = Identifier.getDefaultInstance();
                } else {
                    this.nameBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFFFE;
                if (this.paramBuilder_ == null) {
                    this.param_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFFFFD;
                } else {
                    this.paramBuilder_.clear();
                }
                return this;
            }

            @Override
            public Builder clone() {
                return Builder.create().mergeFrom(this.buildPartial());
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return internal_static_Mysqlx_Expr_FunctionCall_descriptor;
            }

            @Override
            public FunctionCall getDefaultInstanceForType() {
                return FunctionCall.getDefaultInstance();
            }

            @Override
            public FunctionCall build() {
                FunctionCall result = this.buildPartial();
                if (!result.isInitialized()) {
                    throw Builder.newUninitializedMessageException(result);
                }
                return result;
            }

            @Override
            public FunctionCall buildPartial() {
                FunctionCall result = new FunctionCall(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ |= 1;
                }
                if (this.nameBuilder_ == null) {
                    result.name_ = this.name_;
                } else {
                    result.name_ = this.nameBuilder_.build();
                }
                if (this.paramBuilder_ == null) {
                    if ((this.bitField0_ & 2) == 2) {
                        this.param_ = Collections.unmodifiableList(this.param_);
                        this.bitField0_ &= 0xFFFFFFFD;
                    }
                    result.param_ = this.param_;
                } else {
                    result.param_ = this.paramBuilder_.build();
                }
                result.bitField0_ = to_bitField0_;
                this.onBuilt();
                return result;
            }

            @Override
            public Builder mergeFrom(Message other) {
                if (other instanceof FunctionCall) {
                    return this.mergeFrom((FunctionCall)other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(FunctionCall other) {
                if (other == FunctionCall.getDefaultInstance()) {
                    return this;
                }
                if (other.hasName()) {
                    this.mergeName(other.getName());
                }
                if (this.paramBuilder_ == null) {
                    if (!other.param_.isEmpty()) {
                        if (this.param_.isEmpty()) {
                            this.param_ = other.param_;
                            this.bitField0_ &= 0xFFFFFFFD;
                        } else {
                            this.ensureParamIsMutable();
                            this.param_.addAll(other.param_);
                        }
                        this.onChanged();
                    }
                } else if (!other.param_.isEmpty()) {
                    if (this.paramBuilder_.isEmpty()) {
                        this.paramBuilder_.dispose();
                        this.paramBuilder_ = null;
                        this.param_ = other.param_;
                        this.bitField0_ &= 0xFFFFFFFD;
                        this.paramBuilder_ = alwaysUseFieldBuilders ? this.getParamFieldBuilder() : null;
                    } else {
                        this.paramBuilder_.addAllMessages(other.param_);
                    }
                }
                this.mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            @Override
            public final boolean isInitialized() {
                if (!this.hasName()) {
                    return false;
                }
                if (!this.getName().isInitialized()) {
                    return false;
                }
                for (int i = 0; i < this.getParamCount(); ++i) {
                    if (this.getParam(i).isInitialized()) continue;
                    return false;
                }
                return true;
            }

            @Override
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                FunctionCall parsedMessage = null;
                try {
                    parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
                } catch (InvalidProtocolBufferException e) {
                    parsedMessage = (FunctionCall)e.getUnfinishedMessage();
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
            public Identifier getName() {
                if (this.nameBuilder_ == null) {
                    return this.name_;
                }
                return this.nameBuilder_.getMessage();
            }

            public Builder setName(Identifier value) {
                if (this.nameBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.name_ = value;
                    this.onChanged();
                } else {
                    this.nameBuilder_.setMessage(value);
                }
                this.bitField0_ |= 1;
                return this;
            }

            public Builder setName(Identifier.Builder builderForValue) {
                if (this.nameBuilder_ == null) {
                    this.name_ = builderForValue.build();
                    this.onChanged();
                } else {
                    this.nameBuilder_.setMessage(builderForValue.build());
                }
                this.bitField0_ |= 1;
                return this;
            }

            public Builder mergeName(Identifier value) {
                if (this.nameBuilder_ == null) {
                    this.name_ = (this.bitField0_ & 1) == 1 && this.name_ != Identifier.getDefaultInstance() ? Identifier.newBuilder(this.name_).mergeFrom(value).buildPartial() : value;
                    this.onChanged();
                } else {
                    this.nameBuilder_.mergeFrom(value);
                }
                this.bitField0_ |= 1;
                return this;
            }

            public Builder clearName() {
                if (this.nameBuilder_ == null) {
                    this.name_ = Identifier.getDefaultInstance();
                    this.onChanged();
                } else {
                    this.nameBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFFFE;
                return this;
            }

            public Identifier.Builder getNameBuilder() {
                this.bitField0_ |= 1;
                this.onChanged();
                return this.getNameFieldBuilder().getBuilder();
            }

            @Override
            public IdentifierOrBuilder getNameOrBuilder() {
                if (this.nameBuilder_ != null) {
                    return this.nameBuilder_.getMessageOrBuilder();
                }
                return this.name_;
            }

            private SingleFieldBuilder<Identifier, Identifier.Builder, IdentifierOrBuilder> getNameFieldBuilder() {
                if (this.nameBuilder_ == null) {
                    this.nameBuilder_ = new SingleFieldBuilder(this.getName(), this.getParentForChildren(), this.isClean());
                    this.name_ = null;
                }
                return this.nameBuilder_;
            }

            private void ensureParamIsMutable() {
                if ((this.bitField0_ & 2) != 2) {
                    this.param_ = new ArrayList<Expr>(this.param_);
                    this.bitField0_ |= 2;
                }
            }

            @Override
            public List<Expr> getParamList() {
                if (this.paramBuilder_ == null) {
                    return Collections.unmodifiableList(this.param_);
                }
                return this.paramBuilder_.getMessageList();
            }

            @Override
            public int getParamCount() {
                if (this.paramBuilder_ == null) {
                    return this.param_.size();
                }
                return this.paramBuilder_.getCount();
            }

            @Override
            public Expr getParam(int index) {
                if (this.paramBuilder_ == null) {
                    return this.param_.get(index);
                }
                return this.paramBuilder_.getMessage(index);
            }

            public Builder setParam(int index, Expr value) {
                if (this.paramBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureParamIsMutable();
                    this.param_.set(index, value);
                    this.onChanged();
                } else {
                    this.paramBuilder_.setMessage(index, value);
                }
                return this;
            }

            public Builder setParam(int index, Expr.Builder builderForValue) {
                if (this.paramBuilder_ == null) {
                    this.ensureParamIsMutable();
                    this.param_.set(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.paramBuilder_.setMessage(index, builderForValue.build());
                }
                return this;
            }

            public Builder addParam(Expr value) {
                if (this.paramBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureParamIsMutable();
                    this.param_.add(value);
                    this.onChanged();
                } else {
                    this.paramBuilder_.addMessage(value);
                }
                return this;
            }

            public Builder addParam(int index, Expr value) {
                if (this.paramBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureParamIsMutable();
                    this.param_.add(index, value);
                    this.onChanged();
                } else {
                    this.paramBuilder_.addMessage(index, value);
                }
                return this;
            }

            public Builder addParam(Expr.Builder builderForValue) {
                if (this.paramBuilder_ == null) {
                    this.ensureParamIsMutable();
                    this.param_.add(builderForValue.build());
                    this.onChanged();
                } else {
                    this.paramBuilder_.addMessage(builderForValue.build());
                }
                return this;
            }

            public Builder addParam(int index, Expr.Builder builderForValue) {
                if (this.paramBuilder_ == null) {
                    this.ensureParamIsMutable();
                    this.param_.add(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.paramBuilder_.addMessage(index, builderForValue.build());
                }
                return this;
            }

            public Builder addAllParam(Iterable<? extends Expr> values2) {
                if (this.paramBuilder_ == null) {
                    this.ensureParamIsMutable();
                    AbstractMessageLite.Builder.addAll(values2, this.param_);
                    this.onChanged();
                } else {
                    this.paramBuilder_.addAllMessages(values2);
                }
                return this;
            }

            public Builder clearParam() {
                if (this.paramBuilder_ == null) {
                    this.param_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFFFFD;
                    this.onChanged();
                } else {
                    this.paramBuilder_.clear();
                }
                return this;
            }

            public Builder removeParam(int index) {
                if (this.paramBuilder_ == null) {
                    this.ensureParamIsMutable();
                    this.param_.remove(index);
                    this.onChanged();
                } else {
                    this.paramBuilder_.remove(index);
                }
                return this;
            }

            public Expr.Builder getParamBuilder(int index) {
                return this.getParamFieldBuilder().getBuilder(index);
            }

            @Override
            public ExprOrBuilder getParamOrBuilder(int index) {
                if (this.paramBuilder_ == null) {
                    return this.param_.get(index);
                }
                return this.paramBuilder_.getMessageOrBuilder(index);
            }

            @Override
            public List<? extends ExprOrBuilder> getParamOrBuilderList() {
                if (this.paramBuilder_ != null) {
                    return this.paramBuilder_.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.param_);
            }

            public Expr.Builder addParamBuilder() {
                return this.getParamFieldBuilder().addBuilder(Expr.getDefaultInstance());
            }

            public Expr.Builder addParamBuilder(int index) {
                return this.getParamFieldBuilder().addBuilder(index, Expr.getDefaultInstance());
            }

            public List<Expr.Builder> getParamBuilderList() {
                return this.getParamFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilder<Expr, Expr.Builder, ExprOrBuilder> getParamFieldBuilder() {
                if (this.paramBuilder_ == null) {
                    this.paramBuilder_ = new RepeatedFieldBuilder(this.param_, (this.bitField0_ & 2) == 2, this.getParentForChildren(), this.isClean());
                    this.param_ = null;
                }
                return this.paramBuilder_;
            }
        }
    }

    public static interface FunctionCallOrBuilder
    extends MessageOrBuilder {
        public boolean hasName();

        public Identifier getName();

        public IdentifierOrBuilder getNameOrBuilder();

        public List<Expr> getParamList();

        public Expr getParam(int var1);

        public int getParamCount();

        public List<? extends ExprOrBuilder> getParamOrBuilderList();

        public ExprOrBuilder getParamOrBuilder(int var1);
    }

    public static final class ColumnIdentifier
    extends GeneratedMessage
    implements ColumnIdentifierOrBuilder {
        private static final ColumnIdentifier defaultInstance;
        private final UnknownFieldSet unknownFields;
        public static Parser<ColumnIdentifier> PARSER;
        private int bitField0_;
        public static final int DOCUMENT_PATH_FIELD_NUMBER = 1;
        private List<DocumentPathItem> documentPath_;
        public static final int NAME_FIELD_NUMBER = 2;
        private java.lang.Object name_;
        public static final int TABLE_NAME_FIELD_NUMBER = 3;
        private java.lang.Object tableName_;
        public static final int SCHEMA_NAME_FIELD_NUMBER = 4;
        private java.lang.Object schemaName_;
        private byte memoizedIsInitialized = (byte)-1;
        private int memoizedSerializedSize = -1;
        private static final long serialVersionUID = 0L;

        private ColumnIdentifier(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.unknownFields = builder.getUnknownFields();
        }

        private ColumnIdentifier(boolean noInit) {
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static ColumnIdentifier getDefaultInstance() {
            return defaultInstance;
        }

        @Override
        public ColumnIdentifier getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private ColumnIdentifier(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this.initFields();
            boolean mutable_bitField0_ = false;
            UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
            try {
                boolean done = false;
                block13: while (!done) {
                    ByteString bs;
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
                            if (!(mutable_bitField0_ & true)) {
                                this.documentPath_ = new ArrayList<DocumentPathItem>();
                                mutable_bitField0_ |= true;
                            }
                            this.documentPath_.add(input.readMessage(DocumentPathItem.PARSER, extensionRegistry));
                            continue block13;
                        }
                        case 18: {
                            bs = input.readBytes();
                            this.bitField0_ |= 1;
                            this.name_ = bs;
                            continue block13;
                        }
                        case 26: {
                            bs = input.readBytes();
                            this.bitField0_ |= 2;
                            this.tableName_ = bs;
                            continue block13;
                        }
                        case 34: 
                    }
                    bs = input.readBytes();
                    this.bitField0_ |= 4;
                    this.schemaName_ = bs;
                }
            } catch (InvalidProtocolBufferException e) {
                throw e.setUnfinishedMessage(this);
            } catch (IOException e) {
                throw new InvalidProtocolBufferException(e.getMessage()).setUnfinishedMessage(this);
            } finally {
                if (mutable_bitField0_ & true) {
                    this.documentPath_ = Collections.unmodifiableList(this.documentPath_);
                }
                this.unknownFields = unknownFields.build();
                this.makeExtensionsImmutable();
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return internal_static_Mysqlx_Expr_ColumnIdentifier_descriptor;
        }

        @Override
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return internal_static_Mysqlx_Expr_ColumnIdentifier_fieldAccessorTable.ensureFieldAccessorsInitialized(ColumnIdentifier.class, Builder.class);
        }

        public Parser<ColumnIdentifier> getParserForType() {
            return PARSER;
        }

        @Override
        public List<DocumentPathItem> getDocumentPathList() {
            return this.documentPath_;
        }

        @Override
        public List<? extends DocumentPathItemOrBuilder> getDocumentPathOrBuilderList() {
            return this.documentPath_;
        }

        @Override
        public int getDocumentPathCount() {
            return this.documentPath_.size();
        }

        @Override
        public DocumentPathItem getDocumentPath(int index) {
            return this.documentPath_.get(index);
        }

        @Override
        public DocumentPathItemOrBuilder getDocumentPathOrBuilder(int index) {
            return this.documentPath_.get(index);
        }

        @Override
        public boolean hasName() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override
        public String getName() {
            java.lang.Object ref = this.name_;
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
            java.lang.Object ref = this.name_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.name_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        @Override
        public boolean hasTableName() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override
        public String getTableName() {
            java.lang.Object ref = this.tableName_;
            if (ref instanceof String) {
                return (String)ref;
            }
            ByteString bs = (ByteString)ref;
            String s2 = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.tableName_ = s2;
            }
            return s2;
        }

        @Override
        public ByteString getTableNameBytes() {
            java.lang.Object ref = this.tableName_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.tableName_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        @Override
        public boolean hasSchemaName() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override
        public String getSchemaName() {
            java.lang.Object ref = this.schemaName_;
            if (ref instanceof String) {
                return (String)ref;
            }
            ByteString bs = (ByteString)ref;
            String s2 = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.schemaName_ = s2;
            }
            return s2;
        }

        @Override
        public ByteString getSchemaNameBytes() {
            java.lang.Object ref = this.schemaName_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.schemaName_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        private void initFields() {
            this.documentPath_ = Collections.emptyList();
            this.name_ = "";
            this.tableName_ = "";
            this.schemaName_ = "";
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
            for (int i = 0; i < this.getDocumentPathCount(); ++i) {
                if (this.getDocumentPath(i).isInitialized()) continue;
                this.memoizedIsInitialized = 0;
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        @Override
        public void writeTo(CodedOutputStream output) throws IOException {
            this.getSerializedSize();
            for (int i = 0; i < this.documentPath_.size(); ++i) {
                output.writeMessage(1, this.documentPath_.get(i));
            }
            if ((this.bitField0_ & 1) == 1) {
                output.writeBytes(2, this.getNameBytes());
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeBytes(3, this.getTableNameBytes());
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeBytes(4, this.getSchemaNameBytes());
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
            for (int i = 0; i < this.documentPath_.size(); ++i) {
                size += CodedOutputStream.computeMessageSize(1, this.documentPath_.get(i));
            }
            if ((this.bitField0_ & 1) == 1) {
                size += CodedOutputStream.computeBytesSize(2, this.getNameBytes());
            }
            if ((this.bitField0_ & 2) == 2) {
                size += CodedOutputStream.computeBytesSize(3, this.getTableNameBytes());
            }
            if ((this.bitField0_ & 4) == 4) {
                size += CodedOutputStream.computeBytesSize(4, this.getSchemaNameBytes());
            }
            this.memoizedSerializedSize = size += this.getUnknownFields().getSerializedSize();
            return size;
        }

        @Override
        protected java.lang.Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static ColumnIdentifier parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static ColumnIdentifier parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static ColumnIdentifier parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static ColumnIdentifier parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static ColumnIdentifier parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static ColumnIdentifier parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static ColumnIdentifier parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static ColumnIdentifier parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static ColumnIdentifier parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static ColumnIdentifier parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override
        public Builder newBuilderForType() {
            return ColumnIdentifier.newBuilder();
        }

        public static Builder newBuilder(ColumnIdentifier prototype) {
            return ColumnIdentifier.newBuilder().mergeFrom(prototype);
        }

        @Override
        public Builder toBuilder() {
            return ColumnIdentifier.newBuilder(this);
        }

        @Override
        protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        static {
            PARSER = new AbstractParser<ColumnIdentifier>(){

                @Override
                public ColumnIdentifier parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                    return new ColumnIdentifier(input, extensionRegistry);
                }
            };
            defaultInstance = new ColumnIdentifier(true);
            defaultInstance.initFields();
        }

        public static final class Builder
        extends GeneratedMessage.Builder<Builder>
        implements ColumnIdentifierOrBuilder {
            private int bitField0_;
            private List<DocumentPathItem> documentPath_ = Collections.emptyList();
            private RepeatedFieldBuilder<DocumentPathItem, DocumentPathItem.Builder, DocumentPathItemOrBuilder> documentPathBuilder_;
            private java.lang.Object name_ = "";
            private java.lang.Object tableName_ = "";
            private java.lang.Object schemaName_ = "";

            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_Mysqlx_Expr_ColumnIdentifier_descriptor;
            }

            @Override
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_Mysqlx_Expr_ColumnIdentifier_fieldAccessorTable.ensureFieldAccessorsInitialized(ColumnIdentifier.class, Builder.class);
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
                    this.getDocumentPathFieldBuilder();
                }
            }

            private static Builder create() {
                return new Builder();
            }

            @Override
            public Builder clear() {
                super.clear();
                if (this.documentPathBuilder_ == null) {
                    this.documentPath_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFFFFE;
                } else {
                    this.documentPathBuilder_.clear();
                }
                this.name_ = "";
                this.bitField0_ &= 0xFFFFFFFD;
                this.tableName_ = "";
                this.bitField0_ &= 0xFFFFFFFB;
                this.schemaName_ = "";
                this.bitField0_ &= 0xFFFFFFF7;
                return this;
            }

            @Override
            public Builder clone() {
                return Builder.create().mergeFrom(this.buildPartial());
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return internal_static_Mysqlx_Expr_ColumnIdentifier_descriptor;
            }

            @Override
            public ColumnIdentifier getDefaultInstanceForType() {
                return ColumnIdentifier.getDefaultInstance();
            }

            @Override
            public ColumnIdentifier build() {
                ColumnIdentifier result = this.buildPartial();
                if (!result.isInitialized()) {
                    throw Builder.newUninitializedMessageException(result);
                }
                return result;
            }

            @Override
            public ColumnIdentifier buildPartial() {
                ColumnIdentifier result = new ColumnIdentifier(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if (this.documentPathBuilder_ == null) {
                    if ((this.bitField0_ & 1) == 1) {
                        this.documentPath_ = Collections.unmodifiableList(this.documentPath_);
                        this.bitField0_ &= 0xFFFFFFFE;
                    }
                    result.documentPath_ = this.documentPath_;
                } else {
                    result.documentPath_ = this.documentPathBuilder_.build();
                }
                if ((from_bitField0_ & 2) == 2) {
                    to_bitField0_ |= 1;
                }
                result.name_ = this.name_;
                if ((from_bitField0_ & 4) == 4) {
                    to_bitField0_ |= 2;
                }
                result.tableName_ = this.tableName_;
                if ((from_bitField0_ & 8) == 8) {
                    to_bitField0_ |= 4;
                }
                result.schemaName_ = this.schemaName_;
                result.bitField0_ = to_bitField0_;
                this.onBuilt();
                return result;
            }

            @Override
            public Builder mergeFrom(Message other) {
                if (other instanceof ColumnIdentifier) {
                    return this.mergeFrom((ColumnIdentifier)other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(ColumnIdentifier other) {
                if (other == ColumnIdentifier.getDefaultInstance()) {
                    return this;
                }
                if (this.documentPathBuilder_ == null) {
                    if (!other.documentPath_.isEmpty()) {
                        if (this.documentPath_.isEmpty()) {
                            this.documentPath_ = other.documentPath_;
                            this.bitField0_ &= 0xFFFFFFFE;
                        } else {
                            this.ensureDocumentPathIsMutable();
                            this.documentPath_.addAll(other.documentPath_);
                        }
                        this.onChanged();
                    }
                } else if (!other.documentPath_.isEmpty()) {
                    if (this.documentPathBuilder_.isEmpty()) {
                        this.documentPathBuilder_.dispose();
                        this.documentPathBuilder_ = null;
                        this.documentPath_ = other.documentPath_;
                        this.bitField0_ &= 0xFFFFFFFE;
                        this.documentPathBuilder_ = alwaysUseFieldBuilders ? this.getDocumentPathFieldBuilder() : null;
                    } else {
                        this.documentPathBuilder_.addAllMessages(other.documentPath_);
                    }
                }
                if (other.hasName()) {
                    this.bitField0_ |= 2;
                    this.name_ = other.name_;
                    this.onChanged();
                }
                if (other.hasTableName()) {
                    this.bitField0_ |= 4;
                    this.tableName_ = other.tableName_;
                    this.onChanged();
                }
                if (other.hasSchemaName()) {
                    this.bitField0_ |= 8;
                    this.schemaName_ = other.schemaName_;
                    this.onChanged();
                }
                this.mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            @Override
            public final boolean isInitialized() {
                for (int i = 0; i < this.getDocumentPathCount(); ++i) {
                    if (this.getDocumentPath(i).isInitialized()) continue;
                    return false;
                }
                return true;
            }

            @Override
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                ColumnIdentifier parsedMessage = null;
                try {
                    parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
                } catch (InvalidProtocolBufferException e) {
                    parsedMessage = (ColumnIdentifier)e.getUnfinishedMessage();
                    throw e;
                } finally {
                    if (parsedMessage != null) {
                        this.mergeFrom(parsedMessage);
                    }
                }
                return this;
            }

            private void ensureDocumentPathIsMutable() {
                if ((this.bitField0_ & 1) != 1) {
                    this.documentPath_ = new ArrayList<DocumentPathItem>(this.documentPath_);
                    this.bitField0_ |= 1;
                }
            }

            @Override
            public List<DocumentPathItem> getDocumentPathList() {
                if (this.documentPathBuilder_ == null) {
                    return Collections.unmodifiableList(this.documentPath_);
                }
                return this.documentPathBuilder_.getMessageList();
            }

            @Override
            public int getDocumentPathCount() {
                if (this.documentPathBuilder_ == null) {
                    return this.documentPath_.size();
                }
                return this.documentPathBuilder_.getCount();
            }

            @Override
            public DocumentPathItem getDocumentPath(int index) {
                if (this.documentPathBuilder_ == null) {
                    return this.documentPath_.get(index);
                }
                return this.documentPathBuilder_.getMessage(index);
            }

            public Builder setDocumentPath(int index, DocumentPathItem value) {
                if (this.documentPathBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureDocumentPathIsMutable();
                    this.documentPath_.set(index, value);
                    this.onChanged();
                } else {
                    this.documentPathBuilder_.setMessage(index, value);
                }
                return this;
            }

            public Builder setDocumentPath(int index, DocumentPathItem.Builder builderForValue) {
                if (this.documentPathBuilder_ == null) {
                    this.ensureDocumentPathIsMutable();
                    this.documentPath_.set(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.documentPathBuilder_.setMessage(index, builderForValue.build());
                }
                return this;
            }

            public Builder addDocumentPath(DocumentPathItem value) {
                if (this.documentPathBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureDocumentPathIsMutable();
                    this.documentPath_.add(value);
                    this.onChanged();
                } else {
                    this.documentPathBuilder_.addMessage(value);
                }
                return this;
            }

            public Builder addDocumentPath(int index, DocumentPathItem value) {
                if (this.documentPathBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureDocumentPathIsMutable();
                    this.documentPath_.add(index, value);
                    this.onChanged();
                } else {
                    this.documentPathBuilder_.addMessage(index, value);
                }
                return this;
            }

            public Builder addDocumentPath(DocumentPathItem.Builder builderForValue) {
                if (this.documentPathBuilder_ == null) {
                    this.ensureDocumentPathIsMutable();
                    this.documentPath_.add(builderForValue.build());
                    this.onChanged();
                } else {
                    this.documentPathBuilder_.addMessage(builderForValue.build());
                }
                return this;
            }

            public Builder addDocumentPath(int index, DocumentPathItem.Builder builderForValue) {
                if (this.documentPathBuilder_ == null) {
                    this.ensureDocumentPathIsMutable();
                    this.documentPath_.add(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.documentPathBuilder_.addMessage(index, builderForValue.build());
                }
                return this;
            }

            public Builder addAllDocumentPath(Iterable<? extends DocumentPathItem> values2) {
                if (this.documentPathBuilder_ == null) {
                    this.ensureDocumentPathIsMutable();
                    AbstractMessageLite.Builder.addAll(values2, this.documentPath_);
                    this.onChanged();
                } else {
                    this.documentPathBuilder_.addAllMessages(values2);
                }
                return this;
            }

            public Builder clearDocumentPath() {
                if (this.documentPathBuilder_ == null) {
                    this.documentPath_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFFFFE;
                    this.onChanged();
                } else {
                    this.documentPathBuilder_.clear();
                }
                return this;
            }

            public Builder removeDocumentPath(int index) {
                if (this.documentPathBuilder_ == null) {
                    this.ensureDocumentPathIsMutable();
                    this.documentPath_.remove(index);
                    this.onChanged();
                } else {
                    this.documentPathBuilder_.remove(index);
                }
                return this;
            }

            public DocumentPathItem.Builder getDocumentPathBuilder(int index) {
                return this.getDocumentPathFieldBuilder().getBuilder(index);
            }

            @Override
            public DocumentPathItemOrBuilder getDocumentPathOrBuilder(int index) {
                if (this.documentPathBuilder_ == null) {
                    return this.documentPath_.get(index);
                }
                return this.documentPathBuilder_.getMessageOrBuilder(index);
            }

            @Override
            public List<? extends DocumentPathItemOrBuilder> getDocumentPathOrBuilderList() {
                if (this.documentPathBuilder_ != null) {
                    return this.documentPathBuilder_.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.documentPath_);
            }

            public DocumentPathItem.Builder addDocumentPathBuilder() {
                return this.getDocumentPathFieldBuilder().addBuilder(DocumentPathItem.getDefaultInstance());
            }

            public DocumentPathItem.Builder addDocumentPathBuilder(int index) {
                return this.getDocumentPathFieldBuilder().addBuilder(index, DocumentPathItem.getDefaultInstance());
            }

            public List<DocumentPathItem.Builder> getDocumentPathBuilderList() {
                return this.getDocumentPathFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilder<DocumentPathItem, DocumentPathItem.Builder, DocumentPathItemOrBuilder> getDocumentPathFieldBuilder() {
                if (this.documentPathBuilder_ == null) {
                    this.documentPathBuilder_ = new RepeatedFieldBuilder(this.documentPath_, (this.bitField0_ & 1) == 1, this.getParentForChildren(), this.isClean());
                    this.documentPath_ = null;
                }
                return this.documentPathBuilder_;
            }

            @Override
            public boolean hasName() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override
            public String getName() {
                java.lang.Object ref = this.name_;
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
                java.lang.Object ref = this.name_;
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
                this.bitField0_ |= 2;
                this.name_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearName() {
                this.bitField0_ &= 0xFFFFFFFD;
                this.name_ = ColumnIdentifier.getDefaultInstance().getName();
                this.onChanged();
                return this;
            }

            public Builder setNameBytes(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 2;
                this.name_ = value;
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasTableName() {
                return (this.bitField0_ & 4) == 4;
            }

            @Override
            public String getTableName() {
                java.lang.Object ref = this.tableName_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString)ref;
                    String s2 = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.tableName_ = s2;
                    }
                    return s2;
                }
                return (String)ref;
            }

            @Override
            public ByteString getTableNameBytes() {
                java.lang.Object ref = this.tableName_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String)ref);
                    this.tableName_ = b;
                    return b;
                }
                return (ByteString)ref;
            }

            public Builder setTableName(String value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 4;
                this.tableName_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearTableName() {
                this.bitField0_ &= 0xFFFFFFFB;
                this.tableName_ = ColumnIdentifier.getDefaultInstance().getTableName();
                this.onChanged();
                return this;
            }

            public Builder setTableNameBytes(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 4;
                this.tableName_ = value;
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasSchemaName() {
                return (this.bitField0_ & 8) == 8;
            }

            @Override
            public String getSchemaName() {
                java.lang.Object ref = this.schemaName_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString)ref;
                    String s2 = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.schemaName_ = s2;
                    }
                    return s2;
                }
                return (String)ref;
            }

            @Override
            public ByteString getSchemaNameBytes() {
                java.lang.Object ref = this.schemaName_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String)ref);
                    this.schemaName_ = b;
                    return b;
                }
                return (ByteString)ref;
            }

            public Builder setSchemaName(String value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 8;
                this.schemaName_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearSchemaName() {
                this.bitField0_ &= 0xFFFFFFF7;
                this.schemaName_ = ColumnIdentifier.getDefaultInstance().getSchemaName();
                this.onChanged();
                return this;
            }

            public Builder setSchemaNameBytes(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 8;
                this.schemaName_ = value;
                this.onChanged();
                return this;
            }
        }
    }

    public static interface ColumnIdentifierOrBuilder
    extends MessageOrBuilder {
        public List<DocumentPathItem> getDocumentPathList();

        public DocumentPathItem getDocumentPath(int var1);

        public int getDocumentPathCount();

        public List<? extends DocumentPathItemOrBuilder> getDocumentPathOrBuilderList();

        public DocumentPathItemOrBuilder getDocumentPathOrBuilder(int var1);

        public boolean hasName();

        public String getName();

        public ByteString getNameBytes();

        public boolean hasTableName();

        public String getTableName();

        public ByteString getTableNameBytes();

        public boolean hasSchemaName();

        public String getSchemaName();

        public ByteString getSchemaNameBytes();
    }

    public static final class DocumentPathItem
    extends GeneratedMessage
    implements DocumentPathItemOrBuilder {
        private static final DocumentPathItem defaultInstance;
        private final UnknownFieldSet unknownFields;
        public static Parser<DocumentPathItem> PARSER;
        private int bitField0_;
        public static final int TYPE_FIELD_NUMBER = 1;
        private Type type_;
        public static final int VALUE_FIELD_NUMBER = 2;
        private java.lang.Object value_;
        public static final int INDEX_FIELD_NUMBER = 3;
        private int index_;
        private byte memoizedIsInitialized = (byte)-1;
        private int memoizedSerializedSize = -1;
        private static final long serialVersionUID = 0L;

        private DocumentPathItem(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.unknownFields = builder.getUnknownFields();
        }

        private DocumentPathItem(boolean noInit) {
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static DocumentPathItem getDefaultInstance() {
            return defaultInstance;
        }

        @Override
        public DocumentPathItem getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private DocumentPathItem(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
                            Type value = Type.valueOf(rawValue);
                            if (value == null) {
                                unknownFields.mergeVarintField(1, rawValue);
                                continue block12;
                            }
                            this.bitField0_ |= 1;
                            this.type_ = value;
                            continue block12;
                        }
                        case 18: {
                            ByteString bs = input.readBytes();
                            this.bitField0_ |= 2;
                            this.value_ = bs;
                            continue block12;
                        }
                        case 24: 
                    }
                    this.bitField0_ |= 4;
                    this.index_ = input.readUInt32();
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
            return internal_static_Mysqlx_Expr_DocumentPathItem_descriptor;
        }

        @Override
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return internal_static_Mysqlx_Expr_DocumentPathItem_fieldAccessorTable.ensureFieldAccessorsInitialized(DocumentPathItem.class, Builder.class);
        }

        public Parser<DocumentPathItem> getParserForType() {
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
        public boolean hasValue() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override
        public String getValue() {
            java.lang.Object ref = this.value_;
            if (ref instanceof String) {
                return (String)ref;
            }
            ByteString bs = (ByteString)ref;
            String s2 = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.value_ = s2;
            }
            return s2;
        }

        @Override
        public ByteString getValueBytes() {
            java.lang.Object ref = this.value_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.value_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        @Override
        public boolean hasIndex() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override
        public int getIndex() {
            return this.index_;
        }

        private void initFields() {
            this.type_ = Type.MEMBER;
            this.value_ = "";
            this.index_ = 0;
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
                output.writeBytes(2, this.getValueBytes());
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeUInt32(3, this.index_);
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
                size += CodedOutputStream.computeBytesSize(2, this.getValueBytes());
            }
            if ((this.bitField0_ & 4) == 4) {
                size += CodedOutputStream.computeUInt32Size(3, this.index_);
            }
            this.memoizedSerializedSize = size += this.getUnknownFields().getSerializedSize();
            return size;
        }

        @Override
        protected java.lang.Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static DocumentPathItem parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static DocumentPathItem parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static DocumentPathItem parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static DocumentPathItem parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static DocumentPathItem parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static DocumentPathItem parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static DocumentPathItem parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static DocumentPathItem parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static DocumentPathItem parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static DocumentPathItem parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override
        public Builder newBuilderForType() {
            return DocumentPathItem.newBuilder();
        }

        public static Builder newBuilder(DocumentPathItem prototype) {
            return DocumentPathItem.newBuilder().mergeFrom(prototype);
        }

        @Override
        public Builder toBuilder() {
            return DocumentPathItem.newBuilder(this);
        }

        @Override
        protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        static {
            PARSER = new AbstractParser<DocumentPathItem>(){

                @Override
                public DocumentPathItem parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                    return new DocumentPathItem(input, extensionRegistry);
                }
            };
            defaultInstance = new DocumentPathItem(true);
            defaultInstance.initFields();
        }

        public static final class Builder
        extends GeneratedMessage.Builder<Builder>
        implements DocumentPathItemOrBuilder {
            private int bitField0_;
            private Type type_ = Type.MEMBER;
            private java.lang.Object value_ = "";
            private int index_;

            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_Mysqlx_Expr_DocumentPathItem_descriptor;
            }

            @Override
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_Mysqlx_Expr_DocumentPathItem_fieldAccessorTable.ensureFieldAccessorsInitialized(DocumentPathItem.class, Builder.class);
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
                this.type_ = Type.MEMBER;
                this.bitField0_ &= 0xFFFFFFFE;
                this.value_ = "";
                this.bitField0_ &= 0xFFFFFFFD;
                this.index_ = 0;
                this.bitField0_ &= 0xFFFFFFFB;
                return this;
            }

            @Override
            public Builder clone() {
                return Builder.create().mergeFrom(this.buildPartial());
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return internal_static_Mysqlx_Expr_DocumentPathItem_descriptor;
            }

            @Override
            public DocumentPathItem getDefaultInstanceForType() {
                return DocumentPathItem.getDefaultInstance();
            }

            @Override
            public DocumentPathItem build() {
                DocumentPathItem result = this.buildPartial();
                if (!result.isInitialized()) {
                    throw Builder.newUninitializedMessageException(result);
                }
                return result;
            }

            @Override
            public DocumentPathItem buildPartial() {
                DocumentPathItem result = new DocumentPathItem(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ |= 1;
                }
                result.type_ = this.type_;
                if ((from_bitField0_ & 2) == 2) {
                    to_bitField0_ |= 2;
                }
                result.value_ = this.value_;
                if ((from_bitField0_ & 4) == 4) {
                    to_bitField0_ |= 4;
                }
                result.index_ = this.index_;
                result.bitField0_ = to_bitField0_;
                this.onBuilt();
                return result;
            }

            @Override
            public Builder mergeFrom(Message other) {
                if (other instanceof DocumentPathItem) {
                    return this.mergeFrom((DocumentPathItem)other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(DocumentPathItem other) {
                if (other == DocumentPathItem.getDefaultInstance()) {
                    return this;
                }
                if (other.hasType()) {
                    this.setType(other.getType());
                }
                if (other.hasValue()) {
                    this.bitField0_ |= 2;
                    this.value_ = other.value_;
                    this.onChanged();
                }
                if (other.hasIndex()) {
                    this.setIndex(other.getIndex());
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
                DocumentPathItem parsedMessage = null;
                try {
                    parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
                } catch (InvalidProtocolBufferException e) {
                    parsedMessage = (DocumentPathItem)e.getUnfinishedMessage();
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
                this.type_ = Type.MEMBER;
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasValue() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override
            public String getValue() {
                java.lang.Object ref = this.value_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString)ref;
                    String s2 = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.value_ = s2;
                    }
                    return s2;
                }
                return (String)ref;
            }

            @Override
            public ByteString getValueBytes() {
                java.lang.Object ref = this.value_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String)ref);
                    this.value_ = b;
                    return b;
                }
                return (ByteString)ref;
            }

            public Builder setValue(String value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 2;
                this.value_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearValue() {
                this.bitField0_ &= 0xFFFFFFFD;
                this.value_ = DocumentPathItem.getDefaultInstance().getValue();
                this.onChanged();
                return this;
            }

            public Builder setValueBytes(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 2;
                this.value_ = value;
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasIndex() {
                return (this.bitField0_ & 4) == 4;
            }

            @Override
            public int getIndex() {
                return this.index_;
            }

            public Builder setIndex(int value) {
                this.bitField0_ |= 4;
                this.index_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearIndex() {
                this.bitField0_ &= 0xFFFFFFFB;
                this.index_ = 0;
                this.onChanged();
                return this;
            }
        }

        public static enum Type implements ProtocolMessageEnum
        {
            MEMBER(0, 1),
            MEMBER_ASTERISK(1, 2),
            ARRAY_INDEX(2, 3),
            ARRAY_INDEX_ASTERISK(3, 4),
            DOUBLE_ASTERISK(4, 5);

            public static final int MEMBER_VALUE = 1;
            public static final int MEMBER_ASTERISK_VALUE = 2;
            public static final int ARRAY_INDEX_VALUE = 3;
            public static final int ARRAY_INDEX_ASTERISK_VALUE = 4;
            public static final int DOUBLE_ASTERISK_VALUE = 5;
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
                        return MEMBER;
                    }
                    case 2: {
                        return MEMBER_ASTERISK;
                    }
                    case 3: {
                        return ARRAY_INDEX;
                    }
                    case 4: {
                        return ARRAY_INDEX_ASTERISK;
                    }
                    case 5: {
                        return DOUBLE_ASTERISK;
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
                return DocumentPathItem.getDescriptor().getEnumTypes().get(0);
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

    public static interface DocumentPathItemOrBuilder
    extends MessageOrBuilder {
        public boolean hasType();

        public DocumentPathItem.Type getType();

        public boolean hasValue();

        public String getValue();

        public ByteString getValueBytes();

        public boolean hasIndex();

        public int getIndex();
    }

    public static final class Identifier
    extends GeneratedMessage
    implements IdentifierOrBuilder {
        private static final Identifier defaultInstance;
        private final UnknownFieldSet unknownFields;
        public static Parser<Identifier> PARSER;
        private int bitField0_;
        public static final int NAME_FIELD_NUMBER = 1;
        private java.lang.Object name_;
        public static final int SCHEMA_NAME_FIELD_NUMBER = 2;
        private java.lang.Object schemaName_;
        private byte memoizedIsInitialized = (byte)-1;
        private int memoizedSerializedSize = -1;
        private static final long serialVersionUID = 0L;

        private Identifier(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.unknownFields = builder.getUnknownFields();
        }

        private Identifier(boolean noInit) {
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static Identifier getDefaultInstance() {
            return defaultInstance;
        }

        @Override
        public Identifier getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private Identifier(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this.initFields();
            boolean mutable_bitField0_ = false;
            UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
            try {
                boolean done = false;
                block11: while (!done) {
                    ByteString bs;
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
                            bs = input.readBytes();
                            this.bitField0_ |= 1;
                            this.name_ = bs;
                            continue block11;
                        }
                        case 18: 
                    }
                    bs = input.readBytes();
                    this.bitField0_ |= 2;
                    this.schemaName_ = bs;
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
            return internal_static_Mysqlx_Expr_Identifier_descriptor;
        }

        @Override
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return internal_static_Mysqlx_Expr_Identifier_fieldAccessorTable.ensureFieldAccessorsInitialized(Identifier.class, Builder.class);
        }

        public Parser<Identifier> getParserForType() {
            return PARSER;
        }

        @Override
        public boolean hasName() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override
        public String getName() {
            java.lang.Object ref = this.name_;
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
            java.lang.Object ref = this.name_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.name_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        @Override
        public boolean hasSchemaName() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override
        public String getSchemaName() {
            java.lang.Object ref = this.schemaName_;
            if (ref instanceof String) {
                return (String)ref;
            }
            ByteString bs = (ByteString)ref;
            String s2 = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.schemaName_ = s2;
            }
            return s2;
        }

        @Override
        public ByteString getSchemaNameBytes() {
            java.lang.Object ref = this.schemaName_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.schemaName_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        private void initFields() {
            this.name_ = "";
            this.schemaName_ = "";
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
                output.writeBytes(2, this.getSchemaNameBytes());
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
                size += CodedOutputStream.computeBytesSize(2, this.getSchemaNameBytes());
            }
            this.memoizedSerializedSize = size += this.getUnknownFields().getSerializedSize();
            return size;
        }

        @Override
        protected java.lang.Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static Identifier parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static Identifier parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static Identifier parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static Identifier parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static Identifier parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static Identifier parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Identifier parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static Identifier parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static Identifier parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static Identifier parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override
        public Builder newBuilderForType() {
            return Identifier.newBuilder();
        }

        public static Builder newBuilder(Identifier prototype) {
            return Identifier.newBuilder().mergeFrom(prototype);
        }

        @Override
        public Builder toBuilder() {
            return Identifier.newBuilder(this);
        }

        @Override
        protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        static {
            PARSER = new AbstractParser<Identifier>(){

                @Override
                public Identifier parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                    return new Identifier(input, extensionRegistry);
                }
            };
            defaultInstance = new Identifier(true);
            defaultInstance.initFields();
        }

        public static final class Builder
        extends GeneratedMessage.Builder<Builder>
        implements IdentifierOrBuilder {
            private int bitField0_;
            private java.lang.Object name_ = "";
            private java.lang.Object schemaName_ = "";

            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_Mysqlx_Expr_Identifier_descriptor;
            }

            @Override
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_Mysqlx_Expr_Identifier_fieldAccessorTable.ensureFieldAccessorsInitialized(Identifier.class, Builder.class);
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
                this.name_ = "";
                this.bitField0_ &= 0xFFFFFFFE;
                this.schemaName_ = "";
                this.bitField0_ &= 0xFFFFFFFD;
                return this;
            }

            @Override
            public Builder clone() {
                return Builder.create().mergeFrom(this.buildPartial());
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return internal_static_Mysqlx_Expr_Identifier_descriptor;
            }

            @Override
            public Identifier getDefaultInstanceForType() {
                return Identifier.getDefaultInstance();
            }

            @Override
            public Identifier build() {
                Identifier result = this.buildPartial();
                if (!result.isInitialized()) {
                    throw Builder.newUninitializedMessageException(result);
                }
                return result;
            }

            @Override
            public Identifier buildPartial() {
                Identifier result = new Identifier(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ |= 1;
                }
                result.name_ = this.name_;
                if ((from_bitField0_ & 2) == 2) {
                    to_bitField0_ |= 2;
                }
                result.schemaName_ = this.schemaName_;
                result.bitField0_ = to_bitField0_;
                this.onBuilt();
                return result;
            }

            @Override
            public Builder mergeFrom(Message other) {
                if (other instanceof Identifier) {
                    return this.mergeFrom((Identifier)other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(Identifier other) {
                if (other == Identifier.getDefaultInstance()) {
                    return this;
                }
                if (other.hasName()) {
                    this.bitField0_ |= 1;
                    this.name_ = other.name_;
                    this.onChanged();
                }
                if (other.hasSchemaName()) {
                    this.bitField0_ |= 2;
                    this.schemaName_ = other.schemaName_;
                    this.onChanged();
                }
                this.mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            @Override
            public final boolean isInitialized() {
                return this.hasName();
            }

            @Override
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                Identifier parsedMessage = null;
                try {
                    parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
                } catch (InvalidProtocolBufferException e) {
                    parsedMessage = (Identifier)e.getUnfinishedMessage();
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
                java.lang.Object ref = this.name_;
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
                java.lang.Object ref = this.name_;
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
                this.name_ = Identifier.getDefaultInstance().getName();
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
            public boolean hasSchemaName() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override
            public String getSchemaName() {
                java.lang.Object ref = this.schemaName_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString)ref;
                    String s2 = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.schemaName_ = s2;
                    }
                    return s2;
                }
                return (String)ref;
            }

            @Override
            public ByteString getSchemaNameBytes() {
                java.lang.Object ref = this.schemaName_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String)ref);
                    this.schemaName_ = b;
                    return b;
                }
                return (ByteString)ref;
            }

            public Builder setSchemaName(String value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 2;
                this.schemaName_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearSchemaName() {
                this.bitField0_ &= 0xFFFFFFFD;
                this.schemaName_ = Identifier.getDefaultInstance().getSchemaName();
                this.onChanged();
                return this;
            }

            public Builder setSchemaNameBytes(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 2;
                this.schemaName_ = value;
                this.onChanged();
                return this;
            }
        }
    }

    public static interface IdentifierOrBuilder
    extends MessageOrBuilder {
        public boolean hasName();

        public String getName();

        public ByteString getNameBytes();

        public boolean hasSchemaName();

        public String getSchemaName();

        public ByteString getSchemaNameBytes();
    }

    public static final class Expr
    extends GeneratedMessage
    implements ExprOrBuilder {
        private static final Expr defaultInstance;
        private final UnknownFieldSet unknownFields;
        public static Parser<Expr> PARSER;
        private int bitField0_;
        public static final int TYPE_FIELD_NUMBER = 1;
        private Type type_;
        public static final int IDENTIFIER_FIELD_NUMBER = 2;
        private ColumnIdentifier identifier_;
        public static final int VARIABLE_FIELD_NUMBER = 3;
        private java.lang.Object variable_;
        public static final int LITERAL_FIELD_NUMBER = 4;
        private MysqlxDatatypes.Scalar literal_;
        public static final int FUNCTION_CALL_FIELD_NUMBER = 5;
        private FunctionCall functionCall_;
        public static final int OPERATOR_FIELD_NUMBER = 6;
        private Operator operator_;
        public static final int POSITION_FIELD_NUMBER = 7;
        private int position_;
        public static final int OBJECT_FIELD_NUMBER = 8;
        private Object object_;
        public static final int ARRAY_FIELD_NUMBER = 9;
        private Array array_;
        private byte memoizedIsInitialized = (byte)-1;
        private int memoizedSerializedSize = -1;
        private static final long serialVersionUID = 0L;

        private Expr(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.unknownFields = builder.getUnknownFields();
        }

        private Expr(boolean noInit) {
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static Expr getDefaultInstance() {
            return defaultInstance;
        }

        @Override
        public Expr getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private Expr(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this.initFields();
            boolean mutable_bitField0_ = false;
            UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
            try {
                boolean done = false;
                block18: while (!done) {
                    int tag = input.readTag();
                    switch (tag) {
                        case 0: {
                            done = true;
                            continue block18;
                        }
                        default: {
                            if (this.parseUnknownField(input, unknownFields, extensionRegistry, tag)) continue block18;
                            done = true;
                            continue block18;
                        }
                        case 8: {
                            int rawValue = input.readEnum();
                            Type value = Type.valueOf(rawValue);
                            if (value == null) {
                                unknownFields.mergeVarintField(1, rawValue);
                                continue block18;
                            }
                            this.bitField0_ |= 1;
                            this.type_ = value;
                            continue block18;
                        }
                        case 18: {
                            ColumnIdentifier.Builder subBuilder = null;
                            if ((this.bitField0_ & 2) == 2) {
                                subBuilder = this.identifier_.toBuilder();
                            }
                            this.identifier_ = input.readMessage(ColumnIdentifier.PARSER, extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom(this.identifier_);
                                this.identifier_ = subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 2;
                            continue block18;
                        }
                        case 26: {
                            ByteString bs = input.readBytes();
                            this.bitField0_ |= 4;
                            this.variable_ = bs;
                            continue block18;
                        }
                        case 34: {
                            MysqlxDatatypes.Scalar.Builder subBuilder = null;
                            if ((this.bitField0_ & 8) == 8) {
                                subBuilder = this.literal_.toBuilder();
                            }
                            this.literal_ = input.readMessage(MysqlxDatatypes.Scalar.PARSER, extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom(this.literal_);
                                this.literal_ = subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 8;
                            continue block18;
                        }
                        case 42: {
                            FunctionCall.Builder subBuilder = null;
                            if ((this.bitField0_ & 0x10) == 16) {
                                subBuilder = this.functionCall_.toBuilder();
                            }
                            this.functionCall_ = input.readMessage(FunctionCall.PARSER, extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom(this.functionCall_);
                                this.functionCall_ = subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 0x10;
                            continue block18;
                        }
                        case 50: {
                            Operator.Builder subBuilder = null;
                            if ((this.bitField0_ & 0x20) == 32) {
                                subBuilder = this.operator_.toBuilder();
                            }
                            this.operator_ = input.readMessage(Operator.PARSER, extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom(this.operator_);
                                this.operator_ = subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 0x20;
                            continue block18;
                        }
                        case 56: {
                            this.bitField0_ |= 0x40;
                            this.position_ = input.readUInt32();
                            continue block18;
                        }
                        case 66: {
                            Object.Builder subBuilder = null;
                            if ((this.bitField0_ & 0x80) == 128) {
                                subBuilder = this.object_.toBuilder();
                            }
                            this.object_ = input.readMessage(Object.PARSER, extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom(this.object_);
                                this.object_ = subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 0x80;
                            continue block18;
                        }
                        case 74: 
                    }
                    Array.Builder subBuilder = null;
                    if ((this.bitField0_ & 0x100) == 256) {
                        subBuilder = this.array_.toBuilder();
                    }
                    this.array_ = input.readMessage(Array.PARSER, extensionRegistry);
                    if (subBuilder != null) {
                        subBuilder.mergeFrom(this.array_);
                        this.array_ = subBuilder.buildPartial();
                    }
                    this.bitField0_ |= 0x100;
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
            return internal_static_Mysqlx_Expr_Expr_descriptor;
        }

        @Override
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return internal_static_Mysqlx_Expr_Expr_fieldAccessorTable.ensureFieldAccessorsInitialized(Expr.class, Builder.class);
        }

        public Parser<Expr> getParserForType() {
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
        public boolean hasIdentifier() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override
        public ColumnIdentifier getIdentifier() {
            return this.identifier_;
        }

        @Override
        public ColumnIdentifierOrBuilder getIdentifierOrBuilder() {
            return this.identifier_;
        }

        @Override
        public boolean hasVariable() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override
        public String getVariable() {
            java.lang.Object ref = this.variable_;
            if (ref instanceof String) {
                return (String)ref;
            }
            ByteString bs = (ByteString)ref;
            String s2 = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.variable_ = s2;
            }
            return s2;
        }

        @Override
        public ByteString getVariableBytes() {
            java.lang.Object ref = this.variable_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.variable_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        @Override
        public boolean hasLiteral() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override
        public MysqlxDatatypes.Scalar getLiteral() {
            return this.literal_;
        }

        @Override
        public MysqlxDatatypes.ScalarOrBuilder getLiteralOrBuilder() {
            return this.literal_;
        }

        @Override
        public boolean hasFunctionCall() {
            return (this.bitField0_ & 0x10) == 16;
        }

        @Override
        public FunctionCall getFunctionCall() {
            return this.functionCall_;
        }

        @Override
        public FunctionCallOrBuilder getFunctionCallOrBuilder() {
            return this.functionCall_;
        }

        @Override
        public boolean hasOperator() {
            return (this.bitField0_ & 0x20) == 32;
        }

        @Override
        public Operator getOperator() {
            return this.operator_;
        }

        @Override
        public OperatorOrBuilder getOperatorOrBuilder() {
            return this.operator_;
        }

        @Override
        public boolean hasPosition() {
            return (this.bitField0_ & 0x40) == 64;
        }

        @Override
        public int getPosition() {
            return this.position_;
        }

        @Override
        public boolean hasObject() {
            return (this.bitField0_ & 0x80) == 128;
        }

        @Override
        public Object getObject() {
            return this.object_;
        }

        @Override
        public ObjectOrBuilder getObjectOrBuilder() {
            return this.object_;
        }

        @Override
        public boolean hasArray() {
            return (this.bitField0_ & 0x100) == 256;
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
            this.type_ = Type.IDENT;
            this.identifier_ = ColumnIdentifier.getDefaultInstance();
            this.variable_ = "";
            this.literal_ = MysqlxDatatypes.Scalar.getDefaultInstance();
            this.functionCall_ = FunctionCall.getDefaultInstance();
            this.operator_ = Operator.getDefaultInstance();
            this.position_ = 0;
            this.object_ = Object.getDefaultInstance();
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
            if (this.hasIdentifier() && !this.getIdentifier().isInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            if (this.hasLiteral() && !this.getLiteral().isInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            if (this.hasFunctionCall() && !this.getFunctionCall().isInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            if (this.hasOperator() && !this.getOperator().isInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            if (this.hasObject() && !this.getObject().isInitialized()) {
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
                output.writeMessage(2, this.identifier_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeBytes(3, this.getVariableBytes());
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeMessage(4, this.literal_);
            }
            if ((this.bitField0_ & 0x10) == 16) {
                output.writeMessage(5, this.functionCall_);
            }
            if ((this.bitField0_ & 0x20) == 32) {
                output.writeMessage(6, this.operator_);
            }
            if ((this.bitField0_ & 0x40) == 64) {
                output.writeUInt32(7, this.position_);
            }
            if ((this.bitField0_ & 0x80) == 128) {
                output.writeMessage(8, this.object_);
            }
            if ((this.bitField0_ & 0x100) == 256) {
                output.writeMessage(9, this.array_);
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
                size += CodedOutputStream.computeMessageSize(2, this.identifier_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size += CodedOutputStream.computeBytesSize(3, this.getVariableBytes());
            }
            if ((this.bitField0_ & 8) == 8) {
                size += CodedOutputStream.computeMessageSize(4, this.literal_);
            }
            if ((this.bitField0_ & 0x10) == 16) {
                size += CodedOutputStream.computeMessageSize(5, this.functionCall_);
            }
            if ((this.bitField0_ & 0x20) == 32) {
                size += CodedOutputStream.computeMessageSize(6, this.operator_);
            }
            if ((this.bitField0_ & 0x40) == 64) {
                size += CodedOutputStream.computeUInt32Size(7, this.position_);
            }
            if ((this.bitField0_ & 0x80) == 128) {
                size += CodedOutputStream.computeMessageSize(8, this.object_);
            }
            if ((this.bitField0_ & 0x100) == 256) {
                size += CodedOutputStream.computeMessageSize(9, this.array_);
            }
            this.memoizedSerializedSize = size += this.getUnknownFields().getSerializedSize();
            return size;
        }

        @Override
        protected java.lang.Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static Expr parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static Expr parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static Expr parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static Expr parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static Expr parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static Expr parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Expr parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static Expr parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static Expr parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static Expr parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override
        public Builder newBuilderForType() {
            return Expr.newBuilder();
        }

        public static Builder newBuilder(Expr prototype) {
            return Expr.newBuilder().mergeFrom(prototype);
        }

        @Override
        public Builder toBuilder() {
            return Expr.newBuilder(this);
        }

        @Override
        protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        static {
            PARSER = new AbstractParser<Expr>(){

                @Override
                public Expr parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                    return new Expr(input, extensionRegistry);
                }
            };
            defaultInstance = new Expr(true);
            defaultInstance.initFields();
        }

        public static final class Builder
        extends GeneratedMessage.Builder<Builder>
        implements ExprOrBuilder {
            private int bitField0_;
            private Type type_ = Type.IDENT;
            private ColumnIdentifier identifier_ = ColumnIdentifier.getDefaultInstance();
            private SingleFieldBuilder<ColumnIdentifier, ColumnIdentifier.Builder, ColumnIdentifierOrBuilder> identifierBuilder_;
            private java.lang.Object variable_ = "";
            private MysqlxDatatypes.Scalar literal_ = MysqlxDatatypes.Scalar.getDefaultInstance();
            private SingleFieldBuilder<MysqlxDatatypes.Scalar, MysqlxDatatypes.Scalar.Builder, MysqlxDatatypes.ScalarOrBuilder> literalBuilder_;
            private FunctionCall functionCall_ = FunctionCall.getDefaultInstance();
            private SingleFieldBuilder<FunctionCall, FunctionCall.Builder, FunctionCallOrBuilder> functionCallBuilder_;
            private Operator operator_ = Operator.getDefaultInstance();
            private SingleFieldBuilder<Operator, Operator.Builder, OperatorOrBuilder> operatorBuilder_;
            private int position_;
            private Object object_ = Object.getDefaultInstance();
            private SingleFieldBuilder<Object, Object.Builder, ObjectOrBuilder> objectBuilder_;
            private Array array_ = Array.getDefaultInstance();
            private SingleFieldBuilder<Array, Array.Builder, ArrayOrBuilder> arrayBuilder_;

            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_Mysqlx_Expr_Expr_descriptor;
            }

            @Override
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_Mysqlx_Expr_Expr_fieldAccessorTable.ensureFieldAccessorsInitialized(Expr.class, Builder.class);
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
                    this.getIdentifierFieldBuilder();
                    this.getLiteralFieldBuilder();
                    this.getFunctionCallFieldBuilder();
                    this.getOperatorFieldBuilder();
                    this.getObjectFieldBuilder();
                    this.getArrayFieldBuilder();
                }
            }

            private static Builder create() {
                return new Builder();
            }

            @Override
            public Builder clear() {
                super.clear();
                this.type_ = Type.IDENT;
                this.bitField0_ &= 0xFFFFFFFE;
                if (this.identifierBuilder_ == null) {
                    this.identifier_ = ColumnIdentifier.getDefaultInstance();
                } else {
                    this.identifierBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFFFD;
                this.variable_ = "";
                this.bitField0_ &= 0xFFFFFFFB;
                if (this.literalBuilder_ == null) {
                    this.literal_ = MysqlxDatatypes.Scalar.getDefaultInstance();
                } else {
                    this.literalBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFFF7;
                if (this.functionCallBuilder_ == null) {
                    this.functionCall_ = FunctionCall.getDefaultInstance();
                } else {
                    this.functionCallBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFFEF;
                if (this.operatorBuilder_ == null) {
                    this.operator_ = Operator.getDefaultInstance();
                } else {
                    this.operatorBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFFDF;
                this.position_ = 0;
                this.bitField0_ &= 0xFFFFFFBF;
                if (this.objectBuilder_ == null) {
                    this.object_ = Object.getDefaultInstance();
                } else {
                    this.objectBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFF7F;
                if (this.arrayBuilder_ == null) {
                    this.array_ = Array.getDefaultInstance();
                } else {
                    this.arrayBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFEFF;
                return this;
            }

            @Override
            public Builder clone() {
                return Builder.create().mergeFrom(this.buildPartial());
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return internal_static_Mysqlx_Expr_Expr_descriptor;
            }

            @Override
            public Expr getDefaultInstanceForType() {
                return Expr.getDefaultInstance();
            }

            @Override
            public Expr build() {
                Expr result = this.buildPartial();
                if (!result.isInitialized()) {
                    throw Builder.newUninitializedMessageException(result);
                }
                return result;
            }

            @Override
            public Expr buildPartial() {
                Expr result = new Expr(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ |= 1;
                }
                result.type_ = this.type_;
                if ((from_bitField0_ & 2) == 2) {
                    to_bitField0_ |= 2;
                }
                if (this.identifierBuilder_ == null) {
                    result.identifier_ = this.identifier_;
                } else {
                    result.identifier_ = this.identifierBuilder_.build();
                }
                if ((from_bitField0_ & 4) == 4) {
                    to_bitField0_ |= 4;
                }
                result.variable_ = this.variable_;
                if ((from_bitField0_ & 8) == 8) {
                    to_bitField0_ |= 8;
                }
                if (this.literalBuilder_ == null) {
                    result.literal_ = this.literal_;
                } else {
                    result.literal_ = this.literalBuilder_.build();
                }
                if ((from_bitField0_ & 0x10) == 16) {
                    to_bitField0_ |= 0x10;
                }
                if (this.functionCallBuilder_ == null) {
                    result.functionCall_ = this.functionCall_;
                } else {
                    result.functionCall_ = this.functionCallBuilder_.build();
                }
                if ((from_bitField0_ & 0x20) == 32) {
                    to_bitField0_ |= 0x20;
                }
                if (this.operatorBuilder_ == null) {
                    result.operator_ = this.operator_;
                } else {
                    result.operator_ = this.operatorBuilder_.build();
                }
                if ((from_bitField0_ & 0x40) == 64) {
                    to_bitField0_ |= 0x40;
                }
                result.position_ = this.position_;
                if ((from_bitField0_ & 0x80) == 128) {
                    to_bitField0_ |= 0x80;
                }
                if (this.objectBuilder_ == null) {
                    result.object_ = this.object_;
                } else {
                    result.object_ = this.objectBuilder_.build();
                }
                if ((from_bitField0_ & 0x100) == 256) {
                    to_bitField0_ |= 0x100;
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
                if (other instanceof Expr) {
                    return this.mergeFrom((Expr)other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(Expr other) {
                if (other == Expr.getDefaultInstance()) {
                    return this;
                }
                if (other.hasType()) {
                    this.setType(other.getType());
                }
                if (other.hasIdentifier()) {
                    this.mergeIdentifier(other.getIdentifier());
                }
                if (other.hasVariable()) {
                    this.bitField0_ |= 4;
                    this.variable_ = other.variable_;
                    this.onChanged();
                }
                if (other.hasLiteral()) {
                    this.mergeLiteral(other.getLiteral());
                }
                if (other.hasFunctionCall()) {
                    this.mergeFunctionCall(other.getFunctionCall());
                }
                if (other.hasOperator()) {
                    this.mergeOperator(other.getOperator());
                }
                if (other.hasPosition()) {
                    this.setPosition(other.getPosition());
                }
                if (other.hasObject()) {
                    this.mergeObject(other.getObject());
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
                if (this.hasIdentifier() && !this.getIdentifier().isInitialized()) {
                    return false;
                }
                if (this.hasLiteral() && !this.getLiteral().isInitialized()) {
                    return false;
                }
                if (this.hasFunctionCall() && !this.getFunctionCall().isInitialized()) {
                    return false;
                }
                if (this.hasOperator() && !this.getOperator().isInitialized()) {
                    return false;
                }
                if (this.hasObject() && !this.getObject().isInitialized()) {
                    return false;
                }
                return !this.hasArray() || this.getArray().isInitialized();
            }

            @Override
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                Expr parsedMessage = null;
                try {
                    parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
                } catch (InvalidProtocolBufferException e) {
                    parsedMessage = (Expr)e.getUnfinishedMessage();
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
                this.type_ = Type.IDENT;
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasIdentifier() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override
            public ColumnIdentifier getIdentifier() {
                if (this.identifierBuilder_ == null) {
                    return this.identifier_;
                }
                return this.identifierBuilder_.getMessage();
            }

            public Builder setIdentifier(ColumnIdentifier value) {
                if (this.identifierBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.identifier_ = value;
                    this.onChanged();
                } else {
                    this.identifierBuilder_.setMessage(value);
                }
                this.bitField0_ |= 2;
                return this;
            }

            public Builder setIdentifier(ColumnIdentifier.Builder builderForValue) {
                if (this.identifierBuilder_ == null) {
                    this.identifier_ = builderForValue.build();
                    this.onChanged();
                } else {
                    this.identifierBuilder_.setMessage(builderForValue.build());
                }
                this.bitField0_ |= 2;
                return this;
            }

            public Builder mergeIdentifier(ColumnIdentifier value) {
                if (this.identifierBuilder_ == null) {
                    this.identifier_ = (this.bitField0_ & 2) == 2 && this.identifier_ != ColumnIdentifier.getDefaultInstance() ? ColumnIdentifier.newBuilder(this.identifier_).mergeFrom(value).buildPartial() : value;
                    this.onChanged();
                } else {
                    this.identifierBuilder_.mergeFrom(value);
                }
                this.bitField0_ |= 2;
                return this;
            }

            public Builder clearIdentifier() {
                if (this.identifierBuilder_ == null) {
                    this.identifier_ = ColumnIdentifier.getDefaultInstance();
                    this.onChanged();
                } else {
                    this.identifierBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFFFD;
                return this;
            }

            public ColumnIdentifier.Builder getIdentifierBuilder() {
                this.bitField0_ |= 2;
                this.onChanged();
                return this.getIdentifierFieldBuilder().getBuilder();
            }

            @Override
            public ColumnIdentifierOrBuilder getIdentifierOrBuilder() {
                if (this.identifierBuilder_ != null) {
                    return this.identifierBuilder_.getMessageOrBuilder();
                }
                return this.identifier_;
            }

            private SingleFieldBuilder<ColumnIdentifier, ColumnIdentifier.Builder, ColumnIdentifierOrBuilder> getIdentifierFieldBuilder() {
                if (this.identifierBuilder_ == null) {
                    this.identifierBuilder_ = new SingleFieldBuilder(this.getIdentifier(), this.getParentForChildren(), this.isClean());
                    this.identifier_ = null;
                }
                return this.identifierBuilder_;
            }

            @Override
            public boolean hasVariable() {
                return (this.bitField0_ & 4) == 4;
            }

            @Override
            public String getVariable() {
                java.lang.Object ref = this.variable_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString)ref;
                    String s2 = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.variable_ = s2;
                    }
                    return s2;
                }
                return (String)ref;
            }

            @Override
            public ByteString getVariableBytes() {
                java.lang.Object ref = this.variable_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String)ref);
                    this.variable_ = b;
                    return b;
                }
                return (ByteString)ref;
            }

            public Builder setVariable(String value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 4;
                this.variable_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearVariable() {
                this.bitField0_ &= 0xFFFFFFFB;
                this.variable_ = Expr.getDefaultInstance().getVariable();
                this.onChanged();
                return this;
            }

            public Builder setVariableBytes(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 4;
                this.variable_ = value;
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasLiteral() {
                return (this.bitField0_ & 8) == 8;
            }

            @Override
            public MysqlxDatatypes.Scalar getLiteral() {
                if (this.literalBuilder_ == null) {
                    return this.literal_;
                }
                return this.literalBuilder_.getMessage();
            }

            public Builder setLiteral(MysqlxDatatypes.Scalar value) {
                if (this.literalBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.literal_ = value;
                    this.onChanged();
                } else {
                    this.literalBuilder_.setMessage(value);
                }
                this.bitField0_ |= 8;
                return this;
            }

            public Builder setLiteral(MysqlxDatatypes.Scalar.Builder builderForValue) {
                if (this.literalBuilder_ == null) {
                    this.literal_ = builderForValue.build();
                    this.onChanged();
                } else {
                    this.literalBuilder_.setMessage(builderForValue.build());
                }
                this.bitField0_ |= 8;
                return this;
            }

            public Builder mergeLiteral(MysqlxDatatypes.Scalar value) {
                if (this.literalBuilder_ == null) {
                    this.literal_ = (this.bitField0_ & 8) == 8 && this.literal_ != MysqlxDatatypes.Scalar.getDefaultInstance() ? MysqlxDatatypes.Scalar.newBuilder(this.literal_).mergeFrom(value).buildPartial() : value;
                    this.onChanged();
                } else {
                    this.literalBuilder_.mergeFrom(value);
                }
                this.bitField0_ |= 8;
                return this;
            }

            public Builder clearLiteral() {
                if (this.literalBuilder_ == null) {
                    this.literal_ = MysqlxDatatypes.Scalar.getDefaultInstance();
                    this.onChanged();
                } else {
                    this.literalBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFFF7;
                return this;
            }

            public MysqlxDatatypes.Scalar.Builder getLiteralBuilder() {
                this.bitField0_ |= 8;
                this.onChanged();
                return this.getLiteralFieldBuilder().getBuilder();
            }

            @Override
            public MysqlxDatatypes.ScalarOrBuilder getLiteralOrBuilder() {
                if (this.literalBuilder_ != null) {
                    return this.literalBuilder_.getMessageOrBuilder();
                }
                return this.literal_;
            }

            private SingleFieldBuilder<MysqlxDatatypes.Scalar, MysqlxDatatypes.Scalar.Builder, MysqlxDatatypes.ScalarOrBuilder> getLiteralFieldBuilder() {
                if (this.literalBuilder_ == null) {
                    this.literalBuilder_ = new SingleFieldBuilder(this.getLiteral(), this.getParentForChildren(), this.isClean());
                    this.literal_ = null;
                }
                return this.literalBuilder_;
            }

            @Override
            public boolean hasFunctionCall() {
                return (this.bitField0_ & 0x10) == 16;
            }

            @Override
            public FunctionCall getFunctionCall() {
                if (this.functionCallBuilder_ == null) {
                    return this.functionCall_;
                }
                return this.functionCallBuilder_.getMessage();
            }

            public Builder setFunctionCall(FunctionCall value) {
                if (this.functionCallBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.functionCall_ = value;
                    this.onChanged();
                } else {
                    this.functionCallBuilder_.setMessage(value);
                }
                this.bitField0_ |= 0x10;
                return this;
            }

            public Builder setFunctionCall(FunctionCall.Builder builderForValue) {
                if (this.functionCallBuilder_ == null) {
                    this.functionCall_ = builderForValue.build();
                    this.onChanged();
                } else {
                    this.functionCallBuilder_.setMessage(builderForValue.build());
                }
                this.bitField0_ |= 0x10;
                return this;
            }

            public Builder mergeFunctionCall(FunctionCall value) {
                if (this.functionCallBuilder_ == null) {
                    this.functionCall_ = (this.bitField0_ & 0x10) == 16 && this.functionCall_ != FunctionCall.getDefaultInstance() ? FunctionCall.newBuilder(this.functionCall_).mergeFrom(value).buildPartial() : value;
                    this.onChanged();
                } else {
                    this.functionCallBuilder_.mergeFrom(value);
                }
                this.bitField0_ |= 0x10;
                return this;
            }

            public Builder clearFunctionCall() {
                if (this.functionCallBuilder_ == null) {
                    this.functionCall_ = FunctionCall.getDefaultInstance();
                    this.onChanged();
                } else {
                    this.functionCallBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFFEF;
                return this;
            }

            public FunctionCall.Builder getFunctionCallBuilder() {
                this.bitField0_ |= 0x10;
                this.onChanged();
                return this.getFunctionCallFieldBuilder().getBuilder();
            }

            @Override
            public FunctionCallOrBuilder getFunctionCallOrBuilder() {
                if (this.functionCallBuilder_ != null) {
                    return this.functionCallBuilder_.getMessageOrBuilder();
                }
                return this.functionCall_;
            }

            private SingleFieldBuilder<FunctionCall, FunctionCall.Builder, FunctionCallOrBuilder> getFunctionCallFieldBuilder() {
                if (this.functionCallBuilder_ == null) {
                    this.functionCallBuilder_ = new SingleFieldBuilder(this.getFunctionCall(), this.getParentForChildren(), this.isClean());
                    this.functionCall_ = null;
                }
                return this.functionCallBuilder_;
            }

            @Override
            public boolean hasOperator() {
                return (this.bitField0_ & 0x20) == 32;
            }

            @Override
            public Operator getOperator() {
                if (this.operatorBuilder_ == null) {
                    return this.operator_;
                }
                return this.operatorBuilder_.getMessage();
            }

            public Builder setOperator(Operator value) {
                if (this.operatorBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.operator_ = value;
                    this.onChanged();
                } else {
                    this.operatorBuilder_.setMessage(value);
                }
                this.bitField0_ |= 0x20;
                return this;
            }

            public Builder setOperator(Operator.Builder builderForValue) {
                if (this.operatorBuilder_ == null) {
                    this.operator_ = builderForValue.build();
                    this.onChanged();
                } else {
                    this.operatorBuilder_.setMessage(builderForValue.build());
                }
                this.bitField0_ |= 0x20;
                return this;
            }

            public Builder mergeOperator(Operator value) {
                if (this.operatorBuilder_ == null) {
                    this.operator_ = (this.bitField0_ & 0x20) == 32 && this.operator_ != Operator.getDefaultInstance() ? Operator.newBuilder(this.operator_).mergeFrom(value).buildPartial() : value;
                    this.onChanged();
                } else {
                    this.operatorBuilder_.mergeFrom(value);
                }
                this.bitField0_ |= 0x20;
                return this;
            }

            public Builder clearOperator() {
                if (this.operatorBuilder_ == null) {
                    this.operator_ = Operator.getDefaultInstance();
                    this.onChanged();
                } else {
                    this.operatorBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFFDF;
                return this;
            }

            public Operator.Builder getOperatorBuilder() {
                this.bitField0_ |= 0x20;
                this.onChanged();
                return this.getOperatorFieldBuilder().getBuilder();
            }

            @Override
            public OperatorOrBuilder getOperatorOrBuilder() {
                if (this.operatorBuilder_ != null) {
                    return this.operatorBuilder_.getMessageOrBuilder();
                }
                return this.operator_;
            }

            private SingleFieldBuilder<Operator, Operator.Builder, OperatorOrBuilder> getOperatorFieldBuilder() {
                if (this.operatorBuilder_ == null) {
                    this.operatorBuilder_ = new SingleFieldBuilder(this.getOperator(), this.getParentForChildren(), this.isClean());
                    this.operator_ = null;
                }
                return this.operatorBuilder_;
            }

            @Override
            public boolean hasPosition() {
                return (this.bitField0_ & 0x40) == 64;
            }

            @Override
            public int getPosition() {
                return this.position_;
            }

            public Builder setPosition(int value) {
                this.bitField0_ |= 0x40;
                this.position_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearPosition() {
                this.bitField0_ &= 0xFFFFFFBF;
                this.position_ = 0;
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasObject() {
                return (this.bitField0_ & 0x80) == 128;
            }

            @Override
            public Object getObject() {
                if (this.objectBuilder_ == null) {
                    return this.object_;
                }
                return this.objectBuilder_.getMessage();
            }

            public Builder setObject(Object value) {
                if (this.objectBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.object_ = value;
                    this.onChanged();
                } else {
                    this.objectBuilder_.setMessage(value);
                }
                this.bitField0_ |= 0x80;
                return this;
            }

            public Builder setObject(Object.Builder builderForValue) {
                if (this.objectBuilder_ == null) {
                    this.object_ = builderForValue.build();
                    this.onChanged();
                } else {
                    this.objectBuilder_.setMessage(builderForValue.build());
                }
                this.bitField0_ |= 0x80;
                return this;
            }

            public Builder mergeObject(Object value) {
                if (this.objectBuilder_ == null) {
                    this.object_ = (this.bitField0_ & 0x80) == 128 && this.object_ != Object.getDefaultInstance() ? Object.newBuilder(this.object_).mergeFrom(value).buildPartial() : value;
                    this.onChanged();
                } else {
                    this.objectBuilder_.mergeFrom(value);
                }
                this.bitField0_ |= 0x80;
                return this;
            }

            public Builder clearObject() {
                if (this.objectBuilder_ == null) {
                    this.object_ = Object.getDefaultInstance();
                    this.onChanged();
                } else {
                    this.objectBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFF7F;
                return this;
            }

            public Object.Builder getObjectBuilder() {
                this.bitField0_ |= 0x80;
                this.onChanged();
                return this.getObjectFieldBuilder().getBuilder();
            }

            @Override
            public ObjectOrBuilder getObjectOrBuilder() {
                if (this.objectBuilder_ != null) {
                    return this.objectBuilder_.getMessageOrBuilder();
                }
                return this.object_;
            }

            private SingleFieldBuilder<Object, Object.Builder, ObjectOrBuilder> getObjectFieldBuilder() {
                if (this.objectBuilder_ == null) {
                    this.objectBuilder_ = new SingleFieldBuilder(this.getObject(), this.getParentForChildren(), this.isClean());
                    this.object_ = null;
                }
                return this.objectBuilder_;
            }

            @Override
            public boolean hasArray() {
                return (this.bitField0_ & 0x100) == 256;
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
                this.bitField0_ |= 0x100;
                return this;
            }

            public Builder setArray(Array.Builder builderForValue) {
                if (this.arrayBuilder_ == null) {
                    this.array_ = builderForValue.build();
                    this.onChanged();
                } else {
                    this.arrayBuilder_.setMessage(builderForValue.build());
                }
                this.bitField0_ |= 0x100;
                return this;
            }

            public Builder mergeArray(Array value) {
                if (this.arrayBuilder_ == null) {
                    this.array_ = (this.bitField0_ & 0x100) == 256 && this.array_ != Array.getDefaultInstance() ? Array.newBuilder(this.array_).mergeFrom(value).buildPartial() : value;
                    this.onChanged();
                } else {
                    this.arrayBuilder_.mergeFrom(value);
                }
                this.bitField0_ |= 0x100;
                return this;
            }

            public Builder clearArray() {
                if (this.arrayBuilder_ == null) {
                    this.array_ = Array.getDefaultInstance();
                    this.onChanged();
                } else {
                    this.arrayBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFEFF;
                return this;
            }

            public Array.Builder getArrayBuilder() {
                this.bitField0_ |= 0x100;
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
            IDENT(0, 1),
            LITERAL(1, 2),
            VARIABLE(2, 3),
            FUNC_CALL(3, 4),
            OPERATOR(4, 5),
            PLACEHOLDER(5, 6),
            OBJECT(6, 7),
            ARRAY(7, 8);

            public static final int IDENT_VALUE = 1;
            public static final int LITERAL_VALUE = 2;
            public static final int VARIABLE_VALUE = 3;
            public static final int FUNC_CALL_VALUE = 4;
            public static final int OPERATOR_VALUE = 5;
            public static final int PLACEHOLDER_VALUE = 6;
            public static final int OBJECT_VALUE = 7;
            public static final int ARRAY_VALUE = 8;
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
                        return IDENT;
                    }
                    case 2: {
                        return LITERAL;
                    }
                    case 3: {
                        return VARIABLE;
                    }
                    case 4: {
                        return FUNC_CALL;
                    }
                    case 5: {
                        return OPERATOR;
                    }
                    case 6: {
                        return PLACEHOLDER;
                    }
                    case 7: {
                        return OBJECT;
                    }
                    case 8: {
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
                return Expr.getDescriptor().getEnumTypes().get(0);
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

    public static interface ExprOrBuilder
    extends MessageOrBuilder {
        public boolean hasType();

        public Expr.Type getType();

        public boolean hasIdentifier();

        public ColumnIdentifier getIdentifier();

        public ColumnIdentifierOrBuilder getIdentifierOrBuilder();

        public boolean hasVariable();

        public String getVariable();

        public ByteString getVariableBytes();

        public boolean hasLiteral();

        public MysqlxDatatypes.Scalar getLiteral();

        public MysqlxDatatypes.ScalarOrBuilder getLiteralOrBuilder();

        public boolean hasFunctionCall();

        public FunctionCall getFunctionCall();

        public FunctionCallOrBuilder getFunctionCallOrBuilder();

        public boolean hasOperator();

        public Operator getOperator();

        public OperatorOrBuilder getOperatorOrBuilder();

        public boolean hasPosition();

        public int getPosition();

        public boolean hasObject();

        public Object getObject();

        public ObjectOrBuilder getObjectOrBuilder();

        public boolean hasArray();

        public Array getArray();

        public ArrayOrBuilder getArrayOrBuilder();
    }
}

