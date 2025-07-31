/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.protobuf;

import com.google.protobuf.AbstractMessage;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.DynamicMessage;
import com.google.protobuf.Extension;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.FieldSet;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.LazyField;
import com.google.protobuf.Message;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.MessageReflection;
import com.google.protobuf.Parser;
import com.google.protobuf.ProtocolMessageEnum;
import com.google.protobuf.UnknownFieldSet;
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
import java.util.TreeMap;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public abstract class GeneratedMessage
extends AbstractMessage
implements Serializable {
    private static final long serialVersionUID = 1L;
    protected static boolean alwaysUseFieldBuilders = false;

    protected GeneratedMessage() {
    }

    protected GeneratedMessage(Builder<?> builder) {
    }

    public Parser<? extends GeneratedMessage> getParserForType() {
        throw new UnsupportedOperationException("This is supposed to be overridden by subclasses.");
    }

    static void enableAlwaysUseFieldBuildersForTesting() {
        alwaysUseFieldBuilders = true;
    }

    protected abstract FieldAccessorTable internalGetFieldAccessorTable();

    @Override
    public Descriptors.Descriptor getDescriptorForType() {
        return this.internalGetFieldAccessorTable().descriptor;
    }

    private Map<Descriptors.FieldDescriptor, Object> getAllFieldsMutable() {
        TreeMap<Descriptors.FieldDescriptor, Object> result = new TreeMap<Descriptors.FieldDescriptor, Object>();
        Descriptors.Descriptor descriptor = this.internalGetFieldAccessorTable().descriptor;
        for (Descriptors.FieldDescriptor field : descriptor.getFields()) {
            if (field.isRepeated()) {
                List value = (List)this.getField(field);
                if (value.isEmpty()) continue;
                result.put(field, value);
                continue;
            }
            if (!this.hasField(field)) continue;
            result.put(field, this.getField(field));
        }
        return result;
    }

    @Override
    public boolean isInitialized() {
        for (Descriptors.FieldDescriptor field : this.getDescriptorForType().getFields()) {
            if (field.isRequired() && !this.hasField(field)) {
                return false;
            }
            if (field.getJavaType() != Descriptors.FieldDescriptor.JavaType.MESSAGE) continue;
            if (field.isRepeated()) {
                List messageList = (List)this.getField(field);
                for (Message element : messageList) {
                    if (element.isInitialized()) continue;
                    return false;
                }
                continue;
            }
            if (!this.hasField(field) || ((Message)this.getField(field)).isInitialized()) continue;
            return false;
        }
        return true;
    }

    @Override
    public Map<Descriptors.FieldDescriptor, Object> getAllFields() {
        return Collections.unmodifiableMap(this.getAllFieldsMutable());
    }

    @Override
    public boolean hasOneof(Descriptors.OneofDescriptor oneof) {
        return this.internalGetFieldAccessorTable().getOneof(oneof).has(this);
    }

    @Override
    public Descriptors.FieldDescriptor getOneofFieldDescriptor(Descriptors.OneofDescriptor oneof) {
        return this.internalGetFieldAccessorTable().getOneof(oneof).get(this);
    }

    @Override
    public boolean hasField(Descriptors.FieldDescriptor field) {
        return this.internalGetFieldAccessorTable().getField(field).has(this);
    }

    @Override
    public Object getField(Descriptors.FieldDescriptor field) {
        return this.internalGetFieldAccessorTable().getField(field).get(this);
    }

    @Override
    public int getRepeatedFieldCount(Descriptors.FieldDescriptor field) {
        return this.internalGetFieldAccessorTable().getField(field).getRepeatedCount(this);
    }

    @Override
    public Object getRepeatedField(Descriptors.FieldDescriptor field, int index) {
        return this.internalGetFieldAccessorTable().getField(field).getRepeated(this, index);
    }

    @Override
    public UnknownFieldSet getUnknownFields() {
        throw new UnsupportedOperationException("This is supposed to be overridden by subclasses.");
    }

    protected boolean parseUnknownField(CodedInputStream input, UnknownFieldSet.Builder unknownFields, ExtensionRegistryLite extensionRegistry, int tag) throws IOException {
        return unknownFields.mergeFieldFrom(tag, input);
    }

    protected void makeExtensionsImmutable() {
    }

    protected abstract Message.Builder newBuilderForType(BuilderParent var1);

    public static <ContainingType extends Message, Type> GeneratedExtension<ContainingType, Type> newMessageScopedGeneratedExtension(final Message scope, final int descriptorIndex, Class singularType, Message defaultInstance) {
        return new GeneratedExtension(new CachedDescriptorRetriever(){

            public Descriptors.FieldDescriptor loadDescriptor() {
                return scope.getDescriptorForType().getExtensions().get(descriptorIndex);
            }
        }, singularType, defaultInstance, Extension.ExtensionType.IMMUTABLE);
    }

    public static <ContainingType extends Message, Type> GeneratedExtension<ContainingType, Type> newFileScopedGeneratedExtension(Class singularType, Message defaultInstance) {
        return new GeneratedExtension(null, singularType, defaultInstance, Extension.ExtensionType.IMMUTABLE);
    }

    public static <ContainingType extends Message, Type> GeneratedExtension<ContainingType, Type> newMessageScopedGeneratedExtension(final Message scope, final String name, Class singularType, Message defaultInstance) {
        return new GeneratedExtension(new CachedDescriptorRetriever(){

            protected Descriptors.FieldDescriptor loadDescriptor() {
                return scope.getDescriptorForType().findFieldByName(name);
            }
        }, singularType, defaultInstance, Extension.ExtensionType.MUTABLE);
    }

    public static <ContainingType extends Message, Type> GeneratedExtension<ContainingType, Type> newFileScopedGeneratedExtension(final Class singularType, Message defaultInstance, final String descriptorOuterClass, final String extensionName) {
        return new GeneratedExtension(new CachedDescriptorRetriever(){

            protected Descriptors.FieldDescriptor loadDescriptor() {
                try {
                    Class<?> clazz = singularType.getClassLoader().loadClass(descriptorOuterClass);
                    Descriptors.FileDescriptor file = (Descriptors.FileDescriptor)clazz.getField("descriptor").get(null);
                    return file.findExtensionByName(extensionName);
                } catch (Exception e) {
                    String string = String.valueOf(String.valueOf(descriptorOuterClass));
                    throw new RuntimeException(new StringBuilder(62 + string.length()).append("Cannot load descriptors: ").append(string).append(" is not a valid descriptor class name").toString(), e);
                }
            }
        }, singularType, defaultInstance, Extension.ExtensionType.MUTABLE);
    }

    private static Method getMethodOrDie(Class clazz, String name, Class ... params) {
        try {
            return clazz.getMethod(name, params);
        } catch (NoSuchMethodException e) {
            String string = String.valueOf(String.valueOf(clazz.getName()));
            String string2 = String.valueOf(String.valueOf(name));
            throw new RuntimeException(new StringBuilder(45 + string.length() + string2.length()).append("Generated message class \"").append(string).append("\" missing method \"").append(string2).append("\".").toString(), e);
        }
    }

    private static Object invokeOrDie(Method method, Object object, Object ... params) {
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
        return new GeneratedMessageLite.SerializedForm(this);
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static final class FieldAccessorTable {
        private final Descriptors.Descriptor descriptor;
        private final FieldAccessor[] fields;
        private String[] camelCaseNames;
        private final OneofAccessor[] oneofs;
        private volatile boolean initialized;

        public FieldAccessorTable(Descriptors.Descriptor descriptor, String[] camelCaseNames, Class<? extends GeneratedMessage> messageClass, Class<? extends Builder> builderClass) {
            this(descriptor, camelCaseNames);
            this.ensureFieldAccessorsInitialized(messageClass, builderClass);
        }

        public FieldAccessorTable(Descriptors.Descriptor descriptor, String[] camelCaseNames) {
            this.descriptor = descriptor;
            this.camelCaseNames = camelCaseNames;
            this.fields = new FieldAccessor[descriptor.getFields().size()];
            this.oneofs = new OneofAccessor[descriptor.getOneofs().size()];
            this.initialized = false;
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        public FieldAccessorTable ensureFieldAccessorsInitialized(Class<? extends GeneratedMessage> messageClass, Class<? extends Builder> builderClass) {
            if (this.initialized) {
                return this;
            }
            FieldAccessorTable fieldAccessorTable = this;
            synchronized (fieldAccessorTable) {
                if (this.initialized) {
                    return this;
                }
                int fieldsSize = this.fields.length;
                for (int i = 0; i < fieldsSize; ++i) {
                    Descriptors.FieldDescriptor field = this.descriptor.getFields().get(i);
                    String containingOneofCamelCaseName = null;
                    if (field.getContainingOneof() != null) {
                        containingOneofCamelCaseName = this.camelCaseNames[fieldsSize + field.getContainingOneof().getIndex()];
                    }
                    if (field.isRepeated()) {
                        if (field.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                            this.fields[i] = new RepeatedMessageFieldAccessor(field, this.camelCaseNames[i], messageClass, builderClass);
                            continue;
                        }
                        if (field.getJavaType() == Descriptors.FieldDescriptor.JavaType.ENUM) {
                            this.fields[i] = new RepeatedEnumFieldAccessor(field, this.camelCaseNames[i], messageClass, builderClass);
                            continue;
                        }
                        this.fields[i] = new RepeatedFieldAccessor(field, this.camelCaseNames[i], messageClass, builderClass);
                        continue;
                    }
                    this.fields[i] = field.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE ? new SingularMessageFieldAccessor(field, this.camelCaseNames[i], messageClass, builderClass, containingOneofCamelCaseName) : (field.getJavaType() == Descriptors.FieldDescriptor.JavaType.ENUM ? new SingularEnumFieldAccessor(field, this.camelCaseNames[i], messageClass, builderClass, containingOneofCamelCaseName) : new SingularFieldAccessor(field, this.camelCaseNames[i], messageClass, builderClass, containingOneofCamelCaseName));
                }
                int oneofsSize = this.oneofs.length;
                for (int i = 0; i < oneofsSize; ++i) {
                    this.oneofs[i] = new OneofAccessor(this.descriptor, this.camelCaseNames[i + fieldsSize], messageClass, builderClass);
                }
                this.initialized = true;
                this.camelCaseNames = null;
                return this;
            }
        }

        private FieldAccessor getField(Descriptors.FieldDescriptor field) {
            if (field.getContainingType() != this.descriptor) {
                throw new IllegalArgumentException("FieldDescriptor does not match message type.");
            }
            if (field.isExtension()) {
                throw new IllegalArgumentException("This type does not have extensions.");
            }
            return this.fields[field.getIndex()];
        }

        private OneofAccessor getOneof(Descriptors.OneofDescriptor oneof) {
            if (oneof.getContainingType() != this.descriptor) {
                throw new IllegalArgumentException("OneofDescriptor does not match message type.");
            }
            return this.oneofs[oneof.getIndex()];
        }

        private static boolean supportFieldPresence(Descriptors.FileDescriptor file) {
            return true;
        }

        /*
         * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
         */
        private static final class RepeatedMessageFieldAccessor
        extends RepeatedFieldAccessor {
            private final Method newBuilderMethod;

            RepeatedMessageFieldAccessor(Descriptors.FieldDescriptor descriptor, String camelCaseName, Class<? extends GeneratedMessage> messageClass, Class<? extends Builder> builderClass) {
                super(descriptor, camelCaseName, messageClass, builderClass);
                this.newBuilderMethod = GeneratedMessage.getMethodOrDie(this.type, "newBuilder", new Class[0]);
            }

            private Object coerceType(Object value) {
                if (this.type.isInstance(value)) {
                    return value;
                }
                return ((Message.Builder)GeneratedMessage.invokeOrDie(this.newBuilderMethod, null, new Object[0])).mergeFrom((Message)value).build();
            }

            @Override
            public void setRepeated(Builder builder, int index, Object value) {
                super.setRepeated(builder, index, this.coerceType(value));
            }

            @Override
            public void addRepeated(Builder builder, Object value) {
                super.addRepeated(builder, this.coerceType(value));
            }

            @Override
            public Message.Builder newBuilder() {
                return (Message.Builder)GeneratedMessage.invokeOrDie(this.newBuilderMethod, null, new Object[0]);
            }
        }

        /*
         * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
         */
        private static final class SingularMessageFieldAccessor
        extends SingularFieldAccessor {
            private final Method newBuilderMethod;
            private final Method getBuilderMethodBuilder;

            SingularMessageFieldAccessor(Descriptors.FieldDescriptor descriptor, String camelCaseName, Class<? extends GeneratedMessage> messageClass, Class<? extends Builder> builderClass, String containingOneofCamelCaseName) {
                super(descriptor, camelCaseName, messageClass, builderClass, containingOneofCamelCaseName);
                this.newBuilderMethod = GeneratedMessage.getMethodOrDie(this.type, "newBuilder", new Class[0]);
                String string = String.valueOf(String.valueOf(camelCaseName));
                this.getBuilderMethodBuilder = GeneratedMessage.getMethodOrDie(builderClass, new StringBuilder(10 + string.length()).append("get").append(string).append("Builder").toString(), new Class[0]);
            }

            private Object coerceType(Object value) {
                if (this.type.isInstance(value)) {
                    return value;
                }
                return ((Message.Builder)GeneratedMessage.invokeOrDie(this.newBuilderMethod, null, new Object[0])).mergeFrom((Message)value).buildPartial();
            }

            @Override
            public void set(Builder builder, Object value) {
                super.set(builder, this.coerceType(value));
            }

            @Override
            public Message.Builder newBuilder() {
                return (Message.Builder)GeneratedMessage.invokeOrDie(this.newBuilderMethod, null, new Object[0]);
            }

            @Override
            public Message.Builder getBuilder(Builder builder) {
                return (Message.Builder)GeneratedMessage.invokeOrDie(this.getBuilderMethodBuilder, builder, new Object[0]);
            }
        }

        /*
         * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
         */
        private static final class RepeatedEnumFieldAccessor
        extends RepeatedFieldAccessor {
            private final Method valueOfMethod;
            private final Method getValueDescriptorMethod;

            RepeatedEnumFieldAccessor(Descriptors.FieldDescriptor descriptor, String camelCaseName, Class<? extends GeneratedMessage> messageClass, Class<? extends Builder> builderClass) {
                super(descriptor, camelCaseName, messageClass, builderClass);
                this.valueOfMethod = GeneratedMessage.getMethodOrDie(this.type, "valueOf", new Class[]{Descriptors.EnumValueDescriptor.class});
                this.getValueDescriptorMethod = GeneratedMessage.getMethodOrDie(this.type, "getValueDescriptor", new Class[0]);
            }

            @Override
            public Object get(GeneratedMessage message) {
                ArrayList<Object> newList = new ArrayList<Object>();
                for (Object element : (List)super.get(message)) {
                    newList.add(GeneratedMessage.invokeOrDie(this.getValueDescriptorMethod, element, new Object[0]));
                }
                return Collections.unmodifiableList(newList);
            }

            @Override
            public Object get(Builder builder) {
                ArrayList<Object> newList = new ArrayList<Object>();
                for (Object element : (List)super.get(builder)) {
                    newList.add(GeneratedMessage.invokeOrDie(this.getValueDescriptorMethod, element, new Object[0]));
                }
                return Collections.unmodifiableList(newList);
            }

            @Override
            public Object getRepeated(GeneratedMessage message, int index) {
                return GeneratedMessage.invokeOrDie(this.getValueDescriptorMethod, super.getRepeated(message, index), new Object[0]);
            }

            @Override
            public Object getRepeated(Builder builder, int index) {
                return GeneratedMessage.invokeOrDie(this.getValueDescriptorMethod, super.getRepeated(builder, index), new Object[0]);
            }

            @Override
            public void setRepeated(Builder builder, int index, Object value) {
                super.setRepeated(builder, index, GeneratedMessage.invokeOrDie(this.valueOfMethod, null, new Object[]{value}));
            }

            @Override
            public void addRepeated(Builder builder, Object value) {
                super.addRepeated(builder, GeneratedMessage.invokeOrDie(this.valueOfMethod, null, new Object[]{value}));
            }
        }

        /*
         * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
         */
        private static final class SingularEnumFieldAccessor
        extends SingularFieldAccessor {
            private Method valueOfMethod;
            private Method getValueDescriptorMethod;

            SingularEnumFieldAccessor(Descriptors.FieldDescriptor descriptor, String camelCaseName, Class<? extends GeneratedMessage> messageClass, Class<? extends Builder> builderClass, String containingOneofCamelCaseName) {
                super(descriptor, camelCaseName, messageClass, builderClass, containingOneofCamelCaseName);
                this.valueOfMethod = GeneratedMessage.getMethodOrDie(this.type, "valueOf", new Class[]{Descriptors.EnumValueDescriptor.class});
                this.getValueDescriptorMethod = GeneratedMessage.getMethodOrDie(this.type, "getValueDescriptor", new Class[0]);
            }

            @Override
            public Object get(GeneratedMessage message) {
                return GeneratedMessage.invokeOrDie(this.getValueDescriptorMethod, super.get(message), new Object[0]);
            }

            @Override
            public Object get(Builder builder) {
                return GeneratedMessage.invokeOrDie(this.getValueDescriptorMethod, super.get(builder), new Object[0]);
            }

            @Override
            public void set(Builder builder, Object value) {
                super.set(builder, GeneratedMessage.invokeOrDie(this.valueOfMethod, null, new Object[]{value}));
            }
        }

        /*
         * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
         */
        private static class RepeatedFieldAccessor
        implements FieldAccessor {
            protected final Class type;
            protected final Method getMethod;
            protected final Method getMethodBuilder;
            protected final Method getRepeatedMethod;
            protected final Method getRepeatedMethodBuilder;
            protected final Method setRepeatedMethod;
            protected final Method addRepeatedMethod;
            protected final Method getCountMethod;
            protected final Method getCountMethodBuilder;
            protected final Method clearMethod;

            RepeatedFieldAccessor(Descriptors.FieldDescriptor descriptor, String camelCaseName, Class<? extends GeneratedMessage> messageClass, Class<? extends Builder> builderClass) {
                String string = String.valueOf(String.valueOf(camelCaseName));
                this.getMethod = GeneratedMessage.getMethodOrDie(messageClass, new StringBuilder(7 + string.length()).append("get").append(string).append("List").toString(), new Class[0]);
                String string2 = String.valueOf(String.valueOf(camelCaseName));
                this.getMethodBuilder = GeneratedMessage.getMethodOrDie(builderClass, new StringBuilder(7 + string2.length()).append("get").append(string2).append("List").toString(), new Class[0]);
                String string3 = String.valueOf(camelCaseName);
                this.getRepeatedMethod = GeneratedMessage.getMethodOrDie(messageClass, string3.length() != 0 ? "get".concat(string3) : new String("get"), new Class[]{Integer.TYPE});
                String string4 = String.valueOf(camelCaseName);
                this.getRepeatedMethodBuilder = GeneratedMessage.getMethodOrDie(builderClass, string4.length() != 0 ? "get".concat(string4) : new String("get"), new Class[]{Integer.TYPE});
                this.type = this.getRepeatedMethod.getReturnType();
                String string5 = String.valueOf(camelCaseName);
                this.setRepeatedMethod = GeneratedMessage.getMethodOrDie(builderClass, string5.length() != 0 ? "set".concat(string5) : new String("set"), new Class[]{Integer.TYPE, this.type});
                String string6 = String.valueOf(camelCaseName);
                this.addRepeatedMethod = GeneratedMessage.getMethodOrDie(builderClass, string6.length() != 0 ? "add".concat(string6) : new String("add"), new Class[]{this.type});
                String string7 = String.valueOf(String.valueOf(camelCaseName));
                this.getCountMethod = GeneratedMessage.getMethodOrDie(messageClass, new StringBuilder(8 + string7.length()).append("get").append(string7).append("Count").toString(), new Class[0]);
                String string8 = String.valueOf(String.valueOf(camelCaseName));
                this.getCountMethodBuilder = GeneratedMessage.getMethodOrDie(builderClass, new StringBuilder(8 + string8.length()).append("get").append(string8).append("Count").toString(), new Class[0]);
                String string9 = String.valueOf(camelCaseName);
                this.clearMethod = GeneratedMessage.getMethodOrDie(builderClass, string9.length() != 0 ? "clear".concat(string9) : new String("clear"), new Class[0]);
            }

            @Override
            public Object get(GeneratedMessage message) {
                return GeneratedMessage.invokeOrDie(this.getMethod, message, new Object[0]);
            }

            @Override
            public Object get(Builder builder) {
                return GeneratedMessage.invokeOrDie(this.getMethodBuilder, builder, new Object[0]);
            }

            @Override
            public void set(Builder builder, Object value) {
                this.clear(builder);
                for (Object element : (List)value) {
                    this.addRepeated(builder, element);
                }
            }

            @Override
            public Object getRepeated(GeneratedMessage message, int index) {
                return GeneratedMessage.invokeOrDie(this.getRepeatedMethod, message, new Object[]{index});
            }

            @Override
            public Object getRepeated(Builder builder, int index) {
                return GeneratedMessage.invokeOrDie(this.getRepeatedMethodBuilder, builder, new Object[]{index});
            }

            @Override
            public void setRepeated(Builder builder, int index, Object value) {
                GeneratedMessage.invokeOrDie(this.setRepeatedMethod, builder, new Object[]{index, value});
            }

            @Override
            public void addRepeated(Builder builder, Object value) {
                GeneratedMessage.invokeOrDie(this.addRepeatedMethod, builder, new Object[]{value});
            }

            @Override
            public boolean has(GeneratedMessage message) {
                throw new UnsupportedOperationException("hasField() called on a repeated field.");
            }

            @Override
            public boolean has(Builder builder) {
                throw new UnsupportedOperationException("hasField() called on a repeated field.");
            }

            @Override
            public int getRepeatedCount(GeneratedMessage message) {
                return (Integer)GeneratedMessage.invokeOrDie(this.getCountMethod, message, new Object[0]);
            }

            @Override
            public int getRepeatedCount(Builder builder) {
                return (Integer)GeneratedMessage.invokeOrDie(this.getCountMethodBuilder, builder, new Object[0]);
            }

            @Override
            public void clear(Builder builder) {
                GeneratedMessage.invokeOrDie(this.clearMethod, builder, new Object[0]);
            }

            @Override
            public Message.Builder newBuilder() {
                throw new UnsupportedOperationException("newBuilderForField() called on a non-Message type.");
            }

            @Override
            public Message.Builder getBuilder(Builder builder) {
                throw new UnsupportedOperationException("getFieldBuilder() called on a non-Message type.");
            }
        }

        /*
         * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
         */
        private static class SingularFieldAccessor
        implements FieldAccessor {
            protected final Class<?> type;
            protected final Method getMethod;
            protected final Method getMethodBuilder;
            protected final Method setMethod;
            protected final Method hasMethod;
            protected final Method hasMethodBuilder;
            protected final Method clearMethod;
            protected final Method caseMethod;
            protected final Method caseMethodBuilder;
            protected final Descriptors.FieldDescriptor field;
            protected final boolean isOneofField;
            protected final boolean hasHasMethod;

            SingularFieldAccessor(Descriptors.FieldDescriptor descriptor, String camelCaseName, Class<? extends GeneratedMessage> messageClass, Class<? extends Builder> builderClass, String containingOneofCamelCaseName) {
                Method method;
                Method method2;
                Method method3;
                Method method4;
                this.field = descriptor;
                this.isOneofField = descriptor.getContainingOneof() != null;
                this.hasHasMethod = FieldAccessorTable.supportFieldPresence(descriptor.getFile()) || !this.isOneofField && descriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE;
                String string = String.valueOf(camelCaseName);
                this.getMethod = GeneratedMessage.getMethodOrDie(messageClass, string.length() != 0 ? "get".concat(string) : new String("get"), new Class[0]);
                String string2 = String.valueOf(camelCaseName);
                this.getMethodBuilder = GeneratedMessage.getMethodOrDie(builderClass, string2.length() != 0 ? "get".concat(string2) : new String("get"), new Class[0]);
                this.type = this.getMethod.getReturnType();
                String string3 = String.valueOf(camelCaseName);
                this.setMethod = GeneratedMessage.getMethodOrDie(builderClass, string3.length() != 0 ? "set".concat(string3) : new String("set"), new Class[]{this.type});
                if (this.hasHasMethod) {
                    String string4 = String.valueOf(camelCaseName);
                    method4 = GeneratedMessage.getMethodOrDie(messageClass, string4.length() != 0 ? "has".concat(string4) : new String("has"), new Class[0]);
                } else {
                    method4 = this.hasMethod = null;
                }
                if (this.hasHasMethod) {
                    String string5 = String.valueOf(camelCaseName);
                    method3 = GeneratedMessage.getMethodOrDie(builderClass, string5.length() != 0 ? "has".concat(string5) : new String("has"), new Class[0]);
                } else {
                    method3 = null;
                }
                this.hasMethodBuilder = method3;
                String string6 = String.valueOf(camelCaseName);
                this.clearMethod = GeneratedMessage.getMethodOrDie(builderClass, string6.length() != 0 ? "clear".concat(string6) : new String("clear"), new Class[0]);
                if (this.isOneofField) {
                    String string7 = String.valueOf(String.valueOf(containingOneofCamelCaseName));
                    method2 = GeneratedMessage.getMethodOrDie(messageClass, new StringBuilder(7 + string7.length()).append("get").append(string7).append("Case").toString(), new Class[0]);
                } else {
                    method2 = this.caseMethod = null;
                }
                if (this.isOneofField) {
                    String string8 = String.valueOf(String.valueOf(containingOneofCamelCaseName));
                    method = GeneratedMessage.getMethodOrDie(builderClass, new StringBuilder(7 + string8.length()).append("get").append(string8).append("Case").toString(), new Class[0]);
                } else {
                    method = null;
                }
                this.caseMethodBuilder = method;
            }

            private int getOneofFieldNumber(GeneratedMessage message) {
                return ((Internal.EnumLite)GeneratedMessage.invokeOrDie(this.caseMethod, message, new Object[0])).getNumber();
            }

            private int getOneofFieldNumber(Builder builder) {
                return ((Internal.EnumLite)GeneratedMessage.invokeOrDie(this.caseMethodBuilder, builder, new Object[0])).getNumber();
            }

            @Override
            public Object get(GeneratedMessage message) {
                return GeneratedMessage.invokeOrDie(this.getMethod, message, new Object[0]);
            }

            @Override
            public Object get(Builder builder) {
                return GeneratedMessage.invokeOrDie(this.getMethodBuilder, builder, new Object[0]);
            }

            @Override
            public void set(Builder builder, Object value) {
                GeneratedMessage.invokeOrDie(this.setMethod, builder, new Object[]{value});
            }

            @Override
            public Object getRepeated(GeneratedMessage message, int index) {
                throw new UnsupportedOperationException("getRepeatedField() called on a singular field.");
            }

            @Override
            public Object getRepeated(Builder builder, int index) {
                throw new UnsupportedOperationException("getRepeatedField() called on a singular field.");
            }

            @Override
            public void setRepeated(Builder builder, int index, Object value) {
                throw new UnsupportedOperationException("setRepeatedField() called on a singular field.");
            }

            @Override
            public void addRepeated(Builder builder, Object value) {
                throw new UnsupportedOperationException("addRepeatedField() called on a singular field.");
            }

            @Override
            public boolean has(GeneratedMessage message) {
                if (!this.hasHasMethod) {
                    if (this.isOneofField) {
                        return this.getOneofFieldNumber(message) == this.field.getNumber();
                    }
                    return !this.get(message).equals(this.field.getDefaultValue());
                }
                return (Boolean)GeneratedMessage.invokeOrDie(this.hasMethod, message, new Object[0]);
            }

            @Override
            public boolean has(Builder builder) {
                if (!this.hasHasMethod) {
                    if (this.isOneofField) {
                        return this.getOneofFieldNumber(builder) == this.field.getNumber();
                    }
                    return !this.get(builder).equals(this.field.getDefaultValue());
                }
                return (Boolean)GeneratedMessage.invokeOrDie(this.hasMethodBuilder, builder, new Object[0]);
            }

            @Override
            public int getRepeatedCount(GeneratedMessage message) {
                throw new UnsupportedOperationException("getRepeatedFieldSize() called on a singular field.");
            }

            @Override
            public int getRepeatedCount(Builder builder) {
                throw new UnsupportedOperationException("getRepeatedFieldSize() called on a singular field.");
            }

            @Override
            public void clear(Builder builder) {
                GeneratedMessage.invokeOrDie(this.clearMethod, builder, new Object[0]);
            }

            @Override
            public Message.Builder newBuilder() {
                throw new UnsupportedOperationException("newBuilderForField() called on a non-Message type.");
            }

            @Override
            public Message.Builder getBuilder(Builder builder) {
                throw new UnsupportedOperationException("getFieldBuilder() called on a non-Message type.");
            }
        }

        /*
         * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
         */
        private static class OneofAccessor {
            private final Descriptors.Descriptor descriptor;
            private final Method caseMethod;
            private final Method caseMethodBuilder;
            private final Method clearMethod;

            OneofAccessor(Descriptors.Descriptor descriptor, String camelCaseName, Class<? extends GeneratedMessage> messageClass, Class<? extends Builder> builderClass) {
                this.descriptor = descriptor;
                String string = String.valueOf(String.valueOf(camelCaseName));
                this.caseMethod = GeneratedMessage.getMethodOrDie(messageClass, new StringBuilder(7 + string.length()).append("get").append(string).append("Case").toString(), new Class[0]);
                String string2 = String.valueOf(String.valueOf(camelCaseName));
                this.caseMethodBuilder = GeneratedMessage.getMethodOrDie(builderClass, new StringBuilder(7 + string2.length()).append("get").append(string2).append("Case").toString(), new Class[0]);
                String string3 = String.valueOf(camelCaseName);
                this.clearMethod = GeneratedMessage.getMethodOrDie(builderClass, string3.length() != 0 ? "clear".concat(string3) : new String("clear"), new Class[0]);
            }

            public boolean has(GeneratedMessage message) {
                return ((Internal.EnumLite)GeneratedMessage.invokeOrDie(this.caseMethod, message, new Object[0])).getNumber() != 0;
            }

            public boolean has(Builder builder) {
                return ((Internal.EnumLite)GeneratedMessage.invokeOrDie(this.caseMethodBuilder, builder, new Object[0])).getNumber() != 0;
            }

            public Descriptors.FieldDescriptor get(GeneratedMessage message) {
                int fieldNumber = ((Internal.EnumLite)GeneratedMessage.invokeOrDie(this.caseMethod, message, new Object[0])).getNumber();
                if (fieldNumber > 0) {
                    return this.descriptor.findFieldByNumber(fieldNumber);
                }
                return null;
            }

            public Descriptors.FieldDescriptor get(Builder builder) {
                int fieldNumber = ((Internal.EnumLite)GeneratedMessage.invokeOrDie(this.caseMethodBuilder, builder, new Object[0])).getNumber();
                if (fieldNumber > 0) {
                    return this.descriptor.findFieldByNumber(fieldNumber);
                }
                return null;
            }

            public void clear(Builder builder) {
                GeneratedMessage.invokeOrDie(this.clearMethod, builder, new Object[0]);
            }
        }

        private static interface FieldAccessor {
            public Object get(GeneratedMessage var1);

            public Object get(Builder var1);

            public void set(Builder var1, Object var2);

            public Object getRepeated(GeneratedMessage var1, int var2);

            public Object getRepeated(Builder var1, int var2);

            public void setRepeated(Builder var1, int var2, Object var3);

            public void addRepeated(Builder var1, Object var2);

            public boolean has(GeneratedMessage var1);

            public boolean has(Builder var1);

            public int getRepeatedCount(GeneratedMessage var1);

            public int getRepeatedCount(Builder var1);

            public void clear(Builder var1);

            public Message.Builder newBuilder();

            public Message.Builder getBuilder(Builder var1);
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static class GeneratedExtension<ContainingType extends Message, Type>
    extends Extension<ContainingType, Type> {
        private ExtensionDescriptorRetriever descriptorRetriever;
        private final Class singularType;
        private final Message messageDefaultInstance;
        private final Method enumValueOf;
        private final Method enumGetValueDescriptor;
        private final Extension.ExtensionType extensionType;

        GeneratedExtension(ExtensionDescriptorRetriever descriptorRetriever, Class singularType, Message messageDefaultInstance, Extension.ExtensionType extensionType) {
            if (Message.class.isAssignableFrom(singularType) && !singularType.isInstance(messageDefaultInstance)) {
                String string = String.valueOf(singularType.getName());
                throw new IllegalArgumentException(string.length() != 0 ? "Bad messageDefaultInstance for ".concat(string) : new String("Bad messageDefaultInstance for "));
            }
            this.descriptorRetriever = descriptorRetriever;
            this.singularType = singularType;
            this.messageDefaultInstance = messageDefaultInstance;
            if (ProtocolMessageEnum.class.isAssignableFrom(singularType)) {
                this.enumValueOf = GeneratedMessage.getMethodOrDie(singularType, "valueOf", new Class[]{Descriptors.EnumValueDescriptor.class});
                this.enumGetValueDescriptor = GeneratedMessage.getMethodOrDie(singularType, "getValueDescriptor", new Class[0]);
            } else {
                this.enumValueOf = null;
                this.enumGetValueDescriptor = null;
            }
            this.extensionType = extensionType;
        }

        public void internalInit(final Descriptors.FieldDescriptor descriptor) {
            if (this.descriptorRetriever != null) {
                throw new IllegalStateException("Already initialized.");
            }
            this.descriptorRetriever = new ExtensionDescriptorRetriever(){

                public Descriptors.FieldDescriptor getDescriptor() {
                    return descriptor;
                }
            };
        }

        @Override
        public Descriptors.FieldDescriptor getDescriptor() {
            if (this.descriptorRetriever == null) {
                throw new IllegalStateException("getDescriptor() called before internalInit()");
            }
            return this.descriptorRetriever.getDescriptor();
        }

        @Override
        public Message getMessageDefaultInstance() {
            return this.messageDefaultInstance;
        }

        @Override
        protected Extension.ExtensionType getExtensionType() {
            return this.extensionType;
        }

        @Override
        protected Object fromReflectionType(Object value) {
            Descriptors.FieldDescriptor descriptor = this.getDescriptor();
            if (descriptor.isRepeated()) {
                if (descriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE || descriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.ENUM) {
                    ArrayList<Object> result = new ArrayList<Object>();
                    for (Object element : (List)value) {
                        result.add(this.singularFromReflectionType(element));
                    }
                    return result;
                }
                return value;
            }
            return this.singularFromReflectionType(value);
        }

        @Override
        protected Object singularFromReflectionType(Object value) {
            Descriptors.FieldDescriptor descriptor = this.getDescriptor();
            switch (descriptor.getJavaType()) {
                case MESSAGE: {
                    if (this.singularType.isInstance(value)) {
                        return value;
                    }
                    return this.messageDefaultInstance.newBuilderForType().mergeFrom((Message)value).build();
                }
                case ENUM: {
                    return GeneratedMessage.invokeOrDie(this.enumValueOf, null, new Object[]{(Descriptors.EnumValueDescriptor)value});
                }
            }
            return value;
        }

        @Override
        protected Object toReflectionType(Object value) {
            Descriptors.FieldDescriptor descriptor = this.getDescriptor();
            if (descriptor.isRepeated()) {
                if (descriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.ENUM) {
                    ArrayList<Object> result = new ArrayList<Object>();
                    for (Object element : (List)value) {
                        result.add(this.singularToReflectionType(element));
                    }
                    return result;
                }
                return value;
            }
            return this.singularToReflectionType(value);
        }

        @Override
        protected Object singularToReflectionType(Object value) {
            Descriptors.FieldDescriptor descriptor = this.getDescriptor();
            switch (descriptor.getJavaType()) {
                case ENUM: {
                    return GeneratedMessage.invokeOrDie(this.enumGetValueDescriptor, value, new Object[0]);
                }
            }
            return value;
        }

        @Override
        public int getNumber() {
            return this.getDescriptor().getNumber();
        }

        @Override
        public WireFormat.FieldType getLiteType() {
            return this.getDescriptor().getLiteType();
        }

        @Override
        public boolean isRepeated() {
            return this.getDescriptor().isRepeated();
        }

        @Override
        public Type getDefaultValue() {
            if (this.isRepeated()) {
                return (Type)Collections.emptyList();
            }
            if (this.getDescriptor().getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                return (Type)this.messageDefaultInstance;
            }
            return (Type)this.singularFromReflectionType(this.getDescriptor().getDefaultValue());
        }
    }

    private static abstract class CachedDescriptorRetriever
    implements ExtensionDescriptorRetriever {
        private volatile Descriptors.FieldDescriptor descriptor;

        private CachedDescriptorRetriever() {
        }

        protected abstract Descriptors.FieldDescriptor loadDescriptor();

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        public Descriptors.FieldDescriptor getDescriptor() {
            if (this.descriptor == null) {
                CachedDescriptorRetriever cachedDescriptorRetriever = this;
                synchronized (cachedDescriptorRetriever) {
                    if (this.descriptor == null) {
                        this.descriptor = this.loadDescriptor();
                    }
                }
            }
            return this.descriptor;
        }
    }

    static interface ExtensionDescriptorRetriever {
        public Descriptors.FieldDescriptor getDescriptor();
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static abstract class ExtendableBuilder<MessageType extends ExtendableMessage, BuilderType extends ExtendableBuilder>
    extends Builder<BuilderType>
    implements ExtendableMessageOrBuilder<MessageType> {
        private FieldSet<Descriptors.FieldDescriptor> extensions = FieldSet.emptySet();

        protected ExtendableBuilder() {
        }

        protected ExtendableBuilder(BuilderParent parent) {
            super(parent);
        }

        void internalSetExtensionSet(FieldSet<Descriptors.FieldDescriptor> extensions) {
            this.extensions = extensions;
        }

        @Override
        public BuilderType clear() {
            this.extensions = FieldSet.emptySet();
            return (BuilderType)((ExtendableBuilder)super.clear());
        }

        @Override
        public BuilderType clone() {
            throw new UnsupportedOperationException("This is supposed to be overridden by subclasses.");
        }

        private void ensureExtensionsIsMutable() {
            if (this.extensions.isImmutable()) {
                this.extensions = this.extensions.clone();
            }
        }

        private void verifyExtensionContainingType(Extension<MessageType, ?> extension) {
            if (extension.getDescriptor().getContainingType() != this.getDescriptorForType()) {
                String string = String.valueOf(String.valueOf(extension.getDescriptor().getContainingType().getFullName()));
                String string2 = String.valueOf(String.valueOf(this.getDescriptorForType().getFullName()));
                throw new IllegalArgumentException(new StringBuilder(62 + string.length() + string2.length()).append("Extension is for type \"").append(string).append("\" which does not match message type \"").append(string2).append("\".").toString());
            }
        }

        @Override
        public final <Type> boolean hasExtension(Extension<MessageType, Type> extension) {
            this.verifyExtensionContainingType(extension);
            return this.extensions.hasField(extension.getDescriptor());
        }

        @Override
        public final <Type> int getExtensionCount(Extension<MessageType, List<Type>> extension) {
            this.verifyExtensionContainingType(extension);
            Descriptors.FieldDescriptor descriptor = extension.getDescriptor();
            return this.extensions.getRepeatedFieldCount(descriptor);
        }

        @Override
        public final <Type> Type getExtension(Extension<MessageType, Type> extension) {
            this.verifyExtensionContainingType(extension);
            Descriptors.FieldDescriptor descriptor = extension.getDescriptor();
            Object value = this.extensions.getField(descriptor);
            if (value == null) {
                if (descriptor.isRepeated()) {
                    return (Type)Collections.emptyList();
                }
                if (descriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                    return (Type)extension.getMessageDefaultInstance();
                }
                return (Type)extension.fromReflectionType(descriptor.getDefaultValue());
            }
            return (Type)extension.fromReflectionType(value);
        }

        @Override
        public final <Type> Type getExtension(Extension<MessageType, List<Type>> extension, int index) {
            this.verifyExtensionContainingType(extension);
            Descriptors.FieldDescriptor descriptor = extension.getDescriptor();
            return (Type)extension.singularFromReflectionType(this.extensions.getRepeatedField(descriptor, index));
        }

        public final <Type> BuilderType setExtension(Extension<MessageType, Type> extension, Type value) {
            this.verifyExtensionContainingType(extension);
            this.ensureExtensionsIsMutable();
            Descriptors.FieldDescriptor descriptor = extension.getDescriptor();
            this.extensions.setField(descriptor, extension.toReflectionType(value));
            this.onChanged();
            return (BuilderType)this;
        }

        public final <Type> BuilderType setExtension(Extension<MessageType, List<Type>> extension, int index, Type value) {
            this.verifyExtensionContainingType(extension);
            this.ensureExtensionsIsMutable();
            Descriptors.FieldDescriptor descriptor = extension.getDescriptor();
            this.extensions.setRepeatedField(descriptor, index, extension.singularToReflectionType(value));
            this.onChanged();
            return (BuilderType)this;
        }

        public final <Type> BuilderType addExtension(Extension<MessageType, List<Type>> extension, Type value) {
            this.verifyExtensionContainingType(extension);
            this.ensureExtensionsIsMutable();
            Descriptors.FieldDescriptor descriptor = extension.getDescriptor();
            this.extensions.addRepeatedField(descriptor, extension.singularToReflectionType(value));
            this.onChanged();
            return (BuilderType)this;
        }

        public final <Type> BuilderType clearExtension(Extension<MessageType, ?> extension) {
            this.verifyExtensionContainingType(extension);
            this.ensureExtensionsIsMutable();
            this.extensions.clearField(extension.getDescriptor());
            this.onChanged();
            return (BuilderType)this;
        }

        protected boolean extensionsAreInitialized() {
            return this.extensions.isInitialized();
        }

        private FieldSet<Descriptors.FieldDescriptor> buildExtensions() {
            this.extensions.makeImmutable();
            return this.extensions;
        }

        @Override
        public boolean isInitialized() {
            return super.isInitialized() && this.extensionsAreInitialized();
        }

        @Override
        protected boolean parseUnknownField(CodedInputStream input, UnknownFieldSet.Builder unknownFields, ExtensionRegistryLite extensionRegistry, int tag) throws IOException {
            return MessageReflection.mergeFieldFrom(input, unknownFields, extensionRegistry, this.getDescriptorForType(), new MessageReflection.BuilderAdapter(this), tag);
        }

        @Override
        public Map<Descriptors.FieldDescriptor, Object> getAllFields() {
            Map result = ((Builder)this).getAllFieldsMutable();
            result.putAll(this.extensions.getAllFields());
            return Collections.unmodifiableMap(result);
        }

        @Override
        public Object getField(Descriptors.FieldDescriptor field) {
            if (field.isExtension()) {
                this.verifyContainingType(field);
                Object value = this.extensions.getField(field);
                if (value == null) {
                    if (field.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                        return DynamicMessage.getDefaultInstance(field.getMessageType());
                    }
                    return field.getDefaultValue();
                }
                return value;
            }
            return super.getField(field);
        }

        @Override
        public int getRepeatedFieldCount(Descriptors.FieldDescriptor field) {
            if (field.isExtension()) {
                this.verifyContainingType(field);
                return this.extensions.getRepeatedFieldCount(field);
            }
            return super.getRepeatedFieldCount(field);
        }

        @Override
        public Object getRepeatedField(Descriptors.FieldDescriptor field, int index) {
            if (field.isExtension()) {
                this.verifyContainingType(field);
                return this.extensions.getRepeatedField(field, index);
            }
            return super.getRepeatedField(field, index);
        }

        @Override
        public boolean hasField(Descriptors.FieldDescriptor field) {
            if (field.isExtension()) {
                this.verifyContainingType(field);
                return this.extensions.hasField(field);
            }
            return super.hasField(field);
        }

        @Override
        public BuilderType setField(Descriptors.FieldDescriptor field, Object value) {
            if (field.isExtension()) {
                this.verifyContainingType(field);
                this.ensureExtensionsIsMutable();
                this.extensions.setField(field, value);
                this.onChanged();
                return (BuilderType)this;
            }
            return (BuilderType)((ExtendableBuilder)super.setField(field, value));
        }

        @Override
        public BuilderType clearField(Descriptors.FieldDescriptor field) {
            if (field.isExtension()) {
                this.verifyContainingType(field);
                this.ensureExtensionsIsMutable();
                this.extensions.clearField(field);
                this.onChanged();
                return (BuilderType)this;
            }
            return (BuilderType)((ExtendableBuilder)super.clearField(field));
        }

        @Override
        public BuilderType setRepeatedField(Descriptors.FieldDescriptor field, int index, Object value) {
            if (field.isExtension()) {
                this.verifyContainingType(field);
                this.ensureExtensionsIsMutable();
                this.extensions.setRepeatedField(field, index, value);
                this.onChanged();
                return (BuilderType)this;
            }
            return (BuilderType)((ExtendableBuilder)super.setRepeatedField(field, index, value));
        }

        @Override
        public BuilderType addRepeatedField(Descriptors.FieldDescriptor field, Object value) {
            if (field.isExtension()) {
                this.verifyContainingType(field);
                this.ensureExtensionsIsMutable();
                this.extensions.addRepeatedField(field, value);
                this.onChanged();
                return (BuilderType)this;
            }
            return (BuilderType)((ExtendableBuilder)super.addRepeatedField(field, value));
        }

        protected final void mergeExtensionFields(ExtendableMessage other) {
            this.ensureExtensionsIsMutable();
            this.extensions.mergeFrom(other.extensions);
            this.onChanged();
        }

        private void verifyContainingType(Descriptors.FieldDescriptor field) {
            if (field.getContainingType() != this.getDescriptorForType()) {
                throw new IllegalArgumentException("FieldDescriptor does not match message type.");
            }
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static abstract class ExtendableMessage<MessageType extends ExtendableMessage>
    extends GeneratedMessage
    implements ExtendableMessageOrBuilder<MessageType> {
        private final FieldSet<Descriptors.FieldDescriptor> extensions;

        protected ExtendableMessage() {
            this.extensions = FieldSet.newFieldSet();
        }

        protected ExtendableMessage(ExtendableBuilder<MessageType, ?> builder) {
            super(builder);
            this.extensions = ((ExtendableBuilder)builder).buildExtensions();
        }

        private void verifyExtensionContainingType(Extension<MessageType, ?> extension) {
            if (extension.getDescriptor().getContainingType() != this.getDescriptorForType()) {
                String string = String.valueOf(String.valueOf(extension.getDescriptor().getContainingType().getFullName()));
                String string2 = String.valueOf(String.valueOf(this.getDescriptorForType().getFullName()));
                throw new IllegalArgumentException(new StringBuilder(62 + string.length() + string2.length()).append("Extension is for type \"").append(string).append("\" which does not match message type \"").append(string2).append("\".").toString());
            }
        }

        @Override
        public final <Type> boolean hasExtension(Extension<MessageType, Type> extension) {
            this.verifyExtensionContainingType(extension);
            return this.extensions.hasField(extension.getDescriptor());
        }

        @Override
        public final <Type> int getExtensionCount(Extension<MessageType, List<Type>> extension) {
            this.verifyExtensionContainingType(extension);
            Descriptors.FieldDescriptor descriptor = extension.getDescriptor();
            return this.extensions.getRepeatedFieldCount(descriptor);
        }

        @Override
        public final <Type> Type getExtension(Extension<MessageType, Type> extension) {
            this.verifyExtensionContainingType(extension);
            Descriptors.FieldDescriptor descriptor = extension.getDescriptor();
            Object value = this.extensions.getField(descriptor);
            if (value == null) {
                if (descriptor.isRepeated()) {
                    return (Type)Collections.emptyList();
                }
                if (descriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                    return (Type)extension.getMessageDefaultInstance();
                }
                return (Type)extension.fromReflectionType(descriptor.getDefaultValue());
            }
            return (Type)extension.fromReflectionType(value);
        }

        @Override
        public final <Type> Type getExtension(Extension<MessageType, List<Type>> extension, int index) {
            this.verifyExtensionContainingType(extension);
            Descriptors.FieldDescriptor descriptor = extension.getDescriptor();
            return (Type)extension.singularFromReflectionType(this.extensions.getRepeatedField(descriptor, index));
        }

        protected boolean extensionsAreInitialized() {
            return this.extensions.isInitialized();
        }

        @Override
        public boolean isInitialized() {
            return super.isInitialized() && this.extensionsAreInitialized();
        }

        @Override
        protected boolean parseUnknownField(CodedInputStream input, UnknownFieldSet.Builder unknownFields, ExtensionRegistryLite extensionRegistry, int tag) throws IOException {
            return MessageReflection.mergeFieldFrom(input, unknownFields, extensionRegistry, this.getDescriptorForType(), new MessageReflection.ExtensionAdapter(this.extensions), tag);
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

        protected Map<Descriptors.FieldDescriptor, Object> getExtensionFields() {
            return this.extensions.getAllFields();
        }

        @Override
        public Map<Descriptors.FieldDescriptor, Object> getAllFields() {
            Map result = ((GeneratedMessage)this).getAllFieldsMutable();
            result.putAll(this.getExtensionFields());
            return Collections.unmodifiableMap(result);
        }

        @Override
        public boolean hasField(Descriptors.FieldDescriptor field) {
            if (field.isExtension()) {
                this.verifyContainingType(field);
                return this.extensions.hasField(field);
            }
            return super.hasField(field);
        }

        @Override
        public Object getField(Descriptors.FieldDescriptor field) {
            if (field.isExtension()) {
                this.verifyContainingType(field);
                Object value = this.extensions.getField(field);
                if (value == null) {
                    if (field.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                        return DynamicMessage.getDefaultInstance(field.getMessageType());
                    }
                    return field.getDefaultValue();
                }
                return value;
            }
            return super.getField(field);
        }

        @Override
        public int getRepeatedFieldCount(Descriptors.FieldDescriptor field) {
            if (field.isExtension()) {
                this.verifyContainingType(field);
                return this.extensions.getRepeatedFieldCount(field);
            }
            return super.getRepeatedFieldCount(field);
        }

        @Override
        public Object getRepeatedField(Descriptors.FieldDescriptor field, int index) {
            if (field.isExtension()) {
                this.verifyContainingType(field);
                return this.extensions.getRepeatedField(field, index);
            }
            return super.getRepeatedField(field, index);
        }

        private void verifyContainingType(Descriptors.FieldDescriptor field) {
            if (field.getContainingType() != this.getDescriptorForType()) {
                throw new IllegalArgumentException("FieldDescriptor does not match message type.");
            }
        }

        protected class ExtensionWriter {
            private final Iterator<Map.Entry<Descriptors.FieldDescriptor, Object>> iter;
            private Map.Entry<Descriptors.FieldDescriptor, Object> next;
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
                    Descriptors.FieldDescriptor descriptor = this.next.getKey();
                    if (this.messageSetWireFormat && descriptor.getLiteJavaType() == WireFormat.JavaType.MESSAGE && !descriptor.isRepeated()) {
                        if (this.next instanceof LazyField.LazyEntry) {
                            output.writeRawMessageSetExtension(descriptor.getNumber(), ((LazyField.LazyEntry)this.next).getField().toByteString());
                        } else {
                            output.writeMessageSetExtension(descriptor.getNumber(), (Message)this.next.getValue());
                        }
                    } else {
                        FieldSet.writeField(descriptor, this.next.getValue(), output);
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
    extends MessageOrBuilder {
        @Override
        public Message getDefaultInstanceForType();

        public <Type> boolean hasExtension(Extension<MessageType, Type> var1);

        public <Type> int getExtensionCount(Extension<MessageType, List<Type>> var1);

        public <Type> Type getExtension(Extension<MessageType, Type> var1);

        public <Type> Type getExtension(Extension<MessageType, List<Type>> var1, int var2);
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static abstract class Builder<BuilderType extends Builder>
    extends AbstractMessage.Builder<BuilderType> {
        private BuilderParent builderParent;
        private BuilderParentImpl meAsParent;
        private boolean isClean;
        private UnknownFieldSet unknownFields = UnknownFieldSet.getDefaultInstance();

        protected Builder() {
            this(null);
        }

        protected Builder(BuilderParent builderParent) {
            this.builderParent = builderParent;
        }

        void dispose() {
            this.builderParent = null;
        }

        protected void onBuilt() {
            if (this.builderParent != null) {
                this.markClean();
            }
        }

        protected void markClean() {
            this.isClean = true;
        }

        protected boolean isClean() {
            return this.isClean;
        }

        @Override
        public BuilderType clone() {
            throw new UnsupportedOperationException("This is supposed to be overridden by subclasses.");
        }

        @Override
        public BuilderType clear() {
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
            this.onChanged();
            return (BuilderType)this;
        }

        protected abstract FieldAccessorTable internalGetFieldAccessorTable();

        @Override
        public Descriptors.Descriptor getDescriptorForType() {
            return this.internalGetFieldAccessorTable().descriptor;
        }

        @Override
        public Map<Descriptors.FieldDescriptor, Object> getAllFields() {
            return Collections.unmodifiableMap(this.getAllFieldsMutable());
        }

        private Map<Descriptors.FieldDescriptor, Object> getAllFieldsMutable() {
            TreeMap<Descriptors.FieldDescriptor, Object> result = new TreeMap<Descriptors.FieldDescriptor, Object>();
            Descriptors.Descriptor descriptor = this.internalGetFieldAccessorTable().descriptor;
            for (Descriptors.FieldDescriptor field : descriptor.getFields()) {
                if (field.isRepeated()) {
                    List value = (List)this.getField(field);
                    if (value.isEmpty()) continue;
                    result.put(field, value);
                    continue;
                }
                if (!this.hasField(field)) continue;
                result.put(field, this.getField(field));
            }
            return result;
        }

        @Override
        public Message.Builder newBuilderForField(Descriptors.FieldDescriptor field) {
            return this.internalGetFieldAccessorTable().getField(field).newBuilder();
        }

        @Override
        public Message.Builder getFieldBuilder(Descriptors.FieldDescriptor field) {
            return this.internalGetFieldAccessorTable().getField(field).getBuilder(this);
        }

        @Override
        public boolean hasOneof(Descriptors.OneofDescriptor oneof) {
            return this.internalGetFieldAccessorTable().getOneof(oneof).has(this);
        }

        @Override
        public Descriptors.FieldDescriptor getOneofFieldDescriptor(Descriptors.OneofDescriptor oneof) {
            return this.internalGetFieldAccessorTable().getOneof(oneof).get(this);
        }

        @Override
        public boolean hasField(Descriptors.FieldDescriptor field) {
            return this.internalGetFieldAccessorTable().getField(field).has(this);
        }

        @Override
        public Object getField(Descriptors.FieldDescriptor field) {
            Object object = this.internalGetFieldAccessorTable().getField(field).get(this);
            if (field.isRepeated()) {
                return Collections.unmodifiableList((List)object);
            }
            return object;
        }

        public BuilderType setField(Descriptors.FieldDescriptor field, Object value) {
            this.internalGetFieldAccessorTable().getField(field).set(this, value);
            return (BuilderType)this;
        }

        public BuilderType clearField(Descriptors.FieldDescriptor field) {
            this.internalGetFieldAccessorTable().getField(field).clear(this);
            return (BuilderType)this;
        }

        @Override
        public BuilderType clearOneof(Descriptors.OneofDescriptor oneof) {
            this.internalGetFieldAccessorTable().getOneof(oneof).clear(this);
            return (BuilderType)this;
        }

        @Override
        public int getRepeatedFieldCount(Descriptors.FieldDescriptor field) {
            return this.internalGetFieldAccessorTable().getField(field).getRepeatedCount(this);
        }

        @Override
        public Object getRepeatedField(Descriptors.FieldDescriptor field, int index) {
            return this.internalGetFieldAccessorTable().getField(field).getRepeated(this, index);
        }

        public BuilderType setRepeatedField(Descriptors.FieldDescriptor field, int index, Object value) {
            this.internalGetFieldAccessorTable().getField(field).setRepeated(this, index, value);
            return (BuilderType)this;
        }

        public BuilderType addRepeatedField(Descriptors.FieldDescriptor field, Object value) {
            this.internalGetFieldAccessorTable().getField(field).addRepeated(this, value);
            return (BuilderType)this;
        }

        public final BuilderType setUnknownFields(UnknownFieldSet unknownFields) {
            this.unknownFields = unknownFields;
            this.onChanged();
            return (BuilderType)this;
        }

        @Override
        public final BuilderType mergeUnknownFields(UnknownFieldSet unknownFields) {
            this.unknownFields = UnknownFieldSet.newBuilder(this.unknownFields).mergeFrom(unknownFields).build();
            this.onChanged();
            return (BuilderType)this;
        }

        @Override
        public boolean isInitialized() {
            for (Descriptors.FieldDescriptor field : this.getDescriptorForType().getFields()) {
                if (field.isRequired() && !this.hasField(field)) {
                    return false;
                }
                if (field.getJavaType() != Descriptors.FieldDescriptor.JavaType.MESSAGE) continue;
                if (field.isRepeated()) {
                    List messageList = (List)this.getField(field);
                    for (Message element : messageList) {
                        if (element.isInitialized()) continue;
                        return false;
                    }
                    continue;
                }
                if (!this.hasField(field) || ((Message)this.getField(field)).isInitialized()) continue;
                return false;
            }
            return true;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        protected boolean parseUnknownField(CodedInputStream input, UnknownFieldSet.Builder unknownFields, ExtensionRegistryLite extensionRegistry, int tag) throws IOException {
            return unknownFields.mergeFieldFrom(tag, input);
        }

        protected BuilderParent getParentForChildren() {
            if (this.meAsParent == null) {
                this.meAsParent = new BuilderParentImpl();
            }
            return this.meAsParent;
        }

        protected final void onChanged() {
            if (this.isClean && this.builderParent != null) {
                this.builderParent.markDirty();
                this.isClean = false;
            }
        }

        private class BuilderParentImpl
        implements BuilderParent {
            private BuilderParentImpl() {
            }

            public void markDirty() {
                Builder.this.onChanged();
            }
        }
    }

    protected static interface BuilderParent {
        public void markDirty();
    }
}

