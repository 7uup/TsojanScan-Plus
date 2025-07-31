/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.util;

import com.alibaba.fastjson.JSONException;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Arrays;
import java.util.Properties;

public class IOUtils {
    public static final String FASTJSON_PROPERTIES = "fastjson.properties";
    public static final String FASTJSON_COMPATIBLEWITHJAVABEAN = "fastjson.compatibleWithJavaBean";
    public static final String FASTJSON_COMPATIBLEWITHFIELDNAME = "fastjson.compatibleWithFieldName";
    public static final Properties DEFAULT_PROPERTIES;
    public static final Charset UTF8;
    public static final char[] DIGITS;
    public static final boolean[] firstIdentifierFlags;
    public static final boolean[] identifierFlags;
    public static final byte[] specicalFlags_doubleQuotes;
    public static final byte[] specicalFlags_singleQuotes;
    public static final boolean[] specicalFlags_doubleQuotesFlags;
    public static final boolean[] specicalFlags_singleQuotesFlags;
    public static final char[] replaceChars;
    public static final char[] ASCII_CHARS;
    static final char[] digits;
    static final char[] DigitTens;
    static final char[] DigitOnes;
    static final int[] sizeTable;
    public static final char[] CA;
    public static final int[] IA;

    public static String getStringProperty(String name) {
        String prop = null;
        try {
            prop = System.getProperty(name);
        } catch (SecurityException securityException) {
            // empty catch block
        }
        return prop == null ? DEFAULT_PROPERTIES.getProperty(name) : prop;
    }

    public static void loadPropertiesFromFile() {
        InputStream imputStream = AccessController.doPrivileged(new PrivilegedAction<InputStream>(){

            @Override
            public InputStream run() {
                ClassLoader cl = Thread.currentThread().getContextClassLoader();
                if (cl != null) {
                    return cl.getResourceAsStream(IOUtils.FASTJSON_PROPERTIES);
                }
                return ClassLoader.getSystemResourceAsStream(IOUtils.FASTJSON_PROPERTIES);
            }
        });
        if (null != imputStream) {
            try {
                DEFAULT_PROPERTIES.load(imputStream);
                imputStream.close();
            } catch (IOException iOException) {
                // empty catch block
            }
        }
    }

    public static void close(Closeable x) {
        if (x != null) {
            try {
                x.close();
            } catch (Exception exception) {
                // empty catch block
            }
        }
    }

    public static int stringSize(long x) {
        long p = 10L;
        for (int i = 1; i < 19; ++i) {
            if (x < p) {
                return i;
            }
            p = 10L * p;
        }
        return 19;
    }

    public static void getChars(long i, int index, char[] buf) {
        int q2;
        int r;
        int charPos = index;
        int sign = 0;
        if (i < 0L) {
            sign = 45;
            i = -i;
        }
        while (i > Integer.MAX_VALUE) {
            long q = i / 100L;
            r = (int)(i - ((q << 6) + (q << 5) + (q << 2)));
            i = q;
            buf[--charPos] = DigitOnes[r];
            buf[--charPos] = DigitTens[r];
        }
        int i2 = (int)i;
        while (i2 >= 65536) {
            q2 = i2 / 100;
            r = i2 - ((q2 << 6) + (q2 << 5) + (q2 << 2));
            i2 = q2;
            buf[--charPos] = DigitOnes[r];
            buf[--charPos] = DigitTens[r];
        }
        do {
            q2 = i2 * 52429 >>> 19;
            r = i2 - ((q2 << 3) + (q2 << 1));
            buf[--charPos] = digits[r];
        } while ((i2 = q2) != 0);
        if (sign != 0) {
            buf[--charPos] = sign;
        }
    }

