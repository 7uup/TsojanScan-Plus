/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.protobuf;

import com.google.protobuf.ByteString;
import com.google.protobuf.DescriptorProtos;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.FieldSet;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.MessageLite;
import com.google.protobuf.TextFormat;
import com.google.protobuf.WireFormat;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

public final class Descriptors {
    private static final Logger logger = Logger.getLogger(Descriptors.class.getName());

    private static String computeFullName(FileDescriptor file, Descriptor parent, String name) {
        if (parent != null) {
            String string = String.valueOf(String.valueOf(parent.getFullName()));
            String string2 = String.valueOf(String.valueOf(name));
            return new StringBuilder(1 + string.length() + string2.length()).append(string).append(".").append(string2).toString();
        }
        if (file.getPackage().length() > 0) {
            String string = String.valueOf(String.valueOf(file.getPackage()));
            String string3 = String.valueOf(String.valueOf(name));
            return new StringBuilder(1 + string.length() + string3.length()).append(string).append(".").append(string3).toString();
        }
        return name;
    }

    public static final class OneofDescriptor {
        private final int index;
        private DescriptorProtos.OneofDescriptorProto proto;
        private final String fullName;
        private final FileDescriptor file;
        private Descriptor containingType;
        private int fieldCount;
        private FieldDescriptor[] fields;

        public int getIndex() {
            return this.index;
        }

        public String getName() {
            return this.proto.getName();
        }

        public FileDescriptor getFile() {
            return this.file;
        }

        public String getFullName() {
            return this.fullName;
        }

        public Descriptor getContainingType() {
            return this.containingType;
        }

        public int getFieldCount() {
            return this.fieldCount;
        }

        public FieldDescriptor getField(int index) {
            return this.fields[index];
        }

        private OneofDescriptor(DescriptorProtos.OneofDescriptorProto proto, FileDescriptor file, Descriptor parent, int index) throws DescriptorValidationException {
            this.proto = proto;
            this.fullName = Descriptors.computeFullName(file, parent, proto.getName());
            this.file = file;
            this.index = index;
            this.containingType = parent;
            this.fieldCount = 0;
        }

        static /* synthetic */ FieldDescriptor[] access$1702(OneofDescriptor x0, FieldDescriptor[] x1) {
            x0.fields = x1;
            return x1;
        }
    }

    private static final class DescriptorPool {
        private final Set<FileDescriptor> dependencies;
        private boolean allowUnknownDependencies;
        private final Map<String, GenericDescriptor> descriptorsByName = new HashMap<String, GenericDescriptor>();
        private final Map<DescriptorIntPair, FieldDescriptor> fieldsByNumber = new HashMap<DescriptorIntPair, FieldDescriptor>();
        private final Map<DescriptorIntPair, EnumValueDescriptor> enumValuesByNumber = new HashMap<DescriptorIntPair, EnumValueDescriptor>();

        DescriptorPool(FileDescriptor[] dependencies, boolean allowUnknownDependencies) {
            this.dependencies = new HashSet<FileDescriptor>();
            this.allowUnknownDependencies = allowUnknownDependencies;
            for (int i = 0; i < dependencies.length; ++i) {
                this.dependencies.add(dependencies[i]);
                this.importPublicDependencies(dependencies[i]);
            }
            for (FileDescriptor dependency : this.dependencies) {
                try {
                    this.addPackage(dependency.getPackage(), dependency);
                } catch (DescriptorValidationException e) {
                    assert (false);
                }
            }
        }

        private void importPublicDependencies(FileDescriptor file) {
            for (FileDescriptor dependency : file.getPublicDependencies()) {
                if (!this.dependencies.add(dependency)) continue;
                this.importPublicDependencies(dependency);
            }
        }

        GenericDescriptor findSymbol(String fullName) {
            return this.findSymbol(fullName, SearchFilter.ALL_SYMBOLS);
        }

        GenericDescriptor findSymbol(String fullName, SearchFilter filter) {
            GenericDescriptor result = this.descriptorsByName.get(fullName);
            if (result != null && (filter == SearchFilter.ALL_SYMBOLS || filter == SearchFilter.TYPES_ONLY && this.isType(result) || filter == SearchFilter.AGGREGATES_ONLY && this.isAggregate(result))) {
                return result;
            }
            for (FileDescriptor dependency : this.dependencies) {
                result = ((FileDescriptor)dependency).pool.descriptorsByName.get(fullName);
                if (result == null || filter != SearchFilter.ALL_SYMBOLS && (filter != SearchFilter.TYPES_ONLY || !this.isType(result)) && (filter != SearchFilter.AGGREGATES_ONLY || !this.isAggregate(result))) continue;
                return result;
            }
            return null;
        }

        boolean isType(GenericDescriptor descriptor) {
            return descriptor instanceof Descriptor || descriptor instanceof EnumDescriptor;
        }

        boolean isAggregate(GenericDescriptor descriptor) {
            return descriptor instanceof Descriptor || descriptor instanceof EnumDescriptor || descriptor instanceof PackageDescriptor || descriptor instanceof ServiceDescriptor;
        }

        GenericDescriptor lookupSymbol(String name, GenericDescriptor relativeTo, SearchFilter filter) throws DescriptorValidationException {
            GenericDescriptor result;
            String fullname;
            if (name.startsWith(".")) {
                fullname = name.substring(1);
                result = this.findSymbol(fullname, filter);
            } else {
                int firstPartLength = name.indexOf(46);
                String firstPart = firstPartLength == -1 ? name : name.substring(0, firstPartLength);
                StringBuilder scopeToTry = new StringBuilder(relativeTo.getFullName());
                while (true) {
                    int dotpos;
                    if ((dotpos = scopeToTry.lastIndexOf(".")) == -1) {
                        fullname = name;
                        result = this.findSymbol(name, filter);
                        break;
                    }
                    scopeToTry.setLength(dotpos + 1);
                    scopeToTry.append(firstPart);
                    result = this.findSymbol(scopeToTry.toString(), SearchFilter.AGGREGATES_ONLY);
                    if (result != null) {
                        if (firstPartLength != -1) {
                            scopeToTry.setLength(dotpos + 1);
                            scopeToTry.append(name);
                            result = this.findSymbol(scopeToTry.toString(), filter);
                        }
                        fullname = scopeToTry.toString();
                        break;
                    }
                    scopeToTry.setLength(dotpos);
                }
            }
            if (result == null) {
                if (this.allowUnknownDependencies && filter == SearchFilter.TYPES_ONLY) {
                    String string = String.valueOf(String.valueOf(name));
                    logger.warning(new StringBuilder(87 + string.length()).append("The descriptor for message type \"").append(string).append("\" can not be found and a placeholder is created for it").toString());
                    result = new Descriptor(fullname);
                    this.dependencies.add(result.getFile());
                    return result;
                }
                String string = String.valueOf(String.valueOf(name));
                throw new DescriptorValidationException(relativeTo, new StringBuilder(18 + string.length()).append("\"").append(string).append("\" is not defined.").toString());
            }
            return result;
        }

