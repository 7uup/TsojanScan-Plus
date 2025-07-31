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
import java.util.List;

public class AxisScan {
    private static final String[] poc_axis_services = new String[]{"services", "axis/services", "axis2/services", "/api/axis"};
    private static final String[] poc_axis_dir = new String[]{"", "axis/", "axis2/"};

    public static IHttpRequestResponse AxisScan(IHttpRequestResponse reqres, IBurpExtenderCallbacks callbacks, IExtensionHelpers helpers) {
        String res;
        byte[] body;
        int i;
        List<String> headers = helpers.analyzeRequest(reqres.getRequest()).getHeaders();
        URL url = helpers.analyzeRequest(reqres).getUrl();
        for (i = 0; i < poc_axis_dir.length; ++i) {
            headers.set(0, "GET /" + poc_axis_dir[i] + " HTTP/1.1");
            body = helpers.buildHttpMessage(headers, null);
            IHttpRequestResponse axisdirreqres = callbacks.makeHttpRequest(reqres.getHttpService(), body);
            try {
                Thread.currentThread();
                Thread.sleep(Integer.parseInt(BurpExtender.sleep_value.getText()));
            } catch (InterruptedException e) {
                BurpExtender.stdout.println(e.getMessage());
            }
            if (axisdirreqres == null || axisdirreqres.getResponse() == null || !(res = Common.getResbody(axisdirreqres.getResponse(), helpers)).contains("Validate") || !res.contains("Welcome") || !res.contains("Axis") || !res.contains("deployed") || !res.contains("installation") || !res.contains("Admin")) continue;
            BurpExtender.scannedDomainURL_axis.add(url.getHost() + ":" + url.getPort());
            return axisdirreqres;
        }
        for (i = 0; i < poc_axis_services.length; ++i) {
            headers.set(0, "GET /" + poc_axis_services[i] + " HTTP/1.1");
            body = helpers.buildHttpMessage(headers, null);
            IHttpRequestResponse axisservicesreqres = callbacks.makeHttpRequest(reqres.getHttpService(), body);
            try {
                Thread.currentThread();
                Thread.sleep(Integer.parseInt(BurpExtender.sleep_value.getText()));
            } catch (InterruptedException e) {
                BurpExtender.stdout.println(e.getMessage());
            }
            if (axisservicesreqres == null || axisservicesreqres.getResponse() == null || !(res = Common.getResbody(axisservicesreqres.getResponse(), helpers)).contains("wsdl") && !res.contains("WSDL") || !res.contains("And now... Some Services") && !res.contains("Available SOAP services")) continue;
            BurpExtender.scannedDomainURL_axis.add(url.getHost() + ":" + url.getPort());
            return axisservicesreqres;
        }
        BurpExtender.scannedDomainURL_axis.add(url.getHost() + ":" + url.getPort());
        return null;
    }
}

