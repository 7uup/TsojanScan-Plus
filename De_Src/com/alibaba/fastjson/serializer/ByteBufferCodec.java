/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.ByteBuffer;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class ByteBufferCodec
implements ObjectSerializer,
ObjectDeserializer {
    public static final ByteBufferCodec instance = new ByteBufferCodec();

    @Override
    public <T> T deserialze(DefaultJSONParser parser, Type type, Object fieldName) {
        ByteBufferBean bean = parser.parseObject(ByteBufferBean.class);
        return (T)bean.byteBuffer();
    }

    @Override
    public int getFastMatchToken() {
        return 14;
    }

    @Override
    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
        ByteBuffer byteBuf = (ByteBuffer)object;
        byte[] array = byteBuf.array();
        SerializeWriter out = serializer.out;
        out.write(123);
        out.writeFieldName("array");
        out.writeByteArray(array);
        out.writeFieldValue(',', "limit", byteBuf.limit());
        out.writeFieldValue(',', "position", byteBuf.position());
        out.write(125);
    }

    public static class ByteBufferBean {
        public byte[] array;
        public int limit;
        public int position;

        public ByteBuffer byteBuffer() {
            ByteBuffer buf = ByteBuffer.wrap(this.array);
            buf.limit(this.limit);
            buf.position(this.position);
            return buf;
        }
    }
}

