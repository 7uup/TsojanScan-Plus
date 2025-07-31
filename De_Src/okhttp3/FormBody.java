/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okhttp3;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.internal.Util;
import okio.Buffer;
import okio.BufferedSink;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000 \u001c2\u00020\u0001:\u0002\u001b\u001cB#\b\u0000\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\u0002\u0010\u0006J\b\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\rH\u0016J\u000e\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\bJ\u000e\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\bJ\u000e\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\bJ\r\u0010\u0007\u001a\u00020\bH\u0007\u00a2\u0006\u0002\b\u0012J\u000e\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\bJ\u001a\u0010\u0014\u001a\u00020\u000b2\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0015\u001a\u00020\u0016H\u0016R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0007\u001a\u00020\b8G\u00a2\u0006\u0006\u001a\u0004\b\u0007\u0010\t\u00a8\u0006\u001d"}, d2={"Lokhttp3/FormBody;", "Lokhttp3/RequestBody;", "encodedNames", "", "", "encodedValues", "(Ljava/util/List;Ljava/util/List;)V", "size", "", "()I", "contentLength", "", "contentType", "Lokhttp3/MediaType;", "encodedName", "index", "encodedValue", "name", "-deprecated_size", "value", "writeOrCountBytes", "sink", "Lokio/BufferedSink;", "countBytes", "", "writeTo", "", "Builder", "Companion", "okhttp"})
public final class FormBody
extends RequestBody {
    private final List<String> encodedNames;
    private final List<String> encodedValues;
    private static final MediaType CONTENT_TYPE;
    public static final Companion Companion;

    @JvmName(name="size")
    public final int size() {
        return this.encodedNames.size();
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="size"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_size")
    public final int -deprecated_size() {
        return this.size();
    }

    @NotNull
    public final String encodedName(int index) {
        return this.encodedNames.get(index);
    }

    @NotNull
    public final String name(int index) {
        return HttpUrl.Companion.percentDecode$okhttp$default(HttpUrl.Companion, this.encodedName(index), 0, 0, true, 3, null);
    }

    @NotNull
    public final String encodedValue(int index) {
        return this.encodedValues.get(index);
    }

    @NotNull
    public final String value(int index) {
        return HttpUrl.Companion.percentDecode$okhttp$default(HttpUrl.Companion, this.encodedValue(index), 0, 0, true, 3, null);
    }

    @Override
    @NotNull
    public MediaType contentType() {
        return CONTENT_TYPE;
    }

    @Override
    public long contentLength() {
        return this.writeOrCountBytes(null, true);
    }

    @Override
    public void writeTo(@NotNull BufferedSink sink2) throws IOException {
        Intrinsics.checkNotNullParameter(sink2, "sink");
        this.writeOrCountBytes(sink2, false);
    }

    /*
     * WARNING - void declaration
     */
    private final long writeOrCountBytes(BufferedSink sink2, boolean countBytes) {
        Buffer buffer;
        long byteCount = 0L;
        if (countBytes) {
            buffer = new Buffer();
        } else {
            BufferedSink bufferedSink = sink2;
            Intrinsics.checkNotNull(bufferedSink);
            buffer = bufferedSink.getBuffer();
        }
        Buffer buffer2 = buffer;
        int n = 0;
        int n2 = this.encodedNames.size();
        while (n < n2) {
            void i;
            if (i > 0) {
                buffer2.writeByte(38);
            }
            buffer2.writeUtf8(this.encodedNames.get((int)i));
            buffer2.writeByte(61);
            buffer2.writeUtf8(this.encodedValues.get((int)i));
            ++i;
        }
        if (countBytes) {
            byteCount = buffer2.size();
            buffer2.clear();
        }
        return byteCount;
    }

    public FormBody(@NotNull List<String> encodedNames, @NotNull List<String> encodedValues) {
        Intrinsics.checkNotNullParameter(encodedNames, "encodedNames");
        Intrinsics.checkNotNullParameter(encodedValues, "encodedValues");
        this.encodedNames = Util.toImmutableList(encodedNames);
        this.encodedValues = Util.toImmutableList(encodedValues);
    }

    static {
        Companion = new Companion(null);
        CONTENT_TYPE = MediaType.Companion.get("application/x-www-form-urlencoded");
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0013\b\u0007\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\t\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u0007J\u0016\u0010\f\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u0007J\u0006\u0010\r\u001a\u00020\u000eR\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2={"Lokhttp3/FormBody$Builder;", "", "charset", "Ljava/nio/charset/Charset;", "(Ljava/nio/charset/Charset;)V", "names", "", "", "values", "add", "name", "value", "addEncoded", "build", "Lokhttp3/FormBody;", "okhttp"})
    public static final class Builder {
        private final List<String> names;
        private final List<String> values;
        private final Charset charset;

        @NotNull
        public final Builder add(@NotNull String name, @NotNull String value) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(value, "value");
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            Collection collection = $this$apply.names;
            String string = HttpUrl.Companion.canonicalize$okhttp$default(HttpUrl.Companion, name, 0, 0, " \"':;<=>@[]^`{}|/\\?#&!$(),~", false, false, true, false, $this$apply.charset, 91, null);
            boolean bl4 = false;
            collection.add(string);
            collection = $this$apply.values;
            string = HttpUrl.Companion.canonicalize$okhttp$default(HttpUrl.Companion, value, 0, 0, " \"':;<=>@[]^`{}|/\\?#&!$(),~", false, false, true, false, $this$apply.charset, 91, null);
            bl4 = false;
            collection.add(string);
            return builder;
        }

        @NotNull
        public final Builder addEncoded(@NotNull String name, @NotNull String value) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(value, "value");
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            Collection collection = $this$apply.names;
            String string = HttpUrl.Companion.canonicalize$okhttp$default(HttpUrl.Companion, name, 0, 0, " \"':;<=>@[]^`{}|/\\?#&!$(),~", true, false, true, false, $this$apply.charset, 83, null);
            boolean bl4 = false;
            collection.add(string);
            collection = $this$apply.values;
            string = HttpUrl.Companion.canonicalize$okhttp$default(HttpUrl.Companion, value, 0, 0, " \"':;<=>@[]^`{}|/\\?#&!$(),~", true, false, true, false, $this$apply.charset, 83, null);
            bl4 = false;
            collection.add(string);
            return builder;
        }

        @NotNull
        public final FormBody build() {
            return new FormBody(this.names, this.values);
        }

        @JvmOverloads
        public Builder(@Nullable Charset charset) {
            this.charset = charset;
            boolean bl = false;
            this.names = new ArrayList();
            bl = false;
            this.values = new ArrayList();
        }

        public /* synthetic */ Builder(Charset charset, int n, DefaultConstructorMarker defaultConstructorMarker) {
            if ((n & 1) != 0) {
                charset = null;
            }
            this(charset);
        }

        @JvmOverloads
        public Builder() {
            this(null, 1, null);
        }
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2={"Lokhttp3/FormBody$Companion;", "", "()V", "CONTENT_TYPE", "Lokhttp3/MediaType;", "okhttp"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

