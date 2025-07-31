/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okhttp3.internal.cache2;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.Util;
import okhttp3.internal.cache2.FileOperator;
import okio.Buffer;
import okio.ByteString;
import okio.Source;
import okio.Timeout;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\t\u0018\u0000 :2\u00020\u0001:\u0002:;B3\b\u0002\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\u000bJ\u000e\u00102\u001a\u0002032\u0006\u00104\u001a\u00020\u0007J\u0006\u0010\b\u001a\u00020\tJ\b\u00105\u001a\u0004\u0018\u00010\u0005J \u00106\u001a\u0002032\u0006\u00107\u001a\u00020\t2\u0006\u00104\u001a\u00020\u00072\u0006\u00108\u001a\u00020\u0007H\u0002J\u0010\u00109\u001a\u0002032\u0006\u00104\u001a\u00020\u0007H\u0002R\u0011\u0010\f\u001a\u00020\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\n\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\u0013X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u0011\u0010\u001c\u001a\u00020\u00138F\u00a2\u0006\u0006\u001a\u0004\b\u001c\u0010\u0015R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u001d\u001a\u00020\u001eX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u0011\u0010'\u001a\u00020\r\u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010\u000fR\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b)\u0010\u0011\"\u0004\b*\u0010+R\u001c\u0010,\u001a\u0004\u0018\u00010-X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b.\u0010/\"\u0004\b0\u00101\u00a8\u0006<"}, d2={"Lokhttp3/internal/cache2/Relay;", "", "file", "Ljava/io/RandomAccessFile;", "upstream", "Lokio/Source;", "upstreamPos", "", "metadata", "Lokio/ByteString;", "bufferMaxSize", "(Ljava/io/RandomAccessFile;Lokio/Source;JLokio/ByteString;J)V", "buffer", "Lokio/Buffer;", "getBuffer", "()Lokio/Buffer;", "getBufferMaxSize", "()J", "complete", "", "getComplete", "()Z", "setComplete", "(Z)V", "getFile", "()Ljava/io/RandomAccessFile;", "setFile", "(Ljava/io/RandomAccessFile;)V", "isClosed", "sourceCount", "", "getSourceCount", "()I", "setSourceCount", "(I)V", "getUpstream", "()Lokio/Source;", "setUpstream", "(Lokio/Source;)V", "upstreamBuffer", "getUpstreamBuffer", "getUpstreamPos", "setUpstreamPos", "(J)V", "upstreamReader", "Ljava/lang/Thread;", "getUpstreamReader", "()Ljava/lang/Thread;", "setUpstreamReader", "(Ljava/lang/Thread;)V", "commit", "", "upstreamSize", "newSource", "writeHeader", "prefix", "metadataSize", "writeMetadata", "Companion", "RelaySource", "okhttp"})
public final class Relay {
    @Nullable
    private Thread upstreamReader;
    @NotNull
    private final Buffer upstreamBuffer;
    private boolean complete;
    @NotNull
    private final Buffer buffer;
    private int sourceCount;
    @Nullable
    private RandomAccessFile file;
    @Nullable
    private Source upstream;
    private long upstreamPos;
    private final ByteString metadata;
    private final long bufferMaxSize;
    private static final int SOURCE_UPSTREAM = 1;
    private static final int SOURCE_FILE = 2;
    @JvmField
    @NotNull
    public static final ByteString PREFIX_CLEAN;
    @JvmField
    @NotNull
    public static final ByteString PREFIX_DIRTY;
    private static final long FILE_HEADER_SIZE = 32L;
    public static final Companion Companion;

    @Nullable
    public final Thread getUpstreamReader() {
        return this.upstreamReader;
    }

    public final void setUpstreamReader(@Nullable Thread thread2) {
        this.upstreamReader = thread2;
    }

    @NotNull
    public final Buffer getUpstreamBuffer() {
        return this.upstreamBuffer;
    }

    public final boolean getComplete() {
        return this.complete;
    }

    public final void setComplete(boolean bl) {
        this.complete = bl;
    }

    @NotNull
    public final Buffer getBuffer() {
        return this.buffer;
    }

    public final int getSourceCount() {
        return this.sourceCount;
    }

    public final void setSourceCount(int n) {
        this.sourceCount = n;
    }

    public final boolean isClosed() {
        return this.file == null;
    }