    public static void getChars(int i, int index, char[] buf) {
        int r;
        int q;
        int p = index;
        int sign = 0;
        if (i < 0) {
            sign = 45;
            i = -i;
        }
        while (i >= 65536) {
            q = i / 100;
            r = i - ((q << 6) + (q << 5) + (q << 2));
            i = q;
            buf[--p] = DigitOnes[r];
            buf[--p] = DigitTens[r];
        }
        do {
            q = i * 52429 >>> 19;
            r = i - ((q << 3) + (q << 1));
            buf[--p] = digits[r];
        } while ((i = q) != 0);
        if (sign != 0) {
            buf[--p] = sign;
        }
    }

    public static void getChars(byte b, int index, char[] buf) {
        int q;
        int i = b;
        int charPos = index;
        int sign = 0;
        if (i < 0) {
            sign = 45;
            i = -i;
        }
        do {
            q = i * 52429 >>> 19;
            int r = i - ((q << 3) + (q << 1));
            buf[--charPos] = digits[r];
        } while ((i = q) != 0);
        if (sign != 0) {
            buf[--charPos] = sign;
        }
    }

    public static int stringSize(int x) {
        int i = 0;
        while (x > sizeTable[i]) {
            ++i;
        }
        return i + 1;
    }

    public static void decode(CharsetDecoder charsetDecoder, ByteBuffer byteBuf, CharBuffer charByte) {
        try {
            CoderResult cr = charsetDecoder.decode(byteBuf, charByte, true);
            if (!cr.isUnderflow()) {
                cr.throwException();
            }
            if (!(cr = charsetDecoder.flush(charByte)).isUnderflow()) {
                cr.throwException();
            }
        } catch (CharacterCodingException x) {
            throw new JSONException("utf8 decode error, " + x.getMessage(), x);
        }
    }

    public static boolean firstIdentifier(char ch) {
        return ch < firstIdentifierFlags.length && firstIdentifierFlags[ch];
    }

    public static boolean isIdent(char ch) {
        return ch < identifierFlags.length && identifierFlags[ch];
    }

    public static byte[] decodeBase64(char[] chars, int offset, int charsLen) {
        int sIx;
        if (charsLen == 0) {
            return new byte[0];
        }
        int eIx = offset + charsLen - 1;
        for (sIx = offset; sIx < eIx && IA[chars[sIx]] < 0; ++sIx) {
        }
        while (eIx > 0 && IA[chars[eIx]] < 0) {
            --eIx;
        }
        int pad = chars[eIx] == '=' ? (chars[eIx - 1] == '=' ? 2 : 1) : 0;
        int cCnt = eIx - sIx + 1;
        int sepCnt = charsLen > 76 ? (chars[76] == '\r' ? cCnt / 78 : 0) << 1 : 0;
        int len = ((cCnt - sepCnt) * 6 >> 3) - pad;
        byte[] bytes = new byte[len];
        int d = 0;
        int cc = 0;
        int eLen = len / 3 * 3;
        while (d < eLen) {
            int i = IA[chars[sIx++]] << 18 | IA[chars[sIx++]] << 12 | IA[chars[sIx++]] << 6 | IA[chars[sIx++]];
            bytes[d++] = (byte)(i >> 16);
            bytes[d++] = (byte)(i >> 8);
            bytes[d++] = (byte)i;
            if (sepCnt <= 0 || ++cc != 19) continue;
            sIx += 2;
            cc = 0;
        }
        if (d < len) {
            int i = 0;
            int j = 0;
            while (sIx <= eIx - pad) {
                i |= IA[chars[sIx++]] << 18 - j * 6;
                ++j;
            }
            int r = 16;
            while (d < len) {
                bytes[d++] = (byte)(i >> r);
                r -= 8;
            }
        }
        return bytes;
    }

