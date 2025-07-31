/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.protobuf;

import com.google.protobuf.Descriptors;
import com.google.protobuf.MessageLite;
import com.google.protobuf.WireFormat;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public abstract class Extension<ContainingType extends MessageLite, Type> {
    public abstract int getNumber();

    public abstract WireFormat.FieldType getLiteType();

    public abstract boolean isRepeated();

    public abstract Descriptors.FieldDescriptor getDescriptor();

    public abstract Type getDefaultValue();

    public abstract MessageLite getMessageDefaultInstance();

    protected ExtensionType getExtensionType() {
        return ExtensionType.IMMUTABLE;
    }

    public MessageType getMessageType() {
        return MessageType.PROTO2;
    }

    protected abstract Object fromReflectionType(Object var1);

    protected abstract Object singularFromReflectionType(Object var1);

    protected abstract Object toReflectionType(Object var1);

    protected abstract Object singularToReflectionType(Object var1);

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static enum MessageType {
        PROTO1,
        PROTO2;

    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    protected static enum ExtensionType {
        IMMUTABLE,
        MUTABLE,
        PROTO1;

    }
}

