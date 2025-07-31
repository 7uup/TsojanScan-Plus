/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package bsh.classpath;

import bsh.BshClassManager;
import bsh.ClassPathException;
import bsh.Interpreter;
import bsh.InterpreterError;
import bsh.UtilEvalError;
import bsh.classpath.BshClassLoader;
import bsh.classpath.BshClassPath;
import bsh.classpath.DiscreteFilesClassLoader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

public class ClassManagerImpl
extends BshClassManager {
    static final String BSH_PACKAGE = "bsh";
    private BshClassPath baseClassPath;
    private boolean superImport;
    private BshClassPath fullClassPath;
    private Vector listeners = new Vector();
    private ReferenceQueue refQueue = new ReferenceQueue();
    private BshClassLoader baseLoader;
    private Map loaderMap;

    public ClassManagerImpl() {
        this.reset();
    }

    public Class classForName(String name) {
        ClassLoader overlayLoader;
        Class<?> c = (Class<?>)this.absoluteClassCache.get(name);
        if (c != null) {
            return c;
        }
        if (this.absoluteNonClasses.get(name) != null) {
            if (Interpreter.DEBUG) {
                Interpreter.debug("absoluteNonClass list hit: " + name);
            }
            return null;
        }
        if (Interpreter.DEBUG) {
            Interpreter.debug("Trying to load class: " + name);
        }
        if ((overlayLoader = this.getLoaderForClass(name)) != null) {
            try {
                c = overlayLoader.loadClass(name);
            } catch (Exception e) {
            } catch (NoClassDefFoundError e2) {
                throw ClassManagerImpl.noClassDefFound(name, e2);
            }
        }
        if (c == null && name.startsWith(BSH_PACKAGE)) {
            try {
                c = Interpreter.class.getClassLoader().loadClass(name);
            } catch (ClassNotFoundException e) {
                // empty catch block
            }
        }
        if (c == null && this.baseLoader != null) {
            try {
                c = this.baseLoader.loadClass(name);
            } catch (ClassNotFoundException e) {
                // empty catch block
            }
        }
        if (c == null && this.externalClassLoader != null) {
            try {
                c = this.externalClassLoader.loadClass(name);
            } catch (ClassNotFoundException e) {
                // empty catch block
            }
        }
        if (c == null) {
            try {
                ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
                if (contextClassLoader != null) {
                    c = Class.forName(name, true, contextClassLoader);
                }
            } catch (ClassNotFoundException e) {
            } catch (SecurityException e) {
                // empty catch block
            }
        }
        if (c == null) {
            try {
                c = this.plainClassForName(name);
            } catch (ClassNotFoundException e) {
                // empty catch block
            }
        }
        if (c == null) {
            c = this.loadSourceClass(name);
        }
        this.cacheClassInfo(name, c);
        return c;
    }

    public URL getResource(String path) {
        URL url = null;
        if (this.baseLoader != null) {
            url = this.baseLoader.getResource(path.substring(1));
        }
        if (url == null) {
            url = super.getResource(path);
        }
        return url;
    }

    public InputStream getResourceAsStream(String path) {
        InputStream in = null;
        if (this.baseLoader != null) {
            in = this.baseLoader.getResourceAsStream(path.substring(1));
        }
        if (in == null) {
            in = super.getResourceAsStream(path);
        }
        return in;
    }

    ClassLoader getLoaderForClass(String name) {
        return (ClassLoader)this.loaderMap.get(name);
    }

    public void addClassPath(URL path) throws IOException {
        if (this.baseLoader == null) {
            this.setClassPath(new URL[]{path});
        } else {
            this.baseLoader.addURL(path);
            this.baseClassPath.add(path);
            this.classLoaderChanged();
        }
    }

    public void reset() {
        this.baseClassPath = new BshClassPath("baseClassPath");
        this.baseLoader = null;
        this.loaderMap = new HashMap();
        this.classLoaderChanged();
    }

    public void setClassPath(URL[] cp) {
        this.baseClassPath.setPath(cp);
        this.initBaseLoader();
        this.loaderMap = new HashMap();
        this.classLoaderChanged();
    }

    public void reloadAllClasses() throws ClassPathException {
        BshClassPath bcp = new BshClassPath("temp");
        bcp.addComponent(this.baseClassPath);
        bcp.addComponent(BshClassPath.getUserClassPath());
        this.setClassPath(bcp.getPathComponents());
    }

    private void initBaseLoader() {
        this.baseLoader = new BshClassLoader((BshClassManager)this, this.baseClassPath);
    }

    public void reloadClasses(String[] classNames) throws ClassPathException {
        if (this.baseLoader == null) {
            this.initBaseLoader();
        }
        DiscreteFilesClassLoader.ClassSourceMap map = new DiscreteFilesClassLoader.ClassSourceMap();
        for (int i = 0; i < classNames.length; ++i) {
            String name = classNames[i];
            BshClassPath.ClassSource classSource = this.baseClassPath.getClassSource(name);
            if (classSource == null) {
                BshClassPath.getUserClassPath().insureInitialized();
                classSource = BshClassPath.getUserClassPath().getClassSource(name);
            }
            if (classSource == null) {
                throw new ClassPathException("Nothing known about class: " + name);
            }
            if (classSource instanceof BshClassPath.JarClassSource) {
                throw new ClassPathException("Cannot reload class: " + name + " from source: " + classSource);
            }
            map.put(name, classSource);
        }
        DiscreteFilesClassLoader cl = new DiscreteFilesClassLoader((BshClassManager)this, map);
        Iterator it = map.keySet().iterator();
        while (it.hasNext()) {
            this.loaderMap.put((String)it.next(), cl);
        }
        this.classLoaderChanged();
    }

    public void reloadPackage(String pack) throws ClassPathException {
        Set classes = this.baseClassPath.getClassesForPackage(pack);
        if (classes == null) {
            classes = BshClassPath.getUserClassPath().getClassesForPackage(pack);
        }
        if (classes == null) {
            throw new ClassPathException("No classes found for package: " + pack);
        }
        this.reloadClasses(classes.toArray(new String[0]));
    }

    public BshClassPath getClassPath() throws ClassPathException {
        if (this.fullClassPath != null) {
            return this.fullClassPath;
        }
        this.fullClassPath = new BshClassPath("BeanShell Full Class Path");
        this.fullClassPath.addComponent(BshClassPath.getUserClassPath());
        try {
            this.fullClassPath.addComponent(BshClassPath.getBootClassPath());
        } catch (ClassPathException e) {
            System.err.println("Warning: can't get boot class path");
        }
        this.fullClassPath.addComponent(this.baseClassPath);
        return this.fullClassPath;
    }

    public void doSuperImport() throws UtilEvalError {
        try {
            this.getClassPath().insureInitialized();
            this.getClassNameByUnqName("");
        } catch (ClassPathException e) {
            throw new UtilEvalError("Error importing classpath " + e);
        }
        this.superImport = true;
    }

    protected boolean hasSuperImport() {
        return this.superImport;
    }

    public String getClassNameByUnqName(String name) throws ClassPathException {
        return this.getClassPath().getClassNameByUnqName(name);
    }

    public void addListener(BshClassManager.Listener l) {
        Reference deadref;
        this.listeners.addElement(new WeakReference<BshClassManager.Listener>(l, this.refQueue));
        while ((deadref = this.refQueue.poll()) != null) {
            boolean ok = this.listeners.removeElement(deadref);
            if (ok || !Interpreter.DEBUG) continue;
            Interpreter.debug("tried to remove non-existent weak ref: " + deadref);
        }
    }

    public void removeListener(BshClassManager.Listener l) {
        throw new Error("unimplemented");
    }

    public ClassLoader getBaseLoader() {
        return this.baseLoader;
    }

    public Class defineClass(String name, byte[] code) {
        this.baseClassPath.setClassSource(name, new BshClassPath.GeneratedClassSource(code));
        try {
            this.reloadClasses(new String[]{name});
        } catch (ClassPathException e) {
            throw new InterpreterError("defineClass: " + e);
        }
        return this.classForName(name);
    }

    protected void classLoaderChanged() {
        this.clearCaches();
        Vector<WeakReference> toRemove = new Vector<WeakReference>();
        Enumeration e = this.listeners.elements();
        while (e.hasMoreElements()) {
            WeakReference wr = (WeakReference)e.nextElement();
            BshClassManager.Listener l = (BshClassManager.Listener)wr.get();
            if (l == null) {
                toRemove.add(wr);
                continue;
            }
            l.classLoaderChanged();
        }
        e = toRemove.elements();
        while (e.hasMoreElements()) {
            this.listeners.removeElement(e.nextElement());
        }
    }

    public void dump(PrintWriter i) {
        i.println("Bsh Class Manager Dump: ");
        i.println("----------------------- ");
        i.println("baseLoader = " + this.baseLoader);
        i.println("loaderMap= " + this.loaderMap);
        i.println("----------------------- ");
        i.println("baseClassPath = " + this.baseClassPath);
    }
}

