/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.random;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.internal.PlatformImplementationsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.PlatformRandomKt;
import kotlin.random.RandomKt;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\b'\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H&J\b\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0016J$\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\u00042\b\b\u0002\u0010\f\u001a\u00020\u0004H\u0016J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u0004H\u0016J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fH\u0016J\u0018\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fH\u0016J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\b\u0010\u0014\u001a\u00020\u0004H\u0016J\u0010\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u0004H\u0016J\u0018\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u0004H\u0016J\b\u0010\u0015\u001a\u00020\u0016H\u0016J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0010\u001a\u00020\u0016H\u0016J\u0018\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0011\u001a\u00020\u00162\u0006\u0010\u0010\u001a\u00020\u0016H\u0016\u00a8\u0006\u0018"}, d2={"Lkotlin/random/Random;", "", "()V", "nextBits", "", "bitCount", "nextBoolean", "", "nextBytes", "", "array", "fromIndex", "toIndex", "size", "nextDouble", "", "until", "from", "nextFloat", "", "nextInt", "nextLong", "", "Default", "kotlin-stdlib"})
@SinceKotlin(version="1.3")
public abstract class Random {
    private static final Random defaultRandom;
    public static final Default Default;

    public abstract int nextBits(int var1);

    public int nextInt() {
        return this.nextBits(32);
    }

    public int nextInt(int until) {
        return this.nextInt(0, until);
    }

    public int nextInt(int from, int until) {
        int rnd;
        int n;
        RandomKt.checkRangeBounds(from, until);
        int n2 = until - from;
        if (n2 > 0 || n2 == Integer.MIN_VALUE) {
            int n3;
            if ((n2 & -n2) == n2) {
                int bitCount = RandomKt.fastLog2(n2);
                n3 = this.nextBits(bitCount);
            } else {
                int bits;
                int v = 0;
                while ((bits = this.nextInt() >>> 1) - (v = bits % n2) + (n2 - 1) < 0) {
                }
                n3 = v;
            }
            int rnd2 = n3;
            return from + rnd2;
        }
        while (from > (n = (rnd = this.nextInt())) || until <= n) {
        }
        return rnd;
    }

    public long nextLong() {
        return ((long)this.nextInt() << 32) + (long)this.nextInt();
    }

    public long nextLong(long until) {
        return this.nextLong(0L, until);
    }

    public long nextLong(long from, long until) {
        long rnd;
        long l;
        RandomKt.checkRangeBounds(from, until);
        long n = until - from;
        if (n > 0L) {
            long rnd2 = 0L;
            if ((n & -n) == n) {
                long l2;
                int bitCount;
                int nLow = (int)n;
                int nHigh = (int)(n >>> 32);
                if (nLow != 0) {
                    bitCount = RandomKt.fastLog2(nLow);
                    l2 = (long)this.nextBits(bitCount) & 0xFFFFFFFFL;
                } else if (nHigh == 1) {
                    l2 = (long)this.nextInt() & 0xFFFFFFFFL;
                } else {
                    bitCount = RandomKt.fastLog2(nHigh);
                    l2 = ((long)this.nextBits(bitCount) << 32) + (long)this.nextInt();
                }
                rnd2 = l2;
            } else {
                long bits;
                long v = 0L;
                while ((bits = this.nextLong() >>> 1) - (v = bits % n) + (n - 1L) < 0L) {
                }
                rnd2 = v;
            }
            return from + rnd2;
        }
        while (from > (l = (rnd = this.nextLong())) || until <= l) {
        }
        return rnd;
    }

    public boolean nextBoolean() {
        return this.nextBits(1) != 0;
    }

    public double nextDouble() {
        return PlatformRandomKt.doubleFromParts(this.nextBits(26), this.nextBits(27));
    }

    public double nextDouble(double until) {
        return this.nextDouble(0.0, until);
    }

    /*
     * Unable to fully structure code
     */
    public double nextDouble(double from, double until) {
        RandomKt.checkRangeBounds(from, until);
        var9_4 = size = until - from;
        var11_5 = false;
        if (!Double.isInfinite(var9_4)) ** GOTO lbl-1000
        var9_4 = from;
        var11_5 = false;
        var12_6 = var9_4;
        var14_7 = false;
        if (Double.isInfinite(var12_6)) ** GOTO lbl-1000
        var12_6 = var9_4;
        var14_7 = false;
        if (!Double.isNaN(var12_6)) {
            v0 = true;
        } else lbl-1000:
        // 2 sources

        {
            v0 = false;
        }
        if (!v0) ** GOTO lbl-1000
        var9_4 = until;
        var11_5 = false;
        var12_6 = var9_4;
        var14_7 = false;
        if (Double.isInfinite(var12_6)) ** GOTO lbl-1000
        var12_6 = var9_4;
        var14_7 = false;
        if (!Double.isNaN(var12_6)) {
            v1 = true;
        } else lbl-1000:
        // 2 sources

        {
            v1 = false;
        }
        if (v1) {
            r1 = this.nextDouble() * (until / (double)2 - from / (double)2);
            v2 = from + r1 + r1;
        } else lbl-1000:
        // 3 sources

        {
            v2 = r = from + this.nextDouble() * size;
        }
        if (r >= until) {
            var9_4 = until;
            var11_5 = false;
            v3 = Math.nextAfter(var9_4, -Infinity);
        } else {
            v3 = r;
        }
        return v3;
    }

    public float nextFloat() {
        return (float)this.nextBits(24) / (float)0x1000000;
    }

