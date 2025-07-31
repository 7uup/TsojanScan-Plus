/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package bsh.classpath;

import bsh.BshClassManager;
import bsh.classpath.BshClassLoader;
import bsh.classpath.BshClassPath;
import java.util.HashMap;

public class DiscreteFilesClassLoader
extends BshClassLoader {
    ClassSourceMap map;

    public DiscreteFilesClassLoader(BshClassManager classManager, ClassSourceMap map) {
        super(classManager);
        this.map = map;
    }

    public Class findClass(String name) throws ClassNotFoundException {
        BshClassPath.ClassSource source2 = this.map.get(name);
        if (source2 != null) {
            byte[] code = source2.getCode(name);
            return this.defineClass(name, code, 0, code.length);
        }
        return super.findClass(name);
    }

    public String toString() {
        return super.toString() + "for files: " + this.map;
    }

    public static class ClassSourceMap
    extends HashMap {
        public void put(String name, BshClassPath.ClassSource source2) {
            super.put(name, source2);
        }

        public BshClassPath.ClassSource get(String name) {
            return (BshClassPath.ClassSource)super.get(name);
        }
    }
}

