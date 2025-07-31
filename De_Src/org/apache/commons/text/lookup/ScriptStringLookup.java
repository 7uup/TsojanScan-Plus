/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.text.lookup;

import java.util.Objects;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import org.apache.commons.text.lookup.AbstractStringLookup;
import org.apache.commons.text.lookup.IllegalArgumentExceptions;

final class ScriptStringLookup
extends AbstractStringLookup {
    static final ScriptStringLookup INSTANCE = new ScriptStringLookup();

    private ScriptStringLookup() {
    }

    @Override
    public String lookup(String key) {
        if (key == null) {
            return null;
        }
        String[] keys2 = key.split(SPLIT_STR);
        int keyLen = keys2.length;
        if (keyLen != 2) {
            throw IllegalArgumentExceptions.format("Bad script key format [%s]; expected format is DocumentPath:Key.", key);
        }
        String engineName = keys2[0];
        String script = keys2[1];
        try {
            ScriptEngine scriptEngine = new ScriptEngineManager().getEngineByName(engineName);
            if (scriptEngine == null) {
                throw new IllegalArgumentException("No script engine named " + engineName);
            }
            return Objects.toString(scriptEngine.eval(script), null);
        } catch (Exception e) {
            throw IllegalArgumentExceptions.format(e, "Error in script engine [%s] evaluating script [%s].", engineName, script);
        }
    }
}

