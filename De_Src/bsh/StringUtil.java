/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package bsh;

import bsh.Reflect;
import java.util.StringTokenizer;
import java.util.Vector;

public class StringUtil {
    public static String[] split(String s2, String delim) {
        Vector<String> v = new Vector<String>();
        StringTokenizer st = new StringTokenizer(s2, delim);
        while (st.hasMoreTokens()) {
            v.addElement(st.nextToken());
        }
        Object[] sa = new String[v.size()];
        v.copyInto(sa);
        return sa;
    }

    public static String[] bubbleSort(String[] in) {
        Vector<String> v = new Vector<String>();
        for (int i = 0; i < in.length; ++i) {
            v.addElement(in[i]);
        }
        int n = v.size();
        boolean swap = true;
        while (swap) {
            swap = false;
            for (int i = 0; i < n - 1; ++i) {
                if (((String)v.elementAt(i)).compareTo((String)v.elementAt(i + 1)) <= 0) continue;
                String tmp = (String)v.elementAt(i + 1);
                v.removeElementAt(i + 1);
                v.insertElementAt(tmp, i);
                swap = true;
            }
        }
        Object[] out = new String[n];
        v.copyInto(out);
        return out;
    }

    public static String maxCommonPrefix(String one, String two) {
        int i = 0;
        while (one.regionMatches(0, two, 0, i)) {
            ++i;
        }
        return one.substring(0, i - 1);
    }

    public static String methodString(String name, Class[] types) {
        StringBuffer sb = new StringBuffer(name + "(");
        if (types.length > 0) {
            sb.append(" ");
        }
        for (int i = 0; i < types.length; ++i) {
            Class c = types[i];
            sb.append((c == null ? "null" : c.getName()) + (i < types.length - 1 ? ", " : " "));
        }
        sb.append(")");
        return sb.toString();
    }

    public static String normalizeClassName(Class type) {
        return Reflect.normalizeClassName(type);
    }
}

