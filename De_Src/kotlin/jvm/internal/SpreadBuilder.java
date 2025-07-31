/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.jvm.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

public class SpreadBuilder {
    private final ArrayList<Object> list;

    public SpreadBuilder(int size) {
        this.list = new ArrayList(size);
    }

    public void addSpread(Object container) {
        if (container == null) {
            return;
        }
        if (container instanceof Object[]) {
            Object[] array = (Object[])container;
            if (array.length > 0) {
                this.list.ensureCapacity(this.list.size() + array.length);
                Collections.addAll(this.list, array);
            }
        } else if (container instanceof Collection) {
            this.list.addAll((Collection)container);
        } else if (container instanceof Iterable) {
            for (Object element : (Iterable)container) {
                this.list.add(element);
            }
        } else if (container instanceof Iterator) {
            Iterator iterator2 = (Iterator)container;
            while (iterator2.hasNext()) {
                this.list.add(iterator2.next());
            }
        } else {
            throw new UnsupportedOperationException("Don't know how to spread " + container.getClass());
        }
    }

    public int size() {
        return this.list.size();
    }

    public void add(Object element) {
        this.list.add(element);
    }

    public Object[] toArray(Object[] a) {
        return this.list.toArray(a);
    }
}

