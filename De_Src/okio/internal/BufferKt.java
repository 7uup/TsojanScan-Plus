/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okio.internal;

import java.io.EOFException;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import okio.-Platform;
import okio.-Util;
import okio.Buffer;
import okio.ByteString;
import okio.Options;
import okio.Segment;
import okio.SegmentPool;
import okio.SegmentedByteString;
import okio.Sink;
import okio.Source;
import okio.internal._Utf8Kt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=2, d1={"\u0000v\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\n\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a0\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\bH\u0000\u001a\r\u0010\u0011\u001a\u00020\u0012*\u00020\u0013H\u0080\b\u001a\r\u0010\u0014\u001a\u00020\u0005*\u00020\u0013H\u0080\b\u001a\r\u0010\u0015\u001a\u00020\u0013*\u00020\u0013H\u0080\b\u001a%\u0010\u0016\u001a\u00020\u0013*\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u0005H\u0080\b\u001a\u0017\u0010\u001a\u001a\u00020\n*\u00020\u00132\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0080\b\u001a\u0015\u0010\u001d\u001a\u00020\u001e*\u00020\u00132\u0006\u0010\u001f\u001a\u00020\u0005H\u0080\b\u001a\r\u0010 \u001a\u00020\b*\u00020\u0013H\u0080\b\u001a%\u0010!\u001a\u00020\u0005*\u00020\u00132\u0006\u0010\"\u001a\u00020\u001e2\u0006\u0010#\u001a\u00020\u00052\u0006\u0010$\u001a\u00020\u0005H\u0080\b\u001a\u001d\u0010!\u001a\u00020\u0005*\u00020\u00132\u0006\u0010\u000e\u001a\u00020%2\u0006\u0010#\u001a\u00020\u0005H\u0080\b\u001a\u001d\u0010&\u001a\u00020\u0005*\u00020\u00132\u0006\u0010'\u001a\u00020%2\u0006\u0010#\u001a\u00020\u0005H\u0080\b\u001a-\u0010(\u001a\u00020\n*\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020%2\u0006\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\bH\u0080\b\u001a\u0015\u0010)\u001a\u00020\b*\u00020\u00132\u0006\u0010*\u001a\u00020\u0001H\u0080\b\u001a%\u0010)\u001a\u00020\b*\u00020\u00132\u0006\u0010*\u001a\u00020\u00012\u0006\u0010\u0018\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\bH\u0080\b\u001a\u001d\u0010)\u001a\u00020\u0005*\u00020\u00132\u0006\u0010*\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u0005H\u0080\b\u001a\u0015\u0010+\u001a\u00020\u0005*\u00020\u00132\u0006\u0010*\u001a\u00020,H\u0080\b\u001a\r\u0010-\u001a\u00020\u001e*\u00020\u0013H\u0080\b\u001a\r\u0010.\u001a\u00020\u0001*\u00020\u0013H\u0080\b\u001a\u0015\u0010.\u001a\u00020\u0001*\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u0005H\u0080\b\u001a\r\u0010/\u001a\u00020%*\u00020\u0013H\u0080\b\u001a\u0015\u0010/\u001a\u00020%*\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u0005H\u0080\b\u001a\r\u00100\u001a\u00020\u0005*\u00020\u0013H\u0080\b\u001a\u0015\u00101\u001a\u00020\u0012*\u00020\u00132\u0006\u0010*\u001a\u00020\u0001H\u0080\b\u001a\u001d\u00101\u001a\u00020\u0012*\u00020\u00132\u0006\u0010*\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u0005H\u0080\b\u001a\r\u00102\u001a\u00020\u0005*\u00020\u0013H\u0080\b\u001a\r\u00103\u001a\u00020\b*\u00020\u0013H\u0080\b\u001a\r\u00104\u001a\u00020\u0005*\u00020\u0013H\u0080\b\u001a\r\u00105\u001a\u000206*\u00020\u0013H\u0080\b\u001a\u0015\u00107\u001a\u000208*\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u0005H\u0080\b\u001a\r\u00109\u001a\u00020\b*\u00020\u0013H\u0080\b\u001a\u000f\u0010:\u001a\u0004\u0018\u000108*\u00020\u0013H\u0080\b\u001a\u0015\u0010;\u001a\u000208*\u00020\u00132\u0006\u0010<\u001a\u00020\u0005H\u0080\b\u001a\u0015\u0010=\u001a\u00020\b*\u00020\u00132\u0006\u0010>\u001a\u00020?H\u0080\b\u001a\u0015\u0010@\u001a\u00020\u0012*\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u0005H\u0080\b\u001a\r\u0010A\u001a\u00020%*\u00020\u0013H\u0080\b\u001a\u0015\u0010A\u001a\u00020%*\u00020\u00132\u0006\u0010\u0019\u001a\u00020\bH\u0080\b\u001a\u0015\u0010B\u001a\u00020\f*\u00020\u00132\u0006\u0010C\u001a\u00020\bH\u0080\b\u001a\u0015\u0010D\u001a\u00020\u0013*\u00020\u00132\u0006\u0010E\u001a\u00020\u0001H\u0080\b\u001a%\u0010D\u001a\u00020\u0013*\u00020\u00132\u0006\u0010E\u001a\u00020\u00012\u0006\u0010\u0018\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\bH\u0080\b\u001a\u001d\u0010D\u001a\u00020\u0012*\u00020\u00132\u0006\u0010E\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u0005H\u0080\b\u001a)\u0010D\u001a\u00020\u0013*\u00020\u00132\u0006\u0010F\u001a\u00020%2\b\b\u0002\u0010\u0018\u001a\u00020\b2\b\b\u0002\u0010\u0019\u001a\u00020\bH\u0080\b\u001a\u001d\u0010D\u001a\u00020\u0013*\u00020\u00132\u0006\u0010E\u001a\u00020G2\u0006\u0010\u0019\u001a\u00020\u0005H\u0080\b\u001a\u0015\u0010H\u001a\u00020\u0005*\u00020\u00132\u0006\u0010E\u001a\u00020GH\u0080\b\u001a\u0015\u0010I\u001a\u00020\u0013*\u00020\u00132\u0006\u0010\"\u001a\u00020\bH\u0080\b\u001a\u0015\u0010J\u001a\u00020\u0013*\u00020\u00132\u0006\u0010K\u001a\u00020\u0005H\u0080\b\u001a\u0015\u0010L\u001a\u00020\u0013*\u00020\u00132\u0006\u0010K\u001a\u00020\u0005H\u0080\b\u001a\u0015\u0010M\u001a\u00020\u0013*\u00020\u00132\u0006\u0010N\u001a\u00020\bH\u0080\b\u001a\u0015\u0010O\u001a\u00020\u0013*\u00020\u00132\u0006\u0010K\u001a\u00020\u0005H\u0080\b\u001a\u0015\u0010P\u001a\u00020\u0013*\u00020\u00132\u0006\u0010Q\u001a\u00020\bH\u0080\b\u001a%\u0010R\u001a\u00020\u0013*\u00020\u00132\u0006\u0010S\u001a\u0002082\u0006\u0010T\u001a\u00020\b2\u0006\u0010U\u001a\u00020\bH\u0080\b\u001a\u0015\u0010V\u001a\u00020\u0013*\u00020\u00132\u0006\u0010W\u001a\u00020\bH\u0080\b\u001a\u0014\u0010X\u001a\u000208*\u00020\u00132\u0006\u0010Y\u001a\u00020\u0005H\u0000\u001a?\u0010Z\u001a\u0002H[\"\u0004\b\u0000\u0010[*\u00020\u00132\u0006\u0010#\u001a\u00020\u00052\u001a\u0010\\\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010\f\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H[0]H\u0080\b\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010^\u001a\u001e\u0010_\u001a\u00020\b*\u00020\u00132\u0006\u0010>\u001a\u00020?2\b\b\u0002\u0010`\u001a\u00020\nH\u0000\"\u0014\u0010\u0000\u001a\u00020\u0001X\u0080\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u000e\u0010\u0004\u001a\u00020\u0005X\u0080T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0005X\u0080T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\bX\u0080T\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001\u00a8\u0006a"}, d2={"HEX_DIGIT_BYTES", "", "getHEX_DIGIT_BYTES", "()[B", "OVERFLOW_DIGIT_START", "", "OVERFLOW_ZONE", "SEGMENTING_THRESHOLD", "", "rangeEquals", "", "segment", "Lokio/Segment;", "segmentPos", "bytes", "bytesOffset", "bytesLimit", "commonClear", "", "Lokio/Buffer;", "commonCompleteSegmentByteCount", "commonCopy", "commonCopyTo", "out", "offset", "byteCount", "commonEquals", "other", "", "commonGet", "", "pos", "commonHashCode", "commonIndexOf", "b", "fromIndex", "toIndex", "Lokio/ByteString;", "commonIndexOfElement", "targetBytes", "commonRangeEquals", "commonRead", "sink", "commonReadAll", "Lokio/Sink;", "commonReadByte", "commonReadByteArray", "commonReadByteString", "commonReadDecimalLong", "commonReadFully", "commonReadHexadecimalUnsignedLong", "commonReadInt", "commonReadLong", "commonReadShort", "", "commonReadUtf8", "", "commonReadUtf8CodePoint", "commonReadUtf8Line", "commonReadUtf8LineStrict", "limit", "commonSelect", "options", "Lokio/Options;", "commonSkip", "commonSnapshot", "commonWritableSegment", "minimumCapacity", "commonWrite", "source", "byteString", "Lokio/Source;", "commonWriteAll", "commonWriteByte", "commonWriteDecimalLong", "v", "commonWriteHexadecimalUnsignedLong", "commonWriteInt", "i", "commonWriteLong", "commonWriteShort", "s", "commonWriteUtf8", "string", "beginIndex", "endIndex", "commonWriteUtf8CodePoint", "codePoint", "readUtf8Line", "newline", "seek", "T", "lambda", "Lkotlin/Function2;", "(Lokio/Buffer;JLkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "selectPrefix", "selectTruncated", "okio"})
public final class BufferKt {
    @NotNull
    private static final byte[] HEX_DIGIT_BYTES = -Platform.asUtf8ToByteArray("0123456789abcdef");
    public static final int SEGMENTING_THRESHOLD = 4096;
    public static final long OVERFLOW_ZONE = -922337203685477580L;
    public static final long OVERFLOW_DIGIT_START = -7L;

    @NotNull
    public static final byte[] getHEX_DIGIT_BYTES() {
        return HEX_DIGIT_BYTES;
    }

    public static final boolean rangeEquals(@NotNull Segment segment, int segmentPos, @NotNull byte[] bytes, int bytesOffset, int bytesLimit) {
        Intrinsics.checkNotNullParameter(segment, "segment");
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        Segment segment2 = segment;
        int segmentPos2 = segmentPos;
        int segmentLimit = segment2.limit;
        byte[] data = segment2.data;
        for (int i = bytesOffset; i < bytesLimit; ++i) {
            if (segmentPos2 == segmentLimit) {
                Intrinsics.checkNotNull(segment2.next);
                data = segment2.data;
                segmentPos2 = segment2.pos;
                segmentLimit = segment2.limit;
            }
            if (data[segmentPos2] != bytes[i]) {
                return false;
            }
            ++segmentPos2;
        }
        return true;
    }

    /*
     * WARNING - void declaration
     */
    @NotNull
    public static final String readUtf8Line(@NotNull Buffer $this$readUtf8Line, long newline) {
        String string;
        Intrinsics.checkNotNullParameter($this$readUtf8Line, "$this$readUtf8Line");
        if (newline > 0L && $this$readUtf8Line.getByte(newline - 1L) == (byte)13) {
            String result = $this$readUtf8Line.readUtf8(newline - 1L);
            $this$readUtf8Line.skip(2L);
            string = result;
        } else {
            void var3_3;
            String result = $this$readUtf8Line.readUtf8(newline);
            $this$readUtf8Line.skip(1L);
            string = var3_3;
        }
        return string;
    }

    public static final <T> T seek(@NotNull Buffer $this$seek, long fromIndex, @NotNull Function2<? super Segment, ? super Long, ? extends T> lambda2) {
        long nextOffset;
        int $i$f$seek = 0;
        Intrinsics.checkNotNullParameter($this$seek, "$this$seek");
        Intrinsics.checkNotNullParameter(lambda2, "lambda");
        Segment segment = $this$seek.head;
        if (segment == null) {
            return lambda2.invoke(null, -1L);
        }
        Segment s2 = segment;
        if ($this$seek.size() - fromIndex < fromIndex) {
            long offset;
            for (offset = $this$seek.size(); offset > fromIndex; offset -= (long)(s2.limit - s2.pos)) {
                Intrinsics.checkNotNull(s2.prev);
            }
            return lambda2.invoke(s2, offset);
        }
        long offset = 0L;
        while ((nextOffset = offset + (long)(s2.limit - s2.pos)) <= fromIndex) {
            Intrinsics.checkNotNull(s2.next);
            offset = nextOffset;
        }
        return lambda2.invoke(s2, offset);
    }

    /*
     * WARNING - void declaration
     */
    public static final int selectPrefix(@NotNull Buffer $this$selectPrefix, @NotNull Options options, boolean selectTruncated) {
        Segment head;
        Intrinsics.checkNotNullParameter($this$selectPrefix, "$this$selectPrefix");
        Intrinsics.checkNotNullParameter(options, "options");
        Segment segment = $this$selectPrefix.head;
        if (segment == null) {
            return selectTruncated ? -2 : -1;
        }
        Segment s2 = head = segment;
        byte[] data = head.data;
        int pos = head.pos;
        int limit = head.limit;
        int[] trie = options.getTrie$okio();
        int triePos = 0;
        int prefixIndex = -1;
        block0: while (true) {
            int n;
            int possiblePrefixIndex;
            int scanOrSelect = trie[triePos++];
            if ((possiblePrefixIndex = trie[triePos++]) != -1) {
                prefixIndex = possiblePrefixIndex;
            }
            int nextStep = 0;
            if (s2 == null) break;
            if (scanOrSelect < 0) {
                boolean scanComplete;
                int scanByteCount = -1 * scanOrSelect;
                int trieLimit = triePos + scanByteCount;
                do {
                    void $this$and$iv;
                    byte by = data[pos++];
                    int other$iv = 255;
                    boolean $i$f$and = false;
                    n = $this$and$iv & other$iv;
                    if (n != trie[triePos++]) {
                        return prefixIndex;
                    }
                    boolean bl = scanComplete = triePos == trieLimit;
                    if (pos != limit) continue;
                    Segment segment2 = s2;
                    Intrinsics.checkNotNull(segment2);
                    Intrinsics.checkNotNull(segment2.next);
                    pos = s2.pos;
                    data = s2.data;
                    limit = s2.limit;
                    if (s2 != head) continue;
                    if (!scanComplete) break block0;
                    s2 = null;
                } while (!scanComplete);
                nextStep = trie[triePos];
            } else {
                void $this$and$iv;
                int selectChoiceCount = scanOrSelect;
                n = data[pos++];
                int other$iv = 255;
                boolean $i$f$and = false;
                byte = $this$and$iv & other$iv;
                int selectLimit = triePos + selectChoiceCount;
                while (true) {
                    if (triePos == selectLimit) {
                        return prefixIndex;
                    }
                    if (byte == trie[triePos]) break;
                    ++triePos;
                }
                nextStep = trie[triePos + selectChoiceCount];
                if (pos == limit) {
                    Intrinsics.checkNotNull(s2.next);
                    pos = s2.pos;
                    data = s2.data;
                    limit = s2.limit;
                    if (s2 == head) {
                        s2 = null;
                    }
                }
            }
            if (nextStep >= 0) {
                return nextStep;
            }
            triePos = -nextStep;
        }
        if (selectTruncated) {
            return -2;
        }
        return prefixIndex;
    }

    public static /* synthetic */ int selectPrefix$default(Buffer buffer, Options options, boolean bl, int n, Object object) {
        if ((n & 2) != 0) {
            bl = false;
        }
        return BufferKt.selectPrefix(buffer, options, bl);
    }

    @NotNull
    public static final Buffer commonCopyTo(@NotNull Buffer $this$commonCopyTo, @NotNull Buffer out, long offset, long byteCount) {
        int $i$f$commonCopyTo = 0;
        Intrinsics.checkNotNullParameter($this$commonCopyTo, "$this$commonCopyTo");
        Intrinsics.checkNotNullParameter(out, "out");
        long offset2 = offset;
        long byteCount2 = byteCount;
        -Util.checkOffsetAndCount($this$commonCopyTo.size(), offset2, byteCount2);
        if (byteCount2 == 0L) {
            return $this$commonCopyTo;
        }
        Buffer buffer = out;
        buffer.setSize$okio(buffer.size() + byteCount2);
        Segment s2 = $this$commonCopyTo.head;
        while (true) {
            Segment segment = s2;
            Intrinsics.checkNotNull(segment);
            if (offset2 < (long)(segment.limit - s2.pos)) break;
            offset2 -= (long)(s2.limit - s2.pos);
            s2 = s2.next;
        }
        while (byteCount2 > 0L) {
            Segment segment = s2;
            Intrinsics.checkNotNull(segment);
            Segment copy = segment.sharedCopy();
            copy.pos += (int)offset2;
            int n = copy.pos + (int)byteCount2;
            int n2 = copy.limit;
            boolean bl = false;
            copy.limit = Math.min(n, n2);
            if (out.head == null) {
                out.head = copy.next = (copy.prev = copy);
            } else {
                Segment segment2 = out.head;
                Intrinsics.checkNotNull(segment2);
                Segment segment3 = segment2.prev;
                Intrinsics.checkNotNull(segment3);
                segment3.push(copy);
            }
            byteCount2 -= (long)(copy.limit - copy.pos);
            offset2 = 0L;
            s2 = s2.next;
        }
        return $this$commonCopyTo;
    }

    public static final long commonCompleteSegmentByteCount(@NotNull Buffer $this$commonCompleteSegmentByteCount) {
        int $i$f$commonCompleteSegmentByteCount = 0;
        Intrinsics.checkNotNullParameter($this$commonCompleteSegmentByteCount, "$this$commonCompleteSegmentByteCount");
        long result = $this$commonCompleteSegmentByteCount.size();
        if (result == 0L) {
            return 0L;
        }
        Segment segment = $this$commonCompleteSegmentByteCount.head;
        Intrinsics.checkNotNull(segment);
        Segment segment2 = segment.prev;
        Intrinsics.checkNotNull(segment2);
        Segment tail = segment2;
        if (tail.limit < 8192 && tail.owner) {
            result -= (long)(tail.limit - tail.pos);
        }
        return result;
    }

    public static final byte commonReadByte(@NotNull Buffer $this$commonReadByte) {
        int $i$f$commonReadByte = 0;
        Intrinsics.checkNotNullParameter($this$commonReadByte, "$this$commonReadByte");
        if ($this$commonReadByte.size() == 0L) {
            throw (Throwable)new EOFException();
        }
        Segment segment = $this$commonReadByte.head;
        Intrinsics.checkNotNull(segment);
        Segment segment2 = segment;
        int pos = segment2.pos;
        int limit = segment2.limit;
        byte[] data = segment2.data;
        byte b = data[pos++];
        Buffer buffer = $this$commonReadByte;
        buffer.setSize$okio(buffer.size() - 1L);
        if (pos == limit) {
            $this$commonReadByte.head = segment2.pop();
            SegmentPool.recycle(segment2);
        } else {
            segment2.pos = pos;
        }
        return b;
    }

    public static final short commonReadShort(@NotNull Buffer $this$commonReadShort) {
        byte $this$and$iv;
        int $i$f$commonReadShort = 0;
        Intrinsics.checkNotNullParameter($this$commonReadShort, "$this$commonReadShort");
        if ($this$commonReadShort.size() < 2L) {
            throw (Throwable)new EOFException();
        }
        Segment segment = $this$commonReadShort.head;
        Intrinsics.checkNotNull(segment);
        Segment segment2 = segment;
        int pos = segment2.pos;
        int limit = segment2.limit;
        if (limit - pos < 2) {
            byte $this$and$iv2;
            byte by = $this$commonReadShort.readByte();
            int other$iv = 255;
            boolean $i$f$and = false;
            int n = ($this$and$iv2 & other$iv) << 8;
            $this$and$iv2 = $this$commonReadShort.readByte();
            other$iv = 255;
            $i$f$and = false;
            int s2 = n | $this$and$iv2 & other$iv;
            return (short)s2;
        }
        byte[] data = segment2.data;
        byte other$iv = data[pos++];
        int other$iv2 = 255;
        boolean $i$f$and = false;
        int n = ($this$and$iv & other$iv2) << 8;
        $this$and$iv = data[pos++];
        other$iv2 = 255;
        $i$f$and = false;
        int s3 = n | $this$and$iv & other$iv2;
        Buffer buffer = $this$commonReadShort;
        buffer.setSize$okio(buffer.size() - 2L);
        if (pos == limit) {
            $this$commonReadShort.head = segment2.pop();
            SegmentPool.recycle(segment2);
        } else {
            segment2.pos = pos;
        }
        return (short)s3;
    }

    public static final int commonReadInt(@NotNull Buffer $this$commonReadInt) {
        byte $this$and$iv;
        int $i$f$commonReadInt = 0;
        Intrinsics.checkNotNullParameter($this$commonReadInt, "$this$commonReadInt");
        if ($this$commonReadInt.size() < 4L) {
            throw (Throwable)new EOFException();
        }
        Segment segment = $this$commonReadInt.head;
        Intrinsics.checkNotNull(segment);
        Segment segment2 = segment;
        int pos = segment2.pos;
        int limit = segment2.limit;
        if ((long)(limit - pos) < 4L) {
            byte $this$and$iv2;
            byte by = $this$commonReadInt.readByte();
            int other$iv = 255;
            boolean $i$f$and = false;
            int n = ($this$and$iv2 & other$iv) << 24;
            $this$and$iv2 = $this$commonReadInt.readByte();
            other$iv = 255;
            $i$f$and = false;
            int n2 = n | ($this$and$iv2 & other$iv) << 16;
            $this$and$iv2 = $this$commonReadInt.readByte();
            other$iv = 255;
            $i$f$and = false;
            int n3 = n2 | ($this$and$iv2 & other$iv) << 8;
            $this$and$iv2 = $this$commonReadInt.readByte();
            other$iv = 255;
            $i$f$and = false;
            return n3 | $this$and$iv2 & other$iv;
        }
        byte[] data = segment2.data;
        byte $i$f$and = data[pos++];
        int other$iv = 255;
        boolean $i$f$and2 = false;
        int n = ($this$and$iv & other$iv) << 24;
        $this$and$iv = data[pos++];
        other$iv = 255;
        $i$f$and2 = false;
        int n4 = n | ($this$and$iv & other$iv) << 16;
        $this$and$iv = data[pos++];
        other$iv = 255;
        $i$f$and2 = false;
        int n5 = n4 | ($this$and$iv & other$iv) << 8;
        $this$and$iv = data[pos++];
        other$iv = 255;
        $i$f$and2 = false;
        int i = n5 | $this$and$iv & other$iv;
        Buffer buffer = $this$commonReadInt;
        buffer.setSize$okio(buffer.size() - 4L);
        if (pos == limit) {
            $this$commonReadInt.head = segment2.pop();
            SegmentPool.recycle(segment2);
        } else {
            segment2.pos = pos;
        }
        return i;
    }

    public static final long commonReadLong(@NotNull Buffer $this$commonReadLong) {
        byte $this$and$iv;
        int $i$f$commonReadLong = 0;
        Intrinsics.checkNotNullParameter($this$commonReadLong, "$this$commonReadLong");
        if ($this$commonReadLong.size() < 8L) {
            throw (Throwable)new EOFException();
        }
        Segment segment = $this$commonReadLong.head;
        Intrinsics.checkNotNull(segment);
        Segment segment2 = segment;
        int pos = segment2.pos;
        int limit = segment2.limit;
        if ((long)(limit - pos) < 8L) {
            int $this$and$iv2;
            int n = $this$commonReadLong.readInt();
            long other$iv = 0xFFFFFFFFL;
            boolean $i$f$and = false;
            long l = ((long)$this$and$iv2 & other$iv) << 32;
            $this$and$iv2 = $this$commonReadLong.readInt();
            other$iv = 0xFFFFFFFFL;
            $i$f$and = false;
            return l | (long)$this$and$iv2 & other$iv;
        }
        byte[] data = segment2.data;
        byte $i$f$and = data[pos++];
        long other$iv = 255L;
        boolean $i$f$and2 = false;
        long l = ((long)$this$and$iv & other$iv) << 56;
        $this$and$iv = data[pos++];
        other$iv = 255L;
        $i$f$and2 = false;
        long l2 = l | ((long)$this$and$iv & other$iv) << 48;
        $this$and$iv = data[pos++];
        other$iv = 255L;
        $i$f$and2 = false;
        long l3 = l2 | ((long)$this$and$iv & other$iv) << 40;
        $this$and$iv = data[pos++];
        other$iv = 255L;
        $i$f$and2 = false;
        long l4 = l3 | ((long)$this$and$iv & other$iv) << 32;
        $this$and$iv = data[pos++];
        other$iv = 255L;
        $i$f$and2 = false;
        long l5 = l4 | ((long)$this$and$iv & other$iv) << 24;
        $this$and$iv = data[pos++];
        other$iv = 255L;
        $i$f$and2 = false;
        long l6 = l5 | ((long)$this$and$iv & other$iv) << 16;
        $this$and$iv = data[pos++];
        other$iv = 255L;
        $i$f$and2 = false;
        long l7 = l6 | ((long)$this$and$iv & other$iv) << 8;
        $this$and$iv = data[pos++];
        other$iv = 255L;
        $i$f$and2 = false;
        long v = l7 | (long)$this$and$iv & other$iv;
        Buffer buffer = $this$commonReadLong;
        buffer.setSize$okio(buffer.size() - 8L);
        if (pos == limit) {
            $this$commonReadLong.head = segment2.pop();
            SegmentPool.recycle(segment2);
        } else {
            segment2.pos = pos;
        }
        return v;
    }

    /*
     * WARNING - void declaration
     */
    public static final byte commonGet(@NotNull Buffer $this$commonGet, long pos) {
        void offset;
        long nextOffset$iv;
        int $i$f$commonGet = 0;
        Intrinsics.checkNotNullParameter($this$commonGet, "$this$commonGet");
        -Util.checkOffsetAndCount($this$commonGet.size(), pos, 1L);
        Buffer $this$seek$iv = $this$commonGet;
        boolean $i$f$seek = false;
        Segment segment = $this$seek$iv.head;
        if (segment == null) {
            void offset2;
            long l = -1L;
            Segment s2 = null;
            boolean bl = false;
            Segment segment2 = s2;
            Intrinsics.checkNotNull(segment2);
            return segment2.data[(int)((long)s2.pos + pos - offset2)];
        }
        Segment s$iv = segment;
        if ($this$seek$iv.size() - pos < pos) {
            void offset3;
            long offset$iv;
            for (offset$iv = $this$seek$iv.size(); offset$iv > pos; offset$iv -= (long)(s$iv.limit - s$iv.pos)) {
                Intrinsics.checkNotNull(s$iv.prev);
            }
            long l = offset$iv;
            Segment s3 = s$iv;
            boolean bl = false;
            Segment segment3 = s3;
            Intrinsics.checkNotNull(segment3);
            return segment3.data[(int)((long)s3.pos + pos - offset3)];
        }
        long offset$iv = 0L;
        while ((nextOffset$iv = offset$iv + (long)(s$iv.limit - s$iv.pos)) <= pos) {
            Intrinsics.checkNotNull(s$iv.next);
            offset$iv = nextOffset$iv;
        }
        long l = offset$iv;
        Segment s4 = s$iv;
        boolean bl = false;
        Segment segment4 = s4;
        Intrinsics.checkNotNull(segment4);
        return segment4.data[(int)((long)s4.pos + pos - offset)];
    }

    public static final void commonClear(@NotNull Buffer $this$commonClear) {
        int $i$f$commonClear = 0;
        Intrinsics.checkNotNullParameter($this$commonClear, "$this$commonClear");
        $this$commonClear.skip($this$commonClear.size());
    }

    public static final void commonSkip(@NotNull Buffer $this$commonSkip, long byteCount) {
        int $i$f$commonSkip = 0;
        Intrinsics.checkNotNullParameter($this$commonSkip, "$this$commonSkip");
        long byteCount2 = byteCount;
        while (byteCount2 > 0L) {
            Segment head;
            if ($this$commonSkip.head == null) {
                throw (Throwable)new EOFException();
            }
            int b$iv = head.limit - head.pos;
            boolean $i$f$minOf = false;
            long l = b$iv;
            boolean bl = false;
            int toSkip = (int)Math.min(byteCount2, l);
            Buffer buffer = $this$commonSkip;
            buffer.setSize$okio(buffer.size() - (long)toSkip);
            byteCount2 -= (long)toSkip;
            head.pos += toSkip;
            if (head.pos != head.limit) continue;
            $this$commonSkip.head = head.pop();
            SegmentPool.recycle(head);
        }
    }

    @NotNull
    public static final Buffer commonWrite(@NotNull Buffer $this$commonWrite, @NotNull ByteString byteString, int offset, int byteCount) {
        int $i$f$commonWrite = 0;
        Intrinsics.checkNotNullParameter($this$commonWrite, "$this$commonWrite");
        Intrinsics.checkNotNullParameter(byteString, "byteString");
        byteString.write$okio($this$commonWrite, offset, byteCount);
        return $this$commonWrite;
    }

    public static /* synthetic */ Buffer commonWrite$default(Buffer $this$commonWrite, ByteString byteString, int offset, int byteCount, int n, Object object) {
        if ((n & 2) != 0) {
            offset = 0;
        }
        if ((n & 4) != 0) {
            byteCount = byteString.size();
        }
        boolean $i$f$commonWrite = false;
        Intrinsics.checkNotNullParameter($this$commonWrite, "$this$commonWrite");
        Intrinsics.checkNotNullParameter(byteString, "byteString");
        byteString.write$okio($this$commonWrite, offset, byteCount);
        return $this$commonWrite;
    }

    @NotNull
    public static final Buffer commonWriteDecimalLong(@NotNull Buffer $this$commonWriteDecimalLong, long v) {
        int width;
        int $i$f$commonWriteDecimalLong = 0;
        Intrinsics.checkNotNullParameter($this$commonWriteDecimalLong, "$this$commonWriteDecimalLong");
        long v2 = v;
        if (v2 == 0L) {
            return $this$commonWriteDecimalLong.writeByte(48);
        }
        boolean negative = false;
        if (v2 < 0L) {
            if ((v2 = -v2) < 0L) {
                return $this$commonWriteDecimalLong.writeUtf8("-9223372036854775808");
            }
            negative = true;
        }
        int n = v2 < 100000000L ? (v2 < 10000L ? (v2 < 100L ? (v2 < 10L ? 1 : 2) : (v2 < 1000L ? 3 : 4)) : (v2 < 1000000L ? (v2 < 100000L ? 5 : 6) : (v2 < 10000000L ? 7 : 8))) : (v2 < 1000000000000L ? (v2 < 10000000000L ? (v2 < 1000000000L ? 9 : 10) : (v2 < 100000000000L ? 11 : 12)) : (v2 < 1000000000000000L ? (v2 < 10000000000000L ? 13 : (v2 < 100000000000000L ? 14 : 15)) : (v2 < 100000000000000000L ? (v2 < 10000000000000000L ? 16 : 17) : (width = v2 < 1000000000000000000L ? 18 : 19))));
        if (negative) {
            ++width;
        }
        Segment tail = $this$commonWriteDecimalLong.writableSegment$okio(width);
        byte[] data = tail.data;
        int pos = tail.limit + width;
        while (v2 != 0L) {
            int digit = (int)(v2 % (long)10);
            data[--pos] = BufferKt.getHEX_DIGIT_BYTES()[digit];
            v2 /= (long)10;
        }
        if (negative) {
            data[--pos] = (byte)45;
        }
        tail.limit += width;
        Buffer buffer = $this$commonWriteDecimalLong;
        buffer.setSize$okio(buffer.size() + (long)width);
        return $this$commonWriteDecimalLong;
    }

    @NotNull
    public static final Buffer commonWriteHexadecimalUnsignedLong(@NotNull Buffer $this$commonWriteHexadecimalUnsignedLong, long v) {
        int $i$f$commonWriteHexadecimalUnsignedLong = 0;
        Intrinsics.checkNotNullParameter($this$commonWriteHexadecimalUnsignedLong, "$this$commonWriteHexadecimalUnsignedLong");
        long v2 = v;
        if (v2 == 0L) {
            return $this$commonWriteHexadecimalUnsignedLong.writeByte(48);
        }
        long x = v2;
        x |= x >>> 1;
        x |= x >>> 2;
        x |= x >>> 4;
        x |= x >>> 8;
        x |= x >>> 16;
        x |= x >>> 32;
        x -= x >>> 1 & 0x5555555555555555L;
        x = (x >>> 2 & 0x3333333333333333L) + (x & 0x3333333333333333L);
        x = (x >>> 4) + x & 0xF0F0F0F0F0F0F0FL;
        x += x >>> 8;
        x += x >>> 16;
        x = (x & 0x3FL) + (x >>> 32 & 0x3FL);
        int width = (int)((x + (long)3) / (long)4);
        Segment tail = $this$commonWriteHexadecimalUnsignedLong.writableSegment$okio(width);
        byte[] data = tail.data;
        int start = tail.limit;
        for (int pos = tail.limit + width - 1; pos >= start; --pos) {
            data[pos] = BufferKt.getHEX_DIGIT_BYTES()[(int)(v2 & 0xFL)];
            v2 >>>= 4;
        }
        tail.limit += width;
        Buffer buffer = $this$commonWriteHexadecimalUnsignedLong;
        buffer.setSize$okio(buffer.size() + (long)width);
        return $this$commonWriteHexadecimalUnsignedLong;
    }

    @NotNull
    public static final Segment commonWritableSegment(@NotNull Buffer $this$commonWritableSegment, int minimumCapacity) {
        Segment tail;
        int $i$f$commonWritableSegment = 0;
        Intrinsics.checkNotNullParameter($this$commonWritableSegment, "$this$commonWritableSegment");
        boolean bl = minimumCapacity >= 1 && minimumCapacity <= 8192;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "unexpected capacity";
            throw (Throwable)new IllegalArgumentException(string.toString());
        }
        if ($this$commonWritableSegment.head == null) {
            Segment result;
            $this$commonWritableSegment.head = result = SegmentPool.take();
            result.prev = result;
            result.next = result;
            return result;
        }
        Segment segment = $this$commonWritableSegment.head;
        Intrinsics.checkNotNull(segment);
        Segment segment2 = tail = segment.prev;
        Intrinsics.checkNotNull(segment2);
        if (segment2.limit + minimumCapacity > 8192 || !tail.owner) {
            tail = tail.push(SegmentPool.take());
        }
        return tail;
    }

    @NotNull
    public static final Buffer commonWrite(@NotNull Buffer $this$commonWrite, @NotNull byte[] source2) {
        int $i$f$commonWrite = 0;
        Intrinsics.checkNotNullParameter($this$commonWrite, "$this$commonWrite");
        Intrinsics.checkNotNullParameter(source2, "source");
        return $this$commonWrite.write(source2, 0, source2.length);
    }

    @NotNull
    public static final Buffer commonWrite(@NotNull Buffer $this$commonWrite, @NotNull byte[] source2, int offset, int byteCount) {
        int $i$f$commonWrite = 0;
        Intrinsics.checkNotNullParameter($this$commonWrite, "$this$commonWrite");
        Intrinsics.checkNotNullParameter(source2, "source");
        int offset2 = offset;
        -Util.checkOffsetAndCount(source2.length, offset2, byteCount);
        int limit = offset2 + byteCount;
        while (offset2 < limit) {
            Segment tail = $this$commonWrite.writableSegment$okio(1);
            int n = limit - offset2;
            int n2 = 8192 - tail.limit;
            boolean bl = false;
            int toCopy = Math.min(n, n2);
            ArraysKt.copyInto(source2, tail.data, tail.limit, offset2, offset2 + toCopy);
            offset2 += toCopy;
            tail.limit += toCopy;
        }
        Buffer buffer = $this$commonWrite;
        buffer.setSize$okio(buffer.size() + (long)byteCount);
        return $this$commonWrite;
    }

    @NotNull
    public static final byte[] commonReadByteArray(@NotNull Buffer $this$commonReadByteArray) {
        int $i$f$commonReadByteArray = 0;
        Intrinsics.checkNotNullParameter($this$commonReadByteArray, "$this$commonReadByteArray");
        return $this$commonReadByteArray.readByteArray($this$commonReadByteArray.size());
    }

    @NotNull
    public static final byte[] commonReadByteArray(@NotNull Buffer $this$commonReadByteArray, long byteCount) {
        int $i$f$commonReadByteArray = 0;
        Intrinsics.checkNotNullParameter($this$commonReadByteArray, "$this$commonReadByteArray");
        boolean bl = byteCount >= 0L && byteCount <= (long)Integer.MAX_VALUE;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "byteCount: " + byteCount;
            throw (Throwable)new IllegalArgumentException(string.toString());
        }
        if ($this$commonReadByteArray.size() < byteCount) {
            throw (Throwable)new EOFException();
        }
        byte[] result = new byte[(int)byteCount];
        $this$commonReadByteArray.readFully(result);
        return result;
    }

    public static final int commonRead(@NotNull Buffer $this$commonRead, @NotNull byte[] sink2) {
        int $i$f$commonRead = 0;
        Intrinsics.checkNotNullParameter($this$commonRead, "$this$commonRead");
        Intrinsics.checkNotNullParameter(sink2, "sink");
        return $this$commonRead.read(sink2, 0, sink2.length);
    }

    public static final void commonReadFully(@NotNull Buffer $this$commonReadFully, @NotNull byte[] sink2) {
        int read;
        int $i$f$commonReadFully = 0;
        Intrinsics.checkNotNullParameter($this$commonReadFully, "$this$commonReadFully");
        Intrinsics.checkNotNullParameter(sink2, "sink");
        for (int offset = 0; offset < sink2.length; offset += read) {
            read = $this$commonReadFully.read(sink2, offset, sink2.length - offset);
            if (read != -1) continue;
            throw (Throwable)new EOFException();
        }
    }

    public static final int commonRead(@NotNull Buffer $this$commonRead, @NotNull byte[] sink2, int offset, int byteCount) {
        int $i$f$commonRead = 0;
        Intrinsics.checkNotNullParameter($this$commonRead, "$this$commonRead");
        Intrinsics.checkNotNullParameter(sink2, "sink");
        -Util.checkOffsetAndCount(sink2.length, offset, byteCount);
        Segment segment = $this$commonRead.head;
        if (segment == null) {
            return -1;
        }
        Segment s2 = segment;
        int n = s2.limit - s2.pos;
        boolean bl = false;
        int toCopy = Math.min(byteCount, n);
        ArraysKt.copyInto(s2.data, sink2, offset, s2.pos, s2.pos + toCopy);
        s2.pos += toCopy;
        Buffer buffer = $this$commonRead;
        buffer.setSize$okio(buffer.size() - (long)toCopy);
        if (s2.pos == s2.limit) {
            $this$commonRead.head = s2.pop();
            SegmentPool.recycle(s2);
        }
        return toCopy;
    }

    public static final long commonReadDecimalLong(@NotNull Buffer $this$commonReadDecimalLong) {
        int $i$f$commonReadDecimalLong = 0;
        Intrinsics.checkNotNullParameter($this$commonReadDecimalLong, "$this$commonReadDecimalLong");
        if ($this$commonReadDecimalLong.size() == 0L) {
            throw (Throwable)new EOFException();
        }
        long value = 0L;
        int seen = 0;
        boolean negative = false;
        boolean done = false;
        long overflowDigit = -7L;
        do {
            Segment segment;
            Intrinsics.checkNotNull($this$commonReadDecimalLong.head);
            byte[] data = segment.data;
            int pos = segment.pos;
            int limit = segment.limit;
            while (pos < limit) {
                byte b = data[pos];
                if (b >= (byte)48 && b <= (byte)57) {
                    int digit = (byte)48 - b;
                    if (value < -922337203685477580L || value == -922337203685477580L && (long)digit < overflowDigit) {
                        Buffer buffer = new Buffer().writeDecimalLong(value).writeByte(b);
                        if (!negative) {
                            buffer.readByte();
                        }
                        throw (Throwable)new NumberFormatException("Number too large: " + buffer.readUtf8());
                    }
                    value *= 10L;
                    value += (long)digit;
                } else if (b == (byte)45 && seen == 0) {
                    negative = true;
                    --overflowDigit;
                } else {
                    if (seen == 0) {
                        throw (Throwable)new NumberFormatException("Expected leading [0-9] or '-' character but was 0x" + -Util.toHexString(b));
                    }
                    done = true;
                    break;
                }
                ++pos;
                ++seen;
            }
            if (pos == limit) {
                $this$commonReadDecimalLong.head = segment.pop();
                SegmentPool.recycle(segment);
                continue;
            }
            segment.pos = pos;
        } while (!done && $this$commonReadDecimalLong.head != null);
        Buffer buffer = $this$commonReadDecimalLong;
        buffer.setSize$okio(buffer.size() - (long)seen);
        return negative ? value : -value;
    }

    public static final long commonReadHexadecimalUnsignedLong(@NotNull Buffer $this$commonReadHexadecimalUnsignedLong) {
        int $i$f$commonReadHexadecimalUnsignedLong = 0;
        Intrinsics.checkNotNullParameter($this$commonReadHexadecimalUnsignedLong, "$this$commonReadHexadecimalUnsignedLong");
        if ($this$commonReadHexadecimalUnsignedLong.size() == 0L) {
            throw (Throwable)new EOFException();
        }
        long value = 0L;
        int seen = 0;
        boolean done = false;
        do {
            Segment segment;
            Intrinsics.checkNotNull($this$commonReadHexadecimalUnsignedLong.head);
            byte[] data = segment.data;
            int pos = segment.pos;
            int limit = segment.limit;
            while (pos < limit) {
                int digit = 0;
                byte b = data[pos];
                if (b >= (byte)48 && b <= (byte)57) {
                    digit = b - (byte)48;
                } else if (b >= (byte)97 && b <= (byte)102) {
                    digit = b - (byte)97 + 10;
                } else if (b >= (byte)65 && b <= (byte)70) {
                    digit = b - (byte)65 + 10;
                } else {
                    if (seen == 0) {
                        throw (Throwable)new NumberFormatException("Expected leading [0-9a-fA-F] character but was 0x" + -Util.toHexString(b));
                    }
                    done = true;
                    break;
                }
                if ((value & 0xF000000000000000L) != 0L) {
                    Buffer buffer = new Buffer().writeHexadecimalUnsignedLong(value).writeByte(b);
                    throw (Throwable)new NumberFormatException("Number too large: " + buffer.readUtf8());
                }
                value <<= 4;
                value |= (long)digit;
                ++pos;
                ++seen;
            }
            if (pos == limit) {
                $this$commonReadHexadecimalUnsignedLong.head = segment.pop();
                SegmentPool.recycle(segment);
                continue;
            }
            segment.pos = pos;
        } while (!done && $this$commonReadHexadecimalUnsignedLong.head != null);
        Buffer buffer = $this$commonReadHexadecimalUnsignedLong;
        buffer.setSize$okio(buffer.size() - (long)seen);
        return value;
    }

    @NotNull
    public static final ByteString commonReadByteString(@NotNull Buffer $this$commonReadByteString) {
        int $i$f$commonReadByteString = 0;
        Intrinsics.checkNotNullParameter($this$commonReadByteString, "$this$commonReadByteString");
        return $this$commonReadByteString.readByteString($this$commonReadByteString.size());
    }

    @NotNull
    public static final ByteString commonReadByteString(@NotNull Buffer $this$commonReadByteString, long byteCount) {
        int $i$f$commonReadByteString = 0;
        Intrinsics.checkNotNullParameter($this$commonReadByteString, "$this$commonReadByteString");
        boolean bl = byteCount >= 0L && byteCount <= (long)Integer.MAX_VALUE;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "byteCount: " + byteCount;
            throw (Throwable)new IllegalArgumentException(string.toString());
        }
        if ($this$commonReadByteString.size() < byteCount) {
            throw (Throwable)new EOFException();
        }
        if (byteCount >= (long)4096) {
            ByteString byteString = $this$commonReadByteString.snapshot((int)byteCount);
            bl2 = false;
            bl3 = false;
            ByteString it = byteString;
            boolean bl5 = false;
            $this$commonReadByteString.skip(byteCount);
            return byteString;
        }
        return new ByteString($this$commonReadByteString.readByteArray(byteCount));
    }

    public static final int commonSelect(@NotNull Buffer $this$commonSelect, @NotNull Options options) {
        int $i$f$commonSelect = 0;
        Intrinsics.checkNotNullParameter($this$commonSelect, "$this$commonSelect");
        Intrinsics.checkNotNullParameter(options, "options");
        int index = BufferKt.selectPrefix$default($this$commonSelect, options, false, 2, null);
        if (index == -1) {
            return -1;
        }
        int selectedSize = options.getByteStrings$okio()[index].size();
        $this$commonSelect.skip(selectedSize);
        return index;
    }

    public static final void commonReadFully(@NotNull Buffer $this$commonReadFully, @NotNull Buffer sink2, long byteCount) {
        int $i$f$commonReadFully = 0;
        Intrinsics.checkNotNullParameter($this$commonReadFully, "$this$commonReadFully");
        Intrinsics.checkNotNullParameter(sink2, "sink");
        if ($this$commonReadFully.size() < byteCount) {
            sink2.write($this$commonReadFully, $this$commonReadFully.size());
            throw (Throwable)new EOFException();
        }
        sink2.write($this$commonReadFully, byteCount);
    }

    public static final long commonReadAll(@NotNull Buffer $this$commonReadAll, @NotNull Sink sink2) {
        int $i$f$commonReadAll = 0;
        Intrinsics.checkNotNullParameter($this$commonReadAll, "$this$commonReadAll");
        Intrinsics.checkNotNullParameter(sink2, "sink");
        long byteCount = $this$commonReadAll.size();
        if (byteCount > 0L) {
            sink2.write($this$commonReadAll, byteCount);
        }
        return byteCount;
    }

    @NotNull
    public static final String commonReadUtf8(@NotNull Buffer $this$commonReadUtf8, long byteCount) {
        int $i$f$commonReadUtf8 = 0;
        Intrinsics.checkNotNullParameter($this$commonReadUtf8, "$this$commonReadUtf8");
        boolean bl = byteCount >= 0L && byteCount <= (long)Integer.MAX_VALUE;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "byteCount: " + byteCount;
            throw (Throwable)new IllegalArgumentException(string.toString());
        }
        if ($this$commonReadUtf8.size() < byteCount) {
            throw (Throwable)new EOFException();
        }
        if (byteCount == 0L) {
            return "";
        }
        Segment segment = $this$commonReadUtf8.head;
        Intrinsics.checkNotNull(segment);
        Segment s2 = segment;
        if ((long)s2.pos + byteCount > (long)s2.limit) {
            return _Utf8Kt.commonToUtf8String$default($this$commonReadUtf8.readByteArray(byteCount), 0, 0, 3, null);
        }
        String result = _Utf8Kt.commonToUtf8String(s2.data, s2.pos, s2.pos + (int)byteCount);
        s2.pos += (int)byteCount;
        Buffer buffer = $this$commonReadUtf8;
        buffer.setSize$okio(buffer.size() - byteCount);
        if (s2.pos == s2.limit) {
            $this$commonReadUtf8.head = s2.pop();
            SegmentPool.recycle(s2);
        }
        return result;
    }

    @Nullable
    public static final String commonReadUtf8Line(@NotNull Buffer $this$commonReadUtf8Line) {
        int $i$f$commonReadUtf8Line = 0;
        Intrinsics.checkNotNullParameter($this$commonReadUtf8Line, "$this$commonReadUtf8Line");
        long newline = $this$commonReadUtf8Line.indexOf((byte)10);
        return newline != -1L ? BufferKt.readUtf8Line($this$commonReadUtf8Line, newline) : ($this$commonReadUtf8Line.size() != 0L ? $this$commonReadUtf8Line.readUtf8($this$commonReadUtf8Line.size()) : null);
    }

    /*
     * WARNING - void declaration
     */
    @NotNull
    public static final String commonReadUtf8LineStrict(@NotNull Buffer $this$commonReadUtf8LineStrict, long limit) {
        void a$iv;
        int $i$f$commonReadUtf8LineStrict = 0;
        Intrinsics.checkNotNullParameter($this$commonReadUtf8LineStrict, "$this$commonReadUtf8LineStrict");
        boolean bl = limit >= 0L;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "limit < 0: " + limit;
            throw (Throwable)new IllegalArgumentException(string.toString());
        }
        long scanLength = limit == Long.MAX_VALUE ? Long.MAX_VALUE : limit + 1L;
        long newline = $this$commonReadUtf8LineStrict.indexOf((byte)10, 0L, scanLength);
        if (newline != -1L) {
            return BufferKt.readUtf8Line($this$commonReadUtf8LineStrict, newline);
        }
        if (scanLength < $this$commonReadUtf8LineStrict.size() && $this$commonReadUtf8LineStrict.getByte(scanLength - 1L) == (byte)13 && $this$commonReadUtf8LineStrict.getByte(scanLength) == (byte)10) {
            return BufferKt.readUtf8Line($this$commonReadUtf8LineStrict, scanLength);
        }
        Buffer data = new Buffer();
        int n = 32;
        long b$iv = $this$commonReadUtf8LineStrict.size();
        boolean $i$f$minOf = false;
        long l = (long)a$iv;
        boolean bl5 = false;
        $this$commonReadUtf8LineStrict.copyTo(data, 0L, Math.min(l, b$iv));
        long l2 = $this$commonReadUtf8LineStrict.size();
        boolean bl6 = false;
        throw (Throwable)new EOFException("\\n not found: limit=" + Math.min(l2, limit) + " content=" + data.readByteString().hex() + '\u2026');
    }

    /*
     * WARNING - void declaration
     */
    public static final int commonReadUtf8CodePoint(@NotNull Buffer $this$commonReadUtf8CodePoint) {
        int $this$and$iv;
        int $i$f$commonReadUtf8CodePoint = 0;
        Intrinsics.checkNotNullParameter($this$commonReadUtf8CodePoint, "$this$commonReadUtf8CodePoint");
        if ($this$commonReadUtf8CodePoint.size() == 0L) {
            throw (Throwable)new EOFException();
        }
        int b0 = $this$commonReadUtf8CodePoint.getByte(0L);
        int codePoint = 0;
        int byteCount = 0;
        int min2 = 0;
        int n = b0;
        int other$iv = 128;
        boolean $i$f$and = false;
        if (($this$and$iv & other$iv) == 0) {
            $this$and$iv = b0;
            other$iv = 127;
            $i$f$and = false;
            codePoint = $this$and$iv & other$iv;
            byteCount = 1;
            min2 = 0;
        } else {
            $this$and$iv = b0;
            other$iv = 224;
            $i$f$and = false;
            if (($this$and$iv & other$iv) == 192) {
                $this$and$iv = b0;
                other$iv = 31;
                $i$f$and = false;
                codePoint = $this$and$iv & other$iv;
                byteCount = 2;
                min2 = 128;
            } else {
                $this$and$iv = b0;
                other$iv = 240;
                $i$f$and = false;
                if (($this$and$iv & other$iv) == 224) {
                    $this$and$iv = b0;
                    other$iv = 15;
                    $i$f$and = false;
                    codePoint = $this$and$iv & other$iv;
                    byteCount = 3;
                    min2 = 2048;
                } else {
                    $this$and$iv = b0;
                    other$iv = 248;
                    $i$f$and = false;
                    if (($this$and$iv & other$iv) == 240) {
                        $this$and$iv = b0;
                        other$iv = 7;
                        $i$f$and = false;
                        codePoint = $this$and$iv & other$iv;
                        byteCount = 4;
                        min2 = 65536;
                    } else {
                        $this$commonReadUtf8CodePoint.skip(1L);
                        return 65533;
                    }
                }
            }
        }
        if ($this$commonReadUtf8CodePoint.size() < (long)byteCount) {
            throw (Throwable)new EOFException("size < " + byteCount + ": " + $this$commonReadUtf8CodePoint.size() + " (to read code point prefixed 0x" + -Util.toHexString((byte)b0) + ')');
        }
        $this$and$iv = 1;
        int n2 = byteCount;
        while ($this$and$iv < n2) {
            byte $this$and$iv2;
            void i;
            byte b;
            byte by = b = $this$commonReadUtf8CodePoint.getByte((long)i);
            int other$iv2 = 192;
            boolean $i$f$and2 = false;
            if (($this$and$iv2 & other$iv2) == 128) {
                codePoint <<= 6;
                $this$and$iv2 = b;
                other$iv2 = 63;
                $i$f$and2 = false;
                codePoint |= $this$and$iv2 & other$iv2;
            } else {
                $this$commonReadUtf8CodePoint.skip((long)i);
                return 65533;
            }
            ++i;
        }
        $this$commonReadUtf8CodePoint.skip(byteCount);
        return codePoint > 0x10FFFF ? 65533 : (55296 <= (n = codePoint) && 57343 >= n ? 65533 : (codePoint < min2 ? 65533 : codePoint));
    }

    @NotNull
    public static final Buffer commonWriteUtf8(@NotNull Buffer $this$commonWriteUtf8, @NotNull String string, int beginIndex, int endIndex) {
        int $i$f$commonWriteUtf8 = 0;
        Intrinsics.checkNotNullParameter($this$commonWriteUtf8, "$this$commonWriteUtf8");
        Intrinsics.checkNotNullParameter(string, "string");
        boolean bl = beginIndex >= 0;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string2 = "beginIndex < 0: " + beginIndex;
            throw (Throwable)new IllegalArgumentException(string2.toString());
        }
        bl = endIndex >= beginIndex;
        bl2 = false;
        bl3 = false;
        if (!bl) {
            boolean bl5 = false;
            String string3 = "endIndex < beginIndex: " + endIndex + " < " + beginIndex;
            throw (Throwable)new IllegalArgumentException(string3.toString());
        }
        bl = endIndex <= string.length();
        bl2 = false;
        bl3 = false;
        if (!bl) {
            boolean bl6 = false;
            String string4 = "endIndex > string.length: " + endIndex + " > " + string.length();
            throw (Throwable)new IllegalArgumentException(string4.toString());
        }
        int i = beginIndex;
        while (i < endIndex) {
            char data2;
            char low;
            char c = string.charAt(i);
            if (c < '\u0080') {
                Segment tail = $this$commonWriteUtf8.writableSegment$okio(1);
                byte[] data2 = tail.data;
                int segmentOffset = tail.limit - i;
                int n = 8192 - segmentOffset;
                boolean bl7 = false;
                int runLimit = Math.min(endIndex, n);
                data2[segmentOffset + i++] = (byte)c;
                while (i < runLimit && (c = string.charAt(i)) < '\u0080') {
                    data2[segmentOffset + i++] = (byte)c;
                }
                int runSize = i + segmentOffset - tail.limit;
                tail.limit += runSize;
                Buffer buffer = $this$commonWriteUtf8;
                buffer.setSize$okio(buffer.size() + (long)runSize);
                continue;
            }
            if (c < '\u0800') {
                Segment tail = $this$commonWriteUtf8.writableSegment$okio(2);
                tail.data[tail.limit] = (byte)(c >> 6 | 0xC0);
                tail.data[tail.limit + 1] = (byte)(c & 0x3F | 0x80);
                tail.limit += 2;
                Buffer buffer = $this$commonWriteUtf8;
                buffer.setSize$okio(buffer.size() + 2L);
                ++i;
                continue;
            }
            if (c < '\ud800' || c > '\udfff') {
                Segment tail = $this$commonWriteUtf8.writableSegment$okio(3);
                tail.data[tail.limit] = (byte)(c >> 12 | 0xE0);
                tail.data[tail.limit + 1] = (byte)(c >> 6 & 0x3F | 0x80);
                tail.data[tail.limit + 2] = (byte)(c & 0x3F | 0x80);
                tail.limit += 3;
                Buffer buffer = $this$commonWriteUtf8;
                buffer.setSize$okio(buffer.size() + 3L);
                ++i;
                continue;
            }
            char c2 = low = i + 1 < endIndex ? string.charAt(i + 1) : (char)'\u0000';
            if (c > '\udbff' || '\udc00' > (data2 = low) || '\udfff' < data2) {
                $this$commonWriteUtf8.writeByte(63);
                ++i;
                continue;
            }
            int codePoint = 65536 + ((c & 0x3FF) << 10 | low & 0x3FF);
            Segment tail = $this$commonWriteUtf8.writableSegment$okio(4);
            tail.data[tail.limit] = (byte)(codePoint >> 18 | 0xF0);
            tail.data[tail.limit + 1] = (byte)(codePoint >> 12 & 0x3F | 0x80);
            tail.data[tail.limit + 2] = (byte)(codePoint >> 6 & 0x3F | 0x80);
            tail.data[tail.limit + 3] = (byte)(codePoint & 0x3F | 0x80);
            tail.limit += 4;
            Buffer buffer = $this$commonWriteUtf8;
            buffer.setSize$okio(buffer.size() + 4L);
            i += 2;
        }
        return $this$commonWriteUtf8;
    }

    @NotNull
    public static final Buffer commonWriteUtf8CodePoint(@NotNull Buffer $this$commonWriteUtf8CodePoint, int codePoint) {
        int $i$f$commonWriteUtf8CodePoint = 0;
        Intrinsics.checkNotNullParameter($this$commonWriteUtf8CodePoint, "$this$commonWriteUtf8CodePoint");
        if (codePoint < 128) {
            $this$commonWriteUtf8CodePoint.writeByte(codePoint);
        } else if (codePoint < 2048) {
            Segment tail = $this$commonWriteUtf8CodePoint.writableSegment$okio(2);
            tail.data[tail.limit] = (byte)(codePoint >> 6 | 0xC0);
            tail.data[tail.limit + 1] = (byte)(codePoint & 0x3F | 0x80);
            tail.limit += 2;
            Buffer buffer = $this$commonWriteUtf8CodePoint;
            buffer.setSize$okio(buffer.size() + 2L);
        } else {
            int tail = codePoint;
            if (55296 <= tail && 57343 >= tail) {
                $this$commonWriteUtf8CodePoint.writeByte(63);
            } else if (codePoint < 65536) {
                Segment tail2 = $this$commonWriteUtf8CodePoint.writableSegment$okio(3);
                tail2.data[tail2.limit] = (byte)(codePoint >> 12 | 0xE0);
                tail2.data[tail2.limit + 1] = (byte)(codePoint >> 6 & 0x3F | 0x80);
                tail2.data[tail2.limit + 2] = (byte)(codePoint & 0x3F | 0x80);
                tail2.limit += 3;
                Buffer buffer = $this$commonWriteUtf8CodePoint;
                buffer.setSize$okio(buffer.size() + 3L);
            } else if (codePoint <= 0x10FFFF) {
                Segment tail3 = $this$commonWriteUtf8CodePoint.writableSegment$okio(4);
                tail3.data[tail3.limit] = (byte)(codePoint >> 18 | 0xF0);
                tail3.data[tail3.limit + 1] = (byte)(codePoint >> 12 & 0x3F | 0x80);
                tail3.data[tail3.limit + 2] = (byte)(codePoint >> 6 & 0x3F | 0x80);
                tail3.data[tail3.limit + 3] = (byte)(codePoint & 0x3F | 0x80);
                tail3.limit += 4;
                Buffer buffer = $this$commonWriteUtf8CodePoint;
                buffer.setSize$okio(buffer.size() + 4L);
            } else {
                throw (Throwable)new IllegalArgumentException("Unexpected code point: 0x" + -Util.toHexString(codePoint));
            }
        }
        return $this$commonWriteUtf8CodePoint;
    }

    public static final long commonWriteAll(@NotNull Buffer $this$commonWriteAll, @NotNull Source source2) {
        long readCount;
        int $i$f$commonWriteAll = 0;
        Intrinsics.checkNotNullParameter($this$commonWriteAll, "$this$commonWriteAll");
        Intrinsics.checkNotNullParameter(source2, "source");
        long totalBytesRead = 0L;
        while ((readCount = source2.read($this$commonWriteAll, 8192)) != -1L) {
            totalBytesRead += readCount;
        }
        return totalBytesRead;
    }

    @NotNull
    public static final Buffer commonWrite(@NotNull Buffer $this$commonWrite, @NotNull Source source2, long byteCount) {
        long read;
        int $i$f$commonWrite = 0;
        Intrinsics.checkNotNullParameter($this$commonWrite, "$this$commonWrite");
        Intrinsics.checkNotNullParameter(source2, "source");
        for (long byteCount2 = byteCount; byteCount2 > 0L; byteCount2 -= read) {
            read = source2.read($this$commonWrite, byteCount2);
            if (read != -1L) continue;
            throw (Throwable)new EOFException();
        }
        return $this$commonWrite;
    }

    @NotNull
    public static final Buffer commonWriteByte(@NotNull Buffer $this$commonWriteByte, int b) {
        int $i$f$commonWriteByte = 0;
        Intrinsics.checkNotNullParameter($this$commonWriteByte, "$this$commonWriteByte");
        Segment tail = $this$commonWriteByte.writableSegment$okio(1);
        int n = tail.limit;
        tail.limit = n + 1;
        tail.data[n] = (byte)b;
        Buffer buffer = $this$commonWriteByte;
        buffer.setSize$okio(buffer.size() + 1L);
        return $this$commonWriteByte;
    }

    @NotNull
    public static final Buffer commonWriteShort(@NotNull Buffer $this$commonWriteShort, int s2) {
        int $i$f$commonWriteShort = 0;
        Intrinsics.checkNotNullParameter($this$commonWriteShort, "$this$commonWriteShort");
        Segment tail = $this$commonWriteShort.writableSegment$okio(2);
        byte[] data = tail.data;
        int limit = tail.limit;
        data[limit++] = (byte)(s2 >>> 8 & 0xFF);
        data[limit++] = (byte)(s2 & 0xFF);
        tail.limit = limit;
        Buffer buffer = $this$commonWriteShort;
        buffer.setSize$okio(buffer.size() + 2L);
        return $this$commonWriteShort;
    }

    @NotNull
    public static final Buffer commonWriteInt(@NotNull Buffer $this$commonWriteInt, int i) {
        int $i$f$commonWriteInt = 0;
        Intrinsics.checkNotNullParameter($this$commonWriteInt, "$this$commonWriteInt");
        Segment tail = $this$commonWriteInt.writableSegment$okio(4);
        byte[] data = tail.data;
        int limit = tail.limit;
        data[limit++] = (byte)(i >>> 24 & 0xFF);
        data[limit++] = (byte)(i >>> 16 & 0xFF);
        data[limit++] = (byte)(i >>> 8 & 0xFF);
        data[limit++] = (byte)(i & 0xFF);
        tail.limit = limit;
        Buffer buffer = $this$commonWriteInt;
        buffer.setSize$okio(buffer.size() + 4L);
        return $this$commonWriteInt;
    }

    @NotNull
    public static final Buffer commonWriteLong(@NotNull Buffer $this$commonWriteLong, long v) {
        int $i$f$commonWriteLong = 0;
        Intrinsics.checkNotNullParameter($this$commonWriteLong, "$this$commonWriteLong");
        Segment tail = $this$commonWriteLong.writableSegment$okio(8);
        byte[] data = tail.data;
        int limit = tail.limit;
        data[limit++] = (byte)(v >>> 56 & 0xFFL);
        data[limit++] = (byte)(v >>> 48 & 0xFFL);
        data[limit++] = (byte)(v >>> 40 & 0xFFL);
        data[limit++] = (byte)(v >>> 32 & 0xFFL);
        data[limit++] = (byte)(v >>> 24 & 0xFFL);
        data[limit++] = (byte)(v >>> 16 & 0xFFL);
        data[limit++] = (byte)(v >>> 8 & 0xFFL);
        data[limit++] = (byte)(v & 0xFFL);
        tail.limit = limit;
        Buffer buffer = $this$commonWriteLong;
        buffer.setSize$okio(buffer.size() + 8L);
        return $this$commonWriteLong;
    }

    public static final void commonWrite(@NotNull Buffer $this$commonWrite, @NotNull Buffer source2, long byteCount) {
        long movedByteCount;
        long byteCount2;
        int $i$f$commonWrite = 0;
        Intrinsics.checkNotNullParameter($this$commonWrite, "$this$commonWrite");
        Intrinsics.checkNotNullParameter(source2, "source");
        boolean bl = source2 != $this$commonWrite;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "source == this";
            throw (Throwable)new IllegalArgumentException(string.toString());
        }
        -Util.checkOffsetAndCount(source2.size(), 0L, byteCount2);
        for (byteCount2 = byteCount; byteCount2 > 0L; byteCount2 -= movedByteCount) {
            Segment segmentToMove;
            Segment segment = source2.head;
            Intrinsics.checkNotNull(segment);
            int n = segment.limit;
            Segment segment2 = source2.head;
            Intrinsics.checkNotNull(segment2);
            if (byteCount2 < (long)(n - segment2.pos)) {
                Segment tail;
                Segment segment3;
                if ($this$commonWrite.head != null) {
                    Segment segment4 = $this$commonWrite.head;
                    Intrinsics.checkNotNull(segment4);
                    segment3 = segment4.prev;
                } else {
                    segment3 = tail = null;
                }
                if (tail != null && tail.owner && byteCount2 + (long)tail.limit - (long)(tail.shared ? 0 : tail.pos) <= (long)8192) {
                    Segment segment5 = source2.head;
                    Intrinsics.checkNotNull(segment5);
                    segment5.writeTo(tail, (int)byteCount2);
                    Buffer buffer = source2;
                    buffer.setSize$okio(buffer.size() - byteCount2);
                    Buffer buffer2 = $this$commonWrite;
                    buffer2.setSize$okio(buffer2.size() + byteCount2);
                    return;
                }
                Segment segment6 = source2.head;
                Intrinsics.checkNotNull(segment6);
                source2.head = segment6.split((int)byteCount2);
            }
            Segment segment7 = segmentToMove = source2.head;
            Intrinsics.checkNotNull(segment7);
            movedByteCount = segment7.limit - segmentToMove.pos;
            source2.head = segmentToMove.pop();
            if ($this$commonWrite.head == null) {
                $this$commonWrite.head = segmentToMove;
                segmentToMove.next = segmentToMove.prev = segmentToMove;
            } else {
                Segment tail;
                Segment segment8 = $this$commonWrite.head;
                Intrinsics.checkNotNull(segment8);
                Segment segment9 = tail = segment8.prev;
                Intrinsics.checkNotNull(segment9);
                tail = segment9.push(segmentToMove);
                tail.compact();
            }
            Buffer buffer = source2;
            buffer.setSize$okio(buffer.size() - movedByteCount);
            Buffer buffer3 = $this$commonWrite;
            buffer3.setSize$okio(buffer3.size() + movedByteCount);
        }
    }

    public static final long commonRead(@NotNull Buffer $this$commonRead, @NotNull Buffer sink2, long byteCount) {
        int $i$f$commonRead = 0;
        Intrinsics.checkNotNullParameter($this$commonRead, "$this$commonRead");
        Intrinsics.checkNotNullParameter(sink2, "sink");
        long byteCount2 = byteCount;
        boolean bl = byteCount2 >= 0L;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "byteCount < 0: " + byteCount2;
            throw (Throwable)new IllegalArgumentException(string.toString());
        }
        if ($this$commonRead.size() == 0L) {
            return -1L;
        }
        if (byteCount2 > $this$commonRead.size()) {
            byteCount2 = $this$commonRead.size();
        }
        sink2.write($this$commonRead, byteCount2);
        return byteCount2;
    }

    /*
     * WARNING - void declaration
     */
    public static final long commonIndexOf(@NotNull Buffer $this$commonIndexOf, byte b, long fromIndex, long toIndex) {
        void offset;
        long nextOffset$iv;
        void $this$seek$iv;
        long toIndex2;
        int $i$f$commonIndexOf = 0;
        Intrinsics.checkNotNullParameter($this$commonIndexOf, "$this$commonIndexOf");
        long fromIndex2 = fromIndex;
        long l = fromIndex2;
        boolean bl = 0L <= l && (toIndex2 = toIndex) >= l;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "size=" + $this$commonIndexOf.size() + " fromIndex=" + fromIndex2 + " toIndex=" + toIndex2;
            throw (Throwable)new IllegalArgumentException(string.toString());
        }
        if (toIndex2 > $this$commonIndexOf.size()) {
            toIndex2 = $this$commonIndexOf.size();
        }
        if (fromIndex2 == toIndex2) {
            return -1L;
        }
        Buffer buffer = $this$commonIndexOf;
        long fromIndex$iv = fromIndex2;
        boolean $i$f$seek = false;
        Segment segment = $this$seek$iv.head;
        if (segment == null) {
            long l2 = -1L;
            Segment s2 = null;
            boolean bl5 = false;
            return -1L;
        }
        Segment s$iv = segment;
        if ($this$seek$iv.size() - fromIndex$iv < fromIndex$iv) {
            long offset$iv;
            for (offset$iv = $this$seek$iv.size(); offset$iv > fromIndex$iv; offset$iv -= (long)(s$iv.limit - s$iv.pos)) {
                Intrinsics.checkNotNull(s$iv.prev);
            }
            long offset2 = offset$iv;
            Segment s3 = s$iv;
            boolean bl6 = false;
            Segment segment2 = s3;
            if (segment2 == null) {
                return -1L;
            }
            Segment s4 = segment2;
            long offset3 = offset2;
            while (offset3 < toIndex2) {
                byte[] data = s4.data;
                long l3 = s4.limit;
                long l4 = (long)s4.pos + toIndex2 - offset3;
                boolean bl7 = false;
                int limit = (int)Math.min(l3, l4);
                for (int pos = (int)((long)s4.pos + fromIndex2 - offset3); pos < limit; ++pos) {
                    if (data[pos] != b) continue;
                    return (long)(pos - s4.pos) + offset3;
                }
                fromIndex2 = offset3 += (long)(s4.limit - s4.pos);
                Intrinsics.checkNotNull(s4.next);
            }
            return -1L;
        }
        long offset$iv = 0L;
        while ((nextOffset$iv = offset$iv + (long)(s$iv.limit - s$iv.pos)) <= fromIndex$iv) {
            Intrinsics.checkNotNull(s$iv.next);
            offset$iv = nextOffset$iv;
        }
        long l5 = offset$iv;
        Segment s5 = s$iv;
        boolean bl8 = false;
        Segment segment3 = s5;
        if (segment3 == null) {
            return -1L;
        }
        Segment s6 = segment3;
        void offset4 = offset;
        while (offset4 < toIndex2) {
            byte[] data = s6.data;
            long l6 = s6.limit;
            long l7 = (long)s6.pos + toIndex2 - offset4;
            boolean bl9 = false;
            int limit = (int)Math.min(l6, l7);
            for (int pos = (int)((long)s6.pos + fromIndex2 - offset4); pos < limit; ++pos) {
                if (data[pos] != b) continue;
                return (long)(pos - s6.pos) + offset4;
            }
            fromIndex2 = offset4 += (long)(s6.limit - s6.pos);
            Intrinsics.checkNotNull(s6.next);
        }
        return -1L;
    }

    /*
     * WARNING - void declaration
     */
    public static final long commonIndexOf(@NotNull Buffer $this$commonIndexOf, @NotNull ByteString bytes, long fromIndex) {
        void offset;
        long nextOffset$iv;
        void $this$seek$iv;
        int $i$f$commonIndexOf = 0;
        Intrinsics.checkNotNullParameter($this$commonIndexOf, "$this$commonIndexOf");
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        long fromIndex2 = fromIndex;
        boolean bl = bytes.size() > 0;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "bytes is empty";
            throw (Throwable)new IllegalArgumentException(string.toString());
        }
        bl = fromIndex2 >= 0L;
        bl2 = false;
        bl3 = false;
        if (!bl) {
            boolean bl5 = false;
            String string = "fromIndex < 0: " + fromIndex2;
            throw (Throwable)new IllegalArgumentException(string.toString());
        }
        Buffer buffer = $this$commonIndexOf;
        long fromIndex$iv = fromIndex2;
        boolean $i$f$seek = false;
        Segment segment = $this$seek$iv.head;
        if (segment == null) {
            long l = -1L;
            Segment s2 = null;
            boolean bl6 = false;
            return -1L;
        }
        Segment s$iv = segment;
        if ($this$seek$iv.size() - fromIndex$iv < fromIndex$iv) {
            long offset$iv;
            for (offset$iv = $this$seek$iv.size(); offset$iv > fromIndex$iv; offset$iv -= (long)(s$iv.limit - s$iv.pos)) {
                Intrinsics.checkNotNull(s$iv.prev);
            }
            long offset2 = offset$iv;
            Segment s3 = s$iv;
            boolean bl7 = false;
            Segment segment2 = s3;
            if (segment2 == null) {
                return -1L;
            }
            Segment s4 = segment2;
            long offset3 = offset2;
            byte[] targetByteArray = bytes.internalArray$okio();
            byte b0 = targetByteArray[0];
            int bytesSize = bytes.size();
            long resultLimit = $this$commonIndexOf.size() - (long)bytesSize + 1L;
            while (offset3 < resultLimit) {
                int a$iv;
                byte[] data = s4.data;
                int n = s4.limit;
                long b$iv = (long)s4.pos + resultLimit - offset3;
                boolean $i$f$minOf = false;
                long l = a$iv;
                boolean bl8 = false;
                int segmentLimit = (int)Math.min(l, b$iv);
                a$iv = (int)((long)s4.pos + fromIndex2 - offset3);
                int n2 = segmentLimit;
                while (a$iv < n2) {
                    void pos;
                    if (data[pos] == b0 && BufferKt.rangeEquals(s4, (int)(pos + true), targetByteArray, 1, bytesSize)) {
                        return (long)(pos - s4.pos) + offset3;
                    }
                    ++pos;
                }
                fromIndex2 = offset3 += (long)(s4.limit - s4.pos);
                Intrinsics.checkNotNull(s4.next);
            }
            return -1L;
        }
        long offset$iv = 0L;
        while ((nextOffset$iv = offset$iv + (long)(s$iv.limit - s$iv.pos)) <= fromIndex$iv) {
            Intrinsics.checkNotNull(s$iv.next);
            offset$iv = nextOffset$iv;
        }
        long l = offset$iv;
        Segment s5 = s$iv;
        boolean bl9 = false;
        Segment segment3 = s5;
        if (segment3 == null) {
            return -1L;
        }
        Segment s6 = segment3;
        void offset4 = offset;
        byte[] targetByteArray = bytes.internalArray$okio();
        byte b0 = targetByteArray[0];
        int bytesSize = bytes.size();
        long resultLimit = $this$commonIndexOf.size() - (long)bytesSize + 1L;
        while (offset4 < resultLimit) {
            int a$iv;
            byte[] data = s6.data;
            int n = s6.limit;
            long b$iv = (long)s6.pos + resultLimit - offset4;
            boolean $i$f$minOf = false;
            long l2 = a$iv;
            boolean bl10 = false;
            int segmentLimit = (int)Math.min(l2, b$iv);
            a$iv = (int)((long)s6.pos + fromIndex2 - offset4);
            int n3 = segmentLimit;
            while (a$iv < n3) {
                void pos;
                if (data[pos] == b0 && BufferKt.rangeEquals(s6, (int)(pos + true), targetByteArray, 1, bytesSize)) {
                    return (long)(pos - s6.pos) + offset4;
                }
                ++pos;
            }
            fromIndex2 = offset4 += (long)(s6.limit - s6.pos);
            Intrinsics.checkNotNull(s6.next);
        }
        return -1L;
    }

    /*
     * WARNING - void declaration
     */
    public static final long commonIndexOfElement(@NotNull Buffer $this$commonIndexOfElement, @NotNull ByteString targetBytes, long fromIndex) {
        void offset;
        long nextOffset$iv;
        void $this$seek$iv;
        int $i$f$commonIndexOfElement = 0;
        Intrinsics.checkNotNullParameter($this$commonIndexOfElement, "$this$commonIndexOfElement");
        Intrinsics.checkNotNullParameter(targetBytes, "targetBytes");
        long fromIndex2 = fromIndex;
        boolean bl = fromIndex2 >= 0L;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "fromIndex < 0: " + fromIndex2;
            throw (Throwable)new IllegalArgumentException(string.toString());
        }
        Buffer buffer = $this$commonIndexOfElement;
        long fromIndex$iv = fromIndex2;
        boolean $i$f$seek = false;
        Segment segment = $this$seek$iv.head;
        if (segment == null) {
            long l = -1L;
            Segment s2 = null;
            boolean bl5 = false;
            return -1L;
        }
        Segment s$iv = segment;
        if ($this$seek$iv.size() - fromIndex$iv < fromIndex$iv) {
            long offset$iv;
            for (offset$iv = $this$seek$iv.size(); offset$iv > fromIndex$iv; offset$iv -= (long)(s$iv.limit - s$iv.pos)) {
                Intrinsics.checkNotNull(s$iv.prev);
            }
            long offset2 = offset$iv;
            Segment s3 = s$iv;
            boolean bl6 = false;
            Segment segment2 = s3;
            if (segment2 == null) {
                return -1L;
            }
            Segment s4 = segment2;
            long offset3 = offset2;
            if (targetBytes.size() == 2) {
                byte b0 = targetBytes.getByte(0);
                byte b1 = targetBytes.getByte(1);
                while (offset3 < $this$commonIndexOfElement.size()) {
                    byte[] data = s4.data;
                    int limit = s4.limit;
                    for (int pos = (int)((long)s4.pos + fromIndex2 - offset3); pos < limit; ++pos) {
                        byte b = data[pos];
                        if (b != b0 && b != b1) continue;
                        return (long)(pos - s4.pos) + offset3;
                    }
                    fromIndex2 = offset3 += (long)(s4.limit - s4.pos);
                    Intrinsics.checkNotNull(s4.next);
                }
            } else {
                byte[] targetByteArray = targetBytes.internalArray$okio();
                while (offset3 < $this$commonIndexOfElement.size()) {
                    byte[] data = s4.data;
                    int limit = s4.limit;
                    for (int pos = (int)((long)s4.pos + fromIndex2 - offset3); pos < limit; ++pos) {
                        byte b = data[pos];
                        for (byte t : targetByteArray) {
                            if (b != t) continue;
                            return (long)(pos - s4.pos) + offset3;
                        }
                    }
                    fromIndex2 = offset3 += (long)(s4.limit - s4.pos);
                    Intrinsics.checkNotNull(s4.next);
                }
            }
            return -1L;
        }
        long offset$iv = 0L;
        while ((nextOffset$iv = offset$iv + (long)(s$iv.limit - s$iv.pos)) <= fromIndex$iv) {
            Intrinsics.checkNotNull(s$iv.next);
            offset$iv = nextOffset$iv;
        }
        long l = offset$iv;
        Segment s5 = s$iv;
        boolean bl7 = false;
        Segment segment3 = s5;
        if (segment3 == null) {
            return -1L;
        }
        Segment s6 = segment3;
        void offset4 = offset;
        if (targetBytes.size() == 2) {
            byte b0 = targetBytes.getByte(0);
            byte b1 = targetBytes.getByte(1);
            while (offset4 < $this$commonIndexOfElement.size()) {
                byte[] data = s6.data;
                int limit = s6.limit;
                for (int pos = (int)((long)s6.pos + fromIndex2 - offset4); pos < limit; ++pos) {
                    byte b = data[pos];
                    if (b != b0 && b != b1) continue;
                    return (long)(pos - s6.pos) + offset4;
                }
                fromIndex2 = offset4 += (long)(s6.limit - s6.pos);
                Intrinsics.checkNotNull(s6.next);
            }
        } else {
            byte[] targetByteArray = targetBytes.internalArray$okio();
            while (offset4 < $this$commonIndexOfElement.size()) {
                byte[] data = s6.data;
                int limit = s6.limit;
                for (int pos = (int)((long)s6.pos + fromIndex2 - offset4); pos < limit; ++pos) {
                    byte b = data[pos];
                    for (byte t : targetByteArray) {
                        if (b != t) continue;
                        return (long)(pos - s6.pos) + offset4;
                    }
                }
                fromIndex2 = offset4 += (long)(s6.limit - s6.pos);
                Intrinsics.checkNotNull(s6.next);
            }
        }
        return -1L;
    }

    /*
     * WARNING - void declaration
     */
    public static final boolean commonRangeEquals(@NotNull Buffer $this$commonRangeEquals, long offset, @NotNull ByteString bytes, int bytesOffset, int byteCount) {
        int $i$f$commonRangeEquals = 0;
        Intrinsics.checkNotNullParameter($this$commonRangeEquals, "$this$commonRangeEquals");
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        if (offset < 0L || bytesOffset < 0 || byteCount < 0 || $this$commonRangeEquals.size() - offset < (long)byteCount || bytes.size() - bytesOffset < byteCount) {
            return false;
        }
        int n = 0;
        int n2 = byteCount;
        while (n < n2) {
            void i;
            if ($this$commonRangeEquals.getByte(offset + (long)i) != bytes.getByte(bytesOffset + i)) {
                return false;
            }
            ++i;
        }
        return true;
    }

    /*
     * WARNING - void declaration
     */
    public static final boolean commonEquals(@NotNull Buffer $this$commonEquals, @Nullable Object other) {
        int $i$f$commonEquals = 0;
        Intrinsics.checkNotNullParameter($this$commonEquals, "$this$commonEquals");
        if ($this$commonEquals == other) {
            return true;
        }
        if (!(other instanceof Buffer)) {
            return false;
        }
        if ($this$commonEquals.size() != ((Buffer)other).size()) {
            return false;
        }
        if ($this$commonEquals.size() == 0L) {
            return true;
        }
        Segment segment = $this$commonEquals.head;
        Intrinsics.checkNotNull(segment);
        Segment sa = segment;
        Segment segment2 = ((Buffer)other).head;
        Intrinsics.checkNotNull(segment2);
        Segment sb = segment2;
        int posA = sa.pos;
        int posB = sb.pos;
        long count = 0L;
        for (long pos = 0L; pos < $this$commonEquals.size(); pos += count) {
            int n = sa.limit - posA;
            int n2 = sb.limit - posB;
            boolean bl = false;
            count = Math.min(n, n2);
            long l = 0L;
            long l2 = count;
            while (l < l2) {
                void i;
                if (sa.data[posA++] != sb.data[posB++]) {
                    return false;
                }
                ++i;
            }
            if (posA == sa.limit) {
                Intrinsics.checkNotNull(sa.next);
                posA = sa.pos;
            }
            if (posB != sb.limit) continue;
            Intrinsics.checkNotNull(sb.next);
            posB = sb.pos;
        }
        return true;
    }

    public static final int commonHashCode(@NotNull Buffer $this$commonHashCode) {
        int $i$f$commonHashCode = 0;
        Intrinsics.checkNotNullParameter($this$commonHashCode, "$this$commonHashCode");
        Segment segment = $this$commonHashCode.head;
        if (segment == null) {
            return 0;
        }
        Segment s2 = segment;
        int result = 1;
        do {
            int limit = s2.limit;
            for (int pos = s2.pos; pos < limit; ++pos) {
                result = 31 * result + s2.data[pos];
            }
            Intrinsics.checkNotNull(s2.next);
        } while (s2 != $this$commonHashCode.head);
        return result;
    }

    @NotNull
    public static final Buffer commonCopy(@NotNull Buffer $this$commonCopy) {
        Segment headCopy;
        int $i$f$commonCopy = 0;
        Intrinsics.checkNotNullParameter($this$commonCopy, "$this$commonCopy");
        Buffer result = new Buffer();
        if ($this$commonCopy.size() == 0L) {
            return result;
        }
        Segment segment = $this$commonCopy.head;
        Intrinsics.checkNotNull(segment);
        Segment head = segment;
        headCopy.next = headCopy.prev = (result.head = (headCopy = head.sharedCopy()));
        Segment s2 = head.next;
        while (s2 != head) {
            Segment segment2 = headCopy.prev;
            Intrinsics.checkNotNull(segment2);
            Segment segment3 = s2;
            Intrinsics.checkNotNull(segment3);
            segment2.push(segment3.sharedCopy());
            s2 = s2.next;
        }
        result.setSize$okio($this$commonCopy.size());
        return result;
    }

    @NotNull
    public static final ByteString commonSnapshot(@NotNull Buffer $this$commonSnapshot) {
        int $i$f$commonSnapshot = 0;
        Intrinsics.checkNotNullParameter($this$commonSnapshot, "$this$commonSnapshot");
        boolean bl = $this$commonSnapshot.size() <= (long)Integer.MAX_VALUE;
        boolean bl2 = false;
        boolean bl3 = false;
        if (!bl) {
            boolean bl4 = false;
            String string = "size > Int.MAX_VALUE: " + $this$commonSnapshot.size();
            throw (Throwable)new IllegalStateException(string.toString());
        }
        return $this$commonSnapshot.snapshot((int)$this$commonSnapshot.size());
    }

    @NotNull
    public static final ByteString commonSnapshot(@NotNull Buffer $this$commonSnapshot, int byteCount) {
        int $i$f$commonSnapshot = 0;
        Intrinsics.checkNotNullParameter($this$commonSnapshot, "$this$commonSnapshot");
        if (byteCount == 0) {
            return ByteString.EMPTY;
        }
        -Util.checkOffsetAndCount($this$commonSnapshot.size(), 0L, byteCount);
        int offset = 0;
        int segmentCount = 0;
        Segment s2 = $this$commonSnapshot.head;
        while (offset < byteCount) {
            Segment segment = s2;
            Intrinsics.checkNotNull(segment);
            if (segment.limit == s2.pos) {
                throw (Throwable)((Object)new AssertionError((Object)"s.limit == s.pos"));
            }
            offset += s2.limit - s2.pos;
            ++segmentCount;
            s2 = s2.next;
        }
        byte[][] segments = new byte[segmentCount][];
        int[] directory = new int[segmentCount * 2];
        offset = 0;
        segmentCount = 0;
        s2 = $this$commonSnapshot.head;
        while (offset < byteCount) {
            Segment segment = s2;
            Intrinsics.checkNotNull(segment);
            segments[segmentCount] = segment.data;
            boolean bl = false;
            directory[segmentCount] = Math.min(offset += s2.limit - s2.pos, byteCount);
            directory[segmentCount + ((Object[])segments).length] = s2.pos;
            s2.shared = true;
            ++segmentCount;
            s2 = s2.next;
        }
        return new SegmentedByteString(segments, directory);
    }
}

