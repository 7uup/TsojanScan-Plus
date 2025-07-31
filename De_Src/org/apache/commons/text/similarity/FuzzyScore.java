/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.text.similarity;

import java.util.Locale;

public class FuzzyScore {
    private final Locale locale;

    public FuzzyScore(Locale locale) {
        if (locale == null) {
            throw new IllegalArgumentException("Locale must not be null");
        }
        this.locale = locale;
    }

    public Integer fuzzyScore(CharSequence term, CharSequence query) {
        if (term == null || query == null) {
            throw new IllegalArgumentException("CharSequences must not be null");
        }
        String termLowerCase = term.toString().toLowerCase(this.locale);
        String queryLowerCase = query.toString().toLowerCase(this.locale);
        int score = 0;
        int termIndex = 0;
        int previousMatchingCharacterIndex = Integer.MIN_VALUE;
        for (int queryIndex = 0; queryIndex < queryLowerCase.length(); ++queryIndex) {
            char queryChar = queryLowerCase.charAt(queryIndex);
            boolean termCharacterMatchFound = false;
            while (termIndex < termLowerCase.length() && !termCharacterMatchFound) {
                char termChar = termLowerCase.charAt(termIndex);
                if (queryChar == termChar) {
                    ++score;
                    if (previousMatchingCharacterIndex + 1 == termIndex) {
                        score += 2;
                    }
                    previousMatchingCharacterIndex = termIndex;
                    termCharacterMatchFound = true;
                }
                ++termIndex;
            }
        }
        return score;
    }

    public Locale getLocale() {
        return this.locale;
    }
}

