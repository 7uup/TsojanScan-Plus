/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.text;

import java.io.IOException;
import java.io.Writer;
import java.util.Collections;
import java.util.HashMap;
import org.apache.commons.text.translate.AggregateTranslator;
import org.apache.commons.text.translate.CharSequenceTranslator;
import org.apache.commons.text.translate.CsvTranslators;
import org.apache.commons.text.translate.EntityArrays;
import org.apache.commons.text.translate.JavaUnicodeEscaper;
import org.apache.commons.text.translate.LookupTranslator;
import org.apache.commons.text.translate.NumericEntityEscaper;
import org.apache.commons.text.translate.NumericEntityUnescaper;
import org.apache.commons.text.translate.OctalUnescaper;
import org.apache.commons.text.translate.UnicodeUnescaper;
import org.apache.commons.text.translate.UnicodeUnpairedSurrogateRemover;

public class StringEscapeUtils {
    public static final CharSequenceTranslator ESCAPE_JAVA;
    public static final CharSequenceTranslator ESCAPE_ECMASCRIPT;
    public static final CharSequenceTranslator ESCAPE_JSON;
    public static final CharSequenceTranslator ESCAPE_XML10;
    public static final CharSequenceTranslator ESCAPE_XML11;
    public static final CharSequenceTranslator ESCAPE_HTML3;
    public static final CharSequenceTranslator ESCAPE_HTML4;
    public static final CharSequenceTranslator ESCAPE_CSV;
    public static final CharSequenceTranslator ESCAPE_XSI;
    public static final CharSequenceTranslator UNESCAPE_JAVA;
    public static final CharSequenceTranslator UNESCAPE_ECMASCRIPT;
    public static final CharSequenceTranslator UNESCAPE_JSON;
    public static final CharSequenceTranslator UNESCAPE_HTML3;
    public static final CharSequenceTranslator UNESCAPE_HTML4;
    public static final CharSequenceTranslator UNESCAPE_XML;
    public static final CharSequenceTranslator UNESCAPE_CSV;
    public static final CharSequenceTranslator UNESCAPE_XSI;

    public static Builder builder(CharSequenceTranslator translator) {
        return new Builder(translator);
    }

    public static final String escapeJava(String input) {
        return ESCAPE_JAVA.translate(input);
    }

    public static final String escapeEcmaScript(String input) {
        return ESCAPE_ECMASCRIPT.translate(input);
    }

    public static final String escapeJson(String input) {
        return ESCAPE_JSON.translate(input);
    }

    public static final String unescapeJava(String input) {
        return UNESCAPE_JAVA.translate(input);
    }

    public static final String unescapeEcmaScript(String input) {
        return UNESCAPE_ECMASCRIPT.translate(input);
    }

    public static final String unescapeJson(String input) {
        return UNESCAPE_JSON.translate(input);
    }

    public static final String escapeHtml4(String input) {
        return ESCAPE_HTML4.translate(input);
    }

    public static final String escapeHtml3(String input) {
        return ESCAPE_HTML3.translate(input);
    }

    public static final String unescapeHtml4(String input) {
        return UNESCAPE_HTML4.translate(input);
    }

    public static final String unescapeHtml3(String input) {
        return UNESCAPE_HTML3.translate(input);
    }

    public static String escapeXml10(String input) {
        return ESCAPE_XML10.translate(input);
    }

    public static String escapeXml11(String input) {
        return ESCAPE_XML11.translate(input);
    }

    public static final String unescapeXml(String input) {
        return UNESCAPE_XML.translate(input);
    }

    public static final String escapeCsv(String input) {
        return ESCAPE_CSV.translate(input);
    }

    public static final String unescapeCsv(String input) {
        return UNESCAPE_CSV.translate(input);
    }

    public static final String escapeXSI(String input) {
        return ESCAPE_XSI.translate(input);
    }

    public static final String unescapeXSI(String input) {
        return UNESCAPE_XSI.translate(input);
    }

