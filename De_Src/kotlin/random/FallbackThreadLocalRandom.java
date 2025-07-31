/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.random;

import java.util.Random;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.AbstractPlatformRandom;
import kotlin.random.FallbackThreadLocalRandom;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\u001b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\b\u0003*\u0001\b\b\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u00048VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0010\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\t\u00a8\u0006\n"}, d2={"Lkotlin/random/FallbackThreadLocalRandom;", "Lkotlin/random/AbstractPlatformRandom;", "()V", "impl", "Ljava/util/Random;", "getImpl", "()Ljava/util/Random;", "implStorage", "kotlin/random/FallbackThreadLocalRandom$implStorage$1", "Lkotlin/random/FallbackThreadLocalRandom$implStorage$1;", "kotlin-stdlib"})
public final class FallbackThreadLocalRandom
extends AbstractPlatformRandom {
    private final implStorage.1 implStorage = new ThreadLocal<Random>(){

        @NotNull
        protected Random initialValue() {
            return new Random();
        }
    };

    @Override
    @NotNull
    public Random getImpl() {
        Object t = this.implStorage.get();
        Intrinsics.checkNotNullExpressionValue(t, "implStorage.get()");
        return (Random)t;
    }
}

