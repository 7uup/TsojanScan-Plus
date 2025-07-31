/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.protocol;

import com.mysql.cj.CharsetMapping;
import com.mysql.cj.Messages;
import com.mysql.cj.ServerVersion;
import com.mysql.cj.conf.PropertySet;
import com.mysql.cj.exceptions.ExceptionFactory;
import com.mysql.cj.exceptions.ExceptionInterceptor;
import com.mysql.cj.protocol.Message;
import com.mysql.cj.protocol.Protocol;
import com.mysql.cj.protocol.ServerSession;

public interface AuthenticationProvider<M extends Message> {
    public void init(Protocol<M> var1, PropertySet var2, ExceptionInterceptor var3);

    public void connect(ServerSession var1, String var2, String var3, String var4);

    public void changeUser(ServerSession var1, String var2, String var3, String var4);

    public String getEncodingForHandshake();

    public static byte getCharsetForHandshake(String enc, ServerVersion sv) {
        int charsetIndex = 0;
        if (enc != null) {
            charsetIndex = CharsetMapping.getCollationIndexForJavaEncoding(enc, sv);
        }
        if (charsetIndex == 0) {
            charsetIndex = 33;
        }
        if (charsetIndex > 255) {
            throw ExceptionFactory.createException(Messages.getString("MysqlIO.113", new Object[]{enc}));
        }
        return (byte)charsetIndex;
    }
}

