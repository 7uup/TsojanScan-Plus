/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package bsh;

import bsh.BshIterator;
import bsh.Capabilities;
import bsh.Interpreter;
import java.lang.reflect.Array;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

public class CollectionManager {
    private static CollectionManager manager;

    public static synchronized CollectionManager getCollectionManager() {
        if (manager == null && Capabilities.classExists("java.util.Collection")) {
            try {
                Class<?> clas = Class.forName("bsh.collection.CollectionManagerImpl");
                manager = (CollectionManager)clas.newInstance();
            } catch (Exception e) {
                Interpreter.debug("unable to load CollectionManagerImpl: " + e);
            }
        }
        if (manager == null) {
            manager = new CollectionManager();
        }
        return manager;
    }

    public boolean isBshIterable(Object obj) {
        try {
            this.getBshIterator(obj);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public BshIterator getBshIterator(Object obj) throws IllegalArgumentException {
        return new BasicBshIterator(obj);
    }

    public boolean isMap(Object obj) {
        return obj instanceof Hashtable;
    }

    public Object getFromMap(Object map, Object key) {
        return ((Hashtable)map).get(key);
    }

    public Object putInMap(Object map, Object key, Object value) {
        return ((Hashtable)map).put(key, value);
    }

    public static class BasicBshIterator
    implements BshIterator {
        Enumeration enumeration;

        public BasicBshIterator(Object iterateOverMe) {
            this.enumeration = this.createEnumeration(iterateOverMe);
        }

        protected Enumeration createEnumeration(Object iterateOverMe) {
            if (iterateOverMe == null) {
                throw new NullPointerException("Object arguments passed to the BasicBshIterator constructor cannot be null.");
            }
            if (iterateOverMe instanceof Enumeration) {
                return (Enumeration)iterateOverMe;
            }
            if (iterateOverMe instanceof Vector) {
                return ((Vector)iterateOverMe).elements();
            }
            if (iterateOverMe.getClass().isArray()) {
                final Object array = iterateOverMe;
                return new Enumeration(){
                    int index = 0;
                    int length = Array.getLength(array);

                    public Object nextElement() {
                        return Array.get(array, this.index++);
                    }

                    public boolean hasMoreElements() {
                        return this.index < this.length;
                    }
                };
            }
            if (iterateOverMe instanceof String) {
                return this.createEnumeration(((String)iterateOverMe).toCharArray());
            }
            if (iterateOverMe instanceof StringBuffer) {
                return this.createEnumeration(iterateOverMe.toString().toCharArray());
            }
            throw new IllegalArgumentException("Cannot enumerate object of type " + iterateOverMe.getClass());
        }

        public Object next() {
            return this.enumeration.nextElement();
        }

        public boolean hasNext() {
            return this.enumeration.hasMoreElements();
        }
    }
}

