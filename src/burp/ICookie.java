/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package burp;

import java.util.Date;

public interface ICookie {
    public String getDomain();

    public String getPath();

    public Date getExpiration();

    public String getName();

    public String getValue();
}

