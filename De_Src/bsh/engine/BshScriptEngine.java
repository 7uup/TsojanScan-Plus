/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package bsh.engine;

import bsh.EvalError;
import bsh.ExternalNameSpace;
import bsh.Interpreter;
import bsh.InterpreterError;
import bsh.NameSpace;
import bsh.ParseException;
import bsh.TargetError;
import bsh.This;
import bsh.UtilEvalError;
import bsh.engine.BshScriptEngineFactory;
import bsh.engine.ScriptContextEngineView;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;
import javax.script.AbstractScriptEngine;
import javax.script.Bindings;
import javax.script.Compilable;
import javax.script.CompiledScript;
import javax.script.Invocable;
import javax.script.ScriptContext;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptException;
import javax.script.SimpleBindings;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class BshScriptEngine
extends AbstractScriptEngine
implements Compilable,
Invocable {
    static final String engineNameSpaceKey = "org_beanshell_engine_namespace";
    private BshScriptEngineFactory factory;
    private Interpreter interpreter;

    public BshScriptEngine() {
        this((BshScriptEngineFactory)null);
    }

    public BshScriptEngine(BshScriptEngineFactory factory2) {
        this.factory = factory2;
        this.getInterpreter();
    }

    protected Interpreter getInterpreter() {
        if (this.interpreter == null) {
            this.interpreter = new Interpreter();
            this.interpreter.setNameSpace(null);
        }
        return this.interpreter;
    }

    @Override
    public Object eval(String script, ScriptContext scriptContext) throws ScriptException {
        return this.evalSource(script, scriptContext);
    }

    @Override
    public Object eval(Reader reader, ScriptContext scriptContext) throws ScriptException {
        return this.evalSource(reader, scriptContext);
    }

    private Object evalSource(Object source2, ScriptContext scriptContext) throws ScriptException {
        NameSpace contextNameSpace = BshScriptEngine.getEngineNameSpace(scriptContext);
        Interpreter bsh = this.getInterpreter();
        bsh.setNameSpace(contextNameSpace);
        bsh.setOut(new PrintStream(new WriterOutputStream(scriptContext.getWriter())));
        bsh.setErr(new PrintStream(new WriterOutputStream(scriptContext.getErrorWriter())));
        try {
            if (source2 instanceof Reader) {
                return bsh.eval((Reader)source2);
            }
            return bsh.eval((String)source2);
        } catch (ParseException e) {
            throw new ScriptException(e.toString(), e.getErrorSourceFile(), e.getErrorLineNumber());
        } catch (TargetError e) {
            ScriptException se = new ScriptException(e.toString(), e.getErrorSourceFile(), e.getErrorLineNumber());
            se.initCause(e.getTarget());
            throw se;
        } catch (EvalError e) {
            throw new ScriptException(e.toString(), e.getErrorSourceFile(), e.getErrorLineNumber());
        } catch (InterpreterError e) {
            throw new ScriptException(e.toString());
        }
    }

    private static NameSpace getEngineNameSpace(ScriptContext scriptContext) {
        NameSpace ns = (NameSpace)scriptContext.getAttribute(engineNameSpaceKey, 100);
        if (ns == null) {
            ScriptContextEngineView engineView = new ScriptContextEngineView(scriptContext);
            ns = new ExternalNameSpace(null, "javax_script_context", engineView);
            scriptContext.setAttribute(engineNameSpaceKey, ns, 100);
        }
        return ns;
    }

    @Override
    public Bindings createBindings() {
        return new SimpleBindings();
    }

    @Override
    public ScriptEngineFactory getFactory() {
        if (this.factory == null) {
            this.factory = new BshScriptEngineFactory();
        }
        return this.factory;
    }

    @Override
    public CompiledScript compile(String script) throws ScriptException {
        return this.compile(new StringReader(script));
    }

    @Override
    public CompiledScript compile(Reader script) throws ScriptException {
        throw new Error("unimplemented");
    }

    public Object invoke(Object thiz, String name, Object ... args2) throws ScriptException, NoSuchMethodException {
        if (!(thiz instanceof This)) {
            throw new ScriptException("Illegal objec type: " + thiz.getClass());
        }
        This bshObject = (This)thiz;
        try {
            return bshObject.invokeMethod(name, args2);
        } catch (ParseException e) {
            throw new ScriptException(e.toString(), e.getErrorSourceFile(), e.getErrorLineNumber());
        } catch (TargetError e) {
            ScriptException se = new ScriptException(e.toString(), e.getErrorSourceFile(), e.getErrorLineNumber());
            se.initCause(e.getTarget());
            throw se;
        } catch (EvalError e) {
            throw new ScriptException(e.toString(), e.getErrorSourceFile(), e.getErrorLineNumber());
        } catch (InterpreterError e) {
            throw new ScriptException(e.toString());
        }
    }

    public Object invoke(String name, Object ... args2) throws ScriptException, NoSuchMethodException {
        return this.invoke(this.getGlobal(), name, args2);
    }

    @Override
    public <T> T getInterface(Class<T> clasz) {
        try {
            return (T)this.getGlobal().getInterface(clasz);
        } catch (UtilEvalError utilEvalError) {
            utilEvalError.printStackTrace();
            return null;
        }
    }

    @Override
    public <T> T getInterface(Object thiz, Class<T> clasz) {
        if (!(thiz instanceof This)) {
            throw new IllegalArgumentException("invalid object type: " + thiz.getClass());
        }
        try {
            This bshThis = (This)thiz;
            return (T)bshThis.getInterface(clasz);
        } catch (UtilEvalError utilEvalError) {
            utilEvalError.printStackTrace(System.err);
            return null;
        }
    }

    private This getGlobal() {
        return BshScriptEngine.getEngineNameSpace(this.getContext()).getThis(this.getInterpreter());
    }

    class WriterOutputStream
    extends OutputStream {
        Writer writer;

        WriterOutputStream(Writer writer) {
            this.writer = writer;
        }

        public void write(int b) throws IOException {
            this.writer.write(b);
        }

        public void flush() throws IOException {
            this.writer.flush();
        }

        public void close() throws IOException {
            this.writer.close();
        }
    }
}