    private final void writeHeader(ByteString prefix, long upstreamSize, long metadataSize) throws IOException {
        Buffer buffer = new Buffer();
        boolean bl = false;
        boolean bl2 = false;
        Buffer $this$apply = buffer;
        boolean bl3 = false;
        $this$apply.write(prefix);
        $this$apply.writeLong(upstreamSize);
        $this$apply.writeLong(metadataSize);
        boolean bl4 = $this$apply.size() == 32L;
        boolean bl5 = false;
        boolean bl6 = false;
        bl6 = false;
        boolean bl7 = false;
        if (!bl4) {
            boolean bl8 = false;
            String string = "Failed requirement.";
            throw (Throwable)new IllegalArgumentException(string.toString());
        }
        Buffer header = buffer;
        RandomAccessFile randomAccessFile = this.file;
        Intrinsics.checkNotNull(randomAccessFile);
        FileChannel fileChannel = randomAccessFile.getChannel();
        Intrinsics.checkNotNullExpressionValue(fileChannel, "file!!.channel");
        FileOperator fileOperator = new FileOperator(fileChannel);
        fileOperator.write(0L, header, 32L);
    }

    private final void writeMetadata(long upstreamSize) throws IOException {
        Buffer metadataBuffer = new Buffer();
        metadataBuffer.write(this.metadata);
        RandomAccessFile randomAccessFile = this.file;
        Intrinsics.checkNotNull(randomAccessFile);
        FileChannel fileChannel = randomAccessFile.getChannel();
        Intrinsics.checkNotNullExpressionValue(fileChannel, "file!!.channel");
        FileOperator fileOperator = new FileOperator(fileChannel);
        fileOperator.write(32L + upstreamSize, metadataBuffer, this.metadata.size());
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final void commit(long upstreamSize) throws IOException {
        this.writeMetadata(upstreamSize);
        RandomAccessFile randomAccessFile = this.file;
        Intrinsics.checkNotNull(randomAccessFile);
        randomAccessFile.getChannel().force(false);
        this.writeHeader(PREFIX_CLEAN, upstreamSize, this.metadata.size());
        RandomAccessFile randomAccessFile2 = this.file;
        Intrinsics.checkNotNull(randomAccessFile2);
        randomAccessFile2.getChannel().force(false);
        Relay relay = this;
        boolean bl = false;
        boolean bl2 = false;
        synchronized (relay) {
            boolean bl3 = false;
            this.complete = true;
            Unit unit = Unit.INSTANCE;
        }
        Source source2 = this.upstream;
        if (source2 != null) {
            Util.closeQuietly(source2);
        }
        this.upstream = null;
    }

    @NotNull
    public final ByteString metadata() {
        return this.metadata;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Nullable
    public final Source newSource() {
        Relay relay = this;
        boolean bl = false;
        int n = 0;
        synchronized (relay) {
            block4: {
                boolean bl2 = false;
                if (this.file != null) break block4;
                Source source2 = null;
                return source2;
            }
            int n2 = this.sourceCount;
            this.sourceCount = n2 + 1;
            n = n2;
        }
        return new RelaySource();
    }

    @Nullable
    public final RandomAccessFile getFile() {
        return this.file;
    }

    public final void setFile(@Nullable RandomAccessFile randomAccessFile) {
        this.file = randomAccessFile;
    }

    @Nullable
    public final Source getUpstream() {
        return this.upstream;
    }

    public final void setUpstream(@Nullable Source source2) {
        this.upstream = source2;
    }

    public final long getUpstreamPos() {
        return this.upstreamPos;
    }

    public final void setUpstreamPos(long l) {
        this.upstreamPos = l;
    }

    public final long getBufferMaxSize() {
        return this.bufferMaxSize;
    }

    private Relay(RandomAccessFile file, Source upstream, long upstreamPos, ByteString metadata, long bufferMaxSize) {
        this.file = file;
        this.upstream = upstream;
        this.upstreamPos = upstreamPos;
        this.metadata = metadata;
        this.bufferMaxSize = bufferMaxSize;
        this.upstreamBuffer = new Buffer();
        this.complete = this.upstream == null;
        this.buffer = new Buffer();
    }

    static {
        Companion = new Companion(null);
        PREFIX_CLEAN = ByteString.Companion.encodeUtf8("OkHttp cache v1\n");
        PREFIX_DIRTY = ByteString.Companion.encodeUtf8("OkHttp DIRTY :(\n");
    }

    public /* synthetic */ Relay(RandomAccessFile file, Source upstream, long upstreamPos, ByteString metadata, long bufferMaxSize, DefaultConstructorMarker $constructor_marker) {
        this(file, upstream, upstreamPos, metadata, bufferMaxSize);
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0080\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\t\u001a\u00020\nH\u0016J\u0018\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0006H\u0016J\b\u0010\u0007\u001a\u00020\bH\u0016R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2={"Lokhttp3/internal/cache2/Relay$RelaySource;", "Lokio/Source;", "(Lokhttp3/internal/cache2/Relay;)V", "fileOperator", "Lokhttp3/internal/cache2/FileOperator;", "sourcePos", "", "timeout", "Lokio/Timeout;", "close", "", "read", "sink", "Lokio/Buffer;", "byteCount", "okhttp"})
    public final class RelaySource
    implements Source {
        private final Timeout timeout = new Timeout();
        private FileOperator fileOperator;
        private long sourcePos;

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        @Override
        public long read(@NotNull Buffer sink2, long byteCount) throws IOException {
            Object object;
            long upstreamBytesRead;
            block24: {
                Relay bufferPos3222;
                long l;
                Intrinsics.checkNotNullParameter(sink2, "sink");
                boolean bl = this.fileOperator != null;
                boolean bl2 = false;
                boolean bl3 = false;
                bl3 = false;
                int n = 0;
                if (!bl) {
                    boolean bl4 = false;
                    String string = "Check failed.";
                    throw (Throwable)new IllegalStateException(string.toString());
                }
                Relay relay = Relay.this;
                bl3 = false;
                n = 0;
                synchronized (relay) {
                    int n2;
                    block25: {
                        block23: {
                            boolean bl5 = false;
                            while (true) {
                                long upstreamPos;
                                if (this.sourcePos != (upstreamPos = Relay.this.getUpstreamPos())) {
                                    long bufferPos3222 = Relay.this.getUpstreamPos() - Relay.this.getBuffer().size();
                                    if (this.sourcePos >= bufferPos3222) {
                                        long l2 = byteCount;
                                        long l3 = Relay.this.getUpstreamPos() - this.sourcePos;
                                        boolean bl6 = false;
                                        long bytesToRead = Math.min(l2, l3);
                                        Relay.this.getBuffer().copyTo(sink2, this.sourcePos - bufferPos3222, bytesToRead);
                                        this.sourcePos += bytesToRead;
                                        return bytesToRead;
                                    }
                                    break block23;
                                }
                                if (Relay.this.getComplete()) {
                                    return -1L;
                                }
                                if (Relay.this.getUpstreamReader() == null) break;
                                this.timeout.waitUntilNotified(Relay.this);
                            }
                            Relay.this.setUpstreamReader(Thread.currentThread());
                            n2 = 1;
                            break block25;
                        }
                        n2 = 2;
                    }
                    n = n2;
                }
                int source2 = n;
                if (source2 == 2) {
                    long l4 = Relay.this.getUpstreamPos() - this.sourcePos;
                    boolean bufferPos3222 = false;
                    long bytesToRead = Math.min(byteCount, l4);
                    FileOperator fileOperator = this.fileOperator;
                    Intrinsics.checkNotNull(fileOperator);
                    fileOperator.read(32L + this.sourcePos, sink2, bytesToRead);
                    this.sourcePos += bytesToRead;
                    return bytesToRead;
                }
                try {
                    Source source3 = Relay.this.getUpstream();
                    Intrinsics.checkNotNull(source3);
                    upstreamBytesRead = source3.read(Relay.this.getUpstreamBuffer(), Relay.this.getBufferMaxSize());
                    if (upstreamBytesRead != -1L) break block24;
                    Relay.this.commit(Relay.this.getUpstreamPos());
                    l = -1L;
                    bufferPos3222 = Relay.this;
                    boolean bl7 = false;
                    boolean bl8 = false;
                } catch (Throwable throwable) {
                    Relay relay2 = Relay.this;
                    n = 0;
                    boolean bl9 = false;
                    synchronized (relay2) {
                        boolean bl10 = false;
                        Relay.this.setUpstreamReader(null);
                        Relay $this$notifyAll$iv = Relay.this;
                        boolean $i$f$notifyAll = false;
                        Relay relay3 = $this$notifyAll$iv;
                        if (relay3 == null) {
                            throw new NullPointerException("null cannot be cast to non-null type java.lang.Object");
                        }
                        ((Object)relay3).notifyAll();
                        Unit unit = Unit.INSTANCE;
                        throw throwable;
                    }
                }
                synchronized (bufferPos3222) {
                    boolean bl11 = false;
                    Relay.this.setUpstreamReader(null);
                    Relay $this$notifyAll$iv = Relay.this;
                    boolean $i$f$notifyAll = false;
                    Relay relay4 = $this$notifyAll$iv;
                    if (relay4 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type java.lang.Object");
                    }
                    ((Object)relay4).notifyAll();
                    Unit unit = Unit.INSTANCE;
                    return l;
                }
            }
            boolean bufferPos3222 = false;
            long bytesRead = Math.min(upstreamBytesRead, byteCount);
            Relay.this.getUpstreamBuffer().copyTo(sink2, 0L, bytesRead);
            this.sourcePos += bytesRead;
            FileOperator fileOperator = this.fileOperator;
            Intrinsics.checkNotNull(fileOperator);
            fileOperator.write(32L + Relay.this.getUpstreamPos(), Relay.this.getUpstreamBuffer().clone(), upstreamBytesRead);
            Relay bufferPos3222 = Relay.this;
            boolean bl = false;
            boolean bl12 = false;
            synchronized (bufferPos3222) {
                boolean bl13 = false;
                Relay.this.getBuffer().write(Relay.this.getUpstreamBuffer(), upstreamBytesRead);
                if (Relay.this.getBuffer().size() > Relay.this.getBufferMaxSize()) {
                    Relay.this.getBuffer().skip(Relay.this.getBuffer().size() - Relay.this.getBufferMaxSize());
                }
                Relay relay = Relay.this;
                relay.setUpstreamPos(relay.getUpstreamPos() + upstreamBytesRead);
                object = Unit.INSTANCE;
            }
            long bufferPos3222 = bytesRead;
            object = Relay.this;
            boolean bl14 = false;
            boolean bl15 = false;
            synchronized (object) {
                boolean bl16 = false;
                Relay.this.setUpstreamReader(null);
                Relay $this$notifyAll$iv = Relay.this;
                boolean $i$f$notifyAll = false;
                Relay relay = $this$notifyAll$iv;
                if (relay == null) {
                    throw new NullPointerException("null cannot be cast to non-null type java.lang.Object");
                }
                ((Object)relay).notifyAll();
                Unit unit = Unit.INSTANCE;
                return bufferPos3222;
            }
        }

        @Override
        @NotNull
        public Timeout timeout() {
            return this.timeout;
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void close() throws IOException {
            block5: {
                if (this.fileOperator == null) {
                    return;
                }
                this.fileOperator = null;
                RandomAccessFile fileToClose = null;
                Relay relay = Relay.this;
                boolean bl = false;
                boolean bl2 = false;
                synchronized (relay) {
                    boolean bl3 = false;
                    Relay relay2 = Relay.this;
                    int n = relay2.getSourceCount();
                    relay2.setSourceCount(n + -1);
                    if (Relay.this.getSourceCount() == 0) {
                        fileToClose = Relay.this.getFile();
                        Relay.this.setFile(null);
                    }
                    Unit unit = Unit.INSTANCE;
                }
                RandomAccessFile randomAccessFile = fileToClose;
                if (randomAccessFile == null) break block5;
                Util.closeQuietly(randomAccessFile);
            }
        }

        public RelaySource() {
            RandomAccessFile randomAccessFile = Relay.this.getFile();
            Intrinsics.checkNotNull(randomAccessFile);
            FileChannel fileChannel = randomAccessFile.getChannel();
            Intrinsics.checkNotNullExpressionValue(fileChannel, "file!!.channel");
            this.fileOperator = new FileOperator(fileChannel);
        }
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J&\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u0004J\u000e\u0010\u0013\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u00020\u00068\u0006X\u0087\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u00020\u00068\u0006X\u0087\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2={"Lokhttp3/internal/cache2/Relay$Companion;", "", "()V", "FILE_HEADER_SIZE", "", "PREFIX_CLEAN", "Lokio/ByteString;", "PREFIX_DIRTY", "SOURCE_FILE", "", "SOURCE_UPSTREAM", "edit", "Lokhttp3/internal/cache2/Relay;", "file", "Ljava/io/File;", "upstream", "Lokio/Source;", "metadata", "bufferMaxSize", "read", "okhttp"})
    public static final class Companion {
        @NotNull
        public final Relay edit(@NotNull File file, @NotNull Source upstream, @NotNull ByteString metadata, long bufferMaxSize) throws IOException {
            Intrinsics.checkNotNullParameter(file, "file");
            Intrinsics.checkNotNullParameter(upstream, "upstream");
            Intrinsics.checkNotNullParameter(metadata, "metadata");
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
            Relay result = new Relay(randomAccessFile, upstream, 0L, metadata, bufferMaxSize, null);
            randomAccessFile.setLength(0L);
            result.writeHeader(PREFIX_DIRTY, -1L, -1L);
            return result;
        }

        @NotNull
        public final Relay read(@NotNull File file) throws IOException {
            Intrinsics.checkNotNullParameter(file, "file");
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
            FileChannel fileChannel = randomAccessFile.getChannel();
            Intrinsics.checkNotNullExpressionValue(fileChannel, "randomAccessFile.channel");
            FileOperator fileOperator = new FileOperator(fileChannel);
            Buffer header = new Buffer();
            fileOperator.read(0L, header, 32L);
            ByteString prefix = header.readByteString(PREFIX_CLEAN.size());
            if (Intrinsics.areEqual(prefix, PREFIX_CLEAN) ^ true) {
                throw (Throwable)new IOException("unreadable cache file");
            }
            long upstreamSize = header.readLong();
            long metadataSize = header.readLong();
            Buffer metadataBuffer = new Buffer();
            fileOperator.read(32L + upstreamSize, metadataBuffer, metadataSize);
            ByteString metadata = metadataBuffer.readByteString();
            return new Relay(randomAccessFile, null, upstreamSize, metadata, 0L, null);
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

