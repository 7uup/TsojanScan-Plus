/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package burp.ScanFun;

import burp.Common;
import burp.GenPoc;
import burp.IBurpExtenderCallbacks;
import burp.IExtensionHelpers;
import burp.IHttpRequestResponse;
import burp.IRequestInfo;
import burp.Listen.IBackend;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Text4shellScan {
    static final String[] HEADER_GUESS = new String[]{"User-Agent", "Origin", "Referer", "X-Api-Version", "Accept", "X-Forwarded-For", "Accept-Encoding", "Accept-Language", "Cache-Control"};

    public static IHttpRequestResponse ScanHeader(IHttpRequestResponse baseRequestResponse, IBurpExtenderCallbacks callbacks, IExtensionHelpers helpers, IBackend dnslog) {
        List<String> headers = helpers.analyzeRequest(baseRequestResponse).getHeaders();
        String reqMethod = helpers.analyzeRequest(baseRequestResponse).getMethod();
        String host = baseRequestResponse.getHttpService().getHost();
        for (int i = 0; i < headers.size(); ++i) {
            List<String> headersPOC = helpers.analyzeRequest(baseRequestResponse).getHeaders();
            String poc_string = GenPoc.GenText4Poc(host, dnslog).get(0);
            String[] tmp = poc_string.split("split_symbol");
            String find_domain = tmp[0];
            String text4poc = tmp[1];
            if (!headersPOC.get(i).contains(": ")) continue;
            String header_name = headersPOC.get(i).split(": ", 2)[0];
            headersPOC.set(i, header_name + ": " + text4poc);
            baseRequestResponse.getRequest();
            if (reqMethod.equalsIgnoreCase("get")) {
                byte[] body = helpers.buildHttpMessage(headersPOC, null);
                IHttpRequestResponse requestResponse = callbacks.makeHttpRequest(baseRequestResponse.getHttpService(), body);
                if (dnslog.CheckResult(find_domain)) {
                    return requestResponse;
                }
            }
            if (!reqMethod.equalsIgnoreCase("post")) continue;
            int start = helpers.analyzeRequest(baseRequestResponse.getRequest()).getBodyOffset();
            byte[] srcbody = baseRequestResponse.getRequest();
            byte[] reqbody = Common.getpostParams(start, srcbody);
            byte[] body = helpers.buildHttpMessage(headersPOC, reqbody);
            IHttpRequestResponse requestResponse = callbacks.makeHttpRequest(baseRequestResponse.getHttpService(), body);
            if (!dnslog.CheckResult(find_domain)) continue;
            return requestResponse;
        }
        List<String> headers_new_POC = helpers.analyzeRequest(baseRequestResponse).getHeaders();
        String poc_string = GenPoc.GenText4Poc(host, dnslog).get(0);
        String[] tmp = poc_string.split("split_symbol");
        String find_domain = tmp[0];
        String text4poc = tmp[1];
        for (int i = 0; i < HEADER_GUESS.length; ++i) {
            headers_new_POC.add(HEADER_GUESS[i] + ": " + text4poc);
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
            IRequestInfo analyzeRequest = helpers.analyzeRequest(baseRequestResponse);
            List<String> headers = helpers.analyzeRequest(baseRequestResponse).getHeaders();
            String reqMethod = helpers.analyzeRequest(baseRequestResponse).getMethod();
            String host = baseRequestResponse.getHttpService().getHost();
            List<String> pocs = GenPoc.GenText4Poc(host, dnslog);
            if (reqMethod.toLowerCase().equals("get")) {
                for (String poc : pocs) {
                    String[] tmp = poc.split("split_symbol");
                    String find_domain = tmp[0];
                    String text4poc = tmp[1];
                    headers = helpers.analyzeRequest(baseRequestResponse).getHeaders();
                    List<String> targertList = Common.ParamAddPocGet(headers.get(0), text4poc);
                    for (String target : targertList) {
                        headers.set(0, target);
                        byte[] body = helpers.buildHttpMessage(headers, null);
                        IHttpRequestResponse requestResponse = callbacks.makeHttpRequest(baseRequestResponse.getHttpService(), body);
                        if (!dnslog.CheckResult(find_domain)) continue;
                        return requestResponse;
                    }
                }
            }
            if (reqMethod.toLowerCase().equals("post")) {
                IHttpRequestResponse requestResponse;
                byte[] body;
                String text4poc;
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
                        text4poc = tmp[1];
                        List<String> targertList = Common.ParamAddPocPost(reqbodystr, text4poc);
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
                        text4poc = tmp[1];
                        List<String> tragetList = Common.ParamAddPocPostJson(reqbodystr, text4poc);
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
}