    /*
     * WARNING - void declaration
     */
    @NotNull
    public byte[] nextBytes(@NotNull byte[] array, int fromIndex, int toIndex) {
        Intrinsics.checkNotNullParameter(array, "array");
        int n = fromIndex;
        n = 0 <= n && array.length >= n && 0 <= (n = toIndex) && array.length >= n ? 1 : 0;
        boolean bl = false;
        boolean bl2 = false;
        if (n == 0) {
            boolean bl3 = false;
            String string = "fromIndex (" + fromIndex + ") or toIndex (" + toIndex + ") are out of range: 0.." + array.length + '.';
            throw (Throwable)new IllegalArgumentException(string.toString());
        }
        n = fromIndex <= toIndex ? 1 : 0;
        bl = false;
        bl2 = false;
        if (n == 0) {
            boolean bl4 = false;
            String string = "fromIndex (" + fromIndex + ") must be not greater than toIndex (" + toIndex + ").";
            throw (Throwable)new IllegalArgumentException(string.toString());
        }
        int steps = (toIndex - fromIndex) / 4;
        int position = fromIndex;
        bl2 = false;
        int bl4 = 0;
        bl4 = 0;
        int n2 = steps;
        while (bl4 < n2) {
            int it = bl4++;
            boolean bl5 = false;
            int v = this.nextInt();
            array[position] = (byte)v;
            array[position + 1] = (byte)(v >>> 8);
            array[position + 2] = (byte)(v >>> 16);
            array[position + 3] = (byte)(v >>> 24);
            position += 4;
        }
        int remainder = toIndex - position;
        int vr = this.nextBits(remainder * 8);
        n2 = 0;
        int n3 = remainder;
        while (n2 < n3) {
            void i;
            array[position + i] = (byte)(vr >>> i * 8);
            ++i;
        }
        return array;
    }

    public static /* synthetic */ byte[] nextBytes$default(Random random, byte[] byArray, int n, int n2, int n3, Object object) {
        if (object != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: nextBytes");
        }
        if ((n3 & 2) != 0) {
            n = 0;
        }
        if ((n3 & 4) != 0) {
            n2 = byArray.length;
        }
        return random.nextBytes(byArray, n, n2);
    }

    @NotNull
    public byte[] nextBytes(@NotNull byte[] array) {
        Intrinsics.checkNotNullParameter(array, "array");
        return this.nextBytes(array, 0, array.length);
    }

    @NotNull
    public byte[] nextBytes(int size) {
        return this.nextBytes(new byte[size]);
    }

    static {
        Default = new Default(null);
        boolean bl = false;
        defaultRandom = PlatformImplementationsKt.IMPLEMENTATIONS.defaultPlatformRandom();
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0016J\b\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0016J \u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u0005H\u0016J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u0005H\u0016J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010H\u0016J\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010H\u0016J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u0010\u0015\u001a\u00020\u0005H\u0016J\u0010\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u0005H\u0016J\u0018\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u0005H\u0016J\b\u0010\u0016\u001a\u00020\u0017H\u0016J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0011\u001a\u00020\u0017H\u0016J\u0018\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0012\u001a\u00020\u00172\u0006\u0010\u0011\u001a\u00020\u0017H\u0016R\u000e\u0010\u0003\u001a\u00020\u0001X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0018"}, d2={"Lkotlin/random/Random$Default;", "Lkotlin/random/Random;", "()V", "defaultRandom", "nextBits", "", "bitCount", "nextBoolean", "", "nextBytes", "", "array", "fromIndex", "toIndex", "size", "nextDouble", "", "until", "from", "nextFloat", "", "nextInt", "nextLong", "", "kotlin-stdlib"})
    public static final class Default
    extends Random {
        @Override
        public int nextBits(int bitCount) {
            return defaultRandom.nextBits(bitCount);
        }

        @Override
        public int nextInt() {
            return defaultRandom.nextInt();
        }

        @Override
        public int nextInt(int until) {
            return defaultRandom.nextInt(until);
        }

        @Override
        public int nextInt(int from, int until) {
            return defaultRandom.nextInt(from, until);
        }

        @Override
        public long nextLong() {
            return defaultRandom.nextLong();
        }

        @Override
        public long nextLong(long until) {
            return defaultRandom.nextLong(until);
        }

        @Override
        public long nextLong(long from, long until) {
            return defaultRandom.nextLong(from, until);
        }

        @Override
        public boolean nextBoolean() {
            return defaultRandom.nextBoolean();
        }

        @Override
        public double nextDouble() {
            return defaultRandom.nextDouble();
        }

        @Override
        public double nextDouble(double until) {
            return defaultRandom.nextDouble(until);
        }

        @Override
        public double nextDouble(double from, double until) {
            return defaultRandom.nextDouble(from, until);
        }

        @Override
        public float nextFloat() {
            return defaultRandom.nextFloat();
        }

        @Override
        @NotNull
        public byte[] nextBytes(@NotNull byte[] array) {
            Intrinsics.checkNotNullParameter(array, "array");
            return defaultRandom.nextBytes(array);
        }

        @Override
        @NotNull
        public byte[] nextBytes(int size) {
            return defaultRandom.nextBytes(size);
        }

        @Override
        @NotNull
        public byte[] nextBytes(@NotNull byte[] array, int fromIndex, int toIndex) {
            Intrinsics.checkNotNullParameter(array, "array");
            return defaultRandom.nextBytes(array, fromIndex, toIndex);
        }

        private Default() {
        }

        public /* synthetic */ Default(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

