/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.protocol.a.authentication;

import com.mysql.cj.Messages;
import com.mysql.cj.exceptions.CJException;
import com.mysql.cj.exceptions.ExceptionFactory;
import com.mysql.cj.exceptions.UnableToConnectException;
import com.mysql.cj.protocol.Protocol;
import com.mysql.cj.protocol.Security;
import com.mysql.cj.protocol.a.NativeConstants;
import com.mysql.cj.protocol.a.NativePacketPayload;
import com.mysql.cj.protocol.a.authentication.Sha256PasswordPlugin;
import com.mysql.cj.util.StringUtils;
import java.security.DigestException;
import java.util.List;

public class CachingSha2PasswordPlugin
extends Sha256PasswordPlugin {
    public static String PLUGIN_NAME = "caching_sha2_password";
    private AuthStage stage = AuthStage.FAST_AUTH_SEND_SCRAMBLE;

    @Override
    public void init(Protocol<NativePacketPayload> prot) {
        super.init(prot);
        this.stage = AuthStage.FAST_AUTH_SEND_SCRAMBLE;
    }

    @Override
    public void reset() {
        this.stage = AuthStage.FAST_AUTH_SEND_SCRAMBLE;
    }

    @Override
    public void destroy() {
        this.stage = AuthStage.FAST_AUTH_SEND_SCRAMBLE;
        super.destroy();
    }

    @Override
    public String getProtocolPluginName() {
        return PLUGIN_NAME;
    }

    @Override
    public boolean nextAuthenticationStep(NativePacketPayload fromServer, List<NativePacketPayload> toServer) {
        toServer.clear();
        if (this.password == null || this.password.length() == 0 || fromServer == null) {
            NativePacketPayload bresp = new NativePacketPayload(new byte[]{0});
            toServer.add(bresp);
        } else {
            try {
                if (this.stage == AuthStage.FAST_AUTH_SEND_SCRAMBLE) {
                    this.seed = fromServer.readString(NativeConstants.StringSelfDataType.STRING_TERM, null);
                    toServer.add(new NativePacketPayload(Security.scrambleCachingSha2(StringUtils.getBytes(this.password, this.protocol.getPasswordCharacterEncoding()), this.seed.getBytes())));
                    this.stage = AuthStage.FAST_AUTH_READ_RESULT;
                    return true;
                }
                if (this.stage == AuthStage.FAST_AUTH_READ_RESULT) {
                    byte fastAuthResult = fromServer.readBytes(NativeConstants.StringLengthDataType.STRING_FIXED, 1)[0];
                    switch (fastAuthResult) {
                        case 3: {
                            this.stage = AuthStage.FAST_AUTH_COMPLETE;
                            return true;
                        }
                        case 4: {
                            this.stage = AuthStage.FULL_AUTH;
                            break;
                        }
                        default: {
                            throw ExceptionFactory.createException("Unknown server response after fast auth.", this.protocol.getExceptionInterceptor());
                        }
                    }
                }
                if (this.protocol.getSocketConnection().isSSLEstablished()) {
                    NativePacketPayload bresp = new NativePacketPayload(StringUtils.getBytes(this.password, this.protocol.getPasswordCharacterEncoding()));
                    bresp.setPosition(bresp.getPayloadLength());
                    bresp.writeInteger(NativeConstants.IntegerDataType.INT1, 0L);
                    bresp.setPosition(0);
                    toServer.add(bresp);
                } else if (this.serverRSAPublicKeyFile.getValue() != null) {
                    NativePacketPayload bresp = new NativePacketPayload(this.encryptPassword());
                    toServer.add(bresp);
                } else {
                    if (!this.protocol.getPropertySet().getBooleanProperty("allowPublicKeyRetrieval").getValue().booleanValue()) {
                        throw ExceptionFactory.createException(UnableToConnectException.class, Messages.getString("Sha256PasswordPlugin.2"), this.protocol.getExceptionInterceptor());
                    }
                    if (this.publicKeyRequested && fromServer.getPayloadLength() > 20) {
                        this.publicKeyString = fromServer.readString(NativeConstants.StringSelfDataType.STRING_TERM, null);
                        NativePacketPayload bresp = new NativePacketPayload(this.encryptPassword());
                        toServer.add(bresp);
                        this.publicKeyRequested = false;
                    } else {
                        NativePacketPayload bresp = new NativePacketPayload(new byte[]{2});
                        toServer.add(bresp);
                        this.publicKeyRequested = true;
                    }
                }
            } catch (CJException | DigestException e) {
                throw ExceptionFactory.createException(e.getMessage(), e, this.protocol.getExceptionInterceptor());
            }
        }
        return true;
    }

    @Override
    protected byte[] encryptPassword() {
        if (this.protocol.versionMeetsMinimum(8, 0, 5)) {
            return super.encryptPassword();
        }
        return super.encryptPassword("RSA/ECB/PKCS1Padding");
    }

    public static enum AuthStage {
        FAST_AUTH_SEND_SCRAMBLE,
        FAST_AUTH_READ_RESULT,
        FAST_AUTH_COMPLETE,
        FULL_AUTH;

    }
}

