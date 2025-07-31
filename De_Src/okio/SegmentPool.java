/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okio;

import java.util.concurrent.atomic.AtomicReference;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import okio.Segment;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u00c0\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0010\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u000eH\u0002J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0006H\u0007J\b\u0010\u0014\u001a\u00020\u0006H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u00020\u0004X\u0086D\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\n\u001a\u00020\u00048F\u00a2\u0006\u0006\u001a\u0004\b\u000b\u0010\tR\u001e\u0010\f\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u000e0\rX\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u000f\u00a8\u0006\u0015"}, d2={"Lokio/SegmentPool;", "", "()V", "HASH_BUCKET_COUNT", "", "LOCK", "Lokio/Segment;", "MAX_SIZE", "getMAX_SIZE", "()I", "byteCount", "getByteCount", "hashBuckets", "", "Ljava/util/concurrent/atomic/AtomicReference;", "[Ljava/util/concurrent/atomic/AtomicReference;", "firstRef", "recycle", "", "segment", "take", "okio"})
public final class SegmentPool {
    private static final int MAX_SIZE;
    private static final Segment LOCK;
    private static final int HASH_BUCKET_COUNT;
    private static final AtomicReference<Segment>[] hashBuckets;
    public static final SegmentPool INSTANCE;

    public final int getMAX_SIZE() {
        return MAX_SIZE;
    }

    public final int getByteCount() {
        Segment segment = this.firstRef().get();
        if (segment == null) {
            return 0;
        }
        Segment first = segment;
        return first.limit;
    }

    @JvmStatic
    @NotNull
    public static final Segment take() {
        AtomicReference<Segment> firstRef = INSTANCE.firstRef();
        Segment first = firstRef.getAndSet(LOCK);
        if (first == LOCK) {
            return new Segment();
        }
        if (first == null) {
            firstRef.set(null);
            return new Segment();
        }
        firstRef.set(first.next);
        first.next = null;
        first.limit = 0;
        return first;
    }

    @JvmStatic
    public static final void recycle(@NotNull Segment segment) {
        int firstLimit;
        Intrinsics.checkNotNullParameter(segment, "segment");
        boolean bl = segment.next == null && segment.prev == null;
        boolean bl2 = false;
        boolean bl3 = false;
        bl3 = false;
        boolean bl4 = false;
        if (!bl) {
            boolean bl5 = false;
            String string = "Failed requirement.";
            throw (Throwable)new IllegalArgumentException(string.toString());
        }
        if (segment.shared) {
            return;
        }
        AtomicReference<Segment> firstRef = INSTANCE.firstRef();
        Segment first = firstRef.get();
        if (first == LOCK) {
            return;
        }
        Segment segment2 = first;
        int n = firstLimit = segment2 != null ? segment2.limit : 0;
        if (firstLimit >= MAX_SIZE) {
            return;
        }
        segment.next = first;
        segment.pos = 0;
        segment.limit = firstLimit + 8192;
        if (!firstRef.compareAndSet(first, segment)) {
            segment.next = null;
        }
    }

    private final AtomicReference<Segment> firstRef() {
        Thread thread2 = Thread.currentThread();
        Intrinsics.checkNotNullExpressionValue(thread2, "Thread.currentThread()");
        int hashBucket = (int)(thread2.getId() & (long)HASH_BUCKET_COUNT - 1L);
        return hashBuckets[hashBucket];
    }

    private SegmentPool() {
    }

    static {
        SegmentPool segmentPool;
        INSTANCE = segmentPool = new SegmentPool();
        MAX_SIZE = 65536;
        LOCK = new Segment(new byte[0], 0, 0, false, false);
        int n = HASH_BUCKET_COUNT = Integer.highestOneBit(Runtime.getRuntime().availableProcessors() * 2 - 1);
        AtomicReference[] atomicReferenceArray = new AtomicReference[n];
        int n2 = 0;
        while (n2 < n) {
            AtomicReference atomicReference;
            int n3 = n2;
            int n4 = n2++;
            AtomicReference[] atomicReferenceArray2 = atomicReferenceArray;
            boolean bl = false;
            atomicReferenceArray2[n4] = atomicReference = new AtomicReference();
        }
        hashBuckets = atomicReferenceArray;
    }
}

