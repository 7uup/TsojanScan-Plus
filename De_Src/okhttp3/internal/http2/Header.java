/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okhttp3.internal.http2;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0086\b\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0005B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0006\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0007B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0006\u0012\u0006\u0010\u0004\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\bJ\t\u0010\u000b\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\f\u001a\u00020\u0006H\u00c6\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00062\b\b\u0002\u0010\u0004\u001a\u00020\u0006H\u00c6\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0011\u001a\u00020\nH\u00d6\u0001J\b\u0010\u0012\u001a\u00020\u0003H\u0016R\u0010\u0010\t\u001a\u00020\n8\u0006X\u0087\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0002\u001a\u00020\u00068\u0006X\u0087\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u00020\u00068\u0006X\u0087\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2={"Lokhttp3/internal/http2/Header;", "", "name", "", "value", "(Ljava/lang/String;Ljava/lang/String;)V", "Lokio/ByteString;", "(Lokio/ByteString;Ljava/lang/String;)V", "(Lokio/ByteString;Lokio/ByteString;)V", "hpackSize", "", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "Companion", "okhttp"})
public final class Header {
    @JvmField
    public final int hpackSize;
    @JvmField
    @NotNull
    public final ByteString name;
    @JvmField
    @NotNull
    public final ByteString value;
    @JvmField
    @NotNull
    public static final ByteString PSEUDO_PREFIX;
    @NotNull
    public static final String RESPONSE_STATUS_UTF8 = ":status";
    @NotNull
    public static final String TARGET_METHOD_UTF8 = ":method";
    @NotNull
    public static final String TARGET_PATH_UTF8 = ":path";
    @NotNull
    public static final String TARGET_SCHEME_UTF8 = ":scheme";
    @NotNull
    public static final String TARGET_AUTHORITY_UTF8 = ":authority";
    @JvmField
    @NotNull
    public static final ByteString RESPONSE_STATUS;
    @JvmField
    @NotNull
    public static final ByteString TARGET_METHOD;
    @JvmField
    @NotNull
    public static final ByteString TARGET_PATH;
    @JvmField
    @NotNull
    public static final ByteString TARGET_SCHEME;
    @JvmField
    @NotNull
    public static final ByteString TARGET_AUTHORITY;
    public static final Companion Companion;

    @NotNull
    public String toString() {
        return this.name.utf8() + ": " + this.value.utf8();
    }

    public Header(@NotNull ByteString name, @NotNull ByteString value) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(value, "value");
        this.name = name;
        this.value = value;
        this.hpackSize = 32 + this.name.size() + this.value.size();
    }

    public Header(@NotNull String name, @NotNull String value) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(value, "value");
        this(ByteString.Companion.encodeUtf8(name), ByteString.Companion.encodeUtf8(value));
    }

    public Header(@NotNull ByteString name, @NotNull String value) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(value, "value");
        this(name, ByteString.Companion.encodeUtf8(value));
    }

    static {
        Companion = new Companion(null);
        PSEUDO_PREFIX = ByteString.Companion.encodeUtf8(":");
        RESPONSE_STATUS = ByteString.Companion.encodeUtf8(RESPONSE_STATUS_UTF8);
        TARGET_METHOD = ByteString.Companion.encodeUtf8(TARGET_METHOD_UTF8);
        TARGET_PATH = ByteString.Companion.encodeUtf8(TARGET_PATH_UTF8);
        TARGET_SCHEME = ByteString.Companion.encodeUtf8(TARGET_SCHEME_UTF8);
        TARGET_AUTHORITY = ByteString.Companion.encodeUtf8(TARGET_AUTHORITY_UTF8);
    }

    @NotNull
    public final ByteString component1() {
        return this.name;
    }

    @NotNull
    public final ByteString component2() {
        return this.value;
    }

    @NotNull
    public final Header copy(@NotNull ByteString name, @NotNull ByteString value) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(value, "value");
        return new Header(name, value);
    }

    public static /* synthetic */ Header copy$default(Header header, ByteString byteString, ByteString byteString2, int n, Object object) {
        if ((n & 1) != 0) {
            byteString = header.name;
        }
        if ((n & 2) != 0) {
            byteString2 = header.value;
        }
        return header.copy(byteString, byteString2);
    }

    public int hashCode() {
        ByteString byteString = this.name;
        ByteString byteString2 = this.value;
        return (byteString != null ? ((Object)byteString).hashCode() : 0) * 31 + (byteString2 != null ? ((Object)byteString2).hashCode() : 0);
    }

    public boolean equals(@Nullable Object object) {
        block3: {
            block2: {
                if (this == object) break block2;
                if (!(object instanceof Header)) break block3;
                Header header = (Header)object;
                if (!Intrinsics.areEqual(this.name, header.name) || !Intrinsics.areEqual(this.value, header.value)) break block3;
            }
            return true;
        }
        return false;
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\t\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u00020\u00048\u0006X\u0087\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0086T\u00a2\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u00020\u00048\u0006X\u0087\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u0086T\u00a2\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u00020\u00048\u0006X\u0087\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0007X\u0086T\u00a2\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u00020\u00048\u0006X\u0087\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0007X\u0086T\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u00020\u00048\u0006X\u0087\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0007X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0010"}, d2={"Lokhttp3/internal/http2/Header$Companion;", "", "()V", "PSEUDO_PREFIX", "Lokio/ByteString;", "RESPONSE_STATUS", "RESPONSE_STATUS_UTF8", "", "TARGET_AUTHORITY", "TARGET_AUTHORITY_UTF8", "TARGET_METHOD", "TARGET_METHOD_UTF8", "TARGET_PATH", "TARGET_PATH_UTF8", "TARGET_SCHEME", "TARGET_SCHEME_UTF8", "okhttp"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

