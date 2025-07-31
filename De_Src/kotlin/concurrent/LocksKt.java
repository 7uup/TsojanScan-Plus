/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package kotlin.concurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import kotlin.Metadata;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.InlineMarker;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=2, d1={"\u0000\u001a\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a6\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0004H\u0087\b\u00f8\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\u00a2\u0006\u0002\u0010\u0005\u001a6\u0010\u0006\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u00072\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0004H\u0087\b\u00f8\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\u00a2\u0006\u0002\u0010\b\u001a6\u0010\t\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0004H\u0087\b\u00f8\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\u00a2\u0006\u0002\u0010\u0005\u0082\u0002\u0007\n\u0005\b\u009920\u0001\u00a8\u0006\n"}, d2={"read", "T", "Ljava/util/concurrent/locks/ReentrantReadWriteLock;", "action", "Lkotlin/Function0;", "(Ljava/util/concurrent/locks/ReentrantReadWriteLock;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "withLock", "Ljava/util/concurrent/locks/Lock;", "(Ljava/util/concurrent/locks/Lock;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "write", "kotlin-stdlib"})
@JvmName(name="LocksKt")
public final class LocksKt {
    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @InlineOnly
    private static final <T> T withLock(Lock $this$withLock, Function0<? extends T> action) {
        int $i$f$withLock = 0;
        boolean bl = false;
        $this$withLock.lock();
        try {
            T t = action.invoke();
            return t;
        } finally {
            InlineMarker.finallyStart(1);
            $this$withLock.unlock();
            InlineMarker.finallyEnd(1);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @InlineOnly
    private static final <T> T read(ReentrantReadWriteLock $this$read, Function0<? extends T> action) {
        int $i$f$read = 0;
        boolean bl = false;
        ReentrantReadWriteLock.ReadLock rl = $this$read.readLock();
        rl.lock();
        try {
            T t = action.invoke();
            return t;
        } finally {
            InlineMarker.finallyStart(1);
            rl.unlock();
            InlineMarker.finallyEnd(1);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @InlineOnly
    private static final <T> T write(ReentrantReadWriteLock $this$write, Function0<? extends T> action) {
        int $i$f$write = 0;
        boolean bl = false;
        ReentrantReadWriteLock.ReadLock rl = $this$write.readLock();
        int readCount = $this$write.getWriteHoldCount() == 0 ? $this$write.getReadHoldCount() : 0;
        boolean bl2 = false;
        int n = 0;
        n = 0;
        int n2 = readCount;
        while (n < n2) {
            int it = n++;
            boolean bl3 = false;
            rl.unlock();
        }
        ReentrantReadWriteLock.WriteLock wl = $this$write.writeLock();
        wl.lock();
        try {
            T t = action.invoke();
            return t;
        } finally {
            InlineMarker.finallyStart(1);
            n2 = 0;
            int n3 = 0;
            n3 = 0;
            int n4 = readCount;
            while (n3 < n4) {
                int it = n3++;
                boolean bl4 = false;
                rl.lock();
            }
            wl.unlock();
            InlineMarker.finallyEnd(1);
        }
    }
}

