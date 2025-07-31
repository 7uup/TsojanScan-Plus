/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.protobuf;

import com.google.protobuf.AbstractMessage;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.FieldSet;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public final class DynamicMessage
extends AbstractMessage {
    private final Descriptors.Descriptor type;
    private final FieldSet<Descriptors.FieldDescriptor> fields;
    private final Descriptors.FieldDescriptor[] oneofCases;
    private final UnknownFieldSet unknownFields;
    private int memoizedSize = -1;

    DynamicMessage(Descriptors.Descriptor type, FieldSet<Descriptors.FieldDescriptor> fields, Descriptors.FieldDescriptor[] oneofCases, UnknownFieldSet unknownFields) {
        this.type = type;
        this.fields = fields;
        this.oneofCases = oneofCases;
        this.unknownFields = unknownFields;
    }

    public static DynamicMessage getDefaultInstance(Descriptors.Descriptor type) {
        int oneofDeclCount = type.toProto().getOneofDeclCount();
        Descriptors.FieldDescriptor[] oneofCases = new Descriptors.FieldDescriptor[oneofDeclCount];
        return new DynamicMessage(type, FieldSet.<Descriptors.FieldDescriptor>emptySet(), oneofCases, UnknownFieldSet.getDefaultInstance());
    }

    public static DynamicMessage parseFrom(Descriptors.Descriptor type, CodedInputStream input) throws IOException {
        return ((Builder)DynamicMessage.newBuilder(type).mergeFrom(input)).buildParsed();
    }

    public static DynamicMessage parseFrom(Descriptors.Descriptor type, CodedInputStream input, ExtensionRegistry extensionRegistry) throws IOException {
        return ((Builder)DynamicMessage.newBuilder(type).mergeFrom(input, (ExtensionRegistryLite)extensionRegistry)).buildParsed();
    }

    public static DynamicMessage parseFrom(Descriptors.Descriptor type, ByteString data) throws InvalidProtocolBufferException {
        return ((Builder)DynamicMessage.newBuilder(type).mergeFrom(data)).buildParsed();
    }

    public static DynamicMessage parseFrom(Descriptors.Descriptor type, ByteString data, ExtensionRegistry extensionRegistry) throws InvalidProtocolBufferException {
        return ((Builder)DynamicMessage.newBuilder(type).mergeFrom(data, (ExtensionRegistryLite)extensionRegistry)).buildParsed();
    }

    public static DynamicMessage parseFrom(Descriptors.Descriptor type, byte[] data) throws InvalidProtocolBufferException {
        return ((Builder)DynamicMessage.newBuilder(type).mergeFrom(data)).buildParsed();
    }

    public static DynamicMessage parseFrom(Descriptors.Descriptor type, byte[] data, ExtensionRegistry extensionRegistry) throws InvalidProtocolBufferException {
        return ((Builder)DynamicMessage.newBuilder(type).mergeFrom(data, (ExtensionRegistryLite)extensionRegistry)).buildParsed();
    }

    public static DynamicMessage parseFrom(Descriptors.Descriptor type, InputStream input) throws IOException {
        return ((Builder)DynamicMessage.newBuilder(type).mergeFrom(input)).buildParsed();
    }

    public static DynamicMessage parseFrom(Descriptors.Descriptor type, InputStream input, ExtensionRegistry extensionRegistry) throws IOException {
        return ((Builder)DynamicMessage.newBuilder(type).mergeFrom(input, (ExtensionRegistryLite)extensionRegistry)).buildParsed();
    }

    public static Builder newBuilder(Descriptors.Descriptor type) {
        return new Builder(type);
    }

    public static Builder newBuilder(Message prototype) {
        return new Builder(prototype.getDescriptorForType()).mergeFrom(prototype);
    }

    @Override
    public Descriptors.Descriptor getDescriptorForType() {
        return this.type;
    }

    @Override
    public DynamicMessage getDefaultInstanceForType() {
        return DynamicMessage.getDefaultInstance(this.type);
    }

    @Override
    public Map<Descriptors.FieldDescriptor, Object> getAllFields() {
        return this.fields.getAllFields();
    }

    @Override
    public boolean hasOneof(Descriptors.OneofDescriptor oneof) {
        this.verifyOneofContainingType(oneof);
        Descriptors.FieldDescriptor field = this.oneofCases[oneof.getIndex()];
        return field != null;
    }

    @Override
    public Descriptors.FieldDescriptor getOneofFieldDescriptor(Descriptors.OneofDescriptor oneof) {
        this.verifyOneofContainingType(oneof);
        return this.oneofCases[oneof.getIndex()];
    }

    @Override
    public boolean hasField(Descriptors.FieldDescriptor field) {
        this.verifyContainingType(field);
        return this.fields.hasField(field);
    }

    @Override
    public Object getField(Descriptors.FieldDescriptor field) {
        this.verifyContainingType(field);
        Object result = this.fields.getField(field);
        if (result == null) {
            result = field.isRepeated() ? Collections.emptyList() : (field.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE ? DynamicMessage.getDefaultInstance(field.getMessageType()) : field.getDefaultValue());
        }
        return result;
    }

    @Override
    public int getRepeatedFieldCount(Descriptors.FieldDescriptor field) {
        this.verifyContainingType(field);
        return this.fields.getRepeatedFieldCount(field);
    }

    @Override
    public Object getRepeatedField(Descriptors.FieldDescriptor field, int index) {
        this.verifyContainingType(field);
        return this.fields.getRepeatedField(field, index);
    }

    @Override
    public UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    static boolean isInitialized(Descriptors.Descriptor type, FieldSet<Descriptors.FieldDescriptor> fields) {
        for (Descriptors.FieldDescriptor field : type.getFields()) {
            if (!field.isRequired() || fields.hasField(field)) continue;
            return false;
        }
        return fields.isInitialized();
    }

    @Override
    public boolean isInitialized() {
        return DynamicMessage.isInitialized(this.type, this.fields);
    }

    @Override
    public void writeTo(CodedOutputStream output) throws IOException {
        if (this.type.getOptions().getMessageSetWireFormat()) {
            this.fields.writeMessageSetTo(output);
            this.unknownFields.writeAsMessageSetTo(output);
        } else {
            this.fields.writeTo(output);
            this.unknownFields.writeTo(output);
        }
    }

    @Override
    public int getSerializedSize() {
        int size = this.memoizedSize;
        if (size != -1) {
            return size;
        }
        if (this.type.getOptions().getMessageSetWireFormat()) {
            size = this.fields.getMessageSetSerializedSize();
            size += this.unknownFields.getSerializedSizeAsMessageSet();
        } else {
            size = this.fields.getSerializedSize();
            size += this.unknownFields.getSerializedSize();
        }
        this.memoizedSize = size;
        return size;
    }

    @Override
    public Builder newBuilderForType() {
        return new Builder(this.type);
    }

    @Override
    public Builder toBuilder() {
        return this.newBuilderForType().mergeFrom(this);
    }

    public Parser<DynamicMessage> getParserForType() {
        return new AbstractParser<DynamicMessage>(){

            @Override
            public DynamicMessage parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                Builder builder = DynamicMessage.newBuilder(DynamicMessage.this.type);
                try {
                    builder.mergeFrom(input, extensionRegistry);
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(builder.buildPartial());
                } catch (IOException e) {
                    throw new InvalidProtocolBufferException(e.getMessage()).setUnfinishedMessage(builder.buildPartial());
                }
                return builder.buildPartial();
            }
        };
    }

    private void verifyContainingType(Descriptors.FieldDescriptor field) {
        if (field.getContainingType() != this.type) {
            throw new IllegalArgumentException("FieldDescriptor does not match message type.");
        }
    }

    private void verifyOneofContainingType(Descriptors.OneofDescriptor oneof) {
        if (oneof.getContainingType() != this.type) {
            throw new IllegalArgumentException("OneofDescriptor does not match message type.");
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static final class Builder
    extends AbstractMessage.Builder<Builder> {
        private final Descriptors.Descriptor type;
        private FieldSet<Descriptors.FieldDescriptor> fields;
        private final Descriptors.FieldDescriptor[] oneofCases;
        private UnknownFieldSet unknownFields;

        private Builder(Descriptors.Descriptor type) {
            this.type = type;
            this.fields = FieldSet.newFieldSet();
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
            this.oneofCases = new Descriptors.FieldDescriptor[type.toProto().getOneofDeclCount()];
        }

        @Override
        public Builder clear() {
            if (this.fields.isImmutable()) {
                this.fields = FieldSet.newFieldSet();
            } else {
                this.fields.clear();
            }
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
            return this;
        }

        @Override
        public Builder mergeFrom(Message other) {
            if (other instanceof DynamicMessage) {
                DynamicMessage otherDynamicMessage = (DynamicMessage)other;
                if (otherDynamicMessage.type != this.type) {
                    throw new IllegalArgumentException("mergeFrom(Message) can only merge messages of the same type.");
                }
                this.ensureIsMutable();
                this.fields.mergeFrom(otherDynamicMessage.fields);
                this.mergeUnknownFields(otherDynamicMessage.unknownFields);
                for (int i = 0; i < this.oneofCases.length; ++i) {
                    if (this.oneofCases[i] == null) {
                        this.oneofCases[i] = otherDynamicMessage.oneofCases[i];
                        continue;
                    }
                    if (otherDynamicMessage.oneofCases[i] == null || this.oneofCases[i] == otherDynamicMessage.oneofCases[i]) continue;
                    this.fields.clearField(this.oneofCases[i]);
                    this.oneofCases[i] = otherDynamicMessage.oneofCases[i];
                }
                return this;
            }
            return (Builder)super.mergeFrom(other);
        }

        @Override
        public DynamicMessage build() {
            if (!this.isInitialized()) {
                throw Builder.newUninitializedMessageException(new DynamicMessage(this.type, this.fields, Arrays.copyOf(this.oneofCases, this.oneofCases.length), this.unknownFields));
            }
            return this.buildPartial();
        }

        private DynamicMessage buildParsed() throws InvalidProtocolBufferException {
            if (!this.isInitialized()) {
                throw Builder.newUninitializedMessageException(new DynamicMessage(this.type, this.fields, Arrays.copyOf(this.oneofCases, this.oneofCases.length), this.unknownFields)).asInvalidProtocolBufferException();
            }
            return this.buildPartial();
        }

        @Override
        public DynamicMessage buildPartial() {
            this.fields.makeImmutable();
            DynamicMessage result = new DynamicMessage(this.type, this.fields, Arrays.copyOf(this.oneofCases, this.oneofCases.length), this.unknownFields);
            return result;
        }

        @Override
        public Builder clone() {
            Builder result = new Builder(this.type);
            result.fields.mergeFrom(this.fields);
            result.mergeUnknownFields(this.unknownFields);
            System.arraycopy(this.oneofCases, 0, result.oneofCases, 0, this.oneofCases.length);
            return result;
        }

        @Override
        public boolean isInitialized() {
            return DynamicMessage.isInitialized(this.type, this.fields);
        }

        @Override
        public Descriptors.Descriptor getDescriptorForType() {
            return this.type;
        }

        @Override
        public DynamicMessage getDefaultInstanceForType() {
            return DynamicMessage.getDefaultInstance(this.type);
        }

        @Override
        public Map<Descriptors.FieldDescriptor, Object> getAllFields() {
            return this.fields.getAllFields();
        }

        @Override
        public Builder newBuilderForField(Descriptors.FieldDescriptor field) {
            this.verifyContainingType(field);
            if (field.getJavaType() != Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                throw new IllegalArgumentException("newBuilderForField is only valid for fields with message type.");
            }
            return new Builder(field.getMessageType());
        }

        @Override
        public boolean hasOneof(Descriptors.OneofDescriptor oneof) {
            this.verifyOneofContainingType(oneof);
            Descriptors.FieldDescriptor field = this.oneofCases[oneof.getIndex()];
            return field != null;
        }

        @Override
        public Descriptors.FieldDescriptor getOneofFieldDescriptor(Descriptors.OneofDescriptor oneof) {
            this.verifyOneofContainingType(oneof);
            return this.oneofCases[oneof.getIndex()];
        }

        @Override
        public Builder clearOneof(Descriptors.OneofDescriptor oneof) {
            this.verifyOneofContainingType(oneof);
            Descriptors.FieldDescriptor field = this.oneofCases[oneof.getIndex()];
            if (field != null) {
                this.clearField(field);
            }
            return this;
        }

        @Override
        public boolean hasField(Descriptors.FieldDescriptor field) {
            this.verifyContainingType(field);
            return this.fields.hasField(field);
        }

        @Override
        public Object getField(Descriptors.FieldDescriptor field) {
            this.verifyContainingType(field);
            Object result = this.fields.getField(field);
            if (result == null) {
                result = field.isRepeated() ? Collections.emptyList() : (field.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE ? DynamicMessage.getDefaultInstance(field.getMessageType()) : field.getDefaultValue());
            }
            return result;
        }

        @Override
        public Builder setField(Descriptors.FieldDescriptor field, Object value) {
            Descriptors.OneofDescriptor oneofDescriptor;
            this.verifyContainingType(field);
            this.ensureIsMutable();
            if (field.getType() == Descriptors.FieldDescriptor.Type.ENUM) {
                this.verifyEnumType(field, value);
            }
            if ((oneofDescriptor = field.getContainingOneof()) != null) {
                int index = oneofDescriptor.getIndex();
                Descriptors.FieldDescriptor oldField = this.oneofCases[index];
                if (oldField != null && oldField != field) {
                    this.fields.clearField(oldField);
                }
                this.oneofCases[index] = field;
            }
            this.fields.setField(field, value);
            return this;
        }

        @Override
        public Builder clearField(Descriptors.FieldDescriptor field) {
            int index;
            this.verifyContainingType(field);
            this.ensureIsMutable();
            Descriptors.OneofDescriptor oneofDescriptor = field.getContainingOneof();
            if (oneofDescriptor != null && this.oneofCases[index = oneofDescriptor.getIndex()] == field) {
                this.oneofCases[index] = null;
            }
            this.fields.clearField(field);
            return this;
        }

        @Override
        public int getRepeatedFieldCount(Descriptors.FieldDescriptor field) {
            this.verifyContainingType(field);
            return this.fields.getRepeatedFieldCount(field);
        }

        @Override
        public Object getRepeatedField(Descriptors.FieldDescriptor field, int index) {
            this.verifyContainingType(field);
            return this.fields.getRepeatedField(field, index);
        }

        @Override
        public Builder setRepeatedField(Descriptors.FieldDescriptor field, int index, Object value) {
            this.verifyContainingType(field);
            this.ensureIsMutable();
            this.fields.setRepeatedField(field, index, value);
            return this;
        }

        @Override
        public Builder addRepeatedField(Descriptors.FieldDescriptor field, Object value) {
            this.verifyContainingType(field);
            this.ensureIsMutable();
            this.fields.addRepeatedField(field, value);
            return this;
        }

        @Override
        public UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        @Override
        public Builder setUnknownFields(UnknownFieldSet unknownFields) {
            this.unknownFields = unknownFields;
            return this;
        }

        @Override
        public Builder mergeUnknownFields(UnknownFieldSet unknownFields) {
            this.unknownFields = UnknownFieldSet.newBuilder(this.unknownFields).mergeFrom(unknownFields).build();
            return this;
        }

        private void verifyContainingType(Descriptors.FieldDescriptor field) {
            if (field.getContainingType() != this.type) {
                throw new IllegalArgumentException("FieldDescriptor does not match message type.");
            }
        }

        private void verifyOneofContainingType(Descriptors.OneofDescriptor oneof) {
            if (oneof.getContainingType() != this.type) {
                throw new IllegalArgumentException("OneofDescriptor does not match message type.");
            }
        }

        private void verifyEnumType(Descriptors.FieldDescriptor field, Object value) {
            if (value == null) {
                throw new NullPointerException();
            }
            if (!(value instanceof Descriptors.EnumValueDescriptor)) {
                throw new IllegalArgumentException("DynamicMessage should use EnumValueDescriptor to set Enum Value.");
            }
            if (field.getEnumType() != ((Descriptors.EnumValueDescriptor)value).getType()) {
                throw new IllegalArgumentException("EnumValueDescriptor doesn't much Enum Field.");
            }
        }

        private void ensureIsMutable() {
            if (this.fields.isImmutable()) {
                this.fields = this.fields.clone();
            }
        }

        @Override
        public Message.Builder getFieldBuilder(Descriptors.FieldDescriptor field) {
            throw new UnsupportedOperationException("getFieldBuilder() called on a dynamic message type.");
        }
    }
}

