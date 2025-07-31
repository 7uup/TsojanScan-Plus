/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package bsh;

public interface NameSource {
    public String[] getAllNames();

    public void addNameSourceListener(Listener var1);

    public static interface Listener {
        public void nameSourceChanged(NameSource var1);
    }
}

