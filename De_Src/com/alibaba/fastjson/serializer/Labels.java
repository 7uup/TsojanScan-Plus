/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.serializer.LabelFilter;
import java.util.Arrays;

public class Labels {
    public static LabelFilter includes(String ... views) {
        return new DefaultLabelFilter(views, null);
    }

    public static LabelFilter excludes(String ... views) {
        return new DefaultLabelFilter(null, views);
    }

    private static class DefaultLabelFilter
    implements LabelFilter {
        private String[] includes;
        private String[] excludes;

        public DefaultLabelFilter(String[] includes, String[] excludes) {
            if (includes != null) {
                this.includes = new String[includes.length];
                System.arraycopy(includes, 0, this.includes, 0, includes.length);
                Arrays.sort(this.includes);
            }
            if (excludes != null) {
                this.excludes = new String[excludes.length];
                System.arraycopy(excludes, 0, this.excludes, 0, excludes.length);
                Arrays.sort(this.excludes);
            }
        }

        public boolean apply(String label) {
            if (this.excludes != null) {
                return Arrays.binarySearch(this.excludes, label) < 0;
            }
            return this.includes != null && Arrays.binarySearch(this.includes, label) >= 0;
        }
    }
}

