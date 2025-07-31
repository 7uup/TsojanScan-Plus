/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.util;

import com.alibaba.fastjson.util.BiFunction;
import com.alibaba.fastjson.util.Function;
import java.util.concurrent.Callable;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class ModuleUtil {
    private static boolean hasJavaSql = false;

    public static <T> T callWhenHasJavaSql(Callable<T> callable) {
        if (hasJavaSql) {
            try {
                return callable.call();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    public static <ARG, T> T callWhenHasJavaSql(Function<ARG, T> callable, ARG arg) {
        if (hasJavaSql) {
            return callable.apply(arg);
        }
        return null;
    }

    public static <T, U, R> R callWhenHasJavaSql(BiFunction<T, U, R> callable, T t, U u) {
        if (hasJavaSql) {
            return callable.apply(t, u);
        }
        return null;
    }

    static {
        try {
            Class.forName("java.sql.Time");
            hasJavaSql = true;
        } catch (Throwable e) {
            hasJavaSql = false;
        }
    }
}

