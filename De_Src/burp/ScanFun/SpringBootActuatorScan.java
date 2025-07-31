/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package burp.ScanFun;

import burp.BurpExtender;
import burp.IBurpExtenderCallbacks;
import burp.IExtensionHelpers;
import burp.IHttpRequestResponse;
import burp.IResponseInfo;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SpringBootActuatorScan {
    private static final byte[] succ_env_v1 = "/env".getBytes();
    private static final byte[] succ_refresh_v1 = "/heapdump".getBytes();
    private static final byte[] succ_env_v2 = "actuator/env".getBytes();
    private static final byte[] succ_refresh_v2 = "actuator/heapdump".getBytes();
    private static final byte[] succ_env = "systemProperties".getBytes();
    private static final byte[] succ_swagger_api = "swagger".getBytes();
    private static final byte[] succ_druid = "JavaClassPath".getBytes();
    private static final byte[] need_auth_druid = "/druid/login.html".getBytes();
    private static final String[] poc_swagger_api = new String[]{"v2/api-docs", "v1/api-docs", "v3/api-docs", "env;.js", "v2/api-docs;.js", "v1/api-docs;.js", "swagger-resources", "swagger/swagger-resources"};
    private static final String[] poc_v1 = new String[]{"env", "mappings", "gateway", "env/", "env.json", ";/env", "env;.js"};
    private static final String[] poc_v2 = new String[]{"actuator/", "actuator/env", "_manage/env", "manage/env", "control/env", "actuator/;.js", "actuator/env;.js", "actuator.json", ";/actuator"};
    private static final String[] poc_druid = new String[]{"druid/basic.json", "basic.json"};
    static PrintWriter stdout;

    public static IHttpRequestResponse ScanEnv(IHttpRequestResponse baseRequestResponse, IBurpExtenderCallbacks callbacks, IExtensionHelpers helpers) {
        URL url = helpers.analyzeRequest(baseRequestResponse).getUrl();
        List<String> headers = helpers.analyzeRequest(baseRequestResponse).getHeaders();
        List<String> queue = BurpExtender.MakeQueue(url.getHost(), headers.get(0), BurpExtender.scannedDomainURL_env);
        ArrayList<String> path_list = new ArrayList<String>();
        for (int i = 0; i < queue.size(); ++i) {
            String path = queue.get(i);
            Matcher matcher = Pattern.compile("GET (.*?) HTTP/1.1").matcher(path);
            if (!matcher.find()) continue;
            path_list.add(matcher.group(1));
        }
        ArrayList<String> scanned_path = new ArrayList<String>();
        for (int j = 0; j < queue.size(); ++j) {
            byte[] body;
            String fpath;
            int i;
            String path = queue.get(j);
            scanned_path.add(path);
            for (i = 0; i < poc_v2.length; ++i) {
                fpath = SpringBootActuatorScan.AddPoc(path, poc_v2[i]);
                headers.set(0, fpath);
                body = helpers.buildHttpMessage(headers, null);
                int a = (int)((Math.random() * 9.0 + 1.0) * 100000.0);
                try {
                    List<int[]> matches_v2_2;
                    Thread.currentThread();
                    Thread.sleep(Integer.parseInt(BurpExtender.sleep_value.getText()));
                    IHttpRequestResponse requestResponse = callbacks.makeHttpRequest(baseRequestResponse.getHttpService(), body);
                    if (requestResponse == null || requestResponse.getResponse() == null) continue;
                    byte[] response = requestResponse.getResponse();
                    IResponseInfo responseInfo = helpers.analyzeResponse(response);
                    List<int[]> matches_v2 = SpringBootActuatorScan.getMatches(requestResponse.getResponse(), succ_env_v2, helpers);
                    if (matches_v2.size() > 0 && (matches_v2_2 = SpringBootActuatorScan.getMatches(requestResponse.getResponse(), succ_refresh_v2, helpers)).size() > 0) {
                        BurpExtender.addScannedURL(url.getHost() + ":" + url.getPort(), scanned_path, BurpExtender.scannedDomainURL_env);
                        return requestResponse;
                    }
                    List<int[]> matches_v3 = SpringBootActuatorScan.getMatches(requestResponse.getResponse(), succ_env, helpers);
                    if (matches_v3.size() <= 0) continue;
                    BurpExtender.addScannedURL(url.getHost() + ":" + url.getPort(), scanned_path, BurpExtender.scannedDomainURL_env);
                    return requestResponse;
                } catch (Exception var21) {
                    stdout.println(var21.getMessage());
                }
            }
            for (i = 0; i < poc_v1.length; ++i) {
                fpath = SpringBootActuatorScan.AddPoc(path, poc_v1[i]);
                headers.set(0, fpath);
                body = helpers.buildHttpMessage(headers, null);
                try {
                    List<int[]> matches_v1_2;
                    Thread.currentThread();
                    Thread.sleep(Integer.parseInt(BurpExtender.sleep_value.getText()));
                    IHttpRequestResponse requestResponse = callbacks.makeHttpRequest(baseRequestResponse.getHttpService(), body);
                    if (requestResponse == null || requestResponse.getResponse() == null) continue;
                    byte[] response = requestResponse.getResponse();
                    List<int[]> matches_v1 = SpringBootActuatorScan.getMatches(requestResponse.getResponse(), succ_env_v1, helpers);
                    if (matches_v1.size() > 0 && (matches_v1_2 = SpringBootActuatorScan.getMatches(requestResponse.getResponse(), succ_refresh_v1, helpers)).size() > 0) {
                        BurpExtender.addScannedURL(url.getHost() + ":" + url.getPort(), scanned_path, BurpExtender.scannedDomainURL_env);
                        return requestResponse;
                    }
                    List<int[]> matches_v3 = SpringBootActuatorScan.getMatches(requestResponse.getResponse(), succ_env, helpers);
                    if (matches_v3.size() <= 0) continue;
                    BurpExtender.addScannedURL(url.getHost() + ":" + url.getPort(), scanned_path, BurpExtender.scannedDomainURL_env);
                    return requestResponse;
                } catch (Exception exception) {
                    // empty catch block
                }
            }
        }
        BurpExtender.addScannedURL(url.getHost() + ":" + url.getPort(), scanned_path, BurpExtender.scannedDomainURL_env);
        return null;
    }

    public static IHttpRequestResponse ScanSwagger(IHttpRequestResponse baseRequestResponse, IBurpExtenderCallbacks callbacks, IExtensionHelpers helpers) {
        URL url = helpers.analyzeRequest(baseRequestResponse).getUrl();
        List<String> headers = helpers.analyzeRequest(baseRequestResponse).getHeaders();
        List<String> queue = BurpExtender.MakeQueue(url.getHost(), headers.get(0), BurpExtender.scannedDomainURL_swagger);
        ArrayList<String> path_list = new ArrayList<String>();
        for (int i = 0; i < queue.size(); ++i) {
            String path = queue.get(i);
            Matcher matcher = Pattern.compile("GET (.*?) HTTP/1.1").matcher(path);
            if (!matcher.find()) continue;
            path_list.add(matcher.group(1));
        }
        ArrayList<String> scanned_path = new ArrayList<String>();
        for (int j = 0; j < queue.size(); ++j) {
            String path = queue.get(j);
            scanned_path.add(path);
            for (int i = 0; i < poc_swagger_api.length; ++i) {
                String fpath = SpringBootActuatorScan.AddPoc(path, poc_swagger_api[i]);
                headers.set(0, fpath);
                byte[] body = helpers.buildHttpMessage(headers, null);
                try {
                    String resp;
                    Thread.currentThread();
                    Thread.sleep(Integer.parseInt(BurpExtender.sleep_value.getText()));
                    IHttpRequestResponse requestResponse = callbacks.makeHttpRequest(baseRequestResponse.getHttpService(), body);
                    List<int[]> matches_api = SpringBootActuatorScan.getMatches(requestResponse.getResponse(), succ_swagger_api, helpers);
                    if (matches_api.size() <= 0 || helpers.analyzeResponse(requestResponse.getResponse()).getStatusCode() != 200 || !(resp = new String(requestResponse.getResponse())).contains("name") || !resp.contains("swaggerVersion") && (!resp.contains("description") || !resp.contains("title"))) continue;
                    BurpExtender.addScannedURL(url.getHost() + ":" + url.getPort(), scanned_path, BurpExtender.scannedDomainURL_swagger);
                    return requestResponse;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        BurpExtender.addScannedURL(url.getHost() + ":" + url.getPort(), scanned_path, BurpExtender.scannedDomainURL_swagger);
        return null;
    }

    public static IHttpRequestResponse ScanDruid(IHttpRequestResponse baseRequestResponse, IBurpExtenderCallbacks callbacks, IExtensionHelpers helpers) {
        URL url = helpers.analyzeRequest(baseRequestResponse).getUrl();
        List<String> headers = helpers.analyzeRequest(baseRequestResponse).getHeaders();
        List<String> queue = BurpExtender.MakeQueue(url.getHost(), headers.get(0), BurpExtender.scannedDomainURL_druid);
        ArrayList<String> path_list = new ArrayList<String>();
        for (int i = 0; i < queue.size(); ++i) {
            String path = queue.get(i);
            Matcher matcher = Pattern.compile("GET (.*?) HTTP/1.1").matcher(path);
            if (!matcher.find()) continue;
            path_list.add(matcher.group(1));
        }
        ArrayList<String> scanned_path = new ArrayList<String>();
        for (int j = 0; j < queue.size(); ++j) {
            String path = queue.get(j);
            BurpExtender.addScannedURL(url.getHost() + ":" + url.getPort(), scanned_path, BurpExtender.scannedDomainURL_druid);
            for (int i = 0; i < poc_druid.length; ++i) {
                String fpath = SpringBootActuatorScan.AddPoc(path, poc_druid[i]);
                headers.set(0, fpath);
                byte[] body = helpers.buildHttpMessage(headers, null);
                try {
                    Thread.currentThread();
                    Thread.sleep(Integer.parseInt(BurpExtender.sleep_value.getText()));
                    IHttpRequestResponse requestResponse = callbacks.makeHttpRequest(baseRequestResponse.getHttpService(), body);
                    if (requestResponse == null || requestResponse.getResponse() == null) continue;
                    byte[] response = requestResponse.getResponse();
                    List<int[]> matches_v3 = SpringBootActuatorScan.getMatches(requestResponse.getResponse(), succ_druid, helpers);
                    if (matches_v3.size() > 0) {
                        BurpExtender.addScannedURL(url.getHost() + ":" + url.getPort(), scanned_path, BurpExtender.scannedDomainURL_druid);
                        return requestResponse;
                    }
                    List<int[]> matches_v4 = SpringBootActuatorScan.getMatches(requestResponse.getResponse(), need_auth_druid, helpers);
                    if (matches_v4.size() <= 0) continue;
                    BurpExtender.addScannedURL(url.getHost() + ":" + url.getPort(), scanned_path, BurpExtender.scannedDomainURL_druid);
                    return requestResponse;
                } catch (Exception e) {
                    stdout.println(e.getMessage());
                }
            }
        }
        BurpExtender.addScannedURL(url.getHost() + ":" + url.getPort(), scanned_path, BurpExtender.scannedDomainURL_druid);
        return null;
    }

    public static IHttpRequestResponse CrossScan(IHttpRequestResponse baseRequestResponse, IBurpExtenderCallbacks callbacks, IExtensionHelpers helpers) {
        URL url = helpers.analyzeRequest(baseRequestResponse).getUrl();
        List<String> headers = helpers.analyzeRequest(baseRequestResponse).getHeaders();
        List<String> queue = SpringBootActuatorScan.MakeCrossQueue(url.getHost(), headers.get(0));
        ArrayList<String> path_list = new ArrayList<String>();
        for (int i = 0; i < queue.size(); ++i) {
            String path = queue.get(i);
            Matcher matcher = Pattern.compile("GET (.*?) HTTP/1.1").matcher(path);
            if (!matcher.find()) continue;
            path_list.add(matcher.group(1));
        }
        ArrayList<String> scanned_path = new ArrayList<String>();
        for (int j = 0; j < queue.size(); ++j) {
            IResponseInfo responseInfo;
            byte[] response;
            IHttpRequestResponse requestResponse2;
            byte[] body;
            String fpath;
            int i;
            String path = queue.get(j);
            for (i = 0; i < poc_v2.length; ++i) {
                fpath = SpringBootActuatorScan.AddPoc(path, poc_v2[i]);
                headers.set(0, fpath);
                body = helpers.buildHttpMessage(headers, null);
                try {
                    List<int[]> matches_v2_2;
                    Thread.currentThread();
                    Thread.sleep(Integer.parseInt(BurpExtender.sleep_value.getText()));
                    requestResponse2 = callbacks.makeHttpRequest(baseRequestResponse.getHttpService(), body);
                    if (requestResponse2 == null || requestResponse2.getResponse() == null) continue;
                    response = requestResponse2.getResponse();
                    responseInfo = helpers.analyzeResponse(response);
                    List<int[]> matches_v2 = SpringBootActuatorScan.getMatches(requestResponse2.getResponse(), succ_env_v2, helpers);
                    if (matches_v2.size() > 0 && (matches_v2_2 = SpringBootActuatorScan.getMatches(requestResponse2.getResponse(), succ_refresh_v2, helpers)).size() > 0) {
                        BurpExtender.addScannedURL(url.getHost() + ":" + url.getPort(), scanned_path, BurpExtender.scannedDomainURL_envcross);
                        return requestResponse2;
                    }
                    List<int[]> matches_v3 = SpringBootActuatorScan.getMatches(requestResponse2.getResponse(), succ_env, helpers);
                    if (matches_v3.size() <= 0) continue;
                    BurpExtender.addScannedURL(url.getHost() + ":" + url.getPort(), scanned_path, BurpExtender.scannedDomainURL_envcross);
                    return requestResponse2;
                } catch (Exception requestResponse2) {
                    // empty catch block
                }
            }
            for (i = 0; i < poc_v1.length; ++i) {
                fpath = SpringBootActuatorScan.AddPoc(path, poc_v1[i]);
                headers.set(0, fpath);
                body = helpers.buildHttpMessage(headers, null);
                try {
                    List<int[]> matches_v1_2;
                    Thread.currentThread();
                    Thread.sleep(Integer.parseInt(BurpExtender.sleep_value.getText()));
                    requestResponse2 = callbacks.makeHttpRequest(baseRequestResponse.getHttpService(), body);
                    if (requestResponse2 == null || requestResponse2.getResponse() == null) continue;
                    response = requestResponse2.getResponse();
                    responseInfo = helpers.analyzeResponse(response);
                    List<String> respHeaders = responseInfo.getHeaders();
                    List<int[]> matches_v1 = SpringBootActuatorScan.getMatches(requestResponse2.getResponse(), succ_env_v1, helpers);
                    if (matches_v1.size() > 0 && (matches_v1_2 = SpringBootActuatorScan.getMatches(requestResponse2.getResponse(), succ_refresh_v1, helpers)).size() > 0) {
                        BurpExtender.addScannedURL(url.getHost() + ":" + url.getPort(), scanned_path, BurpExtender.scannedDomainURL_envcross);
                        return requestResponse2;
                    }
                    List<int[]> matches_v3 = SpringBootActuatorScan.getMatches(requestResponse2.getResponse(), succ_env, helpers);
                    if (matches_v3.size() <= 0) continue;
                    BurpExtender.addScannedURL(url.getHost() + ":" + url.getPort(), scanned_path, BurpExtender.scannedDomainURL_envcross);
                    return requestResponse2;
                } catch (Exception exception) {
                    // empty catch block
                }
            }
        }
        BurpExtender.addScannedURL(url.getHost() + ":" + url.getPort(), scanned_path, BurpExtender.scannedDomainURL_envcross);
        return null;
    }

    public static List<int[]> getMatches(byte[] response, byte[] match, IExtensionHelpers helpers) {
        ArrayList<int[]> matches = new ArrayList<int[]>();
        for (int start = 0; start < response.length && (start = helpers.indexOf(response, match, true, start, response.length)) != -1; start += match.length) {
            matches.add(new int[]{start, start + match.length});
        }
        return matches;
    }

    public static List<String> MakeCrossQueue(String domain, String header) {
        int i;
        if (header.contains("GET /..;/ HTTP")) {
            ArrayList<String> headers = new ArrayList<String>();
            headers.add(header);
            return headers;
        }
        if (header.contains("GET /?")) {
            String[] headerr = header.split("/");
            header = "GET /..;/ HTTP/" + headerr[2];
            ArrayList<String> headers = new ArrayList<String>();
            headers.add(header);
            return headers;
        }
        String[] exts = header.split("/");
        String ext = exts[exts.length - 1];
        if (header.contains("?")) {
            int index = header.indexOf("?");
            header = header.substring(0, index) + " HTTP/" + ext;
            System.out.println(header);
        }
        ArrayList<String> queue = new ArrayList<String>();
        String test = "";
        String[] headers = header.split("/");
        String forigin = "";
        for (int o = 0; o < headers.length - 1; ++o) {
            forigin = o == headers.length - 2 ? forigin + headers[o].split(" ")[0] + "/" : forigin + headers[o] + "/";
        }
        for (i = 0; i < headers.length - 2; ++i) {
            String fianlheader = "";
            forigin = forigin + "..;/";
            fianlheader = forigin + " HTTP/" + ext;
            fianlheader = fianlheader.replace("POST ", "GET ");
            fianlheader = fianlheader.replace("OPTIONS ", "GET ");
            fianlheader = fianlheader.replace("PUT ", "GET ");
            fianlheader = fianlheader.replace("DELETE ", "GET ");
            fianlheader = fianlheader.replace("//", "/");
            queue.add(fianlheader);
        }
        for (i = queue.size() - 1; i >= 0; --i) {
            String tmp = (String)queue.get(i);
            Matcher m3 = Pattern.compile("GET (.*?) HTTP/1.1").matcher(tmp);
            if (!m3.find() || !BurpExtender.IsScannedURL(domain, m3.group(1), BurpExtender.scannedDomainURL_envcross)) continue;
            queue.remove(i);
        }
        return queue;
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

