/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package bsh.collection;

import bsh.BshIterator;
import java.util.Collection;
import java.util.Iterator;

public class CollectionIterator
implements BshIterator {
    private Iterator iterator;

    public CollectionIterator(Object iterateOverMe) {
        this.iterator = this.createIterator(iterateOverMe);
    }

    protected Iterator createIterator(Object iterateOverMe) {
        if (iterateOverMe == null) {
            throw new NullPointerException("Object arguments passed to the CollectionIterator constructor cannot be null.");
        }
        if (iterateOverMe instanceof Iterator) {
            return (Iterator)iterateOverMe;
        }
        if (iterateOverMe instanceof Collection) {
            return ((Collection)iterateOverMe).iterator();
        }
        throw new IllegalArgumentException("Cannot enumerate object of type " + iterateOverMe.getClass());
    }

    public Object next() {
        return this.iterator.next();
    }

    public boolean hasNext() {
        return this.iterator.hasNext();
    }
}

