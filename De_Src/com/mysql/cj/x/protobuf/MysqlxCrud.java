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
import com.google.protobuf.LazyStringArrayList;
import com.google.protobuf.LazyStringList;
import com.google.protobuf.Message;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.ProtocolMessageEnum;
import com.google.protobuf.ProtocolStringList;
import com.google.protobuf.RepeatedFieldBuilder;
import com.google.protobuf.SingleFieldBuilder;
import com.google.protobuf.UnknownFieldSet;
import com.mysql.cj.x.protobuf.Mysqlx;
import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import com.mysql.cj.x.protobuf.MysqlxExpr;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectStreamException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class MysqlxCrud {
    private static final Descriptors.Descriptor internal_static_Mysqlx_Crud_Column_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_Mysqlx_Crud_Column_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_Mysqlx_Crud_Projection_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_Mysqlx_Crud_Projection_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_Mysqlx_Crud_Collection_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_Mysqlx_Crud_Collection_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_Mysqlx_Crud_Limit_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_Mysqlx_Crud_Limit_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_Mysqlx_Crud_Order_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_Mysqlx_Crud_Order_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_Mysqlx_Crud_UpdateOperation_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_Mysqlx_Crud_UpdateOperation_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_Mysqlx_Crud_Find_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_Mysqlx_Crud_Find_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_Mysqlx_Crud_Insert_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_Mysqlx_Crud_Insert_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_Mysqlx_Crud_Insert_TypedRow_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_Mysqlx_Crud_Insert_TypedRow_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_Mysqlx_Crud_Update_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_Mysqlx_Crud_Update_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_Mysqlx_Crud_Delete_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_Mysqlx_Crud_Delete_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_Mysqlx_Crud_CreateView_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_Mysqlx_Crud_CreateView_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_Mysqlx_Crud_ModifyView_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_Mysqlx_Crud_ModifyView_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_Mysqlx_Crud_DropView_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_Mysqlx_Crud_DropView_fieldAccessorTable;
    private static Descriptors.FileDescriptor descriptor;

    private MysqlxCrud() {
    }

    public static void registerAllExtensions(ExtensionRegistry registry) {
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        String[] descriptorData = new String[]{"\n\u0011mysqlx_crud.proto\u0012\u000bMysqlx.Crud\u001a\fmysqlx.proto\u001a\u0011mysqlx_expr.proto\u001a\u0016mysqlx_datatypes.proto\"[\n\u0006Column\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\u0012\r\n\u0005alias\u0018\u0002 \u0001(\t\u00124\n\rdocument_path\u0018\u0003 \u0003(\u000b2\u001d.Mysqlx.Expr.DocumentPathItem\">\n\nProjection\u0012!\n\u0006source\u0018\u0001 \u0002(\u000b2\u0011.Mysqlx.Expr.Expr\u0012\r\n\u0005alias\u0018\u0002 \u0001(\t\"*\n\nCollection\u0012\f\n\u0004name\u0018\u0001 \u0002(\t\u0012\u000e\n\u0006schema\u0018\u0002 \u0001(\t\"*\n\u0005Limit\u0012\u0011\n\trow_count\u0018\u0001 \u0002(\u0004\u0012\u000e\n\u0006offset\u0018\u0002 \u0001(\u0004\"~\n\u0005Order\u0012\u001f\n\u0004expr\u0018\u0001 \u0002(\u000b2\u0011.Mysqlx.Expr.Expr\u00124\n\tdirection\u0018\u0002 \u0001(\u000e2\u001c.My", "sqlx.Crud.Order.Direction:\u0003ASC\"\u001e\n\tDirection\u0012\u0007\n\u0003ASC\u0010\u0001\u0012\b\n\u0004DESC\u0010\u0002\"\u00ac\u0002\n\u000fUpdateOperation\u0012-\n\u0006source\u0018\u0001 \u0002(\u000b2\u001d.Mysqlx.Expr.ColumnIdentifier\u0012:\n\toperation\u0018\u0002 \u0002(\u000e2'.Mysqlx.Crud.UpdateOperation.UpdateType\u0012 \n\u0005value\u0018\u0003 \u0001(\u000b2\u0011.Mysqlx.Expr.Expr\"\u008b\u0001\n\nUpdateType\u0012\u0007\n\u0003SET\u0010\u0001\u0012\u000f\n\u000bITEM_REMOVE\u0010\u0002\u0012\f\n\bITEM_SET\u0010\u0003\u0012\u0010\n\fITEM_REPLACE\u0010\u0004\u0012\u000e\n\nITEM_MERGE\u0010\u0005\u0012\u0010\n\fARRAY_INSERT\u0010\u0006\u0012\u0010\n\fARRAY_APPEND\u0010\u0007\u0012\u000f\n\u000bMERGE_PATCH\u0010\b\"\u00be\u0004\n\u0004Find\u0012+\n\ncollection\u0018\u0002 \u0002(\u000b2\u0017.Mys", "qlx.Crud.Collection\u0012*\n\ndata_model\u0018\u0003 \u0001(\u000e2\u0016.Mysqlx.Crud.DataModel\u0012+\n\nprojection\u0018\u0004 \u0003(\u000b2\u0017.Mysqlx.Crud.Projection\u0012#\n\bcriteria\u0018\u0005 \u0001(\u000b2\u0011.Mysqlx.Expr.Expr\u0012&\n\u0004args\u0018\u000b \u0003(\u000b2\u0018.Mysqlx.Datatypes.Scalar\u0012!\n\u0005limit\u0018\u0006 \u0001(\u000b2\u0012.Mysqlx.Crud.Limit\u0012!\n\u0005order\u0018\u0007 \u0003(\u000b2\u0012.Mysqlx.Crud.Order\u0012#\n\bgrouping\u0018\b \u0003(\u000b2\u0011.Mysqlx.Expr.Expr\u0012,\n\u0011grouping_criteria\u0018\t \u0001(\u000b2\u0011.Mysqlx.Expr.Expr\u0012*\n\u0007locking\u0018\f \u0001(\u000e2\u0019.Mysqlx.Crud.Find.RowLock\u00129\n\u000flocking_option", "s\u0018\r \u0001(\u000e2 .Mysqlx.Crud.Find.RowLockOptions\".\n\u0007RowLock\u0012\u000f\n\u000bSHARED_LOCK\u0010\u0001\u0012\u0012\n\u000eEXCLUSIVE_LOCK\u0010\u0002\"-\n\u000eRowLockOptions\u0012\n\n\u0006NOWAIT\u0010\u0001\u0012\u000f\n\u000bSKIP_LOCKED\u0010\u0002:\u0004\u0088\u00ea0\u0011\"\u00a8\u0002\n\u0006Insert\u0012+\n\ncollection\u0018\u0001 \u0002(\u000b2\u0017.Mysqlx.Crud.Collection\u0012*\n\ndata_model\u0018\u0002 \u0001(\u000e2\u0016.Mysqlx.Crud.DataModel\u0012'\n\nprojection\u0018\u0003 \u0003(\u000b2\u0013.Mysqlx.Crud.Column\u0012)\n\u0003row\u0018\u0004 \u0003(\u000b2\u001c.Mysqlx.Crud.Insert.TypedRow\u0012&\n\u0004args\u0018\u0005 \u0003(\u000b2\u0018.Mysqlx.Datatypes.Scalar\u0012\u0015\n\u0006upsert\u0018\u0006 \u0001(\b:\u0005false\u001a,\n\bTypedRo", "w\u0012 \n\u0005field\u0018\u0001 \u0003(\u000b2\u0011.Mysqlx.Expr.Expr:\u0004\u0088\u00ea0\u0012\"\u00ab\u0002\n\u0006Update\u0012+\n\ncollection\u0018\u0002 \u0002(\u000b2\u0017.Mysqlx.Crud.Collection\u0012*\n\ndata_model\u0018\u0003 \u0001(\u000e2\u0016.Mysqlx.Crud.DataModel\u0012#\n\bcriteria\u0018\u0004 \u0001(\u000b2\u0011.Mysqlx.Expr.Expr\u0012&\n\u0004args\u0018\b \u0003(\u000b2\u0018.Mysqlx.Datatypes.Scalar\u0012!\n\u0005limit\u0018\u0005 \u0001(\u000b2\u0012.Mysqlx.Crud.Limit\u0012!\n\u0005order\u0018\u0006 \u0003(\u000b2\u0012.Mysqlx.Crud.Order\u0012/\n\toperation\u0018\u0007 \u0003(\u000b2\u001c.Mysqlx.Crud.UpdateOperation:\u0004\u0088\u00ea0\u0013\"\u00fa\u0001\n\u0006Delete\u0012+\n\ncollection\u0018\u0001 \u0002(\u000b2\u0017.Mysqlx.Crud.Collection\u0012", "*\n\ndata_model\u0018\u0002 \u0001(\u000e2\u0016.Mysqlx.Crud.DataModel\u0012#\n\bcriteria\u0018\u0003 \u0001(\u000b2\u0011.Mysqlx.Expr.Expr\u0012&\n\u0004args\u0018\u0006 \u0003(\u000b2\u0018.Mysqlx.Datatypes.Scalar\u0012!\n\u0005limit\u0018\u0004 \u0001(\u000b2\u0012.Mysqlx.Crud.Limit\u0012!\n\u0005order\u0018\u0005 \u0003(\u000b2\u0012.Mysqlx.Crud.Order:\u0004\u0088\u00ea0\u0014\"\u00c2\u0002\n\nCreateView\u0012+\n\ncollection\u0018\u0001 \u0002(\u000b2\u0017.Mysqlx.Crud.Collection\u0012\u000f\n\u0007definer\u0018\u0002 \u0001(\t\u00128\n\talgorithm\u0018\u0003 \u0001(\u000e2\u001a.Mysqlx.Crud.ViewAlgorithm:\tUNDEFINED\u00127\n\bsecurity\u0018\u0004 \u0001(\u000e2\u001c.Mysqlx.Crud.ViewSqlSecurity:\u0007DEFINER\u0012+\n\u0005check\u0018\u0005 ", "\u0001(\u000e2\u001c.Mysqlx.Crud.ViewCheckOption\u0012\u000e\n\u0006column\u0018\u0006 \u0003(\t\u0012\u001f\n\u0004stmt\u0018\u0007 \u0002(\u000b2\u0011.Mysqlx.Crud.Find\u0012\u001f\n\u0010replace_existing\u0018\b \u0001(\b:\u0005false:\u0004\u0088\u00ea0\u001e\"\u008d\u0002\n\nModifyView\u0012+\n\ncollection\u0018\u0001 \u0002(\u000b2\u0017.Mysqlx.Crud.Collection\u0012\u000f\n\u0007definer\u0018\u0002 \u0001(\t\u0012-\n\talgorithm\u0018\u0003 \u0001(\u000e2\u001a.Mysqlx.Crud.ViewAlgorithm\u0012.\n\bsecurity\u0018\u0004 \u0001(\u000e2\u001c.Mysqlx.Crud.ViewSqlSecurity\u0012+\n\u0005check\u0018\u0005 \u0001(\u000e2\u001c.Mysqlx.Crud.ViewCheckOption\u0012\u000e\n\u0006column\u0018\u0006 \u0003(\t\u0012\u001f\n\u0004stmt\u0018\u0007 \u0001(\u000b2\u0011.Mysqlx.Crud.Find:\u0004\u0088\u00ea0\u001f\"W\n\bDro", "pView\u0012+\n\ncollection\u0018\u0001 \u0002(\u000b2\u0017.Mysqlx.Crud.Collection\u0012\u0018\n\tif_exists\u0018\u0002 \u0001(\b:\u0005false:\u0004\u0088\u00ea0 *$\n\tDataModel\u0012\f\n\bDOCUMENT\u0010\u0001\u0012\t\n\u0005TABLE\u0010\u0002*8\n\rViewAlgorithm\u0012\r\n\tUNDEFINED\u0010\u0001\u0012\t\n\u0005MERGE\u0010\u0002\u0012\r\n\tTEMPTABLE\u0010\u0003*+\n\u000fViewSqlSecurity\u0012\u000b\n\u0007INVOKER\u0010\u0001\u0012\u000b\n\u0007DEFINER\u0010\u0002**\n\u000fViewCheckOption\u0012\t\n\u0005LOCAL\u0010\u0001\u0012\f\n\bCASCADED\u0010\u0002B\u0019\n\u0017com.mysql.cj.x.protobuf"};
        Descriptors.FileDescriptor.InternalDescriptorAssigner assigner = new Descriptors.FileDescriptor.InternalDescriptorAssigner(){

            @Override
            public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor root) {
                descriptor = root;
                return null;
            }
        };
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[]{Mysqlx.getDescriptor(), MysqlxExpr.getDescriptor(), MysqlxDatatypes.getDescriptor()}, assigner);
        internal_static_Mysqlx_Crud_Column_descriptor = MysqlxCrud.getDescriptor().getMessageTypes().get(0);
        internal_static_Mysqlx_Crud_Column_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_Mysqlx_Crud_Column_descriptor, new String[]{"Name", "Alias", "DocumentPath"});
        internal_static_Mysqlx_Crud_Projection_descriptor = MysqlxCrud.getDescriptor().getMessageTypes().get(1);
        internal_static_Mysqlx_Crud_Projection_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_Mysqlx_Crud_Projection_descriptor, new String[]{"Source", "Alias"});
        internal_static_Mysqlx_Crud_Collection_descriptor = MysqlxCrud.getDescriptor().getMessageTypes().get(2);
        internal_static_Mysqlx_Crud_Collection_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_Mysqlx_Crud_Collection_descriptor, new String[]{"Name", "Schema"});
        internal_static_Mysqlx_Crud_Limit_descriptor = MysqlxCrud.getDescriptor().getMessageTypes().get(3);
        internal_static_Mysqlx_Crud_Limit_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_Mysqlx_Crud_Limit_descriptor, new String[]{"RowCount", "Offset"});
        internal_static_Mysqlx_Crud_Order_descriptor = MysqlxCrud.getDescriptor().getMessageTypes().get(4);
        internal_static_Mysqlx_Crud_Order_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_Mysqlx_Crud_Order_descriptor, new String[]{"Expr", "Direction"});
        internal_static_Mysqlx_Crud_UpdateOperation_descriptor = MysqlxCrud.getDescriptor().getMessageTypes().get(5);
        internal_static_Mysqlx_Crud_UpdateOperation_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_Mysqlx_Crud_UpdateOperation_descriptor, new String[]{"Source", "Operation", "Value"});
        internal_static_Mysqlx_Crud_Find_descriptor = MysqlxCrud.getDescriptor().getMessageTypes().get(6);
        internal_static_Mysqlx_Crud_Find_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_Mysqlx_Crud_Find_descriptor, new String[]{"Collection", "DataModel", "Projection", "Criteria", "Args", "Limit", "Order", "Grouping", "GroupingCriteria", "Locking", "LockingOptions"});
        internal_static_Mysqlx_Crud_Insert_descriptor = MysqlxCrud.getDescriptor().getMessageTypes().get(7);
        internal_static_Mysqlx_Crud_Insert_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_Mysqlx_Crud_Insert_descriptor, new String[]{"Collection", "DataModel", "Projection", "Row", "Args", "Upsert"});
        internal_static_Mysqlx_Crud_Insert_TypedRow_descriptor = internal_static_Mysqlx_Crud_Insert_descriptor.getNestedTypes().get(0);
        internal_static_Mysqlx_Crud_Insert_TypedRow_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_Mysqlx_Crud_Insert_TypedRow_descriptor, new String[]{"Field"});
        internal_static_Mysqlx_Crud_Update_descriptor = MysqlxCrud.getDescriptor().getMessageTypes().get(8);
        internal_static_Mysqlx_Crud_Update_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_Mysqlx_Crud_Update_descriptor, new String[]{"Collection", "DataModel", "Criteria", "Args", "Limit", "Order", "Operation"});
        internal_static_Mysqlx_Crud_Delete_descriptor = MysqlxCrud.getDescriptor().getMessageTypes().get(9);
        internal_static_Mysqlx_Crud_Delete_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_Mysqlx_Crud_Delete_descriptor, new String[]{"Collection", "DataModel", "Criteria", "Args", "Limit", "Order"});
        internal_static_Mysqlx_Crud_CreateView_descriptor = MysqlxCrud.getDescriptor().getMessageTypes().get(10);
        internal_static_Mysqlx_Crud_CreateView_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_Mysqlx_Crud_CreateView_descriptor, new String[]{"Collection", "Definer", "Algorithm", "Security", "Check", "Column", "Stmt", "ReplaceExisting"});
        internal_static_Mysqlx_Crud_ModifyView_descriptor = MysqlxCrud.getDescriptor().getMessageTypes().get(11);
        internal_static_Mysqlx_Crud_ModifyView_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_Mysqlx_Crud_ModifyView_descriptor, new String[]{"Collection", "Definer", "Algorithm", "Security", "Check", "Column", "Stmt"});
        internal_static_Mysqlx_Crud_DropView_descriptor = MysqlxCrud.getDescriptor().getMessageTypes().get(12);
        internal_static_Mysqlx_Crud_DropView_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_Mysqlx_Crud_DropView_descriptor, new String[]{"Collection", "IfExists"});
        ExtensionRegistry registry = ExtensionRegistry.newInstance();
        registry.add(Mysqlx.clientMessageId);
        registry.add(Mysqlx.clientMessageId);
        registry.add(Mysqlx.clientMessageId);
        registry.add(Mysqlx.clientMessageId);
        registry.add(Mysqlx.clientMessageId);
        registry.add(Mysqlx.clientMessageId);
        registry.add(Mysqlx.clientMessageId);
        Descriptors.FileDescriptor.internalUpdateFileDescriptor(descriptor, registry);
        Mysqlx.getDescriptor();
        MysqlxExpr.getDescriptor();
        MysqlxDatatypes.getDescriptor();
    }

    public static final class DropView
    extends GeneratedMessage
    implements DropViewOrBuilder {
        private static final DropView defaultInstance;
        private final UnknownFieldSet unknownFields;
        public static Parser<DropView> PARSER;
        private int bitField0_;
        public static final int COLLECTION_FIELD_NUMBER = 1;
        private Collection collection_;
        public static final int IF_EXISTS_FIELD_NUMBER = 2;
        private boolean ifExists_;
        private byte memoizedIsInitialized = (byte)-1;
        private int memoizedSerializedSize = -1;
        private static final long serialVersionUID = 0L;

        private DropView(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.unknownFields = builder.getUnknownFields();
        }

        private DropView(boolean noInit) {
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static DropView getDefaultInstance() {
            return defaultInstance;
        }

        @Override
        public DropView getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private DropView(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
                            Collection.Builder subBuilder = null;
                            if ((this.bitField0_ & 1) == 1) {
                                subBuilder = this.collection_.toBuilder();
                            }
                            this.collection_ = input.readMessage(Collection.PARSER, extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom(this.collection_);
                                this.collection_ = subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 1;
                            continue block11;
                        }
                        case 16: 
                    }
                    this.bitField0_ |= 2;
                    this.ifExists_ = input.readBool();
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
            return internal_static_Mysqlx_Crud_DropView_descriptor;
        }

        @Override
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return internal_static_Mysqlx_Crud_DropView_fieldAccessorTable.ensureFieldAccessorsInitialized(DropView.class, Builder.class);
        }

        public Parser<DropView> getParserForType() {
            return PARSER;
        }

        @Override
        public boolean hasCollection() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override
        public Collection getCollection() {
            return this.collection_;
        }

        @Override
        public CollectionOrBuilder getCollectionOrBuilder() {
            return this.collection_;
        }

        @Override
        public boolean hasIfExists() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override
        public boolean getIfExists() {
            return this.ifExists_;
        }

        private void initFields() {
            this.collection_ = Collection.getDefaultInstance();
            this.ifExists_ = false;
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
            if (!this.hasCollection()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            if (!this.getCollection().isInitialized()) {
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
                output.writeMessage(1, this.collection_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeBool(2, this.ifExists_);
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
                size += CodedOutputStream.computeMessageSize(1, this.collection_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size += CodedOutputStream.computeBoolSize(2, this.ifExists_);
            }
            this.memoizedSerializedSize = size += this.getUnknownFields().getSerializedSize();
            return size;
        }

        @Override
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static DropView parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static DropView parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static DropView parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static DropView parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static DropView parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static DropView parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static DropView parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static DropView parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static DropView parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static DropView parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override
        public Builder newBuilderForType() {
            return DropView.newBuilder();
        }

        public static Builder newBuilder(DropView prototype) {
            return DropView.newBuilder().mergeFrom(prototype);
        }

        @Override
        public Builder toBuilder() {
            return DropView.newBuilder(this);
        }

        @Override
        protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        static {
            PARSER = new AbstractParser<DropView>(){

                @Override
                public DropView parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                    return new DropView(input, extensionRegistry);
                }
            };
            defaultInstance = new DropView(true);
            defaultInstance.initFields();
        }

        public static final class Builder
        extends GeneratedMessage.Builder<Builder>
        implements DropViewOrBuilder {
            private int bitField0_;
            private Collection collection_ = Collection.getDefaultInstance();
            private SingleFieldBuilder<Collection, Collection.Builder, CollectionOrBuilder> collectionBuilder_;
            private boolean ifExists_;

            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_Mysqlx_Crud_DropView_descriptor;
            }

            @Override
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_Mysqlx_Crud_DropView_fieldAccessorTable.ensureFieldAccessorsInitialized(DropView.class, Builder.class);
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
                    this.getCollectionFieldBuilder();
                }
            }

            private static Builder create() {
                return new Builder();
            }

            @Override
            public Builder clear() {
                super.clear();
                if (this.collectionBuilder_ == null) {
                    this.collection_ = Collection.getDefaultInstance();
                } else {
                    this.collectionBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFFFE;
                this.ifExists_ = false;
                this.bitField0_ &= 0xFFFFFFFD;
                return this;
            }

            @Override
            public Builder clone() {
                return Builder.create().mergeFrom(this.buildPartial());
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return internal_static_Mysqlx_Crud_DropView_descriptor;
            }

            @Override
            public DropView getDefaultInstanceForType() {
                return DropView.getDefaultInstance();
            }

            @Override
            public DropView build() {
                DropView result = this.buildPartial();
                if (!result.isInitialized()) {
                    throw Builder.newUninitializedMessageException(result);
                }
                return result;
            }

            @Override
            public DropView buildPartial() {
                DropView result = new DropView(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ |= 1;
                }
                if (this.collectionBuilder_ == null) {
                    result.collection_ = this.collection_;
                } else {
                    result.collection_ = this.collectionBuilder_.build();
                }
                if ((from_bitField0_ & 2) == 2) {
                    to_bitField0_ |= 2;
                }
                result.ifExists_ = this.ifExists_;
                result.bitField0_ = to_bitField0_;
                this.onBuilt();
                return result;
            }

            @Override
            public Builder mergeFrom(Message other) {
                if (other instanceof DropView) {
                    return this.mergeFrom((DropView)other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(DropView other) {
                if (other == DropView.getDefaultInstance()) {
                    return this;
                }
                if (other.hasCollection()) {
                    this.mergeCollection(other.getCollection());
                }
                if (other.hasIfExists()) {
                    this.setIfExists(other.getIfExists());
                }
                this.mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            @Override
            public final boolean isInitialized() {
                if (!this.hasCollection()) {
                    return false;
                }
                return this.getCollection().isInitialized();
            }

            @Override
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                DropView parsedMessage = null;
                try {
                    parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
                } catch (InvalidProtocolBufferException e) {
                    parsedMessage = (DropView)e.getUnfinishedMessage();
                    throw e;
                } finally {
                    if (parsedMessage != null) {
                        this.mergeFrom(parsedMessage);
                    }
                }
                return this;
            }

            @Override
            public boolean hasCollection() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override
            public Collection getCollection() {
                if (this.collectionBuilder_ == null) {
                    return this.collection_;
                }
                return this.collectionBuilder_.getMessage();
            }

            public Builder setCollection(Collection value) {
                if (this.collectionBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.collection_ = value;
                    this.onChanged();
                } else {
                    this.collectionBuilder_.setMessage(value);
                }
                this.bitField0_ |= 1;
                return this;
            }

            public Builder setCollection(Collection.Builder builderForValue) {
                if (this.collectionBuilder_ == null) {
                    this.collection_ = builderForValue.build();
                    this.onChanged();
                } else {
                    this.collectionBuilder_.setMessage(builderForValue.build());
                }
                this.bitField0_ |= 1;
                return this;
            }

            public Builder mergeCollection(Collection value) {
                if (this.collectionBuilder_ == null) {
                    this.collection_ = (this.bitField0_ & 1) == 1 && this.collection_ != Collection.getDefaultInstance() ? Collection.newBuilder(this.collection_).mergeFrom(value).buildPartial() : value;
                    this.onChanged();
                } else {
                    this.collectionBuilder_.mergeFrom(value);
                }
                this.bitField0_ |= 1;
                return this;
            }

            public Builder clearCollection() {
                if (this.collectionBuilder_ == null) {
                    this.collection_ = Collection.getDefaultInstance();
                    this.onChanged();
                } else {
                    this.collectionBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFFFE;
                return this;
            }

            public Collection.Builder getCollectionBuilder() {
                this.bitField0_ |= 1;
                this.onChanged();
                return this.getCollectionFieldBuilder().getBuilder();
            }

            @Override
            public CollectionOrBuilder getCollectionOrBuilder() {
                if (this.collectionBuilder_ != null) {
                    return this.collectionBuilder_.getMessageOrBuilder();
                }
                return this.collection_;
            }

            private SingleFieldBuilder<Collection, Collection.Builder, CollectionOrBuilder> getCollectionFieldBuilder() {
                if (this.collectionBuilder_ == null) {
                    this.collectionBuilder_ = new SingleFieldBuilder(this.getCollection(), this.getParentForChildren(), this.isClean());
                    this.collection_ = null;
                }
                return this.collectionBuilder_;
            }

            @Override
            public boolean hasIfExists() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override
            public boolean getIfExists() {
                return this.ifExists_;
            }

            public Builder setIfExists(boolean value) {
                this.bitField0_ |= 2;
                this.ifExists_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearIfExists() {
                this.bitField0_ &= 0xFFFFFFFD;
                this.ifExists_ = false;
                this.onChanged();
                return this;
            }
        }
    }

    public static interface DropViewOrBuilder
    extends MessageOrBuilder {
        public boolean hasCollection();

        public Collection getCollection();

        public CollectionOrBuilder getCollectionOrBuilder();

        public boolean hasIfExists();

        public boolean getIfExists();
    }

    public static final class ModifyView
    extends GeneratedMessage
    implements ModifyViewOrBuilder {
        private static final ModifyView defaultInstance;
        private final UnknownFieldSet unknownFields;
        public static Parser<ModifyView> PARSER;
        private int bitField0_;
        public static final int COLLECTION_FIELD_NUMBER = 1;
        private Collection collection_;
        public static final int DEFINER_FIELD_NUMBER = 2;
        private Object definer_;
        public static final int ALGORITHM_FIELD_NUMBER = 3;
        private ViewAlgorithm algorithm_;
        public static final int SECURITY_FIELD_NUMBER = 4;
        private ViewSqlSecurity security_;
        public static final int CHECK_FIELD_NUMBER = 5;
        private ViewCheckOption check_;
        public static final int COLUMN_FIELD_NUMBER = 6;
        private LazyStringList column_;
        public static final int STMT_FIELD_NUMBER = 7;
        private Find stmt_;
        private byte memoizedIsInitialized = (byte)-1;
        private int memoizedSerializedSize = -1;
        private static final long serialVersionUID = 0L;

        private ModifyView(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.unknownFields = builder.getUnknownFields();
        }

        private ModifyView(boolean noInit) {
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static ModifyView getDefaultInstance() {
            return defaultInstance;
        }

        @Override
        public ModifyView getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private ModifyView(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this.initFields();
            int mutable_bitField0_ = 0;
            UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
            try {
                boolean done = false;
                block16: while (!done) {
                    int tag = input.readTag();
                    switch (tag) {
                        case 0: {
                            done = true;
                            continue block16;
                        }
                        default: {
                            if (this.parseUnknownField(input, unknownFields, extensionRegistry, tag)) continue block16;
                            done = true;
                            continue block16;
                        }
                        case 10: {
                            Collection.Builder subBuilder = null;
                            if ((this.bitField0_ & 1) == 1) {
                                subBuilder = this.collection_.toBuilder();
                            }
                            this.collection_ = input.readMessage(Collection.PARSER, extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom(this.collection_);
                                this.collection_ = subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 1;
                            continue block16;
                        }
                        case 18: {
                            ByteString bs = input.readBytes();
                            this.bitField0_ |= 2;
                            this.definer_ = bs;
                            continue block16;
                        }
                        case 24: {
                            int rawValue = input.readEnum();
                            Enum value = ViewAlgorithm.valueOf(rawValue);
                            if (value == null) {
                                unknownFields.mergeVarintField(3, rawValue);
                                continue block16;
                            }
                            this.bitField0_ |= 4;
                            this.algorithm_ = value;
                            continue block16;
                        }
                        case 32: {
                            int rawValue = input.readEnum();
                            Enum value = ViewSqlSecurity.valueOf(rawValue);
                            if (value == null) {
                                unknownFields.mergeVarintField(4, rawValue);
                                continue block16;
                            }
                            this.bitField0_ |= 8;
                            this.security_ = value;
                            continue block16;
                        }
                        case 40: {
                            int rawValue = input.readEnum();
                            Enum value = ViewCheckOption.valueOf(rawValue);
                            if (value == null) {
                                unknownFields.mergeVarintField(5, rawValue);
                                continue block16;
                            }
                            this.bitField0_ |= 0x10;
                            this.check_ = value;
                            continue block16;
                        }
                        case 50: {
                            ByteString bs = input.readBytes();
                            if ((mutable_bitField0_ & 0x20) != 32) {
                                this.column_ = new LazyStringArrayList();
                                mutable_bitField0_ |= 0x20;
                            }
                            this.column_.add(bs);
                            continue block16;
                        }
                        case 58: 
                    }
                    Find.Builder subBuilder = null;
                    if ((this.bitField0_ & 0x20) == 32) {
                        subBuilder = this.stmt_.toBuilder();
                    }
                    this.stmt_ = input.readMessage(Find.PARSER, extensionRegistry);
                    if (subBuilder != null) {
                        subBuilder.mergeFrom(this.stmt_);
                        this.stmt_ = subBuilder.buildPartial();
                    }
                    this.bitField0_ |= 0x20;
                }
            } catch (InvalidProtocolBufferException e) {
                throw e.setUnfinishedMessage(this);
            } catch (IOException e) {
                throw new InvalidProtocolBufferException(e.getMessage()).setUnfinishedMessage(this);
            } finally {
                if ((mutable_bitField0_ & 0x20) == 32) {
                    this.column_ = this.column_.getUnmodifiableView();
                }
                this.unknownFields = unknownFields.build();
                this.makeExtensionsImmutable();
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return internal_static_Mysqlx_Crud_ModifyView_descriptor;
        }

        @Override
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return internal_static_Mysqlx_Crud_ModifyView_fieldAccessorTable.ensureFieldAccessorsInitialized(ModifyView.class, Builder.class);
        }

        public Parser<ModifyView> getParserForType() {
            return PARSER;
        }

        @Override
        public boolean hasCollection() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override
        public Collection getCollection() {
            return this.collection_;
        }

        @Override
        public CollectionOrBuilder getCollectionOrBuilder() {
            return this.collection_;
        }

        @Override
        public boolean hasDefiner() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override
        public String getDefiner() {
            Object ref = this.definer_;
            if (ref instanceof String) {
                return (String)ref;
            }
            ByteString bs = (ByteString)ref;
            String s2 = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.definer_ = s2;
            }
            return s2;
        }

        @Override
        public ByteString getDefinerBytes() {
            Object ref = this.definer_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.definer_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        @Override
        public boolean hasAlgorithm() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override
        public ViewAlgorithm getAlgorithm() {
            return this.algorithm_;
        }

        @Override
        public boolean hasSecurity() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override
        public ViewSqlSecurity getSecurity() {
            return this.security_;
        }

        @Override
        public boolean hasCheck() {
            return (this.bitField0_ & 0x10) == 16;
        }

        @Override
        public ViewCheckOption getCheck() {
            return this.check_;
        }

        @Override
        public ProtocolStringList getColumnList() {
            return this.column_;
        }

        @Override
        public int getColumnCount() {
            return this.column_.size();
        }

        @Override
        public String getColumn(int index) {
            return (String)this.column_.get(index);
        }

        @Override
        public ByteString getColumnBytes(int index) {
            return this.column_.getByteString(index);
        }

        @Override
        public boolean hasStmt() {
            return (this.bitField0_ & 0x20) == 32;
        }

        @Override
        public Find getStmt() {
            return this.stmt_;
        }

        @Override
        public FindOrBuilder getStmtOrBuilder() {
            return this.stmt_;
        }

        private void initFields() {
            this.collection_ = Collection.getDefaultInstance();
            this.definer_ = "";
            this.algorithm_ = ViewAlgorithm.UNDEFINED;
            this.security_ = ViewSqlSecurity.INVOKER;
            this.check_ = ViewCheckOption.LOCAL;
            this.column_ = LazyStringArrayList.EMPTY;
            this.stmt_ = Find.getDefaultInstance();
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
            if (!this.hasCollection()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            if (!this.getCollection().isInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            if (this.hasStmt() && !this.getStmt().isInitialized()) {
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
                output.writeMessage(1, this.collection_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeBytes(2, this.getDefinerBytes());
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeEnum(3, this.algorithm_.getNumber());
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeEnum(4, this.security_.getNumber());
            }
            if ((this.bitField0_ & 0x10) == 16) {
                output.writeEnum(5, this.check_.getNumber());
            }
            for (int i = 0; i < this.column_.size(); ++i) {
                output.writeBytes(6, this.column_.getByteString(i));
            }
            if ((this.bitField0_ & 0x20) == 32) {
                output.writeMessage(7, this.stmt_);
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
                size += CodedOutputStream.computeMessageSize(1, this.collection_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size += CodedOutputStream.computeBytesSize(2, this.getDefinerBytes());
            }
            if ((this.bitField0_ & 4) == 4) {
                size += CodedOutputStream.computeEnumSize(3, this.algorithm_.getNumber());
            }
            if ((this.bitField0_ & 8) == 8) {
                size += CodedOutputStream.computeEnumSize(4, this.security_.getNumber());
            }
            if ((this.bitField0_ & 0x10) == 16) {
                size += CodedOutputStream.computeEnumSize(5, this.check_.getNumber());
            }
            int dataSize = 0;
            for (int i = 0; i < this.column_.size(); ++i) {
                dataSize += CodedOutputStream.computeBytesSizeNoTag(this.column_.getByteString(i));
            }
            size += dataSize;
            size += 1 * this.getColumnList().size();
            if ((this.bitField0_ & 0x20) == 32) {
                size += CodedOutputStream.computeMessageSize(7, this.stmt_);
            }
            this.memoizedSerializedSize = size += this.getUnknownFields().getSerializedSize();
            return size;
        }

        @Override
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static ModifyView parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static ModifyView parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static ModifyView parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static ModifyView parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static ModifyView parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static ModifyView parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static ModifyView parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static ModifyView parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static ModifyView parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static ModifyView parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override
        public Builder newBuilderForType() {
            return ModifyView.newBuilder();
        }

        public static Builder newBuilder(ModifyView prototype) {
            return ModifyView.newBuilder().mergeFrom(prototype);
        }

        @Override
        public Builder toBuilder() {
            return ModifyView.newBuilder(this);
        }

        @Override
        protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        static {
            PARSER = new AbstractParser<ModifyView>(){

                @Override
                public ModifyView parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                    return new ModifyView(input, extensionRegistry);
                }
            };
            defaultInstance = new ModifyView(true);
            defaultInstance.initFields();
        }

        public static final class Builder
        extends GeneratedMessage.Builder<Builder>
        implements ModifyViewOrBuilder {
            private int bitField0_;
            private Collection collection_ = Collection.getDefaultInstance();
            private SingleFieldBuilder<Collection, Collection.Builder, CollectionOrBuilder> collectionBuilder_;
            private Object definer_ = "";
            private ViewAlgorithm algorithm_ = ViewAlgorithm.UNDEFINED;
            private ViewSqlSecurity security_ = ViewSqlSecurity.INVOKER;
            private ViewCheckOption check_ = ViewCheckOption.LOCAL;
            private LazyStringList column_ = LazyStringArrayList.EMPTY;
            private Find stmt_ = Find.getDefaultInstance();
            private SingleFieldBuilder<Find, Find.Builder, FindOrBuilder> stmtBuilder_;

            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_Mysqlx_Crud_ModifyView_descriptor;
            }

            @Override
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_Mysqlx_Crud_ModifyView_fieldAccessorTable.ensureFieldAccessorsInitialized(ModifyView.class, Builder.class);
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
                    this.getCollectionFieldBuilder();
                    this.getStmtFieldBuilder();
                }
            }

            private static Builder create() {
                return new Builder();
            }

            @Override
            public Builder clear() {
                super.clear();
                if (this.collectionBuilder_ == null) {
                    this.collection_ = Collection.getDefaultInstance();
                } else {
                    this.collectionBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFFFE;
                this.definer_ = "";
                this.bitField0_ &= 0xFFFFFFFD;
                this.algorithm_ = ViewAlgorithm.UNDEFINED;
                this.bitField0_ &= 0xFFFFFFFB;
                this.security_ = ViewSqlSecurity.INVOKER;
                this.bitField0_ &= 0xFFFFFFF7;
                this.check_ = ViewCheckOption.LOCAL;
                this.bitField0_ &= 0xFFFFFFEF;
                this.column_ = LazyStringArrayList.EMPTY;
                this.bitField0_ &= 0xFFFFFFDF;
                if (this.stmtBuilder_ == null) {
                    this.stmt_ = Find.getDefaultInstance();
                } else {
                    this.stmtBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFFBF;
                return this;
            }

            @Override
            public Builder clone() {
                return Builder.create().mergeFrom(this.buildPartial());
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return internal_static_Mysqlx_Crud_ModifyView_descriptor;
            }

            @Override
            public ModifyView getDefaultInstanceForType() {
                return ModifyView.getDefaultInstance();
            }

            @Override
            public ModifyView build() {
                ModifyView result = this.buildPartial();
                if (!result.isInitialized()) {
                    throw Builder.newUninitializedMessageException(result);
                }
                return result;
            }

            @Override
            public ModifyView buildPartial() {
                ModifyView result = new ModifyView(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ |= 1;
                }
                if (this.collectionBuilder_ == null) {
                    result.collection_ = this.collection_;
                } else {
                    result.collection_ = this.collectionBuilder_.build();
                }
                if ((from_bitField0_ & 2) == 2) {
                    to_bitField0_ |= 2;
                }
                result.definer_ = this.definer_;
                if ((from_bitField0_ & 4) == 4) {
                    to_bitField0_ |= 4;
                }
                result.algorithm_ = this.algorithm_;
                if ((from_bitField0_ & 8) == 8) {
                    to_bitField0_ |= 8;
                }
                result.security_ = this.security_;
                if ((from_bitField0_ & 0x10) == 16) {
                    to_bitField0_ |= 0x10;
                }
                result.check_ = this.check_;
                if ((this.bitField0_ & 0x20) == 32) {
                    this.column_ = this.column_.getUnmodifiableView();
                    this.bitField0_ &= 0xFFFFFFDF;
                }
                result.column_ = this.column_;
                if ((from_bitField0_ & 0x40) == 64) {
                    to_bitField0_ |= 0x20;
                }
                if (this.stmtBuilder_ == null) {
                    result.stmt_ = this.stmt_;
                } else {
                    result.stmt_ = this.stmtBuilder_.build();
                }
                result.bitField0_ = to_bitField0_;
                this.onBuilt();
                return result;
            }

            @Override
            public Builder mergeFrom(Message other) {
                if (other instanceof ModifyView) {
                    return this.mergeFrom((ModifyView)other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(ModifyView other) {
                if (other == ModifyView.getDefaultInstance()) {
                    return this;
                }
                if (other.hasCollection()) {
                    this.mergeCollection(other.getCollection());
                }
                if (other.hasDefiner()) {
                    this.bitField0_ |= 2;
                    this.definer_ = other.definer_;
                    this.onChanged();
                }
                if (other.hasAlgorithm()) {
                    this.setAlgorithm(other.getAlgorithm());
                }
                if (other.hasSecurity()) {
                    this.setSecurity(other.getSecurity());
                }
                if (other.hasCheck()) {
                    this.setCheck(other.getCheck());
                }
                if (!other.column_.isEmpty()) {
                    if (this.column_.isEmpty()) {
                        this.column_ = other.column_;
                        this.bitField0_ &= 0xFFFFFFDF;
                    } else {
                        this.ensureColumnIsMutable();
                        this.column_.addAll(other.column_);
                    }
                    this.onChanged();
                }
                if (other.hasStmt()) {
                    this.mergeStmt(other.getStmt());
                }
                this.mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            @Override
            public final boolean isInitialized() {
                if (!this.hasCollection()) {
                    return false;
                }
                if (!this.getCollection().isInitialized()) {
                    return false;
                }
                return !this.hasStmt() || this.getStmt().isInitialized();
            }

            @Override
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                ModifyView parsedMessage = null;
                try {
                    parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
                } catch (InvalidProtocolBufferException e) {
                    parsedMessage = (ModifyView)e.getUnfinishedMessage();
                    throw e;
                } finally {
                    if (parsedMessage != null) {
                        this.mergeFrom(parsedMessage);
                    }
                }
                return this;
            }

            @Override
            public boolean hasCollection() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override
            public Collection getCollection() {
                if (this.collectionBuilder_ == null) {
                    return this.collection_;
                }
                return this.collectionBuilder_.getMessage();
            }

            public Builder setCollection(Collection value) {
                if (this.collectionBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.collection_ = value;
                    this.onChanged();
                } else {
                    this.collectionBuilder_.setMessage(value);
                }
                this.bitField0_ |= 1;
                return this;
            }

            public Builder setCollection(Collection.Builder builderForValue) {
                if (this.collectionBuilder_ == null) {
                    this.collection_ = builderForValue.build();
                    this.onChanged();
                } else {
                    this.collectionBuilder_.setMessage(builderForValue.build());
                }
                this.bitField0_ |= 1;
                return this;
            }

            public Builder mergeCollection(Collection value) {
                if (this.collectionBuilder_ == null) {
                    this.collection_ = (this.bitField0_ & 1) == 1 && this.collection_ != Collection.getDefaultInstance() ? Collection.newBuilder(this.collection_).mergeFrom(value).buildPartial() : value;
                    this.onChanged();
                } else {
                    this.collectionBuilder_.mergeFrom(value);
                }
                this.bitField0_ |= 1;
                return this;
            }

            public Builder clearCollection() {
                if (this.collectionBuilder_ == null) {
                    this.collection_ = Collection.getDefaultInstance();
                    this.onChanged();
                } else {
                    this.collectionBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFFFE;
                return this;
            }

            public Collection.Builder getCollectionBuilder() {
                this.bitField0_ |= 1;
                this.onChanged();
                return this.getCollectionFieldBuilder().getBuilder();
            }

            @Override
            public CollectionOrBuilder getCollectionOrBuilder() {
                if (this.collectionBuilder_ != null) {
                    return this.collectionBuilder_.getMessageOrBuilder();
                }
                return this.collection_;
            }

            private SingleFieldBuilder<Collection, Collection.Builder, CollectionOrBuilder> getCollectionFieldBuilder() {
                if (this.collectionBuilder_ == null) {
                    this.collectionBuilder_ = new SingleFieldBuilder(this.getCollection(), this.getParentForChildren(), this.isClean());
                    this.collection_ = null;
                }
                return this.collectionBuilder_;
            }

            @Override
            public boolean hasDefiner() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override
            public String getDefiner() {
                Object ref = this.definer_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString)ref;
                    String s2 = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.definer_ = s2;
                    }
                    return s2;
                }
                return (String)ref;
            }

            @Override
            public ByteString getDefinerBytes() {
                Object ref = this.definer_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String)ref);
                    this.definer_ = b;
                    return b;
                }
                return (ByteString)ref;
            }

            public Builder setDefiner(String value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 2;
                this.definer_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearDefiner() {
                this.bitField0_ &= 0xFFFFFFFD;
                this.definer_ = ModifyView.getDefaultInstance().getDefiner();
                this.onChanged();
                return this;
            }

            public Builder setDefinerBytes(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 2;
                this.definer_ = value;
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasAlgorithm() {
                return (this.bitField0_ & 4) == 4;
            }

            @Override
            public ViewAlgorithm getAlgorithm() {
                return this.algorithm_;
            }

            public Builder setAlgorithm(ViewAlgorithm value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 4;
                this.algorithm_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearAlgorithm() {
                this.bitField0_ &= 0xFFFFFFFB;
                this.algorithm_ = ViewAlgorithm.UNDEFINED;
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasSecurity() {
                return (this.bitField0_ & 8) == 8;
            }

            @Override
            public ViewSqlSecurity getSecurity() {
                return this.security_;
            }

            public Builder setSecurity(ViewSqlSecurity value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 8;
                this.security_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearSecurity() {
                this.bitField0_ &= 0xFFFFFFF7;
                this.security_ = ViewSqlSecurity.INVOKER;
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasCheck() {
                return (this.bitField0_ & 0x10) == 16;
            }

            @Override
            public ViewCheckOption getCheck() {
                return this.check_;
            }

            public Builder setCheck(ViewCheckOption value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 0x10;
                this.check_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearCheck() {
                this.bitField0_ &= 0xFFFFFFEF;
                this.check_ = ViewCheckOption.LOCAL;
                this.onChanged();
                return this;
            }

            private void ensureColumnIsMutable() {
                if ((this.bitField0_ & 0x20) != 32) {
                    this.column_ = new LazyStringArrayList(this.column_);
                    this.bitField0_ |= 0x20;
                }
            }

            @Override
            public ProtocolStringList getColumnList() {
                return this.column_.getUnmodifiableView();
            }

            @Override
            public int getColumnCount() {
                return this.column_.size();
            }

            @Override
            public String getColumn(int index) {
                return (String)this.column_.get(index);
            }

            @Override
            public ByteString getColumnBytes(int index) {
                return this.column_.getByteString(index);
            }

            public Builder setColumn(int index, String value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ensureColumnIsMutable();
                this.column_.set(index, value);
                this.onChanged();
                return this;
            }

            public Builder addColumn(String value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ensureColumnIsMutable();
                this.column_.add(value);
                this.onChanged();
                return this;
            }

            public Builder addAllColumn(Iterable<String> values2) {
                this.ensureColumnIsMutable();
                AbstractMessageLite.Builder.addAll(values2, this.column_);
                this.onChanged();
                return this;
            }

            public Builder clearColumn() {
                this.column_ = LazyStringArrayList.EMPTY;
                this.bitField0_ &= 0xFFFFFFDF;
                this.onChanged();
                return this;
            }

            public Builder addColumnBytes(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ensureColumnIsMutable();
                this.column_.add(value);
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasStmt() {
                return (this.bitField0_ & 0x40) == 64;
            }

            @Override
            public Find getStmt() {
                if (this.stmtBuilder_ == null) {
                    return this.stmt_;
                }
                return this.stmtBuilder_.getMessage();
            }

            public Builder setStmt(Find value) {
                if (this.stmtBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.stmt_ = value;
                    this.onChanged();
                } else {
                    this.stmtBuilder_.setMessage(value);
                }
                this.bitField0_ |= 0x40;
                return this;
            }

            public Builder setStmt(Find.Builder builderForValue) {
                if (this.stmtBuilder_ == null) {
                    this.stmt_ = builderForValue.build();
                    this.onChanged();
                } else {
                    this.stmtBuilder_.setMessage(builderForValue.build());
                }
                this.bitField0_ |= 0x40;
                return this;
            }

            public Builder mergeStmt(Find value) {
                if (this.stmtBuilder_ == null) {
                    this.stmt_ = (this.bitField0_ & 0x40) == 64 && this.stmt_ != Find.getDefaultInstance() ? Find.newBuilder(this.stmt_).mergeFrom(value).buildPartial() : value;
                    this.onChanged();
                } else {
                    this.stmtBuilder_.mergeFrom(value);
                }
                this.bitField0_ |= 0x40;
                return this;
            }

            public Builder clearStmt() {
                if (this.stmtBuilder_ == null) {
                    this.stmt_ = Find.getDefaultInstance();
                    this.onChanged();
                } else {
                    this.stmtBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFFBF;
                return this;
            }

            public Find.Builder getStmtBuilder() {
                this.bitField0_ |= 0x40;
                this.onChanged();
                return this.getStmtFieldBuilder().getBuilder();
            }

            @Override
            public FindOrBuilder getStmtOrBuilder() {
                if (this.stmtBuilder_ != null) {
                    return this.stmtBuilder_.getMessageOrBuilder();
                }
                return this.stmt_;
            }

            private SingleFieldBuilder<Find, Find.Builder, FindOrBuilder> getStmtFieldBuilder() {
                if (this.stmtBuilder_ == null) {
                    this.stmtBuilder_ = new SingleFieldBuilder(this.getStmt(), this.getParentForChildren(), this.isClean());
                    this.stmt_ = null;
                }
                return this.stmtBuilder_;
            }
        }
    }

    public static interface ModifyViewOrBuilder
    extends MessageOrBuilder {
        public boolean hasCollection();

        public Collection getCollection();

        public CollectionOrBuilder getCollectionOrBuilder();

        public boolean hasDefiner();

        public String getDefiner();

        public ByteString getDefinerBytes();

        public boolean hasAlgorithm();

        public ViewAlgorithm getAlgorithm();

        public boolean hasSecurity();

        public ViewSqlSecurity getSecurity();

        public boolean hasCheck();

        public ViewCheckOption getCheck();

        public ProtocolStringList getColumnList();

        public int getColumnCount();

        public String getColumn(int var1);

        public ByteString getColumnBytes(int var1);

        public boolean hasStmt();

        public Find getStmt();

        public FindOrBuilder getStmtOrBuilder();
    }

    public static final class CreateView
    extends GeneratedMessage
    implements CreateViewOrBuilder {
        private static final CreateView defaultInstance;
        private final UnknownFieldSet unknownFields;
        public static Parser<CreateView> PARSER;
        private int bitField0_;
        public static final int COLLECTION_FIELD_NUMBER = 1;
        private Collection collection_;
        public static final int DEFINER_FIELD_NUMBER = 2;
        private Object definer_;
        public static final int ALGORITHM_FIELD_NUMBER = 3;
        private ViewAlgorithm algorithm_;
        public static final int SECURITY_FIELD_NUMBER = 4;
        private ViewSqlSecurity security_;
        public static final int CHECK_FIELD_NUMBER = 5;
        private ViewCheckOption check_;
        public static final int COLUMN_FIELD_NUMBER = 6;
        private LazyStringList column_;
        public static final int STMT_FIELD_NUMBER = 7;
        private Find stmt_;
        public static final int REPLACE_EXISTING_FIELD_NUMBER = 8;
        private boolean replaceExisting_;
        private byte memoizedIsInitialized = (byte)-1;
        private int memoizedSerializedSize = -1;
        private static final long serialVersionUID = 0L;

        private CreateView(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.unknownFields = builder.getUnknownFields();
        }

        private CreateView(boolean noInit) {
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static CreateView getDefaultInstance() {
            return defaultInstance;
        }

        @Override
        public CreateView getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private CreateView(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this.initFields();
            int mutable_bitField0_ = 0;
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
                        case 10: {
                            Collection.Builder subBuilder = null;
                            if ((this.bitField0_ & 1) == 1) {
                                subBuilder = this.collection_.toBuilder();
                            }
                            this.collection_ = input.readMessage(Collection.PARSER, extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom(this.collection_);
                                this.collection_ = subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 1;
                            continue block17;
                        }
                        case 18: {
                            ByteString bs = input.readBytes();
                            this.bitField0_ |= 2;
                            this.definer_ = bs;
                            continue block17;
                        }
                        case 24: {
                            int rawValue = input.readEnum();
                            Enum value = ViewAlgorithm.valueOf(rawValue);
                            if (value == null) {
                                unknownFields.mergeVarintField(3, rawValue);
                                continue block17;
                            }
                            this.bitField0_ |= 4;
                            this.algorithm_ = value;
                            continue block17;
                        }
                        case 32: {
                            int rawValue = input.readEnum();
                            Enum value = ViewSqlSecurity.valueOf(rawValue);
                            if (value == null) {
                                unknownFields.mergeVarintField(4, rawValue);
                                continue block17;
                            }
                            this.bitField0_ |= 8;
                            this.security_ = value;
                            continue block17;
                        }
                        case 40: {
                            int rawValue = input.readEnum();
                            Enum value = ViewCheckOption.valueOf(rawValue);
                            if (value == null) {
                                unknownFields.mergeVarintField(5, rawValue);
                                continue block17;
                            }
                            this.bitField0_ |= 0x10;
                            this.check_ = value;
                            continue block17;
                        }
                        case 50: {
                            ByteString bs = input.readBytes();
                            if ((mutable_bitField0_ & 0x20) != 32) {
                                this.column_ = new LazyStringArrayList();
                                mutable_bitField0_ |= 0x20;
                            }
                            this.column_.add(bs);
                            continue block17;
                        }
                        case 58: {
                            Find.Builder subBuilder = null;
                            if ((this.bitField0_ & 0x20) == 32) {
                                subBuilder = this.stmt_.toBuilder();
                            }
                            this.stmt_ = input.readMessage(Find.PARSER, extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom(this.stmt_);
                                this.stmt_ = subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 0x20;
                            continue block17;
                        }
                        case 64: 
                    }
                    this.bitField0_ |= 0x40;
                    this.replaceExisting_ = input.readBool();
                }
            } catch (InvalidProtocolBufferException e) {
                throw e.setUnfinishedMessage(this);
            } catch (IOException e) {
                throw new InvalidProtocolBufferException(e.getMessage()).setUnfinishedMessage(this);
            } finally {
                if ((mutable_bitField0_ & 0x20) == 32) {
                    this.column_ = this.column_.getUnmodifiableView();
                }
                this.unknownFields = unknownFields.build();
                this.makeExtensionsImmutable();
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return internal_static_Mysqlx_Crud_CreateView_descriptor;
        }

        @Override
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return internal_static_Mysqlx_Crud_CreateView_fieldAccessorTable.ensureFieldAccessorsInitialized(CreateView.class, Builder.class);
        }

        public Parser<CreateView> getParserForType() {
            return PARSER;
        }

        @Override
        public boolean hasCollection() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override
        public Collection getCollection() {
            return this.collection_;
        }

        @Override
        public CollectionOrBuilder getCollectionOrBuilder() {
            return this.collection_;
        }

        @Override
        public boolean hasDefiner() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override
        public String getDefiner() {
            Object ref = this.definer_;
            if (ref instanceof String) {
                return (String)ref;
            }
            ByteString bs = (ByteString)ref;
            String s2 = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.definer_ = s2;
            }
            return s2;
        }

        @Override
        public ByteString getDefinerBytes() {
            Object ref = this.definer_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.definer_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        @Override
        public boolean hasAlgorithm() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override
        public ViewAlgorithm getAlgorithm() {
            return this.algorithm_;
        }

        @Override
        public boolean hasSecurity() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override
        public ViewSqlSecurity getSecurity() {
            return this.security_;
        }

        @Override
        public boolean hasCheck() {
            return (this.bitField0_ & 0x10) == 16;
        }

        @Override
        public ViewCheckOption getCheck() {
            return this.check_;
        }

        @Override
        public ProtocolStringList getColumnList() {
            return this.column_;
        }

        @Override
        public int getColumnCount() {
            return this.column_.size();
        }

        @Override
        public String getColumn(int index) {
            return (String)this.column_.get(index);
        }

        @Override
        public ByteString getColumnBytes(int index) {
            return this.column_.getByteString(index);
        }

        @Override
        public boolean hasStmt() {
            return (this.bitField0_ & 0x20) == 32;
        }

        @Override
        public Find getStmt() {
            return this.stmt_;
        }

        @Override
        public FindOrBuilder getStmtOrBuilder() {
            return this.stmt_;
        }

        @Override
        public boolean hasReplaceExisting() {
            return (this.bitField0_ & 0x40) == 64;
        }

        @Override
        public boolean getReplaceExisting() {
            return this.replaceExisting_;
        }

        private void initFields() {
            this.collection_ = Collection.getDefaultInstance();
            this.definer_ = "";
            this.algorithm_ = ViewAlgorithm.UNDEFINED;
            this.security_ = ViewSqlSecurity.DEFINER;
            this.check_ = ViewCheckOption.LOCAL;
            this.column_ = LazyStringArrayList.EMPTY;
            this.stmt_ = Find.getDefaultInstance();
            this.replaceExisting_ = false;
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
            if (!this.hasCollection()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            if (!this.hasStmt()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            if (!this.getCollection().isInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            if (!this.getStmt().isInitialized()) {
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
                output.writeMessage(1, this.collection_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeBytes(2, this.getDefinerBytes());
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeEnum(3, this.algorithm_.getNumber());
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeEnum(4, this.security_.getNumber());
            }
            if ((this.bitField0_ & 0x10) == 16) {
                output.writeEnum(5, this.check_.getNumber());
            }
            for (int i = 0; i < this.column_.size(); ++i) {
                output.writeBytes(6, this.column_.getByteString(i));
            }
            if ((this.bitField0_ & 0x20) == 32) {
                output.writeMessage(7, this.stmt_);
            }
            if ((this.bitField0_ & 0x40) == 64) {
                output.writeBool(8, this.replaceExisting_);
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
                size += CodedOutputStream.computeMessageSize(1, this.collection_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size += CodedOutputStream.computeBytesSize(2, this.getDefinerBytes());
            }
            if ((this.bitField0_ & 4) == 4) {
                size += CodedOutputStream.computeEnumSize(3, this.algorithm_.getNumber());
            }
            if ((this.bitField0_ & 8) == 8) {
                size += CodedOutputStream.computeEnumSize(4, this.security_.getNumber());
            }
            if ((this.bitField0_ & 0x10) == 16) {
                size += CodedOutputStream.computeEnumSize(5, this.check_.getNumber());
            }
            int dataSize = 0;
            for (int i = 0; i < this.column_.size(); ++i) {
                dataSize += CodedOutputStream.computeBytesSizeNoTag(this.column_.getByteString(i));
            }
            size += dataSize;
            size += 1 * this.getColumnList().size();
            if ((this.bitField0_ & 0x20) == 32) {
                size += CodedOutputStream.computeMessageSize(7, this.stmt_);
            }
            if ((this.bitField0_ & 0x40) == 64) {
                size += CodedOutputStream.computeBoolSize(8, this.replaceExisting_);
            }
            this.memoizedSerializedSize = size += this.getUnknownFields().getSerializedSize();
            return size;
        }

        @Override
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static CreateView parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static CreateView parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static CreateView parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static CreateView parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static CreateView parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static CreateView parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static CreateView parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static CreateView parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static CreateView parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static CreateView parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override
        public Builder newBuilderForType() {
            return CreateView.newBuilder();
        }

        public static Builder newBuilder(CreateView prototype) {
            return CreateView.newBuilder().mergeFrom(prototype);
        }

        @Override
        public Builder toBuilder() {
            return CreateView.newBuilder(this);
        }

        @Override
        protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        static {
            PARSER = new AbstractParser<CreateView>(){

                @Override
                public CreateView parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                    return new CreateView(input, extensionRegistry);
                }
            };
            defaultInstance = new CreateView(true);
            defaultInstance.initFields();
        }

        public static final class Builder
        extends GeneratedMessage.Builder<Builder>
        implements CreateViewOrBuilder {
            private int bitField0_;
            private Collection collection_ = Collection.getDefaultInstance();
            private SingleFieldBuilder<Collection, Collection.Builder, CollectionOrBuilder> collectionBuilder_;
            private Object definer_ = "";
            private ViewAlgorithm algorithm_ = ViewAlgorithm.UNDEFINED;
            private ViewSqlSecurity security_ = ViewSqlSecurity.DEFINER;
            private ViewCheckOption check_ = ViewCheckOption.LOCAL;
            private LazyStringList column_ = LazyStringArrayList.EMPTY;
            private Find stmt_ = Find.getDefaultInstance();
            private SingleFieldBuilder<Find, Find.Builder, FindOrBuilder> stmtBuilder_;
            private boolean replaceExisting_;

            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_Mysqlx_Crud_CreateView_descriptor;
            }

            @Override
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_Mysqlx_Crud_CreateView_fieldAccessorTable.ensureFieldAccessorsInitialized(CreateView.class, Builder.class);
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
                    this.getCollectionFieldBuilder();
                    this.getStmtFieldBuilder();
                }
            }

            private static Builder create() {
                return new Builder();
            }

            @Override
            public Builder clear() {
                super.clear();
                if (this.collectionBuilder_ == null) {
                    this.collection_ = Collection.getDefaultInstance();
                } else {
                    this.collectionBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFFFE;
                this.definer_ = "";
                this.bitField0_ &= 0xFFFFFFFD;
                this.algorithm_ = ViewAlgorithm.UNDEFINED;
                this.bitField0_ &= 0xFFFFFFFB;
                this.security_ = ViewSqlSecurity.DEFINER;
                this.bitField0_ &= 0xFFFFFFF7;
                this.check_ = ViewCheckOption.LOCAL;
                this.bitField0_ &= 0xFFFFFFEF;
                this.column_ = LazyStringArrayList.EMPTY;
                this.bitField0_ &= 0xFFFFFFDF;
                if (this.stmtBuilder_ == null) {
                    this.stmt_ = Find.getDefaultInstance();
                } else {
                    this.stmtBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFFBF;
                this.replaceExisting_ = false;
                this.bitField0_ &= 0xFFFFFF7F;
                return this;
            }

            @Override
            public Builder clone() {
                return Builder.create().mergeFrom(this.buildPartial());
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return internal_static_Mysqlx_Crud_CreateView_descriptor;
            }

            @Override
            public CreateView getDefaultInstanceForType() {
                return CreateView.getDefaultInstance();
            }

            @Override
            public CreateView build() {
                CreateView result = this.buildPartial();
                if (!result.isInitialized()) {
                    throw Builder.newUninitializedMessageException(result);
                }
                return result;
            }

            @Override
            public CreateView buildPartial() {
                CreateView result = new CreateView(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ |= 1;
                }
                if (this.collectionBuilder_ == null) {
                    result.collection_ = this.collection_;
                } else {
                    result.collection_ = this.collectionBuilder_.build();
                }
                if ((from_bitField0_ & 2) == 2) {
                    to_bitField0_ |= 2;
                }
                result.definer_ = this.definer_;
                if ((from_bitField0_ & 4) == 4) {
                    to_bitField0_ |= 4;
                }
                result.algorithm_ = this.algorithm_;
                if ((from_bitField0_ & 8) == 8) {
                    to_bitField0_ |= 8;
                }
                result.security_ = this.security_;
                if ((from_bitField0_ & 0x10) == 16) {
                    to_bitField0_ |= 0x10;
                }
                result.check_ = this.check_;
                if ((this.bitField0_ & 0x20) == 32) {
                    this.column_ = this.column_.getUnmodifiableView();
                    this.bitField0_ &= 0xFFFFFFDF;
                }
                result.column_ = this.column_;
                if ((from_bitField0_ & 0x40) == 64) {
                    to_bitField0_ |= 0x20;
                }
                if (this.stmtBuilder_ == null) {
                    result.stmt_ = this.stmt_;
                } else {
                    result.stmt_ = this.stmtBuilder_.build();
                }
                if ((from_bitField0_ & 0x80) == 128) {
                    to_bitField0_ |= 0x40;
                }
                result.replaceExisting_ = this.replaceExisting_;
                result.bitField0_ = to_bitField0_;
                this.onBuilt();
                return result;
            }

            @Override
            public Builder mergeFrom(Message other) {
                if (other instanceof CreateView) {
                    return this.mergeFrom((CreateView)other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(CreateView other) {
                if (other == CreateView.getDefaultInstance()) {
                    return this;
                }
                if (other.hasCollection()) {
                    this.mergeCollection(other.getCollection());
                }
                if (other.hasDefiner()) {
                    this.bitField0_ |= 2;
                    this.definer_ = other.definer_;
                    this.onChanged();
                }
                if (other.hasAlgorithm()) {
                    this.setAlgorithm(other.getAlgorithm());
                }
                if (other.hasSecurity()) {
                    this.setSecurity(other.getSecurity());
                }
                if (other.hasCheck()) {
                    this.setCheck(other.getCheck());
                }
                if (!other.column_.isEmpty()) {
                    if (this.column_.isEmpty()) {
                        this.column_ = other.column_;
                        this.bitField0_ &= 0xFFFFFFDF;
                    } else {
                        this.ensureColumnIsMutable();
                        this.column_.addAll(other.column_);
                    }
                    this.onChanged();
                }
                if (other.hasStmt()) {
                    this.mergeStmt(other.getStmt());
                }
                if (other.hasReplaceExisting()) {
                    this.setReplaceExisting(other.getReplaceExisting());
                }
                this.mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            @Override
            public final boolean isInitialized() {
                if (!this.hasCollection()) {
                    return false;
                }
                if (!this.hasStmt()) {
                    return false;
                }
                if (!this.getCollection().isInitialized()) {
                    return false;
                }
                return this.getStmt().isInitialized();
            }

            @Override
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                CreateView parsedMessage = null;
                try {
                    parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
                } catch (InvalidProtocolBufferException e) {
                    parsedMessage = (CreateView)e.getUnfinishedMessage();
                    throw e;
                } finally {
                    if (parsedMessage != null) {
                        this.mergeFrom(parsedMessage);
                    }
                }
                return this;
            }

            @Override
            public boolean hasCollection() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override
            public Collection getCollection() {
                if (this.collectionBuilder_ == null) {
                    return this.collection_;
                }
                return this.collectionBuilder_.getMessage();
            }

            public Builder setCollection(Collection value) {
                if (this.collectionBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.collection_ = value;
                    this.onChanged();
                } else {
                    this.collectionBuilder_.setMessage(value);
                }
                this.bitField0_ |= 1;
                return this;
            }

            public Builder setCollection(Collection.Builder builderForValue) {
                if (this.collectionBuilder_ == null) {
                    this.collection_ = builderForValue.build();
                    this.onChanged();
                } else {
                    this.collectionBuilder_.setMessage(builderForValue.build());
                }
                this.bitField0_ |= 1;
                return this;
            }

            public Builder mergeCollection(Collection value) {
                if (this.collectionBuilder_ == null) {
                    this.collection_ = (this.bitField0_ & 1) == 1 && this.collection_ != Collection.getDefaultInstance() ? Collection.newBuilder(this.collection_).mergeFrom(value).buildPartial() : value;
                    this.onChanged();
                } else {
                    this.collectionBuilder_.mergeFrom(value);
                }
                this.bitField0_ |= 1;
                return this;
            }

            public Builder clearCollection() {
                if (this.collectionBuilder_ == null) {
                    this.collection_ = Collection.getDefaultInstance();
                    this.onChanged();
                } else {
                    this.collectionBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFFFE;
                return this;
            }

            public Collection.Builder getCollectionBuilder() {
                this.bitField0_ |= 1;
                this.onChanged();
                return this.getCollectionFieldBuilder().getBuilder();
            }

            @Override
            public CollectionOrBuilder getCollectionOrBuilder() {
                if (this.collectionBuilder_ != null) {
                    return this.collectionBuilder_.getMessageOrBuilder();
                }
                return this.collection_;
            }

            private SingleFieldBuilder<Collection, Collection.Builder, CollectionOrBuilder> getCollectionFieldBuilder() {
                if (this.collectionBuilder_ == null) {
                    this.collectionBuilder_ = new SingleFieldBuilder(this.getCollection(), this.getParentForChildren(), this.isClean());
                    this.collection_ = null;
                }
                return this.collectionBuilder_;
            }

            @Override
            public boolean hasDefiner() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override
            public String getDefiner() {
                Object ref = this.definer_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString)ref;
                    String s2 = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.definer_ = s2;
                    }
                    return s2;
                }
                return (String)ref;
            }

            @Override
            public ByteString getDefinerBytes() {
                Object ref = this.definer_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String)ref);
                    this.definer_ = b;
                    return b;
                }
                return (ByteString)ref;
            }

            public Builder setDefiner(String value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 2;
                this.definer_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearDefiner() {
                this.bitField0_ &= 0xFFFFFFFD;
                this.definer_ = CreateView.getDefaultInstance().getDefiner();
                this.onChanged();
                return this;
            }

            public Builder setDefinerBytes(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 2;
                this.definer_ = value;
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasAlgorithm() {
                return (this.bitField0_ & 4) == 4;
            }

            @Override
            public ViewAlgorithm getAlgorithm() {
                return this.algorithm_;
            }

            public Builder setAlgorithm(ViewAlgorithm value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 4;
                this.algorithm_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearAlgorithm() {
                this.bitField0_ &= 0xFFFFFFFB;
                this.algorithm_ = ViewAlgorithm.UNDEFINED;
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasSecurity() {
                return (this.bitField0_ & 8) == 8;
            }

            @Override
            public ViewSqlSecurity getSecurity() {
                return this.security_;
            }

            public Builder setSecurity(ViewSqlSecurity value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 8;
                this.security_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearSecurity() {
                this.bitField0_ &= 0xFFFFFFF7;
                this.security_ = ViewSqlSecurity.DEFINER;
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasCheck() {
                return (this.bitField0_ & 0x10) == 16;
            }

            @Override
            public ViewCheckOption getCheck() {
                return this.check_;
            }

            public Builder setCheck(ViewCheckOption value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 0x10;
                this.check_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearCheck() {
                this.bitField0_ &= 0xFFFFFFEF;
                this.check_ = ViewCheckOption.LOCAL;
                this.onChanged();
                return this;
            }

            private void ensureColumnIsMutable() {
                if ((this.bitField0_ & 0x20) != 32) {
                    this.column_ = new LazyStringArrayList(this.column_);
                    this.bitField0_ |= 0x20;
                }
            }

            @Override
            public ProtocolStringList getColumnList() {
                return this.column_.getUnmodifiableView();
            }

            @Override
            public int getColumnCount() {
                return this.column_.size();
            }

            @Override
            public String getColumn(int index) {
                return (String)this.column_.get(index);
            }

            @Override
            public ByteString getColumnBytes(int index) {
                return this.column_.getByteString(index);
            }

            public Builder setColumn(int index, String value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ensureColumnIsMutable();
                this.column_.set(index, value);
                this.onChanged();
                return this;
            }

            public Builder addColumn(String value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ensureColumnIsMutable();
                this.column_.add(value);
                this.onChanged();
                return this;
            }

            public Builder addAllColumn(Iterable<String> values2) {
                this.ensureColumnIsMutable();
                AbstractMessageLite.Builder.addAll(values2, this.column_);
                this.onChanged();
                return this;
            }

            public Builder clearColumn() {
                this.column_ = LazyStringArrayList.EMPTY;
                this.bitField0_ &= 0xFFFFFFDF;
                this.onChanged();
                return this;
            }

            public Builder addColumnBytes(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ensureColumnIsMutable();
                this.column_.add(value);
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasStmt() {
                return (this.bitField0_ & 0x40) == 64;
            }

            @Override
            public Find getStmt() {
                if (this.stmtBuilder_ == null) {
                    return this.stmt_;
                }
                return this.stmtBuilder_.getMessage();
            }

            public Builder setStmt(Find value) {
                if (this.stmtBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.stmt_ = value;
                    this.onChanged();
                } else {
                    this.stmtBuilder_.setMessage(value);
                }
                this.bitField0_ |= 0x40;
                return this;
            }

            public Builder setStmt(Find.Builder builderForValue) {
                if (this.stmtBuilder_ == null) {
                    this.stmt_ = builderForValue.build();
                    this.onChanged();
                } else {
                    this.stmtBuilder_.setMessage(builderForValue.build());
                }
                this.bitField0_ |= 0x40;
                return this;
            }

            public Builder mergeStmt(Find value) {
                if (this.stmtBuilder_ == null) {
                    this.stmt_ = (this.bitField0_ & 0x40) == 64 && this.stmt_ != Find.getDefaultInstance() ? Find.newBuilder(this.stmt_).mergeFrom(value).buildPartial() : value;
                    this.onChanged();
                } else {
                    this.stmtBuilder_.mergeFrom(value);
                }
                this.bitField0_ |= 0x40;
                return this;
            }

            public Builder clearStmt() {
                if (this.stmtBuilder_ == null) {
                    this.stmt_ = Find.getDefaultInstance();
                    this.onChanged();
                } else {
                    this.stmtBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFFBF;
                return this;
            }

            public Find.Builder getStmtBuilder() {
                this.bitField0_ |= 0x40;
                this.onChanged();
                return this.getStmtFieldBuilder().getBuilder();
            }

            @Override
            public FindOrBuilder getStmtOrBuilder() {
                if (this.stmtBuilder_ != null) {
                    return this.stmtBuilder_.getMessageOrBuilder();
                }
                return this.stmt_;
            }

            private SingleFieldBuilder<Find, Find.Builder, FindOrBuilder> getStmtFieldBuilder() {
                if (this.stmtBuilder_ == null) {
                    this.stmtBuilder_ = new SingleFieldBuilder(this.getStmt(), this.getParentForChildren(), this.isClean());
                    this.stmt_ = null;
                }
                return this.stmtBuilder_;
            }

            @Override
            public boolean hasReplaceExisting() {
                return (this.bitField0_ & 0x80) == 128;
            }

            @Override
            public boolean getReplaceExisting() {
                return this.replaceExisting_;
            }

            public Builder setReplaceExisting(boolean value) {
                this.bitField0_ |= 0x80;
                this.replaceExisting_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearReplaceExisting() {
                this.bitField0_ &= 0xFFFFFF7F;
                this.replaceExisting_ = false;
                this.onChanged();
                return this;
            }
        }
    }

    public static interface CreateViewOrBuilder
    extends MessageOrBuilder {
        public boolean hasCollection();

        public Collection getCollection();

        public CollectionOrBuilder getCollectionOrBuilder();

        public boolean hasDefiner();

        public String getDefiner();

        public ByteString getDefinerBytes();

        public boolean hasAlgorithm();

        public ViewAlgorithm getAlgorithm();

        public boolean hasSecurity();

        public ViewSqlSecurity getSecurity();

        public boolean hasCheck();

        public ViewCheckOption getCheck();

        public ProtocolStringList getColumnList();

        public int getColumnCount();

        public String getColumn(int var1);

        public ByteString getColumnBytes(int var1);

        public boolean hasStmt();

        public Find getStmt();

        public FindOrBuilder getStmtOrBuilder();

        public boolean hasReplaceExisting();

        public boolean getReplaceExisting();
    }

    public static final class Delete
    extends GeneratedMessage
    implements DeleteOrBuilder {
        private static final Delete defaultInstance;
        private final UnknownFieldSet unknownFields;
        public static Parser<Delete> PARSER;
        private int bitField0_;
        public static final int COLLECTION_FIELD_NUMBER = 1;
        private Collection collection_;
        public static final int DATA_MODEL_FIELD_NUMBER = 2;
        private DataModel dataModel_;
        public static final int CRITERIA_FIELD_NUMBER = 3;
        private MysqlxExpr.Expr criteria_;
        public static final int ARGS_FIELD_NUMBER = 6;
        private List<MysqlxDatatypes.Scalar> args_;
        public static final int LIMIT_FIELD_NUMBER = 4;
        private Limit limit_;
        public static final int ORDER_FIELD_NUMBER = 5;
        private List<Order> order_;
        private byte memoizedIsInitialized = (byte)-1;
        private int memoizedSerializedSize = -1;
        private static final long serialVersionUID = 0L;

        private Delete(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.unknownFields = builder.getUnknownFields();
        }

        private Delete(boolean noInit) {
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static Delete getDefaultInstance() {
            return defaultInstance;
        }

        @Override
        public Delete getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private Delete(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this.initFields();
            int mutable_bitField0_ = 0;
            UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
            try {
                boolean done = false;
                block15: while (!done) {
                    int tag = input.readTag();
                    switch (tag) {
                        case 0: {
                            done = true;
                            continue block15;
                        }
                        default: {
                            if (this.parseUnknownField(input, unknownFields, extensionRegistry, tag)) continue block15;
                            done = true;
                            continue block15;
                        }
                        case 10: {
                            Collection.Builder subBuilder = null;
                            if ((this.bitField0_ & 1) == 1) {
                                subBuilder = this.collection_.toBuilder();
                            }
                            this.collection_ = input.readMessage(Collection.PARSER, extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom(this.collection_);
                                this.collection_ = subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 1;
                            continue block15;
                        }
                        case 16: {
                            int rawValue = input.readEnum();
                            DataModel value = DataModel.valueOf(rawValue);
                            if (value == null) {
                                unknownFields.mergeVarintField(2, rawValue);
                                continue block15;
                            }
                            this.bitField0_ |= 2;
                            this.dataModel_ = value;
                            continue block15;
                        }
                        case 26: {
                            MysqlxExpr.Expr.Builder subBuilder = null;
                            if ((this.bitField0_ & 4) == 4) {
                                subBuilder = this.criteria_.toBuilder();
                            }
                            this.criteria_ = input.readMessage(MysqlxExpr.Expr.PARSER, extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom(this.criteria_);
                                this.criteria_ = subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 4;
                            continue block15;
                        }
                        case 34: {
                            Limit.Builder subBuilder = null;
                            if ((this.bitField0_ & 8) == 8) {
                                subBuilder = this.limit_.toBuilder();
                            }
                            this.limit_ = input.readMessage(Limit.PARSER, extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom(this.limit_);
                                this.limit_ = subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 8;
                            continue block15;
                        }
                        case 42: {
                            if ((mutable_bitField0_ & 0x20) != 32) {
                                this.order_ = new ArrayList<Order>();
                                mutable_bitField0_ |= 0x20;
                            }
                            this.order_.add(input.readMessage(Order.PARSER, extensionRegistry));
                            continue block15;
                        }
                        case 50: 
                    }
                    if ((mutable_bitField0_ & 8) != 8) {
                        this.args_ = new ArrayList<MysqlxDatatypes.Scalar>();
                        mutable_bitField0_ |= 8;
                    }
                    this.args_.add(input.readMessage(MysqlxDatatypes.Scalar.PARSER, extensionRegistry));
                }
            } catch (InvalidProtocolBufferException e) {
                throw e.setUnfinishedMessage(this);
            } catch (IOException e) {
                throw new InvalidProtocolBufferException(e.getMessage()).setUnfinishedMessage(this);
            } finally {
                if ((mutable_bitField0_ & 0x20) == 32) {
                    this.order_ = Collections.unmodifiableList(this.order_);
                }
                if ((mutable_bitField0_ & 8) == 8) {
                    this.args_ = Collections.unmodifiableList(this.args_);
                }
                this.unknownFields = unknownFields.build();
                this.makeExtensionsImmutable();
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return internal_static_Mysqlx_Crud_Delete_descriptor;
        }

        @Override
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return internal_static_Mysqlx_Crud_Delete_fieldAccessorTable.ensureFieldAccessorsInitialized(Delete.class, Builder.class);
        }

        public Parser<Delete> getParserForType() {
            return PARSER;
        }

        @Override
        public boolean hasCollection() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override
        public Collection getCollection() {
            return this.collection_;
        }

        @Override
        public CollectionOrBuilder getCollectionOrBuilder() {
            return this.collection_;
        }

        @Override
        public boolean hasDataModel() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override
        public DataModel getDataModel() {
            return this.dataModel_;
        }

        @Override
        public boolean hasCriteria() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override
        public MysqlxExpr.Expr getCriteria() {
            return this.criteria_;
        }

        @Override
        public MysqlxExpr.ExprOrBuilder getCriteriaOrBuilder() {
            return this.criteria_;
        }

        @Override
        public List<MysqlxDatatypes.Scalar> getArgsList() {
            return this.args_;
        }

        @Override
        public List<? extends MysqlxDatatypes.ScalarOrBuilder> getArgsOrBuilderList() {
            return this.args_;
        }

        @Override
        public int getArgsCount() {
            return this.args_.size();
        }

        @Override
        public MysqlxDatatypes.Scalar getArgs(int index) {
            return this.args_.get(index);
        }

        @Override
        public MysqlxDatatypes.ScalarOrBuilder getArgsOrBuilder(int index) {
            return this.args_.get(index);
        }

        @Override
        public boolean hasLimit() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override
        public Limit getLimit() {
            return this.limit_;
        }

        @Override
        public LimitOrBuilder getLimitOrBuilder() {
            return this.limit_;
        }

        @Override
        public List<Order> getOrderList() {
            return this.order_;
        }

        @Override
        public List<? extends OrderOrBuilder> getOrderOrBuilderList() {
            return this.order_;
        }

        @Override
        public int getOrderCount() {
            return this.order_.size();
        }

        @Override
        public Order getOrder(int index) {
            return this.order_.get(index);
        }

        @Override
        public OrderOrBuilder getOrderOrBuilder(int index) {
            return this.order_.get(index);
        }

        private void initFields() {
            this.collection_ = Collection.getDefaultInstance();
            this.dataModel_ = DataModel.DOCUMENT;
            this.criteria_ = MysqlxExpr.Expr.getDefaultInstance();
            this.args_ = Collections.emptyList();
            this.limit_ = Limit.getDefaultInstance();
            this.order_ = Collections.emptyList();
        }

        @Override
        public final boolean isInitialized() {
            int i;
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            if (!this.hasCollection()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            if (!this.getCollection().isInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            if (this.hasCriteria() && !this.getCriteria().isInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            for (i = 0; i < this.getArgsCount(); ++i) {
                if (this.getArgs(i).isInitialized()) continue;
                this.memoizedIsInitialized = 0;
                return false;
            }
            if (this.hasLimit() && !this.getLimit().isInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            for (i = 0; i < this.getOrderCount(); ++i) {
                if (this.getOrder(i).isInitialized()) continue;
                this.memoizedIsInitialized = 0;
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        @Override
        public void writeTo(CodedOutputStream output) throws IOException {
            int i;
            this.getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                output.writeMessage(1, this.collection_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeEnum(2, this.dataModel_.getNumber());
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeMessage(3, this.criteria_);
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeMessage(4, this.limit_);
            }
            for (i = 0; i < this.order_.size(); ++i) {
                output.writeMessage(5, this.order_.get(i));
            }
            for (i = 0; i < this.args_.size(); ++i) {
                output.writeMessage(6, this.args_.get(i));
            }
            this.getUnknownFields().writeTo(output);
        }

        @Override
        public int getSerializedSize() {
            int i;
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            size = 0;
            if ((this.bitField0_ & 1) == 1) {
                size += CodedOutputStream.computeMessageSize(1, this.collection_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size += CodedOutputStream.computeEnumSize(2, this.dataModel_.getNumber());
            }
            if ((this.bitField0_ & 4) == 4) {
                size += CodedOutputStream.computeMessageSize(3, this.criteria_);
            }
            if ((this.bitField0_ & 8) == 8) {
                size += CodedOutputStream.computeMessageSize(4, this.limit_);
            }
            for (i = 0; i < this.order_.size(); ++i) {
                size += CodedOutputStream.computeMessageSize(5, this.order_.get(i));
            }
            for (i = 0; i < this.args_.size(); ++i) {
                size += CodedOutputStream.computeMessageSize(6, this.args_.get(i));
            }
            this.memoizedSerializedSize = size += this.getUnknownFields().getSerializedSize();
            return size;
        }

        @Override
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static Delete parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static Delete parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static Delete parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static Delete parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static Delete parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static Delete parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Delete parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static Delete parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static Delete parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static Delete parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override
        public Builder newBuilderForType() {
            return Delete.newBuilder();
        }

        public static Builder newBuilder(Delete prototype) {
            return Delete.newBuilder().mergeFrom(prototype);
        }

        @Override
        public Builder toBuilder() {
            return Delete.newBuilder(this);
        }

        @Override
        protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        static {
            PARSER = new AbstractParser<Delete>(){

                @Override
                public Delete parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                    return new Delete(input, extensionRegistry);
                }
            };
            defaultInstance = new Delete(true);
            defaultInstance.initFields();
        }

        public static final class Builder
        extends GeneratedMessage.Builder<Builder>
        implements DeleteOrBuilder {
            private int bitField0_;
            private Collection collection_ = Collection.getDefaultInstance();
            private SingleFieldBuilder<Collection, Collection.Builder, CollectionOrBuilder> collectionBuilder_;
            private DataModel dataModel_ = DataModel.DOCUMENT;
            private MysqlxExpr.Expr criteria_ = MysqlxExpr.Expr.getDefaultInstance();
            private SingleFieldBuilder<MysqlxExpr.Expr, MysqlxExpr.Expr.Builder, MysqlxExpr.ExprOrBuilder> criteriaBuilder_;
            private List<MysqlxDatatypes.Scalar> args_ = Collections.emptyList();
            private RepeatedFieldBuilder<MysqlxDatatypes.Scalar, MysqlxDatatypes.Scalar.Builder, MysqlxDatatypes.ScalarOrBuilder> argsBuilder_;
            private Limit limit_ = Limit.getDefaultInstance();
            private SingleFieldBuilder<Limit, Limit.Builder, LimitOrBuilder> limitBuilder_;
            private List<Order> order_ = Collections.emptyList();
            private RepeatedFieldBuilder<Order, Order.Builder, OrderOrBuilder> orderBuilder_;

            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_Mysqlx_Crud_Delete_descriptor;
            }

            @Override
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_Mysqlx_Crud_Delete_fieldAccessorTable.ensureFieldAccessorsInitialized(Delete.class, Builder.class);
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
                    this.getCollectionFieldBuilder();
                    this.getCriteriaFieldBuilder();
                    this.getArgsFieldBuilder();
                    this.getLimitFieldBuilder();
                    this.getOrderFieldBuilder();
                }
            }

            private static Builder create() {
                return new Builder();
            }

            @Override
            public Builder clear() {
                super.clear();
                if (this.collectionBuilder_ == null) {
                    this.collection_ = Collection.getDefaultInstance();
                } else {
                    this.collectionBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFFFE;
                this.dataModel_ = DataModel.DOCUMENT;
                this.bitField0_ &= 0xFFFFFFFD;
                if (this.criteriaBuilder_ == null) {
                    this.criteria_ = MysqlxExpr.Expr.getDefaultInstance();
                } else {
                    this.criteriaBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFFFB;
                if (this.argsBuilder_ == null) {
                    this.args_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFFFF7;
                } else {
                    this.argsBuilder_.clear();
                }
                if (this.limitBuilder_ == null) {
                    this.limit_ = Limit.getDefaultInstance();
                } else {
                    this.limitBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFFEF;
                if (this.orderBuilder_ == null) {
                    this.order_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFFFDF;
                } else {
                    this.orderBuilder_.clear();
                }
                return this;
            }

            @Override
            public Builder clone() {
                return Builder.create().mergeFrom(this.buildPartial());
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return internal_static_Mysqlx_Crud_Delete_descriptor;
            }

            @Override
            public Delete getDefaultInstanceForType() {
                return Delete.getDefaultInstance();
            }

            @Override
            public Delete build() {
                Delete result = this.buildPartial();
                if (!result.isInitialized()) {
                    throw Builder.newUninitializedMessageException(result);
                }
                return result;
            }

            @Override
            public Delete buildPartial() {
                Delete result = new Delete(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ |= 1;
                }
                if (this.collectionBuilder_ == null) {
                    result.collection_ = this.collection_;
                } else {
                    result.collection_ = this.collectionBuilder_.build();
                }
                if ((from_bitField0_ & 2) == 2) {
                    to_bitField0_ |= 2;
                }
                result.dataModel_ = this.dataModel_;
                if ((from_bitField0_ & 4) == 4) {
                    to_bitField0_ |= 4;
                }
                if (this.criteriaBuilder_ == null) {
                    result.criteria_ = this.criteria_;
                } else {
                    result.criteria_ = this.criteriaBuilder_.build();
                }
                if (this.argsBuilder_ == null) {
                    if ((this.bitField0_ & 8) == 8) {
                        this.args_ = Collections.unmodifiableList(this.args_);
                        this.bitField0_ &= 0xFFFFFFF7;
                    }
                    result.args_ = this.args_;
                } else {
                    result.args_ = this.argsBuilder_.build();
                }
                if ((from_bitField0_ & 0x10) == 16) {
                    to_bitField0_ |= 8;
                }
                if (this.limitBuilder_ == null) {
                    result.limit_ = this.limit_;
                } else {
                    result.limit_ = this.limitBuilder_.build();
                }
                if (this.orderBuilder_ == null) {
                    if ((this.bitField0_ & 0x20) == 32) {
                        this.order_ = Collections.unmodifiableList(this.order_);
                        this.bitField0_ &= 0xFFFFFFDF;
                    }
                    result.order_ = this.order_;
                } else {
                    result.order_ = this.orderBuilder_.build();
                }
                result.bitField0_ = to_bitField0_;
                this.onBuilt();
                return result;
            }

            @Override
            public Builder mergeFrom(Message other) {
                if (other instanceof Delete) {
                    return this.mergeFrom((Delete)other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(Delete other) {
                if (other == Delete.getDefaultInstance()) {
                    return this;
                }
                if (other.hasCollection()) {
                    this.mergeCollection(other.getCollection());
                }
                if (other.hasDataModel()) {
                    this.setDataModel(other.getDataModel());
                }
                if (other.hasCriteria()) {
                    this.mergeCriteria(other.getCriteria());
                }
                if (this.argsBuilder_ == null) {
                    if (!other.args_.isEmpty()) {
                        if (this.args_.isEmpty()) {
                            this.args_ = other.args_;
                            this.bitField0_ &= 0xFFFFFFF7;
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
                        this.bitField0_ &= 0xFFFFFFF7;
                        this.argsBuilder_ = alwaysUseFieldBuilders ? this.getArgsFieldBuilder() : null;
                    } else {
                        this.argsBuilder_.addAllMessages(other.args_);
                    }
                }
                if (other.hasLimit()) {
                    this.mergeLimit(other.getLimit());
                }
                if (this.orderBuilder_ == null) {
                    if (!other.order_.isEmpty()) {
                        if (this.order_.isEmpty()) {
                            this.order_ = other.order_;
                            this.bitField0_ &= 0xFFFFFFDF;
                        } else {
                            this.ensureOrderIsMutable();
                            this.order_.addAll(other.order_);
                        }
                        this.onChanged();
                    }
                } else if (!other.order_.isEmpty()) {
                    if (this.orderBuilder_.isEmpty()) {
                        this.orderBuilder_.dispose();
                        this.orderBuilder_ = null;
                        this.order_ = other.order_;
                        this.bitField0_ &= 0xFFFFFFDF;
                        this.orderBuilder_ = alwaysUseFieldBuilders ? this.getOrderFieldBuilder() : null;
                    } else {
                        this.orderBuilder_.addAllMessages(other.order_);
                    }
                }
                this.mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            @Override
            public final boolean isInitialized() {
                int i;
                if (!this.hasCollection()) {
                    return false;
                }
                if (!this.getCollection().isInitialized()) {
                    return false;
                }
                if (this.hasCriteria() && !this.getCriteria().isInitialized()) {
                    return false;
                }
                for (i = 0; i < this.getArgsCount(); ++i) {
                    if (this.getArgs(i).isInitialized()) continue;
                    return false;
                }
                if (this.hasLimit() && !this.getLimit().isInitialized()) {
                    return false;
                }
                for (i = 0; i < this.getOrderCount(); ++i) {
                    if (this.getOrder(i).isInitialized()) continue;
                    return false;
                }
                return true;
            }

            @Override
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                Delete parsedMessage = null;
                try {
                    parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
                } catch (InvalidProtocolBufferException e) {
                    parsedMessage = (Delete)e.getUnfinishedMessage();
                    throw e;
                } finally {
                    if (parsedMessage != null) {
                        this.mergeFrom(parsedMessage);
                    }
                }
                return this;
            }

            @Override
            public boolean hasCollection() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override
            public Collection getCollection() {
                if (this.collectionBuilder_ == null) {
                    return this.collection_;
                }
                return this.collectionBuilder_.getMessage();
            }

            public Builder setCollection(Collection value) {
                if (this.collectionBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.collection_ = value;
                    this.onChanged();
                } else {
                    this.collectionBuilder_.setMessage(value);
                }
                this.bitField0_ |= 1;
                return this;
            }

            public Builder setCollection(Collection.Builder builderForValue) {
                if (this.collectionBuilder_ == null) {
                    this.collection_ = builderForValue.build();
                    this.onChanged();
                } else {
                    this.collectionBuilder_.setMessage(builderForValue.build());
                }
                this.bitField0_ |= 1;
                return this;
            }

            public Builder mergeCollection(Collection value) {
                if (this.collectionBuilder_ == null) {
                    this.collection_ = (this.bitField0_ & 1) == 1 && this.collection_ != Collection.getDefaultInstance() ? Collection.newBuilder(this.collection_).mergeFrom(value).buildPartial() : value;
                    this.onChanged();
                } else {
                    this.collectionBuilder_.mergeFrom(value);
                }
                this.bitField0_ |= 1;
                return this;
            }

            public Builder clearCollection() {
                if (this.collectionBuilder_ == null) {
                    this.collection_ = Collection.getDefaultInstance();
                    this.onChanged();
                } else {
                    this.collectionBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFFFE;
                return this;
            }

            public Collection.Builder getCollectionBuilder() {
                this.bitField0_ |= 1;
                this.onChanged();
                return this.getCollectionFieldBuilder().getBuilder();
            }

            @Override
            public CollectionOrBuilder getCollectionOrBuilder() {
                if (this.collectionBuilder_ != null) {
                    return this.collectionBuilder_.getMessageOrBuilder();
                }
                return this.collection_;
            }

            private SingleFieldBuilder<Collection, Collection.Builder, CollectionOrBuilder> getCollectionFieldBuilder() {
                if (this.collectionBuilder_ == null) {
                    this.collectionBuilder_ = new SingleFieldBuilder(this.getCollection(), this.getParentForChildren(), this.isClean());
                    this.collection_ = null;
                }
                return this.collectionBuilder_;
            }

            @Override
            public boolean hasDataModel() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override
            public DataModel getDataModel() {
                return this.dataModel_;
            }

            public Builder setDataModel(DataModel value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 2;
                this.dataModel_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearDataModel() {
                this.bitField0_ &= 0xFFFFFFFD;
                this.dataModel_ = DataModel.DOCUMENT;
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasCriteria() {
                return (this.bitField0_ & 4) == 4;
            }

            @Override
            public MysqlxExpr.Expr getCriteria() {
                if (this.criteriaBuilder_ == null) {
                    return this.criteria_;
                }
                return this.criteriaBuilder_.getMessage();
            }

            public Builder setCriteria(MysqlxExpr.Expr value) {
                if (this.criteriaBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.criteria_ = value;
                    this.onChanged();
                } else {
                    this.criteriaBuilder_.setMessage(value);
                }
                this.bitField0_ |= 4;
                return this;
            }

            public Builder setCriteria(MysqlxExpr.Expr.Builder builderForValue) {
                if (this.criteriaBuilder_ == null) {
                    this.criteria_ = builderForValue.build();
                    this.onChanged();
                } else {
                    this.criteriaBuilder_.setMessage(builderForValue.build());
                }
                this.bitField0_ |= 4;
                return this;
            }

            public Builder mergeCriteria(MysqlxExpr.Expr value) {
                if (this.criteriaBuilder_ == null) {
                    this.criteria_ = (this.bitField0_ & 4) == 4 && this.criteria_ != MysqlxExpr.Expr.getDefaultInstance() ? MysqlxExpr.Expr.newBuilder(this.criteria_).mergeFrom(value).buildPartial() : value;
                    this.onChanged();
                } else {
                    this.criteriaBuilder_.mergeFrom(value);
                }
                this.bitField0_ |= 4;
                return this;
            }

            public Builder clearCriteria() {
                if (this.criteriaBuilder_ == null) {
                    this.criteria_ = MysqlxExpr.Expr.getDefaultInstance();
                    this.onChanged();
                } else {
                    this.criteriaBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFFFB;
                return this;
            }

            public MysqlxExpr.Expr.Builder getCriteriaBuilder() {
                this.bitField0_ |= 4;
                this.onChanged();
                return this.getCriteriaFieldBuilder().getBuilder();
            }

            @Override
            public MysqlxExpr.ExprOrBuilder getCriteriaOrBuilder() {
                if (this.criteriaBuilder_ != null) {
                    return this.criteriaBuilder_.getMessageOrBuilder();
                }
                return this.criteria_;
            }

            private SingleFieldBuilder<MysqlxExpr.Expr, MysqlxExpr.Expr.Builder, MysqlxExpr.ExprOrBuilder> getCriteriaFieldBuilder() {
                if (this.criteriaBuilder_ == null) {
                    this.criteriaBuilder_ = new SingleFieldBuilder(this.getCriteria(), this.getParentForChildren(), this.isClean());
                    this.criteria_ = null;
                }
                return this.criteriaBuilder_;
            }

            private void ensureArgsIsMutable() {
                if ((this.bitField0_ & 8) != 8) {
                    this.args_ = new ArrayList<MysqlxDatatypes.Scalar>(this.args_);
                    this.bitField0_ |= 8;
                }
            }

            @Override
            public List<MysqlxDatatypes.Scalar> getArgsList() {
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
            public MysqlxDatatypes.Scalar getArgs(int index) {
                if (this.argsBuilder_ == null) {
                    return this.args_.get(index);
                }
                return this.argsBuilder_.getMessage(index);
            }

            public Builder setArgs(int index, MysqlxDatatypes.Scalar value) {
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

            public Builder setArgs(int index, MysqlxDatatypes.Scalar.Builder builderForValue) {
                if (this.argsBuilder_ == null) {
                    this.ensureArgsIsMutable();
                    this.args_.set(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.argsBuilder_.setMessage(index, builderForValue.build());
                }
                return this;
            }

            public Builder addArgs(MysqlxDatatypes.Scalar value) {
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

            public Builder addArgs(int index, MysqlxDatatypes.Scalar value) {
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

            public Builder addArgs(MysqlxDatatypes.Scalar.Builder builderForValue) {
                if (this.argsBuilder_ == null) {
                    this.ensureArgsIsMutable();
                    this.args_.add(builderForValue.build());
                    this.onChanged();
                } else {
                    this.argsBuilder_.addMessage(builderForValue.build());
                }
                return this;
            }

            public Builder addArgs(int index, MysqlxDatatypes.Scalar.Builder builderForValue) {
                if (this.argsBuilder_ == null) {
                    this.ensureArgsIsMutable();
                    this.args_.add(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.argsBuilder_.addMessage(index, builderForValue.build());
                }
                return this;
            }

            public Builder addAllArgs(Iterable<? extends MysqlxDatatypes.Scalar> values2) {
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
                    this.bitField0_ &= 0xFFFFFFF7;
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

            public MysqlxDatatypes.Scalar.Builder getArgsBuilder(int index) {
                return this.getArgsFieldBuilder().getBuilder(index);
            }

            @Override
            public MysqlxDatatypes.ScalarOrBuilder getArgsOrBuilder(int index) {
                if (this.argsBuilder_ == null) {
                    return this.args_.get(index);
                }
                return this.argsBuilder_.getMessageOrBuilder(index);
            }

            @Override
            public List<? extends MysqlxDatatypes.ScalarOrBuilder> getArgsOrBuilderList() {
                if (this.argsBuilder_ != null) {
                    return this.argsBuilder_.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.args_);
            }

            public MysqlxDatatypes.Scalar.Builder addArgsBuilder() {
                return this.getArgsFieldBuilder().addBuilder(MysqlxDatatypes.Scalar.getDefaultInstance());
            }

            public MysqlxDatatypes.Scalar.Builder addArgsBuilder(int index) {
                return this.getArgsFieldBuilder().addBuilder(index, MysqlxDatatypes.Scalar.getDefaultInstance());
            }

            public List<MysqlxDatatypes.Scalar.Builder> getArgsBuilderList() {
                return this.getArgsFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilder<MysqlxDatatypes.Scalar, MysqlxDatatypes.Scalar.Builder, MysqlxDatatypes.ScalarOrBuilder> getArgsFieldBuilder() {
                if (this.argsBuilder_ == null) {
                    this.argsBuilder_ = new RepeatedFieldBuilder(this.args_, (this.bitField0_ & 8) == 8, this.getParentForChildren(), this.isClean());
                    this.args_ = null;
                }
                return this.argsBuilder_;
            }

            @Override
            public boolean hasLimit() {
                return (this.bitField0_ & 0x10) == 16;
            }

            @Override
            public Limit getLimit() {
                if (this.limitBuilder_ == null) {
                    return this.limit_;
                }
                return this.limitBuilder_.getMessage();
            }

            public Builder setLimit(Limit value) {
                if (this.limitBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.limit_ = value;
                    this.onChanged();
                } else {
                    this.limitBuilder_.setMessage(value);
                }
                this.bitField0_ |= 0x10;
                return this;
            }

            public Builder setLimit(Limit.Builder builderForValue) {
                if (this.limitBuilder_ == null) {
                    this.limit_ = builderForValue.build();
                    this.onChanged();
                } else {
                    this.limitBuilder_.setMessage(builderForValue.build());
                }
                this.bitField0_ |= 0x10;
                return this;
            }

            public Builder mergeLimit(Limit value) {
                if (this.limitBuilder_ == null) {
                    this.limit_ = (this.bitField0_ & 0x10) == 16 && this.limit_ != Limit.getDefaultInstance() ? Limit.newBuilder(this.limit_).mergeFrom(value).buildPartial() : value;
                    this.onChanged();
                } else {
                    this.limitBuilder_.mergeFrom(value);
                }
                this.bitField0_ |= 0x10;
                return this;
            }

            public Builder clearLimit() {
                if (this.limitBuilder_ == null) {
                    this.limit_ = Limit.getDefaultInstance();
                    this.onChanged();
                } else {
                    this.limitBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFFEF;
                return this;
            }

            public Limit.Builder getLimitBuilder() {
                this.bitField0_ |= 0x10;
                this.onChanged();
                return this.getLimitFieldBuilder().getBuilder();
            }

            @Override
            public LimitOrBuilder getLimitOrBuilder() {
                if (this.limitBuilder_ != null) {
                    return this.limitBuilder_.getMessageOrBuilder();
                }
                return this.limit_;
            }

            private SingleFieldBuilder<Limit, Limit.Builder, LimitOrBuilder> getLimitFieldBuilder() {
                if (this.limitBuilder_ == null) {
                    this.limitBuilder_ = new SingleFieldBuilder(this.getLimit(), this.getParentForChildren(), this.isClean());
                    this.limit_ = null;
                }
                return this.limitBuilder_;
            }

            private void ensureOrderIsMutable() {
                if ((this.bitField0_ & 0x20) != 32) {
                    this.order_ = new ArrayList<Order>(this.order_);
                    this.bitField0_ |= 0x20;
                }
            }

            @Override
            public List<Order> getOrderList() {
                if (this.orderBuilder_ == null) {
                    return Collections.unmodifiableList(this.order_);
                }
                return this.orderBuilder_.getMessageList();
            }

            @Override
            public int getOrderCount() {
                if (this.orderBuilder_ == null) {
                    return this.order_.size();
                }
                return this.orderBuilder_.getCount();
            }

            @Override
            public Order getOrder(int index) {
                if (this.orderBuilder_ == null) {
                    return this.order_.get(index);
                }
                return this.orderBuilder_.getMessage(index);
            }

            public Builder setOrder(int index, Order value) {
                if (this.orderBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureOrderIsMutable();
                    this.order_.set(index, value);
                    this.onChanged();
                } else {
                    this.orderBuilder_.setMessage(index, value);
                }
                return this;
            }

            public Builder setOrder(int index, Order.Builder builderForValue) {
                if (this.orderBuilder_ == null) {
                    this.ensureOrderIsMutable();
                    this.order_.set(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.orderBuilder_.setMessage(index, builderForValue.build());
                }
                return this;
            }

            public Builder addOrder(Order value) {
                if (this.orderBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureOrderIsMutable();
                    this.order_.add(value);
                    this.onChanged();
                } else {
                    this.orderBuilder_.addMessage(value);
                }
                return this;
            }

            public Builder addOrder(int index, Order value) {
                if (this.orderBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureOrderIsMutable();
                    this.order_.add(index, value);
                    this.onChanged();
                } else {
                    this.orderBuilder_.addMessage(index, value);
                }
                return this;
            }

            public Builder addOrder(Order.Builder builderForValue) {
                if (this.orderBuilder_ == null) {
                    this.ensureOrderIsMutable();
                    this.order_.add(builderForValue.build());
                    this.onChanged();
                } else {
                    this.orderBuilder_.addMessage(builderForValue.build());
                }
                return this;
            }

            public Builder addOrder(int index, Order.Builder builderForValue) {
                if (this.orderBuilder_ == null) {
                    this.ensureOrderIsMutable();
                    this.order_.add(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.orderBuilder_.addMessage(index, builderForValue.build());
                }
                return this;
            }

            public Builder addAllOrder(Iterable<? extends Order> values2) {
                if (this.orderBuilder_ == null) {
                    this.ensureOrderIsMutable();
                    AbstractMessageLite.Builder.addAll(values2, this.order_);
                    this.onChanged();
                } else {
                    this.orderBuilder_.addAllMessages(values2);
                }
                return this;
            }

            public Builder clearOrder() {
                if (this.orderBuilder_ == null) {
                    this.order_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFFFDF;
                    this.onChanged();
                } else {
                    this.orderBuilder_.clear();
                }
                return this;
            }

            public Builder removeOrder(int index) {
                if (this.orderBuilder_ == null) {
                    this.ensureOrderIsMutable();
                    this.order_.remove(index);
                    this.onChanged();
                } else {
                    this.orderBuilder_.remove(index);
                }
                return this;
            }

            public Order.Builder getOrderBuilder(int index) {
                return this.getOrderFieldBuilder().getBuilder(index);
            }

            @Override
            public OrderOrBuilder getOrderOrBuilder(int index) {
                if (this.orderBuilder_ == null) {
                    return this.order_.get(index);
                }
                return this.orderBuilder_.getMessageOrBuilder(index);
            }

            @Override
            public List<? extends OrderOrBuilder> getOrderOrBuilderList() {
                if (this.orderBuilder_ != null) {
                    return this.orderBuilder_.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.order_);
            }

            public Order.Builder addOrderBuilder() {
                return this.getOrderFieldBuilder().addBuilder(Order.getDefaultInstance());
            }

            public Order.Builder addOrderBuilder(int index) {
                return this.getOrderFieldBuilder().addBuilder(index, Order.getDefaultInstance());
            }

            public List<Order.Builder> getOrderBuilderList() {
                return this.getOrderFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilder<Order, Order.Builder, OrderOrBuilder> getOrderFieldBuilder() {
                if (this.orderBuilder_ == null) {
                    this.orderBuilder_ = new RepeatedFieldBuilder(this.order_, (this.bitField0_ & 0x20) == 32, this.getParentForChildren(), this.isClean());
                    this.order_ = null;
                }
                return this.orderBuilder_;
            }
        }
    }

    public static interface DeleteOrBuilder
    extends MessageOrBuilder {
        public boolean hasCollection();

        public Collection getCollection();

        public CollectionOrBuilder getCollectionOrBuilder();

        public boolean hasDataModel();

        public DataModel getDataModel();

        public boolean hasCriteria();

        public MysqlxExpr.Expr getCriteria();

        public MysqlxExpr.ExprOrBuilder getCriteriaOrBuilder();

        public List<MysqlxDatatypes.Scalar> getArgsList();

        public MysqlxDatatypes.Scalar getArgs(int var1);

        public int getArgsCount();

        public List<? extends MysqlxDatatypes.ScalarOrBuilder> getArgsOrBuilderList();

        public MysqlxDatatypes.ScalarOrBuilder getArgsOrBuilder(int var1);

        public boolean hasLimit();

        public Limit getLimit();

        public LimitOrBuilder getLimitOrBuilder();

        public List<Order> getOrderList();

        public Order getOrder(int var1);

        public int getOrderCount();

        public List<? extends OrderOrBuilder> getOrderOrBuilderList();

        public OrderOrBuilder getOrderOrBuilder(int var1);
    }

    public static final class Update
    extends GeneratedMessage
    implements UpdateOrBuilder {
        private static final Update defaultInstance;
        private final UnknownFieldSet unknownFields;
        public static Parser<Update> PARSER;
        private int bitField0_;
        public static final int COLLECTION_FIELD_NUMBER = 2;
        private Collection collection_;
        public static final int DATA_MODEL_FIELD_NUMBER = 3;
        private DataModel dataModel_;
        public static final int CRITERIA_FIELD_NUMBER = 4;
        private MysqlxExpr.Expr criteria_;
        public static final int ARGS_FIELD_NUMBER = 8;
        private List<MysqlxDatatypes.Scalar> args_;
        public static final int LIMIT_FIELD_NUMBER = 5;
        private Limit limit_;
        public static final int ORDER_FIELD_NUMBER = 6;
        private List<Order> order_;
        public static final int OPERATION_FIELD_NUMBER = 7;
        private List<UpdateOperation> operation_;
        private byte memoizedIsInitialized = (byte)-1;
        private int memoizedSerializedSize = -1;
        private static final long serialVersionUID = 0L;

        private Update(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.unknownFields = builder.getUnknownFields();
        }

        private Update(boolean noInit) {
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static Update getDefaultInstance() {
            return defaultInstance;
        }

        @Override
        public Update getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private Update(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this.initFields();
            int mutable_bitField0_ = 0;
            UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
            try {
                boolean done = false;
                block16: while (!done) {
                    int tag = input.readTag();
                    switch (tag) {
                        case 0: {
                            done = true;
                            continue block16;
                        }
                        default: {
                            if (this.parseUnknownField(input, unknownFields, extensionRegistry, tag)) continue block16;
                            done = true;
                            continue block16;
                        }
                        case 18: {
                            Collection.Builder subBuilder = null;
                            if ((this.bitField0_ & 1) == 1) {
                                subBuilder = this.collection_.toBuilder();
                            }
                            this.collection_ = input.readMessage(Collection.PARSER, extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom(this.collection_);
                                this.collection_ = subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 1;
                            continue block16;
                        }
                        case 24: {
                            int rawValue = input.readEnum();
                            DataModel value = DataModel.valueOf(rawValue);
                            if (value == null) {
                                unknownFields.mergeVarintField(3, rawValue);
                                continue block16;
                            }
                            this.bitField0_ |= 2;
                            this.dataModel_ = value;
                            continue block16;
                        }
                        case 34: {
                            MysqlxExpr.Expr.Builder subBuilder = null;
                            if ((this.bitField0_ & 4) == 4) {
                                subBuilder = this.criteria_.toBuilder();
                            }
                            this.criteria_ = input.readMessage(MysqlxExpr.Expr.PARSER, extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom(this.criteria_);
                                this.criteria_ = subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 4;
                            continue block16;
                        }
                        case 42: {
                            Limit.Builder subBuilder = null;
                            if ((this.bitField0_ & 8) == 8) {
                                subBuilder = this.limit_.toBuilder();
                            }
                            this.limit_ = input.readMessage(Limit.PARSER, extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom(this.limit_);
                                this.limit_ = subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 8;
                            continue block16;
                        }
                        case 50: {
                            if ((mutable_bitField0_ & 0x20) != 32) {
                                this.order_ = new ArrayList<Order>();
                                mutable_bitField0_ |= 0x20;
                            }
                            this.order_.add(input.readMessage(Order.PARSER, extensionRegistry));
                            continue block16;
                        }
                        case 58: {
                            if ((mutable_bitField0_ & 0x40) != 64) {
                                this.operation_ = new ArrayList<UpdateOperation>();
                                mutable_bitField0_ |= 0x40;
                            }
                            this.operation_.add(input.readMessage(UpdateOperation.PARSER, extensionRegistry));
                            continue block16;
                        }
                        case 66: 
                    }
                    if ((mutable_bitField0_ & 8) != 8) {
                        this.args_ = new ArrayList<MysqlxDatatypes.Scalar>();
                        mutable_bitField0_ |= 8;
                    }
                    this.args_.add(input.readMessage(MysqlxDatatypes.Scalar.PARSER, extensionRegistry));
                }
            } catch (InvalidProtocolBufferException e) {
                throw e.setUnfinishedMessage(this);
            } catch (IOException e) {
                throw new InvalidProtocolBufferException(e.getMessage()).setUnfinishedMessage(this);
            } finally {
                if ((mutable_bitField0_ & 0x20) == 32) {
                    this.order_ = Collections.unmodifiableList(this.order_);
                }
                if ((mutable_bitField0_ & 0x40) == 64) {
                    this.operation_ = Collections.unmodifiableList(this.operation_);
                }
                if ((mutable_bitField0_ & 8) == 8) {
                    this.args_ = Collections.unmodifiableList(this.args_);
                }
                this.unknownFields = unknownFields.build();
                this.makeExtensionsImmutable();
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return internal_static_Mysqlx_Crud_Update_descriptor;
        }

        @Override
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return internal_static_Mysqlx_Crud_Update_fieldAccessorTable.ensureFieldAccessorsInitialized(Update.class, Builder.class);
        }

        public Parser<Update> getParserForType() {
            return PARSER;
        }

        @Override
        public boolean hasCollection() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override
        public Collection getCollection() {
            return this.collection_;
        }

        @Override
        public CollectionOrBuilder getCollectionOrBuilder() {
            return this.collection_;
        }

        @Override
        public boolean hasDataModel() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override
        public DataModel getDataModel() {
            return this.dataModel_;
        }

        @Override
        public boolean hasCriteria() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override
        public MysqlxExpr.Expr getCriteria() {
            return this.criteria_;
        }

        @Override
        public MysqlxExpr.ExprOrBuilder getCriteriaOrBuilder() {
            return this.criteria_;
        }

        @Override
        public List<MysqlxDatatypes.Scalar> getArgsList() {
            return this.args_;
        }

        @Override
        public List<? extends MysqlxDatatypes.ScalarOrBuilder> getArgsOrBuilderList() {
            return this.args_;
        }

        @Override
        public int getArgsCount() {
            return this.args_.size();
        }

        @Override
        public MysqlxDatatypes.Scalar getArgs(int index) {
            return this.args_.get(index);
        }

        @Override
        public MysqlxDatatypes.ScalarOrBuilder getArgsOrBuilder(int index) {
            return this.args_.get(index);
        }

        @Override
        public boolean hasLimit() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override
        public Limit getLimit() {
            return this.limit_;
        }

        @Override
        public LimitOrBuilder getLimitOrBuilder() {
            return this.limit_;
        }

        @Override
        public List<Order> getOrderList() {
            return this.order_;
        }

        @Override
        public List<? extends OrderOrBuilder> getOrderOrBuilderList() {
            return this.order_;
        }

        @Override
        public int getOrderCount() {
            return this.order_.size();
        }

        @Override
        public Order getOrder(int index) {
            return this.order_.get(index);
        }

        @Override
        public OrderOrBuilder getOrderOrBuilder(int index) {
            return this.order_.get(index);
        }

        @Override
        public List<UpdateOperation> getOperationList() {
            return this.operation_;
        }

        @Override
        public List<? extends UpdateOperationOrBuilder> getOperationOrBuilderList() {
            return this.operation_;
        }

        @Override
        public int getOperationCount() {
            return this.operation_.size();
        }

        @Override
        public UpdateOperation getOperation(int index) {
            return this.operation_.get(index);
        }

        @Override
        public UpdateOperationOrBuilder getOperationOrBuilder(int index) {
            return this.operation_.get(index);
        }

        private void initFields() {
            this.collection_ = Collection.getDefaultInstance();
            this.dataModel_ = DataModel.DOCUMENT;
            this.criteria_ = MysqlxExpr.Expr.getDefaultInstance();
            this.args_ = Collections.emptyList();
            this.limit_ = Limit.getDefaultInstance();
            this.order_ = Collections.emptyList();
            this.operation_ = Collections.emptyList();
        }

        @Override
        public final boolean isInitialized() {
            int i;
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            if (!this.hasCollection()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            if (!this.getCollection().isInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            if (this.hasCriteria() && !this.getCriteria().isInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            for (i = 0; i < this.getArgsCount(); ++i) {
                if (this.getArgs(i).isInitialized()) continue;
                this.memoizedIsInitialized = 0;
                return false;
            }
            if (this.hasLimit() && !this.getLimit().isInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            for (i = 0; i < this.getOrderCount(); ++i) {
                if (this.getOrder(i).isInitialized()) continue;
                this.memoizedIsInitialized = 0;
                return false;
            }
            for (i = 0; i < this.getOperationCount(); ++i) {
                if (this.getOperation(i).isInitialized()) continue;
                this.memoizedIsInitialized = 0;
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        @Override
        public void writeTo(CodedOutputStream output) throws IOException {
            int i;
            this.getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                output.writeMessage(2, this.collection_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeEnum(3, this.dataModel_.getNumber());
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeMessage(4, this.criteria_);
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeMessage(5, this.limit_);
            }
            for (i = 0; i < this.order_.size(); ++i) {
                output.writeMessage(6, this.order_.get(i));
            }
            for (i = 0; i < this.operation_.size(); ++i) {
                output.writeMessage(7, this.operation_.get(i));
            }
            for (i = 0; i < this.args_.size(); ++i) {
                output.writeMessage(8, this.args_.get(i));
            }
            this.getUnknownFields().writeTo(output);
        }

        @Override
        public int getSerializedSize() {
            int i;
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            size = 0;
            if ((this.bitField0_ & 1) == 1) {
                size += CodedOutputStream.computeMessageSize(2, this.collection_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size += CodedOutputStream.computeEnumSize(3, this.dataModel_.getNumber());
            }
            if ((this.bitField0_ & 4) == 4) {
                size += CodedOutputStream.computeMessageSize(4, this.criteria_);
            }
            if ((this.bitField0_ & 8) == 8) {
                size += CodedOutputStream.computeMessageSize(5, this.limit_);
            }
            for (i = 0; i < this.order_.size(); ++i) {
                size += CodedOutputStream.computeMessageSize(6, this.order_.get(i));
            }
            for (i = 0; i < this.operation_.size(); ++i) {
                size += CodedOutputStream.computeMessageSize(7, this.operation_.get(i));
            }
            for (i = 0; i < this.args_.size(); ++i) {
                size += CodedOutputStream.computeMessageSize(8, this.args_.get(i));
            }
            this.memoizedSerializedSize = size += this.getUnknownFields().getSerializedSize();
            return size;
        }

        @Override
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static Update parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static Update parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static Update parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static Update parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static Update parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static Update parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Update parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static Update parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static Update parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static Update parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override
        public Builder newBuilderForType() {
            return Update.newBuilder();
        }

        public static Builder newBuilder(Update prototype) {
            return Update.newBuilder().mergeFrom(prototype);
        }

        @Override
        public Builder toBuilder() {
            return Update.newBuilder(this);
        }

        @Override
        protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        static {
            PARSER = new AbstractParser<Update>(){

                @Override
                public Update parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                    return new Update(input, extensionRegistry);
                }
            };
            defaultInstance = new Update(true);
            defaultInstance.initFields();
        }

        public static final class Builder
        extends GeneratedMessage.Builder<Builder>
        implements UpdateOrBuilder {
            private int bitField0_;
            private Collection collection_ = Collection.getDefaultInstance();
            private SingleFieldBuilder<Collection, Collection.Builder, CollectionOrBuilder> collectionBuilder_;
            private DataModel dataModel_ = DataModel.DOCUMENT;
            private MysqlxExpr.Expr criteria_ = MysqlxExpr.Expr.getDefaultInstance();
            private SingleFieldBuilder<MysqlxExpr.Expr, MysqlxExpr.Expr.Builder, MysqlxExpr.ExprOrBuilder> criteriaBuilder_;
            private List<MysqlxDatatypes.Scalar> args_ = Collections.emptyList();
            private RepeatedFieldBuilder<MysqlxDatatypes.Scalar, MysqlxDatatypes.Scalar.Builder, MysqlxDatatypes.ScalarOrBuilder> argsBuilder_;
            private Limit limit_ = Limit.getDefaultInstance();
            private SingleFieldBuilder<Limit, Limit.Builder, LimitOrBuilder> limitBuilder_;
            private List<Order> order_ = Collections.emptyList();
            private RepeatedFieldBuilder<Order, Order.Builder, OrderOrBuilder> orderBuilder_;
            private List<UpdateOperation> operation_ = Collections.emptyList();
            private RepeatedFieldBuilder<UpdateOperation, UpdateOperation.Builder, UpdateOperationOrBuilder> operationBuilder_;

            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_Mysqlx_Crud_Update_descriptor;
            }

            @Override
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_Mysqlx_Crud_Update_fieldAccessorTable.ensureFieldAccessorsInitialized(Update.class, Builder.class);
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
                    this.getCollectionFieldBuilder();
                    this.getCriteriaFieldBuilder();
                    this.getArgsFieldBuilder();
                    this.getLimitFieldBuilder();
                    this.getOrderFieldBuilder();
                    this.getOperationFieldBuilder();
                }
            }

            private static Builder create() {
                return new Builder();
            }

            @Override
            public Builder clear() {
                super.clear();
                if (this.collectionBuilder_ == null) {
                    this.collection_ = Collection.getDefaultInstance();
                } else {
                    this.collectionBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFFFE;
                this.dataModel_ = DataModel.DOCUMENT;
                this.bitField0_ &= 0xFFFFFFFD;
                if (this.criteriaBuilder_ == null) {
                    this.criteria_ = MysqlxExpr.Expr.getDefaultInstance();
                } else {
                    this.criteriaBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFFFB;
                if (this.argsBuilder_ == null) {
                    this.args_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFFFF7;
                } else {
                    this.argsBuilder_.clear();
                }
                if (this.limitBuilder_ == null) {
                    this.limit_ = Limit.getDefaultInstance();
                } else {
                    this.limitBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFFEF;
                if (this.orderBuilder_ == null) {
                    this.order_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFFFDF;
                } else {
                    this.orderBuilder_.clear();
                }
                if (this.operationBuilder_ == null) {
                    this.operation_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFFFBF;
                } else {
                    this.operationBuilder_.clear();
                }
                return this;
            }

            @Override
            public Builder clone() {
                return Builder.create().mergeFrom(this.buildPartial());
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return internal_static_Mysqlx_Crud_Update_descriptor;
            }

            @Override
            public Update getDefaultInstanceForType() {
                return Update.getDefaultInstance();
            }

            @Override
            public Update build() {
                Update result = this.buildPartial();
                if (!result.isInitialized()) {
                    throw Builder.newUninitializedMessageException(result);
                }
                return result;
            }

            @Override
            public Update buildPartial() {
                Update result = new Update(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ |= 1;
                }
                if (this.collectionBuilder_ == null) {
                    result.collection_ = this.collection_;
                } else {
                    result.collection_ = this.collectionBuilder_.build();
                }
                if ((from_bitField0_ & 2) == 2) {
                    to_bitField0_ |= 2;
                }
                result.dataModel_ = this.dataModel_;
                if ((from_bitField0_ & 4) == 4) {
                    to_bitField0_ |= 4;
                }
                if (this.criteriaBuilder_ == null) {
                    result.criteria_ = this.criteria_;
                } else {
                    result.criteria_ = this.criteriaBuilder_.build();
                }
                if (this.argsBuilder_ == null) {
                    if ((this.bitField0_ & 8) == 8) {
                        this.args_ = Collections.unmodifiableList(this.args_);
                        this.bitField0_ &= 0xFFFFFFF7;
                    }
                    result.args_ = this.args_;
                } else {
                    result.args_ = this.argsBuilder_.build();
                }
                if ((from_bitField0_ & 0x10) == 16) {
                    to_bitField0_ |= 8;
                }
                if (this.limitBuilder_ == null) {
                    result.limit_ = this.limit_;
                } else {
                    result.limit_ = this.limitBuilder_.build();
                }
                if (this.orderBuilder_ == null) {
                    if ((this.bitField0_ & 0x20) == 32) {
                        this.order_ = Collections.unmodifiableList(this.order_);
                        this.bitField0_ &= 0xFFFFFFDF;
                    }
                    result.order_ = this.order_;
                } else {
                    result.order_ = this.orderBuilder_.build();
                }
                if (this.operationBuilder_ == null) {
                    if ((this.bitField0_ & 0x40) == 64) {
                        this.operation_ = Collections.unmodifiableList(this.operation_);
                        this.bitField0_ &= 0xFFFFFFBF;
                    }
                    result.operation_ = this.operation_;
                } else {
                    result.operation_ = this.operationBuilder_.build();
                }
                result.bitField0_ = to_bitField0_;
                this.onBuilt();
                return result;
            }

            @Override
            public Builder mergeFrom(Message other) {
                if (other instanceof Update) {
                    return this.mergeFrom((Update)other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(Update other) {
                if (other == Update.getDefaultInstance()) {
                    return this;
                }
                if (other.hasCollection()) {
                    this.mergeCollection(other.getCollection());
                }
                if (other.hasDataModel()) {
                    this.setDataModel(other.getDataModel());
                }
                if (other.hasCriteria()) {
                    this.mergeCriteria(other.getCriteria());
                }
                if (this.argsBuilder_ == null) {
                    if (!other.args_.isEmpty()) {
                        if (this.args_.isEmpty()) {
                            this.args_ = other.args_;
                            this.bitField0_ &= 0xFFFFFFF7;
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
                        this.bitField0_ &= 0xFFFFFFF7;
                        this.argsBuilder_ = alwaysUseFieldBuilders ? this.getArgsFieldBuilder() : null;
                    } else {
                        this.argsBuilder_.addAllMessages(other.args_);
                    }
                }
                if (other.hasLimit()) {
                    this.mergeLimit(other.getLimit());
                }
                if (this.orderBuilder_ == null) {
                    if (!other.order_.isEmpty()) {
                        if (this.order_.isEmpty()) {
                            this.order_ = other.order_;
                            this.bitField0_ &= 0xFFFFFFDF;
                        } else {
                            this.ensureOrderIsMutable();
                            this.order_.addAll(other.order_);
                        }
                        this.onChanged();
                    }
                } else if (!other.order_.isEmpty()) {
                    if (this.orderBuilder_.isEmpty()) {
                        this.orderBuilder_.dispose();
                        this.orderBuilder_ = null;
                        this.order_ = other.order_;
                        this.bitField0_ &= 0xFFFFFFDF;
                        this.orderBuilder_ = alwaysUseFieldBuilders ? this.getOrderFieldBuilder() : null;
                    } else {
                        this.orderBuilder_.addAllMessages(other.order_);
                    }
                }
                if (this.operationBuilder_ == null) {
                    if (!other.operation_.isEmpty()) {
                        if (this.operation_.isEmpty()) {
                            this.operation_ = other.operation_;
                            this.bitField0_ &= 0xFFFFFFBF;
                        } else {
                            this.ensureOperationIsMutable();
                            this.operation_.addAll(other.operation_);
                        }
                        this.onChanged();
                    }
                } else if (!other.operation_.isEmpty()) {
                    if (this.operationBuilder_.isEmpty()) {
                        this.operationBuilder_.dispose();
                        this.operationBuilder_ = null;
                        this.operation_ = other.operation_;
                        this.bitField0_ &= 0xFFFFFFBF;
                        this.operationBuilder_ = alwaysUseFieldBuilders ? this.getOperationFieldBuilder() : null;
                    } else {
                        this.operationBuilder_.addAllMessages(other.operation_);
                    }
                }
                this.mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            @Override
            public final boolean isInitialized() {
                int i;
                if (!this.hasCollection()) {
                    return false;
                }
                if (!this.getCollection().isInitialized()) {
                    return false;
                }
                if (this.hasCriteria() && !this.getCriteria().isInitialized()) {
                    return false;
                }
                for (i = 0; i < this.getArgsCount(); ++i) {
                    if (this.getArgs(i).isInitialized()) continue;
                    return false;
                }
                if (this.hasLimit() && !this.getLimit().isInitialized()) {
                    return false;
                }
                for (i = 0; i < this.getOrderCount(); ++i) {
                    if (this.getOrder(i).isInitialized()) continue;
                    return false;
                }
                for (i = 0; i < this.getOperationCount(); ++i) {
                    if (this.getOperation(i).isInitialized()) continue;
                    return false;
                }
                return true;
            }

            @Override
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                Update parsedMessage = null;
                try {
                    parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
                } catch (InvalidProtocolBufferException e) {
                    parsedMessage = (Update)e.getUnfinishedMessage();
                    throw e;
                } finally {
                    if (parsedMessage != null) {
                        this.mergeFrom(parsedMessage);
                    }
                }
                return this;
            }

            @Override
            public boolean hasCollection() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override
            public Collection getCollection() {
                if (this.collectionBuilder_ == null) {
                    return this.collection_;
                }
                return this.collectionBuilder_.getMessage();
            }

            public Builder setCollection(Collection value) {
                if (this.collectionBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.collection_ = value;
                    this.onChanged();
                } else {
                    this.collectionBuilder_.setMessage(value);
                }
                this.bitField0_ |= 1;
                return this;
            }

            public Builder setCollection(Collection.Builder builderForValue) {
                if (this.collectionBuilder_ == null) {
                    this.collection_ = builderForValue.build();
                    this.onChanged();
                } else {
                    this.collectionBuilder_.setMessage(builderForValue.build());
                }
                this.bitField0_ |= 1;
                return this;
            }

            public Builder mergeCollection(Collection value) {
                if (this.collectionBuilder_ == null) {
                    this.collection_ = (this.bitField0_ & 1) == 1 && this.collection_ != Collection.getDefaultInstance() ? Collection.newBuilder(this.collection_).mergeFrom(value).buildPartial() : value;
                    this.onChanged();
                } else {
                    this.collectionBuilder_.mergeFrom(value);
                }
                this.bitField0_ |= 1;
                return this;
            }

            public Builder clearCollection() {
                if (this.collectionBuilder_ == null) {
                    this.collection_ = Collection.getDefaultInstance();
                    this.onChanged();
                } else {
                    this.collectionBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFFFE;
                return this;
            }

            public Collection.Builder getCollectionBuilder() {
                this.bitField0_ |= 1;
                this.onChanged();
                return this.getCollectionFieldBuilder().getBuilder();
            }

            @Override
            public CollectionOrBuilder getCollectionOrBuilder() {
                if (this.collectionBuilder_ != null) {
                    return this.collectionBuilder_.getMessageOrBuilder();
                }
                return this.collection_;
            }

            private SingleFieldBuilder<Collection, Collection.Builder, CollectionOrBuilder> getCollectionFieldBuilder() {
                if (this.collectionBuilder_ == null) {
                    this.collectionBuilder_ = new SingleFieldBuilder(this.getCollection(), this.getParentForChildren(), this.isClean());
                    this.collection_ = null;
                }
                return this.collectionBuilder_;
            }

            @Override
            public boolean hasDataModel() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override
            public DataModel getDataModel() {
                return this.dataModel_;
            }

            public Builder setDataModel(DataModel value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 2;
                this.dataModel_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearDataModel() {
                this.bitField0_ &= 0xFFFFFFFD;
                this.dataModel_ = DataModel.DOCUMENT;
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasCriteria() {
                return (this.bitField0_ & 4) == 4;
            }

            @Override
            public MysqlxExpr.Expr getCriteria() {
                if (this.criteriaBuilder_ == null) {
                    return this.criteria_;
                }
                return this.criteriaBuilder_.getMessage();
            }

            public Builder setCriteria(MysqlxExpr.Expr value) {
                if (this.criteriaBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.criteria_ = value;
                    this.onChanged();
                } else {
                    this.criteriaBuilder_.setMessage(value);
                }
                this.bitField0_ |= 4;
                return this;
            }

            public Builder setCriteria(MysqlxExpr.Expr.Builder builderForValue) {
                if (this.criteriaBuilder_ == null) {
                    this.criteria_ = builderForValue.build();
                    this.onChanged();
                } else {
                    this.criteriaBuilder_.setMessage(builderForValue.build());
                }
                this.bitField0_ |= 4;
                return this;
            }

            public Builder mergeCriteria(MysqlxExpr.Expr value) {
                if (this.criteriaBuilder_ == null) {
                    this.criteria_ = (this.bitField0_ & 4) == 4 && this.criteria_ != MysqlxExpr.Expr.getDefaultInstance() ? MysqlxExpr.Expr.newBuilder(this.criteria_).mergeFrom(value).buildPartial() : value;
                    this.onChanged();
                } else {
                    this.criteriaBuilder_.mergeFrom(value);
                }
                this.bitField0_ |= 4;
                return this;
            }

            public Builder clearCriteria() {
                if (this.criteriaBuilder_ == null) {
                    this.criteria_ = MysqlxExpr.Expr.getDefaultInstance();
                    this.onChanged();
                } else {
                    this.criteriaBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFFFB;
                return this;
            }

            public MysqlxExpr.Expr.Builder getCriteriaBuilder() {
                this.bitField0_ |= 4;
                this.onChanged();
                return this.getCriteriaFieldBuilder().getBuilder();
            }

            @Override
            public MysqlxExpr.ExprOrBuilder getCriteriaOrBuilder() {
                if (this.criteriaBuilder_ != null) {
                    return this.criteriaBuilder_.getMessageOrBuilder();
                }
                return this.criteria_;
            }

            private SingleFieldBuilder<MysqlxExpr.Expr, MysqlxExpr.Expr.Builder, MysqlxExpr.ExprOrBuilder> getCriteriaFieldBuilder() {
                if (this.criteriaBuilder_ == null) {
                    this.criteriaBuilder_ = new SingleFieldBuilder(this.getCriteria(), this.getParentForChildren(), this.isClean());
                    this.criteria_ = null;
                }
                return this.criteriaBuilder_;
            }

            private void ensureArgsIsMutable() {
                if ((this.bitField0_ & 8) != 8) {
                    this.args_ = new ArrayList<MysqlxDatatypes.Scalar>(this.args_);
                    this.bitField0_ |= 8;
                }
            }

            @Override
            public List<MysqlxDatatypes.Scalar> getArgsList() {
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
            public MysqlxDatatypes.Scalar getArgs(int index) {
                if (this.argsBuilder_ == null) {
                    return this.args_.get(index);
                }
                return this.argsBuilder_.getMessage(index);
            }

            public Builder setArgs(int index, MysqlxDatatypes.Scalar value) {
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

            public Builder setArgs(int index, MysqlxDatatypes.Scalar.Builder builderForValue) {
                if (this.argsBuilder_ == null) {
                    this.ensureArgsIsMutable();
                    this.args_.set(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.argsBuilder_.setMessage(index, builderForValue.build());
                }
                return this;
            }

            public Builder addArgs(MysqlxDatatypes.Scalar value) {
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

            public Builder addArgs(int index, MysqlxDatatypes.Scalar value) {
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

            public Builder addArgs(MysqlxDatatypes.Scalar.Builder builderForValue) {
                if (this.argsBuilder_ == null) {
                    this.ensureArgsIsMutable();
                    this.args_.add(builderForValue.build());
                    this.onChanged();
                } else {
                    this.argsBuilder_.addMessage(builderForValue.build());
                }
                return this;
            }

            public Builder addArgs(int index, MysqlxDatatypes.Scalar.Builder builderForValue) {
                if (this.argsBuilder_ == null) {
                    this.ensureArgsIsMutable();
                    this.args_.add(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.argsBuilder_.addMessage(index, builderForValue.build());
                }
                return this;
            }

            public Builder addAllArgs(Iterable<? extends MysqlxDatatypes.Scalar> values2) {
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
                    this.bitField0_ &= 0xFFFFFFF7;
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

            public MysqlxDatatypes.Scalar.Builder getArgsBuilder(int index) {
                return this.getArgsFieldBuilder().getBuilder(index);
            }

            @Override
            public MysqlxDatatypes.ScalarOrBuilder getArgsOrBuilder(int index) {
                if (this.argsBuilder_ == null) {
                    return this.args_.get(index);
                }
                return this.argsBuilder_.getMessageOrBuilder(index);
            }

            @Override
            public List<? extends MysqlxDatatypes.ScalarOrBuilder> getArgsOrBuilderList() {
                if (this.argsBuilder_ != null) {
                    return this.argsBuilder_.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.args_);
            }

            public MysqlxDatatypes.Scalar.Builder addArgsBuilder() {
                return this.getArgsFieldBuilder().addBuilder(MysqlxDatatypes.Scalar.getDefaultInstance());
            }

            public MysqlxDatatypes.Scalar.Builder addArgsBuilder(int index) {
                return this.getArgsFieldBuilder().addBuilder(index, MysqlxDatatypes.Scalar.getDefaultInstance());
            }

            public List<MysqlxDatatypes.Scalar.Builder> getArgsBuilderList() {
                return this.getArgsFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilder<MysqlxDatatypes.Scalar, MysqlxDatatypes.Scalar.Builder, MysqlxDatatypes.ScalarOrBuilder> getArgsFieldBuilder() {
                if (this.argsBuilder_ == null) {
                    this.argsBuilder_ = new RepeatedFieldBuilder(this.args_, (this.bitField0_ & 8) == 8, this.getParentForChildren(), this.isClean());
                    this.args_ = null;
                }
                return this.argsBuilder_;
            }

            @Override
            public boolean hasLimit() {
                return (this.bitField0_ & 0x10) == 16;
            }

            @Override
            public Limit getLimit() {
                if (this.limitBuilder_ == null) {
                    return this.limit_;
                }
                return this.limitBuilder_.getMessage();
            }

            public Builder setLimit(Limit value) {
                if (this.limitBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.limit_ = value;
                    this.onChanged();
                } else {
                    this.limitBuilder_.setMessage(value);
                }
                this.bitField0_ |= 0x10;
                return this;
            }

            public Builder setLimit(Limit.Builder builderForValue) {
                if (this.limitBuilder_ == null) {
                    this.limit_ = builderForValue.build();
                    this.onChanged();
                } else {
                    this.limitBuilder_.setMessage(builderForValue.build());
                }
                this.bitField0_ |= 0x10;
                return this;
            }

            public Builder mergeLimit(Limit value) {
                if (this.limitBuilder_ == null) {
                    this.limit_ = (this.bitField0_ & 0x10) == 16 && this.limit_ != Limit.getDefaultInstance() ? Limit.newBuilder(this.limit_).mergeFrom(value).buildPartial() : value;
                    this.onChanged();
                } else {
                    this.limitBuilder_.mergeFrom(value);
                }
                this.bitField0_ |= 0x10;
                return this;
            }

            public Builder clearLimit() {
                if (this.limitBuilder_ == null) {
                    this.limit_ = Limit.getDefaultInstance();
                    this.onChanged();
                } else {
                    this.limitBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFFEF;
                return this;
            }

            public Limit.Builder getLimitBuilder() {
                this.bitField0_ |= 0x10;
                this.onChanged();
                return this.getLimitFieldBuilder().getBuilder();
            }

            @Override
            public LimitOrBuilder getLimitOrBuilder() {
                if (this.limitBuilder_ != null) {
                    return this.limitBuilder_.getMessageOrBuilder();
                }
                return this.limit_;
            }

            private SingleFieldBuilder<Limit, Limit.Builder, LimitOrBuilder> getLimitFieldBuilder() {
                if (this.limitBuilder_ == null) {
                    this.limitBuilder_ = new SingleFieldBuilder(this.getLimit(), this.getParentForChildren(), this.isClean());
                    this.limit_ = null;
                }
                return this.limitBuilder_;
            }

            private void ensureOrderIsMutable() {
                if ((this.bitField0_ & 0x20) != 32) {
                    this.order_ = new ArrayList<Order>(this.order_);
                    this.bitField0_ |= 0x20;
                }
            }

            @Override
            public List<Order> getOrderList() {
                if (this.orderBuilder_ == null) {
                    return Collections.unmodifiableList(this.order_);
                }
                return this.orderBuilder_.getMessageList();
            }

            @Override
            public int getOrderCount() {
                if (this.orderBuilder_ == null) {
                    return this.order_.size();
                }
                return this.orderBuilder_.getCount();
            }

            @Override
            public Order getOrder(int index) {
                if (this.orderBuilder_ == null) {
                    return this.order_.get(index);
                }
                return this.orderBuilder_.getMessage(index);
            }

            public Builder setOrder(int index, Order value) {
                if (this.orderBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureOrderIsMutable();
                    this.order_.set(index, value);
                    this.onChanged();
                } else {
                    this.orderBuilder_.setMessage(index, value);
                }
                return this;
            }

            public Builder setOrder(int index, Order.Builder builderForValue) {
                if (this.orderBuilder_ == null) {
                    this.ensureOrderIsMutable();
                    this.order_.set(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.orderBuilder_.setMessage(index, builderForValue.build());
                }
                return this;
            }

            public Builder addOrder(Order value) {
                if (this.orderBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureOrderIsMutable();
                    this.order_.add(value);
                    this.onChanged();
                } else {
                    this.orderBuilder_.addMessage(value);
                }
                return this;
            }

            public Builder addOrder(int index, Order value) {
                if (this.orderBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureOrderIsMutable();
                    this.order_.add(index, value);
                    this.onChanged();
                } else {
                    this.orderBuilder_.addMessage(index, value);
                }
                return this;
            }

            public Builder addOrder(Order.Builder builderForValue) {
                if (this.orderBuilder_ == null) {
                    this.ensureOrderIsMutable();
                    this.order_.add(builderForValue.build());
                    this.onChanged();
                } else {
                    this.orderBuilder_.addMessage(builderForValue.build());
                }
                return this;
            }

            public Builder addOrder(int index, Order.Builder builderForValue) {
                if (this.orderBuilder_ == null) {
                    this.ensureOrderIsMutable();
                    this.order_.add(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.orderBuilder_.addMessage(index, builderForValue.build());
                }
                return this;
            }

            public Builder addAllOrder(Iterable<? extends Order> values2) {
                if (this.orderBuilder_ == null) {
                    this.ensureOrderIsMutable();
                    AbstractMessageLite.Builder.addAll(values2, this.order_);
                    this.onChanged();
                } else {
                    this.orderBuilder_.addAllMessages(values2);
                }
                return this;
            }

            public Builder clearOrder() {
                if (this.orderBuilder_ == null) {
                    this.order_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFFFDF;
                    this.onChanged();
                } else {
                    this.orderBuilder_.clear();
                }
                return this;
            }

            public Builder removeOrder(int index) {
                if (this.orderBuilder_ == null) {
                    this.ensureOrderIsMutable();
                    this.order_.remove(index);
                    this.onChanged();
                } else {
                    this.orderBuilder_.remove(index);
                }
                return this;
            }

            public Order.Builder getOrderBuilder(int index) {
                return this.getOrderFieldBuilder().getBuilder(index);
            }

            @Override
            public OrderOrBuilder getOrderOrBuilder(int index) {
                if (this.orderBuilder_ == null) {
                    return this.order_.get(index);
                }
                return this.orderBuilder_.getMessageOrBuilder(index);
            }

            @Override
            public List<? extends OrderOrBuilder> getOrderOrBuilderList() {
                if (this.orderBuilder_ != null) {
                    return this.orderBuilder_.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.order_);
            }

            public Order.Builder addOrderBuilder() {
                return this.getOrderFieldBuilder().addBuilder(Order.getDefaultInstance());
            }

            public Order.Builder addOrderBuilder(int index) {
                return this.getOrderFieldBuilder().addBuilder(index, Order.getDefaultInstance());
            }

            public List<Order.Builder> getOrderBuilderList() {
                return this.getOrderFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilder<Order, Order.Builder, OrderOrBuilder> getOrderFieldBuilder() {
                if (this.orderBuilder_ == null) {
                    this.orderBuilder_ = new RepeatedFieldBuilder(this.order_, (this.bitField0_ & 0x20) == 32, this.getParentForChildren(), this.isClean());
                    this.order_ = null;
                }
                return this.orderBuilder_;
            }

            private void ensureOperationIsMutable() {
                if ((this.bitField0_ & 0x40) != 64) {
                    this.operation_ = new ArrayList<UpdateOperation>(this.operation_);
                    this.bitField0_ |= 0x40;
                }
            }

            @Override
            public List<UpdateOperation> getOperationList() {
                if (this.operationBuilder_ == null) {
                    return Collections.unmodifiableList(this.operation_);
                }
                return this.operationBuilder_.getMessageList();
            }

            @Override
            public int getOperationCount() {
                if (this.operationBuilder_ == null) {
                    return this.operation_.size();
                }
                return this.operationBuilder_.getCount();
            }

            @Override
            public UpdateOperation getOperation(int index) {
                if (this.operationBuilder_ == null) {
                    return this.operation_.get(index);
                }
                return this.operationBuilder_.getMessage(index);
            }

            public Builder setOperation(int index, UpdateOperation value) {
                if (this.operationBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureOperationIsMutable();
                    this.operation_.set(index, value);
                    this.onChanged();
                } else {
                    this.operationBuilder_.setMessage(index, value);
                }
                return this;
            }

            public Builder setOperation(int index, UpdateOperation.Builder builderForValue) {
                if (this.operationBuilder_ == null) {
                    this.ensureOperationIsMutable();
                    this.operation_.set(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.operationBuilder_.setMessage(index, builderForValue.build());
                }
                return this;
            }

            public Builder addOperation(UpdateOperation value) {
                if (this.operationBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureOperationIsMutable();
                    this.operation_.add(value);
                    this.onChanged();
                } else {
                    this.operationBuilder_.addMessage(value);
                }
                return this;
            }

            public Builder addOperation(int index, UpdateOperation value) {
                if (this.operationBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureOperationIsMutable();
                    this.operation_.add(index, value);
                    this.onChanged();
                } else {
                    this.operationBuilder_.addMessage(index, value);
                }
                return this;
            }

            public Builder addOperation(UpdateOperation.Builder builderForValue) {
                if (this.operationBuilder_ == null) {
                    this.ensureOperationIsMutable();
                    this.operation_.add(builderForValue.build());
                    this.onChanged();
                } else {
                    this.operationBuilder_.addMessage(builderForValue.build());
                }
                return this;
            }

            public Builder addOperation(int index, UpdateOperation.Builder builderForValue) {
                if (this.operationBuilder_ == null) {
                    this.ensureOperationIsMutable();
                    this.operation_.add(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.operationBuilder_.addMessage(index, builderForValue.build());
                }
                return this;
            }

            public Builder addAllOperation(Iterable<? extends UpdateOperation> values2) {
                if (this.operationBuilder_ == null) {
                    this.ensureOperationIsMutable();
                    AbstractMessageLite.Builder.addAll(values2, this.operation_);
                    this.onChanged();
                } else {
                    this.operationBuilder_.addAllMessages(values2);
                }
                return this;
            }

            public Builder clearOperation() {
                if (this.operationBuilder_ == null) {
                    this.operation_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFFFBF;
                    this.onChanged();
                } else {
                    this.operationBuilder_.clear();
                }
                return this;
            }

            public Builder removeOperation(int index) {
                if (this.operationBuilder_ == null) {
                    this.ensureOperationIsMutable();
                    this.operation_.remove(index);
                    this.onChanged();
                } else {
                    this.operationBuilder_.remove(index);
                }
                return this;
            }

            public UpdateOperation.Builder getOperationBuilder(int index) {
                return this.getOperationFieldBuilder().getBuilder(index);
            }

            @Override
            public UpdateOperationOrBuilder getOperationOrBuilder(int index) {
                if (this.operationBuilder_ == null) {
                    return this.operation_.get(index);
                }
                return this.operationBuilder_.getMessageOrBuilder(index);
            }

            @Override
            public List<? extends UpdateOperationOrBuilder> getOperationOrBuilderList() {
                if (this.operationBuilder_ != null) {
                    return this.operationBuilder_.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.operation_);
            }

            public UpdateOperation.Builder addOperationBuilder() {
                return this.getOperationFieldBuilder().addBuilder(UpdateOperation.getDefaultInstance());
            }

            public UpdateOperation.Builder addOperationBuilder(int index) {
                return this.getOperationFieldBuilder().addBuilder(index, UpdateOperation.getDefaultInstance());
            }

            public List<UpdateOperation.Builder> getOperationBuilderList() {
                return this.getOperationFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilder<UpdateOperation, UpdateOperation.Builder, UpdateOperationOrBuilder> getOperationFieldBuilder() {
                if (this.operationBuilder_ == null) {
                    this.operationBuilder_ = new RepeatedFieldBuilder(this.operation_, (this.bitField0_ & 0x40) == 64, this.getParentForChildren(), this.isClean());
                    this.operation_ = null;
                }
                return this.operationBuilder_;
            }
        }
    }

    public static interface UpdateOrBuilder
    extends MessageOrBuilder {
        public boolean hasCollection();

        public Collection getCollection();

        public CollectionOrBuilder getCollectionOrBuilder();

        public boolean hasDataModel();

        public DataModel getDataModel();

        public boolean hasCriteria();

        public MysqlxExpr.Expr getCriteria();

        public MysqlxExpr.ExprOrBuilder getCriteriaOrBuilder();

        public List<MysqlxDatatypes.Scalar> getArgsList();

        public MysqlxDatatypes.Scalar getArgs(int var1);

        public int getArgsCount();

        public List<? extends MysqlxDatatypes.ScalarOrBuilder> getArgsOrBuilderList();

        public MysqlxDatatypes.ScalarOrBuilder getArgsOrBuilder(int var1);

        public boolean hasLimit();

        public Limit getLimit();

        public LimitOrBuilder getLimitOrBuilder();

        public List<Order> getOrderList();

        public Order getOrder(int var1);

        public int getOrderCount();

        public List<? extends OrderOrBuilder> getOrderOrBuilderList();

        public OrderOrBuilder getOrderOrBuilder(int var1);

        public List<UpdateOperation> getOperationList();

        public UpdateOperation getOperation(int var1);

        public int getOperationCount();

        public List<? extends UpdateOperationOrBuilder> getOperationOrBuilderList();

        public UpdateOperationOrBuilder getOperationOrBuilder(int var1);
    }

    public static final class Insert
    extends GeneratedMessage
    implements InsertOrBuilder {
        private static final Insert defaultInstance;
        private final UnknownFieldSet unknownFields;
        public static Parser<Insert> PARSER;
        private int bitField0_;
        public static final int COLLECTION_FIELD_NUMBER = 1;
        private Collection collection_;
        public static final int DATA_MODEL_FIELD_NUMBER = 2;
        private DataModel dataModel_;
        public static final int PROJECTION_FIELD_NUMBER = 3;
        private List<Column> projection_;
        public static final int ROW_FIELD_NUMBER = 4;
        private List<TypedRow> row_;
        public static final int ARGS_FIELD_NUMBER = 5;
        private List<MysqlxDatatypes.Scalar> args_;
        public static final int UPSERT_FIELD_NUMBER = 6;
        private boolean upsert_;
        private byte memoizedIsInitialized = (byte)-1;
        private int memoizedSerializedSize = -1;
        private static final long serialVersionUID = 0L;

        private Insert(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.unknownFields = builder.getUnknownFields();
        }

        private Insert(boolean noInit) {
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static Insert getDefaultInstance() {
            return defaultInstance;
        }

        @Override
        public Insert getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private Insert(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this.initFields();
            int mutable_bitField0_ = 0;
            UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
            try {
                boolean done = false;
                block15: while (!done) {
                    int tag = input.readTag();
                    switch (tag) {
                        case 0: {
                            done = true;
                            continue block15;
                        }
                        default: {
                            if (this.parseUnknownField(input, unknownFields, extensionRegistry, tag)) continue block15;
                            done = true;
                            continue block15;
                        }
                        case 10: {
                            Collection.Builder subBuilder = null;
                            if ((this.bitField0_ & 1) == 1) {
                                subBuilder = this.collection_.toBuilder();
                            }
                            this.collection_ = input.readMessage(Collection.PARSER, extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom(this.collection_);
                                this.collection_ = subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 1;
                            continue block15;
                        }
                        case 16: {
                            int rawValue = input.readEnum();
                            DataModel value = DataModel.valueOf(rawValue);
                            if (value == null) {
                                unknownFields.mergeVarintField(2, rawValue);
                                continue block15;
                            }
                            this.bitField0_ |= 2;
                            this.dataModel_ = value;
                            continue block15;
                        }
                        case 26: {
                            if ((mutable_bitField0_ & 4) != 4) {
                                this.projection_ = new ArrayList<Column>();
                                mutable_bitField0_ |= 4;
                            }
                            this.projection_.add(input.readMessage(Column.PARSER, extensionRegistry));
                            continue block15;
                        }
                        case 34: {
                            if ((mutable_bitField0_ & 8) != 8) {
                                this.row_ = new ArrayList<TypedRow>();
                                mutable_bitField0_ |= 8;
                            }
                            this.row_.add(input.readMessage(TypedRow.PARSER, extensionRegistry));
                            continue block15;
                        }
                        case 42: {
                            if ((mutable_bitField0_ & 0x10) != 16) {
                                this.args_ = new ArrayList<MysqlxDatatypes.Scalar>();
                                mutable_bitField0_ |= 0x10;
                            }
                            this.args_.add(input.readMessage(MysqlxDatatypes.Scalar.PARSER, extensionRegistry));
                            continue block15;
                        }
                        case 48: 
                    }
                    this.bitField0_ |= 4;
                    this.upsert_ = input.readBool();
                }
            } catch (InvalidProtocolBufferException e) {
                throw e.setUnfinishedMessage(this);
            } catch (IOException e) {
                throw new InvalidProtocolBufferException(e.getMessage()).setUnfinishedMessage(this);
            } finally {
                if ((mutable_bitField0_ & 4) == 4) {
                    this.projection_ = Collections.unmodifiableList(this.projection_);
                }
                if ((mutable_bitField0_ & 8) == 8) {
                    this.row_ = Collections.unmodifiableList(this.row_);
                }
                if ((mutable_bitField0_ & 0x10) == 16) {
                    this.args_ = Collections.unmodifiableList(this.args_);
                }
                this.unknownFields = unknownFields.build();
                this.makeExtensionsImmutable();
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return internal_static_Mysqlx_Crud_Insert_descriptor;
        }

        @Override
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return internal_static_Mysqlx_Crud_Insert_fieldAccessorTable.ensureFieldAccessorsInitialized(Insert.class, Builder.class);
        }

        public Parser<Insert> getParserForType() {
            return PARSER;
        }

        @Override
        public boolean hasCollection() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override
        public Collection getCollection() {
            return this.collection_;
        }

        @Override
        public CollectionOrBuilder getCollectionOrBuilder() {
            return this.collection_;
        }

        @Override
        public boolean hasDataModel() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override
        public DataModel getDataModel() {
            return this.dataModel_;
        }

        @Override
        public List<Column> getProjectionList() {
            return this.projection_;
        }

        @Override
        public List<? extends ColumnOrBuilder> getProjectionOrBuilderList() {
            return this.projection_;
        }

        @Override
        public int getProjectionCount() {
            return this.projection_.size();
        }

        @Override
        public Column getProjection(int index) {
            return this.projection_.get(index);
        }

        @Override
        public ColumnOrBuilder getProjectionOrBuilder(int index) {
            return this.projection_.get(index);
        }

        @Override
        public List<TypedRow> getRowList() {
            return this.row_;
        }

        @Override
        public List<? extends TypedRowOrBuilder> getRowOrBuilderList() {
            return this.row_;
        }

        @Override
        public int getRowCount() {
            return this.row_.size();
        }

        @Override
        public TypedRow getRow(int index) {
            return this.row_.get(index);
        }

        @Override
        public TypedRowOrBuilder getRowOrBuilder(int index) {
            return this.row_.get(index);
        }

        @Override
        public List<MysqlxDatatypes.Scalar> getArgsList() {
            return this.args_;
        }

        @Override
        public List<? extends MysqlxDatatypes.ScalarOrBuilder> getArgsOrBuilderList() {
            return this.args_;
        }

        @Override
        public int getArgsCount() {
            return this.args_.size();
        }

        @Override
        public MysqlxDatatypes.Scalar getArgs(int index) {
            return this.args_.get(index);
        }

        @Override
        public MysqlxDatatypes.ScalarOrBuilder getArgsOrBuilder(int index) {
            return this.args_.get(index);
        }

        @Override
        public boolean hasUpsert() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override
        public boolean getUpsert() {
            return this.upsert_;
        }

        private void initFields() {
            this.collection_ = Collection.getDefaultInstance();
            this.dataModel_ = DataModel.DOCUMENT;
            this.projection_ = Collections.emptyList();
            this.row_ = Collections.emptyList();
            this.args_ = Collections.emptyList();
            this.upsert_ = false;
        }

        @Override
        public final boolean isInitialized() {
            int i;
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            if (!this.hasCollection()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            if (!this.getCollection().isInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            for (i = 0; i < this.getProjectionCount(); ++i) {
                if (this.getProjection(i).isInitialized()) continue;
                this.memoizedIsInitialized = 0;
                return false;
            }
            for (i = 0; i < this.getRowCount(); ++i) {
                if (this.getRow(i).isInitialized()) continue;
                this.memoizedIsInitialized = 0;
                return false;
            }
            for (i = 0; i < this.getArgsCount(); ++i) {
                if (this.getArgs(i).isInitialized()) continue;
                this.memoizedIsInitialized = 0;
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        @Override
        public void writeTo(CodedOutputStream output) throws IOException {
            int i;
            this.getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                output.writeMessage(1, this.collection_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeEnum(2, this.dataModel_.getNumber());
            }
            for (i = 0; i < this.projection_.size(); ++i) {
                output.writeMessage(3, this.projection_.get(i));
            }
            for (i = 0; i < this.row_.size(); ++i) {
                output.writeMessage(4, this.row_.get(i));
            }
            for (i = 0; i < this.args_.size(); ++i) {
                output.writeMessage(5, this.args_.get(i));
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeBool(6, this.upsert_);
            }
            this.getUnknownFields().writeTo(output);
        }

        @Override
        public int getSerializedSize() {
            int i;
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            size = 0;
            if ((this.bitField0_ & 1) == 1) {
                size += CodedOutputStream.computeMessageSize(1, this.collection_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size += CodedOutputStream.computeEnumSize(2, this.dataModel_.getNumber());
            }
            for (i = 0; i < this.projection_.size(); ++i) {
                size += CodedOutputStream.computeMessageSize(3, this.projection_.get(i));
            }
            for (i = 0; i < this.row_.size(); ++i) {
                size += CodedOutputStream.computeMessageSize(4, this.row_.get(i));
            }
            for (i = 0; i < this.args_.size(); ++i) {
                size += CodedOutputStream.computeMessageSize(5, this.args_.get(i));
            }
            if ((this.bitField0_ & 4) == 4) {
                size += CodedOutputStream.computeBoolSize(6, this.upsert_);
            }
            this.memoizedSerializedSize = size += this.getUnknownFields().getSerializedSize();
            return size;
        }

        @Override
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static Insert parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static Insert parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static Insert parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static Insert parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static Insert parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static Insert parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Insert parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static Insert parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static Insert parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static Insert parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override
        public Builder newBuilderForType() {
            return Insert.newBuilder();
        }

        public static Builder newBuilder(Insert prototype) {
            return Insert.newBuilder().mergeFrom(prototype);
        }

        @Override
        public Builder toBuilder() {
            return Insert.newBuilder(this);
        }

        @Override
        protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        static {
            PARSER = new AbstractParser<Insert>(){

                @Override
                public Insert parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                    return new Insert(input, extensionRegistry);
                }
            };
            defaultInstance = new Insert(true);
            defaultInstance.initFields();
        }

        public static final class Builder
        extends GeneratedMessage.Builder<Builder>
        implements InsertOrBuilder {
            private int bitField0_;
            private Collection collection_ = Collection.getDefaultInstance();
            private SingleFieldBuilder<Collection, Collection.Builder, CollectionOrBuilder> collectionBuilder_;
            private DataModel dataModel_ = DataModel.DOCUMENT;
            private List<Column> projection_ = Collections.emptyList();
            private RepeatedFieldBuilder<Column, Column.Builder, ColumnOrBuilder> projectionBuilder_;
            private List<TypedRow> row_ = Collections.emptyList();
            private RepeatedFieldBuilder<TypedRow, TypedRow.Builder, TypedRowOrBuilder> rowBuilder_;
            private List<MysqlxDatatypes.Scalar> args_ = Collections.emptyList();
            private RepeatedFieldBuilder<MysqlxDatatypes.Scalar, MysqlxDatatypes.Scalar.Builder, MysqlxDatatypes.ScalarOrBuilder> argsBuilder_;
            private boolean upsert_;

            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_Mysqlx_Crud_Insert_descriptor;
            }

            @Override
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_Mysqlx_Crud_Insert_fieldAccessorTable.ensureFieldAccessorsInitialized(Insert.class, Builder.class);
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
                    this.getCollectionFieldBuilder();
                    this.getProjectionFieldBuilder();
                    this.getRowFieldBuilder();
                    this.getArgsFieldBuilder();
                }
            }

            private static Builder create() {
                return new Builder();
            }

            @Override
            public Builder clear() {
                super.clear();
                if (this.collectionBuilder_ == null) {
                    this.collection_ = Collection.getDefaultInstance();
                } else {
                    this.collectionBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFFFE;
                this.dataModel_ = DataModel.DOCUMENT;
                this.bitField0_ &= 0xFFFFFFFD;
                if (this.projectionBuilder_ == null) {
                    this.projection_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFFFFB;
                } else {
                    this.projectionBuilder_.clear();
                }
                if (this.rowBuilder_ == null) {
                    this.row_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFFFF7;
                } else {
                    this.rowBuilder_.clear();
                }
                if (this.argsBuilder_ == null) {
                    this.args_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFFFEF;
                } else {
                    this.argsBuilder_.clear();
                }
                this.upsert_ = false;
                this.bitField0_ &= 0xFFFFFFDF;
                return this;
            }

            @Override
            public Builder clone() {
                return Builder.create().mergeFrom(this.buildPartial());
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return internal_static_Mysqlx_Crud_Insert_descriptor;
            }

            @Override
            public Insert getDefaultInstanceForType() {
                return Insert.getDefaultInstance();
            }

            @Override
            public Insert build() {
                Insert result = this.buildPartial();
                if (!result.isInitialized()) {
                    throw Builder.newUninitializedMessageException(result);
                }
                return result;
            }

            @Override
            public Insert buildPartial() {
                Insert result = new Insert(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ |= 1;
                }
                if (this.collectionBuilder_ == null) {
                    result.collection_ = this.collection_;
                } else {
                    result.collection_ = this.collectionBuilder_.build();
                }
                if ((from_bitField0_ & 2) == 2) {
                    to_bitField0_ |= 2;
                }
                result.dataModel_ = this.dataModel_;
                if (this.projectionBuilder_ == null) {
                    if ((this.bitField0_ & 4) == 4) {
                        this.projection_ = Collections.unmodifiableList(this.projection_);
                        this.bitField0_ &= 0xFFFFFFFB;
                    }
                    result.projection_ = this.projection_;
                } else {
                    result.projection_ = this.projectionBuilder_.build();
                }
                if (this.rowBuilder_ == null) {
                    if ((this.bitField0_ & 8) == 8) {
                        this.row_ = Collections.unmodifiableList(this.row_);
                        this.bitField0_ &= 0xFFFFFFF7;
                    }
                    result.row_ = this.row_;
                } else {
                    result.row_ = this.rowBuilder_.build();
                }
                if (this.argsBuilder_ == null) {
                    if ((this.bitField0_ & 0x10) == 16) {
                        this.args_ = Collections.unmodifiableList(this.args_);
                        this.bitField0_ &= 0xFFFFFFEF;
                    }
                    result.args_ = this.args_;
                } else {
                    result.args_ = this.argsBuilder_.build();
                }
                if ((from_bitField0_ & 0x20) == 32) {
                    to_bitField0_ |= 4;
                }
                result.upsert_ = this.upsert_;
                result.bitField0_ = to_bitField0_;
                this.onBuilt();
                return result;
            }

            @Override
            public Builder mergeFrom(Message other) {
                if (other instanceof Insert) {
                    return this.mergeFrom((Insert)other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(Insert other) {
                if (other == Insert.getDefaultInstance()) {
                    return this;
                }
                if (other.hasCollection()) {
                    this.mergeCollection(other.getCollection());
                }
                if (other.hasDataModel()) {
                    this.setDataModel(other.getDataModel());
                }
                if (this.projectionBuilder_ == null) {
                    if (!other.projection_.isEmpty()) {
                        if (this.projection_.isEmpty()) {
                            this.projection_ = other.projection_;
                            this.bitField0_ &= 0xFFFFFFFB;
                        } else {
                            this.ensureProjectionIsMutable();
                            this.projection_.addAll(other.projection_);
                        }
                        this.onChanged();
                    }
                } else if (!other.projection_.isEmpty()) {
                    if (this.projectionBuilder_.isEmpty()) {
                        this.projectionBuilder_.dispose();
                        this.projectionBuilder_ = null;
                        this.projection_ = other.projection_;
                        this.bitField0_ &= 0xFFFFFFFB;
                        this.projectionBuilder_ = alwaysUseFieldBuilders ? this.getProjectionFieldBuilder() : null;
                    } else {
                        this.projectionBuilder_.addAllMessages(other.projection_);
                    }
                }
                if (this.rowBuilder_ == null) {
                    if (!other.row_.isEmpty()) {
                        if (this.row_.isEmpty()) {
                            this.row_ = other.row_;
                            this.bitField0_ &= 0xFFFFFFF7;
                        } else {
                            this.ensureRowIsMutable();
                            this.row_.addAll(other.row_);
                        }
                        this.onChanged();
                    }
                } else if (!other.row_.isEmpty()) {
                    if (this.rowBuilder_.isEmpty()) {
                        this.rowBuilder_.dispose();
                        this.rowBuilder_ = null;
                        this.row_ = other.row_;
                        this.bitField0_ &= 0xFFFFFFF7;
                        this.rowBuilder_ = alwaysUseFieldBuilders ? this.getRowFieldBuilder() : null;
                    } else {
                        this.rowBuilder_.addAllMessages(other.row_);
                    }
                }
                if (this.argsBuilder_ == null) {
                    if (!other.args_.isEmpty()) {
                        if (this.args_.isEmpty()) {
                            this.args_ = other.args_;
                            this.bitField0_ &= 0xFFFFFFEF;
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
                        this.bitField0_ &= 0xFFFFFFEF;
                        this.argsBuilder_ = alwaysUseFieldBuilders ? this.getArgsFieldBuilder() : null;
                    } else {
                        this.argsBuilder_.addAllMessages(other.args_);
                    }
                }
                if (other.hasUpsert()) {
                    this.setUpsert(other.getUpsert());
                }
                this.mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            @Override
            public final boolean isInitialized() {
                int i;
                if (!this.hasCollection()) {
                    return false;
                }
                if (!this.getCollection().isInitialized()) {
                    return false;
                }
                for (i = 0; i < this.getProjectionCount(); ++i) {
                    if (this.getProjection(i).isInitialized()) continue;
                    return false;
                }
                for (i = 0; i < this.getRowCount(); ++i) {
                    if (this.getRow(i).isInitialized()) continue;
                    return false;
                }
                for (i = 0; i < this.getArgsCount(); ++i) {
                    if (this.getArgs(i).isInitialized()) continue;
                    return false;
                }
                return true;
            }

            @Override
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                Insert parsedMessage = null;
                try {
                    parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
                } catch (InvalidProtocolBufferException e) {
                    parsedMessage = (Insert)e.getUnfinishedMessage();
                    throw e;
                } finally {
                    if (parsedMessage != null) {
                        this.mergeFrom(parsedMessage);
                    }
                }
                return this;
            }

            @Override
            public boolean hasCollection() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override
            public Collection getCollection() {
                if (this.collectionBuilder_ == null) {
                    return this.collection_;
                }
                return this.collectionBuilder_.getMessage();
            }

            public Builder setCollection(Collection value) {
                if (this.collectionBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.collection_ = value;
                    this.onChanged();
                } else {
                    this.collectionBuilder_.setMessage(value);
                }
                this.bitField0_ |= 1;
                return this;
            }

            public Builder setCollection(Collection.Builder builderForValue) {
                if (this.collectionBuilder_ == null) {
                    this.collection_ = builderForValue.build();
                    this.onChanged();
                } else {
                    this.collectionBuilder_.setMessage(builderForValue.build());
                }
                this.bitField0_ |= 1;
                return this;
            }

            public Builder mergeCollection(Collection value) {
                if (this.collectionBuilder_ == null) {
                    this.collection_ = (this.bitField0_ & 1) == 1 && this.collection_ != Collection.getDefaultInstance() ? Collection.newBuilder(this.collection_).mergeFrom(value).buildPartial() : value;
                    this.onChanged();
                } else {
                    this.collectionBuilder_.mergeFrom(value);
                }
                this.bitField0_ |= 1;
                return this;
            }

            public Builder clearCollection() {
                if (this.collectionBuilder_ == null) {
                    this.collection_ = Collection.getDefaultInstance();
                    this.onChanged();
                } else {
                    this.collectionBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFFFE;
                return this;
            }

            public Collection.Builder getCollectionBuilder() {
                this.bitField0_ |= 1;
                this.onChanged();
                return this.getCollectionFieldBuilder().getBuilder();
            }

            @Override
            public CollectionOrBuilder getCollectionOrBuilder() {
                if (this.collectionBuilder_ != null) {
                    return this.collectionBuilder_.getMessageOrBuilder();
                }
                return this.collection_;
            }

            private SingleFieldBuilder<Collection, Collection.Builder, CollectionOrBuilder> getCollectionFieldBuilder() {
                if (this.collectionBuilder_ == null) {
                    this.collectionBuilder_ = new SingleFieldBuilder(this.getCollection(), this.getParentForChildren(), this.isClean());
                    this.collection_ = null;
                }
                return this.collectionBuilder_;
            }

            @Override
            public boolean hasDataModel() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override
            public DataModel getDataModel() {
                return this.dataModel_;
            }

            public Builder setDataModel(DataModel value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 2;
                this.dataModel_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearDataModel() {
                this.bitField0_ &= 0xFFFFFFFD;
                this.dataModel_ = DataModel.DOCUMENT;
                this.onChanged();
                return this;
            }

            private void ensureProjectionIsMutable() {
                if ((this.bitField0_ & 4) != 4) {
                    this.projection_ = new ArrayList<Column>(this.projection_);
                    this.bitField0_ |= 4;
                }
            }

            @Override
            public List<Column> getProjectionList() {
                if (this.projectionBuilder_ == null) {
                    return Collections.unmodifiableList(this.projection_);
                }
                return this.projectionBuilder_.getMessageList();
            }

            @Override
            public int getProjectionCount() {
                if (this.projectionBuilder_ == null) {
                    return this.projection_.size();
                }
                return this.projectionBuilder_.getCount();
            }

            @Override
            public Column getProjection(int index) {
                if (this.projectionBuilder_ == null) {
                    return this.projection_.get(index);
                }
                return this.projectionBuilder_.getMessage(index);
            }

            public Builder setProjection(int index, Column value) {
                if (this.projectionBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureProjectionIsMutable();
                    this.projection_.set(index, value);
                    this.onChanged();
                } else {
                    this.projectionBuilder_.setMessage(index, value);
                }
                return this;
            }

            public Builder setProjection(int index, Column.Builder builderForValue) {
                if (this.projectionBuilder_ == null) {
                    this.ensureProjectionIsMutable();
                    this.projection_.set(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.projectionBuilder_.setMessage(index, builderForValue.build());
                }
                return this;
            }

            public Builder addProjection(Column value) {
                if (this.projectionBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureProjectionIsMutable();
                    this.projection_.add(value);
                    this.onChanged();
                } else {
                    this.projectionBuilder_.addMessage(value);
                }
                return this;
            }

            public Builder addProjection(int index, Column value) {
                if (this.projectionBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureProjectionIsMutable();
                    this.projection_.add(index, value);
                    this.onChanged();
                } else {
                    this.projectionBuilder_.addMessage(index, value);
                }
                return this;
            }

            public Builder addProjection(Column.Builder builderForValue) {
                if (this.projectionBuilder_ == null) {
                    this.ensureProjectionIsMutable();
                    this.projection_.add(builderForValue.build());
                    this.onChanged();
                } else {
                    this.projectionBuilder_.addMessage(builderForValue.build());
                }
                return this;
            }

            public Builder addProjection(int index, Column.Builder builderForValue) {
                if (this.projectionBuilder_ == null) {
                    this.ensureProjectionIsMutable();
                    this.projection_.add(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.projectionBuilder_.addMessage(index, builderForValue.build());
                }
                return this;
            }

            public Builder addAllProjection(Iterable<? extends Column> values2) {
                if (this.projectionBuilder_ == null) {
                    this.ensureProjectionIsMutable();
                    AbstractMessageLite.Builder.addAll(values2, this.projection_);
                    this.onChanged();
                } else {
                    this.projectionBuilder_.addAllMessages(values2);
                }
                return this;
            }

            public Builder clearProjection() {
                if (this.projectionBuilder_ == null) {
                    this.projection_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFFFFB;
                    this.onChanged();
                } else {
                    this.projectionBuilder_.clear();
                }
                return this;
            }

            public Builder removeProjection(int index) {
                if (this.projectionBuilder_ == null) {
                    this.ensureProjectionIsMutable();
                    this.projection_.remove(index);
                    this.onChanged();
                } else {
                    this.projectionBuilder_.remove(index);
                }
                return this;
            }

            public Column.Builder getProjectionBuilder(int index) {
                return this.getProjectionFieldBuilder().getBuilder(index);
            }

            @Override
            public ColumnOrBuilder getProjectionOrBuilder(int index) {
                if (this.projectionBuilder_ == null) {
                    return this.projection_.get(index);
                }
                return this.projectionBuilder_.getMessageOrBuilder(index);
            }

            @Override
            public List<? extends ColumnOrBuilder> getProjectionOrBuilderList() {
                if (this.projectionBuilder_ != null) {
                    return this.projectionBuilder_.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.projection_);
            }

            public Column.Builder addProjectionBuilder() {
                return this.getProjectionFieldBuilder().addBuilder(Column.getDefaultInstance());
            }

            public Column.Builder addProjectionBuilder(int index) {
                return this.getProjectionFieldBuilder().addBuilder(index, Column.getDefaultInstance());
            }

            public List<Column.Builder> getProjectionBuilderList() {
                return this.getProjectionFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilder<Column, Column.Builder, ColumnOrBuilder> getProjectionFieldBuilder() {
                if (this.projectionBuilder_ == null) {
                    this.projectionBuilder_ = new RepeatedFieldBuilder(this.projection_, (this.bitField0_ & 4) == 4, this.getParentForChildren(), this.isClean());
                    this.projection_ = null;
                }
                return this.projectionBuilder_;
            }

            private void ensureRowIsMutable() {
                if ((this.bitField0_ & 8) != 8) {
                    this.row_ = new ArrayList<TypedRow>(this.row_);
                    this.bitField0_ |= 8;
                }
            }

            @Override
            public List<TypedRow> getRowList() {
                if (this.rowBuilder_ == null) {
                    return Collections.unmodifiableList(this.row_);
                }
                return this.rowBuilder_.getMessageList();
            }

            @Override
            public int getRowCount() {
                if (this.rowBuilder_ == null) {
                    return this.row_.size();
                }
                return this.rowBuilder_.getCount();
            }

            @Override
            public TypedRow getRow(int index) {
                if (this.rowBuilder_ == null) {
                    return this.row_.get(index);
                }
                return this.rowBuilder_.getMessage(index);
            }

            public Builder setRow(int index, TypedRow value) {
                if (this.rowBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureRowIsMutable();
                    this.row_.set(index, value);
                    this.onChanged();
                } else {
                    this.rowBuilder_.setMessage(index, value);
                }
                return this;
            }

            public Builder setRow(int index, TypedRow.Builder builderForValue) {
                if (this.rowBuilder_ == null) {
                    this.ensureRowIsMutable();
                    this.row_.set(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.rowBuilder_.setMessage(index, builderForValue.build());
                }
                return this;
            }

            public Builder addRow(TypedRow value) {
                if (this.rowBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureRowIsMutable();
                    this.row_.add(value);
                    this.onChanged();
                } else {
                    this.rowBuilder_.addMessage(value);
                }
                return this;
            }

            public Builder addRow(int index, TypedRow value) {
                if (this.rowBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureRowIsMutable();
                    this.row_.add(index, value);
                    this.onChanged();
                } else {
                    this.rowBuilder_.addMessage(index, value);
                }
                return this;
            }

            public Builder addRow(TypedRow.Builder builderForValue) {
                if (this.rowBuilder_ == null) {
                    this.ensureRowIsMutable();
                    this.row_.add(builderForValue.build());
                    this.onChanged();
                } else {
                    this.rowBuilder_.addMessage(builderForValue.build());
                }
                return this;
            }

            public Builder addRow(int index, TypedRow.Builder builderForValue) {
                if (this.rowBuilder_ == null) {
                    this.ensureRowIsMutable();
                    this.row_.add(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.rowBuilder_.addMessage(index, builderForValue.build());
                }
                return this;
            }

            public Builder addAllRow(Iterable<? extends TypedRow> values2) {
                if (this.rowBuilder_ == null) {
                    this.ensureRowIsMutable();
                    AbstractMessageLite.Builder.addAll(values2, this.row_);
                    this.onChanged();
                } else {
                    this.rowBuilder_.addAllMessages(values2);
                }
                return this;
            }

            public Builder clearRow() {
                if (this.rowBuilder_ == null) {
                    this.row_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFFFF7;
                    this.onChanged();
                } else {
                    this.rowBuilder_.clear();
                }
                return this;
            }

            public Builder removeRow(int index) {
                if (this.rowBuilder_ == null) {
                    this.ensureRowIsMutable();
                    this.row_.remove(index);
                    this.onChanged();
                } else {
                    this.rowBuilder_.remove(index);
                }
                return this;
            }

            public TypedRow.Builder getRowBuilder(int index) {
                return this.getRowFieldBuilder().getBuilder(index);
            }

            @Override
            public TypedRowOrBuilder getRowOrBuilder(int index) {
                if (this.rowBuilder_ == null) {
                    return this.row_.get(index);
                }
                return this.rowBuilder_.getMessageOrBuilder(index);
            }

            @Override
            public List<? extends TypedRowOrBuilder> getRowOrBuilderList() {
                if (this.rowBuilder_ != null) {
                    return this.rowBuilder_.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.row_);
            }

            public TypedRow.Builder addRowBuilder() {
                return this.getRowFieldBuilder().addBuilder(TypedRow.getDefaultInstance());
            }

            public TypedRow.Builder addRowBuilder(int index) {
                return this.getRowFieldBuilder().addBuilder(index, TypedRow.getDefaultInstance());
            }

            public List<TypedRow.Builder> getRowBuilderList() {
                return this.getRowFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilder<TypedRow, TypedRow.Builder, TypedRowOrBuilder> getRowFieldBuilder() {
                if (this.rowBuilder_ == null) {
                    this.rowBuilder_ = new RepeatedFieldBuilder(this.row_, (this.bitField0_ & 8) == 8, this.getParentForChildren(), this.isClean());
                    this.row_ = null;
                }
                return this.rowBuilder_;
            }

            private void ensureArgsIsMutable() {
                if ((this.bitField0_ & 0x10) != 16) {
                    this.args_ = new ArrayList<MysqlxDatatypes.Scalar>(this.args_);
                    this.bitField0_ |= 0x10;
                }
            }

            @Override
            public List<MysqlxDatatypes.Scalar> getArgsList() {
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
            public MysqlxDatatypes.Scalar getArgs(int index) {
                if (this.argsBuilder_ == null) {
                    return this.args_.get(index);
                }
                return this.argsBuilder_.getMessage(index);
            }

            public Builder setArgs(int index, MysqlxDatatypes.Scalar value) {
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

            public Builder setArgs(int index, MysqlxDatatypes.Scalar.Builder builderForValue) {
                if (this.argsBuilder_ == null) {
                    this.ensureArgsIsMutable();
                    this.args_.set(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.argsBuilder_.setMessage(index, builderForValue.build());
                }
                return this;
            }

            public Builder addArgs(MysqlxDatatypes.Scalar value) {
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

            public Builder addArgs(int index, MysqlxDatatypes.Scalar value) {
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

            public Builder addArgs(MysqlxDatatypes.Scalar.Builder builderForValue) {
                if (this.argsBuilder_ == null) {
                    this.ensureArgsIsMutable();
                    this.args_.add(builderForValue.build());
                    this.onChanged();
                } else {
                    this.argsBuilder_.addMessage(builderForValue.build());
                }
                return this;
            }

            public Builder addArgs(int index, MysqlxDatatypes.Scalar.Builder builderForValue) {
                if (this.argsBuilder_ == null) {
                    this.ensureArgsIsMutable();
                    this.args_.add(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.argsBuilder_.addMessage(index, builderForValue.build());
                }
                return this;
            }

            public Builder addAllArgs(Iterable<? extends MysqlxDatatypes.Scalar> values2) {
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
                    this.bitField0_ &= 0xFFFFFFEF;
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

            public MysqlxDatatypes.Scalar.Builder getArgsBuilder(int index) {
                return this.getArgsFieldBuilder().getBuilder(index);
            }

            @Override
            public MysqlxDatatypes.ScalarOrBuilder getArgsOrBuilder(int index) {
                if (this.argsBuilder_ == null) {
                    return this.args_.get(index);
                }
                return this.argsBuilder_.getMessageOrBuilder(index);
            }

            @Override
            public List<? extends MysqlxDatatypes.ScalarOrBuilder> getArgsOrBuilderList() {
                if (this.argsBuilder_ != null) {
                    return this.argsBuilder_.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.args_);
            }

            public MysqlxDatatypes.Scalar.Builder addArgsBuilder() {
                return this.getArgsFieldBuilder().addBuilder(MysqlxDatatypes.Scalar.getDefaultInstance());
            }

            public MysqlxDatatypes.Scalar.Builder addArgsBuilder(int index) {
                return this.getArgsFieldBuilder().addBuilder(index, MysqlxDatatypes.Scalar.getDefaultInstance());
            }

            public List<MysqlxDatatypes.Scalar.Builder> getArgsBuilderList() {
                return this.getArgsFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilder<MysqlxDatatypes.Scalar, MysqlxDatatypes.Scalar.Builder, MysqlxDatatypes.ScalarOrBuilder> getArgsFieldBuilder() {
                if (this.argsBuilder_ == null) {
                    this.argsBuilder_ = new RepeatedFieldBuilder(this.args_, (this.bitField0_ & 0x10) == 16, this.getParentForChildren(), this.isClean());
                    this.args_ = null;
                }
                return this.argsBuilder_;
            }

            @Override
            public boolean hasUpsert() {
                return (this.bitField0_ & 0x20) == 32;
            }

            @Override
            public boolean getUpsert() {
                return this.upsert_;
            }

            public Builder setUpsert(boolean value) {
                this.bitField0_ |= 0x20;
                this.upsert_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearUpsert() {
                this.bitField0_ &= 0xFFFFFFDF;
                this.upsert_ = false;
                this.onChanged();
                return this;
            }
        }

        public static final class TypedRow
        extends GeneratedMessage
        implements TypedRowOrBuilder {
            private static final TypedRow defaultInstance;
            private final UnknownFieldSet unknownFields;
            public static Parser<TypedRow> PARSER;
            public static final int FIELD_FIELD_NUMBER = 1;
            private List<MysqlxExpr.Expr> field_;
            private byte memoizedIsInitialized = (byte)-1;
            private int memoizedSerializedSize = -1;
            private static final long serialVersionUID = 0L;

            private TypedRow(GeneratedMessage.Builder<?> builder) {
                super(builder);
                this.unknownFields = builder.getUnknownFields();
            }

            private TypedRow(boolean noInit) {
                this.unknownFields = UnknownFieldSet.getDefaultInstance();
            }

            public static TypedRow getDefaultInstance() {
                return defaultInstance;
            }

            @Override
            public TypedRow getDefaultInstanceForType() {
                return defaultInstance;
            }

            @Override
            public final UnknownFieldSet getUnknownFields() {
                return this.unknownFields;
            }

            private TypedRow(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
                            this.field_ = new ArrayList<MysqlxExpr.Expr>();
                            mutable_bitField0_ |= true;
                        }
                        this.field_.add(input.readMessage(MysqlxExpr.Expr.PARSER, extensionRegistry));
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
                return internal_static_Mysqlx_Crud_Insert_TypedRow_descriptor;
            }

            @Override
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_Mysqlx_Crud_Insert_TypedRow_fieldAccessorTable.ensureFieldAccessorsInitialized(TypedRow.class, Builder.class);
            }

            public Parser<TypedRow> getParserForType() {
                return PARSER;
            }

            @Override
            public List<MysqlxExpr.Expr> getFieldList() {
                return this.field_;
            }

            @Override
            public List<? extends MysqlxExpr.ExprOrBuilder> getFieldOrBuilderList() {
                return this.field_;
            }

            @Override
            public int getFieldCount() {
                return this.field_.size();
            }

            @Override
            public MysqlxExpr.Expr getField(int index) {
                return this.field_.get(index);
            }

            @Override
            public MysqlxExpr.ExprOrBuilder getFieldOrBuilder(int index) {
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
                for (int i = 0; i < this.getFieldCount(); ++i) {
                    if (this.getField(i).isInitialized()) continue;
                    this.memoizedIsInitialized = 0;
                    return false;
                }
                this.memoizedIsInitialized = 1;
                return true;
            }

            @Override
            public void writeTo(CodedOutputStream output) throws IOException {
                this.getSerializedSize();
                for (int i = 0; i < this.field_.size(); ++i) {
                    output.writeMessage(1, this.field_.get(i));
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
                for (int i = 0; i < this.field_.size(); ++i) {
                    size += CodedOutputStream.computeMessageSize(1, this.field_.get(i));
                }
                this.memoizedSerializedSize = size += this.getUnknownFields().getSerializedSize();
                return size;
            }

            @Override
            protected Object writeReplace() throws ObjectStreamException {
                return super.writeReplace();
            }

            public static TypedRow parseFrom(ByteString data) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(data);
            }

            public static TypedRow parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(data, extensionRegistry);
            }

            public static TypedRow parseFrom(byte[] data) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(data);
            }

            public static TypedRow parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(data, extensionRegistry);
            }

            public static TypedRow parseFrom(InputStream input) throws IOException {
                return PARSER.parseFrom(input);
            }

            public static TypedRow parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return PARSER.parseFrom(input, extensionRegistry);
            }

            public static TypedRow parseDelimitedFrom(InputStream input) throws IOException {
                return PARSER.parseDelimitedFrom(input);
            }

            public static TypedRow parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return PARSER.parseDelimitedFrom(input, extensionRegistry);
            }

            public static TypedRow parseFrom(CodedInputStream input) throws IOException {
                return PARSER.parseFrom(input);
            }

            public static TypedRow parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return PARSER.parseFrom(input, extensionRegistry);
            }

            public static Builder newBuilder() {
                return Builder.create();
            }

            @Override
            public Builder newBuilderForType() {
                return TypedRow.newBuilder();
            }

            public static Builder newBuilder(TypedRow prototype) {
                return TypedRow.newBuilder().mergeFrom(prototype);
            }

            @Override
            public Builder toBuilder() {
                return TypedRow.newBuilder(this);
            }

            @Override
            protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
                Builder builder = new Builder(parent);
                return builder;
            }

            static {
                PARSER = new AbstractParser<TypedRow>(){

                    @Override
                    public TypedRow parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                        return new TypedRow(input, extensionRegistry);
                    }
                };
                defaultInstance = new TypedRow(true);
                defaultInstance.initFields();
            }

            public static final class Builder
            extends GeneratedMessage.Builder<Builder>
            implements TypedRowOrBuilder {
                private int bitField0_;
                private List<MysqlxExpr.Expr> field_ = Collections.emptyList();
                private RepeatedFieldBuilder<MysqlxExpr.Expr, MysqlxExpr.Expr.Builder, MysqlxExpr.ExprOrBuilder> fieldBuilder_;

                public static final Descriptors.Descriptor getDescriptor() {
                    return internal_static_Mysqlx_Crud_Insert_TypedRow_descriptor;
                }

                @Override
                protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                    return internal_static_Mysqlx_Crud_Insert_TypedRow_fieldAccessorTable.ensureFieldAccessorsInitialized(TypedRow.class, Builder.class);
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
                        this.getFieldFieldBuilder();
                    }
                }

                private static Builder create() {
                    return new Builder();
                }

                @Override
                public Builder clear() {
                    super.clear();
                    if (this.fieldBuilder_ == null) {
                        this.field_ = Collections.emptyList();
                        this.bitField0_ &= 0xFFFFFFFE;
                    } else {
                        this.fieldBuilder_.clear();
                    }
                    return this;
                }

                @Override
                public Builder clone() {
                    return Builder.create().mergeFrom(this.buildPartial());
                }

                @Override
                public Descriptors.Descriptor getDescriptorForType() {
                    return internal_static_Mysqlx_Crud_Insert_TypedRow_descriptor;
                }

                @Override
                public TypedRow getDefaultInstanceForType() {
                    return TypedRow.getDefaultInstance();
                }

                @Override
                public TypedRow build() {
                    TypedRow result = this.buildPartial();
                    if (!result.isInitialized()) {
                        throw Builder.newUninitializedMessageException(result);
                    }
                    return result;
                }

                @Override
                public TypedRow buildPartial() {
                    TypedRow result = new TypedRow(this);
                    int from_bitField0_ = this.bitField0_;
                    if (this.fieldBuilder_ == null) {
                        if ((this.bitField0_ & 1) == 1) {
                            this.field_ = Collections.unmodifiableList(this.field_);
                            this.bitField0_ &= 0xFFFFFFFE;
                        }
                        result.field_ = this.field_;
                    } else {
                        result.field_ = this.fieldBuilder_.build();
                    }
                    this.onBuilt();
                    return result;
                }

                @Override
                public Builder mergeFrom(Message other) {
                    if (other instanceof TypedRow) {
                        return this.mergeFrom((TypedRow)other);
                    }
                    super.mergeFrom(other);
                    return this;
                }

                public Builder mergeFrom(TypedRow other) {
                    if (other == TypedRow.getDefaultInstance()) {
                        return this;
                    }
                    if (this.fieldBuilder_ == null) {
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
                    } else if (!other.field_.isEmpty()) {
                        if (this.fieldBuilder_.isEmpty()) {
                            this.fieldBuilder_.dispose();
                            this.fieldBuilder_ = null;
                            this.field_ = other.field_;
                            this.bitField0_ &= 0xFFFFFFFE;
                            this.fieldBuilder_ = alwaysUseFieldBuilders ? this.getFieldFieldBuilder() : null;
                        } else {
                            this.fieldBuilder_.addAllMessages(other.field_);
                        }
                    }
                    this.mergeUnknownFields(other.getUnknownFields());
                    return this;
                }

                @Override
                public final boolean isInitialized() {
                    for (int i = 0; i < this.getFieldCount(); ++i) {
                        if (this.getField(i).isInitialized()) continue;
                        return false;
                    }
                    return true;
                }

                @Override
                public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                    TypedRow parsedMessage = null;
                    try {
                        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
                    } catch (InvalidProtocolBufferException e) {
                        parsedMessage = (TypedRow)e.getUnfinishedMessage();
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
                        this.field_ = new ArrayList<MysqlxExpr.Expr>(this.field_);
                        this.bitField0_ |= 1;
                    }
                }

                @Override
                public List<MysqlxExpr.Expr> getFieldList() {
                    if (this.fieldBuilder_ == null) {
                        return Collections.unmodifiableList(this.field_);
                    }
                    return this.fieldBuilder_.getMessageList();
                }

                @Override
                public int getFieldCount() {
                    if (this.fieldBuilder_ == null) {
                        return this.field_.size();
                    }
                    return this.fieldBuilder_.getCount();
                }

                @Override
                public MysqlxExpr.Expr getField(int index) {
                    if (this.fieldBuilder_ == null) {
                        return this.field_.get(index);
                    }
                    return this.fieldBuilder_.getMessage(index);
                }

                public Builder setField(int index, MysqlxExpr.Expr value) {
                    if (this.fieldBuilder_ == null) {
                        if (value == null) {
                            throw new NullPointerException();
                        }
                        this.ensureFieldIsMutable();
                        this.field_.set(index, value);
                        this.onChanged();
                    } else {
                        this.fieldBuilder_.setMessage(index, value);
                    }
                    return this;
                }

                public Builder setField(int index, MysqlxExpr.Expr.Builder builderForValue) {
                    if (this.fieldBuilder_ == null) {
                        this.ensureFieldIsMutable();
                        this.field_.set(index, builderForValue.build());
                        this.onChanged();
                    } else {
                        this.fieldBuilder_.setMessage(index, builderForValue.build());
                    }
                    return this;
                }

                public Builder addField(MysqlxExpr.Expr value) {
                    if (this.fieldBuilder_ == null) {
                        if (value == null) {
                            throw new NullPointerException();
                        }
                        this.ensureFieldIsMutable();
                        this.field_.add(value);
                        this.onChanged();
                    } else {
                        this.fieldBuilder_.addMessage(value);
                    }
                    return this;
                }

                public Builder addField(int index, MysqlxExpr.Expr value) {
                    if (this.fieldBuilder_ == null) {
                        if (value == null) {
                            throw new NullPointerException();
                        }
                        this.ensureFieldIsMutable();
                        this.field_.add(index, value);
                        this.onChanged();
                    } else {
                        this.fieldBuilder_.addMessage(index, value);
                    }
                    return this;
                }

                public Builder addField(MysqlxExpr.Expr.Builder builderForValue) {
                    if (this.fieldBuilder_ == null) {
                        this.ensureFieldIsMutable();
                        this.field_.add(builderForValue.build());
                        this.onChanged();
                    } else {
                        this.fieldBuilder_.addMessage(builderForValue.build());
                    }
                    return this;
                }

                public Builder addField(int index, MysqlxExpr.Expr.Builder builderForValue) {
                    if (this.fieldBuilder_ == null) {
                        this.ensureFieldIsMutable();
                        this.field_.add(index, builderForValue.build());
                        this.onChanged();
                    } else {
                        this.fieldBuilder_.addMessage(index, builderForValue.build());
                    }
                    return this;
                }

                public Builder addAllField(Iterable<? extends MysqlxExpr.Expr> values2) {
                    if (this.fieldBuilder_ == null) {
                        this.ensureFieldIsMutable();
                        AbstractMessageLite.Builder.addAll(values2, this.field_);
                        this.onChanged();
                    } else {
                        this.fieldBuilder_.addAllMessages(values2);
                    }
                    return this;
                }

                public Builder clearField() {
                    if (this.fieldBuilder_ == null) {
                        this.field_ = Collections.emptyList();
                        this.bitField0_ &= 0xFFFFFFFE;
                        this.onChanged();
                    } else {
                        this.fieldBuilder_.clear();
                    }
                    return this;
                }

                public Builder removeField(int index) {
                    if (this.fieldBuilder_ == null) {
                        this.ensureFieldIsMutable();
                        this.field_.remove(index);
                        this.onChanged();
                    } else {
                        this.fieldBuilder_.remove(index);
                    }
                    return this;
                }

                public MysqlxExpr.Expr.Builder getFieldBuilder(int index) {
                    return this.getFieldFieldBuilder().getBuilder(index);
                }

                @Override
                public MysqlxExpr.ExprOrBuilder getFieldOrBuilder(int index) {
                    if (this.fieldBuilder_ == null) {
                        return this.field_.get(index);
                    }
                    return this.fieldBuilder_.getMessageOrBuilder(index);
                }

                @Override
                public List<? extends MysqlxExpr.ExprOrBuilder> getFieldOrBuilderList() {
                    if (this.fieldBuilder_ != null) {
                        return this.fieldBuilder_.getMessageOrBuilderList();
                    }
                    return Collections.unmodifiableList(this.field_);
                }

                public MysqlxExpr.Expr.Builder addFieldBuilder() {
                    return this.getFieldFieldBuilder().addBuilder(MysqlxExpr.Expr.getDefaultInstance());
                }

                public MysqlxExpr.Expr.Builder addFieldBuilder(int index) {
                    return this.getFieldFieldBuilder().addBuilder(index, MysqlxExpr.Expr.getDefaultInstance());
                }

                public List<MysqlxExpr.Expr.Builder> getFieldBuilderList() {
                    return this.getFieldFieldBuilder().getBuilderList();
                }

                private RepeatedFieldBuilder<MysqlxExpr.Expr, MysqlxExpr.Expr.Builder, MysqlxExpr.ExprOrBuilder> getFieldFieldBuilder() {
                    if (this.fieldBuilder_ == null) {
                        this.fieldBuilder_ = new RepeatedFieldBuilder(this.field_, (this.bitField0_ & 1) == 1, this.getParentForChildren(), this.isClean());
                        this.field_ = null;
                    }
                    return this.fieldBuilder_;
                }
            }
        }

        public static interface TypedRowOrBuilder
        extends MessageOrBuilder {
            public List<MysqlxExpr.Expr> getFieldList();

            public MysqlxExpr.Expr getField(int var1);

            public int getFieldCount();

            public List<? extends MysqlxExpr.ExprOrBuilder> getFieldOrBuilderList();

            public MysqlxExpr.ExprOrBuilder getFieldOrBuilder(int var1);
        }
    }

    public static interface InsertOrBuilder
    extends MessageOrBuilder {
        public boolean hasCollection();

        public Collection getCollection();

        public CollectionOrBuilder getCollectionOrBuilder();

        public boolean hasDataModel();

        public DataModel getDataModel();

        public List<Column> getProjectionList();

        public Column getProjection(int var1);

        public int getProjectionCount();

        public List<? extends ColumnOrBuilder> getProjectionOrBuilderList();

        public ColumnOrBuilder getProjectionOrBuilder(int var1);

        public List<Insert.TypedRow> getRowList();

        public Insert.TypedRow getRow(int var1);

        public int getRowCount();

        public List<? extends Insert.TypedRowOrBuilder> getRowOrBuilderList();

        public Insert.TypedRowOrBuilder getRowOrBuilder(int var1);

        public List<MysqlxDatatypes.Scalar> getArgsList();

        public MysqlxDatatypes.Scalar getArgs(int var1);

        public int getArgsCount();

        public List<? extends MysqlxDatatypes.ScalarOrBuilder> getArgsOrBuilderList();

        public MysqlxDatatypes.ScalarOrBuilder getArgsOrBuilder(int var1);

        public boolean hasUpsert();

        public boolean getUpsert();
    }

    public static final class Find
    extends GeneratedMessage
    implements FindOrBuilder {
        private static final Find defaultInstance;
        private final UnknownFieldSet unknownFields;
        public static Parser<Find> PARSER;
        private int bitField0_;
        public static final int COLLECTION_FIELD_NUMBER = 2;
        private Collection collection_;
        public static final int DATA_MODEL_FIELD_NUMBER = 3;
        private DataModel dataModel_;
        public static final int PROJECTION_FIELD_NUMBER = 4;
        private List<Projection> projection_;
        public static final int CRITERIA_FIELD_NUMBER = 5;
        private MysqlxExpr.Expr criteria_;
        public static final int ARGS_FIELD_NUMBER = 11;
        private List<MysqlxDatatypes.Scalar> args_;
        public static final int LIMIT_FIELD_NUMBER = 6;
        private Limit limit_;
        public static final int ORDER_FIELD_NUMBER = 7;
        private List<Order> order_;
        public static final int GROUPING_FIELD_NUMBER = 8;
        private List<MysqlxExpr.Expr> grouping_;
        public static final int GROUPING_CRITERIA_FIELD_NUMBER = 9;
        private MysqlxExpr.Expr groupingCriteria_;
        public static final int LOCKING_FIELD_NUMBER = 12;
        private RowLock locking_;
        public static final int LOCKING_OPTIONS_FIELD_NUMBER = 13;
        private RowLockOptions lockingOptions_;
        private byte memoizedIsInitialized = (byte)-1;
        private int memoizedSerializedSize = -1;
        private static final long serialVersionUID = 0L;

        private Find(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.unknownFields = builder.getUnknownFields();
        }

        private Find(boolean noInit) {
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static Find getDefaultInstance() {
            return defaultInstance;
        }

        @Override
        public Find getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private Find(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this.initFields();
            int mutable_bitField0_ = 0;
            UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
            try {
                boolean done = false;
                block20: while (!done) {
                    Enum value;
                    int tag = input.readTag();
                    switch (tag) {
                        case 0: {
                            done = true;
                            continue block20;
                        }
                        default: {
                            if (this.parseUnknownField(input, unknownFields, extensionRegistry, tag)) continue block20;
                            done = true;
                            continue block20;
                        }
                        case 18: {
                            Collection.Builder subBuilder = null;
                            if ((this.bitField0_ & 1) == 1) {
                                subBuilder = this.collection_.toBuilder();
                            }
                            this.collection_ = input.readMessage(Collection.PARSER, extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom(this.collection_);
                                this.collection_ = subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 1;
                            continue block20;
                        }
                        case 24: {
                            int rawValue = input.readEnum();
                            value = DataModel.valueOf(rawValue);
                            if (value == null) {
                                unknownFields.mergeVarintField(3, rawValue);
                                continue block20;
                            }
                            this.bitField0_ |= 2;
                            this.dataModel_ = value;
                            continue block20;
                        }
                        case 34: {
                            if ((mutable_bitField0_ & 4) != 4) {
                                this.projection_ = new ArrayList<Projection>();
                                mutable_bitField0_ |= 4;
                            }
                            this.projection_.add(input.readMessage(Projection.PARSER, extensionRegistry));
                            continue block20;
                        }
                        case 42: {
                            MysqlxExpr.Expr.Builder subBuilder = null;
                            if ((this.bitField0_ & 4) == 4) {
                                subBuilder = this.criteria_.toBuilder();
                            }
                            this.criteria_ = input.readMessage(MysqlxExpr.Expr.PARSER, extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom(this.criteria_);
                                this.criteria_ = subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 4;
                            continue block20;
                        }
                        case 50: {
                            Limit.Builder subBuilder = null;
                            if ((this.bitField0_ & 8) == 8) {
                                subBuilder = this.limit_.toBuilder();
                            }
                            this.limit_ = input.readMessage(Limit.PARSER, extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom(this.limit_);
                                this.limit_ = subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 8;
                            continue block20;
                        }
                        case 58: {
                            if ((mutable_bitField0_ & 0x40) != 64) {
                                this.order_ = new ArrayList<Order>();
                                mutable_bitField0_ |= 0x40;
                            }
                            this.order_.add(input.readMessage(Order.PARSER, extensionRegistry));
                            continue block20;
                        }
                        case 66: {
                            if ((mutable_bitField0_ & 0x80) != 128) {
                                this.grouping_ = new ArrayList<MysqlxExpr.Expr>();
                                mutable_bitField0_ |= 0x80;
                            }
                            this.grouping_.add(input.readMessage(MysqlxExpr.Expr.PARSER, extensionRegistry));
                            continue block20;
                        }
                        case 74: {
                            MysqlxExpr.Expr.Builder subBuilder = null;
                            if ((this.bitField0_ & 0x10) == 16) {
                                subBuilder = this.groupingCriteria_.toBuilder();
                            }
                            this.groupingCriteria_ = input.readMessage(MysqlxExpr.Expr.PARSER, extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom(this.groupingCriteria_);
                                this.groupingCriteria_ = subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 0x10;
                            continue block20;
                        }
                        case 90: {
                            if ((mutable_bitField0_ & 0x10) != 16) {
                                this.args_ = new ArrayList<MysqlxDatatypes.Scalar>();
                                mutable_bitField0_ |= 0x10;
                            }
                            this.args_.add(input.readMessage(MysqlxDatatypes.Scalar.PARSER, extensionRegistry));
                            continue block20;
                        }
                        case 96: {
                            int rawValue = input.readEnum();
                            value = RowLock.valueOf(rawValue);
                            if (value == null) {
                                unknownFields.mergeVarintField(12, rawValue);
                                continue block20;
                            }
                            this.bitField0_ |= 0x20;
                            this.locking_ = value;
                            continue block20;
                        }
                        case 104: 
                    }
                    int rawValue = input.readEnum();
                    value = RowLockOptions.valueOf(rawValue);
                    if (value == null) {
                        unknownFields.mergeVarintField(13, rawValue);
                        continue;
                    }
                    this.bitField0_ |= 0x40;
                    this.lockingOptions_ = value;
                }
            } catch (InvalidProtocolBufferException e) {
                throw e.setUnfinishedMessage(this);
            } catch (IOException e) {
                throw new InvalidProtocolBufferException(e.getMessage()).setUnfinishedMessage(this);
            } finally {
                if ((mutable_bitField0_ & 4) == 4) {
                    this.projection_ = Collections.unmodifiableList(this.projection_);
                }
                if ((mutable_bitField0_ & 0x40) == 64) {
                    this.order_ = Collections.unmodifiableList(this.order_);
                }
                if ((mutable_bitField0_ & 0x80) == 128) {
                    this.grouping_ = Collections.unmodifiableList(this.grouping_);
                }
                if ((mutable_bitField0_ & 0x10) == 16) {
                    this.args_ = Collections.unmodifiableList(this.args_);
                }
                this.unknownFields = unknownFields.build();
                this.makeExtensionsImmutable();
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return internal_static_Mysqlx_Crud_Find_descriptor;
        }

        @Override
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return internal_static_Mysqlx_Crud_Find_fieldAccessorTable.ensureFieldAccessorsInitialized(Find.class, Builder.class);
        }

        public Parser<Find> getParserForType() {
            return PARSER;
        }

        @Override
        public boolean hasCollection() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override
        public Collection getCollection() {
            return this.collection_;
        }

        @Override
        public CollectionOrBuilder getCollectionOrBuilder() {
            return this.collection_;
        }

        @Override
        public boolean hasDataModel() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override
        public DataModel getDataModel() {
            return this.dataModel_;
        }

        @Override
        public List<Projection> getProjectionList() {
            return this.projection_;
        }

        @Override
        public List<? extends ProjectionOrBuilder> getProjectionOrBuilderList() {
            return this.projection_;
        }

        @Override
        public int getProjectionCount() {
            return this.projection_.size();
        }

        @Override
        public Projection getProjection(int index) {
            return this.projection_.get(index);
        }

        @Override
        public ProjectionOrBuilder getProjectionOrBuilder(int index) {
            return this.projection_.get(index);
        }

        @Override
        public boolean hasCriteria() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override
        public MysqlxExpr.Expr getCriteria() {
            return this.criteria_;
        }

        @Override
        public MysqlxExpr.ExprOrBuilder getCriteriaOrBuilder() {
            return this.criteria_;
        }

        @Override
        public List<MysqlxDatatypes.Scalar> getArgsList() {
            return this.args_;
        }

        @Override
        public List<? extends MysqlxDatatypes.ScalarOrBuilder> getArgsOrBuilderList() {
            return this.args_;
        }

        @Override
        public int getArgsCount() {
            return this.args_.size();
        }

        @Override
        public MysqlxDatatypes.Scalar getArgs(int index) {
            return this.args_.get(index);
        }

        @Override
        public MysqlxDatatypes.ScalarOrBuilder getArgsOrBuilder(int index) {
            return this.args_.get(index);
        }

        @Override
        public boolean hasLimit() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override
        public Limit getLimit() {
            return this.limit_;
        }

        @Override
        public LimitOrBuilder getLimitOrBuilder() {
            return this.limit_;
        }

        @Override
        public List<Order> getOrderList() {
            return this.order_;
        }

        @Override
        public List<? extends OrderOrBuilder> getOrderOrBuilderList() {
            return this.order_;
        }

        @Override
        public int getOrderCount() {
            return this.order_.size();
        }

        @Override
        public Order getOrder(int index) {
            return this.order_.get(index);
        }

        @Override
        public OrderOrBuilder getOrderOrBuilder(int index) {
            return this.order_.get(index);
        }

        @Override
        public List<MysqlxExpr.Expr> getGroupingList() {
            return this.grouping_;
        }

        @Override
        public List<? extends MysqlxExpr.ExprOrBuilder> getGroupingOrBuilderList() {
            return this.grouping_;
        }

        @Override
        public int getGroupingCount() {
            return this.grouping_.size();
        }

        @Override
        public MysqlxExpr.Expr getGrouping(int index) {
            return this.grouping_.get(index);
        }

        @Override
        public MysqlxExpr.ExprOrBuilder getGroupingOrBuilder(int index) {
            return this.grouping_.get(index);
        }

        @Override
        public boolean hasGroupingCriteria() {
            return (this.bitField0_ & 0x10) == 16;
        }

        @Override
        public MysqlxExpr.Expr getGroupingCriteria() {
            return this.groupingCriteria_;
        }

        @Override
        public MysqlxExpr.ExprOrBuilder getGroupingCriteriaOrBuilder() {
            return this.groupingCriteria_;
        }

        @Override
        public boolean hasLocking() {
            return (this.bitField0_ & 0x20) == 32;
        }

        @Override
        public RowLock getLocking() {
            return this.locking_;
        }

        @Override
        public boolean hasLockingOptions() {
            return (this.bitField0_ & 0x40) == 64;
        }

        @Override
        public RowLockOptions getLockingOptions() {
            return this.lockingOptions_;
        }

        private void initFields() {
            this.collection_ = Collection.getDefaultInstance();
            this.dataModel_ = DataModel.DOCUMENT;
            this.projection_ = Collections.emptyList();
            this.criteria_ = MysqlxExpr.Expr.getDefaultInstance();
            this.args_ = Collections.emptyList();
            this.limit_ = Limit.getDefaultInstance();
            this.order_ = Collections.emptyList();
            this.grouping_ = Collections.emptyList();
            this.groupingCriteria_ = MysqlxExpr.Expr.getDefaultInstance();
            this.locking_ = RowLock.SHARED_LOCK;
            this.lockingOptions_ = RowLockOptions.NOWAIT;
        }

        @Override
        public final boolean isInitialized() {
            int i;
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            if (!this.hasCollection()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            if (!this.getCollection().isInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            for (i = 0; i < this.getProjectionCount(); ++i) {
                if (this.getProjection(i).isInitialized()) continue;
                this.memoizedIsInitialized = 0;
                return false;
            }
            if (this.hasCriteria() && !this.getCriteria().isInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            for (i = 0; i < this.getArgsCount(); ++i) {
                if (this.getArgs(i).isInitialized()) continue;
                this.memoizedIsInitialized = 0;
                return false;
            }
            if (this.hasLimit() && !this.getLimit().isInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            for (i = 0; i < this.getOrderCount(); ++i) {
                if (this.getOrder(i).isInitialized()) continue;
                this.memoizedIsInitialized = 0;
                return false;
            }
            for (i = 0; i < this.getGroupingCount(); ++i) {
                if (this.getGrouping(i).isInitialized()) continue;
                this.memoizedIsInitialized = 0;
                return false;
            }
            if (this.hasGroupingCriteria() && !this.getGroupingCriteria().isInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        @Override
        public void writeTo(CodedOutputStream output) throws IOException {
            int i;
            this.getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                output.writeMessage(2, this.collection_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeEnum(3, this.dataModel_.getNumber());
            }
            for (i = 0; i < this.projection_.size(); ++i) {
                output.writeMessage(4, this.projection_.get(i));
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeMessage(5, this.criteria_);
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeMessage(6, this.limit_);
            }
            for (i = 0; i < this.order_.size(); ++i) {
                output.writeMessage(7, this.order_.get(i));
            }
            for (i = 0; i < this.grouping_.size(); ++i) {
                output.writeMessage(8, this.grouping_.get(i));
            }
            if ((this.bitField0_ & 0x10) == 16) {
                output.writeMessage(9, this.groupingCriteria_);
            }
            for (i = 0; i < this.args_.size(); ++i) {
                output.writeMessage(11, this.args_.get(i));
            }
            if ((this.bitField0_ & 0x20) == 32) {
                output.writeEnum(12, this.locking_.getNumber());
            }
            if ((this.bitField0_ & 0x40) == 64) {
                output.writeEnum(13, this.lockingOptions_.getNumber());
            }
            this.getUnknownFields().writeTo(output);
        }

        @Override
        public int getSerializedSize() {
            int i;
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            size = 0;
            if ((this.bitField0_ & 1) == 1) {
                size += CodedOutputStream.computeMessageSize(2, this.collection_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size += CodedOutputStream.computeEnumSize(3, this.dataModel_.getNumber());
            }
            for (i = 0; i < this.projection_.size(); ++i) {
                size += CodedOutputStream.computeMessageSize(4, this.projection_.get(i));
            }
            if ((this.bitField0_ & 4) == 4) {
                size += CodedOutputStream.computeMessageSize(5, this.criteria_);
            }
            if ((this.bitField0_ & 8) == 8) {
                size += CodedOutputStream.computeMessageSize(6, this.limit_);
            }
            for (i = 0; i < this.order_.size(); ++i) {
                size += CodedOutputStream.computeMessageSize(7, this.order_.get(i));
            }
            for (i = 0; i < this.grouping_.size(); ++i) {
                size += CodedOutputStream.computeMessageSize(8, this.grouping_.get(i));
            }
            if ((this.bitField0_ & 0x10) == 16) {
                size += CodedOutputStream.computeMessageSize(9, this.groupingCriteria_);
            }
            for (i = 0; i < this.args_.size(); ++i) {
                size += CodedOutputStream.computeMessageSize(11, this.args_.get(i));
            }
            if ((this.bitField0_ & 0x20) == 32) {
                size += CodedOutputStream.computeEnumSize(12, this.locking_.getNumber());
            }
            if ((this.bitField0_ & 0x40) == 64) {
                size += CodedOutputStream.computeEnumSize(13, this.lockingOptions_.getNumber());
            }
            this.memoizedSerializedSize = size += this.getUnknownFields().getSerializedSize();
            return size;
        }

        @Override
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static Find parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static Find parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static Find parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static Find parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static Find parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static Find parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Find parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static Find parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static Find parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static Find parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override
        public Builder newBuilderForType() {
            return Find.newBuilder();
        }

        public static Builder newBuilder(Find prototype) {
            return Find.newBuilder().mergeFrom(prototype);
        }

        @Override
        public Builder toBuilder() {
            return Find.newBuilder(this);
        }

        @Override
        protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        static {
            PARSER = new AbstractParser<Find>(){

                @Override
                public Find parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                    return new Find(input, extensionRegistry);
                }
            };
            defaultInstance = new Find(true);
            defaultInstance.initFields();
        }

        public static final class Builder
        extends GeneratedMessage.Builder<Builder>
        implements FindOrBuilder {
            private int bitField0_;
            private Collection collection_ = Collection.getDefaultInstance();
            private SingleFieldBuilder<Collection, Collection.Builder, CollectionOrBuilder> collectionBuilder_;
            private DataModel dataModel_ = DataModel.DOCUMENT;
            private List<Projection> projection_ = Collections.emptyList();
            private RepeatedFieldBuilder<Projection, Projection.Builder, ProjectionOrBuilder> projectionBuilder_;
            private MysqlxExpr.Expr criteria_ = MysqlxExpr.Expr.getDefaultInstance();
            private SingleFieldBuilder<MysqlxExpr.Expr, MysqlxExpr.Expr.Builder, MysqlxExpr.ExprOrBuilder> criteriaBuilder_;
            private List<MysqlxDatatypes.Scalar> args_ = Collections.emptyList();
            private RepeatedFieldBuilder<MysqlxDatatypes.Scalar, MysqlxDatatypes.Scalar.Builder, MysqlxDatatypes.ScalarOrBuilder> argsBuilder_;
            private Limit limit_ = Limit.getDefaultInstance();
            private SingleFieldBuilder<Limit, Limit.Builder, LimitOrBuilder> limitBuilder_;
            private List<Order> order_ = Collections.emptyList();
            private RepeatedFieldBuilder<Order, Order.Builder, OrderOrBuilder> orderBuilder_;
            private List<MysqlxExpr.Expr> grouping_ = Collections.emptyList();
            private RepeatedFieldBuilder<MysqlxExpr.Expr, MysqlxExpr.Expr.Builder, MysqlxExpr.ExprOrBuilder> groupingBuilder_;
            private MysqlxExpr.Expr groupingCriteria_ = MysqlxExpr.Expr.getDefaultInstance();
            private SingleFieldBuilder<MysqlxExpr.Expr, MysqlxExpr.Expr.Builder, MysqlxExpr.ExprOrBuilder> groupingCriteriaBuilder_;
            private RowLock locking_ = RowLock.SHARED_LOCK;
            private RowLockOptions lockingOptions_ = RowLockOptions.NOWAIT;

            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_Mysqlx_Crud_Find_descriptor;
            }

            @Override
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_Mysqlx_Crud_Find_fieldAccessorTable.ensureFieldAccessorsInitialized(Find.class, Builder.class);
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
                    this.getCollectionFieldBuilder();
                    this.getProjectionFieldBuilder();
                    this.getCriteriaFieldBuilder();
                    this.getArgsFieldBuilder();
                    this.getLimitFieldBuilder();
                    this.getOrderFieldBuilder();
                    this.getGroupingFieldBuilder();
                    this.getGroupingCriteriaFieldBuilder();
                }
            }

            private static Builder create() {
                return new Builder();
            }

            @Override
            public Builder clear() {
                super.clear();
                if (this.collectionBuilder_ == null) {
                    this.collection_ = Collection.getDefaultInstance();
                } else {
                    this.collectionBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFFFE;
                this.dataModel_ = DataModel.DOCUMENT;
                this.bitField0_ &= 0xFFFFFFFD;
                if (this.projectionBuilder_ == null) {
                    this.projection_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFFFFB;
                } else {
                    this.projectionBuilder_.clear();
                }
                if (this.criteriaBuilder_ == null) {
                    this.criteria_ = MysqlxExpr.Expr.getDefaultInstance();
                } else {
                    this.criteriaBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFFF7;
                if (this.argsBuilder_ == null) {
                    this.args_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFFFEF;
                } else {
                    this.argsBuilder_.clear();
                }
                if (this.limitBuilder_ == null) {
                    this.limit_ = Limit.getDefaultInstance();
                } else {
                    this.limitBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFFDF;
                if (this.orderBuilder_ == null) {
                    this.order_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFFFBF;
                } else {
                    this.orderBuilder_.clear();
                }
                if (this.groupingBuilder_ == null) {
                    this.grouping_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFFF7F;
                } else {
                    this.groupingBuilder_.clear();
                }
                if (this.groupingCriteriaBuilder_ == null) {
                    this.groupingCriteria_ = MysqlxExpr.Expr.getDefaultInstance();
                } else {
                    this.groupingCriteriaBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFEFF;
                this.locking_ = RowLock.SHARED_LOCK;
                this.bitField0_ &= 0xFFFFFDFF;
                this.lockingOptions_ = RowLockOptions.NOWAIT;
                this.bitField0_ &= 0xFFFFFBFF;
                return this;
            }

            @Override
            public Builder clone() {
                return Builder.create().mergeFrom(this.buildPartial());
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return internal_static_Mysqlx_Crud_Find_descriptor;
            }

            @Override
            public Find getDefaultInstanceForType() {
                return Find.getDefaultInstance();
            }

            @Override
            public Find build() {
                Find result = this.buildPartial();
                if (!result.isInitialized()) {
                    throw Builder.newUninitializedMessageException(result);
                }
                return result;
            }

            @Override
            public Find buildPartial() {
                Find result = new Find(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ |= 1;
                }
                if (this.collectionBuilder_ == null) {
                    result.collection_ = this.collection_;
                } else {
                    result.collection_ = this.collectionBuilder_.build();
                }
                if ((from_bitField0_ & 2) == 2) {
                    to_bitField0_ |= 2;
                }
                result.dataModel_ = this.dataModel_;
                if (this.projectionBuilder_ == null) {
                    if ((this.bitField0_ & 4) == 4) {
                        this.projection_ = Collections.unmodifiableList(this.projection_);
                        this.bitField0_ &= 0xFFFFFFFB;
                    }
                    result.projection_ = this.projection_;
                } else {
                    result.projection_ = this.projectionBuilder_.build();
                }
                if ((from_bitField0_ & 8) == 8) {
                    to_bitField0_ |= 4;
                }
                if (this.criteriaBuilder_ == null) {
                    result.criteria_ = this.criteria_;
                } else {
                    result.criteria_ = this.criteriaBuilder_.build();
                }
                if (this.argsBuilder_ == null) {
                    if ((this.bitField0_ & 0x10) == 16) {
                        this.args_ = Collections.unmodifiableList(this.args_);
                        this.bitField0_ &= 0xFFFFFFEF;
                    }
                    result.args_ = this.args_;
                } else {
                    result.args_ = this.argsBuilder_.build();
                }
                if ((from_bitField0_ & 0x20) == 32) {
                    to_bitField0_ |= 8;
                }
                if (this.limitBuilder_ == null) {
                    result.limit_ = this.limit_;
                } else {
                    result.limit_ = this.limitBuilder_.build();
                }
                if (this.orderBuilder_ == null) {
                    if ((this.bitField0_ & 0x40) == 64) {
                        this.order_ = Collections.unmodifiableList(this.order_);
                        this.bitField0_ &= 0xFFFFFFBF;
                    }
                    result.order_ = this.order_;
                } else {
                    result.order_ = this.orderBuilder_.build();
                }
                if (this.groupingBuilder_ == null) {
                    if ((this.bitField0_ & 0x80) == 128) {
                        this.grouping_ = Collections.unmodifiableList(this.grouping_);
                        this.bitField0_ &= 0xFFFFFF7F;
                    }
                    result.grouping_ = this.grouping_;
                } else {
                    result.grouping_ = this.groupingBuilder_.build();
                }
                if ((from_bitField0_ & 0x100) == 256) {
                    to_bitField0_ |= 0x10;
                }
                if (this.groupingCriteriaBuilder_ == null) {
                    result.groupingCriteria_ = this.groupingCriteria_;
                } else {
                    result.groupingCriteria_ = this.groupingCriteriaBuilder_.build();
                }
                if ((from_bitField0_ & 0x200) == 512) {
                    to_bitField0_ |= 0x20;
                }
                result.locking_ = this.locking_;
                if ((from_bitField0_ & 0x400) == 1024) {
                    to_bitField0_ |= 0x40;
                }
                result.lockingOptions_ = this.lockingOptions_;
                result.bitField0_ = to_bitField0_;
                this.onBuilt();
                return result;
            }

            @Override
            public Builder mergeFrom(Message other) {
                if (other instanceof Find) {
                    return this.mergeFrom((Find)other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(Find other) {
                if (other == Find.getDefaultInstance()) {
                    return this;
                }
                if (other.hasCollection()) {
                    this.mergeCollection(other.getCollection());
                }
                if (other.hasDataModel()) {
                    this.setDataModel(other.getDataModel());
                }
                if (this.projectionBuilder_ == null) {
                    if (!other.projection_.isEmpty()) {
                        if (this.projection_.isEmpty()) {
                            this.projection_ = other.projection_;
                            this.bitField0_ &= 0xFFFFFFFB;
                        } else {
                            this.ensureProjectionIsMutable();
                            this.projection_.addAll(other.projection_);
                        }
                        this.onChanged();
                    }
                } else if (!other.projection_.isEmpty()) {
                    if (this.projectionBuilder_.isEmpty()) {
                        this.projectionBuilder_.dispose();
                        this.projectionBuilder_ = null;
                        this.projection_ = other.projection_;
                        this.bitField0_ &= 0xFFFFFFFB;
                        this.projectionBuilder_ = alwaysUseFieldBuilders ? this.getProjectionFieldBuilder() : null;
                    } else {
                        this.projectionBuilder_.addAllMessages(other.projection_);
                    }
                }
                if (other.hasCriteria()) {
                    this.mergeCriteria(other.getCriteria());
                }
                if (this.argsBuilder_ == null) {
                    if (!other.args_.isEmpty()) {
                        if (this.args_.isEmpty()) {
                            this.args_ = other.args_;
                            this.bitField0_ &= 0xFFFFFFEF;
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
                        this.bitField0_ &= 0xFFFFFFEF;
                        this.argsBuilder_ = alwaysUseFieldBuilders ? this.getArgsFieldBuilder() : null;
                    } else {
                        this.argsBuilder_.addAllMessages(other.args_);
                    }
                }
                if (other.hasLimit()) {
                    this.mergeLimit(other.getLimit());
                }
                if (this.orderBuilder_ == null) {
                    if (!other.order_.isEmpty()) {
                        if (this.order_.isEmpty()) {
                            this.order_ = other.order_;
                            this.bitField0_ &= 0xFFFFFFBF;
                        } else {
                            this.ensureOrderIsMutable();
                            this.order_.addAll(other.order_);
                        }
                        this.onChanged();
                    }
                } else if (!other.order_.isEmpty()) {
                    if (this.orderBuilder_.isEmpty()) {
                        this.orderBuilder_.dispose();
                        this.orderBuilder_ = null;
                        this.order_ = other.order_;
                        this.bitField0_ &= 0xFFFFFFBF;
                        this.orderBuilder_ = alwaysUseFieldBuilders ? this.getOrderFieldBuilder() : null;
                    } else {
                        this.orderBuilder_.addAllMessages(other.order_);
                    }
                }
                if (this.groupingBuilder_ == null) {
                    if (!other.grouping_.isEmpty()) {
                        if (this.grouping_.isEmpty()) {
                            this.grouping_ = other.grouping_;
                            this.bitField0_ &= 0xFFFFFF7F;
                        } else {
                            this.ensureGroupingIsMutable();
                            this.grouping_.addAll(other.grouping_);
                        }
                        this.onChanged();
                    }
                } else if (!other.grouping_.isEmpty()) {
                    if (this.groupingBuilder_.isEmpty()) {
                        this.groupingBuilder_.dispose();
                        this.groupingBuilder_ = null;
                        this.grouping_ = other.grouping_;
                        this.bitField0_ &= 0xFFFFFF7F;
                        this.groupingBuilder_ = alwaysUseFieldBuilders ? this.getGroupingFieldBuilder() : null;
                    } else {
                        this.groupingBuilder_.addAllMessages(other.grouping_);
                    }
                }
                if (other.hasGroupingCriteria()) {
                    this.mergeGroupingCriteria(other.getGroupingCriteria());
                }
                if (other.hasLocking()) {
                    this.setLocking(other.getLocking());
                }
                if (other.hasLockingOptions()) {
                    this.setLockingOptions(other.getLockingOptions());
                }
                this.mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            @Override
            public final boolean isInitialized() {
                int i;
                if (!this.hasCollection()) {
                    return false;
                }
                if (!this.getCollection().isInitialized()) {
                    return false;
                }
                for (i = 0; i < this.getProjectionCount(); ++i) {
                    if (this.getProjection(i).isInitialized()) continue;
                    return false;
                }
                if (this.hasCriteria() && !this.getCriteria().isInitialized()) {
                    return false;
                }
                for (i = 0; i < this.getArgsCount(); ++i) {
                    if (this.getArgs(i).isInitialized()) continue;
                    return false;
                }
                if (this.hasLimit() && !this.getLimit().isInitialized()) {
                    return false;
                }
                for (i = 0; i < this.getOrderCount(); ++i) {
                    if (this.getOrder(i).isInitialized()) continue;
                    return false;
                }
                for (i = 0; i < this.getGroupingCount(); ++i) {
                    if (this.getGrouping(i).isInitialized()) continue;
                    return false;
                }
                return !this.hasGroupingCriteria() || this.getGroupingCriteria().isInitialized();
            }

            @Override
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                Find parsedMessage = null;
                try {
                    parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
                } catch (InvalidProtocolBufferException e) {
                    parsedMessage = (Find)e.getUnfinishedMessage();
                    throw e;
                } finally {
                    if (parsedMessage != null) {
                        this.mergeFrom(parsedMessage);
                    }
                }
                return this;
            }

            @Override
            public boolean hasCollection() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override
            public Collection getCollection() {
                if (this.collectionBuilder_ == null) {
                    return this.collection_;
                }
                return this.collectionBuilder_.getMessage();
            }

            public Builder setCollection(Collection value) {
                if (this.collectionBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.collection_ = value;
                    this.onChanged();
                } else {
                    this.collectionBuilder_.setMessage(value);
                }
                this.bitField0_ |= 1;
                return this;
            }

            public Builder setCollection(Collection.Builder builderForValue) {
                if (this.collectionBuilder_ == null) {
                    this.collection_ = builderForValue.build();
                    this.onChanged();
                } else {
                    this.collectionBuilder_.setMessage(builderForValue.build());
                }
                this.bitField0_ |= 1;
                return this;
            }

            public Builder mergeCollection(Collection value) {
                if (this.collectionBuilder_ == null) {
                    this.collection_ = (this.bitField0_ & 1) == 1 && this.collection_ != Collection.getDefaultInstance() ? Collection.newBuilder(this.collection_).mergeFrom(value).buildPartial() : value;
                    this.onChanged();
                } else {
                    this.collectionBuilder_.mergeFrom(value);
                }
                this.bitField0_ |= 1;
                return this;
            }

            public Builder clearCollection() {
                if (this.collectionBuilder_ == null) {
                    this.collection_ = Collection.getDefaultInstance();
                    this.onChanged();
                } else {
                    this.collectionBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFFFE;
                return this;
            }

            public Collection.Builder getCollectionBuilder() {
                this.bitField0_ |= 1;
                this.onChanged();
                return this.getCollectionFieldBuilder().getBuilder();
            }

            @Override
            public CollectionOrBuilder getCollectionOrBuilder() {
                if (this.collectionBuilder_ != null) {
                    return this.collectionBuilder_.getMessageOrBuilder();
                }
                return this.collection_;
            }

            private SingleFieldBuilder<Collection, Collection.Builder, CollectionOrBuilder> getCollectionFieldBuilder() {
                if (this.collectionBuilder_ == null) {
                    this.collectionBuilder_ = new SingleFieldBuilder(this.getCollection(), this.getParentForChildren(), this.isClean());
                    this.collection_ = null;
                }
                return this.collectionBuilder_;
            }

            @Override
            public boolean hasDataModel() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override
            public DataModel getDataModel() {
                return this.dataModel_;
            }

            public Builder setDataModel(DataModel value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 2;
                this.dataModel_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearDataModel() {
                this.bitField0_ &= 0xFFFFFFFD;
                this.dataModel_ = DataModel.DOCUMENT;
                this.onChanged();
                return this;
            }

            private void ensureProjectionIsMutable() {
                if ((this.bitField0_ & 4) != 4) {
                    this.projection_ = new ArrayList<Projection>(this.projection_);
                    this.bitField0_ |= 4;
                }
            }

            @Override
            public List<Projection> getProjectionList() {
                if (this.projectionBuilder_ == null) {
                    return Collections.unmodifiableList(this.projection_);
                }
                return this.projectionBuilder_.getMessageList();
            }

            @Override
            public int getProjectionCount() {
                if (this.projectionBuilder_ == null) {
                    return this.projection_.size();
                }
                return this.projectionBuilder_.getCount();
            }

            @Override
            public Projection getProjection(int index) {
                if (this.projectionBuilder_ == null) {
                    return this.projection_.get(index);
                }
                return this.projectionBuilder_.getMessage(index);
            }

            public Builder setProjection(int index, Projection value) {
                if (this.projectionBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureProjectionIsMutable();
                    this.projection_.set(index, value);
                    this.onChanged();
                } else {
                    this.projectionBuilder_.setMessage(index, value);
                }
                return this;
            }

            public Builder setProjection(int index, Projection.Builder builderForValue) {
                if (this.projectionBuilder_ == null) {
                    this.ensureProjectionIsMutable();
                    this.projection_.set(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.projectionBuilder_.setMessage(index, builderForValue.build());
                }
                return this;
            }

            public Builder addProjection(Projection value) {
                if (this.projectionBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureProjectionIsMutable();
                    this.projection_.add(value);
                    this.onChanged();
                } else {
                    this.projectionBuilder_.addMessage(value);
                }
                return this;
            }

            public Builder addProjection(int index, Projection value) {
                if (this.projectionBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureProjectionIsMutable();
                    this.projection_.add(index, value);
                    this.onChanged();
                } else {
                    this.projectionBuilder_.addMessage(index, value);
                }
                return this;
            }

            public Builder addProjection(Projection.Builder builderForValue) {
                if (this.projectionBuilder_ == null) {
                    this.ensureProjectionIsMutable();
                    this.projection_.add(builderForValue.build());
                    this.onChanged();
                } else {
                    this.projectionBuilder_.addMessage(builderForValue.build());
                }
                return this;
            }

            public Builder addProjection(int index, Projection.Builder builderForValue) {
                if (this.projectionBuilder_ == null) {
                    this.ensureProjectionIsMutable();
                    this.projection_.add(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.projectionBuilder_.addMessage(index, builderForValue.build());
                }
                return this;
            }

            public Builder addAllProjection(Iterable<? extends Projection> values2) {
                if (this.projectionBuilder_ == null) {
                    this.ensureProjectionIsMutable();
                    AbstractMessageLite.Builder.addAll(values2, this.projection_);
                    this.onChanged();
                } else {
                    this.projectionBuilder_.addAllMessages(values2);
                }
                return this;
            }

            public Builder clearProjection() {
                if (this.projectionBuilder_ == null) {
                    this.projection_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFFFFB;
                    this.onChanged();
                } else {
                    this.projectionBuilder_.clear();
                }
                return this;
            }

            public Builder removeProjection(int index) {
                if (this.projectionBuilder_ == null) {
                    this.ensureProjectionIsMutable();
                    this.projection_.remove(index);
                    this.onChanged();
                } else {
                    this.projectionBuilder_.remove(index);
                }
                return this;
            }

            public Projection.Builder getProjectionBuilder(int index) {
                return this.getProjectionFieldBuilder().getBuilder(index);
            }

            @Override
            public ProjectionOrBuilder getProjectionOrBuilder(int index) {
                if (this.projectionBuilder_ == null) {
                    return this.projection_.get(index);
                }
                return this.projectionBuilder_.getMessageOrBuilder(index);
            }

            @Override
            public List<? extends ProjectionOrBuilder> getProjectionOrBuilderList() {
                if (this.projectionBuilder_ != null) {
                    return this.projectionBuilder_.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.projection_);
            }

            public Projection.Builder addProjectionBuilder() {
                return this.getProjectionFieldBuilder().addBuilder(Projection.getDefaultInstance());
            }

            public Projection.Builder addProjectionBuilder(int index) {
                return this.getProjectionFieldBuilder().addBuilder(index, Projection.getDefaultInstance());
            }

            public List<Projection.Builder> getProjectionBuilderList() {
                return this.getProjectionFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilder<Projection, Projection.Builder, ProjectionOrBuilder> getProjectionFieldBuilder() {
                if (this.projectionBuilder_ == null) {
                    this.projectionBuilder_ = new RepeatedFieldBuilder(this.projection_, (this.bitField0_ & 4) == 4, this.getParentForChildren(), this.isClean());
                    this.projection_ = null;
                }
                return this.projectionBuilder_;
            }

            @Override
            public boolean hasCriteria() {
                return (this.bitField0_ & 8) == 8;
            }

            @Override
            public MysqlxExpr.Expr getCriteria() {
                if (this.criteriaBuilder_ == null) {
                    return this.criteria_;
                }
                return this.criteriaBuilder_.getMessage();
            }

            public Builder setCriteria(MysqlxExpr.Expr value) {
                if (this.criteriaBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.criteria_ = value;
                    this.onChanged();
                } else {
                    this.criteriaBuilder_.setMessage(value);
                }
                this.bitField0_ |= 8;
                return this;
            }

            public Builder setCriteria(MysqlxExpr.Expr.Builder builderForValue) {
                if (this.criteriaBuilder_ == null) {
                    this.criteria_ = builderForValue.build();
                    this.onChanged();
                } else {
                    this.criteriaBuilder_.setMessage(builderForValue.build());
                }
                this.bitField0_ |= 8;
                return this;
            }

            public Builder mergeCriteria(MysqlxExpr.Expr value) {
                if (this.criteriaBuilder_ == null) {
                    this.criteria_ = (this.bitField0_ & 8) == 8 && this.criteria_ != MysqlxExpr.Expr.getDefaultInstance() ? MysqlxExpr.Expr.newBuilder(this.criteria_).mergeFrom(value).buildPartial() : value;
                    this.onChanged();
                } else {
                    this.criteriaBuilder_.mergeFrom(value);
                }
                this.bitField0_ |= 8;
                return this;
            }

            public Builder clearCriteria() {
                if (this.criteriaBuilder_ == null) {
                    this.criteria_ = MysqlxExpr.Expr.getDefaultInstance();
                    this.onChanged();
                } else {
                    this.criteriaBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFFF7;
                return this;
            }

            public MysqlxExpr.Expr.Builder getCriteriaBuilder() {
                this.bitField0_ |= 8;
                this.onChanged();
                return this.getCriteriaFieldBuilder().getBuilder();
            }

            @Override
            public MysqlxExpr.ExprOrBuilder getCriteriaOrBuilder() {
                if (this.criteriaBuilder_ != null) {
                    return this.criteriaBuilder_.getMessageOrBuilder();
                }
                return this.criteria_;
            }

            private SingleFieldBuilder<MysqlxExpr.Expr, MysqlxExpr.Expr.Builder, MysqlxExpr.ExprOrBuilder> getCriteriaFieldBuilder() {
                if (this.criteriaBuilder_ == null) {
                    this.criteriaBuilder_ = new SingleFieldBuilder(this.getCriteria(), this.getParentForChildren(), this.isClean());
                    this.criteria_ = null;
                }
                return this.criteriaBuilder_;
            }

            private void ensureArgsIsMutable() {
                if ((this.bitField0_ & 0x10) != 16) {
                    this.args_ = new ArrayList<MysqlxDatatypes.Scalar>(this.args_);
                    this.bitField0_ |= 0x10;
                }
            }

            @Override
            public List<MysqlxDatatypes.Scalar> getArgsList() {
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
            public MysqlxDatatypes.Scalar getArgs(int index) {
                if (this.argsBuilder_ == null) {
                    return this.args_.get(index);
                }
                return this.argsBuilder_.getMessage(index);
            }

            public Builder setArgs(int index, MysqlxDatatypes.Scalar value) {
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

            public Builder setArgs(int index, MysqlxDatatypes.Scalar.Builder builderForValue) {
                if (this.argsBuilder_ == null) {
                    this.ensureArgsIsMutable();
                    this.args_.set(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.argsBuilder_.setMessage(index, builderForValue.build());
                }
                return this;
            }

            public Builder addArgs(MysqlxDatatypes.Scalar value) {
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

            public Builder addArgs(int index, MysqlxDatatypes.Scalar value) {
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

            public Builder addArgs(MysqlxDatatypes.Scalar.Builder builderForValue) {
                if (this.argsBuilder_ == null) {
                    this.ensureArgsIsMutable();
                    this.args_.add(builderForValue.build());
                    this.onChanged();
                } else {
                    this.argsBuilder_.addMessage(builderForValue.build());
                }
                return this;
            }

            public Builder addArgs(int index, MysqlxDatatypes.Scalar.Builder builderForValue) {
                if (this.argsBuilder_ == null) {
                    this.ensureArgsIsMutable();
                    this.args_.add(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.argsBuilder_.addMessage(index, builderForValue.build());
                }
                return this;
            }

            public Builder addAllArgs(Iterable<? extends MysqlxDatatypes.Scalar> values2) {
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
                    this.bitField0_ &= 0xFFFFFFEF;
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

            public MysqlxDatatypes.Scalar.Builder getArgsBuilder(int index) {
                return this.getArgsFieldBuilder().getBuilder(index);
            }

            @Override
            public MysqlxDatatypes.ScalarOrBuilder getArgsOrBuilder(int index) {
                if (this.argsBuilder_ == null) {
                    return this.args_.get(index);
                }
                return this.argsBuilder_.getMessageOrBuilder(index);
            }

            @Override
            public List<? extends MysqlxDatatypes.ScalarOrBuilder> getArgsOrBuilderList() {
                if (this.argsBuilder_ != null) {
                    return this.argsBuilder_.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.args_);
            }

            public MysqlxDatatypes.Scalar.Builder addArgsBuilder() {
                return this.getArgsFieldBuilder().addBuilder(MysqlxDatatypes.Scalar.getDefaultInstance());
            }

            public MysqlxDatatypes.Scalar.Builder addArgsBuilder(int index) {
                return this.getArgsFieldBuilder().addBuilder(index, MysqlxDatatypes.Scalar.getDefaultInstance());
            }

            public List<MysqlxDatatypes.Scalar.Builder> getArgsBuilderList() {
                return this.getArgsFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilder<MysqlxDatatypes.Scalar, MysqlxDatatypes.Scalar.Builder, MysqlxDatatypes.ScalarOrBuilder> getArgsFieldBuilder() {
                if (this.argsBuilder_ == null) {
                    this.argsBuilder_ = new RepeatedFieldBuilder(this.args_, (this.bitField0_ & 0x10) == 16, this.getParentForChildren(), this.isClean());
                    this.args_ = null;
                }
                return this.argsBuilder_;
            }

            @Override
            public boolean hasLimit() {
                return (this.bitField0_ & 0x20) == 32;
            }

            @Override
            public Limit getLimit() {
                if (this.limitBuilder_ == null) {
                    return this.limit_;
                }
                return this.limitBuilder_.getMessage();
            }

            public Builder setLimit(Limit value) {
                if (this.limitBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.limit_ = value;
                    this.onChanged();
                } else {
                    this.limitBuilder_.setMessage(value);
                }
                this.bitField0_ |= 0x20;
                return this;
            }

            public Builder setLimit(Limit.Builder builderForValue) {
                if (this.limitBuilder_ == null) {
                    this.limit_ = builderForValue.build();
                    this.onChanged();
                } else {
                    this.limitBuilder_.setMessage(builderForValue.build());
                }
                this.bitField0_ |= 0x20;
                return this;
            }

            public Builder mergeLimit(Limit value) {
                if (this.limitBuilder_ == null) {
                    this.limit_ = (this.bitField0_ & 0x20) == 32 && this.limit_ != Limit.getDefaultInstance() ? Limit.newBuilder(this.limit_).mergeFrom(value).buildPartial() : value;
                    this.onChanged();
                } else {
                    this.limitBuilder_.mergeFrom(value);
                }
                this.bitField0_ |= 0x20;
                return this;
            }

            public Builder clearLimit() {
                if (this.limitBuilder_ == null) {
                    this.limit_ = Limit.getDefaultInstance();
                    this.onChanged();
                } else {
                    this.limitBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFFDF;
                return this;
            }

            public Limit.Builder getLimitBuilder() {
                this.bitField0_ |= 0x20;
                this.onChanged();
                return this.getLimitFieldBuilder().getBuilder();
            }

            @Override
            public LimitOrBuilder getLimitOrBuilder() {
                if (this.limitBuilder_ != null) {
                    return this.limitBuilder_.getMessageOrBuilder();
                }
                return this.limit_;
            }

            private SingleFieldBuilder<Limit, Limit.Builder, LimitOrBuilder> getLimitFieldBuilder() {
                if (this.limitBuilder_ == null) {
                    this.limitBuilder_ = new SingleFieldBuilder(this.getLimit(), this.getParentForChildren(), this.isClean());
                    this.limit_ = null;
                }
                return this.limitBuilder_;
            }

            private void ensureOrderIsMutable() {
                if ((this.bitField0_ & 0x40) != 64) {
                    this.order_ = new ArrayList<Order>(this.order_);
                    this.bitField0_ |= 0x40;
                }
            }

            @Override
            public List<Order> getOrderList() {
                if (this.orderBuilder_ == null) {
                    return Collections.unmodifiableList(this.order_);
                }
                return this.orderBuilder_.getMessageList();
            }

            @Override
            public int getOrderCount() {
                if (this.orderBuilder_ == null) {
                    return this.order_.size();
                }
                return this.orderBuilder_.getCount();
            }

            @Override
            public Order getOrder(int index) {
                if (this.orderBuilder_ == null) {
                    return this.order_.get(index);
                }
                return this.orderBuilder_.getMessage(index);
            }

            public Builder setOrder(int index, Order value) {
                if (this.orderBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureOrderIsMutable();
                    this.order_.set(index, value);
                    this.onChanged();
                } else {
                    this.orderBuilder_.setMessage(index, value);
                }
                return this;
            }

            public Builder setOrder(int index, Order.Builder builderForValue) {
                if (this.orderBuilder_ == null) {
                    this.ensureOrderIsMutable();
                    this.order_.set(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.orderBuilder_.setMessage(index, builderForValue.build());
                }
                return this;
            }

            public Builder addOrder(Order value) {
                if (this.orderBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureOrderIsMutable();
                    this.order_.add(value);
                    this.onChanged();
                } else {
                    this.orderBuilder_.addMessage(value);
                }
                return this;
            }

            public Builder addOrder(int index, Order value) {
                if (this.orderBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureOrderIsMutable();
                    this.order_.add(index, value);
                    this.onChanged();
                } else {
                    this.orderBuilder_.addMessage(index, value);
                }
                return this;
            }

            public Builder addOrder(Order.Builder builderForValue) {
                if (this.orderBuilder_ == null) {
                    this.ensureOrderIsMutable();
                    this.order_.add(builderForValue.build());
                    this.onChanged();
                } else {
                    this.orderBuilder_.addMessage(builderForValue.build());
                }
                return this;
            }

            public Builder addOrder(int index, Order.Builder builderForValue) {
                if (this.orderBuilder_ == null) {
                    this.ensureOrderIsMutable();
                    this.order_.add(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.orderBuilder_.addMessage(index, builderForValue.build());
                }
                return this;
            }

            public Builder addAllOrder(Iterable<? extends Order> values2) {
                if (this.orderBuilder_ == null) {
                    this.ensureOrderIsMutable();
                    AbstractMessageLite.Builder.addAll(values2, this.order_);
                    this.onChanged();
                } else {
                    this.orderBuilder_.addAllMessages(values2);
                }
                return this;
            }

            public Builder clearOrder() {
                if (this.orderBuilder_ == null) {
                    this.order_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFFFBF;
                    this.onChanged();
                } else {
                    this.orderBuilder_.clear();
                }
                return this;
            }

            public Builder removeOrder(int index) {
                if (this.orderBuilder_ == null) {
                    this.ensureOrderIsMutable();
                    this.order_.remove(index);
                    this.onChanged();
                } else {
                    this.orderBuilder_.remove(index);
                }
                return this;
            }

            public Order.Builder getOrderBuilder(int index) {
                return this.getOrderFieldBuilder().getBuilder(index);
            }

            @Override
            public OrderOrBuilder getOrderOrBuilder(int index) {
                if (this.orderBuilder_ == null) {
                    return this.order_.get(index);
                }
                return this.orderBuilder_.getMessageOrBuilder(index);
            }

            @Override
            public List<? extends OrderOrBuilder> getOrderOrBuilderList() {
                if (this.orderBuilder_ != null) {
                    return this.orderBuilder_.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.order_);
            }

            public Order.Builder addOrderBuilder() {
                return this.getOrderFieldBuilder().addBuilder(Order.getDefaultInstance());
            }

            public Order.Builder addOrderBuilder(int index) {
                return this.getOrderFieldBuilder().addBuilder(index, Order.getDefaultInstance());
            }

            public List<Order.Builder> getOrderBuilderList() {
                return this.getOrderFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilder<Order, Order.Builder, OrderOrBuilder> getOrderFieldBuilder() {
                if (this.orderBuilder_ == null) {
                    this.orderBuilder_ = new RepeatedFieldBuilder(this.order_, (this.bitField0_ & 0x40) == 64, this.getParentForChildren(), this.isClean());
                    this.order_ = null;
                }
                return this.orderBuilder_;
            }

            private void ensureGroupingIsMutable() {
                if ((this.bitField0_ & 0x80) != 128) {
                    this.grouping_ = new ArrayList<MysqlxExpr.Expr>(this.grouping_);
                    this.bitField0_ |= 0x80;
                }
            }

            @Override
            public List<MysqlxExpr.Expr> getGroupingList() {
                if (this.groupingBuilder_ == null) {
                    return Collections.unmodifiableList(this.grouping_);
                }
                return this.groupingBuilder_.getMessageList();
            }

            @Override
            public int getGroupingCount() {
                if (this.groupingBuilder_ == null) {
                    return this.grouping_.size();
                }
                return this.groupingBuilder_.getCount();
            }

            @Override
            public MysqlxExpr.Expr getGrouping(int index) {
                if (this.groupingBuilder_ == null) {
                    return this.grouping_.get(index);
                }
                return this.groupingBuilder_.getMessage(index);
            }

            public Builder setGrouping(int index, MysqlxExpr.Expr value) {
                if (this.groupingBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureGroupingIsMutable();
                    this.grouping_.set(index, value);
                    this.onChanged();
                } else {
                    this.groupingBuilder_.setMessage(index, value);
                }
                return this;
            }

            public Builder setGrouping(int index, MysqlxExpr.Expr.Builder builderForValue) {
                if (this.groupingBuilder_ == null) {
                    this.ensureGroupingIsMutable();
                    this.grouping_.set(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.groupingBuilder_.setMessage(index, builderForValue.build());
                }
                return this;
            }

            public Builder addGrouping(MysqlxExpr.Expr value) {
                if (this.groupingBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureGroupingIsMutable();
                    this.grouping_.add(value);
                    this.onChanged();
                } else {
                    this.groupingBuilder_.addMessage(value);
                }
                return this;
            }

            public Builder addGrouping(int index, MysqlxExpr.Expr value) {
                if (this.groupingBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureGroupingIsMutable();
                    this.grouping_.add(index, value);
                    this.onChanged();
                } else {
                    this.groupingBuilder_.addMessage(index, value);
                }
                return this;
            }

            public Builder addGrouping(MysqlxExpr.Expr.Builder builderForValue) {
                if (this.groupingBuilder_ == null) {
                    this.ensureGroupingIsMutable();
                    this.grouping_.add(builderForValue.build());
                    this.onChanged();
                } else {
                    this.groupingBuilder_.addMessage(builderForValue.build());
                }
                return this;
            }

            public Builder addGrouping(int index, MysqlxExpr.Expr.Builder builderForValue) {
                if (this.groupingBuilder_ == null) {
                    this.ensureGroupingIsMutable();
                    this.grouping_.add(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.groupingBuilder_.addMessage(index, builderForValue.build());
                }
                return this;
            }

            public Builder addAllGrouping(Iterable<? extends MysqlxExpr.Expr> values2) {
                if (this.groupingBuilder_ == null) {
                    this.ensureGroupingIsMutable();
                    AbstractMessageLite.Builder.addAll(values2, this.grouping_);
                    this.onChanged();
                } else {
                    this.groupingBuilder_.addAllMessages(values2);
                }
                return this;
            }

            public Builder clearGrouping() {
                if (this.groupingBuilder_ == null) {
                    this.grouping_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFFF7F;
                    this.onChanged();
                } else {
                    this.groupingBuilder_.clear();
                }
                return this;
            }

            public Builder removeGrouping(int index) {
                if (this.groupingBuilder_ == null) {
                    this.ensureGroupingIsMutable();
                    this.grouping_.remove(index);
                    this.onChanged();
                } else {
                    this.groupingBuilder_.remove(index);
                }
                return this;
            }

            public MysqlxExpr.Expr.Builder getGroupingBuilder(int index) {
                return this.getGroupingFieldBuilder().getBuilder(index);
            }

            @Override
            public MysqlxExpr.ExprOrBuilder getGroupingOrBuilder(int index) {
                if (this.groupingBuilder_ == null) {
                    return this.grouping_.get(index);
                }
                return this.groupingBuilder_.getMessageOrBuilder(index);
            }

            @Override
            public List<? extends MysqlxExpr.ExprOrBuilder> getGroupingOrBuilderList() {
                if (this.groupingBuilder_ != null) {
                    return this.groupingBuilder_.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.grouping_);
            }

            public MysqlxExpr.Expr.Builder addGroupingBuilder() {
                return this.getGroupingFieldBuilder().addBuilder(MysqlxExpr.Expr.getDefaultInstance());
            }

            public MysqlxExpr.Expr.Builder addGroupingBuilder(int index) {
                return this.getGroupingFieldBuilder().addBuilder(index, MysqlxExpr.Expr.getDefaultInstance());
            }

            public List<MysqlxExpr.Expr.Builder> getGroupingBuilderList() {
                return this.getGroupingFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilder<MysqlxExpr.Expr, MysqlxExpr.Expr.Builder, MysqlxExpr.ExprOrBuilder> getGroupingFieldBuilder() {
                if (this.groupingBuilder_ == null) {
                    this.groupingBuilder_ = new RepeatedFieldBuilder(this.grouping_, (this.bitField0_ & 0x80) == 128, this.getParentForChildren(), this.isClean());
                    this.grouping_ = null;
                }
                return this.groupingBuilder_;
            }

            @Override
            public boolean hasGroupingCriteria() {
                return (this.bitField0_ & 0x100) == 256;
            }

            @Override
            public MysqlxExpr.Expr getGroupingCriteria() {
                if (this.groupingCriteriaBuilder_ == null) {
                    return this.groupingCriteria_;
                }
                return this.groupingCriteriaBuilder_.getMessage();
            }

            public Builder setGroupingCriteria(MysqlxExpr.Expr value) {
                if (this.groupingCriteriaBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.groupingCriteria_ = value;
                    this.onChanged();
                } else {
                    this.groupingCriteriaBuilder_.setMessage(value);
                }
                this.bitField0_ |= 0x100;
                return this;
            }

            public Builder setGroupingCriteria(MysqlxExpr.Expr.Builder builderForValue) {
                if (this.groupingCriteriaBuilder_ == null) {
                    this.groupingCriteria_ = builderForValue.build();
                    this.onChanged();
                } else {
                    this.groupingCriteriaBuilder_.setMessage(builderForValue.build());
                }
                this.bitField0_ |= 0x100;
                return this;
            }

            public Builder mergeGroupingCriteria(MysqlxExpr.Expr value) {
                if (this.groupingCriteriaBuilder_ == null) {
                    this.groupingCriteria_ = (this.bitField0_ & 0x100) == 256 && this.groupingCriteria_ != MysqlxExpr.Expr.getDefaultInstance() ? MysqlxExpr.Expr.newBuilder(this.groupingCriteria_).mergeFrom(value).buildPartial() : value;
                    this.onChanged();
                } else {
                    this.groupingCriteriaBuilder_.mergeFrom(value);
                }
                this.bitField0_ |= 0x100;
                return this;
            }

            public Builder clearGroupingCriteria() {
                if (this.groupingCriteriaBuilder_ == null) {
                    this.groupingCriteria_ = MysqlxExpr.Expr.getDefaultInstance();
                    this.onChanged();
                } else {
                    this.groupingCriteriaBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFEFF;
                return this;
            }

            public MysqlxExpr.Expr.Builder getGroupingCriteriaBuilder() {
                this.bitField0_ |= 0x100;
                this.onChanged();
                return this.getGroupingCriteriaFieldBuilder().getBuilder();
            }

            @Override
            public MysqlxExpr.ExprOrBuilder getGroupingCriteriaOrBuilder() {
                if (this.groupingCriteriaBuilder_ != null) {
                    return this.groupingCriteriaBuilder_.getMessageOrBuilder();
                }
                return this.groupingCriteria_;
            }

            private SingleFieldBuilder<MysqlxExpr.Expr, MysqlxExpr.Expr.Builder, MysqlxExpr.ExprOrBuilder> getGroupingCriteriaFieldBuilder() {
                if (this.groupingCriteriaBuilder_ == null) {
                    this.groupingCriteriaBuilder_ = new SingleFieldBuilder(this.getGroupingCriteria(), this.getParentForChildren(), this.isClean());
                    this.groupingCriteria_ = null;
                }
                return this.groupingCriteriaBuilder_;
            }

            @Override
            public boolean hasLocking() {
                return (this.bitField0_ & 0x200) == 512;
            }

            @Override
            public RowLock getLocking() {
                return this.locking_;
            }

            public Builder setLocking(RowLock value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 0x200;
                this.locking_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearLocking() {
                this.bitField0_ &= 0xFFFFFDFF;
                this.locking_ = RowLock.SHARED_LOCK;
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasLockingOptions() {
                return (this.bitField0_ & 0x400) == 1024;
            }

            @Override
            public RowLockOptions getLockingOptions() {
                return this.lockingOptions_;
            }

            public Builder setLockingOptions(RowLockOptions value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 0x400;
                this.lockingOptions_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearLockingOptions() {
                this.bitField0_ &= 0xFFFFFBFF;
                this.lockingOptions_ = RowLockOptions.NOWAIT;
                this.onChanged();
                return this;
            }
        }

        public static enum RowLockOptions implements ProtocolMessageEnum
        {
            NOWAIT(0, 1),
            SKIP_LOCKED(1, 2);

            public static final int NOWAIT_VALUE = 1;
            public static final int SKIP_LOCKED_VALUE = 2;
            private static Internal.EnumLiteMap<RowLockOptions> internalValueMap;
            private static final RowLockOptions[] VALUES;
            private final int index;
            private final int value;

            @Override
            public final int getNumber() {
                return this.value;
            }

            public static RowLockOptions valueOf(int value) {
                switch (value) {
                    case 1: {
                        return NOWAIT;
                    }
                    case 2: {
                        return SKIP_LOCKED;
                    }
                }
                return null;
            }

            public static Internal.EnumLiteMap<RowLockOptions> internalGetValueMap() {
                return internalValueMap;
            }

            @Override
            public final Descriptors.EnumValueDescriptor getValueDescriptor() {
                return RowLockOptions.getDescriptor().getValues().get(this.index);
            }

            @Override
            public final Descriptors.EnumDescriptor getDescriptorForType() {
                return RowLockOptions.getDescriptor();
            }

            public static final Descriptors.EnumDescriptor getDescriptor() {
                return Find.getDescriptor().getEnumTypes().get(1);
            }

            public static RowLockOptions valueOf(Descriptors.EnumValueDescriptor desc) {
                if (desc.getType() != RowLockOptions.getDescriptor()) {
                    throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
                }
                return VALUES[desc.getIndex()];
            }

            private RowLockOptions(int index, int value) {
                this.index = index;
                this.value = value;
            }

            static {
                internalValueMap = new Internal.EnumLiteMap<RowLockOptions>(){

                    @Override
                    public RowLockOptions findValueByNumber(int number) {
                        return RowLockOptions.valueOf(number);
                    }
                };
                VALUES = RowLockOptions.values();
            }
        }

        public static enum RowLock implements ProtocolMessageEnum
        {
            SHARED_LOCK(0, 1),
            EXCLUSIVE_LOCK(1, 2);

            public static final int SHARED_LOCK_VALUE = 1;
            public static final int EXCLUSIVE_LOCK_VALUE = 2;
            private static Internal.EnumLiteMap<RowLock> internalValueMap;
            private static final RowLock[] VALUES;
            private final int index;
            private final int value;

            @Override
            public final int getNumber() {
                return this.value;
            }

            public static RowLock valueOf(int value) {
                switch (value) {
                    case 1: {
                        return SHARED_LOCK;
                    }
                    case 2: {
                        return EXCLUSIVE_LOCK;
                    }
                }
                return null;
            }

            public static Internal.EnumLiteMap<RowLock> internalGetValueMap() {
                return internalValueMap;
            }

            @Override
            public final Descriptors.EnumValueDescriptor getValueDescriptor() {
                return RowLock.getDescriptor().getValues().get(this.index);
            }

            @Override
            public final Descriptors.EnumDescriptor getDescriptorForType() {
                return RowLock.getDescriptor();
            }

            public static final Descriptors.EnumDescriptor getDescriptor() {
                return Find.getDescriptor().getEnumTypes().get(0);
            }

            public static RowLock valueOf(Descriptors.EnumValueDescriptor desc) {
                if (desc.getType() != RowLock.getDescriptor()) {
                    throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
                }
                return VALUES[desc.getIndex()];
            }

            private RowLock(int index, int value) {
                this.index = index;
                this.value = value;
            }

            static {
                internalValueMap = new Internal.EnumLiteMap<RowLock>(){

                    @Override
                    public RowLock findValueByNumber(int number) {
                        return RowLock.valueOf(number);
                    }
                };
                VALUES = RowLock.values();
            }
        }
    }

    public static interface FindOrBuilder
    extends MessageOrBuilder {
        public boolean hasCollection();

        public Collection getCollection();

        public CollectionOrBuilder getCollectionOrBuilder();

        public boolean hasDataModel();

        public DataModel getDataModel();

        public List<Projection> getProjectionList();

        public Projection getProjection(int var1);

        public int getProjectionCount();

        public List<? extends ProjectionOrBuilder> getProjectionOrBuilderList();

        public ProjectionOrBuilder getProjectionOrBuilder(int var1);

        public boolean hasCriteria();

        public MysqlxExpr.Expr getCriteria();

        public MysqlxExpr.ExprOrBuilder getCriteriaOrBuilder();

        public List<MysqlxDatatypes.Scalar> getArgsList();

        public MysqlxDatatypes.Scalar getArgs(int var1);

        public int getArgsCount();

        public List<? extends MysqlxDatatypes.ScalarOrBuilder> getArgsOrBuilderList();

        public MysqlxDatatypes.ScalarOrBuilder getArgsOrBuilder(int var1);

        public boolean hasLimit();

        public Limit getLimit();

        public LimitOrBuilder getLimitOrBuilder();

        public List<Order> getOrderList();

        public Order getOrder(int var1);

        public int getOrderCount();

        public List<? extends OrderOrBuilder> getOrderOrBuilderList();

        public OrderOrBuilder getOrderOrBuilder(int var1);

        public List<MysqlxExpr.Expr> getGroupingList();

        public MysqlxExpr.Expr getGrouping(int var1);

        public int getGroupingCount();

        public List<? extends MysqlxExpr.ExprOrBuilder> getGroupingOrBuilderList();

        public MysqlxExpr.ExprOrBuilder getGroupingOrBuilder(int var1);

        public boolean hasGroupingCriteria();

        public MysqlxExpr.Expr getGroupingCriteria();

        public MysqlxExpr.ExprOrBuilder getGroupingCriteriaOrBuilder();

        public boolean hasLocking();

        public Find.RowLock getLocking();

        public boolean hasLockingOptions();

        public Find.RowLockOptions getLockingOptions();
    }

    public static final class UpdateOperation
    extends GeneratedMessage
    implements UpdateOperationOrBuilder {
        private static final UpdateOperation defaultInstance;
        private final UnknownFieldSet unknownFields;
        public static Parser<UpdateOperation> PARSER;
        private int bitField0_;
        public static final int SOURCE_FIELD_NUMBER = 1;
        private MysqlxExpr.ColumnIdentifier source_;
        public static final int OPERATION_FIELD_NUMBER = 2;
        private UpdateType operation_;
        public static final int VALUE_FIELD_NUMBER = 3;
        private MysqlxExpr.Expr value_;
        private byte memoizedIsInitialized = (byte)-1;
        private int memoizedSerializedSize = -1;
        private static final long serialVersionUID = 0L;

        private UpdateOperation(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.unknownFields = builder.getUnknownFields();
        }

        private UpdateOperation(boolean noInit) {
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static UpdateOperation getDefaultInstance() {
            return defaultInstance;
        }

        @Override
        public UpdateOperation getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private UpdateOperation(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
                            MysqlxExpr.ColumnIdentifier.Builder subBuilder = null;
                            if ((this.bitField0_ & 1) == 1) {
                                subBuilder = this.source_.toBuilder();
                            }
                            this.source_ = input.readMessage(MysqlxExpr.ColumnIdentifier.PARSER, extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom(this.source_);
                                this.source_ = subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 1;
                            continue block12;
                        }
                        case 16: {
                            int rawValue = input.readEnum();
                            UpdateType value = UpdateType.valueOf(rawValue);
                            if (value == null) {
                                unknownFields.mergeVarintField(2, rawValue);
                                continue block12;
                            }
                            this.bitField0_ |= 2;
                            this.operation_ = value;
                            continue block12;
                        }
                        case 26: 
                    }
                    MysqlxExpr.Expr.Builder subBuilder = null;
                    if ((this.bitField0_ & 4) == 4) {
                        subBuilder = this.value_.toBuilder();
                    }
                    this.value_ = input.readMessage(MysqlxExpr.Expr.PARSER, extensionRegistry);
                    if (subBuilder != null) {
                        subBuilder.mergeFrom(this.value_);
                        this.value_ = subBuilder.buildPartial();
                    }
                    this.bitField0_ |= 4;
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
            return internal_static_Mysqlx_Crud_UpdateOperation_descriptor;
        }

        @Override
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return internal_static_Mysqlx_Crud_UpdateOperation_fieldAccessorTable.ensureFieldAccessorsInitialized(UpdateOperation.class, Builder.class);
        }

        public Parser<UpdateOperation> getParserForType() {
            return PARSER;
        }

        @Override
        public boolean hasSource() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override
        public MysqlxExpr.ColumnIdentifier getSource() {
            return this.source_;
        }

        @Override
        public MysqlxExpr.ColumnIdentifierOrBuilder getSourceOrBuilder() {
            return this.source_;
        }

        @Override
        public boolean hasOperation() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override
        public UpdateType getOperation() {
            return this.operation_;
        }

        @Override
        public boolean hasValue() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override
        public MysqlxExpr.Expr getValue() {
            return this.value_;
        }

        @Override
        public MysqlxExpr.ExprOrBuilder getValueOrBuilder() {
            return this.value_;
        }

        private void initFields() {
            this.source_ = MysqlxExpr.ColumnIdentifier.getDefaultInstance();
            this.operation_ = UpdateType.SET;
            this.value_ = MysqlxExpr.Expr.getDefaultInstance();
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
            if (!this.hasSource()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            if (!this.hasOperation()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            if (!this.getSource().isInitialized()) {
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
                output.writeMessage(1, this.source_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeEnum(2, this.operation_.getNumber());
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeMessage(3, this.value_);
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
                size += CodedOutputStream.computeMessageSize(1, this.source_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size += CodedOutputStream.computeEnumSize(2, this.operation_.getNumber());
            }
            if ((this.bitField0_ & 4) == 4) {
                size += CodedOutputStream.computeMessageSize(3, this.value_);
            }
            this.memoizedSerializedSize = size += this.getUnknownFields().getSerializedSize();
            return size;
        }

        @Override
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static UpdateOperation parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static UpdateOperation parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static UpdateOperation parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static UpdateOperation parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static UpdateOperation parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static UpdateOperation parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static UpdateOperation parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static UpdateOperation parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static UpdateOperation parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static UpdateOperation parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override
        public Builder newBuilderForType() {
            return UpdateOperation.newBuilder();
        }

        public static Builder newBuilder(UpdateOperation prototype) {
            return UpdateOperation.newBuilder().mergeFrom(prototype);
        }

        @Override
        public Builder toBuilder() {
            return UpdateOperation.newBuilder(this);
        }

        @Override
        protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        static {
            PARSER = new AbstractParser<UpdateOperation>(){

                @Override
                public UpdateOperation parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                    return new UpdateOperation(input, extensionRegistry);
                }
            };
            defaultInstance = new UpdateOperation(true);
            defaultInstance.initFields();
        }

        public static final class Builder
        extends GeneratedMessage.Builder<Builder>
        implements UpdateOperationOrBuilder {
            private int bitField0_;
            private MysqlxExpr.ColumnIdentifier source_ = MysqlxExpr.ColumnIdentifier.getDefaultInstance();
            private SingleFieldBuilder<MysqlxExpr.ColumnIdentifier, MysqlxExpr.ColumnIdentifier.Builder, MysqlxExpr.ColumnIdentifierOrBuilder> sourceBuilder_;
            private UpdateType operation_ = UpdateType.SET;
            private MysqlxExpr.Expr value_ = MysqlxExpr.Expr.getDefaultInstance();
            private SingleFieldBuilder<MysqlxExpr.Expr, MysqlxExpr.Expr.Builder, MysqlxExpr.ExprOrBuilder> valueBuilder_;

            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_Mysqlx_Crud_UpdateOperation_descriptor;
            }

            @Override
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_Mysqlx_Crud_UpdateOperation_fieldAccessorTable.ensureFieldAccessorsInitialized(UpdateOperation.class, Builder.class);
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
                    this.getSourceFieldBuilder();
                    this.getValueFieldBuilder();
                }
            }

            private static Builder create() {
                return new Builder();
            }

            @Override
            public Builder clear() {
                super.clear();
                if (this.sourceBuilder_ == null) {
                    this.source_ = MysqlxExpr.ColumnIdentifier.getDefaultInstance();
                } else {
                    this.sourceBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFFFE;
                this.operation_ = UpdateType.SET;
                this.bitField0_ &= 0xFFFFFFFD;
                if (this.valueBuilder_ == null) {
                    this.value_ = MysqlxExpr.Expr.getDefaultInstance();
                } else {
                    this.valueBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFFFB;
                return this;
            }

            @Override
            public Builder clone() {
                return Builder.create().mergeFrom(this.buildPartial());
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return internal_static_Mysqlx_Crud_UpdateOperation_descriptor;
            }

            @Override
            public UpdateOperation getDefaultInstanceForType() {
                return UpdateOperation.getDefaultInstance();
            }

            @Override
            public UpdateOperation build() {
                UpdateOperation result = this.buildPartial();
                if (!result.isInitialized()) {
                    throw Builder.newUninitializedMessageException(result);
                }
                return result;
            }

            @Override
            public UpdateOperation buildPartial() {
                UpdateOperation result = new UpdateOperation(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ |= 1;
                }
                if (this.sourceBuilder_ == null) {
                    result.source_ = this.source_;
                } else {
                    result.source_ = this.sourceBuilder_.build();
                }
                if ((from_bitField0_ & 2) == 2) {
                    to_bitField0_ |= 2;
                }
                result.operation_ = this.operation_;
                if ((from_bitField0_ & 4) == 4) {
                    to_bitField0_ |= 4;
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
                if (other instanceof UpdateOperation) {
                    return this.mergeFrom((UpdateOperation)other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(UpdateOperation other) {
                if (other == UpdateOperation.getDefaultInstance()) {
                    return this;
                }
                if (other.hasSource()) {
                    this.mergeSource(other.getSource());
                }
                if (other.hasOperation()) {
                    this.setOperation(other.getOperation());
                }
                if (other.hasValue()) {
                    this.mergeValue(other.getValue());
                }
                this.mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            @Override
            public final boolean isInitialized() {
                if (!this.hasSource()) {
                    return false;
                }
                if (!this.hasOperation()) {
                    return false;
                }
                if (!this.getSource().isInitialized()) {
                    return false;
                }
                return !this.hasValue() || this.getValue().isInitialized();
            }

            @Override
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                UpdateOperation parsedMessage = null;
                try {
                    parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
                } catch (InvalidProtocolBufferException e) {
                    parsedMessage = (UpdateOperation)e.getUnfinishedMessage();
                    throw e;
                } finally {
                    if (parsedMessage != null) {
                        this.mergeFrom(parsedMessage);
                    }
                }
                return this;
            }

            @Override
            public boolean hasSource() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override
            public MysqlxExpr.ColumnIdentifier getSource() {
                if (this.sourceBuilder_ == null) {
                    return this.source_;
                }
                return this.sourceBuilder_.getMessage();
            }

            public Builder setSource(MysqlxExpr.ColumnIdentifier value) {
                if (this.sourceBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.source_ = value;
                    this.onChanged();
                } else {
                    this.sourceBuilder_.setMessage(value);
                }
                this.bitField0_ |= 1;
                return this;
            }

            public Builder setSource(MysqlxExpr.ColumnIdentifier.Builder builderForValue) {
                if (this.sourceBuilder_ == null) {
                    this.source_ = builderForValue.build();
                    this.onChanged();
                } else {
                    this.sourceBuilder_.setMessage(builderForValue.build());
                }
                this.bitField0_ |= 1;
                return this;
            }

            public Builder mergeSource(MysqlxExpr.ColumnIdentifier value) {
                if (this.sourceBuilder_ == null) {
                    this.source_ = (this.bitField0_ & 1) == 1 && this.source_ != MysqlxExpr.ColumnIdentifier.getDefaultInstance() ? MysqlxExpr.ColumnIdentifier.newBuilder(this.source_).mergeFrom(value).buildPartial() : value;
                    this.onChanged();
                } else {
                    this.sourceBuilder_.mergeFrom(value);
                }
                this.bitField0_ |= 1;
                return this;
            }

            public Builder clearSource() {
                if (this.sourceBuilder_ == null) {
                    this.source_ = MysqlxExpr.ColumnIdentifier.getDefaultInstance();
                    this.onChanged();
                } else {
                    this.sourceBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFFFE;
                return this;
            }

            public MysqlxExpr.ColumnIdentifier.Builder getSourceBuilder() {
                this.bitField0_ |= 1;
                this.onChanged();
                return this.getSourceFieldBuilder().getBuilder();
            }

            @Override
            public MysqlxExpr.ColumnIdentifierOrBuilder getSourceOrBuilder() {
                if (this.sourceBuilder_ != null) {
                    return this.sourceBuilder_.getMessageOrBuilder();
                }
                return this.source_;
            }

            private SingleFieldBuilder<MysqlxExpr.ColumnIdentifier, MysqlxExpr.ColumnIdentifier.Builder, MysqlxExpr.ColumnIdentifierOrBuilder> getSourceFieldBuilder() {
                if (this.sourceBuilder_ == null) {
                    this.sourceBuilder_ = new SingleFieldBuilder(this.getSource(), this.getParentForChildren(), this.isClean());
                    this.source_ = null;
                }
                return this.sourceBuilder_;
            }

            @Override
            public boolean hasOperation() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override
            public UpdateType getOperation() {
                return this.operation_;
            }

            public Builder setOperation(UpdateType value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 2;
                this.operation_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearOperation() {
                this.bitField0_ &= 0xFFFFFFFD;
                this.operation_ = UpdateType.SET;
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasValue() {
                return (this.bitField0_ & 4) == 4;
            }

            @Override
            public MysqlxExpr.Expr getValue() {
                if (this.valueBuilder_ == null) {
                    return this.value_;
                }
                return this.valueBuilder_.getMessage();
            }

            public Builder setValue(MysqlxExpr.Expr value) {
                if (this.valueBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.value_ = value;
                    this.onChanged();
                } else {
                    this.valueBuilder_.setMessage(value);
                }
                this.bitField0_ |= 4;
                return this;
            }

            public Builder setValue(MysqlxExpr.Expr.Builder builderForValue) {
                if (this.valueBuilder_ == null) {
                    this.value_ = builderForValue.build();
                    this.onChanged();
                } else {
                    this.valueBuilder_.setMessage(builderForValue.build());
                }
                this.bitField0_ |= 4;
                return this;
            }

            public Builder mergeValue(MysqlxExpr.Expr value) {
                if (this.valueBuilder_ == null) {
                    this.value_ = (this.bitField0_ & 4) == 4 && this.value_ != MysqlxExpr.Expr.getDefaultInstance() ? MysqlxExpr.Expr.newBuilder(this.value_).mergeFrom(value).buildPartial() : value;
                    this.onChanged();
                } else {
                    this.valueBuilder_.mergeFrom(value);
                }
                this.bitField0_ |= 4;
                return this;
            }

            public Builder clearValue() {
                if (this.valueBuilder_ == null) {
                    this.value_ = MysqlxExpr.Expr.getDefaultInstance();
                    this.onChanged();
                } else {
                    this.valueBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFFFB;
                return this;
            }

            public MysqlxExpr.Expr.Builder getValueBuilder() {
                this.bitField0_ |= 4;
                this.onChanged();
                return this.getValueFieldBuilder().getBuilder();
            }

            @Override
            public MysqlxExpr.ExprOrBuilder getValueOrBuilder() {
                if (this.valueBuilder_ != null) {
                    return this.valueBuilder_.getMessageOrBuilder();
                }
                return this.value_;
            }

            private SingleFieldBuilder<MysqlxExpr.Expr, MysqlxExpr.Expr.Builder, MysqlxExpr.ExprOrBuilder> getValueFieldBuilder() {
                if (this.valueBuilder_ == null) {
                    this.valueBuilder_ = new SingleFieldBuilder(this.getValue(), this.getParentForChildren(), this.isClean());
                    this.value_ = null;
                }
                return this.valueBuilder_;
            }
        }

        public static enum UpdateType implements ProtocolMessageEnum
        {
            SET(0, 1),
            ITEM_REMOVE(1, 2),
            ITEM_SET(2, 3),
            ITEM_REPLACE(3, 4),
            ITEM_MERGE(4, 5),
            ARRAY_INSERT(5, 6),
            ARRAY_APPEND(6, 7),
            MERGE_PATCH(7, 8);

            public static final int SET_VALUE = 1;
            public static final int ITEM_REMOVE_VALUE = 2;
            public static final int ITEM_SET_VALUE = 3;
            public static final int ITEM_REPLACE_VALUE = 4;
            public static final int ITEM_MERGE_VALUE = 5;
            public static final int ARRAY_INSERT_VALUE = 6;
            public static final int ARRAY_APPEND_VALUE = 7;
            public static final int MERGE_PATCH_VALUE = 8;
            private static Internal.EnumLiteMap<UpdateType> internalValueMap;
            private static final UpdateType[] VALUES;
            private final int index;
            private final int value;

            @Override
            public final int getNumber() {
                return this.value;
            }

            public static UpdateType valueOf(int value) {
                switch (value) {
                    case 1: {
                        return SET;
                    }
                    case 2: {
                        return ITEM_REMOVE;
                    }
                    case 3: {
                        return ITEM_SET;
                    }
                    case 4: {
                        return ITEM_REPLACE;
                    }
                    case 5: {
                        return ITEM_MERGE;
                    }
                    case 6: {
                        return ARRAY_INSERT;
                    }
                    case 7: {
                        return ARRAY_APPEND;
                    }
                    case 8: {
                        return MERGE_PATCH;
                    }
                }
                return null;
            }

            public static Internal.EnumLiteMap<UpdateType> internalGetValueMap() {
                return internalValueMap;
            }

            @Override
            public final Descriptors.EnumValueDescriptor getValueDescriptor() {
                return UpdateType.getDescriptor().getValues().get(this.index);
            }

            @Override
            public final Descriptors.EnumDescriptor getDescriptorForType() {
                return UpdateType.getDescriptor();
            }

            public static final Descriptors.EnumDescriptor getDescriptor() {
                return UpdateOperation.getDescriptor().getEnumTypes().get(0);
            }

            public static UpdateType valueOf(Descriptors.EnumValueDescriptor desc) {
                if (desc.getType() != UpdateType.getDescriptor()) {
                    throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
                }
                return VALUES[desc.getIndex()];
            }

            private UpdateType(int index, int value) {
                this.index = index;
                this.value = value;
            }

            static {
                internalValueMap = new Internal.EnumLiteMap<UpdateType>(){

                    @Override
                    public UpdateType findValueByNumber(int number) {
                        return UpdateType.valueOf(number);
                    }
                };
                VALUES = UpdateType.values();
            }
        }
    }

    public static interface UpdateOperationOrBuilder
    extends MessageOrBuilder {
        public boolean hasSource();

        public MysqlxExpr.ColumnIdentifier getSource();

        public MysqlxExpr.ColumnIdentifierOrBuilder getSourceOrBuilder();

        public boolean hasOperation();

        public UpdateOperation.UpdateType getOperation();

        public boolean hasValue();

        public MysqlxExpr.Expr getValue();

        public MysqlxExpr.ExprOrBuilder getValueOrBuilder();
    }

    public static final class Order
    extends GeneratedMessage
    implements OrderOrBuilder {
        private static final Order defaultInstance;
        private final UnknownFieldSet unknownFields;
        public static Parser<Order> PARSER;
        private int bitField0_;
        public static final int EXPR_FIELD_NUMBER = 1;
        private MysqlxExpr.Expr expr_;
        public static final int DIRECTION_FIELD_NUMBER = 2;
        private Direction direction_;
        private byte memoizedIsInitialized = (byte)-1;
        private int memoizedSerializedSize = -1;
        private static final long serialVersionUID = 0L;

        private Order(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.unknownFields = builder.getUnknownFields();
        }

        private Order(boolean noInit) {
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static Order getDefaultInstance() {
            return defaultInstance;
        }

        @Override
        public Order getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private Order(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
                            MysqlxExpr.Expr.Builder subBuilder = null;
                            if ((this.bitField0_ & 1) == 1) {
                                subBuilder = this.expr_.toBuilder();
                            }
                            this.expr_ = input.readMessage(MysqlxExpr.Expr.PARSER, extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom(this.expr_);
                                this.expr_ = subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 1;
                            continue block11;
                        }
                        case 16: 
                    }
                    int rawValue = input.readEnum();
                    Direction value = Direction.valueOf(rawValue);
                    if (value == null) {
                        unknownFields.mergeVarintField(2, rawValue);
                        continue;
                    }
                    this.bitField0_ |= 2;
                    this.direction_ = value;
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
            return internal_static_Mysqlx_Crud_Order_descriptor;
        }

        @Override
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return internal_static_Mysqlx_Crud_Order_fieldAccessorTable.ensureFieldAccessorsInitialized(Order.class, Builder.class);
        }

        public Parser<Order> getParserForType() {
            return PARSER;
        }

        @Override
        public boolean hasExpr() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override
        public MysqlxExpr.Expr getExpr() {
            return this.expr_;
        }

        @Override
        public MysqlxExpr.ExprOrBuilder getExprOrBuilder() {
            return this.expr_;
        }

        @Override
        public boolean hasDirection() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override
        public Direction getDirection() {
            return this.direction_;
        }

        private void initFields() {
            this.expr_ = MysqlxExpr.Expr.getDefaultInstance();
            this.direction_ = Direction.ASC;
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
            if (!this.hasExpr()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            if (!this.getExpr().isInitialized()) {
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
                output.writeMessage(1, this.expr_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeEnum(2, this.direction_.getNumber());
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
                size += CodedOutputStream.computeMessageSize(1, this.expr_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size += CodedOutputStream.computeEnumSize(2, this.direction_.getNumber());
            }
            this.memoizedSerializedSize = size += this.getUnknownFields().getSerializedSize();
            return size;
        }

        @Override
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static Order parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static Order parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static Order parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static Order parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static Order parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static Order parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Order parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static Order parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static Order parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static Order parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override
        public Builder newBuilderForType() {
            return Order.newBuilder();
        }

        public static Builder newBuilder(Order prototype) {
            return Order.newBuilder().mergeFrom(prototype);
        }

        @Override
        public Builder toBuilder() {
            return Order.newBuilder(this);
        }

        @Override
        protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        static {
            PARSER = new AbstractParser<Order>(){

                @Override
                public Order parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                    return new Order(input, extensionRegistry);
                }
            };
            defaultInstance = new Order(true);
            defaultInstance.initFields();
        }

        public static final class Builder
        extends GeneratedMessage.Builder<Builder>
        implements OrderOrBuilder {
            private int bitField0_;
            private MysqlxExpr.Expr expr_ = MysqlxExpr.Expr.getDefaultInstance();
            private SingleFieldBuilder<MysqlxExpr.Expr, MysqlxExpr.Expr.Builder, MysqlxExpr.ExprOrBuilder> exprBuilder_;
            private Direction direction_ = Direction.ASC;

            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_Mysqlx_Crud_Order_descriptor;
            }

            @Override
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_Mysqlx_Crud_Order_fieldAccessorTable.ensureFieldAccessorsInitialized(Order.class, Builder.class);
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
                    this.getExprFieldBuilder();
                }
            }

            private static Builder create() {
                return new Builder();
            }

            @Override
            public Builder clear() {
                super.clear();
                if (this.exprBuilder_ == null) {
                    this.expr_ = MysqlxExpr.Expr.getDefaultInstance();
                } else {
                    this.exprBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFFFE;
                this.direction_ = Direction.ASC;
                this.bitField0_ &= 0xFFFFFFFD;
                return this;
            }

            @Override
            public Builder clone() {
                return Builder.create().mergeFrom(this.buildPartial());
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return internal_static_Mysqlx_Crud_Order_descriptor;
            }

            @Override
            public Order getDefaultInstanceForType() {
                return Order.getDefaultInstance();
            }

            @Override
            public Order build() {
                Order result = this.buildPartial();
                if (!result.isInitialized()) {
                    throw Builder.newUninitializedMessageException(result);
                }
                return result;
            }

            @Override
            public Order buildPartial() {
                Order result = new Order(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ |= 1;
                }
                if (this.exprBuilder_ == null) {
                    result.expr_ = this.expr_;
                } else {
                    result.expr_ = this.exprBuilder_.build();
                }
                if ((from_bitField0_ & 2) == 2) {
                    to_bitField0_ |= 2;
                }
                result.direction_ = this.direction_;
                result.bitField0_ = to_bitField0_;
                this.onBuilt();
                return result;
            }

            @Override
            public Builder mergeFrom(Message other) {
                if (other instanceof Order) {
                    return this.mergeFrom((Order)other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(Order other) {
                if (other == Order.getDefaultInstance()) {
                    return this;
                }
                if (other.hasExpr()) {
                    this.mergeExpr(other.getExpr());
                }
                if (other.hasDirection()) {
                    this.setDirection(other.getDirection());
                }
                this.mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            @Override
            public final boolean isInitialized() {
                if (!this.hasExpr()) {
                    return false;
                }
                return this.getExpr().isInitialized();
            }

            @Override
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                Order parsedMessage = null;
                try {
                    parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
                } catch (InvalidProtocolBufferException e) {
                    parsedMessage = (Order)e.getUnfinishedMessage();
                    throw e;
                } finally {
                    if (parsedMessage != null) {
                        this.mergeFrom(parsedMessage);
                    }
                }
                return this;
            }

            @Override
            public boolean hasExpr() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override
            public MysqlxExpr.Expr getExpr() {
                if (this.exprBuilder_ == null) {
                    return this.expr_;
                }
                return this.exprBuilder_.getMessage();
            }

            public Builder setExpr(MysqlxExpr.Expr value) {
                if (this.exprBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.expr_ = value;
                    this.onChanged();
                } else {
                    this.exprBuilder_.setMessage(value);
                }
                this.bitField0_ |= 1;
                return this;
            }

            public Builder setExpr(MysqlxExpr.Expr.Builder builderForValue) {
                if (this.exprBuilder_ == null) {
                    this.expr_ = builderForValue.build();
                    this.onChanged();
                } else {
                    this.exprBuilder_.setMessage(builderForValue.build());
                }
                this.bitField0_ |= 1;
                return this;
            }

            public Builder mergeExpr(MysqlxExpr.Expr value) {
                if (this.exprBuilder_ == null) {
                    this.expr_ = (this.bitField0_ & 1) == 1 && this.expr_ != MysqlxExpr.Expr.getDefaultInstance() ? MysqlxExpr.Expr.newBuilder(this.expr_).mergeFrom(value).buildPartial() : value;
                    this.onChanged();
                } else {
                    this.exprBuilder_.mergeFrom(value);
                }
                this.bitField0_ |= 1;
                return this;
            }

            public Builder clearExpr() {
                if (this.exprBuilder_ == null) {
                    this.expr_ = MysqlxExpr.Expr.getDefaultInstance();
                    this.onChanged();
                } else {
                    this.exprBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFFFE;
                return this;
            }

            public MysqlxExpr.Expr.Builder getExprBuilder() {
                this.bitField0_ |= 1;
                this.onChanged();
                return this.getExprFieldBuilder().getBuilder();
            }

            @Override
            public MysqlxExpr.ExprOrBuilder getExprOrBuilder() {
                if (this.exprBuilder_ != null) {
                    return this.exprBuilder_.getMessageOrBuilder();
                }
                return this.expr_;
            }

            private SingleFieldBuilder<MysqlxExpr.Expr, MysqlxExpr.Expr.Builder, MysqlxExpr.ExprOrBuilder> getExprFieldBuilder() {
                if (this.exprBuilder_ == null) {
                    this.exprBuilder_ = new SingleFieldBuilder(this.getExpr(), this.getParentForChildren(), this.isClean());
                    this.expr_ = null;
                }
                return this.exprBuilder_;
            }

            @Override
            public boolean hasDirection() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override
            public Direction getDirection() {
                return this.direction_;
            }

            public Builder setDirection(Direction value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 2;
                this.direction_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearDirection() {
                this.bitField0_ &= 0xFFFFFFFD;
                this.direction_ = Direction.ASC;
                this.onChanged();
                return this;
            }
        }

        public static enum Direction implements ProtocolMessageEnum
        {
            ASC(0, 1),
            DESC(1, 2);

            public static final int ASC_VALUE = 1;
            public static final int DESC_VALUE = 2;
            private static Internal.EnumLiteMap<Direction> internalValueMap;
            private static final Direction[] VALUES;
            private final int index;
            private final int value;

            @Override
            public final int getNumber() {
                return this.value;
            }

            public static Direction valueOf(int value) {
                switch (value) {
                    case 1: {
                        return ASC;
                    }
                    case 2: {
                        return DESC;
                    }
                }
                return null;
            }

            public static Internal.EnumLiteMap<Direction> internalGetValueMap() {
                return internalValueMap;
            }

            @Override
            public final Descriptors.EnumValueDescriptor getValueDescriptor() {
                return Direction.getDescriptor().getValues().get(this.index);
            }

            @Override
            public final Descriptors.EnumDescriptor getDescriptorForType() {
                return Direction.getDescriptor();
            }

            public static final Descriptors.EnumDescriptor getDescriptor() {
                return Order.getDescriptor().getEnumTypes().get(0);
            }

            public static Direction valueOf(Descriptors.EnumValueDescriptor desc) {
                if (desc.getType() != Direction.getDescriptor()) {
                    throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
                }
                return VALUES[desc.getIndex()];
            }

            private Direction(int index, int value) {
                this.index = index;
                this.value = value;
            }

            static {
                internalValueMap = new Internal.EnumLiteMap<Direction>(){

                    @Override
                    public Direction findValueByNumber(int number) {
                        return Direction.valueOf(number);
                    }
                };
                VALUES = Direction.values();
            }
        }
    }

    public static interface OrderOrBuilder
    extends MessageOrBuilder {
        public boolean hasExpr();

        public MysqlxExpr.Expr getExpr();

        public MysqlxExpr.ExprOrBuilder getExprOrBuilder();

        public boolean hasDirection();

        public Order.Direction getDirection();
    }

    public static final class Limit
    extends GeneratedMessage
    implements LimitOrBuilder {
        private static final Limit defaultInstance;
        private final UnknownFieldSet unknownFields;
        public static Parser<Limit> PARSER;
        private int bitField0_;
        public static final int ROW_COUNT_FIELD_NUMBER = 1;
        private long rowCount_;
        public static final int OFFSET_FIELD_NUMBER = 2;
        private long offset_;
        private byte memoizedIsInitialized = (byte)-1;
        private int memoizedSerializedSize = -1;
        private static final long serialVersionUID = 0L;

        private Limit(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.unknownFields = builder.getUnknownFields();
        }

        private Limit(boolean noInit) {
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static Limit getDefaultInstance() {
            return defaultInstance;
        }

        @Override
        public Limit getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private Limit(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
                        case 8: {
                            this.bitField0_ |= 1;
                            this.rowCount_ = input.readUInt64();
                            continue block11;
                        }
                        case 16: 
                    }
                    this.bitField0_ |= 2;
                    this.offset_ = input.readUInt64();
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
            return internal_static_Mysqlx_Crud_Limit_descriptor;
        }

        @Override
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return internal_static_Mysqlx_Crud_Limit_fieldAccessorTable.ensureFieldAccessorsInitialized(Limit.class, Builder.class);
        }

        public Parser<Limit> getParserForType() {
            return PARSER;
        }

        @Override
        public boolean hasRowCount() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override
        public long getRowCount() {
            return this.rowCount_;
        }

        @Override
        public boolean hasOffset() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override
        public long getOffset() {
            return this.offset_;
        }

        private void initFields() {
            this.rowCount_ = 0L;
            this.offset_ = 0L;
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
            if (!this.hasRowCount()) {
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
                output.writeUInt64(1, this.rowCount_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeUInt64(2, this.offset_);
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
                size += CodedOutputStream.computeUInt64Size(1, this.rowCount_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size += CodedOutputStream.computeUInt64Size(2, this.offset_);
            }
            this.memoizedSerializedSize = size += this.getUnknownFields().getSerializedSize();
            return size;
        }

        @Override
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static Limit parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static Limit parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static Limit parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static Limit parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static Limit parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static Limit parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Limit parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static Limit parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static Limit parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static Limit parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override
        public Builder newBuilderForType() {
            return Limit.newBuilder();
        }

        public static Builder newBuilder(Limit prototype) {
            return Limit.newBuilder().mergeFrom(prototype);
        }

        @Override
        public Builder toBuilder() {
            return Limit.newBuilder(this);
        }

        @Override
        protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        static {
            PARSER = new AbstractParser<Limit>(){

                @Override
                public Limit parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                    return new Limit(input, extensionRegistry);
                }
            };
            defaultInstance = new Limit(true);
            defaultInstance.initFields();
        }

        public static final class Builder
        extends GeneratedMessage.Builder<Builder>
        implements LimitOrBuilder {
            private int bitField0_;
            private long rowCount_;
            private long offset_;

            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_Mysqlx_Crud_Limit_descriptor;
            }

            @Override
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_Mysqlx_Crud_Limit_fieldAccessorTable.ensureFieldAccessorsInitialized(Limit.class, Builder.class);
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
                this.rowCount_ = 0L;
                this.bitField0_ &= 0xFFFFFFFE;
                this.offset_ = 0L;
                this.bitField0_ &= 0xFFFFFFFD;
                return this;
            }

            @Override
            public Builder clone() {
                return Builder.create().mergeFrom(this.buildPartial());
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return internal_static_Mysqlx_Crud_Limit_descriptor;
            }

            @Override
            public Limit getDefaultInstanceForType() {
                return Limit.getDefaultInstance();
            }

            @Override
            public Limit build() {
                Limit result = this.buildPartial();
                if (!result.isInitialized()) {
                    throw Builder.newUninitializedMessageException(result);
                }
                return result;
            }

            @Override
            public Limit buildPartial() {
                Limit result = new Limit(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ |= 1;
                }
                result.rowCount_ = this.rowCount_;
                if ((from_bitField0_ & 2) == 2) {
                    to_bitField0_ |= 2;
                }
                result.offset_ = this.offset_;
                result.bitField0_ = to_bitField0_;
                this.onBuilt();
                return result;
            }

            @Override
            public Builder mergeFrom(Message other) {
                if (other instanceof Limit) {
                    return this.mergeFrom((Limit)other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(Limit other) {
                if (other == Limit.getDefaultInstance()) {
                    return this;
                }
                if (other.hasRowCount()) {
                    this.setRowCount(other.getRowCount());
                }
                if (other.hasOffset()) {
                    this.setOffset(other.getOffset());
                }
                this.mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            @Override
            public final boolean isInitialized() {
                return this.hasRowCount();
            }

            @Override
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                Limit parsedMessage = null;
                try {
                    parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
                } catch (InvalidProtocolBufferException e) {
                    parsedMessage = (Limit)e.getUnfinishedMessage();
                    throw e;
                } finally {
                    if (parsedMessage != null) {
                        this.mergeFrom(parsedMessage);
                    }
                }
                return this;
            }

            @Override
            public boolean hasRowCount() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override
            public long getRowCount() {
                return this.rowCount_;
            }

            public Builder setRowCount(long value) {
                this.bitField0_ |= 1;
                this.rowCount_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearRowCount() {
                this.bitField0_ &= 0xFFFFFFFE;
                this.rowCount_ = 0L;
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasOffset() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override
            public long getOffset() {
                return this.offset_;
            }

            public Builder setOffset(long value) {
                this.bitField0_ |= 2;
                this.offset_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearOffset() {
                this.bitField0_ &= 0xFFFFFFFD;
                this.offset_ = 0L;
                this.onChanged();
                return this;
            }
        }
    }

    public static interface LimitOrBuilder
    extends MessageOrBuilder {
        public boolean hasRowCount();

        public long getRowCount();

        public boolean hasOffset();

        public long getOffset();
    }

    public static final class Collection
    extends GeneratedMessage
    implements CollectionOrBuilder {
        private static final Collection defaultInstance;
        private final UnknownFieldSet unknownFields;
        public static Parser<Collection> PARSER;
        private int bitField0_;
        public static final int NAME_FIELD_NUMBER = 1;
        private Object name_;
        public static final int SCHEMA_FIELD_NUMBER = 2;
        private Object schema_;
        private byte memoizedIsInitialized = (byte)-1;
        private int memoizedSerializedSize = -1;
        private static final long serialVersionUID = 0L;

        private Collection(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.unknownFields = builder.getUnknownFields();
        }

        private Collection(boolean noInit) {
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static Collection getDefaultInstance() {
            return defaultInstance;
        }

        @Override
        public Collection getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private Collection(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
                    this.schema_ = bs;
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
            return internal_static_Mysqlx_Crud_Collection_descriptor;
        }

        @Override
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return internal_static_Mysqlx_Crud_Collection_fieldAccessorTable.ensureFieldAccessorsInitialized(Collection.class, Builder.class);
        }

        public Parser<Collection> getParserForType() {
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
        public boolean hasSchema() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override
        public String getSchema() {
            Object ref = this.schema_;
            if (ref instanceof String) {
                return (String)ref;
            }
            ByteString bs = (ByteString)ref;
            String s2 = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.schema_ = s2;
            }
            return s2;
        }

        @Override
        public ByteString getSchemaBytes() {
            Object ref = this.schema_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.schema_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        private void initFields() {
            this.name_ = "";
            this.schema_ = "";
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
                output.writeBytes(2, this.getSchemaBytes());
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
                size += CodedOutputStream.computeBytesSize(2, this.getSchemaBytes());
            }
            this.memoizedSerializedSize = size += this.getUnknownFields().getSerializedSize();
            return size;
        }

        @Override
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static Collection parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static Collection parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static Collection parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static Collection parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static Collection parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static Collection parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Collection parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static Collection parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static Collection parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static Collection parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override
        public Builder newBuilderForType() {
            return Collection.newBuilder();
        }

        public static Builder newBuilder(Collection prototype) {
            return Collection.newBuilder().mergeFrom(prototype);
        }

        @Override
        public Builder toBuilder() {
            return Collection.newBuilder(this);
        }

        @Override
        protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        static {
            PARSER = new AbstractParser<Collection>(){

                @Override
                public Collection parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                    return new Collection(input, extensionRegistry);
                }
            };
            defaultInstance = new Collection(true);
            defaultInstance.initFields();
        }

        public static final class Builder
        extends GeneratedMessage.Builder<Builder>
        implements CollectionOrBuilder {
            private int bitField0_;
            private Object name_ = "";
            private Object schema_ = "";

            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_Mysqlx_Crud_Collection_descriptor;
            }

            @Override
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_Mysqlx_Crud_Collection_fieldAccessorTable.ensureFieldAccessorsInitialized(Collection.class, Builder.class);
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
                this.schema_ = "";
                this.bitField0_ &= 0xFFFFFFFD;
                return this;
            }

            @Override
            public Builder clone() {
                return Builder.create().mergeFrom(this.buildPartial());
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return internal_static_Mysqlx_Crud_Collection_descriptor;
            }

            @Override
            public Collection getDefaultInstanceForType() {
                return Collection.getDefaultInstance();
            }

            @Override
            public Collection build() {
                Collection result = this.buildPartial();
                if (!result.isInitialized()) {
                    throw Builder.newUninitializedMessageException(result);
                }
                return result;
            }

            @Override
            public Collection buildPartial() {
                Collection result = new Collection(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ |= 1;
                }
                result.name_ = this.name_;
                if ((from_bitField0_ & 2) == 2) {
                    to_bitField0_ |= 2;
                }
                result.schema_ = this.schema_;
                result.bitField0_ = to_bitField0_;
                this.onBuilt();
                return result;
            }

            @Override
            public Builder mergeFrom(Message other) {
                if (other instanceof Collection) {
                    return this.mergeFrom((Collection)other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(Collection other) {
                if (other == Collection.getDefaultInstance()) {
                    return this;
                }
                if (other.hasName()) {
                    this.bitField0_ |= 1;
                    this.name_ = other.name_;
                    this.onChanged();
                }
                if (other.hasSchema()) {
                    this.bitField0_ |= 2;
                    this.schema_ = other.schema_;
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
                Collection parsedMessage = null;
                try {
                    parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
                } catch (InvalidProtocolBufferException e) {
                    parsedMessage = (Collection)e.getUnfinishedMessage();
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
                this.name_ = Collection.getDefaultInstance().getName();
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
            public boolean hasSchema() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override
            public String getSchema() {
                Object ref = this.schema_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString)ref;
                    String s2 = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.schema_ = s2;
                    }
                    return s2;
                }
                return (String)ref;
            }

            @Override
            public ByteString getSchemaBytes() {
                Object ref = this.schema_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String)ref);
                    this.schema_ = b;
                    return b;
                }
                return (ByteString)ref;
            }

            public Builder setSchema(String value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 2;
                this.schema_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearSchema() {
                this.bitField0_ &= 0xFFFFFFFD;
                this.schema_ = Collection.getDefaultInstance().getSchema();
                this.onChanged();
                return this;
            }

            public Builder setSchemaBytes(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 2;
                this.schema_ = value;
                this.onChanged();
                return this;
            }
        }
    }

    public static interface CollectionOrBuilder
    extends MessageOrBuilder {
        public boolean hasName();

        public String getName();

        public ByteString getNameBytes();

        public boolean hasSchema();

        public String getSchema();

        public ByteString getSchemaBytes();
    }

    public static final class Projection
    extends GeneratedMessage
    implements ProjectionOrBuilder {
        private static final Projection defaultInstance;
        private final UnknownFieldSet unknownFields;
        public static Parser<Projection> PARSER;
        private int bitField0_;
        public static final int SOURCE_FIELD_NUMBER = 1;
        private MysqlxExpr.Expr source_;
        public static final int ALIAS_FIELD_NUMBER = 2;
        private Object alias_;
        private byte memoizedIsInitialized = (byte)-1;
        private int memoizedSerializedSize = -1;
        private static final long serialVersionUID = 0L;

        private Projection(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.unknownFields = builder.getUnknownFields();
        }

        private Projection(boolean noInit) {
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static Projection getDefaultInstance() {
            return defaultInstance;
        }

        @Override
        public Projection getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private Projection(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
                            MysqlxExpr.Expr.Builder subBuilder = null;
                            if ((this.bitField0_ & 1) == 1) {
                                subBuilder = this.source_.toBuilder();
                            }
                            this.source_ = input.readMessage(MysqlxExpr.Expr.PARSER, extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom(this.source_);
                                this.source_ = subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 1;
                            continue block11;
                        }
                        case 18: 
                    }
                    ByteString bs = input.readBytes();
                    this.bitField0_ |= 2;
                    this.alias_ = bs;
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
            return internal_static_Mysqlx_Crud_Projection_descriptor;
        }

        @Override
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return internal_static_Mysqlx_Crud_Projection_fieldAccessorTable.ensureFieldAccessorsInitialized(Projection.class, Builder.class);
        }

        public Parser<Projection> getParserForType() {
            return PARSER;
        }

        @Override
        public boolean hasSource() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override
        public MysqlxExpr.Expr getSource() {
            return this.source_;
        }

        @Override
        public MysqlxExpr.ExprOrBuilder getSourceOrBuilder() {
            return this.source_;
        }

        @Override
        public boolean hasAlias() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override
        public String getAlias() {
            Object ref = this.alias_;
            if (ref instanceof String) {
                return (String)ref;
            }
            ByteString bs = (ByteString)ref;
            String s2 = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.alias_ = s2;
            }
            return s2;
        }

        @Override
        public ByteString getAliasBytes() {
            Object ref = this.alias_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.alias_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        private void initFields() {
            this.source_ = MysqlxExpr.Expr.getDefaultInstance();
            this.alias_ = "";
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
            if (!this.hasSource()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            if (!this.getSource().isInitialized()) {
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
                output.writeMessage(1, this.source_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeBytes(2, this.getAliasBytes());
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
                size += CodedOutputStream.computeMessageSize(1, this.source_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size += CodedOutputStream.computeBytesSize(2, this.getAliasBytes());
            }
            this.memoizedSerializedSize = size += this.getUnknownFields().getSerializedSize();
            return size;
        }

        @Override
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static Projection parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static Projection parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static Projection parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static Projection parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static Projection parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static Projection parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Projection parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static Projection parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static Projection parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static Projection parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override
        public Builder newBuilderForType() {
            return Projection.newBuilder();
        }

        public static Builder newBuilder(Projection prototype) {
            return Projection.newBuilder().mergeFrom(prototype);
        }

        @Override
        public Builder toBuilder() {
            return Projection.newBuilder(this);
        }

        @Override
        protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        static {
            PARSER = new AbstractParser<Projection>(){

                @Override
                public Projection parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                    return new Projection(input, extensionRegistry);
                }
            };
            defaultInstance = new Projection(true);
            defaultInstance.initFields();
        }

        public static final class Builder
        extends GeneratedMessage.Builder<Builder>
        implements ProjectionOrBuilder {
            private int bitField0_;
            private MysqlxExpr.Expr source_ = MysqlxExpr.Expr.getDefaultInstance();
            private SingleFieldBuilder<MysqlxExpr.Expr, MysqlxExpr.Expr.Builder, MysqlxExpr.ExprOrBuilder> sourceBuilder_;
            private Object alias_ = "";

            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_Mysqlx_Crud_Projection_descriptor;
            }

            @Override
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_Mysqlx_Crud_Projection_fieldAccessorTable.ensureFieldAccessorsInitialized(Projection.class, Builder.class);
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
                    this.getSourceFieldBuilder();
                }
            }

            private static Builder create() {
                return new Builder();
            }

            @Override
            public Builder clear() {
                super.clear();
                if (this.sourceBuilder_ == null) {
                    this.source_ = MysqlxExpr.Expr.getDefaultInstance();
                } else {
                    this.sourceBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFFFE;
                this.alias_ = "";
                this.bitField0_ &= 0xFFFFFFFD;
                return this;
            }

            @Override
            public Builder clone() {
                return Builder.create().mergeFrom(this.buildPartial());
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return internal_static_Mysqlx_Crud_Projection_descriptor;
            }

            @Override
            public Projection getDefaultInstanceForType() {
                return Projection.getDefaultInstance();
            }

            @Override
            public Projection build() {
                Projection result = this.buildPartial();
                if (!result.isInitialized()) {
                    throw Builder.newUninitializedMessageException(result);
                }
                return result;
            }

            @Override
            public Projection buildPartial() {
                Projection result = new Projection(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ |= 1;
                }
                if (this.sourceBuilder_ == null) {
                    result.source_ = this.source_;
                } else {
                    result.source_ = this.sourceBuilder_.build();
                }
                if ((from_bitField0_ & 2) == 2) {
                    to_bitField0_ |= 2;
                }
                result.alias_ = this.alias_;
                result.bitField0_ = to_bitField0_;
                this.onBuilt();
                return result;
            }

            @Override
            public Builder mergeFrom(Message other) {
                if (other instanceof Projection) {
                    return this.mergeFrom((Projection)other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(Projection other) {
                if (other == Projection.getDefaultInstance()) {
                    return this;
                }
                if (other.hasSource()) {
                    this.mergeSource(other.getSource());
                }
                if (other.hasAlias()) {
                    this.bitField0_ |= 2;
                    this.alias_ = other.alias_;
                    this.onChanged();
                }
                this.mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            @Override
            public final boolean isInitialized() {
                if (!this.hasSource()) {
                    return false;
                }
                return this.getSource().isInitialized();
            }

            @Override
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                Projection parsedMessage = null;
                try {
                    parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
                } catch (InvalidProtocolBufferException e) {
                    parsedMessage = (Projection)e.getUnfinishedMessage();
                    throw e;
                } finally {
                    if (parsedMessage != null) {
                        this.mergeFrom(parsedMessage);
                    }
                }
                return this;
            }

            @Override
            public boolean hasSource() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override
            public MysqlxExpr.Expr getSource() {
                if (this.sourceBuilder_ == null) {
                    return this.source_;
                }
                return this.sourceBuilder_.getMessage();
            }

            public Builder setSource(MysqlxExpr.Expr value) {
                if (this.sourceBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.source_ = value;
                    this.onChanged();
                } else {
                    this.sourceBuilder_.setMessage(value);
                }
                this.bitField0_ |= 1;
                return this;
            }

            public Builder setSource(MysqlxExpr.Expr.Builder builderForValue) {
                if (this.sourceBuilder_ == null) {
                    this.source_ = builderForValue.build();
                    this.onChanged();
                } else {
                    this.sourceBuilder_.setMessage(builderForValue.build());
                }
                this.bitField0_ |= 1;
                return this;
            }

            public Builder mergeSource(MysqlxExpr.Expr value) {
                if (this.sourceBuilder_ == null) {
                    this.source_ = (this.bitField0_ & 1) == 1 && this.source_ != MysqlxExpr.Expr.getDefaultInstance() ? MysqlxExpr.Expr.newBuilder(this.source_).mergeFrom(value).buildPartial() : value;
                    this.onChanged();
                } else {
                    this.sourceBuilder_.mergeFrom(value);
                }
                this.bitField0_ |= 1;
                return this;
            }

            public Builder clearSource() {
                if (this.sourceBuilder_ == null) {
                    this.source_ = MysqlxExpr.Expr.getDefaultInstance();
                    this.onChanged();
                } else {
                    this.sourceBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFFFE;
                return this;
            }

            public MysqlxExpr.Expr.Builder getSourceBuilder() {
                this.bitField0_ |= 1;
                this.onChanged();
                return this.getSourceFieldBuilder().getBuilder();
            }

            @Override
            public MysqlxExpr.ExprOrBuilder getSourceOrBuilder() {
                if (this.sourceBuilder_ != null) {
                    return this.sourceBuilder_.getMessageOrBuilder();
                }
                return this.source_;
            }

            private SingleFieldBuilder<MysqlxExpr.Expr, MysqlxExpr.Expr.Builder, MysqlxExpr.ExprOrBuilder> getSourceFieldBuilder() {
                if (this.sourceBuilder_ == null) {
                    this.sourceBuilder_ = new SingleFieldBuilder(this.getSource(), this.getParentForChildren(), this.isClean());
                    this.source_ = null;
                }
                return this.sourceBuilder_;
            }

            @Override
            public boolean hasAlias() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override
            public String getAlias() {
                Object ref = this.alias_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString)ref;
                    String s2 = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.alias_ = s2;
                    }
                    return s2;
                }
                return (String)ref;
            }

            @Override
            public ByteString getAliasBytes() {
                Object ref = this.alias_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String)ref);
                    this.alias_ = b;
                    return b;
                }
                return (ByteString)ref;
            }

            public Builder setAlias(String value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 2;
                this.alias_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearAlias() {
                this.bitField0_ &= 0xFFFFFFFD;
                this.alias_ = Projection.getDefaultInstance().getAlias();
                this.onChanged();
                return this;
            }

            public Builder setAliasBytes(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 2;
                this.alias_ = value;
                this.onChanged();
                return this;
            }
        }
    }

    public static interface ProjectionOrBuilder
    extends MessageOrBuilder {
        public boolean hasSource();

        public MysqlxExpr.Expr getSource();

        public MysqlxExpr.ExprOrBuilder getSourceOrBuilder();

        public boolean hasAlias();

        public String getAlias();

        public ByteString getAliasBytes();
    }

    public static final class Column
    extends GeneratedMessage
    implements ColumnOrBuilder {
        private static final Column defaultInstance;
        private final UnknownFieldSet unknownFields;
        public static Parser<Column> PARSER;
        private int bitField0_;
        public static final int NAME_FIELD_NUMBER = 1;
        private Object name_;
        public static final int ALIAS_FIELD_NUMBER = 2;
        private Object alias_;
        public static final int DOCUMENT_PATH_FIELD_NUMBER = 3;
        private List<MysqlxExpr.DocumentPathItem> documentPath_;
        private byte memoizedIsInitialized = (byte)-1;
        private int memoizedSerializedSize = -1;
        private static final long serialVersionUID = 0L;

        private Column(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.unknownFields = builder.getUnknownFields();
        }

        private Column(boolean noInit) {
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static Column getDefaultInstance() {
            return defaultInstance;
        }

        @Override
        public Column getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private Column(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this.initFields();
            int mutable_bitField0_ = 0;
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
                            this.name_ = bs;
                            continue block12;
                        }
                        case 18: {
                            ByteString bs = input.readBytes();
                            this.bitField0_ |= 2;
                            this.alias_ = bs;
                            continue block12;
                        }
                        case 26: 
                    }
                    if ((mutable_bitField0_ & 4) != 4) {
                        this.documentPath_ = new ArrayList<MysqlxExpr.DocumentPathItem>();
                        mutable_bitField0_ |= 4;
                    }
                    this.documentPath_.add(input.readMessage(MysqlxExpr.DocumentPathItem.PARSER, extensionRegistry));
                }
            } catch (InvalidProtocolBufferException e) {
                throw e.setUnfinishedMessage(this);
            } catch (IOException e) {
                throw new InvalidProtocolBufferException(e.getMessage()).setUnfinishedMessage(this);
            } finally {
                if ((mutable_bitField0_ & 4) == 4) {
                    this.documentPath_ = Collections.unmodifiableList(this.documentPath_);
                }
                this.unknownFields = unknownFields.build();
                this.makeExtensionsImmutable();
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return internal_static_Mysqlx_Crud_Column_descriptor;
        }

        @Override
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return internal_static_Mysqlx_Crud_Column_fieldAccessorTable.ensureFieldAccessorsInitialized(Column.class, Builder.class);
        }

        public Parser<Column> getParserForType() {
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
        public boolean hasAlias() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override
        public String getAlias() {
            Object ref = this.alias_;
            if (ref instanceof String) {
                return (String)ref;
            }
            ByteString bs = (ByteString)ref;
            String s2 = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.alias_ = s2;
            }
            return s2;
        }

        @Override
        public ByteString getAliasBytes() {
            Object ref = this.alias_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.alias_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        @Override
        public List<MysqlxExpr.DocumentPathItem> getDocumentPathList() {
            return this.documentPath_;
        }

        @Override
        public List<? extends MysqlxExpr.DocumentPathItemOrBuilder> getDocumentPathOrBuilderList() {
            return this.documentPath_;
        }

        @Override
        public int getDocumentPathCount() {
            return this.documentPath_.size();
        }

        @Override
        public MysqlxExpr.DocumentPathItem getDocumentPath(int index) {
            return this.documentPath_.get(index);
        }

        @Override
        public MysqlxExpr.DocumentPathItemOrBuilder getDocumentPathOrBuilder(int index) {
            return this.documentPath_.get(index);
        }

        private void initFields() {
            this.name_ = "";
            this.alias_ = "";
            this.documentPath_ = Collections.emptyList();
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
            if ((this.bitField0_ & 1) == 1) {
                output.writeBytes(1, this.getNameBytes());
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeBytes(2, this.getAliasBytes());
            }
            for (int i = 0; i < this.documentPath_.size(); ++i) {
                output.writeMessage(3, this.documentPath_.get(i));
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
                size += CodedOutputStream.computeBytesSize(2, this.getAliasBytes());
            }
            for (int i = 0; i < this.documentPath_.size(); ++i) {
                size += CodedOutputStream.computeMessageSize(3, this.documentPath_.get(i));
            }
            this.memoizedSerializedSize = size += this.getUnknownFields().getSerializedSize();
            return size;
        }

        @Override
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static Column parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static Column parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static Column parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static Column parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static Column parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static Column parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Column parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static Column parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static Column parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static Column parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override
        public Builder newBuilderForType() {
            return Column.newBuilder();
        }

        public static Builder newBuilder(Column prototype) {
            return Column.newBuilder().mergeFrom(prototype);
        }

        @Override
        public Builder toBuilder() {
            return Column.newBuilder(this);
        }

        @Override
        protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        static {
            PARSER = new AbstractParser<Column>(){

                @Override
                public Column parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                    return new Column(input, extensionRegistry);
                }
            };
            defaultInstance = new Column(true);
            defaultInstance.initFields();
        }

        public static final class Builder
        extends GeneratedMessage.Builder<Builder>
        implements ColumnOrBuilder {
            private int bitField0_;
            private Object name_ = "";
            private Object alias_ = "";
            private List<MysqlxExpr.DocumentPathItem> documentPath_ = Collections.emptyList();
            private RepeatedFieldBuilder<MysqlxExpr.DocumentPathItem, MysqlxExpr.DocumentPathItem.Builder, MysqlxExpr.DocumentPathItemOrBuilder> documentPathBuilder_;

            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_Mysqlx_Crud_Column_descriptor;
            }

            @Override
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_Mysqlx_Crud_Column_fieldAccessorTable.ensureFieldAccessorsInitialized(Column.class, Builder.class);
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
                this.name_ = "";
                this.bitField0_ &= 0xFFFFFFFE;
                this.alias_ = "";
                this.bitField0_ &= 0xFFFFFFFD;
                if (this.documentPathBuilder_ == null) {
                    this.documentPath_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFFFFB;
                } else {
                    this.documentPathBuilder_.clear();
                }
                return this;
            }

            @Override
            public Builder clone() {
                return Builder.create().mergeFrom(this.buildPartial());
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return internal_static_Mysqlx_Crud_Column_descriptor;
            }

            @Override
            public Column getDefaultInstanceForType() {
                return Column.getDefaultInstance();
            }

            @Override
            public Column build() {
                Column result = this.buildPartial();
                if (!result.isInitialized()) {
                    throw Builder.newUninitializedMessageException(result);
                }
                return result;
            }

            @Override
            public Column buildPartial() {
                Column result = new Column(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ |= 1;
                }
                result.name_ = this.name_;
                if ((from_bitField0_ & 2) == 2) {
                    to_bitField0_ |= 2;
                }
                result.alias_ = this.alias_;
                if (this.documentPathBuilder_ == null) {
                    if ((this.bitField0_ & 4) == 4) {
                        this.documentPath_ = Collections.unmodifiableList(this.documentPath_);
                        this.bitField0_ &= 0xFFFFFFFB;
                    }
                    result.documentPath_ = this.documentPath_;
                } else {
                    result.documentPath_ = this.documentPathBuilder_.build();
                }
                result.bitField0_ = to_bitField0_;
                this.onBuilt();
                return result;
            }

            @Override
            public Builder mergeFrom(Message other) {
                if (other instanceof Column) {
                    return this.mergeFrom((Column)other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(Column other) {
                if (other == Column.getDefaultInstance()) {
                    return this;
                }
                if (other.hasName()) {
                    this.bitField0_ |= 1;
                    this.name_ = other.name_;
                    this.onChanged();
                }
                if (other.hasAlias()) {
                    this.bitField0_ |= 2;
                    this.alias_ = other.alias_;
                    this.onChanged();
                }
                if (this.documentPathBuilder_ == null) {
                    if (!other.documentPath_.isEmpty()) {
                        if (this.documentPath_.isEmpty()) {
                            this.documentPath_ = other.documentPath_;
                            this.bitField0_ &= 0xFFFFFFFB;
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
                        this.bitField0_ &= 0xFFFFFFFB;
                        this.documentPathBuilder_ = alwaysUseFieldBuilders ? this.getDocumentPathFieldBuilder() : null;
                    } else {
                        this.documentPathBuilder_.addAllMessages(other.documentPath_);
                    }
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
                Column parsedMessage = null;
                try {
                    parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
                } catch (InvalidProtocolBufferException e) {
                    parsedMessage = (Column)e.getUnfinishedMessage();
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
                this.name_ = Column.getDefaultInstance().getName();
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
            public boolean hasAlias() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override
            public String getAlias() {
                Object ref = this.alias_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString)ref;
                    String s2 = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.alias_ = s2;
                    }
                    return s2;
                }
                return (String)ref;
            }

            @Override
            public ByteString getAliasBytes() {
                Object ref = this.alias_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String)ref);
                    this.alias_ = b;
                    return b;
                }
                return (ByteString)ref;
            }

            public Builder setAlias(String value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 2;
                this.alias_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearAlias() {
                this.bitField0_ &= 0xFFFFFFFD;
                this.alias_ = Column.getDefaultInstance().getAlias();
                this.onChanged();
                return this;
            }

            public Builder setAliasBytes(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 2;
                this.alias_ = value;
                this.onChanged();
                return this;
            }

            private void ensureDocumentPathIsMutable() {
                if ((this.bitField0_ & 4) != 4) {
                    this.documentPath_ = new ArrayList<MysqlxExpr.DocumentPathItem>(this.documentPath_);
                    this.bitField0_ |= 4;
                }
            }

            @Override
            public List<MysqlxExpr.DocumentPathItem> getDocumentPathList() {
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
            public MysqlxExpr.DocumentPathItem getDocumentPath(int index) {
                if (this.documentPathBuilder_ == null) {
                    return this.documentPath_.get(index);
                }
                return this.documentPathBuilder_.getMessage(index);
            }

            public Builder setDocumentPath(int index, MysqlxExpr.DocumentPathItem value) {
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

            public Builder setDocumentPath(int index, MysqlxExpr.DocumentPathItem.Builder builderForValue) {
                if (this.documentPathBuilder_ == null) {
                    this.ensureDocumentPathIsMutable();
                    this.documentPath_.set(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.documentPathBuilder_.setMessage(index, builderForValue.build());
                }
                return this;
            }

            public Builder addDocumentPath(MysqlxExpr.DocumentPathItem value) {
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

            public Builder addDocumentPath(int index, MysqlxExpr.DocumentPathItem value) {
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

            public Builder addDocumentPath(MysqlxExpr.DocumentPathItem.Builder builderForValue) {
                if (this.documentPathBuilder_ == null) {
                    this.ensureDocumentPathIsMutable();
                    this.documentPath_.add(builderForValue.build());
                    this.onChanged();
                } else {
                    this.documentPathBuilder_.addMessage(builderForValue.build());
                }
                return this;
            }

            public Builder addDocumentPath(int index, MysqlxExpr.DocumentPathItem.Builder builderForValue) {
                if (this.documentPathBuilder_ == null) {
                    this.ensureDocumentPathIsMutable();
                    this.documentPath_.add(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.documentPathBuilder_.addMessage(index, builderForValue.build());
                }
                return this;
            }

            public Builder addAllDocumentPath(Iterable<? extends MysqlxExpr.DocumentPathItem> values2) {
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
                    this.bitField0_ &= 0xFFFFFFFB;
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

            public MysqlxExpr.DocumentPathItem.Builder getDocumentPathBuilder(int index) {
                return this.getDocumentPathFieldBuilder().getBuilder(index);
            }

            @Override
            public MysqlxExpr.DocumentPathItemOrBuilder getDocumentPathOrBuilder(int index) {
                if (this.documentPathBuilder_ == null) {
                    return this.documentPath_.get(index);
                }
                return this.documentPathBuilder_.getMessageOrBuilder(index);
            }

            @Override
            public List<? extends MysqlxExpr.DocumentPathItemOrBuilder> getDocumentPathOrBuilderList() {
                if (this.documentPathBuilder_ != null) {
                    return this.documentPathBuilder_.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.documentPath_);
            }

            public MysqlxExpr.DocumentPathItem.Builder addDocumentPathBuilder() {
                return this.getDocumentPathFieldBuilder().addBuilder(MysqlxExpr.DocumentPathItem.getDefaultInstance());
            }

            public MysqlxExpr.DocumentPathItem.Builder addDocumentPathBuilder(int index) {
                return this.getDocumentPathFieldBuilder().addBuilder(index, MysqlxExpr.DocumentPathItem.getDefaultInstance());
            }

            public List<MysqlxExpr.DocumentPathItem.Builder> getDocumentPathBuilderList() {
                return this.getDocumentPathFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilder<MysqlxExpr.DocumentPathItem, MysqlxExpr.DocumentPathItem.Builder, MysqlxExpr.DocumentPathItemOrBuilder> getDocumentPathFieldBuilder() {
                if (this.documentPathBuilder_ == null) {
                    this.documentPathBuilder_ = new RepeatedFieldBuilder(this.documentPath_, (this.bitField0_ & 4) == 4, this.getParentForChildren(), this.isClean());
                    this.documentPath_ = null;
                }
                return this.documentPathBuilder_;
            }
        }
    }

    public static interface ColumnOrBuilder
    extends MessageOrBuilder {
        public boolean hasName();

        public String getName();

        public ByteString getNameBytes();

        public boolean hasAlias();

        public String getAlias();

        public ByteString getAliasBytes();

        public List<MysqlxExpr.DocumentPathItem> getDocumentPathList();

        public MysqlxExpr.DocumentPathItem getDocumentPath(int var1);

        public int getDocumentPathCount();

        public List<? extends MysqlxExpr.DocumentPathItemOrBuilder> getDocumentPathOrBuilderList();

        public MysqlxExpr.DocumentPathItemOrBuilder getDocumentPathOrBuilder(int var1);
    }

    public static enum ViewCheckOption implements ProtocolMessageEnum
    {
        LOCAL(0, 1),
        CASCADED(1, 2);

        public static final int LOCAL_VALUE = 1;
        public static final int CASCADED_VALUE = 2;
        private static Internal.EnumLiteMap<ViewCheckOption> internalValueMap;
        private static final ViewCheckOption[] VALUES;
        private final int index;
        private final int value;

        @Override
        public final int getNumber() {
            return this.value;
        }

        public static ViewCheckOption valueOf(int value) {
            switch (value) {
                case 1: {
                    return LOCAL;
                }
                case 2: {
                    return CASCADED;
                }
            }
            return null;
        }

        public static Internal.EnumLiteMap<ViewCheckOption> internalGetValueMap() {
            return internalValueMap;
        }

        @Override
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            return ViewCheckOption.getDescriptor().getValues().get(this.index);
        }

        @Override
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return ViewCheckOption.getDescriptor();
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return MysqlxCrud.getDescriptor().getEnumTypes().get(3);
        }

        public static ViewCheckOption valueOf(Descriptors.EnumValueDescriptor desc) {
            if (desc.getType() != ViewCheckOption.getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            return VALUES[desc.getIndex()];
        }

        private ViewCheckOption(int index, int value) {
            this.index = index;
            this.value = value;
        }

        static {
            internalValueMap = new Internal.EnumLiteMap<ViewCheckOption>(){

                @Override
                public ViewCheckOption findValueByNumber(int number) {
                    return ViewCheckOption.valueOf(number);
                }
            };
            VALUES = ViewCheckOption.values();
        }
    }

    public static enum ViewSqlSecurity implements ProtocolMessageEnum
    {
        INVOKER(0, 1),
        DEFINER(1, 2);

        public static final int INVOKER_VALUE = 1;
        public static final int DEFINER_VALUE = 2;
        private static Internal.EnumLiteMap<ViewSqlSecurity> internalValueMap;
        private static final ViewSqlSecurity[] VALUES;
        private final int index;
        private final int value;

        @Override
        public final int getNumber() {
            return this.value;
        }

        public static ViewSqlSecurity valueOf(int value) {
            switch (value) {
                case 1: {
                    return INVOKER;
                }
                case 2: {
                    return DEFINER;
                }
            }
            return null;
        }

        public static Internal.EnumLiteMap<ViewSqlSecurity> internalGetValueMap() {
            return internalValueMap;
        }

        @Override
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            return ViewSqlSecurity.getDescriptor().getValues().get(this.index);
        }

        @Override
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return ViewSqlSecurity.getDescriptor();
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return MysqlxCrud.getDescriptor().getEnumTypes().get(2);
        }

        public static ViewSqlSecurity valueOf(Descriptors.EnumValueDescriptor desc) {
            if (desc.getType() != ViewSqlSecurity.getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            return VALUES[desc.getIndex()];
        }

        private ViewSqlSecurity(int index, int value) {
            this.index = index;
            this.value = value;
        }

        static {
            internalValueMap = new Internal.EnumLiteMap<ViewSqlSecurity>(){

                @Override
                public ViewSqlSecurity findValueByNumber(int number) {
                    return ViewSqlSecurity.valueOf(number);
                }
            };
            VALUES = ViewSqlSecurity.values();
        }
    }

    public static enum ViewAlgorithm implements ProtocolMessageEnum
    {
        UNDEFINED(0, 1),
        MERGE(1, 2),
        TEMPTABLE(2, 3);

        public static final int UNDEFINED_VALUE = 1;
        public static final int MERGE_VALUE = 2;
        public static final int TEMPTABLE_VALUE = 3;
        private static Internal.EnumLiteMap<ViewAlgorithm> internalValueMap;
        private static final ViewAlgorithm[] VALUES;
        private final int index;
        private final int value;

        @Override
        public final int getNumber() {
            return this.value;
        }

        public static ViewAlgorithm valueOf(int value) {
            switch (value) {
                case 1: {
                    return UNDEFINED;
                }
                case 2: {
                    return MERGE;
                }
                case 3: {
                    return TEMPTABLE;
                }
            }
            return null;
        }

        public static Internal.EnumLiteMap<ViewAlgorithm> internalGetValueMap() {
            return internalValueMap;
        }

        @Override
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            return ViewAlgorithm.getDescriptor().getValues().get(this.index);
        }

        @Override
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return ViewAlgorithm.getDescriptor();
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return MysqlxCrud.getDescriptor().getEnumTypes().get(1);
        }

        public static ViewAlgorithm valueOf(Descriptors.EnumValueDescriptor desc) {
            if (desc.getType() != ViewAlgorithm.getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            return VALUES[desc.getIndex()];
        }

        private ViewAlgorithm(int index, int value) {
            this.index = index;
            this.value = value;
        }

        static {
            internalValueMap = new Internal.EnumLiteMap<ViewAlgorithm>(){

                @Override
                public ViewAlgorithm findValueByNumber(int number) {
                    return ViewAlgorithm.valueOf(number);
                }
            };
            VALUES = ViewAlgorithm.values();
        }
    }

    public static enum DataModel implements ProtocolMessageEnum
    {
        DOCUMENT(0, 1),
        TABLE(1, 2);

        public static final int DOCUMENT_VALUE = 1;
        public static final int TABLE_VALUE = 2;
        private static Internal.EnumLiteMap<DataModel> internalValueMap;
        private static final DataModel[] VALUES;
        private final int index;
        private final int value;

        @Override
        public final int getNumber() {
            return this.value;
        }

        public static DataModel valueOf(int value) {
            switch (value) {
                case 1: {
                    return DOCUMENT;
                }
                case 2: {
                    return TABLE;
                }
            }
            return null;
        }

        public static Internal.EnumLiteMap<DataModel> internalGetValueMap() {
            return internalValueMap;
        }

        @Override
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            return DataModel.getDescriptor().getValues().get(this.index);
        }

        @Override
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return DataModel.getDescriptor();
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return MysqlxCrud.getDescriptor().getEnumTypes().get(0);
        }

        public static DataModel valueOf(Descriptors.EnumValueDescriptor desc) {
            if (desc.getType() != DataModel.getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            return VALUES[desc.getIndex()];
        }

        private DataModel(int index, int value) {
            this.index = index;
            this.value = value;
        }

        static {
            internalValueMap = new Internal.EnumLiteMap<DataModel>(){

                @Override
                public DataModel findValueByNumber(int number) {
                    return DataModel.valueOf(number);
                }
            };
            VALUES = DataModel.values();
        }
    }
}

