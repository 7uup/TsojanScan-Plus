/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.text;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Objects;

public final class AlphabetConverter {
    private final Map<Integer, String> originalToEncoded;
    private final Map<String, String> encodedToOriginal;
    private final int encodedLetterLength;
    private static final String ARROW = " -> ";

    private AlphabetConverter(Map<Integer, String> originalToEncoded, Map<String, String> encodedToOriginal, int encodedLetterLength) {
        this.originalToEncoded = originalToEncoded;
        this.encodedToOriginal = encodedToOriginal;
        this.encodedLetterLength = encodedLetterLength;
    }

    public String encode(String original) throws UnsupportedEncodingException {
        int codepoint;
        if (original == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < original.length(); i += Character.charCount(codepoint)) {
            codepoint = original.codePointAt(i);
            String nextLetter = this.originalToEncoded.get(codepoint);
            if (nextLetter == null) {
                throw new UnsupportedEncodingException("Couldn't find encoding for '" + AlphabetConverter.codePointToString(codepoint) + "' in " + original);
            }
            sb.append(nextLetter);
        }
        return sb.toString();
    }

    public String decode(String encoded) throws UnsupportedEncodingException {
        if (encoded == null) {
            return null;
        }
        StringBuilder result = new StringBuilder();
        int j = 0;
        while (j < encoded.length()) {
            Integer i = encoded.codePointAt(j);
            String s2 = AlphabetConverter.codePointToString(i);
            if (s2.equals(this.originalToEncoded.get(i))) {
                result.append(s2);
                ++j;
                continue;
            }
            if (j + this.encodedLetterLength > encoded.length()) {
                throw new UnsupportedEncodingException("Unexpected end of string while decoding " + encoded);
            }
            String nextGroup = encoded.substring(j, j + this.encodedLetterLength);
            String next = this.encodedToOriginal.get(nextGroup);
            if (next == null) {
                throw new UnsupportedEncodingException("Unexpected string without decoding (" + nextGroup + ") in " + encoded);
            }
            result.append(next);
            j += this.encodedLetterLength;
        }
        return result.toString();
    }

    public int getEncodedCharLength() {
        return this.encodedLetterLength;
    }

    public Map<Integer, String> getOriginalToEncoded() {
        return Collections.unmodifiableMap(this.originalToEncoded);
    }

