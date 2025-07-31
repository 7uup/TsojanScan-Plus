/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.protobuf;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public interface MessageLite
extends MessageLiteOrBuilder {
    public void writeTo(CodedOutputStream var1) throws IOException;

    public int getSerializedSize();

    public Parser<? extends MessageLite> getParserForType();

    public ByteString toByteString();

    public byte[] toByteArray();

    public void writeTo(OutputStream var1) throws IOException;

    public void writeDelimitedTo(OutputStream var1) throws IOException;

    public Builder newBuilderForType();

    public Builder toBuilder();

    public static interface Builder
    extends MessageLiteOrBuilder,
    Cloneable {
        public Builder clear();

        public MessageLite build();

        public MessageLite buildPartial();

        public Builder clone();

        public Builder mergeFrom(CodedInputStream var1) throws IOException;

        public Builder mergeFrom(CodedInputStream var1, ExtensionRegistryLite var2) throws IOException;

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

