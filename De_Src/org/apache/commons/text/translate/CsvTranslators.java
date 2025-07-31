/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.text.translate;

import java.io.IOException;
import java.io.Writer;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.translate.SinglePassTranslator;

public final class CsvTranslators {
    private static final char CSV_DELIMITER = ',';
    private static final char CSV_QUOTE = '\"';
    private static final String CSV_QUOTE_STR = String.valueOf('\"');
    private static final String CSV_ESCAPED_QUOTE_STR = CSV_QUOTE_STR + CSV_QUOTE_STR;
    private static final char[] CSV_SEARCH_CHARS = new char[]{',', '\"', '\r', '\n'};

    private CsvTranslators() {
    }

    public static class CsvUnescaper
    extends SinglePassTranslator {
        @Override
        void translateWhole(CharSequence input, Writer out) throws IOException {
            if (input.charAt(0) != '\"' || input.charAt(input.length() - 1) != '\"') {
                out.write(input.toString());
                return;
            }
            String quoteless = input.subSequence(1, input.length() - 1).toString();
            if (StringUtils.containsAny((CharSequence)quoteless, CSV_SEARCH_CHARS)) {
                out.write(StringUtils.replace(quoteless, CSV_ESCAPED_QUOTE_STR, CSV_QUOTE_STR));
            } else {
                out.write(input.toString());
            }
        }
    }

    public static class CsvEscaper
    extends SinglePassTranslator {
        @Override
        void translateWhole(CharSequence input, Writer out) throws IOException {
            String inputSting = input.toString();
            if (StringUtils.containsNone((CharSequence)inputSting, CSV_SEARCH_CHARS)) {
                out.write(inputSting);
            } else {
                out.write(34);
                out.write(StringUtils.replace(inputSting, CSV_QUOTE_STR, CSV_ESCAPED_QUOTE_STR));
                out.write(34);
            }
        }
    }
}

