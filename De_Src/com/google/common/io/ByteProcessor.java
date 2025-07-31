/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.io;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.DoNotMock;
import java.io.IOException;

@DoNotMock(value="Implement it normally")
@Beta
@GwtIncompatible
public interface ByteProcessor<T> {
    @CanIgnoreReturnValue
    public boolean processBytes(byte[] var1, int var2, int var3) throws IOException;

    public T getResult();
}

