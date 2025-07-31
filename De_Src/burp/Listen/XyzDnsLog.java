/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package burp.Listen;

import burp.BurpExtender;
import burp.Listen.IBackend;
import burp.utils.HttpUtils;
import burp.utils.Utils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.Response;

public class XyzDnsLog
implements IBackend {
    OkHttpClient client = new OkHttpClient().newBuilder().connectTimeout(3000L, TimeUnit.SECONDS).callTimeout(3000L, TimeUnit.SECONDS).build();
    String platformUrl = "http://47.100.85.78:58000/";
    String rootDomain;
    String token;

    public XyzDnsLog() {
        this.getRootDomain();
    }

    public void getRootDomain() {
        try {
            Response resp = this.client.newCall(HttpUtils.GetDefaultRequest(this.platformUrl + "/new_gen").build()).execute();
            JSONObject jsonObject = JSON.parseObject(resp.body().string());
            this.rootDomain = jsonObject.getString("domain");
            this.token = jsonObject.getString("token");
            BurpExtender.stdout.println(String.format("URL: %s , Domain: %s , Token: %s", this.platformUrl, this.rootDomain, this.token));
        } catch (Exception ex) {
            BurpExtender.stdout.println("initDomain failed: " + ex.getMessage());
        }
    }

    @Override
    public String getName() {
        return "XyzDnsLog";
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
                Response resp = this.client.newCall(HttpUtils.GetDefaultRequest(this.platformUrl + this.token + "?t=0." + Math.abs(Utils.getRandomLong())).build()).execute();
                if (resp.header("Content-Type").contains("json")) {
                    String dnsLogResultCache = resp.body().string().toLowerCase();
                    return dnsLogResultCache.contains(domain.toLowerCase());
                }
                BurpExtender.stdout.println("Get Dnslog Result Failed!");
                return false;
            } catch (Exception var4) {
                continue;
            }
        }
        return false;
    }
}

