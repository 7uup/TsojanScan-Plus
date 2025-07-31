/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okhttp3.internal.http2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.Util;
import okhttp3.internal.http2.Header;
import okhttp3.internal.http2.Huffman;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;
import okio.Okio;
import okio.Source;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\t\b\u00c6\u0002\u0018\u00002\u00020\u0001:\u0002\u0018\u0019B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u0005J\u0014\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004H\u0002R\u001d\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u0019\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010\u00a2\u0006\n\n\u0002\u0010\u0014\u001a\u0004\b\u0012\u0010\u0013\u00a8\u0006\u001a"}, d2={"Lokhttp3/internal/http2/Hpack;", "", "()V", "NAME_TO_FIRST_INDEX", "", "Lokio/ByteString;", "", "getNAME_TO_FIRST_INDEX", "()Ljava/util/Map;", "PREFIX_4_BITS", "PREFIX_5_BITS", "PREFIX_6_BITS", "PREFIX_7_BITS", "SETTINGS_HEADER_TABLE_SIZE", "SETTINGS_HEADER_TABLE_SIZE_LIMIT", "STATIC_HEADER_TABLE", "", "Lokhttp3/internal/http2/Header;", "getSTATIC_HEADER_TABLE", "()[Lokhttp3/internal/http2/Header;", "[Lokhttp3/internal/http2/Header;", "checkLowercase", "name", "nameToFirstIndex", "Reader", "Writer", "okhttp"})
public final class Hpack {
    private static final int PREFIX_4_BITS = 15;
    private static final int PREFIX_5_BITS = 31;
    private static final int PREFIX_6_BITS = 63;
    private static final int PREFIX_7_BITS = 127;
    private static final int SETTINGS_HEADER_TABLE_SIZE = 4096;
    private static final int SETTINGS_HEADER_TABLE_SIZE_LIMIT = 16384;
    @NotNull
    private static final Header[] STATIC_HEADER_TABLE;
    @NotNull
    private static final Map<ByteString, Integer> NAME_TO_FIRST_INDEX;
    public static final Hpack INSTANCE;

    @NotNull
    public final Header[] getSTATIC_HEADER_TABLE() {
        return STATIC_HEADER_TABLE;
    }

    @NotNull
    public final Map<ByteString, Integer> getNAME_TO_FIRST_INDEX() {
        return NAME_TO_FIRST_INDEX;
    }

    /*
     * WARNING - void declaration
     */
    private final Map<ByteString, Integer> nameToFirstIndex() {
        LinkedHashMap result = new LinkedHashMap(STATIC_HEADER_TABLE.length);
        int n = 0;
        int n2 = STATIC_HEADER_TABLE.length;
        while (n < n2) {
            void i;
            if (!result.containsKey(Hpack.STATIC_HEADER_TABLE[i].name)) {
                ((Map)result).put(Hpack.STATIC_HEADER_TABLE[i].name, (int)i);
            }
            ++i;
        }
        Map<ByteString, Integer> map = Collections.unmodifiableMap(result);
        Intrinsics.checkNotNullExpressionValue(map, "Collections.unmodifiableMap(result)");
        return map;
    }

    /*
     * WARNING - void declaration
     */
    @NotNull
    public final ByteString checkLowercase(@NotNull ByteString name) throws IOException {
        Intrinsics.checkNotNullParameter(name, "name");
        int n = 0;
        int n2 = name.size();
        while (n < n2) {
            void i;
            byte by = name.getByte((int)i);
            if ((byte)65 <= by && (byte)90 >= by) {
                throw (Throwable)new IOException("PROTOCOL_ERROR response malformed: mixed case name: " + name.utf8());
            }
            ++i;
        }
        return name;
    }

    private Hpack() {
    }

    static {
        Hpack hpack;
        INSTANCE = hpack = new Hpack();
        STATIC_HEADER_TABLE = new Header[]{new Header(Header.TARGET_AUTHORITY, ""), new Header(Header.TARGET_METHOD, "GET"), new Header(Header.TARGET_METHOD, "POST"), new Header(Header.TARGET_PATH, "/"), new Header(Header.TARGET_PATH, "/index.html"), new Header(Header.TARGET_SCHEME, "http"), new Header(Header.TARGET_SCHEME, "https"), new Header(Header.RESPONSE_STATUS, "200"), new Header(Header.RESPONSE_STATUS, "204"), new Header(Header.RESPONSE_STATUS, "206"), new Header(Header.RESPONSE_STATUS, "304"), new Header(Header.RESPONSE_STATUS, "400"), new Header(Header.RESPONSE_STATUS, "404"), new Header(Header.RESPONSE_STATUS, "500"), new Header("accept-charset", ""), new Header("accept-encoding", "gzip, deflate"), new Header("accept-language", ""), new Header("accept-ranges", ""), new Header("accept", ""), new Header("access-control-allow-origin", ""), new Header("age", ""), new Header("allow", ""), new Header("authorization", ""), new Header("cache-control", ""), new Header("content-disposition", ""), new Header("content-encoding", ""), new Header("content-language", ""), new Header("content-length", ""), new Header("content-location", ""), new Header("content-range", ""), new Header("content-type", ""), new Header("cookie", ""), new Header("date", ""), new Header("etag", ""), new Header("expect", ""), new Header("expires", ""), new Header("from", ""), new Header("host", ""), new Header("if-match", ""), new Header("if-modified-since", ""), new Header("if-none-match", ""), new Header("if-range", ""), new Header("if-unmodified-since", ""), new Header("last-modified", ""), new Header("link", ""), new Header("location", ""), new Header("max-forwards", ""), new Header("proxy-authenticate", ""), new Header("proxy-authorization", ""), new Header("range", ""), new Header("referer", ""), new Header("refresh", ""), new Header("retry-after", ""), new Header("server", ""), new Header("set-cookie", ""), new Header("strict-transport-security", ""), new Header("transfer-encoding", ""), new Header("user-agent", ""), new Header("vary", ""), new Header("via", ""), new Header("www-authenticate", "")};
        NAME_TO_FIRST_INDEX = hpack.nameToFirstIndex();
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\r\u0018\u00002\u00020\u0001B!\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0007J\b\u0010\u0012\u001a\u00020\u0013H\u0002J\b\u0010\u0014\u001a\u00020\u0013H\u0002J\u0010\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u0005H\u0002J\u0010\u0010\u0017\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u0005H\u0002J\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\n0\u001aJ\u0010\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u0016\u001a\u00020\u0005H\u0002J\u0018\u0010\u001d\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u00052\u0006\u0010\u001e\u001a\u00020\nH\u0002J\u0010\u0010\u001f\u001a\u00020 2\u0006\u0010\u0016\u001a\u00020\u0005H\u0002J\u0006\u0010\u0006\u001a\u00020\u0005J\b\u0010!\u001a\u00020\u0005H\u0002J\u0006\u0010\"\u001a\u00020\u001cJ\u0006\u0010#\u001a\u00020\u0013J\u0010\u0010$\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u0005H\u0002J\u0016\u0010%\u001a\u00020\u00052\u0006\u0010&\u001a\u00020\u00052\u0006\u0010'\u001a\u00020\u0005J\u0010\u0010(\u001a\u00020\u00132\u0006\u0010)\u001a\u00020\u0005H\u0002J\b\u0010*\u001a\u00020\u0013H\u0002J\u0010\u0010+\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u0005H\u0002J\b\u0010,\u001a\u00020\u0013H\u0002R\u001c\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\t8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0004\n\u0002\u0010\u000bR\u0012\u0010\f\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0002\n\u0000R\u0012\u0010\r\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\n0\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006-"}, d2={"Lokhttp3/internal/http2/Hpack$Reader;", "", "source", "Lokio/Source;", "headerTableSizeSetting", "", "maxDynamicTableByteCount", "(Lokio/Source;II)V", "dynamicTable", "", "Lokhttp3/internal/http2/Header;", "[Lokhttp3/internal/http2/Header;", "dynamicTableByteCount", "headerCount", "headerList", "", "nextHeaderIndex", "Lokio/BufferedSource;", "adjustDynamicTableByteCount", "", "clearDynamicTable", "dynamicTableIndex", "index", "evictToRecoverBytes", "bytesToRecover", "getAndResetHeaderList", "", "getName", "Lokio/ByteString;", "insertIntoDynamicTable", "entry", "isStaticHeader", "", "readByte", "readByteString", "readHeaders", "readIndexedHeader", "readInt", "firstByte", "prefixMask", "readLiteralHeaderWithIncrementalIndexingIndexedName", "nameIndex", "readLiteralHeaderWithIncrementalIndexingNewName", "readLiteralHeaderWithoutIndexingIndexedName", "readLiteralHeaderWithoutIndexingNewName", "okhttp"})
    public static final class Reader {
        private final List<Header> headerList;
        private final BufferedSource source;
        @JvmField
        @NotNull
        public Header[] dynamicTable;
        private int nextHeaderIndex;
        @JvmField
        public int headerCount;
        @JvmField
        public int dynamicTableByteCount;
        private final int headerTableSizeSetting;
        private int maxDynamicTableByteCount;

        @NotNull
        public final List<Header> getAndResetHeaderList() {
            List<Header> result = CollectionsKt.toList((Iterable)this.headerList);
            this.headerList.clear();
            return result;
        }

        public final int maxDynamicTableByteCount() {
            return this.maxDynamicTableByteCount;
        }

        private final void adjustDynamicTableByteCount() {
            if (this.maxDynamicTableByteCount < this.dynamicTableByteCount) {
                if (this.maxDynamicTableByteCount == 0) {
                    this.clearDynamicTable();
                } else {
                    this.evictToRecoverBytes(this.dynamicTableByteCount - this.maxDynamicTableByteCount);
                }
            }
        }

        private final void clearDynamicTable() {
            ArraysKt.fill$default(this.dynamicTable, null, 0, 0, 6, null);
            this.nextHeaderIndex = this.dynamicTable.length - 1;
            this.headerCount = 0;
            this.dynamicTableByteCount = 0;
        }

        private final int evictToRecoverBytes(int bytesToRecover) {
            int bytesToRecover2 = bytesToRecover;
            int entriesToEvict = 0;
            if (bytesToRecover2 > 0) {
                for (int j = this.dynamicTable.length - 1; j >= this.nextHeaderIndex && bytesToRecover2 > 0; --j) {
                    Header toEvict;
                    Intrinsics.checkNotNull(this.dynamicTable[j]);
                    bytesToRecover2 -= toEvict.hpackSize;
                    this.dynamicTableByteCount -= toEvict.hpackSize;
                    int n = this.headerCount;
                    this.headerCount = n + -1;
                    ++entriesToEvict;
                }
                System.arraycopy(this.dynamicTable, this.nextHeaderIndex + 1, this.dynamicTable, this.nextHeaderIndex + 1 + entriesToEvict, this.headerCount);
                this.nextHeaderIndex += entriesToEvict;
            }
            return entriesToEvict;
        }

        public final void readHeaders() throws IOException {
            while (!this.source.exhausted()) {
                int index;
                int b = Util.and(this.source.readByte(), 255);
                if (b == 128) {
                    throw (Throwable)new IOException("index == 0");
                }
                if ((b & 0x80) == 128) {
                    index = this.readInt(b, 127);
                    this.readIndexedHeader(index - 1);
                    continue;
                }
                if (b == 64) {
                    this.readLiteralHeaderWithIncrementalIndexingNewName();
                    continue;
                }
                if ((b & 0x40) == 64) {
                    index = this.readInt(b, 63);
                    this.readLiteralHeaderWithIncrementalIndexingIndexedName(index - 1);
                    continue;
                }
                if ((b & 0x20) == 32) {
                    this.maxDynamicTableByteCount = this.readInt(b, 31);
                    if (this.maxDynamicTableByteCount < 0 || this.maxDynamicTableByteCount > this.headerTableSizeSetting) {
                        throw (Throwable)new IOException("Invalid dynamic table size update " + this.maxDynamicTableByteCount);
                    }
                    this.adjustDynamicTableByteCount();
                    continue;
                }
                if (b == 16 || b == 0) {
                    this.readLiteralHeaderWithoutIndexingNewName();
                    continue;
                }
                index = this.readInt(b, 15);
                this.readLiteralHeaderWithoutIndexingIndexedName(index - 1);
            }
        }

        private final void readIndexedHeader(int index) throws IOException {
            if (this.isStaticHeader(index)) {
                Header staticEntry = INSTANCE.getSTATIC_HEADER_TABLE()[index];
                this.headerList.add(staticEntry);
            } else {
                int dynamicTableIndex = this.dynamicTableIndex(index - INSTANCE.getSTATIC_HEADER_TABLE().length);
                if (dynamicTableIndex < 0 || dynamicTableIndex >= this.dynamicTable.length) {
                    throw (Throwable)new IOException("Header index too large " + (index + 1));
                }
                Collection collection = this.headerList;
                Header header = this.dynamicTable[dynamicTableIndex];
                Intrinsics.checkNotNull(header);
                Header header2 = header;
                boolean bl = false;
                collection.add(header2);
            }
        }

        private final int dynamicTableIndex(int index) {
            return this.nextHeaderIndex + 1 + index;
        }

        private final void readLiteralHeaderWithoutIndexingIndexedName(int index) throws IOException {
            ByteString name = this.getName(index);
            ByteString value = this.readByteString();
            this.headerList.add(new Header(name, value));
        }

        private final void readLiteralHeaderWithoutIndexingNewName() throws IOException {
            ByteString name = INSTANCE.checkLowercase(this.readByteString());
            ByteString value = this.readByteString();
            this.headerList.add(new Header(name, value));
        }

        private final void readLiteralHeaderWithIncrementalIndexingIndexedName(int nameIndex) throws IOException {
            ByteString name = this.getName(nameIndex);
            ByteString value = this.readByteString();
            this.insertIntoDynamicTable(-1, new Header(name, value));
        }

        private final void readLiteralHeaderWithIncrementalIndexingNewName() throws IOException {
            ByteString name = INSTANCE.checkLowercase(this.readByteString());
            ByteString value = this.readByteString();
            this.insertIntoDynamicTable(-1, new Header(name, value));
        }

        private final ByteString getName(int index) throws IOException {
            ByteString byteString;
            if (this.isStaticHeader(index)) {
                byteString = Hpack.INSTANCE.getSTATIC_HEADER_TABLE()[index].name;
            } else {
                int dynamicTableIndex = this.dynamicTableIndex(index - INSTANCE.getSTATIC_HEADER_TABLE().length);
                if (dynamicTableIndex < 0 || dynamicTableIndex >= this.dynamicTable.length) {
                    throw (Throwable)new IOException("Header index too large " + (index + 1));
                }
                Header header = this.dynamicTable[dynamicTableIndex];
                Intrinsics.checkNotNull(header);
                byteString = header.name;
            }
            return byteString;
        }

        private final boolean isStaticHeader(int index) {
            return index >= 0 && index <= INSTANCE.getSTATIC_HEADER_TABLE().length - 1;
        }

        private final void insertIntoDynamicTable(int index, Header entry) {
            int index2 = index;
            this.headerList.add(entry);
            int delta = entry.hpackSize;
            if (index2 != -1) {
                Header header = this.dynamicTable[this.dynamicTableIndex(index2)];
                Intrinsics.checkNotNull(header);
                delta -= header.hpackSize;
            }
            if (delta > this.maxDynamicTableByteCount) {
                this.clearDynamicTable();
                return;
            }
            int bytesToRecover = this.dynamicTableByteCount + delta - this.maxDynamicTableByteCount;
            int entriesEvicted = this.evictToRecoverBytes(bytesToRecover);
            if (index2 == -1) {
                if (this.headerCount + 1 > this.dynamicTable.length) {
                    Header[] doubled = new Header[this.dynamicTable.length * 2];
                    System.arraycopy(this.dynamicTable, 0, doubled, this.dynamicTable.length, this.dynamicTable.length);
                    this.nextHeaderIndex = this.dynamicTable.length - 1;
                    this.dynamicTable = doubled;
                }
                int n = this.nextHeaderIndex;
                this.nextHeaderIndex = n + -1;
                index2 = n;
                this.dynamicTable[index2] = entry;
                n = this.headerCount;
                this.headerCount = n + 1;
            } else {
                index2 += this.dynamicTableIndex(index2) + entriesEvicted;
                this.dynamicTable[index2] = entry;
            }
            this.dynamicTableByteCount += delta;
        }

        private final int readByte() throws IOException {
            return Util.and(this.source.readByte(), 255);
        }

        public final int readInt(int firstByte, int prefixMask) throws IOException {
            int b;
            int prefix = firstByte & prefixMask;
            if (prefix < prefixMask) {
                return prefix;
            }
            int result = prefixMask;
            int shift = 0;
            while (((b = this.readByte()) & 0x80) != 0) {
                result += (b & 0x7F) << shift;
                shift += 7;
            }
            return result += b << shift;
        }

        @NotNull
        public final ByteString readByteString() throws IOException {
            ByteString byteString;
            int firstByte = this.readByte();
            boolean huffmanDecode = (firstByte & 0x80) == 128;
            long length = this.readInt(firstByte, 127);
            if (huffmanDecode) {
                Buffer decodeBuffer = new Buffer();
                Huffman.INSTANCE.decode(this.source, length, decodeBuffer);
                byteString = decodeBuffer.readByteString();
            } else {
                byteString = this.source.readByteString(length);
            }
            return byteString;
        }

        @JvmOverloads
        public Reader(@NotNull Source source2, int headerTableSizeSetting, int maxDynamicTableByteCount) {
            Intrinsics.checkNotNullParameter(source2, "source");
            this.headerTableSizeSetting = headerTableSizeSetting;
            this.maxDynamicTableByteCount = maxDynamicTableByteCount;
            boolean bl = false;
            this.headerList = new ArrayList();
            this.source = Okio.buffer(source2);
            this.dynamicTable = new Header[8];
            this.nextHeaderIndex = this.dynamicTable.length - 1;
        }

        public /* synthetic */ Reader(Source source2, int n, int n2, int n3, DefaultConstructorMarker defaultConstructorMarker) {
            if ((n3 & 4) != 0) {
                n2 = n;
            }
            this(source2, n, n2);
        }

        @JvmOverloads
        public Reader(@NotNull Source source2, int headerTableSizeSetting) {
            this(source2, headerTableSizeSetting, 0, 4, null);
        }
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0005\u0018\u00002\u00020\u0001B#\b\u0007\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\b\u0010\u0013\u001a\u00020\u0014H\u0002J\b\u0010\u0015\u001a\u00020\u0014H\u0002J\u0010\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u0003H\u0002J\u0010\u0010\u0018\u001a\u00020\u00142\u0006\u0010\u0019\u001a\u00020\u000bH\u0002J\u000e\u0010\u001a\u001a\u00020\u00142\u0006\u0010\u0002\u001a\u00020\u0003J\u000e\u0010\u001b\u001a\u00020\u00142\u0006\u0010\u001c\u001a\u00020\u001dJ\u0014\u0010\u001e\u001a\u00020\u00142\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u000b0 J\u001e\u0010!\u001a\u00020\u00142\u0006\u0010\"\u001a\u00020\u00032\u0006\u0010#\u001a\u00020\u00032\u0006\u0010$\u001a\u00020\u0003R\u001c\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\n8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0004\n\u0002\u0010\fR\u0012\u0010\r\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0012\u0010\u000f\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0002\n\u0000R\u0012\u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0002\n\u0000R\u0012\u0010\u0010\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0003X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0003X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006%"}, d2={"Lokhttp3/internal/http2/Hpack$Writer;", "", "headerTableSizeSetting", "", "useCompression", "", "out", "Lokio/Buffer;", "(IZLokio/Buffer;)V", "dynamicTable", "", "Lokhttp3/internal/http2/Header;", "[Lokhttp3/internal/http2/Header;", "dynamicTableByteCount", "emitDynamicTableSizeUpdate", "headerCount", "maxDynamicTableByteCount", "nextHeaderIndex", "smallestHeaderTableSizeSetting", "adjustDynamicTableByteCount", "", "clearDynamicTable", "evictToRecoverBytes", "bytesToRecover", "insertIntoDynamicTable", "entry", "resizeHeaderTable", "writeByteString", "data", "Lokio/ByteString;", "writeHeaders", "headerBlock", "", "writeInt", "value", "prefixMask", "bits", "okhttp"})
    public static final class Writer {
        private int smallestHeaderTableSizeSetting;
        private boolean emitDynamicTableSizeUpdate;
        @JvmField
        public int maxDynamicTableByteCount;
        @JvmField
        @NotNull
        public Header[] dynamicTable;
        private int nextHeaderIndex;
        @JvmField
        public int headerCount;
        @JvmField
        public int dynamicTableByteCount;
        @JvmField
        public int headerTableSizeSetting;
        private final boolean useCompression;
        private final Buffer out;

        private final void clearDynamicTable() {
            ArraysKt.fill$default(this.dynamicTable, null, 0, 0, 6, null);
            this.nextHeaderIndex = this.dynamicTable.length - 1;
            this.headerCount = 0;
            this.dynamicTableByteCount = 0;
        }

        private final int evictToRecoverBytes(int bytesToRecover) {
            int bytesToRecover2 = bytesToRecover;
            int entriesToEvict = 0;
            if (bytesToRecover2 > 0) {
                for (int j = this.dynamicTable.length - 1; j >= this.nextHeaderIndex && bytesToRecover2 > 0; --j) {
                    Header header = this.dynamicTable[j];
                    Intrinsics.checkNotNull(header);
                    bytesToRecover2 -= header.hpackSize;
                    Header header2 = this.dynamicTable[j];
                    Intrinsics.checkNotNull(header2);
                    this.dynamicTableByteCount -= header2.hpackSize;
                    int n = this.headerCount;
                    this.headerCount = n + -1;
                    ++entriesToEvict;
                }
                System.arraycopy(this.dynamicTable, this.nextHeaderIndex + 1, this.dynamicTable, this.nextHeaderIndex + 1 + entriesToEvict, this.headerCount);
                Arrays.fill(this.dynamicTable, this.nextHeaderIndex + 1, this.nextHeaderIndex + 1 + entriesToEvict, null);
                this.nextHeaderIndex += entriesToEvict;
            }
            return entriesToEvict;
        }

        private final void insertIntoDynamicTable(Header entry) {
            int delta = entry.hpackSize;
            if (delta > this.maxDynamicTableByteCount) {
                this.clearDynamicTable();
                return;
            }
            int bytesToRecover = this.dynamicTableByteCount + delta - this.maxDynamicTableByteCount;
            this.evictToRecoverBytes(bytesToRecover);
            if (this.headerCount + 1 > this.dynamicTable.length) {
                Header[] doubled = new Header[this.dynamicTable.length * 2];
                System.arraycopy(this.dynamicTable, 0, doubled, this.dynamicTable.length, this.dynamicTable.length);
                this.nextHeaderIndex = this.dynamicTable.length - 1;
                this.dynamicTable = doubled;
            }
            int n = this.nextHeaderIndex;
            this.nextHeaderIndex = n + -1;
            int index = n;
            this.dynamicTable[index] = entry;
            n = this.headerCount;
            this.headerCount = n + 1;
            this.dynamicTableByteCount += delta;
        }

        /*
         * WARNING - void declaration
         */
        public final void writeHeaders(@NotNull List<Header> headerBlock) throws IOException {
            Intrinsics.checkNotNullParameter(headerBlock, "headerBlock");
            if (this.emitDynamicTableSizeUpdate) {
                if (this.smallestHeaderTableSizeSetting < this.maxDynamicTableByteCount) {
                    this.writeInt(this.smallestHeaderTableSizeSetting, 31, 32);
                }
                this.emitDynamicTableSizeUpdate = false;
                this.smallestHeaderTableSizeSetting = Integer.MAX_VALUE;
                this.writeInt(this.maxDynamicTableByteCount, 31, 32);
            }
            int n = 0;
            int n2 = headerBlock.size();
            while (n < n2) {
                int n3;
                void i;
                Header header = headerBlock.get((int)i);
                ByteString name = header.name.toAsciiLowercase();
                ByteString value = header.value;
                int headerIndex = -1;
                int headerNameIndex = -1;
                Integer staticIndex = INSTANCE.getNAME_TO_FIRST_INDEX().get(name);
                if (staticIndex != null && 2 <= (n3 = (headerNameIndex = staticIndex + 1)) && 7 >= n3) {
                    if (Intrinsics.areEqual(Hpack.INSTANCE.getSTATIC_HEADER_TABLE()[headerNameIndex - 1].value, value)) {
                        headerIndex = headerNameIndex;
                    } else if (Intrinsics.areEqual(Hpack.INSTANCE.getSTATIC_HEADER_TABLE()[headerNameIndex].value, value)) {
                        headerIndex = headerNameIndex + 1;
                    }
                }
                if (headerIndex == -1) {
                    n3 = this.nextHeaderIndex + 1;
                    int n4 = this.dynamicTable.length;
                    while (n3 < n4) {
                        void j;
                        Header header2 = this.dynamicTable[j];
                        Intrinsics.checkNotNull(header2);
                        if (Intrinsics.areEqual(header2.name, name)) {
                            Header header3 = this.dynamicTable[j];
                            Intrinsics.checkNotNull(header3);
                            if (Intrinsics.areEqual(header3.value, value)) {
                                headerIndex = j - this.nextHeaderIndex + INSTANCE.getSTATIC_HEADER_TABLE().length;
                                break;
                            }
                            if (headerNameIndex == -1) {
                                headerNameIndex = j - this.nextHeaderIndex + INSTANCE.getSTATIC_HEADER_TABLE().length;
                            }
                        }
                        ++j;
                    }
                }
                if (headerIndex != -1) {
                    this.writeInt(headerIndex, 127, 128);
                } else if (headerNameIndex == -1) {
                    this.out.writeByte(64);
                    this.writeByteString(name);
                    this.writeByteString(value);
                    this.insertIntoDynamicTable(header);
                } else if (name.startsWith(Header.PSEUDO_PREFIX) && Intrinsics.areEqual(Header.TARGET_AUTHORITY, name) ^ true) {
                    this.writeInt(headerNameIndex, 15, 0);
                    this.writeByteString(value);
                } else {
                    this.writeInt(headerNameIndex, 63, 64);
                    this.writeByteString(value);
                    this.insertIntoDynamicTable(header);
                }
                ++i;
            }
        }

        public final void writeInt(int value, int prefixMask, int bits) {
            int value2 = value;
            if (value2 < prefixMask) {
                this.out.writeByte(bits | value2);
                return;
            }
            this.out.writeByte(bits | prefixMask);
            value2 -= prefixMask;
            while (value2 >= 128) {
                int b = value2 & 0x7F;
                this.out.writeByte(b | 0x80);
                value2 >>>= 7;
            }
            this.out.writeByte(value2);
        }

        public final void writeByteString(@NotNull ByteString data) throws IOException {
            Intrinsics.checkNotNullParameter(data, "data");
            if (this.useCompression && Huffman.INSTANCE.encodedLength(data) < data.size()) {
                Buffer huffmanBuffer = new Buffer();
                Huffman.INSTANCE.encode(data, huffmanBuffer);
                ByteString huffmanBytes = huffmanBuffer.readByteString();
                this.writeInt(huffmanBytes.size(), 127, 128);
                this.out.write(huffmanBytes);
            } else {
                this.writeInt(data.size(), 127, 0);
                this.out.write(data);
            }
        }

        public final void resizeHeaderTable(int headerTableSizeSetting) {
            this.headerTableSizeSetting = headerTableSizeSetting;
            int n = 16384;
            boolean bl = false;
            int effectiveHeaderTableSize = Math.min(headerTableSizeSetting, n);
            if (this.maxDynamicTableByteCount == effectiveHeaderTableSize) {
                return;
            }
            if (effectiveHeaderTableSize < this.maxDynamicTableByteCount) {
                n = this.smallestHeaderTableSizeSetting;
                bl = false;
                this.smallestHeaderTableSizeSetting = Math.min(n, effectiveHeaderTableSize);
            }
            this.emitDynamicTableSizeUpdate = true;
            this.maxDynamicTableByteCount = effectiveHeaderTableSize;
            this.adjustDynamicTableByteCount();
        }

        private final void adjustDynamicTableByteCount() {
            if (this.maxDynamicTableByteCount < this.dynamicTableByteCount) {
                if (this.maxDynamicTableByteCount == 0) {
                    this.clearDynamicTable();
                } else {
                    this.evictToRecoverBytes(this.dynamicTableByteCount - this.maxDynamicTableByteCount);
                }
            }
        }

        @JvmOverloads
        public Writer(int headerTableSizeSetting, boolean useCompression, @NotNull Buffer out) {
            Intrinsics.checkNotNullParameter(out, "out");
            this.headerTableSizeSetting = headerTableSizeSetting;
            this.useCompression = useCompression;
            this.out = out;
            this.smallestHeaderTableSizeSetting = Integer.MAX_VALUE;
            this.maxDynamicTableByteCount = this.headerTableSizeSetting;
            this.dynamicTable = new Header[8];
            this.nextHeaderIndex = this.dynamicTable.length - 1;
        }

        public /* synthetic */ Writer(int n, boolean bl, Buffer buffer, int n2, DefaultConstructorMarker defaultConstructorMarker) {
            if ((n2 & 1) != 0) {
                n = 4096;
            }
            if ((n2 & 2) != 0) {
                bl = true;
            }
            this(n, bl, buffer);
        }

        @JvmOverloads
        public Writer(int headerTableSizeSetting, @NotNull Buffer out) {
            this(headerTableSizeSetting, false, out, 2, null);
        }

        @JvmOverloads
        public Writer(@NotNull Buffer out) {
            this(0, false, out, 3, null);
        }
    }
}

