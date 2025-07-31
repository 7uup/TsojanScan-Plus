/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.util;

import com.mysql.cj.Messages;
import com.mysql.cj.ServerVersion;
import com.mysql.cj.exceptions.ExceptionFactory;
import com.mysql.cj.exceptions.NumberOutOfRange;
import com.mysql.cj.exceptions.WrongArgumentException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StringUtils {
    public static final Set<SearchMode> SEARCH_MODE__ALL = Collections.unmodifiableSet(EnumSet.allOf(SearchMode.class));
    public static final Set<SearchMode> SEARCH_MODE__MRK_COM_WS = Collections.unmodifiableSet(EnumSet.of(SearchMode.SKIP_BETWEEN_MARKERS, SearchMode.SKIP_BLOCK_COMMENTS, SearchMode.SKIP_LINE_COMMENTS, SearchMode.SKIP_WHITE_SPACE));
    public static final Set<SearchMode> SEARCH_MODE__BSESC_COM_WS = Collections.unmodifiableSet(EnumSet.of(SearchMode.ALLOW_BACKSLASH_ESCAPE, SearchMode.SKIP_BLOCK_COMMENTS, SearchMode.SKIP_LINE_COMMENTS, SearchMode.SKIP_WHITE_SPACE));
    public static final Set<SearchMode> SEARCH_MODE__BSESC_MRK_WS = Collections.unmodifiableSet(EnumSet.of(SearchMode.ALLOW_BACKSLASH_ESCAPE, SearchMode.SKIP_BETWEEN_MARKERS, SearchMode.SKIP_WHITE_SPACE));
    public static final Set<SearchMode> SEARCH_MODE__COM_WS = Collections.unmodifiableSet(EnumSet.of(SearchMode.SKIP_BLOCK_COMMENTS, SearchMode.SKIP_LINE_COMMENTS, SearchMode.SKIP_WHITE_SPACE));
    public static final Set<SearchMode> SEARCH_MODE__MRK_WS = Collections.unmodifiableSet(EnumSet.of(SearchMode.SKIP_BETWEEN_MARKERS, SearchMode.SKIP_WHITE_SPACE));
    public static final Set<SearchMode> SEARCH_MODE__NONE = Collections.unmodifiableSet(EnumSet.noneOf(SearchMode.class));
    private static final int NON_COMMENTS_MYSQL_VERSION_REF_LENGTH = 5;
    private static final int WILD_COMPARE_MATCH = 0;
    private static final int WILD_COMPARE_CONTINUE_WITH_WILD = 1;
    private static final int WILD_COMPARE_NO_MATCH = -1;
    static final char WILDCARD_MANY = '%';
    static final char WILDCARD_ONE = '_';
    static final char WILDCARD_ESCAPE = '\\';
    private static final String VALID_ID_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIGKLMNOPQRSTUVWXYZ0123456789$_#@";
    private static final char[] HEX_DIGITS = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    static final char[] EMPTY_SPACE = new char[255];

    public static String dumpAsHex(byte[] byteBuffer, int length) {
        length = Math.min(length, byteBuffer.length);
        StringBuilder fullOutBuilder = new StringBuilder(length * 4);
        StringBuilder asciiOutBuilder = new StringBuilder(16);
        int p = 0;
        int l = 0;
        while (p < length) {
            while (l < 8 && p < length) {
                int asInt = byteBuffer[p] & 0xFF;
                if (asInt < 16) {
                    fullOutBuilder.append("0");
                }
                fullOutBuilder.append(Integer.toHexString(asInt)).append(" ");
                asciiOutBuilder.append(" ").append(asInt >= 32 && asInt < 127 ? Character.valueOf((char)asInt) : ".");
                ++p;
                ++l;
            }
            while (l < 8) {
                fullOutBuilder.append("   ");
                ++l;
            }
            fullOutBuilder.append("   ").append((CharSequence)asciiOutBuilder).append(System.lineSeparator());
            asciiOutBuilder.setLength(0);
            l = 0;
        }
        return fullOutBuilder.toString();
    }

    public static String toHexString(byte[] byteBuffer, int length) {
        length = Math.min(length, byteBuffer.length);
        StringBuilder outputBuilder = new StringBuilder(length * 2);
        for (int i = 0; i < length; ++i) {
            int asInt = byteBuffer[i] & 0xFF;
            if (asInt < 16) {
                outputBuilder.append("0");
            }
            outputBuilder.append(Integer.toHexString(asInt));
        }
        return outputBuilder.toString();
    }

    private static boolean endsWith(byte[] dataFrom, String suffix) {
        for (int i = 1; i <= suffix.length(); ++i) {
            int dfOffset = dataFrom.length - i;
            int suffixOffset = suffix.length() - i;
            if (dataFrom[dfOffset] == suffix.charAt(suffixOffset)) continue;
            return false;
        }
        return true;
    }

    public static char firstNonWsCharUc(String searchIn) {
        return StringUtils.firstNonWsCharUc(searchIn, 0);
    }

    public static char firstNonWsCharUc(String searchIn, int startAt) {
        if (searchIn == null) {
            return '\u0000';
        }
        int length = searchIn.length();
        for (int i = startAt; i < length; ++i) {
            char c = searchIn.charAt(i);
            if (Character.isWhitespace(c)) continue;
            return Character.toUpperCase(c);
        }
        return '\u0000';
    }

    public static char firstAlphaCharUc(String searchIn, int startAt) {
        if (searchIn == null) {
            return '\u0000';
        }
        int length = searchIn.length();
        for (int i = startAt; i < length; ++i) {
            char c = searchIn.charAt(i);
            if (!Character.isLetter(c)) continue;
            return Character.toUpperCase(c);
        }
        return '\u0000';
    }

    public static String fixDecimalExponent(String dString) {
        char maybeMinusChar;
        int ePos = dString.indexOf(69);
        if (ePos == -1) {
            ePos = dString.indexOf(101);
        }
        if (ePos != -1 && dString.length() > ePos + 1 && (maybeMinusChar = dString.charAt(ePos + 1)) != '-' && maybeMinusChar != '+') {
            StringBuilder strBuilder = new StringBuilder(dString.length() + 1);
            strBuilder.append(dString.substring(0, ePos + 1));
            strBuilder.append('+');
            strBuilder.append(dString.substring(ePos + 1, dString.length()));
            dString = strBuilder.toString();
        }
        return dString;
    }

    public static byte[] getBytes(String s2, String encoding) {
        if (encoding == null) {
            return StringUtils.getBytes(s2);
        }
        try {
            return s2.getBytes(encoding);
        } catch (UnsupportedEncodingException uee) {
            throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("StringUtils.0", new Object[]{encoding}), uee);
        }
    }

    public static byte[] getBytesWrapped(String s2, char beginWrap, char endWrap, String encoding) {
        byte[] b;
        if (encoding == null) {
            StringBuilder strBuilder = new StringBuilder(s2.length() + 2);
            strBuilder.append(beginWrap);
            strBuilder.append(s2);
            strBuilder.append(endWrap);
            b = StringUtils.getBytes(strBuilder.toString());
        } else {
            StringBuilder strBuilder = new StringBuilder(s2.length() + 2);
            strBuilder.append(beginWrap);
            strBuilder.append(s2);
            strBuilder.append(endWrap);
            s2 = strBuilder.toString();
            b = StringUtils.getBytes(s2, encoding);
        }
        return b;
    }

    public static int getInt(byte[] buf) throws NumberFormatException {
        return StringUtils.getInt(buf, 0, buf.length);
    }

    public static int getInt(byte[] buf, int offset, int endpos) throws NumberFormatException {
        long l = StringUtils.getLong(buf, offset, endpos);
        if (l < Integer.MIN_VALUE || l > Integer.MAX_VALUE) {
            throw new NumberOutOfRange(Messages.getString("StringUtils.badIntFormat", new Object[]{StringUtils.toString(buf, offset, endpos - offset)}));
        }
        return (int)l;
    }

    public static long getLong(byte[] buf) throws NumberFormatException {
        return StringUtils.getLong(buf, 0, buf.length);
    }

    public static long getLong(byte[] buf, int offset, int endpos) throws NumberFormatException {
        int s2;
        char base = '\n';
        for (s2 = offset; s2 < endpos && Character.isWhitespace((char)buf[s2]); ++s2) {
        }
        if (s2 == endpos) {
            throw new NumberFormatException(StringUtils.toString(buf));
        }
        boolean negative = false;
        if ((char)buf[s2] == '-') {
            negative = true;
            ++s2;
        } else if ((char)buf[s2] == '+') {
            ++s2;
        }
        int save = s2;
        long cutoff = Long.MAX_VALUE / (long)base;
        long cutlim = (int)(Long.MAX_VALUE % (long)base);
        if (negative) {
            ++cutlim;
        }
        boolean overflow = false;
        long i = 0L;
        while (s2 < endpos) {
            char c = (char)buf[s2];
            if (c >= '0' && c <= '9') {
                c = (char)(c - 48);
            } else {
                if (!Character.isLetter(c)) break;
                c = (char)(Character.toUpperCase(c) - 65 + 10);
            }
            if (c >= base) break;
            if (i > cutoff || i == cutoff && (long)c > cutlim) {
                overflow = true;
            } else {
                i *= (long)base;
                i += (long)c;
            }
            ++s2;
        }
        if (s2 == save) {
            throw new NumberFormatException(Messages.getString("StringUtils.badIntFormat", new Object[]{StringUtils.toString(buf, offset, endpos - offset)}));
        }
        if (overflow) {
            throw new NumberOutOfRange(Messages.getString("StringUtils.badIntFormat", new Object[]{StringUtils.toString(buf, offset, endpos - offset)}));
        }
        return negative ? -i : i;
    }

    public static int indexOfIgnoreCase(String searchIn, String searchFor) {
        return StringUtils.indexOfIgnoreCase(0, searchIn, searchFor);
    }

    public static int indexOfIgnoreCase(int startingPosition, String searchIn, String searchFor) {
        int searchForLength;
        if (searchIn == null || searchFor == null) {
            return -1;
        }
        int searchInLength = searchIn.length();
        int stopSearchingAt = searchInLength - (searchForLength = searchFor.length());
        if (startingPosition > stopSearchingAt || searchForLength == 0) {
            return -1;
        }
        char firstCharOfSearchForUc = Character.toUpperCase(searchFor.charAt(0));
        char firstCharOfSearchForLc = Character.toLowerCase(searchFor.charAt(0));
        for (int i = startingPosition; i <= stopSearchingAt; ++i) {
            if (StringUtils.isCharAtPosNotEqualIgnoreCase(searchIn, i, firstCharOfSearchForUc, firstCharOfSearchForLc)) {
                while (++i <= stopSearchingAt && StringUtils.isCharAtPosNotEqualIgnoreCase(searchIn, i, firstCharOfSearchForUc, firstCharOfSearchForLc)) {
                }
            }
            if (i > stopSearchingAt || !StringUtils.startsWithIgnoreCase(searchIn, i, searchFor)) continue;
            return i;
        }
        return -1;
    }

    public static int indexOfIgnoreCase(int startingPosition, String searchIn, String[] searchForSequence, String openingMarkers, String closingMarkers, Set<SearchMode> searchMode) {
        if (searchIn == null || searchForSequence == null) {
            return -1;
        }
        int searchInLength = searchIn.length();
        int searchForLength = 0;
        for (String searchForPart : searchForSequence) {
            searchForLength += searchForPart.length();
        }
        if (searchForLength == 0) {
            return -1;
        }
        int searchForWordsCount = searchForSequence.length;
        int stopSearchingAt = searchInLength - (searchForLength += searchForWordsCount > 0 ? searchForWordsCount - 1 : 0);
        if (startingPosition > stopSearchingAt) {
            return -1;
        }
        if (searchMode.contains((Object)SearchMode.SKIP_BETWEEN_MARKERS) && (openingMarkers == null || closingMarkers == null || openingMarkers.length() != closingMarkers.length())) {
            throw new IllegalArgumentException(Messages.getString("StringUtils.15", new String[]{openingMarkers, closingMarkers}));
        }
        if (Character.isWhitespace(searchForSequence[0].charAt(0)) && searchMode.contains((Object)SearchMode.SKIP_WHITE_SPACE)) {
            searchMode = EnumSet.copyOf(searchMode);
            searchMode.remove((Object)SearchMode.SKIP_WHITE_SPACE);
        }
        EnumSet<SearchMode> searchMode2 = EnumSet.of(SearchMode.SKIP_WHITE_SPACE);
        searchMode2.addAll(searchMode);
        searchMode2.remove((Object)SearchMode.SKIP_BETWEEN_MARKERS);
        for (int positionOfFirstWord = startingPosition; positionOfFirstWord <= stopSearchingAt; ++positionOfFirstWord) {
            if ((positionOfFirstWord = StringUtils.indexOfIgnoreCase(positionOfFirstWord, searchIn, searchForSequence[0], openingMarkers, closingMarkers, searchMode)) == -1 || positionOfFirstWord > stopSearchingAt) {
                return -1;
            }
            int startingPositionForNextWord = positionOfFirstWord + searchForSequence[0].length();
            int wc = 0;
            boolean match = true;
            while (++wc < searchForWordsCount && match) {
                int positionOfNextWord = StringUtils.indexOfNextChar(startingPositionForNextWord, searchInLength - 1, searchIn, null, null, null, searchMode2);
                if (startingPositionForNextWord == positionOfNextWord || !StringUtils.startsWithIgnoreCase(searchIn, positionOfNextWord, searchForSequence[wc])) {
                    match = false;
                    continue;
                }
                startingPositionForNextWord = positionOfNextWord + searchForSequence[wc].length();
            }
            if (!match) continue;
            return positionOfFirstWord;
        }
        return -1;
    }

    public static int indexOfIgnoreCase(int startingPosition, String searchIn, String searchFor, String openingMarkers, String closingMarkers, Set<SearchMode> searchMode) {
        return StringUtils.indexOfIgnoreCase(startingPosition, searchIn, searchFor, openingMarkers, closingMarkers, "", searchMode);
    }

    public static int indexOfIgnoreCase(int startingPosition, String searchIn, String searchFor, String openingMarkers, String closingMarkers, String overridingMarkers, Set<SearchMode> searchMode) {
        int searchForLength;
        if (searchIn == null || searchFor == null) {
            return -1;
        }
        int searchInLength = searchIn.length();
        int stopSearchingAt = searchInLength - (searchForLength = searchFor.length());
        if (startingPosition > stopSearchingAt || searchForLength == 0) {
            return -1;
        }
        if (searchMode.contains((Object)SearchMode.SKIP_BETWEEN_MARKERS)) {
            if (openingMarkers == null || closingMarkers == null || openingMarkers.length() != closingMarkers.length()) {
                throw new IllegalArgumentException(Messages.getString("StringUtils.15", new String[]{openingMarkers, closingMarkers}));
            }
            if (overridingMarkers == null) {
                throw new IllegalArgumentException(Messages.getString("StringUtils.16", new String[]{overridingMarkers, openingMarkers}));
            }
            for (char c : overridingMarkers.toCharArray()) {
                if (openingMarkers.indexOf(c) != -1) continue;
                throw new IllegalArgumentException(Messages.getString("StringUtils.16", new String[]{overridingMarkers, openingMarkers}));
            }
        }
        char firstCharOfSearchForUc = Character.toUpperCase(searchFor.charAt(0));
        char firstCharOfSearchForLc = Character.toLowerCase(searchFor.charAt(0));
        if (Character.isWhitespace(firstCharOfSearchForLc) && searchMode.contains((Object)SearchMode.SKIP_WHITE_SPACE)) {
            searchMode = EnumSet.copyOf(searchMode);
            searchMode.remove((Object)SearchMode.SKIP_WHITE_SPACE);
        }
        for (int i = startingPosition; i <= stopSearchingAt; ++i) {
            char c;
            if ((i = StringUtils.indexOfNextChar(i, stopSearchingAt, searchIn, openingMarkers, closingMarkers, overridingMarkers, searchMode)) == -1) {
                return -1;
            }
            c = searchIn.charAt(i);
            if (!StringUtils.isCharEqualIgnoreCase(c, firstCharOfSearchForUc, firstCharOfSearchForLc) || !StringUtils.startsWithIgnoreCase(searchIn, i, searchFor)) continue;
            return i;
        }
        return -1;
    }

    private static int indexOfNextChar(int startingPosition, int stopPosition, String searchIn, String openingMarkers, String closingMarkers, String overridingMarkers, Set<SearchMode> searchMode) {
        if (searchIn == null) {
            return -1;
        }
        int searchInLength = searchIn.length();
        if (startingPosition >= searchInLength) {
            return -1;
        }
        char c0 = '\u0000';
        char c1 = searchIn.charAt(startingPosition);
        char c2 = startingPosition + 1 < searchInLength ? searchIn.charAt(startingPosition + 1) : (char)'\u0000';
        for (int i = startingPosition; i <= stopPosition; ++i) {
            c0 = c1;
            c1 = c2;
            c2 = i + 2 < searchInLength ? searchIn.charAt(i + 2) : (char)'\u0000';
            boolean dashDashCommentImmediateEnd = false;
            int markerIndex = -1;
            if (searchMode.contains((Object)SearchMode.ALLOW_BACKSLASH_ESCAPE) && c0 == '\\') {
                c1 = c2;
                c2 = ++i + 2 < searchInLength ? searchIn.charAt(i + 2) : (char)'\u0000';
                continue;
            }
            if (searchMode.contains((Object)SearchMode.SKIP_BETWEEN_MARKERS) && (markerIndex = openingMarkers.indexOf(c0)) != -1) {
                boolean outerIsAnOverridingMarker;
                int nestedMarkersCount = 0;
                char openingMarker = c0;
                char closingMarker = closingMarkers.charAt(markerIndex);
                boolean bl = outerIsAnOverridingMarker = overridingMarkers.indexOf(openingMarker) != -1;
                while (++i <= stopPosition && ((c0 = searchIn.charAt(i)) != closingMarker || nestedMarkersCount != 0)) {
                    if (!outerIsAnOverridingMarker && overridingMarkers.indexOf(c0) != -1) {
                        int overridingMarkerIndex = openingMarkers.indexOf(c0);
                        int overridingNestedMarkersCount = 0;
                        char overridingOpeningMarker = c0;
                        char overridingClosingMarker = closingMarkers.charAt(overridingMarkerIndex);
                        while (++i <= stopPosition && ((c0 = searchIn.charAt(i)) != overridingClosingMarker || overridingNestedMarkersCount != 0)) {
                            if (c0 == overridingOpeningMarker) {
                                ++overridingNestedMarkersCount;
                                continue;
                            }
                            if (c0 == overridingClosingMarker) {
                                --overridingNestedMarkersCount;
                                continue;
                            }
                            if (!searchMode.contains((Object)SearchMode.ALLOW_BACKSLASH_ESCAPE) || c0 != '\\') continue;
                            ++i;
                        }
                        continue;
                    }
                    if (c0 == openingMarker) {
                        ++nestedMarkersCount;
                        continue;
                    }
                    if (c0 == closingMarker) {
                        --nestedMarkersCount;
                        continue;
                    }
                    if (!searchMode.contains((Object)SearchMode.ALLOW_BACKSLASH_ESCAPE) || c0 != '\\') continue;
                    ++i;
                }
                c1 = i + 1 < searchInLength ? searchIn.charAt(i + 1) : (char)'\u0000';
                c2 = i + 2 < searchInLength ? searchIn.charAt(i + 2) : (char)'\u0000';
                continue;
            }
            if (searchMode.contains((Object)SearchMode.SKIP_BLOCK_COMMENTS) && c0 == '/' && c1 == '*') {
                if (c2 != '!') {
                    ++i;
                    while (++i <= stopPosition && (searchIn.charAt(i) != '*' || (i + 1 < searchInLength ? (int)searchIn.charAt(i + 1) : 0) != 47)) {
                    }
                    ++i;
                } else {
                    int j;
                    ++i;
                    ++i;
                    for (j = 1; j <= 5 && i + j < searchInLength && Character.isDigit(searchIn.charAt(i + j)); ++j) {
                    }
                    if (j == 5) {
                        i += 5;
                    }
                }
                c1 = i + 1 < searchInLength ? searchIn.charAt(i + 1) : (char)'\u0000';
                c2 = i + 2 < searchInLength ? searchIn.charAt(i + 2) : (char)'\u0000';
                continue;
            }
            if (searchMode.contains((Object)SearchMode.SKIP_BLOCK_COMMENTS) && c0 == '*' && c1 == '/') {
                c1 = c2;
                c2 = ++i + 2 < searchInLength ? searchIn.charAt(i + 2) : (char)'\u0000';
                continue;
            }
            if (searchMode.contains((Object)SearchMode.SKIP_LINE_COMMENTS) && (c0 == '-' && c1 == '-' && (Character.isWhitespace(c2) || (dashDashCommentImmediateEnd = c2 == ';') || c2 == '\u0000') || c0 == '#')) {
                if (dashDashCommentImmediateEnd) {
                    ++i;
                    c1 = ++i + 1 < searchInLength ? searchIn.charAt(i + 1) : (char)'\u0000';
                    c2 = i + 2 < searchInLength ? searchIn.charAt(i + 2) : (char)'\u0000';
                    continue;
                }
                while (++i <= stopPosition && (c0 = searchIn.charAt(i)) != '\n' && c0 != '\r') {
                }
                char c = c1 = i + 1 < searchInLength ? searchIn.charAt(i + 1) : (char)'\u0000';
                if (c0 == '\r' && c1 == '\n') {
                    c1 = ++i + 1 < searchInLength ? searchIn.charAt(i + 1) : (char)'\u0000';
                }
                c2 = i + 2 < searchInLength ? searchIn.charAt(i + 2) : (char)'\u0000';
                continue;
            }
            if (searchMode.contains((Object)SearchMode.SKIP_WHITE_SPACE) && Character.isWhitespace(c0)) continue;
            return i;
        }
        return -1;
    }

    private static boolean isCharAtPosNotEqualIgnoreCase(String searchIn, int pos, char firstCharOfSearchForUc, char firstCharOfSearchForLc) {
        return Character.toLowerCase(searchIn.charAt(pos)) != firstCharOfSearchForLc && Character.toUpperCase(searchIn.charAt(pos)) != firstCharOfSearchForUc;
    }

    private static boolean isCharEqualIgnoreCase(char charToCompare, char compareToCharUC, char compareToCharLC) {
        return Character.toLowerCase(charToCompare) == compareToCharLC || Character.toUpperCase(charToCompare) == compareToCharUC;
    }

    public static List<String> split(String stringToSplit, String delimiter, boolean trim) {
        if (stringToSplit == null) {
            return new ArrayList<String>();
        }
        if (delimiter == null) {
            throw new IllegalArgumentException();
        }
        String[] tokens = stringToSplit.split(delimiter, -1);
        Stream<Object> tokensStream = Arrays.asList(tokens).stream();
        if (trim) {
            tokensStream = tokensStream.map(String::trim);
        }
        return tokensStream.collect(Collectors.toList());
    }

    public static List<String> split(String stringToSplit, String delimiter, String openingMarkers, String closingMarkers, boolean trim) {
        return StringUtils.split(stringToSplit, delimiter, openingMarkers, closingMarkers, "", trim);
    }

    public static List<String> split(String stringToSplit, String delimiter, String openingMarkers, String closingMarkers, boolean trim, Set<SearchMode> searchMode) {
        return StringUtils.split(stringToSplit, delimiter, openingMarkers, closingMarkers, "", trim, searchMode);
    }

    public static List<String> split(String stringToSplit, String delimiter, String openingMarkers, String closingMarkers, String overridingMarkers, boolean trim) {
        return StringUtils.split(stringToSplit, delimiter, openingMarkers, closingMarkers, overridingMarkers, trim, SEARCH_MODE__MRK_COM_WS);
    }

    public static List<String> split(String stringToSplit, String delimiter, String openingMarkers, String closingMarkers, String overridingMarkers, boolean trim, Set<SearchMode> searchMode) {
        String token;
        if (stringToSplit == null) {
            return new ArrayList<String>();
        }
        if (delimiter == null) {
            throw new IllegalArgumentException();
        }
        int delimPos = 0;
        int currentPos = 0;
        ArrayList<String> splitTokens = new ArrayList<String>();
        while ((delimPos = StringUtils.indexOfIgnoreCase(currentPos, stringToSplit, delimiter, openingMarkers, closingMarkers, overridingMarkers, searchMode)) != -1) {
            token = stringToSplit.substring(currentPos, delimPos);
            if (trim) {
                token = token.trim();
            }
            splitTokens.add(token);
            currentPos = delimPos + delimiter.length();
        }
        token = stringToSplit.substring(currentPos);
        if (trim) {
            token = token.trim();
        }
        splitTokens.add(token);
        return splitTokens;
    }

    private static boolean startsWith(byte[] dataFrom, String chars) {
        int charsLength = chars.length();
        if (dataFrom.length < charsLength) {
            return false;
        }
        for (int i = 0; i < charsLength; ++i) {
            if (dataFrom[i] == chars.charAt(i)) continue;
            return false;
        }
        return true;
    }

    public static boolean startsWithIgnoreCase(String searchIn, int startAt, String searchFor) {
        return searchIn.regionMatches(true, startAt, searchFor, 0, searchFor.length());
    }

    public static boolean startsWithIgnoreCase(String searchIn, String searchFor) {
        return StringUtils.startsWithIgnoreCase(searchIn, 0, searchFor);
    }

    public static boolean startsWithIgnoreCaseAndNonAlphaNumeric(String searchIn, String searchFor) {
        char c;
        int beginPos;
        if (searchIn == null) {
            return searchFor == null;
        }
        int inLength = searchIn.length();
        for (beginPos = 0; beginPos < inLength && !Character.isLetterOrDigit(c = searchIn.charAt(beginPos)); ++beginPos) {
        }
        return StringUtils.startsWithIgnoreCase(searchIn, beginPos, searchFor);
    }

    public static boolean startsWithIgnoreCaseAndWs(String searchIn, String searchFor) {
        return StringUtils.startsWithIgnoreCaseAndWs(searchIn, searchFor, 0);
    }

    public static boolean startsWithIgnoreCaseAndWs(String searchIn, String searchFor, int beginPos) {
        if (searchIn == null) {
            return searchFor == null;
        }
        int inLength = searchIn.length();
        while (beginPos < inLength && Character.isWhitespace(searchIn.charAt(beginPos))) {
            ++beginPos;
        }
        return StringUtils.startsWithIgnoreCase(searchIn, beginPos, searchFor);
    }

    public static int startsWithIgnoreCaseAndWs(String searchIn, String[] searchFor) {
        for (int i = 0; i < searchFor.length; ++i) {
            if (!StringUtils.startsWithIgnoreCaseAndWs(searchIn, searchFor[i], 0)) continue;
            return i;
        }
        return -1;
    }

    public static byte[] stripEnclosure(byte[] source2, String prefix, String suffix) {
        if (source2.length >= prefix.length() + suffix.length() && StringUtils.startsWith(source2, prefix) && StringUtils.endsWith(source2, suffix)) {
            int totalToStrip = prefix.length() + suffix.length();
            int enclosedLength = source2.length - totalToStrip;
            byte[] enclosed = new byte[enclosedLength];
            int startPos = prefix.length();
            int numToCopy = enclosed.length;
            System.arraycopy(source2, startPos, enclosed, 0, numToCopy);
            return enclosed;
        }
        return source2;
    }

    public static String toAsciiString(byte[] buffer) {
        return StringUtils.toAsciiString(buffer, 0, buffer.length);
    }

    public static String toAsciiString(byte[] buffer, int startPos, int length) {
        char[] charArray = new char[length];
        int readpoint = startPos;
        for (int i = 0; i < length; ++i) {
            charArray[i] = (char)buffer[readpoint];
            ++readpoint;
        }
        return new String(charArray);
    }

    public static boolean wildCompareIgnoreCase(String searchIn, String searchFor) {
        return StringUtils.wildCompareInternal(searchIn, searchFor) == 0;
    }

    private static int wildCompareInternal(String searchIn, String searchFor) {
        if (searchIn == null || searchFor == null) {
            return -1;
        }
        if (searchFor.equals("%")) {
            return 0;
        }
        int searchForPos = 0;
        int searchForEnd = searchFor.length();
        int searchInPos = 0;
        int searchInEnd = searchIn.length();
        int result = -1;
        while (searchForPos != searchForEnd) {
            while (searchFor.charAt(searchForPos) != '%' && searchFor.charAt(searchForPos) != '_') {
                if (searchFor.charAt(searchForPos) == '\\' && searchForPos + 1 != searchForEnd) {
                    ++searchForPos;
                }
                if (searchInPos == searchInEnd || Character.toUpperCase(searchFor.charAt(searchForPos++)) != Character.toUpperCase(searchIn.charAt(searchInPos++))) {
                    return 1;
                }
                if (searchForPos == searchForEnd) {
                    return searchInPos != searchInEnd ? 1 : 0;
                }
                result = 1;
            }
            if (searchFor.charAt(searchForPos) == '_') {
                do {
                    if (searchInPos == searchInEnd) {
                        return result;
                    }
                    ++searchInPos;
                } while (++searchForPos < searchForEnd && searchFor.charAt(searchForPos) == '_');
                if (searchForPos == searchForEnd) break;
            }
            if (searchFor.charAt(searchForPos) != '%') continue;
            ++searchForPos;
            while (searchForPos != searchForEnd) {
                if (searchFor.charAt(searchForPos) != '%') {
                    if (searchFor.charAt(searchForPos) != '_') break;
                    if (searchInPos == searchInEnd) {
                        return -1;
                    }
                    ++searchInPos;
                }
                ++searchForPos;
            }
            if (searchForPos == searchForEnd) {
                return 0;
            }
            if (searchInPos == searchInEnd) {
                return -1;
            }
            char cmp = searchFor.charAt(searchForPos);
            if (cmp == '\\' && searchForPos + 1 != searchForEnd) {
                cmp = searchFor.charAt(++searchForPos);
            }
            ++searchForPos;
            while (true) {
                if (searchInPos != searchInEnd && Character.toUpperCase(searchIn.charAt(searchInPos)) != Character.toUpperCase(cmp)) {
                    ++searchInPos;
                    continue;
                }
                if (searchInPos++ == searchInEnd) {
                    return -1;
                }
                int tmp = StringUtils.wildCompareInternal(searchIn.substring(searchInPos), searchFor.substring(searchForPos));
                if (tmp <= 0) {
                    return tmp;
                }
                if (searchInPos == searchInEnd) break;
            }
            return -1;
        }
        return searchInPos != searchInEnd ? 1 : 0;
    }

    public static int lastIndexOf(byte[] s2, char c) {
        if (s2 == null) {
            return -1;
        }
        for (int i = s2.length - 1; i >= 0; --i) {
            if (s2[i] != c) continue;
            return i;
        }
        return -1;
    }

    public static int indexOf(byte[] s2, char c) {
        if (s2 == null) {
            return -1;
        }
        int length = s2.length;
        for (int i = 0; i < length; ++i) {
            if (s2[i] != c) continue;
            return i;
        }
        return -1;
    }

    public static boolean isNullOrEmpty(String toTest) {
        return toTest == null || toTest.isEmpty();
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static String stripComments(String src, String stringOpens, String stringCloses, boolean slashStarComments, boolean slashSlashComments, boolean hashComments, boolean dashDashComments) {
        if (src == null) {
            return null;
        }
        StringBuilder strBuilder = new StringBuilder(src.length());
        StringReader sourceReader = new StringReader(src);
        char contextMarker = '\u0000';
        boolean escaped = false;
        int markerTypeFound = -1;
        int ind = 0;
        char currentChar = '\u0000';
        try {
            block2: while ((currentChar = sourceReader.read()) != '\uffffffff') {
                char prevChar;
                block22: {
                    block23: {
                        block20: {
                            block21: {
                                if (markerTypeFound != -1 && currentChar == stringCloses.charAt(markerTypeFound) && !escaped) {
                                    contextMarker = '\u0000';
                                    markerTypeFound = -1;
                                } else {
                                    ind = stringOpens.indexOf(currentChar);
                                    if (ind != -1 && !escaped && contextMarker == '\u0000') {
                                        markerTypeFound = ind;
                                        contextMarker = currentChar;
                                    }
                                }
                                if (contextMarker != '\u0000' || currentChar != '/' || !slashSlashComments && !slashStarComments) break block20;
                                currentChar = sourceReader.read();
                                if (currentChar != '*' || !slashStarComments) break block21;
                                prevChar = '\u0000';
                                break block22;
                            }
                            if (currentChar == '/' && slashSlashComments) {
                                while ((currentChar = sourceReader.read()) != '\n' && currentChar != '\r' && currentChar >= '\u0000') {
                                }
                            }
                            break block23;
                        }
                        if (contextMarker == '\u0000' && currentChar == '#' && hashComments) {
                            while ((currentChar = sourceReader.read()) != '\n' && currentChar != '\r' && currentChar >= '\u0000') {
                            }
                        } else if (contextMarker == '\u0000' && currentChar == '-' && dashDashComments) {
                            currentChar = sourceReader.read();
                            if (currentChar == '\uffffffff' || currentChar != '-') {
                                strBuilder.append('-');
                                if (currentChar == '\uffffffff') continue;
                                strBuilder.append(currentChar);
                                continue;
                            }
                            while ((currentChar = sourceReader.read()) != '\n' && currentChar != '\r' && currentChar >= '\u0000') {
                            }
                        }
                    }
                    if (currentChar == '\uffffffff') continue;
                    strBuilder.append(currentChar);
                    continue;
                }
                while ((currentChar = sourceReader.read()) != '/' || prevChar != '*') {
                    if (currentChar == '\r') {
                        currentChar = sourceReader.read();
                        if (currentChar == '\n') {
                            currentChar = sourceReader.read();
                        }
                    } else if (currentChar == '\n') {
                        currentChar = sourceReader.read();
                    }
                    if (currentChar < '\u0000') continue block2;
                    prevChar = currentChar;
                }
            }
            return strBuilder.toString();
        } catch (IOException iOException) {
            // empty catch block
        }
        return strBuilder.toString();
    }

    public static String sanitizeProcOrFuncName(String src) {
        if (src == null || src.equals("%")) {
            return null;
        }
        return src;
    }

    public static List<String> splitDBdotName(String source2, String catalog, String quoteId, boolean isNoBslashEscSet) {
        String entityName;
        if (source2 == null || source2.equals("%")) {
            return Collections.emptyList();
        }
        int dotIndex = -1;
        dotIndex = " ".equals(quoteId) ? source2.indexOf(".") : StringUtils.indexOfIgnoreCase(0, source2, ".", quoteId, quoteId, isNoBslashEscSet ? SEARCH_MODE__MRK_WS : SEARCH_MODE__BSESC_MRK_WS);
        String database = catalog;
        if (dotIndex != -1) {
            database = StringUtils.unQuoteIdentifier(source2.substring(0, dotIndex), quoteId);
            entityName = StringUtils.unQuoteIdentifier(source2.substring(dotIndex + 1), quoteId);
        } else {
            entityName = StringUtils.unQuoteIdentifier(source2, quoteId);
        }
        return Arrays.asList(database, entityName);
    }

    public static boolean isEmptyOrWhitespaceOnly(String str) {
        if (str == null || str.length() == 0) {
            return true;
        }
        int length = str.length();
        for (int i = 0; i < length; ++i) {
            if (Character.isWhitespace(str.charAt(i))) continue;
            return false;
        }
        return true;
    }

    public static String escapeQuote(String src, String quotChar) {
        if (src == null) {
            return null;
        }
        src = StringUtils.toString(StringUtils.stripEnclosure(src.getBytes(), quotChar, quotChar));
        int lastNdx = src.indexOf(quotChar);
        String tmpSrc = src.substring(0, lastNdx);
        tmpSrc = tmpSrc + quotChar + quotChar;
        String tmpRest = src.substring(lastNdx + 1, src.length());
        lastNdx = tmpRest.indexOf(quotChar);
        while (lastNdx > -1) {
            tmpSrc = tmpSrc + tmpRest.substring(0, lastNdx);
            tmpSrc = tmpSrc + quotChar + quotChar;
            tmpRest = tmpRest.substring(lastNdx + 1, tmpRest.length());
            lastNdx = tmpRest.indexOf(quotChar);
        }
        src = tmpSrc = tmpSrc + tmpRest;
        return src;
    }

    public static String quoteIdentifier(String identifier, String quoteChar, boolean isPedantic) {
        if (identifier == null) {
            return null;
        }
        identifier = identifier.trim();
        int quoteCharLength = quoteChar.length();
        if (quoteCharLength == 0) {
            return identifier;
        }
        if (!isPedantic && identifier.startsWith(quoteChar) && identifier.endsWith(quoteChar)) {
            int quoteCharNextExpectedPos;
            int quoteCharNextPosition;
            String identifierQuoteTrimmed = identifier.substring(quoteCharLength, identifier.length() - quoteCharLength);
            int quoteCharPos = identifierQuoteTrimmed.indexOf(quoteChar);
            while (quoteCharPos >= 0 && (quoteCharNextPosition = identifierQuoteTrimmed.indexOf(quoteChar, quoteCharNextExpectedPos = quoteCharPos + quoteCharLength)) == quoteCharNextExpectedPos) {
                quoteCharPos = identifierQuoteTrimmed.indexOf(quoteChar, quoteCharNextPosition + quoteCharLength);
            }
            if (quoteCharPos < 0) {
                return identifier;
            }
        }
        return quoteChar + identifier.replaceAll(quoteChar, quoteChar + quoteChar) + quoteChar;
    }

    public static String quoteIdentifier(String identifier, boolean isPedantic) {
        return StringUtils.quoteIdentifier(identifier, "`", isPedantic);
    }

    public static String unQuoteIdentifier(String identifier, String quoteChar) {
        if (identifier == null) {
            return null;
        }
        identifier = identifier.trim();
        int quoteCharLength = quoteChar.length();
        if (quoteCharLength == 0) {
            return identifier;
        }
        if (identifier.startsWith(quoteChar) && identifier.endsWith(quoteChar)) {
            String identifierQuoteTrimmed = identifier.substring(quoteCharLength, identifier.length() - quoteCharLength);
            int quoteCharPos = identifierQuoteTrimmed.indexOf(quoteChar);
            while (quoteCharPos >= 0) {
                int quoteCharNextExpectedPos = quoteCharPos + quoteCharLength;
                int quoteCharNextPosition = identifierQuoteTrimmed.indexOf(quoteChar, quoteCharNextExpectedPos);
                if (quoteCharNextPosition == quoteCharNextExpectedPos) {
                    quoteCharPos = identifierQuoteTrimmed.indexOf(quoteChar, quoteCharNextPosition + quoteCharLength);
                    continue;
                }
                return identifier;
            }
            return identifier.substring(quoteCharLength, identifier.length() - quoteCharLength).replaceAll(quoteChar + quoteChar, quoteChar);
        }
        return identifier;
    }

    public static int indexOfQuoteDoubleAware(String searchIn, String quoteChar, int startFrom) {
        if (searchIn == null || quoteChar == null || quoteChar.length() == 0 || startFrom > searchIn.length()) {
            return -1;
        }
        int lastIndex = searchIn.length() - 1;
        int beginPos = startFrom;
        int pos = -1;
        boolean next = true;
        while (next) {
            pos = searchIn.indexOf(quoteChar, beginPos);
            if (pos == -1 || pos == lastIndex || !searchIn.startsWith(quoteChar, pos + 1)) {
                next = false;
                continue;
            }
            beginPos = pos + 2;
        }
        return pos;
    }

    public static String toString(byte[] value, int offset, int length, String encoding) {
        if (encoding == null || "null".equalsIgnoreCase(encoding)) {
            return new String(value, offset, length);
        }
        try {
            return new String(value, offset, length, encoding);
        } catch (UnsupportedEncodingException uee) {
            throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("StringUtils.0", new Object[]{encoding}), uee);
        }
    }

    public static String toString(byte[] value, String encoding) {
        if (encoding == null) {
            return new String(value);
        }
        try {
            return new String(value, encoding);
        } catch (UnsupportedEncodingException uee) {
            throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("StringUtils.0", new Object[]{encoding}), uee);
        }
    }

    public static String toString(byte[] value, int offset, int length) {
        return new String(value, offset, length);
    }

    public static String toString(byte[] value) {
        return new String(value);
    }

    public static byte[] getBytes(char[] value) {
        return StringUtils.getBytes(value, 0, value.length);
    }

    public static byte[] getBytes(char[] c, String encoding) {
        return StringUtils.getBytes(c, 0, c.length, encoding);
    }

    public static byte[] getBytes(char[] value, int offset, int length) {
        return StringUtils.getBytes(value, offset, length, null);
    }

    public static byte[] getBytes(char[] value, int offset, int length, String encoding) {
        Charset cs;
        try {
            cs = encoding == null ? Charset.defaultCharset() : Charset.forName(encoding);
        } catch (UnsupportedCharsetException ex) {
            throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("StringUtils.0", new Object[]{encoding}), ex);
        }
        ByteBuffer buf = cs.encode(CharBuffer.wrap(value, offset, length));
        int encodedLen = buf.limit();
        byte[] asBytes = new byte[encodedLen];
        buf.get(asBytes, 0, encodedLen);
        return asBytes;
    }

    public static byte[] getBytes(String value) {
        return value.getBytes();
    }

    public static byte[] getBytes(String value, int offset, int length) {
        return value.substring(offset, offset + length).getBytes();
    }

    public static byte[] getBytes(String value, int offset, int length, String encoding) {
        if (encoding == null) {
            return StringUtils.getBytes(value, offset, length);
        }
        try {
            return value.substring(offset, offset + length).getBytes(encoding);
        } catch (UnsupportedEncodingException uee) {
            throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("StringUtils.0", new Object[]{encoding}), uee);
        }
    }

    public static final boolean isValidIdChar(char c) {
        return VALID_ID_CHARS.indexOf(c) != -1;
    }

    public static void appendAsHex(StringBuilder builder, byte[] bytes) {
        builder.append("0x");
        for (byte b : bytes) {
            builder.append(HEX_DIGITS[b >>> 4 & 0xF]).append(HEX_DIGITS[b & 0xF]);
        }
    }

    public static void appendAsHex(StringBuilder builder, int value) {
        if (value == 0) {
            builder.append("0x0");
            return;
        }
        int shift = 32;
        boolean nonZeroFound = false;
        builder.append("0x");
        do {
            byte nibble = (byte)(value >>> (shift -= 4) & 0xF);
            if (nonZeroFound) {
                builder.append(HEX_DIGITS[nibble]);
                continue;
            }
            if (nibble == 0) continue;
            builder.append(HEX_DIGITS[nibble]);
            nonZeroFound = true;
        } while (shift != 0);
    }

    public static byte[] getBytesNullTerminated(String value, String encoding) {
        Charset cs = Charset.forName(encoding);
        ByteBuffer buf = cs.encode(value);
        int encodedLen = buf.limit();
        byte[] asBytes = new byte[encodedLen + 1];
        buf.get(asBytes, 0, encodedLen);
        asBytes[encodedLen] = 0;
        return asBytes;
    }

    public static boolean canHandleAsServerPreparedStatementNoCache(String sql, ServerVersion serverVersion) {
        if (StringUtils.startsWithIgnoreCaseAndNonAlphaNumeric(sql, "CALL")) {
            return false;
        }
        boolean canHandleAsStatement = true;
        if (StringUtils.startsWithIgnoreCaseAndWs(sql, "XA ")) {
            canHandleAsStatement = false;
        } else if (StringUtils.startsWithIgnoreCaseAndWs(sql, "CREATE TABLE")) {
            canHandleAsStatement = false;
        } else if (StringUtils.startsWithIgnoreCaseAndWs(sql, "DO")) {
            canHandleAsStatement = false;
        } else if (StringUtils.startsWithIgnoreCaseAndWs(sql, "SET")) {
            canHandleAsStatement = false;
        } else if (StringUtils.startsWithIgnoreCaseAndWs(sql, "SHOW WARNINGS") && serverVersion.meetsMinimum(ServerVersion.parseVersion("5.7.2"))) {
            canHandleAsStatement = false;
        } else if (sql.startsWith("/* ping */")) {
            canHandleAsStatement = false;
        }
        return canHandleAsStatement;
    }

    public static String padString(String stringVal, int requiredLength) {
        int currentLength = stringVal.length();
        int difference = requiredLength - currentLength;
        if (difference > 0) {
            StringBuilder paddedBuf = new StringBuilder(requiredLength);
            paddedBuf.append(stringVal);
            paddedBuf.append(EMPTY_SPACE, 0, difference);
            return paddedBuf.toString();
        }
        return stringVal;
    }

    public static int safeIntParse(String intAsString) {
        try {
            return Integer.parseInt(intAsString);
        } catch (NumberFormatException nfe) {
            return 0;
        }
    }

    public static boolean isStrictlyNumeric(CharSequence cs) {
        if (cs == null || cs.length() == 0) {
            return false;
        }
        for (int i = 0; i < cs.length(); ++i) {
            if (Character.isDigit(cs.charAt(i))) continue;
            return false;
        }
        return true;
    }

    public static String safeTrim(String toTrim) {
        return StringUtils.isNullOrEmpty(toTrim) ? toTrim : toTrim.trim();
    }

    public static String stringArrayToString(String[] elems, String prefix, String midDelimiter, String lastDelimiter, String suffix) {
        StringBuilder valuesString = new StringBuilder();
        if (elems.length > 1) {
            valuesString.append(Arrays.stream(elems).limit(elems.length - 1).collect(Collectors.joining(midDelimiter, prefix, lastDelimiter)));
        } else {
            valuesString.append(prefix);
        }
        valuesString.append(elems[elems.length - 1]).append(suffix);
        return valuesString.toString();
    }

    public static final void escapeblockFast(byte[] buf, ByteArrayOutputStream bytesOut, int size, boolean useAnsiMode) {
        int lastwritten = 0;
        for (int i = 0; i < size; ++i) {
            byte b = buf[i];
            if (b == 0) {
                if (i > lastwritten) {
                    bytesOut.write(buf, lastwritten, i - lastwritten);
                }
                bytesOut.write(92);
                bytesOut.write(48);
                lastwritten = i + 1;
                continue;
            }
            if (b != 92 && b != 39 && (useAnsiMode || b != 34)) continue;
            if (i > lastwritten) {
                bytesOut.write(buf, lastwritten, i - lastwritten);
            }
            bytesOut.write(92);
            lastwritten = i;
        }
        if (lastwritten < size) {
            bytesOut.write(buf, lastwritten, size - lastwritten);
        }
    }

    public static boolean hasWildcards(String src) {
        return StringUtils.indexOfIgnoreCase(0, src, "%") > -1 || StringUtils.indexOfIgnoreCase(0, src, "_") > -1;
    }

    public static String getUniqueSavepointId() {
        String uuid = UUID.randomUUID().toString();
        return uuid.replaceAll("-", "_");
    }

    public static String joinWithSerialComma(List<?> elements) {
        if (elements == null || elements.size() == 0) {
            return "";
        }
        if (elements.size() == 1) {
            return elements.get(0).toString();
        }
        if (elements.size() == 2) {
            return elements.get(0) + " and " + elements.get(1);
        }
        return elements.subList(0, elements.size() - 1).stream().map(Object::toString).collect(Collectors.joining(", ", "", ", and ")) + elements.get(elements.size() - 1).toString();
    }

    static {
        for (int i = 0; i < EMPTY_SPACE.length; ++i) {
            StringUtils.EMPTY_SPACE[i] = 32;
        }
    }

    public static enum SearchMode {
        ALLOW_BACKSLASH_ESCAPE,
        SKIP_BETWEEN_MARKERS,
        SKIP_BLOCK_COMMENTS,
        SKIP_LINE_COMMENTS,
        SKIP_WHITE_SPACE;

    }
}