        void addSymbol(GenericDescriptor descriptor) throws DescriptorValidationException {
            DescriptorPool.validateSymbolName(descriptor);
            String fullName = descriptor.getFullName();
            int dotpos = fullName.lastIndexOf(46);
            GenericDescriptor old = this.descriptorsByName.put(fullName, descriptor);
            if (old != null) {
                this.descriptorsByName.put(fullName, old);
                if (descriptor.getFile() == old.getFile()) {
                    if (dotpos == -1) {
                        String string = String.valueOf(String.valueOf(fullName));
                        throw new DescriptorValidationException(descriptor, new StringBuilder(22 + string.length()).append("\"").append(string).append("\" is already defined.").toString());
                    }
                    String string = String.valueOf(String.valueOf(fullName.substring(dotpos + 1)));
                    String string2 = String.valueOf(String.valueOf(fullName.substring(0, dotpos)));
                    throw new DescriptorValidationException(descriptor, new StringBuilder(28 + string.length() + string2.length()).append("\"").append(string).append("\" is already defined in \"").append(string2).append("\".").toString());
                }
                String string = String.valueOf(String.valueOf(fullName));
                String string3 = String.valueOf(String.valueOf(old.getFile().getName()));
                throw new DescriptorValidationException(descriptor, new StringBuilder(33 + string.length() + string3.length()).append("\"").append(string).append("\" is already defined in file \"").append(string3).append("\".").toString());
            }
        }

        void addPackage(String fullName, FileDescriptor file) throws DescriptorValidationException {
            String name;
            int dotpos = fullName.lastIndexOf(46);
            if (dotpos == -1) {
                name = fullName;
            } else {
                this.addPackage(fullName.substring(0, dotpos), file);
                name = fullName.substring(dotpos + 1);
            }
            GenericDescriptor old = this.descriptorsByName.put(fullName, new PackageDescriptor(name, fullName, file));
            if (old != null) {
                this.descriptorsByName.put(fullName, old);
                if (!(old instanceof PackageDescriptor)) {
                    String string = String.valueOf(String.valueOf(name));
                    String string2 = String.valueOf(String.valueOf(old.getFile().getName()));
                    throw new DescriptorValidationException(file, new StringBuilder(69 + string.length() + string2.length()).append("\"").append(string).append("\" is already defined (as something other than a ").append("package) in file \"").append(string2).append("\".").toString());
                }
            }
        }

        void addFieldByNumber(FieldDescriptor field) throws DescriptorValidationException {
            DescriptorIntPair key = new DescriptorIntPair(field.getContainingType(), field.getNumber());
            FieldDescriptor old = this.fieldsByNumber.put(key, field);
            if (old != null) {
                this.fieldsByNumber.put(key, old);
                int n = field.getNumber();
                String string = String.valueOf(String.valueOf(field.getContainingType().getFullName()));
                String string2 = String.valueOf(String.valueOf(old.getName()));
                throw new DescriptorValidationException((GenericDescriptor)field, new StringBuilder(65 + string.length() + string2.length()).append("Field number ").append(n).append(" has already been used in \"").append(string).append("\" by field \"").append(string2).append("\".").toString());
            }
        }

        void addEnumValueByNumber(EnumValueDescriptor value) {
            DescriptorIntPair key = new DescriptorIntPair(value.getType(), value.getNumber());
            EnumValueDescriptor old = this.enumValuesByNumber.put(key, value);
            if (old != null) {
                this.enumValuesByNumber.put(key, old);
            }
        }

        static void validateSymbolName(GenericDescriptor descriptor) throws DescriptorValidationException {
            String name = descriptor.getName();
            if (name.length() == 0) {
                throw new DescriptorValidationException(descriptor, "Missing name.");
            }
            boolean valid = true;
            for (int i = 0; i < name.length(); ++i) {
                char c = name.charAt(i);
                if (c >= '\u0080') {
                    valid = false;
                }
                if (Character.isLetter(c) || c == '_' || Character.isDigit(c) && i > 0) continue;
                valid = false;
            }
            if (!valid) {
                String string = String.valueOf(String.valueOf(name));
                throw new DescriptorValidationException(descriptor, new StringBuilder(29 + string.length()).append("\"").append(string).append("\" is not a valid identifier.").toString());
            }
        }

        private static final class DescriptorIntPair {
            private final GenericDescriptor descriptor;
            private final int number;

            DescriptorIntPair(GenericDescriptor descriptor, int number) {
                this.descriptor = descriptor;
                this.number = number;
            }

            public int hashCode() {
                return this.descriptor.hashCode() * 65535 + this.number;
            }

            public boolean equals(Object obj) {
                if (!(obj instanceof DescriptorIntPair)) {
                    return false;
                }
                DescriptorIntPair other = (DescriptorIntPair)obj;
                return this.descriptor == other.descriptor && this.number == other.number;
            }
        }

        private static final class PackageDescriptor
        extends GenericDescriptor {
            private final String name;
            private final String fullName;
            private final FileDescriptor file;

            public Message toProto() {
                return this.file.toProto();
            }

            public String getName() {
                return this.name;
            }

            public String getFullName() {
                return this.fullName;
            }

            public FileDescriptor getFile() {
                return this.file;
            }

            PackageDescriptor(String name, String fullName, FileDescriptor file) {
                this.file = file;
                this.fullName = fullName;
                this.name = name;
            }
        }

        /*
         * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
         */
        static enum SearchFilter {
            TYPES_ONLY,
            AGGREGATES_ONLY,
            ALL_SYMBOLS;

        }
    }

    public static class DescriptorValidationException
    extends Exception {
        private static final long serialVersionUID = 5750205775490483148L;
        private final String name;
        private final Message proto;
        private final String description;

        public String getProblemSymbolName() {
            return this.name;
        }

        public Message getProblemProto() {
            return this.proto;
        }

        public String getDescription() {
            return this.description;
        }

        private DescriptorValidationException(GenericDescriptor problemDescriptor, String description) {
            String string = String.valueOf(String.valueOf(problemDescriptor.getFullName()));
            String string2 = String.valueOf(String.valueOf(description));
            super(new StringBuilder(2 + string.length() + string2.length()).append(string).append(": ").append(string2).toString());
            this.name = problemDescriptor.getFullName();
            this.proto = problemDescriptor.toProto();
            this.description = description;
        }

        private DescriptorValidationException(GenericDescriptor problemDescriptor, String description, Throwable cause) {
            this(problemDescriptor, description);
            this.initCause(cause);
        }

        private DescriptorValidationException(FileDescriptor problemDescriptor, String description) {
            String string = String.valueOf(String.valueOf(problemDescriptor.getName()));
            String string2 = String.valueOf(String.valueOf(description));
            super(new StringBuilder(2 + string.length() + string2.length()).append(string).append(": ").append(string2).toString());
            this.name = problemDescriptor.getName();
            this.proto = problemDescriptor.toProto();
            this.description = description;
        }
    }

    public static abstract class GenericDescriptor {
        public abstract Message toProto();

        public abstract String getName();

        public abstract String getFullName();

        public abstract FileDescriptor getFile();
    }

