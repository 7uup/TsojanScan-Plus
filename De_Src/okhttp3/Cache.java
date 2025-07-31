/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okhttp3;

import java.io.Closeable;
import java.io.File;
import java.io.Flushable;
import java.io.IOException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.TreeSet;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;
import okhttp3.CipherSuite;
import okhttp3.Handshake;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.TlsVersion;
import okhttp3.internal.Util;
import okhttp3.internal.cache.CacheRequest;
import okhttp3.internal.cache.CacheStrategy;
import okhttp3.internal.cache.DiskLruCache;
import okhttp3.internal.concurrent.TaskRunner;
import okhttp3.internal.http.HttpMethod;
import okhttp3.internal.http.StatusLine;
import okhttp3.internal.io.FileSystem;
import okhttp3.internal.platform.Platform;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ByteString;
import okio.ForwardingSink;
import okio.ForwardingSource;
import okio.Okio;
import okio.Sink;
import okio.Source;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010)\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u0000 C2\u00020\u00012\u00020\u0002:\u0004BCDEB\u0017\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007B\u001f\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\u0016\u0010\u001f\u001a\u00020 2\f\u0010!\u001a\b\u0018\u00010\"R\u00020\fH\u0002J\b\u0010#\u001a\u00020 H\u0016J\u0006\u0010$\u001a\u00020 J\r\u0010\u0003\u001a\u00020\u0004H\u0007\u00a2\u0006\u0002\b%J\u0006\u0010&\u001a\u00020 J\b\u0010'\u001a\u00020 H\u0016J\u0017\u0010(\u001a\u0004\u0018\u00010)2\u0006\u0010*\u001a\u00020+H\u0000\u00a2\u0006\u0002\b,J\u0006\u0010\u0010\u001a\u00020\u0011J\u0006\u0010-\u001a\u00020 J\u0006\u0010\u0005\u001a\u00020\u0006J\u0006\u0010\u0015\u001a\u00020\u0011J\u0017\u0010.\u001a\u0004\u0018\u00010/2\u0006\u00100\u001a\u00020)H\u0000\u00a2\u0006\u0002\b1J\u0015\u00102\u001a\u00020 2\u0006\u0010*\u001a\u00020+H\u0000\u00a2\u0006\u0002\b3J\u0006\u0010\u0016\u001a\u00020\u0011J\u0006\u00104\u001a\u00020\u0006J\r\u00105\u001a\u00020 H\u0000\u00a2\u0006\u0002\b6J\u0015\u00107\u001a\u00020 2\u0006\u00108\u001a\u000209H\u0000\u00a2\u0006\u0002\b:J\u001d\u0010;\u001a\u00020 2\u0006\u0010<\u001a\u00020)2\u0006\u0010=\u001a\u00020)H\u0000\u00a2\u0006\u0002\b>J\f\u0010?\u001a\b\u0012\u0004\u0012\u00020A0@J\u0006\u0010\u0017\u001a\u00020\u0011J\u0006\u0010\u001c\u001a\u00020\u0011R\u0014\u0010\u000b\u001a\u00020\fX\u0080\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0003\u001a\u00020\u00048G\u00a2\u0006\u0006\u001a\u0004\b\u0003\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0012\u001a\u00020\u00138F\u00a2\u0006\u0006\u001a\u0004\b\u0012\u0010\u0014R\u000e\u0010\u0015\u001a\u00020\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0017\u001a\u00020\u0011X\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001a\u0010\u001c\u001a\u00020\u0011X\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0019\"\u0004\b\u001e\u0010\u001b\u00a8\u0006F"}, d2={"Lokhttp3/Cache;", "Ljava/io/Closeable;", "Ljava/io/Flushable;", "directory", "Ljava/io/File;", "maxSize", "", "(Ljava/io/File;J)V", "fileSystem", "Lokhttp3/internal/io/FileSystem;", "(Ljava/io/File;JLokhttp3/internal/io/FileSystem;)V", "cache", "Lokhttp3/internal/cache/DiskLruCache;", "getCache$okhttp", "()Lokhttp3/internal/cache/DiskLruCache;", "()Ljava/io/File;", "hitCount", "", "isClosed", "", "()Z", "networkCount", "requestCount", "writeAbortCount", "getWriteAbortCount$okhttp", "()I", "setWriteAbortCount$okhttp", "(I)V", "writeSuccessCount", "getWriteSuccessCount$okhttp", "setWriteSuccessCount$okhttp", "abortQuietly", "", "editor", "Lokhttp3/internal/cache/DiskLruCache$Editor;", "close", "delete", "-deprecated_directory", "evictAll", "flush", "get", "Lokhttp3/Response;", "request", "Lokhttp3/Request;", "get$okhttp", "initialize", "put", "Lokhttp3/internal/cache/CacheRequest;", "response", "put$okhttp", "remove", "remove$okhttp", "size", "trackConditionalCacheHit", "trackConditionalCacheHit$okhttp", "trackResponse", "cacheStrategy", "Lokhttp3/internal/cache/CacheStrategy;", "trackResponse$okhttp", "update", "cached", "network", "update$okhttp", "urls", "", "", "CacheResponseBody", "Companion", "Entry", "RealCacheRequest", "okhttp"})
public final class Cache
implements Closeable,
Flushable {
    @NotNull
    private final DiskLruCache cache;
    private int writeSuccessCount;
    private int writeAbortCount;
    private int networkCount;
    private int hitCount;
    private int requestCount;
    private static final int VERSION = 201105;
    private static final int ENTRY_METADATA = 0;
    private static final int ENTRY_BODY = 1;
    private static final int ENTRY_COUNT = 2;
    public static final Companion Companion = new Companion(null);

    @NotNull
    public final DiskLruCache getCache$okhttp() {
        return this.cache;
    }

    public final int getWriteSuccessCount$okhttp() {
        return this.writeSuccessCount;
    }

    public final void setWriteSuccessCount$okhttp(int n) {
        this.writeSuccessCount = n;
    }

    public final int getWriteAbortCount$okhttp() {
        return this.writeAbortCount;
    }

    public final void setWriteAbortCount$okhttp(int n) {
        this.writeAbortCount = n;
    }

    public final boolean isClosed() {
        return this.cache.isClosed();
    }

    @Nullable
    public final Response get$okhttp(@NotNull Request request) {
        Entry _2;
        DiskLruCache.Snapshot snapshot;
        Intrinsics.checkNotNullParameter(request, "request");
        String key = Companion.key(request.url());
        try {
            DiskLruCache.Snapshot snapshot2 = this.cache.get(key);
            if (snapshot2 == null) {
                return null;
            }
            snapshot = snapshot2;
        } catch (IOException _2) {
            return null;
        }
        DiskLruCache.Snapshot snapshot3 = snapshot;
        try {
            _2 = new Entry(snapshot3.getSource(0));
        } catch (IOException _3) {
            Util.closeQuietly(snapshot3);
            return null;
        }
        Entry entry = _2;
        Response response = entry.response(snapshot3);
        if (!entry.matches(request, response)) {
            ResponseBody responseBody = response.body();
            if (responseBody != null) {
                Util.closeQuietly(responseBody);
            }
            return null;
        }
        return response;
    }

    @Nullable
    public final CacheRequest put$okhttp(@NotNull Response response) {
        Intrinsics.checkNotNullParameter(response, "response");
        String requestMethod = response.request().method();
        if (HttpMethod.INSTANCE.invalidatesCache(response.request().method())) {
            try {
                this.remove$okhttp(response.request());
            } catch (IOException iOException) {
                // empty catch block
            }
            return null;
        }
        if (Intrinsics.areEqual(requestMethod, "GET") ^ true) {
            return null;
        }
        if (Companion.hasVaryAll(response)) {
            return null;
        }
        Entry entry = new Entry(response);
        DiskLruCache.Editor editor = null;
        try {
            DiskLruCache.Editor editor2 = DiskLruCache.edit$default(this.cache, Companion.key(response.request().url()), 0L, 2, null);
            if (editor2 == null) {
                return null;
            }
            editor = editor2;
            entry.writeTo(editor);
            return new RealCacheRequest(editor);
        } catch (IOException _) {
            this.abortQuietly(editor);
            return null;
        }
    }

    public final void remove$okhttp(@NotNull Request request) throws IOException {
        Intrinsics.checkNotNullParameter(request, "request");
        this.cache.remove(Companion.key(request.url()));
    }

    public final void update$okhttp(@NotNull Response cached, @NotNull Response network) {
        Intrinsics.checkNotNullParameter(cached, "cached");
        Intrinsics.checkNotNullParameter(network, "network");
        Entry entry = new Entry(network);
        ResponseBody responseBody = cached.body();
        if (responseBody == null) {
            throw new NullPointerException("null cannot be cast to non-null type okhttp3.Cache.CacheResponseBody");
        }
        DiskLruCache.Snapshot snapshot = ((CacheResponseBody)responseBody).getSnapshot();
        DiskLruCache.Editor editor = null;
        try {
            DiskLruCache.Editor editor2 = snapshot.edit();
            if (editor2 == null) {
                return;
            }
            editor = editor2;
            entry.writeTo(editor);
            editor.commit();
        } catch (IOException _) {
            this.abortQuietly(editor);
        }
    }

    private final void abortQuietly(DiskLruCache.Editor editor) {
        try {
            DiskLruCache.Editor editor2 = editor;
            if (editor2 != null) {
                editor2.abort();
            }
        } catch (IOException iOException) {
            // empty catch block
        }
    }

    public final void initialize() throws IOException {
        this.cache.initialize();
    }

    public final void delete() throws IOException {
        this.cache.delete();
    }

    public final void evictAll() throws IOException {
        this.cache.evictAll();
    }

    @NotNull
    public final Iterator<String> urls() throws IOException {
        return new Iterator<String>(this){
            private final Iterator<DiskLruCache.Snapshot> delegate;
            private String nextUrl;
            private boolean canRemove;
            final /* synthetic */ Cache this$0;

            /*
             * WARNING - Removed try catching itself - possible behaviour change.
             */
            public boolean hasNext() {
                if (this.nextUrl != null) {
                    return true;
                }
                this.canRemove = false;
                while (this.delegate.hasNext()) {
                    try {
                        Closeable closeable = this.delegate.next();
                        boolean bl = false;
                        boolean bl2 = false;
                        Throwable throwable = null;
                        try {
                            DiskLruCache.Snapshot snapshot = (DiskLruCache.Snapshot)closeable;
                            boolean bl3 = false;
                            BufferedSource metadata = Okio.buffer(snapshot.getSource(0));
                            this.nextUrl = metadata.readUtf8LineStrict();
                            boolean bl4 = true;
                            return bl4;
                        } catch (Throwable throwable2) {
                            throwable = throwable2;
                            throw throwable2;
                        } finally {
                            CloseableKt.closeFinally(closeable, throwable);
                        }
                    } catch (IOException iOException) {
                    }
                }
                return false;
            }

            @NotNull
            public String next() {
                if (!this.hasNext()) {
                    throw (Throwable)new NoSuchElementException();
                }
                String string = this.nextUrl;
                Intrinsics.checkNotNull(string);
                String result = string;
                this.nextUrl = null;
                this.canRemove = true;
                return result;
            }

            public void remove() {
                boolean bl = this.canRemove;
                boolean bl2 = false;
                boolean bl3 = false;
                if (!bl) {
                    boolean bl4 = false;
                    String string = "remove() before next()";
                    throw (Throwable)new IllegalStateException(string.toString());
                }
                this.delegate.remove();
            }
            {
                this.this$0 = this$0;
                this.delegate = this$0.getCache$okhttp().snapshots();
            }
        };
    }

    public final synchronized int writeAbortCount() {
        return this.writeAbortCount;
    }

    public final synchronized int writeSuccessCount() {
        return this.writeSuccessCount;
    }

    public final long size() throws IOException {
        return this.cache.size();
    }

    public final long maxSize() {
        return this.cache.getMaxSize();
    }

    @Override
    public void flush() throws IOException {
        this.cache.flush();
    }

    @Override
    public void close() throws IOException {
        this.cache.close();
    }

    @JvmName(name="directory")
    @NotNull
    public final File directory() {
        return this.cache.getDirectory();
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="directory"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_directory")
    @NotNull
    public final File -deprecated_directory() {
        return this.cache.getDirectory();
    }

    public final synchronized void trackResponse$okhttp(@NotNull CacheStrategy cacheStrategy) {
        Intrinsics.checkNotNullParameter(cacheStrategy, "cacheStrategy");
        int n = this.requestCount;
        this.requestCount = n + 1;
        if (cacheStrategy.getNetworkRequest() != null) {
            n = this.networkCount;
            this.networkCount = n + 1;
        } else if (cacheStrategy.getCacheResponse() != null) {
            n = this.hitCount;
            this.hitCount = n + 1;
        }
    }

    public final synchronized void trackConditionalCacheHit$okhttp() {
        int n = this.hitCount;
        this.hitCount = n + 1;
    }

    public final synchronized int networkCount() {
        return this.networkCount;
    }

    public final synchronized int hitCount() {
        return this.hitCount;
    }

    public final synchronized int requestCount() {
        return this.requestCount;
    }

    public Cache(@NotNull File directory, long maxSize, @NotNull FileSystem fileSystem) {
        Intrinsics.checkNotNullParameter(directory, "directory");
        Intrinsics.checkNotNullParameter(fileSystem, "fileSystem");
        this.cache = new DiskLruCache(fileSystem, directory, 201105, 2, maxSize, TaskRunner.INSTANCE);
    }

    public Cache(@NotNull File directory, long maxSize) {
        Intrinsics.checkNotNullParameter(directory, "directory");
        this(directory, maxSize, FileSystem.SYSTEM);
    }

    @JvmStatic
    @NotNull
    public static final String key(@NotNull HttpUrl url) {
        return Companion.key(url);
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0011\u0012\n\u0010\u0002\u001a\u00060\u0003R\u00020\u0004\u00a2\u0006\u0002\u0010\u0005J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0006\u001a\u00020\u0007H\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u00020\nX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u0012\u0010\u0002\u001a\u00060\u0003R\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2={"Lokhttp3/Cache$RealCacheRequest;", "Lokhttp3/internal/cache/CacheRequest;", "editor", "Lokhttp3/internal/cache/DiskLruCache$Editor;", "Lokhttp3/internal/cache/DiskLruCache;", "(Lokhttp3/Cache;Lokhttp3/internal/cache/DiskLruCache$Editor;)V", "body", "Lokio/Sink;", "cacheOut", "done", "", "getDone", "()Z", "setDone", "(Z)V", "abort", "", "okhttp"})
    private final class RealCacheRequest
    implements CacheRequest {
        private final Sink cacheOut;
        private final Sink body;
        private boolean done;
        private final DiskLruCache.Editor editor;

        public final boolean getDone() {
            return this.done;
        }

        public final void setDone(boolean bl) {
            this.done = bl;
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void abort() {
            Cache cache = Cache.this;
            boolean bl = false;
            int n = 0;
            synchronized (cache) {
                boolean bl2 = false;
                if (this.done) {
                    return;
                }
                this.done = true;
                Cache cache2 = Cache.this;
                int n2 = cache2.getWriteAbortCount$okhttp();
                cache2.setWriteAbortCount$okhttp(n2 + 1);
                n = n2;
            }
            Util.closeQuietly(this.cacheOut);
            try {
                this.editor.abort();
            } catch (IOException iOException) {
                // empty catch block
            }
        }

        @Override
        @NotNull
        public Sink body() {
            return this.body;
        }

        public RealCacheRequest(DiskLruCache.Editor editor) {
            Intrinsics.checkNotNullParameter(editor, "editor");
            this.editor = editor;
            this.cacheOut = this.editor.newSink(1);
            this.body = new ForwardingSink(this.cacheOut){

                /*
                 * WARNING - Removed try catching itself - possible behaviour change.
                 */
                @Override
                public void close() throws IOException {
                    Cache cache = Cache.this;
                    boolean bl = false;
                    int n = 0;
                    synchronized (cache) {
                        boolean bl2 = false;
                        if (this.getDone()) {
                            return;
                        }
                        this.setDone(true);
                        Cache cache2 = Cache.this;
                        int n2 = cache2.getWriteSuccessCount$okhttp();
                        cache2.setWriteSuccessCount$okhttp(n2 + 1);
                        n = n2;
                    }
                    super.close();
                    editor.commit();
                }
            };
        }
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u0000 .2\u00020\u0001:\u0001.B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004B\u000f\b\u0016\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\u0016\u0010\u001b\u001a\u00020\r2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u0005\u001a\u00020\u0006J\u0016\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020 0\u001f2\u0006\u0010!\u001a\u00020\"H\u0002J\u0012\u0010\u0005\u001a\u00020\u00062\n\u0010#\u001a\u00060$R\u00020%J\u001e\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020)2\f\u0010*\u001a\b\u0012\u0004\u0012\u00020 0\u001fH\u0002J\u0012\u0010+\u001a\u00020'2\n\u0010,\u001a\u00060-R\u00020%R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\u00020\r8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\f\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0014X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0017X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006/"}, d2={"Lokhttp3/Cache$Entry;", "", "rawSource", "Lokio/Source;", "(Lokio/Source;)V", "response", "Lokhttp3/Response;", "(Lokhttp3/Response;)V", "code", "", "handshake", "Lokhttp3/Handshake;", "isHttps", "", "()Z", "message", "", "protocol", "Lokhttp3/Protocol;", "receivedResponseMillis", "", "requestMethod", "responseHeaders", "Lokhttp3/Headers;", "sentRequestMillis", "url", "varyHeaders", "matches", "request", "Lokhttp3/Request;", "readCertificateList", "", "Ljava/security/cert/Certificate;", "source", "Lokio/BufferedSource;", "snapshot", "Lokhttp3/internal/cache/DiskLruCache$Snapshot;", "Lokhttp3/internal/cache/DiskLruCache;", "writeCertList", "", "sink", "Lokio/BufferedSink;", "certificates", "writeTo", "editor", "Lokhttp3/internal/cache/DiskLruCache$Editor;", "Companion", "okhttp"})
    private static final class Entry {
        private final String url;
        private final Headers varyHeaders;
        private final String requestMethod;
        private final Protocol protocol;
        private final int code;
        private final String message;
        private final Headers responseHeaders;
        private final Handshake handshake;
        private final long sentRequestMillis;
        private final long receivedResponseMillis;
        private static final String SENT_MILLIS;
        private static final String RECEIVED_MILLIS;
        public static final Companion Companion;

        private final boolean isHttps() {
            return StringsKt.startsWith$default(this.url, "https://", false, 2, null);
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        public final void writeTo(@NotNull DiskLruCache.Editor editor) throws IOException {
            Intrinsics.checkNotNullParameter(editor, "editor");
            Closeable closeable = Okio.buffer(editor.newSink(0));
            boolean bl = false;
            boolean bl2 = false;
            Throwable throwable = null;
            try {
                int i;
                BufferedSink sink2 = (BufferedSink)closeable;
                boolean bl3 = false;
                sink2.writeUtf8(this.url).writeByte(10);
                sink2.writeUtf8(this.requestMethod).writeByte(10);
                sink2.writeDecimalLong(this.varyHeaders.size()).writeByte(10);
                int n = 0;
                int n2 = this.varyHeaders.size();
                while (n < n2) {
                    sink2.writeUtf8(this.varyHeaders.name(i)).writeUtf8(": ").writeUtf8(this.varyHeaders.value(i)).writeByte(10);
                    ++i;
                }
                sink2.writeUtf8(new StatusLine(this.protocol, this.code, this.message).toString()).writeByte(10);
                sink2.writeDecimalLong(this.responseHeaders.size() + 2).writeByte(10);
                n2 = this.responseHeaders.size();
                for (i = 0; i < n2; ++i) {
                    sink2.writeUtf8(this.responseHeaders.name(i)).writeUtf8(": ").writeUtf8(this.responseHeaders.value(i)).writeByte(10);
                }
                sink2.writeUtf8(SENT_MILLIS).writeUtf8(": ").writeDecimalLong(this.sentRequestMillis).writeByte(10);
                sink2.writeUtf8(RECEIVED_MILLIS).writeUtf8(": ").writeDecimalLong(this.receivedResponseMillis).writeByte(10);
                if (this.isHttps()) {
                    sink2.writeByte(10);
                    Handshake handshake2 = this.handshake;
                    Intrinsics.checkNotNull(handshake2);
                    sink2.writeUtf8(handshake2.cipherSuite().javaName()).writeByte(10);
                    this.writeCertList(sink2, this.handshake.peerCertificates());
                    this.writeCertList(sink2, this.handshake.localCertificates());
                    sink2.writeUtf8(this.handshake.tlsVersion().javaName()).writeByte(10);
                }
                Unit unit = Unit.INSTANCE;
            } catch (Throwable throwable2) {
                throwable = throwable2;
                throw throwable2;
            } finally {
                CloseableKt.closeFinally(closeable, throwable);
            }
        }

        /*
         * WARNING - void declaration
         */
        private final List<Certificate> readCertificateList(BufferedSource source2) throws IOException {
            int length = Companion.readInt$okhttp(source2);
            if (length == -1) {
                return CollectionsKt.emptyList();
            }
            try {
                CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
                ArrayList<Certificate> result = new ArrayList<Certificate>(length);
                int n = 0;
                int n2 = length;
                while (n < n2) {
                    void i;
                    String line = source2.readUtf8LineStrict();
                    Buffer bytes = new Buffer();
                    ByteString byteString = ByteString.Companion.decodeBase64(line);
                    Intrinsics.checkNotNull(byteString);
                    bytes.write(byteString);
                    result.add(certificateFactory.generateCertificate(bytes.inputStream()));
                    ++i;
                }
                return result;
            } catch (CertificateException e) {
                throw (Throwable)new IOException(e.getMessage());
            }
        }

        /*
         * WARNING - void declaration
         */
        private final void writeCertList(BufferedSink sink2, List<? extends Certificate> certificates) throws IOException {
            try {
                sink2.writeDecimalLong(certificates.size()).writeByte(10);
                int n = 0;
                int n2 = certificates.size();
                while (n < n2) {
                    void i;
                    byte[] bytes = certificates.get((int)i).getEncoded();
                    Intrinsics.checkNotNullExpressionValue(bytes, "bytes");
                    String line = ByteString.Companion.of$default(ByteString.Companion, bytes, 0, 0, 3, null).base64();
                    sink2.writeUtf8(line).writeByte(10);
                    ++i;
                }
            } catch (CertificateEncodingException e) {
                throw (Throwable)new IOException(e.getMessage());
            }
        }

        public final boolean matches(@NotNull Request request, @NotNull Response response) {
            Intrinsics.checkNotNullParameter(request, "request");
            Intrinsics.checkNotNullParameter(response, "response");
            return Intrinsics.areEqual(this.url, request.url().toString()) && Intrinsics.areEqual(this.requestMethod, request.method()) && Companion.varyMatches(response, this.varyHeaders, request);
        }

        @NotNull
        public final Response response(@NotNull DiskLruCache.Snapshot snapshot) {
            Intrinsics.checkNotNullParameter(snapshot, "snapshot");
            String contentType = this.responseHeaders.get("Content-Type");
            String contentLength = this.responseHeaders.get("Content-Length");
            Request cacheRequest = new Request.Builder().url(this.url).method(this.requestMethod, null).headers(this.varyHeaders).build();
            return new Response.Builder().request(cacheRequest).protocol(this.protocol).code(this.code).message(this.message).headers(this.responseHeaders).body(new CacheResponseBody(snapshot, contentType, contentLength)).handshake(this.handshake).sentRequestAtMillis(this.sentRequestMillis).receivedResponseAtMillis(this.receivedResponseMillis).build();
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         * WARNING - void declaration
         */
        public Entry(@NotNull Source rawSource) throws IOException {
            Intrinsics.checkNotNullParameter(rawSource, "rawSource");
            try {
                long l;
                long l2;
                boolean bl;
                String string;
                BufferedSource source2 = Okio.buffer(rawSource);
                this.url = source2.readUtf8LineStrict();
                this.requestMethod = source2.readUtf8LineStrict();
                Headers.Builder varyHeadersBuilder = new Headers.Builder();
                int varyRequestHeaderLineCount = Companion.readInt$okhttp(source2);
                int n = 0;
                int n2 = varyRequestHeaderLineCount;
                while (n < n2) {
                    void i;
                    varyHeadersBuilder.addLenient$okhttp(source2.readUtf8LineStrict());
                    ++i;
                }
                this.varyHeaders = varyHeadersBuilder.build();
                StatusLine statusLine = StatusLine.Companion.parse(source2.readUtf8LineStrict());
                this.protocol = statusLine.protocol;
                this.code = statusLine.code;
                this.message = statusLine.message;
                Headers.Builder responseHeadersBuilder = new Headers.Builder();
                int responseHeaderLineCount = Companion.readInt$okhttp(source2);
                int n3 = 0;
                int n4 = responseHeaderLineCount;
                while (n3 < n4) {
                    void i;
                    responseHeadersBuilder.addLenient$okhttp(source2.readUtf8LineStrict());
                    ++i;
                }
                String sendRequestMillisString = responseHeadersBuilder.get(SENT_MILLIS);
                String receivedResponseMillisString = responseHeadersBuilder.get(RECEIVED_MILLIS);
                responseHeadersBuilder.removeAll(SENT_MILLIS);
                responseHeadersBuilder.removeAll(RECEIVED_MILLIS);
                String string2 = sendRequestMillisString;
                if (string2 != null) {
                    string = string2;
                    bl = false;
                    l2 = Long.parseLong(string);
                } else {
                    l2 = 0L;
                }
                this.sentRequestMillis = l2;
                String string3 = receivedResponseMillisString;
                if (string3 != null) {
                    string = string3;
                    bl = false;
                    l = Long.parseLong(string);
                } else {
                    l = 0L;
                }
                this.receivedResponseMillis = l;
                this.responseHeaders = responseHeadersBuilder.build();
                if (this.isHttps()) {
                    String blank = source2.readUtf8LineStrict();
                    CharSequence charSequence = blank;
                    boolean bl2 = false;
                    if (charSequence.length() > 0) {
                        throw (Throwable)new IOException("expected \"\" but was \"" + blank + '\"');
                    }
                    String cipherSuiteString = source2.readUtf8LineStrict();
                    CipherSuite cipherSuite = CipherSuite.Companion.forJavaName(cipherSuiteString);
                    List<Certificate> peerCertificates2 = this.readCertificateList(source2);
                    List<Certificate> localCertificates = this.readCertificateList(source2);
                    TlsVersion tlsVersion = !source2.exhausted() ? TlsVersion.Companion.forJavaName(source2.readUtf8LineStrict()) : TlsVersion.SSL_3_0;
                    this.handshake = Handshake.Companion.get(tlsVersion, cipherSuite, peerCertificates2, localCertificates);
                } else {
                    this.handshake = null;
                }
            } finally {
                rawSource.close();
            }
        }

        public Entry(@NotNull Response response) {
            Intrinsics.checkNotNullParameter(response, "response");
            this.url = response.request().url().toString();
            this.varyHeaders = Companion.varyHeaders(response);
            this.requestMethod = response.request().method();
            this.protocol = response.protocol();
            this.code = response.code();
            this.message = response.message();
            this.responseHeaders = response.headers();
            this.handshake = response.handshake();
            this.sentRequestMillis = response.sentRequestAtMillis();
            this.receivedResponseMillis = response.receivedResponseAtMillis();
        }

        static {
            Companion = new Companion(null);
            SENT_MILLIS = Platform.Companion.get().getPrefix() + "-Sent-Millis";
            RECEIVED_MILLIS = Platform.Companion.get().getPrefix() + "-Received-Millis";
        }

        @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2={"Lokhttp3/Cache$Entry$Companion;", "", "()V", "RECEIVED_MILLIS", "", "SENT_MILLIS", "okhttp"})
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
                this();
            }
        }
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B%\u0012\n\u0010\u0002\u001a\u00060\u0003R\u00020\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\u0002\u0010\bJ\b\u0010\u0007\u001a\u00020\rH\u0016J\n\u0010\u0005\u001a\u0004\u0018\u00010\u000eH\u0016J\b\u0010\u000f\u001a\u00020\nH\u0016R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0015\u0010\u0002\u001a\u00060\u0003R\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f\u00a8\u0006\u0010"}, d2={"Lokhttp3/Cache$CacheResponseBody;", "Lokhttp3/ResponseBody;", "snapshot", "Lokhttp3/internal/cache/DiskLruCache$Snapshot;", "Lokhttp3/internal/cache/DiskLruCache;", "contentType", "", "contentLength", "(Lokhttp3/internal/cache/DiskLruCache$Snapshot;Ljava/lang/String;Ljava/lang/String;)V", "bodySource", "Lokio/BufferedSource;", "getSnapshot", "()Lokhttp3/internal/cache/DiskLruCache$Snapshot;", "", "Lokhttp3/MediaType;", "source", "okhttp"})
    private static final class CacheResponseBody
    extends ResponseBody {
        private final BufferedSource bodySource;
        @NotNull
        private final DiskLruCache.Snapshot snapshot;
        private final String contentType;
        private final String contentLength;

        @Override
        @Nullable
        public MediaType contentType() {
            String string = this.contentType;
            return string != null ? MediaType.Companion.parse(string) : null;
        }

        @Override
        public long contentLength() {
            String string = this.contentLength;
            return string != null ? Util.toLongOrDefault(string, -1L) : -1L;
        }

        @Override
        @NotNull
        public BufferedSource source() {
            return this.bodySource;
        }

        @NotNull
        public final DiskLruCache.Snapshot getSnapshot() {
            return this.snapshot;
        }

        public CacheResponseBody(@NotNull DiskLruCache.Snapshot snapshot, @Nullable String contentType, @Nullable String contentLength) {
            Intrinsics.checkNotNullParameter(snapshot, "snapshot");
            this.snapshot = snapshot;
            this.contentType = contentType;
            this.contentLength = contentLength;
            final Source source2 = this.snapshot.getSource(1);
            this.bodySource = Okio.buffer(new ForwardingSource(source2){

                @Override
                public void close() throws IOException {
                    this.getSnapshot().close();
                    super.close();
                }
            });
        }
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0007J\u0015\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u000eH\u0000\u00a2\u0006\u0002\b\u000fJ\u0018\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u0011H\u0002J\u001e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00112\u0006\u0010\u0019\u001a\u00020\u001aJ\n\u0010\u001b\u001a\u00020\u0015*\u00020\u0017J\u0012\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\t0\u001d*\u00020\u0011H\u0002J\n\u0010\u0010\u001a\u00020\u0011*\u00020\u0017R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001e"}, d2={"Lokhttp3/Cache$Companion;", "", "()V", "ENTRY_BODY", "", "ENTRY_COUNT", "ENTRY_METADATA", "VERSION", "key", "", "url", "Lokhttp3/HttpUrl;", "readInt", "source", "Lokio/BufferedSource;", "readInt$okhttp", "varyHeaders", "Lokhttp3/Headers;", "requestHeaders", "responseHeaders", "varyMatches", "", "cachedResponse", "Lokhttp3/Response;", "cachedRequest", "newRequest", "Lokhttp3/Request;", "hasVaryAll", "varyFields", "", "okhttp"})
    public static final class Companion {
        @JvmStatic
        @NotNull
        public final String key(@NotNull HttpUrl url) {
            Intrinsics.checkNotNullParameter(url, "url");
            return ByteString.Companion.encodeUtf8(url.toString()).md5().hex();
        }

        public final int readInt$okhttp(@NotNull BufferedSource source2) throws IOException {
            Intrinsics.checkNotNullParameter(source2, "source");
            try {
                long result;
                block5: {
                    String line;
                    block4: {
                        result = source2.readDecimalLong();
                        line = source2.readUtf8LineStrict();
                        if (result < 0L || result > (long)Integer.MAX_VALUE) break block4;
                        CharSequence charSequence = line;
                        boolean bl = false;
                        if (!(charSequence.length() > 0)) break block5;
                    }
                    throw (Throwable)new IOException("expected an int but was \"" + result + line + '\"');
                }
                return (int)result;
            } catch (NumberFormatException e) {
                throw (Throwable)new IOException(e.getMessage());
            }
        }

        public final boolean varyMatches(@NotNull Response cachedResponse, @NotNull Headers cachedRequest, @NotNull Request newRequest) {
            boolean bl;
            block3: {
                Intrinsics.checkNotNullParameter(cachedResponse, "cachedResponse");
                Intrinsics.checkNotNullParameter(cachedRequest, "cachedRequest");
                Intrinsics.checkNotNullParameter(newRequest, "newRequest");
                Iterable $this$none$iv = this.varyFields(cachedResponse.headers());
                boolean $i$f$none = false;
                if ($this$none$iv instanceof Collection && ((Collection)$this$none$iv).isEmpty()) {
                    bl = true;
                } else {
                    for (Object element$iv : $this$none$iv) {
                        String it = (String)element$iv;
                        boolean bl2 = false;
                        if (!(Intrinsics.areEqual(cachedRequest.values(it), newRequest.headers(it)) ^ true)) continue;
                        bl = false;
                        break block3;
                    }
                    bl = true;
                }
            }
            return bl;
        }

        public final boolean hasVaryAll(@NotNull Response $this$hasVaryAll) {
            Intrinsics.checkNotNullParameter($this$hasVaryAll, "$this$hasVaryAll");
            return this.varyFields($this$hasVaryAll.headers()).contains("*");
        }

        /*
         * WARNING - void declaration
         */
        private final Set<String> varyFields(Headers $this$varyFields) {
            Set result = null;
            int n = 0;
            int n2 = $this$varyFields.size();
            while (n < n2) {
                void i;
                if (StringsKt.equals("Vary", $this$varyFields.name((int)i), true)) {
                    String value = $this$varyFields.value((int)i);
                    if (result == null) {
                        result = new TreeSet<String>(StringsKt.getCASE_INSENSITIVE_ORDER(StringCompanionObject.INSTANCE));
                    }
                    Iterator iterator2 = StringsKt.split$default((CharSequence)value, new char[]{','}, false, 0, 6, null).iterator();
                    while (iterator2.hasNext()) {
                        String varyField;
                        String string = varyField = (String)iterator2.next();
                        boolean bl = false;
                        String string2 = string;
                        if (string2 == null) {
                            throw new NullPointerException("null cannot be cast to non-null type kotlin.CharSequence");
                        }
                        result.add(((Object)StringsKt.trim((CharSequence)string2)).toString());
                    }
                }
                ++i;
            }
            Set set = result;
            if (set == null) {
                set = SetsKt.emptySet();
            }
            return set;
        }

        @NotNull
        public final Headers varyHeaders(@NotNull Response $this$varyHeaders) {
            Intrinsics.checkNotNullParameter($this$varyHeaders, "$this$varyHeaders");
            Response response = $this$varyHeaders.networkResponse();
            Intrinsics.checkNotNull(response);
            Headers requestHeaders = response.request().headers();
            Headers responseHeaders = $this$varyHeaders.headers();
            return this.varyHeaders(requestHeaders, responseHeaders);
        }

        /*
         * WARNING - void declaration
         */
        private final Headers varyHeaders(Headers requestHeaders, Headers responseHeaders) {
            Set<String> varyFields = this.varyFields(responseHeaders);
            if (varyFields.isEmpty()) {
                return Util.EMPTY_HEADERS;
            }
            Headers.Builder result = new Headers.Builder();
            int n = 0;
            int n2 = requestHeaders.size();
            while (n < n2) {
                void i;
                String fieldName = requestHeaders.name((int)i);
                if (varyFields.contains(fieldName)) {
                    result.add(fieldName, requestHeaders.value((int)i));
                }
                ++i;
            }
            return result.build();
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

