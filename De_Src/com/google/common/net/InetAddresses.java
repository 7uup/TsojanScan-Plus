/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.net;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.google.common.hash.Hashing;
import com.google.common.io.ByteStreams;
import com.google.common.primitives.Ints;
import java.math.BigInteger;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import org.checkerframework.checker.nullness.qual.Nullable;

@Beta
@GwtIncompatible
public final class InetAddresses {
    private static final int IPV4_PART_COUNT = 4;
    private static final int IPV6_PART_COUNT = 8;
    private static final Splitter IPV4_SPLITTER = Splitter.on('.').limit(4);
    private static final Splitter IPV6_SPLITTER = Splitter.on(':').limit(10);
    private static final Inet4Address LOOPBACK4 = (Inet4Address)InetAddresses.forString("127.0.0.1");
    private static final Inet4Address ANY4 = (Inet4Address)InetAddresses.forString("0.0.0.0");

    private InetAddresses() {
    }

    private static Inet4Address getInet4Address(byte[] bytes) {
        Preconditions.checkArgument(bytes.length == 4, "Byte array has invalid length for an IPv4 address: %s != 4.", bytes.length);
        return (Inet4Address)InetAddresses.bytesToInetAddress(bytes);
    }

    public static InetAddress forString(String ipString) {
        byte[] addr = InetAddresses.ipStringToBytes(ipString);
        if (addr == null) {
            throw InetAddresses.formatIllegalArgumentException("'%s' is not an IP string literal.", ipString);
        }
        return InetAddresses.bytesToInetAddress(addr);
    }

    public static boolean isInetAddress(String ipString) {
        return InetAddresses.ipStringToBytes(ipString) != null;
    }

    private static byte @Nullable [] ipStringToBytes(String ipString) {
        boolean hasColon = false;
        boolean hasDot = false;
        int percentIndex = -1;
        for (int i = 0; i < ipString.length(); ++i) {
            char c = ipString.charAt(i);
            if (c == '.') {
                hasDot = true;
                continue;
            }
            if (c == ':') {
                if (hasDot) {
                    return null;
                }
                hasColon = true;
                continue;
            }
            if (c == '%') {
                percentIndex = i;
                break;
            }
            if (Character.digit(c, 16) != -1) continue;
            return null;
        }
        if (hasColon) {
            if (hasDot && (ipString = InetAddresses.convertDottedQuadToHex(ipString)) == null) {
                return null;
            }
            if (percentIndex != -1) {
                ipString = ipString.substring(0, percentIndex);
            }
            return InetAddresses.textToNumericFormatV6(ipString);
        }
        if (hasDot) {
            return InetAddresses.textToNumericFormatV4(ipString);
        }
        return null;
    }

    private static byte @Nullable [] textToNumericFormatV4(String ipString) {
        byte[] bytes = new byte[4];
        int i = 0;
        try {
            for (String octet : IPV4_SPLITTER.split(ipString)) {
                bytes[i++] = InetAddresses.parseOctet(octet);
            }
        } catch (NumberFormatException ex) {
            return null;
        }
        return (byte[])(i == 4 ? bytes : null);
    }

