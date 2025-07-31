/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.util;

import com.alibaba.fastjson.util.IOUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class ServiceLoader {
    private static final String PREFIX = "META-INF/services/";
    private static final Set<String> loadedUrls = new HashSet<String>();

    public static <T> Set<T> load(Class<T> clazz, ClassLoader classLoader) {
        if (classLoader == null) {
            return Collections.emptySet();
        }
        HashSet services = new HashSet();
        String className = clazz.getName();
        String path = PREFIX + className;
        HashSet<String> serviceNames = new HashSet<String>();
        try {
            Enumeration<URL> urls2 = classLoader.getResources(path);
            while (urls2.hasMoreElements()) {
                URL url = urls2.nextElement();
                if (loadedUrls.contains(url.toString())) continue;
                ServiceLoader.load(url, serviceNames);
                loadedUrls.add(url.toString());
            }
        } catch (Throwable throwable) {
            // empty catch block
        }
        for (String serviceName : serviceNames) {
            try {
                Class<?> serviceClass = classLoader.loadClass(serviceName);
                Object service = serviceClass.newInstance();
                services.add(service);
            } catch (Exception exception) {}
        }
        return services;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void load(URL url, Set<String> set) throws IOException {
        InputStream is = null;
        BufferedReader reader = null;
        try {
            String line;
            is = url.openStream();
            reader = new BufferedReader(new InputStreamReader(is, "utf-8"));
            while ((line = reader.readLine()) != null) {
                int ci = line.indexOf(35);
                if (ci >= 0) {
                    line = line.substring(0, ci);
                }
                if ((line = line.trim()).length() == 0) continue;
                set.add(line);
            }
        } catch (Throwable throwable) {
            IOUtils.close(reader);
            IOUtils.close(is);
            throw throwable;
        }
        IOUtils.close(reader);
        IOUtils.close(is);
    }
}

