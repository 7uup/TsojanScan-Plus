/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.random;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0014J\b\u0010\f\u001a\u00020\bH\u0016J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u0010\u0015\u001a\u00020\nH\u0016J\u0010\u0010\u0015\u001a\u00020\n2\u0006\u0010\u0016\u001a\u00020\nH\u0016J\b\u0010\u0017\u001a\u00020\u0018H\u0016J\u0010\u0010\u0019\u001a\u00020\u000e2\u0006\u0010\u001a\u001a\u00020\u0018H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001b"}, d2={"Lkotlin/random/KotlinRandom;", "Ljava/util/Random;", "impl", "Lkotlin/random/Random;", "(Lkotlin/random/Random;)V", "getImpl", "()Lkotlin/random/Random;", "seedInitialized", "", "next", "", "bits", "nextBoolean", "nextBytes", "", "bytes", "", "nextDouble", "", "nextFloat", "", "nextInt", "bound", "nextLong", "", "setSeed", "seed", "kotlin-stdlib"})
final class KotlinRandom
extends java.util.Random {
    private boolean seedInitialized;
    @NotNull
    private final Random impl;

    @Override
    protected int next(int bits) {
        return this.impl.nextBits(bits);
    }

    @Override
    public int nextInt() {
        return this.impl.nextInt();
    }

    @Override
    public int nextInt(int bound) {
        return this.impl.nextInt(bound);
    }

    @Override
    public boolean nextBoolean() {
        return this.impl.nextBoolean();
    }

    @Override
    public long nextLong() {
        return this.impl.nextLong();
    }

    @Override
    public float nextFloat() {
        return this.impl.nextFloat();
    }

    @Override
    public double nextDouble() {
        return this.impl.nextDouble();
    }

    @Override
    public void nextBytes(@NotNull byte[] bytes) {
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        this.impl.nextBytes(bytes);
    }

    @Override
    public void setSeed(long seed) {
        if (this.seedInitialized) {
            throw (Throwable)new UnsupportedOperationException("Setting seed is not supported.");
        }
        this.seedInitialized = true;
    }

    @NotNull
    public final Random getImpl() {
        return this.impl;
    }

    public KotlinRandom(@NotNull Random impl) {
        Intrinsics.checkNotNullParameter(impl, "impl");
        this.impl = impl;
    }
}

