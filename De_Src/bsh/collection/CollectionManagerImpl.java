/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package bsh.collection;

import bsh.BshIterator;
import bsh.CollectionManager;
import bsh.collection.CollectionIterator;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

public class CollectionManagerImpl
extends CollectionManager {
    public BshIterator getBshIterator(Object obj) throws IllegalArgumentException {
        if (obj instanceof Collection || obj instanceof Iterator) {
            return new CollectionIterator(obj);
        }
        return new CollectionManager.BasicBshIterator(obj);
    }

    public boolean isMap(Object obj) {
        if (obj instanceof Map) {
            return true;
        }
        return super.isMap(obj);
    }

    public Object getFromMap(Object map, Object key) {
        return ((Map)map).get(key);
    }

    public Object putInMap(Object map, Object key, Object value) {
        return ((Map)map).put(key, value);
    }
}

