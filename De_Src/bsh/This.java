/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package bsh;

import bsh.BshMethod;
import bsh.CallStack;
import bsh.Capabilities;
import bsh.EvalError;
import bsh.Interpreter;
import bsh.InterpreterError;
import bsh.NameSpace;
import bsh.Primitive;
import bsh.Reflect;
import bsh.SimpleNode;
import bsh.StringUtil;
import bsh.Types;
import bsh.UtilEvalError;
import java.io.Serializable;

public class This
implements Serializable,
Runnable {
    NameSpace namespace;
    transient Interpreter declaringInterpreter;

    static This getThis(NameSpace namespace, Interpreter declaringInterpreter) {
        try {
            Class<?> c;
            if (Capabilities.canGenerateInterfaces()) {
                c = Class.forName("bsh.XThis");
            } else if (Capabilities.haveSwing()) {
                c = Class.forName("bsh.JThis");
            } else {
                return new This(namespace, declaringInterpreter);
            }
            return (This)Reflect.constructObject(c, new Object[]{namespace, declaringInterpreter});
        } catch (Exception e) {
            throw new InterpreterError("internal error 1 in This: " + e);
        }
    }

    public Object getInterface(Class clas) throws UtilEvalError {
        if (clas.isInstance(this)) {
            return this;
        }
        throw new UtilEvalError("Dynamic proxy mechanism not available. Cannot construct interface type: " + clas);
    }

    public Object getInterface(Class[] ca) throws UtilEvalError {
        for (int i = 0; i < ca.length; ++i) {
            if (ca[i].isInstance(this)) continue;
            throw new UtilEvalError("Dynamic proxy mechanism not available. Cannot construct interface type: " + ca[i]);
        }
        return this;
    }

    protected This(NameSpace namespace, Interpreter declaringInterpreter) {
        this.namespace = namespace;
        this.declaringInterpreter = declaringInterpreter;
    }

    public NameSpace getNameSpace() {
        return this.namespace;
    }

    public String toString() {
        return "'this' reference to Bsh object: " + this.namespace;
    }

    public void run() {
        try {
            this.invokeMethod("run", new Object[0]);
        } catch (EvalError e) {
            this.declaringInterpreter.error("Exception in runnable:" + e);
        }
    }

    public Object invokeMethod(String name, Object[] args2) throws EvalError {
        return this.invokeMethod(name, args2, null, null, null, false);
    }

    public Object invokeMethod(String methodName, Object[] args2, Interpreter interpreter, CallStack callstack, SimpleNode callerInfo, boolean declaredOnly) throws EvalError {
        if (args2 != null) {
            Object[] oa = new Object[args2.length];
            for (int i = 0; i < args2.length; ++i) {
                oa[i] = args2[i] == null ? Primitive.NULL : args2[i];
            }
            args2 = oa;
        }
        if (interpreter == null) {
            interpreter = this.declaringInterpreter;
        }
        if (callstack == null) {
            callstack = new CallStack(this.namespace);
        }
        if (callerInfo == null) {
            callerInfo = SimpleNode.JAVACODE;
        }
        Class[] types = Types.getTypes(args2);
        BshMethod bshMethod = null;
        try {
            bshMethod = this.namespace.getMethod(methodName, types, declaredOnly);
        } catch (UtilEvalError e) {
            // empty catch block
        }
        if (bshMethod != null) {
            return bshMethod.invoke(args2, interpreter, callstack, callerInfo);
        }
        if (methodName.equals("toString")) {
            return this.toString();
        }
        if (methodName.equals("hashCode")) {
            return new Integer(this.hashCode());
        }
        if (methodName.equals("equals")) {
            Object obj = args2[0];
            return new Boolean(this == obj);
        }
        try {
            bshMethod = this.namespace.getMethod("invoke", new Class[]{null, null});
        } catch (UtilEvalError e) {
            // empty catch block
        }
        if (bshMethod != null) {
            return bshMethod.invoke(new Object[]{methodName, args2}, interpreter, callstack, callerInfo);
        }
        throw new EvalError("Method " + StringUtil.methodString(methodName, types) + " not found in bsh scripted object: " + this.namespace.getName(), callerInfo, callstack);
    }

    public static void bind(This ths, NameSpace namespace, Interpreter declaringInterpreter) {
        ths.namespace.setParent(namespace);
        ths.declaringInterpreter = declaringInterpreter;
    }

    static boolean isExposedThisMethod(String name) {
        return name.equals("getClass") || name.equals("invokeMethod") || name.equals("getInterface") || name.equals("wait") || name.equals("notify") || name.equals("notifyAll");
    }
}

