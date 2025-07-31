/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.text.translate;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.text.translate.CharSequenceTranslator;

public class AggregateTranslator
extends CharSequenceTranslator {
    private final List<CharSequenceTranslator> translators = new ArrayList<CharSequenceTranslator>();

    public AggregateTranslator(CharSequenceTranslator ... translators) {
        if (translators != null) {
            for (CharSequenceTranslator translator : translators) {
                if (translator == null) continue;
                this.translators.add(translator);
            }
        }
    }

    @Override
    public int translate(CharSequence input, int index, Writer out) throws IOException {
        for (CharSequenceTranslator translator : this.translators) {
            int consumed = translator.translate(input, index, out);
            if (consumed == 0) continue;
            return consumed;
        }
        return 0;
    }
}

