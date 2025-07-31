/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package bsh;

import bsh.BSHBlock;
import bsh.BSHClassDeclaration;
import bsh.BSHFormalParameters;
import bsh.BSHMethodDeclaration;
import bsh.BSHReturnType;
import bsh.BSHTypedVariableDeclaration;
import bsh.BSHVariableDeclarator;
import bsh.BshClassManager;
import bsh.CallStack;
import bsh.Capabilities;
import bsh.ClassGenerator;
import bsh.ClassGeneratorUtil;
import bsh.DelayedEvalBshMethod;
import bsh.EvalError;
import bsh.Interpreter;
import bsh.InterpreterError;
import bsh.LHS;
import bsh.Modifiers;
import bsh.NameSpace;
import bsh.Reflect;
import bsh.ReflectError;
import bsh.SimpleNode;
import bsh.This;
import bsh.Types;
import bsh.UtilEvalError;
import bsh.Variable;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class ClassGeneratorImpl
extends ClassGenerator {
    public Class generateClass(String name, Modifiers modifiers, Class[] interfaces, Class superClass, BSHBlock block, boolean isInterface, CallStack callstack, Interpreter interpreter) throws EvalError {
        return ClassGeneratorImpl.generateClassImpl(name, modifiers, interfaces, superClass, block, isInterface, callstack, interpreter);
    }

    public Object invokeSuperclassMethod(BshClassManager bcm, Object instance, String methodName, Object[] args2) throws UtilEvalError, ReflectError, InvocationTargetException {
        return ClassGeneratorImpl.invokeSuperclassMethodImpl(bcm, instance, methodName, args2);
    }

    public void setInstanceNameSpaceParent(Object instance, String className, NameSpace parent) {
        This ithis = ClassGeneratorUtil.getClassInstanceThis(instance, className);
        ithis.getNameSpace().setParent(parent);
    }

    public static Class generateClassImpl(String name, Modifiers modifiers, Class[] interfaces, Class superClass, BSHBlock block, boolean isInterface, CallStack callstack, Interpreter interpreter) throws EvalError {
        try {
            Capabilities.setAccessibility(true);
        } catch (Capabilities.Unavailable e) {
            throw new EvalError("Defining classes currently requires reflective Accessibility.", block, callstack);
        }
        NameSpace enclosingNameSpace = callstack.top();
        String packageName = enclosingNameSpace.getPackage();
        String className = enclosingNameSpace.isClass ? enclosingNameSpace.getName() + "$" + name : name;
        String fqClassName = packageName == null ? className : packageName + "." + className;
        String bshStaticFieldName = "_bshStatic" + className;
        BshClassManager bcm = interpreter.getClassManager();
        bcm.definingClass(fqClassName);
        NameSpace classStaticNameSpace = new NameSpace(enclosingNameSpace, className);
        classStaticNameSpace.isClass = true;
        callstack.push(classStaticNameSpace);
        block.evalBlock(callstack, interpreter, true, ClassNodeFilter.CLASSCLASSES);
        Variable[] variables = ClassGeneratorImpl.getDeclaredVariables(block, callstack, interpreter, packageName);
        DelayedEvalBshMethod[] methods = ClassGeneratorImpl.getDeclaredMethods(block, callstack, interpreter, packageName);
        Class genClass = null;
        if (genClass == null) {
            Interpreter.debug("generating class: " + fqClassName);
            ClassGeneratorUtil classGenerator = new ClassGeneratorUtil(modifiers, className, packageName, superClass, interfaces, variables, methods, classStaticNameSpace, isInterface);
            byte[] code = classGenerator.generateClass();
            ClassGeneratorImpl.debugClasses(className, code);
            genClass = bcm.defineClass(fqClassName, code);
        } else {
            Interpreter.debug("Found existing generated class: " + fqClassName);
        }
        enclosingNameSpace.importClass(fqClassName.replace('$', '.'));
        try {
            classStaticNameSpace.setLocalVariable("_bshInstanceInitializer", block, false);
        } catch (UtilEvalError e) {
            throw new InterpreterError("unable to init static: " + e);
        }
        classStaticNameSpace.setClassStatic(genClass);
        block.evalBlock(callstack, interpreter, true, ClassNodeFilter.CLASSSTATIC);
        callstack.pop();
        if (!genClass.isInterface()) {
            try {
                LHS lhs = Reflect.getLHSStaticField(genClass, bshStaticFieldName);
                lhs.assign(classStaticNameSpace.getThis(interpreter), false);
            } catch (Exception e) {
                throw new InterpreterError("Error in class gen setup: " + e);
            }
        }
        bcm.doneDefiningClass(fqClassName);
        return genClass;
    }

    private static void debugClasses(String className, byte[] code) {
        String dir2 = System.getProperty("debugClasses");
        if (dir2 != null) {
            try {
                FileOutputStream out = new FileOutputStream(dir2 + "/" + className + ".class");
                out.write(code);
                out.close();
            } catch (IOException iOException) {
                // empty catch block
            }
        }
    }

    static Variable[] getDeclaredVariables(BSHBlock body, CallStack callstack, Interpreter interpreter, String defaultPackage) {
        ArrayList<Variable> vars = new ArrayList<Variable>();
        for (int child = 0; child < body.jjtGetNumChildren(); ++child) {
            SimpleNode node = (SimpleNode)body.jjtGetChild(child);
            if (!(node instanceof BSHTypedVariableDeclaration)) continue;
            BSHTypedVariableDeclaration tvd = (BSHTypedVariableDeclaration)node;
            Modifiers modifiers = tvd.modifiers;
            String type = tvd.getTypeDescriptor(callstack, interpreter, defaultPackage);
            BSHVariableDeclarator[] vardec = tvd.getDeclarators();
            for (int i = 0; i < vardec.length; ++i) {
                String name = vardec[i].name;
                try {
                    Variable var = new Variable(name, type, null, modifiers);
                    vars.add(var);
                    continue;
                } catch (UtilEvalError e) {
                    // empty catch block
                }
            }
        }
        return vars.toArray(new Variable[0]);
    }

    static DelayedEvalBshMethod[] getDeclaredMethods(BSHBlock body, CallStack callstack, Interpreter interpreter, String defaultPackage) throws EvalError {
        ArrayList<DelayedEvalBshMethod> methods = new ArrayList<DelayedEvalBshMethod>();
        for (int child = 0; child < body.jjtGetNumChildren(); ++child) {
            SimpleNode node = (SimpleNode)body.jjtGetChild(child);
            if (!(node instanceof BSHMethodDeclaration)) continue;
            BSHMethodDeclaration md = (BSHMethodDeclaration)node;
            md.insureNodesParsed();
            Modifiers modifiers = md.modifiers;
            String name = md.name;
            String returnType = md.getReturnTypeDescriptor(callstack, interpreter, defaultPackage);
            BSHReturnType returnTypeNode = md.getReturnTypeNode();
            BSHFormalParameters paramTypesNode = md.paramsNode;
            String[] paramTypes = paramTypesNode.getTypeDescriptors(callstack, interpreter, defaultPackage);
            DelayedEvalBshMethod bm = new DelayedEvalBshMethod(name, returnType, returnTypeNode, md.paramsNode.getParamNames(), paramTypes, paramTypesNode, md.blockNode, null, modifiers, callstack, interpreter);
            methods.add(bm);
        }
        return methods.toArray(new DelayedEvalBshMethod[0]);
    }

    public static Object invokeSuperclassMethodImpl(BshClassManager bcm, Object instance, String methodName, Object[] args2) throws UtilEvalError, ReflectError, InvocationTargetException {
        String superName = "_bshSuper" + methodName;
        Class<?> clas = instance.getClass();
        Method superMethod = Reflect.resolveJavaMethod(bcm, clas, superName, Types.getTypes(args2), false);
        if (superMethod != null) {
            return Reflect.invokeMethod(superMethod, instance, args2);
        }
        Class<?> superClass = clas.getSuperclass();
        superMethod = Reflect.resolveExpectedJavaMethod(bcm, superClass, instance, methodName, args2, false);
        return Reflect.invokeMethod(superMethod, instance, args2);
    }

    private static Class getExistingGeneratedClass(BshClassManager bcm, String fqClassName, String bshStaticFieldName) {
        Class genClass = bcm.classForName(fqClassName);
        boolean isGenClass = false;
        if (genClass != null) {
            try {
                isGenClass = Reflect.resolveJavaField(genClass, bshStaticFieldName, true) != null;
            } catch (Exception e) {
                // empty catch block
            }
        }
        return isGenClass ? genClass : null;
    }

    static class ClassNodeFilter
    implements BSHBlock.NodeFilter {
        public static final int STATIC = 0;
        public static final int INSTANCE = 1;
        public static final int CLASSES = 2;
        public static ClassNodeFilter CLASSSTATIC = new ClassNodeFilter(0);
        public static ClassNodeFilter CLASSINSTANCE = new ClassNodeFilter(1);
        public static ClassNodeFilter CLASSCLASSES = new ClassNodeFilter(2);
        int context;

        private ClassNodeFilter(int context) {
            this.context = context;
        }

        public boolean isVisible(SimpleNode node) {
            if (this.context == 2) {
                return node instanceof BSHClassDeclaration;
            }
            if (node instanceof BSHClassDeclaration) {
                return false;
            }
            if (this.context == 0) {
                return this.isStatic(node);
            }
            if (this.context == 1) {
                return !this.isStatic(node);
            }
            return true;
        }

        boolean isStatic(SimpleNode node) {
            if (node instanceof BSHTypedVariableDeclaration) {
                return ((BSHTypedVariableDeclaration)node).modifiers != null && ((BSHTypedVariableDeclaration)node).modifiers.hasModifier("static");
            }
            if (node instanceof BSHMethodDeclaration) {
                return ((BSHMethodDeclaration)node).modifiers != null && ((BSHMethodDeclaration)node).modifiers.hasModifier("static");
            }
            if (node instanceof BSHBlock) {
                return false;
            }
            return false;
        }
    }
}