    private static byte @Nullable [] textToNumericFormatV6(String ipString) {
        int partsLo;
        int partsHi;
        List<String> parts = IPV6_SPLITTER.splitToList(ipString);
        if (parts.size() < 3 || parts.size() > 9) {
            return null;
        }
        int skipIndex = -1;
        for (int i = 1; i < parts.size() - 1; ++i) {
            if (parts.get(i).length() != 0) continue;
            if (skipIndex >= 0) {
                return null;
            }
            skipIndex = i;
        }
        if (skipIndex >= 0) {
            partsHi = skipIndex;
            partsLo = parts.size() - skipIndex - 1;
            if (parts.get(0).length() == 0 && --partsHi != 0) {
                return null;
            }
            if (Iterables.getLast(parts).length() == 0 && --partsLo != 0) {
                return null;
            }
        } else {
            partsHi = parts.size();
            partsLo = 0;
        }
        int partsSkipped = 8 - (partsHi + partsLo);
        if (!(skipIndex < 0 ? partsSkipped == 0 : partsSkipped >= 1)) {
            return null;
        }
        ByteBuffer rawBytes = ByteBuffer.allocate(16);
        try {
            int i;
            for (i = 0; i < partsHi; ++i) {
                rawBytes.putShort(InetAddresses.parseHextet(parts.get(i)));
            }
            for (i = 0; i < partsSkipped; ++i) {
                rawBytes.putShort((short)0);
            }
            for (i = partsLo; i > 0; --i) {
                rawBytes.putShort(InetAddresses.parseHextet(parts.get(parts.size() - i)));
            }
        } catch (NumberFormatException ex) {
            return null;
        }
        return rawBytes.array();
    }

    private static @Nullable String convertDottedQuadToHex(String ipString) {
        int lastColon = ipString.lastIndexOf(58);
        String initialPart = ipString.substring(0, lastColon + 1);
        String dottedQuad = ipString.substring(lastColon + 1);
        byte[] quad = InetAddresses.textToNumericFormatV4(dottedQuad);
        if (quad == null) {
            return null;
        }
        String penultimate = Integer.toHexString((quad[0] & 0xFF) << 8 | quad[1] & 0xFF);
        String ultimate = Integer.toHexString((quad[2] & 0xFF) << 8 | quad[3] & 0xFF);
        return initialPart + penultimate + ":" + ultimate;
    }

    private static byte parseOctet(String ipPart) {
        int octet = Integer.parseInt(ipPart);
        if (octet > 255 || ipPart.startsWith("0") && ipPart.length() > 1) {
            throw new NumberFormatException();
        }
        return (byte)octet;
    }

    private static short parseHextet(String ipPart) {
        int hextet = Integer.parseInt(ipPart, 16);
        if (hextet > 65535) {
            throw new NumberFormatException();
        }
        return (short)hextet;
    }

    private static InetAddress bytesToInetAddress(byte[] addr) {
        try {
            return InetAddress.getByAddress(addr);
        } catch (UnknownHostException e) {
            throw new AssertionError((Object)e);
        }
    }

    public static String toAddrString(InetAddress ip) {
        Preconditions.checkNotNull(ip);
        if (ip instanceof Inet4Address) {
            return ip.getHostAddress();
        }
        Preconditions.checkArgument(ip instanceof Inet6Address);
        byte[] bytes = ip.getAddress();
        int[] hextets = new int[8];
        for (int i = 0; i < hextets.length; ++i) {
            hextets[i] = Ints.fromBytes((byte)0, (byte)0, bytes[2 * i], bytes[2 * i + 1]);
        }
        InetAddresses.compressLongestRunOfZeroes(hextets);
        return InetAddresses.hextetsToIPv6String(hextets);
    }

    private static void compressLongestRunOfZeroes(int[] hextets) {
        int bestRunStart = -1;
        int bestRunLength = -1;
        int runStart = -1;
        for (int i = 0; i < hextets.length + 1; ++i) {
            if (i < hextets.length && hextets[i] == 0) {
                if (runStart >= 0) continue;
                runStart = i;
                continue;
            }
            if (runStart < 0) continue;
            int runLength = i - runStart;
            if (runLength > bestRunLength) {
                bestRunStart = runStart;
                bestRunLength = runLength;
            }
            runStart = -1;
        }
        if (bestRunLength >= 2) {
            Arrays.fill(hextets, bestRunStart, bestRunStart + bestRunLength, -1);
        }
    }

    private static String hextetsToIPv6String(int[] hextets) {
        StringBuilder buf = new StringBuilder(39);
        boolean lastWasNumber = false;
        for (int i = 0; i < hextets.length; ++i) {
            boolean thisIsNumber;
            boolean bl = thisIsNumber = hextets[i] >= 0;
            if (thisIsNumber) {
                if (lastWasNumber) {
                    buf.append(':');
                }
                buf.append(Integer.toHexString(hextets[i]));
            } else if (i == 0 || lastWasNumber) {
                buf.append("::");
            }
            lastWasNumber = thisIsNumber;
        }
        return buf.toString();
    }

