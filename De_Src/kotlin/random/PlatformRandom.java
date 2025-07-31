/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.random;

import java.util.Random;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.AbstractPlatformRandom;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2={"Lkotlin/random/PlatformRandom;", "Lkotlin/random/AbstractPlatformRandom;", "impl", "Ljava/util/Random;", "(Ljava/util/Random;)V", "getImpl", "()Ljava/util/Random;", "kotlin-stdlib"})
final class PlatformRandom
extends AbstractPlatformRandom {
    @NotNull
    private final Random impl;

    @Override
    @NotNull
    public Random getImpl() {
        return this.impl;
    }

    public PlatformRandom(@NotNull Random impl) {
        Intrinsics.checkNotNullParameter(impl, "impl");
        this.impl = impl;
    }
}

