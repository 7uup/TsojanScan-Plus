/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.serializer;

public class SerialContext {
    public final SerialContext parent;
    public final Object object;
    public final Object fieldName;
    public final int features;

    public SerialContext(SerialContext parent, Object object, Object fieldName, int features, int fieldFeatures) {
        this.parent = parent;
        this.object = object;
        this.fieldName = fieldName;
        this.features = features;
    }

    public String toString() {
        if (this.parent == null) {
            return "$";
        }
        StringBuilder buf = new StringBuilder();
        this.toString(buf);
        return buf.toString();
    }

    protected void toString(StringBuilder buf) {
        if (this.parent == null) {
            buf.append('$');
        } else {
            this.parent.toString(buf);
            if (this.fieldName == null) {
                buf.append(".null");
            } else if (this.fieldName instanceof Integer) {
                buf.append('[');
                buf.append((Integer)this.fieldName);
                buf.append(']');
            } else {
                char ch;
                int i;
                buf.append('.');
                String fieldName = this.fieldName.toString();
                boolean special = false;
                for (i = 0; i < fieldName.length(); ++i) {
                    ch = fieldName.charAt(i);
                    if (ch >= '0' && ch <= '9' || ch >= 'A' && ch <= 'Z' || ch >= 'a' && ch <= 'z' || ch > '\u0080') continue;
                    special = true;
                    break;
                }
                if (special) {
                    for (i = 0; i < fieldName.length(); ++i) {
                        ch = fieldName.charAt(i);
                        if (ch == '\\') {
                            buf.append('\\');
                            buf.append('\\');
                            buf.append('\\');
                        } else {
                            if (ch >= '0' && ch <= '9' || ch >= 'A' && ch <= 'Z' || ch >= 'a' && ch <= 'z' || ch > '\u0080') {
                                buf.append(ch);
                                continue;
                            }
                            if (ch == '\"') {
                                buf.append('\\');
                                buf.append('\\');
                                buf.append('\\');
                            } else {
                                buf.append('\\');
                                buf.append('\\');
                            }
                        }
                        buf.append(ch);
                    }
                } else {
                    buf.append(fieldName);
                }
            }
        }
    }

    public SerialContext getParent() {
        return this.parent;
    }

    public Object getObject() {
        return this.object;
    }

    public Object getFieldName() {
        return this.fieldName;
    }

    public String getPath() {
        return this.toString();
    }
}

