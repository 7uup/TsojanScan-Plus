/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package bsh;

import bsh.BshClassManager;
import bsh.BshMethod;
import bsh.CallStack;
import bsh.ClassGenerator;
import bsh.ClassIdentifier;
import bsh.EvalError;
import bsh.Interpreter;
import bsh.InterpreterError;
import bsh.LHS;
import bsh.NameSpace;
import bsh.Primitive;
import bsh.Reflect;
import bsh.ReflectError;
import bsh.SimpleNode;
import bsh.StringUtil;
import bsh.This;
import bsh.Types;
import bsh.UtilEvalError;
import bsh.UtilTargetError;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;

class Name
implements Serializable {
    public NameSpace namespace;
    String value = null;
    private String evalName;
    private String lastEvalName;
    private static String FINISHED = null;
    private Object evalBaseObject;
    private int callstackDepth;
    Class asClass;
    Class classOfStaticMethod;

    private void reset() {
        this.evalName = this.value;
        this.evalBaseObject = null;
        this.callstackDepth = 0;
    }

    Name(NameSpace namespace, String s2) {
        this.namespace = namespace;
        this.value = s2;
    }

    public Object toObject(CallStack callstack, Interpreter interpreter) throws UtilEvalError {
        return this.toObject(callstack, interpreter, false);
    }

    public synchronized Object toObject(CallStack callstack, Interpreter interpreter, boolean forceClass) throws UtilEvalError {
        this.reset();
        Object obj = null;
        while (this.evalName != null) {
            obj = this.consumeNextObjectField(callstack, interpreter, forceClass, false);
        }
        if (obj == null) {
            throw new InterpreterError("null value in toObject()");
        }
        return obj;
    }

    private Object completeRound(String lastEvalName, String nextEvalName, Object returnObject) {
        if (returnObject == null) {
            throw new InterpreterError("lastEvalName = " + lastEvalName);
        }
        this.lastEvalName = lastEvalName;
        this.evalName = nextEvalName;
        this.evalBaseObject = returnObject;
        return returnObject;
    }

    private Object consumeNextObjectField(CallStack callstack, Interpreter interpreter, boolean forceClass, boolean autoAllocateThis) throws UtilEvalError {
        Class clas;
        Object obj;
        if (this.evalBaseObject == null && !Name.isCompound(this.evalName) && !forceClass && (obj = this.resolveThisFieldReference(callstack, this.namespace, interpreter, this.evalName, false)) != Primitive.VOID) {
            return this.completeRound(this.evalName, FINISHED, obj);
        }
        String varName = Name.prefix(this.evalName, 1);
        if ((this.evalBaseObject == null || this.evalBaseObject instanceof This) && !forceClass) {
            Object obj2;
            if (Interpreter.DEBUG) {
                Interpreter.debug("trying to resolve variable: " + varName);
            }
            if ((obj2 = this.evalBaseObject == null ? this.resolveThisFieldReference(callstack, this.namespace, interpreter, varName, false) : this.resolveThisFieldReference(callstack, ((This)this.evalBaseObject).namespace, interpreter, varName, true)) != Primitive.VOID) {
                if (Interpreter.DEBUG) {
                    Interpreter.debug("resolved variable: " + varName + " in namespace: " + this.namespace);
                }
                return this.completeRound(varName, Name.suffix(this.evalName), obj2);
            }
        }
        if (this.evalBaseObject == null) {
            int i;
            if (Interpreter.DEBUG) {
                Interpreter.debug("trying class: " + this.evalName);
            }
            clas = null;
            String className = null;
            for (i = 1; i <= Name.countParts(this.evalName) && (clas = this.namespace.getClass(className = Name.prefix(this.evalName, i))) == null; ++i) {
            }
            if (clas != null) {
                return this.completeRound(className, Name.suffix(this.evalName, Name.countParts(this.evalName) - i), new ClassIdentifier(clas));
            }
            if (Interpreter.DEBUG) {
                Interpreter.debug("not a class, trying var prefix " + this.evalName);
            }
        }
        if ((this.evalBaseObject == null || this.evalBaseObject instanceof This) && !forceClass && autoAllocateThis) {
            NameSpace targetNameSpace = this.evalBaseObject == null ? this.namespace : ((This)this.evalBaseObject).namespace;
            This obj3 = new NameSpace(targetNameSpace, "auto: " + varName).getThis(interpreter);
            targetNameSpace.setVariable(varName, obj3, false);
            return this.completeRound(varName, Name.suffix(this.evalName), obj3);
        }
        if (this.evalBaseObject == null) {
            if (!Name.isCompound(this.evalName)) {
                return this.completeRound(this.evalName, FINISHED, Primitive.VOID);
            }
            throw new UtilEvalError("Class or variable not found: " + this.evalName);
        }
        if (this.evalBaseObject == Primitive.NULL) {
            throw new UtilTargetError(new NullPointerException("Null Pointer while evaluating: " + this.value));
        }
        if (this.evalBaseObject == Primitive.VOID) {
            throw new UtilEvalError("Undefined variable or class name while evaluating: " + this.value);
        }
        if (this.evalBaseObject instanceof Primitive) {
            throw new UtilEvalError("Can't treat primitive like an object. Error while evaluating: " + this.value);
        }
        if (this.evalBaseObject instanceof ClassIdentifier) {
            String iclass;
            Class c;
            Object obj4;
            String field;
            block28: {
                clas = ((ClassIdentifier)this.evalBaseObject).getTargetClass();
                field = Name.prefix(this.evalName, 1);
                if (field.equals("this")) {
                    for (NameSpace ns = this.namespace; ns != null; ns = ns.getParent()) {
                        if (ns.classInstance == null || ns.classInstance.getClass() != clas) continue;
                        return this.completeRound(field, Name.suffix(this.evalName), ns.classInstance);
                    }
                    throw new UtilEvalError("Can't find enclosing 'this' instance of class: " + clas);
                }
                obj4 = null;
                try {
                    if (Interpreter.DEBUG) {
                        Interpreter.debug("Name call to getStaticFieldValue, class: " + clas + ", field:" + field);
                    }
                    obj4 = Reflect.getStaticFieldValue(clas, field);
                } catch (ReflectError e) {
                    if (!Interpreter.DEBUG) break block28;
                    Interpreter.debug("field reflect error: " + e);
                }
            }
            if (obj4 == null && (c = this.namespace.getClass(iclass = clas.getName() + "$" + field)) != null) {
                obj4 = new ClassIdentifier(c);
            }
            if (obj4 == null) {
                throw new UtilEvalError("No static field or inner class: " + field + " of " + clas);
            }
            return this.completeRound(field, Name.suffix(this.evalName), obj4);
        }
        if (forceClass) {
            throw new UtilEvalError(this.value + " does not resolve to a class name.");
        }
        String field = Name.prefix(this.evalName, 1);
        if (field.equals("length") && this.evalBaseObject.getClass().isArray()) {
            Primitive obj5 = new Primitive(Array.getLength(this.evalBaseObject));
            return this.completeRound(field, Name.suffix(this.evalName), obj5);
        }
        try {
            Object obj6 = Reflect.getObjectFieldValue(this.evalBaseObject, field);
            return this.completeRound(field, Name.suffix(this.evalName), obj6);
        } catch (ReflectError e) {
            throw new UtilEvalError("Cannot access field: " + field + ", on object: " + this.evalBaseObject);
        }
    }

    Object resolveThisFieldReference(CallStack callstack, NameSpace thisNameSpace, Interpreter interpreter, String varName, boolean specialFieldsVisible) throws UtilEvalError {
        if (varName.equals("this")) {
            if (specialFieldsVisible) {
                throw new UtilEvalError("Redundant to call .this on This type");
            }
            This ths = thisNameSpace.getThis(interpreter);
            thisNameSpace = ths.getNameSpace();
            Object result = ths;
            NameSpace classNameSpace = Name.getClassNameSpace(thisNameSpace);
            if (classNameSpace != null) {
                result = Name.isCompound(this.evalName) ? classNameSpace.getThis(interpreter) : classNameSpace.getClassInstance();
            }
            return result;
        }
        if (varName.equals("super")) {
            This ths = thisNameSpace.getSuper(interpreter);
            if ((thisNameSpace = ths.getNameSpace()).getParent() != null && thisNameSpace.getParent().isClass) {
                ths = thisNameSpace.getParent().getThis(interpreter);
            }
            return ths;
        }
        Object obj = null;
        if (varName.equals("global")) {
            obj = thisNameSpace.getGlobal(interpreter);
        }
        if (obj == null && specialFieldsVisible) {
            if (varName.equals("namespace")) {
                obj = thisNameSpace;
            } else if (varName.equals("variables")) {
                obj = thisNameSpace.getVariableNames();
            } else if (varName.equals("methods")) {
                obj = thisNameSpace.getMethodNames();
            } else if (varName.equals("interpreter")) {
                if (this.lastEvalName.equals("this")) {
                    obj = interpreter;
                } else {
                    throw new UtilEvalError("Can only call .interpreter on literal 'this'");
                }
            }
        }
        if (obj == null && specialFieldsVisible && varName.equals("caller")) {
            if (this.lastEvalName.equals("this") || this.lastEvalName.equals("caller")) {
                if (callstack == null) {
                    throw new InterpreterError("no callstack");
                }
            } else {
                throw new UtilEvalError("Can only call .caller on literal 'this' or literal '.caller'");
            }
            obj = callstack.get(++this.callstackDepth).getThis(interpreter);
            return obj;
        }
        if (obj == null && specialFieldsVisible && varName.equals("callstack")) {
            if (this.lastEvalName.equals("this")) {
                if (callstack == null) {
                    throw new InterpreterError("no callstack");
                }
                obj = callstack;
            } else {
                throw new UtilEvalError("Can only call .callstack on literal 'this'");
            }
        }
        if (obj == null) {
            obj = thisNameSpace.getVariable(varName);
        }
        if (obj == null) {
            throw new InterpreterError("null this field ref:" + varName);
        }
        return obj;
    }

    static NameSpace getClassNameSpace(NameSpace thisNameSpace) {
        if (thisNameSpace.isClass) {
            return thisNameSpace;
        }
        if (thisNameSpace.isMethod && thisNameSpace.getParent() != null && thisNameSpace.getParent().isClass) {
            return thisNameSpace.getParent();
        }
        return null;
    }

    public synchronized Class toClass() throws ClassNotFoundException, UtilEvalError {
        if (this.asClass != null) {
            return this.asClass;
        }
        this.reset();
        if (this.evalName.equals("var")) {
            this.asClass = null;
            return null;
        }
        Class clas = this.namespace.getClass(this.evalName);
        if (clas == null) {
            Object obj = null;
            try {
                obj = this.toObject(null, null, true);
            } catch (UtilEvalError utilEvalError) {
                // empty catch block
            }
            if (obj instanceof ClassIdentifier) {
                clas = ((ClassIdentifier)obj).getTargetClass();
            }
        }
        if (clas == null) {
            throw new ClassNotFoundException("Class: " + this.value + " not found in namespace");
        }
        this.asClass = clas;
        return this.asClass;
    }

    public synchronized LHS toLHS(CallStack callstack, Interpreter interpreter) throws UtilEvalError {
        this.reset();
        if (!Name.isCompound(this.evalName)) {
            if (this.evalName.equals("this")) {
                throw new UtilEvalError("Can't assign to 'this'.");
            }
            LHS lhs = new LHS(this.namespace, this.evalName, false);
            return lhs;
        }
        Object obj = null;
        try {
            while (this.evalName != null && Name.isCompound(this.evalName)) {
                obj = this.consumeNextObjectField(callstack, interpreter, false, true);
            }
        } catch (UtilEvalError e) {
            throw new UtilEvalError("LHS evaluation: " + e.getMessage());
        }
        if (this.evalName == null && obj instanceof ClassIdentifier) {
            throw new UtilEvalError("Can't assign to class: " + this.value);
        }
        if (obj == null) {
            throw new UtilEvalError("Error in LHS: " + this.value);
        }
        if (obj instanceof This) {
            if (this.evalName.equals("namespace") || this.evalName.equals("variables") || this.evalName.equals("methods") || this.evalName.equals("caller")) {
                throw new UtilEvalError("Can't assign to special variable: " + this.evalName);
            }
            Interpreter.debug("found This reference evaluating LHS");
            boolean localVar = !this.lastEvalName.equals("super");
            return new LHS(((This)obj).namespace, this.evalName, localVar);
        }
        if (this.evalName != null) {
            try {
                if (obj instanceof ClassIdentifier) {
                    Class clas = ((ClassIdentifier)obj).getTargetClass();
                    LHS lhs = Reflect.getLHSStaticField(clas, this.evalName);
                    return lhs;
                }
                LHS lhs = Reflect.getLHSObjectField(obj, this.evalName);
                return lhs;
            } catch (ReflectError e) {
                throw new UtilEvalError("Field access: " + e);
            }
        }
        throw new InterpreterError("Internal error in lhs...");
    }

    public Object invokeMethod(Interpreter interpreter, Object[] args2, CallStack callstack, SimpleNode callerInfo) throws UtilEvalError, EvalError, ReflectError, InvocationTargetException {
        Class clas;
        This ths;
        NameSpace thisNameSpace;
        NameSpace classNameSpace;
        String methodName = Name.suffix(this.value, 1);
        BshClassManager bcm = interpreter.getClassManager();
        NameSpace namespace = callstack.top();
        if (this.classOfStaticMethod != null) {
            return Reflect.invokeStaticMethod(bcm, this.classOfStaticMethod, methodName, args2);
        }
        if (!Name.isCompound(this.value)) {
            return this.invokeLocalMethod(interpreter, args2, callstack, callerInfo);
        }
        String prefix = Name.prefix(this.value);
        if (prefix.equals("super") && Name.countParts(this.value) == 2 && (classNameSpace = Name.getClassNameSpace(thisNameSpace = (ths = namespace.getThis(interpreter)).getNameSpace())) != null) {
            Object instance = classNameSpace.getClassInstance();
            return ClassGenerator.getClassGenerator().invokeSuperclassMethod(bcm, instance, methodName, args2);
        }
        Name targetName = namespace.getNameResolver(prefix);
        Object obj = targetName.toObject(callstack, interpreter);
        if (obj == Primitive.VOID) {
            throw new UtilEvalError("Attempt to resolve method: " + methodName + "() on undefined variable or class name: " + targetName);
        }
        if (!(obj instanceof ClassIdentifier)) {
            if (obj instanceof Primitive) {
                if (obj == Primitive.NULL) {
                    throw new UtilTargetError(new NullPointerException("Null Pointer in Method Invocation"));
                }
                if (Interpreter.DEBUG) {
                    Interpreter.debug("Attempt to access method on primitive... allowing bsh.Primitive to peek through for debugging");
                }
            }
            return Reflect.invokeObjectMethod(obj, methodName, args2, interpreter, callstack, callerInfo);
        }
        if (Interpreter.DEBUG) {
            Interpreter.debug("invokeMethod: trying static - " + targetName);
        }
        this.classOfStaticMethod = clas = ((ClassIdentifier)obj).getTargetClass();
        if (clas != null) {
            return Reflect.invokeStaticMethod(bcm, clas, methodName, args2);
        }
        throw new UtilEvalError("invokeMethod: unknown target: " + targetName);
    }

    private Object invokeLocalMethod(Interpreter interpreter, Object[] args2, CallStack callstack, SimpleNode callerInfo) throws EvalError {
        Object commandObject;
        if (Interpreter.DEBUG) {
            Interpreter.debug("invokeLocalMethod: " + this.value);
        }
        if (interpreter == null) {
            throw new InterpreterError("invokeLocalMethod: interpreter = null");
        }
        String commandName = this.value;
        Class[] argTypes = Types.getTypes(args2);
        BshMethod meth = null;
        try {
            meth = this.namespace.getMethod(commandName, argTypes);
        } catch (UtilEvalError e) {
            throw e.toEvalError("Local method invocation", callerInfo, callstack);
        }
        if (meth != null) {
            return meth.invoke(args2, interpreter, callstack, callerInfo);
        }
        BshClassManager bcm = interpreter.getClassManager();
        try {
            commandObject = this.namespace.getCommand(commandName, argTypes, interpreter);
        } catch (UtilEvalError e) {
            throw e.toEvalError("Error loading command: ", callerInfo, callstack);
        }
        if (commandObject == null) {
            BshMethod invokeMethod = null;
            try {
                invokeMethod = this.namespace.getMethod("invoke", new Class[]{null, null});
            } catch (UtilEvalError e) {
                throw e.toEvalError("Local method invocation", callerInfo, callstack);
            }
            if (invokeMethod != null) {
                return invokeMethod.invoke(new Object[]{commandName, args2}, interpreter, callstack, callerInfo);
            }
            throw new EvalError("Command not found: " + StringUtil.methodString(commandName, argTypes), callerInfo, callstack);
        }
        if (commandObject instanceof BshMethod) {
            return ((BshMethod)commandObject).invoke(args2, interpreter, callstack, callerInfo);
        }
        if (commandObject instanceof Class) {
            try {
                return Reflect.invokeCompiledCommand((Class)commandObject, args2, interpreter, callstack);
            } catch (UtilEvalError e) {
                throw e.toEvalError("Error invoking compiled command: ", callerInfo, callstack);
            }
        }
        throw new InterpreterError("invalid command type");
    }

    public static boolean isCompound(String value) {
        return value.indexOf(46) != -1;
    }

    static int countParts(String value) {
        if (value == null) {
            return 0;
        }
        int count = 0;
        int index = -1;
        while ((index = value.indexOf(46, index + 1)) != -1) {
            ++count;
        }
        return count + 1;
    }

    static String prefix(String value) {
        if (!Name.isCompound(value)) {
            return null;
        }
        return Name.prefix(value, Name.countParts(value) - 1);
    }

    static String prefix(String value, int parts) {
        if (parts < 1) {
            return null;
        }
        int count = 0;
        int index = -1;
        while ((index = value.indexOf(46, index + 1)) != -1 && ++count < parts) {
        }
        return index == -1 ? value : value.substring(0, index);
    }

    static String suffix(String name) {
        if (!Name.isCompound(name)) {
            return null;
        }
        return Name.suffix(name, Name.countParts(name) - 1);
    }

    public static String suffix(String value, int parts) {
        if (parts < 1) {
            return null;
        }
        int count = 0;
        int index = value.length() + 1;
        while ((index = value.lastIndexOf(46, index - 1)) != -1 && ++count < parts) {
        }
        return index == -1 ? value : value.substring(index + 1);
    }

    public String toString() {
        return this.value;
    }
}

