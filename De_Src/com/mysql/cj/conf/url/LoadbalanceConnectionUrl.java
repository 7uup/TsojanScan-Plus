/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.conf.url;

import com.mysql.cj.conf.ConnectionUrl;
import com.mysql.cj.conf.ConnectionUrlParser;
import com.mysql.cj.conf.HostInfo;
import com.mysql.cj.util.StringUtils;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

public class LoadbalanceConnectionUrl
extends ConnectionUrl {
    public LoadbalanceConnectionUrl(ConnectionUrlParser connStrParser, Properties info) {
        super(connStrParser, info);
        this.type = ConnectionUrl.Type.LOADBALANCE_CONNECTION;
    }

    public LoadbalanceConnectionUrl(List<HostInfo> hosts, Map<String, String> properties) {
        this.originalConnStr = ConnectionUrl.Type.LOADBALANCE_CONNECTION.getScheme() + "//**internally_generated**" + System.currentTimeMillis() + "**";
        this.type = ConnectionUrl.Type.LOADBALANCE_CONNECTION;
        this.hosts.addAll(hosts);
        this.properties.putAll(properties);
        this.injectPerTypeProperties(this.properties);
        this.setupPropertiesTransformer();
    }

    @Override
    protected void injectPerTypeProperties(Map<String, String> props) {
        if (props.containsKey("loadBalanceAutoCommitStatementThreshold")) {
            try {
                int autoCommitSwapThreshold = Integer.parseInt(props.get("loadBalanceAutoCommitStatementThreshold"));
                if (autoCommitSwapThreshold > 0) {
                    String queryInterceptors = props.get("queryInterceptors");
                    String lbi = "com.mysql.cj.jdbc.ha.LoadBalancedAutoCommitInterceptor";
                    if (StringUtils.isNullOrEmpty(queryInterceptors)) {
                        props.put("queryInterceptors", lbi);
                    } else {
                        props.put("queryInterceptors", queryInterceptors + "," + lbi);
                    }
                }
            } catch (Throwable throwable) {
                // empty catch block
            }
        }
    }

    public List<String> getHostInfoListAsHostPortPairs() {
        return this.hosts.stream().map(hi -> hi.getHostPortPair()).collect(Collectors.toList());
    }

    public List<HostInfo> getHostInfoListFromHostPortPairs(Collection<String> hostPortPairs) {
        return hostPortPairs.stream().map(this::getHostOrSpawnIsolated).collect(Collectors.toList());
    }
}

