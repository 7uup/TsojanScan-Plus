/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.protobuf;

import com.google.protobuf.Descriptors;
import com.google.protobuf.Message;
import com.google.protobuf.RpcCallback;
import com.google.protobuf.RpcController;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public interface Service {
    public Descriptors.ServiceDescriptor getDescriptorForType();

    public void callMethod(Descriptors.MethodDescriptor var1, RpcController var2, Message var3, RpcCallback<Message> var4);

    public Message getRequestPrototype(Descriptors.MethodDescriptor var1);

    public Message getResponsePrototype(Descriptors.MethodDescriptor var1);
}

