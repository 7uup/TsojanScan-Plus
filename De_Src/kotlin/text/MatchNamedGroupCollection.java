/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.text;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.text.MatchGroup;
import kotlin.text.MatchGroupCollection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bg\u0018\u00002\u00020\u0001J\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a6\u0002\u00a8\u0006\u0006"}, d2={"Lkotlin/text/MatchNamedGroupCollection;", "Lkotlin/text/MatchGroupCollection;", "get", "Lkotlin/text/MatchGroup;", "name", "", "kotlin-stdlib"})
@SinceKotlin(version="1.1")
public interface MatchNamedGroupCollection
extends MatchGroupCollection {
    @Nullable
    public MatchGroup get(@NotNull String var1);
}

