/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.protocol;

import com.mysql.cj.exceptions.AssertionFailedException;
import com.mysql.cj.util.StringUtils;
import java.security.DigestException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Security {
    private static int CACHING_SHA2_DIGEST_LENGTH = 32;

    public static void xorString(byte[] from, byte[] to, byte[] scramble, int length) {
        int scrambleLength = scramble.length;
        for (int pos = 0; pos < length; ++pos) {
            to[pos] = (byte)(from[pos] ^ scramble[pos % scrambleLength]);
        }
    }

    public static byte[] scramble411(String password, byte[] seed, String passwordEncoding) {
        byte[] passwordBytes = passwordEncoding == null || passwordEncoding.length() == 0 ? StringUtils.getBytes(password) : StringUtils.getBytes(password, passwordEncoding);
        return Security.scramble411(passwordBytes, seed);
    }

    public static byte[] scramble411(byte[] password, byte[] seed) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException ex) {
            throw new AssertionFailedException(ex);
        }
        byte[] passwordHashStage1 = md.digest(password);
        md.reset();
        byte[] passwordHashStage2 = md.digest(passwordHashStage1);
        md.reset();
        md.update(seed);
        md.update(passwordHashStage2);
        byte[] toBeXord = md.digest();
        int numToXor = toBeXord.length;
        for (int i = 0; i < numToXor; ++i) {
            toBeXord[i] = (byte)(toBeXord[i] ^ passwordHashStage1[i]);
        }
        return toBeXord;
    }

    public static byte[] scrambleCachingSha2(byte[] password, byte[] seed) throws DigestException {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException ex) {
            throw new AssertionFailedException(ex);
        }
        byte[] dig1 = new byte[CACHING_SHA2_DIGEST_LENGTH];
        byte[] dig2 = new byte[CACHING_SHA2_DIGEST_LENGTH];
        byte[] scramble1 = new byte[CACHING_SHA2_DIGEST_LENGTH];
        md.update(password, 0, password.length);
        md.digest(dig1, 0, CACHING_SHA2_DIGEST_LENGTH);
        md.reset();
        md.update(dig1, 0, dig1.length);
        md.digest(dig2, 0, CACHING_SHA2_DIGEST_LENGTH);
        md.reset();
        md.update(dig2, 0, dig1.length);
        md.update(seed, 0, seed.length);
        md.digest(scramble1, 0, CACHING_SHA2_DIGEST_LENGTH);
        byte[] mysqlScrambleBuff = new byte[CACHING_SHA2_DIGEST_LENGTH];
        Security.xorString(dig1, mysqlScrambleBuff, scramble1, CACHING_SHA2_DIGEST_LENGTH);
        return mysqlScrambleBuff;
    }

    private Security() {
    }
}

