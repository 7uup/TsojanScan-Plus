/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.protobuf;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.LazyStringArrayList;
import com.google.protobuf.LazyStringList;
import com.google.protobuf.Message;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.ProtocolMessageEnum;
import com.google.protobuf.ProtocolStringList;
import com.google.protobuf.RepeatedFieldBuilder;
import com.google.protobuf.SingleFieldBuilder;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectStreamException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class DescriptorProtos {
    private static final Descriptors.Descriptor internal_static_google_protobuf_FileDescriptorSet_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_google_protobuf_FileDescriptorSet_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_google_protobuf_FileDescriptorProto_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_google_protobuf_FileDescriptorProto_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_google_protobuf_DescriptorProto_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_google_protobuf_DescriptorProto_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_google_protobuf_DescriptorProto_ExtensionRange_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_google_protobuf_DescriptorProto_ExtensionRange_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_google_protobuf_FieldDescriptorProto_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_google_protobuf_FieldDescriptorProto_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_google_protobuf_OneofDescriptorProto_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_google_protobuf_OneofDescriptorProto_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_google_protobuf_EnumDescriptorProto_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_google_protobuf_EnumDescriptorProto_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_google_protobuf_EnumValueDescriptorProto_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_google_protobuf_EnumValueDescriptorProto_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_google_protobuf_ServiceDescriptorProto_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_google_protobuf_ServiceDescriptorProto_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_google_protobuf_MethodDescriptorProto_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_google_protobuf_MethodDescriptorProto_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_google_protobuf_FileOptions_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_google_protobuf_FileOptions_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_google_protobuf_MessageOptions_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_google_protobuf_MessageOptions_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_google_protobuf_FieldOptions_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_google_protobuf_FieldOptions_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_google_protobuf_EnumOptions_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_google_protobuf_EnumOptions_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_google_protobuf_EnumValueOptions_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_google_protobuf_EnumValueOptions_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_google_protobuf_ServiceOptions_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_google_protobuf_ServiceOptions_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_google_protobuf_MethodOptions_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_google_protobuf_MethodOptions_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_google_protobuf_UninterpretedOption_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_google_protobuf_UninterpretedOption_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_google_protobuf_UninterpretedOption_NamePart_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_google_protobuf_UninterpretedOption_NamePart_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_google_protobuf_SourceCodeInfo_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_google_protobuf_SourceCodeInfo_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_google_protobuf_SourceCodeInfo_Location_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_google_protobuf_SourceCodeInfo_Location_fieldAccessorTable;
    private static Descriptors.FileDescriptor descriptor;

    private DescriptorProtos() {
    }

    public static void registerAllExtensions(ExtensionRegistry registry) {
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        String[] descriptorData = new String[]{"\n google/protobuf/descriptor.proto\u0012\u000fgoogle.protobuf\"G\n\u0011FileDescriptorSet\u00122\n\u0004file\u0018\u0001 \u0003(\u000b2$.google.protobuf.FileDescriptorProto\"\u00cb\u0003\n\u0013FileDescriptorProto\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\u0012\u000f\n\u0007package\u0018\u0002 \u0001(\t\u0012\u0012\n\ndependency\u0018\u0003 \u0003(\t\u0012\u0019\n\u0011public_dependency\u0018\n \u0003(\u0005\u0012\u0017\n\u000fweak_dependency\u0018\u000b \u0003(\u0005\u00126\n\fmessage_type\u0018\u0004 \u0003(\u000b2 .google.protobuf.DescriptorProto\u00127\n\tenum_type\u0018\u0005 \u0003(\u000b2$.google.protobuf.EnumDescriptorProto\u00128\n\u0007service\u0018\u0006 \u0003(\u000b2'.google.protobuf.", "ServiceDescriptorProto\u00128\n\textension\u0018\u0007 \u0003(\u000b2%.google.protobuf.FieldDescriptorProto\u0012-\n\u0007options\u0018\b \u0001(\u000b2\u001c.google.protobuf.FileOptions\u00129\n\u0010source_code_info\u0018\t \u0001(\u000b2\u001f.google.protobuf.SourceCodeInfo\"\u00e4\u0003\n\u000fDescriptorProto\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\u00124\n\u0005field\u0018\u0002 \u0003(\u000b2%.google.protobuf.FieldDescriptorProto\u00128\n\textension\u0018\u0006 \u0003(\u000b2%.google.protobuf.FieldDescriptorProto\u00125\n\u000bnested_type\u0018\u0003 \u0003(\u000b2 .google.protobuf.DescriptorProto\u00127\n\tenum_type", "\u0018\u0004 \u0003(\u000b2$.google.protobuf.EnumDescriptorProto\u0012H\n\u000fextension_range\u0018\u0005 \u0003(\u000b2/.google.protobuf.DescriptorProto.ExtensionRange\u00129\n\noneof_decl\u0018\b \u0003(\u000b2%.google.protobuf.OneofDescriptorProto\u00120\n\u0007options\u0018\u0007 \u0001(\u000b2\u001f.google.protobuf.MessageOptions\u001a,\n\u000eExtensionRange\u0012\r\n\u0005start\u0018\u0001 \u0001(\u0005\u0012\u000b\n\u0003end\u0018\u0002 \u0001(\u0005\"\u00a9\u0005\n\u0014FieldDescriptorProto\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\u0012\u000e\n\u0006number\u0018\u0003 \u0001(\u0005\u0012:\n\u0005label\u0018\u0004 \u0001(\u000e2+.google.protobuf.FieldDescriptorProto.Label\u00128\n\u0004type\u0018\u0005 \u0001", "(\u000e2*.google.protobuf.FieldDescriptorProto.Type\u0012\u0011\n\ttype_name\u0018\u0006 \u0001(\t\u0012\u0010\n\bextendee\u0018\u0002 \u0001(\t\u0012\u0015\n\rdefault_value\u0018\u0007 \u0001(\t\u0012\u0013\n\u000boneof_index\u0018\t \u0001(\u0005\u0012.\n\u0007options\u0018\b \u0001(\u000b2\u001d.google.protobuf.FieldOptions\"\u00b6\u0002\n\u0004Type\u0012\u000f\n\u000bTYPE_DOUBLE\u0010\u0001\u0012\u000e\n\nTYPE_FLOAT\u0010\u0002\u0012\u000e\n\nTYPE_INT64\u0010\u0003\u0012\u000f\n\u000bTYPE_UINT64\u0010\u0004\u0012\u000e\n\nTYPE_INT32\u0010\u0005\u0012\u0010\n\fTYPE_FIXED64\u0010\u0006\u0012\u0010\n\fTYPE_FIXED32\u0010\u0007\u0012\r\n\tTYPE_BOOL\u0010\b\u0012\u000f\n\u000bTYPE_STRING\u0010\t\u0012\u000e\n\nTYPE_GROUP\u0010\n\u0012\u0010\n\fTYPE_MESSAGE\u0010\u000b\u0012\u000e\n\nTYPE_BYTES\u0010\f\u0012\u000f\n\u000bTYPE_UINT32\u0010", "\r\u0012\r\n\tTYPE_ENUM\u0010\u000e\u0012\u0011\n\rTYPE_SFIXED32\u0010\u000f\u0012\u0011\n\rTYPE_SFIXED64\u0010\u0010\u0012\u000f\n\u000bTYPE_SINT32\u0010\u0011\u0012\u000f\n\u000bTYPE_SINT64\u0010\u0012\"C\n\u0005Label\u0012\u0012\n\u000eLABEL_OPTIONAL\u0010\u0001\u0012\u0012\n\u000eLABEL_REQUIRED\u0010\u0002\u0012\u0012\n\u000eLABEL_REPEATED\u0010\u0003\"$\n\u0014OneofDescriptorProto\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\"\u008c\u0001\n\u0013EnumDescriptorProto\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\u00128\n\u0005value\u0018\u0002 \u0003(\u000b2).google.protobuf.EnumValueDescriptorProto\u0012-\n\u0007options\u0018\u0003 \u0001(\u000b2\u001c.google.protobuf.EnumOptions\"l\n\u0018EnumValueDescriptorProto\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\u0012\u000e\n\u0006number\u0018\u0002 \u0001(\u0005\u00122\n\u0007", "options\u0018\u0003 \u0001(\u000b2!.google.protobuf.EnumValueOptions\"\u0090\u0001\n\u0016ServiceDescriptorProto\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\u00126\n\u0006method\u0018\u0002 \u0003(\u000b2&.google.protobuf.MethodDescriptorProto\u00120\n\u0007options\u0018\u0003 \u0001(\u000b2\u001f.google.protobuf.ServiceOptions\"\u007f\n\u0015MethodDescriptorProto\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\u0012\u0012\n\ninput_type\u0018\u0002 \u0001(\t\u0012\u0013\n\u000boutput_type\u0018\u0003 \u0001(\t\u0012/\n\u0007options\u0018\u0004 \u0001(\u000b2\u001e.google.protobuf.MethodOptions\"\u00ab\u0004\n\u000bFileOptions\u0012\u0014\n\fjava_package\u0018\u0001 \u0001(\t\u0012\u001c\n\u0014java_outer_classname\u0018\b \u0001(\t\u0012\"\n\u0013java", "_multiple_files\u0018\n \u0001(\b:\u0005false\u0012,\n\u001djava_generate_equals_and_hash\u0018\u0014 \u0001(\b:\u0005false\u0012%\n\u0016java_string_check_utf8\u0018\u001b \u0001(\b:\u0005false\u0012F\n\foptimize_for\u0018\t \u0001(\u000e2).google.protobuf.FileOptions.OptimizeMode:\u0005SPEED\u0012\u0012\n\ngo_package\u0018\u000b \u0001(\t\u0012\"\n\u0013cc_generic_services\u0018\u0010 \u0001(\b:\u0005false\u0012$\n\u0015java_generic_services\u0018\u0011 \u0001(\b:\u0005false\u0012\"\n\u0013py_generic_services\u0018\u0012 \u0001(\b:\u0005false\u0012\u0019\n\ndeprecated\u0018\u0017 \u0001(\b:\u0005false\u0012C\n\u0014uninterpreted_option\u0018\u00e7\u0007 \u0003(\u000b2$.google.protobuf.Uninterp", "retedOption\":\n\fOptimizeMode\u0012\t\n\u0005SPEED\u0010\u0001\u0012\r\n\tCODE_SIZE\u0010\u0002\u0012\u0010\n\fLITE_RUNTIME\u0010\u0003*\t\b\u00e8\u0007\u0010\u0080\u0080\u0080\u0080\u0002\"\u00d3\u0001\n\u000eMessageOptions\u0012&\n\u0017message_set_wire_format\u0018\u0001 \u0001(\b:\u0005false\u0012.\n\u001fno_standard_descriptor_accessor\u0018\u0002 \u0001(\b:\u0005false\u0012\u0019\n\ndeprecated\u0018\u0003 \u0001(\b:\u0005false\u0012C\n\u0014uninterpreted_option\u0018\u00e7\u0007 \u0003(\u000b2$.google.protobuf.UninterpretedOption*\t\b\u00e8\u0007\u0010\u0080\u0080\u0080\u0080\u0002\"\u00be\u0002\n\fFieldOptions\u0012:\n\u0005ctype\u0018\u0001 \u0001(\u000e2#.google.protobuf.FieldOptions.CType:\u0006STRING\u0012\u000e\n\u0006packed\u0018\u0002 \u0001(\b\u0012\u0013\n\u0004lazy\u0018\u0005 ", "\u0001(\b:\u0005false\u0012\u0019\n\ndeprecated\u0018\u0003 \u0001(\b:\u0005false\u0012\u001c\n\u0014experimental_map_key\u0018\t \u0001(\t\u0012\u0013\n\u0004weak\u0018\n \u0001(\b:\u0005false\u0012C\n\u0014uninterpreted_option\u0018\u00e7\u0007 \u0003(\u000b2$.google.protobuf.UninterpretedOption\"/\n\u0005CType\u0012\n\n\u0006STRING\u0010\u0000\u0012\b\n\u0004CORD\u0010\u0001\u0012\u0010\n\fSTRING_PIECE\u0010\u0002*\t\b\u00e8\u0007\u0010\u0080\u0080\u0080\u0080\u0002\"\u008d\u0001\n\u000bEnumOptions\u0012\u0013\n\u000ballow_alias\u0018\u0002 \u0001(\b\u0012\u0019\n\ndeprecated\u0018\u0003 \u0001(\b:\u0005false\u0012C\n\u0014uninterpreted_option\u0018\u00e7\u0007 \u0003(\u000b2$.google.protobuf.UninterpretedOption*\t\b\u00e8\u0007\u0010\u0080\u0080\u0080\u0080\u0002\"}\n\u0010EnumValueOptions\u0012\u0019\n\ndeprecated\u0018\u0001 \u0001(", "\b:\u0005false\u0012C\n\u0014uninterpreted_option\u0018\u00e7\u0007 \u0003(\u000b2$.google.protobuf.UninterpretedOption*\t\b\u00e8\u0007\u0010\u0080\u0080\u0080\u0080\u0002\"{\n\u000eServiceOptions\u0012\u0019\n\ndeprecated\u0018! \u0001(\b:\u0005false\u0012C\n\u0014uninterpreted_option\u0018\u00e7\u0007 \u0003(\u000b2$.google.protobuf.UninterpretedOption*\t\b\u00e8\u0007\u0010\u0080\u0080\u0080\u0080\u0002\"z\n\rMethodOptions\u0012\u0019\n\ndeprecated\u0018! \u0001(\b:\u0005false\u0012C\n\u0014uninterpreted_option\u0018\u00e7\u0007 \u0003(\u000b2$.google.protobuf.UninterpretedOption*\t\b\u00e8\u0007\u0010\u0080\u0080\u0080\u0080\u0002\"\u009e\u0002\n\u0013UninterpretedOption\u0012;\n\u0004name\u0018\u0002 \u0003(\u000b2-.google.protobuf.Uninte", "rpretedOption.NamePart\u0012\u0018\n\u0010identifier_value\u0018\u0003 \u0001(\t\u0012\u001a\n\u0012positive_int_value\u0018\u0004 \u0001(\u0004\u0012\u001a\n\u0012negative_int_value\u0018\u0005 \u0001(\u0003\u0012\u0014\n\fdouble_value\u0018\u0006 \u0001(\u0001\u0012\u0014\n\fstring_value\u0018\u0007 \u0001(\f\u0012\u0017\n\u000faggregate_value\u0018\b \u0001(\t\u001a3\n\bNamePart\u0012\u0011\n\tname_part\u0018\u0001 \u0002(\t\u0012\u0014\n\fis_extension\u0018\u0002 \u0002(\b\"\u00b1\u0001\n\u000eSourceCodeInfo\u0012:\n\blocation\u0018\u0001 \u0003(\u000b2(.google.protobuf.SourceCodeInfo.Location\u001ac\n\bLocation\u0012\u0010\n\u0004path\u0018\u0001 \u0003(\u0005B\u0002\u0010\u0001\u0012\u0010\n\u0004span\u0018\u0002 \u0003(\u0005B\u0002\u0010\u0001\u0012\u0018\n\u0010leading_comments\u0018\u0003 \u0001(\t\u0012\u0019\n\u0011trailing_comments", "\u0018\u0004 \u0001(\tB)\n\u0013com.google.protobufB\u0010DescriptorProtosH\u0001"};
        Descriptors.FileDescriptor.InternalDescriptorAssigner assigner = new Descriptors.FileDescriptor.InternalDescriptorAssigner(){

            public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor root) {
                descriptor = root;
                return null;
            }
        };
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[0], assigner);
        internal_static_google_protobuf_FileDescriptorSet_descriptor = DescriptorProtos.getDescriptor().getMessageTypes().get(0);
        internal_static_google_protobuf_FileDescriptorSet_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_google_protobuf_FileDescriptorSet_descriptor, new String[]{"File"});
        internal_static_google_protobuf_FileDescriptorProto_descriptor = DescriptorProtos.getDescriptor().getMessageTypes().get(1);
        internal_static_google_protobuf_FileDescriptorProto_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_google_protobuf_FileDescriptorProto_descriptor, new String[]{"Name", "Package", "Dependency", "PublicDependency", "WeakDependency", "MessageType", "EnumType", "Service", "Extension", "Options", "SourceCodeInfo"});
        internal_static_google_protobuf_DescriptorProto_descriptor = DescriptorProtos.getDescriptor().getMessageTypes().get(2);
        internal_static_google_protobuf_DescriptorProto_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_google_protobuf_DescriptorProto_descriptor, new String[]{"Name", "Field", "Extension", "NestedType", "EnumType", "ExtensionRange", "OneofDecl", "Options"});
        internal_static_google_protobuf_DescriptorProto_ExtensionRange_descriptor = internal_static_google_protobuf_DescriptorProto_descriptor.getNestedTypes().get(0);
        internal_static_google_protobuf_DescriptorProto_ExtensionRange_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_google_protobuf_DescriptorProto_ExtensionRange_descriptor, new String[]{"Start", "End"});
        internal_static_google_protobuf_FieldDescriptorProto_descriptor = DescriptorProtos.getDescriptor().getMessageTypes().get(3);
        internal_static_google_protobuf_FieldDescriptorProto_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_google_protobuf_FieldDescriptorProto_descriptor, new String[]{"Name", "Number", "Label", "Type", "TypeName", "Extendee", "DefaultValue", "OneofIndex", "Options"});
        internal_static_google_protobuf_OneofDescriptorProto_descriptor = DescriptorProtos.getDescriptor().getMessageTypes().get(4);
        internal_static_google_protobuf_OneofDescriptorProto_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_google_protobuf_OneofDescriptorProto_descriptor, new String[]{"Name"});
        internal_static_google_protobuf_EnumDescriptorProto_descriptor = DescriptorProtos.getDescriptor().getMessageTypes().get(5);
        internal_static_google_protobuf_EnumDescriptorProto_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_google_protobuf_EnumDescriptorProto_descriptor, new String[]{"Name", "Value", "Options"});
        internal_static_google_protobuf_EnumValueDescriptorProto_descriptor = DescriptorProtos.getDescriptor().getMessageTypes().get(6);
        internal_static_google_protobuf_EnumValueDescriptorProto_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_google_protobuf_EnumValueDescriptorProto_descriptor, new String[]{"Name", "Number", "Options"});
        internal_static_google_protobuf_ServiceDescriptorProto_descriptor = DescriptorProtos.getDescriptor().getMessageTypes().get(7);
        internal_static_google_protobuf_ServiceDescriptorProto_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_google_protobuf_ServiceDescriptorProto_descriptor, new String[]{"Name", "Method", "Options"});
        internal_static_google_protobuf_MethodDescriptorProto_descriptor = DescriptorProtos.getDescriptor().getMessageTypes().get(8);
        internal_static_google_protobuf_MethodDescriptorProto_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_google_protobuf_MethodDescriptorProto_descriptor, new String[]{"Name", "InputType", "OutputType", "Options"});
        internal_static_google_protobuf_FileOptions_descriptor = DescriptorProtos.getDescriptor().getMessageTypes().get(9);
        internal_static_google_protobuf_FileOptions_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_google_protobuf_FileOptions_descriptor, new String[]{"JavaPackage", "JavaOuterClassname", "JavaMultipleFiles", "JavaGenerateEqualsAndHash", "JavaStringCheckUtf8", "OptimizeFor", "GoPackage", "CcGenericServices", "JavaGenericServices", "PyGenericServices", "Deprecated", "UninterpretedOption"});
        internal_static_google_protobuf_MessageOptions_descriptor = DescriptorProtos.getDescriptor().getMessageTypes().get(10);
        internal_static_google_protobuf_MessageOptions_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_google_protobuf_MessageOptions_descriptor, new String[]{"MessageSetWireFormat", "NoStandardDescriptorAccessor", "Deprecated", "UninterpretedOption"});
        internal_static_google_protobuf_FieldOptions_descriptor = DescriptorProtos.getDescriptor().getMessageTypes().get(11);
        internal_static_google_protobuf_FieldOptions_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_google_protobuf_FieldOptions_descriptor, new String[]{"Ctype", "Packed", "Lazy", "Deprecated", "ExperimentalMapKey", "Weak", "UninterpretedOption"});
        internal_static_google_protobuf_EnumOptions_descriptor = DescriptorProtos.getDescriptor().getMessageTypes().get(12);
        internal_static_google_protobuf_EnumOptions_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_google_protobuf_EnumOptions_descriptor, new String[]{"AllowAlias", "Deprecated", "UninterpretedOption"});
        internal_static_google_protobuf_EnumValueOptions_descriptor = DescriptorProtos.getDescriptor().getMessageTypes().get(13);
        internal_static_google_protobuf_EnumValueOptions_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_google_protobuf_EnumValueOptions_descriptor, new String[]{"Deprecated", "UninterpretedOption"});
        internal_static_google_protobuf_ServiceOptions_descriptor = DescriptorProtos.getDescriptor().getMessageTypes().get(14);
        internal_static_google_protobuf_ServiceOptions_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_google_protobuf_ServiceOptions_descriptor, new String[]{"Deprecated", "UninterpretedOption"});
        internal_static_google_protobuf_MethodOptions_descriptor = DescriptorProtos.getDescriptor().getMessageTypes().get(15);
        internal_static_google_protobuf_MethodOptions_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_google_protobuf_MethodOptions_descriptor, new String[]{"Deprecated", "UninterpretedOption"});
        internal_static_google_protobuf_UninterpretedOption_descriptor = DescriptorProtos.getDescriptor().getMessageTypes().get(16);
        internal_static_google_protobuf_UninterpretedOption_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_google_protobuf_UninterpretedOption_descriptor, new String[]{"Name", "IdentifierValue", "PositiveIntValue", "NegativeIntValue", "DoubleValue", "StringValue", "AggregateValue"});
        internal_static_google_protobuf_UninterpretedOption_NamePart_descriptor = internal_static_google_protobuf_UninterpretedOption_descriptor.getNestedTypes().get(0);
        internal_static_google_protobuf_UninterpretedOption_NamePart_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_google_protobuf_UninterpretedOption_NamePart_descriptor, new String[]{"NamePart", "IsExtension"});
        internal_static_google_protobuf_SourceCodeInfo_descriptor = DescriptorProtos.getDescriptor().getMessageTypes().get(17);
        internal_static_google_protobuf_SourceCodeInfo_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_google_protobuf_SourceCodeInfo_descriptor, new String[]{"Location"});
        internal_static_google_protobuf_SourceCodeInfo_Location_descriptor = internal_static_google_protobuf_SourceCodeInfo_descriptor.getNestedTypes().get(0);
        internal_static_google_protobuf_SourceCodeInfo_Location_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(internal_static_google_protobuf_SourceCodeInfo_Location_descriptor, new String[]{"Path", "Span", "LeadingComments", "TrailingComments"});
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static final class SourceCodeInfo
    extends GeneratedMessage
    implements SourceCodeInfoOrBuilder {
        private static final SourceCodeInfo defaultInstance;
        private final UnknownFieldSet unknownFields;
        public static Parser<SourceCodeInfo> PARSER;
        public static final int LOCATION_FIELD_NUMBER = 1;
        private List<Location> location_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private static final long serialVersionUID = 0L;

        private SourceCodeInfo(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte)-1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private SourceCodeInfo(boolean noInit) {
            this.memoizedIsInitialized = (byte)-1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static SourceCodeInfo getDefaultInstance() {
            return defaultInstance;
        }

        @Override
        public SourceCodeInfo getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        private SourceCodeInfo(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            UnknownFieldSet.Builder unknownFields;
            block13: {
                this.memoizedIsInitialized = (byte)-1;
                this.memoizedSerializedSize = -1;
                this.initFields();
                boolean mutable_bitField0_ = false;
                unknownFields = UnknownFieldSet.newBuilder();
                try {
                    try {
                        boolean done = false;
                        block9: while (!done) {
                            int tag = input.readTag();
                            switch (tag) {
                                case 0: {
                                    done = true;
                                    continue block9;
                                }
                                default: {
                                    if (this.parseUnknownField(input, unknownFields, extensionRegistry, tag)) continue block9;
                                    done = true;
                                    continue block9;
                                }
                                case 10: 
                            }
                            if (!(mutable_bitField0_ & true)) {
                                this.location_ = new ArrayList<Location>();
                                mutable_bitField0_ |= true;
                            }
                            this.location_.add(input.readMessage(Location.PARSER, extensionRegistry));
                        }
                        Object var8_9 = null;
                        if (!(mutable_bitField0_ & true)) break block13;
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e) {
                        throw new InvalidProtocolBufferException(e.getMessage()).setUnfinishedMessage(this);
                    }
                } catch (Throwable throwable) {
                    Object var8_10 = null;
                    if (mutable_bitField0_ & true) {
                        this.location_ = Collections.unmodifiableList(this.location_);
                    }
                    this.unknownFields = unknownFields.build();
                    this.makeExtensionsImmutable();
                    throw throwable;
                }
                this.location_ = Collections.unmodifiableList(this.location_);
            }
            this.unknownFields = unknownFields.build();
            this.makeExtensionsImmutable();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return internal_static_google_protobuf_SourceCodeInfo_descriptor;
        }

        @Override
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return internal_static_google_protobuf_SourceCodeInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(SourceCodeInfo.class, Builder.class);
        }

        public Parser<SourceCodeInfo> getParserForType() {
            return PARSER;
        }

        @Override
        public List<Location> getLocationList() {
            return this.location_;
        }

        @Override
        public List<? extends LocationOrBuilder> getLocationOrBuilderList() {
            return this.location_;
        }

        @Override
        public int getLocationCount() {
            return this.location_.size();
        }

        @Override
        public Location getLocation(int index) {
            return this.location_.get(index);
        }

        @Override
        public LocationOrBuilder getLocationOrBuilder(int index) {
            return this.location_.get(index);
        }

        private void initFields() {
            this.location_ = Collections.emptyList();
        }

        @Override
        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        @Override
        public void writeTo(CodedOutputStream output) throws IOException {
            this.getSerializedSize();
            for (int i = 0; i < this.location_.size(); ++i) {
                output.writeMessage(1, this.location_.get(i));
            }
            this.getUnknownFields().writeTo(output);
        }

        @Override
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            size = 0;
            for (int i = 0; i < this.location_.size(); ++i) {
                size += CodedOutputStream.computeMessageSize(1, this.location_.get(i));
            }
            this.memoizedSerializedSize = size += this.getUnknownFields().getSerializedSize();
            return size;
        }

        @Override
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static SourceCodeInfo parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static SourceCodeInfo parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static SourceCodeInfo parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static SourceCodeInfo parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static SourceCodeInfo parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static SourceCodeInfo parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static SourceCodeInfo parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static SourceCodeInfo parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static SourceCodeInfo parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static SourceCodeInfo parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override
        public Builder newBuilderForType() {
            return SourceCodeInfo.newBuilder();
        }

        public static Builder newBuilder(SourceCodeInfo prototype) {
            return SourceCodeInfo.newBuilder().mergeFrom(prototype);
        }

        @Override
        public Builder toBuilder() {
            return SourceCodeInfo.newBuilder(this);
        }

        @Override
        protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        static {
            PARSER = new AbstractParser<SourceCodeInfo>(){

                @Override
                public SourceCodeInfo parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                    return new SourceCodeInfo(input, extensionRegistry);
                }
            };
            defaultInstance = new SourceCodeInfo(true);
            defaultInstance.initFields();
        }

        /*
         * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
         */
        public static final class Builder
        extends GeneratedMessage.Builder<Builder>
        implements SourceCodeInfoOrBuilder {
            private int bitField0_;
            private List<Location> location_ = Collections.emptyList();
            private RepeatedFieldBuilder<Location, Location.Builder, LocationOrBuilder> locationBuilder_;

            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_google_protobuf_SourceCodeInfo_descriptor;
            }

            @Override
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_google_protobuf_SourceCodeInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(SourceCodeInfo.class, Builder.class);
            }

            private Builder() {
                this.maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessage.BuilderParent parent) {
                super(parent);
                this.maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                if (GeneratedMessage.alwaysUseFieldBuilders) {
                    this.getLocationFieldBuilder();
                }
            }

            private static Builder create() {
                return new Builder();
            }

            @Override
            public Builder clear() {
                super.clear();
                if (this.locationBuilder_ == null) {
                    this.location_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFFFFE;
                } else {
                    this.locationBuilder_.clear();
                }
                return this;
            }

            @Override
            public Builder clone() {
                return Builder.create().mergeFrom(this.buildPartial());
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return internal_static_google_protobuf_SourceCodeInfo_descriptor;
            }

            @Override
            public SourceCodeInfo getDefaultInstanceForType() {
                return SourceCodeInfo.getDefaultInstance();
            }

            @Override
            public SourceCodeInfo build() {
                SourceCodeInfo result = this.buildPartial();
                if (!result.isInitialized()) {
                    throw Builder.newUninitializedMessageException(result);
                }
                return result;
            }

            @Override
            public SourceCodeInfo buildPartial() {
                SourceCodeInfo result = new SourceCodeInfo(this);
                int from_bitField0_ = this.bitField0_;
                if (this.locationBuilder_ == null) {
                    if ((this.bitField0_ & 1) == 1) {
                        this.location_ = Collections.unmodifiableList(this.location_);
                        this.bitField0_ &= 0xFFFFFFFE;
                    }
                    result.location_ = this.location_;
                } else {
                    result.location_ = this.locationBuilder_.build();
                }
                this.onBuilt();
                return result;
            }

            @Override
            public Builder mergeFrom(Message other) {
                if (other instanceof SourceCodeInfo) {
                    return this.mergeFrom((SourceCodeInfo)other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(SourceCodeInfo other) {
                if (other == SourceCodeInfo.getDefaultInstance()) {
                    return this;
                }
                if (this.locationBuilder_ == null) {
                    if (!other.location_.isEmpty()) {
                        if (this.location_.isEmpty()) {
                            this.location_ = other.location_;
                            this.bitField0_ &= 0xFFFFFFFE;
                        } else {
                            this.ensureLocationIsMutable();
                            this.location_.addAll(other.location_);
                        }
                        this.onChanged();
                    }
                } else if (!other.location_.isEmpty()) {
                    if (this.locationBuilder_.isEmpty()) {
                        this.locationBuilder_.dispose();
                        this.locationBuilder_ = null;
                        this.location_ = other.location_;
                        this.bitField0_ &= 0xFFFFFFFE;
                        this.locationBuilder_ = GeneratedMessage.alwaysUseFieldBuilders ? this.getLocationFieldBuilder() : null;
                    } else {
                        this.locationBuilder_.addAllMessages(other.location_);
                    }
                }
                this.mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            @Override
            public final boolean isInitialized() {
                return true;
            }

            /*
             * Enabled force condition propagation
             * Lifted jumps to return sites
             */
            @Override
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                SourceCodeInfo parsedMessage = null;
                try {
                    try {
                        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
                    } catch (InvalidProtocolBufferException e) {
                        parsedMessage = (SourceCodeInfo)e.getUnfinishedMessage();
                        throw e;
                    }
                    Object var6_4 = null;
                    if (parsedMessage == null) return this;
                    this.mergeFrom(parsedMessage);
                    return this;
                } catch (Throwable throwable) {
                    Object var6_5 = null;
                    if (parsedMessage == null) throw throwable;
                    this.mergeFrom(parsedMessage);
                    throw throwable;
                }
            }

            private void ensureLocationIsMutable() {
                if ((this.bitField0_ & 1) != 1) {
                    this.location_ = new ArrayList<Location>(this.location_);
                    this.bitField0_ |= 1;
                }
            }

            @Override
            public List<Location> getLocationList() {
                if (this.locationBuilder_ == null) {
                    return Collections.unmodifiableList(this.location_);
                }
                return this.locationBuilder_.getMessageList();
            }

            @Override
            public int getLocationCount() {
                if (this.locationBuilder_ == null) {
                    return this.location_.size();
                }
                return this.locationBuilder_.getCount();
            }

            @Override
            public Location getLocation(int index) {
                if (this.locationBuilder_ == null) {
                    return this.location_.get(index);
                }
                return this.locationBuilder_.getMessage(index);
            }

            public Builder setLocation(int index, Location value) {
                if (this.locationBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureLocationIsMutable();
                    this.location_.set(index, value);
                    this.onChanged();
                } else {
                    this.locationBuilder_.setMessage(index, value);
                }
                return this;
            }

            public Builder setLocation(int index, Location.Builder builderForValue) {
                if (this.locationBuilder_ == null) {
                    this.ensureLocationIsMutable();
                    this.location_.set(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.locationBuilder_.setMessage(index, builderForValue.build());
                }
                return this;
            }

            public Builder addLocation(Location value) {
                if (this.locationBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureLocationIsMutable();
                    this.location_.add(value);
                    this.onChanged();
                } else {
                    this.locationBuilder_.addMessage(value);
                }
                return this;
            }

            public Builder addLocation(int index, Location value) {
                if (this.locationBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureLocationIsMutable();
                    this.location_.add(index, value);
                    this.onChanged();
                } else {
                    this.locationBuilder_.addMessage(index, value);
                }
                return this;
            }

            public Builder addLocation(Location.Builder builderForValue) {
                if (this.locationBuilder_ == null) {
                    this.ensureLocationIsMutable();
                    this.location_.add(builderForValue.build());
                    this.onChanged();
                } else {
                    this.locationBuilder_.addMessage(builderForValue.build());
                }
                return this;
            }

            public Builder addLocation(int index, Location.Builder builderForValue) {
                if (this.locationBuilder_ == null) {
                    this.ensureLocationIsMutable();
                    this.location_.add(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.locationBuilder_.addMessage(index, builderForValue.build());
                }
                return this;
            }

            public Builder addAllLocation(Iterable<? extends Location> values2) {
                if (this.locationBuilder_ == null) {
                    this.ensureLocationIsMutable();
                    AbstractMessageLite.Builder.addAll(values2, this.location_);
                    this.onChanged();
                } else {
                    this.locationBuilder_.addAllMessages(values2);
                }
                return this;
            }

            public Builder clearLocation() {
                if (this.locationBuilder_ == null) {
                    this.location_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFFFFE;
                    this.onChanged();
                } else {
                    this.locationBuilder_.clear();
                }
                return this;
            }

            public Builder removeLocation(int index) {
                if (this.locationBuilder_ == null) {
                    this.ensureLocationIsMutable();
                    this.location_.remove(index);
                    this.onChanged();
                } else {
                    this.locationBuilder_.remove(index);
                }
                return this;
            }

            public Location.Builder getLocationBuilder(int index) {
                return this.getLocationFieldBuilder().getBuilder(index);
            }

            @Override
            public LocationOrBuilder getLocationOrBuilder(int index) {
                if (this.locationBuilder_ == null) {
                    return this.location_.get(index);
                }
                return this.locationBuilder_.getMessageOrBuilder(index);
            }

            @Override
            public List<? extends LocationOrBuilder> getLocationOrBuilderList() {
                if (this.locationBuilder_ != null) {
                    return this.locationBuilder_.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.location_);
            }

            public Location.Builder addLocationBuilder() {
                return this.getLocationFieldBuilder().addBuilder(Location.getDefaultInstance());
            }

            public Location.Builder addLocationBuilder(int index) {
                return this.getLocationFieldBuilder().addBuilder(index, Location.getDefaultInstance());
            }

            public List<Location.Builder> getLocationBuilderList() {
                return this.getLocationFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilder<Location, Location.Builder, LocationOrBuilder> getLocationFieldBuilder() {
                if (this.locationBuilder_ == null) {
                    this.locationBuilder_ = new RepeatedFieldBuilder(this.location_, (this.bitField0_ & 1) == 1, this.getParentForChildren(), this.isClean());
                    this.location_ = null;
                }
                return this.locationBuilder_;
            }
        }

        /*
         * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
         */
        public static final class Location
        extends GeneratedMessage
        implements LocationOrBuilder {
            private static final Location defaultInstance;
            private final UnknownFieldSet unknownFields;
            public static Parser<Location> PARSER;
            private int bitField0_;
            public static final int PATH_FIELD_NUMBER = 1;
            private List<Integer> path_;
            private int pathMemoizedSerializedSize;
            public static final int SPAN_FIELD_NUMBER = 2;
            private List<Integer> span_;
            private int spanMemoizedSerializedSize;
            public static final int LEADING_COMMENTS_FIELD_NUMBER = 3;
            private Object leadingComments_;
            public static final int TRAILING_COMMENTS_FIELD_NUMBER = 4;
            private Object trailingComments_;
            private byte memoizedIsInitialized;
            private int memoizedSerializedSize;
            private static final long serialVersionUID = 0L;

            private Location(GeneratedMessage.Builder<?> builder) {
                super(builder);
                this.pathMemoizedSerializedSize = -1;
                this.spanMemoizedSerializedSize = -1;
                this.memoizedIsInitialized = (byte)-1;
                this.memoizedSerializedSize = -1;
                this.unknownFields = builder.getUnknownFields();
            }

            private Location(boolean noInit) {
                this.pathMemoizedSerializedSize = -1;
                this.spanMemoizedSerializedSize = -1;
                this.memoizedIsInitialized = (byte)-1;
                this.memoizedSerializedSize = -1;
                this.unknownFields = UnknownFieldSet.getDefaultInstance();
            }

            public static Location getDefaultInstance() {
                return defaultInstance;
            }

            @Override
            public Location getDefaultInstanceForType() {
                return defaultInstance;
            }

            @Override
            public final UnknownFieldSet getUnknownFields() {
                return this.unknownFields;
            }

            /*
             * Enabled aggressive block sorting
             * Enabled unnecessary exception pruning
             * Enabled aggressive exception aggregation
             */
            private Location(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                UnknownFieldSet.Builder unknownFields;
                int mutable_bitField0_;
                block25: {
                    this.pathMemoizedSerializedSize = -1;
                    this.spanMemoizedSerializedSize = -1;
                    this.memoizedIsInitialized = (byte)-1;
                    this.memoizedSerializedSize = -1;
                    this.initFields();
                    mutable_bitField0_ = 0;
                    unknownFields = UnknownFieldSet.newBuilder();
                    try {
                        try {
                            boolean done = false;
                            block14: while (!done) {
                                int tag = input.readTag();
                                switch (tag) {
                                    case 0: {
                                        done = true;
                                        continue block14;
                                    }
                                    default: {
                                        if (this.parseUnknownField(input, unknownFields, extensionRegistry, tag)) continue block14;
                                        done = true;
                                        continue block14;
                                    }
                                    case 8: {
                                        if (!(mutable_bitField0_ & true)) {
                                            this.path_ = new ArrayList<Integer>();
                                            mutable_bitField0_ |= 1;
                                        }
                                        this.path_.add(input.readInt32());
                                        continue block14;
                                    }
                                    case 10: {
                                        int length = input.readRawVarint32();
                                        int limit = input.pushLimit(length);
                                        if ((mutable_bitField0_ & 1) != 1 && input.getBytesUntilLimit() > 0) {
                                            this.path_ = new ArrayList<Integer>();
                                            mutable_bitField0_ |= 1;
                                        }
                                        while (input.getBytesUntilLimit() > 0) {
                                            this.path_.add(input.readInt32());
                                        }
                                        input.popLimit(limit);
                                        continue block14;
                                    }
                                    case 16: {
                                        if ((mutable_bitField0_ & 2) != 2) {
                                            this.span_ = new ArrayList<Integer>();
                                            mutable_bitField0_ |= 2;
                                        }
                                        this.span_.add(input.readInt32());
                                        continue block14;
                                    }
                                    case 18: {
                                        int length = input.readRawVarint32();
                                        int limit = input.pushLimit(length);
                                        if ((mutable_bitField0_ & 2) != 2 && input.getBytesUntilLimit() > 0) {
                                            this.span_ = new ArrayList<Integer>();
                                            mutable_bitField0_ |= 2;
                                        }
                                        while (input.getBytesUntilLimit() > 0) {
                                            this.span_.add(input.readInt32());
                                        }
                                        input.popLimit(limit);
                                        continue block14;
                                    }
                                    case 26: {
                                        ByteString bs = input.readBytes();
                                        this.bitField0_ |= 1;
                                        this.leadingComments_ = bs;
                                        continue block14;
                                    }
                                    case 34: 
                                }
                                ByteString bs = input.readBytes();
                                this.bitField0_ |= 2;
                                this.trailingComments_ = bs;
                            }
                            Object var10_14 = null;
                            if (!(mutable_bitField0_ & true)) break block25;
                        } catch (InvalidProtocolBufferException e) {
                            throw e.setUnfinishedMessage(this);
                        } catch (IOException e) {
                            throw new InvalidProtocolBufferException(e.getMessage()).setUnfinishedMessage(this);
                        }
                    } catch (Throwable throwable) {
                        Object var10_15 = null;
                        if (mutable_bitField0_ & true) {
                            this.path_ = Collections.unmodifiableList(this.path_);
                        }
                        if ((mutable_bitField0_ & 2) == 2) {
                            this.span_ = Collections.unmodifiableList(this.span_);
                        }
                        this.unknownFields = unknownFields.build();
                        this.makeExtensionsImmutable();
                        throw throwable;
                    }
                    this.path_ = Collections.unmodifiableList(this.path_);
                }
                if ((mutable_bitField0_ & 2) == 2) {
                    this.span_ = Collections.unmodifiableList(this.span_);
                }
                this.unknownFields = unknownFields.build();
                this.makeExtensionsImmutable();
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_google_protobuf_SourceCodeInfo_Location_descriptor;
            }

            @Override
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_google_protobuf_SourceCodeInfo_Location_fieldAccessorTable.ensureFieldAccessorsInitialized(Location.class, Builder.class);
            }

            public Parser<Location> getParserForType() {
                return PARSER;
            }

            @Override
            public List<Integer> getPathList() {
                return this.path_;
            }

            @Override
            public int getPathCount() {
                return this.path_.size();
            }

            @Override
            public int getPath(int index) {
                return this.path_.get(index);
            }

            @Override
            public List<Integer> getSpanList() {
                return this.span_;
            }

            @Override
            public int getSpanCount() {
                return this.span_.size();
            }

            @Override
            public int getSpan(int index) {
                return this.span_.get(index);
            }

            @Override
            public boolean hasLeadingComments() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override
            public String getLeadingComments() {
                Object ref = this.leadingComments_;
                if (ref instanceof String) {
                    return (String)ref;
                }
                ByteString bs = (ByteString)ref;
                String s2 = bs.toStringUtf8();
                if (bs.isValidUtf8()) {
                    this.leadingComments_ = s2;
                }
                return s2;
            }

            @Override
            public ByteString getLeadingCommentsBytes() {
                Object ref = this.leadingComments_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String)ref);
                    this.leadingComments_ = b;
                    return b;
                }
                return (ByteString)ref;
            }

            @Override
            public boolean hasTrailingComments() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override
            public String getTrailingComments() {
                Object ref = this.trailingComments_;
                if (ref instanceof String) {
                    return (String)ref;
                }
                ByteString bs = (ByteString)ref;
                String s2 = bs.toStringUtf8();
                if (bs.isValidUtf8()) {
                    this.trailingComments_ = s2;
                }
                return s2;
            }

            @Override
            public ByteString getTrailingCommentsBytes() {
                Object ref = this.trailingComments_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String)ref);
                    this.trailingComments_ = b;
                    return b;
                }
                return (ByteString)ref;
            }

            private void initFields() {
                this.path_ = Collections.emptyList();
                this.span_ = Collections.emptyList();
                this.leadingComments_ = "";
                this.trailingComments_ = "";
            }

            @Override
            public final boolean isInitialized() {
                byte isInitialized = this.memoizedIsInitialized;
                if (isInitialized == 1) {
                    return true;
                }
                if (isInitialized == 0) {
                    return false;
                }
                this.memoizedIsInitialized = 1;
                return true;
            }

            @Override
            public void writeTo(CodedOutputStream output) throws IOException {
                int i;
                this.getSerializedSize();
                if (this.getPathList().size() > 0) {
                    output.writeRawVarint32(10);
                    output.writeRawVarint32(this.pathMemoizedSerializedSize);
                }
                for (i = 0; i < this.path_.size(); ++i) {
                    output.writeInt32NoTag(this.path_.get(i));
                }
                if (this.getSpanList().size() > 0) {
                    output.writeRawVarint32(18);
                    output.writeRawVarint32(this.spanMemoizedSerializedSize);
                }
                for (i = 0; i < this.span_.size(); ++i) {
                    output.writeInt32NoTag(this.span_.get(i));
                }
                if ((this.bitField0_ & 1) == 1) {
                    output.writeBytes(3, this.getLeadingCommentsBytes());
                }
                if ((this.bitField0_ & 2) == 2) {
                    output.writeBytes(4, this.getTrailingCommentsBytes());
                }
                this.getUnknownFields().writeTo(output);
            }

            @Override
            public int getSerializedSize() {
                int i;
                int size = this.memoizedSerializedSize;
                if (size != -1) {
                    return size;
                }
                size = 0;
                int dataSize = 0;
                for (i = 0; i < this.path_.size(); ++i) {
                    dataSize += CodedOutputStream.computeInt32SizeNoTag(this.path_.get(i));
                }
                size += dataSize;
                if (!this.getPathList().isEmpty()) {
                    ++size;
                    size += CodedOutputStream.computeInt32SizeNoTag(dataSize);
                }
                this.pathMemoizedSerializedSize = dataSize;
                dataSize = 0;
                for (i = 0; i < this.span_.size(); ++i) {
                    dataSize += CodedOutputStream.computeInt32SizeNoTag(this.span_.get(i));
                }
                size += dataSize;
                if (!this.getSpanList().isEmpty()) {
                    ++size;
                    size += CodedOutputStream.computeInt32SizeNoTag(dataSize);
                }
                this.spanMemoizedSerializedSize = dataSize;
                if ((this.bitField0_ & 1) == 1) {
                    size += CodedOutputStream.computeBytesSize(3, this.getLeadingCommentsBytes());
                }
                if ((this.bitField0_ & 2) == 2) {
                    size += CodedOutputStream.computeBytesSize(4, this.getTrailingCommentsBytes());
                }
                this.memoizedSerializedSize = size += this.getUnknownFields().getSerializedSize();
                return size;
            }

            @Override
            protected Object writeReplace() throws ObjectStreamException {
                return super.writeReplace();
            }

            public static Location parseFrom(ByteString data) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(data);
            }

            public static Location parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(data, extensionRegistry);
            }

            public static Location parseFrom(byte[] data) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(data);
            }

            public static Location parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(data, extensionRegistry);
            }

            public static Location parseFrom(InputStream input) throws IOException {
                return PARSER.parseFrom(input);
            }

            public static Location parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return PARSER.parseFrom(input, extensionRegistry);
            }

            public static Location parseDelimitedFrom(InputStream input) throws IOException {
                return PARSER.parseDelimitedFrom(input);
            }

            public static Location parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return PARSER.parseDelimitedFrom(input, extensionRegistry);
            }

            public static Location parseFrom(CodedInputStream input) throws IOException {
                return PARSER.parseFrom(input);
            }

            public static Location parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return PARSER.parseFrom(input, extensionRegistry);
            }

            public static Builder newBuilder() {
                return Builder.create();
            }

            @Override
            public Builder newBuilderForType() {
                return Location.newBuilder();
            }

            public static Builder newBuilder(Location prototype) {
                return Location.newBuilder().mergeFrom(prototype);
            }

            @Override
            public Builder toBuilder() {
                return Location.newBuilder(this);
            }

            @Override
            protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
                Builder builder = new Builder(parent);
                return builder;
            }

            static {
                PARSER = new AbstractParser<Location>(){

                    @Override
                    public Location parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                        return new Location(input, extensionRegistry);
                    }
                };
                defaultInstance = new Location(true);
                defaultInstance.initFields();
            }

            /*
             * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
             */
            public static final class Builder
            extends GeneratedMessage.Builder<Builder>
            implements LocationOrBuilder {
                private int bitField0_;
                private List<Integer> path_ = Collections.emptyList();
                private List<Integer> span_ = Collections.emptyList();
                private Object leadingComments_ = "";
                private Object trailingComments_ = "";

                public static final Descriptors.Descriptor getDescriptor() {
                    return internal_static_google_protobuf_SourceCodeInfo_Location_descriptor;
                }

                @Override
                protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                    return internal_static_google_protobuf_SourceCodeInfo_Location_fieldAccessorTable.ensureFieldAccessorsInitialized(Location.class, Builder.class);
                }

                private Builder() {
                    this.maybeForceBuilderInitialization();
                }

                private Builder(GeneratedMessage.BuilderParent parent) {
                    super(parent);
                    this.maybeForceBuilderInitialization();
                }

                private void maybeForceBuilderInitialization() {
                    if (GeneratedMessage.alwaysUseFieldBuilders) {
                        // empty if block
                    }
                }

                private static Builder create() {
                    return new Builder();
                }

                @Override
                public Builder clear() {
                    super.clear();
                    this.path_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFFFFE;
                    this.span_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFFFFD;
                    this.leadingComments_ = "";
                    this.bitField0_ &= 0xFFFFFFFB;
                    this.trailingComments_ = "";
                    this.bitField0_ &= 0xFFFFFFF7;
                    return this;
                }

                @Override
                public Builder clone() {
                    return Builder.create().mergeFrom(this.buildPartial());
                }

                @Override
                public Descriptors.Descriptor getDescriptorForType() {
                    return internal_static_google_protobuf_SourceCodeInfo_Location_descriptor;
                }

                @Override
                public Location getDefaultInstanceForType() {
                    return Location.getDefaultInstance();
                }

                @Override
                public Location build() {
                    Location result = this.buildPartial();
                    if (!result.isInitialized()) {
                        throw Builder.newUninitializedMessageException(result);
                    }
                    return result;
                }

                @Override
                public Location buildPartial() {
                    Location result = new Location(this);
                    int from_bitField0_ = this.bitField0_;
                    int to_bitField0_ = 0;
                    if ((this.bitField0_ & 1) == 1) {
                        this.path_ = Collections.unmodifiableList(this.path_);
                        this.bitField0_ &= 0xFFFFFFFE;
                    }
                    result.path_ = this.path_;
                    if ((this.bitField0_ & 2) == 2) {
                        this.span_ = Collections.unmodifiableList(this.span_);
                        this.bitField0_ &= 0xFFFFFFFD;
                    }
                    result.span_ = this.span_;
                    if ((from_bitField0_ & 4) == 4) {
                        to_bitField0_ |= 1;
                    }
                    result.leadingComments_ = this.leadingComments_;
                    if ((from_bitField0_ & 8) == 8) {
                        to_bitField0_ |= 2;
                    }
                    result.trailingComments_ = this.trailingComments_;
                    result.bitField0_ = to_bitField0_;
                    this.onBuilt();
                    return result;
                }

                @Override
                public Builder mergeFrom(Message other) {
                    if (other instanceof Location) {
                        return this.mergeFrom((Location)other);
                    }
                    super.mergeFrom(other);
                    return this;
                }

                public Builder mergeFrom(Location other) {
                    if (other == Location.getDefaultInstance()) {
                        return this;
                    }
                    if (!other.path_.isEmpty()) {
                        if (this.path_.isEmpty()) {
                            this.path_ = other.path_;
                            this.bitField0_ &= 0xFFFFFFFE;
                        } else {
                            this.ensurePathIsMutable();
                            this.path_.addAll(other.path_);
                        }
                        this.onChanged();
                    }
                    if (!other.span_.isEmpty()) {
                        if (this.span_.isEmpty()) {
                            this.span_ = other.span_;
                            this.bitField0_ &= 0xFFFFFFFD;
                        } else {
                            this.ensureSpanIsMutable();
                            this.span_.addAll(other.span_);
                        }
                        this.onChanged();
                    }
                    if (other.hasLeadingComments()) {
                        this.bitField0_ |= 4;
                        this.leadingComments_ = other.leadingComments_;
                        this.onChanged();
                    }
                    if (other.hasTrailingComments()) {
                        this.bitField0_ |= 8;
                        this.trailingComments_ = other.trailingComments_;
                        this.onChanged();
                    }
                    this.mergeUnknownFields(other.getUnknownFields());
                    return this;
                }

                @Override
                public final boolean isInitialized() {
                    return true;
                }

                /*
                 * Enabled force condition propagation
                 * Lifted jumps to return sites
                 */
                @Override
                public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                    Location parsedMessage = null;
                    try {
                        try {
                            parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
                        } catch (InvalidProtocolBufferException e) {
                            parsedMessage = (Location)e.getUnfinishedMessage();
                            throw e;
                        }
                        Object var6_4 = null;
                        if (parsedMessage == null) return this;
                        this.mergeFrom(parsedMessage);
                        return this;
                    } catch (Throwable throwable) {
                        Object var6_5 = null;
                        if (parsedMessage == null) throw throwable;
                        this.mergeFrom(parsedMessage);
                        throw throwable;
                    }
                }

                private void ensurePathIsMutable() {
                    if ((this.bitField0_ & 1) != 1) {
                        this.path_ = new ArrayList<Integer>(this.path_);
                        this.bitField0_ |= 1;
                    }
                }

                @Override
                public List<Integer> getPathList() {
                    return Collections.unmodifiableList(this.path_);
                }

                @Override
                public int getPathCount() {
                    return this.path_.size();
                }

                @Override
                public int getPath(int index) {
                    return this.path_.get(index);
                }

                public Builder setPath(int index, int value) {
                    this.ensurePathIsMutable();
                    this.path_.set(index, value);
                    this.onChanged();
                    return this;
                }

                public Builder addPath(int value) {
                    this.ensurePathIsMutable();
                    this.path_.add(value);
                    this.onChanged();
                    return this;
                }

                public Builder addAllPath(Iterable<? extends Integer> values2) {
                    this.ensurePathIsMutable();
                    AbstractMessageLite.Builder.addAll(values2, this.path_);
                    this.onChanged();
                    return this;
                }

                public Builder clearPath() {
                    this.path_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFFFFE;
                    this.onChanged();
                    return this;
                }

                private void ensureSpanIsMutable() {
                    if ((this.bitField0_ & 2) != 2) {
                        this.span_ = new ArrayList<Integer>(this.span_);
                        this.bitField0_ |= 2;
                    }
                }

                @Override
                public List<Integer> getSpanList() {
                    return Collections.unmodifiableList(this.span_);
                }

                @Override
                public int getSpanCount() {
                    return this.span_.size();
                }

                @Override
                public int getSpan(int index) {
                    return this.span_.get(index);
                }

                public Builder setSpan(int index, int value) {
                    this.ensureSpanIsMutable();
                    this.span_.set(index, value);
                    this.onChanged();
                    return this;
                }

                public Builder addSpan(int value) {
                    this.ensureSpanIsMutable();
                    this.span_.add(value);
                    this.onChanged();
                    return this;
                }

                public Builder addAllSpan(Iterable<? extends Integer> values2) {
                    this.ensureSpanIsMutable();
                    AbstractMessageLite.Builder.addAll(values2, this.span_);
                    this.onChanged();
                    return this;
                }

                public Builder clearSpan() {
                    this.span_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFFFFD;
                    this.onChanged();
                    return this;
                }

                @Override
                public boolean hasLeadingComments() {
                    return (this.bitField0_ & 4) == 4;
                }

                @Override
                public String getLeadingComments() {
                    Object ref = this.leadingComments_;
                    if (!(ref instanceof String)) {
                        ByteString bs = (ByteString)ref;
                        String s2 = bs.toStringUtf8();
                        if (bs.isValidUtf8()) {
                            this.leadingComments_ = s2;
                        }
                        return s2;
                    }
                    return (String)ref;
                }

                @Override
                public ByteString getLeadingCommentsBytes() {
                    Object ref = this.leadingComments_;
                    if (ref instanceof String) {
                        ByteString b = ByteString.copyFromUtf8((String)ref);
                        this.leadingComments_ = b;
                        return b;
                    }
                    return (ByteString)ref;
                }

                public Builder setLeadingComments(String value) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.bitField0_ |= 4;
                    this.leadingComments_ = value;
                    this.onChanged();
                    return this;
                }

                public Builder clearLeadingComments() {
                    this.bitField0_ &= 0xFFFFFFFB;
                    this.leadingComments_ = Location.getDefaultInstance().getLeadingComments();
                    this.onChanged();
                    return this;
                }

                public Builder setLeadingCommentsBytes(ByteString value) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.bitField0_ |= 4;
                    this.leadingComments_ = value;
                    this.onChanged();
                    return this;
                }

                @Override
                public boolean hasTrailingComments() {
                    return (this.bitField0_ & 8) == 8;
                }

                @Override
                public String getTrailingComments() {
                    Object ref = this.trailingComments_;
                    if (!(ref instanceof String)) {
                        ByteString bs = (ByteString)ref;
                        String s2 = bs.toStringUtf8();
                        if (bs.isValidUtf8()) {
                            this.trailingComments_ = s2;
                        }
                        return s2;
                    }
                    return (String)ref;
                }

                @Override
                public ByteString getTrailingCommentsBytes() {
                    Object ref = this.trailingComments_;
                    if (ref instanceof String) {
                        ByteString b = ByteString.copyFromUtf8((String)ref);
                        this.trailingComments_ = b;
                        return b;
                    }
                    return (ByteString)ref;
                }

                public Builder setTrailingComments(String value) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.bitField0_ |= 8;
                    this.trailingComments_ = value;
                    this.onChanged();
                    return this;
                }

                public Builder clearTrailingComments() {
                    this.bitField0_ &= 0xFFFFFFF7;
                    this.trailingComments_ = Location.getDefaultInstance().getTrailingComments();
                    this.onChanged();
                    return this;
                }

                public Builder setTrailingCommentsBytes(ByteString value) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.bitField0_ |= 8;
                    this.trailingComments_ = value;
                    this.onChanged();
                    return this;
                }
            }
        }

        /*
         * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
         */
        public static interface LocationOrBuilder
        extends MessageOrBuilder {
            public List<Integer> getPathList();

            public int getPathCount();

            public int getPath(int var1);

            public List<Integer> getSpanList();

            public int getSpanCount();

            public int getSpan(int var1);

            public boolean hasLeadingComments();

            public String getLeadingComments();

            public ByteString getLeadingCommentsBytes();

            public boolean hasTrailingComments();

            public String getTrailingComments();

            public ByteString getTrailingCommentsBytes();
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static interface SourceCodeInfoOrBuilder
    extends MessageOrBuilder {
        public List<SourceCodeInfo.Location> getLocationList();

        public SourceCodeInfo.Location getLocation(int var1);

        public int getLocationCount();

        public List<? extends SourceCodeInfo.LocationOrBuilder> getLocationOrBuilderList();

        public SourceCodeInfo.LocationOrBuilder getLocationOrBuilder(int var1);
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static final class UninterpretedOption
    extends GeneratedMessage
    implements UninterpretedOptionOrBuilder {
        private static final UninterpretedOption defaultInstance;
        private final UnknownFieldSet unknownFields;
        public static Parser<UninterpretedOption> PARSER;
        private int bitField0_;
        public static final int NAME_FIELD_NUMBER = 2;
        private List<NamePart> name_;
        public static final int IDENTIFIER_VALUE_FIELD_NUMBER = 3;
        private Object identifierValue_;
        public static final int POSITIVE_INT_VALUE_FIELD_NUMBER = 4;
        private long positiveIntValue_;
        public static final int NEGATIVE_INT_VALUE_FIELD_NUMBER = 5;
        private long negativeIntValue_;
        public static final int DOUBLE_VALUE_FIELD_NUMBER = 6;
        private double doubleValue_;
        public static final int STRING_VALUE_FIELD_NUMBER = 7;
        private ByteString stringValue_;
        public static final int AGGREGATE_VALUE_FIELD_NUMBER = 8;
        private Object aggregateValue_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private static final long serialVersionUID = 0L;

        private UninterpretedOption(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte)-1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private UninterpretedOption(boolean noInit) {
            this.memoizedIsInitialized = (byte)-1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static UninterpretedOption getDefaultInstance() {
            return defaultInstance;
        }

        @Override
        public UninterpretedOption getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        private UninterpretedOption(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            UnknownFieldSet.Builder unknownFields;
            block19: {
                this.memoizedIsInitialized = (byte)-1;
                this.memoizedSerializedSize = -1;
                this.initFields();
                boolean mutable_bitField0_ = false;
                unknownFields = UnknownFieldSet.newBuilder();
                try {
                    try {
                        boolean done = false;
                        block15: while (!done) {
                            ByteString bs;
                            int tag = input.readTag();
                            switch (tag) {
                                case 0: {
                                    done = true;
                                    continue block15;
                                }
                                default: {
                                    if (this.parseUnknownField(input, unknownFields, extensionRegistry, tag)) continue block15;
                                    done = true;
                                    continue block15;
                                }
                                case 18: {
                                    if (!(mutable_bitField0_ & true)) {
                                        this.name_ = new ArrayList<NamePart>();
                                        mutable_bitField0_ |= true;
                                    }
                                    this.name_.add(input.readMessage(NamePart.PARSER, extensionRegistry));
                                    continue block15;
                                }
                                case 26: {
                                    bs = input.readBytes();
                                    this.bitField0_ |= 1;
                                    this.identifierValue_ = bs;
                                    continue block15;
                                }
                                case 32: {
                                    this.bitField0_ |= 2;
                                    this.positiveIntValue_ = input.readUInt64();
                                    continue block15;
                                }
                                case 40: {
                                    this.bitField0_ |= 4;
                                    this.negativeIntValue_ = input.readInt64();
                                    continue block15;
                                }
                                case 49: {
                                    this.bitField0_ |= 8;
                                    this.doubleValue_ = input.readDouble();
                                    continue block15;
                                }
                                case 58: {
                                    this.bitField0_ |= 0x10;
                                    this.stringValue_ = input.readBytes();
                                    continue block15;
                                }
                                case 66: 
                            }
                            bs = input.readBytes();
                            this.bitField0_ |= 0x20;
                            this.aggregateValue_ = bs;
                        }
                        Object var9_10 = null;
                        if (!(mutable_bitField0_ & true)) break block19;
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e) {
                        throw new InvalidProtocolBufferException(e.getMessage()).setUnfinishedMessage(this);
                    }
                } catch (Throwable throwable) {
                    Object var9_11 = null;
                    if (mutable_bitField0_ & true) {
                        this.name_ = Collections.unmodifiableList(this.name_);
                    }
                    this.unknownFields = unknownFields.build();
                    this.makeExtensionsImmutable();
                    throw throwable;
                }
                this.name_ = Collections.unmodifiableList(this.name_);
            }
            this.unknownFields = unknownFields.build();
            this.makeExtensionsImmutable();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return internal_static_google_protobuf_UninterpretedOption_descriptor;
        }

        @Override
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return internal_static_google_protobuf_UninterpretedOption_fieldAccessorTable.ensureFieldAccessorsInitialized(UninterpretedOption.class, Builder.class);
        }

        public Parser<UninterpretedOption> getParserForType() {
            return PARSER;
        }

        @Override
        public List<NamePart> getNameList() {
            return this.name_;
        }

        @Override
        public List<? extends NamePartOrBuilder> getNameOrBuilderList() {
            return this.name_;
        }

        @Override
        public int getNameCount() {
            return this.name_.size();
        }

        @Override
        public NamePart getName(int index) {
            return this.name_.get(index);
        }

        @Override
        public NamePartOrBuilder getNameOrBuilder(int index) {
            return this.name_.get(index);
        }

        @Override
        public boolean hasIdentifierValue() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override
        public String getIdentifierValue() {
            Object ref = this.identifierValue_;
            if (ref instanceof String) {
                return (String)ref;
            }
            ByteString bs = (ByteString)ref;
            String s2 = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.identifierValue_ = s2;
            }
            return s2;
        }

        @Override
        public ByteString getIdentifierValueBytes() {
            Object ref = this.identifierValue_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.identifierValue_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        @Override
        public boolean hasPositiveIntValue() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override
        public long getPositiveIntValue() {
            return this.positiveIntValue_;
        }

        @Override
        public boolean hasNegativeIntValue() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override
        public long getNegativeIntValue() {
            return this.negativeIntValue_;
        }

        @Override
        public boolean hasDoubleValue() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override
        public double getDoubleValue() {
            return this.doubleValue_;
        }

        @Override
        public boolean hasStringValue() {
            return (this.bitField0_ & 0x10) == 16;
        }

        @Override
        public ByteString getStringValue() {
            return this.stringValue_;
        }

        @Override
        public boolean hasAggregateValue() {
            return (this.bitField0_ & 0x20) == 32;
        }

        @Override
        public String getAggregateValue() {
            Object ref = this.aggregateValue_;
            if (ref instanceof String) {
                return (String)ref;
            }
            ByteString bs = (ByteString)ref;
            String s2 = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.aggregateValue_ = s2;
            }
            return s2;
        }

        @Override
        public ByteString getAggregateValueBytes() {
            Object ref = this.aggregateValue_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.aggregateValue_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        private void initFields() {
            this.name_ = Collections.emptyList();
            this.identifierValue_ = "";
            this.positiveIntValue_ = 0L;
            this.negativeIntValue_ = 0L;
            this.doubleValue_ = 0.0;
            this.stringValue_ = ByteString.EMPTY;
            this.aggregateValue_ = "";
        }

        @Override
        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            for (int i = 0; i < this.getNameCount(); ++i) {
                if (this.getName(i).isInitialized()) continue;
                this.memoizedIsInitialized = 0;
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        @Override
        public void writeTo(CodedOutputStream output) throws IOException {
            this.getSerializedSize();
            for (int i = 0; i < this.name_.size(); ++i) {
                output.writeMessage(2, this.name_.get(i));
            }
            if ((this.bitField0_ & 1) == 1) {
                output.writeBytes(3, this.getIdentifierValueBytes());
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeUInt64(4, this.positiveIntValue_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeInt64(5, this.negativeIntValue_);
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeDouble(6, this.doubleValue_);
            }
            if ((this.bitField0_ & 0x10) == 16) {
                output.writeBytes(7, this.stringValue_);
            }
            if ((this.bitField0_ & 0x20) == 32) {
                output.writeBytes(8, this.getAggregateValueBytes());
            }
            this.getUnknownFields().writeTo(output);
        }

        @Override
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            size = 0;
            for (int i = 0; i < this.name_.size(); ++i) {
                size += CodedOutputStream.computeMessageSize(2, this.name_.get(i));
            }
            if ((this.bitField0_ & 1) == 1) {
                size += CodedOutputStream.computeBytesSize(3, this.getIdentifierValueBytes());
            }
            if ((this.bitField0_ & 2) == 2) {
                size += CodedOutputStream.computeUInt64Size(4, this.positiveIntValue_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size += CodedOutputStream.computeInt64Size(5, this.negativeIntValue_);
            }
            if ((this.bitField0_ & 8) == 8) {
                size += CodedOutputStream.computeDoubleSize(6, this.doubleValue_);
            }
            if ((this.bitField0_ & 0x10) == 16) {
                size += CodedOutputStream.computeBytesSize(7, this.stringValue_);
            }
            if ((this.bitField0_ & 0x20) == 32) {
                size += CodedOutputStream.computeBytesSize(8, this.getAggregateValueBytes());
            }
            this.memoizedSerializedSize = size += this.getUnknownFields().getSerializedSize();
            return size;
        }

        @Override
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static UninterpretedOption parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static UninterpretedOption parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static UninterpretedOption parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static UninterpretedOption parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static UninterpretedOption parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static UninterpretedOption parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static UninterpretedOption parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static UninterpretedOption parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static UninterpretedOption parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static UninterpretedOption parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override
        public Builder newBuilderForType() {
            return UninterpretedOption.newBuilder();
        }

        public static Builder newBuilder(UninterpretedOption prototype) {
            return UninterpretedOption.newBuilder().mergeFrom(prototype);
        }

        @Override
        public Builder toBuilder() {
            return UninterpretedOption.newBuilder(this);
        }

        @Override
        protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        static {
            PARSER = new AbstractParser<UninterpretedOption>(){

                @Override
                public UninterpretedOption parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                    return new UninterpretedOption(input, extensionRegistry);
                }
            };
            defaultInstance = new UninterpretedOption(true);
            defaultInstance.initFields();
        }

        /*
         * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
         */
        public static final class Builder
        extends GeneratedMessage.Builder<Builder>
        implements UninterpretedOptionOrBuilder {
            private int bitField0_;
            private List<NamePart> name_ = Collections.emptyList();
            private RepeatedFieldBuilder<NamePart, NamePart.Builder, NamePartOrBuilder> nameBuilder_;
            private Object identifierValue_ = "";
            private long positiveIntValue_;
            private long negativeIntValue_;
            private double doubleValue_;
            private ByteString stringValue_ = ByteString.EMPTY;
            private Object aggregateValue_ = "";

            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_google_protobuf_UninterpretedOption_descriptor;
            }

            @Override
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_google_protobuf_UninterpretedOption_fieldAccessorTable.ensureFieldAccessorsInitialized(UninterpretedOption.class, Builder.class);
            }

            private Builder() {
                this.maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessage.BuilderParent parent) {
                super(parent);
                this.maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                if (GeneratedMessage.alwaysUseFieldBuilders) {
                    this.getNameFieldBuilder();
                }
            }

            private static Builder create() {
                return new Builder();
            }

            @Override
            public Builder clear() {
                super.clear();
                if (this.nameBuilder_ == null) {
                    this.name_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFFFFE;
                } else {
                    this.nameBuilder_.clear();
                }
                this.identifierValue_ = "";
                this.bitField0_ &= 0xFFFFFFFD;
                this.positiveIntValue_ = 0L;
                this.bitField0_ &= 0xFFFFFFFB;
                this.negativeIntValue_ = 0L;
                this.bitField0_ &= 0xFFFFFFF7;
                this.doubleValue_ = 0.0;
                this.bitField0_ &= 0xFFFFFFEF;
                this.stringValue_ = ByteString.EMPTY;
                this.bitField0_ &= 0xFFFFFFDF;
                this.aggregateValue_ = "";
                this.bitField0_ &= 0xFFFFFFBF;
                return this;
            }

            @Override
            public Builder clone() {
                return Builder.create().mergeFrom(this.buildPartial());
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return internal_static_google_protobuf_UninterpretedOption_descriptor;
            }

            @Override
            public UninterpretedOption getDefaultInstanceForType() {
                return UninterpretedOption.getDefaultInstance();
            }

            @Override
            public UninterpretedOption build() {
                UninterpretedOption result = this.buildPartial();
                if (!result.isInitialized()) {
                    throw Builder.newUninitializedMessageException(result);
                }
                return result;
            }

            @Override
            public UninterpretedOption buildPartial() {
                UninterpretedOption result = new UninterpretedOption(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if (this.nameBuilder_ == null) {
                    if ((this.bitField0_ & 1) == 1) {
                        this.name_ = Collections.unmodifiableList(this.name_);
                        this.bitField0_ &= 0xFFFFFFFE;
                    }
                    result.name_ = this.name_;
                } else {
                    result.name_ = this.nameBuilder_.build();
                }
                if ((from_bitField0_ & 2) == 2) {
                    to_bitField0_ |= 1;
                }
                result.identifierValue_ = this.identifierValue_;
                if ((from_bitField0_ & 4) == 4) {
                    to_bitField0_ |= 2;
                }
                result.positiveIntValue_ = this.positiveIntValue_;
                if ((from_bitField0_ & 8) == 8) {
                    to_bitField0_ |= 4;
                }
                result.negativeIntValue_ = this.negativeIntValue_;
                if ((from_bitField0_ & 0x10) == 16) {
                    to_bitField0_ |= 8;
                }
                result.doubleValue_ = this.doubleValue_;
                if ((from_bitField0_ & 0x20) == 32) {
                    to_bitField0_ |= 0x10;
                }
                result.stringValue_ = this.stringValue_;
                if ((from_bitField0_ & 0x40) == 64) {
                    to_bitField0_ |= 0x20;
                }
                result.aggregateValue_ = this.aggregateValue_;
                result.bitField0_ = to_bitField0_;
                this.onBuilt();
                return result;
            }

            @Override
            public Builder mergeFrom(Message other) {
                if (other instanceof UninterpretedOption) {
                    return this.mergeFrom((UninterpretedOption)other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(UninterpretedOption other) {
                if (other == UninterpretedOption.getDefaultInstance()) {
                    return this;
                }
                if (this.nameBuilder_ == null) {
                    if (!other.name_.isEmpty()) {
                        if (this.name_.isEmpty()) {
                            this.name_ = other.name_;
                            this.bitField0_ &= 0xFFFFFFFE;
                        } else {
                            this.ensureNameIsMutable();
                            this.name_.addAll(other.name_);
                        }
                        this.onChanged();
                    }
                } else if (!other.name_.isEmpty()) {
                    if (this.nameBuilder_.isEmpty()) {
                        this.nameBuilder_.dispose();
                        this.nameBuilder_ = null;
                        this.name_ = other.name_;
                        this.bitField0_ &= 0xFFFFFFFE;
                        this.nameBuilder_ = GeneratedMessage.alwaysUseFieldBuilders ? this.getNameFieldBuilder() : null;
                    } else {
                        this.nameBuilder_.addAllMessages(other.name_);
                    }
                }
                if (other.hasIdentifierValue()) {
                    this.bitField0_ |= 2;
                    this.identifierValue_ = other.identifierValue_;
                    this.onChanged();
                }
                if (other.hasPositiveIntValue()) {
                    this.setPositiveIntValue(other.getPositiveIntValue());
                }
                if (other.hasNegativeIntValue()) {
                    this.setNegativeIntValue(other.getNegativeIntValue());
                }
                if (other.hasDoubleValue()) {
                    this.setDoubleValue(other.getDoubleValue());
                }
                if (other.hasStringValue()) {
                    this.setStringValue(other.getStringValue());
                }
                if (other.hasAggregateValue()) {
                    this.bitField0_ |= 0x40;
                    this.aggregateValue_ = other.aggregateValue_;
                    this.onChanged();
                }
                this.mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            @Override
            public final boolean isInitialized() {
                for (int i = 0; i < this.getNameCount(); ++i) {
                    if (this.getName(i).isInitialized()) continue;
                    return false;
                }
                return true;
            }

            /*
             * Enabled force condition propagation
             * Lifted jumps to return sites
             */
            @Override
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                UninterpretedOption parsedMessage = null;
                try {
                    try {
                        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
                    } catch (InvalidProtocolBufferException e) {
                        parsedMessage = (UninterpretedOption)e.getUnfinishedMessage();
                        throw e;
                    }
                    Object var6_4 = null;
                    if (parsedMessage == null) return this;
                    this.mergeFrom(parsedMessage);
                    return this;
                } catch (Throwable throwable) {
                    Object var6_5 = null;
                    if (parsedMessage == null) throw throwable;
                    this.mergeFrom(parsedMessage);
                    throw throwable;
                }
            }

            private void ensureNameIsMutable() {
                if ((this.bitField0_ & 1) != 1) {
                    this.name_ = new ArrayList<NamePart>(this.name_);
                    this.bitField0_ |= 1;
                }
            }

            @Override
            public List<NamePart> getNameList() {
                if (this.nameBuilder_ == null) {
                    return Collections.unmodifiableList(this.name_);
                }
                return this.nameBuilder_.getMessageList();
            }

            @Override
            public int getNameCount() {
                if (this.nameBuilder_ == null) {
                    return this.name_.size();
                }
                return this.nameBuilder_.getCount();
            }

            @Override
            public NamePart getName(int index) {
                if (this.nameBuilder_ == null) {
                    return this.name_.get(index);
                }
                return this.nameBuilder_.getMessage(index);
            }

            public Builder setName(int index, NamePart value) {
                if (this.nameBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureNameIsMutable();
                    this.name_.set(index, value);
                    this.onChanged();
                } else {
                    this.nameBuilder_.setMessage(index, value);
                }
                return this;
            }

            public Builder setName(int index, NamePart.Builder builderForValue) {
                if (this.nameBuilder_ == null) {
                    this.ensureNameIsMutable();
                    this.name_.set(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.nameBuilder_.setMessage(index, builderForValue.build());
                }
                return this;
            }

            public Builder addName(NamePart value) {
                if (this.nameBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureNameIsMutable();
                    this.name_.add(value);
                    this.onChanged();
                } else {
                    this.nameBuilder_.addMessage(value);
                }
                return this;
            }

            public Builder addName(int index, NamePart value) {
                if (this.nameBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureNameIsMutable();
                    this.name_.add(index, value);
                    this.onChanged();
                } else {
                    this.nameBuilder_.addMessage(index, value);
                }
                return this;
            }

            public Builder addName(NamePart.Builder builderForValue) {
                if (this.nameBuilder_ == null) {
                    this.ensureNameIsMutable();
                    this.name_.add(builderForValue.build());
                    this.onChanged();
                } else {
                    this.nameBuilder_.addMessage(builderForValue.build());
                }
                return this;
            }

            public Builder addName(int index, NamePart.Builder builderForValue) {
                if (this.nameBuilder_ == null) {
                    this.ensureNameIsMutable();
                    this.name_.add(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.nameBuilder_.addMessage(index, builderForValue.build());
                }
                return this;
            }

            public Builder addAllName(Iterable<? extends NamePart> values2) {
                if (this.nameBuilder_ == null) {
                    this.ensureNameIsMutable();
                    AbstractMessageLite.Builder.addAll(values2, this.name_);
                    this.onChanged();
                } else {
                    this.nameBuilder_.addAllMessages(values2);
                }
                return this;
            }

            public Builder clearName() {
                if (this.nameBuilder_ == null) {
                    this.name_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFFFFE;
                    this.onChanged();
                } else {
                    this.nameBuilder_.clear();
                }
                return this;
            }

            public Builder removeName(int index) {
                if (this.nameBuilder_ == null) {
                    this.ensureNameIsMutable();
                    this.name_.remove(index);
                    this.onChanged();
                } else {
                    this.nameBuilder_.remove(index);
                }
                return this;
            }

            public NamePart.Builder getNameBuilder(int index) {
                return this.getNameFieldBuilder().getBuilder(index);
            }

            @Override
            public NamePartOrBuilder getNameOrBuilder(int index) {
                if (this.nameBuilder_ == null) {
                    return this.name_.get(index);
                }
                return this.nameBuilder_.getMessageOrBuilder(index);
            }

            @Override
            public List<? extends NamePartOrBuilder> getNameOrBuilderList() {
                if (this.nameBuilder_ != null) {
                    return this.nameBuilder_.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.name_);
            }

            public NamePart.Builder addNameBuilder() {
                return this.getNameFieldBuilder().addBuilder(NamePart.getDefaultInstance());
            }

            public NamePart.Builder addNameBuilder(int index) {
                return this.getNameFieldBuilder().addBuilder(index, NamePart.getDefaultInstance());
            }

            public List<NamePart.Builder> getNameBuilderList() {
                return this.getNameFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilder<NamePart, NamePart.Builder, NamePartOrBuilder> getNameFieldBuilder() {
                if (this.nameBuilder_ == null) {
                    this.nameBuilder_ = new RepeatedFieldBuilder(this.name_, (this.bitField0_ & 1) == 1, this.getParentForChildren(), this.isClean());
                    this.name_ = null;
                }
                return this.nameBuilder_;
            }

            @Override
            public boolean hasIdentifierValue() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override
            public String getIdentifierValue() {
                Object ref = this.identifierValue_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString)ref;
                    String s2 = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.identifierValue_ = s2;
                    }
                    return s2;
                }
                return (String)ref;
            }

            @Override
            public ByteString getIdentifierValueBytes() {
                Object ref = this.identifierValue_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String)ref);
                    this.identifierValue_ = b;
                    return b;
                }
                return (ByteString)ref;
            }

            public Builder setIdentifierValue(String value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 2;
                this.identifierValue_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearIdentifierValue() {
                this.bitField0_ &= 0xFFFFFFFD;
                this.identifierValue_ = UninterpretedOption.getDefaultInstance().getIdentifierValue();
                this.onChanged();
                return this;
            }

            public Builder setIdentifierValueBytes(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 2;
                this.identifierValue_ = value;
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasPositiveIntValue() {
                return (this.bitField0_ & 4) == 4;
            }

            @Override
            public long getPositiveIntValue() {
                return this.positiveIntValue_;
            }

            public Builder setPositiveIntValue(long value) {
                this.bitField0_ |= 4;
                this.positiveIntValue_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearPositiveIntValue() {
                this.bitField0_ &= 0xFFFFFFFB;
                this.positiveIntValue_ = 0L;
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasNegativeIntValue() {
                return (this.bitField0_ & 8) == 8;
            }

            @Override
            public long getNegativeIntValue() {
                return this.negativeIntValue_;
            }

            public Builder setNegativeIntValue(long value) {
                this.bitField0_ |= 8;
                this.negativeIntValue_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearNegativeIntValue() {
                this.bitField0_ &= 0xFFFFFFF7;
                this.negativeIntValue_ = 0L;
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasDoubleValue() {
                return (this.bitField0_ & 0x10) == 16;
            }

            @Override
            public double getDoubleValue() {
                return this.doubleValue_;
            }

            public Builder setDoubleValue(double value) {
                this.bitField0_ |= 0x10;
                this.doubleValue_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearDoubleValue() {
                this.bitField0_ &= 0xFFFFFFEF;
                this.doubleValue_ = 0.0;
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasStringValue() {
                return (this.bitField0_ & 0x20) == 32;
            }

            @Override
            public ByteString getStringValue() {
                return this.stringValue_;
            }

            public Builder setStringValue(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 0x20;
                this.stringValue_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearStringValue() {
                this.bitField0_ &= 0xFFFFFFDF;
                this.stringValue_ = UninterpretedOption.getDefaultInstance().getStringValue();
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasAggregateValue() {
                return (this.bitField0_ & 0x40) == 64;
            }

            @Override
            public String getAggregateValue() {
                Object ref = this.aggregateValue_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString)ref;
                    String s2 = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.aggregateValue_ = s2;
                    }
                    return s2;
                }
                return (String)ref;
            }

            @Override
            public ByteString getAggregateValueBytes() {
                Object ref = this.aggregateValue_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String)ref);
                    this.aggregateValue_ = b;
                    return b;
                }
                return (ByteString)ref;
            }

            public Builder setAggregateValue(String value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 0x40;
                this.aggregateValue_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearAggregateValue() {
                this.bitField0_ &= 0xFFFFFFBF;
                this.aggregateValue_ = UninterpretedOption.getDefaultInstance().getAggregateValue();
                this.onChanged();
                return this;
            }

            public Builder setAggregateValueBytes(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 0x40;
                this.aggregateValue_ = value;
                this.onChanged();
                return this;
            }
        }

        /*
         * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
         */
        public static final class NamePart
        extends GeneratedMessage
        implements NamePartOrBuilder {
            private static final NamePart defaultInstance;
            private final UnknownFieldSet unknownFields;
            public static Parser<NamePart> PARSER;
            private int bitField0_;
            public static final int NAME_PART_FIELD_NUMBER = 1;
            private Object namePart_;
            public static final int IS_EXTENSION_FIELD_NUMBER = 2;
            private boolean isExtension_;
            private byte memoizedIsInitialized = (byte)-1;
            private int memoizedSerializedSize = -1;
            private static final long serialVersionUID = 0L;

            private NamePart(GeneratedMessage.Builder<?> builder) {
                super(builder);
                this.unknownFields = builder.getUnknownFields();
            }

            private NamePart(boolean noInit) {
                this.unknownFields = UnknownFieldSet.getDefaultInstance();
            }

            public static NamePart getDefaultInstance() {
                return defaultInstance;
            }

            @Override
            public NamePart getDefaultInstanceForType() {
                return defaultInstance;
            }

            @Override
            public final UnknownFieldSet getUnknownFields() {
                return this.unknownFields;
            }

            private NamePart(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                this.initFields();
                boolean mutable_bitField0_ = false;
                UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
                try {
                    try {
                        boolean done = false;
                        block10: while (!done) {
                            int tag = input.readTag();
                            switch (tag) {
                                case 0: {
                                    done = true;
                                    continue block10;
                                }
                                default: {
                                    if (this.parseUnknownField(input, unknownFields, extensionRegistry, tag)) continue block10;
                                    done = true;
                                    continue block10;
                                }
                                case 10: {
                                    ByteString bs = input.readBytes();
                                    this.bitField0_ |= 1;
                                    this.namePart_ = bs;
                                    continue block10;
                                }
                                case 16: 
                            }
                            this.bitField0_ |= 2;
                            this.isExtension_ = input.readBool();
                        }
                        Object var9_10 = null;
                        this.unknownFields = unknownFields.build();
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e) {
                        throw new InvalidProtocolBufferException(e.getMessage()).setUnfinishedMessage(this);
                    }
                } catch (Throwable throwable) {
                    Object var9_11 = null;
                    this.unknownFields = unknownFields.build();
                    this.makeExtensionsImmutable();
                    throw throwable;
                }
                this.makeExtensionsImmutable();
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_google_protobuf_UninterpretedOption_NamePart_descriptor;
            }

            @Override
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_google_protobuf_UninterpretedOption_NamePart_fieldAccessorTable.ensureFieldAccessorsInitialized(NamePart.class, Builder.class);
            }

            public Parser<NamePart> getParserForType() {
                return PARSER;
            }

            @Override
            public boolean hasNamePart() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override
            public String getNamePart() {
                Object ref = this.namePart_;
                if (ref instanceof String) {
                    return (String)ref;
                }
                ByteString bs = (ByteString)ref;
                String s2 = bs.toStringUtf8();
                if (bs.isValidUtf8()) {
                    this.namePart_ = s2;
                }
                return s2;
            }

            @Override
            public ByteString getNamePartBytes() {
                Object ref = this.namePart_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String)ref);
                    this.namePart_ = b;
                    return b;
                }
                return (ByteString)ref;
            }

            @Override
            public boolean hasIsExtension() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override
            public boolean getIsExtension() {
                return this.isExtension_;
            }

            private void initFields() {
                this.namePart_ = "";
                this.isExtension_ = false;
            }

            @Override
            public final boolean isInitialized() {
                byte isInitialized = this.memoizedIsInitialized;
                if (isInitialized == 1) {
                    return true;
                }
                if (isInitialized == 0) {
                    return false;
                }
                if (!this.hasNamePart()) {
                    this.memoizedIsInitialized = 0;
                    return false;
                }
                if (!this.hasIsExtension()) {
                    this.memoizedIsInitialized = 0;
                    return false;
                }
                this.memoizedIsInitialized = 1;
                return true;
            }

            @Override
            public void writeTo(CodedOutputStream output) throws IOException {
                this.getSerializedSize();
                if ((this.bitField0_ & 1) == 1) {
                    output.writeBytes(1, this.getNamePartBytes());
                }
                if ((this.bitField0_ & 2) == 2) {
                    output.writeBool(2, this.isExtension_);
                }
                this.getUnknownFields().writeTo(output);
            }

            @Override
            public int getSerializedSize() {
                int size = this.memoizedSerializedSize;
                if (size != -1) {
                    return size;
                }
                size = 0;
                if ((this.bitField0_ & 1) == 1) {
                    size += CodedOutputStream.computeBytesSize(1, this.getNamePartBytes());
                }
                if ((this.bitField0_ & 2) == 2) {
                    size += CodedOutputStream.computeBoolSize(2, this.isExtension_);
                }
                this.memoizedSerializedSize = size += this.getUnknownFields().getSerializedSize();
                return size;
            }

            @Override
            protected Object writeReplace() throws ObjectStreamException {
                return super.writeReplace();
            }

            public static NamePart parseFrom(ByteString data) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(data);
            }

            public static NamePart parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(data, extensionRegistry);
            }

            public static NamePart parseFrom(byte[] data) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(data);
            }

            public static NamePart parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(data, extensionRegistry);
            }

            public static NamePart parseFrom(InputStream input) throws IOException {
                return PARSER.parseFrom(input);
            }

            public static NamePart parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return PARSER.parseFrom(input, extensionRegistry);
            }

            public static NamePart parseDelimitedFrom(InputStream input) throws IOException {
                return PARSER.parseDelimitedFrom(input);
            }

            public static NamePart parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return PARSER.parseDelimitedFrom(input, extensionRegistry);
            }

            public static NamePart parseFrom(CodedInputStream input) throws IOException {
                return PARSER.parseFrom(input);
            }

            public static NamePart parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return PARSER.parseFrom(input, extensionRegistry);
            }

            public static Builder newBuilder() {
                return Builder.create();
            }

            @Override
            public Builder newBuilderForType() {
                return NamePart.newBuilder();
            }

            public static Builder newBuilder(NamePart prototype) {
                return NamePart.newBuilder().mergeFrom(prototype);
            }

            @Override
            public Builder toBuilder() {
                return NamePart.newBuilder(this);
            }

            @Override
            protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
                Builder builder = new Builder(parent);
                return builder;
            }

            static {
                PARSER = new AbstractParser<NamePart>(){

                    @Override
                    public NamePart parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                        return new NamePart(input, extensionRegistry);
                    }
                };
                defaultInstance = new NamePart(true);
                defaultInstance.initFields();
            }

            /*
             * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
             */
            public static final class Builder
            extends GeneratedMessage.Builder<Builder>
            implements NamePartOrBuilder {
                private int bitField0_;
                private Object namePart_ = "";
                private boolean isExtension_;

                public static final Descriptors.Descriptor getDescriptor() {
                    return internal_static_google_protobuf_UninterpretedOption_NamePart_descriptor;
                }

                @Override
                protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                    return internal_static_google_protobuf_UninterpretedOption_NamePart_fieldAccessorTable.ensureFieldAccessorsInitialized(NamePart.class, Builder.class);
                }

                private Builder() {
                    this.maybeForceBuilderInitialization();
                }

                private Builder(GeneratedMessage.BuilderParent parent) {
                    super(parent);
                    this.maybeForceBuilderInitialization();
                }

                private void maybeForceBuilderInitialization() {
                    if (GeneratedMessage.alwaysUseFieldBuilders) {
                        // empty if block
                    }
                }

                private static Builder create() {
                    return new Builder();
                }

                @Override
                public Builder clear() {
                    super.clear();
                    this.namePart_ = "";
                    this.bitField0_ &= 0xFFFFFFFE;
                    this.isExtension_ = false;
                    this.bitField0_ &= 0xFFFFFFFD;
                    return this;
                }

                @Override
                public Builder clone() {
                    return Builder.create().mergeFrom(this.buildPartial());
                }

                @Override
                public Descriptors.Descriptor getDescriptorForType() {
                    return internal_static_google_protobuf_UninterpretedOption_NamePart_descriptor;
                }

                @Override
                public NamePart getDefaultInstanceForType() {
                    return NamePart.getDefaultInstance();
                }

                @Override
                public NamePart build() {
                    NamePart result = this.buildPartial();
                    if (!result.isInitialized()) {
                        throw Builder.newUninitializedMessageException(result);
                    }
                    return result;
                }

                @Override
                public NamePart buildPartial() {
                    NamePart result = new NamePart(this);
                    int from_bitField0_ = this.bitField0_;
                    int to_bitField0_ = 0;
                    if ((from_bitField0_ & 1) == 1) {
                        to_bitField0_ |= 1;
                    }
                    result.namePart_ = this.namePart_;
                    if ((from_bitField0_ & 2) == 2) {
                        to_bitField0_ |= 2;
                    }
                    result.isExtension_ = this.isExtension_;
                    result.bitField0_ = to_bitField0_;
                    this.onBuilt();
                    return result;
                }

                @Override
                public Builder mergeFrom(Message other) {
                    if (other instanceof NamePart) {
                        return this.mergeFrom((NamePart)other);
                    }
                    super.mergeFrom(other);
                    return this;
                }

                public Builder mergeFrom(NamePart other) {
                    if (other == NamePart.getDefaultInstance()) {
                        return this;
                    }
                    if (other.hasNamePart()) {
                        this.bitField0_ |= 1;
                        this.namePart_ = other.namePart_;
                        this.onChanged();
                    }
                    if (other.hasIsExtension()) {
                        this.setIsExtension(other.getIsExtension());
                    }
                    this.mergeUnknownFields(other.getUnknownFields());
                    return this;
                }

                @Override
                public final boolean isInitialized() {
                    if (!this.hasNamePart()) {
                        return false;
                    }
                    return this.hasIsExtension();
                }

                /*
                 * Enabled force condition propagation
                 * Lifted jumps to return sites
                 */
                @Override
                public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                    NamePart parsedMessage = null;
                    try {
                        try {
                            parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
                        } catch (InvalidProtocolBufferException e) {
                            parsedMessage = (NamePart)e.getUnfinishedMessage();
                            throw e;
                        }
                        Object var6_4 = null;
                        if (parsedMessage == null) return this;
                        this.mergeFrom(parsedMessage);
                        return this;
                    } catch (Throwable throwable) {
                        Object var6_5 = null;
                        if (parsedMessage == null) throw throwable;
                        this.mergeFrom(parsedMessage);
                        throw throwable;
                    }
                }

                @Override
                public boolean hasNamePart() {
                    return (this.bitField0_ & 1) == 1;
                }

                @Override
                public String getNamePart() {
                    Object ref = this.namePart_;
                    if (!(ref instanceof String)) {
                        ByteString bs = (ByteString)ref;
                        String s2 = bs.toStringUtf8();
                        if (bs.isValidUtf8()) {
                            this.namePart_ = s2;
                        }
                        return s2;
                    }
                    return (String)ref;
                }

                @Override
                public ByteString getNamePartBytes() {
                    Object ref = this.namePart_;
                    if (ref instanceof String) {
                        ByteString b = ByteString.copyFromUtf8((String)ref);
                        this.namePart_ = b;
                        return b;
                    }
                    return (ByteString)ref;
                }

                public Builder setNamePart(String value) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.bitField0_ |= 1;
                    this.namePart_ = value;
                    this.onChanged();
                    return this;
                }

                public Builder clearNamePart() {
                    this.bitField0_ &= 0xFFFFFFFE;
                    this.namePart_ = NamePart.getDefaultInstance().getNamePart();
                    this.onChanged();
                    return this;
                }

                public Builder setNamePartBytes(ByteString value) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.bitField0_ |= 1;
                    this.namePart_ = value;
                    this.onChanged();
                    return this;
                }

                @Override
                public boolean hasIsExtension() {
                    return (this.bitField0_ & 2) == 2;
                }

                @Override
                public boolean getIsExtension() {
                    return this.isExtension_;
                }

                public Builder setIsExtension(boolean value) {
                    this.bitField0_ |= 2;
                    this.isExtension_ = value;
                    this.onChanged();
                    return this;
                }

                public Builder clearIsExtension() {
                    this.bitField0_ &= 0xFFFFFFFD;
                    this.isExtension_ = false;
                    this.onChanged();
                    return this;
                }
            }
        }

        public static interface NamePartOrBuilder
        extends MessageOrBuilder {
            public boolean hasNamePart();

            public String getNamePart();

            public ByteString getNamePartBytes();

            public boolean hasIsExtension();

            public boolean getIsExtension();
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static interface UninterpretedOptionOrBuilder
    extends MessageOrBuilder {
        public List<UninterpretedOption.NamePart> getNameList();

        public UninterpretedOption.NamePart getName(int var1);

        public int getNameCount();

        public List<? extends UninterpretedOption.NamePartOrBuilder> getNameOrBuilderList();

        public UninterpretedOption.NamePartOrBuilder getNameOrBuilder(int var1);

        public boolean hasIdentifierValue();

        public String getIdentifierValue();

        public ByteString getIdentifierValueBytes();

        public boolean hasPositiveIntValue();

        public long getPositiveIntValue();

        public boolean hasNegativeIntValue();

        public long getNegativeIntValue();

        public boolean hasDoubleValue();

        public double getDoubleValue();

        public boolean hasStringValue();

        public ByteString getStringValue();

        public boolean hasAggregateValue();

        public String getAggregateValue();

        public ByteString getAggregateValueBytes();
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static final class MethodOptions
    extends GeneratedMessage.ExtendableMessage<MethodOptions>
    implements MethodOptionsOrBuilder {
        private static final MethodOptions defaultInstance;
        private final UnknownFieldSet unknownFields;
        public static Parser<MethodOptions> PARSER;
        private int bitField0_;
        public static final int DEPRECATED_FIELD_NUMBER = 33;
        private boolean deprecated_;
        public static final int UNINTERPRETED_OPTION_FIELD_NUMBER = 999;
        private List<UninterpretedOption> uninterpretedOption_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private static final long serialVersionUID = 0L;

        private MethodOptions(GeneratedMessage.ExtendableBuilder<MethodOptions, ?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte)-1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private MethodOptions(boolean noInit) {
            this.memoizedIsInitialized = (byte)-1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static MethodOptions getDefaultInstance() {
            return defaultInstance;
        }

        @Override
        public MethodOptions getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        private MethodOptions(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            UnknownFieldSet.Builder unknownFields;
            block14: {
                this.memoizedIsInitialized = (byte)-1;
                this.memoizedSerializedSize = -1;
                this.initFields();
                int mutable_bitField0_ = 0;
                unknownFields = UnknownFieldSet.newBuilder();
                try {
                    try {
                        boolean done = false;
                        block10: while (!done) {
                            int tag = input.readTag();
                            switch (tag) {
                                case 0: {
                                    done = true;
                                    continue block10;
                                }
                                default: {
                                    if (this.parseUnknownField(input, unknownFields, extensionRegistry, tag)) continue block10;
                                    done = true;
                                    continue block10;
                                }
                                case 264: {
                                    this.bitField0_ |= 1;
                                    this.deprecated_ = input.readBool();
                                    continue block10;
                                }
                                case 7994: 
                            }
                            if ((mutable_bitField0_ & 2) != 2) {
                                this.uninterpretedOption_ = new ArrayList<UninterpretedOption>();
                                mutable_bitField0_ |= 2;
                            }
                            this.uninterpretedOption_.add(input.readMessage(UninterpretedOption.PARSER, extensionRegistry));
                        }
                        Object var8_9 = null;
                        if ((mutable_bitField0_ & 2) != 2) break block14;
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e) {
                        throw new InvalidProtocolBufferException(e.getMessage()).setUnfinishedMessage(this);
                    }
                } catch (Throwable throwable) {
                    Object var8_10 = null;
                    if ((mutable_bitField0_ & 2) == 2) {
                        this.uninterpretedOption_ = Collections.unmodifiableList(this.uninterpretedOption_);
                    }
                    this.unknownFields = unknownFields.build();
                    this.makeExtensionsImmutable();
                    throw throwable;
                }
                this.uninterpretedOption_ = Collections.unmodifiableList(this.uninterpretedOption_);
            }
            this.unknownFields = unknownFields.build();
            this.makeExtensionsImmutable();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return internal_static_google_protobuf_MethodOptions_descriptor;
        }

        @Override
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return internal_static_google_protobuf_MethodOptions_fieldAccessorTable.ensureFieldAccessorsInitialized(MethodOptions.class, Builder.class);
        }

        public Parser<MethodOptions> getParserForType() {
            return PARSER;
        }

        @Override
        public boolean hasDeprecated() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override
        public boolean getDeprecated() {
            return this.deprecated_;
        }

        @Override
        public List<UninterpretedOption> getUninterpretedOptionList() {
            return this.uninterpretedOption_;
        }

        @Override
        public List<? extends UninterpretedOptionOrBuilder> getUninterpretedOptionOrBuilderList() {
            return this.uninterpretedOption_;
        }

        @Override
        public int getUninterpretedOptionCount() {
            return this.uninterpretedOption_.size();
        }

        @Override
        public UninterpretedOption getUninterpretedOption(int index) {
            return this.uninterpretedOption_.get(index);
        }

        @Override
        public UninterpretedOptionOrBuilder getUninterpretedOptionOrBuilder(int index) {
            return this.uninterpretedOption_.get(index);
        }

        private void initFields() {
            this.deprecated_ = false;
            this.uninterpretedOption_ = Collections.emptyList();
        }

        @Override
        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            for (int i = 0; i < this.getUninterpretedOptionCount(); ++i) {
                if (this.getUninterpretedOption(i).isInitialized()) continue;
                this.memoizedIsInitialized = 0;
                return false;
            }
            if (!this.extensionsAreInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        @Override
        public void writeTo(CodedOutputStream output) throws IOException {
            this.getSerializedSize();
            GeneratedMessage.ExtendableMessage.ExtensionWriter extensionWriter = this.newExtensionWriter();
            if ((this.bitField0_ & 1) == 1) {
                output.writeBool(33, this.deprecated_);
            }
            for (int i = 0; i < this.uninterpretedOption_.size(); ++i) {
                output.writeMessage(999, this.uninterpretedOption_.get(i));
            }
            extensionWriter.writeUntil(0x20000000, output);
            this.getUnknownFields().writeTo(output);
        }

        @Override
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            size = 0;
            if ((this.bitField0_ & 1) == 1) {
                size += CodedOutputStream.computeBoolSize(33, this.deprecated_);
            }
            for (int i = 0; i < this.uninterpretedOption_.size(); ++i) {
                size += CodedOutputStream.computeMessageSize(999, this.uninterpretedOption_.get(i));
            }
            size += this.extensionsSerializedSize();
            this.memoizedSerializedSize = size += this.getUnknownFields().getSerializedSize();
            return size;
        }

        @Override
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static MethodOptions parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static MethodOptions parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static MethodOptions parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static MethodOptions parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static MethodOptions parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static MethodOptions parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static MethodOptions parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static MethodOptions parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static MethodOptions parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static MethodOptions parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override
        public Builder newBuilderForType() {
            return MethodOptions.newBuilder();
        }

        public static Builder newBuilder(MethodOptions prototype) {
            return MethodOptions.newBuilder().mergeFrom(prototype);
        }

        @Override
        public Builder toBuilder() {
            return MethodOptions.newBuilder(this);
        }

        @Override
        protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        static {
            PARSER = new AbstractParser<MethodOptions>(){

                @Override
                public MethodOptions parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                    return new MethodOptions(input, extensionRegistry);
                }
            };
            defaultInstance = new MethodOptions(true);
            defaultInstance.initFields();
        }

        /*
         * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
         */
        public static final class Builder
        extends GeneratedMessage.ExtendableBuilder<MethodOptions, Builder>
        implements MethodOptionsOrBuilder {
            private int bitField0_;
            private boolean deprecated_;
            private List<UninterpretedOption> uninterpretedOption_ = Collections.emptyList();
            private RepeatedFieldBuilder<UninterpretedOption, UninterpretedOption.Builder, UninterpretedOptionOrBuilder> uninterpretedOptionBuilder_;

            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_google_protobuf_MethodOptions_descriptor;
            }

            @Override
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_google_protobuf_MethodOptions_fieldAccessorTable.ensureFieldAccessorsInitialized(MethodOptions.class, Builder.class);
            }

            private Builder() {
                this.maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessage.BuilderParent parent) {
                super(parent);
                this.maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                if (GeneratedMessage.alwaysUseFieldBuilders) {
                    this.getUninterpretedOptionFieldBuilder();
                }
            }

            private static Builder create() {
                return new Builder();
            }

            @Override
            public Builder clear() {
                super.clear();
                this.deprecated_ = false;
                this.bitField0_ &= 0xFFFFFFFE;
                if (this.uninterpretedOptionBuilder_ == null) {
                    this.uninterpretedOption_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFFFFD;
                } else {
                    this.uninterpretedOptionBuilder_.clear();
                }
                return this;
            }

            @Override
            public Builder clone() {
                return Builder.create().mergeFrom(this.buildPartial());
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return internal_static_google_protobuf_MethodOptions_descriptor;
            }

            @Override
            public MethodOptions getDefaultInstanceForType() {
                return MethodOptions.getDefaultInstance();
            }

            @Override
            public MethodOptions build() {
                MethodOptions result = this.buildPartial();
                if (!result.isInitialized()) {
                    throw Builder.newUninitializedMessageException(result);
                }
                return result;
            }

            @Override
            public MethodOptions buildPartial() {
                MethodOptions result = new MethodOptions(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ |= 1;
                }
                result.deprecated_ = this.deprecated_;
                if (this.uninterpretedOptionBuilder_ == null) {
                    if ((this.bitField0_ & 2) == 2) {
                        this.uninterpretedOption_ = Collections.unmodifiableList(this.uninterpretedOption_);
                        this.bitField0_ &= 0xFFFFFFFD;
                    }
                    result.uninterpretedOption_ = this.uninterpretedOption_;
                } else {
                    result.uninterpretedOption_ = this.uninterpretedOptionBuilder_.build();
                }
                result.bitField0_ = to_bitField0_;
                this.onBuilt();
                return result;
            }

            @Override
            public Builder mergeFrom(Message other) {
                if (other instanceof MethodOptions) {
                    return this.mergeFrom((MethodOptions)other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(MethodOptions other) {
                if (other == MethodOptions.getDefaultInstance()) {
                    return this;
                }
                if (other.hasDeprecated()) {
                    this.setDeprecated(other.getDeprecated());
                }
                if (this.uninterpretedOptionBuilder_ == null) {
                    if (!other.uninterpretedOption_.isEmpty()) {
                        if (this.uninterpretedOption_.isEmpty()) {
                            this.uninterpretedOption_ = other.uninterpretedOption_;
                            this.bitField0_ &= 0xFFFFFFFD;
                        } else {
                            this.ensureUninterpretedOptionIsMutable();
                            this.uninterpretedOption_.addAll(other.uninterpretedOption_);
                        }
                        this.onChanged();
                    }
                } else if (!other.uninterpretedOption_.isEmpty()) {
                    if (this.uninterpretedOptionBuilder_.isEmpty()) {
                        this.uninterpretedOptionBuilder_.dispose();
                        this.uninterpretedOptionBuilder_ = null;
                        this.uninterpretedOption_ = other.uninterpretedOption_;
                        this.bitField0_ &= 0xFFFFFFFD;
                        this.uninterpretedOptionBuilder_ = GeneratedMessage.alwaysUseFieldBuilders ? this.getUninterpretedOptionFieldBuilder() : null;
                    } else {
                        this.uninterpretedOptionBuilder_.addAllMessages(other.uninterpretedOption_);
                    }
                }
                this.mergeExtensionFields(other);
                this.mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            @Override
            public final boolean isInitialized() {
                for (int i = 0; i < this.getUninterpretedOptionCount(); ++i) {
                    if (this.getUninterpretedOption(i).isInitialized()) continue;
                    return false;
                }
                return this.extensionsAreInitialized();
            }

            /*
             * Enabled force condition propagation
             * Lifted jumps to return sites
             */
            @Override
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                MethodOptions parsedMessage = null;
                try {
                    try {
                        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
                    } catch (InvalidProtocolBufferException e) {
                        parsedMessage = (MethodOptions)e.getUnfinishedMessage();
                        throw e;
                    }
                    Object var6_4 = null;
                    if (parsedMessage == null) return this;
                    this.mergeFrom(parsedMessage);
                    return this;
                } catch (Throwable throwable) {
                    Object var6_5 = null;
                    if (parsedMessage == null) throw throwable;
                    this.mergeFrom(parsedMessage);
                    throw throwable;
                }
            }

            @Override
            public boolean hasDeprecated() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override
            public boolean getDeprecated() {
                return this.deprecated_;
            }

            public Builder setDeprecated(boolean value) {
                this.bitField0_ |= 1;
                this.deprecated_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearDeprecated() {
                this.bitField0_ &= 0xFFFFFFFE;
                this.deprecated_ = false;
                this.onChanged();
                return this;
            }

            private void ensureUninterpretedOptionIsMutable() {
                if ((this.bitField0_ & 2) != 2) {
                    this.uninterpretedOption_ = new ArrayList<UninterpretedOption>(this.uninterpretedOption_);
                    this.bitField0_ |= 2;
                }
            }

            @Override
            public List<UninterpretedOption> getUninterpretedOptionList() {
                if (this.uninterpretedOptionBuilder_ == null) {
                    return Collections.unmodifiableList(this.uninterpretedOption_);
                }
                return this.uninterpretedOptionBuilder_.getMessageList();
            }

            @Override
            public int getUninterpretedOptionCount() {
                if (this.uninterpretedOptionBuilder_ == null) {
                    return this.uninterpretedOption_.size();
                }
                return this.uninterpretedOptionBuilder_.getCount();
            }

            @Override
            public UninterpretedOption getUninterpretedOption(int index) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    return this.uninterpretedOption_.get(index);
                }
                return this.uninterpretedOptionBuilder_.getMessage(index);
            }

            public Builder setUninterpretedOption(int index, UninterpretedOption value) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureUninterpretedOptionIsMutable();
                    this.uninterpretedOption_.set(index, value);
                    this.onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.setMessage(index, value);
                }
                return this;
            }

            public Builder setUninterpretedOption(int index, UninterpretedOption.Builder builderForValue) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    this.ensureUninterpretedOptionIsMutable();
                    this.uninterpretedOption_.set(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.setMessage(index, builderForValue.build());
                }
                return this;
            }

            public Builder addUninterpretedOption(UninterpretedOption value) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureUninterpretedOptionIsMutable();
                    this.uninterpretedOption_.add(value);
                    this.onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.addMessage(value);
                }
                return this;
            }

            public Builder addUninterpretedOption(int index, UninterpretedOption value) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureUninterpretedOptionIsMutable();
                    this.uninterpretedOption_.add(index, value);
                    this.onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.addMessage(index, value);
                }
                return this;
            }

            public Builder addUninterpretedOption(UninterpretedOption.Builder builderForValue) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    this.ensureUninterpretedOptionIsMutable();
                    this.uninterpretedOption_.add(builderForValue.build());
                    this.onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.addMessage(builderForValue.build());
                }
                return this;
            }

            public Builder addUninterpretedOption(int index, UninterpretedOption.Builder builderForValue) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    this.ensureUninterpretedOptionIsMutable();
                    this.uninterpretedOption_.add(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.addMessage(index, builderForValue.build());
                }
                return this;
            }

            public Builder addAllUninterpretedOption(Iterable<? extends UninterpretedOption> values2) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    this.ensureUninterpretedOptionIsMutable();
                    AbstractMessageLite.Builder.addAll(values2, this.uninterpretedOption_);
                    this.onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.addAllMessages(values2);
                }
                return this;
            }

            public Builder clearUninterpretedOption() {
                if (this.uninterpretedOptionBuilder_ == null) {
                    this.uninterpretedOption_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFFFFD;
                    this.onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.clear();
                }
                return this;
            }

            public Builder removeUninterpretedOption(int index) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    this.ensureUninterpretedOptionIsMutable();
                    this.uninterpretedOption_.remove(index);
                    this.onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.remove(index);
                }
                return this;
            }

            public UninterpretedOption.Builder getUninterpretedOptionBuilder(int index) {
                return this.getUninterpretedOptionFieldBuilder().getBuilder(index);
            }

            @Override
            public UninterpretedOptionOrBuilder getUninterpretedOptionOrBuilder(int index) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    return this.uninterpretedOption_.get(index);
                }
                return this.uninterpretedOptionBuilder_.getMessageOrBuilder(index);
            }

            @Override
            public List<? extends UninterpretedOptionOrBuilder> getUninterpretedOptionOrBuilderList() {
                if (this.uninterpretedOptionBuilder_ != null) {
                    return this.uninterpretedOptionBuilder_.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.uninterpretedOption_);
            }

            public UninterpretedOption.Builder addUninterpretedOptionBuilder() {
                return this.getUninterpretedOptionFieldBuilder().addBuilder(UninterpretedOption.getDefaultInstance());
            }

            public UninterpretedOption.Builder addUninterpretedOptionBuilder(int index) {
                return this.getUninterpretedOptionFieldBuilder().addBuilder(index, UninterpretedOption.getDefaultInstance());
            }

            public List<UninterpretedOption.Builder> getUninterpretedOptionBuilderList() {
                return this.getUninterpretedOptionFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilder<UninterpretedOption, UninterpretedOption.Builder, UninterpretedOptionOrBuilder> getUninterpretedOptionFieldBuilder() {
                if (this.uninterpretedOptionBuilder_ == null) {
                    this.uninterpretedOptionBuilder_ = new RepeatedFieldBuilder(this.uninterpretedOption_, (this.bitField0_ & 2) == 2, this.getParentForChildren(), this.isClean());
                    this.uninterpretedOption_ = null;
                }
                return this.uninterpretedOptionBuilder_;
            }
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static interface MethodOptionsOrBuilder
    extends GeneratedMessage.ExtendableMessageOrBuilder<MethodOptions> {
        public boolean hasDeprecated();

        public boolean getDeprecated();

        public List<UninterpretedOption> getUninterpretedOptionList();

        public UninterpretedOption getUninterpretedOption(int var1);

        public int getUninterpretedOptionCount();

        public List<? extends UninterpretedOptionOrBuilder> getUninterpretedOptionOrBuilderList();

        public UninterpretedOptionOrBuilder getUninterpretedOptionOrBuilder(int var1);
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static final class ServiceOptions
    extends GeneratedMessage.ExtendableMessage<ServiceOptions>
    implements ServiceOptionsOrBuilder {
        private static final ServiceOptions defaultInstance;
        private final UnknownFieldSet unknownFields;
        public static Parser<ServiceOptions> PARSER;
        private int bitField0_;
        public static final int DEPRECATED_FIELD_NUMBER = 33;
        private boolean deprecated_;
        public static final int UNINTERPRETED_OPTION_FIELD_NUMBER = 999;
        private List<UninterpretedOption> uninterpretedOption_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private static final long serialVersionUID = 0L;

        private ServiceOptions(GeneratedMessage.ExtendableBuilder<ServiceOptions, ?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte)-1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private ServiceOptions(boolean noInit) {
            this.memoizedIsInitialized = (byte)-1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static ServiceOptions getDefaultInstance() {
            return defaultInstance;
        }

        @Override
        public ServiceOptions getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        private ServiceOptions(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            UnknownFieldSet.Builder unknownFields;
            block14: {
                this.memoizedIsInitialized = (byte)-1;
                this.memoizedSerializedSize = -1;
                this.initFields();
                int mutable_bitField0_ = 0;
                unknownFields = UnknownFieldSet.newBuilder();
                try {
                    try {
                        boolean done = false;
                        block10: while (!done) {
                            int tag = input.readTag();
                            switch (tag) {
                                case 0: {
                                    done = true;
                                    continue block10;
                                }
                                default: {
                                    if (this.parseUnknownField(input, unknownFields, extensionRegistry, tag)) continue block10;
                                    done = true;
                                    continue block10;
                                }
                                case 264: {
                                    this.bitField0_ |= 1;
                                    this.deprecated_ = input.readBool();
                                    continue block10;
                                }
                                case 7994: 
                            }
                            if ((mutable_bitField0_ & 2) != 2) {
                                this.uninterpretedOption_ = new ArrayList<UninterpretedOption>();
                                mutable_bitField0_ |= 2;
                            }
                            this.uninterpretedOption_.add(input.readMessage(UninterpretedOption.PARSER, extensionRegistry));
                        }
                        Object var8_9 = null;
                        if ((mutable_bitField0_ & 2) != 2) break block14;
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e) {
                        throw new InvalidProtocolBufferException(e.getMessage()).setUnfinishedMessage(this);
                    }
                } catch (Throwable throwable) {
                    Object var8_10 = null;
                    if ((mutable_bitField0_ & 2) == 2) {
                        this.uninterpretedOption_ = Collections.unmodifiableList(this.uninterpretedOption_);
                    }
                    this.unknownFields = unknownFields.build();
                    this.makeExtensionsImmutable();
                    throw throwable;
                }
                this.uninterpretedOption_ = Collections.unmodifiableList(this.uninterpretedOption_);
            }
            this.unknownFields = unknownFields.build();
            this.makeExtensionsImmutable();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return internal_static_google_protobuf_ServiceOptions_descriptor;
        }

        @Override
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return internal_static_google_protobuf_ServiceOptions_fieldAccessorTable.ensureFieldAccessorsInitialized(ServiceOptions.class, Builder.class);
        }

        public Parser<ServiceOptions> getParserForType() {
            return PARSER;
        }

        @Override
        public boolean hasDeprecated() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override
        public boolean getDeprecated() {
            return this.deprecated_;
        }

        @Override
        public List<UninterpretedOption> getUninterpretedOptionList() {
            return this.uninterpretedOption_;
        }

        @Override
        public List<? extends UninterpretedOptionOrBuilder> getUninterpretedOptionOrBuilderList() {
            return this.uninterpretedOption_;
        }

        @Override
        public int getUninterpretedOptionCount() {
            return this.uninterpretedOption_.size();
        }

        @Override
        public UninterpretedOption getUninterpretedOption(int index) {
            return this.uninterpretedOption_.get(index);
        }

        @Override
        public UninterpretedOptionOrBuilder getUninterpretedOptionOrBuilder(int index) {
            return this.uninterpretedOption_.get(index);
        }

        private void initFields() {
            this.deprecated_ = false;
            this.uninterpretedOption_ = Collections.emptyList();
        }

        @Override
        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            for (int i = 0; i < this.getUninterpretedOptionCount(); ++i) {
                if (this.getUninterpretedOption(i).isInitialized()) continue;
                this.memoizedIsInitialized = 0;
                return false;
            }
            if (!this.extensionsAreInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        @Override
        public void writeTo(CodedOutputStream output) throws IOException {
            this.getSerializedSize();
            GeneratedMessage.ExtendableMessage.ExtensionWriter extensionWriter = this.newExtensionWriter();
            if ((this.bitField0_ & 1) == 1) {
                output.writeBool(33, this.deprecated_);
            }
            for (int i = 0; i < this.uninterpretedOption_.size(); ++i) {
                output.writeMessage(999, this.uninterpretedOption_.get(i));
            }
            extensionWriter.writeUntil(0x20000000, output);
            this.getUnknownFields().writeTo(output);
        }

        @Override
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            size = 0;
            if ((this.bitField0_ & 1) == 1) {
                size += CodedOutputStream.computeBoolSize(33, this.deprecated_);
            }
            for (int i = 0; i < this.uninterpretedOption_.size(); ++i) {
                size += CodedOutputStream.computeMessageSize(999, this.uninterpretedOption_.get(i));
            }
            size += this.extensionsSerializedSize();
            this.memoizedSerializedSize = size += this.getUnknownFields().getSerializedSize();
            return size;
        }

        @Override
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static ServiceOptions parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static ServiceOptions parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static ServiceOptions parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static ServiceOptions parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static ServiceOptions parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static ServiceOptions parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static ServiceOptions parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static ServiceOptions parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static ServiceOptions parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static ServiceOptions parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override
        public Builder newBuilderForType() {
            return ServiceOptions.newBuilder();
        }

        public static Builder newBuilder(ServiceOptions prototype) {
            return ServiceOptions.newBuilder().mergeFrom(prototype);
        }

        @Override
        public Builder toBuilder() {
            return ServiceOptions.newBuilder(this);
        }

        @Override
        protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        static {
            PARSER = new AbstractParser<ServiceOptions>(){

                @Override
                public ServiceOptions parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                    return new ServiceOptions(input, extensionRegistry);
                }
            };
            defaultInstance = new ServiceOptions(true);
            defaultInstance.initFields();
        }

        /*
         * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
         */
        public static final class Builder
        extends GeneratedMessage.ExtendableBuilder<ServiceOptions, Builder>
        implements ServiceOptionsOrBuilder {
            private int bitField0_;
            private boolean deprecated_;
            private List<UninterpretedOption> uninterpretedOption_ = Collections.emptyList();
            private RepeatedFieldBuilder<UninterpretedOption, UninterpretedOption.Builder, UninterpretedOptionOrBuilder> uninterpretedOptionBuilder_;

            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_google_protobuf_ServiceOptions_descriptor;
            }

            @Override
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_google_protobuf_ServiceOptions_fieldAccessorTable.ensureFieldAccessorsInitialized(ServiceOptions.class, Builder.class);
            }

            private Builder() {
                this.maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessage.BuilderParent parent) {
                super(parent);
                this.maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                if (GeneratedMessage.alwaysUseFieldBuilders) {
                    this.getUninterpretedOptionFieldBuilder();
                }
            }

            private static Builder create() {
                return new Builder();
            }

            @Override
            public Builder clear() {
                super.clear();
                this.deprecated_ = false;
                this.bitField0_ &= 0xFFFFFFFE;
                if (this.uninterpretedOptionBuilder_ == null) {
                    this.uninterpretedOption_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFFFFD;
                } else {
                    this.uninterpretedOptionBuilder_.clear();
                }
                return this;
            }

            @Override
            public Builder clone() {
                return Builder.create().mergeFrom(this.buildPartial());
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return internal_static_google_protobuf_ServiceOptions_descriptor;
            }

            @Override
            public ServiceOptions getDefaultInstanceForType() {
                return ServiceOptions.getDefaultInstance();
            }

            @Override
            public ServiceOptions build() {
                ServiceOptions result = this.buildPartial();
                if (!result.isInitialized()) {
                    throw Builder.newUninitializedMessageException(result);
                }
                return result;
            }

            @Override
            public ServiceOptions buildPartial() {
                ServiceOptions result = new ServiceOptions(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ |= 1;
                }
                result.deprecated_ = this.deprecated_;
                if (this.uninterpretedOptionBuilder_ == null) {
                    if ((this.bitField0_ & 2) == 2) {
                        this.uninterpretedOption_ = Collections.unmodifiableList(this.uninterpretedOption_);
                        this.bitField0_ &= 0xFFFFFFFD;
                    }
                    result.uninterpretedOption_ = this.uninterpretedOption_;
                } else {
                    result.uninterpretedOption_ = this.uninterpretedOptionBuilder_.build();
                }
                result.bitField0_ = to_bitField0_;
                this.onBuilt();
                return result;
            }

            @Override
            public Builder mergeFrom(Message other) {
                if (other instanceof ServiceOptions) {
                    return this.mergeFrom((ServiceOptions)other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(ServiceOptions other) {
                if (other == ServiceOptions.getDefaultInstance()) {
                    return this;
                }
                if (other.hasDeprecated()) {
                    this.setDeprecated(other.getDeprecated());
                }
                if (this.uninterpretedOptionBuilder_ == null) {
                    if (!other.uninterpretedOption_.isEmpty()) {
                        if (this.uninterpretedOption_.isEmpty()) {
                            this.uninterpretedOption_ = other.uninterpretedOption_;
                            this.bitField0_ &= 0xFFFFFFFD;
                        } else {
                            this.ensureUninterpretedOptionIsMutable();
                            this.uninterpretedOption_.addAll(other.uninterpretedOption_);
                        }
                        this.onChanged();
                    }
                } else if (!other.uninterpretedOption_.isEmpty()) {
                    if (this.uninterpretedOptionBuilder_.isEmpty()) {
                        this.uninterpretedOptionBuilder_.dispose();
                        this.uninterpretedOptionBuilder_ = null;
                        this.uninterpretedOption_ = other.uninterpretedOption_;
                        this.bitField0_ &= 0xFFFFFFFD;
                        this.uninterpretedOptionBuilder_ = GeneratedMessage.alwaysUseFieldBuilders ? this.getUninterpretedOptionFieldBuilder() : null;
                    } else {
                        this.uninterpretedOptionBuilder_.addAllMessages(other.uninterpretedOption_);
                    }
                }
                this.mergeExtensionFields(other);
                this.mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            @Override
            public final boolean isInitialized() {
                for (int i = 0; i < this.getUninterpretedOptionCount(); ++i) {
                    if (this.getUninterpretedOption(i).isInitialized()) continue;
                    return false;
                }
                return this.extensionsAreInitialized();
            }

            /*
             * Enabled force condition propagation
             * Lifted jumps to return sites
             */
            @Override
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                ServiceOptions parsedMessage = null;
                try {
                    try {
                        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
                    } catch (InvalidProtocolBufferException e) {
                        parsedMessage = (ServiceOptions)e.getUnfinishedMessage();
                        throw e;
                    }
                    Object var6_4 = null;
                    if (parsedMessage == null) return this;
                    this.mergeFrom(parsedMessage);
                    return this;
                } catch (Throwable throwable) {
                    Object var6_5 = null;
                    if (parsedMessage == null) throw throwable;
                    this.mergeFrom(parsedMessage);
                    throw throwable;
                }
            }

            @Override
            public boolean hasDeprecated() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override
            public boolean getDeprecated() {
                return this.deprecated_;
            }

            public Builder setDeprecated(boolean value) {
                this.bitField0_ |= 1;
                this.deprecated_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearDeprecated() {
                this.bitField0_ &= 0xFFFFFFFE;
                this.deprecated_ = false;
                this.onChanged();
                return this;
            }

            private void ensureUninterpretedOptionIsMutable() {
                if ((this.bitField0_ & 2) != 2) {
                    this.uninterpretedOption_ = new ArrayList<UninterpretedOption>(this.uninterpretedOption_);
                    this.bitField0_ |= 2;
                }
            }

            @Override
            public List<UninterpretedOption> getUninterpretedOptionList() {
                if (this.uninterpretedOptionBuilder_ == null) {
                    return Collections.unmodifiableList(this.uninterpretedOption_);
                }
                return this.uninterpretedOptionBuilder_.getMessageList();
            }

            @Override
            public int getUninterpretedOptionCount() {
                if (this.uninterpretedOptionBuilder_ == null) {
                    return this.uninterpretedOption_.size();
                }
                return this.uninterpretedOptionBuilder_.getCount();
            }

            @Override
            public UninterpretedOption getUninterpretedOption(int index) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    return this.uninterpretedOption_.get(index);
                }
                return this.uninterpretedOptionBuilder_.getMessage(index);
            }

            public Builder setUninterpretedOption(int index, UninterpretedOption value) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureUninterpretedOptionIsMutable();
                    this.uninterpretedOption_.set(index, value);
                    this.onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.setMessage(index, value);
                }
                return this;
            }

            public Builder setUninterpretedOption(int index, UninterpretedOption.Builder builderForValue) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    this.ensureUninterpretedOptionIsMutable();
                    this.uninterpretedOption_.set(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.setMessage(index, builderForValue.build());
                }
                return this;
            }

            public Builder addUninterpretedOption(UninterpretedOption value) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureUninterpretedOptionIsMutable();
                    this.uninterpretedOption_.add(value);
                    this.onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.addMessage(value);
                }
                return this;
            }

            public Builder addUninterpretedOption(int index, UninterpretedOption value) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureUninterpretedOptionIsMutable();
                    this.uninterpretedOption_.add(index, value);
                    this.onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.addMessage(index, value);
                }
                return this;
            }

            public Builder addUninterpretedOption(UninterpretedOption.Builder builderForValue) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    this.ensureUninterpretedOptionIsMutable();
                    this.uninterpretedOption_.add(builderForValue.build());
                    this.onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.addMessage(builderForValue.build());
                }
                return this;
            }

            public Builder addUninterpretedOption(int index, UninterpretedOption.Builder builderForValue) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    this.ensureUninterpretedOptionIsMutable();
                    this.uninterpretedOption_.add(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.addMessage(index, builderForValue.build());
                }
                return this;
            }

            public Builder addAllUninterpretedOption(Iterable<? extends UninterpretedOption> values2) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    this.ensureUninterpretedOptionIsMutable();
                    AbstractMessageLite.Builder.addAll(values2, this.uninterpretedOption_);
                    this.onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.addAllMessages(values2);
                }
                return this;
            }

            public Builder clearUninterpretedOption() {
                if (this.uninterpretedOptionBuilder_ == null) {
                    this.uninterpretedOption_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFFFFD;
                    this.onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.clear();
                }
                return this;
            }

            public Builder removeUninterpretedOption(int index) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    this.ensureUninterpretedOptionIsMutable();
                    this.uninterpretedOption_.remove(index);
                    this.onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.remove(index);
                }
                return this;
            }

            public UninterpretedOption.Builder getUninterpretedOptionBuilder(int index) {
                return this.getUninterpretedOptionFieldBuilder().getBuilder(index);
            }

            @Override
            public UninterpretedOptionOrBuilder getUninterpretedOptionOrBuilder(int index) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    return this.uninterpretedOption_.get(index);
                }
                return this.uninterpretedOptionBuilder_.getMessageOrBuilder(index);
            }

            @Override
            public List<? extends UninterpretedOptionOrBuilder> getUninterpretedOptionOrBuilderList() {
                if (this.uninterpretedOptionBuilder_ != null) {
                    return this.uninterpretedOptionBuilder_.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.uninterpretedOption_);
            }

            public UninterpretedOption.Builder addUninterpretedOptionBuilder() {
                return this.getUninterpretedOptionFieldBuilder().addBuilder(UninterpretedOption.getDefaultInstance());
            }

            public UninterpretedOption.Builder addUninterpretedOptionBuilder(int index) {
                return this.getUninterpretedOptionFieldBuilder().addBuilder(index, UninterpretedOption.getDefaultInstance());
            }

            public List<UninterpretedOption.Builder> getUninterpretedOptionBuilderList() {
                return this.getUninterpretedOptionFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilder<UninterpretedOption, UninterpretedOption.Builder, UninterpretedOptionOrBuilder> getUninterpretedOptionFieldBuilder() {
                if (this.uninterpretedOptionBuilder_ == null) {
                    this.uninterpretedOptionBuilder_ = new RepeatedFieldBuilder(this.uninterpretedOption_, (this.bitField0_ & 2) == 2, this.getParentForChildren(), this.isClean());
                    this.uninterpretedOption_ = null;
                }
                return this.uninterpretedOptionBuilder_;
            }
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static interface ServiceOptionsOrBuilder
    extends GeneratedMessage.ExtendableMessageOrBuilder<ServiceOptions> {
        public boolean hasDeprecated();

        public boolean getDeprecated();

        public List<UninterpretedOption> getUninterpretedOptionList();

        public UninterpretedOption getUninterpretedOption(int var1);

        public int getUninterpretedOptionCount();

        public List<? extends UninterpretedOptionOrBuilder> getUninterpretedOptionOrBuilderList();

        public UninterpretedOptionOrBuilder getUninterpretedOptionOrBuilder(int var1);
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static final class EnumValueOptions
    extends GeneratedMessage.ExtendableMessage<EnumValueOptions>
    implements EnumValueOptionsOrBuilder {
        private static final EnumValueOptions defaultInstance;
        private final UnknownFieldSet unknownFields;
        public static Parser<EnumValueOptions> PARSER;
        private int bitField0_;
        public static final int DEPRECATED_FIELD_NUMBER = 1;
        private boolean deprecated_;
        public static final int UNINTERPRETED_OPTION_FIELD_NUMBER = 999;
        private List<UninterpretedOption> uninterpretedOption_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private static final long serialVersionUID = 0L;

        private EnumValueOptions(GeneratedMessage.ExtendableBuilder<EnumValueOptions, ?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte)-1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private EnumValueOptions(boolean noInit) {
            this.memoizedIsInitialized = (byte)-1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static EnumValueOptions getDefaultInstance() {
            return defaultInstance;
        }

        @Override
        public EnumValueOptions getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        private EnumValueOptions(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            UnknownFieldSet.Builder unknownFields;
            block14: {
                this.memoizedIsInitialized = (byte)-1;
                this.memoizedSerializedSize = -1;
                this.initFields();
                int mutable_bitField0_ = 0;
                unknownFields = UnknownFieldSet.newBuilder();
                try {
                    try {
                        boolean done = false;
                        block10: while (!done) {
                            int tag = input.readTag();
                            switch (tag) {
                                case 0: {
                                    done = true;
                                    continue block10;
                                }
                                default: {
                                    if (this.parseUnknownField(input, unknownFields, extensionRegistry, tag)) continue block10;
                                    done = true;
                                    continue block10;
                                }
                                case 8: {
                                    this.bitField0_ |= 1;
                                    this.deprecated_ = input.readBool();
                                    continue block10;
                                }
                                case 7994: 
                            }
                            if ((mutable_bitField0_ & 2) != 2) {
                                this.uninterpretedOption_ = new ArrayList<UninterpretedOption>();
                                mutable_bitField0_ |= 2;
                            }
                            this.uninterpretedOption_.add(input.readMessage(UninterpretedOption.PARSER, extensionRegistry));
                        }
                        Object var8_9 = null;
                        if ((mutable_bitField0_ & 2) != 2) break block14;
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e) {
                        throw new InvalidProtocolBufferException(e.getMessage()).setUnfinishedMessage(this);
                    }
                } catch (Throwable throwable) {
                    Object var8_10 = null;
                    if ((mutable_bitField0_ & 2) == 2) {
                        this.uninterpretedOption_ = Collections.unmodifiableList(this.uninterpretedOption_);
                    }
                    this.unknownFields = unknownFields.build();
                    this.makeExtensionsImmutable();
                    throw throwable;
                }
                this.uninterpretedOption_ = Collections.unmodifiableList(this.uninterpretedOption_);
            }
            this.unknownFields = unknownFields.build();
            this.makeExtensionsImmutable();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return internal_static_google_protobuf_EnumValueOptions_descriptor;
        }

        @Override
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return internal_static_google_protobuf_EnumValueOptions_fieldAccessorTable.ensureFieldAccessorsInitialized(EnumValueOptions.class, Builder.class);
        }

        public Parser<EnumValueOptions> getParserForType() {
            return PARSER;
        }

        @Override
        public boolean hasDeprecated() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override
        public boolean getDeprecated() {
            return this.deprecated_;
        }

        @Override
        public List<UninterpretedOption> getUninterpretedOptionList() {
            return this.uninterpretedOption_;
        }

        @Override
        public List<? extends UninterpretedOptionOrBuilder> getUninterpretedOptionOrBuilderList() {
            return this.uninterpretedOption_;
        }

        @Override
        public int getUninterpretedOptionCount() {
            return this.uninterpretedOption_.size();
        }

        @Override
        public UninterpretedOption getUninterpretedOption(int index) {
            return this.uninterpretedOption_.get(index);
        }

        @Override
        public UninterpretedOptionOrBuilder getUninterpretedOptionOrBuilder(int index) {
            return this.uninterpretedOption_.get(index);
        }

        private void initFields() {
            this.deprecated_ = false;
            this.uninterpretedOption_ = Collections.emptyList();
        }

        @Override
        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            for (int i = 0; i < this.getUninterpretedOptionCount(); ++i) {
                if (this.getUninterpretedOption(i).isInitialized()) continue;
                this.memoizedIsInitialized = 0;
                return false;
            }
            if (!this.extensionsAreInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        @Override
        public void writeTo(CodedOutputStream output) throws IOException {
            this.getSerializedSize();
            GeneratedMessage.ExtendableMessage.ExtensionWriter extensionWriter = this.newExtensionWriter();
            if ((this.bitField0_ & 1) == 1) {
                output.writeBool(1, this.deprecated_);
            }
            for (int i = 0; i < this.uninterpretedOption_.size(); ++i) {
                output.writeMessage(999, this.uninterpretedOption_.get(i));
            }
            extensionWriter.writeUntil(0x20000000, output);
            this.getUnknownFields().writeTo(output);
        }

        @Override
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            size = 0;
            if ((this.bitField0_ & 1) == 1) {
                size += CodedOutputStream.computeBoolSize(1, this.deprecated_);
            }
            for (int i = 0; i < this.uninterpretedOption_.size(); ++i) {
                size += CodedOutputStream.computeMessageSize(999, this.uninterpretedOption_.get(i));
            }
            size += this.extensionsSerializedSize();
            this.memoizedSerializedSize = size += this.getUnknownFields().getSerializedSize();
            return size;
        }

        @Override
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static EnumValueOptions parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static EnumValueOptions parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static EnumValueOptions parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static EnumValueOptions parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static EnumValueOptions parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static EnumValueOptions parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static EnumValueOptions parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static EnumValueOptions parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static EnumValueOptions parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static EnumValueOptions parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override
        public Builder newBuilderForType() {
            return EnumValueOptions.newBuilder();
        }

        public static Builder newBuilder(EnumValueOptions prototype) {
            return EnumValueOptions.newBuilder().mergeFrom(prototype);
        }

        @Override
        public Builder toBuilder() {
            return EnumValueOptions.newBuilder(this);
        }

        @Override
        protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        static {
            PARSER = new AbstractParser<EnumValueOptions>(){

                @Override
                public EnumValueOptions parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                    return new EnumValueOptions(input, extensionRegistry);
                }
            };
            defaultInstance = new EnumValueOptions(true);
            defaultInstance.initFields();
        }

        /*
         * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
         */
        public static final class Builder
        extends GeneratedMessage.ExtendableBuilder<EnumValueOptions, Builder>
        implements EnumValueOptionsOrBuilder {
            private int bitField0_;
            private boolean deprecated_;
            private List<UninterpretedOption> uninterpretedOption_ = Collections.emptyList();
            private RepeatedFieldBuilder<UninterpretedOption, UninterpretedOption.Builder, UninterpretedOptionOrBuilder> uninterpretedOptionBuilder_;

            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_google_protobuf_EnumValueOptions_descriptor;
            }

            @Override
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_google_protobuf_EnumValueOptions_fieldAccessorTable.ensureFieldAccessorsInitialized(EnumValueOptions.class, Builder.class);
            }

            private Builder() {
                this.maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessage.BuilderParent parent) {
                super(parent);
                this.maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                if (GeneratedMessage.alwaysUseFieldBuilders) {
                    this.getUninterpretedOptionFieldBuilder();
                }
            }

            private static Builder create() {
                return new Builder();
            }

            @Override
            public Builder clear() {
                super.clear();
                this.deprecated_ = false;
                this.bitField0_ &= 0xFFFFFFFE;
                if (this.uninterpretedOptionBuilder_ == null) {
                    this.uninterpretedOption_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFFFFD;
                } else {
                    this.uninterpretedOptionBuilder_.clear();
                }
                return this;
            }

            @Override
            public Builder clone() {
                return Builder.create().mergeFrom(this.buildPartial());
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return internal_static_google_protobuf_EnumValueOptions_descriptor;
            }

            @Override
            public EnumValueOptions getDefaultInstanceForType() {
                return EnumValueOptions.getDefaultInstance();
            }

            @Override
            public EnumValueOptions build() {
                EnumValueOptions result = this.buildPartial();
                if (!result.isInitialized()) {
                    throw Builder.newUninitializedMessageException(result);
                }
                return result;
            }

            @Override
            public EnumValueOptions buildPartial() {
                EnumValueOptions result = new EnumValueOptions(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ |= 1;
                }
                result.deprecated_ = this.deprecated_;
                if (this.uninterpretedOptionBuilder_ == null) {
                    if ((this.bitField0_ & 2) == 2) {
                        this.uninterpretedOption_ = Collections.unmodifiableList(this.uninterpretedOption_);
                        this.bitField0_ &= 0xFFFFFFFD;
                    }
                    result.uninterpretedOption_ = this.uninterpretedOption_;
                } else {
                    result.uninterpretedOption_ = this.uninterpretedOptionBuilder_.build();
                }
                result.bitField0_ = to_bitField0_;
                this.onBuilt();
                return result;
            }

            @Override
            public Builder mergeFrom(Message other) {
                if (other instanceof EnumValueOptions) {
                    return this.mergeFrom((EnumValueOptions)other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(EnumValueOptions other) {
                if (other == EnumValueOptions.getDefaultInstance()) {
                    return this;
                }
                if (other.hasDeprecated()) {
                    this.setDeprecated(other.getDeprecated());
                }
                if (this.uninterpretedOptionBuilder_ == null) {
                    if (!other.uninterpretedOption_.isEmpty()) {
                        if (this.uninterpretedOption_.isEmpty()) {
                            this.uninterpretedOption_ = other.uninterpretedOption_;
                            this.bitField0_ &= 0xFFFFFFFD;
                        } else {
                            this.ensureUninterpretedOptionIsMutable();
                            this.uninterpretedOption_.addAll(other.uninterpretedOption_);
                        }
                        this.onChanged();
                    }
                } else if (!other.uninterpretedOption_.isEmpty()) {
                    if (this.uninterpretedOptionBuilder_.isEmpty()) {
                        this.uninterpretedOptionBuilder_.dispose();
                        this.uninterpretedOptionBuilder_ = null;
                        this.uninterpretedOption_ = other.uninterpretedOption_;
                        this.bitField0_ &= 0xFFFFFFFD;
                        this.uninterpretedOptionBuilder_ = GeneratedMessage.alwaysUseFieldBuilders ? this.getUninterpretedOptionFieldBuilder() : null;
                    } else {
                        this.uninterpretedOptionBuilder_.addAllMessages(other.uninterpretedOption_);
                    }
                }
                this.mergeExtensionFields(other);
                this.mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            @Override
            public final boolean isInitialized() {
                for (int i = 0; i < this.getUninterpretedOptionCount(); ++i) {
                    if (this.getUninterpretedOption(i).isInitialized()) continue;
                    return false;
                }
                return this.extensionsAreInitialized();
            }

            /*
             * Enabled force condition propagation
             * Lifted jumps to return sites
             */
            @Override
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                EnumValueOptions parsedMessage = null;
                try {
                    try {
                        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
                    } catch (InvalidProtocolBufferException e) {
                        parsedMessage = (EnumValueOptions)e.getUnfinishedMessage();
                        throw e;
                    }
                    Object var6_4 = null;
                    if (parsedMessage == null) return this;
                    this.mergeFrom(parsedMessage);
                    return this;
                } catch (Throwable throwable) {
                    Object var6_5 = null;
                    if (parsedMessage == null) throw throwable;
                    this.mergeFrom(parsedMessage);
                    throw throwable;
                }
            }

            @Override
            public boolean hasDeprecated() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override
            public boolean getDeprecated() {
                return this.deprecated_;
            }

            public Builder setDeprecated(boolean value) {
                this.bitField0_ |= 1;
                this.deprecated_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearDeprecated() {
                this.bitField0_ &= 0xFFFFFFFE;
                this.deprecated_ = false;
                this.onChanged();
                return this;
            }

            private void ensureUninterpretedOptionIsMutable() {
                if ((this.bitField0_ & 2) != 2) {
                    this.uninterpretedOption_ = new ArrayList<UninterpretedOption>(this.uninterpretedOption_);
                    this.bitField0_ |= 2;
                }
            }

            @Override
            public List<UninterpretedOption> getUninterpretedOptionList() {
                if (this.uninterpretedOptionBuilder_ == null) {
                    return Collections.unmodifiableList(this.uninterpretedOption_);
                }
                return this.uninterpretedOptionBuilder_.getMessageList();
            }

            @Override
            public int getUninterpretedOptionCount() {
                if (this.uninterpretedOptionBuilder_ == null) {
                    return this.uninterpretedOption_.size();
                }
                return this.uninterpretedOptionBuilder_.getCount();
            }

            @Override
            public UninterpretedOption getUninterpretedOption(int index) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    return this.uninterpretedOption_.get(index);
                }
                return this.uninterpretedOptionBuilder_.getMessage(index);
            }

            public Builder setUninterpretedOption(int index, UninterpretedOption value) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureUninterpretedOptionIsMutable();
                    this.uninterpretedOption_.set(index, value);
                    this.onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.setMessage(index, value);
                }
                return this;
            }

            public Builder setUninterpretedOption(int index, UninterpretedOption.Builder builderForValue) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    this.ensureUninterpretedOptionIsMutable();
                    this.uninterpretedOption_.set(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.setMessage(index, builderForValue.build());
                }
                return this;
            }

            public Builder addUninterpretedOption(UninterpretedOption value) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureUninterpretedOptionIsMutable();
                    this.uninterpretedOption_.add(value);
                    this.onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.addMessage(value);
                }
                return this;
            }

            public Builder addUninterpretedOption(int index, UninterpretedOption value) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureUninterpretedOptionIsMutable();
                    this.uninterpretedOption_.add(index, value);
                    this.onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.addMessage(index, value);
                }
                return this;
            }

            public Builder addUninterpretedOption(UninterpretedOption.Builder builderForValue) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    this.ensureUninterpretedOptionIsMutable();
                    this.uninterpretedOption_.add(builderForValue.build());
                    this.onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.addMessage(builderForValue.build());
                }
                return this;
            }

            public Builder addUninterpretedOption(int index, UninterpretedOption.Builder builderForValue) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    this.ensureUninterpretedOptionIsMutable();
                    this.uninterpretedOption_.add(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.addMessage(index, builderForValue.build());
                }
                return this;
            }

            public Builder addAllUninterpretedOption(Iterable<? extends UninterpretedOption> values2) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    this.ensureUninterpretedOptionIsMutable();
                    AbstractMessageLite.Builder.addAll(values2, this.uninterpretedOption_);
                    this.onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.addAllMessages(values2);
                }
                return this;
            }

            public Builder clearUninterpretedOption() {
                if (this.uninterpretedOptionBuilder_ == null) {
                    this.uninterpretedOption_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFFFFD;
                    this.onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.clear();
                }
                return this;
            }

            public Builder removeUninterpretedOption(int index) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    this.ensureUninterpretedOptionIsMutable();
                    this.uninterpretedOption_.remove(index);
                    this.onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.remove(index);
                }
                return this;
            }

            public UninterpretedOption.Builder getUninterpretedOptionBuilder(int index) {
                return this.getUninterpretedOptionFieldBuilder().getBuilder(index);
            }

            @Override
            public UninterpretedOptionOrBuilder getUninterpretedOptionOrBuilder(int index) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    return this.uninterpretedOption_.get(index);
                }
                return this.uninterpretedOptionBuilder_.getMessageOrBuilder(index);
            }

            @Override
            public List<? extends UninterpretedOptionOrBuilder> getUninterpretedOptionOrBuilderList() {
                if (this.uninterpretedOptionBuilder_ != null) {
                    return this.uninterpretedOptionBuilder_.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.uninterpretedOption_);
            }

            public UninterpretedOption.Builder addUninterpretedOptionBuilder() {
                return this.getUninterpretedOptionFieldBuilder().addBuilder(UninterpretedOption.getDefaultInstance());
            }

            public UninterpretedOption.Builder addUninterpretedOptionBuilder(int index) {
                return this.getUninterpretedOptionFieldBuilder().addBuilder(index, UninterpretedOption.getDefaultInstance());
            }

            public List<UninterpretedOption.Builder> getUninterpretedOptionBuilderList() {
                return this.getUninterpretedOptionFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilder<UninterpretedOption, UninterpretedOption.Builder, UninterpretedOptionOrBuilder> getUninterpretedOptionFieldBuilder() {
                if (this.uninterpretedOptionBuilder_ == null) {
                    this.uninterpretedOptionBuilder_ = new RepeatedFieldBuilder(this.uninterpretedOption_, (this.bitField0_ & 2) == 2, this.getParentForChildren(), this.isClean());
                    this.uninterpretedOption_ = null;
                }
                return this.uninterpretedOptionBuilder_;
            }
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static interface EnumValueOptionsOrBuilder
    extends GeneratedMessage.ExtendableMessageOrBuilder<EnumValueOptions> {
        public boolean hasDeprecated();

        public boolean getDeprecated();

        public List<UninterpretedOption> getUninterpretedOptionList();

        public UninterpretedOption getUninterpretedOption(int var1);

        public int getUninterpretedOptionCount();

        public List<? extends UninterpretedOptionOrBuilder> getUninterpretedOptionOrBuilderList();

        public UninterpretedOptionOrBuilder getUninterpretedOptionOrBuilder(int var1);
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static final class EnumOptions
    extends GeneratedMessage.ExtendableMessage<EnumOptions>
    implements EnumOptionsOrBuilder {
        private static final EnumOptions defaultInstance;
        private final UnknownFieldSet unknownFields;
        public static Parser<EnumOptions> PARSER;
        private int bitField0_;
        public static final int ALLOW_ALIAS_FIELD_NUMBER = 2;
        private boolean allowAlias_;
        public static final int DEPRECATED_FIELD_NUMBER = 3;
        private boolean deprecated_;
        public static final int UNINTERPRETED_OPTION_FIELD_NUMBER = 999;
        private List<UninterpretedOption> uninterpretedOption_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private static final long serialVersionUID = 0L;

        private EnumOptions(GeneratedMessage.ExtendableBuilder<EnumOptions, ?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte)-1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private EnumOptions(boolean noInit) {
            this.memoizedIsInitialized = (byte)-1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static EnumOptions getDefaultInstance() {
            return defaultInstance;
        }

        @Override
        public EnumOptions getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        private EnumOptions(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            UnknownFieldSet.Builder unknownFields;
            block15: {
                this.memoizedIsInitialized = (byte)-1;
                this.memoizedSerializedSize = -1;
                this.initFields();
                int mutable_bitField0_ = 0;
                unknownFields = UnknownFieldSet.newBuilder();
                try {
                    try {
                        boolean done = false;
                        block11: while (!done) {
                            int tag = input.readTag();
                            switch (tag) {
                                case 0: {
                                    done = true;
                                    continue block11;
                                }
                                default: {
                                    if (this.parseUnknownField(input, unknownFields, extensionRegistry, tag)) continue block11;
                                    done = true;
                                    continue block11;
                                }
                                case 16: {
                                    this.bitField0_ |= 1;
                                    this.allowAlias_ = input.readBool();
                                    continue block11;
                                }
                                case 24: {
                                    this.bitField0_ |= 2;
                                    this.deprecated_ = input.readBool();
                                    continue block11;
                                }
                                case 7994: 
                            }
                            if ((mutable_bitField0_ & 4) != 4) {
                                this.uninterpretedOption_ = new ArrayList<UninterpretedOption>();
                                mutable_bitField0_ |= 4;
                            }
                            this.uninterpretedOption_.add(input.readMessage(UninterpretedOption.PARSER, extensionRegistry));
                        }
                        Object var8_9 = null;
                        if ((mutable_bitField0_ & 4) != 4) break block15;
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e) {
                        throw new InvalidProtocolBufferException(e.getMessage()).setUnfinishedMessage(this);
                    }
                } catch (Throwable throwable) {
                    Object var8_10 = null;
                    if ((mutable_bitField0_ & 4) == 4) {
                        this.uninterpretedOption_ = Collections.unmodifiableList(this.uninterpretedOption_);
                    }
                    this.unknownFields = unknownFields.build();
                    this.makeExtensionsImmutable();
                    throw throwable;
                }
                this.uninterpretedOption_ = Collections.unmodifiableList(this.uninterpretedOption_);
            }
            this.unknownFields = unknownFields.build();
            this.makeExtensionsImmutable();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return internal_static_google_protobuf_EnumOptions_descriptor;
        }

        @Override
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return internal_static_google_protobuf_EnumOptions_fieldAccessorTable.ensureFieldAccessorsInitialized(EnumOptions.class, Builder.class);
        }

        public Parser<EnumOptions> getParserForType() {
            return PARSER;
        }

        @Override
        public boolean hasAllowAlias() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override
        public boolean getAllowAlias() {
            return this.allowAlias_;
        }

        @Override
        public boolean hasDeprecated() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override
        public boolean getDeprecated() {
            return this.deprecated_;
        }

        @Override
        public List<UninterpretedOption> getUninterpretedOptionList() {
            return this.uninterpretedOption_;
        }

        @Override
        public List<? extends UninterpretedOptionOrBuilder> getUninterpretedOptionOrBuilderList() {
            return this.uninterpretedOption_;
        }

        @Override
        public int getUninterpretedOptionCount() {
            return this.uninterpretedOption_.size();
        }

        @Override
        public UninterpretedOption getUninterpretedOption(int index) {
            return this.uninterpretedOption_.get(index);
        }

        @Override
        public UninterpretedOptionOrBuilder getUninterpretedOptionOrBuilder(int index) {
            return this.uninterpretedOption_.get(index);
        }

        private void initFields() {
            this.allowAlias_ = false;
            this.deprecated_ = false;
            this.uninterpretedOption_ = Collections.emptyList();
        }

        @Override
        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            for (int i = 0; i < this.getUninterpretedOptionCount(); ++i) {
                if (this.getUninterpretedOption(i).isInitialized()) continue;
                this.memoizedIsInitialized = 0;
                return false;
            }
            if (!this.extensionsAreInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        @Override
        public void writeTo(CodedOutputStream output) throws IOException {
            this.getSerializedSize();
            GeneratedMessage.ExtendableMessage.ExtensionWriter extensionWriter = this.newExtensionWriter();
            if ((this.bitField0_ & 1) == 1) {
                output.writeBool(2, this.allowAlias_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeBool(3, this.deprecated_);
            }
            for (int i = 0; i < this.uninterpretedOption_.size(); ++i) {
                output.writeMessage(999, this.uninterpretedOption_.get(i));
            }
            extensionWriter.writeUntil(0x20000000, output);
            this.getUnknownFields().writeTo(output);
        }

        @Override
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            size = 0;
            if ((this.bitField0_ & 1) == 1) {
                size += CodedOutputStream.computeBoolSize(2, this.allowAlias_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size += CodedOutputStream.computeBoolSize(3, this.deprecated_);
            }
            for (int i = 0; i < this.uninterpretedOption_.size(); ++i) {
                size += CodedOutputStream.computeMessageSize(999, this.uninterpretedOption_.get(i));
            }
            size += this.extensionsSerializedSize();
            this.memoizedSerializedSize = size += this.getUnknownFields().getSerializedSize();
            return size;
        }

        @Override
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static EnumOptions parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static EnumOptions parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static EnumOptions parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static EnumOptions parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static EnumOptions parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static EnumOptions parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static EnumOptions parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static EnumOptions parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static EnumOptions parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static EnumOptions parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override
        public Builder newBuilderForType() {
            return EnumOptions.newBuilder();
        }

        public static Builder newBuilder(EnumOptions prototype) {
            return EnumOptions.newBuilder().mergeFrom(prototype);
        }

        @Override
        public Builder toBuilder() {
            return EnumOptions.newBuilder(this);
        }

        @Override
        protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        static {
            PARSER = new AbstractParser<EnumOptions>(){

                @Override
                public EnumOptions parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                    return new EnumOptions(input, extensionRegistry);
                }
            };
            defaultInstance = new EnumOptions(true);
            defaultInstance.initFields();
        }

        /*
         * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
         */
        public static final class Builder
        extends GeneratedMessage.ExtendableBuilder<EnumOptions, Builder>
        implements EnumOptionsOrBuilder {
            private int bitField0_;
            private boolean allowAlias_;
            private boolean deprecated_;
            private List<UninterpretedOption> uninterpretedOption_ = Collections.emptyList();
            private RepeatedFieldBuilder<UninterpretedOption, UninterpretedOption.Builder, UninterpretedOptionOrBuilder> uninterpretedOptionBuilder_;

            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_google_protobuf_EnumOptions_descriptor;
            }

            @Override
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_google_protobuf_EnumOptions_fieldAccessorTable.ensureFieldAccessorsInitialized(EnumOptions.class, Builder.class);
            }

            private Builder() {
                this.maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessage.BuilderParent parent) {
                super(parent);
                this.maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                if (GeneratedMessage.alwaysUseFieldBuilders) {
                    this.getUninterpretedOptionFieldBuilder();
                }
            }

            private static Builder create() {
                return new Builder();
            }

            @Override
            public Builder clear() {
                super.clear();
                this.allowAlias_ = false;
                this.bitField0_ &= 0xFFFFFFFE;
                this.deprecated_ = false;
                this.bitField0_ &= 0xFFFFFFFD;
                if (this.uninterpretedOptionBuilder_ == null) {
                    this.uninterpretedOption_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFFFFB;
                } else {
                    this.uninterpretedOptionBuilder_.clear();
                }
                return this;
            }

            @Override
            public Builder clone() {
                return Builder.create().mergeFrom(this.buildPartial());
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return internal_static_google_protobuf_EnumOptions_descriptor;
            }

            @Override
            public EnumOptions getDefaultInstanceForType() {
                return EnumOptions.getDefaultInstance();
            }

            @Override
            public EnumOptions build() {
                EnumOptions result = this.buildPartial();
                if (!result.isInitialized()) {
                    throw Builder.newUninitializedMessageException(result);
                }
                return result;
            }

            @Override
            public EnumOptions buildPartial() {
                EnumOptions result = new EnumOptions(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ |= 1;
                }
                result.allowAlias_ = this.allowAlias_;
                if ((from_bitField0_ & 2) == 2) {
                    to_bitField0_ |= 2;
                }
                result.deprecated_ = this.deprecated_;
                if (this.uninterpretedOptionBuilder_ == null) {
                    if ((this.bitField0_ & 4) == 4) {
                        this.uninterpretedOption_ = Collections.unmodifiableList(this.uninterpretedOption_);
                        this.bitField0_ &= 0xFFFFFFFB;
                    }
                    result.uninterpretedOption_ = this.uninterpretedOption_;
                } else {
                    result.uninterpretedOption_ = this.uninterpretedOptionBuilder_.build();
                }
                result.bitField0_ = to_bitField0_;
                this.onBuilt();
                return result;
            }

            @Override
            public Builder mergeFrom(Message other) {
                if (other instanceof EnumOptions) {
                    return this.mergeFrom((EnumOptions)other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(EnumOptions other) {
                if (other == EnumOptions.getDefaultInstance()) {
                    return this;
                }
                if (other.hasAllowAlias()) {
                    this.setAllowAlias(other.getAllowAlias());
                }
                if (other.hasDeprecated()) {
                    this.setDeprecated(other.getDeprecated());
                }
                if (this.uninterpretedOptionBuilder_ == null) {
                    if (!other.uninterpretedOption_.isEmpty()) {
                        if (this.uninterpretedOption_.isEmpty()) {
                            this.uninterpretedOption_ = other.uninterpretedOption_;
                            this.bitField0_ &= 0xFFFFFFFB;
                        } else {
                            this.ensureUninterpretedOptionIsMutable();
                            this.uninterpretedOption_.addAll(other.uninterpretedOption_);
                        }
                        this.onChanged();
                    }
                } else if (!other.uninterpretedOption_.isEmpty()) {
                    if (this.uninterpretedOptionBuilder_.isEmpty()) {
                        this.uninterpretedOptionBuilder_.dispose();
                        this.uninterpretedOptionBuilder_ = null;
                        this.uninterpretedOption_ = other.uninterpretedOption_;
                        this.bitField0_ &= 0xFFFFFFFB;
                        this.uninterpretedOptionBuilder_ = GeneratedMessage.alwaysUseFieldBuilders ? this.getUninterpretedOptionFieldBuilder() : null;
                    } else {
                        this.uninterpretedOptionBuilder_.addAllMessages(other.uninterpretedOption_);
                    }
                }
                this.mergeExtensionFields(other);
                this.mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            @Override
            public final boolean isInitialized() {
                for (int i = 0; i < this.getUninterpretedOptionCount(); ++i) {
                    if (this.getUninterpretedOption(i).isInitialized()) continue;
                    return false;
                }
                return this.extensionsAreInitialized();
            }

            /*
             * Enabled force condition propagation
             * Lifted jumps to return sites
             */
            @Override
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                EnumOptions parsedMessage = null;
                try {
                    try {
                        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
                    } catch (InvalidProtocolBufferException e) {
                        parsedMessage = (EnumOptions)e.getUnfinishedMessage();
                        throw e;
                    }
                    Object var6_4 = null;
                    if (parsedMessage == null) return this;
                    this.mergeFrom(parsedMessage);
                    return this;
                } catch (Throwable throwable) {
                    Object var6_5 = null;
                    if (parsedMessage == null) throw throwable;
                    this.mergeFrom(parsedMessage);
                    throw throwable;
                }
            }

            @Override
            public boolean hasAllowAlias() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override
            public boolean getAllowAlias() {
                return this.allowAlias_;
            }

            public Builder setAllowAlias(boolean value) {
                this.bitField0_ |= 1;
                this.allowAlias_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearAllowAlias() {
                this.bitField0_ &= 0xFFFFFFFE;
                this.allowAlias_ = false;
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasDeprecated() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override
            public boolean getDeprecated() {
                return this.deprecated_;
            }

            public Builder setDeprecated(boolean value) {
                this.bitField0_ |= 2;
                this.deprecated_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearDeprecated() {
                this.bitField0_ &= 0xFFFFFFFD;
                this.deprecated_ = false;
                this.onChanged();
                return this;
            }

            private void ensureUninterpretedOptionIsMutable() {
                if ((this.bitField0_ & 4) != 4) {
                    this.uninterpretedOption_ = new ArrayList<UninterpretedOption>(this.uninterpretedOption_);
                    this.bitField0_ |= 4;
                }
            }

            @Override
            public List<UninterpretedOption> getUninterpretedOptionList() {
                if (this.uninterpretedOptionBuilder_ == null) {
                    return Collections.unmodifiableList(this.uninterpretedOption_);
                }
                return this.uninterpretedOptionBuilder_.getMessageList();
            }

            @Override
            public int getUninterpretedOptionCount() {
                if (this.uninterpretedOptionBuilder_ == null) {
                    return this.uninterpretedOption_.size();
                }
                return this.uninterpretedOptionBuilder_.getCount();
            }

            @Override
            public UninterpretedOption getUninterpretedOption(int index) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    return this.uninterpretedOption_.get(index);
                }
                return this.uninterpretedOptionBuilder_.getMessage(index);
            }

            public Builder setUninterpretedOption(int index, UninterpretedOption value) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureUninterpretedOptionIsMutable();
                    this.uninterpretedOption_.set(index, value);
                    this.onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.setMessage(index, value);
                }
                return this;
            }

            public Builder setUninterpretedOption(int index, UninterpretedOption.Builder builderForValue) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    this.ensureUninterpretedOptionIsMutable();
                    this.uninterpretedOption_.set(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.setMessage(index, builderForValue.build());
                }
                return this;
            }

            public Builder addUninterpretedOption(UninterpretedOption value) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureUninterpretedOptionIsMutable();
                    this.uninterpretedOption_.add(value);
                    this.onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.addMessage(value);
                }
                return this;
            }

            public Builder addUninterpretedOption(int index, UninterpretedOption value) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureUninterpretedOptionIsMutable();
                    this.uninterpretedOption_.add(index, value);
                    this.onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.addMessage(index, value);
                }
                return this;
            }

            public Builder addUninterpretedOption(UninterpretedOption.Builder builderForValue) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    this.ensureUninterpretedOptionIsMutable();
                    this.uninterpretedOption_.add(builderForValue.build());
                    this.onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.addMessage(builderForValue.build());
                }
                return this;
            }

            public Builder addUninterpretedOption(int index, UninterpretedOption.Builder builderForValue) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    this.ensureUninterpretedOptionIsMutable();
                    this.uninterpretedOption_.add(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.addMessage(index, builderForValue.build());
                }
                return this;
            }

            public Builder addAllUninterpretedOption(Iterable<? extends UninterpretedOption> values2) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    this.ensureUninterpretedOptionIsMutable();
                    AbstractMessageLite.Builder.addAll(values2, this.uninterpretedOption_);
                    this.onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.addAllMessages(values2);
                }
                return this;
            }

            public Builder clearUninterpretedOption() {
                if (this.uninterpretedOptionBuilder_ == null) {
                    this.uninterpretedOption_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFFFFB;
                    this.onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.clear();
                }
                return this;
            }

            public Builder removeUninterpretedOption(int index) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    this.ensureUninterpretedOptionIsMutable();
                    this.uninterpretedOption_.remove(index);
                    this.onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.remove(index);
                }
                return this;
            }

            public UninterpretedOption.Builder getUninterpretedOptionBuilder(int index) {
                return this.getUninterpretedOptionFieldBuilder().getBuilder(index);
            }

            @Override
            public UninterpretedOptionOrBuilder getUninterpretedOptionOrBuilder(int index) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    return this.uninterpretedOption_.get(index);
                }
                return this.uninterpretedOptionBuilder_.getMessageOrBuilder(index);
            }

            @Override
            public List<? extends UninterpretedOptionOrBuilder> getUninterpretedOptionOrBuilderList() {
                if (this.uninterpretedOptionBuilder_ != null) {
                    return this.uninterpretedOptionBuilder_.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.uninterpretedOption_);
            }

            public UninterpretedOption.Builder addUninterpretedOptionBuilder() {
                return this.getUninterpretedOptionFieldBuilder().addBuilder(UninterpretedOption.getDefaultInstance());
            }

            public UninterpretedOption.Builder addUninterpretedOptionBuilder(int index) {
                return this.getUninterpretedOptionFieldBuilder().addBuilder(index, UninterpretedOption.getDefaultInstance());
            }

            public List<UninterpretedOption.Builder> getUninterpretedOptionBuilderList() {
                return this.getUninterpretedOptionFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilder<UninterpretedOption, UninterpretedOption.Builder, UninterpretedOptionOrBuilder> getUninterpretedOptionFieldBuilder() {
                if (this.uninterpretedOptionBuilder_ == null) {
                    this.uninterpretedOptionBuilder_ = new RepeatedFieldBuilder(this.uninterpretedOption_, (this.bitField0_ & 4) == 4, this.getParentForChildren(), this.isClean());
                    this.uninterpretedOption_ = null;
                }
                return this.uninterpretedOptionBuilder_;
            }
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static interface EnumOptionsOrBuilder
    extends GeneratedMessage.ExtendableMessageOrBuilder<EnumOptions> {
        public boolean hasAllowAlias();

        public boolean getAllowAlias();

        public boolean hasDeprecated();

        public boolean getDeprecated();

        public List<UninterpretedOption> getUninterpretedOptionList();

        public UninterpretedOption getUninterpretedOption(int var1);

        public int getUninterpretedOptionCount();

        public List<? extends UninterpretedOptionOrBuilder> getUninterpretedOptionOrBuilderList();

        public UninterpretedOptionOrBuilder getUninterpretedOptionOrBuilder(int var1);
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static final class FieldOptions
    extends GeneratedMessage.ExtendableMessage<FieldOptions>
    implements FieldOptionsOrBuilder {
        private static final FieldOptions defaultInstance;
        private final UnknownFieldSet unknownFields;
        public static Parser<FieldOptions> PARSER;
        private int bitField0_;
        public static final int CTYPE_FIELD_NUMBER = 1;
        private CType ctype_;
        public static final int PACKED_FIELD_NUMBER = 2;
        private boolean packed_;
        public static final int LAZY_FIELD_NUMBER = 5;
        private boolean lazy_;
        public static final int DEPRECATED_FIELD_NUMBER = 3;
        private boolean deprecated_;
        public static final int EXPERIMENTAL_MAP_KEY_FIELD_NUMBER = 9;
        private Object experimentalMapKey_;
        public static final int WEAK_FIELD_NUMBER = 10;
        private boolean weak_;
        public static final int UNINTERPRETED_OPTION_FIELD_NUMBER = 999;
        private List<UninterpretedOption> uninterpretedOption_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private static final long serialVersionUID = 0L;

        private FieldOptions(GeneratedMessage.ExtendableBuilder<FieldOptions, ?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte)-1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private FieldOptions(boolean noInit) {
            this.memoizedIsInitialized = (byte)-1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static FieldOptions getDefaultInstance() {
            return defaultInstance;
        }

        @Override
        public FieldOptions getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        private FieldOptions(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            UnknownFieldSet.Builder unknownFields;
            block20: {
                this.memoizedIsInitialized = (byte)-1;
                this.memoizedSerializedSize = -1;
                this.initFields();
                int mutable_bitField0_ = 0;
                unknownFields = UnknownFieldSet.newBuilder();
                try {
                    try {
                        boolean done = false;
                        block15: while (!done) {
                            int tag = input.readTag();
                            switch (tag) {
                                case 0: {
                                    done = true;
                                    continue block15;
                                }
                                default: {
                                    if (this.parseUnknownField(input, unknownFields, extensionRegistry, tag)) continue block15;
                                    done = true;
                                    continue block15;
                                }
                                case 8: {
                                    int rawValue = input.readEnum();
                                    CType value = CType.valueOf(rawValue);
                                    if (value == null) {
                                        unknownFields.mergeVarintField(1, rawValue);
                                        continue block15;
                                    }
                                    this.bitField0_ |= 1;
                                    this.ctype_ = value;
                                    continue block15;
                                }
                                case 16: {
                                    this.bitField0_ |= 2;
                                    this.packed_ = input.readBool();
                                    continue block15;
                                }
                                case 24: {
                                    this.bitField0_ |= 8;
                                    this.deprecated_ = input.readBool();
                                    continue block15;
                                }
                                case 40: {
                                    this.bitField0_ |= 4;
                                    this.lazy_ = input.readBool();
                                    continue block15;
                                }
                                case 74: {
                                    ByteString bs = input.readBytes();
                                    this.bitField0_ |= 0x10;
                                    this.experimentalMapKey_ = bs;
                                    continue block15;
                                }
                                case 80: {
                                    this.bitField0_ |= 0x20;
                                    this.weak_ = input.readBool();
                                    continue block15;
                                }
                                case 7994: 
                            }
                            if ((mutable_bitField0_ & 0x40) != 64) {
                                this.uninterpretedOption_ = new ArrayList<UninterpretedOption>();
                                mutable_bitField0_ |= 0x40;
                            }
                            this.uninterpretedOption_.add(input.readMessage(UninterpretedOption.PARSER, extensionRegistry));
                        }
                        Object var10_12 = null;
                        if ((mutable_bitField0_ & 0x40) != 64) break block20;
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e) {
                        throw new InvalidProtocolBufferException(e.getMessage()).setUnfinishedMessage(this);
                    }
                } catch (Throwable throwable) {
                    Object var10_13 = null;
                    if ((mutable_bitField0_ & 0x40) == 64) {
                        this.uninterpretedOption_ = Collections.unmodifiableList(this.uninterpretedOption_);
                    }
                    this.unknownFields = unknownFields.build();
                    this.makeExtensionsImmutable();
                    throw throwable;
                }
                this.uninterpretedOption_ = Collections.unmodifiableList(this.uninterpretedOption_);
            }
            this.unknownFields = unknownFields.build();
            this.makeExtensionsImmutable();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return internal_static_google_protobuf_FieldOptions_descriptor;
        }

        @Override
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return internal_static_google_protobuf_FieldOptions_fieldAccessorTable.ensureFieldAccessorsInitialized(FieldOptions.class, Builder.class);
        }

        public Parser<FieldOptions> getParserForType() {
            return PARSER;
        }

        @Override
        public boolean hasCtype() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override
        public CType getCtype() {
            return this.ctype_;
        }

        @Override
        public boolean hasPacked() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override
        public boolean getPacked() {
            return this.packed_;
        }

        @Override
        public boolean hasLazy() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override
        public boolean getLazy() {
            return this.lazy_;
        }

        @Override
        public boolean hasDeprecated() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override
        public boolean getDeprecated() {
            return this.deprecated_;
        }

        @Override
        public boolean hasExperimentalMapKey() {
            return (this.bitField0_ & 0x10) == 16;
        }

        @Override
        public String getExperimentalMapKey() {
            Object ref = this.experimentalMapKey_;
            if (ref instanceof String) {
                return (String)ref;
            }
            ByteString bs = (ByteString)ref;
            String s2 = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.experimentalMapKey_ = s2;
            }
            return s2;
        }

        @Override
        public ByteString getExperimentalMapKeyBytes() {
            Object ref = this.experimentalMapKey_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.experimentalMapKey_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        @Override
        public boolean hasWeak() {
            return (this.bitField0_ & 0x20) == 32;
        }

        @Override
        public boolean getWeak() {
            return this.weak_;
        }

        @Override
        public List<UninterpretedOption> getUninterpretedOptionList() {
            return this.uninterpretedOption_;
        }

        @Override
        public List<? extends UninterpretedOptionOrBuilder> getUninterpretedOptionOrBuilderList() {
            return this.uninterpretedOption_;
        }

        @Override
        public int getUninterpretedOptionCount() {
            return this.uninterpretedOption_.size();
        }

        @Override
        public UninterpretedOption getUninterpretedOption(int index) {
            return this.uninterpretedOption_.get(index);
        }

        @Override
        public UninterpretedOptionOrBuilder getUninterpretedOptionOrBuilder(int index) {
            return this.uninterpretedOption_.get(index);
        }

        private void initFields() {
            this.ctype_ = CType.STRING;
            this.packed_ = false;
            this.lazy_ = false;
            this.deprecated_ = false;
            this.experimentalMapKey_ = "";
            this.weak_ = false;
            this.uninterpretedOption_ = Collections.emptyList();
        }

        @Override
        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            for (int i = 0; i < this.getUninterpretedOptionCount(); ++i) {
                if (this.getUninterpretedOption(i).isInitialized()) continue;
                this.memoizedIsInitialized = 0;
                return false;
            }
            if (!this.extensionsAreInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        @Override
        public void writeTo(CodedOutputStream output) throws IOException {
            this.getSerializedSize();
            GeneratedMessage.ExtendableMessage.ExtensionWriter extensionWriter = this.newExtensionWriter();
            if ((this.bitField0_ & 1) == 1) {
                output.writeEnum(1, this.ctype_.getNumber());
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeBool(2, this.packed_);
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeBool(3, this.deprecated_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeBool(5, this.lazy_);
            }
            if ((this.bitField0_ & 0x10) == 16) {
                output.writeBytes(9, this.getExperimentalMapKeyBytes());
            }
            if ((this.bitField0_ & 0x20) == 32) {
                output.writeBool(10, this.weak_);
            }
            for (int i = 0; i < this.uninterpretedOption_.size(); ++i) {
                output.writeMessage(999, this.uninterpretedOption_.get(i));
            }
            extensionWriter.writeUntil(0x20000000, output);
            this.getUnknownFields().writeTo(output);
        }

        @Override
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            size = 0;
            if ((this.bitField0_ & 1) == 1) {
                size += CodedOutputStream.computeEnumSize(1, this.ctype_.getNumber());
            }
            if ((this.bitField0_ & 2) == 2) {
                size += CodedOutputStream.computeBoolSize(2, this.packed_);
            }
            if ((this.bitField0_ & 8) == 8) {
                size += CodedOutputStream.computeBoolSize(3, this.deprecated_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size += CodedOutputStream.computeBoolSize(5, this.lazy_);
            }
            if ((this.bitField0_ & 0x10) == 16) {
                size += CodedOutputStream.computeBytesSize(9, this.getExperimentalMapKeyBytes());
            }
            if ((this.bitField0_ & 0x20) == 32) {
                size += CodedOutputStream.computeBoolSize(10, this.weak_);
            }
            for (int i = 0; i < this.uninterpretedOption_.size(); ++i) {
                size += CodedOutputStream.computeMessageSize(999, this.uninterpretedOption_.get(i));
            }
            size += this.extensionsSerializedSize();
            this.memoizedSerializedSize = size += this.getUnknownFields().getSerializedSize();
            return size;
        }

        @Override
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static FieldOptions parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static FieldOptions parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static FieldOptions parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static FieldOptions parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static FieldOptions parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static FieldOptions parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static FieldOptions parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static FieldOptions parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static FieldOptions parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static FieldOptions parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override
        public Builder newBuilderForType() {
            return FieldOptions.newBuilder();
        }

        public static Builder newBuilder(FieldOptions prototype) {
            return FieldOptions.newBuilder().mergeFrom(prototype);
        }

        @Override
        public Builder toBuilder() {
            return FieldOptions.newBuilder(this);
        }

        @Override
        protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        static {
            PARSER = new AbstractParser<FieldOptions>(){

                @Override
                public FieldOptions parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                    return new FieldOptions(input, extensionRegistry);
                }
            };
            defaultInstance = new FieldOptions(true);
            defaultInstance.initFields();
        }

        /*
         * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
         */
        public static final class Builder
        extends GeneratedMessage.ExtendableBuilder<FieldOptions, Builder>
        implements FieldOptionsOrBuilder {
            private int bitField0_;
            private CType ctype_ = CType.STRING;
            private boolean packed_;
            private boolean lazy_;
            private boolean deprecated_;
            private Object experimentalMapKey_ = "";
            private boolean weak_;
            private List<UninterpretedOption> uninterpretedOption_ = Collections.emptyList();
            private RepeatedFieldBuilder<UninterpretedOption, UninterpretedOption.Builder, UninterpretedOptionOrBuilder> uninterpretedOptionBuilder_;

            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_google_protobuf_FieldOptions_descriptor;
            }

            @Override
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_google_protobuf_FieldOptions_fieldAccessorTable.ensureFieldAccessorsInitialized(FieldOptions.class, Builder.class);
            }

            private Builder() {
                this.maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessage.BuilderParent parent) {
                super(parent);
                this.maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                if (GeneratedMessage.alwaysUseFieldBuilders) {
                    this.getUninterpretedOptionFieldBuilder();
                }
            }

            private static Builder create() {
                return new Builder();
            }

            @Override
            public Builder clear() {
                super.clear();
                this.ctype_ = CType.STRING;
                this.bitField0_ &= 0xFFFFFFFE;
                this.packed_ = false;
                this.bitField0_ &= 0xFFFFFFFD;
                this.lazy_ = false;
                this.bitField0_ &= 0xFFFFFFFB;
                this.deprecated_ = false;
                this.bitField0_ &= 0xFFFFFFF7;
                this.experimentalMapKey_ = "";
                this.bitField0_ &= 0xFFFFFFEF;
                this.weak_ = false;
                this.bitField0_ &= 0xFFFFFFDF;
                if (this.uninterpretedOptionBuilder_ == null) {
                    this.uninterpretedOption_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFFFBF;
                } else {
                    this.uninterpretedOptionBuilder_.clear();
                }
                return this;
            }

            @Override
            public Builder clone() {
                return Builder.create().mergeFrom(this.buildPartial());
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return internal_static_google_protobuf_FieldOptions_descriptor;
            }

            @Override
            public FieldOptions getDefaultInstanceForType() {
                return FieldOptions.getDefaultInstance();
            }

            @Override
            public FieldOptions build() {
                FieldOptions result = this.buildPartial();
                if (!result.isInitialized()) {
                    throw Builder.newUninitializedMessageException(result);
                }
                return result;
            }

            @Override
            public FieldOptions buildPartial() {
                FieldOptions result = new FieldOptions(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ |= 1;
                }
                result.ctype_ = this.ctype_;
                if ((from_bitField0_ & 2) == 2) {
                    to_bitField0_ |= 2;
                }
                result.packed_ = this.packed_;
                if ((from_bitField0_ & 4) == 4) {
                    to_bitField0_ |= 4;
                }
                result.lazy_ = this.lazy_;
                if ((from_bitField0_ & 8) == 8) {
                    to_bitField0_ |= 8;
                }
                result.deprecated_ = this.deprecated_;
                if ((from_bitField0_ & 0x10) == 16) {
                    to_bitField0_ |= 0x10;
                }
                result.experimentalMapKey_ = this.experimentalMapKey_;
                if ((from_bitField0_ & 0x20) == 32) {
                    to_bitField0_ |= 0x20;
                }
                result.weak_ = this.weak_;
                if (this.uninterpretedOptionBuilder_ == null) {
                    if ((this.bitField0_ & 0x40) == 64) {
                        this.uninterpretedOption_ = Collections.unmodifiableList(this.uninterpretedOption_);
                        this.bitField0_ &= 0xFFFFFFBF;
                    }
                    result.uninterpretedOption_ = this.uninterpretedOption_;
                } else {
                    result.uninterpretedOption_ = this.uninterpretedOptionBuilder_.build();
                }
                result.bitField0_ = to_bitField0_;
                this.onBuilt();
                return result;
            }

            @Override
            public Builder mergeFrom(Message other) {
                if (other instanceof FieldOptions) {
                    return this.mergeFrom((FieldOptions)other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(FieldOptions other) {
                if (other == FieldOptions.getDefaultInstance()) {
                    return this;
                }
                if (other.hasCtype()) {
                    this.setCtype(other.getCtype());
                }
                if (other.hasPacked()) {
                    this.setPacked(other.getPacked());
                }
                if (other.hasLazy()) {
                    this.setLazy(other.getLazy());
                }
                if (other.hasDeprecated()) {
                    this.setDeprecated(other.getDeprecated());
                }
                if (other.hasExperimentalMapKey()) {
                    this.bitField0_ |= 0x10;
                    this.experimentalMapKey_ = other.experimentalMapKey_;
                    this.onChanged();
                }
                if (other.hasWeak()) {
                    this.setWeak(other.getWeak());
                }
                if (this.uninterpretedOptionBuilder_ == null) {
                    if (!other.uninterpretedOption_.isEmpty()) {
                        if (this.uninterpretedOption_.isEmpty()) {
                            this.uninterpretedOption_ = other.uninterpretedOption_;
                            this.bitField0_ &= 0xFFFFFFBF;
                        } else {
                            this.ensureUninterpretedOptionIsMutable();
                            this.uninterpretedOption_.addAll(other.uninterpretedOption_);
                        }
                        this.onChanged();
                    }
                } else if (!other.uninterpretedOption_.isEmpty()) {
                    if (this.uninterpretedOptionBuilder_.isEmpty()) {
                        this.uninterpretedOptionBuilder_.dispose();
                        this.uninterpretedOptionBuilder_ = null;
                        this.uninterpretedOption_ = other.uninterpretedOption_;
                        this.bitField0_ &= 0xFFFFFFBF;
                        this.uninterpretedOptionBuilder_ = GeneratedMessage.alwaysUseFieldBuilders ? this.getUninterpretedOptionFieldBuilder() : null;
                    } else {
                        this.uninterpretedOptionBuilder_.addAllMessages(other.uninterpretedOption_);
                    }
                }
                this.mergeExtensionFields(other);
                this.mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            @Override
            public final boolean isInitialized() {
                for (int i = 0; i < this.getUninterpretedOptionCount(); ++i) {
                    if (this.getUninterpretedOption(i).isInitialized()) continue;
                    return false;
                }
                return this.extensionsAreInitialized();
            }

            /*
             * Enabled force condition propagation
             * Lifted jumps to return sites
             */
            @Override
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                FieldOptions parsedMessage = null;
                try {
                    try {
                        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
                    } catch (InvalidProtocolBufferException e) {
                        parsedMessage = (FieldOptions)e.getUnfinishedMessage();
                        throw e;
                    }
                    Object var6_4 = null;
                    if (parsedMessage == null) return this;
                    this.mergeFrom(parsedMessage);
                    return this;
                } catch (Throwable throwable) {
                    Object var6_5 = null;
                    if (parsedMessage == null) throw throwable;
                    this.mergeFrom(parsedMessage);
                    throw throwable;
                }
            }

            @Override
            public boolean hasCtype() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override
            public CType getCtype() {
                return this.ctype_;
            }

            public Builder setCtype(CType value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 1;
                this.ctype_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearCtype() {
                this.bitField0_ &= 0xFFFFFFFE;
                this.ctype_ = CType.STRING;
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasPacked() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override
            public boolean getPacked() {
                return this.packed_;
            }

            public Builder setPacked(boolean value) {
                this.bitField0_ |= 2;
                this.packed_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearPacked() {
                this.bitField0_ &= 0xFFFFFFFD;
                this.packed_ = false;
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasLazy() {
                return (this.bitField0_ & 4) == 4;
            }

            @Override
            public boolean getLazy() {
                return this.lazy_;
            }

            public Builder setLazy(boolean value) {
                this.bitField0_ |= 4;
                this.lazy_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearLazy() {
                this.bitField0_ &= 0xFFFFFFFB;
                this.lazy_ = false;
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasDeprecated() {
                return (this.bitField0_ & 8) == 8;
            }

            @Override
            public boolean getDeprecated() {
                return this.deprecated_;
            }

            public Builder setDeprecated(boolean value) {
                this.bitField0_ |= 8;
                this.deprecated_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearDeprecated() {
                this.bitField0_ &= 0xFFFFFFF7;
                this.deprecated_ = false;
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasExperimentalMapKey() {
                return (this.bitField0_ & 0x10) == 16;
            }

            @Override
            public String getExperimentalMapKey() {
                Object ref = this.experimentalMapKey_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString)ref;
                    String s2 = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.experimentalMapKey_ = s2;
                    }
                    return s2;
                }
                return (String)ref;
            }

            @Override
            public ByteString getExperimentalMapKeyBytes() {
                Object ref = this.experimentalMapKey_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String)ref);
                    this.experimentalMapKey_ = b;
                    return b;
                }
                return (ByteString)ref;
            }

            public Builder setExperimentalMapKey(String value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 0x10;
                this.experimentalMapKey_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearExperimentalMapKey() {
                this.bitField0_ &= 0xFFFFFFEF;
                this.experimentalMapKey_ = FieldOptions.getDefaultInstance().getExperimentalMapKey();
                this.onChanged();
                return this;
            }

            public Builder setExperimentalMapKeyBytes(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 0x10;
                this.experimentalMapKey_ = value;
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasWeak() {
                return (this.bitField0_ & 0x20) == 32;
            }

            @Override
            public boolean getWeak() {
                return this.weak_;
            }

            public Builder setWeak(boolean value) {
                this.bitField0_ |= 0x20;
                this.weak_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearWeak() {
                this.bitField0_ &= 0xFFFFFFDF;
                this.weak_ = false;
                this.onChanged();
                return this;
            }

            private void ensureUninterpretedOptionIsMutable() {
                if ((this.bitField0_ & 0x40) != 64) {
                    this.uninterpretedOption_ = new ArrayList<UninterpretedOption>(this.uninterpretedOption_);
                    this.bitField0_ |= 0x40;
                }
            }

            @Override
            public List<UninterpretedOption> getUninterpretedOptionList() {
                if (this.uninterpretedOptionBuilder_ == null) {
                    return Collections.unmodifiableList(this.uninterpretedOption_);
                }
                return this.uninterpretedOptionBuilder_.getMessageList();
            }

            @Override
            public int getUninterpretedOptionCount() {
                if (this.uninterpretedOptionBuilder_ == null) {
                    return this.uninterpretedOption_.size();
                }
                return this.uninterpretedOptionBuilder_.getCount();
            }

            @Override
            public UninterpretedOption getUninterpretedOption(int index) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    return this.uninterpretedOption_.get(index);
                }
                return this.uninterpretedOptionBuilder_.getMessage(index);
            }

            public Builder setUninterpretedOption(int index, UninterpretedOption value) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureUninterpretedOptionIsMutable();
                    this.uninterpretedOption_.set(index, value);
                    this.onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.setMessage(index, value);
                }
                return this;
            }

            public Builder setUninterpretedOption(int index, UninterpretedOption.Builder builderForValue) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    this.ensureUninterpretedOptionIsMutable();
                    this.uninterpretedOption_.set(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.setMessage(index, builderForValue.build());
                }
                return this;
            }

            public Builder addUninterpretedOption(UninterpretedOption value) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureUninterpretedOptionIsMutable();
                    this.uninterpretedOption_.add(value);
                    this.onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.addMessage(value);
                }
                return this;
            }

            public Builder addUninterpretedOption(int index, UninterpretedOption value) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureUninterpretedOptionIsMutable();
                    this.uninterpretedOption_.add(index, value);
                    this.onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.addMessage(index, value);
                }
                return this;
            }

            public Builder addUninterpretedOption(UninterpretedOption.Builder builderForValue) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    this.ensureUninterpretedOptionIsMutable();
                    this.uninterpretedOption_.add(builderForValue.build());
                    this.onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.addMessage(builderForValue.build());
                }
                return this;
            }

            public Builder addUninterpretedOption(int index, UninterpretedOption.Builder builderForValue) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    this.ensureUninterpretedOptionIsMutable();
                    this.uninterpretedOption_.add(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.addMessage(index, builderForValue.build());
                }
                return this;
            }

            public Builder addAllUninterpretedOption(Iterable<? extends UninterpretedOption> values2) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    this.ensureUninterpretedOptionIsMutable();
                    AbstractMessageLite.Builder.addAll(values2, this.uninterpretedOption_);
                    this.onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.addAllMessages(values2);
                }
                return this;
            }

            public Builder clearUninterpretedOption() {
                if (this.uninterpretedOptionBuilder_ == null) {
                    this.uninterpretedOption_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFFFBF;
                    this.onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.clear();
                }
                return this;
            }

            public Builder removeUninterpretedOption(int index) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    this.ensureUninterpretedOptionIsMutable();
                    this.uninterpretedOption_.remove(index);
                    this.onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.remove(index);
                }
                return this;
            }

            public UninterpretedOption.Builder getUninterpretedOptionBuilder(int index) {
                return this.getUninterpretedOptionFieldBuilder().getBuilder(index);
            }

            @Override
            public UninterpretedOptionOrBuilder getUninterpretedOptionOrBuilder(int index) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    return this.uninterpretedOption_.get(index);
                }
                return this.uninterpretedOptionBuilder_.getMessageOrBuilder(index);
            }

            @Override
            public List<? extends UninterpretedOptionOrBuilder> getUninterpretedOptionOrBuilderList() {
                if (this.uninterpretedOptionBuilder_ != null) {
                    return this.uninterpretedOptionBuilder_.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.uninterpretedOption_);
            }

            public UninterpretedOption.Builder addUninterpretedOptionBuilder() {
                return this.getUninterpretedOptionFieldBuilder().addBuilder(UninterpretedOption.getDefaultInstance());
            }

            public UninterpretedOption.Builder addUninterpretedOptionBuilder(int index) {
                return this.getUninterpretedOptionFieldBuilder().addBuilder(index, UninterpretedOption.getDefaultInstance());
            }

            public List<UninterpretedOption.Builder> getUninterpretedOptionBuilderList() {
                return this.getUninterpretedOptionFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilder<UninterpretedOption, UninterpretedOption.Builder, UninterpretedOptionOrBuilder> getUninterpretedOptionFieldBuilder() {
                if (this.uninterpretedOptionBuilder_ == null) {
                    this.uninterpretedOptionBuilder_ = new RepeatedFieldBuilder(this.uninterpretedOption_, (this.bitField0_ & 0x40) == 64, this.getParentForChildren(), this.isClean());
                    this.uninterpretedOption_ = null;
                }
                return this.uninterpretedOptionBuilder_;
            }
        }

        /*
         * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
         */
        public static enum CType implements ProtocolMessageEnum
        {
            STRING(0, 0),
            CORD(1, 1),
            STRING_PIECE(2, 2);

            public static final int STRING_VALUE = 0;
            public static final int CORD_VALUE = 1;
            public static final int STRING_PIECE_VALUE = 2;
            private static Internal.EnumLiteMap<CType> internalValueMap;
            private static final CType[] VALUES;
            private final int index;
            private final int value;

            @Override
            public final int getNumber() {
                return this.value;
            }

            public static CType valueOf(int value) {
                switch (value) {
                    case 0: {
                        return STRING;
                    }
                    case 1: {
                        return CORD;
                    }
                    case 2: {
                        return STRING_PIECE;
                    }
                }
                return null;
            }

            public static Internal.EnumLiteMap<CType> internalGetValueMap() {
                return internalValueMap;
            }

            @Override
            public final Descriptors.EnumValueDescriptor getValueDescriptor() {
                return CType.getDescriptor().getValues().get(this.index);
            }

            @Override
            public final Descriptors.EnumDescriptor getDescriptorForType() {
                return CType.getDescriptor();
            }

            public static final Descriptors.EnumDescriptor getDescriptor() {
                return FieldOptions.getDescriptor().getEnumTypes().get(0);
            }

            public static CType valueOf(Descriptors.EnumValueDescriptor desc) {
                if (desc.getType() != CType.getDescriptor()) {
                    throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
                }
                return VALUES[desc.getIndex()];
            }

            private CType(int index, int value) {
                this.index = index;
                this.value = value;
            }

            static {
                internalValueMap = new Internal.EnumLiteMap<CType>(){

                    @Override
                    public CType findValueByNumber(int number) {
                        return CType.valueOf(number);
                    }
                };
                VALUES = CType.values();
            }
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static interface FieldOptionsOrBuilder
    extends GeneratedMessage.ExtendableMessageOrBuilder<FieldOptions> {
        public boolean hasCtype();

        public FieldOptions.CType getCtype();

        public boolean hasPacked();

        public boolean getPacked();

        public boolean hasLazy();

        public boolean getLazy();

        public boolean hasDeprecated();

        public boolean getDeprecated();

        public boolean hasExperimentalMapKey();

        public String getExperimentalMapKey();

        public ByteString getExperimentalMapKeyBytes();

        public boolean hasWeak();

        public boolean getWeak();

        public List<UninterpretedOption> getUninterpretedOptionList();

        public UninterpretedOption getUninterpretedOption(int var1);

        public int getUninterpretedOptionCount();

        public List<? extends UninterpretedOptionOrBuilder> getUninterpretedOptionOrBuilderList();

        public UninterpretedOptionOrBuilder getUninterpretedOptionOrBuilder(int var1);
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static final class MessageOptions
    extends GeneratedMessage.ExtendableMessage<MessageOptions>
    implements MessageOptionsOrBuilder {
        private static final MessageOptions defaultInstance;
        private final UnknownFieldSet unknownFields;
        public static Parser<MessageOptions> PARSER;
        private int bitField0_;
        public static final int MESSAGE_SET_WIRE_FORMAT_FIELD_NUMBER = 1;
        private boolean messageSetWireFormat_;
        public static final int NO_STANDARD_DESCRIPTOR_ACCESSOR_FIELD_NUMBER = 2;
        private boolean noStandardDescriptorAccessor_;
        public static final int DEPRECATED_FIELD_NUMBER = 3;
        private boolean deprecated_;
        public static final int UNINTERPRETED_OPTION_FIELD_NUMBER = 999;
        private List<UninterpretedOption> uninterpretedOption_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private static final long serialVersionUID = 0L;

        private MessageOptions(GeneratedMessage.ExtendableBuilder<MessageOptions, ?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte)-1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private MessageOptions(boolean noInit) {
            this.memoizedIsInitialized = (byte)-1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static MessageOptions getDefaultInstance() {
            return defaultInstance;
        }

        @Override
        public MessageOptions getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        private MessageOptions(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            UnknownFieldSet.Builder unknownFields;
            block16: {
                this.memoizedIsInitialized = (byte)-1;
                this.memoizedSerializedSize = -1;
                this.initFields();
                int mutable_bitField0_ = 0;
                unknownFields = UnknownFieldSet.newBuilder();
                try {
                    try {
                        boolean done = false;
                        block12: while (!done) {
                            int tag = input.readTag();
                            switch (tag) {
                                case 0: {
                                    done = true;
                                    continue block12;
                                }
                                default: {
                                    if (this.parseUnknownField(input, unknownFields, extensionRegistry, tag)) continue block12;
                                    done = true;
                                    continue block12;
                                }
                                case 8: {
                                    this.bitField0_ |= 1;
                                    this.messageSetWireFormat_ = input.readBool();
                                    continue block12;
                                }
                                case 16: {
                                    this.bitField0_ |= 2;
                                    this.noStandardDescriptorAccessor_ = input.readBool();
                                    continue block12;
                                }
                                case 24: {
                                    this.bitField0_ |= 4;
                                    this.deprecated_ = input.readBool();
                                    continue block12;
                                }
                                case 7994: 
                            }
                            if ((mutable_bitField0_ & 8) != 8) {
                                this.uninterpretedOption_ = new ArrayList<UninterpretedOption>();
                                mutable_bitField0_ |= 8;
                            }
                            this.uninterpretedOption_.add(input.readMessage(UninterpretedOption.PARSER, extensionRegistry));
                        }
                        Object var8_9 = null;
                        if ((mutable_bitField0_ & 8) != 8) break block16;
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e) {
                        throw new InvalidProtocolBufferException(e.getMessage()).setUnfinishedMessage(this);
                    }
                } catch (Throwable throwable) {
                    Object var8_10 = null;
                    if ((mutable_bitField0_ & 8) == 8) {
                        this.uninterpretedOption_ = Collections.unmodifiableList(this.uninterpretedOption_);
                    }
                    this.unknownFields = unknownFields.build();
                    this.makeExtensionsImmutable();
                    throw throwable;
                }
                this.uninterpretedOption_ = Collections.unmodifiableList(this.uninterpretedOption_);
            }
            this.unknownFields = unknownFields.build();
            this.makeExtensionsImmutable();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return internal_static_google_protobuf_MessageOptions_descriptor;
        }

        @Override
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return internal_static_google_protobuf_MessageOptions_fieldAccessorTable.ensureFieldAccessorsInitialized(MessageOptions.class, Builder.class);
        }

        public Parser<MessageOptions> getParserForType() {
            return PARSER;
        }

        @Override
        public boolean hasMessageSetWireFormat() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override
        public boolean getMessageSetWireFormat() {
            return this.messageSetWireFormat_;
        }

        @Override
        public boolean hasNoStandardDescriptorAccessor() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override
        public boolean getNoStandardDescriptorAccessor() {
            return this.noStandardDescriptorAccessor_;
        }

        @Override
        public boolean hasDeprecated() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override
        public boolean getDeprecated() {
            return this.deprecated_;
        }

        @Override
        public List<UninterpretedOption> getUninterpretedOptionList() {
            return this.uninterpretedOption_;
        }

        @Override
        public List<? extends UninterpretedOptionOrBuilder> getUninterpretedOptionOrBuilderList() {
            return this.uninterpretedOption_;
        }

        @Override
        public int getUninterpretedOptionCount() {
            return this.uninterpretedOption_.size();
        }

        @Override
        public UninterpretedOption getUninterpretedOption(int index) {
            return this.uninterpretedOption_.get(index);
        }

        @Override
        public UninterpretedOptionOrBuilder getUninterpretedOptionOrBuilder(int index) {
            return this.uninterpretedOption_.get(index);
        }

        private void initFields() {
            this.messageSetWireFormat_ = false;
            this.noStandardDescriptorAccessor_ = false;
            this.deprecated_ = false;
            this.uninterpretedOption_ = Collections.emptyList();
        }

        @Override
        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            for (int i = 0; i < this.getUninterpretedOptionCount(); ++i) {
                if (this.getUninterpretedOption(i).isInitialized()) continue;
                this.memoizedIsInitialized = 0;
                return false;
            }
            if (!this.extensionsAreInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        @Override
        public void writeTo(CodedOutputStream output) throws IOException {
            this.getSerializedSize();
            GeneratedMessage.ExtendableMessage.ExtensionWriter extensionWriter = this.newExtensionWriter();
            if ((this.bitField0_ & 1) == 1) {
                output.writeBool(1, this.messageSetWireFormat_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeBool(2, this.noStandardDescriptorAccessor_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeBool(3, this.deprecated_);
            }
            for (int i = 0; i < this.uninterpretedOption_.size(); ++i) {
                output.writeMessage(999, this.uninterpretedOption_.get(i));
            }
            extensionWriter.writeUntil(0x20000000, output);
            this.getUnknownFields().writeTo(output);
        }

        @Override
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            size = 0;
            if ((this.bitField0_ & 1) == 1) {
                size += CodedOutputStream.computeBoolSize(1, this.messageSetWireFormat_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size += CodedOutputStream.computeBoolSize(2, this.noStandardDescriptorAccessor_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size += CodedOutputStream.computeBoolSize(3, this.deprecated_);
            }
            for (int i = 0; i < this.uninterpretedOption_.size(); ++i) {
                size += CodedOutputStream.computeMessageSize(999, this.uninterpretedOption_.get(i));
            }
            size += this.extensionsSerializedSize();
            this.memoizedSerializedSize = size += this.getUnknownFields().getSerializedSize();
            return size;
        }

        @Override
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static MessageOptions parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static MessageOptions parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static MessageOptions parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static MessageOptions parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static MessageOptions parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static MessageOptions parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static MessageOptions parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static MessageOptions parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static MessageOptions parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static MessageOptions parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override
        public Builder newBuilderForType() {
            return MessageOptions.newBuilder();
        }

        public static Builder newBuilder(MessageOptions prototype) {
            return MessageOptions.newBuilder().mergeFrom(prototype);
        }

        @Override
        public Builder toBuilder() {
            return MessageOptions.newBuilder(this);
        }

        @Override
        protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        static {
            PARSER = new AbstractParser<MessageOptions>(){

                @Override
                public MessageOptions parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                    return new MessageOptions(input, extensionRegistry);
                }
            };
            defaultInstance = new MessageOptions(true);
            defaultInstance.initFields();
        }

        /*
         * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
         */
        public static final class Builder
        extends GeneratedMessage.ExtendableBuilder<MessageOptions, Builder>
        implements MessageOptionsOrBuilder {
            private int bitField0_;
            private boolean messageSetWireFormat_;
            private boolean noStandardDescriptorAccessor_;
            private boolean deprecated_;
            private List<UninterpretedOption> uninterpretedOption_ = Collections.emptyList();
            private RepeatedFieldBuilder<UninterpretedOption, UninterpretedOption.Builder, UninterpretedOptionOrBuilder> uninterpretedOptionBuilder_;

            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_google_protobuf_MessageOptions_descriptor;
            }

            @Override
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_google_protobuf_MessageOptions_fieldAccessorTable.ensureFieldAccessorsInitialized(MessageOptions.class, Builder.class);
            }

            private Builder() {
                this.maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessage.BuilderParent parent) {
                super(parent);
                this.maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                if (GeneratedMessage.alwaysUseFieldBuilders) {
                    this.getUninterpretedOptionFieldBuilder();
                }
            }

            private static Builder create() {
                return new Builder();
            }

            @Override
            public Builder clear() {
                super.clear();
                this.messageSetWireFormat_ = false;
                this.bitField0_ &= 0xFFFFFFFE;
                this.noStandardDescriptorAccessor_ = false;
                this.bitField0_ &= 0xFFFFFFFD;
                this.deprecated_ = false;
                this.bitField0_ &= 0xFFFFFFFB;
                if (this.uninterpretedOptionBuilder_ == null) {
                    this.uninterpretedOption_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFFFF7;
                } else {
                    this.uninterpretedOptionBuilder_.clear();
                }
                return this;
            }

            @Override
            public Builder clone() {
                return Builder.create().mergeFrom(this.buildPartial());
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return internal_static_google_protobuf_MessageOptions_descriptor;
            }

            @Override
            public MessageOptions getDefaultInstanceForType() {
                return MessageOptions.getDefaultInstance();
            }

            @Override
            public MessageOptions build() {
                MessageOptions result = this.buildPartial();
                if (!result.isInitialized()) {
                    throw Builder.newUninitializedMessageException(result);
                }
                return result;
            }

            @Override
            public MessageOptions buildPartial() {
                MessageOptions result = new MessageOptions(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ |= 1;
                }
                result.messageSetWireFormat_ = this.messageSetWireFormat_;
                if ((from_bitField0_ & 2) == 2) {
                    to_bitField0_ |= 2;
                }
                result.noStandardDescriptorAccessor_ = this.noStandardDescriptorAccessor_;
                if ((from_bitField0_ & 4) == 4) {
                    to_bitField0_ |= 4;
                }
                result.deprecated_ = this.deprecated_;
                if (this.uninterpretedOptionBuilder_ == null) {
                    if ((this.bitField0_ & 8) == 8) {
                        this.uninterpretedOption_ = Collections.unmodifiableList(this.uninterpretedOption_);
                        this.bitField0_ &= 0xFFFFFFF7;
                    }
                    result.uninterpretedOption_ = this.uninterpretedOption_;
                } else {
                    result.uninterpretedOption_ = this.uninterpretedOptionBuilder_.build();
                }
                result.bitField0_ = to_bitField0_;
                this.onBuilt();
                return result;
            }

            @Override
            public Builder mergeFrom(Message other) {
                if (other instanceof MessageOptions) {
                    return this.mergeFrom((MessageOptions)other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(MessageOptions other) {
                if (other == MessageOptions.getDefaultInstance()) {
                    return this;
                }
                if (other.hasMessageSetWireFormat()) {
                    this.setMessageSetWireFormat(other.getMessageSetWireFormat());
                }
                if (other.hasNoStandardDescriptorAccessor()) {
                    this.setNoStandardDescriptorAccessor(other.getNoStandardDescriptorAccessor());
                }
                if (other.hasDeprecated()) {
                    this.setDeprecated(other.getDeprecated());
                }
                if (this.uninterpretedOptionBuilder_ == null) {
                    if (!other.uninterpretedOption_.isEmpty()) {
                        if (this.uninterpretedOption_.isEmpty()) {
                            this.uninterpretedOption_ = other.uninterpretedOption_;
                            this.bitField0_ &= 0xFFFFFFF7;
                        } else {
                            this.ensureUninterpretedOptionIsMutable();
                            this.uninterpretedOption_.addAll(other.uninterpretedOption_);
                        }
                        this.onChanged();
                    }
                } else if (!other.uninterpretedOption_.isEmpty()) {
                    if (this.uninterpretedOptionBuilder_.isEmpty()) {
                        this.uninterpretedOptionBuilder_.dispose();
                        this.uninterpretedOptionBuilder_ = null;
                        this.uninterpretedOption_ = other.uninterpretedOption_;
                        this.bitField0_ &= 0xFFFFFFF7;
                        this.uninterpretedOptionBuilder_ = GeneratedMessage.alwaysUseFieldBuilders ? this.getUninterpretedOptionFieldBuilder() : null;
                    } else {
                        this.uninterpretedOptionBuilder_.addAllMessages(other.uninterpretedOption_);
                    }
                }
                this.mergeExtensionFields(other);
                this.mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            @Override
            public final boolean isInitialized() {
                for (int i = 0; i < this.getUninterpretedOptionCount(); ++i) {
                    if (this.getUninterpretedOption(i).isInitialized()) continue;
                    return false;
                }
                return this.extensionsAreInitialized();
            }

            /*
             * Enabled force condition propagation
             * Lifted jumps to return sites
             */
            @Override
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                MessageOptions parsedMessage = null;
                try {
                    try {
                        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
                    } catch (InvalidProtocolBufferException e) {
                        parsedMessage = (MessageOptions)e.getUnfinishedMessage();
                        throw e;
                    }
                    Object var6_4 = null;
                    if (parsedMessage == null) return this;
                    this.mergeFrom(parsedMessage);
                    return this;
                } catch (Throwable throwable) {
                    Object var6_5 = null;
                    if (parsedMessage == null) throw throwable;
                    this.mergeFrom(parsedMessage);
                    throw throwable;
                }
            }

            @Override
            public boolean hasMessageSetWireFormat() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override
            public boolean getMessageSetWireFormat() {
                return this.messageSetWireFormat_;
            }

            public Builder setMessageSetWireFormat(boolean value) {
                this.bitField0_ |= 1;
                this.messageSetWireFormat_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearMessageSetWireFormat() {
                this.bitField0_ &= 0xFFFFFFFE;
                this.messageSetWireFormat_ = false;
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasNoStandardDescriptorAccessor() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override
            public boolean getNoStandardDescriptorAccessor() {
                return this.noStandardDescriptorAccessor_;
            }

            public Builder setNoStandardDescriptorAccessor(boolean value) {
                this.bitField0_ |= 2;
                this.noStandardDescriptorAccessor_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearNoStandardDescriptorAccessor() {
                this.bitField0_ &= 0xFFFFFFFD;
                this.noStandardDescriptorAccessor_ = false;
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasDeprecated() {
                return (this.bitField0_ & 4) == 4;
            }

            @Override
            public boolean getDeprecated() {
                return this.deprecated_;
            }

            public Builder setDeprecated(boolean value) {
                this.bitField0_ |= 4;
                this.deprecated_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearDeprecated() {
                this.bitField0_ &= 0xFFFFFFFB;
                this.deprecated_ = false;
                this.onChanged();
                return this;
            }

            private void ensureUninterpretedOptionIsMutable() {
                if ((this.bitField0_ & 8) != 8) {
                    this.uninterpretedOption_ = new ArrayList<UninterpretedOption>(this.uninterpretedOption_);
                    this.bitField0_ |= 8;
                }
            }

            @Override
            public List<UninterpretedOption> getUninterpretedOptionList() {
                if (this.uninterpretedOptionBuilder_ == null) {
                    return Collections.unmodifiableList(this.uninterpretedOption_);
                }
                return this.uninterpretedOptionBuilder_.getMessageList();
            }

            @Override
            public int getUninterpretedOptionCount() {
                if (this.uninterpretedOptionBuilder_ == null) {
                    return this.uninterpretedOption_.size();
                }
                return this.uninterpretedOptionBuilder_.getCount();
            }

            @Override
            public UninterpretedOption getUninterpretedOption(int index) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    return this.uninterpretedOption_.get(index);
                }
                return this.uninterpretedOptionBuilder_.getMessage(index);
            }

            public Builder setUninterpretedOption(int index, UninterpretedOption value) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureUninterpretedOptionIsMutable();
                    this.uninterpretedOption_.set(index, value);
                    this.onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.setMessage(index, value);
                }
                return this;
            }

            public Builder setUninterpretedOption(int index, UninterpretedOption.Builder builderForValue) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    this.ensureUninterpretedOptionIsMutable();
                    this.uninterpretedOption_.set(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.setMessage(index, builderForValue.build());
                }
                return this;
            }

            public Builder addUninterpretedOption(UninterpretedOption value) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureUninterpretedOptionIsMutable();
                    this.uninterpretedOption_.add(value);
                    this.onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.addMessage(value);
                }
                return this;
            }

            public Builder addUninterpretedOption(int index, UninterpretedOption value) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureUninterpretedOptionIsMutable();
                    this.uninterpretedOption_.add(index, value);
                    this.onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.addMessage(index, value);
                }
                return this;
            }

            public Builder addUninterpretedOption(UninterpretedOption.Builder builderForValue) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    this.ensureUninterpretedOptionIsMutable();
                    this.uninterpretedOption_.add(builderForValue.build());
                    this.onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.addMessage(builderForValue.build());
                }
                return this;
            }

            public Builder addUninterpretedOption(int index, UninterpretedOption.Builder builderForValue) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    this.ensureUninterpretedOptionIsMutable();
                    this.uninterpretedOption_.add(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.addMessage(index, builderForValue.build());
                }
                return this;
            }

            public Builder addAllUninterpretedOption(Iterable<? extends UninterpretedOption> values2) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    this.ensureUninterpretedOptionIsMutable();
                    AbstractMessageLite.Builder.addAll(values2, this.uninterpretedOption_);
                    this.onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.addAllMessages(values2);
                }
                return this;
            }

            public Builder clearUninterpretedOption() {
                if (this.uninterpretedOptionBuilder_ == null) {
                    this.uninterpretedOption_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFFFF7;
                    this.onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.clear();
                }
                return this;
            }

            public Builder removeUninterpretedOption(int index) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    this.ensureUninterpretedOptionIsMutable();
                    this.uninterpretedOption_.remove(index);
                    this.onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.remove(index);
                }
                return this;
            }

            public UninterpretedOption.Builder getUninterpretedOptionBuilder(int index) {
                return this.getUninterpretedOptionFieldBuilder().getBuilder(index);
            }

            @Override
            public UninterpretedOptionOrBuilder getUninterpretedOptionOrBuilder(int index) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    return this.uninterpretedOption_.get(index);
                }
                return this.uninterpretedOptionBuilder_.getMessageOrBuilder(index);
            }

            @Override
            public List<? extends UninterpretedOptionOrBuilder> getUninterpretedOptionOrBuilderList() {
                if (this.uninterpretedOptionBuilder_ != null) {
                    return this.uninterpretedOptionBuilder_.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.uninterpretedOption_);
            }

            public UninterpretedOption.Builder addUninterpretedOptionBuilder() {
                return this.getUninterpretedOptionFieldBuilder().addBuilder(UninterpretedOption.getDefaultInstance());
            }

            public UninterpretedOption.Builder addUninterpretedOptionBuilder(int index) {
                return this.getUninterpretedOptionFieldBuilder().addBuilder(index, UninterpretedOption.getDefaultInstance());
            }

            public List<UninterpretedOption.Builder> getUninterpretedOptionBuilderList() {
                return this.getUninterpretedOptionFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilder<UninterpretedOption, UninterpretedOption.Builder, UninterpretedOptionOrBuilder> getUninterpretedOptionFieldBuilder() {
                if (this.uninterpretedOptionBuilder_ == null) {
                    this.uninterpretedOptionBuilder_ = new RepeatedFieldBuilder(this.uninterpretedOption_, (this.bitField0_ & 8) == 8, this.getParentForChildren(), this.isClean());
                    this.uninterpretedOption_ = null;
                }
                return this.uninterpretedOptionBuilder_;
            }
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static interface MessageOptionsOrBuilder
    extends GeneratedMessage.ExtendableMessageOrBuilder<MessageOptions> {
        public boolean hasMessageSetWireFormat();

        public boolean getMessageSetWireFormat();

        public boolean hasNoStandardDescriptorAccessor();

        public boolean getNoStandardDescriptorAccessor();

        public boolean hasDeprecated();

        public boolean getDeprecated();

        public List<UninterpretedOption> getUninterpretedOptionList();

        public UninterpretedOption getUninterpretedOption(int var1);

        public int getUninterpretedOptionCount();

        public List<? extends UninterpretedOptionOrBuilder> getUninterpretedOptionOrBuilderList();

        public UninterpretedOptionOrBuilder getUninterpretedOptionOrBuilder(int var1);
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static final class FileOptions
    extends GeneratedMessage.ExtendableMessage<FileOptions>
    implements FileOptionsOrBuilder {
        private static final FileOptions defaultInstance;
        private final UnknownFieldSet unknownFields;
        public static Parser<FileOptions> PARSER;
        private int bitField0_;
        public static final int JAVA_PACKAGE_FIELD_NUMBER = 1;
        private Object javaPackage_;
        public static final int JAVA_OUTER_CLASSNAME_FIELD_NUMBER = 8;
        private Object javaOuterClassname_;
        public static final int JAVA_MULTIPLE_FILES_FIELD_NUMBER = 10;
        private boolean javaMultipleFiles_;
        public static final int JAVA_GENERATE_EQUALS_AND_HASH_FIELD_NUMBER = 20;
        private boolean javaGenerateEqualsAndHash_;
        public static final int JAVA_STRING_CHECK_UTF8_FIELD_NUMBER = 27;
        private boolean javaStringCheckUtf8_;
        public static final int OPTIMIZE_FOR_FIELD_NUMBER = 9;
        private OptimizeMode optimizeFor_;
        public static final int GO_PACKAGE_FIELD_NUMBER = 11;
        private Object goPackage_;
        public static final int CC_GENERIC_SERVICES_FIELD_NUMBER = 16;
        private boolean ccGenericServices_;
        public static final int JAVA_GENERIC_SERVICES_FIELD_NUMBER = 17;
        private boolean javaGenericServices_;
        public static final int PY_GENERIC_SERVICES_FIELD_NUMBER = 18;
        private boolean pyGenericServices_;
        public static final int DEPRECATED_FIELD_NUMBER = 23;
        private boolean deprecated_;
        public static final int UNINTERPRETED_OPTION_FIELD_NUMBER = 999;
        private List<UninterpretedOption> uninterpretedOption_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private static final long serialVersionUID = 0L;

        private FileOptions(GeneratedMessage.ExtendableBuilder<FileOptions, ?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte)-1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private FileOptions(boolean noInit) {
            this.memoizedIsInitialized = (byte)-1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static FileOptions getDefaultInstance() {
            return defaultInstance;
        }

        @Override
        public FileOptions getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        private FileOptions(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            UnknownFieldSet.Builder unknownFields;
            block25: {
                this.memoizedIsInitialized = (byte)-1;
                this.memoizedSerializedSize = -1;
                this.initFields();
                int mutable_bitField0_ = 0;
                unknownFields = UnknownFieldSet.newBuilder();
                try {
                    try {
                        boolean done = false;
                        block20: while (!done) {
                            int tag = input.readTag();
                            switch (tag) {
                                case 0: {
                                    done = true;
                                    continue block20;
                                }
                                default: {
                                    if (this.parseUnknownField(input, unknownFields, extensionRegistry, tag)) continue block20;
                                    done = true;
                                    continue block20;
                                }
                                case 10: {
                                    ByteString bs = input.readBytes();
                                    this.bitField0_ |= 1;
                                    this.javaPackage_ = bs;
                                    continue block20;
                                }
                                case 66: {
                                    ByteString bs = input.readBytes();
                                    this.bitField0_ |= 2;
                                    this.javaOuterClassname_ = bs;
                                    continue block20;
                                }
                                case 72: {
                                    int rawValue = input.readEnum();
                                    OptimizeMode value = OptimizeMode.valueOf(rawValue);
                                    if (value == null) {
                                        unknownFields.mergeVarintField(9, rawValue);
                                        continue block20;
                                    }
                                    this.bitField0_ |= 0x20;
                                    this.optimizeFor_ = value;
                                    continue block20;
                                }
                                case 80: {
                                    this.bitField0_ |= 4;
                                    this.javaMultipleFiles_ = input.readBool();
                                    continue block20;
                                }
                                case 90: {
                                    ByteString bs = input.readBytes();
                                    this.bitField0_ |= 0x40;
                                    this.goPackage_ = bs;
                                    continue block20;
                                }
                                case 128: {
                                    this.bitField0_ |= 0x80;
                                    this.ccGenericServices_ = input.readBool();
                                    continue block20;
                                }
                                case 136: {
                                    this.bitField0_ |= 0x100;
                                    this.javaGenericServices_ = input.readBool();
                                    continue block20;
                                }
                                case 144: {
                                    this.bitField0_ |= 0x200;
                                    this.pyGenericServices_ = input.readBool();
                                    continue block20;
                                }
                                case 160: {
                                    this.bitField0_ |= 8;
                                    this.javaGenerateEqualsAndHash_ = input.readBool();
                                    continue block20;
                                }
                                case 184: {
                                    this.bitField0_ |= 0x400;
                                    this.deprecated_ = input.readBool();
                                    continue block20;
                                }
                                case 216: {
                                    this.bitField0_ |= 0x10;
                                    this.javaStringCheckUtf8_ = input.readBool();
                                    continue block20;
                                }
                                case 7994: 
                            }
                            if ((mutable_bitField0_ & 0x800) != 2048) {
                                this.uninterpretedOption_ = new ArrayList<UninterpretedOption>();
                                mutable_bitField0_ |= 0x800;
                            }
                            this.uninterpretedOption_.add(input.readMessage(UninterpretedOption.PARSER, extensionRegistry));
                        }
                        Object var10_14 = null;
                        if ((mutable_bitField0_ & 0x800) != 2048) break block25;
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e) {
                        throw new InvalidProtocolBufferException(e.getMessage()).setUnfinishedMessage(this);
                    }
                } catch (Throwable throwable) {
                    Object var10_15 = null;
                    if ((mutable_bitField0_ & 0x800) == 2048) {
                        this.uninterpretedOption_ = Collections.unmodifiableList(this.uninterpretedOption_);
                    }
                    this.unknownFields = unknownFields.build();
                    this.makeExtensionsImmutable();
                    throw throwable;
                }
                this.uninterpretedOption_ = Collections.unmodifiableList(this.uninterpretedOption_);
            }
            this.unknownFields = unknownFields.build();
            this.makeExtensionsImmutable();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return internal_static_google_protobuf_FileOptions_descriptor;
        }

        @Override
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return internal_static_google_protobuf_FileOptions_fieldAccessorTable.ensureFieldAccessorsInitialized(FileOptions.class, Builder.class);
        }

        public Parser<FileOptions> getParserForType() {
            return PARSER;
        }

        @Override
        public boolean hasJavaPackage() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override
        public String getJavaPackage() {
            Object ref = this.javaPackage_;
            if (ref instanceof String) {
                return (String)ref;
            }
            ByteString bs = (ByteString)ref;
            String s2 = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.javaPackage_ = s2;
            }
            return s2;
        }

        @Override
        public ByteString getJavaPackageBytes() {
            Object ref = this.javaPackage_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.javaPackage_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        @Override
        public boolean hasJavaOuterClassname() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override
        public String getJavaOuterClassname() {
            Object ref = this.javaOuterClassname_;
            if (ref instanceof String) {
                return (String)ref;
            }
            ByteString bs = (ByteString)ref;
            String s2 = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.javaOuterClassname_ = s2;
            }
            return s2;
        }

        @Override
        public ByteString getJavaOuterClassnameBytes() {
            Object ref = this.javaOuterClassname_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.javaOuterClassname_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        @Override
        public boolean hasJavaMultipleFiles() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override
        public boolean getJavaMultipleFiles() {
            return this.javaMultipleFiles_;
        }

        @Override
        public boolean hasJavaGenerateEqualsAndHash() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override
        public boolean getJavaGenerateEqualsAndHash() {
            return this.javaGenerateEqualsAndHash_;
        }

        @Override
        public boolean hasJavaStringCheckUtf8() {
            return (this.bitField0_ & 0x10) == 16;
        }

        @Override
        public boolean getJavaStringCheckUtf8() {
            return this.javaStringCheckUtf8_;
        }

        @Override
        public boolean hasOptimizeFor() {
            return (this.bitField0_ & 0x20) == 32;
        }

        @Override
        public OptimizeMode getOptimizeFor() {
            return this.optimizeFor_;
        }

        @Override
        public boolean hasGoPackage() {
            return (this.bitField0_ & 0x40) == 64;
        }

        @Override
        public String getGoPackage() {
            Object ref = this.goPackage_;
            if (ref instanceof String) {
                return (String)ref;
            }
            ByteString bs = (ByteString)ref;
            String s2 = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.goPackage_ = s2;
            }
            return s2;
        }

        @Override
        public ByteString getGoPackageBytes() {
            Object ref = this.goPackage_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.goPackage_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        @Override
        public boolean hasCcGenericServices() {
            return (this.bitField0_ & 0x80) == 128;
        }

        @Override
        public boolean getCcGenericServices() {
            return this.ccGenericServices_;
        }

        @Override
        public boolean hasJavaGenericServices() {
            return (this.bitField0_ & 0x100) == 256;
        }

        @Override
        public boolean getJavaGenericServices() {
            return this.javaGenericServices_;
        }

        @Override
        public boolean hasPyGenericServices() {
            return (this.bitField0_ & 0x200) == 512;
        }

        @Override
        public boolean getPyGenericServices() {
            return this.pyGenericServices_;
        }

        @Override
        public boolean hasDeprecated() {
            return (this.bitField0_ & 0x400) == 1024;
        }

        @Override
        public boolean getDeprecated() {
            return this.deprecated_;
        }

        @Override
        public List<UninterpretedOption> getUninterpretedOptionList() {
            return this.uninterpretedOption_;
        }

        @Override
        public List<? extends UninterpretedOptionOrBuilder> getUninterpretedOptionOrBuilderList() {
            return this.uninterpretedOption_;
        }

        @Override
        public int getUninterpretedOptionCount() {
            return this.uninterpretedOption_.size();
        }

        @Override
        public UninterpretedOption getUninterpretedOption(int index) {
            return this.uninterpretedOption_.get(index);
        }

        @Override
        public UninterpretedOptionOrBuilder getUninterpretedOptionOrBuilder(int index) {
            return this.uninterpretedOption_.get(index);
        }

        private void initFields() {
            this.javaPackage_ = "";
            this.javaOuterClassname_ = "";
            this.javaMultipleFiles_ = false;
            this.javaGenerateEqualsAndHash_ = false;
            this.javaStringCheckUtf8_ = false;
            this.optimizeFor_ = OptimizeMode.SPEED;
            this.goPackage_ = "";
            this.ccGenericServices_ = false;
            this.javaGenericServices_ = false;
            this.pyGenericServices_ = false;
            this.deprecated_ = false;
            this.uninterpretedOption_ = Collections.emptyList();
        }

        @Override
        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            for (int i = 0; i < this.getUninterpretedOptionCount(); ++i) {
                if (this.getUninterpretedOption(i).isInitialized()) continue;
                this.memoizedIsInitialized = 0;
                return false;
            }
            if (!this.extensionsAreInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        @Override
        public void writeTo(CodedOutputStream output) throws IOException {
            this.getSerializedSize();
            GeneratedMessage.ExtendableMessage.ExtensionWriter extensionWriter = this.newExtensionWriter();
            if ((this.bitField0_ & 1) == 1) {
                output.writeBytes(1, this.getJavaPackageBytes());
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeBytes(8, this.getJavaOuterClassnameBytes());
            }
            if ((this.bitField0_ & 0x20) == 32) {
                output.writeEnum(9, this.optimizeFor_.getNumber());
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeBool(10, this.javaMultipleFiles_);
            }
            if ((this.bitField0_ & 0x40) == 64) {
                output.writeBytes(11, this.getGoPackageBytes());
            }
            if ((this.bitField0_ & 0x80) == 128) {
                output.writeBool(16, this.ccGenericServices_);
            }
            if ((this.bitField0_ & 0x100) == 256) {
                output.writeBool(17, this.javaGenericServices_);
            }
            if ((this.bitField0_ & 0x200) == 512) {
                output.writeBool(18, this.pyGenericServices_);
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeBool(20, this.javaGenerateEqualsAndHash_);
            }
            if ((this.bitField0_ & 0x400) == 1024) {
                output.writeBool(23, this.deprecated_);
            }
            if ((this.bitField0_ & 0x10) == 16) {
                output.writeBool(27, this.javaStringCheckUtf8_);
            }
            for (int i = 0; i < this.uninterpretedOption_.size(); ++i) {
                output.writeMessage(999, this.uninterpretedOption_.get(i));
            }
            extensionWriter.writeUntil(0x20000000, output);
            this.getUnknownFields().writeTo(output);
        }

        @Override
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            size = 0;
            if ((this.bitField0_ & 1) == 1) {
                size += CodedOutputStream.computeBytesSize(1, this.getJavaPackageBytes());
            }
            if ((this.bitField0_ & 2) == 2) {
                size += CodedOutputStream.computeBytesSize(8, this.getJavaOuterClassnameBytes());
            }
            if ((this.bitField0_ & 0x20) == 32) {
                size += CodedOutputStream.computeEnumSize(9, this.optimizeFor_.getNumber());
            }
            if ((this.bitField0_ & 4) == 4) {
                size += CodedOutputStream.computeBoolSize(10, this.javaMultipleFiles_);
            }
            if ((this.bitField0_ & 0x40) == 64) {
                size += CodedOutputStream.computeBytesSize(11, this.getGoPackageBytes());
            }
            if ((this.bitField0_ & 0x80) == 128) {
                size += CodedOutputStream.computeBoolSize(16, this.ccGenericServices_);
            }
            if ((this.bitField0_ & 0x100) == 256) {
                size += CodedOutputStream.computeBoolSize(17, this.javaGenericServices_);
            }
            if ((this.bitField0_ & 0x200) == 512) {
                size += CodedOutputStream.computeBoolSize(18, this.pyGenericServices_);
            }
            if ((this.bitField0_ & 8) == 8) {
                size += CodedOutputStream.computeBoolSize(20, this.javaGenerateEqualsAndHash_);
            }
            if ((this.bitField0_ & 0x400) == 1024) {
                size += CodedOutputStream.computeBoolSize(23, this.deprecated_);
            }
            if ((this.bitField0_ & 0x10) == 16) {
                size += CodedOutputStream.computeBoolSize(27, this.javaStringCheckUtf8_);
            }
            for (int i = 0; i < this.uninterpretedOption_.size(); ++i) {
                size += CodedOutputStream.computeMessageSize(999, this.uninterpretedOption_.get(i));
            }
            size += this.extensionsSerializedSize();
            this.memoizedSerializedSize = size += this.getUnknownFields().getSerializedSize();
            return size;
        }

        @Override
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static FileOptions parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static FileOptions parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static FileOptions parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static FileOptions parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static FileOptions parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static FileOptions parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static FileOptions parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static FileOptions parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static FileOptions parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static FileOptions parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override
        public Builder newBuilderForType() {
            return FileOptions.newBuilder();
        }

        public static Builder newBuilder(FileOptions prototype) {
            return FileOptions.newBuilder().mergeFrom(prototype);
        }

        @Override
        public Builder toBuilder() {
            return FileOptions.newBuilder(this);
        }

        @Override
        protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        static {
            PARSER = new AbstractParser<FileOptions>(){

                @Override
                public FileOptions parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                    return new FileOptions(input, extensionRegistry);
                }
            };
            defaultInstance = new FileOptions(true);
            defaultInstance.initFields();
        }

        /*
         * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
         */
        public static final class Builder
        extends GeneratedMessage.ExtendableBuilder<FileOptions, Builder>
        implements FileOptionsOrBuilder {
            private int bitField0_;
            private Object javaPackage_ = "";
            private Object javaOuterClassname_ = "";
            private boolean javaMultipleFiles_;
            private boolean javaGenerateEqualsAndHash_;
            private boolean javaStringCheckUtf8_;
            private OptimizeMode optimizeFor_ = OptimizeMode.SPEED;
            private Object goPackage_ = "";
            private boolean ccGenericServices_;
            private boolean javaGenericServices_;
            private boolean pyGenericServices_;
            private boolean deprecated_;
            private List<UninterpretedOption> uninterpretedOption_ = Collections.emptyList();
            private RepeatedFieldBuilder<UninterpretedOption, UninterpretedOption.Builder, UninterpretedOptionOrBuilder> uninterpretedOptionBuilder_;

            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_google_protobuf_FileOptions_descriptor;
            }

            @Override
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_google_protobuf_FileOptions_fieldAccessorTable.ensureFieldAccessorsInitialized(FileOptions.class, Builder.class);
            }

            private Builder() {
                this.maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessage.BuilderParent parent) {
                super(parent);
                this.maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                if (GeneratedMessage.alwaysUseFieldBuilders) {
                    this.getUninterpretedOptionFieldBuilder();
                }
            }

            private static Builder create() {
                return new Builder();
            }

            @Override
            public Builder clear() {
                super.clear();
                this.javaPackage_ = "";
                this.bitField0_ &= 0xFFFFFFFE;
                this.javaOuterClassname_ = "";
                this.bitField0_ &= 0xFFFFFFFD;
                this.javaMultipleFiles_ = false;
                this.bitField0_ &= 0xFFFFFFFB;
                this.javaGenerateEqualsAndHash_ = false;
                this.bitField0_ &= 0xFFFFFFF7;
                this.javaStringCheckUtf8_ = false;
                this.bitField0_ &= 0xFFFFFFEF;
                this.optimizeFor_ = OptimizeMode.SPEED;
                this.bitField0_ &= 0xFFFFFFDF;
                this.goPackage_ = "";
                this.bitField0_ &= 0xFFFFFFBF;
                this.ccGenericServices_ = false;
                this.bitField0_ &= 0xFFFFFF7F;
                this.javaGenericServices_ = false;
                this.bitField0_ &= 0xFFFFFEFF;
                this.pyGenericServices_ = false;
                this.bitField0_ &= 0xFFFFFDFF;
                this.deprecated_ = false;
                this.bitField0_ &= 0xFFFFFBFF;
                if (this.uninterpretedOptionBuilder_ == null) {
                    this.uninterpretedOption_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFF7FF;
                } else {
                    this.uninterpretedOptionBuilder_.clear();
                }
                return this;
            }

            @Override
            public Builder clone() {
                return Builder.create().mergeFrom(this.buildPartial());
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return internal_static_google_protobuf_FileOptions_descriptor;
            }

            @Override
            public FileOptions getDefaultInstanceForType() {
                return FileOptions.getDefaultInstance();
            }

            @Override
            public FileOptions build() {
                FileOptions result = this.buildPartial();
                if (!result.isInitialized()) {
                    throw Builder.newUninitializedMessageException(result);
                }
                return result;
            }

            @Override
            public FileOptions buildPartial() {
                FileOptions result = new FileOptions(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ |= 1;
                }
                result.javaPackage_ = this.javaPackage_;
                if ((from_bitField0_ & 2) == 2) {
                    to_bitField0_ |= 2;
                }
                result.javaOuterClassname_ = this.javaOuterClassname_;
                if ((from_bitField0_ & 4) == 4) {
                    to_bitField0_ |= 4;
                }
                result.javaMultipleFiles_ = this.javaMultipleFiles_;
                if ((from_bitField0_ & 8) == 8) {
                    to_bitField0_ |= 8;
                }
                result.javaGenerateEqualsAndHash_ = this.javaGenerateEqualsAndHash_;
                if ((from_bitField0_ & 0x10) == 16) {
                    to_bitField0_ |= 0x10;
                }
                result.javaStringCheckUtf8_ = this.javaStringCheckUtf8_;
                if ((from_bitField0_ & 0x20) == 32) {
                    to_bitField0_ |= 0x20;
                }
                result.optimizeFor_ = this.optimizeFor_;
                if ((from_bitField0_ & 0x40) == 64) {
                    to_bitField0_ |= 0x40;
                }
                result.goPackage_ = this.goPackage_;
                if ((from_bitField0_ & 0x80) == 128) {
                    to_bitField0_ |= 0x80;
                }
                result.ccGenericServices_ = this.ccGenericServices_;
                if ((from_bitField0_ & 0x100) == 256) {
                    to_bitField0_ |= 0x100;
                }
                result.javaGenericServices_ = this.javaGenericServices_;
                if ((from_bitField0_ & 0x200) == 512) {
                    to_bitField0_ |= 0x200;
                }
                result.pyGenericServices_ = this.pyGenericServices_;
                if ((from_bitField0_ & 0x400) == 1024) {
                    to_bitField0_ |= 0x400;
                }
                result.deprecated_ = this.deprecated_;
                if (this.uninterpretedOptionBuilder_ == null) {
                    if ((this.bitField0_ & 0x800) == 2048) {
                        this.uninterpretedOption_ = Collections.unmodifiableList(this.uninterpretedOption_);
                        this.bitField0_ &= 0xFFFFF7FF;
                    }
                    result.uninterpretedOption_ = this.uninterpretedOption_;
                } else {
                    result.uninterpretedOption_ = this.uninterpretedOptionBuilder_.build();
                }
                result.bitField0_ = to_bitField0_;
                this.onBuilt();
                return result;
            }

            @Override
            public Builder mergeFrom(Message other) {
                if (other instanceof FileOptions) {
                    return this.mergeFrom((FileOptions)other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(FileOptions other) {
                if (other == FileOptions.getDefaultInstance()) {
                    return this;
                }
                if (other.hasJavaPackage()) {
                    this.bitField0_ |= 1;
                    this.javaPackage_ = other.javaPackage_;
                    this.onChanged();
                }
                if (other.hasJavaOuterClassname()) {
                    this.bitField0_ |= 2;
                    this.javaOuterClassname_ = other.javaOuterClassname_;
                    this.onChanged();
                }
                if (other.hasJavaMultipleFiles()) {
                    this.setJavaMultipleFiles(other.getJavaMultipleFiles());
                }
                if (other.hasJavaGenerateEqualsAndHash()) {
                    this.setJavaGenerateEqualsAndHash(other.getJavaGenerateEqualsAndHash());
                }
                if (other.hasJavaStringCheckUtf8()) {
                    this.setJavaStringCheckUtf8(other.getJavaStringCheckUtf8());
                }
                if (other.hasOptimizeFor()) {
                    this.setOptimizeFor(other.getOptimizeFor());
                }
                if (other.hasGoPackage()) {
                    this.bitField0_ |= 0x40;
                    this.goPackage_ = other.goPackage_;
                    this.onChanged();
                }
                if (other.hasCcGenericServices()) {
                    this.setCcGenericServices(other.getCcGenericServices());
                }
                if (other.hasJavaGenericServices()) {
                    this.setJavaGenericServices(other.getJavaGenericServices());
                }
                if (other.hasPyGenericServices()) {
                    this.setPyGenericServices(other.getPyGenericServices());
                }
                if (other.hasDeprecated()) {
                    this.setDeprecated(other.getDeprecated());
                }
                if (this.uninterpretedOptionBuilder_ == null) {
                    if (!other.uninterpretedOption_.isEmpty()) {
                        if (this.uninterpretedOption_.isEmpty()) {
                            this.uninterpretedOption_ = other.uninterpretedOption_;
                            this.bitField0_ &= 0xFFFFF7FF;
                        } else {
                            this.ensureUninterpretedOptionIsMutable();
                            this.uninterpretedOption_.addAll(other.uninterpretedOption_);
                        }
                        this.onChanged();
                    }
                } else if (!other.uninterpretedOption_.isEmpty()) {
                    if (this.uninterpretedOptionBuilder_.isEmpty()) {
                        this.uninterpretedOptionBuilder_.dispose();
                        this.uninterpretedOptionBuilder_ = null;
                        this.uninterpretedOption_ = other.uninterpretedOption_;
                        this.bitField0_ &= 0xFFFFF7FF;
                        this.uninterpretedOptionBuilder_ = GeneratedMessage.alwaysUseFieldBuilders ? this.getUninterpretedOptionFieldBuilder() : null;
                    } else {
                        this.uninterpretedOptionBuilder_.addAllMessages(other.uninterpretedOption_);
                    }
                }
                this.mergeExtensionFields(other);
                this.mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            @Override
            public final boolean isInitialized() {
                for (int i = 0; i < this.getUninterpretedOptionCount(); ++i) {
                    if (this.getUninterpretedOption(i).isInitialized()) continue;
                    return false;
                }
                return this.extensionsAreInitialized();
            }

            /*
             * Enabled force condition propagation
             * Lifted jumps to return sites
             */
            @Override
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                FileOptions parsedMessage = null;
                try {
                    try {
                        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
                    } catch (InvalidProtocolBufferException e) {
                        parsedMessage = (FileOptions)e.getUnfinishedMessage();
                        throw e;
                    }
                    Object var6_4 = null;
                    if (parsedMessage == null) return this;
                    this.mergeFrom(parsedMessage);
                    return this;
                } catch (Throwable throwable) {
                    Object var6_5 = null;
                    if (parsedMessage == null) throw throwable;
                    this.mergeFrom(parsedMessage);
                    throw throwable;
                }
            }

            @Override
            public boolean hasJavaPackage() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override
            public String getJavaPackage() {
                Object ref = this.javaPackage_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString)ref;
                    String s2 = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.javaPackage_ = s2;
                    }
                    return s2;
                }
                return (String)ref;
            }

            @Override
            public ByteString getJavaPackageBytes() {
                Object ref = this.javaPackage_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String)ref);
                    this.javaPackage_ = b;
                    return b;
                }
                return (ByteString)ref;
            }

            public Builder setJavaPackage(String value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 1;
                this.javaPackage_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearJavaPackage() {
                this.bitField0_ &= 0xFFFFFFFE;
                this.javaPackage_ = FileOptions.getDefaultInstance().getJavaPackage();
                this.onChanged();
                return this;
            }

            public Builder setJavaPackageBytes(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 1;
                this.javaPackage_ = value;
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasJavaOuterClassname() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override
            public String getJavaOuterClassname() {
                Object ref = this.javaOuterClassname_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString)ref;
                    String s2 = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.javaOuterClassname_ = s2;
                    }
                    return s2;
                }
                return (String)ref;
            }

            @Override
            public ByteString getJavaOuterClassnameBytes() {
                Object ref = this.javaOuterClassname_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String)ref);
                    this.javaOuterClassname_ = b;
                    return b;
                }
                return (ByteString)ref;
            }

            public Builder setJavaOuterClassname(String value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 2;
                this.javaOuterClassname_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearJavaOuterClassname() {
                this.bitField0_ &= 0xFFFFFFFD;
                this.javaOuterClassname_ = FileOptions.getDefaultInstance().getJavaOuterClassname();
                this.onChanged();
                return this;
            }

            public Builder setJavaOuterClassnameBytes(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 2;
                this.javaOuterClassname_ = value;
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasJavaMultipleFiles() {
                return (this.bitField0_ & 4) == 4;
            }

            @Override
            public boolean getJavaMultipleFiles() {
                return this.javaMultipleFiles_;
            }

            public Builder setJavaMultipleFiles(boolean value) {
                this.bitField0_ |= 4;
                this.javaMultipleFiles_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearJavaMultipleFiles() {
                this.bitField0_ &= 0xFFFFFFFB;
                this.javaMultipleFiles_ = false;
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasJavaGenerateEqualsAndHash() {
                return (this.bitField0_ & 8) == 8;
            }

            @Override
            public boolean getJavaGenerateEqualsAndHash() {
                return this.javaGenerateEqualsAndHash_;
            }

            public Builder setJavaGenerateEqualsAndHash(boolean value) {
                this.bitField0_ |= 8;
                this.javaGenerateEqualsAndHash_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearJavaGenerateEqualsAndHash() {
                this.bitField0_ &= 0xFFFFFFF7;
                this.javaGenerateEqualsAndHash_ = false;
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasJavaStringCheckUtf8() {
                return (this.bitField0_ & 0x10) == 16;
            }

            @Override
            public boolean getJavaStringCheckUtf8() {
                return this.javaStringCheckUtf8_;
            }

            public Builder setJavaStringCheckUtf8(boolean value) {
                this.bitField0_ |= 0x10;
                this.javaStringCheckUtf8_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearJavaStringCheckUtf8() {
                this.bitField0_ &= 0xFFFFFFEF;
                this.javaStringCheckUtf8_ = false;
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasOptimizeFor() {
                return (this.bitField0_ & 0x20) == 32;
            }

            @Override
            public OptimizeMode getOptimizeFor() {
                return this.optimizeFor_;
            }

            public Builder setOptimizeFor(OptimizeMode value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 0x20;
                this.optimizeFor_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearOptimizeFor() {
                this.bitField0_ &= 0xFFFFFFDF;
                this.optimizeFor_ = OptimizeMode.SPEED;
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasGoPackage() {
                return (this.bitField0_ & 0x40) == 64;
            }

            @Override
            public String getGoPackage() {
                Object ref = this.goPackage_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString)ref;
                    String s2 = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.goPackage_ = s2;
                    }
                    return s2;
                }
                return (String)ref;
            }

            @Override
            public ByteString getGoPackageBytes() {
                Object ref = this.goPackage_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String)ref);
                    this.goPackage_ = b;
                    return b;
                }
                return (ByteString)ref;
            }

            public Builder setGoPackage(String value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 0x40;
                this.goPackage_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearGoPackage() {
                this.bitField0_ &= 0xFFFFFFBF;
                this.goPackage_ = FileOptions.getDefaultInstance().getGoPackage();
                this.onChanged();
                return this;
            }

            public Builder setGoPackageBytes(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 0x40;
                this.goPackage_ = value;
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasCcGenericServices() {
                return (this.bitField0_ & 0x80) == 128;
            }

            @Override
            public boolean getCcGenericServices() {
                return this.ccGenericServices_;
            }

            public Builder setCcGenericServices(boolean value) {
                this.bitField0_ |= 0x80;
                this.ccGenericServices_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearCcGenericServices() {
                this.bitField0_ &= 0xFFFFFF7F;
                this.ccGenericServices_ = false;
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasJavaGenericServices() {
                return (this.bitField0_ & 0x100) == 256;
            }

            @Override
            public boolean getJavaGenericServices() {
                return this.javaGenericServices_;
            }

            public Builder setJavaGenericServices(boolean value) {
                this.bitField0_ |= 0x100;
                this.javaGenericServices_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearJavaGenericServices() {
                this.bitField0_ &= 0xFFFFFEFF;
                this.javaGenericServices_ = false;
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasPyGenericServices() {
                return (this.bitField0_ & 0x200) == 512;
            }

            @Override
            public boolean getPyGenericServices() {
                return this.pyGenericServices_;
            }

            public Builder setPyGenericServices(boolean value) {
                this.bitField0_ |= 0x200;
                this.pyGenericServices_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearPyGenericServices() {
                this.bitField0_ &= 0xFFFFFDFF;
                this.pyGenericServices_ = false;
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasDeprecated() {
                return (this.bitField0_ & 0x400) == 1024;
            }

            @Override
            public boolean getDeprecated() {
                return this.deprecated_;
            }

            public Builder setDeprecated(boolean value) {
                this.bitField0_ |= 0x400;
                this.deprecated_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearDeprecated() {
                this.bitField0_ &= 0xFFFFFBFF;
                this.deprecated_ = false;
                this.onChanged();
                return this;
            }

            private void ensureUninterpretedOptionIsMutable() {
                if ((this.bitField0_ & 0x800) != 2048) {
                    this.uninterpretedOption_ = new ArrayList<UninterpretedOption>(this.uninterpretedOption_);
                    this.bitField0_ |= 0x800;
                }
            }

            @Override
            public List<UninterpretedOption> getUninterpretedOptionList() {
                if (this.uninterpretedOptionBuilder_ == null) {
                    return Collections.unmodifiableList(this.uninterpretedOption_);
                }
                return this.uninterpretedOptionBuilder_.getMessageList();
            }

            @Override
            public int getUninterpretedOptionCount() {
                if (this.uninterpretedOptionBuilder_ == null) {
                    return this.uninterpretedOption_.size();
                }
                return this.uninterpretedOptionBuilder_.getCount();
            }

            @Override
            public UninterpretedOption getUninterpretedOption(int index) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    return this.uninterpretedOption_.get(index);
                }
                return this.uninterpretedOptionBuilder_.getMessage(index);
            }

            public Builder setUninterpretedOption(int index, UninterpretedOption value) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureUninterpretedOptionIsMutable();
                    this.uninterpretedOption_.set(index, value);
                    this.onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.setMessage(index, value);
                }
                return this;
            }

            public Builder setUninterpretedOption(int index, UninterpretedOption.Builder builderForValue) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    this.ensureUninterpretedOptionIsMutable();
                    this.uninterpretedOption_.set(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.setMessage(index, builderForValue.build());
                }
                return this;
            }

            public Builder addUninterpretedOption(UninterpretedOption value) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureUninterpretedOptionIsMutable();
                    this.uninterpretedOption_.add(value);
                    this.onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.addMessage(value);
                }
                return this;
            }

            public Builder addUninterpretedOption(int index, UninterpretedOption value) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureUninterpretedOptionIsMutable();
                    this.uninterpretedOption_.add(index, value);
                    this.onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.addMessage(index, value);
                }
                return this;
            }

            public Builder addUninterpretedOption(UninterpretedOption.Builder builderForValue) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    this.ensureUninterpretedOptionIsMutable();
                    this.uninterpretedOption_.add(builderForValue.build());
                    this.onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.addMessage(builderForValue.build());
                }
                return this;
            }

            public Builder addUninterpretedOption(int index, UninterpretedOption.Builder builderForValue) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    this.ensureUninterpretedOptionIsMutable();
                    this.uninterpretedOption_.add(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.addMessage(index, builderForValue.build());
                }
                return this;
            }

            public Builder addAllUninterpretedOption(Iterable<? extends UninterpretedOption> values2) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    this.ensureUninterpretedOptionIsMutable();
                    AbstractMessageLite.Builder.addAll(values2, this.uninterpretedOption_);
                    this.onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.addAllMessages(values2);
                }
                return this;
            }

            public Builder clearUninterpretedOption() {
                if (this.uninterpretedOptionBuilder_ == null) {
                    this.uninterpretedOption_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFF7FF;
                    this.onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.clear();
                }
                return this;
            }

            public Builder removeUninterpretedOption(int index) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    this.ensureUninterpretedOptionIsMutable();
                    this.uninterpretedOption_.remove(index);
                    this.onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.remove(index);
                }
                return this;
            }

            public UninterpretedOption.Builder getUninterpretedOptionBuilder(int index) {
                return this.getUninterpretedOptionFieldBuilder().getBuilder(index);
            }

            @Override
            public UninterpretedOptionOrBuilder getUninterpretedOptionOrBuilder(int index) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    return this.uninterpretedOption_.get(index);
                }
                return this.uninterpretedOptionBuilder_.getMessageOrBuilder(index);
            }

            @Override
            public List<? extends UninterpretedOptionOrBuilder> getUninterpretedOptionOrBuilderList() {
                if (this.uninterpretedOptionBuilder_ != null) {
                    return this.uninterpretedOptionBuilder_.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.uninterpretedOption_);
            }

            public UninterpretedOption.Builder addUninterpretedOptionBuilder() {
                return this.getUninterpretedOptionFieldBuilder().addBuilder(UninterpretedOption.getDefaultInstance());
            }

            public UninterpretedOption.Builder addUninterpretedOptionBuilder(int index) {
                return this.getUninterpretedOptionFieldBuilder().addBuilder(index, UninterpretedOption.getDefaultInstance());
            }

            public List<UninterpretedOption.Builder> getUninterpretedOptionBuilderList() {
                return this.getUninterpretedOptionFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilder<UninterpretedOption, UninterpretedOption.Builder, UninterpretedOptionOrBuilder> getUninterpretedOptionFieldBuilder() {
                if (this.uninterpretedOptionBuilder_ == null) {
                    this.uninterpretedOptionBuilder_ = new RepeatedFieldBuilder(this.uninterpretedOption_, (this.bitField0_ & 0x800) == 2048, this.getParentForChildren(), this.isClean());
                    this.uninterpretedOption_ = null;
                }
                return this.uninterpretedOptionBuilder_;
            }
        }

        /*
         * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
         */
        public static enum OptimizeMode implements ProtocolMessageEnum
        {
            SPEED(0, 1),
            CODE_SIZE(1, 2),
            LITE_RUNTIME(2, 3);

            public static final int SPEED_VALUE = 1;
            public static final int CODE_SIZE_VALUE = 2;
            public static final int LITE_RUNTIME_VALUE = 3;
            private static Internal.EnumLiteMap<OptimizeMode> internalValueMap;
            private static final OptimizeMode[] VALUES;
            private final int index;
            private final int value;

            @Override
            public final int getNumber() {
                return this.value;
            }

            public static OptimizeMode valueOf(int value) {
                switch (value) {
                    case 1: {
                        return SPEED;
                    }
                    case 2: {
                        return CODE_SIZE;
                    }
                    case 3: {
                        return LITE_RUNTIME;
                    }
                }
                return null;
            }

            public static Internal.EnumLiteMap<OptimizeMode> internalGetValueMap() {
                return internalValueMap;
            }

            @Override
            public final Descriptors.EnumValueDescriptor getValueDescriptor() {
                return OptimizeMode.getDescriptor().getValues().get(this.index);
            }

            @Override
            public final Descriptors.EnumDescriptor getDescriptorForType() {
                return OptimizeMode.getDescriptor();
            }

            public static final Descriptors.EnumDescriptor getDescriptor() {
                return FileOptions.getDescriptor().getEnumTypes().get(0);
            }

            public static OptimizeMode valueOf(Descriptors.EnumValueDescriptor desc) {
                if (desc.getType() != OptimizeMode.getDescriptor()) {
                    throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
                }
                return VALUES[desc.getIndex()];
            }

            private OptimizeMode(int index, int value) {
                this.index = index;
                this.value = value;
            }

            static {
                internalValueMap = new Internal.EnumLiteMap<OptimizeMode>(){

                    @Override
                    public OptimizeMode findValueByNumber(int number) {
                        return OptimizeMode.valueOf(number);
                    }
                };
                VALUES = OptimizeMode.values();
            }
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static interface FileOptionsOrBuilder
    extends GeneratedMessage.ExtendableMessageOrBuilder<FileOptions> {
        public boolean hasJavaPackage();

        public String getJavaPackage();

        public ByteString getJavaPackageBytes();

        public boolean hasJavaOuterClassname();

        public String getJavaOuterClassname();

        public ByteString getJavaOuterClassnameBytes();

        public boolean hasJavaMultipleFiles();

        public boolean getJavaMultipleFiles();

        public boolean hasJavaGenerateEqualsAndHash();

        public boolean getJavaGenerateEqualsAndHash();

        public boolean hasJavaStringCheckUtf8();

        public boolean getJavaStringCheckUtf8();

        public boolean hasOptimizeFor();

        public FileOptions.OptimizeMode getOptimizeFor();

        public boolean hasGoPackage();

        public String getGoPackage();

        public ByteString getGoPackageBytes();

        public boolean hasCcGenericServices();

        public boolean getCcGenericServices();

        public boolean hasJavaGenericServices();

        public boolean getJavaGenericServices();

        public boolean hasPyGenericServices();

        public boolean getPyGenericServices();

        public boolean hasDeprecated();

        public boolean getDeprecated();

        public List<UninterpretedOption> getUninterpretedOptionList();

        public UninterpretedOption getUninterpretedOption(int var1);

        public int getUninterpretedOptionCount();

        public List<? extends UninterpretedOptionOrBuilder> getUninterpretedOptionOrBuilderList();

        public UninterpretedOptionOrBuilder getUninterpretedOptionOrBuilder(int var1);
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static final class MethodDescriptorProto
    extends GeneratedMessage
    implements MethodDescriptorProtoOrBuilder {
        private static final MethodDescriptorProto defaultInstance;
        private final UnknownFieldSet unknownFields;
        public static Parser<MethodDescriptorProto> PARSER;
        private int bitField0_;
        public static final int NAME_FIELD_NUMBER = 1;
        private Object name_;
        public static final int INPUT_TYPE_FIELD_NUMBER = 2;
        private Object inputType_;
        public static final int OUTPUT_TYPE_FIELD_NUMBER = 3;
        private Object outputType_;
        public static final int OPTIONS_FIELD_NUMBER = 4;
        private MethodOptions options_;
        private byte memoizedIsInitialized = (byte)-1;
        private int memoizedSerializedSize = -1;
        private static final long serialVersionUID = 0L;

        private MethodDescriptorProto(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.unknownFields = builder.getUnknownFields();
        }

        private MethodDescriptorProto(boolean noInit) {
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static MethodDescriptorProto getDefaultInstance() {
            return defaultInstance;
        }

        @Override
        public MethodDescriptorProto getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private MethodDescriptorProto(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this.initFields();
            boolean mutable_bitField0_ = false;
            UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
            try {
                try {
                    boolean done = false;
                    block12: while (!done) {
                        int tag = input.readTag();
                        switch (tag) {
                            case 0: {
                                done = true;
                                continue block12;
                            }
                            default: {
                                if (this.parseUnknownField(input, unknownFields, extensionRegistry, tag)) continue block12;
                                done = true;
                                continue block12;
                            }
                            case 10: {
                                ByteString bs = input.readBytes();
                                this.bitField0_ |= 1;
                                this.name_ = bs;
                                continue block12;
                            }
                            case 18: {
                                ByteString bs = input.readBytes();
                                this.bitField0_ |= 2;
                                this.inputType_ = bs;
                                continue block12;
                            }
                            case 26: {
                                ByteString bs = input.readBytes();
                                this.bitField0_ |= 4;
                                this.outputType_ = bs;
                                continue block12;
                            }
                            case 34: 
                        }
                        MethodOptions.Builder subBuilder = null;
                        if ((this.bitField0_ & 8) == 8) {
                            subBuilder = this.options_.toBuilder();
                        }
                        this.options_ = input.readMessage(MethodOptions.PARSER, extensionRegistry);
                        if (subBuilder != null) {
                            subBuilder.mergeFrom(this.options_);
                            this.options_ = subBuilder.buildPartial();
                        }
                        this.bitField0_ |= 8;
                    }
                    Object var9_10 = null;
                    this.unknownFields = unknownFields.build();
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e) {
                    throw new InvalidProtocolBufferException(e.getMessage()).setUnfinishedMessage(this);
                }
            } catch (Throwable throwable) {
                Object var9_11 = null;
                this.unknownFields = unknownFields.build();
                this.makeExtensionsImmutable();
                throw throwable;
            }
            this.makeExtensionsImmutable();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return internal_static_google_protobuf_MethodDescriptorProto_descriptor;
        }

        @Override
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return internal_static_google_protobuf_MethodDescriptorProto_fieldAccessorTable.ensureFieldAccessorsInitialized(MethodDescriptorProto.class, Builder.class);
        }

        public Parser<MethodDescriptorProto> getParserForType() {
            return PARSER;
        }

        @Override
        public boolean hasName() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override
        public String getName() {
            Object ref = this.name_;
            if (ref instanceof String) {
                return (String)ref;
            }
            ByteString bs = (ByteString)ref;
            String s2 = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.name_ = s2;
            }
            return s2;
        }

        @Override
        public ByteString getNameBytes() {
            Object ref = this.name_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.name_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        @Override
        public boolean hasInputType() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override
        public String getInputType() {
            Object ref = this.inputType_;
            if (ref instanceof String) {
                return (String)ref;
            }
            ByteString bs = (ByteString)ref;
            String s2 = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.inputType_ = s2;
            }
            return s2;
        }

        @Override
        public ByteString getInputTypeBytes() {
            Object ref = this.inputType_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.inputType_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        @Override
        public boolean hasOutputType() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override
        public String getOutputType() {
            Object ref = this.outputType_;
            if (ref instanceof String) {
                return (String)ref;
            }
            ByteString bs = (ByteString)ref;
            String s2 = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.outputType_ = s2;
            }
            return s2;
        }

        @Override
        public ByteString getOutputTypeBytes() {
            Object ref = this.outputType_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.outputType_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        @Override
        public boolean hasOptions() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override
        public MethodOptions getOptions() {
            return this.options_;
        }

        @Override
        public MethodOptionsOrBuilder getOptionsOrBuilder() {
            return this.options_;
        }

        private void initFields() {
            this.name_ = "";
            this.inputType_ = "";
            this.outputType_ = "";
            this.options_ = MethodOptions.getDefaultInstance();
        }

        @Override
        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            if (this.hasOptions() && !this.getOptions().isInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        @Override
        public void writeTo(CodedOutputStream output) throws IOException {
            this.getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                output.writeBytes(1, this.getNameBytes());
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeBytes(2, this.getInputTypeBytes());
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeBytes(3, this.getOutputTypeBytes());
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeMessage(4, this.options_);
            }
            this.getUnknownFields().writeTo(output);
        }

        @Override
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            size = 0;
            if ((this.bitField0_ & 1) == 1) {
                size += CodedOutputStream.computeBytesSize(1, this.getNameBytes());
            }
            if ((this.bitField0_ & 2) == 2) {
                size += CodedOutputStream.computeBytesSize(2, this.getInputTypeBytes());
            }
            if ((this.bitField0_ & 4) == 4) {
                size += CodedOutputStream.computeBytesSize(3, this.getOutputTypeBytes());
            }
            if ((this.bitField0_ & 8) == 8) {
                size += CodedOutputStream.computeMessageSize(4, this.options_);
            }
            this.memoizedSerializedSize = size += this.getUnknownFields().getSerializedSize();
            return size;
        }

        @Override
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static MethodDescriptorProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static MethodDescriptorProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static MethodDescriptorProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static MethodDescriptorProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static MethodDescriptorProto parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static MethodDescriptorProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static MethodDescriptorProto parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static MethodDescriptorProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static MethodDescriptorProto parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static MethodDescriptorProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override
        public Builder newBuilderForType() {
            return MethodDescriptorProto.newBuilder();
        }

        public static Builder newBuilder(MethodDescriptorProto prototype) {
            return MethodDescriptorProto.newBuilder().mergeFrom(prototype);
        }

        @Override
        public Builder toBuilder() {
            return MethodDescriptorProto.newBuilder(this);
        }

        @Override
        protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        static {
            PARSER = new AbstractParser<MethodDescriptorProto>(){

                @Override
                public MethodDescriptorProto parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                    return new MethodDescriptorProto(input, extensionRegistry);
                }
            };
            defaultInstance = new MethodDescriptorProto(true);
            defaultInstance.initFields();
        }

        /*
         * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
         */
        public static final class Builder
        extends GeneratedMessage.Builder<Builder>
        implements MethodDescriptorProtoOrBuilder {
            private int bitField0_;
            private Object name_ = "";
            private Object inputType_ = "";
            private Object outputType_ = "";
            private MethodOptions options_ = MethodOptions.getDefaultInstance();
            private SingleFieldBuilder<MethodOptions, MethodOptions.Builder, MethodOptionsOrBuilder> optionsBuilder_;

            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_google_protobuf_MethodDescriptorProto_descriptor;
            }

            @Override
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_google_protobuf_MethodDescriptorProto_fieldAccessorTable.ensureFieldAccessorsInitialized(MethodDescriptorProto.class, Builder.class);
            }

            private Builder() {
                this.maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessage.BuilderParent parent) {
                super(parent);
                this.maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                if (GeneratedMessage.alwaysUseFieldBuilders) {
                    this.getOptionsFieldBuilder();
                }
            }

            private static Builder create() {
                return new Builder();
            }

            @Override
            public Builder clear() {
                super.clear();
                this.name_ = "";
                this.bitField0_ &= 0xFFFFFFFE;
                this.inputType_ = "";
                this.bitField0_ &= 0xFFFFFFFD;
                this.outputType_ = "";
                this.bitField0_ &= 0xFFFFFFFB;
                if (this.optionsBuilder_ == null) {
                    this.options_ = MethodOptions.getDefaultInstance();
                } else {
                    this.optionsBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFFF7;
                return this;
            }

            @Override
            public Builder clone() {
                return Builder.create().mergeFrom(this.buildPartial());
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return internal_static_google_protobuf_MethodDescriptorProto_descriptor;
            }

            @Override
            public MethodDescriptorProto getDefaultInstanceForType() {
                return MethodDescriptorProto.getDefaultInstance();
            }

            @Override
            public MethodDescriptorProto build() {
                MethodDescriptorProto result = this.buildPartial();
                if (!result.isInitialized()) {
                    throw Builder.newUninitializedMessageException(result);
                }
                return result;
            }

            @Override
            public MethodDescriptorProto buildPartial() {
                MethodDescriptorProto result = new MethodDescriptorProto(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ |= 1;
                }
                result.name_ = this.name_;
                if ((from_bitField0_ & 2) == 2) {
                    to_bitField0_ |= 2;
                }
                result.inputType_ = this.inputType_;
                if ((from_bitField0_ & 4) == 4) {
                    to_bitField0_ |= 4;
                }
                result.outputType_ = this.outputType_;
                if ((from_bitField0_ & 8) == 8) {
                    to_bitField0_ |= 8;
                }
                if (this.optionsBuilder_ == null) {
                    result.options_ = this.options_;
                } else {
                    result.options_ = this.optionsBuilder_.build();
                }
                result.bitField0_ = to_bitField0_;
                this.onBuilt();
                return result;
            }

            @Override
            public Builder mergeFrom(Message other) {
                if (other instanceof MethodDescriptorProto) {
                    return this.mergeFrom((MethodDescriptorProto)other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(MethodDescriptorProto other) {
                if (other == MethodDescriptorProto.getDefaultInstance()) {
                    return this;
                }
                if (other.hasName()) {
                    this.bitField0_ |= 1;
                    this.name_ = other.name_;
                    this.onChanged();
                }
                if (other.hasInputType()) {
                    this.bitField0_ |= 2;
                    this.inputType_ = other.inputType_;
                    this.onChanged();
                }
                if (other.hasOutputType()) {
                    this.bitField0_ |= 4;
                    this.outputType_ = other.outputType_;
                    this.onChanged();
                }
                if (other.hasOptions()) {
                    this.mergeOptions(other.getOptions());
                }
                this.mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            @Override
            public final boolean isInitialized() {
                return !this.hasOptions() || this.getOptions().isInitialized();
            }

            /*
             * Enabled force condition propagation
             * Lifted jumps to return sites
             */
            @Override
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                MethodDescriptorProto parsedMessage = null;
                try {
                    try {
                        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
                    } catch (InvalidProtocolBufferException e) {
                        parsedMessage = (MethodDescriptorProto)e.getUnfinishedMessage();
                        throw e;
                    }
                    Object var6_4 = null;
                    if (parsedMessage == null) return this;
                    this.mergeFrom(parsedMessage);
                    return this;
                } catch (Throwable throwable) {
                    Object var6_5 = null;
                    if (parsedMessage == null) throw throwable;
                    this.mergeFrom(parsedMessage);
                    throw throwable;
                }
            }

            @Override
            public boolean hasName() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override
            public String getName() {
                Object ref = this.name_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString)ref;
                    String s2 = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.name_ = s2;
                    }
                    return s2;
                }
                return (String)ref;
            }

            @Override
            public ByteString getNameBytes() {
                Object ref = this.name_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String)ref);
                    this.name_ = b;
                    return b;
                }
                return (ByteString)ref;
            }

            public Builder setName(String value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 1;
                this.name_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearName() {
                this.bitField0_ &= 0xFFFFFFFE;
                this.name_ = MethodDescriptorProto.getDefaultInstance().getName();
                this.onChanged();
                return this;
            }

            public Builder setNameBytes(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 1;
                this.name_ = value;
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasInputType() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override
            public String getInputType() {
                Object ref = this.inputType_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString)ref;
                    String s2 = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.inputType_ = s2;
                    }
                    return s2;
                }
                return (String)ref;
            }

            @Override
            public ByteString getInputTypeBytes() {
                Object ref = this.inputType_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String)ref);
                    this.inputType_ = b;
                    return b;
                }
                return (ByteString)ref;
            }

            public Builder setInputType(String value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 2;
                this.inputType_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearInputType() {
                this.bitField0_ &= 0xFFFFFFFD;
                this.inputType_ = MethodDescriptorProto.getDefaultInstance().getInputType();
                this.onChanged();
                return this;
            }

            public Builder setInputTypeBytes(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 2;
                this.inputType_ = value;
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasOutputType() {
                return (this.bitField0_ & 4) == 4;
            }

            @Override
            public String getOutputType() {
                Object ref = this.outputType_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString)ref;
                    String s2 = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.outputType_ = s2;
                    }
                    return s2;
                }
                return (String)ref;
            }

            @Override
            public ByteString getOutputTypeBytes() {
                Object ref = this.outputType_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String)ref);
                    this.outputType_ = b;
                    return b;
                }
                return (ByteString)ref;
            }

            public Builder setOutputType(String value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 4;
                this.outputType_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearOutputType() {
                this.bitField0_ &= 0xFFFFFFFB;
                this.outputType_ = MethodDescriptorProto.getDefaultInstance().getOutputType();
                this.onChanged();
                return this;
            }

            public Builder setOutputTypeBytes(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 4;
                this.outputType_ = value;
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasOptions() {
                return (this.bitField0_ & 8) == 8;
            }

            @Override
            public MethodOptions getOptions() {
                if (this.optionsBuilder_ == null) {
                    return this.options_;
                }
                return this.optionsBuilder_.getMessage();
            }

            public Builder setOptions(MethodOptions value) {
                if (this.optionsBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.options_ = value;
                    this.onChanged();
                } else {
                    this.optionsBuilder_.setMessage(value);
                }
                this.bitField0_ |= 8;
                return this;
            }

            public Builder setOptions(MethodOptions.Builder builderForValue) {
                if (this.optionsBuilder_ == null) {
                    this.options_ = builderForValue.build();
                    this.onChanged();
                } else {
                    this.optionsBuilder_.setMessage(builderForValue.build());
                }
                this.bitField0_ |= 8;
                return this;
            }

            public Builder mergeOptions(MethodOptions value) {
                if (this.optionsBuilder_ == null) {
                    this.options_ = (this.bitField0_ & 8) == 8 && this.options_ != MethodOptions.getDefaultInstance() ? MethodOptions.newBuilder(this.options_).mergeFrom(value).buildPartial() : value;
                    this.onChanged();
                } else {
                    this.optionsBuilder_.mergeFrom(value);
                }
                this.bitField0_ |= 8;
                return this;
            }

            public Builder clearOptions() {
                if (this.optionsBuilder_ == null) {
                    this.options_ = MethodOptions.getDefaultInstance();
                    this.onChanged();
                } else {
                    this.optionsBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFFF7;
                return this;
            }

            public MethodOptions.Builder getOptionsBuilder() {
                this.bitField0_ |= 8;
                this.onChanged();
                return this.getOptionsFieldBuilder().getBuilder();
            }

            @Override
            public MethodOptionsOrBuilder getOptionsOrBuilder() {
                if (this.optionsBuilder_ != null) {
                    return this.optionsBuilder_.getMessageOrBuilder();
                }
                return this.options_;
            }

            private SingleFieldBuilder<MethodOptions, MethodOptions.Builder, MethodOptionsOrBuilder> getOptionsFieldBuilder() {
                if (this.optionsBuilder_ == null) {
                    this.optionsBuilder_ = new SingleFieldBuilder(this.getOptions(), this.getParentForChildren(), this.isClean());
                    this.options_ = null;
                }
                return this.optionsBuilder_;
            }
        }
    }

    public static interface MethodDescriptorProtoOrBuilder
    extends MessageOrBuilder {
        public boolean hasName();

        public String getName();

        public ByteString getNameBytes();

        public boolean hasInputType();

        public String getInputType();

        public ByteString getInputTypeBytes();

        public boolean hasOutputType();

        public String getOutputType();

        public ByteString getOutputTypeBytes();

        public boolean hasOptions();

        public MethodOptions getOptions();

        public MethodOptionsOrBuilder getOptionsOrBuilder();
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static final class ServiceDescriptorProto
    extends GeneratedMessage
    implements ServiceDescriptorProtoOrBuilder {
        private static final ServiceDescriptorProto defaultInstance;
        private final UnknownFieldSet unknownFields;
        public static Parser<ServiceDescriptorProto> PARSER;
        private int bitField0_;
        public static final int NAME_FIELD_NUMBER = 1;
        private Object name_;
        public static final int METHOD_FIELD_NUMBER = 2;
        private List<MethodDescriptorProto> method_;
        public static final int OPTIONS_FIELD_NUMBER = 3;
        private ServiceOptions options_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private static final long serialVersionUID = 0L;

        private ServiceDescriptorProto(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte)-1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private ServiceDescriptorProto(boolean noInit) {
            this.memoizedIsInitialized = (byte)-1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static ServiceDescriptorProto getDefaultInstance() {
            return defaultInstance;
        }

        @Override
        public ServiceDescriptorProto getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        private ServiceDescriptorProto(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            UnknownFieldSet.Builder unknownFields;
            block17: {
                this.memoizedIsInitialized = (byte)-1;
                this.memoizedSerializedSize = -1;
                this.initFields();
                int mutable_bitField0_ = 0;
                unknownFields = UnknownFieldSet.newBuilder();
                try {
                    try {
                        boolean done = false;
                        block11: while (!done) {
                            int tag = input.readTag();
                            switch (tag) {
                                case 0: {
                                    done = true;
                                    continue block11;
                                }
                                default: {
                                    if (this.parseUnknownField(input, unknownFields, extensionRegistry, tag)) continue block11;
                                    done = true;
                                    continue block11;
                                }
                                case 10: {
                                    ByteString bs = input.readBytes();
                                    this.bitField0_ |= 1;
                                    this.name_ = bs;
                                    continue block11;
                                }
                                case 18: {
                                    if ((mutable_bitField0_ & 2) != 2) {
                                        this.method_ = new ArrayList<MethodDescriptorProto>();
                                        mutable_bitField0_ |= 2;
                                    }
                                    this.method_.add(input.readMessage(MethodDescriptorProto.PARSER, extensionRegistry));
                                    continue block11;
                                }
                                case 26: 
                            }
                            ServiceOptions.Builder subBuilder = null;
                            if ((this.bitField0_ & 2) == 2) {
                                subBuilder = this.options_.toBuilder();
                            }
                            this.options_ = input.readMessage(ServiceOptions.PARSER, extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom(this.options_);
                                this.options_ = subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 2;
                        }
                        Object var9_10 = null;
                        if ((mutable_bitField0_ & 2) != 2) break block17;
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e) {
                        throw new InvalidProtocolBufferException(e.getMessage()).setUnfinishedMessage(this);
                    }
                } catch (Throwable throwable) {
                    Object var9_11 = null;
                    if ((mutable_bitField0_ & 2) == 2) {
                        this.method_ = Collections.unmodifiableList(this.method_);
                    }
                    this.unknownFields = unknownFields.build();
                    this.makeExtensionsImmutable();
                    throw throwable;
                }
                this.method_ = Collections.unmodifiableList(this.method_);
            }
            this.unknownFields = unknownFields.build();
            this.makeExtensionsImmutable();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return internal_static_google_protobuf_ServiceDescriptorProto_descriptor;
        }

        @Override
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return internal_static_google_protobuf_ServiceDescriptorProto_fieldAccessorTable.ensureFieldAccessorsInitialized(ServiceDescriptorProto.class, Builder.class);
        }

        public Parser<ServiceDescriptorProto> getParserForType() {
            return PARSER;
        }

        @Override
        public boolean hasName() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override
        public String getName() {
            Object ref = this.name_;
            if (ref instanceof String) {
                return (String)ref;
            }
            ByteString bs = (ByteString)ref;
            String s2 = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.name_ = s2;
            }
            return s2;
        }

        @Override
        public ByteString getNameBytes() {
            Object ref = this.name_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.name_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        @Override
        public List<MethodDescriptorProto> getMethodList() {
            return this.method_;
        }

        @Override
        public List<? extends MethodDescriptorProtoOrBuilder> getMethodOrBuilderList() {
            return this.method_;
        }

        @Override
        public int getMethodCount() {
            return this.method_.size();
        }

        @Override
        public MethodDescriptorProto getMethod(int index) {
            return this.method_.get(index);
        }

        @Override
        public MethodDescriptorProtoOrBuilder getMethodOrBuilder(int index) {
            return this.method_.get(index);
        }

        @Override
        public boolean hasOptions() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override
        public ServiceOptions getOptions() {
            return this.options_;
        }

        @Override
        public ServiceOptionsOrBuilder getOptionsOrBuilder() {
            return this.options_;
        }

        private void initFields() {
            this.name_ = "";
            this.method_ = Collections.emptyList();
            this.options_ = ServiceOptions.getDefaultInstance();
        }

        @Override
        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            for (int i = 0; i < this.getMethodCount(); ++i) {
                if (this.getMethod(i).isInitialized()) continue;
                this.memoizedIsInitialized = 0;
                return false;
            }
            if (this.hasOptions() && !this.getOptions().isInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        @Override
        public void writeTo(CodedOutputStream output) throws IOException {
            this.getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                output.writeBytes(1, this.getNameBytes());
            }
            for (int i = 0; i < this.method_.size(); ++i) {
                output.writeMessage(2, this.method_.get(i));
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeMessage(3, this.options_);
            }
            this.getUnknownFields().writeTo(output);
        }

        @Override
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            size = 0;
            if ((this.bitField0_ & 1) == 1) {
                size += CodedOutputStream.computeBytesSize(1, this.getNameBytes());
            }
            for (int i = 0; i < this.method_.size(); ++i) {
                size += CodedOutputStream.computeMessageSize(2, this.method_.get(i));
            }
            if ((this.bitField0_ & 2) == 2) {
                size += CodedOutputStream.computeMessageSize(3, this.options_);
            }
            this.memoizedSerializedSize = size += this.getUnknownFields().getSerializedSize();
            return size;
        }

        @Override
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static ServiceDescriptorProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static ServiceDescriptorProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static ServiceDescriptorProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static ServiceDescriptorProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static ServiceDescriptorProto parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static ServiceDescriptorProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static ServiceDescriptorProto parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static ServiceDescriptorProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static ServiceDescriptorProto parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static ServiceDescriptorProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override
        public Builder newBuilderForType() {
            return ServiceDescriptorProto.newBuilder();
        }

        public static Builder newBuilder(ServiceDescriptorProto prototype) {
            return ServiceDescriptorProto.newBuilder().mergeFrom(prototype);
        }

        @Override
        public Builder toBuilder() {
            return ServiceDescriptorProto.newBuilder(this);
        }

        @Override
        protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        static {
            PARSER = new AbstractParser<ServiceDescriptorProto>(){

                @Override
                public ServiceDescriptorProto parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                    return new ServiceDescriptorProto(input, extensionRegistry);
                }
            };
            defaultInstance = new ServiceDescriptorProto(true);
            defaultInstance.initFields();
        }

        /*
         * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
         */
        public static final class Builder
        extends GeneratedMessage.Builder<Builder>
        implements ServiceDescriptorProtoOrBuilder {
            private int bitField0_;
            private Object name_ = "";
            private List<MethodDescriptorProto> method_ = Collections.emptyList();
            private RepeatedFieldBuilder<MethodDescriptorProto, MethodDescriptorProto.Builder, MethodDescriptorProtoOrBuilder> methodBuilder_;
            private ServiceOptions options_ = ServiceOptions.getDefaultInstance();
            private SingleFieldBuilder<ServiceOptions, ServiceOptions.Builder, ServiceOptionsOrBuilder> optionsBuilder_;

            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_google_protobuf_ServiceDescriptorProto_descriptor;
            }

            @Override
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_google_protobuf_ServiceDescriptorProto_fieldAccessorTable.ensureFieldAccessorsInitialized(ServiceDescriptorProto.class, Builder.class);
            }

            private Builder() {
                this.maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessage.BuilderParent parent) {
                super(parent);
                this.maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                if (GeneratedMessage.alwaysUseFieldBuilders) {
                    this.getMethodFieldBuilder();
                    this.getOptionsFieldBuilder();
                }
            }

            private static Builder create() {
                return new Builder();
            }

            @Override
            public Builder clear() {
                super.clear();
                this.name_ = "";
                this.bitField0_ &= 0xFFFFFFFE;
                if (this.methodBuilder_ == null) {
                    this.method_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFFFFD;
                } else {
                    this.methodBuilder_.clear();
                }
                if (this.optionsBuilder_ == null) {
                    this.options_ = ServiceOptions.getDefaultInstance();
                } else {
                    this.optionsBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFFFB;
                return this;
            }

            @Override
            public Builder clone() {
                return Builder.create().mergeFrom(this.buildPartial());
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return internal_static_google_protobuf_ServiceDescriptorProto_descriptor;
            }

            @Override
            public ServiceDescriptorProto getDefaultInstanceForType() {
                return ServiceDescriptorProto.getDefaultInstance();
            }

            @Override
            public ServiceDescriptorProto build() {
                ServiceDescriptorProto result = this.buildPartial();
                if (!result.isInitialized()) {
                    throw Builder.newUninitializedMessageException(result);
                }
                return result;
            }

            @Override
            public ServiceDescriptorProto buildPartial() {
                ServiceDescriptorProto result = new ServiceDescriptorProto(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ |= 1;
                }
                result.name_ = this.name_;
                if (this.methodBuilder_ == null) {
                    if ((this.bitField0_ & 2) == 2) {
                        this.method_ = Collections.unmodifiableList(this.method_);
                        this.bitField0_ &= 0xFFFFFFFD;
                    }
                    result.method_ = this.method_;
                } else {
                    result.method_ = this.methodBuilder_.build();
                }
                if ((from_bitField0_ & 4) == 4) {
                    to_bitField0_ |= 2;
                }
                if (this.optionsBuilder_ == null) {
                    result.options_ = this.options_;
                } else {
                    result.options_ = this.optionsBuilder_.build();
                }
                result.bitField0_ = to_bitField0_;
                this.onBuilt();
                return result;
            }

            @Override
            public Builder mergeFrom(Message other) {
                if (other instanceof ServiceDescriptorProto) {
                    return this.mergeFrom((ServiceDescriptorProto)other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(ServiceDescriptorProto other) {
                if (other == ServiceDescriptorProto.getDefaultInstance()) {
                    return this;
                }
                if (other.hasName()) {
                    this.bitField0_ |= 1;
                    this.name_ = other.name_;
                    this.onChanged();
                }
                if (this.methodBuilder_ == null) {
                    if (!other.method_.isEmpty()) {
                        if (this.method_.isEmpty()) {
                            this.method_ = other.method_;
                            this.bitField0_ &= 0xFFFFFFFD;
                        } else {
                            this.ensureMethodIsMutable();
                            this.method_.addAll(other.method_);
                        }
                        this.onChanged();
                    }
                } else if (!other.method_.isEmpty()) {
                    if (this.methodBuilder_.isEmpty()) {
                        this.methodBuilder_.dispose();
                        this.methodBuilder_ = null;
                        this.method_ = other.method_;
                        this.bitField0_ &= 0xFFFFFFFD;
                        this.methodBuilder_ = GeneratedMessage.alwaysUseFieldBuilders ? this.getMethodFieldBuilder() : null;
                    } else {
                        this.methodBuilder_.addAllMessages(other.method_);
                    }
                }
                if (other.hasOptions()) {
                    this.mergeOptions(other.getOptions());
                }
                this.mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            @Override
            public final boolean isInitialized() {
                for (int i = 0; i < this.getMethodCount(); ++i) {
                    if (this.getMethod(i).isInitialized()) continue;
                    return false;
                }
                return !this.hasOptions() || this.getOptions().isInitialized();
            }

            /*
             * Enabled force condition propagation
             * Lifted jumps to return sites
             */
            @Override
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                ServiceDescriptorProto parsedMessage = null;
                try {
                    try {
                        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
                    } catch (InvalidProtocolBufferException e) {
                        parsedMessage = (ServiceDescriptorProto)e.getUnfinishedMessage();
                        throw e;
                    }
                    Object var6_4 = null;
                    if (parsedMessage == null) return this;
                    this.mergeFrom(parsedMessage);
                    return this;
                } catch (Throwable throwable) {
                    Object var6_5 = null;
                    if (parsedMessage == null) throw throwable;
                    this.mergeFrom(parsedMessage);
                    throw throwable;
                }
            }

            @Override
            public boolean hasName() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override
            public String getName() {
                Object ref = this.name_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString)ref;
                    String s2 = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.name_ = s2;
                    }
                    return s2;
                }
                return (String)ref;
            }

            @Override
            public ByteString getNameBytes() {
                Object ref = this.name_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String)ref);
                    this.name_ = b;
                    return b;
                }
                return (ByteString)ref;
            }

            public Builder setName(String value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 1;
                this.name_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearName() {
                this.bitField0_ &= 0xFFFFFFFE;
                this.name_ = ServiceDescriptorProto.getDefaultInstance().getName();
                this.onChanged();
                return this;
            }

            public Builder setNameBytes(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 1;
                this.name_ = value;
                this.onChanged();
                return this;
            }

            private void ensureMethodIsMutable() {
                if ((this.bitField0_ & 2) != 2) {
                    this.method_ = new ArrayList<MethodDescriptorProto>(this.method_);
                    this.bitField0_ |= 2;
                }
            }

            @Override
            public List<MethodDescriptorProto> getMethodList() {
                if (this.methodBuilder_ == null) {
                    return Collections.unmodifiableList(this.method_);
                }
                return this.methodBuilder_.getMessageList();
            }

            @Override
            public int getMethodCount() {
                if (this.methodBuilder_ == null) {
                    return this.method_.size();
                }
                return this.methodBuilder_.getCount();
            }

            @Override
            public MethodDescriptorProto getMethod(int index) {
                if (this.methodBuilder_ == null) {
                    return this.method_.get(index);
                }
                return this.methodBuilder_.getMessage(index);
            }

            public Builder setMethod(int index, MethodDescriptorProto value) {
                if (this.methodBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureMethodIsMutable();
                    this.method_.set(index, value);
                    this.onChanged();
                } else {
                    this.methodBuilder_.setMessage(index, value);
                }
                return this;
            }

            public Builder setMethod(int index, MethodDescriptorProto.Builder builderForValue) {
                if (this.methodBuilder_ == null) {
                    this.ensureMethodIsMutable();
                    this.method_.set(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.methodBuilder_.setMessage(index, builderForValue.build());
                }
                return this;
            }

            public Builder addMethod(MethodDescriptorProto value) {
                if (this.methodBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureMethodIsMutable();
                    this.method_.add(value);
                    this.onChanged();
                } else {
                    this.methodBuilder_.addMessage(value);
                }
                return this;
            }

            public Builder addMethod(int index, MethodDescriptorProto value) {
                if (this.methodBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureMethodIsMutable();
                    this.method_.add(index, value);
                    this.onChanged();
                } else {
                    this.methodBuilder_.addMessage(index, value);
                }
                return this;
            }

            public Builder addMethod(MethodDescriptorProto.Builder builderForValue) {
                if (this.methodBuilder_ == null) {
                    this.ensureMethodIsMutable();
                    this.method_.add(builderForValue.build());
                    this.onChanged();
                } else {
                    this.methodBuilder_.addMessage(builderForValue.build());
                }
                return this;
            }

            public Builder addMethod(int index, MethodDescriptorProto.Builder builderForValue) {
                if (this.methodBuilder_ == null) {
                    this.ensureMethodIsMutable();
                    this.method_.add(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.methodBuilder_.addMessage(index, builderForValue.build());
                }
                return this;
            }

            public Builder addAllMethod(Iterable<? extends MethodDescriptorProto> values2) {
                if (this.methodBuilder_ == null) {
                    this.ensureMethodIsMutable();
                    AbstractMessageLite.Builder.addAll(values2, this.method_);
                    this.onChanged();
                } else {
                    this.methodBuilder_.addAllMessages(values2);
                }
                return this;
            }

            public Builder clearMethod() {
                if (this.methodBuilder_ == null) {
                    this.method_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFFFFD;
                    this.onChanged();
                } else {
                    this.methodBuilder_.clear();
                }
                return this;
            }

            public Builder removeMethod(int index) {
                if (this.methodBuilder_ == null) {
                    this.ensureMethodIsMutable();
                    this.method_.remove(index);
                    this.onChanged();
                } else {
                    this.methodBuilder_.remove(index);
                }
                return this;
            }

            public MethodDescriptorProto.Builder getMethodBuilder(int index) {
                return this.getMethodFieldBuilder().getBuilder(index);
            }

            @Override
            public MethodDescriptorProtoOrBuilder getMethodOrBuilder(int index) {
                if (this.methodBuilder_ == null) {
                    return this.method_.get(index);
                }
                return this.methodBuilder_.getMessageOrBuilder(index);
            }

            @Override
            public List<? extends MethodDescriptorProtoOrBuilder> getMethodOrBuilderList() {
                if (this.methodBuilder_ != null) {
                    return this.methodBuilder_.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.method_);
            }

            public MethodDescriptorProto.Builder addMethodBuilder() {
                return this.getMethodFieldBuilder().addBuilder(MethodDescriptorProto.getDefaultInstance());
            }

            public MethodDescriptorProto.Builder addMethodBuilder(int index) {
                return this.getMethodFieldBuilder().addBuilder(index, MethodDescriptorProto.getDefaultInstance());
            }

            public List<MethodDescriptorProto.Builder> getMethodBuilderList() {
                return this.getMethodFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilder<MethodDescriptorProto, MethodDescriptorProto.Builder, MethodDescriptorProtoOrBuilder> getMethodFieldBuilder() {
                if (this.methodBuilder_ == null) {
                    this.methodBuilder_ = new RepeatedFieldBuilder(this.method_, (this.bitField0_ & 2) == 2, this.getParentForChildren(), this.isClean());
                    this.method_ = null;
                }
                return this.methodBuilder_;
            }

            @Override
            public boolean hasOptions() {
                return (this.bitField0_ & 4) == 4;
            }

            @Override
            public ServiceOptions getOptions() {
                if (this.optionsBuilder_ == null) {
                    return this.options_;
                }
                return this.optionsBuilder_.getMessage();
            }

            public Builder setOptions(ServiceOptions value) {
                if (this.optionsBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.options_ = value;
                    this.onChanged();
                } else {
                    this.optionsBuilder_.setMessage(value);
                }
                this.bitField0_ |= 4;
                return this;
            }

            public Builder setOptions(ServiceOptions.Builder builderForValue) {
                if (this.optionsBuilder_ == null) {
                    this.options_ = builderForValue.build();
                    this.onChanged();
                } else {
                    this.optionsBuilder_.setMessage(builderForValue.build());
                }
                this.bitField0_ |= 4;
                return this;
            }

            public Builder mergeOptions(ServiceOptions value) {
                if (this.optionsBuilder_ == null) {
                    this.options_ = (this.bitField0_ & 4) == 4 && this.options_ != ServiceOptions.getDefaultInstance() ? ServiceOptions.newBuilder(this.options_).mergeFrom(value).buildPartial() : value;
                    this.onChanged();
                } else {
                    this.optionsBuilder_.mergeFrom(value);
                }
                this.bitField0_ |= 4;
                return this;
            }

            public Builder clearOptions() {
                if (this.optionsBuilder_ == null) {
                    this.options_ = ServiceOptions.getDefaultInstance();
                    this.onChanged();
                } else {
                    this.optionsBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFFFB;
                return this;
            }

            public ServiceOptions.Builder getOptionsBuilder() {
                this.bitField0_ |= 4;
                this.onChanged();
                return this.getOptionsFieldBuilder().getBuilder();
            }

            @Override
            public ServiceOptionsOrBuilder getOptionsOrBuilder() {
                if (this.optionsBuilder_ != null) {
                    return this.optionsBuilder_.getMessageOrBuilder();
                }
                return this.options_;
            }

            private SingleFieldBuilder<ServiceOptions, ServiceOptions.Builder, ServiceOptionsOrBuilder> getOptionsFieldBuilder() {
                if (this.optionsBuilder_ == null) {
                    this.optionsBuilder_ = new SingleFieldBuilder(this.getOptions(), this.getParentForChildren(), this.isClean());
                    this.options_ = null;
                }
                return this.optionsBuilder_;
            }
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static interface ServiceDescriptorProtoOrBuilder
    extends MessageOrBuilder {
        public boolean hasName();

        public String getName();

        public ByteString getNameBytes();

        public List<MethodDescriptorProto> getMethodList();

        public MethodDescriptorProto getMethod(int var1);

        public int getMethodCount();

        public List<? extends MethodDescriptorProtoOrBuilder> getMethodOrBuilderList();

        public MethodDescriptorProtoOrBuilder getMethodOrBuilder(int var1);

        public boolean hasOptions();

        public ServiceOptions getOptions();

        public ServiceOptionsOrBuilder getOptionsOrBuilder();
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static final class EnumValueDescriptorProto
    extends GeneratedMessage
    implements EnumValueDescriptorProtoOrBuilder {
        private static final EnumValueDescriptorProto defaultInstance;
        private final UnknownFieldSet unknownFields;
        public static Parser<EnumValueDescriptorProto> PARSER;
        private int bitField0_;
        public static final int NAME_FIELD_NUMBER = 1;
        private Object name_;
        public static final int NUMBER_FIELD_NUMBER = 2;
        private int number_;
        public static final int OPTIONS_FIELD_NUMBER = 3;
        private EnumValueOptions options_;
        private byte memoizedIsInitialized = (byte)-1;
        private int memoizedSerializedSize = -1;
        private static final long serialVersionUID = 0L;

        private EnumValueDescriptorProto(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.unknownFields = builder.getUnknownFields();
        }

        private EnumValueDescriptorProto(boolean noInit) {
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static EnumValueDescriptorProto getDefaultInstance() {
            return defaultInstance;
        }

        @Override
        public EnumValueDescriptorProto getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private EnumValueDescriptorProto(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this.initFields();
            boolean mutable_bitField0_ = false;
            UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
            try {
                try {
                    boolean done = false;
                    block11: while (!done) {
                        int tag = input.readTag();
                        switch (tag) {
                            case 0: {
                                done = true;
                                continue block11;
                            }
                            default: {
                                if (this.parseUnknownField(input, unknownFields, extensionRegistry, tag)) continue block11;
                                done = true;
                                continue block11;
                            }
                            case 10: {
                                ByteString bs = input.readBytes();
                                this.bitField0_ |= 1;
                                this.name_ = bs;
                                continue block11;
                            }
                            case 16: {
                                this.bitField0_ |= 2;
                                this.number_ = input.readInt32();
                                continue block11;
                            }
                            case 26: 
                        }
                        EnumValueOptions.Builder subBuilder = null;
                        if ((this.bitField0_ & 4) == 4) {
                            subBuilder = this.options_.toBuilder();
                        }
                        this.options_ = input.readMessage(EnumValueOptions.PARSER, extensionRegistry);
                        if (subBuilder != null) {
                            subBuilder.mergeFrom(this.options_);
                            this.options_ = subBuilder.buildPartial();
                        }
                        this.bitField0_ |= 4;
                    }
                    Object var9_10 = null;
                    this.unknownFields = unknownFields.build();
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e) {
                    throw new InvalidProtocolBufferException(e.getMessage()).setUnfinishedMessage(this);
                }
            } catch (Throwable throwable) {
                Object var9_11 = null;
                this.unknownFields = unknownFields.build();
                this.makeExtensionsImmutable();
                throw throwable;
            }
            this.makeExtensionsImmutable();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return internal_static_google_protobuf_EnumValueDescriptorProto_descriptor;
        }

        @Override
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return internal_static_google_protobuf_EnumValueDescriptorProto_fieldAccessorTable.ensureFieldAccessorsInitialized(EnumValueDescriptorProto.class, Builder.class);
        }

        public Parser<EnumValueDescriptorProto> getParserForType() {
            return PARSER;
        }

        @Override
        public boolean hasName() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override
        public String getName() {
            Object ref = this.name_;
            if (ref instanceof String) {
                return (String)ref;
            }
            ByteString bs = (ByteString)ref;
            String s2 = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.name_ = s2;
            }
            return s2;
        }

        @Override
        public ByteString getNameBytes() {
            Object ref = this.name_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.name_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        @Override
        public boolean hasNumber() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override
        public int getNumber() {
            return this.number_;
        }

        @Override
        public boolean hasOptions() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override
        public EnumValueOptions getOptions() {
            return this.options_;
        }

        @Override
        public EnumValueOptionsOrBuilder getOptionsOrBuilder() {
            return this.options_;
        }

        private void initFields() {
            this.name_ = "";
            this.number_ = 0;
            this.options_ = EnumValueOptions.getDefaultInstance();
        }

        @Override
        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            if (this.hasOptions() && !this.getOptions().isInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        @Override
        public void writeTo(CodedOutputStream output) throws IOException {
            this.getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                output.writeBytes(1, this.getNameBytes());
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeInt32(2, this.number_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeMessage(3, this.options_);
            }
            this.getUnknownFields().writeTo(output);
        }

        @Override
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            size = 0;
            if ((this.bitField0_ & 1) == 1) {
                size += CodedOutputStream.computeBytesSize(1, this.getNameBytes());
            }
            if ((this.bitField0_ & 2) == 2) {
                size += CodedOutputStream.computeInt32Size(2, this.number_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size += CodedOutputStream.computeMessageSize(3, this.options_);
            }
            this.memoizedSerializedSize = size += this.getUnknownFields().getSerializedSize();
            return size;
        }

        @Override
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static EnumValueDescriptorProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static EnumValueDescriptorProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static EnumValueDescriptorProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static EnumValueDescriptorProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static EnumValueDescriptorProto parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static EnumValueDescriptorProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static EnumValueDescriptorProto parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static EnumValueDescriptorProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static EnumValueDescriptorProto parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static EnumValueDescriptorProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override
        public Builder newBuilderForType() {
            return EnumValueDescriptorProto.newBuilder();
        }

        public static Builder newBuilder(EnumValueDescriptorProto prototype) {
            return EnumValueDescriptorProto.newBuilder().mergeFrom(prototype);
        }

        @Override
        public Builder toBuilder() {
            return EnumValueDescriptorProto.newBuilder(this);
        }

        @Override
        protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        static {
            PARSER = new AbstractParser<EnumValueDescriptorProto>(){

                @Override
                public EnumValueDescriptorProto parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                    return new EnumValueDescriptorProto(input, extensionRegistry);
                }
            };
            defaultInstance = new EnumValueDescriptorProto(true);
            defaultInstance.initFields();
        }

        /*
         * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
         */
        public static final class Builder
        extends GeneratedMessage.Builder<Builder>
        implements EnumValueDescriptorProtoOrBuilder {
            private int bitField0_;
            private Object name_ = "";
            private int number_;
            private EnumValueOptions options_ = EnumValueOptions.getDefaultInstance();
            private SingleFieldBuilder<EnumValueOptions, EnumValueOptions.Builder, EnumValueOptionsOrBuilder> optionsBuilder_;

            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_google_protobuf_EnumValueDescriptorProto_descriptor;
            }

            @Override
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_google_protobuf_EnumValueDescriptorProto_fieldAccessorTable.ensureFieldAccessorsInitialized(EnumValueDescriptorProto.class, Builder.class);
            }

            private Builder() {
                this.maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessage.BuilderParent parent) {
                super(parent);
                this.maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                if (GeneratedMessage.alwaysUseFieldBuilders) {
                    this.getOptionsFieldBuilder();
                }
            }

            private static Builder create() {
                return new Builder();
            }

            @Override
            public Builder clear() {
                super.clear();
                this.name_ = "";
                this.bitField0_ &= 0xFFFFFFFE;
                this.number_ = 0;
                this.bitField0_ &= 0xFFFFFFFD;
                if (this.optionsBuilder_ == null) {
                    this.options_ = EnumValueOptions.getDefaultInstance();
                } else {
                    this.optionsBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFFFB;
                return this;
            }

            @Override
            public Builder clone() {
                return Builder.create().mergeFrom(this.buildPartial());
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return internal_static_google_protobuf_EnumValueDescriptorProto_descriptor;
            }

            @Override
            public EnumValueDescriptorProto getDefaultInstanceForType() {
                return EnumValueDescriptorProto.getDefaultInstance();
            }

            @Override
            public EnumValueDescriptorProto build() {
                EnumValueDescriptorProto result = this.buildPartial();
                if (!result.isInitialized()) {
                    throw Builder.newUninitializedMessageException(result);
                }
                return result;
            }

            @Override
            public EnumValueDescriptorProto buildPartial() {
                EnumValueDescriptorProto result = new EnumValueDescriptorProto(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ |= 1;
                }
                result.name_ = this.name_;
                if ((from_bitField0_ & 2) == 2) {
                    to_bitField0_ |= 2;
                }
                result.number_ = this.number_;
                if ((from_bitField0_ & 4) == 4) {
                    to_bitField0_ |= 4;
                }
                if (this.optionsBuilder_ == null) {
                    result.options_ = this.options_;
                } else {
                    result.options_ = this.optionsBuilder_.build();
                }
                result.bitField0_ = to_bitField0_;
                this.onBuilt();
                return result;
            }

            @Override
            public Builder mergeFrom(Message other) {
                if (other instanceof EnumValueDescriptorProto) {
                    return this.mergeFrom((EnumValueDescriptorProto)other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(EnumValueDescriptorProto other) {
                if (other == EnumValueDescriptorProto.getDefaultInstance()) {
                    return this;
                }
                if (other.hasName()) {
                    this.bitField0_ |= 1;
                    this.name_ = other.name_;
                    this.onChanged();
                }
                if (other.hasNumber()) {
                    this.setNumber(other.getNumber());
                }
                if (other.hasOptions()) {
                    this.mergeOptions(other.getOptions());
                }
                this.mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            @Override
            public final boolean isInitialized() {
                return !this.hasOptions() || this.getOptions().isInitialized();
            }

            /*
             * Enabled force condition propagation
             * Lifted jumps to return sites
             */
            @Override
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                EnumValueDescriptorProto parsedMessage = null;
                try {
                    try {
                        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
                    } catch (InvalidProtocolBufferException e) {
                        parsedMessage = (EnumValueDescriptorProto)e.getUnfinishedMessage();
                        throw e;
                    }
                    Object var6_4 = null;
                    if (parsedMessage == null) return this;
                    this.mergeFrom(parsedMessage);
                    return this;
                } catch (Throwable throwable) {
                    Object var6_5 = null;
                    if (parsedMessage == null) throw throwable;
                    this.mergeFrom(parsedMessage);
                    throw throwable;
                }
            }

            @Override
            public boolean hasName() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override
            public String getName() {
                Object ref = this.name_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString)ref;
                    String s2 = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.name_ = s2;
                    }
                    return s2;
                }
                return (String)ref;
            }

            @Override
            public ByteString getNameBytes() {
                Object ref = this.name_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String)ref);
                    this.name_ = b;
                    return b;
                }
                return (ByteString)ref;
            }

            public Builder setName(String value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 1;
                this.name_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearName() {
                this.bitField0_ &= 0xFFFFFFFE;
                this.name_ = EnumValueDescriptorProto.getDefaultInstance().getName();
                this.onChanged();
                return this;
            }

            public Builder setNameBytes(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 1;
                this.name_ = value;
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasNumber() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override
            public int getNumber() {
                return this.number_;
            }

            public Builder setNumber(int value) {
                this.bitField0_ |= 2;
                this.number_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearNumber() {
                this.bitField0_ &= 0xFFFFFFFD;
                this.number_ = 0;
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasOptions() {
                return (this.bitField0_ & 4) == 4;
            }

            @Override
            public EnumValueOptions getOptions() {
                if (this.optionsBuilder_ == null) {
                    return this.options_;
                }
                return this.optionsBuilder_.getMessage();
            }

            public Builder setOptions(EnumValueOptions value) {
                if (this.optionsBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.options_ = value;
                    this.onChanged();
                } else {
                    this.optionsBuilder_.setMessage(value);
                }
                this.bitField0_ |= 4;
                return this;
            }

            public Builder setOptions(EnumValueOptions.Builder builderForValue) {
                if (this.optionsBuilder_ == null) {
                    this.options_ = builderForValue.build();
                    this.onChanged();
                } else {
                    this.optionsBuilder_.setMessage(builderForValue.build());
                }
                this.bitField0_ |= 4;
                return this;
            }

            public Builder mergeOptions(EnumValueOptions value) {
                if (this.optionsBuilder_ == null) {
                    this.options_ = (this.bitField0_ & 4) == 4 && this.options_ != EnumValueOptions.getDefaultInstance() ? EnumValueOptions.newBuilder(this.options_).mergeFrom(value).buildPartial() : value;
                    this.onChanged();
                } else {
                    this.optionsBuilder_.mergeFrom(value);
                }
                this.bitField0_ |= 4;
                return this;
            }

            public Builder clearOptions() {
                if (this.optionsBuilder_ == null) {
                    this.options_ = EnumValueOptions.getDefaultInstance();
                    this.onChanged();
                } else {
                    this.optionsBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFFFB;
                return this;
            }

            public EnumValueOptions.Builder getOptionsBuilder() {
                this.bitField0_ |= 4;
                this.onChanged();
                return this.getOptionsFieldBuilder().getBuilder();
            }

            @Override
            public EnumValueOptionsOrBuilder getOptionsOrBuilder() {
                if (this.optionsBuilder_ != null) {
                    return this.optionsBuilder_.getMessageOrBuilder();
                }
                return this.options_;
            }

            private SingleFieldBuilder<EnumValueOptions, EnumValueOptions.Builder, EnumValueOptionsOrBuilder> getOptionsFieldBuilder() {
                if (this.optionsBuilder_ == null) {
                    this.optionsBuilder_ = new SingleFieldBuilder(this.getOptions(), this.getParentForChildren(), this.isClean());
                    this.options_ = null;
                }
                return this.optionsBuilder_;
            }
        }
    }

    public static interface EnumValueDescriptorProtoOrBuilder
    extends MessageOrBuilder {
        public boolean hasName();

        public String getName();

        public ByteString getNameBytes();

        public boolean hasNumber();

        public int getNumber();

        public boolean hasOptions();

        public EnumValueOptions getOptions();

        public EnumValueOptionsOrBuilder getOptionsOrBuilder();
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static final class EnumDescriptorProto
    extends GeneratedMessage
    implements EnumDescriptorProtoOrBuilder {
        private static final EnumDescriptorProto defaultInstance;
        private final UnknownFieldSet unknownFields;
        public static Parser<EnumDescriptorProto> PARSER;
        private int bitField0_;
        public static final int NAME_FIELD_NUMBER = 1;
        private Object name_;
        public static final int VALUE_FIELD_NUMBER = 2;
        private List<EnumValueDescriptorProto> value_;
        public static final int OPTIONS_FIELD_NUMBER = 3;
        private EnumOptions options_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private static final long serialVersionUID = 0L;

        private EnumDescriptorProto(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte)-1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private EnumDescriptorProto(boolean noInit) {
            this.memoizedIsInitialized = (byte)-1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static EnumDescriptorProto getDefaultInstance() {
            return defaultInstance;
        }

        @Override
        public EnumDescriptorProto getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        private EnumDescriptorProto(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            UnknownFieldSet.Builder unknownFields;
            block17: {
                this.memoizedIsInitialized = (byte)-1;
                this.memoizedSerializedSize = -1;
                this.initFields();
                int mutable_bitField0_ = 0;
                unknownFields = UnknownFieldSet.newBuilder();
                try {
                    try {
                        boolean done = false;
                        block11: while (!done) {
                            int tag = input.readTag();
                            switch (tag) {
                                case 0: {
                                    done = true;
                                    continue block11;
                                }
                                default: {
                                    if (this.parseUnknownField(input, unknownFields, extensionRegistry, tag)) continue block11;
                                    done = true;
                                    continue block11;
                                }
                                case 10: {
                                    ByteString bs = input.readBytes();
                                    this.bitField0_ |= 1;
                                    this.name_ = bs;
                                    continue block11;
                                }
                                case 18: {
                                    if ((mutable_bitField0_ & 2) != 2) {
                                        this.value_ = new ArrayList<EnumValueDescriptorProto>();
                                        mutable_bitField0_ |= 2;
                                    }
                                    this.value_.add(input.readMessage(EnumValueDescriptorProto.PARSER, extensionRegistry));
                                    continue block11;
                                }
                                case 26: 
                            }
                            EnumOptions.Builder subBuilder = null;
                            if ((this.bitField0_ & 2) == 2) {
                                subBuilder = this.options_.toBuilder();
                            }
                            this.options_ = input.readMessage(EnumOptions.PARSER, extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom(this.options_);
                                this.options_ = subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 2;
                        }
                        Object var9_10 = null;
                        if ((mutable_bitField0_ & 2) != 2) break block17;
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e) {
                        throw new InvalidProtocolBufferException(e.getMessage()).setUnfinishedMessage(this);
                    }
                } catch (Throwable throwable) {
                    Object var9_11 = null;
                    if ((mutable_bitField0_ & 2) == 2) {
                        this.value_ = Collections.unmodifiableList(this.value_);
                    }
                    this.unknownFields = unknownFields.build();
                    this.makeExtensionsImmutable();
                    throw throwable;
                }
                this.value_ = Collections.unmodifiableList(this.value_);
            }
            this.unknownFields = unknownFields.build();
            this.makeExtensionsImmutable();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return internal_static_google_protobuf_EnumDescriptorProto_descriptor;
        }

        @Override
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return internal_static_google_protobuf_EnumDescriptorProto_fieldAccessorTable.ensureFieldAccessorsInitialized(EnumDescriptorProto.class, Builder.class);
        }

        public Parser<EnumDescriptorProto> getParserForType() {
            return PARSER;
        }

        @Override
        public boolean hasName() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override
        public String getName() {
            Object ref = this.name_;
            if (ref instanceof String) {
                return (String)ref;
            }
            ByteString bs = (ByteString)ref;
            String s2 = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.name_ = s2;
            }
            return s2;
        }

        @Override
        public ByteString getNameBytes() {
            Object ref = this.name_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.name_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        @Override
        public List<EnumValueDescriptorProto> getValueList() {
            return this.value_;
        }

        @Override
        public List<? extends EnumValueDescriptorProtoOrBuilder> getValueOrBuilderList() {
            return this.value_;
        }

        @Override
        public int getValueCount() {
            return this.value_.size();
        }

        @Override
        public EnumValueDescriptorProto getValue(int index) {
            return this.value_.get(index);
        }

        @Override
        public EnumValueDescriptorProtoOrBuilder getValueOrBuilder(int index) {
            return this.value_.get(index);
        }

        @Override
        public boolean hasOptions() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override
        public EnumOptions getOptions() {
            return this.options_;
        }

        @Override
        public EnumOptionsOrBuilder getOptionsOrBuilder() {
            return this.options_;
        }

        private void initFields() {
            this.name_ = "";
            this.value_ = Collections.emptyList();
            this.options_ = EnumOptions.getDefaultInstance();
        }

        @Override
        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            for (int i = 0; i < this.getValueCount(); ++i) {
                if (this.getValue(i).isInitialized()) continue;
                this.memoizedIsInitialized = 0;
                return false;
            }
            if (this.hasOptions() && !this.getOptions().isInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        @Override
        public void writeTo(CodedOutputStream output) throws IOException {
            this.getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                output.writeBytes(1, this.getNameBytes());
            }
            for (int i = 0; i < this.value_.size(); ++i) {
                output.writeMessage(2, this.value_.get(i));
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeMessage(3, this.options_);
            }
            this.getUnknownFields().writeTo(output);
        }

        @Override
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            size = 0;
            if ((this.bitField0_ & 1) == 1) {
                size += CodedOutputStream.computeBytesSize(1, this.getNameBytes());
            }
            for (int i = 0; i < this.value_.size(); ++i) {
                size += CodedOutputStream.computeMessageSize(2, this.value_.get(i));
            }
            if ((this.bitField0_ & 2) == 2) {
                size += CodedOutputStream.computeMessageSize(3, this.options_);
            }
            this.memoizedSerializedSize = size += this.getUnknownFields().getSerializedSize();
            return size;
        }

        @Override
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static EnumDescriptorProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static EnumDescriptorProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static EnumDescriptorProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static EnumDescriptorProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static EnumDescriptorProto parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static EnumDescriptorProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static EnumDescriptorProto parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static EnumDescriptorProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static EnumDescriptorProto parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static EnumDescriptorProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override
        public Builder newBuilderForType() {
            return EnumDescriptorProto.newBuilder();
        }

        public static Builder newBuilder(EnumDescriptorProto prototype) {
            return EnumDescriptorProto.newBuilder().mergeFrom(prototype);
        }

        @Override
        public Builder toBuilder() {
            return EnumDescriptorProto.newBuilder(this);
        }

        @Override
        protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        static {
            PARSER = new AbstractParser<EnumDescriptorProto>(){

                @Override
                public EnumDescriptorProto parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                    return new EnumDescriptorProto(input, extensionRegistry);
                }
            };
            defaultInstance = new EnumDescriptorProto(true);
            defaultInstance.initFields();
        }

        /*
         * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
         */
        public static final class Builder
        extends GeneratedMessage.Builder<Builder>
        implements EnumDescriptorProtoOrBuilder {
            private int bitField0_;
            private Object name_ = "";
            private List<EnumValueDescriptorProto> value_ = Collections.emptyList();
            private RepeatedFieldBuilder<EnumValueDescriptorProto, EnumValueDescriptorProto.Builder, EnumValueDescriptorProtoOrBuilder> valueBuilder_;
            private EnumOptions options_ = EnumOptions.getDefaultInstance();
            private SingleFieldBuilder<EnumOptions, EnumOptions.Builder, EnumOptionsOrBuilder> optionsBuilder_;

            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_google_protobuf_EnumDescriptorProto_descriptor;
            }

            @Override
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_google_protobuf_EnumDescriptorProto_fieldAccessorTable.ensureFieldAccessorsInitialized(EnumDescriptorProto.class, Builder.class);
            }

            private Builder() {
                this.maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessage.BuilderParent parent) {
                super(parent);
                this.maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                if (GeneratedMessage.alwaysUseFieldBuilders) {
                    this.getValueFieldBuilder();
                    this.getOptionsFieldBuilder();
                }
            }

            private static Builder create() {
                return new Builder();
            }

            @Override
            public Builder clear() {
                super.clear();
                this.name_ = "";
                this.bitField0_ &= 0xFFFFFFFE;
                if (this.valueBuilder_ == null) {
                    this.value_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFFFFD;
                } else {
                    this.valueBuilder_.clear();
                }
                if (this.optionsBuilder_ == null) {
                    this.options_ = EnumOptions.getDefaultInstance();
                } else {
                    this.optionsBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFFFB;
                return this;
            }

            @Override
            public Builder clone() {
                return Builder.create().mergeFrom(this.buildPartial());
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return internal_static_google_protobuf_EnumDescriptorProto_descriptor;
            }

            @Override
            public EnumDescriptorProto getDefaultInstanceForType() {
                return EnumDescriptorProto.getDefaultInstance();
            }

            @Override
            public EnumDescriptorProto build() {
                EnumDescriptorProto result = this.buildPartial();
                if (!result.isInitialized()) {
                    throw Builder.newUninitializedMessageException(result);
                }
                return result;
            }

            @Override
            public EnumDescriptorProto buildPartial() {
                EnumDescriptorProto result = new EnumDescriptorProto(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ |= 1;
                }
                result.name_ = this.name_;
                if (this.valueBuilder_ == null) {
                    if ((this.bitField0_ & 2) == 2) {
                        this.value_ = Collections.unmodifiableList(this.value_);
                        this.bitField0_ &= 0xFFFFFFFD;
                    }
                    result.value_ = this.value_;
                } else {
                    result.value_ = this.valueBuilder_.build();
                }
                if ((from_bitField0_ & 4) == 4) {
                    to_bitField0_ |= 2;
                }
                if (this.optionsBuilder_ == null) {
                    result.options_ = this.options_;
                } else {
                    result.options_ = this.optionsBuilder_.build();
                }
                result.bitField0_ = to_bitField0_;
                this.onBuilt();
                return result;
            }

            @Override
            public Builder mergeFrom(Message other) {
                if (other instanceof EnumDescriptorProto) {
                    return this.mergeFrom((EnumDescriptorProto)other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(EnumDescriptorProto other) {
                if (other == EnumDescriptorProto.getDefaultInstance()) {
                    return this;
                }
                if (other.hasName()) {
                    this.bitField0_ |= 1;
                    this.name_ = other.name_;
                    this.onChanged();
                }
                if (this.valueBuilder_ == null) {
                    if (!other.value_.isEmpty()) {
                        if (this.value_.isEmpty()) {
                            this.value_ = other.value_;
                            this.bitField0_ &= 0xFFFFFFFD;
                        } else {
                            this.ensureValueIsMutable();
                            this.value_.addAll(other.value_);
                        }
                        this.onChanged();
                    }
                } else if (!other.value_.isEmpty()) {
                    if (this.valueBuilder_.isEmpty()) {
                        this.valueBuilder_.dispose();
                        this.valueBuilder_ = null;
                        this.value_ = other.value_;
                        this.bitField0_ &= 0xFFFFFFFD;
                        this.valueBuilder_ = GeneratedMessage.alwaysUseFieldBuilders ? this.getValueFieldBuilder() : null;
                    } else {
                        this.valueBuilder_.addAllMessages(other.value_);
                    }
                }
                if (other.hasOptions()) {
                    this.mergeOptions(other.getOptions());
                }
                this.mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            @Override
            public final boolean isInitialized() {
                for (int i = 0; i < this.getValueCount(); ++i) {
                    if (this.getValue(i).isInitialized()) continue;
                    return false;
                }
                return !this.hasOptions() || this.getOptions().isInitialized();
            }

            /*
             * Enabled force condition propagation
             * Lifted jumps to return sites
             */
            @Override
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                EnumDescriptorProto parsedMessage = null;
                try {
                    try {
                        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
                    } catch (InvalidProtocolBufferException e) {
                        parsedMessage = (EnumDescriptorProto)e.getUnfinishedMessage();
                        throw e;
                    }
                    Object var6_4 = null;
                    if (parsedMessage == null) return this;
                    this.mergeFrom(parsedMessage);
                    return this;
                } catch (Throwable throwable) {
                    Object var6_5 = null;
                    if (parsedMessage == null) throw throwable;
                    this.mergeFrom(parsedMessage);
                    throw throwable;
                }
            }

            @Override
            public boolean hasName() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override
            public String getName() {
                Object ref = this.name_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString)ref;
                    String s2 = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.name_ = s2;
                    }
                    return s2;
                }
                return (String)ref;
            }

            @Override
            public ByteString getNameBytes() {
                Object ref = this.name_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String)ref);
                    this.name_ = b;
                    return b;
                }
                return (ByteString)ref;
            }

            public Builder setName(String value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 1;
                this.name_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearName() {
                this.bitField0_ &= 0xFFFFFFFE;
                this.name_ = EnumDescriptorProto.getDefaultInstance().getName();
                this.onChanged();
                return this;
            }

            public Builder setNameBytes(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 1;
                this.name_ = value;
                this.onChanged();
                return this;
            }

            private void ensureValueIsMutable() {
                if ((this.bitField0_ & 2) != 2) {
                    this.value_ = new ArrayList<EnumValueDescriptorProto>(this.value_);
                    this.bitField0_ |= 2;
                }
            }

            @Override
            public List<EnumValueDescriptorProto> getValueList() {
                if (this.valueBuilder_ == null) {
                    return Collections.unmodifiableList(this.value_);
                }
                return this.valueBuilder_.getMessageList();
            }

            @Override
            public int getValueCount() {
                if (this.valueBuilder_ == null) {
                    return this.value_.size();
                }
                return this.valueBuilder_.getCount();
            }

            @Override
            public EnumValueDescriptorProto getValue(int index) {
                if (this.valueBuilder_ == null) {
                    return this.value_.get(index);
                }
                return this.valueBuilder_.getMessage(index);
            }

            public Builder setValue(int index, EnumValueDescriptorProto value) {
                if (this.valueBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureValueIsMutable();
                    this.value_.set(index, value);
                    this.onChanged();
                } else {
                    this.valueBuilder_.setMessage(index, value);
                }
                return this;
            }

            public Builder setValue(int index, EnumValueDescriptorProto.Builder builderForValue) {
                if (this.valueBuilder_ == null) {
                    this.ensureValueIsMutable();
                    this.value_.set(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.valueBuilder_.setMessage(index, builderForValue.build());
                }
                return this;
            }

            public Builder addValue(EnumValueDescriptorProto value) {
                if (this.valueBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureValueIsMutable();
                    this.value_.add(value);
                    this.onChanged();
                } else {
                    this.valueBuilder_.addMessage(value);
                }
                return this;
            }

            public Builder addValue(int index, EnumValueDescriptorProto value) {
                if (this.valueBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureValueIsMutable();
                    this.value_.add(index, value);
                    this.onChanged();
                } else {
                    this.valueBuilder_.addMessage(index, value);
                }
                return this;
            }

            public Builder addValue(EnumValueDescriptorProto.Builder builderForValue) {
                if (this.valueBuilder_ == null) {
                    this.ensureValueIsMutable();
                    this.value_.add(builderForValue.build());
                    this.onChanged();
                } else {
                    this.valueBuilder_.addMessage(builderForValue.build());
                }
                return this;
            }

            public Builder addValue(int index, EnumValueDescriptorProto.Builder builderForValue) {
                if (this.valueBuilder_ == null) {
                    this.ensureValueIsMutable();
                    this.value_.add(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.valueBuilder_.addMessage(index, builderForValue.build());
                }
                return this;
            }

            public Builder addAllValue(Iterable<? extends EnumValueDescriptorProto> values2) {
                if (this.valueBuilder_ == null) {
                    this.ensureValueIsMutable();
                    AbstractMessageLite.Builder.addAll(values2, this.value_);
                    this.onChanged();
                } else {
                    this.valueBuilder_.addAllMessages(values2);
                }
                return this;
            }

            public Builder clearValue() {
                if (this.valueBuilder_ == null) {
                    this.value_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFFFFD;
                    this.onChanged();
                } else {
                    this.valueBuilder_.clear();
                }
                return this;
            }

            public Builder removeValue(int index) {
                if (this.valueBuilder_ == null) {
                    this.ensureValueIsMutable();
                    this.value_.remove(index);
                    this.onChanged();
                } else {
                    this.valueBuilder_.remove(index);
                }
                return this;
            }

            public EnumValueDescriptorProto.Builder getValueBuilder(int index) {
                return this.getValueFieldBuilder().getBuilder(index);
            }

            @Override
            public EnumValueDescriptorProtoOrBuilder getValueOrBuilder(int index) {
                if (this.valueBuilder_ == null) {
                    return this.value_.get(index);
                }
                return this.valueBuilder_.getMessageOrBuilder(index);
            }

            @Override
            public List<? extends EnumValueDescriptorProtoOrBuilder> getValueOrBuilderList() {
                if (this.valueBuilder_ != null) {
                    return this.valueBuilder_.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.value_);
            }

            public EnumValueDescriptorProto.Builder addValueBuilder() {
                return this.getValueFieldBuilder().addBuilder(EnumValueDescriptorProto.getDefaultInstance());
            }

            public EnumValueDescriptorProto.Builder addValueBuilder(int index) {
                return this.getValueFieldBuilder().addBuilder(index, EnumValueDescriptorProto.getDefaultInstance());
            }

            public List<EnumValueDescriptorProto.Builder> getValueBuilderList() {
                return this.getValueFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilder<EnumValueDescriptorProto, EnumValueDescriptorProto.Builder, EnumValueDescriptorProtoOrBuilder> getValueFieldBuilder() {
                if (this.valueBuilder_ == null) {
                    this.valueBuilder_ = new RepeatedFieldBuilder(this.value_, (this.bitField0_ & 2) == 2, this.getParentForChildren(), this.isClean());
                    this.value_ = null;
                }
                return this.valueBuilder_;
            }

            @Override
            public boolean hasOptions() {
                return (this.bitField0_ & 4) == 4;
            }

            @Override
            public EnumOptions getOptions() {
                if (this.optionsBuilder_ == null) {
                    return this.options_;
                }
                return this.optionsBuilder_.getMessage();
            }

            public Builder setOptions(EnumOptions value) {
                if (this.optionsBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.options_ = value;
                    this.onChanged();
                } else {
                    this.optionsBuilder_.setMessage(value);
                }
                this.bitField0_ |= 4;
                return this;
            }

            public Builder setOptions(EnumOptions.Builder builderForValue) {
                if (this.optionsBuilder_ == null) {
                    this.options_ = builderForValue.build();
                    this.onChanged();
                } else {
                    this.optionsBuilder_.setMessage(builderForValue.build());
                }
                this.bitField0_ |= 4;
                return this;
            }

            public Builder mergeOptions(EnumOptions value) {
                if (this.optionsBuilder_ == null) {
                    this.options_ = (this.bitField0_ & 4) == 4 && this.options_ != EnumOptions.getDefaultInstance() ? EnumOptions.newBuilder(this.options_).mergeFrom(value).buildPartial() : value;
                    this.onChanged();
                } else {
                    this.optionsBuilder_.mergeFrom(value);
                }
                this.bitField0_ |= 4;
                return this;
            }

            public Builder clearOptions() {
                if (this.optionsBuilder_ == null) {
                    this.options_ = EnumOptions.getDefaultInstance();
                    this.onChanged();
                } else {
                    this.optionsBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFFFB;
                return this;
            }

            public EnumOptions.Builder getOptionsBuilder() {
                this.bitField0_ |= 4;
                this.onChanged();
                return this.getOptionsFieldBuilder().getBuilder();
            }

            @Override
            public EnumOptionsOrBuilder getOptionsOrBuilder() {
                if (this.optionsBuilder_ != null) {
                    return this.optionsBuilder_.getMessageOrBuilder();
                }
                return this.options_;
            }

            private SingleFieldBuilder<EnumOptions, EnumOptions.Builder, EnumOptionsOrBuilder> getOptionsFieldBuilder() {
                if (this.optionsBuilder_ == null) {
                    this.optionsBuilder_ = new SingleFieldBuilder(this.getOptions(), this.getParentForChildren(), this.isClean());
                    this.options_ = null;
                }
                return this.optionsBuilder_;
            }
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static interface EnumDescriptorProtoOrBuilder
    extends MessageOrBuilder {
        public boolean hasName();

        public String getName();

        public ByteString getNameBytes();

        public List<EnumValueDescriptorProto> getValueList();

        public EnumValueDescriptorProto getValue(int var1);

        public int getValueCount();

        public List<? extends EnumValueDescriptorProtoOrBuilder> getValueOrBuilderList();

        public EnumValueDescriptorProtoOrBuilder getValueOrBuilder(int var1);

        public boolean hasOptions();

        public EnumOptions getOptions();

        public EnumOptionsOrBuilder getOptionsOrBuilder();
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static final class OneofDescriptorProto
    extends GeneratedMessage
    implements OneofDescriptorProtoOrBuilder {
        private static final OneofDescriptorProto defaultInstance;
        private final UnknownFieldSet unknownFields;
        public static Parser<OneofDescriptorProto> PARSER;
        private int bitField0_;
        public static final int NAME_FIELD_NUMBER = 1;
        private Object name_;
        private byte memoizedIsInitialized = (byte)-1;
        private int memoizedSerializedSize = -1;
        private static final long serialVersionUID = 0L;

        private OneofDescriptorProto(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.unknownFields = builder.getUnknownFields();
        }

        private OneofDescriptorProto(boolean noInit) {
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static OneofDescriptorProto getDefaultInstance() {
            return defaultInstance;
        }

        @Override
        public OneofDescriptorProto getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private OneofDescriptorProto(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this.initFields();
            boolean mutable_bitField0_ = false;
            UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
            try {
                try {
                    boolean done = false;
                    block9: while (!done) {
                        int tag = input.readTag();
                        switch (tag) {
                            case 0: {
                                done = true;
                                continue block9;
                            }
                            default: {
                                if (this.parseUnknownField(input, unknownFields, extensionRegistry, tag)) continue block9;
                                done = true;
                                continue block9;
                            }
                            case 10: 
                        }
                        ByteString bs = input.readBytes();
                        this.bitField0_ |= 1;
                        this.name_ = bs;
                    }
                    Object var9_10 = null;
                    this.unknownFields = unknownFields.build();
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e) {
                    throw new InvalidProtocolBufferException(e.getMessage()).setUnfinishedMessage(this);
                }
            } catch (Throwable throwable) {
                Object var9_11 = null;
                this.unknownFields = unknownFields.build();
                this.makeExtensionsImmutable();
                throw throwable;
            }
            this.makeExtensionsImmutable();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return internal_static_google_protobuf_OneofDescriptorProto_descriptor;
        }

        @Override
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return internal_static_google_protobuf_OneofDescriptorProto_fieldAccessorTable.ensureFieldAccessorsInitialized(OneofDescriptorProto.class, Builder.class);
        }

        public Parser<OneofDescriptorProto> getParserForType() {
            return PARSER;
        }

        @Override
        public boolean hasName() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override
        public String getName() {
            Object ref = this.name_;
            if (ref instanceof String) {
                return (String)ref;
            }
            ByteString bs = (ByteString)ref;
            String s2 = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.name_ = s2;
            }
            return s2;
        }

        @Override
        public ByteString getNameBytes() {
            Object ref = this.name_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.name_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        private void initFields() {
            this.name_ = "";
        }

        @Override
        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        @Override
        public void writeTo(CodedOutputStream output) throws IOException {
            this.getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                output.writeBytes(1, this.getNameBytes());
            }
            this.getUnknownFields().writeTo(output);
        }

        @Override
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            size = 0;
            if ((this.bitField0_ & 1) == 1) {
                size += CodedOutputStream.computeBytesSize(1, this.getNameBytes());
            }
            this.memoizedSerializedSize = size += this.getUnknownFields().getSerializedSize();
            return size;
        }

        @Override
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static OneofDescriptorProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static OneofDescriptorProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static OneofDescriptorProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static OneofDescriptorProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static OneofDescriptorProto parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static OneofDescriptorProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static OneofDescriptorProto parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static OneofDescriptorProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static OneofDescriptorProto parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static OneofDescriptorProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override
        public Builder newBuilderForType() {
            return OneofDescriptorProto.newBuilder();
        }

        public static Builder newBuilder(OneofDescriptorProto prototype) {
            return OneofDescriptorProto.newBuilder().mergeFrom(prototype);
        }

        @Override
        public Builder toBuilder() {
            return OneofDescriptorProto.newBuilder(this);
        }

        @Override
        protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        static {
            PARSER = new AbstractParser<OneofDescriptorProto>(){

                @Override
                public OneofDescriptorProto parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                    return new OneofDescriptorProto(input, extensionRegistry);
                }
            };
            defaultInstance = new OneofDescriptorProto(true);
            defaultInstance.initFields();
        }

        /*
         * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
         */
        public static final class Builder
        extends GeneratedMessage.Builder<Builder>
        implements OneofDescriptorProtoOrBuilder {
            private int bitField0_;
            private Object name_ = "";

            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_google_protobuf_OneofDescriptorProto_descriptor;
            }

            @Override
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_google_protobuf_OneofDescriptorProto_fieldAccessorTable.ensureFieldAccessorsInitialized(OneofDescriptorProto.class, Builder.class);
            }

            private Builder() {
                this.maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessage.BuilderParent parent) {
                super(parent);
                this.maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                if (GeneratedMessage.alwaysUseFieldBuilders) {
                    // empty if block
                }
            }

            private static Builder create() {
                return new Builder();
            }

            @Override
            public Builder clear() {
                super.clear();
                this.name_ = "";
                this.bitField0_ &= 0xFFFFFFFE;
                return this;
            }

            @Override
            public Builder clone() {
                return Builder.create().mergeFrom(this.buildPartial());
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return internal_static_google_protobuf_OneofDescriptorProto_descriptor;
            }

            @Override
            public OneofDescriptorProto getDefaultInstanceForType() {
                return OneofDescriptorProto.getDefaultInstance();
            }

            @Override
            public OneofDescriptorProto build() {
                OneofDescriptorProto result = this.buildPartial();
                if (!result.isInitialized()) {
                    throw Builder.newUninitializedMessageException(result);
                }
                return result;
            }

            @Override
            public OneofDescriptorProto buildPartial() {
                OneofDescriptorProto result = new OneofDescriptorProto(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ |= 1;
                }
                result.name_ = this.name_;
                result.bitField0_ = to_bitField0_;
                this.onBuilt();
                return result;
            }

            @Override
            public Builder mergeFrom(Message other) {
                if (other instanceof OneofDescriptorProto) {
                    return this.mergeFrom((OneofDescriptorProto)other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(OneofDescriptorProto other) {
                if (other == OneofDescriptorProto.getDefaultInstance()) {
                    return this;
                }
                if (other.hasName()) {
                    this.bitField0_ |= 1;
                    this.name_ = other.name_;
                    this.onChanged();
                }
                this.mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            @Override
            public final boolean isInitialized() {
                return true;
            }

            /*
             * Enabled force condition propagation
             * Lifted jumps to return sites
             */
            @Override
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                OneofDescriptorProto parsedMessage = null;
                try {
                    try {
                        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
                    } catch (InvalidProtocolBufferException e) {
                        parsedMessage = (OneofDescriptorProto)e.getUnfinishedMessage();
                        throw e;
                    }
                    Object var6_4 = null;
                    if (parsedMessage == null) return this;
                    this.mergeFrom(parsedMessage);
                    return this;
                } catch (Throwable throwable) {
                    Object var6_5 = null;
                    if (parsedMessage == null) throw throwable;
                    this.mergeFrom(parsedMessage);
                    throw throwable;
                }
            }

            @Override
            public boolean hasName() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override
            public String getName() {
                Object ref = this.name_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString)ref;
                    String s2 = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.name_ = s2;
                    }
                    return s2;
                }
                return (String)ref;
            }

            @Override
            public ByteString getNameBytes() {
                Object ref = this.name_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String)ref);
                    this.name_ = b;
                    return b;
                }
                return (ByteString)ref;
            }

            public Builder setName(String value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 1;
                this.name_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearName() {
                this.bitField0_ &= 0xFFFFFFFE;
                this.name_ = OneofDescriptorProto.getDefaultInstance().getName();
                this.onChanged();
                return this;
            }

            public Builder setNameBytes(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 1;
                this.name_ = value;
                this.onChanged();
                return this;
            }
        }
    }

    public static interface OneofDescriptorProtoOrBuilder
    extends MessageOrBuilder {
        public boolean hasName();

        public String getName();

        public ByteString getNameBytes();
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static final class FieldDescriptorProto
    extends GeneratedMessage
    implements FieldDescriptorProtoOrBuilder {
        private static final FieldDescriptorProto defaultInstance;
        private final UnknownFieldSet unknownFields;
        public static Parser<FieldDescriptorProto> PARSER;
        private int bitField0_;
        public static final int NAME_FIELD_NUMBER = 1;
        private Object name_;
        public static final int NUMBER_FIELD_NUMBER = 3;
        private int number_;
        public static final int LABEL_FIELD_NUMBER = 4;
        private Label label_;
        public static final int TYPE_FIELD_NUMBER = 5;
        private Type type_;
        public static final int TYPE_NAME_FIELD_NUMBER = 6;
        private Object typeName_;
        public static final int EXTENDEE_FIELD_NUMBER = 2;
        private Object extendee_;
        public static final int DEFAULT_VALUE_FIELD_NUMBER = 7;
        private Object defaultValue_;
        public static final int ONEOF_INDEX_FIELD_NUMBER = 9;
        private int oneofIndex_;
        public static final int OPTIONS_FIELD_NUMBER = 8;
        private FieldOptions options_;
        private byte memoizedIsInitialized = (byte)-1;
        private int memoizedSerializedSize = -1;
        private static final long serialVersionUID = 0L;

        private FieldDescriptorProto(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.unknownFields = builder.getUnknownFields();
        }

        private FieldDescriptorProto(boolean noInit) {
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static FieldDescriptorProto getDefaultInstance() {
            return defaultInstance;
        }

        @Override
        public FieldDescriptorProto getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private FieldDescriptorProto(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this.initFields();
            boolean mutable_bitField0_ = false;
            UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
            try {
                try {
                    boolean done = false;
                    block17: while (!done) {
                        int tag = input.readTag();
                        switch (tag) {
                            case 0: {
                                done = true;
                                continue block17;
                            }
                            default: {
                                if (this.parseUnknownField(input, unknownFields, extensionRegistry, tag)) continue block17;
                                done = true;
                                continue block17;
                            }
                            case 10: {
                                ByteString bs = input.readBytes();
                                this.bitField0_ |= 1;
                                this.name_ = bs;
                                continue block17;
                            }
                            case 18: {
                                ByteString bs = input.readBytes();
                                this.bitField0_ |= 0x20;
                                this.extendee_ = bs;
                                continue block17;
                            }
                            case 24: {
                                this.bitField0_ |= 2;
                                this.number_ = input.readInt32();
                                continue block17;
                            }
                            case 32: {
                                int rawValue = input.readEnum();
                                Enum value = Label.valueOf(rawValue);
                                if (value == null) {
                                    unknownFields.mergeVarintField(4, rawValue);
                                    continue block17;
                                }
                                this.bitField0_ |= 4;
                                this.label_ = value;
                                continue block17;
                            }
                            case 40: {
                                int rawValue = input.readEnum();
                                Enum value = Type.valueOf(rawValue);
                                if (value == null) {
                                    unknownFields.mergeVarintField(5, rawValue);
                                    continue block17;
                                }
                                this.bitField0_ |= 8;
                                this.type_ = value;
                                continue block17;
                            }
                            case 50: {
                                ByteString bs = input.readBytes();
                                this.bitField0_ |= 0x10;
                                this.typeName_ = bs;
                                continue block17;
                            }
                            case 58: {
                                ByteString bs = input.readBytes();
                                this.bitField0_ |= 0x40;
                                this.defaultValue_ = bs;
                                continue block17;
                            }
                            case 66: {
                                FieldOptions.Builder subBuilder = null;
                                if ((this.bitField0_ & 0x100) == 256) {
                                    subBuilder = this.options_.toBuilder();
                                }
                                this.options_ = input.readMessage(FieldOptions.PARSER, extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom(this.options_);
                                    this.options_ = subBuilder.buildPartial();
                                }
                                this.bitField0_ |= 0x100;
                                continue block17;
                            }
                            case 72: 
                        }
                        this.bitField0_ |= 0x80;
                        this.oneofIndex_ = input.readInt32();
                    }
                    Object var10_17 = null;
                    this.unknownFields = unknownFields.build();
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e) {
                    throw new InvalidProtocolBufferException(e.getMessage()).setUnfinishedMessage(this);
                }
            } catch (Throwable throwable) {
                Object var10_18 = null;
                this.unknownFields = unknownFields.build();
                this.makeExtensionsImmutable();
                throw throwable;
            }
            this.makeExtensionsImmutable();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return internal_static_google_protobuf_FieldDescriptorProto_descriptor;
        }

        @Override
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return internal_static_google_protobuf_FieldDescriptorProto_fieldAccessorTable.ensureFieldAccessorsInitialized(FieldDescriptorProto.class, Builder.class);
        }

        public Parser<FieldDescriptorProto> getParserForType() {
            return PARSER;
        }

        @Override
        public boolean hasName() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override
        public String getName() {
            Object ref = this.name_;
            if (ref instanceof String) {
                return (String)ref;
            }
            ByteString bs = (ByteString)ref;
            String s2 = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.name_ = s2;
            }
            return s2;
        }

        @Override
        public ByteString getNameBytes() {
            Object ref = this.name_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.name_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        @Override
        public boolean hasNumber() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override
        public int getNumber() {
            return this.number_;
        }

        @Override
        public boolean hasLabel() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override
        public Label getLabel() {
            return this.label_;
        }

        @Override
        public boolean hasType() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override
        public Type getType() {
            return this.type_;
        }

        @Override
        public boolean hasTypeName() {
            return (this.bitField0_ & 0x10) == 16;
        }

        @Override
        public String getTypeName() {
            Object ref = this.typeName_;
            if (ref instanceof String) {
                return (String)ref;
            }
            ByteString bs = (ByteString)ref;
            String s2 = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.typeName_ = s2;
            }
            return s2;
        }

        @Override
        public ByteString getTypeNameBytes() {
            Object ref = this.typeName_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.typeName_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        @Override
        public boolean hasExtendee() {
            return (this.bitField0_ & 0x20) == 32;
        }

        @Override
        public String getExtendee() {
            Object ref = this.extendee_;
            if (ref instanceof String) {
                return (String)ref;
            }
            ByteString bs = (ByteString)ref;
            String s2 = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.extendee_ = s2;
            }
            return s2;
        }

        @Override
        public ByteString getExtendeeBytes() {
            Object ref = this.extendee_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.extendee_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        @Override
        public boolean hasDefaultValue() {
            return (this.bitField0_ & 0x40) == 64;
        }

        @Override
        public String getDefaultValue() {
            Object ref = this.defaultValue_;
            if (ref instanceof String) {
                return (String)ref;
            }
            ByteString bs = (ByteString)ref;
            String s2 = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.defaultValue_ = s2;
            }
            return s2;
        }

        @Override
        public ByteString getDefaultValueBytes() {
            Object ref = this.defaultValue_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.defaultValue_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        @Override
        public boolean hasOneofIndex() {
            return (this.bitField0_ & 0x80) == 128;
        }

        @Override
        public int getOneofIndex() {
            return this.oneofIndex_;
        }

        @Override
        public boolean hasOptions() {
            return (this.bitField0_ & 0x100) == 256;
        }

        @Override
        public FieldOptions getOptions() {
            return this.options_;
        }

        @Override
        public FieldOptionsOrBuilder getOptionsOrBuilder() {
            return this.options_;
        }

        private void initFields() {
            this.name_ = "";
            this.number_ = 0;
            this.label_ = Label.LABEL_OPTIONAL;
            this.type_ = Type.TYPE_DOUBLE;
            this.typeName_ = "";
            this.extendee_ = "";
            this.defaultValue_ = "";
            this.oneofIndex_ = 0;
            this.options_ = FieldOptions.getDefaultInstance();
        }

        @Override
        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            if (this.hasOptions() && !this.getOptions().isInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        @Override
        public void writeTo(CodedOutputStream output) throws IOException {
            this.getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                output.writeBytes(1, this.getNameBytes());
            }
            if ((this.bitField0_ & 0x20) == 32) {
                output.writeBytes(2, this.getExtendeeBytes());
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeInt32(3, this.number_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeEnum(4, this.label_.getNumber());
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeEnum(5, this.type_.getNumber());
            }
            if ((this.bitField0_ & 0x10) == 16) {
                output.writeBytes(6, this.getTypeNameBytes());
            }
            if ((this.bitField0_ & 0x40) == 64) {
                output.writeBytes(7, this.getDefaultValueBytes());
            }
            if ((this.bitField0_ & 0x100) == 256) {
                output.writeMessage(8, this.options_);
            }
            if ((this.bitField0_ & 0x80) == 128) {
                output.writeInt32(9, this.oneofIndex_);
            }
            this.getUnknownFields().writeTo(output);
        }

        @Override
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            size = 0;
            if ((this.bitField0_ & 1) == 1) {
                size += CodedOutputStream.computeBytesSize(1, this.getNameBytes());
            }
            if ((this.bitField0_ & 0x20) == 32) {
                size += CodedOutputStream.computeBytesSize(2, this.getExtendeeBytes());
            }
            if ((this.bitField0_ & 2) == 2) {
                size += CodedOutputStream.computeInt32Size(3, this.number_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size += CodedOutputStream.computeEnumSize(4, this.label_.getNumber());
            }
            if ((this.bitField0_ & 8) == 8) {
                size += CodedOutputStream.computeEnumSize(5, this.type_.getNumber());
            }
            if ((this.bitField0_ & 0x10) == 16) {
                size += CodedOutputStream.computeBytesSize(6, this.getTypeNameBytes());
            }
            if ((this.bitField0_ & 0x40) == 64) {
                size += CodedOutputStream.computeBytesSize(7, this.getDefaultValueBytes());
            }
            if ((this.bitField0_ & 0x100) == 256) {
                size += CodedOutputStream.computeMessageSize(8, this.options_);
            }
            if ((this.bitField0_ & 0x80) == 128) {
                size += CodedOutputStream.computeInt32Size(9, this.oneofIndex_);
            }
            this.memoizedSerializedSize = size += this.getUnknownFields().getSerializedSize();
            return size;
        }

        @Override
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static FieldDescriptorProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static FieldDescriptorProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static FieldDescriptorProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static FieldDescriptorProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static FieldDescriptorProto parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static FieldDescriptorProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static FieldDescriptorProto parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static FieldDescriptorProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static FieldDescriptorProto parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static FieldDescriptorProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override
        public Builder newBuilderForType() {
            return FieldDescriptorProto.newBuilder();
        }

        public static Builder newBuilder(FieldDescriptorProto prototype) {
            return FieldDescriptorProto.newBuilder().mergeFrom(prototype);
        }

        @Override
        public Builder toBuilder() {
            return FieldDescriptorProto.newBuilder(this);
        }

        @Override
        protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        static {
            PARSER = new AbstractParser<FieldDescriptorProto>(){

                @Override
                public FieldDescriptorProto parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                    return new FieldDescriptorProto(input, extensionRegistry);
                }
            };
            defaultInstance = new FieldDescriptorProto(true);
            defaultInstance.initFields();
        }

        /*
         * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
         */
        public static final class Builder
        extends GeneratedMessage.Builder<Builder>
        implements FieldDescriptorProtoOrBuilder {
            private int bitField0_;
            private Object name_ = "";
            private int number_;
            private Label label_ = Label.LABEL_OPTIONAL;
            private Type type_ = Type.TYPE_DOUBLE;
            private Object typeName_ = "";
            private Object extendee_ = "";
            private Object defaultValue_ = "";
            private int oneofIndex_;
            private FieldOptions options_ = FieldOptions.getDefaultInstance();
            private SingleFieldBuilder<FieldOptions, FieldOptions.Builder, FieldOptionsOrBuilder> optionsBuilder_;

            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_google_protobuf_FieldDescriptorProto_descriptor;
            }

            @Override
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_google_protobuf_FieldDescriptorProto_fieldAccessorTable.ensureFieldAccessorsInitialized(FieldDescriptorProto.class, Builder.class);
            }

            private Builder() {
                this.maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessage.BuilderParent parent) {
                super(parent);
                this.maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                if (GeneratedMessage.alwaysUseFieldBuilders) {
                    this.getOptionsFieldBuilder();
                }
            }

            private static Builder create() {
                return new Builder();
            }

            @Override
            public Builder clear() {
                super.clear();
                this.name_ = "";
                this.bitField0_ &= 0xFFFFFFFE;
                this.number_ = 0;
                this.bitField0_ &= 0xFFFFFFFD;
                this.label_ = Label.LABEL_OPTIONAL;
                this.bitField0_ &= 0xFFFFFFFB;
                this.type_ = Type.TYPE_DOUBLE;
                this.bitField0_ &= 0xFFFFFFF7;
                this.typeName_ = "";
                this.bitField0_ &= 0xFFFFFFEF;
                this.extendee_ = "";
                this.bitField0_ &= 0xFFFFFFDF;
                this.defaultValue_ = "";
                this.bitField0_ &= 0xFFFFFFBF;
                this.oneofIndex_ = 0;
                this.bitField0_ &= 0xFFFFFF7F;
                if (this.optionsBuilder_ == null) {
                    this.options_ = FieldOptions.getDefaultInstance();
                } else {
                    this.optionsBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFEFF;
                return this;
            }

            @Override
            public Builder clone() {
                return Builder.create().mergeFrom(this.buildPartial());
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return internal_static_google_protobuf_FieldDescriptorProto_descriptor;
            }

            @Override
            public FieldDescriptorProto getDefaultInstanceForType() {
                return FieldDescriptorProto.getDefaultInstance();
            }

            @Override
            public FieldDescriptorProto build() {
                FieldDescriptorProto result = this.buildPartial();
                if (!result.isInitialized()) {
                    throw Builder.newUninitializedMessageException(result);
                }
                return result;
            }

            @Override
            public FieldDescriptorProto buildPartial() {
                FieldDescriptorProto result = new FieldDescriptorProto(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ |= 1;
                }
                result.name_ = this.name_;
                if ((from_bitField0_ & 2) == 2) {
                    to_bitField0_ |= 2;
                }
                result.number_ = this.number_;
                if ((from_bitField0_ & 4) == 4) {
                    to_bitField0_ |= 4;
                }
                result.label_ = this.label_;
                if ((from_bitField0_ & 8) == 8) {
                    to_bitField0_ |= 8;
                }
                result.type_ = this.type_;
                if ((from_bitField0_ & 0x10) == 16) {
                    to_bitField0_ |= 0x10;
                }
                result.typeName_ = this.typeName_;
                if ((from_bitField0_ & 0x20) == 32) {
                    to_bitField0_ |= 0x20;
                }
                result.extendee_ = this.extendee_;
                if ((from_bitField0_ & 0x40) == 64) {
                    to_bitField0_ |= 0x40;
                }
                result.defaultValue_ = this.defaultValue_;
                if ((from_bitField0_ & 0x80) == 128) {
                    to_bitField0_ |= 0x80;
                }
                result.oneofIndex_ = this.oneofIndex_;
                if ((from_bitField0_ & 0x100) == 256) {
                    to_bitField0_ |= 0x100;
                }
                if (this.optionsBuilder_ == null) {
                    result.options_ = this.options_;
                } else {
                    result.options_ = this.optionsBuilder_.build();
                }
                result.bitField0_ = to_bitField0_;
                this.onBuilt();
                return result;
            }

            @Override
            public Builder mergeFrom(Message other) {
                if (other instanceof FieldDescriptorProto) {
                    return this.mergeFrom((FieldDescriptorProto)other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(FieldDescriptorProto other) {
                if (other == FieldDescriptorProto.getDefaultInstance()) {
                    return this;
                }
                if (other.hasName()) {
                    this.bitField0_ |= 1;
                    this.name_ = other.name_;
                    this.onChanged();
                }
                if (other.hasNumber()) {
                    this.setNumber(other.getNumber());
                }
                if (other.hasLabel()) {
                    this.setLabel(other.getLabel());
                }
                if (other.hasType()) {
                    this.setType(other.getType());
                }
                if (other.hasTypeName()) {
                    this.bitField0_ |= 0x10;
                    this.typeName_ = other.typeName_;
                    this.onChanged();
                }
                if (other.hasExtendee()) {
                    this.bitField0_ |= 0x20;
                    this.extendee_ = other.extendee_;
                    this.onChanged();
                }
                if (other.hasDefaultValue()) {
                    this.bitField0_ |= 0x40;
                    this.defaultValue_ = other.defaultValue_;
                    this.onChanged();
                }
                if (other.hasOneofIndex()) {
                    this.setOneofIndex(other.getOneofIndex());
                }
                if (other.hasOptions()) {
                    this.mergeOptions(other.getOptions());
                }
                this.mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            @Override
            public final boolean isInitialized() {
                return !this.hasOptions() || this.getOptions().isInitialized();
            }

            /*
             * Enabled force condition propagation
             * Lifted jumps to return sites
             */
            @Override
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                FieldDescriptorProto parsedMessage = null;
                try {
                    try {
                        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
                    } catch (InvalidProtocolBufferException e) {
                        parsedMessage = (FieldDescriptorProto)e.getUnfinishedMessage();
                        throw e;
                    }
                    Object var6_4 = null;
                    if (parsedMessage == null) return this;
                    this.mergeFrom(parsedMessage);
                    return this;
                } catch (Throwable throwable) {
                    Object var6_5 = null;
                    if (parsedMessage == null) throw throwable;
                    this.mergeFrom(parsedMessage);
                    throw throwable;
                }
            }

            @Override
            public boolean hasName() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override
            public String getName() {
                Object ref = this.name_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString)ref;
                    String s2 = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.name_ = s2;
                    }
                    return s2;
                }
                return (String)ref;
            }

            @Override
            public ByteString getNameBytes() {
                Object ref = this.name_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String)ref);
                    this.name_ = b;
                    return b;
                }
                return (ByteString)ref;
            }

            public Builder setName(String value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 1;
                this.name_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearName() {
                this.bitField0_ &= 0xFFFFFFFE;
                this.name_ = FieldDescriptorProto.getDefaultInstance().getName();
                this.onChanged();
                return this;
            }

            public Builder setNameBytes(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 1;
                this.name_ = value;
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasNumber() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override
            public int getNumber() {
                return this.number_;
            }

            public Builder setNumber(int value) {
                this.bitField0_ |= 2;
                this.number_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearNumber() {
                this.bitField0_ &= 0xFFFFFFFD;
                this.number_ = 0;
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasLabel() {
                return (this.bitField0_ & 4) == 4;
            }

            @Override
            public Label getLabel() {
                return this.label_;
            }

            public Builder setLabel(Label value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 4;
                this.label_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearLabel() {
                this.bitField0_ &= 0xFFFFFFFB;
                this.label_ = Label.LABEL_OPTIONAL;
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasType() {
                return (this.bitField0_ & 8) == 8;
            }

            @Override
            public Type getType() {
                return this.type_;
            }

            public Builder setType(Type value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 8;
                this.type_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearType() {
                this.bitField0_ &= 0xFFFFFFF7;
                this.type_ = Type.TYPE_DOUBLE;
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasTypeName() {
                return (this.bitField0_ & 0x10) == 16;
            }

            @Override
            public String getTypeName() {
                Object ref = this.typeName_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString)ref;
                    String s2 = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.typeName_ = s2;
                    }
                    return s2;
                }
                return (String)ref;
            }

            @Override
            public ByteString getTypeNameBytes() {
                Object ref = this.typeName_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String)ref);
                    this.typeName_ = b;
                    return b;
                }
                return (ByteString)ref;
            }

            public Builder setTypeName(String value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 0x10;
                this.typeName_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearTypeName() {
                this.bitField0_ &= 0xFFFFFFEF;
                this.typeName_ = FieldDescriptorProto.getDefaultInstance().getTypeName();
                this.onChanged();
                return this;
            }

            public Builder setTypeNameBytes(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 0x10;
                this.typeName_ = value;
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasExtendee() {
                return (this.bitField0_ & 0x20) == 32;
            }

            @Override
            public String getExtendee() {
                Object ref = this.extendee_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString)ref;
                    String s2 = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.extendee_ = s2;
                    }
                    return s2;
                }
                return (String)ref;
            }

            @Override
            public ByteString getExtendeeBytes() {
                Object ref = this.extendee_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String)ref);
                    this.extendee_ = b;
                    return b;
                }
                return (ByteString)ref;
            }

            public Builder setExtendee(String value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 0x20;
                this.extendee_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearExtendee() {
                this.bitField0_ &= 0xFFFFFFDF;
                this.extendee_ = FieldDescriptorProto.getDefaultInstance().getExtendee();
                this.onChanged();
                return this;
            }

            public Builder setExtendeeBytes(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 0x20;
                this.extendee_ = value;
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasDefaultValue() {
                return (this.bitField0_ & 0x40) == 64;
            }

            @Override
            public String getDefaultValue() {
                Object ref = this.defaultValue_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString)ref;
                    String s2 = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.defaultValue_ = s2;
                    }
                    return s2;
                }
                return (String)ref;
            }

            @Override
            public ByteString getDefaultValueBytes() {
                Object ref = this.defaultValue_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String)ref);
                    this.defaultValue_ = b;
                    return b;
                }
                return (ByteString)ref;
            }

            public Builder setDefaultValue(String value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 0x40;
                this.defaultValue_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearDefaultValue() {
                this.bitField0_ &= 0xFFFFFFBF;
                this.defaultValue_ = FieldDescriptorProto.getDefaultInstance().getDefaultValue();
                this.onChanged();
                return this;
            }

            public Builder setDefaultValueBytes(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 0x40;
                this.defaultValue_ = value;
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasOneofIndex() {
                return (this.bitField0_ & 0x80) == 128;
            }

            @Override
            public int getOneofIndex() {
                return this.oneofIndex_;
            }

            public Builder setOneofIndex(int value) {
                this.bitField0_ |= 0x80;
                this.oneofIndex_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearOneofIndex() {
                this.bitField0_ &= 0xFFFFFF7F;
                this.oneofIndex_ = 0;
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasOptions() {
                return (this.bitField0_ & 0x100) == 256;
            }

            @Override
            public FieldOptions getOptions() {
                if (this.optionsBuilder_ == null) {
                    return this.options_;
                }
                return this.optionsBuilder_.getMessage();
            }

            public Builder setOptions(FieldOptions value) {
                if (this.optionsBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.options_ = value;
                    this.onChanged();
                } else {
                    this.optionsBuilder_.setMessage(value);
                }
                this.bitField0_ |= 0x100;
                return this;
            }

            public Builder setOptions(FieldOptions.Builder builderForValue) {
                if (this.optionsBuilder_ == null) {
                    this.options_ = builderForValue.build();
                    this.onChanged();
                } else {
                    this.optionsBuilder_.setMessage(builderForValue.build());
                }
                this.bitField0_ |= 0x100;
                return this;
            }

            public Builder mergeOptions(FieldOptions value) {
                if (this.optionsBuilder_ == null) {
                    this.options_ = (this.bitField0_ & 0x100) == 256 && this.options_ != FieldOptions.getDefaultInstance() ? FieldOptions.newBuilder(this.options_).mergeFrom(value).buildPartial() : value;
                    this.onChanged();
                } else {
                    this.optionsBuilder_.mergeFrom(value);
                }
                this.bitField0_ |= 0x100;
                return this;
            }

            public Builder clearOptions() {
                if (this.optionsBuilder_ == null) {
                    this.options_ = FieldOptions.getDefaultInstance();
                    this.onChanged();
                } else {
                    this.optionsBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFEFF;
                return this;
            }

            public FieldOptions.Builder getOptionsBuilder() {
                this.bitField0_ |= 0x100;
                this.onChanged();
                return this.getOptionsFieldBuilder().getBuilder();
            }

            @Override
            public FieldOptionsOrBuilder getOptionsOrBuilder() {
                if (this.optionsBuilder_ != null) {
                    return this.optionsBuilder_.getMessageOrBuilder();
                }
                return this.options_;
            }

            private SingleFieldBuilder<FieldOptions, FieldOptions.Builder, FieldOptionsOrBuilder> getOptionsFieldBuilder() {
                if (this.optionsBuilder_ == null) {
                    this.optionsBuilder_ = new SingleFieldBuilder(this.getOptions(), this.getParentForChildren(), this.isClean());
                    this.options_ = null;
                }
                return this.optionsBuilder_;
            }
        }

        /*
         * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
         */
        public static enum Label implements ProtocolMessageEnum
        {
            LABEL_OPTIONAL(0, 1),
            LABEL_REQUIRED(1, 2),
            LABEL_REPEATED(2, 3);

            public static final int LABEL_OPTIONAL_VALUE = 1;
            public static final int LABEL_REQUIRED_VALUE = 2;
            public static final int LABEL_REPEATED_VALUE = 3;
            private static Internal.EnumLiteMap<Label> internalValueMap;
            private static final Label[] VALUES;
            private final int index;
            private final int value;

            @Override
            public final int getNumber() {
                return this.value;
            }

            public static Label valueOf(int value) {
                switch (value) {
                    case 1: {
                        return LABEL_OPTIONAL;
                    }
                    case 2: {
                        return LABEL_REQUIRED;
                    }
                    case 3: {
                        return LABEL_REPEATED;
                    }
                }
                return null;
            }

            public static Internal.EnumLiteMap<Label> internalGetValueMap() {
                return internalValueMap;
            }

            @Override
            public final Descriptors.EnumValueDescriptor getValueDescriptor() {
                return Label.getDescriptor().getValues().get(this.index);
            }

            @Override
            public final Descriptors.EnumDescriptor getDescriptorForType() {
                return Label.getDescriptor();
            }

            public static final Descriptors.EnumDescriptor getDescriptor() {
                return FieldDescriptorProto.getDescriptor().getEnumTypes().get(1);
            }

            public static Label valueOf(Descriptors.EnumValueDescriptor desc) {
                if (desc.getType() != Label.getDescriptor()) {
                    throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
                }
                return VALUES[desc.getIndex()];
            }

            private Label(int index, int value) {
                this.index = index;
                this.value = value;
            }

            static {
                internalValueMap = new Internal.EnumLiteMap<Label>(){

                    @Override
                    public Label findValueByNumber(int number) {
                        return Label.valueOf(number);
                    }
                };
                VALUES = Label.values();
            }
        }

        /*
         * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
         */
        public static enum Type implements ProtocolMessageEnum
        {
            TYPE_DOUBLE(0, 1),
            TYPE_FLOAT(1, 2),
            TYPE_INT64(2, 3),
            TYPE_UINT64(3, 4),
            TYPE_INT32(4, 5),
            TYPE_FIXED64(5, 6),
            TYPE_FIXED32(6, 7),
            TYPE_BOOL(7, 8),
            TYPE_STRING(8, 9),
            TYPE_GROUP(9, 10),
            TYPE_MESSAGE(10, 11),
            TYPE_BYTES(11, 12),
            TYPE_UINT32(12, 13),
            TYPE_ENUM(13, 14),
            TYPE_SFIXED32(14, 15),
            TYPE_SFIXED64(15, 16),
            TYPE_SINT32(16, 17),
            TYPE_SINT64(17, 18);

            public static final int TYPE_DOUBLE_VALUE = 1;
            public static final int TYPE_FLOAT_VALUE = 2;
            public static final int TYPE_INT64_VALUE = 3;
            public static final int TYPE_UINT64_VALUE = 4;
            public static final int TYPE_INT32_VALUE = 5;
            public static final int TYPE_FIXED64_VALUE = 6;
            public static final int TYPE_FIXED32_VALUE = 7;
            public static final int TYPE_BOOL_VALUE = 8;
            public static final int TYPE_STRING_VALUE = 9;
            public static final int TYPE_GROUP_VALUE = 10;
            public static final int TYPE_MESSAGE_VALUE = 11;
            public static final int TYPE_BYTES_VALUE = 12;
            public static final int TYPE_UINT32_VALUE = 13;
            public static final int TYPE_ENUM_VALUE = 14;
            public static final int TYPE_SFIXED32_VALUE = 15;
            public static final int TYPE_SFIXED64_VALUE = 16;
            public static final int TYPE_SINT32_VALUE = 17;
            public static final int TYPE_SINT64_VALUE = 18;
            private static Internal.EnumLiteMap<Type> internalValueMap;
            private static final Type[] VALUES;
            private final int index;
            private final int value;

            @Override
            public final int getNumber() {
                return this.value;
            }

            public static Type valueOf(int value) {
                switch (value) {
                    case 1: {
                        return TYPE_DOUBLE;
                    }
                    case 2: {
                        return TYPE_FLOAT;
                    }
                    case 3: {
                        return TYPE_INT64;
                    }
                    case 4: {
                        return TYPE_UINT64;
                    }
                    case 5: {
                        return TYPE_INT32;
                    }
                    case 6: {
                        return TYPE_FIXED64;
                    }
                    case 7: {
                        return TYPE_FIXED32;
                    }
                    case 8: {
                        return TYPE_BOOL;
                    }
                    case 9: {
                        return TYPE_STRING;
                    }
                    case 10: {
                        return TYPE_GROUP;
                    }
                    case 11: {
                        return TYPE_MESSAGE;
                    }
                    case 12: {
                        return TYPE_BYTES;
                    }
                    case 13: {
                        return TYPE_UINT32;
                    }
                    case 14: {
                        return TYPE_ENUM;
                    }
                    case 15: {
                        return TYPE_SFIXED32;
                    }
                    case 16: {
                        return TYPE_SFIXED64;
                    }
                    case 17: {
                        return TYPE_SINT32;
                    }
                    case 18: {
                        return TYPE_SINT64;
                    }
                }
                return null;
            }

            public static Internal.EnumLiteMap<Type> internalGetValueMap() {
                return internalValueMap;
            }

            @Override
            public final Descriptors.EnumValueDescriptor getValueDescriptor() {
                return Type.getDescriptor().getValues().get(this.index);
            }

            @Override
            public final Descriptors.EnumDescriptor getDescriptorForType() {
                return Type.getDescriptor();
            }

            public static final Descriptors.EnumDescriptor getDescriptor() {
                return FieldDescriptorProto.getDescriptor().getEnumTypes().get(0);
            }

            public static Type valueOf(Descriptors.EnumValueDescriptor desc) {
                if (desc.getType() != Type.getDescriptor()) {
                    throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
                }
                return VALUES[desc.getIndex()];
            }

            private Type(int index, int value) {
                this.index = index;
                this.value = value;
            }

            static {
                internalValueMap = new Internal.EnumLiteMap<Type>(){

                    @Override
                    public Type findValueByNumber(int number) {
                        return Type.valueOf(number);
                    }
                };
                VALUES = Type.values();
            }
        }
    }

    public static interface FieldDescriptorProtoOrBuilder
    extends MessageOrBuilder {
        public boolean hasName();

        public String getName();

        public ByteString getNameBytes();

        public boolean hasNumber();

        public int getNumber();

        public boolean hasLabel();

        public FieldDescriptorProto.Label getLabel();

        public boolean hasType();

        public FieldDescriptorProto.Type getType();

        public boolean hasTypeName();

        public String getTypeName();

        public ByteString getTypeNameBytes();

        public boolean hasExtendee();

        public String getExtendee();

        public ByteString getExtendeeBytes();

        public boolean hasDefaultValue();

        public String getDefaultValue();

        public ByteString getDefaultValueBytes();

        public boolean hasOneofIndex();

        public int getOneofIndex();

        public boolean hasOptions();

        public FieldOptions getOptions();

        public FieldOptionsOrBuilder getOptionsOrBuilder();
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static final class DescriptorProto
    extends GeneratedMessage
    implements DescriptorProtoOrBuilder {
        private static final DescriptorProto defaultInstance;
        private final UnknownFieldSet unknownFields;
        public static Parser<DescriptorProto> PARSER;
        private int bitField0_;
        public static final int NAME_FIELD_NUMBER = 1;
        private Object name_;
        public static final int FIELD_FIELD_NUMBER = 2;
        private List<FieldDescriptorProto> field_;
        public static final int EXTENSION_FIELD_NUMBER = 6;
        private List<FieldDescriptorProto> extension_;
        public static final int NESTED_TYPE_FIELD_NUMBER = 3;
        private List<DescriptorProto> nestedType_;
        public static final int ENUM_TYPE_FIELD_NUMBER = 4;
        private List<EnumDescriptorProto> enumType_;
        public static final int EXTENSION_RANGE_FIELD_NUMBER = 5;
        private List<ExtensionRange> extensionRange_;
        public static final int ONEOF_DECL_FIELD_NUMBER = 8;
        private List<OneofDescriptorProto> oneofDecl_;
        public static final int OPTIONS_FIELD_NUMBER = 7;
        private MessageOptions options_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private static final long serialVersionUID = 0L;

        private DescriptorProto(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte)-1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private DescriptorProto(boolean noInit) {
            this.memoizedIsInitialized = (byte)-1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static DescriptorProto getDefaultInstance() {
            return defaultInstance;
        }

        @Override
        public DescriptorProto getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        private DescriptorProto(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            UnknownFieldSet.Builder unknownFields;
            int mutable_bitField0_;
            block37: {
                this.memoizedIsInitialized = (byte)-1;
                this.memoizedSerializedSize = -1;
                this.initFields();
                mutable_bitField0_ = 0;
                unknownFields = UnknownFieldSet.newBuilder();
                try {
                    try {
                        boolean done = false;
                        block16: while (!done) {
                            int tag = input.readTag();
                            switch (tag) {
                                case 0: {
                                    done = true;
                                    continue block16;
                                }
                                default: {
                                    if (this.parseUnknownField(input, unknownFields, extensionRegistry, tag)) continue block16;
                                    done = true;
                                    continue block16;
                                }
                                case 10: {
                                    ByteString bs = input.readBytes();
                                    this.bitField0_ |= 1;
                                    this.name_ = bs;
                                    continue block16;
                                }
                                case 18: {
                                    if ((mutable_bitField0_ & 2) != 2) {
                                        this.field_ = new ArrayList<FieldDescriptorProto>();
                                        mutable_bitField0_ |= 2;
                                    }
                                    this.field_.add(input.readMessage(FieldDescriptorProto.PARSER, extensionRegistry));
                                    continue block16;
                                }
                                case 26: {
                                    if ((mutable_bitField0_ & 8) != 8) {
                                        this.nestedType_ = new ArrayList<DescriptorProto>();
                                        mutable_bitField0_ |= 8;
                                    }
                                    this.nestedType_.add(input.readMessage(PARSER, extensionRegistry));
                                    continue block16;
                                }
                                case 34: {
                                    if ((mutable_bitField0_ & 0x10) != 16) {
                                        this.enumType_ = new ArrayList<EnumDescriptorProto>();
                                        mutable_bitField0_ |= 0x10;
                                    }
                                    this.enumType_.add(input.readMessage(EnumDescriptorProto.PARSER, extensionRegistry));
                                    continue block16;
                                }
                                case 42: {
                                    if ((mutable_bitField0_ & 0x20) != 32) {
                                        this.extensionRange_ = new ArrayList<ExtensionRange>();
                                        mutable_bitField0_ |= 0x20;
                                    }
                                    this.extensionRange_.add(input.readMessage(ExtensionRange.PARSER, extensionRegistry));
                                    continue block16;
                                }
                                case 50: {
                                    if ((mutable_bitField0_ & 4) != 4) {
                                        this.extension_ = new ArrayList<FieldDescriptorProto>();
                                        mutable_bitField0_ |= 4;
                                    }
                                    this.extension_.add(input.readMessage(FieldDescriptorProto.PARSER, extensionRegistry));
                                    continue block16;
                                }
                                case 58: {
                                    MessageOptions.Builder subBuilder = null;
                                    if ((this.bitField0_ & 2) == 2) {
                                        subBuilder = this.options_.toBuilder();
                                    }
                                    this.options_ = input.readMessage(MessageOptions.PARSER, extensionRegistry);
                                    if (subBuilder != null) {
                                        subBuilder.mergeFrom(this.options_);
                                        this.options_ = subBuilder.buildPartial();
                                    }
                                    this.bitField0_ |= 2;
                                    continue block16;
                                }
                                case 66: 
                            }
                            if ((mutable_bitField0_ & 0x40) != 64) {
                                this.oneofDecl_ = new ArrayList<OneofDescriptorProto>();
                                mutable_bitField0_ |= 0x40;
                            }
                            this.oneofDecl_.add(input.readMessage(OneofDescriptorProto.PARSER, extensionRegistry));
                        }
                        Object var9_10 = null;
                        if ((mutable_bitField0_ & 2) != 2) break block37;
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e) {
                        throw new InvalidProtocolBufferException(e.getMessage()).setUnfinishedMessage(this);
                    }
                } catch (Throwable throwable) {
                    Object var9_11 = null;
                    if ((mutable_bitField0_ & 2) == 2) {
                        this.field_ = Collections.unmodifiableList(this.field_);
                    }
                    if ((mutable_bitField0_ & 8) == 8) {
                        this.nestedType_ = Collections.unmodifiableList(this.nestedType_);
                    }
                    if ((mutable_bitField0_ & 0x10) == 16) {
                        this.enumType_ = Collections.unmodifiableList(this.enumType_);
                    }
                    if ((mutable_bitField0_ & 0x20) == 32) {
                        this.extensionRange_ = Collections.unmodifiableList(this.extensionRange_);
                    }
                    if ((mutable_bitField0_ & 4) == 4) {
                        this.extension_ = Collections.unmodifiableList(this.extension_);
                    }
                    if ((mutable_bitField0_ & 0x40) == 64) {
                        this.oneofDecl_ = Collections.unmodifiableList(this.oneofDecl_);
                    }
                    this.unknownFields = unknownFields.build();
                    this.makeExtensionsImmutable();
                    throw throwable;
                }
                this.field_ = Collections.unmodifiableList(this.field_);
            }
            if ((mutable_bitField0_ & 8) == 8) {
                this.nestedType_ = Collections.unmodifiableList(this.nestedType_);
            }
            if ((mutable_bitField0_ & 0x10) == 16) {
                this.enumType_ = Collections.unmodifiableList(this.enumType_);
            }
            if ((mutable_bitField0_ & 0x20) == 32) {
                this.extensionRange_ = Collections.unmodifiableList(this.extensionRange_);
            }
            if ((mutable_bitField0_ & 4) == 4) {
                this.extension_ = Collections.unmodifiableList(this.extension_);
            }
            if ((mutable_bitField0_ & 0x40) == 64) {
                this.oneofDecl_ = Collections.unmodifiableList(this.oneofDecl_);
            }
            this.unknownFields = unknownFields.build();
            this.makeExtensionsImmutable();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return internal_static_google_protobuf_DescriptorProto_descriptor;
        }

        @Override
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return internal_static_google_protobuf_DescriptorProto_fieldAccessorTable.ensureFieldAccessorsInitialized(DescriptorProto.class, Builder.class);
        }

        public Parser<DescriptorProto> getParserForType() {
            return PARSER;
        }

        @Override
        public boolean hasName() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override
        public String getName() {
            Object ref = this.name_;
            if (ref instanceof String) {
                return (String)ref;
            }
            ByteString bs = (ByteString)ref;
            String s2 = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.name_ = s2;
            }
            return s2;
        }

        @Override
        public ByteString getNameBytes() {
            Object ref = this.name_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.name_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        @Override
        public List<FieldDescriptorProto> getFieldList() {
            return this.field_;
        }

        @Override
        public List<? extends FieldDescriptorProtoOrBuilder> getFieldOrBuilderList() {
            return this.field_;
        }

        @Override
        public int getFieldCount() {
            return this.field_.size();
        }

        @Override
        public FieldDescriptorProto getField(int index) {
            return this.field_.get(index);
        }

        @Override
        public FieldDescriptorProtoOrBuilder getFieldOrBuilder(int index) {
            return this.field_.get(index);
        }

        @Override
        public List<FieldDescriptorProto> getExtensionList() {
            return this.extension_;
        }

        @Override
        public List<? extends FieldDescriptorProtoOrBuilder> getExtensionOrBuilderList() {
            return this.extension_;
        }

        @Override
        public int getExtensionCount() {
            return this.extension_.size();
        }

        @Override
        public FieldDescriptorProto getExtension(int index) {
            return this.extension_.get(index);
        }

        @Override
        public FieldDescriptorProtoOrBuilder getExtensionOrBuilder(int index) {
            return this.extension_.get(index);
        }

        @Override
        public List<DescriptorProto> getNestedTypeList() {
            return this.nestedType_;
        }

        @Override
        public List<? extends DescriptorProtoOrBuilder> getNestedTypeOrBuilderList() {
            return this.nestedType_;
        }

        @Override
        public int getNestedTypeCount() {
            return this.nestedType_.size();
        }

        @Override
        public DescriptorProto getNestedType(int index) {
            return this.nestedType_.get(index);
        }

        @Override
        public DescriptorProtoOrBuilder getNestedTypeOrBuilder(int index) {
            return this.nestedType_.get(index);
        }

        @Override
        public List<EnumDescriptorProto> getEnumTypeList() {
            return this.enumType_;
        }

        @Override
        public List<? extends EnumDescriptorProtoOrBuilder> getEnumTypeOrBuilderList() {
            return this.enumType_;
        }

        @Override
        public int getEnumTypeCount() {
            return this.enumType_.size();
        }

        @Override
        public EnumDescriptorProto getEnumType(int index) {
            return this.enumType_.get(index);
        }

        @Override
        public EnumDescriptorProtoOrBuilder getEnumTypeOrBuilder(int index) {
            return this.enumType_.get(index);
        }

        @Override
        public List<ExtensionRange> getExtensionRangeList() {
            return this.extensionRange_;
        }

        @Override
        public List<? extends ExtensionRangeOrBuilder> getExtensionRangeOrBuilderList() {
            return this.extensionRange_;
        }

        @Override
        public int getExtensionRangeCount() {
            return this.extensionRange_.size();
        }

        @Override
        public ExtensionRange getExtensionRange(int index) {
            return this.extensionRange_.get(index);
        }

        @Override
        public ExtensionRangeOrBuilder getExtensionRangeOrBuilder(int index) {
            return this.extensionRange_.get(index);
        }

        @Override
        public List<OneofDescriptorProto> getOneofDeclList() {
            return this.oneofDecl_;
        }

        @Override
        public List<? extends OneofDescriptorProtoOrBuilder> getOneofDeclOrBuilderList() {
            return this.oneofDecl_;
        }

        @Override
        public int getOneofDeclCount() {
            return this.oneofDecl_.size();
        }

        @Override
        public OneofDescriptorProto getOneofDecl(int index) {
            return this.oneofDecl_.get(index);
        }

        @Override
        public OneofDescriptorProtoOrBuilder getOneofDeclOrBuilder(int index) {
            return this.oneofDecl_.get(index);
        }

        @Override
        public boolean hasOptions() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override
        public MessageOptions getOptions() {
            return this.options_;
        }

        @Override
        public MessageOptionsOrBuilder getOptionsOrBuilder() {
            return this.options_;
        }

        private void initFields() {
            this.name_ = "";
            this.field_ = Collections.emptyList();
            this.extension_ = Collections.emptyList();
            this.nestedType_ = Collections.emptyList();
            this.enumType_ = Collections.emptyList();
            this.extensionRange_ = Collections.emptyList();
            this.oneofDecl_ = Collections.emptyList();
            this.options_ = MessageOptions.getDefaultInstance();
        }

        @Override
        public final boolean isInitialized() {
            int i;
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            for (i = 0; i < this.getFieldCount(); ++i) {
                if (this.getField(i).isInitialized()) continue;
                this.memoizedIsInitialized = 0;
                return false;
            }
            for (i = 0; i < this.getExtensionCount(); ++i) {
                if (this.getExtension(i).isInitialized()) continue;
                this.memoizedIsInitialized = 0;
                return false;
            }
            for (i = 0; i < this.getNestedTypeCount(); ++i) {
                if (this.getNestedType(i).isInitialized()) continue;
                this.memoizedIsInitialized = 0;
                return false;
            }
            for (i = 0; i < this.getEnumTypeCount(); ++i) {
                if (this.getEnumType(i).isInitialized()) continue;
                this.memoizedIsInitialized = 0;
                return false;
            }
            if (this.hasOptions() && !this.getOptions().isInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        @Override
        public void writeTo(CodedOutputStream output) throws IOException {
            int i;
            this.getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                output.writeBytes(1, this.getNameBytes());
            }
            for (i = 0; i < this.field_.size(); ++i) {
                output.writeMessage(2, this.field_.get(i));
            }
            for (i = 0; i < this.nestedType_.size(); ++i) {
                output.writeMessage(3, this.nestedType_.get(i));
            }
            for (i = 0; i < this.enumType_.size(); ++i) {
                output.writeMessage(4, this.enumType_.get(i));
            }
            for (i = 0; i < this.extensionRange_.size(); ++i) {
                output.writeMessage(5, this.extensionRange_.get(i));
            }
            for (i = 0; i < this.extension_.size(); ++i) {
                output.writeMessage(6, this.extension_.get(i));
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeMessage(7, this.options_);
            }
            for (i = 0; i < this.oneofDecl_.size(); ++i) {
                output.writeMessage(8, this.oneofDecl_.get(i));
            }
            this.getUnknownFields().writeTo(output);
        }

        @Override
        public int getSerializedSize() {
            int i;
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            size = 0;
            if ((this.bitField0_ & 1) == 1) {
                size += CodedOutputStream.computeBytesSize(1, this.getNameBytes());
            }
            for (i = 0; i < this.field_.size(); ++i) {
                size += CodedOutputStream.computeMessageSize(2, this.field_.get(i));
            }
            for (i = 0; i < this.nestedType_.size(); ++i) {
                size += CodedOutputStream.computeMessageSize(3, this.nestedType_.get(i));
            }
            for (i = 0; i < this.enumType_.size(); ++i) {
                size += CodedOutputStream.computeMessageSize(4, this.enumType_.get(i));
            }
            for (i = 0; i < this.extensionRange_.size(); ++i) {
                size += CodedOutputStream.computeMessageSize(5, this.extensionRange_.get(i));
            }
            for (i = 0; i < this.extension_.size(); ++i) {
                size += CodedOutputStream.computeMessageSize(6, this.extension_.get(i));
            }
            if ((this.bitField0_ & 2) == 2) {
                size += CodedOutputStream.computeMessageSize(7, this.options_);
            }
            for (i = 0; i < this.oneofDecl_.size(); ++i) {
                size += CodedOutputStream.computeMessageSize(8, this.oneofDecl_.get(i));
            }
            this.memoizedSerializedSize = size += this.getUnknownFields().getSerializedSize();
            return size;
        }

        @Override
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static DescriptorProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static DescriptorProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static DescriptorProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static DescriptorProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static DescriptorProto parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static DescriptorProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static DescriptorProto parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static DescriptorProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static DescriptorProto parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static DescriptorProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override
        public Builder newBuilderForType() {
            return DescriptorProto.newBuilder();
        }

        public static Builder newBuilder(DescriptorProto prototype) {
            return DescriptorProto.newBuilder().mergeFrom(prototype);
        }

        @Override
        public Builder toBuilder() {
            return DescriptorProto.newBuilder(this);
        }

        @Override
        protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        static {
            PARSER = new AbstractParser<DescriptorProto>(){

                @Override
                public DescriptorProto parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                    return new DescriptorProto(input, extensionRegistry);
                }
            };
            defaultInstance = new DescriptorProto(true);
            defaultInstance.initFields();
        }

        /*
         * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
         */
        public static final class Builder
        extends GeneratedMessage.Builder<Builder>
        implements DescriptorProtoOrBuilder {
            private int bitField0_;
            private Object name_ = "";
            private List<FieldDescriptorProto> field_ = Collections.emptyList();
            private RepeatedFieldBuilder<FieldDescriptorProto, FieldDescriptorProto.Builder, FieldDescriptorProtoOrBuilder> fieldBuilder_;
            private List<FieldDescriptorProto> extension_ = Collections.emptyList();
            private RepeatedFieldBuilder<FieldDescriptorProto, FieldDescriptorProto.Builder, FieldDescriptorProtoOrBuilder> extensionBuilder_;
            private List<DescriptorProto> nestedType_ = Collections.emptyList();
            private RepeatedFieldBuilder<DescriptorProto, Builder, DescriptorProtoOrBuilder> nestedTypeBuilder_;
            private List<EnumDescriptorProto> enumType_ = Collections.emptyList();
            private RepeatedFieldBuilder<EnumDescriptorProto, EnumDescriptorProto.Builder, EnumDescriptorProtoOrBuilder> enumTypeBuilder_;
            private List<ExtensionRange> extensionRange_ = Collections.emptyList();
            private RepeatedFieldBuilder<ExtensionRange, ExtensionRange.Builder, ExtensionRangeOrBuilder> extensionRangeBuilder_;
            private List<OneofDescriptorProto> oneofDecl_ = Collections.emptyList();
            private RepeatedFieldBuilder<OneofDescriptorProto, OneofDescriptorProto.Builder, OneofDescriptorProtoOrBuilder> oneofDeclBuilder_;
            private MessageOptions options_ = MessageOptions.getDefaultInstance();
            private SingleFieldBuilder<MessageOptions, MessageOptions.Builder, MessageOptionsOrBuilder> optionsBuilder_;

            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_google_protobuf_DescriptorProto_descriptor;
            }

            @Override
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_google_protobuf_DescriptorProto_fieldAccessorTable.ensureFieldAccessorsInitialized(DescriptorProto.class, Builder.class);
            }

            private Builder() {
                this.maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessage.BuilderParent parent) {
                super(parent);
                this.maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                if (GeneratedMessage.alwaysUseFieldBuilders) {
                    this.getFieldFieldBuilder();
                    this.getExtensionFieldBuilder();
                    this.getNestedTypeFieldBuilder();
                    this.getEnumTypeFieldBuilder();
                    this.getExtensionRangeFieldBuilder();
                    this.getOneofDeclFieldBuilder();
                    this.getOptionsFieldBuilder();
                }
            }

            private static Builder create() {
                return new Builder();
            }

            @Override
            public Builder clear() {
                super.clear();
                this.name_ = "";
                this.bitField0_ &= 0xFFFFFFFE;
                if (this.fieldBuilder_ == null) {
                    this.field_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFFFFD;
                } else {
                    this.fieldBuilder_.clear();
                }
                if (this.extensionBuilder_ == null) {
                    this.extension_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFFFFB;
                } else {
                    this.extensionBuilder_.clear();
                }
                if (this.nestedTypeBuilder_ == null) {
                    this.nestedType_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFFFF7;
                } else {
                    this.nestedTypeBuilder_.clear();
                }
                if (this.enumTypeBuilder_ == null) {
                    this.enumType_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFFFEF;
                } else {
                    this.enumTypeBuilder_.clear();
                }
                if (this.extensionRangeBuilder_ == null) {
                    this.extensionRange_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFFFDF;
                } else {
                    this.extensionRangeBuilder_.clear();
                }
                if (this.oneofDeclBuilder_ == null) {
                    this.oneofDecl_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFFFBF;
                } else {
                    this.oneofDeclBuilder_.clear();
                }
                if (this.optionsBuilder_ == null) {
                    this.options_ = MessageOptions.getDefaultInstance();
                } else {
                    this.optionsBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFF7F;
                return this;
            }

            @Override
            public Builder clone() {
                return Builder.create().mergeFrom(this.buildPartial());
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return internal_static_google_protobuf_DescriptorProto_descriptor;
            }

            @Override
            public DescriptorProto getDefaultInstanceForType() {
                return DescriptorProto.getDefaultInstance();
            }

            @Override
            public DescriptorProto build() {
                DescriptorProto result = this.buildPartial();
                if (!result.isInitialized()) {
                    throw Builder.newUninitializedMessageException(result);
                }
                return result;
            }

            @Override
            public DescriptorProto buildPartial() {
                DescriptorProto result = new DescriptorProto(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ |= 1;
                }
                result.name_ = this.name_;
                if (this.fieldBuilder_ == null) {
                    if ((this.bitField0_ & 2) == 2) {
                        this.field_ = Collections.unmodifiableList(this.field_);
                        this.bitField0_ &= 0xFFFFFFFD;
                    }
                    result.field_ = this.field_;
                } else {
                    result.field_ = this.fieldBuilder_.build();
                }
                if (this.extensionBuilder_ == null) {
                    if ((this.bitField0_ & 4) == 4) {
                        this.extension_ = Collections.unmodifiableList(this.extension_);
                        this.bitField0_ &= 0xFFFFFFFB;
                    }
                    result.extension_ = this.extension_;
                } else {
                    result.extension_ = this.extensionBuilder_.build();
                }
                if (this.nestedTypeBuilder_ == null) {
                    if ((this.bitField0_ & 8) == 8) {
                        this.nestedType_ = Collections.unmodifiableList(this.nestedType_);
                        this.bitField0_ &= 0xFFFFFFF7;
                    }
                    result.nestedType_ = this.nestedType_;
                } else {
                    result.nestedType_ = this.nestedTypeBuilder_.build();
                }
                if (this.enumTypeBuilder_ == null) {
                    if ((this.bitField0_ & 0x10) == 16) {
                        this.enumType_ = Collections.unmodifiableList(this.enumType_);
                        this.bitField0_ &= 0xFFFFFFEF;
                    }
                    result.enumType_ = this.enumType_;
                } else {
                    result.enumType_ = this.enumTypeBuilder_.build();
                }
                if (this.extensionRangeBuilder_ == null) {
                    if ((this.bitField0_ & 0x20) == 32) {
                        this.extensionRange_ = Collections.unmodifiableList(this.extensionRange_);
                        this.bitField0_ &= 0xFFFFFFDF;
                    }
                    result.extensionRange_ = this.extensionRange_;
                } else {
                    result.extensionRange_ = this.extensionRangeBuilder_.build();
                }
                if (this.oneofDeclBuilder_ == null) {
                    if ((this.bitField0_ & 0x40) == 64) {
                        this.oneofDecl_ = Collections.unmodifiableList(this.oneofDecl_);
                        this.bitField0_ &= 0xFFFFFFBF;
                    }
                    result.oneofDecl_ = this.oneofDecl_;
                } else {
                    result.oneofDecl_ = this.oneofDeclBuilder_.build();
                }
                if ((from_bitField0_ & 0x80) == 128) {
                    to_bitField0_ |= 2;
                }
                if (this.optionsBuilder_ == null) {
                    result.options_ = this.options_;
                } else {
                    result.options_ = this.optionsBuilder_.build();
                }
                result.bitField0_ = to_bitField0_;
                this.onBuilt();
                return result;
            }

            @Override
            public Builder mergeFrom(Message other) {
                if (other instanceof DescriptorProto) {
                    return this.mergeFrom((DescriptorProto)other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(DescriptorProto other) {
                if (other == DescriptorProto.getDefaultInstance()) {
                    return this;
                }
                if (other.hasName()) {
                    this.bitField0_ |= 1;
                    this.name_ = other.name_;
                    this.onChanged();
                }
                if (this.fieldBuilder_ == null) {
                    if (!other.field_.isEmpty()) {
                        if (this.field_.isEmpty()) {
                            this.field_ = other.field_;
                            this.bitField0_ &= 0xFFFFFFFD;
                        } else {
                            this.ensureFieldIsMutable();
                            this.field_.addAll(other.field_);
                        }
                        this.onChanged();
                    }
                } else if (!other.field_.isEmpty()) {
                    if (this.fieldBuilder_.isEmpty()) {
                        this.fieldBuilder_.dispose();
                        this.fieldBuilder_ = null;
                        this.field_ = other.field_;
                        this.bitField0_ &= 0xFFFFFFFD;
                        this.fieldBuilder_ = GeneratedMessage.alwaysUseFieldBuilders ? this.getFieldFieldBuilder() : null;
                    } else {
                        this.fieldBuilder_.addAllMessages(other.field_);
                    }
                }
                if (this.extensionBuilder_ == null) {
                    if (!other.extension_.isEmpty()) {
                        if (this.extension_.isEmpty()) {
                            this.extension_ = other.extension_;
                            this.bitField0_ &= 0xFFFFFFFB;
                        } else {
                            this.ensureExtensionIsMutable();
                            this.extension_.addAll(other.extension_);
                        }
                        this.onChanged();
                    }
                } else if (!other.extension_.isEmpty()) {
                    if (this.extensionBuilder_.isEmpty()) {
                        this.extensionBuilder_.dispose();
                        this.extensionBuilder_ = null;
                        this.extension_ = other.extension_;
                        this.bitField0_ &= 0xFFFFFFFB;
                        this.extensionBuilder_ = GeneratedMessage.alwaysUseFieldBuilders ? this.getExtensionFieldBuilder() : null;
                    } else {
                        this.extensionBuilder_.addAllMessages(other.extension_);
                    }
                }
                if (this.nestedTypeBuilder_ == null) {
                    if (!other.nestedType_.isEmpty()) {
                        if (this.nestedType_.isEmpty()) {
                            this.nestedType_ = other.nestedType_;
                            this.bitField0_ &= 0xFFFFFFF7;
                        } else {
                            this.ensureNestedTypeIsMutable();
                            this.nestedType_.addAll(other.nestedType_);
                        }
                        this.onChanged();
                    }
                } else if (!other.nestedType_.isEmpty()) {
                    if (this.nestedTypeBuilder_.isEmpty()) {
                        this.nestedTypeBuilder_.dispose();
                        this.nestedTypeBuilder_ = null;
                        this.nestedType_ = other.nestedType_;
                        this.bitField0_ &= 0xFFFFFFF7;
                        this.nestedTypeBuilder_ = GeneratedMessage.alwaysUseFieldBuilders ? this.getNestedTypeFieldBuilder() : null;
                    } else {
                        this.nestedTypeBuilder_.addAllMessages(other.nestedType_);
                    }
                }
                if (this.enumTypeBuilder_ == null) {
                    if (!other.enumType_.isEmpty()) {
                        if (this.enumType_.isEmpty()) {
                            this.enumType_ = other.enumType_;
                            this.bitField0_ &= 0xFFFFFFEF;
                        } else {
                            this.ensureEnumTypeIsMutable();
                            this.enumType_.addAll(other.enumType_);
                        }
                        this.onChanged();
                    }
                } else if (!other.enumType_.isEmpty()) {
                    if (this.enumTypeBuilder_.isEmpty()) {
                        this.enumTypeBuilder_.dispose();
                        this.enumTypeBuilder_ = null;
                        this.enumType_ = other.enumType_;
                        this.bitField0_ &= 0xFFFFFFEF;
                        this.enumTypeBuilder_ = GeneratedMessage.alwaysUseFieldBuilders ? this.getEnumTypeFieldBuilder() : null;
                    } else {
                        this.enumTypeBuilder_.addAllMessages(other.enumType_);
                    }
                }
                if (this.extensionRangeBuilder_ == null) {
                    if (!other.extensionRange_.isEmpty()) {
                        if (this.extensionRange_.isEmpty()) {
                            this.extensionRange_ = other.extensionRange_;
                            this.bitField0_ &= 0xFFFFFFDF;
                        } else {
                            this.ensureExtensionRangeIsMutable();
                            this.extensionRange_.addAll(other.extensionRange_);
                        }
                        this.onChanged();
                    }
                } else if (!other.extensionRange_.isEmpty()) {
                    if (this.extensionRangeBuilder_.isEmpty()) {
                        this.extensionRangeBuilder_.dispose();
                        this.extensionRangeBuilder_ = null;
                        this.extensionRange_ = other.extensionRange_;
                        this.bitField0_ &= 0xFFFFFFDF;
                        this.extensionRangeBuilder_ = GeneratedMessage.alwaysUseFieldBuilders ? this.getExtensionRangeFieldBuilder() : null;
                    } else {
                        this.extensionRangeBuilder_.addAllMessages(other.extensionRange_);
                    }
                }
                if (this.oneofDeclBuilder_ == null) {
                    if (!other.oneofDecl_.isEmpty()) {
                        if (this.oneofDecl_.isEmpty()) {
                            this.oneofDecl_ = other.oneofDecl_;
                            this.bitField0_ &= 0xFFFFFFBF;
                        } else {
                            this.ensureOneofDeclIsMutable();
                            this.oneofDecl_.addAll(other.oneofDecl_);
                        }
                        this.onChanged();
                    }
                } else if (!other.oneofDecl_.isEmpty()) {
                    if (this.oneofDeclBuilder_.isEmpty()) {
                        this.oneofDeclBuilder_.dispose();
                        this.oneofDeclBuilder_ = null;
                        this.oneofDecl_ = other.oneofDecl_;
                        this.bitField0_ &= 0xFFFFFFBF;
                        this.oneofDeclBuilder_ = GeneratedMessage.alwaysUseFieldBuilders ? this.getOneofDeclFieldBuilder() : null;
                    } else {
                        this.oneofDeclBuilder_.addAllMessages(other.oneofDecl_);
                    }
                }
                if (other.hasOptions()) {
                    this.mergeOptions(other.getOptions());
                }
                this.mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            @Override
            public final boolean isInitialized() {
                int i;
                for (i = 0; i < this.getFieldCount(); ++i) {
                    if (this.getField(i).isInitialized()) continue;
                    return false;
                }
                for (i = 0; i < this.getExtensionCount(); ++i) {
                    if (this.getExtension(i).isInitialized()) continue;
                    return false;
                }
                for (i = 0; i < this.getNestedTypeCount(); ++i) {
                    if (this.getNestedType(i).isInitialized()) continue;
                    return false;
                }
                for (i = 0; i < this.getEnumTypeCount(); ++i) {
                    if (this.getEnumType(i).isInitialized()) continue;
                    return false;
                }
                return !this.hasOptions() || this.getOptions().isInitialized();
            }

            /*
             * Enabled force condition propagation
             * Lifted jumps to return sites
             */
            @Override
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                DescriptorProto parsedMessage = null;
                try {
                    try {
                        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
                    } catch (InvalidProtocolBufferException e) {
                        parsedMessage = (DescriptorProto)e.getUnfinishedMessage();
                        throw e;
                    }
                    Object var6_4 = null;
                    if (parsedMessage == null) return this;
                    this.mergeFrom(parsedMessage);
                    return this;
                } catch (Throwable throwable) {
                    Object var6_5 = null;
                    if (parsedMessage == null) throw throwable;
                    this.mergeFrom(parsedMessage);
                    throw throwable;
                }
            }

            @Override
            public boolean hasName() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override
            public String getName() {
                Object ref = this.name_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString)ref;
                    String s2 = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.name_ = s2;
                    }
                    return s2;
                }
                return (String)ref;
            }

            @Override
            public ByteString getNameBytes() {
                Object ref = this.name_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String)ref);
                    this.name_ = b;
                    return b;
                }
                return (ByteString)ref;
            }

            public Builder setName(String value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 1;
                this.name_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearName() {
                this.bitField0_ &= 0xFFFFFFFE;
                this.name_ = DescriptorProto.getDefaultInstance().getName();
                this.onChanged();
                return this;
            }

            public Builder setNameBytes(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 1;
                this.name_ = value;
                this.onChanged();
                return this;
            }

            private void ensureFieldIsMutable() {
                if ((this.bitField0_ & 2) != 2) {
                    this.field_ = new ArrayList<FieldDescriptorProto>(this.field_);
                    this.bitField0_ |= 2;
                }
            }

            @Override
            public List<FieldDescriptorProto> getFieldList() {
                if (this.fieldBuilder_ == null) {
                    return Collections.unmodifiableList(this.field_);
                }
                return this.fieldBuilder_.getMessageList();
            }

            @Override
            public int getFieldCount() {
                if (this.fieldBuilder_ == null) {
                    return this.field_.size();
                }
                return this.fieldBuilder_.getCount();
            }

            @Override
            public FieldDescriptorProto getField(int index) {
                if (this.fieldBuilder_ == null) {
                    return this.field_.get(index);
                }
                return this.fieldBuilder_.getMessage(index);
            }

            public Builder setField(int index, FieldDescriptorProto value) {
                if (this.fieldBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureFieldIsMutable();
                    this.field_.set(index, value);
                    this.onChanged();
                } else {
                    this.fieldBuilder_.setMessage(index, value);
                }
                return this;
            }

            public Builder setField(int index, FieldDescriptorProto.Builder builderForValue) {
                if (this.fieldBuilder_ == null) {
                    this.ensureFieldIsMutable();
                    this.field_.set(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.fieldBuilder_.setMessage(index, builderForValue.build());
                }
                return this;
            }

            public Builder addField(FieldDescriptorProto value) {
                if (this.fieldBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureFieldIsMutable();
                    this.field_.add(value);
                    this.onChanged();
                } else {
                    this.fieldBuilder_.addMessage(value);
                }
                return this;
            }

            public Builder addField(int index, FieldDescriptorProto value) {
                if (this.fieldBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureFieldIsMutable();
                    this.field_.add(index, value);
                    this.onChanged();
                } else {
                    this.fieldBuilder_.addMessage(index, value);
                }
                return this;
            }

            public Builder addField(FieldDescriptorProto.Builder builderForValue) {
                if (this.fieldBuilder_ == null) {
                    this.ensureFieldIsMutable();
                    this.field_.add(builderForValue.build());
                    this.onChanged();
                } else {
                    this.fieldBuilder_.addMessage(builderForValue.build());
                }
                return this;
            }

            public Builder addField(int index, FieldDescriptorProto.Builder builderForValue) {
                if (this.fieldBuilder_ == null) {
                    this.ensureFieldIsMutable();
                    this.field_.add(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.fieldBuilder_.addMessage(index, builderForValue.build());
                }
                return this;
            }

            public Builder addAllField(Iterable<? extends FieldDescriptorProto> values2) {
                if (this.fieldBuilder_ == null) {
                    this.ensureFieldIsMutable();
                    AbstractMessageLite.Builder.addAll(values2, this.field_);
                    this.onChanged();
                } else {
                    this.fieldBuilder_.addAllMessages(values2);
                }
                return this;
            }

            public Builder clearField() {
                if (this.fieldBuilder_ == null) {
                    this.field_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFFFFD;
                    this.onChanged();
                } else {
                    this.fieldBuilder_.clear();
                }
                return this;
            }

            public Builder removeField(int index) {
                if (this.fieldBuilder_ == null) {
                    this.ensureFieldIsMutable();
                    this.field_.remove(index);
                    this.onChanged();
                } else {
                    this.fieldBuilder_.remove(index);
                }
                return this;
            }

            public FieldDescriptorProto.Builder getFieldBuilder(int index) {
                return this.getFieldFieldBuilder().getBuilder(index);
            }

            @Override
            public FieldDescriptorProtoOrBuilder getFieldOrBuilder(int index) {
                if (this.fieldBuilder_ == null) {
                    return this.field_.get(index);
                }
                return this.fieldBuilder_.getMessageOrBuilder(index);
            }

            @Override
            public List<? extends FieldDescriptorProtoOrBuilder> getFieldOrBuilderList() {
                if (this.fieldBuilder_ != null) {
                    return this.fieldBuilder_.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.field_);
            }

            public FieldDescriptorProto.Builder addFieldBuilder() {
                return this.getFieldFieldBuilder().addBuilder(FieldDescriptorProto.getDefaultInstance());
            }

            public FieldDescriptorProto.Builder addFieldBuilder(int index) {
                return this.getFieldFieldBuilder().addBuilder(index, FieldDescriptorProto.getDefaultInstance());
            }

            public List<FieldDescriptorProto.Builder> getFieldBuilderList() {
                return this.getFieldFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilder<FieldDescriptorProto, FieldDescriptorProto.Builder, FieldDescriptorProtoOrBuilder> getFieldFieldBuilder() {
                if (this.fieldBuilder_ == null) {
                    this.fieldBuilder_ = new RepeatedFieldBuilder(this.field_, (this.bitField0_ & 2) == 2, this.getParentForChildren(), this.isClean());
                    this.field_ = null;
                }
                return this.fieldBuilder_;
            }

            private void ensureExtensionIsMutable() {
                if ((this.bitField0_ & 4) != 4) {
                    this.extension_ = new ArrayList<FieldDescriptorProto>(this.extension_);
                    this.bitField0_ |= 4;
                }
            }

            @Override
            public List<FieldDescriptorProto> getExtensionList() {
                if (this.extensionBuilder_ == null) {
                    return Collections.unmodifiableList(this.extension_);
                }
                return this.extensionBuilder_.getMessageList();
            }

            @Override
            public int getExtensionCount() {
                if (this.extensionBuilder_ == null) {
                    return this.extension_.size();
                }
                return this.extensionBuilder_.getCount();
            }

            @Override
            public FieldDescriptorProto getExtension(int index) {
                if (this.extensionBuilder_ == null) {
                    return this.extension_.get(index);
                }
                return this.extensionBuilder_.getMessage(index);
            }

            public Builder setExtension(int index, FieldDescriptorProto value) {
                if (this.extensionBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureExtensionIsMutable();
                    this.extension_.set(index, value);
                    this.onChanged();
                } else {
                    this.extensionBuilder_.setMessage(index, value);
                }
                return this;
            }

            public Builder setExtension(int index, FieldDescriptorProto.Builder builderForValue) {
                if (this.extensionBuilder_ == null) {
                    this.ensureExtensionIsMutable();
                    this.extension_.set(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.extensionBuilder_.setMessage(index, builderForValue.build());
                }
                return this;
            }

            public Builder addExtension(FieldDescriptorProto value) {
                if (this.extensionBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureExtensionIsMutable();
                    this.extension_.add(value);
                    this.onChanged();
                } else {
                    this.extensionBuilder_.addMessage(value);
                }
                return this;
            }

            public Builder addExtension(int index, FieldDescriptorProto value) {
                if (this.extensionBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureExtensionIsMutable();
                    this.extension_.add(index, value);
                    this.onChanged();
                } else {
                    this.extensionBuilder_.addMessage(index, value);
                }
                return this;
            }

            public Builder addExtension(FieldDescriptorProto.Builder builderForValue) {
                if (this.extensionBuilder_ == null) {
                    this.ensureExtensionIsMutable();
                    this.extension_.add(builderForValue.build());
                    this.onChanged();
                } else {
                    this.extensionBuilder_.addMessage(builderForValue.build());
                }
                return this;
            }

            public Builder addExtension(int index, FieldDescriptorProto.Builder builderForValue) {
                if (this.extensionBuilder_ == null) {
                    this.ensureExtensionIsMutable();
                    this.extension_.add(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.extensionBuilder_.addMessage(index, builderForValue.build());
                }
                return this;
            }

            public Builder addAllExtension(Iterable<? extends FieldDescriptorProto> values2) {
                if (this.extensionBuilder_ == null) {
                    this.ensureExtensionIsMutable();
                    AbstractMessageLite.Builder.addAll(values2, this.extension_);
                    this.onChanged();
                } else {
                    this.extensionBuilder_.addAllMessages(values2);
                }
                return this;
            }

            public Builder clearExtension() {
                if (this.extensionBuilder_ == null) {
                    this.extension_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFFFFB;
                    this.onChanged();
                } else {
                    this.extensionBuilder_.clear();
                }
                return this;
            }

            public Builder removeExtension(int index) {
                if (this.extensionBuilder_ == null) {
                    this.ensureExtensionIsMutable();
                    this.extension_.remove(index);
                    this.onChanged();
                } else {
                    this.extensionBuilder_.remove(index);
                }
                return this;
            }

            public FieldDescriptorProto.Builder getExtensionBuilder(int index) {
                return this.getExtensionFieldBuilder().getBuilder(index);
            }

            @Override
            public FieldDescriptorProtoOrBuilder getExtensionOrBuilder(int index) {
                if (this.extensionBuilder_ == null) {
                    return this.extension_.get(index);
                }
                return this.extensionBuilder_.getMessageOrBuilder(index);
            }

            @Override
            public List<? extends FieldDescriptorProtoOrBuilder> getExtensionOrBuilderList() {
                if (this.extensionBuilder_ != null) {
                    return this.extensionBuilder_.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.extension_);
            }

            public FieldDescriptorProto.Builder addExtensionBuilder() {
                return this.getExtensionFieldBuilder().addBuilder(FieldDescriptorProto.getDefaultInstance());
            }

            public FieldDescriptorProto.Builder addExtensionBuilder(int index) {
                return this.getExtensionFieldBuilder().addBuilder(index, FieldDescriptorProto.getDefaultInstance());
            }

            public List<FieldDescriptorProto.Builder> getExtensionBuilderList() {
                return this.getExtensionFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilder<FieldDescriptorProto, FieldDescriptorProto.Builder, FieldDescriptorProtoOrBuilder> getExtensionFieldBuilder() {
                if (this.extensionBuilder_ == null) {
                    this.extensionBuilder_ = new RepeatedFieldBuilder(this.extension_, (this.bitField0_ & 4) == 4, this.getParentForChildren(), this.isClean());
                    this.extension_ = null;
                }
                return this.extensionBuilder_;
            }

            private void ensureNestedTypeIsMutable() {
                if ((this.bitField0_ & 8) != 8) {
                    this.nestedType_ = new ArrayList<DescriptorProto>(this.nestedType_);
                    this.bitField0_ |= 8;
                }
            }

            @Override
            public List<DescriptorProto> getNestedTypeList() {
                if (this.nestedTypeBuilder_ == null) {
                    return Collections.unmodifiableList(this.nestedType_);
                }
                return this.nestedTypeBuilder_.getMessageList();
            }

            @Override
            public int getNestedTypeCount() {
                if (this.nestedTypeBuilder_ == null) {
                    return this.nestedType_.size();
                }
                return this.nestedTypeBuilder_.getCount();
            }

            @Override
            public DescriptorProto getNestedType(int index) {
                if (this.nestedTypeBuilder_ == null) {
                    return this.nestedType_.get(index);
                }
                return this.nestedTypeBuilder_.getMessage(index);
            }

            public Builder setNestedType(int index, DescriptorProto value) {
                if (this.nestedTypeBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureNestedTypeIsMutable();
                    this.nestedType_.set(index, value);
                    this.onChanged();
                } else {
                    this.nestedTypeBuilder_.setMessage(index, value);
                }
                return this;
            }

            public Builder setNestedType(int index, Builder builderForValue) {
                if (this.nestedTypeBuilder_ == null) {
                    this.ensureNestedTypeIsMutable();
                    this.nestedType_.set(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.nestedTypeBuilder_.setMessage(index, builderForValue.build());
                }
                return this;
            }

            public Builder addNestedType(DescriptorProto value) {
                if (this.nestedTypeBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureNestedTypeIsMutable();
                    this.nestedType_.add(value);
                    this.onChanged();
                } else {
                    this.nestedTypeBuilder_.addMessage(value);
                }
                return this;
            }

            public Builder addNestedType(int index, DescriptorProto value) {
                if (this.nestedTypeBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureNestedTypeIsMutable();
                    this.nestedType_.add(index, value);
                    this.onChanged();
                } else {
                    this.nestedTypeBuilder_.addMessage(index, value);
                }
                return this;
            }

            public Builder addNestedType(Builder builderForValue) {
                if (this.nestedTypeBuilder_ == null) {
                    this.ensureNestedTypeIsMutable();
                    this.nestedType_.add(builderForValue.build());
                    this.onChanged();
                } else {
                    this.nestedTypeBuilder_.addMessage(builderForValue.build());
                }
                return this;
            }

            public Builder addNestedType(int index, Builder builderForValue) {
                if (this.nestedTypeBuilder_ == null) {
                    this.ensureNestedTypeIsMutable();
                    this.nestedType_.add(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.nestedTypeBuilder_.addMessage(index, builderForValue.build());
                }
                return this;
            }

            public Builder addAllNestedType(Iterable<? extends DescriptorProto> values2) {
                if (this.nestedTypeBuilder_ == null) {
                    this.ensureNestedTypeIsMutable();
                    AbstractMessageLite.Builder.addAll(values2, this.nestedType_);
                    this.onChanged();
                } else {
                    this.nestedTypeBuilder_.addAllMessages(values2);
                }
                return this;
            }

            public Builder clearNestedType() {
                if (this.nestedTypeBuilder_ == null) {
                    this.nestedType_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFFFF7;
                    this.onChanged();
                } else {
                    this.nestedTypeBuilder_.clear();
                }
                return this;
            }

            public Builder removeNestedType(int index) {
                if (this.nestedTypeBuilder_ == null) {
                    this.ensureNestedTypeIsMutable();
                    this.nestedType_.remove(index);
                    this.onChanged();
                } else {
                    this.nestedTypeBuilder_.remove(index);
                }
                return this;
            }

            public Builder getNestedTypeBuilder(int index) {
                return this.getNestedTypeFieldBuilder().getBuilder(index);
            }

            @Override
            public DescriptorProtoOrBuilder getNestedTypeOrBuilder(int index) {
                if (this.nestedTypeBuilder_ == null) {
                    return this.nestedType_.get(index);
                }
                return this.nestedTypeBuilder_.getMessageOrBuilder(index);
            }

            @Override
            public List<? extends DescriptorProtoOrBuilder> getNestedTypeOrBuilderList() {
                if (this.nestedTypeBuilder_ != null) {
                    return this.nestedTypeBuilder_.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.nestedType_);
            }

            public Builder addNestedTypeBuilder() {
                return this.getNestedTypeFieldBuilder().addBuilder(DescriptorProto.getDefaultInstance());
            }

            public Builder addNestedTypeBuilder(int index) {
                return this.getNestedTypeFieldBuilder().addBuilder(index, DescriptorProto.getDefaultInstance());
            }

            public List<Builder> getNestedTypeBuilderList() {
                return this.getNestedTypeFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilder<DescriptorProto, Builder, DescriptorProtoOrBuilder> getNestedTypeFieldBuilder() {
                if (this.nestedTypeBuilder_ == null) {
                    this.nestedTypeBuilder_ = new RepeatedFieldBuilder(this.nestedType_, (this.bitField0_ & 8) == 8, this.getParentForChildren(), this.isClean());
                    this.nestedType_ = null;
                }
                return this.nestedTypeBuilder_;
            }

            private void ensureEnumTypeIsMutable() {
                if ((this.bitField0_ & 0x10) != 16) {
                    this.enumType_ = new ArrayList<EnumDescriptorProto>(this.enumType_);
                    this.bitField0_ |= 0x10;
                }
            }

            @Override
            public List<EnumDescriptorProto> getEnumTypeList() {
                if (this.enumTypeBuilder_ == null) {
                    return Collections.unmodifiableList(this.enumType_);
                }
                return this.enumTypeBuilder_.getMessageList();
            }

            @Override
            public int getEnumTypeCount() {
                if (this.enumTypeBuilder_ == null) {
                    return this.enumType_.size();
                }
                return this.enumTypeBuilder_.getCount();
            }

            @Override
            public EnumDescriptorProto getEnumType(int index) {
                if (this.enumTypeBuilder_ == null) {
                    return this.enumType_.get(index);
                }
                return this.enumTypeBuilder_.getMessage(index);
            }

            public Builder setEnumType(int index, EnumDescriptorProto value) {
                if (this.enumTypeBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureEnumTypeIsMutable();
                    this.enumType_.set(index, value);
                    this.onChanged();
                } else {
                    this.enumTypeBuilder_.setMessage(index, value);
                }
                return this;
            }

            public Builder setEnumType(int index, EnumDescriptorProto.Builder builderForValue) {
                if (this.enumTypeBuilder_ == null) {
                    this.ensureEnumTypeIsMutable();
                    this.enumType_.set(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.enumTypeBuilder_.setMessage(index, builderForValue.build());
                }
                return this;
            }

            public Builder addEnumType(EnumDescriptorProto value) {
                if (this.enumTypeBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureEnumTypeIsMutable();
                    this.enumType_.add(value);
                    this.onChanged();
                } else {
                    this.enumTypeBuilder_.addMessage(value);
                }
                return this;
            }

            public Builder addEnumType(int index, EnumDescriptorProto value) {
                if (this.enumTypeBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureEnumTypeIsMutable();
                    this.enumType_.add(index, value);
                    this.onChanged();
                } else {
                    this.enumTypeBuilder_.addMessage(index, value);
                }
                return this;
            }

            public Builder addEnumType(EnumDescriptorProto.Builder builderForValue) {
                if (this.enumTypeBuilder_ == null) {
                    this.ensureEnumTypeIsMutable();
                    this.enumType_.add(builderForValue.build());
                    this.onChanged();
                } else {
                    this.enumTypeBuilder_.addMessage(builderForValue.build());
                }
                return this;
            }

            public Builder addEnumType(int index, EnumDescriptorProto.Builder builderForValue) {
                if (this.enumTypeBuilder_ == null) {
                    this.ensureEnumTypeIsMutable();
                    this.enumType_.add(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.enumTypeBuilder_.addMessage(index, builderForValue.build());
                }
                return this;
            }

            public Builder addAllEnumType(Iterable<? extends EnumDescriptorProto> values2) {
                if (this.enumTypeBuilder_ == null) {
                    this.ensureEnumTypeIsMutable();
                    AbstractMessageLite.Builder.addAll(values2, this.enumType_);
                    this.onChanged();
                } else {
                    this.enumTypeBuilder_.addAllMessages(values2);
                }
                return this;
            }

            public Builder clearEnumType() {
                if (this.enumTypeBuilder_ == null) {
                    this.enumType_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFFFEF;
                    this.onChanged();
                } else {
                    this.enumTypeBuilder_.clear();
                }
                return this;
            }

            public Builder removeEnumType(int index) {
                if (this.enumTypeBuilder_ == null) {
                    this.ensureEnumTypeIsMutable();
                    this.enumType_.remove(index);
                    this.onChanged();
                } else {
                    this.enumTypeBuilder_.remove(index);
                }
                return this;
            }

            public EnumDescriptorProto.Builder getEnumTypeBuilder(int index) {
                return this.getEnumTypeFieldBuilder().getBuilder(index);
            }

            @Override
            public EnumDescriptorProtoOrBuilder getEnumTypeOrBuilder(int index) {
                if (this.enumTypeBuilder_ == null) {
                    return this.enumType_.get(index);
                }
                return this.enumTypeBuilder_.getMessageOrBuilder(index);
            }

            @Override
            public List<? extends EnumDescriptorProtoOrBuilder> getEnumTypeOrBuilderList() {
                if (this.enumTypeBuilder_ != null) {
                    return this.enumTypeBuilder_.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.enumType_);
            }

            public EnumDescriptorProto.Builder addEnumTypeBuilder() {
                return this.getEnumTypeFieldBuilder().addBuilder(EnumDescriptorProto.getDefaultInstance());
            }

            public EnumDescriptorProto.Builder addEnumTypeBuilder(int index) {
                return this.getEnumTypeFieldBuilder().addBuilder(index, EnumDescriptorProto.getDefaultInstance());
            }

            public List<EnumDescriptorProto.Builder> getEnumTypeBuilderList() {
                return this.getEnumTypeFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilder<EnumDescriptorProto, EnumDescriptorProto.Builder, EnumDescriptorProtoOrBuilder> getEnumTypeFieldBuilder() {
                if (this.enumTypeBuilder_ == null) {
                    this.enumTypeBuilder_ = new RepeatedFieldBuilder(this.enumType_, (this.bitField0_ & 0x10) == 16, this.getParentForChildren(), this.isClean());
                    this.enumType_ = null;
                }
                return this.enumTypeBuilder_;
            }

            private void ensureExtensionRangeIsMutable() {
                if ((this.bitField0_ & 0x20) != 32) {
                    this.extensionRange_ = new ArrayList<ExtensionRange>(this.extensionRange_);
                    this.bitField0_ |= 0x20;
                }
            }

            @Override
            public List<ExtensionRange> getExtensionRangeList() {
                if (this.extensionRangeBuilder_ == null) {
                    return Collections.unmodifiableList(this.extensionRange_);
                }
                return this.extensionRangeBuilder_.getMessageList();
            }

            @Override
            public int getExtensionRangeCount() {
                if (this.extensionRangeBuilder_ == null) {
                    return this.extensionRange_.size();
                }
                return this.extensionRangeBuilder_.getCount();
            }

            @Override
            public ExtensionRange getExtensionRange(int index) {
                if (this.extensionRangeBuilder_ == null) {
                    return this.extensionRange_.get(index);
                }
                return this.extensionRangeBuilder_.getMessage(index);
            }

            public Builder setExtensionRange(int index, ExtensionRange value) {
                if (this.extensionRangeBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureExtensionRangeIsMutable();
                    this.extensionRange_.set(index, value);
                    this.onChanged();
                } else {
                    this.extensionRangeBuilder_.setMessage(index, value);
                }
                return this;
            }

            public Builder setExtensionRange(int index, ExtensionRange.Builder builderForValue) {
                if (this.extensionRangeBuilder_ == null) {
                    this.ensureExtensionRangeIsMutable();
                    this.extensionRange_.set(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.extensionRangeBuilder_.setMessage(index, builderForValue.build());
                }
                return this;
            }

            public Builder addExtensionRange(ExtensionRange value) {
                if (this.extensionRangeBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureExtensionRangeIsMutable();
                    this.extensionRange_.add(value);
                    this.onChanged();
                } else {
                    this.extensionRangeBuilder_.addMessage(value);
                }
                return this;
            }

            public Builder addExtensionRange(int index, ExtensionRange value) {
                if (this.extensionRangeBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureExtensionRangeIsMutable();
                    this.extensionRange_.add(index, value);
                    this.onChanged();
                } else {
                    this.extensionRangeBuilder_.addMessage(index, value);
                }
                return this;
            }

            public Builder addExtensionRange(ExtensionRange.Builder builderForValue) {
                if (this.extensionRangeBuilder_ == null) {
                    this.ensureExtensionRangeIsMutable();
                    this.extensionRange_.add(builderForValue.build());
                    this.onChanged();
                } else {
                    this.extensionRangeBuilder_.addMessage(builderForValue.build());
                }
                return this;
            }

            public Builder addExtensionRange(int index, ExtensionRange.Builder builderForValue) {
                if (this.extensionRangeBuilder_ == null) {
                    this.ensureExtensionRangeIsMutable();
                    this.extensionRange_.add(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.extensionRangeBuilder_.addMessage(index, builderForValue.build());
                }
                return this;
            }

            public Builder addAllExtensionRange(Iterable<? extends ExtensionRange> values2) {
                if (this.extensionRangeBuilder_ == null) {
                    this.ensureExtensionRangeIsMutable();
                    AbstractMessageLite.Builder.addAll(values2, this.extensionRange_);
                    this.onChanged();
                } else {
                    this.extensionRangeBuilder_.addAllMessages(values2);
                }
                return this;
            }

            public Builder clearExtensionRange() {
                if (this.extensionRangeBuilder_ == null) {
                    this.extensionRange_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFFFDF;
                    this.onChanged();
                } else {
                    this.extensionRangeBuilder_.clear();
                }
                return this;
            }

            public Builder removeExtensionRange(int index) {
                if (this.extensionRangeBuilder_ == null) {
                    this.ensureExtensionRangeIsMutable();
                    this.extensionRange_.remove(index);
                    this.onChanged();
                } else {
                    this.extensionRangeBuilder_.remove(index);
                }
                return this;
            }

            public ExtensionRange.Builder getExtensionRangeBuilder(int index) {
                return this.getExtensionRangeFieldBuilder().getBuilder(index);
            }

            @Override
            public ExtensionRangeOrBuilder getExtensionRangeOrBuilder(int index) {
                if (this.extensionRangeBuilder_ == null) {
                    return this.extensionRange_.get(index);
                }
                return this.extensionRangeBuilder_.getMessageOrBuilder(index);
            }

            @Override
            public List<? extends ExtensionRangeOrBuilder> getExtensionRangeOrBuilderList() {
                if (this.extensionRangeBuilder_ != null) {
                    return this.extensionRangeBuilder_.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.extensionRange_);
            }

            public ExtensionRange.Builder addExtensionRangeBuilder() {
                return this.getExtensionRangeFieldBuilder().addBuilder(ExtensionRange.getDefaultInstance());
            }

            public ExtensionRange.Builder addExtensionRangeBuilder(int index) {
                return this.getExtensionRangeFieldBuilder().addBuilder(index, ExtensionRange.getDefaultInstance());
            }

            public List<ExtensionRange.Builder> getExtensionRangeBuilderList() {
                return this.getExtensionRangeFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilder<ExtensionRange, ExtensionRange.Builder, ExtensionRangeOrBuilder> getExtensionRangeFieldBuilder() {
                if (this.extensionRangeBuilder_ == null) {
                    this.extensionRangeBuilder_ = new RepeatedFieldBuilder(this.extensionRange_, (this.bitField0_ & 0x20) == 32, this.getParentForChildren(), this.isClean());
                    this.extensionRange_ = null;
                }
                return this.extensionRangeBuilder_;
            }

            private void ensureOneofDeclIsMutable() {
                if ((this.bitField0_ & 0x40) != 64) {
                    this.oneofDecl_ = new ArrayList<OneofDescriptorProto>(this.oneofDecl_);
                    this.bitField0_ |= 0x40;
                }
            }

            @Override
            public List<OneofDescriptorProto> getOneofDeclList() {
                if (this.oneofDeclBuilder_ == null) {
                    return Collections.unmodifiableList(this.oneofDecl_);
                }
                return this.oneofDeclBuilder_.getMessageList();
            }

            @Override
            public int getOneofDeclCount() {
                if (this.oneofDeclBuilder_ == null) {
                    return this.oneofDecl_.size();
                }
                return this.oneofDeclBuilder_.getCount();
            }

            @Override
            public OneofDescriptorProto getOneofDecl(int index) {
                if (this.oneofDeclBuilder_ == null) {
                    return this.oneofDecl_.get(index);
                }
                return this.oneofDeclBuilder_.getMessage(index);
            }

            public Builder setOneofDecl(int index, OneofDescriptorProto value) {
                if (this.oneofDeclBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureOneofDeclIsMutable();
                    this.oneofDecl_.set(index, value);
                    this.onChanged();
                } else {
                    this.oneofDeclBuilder_.setMessage(index, value);
                }
                return this;
            }

            public Builder setOneofDecl(int index, OneofDescriptorProto.Builder builderForValue) {
                if (this.oneofDeclBuilder_ == null) {
                    this.ensureOneofDeclIsMutable();
                    this.oneofDecl_.set(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.oneofDeclBuilder_.setMessage(index, builderForValue.build());
                }
                return this;
            }

            public Builder addOneofDecl(OneofDescriptorProto value) {
                if (this.oneofDeclBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureOneofDeclIsMutable();
                    this.oneofDecl_.add(value);
                    this.onChanged();
                } else {
                    this.oneofDeclBuilder_.addMessage(value);
                }
                return this;
            }

            public Builder addOneofDecl(int index, OneofDescriptorProto value) {
                if (this.oneofDeclBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureOneofDeclIsMutable();
                    this.oneofDecl_.add(index, value);
                    this.onChanged();
                } else {
                    this.oneofDeclBuilder_.addMessage(index, value);
                }
                return this;
            }

            public Builder addOneofDecl(OneofDescriptorProto.Builder builderForValue) {
                if (this.oneofDeclBuilder_ == null) {
                    this.ensureOneofDeclIsMutable();
                    this.oneofDecl_.add(builderForValue.build());
                    this.onChanged();
                } else {
                    this.oneofDeclBuilder_.addMessage(builderForValue.build());
                }
                return this;
            }

            public Builder addOneofDecl(int index, OneofDescriptorProto.Builder builderForValue) {
                if (this.oneofDeclBuilder_ == null) {
                    this.ensureOneofDeclIsMutable();
                    this.oneofDecl_.add(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.oneofDeclBuilder_.addMessage(index, builderForValue.build());
                }
                return this;
            }

            public Builder addAllOneofDecl(Iterable<? extends OneofDescriptorProto> values2) {
                if (this.oneofDeclBuilder_ == null) {
                    this.ensureOneofDeclIsMutable();
                    AbstractMessageLite.Builder.addAll(values2, this.oneofDecl_);
                    this.onChanged();
                } else {
                    this.oneofDeclBuilder_.addAllMessages(values2);
                }
                return this;
            }

            public Builder clearOneofDecl() {
                if (this.oneofDeclBuilder_ == null) {
                    this.oneofDecl_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFFFBF;
                    this.onChanged();
                } else {
                    this.oneofDeclBuilder_.clear();
                }
                return this;
            }

            public Builder removeOneofDecl(int index) {
                if (this.oneofDeclBuilder_ == null) {
                    this.ensureOneofDeclIsMutable();
                    this.oneofDecl_.remove(index);
                    this.onChanged();
                } else {
                    this.oneofDeclBuilder_.remove(index);
                }
                return this;
            }

            public OneofDescriptorProto.Builder getOneofDeclBuilder(int index) {
                return this.getOneofDeclFieldBuilder().getBuilder(index);
            }

            @Override
            public OneofDescriptorProtoOrBuilder getOneofDeclOrBuilder(int index) {
                if (this.oneofDeclBuilder_ == null) {
                    return this.oneofDecl_.get(index);
                }
                return this.oneofDeclBuilder_.getMessageOrBuilder(index);
            }

            @Override
            public List<? extends OneofDescriptorProtoOrBuilder> getOneofDeclOrBuilderList() {
                if (this.oneofDeclBuilder_ != null) {
                    return this.oneofDeclBuilder_.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.oneofDecl_);
            }

            public OneofDescriptorProto.Builder addOneofDeclBuilder() {
                return this.getOneofDeclFieldBuilder().addBuilder(OneofDescriptorProto.getDefaultInstance());
            }

            public OneofDescriptorProto.Builder addOneofDeclBuilder(int index) {
                return this.getOneofDeclFieldBuilder().addBuilder(index, OneofDescriptorProto.getDefaultInstance());
            }

            public List<OneofDescriptorProto.Builder> getOneofDeclBuilderList() {
                return this.getOneofDeclFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilder<OneofDescriptorProto, OneofDescriptorProto.Builder, OneofDescriptorProtoOrBuilder> getOneofDeclFieldBuilder() {
                if (this.oneofDeclBuilder_ == null) {
                    this.oneofDeclBuilder_ = new RepeatedFieldBuilder(this.oneofDecl_, (this.bitField0_ & 0x40) == 64, this.getParentForChildren(), this.isClean());
                    this.oneofDecl_ = null;
                }
                return this.oneofDeclBuilder_;
            }

            @Override
            public boolean hasOptions() {
                return (this.bitField0_ & 0x80) == 128;
            }

            @Override
            public MessageOptions getOptions() {
                if (this.optionsBuilder_ == null) {
                    return this.options_;
                }
                return this.optionsBuilder_.getMessage();
            }

            public Builder setOptions(MessageOptions value) {
                if (this.optionsBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.options_ = value;
                    this.onChanged();
                } else {
                    this.optionsBuilder_.setMessage(value);
                }
                this.bitField0_ |= 0x80;
                return this;
            }

            public Builder setOptions(MessageOptions.Builder builderForValue) {
                if (this.optionsBuilder_ == null) {
                    this.options_ = builderForValue.build();
                    this.onChanged();
                } else {
                    this.optionsBuilder_.setMessage(builderForValue.build());
                }
                this.bitField0_ |= 0x80;
                return this;
            }

            public Builder mergeOptions(MessageOptions value) {
                if (this.optionsBuilder_ == null) {
                    this.options_ = (this.bitField0_ & 0x80) == 128 && this.options_ != MessageOptions.getDefaultInstance() ? MessageOptions.newBuilder(this.options_).mergeFrom(value).buildPartial() : value;
                    this.onChanged();
                } else {
                    this.optionsBuilder_.mergeFrom(value);
                }
                this.bitField0_ |= 0x80;
                return this;
            }

            public Builder clearOptions() {
                if (this.optionsBuilder_ == null) {
                    this.options_ = MessageOptions.getDefaultInstance();
                    this.onChanged();
                } else {
                    this.optionsBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFF7F;
                return this;
            }

            public MessageOptions.Builder getOptionsBuilder() {
                this.bitField0_ |= 0x80;
                this.onChanged();
                return this.getOptionsFieldBuilder().getBuilder();
            }

            @Override
            public MessageOptionsOrBuilder getOptionsOrBuilder() {
                if (this.optionsBuilder_ != null) {
                    return this.optionsBuilder_.getMessageOrBuilder();
                }
                return this.options_;
            }

            private SingleFieldBuilder<MessageOptions, MessageOptions.Builder, MessageOptionsOrBuilder> getOptionsFieldBuilder() {
                if (this.optionsBuilder_ == null) {
                    this.optionsBuilder_ = new SingleFieldBuilder(this.getOptions(), this.getParentForChildren(), this.isClean());
                    this.options_ = null;
                }
                return this.optionsBuilder_;
            }
        }

        /*
         * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
         */
        public static final class ExtensionRange
        extends GeneratedMessage
        implements ExtensionRangeOrBuilder {
            private static final ExtensionRange defaultInstance;
            private final UnknownFieldSet unknownFields;
            public static Parser<ExtensionRange> PARSER;
            private int bitField0_;
            public static final int START_FIELD_NUMBER = 1;
            private int start_;
            public static final int END_FIELD_NUMBER = 2;
            private int end_;
            private byte memoizedIsInitialized = (byte)-1;
            private int memoizedSerializedSize = -1;
            private static final long serialVersionUID = 0L;

            private ExtensionRange(GeneratedMessage.Builder<?> builder) {
                super(builder);
                this.unknownFields = builder.getUnknownFields();
            }

            private ExtensionRange(boolean noInit) {
                this.unknownFields = UnknownFieldSet.getDefaultInstance();
            }

            public static ExtensionRange getDefaultInstance() {
                return defaultInstance;
            }

            @Override
            public ExtensionRange getDefaultInstanceForType() {
                return defaultInstance;
            }

            @Override
            public final UnknownFieldSet getUnknownFields() {
                return this.unknownFields;
            }

            private ExtensionRange(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                this.initFields();
                boolean mutable_bitField0_ = false;
                UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
                try {
                    try {
                        boolean done = false;
                        block10: while (!done) {
                            int tag = input.readTag();
                            switch (tag) {
                                case 0: {
                                    done = true;
                                    continue block10;
                                }
                                default: {
                                    if (this.parseUnknownField(input, unknownFields, extensionRegistry, tag)) continue block10;
                                    done = true;
                                    continue block10;
                                }
                                case 8: {
                                    this.bitField0_ |= 1;
                                    this.start_ = input.readInt32();
                                    continue block10;
                                }
                                case 16: 
                            }
                            this.bitField0_ |= 2;
                            this.end_ = input.readInt32();
                        }
                        Object var8_9 = null;
                        this.unknownFields = unknownFields.build();
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e) {
                        throw new InvalidProtocolBufferException(e.getMessage()).setUnfinishedMessage(this);
                    }
                } catch (Throwable throwable) {
                    Object var8_10 = null;
                    this.unknownFields = unknownFields.build();
                    this.makeExtensionsImmutable();
                    throw throwable;
                }
                this.makeExtensionsImmutable();
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_google_protobuf_DescriptorProto_ExtensionRange_descriptor;
            }

            @Override
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_google_protobuf_DescriptorProto_ExtensionRange_fieldAccessorTable.ensureFieldAccessorsInitialized(ExtensionRange.class, Builder.class);
            }

            public Parser<ExtensionRange> getParserForType() {
                return PARSER;
            }

            @Override
            public boolean hasStart() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override
            public int getStart() {
                return this.start_;
            }

            @Override
            public boolean hasEnd() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override
            public int getEnd() {
                return this.end_;
            }

            private void initFields() {
                this.start_ = 0;
                this.end_ = 0;
            }

            @Override
            public final boolean isInitialized() {
                byte isInitialized = this.memoizedIsInitialized;
                if (isInitialized == 1) {
                    return true;
                }
                if (isInitialized == 0) {
                    return false;
                }
                this.memoizedIsInitialized = 1;
                return true;
            }

            @Override
            public void writeTo(CodedOutputStream output) throws IOException {
                this.getSerializedSize();
                if ((this.bitField0_ & 1) == 1) {
                    output.writeInt32(1, this.start_);
                }
                if ((this.bitField0_ & 2) == 2) {
                    output.writeInt32(2, this.end_);
                }
                this.getUnknownFields().writeTo(output);
            }

            @Override
            public int getSerializedSize() {
                int size = this.memoizedSerializedSize;
                if (size != -1) {
                    return size;
                }
                size = 0;
                if ((this.bitField0_ & 1) == 1) {
                    size += CodedOutputStream.computeInt32Size(1, this.start_);
                }
                if ((this.bitField0_ & 2) == 2) {
                    size += CodedOutputStream.computeInt32Size(2, this.end_);
                }
                this.memoizedSerializedSize = size += this.getUnknownFields().getSerializedSize();
                return size;
            }

            @Override
            protected Object writeReplace() throws ObjectStreamException {
                return super.writeReplace();
            }

            public static ExtensionRange parseFrom(ByteString data) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(data);
            }

            public static ExtensionRange parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(data, extensionRegistry);
            }

            public static ExtensionRange parseFrom(byte[] data) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(data);
            }

            public static ExtensionRange parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(data, extensionRegistry);
            }

            public static ExtensionRange parseFrom(InputStream input) throws IOException {
                return PARSER.parseFrom(input);
            }

            public static ExtensionRange parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return PARSER.parseFrom(input, extensionRegistry);
            }

            public static ExtensionRange parseDelimitedFrom(InputStream input) throws IOException {
                return PARSER.parseDelimitedFrom(input);
            }

            public static ExtensionRange parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return PARSER.parseDelimitedFrom(input, extensionRegistry);
            }

            public static ExtensionRange parseFrom(CodedInputStream input) throws IOException {
                return PARSER.parseFrom(input);
            }

            public static ExtensionRange parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return PARSER.parseFrom(input, extensionRegistry);
            }

            public static Builder newBuilder() {
                return Builder.create();
            }

            @Override
            public Builder newBuilderForType() {
                return ExtensionRange.newBuilder();
            }

            public static Builder newBuilder(ExtensionRange prototype) {
                return ExtensionRange.newBuilder().mergeFrom(prototype);
            }

            @Override
            public Builder toBuilder() {
                return ExtensionRange.newBuilder(this);
            }

            @Override
            protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
                Builder builder = new Builder(parent);
                return builder;
            }

            static {
                PARSER = new AbstractParser<ExtensionRange>(){

                    @Override
                    public ExtensionRange parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                        return new ExtensionRange(input, extensionRegistry);
                    }
                };
                defaultInstance = new ExtensionRange(true);
                defaultInstance.initFields();
            }

            /*
             * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
             */
            public static final class Builder
            extends GeneratedMessage.Builder<Builder>
            implements ExtensionRangeOrBuilder {
                private int bitField0_;
                private int start_;
                private int end_;

                public static final Descriptors.Descriptor getDescriptor() {
                    return internal_static_google_protobuf_DescriptorProto_ExtensionRange_descriptor;
                }

                @Override
                protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                    return internal_static_google_protobuf_DescriptorProto_ExtensionRange_fieldAccessorTable.ensureFieldAccessorsInitialized(ExtensionRange.class, Builder.class);
                }

                private Builder() {
                    this.maybeForceBuilderInitialization();
                }

                private Builder(GeneratedMessage.BuilderParent parent) {
                    super(parent);
                    this.maybeForceBuilderInitialization();
                }

                private void maybeForceBuilderInitialization() {
                    if (GeneratedMessage.alwaysUseFieldBuilders) {
                        // empty if block
                    }
                }

                private static Builder create() {
                    return new Builder();
                }

                @Override
                public Builder clear() {
                    super.clear();
                    this.start_ = 0;
                    this.bitField0_ &= 0xFFFFFFFE;
                    this.end_ = 0;
                    this.bitField0_ &= 0xFFFFFFFD;
                    return this;
                }

                @Override
                public Builder clone() {
                    return Builder.create().mergeFrom(this.buildPartial());
                }

                @Override
                public Descriptors.Descriptor getDescriptorForType() {
                    return internal_static_google_protobuf_DescriptorProto_ExtensionRange_descriptor;
                }

                @Override
                public ExtensionRange getDefaultInstanceForType() {
                    return ExtensionRange.getDefaultInstance();
                }

                @Override
                public ExtensionRange build() {
                    ExtensionRange result = this.buildPartial();
                    if (!result.isInitialized()) {
                        throw Builder.newUninitializedMessageException(result);
                    }
                    return result;
                }

                @Override
                public ExtensionRange buildPartial() {
                    ExtensionRange result = new ExtensionRange(this);
                    int from_bitField0_ = this.bitField0_;
                    int to_bitField0_ = 0;
                    if ((from_bitField0_ & 1) == 1) {
                        to_bitField0_ |= 1;
                    }
                    result.start_ = this.start_;
                    if ((from_bitField0_ & 2) == 2) {
                        to_bitField0_ |= 2;
                    }
                    result.end_ = this.end_;
                    result.bitField0_ = to_bitField0_;
                    this.onBuilt();
                    return result;
                }

                @Override
                public Builder mergeFrom(Message other) {
                    if (other instanceof ExtensionRange) {
                        return this.mergeFrom((ExtensionRange)other);
                    }
                    super.mergeFrom(other);
                    return this;
                }

                public Builder mergeFrom(ExtensionRange other) {
                    if (other == ExtensionRange.getDefaultInstance()) {
                        return this;
                    }
                    if (other.hasStart()) {
                        this.setStart(other.getStart());
                    }
                    if (other.hasEnd()) {
                        this.setEnd(other.getEnd());
                    }
                    this.mergeUnknownFields(other.getUnknownFields());
                    return this;
                }

                @Override
                public final boolean isInitialized() {
                    return true;
                }

                /*
                 * Enabled force condition propagation
                 * Lifted jumps to return sites
                 */
                @Override
                public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                    ExtensionRange parsedMessage = null;
                    try {
                        try {
                            parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
                        } catch (InvalidProtocolBufferException e) {
                            parsedMessage = (ExtensionRange)e.getUnfinishedMessage();
                            throw e;
                        }
                        Object var6_4 = null;
                        if (parsedMessage == null) return this;
                        this.mergeFrom(parsedMessage);
                        return this;
                    } catch (Throwable throwable) {
                        Object var6_5 = null;
                        if (parsedMessage == null) throw throwable;
                        this.mergeFrom(parsedMessage);
                        throw throwable;
                    }
                }

                @Override
                public boolean hasStart() {
                    return (this.bitField0_ & 1) == 1;
                }

                @Override
                public int getStart() {
                    return this.start_;
                }

                public Builder setStart(int value) {
                    this.bitField0_ |= 1;
                    this.start_ = value;
                    this.onChanged();
                    return this;
                }

                public Builder clearStart() {
                    this.bitField0_ &= 0xFFFFFFFE;
                    this.start_ = 0;
                    this.onChanged();
                    return this;
                }

                @Override
                public boolean hasEnd() {
                    return (this.bitField0_ & 2) == 2;
                }

                @Override
                public int getEnd() {
                    return this.end_;
                }

                public Builder setEnd(int value) {
                    this.bitField0_ |= 2;
                    this.end_ = value;
                    this.onChanged();
                    return this;
                }

                public Builder clearEnd() {
                    this.bitField0_ &= 0xFFFFFFFD;
                    this.end_ = 0;
                    this.onChanged();
                    return this;
                }
            }
        }

        public static interface ExtensionRangeOrBuilder
        extends MessageOrBuilder {
            public boolean hasStart();

            public int getStart();

            public boolean hasEnd();

            public int getEnd();
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static interface DescriptorProtoOrBuilder
    extends MessageOrBuilder {
        public boolean hasName();

        public String getName();

        public ByteString getNameBytes();

        public List<FieldDescriptorProto> getFieldList();

        public FieldDescriptorProto getField(int var1);

        public int getFieldCount();

        public List<? extends FieldDescriptorProtoOrBuilder> getFieldOrBuilderList();

        public FieldDescriptorProtoOrBuilder getFieldOrBuilder(int var1);

        public List<FieldDescriptorProto> getExtensionList();

        public FieldDescriptorProto getExtension(int var1);

        public int getExtensionCount();

        public List<? extends FieldDescriptorProtoOrBuilder> getExtensionOrBuilderList();

        public FieldDescriptorProtoOrBuilder getExtensionOrBuilder(int var1);

        public List<DescriptorProto> getNestedTypeList();

        public DescriptorProto getNestedType(int var1);

        public int getNestedTypeCount();

        public List<? extends DescriptorProtoOrBuilder> getNestedTypeOrBuilderList();

        public DescriptorProtoOrBuilder getNestedTypeOrBuilder(int var1);

        public List<EnumDescriptorProto> getEnumTypeList();

        public EnumDescriptorProto getEnumType(int var1);

        public int getEnumTypeCount();

        public List<? extends EnumDescriptorProtoOrBuilder> getEnumTypeOrBuilderList();

        public EnumDescriptorProtoOrBuilder getEnumTypeOrBuilder(int var1);

        public List<DescriptorProto.ExtensionRange> getExtensionRangeList();

        public DescriptorProto.ExtensionRange getExtensionRange(int var1);

        public int getExtensionRangeCount();

        public List<? extends DescriptorProto.ExtensionRangeOrBuilder> getExtensionRangeOrBuilderList();

        public DescriptorProto.ExtensionRangeOrBuilder getExtensionRangeOrBuilder(int var1);

        public List<OneofDescriptorProto> getOneofDeclList();

        public OneofDescriptorProto getOneofDecl(int var1);

        public int getOneofDeclCount();

        public List<? extends OneofDescriptorProtoOrBuilder> getOneofDeclOrBuilderList();

        public OneofDescriptorProtoOrBuilder getOneofDeclOrBuilder(int var1);

        public boolean hasOptions();

        public MessageOptions getOptions();

        public MessageOptionsOrBuilder getOptionsOrBuilder();
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static final class FileDescriptorProto
    extends GeneratedMessage
    implements FileDescriptorProtoOrBuilder {
        private static final FileDescriptorProto defaultInstance;
        private final UnknownFieldSet unknownFields;
        public static Parser<FileDescriptorProto> PARSER;
        private int bitField0_;
        public static final int NAME_FIELD_NUMBER = 1;
        private Object name_;
        public static final int PACKAGE_FIELD_NUMBER = 2;
        private Object package_;
        public static final int DEPENDENCY_FIELD_NUMBER = 3;
        private LazyStringList dependency_;
        public static final int PUBLIC_DEPENDENCY_FIELD_NUMBER = 10;
        private List<Integer> publicDependency_;
        public static final int WEAK_DEPENDENCY_FIELD_NUMBER = 11;
        private List<Integer> weakDependency_;
        public static final int MESSAGE_TYPE_FIELD_NUMBER = 4;
        private List<DescriptorProto> messageType_;
        public static final int ENUM_TYPE_FIELD_NUMBER = 5;
        private List<EnumDescriptorProto> enumType_;
        public static final int SERVICE_FIELD_NUMBER = 6;
        private List<ServiceDescriptorProto> service_;
        public static final int EXTENSION_FIELD_NUMBER = 7;
        private List<FieldDescriptorProto> extension_;
        public static final int OPTIONS_FIELD_NUMBER = 8;
        private FileOptions options_;
        public static final int SOURCE_CODE_INFO_FIELD_NUMBER = 9;
        private SourceCodeInfo sourceCodeInfo_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private static final long serialVersionUID = 0L;

        private FileDescriptorProto(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte)-1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private FileDescriptorProto(boolean noInit) {
            this.memoizedIsInitialized = (byte)-1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static FileDescriptorProto getDefaultInstance() {
            return defaultInstance;
        }

        @Override
        public FileDescriptorProto getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        private FileDescriptorProto(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            UnknownFieldSet.Builder unknownFields;
            int mutable_bitField0_;
            block51: {
                this.memoizedIsInitialized = (byte)-1;
                this.memoizedSerializedSize = -1;
                this.initFields();
                mutable_bitField0_ = 0;
                unknownFields = UnknownFieldSet.newBuilder();
                try {
                    try {
                        boolean done = false;
                        block21: while (!done) {
                            int limit;
                            int tag = input.readTag();
                            switch (tag) {
                                case 0: {
                                    done = true;
                                    continue block21;
                                }
                                default: {
                                    if (this.parseUnknownField(input, unknownFields, extensionRegistry, tag)) continue block21;
                                    done = true;
                                    continue block21;
                                }
                                case 10: {
                                    ByteString bs = input.readBytes();
                                    this.bitField0_ |= 1;
                                    this.name_ = bs;
                                    continue block21;
                                }
                                case 18: {
                                    ByteString bs = input.readBytes();
                                    this.bitField0_ |= 2;
                                    this.package_ = bs;
                                    continue block21;
                                }
                                case 26: {
                                    ByteString bs = input.readBytes();
                                    if ((mutable_bitField0_ & 4) != 4) {
                                        this.dependency_ = new LazyStringArrayList();
                                        mutable_bitField0_ |= 4;
                                    }
                                    this.dependency_.add(bs);
                                    continue block21;
                                }
                                case 34: {
                                    if ((mutable_bitField0_ & 0x20) != 32) {
                                        this.messageType_ = new ArrayList<DescriptorProto>();
                                        mutable_bitField0_ |= 0x20;
                                    }
                                    this.messageType_.add(input.readMessage(DescriptorProto.PARSER, extensionRegistry));
                                    continue block21;
                                }
                                case 42: {
                                    if ((mutable_bitField0_ & 0x40) != 64) {
                                        this.enumType_ = new ArrayList<EnumDescriptorProto>();
                                        mutable_bitField0_ |= 0x40;
                                    }
                                    this.enumType_.add(input.readMessage(EnumDescriptorProto.PARSER, extensionRegistry));
                                    continue block21;
                                }
                                case 50: {
                                    if ((mutable_bitField0_ & 0x80) != 128) {
                                        this.service_ = new ArrayList<ServiceDescriptorProto>();
                                        mutable_bitField0_ |= 0x80;
                                    }
                                    this.service_.add(input.readMessage(ServiceDescriptorProto.PARSER, extensionRegistry));
                                    continue block21;
                                }
                                case 58: {
                                    if ((mutable_bitField0_ & 0x100) != 256) {
                                        this.extension_ = new ArrayList<FieldDescriptorProto>();
                                        mutable_bitField0_ |= 0x100;
                                    }
                                    this.extension_.add(input.readMessage(FieldDescriptorProto.PARSER, extensionRegistry));
                                    continue block21;
                                }
                                case 66: {
                                    FileOptions.Builder subBuilder = null;
                                    if ((this.bitField0_ & 4) == 4) {
                                        subBuilder = this.options_.toBuilder();
                                    }
                                    this.options_ = input.readMessage(FileOptions.PARSER, extensionRegistry);
                                    if (subBuilder != null) {
                                        subBuilder.mergeFrom(this.options_);
                                        this.options_ = subBuilder.buildPartial();
                                    }
                                    this.bitField0_ |= 4;
                                    continue block21;
                                }
                                case 74: {
                                    SourceCodeInfo.Builder subBuilder = null;
                                    if ((this.bitField0_ & 8) == 8) {
                                        subBuilder = this.sourceCodeInfo_.toBuilder();
                                    }
                                    this.sourceCodeInfo_ = input.readMessage(SourceCodeInfo.PARSER, extensionRegistry);
                                    if (subBuilder != null) {
                                        subBuilder.mergeFrom(this.sourceCodeInfo_);
                                        this.sourceCodeInfo_ = subBuilder.buildPartial();
                                    }
                                    this.bitField0_ |= 8;
                                    continue block21;
                                }
                                case 80: {
                                    if ((mutable_bitField0_ & 8) != 8) {
                                        this.publicDependency_ = new ArrayList<Integer>();
                                        mutable_bitField0_ |= 8;
                                    }
                                    this.publicDependency_.add(input.readInt32());
                                    continue block21;
                                }
                                case 82: {
                                    int length = input.readRawVarint32();
                                    limit = input.pushLimit(length);
                                    if ((mutable_bitField0_ & 8) != 8 && input.getBytesUntilLimit() > 0) {
                                        this.publicDependency_ = new ArrayList<Integer>();
                                        mutable_bitField0_ |= 8;
                                    }
                                    while (input.getBytesUntilLimit() > 0) {
                                        this.publicDependency_.add(input.readInt32());
                                    }
                                    input.popLimit(limit);
                                    continue block21;
                                }
                                case 88: {
                                    if ((mutable_bitField0_ & 0x10) != 16) {
                                        this.weakDependency_ = new ArrayList<Integer>();
                                        mutable_bitField0_ |= 0x10;
                                    }
                                    this.weakDependency_.add(input.readInt32());
                                    continue block21;
                                }
                                case 90: 
                            }
                            int length = input.readRawVarint32();
                            limit = input.pushLimit(length);
                            if ((mutable_bitField0_ & 0x10) != 16 && input.getBytesUntilLimit() > 0) {
                                this.weakDependency_ = new ArrayList<Integer>();
                                mutable_bitField0_ |= 0x10;
                            }
                            while (input.getBytesUntilLimit() > 0) {
                                this.weakDependency_.add(input.readInt32());
                            }
                            input.popLimit(limit);
                        }
                        Object var10_17 = null;
                        if ((mutable_bitField0_ & 4) == 4) {
                            this.dependency_ = this.dependency_.getUnmodifiableView();
                        }
                        if ((mutable_bitField0_ & 0x20) != 32) break block51;
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e) {
                        throw new InvalidProtocolBufferException(e.getMessage()).setUnfinishedMessage(this);
                    }
                } catch (Throwable throwable) {
                    Object var10_18 = null;
                    if ((mutable_bitField0_ & 4) == 4) {
                        this.dependency_ = this.dependency_.getUnmodifiableView();
                    }
                    if ((mutable_bitField0_ & 0x20) == 32) {
                        this.messageType_ = Collections.unmodifiableList(this.messageType_);
                    }
                    if ((mutable_bitField0_ & 0x40) == 64) {
                        this.enumType_ = Collections.unmodifiableList(this.enumType_);
                    }
                    if ((mutable_bitField0_ & 0x80) == 128) {
                        this.service_ = Collections.unmodifiableList(this.service_);
                    }
                    if ((mutable_bitField0_ & 0x100) == 256) {
                        this.extension_ = Collections.unmodifiableList(this.extension_);
                    }
                    if ((mutable_bitField0_ & 8) == 8) {
                        this.publicDependency_ = Collections.unmodifiableList(this.publicDependency_);
                    }
                    if ((mutable_bitField0_ & 0x10) == 16) {
                        this.weakDependency_ = Collections.unmodifiableList(this.weakDependency_);
                    }
                    this.unknownFields = unknownFields.build();
                    this.makeExtensionsImmutable();
                    throw throwable;
                }
                this.messageType_ = Collections.unmodifiableList(this.messageType_);
            }
            if ((mutable_bitField0_ & 0x40) == 64) {
                this.enumType_ = Collections.unmodifiableList(this.enumType_);
            }
            if ((mutable_bitField0_ & 0x80) == 128) {
                this.service_ = Collections.unmodifiableList(this.service_);
            }
            if ((mutable_bitField0_ & 0x100) == 256) {
                this.extension_ = Collections.unmodifiableList(this.extension_);
            }
            if ((mutable_bitField0_ & 8) == 8) {
                this.publicDependency_ = Collections.unmodifiableList(this.publicDependency_);
            }
            if ((mutable_bitField0_ & 0x10) == 16) {
                this.weakDependency_ = Collections.unmodifiableList(this.weakDependency_);
            }
            this.unknownFields = unknownFields.build();
            this.makeExtensionsImmutable();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return internal_static_google_protobuf_FileDescriptorProto_descriptor;
        }

        @Override
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return internal_static_google_protobuf_FileDescriptorProto_fieldAccessorTable.ensureFieldAccessorsInitialized(FileDescriptorProto.class, Builder.class);
        }

        public Parser<FileDescriptorProto> getParserForType() {
            return PARSER;
        }

        @Override
        public boolean hasName() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override
        public String getName() {
            Object ref = this.name_;
            if (ref instanceof String) {
                return (String)ref;
            }
            ByteString bs = (ByteString)ref;
            String s2 = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.name_ = s2;
            }
            return s2;
        }

        @Override
        public ByteString getNameBytes() {
            Object ref = this.name_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.name_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        @Override
        public boolean hasPackage() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override
        public String getPackage() {
            Object ref = this.package_;
            if (ref instanceof String) {
                return (String)ref;
            }
            ByteString bs = (ByteString)ref;
            String s2 = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.package_ = s2;
            }
            return s2;
        }

        @Override
        public ByteString getPackageBytes() {
            Object ref = this.package_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.package_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        @Override
        public ProtocolStringList getDependencyList() {
            return this.dependency_;
        }

        @Override
        public int getDependencyCount() {
            return this.dependency_.size();
        }

        @Override
        public String getDependency(int index) {
            return (String)this.dependency_.get(index);
        }

        @Override
        public ByteString getDependencyBytes(int index) {
            return this.dependency_.getByteString(index);
        }

        @Override
        public List<Integer> getPublicDependencyList() {
            return this.publicDependency_;
        }

        @Override
        public int getPublicDependencyCount() {
            return this.publicDependency_.size();
        }

        @Override
        public int getPublicDependency(int index) {
            return this.publicDependency_.get(index);
        }

        @Override
        public List<Integer> getWeakDependencyList() {
            return this.weakDependency_;
        }

        @Override
        public int getWeakDependencyCount() {
            return this.weakDependency_.size();
        }

        @Override
        public int getWeakDependency(int index) {
            return this.weakDependency_.get(index);
        }

        @Override
        public List<DescriptorProto> getMessageTypeList() {
            return this.messageType_;
        }

        @Override
        public List<? extends DescriptorProtoOrBuilder> getMessageTypeOrBuilderList() {
            return this.messageType_;
        }

        @Override
        public int getMessageTypeCount() {
            return this.messageType_.size();
        }

        @Override
        public DescriptorProto getMessageType(int index) {
            return this.messageType_.get(index);
        }

        @Override
        public DescriptorProtoOrBuilder getMessageTypeOrBuilder(int index) {
            return this.messageType_.get(index);
        }

        @Override
        public List<EnumDescriptorProto> getEnumTypeList() {
            return this.enumType_;
        }

        @Override
        public List<? extends EnumDescriptorProtoOrBuilder> getEnumTypeOrBuilderList() {
            return this.enumType_;
        }

        @Override
        public int getEnumTypeCount() {
            return this.enumType_.size();
        }

        @Override
        public EnumDescriptorProto getEnumType(int index) {
            return this.enumType_.get(index);
        }

        @Override
        public EnumDescriptorProtoOrBuilder getEnumTypeOrBuilder(int index) {
            return this.enumType_.get(index);
        }

        @Override
        public List<ServiceDescriptorProto> getServiceList() {
            return this.service_;
        }

        @Override
        public List<? extends ServiceDescriptorProtoOrBuilder> getServiceOrBuilderList() {
            return this.service_;
        }

        @Override
        public int getServiceCount() {
            return this.service_.size();
        }

        @Override
        public ServiceDescriptorProto getService(int index) {
            return this.service_.get(index);
        }

        @Override
        public ServiceDescriptorProtoOrBuilder getServiceOrBuilder(int index) {
            return this.service_.get(index);
        }

        @Override
        public List<FieldDescriptorProto> getExtensionList() {
            return this.extension_;
        }

        @Override
        public List<? extends FieldDescriptorProtoOrBuilder> getExtensionOrBuilderList() {
            return this.extension_;
        }

        @Override
        public int getExtensionCount() {
            return this.extension_.size();
        }

        @Override
        public FieldDescriptorProto getExtension(int index) {
            return this.extension_.get(index);
        }

        @Override
        public FieldDescriptorProtoOrBuilder getExtensionOrBuilder(int index) {
            return this.extension_.get(index);
        }

        @Override
        public boolean hasOptions() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override
        public FileOptions getOptions() {
            return this.options_;
        }

        @Override
        public FileOptionsOrBuilder getOptionsOrBuilder() {
            return this.options_;
        }

        @Override
        public boolean hasSourceCodeInfo() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override
        public SourceCodeInfo getSourceCodeInfo() {
            return this.sourceCodeInfo_;
        }

        @Override
        public SourceCodeInfoOrBuilder getSourceCodeInfoOrBuilder() {
            return this.sourceCodeInfo_;
        }

        private void initFields() {
            this.name_ = "";
            this.package_ = "";
            this.dependency_ = LazyStringArrayList.EMPTY;
            this.publicDependency_ = Collections.emptyList();
            this.weakDependency_ = Collections.emptyList();
            this.messageType_ = Collections.emptyList();
            this.enumType_ = Collections.emptyList();
            this.service_ = Collections.emptyList();
            this.extension_ = Collections.emptyList();
            this.options_ = FileOptions.getDefaultInstance();
            this.sourceCodeInfo_ = SourceCodeInfo.getDefaultInstance();
        }

        @Override
        public final boolean isInitialized() {
            int i;
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            for (i = 0; i < this.getMessageTypeCount(); ++i) {
                if (this.getMessageType(i).isInitialized()) continue;
                this.memoizedIsInitialized = 0;
                return false;
            }
            for (i = 0; i < this.getEnumTypeCount(); ++i) {
                if (this.getEnumType(i).isInitialized()) continue;
                this.memoizedIsInitialized = 0;
                return false;
            }
            for (i = 0; i < this.getServiceCount(); ++i) {
                if (this.getService(i).isInitialized()) continue;
                this.memoizedIsInitialized = 0;
                return false;
            }
            for (i = 0; i < this.getExtensionCount(); ++i) {
                if (this.getExtension(i).isInitialized()) continue;
                this.memoizedIsInitialized = 0;
                return false;
            }
            if (this.hasOptions() && !this.getOptions().isInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        @Override
        public void writeTo(CodedOutputStream output) throws IOException {
            int i;
            this.getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                output.writeBytes(1, this.getNameBytes());
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeBytes(2, this.getPackageBytes());
            }
            for (i = 0; i < this.dependency_.size(); ++i) {
                output.writeBytes(3, this.dependency_.getByteString(i));
            }
            for (i = 0; i < this.messageType_.size(); ++i) {
                output.writeMessage(4, this.messageType_.get(i));
            }
            for (i = 0; i < this.enumType_.size(); ++i) {
                output.writeMessage(5, this.enumType_.get(i));
            }
            for (i = 0; i < this.service_.size(); ++i) {
                output.writeMessage(6, this.service_.get(i));
            }
            for (i = 0; i < this.extension_.size(); ++i) {
                output.writeMessage(7, this.extension_.get(i));
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeMessage(8, this.options_);
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeMessage(9, this.sourceCodeInfo_);
            }
            for (i = 0; i < this.publicDependency_.size(); ++i) {
                output.writeInt32(10, this.publicDependency_.get(i));
            }
            for (i = 0; i < this.weakDependency_.size(); ++i) {
                output.writeInt32(11, this.weakDependency_.get(i));
            }
            this.getUnknownFields().writeTo(output);
        }

        @Override
        public int getSerializedSize() {
            int i;
            int i2;
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            size = 0;
            if ((this.bitField0_ & 1) == 1) {
                size += CodedOutputStream.computeBytesSize(1, this.getNameBytes());
            }
            if ((this.bitField0_ & 2) == 2) {
                size += CodedOutputStream.computeBytesSize(2, this.getPackageBytes());
            }
            int dataSize = 0;
            for (i2 = 0; i2 < this.dependency_.size(); ++i2) {
                dataSize += CodedOutputStream.computeBytesSizeNoTag(this.dependency_.getByteString(i2));
            }
            size += dataSize;
            size += 1 * this.getDependencyList().size();
            for (i = 0; i < this.messageType_.size(); ++i) {
                size += CodedOutputStream.computeMessageSize(4, this.messageType_.get(i));
            }
            for (i = 0; i < this.enumType_.size(); ++i) {
                size += CodedOutputStream.computeMessageSize(5, this.enumType_.get(i));
            }
            for (i = 0; i < this.service_.size(); ++i) {
                size += CodedOutputStream.computeMessageSize(6, this.service_.get(i));
            }
            for (i = 0; i < this.extension_.size(); ++i) {
                size += CodedOutputStream.computeMessageSize(7, this.extension_.get(i));
            }
            if ((this.bitField0_ & 4) == 4) {
                size += CodedOutputStream.computeMessageSize(8, this.options_);
            }
            if ((this.bitField0_ & 8) == 8) {
                size += CodedOutputStream.computeMessageSize(9, this.sourceCodeInfo_);
            }
            dataSize = 0;
            for (i2 = 0; i2 < this.publicDependency_.size(); ++i2) {
                dataSize += CodedOutputStream.computeInt32SizeNoTag(this.publicDependency_.get(i2));
            }
            size += dataSize;
            size += 1 * this.getPublicDependencyList().size();
            dataSize = 0;
            for (i2 = 0; i2 < this.weakDependency_.size(); ++i2) {
                dataSize += CodedOutputStream.computeInt32SizeNoTag(this.weakDependency_.get(i2));
            }
            size += dataSize;
            size += 1 * this.getWeakDependencyList().size();
            this.memoizedSerializedSize = size += this.getUnknownFields().getSerializedSize();
            return size;
        }

        @Override
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static FileDescriptorProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static FileDescriptorProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static FileDescriptorProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static FileDescriptorProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static FileDescriptorProto parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static FileDescriptorProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static FileDescriptorProto parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static FileDescriptorProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static FileDescriptorProto parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static FileDescriptorProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override
        public Builder newBuilderForType() {
            return FileDescriptorProto.newBuilder();
        }

        public static Builder newBuilder(FileDescriptorProto prototype) {
            return FileDescriptorProto.newBuilder().mergeFrom(prototype);
        }

        @Override
        public Builder toBuilder() {
            return FileDescriptorProto.newBuilder(this);
        }

        @Override
        protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        static {
            PARSER = new AbstractParser<FileDescriptorProto>(){

                @Override
                public FileDescriptorProto parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                    return new FileDescriptorProto(input, extensionRegistry);
                }
            };
            defaultInstance = new FileDescriptorProto(true);
            defaultInstance.initFields();
        }

        /*
         * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
         */
        public static final class Builder
        extends GeneratedMessage.Builder<Builder>
        implements FileDescriptorProtoOrBuilder {
            private int bitField0_;
            private Object name_ = "";
            private Object package_ = "";
            private LazyStringList dependency_ = LazyStringArrayList.EMPTY;
            private List<Integer> publicDependency_ = Collections.emptyList();
            private List<Integer> weakDependency_ = Collections.emptyList();
            private List<DescriptorProto> messageType_ = Collections.emptyList();
            private RepeatedFieldBuilder<DescriptorProto, DescriptorProto.Builder, DescriptorProtoOrBuilder> messageTypeBuilder_;
            private List<EnumDescriptorProto> enumType_ = Collections.emptyList();
            private RepeatedFieldBuilder<EnumDescriptorProto, EnumDescriptorProto.Builder, EnumDescriptorProtoOrBuilder> enumTypeBuilder_;
            private List<ServiceDescriptorProto> service_ = Collections.emptyList();
            private RepeatedFieldBuilder<ServiceDescriptorProto, ServiceDescriptorProto.Builder, ServiceDescriptorProtoOrBuilder> serviceBuilder_;
            private List<FieldDescriptorProto> extension_ = Collections.emptyList();
            private RepeatedFieldBuilder<FieldDescriptorProto, FieldDescriptorProto.Builder, FieldDescriptorProtoOrBuilder> extensionBuilder_;
            private FileOptions options_ = FileOptions.getDefaultInstance();
            private SingleFieldBuilder<FileOptions, FileOptions.Builder, FileOptionsOrBuilder> optionsBuilder_;
            private SourceCodeInfo sourceCodeInfo_ = SourceCodeInfo.getDefaultInstance();
            private SingleFieldBuilder<SourceCodeInfo, SourceCodeInfo.Builder, SourceCodeInfoOrBuilder> sourceCodeInfoBuilder_;

            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_google_protobuf_FileDescriptorProto_descriptor;
            }

            @Override
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_google_protobuf_FileDescriptorProto_fieldAccessorTable.ensureFieldAccessorsInitialized(FileDescriptorProto.class, Builder.class);
            }

            private Builder() {
                this.maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessage.BuilderParent parent) {
                super(parent);
                this.maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                if (GeneratedMessage.alwaysUseFieldBuilders) {
                    this.getMessageTypeFieldBuilder();
                    this.getEnumTypeFieldBuilder();
                    this.getServiceFieldBuilder();
                    this.getExtensionFieldBuilder();
                    this.getOptionsFieldBuilder();
                    this.getSourceCodeInfoFieldBuilder();
                }
            }

            private static Builder create() {
                return new Builder();
            }

            @Override
            public Builder clear() {
                super.clear();
                this.name_ = "";
                this.bitField0_ &= 0xFFFFFFFE;
                this.package_ = "";
                this.bitField0_ &= 0xFFFFFFFD;
                this.dependency_ = LazyStringArrayList.EMPTY;
                this.bitField0_ &= 0xFFFFFFFB;
                this.publicDependency_ = Collections.emptyList();
                this.bitField0_ &= 0xFFFFFFF7;
                this.weakDependency_ = Collections.emptyList();
                this.bitField0_ &= 0xFFFFFFEF;
                if (this.messageTypeBuilder_ == null) {
                    this.messageType_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFFFDF;
                } else {
                    this.messageTypeBuilder_.clear();
                }
                if (this.enumTypeBuilder_ == null) {
                    this.enumType_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFFFBF;
                } else {
                    this.enumTypeBuilder_.clear();
                }
                if (this.serviceBuilder_ == null) {
                    this.service_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFFF7F;
                } else {
                    this.serviceBuilder_.clear();
                }
                if (this.extensionBuilder_ == null) {
                    this.extension_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFFEFF;
                } else {
                    this.extensionBuilder_.clear();
                }
                if (this.optionsBuilder_ == null) {
                    this.options_ = FileOptions.getDefaultInstance();
                } else {
                    this.optionsBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFDFF;
                if (this.sourceCodeInfoBuilder_ == null) {
                    this.sourceCodeInfo_ = SourceCodeInfo.getDefaultInstance();
                } else {
                    this.sourceCodeInfoBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFBFF;
                return this;
            }

            @Override
            public Builder clone() {
                return Builder.create().mergeFrom(this.buildPartial());
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return internal_static_google_protobuf_FileDescriptorProto_descriptor;
            }

            @Override
            public FileDescriptorProto getDefaultInstanceForType() {
                return FileDescriptorProto.getDefaultInstance();
            }

            @Override
            public FileDescriptorProto build() {
                FileDescriptorProto result = this.buildPartial();
                if (!result.isInitialized()) {
                    throw Builder.newUninitializedMessageException(result);
                }
                return result;
            }

            @Override
            public FileDescriptorProto buildPartial() {
                FileDescriptorProto result = new FileDescriptorProto(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ |= 1;
                }
                result.name_ = this.name_;
                if ((from_bitField0_ & 2) == 2) {
                    to_bitField0_ |= 2;
                }
                result.package_ = this.package_;
                if ((this.bitField0_ & 4) == 4) {
                    this.dependency_ = this.dependency_.getUnmodifiableView();
                    this.bitField0_ &= 0xFFFFFFFB;
                }
                result.dependency_ = this.dependency_;
                if ((this.bitField0_ & 8) == 8) {
                    this.publicDependency_ = Collections.unmodifiableList(this.publicDependency_);
                    this.bitField0_ &= 0xFFFFFFF7;
                }
                result.publicDependency_ = this.publicDependency_;
                if ((this.bitField0_ & 0x10) == 16) {
                    this.weakDependency_ = Collections.unmodifiableList(this.weakDependency_);
                    this.bitField0_ &= 0xFFFFFFEF;
                }
                result.weakDependency_ = this.weakDependency_;
                if (this.messageTypeBuilder_ == null) {
                    if ((this.bitField0_ & 0x20) == 32) {
                        this.messageType_ = Collections.unmodifiableList(this.messageType_);
                        this.bitField0_ &= 0xFFFFFFDF;
                    }
                    result.messageType_ = this.messageType_;
                } else {
                    result.messageType_ = this.messageTypeBuilder_.build();
                }
                if (this.enumTypeBuilder_ == null) {
                    if ((this.bitField0_ & 0x40) == 64) {
                        this.enumType_ = Collections.unmodifiableList(this.enumType_);
                        this.bitField0_ &= 0xFFFFFFBF;
                    }
                    result.enumType_ = this.enumType_;
                } else {
                    result.enumType_ = this.enumTypeBuilder_.build();
                }
                if (this.serviceBuilder_ == null) {
                    if ((this.bitField0_ & 0x80) == 128) {
                        this.service_ = Collections.unmodifiableList(this.service_);
                        this.bitField0_ &= 0xFFFFFF7F;
                    }
                    result.service_ = this.service_;
                } else {
                    result.service_ = this.serviceBuilder_.build();
                }
                if (this.extensionBuilder_ == null) {
                    if ((this.bitField0_ & 0x100) == 256) {
                        this.extension_ = Collections.unmodifiableList(this.extension_);
                        this.bitField0_ &= 0xFFFFFEFF;
                    }
                    result.extension_ = this.extension_;
                } else {
                    result.extension_ = this.extensionBuilder_.build();
                }
                if ((from_bitField0_ & 0x200) == 512) {
                    to_bitField0_ |= 4;
                }
                if (this.optionsBuilder_ == null) {
                    result.options_ = this.options_;
                } else {
                    result.options_ = this.optionsBuilder_.build();
                }
                if ((from_bitField0_ & 0x400) == 1024) {
                    to_bitField0_ |= 8;
                }
                if (this.sourceCodeInfoBuilder_ == null) {
                    result.sourceCodeInfo_ = this.sourceCodeInfo_;
                } else {
                    result.sourceCodeInfo_ = this.sourceCodeInfoBuilder_.build();
                }
                result.bitField0_ = to_bitField0_;
                this.onBuilt();
                return result;
            }

            @Override
            public Builder mergeFrom(Message other) {
                if (other instanceof FileDescriptorProto) {
                    return this.mergeFrom((FileDescriptorProto)other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(FileDescriptorProto other) {
                if (other == FileDescriptorProto.getDefaultInstance()) {
                    return this;
                }
                if (other.hasName()) {
                    this.bitField0_ |= 1;
                    this.name_ = other.name_;
                    this.onChanged();
                }
                if (other.hasPackage()) {
                    this.bitField0_ |= 2;
                    this.package_ = other.package_;
                    this.onChanged();
                }
                if (!other.dependency_.isEmpty()) {
                    if (this.dependency_.isEmpty()) {
                        this.dependency_ = other.dependency_;
                        this.bitField0_ &= 0xFFFFFFFB;
                    } else {
                        this.ensureDependencyIsMutable();
                        this.dependency_.addAll(other.dependency_);
                    }
                    this.onChanged();
                }
                if (!other.publicDependency_.isEmpty()) {
                    if (this.publicDependency_.isEmpty()) {
                        this.publicDependency_ = other.publicDependency_;
                        this.bitField0_ &= 0xFFFFFFF7;
                    } else {
                        this.ensurePublicDependencyIsMutable();
                        this.publicDependency_.addAll(other.publicDependency_);
                    }
                    this.onChanged();
                }
                if (!other.weakDependency_.isEmpty()) {
                    if (this.weakDependency_.isEmpty()) {
                        this.weakDependency_ = other.weakDependency_;
                        this.bitField0_ &= 0xFFFFFFEF;
                    } else {
                        this.ensureWeakDependencyIsMutable();
                        this.weakDependency_.addAll(other.weakDependency_);
                    }
                    this.onChanged();
                }
                if (this.messageTypeBuilder_ == null) {
                    if (!other.messageType_.isEmpty()) {
                        if (this.messageType_.isEmpty()) {
                            this.messageType_ = other.messageType_;
                            this.bitField0_ &= 0xFFFFFFDF;
                        } else {
                            this.ensureMessageTypeIsMutable();
                            this.messageType_.addAll(other.messageType_);
                        }
                        this.onChanged();
                    }
                } else if (!other.messageType_.isEmpty()) {
                    if (this.messageTypeBuilder_.isEmpty()) {
                        this.messageTypeBuilder_.dispose();
                        this.messageTypeBuilder_ = null;
                        this.messageType_ = other.messageType_;
                        this.bitField0_ &= 0xFFFFFFDF;
                        this.messageTypeBuilder_ = GeneratedMessage.alwaysUseFieldBuilders ? this.getMessageTypeFieldBuilder() : null;
                    } else {
                        this.messageTypeBuilder_.addAllMessages(other.messageType_);
                    }
                }
                if (this.enumTypeBuilder_ == null) {
                    if (!other.enumType_.isEmpty()) {
                        if (this.enumType_.isEmpty()) {
                            this.enumType_ = other.enumType_;
                            this.bitField0_ &= 0xFFFFFFBF;
                        } else {
                            this.ensureEnumTypeIsMutable();
                            this.enumType_.addAll(other.enumType_);
                        }
                        this.onChanged();
                    }
                } else if (!other.enumType_.isEmpty()) {
                    if (this.enumTypeBuilder_.isEmpty()) {
                        this.enumTypeBuilder_.dispose();
                        this.enumTypeBuilder_ = null;
                        this.enumType_ = other.enumType_;
                        this.bitField0_ &= 0xFFFFFFBF;
                        this.enumTypeBuilder_ = GeneratedMessage.alwaysUseFieldBuilders ? this.getEnumTypeFieldBuilder() : null;
                    } else {
                        this.enumTypeBuilder_.addAllMessages(other.enumType_);
                    }
                }
                if (this.serviceBuilder_ == null) {
                    if (!other.service_.isEmpty()) {
                        if (this.service_.isEmpty()) {
                            this.service_ = other.service_;
                            this.bitField0_ &= 0xFFFFFF7F;
                        } else {
                            this.ensureServiceIsMutable();
                            this.service_.addAll(other.service_);
                        }
                        this.onChanged();
                    }
                } else if (!other.service_.isEmpty()) {
                    if (this.serviceBuilder_.isEmpty()) {
                        this.serviceBuilder_.dispose();
                        this.serviceBuilder_ = null;
                        this.service_ = other.service_;
                        this.bitField0_ &= 0xFFFFFF7F;
                        this.serviceBuilder_ = GeneratedMessage.alwaysUseFieldBuilders ? this.getServiceFieldBuilder() : null;
                    } else {
                        this.serviceBuilder_.addAllMessages(other.service_);
                    }
                }
                if (this.extensionBuilder_ == null) {
                    if (!other.extension_.isEmpty()) {
                        if (this.extension_.isEmpty()) {
                            this.extension_ = other.extension_;
                            this.bitField0_ &= 0xFFFFFEFF;
                        } else {
                            this.ensureExtensionIsMutable();
                            this.extension_.addAll(other.extension_);
                        }
                        this.onChanged();
                    }
                } else if (!other.extension_.isEmpty()) {
                    if (this.extensionBuilder_.isEmpty()) {
                        this.extensionBuilder_.dispose();
                        this.extensionBuilder_ = null;
                        this.extension_ = other.extension_;
                        this.bitField0_ &= 0xFFFFFEFF;
                        this.extensionBuilder_ = GeneratedMessage.alwaysUseFieldBuilders ? this.getExtensionFieldBuilder() : null;
                    } else {
                        this.extensionBuilder_.addAllMessages(other.extension_);
                    }
                }
                if (other.hasOptions()) {
                    this.mergeOptions(other.getOptions());
                }
                if (other.hasSourceCodeInfo()) {
                    this.mergeSourceCodeInfo(other.getSourceCodeInfo());
                }
                this.mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            @Override
            public final boolean isInitialized() {
                int i;
                for (i = 0; i < this.getMessageTypeCount(); ++i) {
                    if (this.getMessageType(i).isInitialized()) continue;
                    return false;
                }
                for (i = 0; i < this.getEnumTypeCount(); ++i) {
                    if (this.getEnumType(i).isInitialized()) continue;
                    return false;
                }
                for (i = 0; i < this.getServiceCount(); ++i) {
                    if (this.getService(i).isInitialized()) continue;
                    return false;
                }
                for (i = 0; i < this.getExtensionCount(); ++i) {
                    if (this.getExtension(i).isInitialized()) continue;
                    return false;
                }
                return !this.hasOptions() || this.getOptions().isInitialized();
            }

            @Override
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                FileDescriptorProto parsedMessage = null;
                try {
                    parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
                } catch (InvalidProtocolBufferException e) {
                    parsedMessage = (FileDescriptorProto)e.getUnfinishedMessage();
                    throw e;
                } finally {
                    if (parsedMessage != null) {
                        this.mergeFrom(parsedMessage);
                    }
                }
                return this;
            }

            @Override
            public boolean hasName() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override
            public String getName() {
                Object ref = this.name_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString)ref;
                    String s2 = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.name_ = s2;
                    }
                    return s2;
                }
                return (String)ref;
            }

            @Override
            public ByteString getNameBytes() {
                Object ref = this.name_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String)ref);
                    this.name_ = b;
                    return b;
                }
                return (ByteString)ref;
            }

            public Builder setName(String value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 1;
                this.name_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearName() {
                this.bitField0_ &= 0xFFFFFFFE;
                this.name_ = FileDescriptorProto.getDefaultInstance().getName();
                this.onChanged();
                return this;
            }

            public Builder setNameBytes(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 1;
                this.name_ = value;
                this.onChanged();
                return this;
            }

            @Override
            public boolean hasPackage() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override
            public String getPackage() {
                Object ref = this.package_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString)ref;
                    String s2 = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.package_ = s2;
                    }
                    return s2;
                }
                return (String)ref;
            }

            @Override
            public ByteString getPackageBytes() {
                Object ref = this.package_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String)ref);
                    this.package_ = b;
                    return b;
                }
                return (ByteString)ref;
            }

            public Builder setPackage(String value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 2;
                this.package_ = value;
                this.onChanged();
                return this;
            }

            public Builder clearPackage() {
                this.bitField0_ &= 0xFFFFFFFD;
                this.package_ = FileDescriptorProto.getDefaultInstance().getPackage();
                this.onChanged();
                return this;
            }

            public Builder setPackageBytes(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 2;
                this.package_ = value;
                this.onChanged();
                return this;
            }

            private void ensureDependencyIsMutable() {
                if ((this.bitField0_ & 4) != 4) {
                    this.dependency_ = new LazyStringArrayList(this.dependency_);
                    this.bitField0_ |= 4;
                }
            }

            @Override
            public ProtocolStringList getDependencyList() {
                return this.dependency_.getUnmodifiableView();
            }

            @Override
            public int getDependencyCount() {
                return this.dependency_.size();
            }

            @Override
            public String getDependency(int index) {
                return (String)this.dependency_.get(index);
            }

            @Override
            public ByteString getDependencyBytes(int index) {
                return this.dependency_.getByteString(index);
            }

            public Builder setDependency(int index, String value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ensureDependencyIsMutable();
                this.dependency_.set(index, value);
                this.onChanged();
                return this;
            }

            public Builder addDependency(String value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ensureDependencyIsMutable();
                this.dependency_.add(value);
                this.onChanged();
                return this;
            }

            public Builder addAllDependency(Iterable<String> values2) {
                this.ensureDependencyIsMutable();
                AbstractMessageLite.Builder.addAll(values2, this.dependency_);
                this.onChanged();
                return this;
            }

            public Builder clearDependency() {
                this.dependency_ = LazyStringArrayList.EMPTY;
                this.bitField0_ &= 0xFFFFFFFB;
                this.onChanged();
                return this;
            }

            public Builder addDependencyBytes(ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ensureDependencyIsMutable();
                this.dependency_.add(value);
                this.onChanged();
                return this;
            }

            private void ensurePublicDependencyIsMutable() {
                if ((this.bitField0_ & 8) != 8) {
                    this.publicDependency_ = new ArrayList<Integer>(this.publicDependency_);
                    this.bitField0_ |= 8;
                }
            }

            @Override
            public List<Integer> getPublicDependencyList() {
                return Collections.unmodifiableList(this.publicDependency_);
            }

            @Override
            public int getPublicDependencyCount() {
                return this.publicDependency_.size();
            }

            @Override
            public int getPublicDependency(int index) {
                return this.publicDependency_.get(index);
            }

            public Builder setPublicDependency(int index, int value) {
                this.ensurePublicDependencyIsMutable();
                this.publicDependency_.set(index, value);
                this.onChanged();
                return this;
            }

            public Builder addPublicDependency(int value) {
                this.ensurePublicDependencyIsMutable();
                this.publicDependency_.add(value);
                this.onChanged();
                return this;
            }

            public Builder addAllPublicDependency(Iterable<? extends Integer> values2) {
                this.ensurePublicDependencyIsMutable();
                AbstractMessageLite.Builder.addAll(values2, this.publicDependency_);
                this.onChanged();
                return this;
            }

            public Builder clearPublicDependency() {
                this.publicDependency_ = Collections.emptyList();
                this.bitField0_ &= 0xFFFFFFF7;
                this.onChanged();
                return this;
            }

            private void ensureWeakDependencyIsMutable() {
                if ((this.bitField0_ & 0x10) != 16) {
                    this.weakDependency_ = new ArrayList<Integer>(this.weakDependency_);
                    this.bitField0_ |= 0x10;
                }
            }

            @Override
            public List<Integer> getWeakDependencyList() {
                return Collections.unmodifiableList(this.weakDependency_);
            }

            @Override
            public int getWeakDependencyCount() {
                return this.weakDependency_.size();
            }

            @Override
            public int getWeakDependency(int index) {
                return this.weakDependency_.get(index);
            }

            public Builder setWeakDependency(int index, int value) {
                this.ensureWeakDependencyIsMutable();
                this.weakDependency_.set(index, value);
                this.onChanged();
                return this;
            }

            public Builder addWeakDependency(int value) {
                this.ensureWeakDependencyIsMutable();
                this.weakDependency_.add(value);
                this.onChanged();
                return this;
            }

            public Builder addAllWeakDependency(Iterable<? extends Integer> values2) {
                this.ensureWeakDependencyIsMutable();
                AbstractMessageLite.Builder.addAll(values2, this.weakDependency_);
                this.onChanged();
                return this;
            }

            public Builder clearWeakDependency() {
                this.weakDependency_ = Collections.emptyList();
                this.bitField0_ &= 0xFFFFFFEF;
                this.onChanged();
                return this;
            }

            private void ensureMessageTypeIsMutable() {
                if ((this.bitField0_ & 0x20) != 32) {
                    this.messageType_ = new ArrayList<DescriptorProto>(this.messageType_);
                    this.bitField0_ |= 0x20;
                }
            }

            @Override
            public List<DescriptorProto> getMessageTypeList() {
                if (this.messageTypeBuilder_ == null) {
                    return Collections.unmodifiableList(this.messageType_);
                }
                return this.messageTypeBuilder_.getMessageList();
            }

            @Override
            public int getMessageTypeCount() {
                if (this.messageTypeBuilder_ == null) {
                    return this.messageType_.size();
                }
                return this.messageTypeBuilder_.getCount();
            }

            @Override
            public DescriptorProto getMessageType(int index) {
                if (this.messageTypeBuilder_ == null) {
                    return this.messageType_.get(index);
                }
                return this.messageTypeBuilder_.getMessage(index);
            }

            public Builder setMessageType(int index, DescriptorProto value) {
                if (this.messageTypeBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureMessageTypeIsMutable();
                    this.messageType_.set(index, value);
                    this.onChanged();
                } else {
                    this.messageTypeBuilder_.setMessage(index, value);
                }
                return this;
            }

            public Builder setMessageType(int index, DescriptorProto.Builder builderForValue) {
                if (this.messageTypeBuilder_ == null) {
                    this.ensureMessageTypeIsMutable();
                    this.messageType_.set(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.messageTypeBuilder_.setMessage(index, builderForValue.build());
                }
                return this;
            }

            public Builder addMessageType(DescriptorProto value) {
                if (this.messageTypeBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureMessageTypeIsMutable();
                    this.messageType_.add(value);
                    this.onChanged();
                } else {
                    this.messageTypeBuilder_.addMessage(value);
                }
                return this;
            }

            public Builder addMessageType(int index, DescriptorProto value) {
                if (this.messageTypeBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureMessageTypeIsMutable();
                    this.messageType_.add(index, value);
                    this.onChanged();
                } else {
                    this.messageTypeBuilder_.addMessage(index, value);
                }
                return this;
            }

            public Builder addMessageType(DescriptorProto.Builder builderForValue) {
                if (this.messageTypeBuilder_ == null) {
                    this.ensureMessageTypeIsMutable();
                    this.messageType_.add(builderForValue.build());
                    this.onChanged();
                } else {
                    this.messageTypeBuilder_.addMessage(builderForValue.build());
                }
                return this;
            }

            public Builder addMessageType(int index, DescriptorProto.Builder builderForValue) {
                if (this.messageTypeBuilder_ == null) {
                    this.ensureMessageTypeIsMutable();
                    this.messageType_.add(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.messageTypeBuilder_.addMessage(index, builderForValue.build());
                }
                return this;
            }

            public Builder addAllMessageType(Iterable<? extends DescriptorProto> values2) {
                if (this.messageTypeBuilder_ == null) {
                    this.ensureMessageTypeIsMutable();
                    AbstractMessageLite.Builder.addAll(values2, this.messageType_);
                    this.onChanged();
                } else {
                    this.messageTypeBuilder_.addAllMessages(values2);
                }
                return this;
            }

            public Builder clearMessageType() {
                if (this.messageTypeBuilder_ == null) {
                    this.messageType_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFFFDF;
                    this.onChanged();
                } else {
                    this.messageTypeBuilder_.clear();
                }
                return this;
            }

            public Builder removeMessageType(int index) {
                if (this.messageTypeBuilder_ == null) {
                    this.ensureMessageTypeIsMutable();
                    this.messageType_.remove(index);
                    this.onChanged();
                } else {
                    this.messageTypeBuilder_.remove(index);
                }
                return this;
            }

            public DescriptorProto.Builder getMessageTypeBuilder(int index) {
                return this.getMessageTypeFieldBuilder().getBuilder(index);
            }

            @Override
            public DescriptorProtoOrBuilder getMessageTypeOrBuilder(int index) {
                if (this.messageTypeBuilder_ == null) {
                    return this.messageType_.get(index);
                }
                return this.messageTypeBuilder_.getMessageOrBuilder(index);
            }

            @Override
            public List<? extends DescriptorProtoOrBuilder> getMessageTypeOrBuilderList() {
                if (this.messageTypeBuilder_ != null) {
                    return this.messageTypeBuilder_.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.messageType_);
            }

            public DescriptorProto.Builder addMessageTypeBuilder() {
                return this.getMessageTypeFieldBuilder().addBuilder(DescriptorProto.getDefaultInstance());
            }

            public DescriptorProto.Builder addMessageTypeBuilder(int index) {
                return this.getMessageTypeFieldBuilder().addBuilder(index, DescriptorProto.getDefaultInstance());
            }

            public List<DescriptorProto.Builder> getMessageTypeBuilderList() {
                return this.getMessageTypeFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilder<DescriptorProto, DescriptorProto.Builder, DescriptorProtoOrBuilder> getMessageTypeFieldBuilder() {
                if (this.messageTypeBuilder_ == null) {
                    this.messageTypeBuilder_ = new RepeatedFieldBuilder(this.messageType_, (this.bitField0_ & 0x20) == 32, this.getParentForChildren(), this.isClean());
                    this.messageType_ = null;
                }
                return this.messageTypeBuilder_;
            }

            private void ensureEnumTypeIsMutable() {
                if ((this.bitField0_ & 0x40) != 64) {
                    this.enumType_ = new ArrayList<EnumDescriptorProto>(this.enumType_);
                    this.bitField0_ |= 0x40;
                }
            }

            @Override
            public List<EnumDescriptorProto> getEnumTypeList() {
                if (this.enumTypeBuilder_ == null) {
                    return Collections.unmodifiableList(this.enumType_);
                }
                return this.enumTypeBuilder_.getMessageList();
            }

            @Override
            public int getEnumTypeCount() {
                if (this.enumTypeBuilder_ == null) {
                    return this.enumType_.size();
                }
                return this.enumTypeBuilder_.getCount();
            }

            @Override
            public EnumDescriptorProto getEnumType(int index) {
                if (this.enumTypeBuilder_ == null) {
                    return this.enumType_.get(index);
                }
                return this.enumTypeBuilder_.getMessage(index);
            }

            public Builder setEnumType(int index, EnumDescriptorProto value) {
                if (this.enumTypeBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureEnumTypeIsMutable();
                    this.enumType_.set(index, value);
                    this.onChanged();
                } else {
                    this.enumTypeBuilder_.setMessage(index, value);
                }
                return this;
            }

            public Builder setEnumType(int index, EnumDescriptorProto.Builder builderForValue) {
                if (this.enumTypeBuilder_ == null) {
                    this.ensureEnumTypeIsMutable();
                    this.enumType_.set(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.enumTypeBuilder_.setMessage(index, builderForValue.build());
                }
                return this;
            }

            public Builder addEnumType(EnumDescriptorProto value) {
                if (this.enumTypeBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureEnumTypeIsMutable();
                    this.enumType_.add(value);
                    this.onChanged();
                } else {
                    this.enumTypeBuilder_.addMessage(value);
                }
                return this;
            }

            public Builder addEnumType(int index, EnumDescriptorProto value) {
                if (this.enumTypeBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureEnumTypeIsMutable();
                    this.enumType_.add(index, value);
                    this.onChanged();
                } else {
                    this.enumTypeBuilder_.addMessage(index, value);
                }
                return this;
            }

            public Builder addEnumType(EnumDescriptorProto.Builder builderForValue) {
                if (this.enumTypeBuilder_ == null) {
                    this.ensureEnumTypeIsMutable();
                    this.enumType_.add(builderForValue.build());
                    this.onChanged();
                } else {
                    this.enumTypeBuilder_.addMessage(builderForValue.build());
                }
                return this;
            }

            public Builder addEnumType(int index, EnumDescriptorProto.Builder builderForValue) {
                if (this.enumTypeBuilder_ == null) {
                    this.ensureEnumTypeIsMutable();
                    this.enumType_.add(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.enumTypeBuilder_.addMessage(index, builderForValue.build());
                }
                return this;
            }

            public Builder addAllEnumType(Iterable<? extends EnumDescriptorProto> values2) {
                if (this.enumTypeBuilder_ == null) {
                    this.ensureEnumTypeIsMutable();
                    AbstractMessageLite.Builder.addAll(values2, this.enumType_);
                    this.onChanged();
                } else {
                    this.enumTypeBuilder_.addAllMessages(values2);
                }
                return this;
            }

            public Builder clearEnumType() {
                if (this.enumTypeBuilder_ == null) {
                    this.enumType_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFFFBF;
                    this.onChanged();
                } else {
                    this.enumTypeBuilder_.clear();
                }
                return this;
            }

            public Builder removeEnumType(int index) {
                if (this.enumTypeBuilder_ == null) {
                    this.ensureEnumTypeIsMutable();
                    this.enumType_.remove(index);
                    this.onChanged();
                } else {
                    this.enumTypeBuilder_.remove(index);
                }
                return this;
            }

            public EnumDescriptorProto.Builder getEnumTypeBuilder(int index) {
                return this.getEnumTypeFieldBuilder().getBuilder(index);
            }

            @Override
            public EnumDescriptorProtoOrBuilder getEnumTypeOrBuilder(int index) {
                if (this.enumTypeBuilder_ == null) {
                    return this.enumType_.get(index);
                }
                return this.enumTypeBuilder_.getMessageOrBuilder(index);
            }

            @Override
            public List<? extends EnumDescriptorProtoOrBuilder> getEnumTypeOrBuilderList() {
                if (this.enumTypeBuilder_ != null) {
                    return this.enumTypeBuilder_.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.enumType_);
            }

            public EnumDescriptorProto.Builder addEnumTypeBuilder() {
                return this.getEnumTypeFieldBuilder().addBuilder(EnumDescriptorProto.getDefaultInstance());
            }

            public EnumDescriptorProto.Builder addEnumTypeBuilder(int index) {
                return this.getEnumTypeFieldBuilder().addBuilder(index, EnumDescriptorProto.getDefaultInstance());
            }

            public List<EnumDescriptorProto.Builder> getEnumTypeBuilderList() {
                return this.getEnumTypeFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilder<EnumDescriptorProto, EnumDescriptorProto.Builder, EnumDescriptorProtoOrBuilder> getEnumTypeFieldBuilder() {
                if (this.enumTypeBuilder_ == null) {
                    this.enumTypeBuilder_ = new RepeatedFieldBuilder(this.enumType_, (this.bitField0_ & 0x40) == 64, this.getParentForChildren(), this.isClean());
                    this.enumType_ = null;
                }
                return this.enumTypeBuilder_;
            }

            private void ensureServiceIsMutable() {
                if ((this.bitField0_ & 0x80) != 128) {
                    this.service_ = new ArrayList<ServiceDescriptorProto>(this.service_);
                    this.bitField0_ |= 0x80;
                }
            }

            @Override
            public List<ServiceDescriptorProto> getServiceList() {
                if (this.serviceBuilder_ == null) {
                    return Collections.unmodifiableList(this.service_);
                }
                return this.serviceBuilder_.getMessageList();
            }

            @Override
            public int getServiceCount() {
                if (this.serviceBuilder_ == null) {
                    return this.service_.size();
                }
                return this.serviceBuilder_.getCount();
            }

            @Override
            public ServiceDescriptorProto getService(int index) {
                if (this.serviceBuilder_ == null) {
                    return this.service_.get(index);
                }
                return this.serviceBuilder_.getMessage(index);
            }

            public Builder setService(int index, ServiceDescriptorProto value) {
                if (this.serviceBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureServiceIsMutable();
                    this.service_.set(index, value);
                    this.onChanged();
                } else {
                    this.serviceBuilder_.setMessage(index, value);
                }
                return this;
            }

            public Builder setService(int index, ServiceDescriptorProto.Builder builderForValue) {
                if (this.serviceBuilder_ == null) {
                    this.ensureServiceIsMutable();
                    this.service_.set(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.serviceBuilder_.setMessage(index, builderForValue.build());
                }
                return this;
            }

            public Builder addService(ServiceDescriptorProto value) {
                if (this.serviceBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureServiceIsMutable();
                    this.service_.add(value);
                    this.onChanged();
                } else {
                    this.serviceBuilder_.addMessage(value);
                }
                return this;
            }

            public Builder addService(int index, ServiceDescriptorProto value) {
                if (this.serviceBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureServiceIsMutable();
                    this.service_.add(index, value);
                    this.onChanged();
                } else {
                    this.serviceBuilder_.addMessage(index, value);
                }
                return this;
            }

            public Builder addService(ServiceDescriptorProto.Builder builderForValue) {
                if (this.serviceBuilder_ == null) {
                    this.ensureServiceIsMutable();
                    this.service_.add(builderForValue.build());
                    this.onChanged();
                } else {
                    this.serviceBuilder_.addMessage(builderForValue.build());
                }
                return this;
            }

            public Builder addService(int index, ServiceDescriptorProto.Builder builderForValue) {
                if (this.serviceBuilder_ == null) {
                    this.ensureServiceIsMutable();
                    this.service_.add(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.serviceBuilder_.addMessage(index, builderForValue.build());
                }
                return this;
            }

            public Builder addAllService(Iterable<? extends ServiceDescriptorProto> values2) {
                if (this.serviceBuilder_ == null) {
                    this.ensureServiceIsMutable();
                    AbstractMessageLite.Builder.addAll(values2, this.service_);
                    this.onChanged();
                } else {
                    this.serviceBuilder_.addAllMessages(values2);
                }
                return this;
            }

            public Builder clearService() {
                if (this.serviceBuilder_ == null) {
                    this.service_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFFF7F;
                    this.onChanged();
                } else {
                    this.serviceBuilder_.clear();
                }
                return this;
            }

            public Builder removeService(int index) {
                if (this.serviceBuilder_ == null) {
                    this.ensureServiceIsMutable();
                    this.service_.remove(index);
                    this.onChanged();
                } else {
                    this.serviceBuilder_.remove(index);
                }
                return this;
            }

            public ServiceDescriptorProto.Builder getServiceBuilder(int index) {
                return this.getServiceFieldBuilder().getBuilder(index);
            }

            @Override
            public ServiceDescriptorProtoOrBuilder getServiceOrBuilder(int index) {
                if (this.serviceBuilder_ == null) {
                    return this.service_.get(index);
                }
                return this.serviceBuilder_.getMessageOrBuilder(index);
            }

            @Override
            public List<? extends ServiceDescriptorProtoOrBuilder> getServiceOrBuilderList() {
                if (this.serviceBuilder_ != null) {
                    return this.serviceBuilder_.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.service_);
            }

            public ServiceDescriptorProto.Builder addServiceBuilder() {
                return this.getServiceFieldBuilder().addBuilder(ServiceDescriptorProto.getDefaultInstance());
            }

            public ServiceDescriptorProto.Builder addServiceBuilder(int index) {
                return this.getServiceFieldBuilder().addBuilder(index, ServiceDescriptorProto.getDefaultInstance());
            }

            public List<ServiceDescriptorProto.Builder> getServiceBuilderList() {
                return this.getServiceFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilder<ServiceDescriptorProto, ServiceDescriptorProto.Builder, ServiceDescriptorProtoOrBuilder> getServiceFieldBuilder() {
                if (this.serviceBuilder_ == null) {
                    this.serviceBuilder_ = new RepeatedFieldBuilder(this.service_, (this.bitField0_ & 0x80) == 128, this.getParentForChildren(), this.isClean());
                    this.service_ = null;
                }
                return this.serviceBuilder_;
            }

            private void ensureExtensionIsMutable() {
                if ((this.bitField0_ & 0x100) != 256) {
                    this.extension_ = new ArrayList<FieldDescriptorProto>(this.extension_);
                    this.bitField0_ |= 0x100;
                }
            }

            @Override
            public List<FieldDescriptorProto> getExtensionList() {
                if (this.extensionBuilder_ == null) {
                    return Collections.unmodifiableList(this.extension_);
                }
                return this.extensionBuilder_.getMessageList();
            }

            @Override
            public int getExtensionCount() {
                if (this.extensionBuilder_ == null) {
                    return this.extension_.size();
                }
                return this.extensionBuilder_.getCount();
            }

            @Override
            public FieldDescriptorProto getExtension(int index) {
                if (this.extensionBuilder_ == null) {
                    return this.extension_.get(index);
                }
                return this.extensionBuilder_.getMessage(index);
            }

            public Builder setExtension(int index, FieldDescriptorProto value) {
                if (this.extensionBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureExtensionIsMutable();
                    this.extension_.set(index, value);
                    this.onChanged();
                } else {
                    this.extensionBuilder_.setMessage(index, value);
                }
                return this;
            }

            public Builder setExtension(int index, FieldDescriptorProto.Builder builderForValue) {
                if (this.extensionBuilder_ == null) {
                    this.ensureExtensionIsMutable();
                    this.extension_.set(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.extensionBuilder_.setMessage(index, builderForValue.build());
                }
                return this;
            }

            public Builder addExtension(FieldDescriptorProto value) {
                if (this.extensionBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureExtensionIsMutable();
                    this.extension_.add(value);
                    this.onChanged();
                } else {
                    this.extensionBuilder_.addMessage(value);
                }
                return this;
            }

            public Builder addExtension(int index, FieldDescriptorProto value) {
                if (this.extensionBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureExtensionIsMutable();
                    this.extension_.add(index, value);
                    this.onChanged();
                } else {
                    this.extensionBuilder_.addMessage(index, value);
                }
                return this;
            }

            public Builder addExtension(FieldDescriptorProto.Builder builderForValue) {
                if (this.extensionBuilder_ == null) {
                    this.ensureExtensionIsMutable();
                    this.extension_.add(builderForValue.build());
                    this.onChanged();
                } else {
                    this.extensionBuilder_.addMessage(builderForValue.build());
                }
                return this;
            }

            public Builder addExtension(int index, FieldDescriptorProto.Builder builderForValue) {
                if (this.extensionBuilder_ == null) {
                    this.ensureExtensionIsMutable();
                    this.extension_.add(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.extensionBuilder_.addMessage(index, builderForValue.build());
                }
                return this;
            }

            public Builder addAllExtension(Iterable<? extends FieldDescriptorProto> values2) {
                if (this.extensionBuilder_ == null) {
                    this.ensureExtensionIsMutable();
                    AbstractMessageLite.Builder.addAll(values2, this.extension_);
                    this.onChanged();
                } else {
                    this.extensionBuilder_.addAllMessages(values2);
                }
                return this;
            }

            public Builder clearExtension() {
                if (this.extensionBuilder_ == null) {
                    this.extension_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFFEFF;
                    this.onChanged();
                } else {
                    this.extensionBuilder_.clear();
                }
                return this;
            }

            public Builder removeExtension(int index) {
                if (this.extensionBuilder_ == null) {
                    this.ensureExtensionIsMutable();
                    this.extension_.remove(index);
                    this.onChanged();
                } else {
                    this.extensionBuilder_.remove(index);
                }
                return this;
            }

            public FieldDescriptorProto.Builder getExtensionBuilder(int index) {
                return this.getExtensionFieldBuilder().getBuilder(index);
            }

            @Override
            public FieldDescriptorProtoOrBuilder getExtensionOrBuilder(int index) {
                if (this.extensionBuilder_ == null) {
                    return this.extension_.get(index);
                }
                return this.extensionBuilder_.getMessageOrBuilder(index);
            }

            @Override
            public List<? extends FieldDescriptorProtoOrBuilder> getExtensionOrBuilderList() {
                if (this.extensionBuilder_ != null) {
                    return this.extensionBuilder_.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.extension_);
            }

            public FieldDescriptorProto.Builder addExtensionBuilder() {
                return this.getExtensionFieldBuilder().addBuilder(FieldDescriptorProto.getDefaultInstance());
            }

            public FieldDescriptorProto.Builder addExtensionBuilder(int index) {
                return this.getExtensionFieldBuilder().addBuilder(index, FieldDescriptorProto.getDefaultInstance());
            }

            public List<FieldDescriptorProto.Builder> getExtensionBuilderList() {
                return this.getExtensionFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilder<FieldDescriptorProto, FieldDescriptorProto.Builder, FieldDescriptorProtoOrBuilder> getExtensionFieldBuilder() {
                if (this.extensionBuilder_ == null) {
                    this.extensionBuilder_ = new RepeatedFieldBuilder(this.extension_, (this.bitField0_ & 0x100) == 256, this.getParentForChildren(), this.isClean());
                    this.extension_ = null;
                }
                return this.extensionBuilder_;
            }

            @Override
            public boolean hasOptions() {
                return (this.bitField0_ & 0x200) == 512;
            }

            @Override
            public FileOptions getOptions() {
                if (this.optionsBuilder_ == null) {
                    return this.options_;
                }
                return this.optionsBuilder_.getMessage();
            }

            public Builder setOptions(FileOptions value) {
                if (this.optionsBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.options_ = value;
                    this.onChanged();
                } else {
                    this.optionsBuilder_.setMessage(value);
                }
                this.bitField0_ |= 0x200;
                return this;
            }

            public Builder setOptions(FileOptions.Builder builderForValue) {
                if (this.optionsBuilder_ == null) {
                    this.options_ = builderForValue.build();
                    this.onChanged();
                } else {
                    this.optionsBuilder_.setMessage(builderForValue.build());
                }
                this.bitField0_ |= 0x200;
                return this;
            }

            public Builder mergeOptions(FileOptions value) {
                if (this.optionsBuilder_ == null) {
                    this.options_ = (this.bitField0_ & 0x200) == 512 && this.options_ != FileOptions.getDefaultInstance() ? FileOptions.newBuilder(this.options_).mergeFrom(value).buildPartial() : value;
                    this.onChanged();
                } else {
                    this.optionsBuilder_.mergeFrom(value);
                }
                this.bitField0_ |= 0x200;
                return this;
            }

            public Builder clearOptions() {
                if (this.optionsBuilder_ == null) {
                    this.options_ = FileOptions.getDefaultInstance();
                    this.onChanged();
                } else {
                    this.optionsBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFDFF;
                return this;
            }

            public FileOptions.Builder getOptionsBuilder() {
                this.bitField0_ |= 0x200;
                this.onChanged();
                return this.getOptionsFieldBuilder().getBuilder();
            }

            @Override
            public FileOptionsOrBuilder getOptionsOrBuilder() {
                if (this.optionsBuilder_ != null) {
                    return this.optionsBuilder_.getMessageOrBuilder();
                }
                return this.options_;
            }

            private SingleFieldBuilder<FileOptions, FileOptions.Builder, FileOptionsOrBuilder> getOptionsFieldBuilder() {
                if (this.optionsBuilder_ == null) {
                    this.optionsBuilder_ = new SingleFieldBuilder(this.getOptions(), this.getParentForChildren(), this.isClean());
                    this.options_ = null;
                }
                return this.optionsBuilder_;
            }

            @Override
            public boolean hasSourceCodeInfo() {
                return (this.bitField0_ & 0x400) == 1024;
            }

            @Override
            public SourceCodeInfo getSourceCodeInfo() {
                if (this.sourceCodeInfoBuilder_ == null) {
                    return this.sourceCodeInfo_;
                }
                return this.sourceCodeInfoBuilder_.getMessage();
            }

            public Builder setSourceCodeInfo(SourceCodeInfo value) {
                if (this.sourceCodeInfoBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.sourceCodeInfo_ = value;
                    this.onChanged();
                } else {
                    this.sourceCodeInfoBuilder_.setMessage(value);
                }
                this.bitField0_ |= 0x400;
                return this;
            }

            public Builder setSourceCodeInfo(SourceCodeInfo.Builder builderForValue) {
                if (this.sourceCodeInfoBuilder_ == null) {
                    this.sourceCodeInfo_ = builderForValue.build();
                    this.onChanged();
                } else {
                    this.sourceCodeInfoBuilder_.setMessage(builderForValue.build());
                }
                this.bitField0_ |= 0x400;
                return this;
            }

            public Builder mergeSourceCodeInfo(SourceCodeInfo value) {
                if (this.sourceCodeInfoBuilder_ == null) {
                    this.sourceCodeInfo_ = (this.bitField0_ & 0x400) == 1024 && this.sourceCodeInfo_ != SourceCodeInfo.getDefaultInstance() ? SourceCodeInfo.newBuilder(this.sourceCodeInfo_).mergeFrom(value).buildPartial() : value;
                    this.onChanged();
                } else {
                    this.sourceCodeInfoBuilder_.mergeFrom(value);
                }
                this.bitField0_ |= 0x400;
                return this;
            }

            public Builder clearSourceCodeInfo() {
                if (this.sourceCodeInfoBuilder_ == null) {
                    this.sourceCodeInfo_ = SourceCodeInfo.getDefaultInstance();
                    this.onChanged();
                } else {
                    this.sourceCodeInfoBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFBFF;
                return this;
            }

            public SourceCodeInfo.Builder getSourceCodeInfoBuilder() {
                this.bitField0_ |= 0x400;
                this.onChanged();
                return this.getSourceCodeInfoFieldBuilder().getBuilder();
            }

            @Override
            public SourceCodeInfoOrBuilder getSourceCodeInfoOrBuilder() {
                if (this.sourceCodeInfoBuilder_ != null) {
                    return this.sourceCodeInfoBuilder_.getMessageOrBuilder();
                }
                return this.sourceCodeInfo_;
            }

            private SingleFieldBuilder<SourceCodeInfo, SourceCodeInfo.Builder, SourceCodeInfoOrBuilder> getSourceCodeInfoFieldBuilder() {
                if (this.sourceCodeInfoBuilder_ == null) {
                    this.sourceCodeInfoBuilder_ = new SingleFieldBuilder(this.getSourceCodeInfo(), this.getParentForChildren(), this.isClean());
                    this.sourceCodeInfo_ = null;
                }
                return this.sourceCodeInfoBuilder_;
            }
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static interface FileDescriptorProtoOrBuilder
    extends MessageOrBuilder {
        public boolean hasName();

        public String getName();

        public ByteString getNameBytes();

        public boolean hasPackage();

        public String getPackage();

        public ByteString getPackageBytes();

        public ProtocolStringList getDependencyList();

        public int getDependencyCount();

        public String getDependency(int var1);

        public ByteString getDependencyBytes(int var1);

        public List<Integer> getPublicDependencyList();

        public int getPublicDependencyCount();

        public int getPublicDependency(int var1);

        public List<Integer> getWeakDependencyList();

        public int getWeakDependencyCount();

        public int getWeakDependency(int var1);

        public List<DescriptorProto> getMessageTypeList();

        public DescriptorProto getMessageType(int var1);

        public int getMessageTypeCount();

        public List<? extends DescriptorProtoOrBuilder> getMessageTypeOrBuilderList();

        public DescriptorProtoOrBuilder getMessageTypeOrBuilder(int var1);

        public List<EnumDescriptorProto> getEnumTypeList();

        public EnumDescriptorProto getEnumType(int var1);

        public int getEnumTypeCount();

        public List<? extends EnumDescriptorProtoOrBuilder> getEnumTypeOrBuilderList();

        public EnumDescriptorProtoOrBuilder getEnumTypeOrBuilder(int var1);

        public List<ServiceDescriptorProto> getServiceList();

        public ServiceDescriptorProto getService(int var1);

        public int getServiceCount();

        public List<? extends ServiceDescriptorProtoOrBuilder> getServiceOrBuilderList();

        public ServiceDescriptorProtoOrBuilder getServiceOrBuilder(int var1);

        public List<FieldDescriptorProto> getExtensionList();

        public FieldDescriptorProto getExtension(int var1);

        public int getExtensionCount();

        public List<? extends FieldDescriptorProtoOrBuilder> getExtensionOrBuilderList();

        public FieldDescriptorProtoOrBuilder getExtensionOrBuilder(int var1);

        public boolean hasOptions();

        public FileOptions getOptions();

        public FileOptionsOrBuilder getOptionsOrBuilder();

        public boolean hasSourceCodeInfo();

        public SourceCodeInfo getSourceCodeInfo();

        public SourceCodeInfoOrBuilder getSourceCodeInfoOrBuilder();
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static final class FileDescriptorSet
    extends GeneratedMessage
    implements FileDescriptorSetOrBuilder {
        private static final FileDescriptorSet defaultInstance;
        private final UnknownFieldSet unknownFields;
        public static Parser<FileDescriptorSet> PARSER;
        public static final int FILE_FIELD_NUMBER = 1;
        private List<FileDescriptorProto> file_;
        private byte memoizedIsInitialized = (byte)-1;
        private int memoizedSerializedSize = -1;
        private static final long serialVersionUID = 0L;

        private FileDescriptorSet(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.unknownFields = builder.getUnknownFields();
        }

        private FileDescriptorSet(boolean noInit) {
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static FileDescriptorSet getDefaultInstance() {
            return defaultInstance;
        }

        @Override
        public FileDescriptorSet getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private FileDescriptorSet(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this.initFields();
            boolean mutable_bitField0_ = false;
            UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
            try {
                boolean done = false;
                block10: while (!done) {
                    int tag = input.readTag();
                    switch (tag) {
                        case 0: {
                            done = true;
                            continue block10;
                        }
                        default: {
                            if (this.parseUnknownField(input, unknownFields, extensionRegistry, tag)) continue block10;
                            done = true;
                            continue block10;
                        }
                        case 10: 
                    }
                    if (!(mutable_bitField0_ & true)) {
                        this.file_ = new ArrayList<FileDescriptorProto>();
                        mutable_bitField0_ |= true;
                    }
                    this.file_.add(input.readMessage(FileDescriptorProto.PARSER, extensionRegistry));
                }
            } catch (InvalidProtocolBufferException e) {
                throw e.setUnfinishedMessage(this);
            } catch (IOException e) {
                throw new InvalidProtocolBufferException(e.getMessage()).setUnfinishedMessage(this);
            } finally {
                if (mutable_bitField0_ & true) {
                    this.file_ = Collections.unmodifiableList(this.file_);
                }
                this.unknownFields = unknownFields.build();
                this.makeExtensionsImmutable();
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return internal_static_google_protobuf_FileDescriptorSet_descriptor;
        }

        @Override
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return internal_static_google_protobuf_FileDescriptorSet_fieldAccessorTable.ensureFieldAccessorsInitialized(FileDescriptorSet.class, Builder.class);
        }

        public Parser<FileDescriptorSet> getParserForType() {
            return PARSER;
        }

        @Override
        public List<FileDescriptorProto> getFileList() {
            return this.file_;
        }

        @Override
        public List<? extends FileDescriptorProtoOrBuilder> getFileOrBuilderList() {
            return this.file_;
        }

        @Override
        public int getFileCount() {
            return this.file_.size();
        }

        @Override
        public FileDescriptorProto getFile(int index) {
            return this.file_.get(index);
        }

        @Override
        public FileDescriptorProtoOrBuilder getFileOrBuilder(int index) {
            return this.file_.get(index);
        }

        private void initFields() {
            this.file_ = Collections.emptyList();
        }

        @Override
        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            for (int i = 0; i < this.getFileCount(); ++i) {
                if (this.getFile(i).isInitialized()) continue;
                this.memoizedIsInitialized = 0;
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        @Override
        public void writeTo(CodedOutputStream output) throws IOException {
            this.getSerializedSize();
            for (int i = 0; i < this.file_.size(); ++i) {
                output.writeMessage(1, this.file_.get(i));
            }
            this.getUnknownFields().writeTo(output);
        }

        @Override
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            size = 0;
            for (int i = 0; i < this.file_.size(); ++i) {
                size += CodedOutputStream.computeMessageSize(1, this.file_.get(i));
            }
            this.memoizedSerializedSize = size += this.getUnknownFields().getSerializedSize();
            return size;
        }

        @Override
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static FileDescriptorSet parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static FileDescriptorSet parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static FileDescriptorSet parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static FileDescriptorSet parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static FileDescriptorSet parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static FileDescriptorSet parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static FileDescriptorSet parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static FileDescriptorSet parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static FileDescriptorSet parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static FileDescriptorSet parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override
        public Builder newBuilderForType() {
            return FileDescriptorSet.newBuilder();
        }

        public static Builder newBuilder(FileDescriptorSet prototype) {
            return FileDescriptorSet.newBuilder().mergeFrom(prototype);
        }

        @Override
        public Builder toBuilder() {
            return FileDescriptorSet.newBuilder(this);
        }

        @Override
        protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        static {
            PARSER = new AbstractParser<FileDescriptorSet>(){

                @Override
                public FileDescriptorSet parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                    return new FileDescriptorSet(input, extensionRegistry);
                }
            };
            defaultInstance = new FileDescriptorSet(true);
            defaultInstance.initFields();
        }

        /*
         * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
         */
        public static final class Builder
        extends GeneratedMessage.Builder<Builder>
        implements FileDescriptorSetOrBuilder {
            private int bitField0_;
            private List<FileDescriptorProto> file_ = Collections.emptyList();
            private RepeatedFieldBuilder<FileDescriptorProto, FileDescriptorProto.Builder, FileDescriptorProtoOrBuilder> fileBuilder_;

            public static final Descriptors.Descriptor getDescriptor() {
                return internal_static_google_protobuf_FileDescriptorSet_descriptor;
            }

            @Override
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return internal_static_google_protobuf_FileDescriptorSet_fieldAccessorTable.ensureFieldAccessorsInitialized(FileDescriptorSet.class, Builder.class);
            }

            private Builder() {
                this.maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessage.BuilderParent parent) {
                super(parent);
                this.maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                if (GeneratedMessage.alwaysUseFieldBuilders) {
                    this.getFileFieldBuilder();
                }
            }

            private static Builder create() {
                return new Builder();
            }

            @Override
            public Builder clear() {
                super.clear();
                if (this.fileBuilder_ == null) {
                    this.file_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFFFFE;
                } else {
                    this.fileBuilder_.clear();
                }
                return this;
            }

            @Override
            public Builder clone() {
                return Builder.create().mergeFrom(this.buildPartial());
            }

            @Override
            public Descriptors.Descriptor getDescriptorForType() {
                return internal_static_google_protobuf_FileDescriptorSet_descriptor;
            }

            @Override
            public FileDescriptorSet getDefaultInstanceForType() {
                return FileDescriptorSet.getDefaultInstance();
            }

            @Override
            public FileDescriptorSet build() {
                FileDescriptorSet result = this.buildPartial();
                if (!result.isInitialized()) {
                    throw Builder.newUninitializedMessageException(result);
                }
                return result;
            }

            @Override
            public FileDescriptorSet buildPartial() {
                FileDescriptorSet result = new FileDescriptorSet(this);
                int from_bitField0_ = this.bitField0_;
                if (this.fileBuilder_ == null) {
                    if ((this.bitField0_ & 1) == 1) {
                        this.file_ = Collections.unmodifiableList(this.file_);
                        this.bitField0_ &= 0xFFFFFFFE;
                    }
                    result.file_ = this.file_;
                } else {
                    result.file_ = this.fileBuilder_.build();
                }
                this.onBuilt();
                return result;
            }

            @Override
            public Builder mergeFrom(Message other) {
                if (other instanceof FileDescriptorSet) {
                    return this.mergeFrom((FileDescriptorSet)other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(FileDescriptorSet other) {
                if (other == FileDescriptorSet.getDefaultInstance()) {
                    return this;
                }
                if (this.fileBuilder_ == null) {
                    if (!other.file_.isEmpty()) {
                        if (this.file_.isEmpty()) {
                            this.file_ = other.file_;
                            this.bitField0_ &= 0xFFFFFFFE;
                        } else {
                            this.ensureFileIsMutable();
                            this.file_.addAll(other.file_);
                        }
                        this.onChanged();
                    }
                } else if (!other.file_.isEmpty()) {
                    if (this.fileBuilder_.isEmpty()) {
                        this.fileBuilder_.dispose();
                        this.fileBuilder_ = null;
                        this.file_ = other.file_;
                        this.bitField0_ &= 0xFFFFFFFE;
                        this.fileBuilder_ = GeneratedMessage.alwaysUseFieldBuilders ? this.getFileFieldBuilder() : null;
                    } else {
                        this.fileBuilder_.addAllMessages(other.file_);
                    }
                }
                this.mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            @Override
            public final boolean isInitialized() {
                for (int i = 0; i < this.getFileCount(); ++i) {
                    if (this.getFile(i).isInitialized()) continue;
                    return false;
                }
                return true;
            }

            @Override
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                FileDescriptorSet parsedMessage = null;
                try {
                    parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
                } catch (InvalidProtocolBufferException e) {
                    parsedMessage = (FileDescriptorSet)e.getUnfinishedMessage();
                    throw e;
                } finally {
                    if (parsedMessage != null) {
                        this.mergeFrom(parsedMessage);
                    }
                }
                return this;
            }

            private void ensureFileIsMutable() {
                if ((this.bitField0_ & 1) != 1) {
                    this.file_ = new ArrayList<FileDescriptorProto>(this.file_);
                    this.bitField0_ |= 1;
                }
            }

            @Override
            public List<FileDescriptorProto> getFileList() {
                if (this.fileBuilder_ == null) {
                    return Collections.unmodifiableList(this.file_);
                }
                return this.fileBuilder_.getMessageList();
            }

            @Override
            public int getFileCount() {
                if (this.fileBuilder_ == null) {
                    return this.file_.size();
                }
                return this.fileBuilder_.getCount();
            }

            @Override
            public FileDescriptorProto getFile(int index) {
                if (this.fileBuilder_ == null) {
                    return this.file_.get(index);
                }
                return this.fileBuilder_.getMessage(index);
            }

            public Builder setFile(int index, FileDescriptorProto value) {
                if (this.fileBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureFileIsMutable();
                    this.file_.set(index, value);
                    this.onChanged();
                } else {
                    this.fileBuilder_.setMessage(index, value);
                }
                return this;
            }

            public Builder setFile(int index, FileDescriptorProto.Builder builderForValue) {
                if (this.fileBuilder_ == null) {
                    this.ensureFileIsMutable();
                    this.file_.set(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.fileBuilder_.setMessage(index, builderForValue.build());
                }
                return this;
            }

            public Builder addFile(FileDescriptorProto value) {
                if (this.fileBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureFileIsMutable();
                    this.file_.add(value);
                    this.onChanged();
                } else {
                    this.fileBuilder_.addMessage(value);
                }
                return this;
            }

            public Builder addFile(int index, FileDescriptorProto value) {
                if (this.fileBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.ensureFileIsMutable();
                    this.file_.add(index, value);
                    this.onChanged();
                } else {
                    this.fileBuilder_.addMessage(index, value);
                }
                return this;
            }

            public Builder addFile(FileDescriptorProto.Builder builderForValue) {
                if (this.fileBuilder_ == null) {
                    this.ensureFileIsMutable();
                    this.file_.add(builderForValue.build());
                    this.onChanged();
                } else {
                    this.fileBuilder_.addMessage(builderForValue.build());
                }
                return this;
            }

            public Builder addFile(int index, FileDescriptorProto.Builder builderForValue) {
                if (this.fileBuilder_ == null) {
                    this.ensureFileIsMutable();
                    this.file_.add(index, builderForValue.build());
                    this.onChanged();
                } else {
                    this.fileBuilder_.addMessage(index, builderForValue.build());
                }
                return this;
            }

            public Builder addAllFile(Iterable<? extends FileDescriptorProto> values2) {
                if (this.fileBuilder_ == null) {
                    this.ensureFileIsMutable();
                    AbstractMessageLite.Builder.addAll(values2, this.file_);
                    this.onChanged();
                } else {
                    this.fileBuilder_.addAllMessages(values2);
                }
                return this;
            }

            public Builder clearFile() {
                if (this.fileBuilder_ == null) {
                    this.file_ = Collections.emptyList();
                    this.bitField0_ &= 0xFFFFFFFE;
                    this.onChanged();
                } else {
                    this.fileBuilder_.clear();
                }
                return this;
            }

            public Builder removeFile(int index) {
                if (this.fileBuilder_ == null) {
                    this.ensureFileIsMutable();
                    this.file_.remove(index);
                    this.onChanged();
                } else {
                    this.fileBuilder_.remove(index);
                }
                return this;
            }

            public FileDescriptorProto.Builder getFileBuilder(int index) {
                return this.getFileFieldBuilder().getBuilder(index);
            }

            @Override
            public FileDescriptorProtoOrBuilder getFileOrBuilder(int index) {
                if (this.fileBuilder_ == null) {
                    return this.file_.get(index);
                }
                return this.fileBuilder_.getMessageOrBuilder(index);
            }

            @Override
            public List<? extends FileDescriptorProtoOrBuilder> getFileOrBuilderList() {
                if (this.fileBuilder_ != null) {
                    return this.fileBuilder_.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.file_);
            }

            public FileDescriptorProto.Builder addFileBuilder() {
                return this.getFileFieldBuilder().addBuilder(FileDescriptorProto.getDefaultInstance());
            }

            public FileDescriptorProto.Builder addFileBuilder(int index) {
                return this.getFileFieldBuilder().addBuilder(index, FileDescriptorProto.getDefaultInstance());
            }

            public List<FileDescriptorProto.Builder> getFileBuilderList() {
                return this.getFileFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilder<FileDescriptorProto, FileDescriptorProto.Builder, FileDescriptorProtoOrBuilder> getFileFieldBuilder() {
                if (this.fileBuilder_ == null) {
                    this.fileBuilder_ = new RepeatedFieldBuilder(this.file_, (this.bitField0_ & 1) == 1, this.getParentForChildren(), this.isClean());
                    this.file_ = null;
                }
                return this.fileBuilder_;
            }
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static interface FileDescriptorSetOrBuilder
    extends MessageOrBuilder {
        public List<FileDescriptorProto> getFileList();

        public FileDescriptorProto getFile(int var1);

        public int getFileCount();

        public List<? extends FileDescriptorProtoOrBuilder> getFileOrBuilderList();

        public FileDescriptorProtoOrBuilder getFileOrBuilder(int var1);
    }
}

