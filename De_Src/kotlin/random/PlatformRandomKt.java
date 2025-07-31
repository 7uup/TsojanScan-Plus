/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.random;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.internal.InlineOnly;
import kotlin.internal.PlatformImplementationsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.AbstractPlatformRandom;
import kotlin.random.KotlinRandom;
import kotlin.random.PlatformRandom;
import kotlin.random.Random;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=2, d1={"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\t\u0010\u0000\u001a\u00020\u0001H\u0081\b\u001a\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0000\u001a\f\u0010\u0007\u001a\u00020\b*\u00020\u0001H\u0007\u001a\f\u0010\t\u001a\u00020\u0001*\u00020\bH\u0007\u00a8\u0006\n"}, d2={"defaultPlatformRandom", "Lkotlin/random/Random;", "doubleFromParts", "", "hi26", "", "low27", "asJavaRandom", "Ljava/util/Random;", "asKotlinRandom", "kotlin-stdlib"})
public final class PlatformRandomKt {
    @SinceKotlin(version="1.3")
    @NotNull
    public static final java.util.Random asJavaRandom(@NotNull Random $this$asJavaRandom) {
        Object object;
        Intrinsics.checkNotNullParameter($this$asJavaRandom, "$this$asJavaRandom");
        Random random = $this$asJavaRandom;
        if (!(random instanceof AbstractPlatformRandom)) {
            random = null;
        }
        if ((object = (AbstractPlatformRandom)random) == null || (object = ((AbstractPlatformRandom)object).getImpl()) == null) {
            object = new KotlinRandom($this$asJavaRandom);
        }
        return object;
    }

    @SinceKotlin(version="1.3")
    @NotNull
    public static final Random asKotlinRandom(@NotNull java.util.Random $this$asKotlinRandom) {
        Object object;
        Intrinsics.checkNotNullParameter($this$asKotlinRandom, "$this$asKotlinRandom");
        java.util.Random random = $this$asKotlinRandom;
        if (!(random instanceof KotlinRandom)) {
            random = null;
        }
        if ((object = (KotlinRandom)random) == null || (object = ((KotlinRandom)object).getImpl()) == null) {
            object = new PlatformRandom($this$asKotlinRandom);
        }
        return object;
    }

    @InlineOnly
    private static final Random defaultPlatformRandom() {
        int $i$f$defaultPlatformRandom = 0;
        return PlatformImplementationsKt.IMPLEMENTATIONS.defaultPlatformRandom();
    }

    public static final double doubleFromParts(int hi26, int low27) {
        return (double)(((long)hi26 << 27) + (long)low27) / (double)0x20000000000000L;
    }
}