    public static final class MethodDescriptor
    extends GenericDescriptor {
        private final int index;
        private DescriptorProtos.MethodDescriptorProto proto;
        private final String fullName;
        private final FileDescriptor file;
        private final ServiceDescriptor service;
        private Descriptor inputType;
        private Descriptor outputType;

        public int getIndex() {
            return this.index;
        }

        public DescriptorProtos.MethodDescriptorProto toProto() {
            return this.proto;
        }

        public String getName() {
            return this.proto.getName();
        }

        public String getFullName() {
            return this.fullName;
        }

        public FileDescriptor getFile() {
            return this.file;
        }

        public ServiceDescriptor getService() {
            return this.service;
        }

        public Descriptor getInputType() {
            return this.inputType;
        }

        public Descriptor getOutputType() {
            return this.outputType;
        }

        public DescriptorProtos.MethodOptions getOptions() {
            return this.proto.getOptions();
        }

        private MethodDescriptor(DescriptorProtos.MethodDescriptorProto proto, FileDescriptor file, ServiceDescriptor parent, int index) throws DescriptorValidationException {
            this.index = index;
            this.proto = proto;
            this.file = file;
            this.service = parent;
            String string = String.valueOf(String.valueOf(parent.getFullName()));
            String string2 = String.valueOf(String.valueOf(proto.getName()));
            this.fullName = new StringBuilder(1 + string.length() + string2.length()).append(string).append(".").append(string2).toString();
            file.pool.addSymbol(this);
        }

        private void crossLink() throws DescriptorValidationException {
            GenericDescriptor input = this.file.pool.lookupSymbol(this.proto.getInputType(), this, DescriptorPool.SearchFilter.TYPES_ONLY);
            if (!(input instanceof Descriptor)) {
                String string = String.valueOf(String.valueOf(this.proto.getInputType()));
                throw new DescriptorValidationException((GenericDescriptor)this, new StringBuilder(25 + string.length()).append("\"").append(string).append("\" is not a message type.").toString());
            }
            this.inputType = (Descriptor)input;
            GenericDescriptor output = this.file.pool.lookupSymbol(this.proto.getOutputType(), this, DescriptorPool.SearchFilter.TYPES_ONLY);
            if (!(output instanceof Descriptor)) {
                String string = String.valueOf(String.valueOf(this.proto.getOutputType()));
                throw new DescriptorValidationException((GenericDescriptor)this, new StringBuilder(25 + string.length()).append("\"").append(string).append("\" is not a message type.").toString());
            }
            this.outputType = (Descriptor)output;
        }

        private void setProto(DescriptorProtos.MethodDescriptorProto proto) {
            this.proto = proto;
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static final class ServiceDescriptor
    extends GenericDescriptor {
        private final int index;
        private DescriptorProtos.ServiceDescriptorProto proto;
        private final String fullName;
        private final FileDescriptor file;
        private MethodDescriptor[] methods;

        public int getIndex() {
            return this.index;
        }

        @Override
        public DescriptorProtos.ServiceDescriptorProto toProto() {
            return this.proto;
        }

        @Override
        public String getName() {
            return this.proto.getName();
        }

        @Override
        public String getFullName() {
            return this.fullName;
        }

        @Override
        public FileDescriptor getFile() {
            return this.file;
        }

        public DescriptorProtos.ServiceOptions getOptions() {
            return this.proto.getOptions();
        }

        public List<MethodDescriptor> getMethods() {
            return Collections.unmodifiableList(Arrays.asList(this.methods));
        }

        public MethodDescriptor findMethodByName(String name) {
            String string = String.valueOf(String.valueOf(this.fullName));
            String string2 = String.valueOf(String.valueOf(name));
            GenericDescriptor result = this.file.pool.findSymbol(new StringBuilder(1 + string.length() + string2.length()).append(string).append(".").append(string2).toString());
            if (result != null && result instanceof MethodDescriptor) {
                return (MethodDescriptor)result;
            }
            return null;
        }

        private ServiceDescriptor(DescriptorProtos.ServiceDescriptorProto proto, FileDescriptor file, int index) throws DescriptorValidationException {
            this.index = index;
            this.proto = proto;
            this.fullName = Descriptors.computeFullName(file, null, proto.getName());
            this.file = file;
            this.methods = new MethodDescriptor[proto.getMethodCount()];
            for (int i = 0; i < proto.getMethodCount(); ++i) {
                this.methods[i] = new MethodDescriptor(proto.getMethod(i), file, this, i);
            }
            file.pool.addSymbol(this);
        }

        private void crossLink() throws DescriptorValidationException {
            for (MethodDescriptor method : this.methods) {
                method.crossLink();
            }
        }

        private void setProto(DescriptorProtos.ServiceDescriptorProto proto) {
            this.proto = proto;
            for (int i = 0; i < this.methods.length; ++i) {
                this.methods[i].setProto(proto.getMethod(i));
            }
        }
    }

    public static final class EnumValueDescriptor
    extends GenericDescriptor
    implements Internal.EnumLite {
        private final int index;
        private DescriptorProtos.EnumValueDescriptorProto proto;
        private final String fullName;
        private final FileDescriptor file;
        private final EnumDescriptor type;

        public int getIndex() {
            return this.index;
        }

        public DescriptorProtos.EnumValueDescriptorProto toProto() {
            return this.proto;
        }

        public String getName() {
            return this.proto.getName();
        }

        public int getNumber() {
            return this.proto.getNumber();
        }

        public String toString() {
            return this.proto.getName();
        }

        public String getFullName() {
            return this.fullName;
        }

        public FileDescriptor getFile() {
            return this.file;
        }

        public EnumDescriptor getType() {
            return this.type;
        }

        public DescriptorProtos.EnumValueOptions getOptions() {
            return this.proto.getOptions();
        }

        private EnumValueDescriptor(DescriptorProtos.EnumValueDescriptorProto proto, FileDescriptor file, EnumDescriptor parent, int index) throws DescriptorValidationException {
            this.index = index;
            this.proto = proto;
            this.file = file;
            this.type = parent;
            String string = String.valueOf(String.valueOf(parent.getFullName()));
            String string2 = String.valueOf(String.valueOf(proto.getName()));
            this.fullName = new StringBuilder(1 + string.length() + string2.length()).append(string).append(".").append(string2).toString();
            file.pool.addSymbol(this);
            file.pool.addEnumValueByNumber(this);
        }

        private void setProto(DescriptorProtos.EnumValueDescriptorProto proto) {
            this.proto = proto;
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static final class EnumDescriptor
    extends GenericDescriptor
    implements Internal.EnumLiteMap<EnumValueDescriptor> {
        private final int index;
        private DescriptorProtos.EnumDescriptorProto proto;
        private final String fullName;
        private final FileDescriptor file;
        private final Descriptor containingType;
        private EnumValueDescriptor[] values;

        public int getIndex() {
            return this.index;
        }

        @Override
        public DescriptorProtos.EnumDescriptorProto toProto() {
            return this.proto;
        }

        @Override
        public String getName() {
            return this.proto.getName();
        }

        @Override
        public String getFullName() {
            return this.fullName;
        }

        @Override
        public FileDescriptor getFile() {
            return this.file;
        }

        public Descriptor getContainingType() {
            return this.containingType;
        }

        public DescriptorProtos.EnumOptions getOptions() {
            return this.proto.getOptions();
        }

        public List<EnumValueDescriptor> getValues() {
            return Collections.unmodifiableList(Arrays.asList(this.values));
        }

        public EnumValueDescriptor findValueByName(String name) {
            String string = String.valueOf(String.valueOf(this.fullName));
            String string2 = String.valueOf(String.valueOf(name));
            GenericDescriptor result = this.file.pool.findSymbol(new StringBuilder(1 + string.length() + string2.length()).append(string).append(".").append(string2).toString());
            if (result != null && result instanceof EnumValueDescriptor) {
                return (EnumValueDescriptor)result;
            }
            return null;
        }

        @Override
        public EnumValueDescriptor findValueByNumber(int number) {
            return (EnumValueDescriptor)this.file.pool.enumValuesByNumber.get(new DescriptorPool.DescriptorIntPair(this, number));
        }

        private EnumDescriptor(DescriptorProtos.EnumDescriptorProto proto, FileDescriptor file, Descriptor parent, int index) throws DescriptorValidationException {
            this.index = index;
            this.proto = proto;
            this.fullName = Descriptors.computeFullName(file, parent, proto.getName());
            this.file = file;
            this.containingType = parent;
            if (proto.getValueCount() == 0) {
                throw new DescriptorValidationException((GenericDescriptor)this, "Enums must contain at least one value.");
            }
            this.values = new EnumValueDescriptor[proto.getValueCount()];
            for (int i = 0; i < proto.getValueCount(); ++i) {
                this.values[i] = new EnumValueDescriptor(proto.getValue(i), file, this, i);
            }
            file.pool.addSymbol(this);
        }

        private void setProto(DescriptorProtos.EnumDescriptorProto proto) {
            this.proto = proto;
            for (int i = 0; i < this.values.length; ++i) {
                this.values[i].setProto(proto.getValue(i));
            }
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static final class FieldDescriptor
    extends GenericDescriptor
    implements Comparable<FieldDescriptor>,
    FieldSet.FieldDescriptorLite<FieldDescriptor> {
        private static final WireFormat.FieldType[] table = WireFormat.FieldType.values();
        private final int index;
        private DescriptorProtos.FieldDescriptorProto proto;
        private final String fullName;
        private final FileDescriptor file;
        private final Descriptor extensionScope;
        private Type type;
        private Descriptor containingType;
        private Descriptor messageType;
        private OneofDescriptor containingOneof;
        private EnumDescriptor enumType;
        private Object defaultValue;

        public int getIndex() {
            return this.index;
        }

        @Override
        public DescriptorProtos.FieldDescriptorProto toProto() {
            return this.proto;
        }

        @Override
        public String getName() {
            return this.proto.getName();
        }

        @Override
        public int getNumber() {
            return this.proto.getNumber();
        }

        @Override
        public String getFullName() {
            return this.fullName;
        }

        public JavaType getJavaType() {
            return this.type.getJavaType();
        }

        @Override
        public WireFormat.JavaType getLiteJavaType() {
            return this.getLiteType().getJavaType();
        }

        @Override
        public FileDescriptor getFile() {
            return this.file;
        }

        public Type getType() {
            return this.type;
        }

        @Override
        public WireFormat.FieldType getLiteType() {
            return table[this.type.ordinal()];
        }

        public boolean needsUtf8Check() {
            return this.type == Type.STRING && this.getFile().getOptions().getJavaStringCheckUtf8();
        }

        public boolean isRequired() {
            return this.proto.getLabel() == DescriptorProtos.FieldDescriptorProto.Label.LABEL_REQUIRED;
        }

        public boolean isOptional() {
            return this.proto.getLabel() == DescriptorProtos.FieldDescriptorProto.Label.LABEL_OPTIONAL;
        }

        @Override
        public boolean isRepeated() {
            return this.proto.getLabel() == DescriptorProtos.FieldDescriptorProto.Label.LABEL_REPEATED;
        }

        @Override
        public boolean isPacked() {
            return this.getOptions().getPacked();
        }

        public boolean isPackable() {
            return this.isRepeated() && this.getLiteType().isPackable();
        }

        public boolean hasDefaultValue() {
            return this.proto.hasDefaultValue();
        }

        public Object getDefaultValue() {
            if (this.getJavaType() == JavaType.MESSAGE) {
                throw new UnsupportedOperationException("FieldDescriptor.getDefaultValue() called on an embedded message field.");
            }
            return this.defaultValue;
        }

        public DescriptorProtos.FieldOptions getOptions() {
            return this.proto.getOptions();
        }

        public boolean isExtension() {
            return this.proto.hasExtendee();
        }

        public Descriptor getContainingType() {
            return this.containingType;
        }

        public OneofDescriptor getContainingOneof() {
            return this.containingOneof;
        }

        public Descriptor getExtensionScope() {
            if (!this.isExtension()) {
                throw new UnsupportedOperationException("This field is not an extension.");
            }
            return this.extensionScope;
        }

        public Descriptor getMessageType() {
            if (this.getJavaType() != JavaType.MESSAGE) {
                throw new UnsupportedOperationException("This field is not of message type.");
            }
            return this.messageType;
        }

        public EnumDescriptor getEnumType() {
            if (this.getJavaType() != JavaType.ENUM) {
                throw new UnsupportedOperationException("This field is not of enum type.");
            }
            return this.enumType;
        }

        @Override
        public int compareTo(FieldDescriptor other) {
            if (other.containingType != this.containingType) {
                throw new IllegalArgumentException("FieldDescriptors can only be compared to other FieldDescriptors for fields of the same message type.");
            }
            return this.getNumber() - other.getNumber();
        }

        private FieldDescriptor(DescriptorProtos.FieldDescriptorProto proto, FileDescriptor file, Descriptor parent, int index, boolean isExtension) throws DescriptorValidationException {
            this.index = index;
            this.proto = proto;
            this.fullName = Descriptors.computeFullName(file, parent, proto.getName());
            this.file = file;
            if (proto.hasType()) {
                this.type = Type.valueOf(proto.getType());
            }
            if (this.getNumber() <= 0) {
                throw new DescriptorValidationException((GenericDescriptor)this, "Field numbers must be positive integers.");
            }
            if (proto.getOptions().getPacked() && !this.isPackable()) {
                throw new DescriptorValidationException((GenericDescriptor)this, "[packed = true] can only be specified for repeated primitive fields.");
            }
            if (isExtension) {
                if (!proto.hasExtendee()) {
                    throw new DescriptorValidationException((GenericDescriptor)this, "FieldDescriptorProto.extendee not set for extension field.");
                }
                this.containingType = null;
                this.extensionScope = parent != null ? parent : null;
                if (proto.hasOneofIndex()) {
                    throw new DescriptorValidationException((GenericDescriptor)this, "FieldDescriptorProto.oneof_index set for extension field.");
                }
                this.containingOneof = null;
            } else {
                if (proto.hasExtendee()) {
                    throw new DescriptorValidationException((GenericDescriptor)this, "FieldDescriptorProto.extendee set for non-extension field.");
                }
                this.containingType = parent;
                if (proto.hasOneofIndex()) {
                    if (proto.getOneofIndex() < 0 || proto.getOneofIndex() >= parent.toProto().getOneofDeclCount()) {
                        String string = String.valueOf(parent.getName());
                        throw new DescriptorValidationException((GenericDescriptor)this, string.length() != 0 ? "FieldDescriptorProto.oneof_index is out of range for type ".concat(string) : new String("FieldDescriptorProto.oneof_index is out of range for type "));
                    }
                    this.containingOneof = parent.getOneofs().get(proto.getOneofIndex());
                    this.containingOneof.fieldCount++;
                } else {
                    this.containingOneof = null;
                }
                this.extensionScope = null;
            }
            file.pool.addSymbol(this);
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        private void crossLink() throws DescriptorValidationException {
            if (this.proto.hasExtendee()) {
                GenericDescriptor extendee = this.file.pool.lookupSymbol(this.proto.getExtendee(), this, DescriptorPool.SearchFilter.TYPES_ONLY);
                if (!(extendee instanceof Descriptor)) {
                    String string = String.valueOf(String.valueOf(this.proto.getExtendee()));
                    throw new DescriptorValidationException((GenericDescriptor)this, new StringBuilder(25 + string.length()).append("\"").append(string).append("\" is not a message type.").toString());
                }
                this.containingType = (Descriptor)extendee;
                if (!this.getContainingType().isExtensionNumber(this.getNumber())) {
                    String string = String.valueOf(String.valueOf(this.getContainingType().getFullName()));
                    int n = this.getNumber();
                    throw new DescriptorValidationException((GenericDescriptor)this, new StringBuilder(55 + string.length()).append("\"").append(string).append("\" does not declare ").append(n).append(" as an extension number.").toString());
                }
            }
            if (this.proto.hasTypeName()) {
                GenericDescriptor typeDescriptor = this.file.pool.lookupSymbol(this.proto.getTypeName(), this, DescriptorPool.SearchFilter.TYPES_ONLY);
                if (!this.proto.hasType()) {
                    if (typeDescriptor instanceof Descriptor) {
                        this.type = Type.MESSAGE;
                    } else if (typeDescriptor instanceof EnumDescriptor) {
                        this.type = Type.ENUM;
                    } else {
                        String string = String.valueOf(String.valueOf(this.proto.getTypeName()));
                        throw new DescriptorValidationException((GenericDescriptor)this, new StringBuilder(17 + string.length()).append("\"").append(string).append("\" is not a type.").toString());
                    }
                }
                if (this.getJavaType() == JavaType.MESSAGE) {
                    if (!(typeDescriptor instanceof Descriptor)) {
                        String string = String.valueOf(String.valueOf(this.proto.getTypeName()));
                        throw new DescriptorValidationException((GenericDescriptor)this, new StringBuilder(25 + string.length()).append("\"").append(string).append("\" is not a message type.").toString());
                    }
                    this.messageType = (Descriptor)typeDescriptor;
                    if (this.proto.hasDefaultValue()) {
                        throw new DescriptorValidationException((GenericDescriptor)this, "Messages can't have default values.");
                    }
                } else {
                    if (this.getJavaType() != JavaType.ENUM) throw new DescriptorValidationException((GenericDescriptor)this, "Field with primitive type has type_name.");
                    if (!(typeDescriptor instanceof EnumDescriptor)) {
                        String string = String.valueOf(String.valueOf(this.proto.getTypeName()));
                        throw new DescriptorValidationException((GenericDescriptor)this, new StringBuilder(23 + string.length()).append("\"").append(string).append("\" is not an enum type.").toString());
                    }
                    this.enumType = (EnumDescriptor)typeDescriptor;
                }
            } else if (this.getJavaType() == JavaType.MESSAGE || this.getJavaType() == JavaType.ENUM) {
                throw new DescriptorValidationException((GenericDescriptor)this, "Field with message or enum type missing type_name.");
            }
            if (this.proto.hasDefaultValue()) {
                if (this.isRepeated()) {
                    throw new DescriptorValidationException((GenericDescriptor)this, "Repeated fields cannot have default values.");
                }
                try {
                    switch (this.getType()) {
                        case INT32: 
                        case SINT32: 
                        case SFIXED32: {
                            this.defaultValue = TextFormat.parseInt32(this.proto.getDefaultValue());
                            break;
                        }
                        case UINT32: 
                        case FIXED32: {
                            this.defaultValue = TextFormat.parseUInt32(this.proto.getDefaultValue());
                            break;
                        }
                        case INT64: 
                        case SINT64: 
                        case SFIXED64: {
                            this.defaultValue = TextFormat.parseInt64(this.proto.getDefaultValue());
                            break;
                        }
                        case UINT64: 
                        case FIXED64: {
                            this.defaultValue = TextFormat.parseUInt64(this.proto.getDefaultValue());
                            break;
                        }
                        case FLOAT: {
                            if (this.proto.getDefaultValue().equals("inf")) {
                                this.defaultValue = Float.valueOf(Float.POSITIVE_INFINITY);
                                break;
                            }
                            if (this.proto.getDefaultValue().equals("-inf")) {
                                this.defaultValue = Float.valueOf(Float.NEGATIVE_INFINITY);
                                break;
                            }
                            if (this.proto.getDefaultValue().equals("nan")) {
                                this.defaultValue = Float.valueOf(Float.NaN);
                                break;
                            }
                            this.defaultValue = Float.valueOf(this.proto.getDefaultValue());
                            break;
                        }
                        case DOUBLE: {
                            if (this.proto.getDefaultValue().equals("inf")) {
                                this.defaultValue = Double.POSITIVE_INFINITY;
                                break;
                            }
                            if (this.proto.getDefaultValue().equals("-inf")) {
                                this.defaultValue = Double.NEGATIVE_INFINITY;
                                break;
                            }
                            if (this.proto.getDefaultValue().equals("nan")) {
                                this.defaultValue = Double.NaN;
                                break;
                            }
                            this.defaultValue = Double.valueOf(this.proto.getDefaultValue());
                            break;
                        }
                        case BOOL: {
                            this.defaultValue = Boolean.valueOf(this.proto.getDefaultValue());
                            break;
                        }
                        case STRING: {
                            this.defaultValue = this.proto.getDefaultValue();
                            break;
                        }
                        case BYTES: {
                            try {
                                this.defaultValue = TextFormat.unescapeBytes(this.proto.getDefaultValue());
                                break;
                            } catch (TextFormat.InvalidEscapeSequenceException e) {
                                String string = String.valueOf(e.getMessage());
                                throw new DescriptorValidationException(this, string.length() != 0 ? "Couldn't parse default value: ".concat(string) : new String("Couldn't parse default value: "), e);
                            }
                        }
                        case ENUM: {
                            this.defaultValue = this.enumType.findValueByName(this.proto.getDefaultValue());
                            if (this.defaultValue != null) break;
                            String e = String.valueOf(String.valueOf(this.proto.getDefaultValue()));
                            throw new DescriptorValidationException((GenericDescriptor)this, new StringBuilder(30 + e.length()).append("Unknown enum default value: \"").append(e).append("\"").toString());
                        }
                        case MESSAGE: 
                        case GROUP: {
                            throw new DescriptorValidationException((GenericDescriptor)this, "Message type had default value.");
                        }
                    }
                } catch (NumberFormatException e) {
                    String string = String.valueOf(String.valueOf(this.proto.getDefaultValue()));
                    throw new DescriptorValidationException(this, new StringBuilder(33 + string.length()).append("Could not parse default value: \"").append(string).append("\"").toString(), e);
                }
            } else if (this.isRepeated()) {
                this.defaultValue = Collections.emptyList();
            } else {
                switch (this.getJavaType()) {
                    case ENUM: {
                        this.defaultValue = this.enumType.getValues().get(0);
                        break;
                    }
                    case MESSAGE: {
                        this.defaultValue = null;
                        break;
                    }
                    default: {
                        this.defaultValue = this.getJavaType().defaultDefault;
                    }
                }
            }
            if (!this.isExtension()) {
                this.file.pool.addFieldByNumber(this);
            }
            if (this.containingType == null || !this.containingType.getOptions().getMessageSetWireFormat()) return;
            if (!this.isExtension()) throw new DescriptorValidationException((GenericDescriptor)this, "MessageSets cannot have fields, only extensions.");
            if (this.isOptional() && this.getType() == Type.MESSAGE) return;
            throw new DescriptorValidationException((GenericDescriptor)this, "Extensions of MessageSets must be optional messages.");
        }

        private void setProto(DescriptorProtos.FieldDescriptorProto proto) {
            this.proto = proto;
        }

        @Override
        public MessageLite.Builder internalMergeFrom(MessageLite.Builder to, MessageLite from) {
            return ((Message.Builder)to).mergeFrom((Message)from);
        }

        static {
            if (Type.values().length != DescriptorProtos.FieldDescriptorProto.Type.values().length) {
                throw new RuntimeException("descriptor.proto has a new declared type but Desrciptors.java wasn't updated.");
            }
        }

        /*
         * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
         */
        public static enum JavaType {
            INT(0),
            LONG(0L),
            FLOAT(Float.valueOf(0.0f)),
            DOUBLE(0.0),
            BOOLEAN(false),
            STRING(""),
            BYTE_STRING(ByteString.EMPTY),
            ENUM(null),
            MESSAGE(null);

            private final Object defaultDefault;

            private JavaType(Object defaultDefault) {
                this.defaultDefault = defaultDefault;
            }
        }

        /*
         * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
         */
        public static enum Type {
            DOUBLE(JavaType.DOUBLE),
            FLOAT(JavaType.FLOAT),
            INT64(JavaType.LONG),
            UINT64(JavaType.LONG),
            INT32(JavaType.INT),
            FIXED64(JavaType.LONG),
            FIXED32(JavaType.INT),
            BOOL(JavaType.BOOLEAN),
            STRING(JavaType.STRING),
            GROUP(JavaType.MESSAGE),
            MESSAGE(JavaType.MESSAGE),
            BYTES(JavaType.BYTE_STRING),
            UINT32(JavaType.INT),
            ENUM(JavaType.ENUM),
            SFIXED32(JavaType.INT),
            SFIXED64(JavaType.LONG),
            SINT32(JavaType.INT),
            SINT64(JavaType.LONG);

            private JavaType javaType;

            private Type(JavaType javaType) {
                this.javaType = javaType;
            }

            public DescriptorProtos.FieldDescriptorProto.Type toProto() {
                return DescriptorProtos.FieldDescriptorProto.Type.valueOf(this.ordinal() + 1);
            }

            public JavaType getJavaType() {
                return this.javaType;
            }

            public static Type valueOf(DescriptorProtos.FieldDescriptorProto.Type type) {
                return Type.values()[type.getNumber() - 1];
            }
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static final class Descriptor
    extends GenericDescriptor {
        private final int index;
        private DescriptorProtos.DescriptorProto proto;
        private final String fullName;
        private final FileDescriptor file;
        private final Descriptor containingType;
        private final Descriptor[] nestedTypes;
        private final EnumDescriptor[] enumTypes;
        private final FieldDescriptor[] fields;
        private final FieldDescriptor[] extensions;
        private final OneofDescriptor[] oneofs;

        public int getIndex() {
            return this.index;
        }

        @Override
        public DescriptorProtos.DescriptorProto toProto() {
            return this.proto;
        }

        @Override
        public String getName() {
            return this.proto.getName();
        }

        @Override
        public String getFullName() {
            return this.fullName;
        }

        @Override
        public FileDescriptor getFile() {
            return this.file;
        }

        public Descriptor getContainingType() {
            return this.containingType;
        }

        public DescriptorProtos.MessageOptions getOptions() {
            return this.proto.getOptions();
        }

        public List<FieldDescriptor> getFields() {
            return Collections.unmodifiableList(Arrays.asList(this.fields));
        }

        public List<OneofDescriptor> getOneofs() {
            return Collections.unmodifiableList(Arrays.asList(this.oneofs));
        }

        public List<FieldDescriptor> getExtensions() {
            return Collections.unmodifiableList(Arrays.asList(this.extensions));
        }

        public List<Descriptor> getNestedTypes() {
            return Collections.unmodifiableList(Arrays.asList(this.nestedTypes));
        }

        public List<EnumDescriptor> getEnumTypes() {
            return Collections.unmodifiableList(Arrays.asList(this.enumTypes));
        }

        public boolean isExtensionNumber(int number) {
            for (DescriptorProtos.DescriptorProto.ExtensionRange range : this.proto.getExtensionRangeList()) {
                if (range.getStart() > number || number >= range.getEnd()) continue;
                return true;
            }
            return false;
        }

        public boolean isExtendable() {
            return this.proto.getExtensionRangeList().size() != 0;
        }

        public FieldDescriptor findFieldByName(String name) {
            String string = String.valueOf(String.valueOf(this.fullName));
            String string2 = String.valueOf(String.valueOf(name));
            GenericDescriptor result = this.file.pool.findSymbol(new StringBuilder(1 + string.length() + string2.length()).append(string).append(".").append(string2).toString());
            if (result != null && result instanceof FieldDescriptor) {
                return (FieldDescriptor)result;
            }
            return null;
        }

        public FieldDescriptor findFieldByNumber(int number) {
            return (FieldDescriptor)this.file.pool.fieldsByNumber.get(new DescriptorPool.DescriptorIntPair(this, number));
        }

        public Descriptor findNestedTypeByName(String name) {
            String string = String.valueOf(String.valueOf(this.fullName));
            String string2 = String.valueOf(String.valueOf(name));
            GenericDescriptor result = this.file.pool.findSymbol(new StringBuilder(1 + string.length() + string2.length()).append(string).append(".").append(string2).toString());
            if (result != null && result instanceof Descriptor) {
                return (Descriptor)result;
            }
            return null;
        }

        public EnumDescriptor findEnumTypeByName(String name) {
            String string = String.valueOf(String.valueOf(this.fullName));
            String string2 = String.valueOf(String.valueOf(name));
            GenericDescriptor result = this.file.pool.findSymbol(new StringBuilder(1 + string.length() + string2.length()).append(string).append(".").append(string2).toString());
            if (result != null && result instanceof EnumDescriptor) {
                return (EnumDescriptor)result;
            }
            return null;
        }

        Descriptor(String fullname) throws DescriptorValidationException {
            String name = fullname;
            String packageName = "";
            int pos = fullname.lastIndexOf(46);
            if (pos != -1) {
                name = fullname.substring(pos + 1);
                packageName = fullname.substring(0, pos);
            }
            this.index = 0;
            this.proto = DescriptorProtos.DescriptorProto.newBuilder().setName(name).addExtensionRange(DescriptorProtos.DescriptorProto.ExtensionRange.newBuilder().setStart(1).setEnd(0x20000000).build()).build();
            this.fullName = fullname;
            this.containingType = null;
            this.nestedTypes = new Descriptor[0];
            this.enumTypes = new EnumDescriptor[0];
            this.fields = new FieldDescriptor[0];
            this.extensions = new FieldDescriptor[0];
            this.oneofs = new OneofDescriptor[0];
            this.file = new FileDescriptor(packageName, this);
        }

        private Descriptor(DescriptorProtos.DescriptorProto proto, FileDescriptor file, Descriptor parent, int index) throws DescriptorValidationException {
            int i;
            this.index = index;
            this.proto = proto;
            this.fullName = Descriptors.computeFullName(file, parent, proto.getName());
            this.file = file;
            this.containingType = parent;
            this.oneofs = new OneofDescriptor[proto.getOneofDeclCount()];
            for (i = 0; i < proto.getOneofDeclCount(); ++i) {
                this.oneofs[i] = new OneofDescriptor(proto.getOneofDecl(i), file, this, i);
            }
            this.nestedTypes = new Descriptor[proto.getNestedTypeCount()];
            for (i = 0; i < proto.getNestedTypeCount(); ++i) {
                this.nestedTypes[i] = new Descriptor(proto.getNestedType(i), file, this, i);
            }
            this.enumTypes = new EnumDescriptor[proto.getEnumTypeCount()];
            for (i = 0; i < proto.getEnumTypeCount(); ++i) {
                this.enumTypes[i] = new EnumDescriptor(proto.getEnumType(i), file, this, i);
            }
            this.fields = new FieldDescriptor[proto.getFieldCount()];
            for (i = 0; i < proto.getFieldCount(); ++i) {
                this.fields[i] = new FieldDescriptor(proto.getField(i), file, this, i, false);
            }
            this.extensions = new FieldDescriptor[proto.getExtensionCount()];
            for (i = 0; i < proto.getExtensionCount(); ++i) {
                this.extensions[i] = new FieldDescriptor(proto.getExtension(i), file, this, i, true);
            }
            for (i = 0; i < proto.getOneofDeclCount(); ++i) {
                OneofDescriptor.access$1702(this.oneofs[i], new FieldDescriptor[this.oneofs[i].getFieldCount()]);
                this.oneofs[i].fieldCount = 0;
            }
            for (i = 0; i < proto.getFieldCount(); ++i) {
                OneofDescriptor oneofDescriptor = this.fields[i].getContainingOneof();
                if (oneofDescriptor == null) continue;
                ((OneofDescriptor)oneofDescriptor).fields[((OneofDescriptor)oneofDescriptor).fieldCount++] = this.fields[i];
            }
            file.pool.addSymbol(this);
        }

        private void crossLink() throws DescriptorValidationException {
            for (Descriptor descriptor : this.nestedTypes) {
                descriptor.crossLink();
            }
            for (GenericDescriptor genericDescriptor : this.fields) {
                ((FieldDescriptor)genericDescriptor).crossLink();
            }
            for (GenericDescriptor genericDescriptor : this.extensions) {
                ((FieldDescriptor)genericDescriptor).crossLink();
            }
        }

        private void setProto(DescriptorProtos.DescriptorProto proto) {
            int i;
            this.proto = proto;
            for (i = 0; i < this.nestedTypes.length; ++i) {
                this.nestedTypes[i].setProto(proto.getNestedType(i));
            }
            for (i = 0; i < this.enumTypes.length; ++i) {
                this.enumTypes[i].setProto(proto.getEnumType(i));
            }
            for (i = 0; i < this.fields.length; ++i) {
                this.fields[i].setProto(proto.getField(i));
            }
            for (i = 0; i < this.extensions.length; ++i) {
                this.extensions[i].setProto(proto.getExtension(i));
            }
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static final class FileDescriptor
    extends GenericDescriptor {
        private DescriptorProtos.FileDescriptorProto proto;
        private final Descriptor[] messageTypes;
        private final EnumDescriptor[] enumTypes;
        private final ServiceDescriptor[] services;
        private final FieldDescriptor[] extensions;
        private final FileDescriptor[] dependencies;
        private final FileDescriptor[] publicDependencies;
        private final DescriptorPool pool;

        @Override
        public DescriptorProtos.FileDescriptorProto toProto() {
            return this.proto;
        }

        @Override
        public String getName() {
            return this.proto.getName();
        }

        @Override
        public FileDescriptor getFile() {
            return this;
        }

        @Override
        public String getFullName() {
            return this.proto.getName();
        }

        public String getPackage() {
            return this.proto.getPackage();
        }

        public DescriptorProtos.FileOptions getOptions() {
            return this.proto.getOptions();
        }

        public List<Descriptor> getMessageTypes() {
            return Collections.unmodifiableList(Arrays.asList(this.messageTypes));
        }

        public List<EnumDescriptor> getEnumTypes() {
            return Collections.unmodifiableList(Arrays.asList(this.enumTypes));
        }

        public List<ServiceDescriptor> getServices() {
            return Collections.unmodifiableList(Arrays.asList(this.services));
        }

        public List<FieldDescriptor> getExtensions() {
            return Collections.unmodifiableList(Arrays.asList(this.extensions));
        }

        public List<FileDescriptor> getDependencies() {
            return Collections.unmodifiableList(Arrays.asList(this.dependencies));
        }

        public List<FileDescriptor> getPublicDependencies() {
            return Collections.unmodifiableList(Arrays.asList(this.publicDependencies));
        }

        public Descriptor findMessageTypeByName(String name) {
            GenericDescriptor result;
            if (name.indexOf(46) != -1) {
                return null;
            }
            if (this.getPackage().length() > 0) {
                String string = String.valueOf(String.valueOf(this.getPackage()));
                String string2 = String.valueOf(String.valueOf(name));
                name = new StringBuilder(1 + string.length() + string2.length()).append(string).append(".").append(string2).toString();
            }
            if ((result = this.pool.findSymbol(name)) != null && result instanceof Descriptor && result.getFile() == this) {
                return (Descriptor)result;
            }
            return null;
        }

        public EnumDescriptor findEnumTypeByName(String name) {
            GenericDescriptor result;
            if (name.indexOf(46) != -1) {
                return null;
            }
            if (this.getPackage().length() > 0) {
                String string = String.valueOf(String.valueOf(this.getPackage()));
                String string2 = String.valueOf(String.valueOf(name));
                name = new StringBuilder(1 + string.length() + string2.length()).append(string).append(".").append(string2).toString();
            }
            if ((result = this.pool.findSymbol(name)) != null && result instanceof EnumDescriptor && result.getFile() == this) {
                return (EnumDescriptor)result;
            }
            return null;
        }

        public ServiceDescriptor findServiceByName(String name) {
            GenericDescriptor result;
            if (name.indexOf(46) != -1) {
                return null;
            }
            if (this.getPackage().length() > 0) {
                String string = String.valueOf(String.valueOf(this.getPackage()));
                String string2 = String.valueOf(String.valueOf(name));
                name = new StringBuilder(1 + string.length() + string2.length()).append(string).append(".").append(string2).toString();
            }
            if ((result = this.pool.findSymbol(name)) != null && result instanceof ServiceDescriptor && result.getFile() == this) {
                return (ServiceDescriptor)result;
            }
            return null;
        }

        public FieldDescriptor findExtensionByName(String name) {
            GenericDescriptor result;
            if (name.indexOf(46) != -1) {
                return null;
            }
            if (this.getPackage().length() > 0) {
                String string = String.valueOf(String.valueOf(this.getPackage()));
                String string2 = String.valueOf(String.valueOf(name));
                name = new StringBuilder(1 + string.length() + string2.length()).append(string).append(".").append(string2).toString();
            }
            if ((result = this.pool.findSymbol(name)) != null && result instanceof FieldDescriptor && result.getFile() == this) {
                return (FieldDescriptor)result;
            }
            return null;
        }

        public static FileDescriptor buildFrom(DescriptorProtos.FileDescriptorProto proto, FileDescriptor[] dependencies) throws DescriptorValidationException {
            return FileDescriptor.buildFrom(proto, dependencies, false);
        }

        private static FileDescriptor buildFrom(DescriptorProtos.FileDescriptorProto proto, FileDescriptor[] dependencies, boolean allowUnknownDependencies) throws DescriptorValidationException {
            DescriptorPool pool = new DescriptorPool(dependencies, allowUnknownDependencies);
            FileDescriptor result = new FileDescriptor(proto, dependencies, pool, allowUnknownDependencies);
            result.crossLink();
            return result;
        }

        public static void internalBuildGeneratedFileFrom(String[] descriptorDataParts, FileDescriptor[] dependencies, InternalDescriptorAssigner descriptorAssigner) {
            FileDescriptor result;
            DescriptorProtos.FileDescriptorProto proto;
            byte[] descriptorBytes;
            StringBuilder descriptorData = new StringBuilder();
            for (String part : descriptorDataParts) {
                descriptorData.append(part);
            }
            try {
                descriptorBytes = descriptorData.toString().getBytes("ISO-8859-1");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException("Standard encoding ISO-8859-1 not supported by JVM.", e);
            }
            try {
                proto = DescriptorProtos.FileDescriptorProto.parseFrom(descriptorBytes);
            } catch (InvalidProtocolBufferException e) {
                throw new IllegalArgumentException("Failed to parse protocol buffer descriptor for generated code.", e);
            }
            try {
                result = FileDescriptor.buildFrom(proto, dependencies, true);
            } catch (DescriptorValidationException e) {
                String string = String.valueOf(String.valueOf(proto.getName()));
                throw new IllegalArgumentException(new StringBuilder(35 + string.length()).append("Invalid embedded descriptor for \"").append(string).append("\".").toString(), e);
            }
            ExtensionRegistry registry = descriptorAssigner.assignDescriptors(result);
            if (registry != null) {
                try {
                    proto = DescriptorProtos.FileDescriptorProto.parseFrom(descriptorBytes, (ExtensionRegistryLite)registry);
                } catch (InvalidProtocolBufferException e) {
                    throw new IllegalArgumentException("Failed to parse protocol buffer descriptor for generated code.", e);
                }
                result.setProto(proto);
            }
        }

        public static void internalBuildGeneratedFileFrom(String[] descriptorDataParts, Class<?> descriptorOuterClass, String[] dependencies, String[] dependencyFileNames, InternalDescriptorAssigner descriptorAssigner) {
            ArrayList<FileDescriptor> descriptors = new ArrayList<FileDescriptor>();
            for (int i = 0; i < dependencies.length; ++i) {
                try {
                    Class<?> clazz = descriptorOuterClass.getClassLoader().loadClass(dependencies[i]);
                    descriptors.add((FileDescriptor)clazz.getField("descriptor").get(null));
                    continue;
                } catch (Exception e) {
                    String string = String.valueOf(String.valueOf(dependencyFileNames[i]));
                    logger.warning(new StringBuilder(36 + string.length()).append("Descriptors for \"").append(string).append("\" can not be found.").toString());
                }
            }
            FileDescriptor[] descriptorArray = new FileDescriptor[descriptors.size()];
            descriptors.toArray(descriptorArray);
            FileDescriptor.internalBuildGeneratedFileFrom(descriptorDataParts, descriptorArray, descriptorAssigner);
        }

        public static void internalUpdateFileDescriptor(FileDescriptor descriptor, ExtensionRegistry registry) {
            DescriptorProtos.FileDescriptorProto proto;
            ByteString bytes = descriptor.proto.toByteString();
            try {
                proto = DescriptorProtos.FileDescriptorProto.parseFrom(bytes, (ExtensionRegistryLite)registry);
            } catch (InvalidProtocolBufferException e) {
                throw new IllegalArgumentException("Failed to parse protocol buffer descriptor for generated code.", e);
            }
            descriptor.setProto(proto);
        }

        private FileDescriptor(DescriptorProtos.FileDescriptorProto proto, FileDescriptor[] dependencies, DescriptorPool pool, boolean allowUnknownDependencies) throws DescriptorValidationException {
            int i;
            this.pool = pool;
            this.proto = proto;
            this.dependencies = (FileDescriptor[])dependencies.clone();
            HashMap<String, FileDescriptor> nameToFileMap = new HashMap<String, FileDescriptor>();
            for (FileDescriptor file : dependencies) {
                nameToFileMap.put(file.getName(), file);
            }
            ArrayList<FileDescriptor> publicDependencies = new ArrayList<FileDescriptor>();
            for (i = 0; i < proto.getPublicDependencyCount(); ++i) {
                int index = proto.getPublicDependency(i);
                if (index < 0 || index >= proto.getDependencyCount()) {
                    throw new DescriptorValidationException(this, "Invalid public dependency index.");
                }
                String name = proto.getDependency(index);
                FileDescriptor file = (FileDescriptor)nameToFileMap.get(name);
                if (file == null) {
                    if (allowUnknownDependencies) continue;
                    String string = String.valueOf(name);
                    throw new DescriptorValidationException(this, string.length() != 0 ? "Invalid public dependency: ".concat(string) : new String("Invalid public dependency: "));
                }
                publicDependencies.add(file);
            }
            this.publicDependencies = new FileDescriptor[publicDependencies.size()];
            publicDependencies.toArray(this.publicDependencies);
            pool.addPackage(this.getPackage(), this);
            this.messageTypes = new Descriptor[proto.getMessageTypeCount()];
            for (i = 0; i < proto.getMessageTypeCount(); ++i) {
                this.messageTypes[i] = new Descriptor(proto.getMessageType(i), this, null, i);
            }
            this.enumTypes = new EnumDescriptor[proto.getEnumTypeCount()];
            for (i = 0; i < proto.getEnumTypeCount(); ++i) {
                this.enumTypes[i] = new EnumDescriptor(proto.getEnumType(i), this, null, i);
            }
            this.services = new ServiceDescriptor[proto.getServiceCount()];
            for (i = 0; i < proto.getServiceCount(); ++i) {
                this.services[i] = new ServiceDescriptor(proto.getService(i), this, i);
            }
            this.extensions = new FieldDescriptor[proto.getExtensionCount()];
            for (i = 0; i < proto.getExtensionCount(); ++i) {
                this.extensions[i] = new FieldDescriptor(proto.getExtension(i), this, null, i, true);
            }
        }

        FileDescriptor(String packageName, Descriptor message) throws DescriptorValidationException {
            this.pool = new DescriptorPool(new FileDescriptor[0], true);
            this.proto = DescriptorProtos.FileDescriptorProto.newBuilder().setName(String.valueOf(message.getFullName()).concat(".placeholder.proto")).setPackage(packageName).addMessageType(message.toProto()).build();
            this.dependencies = new FileDescriptor[0];
            this.publicDependencies = new FileDescriptor[0];
            this.messageTypes = new Descriptor[]{message};
            this.enumTypes = new EnumDescriptor[0];
            this.services = new ServiceDescriptor[0];
            this.extensions = new FieldDescriptor[0];
            this.pool.addPackage(packageName, this);
            this.pool.addSymbol(message);
        }

        private void crossLink() throws DescriptorValidationException {
            for (Descriptor descriptor : this.messageTypes) {
                descriptor.crossLink();
            }
            for (GenericDescriptor genericDescriptor : this.services) {
                ((ServiceDescriptor)genericDescriptor).crossLink();
            }
            for (GenericDescriptor genericDescriptor : this.extensions) {
                ((FieldDescriptor)genericDescriptor).crossLink();
            }
        }

        private void setProto(DescriptorProtos.FileDescriptorProto proto) {
            int i;
            this.proto = proto;
            for (i = 0; i < this.messageTypes.length; ++i) {
                this.messageTypes[i].setProto(proto.getMessageType(i));
            }
            for (i = 0; i < this.enumTypes.length; ++i) {
                this.enumTypes[i].setProto(proto.getEnumType(i));
            }
            for (i = 0; i < this.services.length; ++i) {
                this.services[i].setProto(proto.getService(i));
            }
            for (i = 0; i < this.extensions.length; ++i) {
                this.extensions[i].setProto(proto.getExtension(i));
            }
        }

        public static interface InternalDescriptorAssigner {
            public ExtensionRegistry assignDescriptors(FileDescriptor var1);
        }
    }
}

