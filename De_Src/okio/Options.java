/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.RandomAccess;
import kotlin.Metadata;
import kotlin.collections.AbstractList;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okio.Buffer;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\b\u0018\u0000 \u00152\b\u0012\u0004\u0012\u00020\u00020\u00012\u00060\u0003j\u0002`\u0004:\u0001\u0015B\u001f\b\u0002\u0012\u000e\u0010\u0005\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u00a2\u0006\u0002\u0010\tJ\u0011\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u000eH\u0096\u0002R\u001e\u0010\u0005\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00020\u0006X\u0080\u0004\u00a2\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\r\u001a\u00020\u000e8VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0007\u001a\u00020\bX\u0080\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012\u00a8\u0006\u0016"}, d2={"Lokio/Options;", "Lkotlin/collections/AbstractList;", "Lokio/ByteString;", "Ljava/util/RandomAccess;", "Lkotlin/collections/RandomAccess;", "byteStrings", "", "trie", "", "([Lokio/ByteString;[I)V", "getByteStrings$okio", "()[Lokio/ByteString;", "[Lokio/ByteString;", "size", "", "getSize", "()I", "getTrie$okio", "()[I", "get", "index", "Companion", "okio"})
public final class Options
extends AbstractList<ByteString>
implements RandomAccess {
    @NotNull
    private final ByteString[] byteStrings;
    @NotNull
    private final int[] trie;
    public static final Companion Companion = new Companion(null);

    @Override
    public int getSize() {
        return this.byteStrings.length;
    }

    @Override
    @NotNull
    public ByteString get(int index) {
        return this.byteStrings[index];
    }

    @NotNull
    public final ByteString[] getByteStrings$okio() {
        return this.byteStrings;
    }

    @NotNull
    public final int[] getTrie$okio() {
        return this.trie;
    }

    private Options(ByteString[] byteStrings, int[] trie) {
        this.byteStrings = byteStrings;
        this.trie = trie;
    }

    public /* synthetic */ Options(ByteString[] byteStrings, int[] trie, DefaultConstructorMarker $constructor_marker) {
        this(byteStrings, trie);
    }

    @JvmStatic
    @NotNull
    public static final Options of(@NotNull ByteString ... byteStrings) {
        return Companion.of(byteStrings);
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002JT\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u00052\b\b\u0002\u0010\f\u001a\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\b\b\u0002\u0010\u0011\u001a\u00020\r2\b\b\u0002\u0010\u0012\u001a\u00020\r2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\r0\u000fH\u0002J!\u0010\u0014\u001a\u00020\u00152\u0012\u0010\u000e\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00100\u0016\"\u00020\u0010H\u0007\u00a2\u0006\u0002\u0010\u0017R\u0018\u0010\u0003\u001a\u00020\u0004*\u00020\u00058BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\u0018"}, d2={"Lokio/Options$Companion;", "", "()V", "intCount", "", "Lokio/Buffer;", "getIntCount", "(Lokio/Buffer;)J", "buildTrieRecursive", "", "nodeOffset", "node", "byteStringOffset", "", "byteStrings", "", "Lokio/ByteString;", "fromIndex", "toIndex", "indexes", "of", "Lokio/Options;", "", "([Lokio/ByteString;)Lokio/Options;", "okio"})
    public static final class Companion {
        /*
         * WARNING - void declaration
         */
        @JvmStatic
        @NotNull
        public final Options of(@NotNull ByteString ... byteStrings) {
            int n;
            void $this$mapTo$iv$iv;
            Intrinsics.checkNotNullParameter(byteStrings, "byteStrings");
            ByteString[] byteStringArray = byteStrings;
            boolean bl = false;
            if (byteStringArray.length == 0) {
                return new Options(new ByteString[0], new int[]{0, -1}, null);
            }
            List<ByteString> list = ArraysKt.toMutableList(byteStrings);
            CollectionsKt.sort(list);
            ByteString[] $this$map$iv = byteStrings;
            boolean $i$f$map = false;
            ByteString[] byteStringArray2 = $this$map$iv;
            ByteString[] destination$iv$iv = (ByteString[])new ArrayList($this$map$iv.length);
            boolean $i$f$mapTo = false;
            void var9_19 = $this$mapTo$iv$iv;
            int n2 = ((void)var9_19).length;
            for (int i = 0; i < n2; ++i) {
                void item$iv$iv;
                void var13_28 = item$iv$iv = var9_19[i];
                ByteString[] byteStringArray3 = destination$iv$iv;
                boolean bl2 = false;
                Integer n3 = -1;
                byteStringArray3.add(n3);
            }
            Collection $this$toTypedArray$iv = (List)destination$iv$iv;
            boolean $i$f$toTypedArray = false;
            Collection thisCollection$iv = $this$toTypedArray$iv;
            Integer[] integerArray = thisCollection$iv.toArray(new Integer[0]);
            if (integerArray == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
            }
            List<Integer> indexes = CollectionsKt.mutableListOf(Arrays.copyOf(integerArray, integerArray.length));
            ByteString[] $this$forEachIndexed$iv22 = byteStrings;
            boolean $i$f$forEachIndexed = false;
            int index$iv2 = 0;
            for (ByteString item$iv : $this$forEachIndexed$iv22) {
                void byteString;
                int n4 = index$iv2++;
                ByteString byteString2 = item$iv;
                int callerIndex = n4;
                boolean bl3 = false;
                int sortedIndex = CollectionsKt.binarySearch$default(list, (Comparable)byteString, 0, 0, 6, null);
                indexes.set(sortedIndex, callerIndex);
            }
            boolean $this$forEachIndexed$iv22 = list.get(0).size() > 0;
            $i$f$forEachIndexed = false;
            index$iv2 = 0;
            if (!$this$forEachIndexed$iv22) {
                boolean bl4 = false;
                String index$iv2 = "the empty byte string is not a supported option";
                throw (Throwable)new IllegalArgumentException(index$iv2.toString());
            }
            for (int a = 0; a < list.size(); ++a) {
                ByteString byteString;
                ByteString prefix = list.get(a);
                int b = a + 1;
                while (b < list.size() && (byteString = list.get(b)).startsWith(prefix)) {
                    int n5 = byteString.size() != prefix.size() ? 1 : 0;
                    n = 0;
                    n2 = 0;
                    if (n5 == 0) {
                        boolean bl5 = false;
                        String string = "duplicate option: " + byteString;
                        throw (Throwable)new IllegalArgumentException(string.toString());
                    }
                    if (((Number)indexes.get(b)).intValue() > ((Number)indexes.get(a)).intValue()) {
                        list.remove(b);
                        indexes.remove(b);
                        continue;
                    }
                    ++b;
                }
            }
            Buffer trieBytes = new Buffer();
            okio.Options$Companion.buildTrieRecursive$default(this, 0L, trieBytes, 0, list, 0, 0, indexes, 53, null);
            int[] trie = new int[(int)this.getIntCount(trieBytes)];
            int i = 0;
            while (!trieBytes.exhausted()) {
                trie[i++] = trieBytes.readInt();
            }
            ByteString[] byteStringArray4 = byteStrings;
            n = 0;
            ByteString[] byteStringArray5 = Arrays.copyOf(byteStringArray4, byteStringArray4.length);
            Intrinsics.checkNotNullExpressionValue(byteStringArray5, "java.util.Arrays.copyOf(this, size)");
            return new Options(byteStringArray5, trie, null);
        }

        /*
         * WARNING - void declaration
         */
        private final void buildTrieRecursive(long nodeOffset, Buffer node, int byteStringOffset, List<? extends ByteString> byteStrings, int fromIndex, int toIndex, List<Integer> indexes) {
            void i;
            int n;
            int n2 = fromIndex < toIndex ? 1 : 0;
            int n3 = 0;
            boolean bl = false;
            bl = false;
            boolean bl2 = false;
            if (n2 == 0) {
                boolean bl3 = false;
                String string = "Failed requirement.";
                throw (Throwable)new IllegalArgumentException(string.toString());
            }
            n2 = fromIndex;
            n3 = toIndex;
            while (n2 < n3) {
                void i2;
                bl = byteStrings.get((int)i2).size() >= byteStringOffset;
                bl2 = false;
                boolean bl4 = false;
                bl4 = false;
                n = 0;
                if (!bl) {
                    boolean bl5 = false;
                    String string = "Failed requirement.";
                    throw (Throwable)new IllegalArgumentException(string.toString());
                }
                ++i2;
            }
            int fromIndex2 = fromIndex;
            ByteString from = byteStrings.get(fromIndex2);
            ByteString to = byteStrings.get(toIndex - 1);
            int prefixIndex = -1;
            if (byteStringOffset == from.size()) {
                prefixIndex = ((Number)indexes.get(fromIndex2)).intValue();
                from = byteStrings.get(++fromIndex2);
            }
            if (from.getByte(byteStringOffset) != to.getByte(byteStringOffset)) {
                int other$iv;
                byte rangeByte;
                int selectChoiceCount = 1;
                n = fromIndex2 + 1;
                int n4 = toIndex;
                while (n < n4) {
                    if (byteStrings.get((int)(i - true)).getByte(byteStringOffset) != byteStrings.get((int)i).getByte(byteStringOffset)) {
                        ++selectChoiceCount;
                    }
                    ++i;
                }
                long childNodesOffset = nodeOffset + this.getIntCount(node) + (long)2 + (long)(selectChoiceCount * 2);
                node.writeInt(selectChoiceCount);
                node.writeInt(prefixIndex);
                int n5 = fromIndex2;
                int n6 = toIndex;
                while (n5 < n6) {
                    void i3;
                    rangeByte = byteStrings.get((int)i3).getByte(byteStringOffset);
                    if (i3 == fromIndex2 || rangeByte != byteStrings.get((int)(i3 - true)).getByte(byteStringOffset)) {
                        void $this$and$iv;
                        byte by = rangeByte;
                        other$iv = 255;
                        boolean $i$f$and = false;
                        node.writeInt($this$and$iv & other$iv);
                    }
                    ++i3;
                }
                Buffer childNodes = new Buffer();
                int rangeStart = fromIndex2;
                while (rangeStart < toIndex) {
                    rangeByte = byteStrings.get(rangeStart).getByte(byteStringOffset);
                    int rangeEnd = toIndex;
                    other$iv = rangeStart + 1;
                    int n7 = toIndex;
                    while (other$iv < n7) {
                        void i4;
                        if (rangeByte != byteStrings.get((int)i4).getByte(byteStringOffset)) {
                            rangeEnd = i4;
                            break;
                        }
                        ++i4;
                    }
                    if (rangeStart + 1 == rangeEnd && byteStringOffset + 1 == byteStrings.get(rangeStart).size()) {
                        node.writeInt(((Number)indexes.get(rangeStart)).intValue());
                    } else {
                        node.writeInt(-1 * (int)(childNodesOffset + this.getIntCount(childNodes)));
                        this.buildTrieRecursive(childNodesOffset, childNodes, byteStringOffset + 1, byteStrings, rangeStart, rangeEnd, indexes);
                    }
                    rangeStart = rangeEnd;
                }
                node.writeAll(childNodes);
            } else {
                boolean i5;
                int scanByteCount = 0;
                int childNodesOffset = byteStringOffset;
                int childNodes = from.size();
                int n8 = to.size();
                byte rangeByte = 0;
                int n9 = Math.min(childNodes, n8);
                while (childNodesOffset < n9 && from.getByte((int)i) == to.getByte((int)i)) {
                    ++scanByteCount;
                    ++i;
                }
                long childNodesOffset2 = nodeOffset + this.getIntCount(node) + (long)2 + (long)scanByteCount + 1L;
                node.writeInt(-scanByteCount);
                node.writeInt(prefixIndex);
                childNodes = byteStringOffset;
                n8 = byteStringOffset + scanByteCount;
                while (childNodes < n8) {
                    void $this$and$iv;
                    rangeByte = from.getByte(i5 ? 1 : 0);
                    int other$iv = 255;
                    boolean $i$f$and = false;
                    node.writeInt($this$and$iv & other$iv);
                    i5 += 1;
                }
                if (fromIndex2 + 1 == toIndex) {
                    i5 = byteStringOffset + scanByteCount == byteStrings.get(fromIndex2).size();
                    n8 = 0;
                    boolean bl6 = false;
                    bl6 = false;
                    boolean bl7 = false;
                    if (!i5) {
                        boolean bl8 = false;
                        String string = "Check failed.";
                        throw (Throwable)new IllegalStateException(string.toString());
                    }
                    node.writeInt(((Number)indexes.get(fromIndex2)).intValue());
                } else {
                    Buffer childNodes2 = new Buffer();
                    node.writeInt(-1 * (int)(childNodesOffset2 + this.getIntCount(childNodes2)));
                    this.buildTrieRecursive(childNodesOffset2, childNodes2, byteStringOffset + scanByteCount, byteStrings, fromIndex2, toIndex, indexes);
                    node.writeAll(childNodes2);
                }
            }
        }

        static /* synthetic */ void buildTrieRecursive$default(Companion companion, long l, Buffer buffer, int n, List list, int n2, int n3, List list2, int n4, Object object) {
            if ((n4 & 1) != 0) {
                l = 0L;
            }
            if ((n4 & 4) != 0) {
                n = 0;
            }
            if ((n4 & 0x10) != 0) {
                n2 = 0;
            }
            if ((n4 & 0x20) != 0) {
                n3 = list.size();
            }
            companion.buildTrieRecursive(l, buffer, n, list, n2, n3, list2);
        }

        private final long getIntCount(Buffer $this$intCount) {
            return $this$intCount.size() / (long)4;
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

