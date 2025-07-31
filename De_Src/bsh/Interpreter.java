/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package bsh;

import bsh.BshClassManager;
import bsh.CallStack;
import bsh.CommandLineReader;
import bsh.ConsoleInterface;
import bsh.EvalError;
import bsh.InterpreterError;
import bsh.JJTParserState;
import bsh.JavaCharStream;
import bsh.LHS;
import bsh.Name;
import bsh.NameSpace;
import bsh.ParseException;
import bsh.Parser;
import bsh.Primitive;
import bsh.Reflect;
import bsh.ReturnControl;
import bsh.SimpleNode;
import bsh.TargetError;
import bsh.This;
import bsh.TokenMgrError;
import bsh.UtilEvalError;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintStream;
import java.io.Reader;
import java.io.Serializable;
import java.io.StringReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Interpreter
implements Runnable,
ConsoleInterface,
Serializable {
    public static final String VERSION = "2.0b5";
    public static boolean DEBUG;
    public static boolean TRACE;
    public static boolean LOCALSCOPING;
    static transient PrintStream debug;
    static String systemLineSeparator;
    static This sharedObject;
    private boolean strictJava = false;
    transient Parser parser;
    NameSpace globalNameSpace;
    transient Reader in;
    transient PrintStream out;
    transient PrintStream err;
    ConsoleInterface console;
    Interpreter parent;
    String sourceFileInfo;
    private boolean exitOnEOF = true;
    protected boolean evalOnly;
    protected boolean interactive;
    private boolean showResults;

    public Interpreter(Reader in, PrintStream out, PrintStream err, boolean interactive, NameSpace namespace, Interpreter parent, String sourceFileInfo) {
        this.parser = new Parser(in);
        long t1 = System.currentTimeMillis();
        this.in = in;
        this.out = out;
        this.err = err;
        this.interactive = interactive;
        debug = err;
        this.parent = parent;
        if (parent != null) {
            this.setStrictJava(parent.getStrictJava());
        }
        this.sourceFileInfo = sourceFileInfo;
        BshClassManager bcm = BshClassManager.createClassManager(this);
        this.globalNameSpace = namespace == null ? new NameSpace(bcm, "global") : namespace;
        if (!(this.getu("bsh") instanceof This)) {
            this.initRootSystemObject();
        }
        if (interactive) {
            this.loadRCFiles();
        }
        long t2 = System.currentTimeMillis();
        if (DEBUG) {
            Interpreter.debug("Time to initialize interpreter: " + (t2 - t1));
        }
    }

    public Interpreter(Reader in, PrintStream out, PrintStream err, boolean interactive, NameSpace namespace) {
        this(in, out, err, interactive, namespace, null, null);
    }

    public Interpreter(Reader in, PrintStream out, PrintStream err, boolean interactive) {
        this(in, out, err, interactive, null);
    }

    public Interpreter(ConsoleInterface console, NameSpace globalNameSpace) {
        this(console.getIn(), console.getOut(), console.getErr(), true, globalNameSpace);
        this.setConsole(console);
    }

    public Interpreter(ConsoleInterface console) {
        this(console, null);
    }

    public Interpreter() {
        this(new StringReader(""), System.out, System.err, false, null);
        this.evalOnly = true;
        this.setu("bsh.evalOnly", new Primitive(true));
    }

    public void setConsole(ConsoleInterface console) {
        this.console = console;
        this.setu("bsh.console", console);
        this.setOut(console.getOut());
        this.setErr(console.getErr());
    }

    private void initRootSystemObject() {
        BshClassManager bcm = this.getClassManager();
        this.setu("bsh", new NameSpace(bcm, "Bsh Object").getThis(this));
        if (sharedObject == null) {
            sharedObject = new NameSpace(bcm, "Bsh Shared System Object").getThis(this);
        }
        this.setu("bsh.system", sharedObject);
        this.setu("bsh.shared", sharedObject);
        This helpText = new NameSpace(bcm, "Bsh Command Help Text").getThis(this);
        this.setu("bsh.help", helpText);
        try {
            this.setu("bsh.cwd", System.getProperty("user.dir"));
        } catch (SecurityException e) {
            this.setu("bsh.cwd", ".");
        }
        this.setu("bsh.interactive", new Primitive(this.interactive));
        this.setu("bsh.evalOnly", new Primitive(this.evalOnly));
    }

    public void setNameSpace(NameSpace globalNameSpace) {
        this.globalNameSpace = globalNameSpace;
    }

    public NameSpace getNameSpace() {
        return this.globalNameSpace;
    }

    public static void main(String[] args2) {
        block13: {
            if (args2.length > 0) {
                String[] bshArgs;
                String filename = args2[0];
                if (args2.length > 1) {
                    bshArgs = new String[args2.length - 1];
                    System.arraycopy(args2, 1, bshArgs, 0, args2.length - 1);
                } else {
                    bshArgs = new String[]{};
                }
                Interpreter interpreter = new Interpreter();
                interpreter.setu("bsh.args", bshArgs);
                try {
                    Object result = interpreter.source(filename, interpreter.globalNameSpace);
                    if (!(result instanceof Class)) break block13;
                    try {
                        Interpreter.invokeMain((Class)result, bshArgs);
                    } catch (Exception e) {
                        Throwable o = e;
                        if (e instanceof InvocationTargetException) {
                            o = ((InvocationTargetException)e).getTargetException();
                        }
                        System.err.println("Class: " + result + " main method threw exception:" + o);
                    }
                } catch (FileNotFoundException e) {
                    System.out.println("File not found: " + e);
                } catch (TargetError e) {
                    System.out.println("Script threw exception: " + e);
                    if (e.inNativeCode()) {
                        e.printStackTrace(DEBUG, System.err);
                    }
                } catch (EvalError e) {
                    System.out.println("Evaluation Error: " + e);
                } catch (IOException e) {
                    System.out.println("I/O Error: " + e);
                }
            } else {
                InputStream src = System.getProperty("os.name").startsWith("Windows") && System.getProperty("java.version").startsWith("1.1.") ? new FilterInputStream(System.in){

                    public int available() throws IOException {
                        return 0;
                    }
                } : System.in;
                CommandLineReader in = new CommandLineReader(new InputStreamReader(src));
                Interpreter interpreter = new Interpreter(in, System.out, System.err, true);
                interpreter.run();
            }
        }
    }

    public static void invokeMain(Class clas, String[] args2) throws Exception {
        Method main = Reflect.resolveJavaMethod(null, clas, "main", new Class[]{String[].class}, true);
        if (main != null) {
            main.invoke(null, new Object[]{args2});
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void run() {
        if (this.evalOnly) {
            throw new RuntimeException("bsh Interpreter: No stream");
        }
        if (this.interactive) {
            try {
                this.eval("printBanner();");
            } catch (EvalError e) {
                this.println("BeanShell 2.0b5 - by Pat Niemeyer (pat@pat.net)");
            }
        }
        CallStack callstack = new CallStack(this.globalNameSpace);
        boolean eof = false;
        while (!eof) {
            try {
                System.out.flush();
                System.err.flush();
                Thread.yield();
                if (this.interactive) {
                    this.print(this.getBshPrompt());
                }
                eof = this.Line();
                if (this.get_jjtree().nodeArity() <= 0) continue;
                SimpleNode node = (SimpleNode)this.get_jjtree().rootNode();
                if (DEBUG) {
                    node.dump(">");
                }
                Object ret = node.eval(callstack, this);
                if (callstack.depth() > 1) {
                    throw new InterpreterError("Callstack growing: " + callstack);
                }
                if (ret instanceof ReturnControl) {
                    ret = ((ReturnControl)ret).value;
                }
                if (ret == Primitive.VOID) continue;
                this.setu("$_", ret);
                if (!this.showResults) continue;
                this.println("<" + ret + ">");
            } catch (ParseException e) {
                this.error("Parser Error: " + e.getMessage(DEBUG));
                if (DEBUG) {
                    e.printStackTrace();
                }
                if (!this.interactive) {
                    eof = true;
                }
                this.parser.reInitInput(this.in);
            } catch (InterpreterError e) {
                this.error("Internal Error: " + e.getMessage());
                e.printStackTrace();
                if (this.interactive) continue;
                eof = true;
            } catch (TargetError e) {
                this.error("// Uncaught Exception: " + e);
                if (e.inNativeCode()) {
                    e.printStackTrace(DEBUG, this.err);
                }
                if (!this.interactive) {
                    eof = true;
                }
                this.setu("$_e", e.getTarget());
            } catch (EvalError e) {
                if (this.interactive) {
                    this.error("EvalError: " + e.toString());
                } else {
                    this.error("EvalError: " + e.getMessage());
                }
                if (DEBUG) {
                    e.printStackTrace();
                }
                if (this.interactive) continue;
                eof = true;
            } catch (Exception e) {
                this.error("Unknown error: " + e);
                if (DEBUG) {
                    e.printStackTrace();
                }
                if (this.interactive) continue;
                eof = true;
            } catch (TokenMgrError e) {
                this.error("Error parsing input: " + e);
                this.parser.reInitTokenInput(this.in);
                if (this.interactive) continue;
                eof = true;
            } finally {
                this.get_jjtree().reset();
                if (callstack.depth() <= 1) continue;
                callstack.clear();
                callstack.push(this.globalNameSpace);
            }
        }
        if (this.interactive && this.exitOnEOF) {
            System.exit(0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public Object source(String filename, NameSpace nameSpace) throws FileNotFoundException, IOException, EvalError {
        File file = this.pathToFile(filename);
        if (DEBUG) {
            Interpreter.debug("Sourcing file: " + file);
        }
        BufferedReader sourceIn = new BufferedReader(new FileReader(file));
        try {
            Object object = this.eval(sourceIn, nameSpace, filename);
            return object;
        } finally {
            ((Reader)sourceIn).close();
        }
    }

    public Object source(String filename) throws FileNotFoundException, IOException, EvalError {
        return this.source(filename, this.globalNameSpace);
    }

    public Object eval(Reader in, NameSpace nameSpace, String sourceFileInfo) throws EvalError {
        Object retVal = null;
        if (DEBUG) {
            Interpreter.debug("eval: nameSpace = " + nameSpace);
        }
        Interpreter localInterpreter = new Interpreter(in, this.out, this.err, false, nameSpace, this, sourceFileInfo);
        CallStack callstack = new CallStack(nameSpace);
        boolean eof = false;
        while (!eof) {
            SimpleNode node = null;
            try {
                eof = localInterpreter.Line();
                if (localInterpreter.get_jjtree().nodeArity() <= 0) continue;
                node = (SimpleNode)localInterpreter.get_jjtree().rootNode();
                node.setSourceFile(sourceFileInfo);
                if (TRACE) {
                    this.println("// " + node.getText());
                }
                retVal = node.eval(callstack, localInterpreter);
                if (callstack.depth() > 1) {
                    throw new InterpreterError("Callstack growing: " + callstack);
                }
                if (retVal instanceof ReturnControl) {
                    retVal = ((ReturnControl)retVal).value;
                    break;
                }
                if (!localInterpreter.showResults || retVal == Primitive.VOID) continue;
                this.println("<" + retVal + ">");
            } catch (ParseException e) {
                if (DEBUG) {
                    this.error(e.getMessage(DEBUG));
                }
                e.setErrorSourceFile(sourceFileInfo);
                throw e;
            } catch (InterpreterError e) {
                e.printStackTrace();
                throw new EvalError("Sourced file: " + sourceFileInfo + " internal Error: " + e.getMessage(), node, callstack);
            } catch (TargetError e) {
                if (e.getNode() == null) {
                    e.setNode(node);
                }
                e.reThrow("Sourced file: " + sourceFileInfo);
            } catch (EvalError e) {
                if (DEBUG) {
                    e.printStackTrace();
                }
                if (e.getNode() == null) {
                    e.setNode(node);
                }
                e.reThrow("Sourced file: " + sourceFileInfo);
            } catch (Exception e) {
                if (DEBUG) {
                    e.printStackTrace();
                }
                throw new EvalError("Sourced file: " + sourceFileInfo + " unknown error: " + e.getMessage(), node, callstack);
            } catch (TokenMgrError e) {
                throw new EvalError("Sourced file: " + sourceFileInfo + " Token Parsing Error: " + e.getMessage(), node, callstack);
            } finally {
                localInterpreter.get_jjtree().reset();
                if (callstack.depth() <= 1) continue;
                callstack.clear();
                callstack.push(nameSpace);
            }
        }
        return Primitive.unwrap(retVal);
    }

    public Object eval(Reader in) throws EvalError {
        return this.eval(in, this.globalNameSpace, "eval stream");
    }

    public Object eval(String statements) throws EvalError {
        if (DEBUG) {
            Interpreter.debug("eval(String): " + statements);
        }
        return this.eval(statements, this.globalNameSpace);
    }

    public Object eval(String statements, NameSpace nameSpace) throws EvalError {
        String s2 = statements.endsWith(";") ? statements : statements + ";";
        return this.eval(new StringReader(s2), nameSpace, "inline evaluation of: ``" + this.showEvalString(s2) + "''");
    }

    private String showEvalString(String s2) {
        s2 = s2.replace('\n', ' ');
        if ((s2 = s2.replace('\r', ' ')).length() > 80) {
            s2 = s2.substring(0, 80) + " . . . ";
        }
        return s2;
    }

    public final void error(Object o) {
        if (this.console != null) {
            this.console.error("// Error: " + o + "\n");
        } else {
            this.err.println("// Error: " + o);
            this.err.flush();
        }
    }

    public Reader getIn() {
        return this.in;
    }

    public PrintStream getOut() {
        return this.out;
    }

    public PrintStream getErr() {
        return this.err;
    }

    public final void println(Object o) {
        this.print(String.valueOf(o) + systemLineSeparator);
    }

    public final void print(Object o) {
        if (this.console != null) {
            this.console.print(o);
        } else {
            this.out.print(o);
            this.out.flush();
        }
    }

    public static final void debug(String s2) {
        if (DEBUG) {
            debug.println("// Debug: " + s2);
        }
    }

    public Object get(String name) throws EvalError {
        try {
            Object ret = this.globalNameSpace.get(name, this);
            return Primitive.unwrap(ret);
        } catch (UtilEvalError e) {
            throw e.toEvalError(SimpleNode.JAVACODE, new CallStack());
        }
    }

    Object getu(String name) {
        try {
            return this.get(name);
        } catch (EvalError e) {
            throw new InterpreterError("set: " + e);
        }
    }

    public void set(String name, Object value) throws EvalError {
        if (value == null) {
            value = Primitive.NULL;
        }
        CallStack callstack = new CallStack();
        try {
            if (Name.isCompound(name)) {
                LHS lhs = this.globalNameSpace.getNameResolver(name).toLHS(callstack, this);
                lhs.assign(value, false);
            } else {
                this.globalNameSpace.setVariable(name, value, false);
            }
        } catch (UtilEvalError e) {
            throw e.toEvalError(SimpleNode.JAVACODE, callstack);
        }
    }

    void setu(String name, Object value) {
        try {
            this.set(name, value);
        } catch (EvalError e) {
            throw new InterpreterError("set: " + e);
        }
    }

    public void set(String name, long value) throws EvalError {
        this.set(name, new Primitive(value));
    }

    public void set(String name, int value) throws EvalError {
        this.set(name, new Primitive(value));
    }

    public void set(String name, double value) throws EvalError {
        this.set(name, new Primitive(value));
    }

    public void set(String name, float value) throws EvalError {
        this.set(name, new Primitive(value));
    }

    public void set(String name, boolean value) throws EvalError {
        this.set(name, new Primitive(value));
    }

    public void unset(String name) throws EvalError {
        CallStack callstack = new CallStack();
        try {
            LHS lhs = this.globalNameSpace.getNameResolver(name).toLHS(callstack, this);
            if (lhs.type != 0) {
                throw new EvalError("Can't unset, not a variable: " + name, SimpleNode.JAVACODE, new CallStack());
            }
            lhs.nameSpace.unsetVariable(name);
        } catch (UtilEvalError e) {
            throw new EvalError(e.getMessage(), SimpleNode.JAVACODE, new CallStack());
        }
    }

    public Object getInterface(Class interf) throws EvalError {
        try {
            return this.globalNameSpace.getThis(this).getInterface(interf);
        } catch (UtilEvalError e) {
            throw e.toEvalError(SimpleNode.JAVACODE, new CallStack());
        }
    }

    private JJTParserState get_jjtree() {
        return this.parser.jjtree;
    }

    private JavaCharStream get_jj_input_stream() {
        return this.parser.jj_input_stream;
    }

    private boolean Line() throws ParseException {
        return this.parser.Line();
    }

    void loadRCFiles() {
        block2: {
            try {
                String rcfile = System.getProperty("user.home") + File.separator + ".bshrc";
                this.source(rcfile, this.globalNameSpace);
            } catch (Exception e) {
                if (!DEBUG) break block2;
                Interpreter.debug("Could not find rc file: " + e);
            }
        }
    }

    public File pathToFile(String fileName) throws IOException {
        File file = new File(fileName);
        if (!file.isAbsolute()) {
            String cwd = (String)this.getu("bsh.cwd");
            file = new File(cwd + File.separator + fileName);
        }
        return new File(file.getCanonicalPath());
    }

    public static void redirectOutputToFile(String filename) {
        try {
            PrintStream pout = new PrintStream(new FileOutputStream(filename));
            System.setOut(pout);
            System.setErr(pout);
        } catch (IOException e) {
            System.err.println("Can't redirect output to file: " + filename);
        }
    }

    public void setClassLoader(ClassLoader externalCL) {
        this.getClassManager().setClassLoader(externalCL);
    }

    public BshClassManager getClassManager() {
        return this.getNameSpace().getClassManager();
    }

    public void setStrictJava(boolean b) {
        this.strictJava = b;
    }

    public boolean getStrictJava() {
        return this.strictJava;
    }

    static void staticInit() {
        try {
            systemLineSeparator = System.getProperty("line.separator");
            debug = System.err;
            DEBUG = Boolean.getBoolean("debug");
            TRACE = Boolean.getBoolean("trace");
            LOCALSCOPING = Boolean.getBoolean("localscoping");
            String outfilename = System.getProperty("outfile");
            if (outfilename != null) {
                Interpreter.redirectOutputToFile(outfilename);
            }
        } catch (SecurityException e) {
            System.err.println("Could not init static:" + e);
        } catch (Exception e) {
            System.err.println("Could not init static(2):" + e);
        } catch (Throwable e) {
            System.err.println("Could not init static(3):" + e);
        }
    }

    public String getSourceFileInfo() {
        if (this.sourceFileInfo != null) {
            return this.sourceFileInfo;
        }
        return "<unknown source>";
    }

    public Interpreter getParent() {
        return this.parent;
    }

    public void setOut(PrintStream out) {
        this.out = out;
    }

    public void setErr(PrintStream err) {
        this.err = err;
    }

    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        if (this.console != null) {
            this.setOut(this.console.getOut());
            this.setErr(this.console.getErr());
        } else {
            this.setOut(System.out);
            this.setErr(System.err);
        }
    }

    private String getBshPrompt() {
        try {
            return (String)this.eval("getBshPrompt()");
        } catch (Exception e) {
            return "bsh % ";
        }
    }

    public void setExitOnEOF(boolean value) {
        this.exitOnEOF = value;
    }

    public void setShowResults(boolean showResults) {
        this.showResults = showResults;
    }

    public boolean getShowResults() {
        return this.showResults;
    }

    static {
        systemLineSeparator = "\n";
        Interpreter.staticInit();
    }
}

