/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okio;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okio.SegmentPool;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u000b\b\u0000\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\u0007\b\u0016\u00a2\u0006\u0002\u0010\u0002B/\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t\u00a2\u0006\u0002\u0010\u000bJ\u0006\u0010\u000e\u001a\u00020\u000fJ\b\u0010\u0010\u001a\u0004\u0018\u00010\u0000J\u000e\u0010\u0011\u001a\u00020\u00002\u0006\u0010\u0012\u001a\u00020\u0000J\u0006\u0010\u0013\u001a\u00020\u0000J\u000e\u0010\u0014\u001a\u00020\u00002\u0006\u0010\u0015\u001a\u00020\u0006J\u0006\u0010\u0016\u001a\u00020\u0000J\u0016\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\u00002\u0006\u0010\u0015\u001a\u00020\u0006R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004\u00a2\u0006\u0002\n\u0000R\u0012\u0010\u0007\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\u0004\u0018\u00010\u00008\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0002\n\u0000R\u0012\u0010\n\u001a\u00020\t8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0002\n\u0000R\u0012\u0010\u0005\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\u0004\u0018\u00010\u00008\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0002\n\u0000R\u0012\u0010\b\u001a\u00020\t8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001a"}, d2={"Lokio/Segment;", "", "()V", "data", "", "pos", "", "limit", "shared", "", "owner", "([BIIZZ)V", "next", "prev", "compact", "", "pop", "push", "segment", "sharedCopy", "split", "byteCount", "unsharedCopy", "writeTo", "sink", "Companion", "okio"})
public final class Segment {
    @JvmField
    @NotNull
    public final byte[] data;
    @JvmField
    public int pos;
    @JvmField
    public int limit;
    @JvmField
    public boolean shared;
    @JvmField
    public boolean owner;
    @JvmField
    @Nullable
    public Segment next;
    @JvmField
    @Nullable
    public Segment prev;
    public static final int SIZE = 8192;
    public static final int SHARE_MINIMUM = 1024;
    public static final Companion Companion = new Companion(null);

    @NotNull
    public final Segment sharedCopy() {
        this.shared = true;
        return new Segment(this.data, this.pos, this.limit, true, false);
    }

    @NotNull
    public final Segment unsharedCopy() {
        byte[] byArray = this.data;
        boolean bl = false;
        byte[] byArray2 = Arrays.copyOf(byArray, byArray.length);
        Intrinsics.checkNotNullExpressionValue(byArray2, "java.util.Arrays.copyOf(this, size)");
        return new Segment(byArray2, this.pos, this.limit, false, true);
    }

    @Nullable
    public final Segment pop() {
        Segment result = this.next != this ? this.next : null;
        Intrinsics.checkNotNull(this.prev);
        this.prev.next = this.next;
        Intrinsics.checkNotNull(this.next);
        this.next.prev = this.prev;
        this.next = null;
        this.prev = null;
        return result;
    }

    @NotNull
    public final Segment push(@NotNull Segment segment) {
        Intrinsics.checkNotNullParameter(segment, "segment");
        segment.prev = this;
        segment.next = this.next;
        Intrinsics.checkNotNull(this.next);
        this.next.prev = segment;
        this.next = segment;
        return segment;
    }

    @NotNull
    public final Segment split(int byteCount) {
        boolean bl = byteCount > 0 && byteCount <= this.limit - this.pos;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "byteCount out of range";
            throw (Throwable)new IllegalArgumentException(string.toString());
        }
        Segment prefix = null;
        if (byteCount >= 1024) {
            prefix = this.sharedCopy();
        } else {
            prefix = SegmentPool.take();
            ArraysKt.copyInto$default(this.data, prefix.data, 0, this.pos, this.pos + byteCount, 2, null);
        }
        prefix.limit = prefix.pos + byteCount;
        this.pos += byteCount;
        Segment segment = this.prev;
        Intrinsics.checkNotNull(segment);
        segment.push(prefix);
        return prefix;
    }

    public final void compact() {
        int n;
        boolean bl = this.prev != this;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "cannot compact";
            throw (Throwable)new IllegalStateException(string.toString());
        }
        Segment segment = this.prev;
        Intrinsics.checkNotNull(segment);
        if (!segment.owner) {
            return;
        }
        int byteCount = this.limit - this.pos;
        Segment segment2 = this.prev;
        Intrinsics.checkNotNull(segment2);
        int n2 = 8192 - segment2.limit;
        Segment segment3 = this.prev;
        Intrinsics.checkNotNull(segment3);
        if (segment3.shared) {
            n = 0;
        } else {
            Segment segment4 = this.prev;
            Intrinsics.checkNotNull(segment4);
            n = segment4.pos;
        }
        int availableByteCount = n2 + n;
        if (byteCount > availableByteCount) {
            return;
        }
        Segment segment5 = this.prev;
        Intrinsics.checkNotNull(segment5);
        this.writeTo(segment5, byteCount);
        this.pop();
        SegmentPool.recycle(this);
    }

    public final void writeTo(@NotNull Segment sink2, int byteCount) {
        Intrinsics.checkNotNullParameter(sink2, "sink");
        boolean bl = sink2.owner;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "only owner can write";
            throw (Throwable)new IllegalStateException(string.toString());
        }
        if (sink2.limit + byteCount > 8192) {
            if (sink2.shared) {
                throw (Throwable)new IllegalArgumentException();
            }
            if (sink2.limit + byteCount - sink2.pos > 8192) {
                throw (Throwable)new IllegalArgumentException();
            }
            ArraysKt.copyInto$default(sink2.data, sink2.data, 0, sink2.pos, sink2.limit, 2, null);
            sink2.limit -= sink2.pos;
            sink2.pos = 0;
        }
        ArraysKt.copyInto(this.data, sink2.data, sink2.limit, this.pos, this.pos + byteCount);
        sink2.limit += byteCount;
        this.pos += byteCount;
    }

    public Segment() {
        this.data = new byte[8192];
        this.owner = true;
        this.shared = false;
    }

    public Segment(@NotNull byte[] data, int pos, int limit, boolean shared, boolean owner) {
        Intrinsics.checkNotNullParameter(data, "data");
        this.data = data;
        this.pos = pos;
        this.limit = limit;
        this.shared = shared;
        this.owner = owner;
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2={"Lokio/Segment$Companion;", "", "()V", "SHARE_MINIMUM", "", "SIZE", "okio"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

