/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.protocol;

import com.mysql.cj.Messages;
import com.mysql.cj.Session;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.UnknownHostException;

public interface SocketMetadata {
    default public boolean isLocallyConnected(Session sess) {
        String processHost = sess.getProcessHost();
        return this.isLocallyConnected(sess, processHost);
    }

    default public boolean isLocallyConnected(Session sess, String processHost) {
        if (processHost != null) {
            sess.getLog().logDebug(Messages.getString("SocketMetadata.0", new Object[]{processHost}));
            int endIndex = processHost.lastIndexOf(":");
            if (endIndex != -1) {
                processHost = processHost.substring(0, endIndex);
                try {
                    InetAddress[] whereMysqlThinksIConnectedFrom = InetAddress.getAllByName(processHost);
                    SocketAddress remoteSocketAddr = sess.getRemoteSocketAddress();
                    if (remoteSocketAddr instanceof InetSocketAddress) {
                        InetAddress whereIConnectedTo = ((InetSocketAddress)remoteSocketAddr).getAddress();
                        for (InetAddress hostAddr : whereMysqlThinksIConnectedFrom) {
                            if (hostAddr.equals(whereIConnectedTo)) {
                                sess.getLog().logDebug(Messages.getString("SocketMetadata.1", new Object[]{hostAddr, whereIConnectedTo}));
                                return true;
                            }
                            sess.getLog().logDebug(Messages.getString("SocketMetadata.2", new Object[]{hostAddr, whereIConnectedTo}));
                        }
                    } else {
                        sess.getLog().logDebug(Messages.getString("SocketMetadata.3", new Object[]{remoteSocketAddr}));
                    }
                    return false;
                } catch (UnknownHostException e) {
                    sess.getLog().logWarn(Messages.getString("Connection.CantDetectLocalConnect", new Object[]{processHost}), e);
                    return false;
                }
            }
            sess.getLog().logWarn(Messages.getString("SocketMetadata.4", new Object[]{processHost}));
            return false;
        }
        return false;
    }
}

