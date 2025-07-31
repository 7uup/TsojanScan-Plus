/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okhttp3;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.io.CloseableKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import okhttp3.MediaType;
import okhttp3.internal.Util;
import okio.BufferedSink;
import okio.ByteString;
import okio.Okio;
import okio.Source;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\n\u0010\u0005\u001a\u0004\u0018\u00010\u0006H&J\b\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\bH\u0016J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH&\u00a8\u0006\u000f"}, d2={"Lokhttp3/RequestBody;", "", "()V", "contentLength", "", "contentType", "Lokhttp3/MediaType;", "isDuplex", "", "isOneShot", "writeTo", "", "sink", "Lokio/BufferedSink;", "Companion", "okhttp"})
public abstract class RequestBody {
    public static final Companion Companion = new Companion(null);

    @Nullable
    public abstract MediaType contentType();

    public long contentLength() throws IOException {
        return -1L;
    }

    public abstract void writeTo(@NotNull BufferedSink var1) throws IOException;

    public boolean isDuplex() {
        return false;
    }

    public boolean isOneShot() {
        return false;
    }

    @JvmStatic
    @JvmName(name="create")
    @NotNull
    public static final RequestBody create(@NotNull String $this$toRequestBody, @Nullable MediaType contentType) {
        return Companion.create($this$toRequestBody, contentType);
    }

    @JvmStatic
    @JvmName(name="create")
    @NotNull
    public static final RequestBody create(@NotNull ByteString $this$toRequestBody, @Nullable MediaType contentType) {
        return Companion.create($this$toRequestBody, contentType);
    }

    @JvmStatic
    @JvmOverloads
    @JvmName(name="create")
    @NotNull
    public static final RequestBody create(@NotNull byte[] $this$toRequestBody, @Nullable MediaType contentType, int offset, int byteCount) {
        return Companion.create($this$toRequestBody, contentType, offset, byteCount);
    }

    @JvmStatic
    @JvmOverloads
    @JvmName(name="create")
    @NotNull
    public static final RequestBody create(@NotNull byte[] $this$toRequestBody, @Nullable MediaType contentType, int offset) {
        return okhttp3.RequestBody$Companion.create$default(Companion, $this$toRequestBody, contentType, offset, 0, 4, null);
    }

    @JvmStatic
    @JvmOverloads
    @JvmName(name="create")
    @NotNull
    public static final RequestBody create(@NotNull byte[] $this$toRequestBody, @Nullable MediaType contentType) {
        return okhttp3.RequestBody$Companion.create$default(Companion, $this$toRequestBody, contentType, 0, 0, 6, null);
    }

    @JvmStatic
    @JvmOverloads
    @JvmName(name="create")
    @NotNull
    public static final RequestBody create(@NotNull byte[] $this$toRequestBody) {
        return okhttp3.RequestBody$Companion.create$default(Companion, $this$toRequestBody, null, 0, 0, 7, null);
    }

    @JvmStatic
    @JvmName(name="create")
    @NotNull
    public static final RequestBody create(@NotNull File $this$asRequestBody, @Nullable MediaType contentType) {
        return Companion.create($this$asRequestBody, contentType);
    }

    @JvmStatic
    @Deprecated(message="Moved to extension function. Put the 'content' argument first to fix Java", replaceWith=@ReplaceWith(imports={"okhttp3.RequestBody.Companion.toRequestBody"}, expression="content.toRequestBody(contentType)"), level=DeprecationLevel.WARNING)
    @NotNull
    public static final RequestBody create(@Nullable MediaType contentType, @NotNull String content) {
        return Companion.create(contentType, content);
    }

    @JvmStatic
    @Deprecated(message="Moved to extension function. Put the 'content' argument first to fix Java", replaceWith=@ReplaceWith(imports={"okhttp3.RequestBody.Companion.toRequestBody"}, expression="content.toRequestBody(contentType)"), level=DeprecationLevel.WARNING)
    @NotNull
    public static final RequestBody create(@Nullable MediaType contentType, @NotNull ByteString content) {
        return Companion.create(contentType, content);
    }

    @JvmStatic
    @Deprecated(message="Moved to extension function. Put the 'content' argument first to fix Java", replaceWith=@ReplaceWith(imports={"okhttp3.RequestBody.Companion.toRequestBody"}, expression="content.toRequestBody(contentType, offset, byteCount)"), level=DeprecationLevel.WARNING)
    @JvmOverloads
    @NotNull
    public static final RequestBody create(@Nullable MediaType contentType, @NotNull byte[] content, int offset, int byteCount) {
        return Companion.create(contentType, content, offset, byteCount);
    }

