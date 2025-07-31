/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okio.internal;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import okio.-Util;
import okio.Buffer;
import okio.ByteString;
import okio.Segment;
import okio.SegmentedByteString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=2, d1={"\u0000R\n\u0000\n\u0002\u0010\b\n\u0002\u0010\u0015\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0005\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a$\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0000\u001a\u0017\u0010\u0006\u001a\u00020\u0007*\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0080\b\u001a\r\u0010\u000b\u001a\u00020\u0001*\u00020\bH\u0080\b\u001a\r\u0010\f\u001a\u00020\u0001*\u00020\bH\u0080\b\u001a\u0015\u0010\r\u001a\u00020\u000e*\u00020\b2\u0006\u0010\u000f\u001a\u00020\u0001H\u0080\b\u001a-\u0010\u0010\u001a\u00020\u0007*\u00020\b2\u0006\u0010\u0011\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00012\u0006\u0010\u0014\u001a\u00020\u0001H\u0080\b\u001a-\u0010\u0010\u001a\u00020\u0007*\u00020\b2\u0006\u0010\u0011\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\u00152\u0006\u0010\u0013\u001a\u00020\u00012\u0006\u0010\u0014\u001a\u00020\u0001H\u0080\b\u001a\u001d\u0010\u0016\u001a\u00020\u0015*\u00020\b2\u0006\u0010\u0017\u001a\u00020\u00012\u0006\u0010\u0018\u001a\u00020\u0001H\u0080\b\u001a\r\u0010\u0019\u001a\u00020\u0012*\u00020\bH\u0080\b\u001a%\u0010\u001a\u001a\u00020\u001b*\u00020\b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u0011\u001a\u00020\u00012\u0006\u0010\u0014\u001a\u00020\u0001H\u0080\b\u001a]\u0010\u001e\u001a\u00020\u001b*\u00020\b2K\u0010\u001f\u001aG\u0012\u0013\u0012\u00110\u0012\u00a2\u0006\f\b!\u0012\b\b\"\u0012\u0004\b\b(#\u0012\u0013\u0012\u00110\u0001\u00a2\u0006\f\b!\u0012\b\b\"\u0012\u0004\b\b(\u0011\u0012\u0013\u0012\u00110\u0001\u00a2\u0006\f\b!\u0012\b\b\"\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\u001b0 H\u0080\b\u00f8\u0001\u0000\u001aj\u0010\u001e\u001a\u00020\u001b*\u00020\b2\u0006\u0010\u0017\u001a\u00020\u00012\u0006\u0010\u0018\u001a\u00020\u00012K\u0010\u001f\u001aG\u0012\u0013\u0012\u00110\u0012\u00a2\u0006\f\b!\u0012\b\b\"\u0012\u0004\b\b(#\u0012\u0013\u0012\u00110\u0001\u00a2\u0006\f\b!\u0012\b\b\"\u0012\u0004\b\b(\u0011\u0012\u0013\u0012\u00110\u0001\u00a2\u0006\f\b!\u0012\b\b\"\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\u001b0 H\u0082\b\u001a\u0014\u0010$\u001a\u00020\u0001*\u00020\b2\u0006\u0010\u000f\u001a\u00020\u0001H\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001\u00a8\u0006%"}, d2={"binarySearch", "", "", "value", "fromIndex", "toIndex", "commonEquals", "", "Lokio/SegmentedByteString;", "other", "", "commonGetSize", "commonHashCode", "commonInternalGet", "", "pos", "commonRangeEquals", "offset", "", "otherOffset", "byteCount", "Lokio/ByteString;", "commonSubstring", "beginIndex", "endIndex", "commonToByteArray", "commonWrite", "", "buffer", "Lokio/Buffer;", "forEachSegment", "action", "Lkotlin/Function3;", "Lkotlin/ParameterName;", "name", "data", "segment", "okio"})
public final class SegmentedByteStringKt {
    public static final int binarySearch(@NotNull int[] $this$binarySearch, int value, int fromIndex, int toIndex) {
        Intrinsics.checkNotNullParameter($this$binarySearch, "$this$binarySearch");
        int left = fromIndex;
        int right = toIndex - 1;
        while (left <= right) {
            int mid = left + right >>> 1;
            int midVal = $this$binarySearch[mid];
            if (midVal < value) {
                left = mid + 1;
                continue;
            }
            if (midVal > value) {
                right = mid - 1;
                continue;
            }
            return mid;
        }
        return -left - 1;
    }

