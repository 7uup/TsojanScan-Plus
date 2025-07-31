/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.protocol.x;

import com.mysql.cj.ServerVersion;
import com.mysql.cj.protocol.ServerCapabilities;
import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import com.mysql.cj.xdevapi.ExprUtil;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class XServerCapabilities
implements ServerCapabilities {
    private Map<String, MysqlxDatatypes.Any> capabilities;

    public XServerCapabilities(Map<String, MysqlxDatatypes.Any> capabilities) {
        this.capabilities = capabilities;
    }

    public void setCapability(String name, Object value) {
        this.capabilities.put(name, ExprUtil.argObjectToScalarAny(value));
    }

    public boolean hasCapability(String name) {
        return this.capabilities.containsKey(name);
    }

    public String getNodeType() {
        return this.capabilities.get("node_type").getScalar().getVString().getValue().toStringUtf8();
    }

    public boolean getTls() {
        return this.hasCapability("tls") ? this.capabilities.get("tls").getScalar().getVBool() : false;
    }

    public boolean getClientPwdExpireOk() {
        return this.capabilities.get("client.pwd_expire_ok").getScalar().getVBool();
    }

    public List<String> getAuthenticationMechanisms() {
        return this.capabilities.get("authentication.mechanisms").getArray().getValueList().stream().map(v -> v.getScalar().getVString().getValue().toStringUtf8()).collect(Collectors.toList());
    }

    public String getDocFormats() {
        return this.capabilities.get("doc.formats").getScalar().getVString().getValue().toStringUtf8();
    }

    @Override
    public int getCapabilityFlags() {
        return 0;
    }

    @Override
    public void setCapabilityFlags(int capabilityFlags) {
    }

    @Override
    public ServerVersion getServerVersion() {
        return null;
    }

    @Override
    public void setServerVersion(ServerVersion serverVersion) {
    }

    @Override
    public boolean serverSupportsFracSecs() {
        return true;
    }
}

