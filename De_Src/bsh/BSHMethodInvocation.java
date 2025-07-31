/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package bsh;

import bsh.BSHAmbiguousName;
import bsh.BSHArguments;
import bsh.CallStack;
import bsh.EvalError;
import bsh.Interpreter;
import bsh.Name;
import bsh.NameSpace;
import bsh.Primitive;
import bsh.ReflectError;
import bsh.SimpleNode;
import bsh.TargetError;
import bsh.UtilEvalError;
import java.lang.reflect.InvocationTargetException;

class BSHMethodInvocation
extends SimpleNode {
    BSHMethodInvocation(int id) {
        super(id);
    }

    BSHAmbiguousName getNameNode() {
        return (BSHAmbiguousName)this.jjtGetChild(0);
    }

    BSHArguments getArgsNode() {
        return (BSHArguments)this.jjtGetChild(1);
    }

    public Object eval(CallStack callstack, Interpreter interpreter) throws EvalError {
        NameSpace namespace = callstack.top();
        BSHAmbiguousName nameNode = this.getNameNode();
        if (namespace.getParent() != null && namespace.getParent().isClass && (nameNode.text.equals("super") || nameNode.text.equals("this"))) {
            return Primitive.VOID;
        }
        Name name = nameNode.getName(namespace);
        Object[] args2 = this.getArgsNode().getArguments(callstack, interpreter);
        try {
            return name.invokeMethod(interpreter, args2, callstack, this);
        } catch (ReflectError e) {
            throw new EvalError("Error in method invocation: " + e.getMessage(), this, callstack);
        } catch (InvocationTargetException e) {
            String msg = "Method Invocation " + name;
            Throwable te = e.getTargetException();
            boolean isNative = true;
            if (te instanceof EvalError) {
                isNative = te instanceof TargetError ? ((TargetError)te).inNativeCode() : false;
            }
            throw new TargetError(msg, te, this, callstack, isNative);
        } catch (UtilEvalError e) {
            throw e.toEvalError(this, callstack);
        }
    }
}

