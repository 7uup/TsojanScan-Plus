/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.DoNotMock;

@DoNotMock(value="Use Interners.new*Interner")
@Beta
@GwtIncompatible
public interface Interner<E> {
    @CanIgnoreReturnValue
    public E intern(E var1);
}