    public static String toUriString(InetAddress ip) {
        if (ip instanceof Inet6Address) {
            return "[" + InetAddresses.toAddrString(ip) + "]";
        }
        return InetAddresses.toAddrString(ip);
    }

    public static InetAddress forUriString(String hostAddr) {
        InetAddress addr = InetAddresses.forUriStringNoThrow(hostAddr);
        if (addr == null) {
            throw InetAddresses.formatIllegalArgumentException("Not a valid URI IP literal: '%s'", hostAddr);
        }
        return addr;
    }

    private static @Nullable InetAddress forUriStringNoThrow(String hostAddr) {
        int expectBytes;
        String ipString;
        Preconditions.checkNotNull(hostAddr);
        if (hostAddr.startsWith("[") && hostAddr.endsWith("]")) {
            ipString = hostAddr.substring(1, hostAddr.length() - 1);
            expectBytes = 16;
        } else {
            ipString = hostAddr;
            expectBytes = 4;
        }
        byte[] addr = InetAddresses.ipStringToBytes(ipString);
        if (addr == null || addr.length != expectBytes) {
            return null;
        }
        return InetAddresses.bytesToInetAddress(addr);
    }

    public static boolean isUriInetAddress(String ipString) {
        return InetAddresses.forUriStringNoThrow(ipString) != null;
    }

    public static boolean isCompatIPv4Address(Inet6Address ip) {
        if (!ip.isIPv4CompatibleAddress()) {
            return false;
        }
        byte[] bytes = ip.getAddress();
        return bytes[12] != 0 || bytes[13] != 0 || bytes[14] != 0 || bytes[15] != 0 && bytes[15] != 1;
    }

    public static Inet4Address getCompatIPv4Address(Inet6Address ip) {
        Preconditions.checkArgument(InetAddresses.isCompatIPv4Address(ip), "Address '%s' is not IPv4-compatible.", (Object)InetAddresses.toAddrString(ip));
        return InetAddresses.getInet4Address(Arrays.copyOfRange(ip.getAddress(), 12, 16));
    }

    public static boolean is6to4Address(Inet6Address ip) {
        byte[] bytes = ip.getAddress();
        return bytes[0] == 32 && bytes[1] == 2;
    }

    public static Inet4Address get6to4IPv4Address(Inet6Address ip) {
        Preconditions.checkArgument(InetAddresses.is6to4Address(ip), "Address '%s' is not a 6to4 address.", (Object)InetAddresses.toAddrString(ip));
        return InetAddresses.getInet4Address(Arrays.copyOfRange(ip.getAddress(), 2, 6));
    }

    public static boolean isTeredoAddress(Inet6Address ip) {
        byte[] bytes = ip.getAddress();
        return bytes[0] == 32 && bytes[1] == 1 && bytes[2] == 0 && bytes[3] == 0;
    }

    public static TeredoInfo getTeredoInfo(Inet6Address ip) {
        Preconditions.checkArgument(InetAddresses.isTeredoAddress(ip), "Address '%s' is not a Teredo address.", (Object)InetAddresses.toAddrString(ip));
        byte[] bytes = ip.getAddress();
        Inet4Address server = InetAddresses.getInet4Address(Arrays.copyOfRange(bytes, 4, 8));
        int flags = ByteStreams.newDataInput(bytes, 8).readShort() & 0xFFFF;
        int port = ~ByteStreams.newDataInput(bytes, 10).readShort() & 0xFFFF;
        byte[] clientBytes = Arrays.copyOfRange(bytes, 12, 16);
        for (int i = 0; i < clientBytes.length; ++i) {
            clientBytes[i] = ~clientBytes[i];
        }
        Inet4Address client = InetAddresses.getInet4Address(clientBytes);
        return new TeredoInfo(server, client, port, flags);
    }

