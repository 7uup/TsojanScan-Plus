/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package bsh;

import bsh.BshMethod;
import bsh.CallStack;
import bsh.EvalError;
import bsh.Interpreter;
import bsh.NameSpace;
import bsh.Primitive;
import bsh.TargetError;
import bsh.This;
import bsh.UtilEvalError;
import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Hashtable;

public class XThis
extends This {
    Hashtable interfaces;
    InvocationHandler invocationHandler = new Handler();

    public XThis(NameSpace namespace, Interpreter declaringInterp) {
        super(namespace, declaringInterp);
    }

    public String toString() {
        return "'this' reference (XThis) to Bsh object: " + this.namespace;
    }

    public Object getInterface(Class clas) {
        return this.getInterface(new Class[]{clas});
    }

    public Object getInterface(Class[] ca) {
        if (this.interfaces == null) {
            this.interfaces = new Hashtable();
        }
        int hash = 21;
        for (int i = 0; i < ca.length; ++i) {
            hash *= ca[i].hashCode() + 3;
        }
        Integer hashKey = new Integer(hash);
        Object interf = this.interfaces.get(hashKey);
        if (interf == null) {
            ClassLoader classLoader = ca[0].getClassLoader();
            interf = Proxy.newProxyInstance(classLoader, ca, this.invocationHandler);
            this.interfaces.put(hashKey, interf);
        }
        return interf;
    }

    class Handler
    implements InvocationHandler,
    Serializable {
        Handler() {
        }

        public Object invoke(Object proxy, Method method, Object[] args2) throws Throwable {
            try {
                return this.invokeImpl(proxy, method, args2);
            } catch (TargetError te) {
                throw te.getTarget();
            } catch (EvalError ee) {
                if (Interpreter.DEBUG) {
                    Interpreter.debug("EvalError in scripted interface: " + XThis.this.toString() + ": " + ee);
                }
                throw ee;
            }
        }

        public Object invokeImpl(Object proxy, Method method, Object[] args2) throws EvalError {
            String methodName = method.getName();
            CallStack callstack = new CallStack(XThis.this.namespace);
            BshMethod equalsMethod = null;
            try {
                equalsMethod = XThis.this.namespace.getMethod("equals", new Class[]{Object.class});
            } catch (UtilEvalError e) {
                // empty catch block
            }
            if (methodName.equals("equals") && equalsMethod == null) {
                Object obj = args2[0];
                return new Boolean(proxy == obj);
            }
            BshMethod toStringMethod = null;
            try {
                toStringMethod = XThis.this.namespace.getMethod("toString", new Class[0]);
            } catch (UtilEvalError e) {
                // empty catch block
            }
            if (methodName.equals("toString") && toStringMethod == null) {
                Class<?>[] ints = proxy.getClass().getInterfaces();
                StringBuffer sb = new StringBuffer(XThis.this.toString() + "\nimplements:");
                for (int i = 0; i < ints.length; ++i) {
                    sb.append(" " + ints[i].getName() + (ints.length > 1 ? "," : ""));
                }
                return sb.toString();
            }
            Class[] paramTypes = method.getParameterTypes();
            return Primitive.unwrap(XThis.this.invokeMethod(methodName, Primitive.wrap(args2, paramTypes)));
        }
    }
}

