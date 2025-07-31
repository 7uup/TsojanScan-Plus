/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.xdevapi;

import com.mysql.cj.exceptions.AssertionFailedException;
import com.mysql.cj.result.DefaultValueFactory;
import com.mysql.cj.util.StringUtils;
import com.mysql.cj.xdevapi.DbDoc;
import com.mysql.cj.xdevapi.JsonParser;
import java.io.IOException;
import java.io.StringReader;

public class DbDocValueFactory
extends DefaultValueFactory<DbDoc> {
    private String encoding;

    public DbDocValueFactory() {
    }

    public DbDocValueFactory(String encoding) {
        this.encoding = encoding;
    }

    @Override
    public DbDoc createFromBytes(byte[] bytes, int offset, int length) {
        try {
            return JsonParser.parseDoc(new StringReader(StringUtils.toString(bytes, offset, length, this.encoding)));
        } catch (IOException ex) {
            throw AssertionFailedException.shouldNotHappen(ex);
        }
    }

    @Override
    public DbDoc createFromNull() {
        return null;
    }

    @Override
    public String getTargetTypeName() {
        return DbDoc.class.getName();
    }
}

