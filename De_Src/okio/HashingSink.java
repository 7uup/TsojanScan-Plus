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
import okio.-Util;
import okio.Buffer;
import okio.ByteString;
import okio.ForwardingSink;
import okio.Segment;
import okio.Sink;
import org.jetbrains.annotations.NotNull;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\u0017\b\u0010\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006B\u001f\b\u0010\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\tJ\r\u0010\n\u001a\u00020\bH\u0007\u00a2\u0006\u0002\b\u0010J\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0016R\u0011\u0010\n\u001a\u00020\b8G\u00a2\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0018"}, d2={"Lokio/HashingSink;", "Lokio/ForwardingSink;", "sink", "Lokio/Sink;", "algorithm", "", "(Lokio/Sink;Ljava/lang/String;)V", "key", "Lokio/ByteString;", "(Lokio/Sink;Lokio/ByteString;Ljava/lang/String;)V", "hash", "()Lokio/ByteString;", "mac", "Ljavax/crypto/Mac;", "messageDigest", "Ljava/security/MessageDigest;", "-deprecated_hash", "write", "", "source", "Lokio/Buffer;", "byteCount", "", "Companion", "okio"})
public final class HashingSink
extends ForwardingSink {
    private final MessageDigest messageDigest;
    private final Mac mac;
    public static final Companion Companion = new Companion(null);

    /*
     * WARNING - void declaration
     */
    @Override
    public void write(@NotNull Buffer source2, long byteCount) throws IOException {
        int toHash;
        Intrinsics.checkNotNullParameter(source2, "source");
        -Util.checkOffsetAndCount(source2.size(), 0L, byteCount);
        Segment segment = source2.head;
        Intrinsics.checkNotNull(segment);
        Segment s2 = segment;
        for (long hashedCount = 0L; hashedCount < byteCount; hashedCount += (long)toHash) {
            void a$iv;
            long l = byteCount - hashedCount;
            int b$iv = s2.limit - s2.pos;
            boolean $i$f$minOf = false;
            long l2 = b$iv;
            boolean bl = false;
            toHash = (int)Math.min((long)a$iv, l2);
            if (this.messageDigest != null) {
                this.messageDigest.update(s2.data, s2.pos, toHash);
            } else {
                Mac mac = this.mac;
                Intrinsics.checkNotNull(mac);
                mac.update(s2.data, s2.pos, toHash);
            }
            Intrinsics.checkNotNull(s2.next);
        }
        super.write(source2, byteCount);
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

    public HashingSink(@NotNull Sink sink2, @NotNull String algorithm) {
        Intrinsics.checkNotNullParameter(sink2, "sink");
        Intrinsics.checkNotNullParameter(algorithm, "algorithm");
        super(sink2);
        this.messageDigest = MessageDigest.getInstance(algorithm);
        this.mac = null;
    }

    /*
     * WARNING - void declaration
     */
    public HashingSink(@NotNull Sink sink2, @NotNull ByteString key, @NotNull String algorithm) {
        Intrinsics.checkNotNullParameter(sink2, "sink");
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(algorithm, "algorithm");
        super(sink2);
        try {
            void $this$apply;
            Mac mac = Mac.getInstance(algorithm);
            boolean bl = false;
            boolean bl2 = false;
            Mac mac2 = mac;
            HashingSink hashingSink = this;
            boolean bl3 = false;
            $this$apply.init(new SecretKeySpec(key.toByteArray(), algorithm));
            Unit unit = Unit.INSTANCE;
            hashingSink.mac = mac;
            this.messageDigest = null;
        } catch (InvalidKeyException e) {
            throw (Throwable)new IllegalArgumentException(e);
        }
    }

    @JvmStatic
    @NotNull
    public static final HashingSink md5(@NotNull Sink sink2) {
        return Companion.md5(sink2);
    }

    @JvmStatic
    @NotNull
    public static final HashingSink sha1(@NotNull Sink sink2) {
        return Companion.sha1(sink2);
    }

    @JvmStatic
    @NotNull
    public static final HashingSink sha256(@NotNull Sink sink2) {
        return Companion.sha256(sink2);
    }

    @JvmStatic
    @NotNull
    public static final HashingSink sha512(@NotNull Sink sink2) {
        return Companion.sha512(sink2);
    }

    @JvmStatic
    @NotNull
    public static final HashingSink hmacSha1(@NotNull Sink sink2, @NotNull ByteString key) {
        return Companion.hmacSha1(sink2, key);
    }

    @JvmStatic
    @NotNull
    public static final HashingSink hmacSha256(@NotNull Sink sink2, @NotNull ByteString key) {
        return Companion.hmacSha256(sink2, key);
    }

    @JvmStatic
    @NotNull
    public static final HashingSink hmacSha512(@NotNull Sink sink2, @NotNull ByteString key) {
        return Companion.hmacSha512(sink2, key);
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0018\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0018\u0010\n\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0010\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\f\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\r\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007\u00a8\u0006\u000f"}, d2={"Lokio/HashingSink$Companion;", "", "()V", "hmacSha1", "Lokio/HashingSink;", "sink", "Lokio/Sink;", "key", "Lokio/ByteString;", "hmacSha256", "hmacSha512", "md5", "sha1", "sha256", "sha512", "okio"})
    public static final class Companion {
        @JvmStatic
        @NotNull
        public final HashingSink md5(@NotNull Sink sink2) {
            Intrinsics.checkNotNullParameter(sink2, "sink");
            return new HashingSink(sink2, "MD5");
        }

        @JvmStatic
        @NotNull
        public final HashingSink sha1(@NotNull Sink sink2) {
            Intrinsics.checkNotNullParameter(sink2, "sink");
            return new HashingSink(sink2, "SHA-1");
        }

        @JvmStatic
        @NotNull
        public final HashingSink sha256(@NotNull Sink sink2) {
            Intrinsics.checkNotNullParameter(sink2, "sink");
            return new HashingSink(sink2, "SHA-256");
        }

        @JvmStatic
        @NotNull
        public final HashingSink sha512(@NotNull Sink sink2) {
            Intrinsics.checkNotNullParameter(sink2, "sink");
            return new HashingSink(sink2, "SHA-512");
        }

        @JvmStatic
        @NotNull
        public final HashingSink hmacSha1(@NotNull Sink sink2, @NotNull ByteString key) {
            Intrinsics.checkNotNullParameter(sink2, "sink");
            Intrinsics.checkNotNullParameter(key, "key");
            return new HashingSink(sink2, key, "HmacSHA1");
        }

        @JvmStatic
        @NotNull
        public final HashingSink hmacSha256(@NotNull Sink sink2, @NotNull ByteString key) {
            Intrinsics.checkNotNullParameter(sink2, "sink");
            Intrinsics.checkNotNullParameter(key, "key");
            return new HashingSink(sink2, key, "HmacSHA256");
        }

        @JvmStatic
        @NotNull
        public final HashingSink hmacSha512(@NotNull Sink sink2, @NotNull ByteString key) {
            Intrinsics.checkNotNullParameter(sink2, "sink");
            Intrinsics.checkNotNullParameter(key, "key");
            return new HashingSink(sink2, key, "HmacSHA512");
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

