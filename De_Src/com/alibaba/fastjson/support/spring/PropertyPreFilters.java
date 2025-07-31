/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.support.spring;

import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import java.util.ArrayList;
import java.util.List;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class PropertyPreFilters {
    private List<MySimplePropertyPreFilter> filters = new ArrayList<MySimplePropertyPreFilter>();

    public MySimplePropertyPreFilter addFilter() {
        MySimplePropertyPreFilter filter = new MySimplePropertyPreFilter();
        this.filters.add(filter);
        return filter;
    }

    public MySimplePropertyPreFilter addFilter(String ... properties) {
        MySimplePropertyPreFilter filter = new MySimplePropertyPreFilter(properties);
        this.filters.add(filter);
        return filter;
    }

    public MySimplePropertyPreFilter addFilter(Class<?> clazz, String ... properties) {
        MySimplePropertyPreFilter filter = new MySimplePropertyPreFilter(clazz, properties);
        this.filters.add(filter);
        return filter;
    }

    public List<MySimplePropertyPreFilter> getFilters() {
        return this.filters;
    }

    public void setFilters(List<MySimplePropertyPreFilter> filters) {
        this.filters = filters;
    }

    public MySimplePropertyPreFilter[] toFilters() {
        return this.filters.toArray(new MySimplePropertyPreFilter[0]);
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public class MySimplePropertyPreFilter
    extends SimplePropertyPreFilter {
        public MySimplePropertyPreFilter() {
            super(new String[0]);
        }

        public MySimplePropertyPreFilter(String ... properties) {
            super(properties);
        }

        public MySimplePropertyPreFilter(Class<?> clazz, String ... properties) {
            super(clazz, properties);
        }

        public MySimplePropertyPreFilter addExcludes(String ... filters) {
            for (int i = 0; i < filters.length; ++i) {
                this.getExcludes().add(filters[i]);
            }
            return this;
        }

        public MySimplePropertyPreFilter addIncludes(String ... filters) {
            for (int i = 0; i < filters.length; ++i) {
                this.getIncludes().add(filters[i]);
            }
            return this;
        }
    }
}

