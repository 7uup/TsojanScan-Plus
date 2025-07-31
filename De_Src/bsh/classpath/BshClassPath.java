/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package bsh.classpath;

import bsh.ClassPathException;
import bsh.NameSource;
import bsh.StringUtil;
import bsh.classpath.ClassPathListener;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class BshClassPath
implements ClassPathListener,
NameSource {
    String name;
    private List path;
    private List compPaths;
    private Map packageMap;
    private Map classSource;
    private boolean mapsInitialized;
    private UnqualifiedNameTable unqNameTable;
    private boolean nameCompletionIncludesUnqNames = true;
    Vector listeners = new Vector();
    static URL[] userClassPathComp;
    static BshClassPath userClassPath;
    static BshClassPath bootClassPath;
    List nameSourceListeners;
    static MappingFeedback mappingFeedbackListener;

    public BshClassPath(String name) {
        this.name = name;
        this.reset();
    }

    public BshClassPath(String name, URL[] urls2) {
        this(name);
        this.add(urls2);
    }

    public void setPath(URL[] urls2) {
        this.reset();
        this.add(urls2);
    }

    public void addComponent(BshClassPath bcp) {
        if (this.compPaths == null) {
            this.compPaths = new ArrayList();
        }
        this.compPaths.add(bcp);
        bcp.addListener(this);
    }

    public void add(URL[] urls2) {
        this.path.addAll(Arrays.asList(urls2));
        if (this.mapsInitialized) {
            this.map(urls2);
        }
    }

    public void add(URL url) throws IOException {
        this.path.add(url);
        if (this.mapsInitialized) {
            this.map(url);
        }
    }

    public URL[] getPathComponents() {
        return this.getFullPath().toArray(new URL[0]);
    }

    public synchronized Set getClassesForPackage(String pack) {
        this.insureInitialized();
        HashSet set = new HashSet();
        Collection c = (Collection)this.packageMap.get(pack);
        if (c != null) {
            set.addAll(c);
        }
        if (this.compPaths != null) {
            for (int i = 0; i < this.compPaths.size(); ++i) {
                c = ((BshClassPath)this.compPaths.get(i)).getClassesForPackage(pack);
                if (c == null) continue;
                set.addAll(c);
            }
        }
        return set;
    }

    public synchronized ClassSource getClassSource(String className) {
        ClassSource cs = (ClassSource)this.classSource.get(className);
        if (cs != null) {
            return cs;
        }
        this.insureInitialized();
        cs = (ClassSource)this.classSource.get(className);
        if (cs == null && this.compPaths != null) {
            for (int i = 0; i < this.compPaths.size() && cs == null; ++i) {
                cs = ((BshClassPath)this.compPaths.get(i)).getClassSource(className);
            }
        }
        return cs;
    }

    public synchronized void setClassSource(String className, ClassSource cs) {
        this.classSource.put(className, cs);
    }

    public void insureInitialized() {
        this.insureInitialized(true);
    }

    protected synchronized void insureInitialized(boolean topPath) {
        if (topPath && !this.mapsInitialized) {
            this.startClassMapping();
        }
        if (this.compPaths != null) {
            for (int i = 0; i < this.compPaths.size(); ++i) {
                ((BshClassPath)this.compPaths.get(i)).insureInitialized(false);
            }
        }
        if (!this.mapsInitialized) {
            this.map(this.path.toArray(new URL[0]));
        }
        if (topPath && !this.mapsInitialized) {
            this.endClassMapping();
        }
        this.mapsInitialized = true;
    }

    protected List getFullPath() {
        ArrayList list = new ArrayList();
        if (this.compPaths != null) {
            for (int i = 0; i < this.compPaths.size(); ++i) {
                List l = ((BshClassPath)this.compPaths.get(i)).getFullPath();
                for (Object o : l) {
                    if (list.contains(o)) continue;
                    list.add(o);
                }
            }
        }
        list.addAll(this.path);
        return list;
    }

    public String getClassNameByUnqName(String name) throws ClassPathException {
        this.insureInitialized();
        UnqualifiedNameTable unqNameTable = this.getUnqualifiedNameTable();
        Object obj = unqNameTable.get(name);
        if (obj instanceof AmbiguousName) {
            throw new ClassPathException("Ambigous class names: " + ((AmbiguousName)obj).get());
        }
        return (String)obj;
    }

    private UnqualifiedNameTable getUnqualifiedNameTable() {
        if (this.unqNameTable == null) {
            this.unqNameTable = this.buildUnqualifiedNameTable();
        }
        return this.unqNameTable;
    }

    private UnqualifiedNameTable buildUnqualifiedNameTable() {
        UnqualifiedNameTable unqNameTable = new UnqualifiedNameTable();
        if (this.compPaths != null) {
            for (int i = 0; i < this.compPaths.size(); ++i) {
                Set s2 = ((BshClassPath)this.compPaths.get((int)i)).classSource.keySet();
                Iterator it = s2.iterator();
                while (it.hasNext()) {
                    unqNameTable.add((String)it.next());
                }
            }
        }
        Iterator it = this.classSource.keySet().iterator();
        while (it.hasNext()) {
            unqNameTable.add((String)it.next());
        }
        return unqNameTable;
    }

    public String[] getAllNames() {
        this.insureInitialized();
        ArrayList names = new ArrayList();
        for (String pack : this.getPackagesSet()) {
            names.addAll(BshClassPath.removeInnerClassNames(this.getClassesForPackage(pack)));
        }
        if (this.nameCompletionIncludesUnqNames) {
            names.addAll(this.getUnqualifiedNameTable().keySet());
        }
        return names.toArray(new String[0]);
    }

    synchronized void map(URL[] urls2) {
        for (int i = 0; i < urls2.length; ++i) {
            try {
                this.map(urls2[i]);
                continue;
            } catch (IOException e) {
                String s2 = "Error constructing classpath: " + urls2[i] + ": " + e;
                this.errorWhileMapping(s2);
            }
        }
    }

    synchronized void map(URL url) throws IOException {
        String name = url.getFile();
        File f = new File(name);
        if (f.isDirectory()) {
            this.classMapping("Directory " + f.toString());
            this.map(BshClassPath.traverseDirForClasses(f), new DirClassSource(f));
        } else if (BshClassPath.isArchiveFileName(name)) {
            this.classMapping("Archive: " + url);
            this.map(BshClassPath.searchJarForClasses(url), new JarClassSource(url));
        } else {
            String s2 = "Not a classpath component: " + name;
            this.errorWhileMapping(s2);
        }
    }

    private void map(String[] classes, Object source2) {
        for (int i = 0; i < classes.length; ++i) {
            this.mapClass(classes[i], source2);
        }
    }

    private void mapClass(String className, Object source2) {
        String[] sa = BshClassPath.splitClassname(className);
        String pack = sa[0];
        String clas = sa[1];
        HashSet<String> set = (HashSet<String>)this.packageMap.get(pack);
        if (set == null) {
            set = new HashSet<String>();
            this.packageMap.put(pack, set);
        }
        set.add(className);
        Object obj = this.classSource.get(className);
        if (obj == null) {
            this.classSource.put(className, source2);
        }
    }

    private synchronized void reset() {
        this.path = new ArrayList();
        this.compPaths = null;
        this.clearCachedStructures();
    }

    private synchronized void clearCachedStructures() {
        this.mapsInitialized = false;
        this.packageMap = new HashMap();
        this.classSource = new HashMap();
        this.unqNameTable = null;
        this.nameSpaceChanged();
    }

    public void classPathChanged() {
        this.clearCachedStructures();
        this.notifyListeners();
    }

    static String[] traverseDirForClasses(File dir2) throws IOException {
        List list = BshClassPath.traverseDirForClassesAux(dir2, dir2);
        return list.toArray(new String[0]);
    }

    static List traverseDirForClassesAux(File topDir, File dir2) throws IOException {
        ArrayList<String> list = new ArrayList<String>();
        String top = topDir.getAbsolutePath();
        File[] children = dir2.listFiles();
        for (int i = 0; i < children.length; ++i) {
            File child = children[i];
            if (child.isDirectory()) {
                list.addAll(BshClassPath.traverseDirForClassesAux(topDir, child));
                continue;
            }
            String name = child.getAbsolutePath();
            if (!BshClassPath.isClassFileName(name)) continue;
            if (!name.startsWith(top)) {
                throw new IOException("problem parsing paths");
            }
            name = name.substring(top.length() + 1);
            name = BshClassPath.canonicalizeClassName(name);
            list.add(name);
        }
        return list;
    }

    static String[] searchJarForClasses(URL jar) throws IOException {
        ZipEntry ze;
        Vector<String> v = new Vector<String>();
        InputStream in = jar.openStream();
        ZipInputStream zin = new ZipInputStream(in);
        while ((ze = zin.getNextEntry()) != null) {
            String name = ze.getName();
            if (!BshClassPath.isClassFileName(name)) continue;
            v.addElement(BshClassPath.canonicalizeClassName(name));
        }
        zin.close();
        Object[] sa = new String[v.size()];
        v.copyInto(sa);
        return sa;
    }

    public static boolean isClassFileName(String name) {
        return name.toLowerCase().endsWith(".class");
    }

    public static boolean isArchiveFileName(String name) {
        return (name = name.toLowerCase()).endsWith(".jar") || name.endsWith(".zip");
    }

    public static String canonicalizeClassName(String name) {
        String classname = name.replace('/', '.');
        if ((classname = classname.replace('\\', '.')).startsWith("class ")) {
            classname = classname.substring(6);
        }
        if (classname.endsWith(".class")) {
            classname = classname.substring(0, classname.length() - 6);
        }
        return classname;
    }

    public static String[] splitClassname(String classname) {
        String packn;
        String classn;
        int i = (classname = BshClassPath.canonicalizeClassName(classname)).lastIndexOf(".");
        if (i == -1) {
            classn = classname;
            packn = "<unpackaged>";
        } else {
            packn = classname.substring(0, i);
            classn = classname.substring(i + 1);
        }
        return new String[]{packn, classn};
    }

    public static Collection removeInnerClassNames(Collection col) {
        ArrayList list = new ArrayList();
        list.addAll(col);
        Iterator it = list.iterator();
        while (it.hasNext()) {
            String name = (String)it.next();
            if (name.indexOf("$") == -1) continue;
            it.remove();
        }
        return list;
    }

    public static URL[] getUserClassPathComponents() throws ClassPathException {
        if (userClassPathComp != null) {
            return userClassPathComp;
        }
        String cp = System.getProperty("java.class.path");
        String[] paths = StringUtil.split(cp, File.pathSeparator);
        URL[] urls2 = new URL[paths.length];
        try {
            for (int i = 0; i < paths.length; ++i) {
                urls2[i] = new File(new File(paths[i]).getCanonicalPath()).toURL();
            }
        } catch (IOException e) {
            throw new ClassPathException("can't parse class path: " + e);
        }
        userClassPathComp = urls2;
        return urls2;
    }

    public Set getPackagesSet() {
        this.insureInitialized();
        HashSet set = new HashSet();
        set.addAll(this.packageMap.keySet());
        if (this.compPaths != null) {
            for (int i = 0; i < this.compPaths.size(); ++i) {
                set.addAll(((BshClassPath)this.compPaths.get((int)i)).packageMap.keySet());
            }
        }
        return set;
    }

    public void addListener(ClassPathListener l) {
        this.listeners.addElement(new WeakReference<ClassPathListener>(l));
    }

    public void removeListener(ClassPathListener l) {
        this.listeners.removeElement(l);
    }

    void notifyListeners() {
        Enumeration e = this.listeners.elements();
        while (e.hasMoreElements()) {
            WeakReference wr = (WeakReference)e.nextElement();
            ClassPathListener l = (ClassPathListener)wr.get();
            if (l == null) {
                this.listeners.removeElement(wr);
                continue;
            }
            l.classPathChanged();
        }
    }

    public static BshClassPath getUserClassPath() throws ClassPathException {
        if (userClassPath == null) {
            userClassPath = new BshClassPath("User Class Path", BshClassPath.getUserClassPathComponents());
        }
        return userClassPath;
    }

    public static BshClassPath getBootClassPath() throws ClassPathException {
        if (bootClassPath == null) {
            try {
                String rtjar = BshClassPath.getRTJarPath();
                URL url = new File(rtjar).toURL();
                bootClassPath = new BshClassPath("Boot Class Path", new URL[]{url});
            } catch (MalformedURLException e) {
                throw new ClassPathException(" can't find boot jar: " + e);
            }
        }
        return bootClassPath;
    }

    private static String getRTJarPath() {
        String urlString = Class.class.getResource("/java/lang/String.class").toExternalForm();
        if (!urlString.startsWith("jar:file:")) {
            return null;
        }
        int i = urlString.indexOf("!");
        if (i == -1) {
            return null;
        }
        return urlString.substring("jar:file:".length(), i);
    }

    public static void main(String[] args2) throws Exception {
        URL[] urls2 = new URL[args2.length];
        for (int i = 0; i < args2.length; ++i) {
            urls2[i] = new File(args2[i]).toURL();
        }
        BshClassPath bcp = new BshClassPath("Test", urls2);
    }

    public String toString() {
        return "BshClassPath " + this.name + "(" + super.toString() + ") path= " + this.path + "\n" + "compPaths = {" + this.compPaths + " }";
    }

    void nameSpaceChanged() {
        if (this.nameSourceListeners == null) {
            return;
        }
        for (int i = 0; i < this.nameSourceListeners.size(); ++i) {
            ((NameSource.Listener)this.nameSourceListeners.get(i)).nameSourceChanged(this);
        }
    }

    public void addNameSourceListener(NameSource.Listener listener) {
        if (this.nameSourceListeners == null) {
            this.nameSourceListeners = new ArrayList();
        }
        this.nameSourceListeners.add(listener);
    }

    public static void addMappingFeedback(MappingFeedback mf) {
        if (mappingFeedbackListener != null) {
            throw new RuntimeException("Unimplemented: already a listener");
        }
        mappingFeedbackListener = mf;
    }

    void startClassMapping() {
        if (mappingFeedbackListener != null) {
            mappingFeedbackListener.startClassMapping();
        } else {
            System.err.println("Start ClassPath Mapping");
        }
    }

    void classMapping(String msg) {
        if (mappingFeedbackListener != null) {
            mappingFeedbackListener.classMapping(msg);
        } else {
            System.err.println("Mapping: " + msg);
        }
    }

    void errorWhileMapping(String s2) {
        if (mappingFeedbackListener != null) {
            mappingFeedbackListener.errorWhileMapping(s2);
        } else {
            System.err.println(s2);
        }
    }

    void endClassMapping() {
        if (mappingFeedbackListener != null) {
            mappingFeedbackListener.endClassMapping();
        } else {
            System.err.println("End ClassPath Mapping");
        }
    }

    public static interface MappingFeedback {
        public void startClassMapping();

        public void classMapping(String var1);

        public void errorWhileMapping(String var1);

        public void endClassMapping();
    }

    public static class AmbiguousName {
        List list = new ArrayList();

        public void add(String name) {
            this.list.add(name);
        }

        public List get() {
            return this.list;
        }
    }

    static class UnqualifiedNameTable
    extends HashMap {
        UnqualifiedNameTable() {
        }

        void add(String fullname) {
            String name = BshClassPath.splitClassname(fullname)[1];
            Object have = super.get(name);
            if (have == null) {
                super.put(name, fullname);
            } else if (have instanceof AmbiguousName) {
                ((AmbiguousName)have).add(fullname);
            } else {
                AmbiguousName an = new AmbiguousName();
                an.add((String)have);
                an.add(fullname);
                super.put(name, an);
            }
        }
    }

    public static class GeneratedClassSource
    extends ClassSource {
        GeneratedClassSource(byte[] bytecode) {
            this.source = bytecode;
        }

        public byte[] getCode(String className) {
            return (byte[])this.source;
        }
    }

    public static class DirClassSource
    extends ClassSource {
        DirClassSource(File dir2) {
            this.source = dir2;
        }

        public File getDir() {
            return (File)this.source;
        }

        public String toString() {
            return "Dir: " + this.source;
        }

        public byte[] getCode(String className) {
            return DirClassSource.readBytesFromFile(this.getDir(), className);
        }

        public static byte[] readBytesFromFile(File base, String className) {
            byte[] bytes;
            String n = className.replace('.', File.separatorChar) + ".class";
            File file = new File(base, n);
            if (file == null || !file.exists()) {
                return null;
            }
            try {
                FileInputStream fis = new FileInputStream(file);
                DataInputStream dis = new DataInputStream(fis);
                bytes = new byte[(int)file.length()];
                dis.readFully(bytes);
                dis.close();
            } catch (IOException ie) {
                throw new RuntimeException("Couldn't load file: " + file);
            }
            return bytes;
        }
    }

    public static class JarClassSource
    extends ClassSource {
        JarClassSource(URL url) {
            this.source = url;
        }

        public URL getURL() {
            return (URL)this.source;
        }

        public byte[] getCode(String className) {
            throw new Error("Unimplemented");
        }

        public String toString() {
            return "Jar: " + this.source;
        }
    }

    public static abstract class ClassSource {
        Object source;

        abstract byte[] getCode(String var1);
    }
}

