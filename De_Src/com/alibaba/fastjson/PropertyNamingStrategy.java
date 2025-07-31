/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public enum PropertyNamingStrategy {
    CamelCase,
    PascalCase,
    SnakeCase,
    KebabCase,
    NoChange,
    NeverUseThisValueExceptDefaultValue;


    public String translate(String propertyName) {
        switch (this) {
            case SnakeCase: {
                StringBuilder buf = new StringBuilder();
                for (int i = 0; i < propertyName.length(); ++i) {
                    char ch = propertyName.charAt(i);
                    if (ch >= 'A' && ch <= 'Z') {
                        char ch_ucase = (char)(ch + 32);
                        if (i > 0) {
                            buf.append('_');
                        }
                        buf.append(ch_ucase);
                        continue;
                    }
                    buf.append(ch);
                }
                return buf.toString();
            }
            case KebabCase: {
                StringBuilder buf = new StringBuilder();
                for (int i = 0; i < propertyName.length(); ++i) {
                    char ch = propertyName.charAt(i);
                    if (ch >= 'A' && ch <= 'Z') {
                        char ch_ucase = (char)(ch + 32);
                        if (i > 0) {
                            buf.append('-');
                        }
                        buf.append(ch_ucase);
                        continue;
                    }
                    buf.append(ch);
                }
                return buf.toString();
            }
            case PascalCase: {
                char ch = propertyName.charAt(0);
                if (ch >= 'a' && ch <= 'z') {
                    char[] chars = propertyName.toCharArray();
                    chars[0] = (char)(chars[0] - 32);
                    return new String(chars);
                }
                return propertyName;
            }
            case CamelCase: {
                char ch = propertyName.charAt(0);
                if (ch >= 'A' && ch <= 'Z') {
                    char[] chars = propertyName.toCharArray();
                    chars[0] = (char)(chars[0] + 32);
                    return new String(chars);
                }
                return propertyName;
            }
        }
        return propertyName;
    }
}

