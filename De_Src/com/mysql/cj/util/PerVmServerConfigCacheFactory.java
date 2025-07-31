/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.util;

import com.mysql.cj.CacheAdapter;
import com.mysql.cj.CacheAdapterFactory;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class PerVmServerConfigCacheFactory
implements CacheAdapterFactory<String, Map<String, String>> {
    static final ConcurrentHashMap<String, Map<String, String>> serverConfigByUrl = new ConcurrentHashMap();
    private static final CacheAdapter<String, Map<String, String>> serverConfigCache = new CacheAdapter<String, Map<String, String>>(){

        @Override
        public Map<String, String> get(String key) {
            return serverConfigByUrl.get(key);
        }

        @Override
        public void put(String key, Map<String, String> value) {
            serverConfigByUrl.putIfAbsent(key, value);
        }

        @Override
        public void invalidate(String key) {
            serverConfigByUrl.remove(key);
        }

        @Override
        public void invalidateAll(Set<String> keys2) {
            for (String key : keys2) {
                serverConfigByUrl.remove(key);
            }
        }

        @Override
        public void invalidateAll() {
            serverConfigByUrl.clear();
        }
    };

    @Override
    public CacheAdapter<String, Map<String, String>> getInstance(Object syncMutex, String url, int cacheMaxSize, int maxKeySize) {
        return serverConfigCache;
    }
}

