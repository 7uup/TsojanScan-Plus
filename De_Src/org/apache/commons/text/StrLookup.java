/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.text;

import java.util.Map;
import java.util.ResourceBundle;
import org.apache.commons.text.lookup.StringLookup;

@Deprecated
public abstract class StrLookup<V>
implements StringLookup {
    private static final StrLookup<String> NONE_LOOKUP = new MapStrLookup<String>(null);
    private static final StrLookup<String> SYSTEM_PROPERTIES_LOOKUP = new SystemPropertiesStrLookup();

    public static StrLookup<?> noneLookup() {
        return NONE_LOOKUP;
    }

    public static StrLookup<String> systemPropertiesLookup() {
        return SYSTEM_PROPERTIES_LOOKUP;
    }

    public static <V> StrLookup<V> mapLookup(Map<String, V> map) {
        return new MapStrLookup<V>(map);
    }

    public static StrLookup<String> resourceBundleLookup(ResourceBundle resourceBundle) {
        return new ResourceBundleLookup(resourceBundle);
    }

    protected StrLookup() {
    }

    private static final class SystemPropertiesStrLookup
    extends StrLookup<String> {
        private SystemPropertiesStrLookup() {
        }

        @Override
        public String lookup(String key) {
            if (key.length() > 0) {
                try {
                    return System.getProperty(key);
                } catch (SecurityException scex) {
                    return null;
                }
            }
            return null;
        }
    }

    private static final class ResourceBundleLookup
    extends StrLookup<String> {
        private final ResourceBundle resourceBundle;

        private ResourceBundleLookup(ResourceBundle resourceBundle) {
            this.resourceBundle = resourceBundle;
        }

        @Override
        public String lookup(String key) {
            if (this.resourceBundle == null || key == null || !this.resourceBundle.containsKey(key)) {
                return null;
            }
            return this.resourceBundle.getString(key);
        }

        public String toString() {
            return super.toString() + " [resourceBundle=" + this.resourceBundle + "]";
        }
    }

    static class MapStrLookup<V>
    extends StrLookup<V> {
        private final Map<String, V> map;

        MapStrLookup(Map<String, V> map) {
            this.map = map;
        }

        @Override
        public String lookup(String key) {
            if (this.map == null) {
                return null;
            }
            V obj = this.map.get(key);
            if (obj == null) {
                return null;
            }
            return obj.toString();
        }

        public String toString() {
            return super.toString() + " [map=" + this.map + "]";
        }
    }
}