    public static boolean isIsatapAddress(Inet6Address ip) {
        if (InetAddresses.isTeredoAddress(ip)) {
            return false;
        }
        byte[] bytes = ip.getAddress();
        if ((bytes[8] | 3) != 3) {
            return false;
        }
        return bytes[9] == 0 && bytes[10] == 94 && bytes[11] == -2;
    }

    public static Inet4Address getIsatapIPv4Address(Inet6Address ip) {
        Preconditions.checkArgument(InetAddresses.isIsatapAddress(ip), "Address '%s' is not an ISATAP address.", (Object)InetAddresses.toAddrString(ip));
        return InetAddresses.getInet4Address(Arrays.copyOfRange(ip.getAddress(), 12, 16));
    }

    public static boolean hasEmbeddedIPv4ClientAddress(Inet6Address ip) {
        return InetAddresses.isCompatIPv4Address(ip) || InetAddresses.is6to4Address(ip) || InetAddresses.isTeredoAddress(ip);
    }

    public static Inet4Address getEmbeddedIPv4ClientAddress(Inet6Address ip) {
        if (InetAddresses.isCompatIPv4Address(ip)) {
            return InetAddresses.getCompatIPv4Address(ip);
        }
        if (InetAddresses.is6to4Address(ip)) {
            return InetAddresses.get6to4IPv4Address(ip);
        }
        if (InetAddresses.isTeredoAddress(ip)) {
            return InetAddresses.getTeredoInfo(ip).getClient();
        }
        throw InetAddresses.formatIllegalArgumentException("'%s' has no embedded IPv4 address.", InetAddresses.toAddrString(ip));
    }

    public static boolean isMappedIPv4Address(String ipString) {
        byte[] bytes = InetAddresses.ipStringToBytes(ipString);
        if (bytes != null && bytes.length == 16) {
            int i;
            for (i = 0; i < 10; ++i) {
                if (bytes[i] == 0) continue;
                return false;
            }
            for (i = 10; i < 12; ++i) {
                if (bytes[i] == -1) continue;
                return false;
            }
            return true;
        }
        return false;
    }

    public static Inet4Address getCoercedIPv4Address(InetAddress ip) {
        if (ip instanceof Inet4Address) {
            return (Inet4Address)ip;
        }
        byte[] bytes = ip.getAddress();
        boolean leadingBytesOfZero = true;
        for (int i = 0; i < 15; ++i) {
            if (bytes[i] == 0) continue;
            leadingBytesOfZero = false;
            break;
        }
        if (leadingBytesOfZero && bytes[15] == 1) {
            return LOOPBACK4;
        }
        if (leadingBytesOfZero && bytes[15] == 0) {
            return ANY4;
        }
        Inet6Address ip6 = (Inet6Address)ip;
        long addressAsLong = 0L;
        addressAsLong = InetAddresses.hasEmbeddedIPv4ClientAddress(ip6) ? (long)InetAddresses.getEmbeddedIPv4ClientAddress(ip6).hashCode() : ByteBuffer.wrap(ip6.getAddress(), 0, 8).getLong();
        int coercedHash = Hashing.murmur3_32().hashLong(addressAsLong).asInt();
        if ((coercedHash |= 0xE0000000) == -1) {
            coercedHash = -2;
        }
        return InetAddresses.getInet4Address(Ints.toByteArray(coercedHash));
    }

    public static int coerceToInteger(InetAddress ip) {
        return ByteStreams.newDataInput(InetAddresses.getCoercedIPv4Address(ip).getAddress()).readInt();
    }

    public static BigInteger toBigInteger(InetAddress address) {
        return new BigInteger(1, address.getAddress());
    }

    public static Inet4Address fromInteger(int address) {
        return InetAddresses.getInet4Address(Ints.toByteArray(address));
    }

