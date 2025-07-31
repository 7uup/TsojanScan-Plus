/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.protobuf;

import com.google.protobuf.RpcCallback;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public interface RpcController {
    public void reset();

    public boolean failed();

    public String errorText();

    public void startCancel();

    public void setFailed(String var1);

    public boolean isCanceled();

    public void notifyOnCancel(RpcCallback<Object> var1);
}

