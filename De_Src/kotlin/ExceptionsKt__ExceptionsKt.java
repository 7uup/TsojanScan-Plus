/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.internal.HidesMembers;
import kotlin.internal.InlineOnly;
import kotlin.internal.PlatformImplementationsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=5, xi=1, d1={"\u00004\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\u0014\u0010\r\u001a\u00020\u000e*\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0003H\u0007\u001a\r\u0010\u0010\u001a\u00020\u000e*\u00020\u0003H\u0087\b\u001a\u0015\u0010\u0010\u001a\u00020\u000e*\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u0012H\u0087\b\u001a\u0015\u0010\u0010\u001a\u00020\u000e*\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u0014H\u0087\b\u001a\f\u0010\u0015\u001a\u00020\u0016*\u00020\u0003H\u0007\"!\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00038F\u00a2\u0006\f\u0012\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007\"$\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\t*\u00020\u00038FX\u0087\u0004\u00a2\u0006\f\u0012\u0004\b\n\u0010\u0005\u001a\u0004\b\u000b\u0010\f\u00a8\u0006\u0017"}, d2={"stackTrace", "", "Ljava/lang/StackTraceElement;", "", "getStackTrace$annotations", "(Ljava/lang/Throwable;)V", "getStackTrace", "(Ljava/lang/Throwable;)[Ljava/lang/StackTraceElement;", "suppressedExceptions", "", "getSuppressedExceptions$annotations", "getSuppressedExceptions", "(Ljava/lang/Throwable;)Ljava/util/List;", "addSuppressed", "", "exception", "printStackTrace", "stream", "Ljava/io/PrintStream;", "writer", "Ljava/io/PrintWriter;", "stackTraceToString", "", "kotlin-stdlib"}, xs="kotlin/ExceptionsKt")
class ExceptionsKt__ExceptionsKt {
    @InlineOnly
    private static final void printStackTrace(Throwable $this$printStackTrace) {
        int $i$f$printStackTrace = 0;
        Throwable throwable = $this$printStackTrace;
        if (throwable == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.Throwable");
        }
        throwable.printStackTrace();
    }

    @InlineOnly
    private static final void printStackTrace(Throwable $this$printStackTrace, PrintWriter writer) {
        int $i$f$printStackTrace = 0;
        Throwable throwable = $this$printStackTrace;
        if (throwable == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.Throwable");
        }
        throwable.printStackTrace(writer);
    }

    @InlineOnly
    private static final void printStackTrace(Throwable $this$printStackTrace, PrintStream stream) {
        int $i$f$printStackTrace = 0;
        Throwable throwable = $this$printStackTrace;
        if (throwable == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.Throwable");
        }
        throwable.printStackTrace(stream);
    }

    public static /* synthetic */ void getStackTrace$annotations(Throwable throwable) {
    }

    @NotNull
    public static final StackTraceElement[] getStackTrace(@NotNull Throwable $this$stackTrace) {
        Intrinsics.checkNotNullParameter($this$stackTrace, "$this$stackTrace");
        StackTraceElement[] stackTraceElementArray = $this$stackTrace.getStackTrace();
        Intrinsics.checkNotNull(stackTraceElementArray);
        return stackTraceElementArray;
    }

    @SinceKotlin(version="1.4")
    @NotNull
    public static final String stackTraceToString(@NotNull Throwable $this$stackTraceToString) {
        Intrinsics.checkNotNullParameter($this$stackTraceToString, "$this$stackTraceToString");
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        $this$stackTraceToString.printStackTrace(pw);
        pw.flush();
        String string = sw.toString();
        Intrinsics.checkNotNullExpressionValue(string, "sw.toString()");
        return string;
    }

    @SinceKotlin(version="1.1")
    @HidesMembers
    public static final void addSuppressed(@NotNull Throwable $this$addSuppressed, @NotNull Throwable exception) {
        Intrinsics.checkNotNullParameter($this$addSuppressed, "$this$addSuppressed");
        Intrinsics.checkNotNullParameter(exception, "exception");
        if ($this$addSuppressed != exception) {
            PlatformImplementationsKt.IMPLEMENTATIONS.addSuppressed($this$addSuppressed, exception);
        }
    }

    @SinceKotlin(version="1.4")
    public static /* synthetic */ void getSuppressedExceptions$annotations(Throwable throwable) {
    }

    @NotNull
    public static final List<Throwable> getSuppressedExceptions(@NotNull Throwable $this$suppressedExceptions) {
        Intrinsics.checkNotNullParameter($this$suppressedExceptions, "$this$suppressedExceptions");
        return PlatformImplementationsKt.IMPLEMENTATIONS.getSuppressed($this$suppressedExceptions);
    }
}

