/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.asm;

import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.asm.MethodCollector;
import com.alibaba.fastjson.asm.Type;
import com.alibaba.fastjson.util.ASMUtils;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class TypeCollector {
    private static String JSONType = ASMUtils.desc(JSONType.class);
    private static final Map<String, String> primitives = new HashMap<String, String>(){
        {
            this.put("int", "I");
            this.put("boolean", "Z");
            this.put("byte", "B");
            this.put("char", "C");
            this.put("short", "S");
            this.put("float", "F");
            this.put("long", "J");
            this.put("double", "D");
        }
    };
    private final String methodName;
    private final Class<?>[] parameterTypes;
    protected MethodCollector collector;
    protected boolean jsonType;

    public TypeCollector(String methodName, Class<?>[] parameterTypes) {
        this.methodName = methodName;
        this.parameterTypes = parameterTypes;
        this.collector = null;
    }

    protected MethodCollector visitMethod(int access, String name, String desc) {
        if (this.collector != null) {
            return null;
        }
        if (!name.equals(this.methodName)) {
            return null;
        }
        Type[] argTypes = Type.getArgumentTypes(desc);
        int longOrDoubleQuantity = 0;
        for (Type t : argTypes) {
            String className = t.getClassName();
            if (!className.equals("long") && !className.equals("double")) continue;
            ++longOrDoubleQuantity;
        }
        if (argTypes.length != this.parameterTypes.length) {
            return null;
        }
        for (int i = 0; i < argTypes.length; ++i) {
            if (this.correctTypeName(argTypes[i], this.parameterTypes[i].getName())) continue;
            return null;
        }
        this.collector = new MethodCollector(Modifier.isStatic(access) ? 0 : 1, argTypes.length + longOrDoubleQuantity);
        return this.collector;
    }

    public void visitAnnotation(String desc) {
        if (JSONType.equals(desc)) {
            this.jsonType = true;
        }
    }

    private boolean correctTypeName(Type type, String paramTypeName) {
        String s2 = type.getClassName();
        StringBuilder braces = new StringBuilder();
        while (s2.endsWith("[]")) {
            braces.append('[');
            s2 = s2.substring(0, s2.length() - 2);
        }
        if (braces.length() != 0) {
            s2 = primitives.containsKey(s2) ? braces.append(primitives.get(s2)).toString() : braces.append('L').append(s2).append(';').toString();
        }
        return s2.equals(paramTypeName);
    }

    public String[] getParameterNamesForMethod() {
        if (this.collector == null || !this.collector.debugInfoPresent) {
            return new String[0];
        }
        return this.collector.getResult().split(",");
    }

    public boolean matched() {
        return this.collector != null;
    }

    public boolean hasJsonType() {
        return this.jsonType;
    }
}

