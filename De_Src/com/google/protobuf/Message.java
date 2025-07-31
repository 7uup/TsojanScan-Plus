/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.protobuf;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public interface Message
extends MessageLite,
MessageOrBuilder {
    public Parser<? extends Message> getParserForType();

    public boolean equals(Object var1);

    public int hashCode();

    public String toString();

    @Override
    public Builder newBuilderForType();

    @Override
    public Builder toBuilder();

    public static interface Builder
    extends MessageLite.Builder,
    MessageOrBuilder {
        public Builder clear();

        public Builder mergeFrom(Message var1);

        public Message build();

        public Message buildPartial();

        public Builder clone();

        public Builder mergeFrom(CodedInputStream var1) throws IOException;

        public Builder mergeFrom(CodedInputStream var1, ExtensionRegistryLite var2) throws IOException;

        public Descriptors.Descriptor getDescriptorForType();

        public Builder newBuilderForField(Descriptors.FieldDescriptor var1);

        public Builder getFieldBuilder(Descriptors.FieldDescriptor var1);

        public Builder setField(Descriptors.FieldDescriptor var1, Object var2);

        public Builder clearField(Descriptors.FieldDescriptor var1);

        public Builder clearOneof(Descriptors.OneofDescriptor var1);

        public Builder setRepeatedField(Descriptors.FieldDescriptor var1, int var2, Object var3);

        public Builder addRepeatedField(Descriptors.FieldDescriptor var1, Object var2);

        public Builder setUnknownFields(UnknownFieldSet var1);

        public Builder mergeUnknownFields(UnknownFieldSet var1);

        public Builder mergeFrom(ByteString var1) throws InvalidProtocolBufferException;

        public Builder mergeFrom(ByteString var1, ExtensionRegistryLite var2) throws InvalidProtocolBufferException;

        public Builder mergeFrom(byte[] var1) throws InvalidProtocolBufferException;

        public Builder mergeFrom(byte[] var1, int var2, int var3) throws InvalidProtocolBufferException;

        public Builder mergeFrom(byte[] var1, ExtensionRegistryLite var2) throws InvalidProtocolBufferException;

        public Builder mergeFrom(byte[] var1, int var2, int var3, ExtensionRegistryLite var4) throws InvalidProtocolBufferException;

        public Builder mergeFrom(InputStream var1) throws IOException;

        public Builder mergeFrom(InputStream var1, ExtensionRegistryLite var2) throws IOException;

        public boolean mergeDelimitedFrom(InputStream var1) throws IOException;

        public boolean mergeDelimitedFrom(InputStream var1, ExtensionRegistryLite var2) throws IOException;
    }
}

