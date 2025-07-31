/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.protobuf;

import com.google.protobuf.Descriptors;
import com.google.protobuf.Message;
import com.google.protobuf.RpcController;
import com.google.protobuf.ServiceException;

public interface BlockingService {
    public Descriptors.ServiceDescriptor getDescriptorForType();

    public Message callBlockingMethod(Descriptors.MethodDescriptor var1, RpcController var2, Message var3) throws ServiceException;

    public Message getRequestPrototype(Descriptors.MethodDescriptor var1);

    public Message getResponsePrototype(Descriptors.MethodDescriptor var1);
}