    public static byte[] decodeBase64(String chars, int offset, int charsLen) {
        int sIx;
        if (charsLen == 0) {
            return new byte[0];
        }
        int eIx = offset + charsLen - 1;
        for (sIx = offset; sIx < eIx && IA[chars.charAt(sIx)] < 0; ++sIx) {
        }
        while (eIx > 0 && IA[chars.charAt(eIx)] < 0) {
            --eIx;
        }
        int pad = chars.charAt(eIx) == '=' ? (chars.charAt(eIx - 1) == '=' ? 2 : 1) : 0;
        int cCnt = eIx - sIx + 1;
        int sepCnt = charsLen > 76 ? (chars.charAt(76) == '\r' ? cCnt / 78 : 0) << 1 : 0;
        int len = ((cCnt - sepCnt) * 6 >> 3) - pad;
        byte[] bytes = new byte[len];
        int d = 0;
        int cc = 0;
        int eLen = len / 3 * 3;
        while (d < eLen) {
            int i = IA[chars.charAt(sIx++)] << 18 | IA[chars.charAt(sIx++)] << 12 | IA[chars.charAt(sIx++)] << 6 | IA[chars.charAt(sIx++)];
            bytes[d++] = (byte)(i >> 16);
            bytes[d++] = (byte)(i >> 8);
            bytes[d++] = (byte)i;
            if (sepCnt <= 0 || ++cc != 19) continue;
            sIx += 2;
            cc = 0;
        }
        if (d < len) {
            int i = 0;
            int j = 0;
            while (sIx <= eIx - pad) {
                i |= IA[chars.charAt(sIx++)] << 18 - j * 6;
                ++j;
            }
            int r = 16;
            while (d < len) {
                bytes[d++] = (byte)(i >> r);
                r -= 8;
            }
        }
        return bytes;
    }

    public static byte[] decodeBase64(String s2) {
        int sIx;
        int sLen = s2.length();
        if (sLen == 0) {
            return new byte[0];
        }
        int eIx = sLen - 1;
        for (sIx = 0; sIx < eIx && IA[s2.charAt(sIx) & 0xFF] < 0; ++sIx) {
        }
        while (eIx > 0 && IA[s2.charAt(eIx) & 0xFF] < 0) {
            --eIx;
        }
        int pad = s2.charAt(eIx) == '=' ? (s2.charAt(eIx - 1) == '=' ? 2 : 1) : 0;
        int cCnt = eIx - sIx + 1;
        int sepCnt = sLen > 76 ? (s2.charAt(76) == '\r' ? cCnt / 78 : 0) << 1 : 0;
        int len = ((cCnt - sepCnt) * 6 >> 3) - pad;
        byte[] dArr = new byte[len];
        int d = 0;
        int cc = 0;
        int eLen = len / 3 * 3;
        while (d < eLen) {
            int i = IA[s2.charAt(sIx++)] << 18 | IA[s2.charAt(sIx++)] << 12 | IA[s2.charAt(sIx++)] << 6 | IA[s2.charAt(sIx++)];
            dArr[d++] = (byte)(i >> 16);
            dArr[d++] = (byte)(i >> 8);
            dArr[d++] = (byte)i;
            if (sepCnt <= 0 || ++cc != 19) continue;
            sIx += 2;
            cc = 0;
        }
        if (d < len) {
            int i = 0;
            int j = 0;
            while (sIx <= eIx - pad) {
                i |= IA[s2.charAt(sIx++)] << 18 - j * 6;
                ++j;
            }
            int r = 16;
            while (d < len) {
                dArr[d++] = (byte)(i >> r);
                r -= 8;
            }
        }
        return dArr;
    }

