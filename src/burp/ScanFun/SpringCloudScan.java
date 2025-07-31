/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package burp.ScanFun;

import burp.BurpExtender;
import burp.GenPoc;
import burp.IBurpExtenderCallbacks;
import burp.IExtensionHelpers;
import burp.IHttpRequestResponse;
import burp.IRequestInfo;
import burp.IResponseInfo;
import burp.Listen.IBackend;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SpringCloudScan {
    static PrintWriter stdout;
    private static final String[] poc_gateway;
    private static final byte[] succ_gateway;
    private static final String[] poc_spel;

    public static IHttpRequestResponse CloudGatewayScan(IHttpRequestResponse baseRequestResponse, IBurpExtenderCallbacks callbacks, IExtensionHelpers helpers) {
        String path;
        URL url = helpers.analyzeRequest(baseRequestResponse).getUrl();
        List<String> headers = helpers.analyzeRequest(baseRequestResponse).getHeaders();
        List<String> queue_gateway = BurpExtender.MakeQueue(url.getHost(), headers.get(0), BurpExtender.scannedDomainURL_gateway);
        ArrayList<String> path_list_gateway = new ArrayList<String>();
        for (int i = 0; i < queue_gateway.size(); ++i) {
            path = queue_gateway.get(i);
            Matcher matcher = Pattern.compile("GET (.*?) HTTP/1.1").matcher(path);
            if (!matcher.find()) continue;
            path_list_gateway.add(matcher.group(1));
        }
        BurpExtender.addScannedURL(url.getHost() + ":" + url.getPort(), path_list_gateway, BurpExtender.scannedDomainURL_gateway);
        for (int j = 0; j < queue_gateway.size(); ++j) {
            path = queue_gateway.get(j);
            for (int i = 0; i < poc_gateway.length; ++i) {
                List<int[]> matches_v3;
                IResponseInfo resp;
                String fpath = SpringCloudScan.AddPoc(path, poc_gateway[i]);
                headers.set(0, fpath);
                byte[] body = helpers.buildHttpMessage(headers, null);
                IHttpRequestResponse requestResponse = callbacks.makeHttpRequest(baseRequestResponse.getHttpService(), body);
                if (requestResponse != null && requestResponse.getResponse() != null && (resp = helpers.analyzeResponse(requestResponse.getResponse())).getStatusCode() == 200 && (matches_v3 = SpringCloudScan.getMatches(requestResponse.getResponse(), succ_gateway, helpers)).size() > 0) {
                    return requestResponse;
                }
                try {
                    Thread.currentThread();
                    Thread.sleep(Integer.parseInt(BurpExtender.sleep_value.getText()));
                    continue;
                } catch (InterruptedException e) {
                    stdout.println(e.getMessage());
                }
            }
        }
        return null;
    }

    public static IHttpRequestResponse CloudSPELScan(IHttpRequestResponse baseRequestResponse, IBurpExtenderCallbacks callbacks, IExtensionHelpers helpers, IBackend dnslog) {
        String path;
        IRequestInfo analyzeRequest = helpers.analyzeRequest(baseRequestResponse);
        List<String> headers = helpers.analyzeRequest(baseRequestResponse).getHeaders();
        String host = baseRequestResponse.getHttpService().getHost();
        String domain = "cloud" + GenPoc.Makenewhost(host) + dnslog.getNewPayload();
        List<String> poc = GenPoc.GenSpringCloudPoc(host, domain);
        String headerst = headers.get(headers.size() - 1);
        int bodyOffset = analyzeRequest.getBodyOffset();
        byte[] byte_Request = baseRequestResponse.getRequest();
        String request = new String(byte_Request);
        String body = request.substring(bodyOffset);
        byte[] byte_body = body.getBytes();
        byte[] new_Request = helpers.buildHttpMessage(headers, byte_body);
        for(String s:poc){//todo
            headers.set(headers.size() - 1, s);
            headers.add(headerst);
            IHttpRequestResponse requestResponse = callbacks.makeHttpRequest(baseRequestResponse.getHttpService(), new_Request);
            if (dnslog.CheckResult(domain)) {
                return requestResponse;
            }
            if (headers.get(0).contains("GET")) {
                headers.set(0, headers.get(0).replace("GET", "POST"));
                headers.add("Content-Type: application/x-www-form-urlencoded");
                new_Request = helpers.buildHttpMessage(headers, byte_body);
                requestResponse = callbacks.makeHttpRequest(baseRequestResponse.getHttpService(), new_Request);
                if (dnslog.CheckResult(domain)&&s.contains("java.net.InetAddress")) {
                    return requestResponse;
                }else{
                    callbacks.makeHttpRequest(baseRequestResponse.getHttpService(), new_Request);
                    if (checkCloudSpelEcho(helpers, requestResponse)){
                        return requestResponse;
                    }
                }
            }
        }
        URL url_fr = helpers.analyzeRequest(baseRequestResponse).getUrl();
        List<String> headers_fr = helpers.analyzeRequest(baseRequestResponse).getHeaders();
        List<String> queue_spel = BurpExtender.MakeQueue(url_fr.getHost(), headers_fr.get(0), BurpExtender.scannedDomainURL_spel);
        ArrayList<String> path_list_spel = new ArrayList<String>();
        for (int i = 0; i < queue_spel.size(); ++i) {
            path = queue_spel.get(i);
            Matcher matcher = Pattern.compile("GET (.*?) HTTP/1.1").matcher(path);
            if (!matcher.find()) continue;
            path_list_spel.add(matcher.group(1));
        }
        BurpExtender.addScannedURL(url_fr.getHost() + ":" + url_fr.getPort(), path_list_spel, BurpExtender.scannedDomainURL_spel);
        for (int j = 0; j < queue_spel.size(); ++j) {
            path = queue_spel.get(j);
            headers_fr = helpers.analyzeRequest(baseRequestResponse).getHeaders();
            for (int i = 0; i < poc_spel.length; ++i) {
                IResponseInfo resp;
                String domain_fr = "cloud" + GenPoc.Makenewhost(host) + dnslog.getNewPayload();
                List<String> poc_fr = GenPoc.GenSpringCloudPoc(host, domain_fr);
                String fpath = SpringCloudScan.AddPoc(path, poc_spel[i]);
                headers_fr.set(0, fpath);
                for (String s: poc_fr){
                    headers_fr.add(s);
                    byte[] byte_body_fr = body.getBytes();
                    byte[] new_Request_fr = helpers.buildHttpMessage(headers_fr, byte_body_fr);
                    IHttpRequestResponse requestResponse_fr = callbacks.makeHttpRequest(baseRequestResponse.getHttpService(), new_Request_fr);
                    if (requestResponse_fr != null && requestResponse_fr.getResponse() != null && (resp = helpers.analyzeResponse(requestResponse_fr.getResponse())).getStatusCode() == 500) {
                        try {
                            Thread.currentThread();
                            Thread.sleep(Integer.parseInt(BurpExtender.sleep_value.getText()));
                        } catch (InterruptedException e) {
                            stdout.println(e.getMessage());
                        }
                        if (dnslog.CheckResult(domain)&&s.contains("java.net.InetAddress")) {
                            return requestResponse_fr;
                        }else{
                            callbacks.makeHttpRequest(baseRequestResponse.getHttpService(), new_Request);
                            if (checkCloudSpelEcho(helpers, requestResponse_fr)){
                                return requestResponse_fr;
                            }
                        }
                    }
                    if (!headers_fr.get(0).contains("GET")) continue;
                    headers_fr.set(0, headers_fr.get(0).replace("GET", "POST"));
                    headers_fr.add("Content-Type: application/x-www-form-urlencoded");
                    new_Request_fr = helpers.buildHttpMessage(headers_fr, byte_body_fr);
                    requestResponse_fr = callbacks.makeHttpRequest(baseRequestResponse.getHttpService(), new_Request_fr);
                    try {
                        Thread.currentThread();
                        Thread.sleep(Integer.parseInt(BurpExtender.sleep_value.getText()));
                    } catch (InterruptedException e) {
                        stdout.println(e.getMessage());
                    }
                    if (dnslog.CheckResult(domain) && s.contains("java.net.InetAddress")) {
                        return requestResponse_fr;
                    }else {
                        callbacks.makeHttpRequest(baseRequestResponse.getHttpService(), new_Request);
                        if (checkCloudSpelEcho(helpers, requestResponse_fr)){
                            return requestResponse_fr;
                        }
                    }
                }


            }
        }
        return null;
    }


    public static Boolean checkCloudSpelEcho(IExtensionHelpers helpers,IHttpRequestResponse requestResponse){
        for (String h:(helpers.analyzeResponse(requestResponse.getResponse()).getHeaders())){
            if (h.contains("X-TsoSpel-Echo")){
                return true;
            }else{
                return false;
            }
        }

        return false;

    }

    public static String AddPoc(String path, String poc) {
        String fpath = "";
        String[] paths = path.split("/");
        for (int i = 0; i < paths.length; ++i) {
            fpath = i == paths.length - 1 ? fpath + paths[i] : (i == paths.length - 2 ? fpath + poc + " HTTP/" : fpath + paths[i] + "/");
        }
        return fpath;
    }

    public static List<int[]> getMatches(byte[] response, byte[] match, IExtensionHelpers helpers) {
        ArrayList<int[]> matches = new ArrayList<int[]>();
        for (int start = 0; start < response.length && (start = helpers.indexOf(response, match, true, start, response.length)) != -1; start += match.length) {
            matches.add(new int[]{start, start + match.length});
        }
        return matches;
    }

    static {
        poc_gateway = new String[]{"gateway/routes", "actuator/gateway/routes", "manage/gateway/routes", "_manage/gateway/routes", "control/gateway/routes"};
        succ_gateway = "route_id".getBytes();
        poc_spel = new String[]{"functionRouter"};
    }
}

