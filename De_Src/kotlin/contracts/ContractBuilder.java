/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.contracts;

import kotlin.Function;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.contracts.CallsInPlace;
import kotlin.contracts.ExperimentalContracts;
import kotlin.contracts.InvocationKind;
import kotlin.contracts.Returns;
import kotlin.contracts.ReturnsNotNull;
import kotlin.internal.ContractsDsl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J&\u0010\u0002\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00040\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bH'J\b\u0010\t\u001a\u00020\nH'J\u0012\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001H'J\b\u0010\f\u001a\u00020\rH'\u00a8\u0006\u000e"}, d2={"Lkotlin/contracts/ContractBuilder;", "", "callsInPlace", "Lkotlin/contracts/CallsInPlace;", "R", "lambda", "Lkotlin/Function;", "kind", "Lkotlin/contracts/InvocationKind;", "returns", "Lkotlin/contracts/Returns;", "value", "returnsNotNull", "Lkotlin/contracts/ReturnsNotNull;", "kotlin-stdlib"})
@ContractsDsl
@ExperimentalContracts
@SinceKotlin(version="1.3")
public interface ContractBuilder {
    @ContractsDsl
    @NotNull
    public Returns returns();

    @ContractsDsl
    @NotNull
    public Returns returns(@Nullable Object var1);

    @ContractsDsl
    @NotNull
    public ReturnsNotNull returnsNotNull();

    @ContractsDsl
    @NotNull
    public <R> CallsInPlace callsInPlace(@NotNull Function<? extends R> var1, @NotNull InvocationKind var2);

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=3)
    public static final class DefaultImpls {
        public static /* synthetic */ CallsInPlace callsInPlace$default(ContractBuilder contractBuilder, Function function, InvocationKind invocationKind, int n, Object object) {
            if (object != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: callsInPlace");
            }
            if ((n & 2) != 0) {
                invocationKind = InvocationKind.UNKNOWN;
            }
            return contractBuilder.callsInPlace(function, invocationKind);
        }
    }
}

