/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.apache.bsf.BSFDeclaredBean
 *  org.apache.bsf.BSFException
 *  org.apache.bsf.BSFManager
 *  org.apache.bsf.util.BSFEngineImpl
 */
package bsh.util;

import bsh.EvalError;
import bsh.Interpreter;
import bsh.InterpreterError;
import bsh.Primitive;
import bsh.TargetError;
import bsh.This;
import java.util.Vector;
import org.apache.bsf.BSFDeclaredBean;
import org.apache.bsf.BSFException;
import org.apache.bsf.BSFManager;
import org.apache.bsf.util.BSFEngineImpl;

public class BeanShellBSFEngine
extends BSFEngineImpl {
    Interpreter interpreter;
    boolean installedApplyMethod;
    static final String bsfApplyMethod = "_bsfApply( _bsfNames, _bsfArgs, _bsfText ) {for(i=0;i<_bsfNames.length;i++)this.namespace.setVariable(_bsfNames[i], _bsfArgs[i],false);return this.interpreter.eval(_bsfText, this.namespace);}";

    public void initialize(BSFManager mgr, String lang, Vector declaredBeans) throws BSFException {
        super.initialize(mgr, lang, declaredBeans);
        this.interpreter = new Interpreter();
        try {
            this.interpreter.set("bsf", mgr);
        } catch (EvalError e) {
            throw new BSFException("bsh internal error: " + e.toString());
        }
        for (int i = 0; i < declaredBeans.size(); ++i) {
            BSFDeclaredBean bean = (BSFDeclaredBean)declaredBeans.get(i);
            this.declareBean(bean);
        }
    }

    public void setDebug(boolean debug) {
        Interpreter.DEBUG = debug;
    }

    public Object call(Object object, String name, Object[] args2) throws BSFException {
        if (object == null) {
            try {
                object = this.interpreter.get("global");
            } catch (EvalError e) {
                throw new BSFException("bsh internal error: " + e.toString());
            }
        }
        if (object instanceof This) {
            try {
                Object value = ((This)object).invokeMethod(name, args2);
                return Primitive.unwrap(value);
            } catch (InterpreterError e) {
                throw new BSFException("BeanShell interpreter internal error: " + e);
            } catch (TargetError e2) {
                throw new BSFException("The application script threw an exception: " + e2.getTarget());
            } catch (EvalError e3) {
                throw new BSFException("BeanShell script error: " + e3);
            }
        }
        throw new BSFException("Cannot invoke method: " + name + ". Object: " + object + " is not a BeanShell scripted object.");
    }

    public Object apply(String source2, int lineNo, int columnNo, Object funcBody, Vector namesVec, Vector argsVec) throws BSFException {
        if (namesVec.size() != argsVec.size()) {
            throw new BSFException("number of params/names mismatch");
        }
        if (!(funcBody instanceof String)) {
            throw new BSFException("apply: functino body must be a string");
        }
        Object[] names = new String[namesVec.size()];
        namesVec.copyInto(names);
        Object[] args2 = new Object[argsVec.size()];
        argsVec.copyInto(args2);
        try {
            if (!this.installedApplyMethod) {
                this.interpreter.eval(bsfApplyMethod);
                this.installedApplyMethod = true;
            }
            This global = (This)this.interpreter.get("global");
            Object value = global.invokeMethod("_bsfApply", new Object[]{names, args2, (String)funcBody});
            return Primitive.unwrap(value);
        } catch (InterpreterError e) {
            throw new BSFException("BeanShell interpreter internal error: " + e + this.sourceInfo(source2, lineNo, columnNo));
        } catch (TargetError e2) {
            throw new BSFException("The application script threw an exception: " + e2.getTarget() + this.sourceInfo(source2, lineNo, columnNo));
        } catch (EvalError e3) {
            throw new BSFException("BeanShell script error: " + e3 + this.sourceInfo(source2, lineNo, columnNo));
        }
    }

    public Object eval(String source2, int lineNo, int columnNo, Object expr) throws BSFException {
        if (!(expr instanceof String)) {
            throw new BSFException("BeanShell expression must be a string");
        }
        try {
            return this.interpreter.eval((String)expr);
        } catch (InterpreterError e) {
            throw new BSFException("BeanShell interpreter internal error: " + e + this.sourceInfo(source2, lineNo, columnNo));
        } catch (TargetError e2) {
            throw new BSFException("The application script threw an exception: " + e2.getTarget() + this.sourceInfo(source2, lineNo, columnNo));
        } catch (EvalError e3) {
            throw new BSFException("BeanShell script error: " + e3 + this.sourceInfo(source2, lineNo, columnNo));
        }
    }

    public void exec(String source2, int lineNo, int columnNo, Object script) throws BSFException {
        this.eval(source2, lineNo, columnNo, script);
    }

    public void declareBean(BSFDeclaredBean bean) throws BSFException {
        try {
            this.interpreter.set(bean.name, bean.bean);
        } catch (EvalError e) {
            throw new BSFException("error declaring bean: " + bean.name + " : " + e.toString());
        }
    }

    public void undeclareBean(BSFDeclaredBean bean) throws BSFException {
        try {
            this.interpreter.unset(bean.name);
        } catch (EvalError e) {
            throw new BSFException("bsh internal error: " + e.toString());
        }
    }

    public void terminate() {
    }

    private String sourceInfo(String source2, int lineNo, int columnNo) {
        return " BSF info: " + source2 + " at line: " + lineNo + " column: columnNo";
    }
}

