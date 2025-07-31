/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.text.lookup;

import java.util.Locale;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.lookup.AbstractStringLookup;
import org.apache.commons.text.lookup.SystemPropertyStringLookup;

final class JavaPlatformStringLookup
extends AbstractStringLookup {
    private static final String KEY_LOCALE = "locale";
    private static final String KEY_HARDWARE = "hardware";
    private static final String KEY_OS = "os";
    private static final String KEY_VM = "vm";
    private static final String KEY_RUNTIME = "runtime";
    private static final String KEY_VERSION = "version";
    static final JavaPlatformStringLookup INSTANCE = new JavaPlatformStringLookup();

    public static void main(String[] args2) {
        System.out.println(JavaPlatformStringLookup.class);
        System.out.printf("%s = %s%n", KEY_VERSION, INSTANCE.lookup(KEY_VERSION));
        System.out.printf("%s = %s%n", KEY_RUNTIME, INSTANCE.lookup(KEY_RUNTIME));
        System.out.printf("%s = %s%n", KEY_VM, INSTANCE.lookup(KEY_VM));
        System.out.printf("%s = %s%n", KEY_OS, INSTANCE.lookup(KEY_OS));
        System.out.printf("%s = %s%n", KEY_HARDWARE, INSTANCE.lookup(KEY_HARDWARE));
        System.out.printf("%s = %s%n", KEY_LOCALE, INSTANCE.lookup(KEY_LOCALE));
    }

    private JavaPlatformStringLookup() {
    }

    String getHardware() {
        return "processors: " + Runtime.getRuntime().availableProcessors() + ", architecture: " + this.getSystemProperty("os.arch") + this.getSystemProperty("-", "sun.arch.data.model") + this.getSystemProperty(", instruction sets: ", "sun.cpu.isalist");
    }

    String getLocale() {
        return "default locale: " + Locale.getDefault() + ", platform encoding: " + this.getSystemProperty("file.encoding");
    }

    String getOperatingSystem() {
        return this.getSystemProperty("os.name") + " " + this.getSystemProperty("os.version") + this.getSystemProperty(" ", "sun.os.patch.level") + ", architecture: " + this.getSystemProperty("os.arch") + this.getSystemProperty("-", "sun.arch.data.model");
    }

    String getRuntime() {
        return this.getSystemProperty("java.runtime.name") + " (build " + this.getSystemProperty("java.runtime.version") + ") from " + this.getSystemProperty("java.vendor");
    }

    private String getSystemProperty(String name) {
        return SystemPropertyStringLookup.INSTANCE.lookup(name);
    }

    private String getSystemProperty(String prefix, String name) {
        String value = this.getSystemProperty(name);
        if (StringUtils.isEmpty(value)) {
            return "";
        }
        return prefix + value;
    }

    String getVirtualMachine() {
        return this.getSystemProperty("java.vm.name") + " (build " + this.getSystemProperty("java.vm.version") + ", " + this.getSystemProperty("java.vm.info") + ")";
    }

    @Override
    public String lookup(String key) {
        if (key == null) {
            return null;
        }
        switch (key) {
            case "version": {
                return "Java version " + this.getSystemProperty("java.version");
            }
            case "runtime": {
                return this.getRuntime();
            }
            case "vm": {
                return this.getVirtualMachine();
            }
            case "os": {
                return this.getOperatingSystem();
            }
            case "hardware": {
                return this.getHardware();
            }
            case "locale": {
                return this.getLocale();
            }
        }
        throw new IllegalArgumentException(key);
    }
}