    public static Inet4Address fromIPv4BigInteger(BigInteger address) {
        return (Inet4Address)InetAddresses.fromBigInteger(address, false);
    }

    public static Inet6Address fromIPv6BigInteger(BigInteger address) {
        return (Inet6Address)InetAddresses.fromBigInteger(address, true);
    }

    private static InetAddress fromBigInteger(BigInteger address, boolean isIpv6) {
        Preconditions.checkArgument(address.signum() >= 0, "BigInteger must be greater than or equal to 0");
        int numBytes = isIpv6 ? 16 : 4;
        byte[] addressBytes = address.toByteArray();
        byte[] targetCopyArray = new byte[numBytes];
        int srcPos = Math.max(0, addressBytes.length - numBytes);
        int copyLength = addressBytes.length - srcPos;
        int destPos = numBytes - copyLength;
        for (int i = 0; i < srcPos; ++i) {
            if (addressBytes[i] == 0) continue;
            throw InetAddresses.formatIllegalArgumentException("BigInteger cannot be converted to InetAddress because it has more than %d bytes: %s", numBytes, address);
        }
        System.arraycopy(addressBytes, srcPos, targetCopyArray, destPos, copyLength);
        try {
            return InetAddress.getByAddress(targetCopyArray);
        } catch (UnknownHostException impossible) {
            throw new AssertionError((Object)impossible);
        }
    }

    public static InetAddress fromLittleEndianByteArray(byte[] addr) throws UnknownHostException {
        byte[] reversed = new byte[addr.length];
        for (int i = 0; i < addr.length; ++i) {
            reversed[i] = addr[addr.length - i - 1];
        }
        return InetAddress.getByAddress(reversed);
    }

    public static InetAddress decrement(InetAddress address) {
        int i;
        byte[] addr = address.getAddress();
        for (i = addr.length - 1; i >= 0 && addr[i] == 0; --i) {
            addr[i] = -1;
        }
        Preconditions.checkArgument(i >= 0, "Decrementing %s would wrap.", (Object)address);
        int n = i;
        addr[n] = (byte)(addr[n] - 1);
        return InetAddresses.bytesToInetAddress(addr);
    }

    public static InetAddress increment(InetAddress address) {
        int i;
        byte[] addr = address.getAddress();
        for (i = addr.length - 1; i >= 0 && addr[i] == -1; --i) {
            addr[i] = 0;
        }
        Preconditions.checkArgument(i >= 0, "Incrementing %s would wrap.", (Object)address);
        int n = i;
        addr[n] = (byte)(addr[n] + 1);
        return InetAddresses.bytesToInetAddress(addr);
    }

    public static boolean isMaximum(InetAddress address) {
        byte[] addr = address.getAddress();
        for (int i = 0; i < addr.length; ++i) {
            if (addr[i] == -1) continue;
            return false;
        }
        return true;
    }

    private static IllegalArgumentException formatIllegalArgumentException(String format, Object ... args2) {
        return new IllegalArgumentException(String.format(Locale.ROOT, format, args2));
    }

    @Beta
    public static final class TeredoInfo {
        private final Inet4Address server;
        private final Inet4Address client;
        private final int port;
        private final int flags;

        public TeredoInfo(@Nullable Inet4Address server, @Nullable Inet4Address client, int port, int flags) {
            Preconditions.checkArgument(port >= 0 && port <= 65535, "port '%s' is out of range (0 <= port <= 0xffff)", port);
            Preconditions.checkArgument(flags >= 0 && flags <= 65535, "flags '%s' is out of range (0 <= flags <= 0xffff)", flags);
            this.server = MoreObjects.firstNonNull(server, ANY4);
            this.client = MoreObjects.firstNonNull(client, ANY4);
            this.port = port;
            this.flags = flags;
        }

        public Inet4Address getServer() {
            return this.server;
        }

        public Inet4Address getClient() {
            return this.client;
        }

        public int getPort() {
            return this.port;
        }

        public int getFlags() {
            return this.flags;
        }
    }
}

