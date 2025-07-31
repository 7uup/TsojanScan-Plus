/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.xdevapi;

import com.mysql.cj.Messages;
import com.mysql.cj.exceptions.AssertionFailedException;
import com.mysql.cj.exceptions.ExceptionFactory;
import com.mysql.cj.exceptions.WrongArgumentException;
import com.mysql.cj.xdevapi.DbDoc;
import com.mysql.cj.xdevapi.DbDocImpl;
import com.mysql.cj.xdevapi.JsonArray;
import com.mysql.cj.xdevapi.JsonLiteral;
import com.mysql.cj.xdevapi.JsonNumber;
import com.mysql.cj.xdevapi.JsonString;
import com.mysql.cj.xdevapi.JsonValue;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class JsonParser {
    static Set<Character> whitespaceChars = new HashSet<Character>();
    static HashMap<Character, Character> unescapeChars = new HashMap();

    private static boolean isValidEndOfValue(char ch) {
        return StructuralToken.COMMA.CHAR == ch || StructuralToken.RCRBRACKET.CHAR == ch || StructuralToken.RSQBRACKET.CHAR == ch;
    }

    public static DbDoc parseDoc(String jsonString) {
        try {
            return JsonParser.parseDoc(new StringReader(jsonString));
        } catch (IOException ex) {
            throw AssertionFailedException.shouldNotHappen(ex);
        }
    }

    public static DbDoc parseDoc(StringReader reader) throws IOException {
        int intch;
        DbDocImpl doc = new DbDocImpl();
        int leftBrackets = 0;
        int rightBrackets = 0;
        while ((intch = reader.read()) != -1) {
            String key = null;
            char ch = (char)intch;
            if (ch == StructuralToken.LCRBRACKET.CHAR || ch == StructuralToken.COMMA.CHAR) {
                if (ch == StructuralToken.LCRBRACKET.CHAR) {
                    ++leftBrackets;
                }
                if ((key = JsonParser.nextKey(reader)) != null) {
                    try {
                        doc.put(key, JsonParser.nextValue(reader));
                        continue;
                    } catch (WrongArgumentException ex) {
                        throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("JsonParser.0", new String[]{key}), ex);
                    }
                }
                reader.reset();
                continue;
            }
            if (ch == StructuralToken.RCRBRACKET.CHAR) {
                ++rightBrackets;
                break;
            }
            if (whitespaceChars.contains(Character.valueOf(ch))) continue;
            throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("JsonParser.1", new Character[]{Character.valueOf(ch)}));
        }
        if (leftBrackets == 0) {
            throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("JsonParser.2"));
        }
        if (leftBrackets > rightBrackets) {
            throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("JsonParser.3", new Character[]{Character.valueOf(StructuralToken.RCRBRACKET.CHAR)}));
        }
        return doc;
    }

    public static JsonArray parseArray(StringReader reader) throws IOException {
        int intch;
        JsonArray arr = new JsonArray();
        int openings = 0;
        while ((intch = reader.read()) != -1) {
            char ch = (char)intch;
            if (ch == StructuralToken.LSQBRACKET.CHAR || ch == StructuralToken.COMMA.CHAR) {
                JsonValue val;
                if (ch == StructuralToken.LSQBRACKET.CHAR) {
                    ++openings;
                }
                if ((val = JsonParser.nextValue(reader)) != null) {
                    arr.add(val);
                    continue;
                }
                reader.reset();
                continue;
            }
            if (ch == StructuralToken.RSQBRACKET.CHAR) {
                --openings;
                break;
            }
            if (whitespaceChars.contains(Character.valueOf(ch))) continue;
            throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("JsonParser.1", new Character[]{Character.valueOf(ch)}));
        }
        if (openings > 0) {
            throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("JsonParser.3", new Character[]{Character.valueOf(StructuralToken.RSQBRACKET.CHAR)}));
        }
        return arr;
    }

    private static String nextKey(StringReader reader) throws IOException {
        int intch;
        reader.mark(1);
        JsonString val = JsonParser.parseString(reader);
        if (val == null) {
            reader.reset();
        }
        char ch = ' ';
        while ((intch = reader.read()) != -1 && (ch = (char)((char)intch)) != StructuralToken.COLON.CHAR && ch != StructuralToken.RCRBRACKET.CHAR) {
            if (whitespaceChars.contains(Character.valueOf(ch))) continue;
            throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("JsonParser.1", new Character[]{Character.valueOf(ch)}));
        }
        if (ch != StructuralToken.COLON.CHAR && val != null && val.getString().length() > 0) {
            throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("JsonParser.4", new String[]{val.getString()}));
        }
        return val != null ? val.getString() : null;
    }

    private static JsonValue nextValue(StringReader reader) throws IOException {
        int intch;
        reader.mark(1);
        while ((intch = reader.read()) != -1) {
            char ch = (char)intch;
            if (ch == EscapeChar.QUOTE.CHAR) {
                reader.reset();
                return JsonParser.parseString(reader);
            }
            if (ch == StructuralToken.LSQBRACKET.CHAR) {
                reader.reset();
                return JsonParser.parseArray(reader);
            }
            if (ch == StructuralToken.LCRBRACKET.CHAR) {
                reader.reset();
                return JsonParser.parseDoc(reader);
            }
            if (ch == '-' || ch >= '0' && ch <= '9') {
                reader.reset();
                return JsonParser.parseNumber(reader);
            }
            if (ch == JsonLiteral.TRUE.value.charAt(0)) {
                reader.reset();
                return JsonParser.parseLiteral(reader);
            }
            if (ch == JsonLiteral.FALSE.value.charAt(0)) {
                reader.reset();
                return JsonParser.parseLiteral(reader);
            }
            if (ch == JsonLiteral.NULL.value.charAt(0)) {
                reader.reset();
                return JsonParser.parseLiteral(reader);
            }
            if (ch == StructuralToken.RSQBRACKET.CHAR) {
                return null;
            }
            if (!whitespaceChars.contains(Character.valueOf(ch))) {
                throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("JsonParser.1", new Character[]{Character.valueOf(ch)}));
            }
            reader.mark(1);
        }
        throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("JsonParser.5"));
    }

    private static void appendChar(StringBuilder sb, char ch) {
        if (sb == null) {
            if (!whitespaceChars.contains(Character.valueOf(ch))) {
                throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("JsonParser.6", new Character[]{Character.valueOf(ch)}));
            }
        } else {
            sb.append(ch);
        }
    }

    static JsonString parseString(StringReader reader) throws IOException {
        int intch;
        int quotes = 0;
        boolean escapeNextChar = false;
        StringBuilder sb = null;
        while ((intch = reader.read()) != -1) {
            char ch = (char)intch;
            if (escapeNextChar) {
                if (!unescapeChars.containsKey(Character.valueOf(ch))) {
                    throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("JsonParser.7", new Character[]{Character.valueOf(ch)}));
                }
                JsonParser.appendChar(sb, unescapeChars.get(Character.valueOf(ch)).charValue());
                escapeNextChar = false;
                continue;
            }
            if (ch == EscapeChar.QUOTE.CHAR) {
                if (sb == null) {
                    sb = new StringBuilder();
                    ++quotes;
                    continue;
                }
                --quotes;
                break;
            }
            if (quotes == 0 && ch == StructuralToken.RCRBRACKET.CHAR) break;
            if (ch == EscapeChar.RSOLIDUS.CHAR) {
                escapeNextChar = true;
                continue;
            }
            JsonParser.appendChar(sb, ch);
        }
        if (quotes > 0) {
            throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("JsonParser.3", new Character[]{Character.valueOf(EscapeChar.QUOTE.CHAR)}));
        }
        return sb == null ? null : new JsonString().setValue(sb.toString());
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    static JsonNumber parseNumber(StringReader reader) throws IOException {
        int intch;
        StringBuilder sb = null;
        int lastChar = 32;
        int baseLength = 0;
        boolean hasFractionalPart = false;
        boolean hasExponent = false;
        while ((intch = reader.read()) != -1) {
            char ch = (char)intch;
            if (sb == null) {
                if (ch == '-') {
                    sb = new StringBuilder();
                    sb.append(ch);
                } else if (ch >= '0' && ch <= '9') {
                    sb = new StringBuilder();
                    sb.append(ch);
                    ++baseLength;
                } else if (!whitespaceChars.contains(Character.valueOf(ch))) {
                    throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("JsonParser.1", new Character[]{Character.valueOf(ch)}));
                }
            } else if (ch == '-') {
                if (lastChar != 69 && lastChar != 101) throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("JsonParser.8", new Object[]{Character.valueOf(ch), sb.toString()}));
                sb.append(ch);
            } else if (ch >= '0' && ch <= '9') {
                sb.append(ch);
                if (!hasFractionalPart && !hasExponent) {
                    if (baseLength >= 10) throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("JsonParser.9", new Object[]{sb.toString()}));
                    ++baseLength;
                }
            } else if (ch == 'E' || ch == 'e') {
                if (lastChar < 48 || lastChar > 57) throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("JsonParser.8", new Object[]{Character.valueOf(ch), sb.toString()}));
                hasExponent = true;
                sb.append(ch);
            } else if (ch == '.') {
                if (hasFractionalPart) {
                    throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("JsonParser.10", new Object[]{Character.valueOf(ch), sb.toString()}));
                }
                if (hasExponent) {
                    throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("JsonParser.11"));
                }
                if (lastChar < 48 || lastChar > 57) throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("JsonParser.8", new Object[]{Character.valueOf(ch), sb.toString()}));
                hasFractionalPart = true;
                sb.append(ch);
            } else if (ch == '+') {
                if (lastChar != 69 && lastChar != 101) throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("JsonParser.8", new Object[]{Character.valueOf(ch), sb.toString()}));
                sb.append(ch);
            } else {
                if (!whitespaceChars.contains(Character.valueOf(ch)) && !JsonParser.isValidEndOfValue(ch)) throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("JsonParser.1", new Character[]{Character.valueOf(ch)}));
                reader.reset();
                break;
            }
            lastChar = ch;
            reader.mark(1);
        }
        if (sb != null && sb.length() != 0) return new JsonNumber().setValue(sb.toString());
        throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("JsonParser.5"));
    }

    static JsonLiteral parseLiteral(StringReader reader) throws IOException {
        int intch;
        StringBuilder sb = null;
        JsonLiteral res = null;
        int literalIndex = 0;
        while ((intch = reader.read()) != -1) {
            char ch = (char)intch;
            if (sb == null) {
                if (ch == JsonLiteral.TRUE.value.charAt(0)) {
                    res = JsonLiteral.TRUE;
                    sb = new StringBuilder();
                    sb.append(ch);
                    ++literalIndex;
                } else if (ch == JsonLiteral.FALSE.value.charAt(0)) {
                    res = JsonLiteral.FALSE;
                    sb = new StringBuilder();
                    sb.append(ch);
                    ++literalIndex;
                } else if (ch == JsonLiteral.NULL.value.charAt(0)) {
                    res = JsonLiteral.NULL;
                    sb = new StringBuilder();
                    sb.append(ch);
                    ++literalIndex;
                } else if (!whitespaceChars.contains(Character.valueOf(ch))) {
                    throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("JsonParser.1", new Character[]{Character.valueOf(ch)}));
                }
            } else if (literalIndex < res.value.length() && ch == res.value.charAt(literalIndex)) {
                sb.append(ch);
                ++literalIndex;
            } else {
                if (whitespaceChars.contains(Character.valueOf(ch)) || JsonParser.isValidEndOfValue(ch)) {
                    reader.reset();
                    break;
                }
                throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("JsonParser.1", new Character[]{Character.valueOf(ch)}));
            }
            reader.mark(1);
        }
        if (sb == null) {
            throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("JsonParser.5"));
        }
        if (literalIndex == res.value.length()) {
            return res;
        }
        throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("JsonParser.12", new String[]{sb.toString()}));
    }

    static {
        for (EscapeChar escapeChar : EscapeChar.values()) {
            unescapeChars.put(Character.valueOf(escapeChar.ESCAPED.charAt(1)), Character.valueOf(escapeChar.CHAR));
        }
        for (Enum enum_ : Whitespace.values()) {
            whitespaceChars.add(Character.valueOf(((Whitespace)enum_).CHAR));
        }
    }

    static enum EscapeChar {
        QUOTE('\"', "\\\""),
        RSOLIDUS('\\', "\\\\"),
        SOLIDUS('/', "\\/"),
        BACKSPACE('\b', "\\b"),
        FF('\f', "\\f"),
        LF('\n', "\\n"),
        CR('\r', "\\r"),
        TAB('\t', "\\t");

        public final char CHAR;
        public final String ESCAPED;

        private EscapeChar(char character, String escaped) {
            this.CHAR = character;
            this.ESCAPED = escaped;
        }
    }

    static enum StructuralToken {
        LSQBRACKET('['),
        RSQBRACKET(']'),
        LCRBRACKET('{'),
        RCRBRACKET('}'),
        COLON(':'),
        COMMA(',');

        public final char CHAR;

        private StructuralToken(char character) {
            this.CHAR = character;
        }
    }

    static enum Whitespace {
        TAB('\t'),
        LF('\n'),
        CR('\r'),
        SPACE(' ');

        public final char CHAR;

        private Whitespace(char character) {
            this.CHAR = character;
        }
    }
}

