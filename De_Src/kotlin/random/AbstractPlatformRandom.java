/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.random;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlin.random.RandomKt;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\b \u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0016J\b\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\u0016J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\b\u0010\u0013\u001a\u00020\bH\u0016J\u0010\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\bH\u0016J\b\u0010\u0015\u001a\u00020\u0016H\u0016R\u0012\u0010\u0003\u001a\u00020\u0004X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0017"}, d2={"Lkotlin/random/AbstractPlatformRandom;", "Lkotlin/random/Random;", "()V", "impl", "Ljava/util/Random;", "getImpl", "()Ljava/util/Random;", "nextBits", "", "bitCount", "nextBoolean", "", "nextBytes", "", "array", "nextDouble", "", "nextFloat", "", "nextInt", "until", "nextLong", "", "kotlin-stdlib"})
public abstract class AbstractPlatformRandom
extends Random {
    @NotNull
    public abstract java.util.Random getImpl();

    @Override
    public int nextBits(int bitCount) {
        return RandomKt.takeUpperBits(this.getImpl().nextInt(), bitCount);
    }

    @Override
    public int nextInt() {
        return this.getImpl().nextInt();
    }

    @Override
    public int nextInt(int until) {
        return this.getImpl().nextInt(until);
    }

    @Override
    public long nextLong() {
        return this.getImpl().nextLong();
    }

    @Override
    public boolean nextBoolean() {
        return this.getImpl().nextBoolean();
    }

    @Override
    public double nextDouble() {
        return this.getImpl().nextDouble();
    }

    @Override
    public float nextFloat() {
        return this.getImpl().nextFloat();
    }

    @Override
    @NotNull
    public byte[] nextBytes(@NotNull byte[] array) {
        Intrinsics.checkNotNullParameter(array, "array");
        byte[] byArray = array;
        boolean bl = false;
        boolean bl2 = false;
        byte[] it = byArray;
        boolean bl3 = false;
        this.getImpl().nextBytes(it);
        return byArray;
    }
}

