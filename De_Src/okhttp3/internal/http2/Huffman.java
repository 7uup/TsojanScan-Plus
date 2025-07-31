/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okhttp3.internal.http2;

import java.io.IOException;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.Util;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u00c6\u0002\u0018\u00002\u00020\u0001:\u0001\u001aB\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J \u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\fH\u0002J\u001e\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015J\u0016\u0010\u0016\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u00172\u0006\u0010\u0014\u001a\u00020\u0015J\u000e\u0010\u0018\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\u0017R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001b"}, d2={"Lokhttp3/internal/http2/Huffman;", "", "()V", "CODES", "", "CODE_BIT_COUNTS", "", "root", "Lokhttp3/internal/http2/Huffman$Node;", "addCode", "", "symbol", "", "code", "codeBitCount", "decode", "source", "Lokio/BufferedSource;", "byteCount", "", "sink", "Lokio/BufferedSink;", "encode", "Lokio/ByteString;", "encodedLength", "bytes", "Node", "okhttp"})
public final class Huffman {
    private static final int[] CODES;
    private static final byte[] CODE_BIT_COUNTS;
    private static final Node root;
    public static final Huffman INSTANCE;

    /*
     * WARNING - void declaration
     */
    public final void encode(@NotNull ByteString source2, @NotNull BufferedSink sink2) throws IOException {
        Intrinsics.checkNotNullParameter(source2, "source");
        Intrinsics.checkNotNullParameter(sink2, "sink");
        long accumulator = 0L;
        int accumulatorBitCount = 0;
        int n = 0;
        int n2 = source2.size();
        while (n < n2) {
            void i;
            int symbol = Util.and(source2.getByte((int)i), 255);
            int code = CODES[symbol];
            byte codeBitCount = CODE_BIT_COUNTS[symbol];
            accumulator = accumulator << codeBitCount | (long)code;
            accumulatorBitCount += codeBitCount;
            while (accumulatorBitCount >= 8) {
                sink2.writeByte((int)(accumulator >> (accumulatorBitCount -= 8)));
            }
            ++i;
        }
        if (accumulatorBitCount > 0) {
            accumulator <<= 8 - accumulatorBitCount;
            sink2.writeByte((int)(accumulator |= 255L >>> accumulatorBitCount));
        }
    }

    /*
     * WARNING - void declaration
     */
    public final int encodedLength(@NotNull ByteString bytes) {
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        long bitCount = 0L;
        int n = 0;
        int n2 = bytes.size();
        while (n < n2) {
            void i;
            int byteIn = Util.and(bytes.getByte((int)i), 255);
            bitCount += (long)CODE_BIT_COUNTS[byteIn];
            ++i;
        }
        return (int)(bitCount + (long)7 >> 3);
    }

    /*
     * WARNING - void declaration
     */
    public final void decode(@NotNull BufferedSource source2, long byteCount, @NotNull BufferedSink sink2) {
        Intrinsics.checkNotNullParameter(source2, "source");
        Intrinsics.checkNotNullParameter(sink2, "sink");
        Node node = root;
        int accumulator = 0;
        int accumulatorBitCount = 0;
        long l = 0L;
        long l2 = byteCount;
        while (l < l2) {
            void i;
            int byteIn = Util.and(source2.readByte(), 255);
            accumulator = accumulator << 8 | byteIn;
            accumulatorBitCount += 8;
            while (accumulatorBitCount >= 8) {
                int childIndex = accumulator >>> accumulatorBitCount - 8 & 0xFF;
                Node[] nodeArray = node.getChildren();
                Intrinsics.checkNotNull(nodeArray);
                Intrinsics.checkNotNull(nodeArray[childIndex]);
                if (node.getChildren() == null) {
                    sink2.writeByte(node.getSymbol());
                    accumulatorBitCount -= node.getTerminalBitCount();
                    node = root;
                    continue;
                }
                accumulatorBitCount -= 8;
            }
            ++i;
        }
        while (accumulatorBitCount > 0) {
            int childIndex = accumulator << 8 - accumulatorBitCount & 0xFF;
            Node[] nodeArray = node.getChildren();
            Intrinsics.checkNotNull(nodeArray);
            Node node2 = nodeArray[childIndex];
            Intrinsics.checkNotNull(node2);
            node = node2;
            if (node.getChildren() != null || node.getTerminalBitCount() > accumulatorBitCount) break;
            sink2.writeByte(node.getSymbol());
            accumulatorBitCount -= node.getTerminalBitCount();
            node = root;
        }
    }

