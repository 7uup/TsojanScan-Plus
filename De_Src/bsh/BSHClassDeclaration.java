/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package bsh;

import bsh.BSHAmbiguousName;
import bsh.BSHBlock;
import bsh.CallStack;
import bsh.ClassGenerator;
import bsh.EvalError;
import bsh.Interpreter;
import bsh.Modifiers;
import bsh.SimpleNode;
import bsh.UtilEvalError;

class BSHClassDeclaration
extends SimpleNode {
    static final String CLASSINITNAME = "_bshClassInit";
    String name;
    Modifiers modifiers;
    int numInterfaces;
    boolean extend;
    boolean isInterface;

    BSHClassDeclaration(int id) {
        super(id);
    }

    public Object eval(CallStack callstack, Interpreter interpreter) throws EvalError {
        int child = 0;
        Class superClass = null;
        if (this.extend) {
            BSHAmbiguousName superNode = (BSHAmbiguousName)this.jjtGetChild(child++);
            superClass = superNode.toClass(callstack, interpreter);
        }
        Class[] interfaces = new Class[this.numInterfaces];
        for (int i = 0; i < this.numInterfaces; ++i) {
            BSHAmbiguousName node = (BSHAmbiguousName)this.jjtGetChild(child++);
            interfaces[i] = node.toClass(callstack, interpreter);
            if (interfaces[i].isInterface()) continue;
            throw new EvalError("Type: " + node.text + " is not an interface!", this, callstack);
        }
        BSHBlock block = child < this.jjtGetNumChildren() ? (BSHBlock)this.jjtGetChild(child) : new BSHBlock(25);
        try {
            return ClassGenerator.getClassGenerator().generateClass(this.name, this.modifiers, interfaces, superClass, block, this.isInterface, callstack, interpreter);
        } catch (UtilEvalError e) {
            throw e.toEvalError(this, callstack);
        }
    }

    public String toString() {
        return "ClassDeclaration: " + this.name;
    }
}

