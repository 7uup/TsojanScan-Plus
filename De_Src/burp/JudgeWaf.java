/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package burp;

import burp.IBurpExtenderCallbacks;
import burp.IExtensionHelpers;
import burp.IHttpRequestResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JudgeWaf {
    private static final String poc_cross = "..;";
    private static final String poc_usually = "etc/passwd";
    private static final byte[] Waf_1 = "\u9632\u706b\u5899".getBytes();
    private static final byte[] Waf_2 = "\u62e6\u622a".getBytes();

    public static Map ReturnLevel(IHttpRequestResponse baseRequestResponse, IExtensionHelpers helpers, IBurpExtenderCallbacks callbacks) {
        List<int[]> matches;
        HashMap<String, Short> level = new HashMap<String, Short>();
        level.put("cross", (short)1);
        level.put("uspath", (short)1);
        List<String> headers = helpers.analyzeRequest(baseRequestResponse).getHeaders();
        Short OriginStatus = helpers.analyzeResponse(baseRequestResponse.getResponse()).getStatusCode();
        String path = JudgeWaf.AddPoc(headers.get(0), poc_cross);
        headers.set(0, path);
        byte[] body = helpers.buildHttpMessage(headers, null);
        IHttpRequestResponse requestResponse = callbacks.makeHttpRequest(baseRequestResponse.getHttpService(), body);
        if (requestResponse != null && requestResponse.getResponse() != null) {
            if (helpers.analyzeResponse(requestResponse.getResponse()).getStatusCode() != OriginStatus.shortValue()) {
                matches = JudgeWaf.getMatches(requestResponse.getResponse(), Waf_1, helpers);
                if (matches.size() > 0) {
                    level.replace("cross", (short)0);
                }
                if ((matches = JudgeWaf.getMatches(requestResponse.getResponse(), Waf_2, helpers)).size() > 0) {
                    level.replace("cross", (short)0);
                }
            }
            if (helpers.analyzeResponse(requestResponse.getResponse()).getStatusCode() == 0) {
                level.replace("cross", (short)0);
            }
        } else {
            level.replace("cross", (short)0);
        }
        headers = helpers.analyzeRequest(baseRequestResponse).getHeaders();
        OriginStatus = helpers.analyzeResponse(baseRequestResponse.getResponse()).getStatusCode();
        path = JudgeWaf.AddPoc(headers.get(0), poc_usually);
        headers.set(0, path);
        body = helpers.buildHttpMessage(headers, null);
        requestResponse = callbacks.makeHttpRequest(baseRequestResponse.getHttpService(), body);
        if (requestResponse != null && requestResponse.getResponse() != null) {
            if (helpers.analyzeResponse(requestResponse.getResponse()).getStatusCode() != OriginStatus.shortValue()) {
                matches = JudgeWaf.getMatches(requestResponse.getResponse(), Waf_1, helpers);
                if (matches.size() > 0) {
                    level.replace("uspath", (short)0);
                }
                if ((matches = JudgeWaf.getMatches(requestResponse.getResponse(), Waf_2, helpers)).size() > 0) {
                    level.replace("uspath", (short)0);
                }
            }
            if (helpers.analyzeResponse(requestResponse.getResponse()).getStatusCode() == 0) {
                level.replace("uspath", (short)0);
            }
        } else {
            level.replace("uspath", (short)0);
        }
        return level;
    }

    public static List<int[]> getMatches(byte[] response, byte[] match, IExtensionHelpers helpers) {
        ArrayList<int[]> matches = new ArrayList<int[]>();
        for (int start = 0; start < response.length && (start = helpers.indexOf(response, match, true, start, response.length)) != -1; start += match.length) {
            matches.add(new int[]{start, start + match.length});
        }
        return matches;
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

