/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package bsh;

import bsh.BSHAmbiguousName;
import bsh.BSHArguments;
import bsh.BSHBlock;
import bsh.BSHMethodInvocation;
import bsh.BSHPrimaryExpression;
import bsh.BSHType;
import bsh.BshMethod;
import bsh.CallStack;
import bsh.ClassGeneratorImpl;
import bsh.DelayedEvalBshMethod;
import bsh.EvalError;
import bsh.Interpreter;
import bsh.InterpreterError;
import bsh.LHS;
import bsh.Modifiers;
import bsh.Name;
import bsh.NameSpace;
import bsh.Primitive;
import bsh.Reflect;
import bsh.SimpleNode;
import bsh.TargetError;
import bsh.This;
import bsh.Types;
import bsh.UtilEvalError;
import bsh.Variable;
import bsh.org.objectweb.asm.ClassWriter;
import bsh.org.objectweb.asm.CodeVisitor;
import bsh.org.objectweb.asm.Constants;
import bsh.org.objectweb.asm.Label;
import bsh.org.objectweb.asm.Type;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class ClassGeneratorUtil
implements Constants {
    static final String BSHSTATIC = "_bshStatic";
    static final String BSHTHIS = "_bshThis";
    static final String BSHSUPER = "_bshSuper";
    static final String BSHINIT = "_bshInstanceInitializer";
    static final String BSHCONSTRUCTORS = "_bshConstructors";
    static final int DEFAULTCONSTRUCTOR = -1;
    static final String OBJECT = "Ljava/lang/Object;";
    String className;
    String fqClassName;
    Class superClass;
    String superClassName;
    Class[] interfaces;
    Variable[] vars;
    Constructor[] superConstructors;
    DelayedEvalBshMethod[] constructors;
    DelayedEvalBshMethod[] methods;
    NameSpace classStaticNameSpace;
    Modifiers classModifiers;
    boolean isInterface;

    public ClassGeneratorUtil(Modifiers classModifiers, String className, String packageName, Class superClass, Class[] interfaces, Variable[] vars, DelayedEvalBshMethod[] bshmethods, NameSpace classStaticNameSpace, boolean isInterface) {
        this.classModifiers = classModifiers;
        this.className = className;
        this.fqClassName = packageName != null ? packageName.replace('.', '/') + "/" + className : className;
        if (superClass == null) {
            superClass = Object.class;
        }
        this.superClass = superClass;
        this.superClassName = Type.getInternalName(superClass);
        if (interfaces == null) {
            interfaces = new Class[]{};
        }
        this.interfaces = interfaces;
        this.vars = vars;
        this.classStaticNameSpace = classStaticNameSpace;
        this.superConstructors = superClass.getDeclaredConstructors();
        ArrayList<DelayedEvalBshMethod> consl = new ArrayList<DelayedEvalBshMethod>();
        ArrayList<DelayedEvalBshMethod> methodsl = new ArrayList<DelayedEvalBshMethod>();
        String classBaseName = ClassGeneratorUtil.getBaseName(className);
        for (int i = 0; i < bshmethods.length; ++i) {
            DelayedEvalBshMethod bm = bshmethods[i];
            if (bm.getName().equals(classBaseName) && ((BshMethod)bm).getReturnType() == null) {
                consl.add(bm);
                continue;
            }
            methodsl.add(bm);
        }
        this.constructors = consl.toArray(new DelayedEvalBshMethod[0]);
        this.methods = methodsl.toArray(new DelayedEvalBshMethod[0]);
        try {
            classStaticNameSpace.setLocalVariable(BSHCONSTRUCTORS, this.constructors, false);
        } catch (UtilEvalError e) {
            throw new InterpreterError("can't set cons var");
        }
        this.isInterface = isInterface;
    }

    public byte[] generateClass() {
        int i;
        int modifiers;
        int classMods = ClassGeneratorUtil.getASMModifiers(this.classModifiers) | 1;
        if (this.isInterface) {
            classMods |= 0x200;
        }
        String[] interfaceNames = new String[this.interfaces.length];
        for (int i2 = 0; i2 < this.interfaces.length; ++i2) {
            interfaceNames[i2] = Type.getInternalName(this.interfaces[i2]);
        }
        String sourceFile = "BeanShell Generated via ASM (www.objectweb.org)";
        ClassWriter cw = new ClassWriter(true);
        cw.visit(classMods, this.fqClassName, this.superClassName, interfaceNames, sourceFile);
        if (!this.isInterface) {
            ClassGeneratorUtil.generateField(BSHTHIS + this.className, "Lbsh/This;", 1, cw);
            ClassGeneratorUtil.generateField(BSHSTATIC + this.className, "Lbsh/This;", 9, cw);
        }
        for (int i3 = 0; i3 < this.vars.length; ++i3) {
            String type = this.vars[i3].getTypeDescriptor();
            if (this.vars[i3].hasModifier("private") || type == null) continue;
            modifiers = this.isInterface ? 25 : ClassGeneratorUtil.getASMModifiers(this.vars[i3].getModifiers());
            ClassGeneratorUtil.generateField(this.vars[i3].getName(), type, modifiers, cw);
        }
        boolean hasConstructor = false;
        for (i = 0; i < this.constructors.length; ++i) {
            if (this.constructors[i].hasModifier("private")) continue;
            modifiers = ClassGeneratorUtil.getASMModifiers(this.constructors[i].getModifiers());
            this.generateConstructor(i, this.constructors[i].getParamTypeDescriptors(), modifiers, cw);
            hasConstructor = true;
        }
        if (!this.isInterface && !hasConstructor) {
            this.generateConstructor(-1, new String[0], 1, cw);
        }
        for (i = 0; i < this.methods.length; ++i) {
            String returnType = this.methods[i].getReturnTypeDescriptor();
            if (this.methods[i].hasModifier("private")) continue;
            int modifiers2 = ClassGeneratorUtil.getASMModifiers(this.methods[i].getModifiers());
            if (this.isInterface) {
                modifiers2 |= 0x401;
            }
            ClassGeneratorUtil.generateMethod(this.className, this.fqClassName, this.methods[i].getName(), returnType, this.methods[i].getParamTypeDescriptors(), modifiers2, cw);
            boolean isStatic = (modifiers2 & 8) > 0;
            boolean overridden = this.classContainsMethod(this.superClass, this.methods[i].getName(), this.methods[i].getParamTypeDescriptors());
            if (isStatic || !overridden) continue;
            ClassGeneratorUtil.generateSuperDelegateMethod(this.superClassName, this.methods[i].getName(), returnType, this.methods[i].getParamTypeDescriptors(), modifiers2, cw);
        }
        return cw.toByteArray();
    }

    static int getASMModifiers(Modifiers modifiers) {
        int mods = 0;
        if (modifiers == null) {
            return mods;
        }
        if (modifiers.hasModifier("public")) {
            ++mods;
        }
        if (modifiers.hasModifier("protected")) {
            mods += 4;
        }
        if (modifiers.hasModifier("static")) {
            mods += 8;
        }
        if (modifiers.hasModifier("synchronized")) {
            mods += 32;
        }
        if (modifiers.hasModifier("abstract")) {
            mods += 1024;
        }
        return mods;
    }

    static void generateField(String fieldName, String type, int modifiers, ClassWriter cw) {
        cw.visitField(modifiers, fieldName, type, null);
    }

    static void generateMethod(String className, String fqClassName, String methodName, String returnType, String[] paramTypes, int modifiers, ClassWriter cw) {
        boolean isStatic;
        String[] exceptions = null;
        boolean bl = isStatic = (modifiers & 8) != 0;
        if (returnType == null) {
            returnType = OBJECT;
        }
        String methodDescriptor = ClassGeneratorUtil.getMethodDescriptor(returnType, paramTypes);
        CodeVisitor cv = cw.visitMethod(modifiers, methodName, methodDescriptor, exceptions);
        if ((modifiers & 0x400) != 0) {
            return;
        }
        if (isStatic) {
            cv.visitFieldInsn(178, fqClassName, BSHSTATIC + className, "Lbsh/This;");
        } else {
            cv.visitVarInsn(25, 0);
            cv.visitFieldInsn(180, fqClassName, BSHTHIS + className, "Lbsh/This;");
        }
        cv.visitLdcInsn(methodName);
        ClassGeneratorUtil.generateParameterReifierCode(paramTypes, isStatic, cv);
        cv.visitInsn(1);
        cv.visitInsn(1);
        cv.visitInsn(1);
        cv.visitInsn(4);
        cv.visitMethodInsn(182, "bsh/This", "invokeMethod", Type.getMethodDescriptor(Type.getType(Object.class), new Type[]{Type.getType(String.class), Type.getType(Object[].class), Type.getType(Interpreter.class), Type.getType(CallStack.class), Type.getType(SimpleNode.class), Type.getType(Boolean.TYPE)}));
        cv.visitMethodInsn(184, "bsh/Primitive", "unwrap", "(Ljava/lang/Object;)Ljava/lang/Object;");
        ClassGeneratorUtil.generateReturnCode(returnType, cv);
        cv.visitMaxs(0, 0);
    }

    void generateConstructor(int index, String[] paramTypes, int modifiers, ClassWriter cw) {
        int argsVar = paramTypes.length + 1;
        int consArgsVar = paramTypes.length + 2;
        String[] exceptions = null;
        String methodDescriptor = ClassGeneratorUtil.getMethodDescriptor("V", paramTypes);
        CodeVisitor cv = cw.visitMethod(modifiers, "<init>", methodDescriptor, exceptions);
        ClassGeneratorUtil.generateParameterReifierCode(paramTypes, false, cv);
        cv.visitVarInsn(58, argsVar);
        this.generateConstructorSwitch(index, argsVar, consArgsVar, cv);
        cv.visitVarInsn(25, 0);
        cv.visitLdcInsn(this.className);
        cv.visitVarInsn(25, argsVar);
        cv.visitMethodInsn(184, "bsh/ClassGeneratorUtil", "initInstance", "(Ljava/lang/Object;Ljava/lang/String;[Ljava/lang/Object;)V");
        cv.visitInsn(177);
        cv.visitMaxs(0, 0);
    }

    void generateConstructorSwitch(int consIndex, int argsVar, int consArgsVar, CodeVisitor cv) {
        Label defaultLabel = new Label();
        Label endLabel = new Label();
        int cases = this.superConstructors.length + this.constructors.length;
        Label[] labels = new Label[cases];
        for (int i = 0; i < cases; ++i) {
            labels[i] = new Label();
        }
        cv.visitLdcInsn(this.superClass.getName());
        cv.visitFieldInsn(178, this.fqClassName, BSHSTATIC + this.className, "Lbsh/This;");
        cv.visitVarInsn(25, argsVar);
        cv.visitIntInsn(16, consIndex);
        cv.visitMethodInsn(184, "bsh/ClassGeneratorUtil", "getConstructorArgs", "(Ljava/lang/String;Lbsh/This;[Ljava/lang/Object;I)Lbsh/ClassGeneratorUtil$ConstructorArgs;");
        cv.visitVarInsn(58, consArgsVar);
        cv.visitVarInsn(25, consArgsVar);
        cv.visitFieldInsn(180, "bsh/ClassGeneratorUtil$ConstructorArgs", "selector", "I");
        cv.visitTableSwitchInsn(0, cases - 1, defaultLabel, labels);
        int index = 0;
        int i = 0;
        while (i < this.superConstructors.length) {
            ClassGeneratorUtil.doSwitchBranch(index, this.superClassName, ClassGeneratorUtil.getTypeDescriptors(this.superConstructors[i].getParameterTypes()), endLabel, labels, consArgsVar, cv);
            ++i;
            ++index;
        }
        i = 0;
        while (i < this.constructors.length) {
            ClassGeneratorUtil.doSwitchBranch(index, this.fqClassName, this.constructors[i].getParamTypeDescriptors(), endLabel, labels, consArgsVar, cv);
            ++i;
            ++index;
        }
        cv.visitLabel(defaultLabel);
        cv.visitVarInsn(25, 0);
        cv.visitMethodInsn(183, this.superClassName, "<init>", "()V");
        cv.visitLabel(endLabel);
    }

    static void doSwitchBranch(int index, String targetClassName, String[] paramTypes, Label endLabel, Label[] labels, int consArgsVar, CodeVisitor cv) {
        cv.visitLabel(labels[index]);
        cv.visitVarInsn(25, 0);
        for (int i = 0; i < paramTypes.length; ++i) {
            String type = paramTypes[i];
            String method = null;
            method = type.equals("Z") ? "getBoolean" : (type.equals("B") ? "getByte" : (type.equals("C") ? "getChar" : (type.equals("S") ? "getShort" : (type.equals("I") ? "getInt" : (type.equals("J") ? "getLong" : (type.equals("D") ? "getDouble" : (type.equals("F") ? "getFloat" : "getObject")))))));
            cv.visitVarInsn(25, consArgsVar);
            String className = "bsh/ClassGeneratorUtil$ConstructorArgs";
            String retType = method.equals("getObject") ? OBJECT : type;
            cv.visitMethodInsn(182, className, method, "()" + retType);
            if (!method.equals("getObject")) continue;
            cv.visitTypeInsn(192, ClassGeneratorUtil.descriptorToClassName(type));
        }
        String descriptor = ClassGeneratorUtil.getMethodDescriptor("V", paramTypes);
        cv.visitMethodInsn(183, targetClassName, "<init>", descriptor);
        cv.visitJumpInsn(167, endLabel);
    }

    static String getMethodDescriptor(String returnType, String[] paramTypes) {
        StringBuffer sb = new StringBuffer("(");
        for (int i = 0; i < paramTypes.length; ++i) {
            sb.append(paramTypes[i]);
        }
        sb.append(")" + returnType);
        return sb.toString();
    }

    static void generateSuperDelegateMethod(String superClassName, String methodName, String returnType, String[] paramTypes, int modifiers, ClassWriter cw) {
        String[] exceptions = null;
        if (returnType == null) {
            returnType = OBJECT;
        }
        String methodDescriptor = ClassGeneratorUtil.getMethodDescriptor(returnType, paramTypes);
        CodeVisitor cv = cw.visitMethod(modifiers, BSHSUPER + methodName, methodDescriptor, exceptions);
        cv.visitVarInsn(25, 0);
        int localVarIndex = 1;
        for (int i = 0; i < paramTypes.length; ++i) {
            if (ClassGeneratorUtil.isPrimitive(paramTypes[i])) {
                cv.visitVarInsn(21, localVarIndex);
            } else {
                cv.visitVarInsn(25, localVarIndex);
            }
            localVarIndex += paramTypes[i].equals("D") || paramTypes[i].equals("J") ? 2 : 1;
        }
        cv.visitMethodInsn(183, superClassName, methodName, methodDescriptor);
        ClassGeneratorUtil.generatePlainReturnCode(returnType, cv);
        cv.visitMaxs(20, 20);
    }

    boolean classContainsMethod(Class clas, String methodName, String[] paramTypes) {
        while (clas != null) {
            Method[] methods = clas.getDeclaredMethods();
            for (int i = 0; i < methods.length; ++i) {
                if (!methods[i].getName().equals(methodName)) continue;
                String[] methodParamTypes = ClassGeneratorUtil.getTypeDescriptors(methods[i].getParameterTypes());
                boolean found = true;
                for (int j = 0; j < methodParamTypes.length; ++j) {
                    if (paramTypes[j].equals(methodParamTypes[j])) continue;
                    found = false;
                    break;
                }
                if (!found) continue;
                return true;
            }
            clas = clas.getSuperclass();
        }
        return false;
    }

    static void generatePlainReturnCode(String returnType, CodeVisitor cv) {
        if (returnType.equals("V")) {
            cv.visitInsn(177);
        } else if (ClassGeneratorUtil.isPrimitive(returnType)) {
            int opcode = 172;
            if (returnType.equals("D")) {
                opcode = 175;
            } else if (returnType.equals("F")) {
                opcode = 174;
            } else if (returnType.equals("J")) {
                opcode = 173;
            }
            cv.visitInsn(opcode);
        } else {
            cv.visitTypeInsn(192, ClassGeneratorUtil.descriptorToClassName(returnType));
            cv.visitInsn(176);
        }
    }

    public static void generateParameterReifierCode(String[] paramTypes, boolean isStatic, CodeVisitor cv) {
        cv.visitIntInsn(17, paramTypes.length);
        cv.visitTypeInsn(189, "java/lang/Object");
        int localVarIndex = isStatic ? 0 : 1;
        for (int i = 0; i < paramTypes.length; ++i) {
            String param = paramTypes[i];
            cv.visitInsn(89);
            cv.visitIntInsn(17, i);
            if (ClassGeneratorUtil.isPrimitive(param)) {
                int opcode = param.equals("F") ? 23 : (param.equals("D") ? 24 : (param.equals("J") ? 22 : 21));
                String type = "bsh/Primitive";
                cv.visitTypeInsn(187, type);
                cv.visitInsn(89);
                cv.visitVarInsn(opcode, localVarIndex);
                String desc = param;
                cv.visitMethodInsn(183, type, "<init>", "(" + desc + ")V");
            } else {
                cv.visitVarInsn(25, localVarIndex);
            }
            cv.visitInsn(83);
            localVarIndex += param.equals("D") || param.equals("J") ? 2 : 1;
        }
    }

    public static void generateReturnCode(String returnType, CodeVisitor cv) {
        if (returnType.equals("V")) {
            cv.visitInsn(87);
            cv.visitInsn(177);
        } else if (ClassGeneratorUtil.isPrimitive(returnType)) {
            String meth;
            String type;
            int opcode = 172;
            if (returnType.equals("B")) {
                type = "java/lang/Byte";
                meth = "byteValue";
            } else if (returnType.equals("I")) {
                type = "java/lang/Integer";
                meth = "intValue";
            } else if (returnType.equals("Z")) {
                type = "java/lang/Boolean";
                meth = "booleanValue";
            } else if (returnType.equals("D")) {
                opcode = 175;
                type = "java/lang/Double";
                meth = "doubleValue";
            } else if (returnType.equals("F")) {
                opcode = 174;
                type = "java/lang/Float";
                meth = "floatValue";
            } else if (returnType.equals("J")) {
                opcode = 173;
                type = "java/lang/Long";
                meth = "longValue";
            } else if (returnType.equals("C")) {
                type = "java/lang/Character";
                meth = "charValue";
            } else {
                type = "java/lang/Short";
                meth = "shortValue";
            }
            String desc = returnType;
            cv.visitTypeInsn(192, type);
            cv.visitMethodInsn(182, type, meth, "()" + desc);
            cv.visitInsn(opcode);
        } else {
            cv.visitTypeInsn(192, ClassGeneratorUtil.descriptorToClassName(returnType));
            cv.visitInsn(176);
        }
    }

    public static ConstructorArgs getConstructorArgs(String superClassName, This classStaticThis, Object[] consArgs, int index) {
        int i;
        DelayedEvalBshMethod[] constructors;
        try {
            constructors = (DelayedEvalBshMethod[])classStaticThis.getNameSpace().getVariable(BSHCONSTRUCTORS);
        } catch (Exception e) {
            throw new InterpreterError("unable to get instance initializer: " + e);
        }
        if (index == -1) {
            return ConstructorArgs.DEFAULT;
        }
        DelayedEvalBshMethod constructor = constructors[index];
        if (constructor.methodBody.jjtGetNumChildren() == 0) {
            return ConstructorArgs.DEFAULT;
        }
        String altConstructor = null;
        BSHArguments argsNode = null;
        SimpleNode firstStatement = (SimpleNode)constructor.methodBody.jjtGetChild(0);
        if (firstStatement instanceof BSHPrimaryExpression) {
            firstStatement = (SimpleNode)firstStatement.jjtGetChild(0);
        }
        if (firstStatement instanceof BSHMethodInvocation) {
            BSHMethodInvocation methodNode = (BSHMethodInvocation)firstStatement;
            BSHAmbiguousName methodName = methodNode.getNameNode();
            if (methodName.text.equals("super") || methodName.text.equals("this")) {
                altConstructor = methodName.text;
                argsNode = methodNode.getArgsNode();
            }
        }
        if (altConstructor == null) {
            return ConstructorArgs.DEFAULT;
        }
        NameSpace consArgsNameSpace = new NameSpace(classStaticThis.getNameSpace(), "consArgs");
        String[] consArgNames = constructor.getParameterNames();
        Class[] consArgTypes = constructor.getParameterTypes();
        for (int i2 = 0; i2 < consArgs.length; ++i2) {
            try {
                consArgsNameSpace.setTypedVariable(consArgNames[i2], consArgTypes[i2], consArgs[i2], null);
                continue;
            } catch (UtilEvalError e) {
                throw new InterpreterError("err setting local cons arg:" + e);
            }
        }
        CallStack callstack = new CallStack();
        callstack.push(consArgsNameSpace);
        Object[] args2 = null;
        Interpreter interpreter = classStaticThis.declaringInterpreter;
        try {
            args2 = argsNode.getArguments(callstack, interpreter);
        } catch (EvalError e) {
            throw new InterpreterError("Error evaluating constructor args: " + e);
        }
        Class[] argTypes = Types.getTypes(args2);
        args2 = Primitive.unwrap(args2);
        Class superClass = interpreter.getClassManager().classForName(superClassName);
        if (superClass == null) {
            throw new InterpreterError("can't find superclass: " + superClassName);
        }
        Constructor[] superCons = superClass.getDeclaredConstructors();
        if (altConstructor.equals("super")) {
            int i3 = Reflect.findMostSpecificConstructorIndex(argTypes, superCons);
            if (i3 == -1) {
                throw new InterpreterError("can't find constructor for args!");
            }
            return new ConstructorArgs(i3, args2);
        }
        Class[][] candidates = new Class[constructors.length][];
        for (i = 0; i < candidates.length; ++i) {
            candidates[i] = constructors[i].getParameterTypes();
        }
        i = Reflect.findMostSpecificSignature(argTypes, candidates);
        if (i == -1) {
            throw new InterpreterError("can't find constructor for args 2!");
        }
        int selector = i + superCons.length;
        int ourSelector = index + superCons.length;
        if (selector == ourSelector) {
            throw new InterpreterError("Recusive constructor call.");
        }
        return new ConstructorArgs(selector, args2);
    }

    public static void initInstance(Object instance, String className, Object[] args2) {
        NameSpace instanceNameSpace;
        Interpreter interpreter;
        Class[] sig = Types.getTypes(args2);
        CallStack callstack = new CallStack();
        This instanceThis = ClassGeneratorUtil.getClassInstanceThis(instance, className);
        if (instanceThis == null) {
            BSHBlock instanceInitBlock;
            This classStaticThis = ClassGeneratorUtil.getClassStaticThis(instance.getClass(), className);
            if (classStaticThis == null) {
                throw new InterpreterError("Failed to init class: " + className);
            }
            interpreter = classStaticThis.declaringInterpreter;
            try {
                instanceInitBlock = (BSHBlock)classStaticThis.getNameSpace().getVariable(BSHINIT);
            } catch (Exception e) {
                throw new InterpreterError("unable to get instance initializer: " + e);
            }
            instanceNameSpace = new NameSpace(classStaticThis.getNameSpace(), className);
            instanceNameSpace.isClass = true;
            instanceThis = instanceNameSpace.getThis(interpreter);
            try {
                LHS lhs = Reflect.getLHSObjectField(instance, BSHTHIS + className);
                lhs.assign(instanceThis, false);
            } catch (Exception e) {
                throw new InterpreterError("Error in class gen setup: " + e);
            }
            instanceNameSpace.setClassInstance(instance);
            callstack.push(instanceNameSpace);
            try {
                instanceInitBlock.evalBlock(callstack, interpreter, true, ClassGeneratorImpl.ClassNodeFilter.CLASSINSTANCE);
            } catch (Exception e) {
                throw new InterpreterError("Error in class initialization: " + e);
            }
            callstack.pop();
        } else {
            interpreter = instanceThis.declaringInterpreter;
            instanceNameSpace = instanceThis.getNameSpace();
        }
        String constructorName = ClassGeneratorUtil.getBaseName(className);
        try {
            BshMethod constructor = instanceNameSpace.getMethod(constructorName, sig, true);
            if (constructor != null && constructor.getReturnType() != null) {
                constructor = null;
            }
            if (args2.length > 0 && constructor == null) {
                throw new InterpreterError("Can't find constructor: " + className);
            }
            if (constructor != null) {
                constructor.invoke(args2, interpreter, callstack, null, false);
            }
        } catch (Exception e) {
            if (Interpreter.DEBUG) {
                e.printStackTrace();
            }
            if (e instanceof TargetError) {
                e = (Exception)((TargetError)e).getTarget();
            }
            if (e instanceof InvocationTargetException) {
                e = (Exception)((InvocationTargetException)e).getTargetException();
            }
            throw new InterpreterError("Error in class initialization: " + e);
        }
    }

    static This getClassStaticThis(Class clas, String className) {
        try {
            return (This)Reflect.getStaticFieldValue(clas, BSHSTATIC + className);
        } catch (Exception e) {
            throw new InterpreterError("Unable to get class static space: " + e);
        }
    }

    static This getClassInstanceThis(Object instance, String className) {
        try {
            Object o = Reflect.getObjectFieldValue(instance, BSHTHIS + className);
            return (This)Primitive.unwrap(o);
        } catch (Exception e) {
            throw new InterpreterError("Generated class: Error getting This" + e);
        }
    }

    private static boolean isPrimitive(String typeDescriptor) {
        return typeDescriptor.length() == 1;
    }

    static String[] getTypeDescriptors(Class[] cparams) {
        String[] sa = new String[cparams.length];
        for (int i = 0; i < sa.length; ++i) {
            sa[i] = BSHType.getTypeDescriptor(cparams[i]);
        }
        return sa;
    }

    private static String descriptorToClassName(String s2) {
        if (s2.startsWith("[") || !s2.startsWith("L")) {
            return s2;
        }
        return s2.substring(1, s2.length() - 1);
    }

    private static String getBaseName(String className) {
        int i = className.indexOf("$");
        if (i == -1) {
            return className;
        }
        return className.substring(i + 1);
    }

    public static void startInterpreterForClass(Class clas) {
        System.out.println("starting interpreter for class: " + clas);
        String baseName = Name.suffix(clas.getName(), 1);
        String resName = baseName + ".bsh";
        InputStreamReader in = new InputStreamReader(clas.getResourceAsStream(resName));
        if (in == null) {
            throw new InterpreterError("Script for BeanShell class file not found: " + resName);
        }
        Interpreter bsh = new Interpreter();
        try {
            bsh.eval(in, bsh.getNameSpace(), resName);
        } catch (TargetError e) {
            System.out.println("Script threw exception: " + e);
            if (e.inNativeCode()) {
                e.printStackTrace(System.err);
            }
        } catch (EvalError e) {
            System.out.println("Evaluation Error: " + e);
        }
    }

    public static class ConstructorArgs {
        public static ConstructorArgs DEFAULT = new ConstructorArgs();
        public int selector = -1;
        Object[] args;
        int arg = 0;

        ConstructorArgs() {
        }

        ConstructorArgs(int selector, Object[] args2) {
            this.selector = selector;
            this.args = args2;
        }

        Object next() {
            return this.args[this.arg++];
        }

        public boolean getBoolean() {
            return (Boolean)this.next();
        }

        public byte getByte() {
            return (Byte)this.next();
        }

        public char getChar() {
            return ((Character)this.next()).charValue();
        }

        public short getShort() {
            return (Short)this.next();
        }

        public int getInt() {
            return (Integer)this.next();
        }

        public long getLong() {
            return (Long)this.next();
        }

        public double getDouble() {
            return (Double)this.next();
        }

        public float getFloat() {
            return ((Float)this.next()).floatValue();
        }

        public Object getObject() {
            return this.next();
        }
    }
}

