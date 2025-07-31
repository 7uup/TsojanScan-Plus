/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package bsh;

import bsh.UtilEvalError;
import java.util.Hashtable;

public class Capabilities {
    private static boolean accessibility = false;
    private static Hashtable classes = new Hashtable();

    public static boolean haveSwing() {
        return Capabilities.classExists("javax.swing.JButton");
    }

    public static boolean canGenerateInterfaces() {
        return Capabilities.classExists("java.lang.reflect.Proxy");
    }

    public static boolean haveAccessibility() {
        return accessibility;
    }

    public static void setAccessibility(boolean b) throws Unavailable {
        if (!b) {
            accessibility = false;
            return;
        }
        if (!Capabilities.classExists("java.lang.reflect.AccessibleObject") || !Capabilities.classExists("bsh.reflect.ReflectManagerImpl")) {
            throw new Unavailable("Accessibility unavailable");
        }
        try {
            String.class.getDeclaredMethods();
        } catch (SecurityException e) {
            throw new Unavailable("Accessibility unavailable: " + e);
        }
        accessibility = true;
    }

    public static boolean classExists(String name) {
        Object c = classes.get(name);
        if (c == null) {
            try {
                c = Class.forName(name);
            } catch (ClassNotFoundException classNotFoundException) {
                // empty catch block
            }
            if (c != null) {
                classes.put(c, "unused");
            }
        }
        return c != null;
    }

    public static class Unavailable
    extends UtilEvalError {
        public Unavailable(String s2) {
            super(s2);
        }
    }
}

