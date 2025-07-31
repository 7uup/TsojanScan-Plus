/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.protocol.a;

public class NativeUtils {
    private NativeUtils() {
    }

    public static byte[] encodeMysqlThreeByteInteger(int i) {
        byte[] b = new byte[]{(byte)(i & 0xFF), (byte)(i >>> 8), (byte)(i >>> 16)};
        return b;
    }

    public static void encodeMysqlThreeByteInteger(int i, byte[] b, int offset) {
        b[offset++] = (byte)(i & 0xFF);
        b[offset++] = (byte)(i >>> 8);
        b[offset] = (byte)(i >>> 16);
    }

    public static int decodeMysqlThreeByteInteger(byte[] b) {
        return NativeUtils.decodeMysqlThreeByteInteger(b, 0);
    }

    public static int decodeMysqlThreeByteInteger(byte[] b, int offset) {
        return (b[offset + 0] & 0xFF) + ((b[offset + 1] & 0xFF) << 8) + ((b[offset + 2] & 0xFF) << 16);
    }

    public static int getBinaryEncodedLength(int type) {
        switch (type) {
            case 1: {
                return 1;
            }
            case 2: 
            case 13: {
                return 2;
            }
            case 3: 
            case 4: 
            case 9: {
                return 4;
            }
            case 5: 
            case 8: {
                return 8;
            }
            case 0: 
            case 6: 
            case 7: 
            case 10: 
            case 11: 
            case 12: 
            case 15: 
            case 16: 
            case 245: 
            case 246: 
            case 249: 
            case 250: 
            case 251: 
            case 252: 
            case 253: 
            case 254: 
            case 255: {
                return 0;
            }
        }
        return -1;
    }
}

