/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okio;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import okio.-Platform;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=2, d1={"\u0000\u0012\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\u001a\u000e\u0010\u0006\u001a\u0004\u0018\u00010\u0001*\u00020\u0007H\u0000\u001a\u0016\u0010\b\u001a\u00020\u0007*\u00020\u00012\b\b\u0002\u0010\t\u001a\u00020\u0001H\u0000\"\u0014\u0010\u0000\u001a\u00020\u0001X\u0080\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u0014\u0010\u0004\u001a\u00020\u0001X\u0080\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0003\u00a8\u0006\n"}, d2={"BASE64", "", "getBASE64", "()[B", "BASE64_URL_SAFE", "getBASE64_URL_SAFE", "decodeBase64ToArray", "", "encodeBase64", "map", "okio"})
@JvmName(name="-Base64")
public final class -Base64 {
    @NotNull
    private static final byte[] BASE64 = ByteString.Companion.encodeUtf8("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/").getData$okio();
    @NotNull
    private static final byte[] BASE64_URL_SAFE = ByteString.Companion.encodeUtf8("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_").getData$okio();

    @NotNull
    public static final byte[] getBASE64() {
        return BASE64;
    }

    @NotNull
    public static final byte[] getBASE64_URL_SAFE() {
        return BASE64_URL_SAFE;
    }

    /*
     * Unable to fully structure code
     */
    @Nullable
    public static final byte[] decodeBase64ToArray(@NotNull String $this$decodeBase64ToArray) {
        Intrinsics.checkNotNullParameter($this$decodeBase64ToArray, "$this$decodeBase64ToArray");
        for (limit = $this$decodeBase64ToArray.length(); limit > 0 && ((c = $this$decodeBase64ToArray.charAt(limit - 1)) == '=' || c == '\n' || c == '\r' || c == ' ' || c == '\t'); --limit) {
        }
        out = new byte[(int)((long)limit * 6L / 8L)];
        outCount = 0;
        inCount = 0;
        word = 0;
        var6_7 = 0;
        var7_8 = limit;
        while (var6_7 < var7_8) {
            block15: {
                block14: {
                    block13: {
                        block12: {
                            c = $this$decodeBase64ToArray.charAt((int)pos);
                            bits = 0;
                            var10_12 = c;
                            if ('A' > var10_12 || 'Z' < var10_12) break block12;
                            bits = c - 65;
                            ** GOTO lbl37
                        }
                        var10_12 = c;
                        if ('a' > var10_12 || 'z' < var10_12) break block13;
                        bits = c - 71;
                        ** GOTO lbl37
                    }
                    var10_12 = c;
                    if ('0' > var10_12 || '9' < var10_12) break block14;
                    bits = c + 4;
                    ** GOTO lbl37
                }
                if (c != '+' && c != '-') break block15;
                bits = 62;
                ** GOTO lbl37
            }
            if (c != '/' && c != '_') {
                if (c != '\n' && c != '\r' && c != ' ' && c != '\t') {
                    return null;
                }
            } else {
                bits = 63;
lbl37:
                // 5 sources

                word = word << 6 | bits;
                if (++inCount % 4 == 0) {
                    out[outCount++] = (byte)(word >> 16);
                    out[outCount++] = (byte)(word >> 8);
                    out[outCount++] = (byte)word;
                }
            }
            ++pos;
        }
        lastWordChars = inCount % 4;
        switch (lastWordChars) {
            case 1: {
                return null;
            }
            case 2: {
                out[outCount++] = (byte)((word <<= 12) >> 16);
                break;
            }
            case 3: {
                out[outCount++] = (byte)((word <<= 6) >> 16);
                out[outCount++] = (byte)(word >> 8);
                break;
            }
        }
        if (outCount == out.length) {
            return out;
        }
        var7_9 = out;
        var8_10 = false;
        v0 = Arrays.copyOf(var7_9, outCount);
        Intrinsics.checkNotNullExpressionValue(v0, "java.util.Arrays.copyOf(this, newSize)");
        return v0;
    }

    @NotNull
    public static final String encodeBase64(@NotNull byte[] $this$encodeBase64, @NotNull byte[] map) {
        byte b1;
        byte b0;
        Intrinsics.checkNotNullParameter($this$encodeBase64, "$this$encodeBase64");
        Intrinsics.checkNotNullParameter(map, "map");
        int length = ($this$encodeBase64.length + 2) / 3 * 4;
        byte[] out = new byte[length];
        int index = 0;
        int end = $this$encodeBase64.length - $this$encodeBase64.length % 3;
        int i = 0;
        while (i < end) {
            b0 = $this$encodeBase64[i++];
            b1 = $this$encodeBase64[i++];
            byte b2 = $this$encodeBase64[i++];
            out[index++] = map[(b0 & 0xFF) >> 2];
            out[index++] = map[(b0 & 3) << 4 | (b1 & 0xFF) >> 4];
            out[index++] = map[(b1 & 0xF) << 2 | (b2 & 0xFF) >> 6];
            out[index++] = map[b2 & 0x3F];
        }
        switch ($this$encodeBase64.length - end) {
            case 1: {
                b0 = $this$encodeBase64[i];
                out[index++] = map[(b0 & 0xFF) >> 2];
                out[index++] = map[(b0 & 3) << 4];
                out[index++] = (byte)61;
                out[index] = (byte)61;
                break;
            }
            case 2: {
                b0 = $this$encodeBase64[i++];
                b1 = $this$encodeBase64[i];
                out[index++] = map[(b0 & 0xFF) >> 2];
                out[index++] = map[(b0 & 3) << 4 | (b1 & 0xFF) >> 4];
                out[index++] = map[(b1 & 0xF) << 2];
                out[index] = (byte)61;
                break;
            }
        }
        return -Platform.toUtf8String(out);
    }

    public static /* synthetic */ String encodeBase64$default(byte[] byArray, byte[] byArray2, int n, Object object) {
        if ((n & 1) != 0) {
            byArray2 = BASE64;
        }
        return -Base64.encodeBase64(byArray, byArray2);
    }
}

