/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package bsh;

import bsh.BSHBlock;
import bsh.BSHMethodDeclaration;
import bsh.CallStack;
import bsh.EvalError;
import bsh.Interpreter;
import bsh.InterpreterError;
import bsh.Modifiers;
import bsh.NameSpace;
import bsh.Primitive;
import bsh.Reflect;
import bsh.ReflectError;
import bsh.ReturnControl;
import bsh.SimpleNode;
import bsh.StringUtil;
import bsh.TargetError;
import bsh.Types;
import bsh.UtilEvalError;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BshMethod
implements Serializable {
    NameSpace declaringNameSpace;
    Modifiers modifiers;
    private String name;
    private Class creturnType;
    private String[] paramNames;
    private int numArgs;
    private Class[] cparamTypes;
    BSHBlock methodBody;
    private Method javaMethod;
    private Object javaObject;

    BshMethod(BSHMethodDeclaration method, NameSpace declaringNameSpace, Modifiers modifiers) {
        this(method.name, method.returnType, method.paramsNode.getParamNames(), method.paramsNode.paramTypes, method.blockNode, declaringNameSpace, modifiers);
    }

    BshMethod(String name, Class returnType, String[] paramNames, Class[] paramTypes, BSHBlock methodBody, NameSpace declaringNameSpace, Modifiers modifiers) {
        this.name = name;
        this.creturnType = returnType;
        this.paramNames = paramNames;
        if (paramNames != null) {
            this.numArgs = paramNames.length;
        }
        this.cparamTypes = paramTypes;
        this.methodBody = methodBody;
        this.declaringNameSpace = declaringNameSpace;
        this.modifiers = modifiers;
    }

    BshMethod(Method method, Object object) {
        this(method.getName(), method.getReturnType(), null, method.getParameterTypes(), null, null, null);
        this.javaMethod = method;
        this.javaObject = object;
    }

    public Class[] getParameterTypes() {
        return this.cparamTypes;
    }

    public String[] getParameterNames() {
        return this.paramNames;
    }

    public Class getReturnType() {
        return this.creturnType;
    }

    public Modifiers getModifiers() {
        return this.modifiers;
    }

    public String getName() {
        return this.name;
    }

    public Object invoke(Object[] argValues, Interpreter interpreter) throws EvalError {
        return this.invoke(argValues, interpreter, null, null, false);
    }

    public Object invoke(Object[] argValues, Interpreter interpreter, CallStack callstack, SimpleNode callerInfo) throws EvalError {
        return this.invoke(argValues, interpreter, callstack, callerInfo, false);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    Object invoke(Object[] argValues, Interpreter interpreter, CallStack callstack, SimpleNode callerInfo, boolean overrideNameSpace) throws EvalError {
        if (argValues != null) {
            for (int i = 0; i < argValues.length; ++i) {
                if (argValues[i] != null) continue;
                throw new Error("HERE!");
            }
        }
        if (this.javaMethod != null) {
            try {
                return Reflect.invokeMethod(this.javaMethod, this.javaObject, argValues);
            } catch (ReflectError e) {
                throw new EvalError("Error invoking Java method: " + e, callerInfo, callstack);
            } catch (InvocationTargetException e2) {
                throw new TargetError("Exception invoking imported object method.", e2, callerInfo, callstack, true);
            }
        }
        if (this.modifiers != null && this.modifiers.hasModifier("synchronized")) {
            Object lock;
            if (this.declaringNameSpace.isClass) {
                try {
                    lock = this.declaringNameSpace.getClassInstance();
                } catch (UtilEvalError e) {
                    throw new InterpreterError("Can't get class instance for synchronized method.");
                }
            } else {
                lock = this.declaringNameSpace.getThis(interpreter);
            }
            Object object = lock;
            synchronized (object) {
                return this.invokeImpl(argValues, interpreter, callstack, callerInfo, overrideNameSpace);
            }
        }
        return this.invokeImpl(argValues, interpreter, callstack, callerInfo, overrideNameSpace);
    }

    private Object invokeImpl(Object[] argValues, Interpreter interpreter, CallStack callstack, SimpleNode callerInfo, boolean overrideNameSpace) throws EvalError {
        NameSpace localNameSpace;
        Class returnType = this.getReturnType();
        Class[] paramTypes = this.getParameterTypes();
        if (callstack == null) {
            callstack = new CallStack(this.declaringNameSpace);
        }
        if (argValues == null) {
            argValues = new Object[]{};
        }
        if (argValues.length != this.numArgs) {
            throw new EvalError("Wrong number of arguments for local method: " + this.name, callerInfo, callstack);
        }
        if (overrideNameSpace) {
            localNameSpace = callstack.top();
        } else {
            localNameSpace = new NameSpace(this.declaringNameSpace, this.name);
            localNameSpace.isMethod = true;
        }
        localNameSpace.setNode(callerInfo);
        for (int i = 0; i < this.numArgs; ++i) {
            if (paramTypes[i] != null) {
                try {
                    argValues[i] = Types.castObject(argValues[i], paramTypes[i], 1);
                } catch (UtilEvalError e) {
                    throw new EvalError("Invalid argument: `" + this.paramNames[i] + "'" + " for method: " + this.name + " : " + e.getMessage(), callerInfo, callstack);
                }
                try {
                    localNameSpace.setTypedVariable(this.paramNames[i], paramTypes[i], argValues[i], null);
                    continue;
                } catch (UtilEvalError e2) {
                    throw e2.toEvalError("Typed method parameter assignment", callerInfo, callstack);
                }
            }
            if (argValues[i] == Primitive.VOID) {
                throw new EvalError("Undefined variable or class name, parameter: " + this.paramNames[i] + " to method: " + this.name, callerInfo, callstack);
            }
            try {
                localNameSpace.setLocalVariable(this.paramNames[i], argValues[i], interpreter.getStrictJava());
                continue;
            } catch (UtilEvalError e3) {
                throw e3.toEvalError(callerInfo, callstack);
            }
        }
        if (!overrideNameSpace) {
            callstack.push(localNameSpace);
        }
        Object ret = this.methodBody.eval(callstack, interpreter, true);
        CallStack returnStack = callstack.copy();
        if (!overrideNameSpace) {
            callstack.pop();
        }
        ReturnControl retControl = null;
        if (ret instanceof ReturnControl) {
            retControl = (ReturnControl)ret;
            if (retControl.kind != 46) {
                throw new EvalError("'continue' or 'break' in method body", retControl.returnPoint, returnStack);
            }
            ret = ((ReturnControl)ret).value;
            if (returnType == Void.TYPE && ret != Primitive.VOID) {
                throw new EvalError("Cannot return value from void method", retControl.returnPoint, returnStack);
            }
        }
        if (returnType != null) {
            if (returnType == Void.TYPE) {
                return Primitive.VOID;
            }
            try {
                ret = Types.castObject(ret, returnType, 1);
            } catch (UtilEvalError e) {
                SimpleNode node = callerInfo;
                if (retControl != null) {
                    node = retControl.returnPoint;
                }
                throw e.toEvalError("Incorrect type returned from method: " + this.name + e.getMessage(), node, callstack);
            }
        }
        return ret;
    }

    public boolean hasModifier(String name) {
        return this.modifiers != null && this.modifiers.hasModifier(name);
    }

    public String toString() {
        return "Scripted Method: " + StringUtil.methodString(this.name, this.getParameterTypes());
    }
}

