/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package bsh;

import bsh.Capabilities;
import bsh.EvalError;
import bsh.Interpreter;
import bsh.InterpreterError;
import bsh.Name;
import bsh.UtilEvalError;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.Hashtable;

public class BshClassManager {
    private static Object NOVALUE = new Object();
    private Interpreter declaringInterpreter;
    protected ClassLoader externalClassLoader;
    protected transient Hashtable absoluteClassCache = new Hashtable();
    protected transient Hashtable absoluteNonClasses = new Hashtable();
    protected transient Hashtable resolvedObjectMethods = new Hashtable();
    protected transient Hashtable resolvedStaticMethods = new Hashtable();
    protected transient Hashtable definingClasses = new Hashtable();
    protected transient Hashtable definingClassesBaseNames = new Hashtable();

    public static BshClassManager createClassManager(Interpreter interpreter) {
        BshClassManager manager;
        if (Capabilities.classExists("java.lang.ref.WeakReference") && Capabilities.classExists("java.util.HashMap") && Capabilities.classExists("bsh.classpath.ClassManagerImpl")) {
            try {
                Class<?> clas = Class.forName("bsh.classpath.ClassManagerImpl");
                manager = (BshClassManager)clas.newInstance();
            } catch (Exception e) {
                throw new InterpreterError("Error loading classmanager: " + e);
            }
        } else {
            manager = new BshClassManager();
        }
        if (interpreter == null) {
            interpreter = new Interpreter();
        }
        manager.declaringInterpreter = interpreter;
        return manager;
    }

    public boolean classExists(String name) {
        return this.classForName(name) != null;
    }

    public Class classForName(String name) {
        if (this.isClassBeingDefined(name)) {
            throw new InterpreterError("Attempting to load class in the process of being defined: " + name);
        }
        Class clas = null;
        try {
            clas = this.plainClassForName(name);
        } catch (ClassNotFoundException classNotFoundException) {
            // empty catch block
        }
        if (clas == null) {
            clas = this.loadSourceClass(name);
        }
        return clas;
    }

    protected Class loadSourceClass(String name) {
        String fileName = "/" + name.replace('.', '/') + ".java";
        InputStream in = this.getResourceAsStream(fileName);
        if (in == null) {
            return null;
        }
        try {
            System.out.println("Loading class from source file: " + fileName);
            this.declaringInterpreter.eval(new InputStreamReader(in));
        } catch (EvalError e) {
            System.err.println(e);
        }
        try {
            return this.plainClassForName(name);
        } catch (ClassNotFoundException e) {
            System.err.println("Class not found in source file: " + name);
            return null;
        }
    }

    public Class plainClassForName(String name) throws ClassNotFoundException {
        Class<?> c = null;
        try {
            c = this.externalClassLoader != null ? this.externalClassLoader.loadClass(name) : Class.forName(name);
            this.cacheClassInfo(name, c);
        } catch (NoClassDefFoundError e) {
            throw BshClassManager.noClassDefFound(name, e);
        }
        return c;
    }

    public URL getResource(String path) {
        URL url = null;
        if (this.externalClassLoader != null) {
            url = this.externalClassLoader.getResource(path.substring(1));
        }
        if (url == null) {
            url = Interpreter.class.getResource(path);
        }
        return url;
    }

    public InputStream getResourceAsStream(String path) {
        InputStream in = null;
        if (this.externalClassLoader != null) {
            in = this.externalClassLoader.getResourceAsStream(path.substring(1));
        }
        if (in == null) {
            in = Interpreter.class.getResourceAsStream(path);
        }
        return in;
    }

    public void cacheClassInfo(String name, Class value) {
        if (value != null) {
            this.absoluteClassCache.put(name, value);
        } else {
            this.absoluteNonClasses.put(name, NOVALUE);
        }
    }

    public void cacheResolvedMethod(Class clas, Class[] types, Method method) {
        if (Interpreter.DEBUG) {
            Interpreter.debug("cacheResolvedMethod putting: " + clas + " " + method);
        }
        SignatureKey sk = new SignatureKey(clas, method.getName(), types);
        if (Modifier.isStatic(method.getModifiers())) {
            this.resolvedStaticMethods.put(sk, method);
        } else {
            this.resolvedObjectMethods.put(sk, method);
        }
    }

    protected Method getResolvedMethod(Class clas, String methodName, Class[] types, boolean onlyStatic) {
        SignatureKey sk = new SignatureKey(clas, methodName, types);
        Method method = (Method)this.resolvedStaticMethods.get(sk);
        if (method == null && !onlyStatic) {
            method = (Method)this.resolvedObjectMethods.get(sk);
        }
        if (Interpreter.DEBUG) {
            if (method == null) {
                Interpreter.debug("getResolvedMethod cache MISS: " + clas + " - " + methodName);
            } else {
                Interpreter.debug("getResolvedMethod cache HIT: " + clas + " - " + method);
            }
        }
        return method;
    }

