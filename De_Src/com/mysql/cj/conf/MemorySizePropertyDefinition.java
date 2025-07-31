/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.conf;

import com.mysql.cj.conf.IntegerPropertyDefinition;
import com.mysql.cj.conf.MemorySizeProperty;
import com.mysql.cj.conf.RuntimeProperty;
import com.mysql.cj.exceptions.ExceptionInterceptor;
import com.mysql.cj.util.StringUtils;

public class MemorySizePropertyDefinition
extends IntegerPropertyDefinition {
    private static final long serialVersionUID = -6878680905514177949L;

    public MemorySizePropertyDefinition(String name, String alias, int defaultValue, boolean isRuntimeModifiable, String description, String sinceVersion, String category, int orderInCategory) {
        super(name, alias, defaultValue, isRuntimeModifiable, description, sinceVersion, category, orderInCategory);
    }

    public MemorySizePropertyDefinition(String name, String alias, int defaultValue, boolean isRuntimeModifiable, String description, String sinceVersion, String category, int orderInCategory, int lowerBound, int upperBound) {
        super(name, alias, defaultValue, isRuntimeModifiable, description, sinceVersion, category, orderInCategory, lowerBound, upperBound);
    }

    @Override
    public Integer parseObject(String value, ExceptionInterceptor exceptionInterceptor) {
        this.multiplier = 1;
        if (value.endsWith("k") || value.endsWith("K") || value.endsWith("kb") || value.endsWith("Kb") || value.endsWith("kB") || value.endsWith("KB")) {
            this.multiplier = 1024;
            int indexOfK = StringUtils.indexOfIgnoreCase(value, "k");
            value = value.substring(0, indexOfK);
        } else if (value.endsWith("m") || value.endsWith("M") || value.endsWith("mb") || value.endsWith("Mb") || value.endsWith("mB") || value.endsWith("MB")) {
            this.multiplier = 0x100000;
            int indexOfM = StringUtils.indexOfIgnoreCase(value, "m");
            value = value.substring(0, indexOfM);
        } else if (value.endsWith("g") || value.endsWith("G") || value.endsWith("gb") || value.endsWith("Gb") || value.endsWith("gB") || value.endsWith("GB")) {
            this.multiplier = 0x40000000;
            int indexOfG = StringUtils.indexOfIgnoreCase(value, "g");
            value = value.substring(0, indexOfG);
        }
        return super.parseObject(value, exceptionInterceptor);
    }

    @Override
    public RuntimeProperty<Integer> createRuntimeProperty() {
        return new MemorySizeProperty(this);
    }
}

