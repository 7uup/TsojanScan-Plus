/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package bsh;

import bsh.BSHArrayInitializer;
import bsh.CallStack;
import bsh.EvalError;
import bsh.Interpreter;
import bsh.Primitive;
import bsh.Reflect;
import bsh.SimpleNode;
import java.lang.reflect.Array;

class BSHArrayDimensions
extends SimpleNode {
    public Class baseType;
    public int numDefinedDims;
    public int numUndefinedDims;
    public int[] definedDimensions;

    BSHArrayDimensions(int id) {
        super(id);
    }

    public void addDefinedDimension() {
        ++this.numDefinedDims;
    }

    public void addUndefinedDimension() {
        ++this.numUndefinedDims;
    }

    public Object eval(Class type, CallStack callstack, Interpreter interpreter) throws EvalError {
        if (Interpreter.DEBUG) {
            Interpreter.debug("array base type = " + type);
        }
        this.baseType = type;
        return this.eval(callstack, interpreter);
    }

    public Object eval(CallStack callstack, Interpreter interpreter) throws EvalError {
        SimpleNode child = (SimpleNode)this.jjtGetChild(0);
        if (child instanceof BSHArrayInitializer) {
            if (this.baseType == null) {
                throw new EvalError("Internal Array Eval err:  unknown base type", this, callstack);
            }
            Object initValue = ((BSHArrayInitializer)child).eval(this.baseType, this.numUndefinedDims, callstack, interpreter);
            Class<?> arrayClass = initValue.getClass();
            int actualDimensions = Reflect.getArrayDimensions(arrayClass);
            this.definedDimensions = new int[actualDimensions];
            if (this.definedDimensions.length != this.numUndefinedDims) {
                throw new EvalError("Incompatible initializer. Allocation calls for a " + this.numUndefinedDims + " dimensional array, but initializer is a " + actualDimensions + " dimensional array", this, callstack);
            }
            Object arraySlice = initValue;
            for (int i = 0; i < this.definedDimensions.length; ++i) {
                this.definedDimensions[i] = Array.getLength(arraySlice);
                if (this.definedDimensions[i] <= 0) continue;
                arraySlice = Array.get(arraySlice, 0);
            }
            return initValue;
        }
        this.definedDimensions = new int[this.numDefinedDims];
        for (int i = 0; i < this.numDefinedDims; ++i) {
            try {
                Object length = ((SimpleNode)this.jjtGetChild(i)).eval(callstack, interpreter);
                this.definedDimensions[i] = ((Primitive)length).intValue();
                continue;
            } catch (Exception e) {
                throw new EvalError("Array index: " + i + " does not evaluate to an integer", this, callstack);
            }
        }
        return Primitive.VOID;
    }
}

