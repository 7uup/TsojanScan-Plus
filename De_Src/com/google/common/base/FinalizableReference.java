/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.base;

import com.google.common.annotations.GwtIncompatible;
import com.google.errorprone.annotations.DoNotMock;

@DoNotMock(value="Use an instance of one of the Finalizable*Reference classes")
@GwtIncompatible
public interface FinalizableReference {
    public void finalizeReferent();
}

