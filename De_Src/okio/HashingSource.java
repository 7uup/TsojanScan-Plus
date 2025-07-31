/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okio;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okio.Buffer;
import okio.ByteString;
import okio.ForwardingSource;
import okio.Segment;
import okio.Source;
import org.jetbrains.annotations.NotNull;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u0017\b\u0010\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006B\u001f\b\u0010\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\tJ\r\u0010\n\u001a\u00020\bH\u0007\u00a2\u0006\u0002\b\u0010J\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0012H\u0016R\u0011\u0010\n\u001a\u00020\b8G\u00a2\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0017"}, d2={"Lokio/HashingSource;", "Lokio/ForwardingSource;", "source", "Lokio/Source;", "algorithm", "", "(Lokio/Source;Ljava/lang/String;)V", "key", "Lokio/ByteString;", "(Lokio/Source;Lokio/ByteString;Ljava/lang/String;)V", "hash", "()Lokio/ByteString;", "mac", "Ljavax/crypto/Mac;", "messageDigest", "Ljava/security/MessageDigest;", "-deprecated_hash", "read", "", "sink", "Lokio/Buffer;", "byteCount", "Companion", "okio"})
public final class HashingSource
extends ForwardingSource {
    private final MessageDigest messageDigest;
    private final Mac mac;
    public static final Companion Companion = new Companion(null);

    @Override
    public long read(@NotNull Buffer sink2, long byteCount) throws IOException {
        Intrinsics.checkNotNullParameter(sink2, "sink");
        long result = super.read(sink2, byteCount);
        if (result != -1L) {
            long offset;
            long start = sink2.size() - result;
            Segment segment = sink2.head;
            Intrinsics.checkNotNull(segment);
            Segment s2 = segment;
            for (offset = sink2.size(); offset > start; offset -= (long)(s2.limit - s2.pos)) {
                Intrinsics.checkNotNull(s2.prev);
            }
            while (offset < sink2.size()) {
                int pos = (int)((long)s2.pos + start - offset);
                if (this.messageDigest != null) {
                    this.messageDigest.update(s2.data, pos, s2.limit - pos);
                } else {
                    Mac mac = this.mac;
                    Intrinsics.checkNotNull(mac);
                    mac.update(s2.data, pos, s2.limit - pos);
                }
                start = offset += (long)(s2.limit - s2.pos);
                Intrinsics.checkNotNull(s2.next);
            }
        }
        return result;
    }

    @JvmName(name="hash")
    @NotNull
    public final ByteString hash() {
        byte[] byArray;
        if (this.messageDigest != null) {
            byArray = this.messageDigest.digest();
        } else {
            Mac mac = this.mac;
            Intrinsics.checkNotNull(mac);
            byArray = mac.doFinal();
        }
        byte[] result = byArray;
        Intrinsics.checkNotNullExpressionValue(result, "result");
        return new ByteString(result);
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="hash"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_hash")
    @NotNull
    public final ByteString -deprecated_hash() {
        return this.hash();
    }

    public HashingSource(@NotNull Source source2, @NotNull String algorithm) {
        Intrinsics.checkNotNullParameter(source2, "source");
        Intrinsics.checkNotNullParameter(algorithm, "algorithm");
        super(source2);
        this.messageDigest = MessageDigest.getInstance(algorithm);
        this.mac = null;
    }

    /*
     * WARNING - void declaration
     */
    public HashingSource(@NotNull Source source2, @NotNull ByteString key, @NotNull String algorithm) {
        Intrinsics.checkNotNullParameter(source2, "source");
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(algorithm, "algorithm");
        super(source2);
        try {
            void $this$apply;
            Mac mac = Mac.getInstance(algorithm);
            boolean bl = false;
            boolean bl2 = false;
            Mac mac2 = mac;
            HashingSource hashingSource = this;
            boolean bl3 = false;
            $this$apply.init(new SecretKeySpec(key.toByteArray(), algorithm));
            Unit unit = Unit.INSTANCE;
            hashingSource.mac = mac;
            this.messageDigest = null;
        } catch (InvalidKeyException e) {
            throw (Throwable)new IllegalArgumentException(e);
        }
    }

    @JvmStatic
    @NotNull
    public static final HashingSource md5(@NotNull Source source2) {
        return Companion.md5(source2);
    }

    @JvmStatic
    @NotNull
    public static final HashingSource sha1(@NotNull Source source2) {
        return Companion.sha1(source2);
    }

    @JvmStatic
    @NotNull
    public static final HashingSource sha256(@NotNull Source source2) {
        return Companion.sha256(source2);
    }

    @JvmStatic
    @NotNull
    public static final HashingSource sha512(@NotNull Source source2) {
        return Companion.sha512(source2);
    }

    @JvmStatic
    @NotNull
    public static final HashingSource hmacSha1(@NotNull Source source2, @NotNull ByteString key) {
        return Companion.hmacSha1(source2, key);
    }

    @JvmStatic
    @NotNull
    public static final HashingSource hmacSha256(@NotNull Source source2, @NotNull ByteString key) {
        return Companion.hmacSha256(source2, key);
    }

    @JvmStatic
    @NotNull
    public static final HashingSource hmacSha512(@NotNull Source source2, @NotNull ByteString key) {
        return Companion.hmacSha512(source2, key);
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0018\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0018\u0010\n\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0010\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\f\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\r\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007\u00a8\u0006\u000f"}, d2={"Lokio/HashingSource$Companion;", "", "()V", "hmacSha1", "Lokio/HashingSource;", "source", "Lokio/Source;", "key", "Lokio/ByteString;", "hmacSha256", "hmacSha512", "md5", "sha1", "sha256", "sha512", "okio"})
    public static final class Companion {
        @JvmStatic
        @NotNull
        public final HashingSource md5(@NotNull Source source2) {
            Intrinsics.checkNotNullParameter(source2, "source");
            return new HashingSource(source2, "MD5");
        }

        @JvmStatic
        @NotNull
        public final HashingSource sha1(@NotNull Source source2) {
            Intrinsics.checkNotNullParameter(source2, "source");
            return new HashingSource(source2, "SHA-1");
        }

        @JvmStatic
        @NotNull
        public final HashingSource sha256(@NotNull Source source2) {
            Intrinsics.checkNotNullParameter(source2, "source");
            return new HashingSource(source2, "SHA-256");
        }

        @JvmStatic
        @NotNull
        public final HashingSource sha512(@NotNull Source source2) {
            Intrinsics.checkNotNullParameter(source2, "source");
            return new HashingSource(source2, "SHA-512");
        }

        @JvmStatic
        @NotNull
        public final HashingSource hmacSha1(@NotNull Source source2, @NotNull ByteString key) {
            Intrinsics.checkNotNullParameter(source2, "source");
            Intrinsics.checkNotNullParameter(key, "key");
            return new HashingSource(source2, key, "HmacSHA1");
        }

        @JvmStatic
        @NotNull
        public final HashingSource hmacSha256(@NotNull Source source2, @NotNull ByteString key) {
            Intrinsics.checkNotNullParameter(source2, "source");
            Intrinsics.checkNotNullParameter(key, "key");
            return new HashingSource(source2, key, "HmacSHA256");
        }

        @JvmStatic
        @NotNull
        public final HashingSource hmacSha512(@NotNull Source source2, @NotNull ByteString key) {
            Intrinsics.checkNotNullParameter(source2, "source");
            Intrinsics.checkNotNullParameter(key, "key");
            return new HashingSource(source2, key, "HmacSHA512");
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

