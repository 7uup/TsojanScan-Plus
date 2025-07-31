/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package burp.utils;

import burp.utils.Utils;

public class Config {
    public static final String ENABLED_SCAN = "enabled_scan";
    public static final String ENABLED_AXIS = "enabled_axis";
    public static final String ENABLED_NACOS = "enabled_nacos";
    public static final String ENABLED_JBOSS = "enabled_Jboss";
    public static final String ENABLED_BYPASSCHECK = "enabled_BypassCheck";
    public static final String ENABLED_SPRINGCROSS = "enabled_springcross";
    public static final String ENABLED_LOG4J = "enabled_log4j";
    public static final String ENABLED_TEXT4SHELL = "enabled_text4shell";
    public static final String ENABLED_FASTJSON = "enabled_fastjson";
    public static final String ENABLED_SHIRO = "enabled_shiro";
    public static final String ENABLED_SPRINGCLOUD = "enabled_springcloud";
    public static final String ENABLED_SQLI = "enabled_sqli";
    public static final String ENABLED_WEBLOGIC = "enabled_weblogic";
    public static final String ENABLED_THINKPHP = "enabled_thinkphp";
    public static final String ENABLED_LARAVEL = "enabled_laravel";
    public static final String ENABLED_SPRINGENV = "enabled_springenv";
    public static final String ENABLED_UEDITOR = "enabled_ueditor";
    public static final String ENABLED_DOMAIN_BLACKLIST = "enabled_domain_blacklist";
    public static final String ENABLED_SLEEP = "enabled_sleep";
    public static final String ENABLED_SLEEP_VALUE = "enabled_sleep_value";
    public static final String DNSLOG_CHOOSE = "dnslog_choose";
    public static final String CEYE_IDENTIFIER = "ceye_identifier";
    public static final String CEYE_TOKEN = "ceye_token";
    public static final String DOMAIN_BLACKLIST = "domain_blacklist";

    public static String get(String name) {
        return Utils.Callback.loadExtensionSetting(name);
    }

    public static String get(String name, String defaultValue) {
        String val = Utils.Callback.loadExtensionSetting(name);
        return val == null || val.isEmpty() ? defaultValue : val;
    }

    public static boolean getBoolean(String name, boolean defaultValue) {
        String val = Utils.Callback.loadExtensionSetting(name);
        return val == null || val.isEmpty() ? defaultValue : val.equals("1");
    }

    public static void set(String name, String value) {
        Utils.Callback.saveExtensionSetting(name, value);
    }

    public static void setBoolean(String name, boolean value) {
        Utils.Callback.saveExtensionSetting(name, value ? "1" : "0");
    }
}

