/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.text.lookup;

import java.util.Map;
import org.apache.commons.text.lookup.Base64DecoderStringLookup;
import org.apache.commons.text.lookup.Base64EncoderStringLookup;
import org.apache.commons.text.lookup.ConstantStringLookup;
import org.apache.commons.text.lookup.DateStringLookup;
import org.apache.commons.text.lookup.EnvironmentVariableStringLookup;
import org.apache.commons.text.lookup.FileStringLookup;
import org.apache.commons.text.lookup.InterpolatorStringLookup;
import org.apache.commons.text.lookup.JavaPlatformStringLookup;
import org.apache.commons.text.lookup.LocalHostStringLookup;
import org.apache.commons.text.lookup.MapStringLookup;
import org.apache.commons.text.lookup.NullStringLookup;
import org.apache.commons.text.lookup.PropertiesStringLookup;
import org.apache.commons.text.lookup.ResourceBundleStringLookup;
import org.apache.commons.text.lookup.ScriptStringLookup;
import org.apache.commons.text.lookup.StringLookup;
import org.apache.commons.text.lookup.SystemPropertyStringLookup;
import org.apache.commons.text.lookup.UrlDecoderStringLookup;
import org.apache.commons.text.lookup.UrlEncoderStringLookup;
import org.apache.commons.text.lookup.UrlStringLookup;
import org.apache.commons.text.lookup.XmlStringLookup;

public final class StringLookupFactory {
    public static final StringLookupFactory INSTANCE = new StringLookupFactory();
    public static final String KEY_BASE64_DECODER = "base64Decoder";
    public static final String KEY_BASE64_ENCODER = "base64Encoder";
    public static final String KEY_CONST = "const";
    public static final String KEY_DATE = "date";
    public static final String KEY_ENV = "env";
    public static final String KEY_FILE = "file";
    public static final String KEY_JAVA = "java";
    public static final String KEY_LOCALHOST = "localhost";
    public static final String KEY_PROPERTIES = "properties";
    public static final String KEY_RESOURCE_BUNDLE = "resourceBundle";
    public static final String KEY_SCRIPT = "script";
    public static final String KEY_SYS = "sys";
    public static final String KEY_URL = "url";
    public static final String KEY_URL_DECODER = "urlDecoder";
    public static final String KEY_URL_ENCODER = "urlEncoder";
    public static final String KEY_XML = "xml";

    public static void clear() {
        ConstantStringLookup.clear();
    }

    private StringLookupFactory() {
    }

    public void addDefaultStringLookups(Map<String, StringLookup> stringLookupMap) {
        if (stringLookupMap != null) {
            stringLookupMap.put("base64", Base64DecoderStringLookup.INSTANCE);
            stringLookupMap.put(KEY_BASE64_DECODER, Base64DecoderStringLookup.INSTANCE);
            stringLookupMap.put(KEY_BASE64_ENCODER, Base64EncoderStringLookup.INSTANCE);
            stringLookupMap.put(KEY_CONST, ConstantStringLookup.INSTANCE);
            stringLookupMap.put(KEY_DATE, DateStringLookup.INSTANCE);
            stringLookupMap.put(KEY_ENV, EnvironmentVariableStringLookup.INSTANCE);
            stringLookupMap.put(KEY_FILE, FileStringLookup.INSTANCE);
            stringLookupMap.put(KEY_JAVA, JavaPlatformStringLookup.INSTANCE);
            stringLookupMap.put(KEY_LOCALHOST, LocalHostStringLookup.INSTANCE);
            stringLookupMap.put(KEY_PROPERTIES, PropertiesStringLookup.INSTANCE);
            stringLookupMap.put(KEY_RESOURCE_BUNDLE, ResourceBundleStringLookup.INSTANCE);
            stringLookupMap.put(KEY_SCRIPT, ScriptStringLookup.INSTANCE);
            stringLookupMap.put(KEY_SYS, SystemPropertyStringLookup.INSTANCE);
            stringLookupMap.put(KEY_URL, UrlStringLookup.INSTANCE);
            stringLookupMap.put(KEY_URL_DECODER, UrlDecoderStringLookup.INSTANCE);
            stringLookupMap.put(KEY_URL_ENCODER, UrlEncoderStringLookup.INSTANCE);
            stringLookupMap.put(KEY_XML, XmlStringLookup.INSTANCE);
        }
    }

    public StringLookup base64DecoderStringLookup() {
        return Base64DecoderStringLookup.INSTANCE;
    }

    public StringLookup base64EncoderStringLookup() {
        return Base64EncoderStringLookup.INSTANCE;
    }

    @Deprecated
    public StringLookup base64StringLookup() {
        return Base64DecoderStringLookup.INSTANCE;
    }

    public StringLookup constantStringLookup() {
        return ConstantStringLookup.INSTANCE;
    }

    public StringLookup dateStringLookup() {
        return DateStringLookup.INSTANCE;
    }

    public StringLookup environmentVariableStringLookup() {
        return EnvironmentVariableStringLookup.INSTANCE;
    }

    public StringLookup fileStringLookup() {
        return FileStringLookup.INSTANCE;
    }

    public StringLookup interpolatorStringLookup() {
        return InterpolatorStringLookup.INSTANCE;
    }

    public StringLookup interpolatorStringLookup(Map<String, StringLookup> stringLookupMap, StringLookup defaultStringLookup, boolean addDefaultLookups) {
        return new InterpolatorStringLookup(stringLookupMap, defaultStringLookup, addDefaultLookups);
    }

    public <V> StringLookup interpolatorStringLookup(Map<String, V> map) {
        return new InterpolatorStringLookup(map);
    }

    public StringLookup interpolatorStringLookup(StringLookup defaultStringLookup) {
        return new InterpolatorStringLookup(defaultStringLookup);
    }

    public StringLookup javaPlatformStringLookup() {
        return JavaPlatformStringLookup.INSTANCE;
    }

    public StringLookup localHostStringLookup() {
        return LocalHostStringLookup.INSTANCE;
    }

    public <V> StringLookup mapStringLookup(Map<String, V> map) {
        return MapStringLookup.on(map);
    }

    public StringLookup nullStringLookup() {
        return NullStringLookup.INSTANCE;
    }

    public StringLookup propertiesStringLookup() {
        return PropertiesStringLookup.INSTANCE;
    }

    public StringLookup resourceBundleStringLookup() {
        return ResourceBundleStringLookup.INSTANCE;
    }

    public StringLookup resourceBundleStringLookup(String bundleName) {
        return new ResourceBundleStringLookup(bundleName);
    }

    public StringLookup scriptStringLookup() {
        return ScriptStringLookup.INSTANCE;
    }

    public StringLookup systemPropertyStringLookup() {
        return SystemPropertyStringLookup.INSTANCE;
    }

    public StringLookup urlDecoderStringLookup() {
        return UrlDecoderStringLookup.INSTANCE;
    }

    public StringLookup urlEncoderStringLookup() {
        return UrlEncoderStringLookup.INSTANCE;
    }

    public StringLookup urlStringLookup() {
        return UrlStringLookup.INSTANCE;
    }

    public StringLookup xmlStringLookup() {
        return XmlStringLookup.INSTANCE;
    }
}