    /*
     * Enabled aggressive block sorting
     */
    public static int encodeUTF8(char[] chars, int offset, int len, byte[] bytes) {
        int sl = offset + len;
        int dp = 0;
        int dlASCII = dp + Math.min(len, bytes.length);
        while (dp < dlASCII && chars[offset] < '\u0080') {
            bytes[dp++] = (byte)chars[offset++];
        }
        while (true) {
            int c;
            block12: {
                int uc;
                block11: {
                    block13: {
                        if (offset >= sl) {
                            return dp;
                        }
                        if ((c = chars[offset++]) < 128) {
                            bytes[dp++] = (byte)c;
                            continue;
                        }
                        if (c < 2048) {
                            bytes[dp++] = (byte)(0xC0 | c >> 6);
                            bytes[dp++] = (byte)(0x80 | c & 0x3F);
                            continue;
                        }
                        if (c < 55296 || c >= 57344) break block12;
                        int ip = offset - 1;
                        if (c < 55296 || c >= 56320) break block13;
                        if (sl - ip < 2) {
                            uc = -1;
                            break block11;
                        } else {
                            char d = chars[ip + 1];
                            if (d >= '\udc00' && d < '\ue000') {
                                uc = (c << 10) + d + -56613888;
                                break block11;
                            } else {
                                bytes[dp++] = 63;
                                continue;
                            }
                        }
                    }
                    if (c >= 56320 && c < 57344) {
                        bytes[dp++] = 63;
                        continue;
                    }
                    uc = c;
                }
                if (uc < 0) {
                    bytes[dp++] = 63;
                    continue;
                }
                bytes[dp++] = (byte)(0xF0 | uc >> 18);
                bytes[dp++] = (byte)(0x80 | uc >> 12 & 0x3F);
                bytes[dp++] = (byte)(0x80 | uc >> 6 & 0x3F);
                bytes[dp++] = (byte)(0x80 | uc & 0x3F);
                ++offset;
                continue;
            }
            bytes[dp++] = (byte)(0xE0 | c >> 12);
            bytes[dp++] = (byte)(0x80 | c >> 6 & 0x3F);
            bytes[dp++] = (byte)(0x80 | c & 0x3F);
        }
    }

    public static int decodeUTF8(byte[] sa, int sp, int len, char[] da) {
        int sl = sp + len;
        int dp = 0;
        int dlASCII = Math.min(len, da.length);
        while (dp < dlASCII && sa[sp] >= 0) {
            da[dp++] = (char)sa[sp++];
        }
        while (sp < sl) {
            byte b3;
            byte b2;
            byte b1;
            if ((b1 = sa[sp++]) >= 0) {
                da[dp++] = (char)b1;
                continue;
            }
            if (b1 >> 5 == -2 && (b1 & 0x1E) != 0) {
                if (sp < sl) {
                    if (((b2 = sa[sp++]) & 0xC0) != 128) {
                        return -1;
                    }
                    da[dp++] = (char)(b1 << 6 ^ b2 ^ 0xF80);
                    continue;
                }
                return -1;
            }
            if (b1 >> 4 == -2) {
                if (sp + 1 < sl) {
                    boolean isSurrogate;
                    b2 = sa[sp++];
                    b3 = sa[sp++];
                    if (b1 == -32 && (b2 & 0xE0) == 128 || (b2 & 0xC0) != 128 || (b3 & 0xC0) != 128) {
                        return -1;
                    }
                    char c = (char)(b1 << 12 ^ b2 << 6 ^ (b3 ^ 0xFFFE1F80));
                    boolean bl = isSurrogate = c >= '\ud800' && c < '\ue000';
                    if (isSurrogate) {
                        return -1;
                    }
                    da[dp++] = c;
                    continue;
                }
                return -1;
            }
            if (b1 >> 3 == -2) {
                if (sp + 2 < sl) {
                    b2 = sa[sp++];
                    b3 = sa[sp++];
                    byte b4 = sa[sp++];
                    int uc = b1 << 18 ^ b2 << 12 ^ b3 << 6 ^ (b4 ^ 0x381F80);
                    if ((b2 & 0xC0) != 128 || (b3 & 0xC0) != 128 || (b4 & 0xC0) != 128 || uc < 65536 || uc >= 0x110000) {
                        return -1;
                    }
                    da[dp++] = (char)((uc >>> 10) + 55232);
                    da[dp++] = (char)((uc & 0x3FF) + 56320);
                    continue;
                }
                return -1;
            }
            return -1;
        }
        return dp;
    }

