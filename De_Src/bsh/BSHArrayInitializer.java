/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package bsh;

import bsh.CallStack;
import bsh.EvalError;
import bsh.Interpreter;
import bsh.Primitive;
import bsh.Reflect;
import bsh.SimpleNode;
import bsh.Types;
import bsh.UtilEvalError;
import java.lang.reflect.Array;

class BSHArrayInitializer
extends SimpleNode {
    BSHArrayInitializer(int id) {
        super(id);
    }

    public Object eval(CallStack callstack, Interpreter interpreter) throws EvalError {
        throw new EvalError("Array initializer has no base type.", this, callstack);
    }

    public Object eval(Class baseType, int dimensions, CallStack callstack, Interpreter interpreter) throws EvalError {
        int numInitializers = this.jjtGetNumChildren();
        int[] dima = new int[dimensions];
        dima[0] = numInitializers;
        Object initializers = Array.newInstance(baseType, dima);
        for (int i = 0; i < numInitializers; ++i) {
            Object currentInitializer;
            SimpleNode node = (SimpleNode)this.jjtGetChild(i);
            if (node instanceof BSHArrayInitializer) {
                if (dimensions < 2) {
                    throw new EvalError("Invalid Location for Intializer, position: " + i, this, callstack);
                }
                currentInitializer = ((BSHArrayInitializer)node).eval(baseType, dimensions - 1, callstack, interpreter);
            } else {
                currentInitializer = node.eval(callstack, interpreter);
            }
            if (currentInitializer == Primitive.VOID) {
                throw new EvalError("Void in array initializer, position" + i, this, callstack);
            }
            Object value = currentInitializer;
            if (dimensions == 1) {
                try {
                    value = Types.castObject(currentInitializer, baseType, 0);
                } catch (UtilEvalError e) {
                    throw e.toEvalError("Error in array initializer", this, callstack);
                }
                value = Primitive.unwrap(value);
            }
            try {
                Array.set(initializers, i, value);
                continue;
            } catch (IllegalArgumentException e) {
                Interpreter.debug("illegal arg" + e);
                this.throwTypeError(baseType, currentInitializer, i, callstack);
                continue;
            } catch (ArrayStoreException e) {
                Interpreter.debug("arraystore" + e);
                this.throwTypeError(baseType, currentInitializer, i, callstack);
            }
        }
        return initializers;
    }

    private void throwTypeError(Class baseType, Object initializer, int argNum, CallStack callstack) throws EvalError {
        String rhsType = initializer instanceof Primitive ? ((Primitive)initializer).getType().getName() : Reflect.normalizeClassName(initializer.getClass());
        throw new EvalError("Incompatible type: " + rhsType + " in initializer of array type: " + baseType + " at position: " + argNum, this, callstack);
    }
}

