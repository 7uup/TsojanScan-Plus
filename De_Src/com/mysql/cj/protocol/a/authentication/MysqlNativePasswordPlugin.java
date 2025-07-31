/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.protocol.a.authentication;

import com.mysql.cj.protocol.AuthenticationPlugin;
import com.mysql.cj.protocol.Protocol;
import com.mysql.cj.protocol.Security;
import com.mysql.cj.protocol.a.NativeConstants;
import com.mysql.cj.protocol.a.NativePacketPayload;
import java.util.List;

public class MysqlNativePasswordPlugin
implements AuthenticationPlugin<NativePacketPayload> {
    private Protocol<NativePacketPayload> protocol;
    private String password = null;

    @Override
    public void init(Protocol<NativePacketPayload> prot) {
        this.protocol = prot;
    }

    @Override
    public void destroy() {
        this.password = null;
    }

    @Override
    public String getProtocolPluginName() {
        return "mysql_native_password";
    }

    @Override
    public boolean requiresConfidentiality() {
        return false;
    }

    @Override
    public boolean isReusable() {
        return true;
    }

    @Override
    public void setAuthenticationParameters(String user, String password) {
        this.password = password;
    }

    @Override
    public boolean nextAuthenticationStep(NativePacketPayload fromServer, List<NativePacketPayload> toServer) {
        toServer.clear();
        NativePacketPayload bresp = null;
        String pwd = this.password;
        bresp = fromServer == null || pwd == null || pwd.length() == 0 ? new NativePacketPayload(new byte[0]) : new NativePacketPayload(Security.scramble411(pwd, fromServer.readBytes(NativeConstants.StringSelfDataType.STRING_TERM), this.protocol.getPasswordCharacterEncoding()));
        toServer.add(bresp);
        return true;
    }
}

