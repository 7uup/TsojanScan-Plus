/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package burp.ScanFun;

import burp.BurpExtender;
import burp.Common;
import burp.IBurpExtenderCallbacks;
import burp.IExtensionHelpers;
import burp.IHttpRequestResponse;
import burp.utils.Config;
import java.net.URL;
import java.util.List;

public class UeditorScan {
    private static final String[] ueditor_dotnet_rce_path = new String[]{"ueditor/net/controller.ashx?action=catchimage", "Content/plugins/ueditor/net/controller.ashx?action=catchimage", "Utility/UEditor/net?action=catchimage", "Utility/UEditor?action=catchimage", "scripts/ueditor/net/controller.ashx?action=catchimage", "js/ueditor/net/controller.ashx?action=catchimage", "Static/ueditor/net/controller.ashx?action=catchimage", "Scripts/Ueditor/net/controller.ashx?action=catchimage"};

    public static IHttpRequestResponse UeditorDotNetRCEScan(IHttpRequestResponse reqres, IBurpExtenderCallbacks callbacks, IExtensionHelpers helpers, String custompath) {
        List<String> headers = helpers.analyzeRequest(reqres.getRequest()).getHeaders();
        URL url = helpers.analyzeRequest(reqres).getUrl();
        for (int i = 0; i < ueditor_dotnet_rce_path.length; ++i) {
            String res;
            headers.set(0, "GET " + custompath + "/" + ueditor_dotnet_rce_path[i] + " HTTP/1.1");
            byte[] body = helpers.buildHttpMessage(headers, null);
            IHttpRequestResponse laravelenvreqres = callbacks.makeHttpRequest(reqres.getHttpService(), body);
            try {
                Thread.currentThread();
                Thread.sleep(Integer.parseInt(Config.get("enabled_sleep_value")));
            } catch (InterruptedException e) {
                BurpExtender.stdout.println(e.getMessage());
            }
            if (laravelenvreqres == null || laravelenvreqres.getResponse() == null || !(res = Common.getResbody(laravelenvreqres.getResponse(), helpers)).contains("{\"state\":\"\u53c2\u6570\u9519\u8bef") || !res.contains("\u6ca1\u6709\u6307\u5b9a\u6293\u53d6\u6e90")) continue;
            BurpExtender.scannedDomainURL_Ueditor_dotnet_rce.add(url.getHost() + ":" + url.getPort());
            return laravelenvreqres;
        }
        BurpExtender.scannedDomainURL_Ueditor_dotnet_rce.add(url.getHost() + ":" + url.getPort());
        return null;
    }
}

