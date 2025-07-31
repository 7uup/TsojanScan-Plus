/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.protocol.x;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.UnknownFieldSet;
import com.mysql.cj.protocol.Message;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

public class XMessage
implements Message,
com.google.protobuf.Message {
    private com.google.protobuf.Message message;

    public XMessage(com.google.protobuf.Message mess) {
        this.message = mess;
    }

    public com.google.protobuf.Message getMessage() {
        return this.message;
    }

    @Override
    public byte[] getByteBuffer() {
        return this.message.toByteArray();
    }

    @Override
    public int getPosition() {
        return 0;
    }

    @Override
    public int getSerializedSize() {
        return this.message.getSerializedSize();
    }

    @Override
    public byte[] toByteArray() {
        return this.message.toByteArray();
    }

    @Override
    public ByteString toByteString() {
        return this.message.toByteString();
    }

    @Override
    public void writeDelimitedTo(OutputStream arg0) throws IOException {
        this.message.writeDelimitedTo(arg0);
    }

    @Override
    public void writeTo(CodedOutputStream arg0) throws IOException {
        this.message.writeTo(arg0);
    }

    @Override
    public void writeTo(OutputStream arg0) throws IOException {
        this.message.writeTo(arg0);
    }

    @Override
    public boolean isInitialized() {
        return this.message.isInitialized();
    }

    @Override
    public List<String> findInitializationErrors() {
        return this.message.findInitializationErrors();
    }

    @Override
    public Map<Descriptors.FieldDescriptor, Object> getAllFields() {
        return this.message.getAllFields();
    }

    @Override
    public com.google.protobuf.Message getDefaultInstanceForType() {
        return this.message.getDefaultInstanceForType();
    }

    @Override
    public Descriptors.Descriptor getDescriptorForType() {
        return this.message.getDescriptorForType();
    }

    @Override
    public Object getField(Descriptors.FieldDescriptor arg0) {
        return this.message.getField(arg0);
    }

    @Override
    public String getInitializationErrorString() {
        return this.message.getInitializationErrorString();
    }

    @Override
    public Descriptors.FieldDescriptor getOneofFieldDescriptor(Descriptors.OneofDescriptor arg0) {
        return this.message.getOneofFieldDescriptor(arg0);
    }

    @Override
    public Object getRepeatedField(Descriptors.FieldDescriptor arg0, int arg1) {
        return this.message.getRepeatedField(arg0, arg1);
    }

    @Override
    public int getRepeatedFieldCount(Descriptors.FieldDescriptor arg0) {
        return this.message.getRepeatedFieldCount(arg0);
    }

    @Override
    public UnknownFieldSet getUnknownFields() {
        return this.message.getUnknownFields();
    }

    @Override
    public boolean hasField(Descriptors.FieldDescriptor arg0) {
        return this.message.hasField(arg0);
    }

    @Override
    public boolean hasOneof(Descriptors.OneofDescriptor arg0) {
        return this.message.hasOneof(arg0);
    }

    @Override
    public Parser<? extends com.google.protobuf.Message> getParserForType() {
        return this.message.getParserForType();
    }

    @Override
    public Message.Builder newBuilderForType() {
        return this.message.newBuilderForType();
    }

    @Override
    public Message.Builder toBuilder() {
        return this.message.toBuilder();
    }
}

