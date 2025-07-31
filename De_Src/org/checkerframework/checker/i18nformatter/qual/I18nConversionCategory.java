/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.checkerframework.checker.i18nformatter.qual;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public enum I18nConversionCategory {
    UNUSED(null, null),
    GENERAL(null, null),
    DATE(new Class[]{Date.class, Number.class}, new String[]{"date", "time"}),
    NUMBER(new Class[]{Number.class}, new String[]{"number", "choice"});

    public final Class<? extends Object>[] types;
    public final String[] strings;
    static I18nConversionCategory[] namedCategories;

    private I18nConversionCategory(Class<? extends Object>[] types, String[] strings) {
        this.types = types;
        this.strings = strings;
    }

    public static I18nConversionCategory stringToI18nConversionCategory(String string) {
        string = string.toLowerCase();
        for (I18nConversionCategory v : namedCategories) {
            for (String s2 : v.strings) {
                if (!s2.equals(string)) continue;
                return v;
            }
        }
        throw new IllegalArgumentException("Invalid format type " + string);
    }

    private static <E> Set<E> arrayToSet(E[] a) {
        return new HashSet<E>(Arrays.asList(a));
    }

    public static boolean isSubsetOf(I18nConversionCategory a, I18nConversionCategory b) {
        return I18nConversionCategory.intersect(a, b) == a;
    }

    public static I18nConversionCategory intersect(I18nConversionCategory a, I18nConversionCategory b) {
        if (a == UNUSED) {
            return b;
        }
        if (b == UNUSED) {
            return a;
        }
        if (a == GENERAL) {
            return b;
        }
        if (b == GENERAL) {
            return a;
        }
        Set<Class<? extends Object>> as = I18nConversionCategory.arrayToSet(a.types);
        Set<Class<? extends Object>> bs = I18nConversionCategory.arrayToSet(b.types);
        as.retainAll(bs);
        for (I18nConversionCategory v : new I18nConversionCategory[]{DATE, NUMBER}) {
            Set<Class<? extends Object>> vs = I18nConversionCategory.arrayToSet(v.types);
            if (!vs.equals(as)) continue;
            return v;
        }
        throw new RuntimeException();
    }

    public static I18nConversionCategory union(I18nConversionCategory a, I18nConversionCategory b) {
        if (a == UNUSED || b == UNUSED) {
            return UNUSED;
        }
        if (a == GENERAL || b == GENERAL) {
            return GENERAL;
        }
        if (a == DATE || b == DATE) {
            return DATE;
        }
        return NUMBER;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(this.name());
        if (this.types == null) {
            sb.append(" conversion category (all types)");
        } else {
            sb.append(" conversion category (one of: ");
            boolean first = true;
            for (Class<? extends Object> cls : this.types) {
                if (!first) {
                    sb.append(", ");
                }
                sb.append(cls.getCanonicalName());
                first = false;
            }
            sb.append(")");
        }
        return sb.toString();
    }

    static {
        namedCategories = new I18nConversionCategory[]{DATE, NUMBER};
    }
}

