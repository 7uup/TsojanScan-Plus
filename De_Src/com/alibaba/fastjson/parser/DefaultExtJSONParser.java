/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.parser;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.ParserConfig;

@Deprecated
public class DefaultExtJSONParser
extends DefaultJSONParser {
    public DefaultExtJSONParser(String input) {
        this(input, ParserConfig.getGlobalInstance());
    }

    public DefaultExtJSONParser(String input, ParserConfig mapping) {
        super(input, mapping);
    }

    public DefaultExtJSONParser(String input, ParserConfig mapping, int features) {
        super(input, mapping, features);
    }

    public DefaultExtJSONParser(char[] input, int length, ParserConfig mapping, int features) {
        super(input, length, mapping, features);
    }
}

