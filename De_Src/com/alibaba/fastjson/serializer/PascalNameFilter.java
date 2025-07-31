/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.serializer.NameFilter;

public class PascalNameFilter
implements NameFilter {
    public String process(Object source2, String name, Object value) {
        if (name == null || name.length() == 0) {
            return name;
        }
        char[] chars = name.toCharArray();
        chars[0] = Character.toUpperCase(chars[0]);
        String pascalName = new String(chars);
        return pascalName;
    }
}

