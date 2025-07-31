/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.io;

import java.nio.charset.CharsetDecoder;
import kotlin.Metadata;
import kotlin.io.LineReader;
import kotlin.jvm.internal.MutablePropertyReference0Impl;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=3)
final class LineReader$readLine$1
extends MutablePropertyReference0Impl {
    LineReader$readLine$1(LineReader lineReader) {
        super((Object)lineReader, LineReader.class, "decoder", "getDecoder()Ljava/nio/charset/CharsetDecoder;", 0);
    }

    @Override
    @Nullable
    public Object get() {
        return LineReader.access$getDecoder$p((LineReader)this.receiver);
    }

    @Override
    public void set(@Nullable Object value) {
        LineReader.access$setDecoder$p((LineReader)this.receiver, (CharsetDecoder)value);
    }
}

