/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.system;

import kotlin.Metadata;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=2, d1={"\u0000\u000e\n\u0000\n\u0002\u0010\u0001\n\u0000\n\u0002\u0010\b\n\u0000\u001a\u0011\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0087\b\u00a8\u0006\u0004"}, d2={"exitProcess", "", "status", "", "kotlin-stdlib"})
@JvmName(name="ProcessKt")
public final class ProcessKt {
    @InlineOnly
    private static final Void exitProcess(int status) {
        int $i$f$exitProcess = 0;
        System.exit(status);
        throw (Throwable)new RuntimeException("System.exit returned normally, while it was supposed to halt JVM.");
    }
}

