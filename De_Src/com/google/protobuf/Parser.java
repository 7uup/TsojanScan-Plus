/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.protobuf;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.InvalidProtocolBufferException;
import java.io.InputStream;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public interface Parser<MessageType> {
    public MessageType parseFrom(CodedInputStream var1) throws InvalidProtocolBufferException;

    public MessageType parseFrom(CodedInputStream var1, ExtensionRegistryLite var2) throws InvalidProtocolBufferException;

    public MessageType parsePartialFrom(CodedInputStream var1) throws InvalidProtocolBufferException;

    public MessageType parsePartialFrom(CodedInputStream var1, ExtensionRegistryLite var2) throws InvalidProtocolBufferException;

    public MessageType parseFrom(ByteString var1) throws InvalidProtocolBufferException;

    public MessageType parseFrom(ByteString var1, ExtensionRegistryLite var2) throws InvalidProtocolBufferException;

    public MessageType parsePartialFrom(ByteString var1) throws InvalidProtocolBufferException;

    public MessageType parsePartialFrom(ByteString var1, ExtensionRegistryLite var2) throws InvalidProtocolBufferException;

    public MessageType parseFrom(byte[] var1, int var2, int var3) throws InvalidProtocolBufferException;

    public MessageType parseFrom(byte[] var1, int var2, int var3, ExtensionRegistryLite var4) throws InvalidProtocolBufferException;

    public MessageType parseFrom(byte[] var1) throws InvalidProtocolBufferException;

    public MessageType parseFrom(byte[] var1, ExtensionRegistryLite var2) throws InvalidProtocolBufferException;

    public MessageType parsePartialFrom(byte[] var1, int var2, int var3) throws InvalidProtocolBufferException;

    public MessageType parsePartialFrom(byte[] var1, int var2, int var3, ExtensionRegistryLite var4) throws InvalidProtocolBufferException;

    public MessageType parsePartialFrom(byte[] var1) throws InvalidProtocolBufferException;

    public MessageType parsePartialFrom(byte[] var1, ExtensionRegistryLite var2) throws InvalidProtocolBufferException;

    public MessageType parseFrom(InputStream var1) throws InvalidProtocolBufferException;

    public MessageType parseFrom(InputStream var1, ExtensionRegistryLite var2) throws InvalidProtocolBufferException;

    public MessageType parsePartialFrom(InputStream var1) throws InvalidProtocolBufferException;

    public MessageType parsePartialFrom(InputStream var1, ExtensionRegistryLite var2) throws InvalidProtocolBufferException;

    public MessageType parseDelimitedFrom(InputStream var1) throws InvalidProtocolBufferException;

    public MessageType parseDelimitedFrom(InputStream var1, ExtensionRegistryLite var2) throws InvalidProtocolBufferException;

    public MessageType parsePartialDelimitedFrom(InputStream var1) throws InvalidProtocolBufferException;

    public MessageType parsePartialDelimitedFrom(InputStream var1, ExtensionRegistryLite var2) throws InvalidProtocolBufferException;
}

