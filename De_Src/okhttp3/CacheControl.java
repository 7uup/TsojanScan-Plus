/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okhttp3;

import java.util.concurrent.TimeUnit;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.Headers;
import okhttp3.internal.Util;
import org.jetbrains.annotations.NotNull;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u000e\n\u0002\b\u0011\u0018\u0000 !2\u00020\u0001:\u0002 !Bq\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0006\u0012\u0006\u0010\f\u001a\u00020\u0006\u0012\u0006\u0010\r\u001a\u00020\u0003\u0012\u0006\u0010\u000e\u001a\u00020\u0003\u0012\u0006\u0010\u000f\u001a\u00020\u0003\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011\u00a2\u0006\u0002\u0010\u0012J\r\u0010\u000f\u001a\u00020\u0003H\u0007\u00a2\u0006\u0002\b\u0015J\r\u0010\u0005\u001a\u00020\u0006H\u0007\u00a2\u0006\u0002\b\u0016J\r\u0010\u000b\u001a\u00020\u0006H\u0007\u00a2\u0006\u0002\b\u0017J\r\u0010\f\u001a\u00020\u0006H\u0007\u00a2\u0006\u0002\b\u0018J\r\u0010\n\u001a\u00020\u0003H\u0007\u00a2\u0006\u0002\b\u0019J\r\u0010\u0002\u001a\u00020\u0003H\u0007\u00a2\u0006\u0002\b\u001aJ\r\u0010\u0004\u001a\u00020\u0003H\u0007\u00a2\u0006\u0002\b\u001bJ\r\u0010\u000e\u001a\u00020\u0003H\u0007\u00a2\u0006\u0002\b\u001cJ\r\u0010\r\u001a\u00020\u0003H\u0007\u00a2\u0006\u0002\b\u001dJ\r\u0010\u0007\u001a\u00020\u0006H\u0007\u00a2\u0006\u0002\b\u001eJ\b\u0010\u001f\u001a\u00020\u0011H\u0016R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0013\u0010\u000f\u001a\u00020\u00038\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0013R\u0011\u0010\b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0013R\u0011\u0010\t\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0013R\u0013\u0010\u0005\u001a\u00020\u00068\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0014R\u0013\u0010\u000b\u001a\u00020\u00068\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0014R\u0013\u0010\f\u001a\u00020\u00068\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0014R\u0013\u0010\n\u001a\u00020\u00038\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0013R\u0013\u0010\u0002\u001a\u00020\u00038\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0013R\u0013\u0010\u0004\u001a\u00020\u00038\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0013R\u0013\u0010\u000e\u001a\u00020\u00038\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0013R\u0013\u0010\r\u001a\u00020\u00038\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0013R\u0013\u0010\u0007\u001a\u00020\u00068\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u0014\u00a8\u0006\""}, d2={"Lokhttp3/CacheControl;", "", "noCache", "", "noStore", "maxAgeSeconds", "", "sMaxAgeSeconds", "isPrivate", "isPublic", "mustRevalidate", "maxStaleSeconds", "minFreshSeconds", "onlyIfCached", "noTransform", "immutable", "headerValue", "", "(ZZIIZZZIIZZZLjava/lang/String;)V", "()Z", "()I", "-deprecated_immutable", "-deprecated_maxAgeSeconds", "-deprecated_maxStaleSeconds", "-deprecated_minFreshSeconds", "-deprecated_mustRevalidate", "-deprecated_noCache", "-deprecated_noStore", "-deprecated_noTransform", "-deprecated_onlyIfCached", "-deprecated_sMaxAgeSeconds", "toString", "Builder", "Companion", "okhttp"})
public final class CacheControl {
    private final boolean noCache;
    private final boolean noStore;
    private final int maxAgeSeconds;
    private final int sMaxAgeSeconds;
    private final boolean isPrivate;
    private final boolean isPublic;
    private final boolean mustRevalidate;
    private final int maxStaleSeconds;
    private final int minFreshSeconds;
    private final boolean onlyIfCached;
    private final boolean noTransform;
    private final boolean immutable;
    private String headerValue;
    @JvmField
    @NotNull
    public static final CacheControl FORCE_NETWORK;
    @JvmField
    @NotNull
    public static final CacheControl FORCE_CACHE;
    public static final Companion Companion;

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="noCache"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_noCache")
    public final boolean -deprecated_noCache() {
        return this.noCache;
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="noStore"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_noStore")
    public final boolean -deprecated_noStore() {
        return this.noStore;
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="maxAgeSeconds"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_maxAgeSeconds")
    public final int -deprecated_maxAgeSeconds() {
        return this.maxAgeSeconds;
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="sMaxAgeSeconds"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_sMaxAgeSeconds")
    public final int -deprecated_sMaxAgeSeconds() {
        return this.sMaxAgeSeconds;
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="mustRevalidate"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_mustRevalidate")
    public final boolean -deprecated_mustRevalidate() {
        return this.mustRevalidate;
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="maxStaleSeconds"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_maxStaleSeconds")
    public final int -deprecated_maxStaleSeconds() {
        return this.maxStaleSeconds;
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="minFreshSeconds"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_minFreshSeconds")
    public final int -deprecated_minFreshSeconds() {
        return this.minFreshSeconds;
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="onlyIfCached"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_onlyIfCached")
    public final boolean -deprecated_onlyIfCached() {
        return this.onlyIfCached;
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="noTransform"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_noTransform")
    public final boolean -deprecated_noTransform() {
        return this.noTransform;
    }

    @Deprecated(message="moved to val", replaceWith=@ReplaceWith(imports={}, expression="immutable"), level=DeprecationLevel.ERROR)
    @JvmName(name="-deprecated_immutable")
    public final boolean -deprecated_immutable() {
        return this.immutable;
    }

    @NotNull
    public String toString() {
        String result = this.headerValue;
        if (result == null) {
            boolean bl = false;
            boolean bl2 = false;
            StringBuilder stringBuilder = new StringBuilder();
            boolean bl3 = false;
            boolean bl4 = false;
            StringBuilder $this$buildString = stringBuilder;
            boolean bl5 = false;
            if (this.noCache) {
                $this$buildString.append("no-cache, ");
            }
            if (this.noStore) {
                $this$buildString.append("no-store, ");
            }
            if (this.maxAgeSeconds != -1) {
                $this$buildString.append("max-age=").append(this.maxAgeSeconds).append(", ");
            }
            if (this.sMaxAgeSeconds != -1) {
                $this$buildString.append("s-maxage=").append(this.sMaxAgeSeconds).append(", ");
            }
            if (this.isPrivate) {
                $this$buildString.append("private, ");
            }
            if (this.isPublic) {
                $this$buildString.append("public, ");
            }
            if (this.mustRevalidate) {
                $this$buildString.append("must-revalidate, ");
            }
            if (this.maxStaleSeconds != -1) {
                $this$buildString.append("max-stale=").append(this.maxStaleSeconds).append(", ");
            }
            if (this.minFreshSeconds != -1) {
                $this$buildString.append("min-fresh=").append(this.minFreshSeconds).append(", ");
            }
            if (this.onlyIfCached) {
                $this$buildString.append("only-if-cached, ");
            }
            if (this.noTransform) {
                $this$buildString.append("no-transform, ");
            }
            if (this.immutable) {
                $this$buildString.append("immutable, ");
            }
            CharSequence charSequence = $this$buildString;
            boolean bl6 = false;
            if (charSequence.length() == 0) {
                return "";
            }
            $this$buildString.delete($this$buildString.length() - 2, $this$buildString.length());
            String string = stringBuilder.toString();
            Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
            this.headerValue = result = string;
        }
        return result;
    }

    @JvmName(name="noCache")
    public final boolean noCache() {
        return this.noCache;
    }

    @JvmName(name="noStore")
    public final boolean noStore() {
        return this.noStore;
    }

    @JvmName(name="maxAgeSeconds")
    public final int maxAgeSeconds() {
        return this.maxAgeSeconds;
    }

    @JvmName(name="sMaxAgeSeconds")
    public final int sMaxAgeSeconds() {
        return this.sMaxAgeSeconds;
    }

    public final boolean isPrivate() {
        return this.isPrivate;
    }

    public final boolean isPublic() {
        return this.isPublic;
    }

    @JvmName(name="mustRevalidate")
    public final boolean mustRevalidate() {
        return this.mustRevalidate;
    }

    @JvmName(name="maxStaleSeconds")
    public final int maxStaleSeconds() {
        return this.maxStaleSeconds;
    }

    @JvmName(name="minFreshSeconds")
    public final int minFreshSeconds() {
        return this.minFreshSeconds;
    }

    @JvmName(name="onlyIfCached")
    public final boolean onlyIfCached() {
        return this.onlyIfCached;
    }

    @JvmName(name="noTransform")
    public final boolean noTransform() {
        return this.noTransform;
    }

    @JvmName(name="immutable")
    public final boolean immutable() {
        return this.immutable;
    }

    private CacheControl(boolean noCache, boolean noStore, int maxAgeSeconds, int sMaxAgeSeconds, boolean isPrivate, boolean isPublic, boolean mustRevalidate, int maxStaleSeconds, int minFreshSeconds, boolean onlyIfCached, boolean noTransform, boolean immutable, String headerValue) {
        this.noCache = noCache;
        this.noStore = noStore;
        this.maxAgeSeconds = maxAgeSeconds;
        this.sMaxAgeSeconds = sMaxAgeSeconds;
        this.isPrivate = isPrivate;
        this.isPublic = isPublic;
        this.mustRevalidate = mustRevalidate;
        this.maxStaleSeconds = maxStaleSeconds;
        this.minFreshSeconds = minFreshSeconds;
        this.onlyIfCached = onlyIfCached;
        this.noTransform = noTransform;
        this.immutable = immutable;
        this.headerValue = headerValue;
    }

    static {
        Companion = new Companion(null);
        FORCE_NETWORK = new Builder().noCache().build();
        FORCE_CACHE = new Builder().onlyIfCached().maxStale(Integer.MAX_VALUE, TimeUnit.SECONDS).build();
    }

    public /* synthetic */ CacheControl(boolean noCache, boolean noStore, int maxAgeSeconds, int sMaxAgeSeconds, boolean isPrivate, boolean isPublic, boolean mustRevalidate, int maxStaleSeconds, int minFreshSeconds, boolean onlyIfCached, boolean noTransform, boolean immutable, String headerValue, DefaultConstructorMarker $constructor_marker) {
        this(noCache, noStore, maxAgeSeconds, sMaxAgeSeconds, isPrivate, isPublic, mustRevalidate, maxStaleSeconds, minFreshSeconds, onlyIfCached, noTransform, immutable, headerValue);
    }

    @JvmStatic
    @NotNull
    public static final CacheControl parse(@NotNull Headers headers) {
        return Companion.parse(headers);
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\r\u001a\u00020\u000eJ\u0006\u0010\u0003\u001a\u00020\u0000J\u0016\u0010\u000f\u001a\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u0011J\u0016\u0010\u0012\u001a\u00020\u00002\u0006\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u0011J\u0016\u0010\u0013\u001a\u00020\u00002\u0006\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u0011J\u0006\u0010\t\u001a\u00020\u0000J\u0006\u0010\n\u001a\u00020\u0000J\u0006\u0010\u000b\u001a\u00020\u0000J\u0006\u0010\f\u001a\u00020\u0000J\f\u0010\u0014\u001a\u00020\u0006*\u00020\u0015H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0016"}, d2={"Lokhttp3/CacheControl$Builder;", "", "()V", "immutable", "", "maxAgeSeconds", "", "maxStaleSeconds", "minFreshSeconds", "noCache", "noStore", "noTransform", "onlyIfCached", "build", "Lokhttp3/CacheControl;", "maxAge", "timeUnit", "Ljava/util/concurrent/TimeUnit;", "maxStale", "minFresh", "clampToInt", "", "okhttp"})
    public static final class Builder {
        private boolean noCache;
        private boolean noStore;
        private int maxAgeSeconds = -1;
        private int maxStaleSeconds = -1;
        private int minFreshSeconds = -1;
        private boolean onlyIfCached;
        private boolean noTransform;
        private boolean immutable;

        @NotNull
        public final Builder noCache() {
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            $this$apply.noCache = true;
            return builder;
        }

        @NotNull
        public final Builder noStore() {
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            $this$apply.noStore = true;
            return builder;
        }

        @NotNull
        public final Builder maxAge(int maxAge, @NotNull TimeUnit timeUnit) {
            Intrinsics.checkNotNullParameter((Object)timeUnit, "timeUnit");
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            boolean bl4 = maxAge >= 0;
            boolean bl5 = false;
            boolean bl6 = false;
            if (!bl4) {
                boolean bl7 = false;
                String string = "maxAge < 0: " + maxAge;
                throw (Throwable)new IllegalArgumentException(string.toString());
            }
            long maxAgeSecondsLong = timeUnit.toSeconds(maxAge);
            $this$apply.maxAgeSeconds = $this$apply.clampToInt(maxAgeSecondsLong);
            return builder;
        }

        @NotNull
        public final Builder maxStale(int maxStale, @NotNull TimeUnit timeUnit) {
            Intrinsics.checkNotNullParameter((Object)timeUnit, "timeUnit");
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            boolean bl4 = maxStale >= 0;
            boolean bl5 = false;
            boolean bl6 = false;
            if (!bl4) {
                boolean bl7 = false;
                String string = "maxStale < 0: " + maxStale;
                throw (Throwable)new IllegalArgumentException(string.toString());
            }
            long maxStaleSecondsLong = timeUnit.toSeconds(maxStale);
            $this$apply.maxStaleSeconds = $this$apply.clampToInt(maxStaleSecondsLong);
            return builder;
        }

        @NotNull
        public final Builder minFresh(int minFresh, @NotNull TimeUnit timeUnit) {
            Intrinsics.checkNotNullParameter((Object)timeUnit, "timeUnit");
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            boolean bl4 = minFresh >= 0;
            boolean bl5 = false;
            boolean bl6 = false;
            if (!bl4) {
                boolean bl7 = false;
                String string = "minFresh < 0: " + minFresh;
                throw (Throwable)new IllegalArgumentException(string.toString());
            }
            long minFreshSecondsLong = timeUnit.toSeconds(minFresh);
            $this$apply.minFreshSeconds = $this$apply.clampToInt(minFreshSecondsLong);
            return builder;
        }

        @NotNull
        public final Builder onlyIfCached() {
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            $this$apply.onlyIfCached = true;
            return builder;
        }

        @NotNull
        public final Builder noTransform() {
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            $this$apply.noTransform = true;
            return builder;
        }

        @NotNull
        public final Builder immutable() {
            Builder builder = this;
            boolean bl = false;
            boolean bl2 = false;
            Builder $this$apply = builder;
            boolean bl3 = false;
            $this$apply.immutable = true;
            return builder;
        }

        private final int clampToInt(long $this$clampToInt) {
            return $this$clampToInt > (long)Integer.MAX_VALUE ? Integer.MAX_VALUE : (int)$this$clampToInt;
        }

        @NotNull
        public final CacheControl build() {
            return new CacheControl(this.noCache, this.noStore, this.maxAgeSeconds, -1, false, false, false, this.maxStaleSeconds, this.minFreshSeconds, this.onlyIfCached, this.noTransform, this.immutable, null, null);
        }
    }

    @Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=1, d1={"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u001e\u0010\t\u001a\u00020\n*\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\nH\u0002R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u00020\u00048\u0006X\u0087\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000e"}, d2={"Lokhttp3/CacheControl$Companion;", "", "()V", "FORCE_CACHE", "Lokhttp3/CacheControl;", "FORCE_NETWORK", "parse", "headers", "Lokhttp3/Headers;", "indexOfElement", "", "", "characters", "startIndex", "okhttp"})
    public static final class Companion {
        /*
         * WARNING - void declaration
         */
        @JvmStatic
        @NotNull
        public final CacheControl parse(@NotNull Headers headers) {
            Intrinsics.checkNotNullParameter(headers, "headers");
            boolean noCache = false;
            boolean noStore = false;
            int maxAgeSeconds = -1;
            int sMaxAgeSeconds = -1;
            boolean isPrivate = false;
            boolean isPublic = false;
            boolean mustRevalidate = false;
            int maxStaleSeconds = -1;
            int minFreshSeconds = -1;
            boolean onlyIfCached = false;
            boolean noTransform = false;
            boolean immutable = false;
            boolean canUseHeaderValue = true;
            String headerValue = null;
            int n = 0;
            int n2 = headers.size();
            while (n < n2) {
                void i;
                block30: {
                    String value;
                    block29: {
                        String name;
                        block28: {
                            name = headers.name((int)i);
                            value = headers.value((int)i);
                            if (!StringsKt.equals(name, "Cache-Control", true)) break block28;
                            if (headerValue != null) {
                                canUseHeaderValue = false;
                            } else {
                                headerValue = value;
                            }
                            break block29;
                        }
                        if (!StringsKt.equals(name, "Pragma", true)) break block30;
                        canUseHeaderValue = false;
                    }
                    int pos = 0;
                    while (pos < value.length()) {
                        int tokenStart = pos;
                        pos = this.indexOfElement(value, "=,;", pos);
                        String string = value;
                        boolean bl = false;
                        String string2 = string;
                        if (string2 == null) {
                            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                        }
                        String string3 = string2.substring(tokenStart, pos);
                        Intrinsics.checkNotNullExpressionValue(string3, "(this as java.lang.Strin\u2026ing(startIndex, endIndex)");
                        string = string3;
                        bl = false;
                        String string4 = string;
                        if (string4 == null) {
                            throw new NullPointerException("null cannot be cast to non-null type kotlin.CharSequence");
                        }
                        String directive = ((Object)StringsKt.trim((CharSequence)string4)).toString();
                        String parameter = null;
                        if (pos == value.length() || value.charAt(pos) == ',' || value.charAt(pos) == ';') {
                            ++pos;
                            parameter = null;
                        } else {
                            boolean bl2;
                            String string5;
                            int parameterStart;
                            ++pos;
                            if ((pos = Util.indexOfNonWhitespace(value, pos)) < value.length() && value.charAt(pos) == '\"') {
                                parameterStart = ++pos;
                                pos = StringsKt.indexOf$default((CharSequence)value, '\"', pos, false, 4, null);
                                string5 = value;
                                bl2 = false;
                                String string6 = string5;
                                if (string6 == null) {
                                    throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                                }
                                String string7 = string6.substring(parameterStart, pos);
                                Intrinsics.checkNotNullExpressionValue(string7, "(this as java.lang.Strin\u2026ing(startIndex, endIndex)");
                                parameter = string7;
                                ++pos;
                            } else {
                                parameterStart = pos;
                                pos = this.indexOfElement(value, ",;", pos);
                                string5 = value;
                                bl2 = false;
                                String string8 = string5;
                                if (string8 == null) {
                                    throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                                }
                                String string9 = string8.substring(parameterStart, pos);
                                Intrinsics.checkNotNullExpressionValue(string9, "(this as java.lang.Strin\u2026ing(startIndex, endIndex)");
                                string5 = string9;
                                bl2 = false;
                                String string10 = string5;
                                if (string10 == null) {
                                    throw new NullPointerException("null cannot be cast to non-null type kotlin.CharSequence");
                                }
                                parameter = ((Object)StringsKt.trim((CharSequence)string10)).toString();
                            }
                        }
                        if (StringsKt.equals("no-cache", directive, true)) {
                            noCache = true;
                            continue;
                        }
                        if (StringsKt.equals("no-store", directive, true)) {
                            noStore = true;
                            continue;
                        }
                        if (StringsKt.equals("max-age", directive, true)) {
                            maxAgeSeconds = Util.toNonNegativeInt(parameter, -1);
                            continue;
                        }
                        if (StringsKt.equals("s-maxage", directive, true)) {
                            sMaxAgeSeconds = Util.toNonNegativeInt(parameter, -1);
                            continue;
                        }
                        if (StringsKt.equals("private", directive, true)) {
                            isPrivate = true;
                            continue;
                        }
                        if (StringsKt.equals("public", directive, true)) {
                            isPublic = true;
                            continue;
                        }
                        if (StringsKt.equals("must-revalidate", directive, true)) {
                            mustRevalidate = true;
                            continue;
                        }
                        if (StringsKt.equals("max-stale", directive, true)) {
                            maxStaleSeconds = Util.toNonNegativeInt(parameter, Integer.MAX_VALUE);
                            continue;
                        }
                        if (StringsKt.equals("min-fresh", directive, true)) {
                            minFreshSeconds = Util.toNonNegativeInt(parameter, -1);
                            continue;
                        }
                        if (StringsKt.equals("only-if-cached", directive, true)) {
                            onlyIfCached = true;
                            continue;
                        }
                        if (StringsKt.equals("no-transform", directive, true)) {
                            noTransform = true;
                            continue;
                        }
                        if (!StringsKt.equals("immutable", directive, true)) continue;
                        immutable = true;
                    }
                }
                ++i;
            }
            if (!canUseHeaderValue) {
                headerValue = null;
            }
            return new CacheControl(noCache, noStore, maxAgeSeconds, sMaxAgeSeconds, isPrivate, isPublic, mustRevalidate, maxStaleSeconds, minFreshSeconds, onlyIfCached, noTransform, immutable, headerValue, null);
        }

        /*
         * WARNING - void declaration
         */
        private final int indexOfElement(String $this$indexOfElement, String characters, int startIndex) {
            int n = startIndex;
            int n2 = $this$indexOfElement.length();
            while (n < n2) {
                void i;
                if (StringsKt.contains$default((CharSequence)characters, $this$indexOfElement.charAt((int)i), false, 2, null)) {
                    return (int)i;
                }
                ++i;
            }
            return $this$indexOfElement.length();
        }

        static /* synthetic */ int indexOfElement$default(Companion companion, String string, String string2, int n, int n2, Object object) {
            if ((n2 & 2) != 0) {
                n = 0;
            }
            return companion.indexOfElement(string, string2, n);
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