    static {
        HashMap<String, String> escapeJavaMap = new HashMap<String, String>();
        escapeJavaMap.put("\"", "\\\"");
        escapeJavaMap.put("\\", "\\\\");
        ESCAPE_JAVA = new AggregateTranslator(new LookupTranslator(Collections.unmodifiableMap(escapeJavaMap)), new LookupTranslator(EntityArrays.JAVA_CTRL_CHARS_ESCAPE), JavaUnicodeEscaper.outsideOf(32, 127));
        HashMap<String, String> escapeEcmaScriptMap = new HashMap<String, String>();
        escapeEcmaScriptMap.put("'", "\\'");
        escapeEcmaScriptMap.put("\"", "\\\"");
        escapeEcmaScriptMap.put("\\", "\\\\");
        escapeEcmaScriptMap.put("/", "\\/");
        ESCAPE_ECMASCRIPT = new AggregateTranslator(new LookupTranslator(Collections.unmodifiableMap(escapeEcmaScriptMap)), new LookupTranslator(EntityArrays.JAVA_CTRL_CHARS_ESCAPE), JavaUnicodeEscaper.outsideOf(32, 127));
        HashMap<String, String> escapeJsonMap = new HashMap<String, String>();
        escapeJsonMap.put("\"", "\\\"");
        escapeJsonMap.put("\\", "\\\\");
        escapeJsonMap.put("/", "\\/");
        ESCAPE_JSON = new AggregateTranslator(new LookupTranslator(Collections.unmodifiableMap(escapeJsonMap)), new LookupTranslator(EntityArrays.JAVA_CTRL_CHARS_ESCAPE), JavaUnicodeEscaper.outsideOf(32, 126));
        HashMap<String, String> escapeXml10Map = new HashMap<String, String>();
        escapeXml10Map.put("\u0000", "");
        escapeXml10Map.put("\u0001", "");
        escapeXml10Map.put("\u0002", "");
        escapeXml10Map.put("\u0003", "");
        escapeXml10Map.put("\u0004", "");
        escapeXml10Map.put("\u0005", "");
        escapeXml10Map.put("\u0006", "");
        escapeXml10Map.put("\u0007", "");
        escapeXml10Map.put("\b", "");
        escapeXml10Map.put("\u000b", "");
        escapeXml10Map.put("\f", "");
        escapeXml10Map.put("\u000e", "");
        escapeXml10Map.put("\u000f", "");
        escapeXml10Map.put("\u0010", "");
        escapeXml10Map.put("\u0011", "");
        escapeXml10Map.put("\u0012", "");
        escapeXml10Map.put("\u0013", "");
        escapeXml10Map.put("\u0014", "");
        escapeXml10Map.put("\u0015", "");
        escapeXml10Map.put("\u0016", "");
        escapeXml10Map.put("\u0017", "");
        escapeXml10Map.put("\u0018", "");
        escapeXml10Map.put("\u0019", "");
        escapeXml10Map.put("\u001a", "");
        escapeXml10Map.put("\u001b", "");
        escapeXml10Map.put("\u001c", "");
        escapeXml10Map.put("\u001d", "");
        escapeXml10Map.put("\u001e", "");
        escapeXml10Map.put("\u001f", "");
        escapeXml10Map.put("\ufffe", "");
        escapeXml10Map.put("\uffff", "");
        ESCAPE_XML10 = new AggregateTranslator(new LookupTranslator(EntityArrays.BASIC_ESCAPE), new LookupTranslator(EntityArrays.APOS_ESCAPE), new LookupTranslator(Collections.unmodifiableMap(escapeXml10Map)), NumericEntityEscaper.between(127, 132), NumericEntityEscaper.between(134, 159), new UnicodeUnpairedSurrogateRemover());
        HashMap<String, String> escapeXml11Map = new HashMap<String, String>();
        escapeXml11Map.put("\u0000", "");
        escapeXml11Map.put("\u000b", "&#11;");
        escapeXml11Map.put("\f", "&#12;");
        escapeXml11Map.put("\ufffe", "");
        escapeXml11Map.put("\uffff", "");
        ESCAPE_XML11 = new AggregateTranslator(new LookupTranslator(EntityArrays.BASIC_ESCAPE), new LookupTranslator(EntityArrays.APOS_ESCAPE), new LookupTranslator(Collections.unmodifiableMap(escapeXml11Map)), NumericEntityEscaper.between(1, 8), NumericEntityEscaper.between(14, 31), NumericEntityEscaper.between(127, 132), NumericEntityEscaper.between(134, 159), new UnicodeUnpairedSurrogateRemover());
        ESCAPE_HTML3 = new AggregateTranslator(new LookupTranslator(EntityArrays.BASIC_ESCAPE), new LookupTranslator(EntityArrays.ISO8859_1_ESCAPE));
        ESCAPE_HTML4 = new AggregateTranslator(new LookupTranslator(EntityArrays.BASIC_ESCAPE), new LookupTranslator(EntityArrays.ISO8859_1_ESCAPE), new LookupTranslator(EntityArrays.HTML40_EXTENDED_ESCAPE));
        ESCAPE_CSV = new CsvTranslators.CsvEscaper();
        HashMap<String, String> escapeXsiMap = new HashMap<String, String>();
        escapeXsiMap.put("|", "\\|");
        escapeXsiMap.put("&", "\\&");
        escapeXsiMap.put(";", "\\;");
        escapeXsiMap.put("<", "\\<");
        escapeXsiMap.put(">", "\\>");
        escapeXsiMap.put("(", "\\(");
        escapeXsiMap.put(")", "\\)");
        escapeXsiMap.put("$", "\\$");
        escapeXsiMap.put("`", "\\`");
        escapeXsiMap.put("\\", "\\\\");
        escapeXsiMap.put("\"", "\\\"");
        escapeXsiMap.put("'", "\\'");
        escapeXsiMap.put(" ", "\\ ");
        escapeXsiMap.put("\t", "\\\t");
        escapeXsiMap.put("\r\n", "");
        escapeXsiMap.put("\n", "");
        escapeXsiMap.put("*", "\\*");
        escapeXsiMap.put("?", "\\?");
        escapeXsiMap.put("[", "\\[");
        escapeXsiMap.put("#", "\\#");
        escapeXsiMap.put("~", "\\~");
        escapeXsiMap.put("=", "\\=");
        escapeXsiMap.put("%", "\\%");
        ESCAPE_XSI = new LookupTranslator(Collections.unmodifiableMap(escapeXsiMap));
        HashMap<String, String> unescapeJavaMap = new HashMap<String, String>();
        unescapeJavaMap.put("\\\\", "\\");
        unescapeJavaMap.put("\\\"", "\"");
        unescapeJavaMap.put("\\'", "'");
        unescapeJavaMap.put("\\", "");
        UNESCAPE_ECMASCRIPT = UNESCAPE_JAVA = new AggregateTranslator(new OctalUnescaper(), new UnicodeUnescaper(), new LookupTranslator(EntityArrays.JAVA_CTRL_CHARS_UNESCAPE), new LookupTranslator(Collections.unmodifiableMap(unescapeJavaMap)));
        UNESCAPE_JSON = UNESCAPE_JAVA;
        UNESCAPE_HTML3 = new AggregateTranslator(new LookupTranslator(EntityArrays.BASIC_UNESCAPE), new LookupTranslator(EntityArrays.ISO8859_1_UNESCAPE), new NumericEntityUnescaper(new NumericEntityUnescaper.OPTION[0]));
        UNESCAPE_HTML4 = new AggregateTranslator(new LookupTranslator(EntityArrays.BASIC_UNESCAPE), new LookupTranslator(EntityArrays.ISO8859_1_UNESCAPE), new LookupTranslator(EntityArrays.HTML40_EXTENDED_UNESCAPE), new NumericEntityUnescaper(new NumericEntityUnescaper.OPTION[0]));
        UNESCAPE_XML = new AggregateTranslator(new LookupTranslator(EntityArrays.BASIC_UNESCAPE), new LookupTranslator(EntityArrays.APOS_UNESCAPE), new NumericEntityUnescaper(new NumericEntityUnescaper.OPTION[0]));
        UNESCAPE_CSV = new CsvTranslators.CsvUnescaper();
        UNESCAPE_XSI = new XsiUnescaper();
    }

