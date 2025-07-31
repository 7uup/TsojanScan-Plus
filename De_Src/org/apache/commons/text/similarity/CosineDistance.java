/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.text.similarity;

import java.util.Map;
import org.apache.commons.text.similarity.CosineSimilarity;
import org.apache.commons.text.similarity.Counter;
import org.apache.commons.text.similarity.EditDistance;
import org.apache.commons.text.similarity.RegexTokenizer;
import org.apache.commons.text.similarity.Tokenizer;

public class CosineDistance
implements EditDistance<Double> {
    private final Tokenizer<CharSequence> tokenizer = new RegexTokenizer();
    private final CosineSimilarity cosineSimilarity = new CosineSimilarity();

    @Override
    public Double apply(CharSequence left, CharSequence right) {
        CharSequence[] leftTokens = this.tokenizer.tokenize(left);
        CharSequence[] rightTokens = this.tokenizer.tokenize(right);
        Map<CharSequence, Integer> leftVector = Counter.of(leftTokens);
        Map<CharSequence, Integer> rightVector = Counter.of(rightTokens);
        double similarity = this.cosineSimilarity.cosineSimilarity(leftVector, rightVector);
        return 1.0 - similarity;
    }
}

