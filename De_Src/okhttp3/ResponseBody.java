/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okhttp3;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.io.CloseableKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import okhttp3.MediaType;
import okhttp3.internal.Util;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b&\u0018\u0000 !2\u00020\u0001:\u0002 !B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u0005\u001a\u00020\u0006J\u0006\u0010\u0007\u001a\u00020\bJ\u0006\u0010\t\u001a\u00020\nJ\u0006\u0010\u000b\u001a\u00020\u0004J\b\u0010\f\u001a\u00020\rH\u0002J\b\u0010\u000e\u001a\u00020\u000fH\u0016J@\u0010\u0010\u001a\u0002H\u0011\"\b\b\u0000\u0010\u0011*\u00020\u00122\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u0002H\u00110\u00142\u0012\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u0002H\u0011\u0012\u0004\u0012\u00020\u00170\u0014H\u0082\b\u00a2\u0006\u0002\u0010\u0018J\b\u0010\u0019\u001a\u00020\u001aH&J\n\u0010\u001b\u001a\u0004\u0018\u00010\u001cH&J\b\u0010\u001d\u001a\u00020\u0015H&J\u0006\u0010\u001e\u001a\u00020\u001fR\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\""}, d2={"Lokhttp3/ResponseBody;", "Ljava/io/Closeable;", "()V", "reader", "Ljava/io/Reader;", "byteStream", "Ljava/io/InputStream;", "byteString", "Lokio/ByteString;", "bytes", "", "charStream", "charset", "Ljava/nio/charset/Charset;", "close", "", "consumeSource", "T", "", "consumer", "Lkotlin/Function1;", "Lokio/BufferedSource;", "sizeMapper", "", "(Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "contentLength", "", "contentType", "Lokhttp3/MediaType;", "source", "string", "", "BomAwareReader", "Companion", "okhttp"})
public abstract class ResponseBody
implements Closeable {
    private Reader reader;
    public static final Companion Companion = new Companion(null);

    @Nullable
    public abstract MediaType contentType();

    public abstract long contentLength();

    @NotNull
    public final InputStream byteStream() {
        return this.source().inputStream();
    }

    @NotNull
    public abstract BufferedSource source();

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @NotNull
    public final byte[] bytes() throws IOException {
        byte[] bytes$iv;
        byte[] byArray;
        ResponseBody this_$iv = this;
        boolean $i$f$consumeSource = false;
        long contentLength$iv = this_$iv.contentLength();
        if (contentLength$iv > (long)Integer.MAX_VALUE) {
            throw (Throwable)new IOException("Cannot buffer entire body for content length: " + contentLength$iv);
        }
        Closeable closeable = this_$iv.source();
        boolean bl = false;
        boolean bl2 = false;
        Throwable throwable = null;
        try {
            BufferedSource p1 = (BufferedSource)closeable;
            boolean bl3 = false;
            byArray = p1.readByteArray();
        } catch (Throwable throwable2) {
            throwable = throwable2;
            throw throwable2;
        } finally {
            CloseableKt.closeFinally(closeable, throwable);
        }
        byte[] it = bytes$iv = byArray;
        boolean bl4 = false;
        int size$iv = it.length;
        if (contentLength$iv != -1L && contentLength$iv != (long)size$iv) {
            throw (Throwable)new IOException("Content-Length (" + contentLength$iv + ") and stream length (" + size$iv + ") disagree");
        }
        return bytes$iv;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @NotNull
    public final ByteString byteString() throws IOException {
        ByteString bytes$iv;
        ByteString byteString;
        ResponseBody this_$iv = this;
        boolean $i$f$consumeSource = false;
        long contentLength$iv = this_$iv.contentLength();
        if (contentLength$iv > (long)Integer.MAX_VALUE) {
            throw (Throwable)new IOException("Cannot buffer entire body for content length: " + contentLength$iv);
        }
        Closeable closeable = this_$iv.source();
        boolean bl = false;
        boolean bl2 = false;
        Throwable throwable = null;
        try {
            BufferedSource p1 = (BufferedSource)closeable;
            boolean bl3 = false;
            byteString = p1.readByteString();
        } catch (Throwable throwable2) {
            throwable = throwable2;
            throw throwable2;
        } finally {
            CloseableKt.closeFinally(closeable, throwable);
        }
        ByteString it = bytes$iv = byteString;
        boolean bl4 = false;
        int size$iv = it.size();
        if (contentLength$iv != -1L && contentLength$iv != (long)size$iv) {
            throw (Throwable)new IOException("Content-Length (" + contentLength$iv + ") and stream length (" + size$iv + ") disagree");
        }
        return bytes$iv;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private final <T> T consumeSource(Function1<? super BufferedSource, ? extends T> consumer, Function1<? super T, Integer> sizeMapper) {
        T t;
        int $i$f$consumeSource = 0;
        long contentLength = this.contentLength();
        if (contentLength > (long)Integer.MAX_VALUE) {
            throw (Throwable)new IOException("Cannot buffer entire body for content length: " + contentLength);
        }
        Closeable closeable = this.source();
        boolean bl = false;
        boolean bl2 = false;
        Throwable throwable = null;
        try {
            t = consumer.invoke((BufferedSource)closeable);
        } catch (Throwable throwable2) {
            throwable = throwable2;
            throw throwable2;
        } finally {
            InlineMarker.finallyStart(1);
            CloseableKt.closeFinally(closeable, throwable);
            InlineMarker.finallyEnd(1);
        }
        T bytes = t;
        int size = ((Number)sizeMapper.invoke(bytes)).intValue();
        if (contentLength != -1L && contentLength != (long)size) {
            throw (Throwable)new IOException("Content-Length (" + contentLength + ") and stream length (" + size + ") disagree");
        }
        return bytes;
    }

    @NotNull
    public final Reader charStream() {
        Reader reader = this.reader;
        if (reader == null) {
            BomAwareReader bomAwareReader = new BomAwareReader(this.source(), this.charset());
            boolean bl = false;
            boolean bl2 = false;
            BomAwareReader it = bomAwareReader;
            boolean bl3 = false;
            this.reader = it;
            reader = bomAwareReader;
        }
        return reader;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @NotNull
    public final String string() throws IOException {
        String string;
        Closeable closeable = this.source();
        boolean bl = false;
        boolean bl2 = false;
        Throwable throwable = null;
        try {
            BufferedSource source2 = (BufferedSource)closeable;
            boolean bl3 = false;
            string = source2.readString(Util.readBomAsCharset(source2, this.charset()));
        } catch (Throwable throwable2) {
            throwable = throwable2;
            throw throwable2;
        } finally {
            CloseableKt.closeFinally(closeable, throwable);
        }
        return string;
    }

    private final Charset charset() {
        Object object = this.contentType();
        if (object == null || (object = ((MediaType)object).charset(Charsets.UTF_8)) == null) {
            object = Charsets.UTF_8;
        }
        return object;
    }

    @Override
    public void close() {
        Util.closeQuietly(this.source());
    }

    @JvmStatic
    @JvmName(name="create")
    @NotNull
    public static final ResponseBody create(@NotNull String $this$toResponseBody, @Nullable MediaType contentType) {
        return Companion.create($this$toResponseBody, contentType);
    }

    @JvmStatic
    @JvmName(name="create")
    @NotNull
    public static final ResponseBody create(@NotNull byte[] $this$toResponseBody, @Nullable MediaType contentType) {
        return Companion.create($this$toResponseBody, contentType);
    }

    @JvmStatic
    @JvmName(name="create")
    @NotNull
    public static final ResponseBody create(@NotNull ByteString $this$toResponseBody, @Nullable MediaType contentType) {
        return Companion.create($this$toResponseBody, contentType);
    }

    @JvmStatic
    @JvmName(name="create")
    @NotNull
    public static final ResponseBody create(@NotNull BufferedSource $this$asResponseBody, @Nullable MediaType contentType, long contentLength) {
        return Companion.create($this$asResponseBody, contentType, contentLength);
    }

    @JvmStatic
    @Deprecated(message="Moved to extension function. Put the 'content' argument first to fix Java", replaceWith=@ReplaceWith(imports={"okhttp3.ResponseBody.Companion.toResponseBody"}, expression="content.toResponseBody(contentType)"), level=DeprecationLevel.WARNING)
    @NotNull
    public static final ResponseBody create(@Nullable MediaType contentType, @NotNull String content) {
        return Companion.create(contentType, content);
    }

    @JvmStatic
    @Deprecated(message="Moved to extension function. Put the 'content' argument first to fix Java", replaceWith=@ReplaceWith(imports={"okhttp3.ResponseBody.Companion.toResponseBody"}, expression="content.toResponseBody(contentType)"), level=DeprecationLevel.WARNING)
    @NotNull
    public static final ResponseBody create(@Nullable MediaType contentType, @NotNull byte[] content) {
        return Companion.create(contentType, content);
    }

    @JvmStatic
    @Deprecated(message="Moved to extension function. Put the 'content' argument first to fix Java", replaceWith=@ReplaceWith(imports={"okhttp3.ResponseBody.Companion.toResponseBody"}, expression="content.toResponseBody(contentType)"), level=DeprecationLevel.WARNING)
    @NotNull
    public static final ResponseBody create(@Nullable MediaType contentType, @NotNull ByteString content) {
        return Companion.create(contentType, content);
    }

    @JvmStatic
    @Deprecated(message="Moved to extension function. Put the 'content' argument first to fix Java", replaceWith=@ReplaceWith(imports={"okhttp3.ResponseBody.Companion.asResponseBody"}, expression="content.asResponseBody(contentType, contentLength)"), level=DeprecationLevel.WARNING)
    @NotNull
    public static final ResponseBody create(@Nullable MediaType contentType, long contentLength, @NotNull BufferedSource content) {
        return Companion.create(contentType, contentLength, content);
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0019\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\b\u0010\n\u001a\u00020\u000bH\u0016J \u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\rH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\u0001X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2={"Lokhttp3/ResponseBody$BomAwareReader;", "Ljava/io/Reader;", "source", "Lokio/BufferedSource;", "charset", "Ljava/nio/charset/Charset;", "(Lokio/BufferedSource;Ljava/nio/charset/Charset;)V", "closed", "", "delegate", "close", "", "read", "", "cbuf", "", "off", "len", "okhttp"})
    public static final class BomAwareReader
    extends Reader {
        private boolean closed;
        private Reader delegate;
        private final BufferedSource source;
        private final Charset charset;

        @Override
        public int read(@NotNull char[] cbuf, int off, int len) throws IOException {
            Intrinsics.checkNotNullParameter(cbuf, "cbuf");
            if (this.closed) {
                throw (Throwable)new IOException("Stream closed");
            }
            Reader reader = this.delegate;
            if (reader == null) {
                InputStreamReader inputStreamReader = new InputStreamReader(this.source.inputStream(), Util.readBomAsCharset(this.source, this.charset));
                boolean bl = false;
                boolean bl2 = false;
                InputStreamReader it = inputStreamReader;
                boolean bl3 = false;
                this.delegate = it;
                reader = inputStreamReader;
            }
            Reader finalDelegate = reader;
            return finalDelegate.read(cbuf, off, len);
        }

        @Override
        public void close() throws IOException {
            this.closed = true;
            Reader reader = this.delegate;
            if (reader != null) {
                reader.close();
            } else {
                BomAwareReader bomAwareReader = this;
                boolean bl = false;
                boolean bl2 = false;
                BomAwareReader $this$run = bomAwareReader;
                boolean bl3 = false;
                $this$run.source.close();
            }
        }

        public BomAwareReader(@NotNull BufferedSource source2, @NotNull Charset charset) {
            Intrinsics.checkNotNullParameter(source2, "source");
            Intrinsics.checkNotNullParameter(charset, "charset");
            this.source = source2;
            this.charset = charset;
        }
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\"\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\u000bH\u0007J\u001a\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\fH\u0007J\u001a\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\rH\u0007J'\u0010\u000e\u001a\u00020\u0004*\u00020\u000b2\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\t\u001a\u00020\nH\u0007\u00a2\u0006\u0002\b\u0003J\u001d\u0010\u000f\u001a\u00020\u0004*\u00020\b2\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0007\u00a2\u0006\u0002\b\u0003J\u001d\u0010\u000f\u001a\u00020\u0004*\u00020\f2\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0007\u00a2\u0006\u0002\b\u0003J\u001d\u0010\u000f\u001a\u00020\u0004*\u00020\r2\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0007\u00a2\u0006\u0002\b\u0003\u00a8\u0006\u0010"}, d2={"Lokhttp3/ResponseBody$Companion;", "", "()V", "create", "Lokhttp3/ResponseBody;", "contentType", "Lokhttp3/MediaType;", "content", "", "contentLength", "", "Lokio/BufferedSource;", "", "Lokio/ByteString;", "asResponseBody", "toResponseBody", "okhttp"})
    public static final class Companion {
        @JvmStatic
        @JvmName(name="create")
        @NotNull
        public final ResponseBody create(@NotNull String $this$toResponseBody, @Nullable MediaType contentType) {
            Intrinsics.checkNotNullParameter($this$toResponseBody, "$this$toResponseBody");
            Charset charset = Charsets.UTF_8;
            MediaType finalContentType = contentType;
            if (contentType != null) {
                Charset resolvedCharset = MediaType.charset$default(contentType, null, 1, null);
                if (resolvedCharset == null) {
                    charset = Charsets.UTF_8;
                    finalContentType = MediaType.Companion.parse(contentType + "; charset=utf-8");
                } else {
                    charset = resolvedCharset;
                }
            }
            Buffer buffer = new Buffer().writeString($this$toResponseBody, charset);
            return this.create(buffer, finalContentType, buffer.size());
        }

        public static /* synthetic */ ResponseBody create$default(Companion companion, String string, MediaType mediaType, int n, Object object) {
            if ((n & 1) != 0) {
                mediaType = null;
            }
            return companion.create(string, mediaType);
        }

        @JvmStatic
        @JvmName(name="create")
        @NotNull
        public final ResponseBody create(@NotNull byte[] $this$toResponseBody, @Nullable MediaType contentType) {
            Intrinsics.checkNotNullParameter($this$toResponseBody, "$this$toResponseBody");
            return this.create(new Buffer().write($this$toResponseBody), contentType, $this$toResponseBody.length);
        }

        public static /* synthetic */ ResponseBody create$default(Companion companion, byte[] byArray, MediaType mediaType, int n, Object object) {
            if ((n & 1) != 0) {
                mediaType = null;
            }
            return companion.create(byArray, mediaType);
        }

        @JvmStatic
        @JvmName(name="create")
        @NotNull
        public final ResponseBody create(@NotNull ByteString $this$toResponseBody, @Nullable MediaType contentType) {
            Intrinsics.checkNotNullParameter($this$toResponseBody, "$this$toResponseBody");
            return this.create(new Buffer().write($this$toResponseBody), contentType, $this$toResponseBody.size());
        }

        public static /* synthetic */ ResponseBody create$default(Companion companion, ByteString byteString, MediaType mediaType, int n, Object object) {
            if ((n & 1) != 0) {
                mediaType = null;
            }
            return companion.create(byteString, mediaType);
        }

        @JvmStatic
        @JvmName(name="create")
        @NotNull
        public final ResponseBody create(@NotNull BufferedSource $this$asResponseBody, @Nullable MediaType contentType, long contentLength) {
            Intrinsics.checkNotNullParameter($this$asResponseBody, "$this$asResponseBody");
            return new ResponseBody($this$asResponseBody, contentType, contentLength){
                final /* synthetic */ BufferedSource $this_asResponseBody;
                final /* synthetic */ MediaType $contentType;
                final /* synthetic */ long $contentLength;

                @Nullable
                public MediaType contentType() {
                    return this.$contentType;
                }

                public long contentLength() {
                    return this.$contentLength;
                }

                @NotNull
                public BufferedSource source() {
                    return this.$this_asResponseBody;
                }
                {
                    this.$this_asResponseBody = $receiver;
                    this.$contentType = $captured_local_variable$1;
                    this.$contentLength = $captured_local_variable$2;
                }
            };
        }

        public static /* synthetic */ ResponseBody create$default(Companion companion, BufferedSource bufferedSource, MediaType mediaType, long l, int n, Object object) {
            if ((n & 1) != 0) {
                mediaType = null;
            }
            if ((n & 2) != 0) {
                l = -1L;
            }
            return companion.create(bufferedSource, mediaType, l);
        }

        @JvmStatic
        @Deprecated(message="Moved to extension function. Put the 'content' argument first to fix Java", replaceWith=@ReplaceWith(imports={"okhttp3.ResponseBody.Companion.toResponseBody"}, expression="content.toResponseBody(contentType)"), level=DeprecationLevel.WARNING)
        @NotNull
        public final ResponseBody create(@Nullable MediaType contentType, @NotNull String content) {
            Intrinsics.checkNotNullParameter(content, "content");
            return this.create(content, contentType);
        }

        @JvmStatic
        @Deprecated(message="Moved to extension function. Put the 'content' argument first to fix Java", replaceWith=@ReplaceWith(imports={"okhttp3.ResponseBody.Companion.toResponseBody"}, expression="content.toResponseBody(contentType)"), level=DeprecationLevel.WARNING)
        @NotNull
        public final ResponseBody create(@Nullable MediaType contentType, @NotNull byte[] content) {
            Intrinsics.checkNotNullParameter(content, "content");
            return this.create(content, contentType);
        }

        @JvmStatic
        @Deprecated(message="Moved to extension function. Put the 'content' argument first to fix Java", replaceWith=@ReplaceWith(imports={"okhttp3.ResponseBody.Companion.toResponseBody"}, expression="content.toResponseBody(contentType)"), level=DeprecationLevel.WARNING)
        @NotNull
        public final ResponseBody create(@Nullable MediaType contentType, @NotNull ByteString content) {
            Intrinsics.checkNotNullParameter(content, "content");
            return this.create(content, contentType);
        }

        @JvmStatic
        @Deprecated(message="Moved to extension function. Put the 'content' argument first to fix Java", replaceWith=@ReplaceWith(imports={"okhttp3.ResponseBody.Companion.asResponseBody"}, expression="content.asResponseBody(contentType, contentLength)"), level=DeprecationLevel.WARNING)
        @NotNull
        public final ResponseBody create(@Nullable MediaType contentType, long contentLength, @NotNull BufferedSource content) {
            Intrinsics.checkNotNullParameter(content, "content");
            return this.create(content, contentType, contentLength);
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

