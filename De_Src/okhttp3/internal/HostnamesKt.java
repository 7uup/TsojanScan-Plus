/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package okhttp3.internal;

import java.net.IDN;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.internal.Util;
import okio.Buffer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 4, 0}, bv={1, 0, 3}, k=2, d1={"\u0000&\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a0\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0005H\u0002\u001a\"\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0002\u001a\u0010\u0010\f\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH\u0002\u001a\f\u0010\r\u001a\u00020\u0001*\u00020\u0003H\u0002\u001a\f\u0010\u000e\u001a\u0004\u0018\u00010\u0003*\u00020\u0003\u00a8\u0006\u000f"}, d2={"decodeIpv4Suffix", "", "input", "", "pos", "", "limit", "address", "", "addressOffset", "decodeIpv6", "Ljava/net/InetAddress;", "inet6AddressToAscii", "containsInvalidHostnameAsciiCodes", "toCanonicalHost", "okhttp"})
public final class HostnamesKt {
    @Nullable
    public static final String toCanonicalHost(@NotNull String $this$toCanonicalHost) {
        Intrinsics.checkNotNullParameter($this$toCanonicalHost, "$this$toCanonicalHost");
        String host = $this$toCanonicalHost;
        if (StringsKt.contains$default((CharSequence)host, ":", false, 2, null)) {
            InetAddress inetAddress = StringsKt.startsWith$default(host, "[", false, 2, null) && StringsKt.endsWith$default(host, "]", false, 2, null) ? HostnamesKt.decodeIpv6(host, 1, host.length() - 1) : HostnamesKt.decodeIpv6(host, 0, host.length());
            if (inetAddress == null) {
                return null;
            }
            InetAddress inetAddress2 = inetAddress;
            byte[] address = inetAddress2.getAddress();
            if (address.length == 16) {
                Intrinsics.checkNotNullExpressionValue(address, "address");
                return HostnamesKt.inet6AddressToAscii(address);
            }
            if (address.length == 4) {
                return inetAddress2.getHostAddress();
            }
            throw (Throwable)((Object)new AssertionError((Object)("Invalid IPv6 address: '" + host + '\'')));
        }
        try {
            String string = IDN.toASCII(host);
            Intrinsics.checkNotNullExpressionValue(string, "IDN.toASCII(host)");
            CharSequence charSequence = string;
            Locale locale = Locale.US;
            Intrinsics.checkNotNullExpressionValue(locale, "Locale.US");
            Locale locale2 = locale;
            boolean bl = false;
            String string2 = charSequence;
            if (string2 == null) {
                throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
            }
            String string3 = string2.toLowerCase(locale2);
            Intrinsics.checkNotNullExpressionValue(string3, "(this as java.lang.String).toLowerCase(locale)");
            String result = string3;
            charSequence = result;
            boolean bl2 = false;
            if (charSequence.length() == 0) {
                return null;
            }
            return HostnamesKt.containsInvalidHostnameAsciiCodes(result) ? null : result;
        } catch (IllegalArgumentException _) {
            return null;
        }
    }

    /*
     * WARNING - void declaration
     */
    private static final boolean containsInvalidHostnameAsciiCodes(String $this$containsInvalidHostnameAsciiCodes) {
        int n = 0;
        int n2 = $this$containsInvalidHostnameAsciiCodes.length();
        while (n < n2) {
            void i;
            char c = $this$containsInvalidHostnameAsciiCodes.charAt((int)i);
            if (Intrinsics.compare(c, 31) <= 0 || Intrinsics.compare(c, 127) >= 0) {
                return true;
            }
            if (StringsKt.indexOf$default((CharSequence)" #%/:?@[\\]", c, 0, false, 6, null) != -1) {
                return true;
            }
            ++i;
        }
        return false;
    }

    private static final InetAddress decodeIpv6(String input, int pos, int limit) {
        byte[] address = new byte[16];
        int b = 0;
        int compress = -1;
        int groupOffset = -1;
        int i = pos;
        while (i < limit) {
            int hexDigit;
            if (b == address.length) {
                return null;
            }
            if (i + 2 <= limit && StringsKt.startsWith$default(input, "::", i, false, 4, null)) {
                if (compress != -1) {
                    return null;
                }
                compress = b += 2;
                if ((i += 2) == limit) {
                    break;
                }
            } else if (b != 0 && !StringsKt.startsWith$default(input, ":", i, false, 4, null)) {
                if (StringsKt.startsWith$default(input, ".", i, false, 4, null)) {
                    if (!HostnamesKt.decodeIpv4Suffix(input, groupOffset, limit, address, b - 2)) {
                        return null;
                    }
                    b += 2;
                    break;
                }
                return null;
            }
            int value = 0;
            groupOffset = ++i;
            while (i < limit && (hexDigit = Util.parseHexDigit(input.charAt(i))) != -1) {
                value = (value << 4) + hexDigit;
                ++i;
            }
            int groupLength = i - groupOffset;
            if (groupLength == 0 || groupLength > 4) {
                return null;
            }
            address[b++] = (byte)(value >>> 8 & 0xFF);
            address[b++] = (byte)(value & 0xFF);
        }
        if (b != address.length) {
            if (compress == -1) {
                return null;
            }
            System.arraycopy(address, compress, address, address.length - (b - compress), b - compress);
            Arrays.fill(address, compress, compress + (address.length - b), (byte)0);
        }
        return InetAddress.getByAddress(address);
    }

    private static final boolean decodeIpv4Suffix(String input, int pos, int limit, byte[] address, int addressOffset) {
        int b = addressOffset;
        int i = pos;
        while (i < limit) {
            char c;
            if (b == address.length) {
                return false;
            }
            if (b != addressOffset && input.charAt(i) != '.') {
                return false;
            }
            int value = 0;
            int groupOffset = ++i;
            while (i < limit && Intrinsics.compare(c = input.charAt(i), 48) >= 0 && Intrinsics.compare(c, 57) <= 0) {
                if (value == 0 && groupOffset != i) {
                    return false;
                }
                if ((value = value * 10 + c - 48) > 255) {
                    return false;
                }
                ++i;
            }
            int groupLength = i - groupOffset;
            if (groupLength == 0) {
                return false;
            }
            address[b++] = (byte)value;
        }
        return b == addressOffset + 4;
    }

    private static final String inet6AddressToAscii(byte[] address) {
        int longestRunOffset = -1;
        int longestRunLength = 0;
        boolean bl = false;
        boolean bl2 = false;
        boolean bl3 = false;
        for (int i = 0; i < address.length; i += 2) {
            int currentRunOffset = i;
            while (i < 16 && address[i] == 0 && address[i + 1] == 0) {
                i += 2;
            }
            int currentRunLength = i - currentRunOffset;
            if (currentRunLength <= longestRunLength || currentRunLength < 4) continue;
            longestRunOffset = currentRunOffset;
            longestRunLength = currentRunLength;
        }
        Buffer result = new Buffer();
        int i = 0;
        while (i < address.length) {
            if (i == longestRunOffset) {
                result.writeByte(58);
                if ((i += longestRunLength) != 16) continue;
                result.writeByte(58);
                continue;
            }
            if (i > 0) {
                result.writeByte(58);
            }
            int group = Util.and(address[i], 255) << 8 | Util.and(address[i + 1], 255);
            result.writeHexadecimalUnsignedLong(group);
            i += 2;
        }
        return result.readUtf8();
    }
}

