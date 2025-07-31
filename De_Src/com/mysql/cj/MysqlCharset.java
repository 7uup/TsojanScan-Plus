/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj;

import com.mysql.cj.ServerVersion;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;

class MysqlCharset {
    public final String charsetName;
    public final int mblen;
    public final int priority;
    public final List<String> javaEncodingsUc = new ArrayList<String>();
    public final ServerVersion minimumVersion;

    public MysqlCharset(String charsetName, int mblen, int priority, String[] javaEncodings) {
        this(charsetName, mblen, priority, javaEncodings, new ServerVersion(0, 0, 0));
    }

    private void addEncodingMapping(String encoding) {
        String encodingUc = encoding.toUpperCase(Locale.ENGLISH);
        if (!this.javaEncodingsUc.contains(encodingUc)) {
            this.javaEncodingsUc.add(encodingUc);
        }
    }

    public MysqlCharset(String charsetName, int mblen, int priority, String[] javaEncodings, ServerVersion minimumVersion) {
        this.charsetName = charsetName;
        this.mblen = mblen;
        this.priority = priority;
        for (int i = 0; i < javaEncodings.length; ++i) {
            String encoding = javaEncodings[i];
            try {
                Charset cs = Charset.forName(encoding);
                this.addEncodingMapping(cs.name());
                Set<String> als = cs.aliases();
                Iterator<String> ali = als.iterator();
                while (ali.hasNext()) {
                    this.addEncodingMapping(ali.next());
                }
                continue;
            } catch (Exception e) {
                if (mblen != 1) continue;
                this.addEncodingMapping(encoding);
            }
        }
        if (this.javaEncodingsUc.size() == 0) {
            if (mblen > 1) {
                this.addEncodingMapping("UTF-8");
            } else {
                this.addEncodingMapping("Cp1252");
            }
        }
        this.minimumVersion = minimumVersion;
    }

    public String toString() {
        StringBuilder asString2 = new StringBuilder();
        asString2.append("[");
        asString2.append("charsetName=");
        asString2.append(this.charsetName);
        asString2.append(",mblen=");
        asString2.append(this.mblen);
        asString2.append("]");
        return asString2.toString();
    }

    boolean isOkayForVersion(ServerVersion version) {
        return version.meetsMinimum(this.minimumVersion);
    }

    String getMatchingJavaEncoding(String javaEncoding) {
        if (javaEncoding != null && this.javaEncodingsUc.contains(javaEncoding.toUpperCase(Locale.ENGLISH))) {
            return javaEncoding;
        }
        return this.javaEncodingsUc.get(0);
    }
}

