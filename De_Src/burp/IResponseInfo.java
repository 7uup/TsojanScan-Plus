/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package burp;

import burp.ICookie;
import java.util.List;

public interface IResponseInfo {
    public List<String> getHeaders();

    public int getBodyOffset();

    public short getStatusCode();

    public List<ICookie> getCookies();

    public String getStatedMimeType();

    public String getInferredMimeType();
}

