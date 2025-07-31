/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.protobuf;

import com.google.protobuf.Descriptors;
import com.google.protobuf.Internal;

public interface ProtocolMessageEnum
extends Internal.EnumLite {
    public int getNumber();

    public Descriptors.EnumValueDescriptor getValueDescriptor();

    public Descriptors.EnumDescriptor getDescriptorForType();
}

