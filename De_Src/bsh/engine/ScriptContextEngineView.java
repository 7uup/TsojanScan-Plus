/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package bsh.engine;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.script.ScriptContext;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class ScriptContextEngineView
implements Map<String, Object> {
    ScriptContext context;

    public ScriptContextEngineView(ScriptContext context) {
        this.context = context;
    }

    @Override
    public int size() {
        return this.totalKeySet().size();
    }

    @Override
    public boolean isEmpty() {
        return this.totalKeySet().size() == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        return this.context.getAttribute((String)key) != null;
    }

    @Override
    public boolean containsValue(Object value) {
        Set values2 = this.totalValueSet();
        return values2.contains(value);
    }

    @Override
    public Object get(Object key) {
        return this.context.getAttribute((String)key);
    }

    @Override
    public Object put(String key, Object value) {
        Object oldValue = this.context.getAttribute(key, 100);
        this.context.setAttribute(key, value, 100);
        return oldValue;
    }

    @Override
    public void putAll(Map<? extends String, ? extends Object> t) {
        this.context.getBindings(100).putAll(t);
    }

    @Override
    public Object remove(Object okey) {
        String key = (String)okey;
        Object oldValue = this.context.getAttribute(key, 100);
        this.context.removeAttribute(key, 100);
        return oldValue;
    }

    @Override
    public void clear() {
        this.context.getBindings(100).clear();
    }

    @Override
    public Set keySet() {
        return this.totalKeySet();
    }

    @Override
    public Collection values() {
        return this.totalValueSet();
    }

    @Override
    public Set<Map.Entry<String, Object>> entrySet() {
        throw new Error("unimplemented");
    }

    private Set totalKeySet() {
        HashSet keys2 = new HashSet();
        List<Integer> scopes = this.context.getScopes();
        for (int i : scopes) {
            keys2.addAll(this.context.getBindings(i).keySet());
        }
        return Collections.unmodifiableSet(keys2);
    }

    private Set totalValueSet() {
        HashSet values2 = new HashSet();
        List<Integer> scopes = this.context.getScopes();
        for (int i : scopes) {
            values2.addAll(this.context.getBindings(i).values());
        }
        return Collections.unmodifiableSet(values2);
    }
}

