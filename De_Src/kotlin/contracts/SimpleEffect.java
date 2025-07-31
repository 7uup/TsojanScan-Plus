/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.contracts;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.contracts.ConditionalEffect;
import kotlin.contracts.Effect;
import kotlin.contracts.ExperimentalContracts;
import kotlin.internal.ContractsDsl;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\bg\u0018\u00002\u00020\u0001J\u0011\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7\u0004\u00a8\u0006\u0006"}, d2={"Lkotlin/contracts/SimpleEffect;", "Lkotlin/contracts/Effect;", "implies", "Lkotlin/contracts/ConditionalEffect;", "booleanExpression", "", "kotlin-stdlib"})
@ContractsDsl
@ExperimentalContracts
@SinceKotlin(version="1.3")
public interface SimpleEffect
extends Effect {
    @ContractsDsl
    @ExperimentalContracts
    @NotNull
    public ConditionalEffect implies(boolean var1);
}

