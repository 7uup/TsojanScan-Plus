/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okio.internal;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=2, d1={"\u0000\u0016\n\u0000\n\u0002\u0010\u0012\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\u001e\u0010\u0003\u001a\u00020\u0002*\u00020\u00012\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u00a8\u0006\u0007"}, d2={"commonAsUtf8ToByteArray", "", "", "commonToUtf8String", "beginIndex", "", "endIndex", "okio"})
public final class _Utf8Kt {
    /*
     * Unable to fully structure code
     */
    @NotNull
    public static final String commonToUtf8String(@NotNull byte[] $this$commonToUtf8String, int beginIndex, int endIndex) {
        Intrinsics.checkNotNullParameter($this$commonToUtf8String, "$this$commonToUtf8String");
        if (beginIndex < 0 || endIndex > $this$commonToUtf8String.length || beginIndex > endIndex) {
            throw (Throwable)new ArrayIndexOutOfBoundsException("size=" + $this$commonToUtf8String.length + " beginIndex=" + beginIndex + " endIndex=" + endIndex);
        }
        chars = new char[endIndex - beginIndex];
        length = 0;
        $this$processUtf16Chars$iv = $this$commonToUtf8String;
        $i$f$processUtf16Chars = false;
        index$iv = beginIndex;
        while (index$iv < endIndex) {
            block54: {
                block58: {
                    block55: {
                        block57: {
                            block56: {
                                block51: {
                                    block53: {
                                        block52: {
                                            b0$iv = $this$processUtf16Chars$iv[index$iv];
                                            if (b0$iv >= 0) {
                                                c = b0$iv;
                                                $i$a$-processUtf16Chars-_Utf8Kt$commonToUtf8String$1 = false;
                                                var11_12 = length;
                                                length = var11_12 + 1;
                                                chars[var11_12] = c;
                                                ++index$iv;
                                                while (index$iv < endIndex && $this$processUtf16Chars$iv[index$iv] >= 0) {
                                                    c = (char)$this$processUtf16Chars$iv[index$iv++];
                                                    $i$a$-processUtf16Chars-_Utf8Kt$commonToUtf8String$1 = false;
                                                    var11_12 = length;
                                                    length = var11_12 + 1;
                                                    chars[var11_12] = c;
                                                }
                                                continue;
                                            }
                                            var12_13 = b0$iv;
                                            other$iv$iv = 5;
                                            $i$f$shr = 0;
                                            if ($this$shr$iv$iv >> other$iv$iv == -2) {
                                                $this$process2Utf8Bytes$iv$iv = $this$processUtf16Chars$iv;
                                                $i$f$process2Utf8Bytes = false;
                                                if (endIndex <= index$iv + 1) {
                                                    $i$f$shr = 65533;
                                                    var15_19 = index$iv;
                                                    $i$a$-process2Utf8Bytes-Utf8$processUtf16Chars$1$iv = false;
                                                    c = (char)it$iv;
                                                    $i$a$-processUtf16Chars-_Utf8Kt$commonToUtf8String$1 = false;
                                                    var11_12 = length;
                                                    length = var11_12 + 1;
                                                    chars[var11_12] = c;
                                                    var17_21 = Unit.INSTANCE;
                                                    v0 = var15_19;
                                                    v1 = 1;
                                                } else {
                                                    b0$iv$iv = $this$process2Utf8Bytes$iv$iv[index$iv];
                                                    b1$iv$iv = $this$process2Utf8Bytes$iv$iv[index$iv + 1];
                                                    $i$f$isUtf8Continuation = false;
                                                    var21_25 = b1$iv$iv;
                                                    other$iv$iv$iv$iv = 192;
                                                    $i$f$and = false;
                                                    if (!(($this$and$iv$iv$iv$iv & other$iv$iv$iv$iv) == 128)) {
                                                        it$iv = 65533;
                                                        $i$a$-process2Utf8Bytes-Utf8$processUtf16Chars$1$iv = false;
                                                        c = (char)it$iv;
                                                        $i$a$-processUtf16Chars-_Utf8Kt$commonToUtf8String$1 = false;
                                                        var11_12 = length;
                                                        length = var11_12 + 1;
                                                        chars[var11_12] = c;
                                                        var17_21 = Unit.INSTANCE;
                                                        v0 = var15_19;
                                                        v1 = 1;
                                                    } else {
                                                        codePoint$iv$iv = 3968 ^ b1$iv$iv ^ b0$iv$iv << 6;
                                                        if (codePoint$iv$iv < 128) {
                                                            it$iv = 65533;
                                                            $i$a$-process2Utf8Bytes-Utf8$processUtf16Chars$1$iv = false;
                                                            c = (char)it$iv;
                                                            $i$a$-processUtf16Chars-_Utf8Kt$commonToUtf8String$1 = false;
                                                            var11_12 = length;
                                                            length = var11_12 + 1;
                                                            chars[var11_12] = c;
                                                            var17_21 = Unit.INSTANCE;
                                                            v0 = var15_19;
                                                        } else {
                                                            it$iv = codePoint$iv$iv;
                                                            $i$a$-process2Utf8Bytes-Utf8$processUtf16Chars$1$iv = false;
                                                            c = (char)it$iv;
                                                            $i$a$-processUtf16Chars-_Utf8Kt$commonToUtf8String$1 = false;
                                                            var11_12 = length;
                                                            length = var11_12 + 1;
                                                            chars[var11_12] = c;
                                                            var17_21 = Unit.INSTANCE;
                                                            v0 = var15_19;
                                                        }
                                                        v1 = 2;
                                                    }
                                                }
                                                index$iv = v0 + v1;
                                                continue;
                                            }
                                            $this$process2Utf8Bytes$iv$iv = b0$iv;
                                            other$iv$iv = 4;
                                            $i$f$shr = 0;
                                            if ($this$shr$iv$iv >> other$iv$iv != -2) break block51;
                                            $this$process3Utf8Bytes$iv$iv = $this$processUtf16Chars$iv;
                                            $i$f$process3Utf8Bytes = false;
                                            if (endIndex > index$iv + 2) break block52;
                                            $i$f$shr = 65533;
                                            var15_19 = index$iv;
                                            $i$a$-process3Utf8Bytes-Utf8$processUtf16Chars$2$iv = false;
                                            c = (char)it$iv;
                                            $i$a$-processUtf16Chars-_Utf8Kt$commonToUtf8String$1 = false;
                                            var11_12 = length;
                                            length = var11_12 + 1;
                                            chars[var11_12] = c;
                                            var17_21 = Unit.INSTANCE;
                                            v2 = var15_19;
                                            if (endIndex <= index$iv + 1) ** GOTO lbl-1000
                                            byte$iv$iv$iv = $this$process3Utf8Bytes$iv$iv[index$iv + 1];
                                            $i$f$isUtf8Continuation = false;
                                            codePoint$iv$iv = byte$iv$iv$iv;
                                            other$iv$iv$iv$iv = 192;
                                            $i$f$and = false;
                                            if (!(($this$and$iv$iv$iv$iv & other$iv$iv$iv$iv) == 128)) lbl-1000:
                                            // 2 sources

                                            {
                                                v3 = 1;
                                            } else {
                                                v3 = 2;
                                            }
                                            break block53;
                                        }
                                        b0$iv$iv = $this$process3Utf8Bytes$iv$iv[index$iv];
                                        b1$iv$iv = $this$process3Utf8Bytes$iv$iv[index$iv + 1];
                                        $i$f$isUtf8Continuation = false;
                                        other$iv$iv$iv$iv = b1$iv$iv;
                                        other$iv$iv$iv$iv = 192;
                                        $i$f$and = false;
                                        if (!(($this$and$iv$iv$iv$iv & other$iv$iv$iv$iv) == 128)) {
                                            it$iv = 65533;
                                            $i$a$-process3Utf8Bytes-Utf8$processUtf16Chars$2$iv = false;
                                            c = (char)it$iv;
                                            $i$a$-processUtf16Chars-_Utf8Kt$commonToUtf8String$1 = false;
                                            var11_12 = length;
                                            length = var11_12 + 1;
                                            chars[var11_12] = c;
                                            var17_21 = Unit.INSTANCE;
                                            v2 = var15_19;
                                            v3 = 1;
                                        } else {
                                            b2$iv$iv = $this$process3Utf8Bytes$iv$iv[index$iv + 2];
                                            $i$f$isUtf8Continuation = false;
                                            other$iv$iv$iv$iv = b2$iv$iv;
                                            other$iv$iv$iv$iv = 192;
                                            $i$f$and = false;
                                            if (!(($this$and$iv$iv$iv$iv & other$iv$iv$iv$iv) == 128)) {
                                                it$iv = 65533;
                                                $i$a$-process3Utf8Bytes-Utf8$processUtf16Chars$2$iv = false;
                                                c = (char)it$iv;
                                                $i$a$-processUtf16Chars-_Utf8Kt$commonToUtf8String$1 = false;
                                                var11_12 = length;
                                                length = var11_12 + 1;
                                                chars[var11_12] = c;
                                                var17_21 = Unit.INSTANCE;
                                                v2 = var15_19;
                                                v3 = 2;
                                            } else {
                                                codePoint$iv$iv = -123008 ^ b2$iv$iv ^ b1$iv$iv << 6 ^ b0$iv$iv << 12;
                                                if (codePoint$iv$iv < 2048) {
                                                    it$iv = 65533;
                                                    $i$a$-process3Utf8Bytes-Utf8$processUtf16Chars$2$iv = false;
                                                    c = (char)it$iv;
                                                    $i$a$-processUtf16Chars-_Utf8Kt$commonToUtf8String$1 = false;
                                                    var11_12 = length;
                                                    length = var11_12 + 1;
                                                    chars[var11_12] = c;
                                                    var17_21 = Unit.INSTANCE;
                                                    v2 = var15_19;
                                                } else {
                                                    $this$and$iv$iv$iv$iv = codePoint$iv$iv;
                                                    if (55296 <= $this$and$iv$iv$iv$iv && 57343 >= $this$and$iv$iv$iv$iv) {
                                                        it$iv = 65533;
                                                        $i$a$-process3Utf8Bytes-Utf8$processUtf16Chars$2$iv = false;
                                                        c = (char)it$iv;
                                                        $i$a$-processUtf16Chars-_Utf8Kt$commonToUtf8String$1 = false;
                                                        var11_12 = length;
                                                        length = var11_12 + 1;
                                                        chars[var11_12] = c;
                                                        var17_21 = Unit.INSTANCE;
                                                        v2 = var15_19;
                                                    } else {
                                                        it$iv = codePoint$iv$iv;
                                                        $i$a$-process3Utf8Bytes-Utf8$processUtf16Chars$2$iv = false;
                                                        c = (char)it$iv;
                                                        $i$a$-processUtf16Chars-_Utf8Kt$commonToUtf8String$1 = false;
                                                        var11_12 = length;
                                                        length = var11_12 + 1;
                                                        chars[var11_12] = c;
                                                        var17_21 = Unit.INSTANCE;
                                                        v2 = var15_19;
                                                    }
                                                }
                                                v3 = 3;
                                            }
                                        }
                                    }
                                    index$iv = v2 + v3;
                                    continue;
                                }
                                $this$process3Utf8Bytes$iv$iv = b0$iv;
                                other$iv$iv = 3;
                                $i$f$shr = 0;
                                if ($this$shr$iv$iv >> other$iv$iv != -2) break block54;
                                $this$process4Utf8Bytes$iv$iv = $this$processUtf16Chars$iv;
                                $i$f$process4Utf8Bytes = false;
                                if (endIndex > index$iv + 3) break block55;
                                $i$f$shr = 65533;
                                var15_19 = index$iv;
                                $i$a$-process4Utf8Bytes-Utf8$processUtf16Chars$3$iv = false;
                                if (codePoint$iv != 65533) {
                                    c = (char)((codePoint$iv >>> 10) + 55232);
                                    $i$a$-processUtf16Chars-_Utf8Kt$commonToUtf8String$1 = false;
                                    var11_12 = length;
                                    length = var11_12 + 1;
                                    chars[var11_12] = c;
                                    c = (char)((codePoint$iv & 1023) + 56320);
                                    $i$a$-processUtf16Chars-_Utf8Kt$commonToUtf8String$1 = false;
                                    var11_12 = length;
                                    length = var11_12 + 1;
                                    chars[var11_12] = c;
                                } else {
                                    c = 65533;
                                    $i$a$-processUtf16Chars-_Utf8Kt$commonToUtf8String$1 = false;
                                    var11_12 = length;
                                    length = var11_12 + 1;
                                    chars[var11_12] = c;
                                }
                                var17_21 = Unit.INSTANCE;
                                v4 = var15_19;
                                if (endIndex <= index$iv + 1) break block56;
                                byte$iv$iv$iv = $this$process4Utf8Bytes$iv$iv[index$iv + 1];
                                $i$f$isUtf8Continuation = false;
                                b2$iv$iv = byte$iv$iv$iv;
                                other$iv$iv$iv$iv = 192;
                                $i$f$and = false;
                                if (($this$and$iv$iv$iv$iv & other$iv$iv$iv$iv) == 128) break block57;
                            }
                            v5 = 1;
                            break block58;
                        }
                        if (endIndex <= index$iv + 2) ** GOTO lbl-1000
                        byte$iv$iv$iv = $this$process4Utf8Bytes$iv$iv[index$iv + 2];
                        $i$f$isUtf8Continuation = false;
                        $this$and$iv$iv$iv$iv = byte$iv$iv$iv;
                        other$iv$iv$iv$iv = 192;
                        $i$f$and = false;
                        if (!(($this$and$iv$iv$iv$iv & other$iv$iv$iv$iv) == 128)) lbl-1000:
                        // 2 sources

                        {
                            v5 = 2;
                        } else {
                            v5 = 3;
                        }
                        break block58;
                    }
                    b0$iv$iv = $this$process4Utf8Bytes$iv$iv[index$iv];
                    b1$iv$iv = $this$process4Utf8Bytes$iv$iv[index$iv + 1];
                    $i$f$isUtf8Continuation = false;
                    other$iv$iv$iv$iv = b1$iv$iv;
                    other$iv$iv$iv$iv = 192;
                    $i$f$and = false;
                    if (!(($this$and$iv$iv$iv$iv & other$iv$iv$iv$iv) == 128)) {
                        codePoint$iv = 65533;
                        $i$a$-process4Utf8Bytes-Utf8$processUtf16Chars$3$iv = false;
                        if (codePoint$iv != 65533) {
                            c = (char)((codePoint$iv >>> 10) + 55232);
                            $i$a$-processUtf16Chars-_Utf8Kt$commonToUtf8String$1 = false;
                            var11_12 = length;
                            length = var11_12 + 1;
                            chars[var11_12] = c;
                            c = (char)((codePoint$iv & 1023) + 56320);
                            $i$a$-processUtf16Chars-_Utf8Kt$commonToUtf8String$1 = false;
                            var11_12 = length;
                            length = var11_12 + 1;
                            chars[var11_12] = c;
                        } else {
                            c = 65533;
                            $i$a$-processUtf16Chars-_Utf8Kt$commonToUtf8String$1 = false;
                            var11_12 = length;
                            length = var11_12 + 1;
                            chars[var11_12] = c;
                        }
                        var17_21 = Unit.INSTANCE;
                        v4 = var15_19;
                        v5 = 1;
                    } else {
                        b2$iv$iv = $this$process4Utf8Bytes$iv$iv[index$iv + 2];
                        $i$f$isUtf8Continuation = false;
                        other$iv$iv$iv$iv = b2$iv$iv;
                        other$iv$iv$iv$iv = 192;
                        $i$f$and = false;
                        if (!(($this$and$iv$iv$iv$iv & other$iv$iv$iv$iv) == 128)) {
                            codePoint$iv = 65533;
                            $i$a$-process4Utf8Bytes-Utf8$processUtf16Chars$3$iv = false;
                            if (codePoint$iv != 65533) {
                                c = (char)((codePoint$iv >>> 10) + 55232);
                                $i$a$-processUtf16Chars-_Utf8Kt$commonToUtf8String$1 = false;
                                var11_12 = length;
                                length = var11_12 + 1;
                                chars[var11_12] = c;
                                c = (char)((codePoint$iv & 1023) + 56320);
                                $i$a$-processUtf16Chars-_Utf8Kt$commonToUtf8String$1 = false;
                                var11_12 = length;
                                length = var11_12 + 1;
                                chars[var11_12] = c;
                            } else {
                                c = 65533;
                                $i$a$-processUtf16Chars-_Utf8Kt$commonToUtf8String$1 = false;
                                var11_12 = length;
                                length = var11_12 + 1;
                                chars[var11_12] = c;
                            }
                            var17_21 = Unit.INSTANCE;
                            v4 = var15_19;
                            v5 = 2;
                        } else {
                            b3$iv$iv = $this$process4Utf8Bytes$iv$iv[index$iv + 3];
                            $i$f$isUtf8Continuation = false;
                            other$iv$iv$iv$iv = b3$iv$iv;
                            other$iv$iv$iv$iv = 192;
                            $i$f$and = false;
                            if (!(($this$and$iv$iv$iv$iv & other$iv$iv$iv$iv) == 128)) {
                                codePoint$iv = 65533;
                                $i$a$-process4Utf8Bytes-Utf8$processUtf16Chars$3$iv = false;
                                if (codePoint$iv != 65533) {
                                    c = (char)((codePoint$iv >>> 10) + 55232);
                                    $i$a$-processUtf16Chars-_Utf8Kt$commonToUtf8String$1 = false;
                                    var11_12 = length;
                                    length = var11_12 + 1;
                                    chars[var11_12] = c;
                                    c = (char)((codePoint$iv & 1023) + 56320);
                                    $i$a$-processUtf16Chars-_Utf8Kt$commonToUtf8String$1 = false;
                                    var11_12 = length;
                                    length = var11_12 + 1;
                                    chars[var11_12] = c;
                                } else {
                                    c = 65533;
                                    $i$a$-processUtf16Chars-_Utf8Kt$commonToUtf8String$1 = false;
                                    var11_12 = length;
                                    length = var11_12 + 1;
                                    chars[var11_12] = c;
                                }
                                var17_21 = Unit.INSTANCE;
                                v4 = var15_19;
                                v5 = 3;
                            } else {
                                codePoint$iv$iv = 3678080 ^ b3$iv$iv ^ b2$iv$iv << 6 ^ b1$iv$iv << 12 ^ b0$iv$iv << 18;
                                if (codePoint$iv$iv > 0x10FFFF) {
                                    codePoint$iv = 65533;
                                    $i$a$-process4Utf8Bytes-Utf8$processUtf16Chars$3$iv = false;
                                    if (codePoint$iv != 65533) {
                                        c = (char)((codePoint$iv >>> 10) + 55232);
                                        $i$a$-processUtf16Chars-_Utf8Kt$commonToUtf8String$1 = false;
                                        var11_12 = length;
                                        length = var11_12 + 1;
                                        chars[var11_12] = c;
                                        c = (char)((codePoint$iv & 1023) + 56320);
                                        $i$a$-processUtf16Chars-_Utf8Kt$commonToUtf8String$1 = false;
                                        var11_12 = length;
                                        length = var11_12 + 1;
                                        chars[var11_12] = c;
                                    } else {
                                        c = 65533;
                                        $i$a$-processUtf16Chars-_Utf8Kt$commonToUtf8String$1 = false;
                                        var11_12 = length;
                                        length = var11_12 + 1;
                                        chars[var11_12] = c;
                                    }
                                    var17_21 = Unit.INSTANCE;
                                    v4 = var15_19;
                                } else {
                                    var23_27 = codePoint$iv$iv;
                                    if (55296 <= var23_27 && 57343 >= var23_27) {
                                        codePoint$iv = 65533;
                                        $i$a$-process4Utf8Bytes-Utf8$processUtf16Chars$3$iv = false;
                                        if (codePoint$iv != 65533) {
                                            c = (char)((codePoint$iv >>> 10) + 55232);
                                            $i$a$-processUtf16Chars-_Utf8Kt$commonToUtf8String$1 = false;
                                            var11_12 = length;
                                            length = var11_12 + 1;
                                            chars[var11_12] = c;
                                            c = (char)((codePoint$iv & 1023) + 56320);
                                            $i$a$-processUtf16Chars-_Utf8Kt$commonToUtf8String$1 = false;
                                            var11_12 = length;
                                            length = var11_12 + 1;
                                            chars[var11_12] = c;
                                        } else {
                                            c = 65533;
                                            $i$a$-processUtf16Chars-_Utf8Kt$commonToUtf8String$1 = false;
                                            var11_12 = length;
                                            length = var11_12 + 1;
                                            chars[var11_12] = c;
                                        }
                                        var17_21 = Unit.INSTANCE;
                                        v4 = var15_19;
                                    } else if (codePoint$iv$iv < 65536) {
                                        codePoint$iv = 65533;
                                        $i$a$-process4Utf8Bytes-Utf8$processUtf16Chars$3$iv = false;
                                        if (codePoint$iv != 65533) {
                                            c = (char)((codePoint$iv >>> 10) + 55232);
                                            $i$a$-processUtf16Chars-_Utf8Kt$commonToUtf8String$1 = false;
                                            var11_12 = length;
                                            length = var11_12 + 1;
                                            chars[var11_12] = c;
                                            c = (char)((codePoint$iv & 1023) + 56320);
                                            $i$a$-processUtf16Chars-_Utf8Kt$commonToUtf8String$1 = false;
                                            var11_12 = length;
                                            length = var11_12 + 1;
                                            chars[var11_12] = c;
                                        } else {
                                            c = 65533;
                                            $i$a$-processUtf16Chars-_Utf8Kt$commonToUtf8String$1 = false;
                                            var11_12 = length;
                                            length = var11_12 + 1;
                                            chars[var11_12] = c;
                                        }
                                        var17_21 = Unit.INSTANCE;
                                        v4 = var15_19;
                                    } else {
                                        codePoint$iv = codePoint$iv$iv;
                                        $i$a$-process4Utf8Bytes-Utf8$processUtf16Chars$3$iv = false;
                                        if (codePoint$iv != 65533) {
                                            c = (char)((codePoint$iv >>> 10) + 55232);
                                            $i$a$-processUtf16Chars-_Utf8Kt$commonToUtf8String$1 = false;
                                            var11_12 = length;
                                            length = var11_12 + 1;
                                            chars[var11_12] = c;
                                            c = (char)((codePoint$iv & 1023) + 56320);
                                            $i$a$-processUtf16Chars-_Utf8Kt$commonToUtf8String$1 = false;
                                            var11_12 = length;
                                            length = var11_12 + 1;
                                            chars[var11_12] = c;
                                        } else {
                                            c = 65533;
                                            $i$a$-processUtf16Chars-_Utf8Kt$commonToUtf8String$1 = false;
                                            var11_12 = length;
                                            length = var11_12 + 1;
                                            chars[var11_12] = c;
                                        }
                                        var17_21 = Unit.INSTANCE;
                                        v4 = var15_19;
                                    }
                                }
                                v5 = 4;
                            }
                        }
                    }
                }
                index$iv = v4 + v5;
                continue;
            }
            c = 65533;
            $i$a$-processUtf16Chars-_Utf8Kt$commonToUtf8String$1 = false;
            var11_12 = length;
            length = var11_12 + 1;
            chars[var11_12] = c;
            ++index$iv;
        }
        var5_6 = 0;
        var6_7 = length;
        var7_8 = false;
        return new String(chars, var5_6, var6_7);
    }

