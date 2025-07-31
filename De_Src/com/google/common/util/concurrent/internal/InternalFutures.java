/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.util.concurrent.internal;

import com.google.common.util.concurrent.internal.InternalFutureFailureAccess;

public final class InternalFutures {
    public static Throwable tryInternalFastPathGetFailure(InternalFutureFailureAccess future) {
        return future.tryInternalFastPathGetFailure();
    }

    private InternalFutures() {
    }
}

