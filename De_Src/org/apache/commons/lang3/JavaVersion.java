/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3;

import org.apache.commons.lang3.math.NumberUtils;

public enum JavaVersion {
    JAVA_0_9(1.5f, "0.9"),
    JAVA_1_1(1.1f, "1.1"),
    JAVA_1_2(1.2f, "1.2"),
    JAVA_1_3(1.3f, "1.3"),
    JAVA_1_4(1.4f, "1.4"),
    JAVA_1_5(1.5f, "1.5"),
    JAVA_1_6(1.6f, "1.6"),
    JAVA_1_7(1.7f, "1.7"),
    JAVA_1_8(1.8f, "1.8"),
    JAVA_1_9(9.0f, "9"),
    JAVA_9(9.0f, "9"),
    JAVA_10(10.0f, "10"),
    JAVA_11(11.0f, "11"),
    JAVA_RECENT(JavaVersion.maxVersion(), Float.toString(JavaVersion.maxVersion()));

    private final float value;
    private final String name;

    private JavaVersion(float value, String name) {
        this.value = value;
        this.name = name;
    }

    public boolean atLeast(JavaVersion requiredVersion) {
        return this.value >= requiredVersion.value;
    }

    static JavaVersion getJavaVersion(String nom) {
        return JavaVersion.get(nom);
    }

    static JavaVersion get(String nom) {
        int end;
        int firstComma;
        if ("0.9".equals(nom)) {
            return JAVA_0_9;
        }
        if ("1.1".equals(nom)) {
            return JAVA_1_1;
        }
        if ("1.2".equals(nom)) {
            return JAVA_1_2;
        }
        if ("1.3".equals(nom)) {
            return JAVA_1_3;
        }
        if ("1.4".equals(nom)) {
            return JAVA_1_4;
        }
        if ("1.5".equals(nom)) {
            return JAVA_1_5;
        }
        if ("1.6".equals(nom)) {
            return JAVA_1_6;
        }
        if ("1.7".equals(nom)) {
            return JAVA_1_7;
        }
        if ("1.8".equals(nom)) {
            return JAVA_1_8;
        }
        if ("9".equals(nom)) {
            return JAVA_9;
        }
        if ("10".equals(nom)) {
            return JAVA_10;
        }
        if ("11".equals(nom)) {
            return JAVA_11;
        }
        if (nom == null) {
            return null;
        }
        float v = JavaVersion.toFloatVersion(nom);
        if ((double)v - 1.0 < 1.0 ? Float.parseFloat(nom.substring((firstComma = Math.max(nom.indexOf(46), nom.indexOf(44))) + 1, end = Math.max(nom.length(), nom.indexOf(44, firstComma)))) > 0.9f : v > 10.0f) {
            return JAVA_RECENT;
        }
        return null;
    }

    public String toString() {
        return this.name;
    }

    private static float maxVersion() {
        float v = JavaVersion.toFloatVersion(System.getProperty("java.specification.version", "99.0"));
        if (v > 0.0f) {
            return v;
        }
        return 99.0f;
    }

    private static float toFloatVersion(String value) {
        int defaultReturnValue = -1;
        if (value.contains(".")) {
            String[] toParse = value.split("\\.");
            if (toParse.length >= 2) {
                return NumberUtils.toFloat(toParse[0] + '.' + toParse[1], -1.0f);
            }
        } else {
            return NumberUtils.toFloat(value, -1.0f);
        }
        return -1.0f;
    }
}

