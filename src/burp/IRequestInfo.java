/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package burp;

import burp.IParameter;
import java.net.URL;
import java.util.List;

public interface IRequestInfo {
    public static final byte CONTENT_TYPE_NONE = 0;
    public static final byte CONTENT_TYPE_URL_ENCODED = 1;
    public static final byte CONTENT_TYPE_MULTIPART = 2;
    public static final byte CONTENT_TYPE_XML = 3;
    public static final byte CONTENT_TYPE_JSON = 4;
    public static final byte CONTENT_TYPE_AMF = 5;
    public static final byte CONTENT_TYPE_UNKNOWN = -1;

    public String getMethod();

    public URL getUrl();

    public List<String> getHeaders();

    public List<IParameter> getParameters();

    public int getBodyOffset();

    public byte getContentType();
}

