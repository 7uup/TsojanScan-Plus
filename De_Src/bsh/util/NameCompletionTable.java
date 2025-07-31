/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package bsh.util;

import bsh.NameSource;
import bsh.StringUtil;
import bsh.util.NameCompletion;
import java.util.ArrayList;
import java.util.List;

public class NameCompletionTable
extends ArrayList
implements NameCompletion {
    NameCompletionTable table;
    List sources;

    public void add(NameCompletionTable table) {
        if (this.table != null) {
            throw new RuntimeException("Unimplemented usage error");
        }
        this.table = table;
    }

    public void add(NameSource source2) {
        if (this.sources == null) {
            this.sources = new ArrayList();
        }
        this.sources.add(source2);
    }

    protected void getMatchingNames(String part, List found) {
        int i;
        for (i = 0; i < this.size(); ++i) {
            String name = (String)this.get(i);
            if (!name.startsWith(part)) continue;
            found.add(name);
        }
        if (this.table != null) {
            this.table.getMatchingNames(part, found);
        }
        if (this.sources != null) {
            for (i = 0; i < this.sources.size(); ++i) {
                NameSource src = (NameSource)this.sources.get(i);
                String[] names = src.getAllNames();
                for (int j = 0; j < names.length; ++j) {
                    if (!names[j].startsWith(part)) continue;
                    found.add(names[j]);
                }
            }
        }
    }

    public String[] completeName(String part) {
        ArrayList found = new ArrayList();
        this.getMatchingNames(part, found);
        if (found.size() == 0) {
            return new String[0];
        }
        String maxCommon = (String)found.get(0);
        for (int i = 1; i < found.size() && maxCommon.length() > 0 && !(maxCommon = StringUtil.maxCommonPrefix(maxCommon, (String)found.get(i))).equals(part); ++i) {
        }
        if (maxCommon.length() > part.length()) {
            return new String[]{maxCommon};
        }
        return found.toArray(new String[0]);
    }
}