    private void addSingleEncoding(int level, String currentEncoding, Collection<Integer> encoding, Iterator<Integer> originals, Map<Integer, String> doNotEncodeMap) {
        if (level > 0) {
            for (int encodingLetter : encoding) {
                if (originals.hasNext()) {
                    if (level == this.encodedLetterLength && doNotEncodeMap.containsKey(encodingLetter)) continue;
                    this.addSingleEncoding(level - 1, currentEncoding + AlphabetConverter.codePointToString(encodingLetter), encoding, originals, doNotEncodeMap);
                    continue;
                }
                return;
            }
        } else {
            String originalLetterAsString;
            Integer next = originals.next();
            while (doNotEncodeMap.containsKey(next)) {
                originalLetterAsString = AlphabetConverter.codePointToString(next);
                this.originalToEncoded.put(next, originalLetterAsString);
                this.encodedToOriginal.put(originalLetterAsString, originalLetterAsString);
                if (!originals.hasNext()) {
                    return;
                }
                next = originals.next();
            }
            originalLetterAsString = AlphabetConverter.codePointToString(next);
            this.originalToEncoded.put(next, currentEncoding);
            this.encodedToOriginal.put(currentEncoding, originalLetterAsString);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, String> entry : this.originalToEncoded.entrySet()) {
            sb.append(AlphabetConverter.codePointToString(entry.getKey())).append(ARROW).append(entry.getValue()).append(System.lineSeparator());
        }
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AlphabetConverter)) {
            return false;
        }
        AlphabetConverter other = (AlphabetConverter)obj;
        return this.originalToEncoded.equals(other.originalToEncoded) && this.encodedToOriginal.equals(other.encodedToOriginal) && this.encodedLetterLength == other.encodedLetterLength;
    }

    public int hashCode() {
        return Objects.hash(this.originalToEncoded, this.encodedToOriginal, this.encodedLetterLength);
    }

    public static AlphabetConverter createConverterFromMap(Map<Integer, String> originalToEncoded) {
        Map<Integer, String> unmodifiableOriginalToEncoded = Collections.unmodifiableMap(originalToEncoded);
        LinkedHashMap<String, String> encodedToOriginal = new LinkedHashMap<String, String>();
        int encodedLetterLength = 1;
        for (Map.Entry<Integer, String> e : unmodifiableOriginalToEncoded.entrySet()) {
            String originalAsString = AlphabetConverter.codePointToString(e.getKey());
            encodedToOriginal.put(e.getValue(), originalAsString);
            if (e.getValue().length() <= encodedLetterLength) continue;
            encodedLetterLength = e.getValue().length();
        }
        return new AlphabetConverter(unmodifiableOriginalToEncoded, encodedToOriginal, encodedLetterLength);
    }

    public static AlphabetConverter createConverterFromChars(Character[] original, Character[] encoding, Character[] doNotEncode) {
        return AlphabetConverter.createConverter(AlphabetConverter.convertCharsToIntegers(original), AlphabetConverter.convertCharsToIntegers(encoding), AlphabetConverter.convertCharsToIntegers(doNotEncode));
    }

    private static Integer[] convertCharsToIntegers(Character[] chars) {
        if (chars == null || chars.length == 0) {
            return new Integer[0];
        }
        Integer[] integers = new Integer[chars.length];
        for (int i = 0; i < chars.length; ++i) {
            integers[i] = chars[i].charValue();
        }
        return integers;
    }

    public static AlphabetConverter createConverter(Integer[] original, Integer[] encoding, Integer[] doNotEncode) {
        LinkedHashSet<Integer> originalCopy = new LinkedHashSet<Integer>(Arrays.asList(original));
        LinkedHashSet<Integer> encodingCopy = new LinkedHashSet<Integer>(Arrays.asList(encoding));
        LinkedHashSet<Integer> doNotEncodeCopy = new LinkedHashSet<Integer>(Arrays.asList(doNotEncode));
        LinkedHashMap<Integer, String> originalToEncoded = new LinkedHashMap<Integer, String>();
        LinkedHashMap<String, String> encodedToOriginal = new LinkedHashMap<String, String>();
        HashMap<Integer, String> doNotEncodeMap = new HashMap<Integer, String>();
        Iterator iterator2 = doNotEncodeCopy.iterator();
        while (iterator2.hasNext()) {
            int i = (Integer)iterator2.next();
            if (!originalCopy.contains(i)) {
                throw new IllegalArgumentException("Can not use 'do not encode' list because original alphabet does not contain '" + AlphabetConverter.codePointToString(i) + "'");
            }
            if (!encodingCopy.contains(i)) {
                throw new IllegalArgumentException("Can not use 'do not encode' list because encoding alphabet does not contain '" + AlphabetConverter.codePointToString(i) + "'");
            }
            doNotEncodeMap.put(i, AlphabetConverter.codePointToString(i));
        }
        if (encodingCopy.size() >= originalCopy.size()) {
            int encodedLetterLength = 1;
            Iterator it = encodingCopy.iterator();
            Iterator i = originalCopy.iterator();
            while (i.hasNext()) {
                int originalLetter = (Integer)i.next();
                String originalLetterAsString = AlphabetConverter.codePointToString(originalLetter);
                if (doNotEncodeMap.containsKey(originalLetter)) {
                    originalToEncoded.put(originalLetter, originalLetterAsString);
                    encodedToOriginal.put(originalLetterAsString, originalLetterAsString);
                    continue;
                }
                Integer next = (Integer)it.next();
                while (doNotEncodeCopy.contains(next)) {
                    next = (Integer)it.next();
                }
                String encodedLetter = AlphabetConverter.codePointToString(next);
                originalToEncoded.put(originalLetter, encodedLetter);
                encodedToOriginal.put(encodedLetter, originalLetterAsString);
            }
            return new AlphabetConverter(originalToEncoded, encodedToOriginal, encodedLetterLength);
        }
        if (encodingCopy.size() - doNotEncodeCopy.size() < 2) {
            throw new IllegalArgumentException("Must have at least two encoding characters (excluding those in the 'do not encode' list), but has " + (encodingCopy.size() - doNotEncodeCopy.size()));
        }
        int lettersSoFar = 1;
        int lettersLeft = (originalCopy.size() - doNotEncodeCopy.size()) / (encodingCopy.size() - doNotEncodeCopy.size());
        while (lettersLeft / encodingCopy.size() >= 1) {
            lettersLeft /= encodingCopy.size();
            ++lettersSoFar;
        }
        int encodedLetterLength = lettersSoFar + 1;
        AlphabetConverter ac = new AlphabetConverter(originalToEncoded, encodedToOriginal, encodedLetterLength);
        ac.addSingleEncoding(encodedLetterLength, "", encodingCopy, originalCopy.iterator(), doNotEncodeMap);
        return ac;
    }

    private static String codePointToString(int i) {
        if (Character.charCount(i) == 1) {
            return String.valueOf((char)i);
        }
        return new String(Character.toChars(i));
    }
}

