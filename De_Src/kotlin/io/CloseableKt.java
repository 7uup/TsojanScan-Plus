/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.io;

import java.io.Closeable;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.SinceKotlin;
import kotlin.internal.InlineOnly;
import kotlin.internal.PlatformImplementationsKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=2, d1={"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0018\u0010\u0000\u001a\u00020\u0001*\u0004\u0018\u00010\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\u0001\u001aK\u0010\u0005\u001a\u0002H\u0006\"\n\b\u0000\u0010\u0007*\u0004\u0018\u00010\u0002\"\u0004\b\u0001\u0010\u0006*\u0002H\u00072\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u0002H\u00060\tH\u0087\b\u00f8\u0001\u0000\u00f8\u0001\u0001\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\u00a2\u0006\u0002\u0010\u000b\u0082\u0002\u000f\n\u0006\b\u0011(\n0\u0001\n\u0005\b\u009920\u0001\u00a8\u0006\f"}, d2={"closeFinally", "", "Ljava/io/Closeable;", "cause", "", "use", "R", "T", "block", "Lkotlin/Function1;", "Requires newer compiler version to be inlined correctly.", "(Ljava/io/Closeable;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "kotlin-stdlib"})
@JvmName(name="CloseableKt")
public final class CloseableKt {
    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @InlineOnly
    private static final <T extends Closeable, R> R use(T $this$use, Function1<? super T, ? extends R> block) {
        R r;
        int $i$f$use = 0;
        boolean bl = false;
        Throwable exception = null;
        try {
            r = block.invoke($this$use);
        } catch (Throwable e) {
            try {
                exception = e;
                throw e;
            } catch (Throwable throwable) {
                InlineMarker.finallyStart(1);
                if (PlatformImplementationsKt.apiVersionIsAtLeast(1, 1, 0)) {
                    CloseableKt.closeFinally($this$use, exception);
                } else if ($this$use != null) {
                    if (exception == null) {
                        $this$use.close();
                    } else {
                        try {
                            $this$use.close();
                        } catch (Throwable throwable2) {
                            // empty catch block
                        }
                    }
                }
                InlineMarker.finallyEnd(1);
                throw throwable;
            }
        }
        InlineMarker.finallyStart(1);
        if (PlatformImplementationsKt.apiVersionIsAtLeast(1, 1, 0)) {
            CloseableKt.closeFinally($this$use, exception);
        } else if ($this$use != null) {
            $this$use.close();
        }
        InlineMarker.finallyEnd(1);
        return r;
    }

    @SinceKotlin(version="1.1")
    @PublishedApi
    public static final void closeFinally(@Nullable Closeable $this$closeFinally, @Nullable Throwable cause) {
        if ($this$closeFinally != null) {
            if (cause == null) {
                $this$closeFinally.close();
            } else {
                try {
                    $this$closeFinally.close();
                } catch (Throwable closeException) {
                    ExceptionsKt.addSuppressed(cause, closeException);
                }
            }
        }
    }
}

