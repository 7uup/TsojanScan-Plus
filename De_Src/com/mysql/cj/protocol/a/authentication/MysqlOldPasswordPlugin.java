/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.protocol.a.authentication;

import com.mysql.cj.protocol.AuthenticationPlugin;
import com.mysql.cj.protocol.Protocol;
import com.mysql.cj.protocol.a.NativeConstants;
import com.mysql.cj.protocol.a.NativePacketPayload;
import com.mysql.cj.util.StringUtils;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class MysqlOldPasswordPlugin
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
        return "mysql_old_password";
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
        if (fromServer == null || pwd == null || pwd.length() == 0) {
            bresp = new NativePacketPayload(new byte[0]);
        } else {
            bresp = new NativePacketPayload(StringUtils.getBytes(MysqlOldPasswordPlugin.newCrypt(pwd, fromServer.readString(NativeConstants.StringSelfDataType.STRING_TERM, null).substring(0, 8), this.protocol.getPasswordCharacterEncoding())));
            bresp.setPosition(bresp.getPayloadLength());
            bresp.writeInteger(NativeConstants.IntegerDataType.INT1, 0L);
            bresp.setPosition(0);
        }
        toServer.add(bresp);
        return true;
    }

    private static String newCrypt(String password, String seed, String encoding) {
        byte b;
        double d;
        int i;
        if (password == null || password.length() == 0) {
            return password;
        }
        long[] pw = MysqlOldPasswordPlugin.newHash(seed.getBytes());
        long[] msg = MysqlOldPasswordPlugin.hashPre41Password(password, encoding);
        long max = 0x3FFFFFFFL;
        long seed1 = (pw[0] ^ msg[0]) % max;
        long seed2 = (pw[1] ^ msg[1]) % max;
        char[] chars = new char[seed.length()];
        for (i = 0; i < seed.length(); ++i) {
            seed1 = (seed1 * 3L + seed2) % max;
            seed2 = (seed1 + seed2 + 33L) % max;
            d = (double)seed1 / (double)max;
            b = (byte)Math.floor(d * 31.0 + 64.0);
            chars[i] = (char)b;
        }
        seed1 = (seed1 * 3L + seed2) % max;
        seed2 = (seed1 + seed2 + 33L) % max;
        d = (double)seed1 / (double)max;
        b = (byte)Math.floor(d * 31.0);
        i = 0;
        while (i < seed.length()) {
            int n = i++;
            chars[n] = (char)(chars[n] ^ (char)b);
        }
        return new String(chars);
    }

    private static long[] hashPre41Password(String password, String encoding) {
        try {
            return MysqlOldPasswordPlugin.newHash(password.replaceAll("\\s", "").getBytes(encoding));
        } catch (UnsupportedEncodingException e) {
            return new long[0];
        }
    }

    private static long[] newHash(byte[] password) {
        long nr = 1345345333L;
        long add = 7L;
        long nr2 = 305419889L;
        for (byte b : password) {
            long tmp = 0xFF & b;
            nr ^= ((nr & 0x3FL) + add) * tmp + (nr << 8);
            nr2 += nr2 << 8 ^ nr;
            add += tmp;
        }
        long[] result = new long[]{nr & Integer.MAX_VALUE, nr2 & Integer.MAX_VALUE};
        return result;
    }
}

