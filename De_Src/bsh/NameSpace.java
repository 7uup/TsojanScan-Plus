/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package bsh;

import bsh.BshClassManager;
import bsh.BshMethod;
import bsh.CallStack;
import bsh.ClassIdentifier;
import bsh.EvalError;
import bsh.Interpreter;
import bsh.InterpreterError;
import bsh.LHS;
import bsh.Modifiers;
import bsh.Name;
import bsh.NameSource;
import bsh.Primitive;
import bsh.Reflect;
import bsh.SimpleNode;
import bsh.This;
import bsh.UtilEvalError;
import bsh.Variable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

public class NameSpace
implements Serializable,
BshClassManager.Listener,
NameSource {
    public static final NameSpace JAVACODE = new NameSpace((BshClassManager)null, "Called from compiled Java code.");
    private String nsName;
    private NameSpace parent;
    private Hashtable variables;
    private Hashtable methods;
    protected Hashtable importedClasses;
    private Vector importedPackages;
    private Vector importedCommands;
    private Vector importedObjects;
    private Vector importedStatic;
    private String packageName;
    private transient BshClassManager classManager;
    private This thisReference;
    private Hashtable names;
    SimpleNode callerInfoNode;
    boolean isMethod;
    boolean isClass;
    Class classStatic;
    Object classInstance;
    private transient Hashtable classCache;
    Vector nameSourceListeners;

    void setClassStatic(Class clas) {
        this.classStatic = clas;
        this.importStatic(clas);
    }

    void setClassInstance(Object instance) {
        this.classInstance = instance;
        this.importObject(instance);
    }

    Object getClassInstance() throws UtilEvalError {
        if (this.classInstance != null) {
            return this.classInstance;
        }
        if (this.classStatic != null) {
            throw new UtilEvalError("Can't refer to class instance from static context.");
        }
        throw new InterpreterError("Can't resolve class instance 'this' in: " + this);
    }

    public NameSpace(NameSpace parent, String name) {
        this(parent, null, name);
    }

    public NameSpace(BshClassManager classManager, String name) {
        this(null, classManager, name);
    }

    public NameSpace(NameSpace parent, BshClassManager classManager, String name) {
        this.setName(name);
        this.setParent(parent);
        this.setClassManager(classManager);
        if (classManager != null) {
            classManager.addListener(this);
        }
    }

    public void setName(String name) {
        this.nsName = name;
    }

    public String getName() {
        return this.nsName;
    }

    void setNode(SimpleNode node) {
        this.callerInfoNode = node;
    }

    SimpleNode getNode() {
        if (this.callerInfoNode != null) {
            return this.callerInfoNode;
        }
        if (this.parent != null) {
            return this.parent.getNode();
        }
        return null;
    }

    public Object get(String name, Interpreter interpreter) throws UtilEvalError {
        CallStack callstack = new CallStack(this);
        return this.getNameResolver(name).toObject(callstack, interpreter);
    }

    public void setVariable(String name, Object value, boolean strictJava) throws UtilEvalError {
        boolean recurse = Interpreter.LOCALSCOPING ? strictJava : true;
        this.setVariable(name, value, strictJava, recurse);
    }

    void setLocalVariable(String name, Object value, boolean strictJava) throws UtilEvalError {
        this.setVariable(name, value, strictJava, false);
    }

    void setVariable(String name, Object value, boolean strictJava, boolean recurse) throws UtilEvalError {
        if (this.variables == null) {
            this.variables = new Hashtable();
        }
        if (value == null) {
            throw new InterpreterError("null variable value");
        }
        Variable existing = this.getVariableImpl(name, recurse);
        if (existing != null) {
            try {
                existing.setValue(value, 1);
            } catch (UtilEvalError e) {
                throw new UtilEvalError("Variable assignment: " + name + ": " + e.getMessage());
            }
        } else {
            if (strictJava) {
                throw new UtilEvalError("(Strict Java mode) Assignment to undeclared variable: " + name);
            }
            NameSpace varScope = this;
            varScope.variables.put(name, this.createVariable(name, value, null));
            this.nameSpaceChanged();
        }
    }

    protected Variable createVariable(String name, Object value, Modifiers mods) throws UtilEvalError {
        return this.createVariable(name, null, value, mods);
    }

    protected Variable createVariable(String name, Class type, Object value, Modifiers mods) throws UtilEvalError {
        return new Variable(name, type, value, mods);
    }

    protected Variable createVariable(String name, Class type, LHS lhs) throws UtilEvalError {
        return new Variable(name, type, lhs);
    }

    public void unsetVariable(String name) {
        if (this.variables != null) {
            this.variables.remove(name);
            this.nameSpaceChanged();
        }
    }

    public String[] getVariableNames() {
        if (this.variables == null) {
            return new String[0];
        }
        return this.enumerationToStringArray(this.variables.keys());
    }

    public String[] getMethodNames() {
        if (this.methods == null) {
            return new String[0];
        }
        return this.enumerationToStringArray(this.methods.keys());
    }

    public BshMethod[] getMethods() {
        if (this.methods == null) {
            return new BshMethod[0];
        }
        return this.flattenMethodCollection(this.methods.elements());
    }

    private String[] enumerationToStringArray(Enumeration e) {
        Vector v = new Vector();
        while (e.hasMoreElements()) {
            v.addElement(e.nextElement());
        }
        Object[] sa = new String[v.size()];
        v.copyInto(sa);
        return sa;
    }

    private BshMethod[] flattenMethodCollection(Enumeration e) {
        Vector v = new Vector();
        while (e.hasMoreElements()) {
            Object o = e.nextElement();
            if (o instanceof BshMethod) {
                v.addElement(o);
                continue;
            }
            Vector ov = (Vector)o;
            for (int i = 0; i < ov.size(); ++i) {
                v.addElement(ov.elementAt(i));
            }
        }
        Object[] bma = new BshMethod[v.size()];
        v.copyInto(bma);
        return bma;
    }

    public NameSpace getParent() {
        return this.parent;
    }

    public This getSuper(Interpreter declaringInterpreter) {
        if (this.parent != null) {
            return this.parent.getThis(declaringInterpreter);
        }
        return this.getThis(declaringInterpreter);
    }

    public This getGlobal(Interpreter declaringInterpreter) {
        if (this.parent != null) {
            return this.parent.getGlobal(declaringInterpreter);
        }
        return this.getThis(declaringInterpreter);
    }

    public This getThis(Interpreter declaringInterpreter) {
        if (this.thisReference == null) {
            this.thisReference = This.getThis(this, declaringInterpreter);
        }
        return this.thisReference;
    }

    public BshClassManager getClassManager() {
        if (this.classManager != null) {
            return this.classManager;
        }
        if (this.parent != null && this.parent != JAVACODE) {
            return this.parent.getClassManager();
        }
        this.classManager = BshClassManager.createClassManager(null);
        return this.classManager;
    }

    void setClassManager(BshClassManager classManager) {
        this.classManager = classManager;
    }

    public void prune() {
        if (this.classManager == null) {
            this.setClassManager(BshClassManager.createClassManager(null));
        }
        this.setParent(null);
    }

    public void setParent(NameSpace parent) {
        this.parent = parent;
        if (parent == null) {
            this.loadDefaultImports();
        }
    }

    public Object getVariable(String name) throws UtilEvalError {
        return this.getVariable(name, true);
    }

    public Object getVariable(String name, boolean recurse) throws UtilEvalError {
        Variable var = this.getVariableImpl(name, recurse);
        return this.unwrapVariable(var);
    }

    protected Variable getVariableImpl(String name, boolean recurse) throws UtilEvalError {
        Variable var = null;
        if (var == null && this.isClass) {
            var = this.getImportedVar(name);
        }
        if (var == null && this.variables != null) {
            var = (Variable)this.variables.get(name);
        }
        if (var == null && !this.isClass) {
            var = this.getImportedVar(name);
        }
        if (recurse && var == null && this.parent != null) {
            var = this.parent.getVariableImpl(name, recurse);
        }
        return var;
    }

    public Variable[] getDeclaredVariables() {
        if (this.variables == null) {
            return new Variable[0];
        }
        Variable[] vars = new Variable[this.variables.size()];
        int i = 0;
        Enumeration e = this.variables.elements();
        while (e.hasMoreElements()) {
            vars[i++] = (Variable)e.nextElement();
        }
        return vars;
    }

    protected Object unwrapVariable(Variable var) throws UtilEvalError {
        return var == null ? Primitive.VOID : var.getValue();
    }

    public void setTypedVariable(String name, Class type, Object value, boolean isFinal) throws UtilEvalError {
        Modifiers modifiers = new Modifiers();
        if (isFinal) {
            modifiers.addModifier(2, "final");
        }
        this.setTypedVariable(name, type, value, modifiers);
    }

    public void setTypedVariable(String name, Class type, Object value, Modifiers modifiers) throws UtilEvalError {
        Variable existing;
        if (this.variables == null) {
            this.variables = new Hashtable();
        }
        if ((existing = this.getVariableImpl(name, false)) != null && existing.getType() != null) {
            if (existing.getType() != type) {
                throw new UtilEvalError("Typed variable: " + name + " was previously declared with type: " + existing.getType());
            }
            existing.setValue(value, 0);
            return;
        }
        this.variables.put(name, this.createVariable(name, type, value, modifiers));
    }

    public void setMethod(String name, BshMethod method) throws UtilEvalError {
        Object m3;
        if (this.methods == null) {
            this.methods = new Hashtable();
        }
        if ((m3 = this.methods.get(name)) == null) {
            this.methods.put(name, method);
        } else if (m3 instanceof BshMethod) {
            Vector<Object> v = new Vector<Object>();
            v.addElement(m3);
            v.addElement(method);
            this.methods.put(name, v);
        } else {
            ((Vector)m3).addElement(method);
        }
    }

    public BshMethod getMethod(String name, Class[] sig) throws UtilEvalError {
        return this.getMethod(name, sig, false);
    }

    public BshMethod getMethod(String name, Class[] sig, boolean declaredOnly) throws UtilEvalError {
        Object method = null;
        if (method == null && this.isClass && !declaredOnly) {
            method = this.getImportedMethod(name, sig);
        }
        Object m3 = null;
        if (method == null && this.methods != null && (m3 = this.methods.get(name)) != null) {
            Object[] ma;
            if (m3 instanceof Vector) {
                Vector vm = m3;
                ma = new BshMethod[vm.size()];
                vm.copyInto(ma);
            } else {
                ma = new BshMethod[]{m3};
            }
            Class[][] candidates = new Class[ma.length][];
            for (int i = 0; i < ma.length; ++i) {
                candidates[i] = ((BshMethod)ma[i]).getParameterTypes();
            }
            int match = Reflect.findMostSpecificSignature(sig, candidates);
            if (match != -1) {
                method = ma[match];
            }
        }
        if (method == null && !this.isClass && !declaredOnly) {
            method = this.getImportedMethod(name, sig);
        }
        if (!declaredOnly && method == null && this.parent != null) {
            return this.parent.getMethod(name, sig);
        }
        return method;
    }

    public void importClass(String name) {
        if (this.importedClasses == null) {
            this.importedClasses = new Hashtable();
        }
        this.importedClasses.put(Name.suffix(name, 1), name);
        this.nameSpaceChanged();
    }

    public void importPackage(String name) {
        if (this.importedPackages == null) {
            this.importedPackages = new Vector();
        }
        if (this.importedPackages.contains(name)) {
            this.importedPackages.remove(name);
        }
        this.importedPackages.addElement(name);
        this.nameSpaceChanged();
    }

    public void importCommands(String name) {
        if (this.importedCommands == null) {
            this.importedCommands = new Vector();
        }
        if (!(name = name.replace('.', '/')).startsWith("/")) {
            name = "/" + name;
        }
        if (name.length() > 1 && name.endsWith("/")) {
            name = name.substring(0, name.length() - 1);
        }
        if (this.importedCommands.contains(name)) {
            this.importedCommands.remove(name);
        }
        this.importedCommands.addElement(name);
        this.nameSpaceChanged();
    }

    public Object getCommand(String name, Class[] argTypes, Interpreter interpreter) throws UtilEvalError {
        if (Interpreter.DEBUG) {
            Interpreter.debug("getCommand: " + name);
        }
        BshClassManager bcm = interpreter.getClassManager();
        if (this.importedCommands != null) {
            for (int i = this.importedCommands.size() - 1; i >= 0; --i) {
                String path = (String)this.importedCommands.elementAt(i);
                String scriptPath = path.equals("/") ? path + name + ".bsh" : path + "/" + name + ".bsh";
                Interpreter.debug("searching for script: " + scriptPath);
                InputStream in = bcm.getResourceAsStream(scriptPath);
                if (in != null) {
                    return this.loadScriptedCommand(in, name, argTypes, scriptPath, interpreter);
                }
                String className = path.equals("/") ? name : path.substring(1).replace('/', '.') + "." + name;
                Interpreter.debug("searching for class: " + className);
                Class clas = bcm.classForName(className);
                if (clas == null) continue;
                return clas;
            }
        }
        if (this.parent != null) {
            return this.parent.getCommand(name, argTypes, interpreter);
        }
        return null;
    }

    protected BshMethod getImportedMethod(String name, Class[] sig) throws UtilEvalError {
        int i;
        if (this.importedObjects != null) {
            for (i = 0; i < this.importedObjects.size(); ++i) {
                Object object = this.importedObjects.elementAt(i);
                Class<?> clas = object.getClass();
                Method method = Reflect.resolveJavaMethod(this.getClassManager(), clas, name, sig, false);
                if (method == null) continue;
                return new BshMethod(method, object);
            }
        }
        if (this.importedStatic != null) {
            for (i = 0; i < this.importedStatic.size(); ++i) {
                Class clas = (Class)this.importedStatic.elementAt(i);
                Method method = Reflect.resolveJavaMethod(this.getClassManager(), clas, name, sig, true);
                if (method == null) continue;
                return new BshMethod(method, null);
            }
        }
        return null;
    }

    protected Variable getImportedVar(String name) throws UtilEvalError {
        int i;
        if (this.importedObjects != null) {
            for (i = 0; i < this.importedObjects.size(); ++i) {
                Object object = this.importedObjects.elementAt(i);
                Class<?> clas = object.getClass();
                Field field = Reflect.resolveJavaField(clas, name, false);
                if (field == null) continue;
                return this.createVariable(name, field.getType(), new LHS(object, field));
            }
        }
        if (this.importedStatic != null) {
            for (i = 0; i < this.importedStatic.size(); ++i) {
                Class clas = (Class)this.importedStatic.elementAt(i);
                Field field = Reflect.resolveJavaField(clas, name, true);
                if (field == null) continue;
                return this.createVariable(name, field.getType(), new LHS(field));
            }
        }
        return null;
    }

    private BshMethod loadScriptedCommand(InputStream in, String name, Class[] argTypes, String resourcePath, Interpreter interpreter) throws UtilEvalError {
        try {
            interpreter.eval(new InputStreamReader(in), this, resourcePath);
        } catch (EvalError e) {
            Interpreter.debug(e.toString());
            throw new UtilEvalError("Error loading script: " + e.getMessage());
        }
        BshMethod meth = this.getMethod(name, argTypes);
        return meth;
    }

    void cacheClass(String name, Class c) {
        if (this.classCache == null) {
            this.classCache = new Hashtable();
        }
        this.classCache.put(name, c);
    }

    public Class getClass(String name) throws UtilEvalError {
        Class c = this.getClassImpl(name);
        if (c != null) {
            return c;
        }
        if (this.parent != null) {
            return this.parent.getClass(name);
        }
        return null;
    }

    private Class getClassImpl(String name) throws UtilEvalError {
        boolean unqualifiedName;
        Class c = null;
        if (this.classCache != null && (c = (Class)this.classCache.get(name)) != null) {
            return c;
        }
        boolean bl = unqualifiedName = !Name.isCompound(name);
        if (unqualifiedName) {
            if (c == null) {
                c = this.getImportedClassImpl(name);
            }
            if (c != null) {
                this.cacheClass(name, c);
                return c;
            }
        }
        if ((c = this.classForName(name)) != null) {
            if (unqualifiedName) {
                this.cacheClass(name, c);
            }
            return c;
        }
        if (Interpreter.DEBUG) {
            Interpreter.debug("getClass(): " + name + " not\tfound in " + this);
        }
        return null;
    }

    private Class getImportedClassImpl(String name) throws UtilEvalError {
        BshClassManager bcm;
        String s2;
        String fullname = null;
        if (this.importedClasses != null) {
            fullname = (String)this.importedClasses.get(name);
        }
        if (fullname != null) {
            Class clas = this.classForName(fullname);
            if (clas == null) {
                if (Name.isCompound(fullname)) {
                    try {
                        clas = this.getNameResolver(fullname).toClass();
                    } catch (ClassNotFoundException e) {}
                } else if (Interpreter.DEBUG) {
                    Interpreter.debug("imported unpackaged name not found:" + fullname);
                }
                if (clas != null) {
                    this.getClassManager().cacheClassInfo(fullname, clas);
                    return clas;
                }
            } else {
                return clas;
            }
            return null;
        }
        if (this.importedPackages != null) {
            for (int i = this.importedPackages.size() - 1; i >= 0; --i) {
                s2 = (String)this.importedPackages.elementAt(i) + "." + name;
                Class c = this.classForName(s2);
                if (c == null) continue;
                return c;
            }
        }
        if ((bcm = this.getClassManager()).hasSuperImport() && (s2 = bcm.getClassNameByUnqName(name)) != null) {
            return this.classForName(s2);
        }
        return null;
    }

    private Class classForName(String name) {
        return this.getClassManager().classForName(name);
    }

    public String[] getAllNames() {
        Vector vec = new Vector();
        this.getAllNamesAux(vec);
        Object[] names = new String[vec.size()];
        vec.copyInto(names);
        return names;
    }

    protected void getAllNamesAux(Vector vec) {
        Enumeration varNames = this.variables.keys();
        while (varNames.hasMoreElements()) {
            vec.addElement(varNames.nextElement());
        }
        Enumeration methodNames = this.methods.keys();
        while (methodNames.hasMoreElements()) {
            vec.addElement(methodNames.nextElement());
        }
        if (this.parent != null) {
            this.parent.getAllNamesAux(vec);
        }
    }

    public void addNameSourceListener(NameSource.Listener listener) {
        if (this.nameSourceListeners == null) {
            this.nameSourceListeners = new Vector();
        }
        this.nameSourceListeners.addElement(listener);
    }

    public void doSuperImport() throws UtilEvalError {
        this.getClassManager().doSuperImport();
    }

    public String toString() {
        return "NameSpace: " + (this.nsName == null ? super.toString() : this.nsName + " (" + super.toString() + ")") + (this.isClass ? " (isClass) " : "") + (this.isMethod ? " (method) " : "") + (this.classStatic != null ? " (class static) " : "") + (this.classInstance != null ? " (class instance) " : "");
    }

    private synchronized void writeObject(ObjectOutputStream s2) throws IOException {
        this.names = null;
        s2.defaultWriteObject();
    }

    public Object invokeMethod(String methodName, Object[] args2, Interpreter interpreter) throws EvalError {
        return this.invokeMethod(methodName, args2, interpreter, null, null);
    }

    public Object invokeMethod(String methodName, Object[] args2, Interpreter interpreter, CallStack callstack, SimpleNode callerInfo) throws EvalError {
        return this.getThis(interpreter).invokeMethod(methodName, args2, interpreter, callstack, callerInfo, false);
    }

    public void classLoaderChanged() {
        this.nameSpaceChanged();
    }

    public void nameSpaceChanged() {
        this.classCache = null;
        this.names = null;
    }

    public void loadDefaultImports() {
        this.importClass("bsh.EvalError");
        this.importClass("bsh.Interpreter");
        this.importPackage("javax.swing.event");
        this.importPackage("javax.swing");
        this.importPackage("java.awt.event");
        this.importPackage("java.awt");
        this.importPackage("java.net");
        this.importPackage("java.util");
        this.importPackage("java.io");
        this.importPackage("java.lang");
        this.importCommands("/bsh/commands");
    }

    Name getNameResolver(String ambigname) {
        Name name;
        if (this.names == null) {
            this.names = new Hashtable();
        }
        if ((name = (Name)this.names.get(ambigname)) == null) {
            name = new Name(this, ambigname);
            this.names.put(ambigname, name);
        }
        return name;
    }

    public int getInvocationLine() {
        SimpleNode node = this.getNode();
        if (node != null) {
            return node.getLineNumber();
        }
        return -1;
    }

    public String getInvocationText() {
        SimpleNode node = this.getNode();
        if (node != null) {
            return node.getText();
        }
        return "<invoked from Java code>";
    }

    public static Class identifierToClass(ClassIdentifier ci) {
        return ci.getTargetClass();
    }

    public void clear() {
        this.variables = null;
        this.methods = null;
        this.importedClasses = null;
        this.importedPackages = null;
        this.importedCommands = null;
        this.importedObjects = null;
        if (this.parent == null) {
            this.loadDefaultImports();
        }
        this.classCache = null;
        this.names = null;
    }

    public void importObject(Object obj) {
        if (this.importedObjects == null) {
            this.importedObjects = new Vector();
        }
        if (this.importedObjects.contains(obj)) {
            this.importedObjects.remove(obj);
        }
        this.importedObjects.addElement(obj);
        this.nameSpaceChanged();
    }

    public void importStatic(Class clas) {
        if (this.importedStatic == null) {
            this.importedStatic = new Vector();
        }
        if (this.importedStatic.contains(clas)) {
            this.importedStatic.remove(clas);
        }
        this.importedStatic.addElement(clas);
        this.nameSpaceChanged();
    }

    void setPackage(String packageName) {
        this.packageName = packageName;
    }

    String getPackage() {
        if (this.packageName != null) {
            return this.packageName;
        }
        if (this.parent != null) {
            return this.parent.getPackage();
        }
        return null;
    }

    static {
        NameSpace.JAVACODE.isMethod = true;
    }
}