    public static final int segment(@NotNull SegmentedByteString $this$segment, int pos) {
        Intrinsics.checkNotNullParameter($this$segment, "$this$segment");
        int i = SegmentedByteStringKt.binarySearch($this$segment.getDirectory$okio(), pos + 1, 0, ((Object[])$this$segment.getSegments$okio()).length);
        return i >= 0 ? i : ~i;
    }

    public static final void forEachSegment(@NotNull SegmentedByteString $this$forEachSegment, @NotNull Function3<? super byte[], ? super Integer, ? super Integer, Unit> action) {
        int $i$f$forEachSegment = 0;
        Intrinsics.checkNotNullParameter($this$forEachSegment, "$this$forEachSegment");
        Intrinsics.checkNotNullParameter(action, "action");
        int segmentCount = ((Object[])$this$forEachSegment.getSegments$okio()).length;
        int pos = 0;
        for (int s2 = 0; s2 < segmentCount; ++s2) {
            int segmentPos = $this$forEachSegment.getDirectory$okio()[segmentCount + s2];
            int nextSegmentOffset = $this$forEachSegment.getDirectory$okio()[s2];
            action.invoke((byte[])$this$forEachSegment.getSegments$okio()[s2], (Integer)segmentPos, (Integer)(nextSegmentOffset - pos));
            pos = nextSegmentOffset;
        }
    }

    private static final void forEachSegment(SegmentedByteString $this$forEachSegment, int beginIndex, int endIndex, Function3<? super byte[], ? super Integer, ? super Integer, Unit> action) {
        int $i$f$forEachSegment = 0;
        int s2 = SegmentedByteStringKt.segment($this$forEachSegment, beginIndex);
        int pos = beginIndex;
        while (pos < endIndex) {
            int segmentOffset = s2 == 0 ? 0 : $this$forEachSegment.getDirectory$okio()[s2 - 1];
            int segmentSize = $this$forEachSegment.getDirectory$okio()[s2] - segmentOffset;
            int segmentPos = $this$forEachSegment.getDirectory$okio()[((Object[])$this$forEachSegment.getSegments$okio()).length + s2];
            int n = segmentOffset + segmentSize;
            boolean bl = false;
            int byteCount = Math.min(endIndex, n) - pos;
            int offset = segmentPos + (pos - segmentOffset);
            action.invoke((byte[])$this$forEachSegment.getSegments$okio()[s2], (Integer)offset, (Integer)byteCount);
            pos += byteCount;
            ++s2;
        }
    }