    private final void addCode(int symbol, int code, int codeBitCount) {
        Node terminal = new Node(symbol, codeBitCount);
        int accumulatorBitCount = codeBitCount;
        Node node = root;
        while (accumulatorBitCount > 8) {
            Node[] children;
            int childIndex = code >>> (accumulatorBitCount -= 8) & 0xFF;
            Intrinsics.checkNotNull(node.getChildren());
            Node child = children[childIndex];
            if (child == null) {
                children[childIndex] = child = new Node();
            }
            node = child;
        }
        int shift = 8 - accumulatorBitCount;
        int start = code << shift & 0xFF;
        int end = 1 << shift;
        Node[] nodeArray = node.getChildren();
        Intrinsics.checkNotNull(nodeArray);
        ArraysKt.fill(nodeArray, terminal, start, start + end);
    }

    private Huffman() {
    }

    /*
     * WARNING - void declaration
     */
    static {
        Huffman huffman;
        INSTANCE = huffman = new Huffman();
        CODES = new int[]{8184, 8388568, 0xFFFFFE2, 0xFFFFFE3, 0xFFFFFE4, 0xFFFFFE5, 0xFFFFFE6, 0xFFFFFE7, 0xFFFFFE8, 0xFFFFEA, 0x3FFFFFFC, 0xFFFFFE9, 0xFFFFFEA, 0x3FFFFFFD, 0xFFFFFEB, 0xFFFFFEC, 0xFFFFFED, 0xFFFFFEE, 0xFFFFFEF, 0xFFFFFF0, 0xFFFFFF1, 0xFFFFFF2, 0x3FFFFFFE, 0xFFFFFF3, 0xFFFFFF4, 0xFFFFFF5, 0xFFFFFF6, 0xFFFFFF7, 0xFFFFFF8, 0xFFFFFF9, 0xFFFFFFA, 0xFFFFFFB, 20, 1016, 1017, 4090, 8185, 21, 248, 2042, 1018, 1019, 249, 2043, 250, 22, 23, 24, 0, 1, 2, 25, 26, 27, 28, 29, 30, 31, 92, 251, 32764, 32, 4091, 1020, 8186, 33, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 252, 115, 253, 8187, 524272, 8188, 16380, 34, 32765, 3, 35, 4, 36, 5, 37, 38, 39, 6, 116, 117, 40, 41, 42, 7, 43, 118, 44, 8, 9, 45, 119, 120, 121, 122, 123, 32766, 2044, 16381, 8189, 0xFFFFFFC, 1048550, 4194258, 1048551, 1048552, 0x3FFFD3, 4194260, 4194261, 8388569, 4194262, 8388570, 8388571, 8388572, 0x7FFFDD, 8388574, 0xFFFFEB, 0x7FFFDF, 0xFFFFEC, 0xFFFFED, 4194263, 8388576, 0xFFFFEE, 8388577, 8388578, 8388579, 8388580, 2097116, 4194264, 8388581, 4194265, 8388582, 0x7FFFE7, 0xFFFFEF, 4194266, 0x1FFFDD, 1048553, 4194267, 4194268, 8388584, 8388585, 2097118, 8388586, 0x3FFFDD, 4194270, 0xFFFFF0, 0x1FFFDF, 0x3FFFDF, 8388587, 8388588, 2097120, 0x1FFFE1, 4194272, 2097122, 8388589, 4194273, 0x7FFFEE, 0x7FFFEF, 1048554, 4194274, 0x3FFFE3, 4194276, 0x7FFFF0, 4194277, 4194278, 0x7FFFF1, 67108832, 67108833, 1048555, 524273, 4194279, 0x7FFFF2, 4194280, 33554412, 67108834, 0x3FFFFE3, 67108836, 134217694, 0x7FFFFDF, 67108837, 0xFFFFF1, 33554413, 524274, 2097123, 67108838, 134217696, 134217697, 67108839, 134217698, 0xFFFFF2, 2097124, 2097125, 67108840, 67108841, 0xFFFFFFD, 134217699, 134217700, 134217701, 1048556, 0xFFFFF3, 1048557, 2097126, 4194281, 2097127, 2097128, 0x7FFFF3, 4194282, 4194283, 0x1FFFFEE, 0x1FFFFEF, 0xFFFFF4, 0xFFFFF5, 67108842, 0x7FFFF4, 67108843, 134217702, 67108844, 67108845, 0x7FFFFE7, 134217704, 134217705, 134217706, 134217707, 0xFFFFFFE, 134217708, 134217709, 0x7FFFFEE, 0x7FFFFEF, 0x7FFFFF0, 0x3FFFFEE};
        CODE_BIT_COUNTS = new byte[]{13, 23, 28, 28, 28, 28, 28, 28, 28, 24, 30, 28, 28, 30, 28, 28, 28, 28, 28, 28, 28, 28, 30, 28, 28, 28, 28, 28, 28, 28, 28, 28, 6, 10, 10, 12, 13, 6, 8, 11, 10, 10, 8, 11, 8, 6, 6, 6, 5, 5, 5, 6, 6, 6, 6, 6, 6, 6, 7, 8, 15, 6, 12, 10, 13, 6, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 8, 7, 8, 13, 19, 13, 14, 6, 15, 5, 6, 5, 6, 5, 6, 6, 6, 5, 7, 7, 6, 6, 6, 5, 6, 7, 6, 5, 5, 6, 7, 7, 7, 7, 7, 15, 11, 14, 13, 28, 20, 22, 20, 20, 22, 22, 22, 23, 22, 23, 23, 23, 23, 23, 24, 23, 24, 24, 22, 23, 24, 23, 23, 23, 23, 21, 22, 23, 22, 23, 23, 24, 22, 21, 20, 22, 22, 23, 23, 21, 23, 22, 22, 24, 21, 22, 23, 23, 21, 21, 22, 21, 23, 22, 23, 23, 20, 22, 22, 22, 23, 22, 22, 23, 26, 26, 20, 19, 22, 23, 22, 25, 26, 26, 26, 27, 27, 26, 24, 25, 19, 21, 26, 27, 27, 26, 27, 24, 21, 21, 26, 26, 28, 27, 27, 27, 20, 24, 20, 21, 22, 21, 21, 23, 22, 22, 25, 25, 24, 24, 26, 23, 26, 27, 26, 26, 27, 27, 27, 27, 27, 28, 27, 27, 27, 27, 27, 26};
        root = new Node();
        int n = 0;
        int n2 = CODE_BIT_COUNTS.length;
        while (n < n2) {
            void i;
            huffman.addCode((int)i, CODES[i], CODE_BIT_COUNTS[i]);
            ++i;
        }
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\b\b\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0016\u00a2\u0006\u0002\u0010\u0002B\u0017\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0002\u0010\u0006R\u001d\u0010\u0007\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0000\u0018\u00010\b\u00a2\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u000e\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\r\u00a8\u0006\u0010"}, d2={"Lokhttp3/internal/http2/Huffman$Node;", "", "()V", "symbol", "", "bits", "(II)V", "children", "", "getChildren", "()[Lokhttp3/internal/http2/Huffman$Node;", "[Lokhttp3/internal/http2/Huffman$Node;", "getSymbol", "()I", "terminalBitCount", "getTerminalBitCount", "okhttp"})
    private static final class Node {
        @Nullable
        private final Node[] children;
        private final int symbol;
        private final int terminalBitCount;

        @Nullable
        public final Node[] getChildren() {
            return this.children;
        }

        public final int getSymbol() {
            return this.symbol;
        }

        public final int getTerminalBitCount() {
            return this.terminalBitCount;
        }

        public Node() {
            this.children = new Node[256];
            this.symbol = 0;
            this.terminalBitCount = 0;
        }

        public Node(int symbol, int bits) {
            this.children = null;
            this.symbol = symbol;
            int b = bits & 7;
            this.terminalBitCount = b == 0 ? 8 : b;
        }
    }
}

