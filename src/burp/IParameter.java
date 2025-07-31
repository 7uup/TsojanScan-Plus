/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package burp;

public interface IParameter {
    public static final byte PARAM_URL = 0;
    public static final byte PARAM_BODY = 1;
    public static final byte PARAM_COOKIE = 2;
    public static final byte PARAM_XML = 3;
    public static final byte PARAM_XML_ATTR = 4;
    public static final byte PARAM_MULTIPART_ATTR = 5;
    public static final byte PARAM_JSON = 6;

    public byte getType();

    public String getName();

    public String getValue();

    public int getNameStart();

    public int getNameEnd();

    public int getValueStart();

    public int getValueEnd();
}

