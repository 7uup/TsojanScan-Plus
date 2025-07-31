/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.text.similarity;

import org.apache.commons.text.similarity.SimilarityScore;

public interface EditDistance<R>
extends SimilarityScore<R> {
    @Override
    public R apply(CharSequence var1, CharSequence var2);
}