    @JvmStatic
    @Deprecated(message="Moved to extension function. Put the 'content' argument first to fix Java", replaceWith=@ReplaceWith(imports={"okhttp3.RequestBody.Companion.toRequestBody"}, expression="content.toRequestBody(contentType, offset, byteCount)"), level=DeprecationLevel.WARNING)
    @JvmOverloads
    @NotNull
    public static final RequestBody create(@Nullable MediaType contentType, @NotNull byte[] content, int offset) {
        return okhttp3.RequestBody$Companion.create$default(Companion, contentType, content, offset, 0, 8, null);
    }

    @JvmStatic
    @Deprecated(message="Moved to extension function. Put the 'content' argument first to fix Java", replaceWith=@ReplaceWith(imports={"okhttp3.RequestBody.Companion.toRequestBody"}, expression="content.toRequestBody(contentType, offset, byteCount)"), level=DeprecationLevel.WARNING)
    @JvmOverloads
    @NotNull
    public static final RequestBody create(@Nullable MediaType contentType, @NotNull byte[] content) {
        return okhttp3.RequestBody$Companion.create$default(Companion, contentType, content, 0, 0, 12, null);
    }

    @JvmStatic
    @Deprecated(message="Moved to extension function. Put the 'file' argument first to fix Java", replaceWith=@ReplaceWith(imports={"okhttp3.RequestBody.Companion.asRequestBody"}, expression="file.asRequestBody(contentType)"), level=DeprecationLevel.WARNING)
    @NotNull
    public static final RequestBody create(@Nullable MediaType contentType, @NotNull File file) {
        return Companion.create(contentType, file);
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J.\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\fH\u0007J\u001a\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\t\u001a\u00020\u000eH\u0007J\u001a\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\t\u001a\u00020\u000fH\u0007J\u001d\u0010\u0010\u001a\u00020\u0004*\u00020\b2\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0007\u00a2\u0006\u0002\b\u0003J1\u0010\u0011\u001a\u00020\u0004*\u00020\n2\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\fH\u0007\u00a2\u0006\u0002\b\u0003J\u001d\u0010\u0011\u001a\u00020\u0004*\u00020\u000e2\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0007\u00a2\u0006\u0002\b\u0003J\u001d\u0010\u0011\u001a\u00020\u0004*\u00020\u000f2\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0007\u00a2\u0006\u0002\b\u0003\u00a8\u0006\u0012"}, d2={"Lokhttp3/RequestBody$Companion;", "", "()V", "create", "Lokhttp3/RequestBody;", "contentType", "Lokhttp3/MediaType;", "file", "Ljava/io/File;", "content", "", "offset", "", "byteCount", "", "Lokio/ByteString;", "asRequestBody", "toRequestBody", "okhttp"})
    public static final class Companion {
        @JvmStatic
        @JvmName(name="create")
        @NotNull
        public final RequestBody create(@NotNull String $this$toRequestBody, @Nullable MediaType contentType) {
            Intrinsics.checkNotNullParameter($this$toRequestBody, "$this$toRequestBody");
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
            String string = $this$toRequestBody;
            boolean bl = false;
            byte[] byArray = string.getBytes(charset);
            Intrinsics.checkNotNullExpressionValue(byArray, "(this as java.lang.String).getBytes(charset)");
            byte[] bytes = byArray;
            return this.create(bytes, finalContentType, 0, bytes.length);
        }

        public static /* synthetic */ RequestBody create$default(Companion companion, String string, MediaType mediaType, int n, Object object) {
            if ((n & 1) != 0) {
                mediaType = null;
            }
            return companion.create(string, mediaType);
        }

        @JvmStatic
        @JvmName(name="create")
        @NotNull
        public final RequestBody create(@NotNull ByteString $this$toRequestBody, @Nullable MediaType contentType) {
            Intrinsics.checkNotNullParameter($this$toRequestBody, "$this$toRequestBody");
            return new RequestBody($this$toRequestBody, contentType){
                final /* synthetic */ ByteString $this_toRequestBody;
                final /* synthetic */ MediaType $contentType;

                @Nullable
                public MediaType contentType() {
                    return this.$contentType;
                }

                public long contentLength() {
                    return this.$this_toRequestBody.size();
                }

                public void writeTo(@NotNull BufferedSink sink2) {
                    Intrinsics.checkNotNullParameter(sink2, "sink");
                    sink2.write(this.$this_toRequestBody);
                }
                {
                    this.$this_toRequestBody = $receiver;
                    this.$contentType = $captured_local_variable$1;
                }
            };
        }

        public static /* synthetic */ RequestBody create$default(Companion companion, ByteString byteString, MediaType mediaType, int n, Object object) {
            if ((n & 1) != 0) {
                mediaType = null;
            }
            return companion.create(byteString, mediaType);
        }

        @JvmStatic
        @JvmOverloads
        @JvmName(name="create")
        @NotNull
        public final RequestBody create(@NotNull byte[] $this$toRequestBody, @Nullable MediaType contentType, int offset, int byteCount) {
            Intrinsics.checkNotNullParameter($this$toRequestBody, "$this$toRequestBody");
            Util.checkOffsetAndCount($this$toRequestBody.length, offset, byteCount);
            return new RequestBody($this$toRequestBody, contentType, byteCount, offset){
                final /* synthetic */ byte[] $this_toRequestBody;
                final /* synthetic */ MediaType $contentType;
                final /* synthetic */ int $byteCount;
                final /* synthetic */ int $offset;

                @Nullable
                public MediaType contentType() {
                    return this.$contentType;
                }

                public long contentLength() {
                    return this.$byteCount;
                }

                public void writeTo(@NotNull BufferedSink sink2) {
                    Intrinsics.checkNotNullParameter(sink2, "sink");
                    sink2.write(this.$this_toRequestBody, this.$offset, this.$byteCount);
                }
                {
                    this.$this_toRequestBody = $receiver;
                    this.$contentType = $captured_local_variable$1;
                    this.$byteCount = $captured_local_variable$2;
                    this.$offset = $captured_local_variable$3;
                }
            };
        }

        public static /* synthetic */ RequestBody create$default(Companion companion, byte[] byArray, MediaType mediaType, int n, int n2, int n3, Object object) {
            if ((n3 & 1) != 0) {
                mediaType = null;
            }
            if ((n3 & 2) != 0) {
                n = 0;
            }
            if ((n3 & 4) != 0) {
                n2 = byArray.length;
            }
            return companion.create(byArray, mediaType, n, n2);
        }

        @JvmStatic
        @JvmOverloads
        @JvmName(name="create")
        @NotNull
        public final RequestBody create(@NotNull byte[] $this$toRequestBody, @Nullable MediaType contentType, int offset) {
            return okhttp3.RequestBody$Companion.create$default(this, $this$toRequestBody, contentType, offset, 0, 4, null);
        }

        @JvmStatic
        @JvmOverloads
        @JvmName(name="create")
        @NotNull
        public final RequestBody create(@NotNull byte[] $this$toRequestBody, @Nullable MediaType contentType) {
            return okhttp3.RequestBody$Companion.create$default(this, $this$toRequestBody, contentType, 0, 0, 6, null);
        }

        @JvmStatic
        @JvmOverloads
        @JvmName(name="create")
        @NotNull
        public final RequestBody create(@NotNull byte[] $this$toRequestBody) {
            return okhttp3.RequestBody$Companion.create$default(this, $this$toRequestBody, null, 0, 0, 7, null);
        }

        @JvmStatic
        @JvmName(name="create")
        @NotNull
        public final RequestBody create(@NotNull File $this$asRequestBody, @Nullable MediaType contentType) {
            Intrinsics.checkNotNullParameter($this$asRequestBody, "$this$asRequestBody");
            return new RequestBody($this$asRequestBody, contentType){
                final /* synthetic */ File $this_asRequestBody;
                final /* synthetic */ MediaType $contentType;

                @Nullable
                public MediaType contentType() {
                    return this.$contentType;
                }

                public long contentLength() {
                    return this.$this_asRequestBody.length();
                }

                /*
                 * WARNING - Removed try catching itself - possible behaviour change.
                 */
                public void writeTo(@NotNull BufferedSink sink2) {
                    Intrinsics.checkNotNullParameter(sink2, "sink");
                    Closeable closeable = Okio.source(this.$this_asRequestBody);
                    boolean bl = false;
                    boolean bl2 = false;
                    Throwable throwable = null;
                    try {
                        Source source2 = (Source)closeable;
                        boolean bl3 = false;
                        long l = sink2.writeAll(source2);
                    } catch (Throwable throwable2) {
                        throwable = throwable2;
                        throw throwable2;
                    } finally {
                        CloseableKt.closeFinally(closeable, throwable);
                    }
                }
                {
                    this.$this_asRequestBody = $receiver;
                    this.$contentType = $captured_local_variable$1;
                }
            };
        }

        public static /* synthetic */ RequestBody create$default(Companion companion, File file, MediaType mediaType, int n, Object object) {
            if ((n & 1) != 0) {
                mediaType = null;
            }
            return companion.create(file, mediaType);
        }

        @JvmStatic
        @Deprecated(message="Moved to extension function. Put the 'content' argument first to fix Java", replaceWith=@ReplaceWith(imports={"okhttp3.RequestBody.Companion.toRequestBody"}, expression="content.toRequestBody(contentType)"), level=DeprecationLevel.WARNING)
        @NotNull
        public final RequestBody create(@Nullable MediaType contentType, @NotNull String content) {
            Intrinsics.checkNotNullParameter(content, "content");
            return this.create(content, contentType);
        }

        @JvmStatic
        @Deprecated(message="Moved to extension function. Put the 'content' argument first to fix Java", replaceWith=@ReplaceWith(imports={"okhttp3.RequestBody.Companion.toRequestBody"}, expression="content.toRequestBody(contentType)"), level=DeprecationLevel.WARNING)
        @NotNull
        public final RequestBody create(@Nullable MediaType contentType, @NotNull ByteString content) {
            Intrinsics.checkNotNullParameter(content, "content");
            return this.create(content, contentType);
        }

        @JvmStatic
        @Deprecated(message="Moved to extension function. Put the 'content' argument first to fix Java", replaceWith=@ReplaceWith(imports={"okhttp3.RequestBody.Companion.toRequestBody"}, expression="content.toRequestBody(contentType, offset, byteCount)"), level=DeprecationLevel.WARNING)
        @JvmOverloads
        @NotNull
        public final RequestBody create(@Nullable MediaType contentType, @NotNull byte[] content, int offset, int byteCount) {
            Intrinsics.checkNotNullParameter(content, "content");
            return this.create(content, contentType, offset, byteCount);
        }

        public static /* synthetic */ RequestBody create$default(Companion companion, MediaType mediaType, byte[] byArray, int n, int n2, int n3, Object object) {
            if ((n3 & 4) != 0) {
                n = 0;
            }
            if ((n3 & 8) != 0) {
                n2 = byArray.length;
            }
            return companion.create(mediaType, byArray, n, n2);
        }

        @JvmStatic
        @Deprecated(message="Moved to extension function. Put the 'content' argument first to fix Java", replaceWith=@ReplaceWith(imports={"okhttp3.RequestBody.Companion.toRequestBody"}, expression="content.toRequestBody(contentType, offset, byteCount)"), level=DeprecationLevel.WARNING)
        @JvmOverloads
        @NotNull
        public final RequestBody create(@Nullable MediaType contentType, @NotNull byte[] content, int offset) {
            return okhttp3.RequestBody$Companion.create$default(this, contentType, content, offset, 0, 8, null);
        }

        @JvmStatic
        @Deprecated(message="Moved to extension function. Put the 'content' argument first to fix Java", replaceWith=@ReplaceWith(imports={"okhttp3.RequestBody.Companion.toRequestBody"}, expression="content.toRequestBody(contentType, offset, byteCount)"), level=DeprecationLevel.WARNING)
        @JvmOverloads
        @NotNull
        public final RequestBody create(@Nullable MediaType contentType, @NotNull byte[] content) {
            return okhttp3.RequestBody$Companion.create$default(this, contentType, content, 0, 0, 12, null);
        }

        @JvmStatic
        @Deprecated(message="Moved to extension function. Put the 'file' argument first to fix Java", replaceWith=@ReplaceWith(imports={"okhttp3.RequestBody.Companion.asRequestBody"}, expression="file.asRequestBody(contentType)"), level=DeprecationLevel.WARNING)
        @NotNull
        public final RequestBody create(@Nullable MediaType contentType, @NotNull File file) {
            Intrinsics.checkNotNullParameter(file, "file");
            return this.create(file, contentType);
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

