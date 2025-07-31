/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.alibaba.fastjson.parser;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.JSONLexerBase;
import com.alibaba.fastjson.parser.JSONToken;
import com.alibaba.fastjson.parser.SymbolTable;
import com.alibaba.fastjson.util.ASMUtils;
import com.alibaba.fastjson.util.IOUtils;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public final class JSONScanner
extends JSONLexerBase {
    private final String text;
    private final int len;

    public JSONScanner(String input) {
        this(input, JSON.DEFAULT_PARSER_FEATURE);
    }

    public JSONScanner(String input, int features) {
        super(features);
        this.text = input;
        this.len = this.text.length();
        this.bp = -1;
        this.next();
        if (this.ch == '\ufeff') {
            this.next();
        }
    }

    @Override
    public final char charAt(int index) {
        if (index >= this.len) {
            return '\u001a';
        }
        return this.text.charAt(index);
    }

    @Override
    public final char next() {
        int index;
        char c = (index = ++this.bp) >= this.len ? (char)'\u001a' : (char)this.text.charAt(index);
        this.ch = c;
        return c;
    }

    public JSONScanner(char[] input, int inputLength) {
        this(input, inputLength, JSON.DEFAULT_PARSER_FEATURE);
    }

    public JSONScanner(char[] input, int inputLength, int features) {
        this(new String(input, 0, inputLength), features);
    }

    @Override
    protected final void copyTo(int offset, int count, char[] dest) {
        this.text.getChars(offset, offset + count, dest, 0);
    }

    static boolean charArrayCompare(String src, int offset, char[] dest) {
        int destLen = dest.length;
        if (destLen + offset > src.length()) {
            return false;
        }
        for (int i = 0; i < destLen; ++i) {
            if (dest[i] == src.charAt(offset + i)) continue;
            return false;
        }
        return true;
    }

    @Override
    public final boolean charArrayCompare(char[] chars) {
        return JSONScanner.charArrayCompare(this.text, this.bp, chars);
    }

    @Override
    public final int indexOf(char ch, int startIndex) {
        return this.text.indexOf(ch, startIndex);
    }

    @Override
    public final String addSymbol(int offset, int len, int hash, SymbolTable symbolTable) {
        return symbolTable.addSymbol(this.text, offset, len, hash);
    }

    @Override
    public byte[] bytesValue() {
        if (this.token == 26) {
            int start = this.np + 1;
            int len = this.sp;
            if (len % 2 != 0) {
                throw new JSONException("illegal state. " + len);
            }
            byte[] bytes = new byte[len / 2];
            for (int i = 0; i < bytes.length; ++i) {
                char c0 = this.text.charAt(start + i * 2);
                char c1 = this.text.charAt(start + i * 2 + 1);
                int b0 = c0 - (c0 <= '9' ? 48 : 55);
                int b1 = c1 - (c1 <= '9' ? 48 : 55);
                bytes[i] = (byte)(b0 << 4 | b1);
            }
            return bytes;
        }
        if (!this.hasSpecial) {
            return IOUtils.decodeBase64(this.text, this.np + 1, this.sp);
        }
        String escapedText = new String(this.sbuf, 0, this.sp);
        return IOUtils.decodeBase64(escapedText);
    }

    @Override
    public final String stringVal() {
        if (!this.hasSpecial) {
            return this.subString(this.np + 1, this.sp);
        }
        return new String(this.sbuf, 0, this.sp);
    }

    @Override
    public final String subString(int offset, int count) {
        if (ASMUtils.IS_ANDROID) {
            if (count < this.sbuf.length) {
                this.text.getChars(offset, offset + count, this.sbuf, 0);
                return new String(this.sbuf, 0, count);
            }
            char[] chars = new char[count];
            this.text.getChars(offset, offset + count, chars, 0);
            return new String(chars);
        }
        return this.text.substring(offset, offset + count);
    }

    @Override
    public final char[] sub_chars(int offset, int count) {
        if (ASMUtils.IS_ANDROID && count < this.sbuf.length) {
            this.text.getChars(offset, offset + count, this.sbuf, 0);
            return this.sbuf;
        }
        char[] chars = new char[count];
        this.text.getChars(offset, offset + count, chars, 0);
        return chars;
    }

    @Override
    public final String numberString() {
        char chLocal = this.charAt(this.np + this.sp - 1);
        int sp = this.sp;
        if (chLocal == 'L' || chLocal == 'S' || chLocal == 'B' || chLocal == 'F' || chLocal == 'D') {
            --sp;
        }
        return this.subString(this.np, sp);
    }

    @Override
    public final BigDecimal decimalValue() {
        char chLocal = this.charAt(this.np + this.sp - 1);
        int sp = this.sp;
        if (chLocal == 'L' || chLocal == 'S' || chLocal == 'B' || chLocal == 'F' || chLocal == 'D') {
            --sp;
        }
        if (sp > 65535) {
            throw new JSONException("decimal overflow");
        }
        int offset = this.np;
        int count = sp;
        if (count < this.sbuf.length) {
            this.text.getChars(offset, offset + count, this.sbuf, 0);
            return new BigDecimal(this.sbuf, 0, count, MathContext.UNLIMITED);
        }
        char[] chars = new char[count];
        this.text.getChars(offset, offset + count, chars, 0);
        return new BigDecimal(chars, 0, chars.length, MathContext.UNLIMITED);
    }

    public boolean scanISO8601DateIfMatch() {
        return this.scanISO8601DateIfMatch(true);
    }

    public boolean scanISO8601DateIfMatch(boolean strict) {
        int rest = this.len - this.bp;
        return this.scanISO8601DateIfMatch(strict, rest);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private boolean scanISO8601DateIfMatch(boolean strict, int rest) {
        char s1;
        char s0;
        char m1;
        char m0;
        char h1;
        char d1;
        int d0;
        char M1;
        char M0;
        char y3;
        char y2;
        char y1;
        char y0;
        char c10;
        if (rest < 8) {
            return false;
        }
        char c = this.charAt(this.bp);
        char c1 = this.charAt(this.bp + 1);
        char c2 = this.charAt(this.bp + 2);
        char c3 = this.charAt(this.bp + 3);
        char c4 = this.charAt(this.bp + 4);
        char c5 = this.charAt(this.bp + 5);
        char c6 = this.charAt(this.bp + 6);
        char c7 = this.charAt(this.bp + 7);
        if (!strict && rest > 13) {
            char c_r0 = this.charAt(this.bp + rest - 1);
            char c_r1 = this.charAt(this.bp + rest - 2);
            if (c == '/' && c1 == 'D' && c2 == 'a' && c3 == 't' && c4 == 'e' && c5 == '(' && c_r0 == '/' && c_r1 == ')') {
                int plusIndex = -1;
                for (int i = 6; i < rest; ++i) {
                    char c8 = this.charAt(this.bp + i);
                    if (c8 == '+') {
                        plusIndex = i;
                        continue;
                    }
                    if (c8 < '0' || c8 > '9') break;
                }
                if (plusIndex == -1) {
                    return false;
                }
                int offset = this.bp + 6;
                String numberText = this.subString(offset, this.bp + plusIndex - offset);
                long millis = Long.parseLong(numberText);
                this.calendar = Calendar.getInstance(this.timeZone, this.locale);
                this.calendar.setTimeInMillis(millis);
                this.token = 5;
                return true;
            }
        }
        if (rest == 8 || rest == 14 || rest == 16 && ((c10 = this.charAt(this.bp + 10)) == 'T' || c10 == ' ') || rest == 17 && this.charAt(this.bp + 6) != '-') {
            int seconds;
            int minute;
            int hour;
            int millis;
            char d12;
            char d02;
            char M12;
            char M02;
            char y32;
            char y22;
            char y12;
            char y02;
            boolean sperate17;
            if (strict) {
                return false;
            }
            char c8 = this.charAt(this.bp + 8);
            boolean c_47 = c4 == '-' && c7 == '-';
            boolean sperate16 = c_47 && rest == 16;
            boolean bl = sperate17 = c_47 && rest == 17;
            if (sperate17 || sperate16) {
                y02 = c;
                y12 = c1;
                y22 = c2;
                y32 = c3;
                M02 = c5;
                M12 = c6;
                d02 = c8;
                d12 = this.charAt(this.bp + 9);
            } else if (c4 == '-' && c6 == '-') {
                y02 = c;
                y12 = c1;
                y22 = c2;
                y32 = c3;
                M02 = '0';
                M12 = c5;
                d02 = '0';
                d12 = c7;
            } else {
                y02 = c;
                y12 = c1;
                y22 = c2;
                y32 = c3;
                M02 = c4;
                M12 = c5;
                d02 = c6;
                d12 = c7;
            }
            if (!JSONScanner.checkDate(y02, y12, y22, y32, M02, M12, d02, d12)) {
                return false;
            }
            this.setCalendar(y02, y12, y22, y32, M02, M12, d02, d12);
            if (rest != 8) {
                int s12;
                int s02;
                char m12;
                char m02;
                int h12;
                char h0;
                int c9 = this.charAt(this.bp + 9);
                c10 = this.charAt(this.bp + 10);
                char c11 = this.charAt(this.bp + 11);
                int c12 = this.charAt(this.bp + 12);
                int c13 = this.charAt(this.bp + 13);
                if (sperate17 && c10 == 'T' && c13 == 58 && this.charAt(this.bp + 16) == 'Z' || sperate16 && (c10 == ' ' || c10 == 'T') && c13 == 58) {
                    h0 = c11;
                    h12 = c12;
                    m02 = this.charAt(this.bp + 14);
                    m12 = this.charAt(this.bp + 15);
                    s02 = 48;
                    s12 = 48;
                } else {
                    h0 = c8;
                    h12 = c9;
                    m02 = c10;
                    m12 = c11;
                    s02 = c12;
                    s12 = c13;
                }
                if (!this.checkTime(h0, (char)h12, m02, m12, (char)s02, (char)s12)) {
                    return false;
                }
                if (rest == 17 && !sperate17) {
                    char S0 = this.charAt(this.bp + 14);
                    char S1 = this.charAt(this.bp + 15);
                    char S2 = this.charAt(this.bp + 16);
                    if (S0 < '0' || S0 > '9') {
                        return false;
                    }
                    if (S1 < '0' || S1 > '9') {
                        return false;
                    }
                    if (S2 < '0' || S2 > '9') {
                        return false;
                    }
                    millis = (S0 - 48) * 100 + (S1 - 48) * 10 + (S2 - 48);
                } else {
                    millis = 0;
                }
                hour = (h0 - 48) * 10 + (h12 - 48);
                minute = (m02 - 48) * 10 + (m12 - 48);
                seconds = (s02 - 48) * 10 + (s12 - 48);
            } else {
                hour = 0;
                minute = 0;
                seconds = 0;
                millis = 0;
            }
            this.calendar.set(11, hour);
            this.calendar.set(12, minute);
            this.calendar.set(13, seconds);
            this.calendar.set(14, millis);
            this.token = 5;
            return true;
        }
        if (rest < 9) {
            return false;
        }
        char c8 = this.charAt(this.bp + 8);
        char c9 = this.charAt(this.bp + 9);
        int date_len = 10;
        if (c4 == '-' && c7 == '-' || c4 == '/' && c7 == '/') {
            y0 = c;
            y1 = c1;
            y2 = c2;
            y3 = c3;
            M0 = c5;
            M1 = c6;
            if (c9 == ' ') {
                d0 = 48;
                d1 = c8;
                date_len = 9;
            } else {
                d0 = c8;
                d1 = c9;
            }
        } else if (c4 == '-' && c6 == '-') {
            y0 = c;
            y1 = c1;
            y2 = c2;
            y3 = c3;
            M0 = '0';
            M1 = c5;
            if (c8 == ' ') {
                d0 = 48;
                d1 = c7;
                date_len = 8;
            } else {
                d0 = c7;
                d1 = c8;
                date_len = 9;
            }
        } else if (c2 == '.' && c5 == '.' || c2 == '-' && c5 == '-') {
            d0 = c;
            d1 = c1;
            M0 = c3;
            M1 = c4;
            y0 = c6;
            y1 = c7;
            y2 = c8;
            y3 = c9;
        } else if (c8 == 'T') {
            y0 = c;
            y1 = c1;
            y2 = c2;
            y3 = c3;
            M0 = c4;
            M1 = c5;
            d0 = c6;
            d1 = c7;
            date_len = 8;
        } else {
            if (c4 != '\u5e74' && c4 != 45380) return false;
            y0 = c;
            y1 = c1;
            y2 = c2;
            y3 = c3;
            if (c7 == '\u6708' || c7 == '\uc6d4') {
                M0 = c5;
                M1 = c6;
                if (c9 == '\u65e5' || c9 == '\uc77c') {
                    d0 = 48;
                    d1 = c8;
                } else {
                    if (this.charAt(this.bp + 10) != '\u65e5' && this.charAt(this.bp + 10) != '\uc77c') return false;
                    d0 = c8;
                    d1 = c9;
                    date_len = 11;
                }
            } else {
                if (c6 != '\u6708' && c6 != 50900) return false;
                M0 = '0';
                M1 = c5;
                if (c8 == '\u65e5' || c8 == '\uc77c') {
                    d0 = 48;
                    d1 = c7;
                } else {
                    if (c9 != '\u65e5' && c9 != '\uc77c') return false;
                    d0 = c7;
                    d1 = c8;
                }
            }
        }
        if (!JSONScanner.checkDate(y0, y1, y2, y3, M0, M1, d0, d1)) {
            return false;
        }
        this.setCalendar(y0, y1, y2, y3, M0, M1, (char)d0, d1);
        char t = this.charAt(this.bp + date_len);
        if (t == 'T' && rest == 16 && date_len == 8 && this.charAt(this.bp + 15) == 'Z') {
            String[] timeZoneIDs;
            char s13;
            char s03;
            char m13;
            char m03;
            char h13;
            char h0 = this.charAt(this.bp + date_len + 1);
            if (!this.checkTime(h0, h13 = this.charAt(this.bp + date_len + 2), m03 = this.charAt(this.bp + date_len + 3), m13 = this.charAt(this.bp + date_len + 4), s03 = this.charAt(this.bp + date_len + 5), s13 = this.charAt(this.bp + date_len + 6))) {
                return false;
            }
            this.setTime(h0, h13, m03, m13, s03, s13);
            this.calendar.set(14, 0);
            if (this.calendar.getTimeZone().getRawOffset() != 0 && (timeZoneIDs = TimeZone.getAvailableIDs(0)).length > 0) {
                TimeZone timeZone = TimeZone.getTimeZone(timeZoneIDs[0]);
                this.calendar.setTimeZone(timeZone);
            }
            this.token = 5;
            return true;
        }
        if (t == 'T' || t == ' ' && !strict) {
            if (rest < date_len + 9) {
                return false;
            }
        } else {
            if (t == '\"' || t == '\u001a' || t == '\u65e5' || t == '\uc77c') {
                this.calendar.set(11, 0);
                this.calendar.set(12, 0);
                this.calendar.set(13, 0);
                this.calendar.set(14, 0);
                this.ch = this.charAt(this.bp += date_len);
                this.token = 5;
                return true;
            }
            if (t != '+' && t != '-') return false;
            if (this.len != date_len + 6) return false;
            if (this.charAt(this.bp + date_len + 3) != ':' || this.charAt(this.bp + date_len + 4) != '0' || this.charAt(this.bp + date_len + 5) != '0') {
                return false;
            }
            this.setTime('0', '0', '0', '0', '0', '0');
            this.calendar.set(14, 0);
            this.setTimeZone(t, this.charAt(this.bp + date_len + 1), this.charAt(this.bp + date_len + 2));
            return true;
        }
        if (this.charAt(this.bp + date_len + 3) != ':') {
            return false;
        }
        if (this.charAt(this.bp + date_len + 6) != ':') {
            return false;
        }
        char h0 = this.charAt(this.bp + date_len + 1);
        if (!this.checkTime(h0, h1 = this.charAt(this.bp + date_len + 2), m0 = this.charAt(this.bp + date_len + 4), m1 = this.charAt(this.bp + date_len + 5), s0 = this.charAt(this.bp + date_len + 7), s1 = this.charAt(this.bp + date_len + 8))) {
            return false;
        }
        this.setTime(h0, h1, m0, m1, s0, s1);
        char dot = this.charAt(this.bp + date_len + 9);
        int millisLen = -1;
        int millis = 0;
        if (dot == '.') {
            char S2;
            char S1;
            if (rest < date_len + 11) {
                return false;
            }
            char S0 = this.charAt(this.bp + date_len + 10);
            if (S0 < '0' || S0 > '9') {
                return false;
            }
            millis = S0 - 48;
            millisLen = 1;
            if (rest > date_len + 11 && (S1 = this.charAt(this.bp + date_len + 11)) >= '0' && S1 <= '9') {
                millis = millis * 10 + (S1 - 48);
                millisLen = 2;
            }
            if (millisLen == 2 && (S2 = this.charAt(this.bp + date_len + 12)) >= '0' && S2 <= '9') {
                millis = millis * 10 + (S2 - 48);
                millisLen = 3;
            }
        }
        this.calendar.set(14, millis);
        int timzeZoneLength = 0;
        char timeZoneFlag = this.charAt(this.bp + date_len + 10 + millisLen);
        if (timeZoneFlag == ' ') {
            timeZoneFlag = this.charAt(this.bp + date_len + 10 + ++millisLen);
        }
        if (timeZoneFlag == '+' || timeZoneFlag == '-') {
            char t0 = this.charAt(this.bp + date_len + 10 + millisLen + 1);
            if (t0 < '0' || t0 > '1') {
                return false;
            }
            char t1 = this.charAt(this.bp + date_len + 10 + millisLen + 2);
            if (t1 < '0' || t1 > '9') {
                return false;
            }
            char t2 = this.charAt(this.bp + date_len + 10 + millisLen + 3);
            int t3 = 48;
            int t4 = 48;
            if (t2 == ':') {
                t3 = this.charAt(this.bp + date_len + 10 + millisLen + 4);
                t4 = this.charAt(this.bp + date_len + 10 + millisLen + 5);
                if (t3 == 52 && t4 == 53) {
                    if ((t0 != '1' || t1 != '2' && t1 != '3') && (t0 != '0' || t1 != '5' && t1 != '8')) {
                        return false;
                    }
                } else {
                    if (t3 != 48 && t3 != 51) {
                        return false;
                    }
                    if (t4 != 48) {
                        return false;
                    }
                }
                timzeZoneLength = 6;
            } else if (t2 == '0') {
                t3 = this.charAt(this.bp + date_len + 10 + millisLen + 4);
                if (t3 != 48 && t3 != 51) {
                    return false;
                }
                timzeZoneLength = 5;
            } else if (t2 == '3' && this.charAt(this.bp + date_len + 10 + millisLen + 4) == '0') {
                t3 = 51;
                t4 = 48;
                timzeZoneLength = 5;
            } else if (t2 == '4' && this.charAt(this.bp + date_len + 10 + millisLen + 4) == '5') {
                t3 = 52;
                t4 = 53;
                timzeZoneLength = 5;
            } else {
                timzeZoneLength = 3;
            }
            this.setTimeZone(timeZoneFlag, t0, t1, (char)t3, (char)t4);
        } else if (timeZoneFlag == 'Z') {
            String[] timeZoneIDs;
            timzeZoneLength = 1;
            if (this.calendar.getTimeZone().getRawOffset() != 0 && (timeZoneIDs = TimeZone.getAvailableIDs(0)).length > 0) {
                TimeZone timeZone = TimeZone.getTimeZone(timeZoneIDs[0]);
                this.calendar.setTimeZone(timeZone);
            }
        }
        char end = this.charAt(this.bp + (date_len + 10 + millisLen + timzeZoneLength));
        if (end != '\u001a' && end != '\"') {
            return false;
        }
        this.ch = this.charAt(this.bp += date_len + 10 + millisLen + timzeZoneLength);
        this.token = 5;
        return true;
    }

    protected void setTime(char h0, char h1, char m0, char m1, char s0, char s1) {
        int hour = (h0 - 48) * 10 + (h1 - 48);
        int minute = (m0 - 48) * 10 + (m1 - 48);
        int seconds = (s0 - 48) * 10 + (s1 - 48);
        this.calendar.set(11, hour);
        this.calendar.set(12, minute);
        this.calendar.set(13, seconds);
    }

    protected void setTimeZone(char timeZoneFlag, char t0, char t1) {
        this.setTimeZone(timeZoneFlag, t0, t1, '0', '0');
    }

    protected void setTimeZone(char timeZoneFlag, char t0, char t1, char t3, char t4) {
        int timeZoneOffset = ((t0 - 48) * 10 + (t1 - 48)) * 3600 * 1000;
        timeZoneOffset += ((t3 - 48) * 10 + (t4 - 48)) * 60 * 1000;
        if (timeZoneFlag == '-') {
            timeZoneOffset = -timeZoneOffset;
        }
        if (this.calendar.getTimeZone().getRawOffset() != timeZoneOffset) {
            this.calendar.setTimeZone(new SimpleTimeZone(timeZoneOffset, Integer.toString(timeZoneOffset)));
        }
    }

    private boolean checkTime(char h0, char h1, char m0, char m1, char s0, char s1) {
        if (h0 == '0') {
            if (h1 < '0' || h1 > '9') {
                return false;
            }
        } else if (h0 == '1') {
            if (h1 < '0' || h1 > '9') {
                return false;
            }
        } else if (h0 == '2') {
            if (h1 < '0' || h1 > '4') {
                return false;
            }
        } else {
            return false;
        }
        if (m0 >= '0' && m0 <= '5') {
            if (m1 < '0' || m1 > '9') {
                return false;
            }
        } else if (m0 == '6') {
            if (m1 != '0') {
                return false;
            }
        } else {
            return false;
        }
        if (s0 >= '0' && s0 <= '5') {
            if (s1 < '0' || s1 > '9') {
                return false;
            }
        } else if (s0 == '6') {
            if (s1 != '0') {
                return false;
            }
        } else {
            return false;
        }
        return true;
    }

    private void setCalendar(char y0, char y1, char y2, char y3, char M0, char M1, char d0, char d1) {
        this.calendar = Calendar.getInstance(this.timeZone, this.locale);
        int year = (y0 - 48) * 1000 + (y1 - 48) * 100 + (y2 - 48) * 10 + (y3 - 48);
        int month = (M0 - 48) * 10 + (M1 - 48) - 1;
        int day = (d0 - 48) * 10 + (d1 - 48);
        this.calendar.set(1, year);
        this.calendar.set(2, month);
        this.calendar.set(5, day);
    }

    static boolean checkDate(char y0, char y1, char y2, char y3, char M0, char M1, int d0, int d1) {
        if (y0 < '0' || y0 > '9') {
            return false;
        }
        if (y1 < '0' || y1 > '9') {
            return false;
        }
        if (y2 < '0' || y2 > '9') {
            return false;
        }
        if (y3 < '0' || y3 > '9') {
            return false;
        }
        if (M0 == '0') {
            if (M1 < '1' || M1 > '9') {
                return false;
            }
        } else if (M0 == '1') {
            if (M1 != '0' && M1 != '1' && M1 != '2') {
                return false;
            }
        } else {
            return false;
        }
        if (d0 == 48) {
            if (d1 < 49 || d1 > 57) {
                return false;
            }
        } else if (d0 == 49 || d0 == 50) {
            if (d1 < 48 || d1 > 57) {
                return false;
            }
        } else if (d0 == 51) {
            if (d1 != 48 && d1 != 49) {
                return false;
            }
        } else {
            return false;
        }
        return true;
    }

    @Override
    public boolean isEOF() {
        return this.bp == this.len || this.ch == '\u001a' && this.bp + 1 >= this.len;
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public int scanFieldInt(char[] fieldName) {
        int n;
        int value;
        boolean negative;
        char ch;
        int index;
        char startChar;
        int startPos;
        block24: {
            int n2;
            boolean quote;
            this.matchStat = 0;
            startPos = this.bp;
            startChar = this.ch;
            if (!JSONScanner.charArrayCompare(this.text, this.bp, fieldName)) {
                this.matchStat = -2;
                return 0;
            }
            index = this.bp + fieldName.length;
            boolean bl = quote = (ch = this.charAt(index++)) == '\"';
            if (quote) {
                ch = this.charAt(index++);
            }
            boolean bl2 = negative = ch == '-';
            if (negative) {
                ch = this.charAt(index++);
            }
            if (ch >= '0' && ch <= '9') {
                value = ch - 48;
            } else {
                this.matchStat = -1;
                return 0;
            }
            while ((ch = this.charAt(index++)) >= '0' && ch <= '9') {
                int value_10 = value * 10;
                if (value_10 < value) {
                    this.matchStat = -1;
                    return 0;
                }
                value = value_10 + (ch - 48);
            }
            if (ch == '.') {
                this.matchStat = -1;
                return 0;
            }
            if (value < 0) {
                this.matchStat = -1;
                return 0;
            }
            if (quote) {
                if (ch != '\"') {
                    this.matchStat = -1;
                    return 0;
                }
                ch = this.charAt(index++);
            }
            while (true) {
                if (ch == ',' || ch == '}') {
                    this.bp = index - 1;
                    if (ch == ',') {
                        break;
                    }
                    break block24;
                }
                if (!JSONScanner.isWhitespace(ch)) {
                    this.matchStat = -1;
                    return 0;
                }
                ch = this.charAt(index++);
            }
            this.ch = this.charAt(++this.bp);
            this.matchStat = 3;
            this.token = 16;
            if (negative) {
                n2 = -value;
                return n2;
            }
            n2 = value;
            return n2;
        }
        if (ch == '}') {
            this.bp = index - 1;
            ch = this.charAt(++this.bp);
            while (true) {
                if (ch == ',') {
                    this.token = 16;
                    this.ch = this.charAt(++this.bp);
                    break;
                }
                if (ch == ']') {
                    this.token = 15;
                    this.ch = this.charAt(++this.bp);
                    break;
                }
                if (ch == '}') {
                    this.token = 13;
                    this.ch = this.charAt(++this.bp);
                    break;
                }
                if (ch == '\u001a') {
                    this.token = 20;
                    break;
                }
                if (!JSONScanner.isWhitespace(ch)) {
                    this.bp = startPos;
                    this.ch = startChar;
                    this.matchStat = -1;
                    return 0;
                }
                ch = this.charAt(++this.bp);
            }
            this.matchStat = 4;
        }
        if (negative) {
            n = -value;
            return n;
        }
        n = value;
        return n;
    }

    @Override
    public String scanFieldString(char[] fieldName) {
        String stringVal;
        int endIndex;
        char ch;
        char startChar;
        int startPos;
        block21: {
            int startIndex;
            this.matchStat = 0;
            startPos = this.bp;
            startChar = this.ch;
            while (!JSONScanner.charArrayCompare(this.text, this.bp, fieldName)) {
                if (JSONScanner.isWhitespace(this.ch)) {
                    this.next();
                    while (JSONScanner.isWhitespace(this.ch)) {
                        this.next();
                    }
                    continue;
                }
                this.matchStat = -2;
                return this.stringDefaultValue();
            }
            int index = this.bp + fieldName.length;
            int spaceCount = 0;
            if ((ch = this.charAt(index++)) != '\"') {
                while (JSONScanner.isWhitespace(ch)) {
                    ++spaceCount;
                    ch = this.charAt(index++);
                }
                if (ch != '\"') {
                    this.matchStat = -1;
                    return this.stringDefaultValue();
                }
            }
            if ((endIndex = this.indexOf('\"', startIndex = index)) == -1) {
                throw new JSONException("unclosed str");
            }
            stringVal = this.subString(startIndex, endIndex - startIndex);
            if (stringVal.indexOf(92) != -1) {
                while (true) {
                    int slashCount = 0;
                    for (int i = endIndex - 1; i >= 0 && this.charAt(i) == '\\'; --i) {
                        ++slashCount;
                    }
                    if (slashCount % 2 == 0) break;
                    endIndex = this.indexOf('\"', endIndex + 1);
                }
                int chars_len = endIndex - (this.bp + fieldName.length + 1 + spaceCount);
                char[] chars = this.sub_chars(this.bp + fieldName.length + 1 + spaceCount, chars_len);
                stringVal = JSONScanner.readString(chars, chars_len);
            }
            if ((this.features & Feature.TrimStringFieldValue.mask) != 0) {
                stringVal = stringVal.trim();
            }
            ch = this.charAt(endIndex + 1);
            while (true) {
                if (ch == ',' || ch == '}') break block21;
                if (!JSONScanner.isWhitespace(ch)) break;
                ch = this.charAt(++endIndex + 1);
            }
            this.matchStat = -1;
            return this.stringDefaultValue();
        }
        this.bp = endIndex + 1;
        this.ch = ch;
        String strVal = stringVal;
        if (ch == ',') {
            this.ch = this.charAt(++this.bp);
            this.matchStat = 3;
            return strVal;
        }
        if ((ch = this.charAt(++this.bp)) == ',') {
            this.token = 16;
            this.ch = this.charAt(++this.bp);
        } else if (ch == ']') {
            this.token = 15;
            this.ch = this.charAt(++this.bp);
        } else if (ch == '}') {
            this.token = 13;
            this.ch = this.charAt(++this.bp);
        } else if (ch == '\u001a') {
            this.token = 20;
        } else {
            this.bp = startPos;
            this.ch = startChar;
            this.matchStat = -1;
            return this.stringDefaultValue();
        }
        this.matchStat = 4;
        return strVal;
    }

    @Override
    public Date scanFieldDate(char[] fieldName) {
        Date dateVal;
        char ch;
        char startChar;
        int startPos;
        block23: {
            this.matchStat = 0;
            startPos = this.bp;
            startChar = this.ch;
            if (!JSONScanner.charArrayCompare(this.text, this.bp, fieldName)) {
                this.matchStat = -2;
                return null;
            }
            int index = this.bp + fieldName.length;
            if ((ch = this.charAt(index++)) == '\"') {
                int startIndex = index;
                int endIndex = this.indexOf('\"', startIndex);
                if (endIndex == -1) {
                    throw new JSONException("unclosed str");
                }
                int rest = endIndex - startIndex;
                this.bp = index;
                if (!this.scanISO8601DateIfMatch(false, rest)) {
                    this.bp = startPos;
                    this.matchStat = -1;
                    return null;
                }
                dateVal = this.calendar.getTime();
                ch = this.charAt(endIndex + 1);
                this.bp = startPos;
                while (true) {
                    if (ch == ',' || ch == '}') {
                        this.bp = endIndex + 1;
                        this.ch = ch;
                        break block23;
                    }
                    if (!JSONScanner.isWhitespace(ch)) break;
                    ch = this.charAt(++endIndex + 1);
                }
                this.matchStat = -1;
                return null;
            }
            if (ch == '-' || ch >= '0' && ch <= '9') {
                long millis = 0L;
                boolean negative = false;
                if (ch == '-') {
                    ch = this.charAt(index++);
                    negative = true;
                }
                if (ch >= '0' && ch <= '9') {
                    millis = ch - 48;
                    while ((ch = this.charAt(index++)) >= '0' && ch <= '9') {
                        millis = millis * 10L + (long)(ch - 48);
                    }
                    if (ch == ',' || ch == '}') {
                        this.bp = index - 1;
                    }
                }
                if (millis < 0L) {
                    this.matchStat = -1;
                    return null;
                }
                if (negative) {
                    millis = -millis;
                }
                dateVal = new Date(millis);
            } else {
                this.matchStat = -1;
                return null;
            }
        }
        if (ch == ',') {
            this.ch = this.charAt(++this.bp);
            this.matchStat = 3;
            this.token = 16;
            return dateVal;
        }
        if ((ch = this.charAt(++this.bp)) == ',') {
            this.token = 16;
            this.ch = this.charAt(++this.bp);
        } else if (ch == ']') {
            this.token = 15;
            this.ch = this.charAt(++this.bp);
        } else if (ch == '}') {
            this.token = 13;
            this.ch = this.charAt(++this.bp);
        } else if (ch == '\u001a') {
            this.token = 20;
        } else {
            this.bp = startPos;
            this.ch = startChar;
            this.matchStat = -1;
            return null;
        }
        this.matchStat = 4;
        return dateVal;
    }

    @Override
    public long scanFieldSymbol(char[] fieldName) {
        long hash;
        block15: {
            char ch;
            this.matchStat = 0;
            while (!JSONScanner.charArrayCompare(this.text, this.bp, fieldName)) {
                if (JSONScanner.isWhitespace(this.ch)) {
                    this.next();
                    while (JSONScanner.isWhitespace(this.ch)) {
                        this.next();
                    }
                    continue;
                }
                this.matchStat = -2;
                return 0L;
            }
            int index = this.bp + fieldName.length;
            int spaceCount = 0;
            if ((ch = this.charAt(index++)) != '\"') {
                while (JSONScanner.isWhitespace(ch)) {
                    ch = this.charAt(index++);
                    ++spaceCount;
                }
                if (ch != '\"') {
                    this.matchStat = -1;
                    return 0L;
                }
            }
            hash = -3750763034362895579L;
            while (true) {
                if ((ch = this.charAt(index++)) == '\"') break;
                if (index > this.len) {
                    this.matchStat = -1;
                    return 0L;
                }
                hash ^= (long)ch;
                hash *= 1099511628211L;
            }
            this.bp = index;
            this.ch = ch = this.charAt(this.bp);
            while (true) {
                if (ch == ',') {
                    this.ch = this.charAt(++this.bp);
                    this.matchStat = 3;
                    return hash;
                }
                if (ch == '}') {
                    this.next();
                    this.skipWhitespace();
                    ch = this.getCurrent();
                    if (ch == ',') {
                        this.token = 16;
                        this.ch = this.charAt(++this.bp);
                        break block15;
                    }
                    if (ch == ']') {
                        this.token = 15;
                        this.ch = this.charAt(++this.bp);
                        break block15;
                    }
                    if (ch == '}') {
                        this.token = 13;
                        this.ch = this.charAt(++this.bp);
                        break block15;
                    }
                    if (ch == '\u001a') {
                        this.token = 20;
                        break block15;
                    }
                    this.matchStat = -1;
                    return 0L;
                }
                if (!JSONScanner.isWhitespace(ch)) break;
                ch = this.charAt(++this.bp);
            }
            this.matchStat = -1;
            return 0L;
        }
        this.matchStat = 4;
        return hash;
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public Collection<String> scanFieldStringArray(char[] fieldName, Class<?> type) {
        Collection<String> list;
        block23: {
            boolean space;
            char ch;
            int index;
            char startChar;
            int startPos;
            block22: {
                block24: {
                    this.matchStat = 0;
                    while (this.ch == '\n' || this.ch == ' ') {
                        int index2;
                        this.ch = (char)((index2 = ++this.bp) >= this.len ? 26 : (int)this.text.charAt(index2));
                    }
                    if (!JSONScanner.charArrayCompare(this.text, this.bp, fieldName)) {
                        this.matchStat = -2;
                        return null;
                    }
                    list = this.newCollectionByType(type);
                    startPos = this.bp;
                    startChar = this.ch;
                    index = this.bp + fieldName.length;
                    if ((ch = this.charAt(index++)) == '[') break block24;
                    if (!this.text.startsWith("ull", index)) {
                        this.matchStat = -1;
                        return null;
                    }
                    index += 3;
                    ch = this.charAt(index++);
                    list = null;
                    break block22;
                }
                ch = this.charAt(index++);
                while (true) {
                    block28: {
                        String stringVal;
                        int endIndex;
                        block26: {
                            int startIndex;
                            block27: {
                                block25: {
                                    if (ch != '\"') break block25;
                                    startIndex = index;
                                    endIndex = this.indexOf('\"', startIndex);
                                    if (endIndex == -1) {
                                        throw new JSONException("unclosed str");
                                    }
                                    stringVal = this.subString(startIndex, endIndex - startIndex);
                                    if (stringVal.indexOf(92) == -1) break block26;
                                    break block27;
                                }
                                if (ch == 'n' && this.text.startsWith("ull", index)) {
                                    index += 3;
                                    ch = this.charAt(index++);
                                    list.add(null);
                                    break block28;
                                } else if (ch == ']' && list.size() == 0) {
                                    ch = this.charAt(index++);
                                    break block22;
                                } else {
                                    this.matchStat = -1;
                                    return null;
                                }
                            }
                            while (true) {
                                int slashCount = 0;
                                for (int i = endIndex - 1; i >= 0 && this.charAt(i) == '\\'; ++slashCount, --i) {
                                }
                                if (slashCount % 2 == 0) {
                                    int chars_len = endIndex - startIndex;
                                    char[] chars = this.sub_chars(startIndex, chars_len);
                                    stringVal = JSONScanner.readString(chars, chars_len);
                                    break;
                                }
                                endIndex = this.indexOf('\"', endIndex + 1);
                            }
                        }
                        index = endIndex + 1;
                        ch = this.charAt(index++);
                        list.add(stringVal);
                    }
                    if (ch != ',') break;
                    ch = this.charAt(index++);
                }
                if (ch != ']') {
                    this.matchStat = -1;
                    return null;
                }
                ch = this.charAt(index++);
                while (JSONScanner.isWhitespace(ch)) {
                    ch = this.charAt(index++);
                }
            }
            this.bp = index;
            if (ch == ',') {
                this.ch = this.charAt(this.bp);
                this.matchStat = 3;
                return list;
            }
            if (ch != '}') {
                this.ch = startChar;
                this.bp = startPos;
                this.matchStat = -1;
                return null;
            }
            ch = this.charAt(this.bp);
            do {
                if (ch == ',') {
                    this.token = 16;
                    this.ch = this.charAt(++this.bp);
                    break block23;
                }
                if (ch == ']') {
                    this.token = 15;
                    this.ch = this.charAt(++this.bp);
                    break block23;
                }
                if (ch == '}') {
                    this.token = 13;
                    this.ch = this.charAt(++this.bp);
                    break block23;
                }
                if (ch == '\u001a') {
                    this.token = 20;
                    this.ch = ch;
                    break block23;
                }
                space = false;
                while (JSONScanner.isWhitespace(ch)) {
                    ch = this.charAt(index++);
                    this.bp = index;
                    space = true;
                }
            } while (space);
            this.matchStat = -1;
            return null;
        }
        this.matchStat = 4;
        return list;
    }

    @Override
    public long scanFieldLong(char[] fieldName) {
        long value;
        boolean negative;
        block19: {
            char ch;
            boolean quote;
            this.matchStat = 0;
            int startPos = this.bp;
            char startChar = this.ch;
            if (!JSONScanner.charArrayCompare(this.text, this.bp, fieldName)) {
                this.matchStat = -2;
                return 0L;
            }
            int index = this.bp + fieldName.length;
            boolean bl = quote = (ch = this.charAt(index++)) == '\"';
            if (quote) {
                ch = this.charAt(index++);
            }
            negative = false;
            if (ch == '-') {
                ch = this.charAt(index++);
                negative = true;
            }
            if (ch >= '0' && ch <= '9') {
                boolean valid;
                value = ch - 48;
                while ((ch = this.charAt(index++)) >= '0' && ch <= '9') {
                    value = value * 10L + (long)(ch - 48);
                }
                if (ch == '.') {
                    this.matchStat = -1;
                    return 0L;
                }
                if (quote) {
                    if (ch != '\"') {
                        this.matchStat = -1;
                        return 0L;
                    }
                    ch = this.charAt(index++);
                }
                if (ch == ',' || ch == '}') {
                    this.bp = index - 1;
                }
                boolean bl2 = valid = value >= 0L || value == Long.MIN_VALUE && negative;
                if (!valid) {
                    this.bp = startPos;
                    this.ch = startChar;
                    this.matchStat = -1;
                    return 0L;
                }
            } else {
                this.bp = startPos;
                this.ch = startChar;
                this.matchStat = -1;
                return 0L;
            }
            while (true) {
                if (ch == ',') {
                    this.ch = this.charAt(++this.bp);
                    this.matchStat = 3;
                    this.token = 16;
                    return negative ? -value : value;
                }
                if (ch == '}') {
                    ch = this.charAt(++this.bp);
                    while (true) {
                        if (ch == ',') {
                            this.token = 16;
                            this.ch = this.charAt(++this.bp);
                            break block19;
                        }
                        if (ch == ']') {
                            this.token = 15;
                            this.ch = this.charAt(++this.bp);
                            break block19;
                        }
                        if (ch == '}') {
                            this.token = 13;
                            this.ch = this.charAt(++this.bp);
                            break block19;
                        }
                        if (ch == '\u001a') {
                            this.token = 20;
                            break block19;
                        }
                        if (!JSONScanner.isWhitespace(ch)) break;
                        ch = this.charAt(++this.bp);
                    }
                    this.bp = startPos;
                    this.ch = startChar;
                    this.matchStat = -1;
                    return 0L;
                }
                if (!JSONScanner.isWhitespace(ch)) break;
                this.bp = index;
                ch = this.charAt(index++);
            }
            this.matchStat = -1;
            return 0L;
        }
        this.matchStat = 4;
        return negative ? -value : value;
    }

    @Override
    public boolean scanFieldBoolean(char[] fieldName) {
        boolean value;
        block29: {
            char ch;
            boolean quote;
            this.matchStat = 0;
            if (!JSONScanner.charArrayCompare(this.text, this.bp, fieldName)) {
                this.matchStat = -2;
                return false;
            }
            int startPos = this.bp;
            int index = this.bp + fieldName.length;
            boolean bl = quote = (ch = this.charAt(index++)) == '\"';
            if (quote) {
                ch = this.charAt(index++);
            }
            if (ch == 't') {
                if (this.charAt(index++) != 'r') {
                    this.matchStat = -1;
                    return false;
                }
                if (this.charAt(index++) != 'u') {
                    this.matchStat = -1;
                    return false;
                }
                if (this.charAt(index++) != 'e') {
                    this.matchStat = -1;
                    return false;
                }
                if (quote && this.charAt(index++) != '\"') {
                    this.matchStat = -1;
                    return false;
                }
                this.bp = index;
                ch = this.charAt(this.bp);
                value = true;
            } else if (ch == 'f') {
                if (this.charAt(index++) != 'a') {
                    this.matchStat = -1;
                    return false;
                }
                if (this.charAt(index++) != 'l') {
                    this.matchStat = -1;
                    return false;
                }
                if (this.charAt(index++) != 's') {
                    this.matchStat = -1;
                    return false;
                }
                if (this.charAt(index++) != 'e') {
                    this.matchStat = -1;
                    return false;
                }
                if (quote && this.charAt(index++) != '\"') {
                    this.matchStat = -1;
                    return false;
                }
                this.bp = index;
                ch = this.charAt(this.bp);
                value = false;
            } else if (ch == '1') {
                if (quote && this.charAt(index++) != '\"') {
                    this.matchStat = -1;
                    return false;
                }
                this.bp = index;
                ch = this.charAt(this.bp);
                value = true;
            } else if (ch == '0') {
                if (quote && this.charAt(index++) != '\"') {
                    this.matchStat = -1;
                    return false;
                }
                this.bp = index;
                ch = this.charAt(this.bp);
                value = false;
            } else {
                this.matchStat = -1;
                return false;
            }
            while (true) {
                if (ch == ',') {
                    this.ch = this.charAt(++this.bp);
                    this.matchStat = 3;
                    this.token = 16;
                    break block29;
                }
                if (ch == '}') {
                    block30: {
                        ch = this.charAt(++this.bp);
                        while (true) {
                            if (ch == ',') {
                                this.token = 16;
                                this.ch = this.charAt(++this.bp);
                                break block30;
                            }
                            if (ch == ']') {
                                this.token = 15;
                                this.ch = this.charAt(++this.bp);
                                break block30;
                            }
                            if (ch == '}') {
                                this.token = 13;
                                this.ch = this.charAt(++this.bp);
                                break block30;
                            }
                            if (ch == '\u001a') {
                                this.token = 20;
                                break block30;
                            }
                            if (!JSONScanner.isWhitespace(ch)) break;
                            ch = this.charAt(++this.bp);
                        }
                        this.matchStat = -1;
                        return false;
                    }
                    this.matchStat = 4;
                    break block29;
                }
                if (!JSONScanner.isWhitespace(ch)) break;
                ch = this.charAt(++this.bp);
            }
            this.bp = startPos;
            ch = this.charAt(this.bp);
            this.matchStat = -1;
            return false;
        }
        return value;
    }

    @Override
    public final int scanInt(char expectNext) {
        int value;
        boolean negative;
        boolean quote;
        this.matchStat = 0;
        int mark = this.bp;
        int offset = this.bp;
        char chLocal = this.charAt(offset++);
        while (JSONScanner.isWhitespace(chLocal)) {
            chLocal = this.charAt(offset++);
        }
        boolean bl = quote = chLocal == '\"';
        if (quote) {
            chLocal = this.charAt(offset++);
        }
        boolean bl2 = negative = chLocal == '-';
        if (negative) {
            chLocal = this.charAt(offset++);
        }
        if (chLocal >= '0' && chLocal <= '9') {
            value = chLocal - 48;
            while ((chLocal = this.charAt(offset++)) >= '0' && chLocal <= '9') {
                int value_10 = value * 10;
                if (value_10 < value) {
                    throw new JSONException("parseInt error : " + this.subString(mark, offset - 1));
                }
                value = value_10 + (chLocal - 48);
            }
            if (chLocal == '.') {
                this.matchStat = -1;
                return 0;
            }
            if (quote) {
                if (chLocal != '\"') {
                    this.matchStat = -1;
                    return 0;
                }
                chLocal = this.charAt(offset++);
            }
            if (value < 0) {
                this.matchStat = -1;
                return 0;
            }
        } else {
            if (chLocal == 'n' && this.charAt(offset++) == 'u' && this.charAt(offset++) == 'l' && this.charAt(offset++) == 'l') {
                this.matchStat = 5;
                int value2 = 0;
                chLocal = this.charAt(offset++);
                if (quote && chLocal == '\"') {
                    chLocal = this.charAt(offset++);
                }
                while (true) {
                    if (chLocal == ',') {
                        this.bp = offset;
                        this.ch = this.charAt(this.bp);
                        this.matchStat = 5;
                        this.token = 16;
                        return value2;
                    }
                    if (chLocal == ']') {
                        this.bp = offset;
                        this.ch = this.charAt(this.bp);
                        this.matchStat = 5;
                        this.token = 15;
                        return value2;
                    }
                    if (!JSONScanner.isWhitespace(chLocal)) break;
                    chLocal = this.charAt(offset++);
                }
                this.matchStat = -1;
                return 0;
            }
            this.matchStat = -1;
            return 0;
        }
        while (true) {
            if (chLocal == expectNext) {
                this.bp = offset;
                this.ch = this.charAt(this.bp);
                this.matchStat = 3;
                this.token = 16;
                return negative ? -value : value;
            }
            if (!JSONScanner.isWhitespace(chLocal)) break;
            chLocal = this.charAt(offset++);
        }
        this.matchStat = -1;
        return negative ? -value : value;
    }

    @Override
    public double scanDouble(char seperator) {
        double value;
        boolean negative;
        char chLocal;
        boolean quote;
        this.matchStat = 0;
        int offset = this.bp;
        boolean bl = quote = (chLocal = this.charAt(offset++)) == '\"';
        if (quote) {
            chLocal = this.charAt(offset++);
        }
        boolean bl2 = negative = chLocal == '-';
        if (negative) {
            chLocal = this.charAt(offset++);
        }
        if (chLocal >= '0' && chLocal <= '9') {
            int count;
            int start;
            boolean exp;
            boolean small;
            long intVal = chLocal - 48;
            while ((chLocal = this.charAt(offset++)) >= '0' && chLocal <= '9') {
                intVal = intVal * 10L + (long)(chLocal - 48);
            }
            long power = 1L;
            boolean bl3 = small = chLocal == '.';
            if (small) {
                if ((chLocal = this.charAt(offset++)) >= '0' && chLocal <= '9') {
                    intVal = intVal * 10L + (long)(chLocal - 48);
                    power = 10L;
                    while ((chLocal = this.charAt(offset++)) >= '0' && chLocal <= '9') {
                        intVal = intVal * 10L + (long)(chLocal - 48);
                        power *= 10L;
                    }
                } else {
                    this.matchStat = -1;
                    return 0.0;
                }
            }
            boolean bl4 = exp = chLocal == 'e' || chLocal == 'E';
            if (exp) {
                if ((chLocal = this.charAt(offset++)) == '+' || chLocal == '-') {
                    chLocal = this.charAt(offset++);
                }
                while (chLocal >= '0' && chLocal <= '9') {
                    chLocal = this.charAt(offset++);
                }
            }
            if (quote) {
                if (chLocal != '\"') {
                    this.matchStat = -1;
                    return 0.0;
                }
                chLocal = this.charAt(offset++);
                start = this.bp + 1;
                count = offset - start - 2;
            } else {
                start = this.bp;
                count = offset - start - 1;
            }
            if (!exp && count < 18) {
                value = (double)intVal / (double)power;
                if (negative) {
                    value = -value;
                }
            } else {
                String text = this.subString(start, count);
                value = Double.parseDouble(text);
            }
        } else {
            if (chLocal == 'n' && this.charAt(offset++) == 'u' && this.charAt(offset++) == 'l' && this.charAt(offset++) == 'l') {
                this.matchStat = 5;
                double value2 = 0.0;
                chLocal = this.charAt(offset++);
                if (quote && chLocal == '\"') {
                    chLocal = this.charAt(offset++);
                }
                while (true) {
                    if (chLocal == ',') {
                        this.bp = offset;
                        this.ch = this.charAt(this.bp);
                        this.matchStat = 5;
                        this.token = 16;
                        return value2;
                    }
                    if (chLocal == ']') {
                        this.bp = offset;
                        this.ch = this.charAt(this.bp);
                        this.matchStat = 5;
                        this.token = 15;
                        return value2;
                    }
                    if (!JSONScanner.isWhitespace(chLocal)) break;
                    chLocal = this.charAt(offset++);
                }
                this.matchStat = -1;
                return 0.0;
            }
            this.matchStat = -1;
            return 0.0;
        }
        if (chLocal == seperator) {
            this.bp = offset;
            this.ch = this.charAt(this.bp);
            this.matchStat = 3;
            this.token = 16;
            return value;
        }
        this.matchStat = -1;
        return value;
    }

    @Override
    public long scanLong(char seperator) {
        long value;
        boolean negative;
        char chLocal;
        boolean quote;
        this.matchStat = 0;
        int offset = this.bp;
        boolean bl = quote = (chLocal = this.charAt(offset++)) == '\"';
        if (quote) {
            chLocal = this.charAt(offset++);
        }
        boolean bl2 = negative = chLocal == '-';
        if (negative) {
            chLocal = this.charAt(offset++);
        }
        if (chLocal >= '0' && chLocal <= '9') {
            boolean valid;
            value = chLocal - 48;
            while ((chLocal = this.charAt(offset++)) >= '0' && chLocal <= '9') {
                value = value * 10L + (long)(chLocal - 48);
            }
            if (chLocal == '.') {
                this.matchStat = -1;
                return 0L;
            }
            if (quote) {
                if (chLocal != '\"') {
                    this.matchStat = -1;
                    return 0L;
                }
                chLocal = this.charAt(offset++);
            }
            boolean bl3 = valid = value >= 0L || value == Long.MIN_VALUE && negative;
            if (!valid) {
                this.matchStat = -1;
                return 0L;
            }
        } else {
            if (chLocal == 'n' && this.charAt(offset++) == 'u' && this.charAt(offset++) == 'l' && this.charAt(offset++) == 'l') {
                this.matchStat = 5;
                long value2 = 0L;
                chLocal = this.charAt(offset++);
                if (quote && chLocal == '\"') {
                    chLocal = this.charAt(offset++);
                }
                while (true) {
                    if (chLocal == ',') {
                        this.bp = offset;
                        this.ch = this.charAt(this.bp);
                        this.matchStat = 5;
                        this.token = 16;
                        return value2;
                    }
                    if (chLocal == ']') {
                        this.bp = offset;
                        this.ch = this.charAt(this.bp);
                        this.matchStat = 5;
                        this.token = 15;
                        return value2;
                    }
                    if (!JSONScanner.isWhitespace(chLocal)) break;
                    chLocal = this.charAt(offset++);
                }
                this.matchStat = -1;
                return 0L;
            }
            this.matchStat = -1;
            return 0L;
        }
        while (true) {
            if (chLocal == seperator) {
                this.bp = offset;
                this.ch = this.charAt(this.bp);
                this.matchStat = 3;
                this.token = 16;
                return negative ? -value : value;
            }
            if (!JSONScanner.isWhitespace(chLocal)) break;
            chLocal = this.charAt(offset++);
        }
        this.matchStat = -1;
        return value;
    }

    @Override
    public Date scanDate(char seperator) {
        Date dateVal;
        char ch;
        char startChar;
        int startPos;
        block24: {
            this.matchStat = 0;
            startPos = this.bp;
            startChar = this.ch;
            int index = this.bp;
            if ((ch = this.charAt(index++)) == '\"') {
                int startIndex = index;
                int endIndex = this.indexOf('\"', startIndex);
                if (endIndex == -1) {
                    throw new JSONException("unclosed str");
                }
                int rest = endIndex - startIndex;
                this.bp = index;
                if (!this.scanISO8601DateIfMatch(false, rest)) {
                    this.bp = startPos;
                    this.ch = startChar;
                    this.matchStat = -1;
                    return null;
                }
                dateVal = this.calendar.getTime();
                ch = this.charAt(endIndex + 1);
                this.bp = startPos;
                while (true) {
                    if (ch == ',' || ch == ']') {
                        this.bp = endIndex + 1;
                        this.ch = ch;
                        break block24;
                    }
                    if (!JSONScanner.isWhitespace(ch)) break;
                    ch = this.charAt(++endIndex + 1);
                }
                this.bp = startPos;
                this.ch = startChar;
                this.matchStat = -1;
                return null;
            }
            if (ch == '-' || ch >= '0' && ch <= '9') {
                long millis = 0L;
                boolean negative = false;
                if (ch == '-') {
                    ch = this.charAt(index++);
                    negative = true;
                }
                if (ch >= '0' && ch <= '9') {
                    millis = ch - 48;
                    while ((ch = this.charAt(index++)) >= '0' && ch <= '9') {
                        millis = millis * 10L + (long)(ch - 48);
                    }
                    if (ch == ',' || ch == ']') {
                        this.bp = index - 1;
                    }
                }
                if (millis < 0L) {
                    this.bp = startPos;
                    this.ch = startChar;
                    this.matchStat = -1;
                    return null;
                }
                if (negative) {
                    millis = -millis;
                }
                dateVal = new Date(millis);
            } else if (ch == 'n' && this.charAt(index++) == 'u' && this.charAt(index++) == 'l' && this.charAt(index++) == 'l') {
                dateVal = null;
                ch = this.charAt(index);
                this.bp = index;
            } else {
                this.bp = startPos;
                this.ch = startChar;
                this.matchStat = -1;
                return null;
            }
        }
        if (ch == ',') {
            this.ch = this.charAt(++this.bp);
            this.matchStat = 3;
            return dateVal;
        }
        if ((ch = this.charAt(++this.bp)) == ',') {
            this.token = 16;
            this.ch = this.charAt(++this.bp);
        } else if (ch == ']') {
            this.token = 15;
            this.ch = this.charAt(++this.bp);
        } else if (ch == '}') {
            this.token = 13;
            this.ch = this.charAt(++this.bp);
        } else if (ch == '\u001a') {
            this.ch = (char)26;
            this.token = 20;
        } else {
            this.bp = startPos;
            this.ch = startChar;
            this.matchStat = -1;
            return null;
        }
        this.matchStat = 4;
        return dateVal;
    }

    @Override
    protected final void arrayCopy(int srcPos, char[] dest, int destPos, int length) {
        this.text.getChars(srcPos, srcPos + length, dest, destPos);
    }

    @Override
    public String info() {
        StringBuilder buf = new StringBuilder();
        int line = 1;
        int column = 1;
        int i = 0;
        while (i < this.bp) {
            char ch = this.text.charAt(i);
            if (ch == '\n') {
                column = 1;
                ++line;
            }
            ++i;
            ++column;
        }
        buf.append("pos ").append(this.bp).append(", line ").append(line).append(", column ").append(column);
        if (this.text.length() < 65535) {
            buf.append(this.text);
        } else {
            buf.append(this.text.substring(0, 65535));
        }
        return buf.toString();
    }

    @Override
    public String[] scanFieldStringArray(char[] fieldName, int argTypesCount, SymbolTable typeSymbolTable) {
        char ch;
        int offset;
        int startPos = this.bp;
        char starChar = this.ch;
        while (JSONScanner.isWhitespace(this.ch)) {
            this.next();
        }
        if (fieldName != null) {
            this.matchStat = 0;
            if (!this.charArrayCompare(fieldName)) {
                this.matchStat = -2;
                return null;
            }
            offset = this.bp + fieldName.length;
            ch = this.text.charAt(offset++);
            while (JSONScanner.isWhitespace(ch)) {
                ch = this.text.charAt(offset++);
            }
            if (ch == ':') {
                ch = this.text.charAt(offset++);
            } else {
                this.matchStat = -1;
                return null;
            }
            while (JSONScanner.isWhitespace(ch)) {
                ch = this.text.charAt(offset++);
            }
        } else {
            offset = this.bp + 1;
            ch = this.ch;
        }
        if (ch != '[') {
            if (ch == 'n' && this.text.startsWith("ull", this.bp + 1)) {
                this.bp += 4;
                this.ch = this.text.charAt(this.bp);
                return null;
            }
            this.matchStat = -1;
            return null;
        }
        this.bp = offset;
        this.ch = this.text.charAt(this.bp);
        String[] types = argTypesCount >= 0 ? new String[argTypesCount] : new String[4];
        int typeIndex = 0;
        while (true) {
            if (JSONScanner.isWhitespace(this.ch)) {
                this.next();
                continue;
            }
            if (this.ch != '\"') {
                this.bp = startPos;
                this.ch = starChar;
                this.matchStat = -1;
                return null;
            }
            String type = this.scanSymbol(typeSymbolTable, '\"');
            if (typeIndex == types.length) {
                int newCapacity = types.length + (types.length >> 1) + 1;
                String[] array = new String[newCapacity];
                System.arraycopy(types, 0, array, 0, types.length);
                types = array;
            }
            types[typeIndex++] = type;
            while (JSONScanner.isWhitespace(this.ch)) {
                this.next();
            }
            if (this.ch != ',') break;
            this.next();
        }
        if (types.length != typeIndex) {
            String[] array = new String[typeIndex];
            System.arraycopy(types, 0, array, 0, typeIndex);
            types = array;
        }
        while (JSONScanner.isWhitespace(this.ch)) {
            this.next();
        }
        if (this.ch != ']') {
            this.bp = startPos;
            this.ch = starChar;
            this.matchStat = -1;
            return null;
        }
        this.next();
        return types;
    }

    @Override
    public boolean matchField2(char[] fieldName) {
        while (JSONScanner.isWhitespace(this.ch)) {
            this.next();
        }
        if (!this.charArrayCompare(fieldName)) {
            this.matchStat = -2;
            return false;
        }
        int offset = this.bp + fieldName.length;
        char ch = this.text.charAt(offset++);
        while (JSONScanner.isWhitespace(ch)) {
            ch = this.text.charAt(offset++);
        }
        if (ch == ':') {
            this.bp = offset;
            this.ch = this.charAt(this.bp);
            return true;
        }
        this.matchStat = -2;
        return false;
    }

    @Override
    public final void skipObject() {
        this.skipObject(false);
    }

    @Override
    public final void skipObject(boolean valid) {
        int i;
        boolean quote = false;
        int braceCnt = 0;
        for (i = this.bp; i < this.text.length(); ++i) {
            char ch = this.text.charAt(i);
            if (ch == '\\') {
                if (i < this.len - 1) {
                    ++i;
                    continue;
                }
                this.ch = ch;
                this.bp = i;
                throw new JSONException("illegal str, " + this.info());
            }
            if (ch == '\"') {
                quote = !quote;
                continue;
            }
            if (ch == '{') {
                if (quote) continue;
                ++braceCnt;
                continue;
            }
            if (ch != '}' || quote || --braceCnt != -1) continue;
            this.bp = i + 1;
            if (this.bp == this.text.length()) {
                this.ch = (char)26;
                this.token = 20;
                return;
            }
            this.ch = this.text.charAt(this.bp);
            if (this.ch == ',') {
                int index;
                this.token = 16;
                this.ch = (char)((index = ++this.bp) >= this.text.length() ? 26 : (int)this.text.charAt(index));
                return;
            }
            if (this.ch == '}') {
                this.token = 13;
                this.next();
                return;
            }
            if (this.ch == ']') {
                this.token = 15;
                this.next();
                return;
            }
            this.nextToken(16);
            return;
        }
        for (int j = 0; j < this.bp; ++j) {
            if (j >= this.text.length() || this.text.charAt(j) != ' ') continue;
            ++i;
        }
        if (i == this.text.length()) {
            throw new JSONException("illegal str, " + this.info());
        }
    }

    @Override
    public final void skipArray() {
        this.skipArray(false);
    }

    public final void skipArray(boolean valid) {
        int i;
        boolean quote = false;
        int bracketCnt = 0;
        for (i = this.bp; i < this.text.length(); ++i) {
            char ch = this.text.charAt(i);
            if (ch == '\\') {
                if (i < this.len - 1) {
                    ++i;
                    continue;
                }
                this.ch = ch;
                this.bp = i;
                throw new JSONException("illegal str, " + this.info());
            }
            if (ch == '\"') {
                quote = !quote;
                continue;
            }
            if (ch == '[') {
                if (quote) continue;
                ++bracketCnt;
                continue;
            }
            if (ch == '{' && valid) {
                int index;
                this.ch = (char)((index = ++this.bp) >= this.text.length() ? 26 : (int)this.text.charAt(index));
                this.skipObject(valid);
                continue;
            }
            if (ch != ']' || quote || --bracketCnt != -1) continue;
            this.bp = i + 1;
            if (this.bp == this.text.length()) {
                this.ch = (char)26;
                this.token = 20;
                return;
            }
            this.ch = this.text.charAt(this.bp);
            this.nextToken(16);
            return;
        }
        if (i == this.text.length()) {
            throw new JSONException("illegal str, " + this.info());
        }
    }

    public final void skipString() {
        if (this.ch == '\"') {
            for (int i = this.bp + 1; i < this.text.length(); ++i) {
                char c = this.text.charAt(i);
                if (c == '\\') {
                    if (i >= this.len - 1) continue;
                    ++i;
                    continue;
                }
                if (c != '\"') continue;
                this.bp = i + 1;
                this.ch = this.text.charAt(this.bp);
                return;
            }
            throw new JSONException("unclosed str");
        }
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean seekArrayToItem(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("index must > 0, but " + index);
        }
        if (this.token == 20) {
            return false;
        }
        if (this.token != 14) {
            throw new UnsupportedOperationException();
        }
        for (int i = 0; i < index; ++i) {
            this.skipWhitespace();
            if (this.ch == '\"' || this.ch == '\'') {
                this.skipString();
                if (this.ch == ',') {
                    this.next();
                    continue;
                }
                if (this.ch == ']') {
                    this.next();
                    this.nextToken(16);
                    return false;
                }
                throw new JSONException("illegal json.");
            }
            if (this.ch == '{') {
                this.next();
                this.token = 12;
                this.skipObject(false);
            } else if (this.ch == '[') {
                this.next();
                this.token = 14;
                this.skipArray(false);
            } else {
                boolean match = false;
                for (int j = this.bp + 1; j < this.text.length(); ++j) {
                    char c = this.text.charAt(j);
                    if (c == ',') {
                        match = true;
                        this.bp = j + 1;
                        this.ch = this.charAt(this.bp);
                        break;
                    }
                    if (c != ']') continue;
                    this.bp = j + 1;
                    this.ch = this.charAt(this.bp);
                    this.nextToken();
                    return false;
                }
                if (match) continue;
                throw new JSONException("illegal json.");
            }
            if (this.token == 16) continue;
            if (this.token == 15) {
                return false;
            }
            throw new UnsupportedOperationException();
        }
        this.nextToken();
        return true;
    }

    @Override
    public int seekObjectToField(long fieldNameHash, boolean deepScan) {
        block57: {
            if (this.token == 20) {
                return -1;
            }
            if (this.token == 13 || this.token == 15) {
                this.nextToken();
                return -1;
            }
            if (this.token != 12 && this.token != 16) {
                throw new UnsupportedOperationException(JSONToken.name(this.token));
            }
            while (true) {
                int index;
                long hash;
                if (this.ch == '}') {
                    this.next();
                    this.nextToken();
                    return -1;
                }
                if (this.ch == '\u001a') {
                    return -1;
                }
                if (this.ch != '\"') {
                    this.skipWhitespace();
                }
                if (this.ch == '\"') {
                    hash = -3750763034362895579L;
                    for (int i = this.bp + 1; i < this.text.length(); ++i) {
                        char c = this.text.charAt(i);
                        if (c == '\\') {
                            if (++i == this.text.length()) {
                                throw new JSONException("unclosed str, " + this.info());
                            }
                            c = this.text.charAt(i);
                        }
                        if (c == '\"') {
                            this.bp = i + 1;
                            this.ch = (char)(this.bp >= this.text.length() ? 26 : (int)this.text.charAt(this.bp));
                            break;
                        }
                        hash ^= (long)c;
                        hash *= 1099511628211L;
                    }
                } else {
                    throw new UnsupportedOperationException();
                }
                if (hash == fieldNameHash) {
                    if (this.ch != ':') {
                        this.skipWhitespace();
                    }
                    if (this.ch == ':') {
                        this.ch = (char)((index = ++this.bp) >= this.text.length() ? 26 : (int)this.text.charAt(index));
                        if (this.ch == ',') {
                            this.ch = (char)((index = ++this.bp) >= this.text.length() ? 26 : (int)this.text.charAt(index));
                            this.token = 16;
                        } else if (this.ch == ']') {
                            this.ch = (char)((index = ++this.bp) >= this.text.length() ? 26 : (int)this.text.charAt(index));
                            this.token = 15;
                        } else if (this.ch == '}') {
                            this.ch = (char)((index = ++this.bp) >= this.text.length() ? 26 : (int)this.text.charAt(index));
                            this.token = 13;
                        } else if (this.ch >= '0' && this.ch <= '9') {
                            this.sp = 0;
                            this.pos = this.bp;
                            this.scanNumber();
                        } else {
                            this.nextToken(2);
                        }
                    }
                    return 3;
                }
                if (this.ch != ':') {
                    this.skipWhitespace();
                }
                if (this.ch != ':') {
                    throw new JSONException("illegal json, " + this.info());
                }
                this.ch = (char)((index = ++this.bp) >= this.text.length() ? 26 : (int)this.text.charAt(index));
                if (this.ch != '\"' && this.ch != '\'' && this.ch != '{' && this.ch != '[' && this.ch != '0' && this.ch != '1' && this.ch != '2' && this.ch != '3' && this.ch != '4' && this.ch != '5' && this.ch != '6' && this.ch != '7' && this.ch != '8' && this.ch != '9' && this.ch != '+' && this.ch != '-') {
                    this.skipWhitespace();
                }
                if (this.ch == '-' || this.ch == '+' || this.ch >= '0' && this.ch <= '9') {
                    this.next();
                    while (this.ch >= '0' && this.ch <= '9') {
                        this.next();
                    }
                    if (this.ch == '.') {
                        this.next();
                        while (this.ch >= '0' && this.ch <= '9') {
                            this.next();
                        }
                    }
                    if (this.ch == 'E' || this.ch == 'e') {
                        this.next();
                        if (this.ch == '-' || this.ch == '+') {
                            this.next();
                        }
                        while (this.ch >= '0' && this.ch <= '9') {
                            this.next();
                        }
                    }
                    if (this.ch != ',') {
                        this.skipWhitespace();
                    }
                    if (this.ch != ',') continue;
                    this.next();
                    continue;
                }
                if (this.ch == '\"') {
                    this.skipString();
                    if (this.ch != ',' && this.ch != '}') {
                        this.skipWhitespace();
                    }
                    if (this.ch != ',') continue;
                    this.next();
                    continue;
                }
                if (this.ch == 't') {
                    this.next();
                    if (this.ch == 'r') {
                        this.next();
                        if (this.ch == 'u') {
                            this.next();
                            if (this.ch == 'e') {
                                this.next();
                            }
                        }
                    }
                    if (this.ch != ',' && this.ch != '}') {
                        this.skipWhitespace();
                    }
                    if (this.ch != ',') continue;
                    this.next();
                    continue;
                }
                if (this.ch == 'n') {
                    this.next();
                    if (this.ch == 'u') {
                        this.next();
                        if (this.ch == 'l') {
                            this.next();
                            if (this.ch == 'l') {
                                this.next();
                            }
                        }
                    }
                    if (this.ch != ',' && this.ch != '}') {
                        this.skipWhitespace();
                    }
                    if (this.ch != ',') continue;
                    this.next();
                    continue;
                }
                if (this.ch == 'f') {
                    this.next();
                    if (this.ch == 'a') {
                        this.next();
                        if (this.ch == 'l') {
                            this.next();
                            if (this.ch == 's') {
                                this.next();
                                if (this.ch == 'e') {
                                    this.next();
                                }
                            }
                        }
                    }
                    if (this.ch != ',' && this.ch != '}') {
                        this.skipWhitespace();
                    }
                    if (this.ch != ',') continue;
                    this.next();
                    continue;
                }
                if (this.ch == '{') {
                    this.ch = (char)((index = ++this.bp) >= this.text.length() ? 26 : (int)this.text.charAt(index));
                    if (deepScan) {
                        this.token = 12;
                        return 1;
                    }
                    this.skipObject(false);
                    if (this.token != 13) continue;
                    return -1;
                }
                if (this.ch != '[') break block57;
                this.next();
                if (deepScan) {
                    this.token = 14;
                    return 2;
                }
                this.skipArray(false);
                if (this.token == 13) break;
            }
            return -1;
        }
        throw new UnsupportedOperationException();
    }

    @Override
    public int seekObjectToField(long[] fieldNameHash) {
        if (this.token != 12 && this.token != 16) {
            throw new UnsupportedOperationException();
        }
        while (true) {
            int index;
            long hash;
            if (this.ch == '}') {
                this.next();
                this.nextToken();
                this.matchStat = -1;
                return -1;
            }
            if (this.ch == '\u001a') {
                this.matchStat = -1;
                return -1;
            }
            if (this.ch != '\"') {
                this.skipWhitespace();
            }
            if (this.ch == '\"') {
                hash = -3750763034362895579L;
                for (int i = this.bp + 1; i < this.text.length(); ++i) {
                    char c = this.text.charAt(i);
                    if (c == '\\') {
                        if (++i == this.text.length()) {
                            throw new JSONException("unclosed str, " + this.info());
                        }
                        c = this.text.charAt(i);
                    }
                    if (c == '\"') {
                        this.bp = i + 1;
                        this.ch = (char)(this.bp >= this.text.length() ? 26 : (int)this.text.charAt(this.bp));
                        break;
                    }
                    hash ^= (long)c;
                    hash *= 1099511628211L;
                }
            } else {
                throw new UnsupportedOperationException();
            }
            int matchIndex = -1;
            for (int i = 0; i < fieldNameHash.length; ++i) {
                if (hash != fieldNameHash[i]) continue;
                matchIndex = i;
                break;
            }
            if (matchIndex != -1) {
                if (this.ch != ':') {
                    this.skipWhitespace();
                }
                if (this.ch == ':') {
                    this.ch = (char)((index = ++this.bp) >= this.text.length() ? 26 : (int)this.text.charAt(index));
                    if (this.ch == ',') {
                        this.ch = (char)((index = ++this.bp) >= this.text.length() ? 26 : (int)this.text.charAt(index));
                        this.token = 16;
                    } else if (this.ch == ']') {
                        this.ch = (char)((index = ++this.bp) >= this.text.length() ? 26 : (int)this.text.charAt(index));
                        this.token = 15;
                    } else if (this.ch == '}') {
                        this.ch = (char)((index = ++this.bp) >= this.text.length() ? 26 : (int)this.text.charAt(index));
                        this.token = 13;
                    } else if (this.ch >= '0' && this.ch <= '9') {
                        this.sp = 0;
                        this.pos = this.bp;
                        this.scanNumber();
                    } else {
                        this.nextToken(2);
                    }
                }
                this.matchStat = 3;
                return matchIndex;
            }
            if (this.ch != ':') {
                this.skipWhitespace();
            }
            if (this.ch != ':') {
                throw new JSONException("illegal json, " + this.info());
            }
            this.ch = (char)((index = ++this.bp) >= this.text.length() ? 26 : (int)this.text.charAt(index));
            if (this.ch != '\"' && this.ch != '\'' && this.ch != '{' && this.ch != '[' && this.ch != '0' && this.ch != '1' && this.ch != '2' && this.ch != '3' && this.ch != '4' && this.ch != '5' && this.ch != '6' && this.ch != '7' && this.ch != '8' && this.ch != '9' && this.ch != '+' && this.ch != '-') {
                this.skipWhitespace();
            }
            if (this.ch == '-' || this.ch == '+' || this.ch >= '0' && this.ch <= '9') {
                this.next();
                while (this.ch >= '0' && this.ch <= '9') {
                    this.next();
                }
                if (this.ch == '.') {
                    this.next();
                    while (this.ch >= '0' && this.ch <= '9') {
                        this.next();
                    }
                }
                if (this.ch == 'E' || this.ch == 'e') {
                    this.next();
                    if (this.ch == '-' || this.ch == '+') {
                        this.next();
                    }
                    while (this.ch >= '0' && this.ch <= '9') {
                        this.next();
                    }
                }
                if (this.ch != ',') {
                    this.skipWhitespace();
                }
                if (this.ch != ',') continue;
                this.next();
                continue;
            }
            if (this.ch == '\"') {
                this.skipString();
                if (this.ch != ',' && this.ch != '}') {
                    this.skipWhitespace();
                }
                if (this.ch != ',') continue;
                this.next();
                continue;
            }
            if (this.ch == '{') {
                this.ch = (char)((index = ++this.bp) >= this.text.length() ? 26 : (int)this.text.charAt(index));
                this.skipObject(false);
                continue;
            }
            if (this.ch != '[') break;
            this.next();
            this.skipArray(false);
        }
        throw new UnsupportedOperationException();
    }

    @Override
    public String scanTypeName(SymbolTable symbolTable) {
        int p;
        if (this.text.startsWith("\"@type\":\"", this.bp) && (p = this.text.indexOf(34, this.bp + 9)) != -1) {
            this.bp += 9;
            int h2 = 0;
            for (int i = this.bp; i < p; ++i) {
                h2 = 31 * h2 + this.text.charAt(i);
            }
            String typeName = this.addSymbol(this.bp, p - this.bp, h2, symbolTable);
            char separator = this.text.charAt(p + 1);
            if (separator != ',' && separator != ']') {
                return null;
            }
            this.bp = p + 2;
            this.ch = this.text.charAt(this.bp);
            return typeName;
        }
        return null;
    }
}

