/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.result;

import com.mysql.cj.result.DefaultValueFactory;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class BinaryStreamValueFactory
extends DefaultValueFactory<InputStream> {
    @Override
    public InputStream createFromBytes(byte[] bytes, int offset, int length) {
        return new ByteArrayInputStream(bytes, offset, length);
    }

    @Override
    public String getTargetTypeName() {
        return InputStream.class.getName();
    }
}

