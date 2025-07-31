/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package burp.utils;

import burp.utils.Utils;
import java.util.Calendar;
import okhttp3.CacheControl;
import okhttp3.Request;

public class HttpUtils {
    public static CacheControl NoCache = new CacheControl.Builder().noCache().noStore().build();

    public static Request.Builder GetDefaultRequest(String url) {
        int fakeFirefoxVersion = Utils.GetRandomNumber(45, 94 + Calendar.getInstance().get(1) - 2021);
        Request.Builder requestBuilder = new Request.Builder().url(url);
        requestBuilder.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:" + fakeFirefoxVersion + ".0) Gecko/20100101 Firefox/" + fakeFirefoxVersion + ".0");
        return requestBuilder.cacheControl(NoCache);
    }
}

