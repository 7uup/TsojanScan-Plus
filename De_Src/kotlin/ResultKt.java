/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin;

import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.Result;
import kotlin.SinceKotlin;
import kotlin.Unit;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=2, d1={"\u0000:\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0001\u001a.\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00060\u0005\"\u0004\b\u0000\u0010\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00060\bH\u0087\b\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0002\u0010\t\u001a\u0087\u0001\u0010\n\u001a\u0002H\u0006\"\u0004\b\u0000\u0010\u0006\"\u0004\b\u0001\u0010\u000b*\b\u0012\u0004\u0012\u0002H\u000b0\u00052!\u0010\f\u001a\u001d\u0012\u0013\u0012\u0011H\u000b\u00a2\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u0002H\u00060\r2!\u0010\u0011\u001a\u001d\u0012\u0013\u0012\u00110\u0003\u00a2\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0002\u0012\u0004\u0012\u0002H\u00060\rH\u0087\b\u00f8\u0001\u0000\u00f8\u0001\u0001\u0082\u0002\u0014\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0000\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0000\u00a2\u0006\u0002\u0010\u0012\u001a3\u0010\u0013\u001a\u0002H\u0006\"\u0004\b\u0000\u0010\u0006\"\b\b\u0001\u0010\u000b*\u0002H\u0006*\b\u0012\u0004\u0012\u0002H\u000b0\u00052\u0006\u0010\u0014\u001a\u0002H\u0006H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0015\u001a^\u0010\u0016\u001a\u0002H\u0006\"\u0004\b\u0000\u0010\u0006\"\b\b\u0001\u0010\u000b*\u0002H\u0006*\b\u0012\u0004\u0012\u0002H\u000b0\u00052!\u0010\u0011\u001a\u001d\u0012\u0013\u0012\u00110\u0003\u00a2\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0002\u0012\u0004\u0012\u0002H\u00060\rH\u0087\b\u00f8\u0001\u0000\u00f8\u0001\u0001\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0000\u00a2\u0006\u0002\u0010\u0017\u001a!\u0010\u0018\u001a\u0002H\u000b\"\u0004\b\u0000\u0010\u000b*\b\u0012\u0004\u0012\u0002H\u000b0\u0005H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0019\u001a`\u0010\u001a\u001a\b\u0012\u0004\u0012\u0002H\u00060\u0005\"\u0004\b\u0000\u0010\u0006\"\u0004\b\u0001\u0010\u000b*\b\u0012\u0004\u0012\u0002H\u000b0\u00052!\u0010\u001b\u001a\u001d\u0012\u0013\u0012\u0011H\u000b\u00a2\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u0002H\u00060\rH\u0087\b\u00f8\u0001\u0000\u00f8\u0001\u0001\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0000\u00a2\u0006\u0002\u0010\u0017\u001aS\u0010\u001c\u001a\b\u0012\u0004\u0012\u0002H\u00060\u0005\"\u0004\b\u0000\u0010\u0006\"\u0004\b\u0001\u0010\u000b*\b\u0012\u0004\u0012\u0002H\u000b0\u00052!\u0010\u001b\u001a\u001d\u0012\u0013\u0012\u0011H\u000b\u00a2\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u0002H\u00060\rH\u0087\b\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0002\u0010\u0017\u001aZ\u0010\u0011\u001a\b\u0012\u0004\u0012\u0002H\u000b0\u0005\"\u0004\b\u0000\u0010\u000b*\b\u0012\u0004\u0012\u0002H\u000b0\u00052!\u0010\u001d\u001a\u001d\u0012\u0013\u0012\u00110\u0003\u00a2\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0002\u0012\u0004\u0012\u00020\u001e0\rH\u0087\b\u00f8\u0001\u0000\u00f8\u0001\u0001\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0000\u00a2\u0006\u0002\u0010\u0017\u001aZ\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u000b0\u0005\"\u0004\b\u0000\u0010\u000b*\b\u0012\u0004\u0012\u0002H\u000b0\u00052!\u0010\u001d\u001a\u001d\u0012\u0013\u0012\u0011H\u000b\u00a2\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u001e0\rH\u0087\b\u00f8\u0001\u0000\u00f8\u0001\u0001\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0000\u00a2\u0006\u0002\u0010\u0017\u001ad\u0010\u001f\u001a\b\u0012\u0004\u0012\u0002H\u00060\u0005\"\u0004\b\u0000\u0010\u0006\"\b\b\u0001\u0010\u000b*\u0002H\u0006*\b\u0012\u0004\u0012\u0002H\u000b0\u00052!\u0010\u001b\u001a\u001d\u0012\u0013\u0012\u00110\u0003\u00a2\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0002\u0012\u0004\u0012\u0002H\u00060\rH\u0087\b\u00f8\u0001\u0000\u00f8\u0001\u0001\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0000\u00a2\u0006\u0002\u0010\u0017\u001aW\u0010 \u001a\b\u0012\u0004\u0012\u0002H\u00060\u0005\"\u0004\b\u0000\u0010\u0006\"\b\b\u0001\u0010\u000b*\u0002H\u0006*\b\u0012\u0004\u0012\u0002H\u000b0\u00052!\u0010\u001b\u001a\u001d\u0012\u0013\u0012\u00110\u0003\u00a2\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0002\u0012\u0004\u0012\u0002H\u00060\rH\u0087\b\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0002\u0010\u0017\u001aC\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00060\u0005\"\u0004\b\u0000\u0010\u000b\"\u0004\b\u0001\u0010\u0006*\u0002H\u000b2\u0017\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u0002H\u000b\u0012\u0004\u0012\u0002H\u00060\r\u00a2\u0006\u0002\b!H\u0087\b\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0002\u0010\u0017\u001a\u0018\u0010\"\u001a\u00020\u001e*\u0006\u0012\u0002\b\u00030\u0005H\u0001\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010#\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b\u009920\u0001\u00a8\u0006$"}, d2={"createFailure", "", "exception", "", "runCatching", "Lkotlin/Result;", "R", "block", "Lkotlin/Function0;", "(Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "fold", "T", "onSuccess", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "value", "onFailure", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "getOrDefault", "defaultValue", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "getOrElse", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "getOrThrow", "(Ljava/lang/Object;)Ljava/lang/Object;", "map", "transform", "mapCatching", "action", "", "recover", "recoverCatching", "Lkotlin/ExtensionFunctionType;", "throwOnFailure", "(Ljava/lang/Object;)V", "kotlin-stdlib"})
public final class ResultKt {
    @PublishedApi
    @SinceKotlin(version="1.3")
    @NotNull
    public static final Object createFailure(@NotNull Throwable exception) {
        Intrinsics.checkNotNullParameter(exception, "exception");
        return new Result.Failure(exception);
    }

    @PublishedApi
    @SinceKotlin(version="1.3")
    public static final void throwOnFailure(@NotNull Object $this$throwOnFailure) {
        if ($this$throwOnFailure instanceof Result.Failure) {
            throw ((Result.Failure)$this$throwOnFailure).exception;
        }
    }

    @InlineOnly
    @SinceKotlin(version="1.3")
    private static final <R> Object runCatching(Function0<? extends R> block) {
        Object object;
        int $i$f$runCatching = 0;
        try {
            object = Result.Companion;
            R r = block.invoke();
            boolean bl = false;
            object = Result.constructor-impl(r);
        } catch (Throwable e) {
            Result.Companion companion = Result.Companion;
            boolean bl = false;
            object = Result.constructor-impl(ResultKt.createFailure(e));
        }
        return object;
    }

    @InlineOnly
    @SinceKotlin(version="1.3")
    private static final <T, R> Object runCatching(T $this$runCatching, Function1<? super T, ? extends R> block) {
        Object object;
        int $i$f$runCatching = 0;
        try {
            object = Result.Companion;
            R r = block.invoke($this$runCatching);
            boolean bl = false;
            object = Result.constructor-impl(r);
        } catch (Throwable e) {
            Result.Companion companion = Result.Companion;
            boolean bl = false;
            object = Result.constructor-impl(ResultKt.createFailure(e));
        }
        return object;
    }

    @InlineOnly
    @SinceKotlin(version="1.3")
    private static final <T> T getOrThrow(Object $this$getOrThrow) {
        int $i$f$getOrThrow = 0;
        ResultKt.throwOnFailure($this$getOrThrow);
        return (T)$this$getOrThrow;
    }

    @InlineOnly
    @SinceKotlin(version="1.3")
    private static final <R, T extends R> R getOrElse(Object $this$getOrElse, Function1<? super Throwable, ? extends R> onFailure) {
        int $i$f$getOrElse = 0;
        boolean bl = false;
        Throwable exception = Result.exceptionOrNull-impl($this$getOrElse);
        return (R)(exception == null ? $this$getOrElse : onFailure.invoke(exception));
    }

    @InlineOnly
    @SinceKotlin(version="1.3")
    private static final <R, T extends R> R getOrDefault(Object $this$getOrDefault, R defaultValue) {
        int $i$f$getOrDefault = 0;
        if (Result.isFailure-impl($this$getOrDefault)) {
            return defaultValue;
        }
        return (R)$this$getOrDefault;
    }

    @InlineOnly
    @SinceKotlin(version="1.3")
    private static final <R, T> R fold(Object $this$fold, Function1<? super T, ? extends R> onSuccess, Function1<? super Throwable, ? extends R> onFailure) {
        int $i$f$fold = 0;
        boolean bl = false;
        Throwable exception = Result.exceptionOrNull-impl($this$fold);
        return exception == null ? onSuccess.invoke($this$fold) : onFailure.invoke(exception);
    }

    @InlineOnly
    @SinceKotlin(version="1.3")
    private static final <R, T> Object map(Object $this$map, Function1<? super T, ? extends R> transform) {
        Object object;
        int $i$f$map = 0;
        boolean bl = false;
        if (Result.isSuccess-impl($this$map)) {
            Result.Companion companion = Result.Companion;
            R r = transform.invoke($this$map);
            boolean bl2 = false;
            object = Result.constructor-impl(r);
        } else {
            object = Result.constructor-impl($this$map);
        }
        return object;
    }

    @InlineOnly
    @SinceKotlin(version="1.3")
    private static final <R, T> Object mapCatching(Object $this$mapCatching, Function1<? super T, ? extends R> transform) {
        Object object;
        int $i$f$mapCatching = 0;
        if (Result.isSuccess-impl($this$mapCatching)) {
            Object object2;
            Object object3 = $this$mapCatching;
            boolean bl = false;
            try {
                object2 = Result.Companion;
                Object $this$runCatching = object3;
                boolean bl2 = false;
                R r = transform.invoke($this$runCatching);
                boolean bl3 = false;
                object2 = Result.constructor-impl(r);
            } catch (Throwable throwable) {
                Result.Companion companion = Result.Companion;
                boolean bl4 = false;
                object2 = Result.constructor-impl(ResultKt.createFailure(throwable));
            }
            object = object2;
        } else {
            object = Result.constructor-impl($this$mapCatching);
        }
        return object;
    }

    @InlineOnly
    @SinceKotlin(version="1.3")
    private static final <R, T extends R> Object recover(Object $this$recover, Function1<? super Throwable, ? extends R> transform) {
        Object object;
        int $i$f$recover = 0;
        boolean bl = false;
        Throwable exception = Result.exceptionOrNull-impl($this$recover);
        if (exception == null) {
            object = $this$recover;
        } else {
            Result.Companion companion = Result.Companion;
            R r = transform.invoke(exception);
            boolean bl2 = false;
            object = Result.constructor-impl(r);
        }
        return object;
    }

    @InlineOnly
    @SinceKotlin(version="1.3")
    private static final <R, T extends R> Object recoverCatching(Object $this$recoverCatching, Function1<? super Throwable, ? extends R> transform) {
        Object object;
        int $i$f$recoverCatching = 0;
        Object value = $this$recoverCatching;
        Throwable exception = Result.exceptionOrNull-impl($this$recoverCatching);
        if (exception == null) {
            object = $this$recoverCatching;
        } else {
            Object object2;
            Object object3 = $this$recoverCatching;
            boolean bl = false;
            try {
                object2 = Result.Companion;
                Object $this$runCatching = object3;
                boolean bl2 = false;
                R r = transform.invoke(exception);
                boolean bl3 = false;
                object2 = Result.constructor-impl(r);
            } catch (Throwable throwable) {
                Result.Companion companion = Result.Companion;
                boolean bl4 = false;
                object2 = Result.constructor-impl(ResultKt.createFailure(throwable));
            }
            object = object2;
        }
        return object;
    }

    @InlineOnly
    @SinceKotlin(version="1.3")
    private static final <T> Object onFailure(Object $this$onFailure, Function1<? super Throwable, Unit> action) {
        block0: {
            int $i$f$onFailure = 0;
            boolean bl = false;
            Throwable throwable = Result.exceptionOrNull-impl($this$onFailure);
            if (throwable == null) break block0;
            Throwable throwable2 = throwable;
            boolean bl2 = false;
            boolean bl3 = false;
            Throwable it = throwable2;
            boolean bl4 = false;
            action.invoke(it);
        }
        return $this$onFailure;
    }

    @InlineOnly
    @SinceKotlin(version="1.3")
    private static final <T> Object onSuccess(Object $this$onSuccess, Function1<? super T, Unit> action) {
        int $i$f$onSuccess = 0;
        boolean bl = false;
        if (Result.isSuccess-impl($this$onSuccess)) {
            action.invoke($this$onSuccess);
        }
        return $this$onSuccess;
    }
}

