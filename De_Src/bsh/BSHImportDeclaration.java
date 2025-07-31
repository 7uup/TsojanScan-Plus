/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package bsh;

import bsh.BSHAmbiguousName;
import bsh.CallStack;
import bsh.EvalError;
import bsh.Interpreter;
import bsh.NameSpace;
import bsh.Primitive;
import bsh.SimpleNode;
import bsh.UtilEvalError;

class BSHImportDeclaration
extends SimpleNode {
    public boolean importPackage;
    public boolean staticImport;
    public boolean superImport;

    BSHImportDeclaration(int id) {
        super(id);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public Object eval(CallStack callstack, Interpreter interpreter) throws EvalError {
        NameSpace namespace = callstack.top();
        if (this.superImport) {
            try {
                namespace.doSuperImport();
                return Primitive.VOID;
            } catch (UtilEvalError e) {
                throw e.toEvalError(this, callstack);
            }
        } else if (this.staticImport) {
            if (!this.importPackage) throw new EvalError("static field imports not supported yet", this, callstack);
            Class clas = ((BSHAmbiguousName)this.jjtGetChild(0)).toClass(callstack, interpreter);
            namespace.importStatic(clas);
            return Primitive.VOID;
        } else {
            String name = ((BSHAmbiguousName)this.jjtGetChild((int)0)).text;
            if (this.importPackage) {
                namespace.importPackage(name);
                return Primitive.VOID;
            } else {
                namespace.importClass(name);
            }
        }
        return Primitive.VOID;
    }
}

