/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.asm.ClassWriter;
import com.alibaba.fastjson.asm.FieldWriter;
import com.alibaba.fastjson.asm.Label;
import com.alibaba.fastjson.asm.MethodVisitor;
import com.alibaba.fastjson.asm.MethodWriter;
import com.alibaba.fastjson.asm.Opcodes;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.BeanContext;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.JavaBeanSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerialContext;
import com.alibaba.fastjson.serializer.SerializeBeanInfo;
import com.alibaba.fastjson.serializer.SerializeFilterable;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.util.ASMClassLoader;
import com.alibaba.fastjson.util.ASMUtils;
import com.alibaba.fastjson.util.FieldInfo;
import com.alibaba.fastjson.util.TypeUtils;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class ASMSerializerFactory
implements Opcodes {
    protected final ASMClassLoader classLoader = new ASMClassLoader();
    private final AtomicLong seed = new AtomicLong();
    static final String JSONSerializer = ASMUtils.type(JSONSerializer.class);
    static final String ObjectSerializer = ASMUtils.type(ObjectSerializer.class);
    static final String ObjectSerializer_desc = "L" + ObjectSerializer + ";";
    static final String SerializeWriter = ASMUtils.type(SerializeWriter.class);
    static final String SerializeWriter_desc = "L" + SerializeWriter + ";";
    static final String JavaBeanSerializer = ASMUtils.type(JavaBeanSerializer.class);
    static final String JavaBeanSerializer_desc = "L" + ASMUtils.type(JavaBeanSerializer.class) + ";";
    static final String SerialContext_desc = ASMUtils.desc(SerialContext.class);
    static final String SerializeFilterable_desc = ASMUtils.desc(SerializeFilterable.class);

    public JavaBeanSerializer createJavaBeanSerializer(SerializeBeanInfo beanInfo) throws Exception {
        Context context;
        boolean nonContext;
        String classNameFull;
        String classNameType;
        boolean nativeSorted;
        FieldInfo[] unsortedGetters;
        Class<?> clazz = beanInfo.beanType;
        if (clazz.isPrimitive()) {
            throw new JSONException("unsupportd class " + clazz.getName());
        }
        JSONType jsonType = TypeUtils.getAnnotation(clazz, JSONType.class);
        for (FieldInfo fieldInfo : unsortedGetters = beanInfo.fields) {
            if (fieldInfo.field != null || fieldInfo.method == null || !fieldInfo.method.getDeclaringClass().isInterface()) continue;
            return new JavaBeanSerializer(beanInfo);
        }
        FieldInfo[] getters = beanInfo.sortedFields;
        boolean bl = nativeSorted = beanInfo.sortedFields == beanInfo.fields;
        if (getters.length > 256) {
            return new JavaBeanSerializer(beanInfo);
        }
        for (FieldInfo getter : getters) {
            if (ASMUtils.checkName(getter.getMember().getName())) continue;
            return new JavaBeanSerializer(beanInfo);
        }
        String className = "ASMSerializer_" + this.seed.incrementAndGet() + "_" + clazz.getSimpleName();
        Package pkg = ASMSerializerFactory.class.getPackage();
        if (pkg != null) {
            String packageName = pkg.getName();
            classNameType = packageName.replace('.', '/') + "/" + className;
            classNameFull = packageName + "." + className;
        } else {
            classNameType = className;
            classNameFull = className;
        }
        ClassWriter cw = new ClassWriter();
        cw.visit(49, 33, classNameType, JavaBeanSerializer, new String[]{ObjectSerializer});
        for (FieldInfo fieldInfo : getters) {
            if (fieldInfo.fieldClass.isPrimitive() || fieldInfo.fieldClass == String.class) continue;
            new FieldWriter(cw, 1, fieldInfo.name + "_asm_fieldType", "Ljava/lang/reflect/Type;").visitEnd();
            if (List.class.isAssignableFrom(fieldInfo.fieldClass)) {
                new FieldWriter(cw, 1, fieldInfo.name + "_asm_list_item_ser_", ObjectSerializer_desc).visitEnd();
            }
            new FieldWriter(cw, 1, fieldInfo.name + "_asm_ser_", ObjectSerializer_desc).visitEnd();
        }
        MethodWriter mw = new MethodWriter(cw, 1, "<init>", "(" + ASMUtils.desc(SerializeBeanInfo.class) + ")V", null, null);
        mw.visitVarInsn(25, 0);
        mw.visitVarInsn(25, 1);
        mw.visitMethodInsn(183, JavaBeanSerializer, "<init>", "(" + ASMUtils.desc(SerializeBeanInfo.class) + ")V");
        for (int i = 0; i < getters.length; ++i) {
            FieldInfo fieldInfo = getters[i];
            if (fieldInfo.fieldClass.isPrimitive() || fieldInfo.fieldClass == String.class) continue;
            mw.visitVarInsn(25, 0);
            if (fieldInfo.method != null) {
                mw.visitLdcInsn(com.alibaba.fastjson.asm.Type.getType(ASMUtils.desc(fieldInfo.declaringClass)));
                mw.visitLdcInsn(fieldInfo.method.getName());
                mw.visitMethodInsn(184, ASMUtils.type(ASMUtils.class), "getMethodType", "(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/reflect/Type;");
            } else {
                mw.visitVarInsn(25, 0);
                mw.visitLdcInsn(i);
                mw.visitMethodInsn(183, JavaBeanSerializer, "getFieldType", "(I)Ljava/lang/reflect/Type;");
            }
            mw.visitFieldInsn(181, classNameType, fieldInfo.name + "_asm_fieldType", "Ljava/lang/reflect/Type;");
        }
        mw.visitInsn(177);
        mw.visitMaxs(4, 4);
        mw.visitEnd();
        boolean DisableCircularReferenceDetect = false;
        if (jsonType != null) {
            for (SerializerFeature featrues : jsonType.serialzeFeatures()) {
                if (featrues != SerializerFeature.DisableCircularReferenceDetect) continue;
                DisableCircularReferenceDetect = true;
                break;
            }
        }
        for (int i = 0; i < 3; ++i) {
            String methodName;
            nonContext = DisableCircularReferenceDetect;
            boolean writeDirect = false;
            if (i == 0) {
                methodName = "write";
                writeDirect = true;
            } else if (i == 1) {
                methodName = "writeNormal";
            } else {
                writeDirect = true;
                nonContext = true;
                methodName = "writeDirectNonContext";
            }
            context = new Context(getters, beanInfo, classNameType, writeDirect, nonContext);
            mw = new MethodWriter(cw, 1, methodName, "(L" + JSONSerializer + ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V", null, new String[]{"java/io/IOException"});
            Label endIf_ = new Label();
            mw.visitVarInsn(25, 2);
            mw.visitJumpInsn(199, endIf_);
            mw.visitVarInsn(25, 1);
            mw.visitMethodInsn(182, JSONSerializer, "writeNull", "()V");
            mw.visitInsn(177);
            mw.visitLabel(endIf_);
            mw.visitVarInsn(25, 1);
            mw.visitFieldInsn(180, JSONSerializer, "out", SerializeWriter_desc);
            mw.visitVarInsn(58, context.var("out"));
            if (!(nativeSorted || context.writeDirect || jsonType != null && !jsonType.alphabetic())) {
                Label _else = new Label();
                mw.visitVarInsn(25, context.var("out"));
                mw.visitMethodInsn(182, SerializeWriter, "isSortField", "()Z");
                mw.visitJumpInsn(154, _else);
                mw.visitVarInsn(25, 0);
                mw.visitVarInsn(25, 1);
                mw.visitVarInsn(25, 2);
                mw.visitVarInsn(25, 3);
                mw.visitVarInsn(25, 4);
                mw.visitVarInsn(21, 5);
                mw.visitMethodInsn(182, classNameType, "writeUnsorted", "(L" + JSONSerializer + ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
                mw.visitInsn(177);
                mw.visitLabel(_else);
            }
            if (context.writeDirect && !nonContext) {
                Label _direct = new Label();
                Label _directElse = new Label();
                mw.visitVarInsn(25, 0);
                mw.visitVarInsn(25, 1);
                mw.visitMethodInsn(182, JavaBeanSerializer, "writeDirect", "(L" + JSONSerializer + ";)Z");
                mw.visitJumpInsn(154, _directElse);
                mw.visitVarInsn(25, 0);
                mw.visitVarInsn(25, 1);
                mw.visitVarInsn(25, 2);
                mw.visitVarInsn(25, 3);
                mw.visitVarInsn(25, 4);
                mw.visitVarInsn(21, 5);
                mw.visitMethodInsn(182, classNameType, "writeNormal", "(L" + JSONSerializer + ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
                mw.visitInsn(177);
                mw.visitLabel(_directElse);
                mw.visitVarInsn(25, context.var("out"));
                mw.visitLdcInsn(SerializerFeature.DisableCircularReferenceDetect.mask);
                mw.visitMethodInsn(182, SerializeWriter, "isEnabled", "(I)Z");
                mw.visitJumpInsn(153, _direct);
                mw.visitVarInsn(25, 0);
                mw.visitVarInsn(25, 1);
                mw.visitVarInsn(25, 2);
                mw.visitVarInsn(25, 3);
                mw.visitVarInsn(25, 4);
                mw.visitVarInsn(21, 5);
                mw.visitMethodInsn(182, classNameType, "writeDirectNonContext", "(L" + JSONSerializer + ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
                mw.visitInsn(177);
                mw.visitLabel(_direct);
            }
            mw.visitVarInsn(25, 2);
            mw.visitTypeInsn(192, ASMUtils.type(clazz));
            mw.visitVarInsn(58, context.var("entity"));
            this.generateWriteMethod(clazz, mw, getters, context);
            mw.visitInsn(177);
            mw.visitMaxs(7, context.variantIndex + 2);
            mw.visitEnd();
        }
        if (!nativeSorted) {
            Context context2 = new Context(getters, beanInfo, classNameType, false, DisableCircularReferenceDetect);
            mw = new MethodWriter(cw, 1, "writeUnsorted", "(L" + JSONSerializer + ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V", null, new String[]{"java/io/IOException"});
            mw.visitVarInsn(25, 1);
            mw.visitFieldInsn(180, JSONSerializer, "out", SerializeWriter_desc);
            mw.visitVarInsn(58, context2.var("out"));
            mw.visitVarInsn(25, 2);
            mw.visitTypeInsn(192, ASMUtils.type(clazz));
            mw.visitVarInsn(58, context2.var("entity"));
            this.generateWriteMethod(clazz, mw, unsortedGetters, context2);
            mw.visitInsn(177);
            mw.visitMaxs(7, context2.variantIndex + 2);
            mw.visitEnd();
        }
        for (int i = 0; i < 3; ++i) {
            String methodName;
            nonContext = DisableCircularReferenceDetect;
            boolean writeDirect = false;
            if (i == 0) {
                methodName = "writeAsArray";
                writeDirect = true;
            } else if (i == 1) {
                methodName = "writeAsArrayNormal";
            } else {
                writeDirect = true;
                nonContext = true;
                methodName = "writeAsArrayNonContext";
            }
            context = new Context(getters, beanInfo, classNameType, writeDirect, nonContext);
            mw = new MethodWriter(cw, 1, methodName, "(L" + JSONSerializer + ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V", null, new String[]{"java/io/IOException"});
            mw.visitVarInsn(25, 1);
            mw.visitFieldInsn(180, JSONSerializer, "out", SerializeWriter_desc);
            mw.visitVarInsn(58, context.var("out"));
            mw.visitVarInsn(25, 2);
            mw.visitTypeInsn(192, ASMUtils.type(clazz));
            mw.visitVarInsn(58, context.var("entity"));
            this.generateWriteAsArray(clazz, mw, getters, context);
            mw.visitInsn(177);
            mw.visitMaxs(7, context.variantIndex + 2);
            mw.visitEnd();
        }
        byte[] code = cw.toByteArray();
        Class<?> serializerClass = this.classLoader.defineClassPublic(classNameFull, code, 0, code.length);
        Constructor<?> constructor = serializerClass.getConstructor(SerializeBeanInfo.class);
        Object instance = constructor.newInstance(beanInfo);
        return (JavaBeanSerializer)instance;
    }

    private void generateWriteAsArray(Class<?> clazz, MethodVisitor mw, FieldInfo[] getters, Context context) throws Exception {
        Label nonPropertyFilters_ = new Label();
        mw.visitVarInsn(25, 1);
        mw.visitVarInsn(25, 0);
        mw.visitMethodInsn(182, JSONSerializer, "hasPropertyFilters", "(" + SerializeFilterable_desc + ")Z");
        mw.visitJumpInsn(154, nonPropertyFilters_);
        mw.visitVarInsn(25, 0);
        mw.visitVarInsn(25, 1);
        mw.visitVarInsn(25, 2);
        mw.visitVarInsn(25, 3);
        mw.visitVarInsn(25, 4);
        mw.visitVarInsn(21, 5);
        mw.visitMethodInsn(183, JavaBeanSerializer, "writeNoneASM", "(L" + JSONSerializer + ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
        mw.visitInsn(177);
        mw.visitLabel(nonPropertyFilters_);
        mw.visitVarInsn(25, context.var("out"));
        mw.visitVarInsn(16, 91);
        mw.visitMethodInsn(182, SerializeWriter, "write", "(I)V");
        int size = getters.length;
        if (size == 0) {
            mw.visitVarInsn(25, context.var("out"));
            mw.visitVarInsn(16, 93);
            mw.visitMethodInsn(182, SerializeWriter, "write", "(I)V");
            return;
        }
        for (int i = 0; i < size; ++i) {
            int seperator = i == size - 1 ? 93 : 44;
            FieldInfo fieldInfo = getters[i];
            Class<?> fieldClass = fieldInfo.fieldClass;
            mw.visitLdcInsn(fieldInfo.name);
            mw.visitVarInsn(58, Context.fieldName);
            if (fieldClass == Byte.TYPE || fieldClass == Short.TYPE || fieldClass == Integer.TYPE) {
                mw.visitVarInsn(25, context.var("out"));
                mw.visitInsn(89);
                this._get(mw, context, fieldInfo);
                mw.visitMethodInsn(182, SerializeWriter, "writeInt", "(I)V");
                mw.visitVarInsn(16, seperator);
                mw.visitMethodInsn(182, SerializeWriter, "write", "(I)V");
                continue;
            }
            if (fieldClass == Long.TYPE) {
                mw.visitVarInsn(25, context.var("out"));
                mw.visitInsn(89);
                this._get(mw, context, fieldInfo);
                mw.visitMethodInsn(182, SerializeWriter, "writeLong", "(J)V");
                mw.visitVarInsn(16, seperator);
                mw.visitMethodInsn(182, SerializeWriter, "write", "(I)V");
                continue;
            }
            if (fieldClass == Float.TYPE) {
                mw.visitVarInsn(25, context.var("out"));
                mw.visitInsn(89);
                this._get(mw, context, fieldInfo);
                mw.visitInsn(4);
                mw.visitMethodInsn(182, SerializeWriter, "writeFloat", "(FZ)V");
                mw.visitVarInsn(16, seperator);
                mw.visitMethodInsn(182, SerializeWriter, "write", "(I)V");
                continue;
            }
            if (fieldClass == Double.TYPE) {
                mw.visitVarInsn(25, context.var("out"));
                mw.visitInsn(89);
                this._get(mw, context, fieldInfo);
                mw.visitInsn(4);
                mw.visitMethodInsn(182, SerializeWriter, "writeDouble", "(DZ)V");
                mw.visitVarInsn(16, seperator);
                mw.visitMethodInsn(182, SerializeWriter, "write", "(I)V");
                continue;
            }
            if (fieldClass == Boolean.TYPE) {
                mw.visitVarInsn(25, context.var("out"));
                mw.visitInsn(89);
                this._get(mw, context, fieldInfo);
                mw.visitMethodInsn(182, SerializeWriter, "write", "(Z)V");
                mw.visitVarInsn(16, seperator);
                mw.visitMethodInsn(182, SerializeWriter, "write", "(I)V");
                continue;
            }
            if (fieldClass == Character.TYPE) {
                mw.visitVarInsn(25, context.var("out"));
                this._get(mw, context, fieldInfo);
                mw.visitMethodInsn(184, "java/lang/Character", "toString", "(C)Ljava/lang/String;");
                mw.visitVarInsn(16, seperator);
                mw.visitMethodInsn(182, SerializeWriter, "writeString", "(Ljava/lang/String;C)V");
                continue;
            }
            if (fieldClass == String.class) {
                mw.visitVarInsn(25, context.var("out"));
                this._get(mw, context, fieldInfo);
                mw.visitVarInsn(16, seperator);
                mw.visitMethodInsn(182, SerializeWriter, "writeString", "(Ljava/lang/String;C)V");
                continue;
            }
            if (fieldClass.isEnum()) {
                mw.visitVarInsn(25, context.var("out"));
                mw.visitInsn(89);
                this._get(mw, context, fieldInfo);
                mw.visitMethodInsn(182, SerializeWriter, "writeEnum", "(Ljava/lang/Enum;)V");
                mw.visitVarInsn(16, seperator);
                mw.visitMethodInsn(182, SerializeWriter, "write", "(I)V");
                continue;
            }
            if (List.class.isAssignableFrom(fieldClass)) {
                Type fieldType = fieldInfo.fieldType;
                Object elementType = fieldType instanceof Class ? Object.class : ((ParameterizedType)fieldType).getActualTypeArguments()[0];
                Class elementClass = null;
                if (elementType instanceof Class && (elementClass = (Class)elementType) == Object.class) {
                    elementClass = null;
                }
                this._get(mw, context, fieldInfo);
                mw.visitTypeInsn(192, "java/util/List");
                mw.visitVarInsn(58, context.var("list"));
                if (elementClass == String.class && context.writeDirect) {
                    mw.visitVarInsn(25, context.var("out"));
                    mw.visitVarInsn(25, context.var("list"));
                    mw.visitMethodInsn(182, SerializeWriter, "write", "(Ljava/util/List;)V");
                } else {
                    Label nullEnd_ = new Label();
                    Label nullElse_ = new Label();
                    mw.visitVarInsn(25, context.var("list"));
                    mw.visitJumpInsn(199, nullElse_);
                    mw.visitVarInsn(25, context.var("out"));
                    mw.visitMethodInsn(182, SerializeWriter, "writeNull", "()V");
                    mw.visitJumpInsn(167, nullEnd_);
                    mw.visitLabel(nullElse_);
                    mw.visitVarInsn(25, context.var("list"));
                    mw.visitMethodInsn(185, "java/util/List", "size", "()I");
                    mw.visitVarInsn(54, context.var("size"));
                    mw.visitVarInsn(25, context.var("out"));
                    mw.visitVarInsn(16, 91);
                    mw.visitMethodInsn(182, SerializeWriter, "write", "(I)V");
                    Label for_ = new Label();
                    Label forFirst_ = new Label();
                    Label forEnd_ = new Label();
                    mw.visitInsn(3);
                    mw.visitVarInsn(54, context.var("i"));
                    mw.visitLabel(for_);
                    mw.visitVarInsn(21, context.var("i"));
                    mw.visitVarInsn(21, context.var("size"));
                    mw.visitJumpInsn(162, forEnd_);
                    mw.visitVarInsn(21, context.var("i"));
                    mw.visitJumpInsn(153, forFirst_);
                    mw.visitVarInsn(25, context.var("out"));
                    mw.visitVarInsn(16, 44);
                    mw.visitMethodInsn(182, SerializeWriter, "write", "(I)V");
                    mw.visitLabel(forFirst_);
                    mw.visitVarInsn(25, context.var("list"));
                    mw.visitVarInsn(21, context.var("i"));
                    mw.visitMethodInsn(185, "java/util/List", "get", "(I)Ljava/lang/Object;");
                    mw.visitVarInsn(58, context.var("list_item"));
                    Label forItemNullEnd_ = new Label();
                    Label forItemNullElse_ = new Label();
                    mw.visitVarInsn(25, context.var("list_item"));
                    mw.visitJumpInsn(199, forItemNullElse_);
                    mw.visitVarInsn(25, context.var("out"));
                    mw.visitMethodInsn(182, SerializeWriter, "writeNull", "()V");
                    mw.visitJumpInsn(167, forItemNullEnd_);
                    mw.visitLabel(forItemNullElse_);
                    Label forItemClassIfEnd_ = new Label();
                    Label forItemClassIfElse_ = new Label();
                    if (elementClass != null && Modifier.isPublic(elementClass.getModifiers())) {
                        mw.visitVarInsn(25, context.var("list_item"));
                        mw.visitMethodInsn(182, "java/lang/Object", "getClass", "()Ljava/lang/Class;");
                        mw.visitLdcInsn(com.alibaba.fastjson.asm.Type.getType(ASMUtils.desc(elementClass)));
                        mw.visitJumpInsn(166, forItemClassIfElse_);
                        this._getListFieldItemSer(context, mw, fieldInfo, elementClass);
                        mw.visitVarInsn(58, context.var("list_item_desc"));
                        Label instanceOfElse_ = new Label();
                        Label instanceOfEnd_ = new Label();
                        if (context.writeDirect) {
                            mw.visitVarInsn(25, context.var("list_item_desc"));
                            mw.visitTypeInsn(193, JavaBeanSerializer);
                            mw.visitJumpInsn(153, instanceOfElse_);
                            mw.visitVarInsn(25, context.var("list_item_desc"));
                            mw.visitTypeInsn(192, JavaBeanSerializer);
                            mw.visitVarInsn(25, 1);
                            mw.visitVarInsn(25, context.var("list_item"));
                            if (context.nonContext) {
                                mw.visitInsn(1);
                            } else {
                                mw.visitVarInsn(21, context.var("i"));
                                mw.visitMethodInsn(184, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
                            }
                            mw.visitLdcInsn(com.alibaba.fastjson.asm.Type.getType(ASMUtils.desc(elementClass)));
                            mw.visitLdcInsn(fieldInfo.serialzeFeatures);
                            mw.visitMethodInsn(182, JavaBeanSerializer, "writeAsArrayNonContext", "(L" + JSONSerializer + ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
                            mw.visitJumpInsn(167, instanceOfEnd_);
                            mw.visitLabel(instanceOfElse_);
                        }
                        mw.visitVarInsn(25, context.var("list_item_desc"));
                        mw.visitVarInsn(25, 1);
                        mw.visitVarInsn(25, context.var("list_item"));
                        if (context.nonContext) {
                            mw.visitInsn(1);
                        } else {
                            mw.visitVarInsn(21, context.var("i"));
                            mw.visitMethodInsn(184, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
                        }
                        mw.visitLdcInsn(com.alibaba.fastjson.asm.Type.getType(ASMUtils.desc(elementClass)));
                        mw.visitLdcInsn(fieldInfo.serialzeFeatures);
                        mw.visitMethodInsn(185, ObjectSerializer, "write", "(L" + JSONSerializer + ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
                        mw.visitLabel(instanceOfEnd_);
                        mw.visitJumpInsn(167, forItemClassIfEnd_);
                    }
                    mw.visitLabel(forItemClassIfElse_);
                    mw.visitVarInsn(25, 1);
                    mw.visitVarInsn(25, context.var("list_item"));
                    if (context.nonContext) {
                        mw.visitInsn(1);
                    } else {
                        mw.visitVarInsn(21, context.var("i"));
                        mw.visitMethodInsn(184, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
                    }
                    if (elementClass != null && Modifier.isPublic(elementClass.getModifiers())) {
                        mw.visitLdcInsn(com.alibaba.fastjson.asm.Type.getType(ASMUtils.desc((Class)elementType)));
                        mw.visitLdcInsn(fieldInfo.serialzeFeatures);
                        mw.visitMethodInsn(182, JSONSerializer, "writeWithFieldName", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
                    } else {
                        mw.visitMethodInsn(182, JSONSerializer, "writeWithFieldName", "(Ljava/lang/Object;Ljava/lang/Object;)V");
                    }
                    mw.visitLabel(forItemClassIfEnd_);
                    mw.visitLabel(forItemNullEnd_);
                    mw.visitIincInsn(context.var("i"), 1);
                    mw.visitJumpInsn(167, for_);
                    mw.visitLabel(forEnd_);
                    mw.visitVarInsn(25, context.var("out"));
                    mw.visitVarInsn(16, 93);
                    mw.visitMethodInsn(182, SerializeWriter, "write", "(I)V");
                    mw.visitLabel(nullEnd_);
                }
                mw.visitVarInsn(25, context.var("out"));
                mw.visitVarInsn(16, seperator);
                mw.visitMethodInsn(182, SerializeWriter, "write", "(I)V");
                continue;
            }
            Label notNullEnd_ = new Label();
            Label notNullElse_ = new Label();
            this._get(mw, context, fieldInfo);
            mw.visitInsn(89);
            mw.visitVarInsn(58, context.var("field_" + fieldInfo.fieldClass.getName()));
            mw.visitJumpInsn(199, notNullElse_);
            mw.visitVarInsn(25, context.var("out"));
            mw.visitMethodInsn(182, SerializeWriter, "writeNull", "()V");
            mw.visitJumpInsn(167, notNullEnd_);
            mw.visitLabel(notNullElse_);
            Label classIfEnd_ = new Label();
            Label classIfElse_ = new Label();
            mw.visitVarInsn(25, context.var("field_" + fieldInfo.fieldClass.getName()));
            mw.visitMethodInsn(182, "java/lang/Object", "getClass", "()Ljava/lang/Class;");
            mw.visitLdcInsn(com.alibaba.fastjson.asm.Type.getType(ASMUtils.desc(fieldClass)));
            mw.visitJumpInsn(166, classIfElse_);
            this._getFieldSer(context, mw, fieldInfo);
            mw.visitVarInsn(58, context.var("fied_ser"));
            Label instanceOfElse_ = new Label();
            Label instanceOfEnd_ = new Label();
            if (context.writeDirect && Modifier.isPublic(fieldClass.getModifiers())) {
                mw.visitVarInsn(25, context.var("fied_ser"));
                mw.visitTypeInsn(193, JavaBeanSerializer);
                mw.visitJumpInsn(153, instanceOfElse_);
                mw.visitVarInsn(25, context.var("fied_ser"));
                mw.visitTypeInsn(192, JavaBeanSerializer);
                mw.visitVarInsn(25, 1);
                mw.visitVarInsn(25, context.var("field_" + fieldInfo.fieldClass.getName()));
                mw.visitVarInsn(25, Context.fieldName);
                mw.visitLdcInsn(com.alibaba.fastjson.asm.Type.getType(ASMUtils.desc(fieldClass)));
                mw.visitLdcInsn(fieldInfo.serialzeFeatures);
                mw.visitMethodInsn(182, JavaBeanSerializer, "writeAsArrayNonContext", "(L" + JSONSerializer + ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
                mw.visitJumpInsn(167, instanceOfEnd_);
                mw.visitLabel(instanceOfElse_);
            }
            mw.visitVarInsn(25, context.var("fied_ser"));
            mw.visitVarInsn(25, 1);
            mw.visitVarInsn(25, context.var("field_" + fieldInfo.fieldClass.getName()));
            mw.visitVarInsn(25, Context.fieldName);
            mw.visitLdcInsn(com.alibaba.fastjson.asm.Type.getType(ASMUtils.desc(fieldClass)));
            mw.visitLdcInsn(fieldInfo.serialzeFeatures);
            mw.visitMethodInsn(185, ObjectSerializer, "write", "(L" + JSONSerializer + ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
            mw.visitLabel(instanceOfEnd_);
            mw.visitJumpInsn(167, classIfEnd_);
            mw.visitLabel(classIfElse_);
            String format = fieldInfo.getFormat();
            mw.visitVarInsn(25, 1);
            mw.visitVarInsn(25, context.var("field_" + fieldInfo.fieldClass.getName()));
            if (format != null) {
                mw.visitLdcInsn(format);
                mw.visitMethodInsn(182, JSONSerializer, "writeWithFormat", "(Ljava/lang/Object;Ljava/lang/String;)V");
            } else {
                mw.visitVarInsn(25, Context.fieldName);
                if (fieldInfo.fieldType instanceof Class && ((Class)fieldInfo.fieldType).isPrimitive()) {
                    mw.visitMethodInsn(182, JSONSerializer, "writeWithFieldName", "(Ljava/lang/Object;Ljava/lang/Object;)V");
                } else {
                    mw.visitVarInsn(25, 0);
                    mw.visitFieldInsn(180, context.className, fieldInfo.name + "_asm_fieldType", "Ljava/lang/reflect/Type;");
                    mw.visitLdcInsn(fieldInfo.serialzeFeatures);
                    mw.visitMethodInsn(182, JSONSerializer, "writeWithFieldName", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
                }
            }
            mw.visitLabel(classIfEnd_);
            mw.visitLabel(notNullEnd_);
            mw.visitVarInsn(25, context.var("out"));
            mw.visitVarInsn(16, seperator);
            mw.visitMethodInsn(182, SerializeWriter, "write", "(I)V");
        }
    }

    private void generateWriteMethod(Class<?> clazz, MethodVisitor mw, FieldInfo[] getters, Context context) throws Exception {
        boolean writeClasName;
        Label end = new Label();
        int size = getters.length;
        if (!context.writeDirect) {
            Label endSupper_ = new Label();
            Label supper_ = new Label();
            mw.visitVarInsn(25, context.var("out"));
            mw.visitLdcInsn(SerializerFeature.PrettyFormat.mask);
            mw.visitMethodInsn(182, SerializeWriter, "isEnabled", "(I)Z");
            mw.visitJumpInsn(154, supper_);
            boolean hasMethod = false;
            for (FieldInfo getter : getters) {
                if (getter.method == null) continue;
                hasMethod = true;
                break;
            }
            if (hasMethod) {
                mw.visitVarInsn(25, context.var("out"));
                mw.visitLdcInsn(SerializerFeature.IgnoreErrorGetter.mask);
                mw.visitMethodInsn(182, SerializeWriter, "isEnabled", "(I)Z");
                mw.visitJumpInsn(153, endSupper_);
            } else {
                mw.visitJumpInsn(167, endSupper_);
            }
            mw.visitLabel(supper_);
            mw.visitVarInsn(25, 0);
            mw.visitVarInsn(25, 1);
            mw.visitVarInsn(25, 2);
            mw.visitVarInsn(25, 3);
            mw.visitVarInsn(25, 4);
            mw.visitVarInsn(21, 5);
            mw.visitMethodInsn(183, JavaBeanSerializer, "write", "(L" + JSONSerializer + ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
            mw.visitInsn(177);
            mw.visitLabel(endSupper_);
        }
        if (!context.nonContext) {
            Label endRef_ = new Label();
            mw.visitVarInsn(25, 0);
            mw.visitVarInsn(25, 1);
            mw.visitVarInsn(25, 2);
            mw.visitVarInsn(21, 5);
            mw.visitMethodInsn(182, JavaBeanSerializer, "writeReference", "(L" + JSONSerializer + ";Ljava/lang/Object;I)Z");
            mw.visitJumpInsn(153, endRef_);
            mw.visitInsn(177);
            mw.visitLabel(endRef_);
        }
        String writeAsArrayMethodName = context.writeDirect ? (context.nonContext ? "writeAsArrayNonContext" : "writeAsArray") : "writeAsArrayNormal";
        if ((((Context)context).beanInfo.features & SerializerFeature.BeanToArray.mask) == 0) {
            Label endWriteAsArray_ = new Label();
            mw.visitVarInsn(25, context.var("out"));
            mw.visitLdcInsn(SerializerFeature.BeanToArray.mask);
            mw.visitMethodInsn(182, SerializeWriter, "isEnabled", "(I)Z");
            mw.visitJumpInsn(153, endWriteAsArray_);
            mw.visitVarInsn(25, 0);
            mw.visitVarInsn(25, 1);
            mw.visitVarInsn(25, 2);
            mw.visitVarInsn(25, 3);
            mw.visitVarInsn(25, 4);
            mw.visitVarInsn(21, 5);
            mw.visitMethodInsn(182, context.className, writeAsArrayMethodName, "(L" + JSONSerializer + ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
            mw.visitInsn(177);
            mw.visitLabel(endWriteAsArray_);
        } else {
            mw.visitVarInsn(25, 0);
            mw.visitVarInsn(25, 1);
            mw.visitVarInsn(25, 2);
            mw.visitVarInsn(25, 3);
            mw.visitVarInsn(25, 4);
            mw.visitVarInsn(21, 5);
            mw.visitMethodInsn(182, context.className, writeAsArrayMethodName, "(L" + JSONSerializer + ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
            mw.visitInsn(177);
        }
        if (!context.nonContext) {
            mw.visitVarInsn(25, 1);
            mw.visitMethodInsn(182, JSONSerializer, "getContext", "()" + SerialContext_desc);
            mw.visitVarInsn(58, context.var("parent"));
            mw.visitVarInsn(25, 1);
            mw.visitVarInsn(25, context.var("parent"));
            mw.visitVarInsn(25, 2);
            mw.visitVarInsn(25, 3);
            mw.visitLdcInsn(((Context)context).beanInfo.features);
            mw.visitMethodInsn(182, JSONSerializer, "setContext", "(" + SerialContext_desc + "Ljava/lang/Object;Ljava/lang/Object;I)V");
        }
        boolean bl = writeClasName = (((Context)context).beanInfo.features & SerializerFeature.WriteClassName.mask) != 0;
        if (writeClasName || !context.writeDirect) {
            Label end_ = new Label();
            Label else_ = new Label();
            Label writeClass_ = new Label();
            if (!writeClasName) {
                mw.visitVarInsn(25, 1);
                mw.visitVarInsn(25, 4);
                mw.visitVarInsn(25, 2);
                mw.visitMethodInsn(182, JSONSerializer, "isWriteClassName", "(Ljava/lang/reflect/Type;Ljava/lang/Object;)Z");
                mw.visitJumpInsn(153, else_);
            }
            mw.visitVarInsn(25, 4);
            mw.visitVarInsn(25, 2);
            mw.visitMethodInsn(182, "java/lang/Object", "getClass", "()Ljava/lang/Class;");
            mw.visitJumpInsn(165, else_);
            mw.visitLabel(writeClass_);
            mw.visitVarInsn(25, context.var("out"));
            mw.visitVarInsn(16, 123);
            mw.visitMethodInsn(182, SerializeWriter, "write", "(I)V");
            mw.visitVarInsn(25, 0);
            mw.visitVarInsn(25, 1);
            if (((Context)context).beanInfo.typeKey != null) {
                mw.visitLdcInsn(((Context)context).beanInfo.typeKey);
            } else {
                mw.visitInsn(1);
            }
            mw.visitVarInsn(25, 2);
            mw.visitMethodInsn(182, JavaBeanSerializer, "writeClassName", "(L" + JSONSerializer + ";Ljava/lang/String;Ljava/lang/Object;)V");
            mw.visitVarInsn(16, 44);
            mw.visitJumpInsn(167, end_);
            mw.visitLabel(else_);
            mw.visitVarInsn(16, 123);
            mw.visitLabel(end_);
        } else {
            mw.visitVarInsn(16, 123);
        }
        mw.visitVarInsn(54, context.var("seperator"));
        if (!context.writeDirect) {
            this._before(mw, context);
        }
        if (!context.writeDirect) {
            mw.visitVarInsn(25, context.var("out"));
            mw.visitMethodInsn(182, SerializeWriter, "isNotWriteDefaultValue", "()Z");
            mw.visitVarInsn(54, context.var("notWriteDefaultValue"));
            mw.visitVarInsn(25, 1);
            mw.visitVarInsn(25, 0);
            mw.visitMethodInsn(182, JSONSerializer, "checkValue", "(" + SerializeFilterable_desc + ")Z");
            mw.visitVarInsn(54, context.var("checkValue"));
            mw.visitVarInsn(25, 1);
            mw.visitVarInsn(25, 0);
            mw.visitMethodInsn(182, JSONSerializer, "hasNameFilters", "(" + SerializeFilterable_desc + ")Z");
            mw.visitVarInsn(54, context.var("hasNameFilters"));
        }
        for (int i = 0; i < size; ++i) {
            FieldInfo property = getters[i];
            Class<?> propertyClass = property.fieldClass;
            mw.visitLdcInsn(property.name);
            mw.visitVarInsn(58, Context.fieldName);
            if (propertyClass == Byte.TYPE || propertyClass == Short.TYPE || propertyClass == Integer.TYPE) {
                this._int(clazz, mw, property, context, context.var(propertyClass.getName()), 'I');
                continue;
            }
            if (propertyClass == Long.TYPE) {
                this._long(clazz, mw, property, context);
                continue;
            }
            if (propertyClass == Float.TYPE) {
                this._float(clazz, mw, property, context);
                continue;
            }
            if (propertyClass == Double.TYPE) {
                this._double(clazz, mw, property, context);
                continue;
            }
            if (propertyClass == Boolean.TYPE) {
                this._int(clazz, mw, property, context, context.var("boolean"), 'Z');
                continue;
            }
            if (propertyClass == Character.TYPE) {
                this._int(clazz, mw, property, context, context.var("char"), 'C');
                continue;
            }
            if (propertyClass == String.class) {
                this._string(clazz, mw, property, context);
                continue;
            }
            if (propertyClass == BigDecimal.class) {
                this._decimal(clazz, mw, property, context);
                continue;
            }
            if (List.class.isAssignableFrom(propertyClass)) {
                this._list(clazz, mw, property, context);
                continue;
            }
            if (propertyClass.isEnum()) {
                this._enum(clazz, mw, property, context);
                continue;
            }
            this._object(clazz, mw, property, context);
        }
        if (!context.writeDirect) {
            this._after(mw, context);
        }
        Label _else = new Label();
        Label _end_if = new Label();
        mw.visitVarInsn(21, context.var("seperator"));
        mw.visitIntInsn(16, 123);
        mw.visitJumpInsn(160, _else);
        mw.visitVarInsn(25, context.var("out"));
        mw.visitVarInsn(16, 123);
        mw.visitMethodInsn(182, SerializeWriter, "write", "(I)V");
        mw.visitLabel(_else);
        mw.visitVarInsn(25, context.var("out"));
        mw.visitVarInsn(16, 125);
        mw.visitMethodInsn(182, SerializeWriter, "write", "(I)V");
        mw.visitLabel(_end_if);
        mw.visitLabel(end);
        if (!context.nonContext) {
            mw.visitVarInsn(25, 1);
            mw.visitVarInsn(25, context.var("parent"));
            mw.visitMethodInsn(182, JSONSerializer, "setContext", "(" + SerialContext_desc + ")V");
        }
    }

    private void _object(Class<?> clazz, MethodVisitor mw, FieldInfo property, Context context) {
        Label _end = new Label();
        this._nameApply(mw, property, context, _end);
        this._get(mw, context, property);
        mw.visitVarInsn(58, context.var("object"));
        this._filters(mw, property, context, _end);
        this._writeObject(mw, property, context, _end);
        mw.visitLabel(_end);
    }

    private void _enum(Class<?> clazz, MethodVisitor mw, FieldInfo fieldInfo, Context context) {
        Label _not_null = new Label();
        Label _end_if = new Label();
        Label _end = new Label();
        this._nameApply(mw, fieldInfo, context, _end);
        this._get(mw, context, fieldInfo);
        mw.visitTypeInsn(192, "java/lang/Enum");
        mw.visitVarInsn(58, context.var("enum"));
        this._filters(mw, fieldInfo, context, _end);
        mw.visitVarInsn(25, context.var("enum"));
        mw.visitJumpInsn(199, _not_null);
        this._if_write_null(mw, fieldInfo, context);
        mw.visitJumpInsn(167, _end_if);
        mw.visitLabel(_not_null);
        if (context.writeDirect) {
            mw.visitVarInsn(25, context.var("out"));
            mw.visitVarInsn(21, context.var("seperator"));
            mw.visitVarInsn(25, Context.fieldName);
            mw.visitVarInsn(25, context.var("enum"));
            mw.visitMethodInsn(182, "java/lang/Enum", "name", "()Ljava/lang/String;");
            mw.visitMethodInsn(182, SerializeWriter, "writeFieldValueStringWithDoubleQuote", "(CLjava/lang/String;Ljava/lang/String;)V");
        } else {
            mw.visitVarInsn(25, context.var("out"));
            mw.visitVarInsn(21, context.var("seperator"));
            mw.visitMethodInsn(182, SerializeWriter, "write", "(I)V");
            mw.visitVarInsn(25, context.var("out"));
            mw.visitVarInsn(25, Context.fieldName);
            mw.visitInsn(3);
            mw.visitMethodInsn(182, SerializeWriter, "writeFieldName", "(Ljava/lang/String;Z)V");
            mw.visitVarInsn(25, 1);
            mw.visitVarInsn(25, context.var("enum"));
            mw.visitVarInsn(25, Context.fieldName);
            mw.visitLdcInsn(com.alibaba.fastjson.asm.Type.getType(ASMUtils.desc(fieldInfo.fieldClass)));
            mw.visitLdcInsn(fieldInfo.serialzeFeatures);
            mw.visitMethodInsn(182, JSONSerializer, "writeWithFieldName", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
        }
        this._seperator(mw, context);
        mw.visitLabel(_end_if);
        mw.visitLabel(_end);
    }

    private void _int(Class<?> clazz, MethodVisitor mw, FieldInfo property, Context context, int var, char type) {
        Label end_ = new Label();
        this._nameApply(mw, property, context, end_);
        this._get(mw, context, property);
        mw.visitVarInsn(54, var);
        this._filters(mw, property, context, end_);
        mw.visitVarInsn(25, context.var("out"));
        mw.visitVarInsn(21, context.var("seperator"));
        mw.visitVarInsn(25, Context.fieldName);
        mw.visitVarInsn(21, var);
        mw.visitMethodInsn(182, SerializeWriter, "writeFieldValue", "(CLjava/lang/String;" + type + ")V");
        this._seperator(mw, context);
        mw.visitLabel(end_);
    }

    private void _long(Class<?> clazz, MethodVisitor mw, FieldInfo property, Context context) {
        Label end_ = new Label();
        this._nameApply(mw, property, context, end_);
        this._get(mw, context, property);
        mw.visitVarInsn(55, context.var("long", 2));
        this._filters(mw, property, context, end_);
        mw.visitVarInsn(25, context.var("out"));
        mw.visitVarInsn(21, context.var("seperator"));
        mw.visitVarInsn(25, Context.fieldName);
        mw.visitVarInsn(22, context.var("long", 2));
        mw.visitMethodInsn(182, SerializeWriter, "writeFieldValue", "(CLjava/lang/String;J)V");
        this._seperator(mw, context);
        mw.visitLabel(end_);
    }

    private void _float(Class<?> clazz, MethodVisitor mw, FieldInfo property, Context context) {
        Label end_ = new Label();
        this._nameApply(mw, property, context, end_);
        this._get(mw, context, property);
        mw.visitVarInsn(56, context.var("float"));
        this._filters(mw, property, context, end_);
        mw.visitVarInsn(25, context.var("out"));
        mw.visitVarInsn(21, context.var("seperator"));
        mw.visitVarInsn(25, Context.fieldName);
        mw.visitVarInsn(23, context.var("float"));
        mw.visitMethodInsn(182, SerializeWriter, "writeFieldValue", "(CLjava/lang/String;F)V");
        this._seperator(mw, context);
        mw.visitLabel(end_);
    }

    private void _double(Class<?> clazz, MethodVisitor mw, FieldInfo property, Context context) {
        Label end_ = new Label();
        this._nameApply(mw, property, context, end_);
        this._get(mw, context, property);
        mw.visitVarInsn(57, context.var("double", 2));
        this._filters(mw, property, context, end_);
        mw.visitVarInsn(25, context.var("out"));
        mw.visitVarInsn(21, context.var("seperator"));
        mw.visitVarInsn(25, Context.fieldName);
        mw.visitVarInsn(24, context.var("double", 2));
        mw.visitMethodInsn(182, SerializeWriter, "writeFieldValue", "(CLjava/lang/String;D)V");
        this._seperator(mw, context);
        mw.visitLabel(end_);
    }

    private void _get(MethodVisitor mw, Context context, FieldInfo fieldInfo) {
        Method method = fieldInfo.method;
        if (method != null) {
            mw.visitVarInsn(25, context.var("entity"));
            Class<?> declaringClass = method.getDeclaringClass();
            mw.visitMethodInsn(declaringClass.isInterface() ? 185 : 182, ASMUtils.type(declaringClass), method.getName(), ASMUtils.desc(method));
            if (!method.getReturnType().equals(fieldInfo.fieldClass)) {
                mw.visitTypeInsn(192, ASMUtils.type(fieldInfo.fieldClass));
            }
        } else {
            mw.visitVarInsn(25, context.var("entity"));
            Field field = fieldInfo.field;
            mw.visitFieldInsn(180, ASMUtils.type(fieldInfo.declaringClass), field.getName(), ASMUtils.desc(field.getType()));
            if (!field.getType().equals(fieldInfo.fieldClass)) {
                mw.visitTypeInsn(192, ASMUtils.type(fieldInfo.fieldClass));
            }
        }
    }

    private void _decimal(Class<?> clazz, MethodVisitor mw, FieldInfo property, Context context) {
        Label end_ = new Label();
        this._nameApply(mw, property, context, end_);
        this._get(mw, context, property);
        mw.visitVarInsn(58, context.var("decimal"));
        this._filters(mw, property, context, end_);
        Label if_ = new Label();
        Label else_ = new Label();
        Label endIf_ = new Label();
        mw.visitLabel(if_);
        mw.visitVarInsn(25, context.var("decimal"));
        mw.visitJumpInsn(199, else_);
        this._if_write_null(mw, property, context);
        mw.visitJumpInsn(167, endIf_);
        mw.visitLabel(else_);
        mw.visitVarInsn(25, context.var("out"));
        mw.visitVarInsn(21, context.var("seperator"));
        mw.visitVarInsn(25, Context.fieldName);
        mw.visitVarInsn(25, context.var("decimal"));
        mw.visitMethodInsn(182, SerializeWriter, "writeFieldValue", "(CLjava/lang/String;Ljava/math/BigDecimal;)V");
        this._seperator(mw, context);
        mw.visitJumpInsn(167, endIf_);
        mw.visitLabel(endIf_);
        mw.visitLabel(end_);
    }

    private void _string(Class<?> clazz, MethodVisitor mw, FieldInfo property, Context context) {
        Label end_ = new Label();
        if (property.name.equals(((Context)context).beanInfo.typeKey)) {
            mw.visitVarInsn(25, 1);
            mw.visitVarInsn(25, 4);
            mw.visitVarInsn(25, 2);
            mw.visitMethodInsn(182, JSONSerializer, "isWriteClassName", "(Ljava/lang/reflect/Type;Ljava/lang/Object;)Z");
            mw.visitJumpInsn(154, end_);
        }
        this._nameApply(mw, property, context, end_);
        this._get(mw, context, property);
        mw.visitVarInsn(58, context.var("string"));
        this._filters(mw, property, context, end_);
        Label else_ = new Label();
        Label endIf_ = new Label();
        mw.visitVarInsn(25, context.var("string"));
        mw.visitJumpInsn(199, else_);
        this._if_write_null(mw, property, context);
        mw.visitJumpInsn(167, endIf_);
        mw.visitLabel(else_);
        if ("trim".equals(property.format)) {
            mw.visitVarInsn(25, context.var("string"));
            mw.visitMethodInsn(182, "java/lang/String", "trim", "()Ljava/lang/String;");
            mw.visitVarInsn(58, context.var("string"));
        }
        if (context.writeDirect) {
            mw.visitVarInsn(25, context.var("out"));
            mw.visitVarInsn(21, context.var("seperator"));
            mw.visitVarInsn(25, Context.fieldName);
            mw.visitVarInsn(25, context.var("string"));
            mw.visitMethodInsn(182, SerializeWriter, "writeFieldValueStringWithDoubleQuoteCheck", "(CLjava/lang/String;Ljava/lang/String;)V");
        } else {
            mw.visitVarInsn(25, context.var("out"));
            mw.visitVarInsn(21, context.var("seperator"));
            mw.visitVarInsn(25, Context.fieldName);
            mw.visitVarInsn(25, context.var("string"));
            mw.visitMethodInsn(182, SerializeWriter, "writeFieldValue", "(CLjava/lang/String;Ljava/lang/String;)V");
        }
        this._seperator(mw, context);
        mw.visitLabel(endIf_);
        mw.visitLabel(end_);
    }

    private void _list(Class<?> clazz, MethodVisitor mw, FieldInfo fieldInfo, Context context) {
        Type propertyType = fieldInfo.fieldType;
        Type elementType = TypeUtils.getCollectionItemType(propertyType);
        Class elementClass = null;
        if (elementType instanceof Class) {
            elementClass = (Class)elementType;
        }
        if (elementClass == Object.class || elementClass == Serializable.class) {
            elementClass = null;
        }
        Label end_ = new Label();
        Label else_ = new Label();
        Label endIf_ = new Label();
        this._nameApply(mw, fieldInfo, context, end_);
        this._get(mw, context, fieldInfo);
        mw.visitTypeInsn(192, "java/util/List");
        mw.visitVarInsn(58, context.var("list"));
        this._filters(mw, fieldInfo, context, end_);
        mw.visitVarInsn(25, context.var("list"));
        mw.visitJumpInsn(199, else_);
        this._if_write_null(mw, fieldInfo, context);
        mw.visitJumpInsn(167, endIf_);
        mw.visitLabel(else_);
        mw.visitVarInsn(25, context.var("out"));
        mw.visitVarInsn(21, context.var("seperator"));
        mw.visitMethodInsn(182, SerializeWriter, "write", "(I)V");
        this._writeFieldName(mw, context);
        mw.visitVarInsn(25, context.var("list"));
        mw.visitMethodInsn(185, "java/util/List", "size", "()I");
        mw.visitVarInsn(54, context.var("size"));
        Label _else_3 = new Label();
        Label _end_if_3 = new Label();
        mw.visitVarInsn(21, context.var("size"));
        mw.visitInsn(3);
        mw.visitJumpInsn(160, _else_3);
        mw.visitVarInsn(25, context.var("out"));
        mw.visitLdcInsn("[]");
        mw.visitMethodInsn(182, SerializeWriter, "write", "(Ljava/lang/String;)V");
        mw.visitJumpInsn(167, _end_if_3);
        mw.visitLabel(_else_3);
        if (!context.nonContext) {
            mw.visitVarInsn(25, 1);
            mw.visitVarInsn(25, context.var("list"));
            mw.visitVarInsn(25, Context.fieldName);
            mw.visitMethodInsn(182, JSONSerializer, "setContext", "(Ljava/lang/Object;Ljava/lang/Object;)V");
        }
        if (elementType == String.class && context.writeDirect) {
            mw.visitVarInsn(25, context.var("out"));
            mw.visitVarInsn(25, context.var("list"));
            mw.visitMethodInsn(182, SerializeWriter, "write", "(Ljava/util/List;)V");
        } else {
            mw.visitVarInsn(25, context.var("out"));
            mw.visitVarInsn(16, 91);
            mw.visitMethodInsn(182, SerializeWriter, "write", "(I)V");
            Label for_ = new Label();
            Label forFirst_ = new Label();
            Label forEnd_ = new Label();
            mw.visitInsn(3);
            mw.visitVarInsn(54, context.var("i"));
            mw.visitLabel(for_);
            mw.visitVarInsn(21, context.var("i"));
            mw.visitVarInsn(21, context.var("size"));
            mw.visitJumpInsn(162, forEnd_);
            mw.visitVarInsn(21, context.var("i"));
            mw.visitJumpInsn(153, forFirst_);
            mw.visitVarInsn(25, context.var("out"));
            mw.visitVarInsn(16, 44);
            mw.visitMethodInsn(182, SerializeWriter, "write", "(I)V");
            mw.visitLabel(forFirst_);
            mw.visitVarInsn(25, context.var("list"));
            mw.visitVarInsn(21, context.var("i"));
            mw.visitMethodInsn(185, "java/util/List", "get", "(I)Ljava/lang/Object;");
            mw.visitVarInsn(58, context.var("list_item"));
            Label forItemNullEnd_ = new Label();
            Label forItemNullElse_ = new Label();
            mw.visitVarInsn(25, context.var("list_item"));
            mw.visitJumpInsn(199, forItemNullElse_);
            mw.visitVarInsn(25, context.var("out"));
            mw.visitMethodInsn(182, SerializeWriter, "writeNull", "()V");
            mw.visitJumpInsn(167, forItemNullEnd_);
            mw.visitLabel(forItemNullElse_);
            Label forItemClassIfEnd_ = new Label();
            Label forItemClassIfElse_ = new Label();
            if (elementClass != null && Modifier.isPublic(elementClass.getModifiers())) {
                mw.visitVarInsn(25, context.var("list_item"));
                mw.visitMethodInsn(182, "java/lang/Object", "getClass", "()Ljava/lang/Class;");
                mw.visitLdcInsn(com.alibaba.fastjson.asm.Type.getType(ASMUtils.desc(elementClass)));
                mw.visitJumpInsn(166, forItemClassIfElse_);
                this._getListFieldItemSer(context, mw, fieldInfo, elementClass);
                mw.visitVarInsn(58, context.var("list_item_desc"));
                Label instanceOfElse_ = new Label();
                Label instanceOfEnd_ = new Label();
                if (context.writeDirect) {
                    String writeMethodName = context.nonContext && context.writeDirect ? "writeDirectNonContext" : "write";
                    mw.visitVarInsn(25, context.var("list_item_desc"));
                    mw.visitTypeInsn(193, JavaBeanSerializer);
                    mw.visitJumpInsn(153, instanceOfElse_);
                    mw.visitVarInsn(25, context.var("list_item_desc"));
                    mw.visitTypeInsn(192, JavaBeanSerializer);
                    mw.visitVarInsn(25, 1);
                    mw.visitVarInsn(25, context.var("list_item"));
                    if (context.nonContext) {
                        mw.visitInsn(1);
                    } else {
                        mw.visitVarInsn(21, context.var("i"));
                        mw.visitMethodInsn(184, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
                    }
                    mw.visitLdcInsn(com.alibaba.fastjson.asm.Type.getType(ASMUtils.desc(elementClass)));
                    mw.visitLdcInsn(fieldInfo.serialzeFeatures);
                    mw.visitMethodInsn(182, JavaBeanSerializer, writeMethodName, "(L" + JSONSerializer + ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
                    mw.visitJumpInsn(167, instanceOfEnd_);
                    mw.visitLabel(instanceOfElse_);
                }
                mw.visitVarInsn(25, context.var("list_item_desc"));
                mw.visitVarInsn(25, 1);
                mw.visitVarInsn(25, context.var("list_item"));
                if (context.nonContext) {
                    mw.visitInsn(1);
                } else {
                    mw.visitVarInsn(21, context.var("i"));
                    mw.visitMethodInsn(184, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
                }
                mw.visitLdcInsn(com.alibaba.fastjson.asm.Type.getType(ASMUtils.desc(elementClass)));
                mw.visitLdcInsn(fieldInfo.serialzeFeatures);
                mw.visitMethodInsn(185, ObjectSerializer, "write", "(L" + JSONSerializer + ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
                mw.visitLabel(instanceOfEnd_);
                mw.visitJumpInsn(167, forItemClassIfEnd_);
            }
            mw.visitLabel(forItemClassIfElse_);
            mw.visitVarInsn(25, 1);
            mw.visitVarInsn(25, context.var("list_item"));
            if (context.nonContext) {
                mw.visitInsn(1);
            } else {
                mw.visitVarInsn(21, context.var("i"));
                mw.visitMethodInsn(184, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
            }
            if (elementClass != null && Modifier.isPublic(elementClass.getModifiers())) {
                mw.visitLdcInsn(com.alibaba.fastjson.asm.Type.getType(ASMUtils.desc((Class)elementType)));
                mw.visitLdcInsn(fieldInfo.serialzeFeatures);
                mw.visitMethodInsn(182, JSONSerializer, "writeWithFieldName", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
            } else {
                mw.visitMethodInsn(182, JSONSerializer, "writeWithFieldName", "(Ljava/lang/Object;Ljava/lang/Object;)V");
            }
            mw.visitLabel(forItemClassIfEnd_);
            mw.visitLabel(forItemNullEnd_);
            mw.visitIincInsn(context.var("i"), 1);
            mw.visitJumpInsn(167, for_);
            mw.visitLabel(forEnd_);
            mw.visitVarInsn(25, context.var("out"));
            mw.visitVarInsn(16, 93);
            mw.visitMethodInsn(182, SerializeWriter, "write", "(I)V");
        }
        mw.visitVarInsn(25, 1);
        mw.visitMethodInsn(182, JSONSerializer, "popContext", "()V");
        mw.visitLabel(_end_if_3);
        this._seperator(mw, context);
        mw.visitLabel(endIf_);
        mw.visitLabel(end_);
    }

    private void _filters(MethodVisitor mw, FieldInfo property, Context context, Label _end) {
        if (property.fieldTransient) {
            mw.visitVarInsn(25, context.var("out"));
            mw.visitLdcInsn(SerializerFeature.SkipTransientField.mask);
            mw.visitMethodInsn(182, SerializeWriter, "isEnabled", "(I)Z");
            mw.visitJumpInsn(154, _end);
        }
        this._notWriteDefault(mw, property, context, _end);
        if (context.writeDirect) {
            return;
        }
        this._apply(mw, property, context);
        mw.visitJumpInsn(153, _end);
        this._processKey(mw, property, context);
        this._processValue(mw, property, context, _end);
    }

    private void _nameApply(MethodVisitor mw, FieldInfo property, Context context, Label _end) {
        if (!context.writeDirect) {
            mw.visitVarInsn(25, 0);
            mw.visitVarInsn(25, 1);
            mw.visitVarInsn(25, 2);
            mw.visitVarInsn(25, Context.fieldName);
            mw.visitMethodInsn(182, JavaBeanSerializer, "applyName", "(L" + JSONSerializer + ";Ljava/lang/Object;Ljava/lang/String;)Z");
            mw.visitJumpInsn(153, _end);
            this._labelApply(mw, property, context, _end);
        }
        if (property.field == null) {
            mw.visitVarInsn(25, context.var("out"));
            mw.visitLdcInsn(SerializerFeature.IgnoreNonFieldGetter.mask);
            mw.visitMethodInsn(182, SerializeWriter, "isEnabled", "(I)Z");
            mw.visitJumpInsn(154, _end);
        }
    }

    private void _labelApply(MethodVisitor mw, FieldInfo property, Context context, Label _end) {
        mw.visitVarInsn(25, 0);
        mw.visitVarInsn(25, 1);
        mw.visitLdcInsn(property.label);
        mw.visitMethodInsn(182, JavaBeanSerializer, "applyLabel", "(L" + JSONSerializer + ";Ljava/lang/String;)Z");
        mw.visitJumpInsn(153, _end);
    }

    private void _writeObject(MethodVisitor mw, FieldInfo fieldInfo, Context context, Label _end) {
        String format = fieldInfo.getFormat();
        Class<?> fieldClass = fieldInfo.fieldClass;
        Label notNull_ = new Label();
        if (context.writeDirect) {
            mw.visitVarInsn(25, context.var("object"));
        } else {
            mw.visitVarInsn(25, Context.processValue);
        }
        mw.visitInsn(89);
        mw.visitVarInsn(58, context.var("object"));
        mw.visitJumpInsn(199, notNull_);
        this._if_write_null(mw, fieldInfo, context);
        mw.visitJumpInsn(167, _end);
        mw.visitLabel(notNull_);
        mw.visitVarInsn(25, context.var("out"));
        mw.visitVarInsn(21, context.var("seperator"));
        mw.visitMethodInsn(182, SerializeWriter, "write", "(I)V");
        this._writeFieldName(mw, context);
        Label classIfEnd_ = new Label();
        Label classIfElse_ = new Label();
        if (Modifier.isPublic(fieldClass.getModifiers()) && !ParserConfig.isPrimitive2(fieldClass)) {
            boolean fieldBeanToArray;
            mw.visitVarInsn(25, context.var("object"));
            mw.visitMethodInsn(182, "java/lang/Object", "getClass", "()Ljava/lang/Class;");
            mw.visitLdcInsn(com.alibaba.fastjson.asm.Type.getType(ASMUtils.desc(fieldClass)));
            mw.visitJumpInsn(166, classIfElse_);
            this._getFieldSer(context, mw, fieldInfo);
            mw.visitVarInsn(58, context.var("fied_ser"));
            Label instanceOfElse_ = new Label();
            Label instanceOfEnd_ = new Label();
            mw.visitVarInsn(25, context.var("fied_ser"));
            mw.visitTypeInsn(193, JavaBeanSerializer);
            mw.visitJumpInsn(153, instanceOfElse_);
            boolean disableCircularReferenceDetect = (fieldInfo.serialzeFeatures & SerializerFeature.DisableCircularReferenceDetect.mask) != 0;
            boolean bl = fieldBeanToArray = (fieldInfo.serialzeFeatures & SerializerFeature.BeanToArray.mask) != 0;
            String writeMethodName = disableCircularReferenceDetect || context.nonContext && context.writeDirect ? (fieldBeanToArray ? "writeAsArrayNonContext" : "writeDirectNonContext") : (fieldBeanToArray ? "writeAsArray" : "write");
            mw.visitVarInsn(25, context.var("fied_ser"));
            mw.visitTypeInsn(192, JavaBeanSerializer);
            mw.visitVarInsn(25, 1);
            mw.visitVarInsn(25, context.var("object"));
            mw.visitVarInsn(25, Context.fieldName);
            mw.visitVarInsn(25, 0);
            mw.visitFieldInsn(180, context.className, fieldInfo.name + "_asm_fieldType", "Ljava/lang/reflect/Type;");
            mw.visitLdcInsn(fieldInfo.serialzeFeatures);
            mw.visitMethodInsn(182, JavaBeanSerializer, writeMethodName, "(L" + JSONSerializer + ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
            mw.visitJumpInsn(167, instanceOfEnd_);
            mw.visitLabel(instanceOfElse_);
            mw.visitVarInsn(25, context.var("fied_ser"));
            mw.visitVarInsn(25, 1);
            mw.visitVarInsn(25, context.var("object"));
            mw.visitVarInsn(25, Context.fieldName);
            mw.visitVarInsn(25, 0);
            mw.visitFieldInsn(180, context.className, fieldInfo.name + "_asm_fieldType", "Ljava/lang/reflect/Type;");
            mw.visitLdcInsn(fieldInfo.serialzeFeatures);
            mw.visitMethodInsn(185, ObjectSerializer, "write", "(L" + JSONSerializer + ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
            mw.visitLabel(instanceOfEnd_);
            mw.visitJumpInsn(167, classIfEnd_);
        }
        mw.visitLabel(classIfElse_);
        mw.visitVarInsn(25, 1);
        if (context.writeDirect) {
            mw.visitVarInsn(25, context.var("object"));
        } else {
            mw.visitVarInsn(25, Context.processValue);
        }
        if (format != null) {
            mw.visitLdcInsn(format);
            mw.visitMethodInsn(182, JSONSerializer, "writeWithFormat", "(Ljava/lang/Object;Ljava/lang/String;)V");
        } else {
            mw.visitVarInsn(25, Context.fieldName);
            if (fieldInfo.fieldType instanceof Class && ((Class)fieldInfo.fieldType).isPrimitive()) {
                mw.visitMethodInsn(182, JSONSerializer, "writeWithFieldName", "(Ljava/lang/Object;Ljava/lang/Object;)V");
            } else {
                if (fieldInfo.fieldClass == String.class) {
                    mw.visitLdcInsn(com.alibaba.fastjson.asm.Type.getType(ASMUtils.desc(String.class)));
                } else {
                    mw.visitVarInsn(25, 0);
                    mw.visitFieldInsn(180, context.className, fieldInfo.name + "_asm_fieldType", "Ljava/lang/reflect/Type;");
                }
                mw.visitLdcInsn(fieldInfo.serialzeFeatures);
                mw.visitMethodInsn(182, JSONSerializer, "writeWithFieldName", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
            }
        }
        mw.visitLabel(classIfEnd_);
        this._seperator(mw, context);
    }

    private void _before(MethodVisitor mw, Context context) {
        mw.visitVarInsn(25, 0);
        mw.visitVarInsn(25, 1);
        mw.visitVarInsn(25, 2);
        mw.visitVarInsn(21, context.var("seperator"));
        mw.visitMethodInsn(182, JavaBeanSerializer, "writeBefore", "(L" + JSONSerializer + ";Ljava/lang/Object;C)C");
        mw.visitVarInsn(54, context.var("seperator"));
    }

    private void _after(MethodVisitor mw, Context context) {
        mw.visitVarInsn(25, 0);
        mw.visitVarInsn(25, 1);
        mw.visitVarInsn(25, 2);
        mw.visitVarInsn(21, context.var("seperator"));
        mw.visitMethodInsn(182, JavaBeanSerializer, "writeAfter", "(L" + JSONSerializer + ";Ljava/lang/Object;C)C");
        mw.visitVarInsn(54, context.var("seperator"));
    }

    private void _notWriteDefault(MethodVisitor mw, FieldInfo property, Context context, Label _end) {
        if (context.writeDirect) {
            return;
        }
        Label elseLabel = new Label();
        mw.visitVarInsn(21, context.var("notWriteDefaultValue"));
        mw.visitJumpInsn(153, elseLabel);
        Class<?> propertyClass = property.fieldClass;
        if (propertyClass == Boolean.TYPE) {
            mw.visitVarInsn(21, context.var("boolean"));
            mw.visitJumpInsn(153, _end);
        } else if (propertyClass == Byte.TYPE) {
            mw.visitVarInsn(21, context.var("byte"));
            mw.visitJumpInsn(153, _end);
        } else if (propertyClass == Short.TYPE) {
            mw.visitVarInsn(21, context.var("short"));
            mw.visitJumpInsn(153, _end);
        } else if (propertyClass == Integer.TYPE) {
            mw.visitVarInsn(21, context.var("int"));
            mw.visitJumpInsn(153, _end);
        } else if (propertyClass == Long.TYPE) {
            mw.visitVarInsn(22, context.var("long"));
            mw.visitInsn(9);
            mw.visitInsn(148);
            mw.visitJumpInsn(153, _end);
        } else if (propertyClass == Float.TYPE) {
            mw.visitVarInsn(23, context.var("float"));
            mw.visitInsn(11);
            mw.visitInsn(149);
            mw.visitJumpInsn(153, _end);
        } else if (propertyClass == Double.TYPE) {
            mw.visitVarInsn(24, context.var("double"));
            mw.visitInsn(14);
            mw.visitInsn(151);
            mw.visitJumpInsn(153, _end);
        }
        mw.visitLabel(elseLabel);
    }

    private void _apply(MethodVisitor mw, FieldInfo property, Context context) {
        Class<?> propertyClass = property.fieldClass;
        mw.visitVarInsn(25, 0);
        mw.visitVarInsn(25, 1);
        mw.visitVarInsn(25, 2);
        mw.visitVarInsn(25, Context.fieldName);
        if (propertyClass == Byte.TYPE) {
            mw.visitVarInsn(21, context.var("byte"));
            mw.visitMethodInsn(184, "java/lang/Byte", "valueOf", "(B)Ljava/lang/Byte;");
        } else if (propertyClass == Short.TYPE) {
            mw.visitVarInsn(21, context.var("short"));
            mw.visitMethodInsn(184, "java/lang/Short", "valueOf", "(S)Ljava/lang/Short;");
        } else if (propertyClass == Integer.TYPE) {
            mw.visitVarInsn(21, context.var("int"));
            mw.visitMethodInsn(184, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
        } else if (propertyClass == Character.TYPE) {
            mw.visitVarInsn(21, context.var("char"));
            mw.visitMethodInsn(184, "java/lang/Character", "valueOf", "(C)Ljava/lang/Character;");
        } else if (propertyClass == Long.TYPE) {
            mw.visitVarInsn(22, context.var("long", 2));
            mw.visitMethodInsn(184, "java/lang/Long", "valueOf", "(J)Ljava/lang/Long;");
        } else if (propertyClass == Float.TYPE) {
            mw.visitVarInsn(23, context.var("float"));
            mw.visitMethodInsn(184, "java/lang/Float", "valueOf", "(F)Ljava/lang/Float;");
        } else if (propertyClass == Double.TYPE) {
            mw.visitVarInsn(24, context.var("double", 2));
            mw.visitMethodInsn(184, "java/lang/Double", "valueOf", "(D)Ljava/lang/Double;");
        } else if (propertyClass == Boolean.TYPE) {
            mw.visitVarInsn(21, context.var("boolean"));
            mw.visitMethodInsn(184, "java/lang/Boolean", "valueOf", "(Z)Ljava/lang/Boolean;");
        } else if (propertyClass == BigDecimal.class) {
            mw.visitVarInsn(25, context.var("decimal"));
        } else if (propertyClass == String.class) {
            mw.visitVarInsn(25, context.var("string"));
        } else if (propertyClass.isEnum()) {
            mw.visitVarInsn(25, context.var("enum"));
        } else if (List.class.isAssignableFrom(propertyClass)) {
            mw.visitVarInsn(25, context.var("list"));
        } else {
            mw.visitVarInsn(25, context.var("object"));
        }
        mw.visitMethodInsn(182, JavaBeanSerializer, "apply", "(L" + JSONSerializer + ";Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)Z");
    }

    private void _processValue(MethodVisitor mw, FieldInfo fieldInfo, Context context, Label _end) {
        Label processKeyElse_ = new Label();
        Class<?> fieldClass = fieldInfo.fieldClass;
        if (fieldClass.isPrimitive()) {
            Label checkValueEnd_ = new Label();
            mw.visitVarInsn(21, context.var("checkValue"));
            mw.visitJumpInsn(154, checkValueEnd_);
            mw.visitInsn(1);
            mw.visitInsn(89);
            mw.visitVarInsn(58, Context.original);
            mw.visitVarInsn(58, Context.processValue);
            mw.visitJumpInsn(167, processKeyElse_);
            mw.visitLabel(checkValueEnd_);
        }
        mw.visitVarInsn(25, 0);
        mw.visitVarInsn(25, 1);
        mw.visitVarInsn(25, 0);
        mw.visitLdcInsn(context.getFieldOrinal(fieldInfo.name));
        mw.visitMethodInsn(182, JavaBeanSerializer, "getBeanContext", "(I)" + ASMUtils.desc(BeanContext.class));
        mw.visitVarInsn(25, 2);
        mw.visitVarInsn(25, Context.fieldName);
        String valueDesc = "Ljava/lang/Object;";
        if (fieldClass == Byte.TYPE) {
            mw.visitVarInsn(21, context.var("byte"));
            mw.visitMethodInsn(184, "java/lang/Byte", "valueOf", "(B)Ljava/lang/Byte;");
            mw.visitInsn(89);
            mw.visitVarInsn(58, Context.original);
        } else if (fieldClass == Short.TYPE) {
            mw.visitVarInsn(21, context.var("short"));
            mw.visitMethodInsn(184, "java/lang/Short", "valueOf", "(S)Ljava/lang/Short;");
            mw.visitInsn(89);
            mw.visitVarInsn(58, Context.original);
        } else if (fieldClass == Integer.TYPE) {
            mw.visitVarInsn(21, context.var("int"));
            mw.visitMethodInsn(184, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
            mw.visitInsn(89);
            mw.visitVarInsn(58, Context.original);
        } else if (fieldClass == Character.TYPE) {
            mw.visitVarInsn(21, context.var("char"));
            mw.visitMethodInsn(184, "java/lang/Character", "valueOf", "(C)Ljava/lang/Character;");
            mw.visitInsn(89);
            mw.visitVarInsn(58, Context.original);
        } else if (fieldClass == Long.TYPE) {
            mw.visitVarInsn(22, context.var("long", 2));
            mw.visitMethodInsn(184, "java/lang/Long", "valueOf", "(J)Ljava/lang/Long;");
            mw.visitInsn(89);
            mw.visitVarInsn(58, Context.original);
        } else if (fieldClass == Float.TYPE) {
            mw.visitVarInsn(23, context.var("float"));
            mw.visitMethodInsn(184, "java/lang/Float", "valueOf", "(F)Ljava/lang/Float;");
            mw.visitInsn(89);
            mw.visitVarInsn(58, Context.original);
        } else if (fieldClass == Double.TYPE) {
            mw.visitVarInsn(24, context.var("double", 2));
            mw.visitMethodInsn(184, "java/lang/Double", "valueOf", "(D)Ljava/lang/Double;");
            mw.visitInsn(89);
            mw.visitVarInsn(58, Context.original);
        } else if (fieldClass == Boolean.TYPE) {
            mw.visitVarInsn(21, context.var("boolean"));
            mw.visitMethodInsn(184, "java/lang/Boolean", "valueOf", "(Z)Ljava/lang/Boolean;");
            mw.visitInsn(89);
            mw.visitVarInsn(58, Context.original);
        } else if (fieldClass == BigDecimal.class) {
            mw.visitVarInsn(25, context.var("decimal"));
            mw.visitVarInsn(58, Context.original);
            mw.visitVarInsn(25, Context.original);
        } else if (fieldClass == String.class) {
            mw.visitVarInsn(25, context.var("string"));
            mw.visitVarInsn(58, Context.original);
            mw.visitVarInsn(25, Context.original);
        } else if (fieldClass.isEnum()) {
            mw.visitVarInsn(25, context.var("enum"));
            mw.visitVarInsn(58, Context.original);
            mw.visitVarInsn(25, Context.original);
        } else if (List.class.isAssignableFrom(fieldClass)) {
            mw.visitVarInsn(25, context.var("list"));
            mw.visitVarInsn(58, Context.original);
            mw.visitVarInsn(25, Context.original);
        } else {
            mw.visitVarInsn(25, context.var("object"));
            mw.visitVarInsn(58, Context.original);
            mw.visitVarInsn(25, Context.original);
        }
        mw.visitMethodInsn(182, JavaBeanSerializer, "processValue", "(L" + JSONSerializer + ";" + ASMUtils.desc(BeanContext.class) + "Ljava/lang/Object;Ljava/lang/String;" + valueDesc + ")Ljava/lang/Object;");
        mw.visitVarInsn(58, Context.processValue);
        mw.visitVarInsn(25, Context.original);
        mw.visitVarInsn(25, Context.processValue);
        mw.visitJumpInsn(165, processKeyElse_);
        this._writeObject(mw, fieldInfo, context, _end);
        mw.visitJumpInsn(167, _end);
        mw.visitLabel(processKeyElse_);
    }

    private void _processKey(MethodVisitor mw, FieldInfo property, Context context) {
        Label _else_processKey = new Label();
        mw.visitVarInsn(21, context.var("hasNameFilters"));
        mw.visitJumpInsn(153, _else_processKey);
        Class<?> propertyClass = property.fieldClass;
        mw.visitVarInsn(25, 0);
        mw.visitVarInsn(25, 1);
        mw.visitVarInsn(25, 2);
        mw.visitVarInsn(25, Context.fieldName);
        if (propertyClass == Byte.TYPE) {
            mw.visitVarInsn(21, context.var("byte"));
            mw.visitMethodInsn(184, "java/lang/Byte", "valueOf", "(B)Ljava/lang/Byte;");
        } else if (propertyClass == Short.TYPE) {
            mw.visitVarInsn(21, context.var("short"));
            mw.visitMethodInsn(184, "java/lang/Short", "valueOf", "(S)Ljava/lang/Short;");
        } else if (propertyClass == Integer.TYPE) {
            mw.visitVarInsn(21, context.var("int"));
            mw.visitMethodInsn(184, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
        } else if (propertyClass == Character.TYPE) {
            mw.visitVarInsn(21, context.var("char"));
            mw.visitMethodInsn(184, "java/lang/Character", "valueOf", "(C)Ljava/lang/Character;");
        } else if (propertyClass == Long.TYPE) {
            mw.visitVarInsn(22, context.var("long", 2));
            mw.visitMethodInsn(184, "java/lang/Long", "valueOf", "(J)Ljava/lang/Long;");
        } else if (propertyClass == Float.TYPE) {
            mw.visitVarInsn(23, context.var("float"));
            mw.visitMethodInsn(184, "java/lang/Float", "valueOf", "(F)Ljava/lang/Float;");
        } else if (propertyClass == Double.TYPE) {
            mw.visitVarInsn(24, context.var("double", 2));
            mw.visitMethodInsn(184, "java/lang/Double", "valueOf", "(D)Ljava/lang/Double;");
        } else if (propertyClass == Boolean.TYPE) {
            mw.visitVarInsn(21, context.var("boolean"));
            mw.visitMethodInsn(184, "java/lang/Boolean", "valueOf", "(Z)Ljava/lang/Boolean;");
        } else if (propertyClass == BigDecimal.class) {
            mw.visitVarInsn(25, context.var("decimal"));
        } else if (propertyClass == String.class) {
            mw.visitVarInsn(25, context.var("string"));
        } else if (propertyClass.isEnum()) {
            mw.visitVarInsn(25, context.var("enum"));
        } else if (List.class.isAssignableFrom(propertyClass)) {
            mw.visitVarInsn(25, context.var("list"));
        } else {
            mw.visitVarInsn(25, context.var("object"));
        }
        mw.visitMethodInsn(182, JavaBeanSerializer, "processKey", "(L" + JSONSerializer + ";Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/String;");
        mw.visitVarInsn(58, Context.fieldName);
        mw.visitLabel(_else_processKey);
    }

    private void _if_write_null(MethodVisitor mw, FieldInfo fieldInfo, Context context) {
        int writeNullFeatures;
        JSONType jsonType;
        Class<?> propertyClass = fieldInfo.fieldClass;
        Label _if = new Label();
        Label _else = new Label();
        Label _write_null = new Label();
        Label _end_if = new Label();
        mw.visitLabel(_if);
        JSONField annotation = fieldInfo.getAnnotation();
        int features = 0;
        if (annotation != null) {
            features = SerializerFeature.of(annotation.serialzeFeatures());
        }
        if ((jsonType = ((Context)context).beanInfo.jsonType) != null) {
            features |= SerializerFeature.of(jsonType.serialzeFeatures());
        }
        if ((features & (writeNullFeatures = propertyClass == String.class ? SerializerFeature.WriteMapNullValue.getMask() | SerializerFeature.WriteNullStringAsEmpty.getMask() : (Number.class.isAssignableFrom(propertyClass) ? SerializerFeature.WriteMapNullValue.getMask() | SerializerFeature.WriteNullNumberAsZero.getMask() : (Collection.class.isAssignableFrom(propertyClass) ? SerializerFeature.WriteMapNullValue.getMask() | SerializerFeature.WriteNullListAsEmpty.getMask() : (Boolean.class == propertyClass ? SerializerFeature.WriteMapNullValue.getMask() | SerializerFeature.WriteNullBooleanAsFalse.getMask() : SerializerFeature.WRITE_MAP_NULL_FEATURES))))) == 0) {
            mw.visitVarInsn(25, context.var("out"));
            mw.visitLdcInsn(writeNullFeatures);
            mw.visitMethodInsn(182, SerializeWriter, "isEnabled", "(I)Z");
            mw.visitJumpInsn(153, _else);
        }
        mw.visitLabel(_write_null);
        mw.visitVarInsn(25, context.var("out"));
        mw.visitVarInsn(21, context.var("seperator"));
        mw.visitMethodInsn(182, SerializeWriter, "write", "(I)V");
        this._writeFieldName(mw, context);
        mw.visitVarInsn(25, context.var("out"));
        mw.visitLdcInsn(features);
        if (propertyClass == String.class || propertyClass == Character.class) {
            mw.visitLdcInsn(SerializerFeature.WriteNullStringAsEmpty.mask);
        } else if (Number.class.isAssignableFrom(propertyClass)) {
            mw.visitLdcInsn(SerializerFeature.WriteNullNumberAsZero.mask);
        } else if (propertyClass == Boolean.class) {
            mw.visitLdcInsn(SerializerFeature.WriteNullBooleanAsFalse.mask);
        } else if (Collection.class.isAssignableFrom(propertyClass) || propertyClass.isArray()) {
            mw.visitLdcInsn(SerializerFeature.WriteNullListAsEmpty.mask);
        } else {
            mw.visitLdcInsn(0);
        }
        mw.visitMethodInsn(182, SerializeWriter, "writeNull", "(II)V");
        this._seperator(mw, context);
        mw.visitJumpInsn(167, _end_if);
        mw.visitLabel(_else);
        mw.visitLabel(_end_if);
    }

    private void _writeFieldName(MethodVisitor mw, Context context) {
        if (context.writeDirect) {
            mw.visitVarInsn(25, context.var("out"));
            mw.visitVarInsn(25, Context.fieldName);
            mw.visitMethodInsn(182, SerializeWriter, "writeFieldNameDirect", "(Ljava/lang/String;)V");
        } else {
            mw.visitVarInsn(25, context.var("out"));
            mw.visitVarInsn(25, Context.fieldName);
            mw.visitInsn(3);
            mw.visitMethodInsn(182, SerializeWriter, "writeFieldName", "(Ljava/lang/String;Z)V");
        }
    }

    private void _seperator(MethodVisitor mw, Context context) {
        mw.visitVarInsn(16, 44);
        mw.visitVarInsn(54, context.var("seperator"));
    }

    private void _getListFieldItemSer(Context context, MethodVisitor mw, FieldInfo fieldInfo, Class<?> itemType) {
        Label notNull_ = new Label();
        mw.visitVarInsn(25, 0);
        mw.visitFieldInsn(180, context.className, fieldInfo.name + "_asm_list_item_ser_", ObjectSerializer_desc);
        mw.visitJumpInsn(199, notNull_);
        mw.visitVarInsn(25, 0);
        mw.visitVarInsn(25, 1);
        mw.visitLdcInsn(com.alibaba.fastjson.asm.Type.getType(ASMUtils.desc(itemType)));
        mw.visitMethodInsn(182, JSONSerializer, "getObjectWriter", "(Ljava/lang/Class;)" + ObjectSerializer_desc);
        mw.visitFieldInsn(181, context.className, fieldInfo.name + "_asm_list_item_ser_", ObjectSerializer_desc);
        mw.visitLabel(notNull_);
        mw.visitVarInsn(25, 0);
        mw.visitFieldInsn(180, context.className, fieldInfo.name + "_asm_list_item_ser_", ObjectSerializer_desc);
    }

    private void _getFieldSer(Context context, MethodVisitor mw, FieldInfo fieldInfo) {
        Label notNull_ = new Label();
        mw.visitVarInsn(25, 0);
        mw.visitFieldInsn(180, context.className, fieldInfo.name + "_asm_ser_", ObjectSerializer_desc);
        mw.visitJumpInsn(199, notNull_);
        mw.visitVarInsn(25, 0);
        mw.visitVarInsn(25, 1);
        mw.visitLdcInsn(com.alibaba.fastjson.asm.Type.getType(ASMUtils.desc(fieldInfo.fieldClass)));
        mw.visitMethodInsn(182, JSONSerializer, "getObjectWriter", "(Ljava/lang/Class;)" + ObjectSerializer_desc);
        mw.visitFieldInsn(181, context.className, fieldInfo.name + "_asm_ser_", ObjectSerializer_desc);
        mw.visitLabel(notNull_);
        mw.visitVarInsn(25, 0);
        mw.visitFieldInsn(180, context.className, fieldInfo.name + "_asm_ser_", ObjectSerializer_desc);
    }

    static class Context {
        static final int serializer = 1;
        static final int obj = 2;
        static final int paramFieldName = 3;
        static final int paramFieldType = 4;
        static final int features = 5;
        static int fieldName = 6;
        static int original = 7;
        static int processValue = 8;
        private final FieldInfo[] getters;
        private final String className;
        private final SerializeBeanInfo beanInfo;
        private final boolean writeDirect;
        private Map<String, Integer> variants = new HashMap<String, Integer>();
        private int variantIndex = 9;
        private final boolean nonContext;

        public Context(FieldInfo[] getters, SerializeBeanInfo beanInfo, String className, boolean writeDirect, boolean nonContext) {
            this.getters = getters;
            this.className = className;
            this.beanInfo = beanInfo;
            this.writeDirect = writeDirect;
            this.nonContext = nonContext || beanInfo.beanType.isEnum();
        }

        public int var(String name) {
            Integer i = this.variants.get(name);
            if (i == null) {
                this.variants.put(name, this.variantIndex++);
            }
            i = this.variants.get(name);
            return i;
        }

        public int var(String name, int increment) {
            Integer i = this.variants.get(name);
            if (i == null) {
                this.variants.put(name, this.variantIndex);
                this.variantIndex += increment;
            }
            i = this.variants.get(name);
            return i;
        }

        public int getFieldOrinal(String name) {
            int fieldIndex = -1;
            int size = this.getters.length;
            for (int i = 0; i < size; ++i) {
                FieldInfo item = this.getters[i];
                if (!item.name.equals(name)) continue;
                fieldIndex = i;
                break;
            }
            return fieldIndex;
        }
    }
}

