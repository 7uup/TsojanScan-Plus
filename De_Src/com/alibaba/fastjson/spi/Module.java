/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.spi;

import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeConfig;

public interface Module {
    public ObjectDeserializer createDeserializer(ParserConfig var1, Class var2);

    public ObjectSerializer createSerializer(SerializeConfig var1, Class var2);
}

