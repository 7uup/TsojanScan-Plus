/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.text;

import org.apache.commons.text.CharacterPredicate;

public enum CharacterPredicates implements CharacterPredicate
{
    LETTERS{

        @Override
        public boolean test(int codePoint) {
            return Character.isLetter(codePoint);
        }
    }
    ,
    DIGITS{

        @Override
        public boolean test(int codePoint) {
            return Character.isDigit(codePoint);
        }
    }
    ,
    ARABIC_NUMERALS{

        @Override
        public boolean test(int codePoint) {
            return codePoint >= 48 && codePoint <= 57;
        }
    }
    ,
    ASCII_LOWERCASE_LETTERS{

        @Override
        public boolean test(int codePoint) {
            return codePoint >= 97 && codePoint <= 122;
        }
    }
    ,
    ASCII_UPPERCASE_LETTERS{

        @Override
        public boolean test(int codePoint) {
            return codePoint >= 65 && codePoint <= 90;
        }
    }
    ,
    ASCII_LETTERS{

        @Override
        public boolean test(int codePoint) {
            return ASCII_LOWERCASE_LETTERS.test(codePoint) || ASCII_UPPERCASE_LETTERS.test(codePoint);
        }
    }
    ,
    ASCII_ALPHA_NUMERALS{

        @Override
        public boolean test(int codePoint) {
            return ASCII_LOWERCASE_LETTERS.test(codePoint) || ASCII_UPPERCASE_LETTERS.test(codePoint) || ARABIC_NUMERALS.test(codePoint);
        }
    };

}

