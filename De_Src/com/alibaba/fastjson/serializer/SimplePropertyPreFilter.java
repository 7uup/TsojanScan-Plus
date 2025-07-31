/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.PropertyPreFilter;
import com.alibaba.fastjson.serializer.SerialContext;
import java.util.HashSet;
import java.util.Set;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class SimplePropertyPreFilter
implements PropertyPreFilter {
    private final Class<?> clazz;
    private final Set<String> includes = new HashSet<String>();
    private final Set<String> excludes = new HashSet<String>();
    private int maxLevel = 0;

    public SimplePropertyPreFilter(String ... properties) {
        this((Class<?>)null, properties);
    }

    public SimplePropertyPreFilter(Class<?> clazz, String ... properties) {
        this.clazz = clazz;
        for (String item : properties) {
            if (item == null) continue;
            this.includes.add(item);
        }
    }

    public int getMaxLevel() {
        return this.maxLevel;
    }

    public void setMaxLevel(int maxLevel) {
        this.maxLevel = maxLevel;
    }

    public Class<?> getClazz() {
        return this.clazz;
    }

    public Set<String> getIncludes() {
        return this.includes;
    }

    public Set<String> getExcludes() {
        return this.excludes;
    }

    @Override
    public boolean apply(JSONSerializer serializer, Object source2, String name) {
        if (source2 == null) {
            return true;
        }
        if (this.clazz != null && !this.clazz.isInstance(source2)) {
            return true;
        }
        if (this.excludes.contains(name)) {
            return false;
        }
        if (this.maxLevel > 0) {
            int level = 0;
            SerialContext context = serializer.context;
            while (context != null) {
                if (++level > this.maxLevel) {
                    return false;
                }
                context = context.parent;
            }
        }
        return this.includes.size() == 0 || this.includes.contains(name);
    }
}

