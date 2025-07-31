/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin;

import java.io.Serializable;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.ResultKt;
import kotlin.SinceKotlin;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u00006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\u0003\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0087@\u0018\u0000 \u001e*\u0006\b\u0000\u0010\u0001 \u00012\u00060\u0002j\u0002`\u0003:\u0002\u001e\u001fB\u0016\b\u0001\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0013\u0010\u0010\u001a\u00020\t2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0005H\u00d6\u0003J\u000f\u0010\u0012\u001a\u0004\u0018\u00010\u0013\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u0012\u0010\u0016\u001a\u0004\u0018\u00018\u0000H\u0087\b\u00a2\u0006\u0004\b\u0017\u0010\u0007J\t\u0010\u0018\u001a\u00020\u0019H\u00d6\u0001J\u000f\u0010\u001a\u001a\u00020\u001bH\u0016\u00a2\u0006\u0004\b\u001c\u0010\u001dR\u0011\u0010\b\u001a\u00020\t8F\u00a2\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\t8F\u00a2\u0006\u0006\u001a\u0004\b\r\u0010\u000bR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0000X\u0081\u0004\u00a2\u0006\b\n\u0000\u0012\u0004\b\u000e\u0010\u000f\u00f8\u0001\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006 "}, d2={"Lkotlin/Result;", "T", "Ljava/io/Serializable;", "Lkotlin/io/Serializable;", "value", "", "constructor-impl", "(Ljava/lang/Object;)Ljava/lang/Object;", "isFailure", "", "isFailure-impl", "(Ljava/lang/Object;)Z", "isSuccess", "isSuccess-impl", "getValue$annotations", "()V", "equals", "other", "exceptionOrNull", "", "exceptionOrNull-impl", "(Ljava/lang/Object;)Ljava/lang/Throwable;", "getOrNull", "getOrNull-impl", "hashCode", "", "toString", "", "toString-impl", "(Ljava/lang/Object;)Ljava/lang/String;", "Companion", "Failure", "kotlin-stdlib"})
@SinceKotlin(version="1.3")
public final class Result<T>
implements Serializable {
    @Nullable
    private final Object value;
    public static final Companion Companion = new Companion(null);

    @NotNull
    public String toString() {
        return Result.toString-impl(this.value);
    }

    @PublishedApi
    public static /* synthetic */ void getValue$annotations() {
    }

    @PublishedApi
    private /* synthetic */ Result(@Nullable Object value) {
        this.value = value;
    }

    public static final boolean isSuccess-impl(Object $this) {
        return !($this instanceof Failure);
    }

    public static final boolean isFailure-impl(Object $this) {
        return $this instanceof Failure;
    }

    @InlineOnly
    private static final T getOrNull-impl(Object $this) {
        int $i$f$getOrNull = 0;
        return Result.isFailure-impl($this) ? null : $this;
    }

    @Nullable
    public static final Throwable exceptionOrNull-impl(Object $this) {
        Object object = $this;
        return object instanceof Failure ? ((Failure)$this).exception : null;
    }

    @NotNull
    public static String toString-impl(Object $this) {
        Object object = $this;
        return object instanceof Failure ? $this.toString() : "Success(" + $this + ')';
    }

    @PublishedApi
    @NotNull
    public static Object constructor-impl(@Nullable Object value) {
        return value;
    }

    @NotNull
    public static final /* synthetic */ Result box-impl(@Nullable Object v) {
        return new Result(v);
    }

    public static int hashCode-impl(Object object) {
        Object object2 = object;
        return object2 != null ? object2.hashCode() : 0;
    }

    public static boolean equals-impl(Object object, @Nullable Object object2) {
        Object object3;
        return object2 instanceof Result && Intrinsics.areEqual(object, object3 = ((Result)object2).unbox-impl());
    }

    public static final boolean equals-impl0(@Nullable Object p1, @Nullable Object p2) {
        return Intrinsics.areEqual(p1, p2);
    }

    @Nullable
    public final /* synthetic */ Object unbox-impl() {
        return this.value;
    }

    public int hashCode() {
        return Result.hashCode-impl(this.value);
    }

    public boolean equals(Object object) {
        return Result.equals-impl(this.value, object);
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0000\u0018\u00002\u00060\u0001j\u0002`\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\u0002\u0010\u0005J\u0013\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0096\u0002J\b\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\rH\u0016R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000e"}, d2={"Lkotlin/Result$Failure;", "Ljava/io/Serializable;", "Lkotlin/io/Serializable;", "exception", "", "(Ljava/lang/Throwable;)V", "equals", "", "other", "", "hashCode", "", "toString", "", "kotlin-stdlib"})
    public static final class Failure
    implements Serializable {
        @JvmField
        @NotNull
        public final Throwable exception;

        public boolean equals(@Nullable Object other) {
            return other instanceof Failure && Intrinsics.areEqual(this.exception, ((Failure)other).exception);
        }

        public int hashCode() {
            return this.exception.hashCode();
        }

        @NotNull
        public String toString() {
            return "Failure(" + this.exception + ')';
        }

        public Failure(@NotNull Throwable exception) {
            Intrinsics.checkNotNullParameter(exception, "exception");
            this.exception = exception;
        }
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J%\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0001\u0010\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\bJ%\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0001\u0010\u00052\u0006\u0010\n\u001a\u0002H\u0005H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000b\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\f"}, d2={"Lkotlin/Result$Companion;", "", "()V", "failure", "Lkotlin/Result;", "T", "exception", "", "(Ljava/lang/Throwable;)Ljava/lang/Object;", "success", "value", "(Ljava/lang/Object;)Ljava/lang/Object;", "kotlin-stdlib"})
    public static final class Companion {
        @InlineOnly
        @JvmName(name="success")
        private final <T> Object success(T value) {
            int $i$f$success = 0;
            return Result.constructor-impl(value);
        }

        @InlineOnly
        @JvmName(name="failure")
        private final <T> Object failure(Throwable exception) {
            int $i$f$failure = 0;
            return Result.constructor-impl(ResultKt.createFailure(exception));
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