    /*
     * WARNING - void declaration
     */
    @NotNull
    public static final ByteString commonSubstring(@NotNull SegmentedByteString $this$commonSubstring, int beginIndex, int endIndex) {
        int $i$f$commonSubstring = 0;
        Intrinsics.checkNotNullParameter($this$commonSubstring, "$this$commonSubstring");
        boolean bl = beginIndex >= 0;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "beginIndex=" + beginIndex + " < 0";
            throw (Throwable)new IllegalArgumentException(string.toString());
        }
        bl = endIndex <= $this$commonSubstring.size();
        bl2 = false;
        bl3 = false;
        if (!bl) {
            boolean bl52 = false;
            String string = "endIndex=" + endIndex + " > length(" + $this$commonSubstring.size() + ')';
            throw (Throwable)new IllegalArgumentException(string.toString());
        }
        int subLen = endIndex - beginIndex;
        bl2 = subLen >= 0;
        bl3 = false;
        boolean bl52 = false;
        if (!bl2) {
            boolean bl6 = false;
            String bl52 = "endIndex=" + endIndex + " < beginIndex=" + beginIndex;
            throw (Throwable)new IllegalArgumentException(bl52.toString());
        }
        if (beginIndex == 0 && endIndex == $this$commonSubstring.size()) {
            return $this$commonSubstring;
        }
        if (beginIndex == endIndex) {
            return ByteString.EMPTY;
        }
        int beginSegment = SegmentedByteStringKt.segment($this$commonSubstring, beginIndex);
        int endSegment = SegmentedByteStringKt.segment($this$commonSubstring, endIndex - 1);
        Object[] bl6 = (Object[])$this$commonSubstring.getSegments$okio();
        int n = endSegment + 1;
        int n2 = 0;
        byte[][] newSegments = (byte[][])ArraysKt.copyOfRange(bl6, beginSegment, n);
        int[] newDirectory = new int[((Object[])newSegments).length * 2];
        int index = 0;
        n2 = beginSegment;
        int n3 = endSegment;
        if (n2 <= n3) {
            while (true) {
                void s2;
                int n4 = $this$commonSubstring.getDirectory$okio()[s2] - beginIndex;
                boolean bl7 = false;
                newDirectory[index] = Math.min(n4, subLen);
                newDirectory[index++ + ((Object[])newSegments).length] = $this$commonSubstring.getDirectory$okio()[s2 + ((Object[])$this$commonSubstring.getSegments$okio()).length];
                if (s2 == n3) break;
                ++s2;
            }
        }
        int segmentOffset = beginSegment == 0 ? 0 : $this$commonSubstring.getDirectory$okio()[beginSegment - 1];
        int n5 = ((Object[])newSegments).length;
        newDirectory[n5] = newDirectory[n5] + (beginIndex - segmentOffset);
        return new SegmentedByteString(newSegments, newDirectory);
    }

    public static final byte commonInternalGet(@NotNull SegmentedByteString $this$commonInternalGet, int pos) {
        int $i$f$commonInternalGet = 0;
        Intrinsics.checkNotNullParameter($this$commonInternalGet, "$this$commonInternalGet");
        -Util.checkOffsetAndCount($this$commonInternalGet.getDirectory$okio()[((Object[])$this$commonInternalGet.getSegments$okio()).length - 1], pos, 1L);
        int segment = SegmentedByteStringKt.segment($this$commonInternalGet, pos);
        int segmentOffset = segment == 0 ? 0 : $this$commonInternalGet.getDirectory$okio()[segment - 1];
        int segmentPos = $this$commonInternalGet.getDirectory$okio()[segment + ((Object[])$this$commonInternalGet.getSegments$okio()).length];
        return $this$commonInternalGet.getSegments$okio()[segment][pos - segmentOffset + segmentPos];
    }

    public static final int commonGetSize(@NotNull SegmentedByteString $this$commonGetSize) {
        int $i$f$commonGetSize = 0;
        Intrinsics.checkNotNullParameter($this$commonGetSize, "$this$commonGetSize");
        return $this$commonGetSize.getDirectory$okio()[((Object[])$this$commonGetSize.getSegments$okio()).length - 1];
    }

    /*
     * WARNING - void declaration
     */
    @NotNull
    public static final byte[] commonToByteArray(@NotNull SegmentedByteString $this$commonToByteArray) {
        int $i$f$commonToByteArray = 0;
        Intrinsics.checkNotNullParameter($this$commonToByteArray, "$this$commonToByteArray");
        byte[] result = new byte[$this$commonToByteArray.size()];
        int resultPos = 0;
        SegmentedByteString $this$forEachSegment$iv = $this$commonToByteArray;
        boolean $i$f$forEachSegment = false;
        int segmentCount$iv = ((Object[])$this$forEachSegment$iv.getSegments$okio()).length;
        int pos$iv = 0;
        for (int s$iv = 0; s$iv < segmentCount$iv; ++s$iv) {
            void byteCount;
            void offset;
            int segmentPos$iv = $this$forEachSegment$iv.getDirectory$okio()[segmentCount$iv + s$iv];
            int nextSegmentOffset$iv = $this$forEachSegment$iv.getDirectory$okio()[s$iv];
            int n = nextSegmentOffset$iv - pos$iv;
            int n2 = segmentPos$iv;
            byte[] data = $this$forEachSegment$iv.getSegments$okio()[s$iv];
            boolean bl = false;
            ArraysKt.copyInto(data, result, resultPos, (int)offset, (int)(offset + byteCount));
            resultPos += byteCount;
            pos$iv = nextSegmentOffset$iv;
        }
        return result;
    }

    /*
     * WARNING - void declaration
     */
    public static final void commonWrite(@NotNull SegmentedByteString $this$commonWrite, @NotNull Buffer buffer, int offset, int byteCount) {
        void $this$forEachSegment$iv;
        int $i$f$commonWrite = 0;
        Intrinsics.checkNotNullParameter($this$commonWrite, "$this$commonWrite");
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        SegmentedByteString segmentedByteString = $this$commonWrite;
        int endIndex$iv = offset + byteCount;
        boolean $i$f$forEachSegment = false;
        int s$iv = SegmentedByteStringKt.segment((SegmentedByteString)$this$forEachSegment$iv, offset);
        int pos$iv = offset;
        while (pos$iv < endIndex$iv) {
            void byteCount2;
            void offset2;
            int segmentOffset$iv = s$iv == 0 ? 0 : $this$forEachSegment$iv.getDirectory$okio()[s$iv - 1];
            int segmentSize$iv = $this$forEachSegment$iv.getDirectory$okio()[s$iv] - segmentOffset$iv;
            int segmentPos$iv = $this$forEachSegment$iv.getDirectory$okio()[((Object[])$this$forEachSegment$iv.getSegments$okio()).length + s$iv];
            int n = segmentOffset$iv + segmentSize$iv;
            boolean bl = false;
            int byteCount$iv = Math.min(endIndex$iv, n) - pos$iv;
            int offset$iv = segmentPos$iv + (pos$iv - segmentOffset$iv);
            int n2 = byteCount$iv;
            int n3 = offset$iv;
            byte[] data = $this$forEachSegment$iv.getSegments$okio()[s$iv];
            boolean bl2 = false;
            Segment segment = new Segment(data, (int)offset2, (int)(offset2 + byteCount2), true, false);
            if (buffer.head == null) {
                buffer.head = segment.next = (segment.prev = segment);
            } else {
                Segment segment2 = buffer.head;
                Intrinsics.checkNotNull(segment2);
                Segment segment3 = segment2.prev;
                Intrinsics.checkNotNull(segment3);
                segment3.push(segment);
            }
            pos$iv += byteCount$iv;
            ++s$iv;
        }
        Buffer buffer2 = buffer;
        buffer2.setSize$okio(buffer2.size() + (long)$this$commonWrite.size());
    }

    /*
     * WARNING - void declaration
     */
    public static final boolean commonRangeEquals(@NotNull SegmentedByteString $this$commonRangeEquals, int offset, @NotNull ByteString other, int otherOffset, int byteCount) {
        void $this$forEachSegment$iv;
        int $i$f$commonRangeEquals = 0;
        Intrinsics.checkNotNullParameter($this$commonRangeEquals, "$this$commonRangeEquals");
        Intrinsics.checkNotNullParameter(other, "other");
        if (offset < 0 || offset > $this$commonRangeEquals.size() - byteCount) {
            return false;
        }
        int otherOffset2 = otherOffset;
        SegmentedByteString segmentedByteString = $this$commonRangeEquals;
        int endIndex$iv = offset + byteCount;
        boolean $i$f$forEachSegment = false;
        int s$iv = SegmentedByteStringKt.segment((SegmentedByteString)$this$forEachSegment$iv, offset);
        int pos$iv = offset;
        while (pos$iv < endIndex$iv) {
            void byteCount2;
            void offset2;
            int segmentOffset$iv = s$iv == 0 ? 0 : $this$forEachSegment$iv.getDirectory$okio()[s$iv - 1];
            int segmentSize$iv = $this$forEachSegment$iv.getDirectory$okio()[s$iv] - segmentOffset$iv;
            int segmentPos$iv = $this$forEachSegment$iv.getDirectory$okio()[((Object[])$this$forEachSegment$iv.getSegments$okio()).length + s$iv];
            int n = segmentOffset$iv + segmentSize$iv;
            boolean bl = false;
            int byteCount$iv = Math.min(endIndex$iv, n) - pos$iv;
            int offset$iv = segmentPos$iv + (pos$iv - segmentOffset$iv);
            int n2 = byteCount$iv;
            int n3 = offset$iv;
            byte[] data = $this$forEachSegment$iv.getSegments$okio()[s$iv];
            boolean bl2 = false;
            if (!other.rangeEquals(otherOffset2, data, (int)offset2, (int)byteCount2)) {
                return false;
            }
            otherOffset2 += byteCount2;
            pos$iv += byteCount$iv;
            ++s$iv;
        }
        return true;
    }

    /*
     * WARNING - void declaration
     */
    public static final boolean commonRangeEquals(@NotNull SegmentedByteString $this$commonRangeEquals, int offset, @NotNull byte[] other, int otherOffset, int byteCount) {
        void $this$forEachSegment$iv;
        int $i$f$commonRangeEquals = 0;
        Intrinsics.checkNotNullParameter($this$commonRangeEquals, "$this$commonRangeEquals");
        Intrinsics.checkNotNullParameter(other, "other");
        if (offset < 0 || offset > $this$commonRangeEquals.size() - byteCount || otherOffset < 0 || otherOffset > other.length - byteCount) {
            return false;
        }
        int otherOffset2 = otherOffset;
        SegmentedByteString segmentedByteString = $this$commonRangeEquals;
        int endIndex$iv = offset + byteCount;
        boolean $i$f$forEachSegment = false;
        int s$iv = SegmentedByteStringKt.segment((SegmentedByteString)$this$forEachSegment$iv, offset);
        int pos$iv = offset;
        while (pos$iv < endIndex$iv) {
            void byteCount2;
            void offset2;
            int segmentOffset$iv = s$iv == 0 ? 0 : $this$forEachSegment$iv.getDirectory$okio()[s$iv - 1];
            int segmentSize$iv = $this$forEachSegment$iv.getDirectory$okio()[s$iv] - segmentOffset$iv;
            int segmentPos$iv = $this$forEachSegment$iv.getDirectory$okio()[((Object[])$this$forEachSegment$iv.getSegments$okio()).length + s$iv];
            int n = segmentOffset$iv + segmentSize$iv;
            boolean bl = false;
            int byteCount$iv = Math.min(endIndex$iv, n) - pos$iv;
            int offset$iv = segmentPos$iv + (pos$iv - segmentOffset$iv);
            int n2 = byteCount$iv;
            int n3 = offset$iv;
            byte[] data = $this$forEachSegment$iv.getSegments$okio()[s$iv];
            boolean bl2 = false;
            if (!-Util.arrayRangeEquals(data, (int)offset2, other, otherOffset2, (int)byteCount2)) {
                return false;
            }
            otherOffset2 += byteCount2;
            pos$iv += byteCount$iv;
            ++s$iv;
        }
        return true;
    }

    public static final boolean commonEquals(@NotNull SegmentedByteString $this$commonEquals, @Nullable Object other) {
        int $i$f$commonEquals = 0;
        Intrinsics.checkNotNullParameter($this$commonEquals, "$this$commonEquals");
        return other == $this$commonEquals ? true : (other instanceof ByteString ? ((ByteString)other).size() == $this$commonEquals.size() && $this$commonEquals.rangeEquals(0, (ByteString)other, 0, $this$commonEquals.size()) : false);
    }

    /*
     * WARNING - void declaration
     */
    public static final int commonHashCode(@NotNull SegmentedByteString $this$commonHashCode) {
        int $i$f$commonHashCode = 0;
        Intrinsics.checkNotNullParameter($this$commonHashCode, "$this$commonHashCode");
        int result = $this$commonHashCode.getHashCode$okio();
        if (result != 0) {
            return result;
        }
        result = 1;
        SegmentedByteString $this$forEachSegment$iv = $this$commonHashCode;
        boolean $i$f$forEachSegment = false;
        int segmentCount$iv = ((Object[])$this$forEachSegment$iv.getSegments$okio()).length;
        int pos$iv = 0;
        for (int s$iv = 0; s$iv < segmentCount$iv; ++s$iv) {
            void byteCount;
            void offset;
            int segmentPos$iv = $this$forEachSegment$iv.getDirectory$okio()[segmentCount$iv + s$iv];
            int nextSegmentOffset$iv = $this$forEachSegment$iv.getDirectory$okio()[s$iv];
            int n = nextSegmentOffset$iv - pos$iv;
            int n2 = segmentPos$iv;
            byte[] data = $this$forEachSegment$iv.getSegments$okio()[s$iv];
            boolean bl = false;
            void limit = offset + byteCount;
            for (void i = offset; i < limit; ++i) {
                result = 31 * result + data[i];
            }
            pos$iv = nextSegmentOffset$iv;
        }
        $this$commonHashCode.setHashCode$okio(result);
        return result;
    }

    public static final /* synthetic */ void access$forEachSegment(SegmentedByteString $this$access_u24forEachSegment, int beginIndex, int endIndex, Function3 action) {
        SegmentedByteStringKt.forEachSegment($this$access_u24forEachSegment, beginIndex, endIndex, action);
    }
}

