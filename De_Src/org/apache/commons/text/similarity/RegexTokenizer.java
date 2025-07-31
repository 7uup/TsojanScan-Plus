/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.text.similarity;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.text.similarity.Tokenizer;

class RegexTokenizer
implements Tokenizer<CharSequence> {
    RegexTokenizer() {
    }

    public CharSequence[] tokenize(CharSequence text) {
        Validate.isTrue(StringUtils.isNotBlank(text), "Invalid text", new Object[0]);
        Pattern pattern = Pattern.compile("(\\w)+");
        Matcher matcher = pattern.matcher(text.toString());
        ArrayList<String> tokens = new ArrayList<String>();
        while (matcher.find()) {
            tokens.add(matcher.group(0));
        }
        return tokens.toArray(new String[0]);
    }
}

