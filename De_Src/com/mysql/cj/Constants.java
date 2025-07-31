/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj;

import com.mysql.cj.Messages;

public class Constants {
    public static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
    public static final String MILLIS_I18N = Messages.getString("Milliseconds");
    public static final byte[] SLASH_STAR_SPACE_AS_BYTES = new byte[]{47, 42, 32};
    public static final byte[] SPACE_STAR_SLASH_SPACE_AS_BYTES = new byte[]{32, 42, 47, 32};
    public static final String JVM_VENDOR = System.getProperty("java.vendor");
    public static final String JVM_VERSION = System.getProperty("java.version");
    public static final String OS_NAME = System.getProperty("os.name");
    public static final String OS_ARCH = System.getProperty("os.arch");
    public static final String OS_VERSION = System.getProperty("os.version");
    public static final String PLATFORM_ENCODING = System.getProperty("file.encoding");
    public static final String CJ_NAME = "MySQL Connector/J";
    public static final String CJ_FULL_NAME = "mysql-connector-java-8.0.12";
    public static final String CJ_REVISION = "24766725dc6e017025532146d94c6e6c488fb8f1";
    public static final String CJ_VERSION = "8.0.12";
    public static final String CJ_MAJOR_VERSION = "8";
    public static final String CJ_MINOR_VERSION = "0";
    public static final String CJ_LICENSE = "GPL";

    private Constants() {
    }
}