    public static final class Builder {
        private final StringBuilder sb = new StringBuilder();
        private final CharSequenceTranslator translator;

        private Builder(CharSequenceTranslator translator) {
            this.translator = translator;
        }

        public Builder escape(String input) {
            this.sb.append(this.translator.translate(input));
            return this;
        }

        public Builder append(String input) {
            this.sb.append(input);
            return this;
        }

        public String toString() {
            return this.sb.toString();
        }
    }

    static class XsiUnescaper
    extends CharSequenceTranslator {
        private static final char BACKSLASH = '\\';

        XsiUnescaper() {
        }

        @Override
        public int translate(CharSequence input, int index, Writer out) throws IOException {
            if (index != 0) {
                throw new IllegalStateException("XsiUnescaper should never reach the [1] index");
            }
            String s2 = input.toString();
            int segmentStart = 0;
            int searchOffset = 0;
            while (true) {
                int pos;
                if ((pos = s2.indexOf(92, searchOffset)) == -1) {
                    if (segmentStart >= s2.length()) break;
                    out.write(s2.substring(segmentStart));
                    break;
                }
                if (pos > segmentStart) {
                    out.write(s2.substring(segmentStart, pos));
                }
                segmentStart = pos + 1;
                searchOffset = pos + 2;
            }
            return Character.codePointCount(input, 0, input.length());
        }
    }
}

