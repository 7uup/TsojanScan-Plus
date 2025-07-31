/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okhttp3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.internal.Util;
import okio.Buffer;
import okio.BufferedSink;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u0000 #2\u00020\u0001:\u0003\"#$B%\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u00a2\u0006\u0002\u0010\tJ\r\u0010\n\u001a\u00020\u000bH\u0007\u00a2\u0006\u0002\b\u0015J\b\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u0005H\u0016J\u000e\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\u0012J\u0013\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0007\u00a2\u0006\u0002\b\u0018J\r\u0010\u0011\u001a\u00020\u0012H\u0007\u00a2\u0006\u0002\b\u0019J\r\u0010\u0004\u001a\u00020\u0005H\u0007\u00a2\u0006\u0002\b\u001aJ\u001a\u0010\u001b\u001a\u00020\u000e2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0002J\u0010\u0010 \u001a\u00020!2\u0006\u0010\u001c\u001a\u00020\u001dH\u0016R\u0011\u0010\n\u001a\u00020\u000b8G\u00a2\u0006\u0006\u001a\u0004\b\n\u0010\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00078\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0010R\u0011\u0010\u0011\u001a\u00020\u00128G\u00a2\u0006\u0006\u001a\u0004\b\u0011\u0010\u0013R\u0013\u0010\u0004\u001a\u00020\u00058\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0014\u00a8\u0006%"}, d2={"Lokhttp3/MultipartBody;", "Lokhttp3/RequestBody;", "boundaryByteString", "Lokio/ByteString;", "type", "Lokhttp3/MediaType;", "parts", "", "Lokhttp3/MultipartBody$Part;", "(Lokio/ByteString;Lokhttp3/MediaType;Ljava/util/List;)V", "boundary", "", "()Ljava/lang/String;", "contentLength", "", "contentType", "()Ljava/util/List;", "size", "", "()I", "()Lokhttp3/MediaType;", "-deprecated_boundary", "part", "index", "-deprecated_parts", "-deprecated_size", "-deprecated_type", "writeOrCountBytes", "sink", "Lokio/BufferedSink;", "countBytes", "", "writeTo", "", "Builder", "Companion", "Part", "okhttp"})
public final class MultipartBody
extends RequestBody {
    private final MediaType contentType;
    private long contentLength;
    private final ByteString boundaryByteString;
    @NotNull
    private final MediaType type;
    @NotNull
    private final List<Part> parts;
    @JvmField
    @NotNull
    public static final MediaType MIXED;
    @JvmField
    @NotNull
    public static final MediaType ALTERNATIVE;
    @JvmField
    @NotNull
    public static final MediaType DIGEST;
    @JvmField
    @NotNull
    public static final MediaType PARALLEL;
    @JvmField
    @NotNull
    public static final MediaType FORM;
    private static final byte[] COLONSPACE;
    private static final byte[] CRLF;
    private static final byte[] DASHDASH;
    public static final Companion Companion;

    @JvmName(name="boundary")
    @NotNull
    public final String boundary() {
        return this.boundaryByteString.utf8();
    }

    @JvmName(name="size")
    public final int size() {
        return this.parts.size();
    }

    @NotNull
    public final Part part(int index) {
        return this.parts.get(index);
    }

    @Override
    @NotNull
    public MediaType contentType() {
        return this.contentType;
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="type"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_type")
    @NotNull
    public final MediaType -deprecated_type() {
        return this.type;
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="boundary"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_boundary")
    @NotNull
    public final String -deprecated_boundary() {
        return this.boundary();
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="size"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_size")
    public final int -deprecated_size() {
        return this.size();
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="parts"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_parts")
    @NotNull
    public final List<Part> -deprecated_parts() {
        return this.parts;
    }

    @Override
    public long contentLength() throws IOException {
        long result = this.contentLength;
        if (result == -1L) {
            this.contentLength = result = this.writeOrCountBytes(null, true);
        }
        return result;
    }

    @Override
    public void writeTo(@NotNull BufferedSink sink2) throws IOException {
        Intrinsics.checkNotNullParameter(sink2, "sink");
        this.writeOrCountBytes(sink2, false);
    }

    /*
     * WARNING - void declaration
     */
    private final long writeOrCountBytes(BufferedSink sink2, boolean countBytes) throws IOException {
        BufferedSink sink3 = sink2;
        long byteCount = 0L;
        Buffer byteCountBuffer = null;
        if (countBytes) {
            byteCountBuffer = new Buffer();
            sink3 = byteCountBuffer;
        }
        int n = 0;
        int n2 = this.parts.size();
        while (n < n2) {
            long contentLength;
            MediaType contentType;
            void p;
            Part part = this.parts.get((int)p);
            Headers headers = part.headers();
            RequestBody body = part.body();
            BufferedSink bufferedSink = sink3;
            Intrinsics.checkNotNull(bufferedSink);
            bufferedSink.write(DASHDASH);
            sink3.write(this.boundaryByteString);
            sink3.write(CRLF);
            if (headers != null) {
                int n3 = 0;
                int n4 = headers.size();
                while (n3 < n4) {
                    void h2;
                    sink3.writeUtf8(headers.name((int)h2)).write(COLONSPACE).writeUtf8(headers.value((int)h2)).write(CRLF);
                    ++h2;
                }
            }
            if ((contentType = body.contentType()) != null) {
                sink3.writeUtf8("Content-Type: ").writeUtf8(contentType.toString()).write(CRLF);
            }
            if ((contentLength = body.contentLength()) != -1L) {
                sink3.writeUtf8("Content-Length: ").writeDecimalLong(contentLength).write(CRLF);
            } else if (countBytes) {
                Buffer buffer = byteCountBuffer;
                Intrinsics.checkNotNull(buffer);
                buffer.clear();
                return -1L;
            }
            sink3.write(CRLF);
            if (countBytes) {
                byteCount += contentLength;
            } else {
                body.writeTo(sink3);
            }
            sink3.write(CRLF);
            ++p;
        }
        BufferedSink bufferedSink = sink3;
        Intrinsics.checkNotNull(bufferedSink);
        bufferedSink.write(DASHDASH);
        sink3.write(this.boundaryByteString);
        sink3.write(DASHDASH);
        sink3.write(CRLF);
        if (countBytes) {
            Buffer buffer = byteCountBuffer;
            Intrinsics.checkNotNull(buffer);
            byteCount += buffer.size();
            byteCountBuffer.clear();
        }
        return byteCount;
    }

    @JvmName(name="type")
    @NotNull
    public final MediaType type() {
        return this.type;
    }

    @JvmName(name="parts")
    @NotNull
    public final List<Part> parts() {
        return this.parts;
    }

    public MultipartBody(@NotNull ByteString boundaryByteString, @NotNull MediaType type, @NotNull List<Part> parts) {
        Intrinsics.checkNotNullParameter(boundaryByteString, "boundaryByteString");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(parts, "parts");
        this.boundaryByteString = boundaryByteString;
        this.type = type;
        this.parts = parts;
        this.contentType = MediaType.Companion.get(this.type + "; boundary=" + this.boundary());
        this.contentLength = -1L;
    }

    static {
        Companion = new Companion(null);
        MIXED = MediaType.Companion.get("multipart/mixed");
        ALTERNATIVE = MediaType.Companion.get("multipart/alternative");
        DIGEST = MediaType.Companion.get("multipart/digest");
        PARALLEL = MediaType.Companion.get("multipart/parallel");
        FORM = MediaType.Companion.get("multipart/form-data");
        COLONSPACE = new byte[]{(byte)58, (byte)32};
        CRLF = new byte[]{(byte)13, (byte)10};
        DASHDASH = new byte[]{(byte)45, (byte)45};
    }

    /*
     * Illegal identifiers - consider using --renameillegalidents true
     */
    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\u0019\b\u0002\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\r\u0010\u0004\u001a\u00020\u0005H\u0007\u00a2\u0006\u0002\b\tJ\u000f\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u0007\u00a2\u0006\u0002\b\nR\u0013\u0010\u0004\u001a\u00020\u00058\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0007R\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\b\u00a8\u0006\f"}, d2={"Lokhttp3/MultipartBody$Part;", "", "headers", "Lokhttp3/Headers;", "body", "Lokhttp3/RequestBody;", "(Lokhttp3/Headers;Lokhttp3/RequestBody;)V", "()Lokhttp3/RequestBody;", "()Lokhttp3/Headers;", "-deprecated_body", "-deprecated_headers", "Companion", "okhttp"})
    public static final class Part {
        @Nullable
        private final Headers headers;
        @NotNull
        private final RequestBody body;
        public static final Companion Companion = new Companion(null);

        @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="headers"), level=DeprecationLevel.ERROR)
        @JvmName(name="-deprecated_headers")
        @Nullable
        public final Headers -deprecated_headers() {
            return this.headers;
        }

        @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="body"), level=DeprecationLevel.ERROR)
        @JvmName(name="-deprecated_body")
        @NotNull
        public final RequestBody -deprecated_body() {
            return this.body;
        }

        @JvmName(name="headers")
        @Nullable
        public final Headers headers() {
            return this.headers;
        }

        @JvmName(name="body")
        @NotNull
        public final RequestBody body() {
            return this.body;
        }

        private Part(Headers headers, RequestBody body) {
            this.headers = headers;
            this.body = body;
        }

        public /* synthetic */ Part(Headers headers, RequestBody body, DefaultConstructorMarker $constructor_marker) {
            this(headers, body);
        }

        @JvmStatic
        @NotNull
        public static final Part create(@NotNull RequestBody body) {
            return Companion.create(body);
        }

        @JvmStatic
        @NotNull
        public static final Part create(@Nullable Headers headers, @NotNull RequestBody body) {
            return Companion.create(headers, body);
        }

        @JvmStatic
        @NotNull
        public static final Part createFormData(@NotNull String name, @NotNull String value) {
            return Companion.createFormData(name, value);
        }

        @JvmStatic
        @NotNull
        public static final Part createFormData(@NotNull String name, @Nullable String filename, @NotNull RequestBody body) {
            return Companion.createFormData(name, filename, body);
        }

        @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0018\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0007J\"\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\r\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0007\u001a\u00020\bH\u0007\u00a8\u0006\u000e"}, d2={"Lokhttp3/MultipartBody$Part$Companion;", "", "()V", "create", "Lokhttp3/MultipartBody$Part;", "headers", "Lokhttp3/Headers;", "body", "Lokhttp3/RequestBody;", "createFormData", "name", "", "value", "filename", "okhttp"})
        public static final class Companion {
            @JvmStatic
            @NotNull
            public final Part create(@NotNull RequestBody body) {
                Intrinsics.checkNotNullParameter(body, "body");
                return this.create(null, body);
            }

            @JvmStatic
            @NotNull
            public final Part create(@Nullable Headers headers, @NotNull RequestBody body) {
                Intrinsics.checkNotNullParameter(body, "body");
                Headers headers2 = headers;
                boolean bl = (headers2 != null ? headers2.get("Content-Type") : null) == null;
                boolean bl2 = false;
                boolean bl3 = false;
                if (!bl) {
                    boolean bl4 = false;
                    String string = "Unexpected header: Content-Type";
                    throw (Throwable)new IllegalArgumentException(string.toString());
                }
                Headers headers3 = headers;
                bl = (headers3 != null ? headers3.get("Content-Length") : null) == null;
                bl2 = false;
                bl3 = false;
                if (!bl) {
                    boolean bl5 = false;
                    String string = "Unexpected header: Content-Length";
                    throw (Throwable)new IllegalArgumentException(string.toString());
                }
                return new Part(headers, body, null);
            }

            @JvmStatic
            @NotNull
            public final Part createFormData(@NotNull String name, @NotNull String value) {
                Intrinsics.checkNotNullParameter(name, "name");
                Intrinsics.checkNotNullParameter(value, "value");
                return this.createFormData(name, null, RequestBody.Companion.create$default(RequestBody.Companion, value, null, 1, null));
            }

            @JvmStatic
            @NotNull
            public final Part createFormData(@NotNull String name, @Nullable String filename, @NotNull RequestBody body) {
                Intrinsics.checkNotNullParameter(name, "name");
                Intrinsics.checkNotNullParameter(body, "body");
                boolean bl = false;
                boolean bl2 = false;
                StringBuilder stringBuilder = new StringBuilder();
                boolean bl3 = false;
                boolean bl4 = false;
                StringBuilder $this$buildString = stringBuilder;
                boolean bl5 = false;
                $this$buildString.append("form-data; name=");
                MultipartBody.Companion.appendQuotedString$okhttp($this$buildString, name);
                if (filename != null) {
                    $this$buildString.append("; filename=");
                    MultipartBody.Companion.appendQuotedString$okhttp($this$buildString, filename);
                }
                String string = stringBuilder.toString();
                Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
                String disposition = string;
                Headers headers = new Headers.Builder().addUnsafeNonAscii("Content-Disposition", disposition).build();
                return this.create(headers, body);
            }

            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
                this();
            }
        }
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0011\b\u0007\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u000b\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u0003J \u0010\u000b\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\u00032\b\u0010\u000e\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u000f\u001a\u00020\u0010J\u0018\u0010\u0011\u001a\u00020\u00002\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u000f\u001a\u00020\u0010J\u000e\u0010\u0011\u001a\u00020\u00002\u0006\u0010\u0014\u001a\u00020\bJ\u000e\u0010\u0011\u001a\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u0010J\u0006\u0010\u0015\u001a\u00020\u0016J\u000e\u0010\u0017\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\nR\u000e\u0010\u0002\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0018"}, d2={"Lokhttp3/MultipartBody$Builder;", "", "boundary", "", "(Ljava/lang/String;)V", "Lokio/ByteString;", "parts", "", "Lokhttp3/MultipartBody$Part;", "type", "Lokhttp3/MediaType;", "addFormDataPart", "name", "value", "filename", "body", "Lokhttp3/RequestBody;", "addPart", "headers", "Lokhttp3/Headers;", "part", "build", "Lokhttp3/MultipartBody;", "setType", "okhttp"})
    public static final class Builder {
        private final ByteString boundary;
        private MediaType type;
        private final List<Part> parts;

        @NotNull
        public final Builder setType(@NotNull MediaType type) {
            Intrinsics.checkNotNullParameter(type, "type");
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            boolean bl4 = Intrinsics.areEqual(type.type(), "multipart");
            boolean bl5 = false;
            boolean bl6 = false;
            if (!bl4) {
                boolean bl7 = false;
                String string = "multipart != " + type;
                throw (Throwable)new IllegalArgumentException(string.toString());
            }
            $this$apply.type = type;
            return builder;
        }

        @NotNull
        public final Builder addPart(@NotNull RequestBody body) {
            Intrinsics.checkNotNullParameter(body, "body");
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            $this$apply.addPart(Part.Companion.create(body));
            return builder;
        }

        @NotNull
        public final Builder addPart(@Nullable Headers headers, @NotNull RequestBody body) {
            Intrinsics.checkNotNullParameter(body, "body");
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            $this$apply.addPart(Part.Companion.create(headers, body));
            return builder;
        }

        @NotNull
        public final Builder addFormDataPart(@NotNull String name, @NotNull String value) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(value, "value");
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            $this$apply.addPart(Part.Companion.createFormData(name, value));
            return builder;
        }

        @NotNull
        public final Builder addFormDataPart(@NotNull String name, @Nullable String filename, @NotNull RequestBody body) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(body, "body");
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            $this$apply.addPart(Part.Companion.createFormData(name, filename, body));
            return builder;
        }

        @NotNull
        public final Builder addPart(@NotNull Part part) {
            Intrinsics.checkNotNullParameter(part, "part");
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            Collection collection = $this$apply.parts;
            Part part2 = part;
            boolean bl4 = false;
            collection.add(part2);
            return builder;
        }

        @NotNull
        public final MultipartBody build() {
            Collection collection = this.parts;
            boolean bl = false;
            boolean bl2 = !collection.isEmpty();
            bl = false;
            boolean bl3 = false;
            if (!bl2) {
                boolean bl4 = false;
                String string = "Multipart body must have at least one part.";
                throw (Throwable)new IllegalStateException(string.toString());
            }
            return new MultipartBody(this.boundary, this.type, Util.toImmutableList(this.parts));
        }

        @JvmOverloads
        public Builder(@NotNull String boundary) {
            Intrinsics.checkNotNullParameter(boundary, "boundary");
            this.boundary = ByteString.Companion.encodeUtf8(boundary);
            this.type = MIXED;
            boolean bl = false;
            this.parts = new ArrayList();
        }

        public /* synthetic */ Builder(String string, int n, DefaultConstructorMarker defaultConstructorMarker) {
            if ((n & 1) != 0) {
                String string2 = UUID.randomUUID().toString();
                Intrinsics.checkNotNullExpressionValue(string2, "UUID.randomUUID().toString()");
                string = string2;
            }
            this(string);
        }

        @JvmOverloads
        public Builder() {
            this(null, 1, null);
        }
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u001d\u0010\r\u001a\u00020\u000e*\u00060\u000fj\u0002`\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0000\u00a2\u0006\u0002\b\u0013R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u00020\u00048\u0006X\u0087\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u00020\u00048\u0006X\u0087\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u00020\u00048\u0006X\u0087\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u00020\u00048\u0006X\u0087\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2={"Lokhttp3/MultipartBody$Companion;", "", "()V", "ALTERNATIVE", "Lokhttp3/MediaType;", "COLONSPACE", "", "CRLF", "DASHDASH", "DIGEST", "FORM", "MIXED", "PARALLEL", "appendQuotedString", "", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "key", "", "appendQuotedString$okhttp", "okhttp"})
    public static final class Companion {
        /*
         * WARNING - void declaration
         */
        public final void appendQuotedString$okhttp(@NotNull StringBuilder $this$appendQuotedString, @NotNull String key) {
            Intrinsics.checkNotNullParameter($this$appendQuotedString, "$this$appendQuotedString");
            Intrinsics.checkNotNullParameter(key, "key");
            $this$appendQuotedString.append('\"');
            int n = 0;
            int n2 = key.length();
            while (n < n2) {
                void ch;
                void i;
                char c = key.charAt((int)i);
                switch (ch) {
                    case 10: {
                        $this$appendQuotedString.append("%0A");
                        break;
                    }
                    case 13: {
                        $this$appendQuotedString.append("%0D");
                        break;
                    }
                    case 34: {
                        $this$appendQuotedString.append("%22");
                        break;
                    }
                    default: {
                        $this$appendQuotedString.append((char)ch);
                    }
                }
                ++i;
            }
            $this$appendQuotedString.append('\"');
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

