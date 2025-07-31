/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package bsh;

import bsh.InterpreterError;
import bsh.LHS;
import bsh.Modifiers;
import bsh.NameSpace;
import bsh.UtilEvalError;
import bsh.Variable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class ExternalNameSpace
extends NameSpace {
    private Map externalMap;

    public ExternalNameSpace() {
        this(null, "External Map Namespace", null);
    }

    public ExternalNameSpace(NameSpace parent, String name, Map externalMap) {
        super(parent, name);
        if (externalMap == null) {
            externalMap = new HashMap();
        }
        this.externalMap = externalMap;
    }

    public Map getMap() {
        return this.externalMap;
    }

    public void setMap(Map map) {
        this.externalMap = null;
        this.clear();
        this.externalMap = map;
    }

    public void unsetVariable(String name) {
        super.unsetVariable(name);
        this.externalMap.remove(name);
    }

    public String[] getVariableNames() {
        HashSet<String> nameSet = new HashSet<String>();
        String[] nsNames = super.getVariableNames();
        nameSet.addAll(Arrays.asList(nsNames));
        nameSet.addAll(this.externalMap.keySet());
        return nameSet.toArray(new String[0]);
    }

    protected Variable getVariableImpl(String name, boolean recurse) throws UtilEvalError {
        Variable var;
        Object value = this.externalMap.get(name);
        if (value == null) {
            super.unsetVariable(name);
            var = super.getVariableImpl(name, recurse);
        } else {
            Variable localVar = super.getVariableImpl(name, false);
            var = localVar == null ? this.createVariable(name, null, value, null) : localVar;
        }
        return var;
    }

    public Variable createVariable(String name, Class type, Object value, Modifiers mods) {
        LHS lhs = new LHS((Object)this.externalMap, name);
        try {
            lhs.assign(value, false);
        } catch (UtilEvalError e) {
            throw new InterpreterError(e.toString());
        }
        return new Variable(name, type, lhs);
    }

    public void clear() {
        super.clear();
        this.externalMap.clear();
    }
}

