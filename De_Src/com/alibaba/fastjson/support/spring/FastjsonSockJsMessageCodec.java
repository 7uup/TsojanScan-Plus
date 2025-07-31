/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.springframework.web.socket.sockjs.frame.AbstractSockJsMessageCodec
 */
package com.alibaba.fastjson.support.spring;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import java.io.IOException;
import java.io.InputStream;
import org.springframework.web.socket.sockjs.frame.AbstractSockJsMessageCodec;

public class FastjsonSockJsMessageCodec
extends AbstractSockJsMessageCodec {
    public String[] decode(String content) throws IOException {
        return JSON.parseObject(content, String[].class);
    }

    public String[] decodeInputStream(InputStream content) throws IOException {
        return (String[])JSON.parseObject(content, String[].class, new Feature[0]);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected char[] applyJsonQuoting(String content) {
        SerializeWriter out = new SerializeWriter();
        try {
            JSONSerializer serializer = new JSONSerializer(out);
            serializer.write(content);
            char[] cArray = out.toCharArrayForSpringWebSocket();
            return cArray;
        } finally {
            out.close();
        }
    }
}