    public static /* synthetic */ String commonToUtf8String$default(byte[] byArray, int n, int n2, int n3, Object object) {
        if ((n3 & 1) != 0) {
            n = 0;
        }
        if ((n3 & 2) != 0) {
            n2 = byArray.length;
        }
        return _Utf8Kt.commonToUtf8String(byArray, n, n2);
    }

    /*
     * WARNING - void declaration
     */
    @NotNull
    public static final byte[] commonAsUtf8ToByteArray(@NotNull String $this$commonAsUtf8ToByteArray) {
        Intrinsics.checkNotNullParameter($this$commonAsUtf8ToByteArray, "$this$commonAsUtf8ToByteArray");
        byte[] bytes = new byte[4 * $this$commonAsUtf8ToByteArray.length()];
        int n = 0;
        int n2 = $this$commonAsUtf8ToByteArray.length();
        while (n < n2) {
            void index;
            char b0 = $this$commonAsUtf8ToByteArray.charAt((int)index);
            if (Intrinsics.compare(b0, 128) >= 0) {
                void size = index;
                Object object = $this$commonAsUtf8ToByteArray;
                int endIndex$iv = $this$commonAsUtf8ToByteArray.length();
                boolean $i$f$processUtf8Bytes = false;
                void index$iv = index;
                while (index$iv < endIndex$iv) {
                    void var14_15;
                    boolean bl;
                    int c;
                    void var13_14;
                    boolean bl2;
                    char c2;
                    void $this$processUtf8Bytes$iv;
                    char c$iv = $this$processUtf8Bytes$iv.charAt((int)index$iv);
                    if (Intrinsics.compare(c$iv, 128) < 0) {
                        c2 = c$iv;
                        bl2 = false;
                        var13_14 = size;
                        size = var13_14 + true;
                        bytes[var13_14] = c2;
                        ++index$iv;
                        while (index$iv < endIndex$iv && Intrinsics.compare($this$processUtf8Bytes$iv.charAt((int)index$iv), 128) < 0) {
                            c2 = $this$processUtf8Bytes$iv.charAt((int)index$iv++);
                            bl2 = false;
                            var13_14 = size;
                            size = var13_14 + true;
                            bytes[var13_14] = c2;
                        }
                        continue;
                    }
                    if (Intrinsics.compare(c$iv, 2048) < 0) {
                        c2 = (char)(c$iv >> 6 | 0xC0);
                        bl2 = false;
                        var13_14 = size;
                        size = var13_14 + true;
                        bytes[var13_14] = c2;
                        c2 = (char)(c$iv & 0x3F | 0x80);
                        bl2 = false;
                        var13_14 = size;
                        size = var13_14 + true;
                        bytes[var13_14] = c2;
                        ++index$iv;
                        continue;
                    }
                    c2 = c$iv;
                    if ('\ud800' > c2 || '\udfff' < c2) {
                        c = (byte)(c$iv >> 12 | 0xE0);
                        bl = false;
                        var14_15 = size;
                        size = var14_15 + true;
                        bytes[var14_15] = c;
                        c = (byte)(c$iv >> 6 & 0x3F | 0x80);
                        bl = false;
                        var14_15 = size;
                        size = var14_15 + true;
                        bytes[var14_15] = c;
                        c = (byte)(c$iv & 0x3F | 0x80);
                        bl = false;
                        var14_15 = size;
                        size = var14_15 + true;
                        bytes[var14_15] = c;
                        ++index$iv;
                        continue;
                    }
                    if (Intrinsics.compare(c$iv, 56319) > 0 || endIndex$iv <= index$iv + true || '\udc00' > (c2 = $this$processUtf8Bytes$iv.charAt((int)(index$iv + true))) || '\udfff' < c2) {
                        c = 63;
                        bl = false;
                        var14_15 = size;
                        size = var14_15 + true;
                        bytes[var14_15] = c;
                        ++index$iv;
                        continue;
                    }
                    int codePoint$iv = (c$iv << 10) + $this$processUtf8Bytes$iv.charAt((int)(index$iv + true)) + -56613888;
                    c = (byte)(codePoint$iv >> 18 | 0xF0);
                    bl = false;
                    var14_15 = size;
                    size = var14_15 + true;
                    bytes[var14_15] = c;
                    c = (byte)(codePoint$iv >> 12 & 0x3F | 0x80);
                    bl = false;
                    var14_15 = size;
                    size = var14_15 + true;
                    bytes[var14_15] = c;
                    c = (byte)(codePoint$iv >> 6 & 0x3F | 0x80);
                    bl = false;
                    var14_15 = size;
                    size = var14_15 + true;
                    bytes[var14_15] = c;
                    c = (byte)(codePoint$iv & 0x3F | 0x80);
                    bl = false;
                    var14_15 = size;
                    size = var14_15 + true;
                    bytes[var14_15] = c;
                    index$iv += 2;
                }
                object = bytes;
                void var7_8 = size;
                boolean bl = false;
                byte[] byArray = Arrays.copyOf((byte[])object, (int)var7_8);
                Intrinsics.checkNotNullExpressionValue(byArray, "java.util.Arrays.copyOf(this, newSize)");
                return byArray;
            }
            bytes[index] = (byte)b0;
            ++index;
        }
        byte[] byArray = bytes;
        n2 = $this$commonAsUtf8ToByteArray.length();
        boolean bl = false;
        byte[] byArray2 = Arrays.copyOf(byArray, n2);
        Intrinsics.checkNotNullExpressionValue(byArray2, "java.util.Arrays.copyOf(this, newSize)");
        return byArray2;
    }
}

