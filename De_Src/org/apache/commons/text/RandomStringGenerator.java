/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import org.apache.commons.lang3.Validate;
import org.apache.commons.text.CharacterPredicate;
import org.apache.commons.text.TextRandomProvider;

public final class RandomStringGenerator {
    private final int minimumCodePoint;
    private final int maximumCodePoint;
    private final Set<CharacterPredicate> inclusivePredicates;
    private final TextRandomProvider random;
    private final List<Character> characterList;

    private RandomStringGenerator(int minimumCodePoint, int maximumCodePoint, Set<CharacterPredicate> inclusivePredicates, TextRandomProvider random, List<Character> characterList) {
        this.minimumCodePoint = minimumCodePoint;
        this.maximumCodePoint = maximumCodePoint;
        this.inclusivePredicates = inclusivePredicates;
        this.random = random;
        this.characterList = characterList;
    }

    private int generateRandomNumber(int minInclusive, int maxInclusive) {
        if (this.random != null) {
            return this.random.nextInt(maxInclusive - minInclusive + 1) + minInclusive;
        }
        return ThreadLocalRandom.current().nextInt(minInclusive, maxInclusive + 1);
    }

    private int generateRandomNumber(List<Character> characterList) {
        int listSize = characterList.size();
        if (this.random != null) {
            return String.valueOf(characterList.get(this.random.nextInt(listSize))).codePointAt(0);
        }
        return String.valueOf(characterList.get(ThreadLocalRandom.current().nextInt(0, listSize))).codePointAt(0);
    }

    public String generate(int length) {
        if (length == 0) {
            return "";
        }
        Validate.isTrue(length > 0, "Length %d is smaller than zero.", length);
        StringBuilder builder = new StringBuilder(length);
        long remaining = length;
        block3: do {
            int codePoint = this.characterList != null && !this.characterList.isEmpty() ? this.generateRandomNumber(this.characterList) : this.generateRandomNumber(this.minimumCodePoint, this.maximumCodePoint);
            switch (Character.getType(codePoint)) {
                case 0: 
                case 18: 
                case 19: {
                    break;
                }
                default: {
                    if (this.inclusivePredicates != null) {
                        boolean matchedFilter = false;
                        for (CharacterPredicate predicate : this.inclusivePredicates) {
                            if (!predicate.test(codePoint)) continue;
                            matchedFilter = true;
                            break;
                        }
                        if (!matchedFilter) continue block3;
                    }
                    builder.appendCodePoint(codePoint);
                    --remaining;
                }
            }
        } while (remaining != 0L);
        return builder.toString();
    }

    public String generate(int minLengthInclusive, int maxLengthInclusive) {
        Validate.isTrue(minLengthInclusive >= 0, "Minimum length %d is smaller than zero.", minLengthInclusive);
        Validate.isTrue(minLengthInclusive <= maxLengthInclusive, "Maximum length %d is smaller than minimum length %d.", maxLengthInclusive, minLengthInclusive);
        return this.generate(this.generateRandomNumber(minLengthInclusive, maxLengthInclusive));
    }

    public static class Builder
    implements org.apache.commons.text.Builder<RandomStringGenerator> {
        public static final int DEFAULT_MAXIMUM_CODE_POINT = 0x10FFFF;
        public static final int DEFAULT_LENGTH = 0;
        public static final int DEFAULT_MINIMUM_CODE_POINT = 0;
        private int minimumCodePoint = 0;
        private int maximumCodePoint = 0x10FFFF;
        private Set<CharacterPredicate> inclusivePredicates;
        private TextRandomProvider random;
        private List<Character> characterList;

        public Builder withinRange(int minimumCodePoint, int maximumCodePoint) {
            Validate.isTrue(minimumCodePoint <= maximumCodePoint, "Minimum code point %d is larger than maximum code point %d", minimumCodePoint, maximumCodePoint);
            Validate.isTrue(minimumCodePoint >= 0, "Minimum code point %d is negative", minimumCodePoint);
            Validate.isTrue(maximumCodePoint <= 0x10FFFF, "Value %d is larger than Character.MAX_CODE_POINT.", maximumCodePoint);
            this.minimumCodePoint = minimumCodePoint;
            this.maximumCodePoint = maximumCodePoint;
            return this;
        }

        public Builder withinRange(char[] ... pairs) {
            this.characterList = new ArrayList<Character>();
            for (char[] pair : pairs) {
                Validate.isTrue(pair.length == 2, "Each pair must contain minimum and maximum code point", new Object[0]);
                int minimumCodePoint = pair[0];
                char maximumCodePoint = pair[1];
                Validate.isTrue(minimumCodePoint <= maximumCodePoint, "Minimum code point %d is larger than maximum code point %d", minimumCodePoint, maximumCodePoint);
                for (int index = minimumCodePoint; index <= maximumCodePoint; ++index) {
                    this.characterList.add(Character.valueOf((char)index));
                }
            }
            return this;
        }

        public Builder filteredBy(CharacterPredicate ... predicates) {
            if (predicates == null || predicates.length == 0) {
                this.inclusivePredicates = null;
                return this;
            }
            if (this.inclusivePredicates == null) {
                this.inclusivePredicates = new HashSet<CharacterPredicate>();
            } else {
                this.inclusivePredicates.clear();
            }
            Collections.addAll(this.inclusivePredicates, predicates);
            return this;
        }

        public Builder usingRandom(TextRandomProvider random) {
            this.random = random;
            return this;
        }

        public Builder selectFrom(char ... chars) {
            this.characterList = new ArrayList<Character>();
            for (char c : chars) {
                this.characterList.add(Character.valueOf(c));
            }
            return this;
        }

        @Override
        public RandomStringGenerator build() {
            return new RandomStringGenerator(this.minimumCodePoint, this.maximumCodePoint, this.inclusivePredicates, this.random, this.characterList);
        }
    }
}

