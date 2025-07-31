/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.text.similarity;

import java.util.HashMap;
import java.util.Map;

final class Counter {
    private Counter() {
    }

    public static Map<CharSequence, Integer> of(CharSequence[] tokens) {
        HashMap<CharSequence, Integer> innerCounter = new HashMap<CharSequence, Integer>();
        for (CharSequence token : tokens) {
            if (innerCounter.containsKey(token)) {
                int value = (Integer)innerCounter.get(token);
                innerCounter.put(token, ++value);
                continue;
            }
            innerCounter.put(token, 1);
        }
        return innerCounter;
    }
}

