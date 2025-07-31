/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin;

import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Triple;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=2, d1={"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\u001a2\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0002H\u00022\u0006\u0010\u0004\u001a\u0002H\u0003H\u0086\u0004\u00a2\u0006\u0002\u0010\u0005\u001a\"\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\b0\u0007\"\u0004\b\u0000\u0010\b*\u000e\u0012\u0004\u0012\u0002H\b\u0012\u0004\u0012\u0002H\b0\u0001\u001a(\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\b0\u0007\"\u0004\b\u0000\u0010\b*\u0014\u0012\u0004\u0012\u0002H\b\u0012\u0004\u0012\u0002H\b\u0012\u0004\u0012\u0002H\b0\t\u00a8\u0006\n"}, d2={"to", "Lkotlin/Pair;", "A", "B", "that", "(Ljava/lang/Object;Ljava/lang/Object;)Lkotlin/Pair;", "toList", "", "T", "Lkotlin/Triple;", "kotlin-stdlib"})
@JvmName(name="TuplesKt")
public final class TuplesKt {
    @NotNull
    public static final <A, B> Pair<A, B> to(A $this$to, B that) {
        return new Pair<A, B>($this$to, that);
    }

    @NotNull
    public static final <T> List<T> toList(@NotNull Pair<? extends T, ? extends T> $this$toList) {
        Intrinsics.checkNotNullParameter($this$toList, "$this$toList");
        return CollectionsKt.listOf($this$toList.getFirst(), $this$toList.getSecond());
    }

    @NotNull
    public static final <T> List<T> toList(@NotNull Triple<? extends T, ? extends T, ? extends T> $this$toList) {
        Intrinsics.checkNotNullParameter($this$toList, "$this$toList");
        return CollectionsKt.listOf($this$toList.getFirst(), $this$toList.getSecond(), $this$toList.getThird());
    }
}

