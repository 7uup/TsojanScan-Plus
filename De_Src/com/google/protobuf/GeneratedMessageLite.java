/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.protobuf;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.FieldSet;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.WireFormat;
import java.io.IOException;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public abstract class GeneratedMessageLite
extends AbstractMessageLite
implements Serializable {
    private static final long serialVersionUID = 1L;

    protected GeneratedMessageLite() {
    }

    protected GeneratedMessageLite(Builder builder) {
    }

    @Override
    public Parser<? extends MessageLite> getParserForType() {
        throw new UnsupportedOperationException("This is supposed to be overridden by subclasses.");
    }

    protected boolean parseUnknownField(CodedInputStream input, CodedOutputStream unknownFieldsCodedOutput, ExtensionRegistryLite extensionRegistry, int tag) throws IOException {
        return input.skipField(tag, unknownFieldsCodedOutput);
    }

    protected void makeExtensionsImmutable() {
    }

    private static <MessageType extends MessageLite> boolean parseUnknownField(FieldSet<ExtensionDescriptor> extensions, MessageType defaultInstance, CodedInputStream input, CodedOutputStream unknownFieldsCodedOutput, ExtensionRegistryLite extensionRegistry, int tag) throws IOException {
        int wireType = WireFormat.getTagWireType(tag);
        int fieldNumber = WireFormat.getTagFieldNumber(tag);
        GeneratedExtension<MessageType, ?> extension = extensionRegistry.findLiteExtensionByNumber(defaultInstance, fieldNumber);
        boolean unknown = false;
        boolean packed = false;
        if (extension == null) {
            unknown = true;
        } else if (wireType == FieldSet.getWireFormatForFieldType(extension.descriptor.getLiteType(), false)) {
            packed = false;
        } else if (extension.descriptor.isRepeated && extension.descriptor.type.isPackable() && wireType == FieldSet.getWireFormatForFieldType(extension.descriptor.getLiteType(), true)) {
            packed = true;
        } else {
            unknown = true;
        }
        if (unknown) {
            return input.skipField(tag, unknownFieldsCodedOutput);
        }
        if (packed) {
            int length = input.readRawVarint32();
            int limit = input.pushLimit(length);
            if (extension.descriptor.getLiteType() == WireFormat.FieldType.ENUM) {
                while (input.getBytesUntilLimit() > 0) {
                    int rawValue = input.readEnum();
                    Object value = extension.descriptor.getEnumType().findValueByNumber(rawValue);
                    if (value == null) {
                        return true;
                    }
                    extensions.addRepeatedField(extension.descriptor, extension.singularToFieldSetType(value));
                }
            } else {
                while (input.getBytesUntilLimit() > 0) {
                    Object value = FieldSet.readPrimitiveField(input, extension.descriptor.getLiteType(), false);
                    extensions.addRepeatedField(extension.descriptor, value);
                }
            }
            input.popLimit(limit);
        } else {
            Object value;
            switch (extension.descriptor.getLiteJavaType()) {
                case MESSAGE: {
                    MessageLite existingValue;
                    MessageLite.Builder subBuilder = null;
                    if (!extension.descriptor.isRepeated() && (existingValue = (MessageLite)extensions.getField(extension.descriptor)) != null) {
                        subBuilder = existingValue.toBuilder();
                    }
                    if (subBuilder == null) {
                        subBuilder = extension.getMessageDefaultInstance().newBuilderForType();
                    }
                    if (extension.descriptor.getLiteType() == WireFormat.FieldType.GROUP) {
                        input.readGroup(extension.getNumber(), subBuilder, extensionRegistry);
                    } else {
                        input.readMessage(subBuilder, extensionRegistry);
                    }
                    value = subBuilder.build();
                    break;
                }
                case ENUM: {
                    int rawValue = input.readEnum();
                    value = extension.descriptor.getEnumType().findValueByNumber(rawValue);
                    if (value != null) break;
                    unknownFieldsCodedOutput.writeRawVarint32(tag);
                    unknownFieldsCodedOutput.writeUInt32NoTag(rawValue);
                    return true;
                }
                default: {
                    value = FieldSet.readPrimitiveField(input, extension.descriptor.getLiteType(), false);
                }
            }
            if (extension.descriptor.isRepeated()) {
                extensions.addRepeatedField(extension.descriptor, extension.singularToFieldSetType(value));
            } else {
                extensions.setField(extension.descriptor, extension.singularToFieldSetType(value));
            }
        }
        return true;
    }

    public static <ContainingType extends MessageLite, Type> GeneratedExtension<ContainingType, Type> newSingularGeneratedExtension(ContainingType containingTypeDefaultInstance, Type defaultValue, MessageLite messageDefaultInstance, Internal.EnumLiteMap<?> enumTypeMap, int number, WireFormat.FieldType type, Class singularType) {
        return new GeneratedExtension<ContainingType, Type>(containingTypeDefaultInstance, defaultValue, messageDefaultInstance, new ExtensionDescriptor(enumTypeMap, number, type, false, false), singularType);
    }

    public static <ContainingType extends MessageLite, Type> GeneratedExtension<ContainingType, Type> newRepeatedGeneratedExtension(ContainingType containingTypeDefaultInstance, MessageLite messageDefaultInstance, Internal.EnumLiteMap<?> enumTypeMap, int number, WireFormat.FieldType type, boolean isPacked, Class singularType) {
        List emptyList = Collections.emptyList();
        return new GeneratedExtension(containingTypeDefaultInstance, emptyList, messageDefaultInstance, new ExtensionDescriptor(enumTypeMap, number, type, true, isPacked), singularType);
    }

    static Method getMethodOrDie(Class clazz, String name, Class ... params) {
        try {
            return clazz.getMethod(name, params);
        } catch (NoSuchMethodException e) {
            String string = String.valueOf(String.valueOf(clazz.getName()));
            String string2 = String.valueOf(String.valueOf(name));
            throw new RuntimeException(new StringBuilder(45 + string.length() + string2.length()).append("Generated message class \"").append(string).append("\" missing method \"").append(string2).append("\".").toString(), e);
        }
    }

    static Object invokeOrDie(Method method, Object object, Object ... params) {
        try {
            return method.invoke(object, params);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Couldn't use Java reflection to implement protocol message reflection.", e);
        } catch (InvocationTargetException e) {
            Throwable cause = e.getCause();
            if (cause instanceof RuntimeException) {
                throw (RuntimeException)cause;
            }
            if (cause instanceof Error) {
                throw (Error)cause;
            }
            throw new RuntimeException("Unexpected exception thrown by generated accessor method.", cause);
        }
    }

    protected Object writeReplace() throws ObjectStreamException {
        return new SerializedForm(this);
    }

    static final class SerializedForm
    implements Serializable {
        private static final long serialVersionUID = 0L;
        private String messageClassName;
        private byte[] asBytes;

        SerializedForm(MessageLite regularForm) {
            this.messageClassName = regularForm.getClass().getName();
            this.asBytes = regularForm.toByteArray();
        }

        protected Object readResolve() throws ObjectStreamException {
            try {
                Class<?> messageClass = Class.forName(this.messageClassName);
                Method newBuilder = messageClass.getMethod("newBuilder", new Class[0]);
                MessageLite.Builder builder = (MessageLite.Builder)newBuilder.invoke(null, new Object[0]);
                builder.mergeFrom(this.asBytes);
                return builder.buildPartial();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException("Unable to find proto buffer class", e);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException("Unable to find newBuilder method", e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Unable to call newBuilder method", e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException("Error calling newBuilder", e.getCause());
            } catch (InvalidProtocolBufferException e) {
                throw new RuntimeException("Unable to understand proto buffer", e);
            }
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static class GeneratedExtension<ContainingType extends MessageLite, Type> {
        final ContainingType containingTypeDefaultInstance;
        final Type defaultValue;
        final MessageLite messageDefaultInstance;
        final ExtensionDescriptor descriptor;
        final Class singularType;
        final Method enumValueOf;

        GeneratedExtension(ContainingType containingTypeDefaultInstance, Type defaultValue, MessageLite messageDefaultInstance, ExtensionDescriptor descriptor, Class singularType) {
            if (containingTypeDefaultInstance == null) {
                throw new IllegalArgumentException("Null containingTypeDefaultInstance");
            }
            if (descriptor.getLiteType() == WireFormat.FieldType.MESSAGE && messageDefaultInstance == null) {
                throw new IllegalArgumentException("Null messageDefaultInstance");
            }
            this.containingTypeDefaultInstance = containingTypeDefaultInstance;
            this.defaultValue = defaultValue;
            this.messageDefaultInstance = messageDefaultInstance;
            this.descriptor = descriptor;
            this.singularType = singularType;
            this.enumValueOf = Internal.EnumLite.class.isAssignableFrom(singularType) ? GeneratedMessageLite.getMethodOrDie(singularType, "valueOf", Integer.TYPE) : null;
        }

        public ContainingType getContainingTypeDefaultInstance() {
            return this.containingTypeDefaultInstance;
        }

        public int getNumber() {
            return this.descriptor.getNumber();
        }

        public MessageLite getMessageDefaultInstance() {
            return this.messageDefaultInstance;
        }

        Object fromFieldSetType(Object value) {
            if (this.descriptor.isRepeated()) {
                if (this.descriptor.getLiteJavaType() == WireFormat.JavaType.ENUM) {
                    ArrayList<Object> result = new ArrayList<Object>();
                    for (Object element : (List)value) {
                        result.add(this.singularFromFieldSetType(element));
                    }
                    return result;
                }
                return value;
            }
            return this.singularFromFieldSetType(value);
        }

        Object singularFromFieldSetType(Object value) {
            if (this.descriptor.getLiteJavaType() == WireFormat.JavaType.ENUM) {
                return GeneratedMessageLite.invokeOrDie(this.enumValueOf, null, (Integer)value);
            }
            return value;
        }

        Object toFieldSetType(Object value) {
            if (this.descriptor.isRepeated()) {
                if (this.descriptor.getLiteJavaType() == WireFormat.JavaType.ENUM) {
                    ArrayList<Object> result = new ArrayList<Object>();
                    for (Object element : (List)value) {
                        result.add(this.singularToFieldSetType(element));
                    }
                    return result;
                }
                return value;
            }
            return this.singularToFieldSetType(value);
        }

        Object singularToFieldSetType(Object value) {
            if (this.descriptor.getLiteJavaType() == WireFormat.JavaType.ENUM) {
                return ((Internal.EnumLite)value).getNumber();
            }
            return value;
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    static final class ExtensionDescriptor
    implements FieldSet.FieldDescriptorLite<ExtensionDescriptor> {
        final Internal.EnumLiteMap<?> enumTypeMap;
        final int number;
        final WireFormat.FieldType type;
        final boolean isRepeated;
        final boolean isPacked;

        ExtensionDescriptor(Internal.EnumLiteMap<?> enumTypeMap, int number, WireFormat.FieldType type, boolean isRepeated, boolean isPacked) {
            this.enumTypeMap = enumTypeMap;
            this.number = number;
            this.type = type;
            this.isRepeated = isRepeated;
            this.isPacked = isPacked;
        }

        @Override
        public int getNumber() {
            return this.number;
        }

        @Override
        public WireFormat.FieldType getLiteType() {
            return this.type;
        }

        @Override
        public WireFormat.JavaType getLiteJavaType() {
            return this.type.getJavaType();
        }

        @Override
        public boolean isRepeated() {
            return this.isRepeated;
        }

        @Override
        public boolean isPacked() {
            return this.isPacked;
        }

        @Override
        public Internal.EnumLiteMap<?> getEnumType() {
            return this.enumTypeMap;
        }

        @Override
        public MessageLite.Builder internalMergeFrom(MessageLite.Builder to, MessageLite from) {
            return ((Builder)to).mergeFrom((GeneratedMessageLite)from);
        }

        @Override
        public int compareTo(ExtensionDescriptor other) {
            return this.number - other.number;
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static abstract class ExtendableBuilder<MessageType extends ExtendableMessage<MessageType>, BuilderType extends ExtendableBuilder<MessageType, BuilderType>>
    extends Builder<MessageType, BuilderType>
    implements ExtendableMessageOrBuilder<MessageType> {
        private FieldSet<ExtensionDescriptor> extensions = FieldSet.emptySet();
        private boolean extensionsIsMutable;

        protected ExtendableBuilder() {
        }

        void internalSetExtensionSet(FieldSet<ExtensionDescriptor> extensions) {
            this.extensions = extensions;
        }

        @Override
        public BuilderType clear() {
            this.extensions.clear();
            this.extensionsIsMutable = false;
            return (BuilderType)((ExtendableBuilder)super.clear());
        }

        private void ensureExtensionsIsMutable() {
            if (!this.extensionsIsMutable) {
                this.extensions = this.extensions.clone();
                this.extensionsIsMutable = true;
            }
        }

        private FieldSet<ExtensionDescriptor> buildExtensions() {
            this.extensions.makeImmutable();
            this.extensionsIsMutable = false;
            return this.extensions;
        }

        private void verifyExtensionContainingType(GeneratedExtension<MessageType, ?> extension) {
            if (extension.getContainingTypeDefaultInstance() != this.getDefaultInstanceForType()) {
                throw new IllegalArgumentException("This extension is for a different message type.  Please make sure that you are not suppressing any generics type warnings.");
            }
        }

        @Override
        public final <Type> boolean hasExtension(GeneratedExtension<MessageType, Type> extension) {
            this.verifyExtensionContainingType(extension);
            return this.extensions.hasField(extension.descriptor);
        }

        @Override
        public final <Type> int getExtensionCount(GeneratedExtension<MessageType, List<Type>> extension) {
            this.verifyExtensionContainingType(extension);
            return this.extensions.getRepeatedFieldCount(extension.descriptor);
        }

        @Override
        public final <Type> Type getExtension(GeneratedExtension<MessageType, Type> extension) {
            this.verifyExtensionContainingType(extension);
            Object value = this.extensions.getField(extension.descriptor);
            if (value == null) {
                return extension.defaultValue;
            }
            return (Type)extension.fromFieldSetType(value);
        }

        @Override
        public final <Type> Type getExtension(GeneratedExtension<MessageType, List<Type>> extension, int index) {
            this.verifyExtensionContainingType(extension);
            return (Type)extension.singularFromFieldSetType(this.extensions.getRepeatedField(extension.descriptor, index));
        }

        @Override
        public BuilderType clone() {
            throw new UnsupportedOperationException("This is supposed to be overridden by subclasses.");
        }

        public final <Type> BuilderType setExtension(GeneratedExtension<MessageType, Type> extension, Type value) {
            this.verifyExtensionContainingType(extension);
            this.ensureExtensionsIsMutable();
            this.extensions.setField(extension.descriptor, extension.toFieldSetType(value));
            return (BuilderType)this;
        }

        public final <Type> BuilderType setExtension(GeneratedExtension<MessageType, List<Type>> extension, int index, Type value) {
            this.verifyExtensionContainingType(extension);
            this.ensureExtensionsIsMutable();
            this.extensions.setRepeatedField(extension.descriptor, index, extension.singularToFieldSetType(value));
            return (BuilderType)this;
        }

        public final <Type> BuilderType addExtension(GeneratedExtension<MessageType, List<Type>> extension, Type value) {
            this.verifyExtensionContainingType(extension);
            this.ensureExtensionsIsMutable();
            this.extensions.addRepeatedField(extension.descriptor, extension.singularToFieldSetType(value));
            return (BuilderType)this;
        }

        public final <Type> BuilderType clearExtension(GeneratedExtension<MessageType, ?> extension) {
            this.verifyExtensionContainingType(extension);
            this.ensureExtensionsIsMutable();
            this.extensions.clearField(extension.descriptor);
            return (BuilderType)this;
        }

        protected boolean extensionsAreInitialized() {
            return this.extensions.isInitialized();
        }

        @Override
        protected boolean parseUnknownField(CodedInputStream input, CodedOutputStream unknownFieldsCodedOutput, ExtensionRegistryLite extensionRegistry, int tag) throws IOException {
            this.ensureExtensionsIsMutable();
            return GeneratedMessageLite.parseUnknownField(this.extensions, this.getDefaultInstanceForType(), input, unknownFieldsCodedOutput, extensionRegistry, tag);
        }

        protected final void mergeExtensionFields(MessageType other) {
            this.ensureExtensionsIsMutable();
            this.extensions.mergeFrom(((ExtendableMessage)other).extensions);
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static abstract class ExtendableMessage<MessageType extends ExtendableMessage<MessageType>>
    extends GeneratedMessageLite
    implements ExtendableMessageOrBuilder<MessageType> {
        private final FieldSet<ExtensionDescriptor> extensions;

        protected ExtendableMessage() {
            this.extensions = FieldSet.newFieldSet();
        }

        protected ExtendableMessage(ExtendableBuilder<MessageType, ?> builder) {
            this.extensions = ((ExtendableBuilder)builder).buildExtensions();
        }

        private void verifyExtensionContainingType(GeneratedExtension<MessageType, ?> extension) {
            if (extension.getContainingTypeDefaultInstance() != this.getDefaultInstanceForType()) {
                throw new IllegalArgumentException("This extension is for a different message type.  Please make sure that you are not suppressing any generics type warnings.");
            }
        }

        @Override
        public final <Type> boolean hasExtension(GeneratedExtension<MessageType, Type> extension) {
            this.verifyExtensionContainingType(extension);
            return this.extensions.hasField(extension.descriptor);
        }

        @Override
        public final <Type> int getExtensionCount(GeneratedExtension<MessageType, List<Type>> extension) {
            this.verifyExtensionContainingType(extension);
            return this.extensions.getRepeatedFieldCount(extension.descriptor);
        }

        @Override
        public final <Type> Type getExtension(GeneratedExtension<MessageType, Type> extension) {
            this.verifyExtensionContainingType(extension);
            Object value = this.extensions.getField(extension.descriptor);
            if (value == null) {
                return extension.defaultValue;
            }
            return (Type)extension.fromFieldSetType(value);
        }

        @Override
        public final <Type> Type getExtension(GeneratedExtension<MessageType, List<Type>> extension, int index) {
            this.verifyExtensionContainingType(extension);
            return (Type)extension.singularFromFieldSetType(this.extensions.getRepeatedField(extension.descriptor, index));
        }

        protected boolean extensionsAreInitialized() {
            return this.extensions.isInitialized();
        }

        @Override
        protected boolean parseUnknownField(CodedInputStream input, CodedOutputStream unknownFieldsCodedOutput, ExtensionRegistryLite extensionRegistry, int tag) throws IOException {
            return GeneratedMessageLite.parseUnknownField(this.extensions, this.getDefaultInstanceForType(), input, unknownFieldsCodedOutput, extensionRegistry, tag);
        }

        @Override
        protected void makeExtensionsImmutable() {
            this.extensions.makeImmutable();
        }

        protected ExtensionWriter newExtensionWriter() {
            return new ExtensionWriter(false);
        }

        protected ExtensionWriter newMessageSetExtensionWriter() {
            return new ExtensionWriter(true);
        }

        protected int extensionsSerializedSize() {
            return this.extensions.getSerializedSize();
        }

        protected int extensionsSerializedSizeAsMessageSet() {
            return this.extensions.getMessageSetSerializedSize();
        }

        protected class ExtensionWriter {
            private final Iterator<Map.Entry<ExtensionDescriptor, Object>> iter;
            private Map.Entry<ExtensionDescriptor, Object> next;
            private final boolean messageSetWireFormat;

            private ExtensionWriter(boolean messageSetWireFormat) {
                this.iter = ExtendableMessage.this.extensions.iterator();
                if (this.iter.hasNext()) {
                    this.next = this.iter.next();
                }
                this.messageSetWireFormat = messageSetWireFormat;
            }

            public void writeUntil(int end, CodedOutputStream output) throws IOException {
                while (this.next != null && this.next.getKey().getNumber() < end) {
                    ExtensionDescriptor extension = this.next.getKey();
                    if (this.messageSetWireFormat && extension.getLiteJavaType() == WireFormat.JavaType.MESSAGE && !extension.isRepeated()) {
                        output.writeMessageSetExtension(extension.getNumber(), (MessageLite)this.next.getValue());
                    } else {
                        FieldSet.writeField(extension, this.next.getValue(), output);
                    }
                    if (this.iter.hasNext()) {
                        this.next = this.iter.next();
                        continue;
                    }
                    this.next = null;
                }
            }
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static interface ExtendableMessageOrBuilder<MessageType extends ExtendableMessage>
    extends MessageLiteOrBuilder {
        public <Type> boolean hasExtension(GeneratedExtension<MessageType, Type> var1);

        public <Type> int getExtensionCount(GeneratedExtension<MessageType, List<Type>> var1);

        public <Type> Type getExtension(GeneratedExtension<MessageType, Type> var1);

        public <Type> Type getExtension(GeneratedExtension<MessageType, List<Type>> var1, int var2);
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static abstract class Builder<MessageType extends GeneratedMessageLite, BuilderType extends Builder>
    extends AbstractMessageLite.Builder<BuilderType> {
        private ByteString unknownFields = ByteString.EMPTY;

        protected Builder() {
        }

        public BuilderType clear() {
            this.unknownFields = ByteString.EMPTY;
            return (BuilderType)this;
        }

        @Override
        public BuilderType clone() {
            throw new UnsupportedOperationException("This is supposed to be overridden by subclasses.");
        }

        public abstract BuilderType mergeFrom(MessageType var1);

        public abstract MessageType getDefaultInstanceForType();

        protected boolean parseUnknownField(CodedInputStream input, CodedOutputStream unknownFieldsCodedOutput, ExtensionRegistryLite extensionRegistry, int tag) throws IOException {
            return input.skipField(tag, unknownFieldsCodedOutput);
        }

        public final ByteString getUnknownFields() {
            return this.unknownFields;
        }

        public final BuilderType setUnknownFields(ByteString unknownFields) {
            this.unknownFields = unknownFields;
            return (BuilderType)this;
        }
    }
}

