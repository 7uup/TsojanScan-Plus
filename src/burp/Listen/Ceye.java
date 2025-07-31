/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package burp.Listen;

import burp.BurpExtender;
import burp.Listen.IBackend;
import burp.utils.Config;
import burp.utils.HttpUtils;
import burp.utils.Utils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.Response;

public class Ceye
implements IBackend {
    OkHttpClient client = new OkHttpClient().newBuilder().connectTimeout(3000L, TimeUnit.SECONDS).callTimeout(3000L, TimeUnit.SECONDS).build();
    String platformUrl = "http://api.ceye.io/";
    String rootDomain = Config.get("ceye_identifier");
    String token = Config.get("ceye_token");

    public Ceye() {
        BurpExtender.stdout.println(String.format("URL: %s , Domain: %s , Token: %s", this.platformUrl, this.rootDomain, this.token));
    }

    public boolean supportBatchCheck() {
        return false;
    }

    public String[] batchCheck(String[] payloads) {
        return new String[0];
    }

    @Override
    public String getName() {
        return "Ceye.io";
    }

    @Override
    public String getNewPayload() {
        return Utils.GetRandomString(4) + "." + this.rootDomain;
    }

    @Override
    public boolean CheckResult(String domain) {
        for (int i = 0; i < 2; ++i) {
            try {
                Thread.currentThread();
                Thread.sleep(2000L);
                Response resp = this.client.newCall(HttpUtils.GetDefaultRequest(this.platformUrl + "v1/records?token=" + this.token + "&type=dns&filter=" + domain.toLowerCase().substring(0, domain.indexOf("."))).build()).execute();
                JSONObject jObj = JSONObject.parseObject(resp.body().string().toLowerCase());
                if (!jObj.containsKey("data")) continue;
                return ((JSONArray)jObj.get("data")).size() > 0;
            } catch (Exception var4) {
                // empty catch block
            }
        }
        return false;
    }

    public boolean flushCache(int count) {
        return this.flushCache();
    }

    public boolean flushCache() {
        return true;
    }

    public boolean getState() {
        return true;
    }

    public void close() {
    }

    public int[] getSupportedPOCTypes() {
        return new int[]{1, 2};
    }
}