    public static String readAll(Reader reader) {
        StringBuilder buf = new StringBuilder();
        try {
            int len;
            char[] chars = new char[2048];
            while ((len = reader.read(chars, 0, chars.length)) >= 0) {
                buf.append(chars, 0, len);
            }
        } catch (Exception ex) {
            throw new JSONException("read string from reader error", ex);
        }
        return buf.toString();
    }

    public static boolean isValidJsonpQueryParam(String value) {
        if (value == null || value.length() == 0) {
            return false;
        }
        int len = value.length();
        for (int i = 0; i < len; ++i) {
            char ch = value.charAt(i);
            if (ch == '.' || IOUtils.isIdent(ch)) continue;
            return false;
        }
        return true;
    }

    static {
        int i;
        int c;
        DEFAULT_PROPERTIES = new Properties();
        UTF8 = Charset.forName("UTF-8");
        DIGITS = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        firstIdentifierFlags = new boolean[256];
        identifierFlags = new boolean[256];
        for (c = 0; c < firstIdentifierFlags.length; c = (int)((char)(c + 1))) {
            if (c >= 65 && c <= 90) {
                IOUtils.firstIdentifierFlags[c] = true;
                continue;
            }
            if (c >= 97 && c <= 122) {
                IOUtils.firstIdentifierFlags[c] = true;
                continue;
            }
            if (c != 95 && c != 36) continue;
            IOUtils.firstIdentifierFlags[c] = true;
        }
        for (c = 0; c < identifierFlags.length; c = (int)((char)(c + 1))) {
            if (c >= 65 && c <= 90) {
                IOUtils.identifierFlags[c] = true;
                continue;
            }
            if (c >= 97 && c <= 122) {
                IOUtils.identifierFlags[c] = true;
                continue;
            }
            if (c == 95) {
                IOUtils.identifierFlags[c] = true;
                continue;
            }
            if (c < 48 || c > 57) continue;
            IOUtils.identifierFlags[c] = true;
        }
        try {
            IOUtils.loadPropertiesFromFile();
        } catch (Throwable c2) {
            // empty catch block
        }
        specicalFlags_doubleQuotes = new byte[161];
        specicalFlags_singleQuotes = new byte[161];
        specicalFlags_doubleQuotesFlags = new boolean[161];
        specicalFlags_singleQuotesFlags = new boolean[161];
        replaceChars = new char[93];
        IOUtils.specicalFlags_doubleQuotes[0] = 4;
        IOUtils.specicalFlags_doubleQuotes[1] = 4;
        IOUtils.specicalFlags_doubleQuotes[2] = 4;
        IOUtils.specicalFlags_doubleQuotes[3] = 4;
        IOUtils.specicalFlags_doubleQuotes[4] = 4;
        IOUtils.specicalFlags_doubleQuotes[5] = 4;
        IOUtils.specicalFlags_doubleQuotes[6] = 4;
        IOUtils.specicalFlags_doubleQuotes[7] = 4;
        IOUtils.specicalFlags_doubleQuotes[8] = 1;
        IOUtils.specicalFlags_doubleQuotes[9] = 1;
        IOUtils.specicalFlags_doubleQuotes[10] = 1;
        IOUtils.specicalFlags_doubleQuotes[11] = 4;
        IOUtils.specicalFlags_doubleQuotes[12] = 1;
        IOUtils.specicalFlags_doubleQuotes[13] = 1;
        IOUtils.specicalFlags_doubleQuotes[34] = 1;
        IOUtils.specicalFlags_doubleQuotes[92] = 1;
        IOUtils.specicalFlags_singleQuotes[0] = 4;
        IOUtils.specicalFlags_singleQuotes[1] = 4;
        IOUtils.specicalFlags_singleQuotes[2] = 4;
        IOUtils.specicalFlags_singleQuotes[3] = 4;
        IOUtils.specicalFlags_singleQuotes[4] = 4;
        IOUtils.specicalFlags_singleQuotes[5] = 4;
        IOUtils.specicalFlags_singleQuotes[6] = 4;
        IOUtils.specicalFlags_singleQuotes[7] = 4;
        IOUtils.specicalFlags_singleQuotes[8] = 1;
        IOUtils.specicalFlags_singleQuotes[9] = 1;
        IOUtils.specicalFlags_singleQuotes[10] = 1;
        IOUtils.specicalFlags_singleQuotes[11] = 4;
        IOUtils.specicalFlags_singleQuotes[12] = 1;
        IOUtils.specicalFlags_singleQuotes[13] = 1;
        IOUtils.specicalFlags_singleQuotes[92] = 1;
        IOUtils.specicalFlags_singleQuotes[39] = 1;
        for (i = 14; i <= 31; ++i) {
            IOUtils.specicalFlags_doubleQuotes[i] = 4;
            IOUtils.specicalFlags_singleQuotes[i] = 4;
        }
        for (i = 127; i < 160; ++i) {
            IOUtils.specicalFlags_doubleQuotes[i] = 4;
            IOUtils.specicalFlags_singleQuotes[i] = 4;
        }
        for (i = 0; i < 161; ++i) {
            IOUtils.specicalFlags_doubleQuotesFlags[i] = specicalFlags_doubleQuotes[i] != 0;
            IOUtils.specicalFlags_singleQuotesFlags[i] = specicalFlags_singleQuotes[i] != 0;
        }
        IOUtils.replaceChars[0] = 48;
        IOUtils.replaceChars[1] = 49;
        IOUtils.replaceChars[2] = 50;
        IOUtils.replaceChars[3] = 51;
        IOUtils.replaceChars[4] = 52;
        IOUtils.replaceChars[5] = 53;
        IOUtils.replaceChars[6] = 54;
        IOUtils.replaceChars[7] = 55;
        IOUtils.replaceChars[8] = 98;
        IOUtils.replaceChars[9] = 116;
        IOUtils.replaceChars[10] = 110;
        IOUtils.replaceChars[11] = 118;
        IOUtils.replaceChars[12] = 102;
        IOUtils.replaceChars[13] = 114;
        IOUtils.replaceChars[34] = 34;
        IOUtils.replaceChars[39] = 39;
        IOUtils.replaceChars[47] = 47;
        IOUtils.replaceChars[92] = 92;
        ASCII_CHARS = new char[]{'0', '0', '0', '1', '0', '2', '0', '3', '0', '4', '0', '5', '0', '6', '0', '7', '0', '8', '0', '9', '0', 'A', '0', 'B', '0', 'C', '0', 'D', '0', 'E', '0', 'F', '1', '0', '1', '1', '1', '2', '1', '3', '1', '4', '1', '5', '1', '6', '1', '7', '1', '8', '1', '9', '1', 'A', '1', 'B', '1', 'C', '1', 'D', '1', 'E', '1', 'F', '2', '0', '2', '1', '2', '2', '2', '3', '2', '4', '2', '5', '2', '6', '2', '7', '2', '8', '2', '9', '2', 'A', '2', 'B', '2', 'C', '2', 'D', '2', 'E', '2', 'F'};
        digits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        DigitTens = new char[]{'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '4', '4', '4', '4', '4', '4', '4', '4', '4', '4', '5', '5', '5', '5', '5', '5', '5', '5', '5', '5', '6', '6', '6', '6', '6', '6', '6', '6', '6', '6', '7', '7', '7', '7', '7', '7', '7', '7', '7', '7', '8', '8', '8', '8', '8', '8', '8', '8', '8', '8', '9', '9', '9', '9', '9', '9', '9', '9', '9', '9'};
        DigitOnes = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        sizeTable = new int[]{9, 99, 999, 9999, 99999, 999999, 9999999, 99999999, 999999999, Integer.MAX_VALUE};
        CA = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();
        IA = new int[256];
        Arrays.fill(IA, -1);
        int iS = CA.length;
        for (i = 0; i < iS; ++i) {
            IOUtils.IA[IOUtils.CA[i]] = i;
        }
        IOUtils.IA[61] = 0;
    }
}

