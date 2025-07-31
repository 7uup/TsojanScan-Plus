/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okio;

import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import org.jetbrains.annotations.NotNull;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=2, d1={"\u00000\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a-\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0005H\u0080\b\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006\u001a\f\u0010\u0007\u001a\u00020\b*\u00020\tH\u0000\u001a\f\u0010\n\u001a\u00020\t*\u00020\bH\u0000*\n\u0010\u000b\"\u00020\f2\u00020\f*\n\u0010\r\"\u00020\u000e2\u00020\u000e*\n\u0010\u000f\"\u00020\u00102\u00020\u0010\u0082\u0002\u0007\n\u0005\b\u009920\u0001\u00a8\u0006\u0011"}, d2={"synchronized", "R", "lock", "", "block", "Lkotlin/Function0;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "asUtf8ToByteArray", "", "", "toUtf8String", "ArrayIndexOutOfBoundsException", "Ljava/lang/ArrayIndexOutOfBoundsException;", "EOFException", "Ljava/io/EOFException;", "IOException", "Ljava/io/IOException;", "okio"})
@JvmName(name="-Platform")
public final class -Platform {
    @NotNull
    public static final String toUtf8String(@NotNull byte[] $this$toUtf8String) {
        Intrinsics.checkNotNullParameter($this$toUtf8String, "$this$toUtf8String");
        Charset charset = Charsets.UTF_8;
        boolean bl = false;
        return new String($this$toUtf8String, charset);
    }

    @NotNull
    public static final byte[] asUtf8ToByteArray(@NotNull String $this$asUtf8ToByteArray) {
        Intrinsics.checkNotNullParameter($this$asUtf8ToByteArray, "$this$asUtf8ToByteArray");
        String string = $this$asUtf8ToByteArray;
        Charset charset = Charsets.UTF_8;
        boolean bl = false;
        byte[] byArray = string.getBytes(charset);
        Intrinsics.checkNotNullExpressionValue(byArray, "(this as java.lang.String).getBytes(charset)");
        return byArray;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static final <R> R synchronized(@NotNull Object lock, @NotNull Function0<? extends R> block) {
        int $i$f$synchronized = 0;
        Intrinsics.checkNotNullParameter(lock, "lock");
        Intrinsics.checkNotNullParameter(block, "block");
        boolean bl = false;
        boolean bl2 = false;
        synchronized (lock) {
            R r;
            try {
                r = block.invoke();
            } finally {
                InlineMarker.finallyStart(1);
                // MONITOREXIT @DISABLED, blocks:[1, 3] lbl13 : MonitorExitStatement: MONITOREXIT : lock
                InlineMarker.finallyEnd(1);
            }
            return r;
        }
    }
}