    protected void clearCaches() {
        this.absoluteNonClasses = new Hashtable();
        this.absoluteClassCache = new Hashtable();
        this.resolvedObjectMethods = new Hashtable();
        this.resolvedStaticMethods = new Hashtable();
    }

    public void setClassLoader(ClassLoader externalCL) {
        this.externalClassLoader = externalCL;
        this.classLoaderChanged();
    }

    public void addClassPath(URL path) throws IOException {
    }

    public void reset() {
        this.clearCaches();
    }

    public void setClassPath(URL[] cp) throws UtilEvalError {
        throw BshClassManager.cmUnavailable();
    }

    public void reloadAllClasses() throws UtilEvalError {
        throw BshClassManager.cmUnavailable();
    }

    public void reloadClasses(String[] classNames) throws UtilEvalError {
        throw BshClassManager.cmUnavailable();
    }

    public void reloadPackage(String pack) throws UtilEvalError {
        throw BshClassManager.cmUnavailable();
    }

    protected void doSuperImport() throws UtilEvalError {
        throw BshClassManager.cmUnavailable();
    }

    protected boolean hasSuperImport() {
        return false;
    }

    protected String getClassNameByUnqName(String name) throws UtilEvalError {
        throw BshClassManager.cmUnavailable();
    }

    public void addListener(Listener l) {
    }

    public void removeListener(Listener l) {
    }

    public void dump(PrintWriter pw) {
        pw.println("BshClassManager: no class manager.");
    }

    protected void definingClass(String className) {
        String cur;
        String baseName = Name.suffix(className, 1);
        int i = baseName.indexOf("$");
        if (i != -1) {
            baseName = baseName.substring(i + 1);
        }
        if ((cur = (String)this.definingClassesBaseNames.get(baseName)) != null) {
            throw new InterpreterError("Defining class problem: " + className + ": BeanShell cannot yet simultaneously define two or more " + "dependant classes of the same name.  Attempt to define: " + className + " while defining: " + cur);
        }
        this.definingClasses.put(className, NOVALUE);
        this.definingClassesBaseNames.put(baseName, className);
    }

    protected boolean isClassBeingDefined(String className) {
        return this.definingClasses.get(className) != null;
    }

    protected String getClassBeingDefined(String className) {
        String baseName = Name.suffix(className, 1);
        return (String)this.definingClassesBaseNames.get(baseName);
    }

    protected void doneDefiningClass(String className) {
        String baseName = Name.suffix(className, 1);
        this.definingClasses.remove(className);
        this.definingClassesBaseNames.remove(baseName);
    }

    public Class defineClass(String name, byte[] code) {
        throw new InterpreterError("Can't create class (" + name + ") without class manager package.");
    }

    protected void classLoaderChanged() {
    }

    protected static Error noClassDefFound(String className, Error e) {
        return new NoClassDefFoundError("A class required by class: " + className + " could not be loaded:\n" + e.toString());
    }

    protected static UtilEvalError cmUnavailable() {
        return new Capabilities.Unavailable("ClassLoading features unavailable.");
    }

    static class SignatureKey {
        Class clas;
        Class[] types;
        String methodName;
        int hashCode = 0;

        SignatureKey(Class clas, String methodName, Class[] types) {
            this.clas = clas;
            this.methodName = methodName;
            this.types = types;
        }

        public int hashCode() {
            if (this.hashCode == 0) {
                this.hashCode = this.clas.hashCode() * this.methodName.hashCode();
                if (this.types == null) {
                    return this.hashCode;
                }
                for (int i = 0; i < this.types.length; ++i) {
                    int hc = this.types[i] == null ? 21 : this.types[i].hashCode();
                    this.hashCode = this.hashCode * (i + 1) + hc;
                }
            }
            return this.hashCode;
        }

        public boolean equals(Object o) {
            SignatureKey target = (SignatureKey)o;
            if (this.types == null) {
                return target.types == null;
            }
            if (this.clas != target.clas) {
                return false;
            }
            if (!this.methodName.equals(target.methodName)) {
                return false;
            }
            if (this.types.length != target.types.length) {
                return false;
            }
            for (int i = 0; i < this.types.length; ++i) {
                if (!(this.types[i] == null ? target.types[i] != null : !this.types[i].equals(target.types[i]))) continue;
                return false;
            }
            return true;
        }
    }

    public static interface Listener {
        public void classLoaderChanged();
    }
}

