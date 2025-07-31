/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.text.translate;

import java.io.IOException;
import java.io.Writer;
import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import org.apache.commons.text.translate.CharSequenceTranslator;

public class LookupTranslator
extends CharSequenceTranslator {
    private final Map<String, String> lookupMap;
    private final HashSet<Character> prefixSet;
    private final int shortest;
    private final int longest;

    public LookupTranslator(Map<CharSequence, CharSequence> lookupMap) {
        if (lookupMap == null) {
            throw new InvalidParameterException("lookupMap cannot be null");
        }
        this.lookupMap = new HashMap<String, String>();
        this.prefixSet = new HashSet();
        int currentShortest = Integer.MAX_VALUE;
        int currentLongest = 0;
        for (Map.Entry<CharSequence, CharSequence> pair : lookupMap.entrySet()) {
            this.lookupMap.put(pair.getKey().toString(), pair.getValue().toString());
            this.prefixSet.add(Character.valueOf(pair.getKey().charAt(0)));
            int sz = pair.getKey().length();
            if (sz < currentShortest) {
                currentShortest = sz;
            }
            if (sz <= currentLongest) continue;
            currentLongest = sz;
        }
        this.shortest = currentShortest;
        this.longest = currentLongest;
    }

    @Override
    public int translate(CharSequence input, int index, Writer out) throws IOException {
        if (this.prefixSet.contains(Character.valueOf(input.charAt(index)))) {
            int max = this.longest;
            if (index + this.longest > input.length()) {
                max = input.length() - index;
            }
            for (int i = max; i >= this.shortest; --i) {
                CharSequence subSeq = input.subSequence(index, index + i);
                String result = this.lookupMap.get(subSeq.toString());
                if (result == null) continue;
                out.write(result);
                return i;
            }
        }
        return 0;
    }
}

