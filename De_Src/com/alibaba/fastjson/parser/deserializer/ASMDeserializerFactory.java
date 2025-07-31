/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.asm.ClassWriter;
import com.alibaba.fastjson.asm.FieldWriter;
import com.alibaba.fastjson.asm.Label;
import com.alibaba.fastjson.asm.MethodVisitor;
import com.alibaba.fastjson.asm.MethodWriter;
import com.alibaba.fastjson.asm.Opcodes;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.JSONLexerBase;
import com.alibaba.fastjson.parser.ParseContext;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.parser.SymbolTable;
import com.alibaba.fastjson.parser.deserializer.EnumDeserializer;
import com.alibaba.fastjson.parser.deserializer.FieldDeserializer;
import com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.util.ASMClassLoader;
import com.alibaba.fastjson.util.ASMUtils;
import com.alibaba.fastjson.util.FieldInfo;
import com.alibaba.fastjson.util.IOUtils;
import com.alibaba.fastjson.util.JavaBeanInfo;
import com.alibaba.fastjson.util.TypeUtils;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class ASMDeserializerFactory
implements Opcodes {
    public final ASMClassLoader classLoader;
    protected final AtomicLong seed = new AtomicLong();
    static final String DefaultJSONParser = ASMUtils.type(DefaultJSONParser.class);
    static final String JSONLexerBase = ASMUtils.type(JSONLexerBase.class);

    public ASMDeserializerFactory(ClassLoader parentClassLoader) {
        this.classLoader = parentClassLoader instanceof ASMClassLoader ? (ASMClassLoader)parentClassLoader : new ASMClassLoader(parentClassLoader);
    }

    public ObjectDeserializer createJavaBeanDeserializer(ParserConfig config, JavaBeanInfo beanInfo) throws Exception {
        String classNameFull;
        String classNameType;
        Class<?> clazz = beanInfo.clazz;
        if (clazz.isPrimitive()) {
            throw new IllegalArgumentException("not support type :" + clazz.getName());
        }
        String className = "FastjsonASMDeserializer_" + this.seed.incrementAndGet() + "_" + clazz.getSimpleName();
        Package pkg = ASMDeserializerFactory.class.getPackage();
        if (pkg != null) {
            String packageName = pkg.getName();
            classNameType = packageName.replace('.', '/') + "/" + className;
            classNameFull = packageName + "." + className;
        } else {
            classNameType = className;
            classNameFull = className;
        }
        ClassWriter cw = new ClassWriter();
        cw.visit(49, 33, classNameType, ASMUtils.type(JavaBeanDeserializer.class), null);
        this._init(cw, new Context(classNameType, config, beanInfo, 3));
        this._createInstance(cw, new Context(classNameType, config, beanInfo, 3));
        this._deserialze(cw, new Context(classNameType, config, beanInfo, 5));
        this._deserialzeArrayMapping(cw, new Context(classNameType, config, beanInfo, 4));
        byte[] code = cw.toByteArray();
        Class<?> deserClass = this.classLoader.defineClassPublic(classNameFull, code, 0, code.length);
        Constructor<?> constructor = deserClass.getConstructor(ParserConfig.class, JavaBeanInfo.class);
        Object instance = constructor.newInstance(config, beanInfo);
        return (ObjectDeserializer)instance;
    }

    private void _setFlag(MethodVisitor mw, Context context, int i) {
        String varName = "_asm_flag_" + i / 32;
        mw.visitVarInsn(21, context.var(varName));
        mw.visitLdcInsn(1 << i);
        mw.visitInsn(128);
        mw.visitVarInsn(54, context.var(varName));
    }

    private void _isFlag(MethodVisitor mw, Context context, int i, Label label) {
        mw.visitVarInsn(21, context.var("_asm_flag_" + i / 32));
        mw.visitLdcInsn(1 << i);
        mw.visitInsn(126);
        mw.visitJumpInsn(153, label);
    }

    private void _deserialzeArrayMapping(ClassWriter cw, Context context) {
        MethodWriter mw = new MethodWriter(cw, 1, "deserialzeArrayMapping", "(L" + DefaultJSONParser + ";Ljava/lang/reflect/Type;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", null, null);
        this.defineVarLexer(context, mw);
        mw.visitVarInsn(25, context.var("lexer"));
        mw.visitVarInsn(25, 1);
        mw.visitMethodInsn(182, DefaultJSONParser, "getSymbolTable", "()" + ASMUtils.desc(SymbolTable.class));
        mw.visitMethodInsn(182, JSONLexerBase, "scanTypeName", "(" + ASMUtils.desc(SymbolTable.class) + ")Ljava/lang/String;");
        mw.visitVarInsn(58, context.var("typeName"));
        Label typeNameNotNull_ = new Label();
        mw.visitVarInsn(25, context.var("typeName"));
        mw.visitJumpInsn(198, typeNameNotNull_);
        mw.visitVarInsn(25, 1);
        mw.visitMethodInsn(182, DefaultJSONParser, "getConfig", "()" + ASMUtils.desc(ParserConfig.class));
        mw.visitVarInsn(25, 0);
        mw.visitFieldInsn(180, ASMUtils.type(JavaBeanDeserializer.class), "beanInfo", ASMUtils.desc(JavaBeanInfo.class));
        mw.visitVarInsn(25, context.var("typeName"));
        mw.visitMethodInsn(184, ASMUtils.type(JavaBeanDeserializer.class), "getSeeAlso", "(" + ASMUtils.desc(ParserConfig.class) + ASMUtils.desc(JavaBeanInfo.class) + "Ljava/lang/String;)" + ASMUtils.desc(JavaBeanDeserializer.class));
        mw.visitVarInsn(58, context.var("userTypeDeser"));
        mw.visitVarInsn(25, context.var("userTypeDeser"));
        mw.visitTypeInsn(193, ASMUtils.type(JavaBeanDeserializer.class));
        mw.visitJumpInsn(153, typeNameNotNull_);
        mw.visitVarInsn(25, context.var("userTypeDeser"));
        mw.visitVarInsn(25, 1);
        mw.visitVarInsn(25, 2);
        mw.visitVarInsn(25, 3);
        mw.visitVarInsn(25, 4);
        mw.visitMethodInsn(182, ASMUtils.type(JavaBeanDeserializer.class), "deserialzeArrayMapping", "(L" + DefaultJSONParser + ";Ljava/lang/reflect/Type;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;");
        mw.visitInsn(176);
        mw.visitLabel(typeNameNotNull_);
        this._createInstance(context, mw);
        FieldInfo[] sortedFieldInfoList = ((Context)context).beanInfo.sortedFields;
        int fieldListSize = sortedFieldInfoList.length;
        for (int i = 0; i < fieldListSize; ++i) {
            Label valueNullEnd_;
            boolean last = i == fieldListSize - 1;
            int seperator = last ? 93 : 44;
            FieldInfo fieldInfo = sortedFieldInfoList[i];
            Class<?> fieldClass = fieldInfo.fieldClass;
            Type fieldType = fieldInfo.fieldType;
            if (fieldClass == Byte.TYPE || fieldClass == Short.TYPE || fieldClass == Integer.TYPE) {
                mw.visitVarInsn(25, context.var("lexer"));
                mw.visitVarInsn(16, seperator);
                mw.visitMethodInsn(182, JSONLexerBase, "scanInt", "(C)I");
                mw.visitVarInsn(54, context.var_asm(fieldInfo));
                continue;
            }
            if (fieldClass == Byte.class) {
                mw.visitVarInsn(25, context.var("lexer"));
                mw.visitVarInsn(16, seperator);
                mw.visitMethodInsn(182, JSONLexerBase, "scanInt", "(C)I");
                mw.visitMethodInsn(184, "java/lang/Byte", "valueOf", "(B)Ljava/lang/Byte;");
                mw.visitVarInsn(58, context.var_asm(fieldInfo));
                valueNullEnd_ = new Label();
                mw.visitVarInsn(25, context.var("lexer"));
                mw.visitFieldInsn(180, JSONLexerBase, "matchStat", "I");
                mw.visitLdcInsn(5);
                mw.visitJumpInsn(160, valueNullEnd_);
                mw.visitInsn(1);
                mw.visitVarInsn(58, context.var_asm(fieldInfo));
                mw.visitLabel(valueNullEnd_);
                continue;
            }
            if (fieldClass == Short.class) {
                mw.visitVarInsn(25, context.var("lexer"));
                mw.visitVarInsn(16, seperator);
                mw.visitMethodInsn(182, JSONLexerBase, "scanInt", "(C)I");
                mw.visitMethodInsn(184, "java/lang/Short", "valueOf", "(S)Ljava/lang/Short;");
                mw.visitVarInsn(58, context.var_asm(fieldInfo));
                valueNullEnd_ = new Label();
                mw.visitVarInsn(25, context.var("lexer"));
                mw.visitFieldInsn(180, JSONLexerBase, "matchStat", "I");
                mw.visitLdcInsn(5);
                mw.visitJumpInsn(160, valueNullEnd_);
                mw.visitInsn(1);
                mw.visitVarInsn(58, context.var_asm(fieldInfo));
                mw.visitLabel(valueNullEnd_);
                continue;
            }
            if (fieldClass == Integer.class) {
                mw.visitVarInsn(25, context.var("lexer"));
                mw.visitVarInsn(16, seperator);
                mw.visitMethodInsn(182, JSONLexerBase, "scanInt", "(C)I");
                mw.visitMethodInsn(184, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
                mw.visitVarInsn(58, context.var_asm(fieldInfo));
                valueNullEnd_ = new Label();
                mw.visitVarInsn(25, context.var("lexer"));
                mw.visitFieldInsn(180, JSONLexerBase, "matchStat", "I");
                mw.visitLdcInsn(5);
                mw.visitJumpInsn(160, valueNullEnd_);
                mw.visitInsn(1);
                mw.visitVarInsn(58, context.var_asm(fieldInfo));
                mw.visitLabel(valueNullEnd_);
                continue;
            }
            if (fieldClass == Long.TYPE) {
                mw.visitVarInsn(25, context.var("lexer"));
                mw.visitVarInsn(16, seperator);
                mw.visitMethodInsn(182, JSONLexerBase, "scanLong", "(C)J");
                mw.visitVarInsn(55, context.var_asm(fieldInfo, 2));
                continue;
            }
            if (fieldClass == Long.class) {
                mw.visitVarInsn(25, context.var("lexer"));
                mw.visitVarInsn(16, seperator);
                mw.visitMethodInsn(182, JSONLexerBase, "scanLong", "(C)J");
                mw.visitMethodInsn(184, "java/lang/Long", "valueOf", "(J)Ljava/lang/Long;");
                mw.visitVarInsn(58, context.var_asm(fieldInfo));
                valueNullEnd_ = new Label();
                mw.visitVarInsn(25, context.var("lexer"));
                mw.visitFieldInsn(180, JSONLexerBase, "matchStat", "I");
                mw.visitLdcInsn(5);
                mw.visitJumpInsn(160, valueNullEnd_);
                mw.visitInsn(1);
                mw.visitVarInsn(58, context.var_asm(fieldInfo));
                mw.visitLabel(valueNullEnd_);
                continue;
            }
            if (fieldClass == Boolean.TYPE) {
                mw.visitVarInsn(25, context.var("lexer"));
                mw.visitVarInsn(16, seperator);
                mw.visitMethodInsn(182, JSONLexerBase, "scanBoolean", "(C)Z");
                mw.visitVarInsn(54, context.var_asm(fieldInfo));
                continue;
            }
            if (fieldClass == Float.TYPE) {
                mw.visitVarInsn(25, context.var("lexer"));
                mw.visitVarInsn(16, seperator);
                mw.visitMethodInsn(182, JSONLexerBase, "scanFloat", "(C)F");
                mw.visitVarInsn(56, context.var_asm(fieldInfo));
                continue;
            }
            if (fieldClass == Float.class) {
                mw.visitVarInsn(25, context.var("lexer"));
                mw.visitVarInsn(16, seperator);
                mw.visitMethodInsn(182, JSONLexerBase, "scanFloat", "(C)F");
                mw.visitMethodInsn(184, "java/lang/Float", "valueOf", "(F)Ljava/lang/Float;");
                mw.visitVarInsn(58, context.var_asm(fieldInfo));
                valueNullEnd_ = new Label();
                mw.visitVarInsn(25, context.var("lexer"));
                mw.visitFieldInsn(180, JSONLexerBase, "matchStat", "I");
                mw.visitLdcInsn(5);
                mw.visitJumpInsn(160, valueNullEnd_);
                mw.visitInsn(1);
                mw.visitVarInsn(58, context.var_asm(fieldInfo));
                mw.visitLabel(valueNullEnd_);
                continue;
            }
            if (fieldClass == Double.TYPE) {
                mw.visitVarInsn(25, context.var("lexer"));
                mw.visitVarInsn(16, seperator);
                mw.visitMethodInsn(182, JSONLexerBase, "scanDouble", "(C)D");
                mw.visitVarInsn(57, context.var_asm(fieldInfo, 2));
                continue;
            }
            if (fieldClass == Double.class) {
                mw.visitVarInsn(25, context.var("lexer"));
                mw.visitVarInsn(16, seperator);
                mw.visitMethodInsn(182, JSONLexerBase, "scanDouble", "(C)D");
                mw.visitMethodInsn(184, "java/lang/Double", "valueOf", "(D)Ljava/lang/Double;");
                mw.visitVarInsn(58, context.var_asm(fieldInfo));
                valueNullEnd_ = new Label();
                mw.visitVarInsn(25, context.var("lexer"));
                mw.visitFieldInsn(180, JSONLexerBase, "matchStat", "I");
                mw.visitLdcInsn(5);
                mw.visitJumpInsn(160, valueNullEnd_);
                mw.visitInsn(1);
                mw.visitVarInsn(58, context.var_asm(fieldInfo));
                mw.visitLabel(valueNullEnd_);
                continue;
            }
            if (fieldClass == Character.TYPE) {
                mw.visitVarInsn(25, context.var("lexer"));
                mw.visitVarInsn(16, seperator);
                mw.visitMethodInsn(182, JSONLexerBase, "scanString", "(C)Ljava/lang/String;");
                mw.visitInsn(3);
                mw.visitMethodInsn(182, "java/lang/String", "charAt", "(I)C");
                mw.visitVarInsn(54, context.var_asm(fieldInfo));
                continue;
            }
            if (fieldClass == String.class) {
                mw.visitVarInsn(25, context.var("lexer"));
                mw.visitVarInsn(16, seperator);
                mw.visitMethodInsn(182, JSONLexerBase, "scanString", "(C)Ljava/lang/String;");
                mw.visitVarInsn(58, context.var_asm(fieldInfo));
                continue;
            }
            if (fieldClass == BigDecimal.class) {
                mw.visitVarInsn(25, context.var("lexer"));
                mw.visitVarInsn(16, seperator);
                mw.visitMethodInsn(182, JSONLexerBase, "scanDecimal", "(C)Ljava/math/BigDecimal;");
                mw.visitVarInsn(58, context.var_asm(fieldInfo));
                continue;
            }
            if (fieldClass == Date.class) {
                mw.visitVarInsn(25, context.var("lexer"));
                mw.visitVarInsn(16, seperator);
                mw.visitMethodInsn(182, JSONLexerBase, "scanDate", "(C)Ljava/util/Date;");
                mw.visitVarInsn(58, context.var_asm(fieldInfo));
                continue;
            }
            if (fieldClass == UUID.class) {
                mw.visitVarInsn(25, context.var("lexer"));
                mw.visitVarInsn(16, seperator);
                mw.visitMethodInsn(182, JSONLexerBase, "scanUUID", "(C)Ljava/util/UUID;");
                mw.visitVarInsn(58, context.var_asm(fieldInfo));
                continue;
            }
            if (fieldClass.isEnum()) {
                Label enumNumIf_ = new Label();
                Label enumNumErr_ = new Label();
                Label enumStore_ = new Label();
                Label enumQuote_ = new Label();
                mw.visitVarInsn(25, context.var("lexer"));
                mw.visitMethodInsn(182, JSONLexerBase, "getCurrent", "()C");
                mw.visitInsn(89);
                mw.visitVarInsn(54, context.var("ch"));
                mw.visitLdcInsn(110);
                mw.visitJumpInsn(159, enumQuote_);
                mw.visitVarInsn(21, context.var("ch"));
                mw.visitLdcInsn(34);
                mw.visitJumpInsn(160, enumNumIf_);
                mw.visitLabel(enumQuote_);
                mw.visitVarInsn(25, context.var("lexer"));
                mw.visitLdcInsn(com.alibaba.fastjson.asm.Type.getType(ASMUtils.desc(fieldClass)));
                mw.visitVarInsn(25, 1);
                mw.visitMethodInsn(182, DefaultJSONParser, "getSymbolTable", "()" + ASMUtils.desc(SymbolTable.class));
                mw.visitVarInsn(16, seperator);
                mw.visitMethodInsn(182, JSONLexerBase, "scanEnum", "(Ljava/lang/Class;" + ASMUtils.desc(SymbolTable.class) + "C)Ljava/lang/Enum;");
                mw.visitJumpInsn(167, enumStore_);
                mw.visitLabel(enumNumIf_);
                mw.visitVarInsn(21, context.var("ch"));
                mw.visitLdcInsn(48);
                mw.visitJumpInsn(161, enumNumErr_);
                mw.visitVarInsn(21, context.var("ch"));
                mw.visitLdcInsn(57);
                mw.visitJumpInsn(163, enumNumErr_);
                this._getFieldDeser(context, mw, fieldInfo);
                mw.visitTypeInsn(192, ASMUtils.type(EnumDeserializer.class));
                mw.visitVarInsn(25, context.var("lexer"));
                mw.visitVarInsn(16, seperator);
                mw.visitMethodInsn(182, JSONLexerBase, "scanInt", "(C)I");
                mw.visitMethodInsn(182, ASMUtils.type(EnumDeserializer.class), "valueOf", "(I)Ljava/lang/Enum;");
                mw.visitJumpInsn(167, enumStore_);
                mw.visitLabel(enumNumErr_);
                mw.visitVarInsn(25, 0);
                mw.visitVarInsn(25, context.var("lexer"));
                mw.visitVarInsn(16, seperator);
                mw.visitMethodInsn(182, ASMUtils.type(JavaBeanDeserializer.class), "scanEnum", "(L" + JSONLexerBase + ";C)Ljava/lang/Enum;");
                mw.visitLabel(enumStore_);
                mw.visitTypeInsn(192, ASMUtils.type(fieldClass));
                mw.visitVarInsn(58, context.var_asm(fieldInfo));
                continue;
            }
            if (Collection.class.isAssignableFrom(fieldClass)) {
                Class<?> itemClass = TypeUtils.getCollectionItemClass(fieldType);
                if (itemClass == String.class) {
                    if (fieldClass == List.class || fieldClass == Collections.class || fieldClass == ArrayList.class) {
                        mw.visitTypeInsn(187, ASMUtils.type(ArrayList.class));
                        mw.visitInsn(89);
                        mw.visitMethodInsn(183, ASMUtils.type(ArrayList.class), "<init>", "()V");
                    } else {
                        mw.visitLdcInsn(com.alibaba.fastjson.asm.Type.getType(ASMUtils.desc(fieldClass)));
                        mw.visitMethodInsn(184, ASMUtils.type(TypeUtils.class), "createCollection", "(Ljava/lang/Class;)Ljava/util/Collection;");
                    }
                    mw.visitVarInsn(58, context.var_asm(fieldInfo));
                    mw.visitVarInsn(25, context.var("lexer"));
                    mw.visitVarInsn(25, context.var_asm(fieldInfo));
                    mw.visitVarInsn(16, seperator);
                    mw.visitMethodInsn(182, JSONLexerBase, "scanStringArray", "(Ljava/util/Collection;C)V");
                    Label valueNullEnd_2 = new Label();
                    mw.visitVarInsn(25, context.var("lexer"));
                    mw.visitFieldInsn(180, JSONLexerBase, "matchStat", "I");
                    mw.visitLdcInsn(5);
                    mw.visitJumpInsn(160, valueNullEnd_2);
                    mw.visitInsn(1);
                    mw.visitVarInsn(58, context.var_asm(fieldInfo));
                    mw.visitLabel(valueNullEnd_2);
                    continue;
                }
                Label notError_ = new Label();
                mw.visitVarInsn(25, context.var("lexer"));
                mw.visitMethodInsn(182, JSONLexerBase, "token", "()I");
                mw.visitVarInsn(54, context.var("token"));
                mw.visitVarInsn(21, context.var("token"));
                int token = i == 0 ? 14 : 16;
                mw.visitLdcInsn(token);
                mw.visitJumpInsn(159, notError_);
                mw.visitVarInsn(25, 1);
                mw.visitLdcInsn(token);
                mw.visitMethodInsn(182, DefaultJSONParser, "throwException", "(I)V");
                mw.visitLabel(notError_);
                Label quickElse_ = new Label();
                Label quickEnd_ = new Label();
                mw.visitVarInsn(25, context.var("lexer"));
                mw.visitMethodInsn(182, JSONLexerBase, "getCurrent", "()C");
                mw.visitVarInsn(16, 91);
                mw.visitJumpInsn(160, quickElse_);
                mw.visitVarInsn(25, context.var("lexer"));
                mw.visitMethodInsn(182, JSONLexerBase, "next", "()C");
                mw.visitInsn(87);
                mw.visitVarInsn(25, context.var("lexer"));
                mw.visitLdcInsn(14);
                mw.visitMethodInsn(182, JSONLexerBase, "setToken", "(I)V");
                mw.visitJumpInsn(167, quickEnd_);
                mw.visitLabel(quickElse_);
                mw.visitVarInsn(25, context.var("lexer"));
                mw.visitLdcInsn(14);
                mw.visitMethodInsn(182, JSONLexerBase, "nextToken", "(I)V");
                mw.visitLabel(quickEnd_);
                this._newCollection(mw, fieldClass, i, false);
                mw.visitInsn(89);
                mw.visitVarInsn(58, context.var_asm(fieldInfo));
                this._getCollectionFieldItemDeser(context, mw, fieldInfo, itemClass);
                mw.visitVarInsn(25, 1);
                mw.visitLdcInsn(com.alibaba.fastjson.asm.Type.getType(ASMUtils.desc(itemClass)));
                mw.visitVarInsn(25, 3);
                mw.visitMethodInsn(184, ASMUtils.type(JavaBeanDeserializer.class), "parseArray", "(Ljava/util/Collection;" + ASMUtils.desc(ObjectDeserializer.class) + "L" + DefaultJSONParser + ";Ljava/lang/reflect/Type;Ljava/lang/Object;)V");
                continue;
            }
            if (fieldClass.isArray()) {
                mw.visitVarInsn(25, context.var("lexer"));
                mw.visitLdcInsn(14);
                mw.visitMethodInsn(182, JSONLexerBase, "nextToken", "(I)V");
                mw.visitVarInsn(25, 1);
                mw.visitVarInsn(25, 0);
                mw.visitLdcInsn(i);
                mw.visitMethodInsn(182, ASMUtils.type(JavaBeanDeserializer.class), "getFieldType", "(I)Ljava/lang/reflect/Type;");
                mw.visitMethodInsn(182, DefaultJSONParser, "parseObject", "(Ljava/lang/reflect/Type;)Ljava/lang/Object;");
                mw.visitTypeInsn(192, ASMUtils.type(fieldClass));
                mw.visitVarInsn(58, context.var_asm(fieldInfo));
                continue;
            }
            Label objElseIf_ = new Label();
            Label objEndIf_ = new Label();
            if (fieldClass == Date.class) {
                mw.visitVarInsn(25, context.var("lexer"));
                mw.visitMethodInsn(182, JSONLexerBase, "getCurrent", "()C");
                mw.visitLdcInsn(49);
                mw.visitJumpInsn(160, objElseIf_);
                mw.visitTypeInsn(187, ASMUtils.type(Date.class));
                mw.visitInsn(89);
                mw.visitVarInsn(25, context.var("lexer"));
                mw.visitVarInsn(16, seperator);
                mw.visitMethodInsn(182, JSONLexerBase, "scanLong", "(C)J");
                mw.visitMethodInsn(183, ASMUtils.type(Date.class), "<init>", "(J)V");
                mw.visitVarInsn(58, context.var_asm(fieldInfo));
                mw.visitJumpInsn(167, objEndIf_);
            }
            mw.visitLabel(objElseIf_);
            this._quickNextToken(context, mw, 14);
            this._deserObject(context, mw, fieldInfo, fieldClass, i);
            mw.visitVarInsn(25, context.var("lexer"));
            mw.visitMethodInsn(182, JSONLexerBase, "token", "()I");
            mw.visitLdcInsn(15);
            mw.visitJumpInsn(159, objEndIf_);
            mw.visitVarInsn(25, 0);
            mw.visitVarInsn(25, context.var("lexer"));
            if (!last) {
                mw.visitLdcInsn(16);
            } else {
                mw.visitLdcInsn(15);
            }
            mw.visitMethodInsn(183, ASMUtils.type(JavaBeanDeserializer.class), "check", "(" + ASMUtils.desc(JSONLexer.class) + "I)V");
            mw.visitLabel(objEndIf_);
        }
        this._batchSet(context, mw, false);
        Label quickElse_ = new Label();
        Label quickElseIf_ = new Label();
        Label quickElseIfEOI_ = new Label();
        Label quickEnd_ = new Label();
        mw.visitVarInsn(25, context.var("lexer"));
        mw.visitMethodInsn(182, JSONLexerBase, "getCurrent", "()C");
        mw.visitInsn(89);
        mw.visitVarInsn(54, context.var("ch"));
        mw.visitVarInsn(16, 44);
        mw.visitJumpInsn(160, quickElseIf_);
        mw.visitVarInsn(25, context.var("lexer"));
        mw.visitMethodInsn(182, JSONLexerBase, "next", "()C");
        mw.visitInsn(87);
        mw.visitVarInsn(25, context.var("lexer"));
        mw.visitLdcInsn(16);
        mw.visitMethodInsn(182, JSONLexerBase, "setToken", "(I)V");
        mw.visitJumpInsn(167, quickEnd_);
        mw.visitLabel(quickElseIf_);
        mw.visitVarInsn(21, context.var("ch"));
        mw.visitVarInsn(16, 93);
        mw.visitJumpInsn(160, quickElseIfEOI_);
        mw.visitVarInsn(25, context.var("lexer"));
        mw.visitMethodInsn(182, JSONLexerBase, "next", "()C");
        mw.visitInsn(87);
        mw.visitVarInsn(25, context.var("lexer"));
        mw.visitLdcInsn(15);
        mw.visitMethodInsn(182, JSONLexerBase, "setToken", "(I)V");
        mw.visitJumpInsn(167, quickEnd_);
        mw.visitLabel(quickElseIfEOI_);
        mw.visitVarInsn(21, context.var("ch"));
        mw.visitVarInsn(16, 26);
        mw.visitJumpInsn(160, quickElse_);
        mw.visitVarInsn(25, context.var("lexer"));
        mw.visitMethodInsn(182, JSONLexerBase, "next", "()C");
        mw.visitInsn(87);
        mw.visitVarInsn(25, context.var("lexer"));
        mw.visitLdcInsn(20);
        mw.visitMethodInsn(182, JSONLexerBase, "setToken", "(I)V");
        mw.visitJumpInsn(167, quickEnd_);
        mw.visitLabel(quickElse_);
        mw.visitVarInsn(25, context.var("lexer"));
        mw.visitLdcInsn(16);
        mw.visitMethodInsn(182, JSONLexerBase, "nextToken", "(I)V");
        mw.visitLabel(quickEnd_);
        mw.visitVarInsn(25, context.var("instance"));
        mw.visitInsn(176);
        mw.visitMaxs(5, context.variantIndex);
        mw.visitEnd();
    }

    /*
     * Enabled aggressive block sorting
     */
    private void _deserialze(ClassWriter cw, Context context) {
        Class<?> fieldClass;
        FieldInfo fieldInfo;
        int i;
        if (context.fieldInfoList.length == 0) {
            return;
        }
        for (FieldInfo fieldInfo2 : context.fieldInfoList) {
            Class<?> fieldClass2 = fieldInfo2.fieldClass;
            Type fieldType = fieldInfo2.fieldType;
            if (fieldClass2 == Character.TYPE) {
                return;
            }
            if (!Collection.class.isAssignableFrom(fieldClass2)) continue;
            if (!(fieldType instanceof ParameterizedType)) {
                return;
            }
            Type itemType = ((ParameterizedType)fieldType).getActualTypeArguments()[0];
            if (itemType instanceof Class) continue;
            return;
        }
        JavaBeanInfo beanInfo = context.beanInfo;
        Context.access$202(context, beanInfo.sortedFields);
        MethodWriter mw = new MethodWriter(cw, 1, "deserialze", "(L" + DefaultJSONParser + ";Ljava/lang/reflect/Type;Ljava/lang/Object;I)Ljava/lang/Object;", null, null);
        Label reset_ = new Label();
        Label super_ = new Label();
        Label return_ = new Label();
        Label end_ = new Label();
        this.defineVarLexer(context, mw);
        Label next_ = new Label();
        mw.visitVarInsn(25, context.var("lexer"));
        mw.visitMethodInsn(182, JSONLexerBase, "token", "()I");
        mw.visitLdcInsn(14);
        mw.visitJumpInsn(160, next_);
        if ((beanInfo.parserFeatures & Feature.SupportArrayToBean.mask) == 0) {
            mw.visitVarInsn(25, context.var("lexer"));
            mw.visitVarInsn(21, 4);
            mw.visitLdcInsn(Feature.SupportArrayToBean.mask);
            mw.visitMethodInsn(182, JSONLexerBase, "isEnabled", "(II)Z");
            mw.visitJumpInsn(153, next_);
        }
        mw.visitVarInsn(25, 0);
        mw.visitVarInsn(25, 1);
        mw.visitVarInsn(25, 2);
        mw.visitVarInsn(25, 3);
        mw.visitInsn(1);
        mw.visitMethodInsn(183, context.className, "deserialzeArrayMapping", "(L" + DefaultJSONParser + ";Ljava/lang/reflect/Type;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;");
        mw.visitInsn(176);
        mw.visitLabel(next_);
        mw.visitVarInsn(25, context.var("lexer"));
        mw.visitLdcInsn(Feature.SortFeidFastMatch.mask);
        mw.visitMethodInsn(182, JSONLexerBase, "isEnabled", "(I)Z");
        Label continue_ = new Label();
        mw.visitJumpInsn(154, continue_);
        mw.visitJumpInsn(200, super_);
        mw.visitLabel(continue_);
        mw.visitVarInsn(25, context.var("lexer"));
        mw.visitLdcInsn(context.clazz.getName());
        mw.visitMethodInsn(182, JSONLexerBase, "scanType", "(Ljava/lang/String;)I");
        mw.visitLdcInsn(-1);
        Label continue_2 = new Label();
        mw.visitJumpInsn(160, continue_2);
        mw.visitJumpInsn(200, super_);
        mw.visitLabel(continue_2);
        mw.visitVarInsn(25, 1);
        mw.visitMethodInsn(182, DefaultJSONParser, "getContext", "()" + ASMUtils.desc(ParseContext.class));
        mw.visitVarInsn(58, context.var("mark_context"));
        mw.visitInsn(3);
        mw.visitVarInsn(54, context.var("matchedCount"));
        this._createInstance(context, mw);
        mw.visitVarInsn(25, 1);
        mw.visitMethodInsn(182, DefaultJSONParser, "getContext", "()" + ASMUtils.desc(ParseContext.class));
        mw.visitVarInsn(58, context.var("context"));
        mw.visitVarInsn(25, 1);
        mw.visitVarInsn(25, context.var("context"));
        mw.visitVarInsn(25, context.var("instance"));
        mw.visitVarInsn(25, 3);
        mw.visitMethodInsn(182, DefaultJSONParser, "setContext", "(" + ASMUtils.desc(ParseContext.class) + "Ljava/lang/Object;Ljava/lang/Object;)" + ASMUtils.desc(ParseContext.class));
        mw.visitVarInsn(58, context.var("childContext"));
        mw.visitVarInsn(25, context.var("lexer"));
        mw.visitFieldInsn(180, JSONLexerBase, "matchStat", "I");
        mw.visitLdcInsn(4);
        Label continue_3 = new Label();
        mw.visitJumpInsn(160, continue_3);
        mw.visitJumpInsn(200, return_);
        mw.visitLabel(continue_3);
        mw.visitInsn(3);
        mw.visitIntInsn(54, context.var("matchStat"));
        int fieldListSize = context.fieldInfoList.length;
        for (i = 0; i < fieldListSize; i += 32) {
            mw.visitInsn(3);
            mw.visitVarInsn(54, context.var("_asm_flag_" + i / 32));
        }
        mw.visitVarInsn(25, context.var("lexer"));
        mw.visitLdcInsn(Feature.InitStringFieldAsEmpty.mask);
        mw.visitMethodInsn(182, JSONLexerBase, "isEnabled", "(I)Z");
        mw.visitIntInsn(54, context.var("initStringFieldAsEmpty"));
        for (i = 0; i < fieldListSize; ++i) {
            fieldInfo = context.fieldInfoList[i];
            fieldClass = fieldInfo.fieldClass;
            if (fieldClass == Boolean.TYPE || fieldClass == Byte.TYPE || fieldClass == Short.TYPE || fieldClass == Integer.TYPE) {
                mw.visitInsn(3);
                mw.visitVarInsn(54, context.var_asm(fieldInfo));
                continue;
            }
            if (fieldClass == Long.TYPE) {
                mw.visitInsn(9);
                mw.visitVarInsn(55, context.var_asm(fieldInfo, 2));
                continue;
            }
            if (fieldClass == Float.TYPE) {
                mw.visitInsn(11);
                mw.visitVarInsn(56, context.var_asm(fieldInfo));
                continue;
            }
            if (fieldClass == Double.TYPE) {
                mw.visitInsn(14);
                mw.visitVarInsn(57, context.var_asm(fieldInfo, 2));
                continue;
            }
            if (fieldClass == String.class) {
                Label flagEnd_ = new Label();
                Label flagElse_ = new Label();
                mw.visitVarInsn(21, context.var("initStringFieldAsEmpty"));
                mw.visitJumpInsn(153, flagElse_);
                this._setFlag(mw, context, i);
                mw.visitVarInsn(25, context.var("lexer"));
                mw.visitMethodInsn(182, JSONLexerBase, "stringDefaultValue", "()Ljava/lang/String;");
                mw.visitJumpInsn(167, flagEnd_);
                mw.visitLabel(flagElse_);
                mw.visitInsn(1);
                mw.visitLabel(flagEnd_);
            } else {
                mw.visitInsn(1);
            }
            mw.visitTypeInsn(192, ASMUtils.type(fieldClass));
            mw.visitVarInsn(58, context.var_asm(fieldInfo));
        }
        for (i = 0; i < fieldListSize; ++i) {
            Label notMatch_;
            block72: {
                Label valueNullEnd_;
                fieldInfo = context.fieldInfoList[i];
                fieldClass = fieldInfo.fieldClass;
                Type fieldType = fieldInfo.fieldType;
                notMatch_ = new Label();
                if (fieldClass == Boolean.TYPE) {
                    mw.visitVarInsn(25, context.var("lexer"));
                    mw.visitVarInsn(25, 0);
                    mw.visitFieldInsn(180, context.className, context.fieldName(fieldInfo), "[C");
                    mw.visitMethodInsn(182, JSONLexerBase, "scanFieldBoolean", "([C)Z");
                    mw.visitVarInsn(54, context.var_asm(fieldInfo));
                } else if (fieldClass == Byte.TYPE) {
                    mw.visitVarInsn(25, context.var("lexer"));
                    mw.visitVarInsn(25, 0);
                    mw.visitFieldInsn(180, context.className, context.fieldName(fieldInfo), "[C");
                    mw.visitMethodInsn(182, JSONLexerBase, "scanFieldInt", "([C)I");
                    mw.visitVarInsn(54, context.var_asm(fieldInfo));
                } else if (fieldClass == Byte.class) {
                    mw.visitVarInsn(25, context.var("lexer"));
                    mw.visitVarInsn(25, 0);
                    mw.visitFieldInsn(180, context.className, context.fieldName(fieldInfo), "[C");
                    mw.visitMethodInsn(182, JSONLexerBase, "scanFieldInt", "([C)I");
                    mw.visitMethodInsn(184, "java/lang/Byte", "valueOf", "(B)Ljava/lang/Byte;");
                    mw.visitVarInsn(58, context.var_asm(fieldInfo));
                    valueNullEnd_ = new Label();
                    mw.visitVarInsn(25, context.var("lexer"));
                    mw.visitFieldInsn(180, JSONLexerBase, "matchStat", "I");
                    mw.visitLdcInsn(5);
                    mw.visitJumpInsn(160, valueNullEnd_);
                    mw.visitInsn(1);
                    mw.visitVarInsn(58, context.var_asm(fieldInfo));
                    mw.visitLabel(valueNullEnd_);
                } else if (fieldClass == Short.TYPE) {
                    mw.visitVarInsn(25, context.var("lexer"));
                    mw.visitVarInsn(25, 0);
                    mw.visitFieldInsn(180, context.className, context.fieldName(fieldInfo), "[C");
                    mw.visitMethodInsn(182, JSONLexerBase, "scanFieldInt", "([C)I");
                    mw.visitVarInsn(54, context.var_asm(fieldInfo));
                } else if (fieldClass == Short.class) {
                    mw.visitVarInsn(25, context.var("lexer"));
                    mw.visitVarInsn(25, 0);
                    mw.visitFieldInsn(180, context.className, context.fieldName(fieldInfo), "[C");
                    mw.visitMethodInsn(182, JSONLexerBase, "scanFieldInt", "([C)I");
                    mw.visitMethodInsn(184, "java/lang/Short", "valueOf", "(S)Ljava/lang/Short;");
                    mw.visitVarInsn(58, context.var_asm(fieldInfo));
                    valueNullEnd_ = new Label();
                    mw.visitVarInsn(25, context.var("lexer"));
                    mw.visitFieldInsn(180, JSONLexerBase, "matchStat", "I");
                    mw.visitLdcInsn(5);
                    mw.visitJumpInsn(160, valueNullEnd_);
                    mw.visitInsn(1);
                    mw.visitVarInsn(58, context.var_asm(fieldInfo));
                    mw.visitLabel(valueNullEnd_);
                } else if (fieldClass == Integer.TYPE) {
                    mw.visitVarInsn(25, context.var("lexer"));
                    mw.visitVarInsn(25, 0);
                    mw.visitFieldInsn(180, context.className, context.fieldName(fieldInfo), "[C");
                    mw.visitMethodInsn(182, JSONLexerBase, "scanFieldInt", "([C)I");
                    mw.visitVarInsn(54, context.var_asm(fieldInfo));
                } else if (fieldClass == Integer.class) {
                    mw.visitVarInsn(25, context.var("lexer"));
                    mw.visitVarInsn(25, 0);
                    mw.visitFieldInsn(180, context.className, context.fieldName(fieldInfo), "[C");
                    mw.visitMethodInsn(182, JSONLexerBase, "scanFieldInt", "([C)I");
                    mw.visitMethodInsn(184, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
                    mw.visitVarInsn(58, context.var_asm(fieldInfo));
                    valueNullEnd_ = new Label();
                    mw.visitVarInsn(25, context.var("lexer"));
                    mw.visitFieldInsn(180, JSONLexerBase, "matchStat", "I");
                    mw.visitLdcInsn(5);
                    mw.visitJumpInsn(160, valueNullEnd_);
                    mw.visitInsn(1);
                    mw.visitVarInsn(58, context.var_asm(fieldInfo));
                    mw.visitLabel(valueNullEnd_);
                } else if (fieldClass == Long.TYPE) {
                    mw.visitVarInsn(25, context.var("lexer"));
                    mw.visitVarInsn(25, 0);
                    mw.visitFieldInsn(180, context.className, context.fieldName(fieldInfo), "[C");
                    mw.visitMethodInsn(182, JSONLexerBase, "scanFieldLong", "([C)J");
                    mw.visitVarInsn(55, context.var_asm(fieldInfo, 2));
                } else if (fieldClass == Long.class) {
                    mw.visitVarInsn(25, context.var("lexer"));
                    mw.visitVarInsn(25, 0);
                    mw.visitFieldInsn(180, context.className, context.fieldName(fieldInfo), "[C");
                    mw.visitMethodInsn(182, JSONLexerBase, "scanFieldLong", "([C)J");
                    mw.visitMethodInsn(184, "java/lang/Long", "valueOf", "(J)Ljava/lang/Long;");
                    mw.visitVarInsn(58, context.var_asm(fieldInfo));
                    valueNullEnd_ = new Label();
                    mw.visitVarInsn(25, context.var("lexer"));
                    mw.visitFieldInsn(180, JSONLexerBase, "matchStat", "I");
                    mw.visitLdcInsn(5);
                    mw.visitJumpInsn(160, valueNullEnd_);
                    mw.visitInsn(1);
                    mw.visitVarInsn(58, context.var_asm(fieldInfo));
                    mw.visitLabel(valueNullEnd_);
                } else if (fieldClass == Float.TYPE) {
                    mw.visitVarInsn(25, context.var("lexer"));
                    mw.visitVarInsn(25, 0);
                    mw.visitFieldInsn(180, context.className, context.fieldName(fieldInfo), "[C");
                    mw.visitMethodInsn(182, JSONLexerBase, "scanFieldFloat", "([C)F");
                    mw.visitVarInsn(56, context.var_asm(fieldInfo));
                } else if (fieldClass == Float.class) {
                    mw.visitVarInsn(25, context.var("lexer"));
                    mw.visitVarInsn(25, 0);
                    mw.visitFieldInsn(180, context.className, context.fieldName(fieldInfo), "[C");
                    mw.visitMethodInsn(182, JSONLexerBase, "scanFieldFloat", "([C)F");
                    mw.visitMethodInsn(184, "java/lang/Float", "valueOf", "(F)Ljava/lang/Float;");
                    mw.visitVarInsn(58, context.var_asm(fieldInfo));
                    valueNullEnd_ = new Label();
                    mw.visitVarInsn(25, context.var("lexer"));
                    mw.visitFieldInsn(180, JSONLexerBase, "matchStat", "I");
                    mw.visitLdcInsn(5);
                    mw.visitJumpInsn(160, valueNullEnd_);
                    mw.visitInsn(1);
                    mw.visitVarInsn(58, context.var_asm(fieldInfo));
                    mw.visitLabel(valueNullEnd_);
                } else if (fieldClass == Double.TYPE) {
                    mw.visitVarInsn(25, context.var("lexer"));
                    mw.visitVarInsn(25, 0);
                    mw.visitFieldInsn(180, context.className, context.fieldName(fieldInfo), "[C");
                    mw.visitMethodInsn(182, JSONLexerBase, "scanFieldDouble", "([C)D");
                    mw.visitVarInsn(57, context.var_asm(fieldInfo, 2));
                } else if (fieldClass == Double.class) {
                    mw.visitVarInsn(25, context.var("lexer"));
                    mw.visitVarInsn(25, 0);
                    mw.visitFieldInsn(180, context.className, context.fieldName(fieldInfo), "[C");
                    mw.visitMethodInsn(182, JSONLexerBase, "scanFieldDouble", "([C)D");
                    mw.visitMethodInsn(184, "java/lang/Double", "valueOf", "(D)Ljava/lang/Double;");
                    mw.visitVarInsn(58, context.var_asm(fieldInfo));
                    valueNullEnd_ = new Label();
                    mw.visitVarInsn(25, context.var("lexer"));
                    mw.visitFieldInsn(180, JSONLexerBase, "matchStat", "I");
                    mw.visitLdcInsn(5);
                    mw.visitJumpInsn(160, valueNullEnd_);
                    mw.visitInsn(1);
                    mw.visitVarInsn(58, context.var_asm(fieldInfo));
                    mw.visitLabel(valueNullEnd_);
                } else if (fieldClass == String.class) {
                    mw.visitVarInsn(25, context.var("lexer"));
                    mw.visitVarInsn(25, 0);
                    mw.visitFieldInsn(180, context.className, context.fieldName(fieldInfo), "[C");
                    mw.visitMethodInsn(182, JSONLexerBase, "scanFieldString", "([C)Ljava/lang/String;");
                    mw.visitVarInsn(58, context.var_asm(fieldInfo));
                } else if (fieldClass == Date.class) {
                    mw.visitVarInsn(25, context.var("lexer"));
                    mw.visitVarInsn(25, 0);
                    mw.visitFieldInsn(180, context.className, context.fieldName(fieldInfo), "[C");
                    mw.visitMethodInsn(182, JSONLexerBase, "scanFieldDate", "([C)Ljava/util/Date;");
                    mw.visitVarInsn(58, context.var_asm(fieldInfo));
                } else if (fieldClass == UUID.class) {
                    mw.visitVarInsn(25, context.var("lexer"));
                    mw.visitVarInsn(25, 0);
                    mw.visitFieldInsn(180, context.className, context.fieldName(fieldInfo), "[C");
                    mw.visitMethodInsn(182, JSONLexerBase, "scanFieldUUID", "([C)Ljava/util/UUID;");
                    mw.visitVarInsn(58, context.var_asm(fieldInfo));
                } else if (fieldClass == BigDecimal.class) {
                    mw.visitVarInsn(25, context.var("lexer"));
                    mw.visitVarInsn(25, 0);
                    mw.visitFieldInsn(180, context.className, context.fieldName(fieldInfo), "[C");
                    mw.visitMethodInsn(182, JSONLexerBase, "scanFieldDecimal", "([C)Ljava/math/BigDecimal;");
                    mw.visitVarInsn(58, context.var_asm(fieldInfo));
                } else if (fieldClass == BigInteger.class) {
                    mw.visitVarInsn(25, context.var("lexer"));
                    mw.visitVarInsn(25, 0);
                    mw.visitFieldInsn(180, context.className, context.fieldName(fieldInfo), "[C");
                    mw.visitMethodInsn(182, JSONLexerBase, "scanFieldBigInteger", "([C)Ljava/math/BigInteger;");
                    mw.visitVarInsn(58, context.var_asm(fieldInfo));
                } else if (fieldClass == int[].class) {
                    mw.visitVarInsn(25, context.var("lexer"));
                    mw.visitVarInsn(25, 0);
                    mw.visitFieldInsn(180, context.className, context.fieldName(fieldInfo), "[C");
                    mw.visitMethodInsn(182, JSONLexerBase, "scanFieldIntArray", "([C)[I");
                    mw.visitVarInsn(58, context.var_asm(fieldInfo));
                } else if (fieldClass == float[].class) {
                    mw.visitVarInsn(25, context.var("lexer"));
                    mw.visitVarInsn(25, 0);
                    mw.visitFieldInsn(180, context.className, context.fieldName(fieldInfo), "[C");
                    mw.visitMethodInsn(182, JSONLexerBase, "scanFieldFloatArray", "([C)[F");
                    mw.visitVarInsn(58, context.var_asm(fieldInfo));
                } else if (fieldClass == float[][].class) {
                    mw.visitVarInsn(25, context.var("lexer"));
                    mw.visitVarInsn(25, 0);
                    mw.visitFieldInsn(180, context.className, context.fieldName(fieldInfo), "[C");
                    mw.visitMethodInsn(182, JSONLexerBase, "scanFieldFloatArray2", "([C)[[F");
                    mw.visitVarInsn(58, context.var_asm(fieldInfo));
                } else if (fieldClass.isEnum()) {
                    mw.visitVarInsn(25, 0);
                    mw.visitVarInsn(25, context.var("lexer"));
                    mw.visitVarInsn(25, 0);
                    mw.visitFieldInsn(180, context.className, context.fieldName(fieldInfo), "[C");
                    this._getFieldDeser(context, mw, fieldInfo);
                    mw.visitMethodInsn(182, ASMUtils.type(JavaBeanDeserializer.class), "scanEnum", "(L" + JSONLexerBase + ";[C" + ASMUtils.desc(ObjectDeserializer.class) + ")Ljava/lang/Enum;");
                    mw.visitTypeInsn(192, ASMUtils.type(fieldClass));
                    mw.visitVarInsn(58, context.var_asm(fieldInfo));
                } else {
                    if (Collection.class.isAssignableFrom(fieldClass)) {
                        mw.visitVarInsn(25, context.var("lexer"));
                        mw.visitVarInsn(25, 0);
                        mw.visitFieldInsn(180, context.className, context.fieldName(fieldInfo), "[C");
                        Class<?> itemClass = TypeUtils.getCollectionItemClass(fieldType);
                        if (itemClass == String.class) {
                            mw.visitLdcInsn(com.alibaba.fastjson.asm.Type.getType(ASMUtils.desc(fieldClass)));
                            mw.visitMethodInsn(182, JSONLexerBase, "scanFieldStringArray", "([CLjava/lang/Class;)" + ASMUtils.desc(Collection.class));
                            mw.visitVarInsn(58, context.var_asm(fieldInfo));
                            break block72;
                        } else {
                            this._deserialze_list_obj(context, mw, reset_, fieldInfo, fieldClass, itemClass, i);
                            if (i != fieldListSize - 1) continue;
                            this._deserialize_endCheck(context, mw, reset_);
                            continue;
                        }
                    }
                    this._deserialze_obj(context, mw, reset_, fieldInfo, fieldClass, i);
                    if (i != fieldListSize - 1) continue;
                    this._deserialize_endCheck(context, mw, reset_);
                    continue;
                }
            }
            mw.visitVarInsn(25, context.var("lexer"));
            mw.visitFieldInsn(180, JSONLexerBase, "matchStat", "I");
            Label flag_ = new Label();
            mw.visitJumpInsn(158, flag_);
            this._setFlag(mw, context, i);
            mw.visitLabel(flag_);
            mw.visitVarInsn(25, context.var("lexer"));
            mw.visitFieldInsn(180, JSONLexerBase, "matchStat", "I");
            mw.visitInsn(89);
            mw.visitVarInsn(54, context.var("matchStat"));
            mw.visitLdcInsn(-1);
            mw.visitJumpInsn(159, reset_);
            mw.visitVarInsn(25, context.var("lexer"));
            mw.visitFieldInsn(180, JSONLexerBase, "matchStat", "I");
            mw.visitJumpInsn(158, notMatch_);
            mw.visitVarInsn(21, context.var("matchedCount"));
            mw.visitInsn(4);
            mw.visitInsn(96);
            mw.visitVarInsn(54, context.var("matchedCount"));
            mw.visitVarInsn(25, context.var("lexer"));
            mw.visitFieldInsn(180, JSONLexerBase, "matchStat", "I");
            mw.visitLdcInsn(4);
            mw.visitJumpInsn(159, end_);
            mw.visitLabel(notMatch_);
            if (i != fieldListSize - 1) continue;
            mw.visitVarInsn(25, context.var("lexer"));
            mw.visitFieldInsn(180, JSONLexerBase, "matchStat", "I");
            mw.visitLdcInsn(4);
            mw.visitJumpInsn(160, reset_);
        }
        mw.visitLabel(end_);
        if (!context.clazz.isInterface() && !Modifier.isAbstract(context.clazz.getModifiers())) {
            this._batchSet(context, mw);
        }
        mw.visitLabel(return_);
        this._setContext(context, mw);
        mw.visitVarInsn(25, context.var("instance"));
        Method buildMethod = ((Context)context).beanInfo.buildMethod;
        if (buildMethod != null) {
            mw.visitMethodInsn(182, ASMUtils.type(context.getInstClass()), buildMethod.getName(), "()" + ASMUtils.desc(buildMethod.getReturnType()));
        }
        mw.visitInsn(176);
        mw.visitLabel(reset_);
        this._batchSet(context, mw);
        mw.visitVarInsn(25, 0);
        mw.visitVarInsn(25, 1);
        mw.visitVarInsn(25, 2);
        mw.visitVarInsn(25, 3);
        mw.visitVarInsn(25, context.var("instance"));
        mw.visitVarInsn(21, 4);
        int flagSize = fieldListSize / 32;
        if (fieldListSize != 0 && fieldListSize % 32 != 0) {
            ++flagSize;
        }
        if (flagSize == 1) {
            mw.visitInsn(4);
        } else {
            mw.visitIntInsn(16, flagSize);
        }
        mw.visitIntInsn(188, 10);
        int i2 = 0;
        while (true) {
            if (i2 >= flagSize) {
                mw.visitMethodInsn(182, ASMUtils.type(JavaBeanDeserializer.class), "parseRest", "(L" + DefaultJSONParser + ";Ljava/lang/reflect/Type;Ljava/lang/Object;Ljava/lang/Object;I[I)Ljava/lang/Object;");
                mw.visitTypeInsn(192, ASMUtils.type(context.clazz));
                mw.visitInsn(176);
                mw.visitLabel(super_);
                mw.visitVarInsn(25, 0);
                mw.visitVarInsn(25, 1);
                mw.visitVarInsn(25, 2);
                mw.visitVarInsn(25, 3);
                mw.visitVarInsn(21, 4);
                mw.visitMethodInsn(183, ASMUtils.type(JavaBeanDeserializer.class), "deserialze", "(L" + DefaultJSONParser + ";Ljava/lang/reflect/Type;Ljava/lang/Object;I)Ljava/lang/Object;");
                mw.visitInsn(176);
                mw.visitMaxs(10, context.variantIndex);
                mw.visitEnd();
                return;
            }
            mw.visitInsn(89);
            if (i2 == 0) {
                mw.visitInsn(3);
            } else if (i2 == 1) {
                mw.visitInsn(4);
            } else {
                mw.visitIntInsn(16, i2);
            }
            mw.visitVarInsn(21, context.var("_asm_flag_" + i2));
            mw.visitInsn(79);
            ++i2;
        }
    }

    private void defineVarLexer(Context context, MethodVisitor mw) {
        mw.visitVarInsn(25, 1);
        mw.visitFieldInsn(180, DefaultJSONParser, "lexer", ASMUtils.desc(JSONLexer.class));
        mw.visitTypeInsn(192, JSONLexerBase);
        mw.visitVarInsn(58, context.var("lexer"));
    }

    private void _createInstance(Context context, MethodVisitor mw) {
        JavaBeanInfo beanInfo = context.beanInfo;
        Constructor<?> defaultConstructor = beanInfo.defaultConstructor;
        if (Modifier.isPublic(defaultConstructor.getModifiers())) {
            mw.visitTypeInsn(187, ASMUtils.type(context.getInstClass()));
            mw.visitInsn(89);
            mw.visitMethodInsn(183, ASMUtils.type(defaultConstructor.getDeclaringClass()), "<init>", "()V");
        } else {
            mw.visitVarInsn(25, 0);
            mw.visitVarInsn(25, 1);
            mw.visitVarInsn(25, 0);
            mw.visitFieldInsn(180, ASMUtils.type(JavaBeanDeserializer.class), "clazz", "Ljava/lang/Class;");
            mw.visitMethodInsn(183, ASMUtils.type(JavaBeanDeserializer.class), "createInstance", "(L" + DefaultJSONParser + ";Ljava/lang/reflect/Type;)Ljava/lang/Object;");
            mw.visitTypeInsn(192, ASMUtils.type(context.getInstClass()));
        }
        mw.visitVarInsn(58, context.var("instance"));
    }

    private void _batchSet(Context context, MethodVisitor mw) {
        this._batchSet(context, mw, true);
    }

    private void _batchSet(Context context, MethodVisitor mw, boolean flag) {
        int size = context.fieldInfoList.length;
        for (int i = 0; i < size; ++i) {
            Label notSet_ = new Label();
            if (flag) {
                this._isFlag(mw, context, i, notSet_);
            }
            FieldInfo fieldInfo = context.fieldInfoList[i];
            this._loadAndSet(context, mw, fieldInfo);
            if (!flag) continue;
            mw.visitLabel(notSet_);
        }
    }

    private void _loadAndSet(Context context, MethodVisitor mw, FieldInfo fieldInfo) {
        Class<?> fieldClass = fieldInfo.fieldClass;
        Type fieldType = fieldInfo.fieldType;
        if (fieldClass == Boolean.TYPE) {
            mw.visitVarInsn(25, context.var("instance"));
            mw.visitVarInsn(21, context.var_asm(fieldInfo));
            this._set(context, mw, fieldInfo);
        } else if (fieldClass == Byte.TYPE || fieldClass == Short.TYPE || fieldClass == Integer.TYPE || fieldClass == Character.TYPE) {
            mw.visitVarInsn(25, context.var("instance"));
            mw.visitVarInsn(21, context.var_asm(fieldInfo));
            this._set(context, mw, fieldInfo);
        } else if (fieldClass == Long.TYPE) {
            mw.visitVarInsn(25, context.var("instance"));
            mw.visitVarInsn(22, context.var_asm(fieldInfo, 2));
            if (fieldInfo.method != null) {
                mw.visitMethodInsn(182, ASMUtils.type(context.getInstClass()), fieldInfo.method.getName(), ASMUtils.desc(fieldInfo.method));
                if (!fieldInfo.method.getReturnType().equals(Void.TYPE)) {
                    mw.visitInsn(87);
                }
            } else {
                mw.visitFieldInsn(181, ASMUtils.type(fieldInfo.declaringClass), fieldInfo.field.getName(), ASMUtils.desc(fieldInfo.fieldClass));
            }
        } else if (fieldClass == Float.TYPE) {
            mw.visitVarInsn(25, context.var("instance"));
            mw.visitVarInsn(23, context.var_asm(fieldInfo));
            this._set(context, mw, fieldInfo);
        } else if (fieldClass == Double.TYPE) {
            mw.visitVarInsn(25, context.var("instance"));
            mw.visitVarInsn(24, context.var_asm(fieldInfo, 2));
            this._set(context, mw, fieldInfo);
        } else if (fieldClass == String.class) {
            mw.visitVarInsn(25, context.var("instance"));
            mw.visitVarInsn(25, context.var_asm(fieldInfo));
            this._set(context, mw, fieldInfo);
        } else if (fieldClass.isEnum()) {
            mw.visitVarInsn(25, context.var("instance"));
            mw.visitVarInsn(25, context.var_asm(fieldInfo));
            this._set(context, mw, fieldInfo);
        } else if (Collection.class.isAssignableFrom(fieldClass)) {
            mw.visitVarInsn(25, context.var("instance"));
            Class<?> itemType = TypeUtils.getCollectionItemClass(fieldType);
            if (itemType == String.class) {
                mw.visitVarInsn(25, context.var_asm(fieldInfo));
                mw.visitTypeInsn(192, ASMUtils.type(fieldClass));
            } else {
                mw.visitVarInsn(25, context.var_asm(fieldInfo));
            }
            this._set(context, mw, fieldInfo);
        } else {
            mw.visitVarInsn(25, context.var("instance"));
            mw.visitVarInsn(25, context.var_asm(fieldInfo));
            this._set(context, mw, fieldInfo);
        }
    }

    private void _set(Context context, MethodVisitor mw, FieldInfo fieldInfo) {
        Method method = fieldInfo.method;
        if (method != null) {
            Class<?> declaringClass = method.getDeclaringClass();
            mw.visitMethodInsn(declaringClass.isInterface() ? 185 : 182, ASMUtils.type(fieldInfo.declaringClass), method.getName(), ASMUtils.desc(method));
            if (!fieldInfo.method.getReturnType().equals(Void.TYPE)) {
                mw.visitInsn(87);
            }
        } else {
            mw.visitFieldInsn(181, ASMUtils.type(fieldInfo.declaringClass), fieldInfo.field.getName(), ASMUtils.desc(fieldInfo.fieldClass));
        }
    }

    private void _setContext(Context context, MethodVisitor mw) {
        mw.visitVarInsn(25, 1);
        mw.visitVarInsn(25, context.var("context"));
        mw.visitMethodInsn(182, DefaultJSONParser, "setContext", "(" + ASMUtils.desc(ParseContext.class) + ")V");
        Label endIf_ = new Label();
        mw.visitVarInsn(25, context.var("childContext"));
        mw.visitJumpInsn(198, endIf_);
        mw.visitVarInsn(25, context.var("childContext"));
        mw.visitVarInsn(25, context.var("instance"));
        mw.visitFieldInsn(181, ASMUtils.type(ParseContext.class), "object", "Ljava/lang/Object;");
        mw.visitLabel(endIf_);
    }

    private void _deserialize_endCheck(Context context, MethodVisitor mw, Label reset_) {
        mw.visitIntInsn(21, context.var("matchedCount"));
        mw.visitJumpInsn(158, reset_);
        mw.visitVarInsn(25, context.var("lexer"));
        mw.visitMethodInsn(182, JSONLexerBase, "token", "()I");
        mw.visitLdcInsn(13);
        mw.visitJumpInsn(160, reset_);
        this._quickNextTokenComma(context, mw);
    }

    private void _deserialze_list_obj(Context context, MethodVisitor mw, Label reset_, FieldInfo fieldInfo, Class<?> fieldClass, Class<?> itemType, int i) {
        Label _end_if = new Label();
        mw.visitMethodInsn(182, JSONLexerBase, "matchField", "([C)Z");
        mw.visitJumpInsn(153, _end_if);
        this._setFlag(mw, context, i);
        Label valueNotNull_ = new Label();
        mw.visitVarInsn(25, context.var("lexer"));
        mw.visitMethodInsn(182, JSONLexerBase, "token", "()I");
        mw.visitLdcInsn(8);
        mw.visitJumpInsn(160, valueNotNull_);
        mw.visitVarInsn(25, context.var("lexer"));
        mw.visitLdcInsn(16);
        mw.visitMethodInsn(182, JSONLexerBase, "nextToken", "(I)V");
        mw.visitJumpInsn(167, _end_if);
        mw.visitLabel(valueNotNull_);
        Label storeCollection_ = new Label();
        Label endSet_ = new Label();
        Label lbacketNormal_ = new Label();
        mw.visitVarInsn(25, context.var("lexer"));
        mw.visitMethodInsn(182, JSONLexerBase, "token", "()I");
        mw.visitLdcInsn(21);
        mw.visitJumpInsn(160, endSet_);
        mw.visitVarInsn(25, context.var("lexer"));
        mw.visitLdcInsn(14);
        mw.visitMethodInsn(182, JSONLexerBase, "nextToken", "(I)V");
        this._newCollection(mw, fieldClass, i, true);
        mw.visitJumpInsn(167, storeCollection_);
        mw.visitLabel(endSet_);
        mw.visitVarInsn(25, context.var("lexer"));
        mw.visitMethodInsn(182, JSONLexerBase, "token", "()I");
        mw.visitLdcInsn(14);
        mw.visitJumpInsn(159, lbacketNormal_);
        mw.visitVarInsn(25, context.var("lexer"));
        mw.visitMethodInsn(182, JSONLexerBase, "token", "()I");
        mw.visitLdcInsn(12);
        mw.visitJumpInsn(160, reset_);
        this._newCollection(mw, fieldClass, i, false);
        mw.visitVarInsn(58, context.var_asm(fieldInfo));
        this._getCollectionFieldItemDeser(context, mw, fieldInfo, itemType);
        mw.visitVarInsn(25, 1);
        mw.visitLdcInsn(com.alibaba.fastjson.asm.Type.getType(ASMUtils.desc(itemType)));
        mw.visitInsn(3);
        mw.visitMethodInsn(184, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
        mw.visitMethodInsn(185, ASMUtils.type(ObjectDeserializer.class), "deserialze", "(L" + DefaultJSONParser + ";Ljava/lang/reflect/Type;Ljava/lang/Object;)Ljava/lang/Object;");
        mw.visitVarInsn(58, context.var("list_item_value"));
        mw.visitVarInsn(25, context.var_asm(fieldInfo));
        mw.visitVarInsn(25, context.var("list_item_value"));
        if (fieldClass.isInterface()) {
            mw.visitMethodInsn(185, ASMUtils.type(fieldClass), "add", "(Ljava/lang/Object;)Z");
        } else {
            mw.visitMethodInsn(182, ASMUtils.type(fieldClass), "add", "(Ljava/lang/Object;)Z");
        }
        mw.visitInsn(87);
        mw.visitJumpInsn(167, _end_if);
        mw.visitLabel(lbacketNormal_);
        this._newCollection(mw, fieldClass, i, false);
        mw.visitLabel(storeCollection_);
        mw.visitVarInsn(58, context.var_asm(fieldInfo));
        boolean isPrimitive = ParserConfig.isPrimitive2(fieldInfo.fieldClass);
        this._getCollectionFieldItemDeser(context, mw, fieldInfo, itemType);
        if (isPrimitive) {
            mw.visitMethodInsn(185, ASMUtils.type(ObjectDeserializer.class), "getFastMatchToken", "()I");
            mw.visitVarInsn(54, context.var("fastMatchToken"));
            mw.visitVarInsn(25, context.var("lexer"));
            mw.visitVarInsn(21, context.var("fastMatchToken"));
            mw.visitMethodInsn(182, JSONLexerBase, "nextToken", "(I)V");
        } else {
            mw.visitInsn(87);
            mw.visitLdcInsn(12);
            mw.visitVarInsn(54, context.var("fastMatchToken"));
            this._quickNextToken(context, mw, 12);
        }
        mw.visitVarInsn(25, 1);
        mw.visitMethodInsn(182, DefaultJSONParser, "getContext", "()" + ASMUtils.desc(ParseContext.class));
        mw.visitVarInsn(58, context.var("listContext"));
        mw.visitVarInsn(25, 1);
        mw.visitVarInsn(25, context.var_asm(fieldInfo));
        mw.visitLdcInsn(fieldInfo.name);
        mw.visitMethodInsn(182, DefaultJSONParser, "setContext", "(Ljava/lang/Object;Ljava/lang/Object;)" + ASMUtils.desc(ParseContext.class));
        mw.visitInsn(87);
        Label loop_ = new Label();
        Label loop_end_ = new Label();
        mw.visitInsn(3);
        mw.visitVarInsn(54, context.var("i"));
        mw.visitLabel(loop_);
        mw.visitVarInsn(25, context.var("lexer"));
        mw.visitMethodInsn(182, JSONLexerBase, "token", "()I");
        mw.visitLdcInsn(15);
        mw.visitJumpInsn(159, loop_end_);
        mw.visitVarInsn(25, 0);
        mw.visitFieldInsn(180, context.className, fieldInfo.name + "_asm_list_item_deser__", ASMUtils.desc(ObjectDeserializer.class));
        mw.visitVarInsn(25, 1);
        mw.visitLdcInsn(com.alibaba.fastjson.asm.Type.getType(ASMUtils.desc(itemType)));
        mw.visitVarInsn(21, context.var("i"));
        mw.visitMethodInsn(184, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
        mw.visitMethodInsn(185, ASMUtils.type(ObjectDeserializer.class), "deserialze", "(L" + DefaultJSONParser + ";Ljava/lang/reflect/Type;Ljava/lang/Object;)Ljava/lang/Object;");
        mw.visitVarInsn(58, context.var("list_item_value"));
        mw.visitIincInsn(context.var("i"), 1);
        mw.visitVarInsn(25, context.var_asm(fieldInfo));
        mw.visitVarInsn(25, context.var("list_item_value"));
        if (fieldClass.isInterface()) {
            mw.visitMethodInsn(185, ASMUtils.type(fieldClass), "add", "(Ljava/lang/Object;)Z");
        } else {
            mw.visitMethodInsn(182, ASMUtils.type(fieldClass), "add", "(Ljava/lang/Object;)Z");
        }
        mw.visitInsn(87);
        mw.visitVarInsn(25, 1);
        mw.visitVarInsn(25, context.var_asm(fieldInfo));
        mw.visitMethodInsn(182, DefaultJSONParser, "checkListResolve", "(Ljava/util/Collection;)V");
        mw.visitVarInsn(25, context.var("lexer"));
        mw.visitMethodInsn(182, JSONLexerBase, "token", "()I");
        mw.visitLdcInsn(16);
        mw.visitJumpInsn(160, loop_);
        if (isPrimitive) {
            mw.visitVarInsn(25, context.var("lexer"));
            mw.visitVarInsn(21, context.var("fastMatchToken"));
            mw.visitMethodInsn(182, JSONLexerBase, "nextToken", "(I)V");
        } else {
            this._quickNextToken(context, mw, 12);
        }
        mw.visitJumpInsn(167, loop_);
        mw.visitLabel(loop_end_);
        mw.visitVarInsn(25, 1);
        mw.visitVarInsn(25, context.var("listContext"));
        mw.visitMethodInsn(182, DefaultJSONParser, "setContext", "(" + ASMUtils.desc(ParseContext.class) + ")V");
        mw.visitVarInsn(25, context.var("lexer"));
        mw.visitMethodInsn(182, JSONLexerBase, "token", "()I");
        mw.visitLdcInsn(15);
        mw.visitJumpInsn(160, reset_);
        this._quickNextTokenComma(context, mw);
        mw.visitLabel(_end_if);
    }

    private void _quickNextToken(Context context, MethodVisitor mw, int token) {
        Label quickElse_ = new Label();
        Label quickEnd_ = new Label();
        mw.visitVarInsn(25, context.var("lexer"));
        mw.visitMethodInsn(182, JSONLexerBase, "getCurrent", "()C");
        if (token == 12) {
            mw.visitVarInsn(16, 123);
        } else if (token == 14) {
            mw.visitVarInsn(16, 91);
        } else {
            throw new IllegalStateException();
        }
        mw.visitJumpInsn(160, quickElse_);
        mw.visitVarInsn(25, context.var("lexer"));
        mw.visitMethodInsn(182, JSONLexerBase, "next", "()C");
        mw.visitInsn(87);
        mw.visitVarInsn(25, context.var("lexer"));
        mw.visitLdcInsn(token);
        mw.visitMethodInsn(182, JSONLexerBase, "setToken", "(I)V");
        mw.visitJumpInsn(167, quickEnd_);
        mw.visitLabel(quickElse_);
        mw.visitVarInsn(25, context.var("lexer"));
        mw.visitLdcInsn(token);
        mw.visitMethodInsn(182, JSONLexerBase, "nextToken", "(I)V");
        mw.visitLabel(quickEnd_);
    }

    private void _quickNextTokenComma(Context context, MethodVisitor mw) {
        Label quickElse_ = new Label();
        Label quickElseIf0_ = new Label();
        Label quickElseIf1_ = new Label();
        Label quickElseIf2_ = new Label();
        Label quickEnd_ = new Label();
        mw.visitVarInsn(25, context.var("lexer"));
        mw.visitMethodInsn(182, JSONLexerBase, "getCurrent", "()C");
        mw.visitInsn(89);
        mw.visitVarInsn(54, context.var("ch"));
        mw.visitVarInsn(16, 44);
        mw.visitJumpInsn(160, quickElseIf0_);
        mw.visitVarInsn(25, context.var("lexer"));
        mw.visitMethodInsn(182, JSONLexerBase, "next", "()C");
        mw.visitInsn(87);
        mw.visitVarInsn(25, context.var("lexer"));
        mw.visitLdcInsn(16);
        mw.visitMethodInsn(182, JSONLexerBase, "setToken", "(I)V");
        mw.visitJumpInsn(167, quickEnd_);
        mw.visitLabel(quickElseIf0_);
        mw.visitVarInsn(21, context.var("ch"));
        mw.visitVarInsn(16, 125);
        mw.visitJumpInsn(160, quickElseIf1_);
        mw.visitVarInsn(25, context.var("lexer"));
        mw.visitMethodInsn(182, JSONLexerBase, "next", "()C");
        mw.visitInsn(87);
        mw.visitVarInsn(25, context.var("lexer"));
        mw.visitLdcInsn(13);
        mw.visitMethodInsn(182, JSONLexerBase, "setToken", "(I)V");
        mw.visitJumpInsn(167, quickEnd_);
        mw.visitLabel(quickElseIf1_);
        mw.visitVarInsn(21, context.var("ch"));
        mw.visitVarInsn(16, 93);
        mw.visitJumpInsn(160, quickElseIf2_);
        mw.visitVarInsn(25, context.var("lexer"));
        mw.visitMethodInsn(182, JSONLexerBase, "next", "()C");
        mw.visitInsn(87);
        mw.visitVarInsn(25, context.var("lexer"));
        mw.visitLdcInsn(15);
        mw.visitMethodInsn(182, JSONLexerBase, "setToken", "(I)V");
        mw.visitJumpInsn(167, quickEnd_);
        mw.visitLabel(quickElseIf2_);
        mw.visitVarInsn(21, context.var("ch"));
        mw.visitVarInsn(16, 26);
        mw.visitJumpInsn(160, quickElse_);
        mw.visitVarInsn(25, context.var("lexer"));
        mw.visitLdcInsn(20);
        mw.visitMethodInsn(182, JSONLexerBase, "setToken", "(I)V");
        mw.visitJumpInsn(167, quickEnd_);
        mw.visitLabel(quickElse_);
        mw.visitVarInsn(25, context.var("lexer"));
        mw.visitMethodInsn(182, JSONLexerBase, "nextToken", "()V");
        mw.visitLabel(quickEnd_);
    }

    private void _getCollectionFieldItemDeser(Context context, MethodVisitor mw, FieldInfo fieldInfo, Class<?> itemType) {
        Label notNull_ = new Label();
        mw.visitVarInsn(25, 0);
        mw.visitFieldInsn(180, context.className, fieldInfo.name + "_asm_list_item_deser__", ASMUtils.desc(ObjectDeserializer.class));
        mw.visitJumpInsn(199, notNull_);
        mw.visitVarInsn(25, 0);
        mw.visitVarInsn(25, 1);
        mw.visitMethodInsn(182, DefaultJSONParser, "getConfig", "()" + ASMUtils.desc(ParserConfig.class));
        mw.visitLdcInsn(com.alibaba.fastjson.asm.Type.getType(ASMUtils.desc(itemType)));
        mw.visitMethodInsn(182, ASMUtils.type(ParserConfig.class), "getDeserializer", "(Ljava/lang/reflect/Type;)" + ASMUtils.desc(ObjectDeserializer.class));
        mw.visitFieldInsn(181, context.className, fieldInfo.name + "_asm_list_item_deser__", ASMUtils.desc(ObjectDeserializer.class));
        mw.visitLabel(notNull_);
        mw.visitVarInsn(25, 0);
        mw.visitFieldInsn(180, context.className, fieldInfo.name + "_asm_list_item_deser__", ASMUtils.desc(ObjectDeserializer.class));
    }

    private void _newCollection(MethodVisitor mw, Class<?> fieldClass, int i, boolean set) {
        if (fieldClass.isAssignableFrom(ArrayList.class) && !set) {
            mw.visitTypeInsn(187, "java/util/ArrayList");
            mw.visitInsn(89);
            mw.visitMethodInsn(183, "java/util/ArrayList", "<init>", "()V");
        } else if (fieldClass.isAssignableFrom(LinkedList.class) && !set) {
            mw.visitTypeInsn(187, ASMUtils.type(LinkedList.class));
            mw.visitInsn(89);
            mw.visitMethodInsn(183, ASMUtils.type(LinkedList.class), "<init>", "()V");
        } else if (fieldClass.isAssignableFrom(HashSet.class)) {
            mw.visitTypeInsn(187, ASMUtils.type(HashSet.class));
            mw.visitInsn(89);
            mw.visitMethodInsn(183, ASMUtils.type(HashSet.class), "<init>", "()V");
        } else if (fieldClass.isAssignableFrom(TreeSet.class)) {
            mw.visitTypeInsn(187, ASMUtils.type(TreeSet.class));
            mw.visitInsn(89);
            mw.visitMethodInsn(183, ASMUtils.type(TreeSet.class), "<init>", "()V");
        } else if (fieldClass.isAssignableFrom(LinkedHashSet.class)) {
            mw.visitTypeInsn(187, ASMUtils.type(LinkedHashSet.class));
            mw.visitInsn(89);
            mw.visitMethodInsn(183, ASMUtils.type(LinkedHashSet.class), "<init>", "()V");
        } else if (set) {
            mw.visitTypeInsn(187, ASMUtils.type(HashSet.class));
            mw.visitInsn(89);
            mw.visitMethodInsn(183, ASMUtils.type(HashSet.class), "<init>", "()V");
        } else {
            mw.visitVarInsn(25, 0);
            mw.visitLdcInsn(i);
            mw.visitMethodInsn(182, ASMUtils.type(JavaBeanDeserializer.class), "getFieldType", "(I)Ljava/lang/reflect/Type;");
            mw.visitMethodInsn(184, ASMUtils.type(TypeUtils.class), "createCollection", "(Ljava/lang/reflect/Type;)Ljava/util/Collection;");
        }
        mw.visitTypeInsn(192, ASMUtils.type(fieldClass));
    }

    private void _deserialze_obj(Context context, MethodVisitor mw, Label reset_, FieldInfo fieldInfo, Class<?> fieldClass, int i) {
        Label matched_ = new Label();
        Label _end_if = new Label();
        mw.visitVarInsn(25, context.var("lexer"));
        mw.visitVarInsn(25, 0);
        mw.visitFieldInsn(180, context.className, context.fieldName(fieldInfo), "[C");
        mw.visitMethodInsn(182, JSONLexerBase, "matchField", "([C)Z");
        mw.visitJumpInsn(154, matched_);
        mw.visitInsn(1);
        mw.visitVarInsn(58, context.var_asm(fieldInfo));
        mw.visitJumpInsn(167, _end_if);
        mw.visitLabel(matched_);
        this._setFlag(mw, context, i);
        mw.visitVarInsn(21, context.var("matchedCount"));
        mw.visitInsn(4);
        mw.visitInsn(96);
        mw.visitVarInsn(54, context.var("matchedCount"));
        this._deserObject(context, mw, fieldInfo, fieldClass, i);
        mw.visitVarInsn(25, 1);
        mw.visitMethodInsn(182, DefaultJSONParser, "getResolveStatus", "()I");
        mw.visitLdcInsn(1);
        mw.visitJumpInsn(160, _end_if);
        mw.visitVarInsn(25, 1);
        mw.visitMethodInsn(182, DefaultJSONParser, "getLastResolveTask", "()" + ASMUtils.desc(DefaultJSONParser.ResolveTask.class));
        mw.visitVarInsn(58, context.var("resolveTask"));
        mw.visitVarInsn(25, context.var("resolveTask"));
        mw.visitVarInsn(25, 1);
        mw.visitMethodInsn(182, DefaultJSONParser, "getContext", "()" + ASMUtils.desc(ParseContext.class));
        mw.visitFieldInsn(181, ASMUtils.type(DefaultJSONParser.ResolveTask.class), "ownerContext", ASMUtils.desc(ParseContext.class));
        mw.visitVarInsn(25, context.var("resolveTask"));
        mw.visitVarInsn(25, 0);
        mw.visitLdcInsn(fieldInfo.name);
        mw.visitMethodInsn(182, ASMUtils.type(JavaBeanDeserializer.class), "getFieldDeserializer", "(Ljava/lang/String;)" + ASMUtils.desc(FieldDeserializer.class));
        mw.visitFieldInsn(181, ASMUtils.type(DefaultJSONParser.ResolveTask.class), "fieldDeserializer", ASMUtils.desc(FieldDeserializer.class));
        mw.visitVarInsn(25, 1);
        mw.visitLdcInsn(0);
        mw.visitMethodInsn(182, DefaultJSONParser, "setResolveStatus", "(I)V");
        mw.visitLabel(_end_if);
    }

    private void _deserObject(Context context, MethodVisitor mw, FieldInfo fieldInfo, Class<?> fieldClass, int i) {
        this._getFieldDeser(context, mw, fieldInfo);
        Label instanceOfElse_ = new Label();
        Label instanceOfEnd_ = new Label();
        if ((fieldInfo.parserFeatures & Feature.SupportArrayToBean.mask) != 0) {
            mw.visitInsn(89);
            mw.visitTypeInsn(193, ASMUtils.type(JavaBeanDeserializer.class));
            mw.visitJumpInsn(153, instanceOfElse_);
            mw.visitTypeInsn(192, ASMUtils.type(JavaBeanDeserializer.class));
            mw.visitVarInsn(25, 1);
            if (fieldInfo.fieldType instanceof Class) {
                mw.visitLdcInsn(com.alibaba.fastjson.asm.Type.getType(ASMUtils.desc(fieldInfo.fieldClass)));
            } else {
                mw.visitVarInsn(25, 0);
                mw.visitLdcInsn(i);
                mw.visitMethodInsn(182, ASMUtils.type(JavaBeanDeserializer.class), "getFieldType", "(I)Ljava/lang/reflect/Type;");
            }
            mw.visitLdcInsn(fieldInfo.name);
            mw.visitLdcInsn(fieldInfo.parserFeatures);
            mw.visitMethodInsn(182, ASMUtils.type(JavaBeanDeserializer.class), "deserialze", "(L" + DefaultJSONParser + ";Ljava/lang/reflect/Type;Ljava/lang/Object;I)Ljava/lang/Object;");
            mw.visitTypeInsn(192, ASMUtils.type(fieldClass));
            mw.visitVarInsn(58, context.var_asm(fieldInfo));
            mw.visitJumpInsn(167, instanceOfEnd_);
            mw.visitLabel(instanceOfElse_);
        }
        mw.visitVarInsn(25, 1);
        if (fieldInfo.fieldType instanceof Class) {
            mw.visitLdcInsn(com.alibaba.fastjson.asm.Type.getType(ASMUtils.desc(fieldInfo.fieldClass)));
        } else {
            mw.visitVarInsn(25, 0);
            mw.visitLdcInsn(i);
            mw.visitMethodInsn(182, ASMUtils.type(JavaBeanDeserializer.class), "getFieldType", "(I)Ljava/lang/reflect/Type;");
        }
        mw.visitLdcInsn(fieldInfo.name);
        mw.visitMethodInsn(185, ASMUtils.type(ObjectDeserializer.class), "deserialze", "(L" + DefaultJSONParser + ";Ljava/lang/reflect/Type;Ljava/lang/Object;)Ljava/lang/Object;");
        mw.visitTypeInsn(192, ASMUtils.type(fieldClass));
        mw.visitVarInsn(58, context.var_asm(fieldInfo));
        mw.visitLabel(instanceOfEnd_);
    }

    private void _getFieldDeser(Context context, MethodVisitor mw, FieldInfo fieldInfo) {
        Label notNull_ = new Label();
        mw.visitVarInsn(25, 0);
        mw.visitFieldInsn(180, context.className, context.fieldDeserName(fieldInfo), ASMUtils.desc(ObjectDeserializer.class));
        mw.visitJumpInsn(199, notNull_);
        mw.visitVarInsn(25, 0);
        mw.visitVarInsn(25, 1);
        mw.visitMethodInsn(182, DefaultJSONParser, "getConfig", "()" + ASMUtils.desc(ParserConfig.class));
        mw.visitLdcInsn(com.alibaba.fastjson.asm.Type.getType(ASMUtils.desc(fieldInfo.fieldClass)));
        mw.visitMethodInsn(182, ASMUtils.type(ParserConfig.class), "getDeserializer", "(Ljava/lang/reflect/Type;)" + ASMUtils.desc(ObjectDeserializer.class));
        mw.visitFieldInsn(181, context.className, context.fieldDeserName(fieldInfo), ASMUtils.desc(ObjectDeserializer.class));
        mw.visitLabel(notNull_);
        mw.visitVarInsn(25, 0);
        mw.visitFieldInsn(180, context.className, context.fieldDeserName(fieldInfo), ASMUtils.desc(ObjectDeserializer.class));
    }

    private void _init(ClassWriter cw, Context context) {
        FieldInfo fieldInfo;
        int i;
        int size = context.fieldInfoList.length;
        for (i = 0; i < size; ++i) {
            fieldInfo = context.fieldInfoList[i];
            FieldWriter fw = new FieldWriter(cw, 1, context.fieldName(fieldInfo), "[C");
            fw.visitEnd();
        }
        size = context.fieldInfoList.length;
        for (i = 0; i < size; ++i) {
            FieldWriter fw;
            fieldInfo = context.fieldInfoList[i];
            Class<?> fieldClass = fieldInfo.fieldClass;
            if (fieldClass.isPrimitive()) continue;
            if (Collection.class.isAssignableFrom(fieldClass)) {
                fw = new FieldWriter(cw, 1, fieldInfo.name + "_asm_list_item_deser__", ASMUtils.desc(ObjectDeserializer.class));
                fw.visitEnd();
                continue;
            }
            fw = new FieldWriter(cw, 1, context.fieldDeserName(fieldInfo), ASMUtils.desc(ObjectDeserializer.class));
            fw.visitEnd();
        }
        MethodWriter mw = new MethodWriter(cw, 1, "<init>", "(" + ASMUtils.desc(ParserConfig.class) + ASMUtils.desc(JavaBeanInfo.class) + ")V", null, null);
        mw.visitVarInsn(25, 0);
        mw.visitVarInsn(25, 1);
        mw.visitVarInsn(25, 2);
        mw.visitMethodInsn(183, ASMUtils.type(JavaBeanDeserializer.class), "<init>", "(" + ASMUtils.desc(ParserConfig.class) + ASMUtils.desc(JavaBeanInfo.class) + ")V");
        int size2 = context.fieldInfoList.length;
        for (int i2 = 0; i2 < size2; ++i2) {
            FieldInfo fieldInfo2 = context.fieldInfoList[i2];
            mw.visitVarInsn(25, 0);
            mw.visitLdcInsn("\"" + fieldInfo2.name + "\":");
            mw.visitMethodInsn(182, "java/lang/String", "toCharArray", "()[C");
            mw.visitFieldInsn(181, context.className, context.fieldName(fieldInfo2), "[C");
        }
        mw.visitInsn(177);
        mw.visitMaxs(4, 4);
        mw.visitEnd();
    }

    private void _createInstance(ClassWriter cw, Context context) {
        Constructor<?> defaultConstructor = ((Context)context).beanInfo.defaultConstructor;
        if (!Modifier.isPublic(defaultConstructor.getModifiers())) {
            return;
        }
        MethodWriter mw = new MethodWriter(cw, 1, "createInstance", "(L" + DefaultJSONParser + ";Ljava/lang/reflect/Type;)Ljava/lang/Object;", null, null);
        mw.visitTypeInsn(187, ASMUtils.type(context.getInstClass()));
        mw.visitInsn(89);
        mw.visitMethodInsn(183, ASMUtils.type(context.getInstClass()), "<init>", "()V");
        mw.visitInsn(176);
        mw.visitMaxs(3, 3);
        mw.visitEnd();
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    static class Context {
        static final int parser = 1;
        static final int type = 2;
        static final int fieldName = 3;
        private int variantIndex = -1;
        private final Map<String, Integer> variants = new HashMap<String, Integer>();
        private final Class<?> clazz;
        private final JavaBeanInfo beanInfo;
        private final String className;
        private FieldInfo[] fieldInfoList;

        public Context(String className, ParserConfig config, JavaBeanInfo beanInfo, int initVariantIndex) {
            this.className = className;
            this.clazz = beanInfo.clazz;
            this.variantIndex = initVariantIndex;
            this.beanInfo = beanInfo;
            this.fieldInfoList = beanInfo.fields;
        }

        public Class<?> getInstClass() {
            Class<?> instClass = this.beanInfo.builderClass;
            if (instClass == null) {
                instClass = this.clazz;
            }
            return instClass;
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

        public int var(String name) {
            Integer i = this.variants.get(name);
            if (i == null) {
                this.variants.put(name, this.variantIndex++);
            }
            i = this.variants.get(name);
            return i;
        }

        public int var_asm(FieldInfo fieldInfo) {
            return this.var(fieldInfo.name + "_asm");
        }

        public int var_asm(FieldInfo fieldInfo, int increment) {
            return this.var(fieldInfo.name + "_asm", increment);
        }

        public String fieldName(FieldInfo fieldInfo) {
            return this.validIdent(fieldInfo.name) ? fieldInfo.name + "_asm_prefix__" : "asm_field_" + TypeUtils.fnv1a_64_extract(fieldInfo.name);
        }

        public String fieldDeserName(FieldInfo fieldInfo) {
            return this.validIdent(fieldInfo.name) ? fieldInfo.name + "_asm_deser__" : "_asm_deser__" + TypeUtils.fnv1a_64_extract(fieldInfo.name);
        }

        boolean validIdent(String name) {
            for (int i = 0; i < name.length(); ++i) {
                char ch = name.charAt(i);
                if (!(ch == '\u0000' ? !IOUtils.firstIdentifier(ch) : !IOUtils.isIdent(ch))) continue;
                return false;
            }
            return true;
        }

        static /* synthetic */ FieldInfo[] access$202(Context x0, FieldInfo[] x1) {
            x0.fieldInfoList = x1;
            return x1;
        }
    }
}

